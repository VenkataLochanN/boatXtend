package com.ido.alexa;

import android.text.TextUtils;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.alexa.bean.AlexaOperationMode;
import com.ido.alexa.bean.AlexaRangerControllerBean;
import com.ido.alexa.bean.AlexaToggleControllerBean;
import com.ido.alexa.bean.EndpointIDBean;
import com.ido.alexa.callbacks.AlexaSettingCallBack;
import com.ido.alexa.callbacks.HandleDirectiveCallback;
import com.ido.alexa.callbacks.ImplAsyncCallback;
import com.ido.alexa.callbacks.QueryDataCallback;
import com.ido.alexa.data.ApiResponse;
import com.ido.alexa.data.AvsClearIndicator;
import com.ido.alexa.data.AvsDeleteAlertItem;
import com.ido.alexa.data.AvsDeleteAlertsItem;
import com.ido.alexa.data.AvsItem;
import com.ido.alexa.data.AvsReportStateItem;
import com.ido.alexa.data.AvsResetUserItem;
import com.ido.alexa.data.AvsSetAlertItem;
import com.ido.alexa.data.AvsSetIndicator;
import com.ido.alexa.data.AvsStopCaptureItem;
import com.ido.alexa.data.Directive;
import com.ido.alexa.data.Event;
import com.ido.alexa.data.capability.AvsAdjustRangeValueItem;
import com.ido.alexa.data.capability.AvsSetRangeValueItem;
import com.ido.alexa.data.capability.AvsStateReportPropertiesBean;
import com.ido.alexa.data.capability.AvsToggleControllerItem;
import com.ido.alexa.log.AlexaLogUtil;
import com.ido.alexa.manager.AlexaAudioEventManger;
import com.ido.alexa.manager.AlexaEndpointIdDefine;
import com.ido.alexa.manager.LoadSmartHomeManager;
import com.ido.alexa.manager.SpManager;
import com.ido.alexa.net.ApiParser;
import com.ido.alexa.util.AlexaCustomSkillUtil;
import com.ido.alexa.util.AlexaSendCmdToDeviceUtil;
import com.ido.alexa.util.GsonUtil;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.life.bean.SavePrivateSafeSettingBean;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class ResponseHandler {
    private static final String TAG = "Alexa-ReponseHandler";
    private static ResponseHandler instance;
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'", Locale.getDefault());
    private AvsReportStateItem avsReportStateItem;
    private List<HandleDirectiveCallback> mHandleDirectiveCallback = new ArrayList();
    private final QueryDataCallback queryDataCallback = new QueryDataCallback() { // from class: com.ido.alexa.ResponseHandler.2
        @Override // com.ido.alexa.callbacks.QueryDataCallback
        public void callbackData(long j) {
            AlexaApi.unregisterQueryDataCallback();
            ArrayList arrayList = new ArrayList();
            AvsStateReportPropertiesBean avsStateReportPropertiesBean = new AvsStateReportPropertiesBean();
            avsStateReportPropertiesBean.setNamespace("Alexa.RangeController");
            avsStateReportPropertiesBean.setInstance(ResponseHandler.this.avsReportStateItem.getInstance());
            avsStateReportPropertiesBean.setName("rangeValue");
            avsStateReportPropertiesBean.setValue(Long.valueOf(j));
            avsStateReportPropertiesBean.setTimeOfSample(ResponseHandler.simpleDateFormat.format(new Date()));
            avsStateReportPropertiesBean.setUncertaintyInMilliseconds(500);
            arrayList.add(avsStateReportPropertiesBean);
            AlexaAudioEventManger.getInstance().sendEvent(Event.getStateReportEvent(ResponseHandler.this.avsReportStateItem.getMessageId(), ResponseHandler.this.avsReportStateItem.getCorrelationToken(), ResponseHandler.this.avsReportStateItem.getEndpointId(), GsonUtil.toJson(arrayList)), null);
        }
    };

    private ResponseHandler() {
    }

    public static ResponseHandler getInstance() {
        if (instance == null) {
            instance = new ResponseHandler();
        }
        return instance;
    }

    void registerHandleDirectiveCallback(HandleDirectiveCallback handleDirectiveCallback) {
        this.mHandleDirectiveCallback.add(handleDirectiveCallback);
    }

    void unregisterHandleDirectiveCallback(HandleDirectiveCallback handleDirectiveCallback) {
        this.mHandleDirectiveCallback.remove(handleDirectiveCallback);
    }

    public void handleDirective(Directive directive) {
        AvsItem directive2 = ApiParser.parseDirective(directive, null);
        if (directive2 == null) {
            return;
        }
        ApiResponse apiResponse = new ApiResponse();
        ArrayList arrayList = new ArrayList();
        arrayList.add(directive2);
        apiResponse.setAvsItems(arrayList);
        handleItems(apiResponse);
    }

    private void handleItems(ApiResponse apiResponse) {
        if (apiResponse == null || apiResponse.getAvsItems() == null) {
            return;
        }
        for (AvsItem avsItem : apiResponse.getAvsItems()) {
            AlexaLogUtil.d(TAG, "Handling AvsItem: " + avsItem.toString());
            if (avsItem instanceof AvsSetAlertItem) {
                AvsSetAlertItem avsSetAlertItem = (AvsSetAlertItem) avsItem;
                if (avsSetAlertItem.isAlarm()) {
                    setAlarm(avsSetAlertItem);
                } else if (avsSetAlertItem.isTimer()) {
                    setTimer(avsSetAlertItem);
                } else if (avsSetAlertItem.isReminder()) {
                    setReminder(avsSetAlertItem);
                }
            } else if (avsItem instanceof AvsDeleteAlertItem) {
                cancelAlarm((AvsDeleteAlertItem) avsItem);
            } else if (avsItem instanceof AvsDeleteAlertsItem) {
                cancelAlarms((AvsDeleteAlertsItem) avsItem);
            } else if (avsItem instanceof AvsResetUserItem) {
                AlexaApi.sendRecord(AlexaConstant.AUDIO_PCM, new byte[1], new ImplAsyncCallback<ApiResponse, Throwable>() { // from class: com.ido.alexa.ResponseHandler.1
                    @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
                    public void authorize() {
                        super.authorize();
                        ResponseHandler.this.eauthorizerAmazon();
                    }
                });
            } else if (avsItem instanceof AvsStopCaptureItem) {
                stopRecord((AvsStopCaptureItem) avsItem);
            } else if (avsItem instanceof AvsSetIndicator) {
                handlerNotification(false);
            } else if (avsItem instanceof AvsClearIndicator) {
                handlerNotification(true);
            } else if (avsItem instanceof AvsToggleControllerItem) {
                handleToggleController((AvsToggleControllerItem) avsItem);
            } else if (avsItem instanceof AvsAdjustRangeValueItem) {
                handleAdjustRangeController((AvsAdjustRangeValueItem) avsItem);
            } else if (avsItem instanceof AvsSetRangeValueItem) {
                handleSetRangeController((AvsSetRangeValueItem) avsItem);
            } else if (avsItem instanceof AvsReportStateItem) {
                handleStateReport((AvsReportStateItem) avsItem);
            }
        }
    }

    private void reportToggleState(AvsReportStateItem avsReportStateItem, List<AlexaToggleControllerBean.SkillBean> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<AlexaToggleControllerBean.SkillBean> it = list.iterator();
        while (it.hasNext()) {
            for (AlexaToggleControllerBean.SkillBean.CapabilityBean capabilityBean : it.next().getCapability()) {
                AvsStateReportPropertiesBean avsStateReportPropertiesBean = new AvsStateReportPropertiesBean();
                avsStateReportPropertiesBean.setNamespace("Alexa.ToggleController");
                avsStateReportPropertiesBean.setInstance(capabilityBean.getInstance());
                avsStateReportPropertiesBean.setName("toggleState");
                avsStateReportPropertiesBean.setValue(SavePrivateSafeSettingBean.OFF);
                avsStateReportPropertiesBean.setTimeOfSample(simpleDateFormat.format(new Date()));
                avsStateReportPropertiesBean.setUncertaintyInMilliseconds(500);
                arrayList.add(avsStateReportPropertiesBean);
            }
        }
        AlexaAudioEventManger.getInstance().sendEvent(Event.getStateReportEvent(avsReportStateItem.getMessageId(), avsReportStateItem.getCorrelationToken(), avsReportStateItem.getEndpointId(), GsonUtil.toJson(arrayList)), null);
    }

    private void reportRangleState(String str) {
        EndpointIDBean next;
        boolean zIsDevice7261;
        String str2 = SpManager.getEndpointId() + "-";
        Iterator<EndpointIDBean> it = AlexaEndpointIdDefine.getEndpoints().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                zIsDevice7261 = false;
                break;
            }
            next = it.next();
            if (TextUtils.equals(str, str2 + next.getEndpointId())) {
                zIsDevice7261 = isDevice7261(next.getSettingType());
                break;
            }
        }
        if (next != null) {
            Iterator<AlexaRangerControllerBean.SkillBean> it2 = LoadSmartHomeManager.getAlexaRangeControllerBean().getSkill().iterator();
            while (it2.hasNext()) {
                Iterator<AlexaRangerControllerBean.SkillBean.CapabilityBean> it3 = it2.next().getCapability().iterator();
                while (true) {
                    if (it3.hasNext()) {
                        AlexaRangerControllerBean.SkillBean.CapabilityBean next2 = it3.next();
                        if (TextUtils.equals(next.getEndpointId(), next2.getInstance())) {
                            if (!TextUtils.equals(AlexaEndpointIdDefine.BRIGHTNESS, next2.getInstance())) {
                                if (zIsDevice7261 || TextUtils.equals(AlexaEndpointIdDefine.WEEKLY_AVG_HR, next2.getInstance()) || TextUtils.equals(AlexaEndpointIdDefine.MONTHLY_AVG_HR, next2.getInstance()) || TextUtils.equals(AlexaEndpointIdDefine.YEARLY_AVG_HR, next2.getInstance())) {
                                    handlerRangeControlerResult();
                                } else {
                                    handlerRangeControlerResult();
                                    AlexaCustomSkillUtil.openDeviceView(next2.getSutype());
                                }
                            }
                            this.avsReportStateItem.setInstance(next2.getInstance());
                            AlexaApi.registerQueryDataCallback(this.queryDataCallback);
                            AlexaSettingCallBack.onCallback(next.getSettingType(), "");
                        }
                    }
                }
            }
        }
    }

    private void handleStateReport(AvsReportStateItem avsReportStateItem) {
        this.avsReportStateItem = avsReportStateItem;
        String endpointId = avsReportStateItem.getEndpointId();
        if (TextUtils.equals(endpointId, SpManager.getEndpointId())) {
            reportToggleState(avsReportStateItem, LoadSmartHomeManager.getOpenViewToggleControllerBean().getSkill());
            return;
        }
        if (TextUtils.equals(endpointId, SpManager.getEndpointId() + "-" + AlexaEndpointIdDefine.SPORT)) {
            reportToggleState(avsReportStateItem, LoadSmartHomeManager.getAlexaSportToggleControllerBean().getSkill());
        } else {
            reportRangleState(endpointId);
        }
    }

    public boolean isDevice7261(AlexaSettingCallBack.AlexaSettingType alexaSettingType) {
        BLEDevice deviceInfo = getDeviceInfo();
        AlexaLogUtil.printAndSave("isDevice7261 = " + deviceInfo.toString());
        if (deviceInfo.mDeviceId == 7261) {
            return alexaSettingType == AlexaSettingCallBack.AlexaSettingType.WORKOUT_HISTORY || alexaSettingType == AlexaSettingCallBack.AlexaSettingType.TODAY_SWIMMING_STATISTICS;
        }
        return false;
    }

    private BLEDevice getDeviceInfo() {
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (currentDeviceInfo == null) {
            currentDeviceInfo = new BLEDevice();
        }
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        if (basicInfo != null) {
            currentDeviceInfo.mDeviceId = basicInfo.deivceId;
            if (currentDeviceInfo.mDeviceId <= 0) {
                currentDeviceInfo.mDeviceId = basicInfo.deivceId;
            }
        }
        return currentDeviceInfo;
    }

    private void handleSetRangeController(AvsSetRangeValueItem avsSetRangeValueItem) {
        AlexaAudioEventManger.getInstance().sendEvent(Event.getResponseControllerReplyEvent(Event.getState(avsSetRangeValueItem.getNamespace(), "rangeValue", avsSetRangeValueItem.getInstance(), Integer.valueOf(avsSetRangeValueItem.getRangeValue())), avsSetRangeValueItem.getCorrelationToken(), avsSetRangeValueItem.getEndpointId()), null);
        if (TextUtils.equals(AlexaEndpointIdDefine.BRIGHTNESS, avsSetRangeValueItem.getInstance())) {
            handlerRangeControlerResult();
            AlexaOperationMode alexaOperationMode = new AlexaOperationMode();
            alexaOperationMode.setUi_type(1);
            if (AlexaSendCmdToDeviceUtil.getSupportFunctionInfo().v2_set_alexa_operation_100brightness) {
                alexaOperationMode.setOperation_type(8);
            } else {
                alexaOperationMode.setOperation_type(4);
            }
            alexaOperationMode.setCmd(avsSetRangeValueItem.getRangeValue());
            AlexaCustomSkillUtil.brightnessControl(alexaOperationMode);
        }
    }

    private void handleAdjustRangeController(AvsAdjustRangeValueItem avsAdjustRangeValueItem) {
        String state;
        if (TextUtils.equals(AlexaEndpointIdDefine.BRIGHTNESS, avsAdjustRangeValueItem.getInstance())) {
            handlerRangeControlerResult();
            AlexaOperationMode alexaOperationMode = new AlexaOperationMode();
            alexaOperationMode.setUi_type(1);
            if (avsAdjustRangeValueItem.isRangeValueDeltaDefault()) {
                boolean z = AlexaSendCmdToDeviceUtil.getSupportFunctionInfo().v2_set_alexa_operation_100brightness;
                if (avsAdjustRangeValueItem.isIncrease()) {
                    alexaOperationMode.setOperation_type(z ? 6 : 0);
                } else {
                    alexaOperationMode.setOperation_type(z ? 7 : 1);
                }
                state = Event.getState(avsAdjustRangeValueItem.getNamespace(), "rangeValue", avsAdjustRangeValueItem.getInstance(), 1);
            } else {
                alexaOperationMode.setOperation_type(4);
                alexaOperationMode.setCmd(Math.max(1, avsAdjustRangeValueItem.getRangeValueDelta()));
                state = Event.getState(avsAdjustRangeValueItem.getNamespace(), "rangeValue", avsAdjustRangeValueItem.getInstance(), Integer.valueOf(avsAdjustRangeValueItem.getRangeValueDelta()));
            }
            AlexaCustomSkillUtil.brightnessControl(alexaOperationMode);
            AlexaAudioEventManger.getInstance().sendEvent(Event.getResponseControllerReplyEvent(state, avsAdjustRangeValueItem.getCorrelationToken(), avsAdjustRangeValueItem.getEndpointId()), null);
        }
    }

    private void handleToggleController(AvsToggleControllerItem avsToggleControllerItem) {
        List<AlexaToggleControllerBean.SkillBean.CapabilityBean> capability;
        AlexaAudioEventManger.getInstance().sendEvent(Event.getResponseControllerReplyEvent(Event.getState(avsToggleControllerItem.getNamespace(), "toggleState", avsToggleControllerItem.getInstance(), avsToggleControllerItem.isOn() ? SavePrivateSafeSettingBean.ON : SavePrivateSafeSettingBean.OFF), avsToggleControllerItem.getCorrelationToken(), avsToggleControllerItem.getEndpointId()), null);
        String avsToggleControllerItem2 = avsToggleControllerItem.getInstance();
        AlexaToggleControllerBean openViewToggleControllerBean = LoadSmartHomeManager.getOpenViewToggleControllerBean();
        if (openViewToggleControllerBean == null || openViewToggleControllerBean.getSkill() == null) {
            return;
        }
        if (avsToggleControllerItem.getInstance().equalsIgnoreCase(AlexaCustomSkillConstant.EventInstanceName.WAKEUP_GESTURE)) {
            AlexaCustomSkillUtil.handlerWakeupGesture(avsToggleControllerItem.isOn());
            return;
        }
        handlerToggleControlerResult(avsToggleControllerItem.isOn());
        for (AlexaToggleControllerBean.SkillBean skillBean : openViewToggleControllerBean.getSkill()) {
            List<AlexaToggleControllerBean.SkillBean.CapabilityBean> capability2 = skillBean.getCapability();
            if (capability2 != null) {
                for (AlexaToggleControllerBean.SkillBean.CapabilityBean capabilityBean : capability2) {
                    if (TextUtils.equals(capabilityBean.getInstance(), avsToggleControllerItem2)) {
                        if (avsToggleControllerItem.isOn()) {
                            AlexaCustomSkillUtil.handlerToggleCmd(true, skillBean.getType(), capabilityBean.getSutype());
                            return;
                        } else {
                            AlexaCustomSkillUtil.handlerStopCmd(capabilityBean.getStopcmd());
                            return;
                        }
                    }
                }
            }
        }
        AlexaToggleControllerBean alexaSportToggleControllerBean = LoadSmartHomeManager.getAlexaSportToggleControllerBean();
        if (alexaSportToggleControllerBean == null || alexaSportToggleControllerBean.getSkill() == null) {
            return;
        }
        Iterator<AlexaToggleControllerBean.SkillBean> it = alexaSportToggleControllerBean.getSkill().iterator();
        while (it.hasNext() && (capability = it.next().getCapability()) != null) {
            Iterator<AlexaToggleControllerBean.SkillBean.CapabilityBean> it2 = capability.iterator();
            while (true) {
                if (it2.hasNext()) {
                    AlexaToggleControllerBean.SkillBean.CapabilityBean next = it2.next();
                    if (TextUtils.equals(next.getInstance(), avsToggleControllerItem2)) {
                        if (avsToggleControllerItem.isOn()) {
                            AlexaCustomSkillUtil.startSport(false, next.getSutype());
                        } else {
                            AlexaCustomSkillUtil.handlerStopCmd(next.getStopcmd());
                        }
                    }
                }
            }
        }
    }

    private void handlerNotification(boolean z) {
        for (HandleDirectiveCallback handleDirectiveCallback : this.mHandleDirectiveCallback) {
            if (handleDirectiveCallback != null) {
                handleDirectiveCallback.handlerNotification(z);
            }
        }
    }

    public void stopRecord(AvsStopCaptureItem avsStopCaptureItem) {
        for (HandleDirectiveCallback handleDirectiveCallback : this.mHandleDirectiveCallback) {
            if (handleDirectiveCallback != null) {
                handleDirectiveCallback.stopRecord(avsStopCaptureItem.getRequestId());
            }
        }
    }

    private void cancelAlarms(AvsDeleteAlertsItem avsDeleteAlertsItem) {
        for (HandleDirectiveCallback handleDirectiveCallback : this.mHandleDirectiveCallback) {
            if (handleDirectiveCallback != null) {
                handleDirectiveCallback.cancelAlarms(avsDeleteAlertsItem.getTokens());
            }
        }
        AlexaLogUtil.printAndSave(" DeleteAlert.tokens=" + avsDeleteAlertsItem.getTokens().toString());
    }

    private void cancelAlarm(AvsDeleteAlertItem avsDeleteAlertItem) {
        for (HandleDirectiveCallback handleDirectiveCallback : this.mHandleDirectiveCallback) {
            if (handleDirectiveCallback != null) {
                handleDirectiveCallback.cancelAlarm(avsDeleteAlertItem.getToken());
            }
        }
        AlexaLogUtil.printAndSave(" DeleteAlert.token=" + avsDeleteAlertItem.getToken());
    }

    private void setTimer(AvsSetAlertItem avsSetAlertItem) {
        try {
            int scheduledTimeMillis = (int) ((avsSetAlertItem.getScheduledTimeMillis() - System.currentTimeMillis()) / 1000);
            AlexaLogUtil.printAndSave(" setTime: time=" + scheduledTimeMillis + "   date=" + avsSetAlertItem.getDate().toString());
            for (HandleDirectiveCallback handleDirectiveCallback : this.mHandleDirectiveCallback) {
                if (handleDirectiveCallback != null) {
                    handleDirectiveCallback.handlerTimer(avsSetAlertItem.getToken(), Math.max(scheduledTimeMillis, 0));
                }
            }
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
    }

    private void setAlarm(AvsSetAlertItem avsSetAlertItem) {
        try {
            AlexaLogUtil.printAndSave(" setAlarm: time=" + ((int) ((avsSetAlertItem.getScheduledTimeMillis() - System.currentTimeMillis()) / 1000)) + "   date=" + avsSetAlertItem.getDate().toString());
            for (HandleDirectiveCallback handleDirectiveCallback : this.mHandleDirectiveCallback) {
                if (handleDirectiveCallback != null) {
                    handleDirectiveCallback.handlerAlarm(avsSetAlertItem.getToken(), avsSetAlertItem.getDate(), avsSetAlertItem.getScheduledTimeMillis());
                }
            }
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
    }

    private void setReminder(AvsSetAlertItem avsSetAlertItem) {
        try {
            AlexaLogUtil.printAndSave(" setReminder: time=" + ((int) ((avsSetAlertItem.getScheduledTimeMillis() - System.currentTimeMillis()) / 1000)) + "   date=" + avsSetAlertItem.getDate().toString() + "  lable=" + avsSetAlertItem.getReminderEventName());
            for (HandleDirectiveCallback handleDirectiveCallback : this.mHandleDirectiveCallback) {
                if (handleDirectiveCallback != null) {
                    handleDirectiveCallback.handlerReminder(avsSetAlertItem.getToken(), avsSetAlertItem.getDate(), avsSetAlertItem.getScheduledTimeMillis(), avsSetAlertItem.getReminderEventName());
                }
            }
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
    }

    public void eauthorizerAmazon() {
        for (HandleDirectiveCallback handleDirectiveCallback : this.mHandleDirectiveCallback) {
            if (handleDirectiveCallback != null) {
                handleDirectiveCallback.eauthorizerAmazon();
            }
        }
    }

    public void handlerRangeControlerResult() {
        for (HandleDirectiveCallback handleDirectiveCallback : this.mHandleDirectiveCallback) {
            if (handleDirectiveCallback != null) {
                handleDirectiveCallback.handlerRangeControlerResult();
            }
        }
    }

    public void handlerToggleControlerResult(boolean z) {
        for (HandleDirectiveCallback handleDirectiveCallback : this.mHandleDirectiveCallback) {
            if (handleDirectiveCallback != null) {
                handleDirectiveCallback.handlerToggleControlerResult(z);
            }
        }
    }
}