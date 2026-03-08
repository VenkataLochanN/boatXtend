package com.ido.life.module.home.detail;

import com.ido.ble.data.manage.database.HealthSleep;
import com.ido.ble.data.manage.database.HealthSleepItem;
import com.ido.life.base.IBaseView;
import com.ido.life.bean.ValueRangePair;
import com.ido.life.database.model.ActiveTimeDayData;
import com.ido.life.database.model.MultiDaysWalkTotalData;
import com.ido.life.database.model.ServerBloodOxyDayData;
import com.ido.life.database.model.ServerDaysBloodOxyData;
import com.ido.life.database.model.ServerDaysSleepData;
import com.ido.life.database.model.ServerMultiMonthBloodOxyTotalData;
import com.ido.life.database.model.ServerMultiMonthSleepTotalData;
import com.ido.life.database.model.ServerSleepDayData;
import com.ido.life.database.model.SleepDetailModel;
import com.ido.life.database.model.StepDayData;
import com.ido.life.database.model.WalkDayData;
import com.ido.life.database.model.WalkYearTotalData;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
interface IDetailView extends IBaseView {
    void onGetDayActiveTimeData(ActiveTimeDayData activeTimeDayData, float[] fArr);

    void onGetDayBloodOxygen(ServerBloodOxyDayData serverBloodOxyDayData, List<ValueRangePair> list);

    void onGetDayData(int i, List<ValueRangePair> list);

    void onGetDayData(int i, float[] fArr);

    void onGetDaySleepData(HealthSleep healthSleep, List<HealthSleepItem> list, ServerSleepDayData serverSleepDayData);

    void onGetDaySleepList(List<ServerSleepDayData> list);

    void onGetDayStep(StepDayData stepDayData, float[] fArr);

    void onGetDayWalkData(WalkDayData walkDayData, float[] fArr);

    void onGetMonthData(int i, List<ValueRangePair> list);

    void onGetMonthData(int i, float[] fArr);

    void onGetMultiDaysActiveTime(int i, int i2, float[] fArr);

    void onGetMultiDaysBloodOxygen(ServerDaysBloodOxyData serverDaysBloodOxyData, List<ValueRangePair> list, boolean z);

    void onGetMultiDaysSleepTotalData(ServerDaysSleepData serverDaysSleepData, List<SleepDetailModel> list, boolean z);

    void onGetMultiDaysStep(int i, int i2, float[] fArr);

    void onGetMultiDaysWalkData(MultiDaysWalkTotalData multiDaysWalkTotalData, float[] fArr, boolean z);

    void onGetWeekData(int i, List<ValueRangePair> list);

    void onGetWeekData(int i, float[] fArr);

    void onGetYearActiveTime(int i, int i2, float[] fArr);

    void onGetYearBloodOxy(ServerMultiMonthBloodOxyTotalData serverMultiMonthBloodOxyTotalData, List<ValueRangePair> list, boolean z);

    void onGetYearData(int i, List<ValueRangePair> list);

    void onGetYearData(int i, float[] fArr);

    void onGetYearSleepData(ServerMultiMonthSleepTotalData serverMultiMonthSleepTotalData, List<SleepDetailModel> list, boolean z);

    void onGetYearStepData(int i, int i2, float[] fArr);

    void onGetYearWalkData(WalkYearTotalData walkYearTotalData, float[] fArr, boolean z);

    void onNeedTurnedOnHeartRateMeasurement(boolean z);

    void showLoadFailedUI();

    void showLoadSuccessUI();

    void showLoadingUI();
}