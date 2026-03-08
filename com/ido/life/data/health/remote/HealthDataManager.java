package com.ido.life.data.health.remote;

import com.google.android.gms.fitness.data.Field;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.net.BaseEntity;
import com.ido.common.net.BaseEntityNew;
import com.ido.life.bean.DistenceDetailUploadBean;
import com.ido.life.bean.ServerHeartRateDayUploadBean;
import com.ido.life.bean.UploadPressureBean;
import com.ido.life.data.api.HealthApi;
import com.ido.life.data.api.entity.UploadWeightBean;
import com.ido.life.data.listener.ApiCallback;
import com.ido.life.database.model.ActiveTimeDayData;
import com.ido.life.database.model.CalorieDayData;
import com.ido.life.database.model.HealthPressure;
import com.ido.life.database.model.ServerBloodOxyDayData;
import com.ido.life.database.model.ServerHeartRateDayData;
import com.ido.life.database.model.ServerMenstrual;
import com.ido.life.database.model.ServerMultiDaysBloodOxy;
import com.ido.life.database.model.ServerMultiDaysSleep;
import com.ido.life.database.model.ServerMultiMonthBloodOxy;
import com.ido.life.database.model.ServerMultiMonthSleep;
import com.ido.life.database.model.ServerSleepDayData;
import com.ido.life.database.model.SportDistanceBean;
import com.ido.life.database.model.StepDayData;
import com.ido.life.database.model.WalkDayData;
import com.ido.life.database.model.WeightItemBean;
import com.ido.life.database.model.middleModel.ActiveTimeEntity;
import com.ido.life.database.model.middleModel.BloodOxyDaysEntity;
import com.ido.life.database.model.middleModel.BloodOxyEntity;
import com.ido.life.database.model.middleModel.BloodOxyMonthEntity;
import com.ido.life.database.model.middleModel.CalorieEntity;
import com.ido.life.database.model.middleModel.HeartRateDateAreaEntity;
import com.ido.life.database.model.middleModel.HeartRateDateEntity;
import com.ido.life.database.model.middleModel.SleepDaysEntity;
import com.ido.life.database.model.middleModel.SleepEntity;
import com.ido.life.database.model.middleModel.SleepMonthEntity;
import com.ido.life.database.model.middleModel.StepEntity;
import com.ido.life.database.model.middleModel.WalkEntity;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/* JADX INFO: loaded from: classes2.dex */
public class HealthDataManager {
    private static final String TAG = "HealthDataManager";

    public interface OnHealthDataCallback<T> {
        void onFailed(int i, String str);

        void onSuccess(T t);
    }

    public static void requestDayStep(String str, final OnHealthDataCallback<StepDayData> onHealthDataCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("date", str);
        HealthApi.api.getDayStep(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<StepEntity>() { // from class: com.ido.life.data.health.remote.HealthDataManager.1
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(StepEntity stepEntity) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onSuccess(stepEntity == null ? null : stepEntity.result);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str2) {
                CommonLogUtil.d("onError = " + str2);
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onFailed(i, str2);
                }
            }
        });
    }

    public static void getHeartRateByDate(String str, final OnHealthDataCallback<ServerHeartRateDayData> onHealthDataCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("date", str);
        HealthApi.api.getHeartRateByDate(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<HeartRateDateEntity>() { // from class: com.ido.life.data.health.remote.HealthDataManager.2
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(HeartRateDateEntity heartRateDateEntity) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onSuccess(heartRateDateEntity == null ? null : heartRateDateEntity.result);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str2) {
                CommonLogUtil.d("onError = " + str2);
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onFailed(i, str2);
                }
            }
        });
    }

    public static void getHeartRateByDateArea(String str, String str2, final OnHealthDataCallback<List<ServerHeartRateDayData>> onHealthDataCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("start", str);
        jsonObject.addProperty("end", str2);
        HealthApi.api.getHeartRateByDateArea(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<HeartRateDateAreaEntity>() { // from class: com.ido.life.data.health.remote.HealthDataManager.3
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(HeartRateDateAreaEntity heartRateDateAreaEntity) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onSuccess(heartRateDateAreaEntity.result);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str3) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onFailed(i, str3);
                }
            }
        });
    }

    public static void requestDaySleep(String str, final OnHealthDataCallback<ServerSleepDayData> onHealthDataCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("date", str);
        HealthApi.api.getDaySleep(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<SleepEntity>() { // from class: com.ido.life.data.health.remote.HealthDataManager.4
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(SleepEntity sleepEntity) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onSuccess(sleepEntity.result);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str2) {
                CommonLogUtil.d("onError = " + str2);
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onFailed(i, str2);
                }
            }
        });
    }

    public static void requestDayCalorie(String str, final OnHealthDataCallback<CalorieDayData> onHealthDataCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("date", str);
        HealthApi.api.getDayCalorie(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<CalorieEntity>() { // from class: com.ido.life.data.health.remote.HealthDataManager.5
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(CalorieEntity calorieEntity) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onSuccess(calorieEntity == null ? null : calorieEntity.result);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str2) {
                CommonLogUtil.d("onError = " + str2);
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onFailed(i, str2);
                }
            }
        });
    }

    public static void requestMultiDaySleep(String str, String str2, final OnHealthDataCallback<ServerMultiDaysSleep> onHealthDataCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("start", str);
        jsonObject.addProperty("end", str2);
        HealthApi.api.getMultiDailySleep(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<SleepDaysEntity>() { // from class: com.ido.life.data.health.remote.HealthDataManager.6
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(SleepDaysEntity sleepDaysEntity) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onSuccess(sleepDaysEntity.result);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str3) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onFailed(i, str3);
                }
            }
        });
    }

    public static void requestMultiMonthSleep(String str, String str2, final OnHealthDataCallback<ServerMultiMonthSleep> onHealthDataCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("start", str);
        jsonObject.addProperty("end", str2);
        HealthApi.api.getMultiMonthSleep(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<SleepMonthEntity>() { // from class: com.ido.life.data.health.remote.HealthDataManager.7
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(SleepMonthEntity sleepMonthEntity) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onSuccess(sleepMonthEntity.result);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str3) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onFailed(i, str3);
                }
            }
        });
    }

    public static void uploadStepData(final List<StepDayData> list, final OnHealthDataCallback<List<StepDayData>> onHealthDataCallback) {
        JsonArray jsonArray = new JsonArray();
        for (StepDayData stepDayData : list) {
            if (stepDayData != null) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("date", stepDayData.getDate());
                jsonObject.addProperty("distances", Integer.valueOf(stepDayData.getDistances()));
                jsonObject.addProperty("numSteps", Integer.valueOf(stepDayData.getNumSteps()));
                jsonObject.addProperty(Field.NUTRIENT_CALORIES, Integer.valueOf(stepDayData.getCalories()));
                jsonObject.addProperty("timeOfSeconds", Integer.valueOf(stepDayData.getTimeOfSeconds()));
                jsonObject.addProperty("sourceMac", stepDayData.getSourceMac());
                jsonObject.addProperty("deviceName", stepDayData.getDeviceName());
                jsonObject.addProperty("effectiveSteps", Integer.valueOf(stepDayData.getEffectiveSteps()));
                jsonObject.addProperty("maxNumSteps", Integer.valueOf(stepDayData.getMaxNumSteps()));
                jsonObject.addProperty("items", stepDayData.getItems());
                jsonObject.addProperty("timestamp", Long.valueOf(stepDayData.getTimeStamp()));
                jsonArray.add(jsonObject);
            }
        }
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.add("datas", jsonArray);
        CommonLogUtil.d("uploadStepData  json = " + jsonObject2.toString());
        HealthApi.api.uploadStepData(RequestBody.create(MediaType.parse("application/json"), jsonObject2.toString())).enqueue(new ApiCallback<BaseEntity>() { // from class: com.ido.life.data.health.remote.HealthDataManager.8
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntity baseEntity) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onSuccess(list);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onFailed(i, str);
                }
            }
        });
    }

    public static void uploadHeartRateData(final List<ServerHeartRateDayData> list, final OnHealthDataCallback<List<ServerHeartRateDayData>> onHealthDataCallback) {
        if (list == null || list.size() == 0) {
            return;
        }
        for (ServerHeartRateDayData serverHeartRateDayData : list) {
            String items = serverHeartRateDayData.getItems();
            for (int i = 0; i < 1000; i++) {
                items.concat(items);
            }
            serverHeartRateDayData.setItems(items);
        }
        HealthApi.api.uploadHeartRateData(new ServerHeartRateDayUploadBean(list)).enqueue(new ApiCallback<BaseEntity>() { // from class: com.ido.life.data.health.remote.HealthDataManager.9
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntity baseEntity) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onSuccess(list);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i2, String str) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onFailed(i2, str);
                }
            }
        });
    }

    public static void uploadSleepData(final List<ServerSleepDayData> list, final OnHealthDataCallback<List<ServerSleepDayData>> onHealthDataCallback) {
        JsonArray jsonArray = new JsonArray();
        for (ServerSleepDayData serverSleepDayData : list) {
            if (serverSleepDayData != null) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("date", serverSleepDayData.getDate());
                jsonObject.addProperty("awakeSeconds", Integer.valueOf(serverSleepDayData.getAwakeSeconds()));
                jsonObject.addProperty("lightlySeconds", Integer.valueOf(serverSleepDayData.getLightlySeconds()));
                jsonObject.addProperty("deeplySeconds", Integer.valueOf(serverSleepDayData.getDeeplySeconds()));
                jsonObject.addProperty("eyeMovementSeconds", Integer.valueOf(serverSleepDayData.getEyeMovementSeconds()));
                jsonObject.addProperty("startTime", serverSleepDayData.getStartTime());
                jsonObject.addProperty("endTime", serverSleepDayData.getEndTime());
                jsonObject.addProperty("sourceMac", serverSleepDayData.getSourceMac());
                jsonObject.addProperty("deviceName", serverSleepDayData.getDeviceName());
                jsonObject.addProperty("items", serverSleepDayData.getItems());
                jsonObject.addProperty("totalSeconds", Integer.valueOf(serverSleepDayData.getTotalSeconds()));
                jsonObject.addProperty("awakeRatio", Integer.valueOf(serverSleepDayData.getAwakeRatio()));
                jsonObject.addProperty("lightlyRatio", Integer.valueOf(serverSleepDayData.getLightlyRatio()));
                jsonObject.addProperty("deeplyRatio", Integer.valueOf(serverSleepDayData.getDeeplyRatio()));
                jsonObject.addProperty("eyeMovementRatio", Integer.valueOf(serverSleepDayData.getEyeMovementRatio()));
                jsonObject.addProperty("score", Integer.valueOf(serverSleepDayData.getScore()));
                jsonObject.addProperty("breathRate", Integer.valueOf(serverSleepDayData.getBreathRate()));
                jsonObject.addProperty("timestamp", Long.valueOf(serverSleepDayData.getTimestamp()));
                jsonArray.add(jsonObject);
            }
        }
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.add("datas", jsonArray);
        CommonLogUtil.d("uploadSleepData  json = " + jsonObject2.toString());
        HealthApi.api.uploadSleepData(RequestBody.create(MediaType.parse("application/json"), jsonObject2.toString())).enqueue(new ApiCallback<BaseEntity>() { // from class: com.ido.life.data.health.remote.HealthDataManager.10
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntity baseEntity) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onSuccess(list);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onFailed(i, str);
                }
            }
        });
    }

    public static void uploadCalorieData(final List<CalorieDayData> list, final OnHealthDataCallback<List<CalorieDayData>> onHealthDataCallback) {
        JsonArray jsonArray = new JsonArray();
        for (CalorieDayData calorieDayData : list) {
            if (calorieDayData != null) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("date", calorieDayData.getDate());
                jsonObject.addProperty("totalCalorie", Integer.valueOf(calorieDayData.getTotalCalorie()));
                jsonObject.addProperty("activityCalorie", Integer.valueOf(calorieDayData.getActivityCalorie()));
                jsonObject.addProperty("items", calorieDayData.getItems());
                jsonObject.addProperty("activityItems", calorieDayData.getActivityItems());
                jsonObject.addProperty("sourceMac", calorieDayData.getSourceMac());
                jsonObject.addProperty("deviceName", calorieDayData.getDeviceName());
                jsonObject.addProperty("timestamp", Long.valueOf(calorieDayData.getTimestamp()));
                jsonArray.add(jsonObject);
            }
        }
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.add("datas", jsonArray);
        HealthApi.api.uploadCalorieData(RequestBody.create(MediaType.parse("application/json"), jsonObject2.toString())).enqueue(new ApiCallback<BaseEntity>() { // from class: com.ido.life.data.health.remote.HealthDataManager.11
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntity baseEntity) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onSuccess(list);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onFailed(i, str);
                }
            }
        });
    }

    public static void uploadActiveTimeData(final List<ActiveTimeDayData> list, final OnHealthDataCallback<List<ActiveTimeDayData>> onHealthDataCallback) {
        JsonArray jsonArray = new JsonArray();
        for (ActiveTimeDayData activeTimeDayData : list) {
            if (activeTimeDayData != null) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("date", activeTimeDayData.getDate());
                jsonObject.addProperty("totalSeconds", Integer.valueOf(activeTimeDayData.getTotalSeconds()));
                jsonObject.addProperty("totalDistance", Integer.valueOf(activeTimeDayData.getTotalDistance()));
                jsonObject.addProperty("mediumOrHigherSeconds", Integer.valueOf(activeTimeDayData.getMediumOrHigherSeconds()));
                jsonObject.addProperty("sourceMac", activeTimeDayData.getSourceMac());
                jsonObject.addProperty("deviceName", activeTimeDayData.getDeviceName());
                jsonObject.addProperty("items", activeTimeDayData.getItems());
                jsonObject.addProperty("timestamp", Long.valueOf(activeTimeDayData.getTimestamp()));
                jsonArray.add(jsonObject);
            }
        }
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.add("datas", jsonArray);
        CommonLogUtil.d("uploadActiveTimeData  json = " + jsonObject2.toString());
        HealthApi.api.uploadActiveTimeData(RequestBody.create(MediaType.parse("application/json"), jsonObject2.toString())).enqueue(new ApiCallback<BaseEntity>() { // from class: com.ido.life.data.health.remote.HealthDataManager.12
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntity baseEntity) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onSuccess(list);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onFailed(i, str);
                }
            }
        });
    }

    public static void requestDayActiveTime(String str, final OnHealthDataCallback<ActiveTimeDayData> onHealthDataCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("date", str);
        HealthApi.api.getDayActiveTime(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<ActiveTimeEntity>() { // from class: com.ido.life.data.health.remote.HealthDataManager.13
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(ActiveTimeEntity activeTimeEntity) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onSuccess(activeTimeEntity.result);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str2) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onFailed(i, str2);
                }
            }
        });
    }

    public static void requestDayBloodOxygen(String str, final OnHealthDataCallback<ServerBloodOxyDayData> onHealthDataCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("date", str);
        HealthApi.api.getDayBloodOxy(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<BloodOxyEntity>() { // from class: com.ido.life.data.health.remote.HealthDataManager.14
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BloodOxyEntity bloodOxyEntity) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onSuccess(bloodOxyEntity.result);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str2) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onFailed(i, str2);
                }
            }
        });
    }

    public static void requestMultiDayBloodOxygen(String str, String str2, final OnHealthDataCallback<ServerMultiDaysBloodOxy> onHealthDataCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("start", str);
        jsonObject.addProperty("end", str2);
        HealthApi.api.getMultiDailyBloodOxy(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<BloodOxyDaysEntity>() { // from class: com.ido.life.data.health.remote.HealthDataManager.15
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BloodOxyDaysEntity bloodOxyDaysEntity) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onSuccess(bloodOxyDaysEntity.result);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str3) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onFailed(i, str3);
                }
            }
        });
    }

    public static void requestMultiMonthBloodOxy(String str, String str2, final OnHealthDataCallback<ServerMultiMonthBloodOxy> onHealthDataCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("start", str);
        jsonObject.addProperty("end", str2);
        HealthApi.api.getMultiMonthBloodOxy(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<BloodOxyMonthEntity>() { // from class: com.ido.life.data.health.remote.HealthDataManager.16
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BloodOxyMonthEntity bloodOxyMonthEntity) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onSuccess(bloodOxyMonthEntity.result);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str3) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onFailed(i, str3);
                }
            }
        });
    }

    public static void uploadBloodOxyData(final List<ServerBloodOxyDayData> list, final OnHealthDataCallback<List<ServerBloodOxyDayData>> onHealthDataCallback) {
        JsonArray jsonArray = new JsonArray();
        for (ServerBloodOxyDayData serverBloodOxyDayData : list) {
            if (serverBloodOxyDayData != null) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("date", serverBloodOxyDayData.getDate());
                jsonObject.addProperty("items", serverBloodOxyDayData.getItems());
                jsonObject.addProperty("maxValue", Integer.valueOf(serverBloodOxyDayData.getMaxValue()));
                jsonObject.addProperty("minValue", Integer.valueOf(serverBloodOxyDayData.getMinValue()));
                jsonObject.addProperty("avgValue", Integer.valueOf(serverBloodOxyDayData.getAvgValue()));
                jsonObject.addProperty("sourceMac", serverBloodOxyDayData.getSourceMac());
                jsonObject.addProperty("measurementTimes", Integer.valueOf(serverBloodOxyDayData.getMeasurementTimes()));
                jsonObject.addProperty("timestamp", Long.valueOf(serverBloodOxyDayData.getTimestamp()));
                jsonArray.add(jsonObject);
            }
        }
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.add("datas", jsonArray);
        CommonLogUtil.d("uploadBloodOxyData  json = " + jsonObject2.toString());
        HealthApi.api.uploadBloodOxyData(RequestBody.create(MediaType.parse("application/json"), jsonObject2.toString())).enqueue(new ApiCallback<BaseEntity>() { // from class: com.ido.life.data.health.remote.HealthDataManager.17
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntity baseEntity) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onSuccess(list);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onFailed(i, str);
                }
            }
        });
    }

    public static void uploadWeight(UploadWeightBean uploadWeightBean, final OnHealthDataCallback<BaseEntity> onHealthDataCallback) {
        HealthApi.api.uploadWeight(uploadWeightBean).enqueue(new ApiCallback<BaseEntity>() { // from class: com.ido.life.data.health.remote.HealthDataManager.18
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntity baseEntity) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onSuccess(baseEntity);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onFailed(i, str);
                }
            }
        });
    }

    public static void getWeightByDateArea(String str, String str2, final OnHealthDataCallback<BaseEntityNew<List<WeightItemBean>>> onHealthDataCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("start", str);
        jsonObject.addProperty("end", str2);
        HealthApi.api.getWeightByDateArea(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<BaseEntityNew<List<WeightItemBean>>>() { // from class: com.ido.life.data.health.remote.HealthDataManager.19
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntityNew<List<WeightItemBean>> baseEntityNew) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onSuccess(baseEntityNew);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str3) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onFailed(i, str3);
                }
            }
        });
    }

    public static void getWeightByDate(String str, final OnHealthDataCallback<BaseEntityNew<WeightItemBean>> onHealthDataCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("date", str);
        HealthApi.api.getWeightByDate(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<BaseEntityNew<WeightItemBean>>() { // from class: com.ido.life.data.health.remote.HealthDataManager.20
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntityNew<WeightItemBean> baseEntityNew) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onSuccess(baseEntityNew);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str2) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onFailed(i, str2);
                }
            }
        });
    }

    public static void uploadPressure(UploadPressureBean uploadPressureBean, final OnHealthDataCallback<BaseEntity> onHealthDataCallback) {
        HealthApi.api.uploadPressure(uploadPressureBean).enqueue(new ApiCallback<BaseEntity>() { // from class: com.ido.life.data.health.remote.HealthDataManager.21
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntity baseEntity) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onSuccess(baseEntity);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onFailed(i, str);
                }
            }
        });
    }

    public static void getPresureDetailByDateArea(String str, String str2, final OnHealthDataCallback<BaseEntityNew<List<HealthPressure>>> onHealthDataCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("start", str);
        jsonObject.addProperty("end", str2);
        HealthApi.api.getPresureDetailByDateArea(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<BaseEntityNew<List<HealthPressure>>>() { // from class: com.ido.life.data.health.remote.HealthDataManager.22
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntityNew<List<HealthPressure>> baseEntityNew) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onSuccess(baseEntityNew);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str3) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onFailed(i, str3);
                }
            }
        });
    }

    public static void getPresureDetailByDate(String str, final OnHealthDataCallback<HealthPressure> onHealthDataCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("date", str);
        HealthApi.api.getPresureDetailByDate(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<BaseEntityNew<HealthPressure>>() { // from class: com.ido.life.data.health.remote.HealthDataManager.23
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntityNew<HealthPressure> baseEntityNew) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onSuccess(baseEntityNew == null ? null : baseEntityNew.getResult());
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str2) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onFailed(i, str2);
                }
            }
        });
    }

    public static void uploadDistanceDetail(DistenceDetailUploadBean distenceDetailUploadBean, final OnHealthDataCallback<BaseEntity> onHealthDataCallback) {
        HealthApi.api.uploadDistanceDetail(distenceDetailUploadBean).enqueue(new ApiCallback<BaseEntity>() { // from class: com.ido.life.data.health.remote.HealthDataManager.24
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntity baseEntity) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onSuccess(baseEntity);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onFailed(i, str);
                }
            }
        });
    }

    public static void getDistanceDetailByDateArea(String str, String str2, final OnHealthDataCallback<BaseEntityNew<List<SportDistanceBean>>> onHealthDataCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("start", str);
        jsonObject.addProperty("end", str2);
        HealthApi.api.getDistanceDetailByDateArea(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<BaseEntityNew<List<SportDistanceBean>>>() { // from class: com.ido.life.data.health.remote.HealthDataManager.25
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntityNew<List<SportDistanceBean>> baseEntityNew) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onSuccess(baseEntityNew);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str3) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onFailed(i, str3);
                }
            }
        });
    }

    public static void getDistanceDetailByDate(String str, final OnHealthDataCallback<SportDistanceBean> onHealthDataCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("date", str);
        HealthApi.api.getDistanceDetailByDate(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<BaseEntityNew<SportDistanceBean>>() { // from class: com.ido.life.data.health.remote.HealthDataManager.26
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntityNew<SportDistanceBean> baseEntityNew) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onSuccess(baseEntityNew == null ? null : baseEntityNew.getResult());
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str2) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onFailed(i, str2);
                }
            }
        });
    }

    public static void uploadWalkData(final List<WalkDayData> list, final OnHealthDataCallback<List<WalkDayData>> onHealthDataCallback) {
        JsonArray jsonArray = new JsonArray();
        for (WalkDayData walkDayData : list) {
            if (walkDayData != null) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("date", walkDayData.getDate());
                jsonObject.addProperty("targetSteps", Integer.valueOf(walkDayData.getTargetSteps()));
                jsonObject.addProperty("reachSeconds", Integer.valueOf(walkDayData.getReachSeconds()));
                jsonObject.addProperty("startTime", walkDayData.getStartTime());
                jsonObject.addProperty("endTime", walkDayData.getEndTime());
                jsonObject.addProperty("items", walkDayData.getItems());
                jsonObject.addProperty("sourceMac", walkDayData.getSourceMac());
                jsonObject.addProperty("deviceName", walkDayData.getDeviceName());
                jsonObject.addProperty("timestamp", Long.valueOf(walkDayData.getTimestamp()));
                jsonArray.add(jsonObject);
            }
        }
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.add("datas", jsonArray);
        CommonLogUtil.d("uploadWalkData  json = " + jsonObject2.toString());
        HealthApi.api.uploadWalkData(RequestBody.create(MediaType.parse("application/json"), jsonObject2.toString())).enqueue(new ApiCallback<BaseEntity>() { // from class: com.ido.life.data.health.remote.HealthDataManager.27
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntity baseEntity) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onSuccess(list);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onFailed(i, str);
                }
            }
        });
    }

    public static void requestDayWalkData(String str, final OnHealthDataCallback<WalkDayData> onHealthDataCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("date", str);
        HealthApi.api.getDayWalkData(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<WalkEntity>() { // from class: com.ido.life.data.health.remote.HealthDataManager.28
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(WalkEntity walkEntity) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onSuccess(walkEntity.result);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str2) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onFailed(i, str2);
                }
            }
        });
    }

    public static void getStepByDateArea(String str, String str2, final OnHealthDataCallback<List<StepDayData>> onHealthDataCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("start", str);
        jsonObject.addProperty("end", str2);
        HealthApi.api.getStepByDateArea(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<BaseEntityNew<List<StepDayData>>>() { // from class: com.ido.life.data.health.remote.HealthDataManager.29
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntityNew<List<StepDayData>> baseEntityNew) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onSuccess(baseEntityNew.getResult());
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str3) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onFailed(i, str3);
                }
            }
        });
    }

    public static void clearHealthData(RequestBody requestBody, final OnHealthDataCallback<BaseEntity> onHealthDataCallback) {
        HealthApi.api.clearHealthData(requestBody).enqueue(new ApiCallback<BaseEntity>() { // from class: com.ido.life.data.health.remote.HealthDataManager.30
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntity baseEntity) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onSuccess(baseEntity);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onFailed(i, str);
                }
            }
        });
    }

    public static void uploadMenstrualData(ServerMenstrual serverMenstrual, final OnHealthDataCallback<Boolean> onHealthDataCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("date", serverMenstrual.getDate());
        jsonObject.addProperty("latestDate", serverMenstrual.getLatestDate());
        jsonObject.addProperty("mensesCycle", Integer.valueOf(serverMenstrual.getMensesCycle()));
        jsonObject.addProperty("mensesDays", Integer.valueOf(serverMenstrual.getMensesDays()));
        jsonObject.addProperty("sourceMac", serverMenstrual.getSourceMac());
        jsonObject.addProperty("deviceName", serverMenstrual.getDeviceName());
        jsonObject.addProperty("items", serverMenstrual.getItems());
        jsonObject.addProperty("timestamp", Long.valueOf(serverMenstrual.getTimestamp()));
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(jsonObject);
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.add("datas", jsonArray);
        CommonLogUtil.d("uploadMenstrualData ：" + jsonObject2.toString());
        HealthApi.api.uploadMenstrual(RequestBody.create(MediaType.parse("application/json"), jsonObject2.toString())).enqueue(new ApiCallback<BaseEntityNew<Boolean>>() { // from class: com.ido.life.data.health.remote.HealthDataManager.31
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntityNew<Boolean> baseEntityNew) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onSuccess(baseEntityNew.getResult());
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnHealthDataCallback onHealthDataCallback2 = onHealthDataCallback;
                if (onHealthDataCallback2 != null) {
                    onHealthDataCallback2.onFailed(i, str);
                }
            }
        });
    }
}