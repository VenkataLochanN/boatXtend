package com.ido.life.module.sport.history.rate;

import com.ido.life.base.IBaseView;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.module.sport.bean.PieChartBean;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: IRateView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005H&J\u001a\u0010\b\u001a\u00020\u00032\u0010\u0010\t\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0018\u00010\nH&J\u0012\u0010\f\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005H&J\u001a\u0010\u0010\u001a\u00020\u00032\u0010\u0010\u0011\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\nH&J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0015H&J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0015H&J\u0010\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0015H&J\u0010\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0015H&J\u0012\u0010\u001c\u001a\u00020\u00032\b\u0010\u001d\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u001e\u001a\u00020\u00032\b\u0010\u001f\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010 \u001a\u00020\u00032\b\u0010!\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\"\u001a\u00020\u00032\b\u0010#\u001a\u0004\u0018\u00010\u0005H&J\u001a\u0010$\u001a\u00020\u00032\u0010\u0010%\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\nH&J\u0012\u0010&\u001a\u00020\u00032\b\u0010'\u001a\u0004\u0018\u00010\u0005H&J\u001a\u0010(\u001a\u00020\u00032\u0010\u0010)\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\nH&J\u0010\u0010*\u001a\u00020\u00032\u0006\u0010+\u001a\u00020,H&¨\u0006-"}, d2 = {"Lcom/ido/life/module/sport/history/rate/IRateView;", "Lcom/ido/life/base/IBaseView;", "setAerobicEnduranceTime", "", "aerobicEndurance", "", "setBurningHeartRateTime", "burningHeartRate", "setHeartPieChart", "pieChartBeanList", "", "Lcom/ido/life/module/sport/bean/PieChartBean;", "setMaxExerciseTime", "maxExerciseTime", "setNoEnduranceTime", "noEndurance", "setRateList", "baseCharBeans", "Lcom/ido/life/bean/BaseCharBean;", "setRateXMaxValue", "xMaxValue", "", "setRateXMinValue", "rateXMinValue", "setRateYMaxValue", "rateYMaxValue", "setRateYMinValue", "rateYMinValue", "setSportChartRateAvg", "sportRateAvg", "setSportRateMax", "sportRateMax", "setWarmUpHeartText", "warmUpHeartText", "setWarmUpHeartTime", "warmUpHeart", "setXLabelList", "xLabelList", "setXlabelUnit", "xLabelUnit", "setYLabelList", "yLabelList", "showSportItemRate", "isVisible", "", "app_release"}, k = 1, mv = {1, 1, 16})
public interface IRateView extends IBaseView {
    void setAerobicEnduranceTime(String aerobicEndurance);

    void setBurningHeartRateTime(String burningHeartRate);

    void setHeartPieChart(List<? extends PieChartBean> pieChartBeanList);

    void setMaxExerciseTime(String maxExerciseTime);

    void setNoEnduranceTime(String noEndurance);

    void setRateList(List<? extends BaseCharBean> baseCharBeans);

    void setRateXMaxValue(int xMaxValue);

    void setRateXMinValue(int rateXMinValue);

    void setRateYMaxValue(int rateYMaxValue);

    void setRateYMinValue(int rateYMinValue);

    void setSportChartRateAvg(String sportRateAvg);

    void setSportRateMax(String sportRateMax);

    void setWarmUpHeartText(String warmUpHeartText);

    void setWarmUpHeartTime(String warmUpHeart);

    void setXLabelList(List<String> xLabelList);

    void setXlabelUnit(String xLabelUnit);

    void setYLabelList(List<String> yLabelList);

    void showSportItemRate(boolean isVisible);
}