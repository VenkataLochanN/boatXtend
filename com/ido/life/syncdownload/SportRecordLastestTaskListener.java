package com.ido.life.syncdownload;

import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.ble.event.stat.one.d;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.TimeUtil;
import com.ido.life.database.model.SportGps;
import com.ido.life.database.model.SportGpsData;
import com.ido.life.database.model.SportHealth;
import com.ido.life.database.model.SportItem;
import com.ido.life.database.model.SportItemPace;
import com.ido.life.database.model.SportSwimItem;
import com.ido.life.database.model.SportSwimSwolf;
import com.ido.life.net.BaseUrl;
import com.ido.life.syncdownload.NewTask;
import com.ido.life.syncdownload.Task;
import com.ido.life.util.GreenDaoUtil;
import java.util.ArrayList;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: SportRecordLastestTaskListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\u0000H\u0016J\u001c\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u0014"}, d2 = {"Lcom/ido/life/syncdownload/SportRecordLastestTaskListener;", "Lcom/ido/life/syncdownload/BaseLastestTaskListener;", "userId", "", "(J)V", "convertJsonToEntity", "", "jsonObject", "Lorg/json/JSONObject;", "getTaskTag", "", "kotlin.jvm.PlatformType", "getTaskType", "getTaskUrlPath", "newInstance", "onSingleTaskSuccess", "", "taskInfo", "Lcom/ido/life/syncdownload/Task$TaskInfo;", FirebaseAnalytics.Param.CONTENT, "app_release"}, k = 1, mv = {1, 1, 16})
public final class SportRecordLastestTaskListener extends BaseLastestTaskListener {
    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskType() {
        return BaseUrl.URL_NAME_HEALTH;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskUrlPath() {
        return "api/sports/getLatest";
    }

    public SportRecordLastestTaskListener(long j) {
        super(j);
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskTag() {
        return SportHealth.class.getSimpleName();
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public SportRecordLastestTaskListener newInstance() {
        return new SportRecordLastestTaskListener(getUserId());
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
            sb.append("最近一条运动记录数据下拉成功 taskInfo=");
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
                convertJsonToEntity(jSONObject);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            printAndSaveLog("最近一条运动记录数据下拉转JSON失败 content=" + content + ",error=" + e2.getLocalizedMessage());
        }
        return true;
    }

    private final void convertJsonToEntity(JSONObject jsonObject) {
        JSONObject jSONObject;
        JSONObject jSONObject2;
        try {
            SportHealth sportHealth = new SportHealth();
            if (!jsonObject.isNull("datetime")) {
                sportHealth.setDateTime(jsonObject.getString("datetime"));
            }
            String dateTime = sportHealth.getDateTime();
            Intrinsics.checkExpressionValueIsNotNull(dateTime, "item.dateTime");
            if (isNullString(dateTime)) {
                return;
            }
            if (!jsonObject.isNull("startTime")) {
                sportHealth.setStartTime(jsonObject.getString("startTime"));
            }
            String startTime = sportHealth.getStartTime();
            Intrinsics.checkExpressionValueIsNotNull(startTime, "item.startTime");
            if (isNullString(startTime)) {
                return;
            }
            if (!jsonObject.isNull("endTime")) {
                sportHealth.setEndTime(jsonObject.getString("endTime"));
            }
            String endTime = sportHealth.getEndTime();
            Intrinsics.checkExpressionValueIsNotNull(endTime, "item.endTime");
            if (isNullString(endTime)) {
                return;
            }
            if (!jsonObject.isNull("timestamp")) {
                sportHealth.setTimestamp(jsonObject.getLong("timestamp"));
            }
            if (sportHealth.getTimestamp() <= 0) {
                return;
            }
            if (!jsonObject.isNull("sid")) {
                sportHealth.setSid(jsonObject.getString("sid"));
            }
            if (!jsonObject.isNull("type")) {
                sportHealth.setType(jsonObject.getInt("type"));
            }
            if (!jsonObject.isNull("subType")) {
                sportHealth.setSubType(jsonObject.getInt("subType"));
            }
            if (!jsonObject.isNull("totalSeconds")) {
                sportHealth.setTotalSeconds(jsonObject.getInt("totalSeconds"));
            }
            if (!jsonObject.isNull("numCalories")) {
                sportHealth.setNumCalories(jsonObject.getInt("numCalories"));
            }
            if (!jsonObject.isNull("numSteps")) {
                sportHealth.setNumSteps(jsonObject.getInt("numSteps"));
            }
            if (!jsonObject.isNull("distance")) {
                sportHealth.setDistance(jsonObject.getInt("distance"));
            }
            if (!jsonObject.isNull("targetType")) {
                sportHealth.setTargetType(jsonObject.getInt("targetType"));
            }
            if (!jsonObject.isNull("targetValue")) {
                sportHealth.setTargetValue(jsonObject.getInt("targetValue"));
            }
            if (!jsonObject.isNull("warmupSeconds")) {
                sportHealth.setWarmupSeconds(jsonObject.getInt("warmupSeconds"));
            }
            if (!jsonObject.isNull("burnFatSeconds")) {
                sportHealth.setBurnFatSeconds(jsonObject.getInt("burnFatSeconds"));
            }
            if (!jsonObject.isNull("aerobicSeconds")) {
                sportHealth.setAerobicSeconds(jsonObject.getInt("aerobicSeconds"));
            }
            if (!jsonObject.isNull("anaerobicSeconds")) {
                sportHealth.setAnaerobicSecond(jsonObject.getInt("anaerobicSeconds"));
            }
            if (!jsonObject.isNull("extremeSeconds")) {
                sportHealth.setExtremeSecond(jsonObject.getInt("extremeSeconds"));
            }
            if (!jsonObject.isNull("sourceMac")) {
                sportHealth.setSourceMac(jsonObject.getString("sourceMac"));
            }
            if (!jsonObject.isNull("deviceName")) {
                sportHealth.setDeviceName(jsonObject.getString("deviceName"));
            }
            if (!jsonObject.isNull("sourceType")) {
                sportHealth.setSourceType(jsonObject.getInt("sourceType"));
            }
            if (!jsonObject.isNull("minHrValue")) {
                sportHealth.setMinHrValue(jsonObject.getInt("minHrValue"));
            }
            if (!jsonObject.isNull("maxHrValue")) {
                sportHealth.setMaxHrValue(jsonObject.getInt("maxHrValue"));
            }
            if (!jsonObject.isNull("avgHrValue")) {
                sportHealth.setAvgHrValue(jsonObject.getInt("avgHrValue"));
            }
            if (!jsonObject.isNull("minSpeed")) {
                sportHealth.setMinSpeed(jsonObject.getInt("minSpeed"));
            }
            if (!jsonObject.isNull("maxSpeed")) {
                sportHealth.setMaxSpeed(jsonObject.getInt("maxSpeed"));
            }
            if (!jsonObject.isNull("avgSpeed")) {
                sportHealth.setAvgSpeed(jsonObject.getInt("avgSpeed"));
            }
            if (!jsonObject.isNull("minPace")) {
                sportHealth.setMinPace(jsonObject.getInt("minPace"));
            }
            if (!jsonObject.isNull("maxPace")) {
                sportHealth.setMaxPace(jsonObject.getInt("maxPace"));
            }
            if (!jsonObject.isNull("avgPace")) {
                sportHealth.setAvgPace(jsonObject.getInt("avgPace"));
            }
            if (!jsonObject.isNull("isLocus")) {
                sportHealth.setIsLocus(jsonObject.getInt("isLocus"));
            }
            if (!jsonObject.isNull("stepRange")) {
                sportHealth.setStepRange(jsonObject.getInt("stepRange"));
            }
            if (!jsonObject.isNull("minRate")) {
                sportHealth.setMinRate(jsonObject.getInt("minRate"));
            }
            if (!jsonObject.isNull("maxRate")) {
                sportHealth.setStepRateMax(jsonObject.getInt("maxRate"));
            }
            if (!jsonObject.isNull("avgRate")) {
                sportHealth.setStepRate(jsonObject.getInt("avgRate"));
            }
            if (!jsonObject.isNull("swType")) {
                sportHealth.setSwimmingPosture(jsonObject.getInt("swType"));
            }
            if (!jsonObject.isNull("swHitNums")) {
                sportHealth.setTotalStrokesNumber(jsonObject.getInt("swHitNums"));
            }
            if (!jsonObject.isNull("swPoolLength")) {
                sportHealth.setPoolDistance(jsonObject.getInt("swPoolLength"));
            }
            if (!jsonObject.isNull("swTrips")) {
                sportHealth.setTrips(jsonObject.getInt("swTrips"));
            }
            if (!jsonObject.isNull("minSwolfValue")) {
                sportHealth.setBestSWOLF(jsonObject.getInt("minSwolfValue"));
            }
            if (!jsonObject.isNull("maxSwolfValue")) {
                sportHealth.setMaxSwolf(jsonObject.getInt("maxSwolfValue"));
            }
            if (!jsonObject.isNull("avgSwolfValue")) {
                sportHealth.setAverageSWOLF(jsonObject.getInt("avgSwolfValue"));
            }
            if (!jsonObject.isNull("icon")) {
                sportHealth.setIcon(jsonObject.getString("icon"));
            }
            boolean z = true;
            if (!jsonObject.isNull("gps") && (jSONObject2 = jsonObject.getJSONObject("gps")) != null) {
                try {
                    SportGps sportGps = new SportGps();
                    sportGps.setInterval(jSONObject2.getInt("interval"));
                    String items = jSONObject2.getString("items");
                    Intrinsics.checkExpressionValueIsNotNull(items, "items");
                    if (!isNullString(items)) {
                        sportGps.setItems(items);
                    }
                    SportGpsData sportGpsData = new SportGpsData();
                    sportGpsData.setGpsData(sportGps);
                    sportGpsData.timeMillis = TimeUtil.convTimeYmdhmsToLong(sportHealth.getStartTime());
                    sportGpsData.setUserId(getUserId());
                    sportGpsData.setDown(true);
                    GreenDaoUtil.addAppGpsData(sportGpsData);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (!jsonObject.isNull("heartrate") && (jSONObject = jsonObject.getJSONObject("heartrate")) != null) {
                try {
                    SportItem sportItem = new SportItem();
                    sportItem.setInterval(jSONObject.getInt("interval"));
                    String itmes = jSONObject.getString("items");
                    Intrinsics.checkExpressionValueIsNotNull(itmes, "itmes");
                    if (!isNullString(itmes)) {
                        sportItem.setItmes(itmes);
                    }
                    sportHealth.setHeartrate(sportItem);
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
            if (!jsonObject.isNull("rate")) {
                JSONObject jSONObject3 = jsonObject.getJSONObject("rate");
                try {
                    SportItem sportItem2 = new SportItem();
                    sportItem2.setInterval(jSONObject3.getInt("interval"));
                    String itmes2 = jSONObject3.getString("items");
                    Intrinsics.checkExpressionValueIsNotNull(itmes2, "itmes");
                    if (!isNullString(itmes2)) {
                        sportItem2.setItmes(itmes2);
                    }
                    sportHealth.setRate(sportItem2);
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
            if (!jsonObject.isNull("range")) {
                JSONObject jSONObject4 = jsonObject.getJSONObject("range");
                try {
                    SportItem sportItem3 = new SportItem();
                    sportItem3.setInterval(jSONObject4.getInt("interval"));
                    String itmes3 = jSONObject4.getString("items");
                    Intrinsics.checkExpressionValueIsNotNull(itmes3, "itmes");
                    if (!isNullString(itmes3)) {
                        sportItem3.setItmes(itmes3);
                    }
                    sportHealth.setRange(sportItem3);
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
            }
            if (!jsonObject.isNull("pace")) {
                JSONObject jSONObject5 = jsonObject.getJSONObject("pace");
                try {
                    SportItemPace sportItemPace = new SportItemPace();
                    String metricItems = jSONObject5.getString("metricItems");
                    Intrinsics.checkExpressionValueIsNotNull(metricItems, "metricItems");
                    if (!isNullString(metricItems)) {
                        sportItemPace.setMetricItems(metricItems);
                    }
                    String britishItems = jSONObject5.getString("britishItems");
                    Intrinsics.checkExpressionValueIsNotNull(britishItems, "britishItems");
                    if (!isNullString(britishItems)) {
                        sportItemPace.setBritishItems(britishItems);
                    }
                    sportHealth.setPace(sportItemPace);
                } catch (Exception e6) {
                    e6.printStackTrace();
                }
            }
            if (!jsonObject.isNull("swolf")) {
                try {
                    String itemsContent = jsonObject.getJSONObject("swolf").getString("items");
                    Intrinsics.checkExpressionValueIsNotNull(itemsContent, "itemsContent");
                    if (!isNullString(itemsContent)) {
                        JSONArray jSONArray = new JSONArray(itemsContent);
                        if (jSONArray.length() > 0) {
                            SportSwimSwolf sportSwimSwolf = new SportSwimSwolf();
                            ArrayList arrayList = new ArrayList();
                            int length = jSONArray.length();
                            for (int i = 0; i < length; i++) {
                                JSONObject jSONObject6 = jSONArray.getJSONObject(i);
                                if (jSONObject6 != null) {
                                    SportSwimItem sportSwimItem = new SportSwimItem();
                                    sportSwimItem.duration = jSONObject6.getInt(d.C);
                                    sportSwimItem.strokesNumber = jSONObject6.getInt("strokesNumber");
                                    sportSwimItem.swolf = jSONObject6.getInt("swolf");
                                    sportSwimItem.frequency = jSONObject6.getInt("frequency");
                                    sportSwimItem.speed = jSONObject6.getInt("speed");
                                    sportSwimItem.stopTime = jSONObject6.getInt("stopTime");
                                    sportSwimItem.differenceTime = jSONObject6.getInt("differenceTime");
                                    arrayList.add(sportSwimItem);
                                }
                            }
                            sportSwimSwolf.setItems(GsonUtil.toJson(arrayList));
                            sportHealth.setSwolf(sportSwimSwolf);
                        }
                    }
                } catch (Exception e7) {
                    e7.printStackTrace();
                }
            }
            sportHealth.setUploaded(true);
            sportHealth.setUserId(getUserId());
            if (sportHealth.getGps() != null) {
                SportGps gps = sportHealth.getGps();
                Intrinsics.checkExpressionValueIsNotNull(gps, "item.gps");
                String items2 = gps.getItems();
                if (items2 != null && items2.length() != 0) {
                    z = false;
                }
                if (!z) {
                    GreenDaoUtil.saveAppGpsFromTrace(getUserId(), sportHealth.getGps(), sportHealth.getStartTime());
                }
            }
            GreenDaoUtil.saveActivityData(sportHealth);
            Map<Long, String> map = BaseLastestTaskListener.INSTANCE.getMRecentDateMap().get(getClass().getSimpleName());
            if (map == null) {
                Intrinsics.throwNpe();
            }
            Long lValueOf = Long.valueOf(getUserId());
            String dateTime2 = sportHealth.getDateTime();
            Intrinsics.checkExpressionValueIsNotNull(dateTime2, "item.dateTime");
            map.put(lValueOf, dateTime2);
        } catch (Exception e8) {
            e8.printStackTrace();
            printAndSaveLog("最近一条运动记录数据下拉ITEM转换失败 jsonObject=" + jsonObject + ",error=" + e8.getLocalizedMessage());
        }
    }
}