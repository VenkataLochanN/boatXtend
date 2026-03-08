package com.ido.life.module.device.reminders;

import com.ido.ble.protocol.model.ScheduleReminderV3;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: IRemindersView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0012\u0010\u0004\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H&J\b\u0010\u0007\u001a\u00020\u0003H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH&J\u0016\u0010\u000e\u001a\u00020\u00032\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010H&J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\rH&J\u0018\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\nH&¨\u0006\u0016"}, d2 = {"Lcom/ido/life/module/device/reminders/IRemindersView;", "Lcom/ido/life/module/device/reminders/IBaseOperateView;", "onDeleteEventReminderFailed", "", "onDeleteEventReminderSuccess", "scheduleReminderV3", "Lcom/ido/ble/protocol/model/ScheduleReminderV3;", "onQueryEventReminderFailed", "setComplete", "isComplete", "", "setCompletedDeviceReminderList", "int", "", "setDeviceReminderList", "scheduleReminderV3List", "", "setId", "id", "setNoReminderLayout", "hasNoData", "hasCompleteData", "app_release"}, k = 1, mv = {1, 1, 16})
public interface IRemindersView extends IBaseOperateView {
    void onDeleteEventReminderFailed();

    void onDeleteEventReminderSuccess(ScheduleReminderV3 scheduleReminderV3);

    void onQueryEventReminderFailed();

    void setComplete(boolean isComplete);

    void setCompletedDeviceReminderList(int i);

    void setDeviceReminderList(List<? extends ScheduleReminderV3> scheduleReminderV3List);

    void setId(int id);

    void setNoReminderLayout(boolean hasNoData, boolean hasCompleteData);
}