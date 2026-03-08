package com.ido.life.module.user.healthreport;

import com.ido.life.base.IBaseView;
import com.ido.life.bean.BarChartPoint;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.enums.UserModelEnum;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: HealthReportNewView.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b)\n\u0002\u0010\u000b\n\u0002\b\u000b\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0018\u0010\u0004\u001a\u00020\u00032\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H&J\u0018\u0010\b\u001a\u00020\u00032\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tH&J\u0018\u0010\u000b\u001a\u00020\u00032\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006H&J\u0018\u0010\r\u001a\u00020\u00032\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006H&J\u0018\u0010\u000e\u001a\u00020\u00032\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006H&J\u0018\u0010\u000f\u001a\u00020\u00032\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006H&J\b\u0010\u0010\u001a\u00020\u0003H&J\b\u0010\u0011\u001a\u00020\u0003H&J\u0016\u0010\u0012\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00130\tH&J\u0016\u0010\u0014\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00130\tH&J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0017H&J\u0010\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0017H&J\u0010\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0017H&J\u0010\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u0017H&J\u0016\u0010\u001e\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00130\tH&J\u0016\u0010\u001f\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00130\tH&J\u0010\u0010 \u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0017H&J\u0010\u0010!\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0017H&J\u0010\u0010\"\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0017H&J\u0010\u0010#\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u0017H&J\u0016\u0010$\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00130\tH&J\u0016\u0010%\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00130\tH&J\u0010\u0010&\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0017H&J\u0010\u0010'\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0017H&J\u0010\u0010(\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0017H&J\u0010\u0010)\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u0017H&J\u0010\u0010*\u001a\u00020\u00032\u0006\u0010+\u001a\u00020\u0017H&J\u0016\u0010,\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00130\tH&J\u0016\u0010-\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00130\tH&J\u0010\u0010.\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0017H&J\u0010\u0010/\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0017H&J\u0010\u00100\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0017H&J\u0010\u00101\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u0017H&J\u0016\u00102\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00130\tH&J\u0016\u00103\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00130\tH&J\u0010\u00104\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0017H&J\u0010\u00105\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0017H&J\u0010\u00106\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0017H&J\u0010\u00107\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u0017H&J\b\u00108\u001a\u00020\u0003H&J\u0010\u00109\u001a\u00020\u00032\u0006\u0010:\u001a\u00020\u0017H&J\u0018\u0010;\u001a\u00020\u00032\u0006\u0010<\u001a\u00020\u00172\u0006\u0010=\u001a\u00020\u0017H&J\u0018\u0010>\u001a\u00020\u00032\u0006\u0010?\u001a\u00020\u00172\u0006\u0010@\u001a\u00020AH&J\u0010\u0010B\u001a\u00020\u00032\u0006\u0010C\u001a\u00020\u0017H&J\u0012\u0010D\u001a\u00020\u00032\b\u0010E\u001a\u0004\u0018\u00010\u0013H&J\u0012\u0010F\u001a\u00020\u00032\b\u0010G\u001a\u0004\u0018\u00010\u0013H&J\u0010\u0010H\u001a\u00020\u00032\u0006\u0010I\u001a\u00020\u0017H&J\u0010\u0010J\u001a\u00020\u00032\u0006\u0010K\u001a\u00020\u0013H&¨\u0006L"}, d2 = {"Lcom/ido/life/module/user/healthreport/HealthReportNewView;", "Lcom/ido/life/base/IBaseView;", "dismissLoading", "", "onGetFourWeekStepSuccess", "list", "", "Lcom/ido/life/bean/BaseCharBean;", "onGetUserMealSuccess", "", "Lcom/ido/life/enums/UserModelEnum;", "onGetWeekAllCalorieSuccess", "Lcom/ido/life/bean/BarChartPoint;", "onGetWeekStepSuccess", "onGetWeekStrengthSuccess", "onGetWeekWalkHourSuccess", "screenShotFailed", "screenShotSuccess", "setAllDayCalorieBottomLabelList", "", "setAllDayCalorieRightLabelList", "setAllDayCalorieXMaxValue", "xMax", "", "setAllDayCalorieXMinValue", "xMin", "setAllDayCalorieYMaxValue", "yMax", "setAllDayCalorieYMinValue", "yMin", "setCurrentWeekStepBottomLabelList", "setCurrentWeekStepRightLabelList", "setCurrentWeekStepXMaxValue", "setCurrentWeekStepXMinValue", "setCurrentWeekStepYMaxValue", "setCurrentWeekStepYMinValue", "setFourWeekStepBottomLabelList", "setFourWeekStepRightLabelList", "setFourWeekStepXMaxValue", "setFourWeekStepXMinValue", "setFourWeekStepYMaxValue", "setFourWeekStepYMinValue", "setStepTarget", "stepTarget", "setStrengthBottomLabelList", "setStrengthRightLabelList", "setStrengthXMaxValue", "setStrengthXMinValue", "setStrengthYMaxValue", "setStrengthYMinValue", "setWalkHourBottomLabelList", "setWalkHourRightLabelList", "setWalkHourXMaxValue", "setWalkHourXMinValue", "setWalkHourYMaxValue", "setWalkHourYMinValue", "showLoading", "updateCalorieByDay", "calorie", "updateCurrentWeekStepDesc", "maxStep", "dayStep", "updateFourWeekStepDesc", "percent", "lower", "", "updateStrengthByDay", "min", "updateUserAvatar", "avatar", "updateUserNickName", "nickName", "updateWalkHourByDay", "hour", "updateWeekDateArea", "dateArea", "app_release"}, k = 1, mv = {1, 1, 16})
public interface HealthReportNewView extends IBaseView {
    void dismissLoading();

    void onGetFourWeekStepSuccess(List<BaseCharBean> list);

    void onGetUserMealSuccess(List<? extends UserModelEnum> list);

    void onGetWeekAllCalorieSuccess(List<BarChartPoint> list);

    void onGetWeekStepSuccess(List<BarChartPoint> list);

    void onGetWeekStrengthSuccess(List<BarChartPoint> list);

    void onGetWeekWalkHourSuccess(List<BarChartPoint> list);

    void screenShotFailed();

    void screenShotSuccess();

    void setAllDayCalorieBottomLabelList(List<String> list);

    void setAllDayCalorieRightLabelList(List<String> list);

    void setAllDayCalorieXMaxValue(int xMax);

    void setAllDayCalorieXMinValue(int xMin);

    void setAllDayCalorieYMaxValue(int yMax);

    void setAllDayCalorieYMinValue(int yMin);

    void setCurrentWeekStepBottomLabelList(List<String> list);

    void setCurrentWeekStepRightLabelList(List<String> list);

    void setCurrentWeekStepXMaxValue(int xMax);

    void setCurrentWeekStepXMinValue(int xMin);

    void setCurrentWeekStepYMaxValue(int yMax);

    void setCurrentWeekStepYMinValue(int yMin);

    void setFourWeekStepBottomLabelList(List<String> list);

    void setFourWeekStepRightLabelList(List<String> list);

    void setFourWeekStepXMaxValue(int xMax);

    void setFourWeekStepXMinValue(int xMin);

    void setFourWeekStepYMaxValue(int yMax);

    void setFourWeekStepYMinValue(int yMin);

    void setStepTarget(int stepTarget);

    void setStrengthBottomLabelList(List<String> list);

    void setStrengthRightLabelList(List<String> list);

    void setStrengthXMaxValue(int xMax);

    void setStrengthXMinValue(int xMin);

    void setStrengthYMaxValue(int yMax);

    void setStrengthYMinValue(int yMin);

    void setWalkHourBottomLabelList(List<String> list);

    void setWalkHourRightLabelList(List<String> list);

    void setWalkHourXMaxValue(int xMax);

    void setWalkHourXMinValue(int xMin);

    void setWalkHourYMaxValue(int yMax);

    void setWalkHourYMinValue(int yMin);

    void showLoading();

    void updateCalorieByDay(int calorie);

    void updateCurrentWeekStepDesc(int maxStep, int dayStep);

    void updateFourWeekStepDesc(int percent, boolean lower);

    void updateStrengthByDay(int min);

    void updateUserAvatar(String avatar);

    void updateUserNickName(String nickName);

    void updateWalkHourByDay(int hour);

    void updateWeekDateArea(String dateArea);
}