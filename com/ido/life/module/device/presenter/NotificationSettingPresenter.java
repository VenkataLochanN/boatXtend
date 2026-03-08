package com.ido.life.module.device.presenter;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.OperateCallBack;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.event.stat.one.d;
import com.ido.ble.protocol.model.AllHealthMonitorSwitch;
import com.ido.ble.protocol.model.DeviceUnreadReminder;
import com.ido.ble.protocol.model.FitnessGuidance;
import com.ido.ble.protocol.model.HeartRateSmartMode;
import com.ido.ble.protocol.model.Menstrual;
import com.ido.ble.protocol.model.MessageNotifyState;
import com.ido.ble.protocol.model.PressureParam;
import com.ido.ble.protocol.model.SPO2Param;
import com.ido.ble.protocol.model.ScheduleReminderSwitch;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.base.BaseDeviceParaCallBack;
import com.ido.life.bean.HealthMonitoringBean;
import com.ido.life.bean.NotificationApp;
import com.ido.life.bean.SwitchStatus;
import com.ido.life.bean.TranIconBean;
import com.ido.life.ble.BaseOperateCallback;
import com.ido.life.constants.Constants;
import com.ido.life.data.Func;
import com.ido.life.data.cache.RemindDataManager;
import com.ido.life.data.cache.RemindDataManagerKt;
import com.ido.life.data.listener.Callback;
import com.ido.life.module.device.view.INotificationSettingView;
import com.ido.life.util.AppLogUploadManager;
import com.ido.life.util.DateUtil;
import com.ido.life.util.ListUtils;
import com.ido.life.util.MsgNotificationHelper;
import com.ido.life.util.NoticeAppUtil;
import com.ido.life.util.SPHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: compiled from: NotificationSettingPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\n*\u0002\u000b\u000e\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0002J\b\u0010\u001a\u001a\u00020\u0014H\u0016J\b\u0010\u001b\u001a\u00020\u0014H\u0002J\u0006\u0010\u001c\u001a\u00020\u0014J\u0006\u0010\u001d\u001a\u00020\u0014J\u000e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00120\u001fH\u0007J\u001e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00120\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0007J\u0006\u0010$\u001a\u00020%J\u0006\u0010&\u001a\u00020'J\u0006\u0010(\u001a\u00020)J\u0006\u0010*\u001a\u00020\u0014J\u0006\u0010+\u001a\u00020#J\b\u0010,\u001a\u00020\u0014H\u0016J\u0010\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u0018H\u0002J\u000e\u00100\u001a\u00020.2\u0006\u00101\u001a\u00020#J\u0006\u00102\u001a\u00020.J\u0006\u00103\u001a\u00020.J\u0006\u00104\u001a\u00020.J\u0006\u00105\u001a\u00020.J\u0006\u00106\u001a\u00020.J\u0012\u00107\u001a\u00020\u00142\b\u00108\u001a\u0004\u0018\u000109H\u0014J\u001c\u0010:\u001a\u00020\u00142\b\u00108\u001a\u0004\u0018\u0001092\b\u0010;\u001a\u0004\u0018\u00010<H\u0014J\u0010\u0010=\u001a\u00020\u00142\u0006\u0010>\u001a\u00020.H\u0002J\u0010\u0010?\u001a\u00020\u00142\b\u00101\u001a\u0004\u0018\u00010#J\u000e\u0010@\u001a\u00020\u00142\u0006\u0010A\u001a\u00020\u0012J\u000e\u0010B\u001a\u00020\u00142\u0006\u0010C\u001a\u00020%J\u000e\u0010D\u001a\u00020\u00142\u0006\u0010E\u001a\u00020\u0005R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR\u0010\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006F"}, d2 = {"Lcom/ido/life/module/device/presenter/NotificationSettingPresenter;", "Lcom/ido/life/base/BaseCmdPresenter;", "Lcom/ido/life/module/device/view/INotificationSettingView;", "()V", "mDeviceUnreadReminder", "Lcom/ido/ble/protocol/model/DeviceUnreadReminder;", "getMDeviceUnreadReminder", "()Lcom/ido/ble/protocol/model/DeviceUnreadReminder;", "setMDeviceUnreadReminder", "(Lcom/ido/ble/protocol/model/DeviceUnreadReminder;)V", "mGetDeviceParaCallBack", "com/ido/life/module/device/presenter/NotificationSettingPresenter$mGetDeviceParaCallBack$1", "Lcom/ido/life/module/device/presenter/NotificationSettingPresenter$mGetDeviceParaCallBack$1;", "mOperateCallback", "com/ido/life/module/device/presenter/NotificationSettingPresenter$mOperateCallback$1", "Lcom/ido/life/module/device/presenter/NotificationSettingPresenter$mOperateCallback$1;", "mQueue", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "Lcom/ido/life/bean/NotificationApp;", "convertResult", "", "allHealthMonitorSwitch", "Lcom/ido/ble/protocol/model/AllHealthMonitorSwitch;", "convertSwitchStatus", "", "statusInBle", "detachView", "generateHealthList", "getDeviceUnreadReminder", "getHealthAppList", "getInstalledApps", "", "functionInfo", "Lcom/ido/ble/protocol/model/SupportFunctionInfo;", "notificationStatus", "Lcom/ido/life/bean/SwitchStatus$NotificationSwitch;", "getLocalBloodOxyData", "Lcom/ido/ble/protocol/model/SPO2Param;", "getLocalPressureParam", "Lcom/ido/ble/protocol/model/PressureParam;", "getMenstrual", "Lcom/ido/ble/protocol/model/Menstrual;", "getNotificationApps", "getNotificationStatus", d.m, "isModify", "", "type", "isStatusChanged", "notificationSwitch", "isSupportHeartRateDetection", "isSupportOverstressReminder", "isSupportSetBloodOxygenNotifyFlag", "isSupportSetMenstrualNotifyFlag", "isSupportSetRemindMode", "onSetCmdFailed", "settingType", "Lcom/ido/ble/callback/SettingCallBack$SettingType;", "onSetCmdSuccess", "o", "", "onStateAddOrModifyResult", "b", "saveNotificationStatus", "sendNotificationStatus2Device", "mNotificationApp", "setBloodOxy2Device", "spo2Param", "setDeviceUnreadReminder", "param", "app_release"}, k = 1, mv = {1, 1, 16})
public final class NotificationSettingPresenter extends BaseCmdPresenter<INotificationSettingView> {
    private DeviceUnreadReminder mDeviceUnreadReminder;
    private ConcurrentLinkedQueue<NotificationApp> mQueue = new ConcurrentLinkedQueue<>();
    private final NotificationSettingPresenter$mOperateCallback$1 mOperateCallback = new BaseOperateCallback() { // from class: com.ido.life.module.device.presenter.NotificationSettingPresenter$mOperateCallback$1
        @Override // com.ido.life.ble.BaseOperateCallback, com.ido.ble.callback.OperateCallBack.ICallBack
        public void onModifyResult(OperateCallBack.OperateType operateType, boolean b2) {
            if (operateType == OperateCallBack.OperateType.MESSAGE_NOTIFY_STATE) {
                MsgNotificationHelper.saveLog("NotificationSettingPresenter： onModifyResult o = " + b2);
                this.this$0.onStateAddOrModifyResult(b2);
            }
        }

        @Override // com.ido.life.ble.BaseOperateCallback, com.ido.ble.callback.OperateCallBack.ICallBack
        public void onAddResult(OperateCallBack.OperateType operateType, boolean b2) {
            if (operateType == OperateCallBack.OperateType.MESSAGE_NOTIFY_STATE) {
                MsgNotificationHelper.saveLog("NotificationSettingPresenter： onAddResult o = " + b2);
                this.this$0.onStateAddOrModifyResult(b2);
            }
        }
    };
    private final NotificationSettingPresenter$mGetDeviceParaCallBack$1 mGetDeviceParaCallBack = new BaseDeviceParaCallBack() { // from class: com.ido.life.module.device.presenter.NotificationSettingPresenter$mGetDeviceParaCallBack$1
        @Override // com.ido.life.base.BaseDeviceParaCallBack, com.ido.ble.callback.GetDeviceParaCallBack.ICallBack
        public void onGetAllHealthMonitorSwitch(AllHealthMonitorSwitch allHealthMonitorSwitch) {
            CommonLogUtil.printAndSave("onGetAllHealthMonitorSwitch：" + allHealthMonitorSwitch);
            this.this$0.convertResult(allHealthMonitorSwitch);
        }
    };

    private final int convertSwitchStatus(int statusInBle) {
        if (statusInBle != 1) {
            return statusInBle != 2 ? 3 : 2;
        }
        return 1;
    }

    public static final /* synthetic */ INotificationSettingView access$getView(NotificationSettingPresenter notificationSettingPresenter) {
        return (INotificationSettingView) notificationSettingPresenter.getView();
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void init() {
        super.init();
        this.mQueue.clear();
        BLEManager.registerGetDeviceParaCallBack(this.mGetDeviceParaCallBack);
        BLEManager.registerOperateCallBack(this.mOperateCallback);
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void detachView() {
        super.detachView();
        BLEManager.unregisterGetDeviceParaCallBack(this.mGetDeviceParaCallBack);
        BLEManager.unregisterOperateCallBack(this.mOperateCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onStateAddOrModifyResult(boolean b2) {
        NotificationApp notificationAppPoll = this.mQueue.poll();
        MsgNotificationHelper.saveLog("NotificationSettingPresenter： onStateAddOrModifyResult：app = " + notificationAppPoll);
        if (notificationAppPoll != null) {
            if (b2) {
                INotificationSettingView iNotificationSettingView = (INotificationSettingView) getView();
                if (iNotificationSettingView != null) {
                    iNotificationSettingView.onSendNotificationStatus2DeviceSuccess(notificationAppPoll);
                    return;
                }
                return;
            }
            INotificationSettingView iNotificationSettingView2 = (INotificationSettingView) getView();
            if (iNotificationSettingView2 != null) {
                iNotificationSettingView2.onSendNotificationStatus2DeviceFailed(notificationAppPoll);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void convertResult(AllHealthMonitorSwitch allHealthMonitorSwitch) {
        SupportFunctionInfo supportFunctionInfo = getSupportFunctionInfo();
        if (allHealthMonitorSwitch != null && supportFunctionInfo != null) {
            ArrayList arrayList = new ArrayList();
            if (supportFunctionInfo.V3_support_set_smart_heart_rate) {
                arrayList.add(new HealthMonitoringBean(1, convertSwitchStatus(allHealthMonitorSwitch.heartmode_notify_flag), false, 4, null));
                HeartRateSmartMode smartHeartRateMode = SPHelper.getSmartHeartRateMode();
                smartHeartRateMode.notify_flag = convertSwitchStatus(allHealthMonitorSwitch.heartmode_notify_flag);
                SPHelper.saveSmartHeartRateMode(smartHeartRateMode);
            }
            if (supportFunctionInfo.V3_pressure_add_notify_flag_and_mode) {
                arrayList.add(new HealthMonitoringBean(2, convertSwitchStatus(allHealthMonitorSwitch.pressure_notify_flag), false, 4, null));
                PressureParam localPressureParam = getLocalPressureParam();
                localPressureParam.notifyFlag = convertSwitchStatus(allHealthMonitorSwitch.pressure_notify_flag);
                BLEManager.setPressureParamPending(localPressureParam);
            }
            if (supportFunctionInfo.V3_spo2_add_notify_flag) {
                arrayList.add(new HealthMonitoringBean(3, convertSwitchStatus(allHealthMonitorSwitch.spo2_notify_flag), false, 4, null));
                SPO2Param localBloodOxyData = getLocalBloodOxyData();
                localBloodOxyData.notifyFlag = convertSwitchStatus(allHealthMonitorSwitch.spo2_notify_flag);
                setBloodOxy2Device(localBloodOxyData);
            }
            if (supportFunctionInfo.V3_menstrual_add_notify_flag) {
                arrayList.add(new HealthMonitoringBean(7, convertSwitchStatus(allHealthMonitorSwitch.menstrual_notify_flag), false, 4, null));
                Menstrual menstrual = getMenstrual();
                menstrual.notify_flag = convertSwitchStatus(allHealthMonitorSwitch.menstrual_notify_flag);
                BLEManager.setMenstrualPending(menstrual);
            }
            arrayList.add(new HealthMonitoringBean(9, convertSwitchStatus(allHealthMonitorSwitch.reminder_notify_flag), false, 4, null));
            ScheduleReminderSwitch scheduleReminderSwitch = SPHelper.getScheduleReminderSwitch();
            scheduleReminderSwitch.notify_flag = convertSwitchStatus(allHealthMonitorSwitch.reminder_notify_flag);
            SPHelper.saveScheduleReminderSwitch(scheduleReminderSwitch);
            INotificationSettingView iNotificationSettingView = (INotificationSettingView) getView();
            if (iNotificationSettingView != null) {
                iNotificationSettingView.onGetHealthAppListSuccess(arrayList);
                return;
            }
            return;
        }
        INotificationSettingView iNotificationSettingView2 = (INotificationSettingView) getView();
        if (iNotificationSettingView2 != null) {
            iNotificationSettingView2.onGetHealthAppListFailed();
        }
    }

    public final boolean isSupportSetRemindMode() {
        return getSupportFunctionInfo().V3_pressure_add_notify_flag_and_mode && isSupportOverstressReminder();
    }

    public final boolean isSupportOverstressReminder() {
        return getSupportFunctionInfo().ex_table_main11_pressure_high_threshold_reminder;
    }

    public final PressureParam getLocalPressureParam() {
        PressureParam pressureParam = LocalDataManager.getPressureParam();
        if (pressureParam == null) {
            pressureParam = new PressureParam();
            pressureParam.onOff = 170;
            if (getSupportFunctionInfo().ex_table_main11_pressure_high_threshold_reminder) {
                pressureParam.remindOnOff = 85;
                pressureParam.startHour = 9;
                pressureParam.startMinute = 0;
                pressureParam.endHour = 18;
                pressureParam.endMinute = 0;
                pressureParam.highThreshold = 80;
                pressureParam.interval = 60;
                pressureParam.setWeekRepeat(new boolean[]{true, true, true, true, true, false, false});
            }
        }
        if (isSupportSetRemindMode() && pressureParam.notifyFlag == 0) {
            pressureParam.notifyFlag = 1;
        }
        return pressureParam;
    }

    public final Menstrual getMenstrual() {
        Menstrual menstrual = LocalDataManager.getMenstrual();
        if (menstrual == null || menstrual.menstrual_length == 0) {
            menstrual = new Menstrual();
            menstrual.on_off = 85;
            menstrual.menstrual_length = 7;
            menstrual.menstrual_cycle = 28;
            int[] currentDate = DateUtil.getCurrentDate();
            menstrual.last_menstrual_year = currentDate[0];
            menstrual.last_menstrual_month = currentDate[1];
            menstrual.last_menstrual_day = currentDate[2];
        }
        if (isSupportSetMenstrualNotifyFlag() && menstrual.notify_flag == 0) {
            menstrual.notify_flag = 1;
        }
        return menstrual;
    }

    public final boolean isSupportSetMenstrualNotifyFlag() {
        return getSupportFunctionInfo().V3_menstrual_add_notify_flag;
    }

    public final boolean isSupportSetBloodOxygenNotifyFlag() {
        return getSupportFunctionInfo().V3_spo2_add_notify_flag;
    }

    public final boolean isSupportHeartRateDetection() {
        return getSupportFunctionInfo().heartRateMonitor || getSupportFunctionInfo().ex_main4_v3_hr_data;
    }

    public final SPO2Param getLocalBloodOxyData() {
        SPO2Param spO2Param = LocalDataManager.getSpO2Param();
        if (spO2Param == null) {
            spO2Param = new SPO2Param();
            spO2Param.notifyFlag = 1;
            spO2Param.onOff = 170;
            spO2Param.lowSpo2OnOff = 85;
        }
        if (isSupportSetBloodOxygenNotifyFlag() && spO2Param.notifyFlag == 0) {
            spO2Param.notifyFlag = 1;
        }
        CommonLogUtil.d("getLocationBloodOxyData spo2Param = " + spO2Param);
        return spO2Param;
    }

    public final void setBloodOxy2Device(SPO2Param spo2Param) {
        Intrinsics.checkParameterIsNotNull(spo2Param, "spo2Param");
        spo2Param.startHour = 0;
        spo2Param.startMinute = 0;
        spo2Param.endHour = 23;
        spo2Param.endMinute = 59;
        CommonLogUtil.d("setBloodOxy2Device spo2Param = " + spo2Param);
        BLEManager.setSPO2ParamPending(spo2Param);
    }

    private final void generateHealthList() {
        addAutoCancelSubscriber(new Func<List<HealthMonitoringBean>>() { // from class: com.ido.life.module.device.presenter.NotificationSettingPresenter.generateHealthList.1
            @Override // com.ido.life.data.Func
            public List<HealthMonitoringBean> call() {
                SupportFunctionInfo supportFunctionInfo = NotificationSettingPresenter.this.getSupportFunctionInfo();
                ArrayList arrayList = new ArrayList();
                if (supportFunctionInfo != null) {
                    int i = 1;
                    if (supportFunctionInfo.support_set_fitness_guidance) {
                        FitnessGuidance fitnessGuidance = SPHelper.getFitnessGuidance();
                        arrayList.add(new HealthMonitoringBean(8, (fitnessGuidance == null || fitnessGuidance.notify_flag == 0) ? 1 : fitnessGuidance.notify_flag, false, 4, null));
                    }
                    if (supportFunctionInfo.V3_pressure_add_notify_flag_and_mode && supportFunctionInfo.ex_table_main11_pressure_high_threshold_reminder) {
                        PressureParam pressureParam = LocalDataManager.getPressureParam();
                        arrayList.add(new HealthMonitoringBean(2, (pressureParam == null || pressureParam.notifyFlag == 0) ? 1 : pressureParam.notifyFlag, false, 4, null));
                    }
                    if (supportFunctionInfo.V3_spo2_add_notify_flag && supportFunctionInfo.V3_support_set_spo2_low_value_remind) {
                        SPO2Param spO2Param = LocalDataManager.getSpO2Param();
                        arrayList.add(new HealthMonitoringBean(3, (spO2Param == null || spO2Param.notifyFlag == 0) ? 1 : spO2Param.notifyFlag, false, 4, null));
                    }
                    if (supportFunctionInfo.V3_menstrual_add_notify_flag) {
                        Menstrual menstrual = LocalDataManager.getMenstrual();
                        if (menstrual != null && menstrual.notify_flag != 0) {
                            i = menstrual.notify_flag;
                        }
                        arrayList.add(new HealthMonitoringBean(7, i, false, 4, null));
                    }
                    if (supportFunctionInfo.V3_schedule_reminder) {
                        arrayList.add(new HealthMonitoringBean(9, SPHelper.getScheduleReminderSwitch().notify_flag, false, 4, null));
                    }
                }
                return arrayList;
            }
        }, new Callback<List<HealthMonitoringBean>>() { // from class: com.ido.life.module.device.presenter.NotificationSettingPresenter.generateHealthList.2
            @Override // com.ido.life.data.listener.Callback
            public void onSuccess(List<HealthMonitoringBean> data) {
                Intrinsics.checkParameterIsNotNull(data, "data");
                INotificationSettingView iNotificationSettingViewAccess$getView = NotificationSettingPresenter.access$getView(NotificationSettingPresenter.this);
                if (iNotificationSettingViewAccess$getView != null) {
                    iNotificationSettingViewAccess$getView.onGetHealthAppListSuccess(data);
                }
            }

            @Override // com.ido.life.data.listener.Callback
            public void onFailed(String errMsg) {
                Intrinsics.checkParameterIsNotNull(errMsg, "errMsg");
                INotificationSettingView iNotificationSettingViewAccess$getView = NotificationSettingPresenter.access$getView(NotificationSettingPresenter.this);
                if (iNotificationSettingViewAccess$getView != null) {
                    iNotificationSettingViewAccess$getView.onGetHealthAppListFailed();
                }
            }
        });
    }

    public final void getHealthAppList() {
        BLEManager.getAllHealthMonitorSwitch();
    }

    public final void getNotificationApps() {
        addAutoCancelSubscriber(new Func<List<NotificationApp>>() { // from class: com.ido.life.module.device.presenter.NotificationSettingPresenter.getNotificationApps.1
            @Override // com.ido.life.data.Func
            public List<NotificationApp> call() {
                if (LocalDataManager.getSupportFunctionInfo() != null) {
                    return NotificationSettingPresenter.this.getInstalledApps();
                }
                return new ArrayList();
            }
        }, new Callback<List<NotificationApp>>() { // from class: com.ido.life.module.device.presenter.NotificationSettingPresenter.getNotificationApps.2
            @Override // com.ido.life.data.listener.Callback
            public void onSuccess(List<NotificationApp> data) {
                Intrinsics.checkParameterIsNotNull(data, "data");
                INotificationSettingView iNotificationSettingViewAccess$getView = NotificationSettingPresenter.access$getView(NotificationSettingPresenter.this);
                if (iNotificationSettingViewAccess$getView != null) {
                    iNotificationSettingViewAccess$getView.onGetNotificationAppListSuccess(data);
                }
            }

            @Override // com.ido.life.data.listener.Callback
            public void onFailed(String errMsg) {
                Intrinsics.checkParameterIsNotNull(errMsg, "errMsg");
                INotificationSettingView iNotificationSettingViewAccess$getView = NotificationSettingPresenter.access$getView(NotificationSettingPresenter.this);
                if (iNotificationSettingViewAccess$getView != null) {
                    iNotificationSettingViewAccess$getView.onGetNotificationAppListFailed();
                }
            }
        });
    }

    public final List<NotificationApp> getInstalledApps() {
        Object next;
        MsgNotificationHelper.saveLog("getInstalledApps");
        List<MessageNotifyState> reminderStatusInDevice = RemindDataManager.INSTANCE.getInstance().getReminderStatusInDevice();
        boolean z = reminderStatusInDevice.size() > 0;
        HashMap<String, TranIconBean> allNoticeAppBeans = NoticeAppUtil.getAllNoticeAppBeans();
        ArrayList arrayList = new ArrayList();
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        if (allNoticeAppBeans != null && allNoticeAppBeans.size() > 0) {
            for (TranIconBean bean : allNoticeAppBeans.values()) {
                Intrinsics.checkExpressionValueIsNotNull(bean, "bean");
                if (Intrinsics.areEqual(Constants.AppPackage.MISS_CALL, bean.getPkgName()) && (supportFunctionInfo == null || !supportFunctionInfo.V3_support_missed_calls)) {
                    MsgNotificationHelper.saveLog("not support miss call！！！");
                } else if (bean.isInstalled()) {
                    String pkgName = bean.getPkgName();
                    Intrinsics.checkExpressionValueIsNotNull(pkgName, "bean.pkgName");
                    String appName = bean.getAppName();
                    Intrinsics.checkExpressionValueIsNotNull(appName, "bean.appName");
                    NotificationApp notificationApp = new NotificationApp(appName, pkgName, 0, 0, bean.getIcon(), null, null, null, 3, 0, bean.getGroup(), 748, null);
                    int type = bean.getType();
                    int i = 3;
                    if (z && type > 0) {
                        Iterator<T> it = reminderStatusInDevice.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                next = null;
                                break;
                            }
                            next = it.next();
                            if (((MessageNotifyState) next).evt_type == type) {
                                break;
                            }
                        }
                        MessageNotifyState messageNotifyState = (MessageNotifyState) next;
                        if (messageNotifyState != null) {
                            i = messageNotifyState.notify_state;
                        }
                    }
                    notificationApp.setStatus(i);
                    if (!arrayList.contains(notificationApp)) {
                        arrayList.add(notificationApp);
                    }
                } else {
                    MsgNotificationHelper.saveLog("the app【" + bean.getPkgName() + "】is not installed，it's necessary to show in list!!!");
                }
            }
        }
        MsgNotificationHelper.saveLog("all installed APP count : " + arrayList.size());
        return arrayList;
    }

    public final List<NotificationApp> getInstalledApps(SupportFunctionInfo functionInfo, SwitchStatus.NotificationSwitch notificationStatus) {
        String str;
        Intrinsics.checkParameterIsNotNull(functionInfo, "functionInfo");
        Intrinsics.checkParameterIsNotNull(notificationStatus, "notificationStatus");
        ListUtils.INSTANCE.isNotEmpty(RemindDataManager.INSTANCE.getInstance().getReminderStatusInDevice());
        List<String> installedPackages = RemindDataManager.INSTANCE.getInstance().getInstalledPackages();
        Context appContext = IdoApp.getAppContext();
        Intrinsics.checkExpressionValueIsNotNull(appContext, "IdoApp.getAppContext()");
        PackageManager packageManager = appContext.getPackageManager();
        ArrayList arrayList = new ArrayList();
        for (String str2 : installedPackages) {
            try {
                if (!TextUtils.isEmpty(str2)) {
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str2, 128);
                    Intrinsics.checkExpressionValueIsNotNull(applicationInfo, "pm.getApplicationInfo(pk…ageManager.GET_META_DATA)");
                    String string = applicationInfo.loadLabel(packageManager).toString();
                    Drawable drawableLoadIcon = applicationInfo.loadIcon(packageManager);
                    str = str2;
                    try {
                        int iIndexOf = RemindDataManager.INSTANCE.getWHITE_APP_LIST().indexOf(new NotificationApp(null, str2, 0, 0, null, null, null, null, 0, 0, 0, 2045, null));
                        if (iIndexOf >= 0) {
                            NotificationApp notificationApp = RemindDataManager.INSTANCE.getWHITE_APP_LIST().get(iIndexOf);
                            String fieldInFunctionList = notificationApp.getFieldInFunctionList();
                            String fieldIntInNotification = notificationApp.getFieldIntInNotification();
                            boolean fieldValueInFunctionInfo = RemindDataManager.INSTANCE.getFieldValueInFunctionInfo(fieldInFunctionList, functionInfo);
                            int fieldValueInNotificationStatus = RemindDataManager.INSTANCE.getFieldValueInNotificationStatus(fieldIntInNotification, notificationStatus);
                            if (!fieldValueInFunctionInfo) {
                                continue;
                            } else if (string != null) {
                                notificationApp.setName(string);
                                notificationApp.setIcon(drawableLoadIcon);
                                notificationApp.setStatus(fieldValueInNotificationStatus);
                                if (!RemindDataManagerKt.has(arrayList, notificationApp)) {
                                    arrayList.add(notificationApp);
                                }
                            } else {
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                            }
                        }
                    } catch (Exception e2) {
                        e = e2;
                        MsgNotificationHelper.saveLog("Get the installed app " + str + " error：" + e.getMessage());
                    }
                }
            } catch (Exception e3) {
                e = e3;
                str = str2;
            }
        }
        MsgNotificationHelper.saveLog("all installed APP count : " + arrayList.size());
        return arrayList;
    }

    public final SwitchStatus.NotificationSwitch getNotificationStatus() {
        SwitchStatus.NotificationSwitch notificationStatus = SPHelper.getNotificationStatus();
        Intrinsics.checkExpressionValueIsNotNull(notificationStatus, "SPHelper.getNotificationStatus()");
        return notificationStatus;
    }

    public final boolean isStatusChanged(SwitchStatus.NotificationSwitch notificationSwitch) {
        Intrinsics.checkParameterIsNotNull(notificationSwitch, "notificationSwitch");
        return !Intrinsics.areEqual(notificationSwitch, getNotificationStatus());
    }

    public final void saveNotificationStatus(SwitchStatus.NotificationSwitch notificationSwitch) {
        AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_MESSAGE_NOTIFICATION_SUCCESS, "", null);
        SPHelper.setNotificationStatus(notificationSwitch);
    }

    /* JADX INFO: renamed from: com.ido.life.module.device.presenter.NotificationSettingPresenter$sendNotificationStatus2Device$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: NotificationSettingPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.device.presenter.NotificationSettingPresenter$sendNotificationStatus2Device$1", f = "NotificationSettingPresenter.kt", i = {0, 0, 0, 1, 1}, l = {584, 590}, m = "invokeSuspend", n = {"$this$launch", "state", "e", "$this$launch", "state"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1"})
    static final class C02651 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ NotificationApp $mNotificationApp;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02651(NotificationApp notificationApp, Continuation continuation) {
            super(2, continuation);
            this.$mNotificationApp = notificationApp;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C02651 c02651 = NotificationSettingPresenter.this.new C02651(this.$mNotificationApp, completion);
            c02651.p$ = (CoroutineScope) obj;
            return c02651;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02651) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i == 1) {
                } else if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            } else {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                MessageNotifyState notificationState = RemindDataManager.INSTANCE.getNotificationState(this.$mNotificationApp.getPkg(), this.$mNotificationApp.getStatus());
                MsgNotificationHelper.saveLog("NotificationSettingPresenter： sendNotificationStatus2Device state = " + notificationState);
                if (notificationState != null) {
                    MsgNotificationHelper.saveLog("NotificationSettingPresenter： 开始同步：app = " + this.$mNotificationApp);
                    try {
                        if (NotificationSettingPresenter.this.isModify(notificationState.evt_type)) {
                            MsgNotificationHelper.saveLog("NotificationSettingPresenter: modifyMessageNotifyState");
                            BLEManager.modifyMessageNotifyState(CollectionsKt.mutableListOf(notificationState), 0, 0);
                        } else {
                            MsgNotificationHelper.saveLog("NotificationSettingPresenter: addMessageNotifyState");
                            BLEManager.addMessageNotifyState(CollectionsKt.mutableListOf(notificationState), 0, 0);
                        }
                        NotificationSettingPresenter.this.mQueue.add(this.$mNotificationApp);
                    } catch (Exception e2) {
                        MsgNotificationHelper.saveLog("NotificationSettingPresenter：Failed to sendNotificationStatus2Device：" + e2.getMessage());
                        MainCoroutineDispatcher main = Dispatchers.getMain();
                        C01101 c01101 = new C01101(null);
                        this.L$0 = coroutineScope;
                        this.L$1 = notificationState;
                        this.L$2 = e2;
                        this.label = 1;
                        if (BuildersKt.withContext(main, c01101, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                } else {
                    MsgNotificationHelper.saveLog("NotificationSettingPresenter：The state of " + this.$mNotificationApp.getPkg() + " is null!!!");
                    MainCoroutineDispatcher main2 = Dispatchers.getMain();
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2(null);
                    this.L$0 = coroutineScope;
                    this.L$1 = notificationState;
                    this.label = 2;
                    if (BuildersKt.withContext(main2, anonymousClass2, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.ido.life.module.device.presenter.NotificationSettingPresenter$sendNotificationStatus2Device$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: NotificationSettingPresenter.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.module.device.presenter.NotificationSettingPresenter$sendNotificationStatus2Device$1$1", f = "NotificationSettingPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C01101 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            C01101(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C01101 c01101 = C02651.this.new C01101(completion);
                c01101.p$ = (CoroutineScope) obj;
                return c01101;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01101) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                INotificationSettingView iNotificationSettingViewAccess$getView = NotificationSettingPresenter.access$getView(NotificationSettingPresenter.this);
                if (iNotificationSettingViewAccess$getView == null) {
                    return null;
                }
                iNotificationSettingViewAccess$getView.onSendNotificationStatus2DeviceFailed(C02651.this.$mNotificationApp);
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: renamed from: com.ido.life.module.device.presenter.NotificationSettingPresenter$sendNotificationStatus2Device$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: NotificationSettingPresenter.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.module.device.presenter.NotificationSettingPresenter$sendNotificationStatus2Device$1$2", f = "NotificationSettingPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            AnonymousClass2(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass2 anonymousClass2 = C02651.this.new AnonymousClass2(completion);
                anonymousClass2.p$ = (CoroutineScope) obj;
                return anonymousClass2;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                INotificationSettingView iNotificationSettingViewAccess$getView = NotificationSettingPresenter.access$getView(NotificationSettingPresenter.this);
                if (iNotificationSettingViewAccess$getView == null) {
                    return null;
                }
                iNotificationSettingViewAccess$getView.onSendNotificationStatus2DeviceFailed(C02651.this.$mNotificationApp);
                return Unit.INSTANCE;
            }
        }
    }

    public final void sendNotificationStatus2Device(NotificationApp mNotificationApp) {
        Intrinsics.checkParameterIsNotNull(mNotificationApp, "mNotificationApp");
        MsgNotificationHelper.saveLog("NotificationSettingPresenter： sendNotificationStatus2Device app = " + mNotificationApp);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C02651(mNotificationApp, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isModify(int type) {
        return RemindDataManager.INSTANCE.getInstance().getReminderTypesInDevice().contains(Integer.valueOf(type));
    }

    public final DeviceUnreadReminder getMDeviceUnreadReminder() {
        return this.mDeviceUnreadReminder;
    }

    public final void setMDeviceUnreadReminder(DeviceUnreadReminder deviceUnreadReminder) {
        this.mDeviceUnreadReminder = deviceUnreadReminder;
    }

    public final void getDeviceUnreadReminder() {
        DeviceUnreadReminder deviceUnreadReminder = SPHelper.getDeviceUnreadReminder();
        if (deviceUnreadReminder == null) {
            deviceUnreadReminder = new DeviceUnreadReminder();
            deviceUnreadReminder.on_off = 170;
        }
        INotificationSettingView iNotificationSettingView = (INotificationSettingView) getView();
        if (iNotificationSettingView != null) {
            iNotificationSettingView.onGetDeviceUnreadReminderSuccess(deviceUnreadReminder);
        }
    }

    public final void setDeviceUnreadReminder(DeviceUnreadReminder param) {
        Intrinsics.checkParameterIsNotNull(param, "param");
        MsgNotificationHelper.saveLog("NotificationSettingPresenter：setDeviceUnreadReminder on_off = " + param.on_off);
        this.mDeviceUnreadReminder = param;
        BLEManager.setDeviceUnreadReminder(param);
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdFailed(SettingCallBack.SettingType settingType) {
        super.onSetCmdFailed(settingType);
        if (settingType == SettingCallBack.SettingType.DEVICE_UNREAD_REMINDER) {
            MsgNotificationHelper.saveLog("NotificationSettingPresenter：setDeviceUnreadReminder onSetCmdFailed ");
            this.mDeviceUnreadReminder = (DeviceUnreadReminder) null;
            INotificationSettingView iNotificationSettingView = (INotificationSettingView) getView();
            if (iNotificationSettingView != null) {
                iNotificationSettingView.onSetDeviceUnreadReminderFailed();
            }
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdSuccess(SettingCallBack.SettingType settingType, Object o) {
        super.onSetCmdSuccess(settingType, o);
        if (settingType == SettingCallBack.SettingType.DEVICE_UNREAD_REMINDER) {
            MsgNotificationHelper.saveLog("NotificationSettingPresenter：setDeviceUnreadReminder onSetCmdSuccess ");
            SPHelper.setDeviceUnreadReminder(this.mDeviceUnreadReminder);
            INotificationSettingView iNotificationSettingView = (INotificationSettingView) getView();
            if (iNotificationSettingView != null) {
                iNotificationSettingView.onSetDeviceUnreadReminderSuccess();
            }
        }
    }
}