package com.ido.life.module.device.reminders.add;

import android.text.TextUtils;
import com.ido.ble.BLEManager;
import com.ido.ble.callback.OperateCallBack;
import com.ido.ble.protocol.model.ScheduleReminderV3;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.TimeUtil;
import com.ido.life.module.device.reminders.BaseOperatePresenter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: RemindAddPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \u00162\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0016B\u0005¢\u0006\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\u000bJ\u001a\u0010\r\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\tH\u0014J\u001a\u0010\u0011\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\tH\u0014J\u000e\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0014J\u0006\u0010\u0015\u001a\u00020\u0005¨\u0006\u0017"}, d2 = {"Lcom/ido/life/module/device/reminders/add/RemindAddPresenter;", "Lcom/ido/life/module/device/reminders/BaseOperatePresenter;", "Lcom/ido/life/module/device/reminders/add/IRemindAddView;", "()V", "addRemind", "", "scheduleReminderV3", "Lcom/ido/ble/protocol/model/ScheduleReminderV3;", "mIsAdd", "", "getCurrentHour", "", "getCurrentMin", "onOperateAddResult", "operateType", "Lcom/ido/ble/callback/OperateCallBack$OperateType;", "b", "onOperateModifyResult", "showCurrentBirthday", "birthday", "", "verify", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class RemindAddPresenter extends BaseOperatePresenter<IRemindAddView> {
    private static final String TAG = "RemindAddPresenter";

    public final void showCurrentBirthday(String birthday) {
        Intrinsics.checkParameterIsNotNull(birthday, "birthday");
        CommonLogUtil.d(TAG, "showCurrentBirthday: " + birthday);
        int[] iArr = new int[3];
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        String[] strArrSplitDate = TimeUtil.splitDate(calendar);
        int[] iArr2 = {Integer.parseInt(strArrSplitDate[0]), Integer.parseInt(strArrSplitDate[1]), Integer.parseInt(strArrSplitDate[2])};
        calendar.add(1, 100);
        String[] strArrSplitDate2 = TimeUtil.splitDate(calendar);
        int[] iArr3 = {Integer.parseInt(strArrSplitDate2[0]), Integer.parseInt(strArrSplitDate2[1]), Integer.parseInt(strArrSplitDate2[2])};
        String str = birthday;
        if (TextUtils.isEmpty(str)) {
            iArr[0] = iArr2[0];
            iArr[1] = iArr2[1];
            iArr[2] = iArr2[2];
        } else if (StringsKt.contains$default((CharSequence) str, (CharSequence) "/", false, 2, (Object) null)) {
            Object[] array = StringsKt.split$default((CharSequence) str, new String[]{"/"}, false, 0, 6, (Object) null).toArray(new String[0]);
            if (array != null) {
                String[] strArr = (String[]) array;
                iArr[0] = Integer.parseInt(strArr[0]);
                iArr[1] = Integer.parseInt(strArr[1]);
                iArr[2] = Integer.parseInt(strArr[2]);
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
        }
        ((IRemindAddView) getView()).setBirthday(iArr2, iArr3, iArr);
    }

    public final void addRemind(ScheduleReminderV3 scheduleReminderV3, boolean mIsAdd) {
        Intrinsics.checkParameterIsNotNull(scheduleReminderV3, "scheduleReminderV3");
        ArrayList arrayList = new ArrayList();
        scheduleReminderV3.setRemind_on_off(1);
        CommonLogUtil.d(TAG, "addRemind: " + scheduleReminderV3);
        scheduleReminderV3.setState(2);
        arrayList.add(scheduleReminderV3);
        CommonLogUtil.printAndSave("mIsAdd = " + mIsAdd + ", scheduleReminderV3 = " + scheduleReminderV3);
        if (mIsAdd) {
            BLEManager.addScheduleReminderV3(arrayList);
        } else {
            BLEManager.modifyScheduleReminderV3(arrayList);
        }
    }

    public final int getCurrentHour() {
        return Calendar.getInstance(Locale.CHINA).get(11);
    }

    public final int getCurrentMin() {
        return Calendar.getInstance(Locale.CHINA).get(12);
    }

    public final void verify() {
        if (getView() == 0) {
        }
    }

    @Override // com.ido.life.module.device.reminders.BaseOperatePresenter
    protected void onOperateAddResult(OperateCallBack.OperateType operateType, boolean b2) {
        super.onOperateAddResult(operateType, b2);
        CommonLogUtil.printAndSave("onOperateAddResult: " + b2);
        if (operateType == OperateCallBack.OperateType.SCHEDULE_REMINDER) {
            if (b2) {
                ((IRemindAddView) getView()).onSetEventReminderSuccess();
            } else {
                ((IRemindAddView) getView()).onSetEventReminderFailed();
            }
        }
    }

    @Override // com.ido.life.module.device.reminders.BaseOperatePresenter
    protected void onOperateModifyResult(OperateCallBack.OperateType operateType, boolean b2) {
        super.onOperateModifyResult(operateType, b2);
        CommonLogUtil.printAndSave("onOperateModifyResult: " + b2);
        if (operateType == OperateCallBack.OperateType.SCHEDULE_REMINDER) {
            if (b2) {
                ((IRemindAddView) getView()).onSetEventReminderSuccess();
            } else {
                ((IRemindAddView) getView()).onSetEventReminderFailed();
            }
        }
    }
}