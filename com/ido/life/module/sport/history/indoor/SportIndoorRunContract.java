package com.ido.life.module.sport.history.indoor;

import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.database.model.SportHealth;
import com.ido.life.module.sport.bean.HistoryRecordDetailsData;
import com.ido.life.module.sport.bean.PieChartBean;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SportIndoorRunContract {

    interface Presenter extends BasePresenter {
        void clearSportTarget();

        void deleteRecord(String str);

        void getSportData(SportHealth sportHealth);

        void getSportDataByDate(int i, String str, int i2);

        void getSportDataBySid(String str);

        void getSportNameByType(int i);
    }

    interface View extends BaseView<Presenter> {
        void hideLoading();

        void setAerobicEndurance(String str);

        void setBurningHeartRate(String str);

        void setEightItemDesc(String str);

        void setEightItemUnit(String str);

        void setEightItemValue(String str);

        void setFirstItemDesc(String str);

        void setFirstItemUnit(String str);

        void setFirstItemValue(String str);

        void setFiveItemDesc(String str);

        void setFiveItemUnit(String str);

        void setFiveItemValue(String str);

        void setFourItemDesc(String str);

        void setFourItemUnit(String str);

        void setFourItemValue(String str);

        void setHeartPieChart(List<PieChartBean> list);

        void setKmSpace(List<HistoryRecordDetailsData> list, int i, int i2, boolean z);

        void setKmSpeed(List<HistoryRecordDetailsData> list, int i, int i2, boolean z);

        void setLoadLoadTitleShow(boolean z);

        void setLoadTitleText(String str);

        void setMaxExerciseTime(String str);

        void setNoEndurance(String str);

        void setRateList(List<BaseCharBean> list);

        void setRateXMaxValue(int i);

        void setRateXMinValue(int i);

        void setRateYMaxValue(int i);

        void setRateYMinValue(int i);

        void setSecondItemDesc(String str);

        void setSecondItemUnit(String str);

        void setSecondItemValue(String str);

        void setSeekBarProcess(int i, int i2);

        void setSevenItemDesc(String str);

        void setSevenItemUnit(String str);

        void setSevenItemValue(String str);

        void setSixItemDesc(String str);

        void setSixItemUnit(String str);

        void setSixItemValue(String str);

        void setSportChartRateAvg(String str);

        void setSportDistance(String str, int i);

        void setSportDistanceUnit(String str);

        void setSportItemDistanceLabel(String str);

        void setSportItemPaceAverage(String str);

        void setSportItemPaceAverageDesc(String str);

        void setSportItemPaceFaster(String str);

        void setSportItemPaceFasterDesc(String str);

        void setSportItemPaceFasterUnit(String str);

        void setSportItemPaceTitleUnit(String str);

        void setSportItemSpeedTitle(String str);

        void setSportItemSpeedTitleUnit(String str);

        void setSportItemSpeedType(String str);

        void setSportName(String str);

        void setSportRateMax(String str);

        void setSportSpeedItemKm(String str);

        void setSportStartTime(String str);

        void setStepFrequencyAvg(String str);

        void setStepFrequencyList(List<BaseCharBean> list);

        void setStepFrequencyMax(String str);

        void setStepXMaxValue(int i);

        void setStepXMinValue(int i);

        void setStepYMaxValue(int i);

        void setStepYMinValue(int i);

        void setTargetDiff(String str);

        void setThreeItemDesc(String str);

        void setThreeItemUnit(String str);

        void setThreeItemValue(String str);

        void setWarmUpHeart(String str);

        void setXLabelList(List<String> list);

        void setXStepLabelList(List<String> list);

        void setYLabelList(List<String> list);

        void setYStepLabelList(List<String> list);

        void showFourItem(boolean z);

        void showLoading();

        void showMessage(String str);

        void showRightBtn(boolean z);

        void showSeekBarStepNum(boolean z);

        void showSportDataItemFour(boolean z);

        void showSportDataItemOne(boolean z);

        void showSportDataItemThree(boolean z);

        void showSportDataItemTwo(boolean z);

        void showSportDataView(boolean z);

        void showSportItemFrequency(boolean z);

        void showSportItemPace(boolean z);

        void showSportItemRate(boolean z);

        void showSportItemSpeed(boolean z);

        void showSportNoNet(boolean z);

        void showSportRetryView(boolean z);

        void showUserTargetDiff(boolean z);

        void toBack();
    }
}