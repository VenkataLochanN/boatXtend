package com.ido.life.syncdownload;

import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.life.constants.Constants;
import com.ido.life.database.model.UserMedalInfo;
import com.ido.life.net.BaseUrl;
import com.ido.life.net.BaseUrlInterceptor;
import com.ido.life.syncdownload.NewTask;
import com.ido.life.syncdownload.Task;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.eventbus.EventBusHelper;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: UserMedalTaskListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004JL\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b2\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b2\u0006\u0010\u000e\u001a\u00020\bH\u0016J\u0010\u0010\u000f\u001a\n \u0010*\u0004\u0018\u00010\f0\fH\u0016J\b\u0010\u0011\u001a\u00020\fH\u0016J\b\u0010\u0012\u001a\u00020\fH\u0016J\b\u0010\u0013\u001a\u00020\u0000H\u0016J\u001c\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\u0019"}, d2 = {"Lcom/ido/life/syncdownload/UserMedalTaskListener;", "Lcom/ido/life/syncdownload/BaseDataDownloadTaskListener;", "userId", "", "(J)V", "getTask", "Lcom/ido/life/syncdownload/NewTask;", "groupId", "", "stateId", "params", "", "", "headerMap", "taskPriority", "getTaskTag", "kotlin.jvm.PlatformType", "getTaskType", "getTaskUrlPath", "newInstance", "onSingleTaskSuccess", "", "taskInfo", "Lcom/ido/life/syncdownload/Task$TaskInfo;", FirebaseAnalytics.Param.CONTENT, "app_release"}, k = 1, mv = {1, 1, 16})
public final class UserMedalTaskListener extends BaseDataDownloadTaskListener {
    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskType() {
        return BaseUrl.URL_NAME_USER;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskUrlPath() {
        return "userapi/medal/get";
    }

    public UserMedalTaskListener(long j) {
        super(j, 0, 2, null);
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskTag() {
        return UserMedalInfo.class.getSimpleName();
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public UserMedalTaskListener newInstance() {
        return new UserMedalTaskListener(getUserId());
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public NewTask getTask(int groupId, long stateId, Map<String, String> params, Map<String, String> headerMap, int taskPriority) {
        String str = BaseUrlInterceptor.formatBasUrl(getTaskType()) + getTaskUrlPath();
        String taskTag = getTaskTag();
        Intrinsics.checkExpressionValueIsNotNull(taskTag, "getTaskTag()");
        return new NewTask(new NewTask.NewTaskInfo(new NewTask.Builder(str, taskTag).post().bodys(params).headers(headerMap).groupId(groupId).stateId(stateId)), this, taskPriority);
    }

    @Override // com.ido.life.syncdownload.Task.Listenter
    public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String content) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("用户勋章数据下拉成功 taskInfo=");
            sb.append(taskInfo);
            sb.append(",content=");
            String str = content;
            String strSubSequence = (!(str == null || str.length() == 0) && content.length() > 200) ? content.subSequence(0, 200) : content;
            sb.append(strSubSequence);
            printAndSaveLog(sb.toString());
            JSONObject jSONObject = new JSONObject(content);
            if (jSONObject.getInt(NotificationCompat.CATEGORY_STATUS) == 200 && !jSONObject.isNull("result")) {
                JSONArray jSONArray = jSONObject.getJSONArray("result");
                if (jSONArray != null && jSONArray.length() > 0) {
                    int length = jSONArray.length();
                    printAndSaveLog("勋章数据总条数 dataTotalCount=" + length);
                    if (taskInfo instanceof NewTask.NewTaskInfo) {
                        ((NewTask.NewTaskInfo) taskInfo).getBuilder().setDataTotalCount(length);
                    }
                    JSONObject jSONObject2 = (JSONObject) null;
                    for (int i = 0; i < length; i++) {
                        try {
                            jSONObject2 = jSONArray.getJSONObject(i);
                            UserMedalInfo userMedalInfo = new UserMedalInfo();
                            userMedalInfo.setUserId(getUserId());
                            if (!jSONObject2.isNull("createTime")) {
                                userMedalInfo.setCreateTime(jSONObject2.getString("createTime"));
                            }
                            String createTime = userMedalInfo.getCreateTime();
                            if (!(createTime == null || createTime.length() == 0)) {
                                if (!jSONObject2.isNull("medalId")) {
                                    userMedalInfo.setMedalId(jSONObject2.getInt("medalId"));
                                }
                                int medalId = userMedalInfo.getMedalId();
                                if (1 <= medalId && 12 >= medalId) {
                                    userMedalInfo.setDate(userMedalInfo.getCreateTime());
                                    userMedalInfo.setShowToUser(true);
                                    userMedalInfo.setUploadSuccess(true);
                                    GreenDaoUtil.addUserMedalInfo(userMedalInfo);
                                }
                            }
                        } catch (Exception e2) {
                            printAndSaveLog("用户勋章数据下拉ITEM转换失败 itemJsonObject=" + jSONObject2 + ",error=" + e2.getLocalizedMessage());
                        }
                    }
                }
                EventBusHelper.post(Constants.EventConstants.EVENT_USER_MEDAL_PULL_SUCCESS);
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            printAndSaveLog("用户勋章数据下拉转JSON失败 content=" + content + ",error=" + e3.getLocalizedMessage());
        }
        return true;
    }
}