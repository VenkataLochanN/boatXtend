package com.ido.life.module.home.menstrualcycle;

import com.ido.life.customview.MenstruationCalendar;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: MenstrualCycleDetailCallback.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J\b\u0010\b\u001a\u00020\u0006H&J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006H&¨\u0006\f"}, d2 = {"Lcom/ido/life/module/home/menstrualcycle/MenstrualCycleDetailCallback;", "", "getMenstruationDateList", "", "Lcom/ido/life/customview/MenstruationCalendar$DateInfo;", "year", "", "month", "getWeekStart", "updateMonthOffest", "", "offset", "app_release"}, k = 1, mv = {1, 1, 16})
public interface MenstrualCycleDetailCallback {
    List<MenstruationCalendar.DateInfo> getMenstruationDateList(int year, int month);

    int getWeekStart();

    void updateMonthOffest(int offset);
}