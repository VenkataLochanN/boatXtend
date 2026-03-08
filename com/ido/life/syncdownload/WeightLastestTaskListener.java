package com.ido.life.syncdownload;

import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.life.database.model.UserInfo;
import com.ido.life.database.model.WeightItemBean;
import com.ido.life.enums.WeightBmiEnum;
import com.ido.life.net.BaseUrl;
import com.ido.life.syncdownload.Task;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: WeightLastestTaskListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\u0010\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fH\u0016J\b\u0010\u000e\u001a\u00020\fH\u0016J\b\u0010\u000f\u001a\u00020\fH\u0016J\b\u0010\u0010\u001a\u00020\u0000H\u0016J\b\u0010\u0011\u001a\u00020\u0006H\u0016J\u001c\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\u0017"}, d2 = {"Lcom/ido/life/syncdownload/WeightLastestTaskListener;", "Lcom/ido/life/syncdownload/BaseLastestTaskListener;", "userId", "", "(J)V", "convertJsonObjectToEntity", "", "height", "", "jsonObject", "Lorg/json/JSONObject;", "getTaskTag", "", "kotlin.jvm.PlatformType", "getTaskType", "getTaskUrlPath", "newInstance", "onAllTaskComplete", "onSingleTaskSuccess", "", "taskInfo", "Lcom/ido/life/syncdownload/Task$TaskInfo;", FirebaseAnalytics.Param.CONTENT, "app_release"}, k = 1, mv = {1, 1, 16})
public final class WeightLastestTaskListener extends BaseLastestTaskListener {
    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskType() {
        return BaseUrl.URL_NAME_HEALTH;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskUrlPath() {
        return "api/weight/data/last3days";
    }

    public WeightLastestTaskListener(long j) {
        super(j);
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskTag() {
        return WeightItemBean.class.getSimpleName();
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public WeightLastestTaskListener newInstance() {
        return new WeightLastestTaskListener(getUserId());
    }

    @Override // com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
    public void onAllTaskComplete() {
        printAndSaveLog("最近一条体重下拉任务执行完成");
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(getUserId());
        if (userInfoQueryUserInfo != null) {
            float heightCm = userInfoQueryUserInfo.getHeightCm();
            float weightKg = userInfoQueryUserInfo.getWeightKg();
            WeightItemBean weightItemBeanQueryNewestWeightRecord = GreenDaoUtil.queryNewestWeightRecord(getUserId());
            if (weightItemBeanQueryNewestWeightRecord == null || weightKg == weightItemBeanQueryNewestWeightRecord.getTotalWeight()) {
                return;
            }
            WeightItemBean weightItemBean = new WeightItemBean();
            weightItemBean.setTotalWeight(weightKg);
            weightItemBean.setBmi(WeightBmiEnum.caluteBmi(weightKg, heightCm));
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
            weightItemBean.setDate(DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_YMD));
            weightItemBean.setTimeStamp(calendar.getTimeInMillis());
            weightItemBean.setUploadSuccess(false);
            weightItemBean.setUserId(getUserId());
            weightItemBean.setLoadDetail(true);
            GreenDaoUtil.addWeight(weightItemBean);
        }
    }

    @Override // com.ido.life.syncdownload.Task.Listenter
    public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String content) {
        UserInfo userInfoQueryUserInfo;
        Map<Long, String> map = BaseLastestTaskListener.INSTANCE.getMRecentDateMap().get(getClass().getSimpleName());
        if (map == null) {
            Intrinsics.throwNpe();
        }
        map.put(Long.valueOf(getUserId()), "");
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("最近一条用户体重修改记录数据下拉成功 taskInfo=");
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
            if (jSONObject.getInt(NotificationCompat.CATEGORY_STATUS) == 200 && !jSONObject.isNull("result") && (userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(getUserId())) != null) {
                float heightCm = userInfoQueryUserInfo.getHeightCm();
                JSONArray jSONArray = jSONObject.getJSONArray("result");
                int length = jSONArray.length();
                if (length > 0) {
                    for (int i = 0; i < length; i++) {
                        convertJsonObjectToEntity(heightCm, jSONArray.getJSONObject(i));
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            printAndSaveLog("最近一条用户体重修改记录数据下拉转JSON失败 content=" + content + ",error=" + e2.getLocalizedMessage());
        }
        return true;
    }

    private final void convertJsonObjectToEntity(float height, JSONObject jsonObject) {
        if (jsonObject == null) {
            return;
        }
        try {
            WeightItemBean weightItemBean = new WeightItemBean();
            if (!jsonObject.isNull("date")) {
                weightItemBean.setDate(jsonObject.getString("date"));
            }
            String date = weightItemBean.getDate();
            Intrinsics.checkExpressionValueIsNotNull(date, "item.date");
            if (isNullString(date)) {
                return;
            }
            if (!jsonObject.isNull("totalWeight")) {
                weightItemBean.setTotalWeight((float) jsonObject.getDouble("totalWeight"));
            }
            if (weightItemBean.getTotalWeight() >= 10 && weightItemBean.getTotalWeight() <= 250) {
                if (!jsonObject.isNull("timestamp")) {
                    weightItemBean.setTimeStamp(jsonObject.getLong("timestamp"));
                }
                if (weightItemBean.getTimeStamp() <= 0) {
                    return;
                }
                WeightItemBean weightItemBeanQueryWeightByDate = GreenDaoUtil.queryWeightByDate(getUserId(), weightItemBean.getDate());
                if (weightItemBeanQueryWeightByDate != null) {
                    if (weightItemBeanQueryWeightByDate.getTimeStamp() > weightItemBean.getTimeStamp()) {
                        return;
                    } else {
                        weightItemBeanQueryWeightByDate.delete();
                    }
                }
                weightItemBean.setBmi(WeightBmiEnum.caluteBmi(weightItemBean.getTotalWeight(), height));
                weightItemBean.setUserId(getUserId());
                weightItemBean.setUploadSuccess(true);
                weightItemBean.setLoadDetail(true);
                GreenDaoUtil.addWeight(weightItemBean);
                Map<Long, String> map = BaseLastestTaskListener.INSTANCE.getMRecentDateMap().get(getClass().getSimpleName());
                if (map == null) {
                    Intrinsics.throwNpe();
                }
                Long lValueOf = Long.valueOf(getUserId());
                String date2 = weightItemBean.getDate();
                Intrinsics.checkExpressionValueIsNotNull(date2, "item.date");
                map.put(lValueOf, date2);
            }
        } catch (Exception e2) {
            printAndSaveLog("最近一条用户体重修改记录数据下拉ITEM转换失败 jsonObject=" + jsonObject + ",error=" + e2.getLocalizedMessage());
        }
    }
}