package com.ido.life.syncdownload;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.fitness.data.Field;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.net.BaseUrl;
import com.ido.life.syncdownload.NewTask;
import com.ido.life.syncdownload.Task;
import com.ido.life.util.GreenDaoUtil;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: UserTargetLastestTaskListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\u0010\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\u0000H\u0016J\u001c\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u0014"}, d2 = {"Lcom/ido/life/syncdownload/UserTargetLastestTaskListener;", "Lcom/ido/life/syncdownload/BaseLastestTaskListener;", "userId", "", "(J)V", "convertJsonObjectToEntity", "", "jsonObject", "Lorg/json/JSONObject;", "getTaskTag", "", "kotlin.jvm.PlatformType", "getTaskType", "getTaskUrlPath", "newInstance", "onSingleTaskSuccess", "", "taskInfo", "Lcom/ido/life/syncdownload/Task$TaskInfo;", FirebaseAnalytics.Param.CONTENT, "app_release"}, k = 1, mv = {1, 1, 16})
public final class UserTargetLastestTaskListener extends BaseLastestTaskListener {
    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskType() {
        return BaseUrl.URL_NAME_HEALTH;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskUrlPath() {
        return "api/sport/target/get";
    }

    public UserTargetLastestTaskListener(long j) {
        super(j);
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskTag() {
        return UserTargetNew.class.getSimpleName();
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public UserTargetLastestTaskListener newInstance() {
        return new UserTargetLastestTaskListener(getUserId());
    }

    @Override // com.ido.life.syncdownload.Task.Listenter
    public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String content) {
        JSONObject jSONObject;
        Map<Long, String> map = BaseLastestTaskListener.INSTANCE.getMRecentDateMap().get(getClass().getSimpleName());
        if (map == null) {
            Intrinsics.throwNpe();
        }
        map.put(Long.valueOf(getUserId()), "");
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("最近一条用户目标数据下拉成功 taskInfo=");
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
            JSONObject jSONObject2 = new JSONObject(content);
            if (jSONObject2.getInt(NotificationCompat.CATEGORY_STATUS) == 200 && !jSONObject2.isNull("result") && (taskInfo instanceof NewTask.NewTaskInfo) && (jSONObject = jSONObject2.getJSONObject("result")) != null) {
                convertJsonObjectToEntity(jSONObject);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            printAndSaveLog("最近一条用户目标数据下拉转JSON失败 content=" + content + ",error=" + e2.getLocalizedMessage());
        }
        return true;
    }

    private final void convertJsonObjectToEntity(JSONObject jsonObject) {
        String str;
        if (jsonObject == null) {
            return;
        }
        try {
            UserTargetNew userTargetNew = new UserTargetNew();
            if (jsonObject.isNull("timestamp")) {
                str = "服务器上的目标数据不合法,取本地的目标数据";
            } else {
                str = "服务器上的目标数据不合法,取本地的目标数据";
                userTargetNew.setUpdateTime(jsonObject.getLong("timestamp"));
            }
            if (userTargetNew.getUpdateTime() <= 0) {
                String str2 = str;
                printAndSaveLog(str2);
                throw new IllegalStateException(str2);
            }
            userTargetNew.setUserId(getUserId());
            if (!jsonObject.isNull("date")) {
                userTargetNew.setDate(jsonObject.getString("date"));
            }
            String date = userTargetNew.getDate();
            Intrinsics.checkExpressionValueIsNotNull(date, "targetNew.date");
            if (isNullString(date)) {
                return;
            }
            UserTargetNew userTargetNewQueryUserLatestTarget = GreenDaoUtil.queryUserLatestTarget(getUserId());
            if (userTargetNewQueryUserLatestTarget != null && userTargetNewQueryUserLatestTarget.getUpdateTime() < userTargetNew.getUpdateTime()) {
                userTargetNewQueryUserLatestTarget.delete();
            }
            if (!jsonObject.isNull("numSteps")) {
                userTargetNew.setStep(jsonObject.getInt("numSteps"));
            }
            if (!jsonObject.isNull("distances")) {
                userTargetNew.setDistance(jsonObject.getInt("distances"));
            }
            if (!jsonObject.isNull(Field.NUTRIENT_CALORIES)) {
                userTargetNew.setCalories(jsonObject.getInt(Field.NUTRIENT_CALORIES));
            }
            if (!jsonObject.isNull("sleepTimes")) {
                userTargetNew.setSleepTimes(jsonObject.getInt("sleepTimes"));
            }
            if (!jsonObject.isNull("sportTimes")) {
                userTargetNew.setSportTimes(jsonObject.getInt("sportTimes"));
            }
            if (!jsonObject.isNull("walkTimes")) {
                userTargetNew.setWalk(jsonObject.getInt("walkTimes"));
            }
            if (!jsonObject.isNull("exerciseDurationTimes")) {
                userTargetNew.setActivityTime(jsonObject.getInt("exerciseDurationTimes"));
            }
            if (!jsonObject.isNull("sportSteps")) {
                userTargetNew.setSportStep(jsonObject.getInt("sportSteps"));
            }
            if (!jsonObject.isNull("weight")) {
                Object obj = jsonObject.get("weight");
                if (obj instanceof Integer) {
                    userTargetNew.setWeight(((Number) obj).intValue());
                } else if (obj instanceof Float) {
                    userTargetNew.setWeight(((Number) obj).floatValue());
                } else if (obj instanceof Double) {
                    userTargetNew.setWeight((float) ((Number) obj).doubleValue());
                }
            }
            userTargetNew.setHasUpload(true);
            userTargetNew.setCreateTime(userTargetNew.getUpdateTime());
            GreenDaoUtil.addUserTarget(userTargetNew);
            Map<Long, String> map = BaseLastestTaskListener.INSTANCE.getMRecentDateMap().get(getClass().getSimpleName());
            if (map == null) {
                Intrinsics.throwNpe();
            }
            Long lValueOf = Long.valueOf(getUserId());
            String date2 = userTargetNew.getDate();
            Intrinsics.checkExpressionValueIsNotNull(date2, "targetNew.date");
            map.put(lValueOf, date2);
        } catch (Exception e2) {
            e2.printStackTrace();
            printAndSaveLog("最近一条用户目标数据下拉ITEM转换失败 jsonObject=" + jsonObject + ",error=" + e2.getLocalizedMessage());
        }
    }
}