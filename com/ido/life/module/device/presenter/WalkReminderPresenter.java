package com.ido.life.module.device.presenter;

import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.WalkReminder;
import com.ido.life.module.device.view.IWalkReminderView;
import com.ido.life.util.SPHelper;

/* JADX INFO: loaded from: classes2.dex */
public class WalkReminderPresenter extends BaseMonitoringPresenter<IWalkReminderView> {
    private String[] mItemArr;

    public WalkReminder getWalkReminderState() {
        WalkReminder walkReminder = LocalDataManager.getWalkReminder();
        if (walkReminder != null) {
            return walkReminder;
        }
        WalkReminder walkReminder2 = new WalkReminder();
        walkReminder2.setOnOff(0);
        walkReminder2.setWeeks(new boolean[]{true, true, true, true, true, true, true});
        walkReminder2.setStartHour(9);
        walkReminder2.setStartMinute(0);
        walkReminder2.setEndHour(21);
        walkReminder2.setEndMinute(0);
        walkReminder2.setGoalStep(100);
        return walkReminder2;
    }

    public void sendWalkReminder2Device(WalkReminder walkReminder) {
        BLEManager.setWalkReminder(walkReminder);
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdFailed(SettingCallBack.SettingType settingType) {
        super.onSetCmdFailed(settingType);
        if (isAttachView() && settingType == SettingCallBack.SettingType.WALK_REMINDER) {
            ((IWalkReminderView) getView()).onSetWalkReminderFailed();
        }
    }

    @Override // com.ido.life.module.device.presenter.BaseMonitoringPresenter, com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdSuccess(SettingCallBack.SettingType settingType, Object obj) {
        super.onSetCmdSuccess(settingType, obj);
        if (settingType == SettingCallBack.SettingType.WALK_REMINDER && isAttachView()) {
            SPHelper.saveWalkReminder(getWalkReminderState());
            ((IWalkReminderView) getView()).onSetWalkReminderSuccess();
        }
    }

    public String[] getGoalStepItems() {
        if (this.mItemArr == null) {
            this.mItemArr = new String[18];
            for (int i = 0; i < 18; i++) {
                this.mItemArr[i] = String.valueOf((i * 25) + 75);
            }
        }
        return this.mItemArr;
    }
}