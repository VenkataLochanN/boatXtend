package com.ido.life.syncdownload;

import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ido.life.database.model.HealthPressure;
import com.ido.life.enums.PressureEnum;
import com.ido.life.net.BaseUrl;
import com.ido.life.syncdownload.NewTask;
import com.ido.life.syncdownload.Task;
import com.ido.life.util.GreenDaoUtil;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: PressureLastestTaskListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\u0010\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\u0000H\u0016J\u001c\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u0014"}, d2 = {"Lcom/ido/life/syncdownload/PressureLastestTaskListener;", "Lcom/ido/life/syncdownload/BaseLastestTaskListener;", "userId", "", "(J)V", "convertJsonObjectToEntity", "", "jsonObject", "Lorg/json/JSONObject;", "getTaskTag", "", "kotlin.jvm.PlatformType", "getTaskType", "getTaskUrlPath", "newInstance", "onSingleTaskSuccess", "", "taskInfo", "Lcom/ido/life/syncdownload/Task$TaskInfo;", FirebaseAnalytics.Param.CONTENT, "app_release"}, k = 1, mv = {1, 1, 16})
public final class PressureLastestTaskListener extends BaseLastestTaskListener {
    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskType() {
        return BaseUrl.URL_NAME_HEALTH;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskUrlPath() {
        return "api/pressure/data/last1days";
    }

    public PressureLastestTaskListener(long j) {
        super(j);
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskTag() {
        return HealthPressure.class.getSimpleName();
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public PressureLastestTaskListener newInstance() {
        return new PressureLastestTaskListener(getUserId());
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
            sb.append("最近一条压力数据下拉成功taskInfo=");
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
            printAndSaveLog("最近一条压力数据下拉转JSON失败content=" + content + ",error=" + e2.getLocalizedMessage());
        }
        return true;
    }

    private final void convertJsonObjectToEntity(JSONObject jsonObject) {
        if (jsonObject == null) {
            return;
        }
        try {
            HealthPressure healthPressure = new HealthPressure();
            if (!jsonObject.isNull("date")) {
                healthPressure.setDate(jsonObject.getString("date"));
            }
            String date = healthPressure.getDate();
            Intrinsics.checkExpressionValueIsNotNull(date, "item.date");
            if (isNullString(date)) {
                return;
            }
            if (!jsonObject.isNull("items")) {
                healthPressure.setItems(jsonObject.getString("items"));
            }
            String items = healthPressure.getItems();
            Intrinsics.checkExpressionValueIsNotNull(items, "item.items");
            if (isNullString(items)) {
                return;
            }
            if (!jsonObject.isNull("maxValue")) {
                healthPressure.setMaxPressure(jsonObject.getInt("maxValue"));
            }
            if (healthPressure.getMaxPressure() <= PressureEnum.HIGH.Max && healthPressure.getMaxPressure() >= PressureEnum.RELAX.Min) {
                if (!jsonObject.isNull("sourceMac")) {
                    healthPressure.setSourceMac(jsonObject.getString("sourceMac"));
                }
                if (!jsonObject.isNull("minValue")) {
                    healthPressure.setMinPressure(jsonObject.getInt("minValue"));
                }
                if (healthPressure.getMinPressure() <= PressureEnum.HIGH.Max && healthPressure.getMinPressure() >= PressureEnum.RELAX.Min) {
                    if (!jsonObject.isNull("avgValue")) {
                        healthPressure.setAvgPressure(jsonObject.getInt("avgValue"));
                    }
                    if (healthPressure.getAvgPressure() <= PressureEnum.HIGH.Max && healthPressure.getAvgPressure() >= PressureEnum.RELAX.Min) {
                        if (!jsonObject.isNull("latestValue")) {
                            healthPressure.setLastestPressure(jsonObject.getInt("latestValue"));
                        }
                        if (healthPressure.getLastestPressure() <= PressureEnum.HIGH.Max && healthPressure.getLastestPressure() >= PressureEnum.RELAX.Min) {
                            if (!jsonObject.isNull("timestamp")) {
                                healthPressure.setTimeStamp(jsonObject.getLong("timestamp"));
                            }
                            if (healthPressure.getTimeStamp() <= 0) {
                                return;
                            }
                            HealthPressure healthPressureDetailByDay = GreenDaoUtil.getHealthPressureDetailByDay(getUserId(), healthPressure.getDate());
                            if (healthPressureDetailByDay != null) {
                                if (healthPressureDetailByDay.getTimeStamp() > healthPressure.getTimeStamp()) {
                                    return;
                                } else {
                                    healthPressureDetailByDay.delete();
                                }
                            }
                            healthPressure.setUserId(getUserId());
                            if (!jsonObject.isNull("relaxRatio")) {
                                healthPressure.setRelaxRatio(jsonObject.getInt("relaxRatio"));
                            }
                            if (!jsonObject.isNull("normalRatio")) {
                                healthPressure.setNormalRatio(jsonObject.getInt("normalRatio"));
                            }
                            if (!jsonObject.isNull("mediumRatio")) {
                                healthPressure.setMediumRatio(jsonObject.getInt("mediumRatio"));
                            }
                            if (!jsonObject.isNull("higherRatio")) {
                                healthPressure.setHigherRatio(jsonObject.getInt("higherRatio"));
                            }
                            if (!jsonObject.isNull("relaxCount")) {
                                healthPressure.setRelaxCount(jsonObject.getInt("relaxCount"));
                            }
                            if (!jsonObject.isNull("normalCount")) {
                                healthPressure.setNormalCount(jsonObject.getInt("normalCount"));
                            }
                            if (!jsonObject.isNull("mediumCount")) {
                                healthPressure.setMediumCount(jsonObject.getInt("mediumCount"));
                            }
                            if (!jsonObject.isNull("higherCount")) {
                                healthPressure.setHigherCount(jsonObject.getInt("higherCount"));
                            }
                            if (healthPressure.getRelaxCount() + healthPressure.getNormalCount() + healthPressure.getMediumCount() + healthPressure.getHigherCount() == 0) {
                                try {
                                    List list = (List) new Gson().fromJson(healthPressure.getItems(), new TypeToken<List<? extends List<? extends Integer>>>() { // from class: com.ido.life.syncdownload.PressureLastestTaskListener$convertJsonObjectToEntity$itemsList$1
                                    }.getType());
                                    List list2 = list;
                                    if (!(list2 == null || list2.isEmpty())) {
                                        int size = list.size();
                                        for (int i = 0; i < size; i++) {
                                            try {
                                                int iIntValue = ((Number) ((List) list.get(i)).get(1)).intValue();
                                                if (iIntValue >= PressureEnum.HIGH.Min) {
                                                    healthPressure.setHigherCount(healthPressure.getHigherCount() + 1);
                                                } else if (iIntValue >= PressureEnum.MEDIUM.Min) {
                                                    healthPressure.setMediumCount(healthPressure.getMediumCount() + 1);
                                                } else if (iIntValue >= PressureEnum.NORMAL.Min) {
                                                    healthPressure.setNormalCount(healthPressure.getNormalCount() + 1);
                                                } else {
                                                    healthPressure.setRelaxCount(healthPressure.getRelaxCount() + 1);
                                                }
                                            } catch (Exception e2) {
                                                e2.printStackTrace();
                                                printAndSaveLog("日期(" + healthPressure.getDate() + ")的压力Item读取失败 error=" + e2.getLocalizedMessage());
                                            }
                                        }
                                        int higherCount = healthPressure.getHigherCount() + healthPressure.getMediumCount() + healthPressure.getNormalCount() + healthPressure.getRelaxCount();
                                        if (higherCount > 0) {
                                            float f2 = higherCount;
                                            healthPressure.setHigherRatio(MathKt.roundToInt((healthPressure.getHigherCount() * 100.0f) / f2));
                                            healthPressure.setMediumRatio(MathKt.roundToInt((healthPressure.getMediumCount() * 100.0f) / f2));
                                            healthPressure.setNormalRatio(MathKt.roundToInt((healthPressure.getNormalCount() * 100.0f) / f2));
                                            healthPressure.setRelaxRatio(((100 - healthPressure.getHigherRatio()) - healthPressure.getMediumRatio()) - healthPressure.getNormalRatio());
                                        }
                                    }
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                    printAndSaveLog("日期(" + healthPressure.getDate() + ")的压力Item转集合失败 error=" + e3.getLocalizedMessage());
                                }
                            }
                            if (!jsonObject.isNull("deviceName")) {
                                healthPressure.setDeviceName(jsonObject.getString("deviceName"));
                            }
                            healthPressure.setUploadSuccess(true);
                            healthPressure.setLoadDetail(true);
                            GreenDaoUtil.addPressureDayData(healthPressure);
                            Map<Long, String> map = BaseLastestTaskListener.INSTANCE.getMRecentDateMap().get(getClass().getSimpleName());
                            if (map == null) {
                                Intrinsics.throwNpe();
                            }
                            Long lValueOf = Long.valueOf(getUserId());
                            String date2 = healthPressure.getDate();
                            Intrinsics.checkExpressionValueIsNotNull(date2, "item.date");
                            map.put(lValueOf, date2);
                        }
                    }
                }
            }
        } catch (Exception e4) {
            printAndSaveLog("最近一条压力数据下拉ITEM转换失败 jsonObject=" + jsonObject + ",error=" + e4.getLocalizedMessage());
        }
    }
}