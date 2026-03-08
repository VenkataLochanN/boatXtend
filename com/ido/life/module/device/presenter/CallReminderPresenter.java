package com.ido.life.module.device.presenter;

import com.ido.ble.BLEManager;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.NoticeReminderSwitchStatus;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.bean.SwitchStatus;
import com.ido.life.ble.BaseDeviceInfoCallback;
import com.ido.life.module.device.view.ICallReminderView;
import com.ido.life.util.AppLogUploadManager;
import com.ido.life.util.SPHelper;

/* JADX INFO: loaded from: classes2.dex */
public class CallReminderPresenter extends BaseCmdPresenter<ICallReminderView> {
    private static boolean sendTwo = false;
    private static boolean setIsSwitchOn = false;
    private final BaseDeviceInfoCallback mDeviceInfoCallback = new BaseDeviceInfoCallback() { // from class: com.ido.life.module.device.presenter.CallReminderPresenter.1
        @Override // com.ido.life.ble.BaseDeviceInfoCallback, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetNoticeReminderSwitchStatus(NoticeReminderSwitchStatus noticeReminderSwitchStatus) {
            super.onGetNoticeReminderSwitchStatus(noticeReminderSwitchStatus);
            CallReminderPresenter.this.logP("onGetNoticeReminderSwitchStatus：" + noticeReminderSwitchStatus);
            if (noticeReminderSwitchStatus == null) {
                if (CallReminderPresenter.this.isAttachView()) {
                    ((ICallReminderView) CallReminderPresenter.this.getView()).onCallReminderStatusSetFailed();
                }
            } else if (noticeReminderSwitchStatus.notify_switch == 85) {
                CallReminderPresenter.this.logP("onGetNoticeReminderSwitchStatus：已配对，直接下发来电提醒状态");
                CallReminderPresenter.this.setCallSwitch();
            } else {
                CallReminderPresenter.this.setDevicePairCmd();
            }
        }
    };

    public boolean getCallReminderSwitch() {
        return SPHelper.getSwitchStatus().callReminderSwitched;
    }

    public boolean isSupportSetCallReminderStatus2Device() {
        return getSupportFunctionInfo().ancs;
    }

    public void getNoticeReminderSwitchStatus() {
        logP("getNoticeReminderSwitchStatus");
        BLEManager.registerGetDeviceInfoCallBack(this.mDeviceInfoCallback);
        BLEManager.getNoticeReminderSwitchStatus();
    }

    public void setCallReminderSwitch(boolean z) {
        setIsSwitchOn = z;
        if (isSupportSetCallReminderStatus2Device()) {
            if (!BLEManager.isConnected()) {
                logP("设备未连接");
                if (isAttachView()) {
                    ((ICallReminderView) getView()).onCallReminderStatusSetFailed();
                    return;
                }
                return;
            }
            if (isAttachView()) {
                ((ICallReminderView) getView()).showLoading();
            }
            setCallSwitch();
            return;
        }
        logP("不支持设置来电提醒状态");
        saveCallReminderStatus();
    }

    private void saveCallReminderStatus() {
        logP("saveCallReminderStatus，setIsSwitchOn = " + setIsSwitchOn);
        SwitchStatus switchStatus = SPHelper.getSwitchStatus();
        switchStatus.callReminderSwitched = setIsSwitchOn;
        SPHelper.setSwitchStatus(switchStatus);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDevicePairCmd() {
        sendTwo = true;
        NoticeReminderSwitchStatus noticeReminderSwitchStatus = new NoticeReminderSwitchStatus();
        noticeReminderSwitchStatus.notify_switch = 85;
        BLEManager.setNoticeReminderSwitchStatus(noticeReminderSwitchStatus);
        logP("setDevicePairCmd：" + noticeReminderSwitchStatus.toString());
        AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_CALL_REMINDER_SUCCESS, "", null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCallSwitch() {
        sendTwo = false;
        NoticeReminderSwitchStatus noticeReminderSwitchStatus = new NoticeReminderSwitchStatus();
        noticeReminderSwitchStatus.notify_switch = 136;
        if (setIsSwitchOn) {
            noticeReminderSwitchStatus.call_switch = 85;
        } else {
            noticeReminderSwitchStatus.call_switch = 170;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLogPath(), "setCallSwitch:" + noticeReminderSwitchStatus.toString());
        BLEManager.setNoticeReminderSwitchStatus(noticeReminderSwitchStatus);
    }

    public boolean getCallReminderDelay3Switch() {
        return SPHelper.getSwitchStatus().callDelayReminderSwitched;
    }

    public void setCallReminderDelay3Switch(boolean z) {
        SwitchStatus switchStatus = SPHelper.getSwitchStatus();
        switchStatus.callDelayReminderSwitched = z;
        SPHelper.setSwitchStatus(switchStatus);
    }

    public boolean isSupportCallAndRemind() {
        SupportFunctionInfo supportFunctionInfo = getSupportFunctionInfo();
        return supportFunctionInfo != null && supportFunctionInfo.V3_support_sync_contact;
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdFailed(SettingCallBack.SettingType settingType) {
        super.onSetCmdFailed(settingType);
        if (settingType == SettingCallBack.SettingType.NOTICE_REMINDER_SWITCH_STATUS) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLogPath(), "setCallSwitch: fail");
            sendTwo = false;
            if (isAttachView()) {
                ((ICallReminderView) getView()).onCallReminderStatusSetFailed();
            }
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdSuccess(SettingCallBack.SettingType settingType, Object obj) {
        super.onSetCmdSuccess(settingType, obj);
        if (settingType == SettingCallBack.SettingType.NOTICE_REMINDER_SWITCH_STATUS) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLogPath(), "setCallSwitch: success");
            if (sendTwo) {
                setCallSwitch();
            } else {
                saveCallReminderStatus();
                if (isAttachView()) {
                    ((ICallReminderView) getView()).onCallReminderStatusSetSuccess();
                }
            }
            sendTwo = false;
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void init() {
        super.init();
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void detachView() {
        super.detachView();
    }
}