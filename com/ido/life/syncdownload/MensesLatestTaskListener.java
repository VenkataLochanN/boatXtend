package com.ido.life.syncdownload;

import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ido.life.database.model.LifeCycleItemBean;
import com.ido.life.database.model.MenstruationConfig;
import com.ido.life.module.home.HomeHelperKt;
import com.ido.life.net.BaseUrl;
import com.ido.life.syncdownload.NewTask;
import com.ido.life.syncdownload.Task;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: MensesLatestTaskListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\u0000H\u0016J\b\u0010\u000e\u001a\u00020\u0006H\u0016J\u001c\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u0014"}, d2 = {"Lcom/ido/life/syncdownload/MensesLatestTaskListener;", "Lcom/ido/life/syncdownload/BaseLastestTaskListener;", "userId", "", "(J)V", "convertJsonObjectToEntity", "", "jsonObject", "Lorg/json/JSONObject;", "getTaskTag", "", "getTaskType", "getTaskUrlPath", "newInstance", "onAllTaskComplete", "onSingleTaskSuccess", "", "taskInfo", "Lcom/ido/life/syncdownload/Task$TaskInfo;", FirebaseAnalytics.Param.CONTENT, "app_release"}, k = 1, mv = {1, 1, 16})
public final class MensesLatestTaskListener extends BaseLastestTaskListener {
    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskType() {
        return BaseUrl.URL_NAME_HEALTH;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskUrlPath() {
        return "api/menstrual/v2/data/last1days";
    }

    public MensesLatestTaskListener(long j) {
        super(j);
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskTag() {
        String simpleName = LifeCycleItemBean.class.getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "LifeCycleItemBean::class.java.simpleName");
        return simpleName;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public MensesLatestTaskListener newInstance() {
        return new MensesLatestTaskListener(getUserId());
    }

    @Override // com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
    public void onAllTaskComplete() {
        super.onAllTaskComplete();
        LifeCycleItemBean lifeCycleItemBeanQueryLatestLifeCycle = HomeHelperKt.queryLatestLifeCycle(getUserId());
        if (lifeCycleItemBeanQueryLatestLifeCycle == null || GreenDaoUtil.queryMenstruationConfig(getUserId()) != null) {
            return;
        }
        MenstruationConfig menstruationConfig = new MenstruationConfig();
        menstruationConfig.setUserId(getUserId());
        menstruationConfig.setMensLength(lifeCycleItemBeanQueryLatestLifeCycle.getMensesDays());
        menstruationConfig.setMensCycle(lifeCycleItemBeanQueryLatestLifeCycle.getMensesCycle());
        menstruationConfig.setUploadSuccess(false);
        Calendar calendar = Calendar.getInstance();
        try {
            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
            calendar.setTime(DateUtil.string2Date(lifeCycleItemBeanQueryLatestLifeCycle.getMonth(), DateUtil.DATE_FORMAT_YM_3));
            List<List<Integer>> itemList = lifeCycleItemBeanQueryLatestLifeCycle.getItemList();
            Intrinsics.checkExpressionValueIsNotNull(itemList, "lifecycle.itemList");
            Object objLast = CollectionsKt.last((List<? extends Object>) itemList);
            Intrinsics.checkExpressionValueIsNotNull(objLast, "lifecycle.itemList.last()");
            Object objFirst = CollectionsKt.first((List<? extends Object>) objLast);
            Intrinsics.checkExpressionValueIsNotNull(objFirst, "lifecycle.itemList.last().first()");
            calendar.set(5, ((Number) objFirst).intValue());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        menstruationConfig.setUpdateTimeStamp(calendar.getTimeInMillis());
        GreenDaoUtil.addMenstruationConfig(menstruationConfig);
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
            sb.append("最近一条生理周期数据下拉成功taskInfo=");
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
                if (jSONArray.length() > 0) {
                    convertJsonObjectToEntity(jSONArray.getJSONObject(0));
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            printAndSaveLog("最近一条生理周期数据下拉转JSON失败 content=" + content + ",error=" + e2.getLocalizedMessage());
        }
        return true;
    }

    private final void convertJsonObjectToEntity(JSONObject jsonObject) {
        if (jsonObject == null) {
            return;
        }
        try {
            String string = jsonObject.getString("month");
            long j = jsonObject.getLong("timestamp");
            LifeCycleItemBean lifeCycleItemBeanQueryLifeCycleItemBeanByDate = GreenDaoUtil.queryLifeCycleItemBeanByDate(getUserId(), string);
            if (lifeCycleItemBeanQueryLifeCycleItemBeanByDate != null) {
                if (lifeCycleItemBeanQueryLifeCycleItemBeanByDate.getTimeStamp() > j) {
                    printAndSaveLog("本地的生理周期数据时间戳比服务器上面的时间戳新,优先使用本地的数据");
                    return;
                }
                lifeCycleItemBeanQueryLifeCycleItemBeanByDate.delete();
            }
            LifeCycleItemBean lifeCycleItemBean = new LifeCycleItemBean();
            lifeCycleItemBean.setMonth(string);
            lifeCycleItemBean.setMensesCycle(jsonObject.getInt("mensesCycle"));
            lifeCycleItemBean.setMensesDays(jsonObject.getInt("mensesDays"));
            lifeCycleItemBean.setSourceMac(jsonObject.getString("sourceMac"));
            lifeCycleItemBean.setDeviceName(jsonObject.getString("deviceName"));
            lifeCycleItemBean.setItemList((List) new Gson().fromJson(jsonObject.getString("items"), new TypeToken<List<? extends List<? extends Integer>>>() { // from class: com.ido.life.syncdownload.MensesLatestTaskListener.convertJsonObjectToEntity.1
            }.getType()));
            List<List<Integer>> itemList = lifeCycleItemBean.getItemList();
            if (itemList == null || itemList.isEmpty()) {
                return;
            }
            lifeCycleItemBean.setTimeStamp(j);
            lifeCycleItemBean.setMensesStartDay(jsonObject.getInt("mensesStartDay"));
            lifeCycleItemBean.setOvulationDay(jsonObject.getInt("ovulationDay"));
            lifeCycleItemBean.setPregnancyDayBeforeRemind(jsonObject.getInt("pregnancyDayBeforeRemind"));
            lifeCycleItemBean.setReminderTime(jsonObject.getString("reminderTime"));
            GreenDaoUtil.insertLifeCycle(lifeCycleItemBean);
            Map<Long, String> map = BaseLastestTaskListener.INSTANCE.getMRecentDateMap().get(getClass().getSimpleName());
            if (map == null) {
                Intrinsics.throwNpe();
            }
            Long lValueOf = Long.valueOf(getUserId());
            String month = lifeCycleItemBean.getMonth();
            Intrinsics.checkExpressionValueIsNotNull(month, "item.month");
            map.put(lValueOf, month);
        } catch (Exception e2) {
            e2.printStackTrace();
            printAndSaveLog("最近一条生理周期数据下拉ITEM转换失败 jsonObject=" + jsonObject + ",error=" + e2.getLocalizedMessage());
        }
    }
}