package com.ido.life.syncdownload;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.life.database.model.DataPullConfigInfo;
import com.ido.life.database.model.UserInfo;
import com.ido.life.database.model.WeightItemBean;
import com.ido.life.database.model.WeightItemBeanDao;
import com.ido.life.enums.WeightBmiEnum;
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
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: compiled from: WeightTaskListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0000H\u0014J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\"\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\n \u000e*\u0004\u0018\u00010\n0\nH\u0016J\b\u0010\u000f\u001a\u00020\nH\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016J\b\u0010\u0011\u001a\u00020\u0000H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u001a\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\nH\u0016J\u0018\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\"\u0010\u001d\u001a\u0004\u0018\u00010\n2\u0006\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020!H\u0002¨\u0006\""}, d2 = {"Lcom/ido/life/syncdownload/WeightTaskListener;", "Lcom/ido/life/syncdownload/BaseHistoryTaskListener;", "userId", "", "(J)V", "clone", "getDataSize", "getRequestParams", "", "", "", "threadCount", "", "getTaskTag", "kotlin.jvm.PlatformType", "getTaskType", "getTaskUrlPath", "newInstance", "onAllTaskComplete", "", "processData", "", "taskInfo", "Lcom/ido/life/syncdownload/Task$TaskInfo;", FirebaseAnalytics.Param.CONTENT, "resolveContent", "Lcom/ido/life/syncdownload/NewTask$NewTaskInfo;", "jsonArray", "Lorg/json/JSONArray;", "resolveWeight", "taskId", "contentArray", "height", "", "app_release"}, k = 1, mv = {1, 1, 16})
public final class WeightTaskListener extends BaseHistoryTaskListener {
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
        return "api/weight/sync/data";
    }

    public WeightTaskListener(long j) {
        super(j, 0, 2, null);
    }

    @Override // com.ido.life.syncdownload.BaseTaskListener
    public WeightTaskListener clone() {
        return new WeightTaskListener(getUserId());
    }

    @Override // com.ido.life.syncdownload.BaseHistoryTaskListener
    public List<Map<String, String>> getRequestParams(int threadCount) {
        int iMax = Math.max(getMaxDownloadDataCount(threadCount), 30);
        DataPullConfigInfo dataPullConfigInfoQueryDataPullConfigInfo = GreenDaoUtil.queryDataPullConfigInfo(getUserId());
        if (dataPullConfigInfoQueryDataPullConfigInfo == null) {
            return getDefaultParams();
        }
        List<Map<String, String>> listCaluteRequestDate = caluteRequestDate(iMax / 30, dataPullConfigInfoQueryDataPullConfigInfo.getWeightStartTime(), dataPullConfigInfoQueryDataPullConfigInfo.getWeightEndTime(), DateUtil.DATE_FORMAT_YMD);
        List<Map<String, String>> list = listCaluteRequestDate;
        return list == null || list.isEmpty() ? getDefaultParams() : listCaluteRequestDate;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskTag() {
        return WeightItemBean.class.getSimpleName();
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public WeightTaskListener newInstance() {
        return new WeightTaskListener(getUserId());
    }

    @Override // com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
    public void onAllTaskComplete() {
        if (getMHasStop()) {
            return;
        }
        printAndSaveLog("体重下拉任务执行完成");
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(getUserId());
        if (userInfoQueryUserInfo != null) {
            float heightCm = userInfoQueryUserInfo.getHeightCm();
            float weightKg = userInfoQueryUserInfo.getWeightKg();
            WeightItemBean weightItemBeanQueryNewestWeightRecord = GreenDaoUtil.queryNewestWeightRecord(getUserId());
            if (weightItemBeanQueryNewestWeightRecord == null || weightKg == weightItemBeanQueryNewestWeightRecord.getTotalWeight()) {
                return;
            }
            WeightItemBean weightItemBean = new WeightItemBean();
            weightItemBean.setTotalWeight(weightKg);
            weightItemBean.setBmi(WeightBmiEnum.caluteBmi(weightKg, heightCm));
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
            weightItemBean.setDate(DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_YMD));
            weightItemBean.setTimeStamp(calendar.getTimeInMillis());
            weightItemBean.setUploadSuccess(false);
            weightItemBean.setUserId(getUserId());
            GreenDaoUtil.addWeight(weightItemBean);
        }
    }

    @Override // com.ido.life.syncdownload.BaseHistoryTaskListener
    public boolean processData(Task.TaskInfo taskInfo, String content) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("用户体重修改记录数据下拉成功 taskInfo=");
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
            printAndSaveLog("用户体重修改记录数据下拉转JSON失败 content=" + content + ",error=" + e2.getLocalizedMessage());
            return true;
        }
    }

    private final void resolveContent(NewTask.NewTaskInfo taskInfo, JSONArray jsonArray) {
        try {
            int length = jsonArray.length();
            if (length > 0) {
                taskInfo.getBuilder().setDataTotalCount(0);
                UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(getUserId());
                if (userInfoQueryUserInfo != null) {
                    float heightCm = userInfoQueryUserInfo.getHeightCm();
                    int i = 0;
                    String str = "";
                    for (int i2 = 0; i2 < length; i2++) {
                        if (getMHasStop()) {
                            return;
                        }
                        JSONArray jSONArray = jsonArray.getJSONArray(i2);
                        if (jSONArray != null && jSONArray.length() != 0) {
                            String strResolveWeight = resolveWeight(taskInfo.getTaskId(), jSONArray, heightCm);
                            String str2 = strResolveWeight;
                            if (!(str2 == null || str2.length() == 0)) {
                                str = str + strResolveWeight + ',';
                                NewTask.Builder builder = taskInfo.getBuilder();
                                builder.setDataTotalCount(builder.getDataTotalCount() + 1);
                                i++;
                            }
                            if (i >= getSQL_INSERT_MAX_COUNT() || i2 == length - 1) {
                                if (str.length() > 0) {
                                    GreenDaoUtil.execSql("\n                                insert into UserWeight(\n                                Date,\n                                TotalWeight,\n                                Bmi,\n                                TIME_STAMP,\n                                UPLOAD_SUCCESS,\n                                USER_ID,\n                                LOAD_DETAIL\n                                ) VALUES " + StringsKt.dropLast(str, 1) + "\n                            ");
                                    i = 0;
                                    str = "";
                                }
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

    private final String resolveWeight(int taskId, JSONArray contentArray, float height) throws JSONException {
        if (contentArray.length() < 4) {
            return null;
        }
        String string = contentArray.getString(0);
        float f2 = (float) contentArray.getDouble(1);
        long j = contentArray.getLong(3);
        if (f2 <= 250 && f2 >= 10) {
            String str = string;
            if (!(str == null || str.length() == 0) && j > 0) {
                String simpleName = WeightLastestTaskListener.class.getSimpleName();
                Intrinsics.checkExpressionValueIsNotNull(simpleName, "WeightLastestTaskListener::class.java.simpleName");
                if (skipDate(taskId, string, simpleName)) {
                    return null;
                }
                WeightItemBean weightItemBeanQueryWeightByDate = GreenDaoUtil.queryWeightByDate(getUserId(), string);
                if (weightItemBeanQueryWeightByDate != null) {
                    if (weightItemBeanQueryWeightByDate.getTimeStamp() >= j) {
                        return null;
                    }
                    try {
                        weightItemBeanQueryWeightByDate.delete();
                    } catch (Exception unused) {
                        GreenDaoUtil.deleteDataByKey(WeightItemBeanDao.TABLENAME, "Date", string);
                    }
                }
                return "\n            ('" + string + "'," + f2 + ',' + WeightBmiEnum.caluteBmi(f2, height) + ',' + j + ",1," + getUserId() + ",1)\n        ";
            }
        }
        return null;
    }
}