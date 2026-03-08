package com.ido.life.syncdownload;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.life.constants.Constants;
import com.ido.life.database.model.MessageEntity;
import com.ido.life.net.BaseUrl;
import com.ido.life.syncdownload.NewTask;
import com.ido.life.syncdownload.Task;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.WeekReportUtil;
import com.ido.life.util.eventbus.EventBusHelper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: WeeklyReportTaskListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0016B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tH\u0016J\b\u0010\u000b\u001a\u00020\tH\u0016J\b\u0010\f\u001a\u00020\tH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0000H\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u001c\u0010\u0012\u001a\u00020\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\tH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/ido/life/syncdownload/WeeklyReportTaskListener;", "Lcom/ido/life/syncdownload/BaseDataDownloadTaskListener;", "userId", "", "(J)V", "mStatusList", "", "Lcom/ido/life/syncdownload/WeeklyReportTaskListener$StatusBean;", "getTaskTag", "", "kotlin.jvm.PlatformType", "getTaskType", "getTaskUrlPath", "interrupted", "", "newInstance", "onAllTaskComplete", "", "onSingleTaskSuccess", "taskInfo", "Lcom/ido/life/syncdownload/Task$TaskInfo;", FirebaseAnalytics.Param.CONTENT, "StatusBean", "app_release"}, k = 1, mv = {1, 1, 16})
public final class WeeklyReportTaskListener extends BaseDataDownloadTaskListener {
    private List<StatusBean> mStatusList;

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskType() {
        return BaseUrl.URL_NAME_HEALTH;
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskUrlPath() {
        return "api/weekly/status/get";
    }

    @Override // com.ido.life.syncdownload.BaseTaskListener
    public boolean interrupted() {
        return false;
    }

    public WeeklyReportTaskListener(long j) {
        super(j, 0, 2, null);
        this.mStatusList = new ArrayList();
    }

    /* JADX INFO: compiled from: WeeklyReportTaskListener.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/ido/life/syncdownload/WeeklyReportTaskListener$StatusBean;", "", "year", "", "weekNum", NotificationCompat.CATEGORY_STATUS, "(III)V", "getStatus", "()I", "setStatus", "(I)V", "getWeekNum", "setWeekNum", "getYear", "setYear", "component1", "component2", "component3", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class StatusBean {
        private int status;
        private int weekNum;
        private int year;

        public StatusBean() {
            this(0, 0, 0, 7, null);
        }

        public static /* synthetic */ StatusBean copy$default(StatusBean statusBean, int i, int i2, int i3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                i = statusBean.year;
            }
            if ((i4 & 2) != 0) {
                i2 = statusBean.weekNum;
            }
            if ((i4 & 4) != 0) {
                i3 = statusBean.status;
            }
            return statusBean.copy(i, i2, i3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getYear() {
            return this.year;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getWeekNum() {
            return this.weekNum;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getStatus() {
            return this.status;
        }

        public final StatusBean copy(int year, int weekNum, int status) {
            return new StatusBean(year, weekNum, status);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StatusBean)) {
                return false;
            }
            StatusBean statusBean = (StatusBean) other;
            return this.year == statusBean.year && this.weekNum == statusBean.weekNum && this.status == statusBean.status;
        }

        public int hashCode() {
            return (((Integer.valueOf(this.year).hashCode() * 31) + Integer.valueOf(this.weekNum).hashCode()) * 31) + Integer.valueOf(this.status).hashCode();
        }

        public String toString() {
            return "StatusBean(year=" + this.year + ", weekNum=" + this.weekNum + ", status=" + this.status + ")";
        }

        public StatusBean(int i, int i2, int i3) {
            this.year = i;
            this.weekNum = i2;
            this.status = i3;
        }

        public /* synthetic */ StatusBean(int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
            this((i4 & 1) != 0 ? -1 : i, (i4 & 2) != 0 ? -1 : i2, (i4 & 4) != 0 ? -1 : i3);
        }

        public final int getStatus() {
            return this.status;
        }

        public final int getWeekNum() {
            return this.weekNum;
        }

        public final int getYear() {
            return this.year;
        }

        public final void setStatus(int i) {
            this.status = i;
        }

        public final void setWeekNum(int i) {
            this.weekNum = i;
        }

        public final void setYear(int i) {
            this.year = i;
        }
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public String getTaskTag() {
        return MessageEntity.class.getSimpleName();
    }

    @Override // com.ido.life.syncdownload.BaseDataDownloadTaskListener
    public WeeklyReportTaskListener newInstance() {
        return new WeeklyReportTaskListener(getUserId());
    }

    @Override // com.ido.life.syncdownload.BaseTaskListener, com.ido.life.syncdownload.Task.Listenter
    public void onAllTaskComplete() {
        super.onAllTaskComplete();
        long jCurrentTimeMillis = System.currentTimeMillis();
        new WeekReportUtil().generateHealthWeekReport(getUserId());
        printAndSaveLog("健康周报生成用时=" + (System.currentTimeMillis() - jCurrentTimeMillis));
        if (GreenDaoUtil.queryUnReadMessageCountByType(getUserId(), 3) <= 0 || this.mStatusList.isEmpty()) {
            return;
        }
        printAndSaveLog("开始更新健康周报已读状态");
        int size = this.mStatusList.size();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        long jCurrentTimeMillis2 = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            StatusBean statusBean = this.mStatusList.get(i);
            if (statusBean.getYear() > 0 && statusBean.getWeekNum() > 0) {
                calendar.set(1, statusBean.getYear());
                Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
                calendar.setFirstDayOfWeek(2);
                int actualMaximum = calendar.getActualMaximum(3);
                if (statusBean.getWeekNum() > actualMaximum) {
                    calendar.set(3, actualMaximum);
                    calendar.set(7, 2);
                    calendar.add(5, 7);
                } else {
                    calendar.set(3, statusBean.getWeekNum());
                    calendar.set(7, 2);
                }
                MessageEntity messageEntityQueryHealthReportByMonday = GreenDaoUtil.queryHealthReportByMonday(getUserId(), DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD));
                if (messageEntityQueryHealthReportByMonday != null && !messageEntityQueryHealthReportByMonday.getHasRead()) {
                    messageEntityQueryHealthReportByMonday.setHasRead(true);
                    messageEntityQueryHealthReportByMonday.setHasUpload(true);
                    messageEntityQueryHealthReportByMonday.update();
                }
            }
        }
        printAndSaveLog("更新健康周报已读状态用时=" + (System.currentTimeMillis() - jCurrentTimeMillis2));
        this.mStatusList.clear();
        EventBusHelper.post(Constants.EventConstants.EVENT_REFRESH_WEEK_REPORT);
    }

    @Override // com.ido.life.syncdownload.Task.Listenter
    public boolean onSingleTaskSuccess(Task.TaskInfo taskInfo, String content) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("健康周报状态下拉成功 taskInfo=");
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
            if (jSONObject.isNull(NotificationCompat.CATEGORY_STATUS) || jSONObject.getInt(NotificationCompat.CATEGORY_STATUS) != 200 || jSONObject.isNull("result") || !(taskInfo instanceof NewTask.NewTaskInfo)) {
                return true;
            }
            JSONArray jSONArray = jSONObject.getJSONArray("result");
            int length = jSONArray.length();
            printAndSaveLog("周报状态数据总条数 dataTotalCount=" + length);
            if (length <= 0) {
                return true;
            }
            for (int i = 0; i < length; i++) {
                try {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    if (jSONObject2 != null) {
                        StatusBean statusBean = new StatusBean(0, 0, 0, 7, null);
                        if (!jSONObject2.isNull("year")) {
                            statusBean.setYear(jSONObject2.getInt("year"));
                        }
                        if (!jSONObject2.isNull("weekNum")) {
                            statusBean.setWeekNum(jSONObject2.getInt("weekNum"));
                        }
                        if (!jSONObject2.isNull(NotificationCompat.CATEGORY_STATUS)) {
                            statusBean.setStatus(jSONObject2.getInt(NotificationCompat.CATEGORY_STATUS));
                        }
                        this.mStatusList.add(statusBean);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    printAndSaveLog("健康周报状态ITEM转换失败 jsonObject=" + jSONObject);
                }
            }
            ((NewTask.NewTaskInfo) taskInfo).getBuilder().setDataTotalCount(length);
            return true;
        } catch (Exception e3) {
            e3.printStackTrace();
            return true;
        }
    }
}