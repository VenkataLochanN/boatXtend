package com.ido.life.module.device.presenter;

import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.ActivitySwitch;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.module.device.view.IMotionRecognitionView;
import com.ido.life.util.AppLogUploadManager;

/* JADX INFO: loaded from: classes2.dex */
public class MotionRecognitionPresenter extends BaseCmdPresenter<IMotionRecognitionView> {
    public ActivitySwitch getMotionRecognitionState() {
        ActivitySwitch activitySwitch = LocalDataManager.getActivitySwitch();
        return activitySwitch == null ? new ActivitySwitch() : activitySwitch;
    }

    public void sendMotionRecognition2Device(ActivitySwitch activitySwitch) {
        BLEManager.setActivitySwitch(activitySwitch);
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdFailed(SettingCallBack.SettingType settingType) {
        super.onSetCmdFailed(settingType);
        if (settingType == SettingCallBack.SettingType.ACTIVITY_SWITCH) {
            AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_INTELLIGENT_MOTION_FAILURE, "", null);
            if (isAttachView()) {
                ((IMotionRecognitionView) getView()).onSetMotionRecognitionFailed();
            }
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdSuccess(SettingCallBack.SettingType settingType, Object obj) {
        super.onSetCmdSuccess(settingType, obj);
        if (settingType == SettingCallBack.SettingType.ACTIVITY_SWITCH) {
            AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_INTELLIGENT_MOTION_SUCCESS, "", null);
            if (isAttachView()) {
                ((IMotionRecognitionView) getView()).onSetMotionRecognitionSuccess();
            }
        }
    }
}