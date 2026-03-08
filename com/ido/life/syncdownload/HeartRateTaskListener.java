package com.ido.life.syncdownload;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.life.database.model.DataPullConfigInfo;
import com.ido.life.database.model.ServerHeartRateDayData;
import com.ido.life.database.model.ServerHeartRateDayDataDao;
import com.ido.life.net.BaseUrl;
import com.ido.life.syncdownload.NewTask;
import com.ido.life.syncdownload.Task;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;

/* JADX INFO: compiled from: HeartRateTaskListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\u0000H\u0014J\b\u0010\f\u001a\u00020\u0003H\u0016J\"\u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100\u000f0\u000e2\u0006\u0010\u0011\u001a\u00020\u0006H\u0016J\u0010\u0010\u0012\u001a\n \u0013*\u0004\u0018\u00010\u00100\u0010H\u0016J\b\u0010\u0014\u001a\u00020\u0010H\u0016J\b\u0010\u0015\u001a\u00020\u0010H\u0016J\b\u0010\u0016\u001a\u00020\u0000H\u0016J\u001a\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u0010H\u0016J\u0018\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0019\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u001a\u0010!\u001a\u0004\u0018\u00010\u00102\u0006\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020 H\u0002R\u001a\u0010\u0005\u001a\u00020\u0006X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006$"}, d2 = {"Lcom/ido/life/syncdownload/HeartRateTaskListener;", "Lcom/ido/life/syncdownload/BaseHistoryTaskListener;", "userId", "", "(J)V", "SQL_INSERT_MAX_COUNT", "", "getSQL_INSERT_MAX_COUNT", "()I", "setSQL_INSERT_MAX_COUNT", "(I)V", "clone", "getDataSize", "getRequestParams", "", "", "", "threadCount", "getTaskTag", "kotlin.jvm.PlatformType", "getTaskType", "getTaskUrlPath", "newInstance", "processData", "", "taskInfo", "Lcom/ido/life/syncdownload/Task$TaskInfo;", FirebaseAnalytics.Param.CONTENT, "resolveContent", "", "Lcom/ido/life/syncdownload/NewTask$NewTaskInfo;", "jsonArray", "Lorg/json/JSONArray;", "resolveHeartRate", "taskId", "contentArray", "app_release"}, k = 1, mv = {1, 1, 16})
public final class HeartRateTaskListener extends BaseHistoryTaskListener {
    private int SQL_INSERT_MAX_COUNT;

    @Override // com.ido.life.syncdownload.BaseHistoryTaskListener
    public long getDataSize() {
        return 2829L;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskType() {
        return BaseUrl.URL_NAME_HEALTH;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskUrlPath() {
        return "api/heartrate/v2/300/sync/data";
    }

    public HeartRateTaskListener(long j) {
        super(j, 0, 2, null);
        this.SQL_INSERT_MAX_COUNT = 10;
    }

    @Override // com.ido.life.syncdownload.BaseTaskListener
    public HeartRateTaskListener clone() {
        return new HeartRateTaskListener(getUserId());
    }

    @Override // com.ido.life.syncdownload.BaseHistoryTaskListener
    public List<Map<String, String>> getRequestParams(int threadCount) {
        int iMax = Math.max(getMaxDownloadDataCount(threadCount), 30);
        DataPullConfigInfo dataPullConfigInfoQueryDataPullConfigInfo = GreenDaoUtil.queryDataPullConfigInfo(getUserId());
        if (dataPullConfigInfoQueryDataPullConfigInfo == null) {
            return getDefaultParams();
        }
        List<Map<String, String>> listCaluteRequestDate = caluteRequestDate(iMax / 30, dataPullConfigInfoQueryDataPullConfigInfo.getHeartRateStartTime(), dataPullConfigInfoQueryDataPullConfigInfo.getHeartRateEndTime(), DateUtil.DATE_FORMAT_YMD);
        List<Map<String, String>> list = listCaluteRequestDate;
        return list == null || list.isEmpty() ? getDefaultParams() : listCaluteRequestDate;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadProgressTaskListener
    public int getSQL_INSERT_MAX_COUNT() {
        return this.SQL_INSERT_MAX_COUNT;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadProgressTaskListener
    public void setSQL_INSERT_MAX_COUNT(int i) {
        this.SQL_INSERT_MAX_COUNT = i;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskTag() {
        return ServerHeartRateDayData.class.getSimpleName();
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public HeartRateTaskListener newInstance() {
        return new HeartRateTaskListener(getUserId());
    }

    @Override // com.ido.life.syncdownload.BaseHistoryTaskListener
    public boolean processData(Task.TaskInfo taskInfo, String content) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("心率数据下拉成功 taskInfo=");
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
            printAndSaveLog("心率数据下拉转JSON失败taskInfo=" + taskInfo + ",error=" + e2.getLocalizedMessage());
            return true;
        }
    }

    private final void resolveContent(NewTask.NewTaskInfo taskInfo, JSONArray jsonArray) {
        try {
            int length = jsonArray.length();
            if (length > 0) {
                taskInfo.getBuilder().setDataTotalCount(0);
                String str = "";
                int i = 0;
                for (int i2 = 0; i2 < length; i2++) {
                    if (getMHasStop()) {
                        return;
                    }
                    JSONArray jSONArray = jsonArray.getJSONArray(i2);
                    if (jSONArray != null && jSONArray.length() != 0) {
                        String strResolveHeartRate = resolveHeartRate(taskInfo.getTaskId(), jSONArray);
                        String str2 = strResolveHeartRate;
                        if (!(str2 == null || str2.length() == 0)) {
                            str = str + strResolveHeartRate + ',';
                            NewTask.Builder builder = taskInfo.getBuilder();
                            builder.setDataTotalCount(builder.getDataTotalCount() + 1);
                            i++;
                        }
                        if (i >= getSQL_INSERT_MAX_COUNT() || i2 == length - 1) {
                            if (str.length() > 0) {
                                GreenDaoUtil.execSql("\n                                insert into SERVER_HEART_RATE_DAY_DATA(\n                                DATE,\n                                WARM_UP_SECONDS,\n                                WARM_UP_THRESHOLD,\n                                BURN_FAT_SECONDS,\n                                BURN_FAT_THRESHOLD,\n                                AEROBIC_SECONDS,\n                                AEROBIC_THRESHOLD,\n                                ANAEROBIC_SECONDS,\n                                ANAEROBIC_THRESHOLD,\n                                LIMIT_SECONDS,\n                                LIMIT_THRESHOLD,\n                                MIN_VALUE,\n                                MAX_VALUE,\n                                AVG_VALUE,\n                                SILENT_VALUE,\n                                LATEST_VALUE,\n                                START_TIME_VALUE,\n                                HEART_MONITOR_TYPE,\n                                DEVICE_NAME,\n                                SOURCE_MAC,\n                                TIMESTAMP,\n                                ITEMS,\n                                CHART_ITEMS,\n                                UPLOAD_SUCCESS,\n                                USER_ID,\n                                LOAD_DETAIL\n                                ) VALUES " + StringsKt.dropLast(str, 1) + "\n                            ");
                                str = "";
                                i = 0;
                            }
                        }
                    }
                }
                setProgress(taskInfo.getBuilder().getDataTotalCount());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private final String resolveHeartRate(int taskId, JSONArray contentArray) {
        if (contentArray.length() < 22) {
            return null;
        }
        try {
            long j = contentArray.getLong(20);
            String string = contentArray.getString(0);
            String str = string;
            if (!(str == null || str.length() == 0) && j > 0) {
                String simpleName = HeartRateLastestTaskListener.class.getSimpleName();
                Intrinsics.checkExpressionValueIsNotNull(simpleName, "HeartRateLastestTaskList…er::class.java.simpleName");
                if (skipDate(taskId, string, simpleName)) {
                    return null;
                }
                ServerHeartRateDayData dayHeartRateByDate = GreenDaoUtil.getDayHeartRateByDate(getUserId(), string);
                if (dayHeartRateByDate != null) {
                    if (dayHeartRateByDate.getTimestamp() >= j) {
                        printAndSaveLog("userId=" + getUserId() + ",date=" + string + ",beforeHeartRate.timestamp=" + dayHeartRateByDate.getTimestamp() + ",timeStamp=" + j);
                        return null;
                    }
                    try {
                        dayHeartRateByDate.delete();
                    } catch (Exception unused) {
                        GreenDaoUtil.deleteDataByKey(ServerHeartRateDayDataDao.TABLENAME, "DATE", string);
                    }
                }
                String string2 = contentArray.getString(21);
                String str2 = string2;
                if (str2 == null || str2.length() == 0) {
                    string2 = "";
                }
                return "\n            ('" + string + "'," + contentArray.getInt(1) + ',' + contentArray.getInt(2) + ',' + contentArray.getInt(3) + ",\n             " + contentArray.getInt(4) + ',' + contentArray.getInt(5) + ',' + contentArray.getInt(6) + ",\n             " + contentArray.getInt(7) + ',' + contentArray.getInt(8) + ',' + contentArray.getInt(9) + ",\n             " + contentArray.getInt(10) + ',' + contentArray.getInt(11) + ',' + contentArray.getInt(12) + ",\n             " + contentArray.getInt(13) + ',' + contentArray.getInt(14) + ',' + contentArray.getInt(15) + ",\n             " + contentArray.getInt(16) + ',' + contentArray.getInt(17) + ",'" + contentArray.getString(18) + "',\n             '" + contentArray.getString(19) + "'," + j + ",'','" + string2 + "',1," + getUserId() + ",1)\n                ";
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }
}