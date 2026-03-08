package com.ido.life.syncdownload;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.life.database.model.ActiveTimeDayData;
import com.ido.life.database.model.ActiveTimeDayDataDao;
import com.ido.life.database.model.DataPullConfigInfo;
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

/* JADX INFO: compiled from: ActiveTimeTaskListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0000H\u0014J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\"\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\n \u000e*\u0004\u0018\u00010\n0\nH\u0016J\b\u0010\u000f\u001a\u00020\nH\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016J\b\u0010\u0011\u001a\u00020\u0000H\u0016J\u001a\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\nH\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0014\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u001a\u0010\u0017\u001a\u0004\u0018\u00010\n2\u0006\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u001bH\u0002¨\u0006\u001e"}, d2 = {"Lcom/ido/life/syncdownload/ActiveTimeTaskListener;", "Lcom/ido/life/syncdownload/BaseHistoryTaskListener;", "userId", "", "(J)V", "clone", "getDataSize", "getRequestParams", "", "", "", "threadCount", "", "getTaskTag", "kotlin.jvm.PlatformType", "getTaskType", "getTaskUrlPath", "newInstance", "processData", "", "taskInfo", "Lcom/ido/life/syncdownload/Task$TaskInfo;", FirebaseAnalytics.Param.CONTENT, "resolveActiveTime", "", "Lcom/ido/life/syncdownload/NewTask$NewTaskInfo;", "jsonObject", "Lorg/json/JSONArray;", "taskId", "contentArray", "app_release"}, k = 1, mv = {1, 1, 16})
public final class ActiveTimeTaskListener extends BaseHistoryTaskListener {
    @Override // com.ido.life.syncdownload.BaseHistoryTaskListener
    public long getDataSize() {
        return 433L;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskType() {
        return BaseUrl.URL_NAME_HEALTH;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskUrlPath() {
        return "api/exercise/sync/data";
    }

    public ActiveTimeTaskListener(long j) {
        super(j, 0, 2, null);
    }

    @Override // com.ido.life.syncdownload.BaseTaskListener
    public ActiveTimeTaskListener clone() {
        return new ActiveTimeTaskListener(getUserId());
    }

    @Override // com.ido.life.syncdownload.BaseHistoryTaskListener
    public List<Map<String, String>> getRequestParams(int threadCount) {
        int iMax = Math.max(getMaxDownloadDataCount(threadCount), 30);
        DataPullConfigInfo dataPullConfigInfoQueryDataPullConfigInfo = GreenDaoUtil.queryDataPullConfigInfo(getUserId());
        if (dataPullConfigInfoQueryDataPullConfigInfo == null) {
            return getDefaultParams();
        }
        List<Map<String, String>> listCaluteRequestDate = caluteRequestDate(iMax / 30, dataPullConfigInfoQueryDataPullConfigInfo.getExerciseStartTime(), dataPullConfigInfoQueryDataPullConfigInfo.getExerciseEndTime(), DateUtil.DATE_FORMAT_YMD);
        return listCaluteRequestDate != null ? listCaluteRequestDate : getDefaultParams();
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskTag() {
        return ActiveTimeDayData.class.getSimpleName();
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public ActiveTimeTaskListener newInstance() {
        return new ActiveTimeTaskListener(getUserId());
    }

    @Override // com.ido.life.syncdownload.BaseHistoryTaskListener
    public boolean processData(Task.TaskInfo taskInfo, String content) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("中高强度数据下拉成功 taskInfo=");
            sb.append(taskInfo);
            sb.append(",content=");
            sb.append(content.length() > 200 ? content.subSequence(0, 200) : content);
            printAndSaveLog(sb.toString());
            JSONArray jSONArray = new JSONArray(content);
            if (!(taskInfo instanceof NewTask.NewTaskInfo)) {
                return true;
            }
            resolveActiveTime((NewTask.NewTaskInfo) taskInfo, jSONArray);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            printAndSaveLog("中高强度数据下拉转JSON失败 taskInfo=" + taskInfo + ",error=" + e2.getLocalizedMessage());
            return true;
        }
    }

    private final void resolveActiveTime(NewTask.NewTaskInfo taskInfo, JSONArray jsonObject) {
        try {
            int length = jsonObject.length();
            if (length > 0) {
                taskInfo.getBuilder().setDataTotalCount(0);
                String str = "";
                int i = 0;
                for (int i2 = 0; i2 < length; i2++) {
                    if (getMHasStop()) {
                        return;
                    }
                    JSONArray jSONArray = jsonObject.getJSONArray(i2);
                    if (jSONArray != null && jSONArray.length() != 0) {
                        String strResolveActiveTime = resolveActiveTime(taskInfo.getTaskId(), jSONArray);
                        String str2 = strResolveActiveTime;
                        if (!(str2 == null || str2.length() == 0)) {
                            str = str + strResolveActiveTime + ',';
                            NewTask.Builder builder = taskInfo.getBuilder();
                            builder.setDataTotalCount(builder.getDataTotalCount() + 1);
                            i++;
                        }
                        if (i >= getSQL_INSERT_MAX_COUNT() || i2 == length - 1) {
                            if (str.length() > 0) {
                                GreenDaoUtil.execSql("\n                                insert into ACTIVE_TIME_DAY_DATA(\n                                DATE,\n                                SOURCE_MAC,\n                                DEVICE_NAME,\n                                TOTAL_SECONDS,\n                                TOTAL_DISTANCE,\n                                MEDIUM_OR_HIGHER_SECONDS,\n                                TARGET_TOTAL_SECONDS,\n                                TIMESTAMP,\n                                ITEMS,\n                                UPLOADED,\n                                USER_ID,\n                                LOAD_DETAIL,\n                                WEAR_DURATION_LIST,\n                                TOTAL_WEAR_DURATION,\n                                TARGET_EXERCISE_DURATION\n                                ) VALUES " + StringsKt.dropLast(str, 1) + "\n                            ");
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

    private final String resolveActiveTime(int taskId, JSONArray contentArray) {
        if (contentArray.length() < 12) {
            return null;
        }
        try {
            long j = contentArray.getLong(7);
            String string = contentArray.getString(0);
            String str = string;
            if (!(str == null || str.length() == 0) && j > 0) {
                String simpleName = ActiveTimeLastestTaskListener.class.getSimpleName();
                Intrinsics.checkExpressionValueIsNotNull(simpleName, "ActiveTimeLastestTaskLis…er::class.java.simpleName");
                if (skipDate(taskId, string, simpleName)) {
                    return null;
                }
                ActiveTimeDayData activeTimeDayDataQueryActiveTimeDayByDate = GreenDaoUtil.queryActiveTimeDayByDate(getUserId(), string);
                if (activeTimeDayDataQueryActiveTimeDayByDate != null) {
                    if (activeTimeDayDataQueryActiveTimeDayByDate.getTimestamp() >= j) {
                        return null;
                    }
                    try {
                        activeTimeDayDataQueryActiveTimeDayByDate.delete();
                    } catch (Exception unused) {
                        GreenDaoUtil.deleteDataByKey(ActiveTimeDayDataDao.TABLENAME, "DATE", string);
                    }
                }
                String string2 = contentArray.getString(8);
                String str2 = string2;
                if (str2 == null || str2.length() == 0) {
                    string2 = "";
                }
                return "\n            ('" + string + "','" + contentArray.getString(1) + "','" + contentArray.getString(2) + "'," + contentArray.getInt(3) + ",\n                " + contentArray.getInt(4) + ',' + contentArray.getInt(5) + ',' + contentArray.getInt(6) + ",\n                " + j + ",'" + string2 + "',1," + getUserId() + ",1,'" + contentArray.getString(9) + "'," + contentArray.getInt(10) + "\n                ," + contentArray.getInt(11) + ")\n                ";
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }
}