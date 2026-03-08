package com.ido.life.module.device.reminders.complete;

import android.util.Log;
import com.ido.ble.BLEManager;
import com.ido.ble.callback.OperateCallBack;
import com.ido.ble.protocol.model.ScheduleReminderV3;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.module.device.reminders.BaseOperatePresenter;
import com.ido.life.util.DateUtil;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: compiled from: RemindsCompletePresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00122\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005J\u001a\u0010\t\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0014J\u001c\u0010\u000e\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\u0006\u0010\u0011\u001a\u00020\u0007R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/ido/life/module/device/reminders/complete/RemindsCompletePresenter;", "Lcom/ido/life/module/device/reminders/BaseOperatePresenter;", "Lcom/ido/life/module/device/reminders/complete/IRemindCompleteView;", "()V", "mScheduleReminderV3", "Lcom/ido/ble/protocol/model/ScheduleReminderV3;", "deleteScheduleReminder", "", "scheduleReminderV3", "onOperateDeleteResult", "operateType", "Lcom/ido/ble/callback/OperateCallBack$OperateType;", "b", "", "onOperateQueryResult", "o", "", "queryScheduleReminder", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class RemindsCompletePresenter extends BaseOperatePresenter<IRemindCompleteView> {
    private static final String TAG = "RemindsCompletePresente";
    private ScheduleReminderV3 mScheduleReminderV3;

    public final void queryScheduleReminder() {
        Log.d(TAG, "queryScheduleReminder: ");
        removeCallBack();
        addCallBack();
        BLEManager.queryScheduleReminderV3();
    }

    @Override // com.ido.life.module.device.reminders.BaseOperatePresenter
    protected void onOperateQueryResult(OperateCallBack.OperateType operateType, Object o) {
        super.onOperateQueryResult(operateType, o);
        if (operateType == OperateCallBack.OperateType.SCHEDULE_REMINDER) {
            CommonLogUtil.printAndSave("onOperateQueryResult：" + o);
            removeCallBack();
            if (o != null) {
                List<ScheduleReminderV3> listAsMutableList = TypeIntrinsics.asMutableList(o);
                ArrayList arrayList = new ArrayList();
                if (!listAsMutableList.isEmpty()) {
                    for (ScheduleReminderV3 scheduleReminderV3 : listAsMutableList) {
                        CommonLogUtil.printAndSave("scheduleReminder：" + scheduleReminderV3);
                        if (scheduleReminderV3.getState() == 0 && scheduleReminderV3.getYear() + scheduleReminderV3.getMon() + scheduleReminderV3.getDay() + scheduleReminderV3.getHour() + scheduleReminderV3.getMin() + scheduleReminderV3.getSec() > 0) {
                            arrayList.add(scheduleReminderV3);
                        }
                    }
                    if (arrayList.size() > 1) {
                        CollectionsKt.sortWith(arrayList, new Comparator<T>() { // from class: com.ido.life.module.device.reminders.complete.RemindsCompletePresenter$onOperateQueryResult$$inlined$sortByDescending$1
                            /* JADX WARN: Multi-variable type inference failed */
                            @Override // java.util.Comparator
                            public final int compare(T t, T t2) {
                                ScheduleReminderV3 scheduleReminderV32 = (ScheduleReminderV3) t2;
                                StringBuilder sb = new StringBuilder();
                                sb.append(scheduleReminderV32.getYear());
                                sb.append(scheduleReminderV32.getMon());
                                sb.append(scheduleReminderV32.getDay());
                                sb.append(' ');
                                sb.append(scheduleReminderV32.getHour());
                                sb.append(':');
                                sb.append(scheduleReminderV32.getMin());
                                sb.append(':');
                                sb.append(scheduleReminderV32.getSec());
                                Date date = DateUtil.parse(sb.toString(), "yyyyMMdd HH:mm:ss");
                                ScheduleReminderV3 scheduleReminderV33 = (ScheduleReminderV3) t;
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append(scheduleReminderV33.getYear());
                                sb2.append(scheduleReminderV33.getMon());
                                sb2.append(scheduleReminderV33.getDay());
                                sb2.append(' ');
                                sb2.append(scheduleReminderV33.getHour());
                                sb2.append(':');
                                sb2.append(scheduleReminderV33.getMin());
                                sb2.append(':');
                                sb2.append(scheduleReminderV33.getSec());
                                return ComparisonsKt.compareValues(date, DateUtil.parse(sb2.toString(), "yyyyMMdd HH:mm:ss"));
                            }
                        });
                    }
                    IRemindCompleteView iRemindCompleteView = (IRemindCompleteView) getView();
                    if (iRemindCompleteView != null) {
                        iRemindCompleteView.setDeviceReminderList(arrayList);
                        return;
                    }
                    return;
                }
                return;
            }
            IRemindCompleteView iRemindCompleteView2 = (IRemindCompleteView) getView();
            if (iRemindCompleteView2 != null) {
                iRemindCompleteView2.onQueryEventReminderFailed();
            }
        }
    }

    public final void deleteScheduleReminder(ScheduleReminderV3 scheduleReminderV3) {
        Intrinsics.checkParameterIsNotNull(scheduleReminderV3, "scheduleReminderV3");
        this.mScheduleReminderV3 = scheduleReminderV3;
        ArrayList arrayList = new ArrayList();
        arrayList.add(scheduleReminderV3);
        removeCallBack();
        addCallBack();
        BLEManager.deleteScheduleReminderV3(arrayList);
    }

    @Override // com.ido.life.module.device.reminders.BaseOperatePresenter
    protected void onOperateDeleteResult(OperateCallBack.OperateType operateType, boolean b2) {
        super.onOperateDeleteResult(operateType, b2);
        if (operateType == OperateCallBack.OperateType.SCHEDULE_REMINDER) {
            removeCallBack();
            if (b2) {
                ((IRemindCompleteView) getView()).onDeleteEventReminderSuccess(this.mScheduleReminderV3);
            } else {
                ((IRemindCompleteView) getView()).onDeleteEventReminderFailed();
            }
        }
    }
}