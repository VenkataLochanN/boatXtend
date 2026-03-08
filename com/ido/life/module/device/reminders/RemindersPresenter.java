package com.ido.life.module.device.reminders;

import android.util.Log;
import com.ido.ble.BLEManager;
import com.ido.ble.callback.OperateCallBack;
import com.ido.ble.protocol.model.ScheduleReminderV3;
import com.ido.common.log.CommonLogUtil;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RemindersPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00142\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0014B\u0005¢\u0006\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007J\u001a\u0010\u000b\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u0005H\u0014J\u001c\u0010\u000f\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\u001a\u0010\u0012\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u0005H\u0014J\u0006\u0010\u0013\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/ido/life/module/device/reminders/RemindersPresenter;", "Lcom/ido/life/module/device/reminders/BaseOperatePresenter;", "Lcom/ido/life/module/device/reminders/IRemindersView;", "()V", "mIsQuery", "", "mScheduleReminderV3", "Lcom/ido/ble/protocol/model/ScheduleReminderV3;", "deleteScheduleReminder", "", "scheduleReminderV3", "onOperateDeleteResult", "operateType", "Lcom/ido/ble/callback/OperateCallBack$OperateType;", "b", "onOperateQueryResult", "o", "", "onOperateSetResult", "queryScheduleReminder", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class RemindersPresenter extends BaseOperatePresenter<IRemindersView> {
    private static final String TAG = "RemindersPresenter";
    private boolean mIsQuery;
    private ScheduleReminderV3 mScheduleReminderV3;

    public final void queryScheduleReminder() {
        Log.d(TAG, "queryScheduleReminder: ");
        this.mIsQuery = true;
        BLEManager.queryScheduleReminderV3();
    }

    public final void deleteScheduleReminder(ScheduleReminderV3 scheduleReminderV3) {
        Intrinsics.checkParameterIsNotNull(scheduleReminderV3, "scheduleReminderV3");
        this.mScheduleReminderV3 = scheduleReminderV3;
        ArrayList arrayList = new ArrayList();
        arrayList.add(scheduleReminderV3);
        CommonLogUtil.printAndSave("deleteScheduleReminder: " + scheduleReminderV3);
        BLEManager.deleteScheduleReminderV3(arrayList);
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00cc  */
    @Override // com.ido.life.module.device.reminders.BaseOperatePresenter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onOperateQueryResult(com.ido.ble.callback.OperateCallBack.OperateType r8, java.lang.Object r9) {
        /*
            Method dump skipped, instruction units count: 210
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.device.reminders.RemindersPresenter.onOperateQueryResult(com.ido.ble.callback.OperateCallBack$OperateType, java.lang.Object):void");
    }

    @Override // com.ido.life.module.device.reminders.BaseOperatePresenter
    protected void onOperateSetResult(OperateCallBack.OperateType operateType, boolean b2) {
        super.onOperateSetResult(operateType, b2);
        CommonLogUtil.printAndSave("onOperateSetResult: " + operateType + b2);
    }

    @Override // com.ido.life.module.device.reminders.BaseOperatePresenter
    protected void onOperateDeleteResult(OperateCallBack.OperateType operateType, boolean b2) {
        super.onOperateDeleteResult(operateType, b2);
        CommonLogUtil.printAndSave("onOperateDeleteResult: " + operateType + ',' + b2);
        if (operateType == OperateCallBack.OperateType.SCHEDULE_REMINDER) {
            ((IRemindersView) getView()).onDeleteEventReminderSuccess(this.mScheduleReminderV3);
        } else {
            ((IRemindersView) getView()).onDeleteEventReminderFailed();
        }
    }
}