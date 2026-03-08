package com.ido.life.syncdownload;

import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ido.common.utils.GsonUtil;
import com.ido.life.database.model.SportDistanceBean;
import com.ido.life.net.BaseUrl;
import com.ido.life.syncdownload.NewTask;
import com.ido.life.syncdownload.Task;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.HealthDataUtil;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: DistanceLastestTaskListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\u0010\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\u0000H\u0016J\u001c\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u0014"}, d2 = {"Lcom/ido/life/syncdownload/DistanceLastestTaskListener;", "Lcom/ido/life/syncdownload/BaseLastestTaskListener;", "userId", "", "(J)V", "convertJsonObjectToEntity", "", "jsonObject", "Lorg/json/JSONObject;", "getTaskTag", "", "kotlin.jvm.PlatformType", "getTaskType", "getTaskUrlPath", "newInstance", "onSingleTaskSuccess", "", "taskInfo", "Lcom/ido/life/syncdownload/Task$TaskInfo;", FirebaseAnalytics.Param.CONTENT, "app_release"}, k = 1, mv = {1, 1, 16})
public final class DistanceLastestTaskListener extends BaseLastestTaskListener {
    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskType() {
        return BaseUrl.URL_NAME_HEALTH;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskUrlPath() {
        return "api/distance/data/last1days";
    }

    public DistanceLastestTaskListener(long j) {
        super(j);
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskTag() {
        return SportDistanceBean.class.getSimpleName();
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public DistanceLastestTaskListener newInstance() {
        return new DistanceLastestTaskListener(getUserId());
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
            sb.append("最近一条距离数据下拉成功taskInfo=");
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
            printAndSaveLog("最近一条距离数据下拉转JSON失败content=" + content + ",error=" + e2.getLocalizedMessage());
        }
        return true;
    }

    private final void convertJsonObjectToEntity(JSONObject jsonObject) {
        if (jsonObject == null) {
            return;
        }
        try {
            SportDistanceBean sportDistanceBean = new SportDistanceBean();
            if (!jsonObject.isNull("date")) {
                sportDistanceBean.setDate(jsonObject.getString("date"));
            }
            String date = sportDistanceBean.getDate();
            Intrinsics.checkExpressionValueIsNotNull(date, "item.date");
            if (isNullString(date)) {
                return;
            }
            if (!jsonObject.isNull("distances")) {
                sportDistanceBean.setTotalDistance((float) jsonObject.getDouble("distances"));
            }
            if (!jsonObject.isNull("items")) {
                sportDistanceBean.setOrignalItems(jsonObject.getString("items"));
            }
            String orignalItems = sportDistanceBean.getOrignalItems();
            Intrinsics.checkExpressionValueIsNotNull(orignalItems, "item.orignalItems");
            if (isNullString(orignalItems)) {
                return;
            }
            if (!jsonObject.isNull("sourceMac")) {
                sportDistanceBean.setSourceMac(jsonObject.getString("sourceMac"));
            }
            if (!jsonObject.isNull("timestamp")) {
                sportDistanceBean.setTimeStamp(jsonObject.getLong("timestamp"));
            }
            if (sportDistanceBean.getTimeStamp() <= 0) {
                return;
            }
            List list = (List) new Gson().fromJson(sportDistanceBean.getOrignalItems(), new TypeToken<List<Integer>>() { // from class: com.ido.life.syncdownload.DistanceLastestTaskListener$convertJsonObjectToEntity$itemList$1
            }.getType());
            List list2 = list;
            if (!(list2 == null || list2.isEmpty())) {
                sportDistanceBean.setItems(GsonUtil.toJson(HealthDataUtil.convertDistanceToHourItem(CollectionsKt.toIntArray(list), 15)));
            }
            SportDistanceBean sportDistanceBeanQuerySportDistanceByDate = GreenDaoUtil.querySportDistanceByDate(getUserId(), sportDistanceBean.getDate());
            if (sportDistanceBeanQuerySportDistanceByDate != null) {
                if (sportDistanceBeanQuerySportDistanceByDate.getTimeStamp() > sportDistanceBean.getTimeStamp()) {
                    return;
                } else {
                    sportDistanceBeanQuerySportDistanceByDate.delete();
                }
            }
            if (!jsonObject.isNull("deviceName")) {
                sportDistanceBean.setDeviceName(jsonObject.getString("deviceName"));
            }
            sportDistanceBean.setUserId(getUserId());
            sportDistanceBean.setUploadSuccess(true);
            sportDistanceBean.setLoadDetail(true);
            GreenDaoUtil.addSportDistance(sportDistanceBean);
            Map<Long, String> map = BaseLastestTaskListener.INSTANCE.getMRecentDateMap().get(getClass().getSimpleName());
            if (map == null) {
                Intrinsics.throwNpe();
            }
            Long lValueOf = Long.valueOf(getUserId());
            String date2 = sportDistanceBean.getDate();
            Intrinsics.checkExpressionValueIsNotNull(date2, "item.date");
            map.put(lValueOf, date2);
        } catch (Exception e2) {
            printAndSaveLog("最近一条距离数据下拉ITEM转换失败 jsonObject=" + jsonObject + ",error=" + e2.getLocalizedMessage());
        }
    }
}