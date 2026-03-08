package com.ido.life.syncdownload;

import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ido.life.database.model.WalkDayData;
import com.ido.life.net.BaseUrl;
import com.ido.life.syncdownload.NewTask;
import com.ido.life.syncdownload.Task;
import com.ido.life.util.GreenDaoUtil;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: WalkHourLastestTaskListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\u0010\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\u0000H\u0016J\u001c\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u0014"}, d2 = {"Lcom/ido/life/syncdownload/WalkHourLastestTaskListener;", "Lcom/ido/life/syncdownload/BaseLastestTaskListener;", "userId", "", "(J)V", "convertJsonObjectToEntity", "", "jsonObject", "Lorg/json/JSONObject;", "getTaskTag", "", "kotlin.jvm.PlatformType", "getTaskType", "getTaskUrlPath", "newInstance", "onSingleTaskSuccess", "", "taskInfo", "Lcom/ido/life/syncdownload/Task$TaskInfo;", FirebaseAnalytics.Param.CONTENT, "app_release"}, k = 1, mv = {1, 1, 16})
public final class WalkHourLastestTaskListener extends BaseLastestTaskListener {
    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskType() {
        return BaseUrl.URL_NAME_HEALTH;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskUrlPath() {
        return "api/walk/data/last1days";
    }

    public WalkHourLastestTaskListener(long j) {
        super(j);
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskTag() {
        return WalkDayData.class.getSimpleName();
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public WalkHourLastestTaskListener newInstance() {
        return new WalkHourLastestTaskListener(getUserId());
    }

    @Override // com.ido.life.syncdownload.Task.Listenter
    public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String content) {
        JSONArray jSONArray;
        Map<Long, String> map = BaseLastestTaskListener.INSTANCE.getMRecentDateMap().get(getClass().getSimpleName());
        if (map == null) {
            Intrinsics.throwNpe();
        }
        map.put(Long.valueOf(getUserId()), "");
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("最近一条走动小时数据下拉成功 taskInfo=");
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
            if (jSONObject.getInt(NotificationCompat.CATEGORY_STATUS) == 200 && !jSONObject.isNull("result") && (taskInfo instanceof NewTask.NewTaskInfo) && (jSONArray = jSONObject.getJSONArray("result")) != null && jSONArray.length() > 0) {
                convertJsonObjectToEntity(jSONArray.getJSONObject(0));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            printAndSaveLog("最近一条走动小时数据下拉转JSON失败 content=" + content + ",error=" + e2.getLocalizedMessage());
        }
        return true;
    }

    private final void convertJsonObjectToEntity(JSONObject jsonObject) {
        if (jsonObject == null) {
            return;
        }
        try {
            WalkDayData walkDayData = new WalkDayData();
            if (!jsonObject.isNull("date")) {
                walkDayData.setDate(jsonObject.getString("date"));
            }
            String date = walkDayData.getDate();
            Intrinsics.checkExpressionValueIsNotNull(date, "item.date");
            if (isNullString(date)) {
                return;
            }
            if (!jsonObject.isNull("startTime")) {
                walkDayData.setStartTime(jsonObject.getString("startTime"));
            }
            String startTime = walkDayData.getStartTime();
            Intrinsics.checkExpressionValueIsNotNull(startTime, "item.startTime");
            if (isNullString(startTime)) {
                return;
            }
            if (!jsonObject.isNull("endTime")) {
                walkDayData.setEndTime(jsonObject.getString("endTime"));
            }
            String endTime = walkDayData.getEndTime();
            Intrinsics.checkExpressionValueIsNotNull(endTime, "item.endTime");
            if (isNullString(endTime)) {
                return;
            }
            if (!jsonObject.isNull("items")) {
                walkDayData.setItems(jsonObject.getString("items"));
            }
            String items = walkDayData.getItems();
            Intrinsics.checkExpressionValueIsNotNull(items, "item.items");
            if (isNullString(items)) {
                return;
            }
            if (!jsonObject.isNull("timestamp")) {
                walkDayData.setTimestamp(jsonObject.getLong("timestamp"));
            }
            if (walkDayData.getTimestamp() <= 0) {
                return;
            }
            WalkDayData walkDayDataQueryWalkDayByDate = GreenDaoUtil.queryWalkDayByDate(getUserId(), walkDayData.getDate());
            if (!jsonObject.isNull("sourceMac")) {
                walkDayData.setSourceMac(jsonObject.getString("sourceMac"));
            }
            if (walkDayDataQueryWalkDayByDate != null) {
                if (walkDayDataQueryWalkDayByDate.getTimestamp() > walkDayData.getTimestamp()) {
                    return;
                } else {
                    walkDayDataQueryWalkDayByDate.delete();
                }
            }
            walkDayData.setUserId(getUserId());
            if (!jsonObject.isNull("reachSeconds")) {
                walkDayData.setReachSeconds(jsonObject.getInt("reachSeconds"));
            }
            if (!jsonObject.isNull("targetSteps")) {
                walkDayData.setTargetSteps(jsonObject.getInt("targetSteps"));
            }
            if (!jsonObject.isNull("deviceName")) {
                walkDayData.setDeviceName(jsonObject.getString("deviceName"));
            }
            if (!jsonObject.isNull("totalSedentaryDuration")) {
                walkDayData.setSedentaryDuration(jsonObject.getInt("totalSedentaryDuration"));
            }
            if (!jsonObject.isNull("wearItems")) {
                walkDayData.setWearDurationList((List) new Gson().fromJson(jsonObject.getString("wearItems"), new TypeToken<List<? extends Integer>>() { // from class: com.ido.life.syncdownload.WalkHourLastestTaskListener.convertJsonObjectToEntity.1
                }.getType()));
            }
            if (!jsonObject.isNull("targetWalkDuration")) {
                walkDayData.setTargetWalkDuration(jsonObject.getInt("targetWalkDuration"));
            }
            walkDayData.setUploaded(true);
            GreenDaoUtil.insertWalk(walkDayData);
            Map<Long, String> map = BaseLastestTaskListener.INSTANCE.getMRecentDateMap().get(getClass().getSimpleName());
            if (map == null) {
                Intrinsics.throwNpe();
            }
            Long lValueOf = Long.valueOf(getUserId());
            String date2 = walkDayData.getDate();
            Intrinsics.checkExpressionValueIsNotNull(date2, "item.date");
            map.put(lValueOf, date2);
        } catch (Exception e2) {
            printAndSaveLog("最近一条走动小时数据下拉ITEM转换失败 jsonObject=" + jsonObject + ",error=" + e2.getLocalizedMessage());
        }
    }
}