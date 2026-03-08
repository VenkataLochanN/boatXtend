package com.ido.life.syncdownload;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.fitness.data.Field;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.database.model.StepDayData;
import com.ido.life.net.BaseUrl;
import com.ido.life.syncdownload.NewTask;
import com.ido.life.syncdownload.Task;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: StepLastestTaskListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\u0010\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\u0000H\u0016J\u001c\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u0014"}, d2 = {"Lcom/ido/life/syncdownload/StepLastestTaskListener;", "Lcom/ido/life/syncdownload/BaseLastestTaskListener;", "userId", "", "(J)V", "converyJsonObjectToEntity", "", "jsonObject", "Lorg/json/JSONObject;", "getTaskTag", "", "kotlin.jvm.PlatformType", "getTaskType", "getTaskUrlPath", "newInstance", "onSingleTaskSuccess", "", "taskInfo", "Lcom/ido/life/syncdownload/Task$TaskInfo;", FirebaseAnalytics.Param.CONTENT, "app_release"}, k = 1, mv = {1, 1, 16})
public final class StepLastestTaskListener extends BaseLastestTaskListener {
    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskType() {
        return BaseUrl.URL_NAME_HEALTH;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskUrlPath() {
        return "api/steps/data/last1days";
    }

    public StepLastestTaskListener(long j) {
        super(j);
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskTag() {
        return StepDayData.class.getSimpleName();
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public StepLastestTaskListener newInstance() {
        return new StepLastestTaskListener(getUserId());
    }

    @Override // com.ido.life.syncdownload.Task.Listenter
    public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String content) {
        Map<Long, String> map = BaseLastestTaskListener.INSTANCE.getMRecentDateMap().get(getClass().getSimpleName());
        if (map == null) {
            Intrinsics.throwNpe();
        }
        map.put(Long.valueOf(getUserId()), "");
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("最近一条步数数据下拉成功 taskInfo=");
            sb.append(taskInfo);
            sb.append(",content=");
            String str = content;
            String strSubSequence = (!(str == null || str.length() == 0) && content.length() > 200) ? content.subSequence(0, 200) : content;
            sb.append(strSubSequence);
            printAndSaveLog(sb.toString());
            String str2 = content;
            if (str2 == null || str2.length() == 0) {
                return true;
            }
            JSONObject jSONObject = new JSONObject(content);
            if (jSONObject.getInt(NotificationCompat.CATEGORY_STATUS) == 200 && !jSONObject.isNull("result") && (taskInfo instanceof NewTask.NewTaskInfo)) {
                CommonLogUtil.d("最近一次步数任务执行成功");
                JSONArray jSONArray = jSONObject.getJSONArray("result");
                if (jSONArray != null && jSONArray.length() > 0) {
                    converyJsonObjectToEntity(jSONArray.getJSONObject(0));
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            printAndSaveLog("最近一条步数数据下拉转JSON失败 content=" + content + ",error=" + e2.getLocalizedMessage());
        }
        return true;
    }

    private final void converyJsonObjectToEntity(JSONObject jsonObject) {
        if (jsonObject == null) {
            return;
        }
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        int stepTarget = runTimeUtil.getStepTarget();
        try {
            StepDayData stepDayData = new StepDayData();
            if (!jsonObject.isNull("date")) {
                stepDayData.setDate(jsonObject.getString("date"));
            }
            String date = stepDayData.getDate();
            Intrinsics.checkExpressionValueIsNotNull(date, "item.date");
            if (isNullString(date)) {
                return;
            }
            if (!jsonObject.isNull("items")) {
                stepDayData.setItems(jsonObject.getString("items"));
            }
            String items = stepDayData.getItems();
            Intrinsics.checkExpressionValueIsNotNull(items, "item.items");
            if (isNullString(items)) {
                return;
            }
            if (!jsonObject.isNull("timestamp")) {
                stepDayData.setTimestamp(jsonObject.getLong("timestamp"));
            }
            if (stepDayData.getTimeStamp() <= 0) {
                return;
            }
            if (!jsonObject.isNull("sourceMac")) {
                stepDayData.setSourceMac(jsonObject.getString("sourceMac"));
            }
            stepDayData.setUserId(getUserId());
            if (!jsonObject.isNull("distances")) {
                stepDayData.setDistances(jsonObject.getInt("distances"));
            }
            if (!jsonObject.isNull("numSteps")) {
                stepDayData.setNumSteps(jsonObject.getInt("numSteps"));
            }
            if (!jsonObject.isNull(Field.NUTRIENT_CALORIES)) {
                stepDayData.setCalories(jsonObject.getInt(Field.NUTRIENT_CALORIES));
            }
            if (!jsonObject.isNull("timeOfSeconds")) {
                stepDayData.setTimeOfSeconds(jsonObject.getInt("timeOfSeconds"));
            }
            if (!jsonObject.isNull("deviceName")) {
                stepDayData.setDeviceName(jsonObject.getString("deviceName"));
            }
            if (!jsonObject.isNull("effectiveSteps")) {
                stepDayData.setEffectiveSteps(jsonObject.getInt("effectiveSteps"));
            }
            if (!jsonObject.isNull("maxNumSteps")) {
                stepDayData.setMaxNumSteps(jsonObject.getInt("maxNumSteps"));
            }
            if (!jsonObject.isNull("targetSteps")) {
                stepDayData.setTargetSteps(jsonObject.getInt("targetSteps"));
            }
            if (stepDayData.getTargetSteps() < 5000 || stepDayData.getTargetSteps() > 25000) {
                if (stepTarget >= 5000 && stepTarget <= 25000) {
                    stepDayData.setTargetSteps(stepTarget);
                } else {
                    stepDayData.setTargetSteps(10000);
                }
            }
            stepDayData.setUploaded(true);
            StepDayData stepDailyDataByDate = GreenDaoUtil.getStepDailyDataByDate(getUserId(), stepDayData.getDate());
            if (stepDailyDataByDate != null) {
                if (stepDailyDataByDate.getTimeStamp() > stepDayData.getTimeStamp()) {
                    return;
                } else {
                    stepDailyDataByDate.delete();
                }
            }
            stepDayData.setLoadDetail(true);
            GreenDaoUtil.insertStep(stepDayData);
            Map<Long, String> map = BaseLastestTaskListener.INSTANCE.getMRecentDateMap().get(getClass().getSimpleName());
            if (map == null) {
                Intrinsics.throwNpe();
            }
            Long lValueOf = Long.valueOf(getUserId());
            String date2 = stepDayData.getDate();
            Intrinsics.checkExpressionValueIsNotNull(date2, "item.date");
            map.put(lValueOf, date2);
        } catch (Exception e2) {
            printAndSaveLog("最近一条步数数据下拉ITEM转换失败 jsonObject=" + jsonObject + ",error=" + e2.getLocalizedMessage());
        }
    }
}