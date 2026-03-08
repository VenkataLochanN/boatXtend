package com.ido.life.module.device.reminders.complete;

import com.ido.ble.protocol.model.ScheduleReminderV3;
import com.ido.life.module.device.reminders.IBaseOperateView;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: IRemindCompleteView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0012\u0010\u0004\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H&J\b\u0010\u0007\u001a\u00020\u0003H&J\u0016\u0010\b\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\nH&¨\u0006\u000b"}, d2 = {"Lcom/ido/life/module/device/reminders/complete/IRemindCompleteView;", "Lcom/ido/life/module/device/reminders/IBaseOperateView;", "onDeleteEventReminderFailed", "", "onDeleteEventReminderSuccess", "scheduleReminderV3", "Lcom/ido/ble/protocol/model/ScheduleReminderV3;", "onQueryEventReminderFailed", "setDeviceReminderList", "scheduleReminderV3List", "", "app_release"}, k = 1, mv = {1, 1, 16})
public interface IRemindCompleteView extends IBaseOperateView {
    void onDeleteEventReminderFailed();

    void onDeleteEventReminderSuccess(ScheduleReminderV3 scheduleReminderV3);

    void onQueryEventReminderFailed();

    void setDeviceReminderList(List<? extends ScheduleReminderV3> scheduleReminderV3List);
}