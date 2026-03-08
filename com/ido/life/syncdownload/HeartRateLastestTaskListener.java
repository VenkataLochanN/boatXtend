package com.ido.life.syncdownload;

import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.life.database.model.ServerHeartRateDayData;
import com.ido.life.net.BaseUrl;
import com.ido.life.syncdownload.NewTask;
import com.ido.life.syncdownload.Task;
import com.ido.life.util.GreenDaoUtil;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: HeartRateLastestTaskListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\u0000H\u0016J\u001c\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u0014"}, d2 = {"Lcom/ido/life/syncdownload/HeartRateLastestTaskListener;", "Lcom/ido/life/syncdownload/BaseLastestTaskListener;", "userId", "", "(J)V", "convertJsonObjectToEntity", "", "jsonObject", "Lorg/json/JSONObject;", "getTaskTag", "", "kotlin.jvm.PlatformType", "getTaskType", "getTaskUrlPath", "newInstance", "onSingleTaskSuccess", "", "taskInfo", "Lcom/ido/life/syncdownload/Task$TaskInfo;", FirebaseAnalytics.Param.CONTENT, "app_release"}, k = 1, mv = {1, 1, 16})
public final class HeartRateLastestTaskListener extends BaseLastestTaskListener {
    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskType() {
        return BaseUrl.URL_NAME_HEALTH;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskUrlPath() {
        return "api/heartrate/V2/data/last1days";
    }

    public HeartRateLastestTaskListener(long j) {
        super(j);
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskTag() {
        return ServerHeartRateDayData.class.getSimpleName();
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public HeartRateLastestTaskListener newInstance() {
        return new HeartRateLastestTaskListener(getUserId());
    }

    @Override // com.ido.life.syncdownload.Task.Listenter
    public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String content) {
        JSONArray jSONArray;
        int length;
        Map<Long, String> map = BaseLastestTaskListener.INSTANCE.getMRecentDateMap().get(getClass().getSimpleName());
        if (map == null) {
            Intrinsics.throwNpe();
        }
        map.put(Long.valueOf(getUserId()), "");
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("最近一条心率数据下拉成功 taskInfo=");
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
            if (jSONObject.getInt(NotificationCompat.CATEGORY_STATUS) == 200 && !jSONObject.isNull("result") && (taskInfo instanceof NewTask.NewTaskInfo) && (jSONArray = jSONObject.getJSONArray("result")) != null && (length = jSONArray.length()) > 0) {
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    Intrinsics.checkExpressionValueIsNotNull(jSONObject2, "jsonArray.getJSONObject(i)");
                    convertJsonObjectToEntity(jSONObject2);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            printAndSaveLog("最近一条心率数据下拉转JSON失败content=" + content + ",error=" + e2.getLocalizedMessage());
        }
        return true;
    }

    private final void convertJsonObjectToEntity(JSONObject jsonObject) {
        try {
            ServerHeartRateDayData serverHeartRateDayData = new ServerHeartRateDayData();
            if (jsonObject.has("date")) {
                serverHeartRateDayData.setDate(jsonObject.getString("date"));
            }
            String date = serverHeartRateDayData.getDate();
            Intrinsics.checkExpressionValueIsNotNull(date, "item.date");
            if (isNullString(date)) {
                return;
            }
            ServerHeartRateDayData dayHeartRateByDate = GreenDaoUtil.getDayHeartRateByDate(getUserId(), serverHeartRateDayData.getDate());
            if (dayHeartRateByDate != null) {
                if (dayHeartRateByDate.getTimestamp() > serverHeartRateDayData.getTimestamp()) {
                    return;
                } else {
                    dayHeartRateByDate.delete();
                }
            }
            if (!jsonObject.isNull("items")) {
                serverHeartRateDayData.setItems(jsonObject.getString("items"));
            }
            String items = serverHeartRateDayData.getItems();
            Intrinsics.checkExpressionValueIsNotNull(items, "item.items");
            if (isNullString(items)) {
                return;
            }
            if (!jsonObject.isNull("maxValue")) {
                serverHeartRateDayData.setMaxValue(jsonObject.getInt("maxValue"));
            }
            if (serverHeartRateDayData.getMaxValue() <= 0) {
                return;
            }
            if (!jsonObject.isNull("avgValue")) {
                serverHeartRateDayData.setAvgValue(jsonObject.getInt("avgValue"));
            }
            if (!jsonObject.isNull("sourceMac")) {
                serverHeartRateDayData.setSourceMac(jsonObject.getString("sourceMac"));
            }
            if (!jsonObject.isNull("timestamp")) {
                serverHeartRateDayData.setTimestamp(jsonObject.getLong("timestamp"));
            }
            if (serverHeartRateDayData.getTimestamp() <= 0) {
                return;
            }
            serverHeartRateDayData.setUserId(getUserId());
            serverHeartRateDayData.setUploadSuccess(true);
            if (!jsonObject.isNull("warmUpSeconds")) {
                serverHeartRateDayData.setWarmUpSeconds(jsonObject.getInt("warmUpSeconds"));
            }
            if (!jsonObject.isNull("warmUpThreshold")) {
                serverHeartRateDayData.setWarmUpThreshold(jsonObject.getInt("warmUpThreshold"));
            }
            if (!jsonObject.isNull("burnFatSeconds")) {
                serverHeartRateDayData.setBurnFatSeconds(jsonObject.getInt("burnFatSeconds"));
            }
            if (!jsonObject.isNull("burnFatThreshold")) {
                serverHeartRateDayData.setBurnFatThreshold(jsonObject.getInt("burnFatThreshold"));
            }
            if (!jsonObject.isNull("aerobicSeconds")) {
                serverHeartRateDayData.setAerobicSeconds(jsonObject.getInt("aerobicSeconds"));
            }
            if (!jsonObject.isNull("aerobicThreshold")) {
                serverHeartRateDayData.setAerobicThreshold(jsonObject.getInt("aerobicThreshold"));
            }
            if (!jsonObject.isNull("anaerobicSeconds")) {
                serverHeartRateDayData.setAnaerobicSeconds(jsonObject.getInt("anaerobicSeconds"));
            }
            if (!jsonObject.isNull("anaerobicThreshold")) {
                serverHeartRateDayData.setAnaerobicThreshold(jsonObject.getInt("anaerobicThreshold"));
            }
            if (!jsonObject.isNull("limitSeconds")) {
                serverHeartRateDayData.setLimitSeconds(jsonObject.getInt("limitSeconds"));
            }
            if (!jsonObject.isNull("limitThreshold")) {
                serverHeartRateDayData.setLimitThreshold(jsonObject.getInt("limitThreshold"));
            }
            if (!jsonObject.isNull("minValue")) {
                serverHeartRateDayData.setMinValue(jsonObject.getInt("minValue"));
            }
            if (!jsonObject.isNull("silentValue")) {
                serverHeartRateDayData.setSilentValue(jsonObject.getInt("silentValue"));
            }
            if (!jsonObject.isNull("latestValue")) {
                serverHeartRateDayData.setLatestValue(jsonObject.getInt("latestValue"));
            }
            if (!jsonObject.isNull("startTimeValue")) {
                serverHeartRateDayData.setStartTimeValue(jsonObject.getInt("startTimeValue"));
            }
            if (!jsonObject.isNull("heartMonitorType")) {
                serverHeartRateDayData.setHeartMonitorType(jsonObject.getInt("heartMonitorType"));
            }
            if (!jsonObject.isNull("deviceName")) {
                String deviceName = jsonObject.getString("deviceName");
                Intrinsics.checkExpressionValueIsNotNull(deviceName, "deviceName");
                if (!isNullString(deviceName)) {
                    serverHeartRateDayData.setDeviceName(deviceName);
                }
            }
            serverHeartRateDayData.setLoadDetail(true);
            GreenDaoUtil.insertHeartRate(serverHeartRateDayData);
            Map<Long, String> map = BaseLastestTaskListener.INSTANCE.getMRecentDateMap().get(getClass().getSimpleName());
            if (map == null) {
                Intrinsics.throwNpe();
            }
            Long lValueOf = Long.valueOf(getUserId());
            String date2 = serverHeartRateDayData.getDate();
            Intrinsics.checkExpressionValueIsNotNull(date2, "item.date");
            map.put(lValueOf, date2);
        } catch (Exception e2) {
            printAndSaveLog("最近一条心率数据下拉ITEM转换异常 jsonObject=" + jsonObject + ",error=" + e2.getLocalizedMessage());
        }
    }
}