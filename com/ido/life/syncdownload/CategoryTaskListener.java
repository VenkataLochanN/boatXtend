package com.ido.life.syncdownload;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.life.database.model.CalorieDayData;
import com.ido.life.database.model.CalorieDayDataDao;
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

/* JADX INFO: compiled from: CategoryTaskListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0000H\u0014J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\"\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\n \u000e*\u0004\u0018\u00010\n0\nH\u0016J\b\u0010\u000f\u001a\u00020\nH\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016J\b\u0010\u0011\u001a\u00020\u0000H\u0016J\u001a\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\nH\u0016J\u001a\u0010\u0017\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0014\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001aH\u0002¨\u0006\u001f"}, d2 = {"Lcom/ido/life/syncdownload/CategoryTaskListener;", "Lcom/ido/life/syncdownload/BaseHistoryTaskListener;", "userId", "", "(J)V", "clone", "getDataSize", "getRequestParams", "", "", "", "threadCount", "", "getTaskTag", "kotlin.jvm.PlatformType", "getTaskType", "getTaskUrlPath", "newInstance", "processData", "", "taskInfo", "Lcom/ido/life/syncdownload/Task$TaskInfo;", FirebaseAnalytics.Param.CONTENT, "resolveCalorie", "taskId", "contentArray", "Lorg/json/JSONArray;", "resolveContent", "", "Lcom/ido/life/syncdownload/NewTask$NewTaskInfo;", "jsonArray", "app_release"}, k = 1, mv = {1, 1, 16})
public final class CategoryTaskListener extends BaseHistoryTaskListener {
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
        return "api/calorie/sync/data";
    }

    public CategoryTaskListener(long j) {
        super(j, 0, 2, null);
    }

    @Override // com.ido.life.syncdownload.BaseTaskListener
    public CategoryTaskListener clone() {
        return new CategoryTaskListener(getUserId());
    }

    @Override // com.ido.life.syncdownload.BaseHistoryTaskListener
    public List<Map<String, String>> getRequestParams(int threadCount) {
        int iMax = Math.max(getMaxDownloadDataCount(threadCount), 30);
        DataPullConfigInfo dataPullConfigInfoQueryDataPullConfigInfo = GreenDaoUtil.queryDataPullConfigInfo(getUserId());
        if (dataPullConfigInfoQueryDataPullConfigInfo == null) {
            return getDefaultParams();
        }
        List<Map<String, String>> listCaluteRequestDate = caluteRequestDate(iMax / 30, dataPullConfigInfoQueryDataPullConfigInfo.getCalorieStartTime(), dataPullConfigInfoQueryDataPullConfigInfo.getCalorieEndTime(), DateUtil.DATE_FORMAT_YMD);
        List<Map<String, String>> list = listCaluteRequestDate;
        return list == null || list.isEmpty() ? getDefaultParams() : listCaluteRequestDate;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskTag() {
        return CalorieDayData.class.getSimpleName();
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public CategoryTaskListener newInstance() {
        return new CategoryTaskListener(getUserId());
    }

    @Override // com.ido.life.syncdownload.BaseHistoryTaskListener
    public boolean processData(Task.TaskInfo taskInfo, String content) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("卡路里数据下拉成功taskInfo=");
            sb.append(taskInfo);
            sb.append(",content=");
            sb.append(content.length() > 200 ? content.subSequence(0, 200) : content);
            printAndSaveLog(sb.toString());
            JSONArray jSONArray = new JSONArray(content);
            if (!(taskInfo instanceof NewTask.NewTaskInfo)) {
                return true;
            }
            resolveContent((NewTask.NewTaskInfo) taskInfo, jSONArray);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            printAndSaveLog("卡路里数据下拉转JSON失败taskInfo=" + taskInfo + ",error=" + e2.getLocalizedMessage());
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
                        String strResolveCalorie = resolveCalorie(taskInfo.getTaskId(), jSONArray);
                        String str2 = strResolveCalorie;
                        if (!(str2 == null || str2.length() == 0)) {
                            str = str + strResolveCalorie + ',';
                            NewTask.Builder builder = taskInfo.getBuilder();
                            builder.setDataTotalCount(builder.getDataTotalCount() + 1);
                            i++;
                        }
                        if (i >= getSQL_INSERT_MAX_COUNT() || i2 == length - 1) {
                            if (str.length() > 0) {
                                GreenDaoUtil.execSql("\n                                insert into CALORIE_DAY_DATA(\n                                DATE,\n                                TOTAL_CALORIE,\n                                ACTIVITY_CALORIE,\n                                SOURCE_MAC,\n                                DEVICE_NAME,\n                                TIMESTAMP,\n                                ITEMS,\n                                ACTIVITY_ITEMS,\n                                TARGET_CALORIE,\n                                UPLOADED,\n                                USER_ID,\n                                LOAD_DETAIL\n                                ) VALUES " + StringsKt.dropLast(str, 1) + "\n                            ");
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

    private final String resolveCalorie(int taskId, JSONArray contentArray) {
        if (contentArray.length() < 9) {
            return null;
        }
        try {
            long j = contentArray.getLong(5);
            String string = contentArray.getString(0);
            String str = string;
            if (!(str == null || str.length() == 0) && j > 0) {
                String simpleName = CategoryLastestTaskListener.class.getSimpleName();
                Intrinsics.checkExpressionValueIsNotNull(simpleName, "CategoryLastestTaskListener::class.java.simpleName");
                if (skipDate(taskId, string, simpleName)) {
                    return null;
                }
                CalorieDayData calorieDayDataQueryCalorieDayByDate = GreenDaoUtil.queryCalorieDayByDate(getUserId(), string);
                if (calorieDayDataQueryCalorieDayByDate != null) {
                    if (calorieDayDataQueryCalorieDayByDate.getTimestamp() >= j) {
                        return null;
                    }
                    try {
                        calorieDayDataQueryCalorieDayByDate.delete();
                    } catch (Exception unused) {
                        GreenDaoUtil.deleteDataByKey(CalorieDayDataDao.TABLENAME, "DATE", string);
                    }
                }
                String string2 = contentArray.getString(6);
                String str2 = string2;
                if (str2 == null || str2.length() == 0) {
                    string2 = "";
                }
                String string3 = contentArray.getString(7);
                String str3 = string3;
                if (str3 == null || str3.length() == 0) {
                    string3 = "";
                }
                return "\n            ('" + string + "'," + contentArray.getInt(1) + ',' + contentArray.getInt(2) + ",'" + contentArray.getString(3) + "',\n                '" + contentArray.getString(4) + "'," + j + ",'" + string2 + "','" + string3 + "'," + contentArray.getInt(8) + ",1,\n                " + getUserId() + ",1)\n        ";
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }
}