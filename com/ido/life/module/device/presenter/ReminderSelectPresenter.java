package com.ido.life.module.device.presenter;

import androidx.core.app.NotificationCompat;
import com.ido.ble.BLEManager;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.ScheduleReminderSwitch;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.module.device.view.IReminderSelectView;
import com.ido.life.util.SPHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReminderSelectPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0006\u001a\u00020\u0005J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0014J\u001c\u0010\u000b\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\u0010\u0010\u000e\u001a\u00020\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/ido/life/module/device/presenter/ReminderSelectPresenter;", "Lcom/ido/life/base/BaseCmdPresenter;", "Lcom/ido/life/module/device/view/IReminderSelectView;", "()V", "mScheduleReminderSwitch", "Lcom/ido/ble/protocol/model/ScheduleReminderSwitch;", "getReminderStatus", "onSetCmdFailed", "", "settingType", "Lcom/ido/ble/callback/SettingCallBack$SettingType;", "onSetCmdSuccess", "o", "", "setReminderStatus", NotificationCompat.CATEGORY_STATUS, "app_release"}, k = 1, mv = {1, 1, 16})
public final class ReminderSelectPresenter extends BaseCmdPresenter<IReminderSelectView> {
    private ScheduleReminderSwitch mScheduleReminderSwitch;

    public final ScheduleReminderSwitch getReminderStatus() {
        ScheduleReminderSwitch scheduleReminderSwitch = SPHelper.getScheduleReminderSwitch();
        Intrinsics.checkExpressionValueIsNotNull(scheduleReminderSwitch, "SPHelper.getScheduleReminderSwitch()");
        return scheduleReminderSwitch;
    }

    public final void setReminderStatus(ScheduleReminderSwitch status) {
        this.mScheduleReminderSwitch = status;
        CommonLogUtil.printAndSave("setReminderStatus status = " + status);
        BLEManager.setScheduleReminderSwitch(status);
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdFailed(SettingCallBack.SettingType settingType) {
        super.onSetCmdFailed(settingType);
        if (settingType == SettingCallBack.SettingType.SCHEDULE_REMINDER_SWITCH) {
            CommonLogUtil.printAndSave("setReminderStatus onSetCmdFailed");
            IReminderSelectView iReminderSelectView = (IReminderSelectView) getView();
            if (iReminderSelectView != null) {
                iReminderSelectView.onSetReminderStatusFailed();
            }
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    protected void onSetCmdSuccess(SettingCallBack.SettingType settingType, Object o) {
        super.onSetCmdSuccess(settingType, o);
        if (settingType == SettingCallBack.SettingType.SCHEDULE_REMINDER_SWITCH) {
            CommonLogUtil.printAndSave("setReminderStatus onSetCmdSuccess");
            SPHelper.saveScheduleReminderSwitch(this.mScheduleReminderSwitch);
            IReminderSelectView iReminderSelectView = (IReminderSelectView) getView();
            if (iReminderSelectView != null) {
                iReminderSelectView.onSetReminderStatusSuccess();
            }
        }
    }
}