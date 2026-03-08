package com.ido.life.module.user.userdata.mydata;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ido.common.utils.GsonUtil;
import com.ido.life.database.LocalHealthDataManager;
import com.ido.life.database.model.ActiveTimeDayData;
import com.ido.life.database.model.CalorieDayData;
import com.ido.life.database.model.HealthPressure;
import com.ido.life.database.model.ServerBloodOxyDayData;
import com.ido.life.database.model.ServerHeartRateDayData;
import com.ido.life.database.model.ServerSleepDayData;
import com.ido.life.database.model.SportDistanceBean;
import com.ido.life.database.model.StepDayData;
import com.ido.life.database.model.UserInfo;
import com.ido.life.database.model.WalkDayData;
import com.ido.life.module.home.BaseHomePresenter;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.HealthDataUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.Calendar;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MyDataPresenter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0002J\b\u0010\b\u001a\u00020\tH\u0002J\b\u0010\n\u001a\u00020\tH\u0002J\b\u0010\u000b\u001a\u00020\u0007H\u0002J\b\u0010\f\u001a\u00020\u0007H\u0002J\b\u0010\r\u001a\u00020\u0007H\u0002J\u0006\u0010\u000e\u001a\u00020\tJ\b\u0010\u000f\u001a\u00020\u0007H\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\tH\u0002J\b\u0010\u0013\u001a\u00020\u0007H\u0002J\b\u0010\u0014\u001a\u00020\u0007H\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0007H\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/ido/life/module/user/userdata/mydata/MyDataPresenter;", "Lcom/ido/life/module/home/BaseHomePresenter;", "Lcom/ido/life/module/user/userdata/mydata/IMyDataView;", "()V", "mToday", "", "getActiveCalorie", "", "getActiveData", "", "getHealthData", "getHeartRate", "getNearPressure", "getNeareastOxy", "getPageData", "getSleep", "getSportCount", "", "getSportData", "getStronger", "getTodayStep", "getTotalDistance", "", "getWalkHour", "hasSportRecord", "", "app_release"}, k = 1, mv = {1, 1, 16})
public final class MyDataPresenter extends BaseHomePresenter<IMyDataView> {
    private String mToday;

    public MyDataPresenter() {
        String str = DateUtil.format(Calendar.getInstance(), DateUtil.DATE_FORMAT_YMD);
        Intrinsics.checkExpressionValueIsNotNull(str, "DateUtil.format(Calendar…DateUtil.DATE_FORMAT_YMD)");
        this.mToday = str;
    }

    public final void getPageData() {
        getActiveData();
        getHealthData();
        getSportData();
    }

    private final void getActiveData() {
        IMyDataView iMyDataView = (IMyDataView) getView();
        if (iMyDataView != null) {
            iMyDataView.onGetDistance(getTotalDistance());
        }
        IMyDataView iMyDataView2 = (IMyDataView) getView();
        if (iMyDataView2 != null) {
            iMyDataView2.onGetStep(getTodayStep());
        }
        IMyDataView iMyDataView3 = (IMyDataView) getView();
        if (iMyDataView3 != null) {
            iMyDataView3.onGetDistance(getTotalDistance());
        }
        IMyDataView iMyDataView4 = (IMyDataView) getView();
        if (iMyDataView4 != null) {
            iMyDataView4.onGetCagories(getActiveCalorie());
        }
        IMyDataView iMyDataView5 = (IMyDataView) getView();
        if (iMyDataView5 != null) {
            iMyDataView5.onGetStronger(getStronger());
        }
        IMyDataView iMyDataView6 = (IMyDataView) getView();
        if (iMyDataView6 != null) {
            iMyDataView6.onGetWalkDuration(getWalkHour());
        }
    }

    private final void getHealthData() {
        IMyDataView iMyDataView;
        IMyDataView iMyDataView2 = (IMyDataView) getView();
        if (iMyDataView2 != null) {
            iMyDataView2.onGetHeartRate(getHeartRate());
        }
        IMyDataView iMyDataView3 = (IMyDataView) getView();
        if (iMyDataView3 != null) {
            iMyDataView3.onGetSleepDuration(getSleep());
        }
        IMyDataView iMyDataView4 = (IMyDataView) getView();
        if (iMyDataView4 != null) {
            iMyDataView4.onGetPressure(getNearPressure());
        }
        IMyDataView iMyDataView5 = (IMyDataView) getView();
        if (iMyDataView5 != null) {
            iMyDataView5.onGetOxy(getNeareastOxy());
        }
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(runTimeUtil.getUserId());
        if (userInfoQueryUserInfo == null || (iMyDataView = (IMyDataView) getView()) == null) {
            return;
        }
        iMyDataView.onGetWeight(userInfoQueryUserInfo.getWeight(), userInfoQueryUserInfo.getWeightUnit());
    }

    private final void getSportData() {
        IMyDataView iMyDataView = (IMyDataView) getView();
        if (iMyDataView != null) {
            iMyDataView.onGetSportRecord(getSportCount());
        }
    }

    private final int getTodayStep() {
        StepDayData stepDailyDataByDate = LocalHealthDataManager.getInstance().getStepDailyDataByDate(this.mToday);
        if (stepDailyDataByDate != null) {
            return stepDailyDataByDate.getNumSteps();
        }
        return 0;
    }

    private final float getTotalDistance() {
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        SportDistanceBean sportDistanceBeanQuerySportDistanceByDate = GreenDaoUtil.querySportDistanceByDate(runTimeUtil.getUserId(), this.mToday);
        if (sportDistanceBeanQuerySportDistanceByDate != null) {
            return sportDistanceBeanQuerySportDistanceByDate.getTotalDistance() / 1000;
        }
        return 0.0f;
    }

    private final int getActiveCalorie() {
        LocalHealthDataManager localHealthDataManager = LocalHealthDataManager.getInstance();
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        CalorieDayData calorieDailyDataByDate = localHealthDataManager.getCalorieDailyDataByDate(runTimeUtil.getUserId(), this.mToday);
        if (calorieDailyDataByDate != null) {
            return calorieDailyDataByDate.getActivityCalorie();
        }
        return 0;
    }

    private final int getStronger() {
        ActiveTimeDayData activeTimeDailyDataByDate = LocalHealthDataManager.getInstance().getActiveTimeDailyDataByDate(this.mToday);
        if (activeTimeDailyDataByDate != null) {
            return activeTimeDailyDataByDate.getMediumOrHigherSeconds() / 60;
        }
        return 0;
    }

    private final int getWalkHour() {
        float[] walkItems;
        WalkDayData walkDayData = LocalHealthDataManager.getInstance().getWalkDayData(this.mToday);
        if (walkDayData == null || (walkItems = HealthDataUtil.formatWalkItems(walkDayData, true)) == null) {
            return 0;
        }
        if (!(true ^ (walkItems.length == 0))) {
            return 0;
        }
        int i = 0;
        for (float f2 : walkItems) {
            if (((int) f2) == 2) {
                i++;
            }
        }
        return i;
    }

    private final int getHeartRate() {
        LocalHealthDataManager localHealthDataManager = LocalHealthDataManager.getInstance();
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        ServerHeartRateDayData nearHeartRateDailyData = localHealthDataManager.getNearHeartRateDailyData(runTimeUtil.getUserId());
        if (nearHeartRateDailyData != null) {
            return nearHeartRateDailyData.getLatestValue();
        }
        return 0;
    }

    private final int getSleep() {
        LocalHealthDataManager localHealthDataManager = LocalHealthDataManager.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(localHealthDataManager, "LocalHealthDataManager.getInstance()");
        ServerSleepDayData nearestSleep = localHealthDataManager.getNearestSleep();
        if (nearestSleep != null) {
            return nearestSleep.getTotalSeconds();
        }
        return 0;
    }

    private final int getNearPressure() {
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        HealthPressure nearHealthPressureDetail = GreenDaoUtil.getNearHealthPressureDetail(runTimeUtil.getUserId());
        int i = 0;
        if (nearHealthPressureDetail != null) {
            List list = (List) new Gson().fromJson(nearHealthPressureDetail.getItems(), new TypeToken<List<int[]>>() { // from class: com.ido.life.module.user.userdata.mydata.MyDataPresenter$getNearPressure$list$1
            }.getType());
            List list2 = list;
            if (!(list2 == null || list2.isEmpty())) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    i = ((int[]) list.get(size))[1];
                    if (i > 0) {
                        break;
                    }
                }
            }
        }
        return i;
    }

    private final int getNeareastOxy() {
        List listAnalysisJsonArrayToList;
        LocalHealthDataManager localHealthDataManager = LocalHealthDataManager.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(localHealthDataManager, "LocalHealthDataManager.getInstance()");
        ServerBloodOxyDayData nearBloodOxyDailyData = localHealthDataManager.getNearBloodOxyDailyData();
        int i = 0;
        if (nearBloodOxyDailyData != null && (listAnalysisJsonArrayToList = GsonUtil.analysisJsonArrayToList(nearBloodOxyDailyData.getItems(), int[][].class)) != null && listAnalysisJsonArrayToList.size() > 0) {
            for (int size = listAnalysisJsonArrayToList.size() - 1; size >= 0; size--) {
                i = ((int[]) listAnalysisJsonArrayToList.get(size))[1];
                if (i > 0) {
                    break;
                }
            }
        }
        return i;
    }

    private final long getSportCount() {
        LocalHealthDataManager localHealthDataManager = LocalHealthDataManager.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(localHealthDataManager, "LocalHealthDataManager.getInstance()");
        return localHealthDataManager.getSportRecordCount();
    }

    @Override // com.ido.life.module.home.BaseHomePresenter
    public boolean hasSportRecord() {
        LocalHealthDataManager localHealthDataManager = LocalHealthDataManager.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(localHealthDataManager, "LocalHealthDataManager.getInstance()");
        return localHealthDataManager.getSportRecordCount() > 0;
    }
}