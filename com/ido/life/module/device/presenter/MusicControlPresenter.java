package com.ido.life.module.device.presenter;

import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.SettingCallBack;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.base.BaseMessage;
import com.ido.life.bean.SwitchStatus;
import com.ido.life.constants.Constants;
import com.ido.life.module.device.view.IMusicControlView;
import com.ido.life.util.SPHelper;
import com.ido.life.util.eventbus.EventBusHelper;

/* JADX INFO: loaded from: classes2.dex */
public class MusicControlPresenter extends BaseCmdPresenter<IMusicControlView> {
    public boolean isMusicControlSwitchOn() {
        return LocalDataManager.getMusicSwitch();
    }

    public void setMusicSwitch(boolean z) {
        BLEManager.setMusicSwitch(z);
    }

    public boolean getMusicNameSwitch() {
        return SPHelper.getSwitchStatus().musicNameSwitched;
    }

    public void setMusicNameSwitch(boolean z) {
        SwitchStatus switchStatus = SPHelper.getSwitchStatus();
        switchStatus.musicNameSwitched = z;
        SPHelper.setSwitchStatus(switchStatus);
        EventBusHelper.post(new BaseMessage(Constants.EventConstants.EVENT_MUSIC_CONTROL_CHANGED, true));
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdSuccess(SettingCallBack.SettingType settingType, Object obj) {
        super.onSetCmdSuccess(settingType, obj);
        if (settingType == SettingCallBack.SettingType.MUSIC_SWITCH) {
            CommonLogUtil.printAndSave("音乐控制开关设置成功！");
            if (isAttachView()) {
                ((IMusicControlView) getView()).onSetCmdSuccess(settingType);
            }
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdFailed(SettingCallBack.SettingType settingType) {
        super.onSetCmdFailed(settingType);
        if (settingType == SettingCallBack.SettingType.MUSIC_SWITCH) {
            CommonLogUtil.printAndSave("音乐控制开关设置失败！");
            if (isAttachView()) {
                ((IMusicControlView) getView()).onSetCmdFailed(settingType);
            }
        }
    }
}