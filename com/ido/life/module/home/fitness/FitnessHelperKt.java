package com.ido.life.module.home.fitness;

import android.database.Cursor;
import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPath;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.database.model.ActiveTimeDayData;
import com.ido.life.database.model.CalorieDayData;
import com.ido.life.database.model.UserInfo;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.database.model.WalkDayData;
import com.ido.life.enums.StageInfoEnum;
import com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.UnitUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import org.apache.commons.io.IOUtils;

/* JADX INFO: compiled from: FitnessHelper.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0088\u0001\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u0016\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u001e\u0010\b\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b\u001a\u0010\u0010\r\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000b\u001a\u000e\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u000e\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\t\u001a\u000e\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\t\u001a$\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b\u001a\u0018\u0010\u0016\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0002\u001a\"\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u001a\u001e\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b\u001a\"\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\u001a2\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u0006\u001a2\u0010\u001d\u001a\u00020\u00062\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010 \u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u0006\u001a$\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b\u001a$\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b\u001a\u0010\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'\u001a\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00020)\u001a\u0016\u0010*\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010+\u001a\u00020,\u001a\u0014\u0010-\u001a\b\u0012\u0004\u0012\u00020.0\u00012\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0018\u0010/\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\b\u00100\u001a\u0004\u0018\u00010\u000b\u001a\u0016\u00101\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010+\u001a\u00020,\u001a\f\u00102\u001a\b\u0012\u0004\u0012\u00020,0)\u001a.\u00103\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0015042\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u001a.\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u000207062\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u001a\u0018\u00108\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\b\u00100\u001a\u0004\u0018\u00010\u000b\u001a\u0006\u00109\u001a\u00020\u000b\u001a\u0016\u0010:\u001a\u0002072\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u00100\u001a\u00020\u000b\u001a\"\u0010;\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u001a*\u0010<\u001a\n\u0012\u0004\u0012\u00020=\u0018\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u001a\"\u0010>\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u001a0\u0010?\u001a\b\u0012\u0004\u0012\u00020@0\u00012\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010A\u001a\u00020\u0015\u001a\u0018\u0010B\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\b\u00100\u001a\u0004\u0018\u00010\u000b\u001a\u0018\u0010C\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\b\u00100\u001a\u0004\u0018\u00010\u000b\u001a\"\u0010D\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u001a*\u0010E\u001a\n\u0012\u0004\u0012\u00020F\u0018\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u001a\u0018\u0010G\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\b\u00100\u001a\u0004\u0018\u00010\u000b\u001a\u0018\u0010H\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\b\u00100\u001a\u0004\u0018\u00010\u000b\u001a\u0016\u0010I\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010+\u001a\u00020,\u001a\u0012\u0010J\u001a\u00020%2\b\u0010K\u001a\u0004\u0018\u00010\u000bH\u0002\u001a$\u0010L\u001a\b\u0012\u0004\u0012\u00020@0\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b\u001a\u001e\u0010M\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010N\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020,\u001a\u000e\u0010O\u001a\u00020\u00152\u0006\u0010P\u001a\u00020=\u001a\u000e\u0010Q\u001a\u00020\u00152\u0006\u0010R\u001a\u00020@\u001a\u000e\u0010S\u001a\u00020\u00152\u0006\u0010T\u001a\u00020F¨\u0006U"}, d2 = {"calculateRecommendActiveList", "", "Lcom/ido/life/module/home/recentsituation/RecentSituationDetailPresenter$RecommandActiceBean;", "userId", "", "calorie", "", "calculateWalkDuration", "calumetRetentivityActiveCalorieAvg", "", "startDate", "", "endDate", "caluteAge", "birthDay", "caluteBMR", "caluteCalorieMax", "bmr", "caluteCalorieMin", "caluteCalorieValidStateList", "Ljava/util/LinkedList;", "", "caluteDayCount", "caluteRecentSituationActiveTimeAvg", "caluteRecentSituationWalkAvg", "caluteStageDate", "Lkotlin/Pair;", "beforeDayCount", "dayCount", "caluteTargetStateLeftDay", "recentLinkedList", "pastLinkedList", "recentTargetValidCount", "pastTargetValidCount", "caluteTimeValidStateList", "caluteWalkValidStateList", "closeCursor", "", "cursor", "Landroid/database/Cursor;", "getActiveTimeRecommandActiveList", "", "getActiveTimeTargetStageLeft", "stage", "Lcom/ido/life/enums/StageInfoEnum;", "getAllRecommandActive", "Lcom/ido/life/module/home/recentsituation/RecentSituationDetailPresenter$RecommandActive;", "getCalorieTarget", "date", "getCalorieTargetStageLeft", "getSiturationStageList", "getSleepDate", "", "getTargetByDateArea", "", "Lcom/ido/life/database/model/UserTargetNew;", "getTimeTarget", "getToday", "getUserTarget", "getValidActiveTimeCount", "getValidActiveTimeData", "Lcom/ido/life/database/model/ActiveTimeDayData;", "getValidCalorieCount", "getValidCalorieData", "Lcom/ido/life/database/model/CalorieDayData;", "needValid", "getValidCalorieTarget", "getValidTimeTarget", "getValidWalkCount", "getValidWalkData", "Lcom/ido/life/database/model/WalkDayData;", "getValidWalkTarget", "getWalkTarget", "getWalkTargetStageLeft", "printAndSaveLog", "message", "queryCalorieData", "recentSatisfyPrimaryStage", "dateType", "validActiveTimeData", "activeTimeDayData", "validCalorieData", "calorieDayData", "validWalkData", "walkDayData", "app_release"}, k = 2, mv = {1, 1, 16})
public final class FitnessHelperKt {
    public static final boolean validCalorieData(CalorieDayData calorieDayData) {
        Intrinsics.checkParameterIsNotNull(calorieDayData, "calorieDayData");
        ActiveTimeDayData activeTimeDayDataQueryActiveTimeDayByDate = GreenDaoUtil.queryActiveTimeDayByDate(calorieDayData.getUserId(), calorieDayData.getDate());
        if (activeTimeDayDataQueryActiveTimeDayByDate != null) {
            List<Integer> wearDurationList = activeTimeDayDataQueryActiveTimeDayByDate.getWearDurationList();
            if (!(wearDurationList == null || wearDurationList.isEmpty())) {
                return activeTimeDayDataQueryActiveTimeDayByDate.getTotalWearDuration() > 0 || GreenDaoUtil.querySleepDayByDate(calorieDayData.getUserId(), calorieDayData.getDate()) != null;
            }
        }
        return calorieDayData.getActivityCalorie() > 0 || GreenDaoUtil.querySleepDayByDate(calorieDayData.getUserId(), calorieDayData.getDate()) != null;
    }

    public static final boolean validActiveTimeData(ActiveTimeDayData activeTimeDayData) {
        Intrinsics.checkParameterIsNotNull(activeTimeDayData, "activeTimeDayData");
        List<Integer> wearDurationList = activeTimeDayData.getWearDurationList();
        if (!(wearDurationList == null || wearDurationList.isEmpty())) {
            return activeTimeDayData.getTotalWearDuration() > 0 || GreenDaoUtil.querySleepDayByDate(activeTimeDayData.getUserId(), activeTimeDayData.getDate()) != null;
        }
        CalorieDayData calorieDayDataQueryCalorieDayByDate = GreenDaoUtil.queryCalorieDayByDate(activeTimeDayData.getUserId(), activeTimeDayData.getDate());
        if (calorieDayDataQueryCalorieDayByDate != null) {
            return calorieDayDataQueryCalorieDayByDate.getActivityCalorie() > 0 || GreenDaoUtil.querySleepDayByDate(activeTimeDayData.getUserId(), activeTimeDayData.getDate()) != null;
        }
        return false;
    }

    public static final boolean validWalkData(WalkDayData walkDayData) {
        Intrinsics.checkParameterIsNotNull(walkDayData, "walkDayData");
        ActiveTimeDayData activeTimeDayDataQueryActiveTimeDayByDate = GreenDaoUtil.queryActiveTimeDayByDate(walkDayData.getUserId(), walkDayData.getDate());
        if (activeTimeDayDataQueryActiveTimeDayByDate != null) {
            return validActiveTimeData(activeTimeDayDataQueryActiveTimeDayByDate);
        }
        return false;
    }

    public static final List<StageInfoEnum> getSiturationStageList() {
        return CollectionsKt.mutableListOf(StageInfoEnum.PRIMARY, StageInfoEnum.SECOND, StageInfoEnum.THIRD);
    }

    public static final Pair<String, String> caluteStageDate(int i, int i2) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.add(5, -i);
        String str = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
        calendar.add(5, -i2);
        return new Pair<>(DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD), str);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0097 A[PHI: r4 r9
  0x0097: PHI (r4v5 java.lang.String) = (r4v2 java.lang.String), (r4v3 java.lang.String), (r4v7 java.lang.String) binds: [B:24:0x0158, B:17:0x00f9, B:10:0x0095] A[DONT_GENERATE, DONT_INLINE]
  0x0097: PHI (r9v10 java.lang.String) = (r9v5 java.lang.String), (r9v8 java.lang.String), (r9v13 java.lang.String) binds: [B:24:0x0158, B:17:0x00f9, B:10:0x0095] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final boolean recentSatisfyPrimaryStage(long r7, java.lang.String r9, com.ido.life.enums.StageInfoEnum r10) {
        /*
            Method dump skipped, instruction units count: 395
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.fitness.FitnessHelperKt.recentSatisfyPrimaryStage(long, java.lang.String, com.ido.life.enums.StageInfoEnum):boolean");
    }

    public static final float calumetRetentivityActiveCalorieAvg(long j, String startDate, String endDate) {
        Cursor cursorExecSql;
        Intrinsics.checkParameterIsNotNull(startDate, "startDate");
        Intrinsics.checkParameterIsNotNull(endDate, "endDate");
        if (startDate.length() == 0) {
            return 0.0f;
        }
        if ((endDate.length() == 0) || (cursorExecSql = GreenDaoUtil.execSql("\n        select avg(ACTIVITY_CALORIE) from (select c.ACTIVITY_CALORIE from CALORIE_DAY_DATA c left join ACTIVE_TIME_DAY_DATA a on c.DATE=a.DATE and c.USER_ID=a.USER_ID \n        left join SERVER_SLEEP_DAY_DATA s on a.DATE=s.DATE and a.USER_ID=s.USER_ID where c.USER_ID=? and c.DATE>=? and c.DATE<=? and \n        (\n        (a.WEAR_DURATION_LIST is not null and a.WEAR_DURATION_LIST not in ('','null') and a.TOTAL_WEAR_DURATION>0) or \n        (c.ACTIVITY_CALORIE>0 and (a.WEAR_DURATION_LIST is null or a.WEAR_DURATION_LIST in ('','null'))) \n         or s.DATE is not null) \n         group by a.DATE, c.DATE, s.DATE)\n    ", new String[]{String.valueOf(j), startDate, endDate})) == null || !cursorExecSql.moveToNext()) {
            return 0.0f;
        }
        return cursorExecSql.getFloat(0);
    }

    public static final float caluteRecentSituationActiveTimeAvg(long j, String str, String str2) {
        Cursor cursorExecSql;
        String str3 = str;
        if (str3 == null || str3.length() == 0) {
            return 0.0f;
        }
        String str4 = str2;
        if ((str4 == null || str4.length() == 0) || (cursorExecSql = GreenDaoUtil.execSql("\n        select avg(MEDIUM_OR_HIGHER_SECONDS) from (select a.MEDIUM_OR_HIGHER_SECONDS from ACTIVE_TIME_DAY_DATA a left join CALORIE_DAY_DATA c on a.DATE=c.DATE and a.USER_ID=c.USER_ID \n        left join SERVER_SLEEP_DAY_DATA s on c.DATE=s.DATE and c.USER_ID=s.USER_ID where a.USER_ID=? and a.DATE>=? and a.DATE<=? and \n        (\n        (a.WEAR_DURATION_LIST is not null and a.WEAR_DURATION_LIST not in ('','null') and a.TOTAL_WEAR_DURATION>0) or \n        (c.ACTIVITY_CALORIE>0 and (a.WEAR_DURATION_LIST is null or a.WEAR_DURATION_LIST in ('','null'))) or \n        s.DATE is not null) \n        group by a.DATE, c.DATE, s.DATE)\n    ", new String[]{String.valueOf(j), str, str2})) == null || !cursorExecSql.moveToNext()) {
            return 0.0f;
        }
        return cursorExecSql.getFloat(0) / 60;
    }

    public static final float caluteRecentSituationWalkAvg(long j, String startDate, String endDate) {
        Intrinsics.checkParameterIsNotNull(startDate, "startDate");
        Intrinsics.checkParameterIsNotNull(endDate, "endDate");
        float f2 = 0.0f;
        if (!(startDate.length() == 0)) {
            if (!(endDate.length() == 0)) {
                Cursor cursorExecSql = GreenDaoUtil.execSql("\n        select avg(REACH_SECONDS) from (select w.REACH_SECONDS from WALK_DAY_DATA w left join ACTIVE_TIME_DAY_DATA a on w.DATE=a.DATE and w.USER_ID=a.USER_ID \n        left join CALORIE_DAY_DATA c on c.DATE=a.DATE and c.USER_ID=a.USER_ID left join SERVER_SLEEP_DAY_DATA s on c.DATE=s.DATE and c.USER_ID=s.USER_ID \n        where w.USER_ID=? and w.DATE>=? and w.DATE<=? and (\n        (a.WEAR_DURATION_LIST is not null and a.WEAR_DURATION_LIST not in ('','null') and a.TOTAL_WEAR_DURATION>0) or \n        (c.ACTIVITY_CALORIE>0 and (a.WEAR_DURATION_LIST is null or a.WEAR_DURATION_LIST in ('','null'))) or \n        s.DATE is not null)\n        group by a.DATE, w.DATE, c.DATE, s.DATE)\n    ", new String[]{String.valueOf(j), startDate, endDate});
                if (cursorExecSql != null && cursorExecSql.moveToNext()) {
                    f2 = cursorExecSql.getFloat(0) / 3600;
                }
                printAndSaveLog("caluteRecentSituationWalkAvg userId=" + j + ",startDate=" + startDate + ",endDate=" + endDate + ",avg=" + f2);
            }
        }
        return f2;
    }

    private static final int caluteDayCount(String str, String str2) {
        try {
            Calendar startCalendar = Calendar.getInstance(Locale.CHINA);
            Intrinsics.checkExpressionValueIsNotNull(startCalendar, "startCalendar");
            startCalendar.setTime(DateUtil.string2Date(str, DateUtil.DATE_FORMAT_YMD));
            startCalendar.set(11, 0);
            startCalendar.set(12, 0);
            startCalendar.set(13, 0);
            startCalendar.set(14, 0);
            Calendar endCalendar = Calendar.getInstance(Locale.CHINA);
            Intrinsics.checkExpressionValueIsNotNull(endCalendar, "endCalendar");
            endCalendar.setTime(DateUtil.string2Date(str2, DateUtil.DATE_FORMAT_YMD));
            endCalendar.set(11, 0);
            endCalendar.set(12, 0);
            endCalendar.set(13, 0);
            endCalendar.set(14, 0);
            return ((int) ((endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis()) / ((long) 86400000))) + 1;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static final int getCalorieTargetStageLeft(long j, StageInfoEnum stage) {
        Intrinsics.checkParameterIsNotNull(stage, "stage");
        Pair<String, String> pairCaluteStageDate = caluteStageDate(stage.getMRecentStartDayCount(), stage.getMRecentEndDayCount() - stage.getMRecentStartDayCount());
        Pair<String, String> pairCaluteStageDate2 = caluteStageDate(stage.getMPastStartDayCount(), stage.getMPastEndDayCount() - stage.getMPastStartDayCount());
        int iCaluteTargetStateLeftDay = caluteTargetStateLeftDay(caluteCalorieValidStateList(j, pairCaluteStageDate.getFirst(), pairCaluteStageDate.getSecond()), caluteCalorieValidStateList(j, pairCaluteStageDate2.getFirst(), pairCaluteStageDate2.getSecond()), stage.getMRecentValidDayCount(), stage.getMPastValidDayCount());
        printAndSaveLog("活动卡路里到达" + LanguageUtil.getLanguageText(stage.getMTitleResId()) + "，最少需要 " + iCaluteTargetStateLeftDay + " 天");
        return iCaluteTargetStateLeftDay;
    }

    public static final int getActiveTimeTargetStageLeft(long j, StageInfoEnum stage) {
        Intrinsics.checkParameterIsNotNull(stage, "stage");
        Pair<String, String> pairCaluteStageDate = caluteStageDate(stage.getMRecentStartDayCount(), stage.getMRecentEndDayCount() - stage.getMRecentStartDayCount());
        Pair<String, String> pairCaluteStageDate2 = caluteStageDate(stage.getMPastStartDayCount(), stage.getMPastEndDayCount() - stage.getMPastStartDayCount());
        int iCaluteTargetStateLeftDay = caluteTargetStateLeftDay(caluteTimeValidStateList(j, pairCaluteStageDate.getFirst(), pairCaluteStageDate.getSecond()), caluteTimeValidStateList(j, pairCaluteStageDate2.getFirst(), pairCaluteStageDate2.getSecond()), stage.getMRecentValidDayCount(), stage.getMPastValidDayCount());
        printAndSaveLog("锻炼到达" + LanguageUtil.getLanguageText(stage.getMTitleResId()) + "最少需要，" + iCaluteTargetStateLeftDay + " 天");
        return iCaluteTargetStateLeftDay;
    }

    public static final int getWalkTargetStageLeft(long j, StageInfoEnum stage) {
        Intrinsics.checkParameterIsNotNull(stage, "stage");
        Pair<String, String> pairCaluteStageDate = caluteStageDate(stage.getMRecentStartDayCount(), stage.getMRecentEndDayCount() - stage.getMRecentStartDayCount());
        Pair<String, String> pairCaluteStageDate2 = caluteStageDate(stage.getMPastStartDayCount(), stage.getMPastEndDayCount() - stage.getMPastStartDayCount());
        int iCaluteTargetStateLeftDay = caluteTargetStateLeftDay(caluteWalkValidStateList(j, pairCaluteStageDate.getFirst(), pairCaluteStageDate.getSecond()), caluteWalkValidStateList(j, pairCaluteStageDate2.getFirst(), pairCaluteStageDate2.getSecond()), stage.getMRecentValidDayCount(), stage.getMPastValidDayCount());
        printAndSaveLog("走动到达" + LanguageUtil.getLanguageText(stage.getMTitleResId()) + "，最少需要 " + iCaluteTargetStateLeftDay + " 天");
        return iCaluteTargetStateLeftDay;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x009e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.util.LinkedList<java.lang.Boolean> caluteCalorieValidStateList(long r9, java.lang.String r11, java.lang.String r12) {
        /*
            java.lang.String r0 = "startDate"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r11, r0)
            java.lang.String r0 = "endDate"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r12, r0)
            java.util.LinkedList r0 = new java.util.LinkedList
            r0.<init>()
            int r1 = caluteDayCount(r11, r12)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r11)
            r3 = 45
            r2.append(r3)
            r2.append(r12)
            java.lang.String r3 = " 包含天数是"
            r2.append(r3)
            r2.append(r1)
            java.lang.String r2 = r2.toString()
            printAndSaveLog(r2)
            if (r1 > 0) goto L36
            return r0
        L36:
            java.util.Locale r2 = java.util.Locale.CHINA
            java.util.Calendar r2 = java.util.Calendar.getInstance(r2)
            java.lang.String r3 = "calendar"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            java.lang.String r3 = "yyyy-MM-dd"
            java.util.Date r4 = com.ido.life.util.DateUtil.string2Date(r11, r3)
            r2.setTime(r4)
            r4 = 0
            java.util.List r9 = getValidCalorieData(r9, r11, r12, r4)
            if (r9 != 0) goto L59
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.List r9 = (java.util.List) r9
        L59:
            r10 = r9
            java.util.Collection r10 = (java.util.Collection) r10
            boolean r10 = r10.isEmpty()
            if (r10 == 0) goto L64
            r10 = r4
            goto L68
        L64:
            int r10 = r9.size()
        L68:
            r11 = r4
            r12 = r11
        L6a:
            if (r11 >= r1) goto Laf
            java.lang.String r5 = com.ido.life.util.DateUtil.format(r2, r3)
            r6 = 1
            if (r10 <= r12) goto L9e
            java.lang.Object r7 = r9.get(r12)
            com.ido.life.database.model.CalorieDayData r7 = (com.ido.life.database.model.CalorieDayData) r7
            if (r5 != 0) goto L7e
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L7e:
            java.lang.String r7 = r7.getDate()
            java.lang.String r8 = "calorieItem.date"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r8)
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            if (r5 == 0) goto L96
            boolean r5 = r5.contentEquals(r7)
            if (r5 == 0) goto L9e
            int r12 = r12 + 1
            r5 = r12
            r12 = r6
            goto La0
        L96:
            java.lang.NullPointerException r9 = new java.lang.NullPointerException
            java.lang.String r10 = "null cannot be cast to non-null type java.lang.String"
            r9.<init>(r10)
            throw r9
        L9e:
            r5 = r12
            r12 = r4
        La0:
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r12)
            r0.add(r12)
            r12 = 5
            r2.add(r12, r6)
            int r11 = r11 + 1
            r12 = r5
            goto L6a
        Laf:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.fitness.FitnessHelperKt.caluteCalorieValidStateList(long, java.lang.String, java.lang.String):java.util.LinkedList");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x009f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.util.LinkedList<java.lang.Boolean> caluteTimeValidStateList(long r9, java.lang.String r11, java.lang.String r12) {
        /*
            java.lang.String r0 = "startDate"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r11, r0)
            java.lang.String r0 = "endDate"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r12, r0)
            java.util.LinkedList r0 = new java.util.LinkedList
            r0.<init>()
            int r1 = caluteDayCount(r11, r12)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r11)
            r3 = 45
            r2.append(r3)
            r2.append(r12)
            java.lang.String r3 = " 包含天数是"
            r2.append(r3)
            r2.append(r1)
            java.lang.String r2 = r2.toString()
            printAndSaveLog(r2)
            if (r1 > 0) goto L36
            return r0
        L36:
            java.util.Locale r2 = java.util.Locale.CHINA
            java.util.Calendar r2 = java.util.Calendar.getInstance(r2)
            java.lang.String r3 = "calendar"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            java.lang.String r3 = "yyyy-MM-dd"
            java.util.Date r4 = com.ido.life.util.DateUtil.string2Date(r11, r3)
            r2.setTime(r4)
            java.util.List r9 = getValidActiveTimeData(r9, r11, r12)
            if (r9 != 0) goto L58
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.List r9 = (java.util.List) r9
        L58:
            r10 = r9
            java.util.Collection r10 = (java.util.Collection) r10
            boolean r10 = r10.isEmpty()
            r11 = 0
            if (r10 == 0) goto L64
            r10 = r11
            goto L68
        L64:
            int r10 = r9.size()
        L68:
            r12 = r11
            r4 = r12
        L6a:
            if (r12 >= r1) goto Lb0
            java.lang.String r5 = com.ido.life.util.DateUtil.format(r2, r3)
            r6 = 1
            if (r10 <= r4) goto L9f
            java.lang.Object r7 = r9.get(r4)
            com.ido.life.database.model.ActiveTimeDayData r7 = (com.ido.life.database.model.ActiveTimeDayData) r7
            if (r5 != 0) goto L7e
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L7e:
            java.lang.String r7 = r7.getDate()
            java.lang.String r8 = "timeItem.date"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r8)
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            if (r5 == 0) goto L97
            boolean r5 = r5.contentEquals(r7)
            if (r5 == 0) goto L9f
            int r4 = r4 + 1
            r5 = r4
            r4 = r6
            goto La1
        L97:
            java.lang.NullPointerException r9 = new java.lang.NullPointerException
            java.lang.String r10 = "null cannot be cast to non-null type java.lang.String"
            r9.<init>(r10)
            throw r9
        L9f:
            r5 = r4
            r4 = r11
        La1:
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            r0.add(r4)
            r4 = 5
            r2.add(r4, r6)
            int r12 = r12 + 1
            r4 = r5
            goto L6a
        Lb0:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.fitness.FitnessHelperKt.caluteTimeValidStateList(long, java.lang.String, java.lang.String):java.util.LinkedList");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x009f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.util.LinkedList<java.lang.Boolean> caluteWalkValidStateList(long r9, java.lang.String r11, java.lang.String r12) {
        /*
            java.lang.String r0 = "startDate"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r11, r0)
            java.lang.String r0 = "endDate"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r12, r0)
            java.util.LinkedList r0 = new java.util.LinkedList
            r0.<init>()
            int r1 = caluteDayCount(r11, r12)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r11)
            r3 = 45
            r2.append(r3)
            r2.append(r12)
            java.lang.String r3 = " 包含天数是"
            r2.append(r3)
            r2.append(r1)
            java.lang.String r2 = r2.toString()
            printAndSaveLog(r2)
            if (r1 > 0) goto L36
            return r0
        L36:
            java.util.Locale r2 = java.util.Locale.CHINA
            java.util.Calendar r2 = java.util.Calendar.getInstance(r2)
            java.lang.String r3 = "calendar"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            java.lang.String r3 = "yyyy-MM-dd"
            java.util.Date r4 = com.ido.life.util.DateUtil.string2Date(r11, r3)
            r2.setTime(r4)
            java.util.List r9 = getValidWalkData(r9, r11, r12)
            if (r9 != 0) goto L58
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.List r9 = (java.util.List) r9
        L58:
            r10 = r9
            java.util.Collection r10 = (java.util.Collection) r10
            boolean r10 = r10.isEmpty()
            r11 = 0
            if (r10 == 0) goto L64
            r10 = r11
            goto L68
        L64:
            int r10 = r9.size()
        L68:
            r12 = r11
            r4 = r12
        L6a:
            if (r12 >= r1) goto Lb0
            java.lang.String r5 = com.ido.life.util.DateUtil.format(r2, r3)
            r6 = 1
            if (r10 <= r4) goto L9f
            java.lang.Object r7 = r9.get(r4)
            com.ido.life.database.model.WalkDayData r7 = (com.ido.life.database.model.WalkDayData) r7
            if (r5 != 0) goto L7e
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L7e:
            java.lang.String r7 = r7.getDate()
            java.lang.String r8 = "walkItem.date"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r8)
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            if (r5 == 0) goto L97
            boolean r5 = r5.contentEquals(r7)
            if (r5 == 0) goto L9f
            int r4 = r4 + 1
            r5 = r4
            r4 = r6
            goto La1
        L97:
            java.lang.NullPointerException r9 = new java.lang.NullPointerException
            java.lang.String r10 = "null cannot be cast to non-null type java.lang.String"
            r9.<init>(r10)
            throw r9
        L9f:
            r5 = r4
            r4 = r11
        La1:
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            r0.add(r4)
            r4 = 5
            r2.add(r4, r6)
            int r12 = r12 + 1
            r4 = r5
            goto L6a
        Lb0:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.fitness.FitnessHelperKt.caluteWalkValidStateList(long, java.lang.String, java.lang.String):java.util.LinkedList");
    }

    public static final int caluteTargetStateLeftDay(LinkedList<Boolean> recentLinkedList, LinkedList<Boolean> pastLinkedList, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(recentLinkedList, "recentLinkedList");
        Intrinsics.checkParameterIsNotNull(pastLinkedList, "pastLinkedList");
        Iterator<T> it = pastLinkedList.iterator();
        int i3 = 0;
        int i4 = 0;
        while (it.hasNext()) {
            if (((Boolean) it.next()).booleanValue()) {
                i4++;
            }
        }
        Iterator<T> it2 = recentLinkedList.iterator();
        int i5 = 0;
        while (it2.hasNext()) {
            if (((Boolean) it2.next()).booleanValue()) {
                i5++;
            }
        }
        while (true) {
            if (i4 > i2 && i5 > i) {
                return i3;
            }
            Boolean boolPollFirst = pastLinkedList.pollFirst();
            Boolean boolPollFirst2 = recentLinkedList.pollFirst();
            pastLinkedList.add(true);
            recentLinkedList.add(true);
            i3++;
            if (Intrinsics.areEqual((Object) boolPollFirst, (Object) false)) {
                i4++;
            }
            if (Intrinsics.areEqual((Object) boolPollFirst2, (Object) false)) {
                i5++;
            }
        }
    }

    public static final int caluteBMR(long j) {
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(j);
        if (userInfoQueryUserInfo != null) {
            float weight = userInfoQueryUserInfo.getWeight();
            float height = userInfoQueryUserInfo.getHeight();
            int weightUnit = userInfoQueryUserInfo.getWeightUnit();
            if (weightUnit == 2) {
                weight = UnitUtil.getPound2Kg(weight);
            } else if (weightUnit == 3) {
                weight = UnitUtil.getSt2Kg(weight);
            }
            if (userInfoQueryUserInfo.getHeightUnit() == 2) {
                height = UnitUtil.inch2CmF(height);
            }
            int gender = userInfoQueryUserInfo.getGender();
            if (gender == 1) {
                return MathKt.roundToInt(((((double) (10 * weight)) + (((double) height) * 6.25d)) - ((double) (caluteAge(userInfoQueryUserInfo.getBirthday()) * 5))) + ((double) 5));
            }
            if (gender == 2) {
                return MathKt.roundToInt(((((double) (10 * weight)) + (((double) height) * 6.25d)) - ((double) (caluteAge(userInfoQueryUserInfo.getBirthday()) * 5))) - ((double) 161));
            }
        }
        return 0;
    }

    public static final int caluteCalorieMax(float f2) {
        int iRoundToInt = MathKt.roundToInt(f2 * 0.8f);
        if (iRoundToInt <= 50) {
            return 50;
        }
        return iRoundToInt % 50 != 0 ? ((iRoundToInt / 50) + 1) * 50 : iRoundToInt;
    }

    public static final int caluteCalorieMin(float f2) {
        int iRoundToInt = MathKt.roundToInt(0.8f * f2);
        int iRoundToInt2 = MathKt.roundToInt(f2 * 0.15f);
        if (iRoundToInt <= 50) {
            return 10;
        }
        return iRoundToInt2 % 50 != 0 ? (iRoundToInt2 / 50) * 50 : iRoundToInt2;
    }

    public static final int caluteAge(String str) {
        try {
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            int i = calendar.get(1);
            try {
                Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
                calendar.setTime(DateUtil.string2Date(str, DateUtil.DATE_FORMAT_YMD));
            } catch (Exception unused) {
                Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
                calendar.setTime(DateUtil.string2Date(str, DateUtil.DATE_FORMAT_YM_3));
            }
            return Math.max(0, i - calendar.get(1));
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static final UserTargetNew getUserTarget(long j, String date) {
        Intrinsics.checkParameterIsNotNull(date, "date");
        UserTargetNew userTargetNewQueryUserTarget = GreenDaoUtil.queryUserTarget(j, date);
        if (userTargetNewQueryUserTarget != null) {
            return userTargetNewQueryUserTarget;
        }
        UserTargetNew userTargetNewGenerateDefaultUserTargetNew = RunTimeUtil.generateDefaultUserTargetNew(j);
        Intrinsics.checkExpressionValueIsNotNull(userTargetNewGenerateDefaultUserTargetNew, "RunTimeUtil.generateDefaultUserTargetNew(userId)");
        return userTargetNewGenerateDefaultUserTargetNew;
    }

    public static final int getValidCalorieTarget(long j, String str) {
        int calorieTarget = getCalorieTarget(j, str);
        if (calorieTarget > 0) {
            return calorieTarget;
        }
        return 500;
    }

    public static final int getValidTimeTarget(long j, String str) {
        int timeTarget = getTimeTarget(j, str);
        if (timeTarget >= 60) {
            return timeTarget;
        }
        return 1800;
    }

    public static final int getValidWalkTarget(long j, String str) {
        int walkTarget = getWalkTarget(j, str);
        if (walkTarget >= 3600) {
            return walkTarget;
        }
        return 43200;
    }

    public static final int getCalorieTarget(long j, String str) {
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            UserTargetNew userTargetNewGenerateDefaultUserTargetNew = RunTimeUtil.generateDefaultUserTargetNew(j);
            Intrinsics.checkExpressionValueIsNotNull(userTargetNewGenerateDefaultUserTargetNew, "RunTimeUtil.generateDefaultUserTargetNew(userId)");
            return userTargetNewGenerateDefaultUserTargetNew.getCalories();
        }
        String today = getToday();
        if (today == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        if (today.contentEquals(str2)) {
            return getUserTarget(j, str).getCalories();
        }
        CalorieDayData calorieDayDataQueryCalorieDayByDate = GreenDaoUtil.queryCalorieDayByDate(j, str);
        return (calorieDayDataQueryCalorieDayByDate == null || calorieDayDataQueryCalorieDayByDate.getTargetCalorie() <= 0) ? getUserTarget(j, str).getCalories() : calorieDayDataQueryCalorieDayByDate.getTargetCalorie();
    }

    public static final int getTimeTarget(long j, String str) {
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            UserTargetNew userTargetNewGenerateDefaultUserTargetNew = RunTimeUtil.generateDefaultUserTargetNew(j);
            Intrinsics.checkExpressionValueIsNotNull(userTargetNewGenerateDefaultUserTargetNew, "RunTimeUtil.generateDefaultUserTargetNew(userId)");
            return userTargetNewGenerateDefaultUserTargetNew.getActivityTime();
        }
        String today = getToday();
        if (today == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        if (today.contentEquals(str2)) {
            return getUserTarget(j, str).getActivityTime();
        }
        ActiveTimeDayData activeTimeDayDataQueryActiveTimeDayByDate = GreenDaoUtil.queryActiveTimeDayByDate(j, str);
        return (activeTimeDayDataQueryActiveTimeDayByDate == null || !validActiveTimeData(activeTimeDayDataQueryActiveTimeDayByDate)) ? getUserTarget(j, str).getActivityTime() : activeTimeDayDataQueryActiveTimeDayByDate.getTargetExerciseDuration();
    }

    public static final int getWalkTarget(long j, String str) {
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            UserTargetNew userTargetNewGenerateDefaultUserTargetNew = RunTimeUtil.generateDefaultUserTargetNew(j);
            Intrinsics.checkExpressionValueIsNotNull(userTargetNewGenerateDefaultUserTargetNew, "RunTimeUtil.generateDefaultUserTargetNew(userId)");
            return userTargetNewGenerateDefaultUserTargetNew.getWalk();
        }
        String today = getToday();
        if (today == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        if (today.contentEquals(str2)) {
            int walk = getUserTarget(j, str).getWalk();
            if (walk > 0) {
                return walk;
            }
            return 43200;
        }
        WalkDayData walkDayDataQueryWalkDayByDate = GreenDaoUtil.queryWalkDayByDate(j, str);
        if (walkDayDataQueryWalkDayByDate != null && validWalkData(walkDayDataQueryWalkDayByDate)) {
            walkDayDataQueryWalkDayByDate.getTargetWalkDuration();
        }
        return getUserTarget(j, str).getWalk();
    }

    public static final String getToday() {
        String str = DateUtil.format(Calendar.getInstance(Locale.getDefault()), DateUtil.DATE_FORMAT_YMD);
        Intrinsics.checkExpressionValueIsNotNull(str, "DateUtil.format(calendar…DateUtil.DATE_FORMAT_YMD)");
        return str;
    }

    public static final List<CalorieDayData> getValidCalorieData(long j, String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return new ArrayList();
        }
        if (str == null) {
            Intrinsics.throwNpe();
        }
        if (str2 == null) {
            Intrinsics.throwNpe();
        }
        List<CalorieDayData> listQueryCalorieData = queryCalorieData(j, str, str2);
        List<CalorieDayData> list = listQueryCalorieData;
        if (list == null || list.isEmpty()) {
            return new ArrayList();
        }
        if (!z) {
            return listQueryCalorieData;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : listQueryCalorieData) {
            if (validCalorieData((CalorieDayData) obj)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final List<CalorieDayData> queryCalorieData(long j, String startDate, String endDate) {
        Intrinsics.checkParameterIsNotNull(startDate, "startDate");
        Intrinsics.checkParameterIsNotNull(endDate, "endDate");
        ArrayList arrayList = new ArrayList();
        Cursor cursorExecSql = GreenDaoUtil.execSql("\n    select DATE,ACTIVITY_CALORIE,TOTAL_CALORIE,TARGET_CALORIE from CALORIE_DAY_DATA where USER_ID=" + j + " and DATE>='" + startDate + "' and DATE<='" + endDate + "'\n", null);
        while (cursorExecSql.moveToNext()) {
            CalorieDayData calorieDayData = new CalorieDayData();
            calorieDayData.setUserId(j);
            calorieDayData.setDate(cursorExecSql.getString(0));
            calorieDayData.setActivityCalorie(cursorExecSql.getInt(1));
            calorieDayData.setTotalCalorie(cursorExecSql.getInt(2));
            calorieDayData.setTargetCalorie(cursorExecSql.getInt(3));
            arrayList.add(calorieDayData);
        }
        closeCursor(cursorExecSql);
        return arrayList;
    }

    public static final List<ActiveTimeDayData> getValidActiveTimeData(long j, String str, String str2) {
        String str3 = str;
        if (str3 == null || str3.length() == 0) {
            return null;
        }
        String str4 = str2;
        if (str4 == null || str4.length() == 0) {
            return null;
        }
        Cursor cursorExecSql = GreenDaoUtil.execSql("\n        select a.USER_ID,a.DATE,a.TOTAL_DISTANCE,a.MEDIUM_OR_HIGHER_SECONDS,a.TARGET_TOTAL_SECONDS,a.TOTAL_SECONDS,a.TARGET_EXERCISE_DURATION from ACTIVE_TIME_DAY_DATA a \n        left join CALORIE_DAY_DATA c on a.DATE=c.DATE and a.USER_ID=c.USER_ID left join SERVER_SLEEP_DAY_DATA s on c.DATE=s.DATE and c.USER_ID=s.USER_ID \n        where c.USER_ID=? and c.DATE>=? and c.DATE<=? and (\n        (a.WEAR_DURATION_LIST is not null and a.WEAR_DURATION_LIST not in ('','null') and a.TOTAL_WEAR_DURATION>0) or \n        (c.ACTIVITY_CALORIE>0 and (a.WEAR_DURATION_LIST is null or a.WEAR_DURATION_LIST in ('','null'))) \n        or s.DATE is not null) \n        group by a.USER_ID, a.DATE, a.TOTAL_DISTANCE, a.MEDIUM_OR_HIGHER_SECONDS, a.TARGET_TOTAL_SECONDS, a.TOTAL_SECONDS,a.TARGET_EXERCISE_DURATION order by a.DATE\n    ", new String[]{String.valueOf(j), str, str2});
        ArrayList arrayList = new ArrayList();
        if (cursorExecSql != null) {
            while (cursorExecSql.moveToNext()) {
                ActiveTimeDayData activeTimeDayData = new ActiveTimeDayData();
                activeTimeDayData.setUserId(cursorExecSql.getLong(0));
                activeTimeDayData.setDate(cursorExecSql.getString(1));
                activeTimeDayData.setTotalDistance(cursorExecSql.getInt(2));
                activeTimeDayData.setMediumOrHigherSeconds(cursorExecSql.getInt(3));
                activeTimeDayData.setTargetTotalSeconds(cursorExecSql.getInt(4));
                activeTimeDayData.setTotalSeconds(cursorExecSql.getInt(5));
                activeTimeDayData.setTargetExerciseDuration(cursorExecSql.getInt(6));
                arrayList.add(activeTimeDayData);
            }
            closeCursor(cursorExecSql);
        }
        return arrayList;
    }

    public static final List<WalkDayData> getValidWalkData(long j, String str, String str2) {
        String str3 = str;
        if (str3 == null || str3.length() == 0) {
            return null;
        }
        String str4 = str2;
        if (str4 == null || str4.length() == 0) {
            return null;
        }
        Cursor cursorExecSql = GreenDaoUtil.execSql("\n        select w.USER_ID,w.DATE,w.REACH_SECONDS,w.TARGET_STEPS,w.SEDENTARY_DURATION,w.TARGET_WALK_DURATION from WALK_DAY_DATA w left join ACTIVE_TIME_DAY_DATA a on w.DATE=a.DATE and w.USER_ID=a.USER_ID  \n        left join CALORIE_DAY_DATA c on c.DATE=a.DATE and a.USER_ID=c.USER_ID left join SERVER_SLEEP_DAY_DATA s on c.DATE=s.DATE and c.USER_ID=s.USER_ID \n        where w.USER_ID=? and w.DATE>=? and w.DATE<=? and (\n        (a.WEAR_DURATION_LIST is not null and a.WEAR_DURATION_LIST not in ('','null') and a.TOTAL_WEAR_DURATION>0) or \n        (c.ACTIVITY_CALORIE>0 and (a.WEAR_DURATION_LIST is null or a.WEAR_DURATION_LIST in ('','null'))) \n        or s.DATE is not null)\n        group by w.USER_ID, w.DATE, w.REACH_SECONDS, w.TARGET_STEPS, w.SEDENTARY_DURATION,w.TARGET_WALK_DURATION\n    ", new String[]{String.valueOf(j), str, str2});
        ArrayList arrayList = new ArrayList();
        if (cursorExecSql != null) {
            while (cursorExecSql.moveToNext()) {
                WalkDayData walkDayData = new WalkDayData();
                walkDayData.setUserId(cursorExecSql.getLong(0));
                walkDayData.setDate(cursorExecSql.getString(1));
                walkDayData.setReachSeconds(cursorExecSql.getInt(2));
                walkDayData.setTargetSteps(cursorExecSql.getInt(3));
                walkDayData.setSedentaryDuration(cursorExecSql.getInt(4));
                walkDayData.setTargetWalkDuration(cursorExecSql.getInt(5));
                arrayList.add(walkDayData);
            }
            closeCursor(cursorExecSql);
        }
        return arrayList;
    }

    public static final int getValidCalorieCount(long j, String str, String str2) {
        String str3 = str;
        int i = 0;
        if (!(str3 == null || str3.length() == 0)) {
            String str4 = str2;
            if (!(str4 == null || str4.length() == 0)) {
                Cursor cursorExecSql = GreenDaoUtil.execSql("\n        select count(_id) from (select c._id from CALORIE_DAY_DATA c left join ACTIVE_TIME_DAY_DATA a on c.DATE=a.DATE and c.USER_ID=a.USER_ID \n        left join SERVER_SLEEP_DAY_DATA s on a.DATE=s.DATE and a.USER_ID=s.USER_ID where c.USER_ID=? and c.DATE>=? and c.DATE<=? and \n        (\n        (a.WEAR_DURATION_LIST is not null and a.WEAR_DURATION_LIST not in ('','null') and a.TOTAL_WEAR_DURATION>0) \n        or (c.ACTIVITY_CALORIE>0 and (a.WEAR_DURATION_LIST is null or a.WEAR_DURATION_LIST in ('','null'))) \n        or s.DATE is not null) group by a.DATE, c.DATE, s.DATE)\n    ", new String[]{String.valueOf(j), str, str2});
                if (cursorExecSql != null && cursorExecSql.moveToNext()) {
                    i = cursorExecSql.getInt(0);
                }
                closeCursor(cursorExecSql);
            }
        }
        return i;
    }

    public static final int getValidActiveTimeCount(long j, String str, String str2) {
        String str3 = str;
        int i = 0;
        if (!(str3 == null || str3.length() == 0)) {
            String str4 = str2;
            if (!(str4 == null || str4.length() == 0)) {
                Cursor cursorExecSql = GreenDaoUtil.execSql("\n       select count(_id) from (select a.* from ACTIVE_TIME_DAY_DATA a left join CALORIE_DAY_DATA c on a.DATE=c.DATE and a.USER_ID=c.USER_ID \n       left join SERVER_SLEEP_DAY_DATA s on c.DATE=s.DATE and c.USER_ID=s.USER_ID where a.USER_ID=? and a.DATE>=? and a.DATE<=? and \n       (\n       (a.WEAR_DURATION_LIST is not null and a.WEAR_DURATION_LIST not in ('','null') and a.TOTAL_WEAR_DURATION>0) or \n       (c.ACTIVITY_CALORIE>0 and (a.WEAR_DURATION_LIST is null or a.WEAR_DURATION_LIST in ('','null'))) \n       or s.DATE is not null) \n        group by a.DATE, c.DATE, s.DATE) \n    ", new String[]{String.valueOf(j), str, str2});
                if (cursorExecSql != null && cursorExecSql.moveToNext()) {
                    i = cursorExecSql.getInt(0);
                }
                closeCursor(cursorExecSql);
            }
        }
        return i;
    }

    public static final int getValidWalkCount(long j, String str, String str2) {
        String str3 = str;
        int i = 0;
        if (!(str3 == null || str3.length() == 0)) {
            String str4 = str2;
            if (!(str4 == null || str4.length() == 0)) {
                Cursor cursorExecSql = GreenDaoUtil.execSql("\n            select count(_id) from (select w._id from WALK_DAY_DATA w left join ACTIVE_TIME_DAY_DATA a on w.DATE=a.DATE and w.USER_ID=a.USER_ID \n            left join CALORIE_DAY_DATA c on c.DATE=a.DATE and a.USER_ID=c.USER_ID left join SERVER_SLEEP_DAY_DATA s on c.DATE=s.DATE and c.USER_ID=s.USER_ID\n            where w.USER_ID=? and w.DATE>=? and w.DATE<=? and (\n            (a.WEAR_DURATION_LIST is not null and a.WEAR_DURATION_LIST not in ('','null') and a.TOTAL_WEAR_DURATION>0) or \n             (c.ACTIVITY_CALORIE>0 and (a.WEAR_DURATION_LIST is null or a.WEAR_DURATION_LIST in ('','null'))) \n             or s.DATE is not null)\n            group by a.DATE, w.DATE, c.DATE, s.DATE)\n    ", new String[]{String.valueOf(j), str, str2});
                if (cursorExecSql != null && cursorExecSql.moveToNext()) {
                    i = cursorExecSql.getInt(0);
                }
                closeCursor(cursorExecSql);
            }
        }
        return i;
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00d9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.util.Map<java.lang.String, com.ido.life.database.model.UserTargetNew> getTargetByDateArea(long r12, java.lang.String r14, java.lang.String r15) {
        /*
            Method dump skipped, instruction units count: 261
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.fitness.FitnessHelperKt.getTargetByDateArea(long, java.lang.String, java.lang.String):java.util.Map");
    }

    public static final Map<String, Boolean> getSleepDate(long j, String str, String str2) {
        Cursor cursorExecSql;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        String str3 = str;
        if (!(str3 == null || str3.length() == 0)) {
            String str4 = str2;
            if (!(str4 == null || str4.length() == 0) && (cursorExecSql = GreenDaoUtil.execSql("select DATE from SERVER_SLEEP_DAY_DATA where USER_ID=? and DATE>=? and DATE<=? order by DATE", new String[]{String.valueOf(j), str, str2})) != null) {
                while (cursorExecSql.moveToNext()) {
                    try {
                        String string = cursorExecSql.getString(0);
                        String str5 = string;
                        if (!(str5 == null || str5.length() == 0)) {
                            linkedHashMap.put(string, true);
                        }
                    } catch (Exception unused) {
                    }
                }
                closeCursor(cursorExecSql);
            }
        }
        return linkedHashMap;
    }

    public static final int calculateWalkDuration(long j, int i) {
        float weightKg = 60;
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(j);
        if (userInfoQueryUserInfo != null) {
            weightKg = userInfoQueryUserInfo.getWeightKg();
        }
        return MathKt.roundToInt(((i / weightKg) / 0.0175f) / 3.5f);
    }

    public static final List<RecentSituationDetailPresenter.RecommandActiceBean> calculateRecommendActiveList(long j, int i) {
        ArrayList arrayList = new ArrayList();
        List<RecentSituationDetailPresenter.RecommandActive> allRecommandActive = getAllRecommandActive(j);
        int size = allRecommandActive.size();
        String calorieUnit = RunTimeUtil.getCalorieUnit();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        TreeSet<Integer> treeSet = new TreeSet();
        for (int i2 = 0; i2 < size; i2++) {
            List<RecentSituationDetailPresenter.RecommandActiveDesc> activeDesc = allRecommandActive.get(i2).getActiveDesc();
            int size2 = activeDesc.size();
            for (int i3 = 0; i3 < size2; i3++) {
                RecentSituationDetailPresenter.RecommandActiveDesc recommandActiveDesc = activeDesc.get(i3);
                treeSet.add(Integer.valueOf(Math.abs(i - recommandActiveDesc.getKCal())));
                linkedHashMap.put(new Pair(Integer.valueOf(i2), Integer.valueOf(i3)), Integer.valueOf(Math.abs(i - recommandActiveDesc.getKCal())));
            }
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Integer num : treeSet) {
            if (arrayList.size() == 4) {
                break;
            }
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                int iIntValue = ((Number) entry.getValue()).intValue();
                if (num != null && iIntValue == num.intValue()) {
                    RecentSituationDetailPresenter.RecommandActive recommandActive = allRecommandActive.get(((Number) ((Pair) entry.getKey()).getFirst()).intValue());
                    Boolean bool = (Boolean) linkedHashMap2.get(Integer.valueOf(recommandActive.getType()));
                    if (bool == null || !Intrinsics.areEqual((Object) bool, (Object) true)) {
                        linkedHashMap2.put(Integer.valueOf(recommandActive.getType()), true);
                        RecentSituationDetailPresenter.RecommandActiveDesc recommandActiveDesc2 = recommandActive.getActiveDesc().get(((Number) ((Pair) entry.getKey()).getSecond()).intValue());
                        arrayList.add(new RecentSituationDetailPresenter.RecommandActiceBean(recommandActive.getType(), recommandActive.getActiveIcon(), recommandActive.getActiveName(), calorieUnit + IOUtils.DIR_SEPARATOR_UNIX + recommandActiveDesc2.getDurationDesc(), recommandActiveDesc2.getKCal()));
                        if (arrayList.size() == 4) {
                            break;
                        }
                    }
                }
            }
        }
        CollectionsKt.sortWith(arrayList, new Comparator<RecentSituationDetailPresenter.RecommandActiceBean>() { // from class: com.ido.life.module.home.fitness.FitnessHelperKt.calculateRecommendActiveList.1
            @Override // java.util.Comparator
            public final int compare(RecentSituationDetailPresenter.RecommandActiceBean recommandActiceBean, RecentSituationDetailPresenter.RecommandActiceBean recommandActiceBean2) {
                if (recommandActiceBean.getType() > recommandActiceBean2.getType()) {
                    return 1;
                }
                return recommandActiceBean.getType() < recommandActiceBean2.getType() ? -1 : 0;
            }
        });
        return arrayList;
    }

    public static final List<RecentSituationDetailPresenter.RecommandActive> getAllRecommandActive(long j) {
        float weight;
        ArrayList arrayList = new ArrayList();
        float f2 = 60;
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(j);
        float f3 = 0.02f;
        if (userInfoQueryUserInfo != null) {
            weight = userInfoQueryUserInfo.getWeight();
            int weightUnit = userInfoQueryUserInfo.getWeightUnit();
            if (weightUnit == 2) {
                weight = UnitUtil.getPound2Kg(weight);
            } else if (weightUnit == 3) {
                weight = UnitUtil.getSt2Kg(weight);
            }
            f = userInfoQueryUserInfo.getGender() != 1 ? 0.83f : 1.19f;
            if (userInfoQueryUserInfo.getGender() != 1) {
                f3 = 0.12f;
            }
        } else {
            weight = f2;
        }
        ArrayList arrayList2 = new ArrayList();
        float f4 = 3.0f * weight * 0.0175f * f;
        float f5 = f2 - f3;
        int iRoundToInt = MathKt.roundToInt(f4 * f5);
        String languageText = LanguageUtil.getLanguageText(R.string.hour_unit_short);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…R.string.hour_unit_short)");
        arrayList2.add(new RecentSituationDetailPresenter.RecommandActiveDesc(60, iRoundToInt, languageText));
        float f6 = 120 - f3;
        arrayList2.add(new RecentSituationDetailPresenter.RecommandActiveDesc(120, MathKt.roundToInt(f4 * f6), '2' + LanguageUtil.getLanguageText(R.string.hour_unit_short)));
        String languageText2 = LanguageUtil.getLanguageText(R.string.recommand_house_work);
        Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…ing.recommand_house_work)");
        arrayList.add(new RecentSituationDetailPresenter.RecommandActive(1, languageText2, R.mipmap.recommand_house_work, 3.0f, arrayList2));
        ArrayList arrayList3 = new ArrayList();
        float f7 = 4.3f * weight * 0.0175f * f;
        int iRoundToInt2 = MathKt.roundToInt(f7 * f5);
        String languageText3 = LanguageUtil.getLanguageText(R.string.hour_unit_short);
        Intrinsics.checkExpressionValueIsNotNull(languageText3, "LanguageUtil.getLanguage…R.string.hour_unit_short)");
        arrayList3.add(new RecentSituationDetailPresenter.RecommandActiveDesc(60, iRoundToInt2, languageText3));
        arrayList3.add(new RecentSituationDetailPresenter.RecommandActiveDesc(120, MathKt.roundToInt(f7 * f6), '2' + LanguageUtil.getLanguageText(R.string.hour_unit_short)));
        String languageText4 = LanguageUtil.getLanguageText(R.string.recommand_faster_work);
        Intrinsics.checkExpressionValueIsNotNull(languageText4, "LanguageUtil.getLanguage…ng.recommand_faster_work)");
        arrayList.add(new RecentSituationDetailPresenter.RecommandActive(2, languageText4, R.mipmap.recommand_faster_work, 4.3f, arrayList3));
        ArrayList arrayList4 = new ArrayList();
        float f8 = 5.6f * weight * 0.0175f * f;
        int iRoundToInt3 = MathKt.roundToInt(f8 * f5);
        String languageText5 = LanguageUtil.getLanguageText(R.string.hour_unit_short);
        Intrinsics.checkExpressionValueIsNotNull(languageText5, "LanguageUtil.getLanguage…R.string.hour_unit_short)");
        arrayList4.add(new RecentSituationDetailPresenter.RecommandActiveDesc(60, iRoundToInt3, languageText5));
        arrayList4.add(new RecentSituationDetailPresenter.RecommandActiveDesc(120, MathKt.roundToInt(f8 * f6), '2' + LanguageUtil.getLanguageText(R.string.hour_unit_short)));
        String languageText6 = LanguageUtil.getLanguageText(R.string.recommand_leisure_sport_work);
        Intrinsics.checkExpressionValueIsNotNull(languageText6, "LanguageUtil.getLanguage…mmand_leisure_sport_work)");
        arrayList.add(new RecentSituationDetailPresenter.RecommandActive(3, languageText6, R.mipmap.recommand_leisure_sport_work, 5.6f, arrayList4));
        ArrayList arrayList5 = new ArrayList();
        float f9 = 6.8f * weight * 0.0175f * f;
        float f10 = 45 - f3;
        arrayList5.add(new RecentSituationDetailPresenter.RecommandActiveDesc(45, MathKt.roundToInt(f9 * f10), "45" + LanguageUtil.getLanguageText(R.string.min_unit_short)));
        int iRoundToInt4 = MathKt.roundToInt(f9 * f5);
        String languageText7 = LanguageUtil.getLanguageText(R.string.hour_unit_short);
        Intrinsics.checkExpressionValueIsNotNull(languageText7, "LanguageUtil.getLanguage…R.string.hour_unit_short)");
        arrayList5.add(new RecentSituationDetailPresenter.RecommandActiveDesc(60, iRoundToInt4, languageText7));
        float f11 = 90 - f3;
        arrayList5.add(new RecentSituationDetailPresenter.RecommandActiveDesc(90, MathKt.roundToInt(f9 * f11), "1.5" + LanguageUtil.getLanguageText(R.string.hour_unit_short)));
        arrayList5.add(new RecentSituationDetailPresenter.RecommandActiveDesc(120, MathKt.roundToInt(f9 * f6), '2' + LanguageUtil.getLanguageText(R.string.hour_unit_short)));
        String languageText8 = LanguageUtil.getLanguageText(R.string.recommand_slow_ride_work);
        Intrinsics.checkExpressionValueIsNotNull(languageText8, "LanguageUtil.getLanguage…recommand_slow_ride_work)");
        arrayList.add(new RecentSituationDetailPresenter.RecommandActive(4, languageText8, R.mipmap.recommand_slow_ride_work, 6.8f, arrayList5));
        ArrayList arrayList6 = new ArrayList();
        float f12 = 8.0f * weight * 0.0175f * f;
        float f13 = 30 - f3;
        float f14 = f12 * f13;
        arrayList6.add(new RecentSituationDetailPresenter.RecommandActiveDesc(30, MathKt.roundToInt(f14), "30" + LanguageUtil.getLanguageText(R.string.min_unit_short)));
        float f15 = f12 * f10;
        arrayList6.add(new RecentSituationDetailPresenter.RecommandActiveDesc(45, MathKt.roundToInt(f15), "45" + LanguageUtil.getLanguageText(R.string.min_unit_short)));
        float f16 = f12 * f5;
        int iRoundToInt5 = MathKt.roundToInt(f16);
        String languageText9 = LanguageUtil.getLanguageText(R.string.hour_unit_short);
        Intrinsics.checkExpressionValueIsNotNull(languageText9, "LanguageUtil.getLanguage…R.string.hour_unit_short)");
        arrayList6.add(new RecentSituationDetailPresenter.RecommandActiveDesc(60, iRoundToInt5, languageText9));
        float f17 = f12 * f11;
        int iRoundToInt6 = MathKt.roundToInt(f17);
        StringBuilder sb = new StringBuilder();
        sb.append("1.5");
        float f18 = f;
        sb.append(LanguageUtil.getLanguageText(R.string.hour_unit_short));
        arrayList6.add(new RecentSituationDetailPresenter.RecommandActiveDesc(90, iRoundToInt6, sb.toString()));
        float f19 = f12 * f6;
        arrayList6.add(new RecentSituationDetailPresenter.RecommandActiveDesc(120, MathKt.roundToInt(f19), '2' + LanguageUtil.getLanguageText(R.string.hour_unit_short)));
        String languageText10 = LanguageUtil.getLanguageText(R.string.recommand_swim_work);
        Intrinsics.checkExpressionValueIsNotNull(languageText10, "LanguageUtil.getLanguage…ring.recommand_swim_work)");
        arrayList.add(new RecentSituationDetailPresenter.RecommandActive(5, languageText10, R.mipmap.recommand_swim_work, 8.0f, arrayList6));
        ArrayList arrayList7 = new ArrayList();
        arrayList7.add(new RecentSituationDetailPresenter.RecommandActiveDesc(30, MathKt.roundToInt(f14), "30" + LanguageUtil.getLanguageText(R.string.min_unit_short)));
        arrayList7.add(new RecentSituationDetailPresenter.RecommandActiveDesc(45, MathKt.roundToInt(f15), "45" + LanguageUtil.getLanguageText(R.string.min_unit_short)));
        int iRoundToInt7 = MathKt.roundToInt(f16);
        String languageText11 = LanguageUtil.getLanguageText(R.string.hour_unit_short);
        Intrinsics.checkExpressionValueIsNotNull(languageText11, "LanguageUtil.getLanguage…R.string.hour_unit_short)");
        arrayList7.add(new RecentSituationDetailPresenter.RecommandActiveDesc(60, iRoundToInt7, languageText11));
        arrayList7.add(new RecentSituationDetailPresenter.RecommandActiveDesc(90, MathKt.roundToInt(f17), "1.5" + LanguageUtil.getLanguageText(R.string.hour_unit_short)));
        arrayList7.add(new RecentSituationDetailPresenter.RecommandActiveDesc(120, MathKt.roundToInt(f19), '2' + LanguageUtil.getLanguageText(R.string.hour_unit_short)));
        String languageText12 = LanguageUtil.getLanguageText(R.string.recommand_run_work);
        Intrinsics.checkExpressionValueIsNotNull(languageText12, "LanguageUtil.getLanguage…tring.recommand_run_work)");
        arrayList.add(new RecentSituationDetailPresenter.RecommandActive(6, languageText12, R.mipmap.recommand_run_work, 8.0f, arrayList7));
        ArrayList arrayList8 = new ArrayList();
        float f20 = 10.0f * weight * 0.0175f * f18;
        arrayList8.add(new RecentSituationDetailPresenter.RecommandActiveDesc(30, MathKt.roundToInt(f20 * f13), "30" + LanguageUtil.getLanguageText(R.string.min_unit_short)));
        arrayList8.add(new RecentSituationDetailPresenter.RecommandActiveDesc(45, MathKt.roundToInt(f20 * f10), "45" + LanguageUtil.getLanguageText(R.string.min_unit_short)));
        int iRoundToInt8 = MathKt.roundToInt(f20 * f5);
        String languageText13 = LanguageUtil.getLanguageText(R.string.hour_unit_short);
        Intrinsics.checkExpressionValueIsNotNull(languageText13, "LanguageUtil.getLanguage…R.string.hour_unit_short)");
        arrayList8.add(new RecentSituationDetailPresenter.RecommandActiveDesc(60, iRoundToInt8, languageText13));
        arrayList8.add(new RecentSituationDetailPresenter.RecommandActiveDesc(90, MathKt.roundToInt(f20 * f11), "1.5" + LanguageUtil.getLanguageText(R.string.hour_unit_short)));
        arrayList8.add(new RecentSituationDetailPresenter.RecommandActiveDesc(120, MathKt.roundToInt(f20 * f6), '2' + LanguageUtil.getLanguageText(R.string.hour_unit_short)));
        String languageText14 = LanguageUtil.getLanguageText(R.string.recommand_ball_game_work);
        Intrinsics.checkExpressionValueIsNotNull(languageText14, "LanguageUtil.getLanguage…recommand_ball_game_work)");
        arrayList.add(new RecentSituationDetailPresenter.RecommandActive(7, languageText14, R.mipmap.recommand_ball_game_work, 10.0f, arrayList8));
        ArrayList arrayList9 = new ArrayList();
        float f21 = 11.0f * weight * 0.0175f * f18;
        arrayList9.add(new RecentSituationDetailPresenter.RecommandActiveDesc(30, MathKt.roundToInt(f21 * f13), "30" + LanguageUtil.getLanguageText(R.string.min_unit_short)));
        arrayList9.add(new RecentSituationDetailPresenter.RecommandActiveDesc(45, MathKt.roundToInt(f21 * f10), "45" + LanguageUtil.getLanguageText(R.string.min_unit_short)));
        int iRoundToInt9 = MathKt.roundToInt(f21 * f5);
        String languageText15 = LanguageUtil.getLanguageText(R.string.hour_unit_short);
        Intrinsics.checkExpressionValueIsNotNull(languageText15, "LanguageUtil.getLanguage…R.string.hour_unit_short)");
        arrayList9.add(new RecentSituationDetailPresenter.RecommandActiveDesc(60, iRoundToInt9, languageText15));
        arrayList9.add(new RecentSituationDetailPresenter.RecommandActiveDesc(90, MathKt.roundToInt(f21 * f11), "1.5" + LanguageUtil.getLanguageText(R.string.hour_unit_short)));
        arrayList9.add(new RecentSituationDetailPresenter.RecommandActiveDesc(120, MathKt.roundToInt(f21 * f6), '2' + LanguageUtil.getLanguageText(R.string.hour_unit_short)));
        String languageText16 = LanguageUtil.getLanguageText(R.string.recommand_fast_run_work);
        Intrinsics.checkExpressionValueIsNotNull(languageText16, "LanguageUtil.getLanguage….recommand_fast_run_work)");
        arrayList.add(new RecentSituationDetailPresenter.RecommandActive(8, languageText16, R.mipmap.recommand_fast_run_work, 11.0f, arrayList9));
        ArrayList arrayList10 = new ArrayList();
        float f22 = weight * 12.0f * 0.0175f * f18;
        arrayList10.add(new RecentSituationDetailPresenter.RecommandActiveDesc(30, MathKt.roundToInt(f22 * f13), "30" + LanguageUtil.getLanguageText(R.string.min_unit_short)));
        arrayList10.add(new RecentSituationDetailPresenter.RecommandActiveDesc(45, MathKt.roundToInt(f22 * f10), "45" + LanguageUtil.getLanguageText(R.string.min_unit_short)));
        int iRoundToInt10 = MathKt.roundToInt(f22 * f5);
        String languageText17 = LanguageUtil.getLanguageText(R.string.hour_unit_short);
        Intrinsics.checkExpressionValueIsNotNull(languageText17, "LanguageUtil.getLanguage…R.string.hour_unit_short)");
        arrayList10.add(new RecentSituationDetailPresenter.RecommandActiveDesc(60, iRoundToInt10, languageText17));
        arrayList10.add(new RecentSituationDetailPresenter.RecommandActiveDesc(90, MathKt.roundToInt(f22 * f11), "1.5" + LanguageUtil.getLanguageText(R.string.hour_unit_short)));
        arrayList10.add(new RecentSituationDetailPresenter.RecommandActiveDesc(120, MathKt.roundToInt(f22 * f6), '2' + LanguageUtil.getLanguageText(R.string.hour_unit_short)));
        String languageText18 = LanguageUtil.getLanguageText(R.string.recommand_fast_ride_work);
        Intrinsics.checkExpressionValueIsNotNull(languageText18, "LanguageUtil.getLanguage…recommand_fast_ride_work)");
        arrayList.add(new RecentSituationDetailPresenter.RecommandActive(9, languageText18, R.mipmap.recommand_fast_ride_work, 12.0f, arrayList10));
        return arrayList;
    }

    public static final List<RecentSituationDetailPresenter.RecommandActiceBean> getActiveTimeRecommandActiveList() {
        String languageText = LanguageUtil.getLanguageText(R.string.recommand_faster_work);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…ng.recommand_faster_work)");
        String languageText2 = LanguageUtil.getLanguageText(R.string.recommand_run_work);
        Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…tring.recommand_run_work)");
        String languageText3 = LanguageUtil.getLanguageText(R.string.sport_tab_ride);
        Intrinsics.checkExpressionValueIsNotNull(languageText3, "LanguageUtil.getLanguage…(R.string.sport_tab_ride)");
        String str = null;
        int i = 0;
        int i2 = 24;
        DefaultConstructorMarker defaultConstructorMarker = null;
        String languageText4 = LanguageUtil.getLanguageText(R.string.recommand_swim_work);
        Intrinsics.checkExpressionValueIsNotNull(languageText4, "LanguageUtil.getLanguage…ring.recommand_swim_work)");
        String languageText5 = LanguageUtil.getLanguageText(R.string.recommand_ball_game_work);
        Intrinsics.checkExpressionValueIsNotNull(languageText5, "LanguageUtil.getLanguage…recommand_ball_game_work)");
        return CollectionsKt.mutableListOf(new RecentSituationDetailPresenter.RecommandActiceBean(1, R.mipmap.recommand_faster_work, languageText, null, 0, 24, null), new RecentSituationDetailPresenter.RecommandActiceBean(2, R.mipmap.recommand_run_work, languageText2, null, 0, 24, null), new RecentSituationDetailPresenter.RecommandActiceBean(3, R.mipmap.recommand_fast_ride_work, languageText3, str, i, i2, defaultConstructorMarker), new RecentSituationDetailPresenter.RecommandActiceBean(4, R.mipmap.recommand_swim_work, languageText4, str, i, i2, defaultConstructorMarker), new RecentSituationDetailPresenter.RecommandActiceBean(5, R.mipmap.recommand_ball_game_work, languageText5, str, i, i2, defaultConstructorMarker));
    }

    public static final void closeCursor(Cursor cursor) {
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Exception unused) {
            }
        }
    }

    private static final void printAndSaveLog(String str) {
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            return;
        }
        LogPath logPathImpl = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
        CommonLogUtil.printAndSave(logPathImpl.getLogPath(), "FitnessHelper", str);
    }
}