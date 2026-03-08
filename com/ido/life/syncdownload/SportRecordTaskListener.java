package com.ido.life.syncdownload;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.life.database.model.DataPullConfigInfo;
import com.ido.life.database.model.SportHealth;
import com.ido.life.net.BaseUrl;
import com.ido.life.syncdownload.NewTask;
import com.ido.life.syncdownload.Task;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.json.JSONArray;

/* JADX INFO: compiled from: SportRecordTaskListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0000H\u0014J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u001a\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t0\bH\u0016J\"\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\n \u000f*\u0004\u0018\u00010\n0\nH\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016J\b\u0010\u0011\u001a\u00020\nH\u0016J\b\u0010\u0012\u001a\u00020\u0000H\u0016J\u001a\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\nH\u0016J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u001c\u0010\u001d\u001a\u0004\u0018\u00010\n2\u0006\u0010\u001e\u001a\u00020\r2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001cH\u0002¨\u0006 "}, d2 = {"Lcom/ido/life/syncdownload/SportRecordTaskListener;", "Lcom/ido/life/syncdownload/BaseHistoryTaskListener;", "userId", "", "(J)V", "clone", "getDataSize", "getDefaultParams", "", "", "", "getRequestParams", "threadCount", "", "getTaskTag", "kotlin.jvm.PlatformType", "getTaskType", "getTaskUrlPath", "newInstance", "processData", "", "taskInfo", "Lcom/ido/life/syncdownload/Task$TaskInfo;", FirebaseAnalytics.Param.CONTENT, "resolveContent", "", "Lcom/ido/life/syncdownload/NewTask$NewTaskInfo;", "jsonArray", "Lorg/json/JSONArray;", "resolveSportRecord", "taskId", "contentArray", "app_release"}, k = 1, mv = {1, 1, 16})
public final class SportRecordTaskListener extends BaseHistoryTaskListener {
    @Override // com.ido.life.syncdownload.BaseHistoryTaskListener
    public long getDataSize() {
        return 1126L;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskType() {
        return BaseUrl.URL_NAME_HEALTH;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskUrlPath() {
        return "api/sports/sync/data";
    }

    public SportRecordTaskListener(long j) {
        super(j, 0, 2, null);
    }

    @Override // com.ido.life.syncdownload.BaseTaskListener
    public SportRecordTaskListener clone() {
        return new SportRecordTaskListener(getUserId());
    }

    @Override // com.ido.life.syncdownload.BaseHistoryTaskListener
    public List<Map<String, String>> getRequestParams(int threadCount) {
        int iMax = Math.max(getMaxDownloadDataCount(threadCount), 30);
        DataPullConfigInfo dataPullConfigInfoQueryDataPullConfigInfo = GreenDaoUtil.queryDataPullConfigInfo(getUserId());
        if (dataPullConfigInfoQueryDataPullConfigInfo == null) {
            return getDefaultParams();
        }
        List<Map<String, String>> listCaluteRequestDate = caluteRequestDate(iMax / 30, dataPullConfigInfoQueryDataPullConfigInfo.getSportStartTime(), dataPullConfigInfoQueryDataPullConfigInfo.getSportEndTime(), "yyyy-MM-dd HH:mm:ss");
        List<Map<String, String>> list = listCaluteRequestDate;
        return list == null || list.isEmpty() ? getDefaultParams() : listCaluteRequestDate;
    }

    @Override // com.ido.life.syncdownload.BaseHistoryTaskListener
    public List<Map<String, String>> getDefaultParams() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        String str = DateUtil.format(calendar, "yyyy-MM-dd HH:mm:ss");
        calendar.add(1, -100);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        return CollectionsKt.mutableListOf(MapsKt.mutableMapOf(new Pair("start", DateUtil.format(calendar, "yyyy-MM-dd HH:mm:ss")), new Pair("end", str)));
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskTag() {
        return SportHealth.class.getSimpleName();
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public SportRecordTaskListener newInstance() {
        return new SportRecordTaskListener(getUserId());
    }

    @Override // com.ido.life.syncdownload.BaseHistoryTaskListener
    public boolean processData(Task.TaskInfo taskInfo, String content) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("运动记录数据下拉成功 taskInfo=");
            sb.append(taskInfo);
            sb.append(",content=");
            sb.append(content.length() > 200 ? content.subSequence(0, 200) : content);
            printAndSaveLog(sb.toString());
            if (!(taskInfo instanceof NewTask.NewTaskInfo)) {
                return true;
            }
            resolveContent((NewTask.NewTaskInfo) taskInfo, new JSONArray(content));
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            printAndSaveLog("运动记录数据下拉转JSON失败 taskInfo=" + taskInfo + ",error=" + e2.getLocalizedMessage());
            return true;
        }
    }

    private final void resolveContent(NewTask.NewTaskInfo taskInfo, JSONArray jsonArray) {
        try {
            int length = jsonArray.length();
            if (length > 0) {
                taskInfo.getBuilder().setDataTotalCount(0);
                IntProgression intProgressionStep = RangesKt.step(RangesKt.until(1, length), 2);
                int first = intProgressionStep.getFirst();
                int last = intProgressionStep.getLast();
                int step = intProgressionStep.getStep();
                if (step < 0 ? first >= last : first <= last) {
                    int length2 = 0;
                    String str = "";
                    while (!getMHasStop()) {
                        JSONArray jSONArray = jsonArray.getJSONArray(first);
                        String strResolveSportRecord = resolveSportRecord(taskInfo.getTaskId(), jSONArray);
                        String str2 = strResolveSportRecord;
                        if (!(str2 == null || str2.length() == 0)) {
                            str = str + strResolveSportRecord + ',';
                            NewTask.Builder builder = taskInfo.getBuilder();
                            builder.setDataTotalCount(builder.getDataTotalCount() + 1);
                            length2 += jSONArray.length();
                        }
                        if (length2 >= getSQL_INSERT_MAX_COUNT() || first == length - 1) {
                            if (str.length() > 0) {
                                GreenDaoUtil.execSql("\n                                insert into SPORT_HEALTH(\n                                SID,\n                                DATE_TIME,\n                                TYPE,\n                                SUB_TYPE,\n                                TOTAL_SECONDS,\n                                NUM_CALORIES,\n                                NUM_STEPS,\n                                DISTANCE,\n                                START_TIME,\n                                END_TIME,\n                                TARGET_TYPE,\n                                TARGET_VALUE,\n                                WARMUP_SECONDS,\n                                BURN_FAT_SECONDS,\n                                AEROBIC_SECONDS,\n                                ANAEROBIC_SECOND,\n                                EXTREME_SECOND,\n                                SOURCE_MAC,\n                                DEVICE_NAME,\n                                SOURCE_TYPE,\n                                MIN_HR_VALUE,\n                                MAX_HR_VALUE,\n                                AVG_HR_VALUE,\n                                MIN_SPEED,\n                                MAX_SPEED,\n                                AVG_SPEED,\n                                MIN_PACE,\n                                MAX_PACE,\n                                AVG_PACE,\n                                IS_LOCUS,\n                                GPS_SOURCE_TYPE,\n                                STEP_RANGE,\n                                MIN_RATE,\n                                STEP_RATE_MAX,\n                                STEP_RATE,\n                                SWIMMING_POSTURE,\n                                TOTAL_STROKES_NUMBER,\n                                POOL_DISTANCE,\n                                TRIPS,\n                                BEST_SWOLF,\n                                MAX_SWOLF,\n                                AVERAGE_SWOLF,\n                                ICON,\n                                TIMESTAMP,\n                                IS_UPLOADED_STRAVA,\n                                UPLOADED_STRAVA,\n                                CUMULATIVE_CLIMB,\n                                CUMULATIVE_DECLINE,\n                                TRAINING_EFFECT_SCORE,\n                                VO2MAX,\n                                IS_SUPPORT_TRAINING_EFFECT,\n                                DISCOVER_DATE_TIME,\n                                RECOVER_TIME,\n                                SOURCE_OS,\n                                IS_UPLOADED,\n                                USER_ID,\n                                STEP_RANGE_MAX,\n                                HR_DATA_INTERVAL_MINUTE,\n                                FAST_KM_SPEED,\n                                AVG_FREQUENCY,\n                                MAX_FREQUENCY,\n                                INTERVAL_SECOND,\n                                IS_LOAD_DETAIL\n                                ) VALUES " + StringsKt.dropLast(str, 1) + "\n                            ");
                                length2 = 0;
                                str = "";
                            }
                        }
                        if (first != last) {
                            first += step;
                        }
                    }
                    return;
                }
                setProgress(taskInfo.getBuilder().getDataTotalCount());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:26|27|77|28|29|(2:69|30)|(6:32|33|(2:75|36)|50|56|80)|39|40|71|41|42|81) */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x032d, code lost:
    
        r0 = e;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.lang.String resolveSportRecord(int r29, org.json.JSONArray r30) {
        /*
            Method dump skipped, instruction units count: 892
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.syncdownload.SportRecordTaskListener.resolveSportRecord(int, org.json.JSONArray):java.lang.String");
    }
}