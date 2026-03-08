package com.ido.life.syncdownload;

import androidx.core.app.NotificationCompat;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.life.database.model.ServerSleepDayData;
import com.ido.life.net.BaseUrl;
import com.ido.life.syncdownload.NewTask;
import com.ido.life.syncdownload.Task;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import java.util.Date;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: SleepLastestTaskListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\u0010\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\u0000H\u0016J\u001c\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\nH\u0016J\u0012\u0010\u0014\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\u0012\u0010\u0015\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002¨\u0006\u0016"}, d2 = {"Lcom/ido/life/syncdownload/SleepLastestTaskListener;", "Lcom/ido/life/syncdownload/BaseLastestTaskListener;", "userId", "", "(J)V", "convertJsonObjectToEntity", "", "jsonObject", "Lorg/json/JSONObject;", "getTaskTag", "", "kotlin.jvm.PlatformType", "getTaskType", "getTaskUrlPath", "newInstance", "onSingleTaskSuccess", "", "taskInfo", "Lcom/ido/life/syncdownload/Task$TaskInfo;", FirebaseAnalytics.Param.CONTENT, "resolveMultiSleep", "resolveSingleSleep", "app_release"}, k = 1, mv = {1, 1, 16})
public final class SleepLastestTaskListener extends BaseLastestTaskListener {
    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskType() {
        return BaseUrl.URL_NAME_HEALTH;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskUrlPath() {
        return "api/sleep/data/last1days";
    }

    public SleepLastestTaskListener(long j) {
        super(j);
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskTag() {
        return ServerSleepDayData.class.getSimpleName();
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public SleepLastestTaskListener newInstance() {
        return new SleepLastestTaskListener(getUserId());
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
            sb.append("最近一条睡眠数据下拉成功taskInfo=");
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
                JSONArray jSONArray = jSONObject.getJSONArray("result");
                int length = jSONArray.length();
                printAndSaveLog("最近一条睡眠数据总条数 dataTotalCount=" + length);
                if (length > 0) {
                    for (int i = 0; i < length; i++) {
                        convertJsonObjectToEntity(jSONArray.getJSONObject(i));
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            printAndSaveLog("最近一条睡眠数据下拉转JSON失败 content=" + content + ",error=" + e2.getLocalizedMessage());
        }
        return true;
    }

    private final void convertJsonObjectToEntity(JSONObject jsonObject) {
        if (jsonObject == null || jsonObject.isNull("dataSize")) {
            return;
        }
        if (jsonObject.getInt("dataSize") == 0) {
            resolveSingleSleep(jsonObject);
        } else {
            resolveMultiSleep(jsonObject);
        }
    }

    private final void resolveMultiSleep(JSONObject jsonObject) {
        String str;
        long j;
        String str2;
        int i;
        JSONArray jSONArray;
        String str3;
        String str4;
        String str5;
        String str6 = "startTime";
        String str7 = "items";
        if (jsonObject == null) {
            return;
        }
        try {
            int i2 = jsonObject.getInt("dataSize");
            JSONArray jSONArray2 = jsonObject.getJSONArray(AeUtil.ROOT_DATA_PATH_OLD_NAME);
            if (jSONArray2.length() != i2) {
                return;
            }
            String string = (String) null;
            String string2 = (String) null;
            String str8 = (String) null;
            if (!jsonObject.isNull("date")) {
                string = jsonObject.getString("date");
            }
            String str9 = string;
            String str10 = str9;
            if (str10 == null || StringsKt.isBlank(str10)) {
                return;
            }
            if (!jsonObject.isNull("sourceMac")) {
                string2 = jsonObject.getString("sourceMac");
            }
            String str11 = string2;
            String string3 = !jsonObject.isNull("deviceName") ? jsonObject.getString("deviceName") : str8;
            if (jsonObject.isNull("timestamp")) {
                str = string3;
                j = 0;
            } else {
                str = string3;
                j = jsonObject.getLong("timestamp");
            }
            if (j <= 0) {
                return;
            }
            String str12 = "totalSeconds";
            List<ServerSleepDayData> listQuerySleepDailyDataByDate = GreenDaoUtil.querySleepDailyDataByDate(getUserId(), str9);
            List<ServerSleepDayData> list = listQuerySleepDailyDataByDate;
            if (!(list == null || list.isEmpty())) {
                ServerSleepDayData serverSleepDayData = listQuerySleepDailyDataByDate.get(0);
                Intrinsics.checkExpressionValueIsNotNull(serverSleepDayData, "localList[0]");
                if (serverSleepDayData.getTimestamp() > j) {
                    return;
                }
                int size = listQuerySleepDailyDataByDate.size();
                for (int i3 = 0; i3 < size; i3++) {
                    listQuerySleepDailyDataByDate.get(i3).delete();
                }
            }
            int i4 = 0;
            while (i4 < i2) {
                JSONObject jSONObject = jSONArray2.getJSONObject(i4);
                if (jSONObject != null) {
                    ServerSleepDayData serverSleepDayData2 = new ServerSleepDayData();
                    if (jSONObject.isNull(str7)) {
                        i = i2;
                    } else {
                        i = i2;
                        serverSleepDayData2.setItems(jSONObject.getString(str7));
                    }
                    String items = serverSleepDayData2.getItems();
                    str2 = str7;
                    Intrinsics.checkExpressionValueIsNotNull(items, "sleepDayData.items");
                    if (isNullString(items)) {
                        return;
                    }
                    jSONArray = jSONArray2;
                    serverSleepDayData2.setUserId(getUserId());
                    if (!jSONObject.isNull("awakeSeconds")) {
                        serverSleepDayData2.setAwakeSeconds(jSONObject.getInt("awakeSeconds"));
                    }
                    if (!jSONObject.isNull("lightlySeconds")) {
                        serverSleepDayData2.setLightlySeconds(jSONObject.getInt("lightlySeconds"));
                    }
                    if (!jSONObject.isNull("deeplySeconds")) {
                        serverSleepDayData2.setDeeplySeconds(jSONObject.getInt("deeplySeconds"));
                    }
                    if (!jSONObject.isNull("eyeMovementSeconds")) {
                        serverSleepDayData2.setEyeMovementSeconds(jSONObject.getInt("eyeMovementSeconds"));
                    }
                    str3 = str12;
                    if (!jSONObject.isNull(str3)) {
                        serverSleepDayData2.setTotalSeconds(jSONObject.getInt(str3));
                    }
                    if (!jSONObject.isNull(str6)) {
                        serverSleepDayData2.setStartTime(jSONObject.getString(str6));
                    }
                    String startTime = serverSleepDayData2.getStartTime();
                    str4 = str6;
                    Intrinsics.checkExpressionValueIsNotNull(startTime, "sleepDayData.startTime");
                    if (isNullString(startTime)) {
                        return;
                    }
                    if (!jSONObject.isNull("endTime")) {
                        serverSleepDayData2.setEndTime(jSONObject.getString("endTime"));
                    }
                    String endTime = serverSleepDayData2.getEndTime();
                    Intrinsics.checkExpressionValueIsNotNull(endTime, "sleepDayData.endTime");
                    if (isNullString(endTime)) {
                        return;
                    }
                    if (!jSONObject.isNull("awakeRatio")) {
                        serverSleepDayData2.setAwakeRatio(jSONObject.getInt("awakeRatio"));
                    }
                    if (!jSONObject.isNull("lightlyRatio")) {
                        serverSleepDayData2.setLightlyRatio(jSONObject.getInt("lightlyRatio"));
                    }
                    if (!jSONObject.isNull("deeplyRatio")) {
                        serverSleepDayData2.setDeeplyRatio(jSONObject.getInt("deeplyRatio"));
                    }
                    if (!jSONObject.isNull("eyeMovementRatio")) {
                        serverSleepDayData2.setEyeMovementRatio(jSONObject.getInt("eyeMovementRatio"));
                    }
                    if (!jSONObject.isNull("score")) {
                        serverSleepDayData2.setScore(jSONObject.getInt("score"));
                    }
                    if (!jSONObject.isNull("breathRate")) {
                        serverSleepDayData2.setBreathRate(jSONObject.getInt("breathRate"));
                    }
                    if (!jSONObject.isNull("type")) {
                        serverSleepDayData2.setType(jSONObject.getInt("type"));
                    }
                    serverSleepDayData2.setSourceMac(str11);
                    str5 = str;
                    serverSleepDayData2.setDeviceName(str5);
                    Date startDate = DateUtil.string2Date(serverSleepDayData2.getStartTime(), "yyyy-MM-dd HH:mm:ss");
                    Intrinsics.checkExpressionValueIsNotNull(startDate, "startDate");
                    serverSleepDayData2.setStartTimeMinuteOffset((startDate.getHours() * 60) + startDate.getMinutes());
                    Date endDate = DateUtil.string2Date(serverSleepDayData2.getEndTime(), "yyyy-MM-dd HH:mm:ss");
                    Intrinsics.checkExpressionValueIsNotNull(endDate, "endDate");
                    serverSleepDayData2.setEndTimeMinuteOffset((endDate.getHours() * 60) + endDate.getMinutes());
                    serverSleepDayData2.setUploaded(true);
                    serverSleepDayData2.setDate(str9);
                    serverSleepDayData2.setTimestamp(j);
                    GreenDaoUtil.insertSleep(serverSleepDayData2);
                } else {
                    str2 = str7;
                    i = i2;
                    jSONArray = jSONArray2;
                    str3 = str12;
                    str4 = str6;
                    str5 = str;
                }
                i4++;
                str = str5;
                jSONArray2 = jSONArray;
                str6 = str4;
                str7 = str2;
                str12 = str3;
                i2 = i;
            }
            Map<Long, String> map = BaseLastestTaskListener.INSTANCE.getMRecentDateMap().get(getClass().getSimpleName());
            if (map == null) {
                Intrinsics.throwNpe();
            }
            map.put(Long.valueOf(getUserId()), str9);
        } catch (Exception e2) {
            printAndSaveLog("解析多段睡眠数据,ITEM转换失败 jsonObject=" + jsonObject + ",error=" + e2.getLocalizedMessage());
        }
    }

    private final void resolveSingleSleep(JSONObject jsonObject) {
        if (jsonObject == null) {
            return;
        }
        try {
            String string = (String) null;
            String string2 = (String) null;
            String str = (String) null;
            if (!jsonObject.isNull("date")) {
                string = jsonObject.getString("date");
            }
            String str2 = string;
            if (str2 == null || StringsKt.isBlank(str2)) {
                return;
            }
            if (!jsonObject.isNull("sourceMac")) {
                string2 = jsonObject.getString("sourceMac");
            }
            String string3 = !jsonObject.isNull("deviceName") ? jsonObject.getString("deviceName") : str;
            long j = !jsonObject.isNull("timestamp") ? jsonObject.getLong("timestamp") : 0L;
            if (j <= 0) {
                return;
            }
            String str3 = string3;
            List<ServerSleepDayData> listQuerySleepDailyDataByDate = GreenDaoUtil.querySleepDailyDataByDate(getUserId(), string);
            List<ServerSleepDayData> list = listQuerySleepDailyDataByDate;
            if (!(list == null || list.isEmpty())) {
                ServerSleepDayData serverSleepDayData = listQuerySleepDailyDataByDate.get(0);
                Intrinsics.checkExpressionValueIsNotNull(serverSleepDayData, "localList[0]");
                if (serverSleepDayData.getTimestamp() > j) {
                    return;
                }
                int size = listQuerySleepDailyDataByDate.size();
                for (int i = 0; i < size; i++) {
                    listQuerySleepDailyDataByDate.get(i).delete();
                }
            }
            ServerSleepDayData serverSleepDayData2 = new ServerSleepDayData();
            if (!jsonObject.isNull("items")) {
                serverSleepDayData2.setItems(jsonObject.getString("items"));
            }
            String items = serverSleepDayData2.getItems();
            Intrinsics.checkExpressionValueIsNotNull(items, "sleepDayData.items");
            if (isNullString(items)) {
                return;
            }
            long j2 = j;
            serverSleepDayData2.setUserId(getUserId());
            if (!jsonObject.isNull("awakeSeconds")) {
                serverSleepDayData2.setAwakeSeconds(jsonObject.getInt("awakeSeconds"));
            }
            if (!jsonObject.isNull("lightlySeconds")) {
                serverSleepDayData2.setLightlySeconds(jsonObject.getInt("lightlySeconds"));
            }
            if (!jsonObject.isNull("deeplySeconds")) {
                serverSleepDayData2.setDeeplySeconds(jsonObject.getInt("deeplySeconds"));
            }
            if (!jsonObject.isNull("eyeMovementSeconds")) {
                serverSleepDayData2.setEyeMovementSeconds(jsonObject.getInt("eyeMovementSeconds"));
            }
            if (!jsonObject.isNull("totalSeconds")) {
                serverSleepDayData2.setTotalSeconds(jsonObject.getInt("totalSeconds"));
            }
            if (!jsonObject.isNull("startTime")) {
                serverSleepDayData2.setStartTime(jsonObject.getString("startTime"));
            }
            String startTime = serverSleepDayData2.getStartTime();
            Intrinsics.checkExpressionValueIsNotNull(startTime, "sleepDayData.startTime");
            if (isNullString(startTime)) {
                return;
            }
            if (!jsonObject.isNull("endTime")) {
                serverSleepDayData2.setEndTime(jsonObject.getString("endTime"));
            }
            String endTime = serverSleepDayData2.getEndTime();
            Intrinsics.checkExpressionValueIsNotNull(endTime, "sleepDayData.endTime");
            if (isNullString(endTime)) {
                return;
            }
            if (!jsonObject.isNull("awakeRatio")) {
                serverSleepDayData2.setAwakeRatio(jsonObject.getInt("awakeRatio"));
            }
            if (!jsonObject.isNull("lightlyRatio")) {
                serverSleepDayData2.setLightlyRatio(jsonObject.getInt("lightlyRatio"));
            }
            if (!jsonObject.isNull("deeplyRatio")) {
                serverSleepDayData2.setDeeplyRatio(jsonObject.getInt("deeplyRatio"));
            }
            if (!jsonObject.isNull("eyeMovementRatio")) {
                serverSleepDayData2.setEyeMovementRatio(jsonObject.getInt("eyeMovementRatio"));
            }
            if (!jsonObject.isNull("score")) {
                serverSleepDayData2.setScore(jsonObject.getInt("score"));
            }
            if (!jsonObject.isNull("breathRate")) {
                serverSleepDayData2.setBreathRate(jsonObject.getInt("breathRate"));
            }
            if (!jsonObject.isNull("type")) {
                serverSleepDayData2.setType(jsonObject.getInt("type"));
            }
            serverSleepDayData2.setSourceMac(string2);
            serverSleepDayData2.setDeviceName(str3);
            Date startDate = DateUtil.string2Date(serverSleepDayData2.getStartTime(), "yyyy-MM-dd HH:mm:ss");
            Intrinsics.checkExpressionValueIsNotNull(startDate, "startDate");
            serverSleepDayData2.setStartTimeMinuteOffset((startDate.getHours() * 60) + startDate.getMinutes());
            Date endDate = DateUtil.string2Date(serverSleepDayData2.getEndTime(), "yyyy-MM-dd HH:mm:ss");
            Intrinsics.checkExpressionValueIsNotNull(endDate, "endDate");
            serverSleepDayData2.setEndTimeMinuteOffset((endDate.getHours() * 60) + endDate.getMinutes());
            serverSleepDayData2.setUploaded(true);
            serverSleepDayData2.setDate(string);
            serverSleepDayData2.setTimestamp(j2);
            GreenDaoUtil.insertSleep(serverSleepDayData2);
            Map<Long, String> map = BaseLastestTaskListener.INSTANCE.getMRecentDateMap().get(getClass().getSimpleName());
            if (map == null) {
                Intrinsics.throwNpe();
            }
            Long lValueOf = Long.valueOf(getUserId());
            String date = serverSleepDayData2.getDate();
            Intrinsics.checkExpressionValueIsNotNull(date, "sleepDayData.date");
            map.put(lValueOf, date);
        } catch (Exception e2) {
            printAndSaveLog("解析单段睡眠数据,ITEM转换失败 jsonObject=" + jsonObject + ",error=" + e2.getLocalizedMessage());
        }
    }
}