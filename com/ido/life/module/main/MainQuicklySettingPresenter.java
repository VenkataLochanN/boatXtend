package com.ido.life.module.main;

import android.text.TextUtils;
import com.ido.alexa.manager.AlexaManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.life.bean.SwitchStatus;
import com.ido.life.data.device.remote.DeviceManager;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.util.AppLogUploadManager;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SPHelper;

/* JADX INFO: loaded from: classes2.dex */
public class MainQuicklySettingPresenter {
    public static final int CODE_ALEXA = 3;
    public static final int CODE_NOTIFICATION = 2;
    public static final int CODE_PREFERENCES_SETTING = 4;
    public static final int CODE_TARGET = 1;
    private static MainQuicklySettingPresenter sPresenter;
    public String mInstructionUrl;

    public interface QuicklySettingCallback {
        void onGetInstructionInfo(String str);
    }

    private MainQuicklySettingPresenter() {
    }

    public static MainQuicklySettingPresenter getInstance() {
        if (sPresenter == null) {
            sPresenter = new MainQuicklySettingPresenter();
        }
        return sPresenter;
    }

    public String getDeviceName() {
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (currentDeviceInfo == null) {
            return null;
        }
        return currentDeviceInfo.mDeviceName;
    }

    public int getStepTarget() {
        UserTargetNew userTargetNewQueryUserLatestTarget = GreenDaoUtil.queryUserLatestTarget(RunTimeUtil.getInstance().getUserId());
        if (userTargetNewQueryUserLatestTarget == null || userTargetNewQueryUserLatestTarget.getStep() == 0) {
            return 10000;
        }
        return userTargetNewQueryUserLatestTarget.getStep();
    }

    public boolean isNotificationTurnOn() {
        return SPHelper.getNotificationStatus().masterSwitched;
    }

    public boolean isSupportCallRemind() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        return supportFunctionInfo != null && supportFunctionInfo.calling;
    }

    public boolean isCallRemindTurnOn() {
        return SPHelper.getSwitchStatus().callReminderSwitched;
    }

    public boolean isSupportAlexa() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        return supportFunctionInfo != null && supportFunctionInfo.ex_table_main7_voice_transmission;
    }

    public boolean isAlexaLoggedIn() {
        return !TextUtils.isEmpty(AlexaManager.getInstance().getToken());
    }

    public void setCallReminderSwitch(boolean z) {
        SwitchStatus switchStatus = SPHelper.getSwitchStatus();
        switchStatus.callReminderSwitched = z;
        SPHelper.setSwitchStatus(switchStatus);
        AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_CALL_REMINDER_SUCCESS, "", null);
    }

    public void setBindNewDeviceState(boolean z) {
        SPHelper.setBindNewDevice(z);
    }

    public void requestInstructionInfo(final QuicklySettingCallback quicklySettingCallback) {
        this.mInstructionUrl = "";
        saveLog("requestInstructionInfo callback = " + quicklySettingCallback);
        if (quicklySettingCallback == null) {
            return;
        }
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        saveLog("requestInstructionInfo deviceInfo = " + currentDeviceInfo);
        if (currentDeviceInfo == null) {
            return;
        }
        DeviceManager.requestInstructionInfo(currentDeviceInfo.mDeviceId, new DeviceManager.OnDeviceCallback<String>() { // from class: com.ido.life.module.main.MainQuicklySettingPresenter.1
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(String str) {
                MainQuicklySettingPresenter.this.saveLog("requestInstructionInfo onSuccess，url = " + str);
                MainQuicklySettingPresenter.this.mInstructionUrl = str;
                quicklySettingCallback.onGetInstructionInfo(str);
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str) {
                MainQuicklySettingPresenter.this.saveLog("requestInstructionInfo onFailed : " + str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getMainQuicklySettingLogPath(), "MainQuicklySettingPresenter", str);
    }
}