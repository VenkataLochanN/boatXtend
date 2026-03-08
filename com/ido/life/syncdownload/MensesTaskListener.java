package com.ido.life.syncdownload;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.life.database.model.DataPullConfigInfo;
import com.ido.life.database.model.LifeCycleItemBean;
import com.ido.life.database.model.LifeCycleItemBeanDao;
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
import org.json.JSONException;

/* JADX INFO: compiled from: MensesTaskListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0000H\u0014J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\"\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\n \u000e*\u0004\u0018\u00010\n0\nH\u0016J\b\u0010\u000f\u001a\u00020\nH\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016J\b\u0010\u0011\u001a\u00020\u0000H\u0016J\u001a\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\nH\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0014\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u001a\u0010\u001c\u001a\u0004\u0018\u00010\n2\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u001bH\u0002¨\u0006\u001f"}, d2 = {"Lcom/ido/life/syncdownload/MensesTaskListener;", "Lcom/ido/life/syncdownload/BaseHistoryTaskListener;", "userId", "", "(J)V", "clone", "getDataSize", "getRequestParams", "", "", "", "threadCount", "", "getTaskTag", "kotlin.jvm.PlatformType", "getTaskType", "getTaskUrlPath", "newInstance", "processData", "", "taskInfo", "Lcom/ido/life/syncdownload/Task$TaskInfo;", FirebaseAnalytics.Param.CONTENT, "resolveContent", "", "Lcom/ido/life/syncdownload/NewTask$NewTaskInfo;", "jsonArray", "Lorg/json/JSONArray;", "resolveMenses", "taskId", "contentArray", "app_release"}, k = 1, mv = {1, 1, 16})
public final class MensesTaskListener extends BaseHistoryTaskListener {
    @Override // com.ido.life.syncdownload.BaseHistoryTaskListener
    public long getDataSize() {
        return 447L;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskType() {
        return BaseUrl.URL_NAME_HEALTH;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskUrlPath() {
        return "api/menstrual/v2/sync/data";
    }

    public MensesTaskListener(long j) {
        super(j, 0, 2, null);
    }

    @Override // com.ido.life.syncdownload.BaseTaskListener
    public MensesTaskListener clone() {
        return new MensesTaskListener(getUserId());
    }

    @Override // com.ido.life.syncdownload.BaseHistoryTaskListener
    public List<Map<String, String>> getRequestParams(int threadCount) {
        int iMax = Math.max(getMaxDownloadDataCount(threadCount), 30);
        DataPullConfigInfo dataPullConfigInfoQueryDataPullConfigInfo = GreenDaoUtil.queryDataPullConfigInfo(getUserId());
        if (dataPullConfigInfoQueryDataPullConfigInfo == null) {
            return getDefaultParams();
        }
        List<Map<String, String>> listCaluteRequestDate = caluteRequestDate(iMax / 30, dataPullConfigInfoQueryDataPullConfigInfo.getMensesStartTime(), dataPullConfigInfoQueryDataPullConfigInfo.getMensesEndTime(), DateUtil.DATE_FORMAT_YMD);
        List<Map<String, String>> list = listCaluteRequestDate;
        return list == null || list.isEmpty() ? getDefaultParams() : listCaluteRequestDate;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskTag() {
        return LifeCycleItemBean.class.getSimpleName();
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public MensesTaskListener newInstance() {
        return new MensesTaskListener(getUserId());
    }

    @Override // com.ido.life.syncdownload.BaseHistoryTaskListener
    public boolean processData(Task.TaskInfo taskInfo, String content) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("生理周期数据下拉成功taskInfo=");
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
            printAndSaveLog("生理周期数据下拉转JSON失败 taskInfo=" + taskInfo + ",error=" + e2.getLocalizedMessage());
            return true;
        }
    }

    private final void resolveContent(NewTask.NewTaskInfo taskInfo, JSONArray jsonArray) {
        try {
            int length = jsonArray.length();
            if (length > 0) {
                taskInfo.getBuilder().setDataTotalCount(0);
                String str = "";
                for (int i = 0; i < length; i++) {
                    if (getMHasStop()) {
                        return;
                    }
                    JSONArray jSONArray = jsonArray.getJSONArray(i);
                    if (jSONArray != null && jSONArray.length() != 0) {
                        String strResolveMenses = resolveMenses(taskInfo.getTaskId(), jSONArray);
                        String str2 = strResolveMenses;
                        if (!(str2 == null || str2.length() == 0)) {
                            str = str + strResolveMenses + ',';
                            NewTask.Builder builder = taskInfo.getBuilder();
                            builder.setDataTotalCount(builder.getDataTotalCount() + 1);
                        }
                        if (getSQL_INSERT_MAX_COUNT() <= 0 || i == length - 1) {
                            if (str.length() > 0) {
                                GreenDaoUtil.execSql("\n                                insert into LifeCycle(\n                                USER_ID,\n                                MONTH,\n                                MENSES_CYCLE,\n                                MENSES_DAYS,\n                                SOURCE_MAC,\n                                DEVICE_NAME,\n                                ITEM_LIST,\n                                TIME_STAMP,\n                                MENSES_START_DAY,\n                                OVULATION_DAY,\n                                PREGNANCY_DAY_BEFORE_REMIND,\n                                REMINDER_TIME,\n                                UPLOAD,\n                                NEED_SYNC_TO_DEVICE\n                                ) VALUES " + StringsKt.dropLast(str, 1) + "\n                            ");
                                str = "";
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

    private final String resolveMenses(int taskId, JSONArray contentArray) throws JSONException {
        if (contentArray.length() < 11) {
            return null;
        }
        long j = contentArray.getLong(5);
        String string = contentArray.getString(0);
        String string2 = contentArray.getString(6);
        String str = string;
        if (!(str == null || str.length() == 0) && j > 0) {
            String str2 = string2;
            if (!(str2 == null || str2.length() == 0)) {
                String simpleName = MensesLatestTaskListener.class.getSimpleName();
                Intrinsics.checkExpressionValueIsNotNull(simpleName, "MensesLatestTaskListener::class.java.simpleName");
                if (skipDate(taskId, string, simpleName)) {
                    return null;
                }
                LifeCycleItemBean lifeCycleItemBeanQueryLifeCycleItemBeanByDate = GreenDaoUtil.queryLifeCycleItemBeanByDate(getUserId(), string);
                if (lifeCycleItemBeanQueryLifeCycleItemBeanByDate != null) {
                    if (lifeCycleItemBeanQueryLifeCycleItemBeanByDate.getTimeStamp() > j) {
                        return null;
                    }
                    try {
                        lifeCycleItemBeanQueryLifeCycleItemBeanByDate.delete();
                    } catch (Exception unused) {
                        GreenDaoUtil.deleteDataByKey(LifeCycleItemBeanDao.TABLENAME, "MONTH", string);
                    }
                }
                return "\n            (" + getUserId() + ",'" + string + "'," + contentArray.getInt(1) + ',' + contentArray.getInt(2) + ",'" + contentArray.getString(3) + "',\n            '" + contentArray.getString(4) + "','" + contentArray.getString(6) + "'," + j + ',' + contentArray.getInt(7) + ",\n            " + contentArray.getInt(8) + ',' + contentArray.getInt(9) + ",'" + contentArray.getString(10) + "',1,1)\n                ";
            }
        }
        return null;
    }
}