package com.ido.life.syncdownload;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.life.database.model.DataDownLoadState;
import com.ido.life.database.model.DataPullConfigInfo;
import com.ido.life.database.model.HealthVolumeData;
import com.ido.life.database.model.HealthVolumeDataDao;
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

/* JADX INFO: compiled from: AmbientVolumeTaskListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0000H\u0014J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\"\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000b0\n2\u0006\u0010\r\u001a\u00020\u000eH\u0016JL\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00032\u0014\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f\u0018\u00010\u00142\u0014\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f\u0018\u00010\u00142\u0006\u0010\u0016\u001a\u00020\u000eH\u0016J\u0010\u0010\u0017\u001a\n \u0018*\u0004\u0018\u00010\f0\fH\u0016J\b\u0010\u0019\u001a\u00020\fH\u0016J\b\u0010\u001a\u001a\u00020\fH\u0016J\b\u0010\u001b\u001a\u00020\u0000H\u0016J\u0012\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u001a\u0010 \u001a\u00020!2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010\"\u001a\u00020\fH\u0016J\u001a\u0010#\u001a\u0004\u0018\u00010\f2\u0006\u0010$\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020&H\u0002J\u0018\u0010'\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020(2\u0006\u0010)\u001a\u00020&H\u0002¨\u0006*"}, d2 = {"Lcom/ido/life/syncdownload/AmbientVolumeTaskListener;", "Lcom/ido/life/syncdownload/BaseHistoryTaskListener;", "userId", "", "(J)V", "clone", "getDataSize", "getDefaultTaskDownloadState", "Lcom/ido/life/database/model/DataDownLoadState;", "getRequestParams", "", "", "", "threadCount", "", "getTask", "Lcom/ido/life/syncdownload/NewTask;", "groupId", "stateId", "params", "", "headerMap", "taskPriority", "getTaskTag", "kotlin.jvm.PlatformType", "getTaskType", "getTaskUrlPath", "newInstance", "onSingleTaskFailed", "", "taskInfo", "Lcom/ido/life/syncdownload/Task$TaskInfo;", "processData", "", FirebaseAnalytics.Param.CONTENT, "resolveAmbientVolume", "taskId", "contentArray", "Lorg/json/JSONArray;", "resolveContent", "Lcom/ido/life/syncdownload/NewTask$NewTaskInfo;", "jsonArray", "app_release"}, k = 1, mv = {1, 1, 16})
public final class AmbientVolumeTaskListener extends BaseHistoryTaskListener {
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
        return "api/noise/sync/data";
    }

    public AmbientVolumeTaskListener(long j) {
        super(j, 0, 2, null);
    }

    @Override // com.ido.life.syncdownload.BaseTaskListener
    public AmbientVolumeTaskListener clone() {
        return new AmbientVolumeTaskListener(getUserId());
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public NewTask getTask(int groupId, long stateId, Map<String, String> params, Map<String, String> headerMap, int taskPriority) {
        printAndSaveLog("getTask");
        return super.getTask(groupId, stateId, params, headerMap, taskPriority);
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public DataDownLoadState getDefaultTaskDownloadState() {
        printAndSaveLog("getDefaultTaskDownloadState");
        return super.getDefaultTaskDownloadState();
    }

    @Override // com.ido.life.syncdownload.BaseHistoryTaskListener
    public List<Map<String, String>> getRequestParams(int threadCount) {
        printAndSaveLog("getRequestParams");
        int iMax = Math.max(getMaxDownloadDataCount(threadCount), 30);
        DataPullConfigInfo dataPullConfigInfoQueryDataPullConfigInfo = GreenDaoUtil.queryDataPullConfigInfo(getUserId());
        if (dataPullConfigInfoQueryDataPullConfigInfo == null) {
            return getDefaultParams();
        }
        List<Map<String, String>> listCaluteRequestDate = caluteRequestDate(iMax / 30, dataPullConfigInfoQueryDataPullConfigInfo.getNoiseStartTime(), dataPullConfigInfoQueryDataPullConfigInfo.getNoiseEndTime(), DateUtil.DATE_FORMAT_YMD);
        List<Map<String, String>> list = listCaluteRequestDate;
        return list == null || list.isEmpty() ? getDefaultParams() : listCaluteRequestDate;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskTag() {
        return HealthVolumeData.class.getSimpleName();
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public AmbientVolumeTaskListener newInstance() {
        return new AmbientVolumeTaskListener(getUserId());
    }

    @Override // com.ido.life.syncdownload.BaseHistoryTaskListener
    public boolean processData(Task.TaskInfo taskInfo, String content) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("环境音量数据下拉成功 taskInfo=");
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
            printAndSaveLog("环境音量数据下拉转JSON失败 taskInfo=" + taskInfo + ",error=" + e2.getLocalizedMessage());
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
                        String strResolveAmbientVolume = resolveAmbientVolume(taskInfo.getTaskId(), jSONArray);
                        String str2 = strResolveAmbientVolume;
                        if (!(str2 == null || str2.length() == 0)) {
                            str = str + strResolveAmbientVolume + ',';
                            NewTask.Builder builder = taskInfo.getBuilder();
                            builder.setDataTotalCount(builder.getDataTotalCount() + 1);
                            i++;
                        }
                        if (i >= getSQL_INSERT_MAX_COUNT() || i2 == length - 1) {
                            if (str.length() > 0) {
                                GreenDaoUtil.execSql("\n                                insert into HEALTH_VOLUME_DATA(\n                                DATE,\n                                MAX_VALUE,\n                                MIN_VALUE,\n                                AVG_VALUE,\n                                LATEST_VALUE,\n                                SOURCE_MAC,\n                                TIMESTAMP,\n                                DEVICE_NAME,\n                                ITEMS,\n                                INTERVAL,\n                                TOTAL_SECONDS,\n                                SUPER_HIGH_LEVEL_SECONDS,\n                                HIGH_LEVEL_SECONDS,\n                                NORMAL_LEVEL_SECONDS,\n                                LOW_LEVEL_SECONDS,\n                                HAS_UPDATE,\n                                USER_ID\n                                ) VALUES " + StringsKt.dropLast(str, 1) + "\n                            ");
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

    private final String resolveAmbientVolume(int taskId, JSONArray contentArray) {
        String str;
        long j;
        String string;
        String str2;
        if (contentArray.length() < 15) {
            return null;
        }
        try {
            j = contentArray.getLong(7);
            string = contentArray.getString(0);
            str2 = string;
        } catch (Exception e2) {
            e = e2;
            str = "";
        }
        if (!(str2 == null || str2.length() == 0) && j > 0) {
            String simpleName = AmbientVolumeLastestTaskListener.class.getSimpleName();
            Intrinsics.checkExpressionValueIsNotNull(simpleName, "AmbientVolumeLastestTask…er::class.java.simpleName");
            if (skipDate(taskId, string, simpleName)) {
                return null;
            }
            HealthVolumeData healthVolumeDataQueryHealthVolumeData = GreenDaoUtil.queryHealthVolumeData(getUserId(), string);
            if (healthVolumeDataQueryHealthVolumeData != null) {
                if (healthVolumeDataQueryHealthVolumeData.timestamp >= j) {
                    return null;
                }
                try {
                    healthVolumeDataQueryHealthVolumeData.delete();
                } catch (Exception unused) {
                    GreenDaoUtil.deleteDataByKey(HealthVolumeDataDao.TABLENAME, "DATE", string);
                }
            }
            String string2 = contentArray.getString(8);
            String str3 = string2;
            if (str3 == null || str3.length() == 0) {
                string2 = "";
            }
            int i = contentArray.getInt(1);
            int i2 = contentArray.getInt(2);
            int i3 = contentArray.getInt(3);
            int i4 = contentArray.getInt(9);
            int i5 = contentArray.getInt(4);
            String string3 = contentArray.getString(5);
            String string4 = contentArray.getString(6);
            str = "";
            try {
                int i6 = contentArray.getInt(10);
                int i7 = contentArray.getInt(11);
                int i8 = contentArray.getInt(12);
                int i9 = contentArray.getInt(13);
                int i10 = contentArray.getInt(14);
                if (i <= 120 && i >= 0 && i2 <= 120 && i2 >= 0 && i5 <= 120 && i5 >= 0) {
                    return "\n            (\"" + string + "\"," + i + ',' + i2 + ',' + i3 + ',' + i5 + ",\n            \"" + string3 + "\"," + j + ",\"" + string4 + "\",\"" + string2 + "\",\"" + i4 + "\",\n            \"" + i6 + "\",\"" + i7 + "\",\"" + i8 + "\",\n            \"" + i9 + "\",\"" + i10 + "\",\n            1," + getUserId() + ")\n                ";
                }
                return null;
            } catch (Exception e3) {
                e = e3;
            }
            e.printStackTrace();
            return str;
        }
        return null;
    }

    @Override // com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
    public void onSingleTaskFailed(Task.TaskInfo taskInfo) {
        super.onSingleTaskFailed(taskInfo);
        StringBuilder sb = new StringBuilder();
        sb.append("环境音量数据处理失败, taskInfo = ");
        sb.append(taskInfo != null ? Integer.valueOf(taskInfo.getTaskId()) : null);
        printAndSaveLog(sb.toString());
    }
}