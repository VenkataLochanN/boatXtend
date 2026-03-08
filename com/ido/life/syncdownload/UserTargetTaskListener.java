package com.ido.life.syncdownload;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.life.database.model.DataPullConfigInfo;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.database.model.UserTargetNewDao;
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

/* JADX INFO: compiled from: UserTargetTaskListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0000H\u0014J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\"\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\n \u000e*\u0004\u0018\u00010\n0\nH\u0016J\b\u0010\u000f\u001a\u00020\nH\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016J\b\u0010\u0011\u001a\u00020\u0000H\u0016J\u001a\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\nH\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0014\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u001a\u0010\u001c\u001a\u0004\u0018\u00010\n2\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u001bH\u0002¨\u0006\u001f"}, d2 = {"Lcom/ido/life/syncdownload/UserTargetTaskListener;", "Lcom/ido/life/syncdownload/BaseHistoryTaskListener;", "userId", "", "(J)V", "clone", "getDataSize", "getRequestParams", "", "", "", "threadCount", "", "getTaskTag", "kotlin.jvm.PlatformType", "getTaskType", "getTaskUrlPath", "newInstance", "processData", "", "taskInfo", "Lcom/ido/life/syncdownload/Task$TaskInfo;", FirebaseAnalytics.Param.CONTENT, "resolveContent", "", "Lcom/ido/life/syncdownload/NewTask$NewTaskInfo;", "jsonArray", "Lorg/json/JSONArray;", "resolveTarget", "taskId", "contentArray", "app_release"}, k = 1, mv = {1, 1, 16})
public final class UserTargetTaskListener extends BaseHistoryTaskListener {
    @Override // com.ido.life.syncdownload.BaseHistoryTaskListener
    public long getDataSize() {
        return 109L;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskType() {
        return BaseUrl.URL_NAME_HEALTH;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskUrlPath() {
        return "api/sport/target/sync/data";
    }

    public UserTargetTaskListener(long j) {
        super(j, 0, 2, null);
    }

    @Override // com.ido.life.syncdownload.BaseTaskListener
    public UserTargetTaskListener clone() {
        return new UserTargetTaskListener(getUserId());
    }

    @Override // com.ido.life.syncdownload.BaseHistoryTaskListener
    public List<Map<String, String>> getRequestParams(int threadCount) {
        int iMax = Math.max(getMaxDownloadDataCount(threadCount), 30);
        DataPullConfigInfo dataPullConfigInfoQueryDataPullConfigInfo = GreenDaoUtil.queryDataPullConfigInfo(getUserId());
        if (dataPullConfigInfoQueryDataPullConfigInfo == null) {
            return getDefaultParams();
        }
        List<Map<String, String>> listCaluteRequestDate = caluteRequestDate(iMax / 30, dataPullConfigInfoQueryDataPullConfigInfo.getTargetStartTime(), dataPullConfigInfoQueryDataPullConfigInfo.getTargetEndTime(), DateUtil.DATE_FORMAT_YMD);
        List<Map<String, String>> list = listCaluteRequestDate;
        return list == null || list.isEmpty() ? getDefaultParams() : listCaluteRequestDate;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskTag() {
        return UserTargetNew.class.getSimpleName();
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public UserTargetTaskListener newInstance() {
        return new UserTargetTaskListener(getUserId());
    }

    @Override // com.ido.life.syncdownload.BaseHistoryTaskListener
    public boolean processData(Task.TaskInfo taskInfo, String content) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("用户目标数据下拉成功 taskInfo=");
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
            printAndSaveLog("走动小时数据下拉转JSON失败 content=" + content + ",error=" + e2.getLocalizedMessage());
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
                        String strResolveTarget = resolveTarget(taskInfo.getTaskId(), jSONArray);
                        String str2 = strResolveTarget;
                        if (!(str2 == null || str2.length() == 0)) {
                            str = str + strResolveTarget + ',';
                            NewTask.Builder builder = taskInfo.getBuilder();
                            builder.setDataTotalCount(builder.getDataTotalCount() + 1);
                            i++;
                        }
                        if (i >= getSQL_INSERT_MAX_COUNT() || i2 == length - 1) {
                            if (str.length() > 0) {
                                GreenDaoUtil.execSql("\n                                insert into USER_TARGET_NEW(\n                                DATE,\n                                STEP,\n                                DISTANCE,\n                                CALORIES,\n                                SLEEP_TIMES,\n                                SPORT_TIMES,\n                                WEIGHT,\n                                WALK,\n                                ACTIVITY_TIME,\n                                SPORT_STEP,\n                                UPDATE_TIME,\n                                WEIGHT_UNIT,\n                                HAS_SYNC_TO_DEVICE,\n                                HAS_UPLOAD,\n                                CREATE_TIME,\n                                USER_ID\n                                ) VALUES " + StringsKt.dropLast(str, 1) + "\n                            ");
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

    private final String resolveTarget(int taskId, JSONArray contentArray) {
        if (contentArray.length() < 11) {
            return null;
        }
        int i = 10;
        try {
            long j = contentArray.getLong(10);
            String string = contentArray.getString(0);
            String simpleName = UserTargetLastestTaskListener.class.getSimpleName();
            Intrinsics.checkExpressionValueIsNotNull(simpleName, "UserTargetLastestTaskLis…er::class.java.simpleName");
            if (skipDate(taskId, string, simpleName)) {
                return null;
            }
            String str = string;
            if (!(str == null || str.length() == 0) && j > 0) {
                UserTargetNew userTargetNewQueryUserTarget = GreenDaoUtil.queryUserTarget(getUserId(), string);
                if (userTargetNewQueryUserTarget != null) {
                    if (userTargetNewQueryUserTarget.getUpdateTime() >= j) {
                        return null;
                    }
                    try {
                        userTargetNewQueryUserTarget.delete();
                    } catch (Exception unused) {
                        GreenDaoUtil.deleteDataByKey(UserTargetNewDao.TABLENAME, "DATE", string);
                    }
                }
                int i2 = contentArray.getInt(1);
                if (i2 <= 0) {
                    i2 = 10000;
                }
                int i3 = contentArray.getInt(2);
                if (i3 <= 0) {
                    i3 = 5000;
                }
                int i4 = contentArray.getInt(3);
                if (i4 >= 10) {
                    i = i4;
                }
                int i5 = contentArray.getInt(4);
                int i6 = contentArray.getInt(5);
                double d2 = contentArray.getDouble(6);
                if (d2 <= 0) {
                    d2 = 50;
                }
                int i7 = contentArray.getInt(7);
                if (i7 <= 0) {
                    i7 = 43200;
                }
                int i8 = contentArray.getInt(8);
                if (i8 <= 0) {
                    i8 = 1800;
                }
                int i9 = contentArray.getInt(9);
                if (i9 <= 0) {
                    i9 = 100;
                }
                return "\n            ('" + string + "'," + i2 + ',' + i3 + ',' + i + "\n            ," + i5 + ',' + i6 + ',' + d2 + "\n            ," + i7 + ',' + i8 + ',' + i9 + "\n            ," + j + ",1,1,1," + j + ',' + getUserId() + "\n            )\n                ";
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }
}