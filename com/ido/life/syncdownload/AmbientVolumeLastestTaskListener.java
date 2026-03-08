package com.ido.life.syncdownload;

import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.life.database.model.HealthVolumeData;
import com.ido.life.net.BaseUrl;
import com.ido.life.syncdownload.NewTask;
import com.ido.life.syncdownload.Task;
import com.ido.life.util.GreenDaoUtil;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: AmbientVolumeLastestTaskListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\u0000H\u0016J\u001c\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u0014"}, d2 = {"Lcom/ido/life/syncdownload/AmbientVolumeLastestTaskListener;", "Lcom/ido/life/syncdownload/BaseLastestTaskListener;", "userId", "", "(J)V", "convertJsonObjectToEntity", "", "jsonObject", "Lorg/json/JSONObject;", "getTaskTag", "", "kotlin.jvm.PlatformType", "getTaskType", "getTaskUrlPath", "newInstance", "onSingleTaskSuccess", "", "taskInfo", "Lcom/ido/life/syncdownload/Task$TaskInfo;", FirebaseAnalytics.Param.CONTENT, "app_release"}, k = 1, mv = {1, 1, 16})
public final class AmbientVolumeLastestTaskListener extends BaseLastestTaskListener {
    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskType() {
        return BaseUrl.URL_NAME_HEALTH;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskUrlPath() {
        return "api/noise/data/last1days";
    }

    public AmbientVolumeLastestTaskListener(long j) {
        super(j);
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskTag() {
        return HealthVolumeData.class.getSimpleName();
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public AmbientVolumeLastestTaskListener newInstance() {
        return new AmbientVolumeLastestTaskListener(getUserId());
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
            sb.append("最近一条环境音量数据下拉成功 taskInfo=");
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
            if (Intrinsics.areEqual(jSONObject2.get(NotificationCompat.CATEGORY_STATUS), (Object) 200) && !jSONObject2.isNull("result") && (taskInfo instanceof NewTask.NewTaskInfo) && (jSONObject = jSONObject2.getJSONArray("result").getJSONObject(0)) != null) {
                convertJsonObjectToEntity(jSONObject);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            printAndSaveLog("最近一条环境音量数据下拉转JSON失败content=" + content + ",error=" + e2.getLocalizedMessage());
        }
        return true;
    }

    private final void convertJsonObjectToEntity(JSONObject jsonObject) {
        try {
            HealthVolumeData healthVolumeData = new HealthVolumeData();
            if (!jsonObject.isNull("date")) {
                healthVolumeData.setDate(jsonObject.getString("date"));
            }
            String date = healthVolumeData.getDate();
            Intrinsics.checkExpressionValueIsNotNull(date, "item.date");
            if (isNullString(date)) {
                return;
            }
            if (!jsonObject.isNull("sourceMac")) {
                healthVolumeData.setSourceMac(jsonObject.getString("sourceMac"));
            }
            if (!jsonObject.isNull("deviceName")) {
                healthVolumeData.setDeviceName(jsonObject.getString("deviceName"));
            }
            if (!jsonObject.isNull("items")) {
                healthVolumeData.setItems(jsonObject.getString("items"));
            }
            String items = healthVolumeData.getItems();
            Intrinsics.checkExpressionValueIsNotNull(items, "item.items");
            if (isNullString(items)) {
                return;
            }
            if (!jsonObject.isNull("timestamp")) {
                healthVolumeData.timestamp = jsonObject.getLong("timestamp");
            }
            if (healthVolumeData.timestamp <= 0) {
                return;
            }
            HealthVolumeData healthVolumeDate = GreenDaoUtil.getHealthVolumeDate(getUserId(), healthVolumeData.getDate());
            if (healthVolumeDate != null) {
                if (healthVolumeDate.timestamp >= healthVolumeData.timestamp) {
                    return;
                } else {
                    healthVolumeDate.delete();
                }
            }
            if (!jsonObject.isNull("maxValue")) {
                healthVolumeData.setMaxValue(jsonObject.getInt("maxValue"));
            }
            if (!jsonObject.isNull("minValue")) {
                healthVolumeData.setMinValue(jsonObject.getInt("minValue"));
            }
            if (!jsonObject.isNull("latestValue")) {
                healthVolumeData.setLatestValue(jsonObject.getInt("latestValue"));
            }
            if (!jsonObject.isNull("avgValue")) {
                healthVolumeData.setAvgValue(jsonObject.getInt("avgValue"));
            }
            if (!jsonObject.isNull("interval")) {
                healthVolumeData.setInterval(jsonObject.getInt("interval"));
            }
            if (!jsonObject.isNull("totalSeconds")) {
                healthVolumeData.setTotalSeconds(jsonObject.getInt("totalSeconds"));
            }
            if (!jsonObject.isNull("superHighLevelSeconds")) {
                healthVolumeData.setSuperHighLevelSeconds(jsonObject.getInt("superHighLevelSeconds"));
            }
            if (!jsonObject.isNull("highLevelSeconds")) {
                healthVolumeData.setHighLevelSeconds(jsonObject.getInt("highLevelSeconds"));
            }
            if (!jsonObject.isNull("normalLevelSeconds")) {
                healthVolumeData.setNormalLevelSeconds(jsonObject.getInt("normalLevelSeconds"));
            }
            if (!jsonObject.isNull("lowLevelSeconds")) {
                healthVolumeData.setLowLevelSeconds(jsonObject.getInt("lowLevelSeconds"));
            }
            healthVolumeData.setHasUpdate(true);
            healthVolumeData.setUserId(getUserId());
            GreenDaoUtil.saveHealthVolumeData(healthVolumeData);
            Map<Long, String> map = BaseLastestTaskListener.INSTANCE.getMRecentDateMap().get(getClass().getSimpleName());
            if (map == null) {
                Intrinsics.throwNpe();
            }
            Long lValueOf = Long.valueOf(getUserId());
            String date2 = healthVolumeData.getDate();
            Intrinsics.checkExpressionValueIsNotNull(date2, "item.date");
            map.put(lValueOf, date2);
        } catch (Exception e2) {
            printAndSaveLog("最近一条环境音量数据下拉ITEM转换失败 jsonObject=" + jsonObject + ",error=" + e2.getLocalizedMessage());
        }
    }
}