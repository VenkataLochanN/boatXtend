package com.ido.life.module.device.presenter;

import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.HeartRateMeasureMode;
import com.ido.ble.protocol.model.HeartRateMeasureModeV3;
import com.ido.ble.protocol.model.HeartRateSmartMode;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.bean.HeartRateMode;
import com.ido.life.bean.HeartRateModeBean;
import com.ido.life.constants.Constants;
import com.ido.life.module.device.view.IHeartRateView;
import com.ido.life.util.SPHelper;
import com.ido.life.util.TimeUtil;
import com.ido.life.util.eventbus.EventBusHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class HeartRateMonitoringPresenter extends BaseMonitoringPresenter<IHeartRateView> {
    private boolean isGetModeFromDevice = true;
    private HeartRateSmartMode mHeartRateSmartMode;
    private int mHighRemindHeartRate;
    private int mLowRemindHeartRate;

    public boolean isSupportHeartRateV3() {
        return getSupportFunctionInfo().ex_main4_v3_hr_data;
    }

    public boolean isSupportHeartRateDetectionTime() {
        return getSupportFunctionInfo().V3_support_show_detection_time;
    }

    public boolean isSupportHeartRateModeCustomTime() {
        return getSupportFunctionInfo().v3_heart_set_rate_mode_custom;
    }

    public int getLocalHeartRateMode() {
        return SPHelper.getHeartRateMode();
    }

    public void saveHeartRateMode(int i) {
        SPHelper.saveHeartRateMode(i);
    }

    public HeartRateModeBean getSupportHeartRateModeList(HeartRateMeasureModeV3 heartRateMeasureModeV3) {
        ArrayList arrayList = new ArrayList();
        HeartRateModeBean heartRateModeBean = new HeartRateModeBean();
        if (heartRateMeasureModeV3 == null) {
            heartRateModeBean.setRateModes(arrayList);
            return heartRateModeBean;
        }
        List<Integer> secModeArray = heartRateMeasureModeV3.getSecModeArray();
        if (secModeArray != null && !secModeArray.isEmpty()) {
            for (Integer num : secModeArray) {
                if (num != null) {
                    arrayList.add(new HeartRateMode(num.intValue(), 1));
                }
            }
        }
        List<Integer> minModeArray = heartRateMeasureModeV3.getMinModeArray();
        if (minModeArray != null && !minModeArray.isEmpty()) {
            for (Integer num2 : minModeArray) {
                if (num2 != null) {
                    arrayList.add(new HeartRateMode(num2.intValue(), 2));
                }
            }
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDeviceLogPath(), "modeV3.toString===" + heartRateMeasureModeV3.toString());
        heartRateModeBean.setRateModes(arrayList);
        heartRateModeBean.setLowHeartMode(heartRateMeasureModeV3.lowHeartMode == 170 ? 170 : 85);
        heartRateModeBean.setLowHeartValue(heartRateMeasureModeV3.lowHeartValue);
        heartRateModeBean.setHighHeartMode(heartRateMeasureModeV3.highHeartMode == 170 ? 170 : 85);
        heartRateModeBean.setHighHeartValue(heartRateMeasureModeV3.highHeartValue);
        heartRateModeBean.setStartHour(heartRateMeasureModeV3.startHour);
        heartRateModeBean.setStartMinute(heartRateMeasureModeV3.startMinute);
        heartRateModeBean.setEndHour(heartRateMeasureModeV3.endHour);
        heartRateModeBean.setEndMinute(heartRateMeasureModeV3.endMinute);
        return heartRateModeBean;
    }

    public void sendMonitoringMode2Device(int i, int i2, HeartRateModeBean heartRateModeBean) {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        if (supportFunctionInfo == null) {
            supportFunctionInfo = new SupportFunctionInfo();
        }
        this.isGetModeFromDevice = false;
        if (supportFunctionInfo.ex_main4_v3_hr_data) {
            HeartRateMeasureModeV3 heartRateModeV3 = LocalDataManager.getHeartRateModeV3();
            if (heartRateModeV3 == null) {
                heartRateModeV3 = new HeartRateMeasureModeV3();
                heartRateModeV3.endHour = 23;
                heartRateModeV3.endMinute = 59;
            }
            if (isSupportHeartRateDetectionTime() && heartRateModeBean != null) {
                heartRateModeV3.startHour = heartRateModeBean.getStartHour();
                heartRateModeV3.startMinute = heartRateModeBean.getStartMinute();
                heartRateModeV3.endHour = heartRateModeBean.getEndHour();
                heartRateModeV3.endMinute = heartRateModeBean.getEndMinute();
            }
            if (isSupportHeartRateModeCustomTime() && heartRateModeBean != null) {
                heartRateModeV3.lowHeartMode = heartRateModeBean.getLowHeartMode();
                heartRateModeV3.lowHeartValue = heartRateModeBean.getLowHeartValue();
                heartRateModeV3.highHeartMode = heartRateModeBean.getHighHeartMode();
                heartRateModeV3.highHeartValue = heartRateModeBean.getHighHeartValue();
            }
            heartRateModeV3.updateTime = (int) TimeUtil.getUTCTime();
            heartRateModeV3.mode = i;
            heartRateModeV3.measurementInterval = i2;
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDeviceLogPath(), "发送模式到手环===" + heartRateModeV3.toString());
            BLEManager.setHeartRateMeasureModeV3(heartRateModeV3);
            return;
        }
        if (supportFunctionInfo.heartRateMonitor) {
            HeartRateMeasureMode heartRateMode = LocalDataManager.getHeartRateMode();
            if (heartRateMode == null) {
                heartRateMode = new HeartRateMeasureMode();
                heartRateMode.endHour = 23;
                heartRateMode.endMinute = 59;
            }
            if (i != 153) {
                i = 170;
            }
            heartRateMode.mode = i;
            BLEManager.setHeartRateMeasureMode(heartRateMode);
        }
    }

    @Override // com.ido.life.module.device.presenter.BaseMonitoringPresenter
    protected void getHeartRateModeFailed() {
        super.getHeartRateModeFailed();
        if (isAttachView()) {
            ((IHeartRateView) getView()).onGetHeartRateModeFailed();
        }
    }

    @Override // com.ido.life.module.device.presenter.BaseMonitoringPresenter
    protected void onGetHeartRateMode(int i) {
        if (isAttachView()) {
            ((IHeartRateView) getView()).onGetHeartRateModeSuccess(i);
        }
    }

    @Override // com.ido.life.module.device.presenter.BaseMonitoringPresenter
    protected void onGetHeartRateModeV3(int i, int i2) {
        super.onGetHeartRateModeV3(i, i2);
        if (isAttachView()) {
            ((IHeartRateView) getView()).onGetHeartRateModeV3Success(i, i2);
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdFailed(SettingCallBack.SettingType settingType) {
        super.onSetCmdFailed(settingType);
        if (isAttachView()) {
            int i = AnonymousClass1.$SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[settingType.ordinal()];
            if (i == 1 || i == 2) {
                ((IHeartRateView) getView()).onSetHeartRateModeFailed();
            } else {
                if (i != 3) {
                    return;
                }
                CommonLogUtil.d("setHeartRateSmart onSetCmdFailed");
                ((IHeartRateView) getView()).onSetSmartHeartRateModeFailed();
            }
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.device.presenter.HeartRateMonitoringPresenter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType = new int[SettingCallBack.SettingType.values().length];

        static {
            try {
                $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[SettingCallBack.SettingType.HEART_RATE_MEASURE_MODE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[SettingCallBack.SettingType.HEART_RATE_MEASURE_MODE_V3.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[SettingCallBack.SettingType.HEART_RATE_SMART.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // com.ido.life.module.device.presenter.BaseMonitoringPresenter, com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdSuccess(SettingCallBack.SettingType settingType, Object obj) {
        if (isAttachView()) {
            super.onSetCmdSuccess(settingType, obj);
            int i = AnonymousClass1.$SwitchMap$com$ido$ble$callback$SettingCallBack$SettingType[settingType.ordinal()];
            if (i == 1) {
                ((IHeartRateView) getView()).onSetHeartRateModeSuccess();
                return;
            }
            if (i == 2) {
                if (this.isGetModeFromDevice) {
                    ((IHeartRateView) getView()).onGetHeartRateMeasureModeV3((HeartRateMeasureModeV3) obj);
                    return;
                } else {
                    ((IHeartRateView) getView()).onSetHeartRateModeSuccess();
                    return;
                }
            }
            if (i != 3) {
                return;
            }
            CommonLogUtil.printAndSave("setHeartRateSmart onSetCmdSuccess");
            SPHelper.saveSmartHeartRateMode(this.mHeartRateSmartMode);
            EventBusHelper.post(Constants.EventConstants.EVENT_SMART_HEART_RATE_MODE_CHANGED);
            ((IHeartRateView) getView()).onSetSmartHeartRateModeSuccess();
        }
    }

    public void getHeartRateRemindValues() {
        ((IHeartRateView) getView()).onGetHeartRateRemindValues(generateLowHeartRateValues(50, 5, 3), generateHeartRateValues(100, 10, 6));
    }

    private List<String> generateLowHeartRateValues(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(LanguageUtil.getLanguageText(R.string.public_close));
        for (int i4 = 0; i4 < i3; i4++) {
            arrayList.add(i + "");
            i -= i2;
        }
        return arrayList;
    }

    private List<String> generateHeartRateValues(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(LanguageUtil.getLanguageText(R.string.public_close));
        for (int i4 = 0; i4 < i3; i4++) {
            arrayList.add(i + "");
            i += i2;
        }
        return arrayList;
    }

    @Override // com.ido.life.module.device.presenter.BaseMonitoringPresenter
    public boolean isSupportSmartHeartRate() {
        return getSupportFunctionInfo().V3_support_set_smart_heart_rate;
    }

    public void setHeartRateSmart(HeartRateSmartMode heartRateSmartMode) {
        this.mHeartRateSmartMode = heartRateSmartMode;
        CommonLogUtil.d("setHeartRateSmart ：" + this.mHeartRateSmartMode);
        BLEManager.setHeartRateSmart(heartRateSmartMode);
    }

    public boolean isDataChanged(HeartRateSmartMode heartRateSmartMode) {
        return !SPHelper.getSmartHeartRateMode().toString().equals(heartRateSmartMode.toString());
    }

    public void getHeartRateSmartMode() {
        HeartRateSmartMode smartHeartRateMode = SPHelper.getSmartHeartRateMode();
        ((IHeartRateView) getView()).onGetSmartHeartRateMode(smartHeartRateMode);
        setHeartRateHighReminder(smartHeartRateMode.high_heart_mode == 170 ? String.valueOf(smartHeartRateMode.high_heart_value) : "");
        setHeartRateLowReminder(smartHeartRateMode.low_heart_mode == 170 ? String.valueOf(smartHeartRateMode.low_heart_value) : "");
    }

    public void setHeartRateHighReminder(String str) {
        if (!TextUtils.isEmpty(str) && TextUtils.isDigitsOnly(str)) {
            this.mHighRemindHeartRate = Integer.parseInt(str);
        } else {
            this.mHighRemindHeartRate = 0;
        }
        ((IHeartRateView) getView()).onGetHeartRateHighReminder(this.mHighRemindHeartRate);
    }

    public void setHeartRateLowReminder(String str) {
        if (!TextUtils.isEmpty(str) && TextUtils.isDigitsOnly(str)) {
            this.mLowRemindHeartRate = Integer.parseInt(str);
        } else {
            this.mLowRemindHeartRate = 0;
        }
        ((IHeartRateView) getView()).onGetHeartRateLowReminder(this.mLowRemindHeartRate);
    }
}