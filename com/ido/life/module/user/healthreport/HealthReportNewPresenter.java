package com.ido.life.module.user.healthreport;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPath;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.BitmapUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.base.BasePresenter;
import com.ido.life.bean.BarChartPoint;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.constants.Constants;
import com.ido.life.database.LocalHealthDataManager;
import com.ido.life.database.model.ActiveTimeDayData;
import com.ido.life.database.model.CalorieDayData;
import com.ido.life.database.model.MessageEntity;
import com.ido.life.database.model.StepDayData;
import com.ido.life.database.model.UserInfo;
import com.ido.life.database.model.UserMedalInfo;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.database.model.WalkDayData;
import com.ido.life.enums.UserModelEnum;
import com.ido.life.util.AsyncTaskUtil;
import com.ido.life.util.DateUtil;
import com.ido.life.util.FileUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.HealthDataUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.ShareUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HealthReportNewPresenter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0002J\u0006\u0010%\u001a\u00020&J\u0018\u0010'\u001a\u00020\"2\u0006\u0010(\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u0005H\u0002J\b\u0010*\u001a\u00020\"H\u0002J\u0018\u0010+\u001a\u00020\"2\u0006\u0010(\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u0005H\u0002J\b\u0010,\u001a\u00020\"H\u0002J\b\u0010-\u001a\u00020\"H\u0002J\u0018\u0010.\u001a\u00020\"2\u0006\u0010(\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u0005H\u0002J\u0018\u0010/\u001a\u00020\"2\u0006\u0010(\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u0005H\u0002J\u000e\u00100\u001a\u00020\"2\u0006\u00101\u001a\u000202J\u000e\u00103\u001a\u00020\"2\u0006\u0010(\u001a\u00020\u0005J\u000e\u00104\u001a\u00020\"2\u0006\u00101\u001a\u000202J\u0006\u00105\u001a\u00020\"J\u0006\u00106\u001a\u00020\"J\u0006\u00107\u001a\u00020\"J\b\u00108\u001a\u00020\"H\u0002R\u0019\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001a\u0010\u0015\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000eR\u001a\u0010\u0018\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u000eR\u001a\u0010\u001b\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\f\"\u0004\b\u001d\u0010\u000eR\u001a\u0010\u001e\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\f\"\u0004\b \u0010\u000e¨\u00069"}, d2 = {"Lcom/ido/life/module/user/healthreport/HealthReportNewPresenter;", "Lcom/ido/life/base/BasePresenter;", "Lcom/ido/life/module/user/healthreport/HealthReportNewView;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "mCaloriePerDay", "", "getMCaloriePerDay", "()I", "setMCaloriePerDay", "(I)V", "mCurrentWeekMaxStep", "getMCurrentWeekMaxStep", "setMCurrentWeekMaxStep", "mCurrentWeekPerStep", "getMCurrentWeekPerStep", "setMCurrentWeekPerStep", "mPercent", "getMPercent", "setMPercent", "mStrength", "getMStrength", "setMStrength", "mWalkHour", "getMWalkHour", "setMWalkHour", "mWeekOffset", "getMWeekOffset", "setMWeekOffset", "caluteItemChartBottomLabel", "", Constants.AppPackage.CALENDAR, "Ljava/util/Calendar;", "hasNextWeek", "", "loadCalorieData", "startDate", "endDate", "loadFourWeekStepData", "loadStrengthData", "loadUserInfo", "loadUserMedal", "loadWalkHourData", "loadWeekStepData", "saveReportPhoto", "topView", "Landroid/view/View;", "setWeekStartDate", "shotLongScreen", "startLoadData", "switchToNextWeek", "switchToPreviourWeek", "updateReportReadState", "app_release"}, k = 1, mv = {1, 1, 16})
public final class HealthReportNewPresenter extends BasePresenter<HealthReportNewView> {
    private final String TAG = HealthReportNewPresenter.class.getSimpleName();
    private int mCaloriePerDay;
    private int mCurrentWeekMaxStep;
    private int mCurrentWeekPerStep;
    private int mPercent;
    private int mStrength;
    private int mWalkHour;
    private int mWeekOffset;

    public static final /* synthetic */ HealthReportNewView access$getView(HealthReportNewPresenter healthReportNewPresenter) {
        return healthReportNewPresenter.getView();
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final int getMWeekOffset() {
        return this.mWeekOffset;
    }

    public final void setMWeekOffset(int i) {
        this.mWeekOffset = i;
    }

    public final int getMCurrentWeekPerStep() {
        return this.mCurrentWeekPerStep;
    }

    public final void setMCurrentWeekPerStep(int i) {
        this.mCurrentWeekPerStep = i;
    }

    public final int getMCurrentWeekMaxStep() {
        return this.mCurrentWeekMaxStep;
    }

    public final void setMCurrentWeekMaxStep(int i) {
        this.mCurrentWeekMaxStep = i;
    }

    public final int getMCaloriePerDay() {
        return this.mCaloriePerDay;
    }

    public final void setMCaloriePerDay(int i) {
        this.mCaloriePerDay = i;
    }

    public final int getMStrength() {
        return this.mStrength;
    }

    public final void setMStrength(int i) {
        this.mStrength = i;
    }

    public final int getMWalkHour() {
        return this.mWalkHour;
    }

    public final void setMWalkHour(int i) {
        this.mWalkHour = i;
    }

    public final int getMPercent() {
        return this.mPercent;
    }

    public final void setMPercent(int i) {
        this.mPercent = i;
    }

    public final void startLoadData() {
        this.mCurrentWeekPerStep = 0;
        this.mCurrentWeekMaxStep = 0;
        this.mCaloriePerDay = 0;
        this.mStrength = 0;
        this.mWalkHour = 0;
        this.mPercent = 0;
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        int weekStart = runTimeUtil.getWeekStart();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setFirstDayOfWeek(weekStart);
        calendar.set(7, weekStart);
        calendar.add(4, this.mWeekOffset);
        String startDate = DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_YMD);
        String str = DateUtil.format(calendar.getTime(), "d/M/yyyy");
        caluteItemChartBottomLabel(calendar);
        String endDate = DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_YMD);
        String str2 = DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_DM);
        HealthReportNewView view = getView();
        if (view != null) {
            view.updateWeekDateArea(str + '-' + str2);
        }
        updateReportReadState();
        CommonLogUtil.d(this.TAG, "startDate=" + startDate + ",endDate=" + endDate);
        loadUserInfo();
        loadUserMedal();
        Intrinsics.checkExpressionValueIsNotNull(startDate, "startDate");
        Intrinsics.checkExpressionValueIsNotNull(endDate, "endDate");
        loadCalorieData(startDate, endDate);
        loadStrengthData(startDate, endDate);
        loadWalkHourData(startDate, endDate);
        loadWeekStepData(startDate, endDate);
        loadFourWeekStepData();
    }

    private final void updateReportReadState() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setFirstDayOfWeek(2);
        calendar.set(7, 2);
        calendar.add(4, this.mWeekOffset);
        String str = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        MessageEntity messageEntityQueryHealthReportByMonday = GreenDaoUtil.queryHealthReportByMonday(runTimeUtil.getUserId(), str);
        if (messageEntityQueryHealthReportByMonday == null || messageEntityQueryHealthReportByMonday.getHasRead()) {
            return;
        }
        messageEntityQueryHealthReportByMonday.setHasRead(true);
        messageEntityQueryHealthReportByMonday.setHasUpload(false);
        messageEntityQueryHealthReportByMonday.update();
    }

    private final void loadUserInfo() {
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(runTimeUtil.getUserId());
        if (userInfoQueryUserInfo != null) {
            HealthReportNewView view = getView();
            if (view != null) {
                view.updateUserAvatar(userInfoQueryUserInfo.getAvatarUrl());
            }
            HealthReportNewView view2 = getView();
            if (view2 != null) {
                view2.updateUserNickName(userInfoQueryUserInfo.getDisplayName());
                return;
            }
            return;
        }
        HealthReportNewView view3 = getView();
        if (view3 != null) {
            view3.updateUserAvatar(null);
        }
        HealthReportNewView view4 = getView();
        if (view4 != null) {
            view4.updateUserNickName(null);
        }
    }

    private final void loadUserMedal() {
        UserModelEnum userModelEnumById;
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        List<UserMedalInfo> listQueryAllUserMedalInfo = GreenDaoUtil.queryAllUserMedalInfo(runTimeUtil.getUserId());
        List<UserMedalInfo> list = listQueryAllUserMedalInfo;
        if (list == null || list.isEmpty()) {
            HealthReportNewView view = getView();
            if (view != null) {
                view.onGetUserMealSuccess(null);
                return;
            }
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (UserMedalInfo userMedalInfo : listQueryAllUserMedalInfo) {
            if (userMedalInfo != null && (userModelEnumById = UserModelEnum.INSTANCE.getUserModelEnumById(userMedalInfo.getMedalId())) != null) {
                arrayList.add(userModelEnumById);
            }
        }
        HealthReportNewView view2 = getView();
        if (view2 != null) {
            view2.onGetUserMealSuccess(arrayList);
        }
    }

    private final void loadCalorieData(String startDate, String endDate) {
        List<CalorieDayData> calorieDailyDataList = LocalHealthDataManager.getInstance().getCalorieDailyDataList(startDate, endDate);
        List<CalorieDayData> list = calorieDailyDataList;
        if (list == null || list.isEmpty()) {
            HealthReportNewView view = getView();
            if (view != null) {
                view.updateCalorieByDay(this.mCaloriePerDay);
            }
            HealthReportNewView view2 = getView();
            if (view2 != null) {
                view2.setAllDayCalorieYMaxValue(500);
            }
            HealthReportNewView view3 = getView();
            if (view3 != null) {
                view3.setAllDayCalorieRightLabelList(CollectionsKt.mutableListOf("", "", "", "", "500"));
            }
            HealthReportNewView view4 = getView();
            if (view4 != null) {
                view4.onGetWeekAllCalorieSuccess(null);
                return;
            }
            return;
        }
        ArrayList arrayList = new ArrayList();
        int size = calorieDailyDataList.size();
        int i = 0;
        int totalCalorie = 0;
        int iMax = 0;
        for (int i2 = 0; i2 < size; i2++) {
            CalorieDayData calorieDayData = calorieDailyDataList.get(i2);
            if (calorieDayData != null && calorieDayData.getTotalCalorie() > 0) {
                arrayList.add(new BarChartPoint(-1, i2 + 1, calorieDayData.getTotalCalorie()));
                i++;
                totalCalorie += calorieDayData.getTotalCalorie();
                iMax = Math.max(iMax, calorieDayData.getTotalCalorie());
            }
        }
        if (i > 0) {
            this.mCaloriePerDay = totalCalorie / i;
        }
        int i3 = iMax <= 0 ? 500 : iMax;
        HealthReportNewView view5 = getView();
        if (view5 != null) {
            view5.setAllDayCalorieYMaxValue(i3);
        }
        HealthReportNewView view6 = getView();
        if (view6 != null) {
            view6.setAllDayCalorieRightLabelList(CollectionsKt.mutableListOf("", "", "", "", String.valueOf(iMax)));
        }
        HealthReportNewView view7 = getView();
        if (view7 != null) {
            view7.onGetWeekAllCalorieSuccess(arrayList);
        }
        HealthReportNewView view8 = getView();
        if (view8 != null) {
            view8.updateCalorieByDay(this.mCaloriePerDay);
        }
    }

    private final void loadStrengthData(String startDate, String endDate) {
        List<ActiveTimeDayData> activeTimeDailyDataList = LocalHealthDataManager.getInstance().getActiveTimeDailyDataList(startDate, endDate);
        List<ActiveTimeDayData> list = activeTimeDailyDataList;
        if (list == null || list.isEmpty()) {
            HealthReportNewView view = getView();
            if (view != null) {
                view.updateStrengthByDay(this.mStrength);
            }
            HealthReportNewView view2 = getView();
            if (view2 != null) {
                view2.setStrengthYMaxValue(200);
            }
            HealthReportNewView view3 = getView();
            if (view3 != null) {
                view3.setStrengthRightLabelList(CollectionsKt.mutableListOf("", "", "", "", "200"));
            }
            HealthReportNewView view4 = getView();
            if (view4 != null) {
                view4.onGetWeekStrengthSuccess(null);
                return;
            }
            return;
        }
        int size = activeTimeDailyDataList.size();
        ArrayList arrayList = new ArrayList();
        int iMax = 0;
        int i = 0;
        int mediumOrHigherSeconds = 0;
        for (int i2 = 0; i2 < size; i2++) {
            ActiveTimeDayData activeTimeDayData = activeTimeDailyDataList.get(i2);
            if (activeTimeDayData != null && activeTimeDayData.getMediumOrHigherSeconds() > 0) {
                i++;
                mediumOrHigherSeconds += activeTimeDayData.getMediumOrHigherSeconds() / 60;
                arrayList.add(new BarChartPoint(-1, i2 + 1, activeTimeDayData.getMediumOrHigherSeconds() / 60));
                iMax = Math.max(iMax, activeTimeDayData.getMediumOrHigherSeconds() / 60);
            }
        }
        if (iMax == 0) {
            iMax = 200;
        }
        HealthReportNewView view5 = getView();
        if (view5 != null) {
            view5.setStrengthYMaxValue(iMax);
        }
        HealthReportNewView view6 = getView();
        if (view6 != null) {
            view6.setStrengthRightLabelList(CollectionsKt.mutableListOf("", "", "", "", String.valueOf(iMax)));
        }
        if (i > 0) {
            this.mStrength = mediumOrHigherSeconds / i;
        }
        HealthReportNewView view7 = getView();
        if (view7 != null) {
            view7.onGetWeekStrengthSuccess(arrayList);
        }
        HealthReportNewView view8 = getView();
        if (view8 != null) {
            view8.updateStrengthByDay(this.mStrength);
        }
    }

    private final void loadWalkHourData(String startDate, String endDate) {
        int i;
        int i2;
        List<WalkDayData> walkDayDataList = LocalHealthDataManager.getInstance().getWalkDayDataList(startDate, endDate);
        List<WalkDayData> list = walkDayDataList;
        if (list == null || list.isEmpty()) {
            HealthReportNewView view = getView();
            if (view != null) {
                view.updateWalkHourByDay(this.mWalkHour);
            }
            HealthReportNewView view2 = getView();
            if (view2 != null) {
                view2.setWalkHourYMaxValue(12);
            }
            HealthReportNewView view3 = getView();
            if (view3 != null) {
                view3.setWalkHourRightLabelList(CollectionsKt.mutableListOf("", "", "", "", "12"));
            }
            HealthReportNewView view4 = getView();
            if (view4 != null) {
                view4.onGetWeekWalkHourSuccess(null);
                return;
            }
            return;
        }
        int size = walkDayDataList.size();
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < size; i6++) {
            WalkDayData walkDayData = walkDayDataList.get(i6);
            if (walkDayData != null) {
                i3++;
                float[] statusList = HealthDataUtil.formatWalkItems(walkDayData, false);
                Intrinsics.checkExpressionValueIsNotNull(statusList, "statusList");
                if (!(statusList.length == 0)) {
                    i = 0;
                    i2 = i4;
                    for (float f2 : statusList) {
                        if (f2 == 2.0f) {
                            i++;
                            i2++;
                        }
                    }
                } else {
                    i = 0;
                    i2 = i4;
                }
                if (i > 0) {
                    int iMax = Math.max(i5, i);
                    arrayList.add(new BarChartPoint(-1, i6 + 1, i));
                    i5 = iMax;
                }
                i4 = i2;
            }
        }
        if (i3 > 0) {
            this.mWalkHour = i4 / i3;
        }
        int i7 = i5 != 0 ? i5 : 12;
        HealthReportNewView view5 = getView();
        if (view5 != null) {
            view5.setWalkHourYMaxValue(i7);
        }
        HealthReportNewView view6 = getView();
        if (view6 != null) {
            view6.setWalkHourRightLabelList(CollectionsKt.mutableListOf("", "", "", "", String.valueOf(i7)));
        }
        HealthReportNewView view7 = getView();
        if (view7 != null) {
            view7.onGetWeekWalkHourSuccess(arrayList);
        }
        HealthReportNewView view8 = getView();
        if (view8 != null) {
            view8.updateWalkHourByDay(this.mWalkHour);
        }
    }

    private final void loadWeekStepData(String startDate, String endDate) {
        List<StepDayData> stepDailyDataList = LocalHealthDataManager.getInstance().getStepDailyDataList(startDate, endDate);
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        UserTargetNew userTargetNewQueryUserLatestTarget = GreenDaoUtil.queryUserLatestTarget(runTimeUtil.getUserId());
        int step = userTargetNewQueryUserLatestTarget != null ? userTargetNewQueryUserLatestTarget.getStep() : 5000;
        HealthReportNewView view = getView();
        if (view != null) {
            view.setStepTarget(0);
        }
        List<StepDayData> list = stepDailyDataList;
        if (list == null || list.isEmpty()) {
            HealthReportNewView view2 = getView();
            if (view2 != null) {
                view2.setCurrentWeekStepYMaxValue(step);
            }
            HealthReportNewView view3 = getView();
            if (view3 != null) {
                view3.setCurrentWeekStepRightLabelList(CollectionsKt.mutableListOf("", String.valueOf(step / 4), "", "", String.valueOf(step)));
            }
            HealthReportNewView view4 = getView();
            if (view4 != null) {
                view4.updateCurrentWeekStepDesc(this.mCurrentWeekMaxStep, this.mCurrentWeekPerStep);
            }
            HealthReportNewView view5 = getView();
            if (view5 != null) {
                view5.onGetWeekStepSuccess(null);
                return;
            }
            return;
        }
        int size = stepDailyDataList.size();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        int numSteps = 0;
        int iMax = 0;
        for (int i2 = 0; i2 < size; i2++) {
            StepDayData stepDayData = stepDailyDataList.get(i2);
            if (stepDayData != null && stepDayData.getNumSteps() > 0) {
                i++;
                numSteps += stepDayData.getNumSteps();
                if (stepDayData.getNumSteps() >= step) {
                    arrayList.add(new BarChartPoint(-1, i2 + 1, stepDayData.getNumSteps(), ResourceUtil.getColor(R.color.color_FF4A00)));
                } else {
                    arrayList.add(new BarChartPoint(-1, i2 + 1, stepDayData.getNumSteps()));
                }
                iMax = Math.max(iMax, stepDayData.getNumSteps());
            }
        }
        if (i > 0) {
            this.mCurrentWeekPerStep = numSteps / i;
        }
        this.mCurrentWeekMaxStep = iMax;
        HealthReportNewView view6 = getView();
        if (view6 != null) {
            view6.updateCurrentWeekStepDesc(iMax, this.mCurrentWeekPerStep);
        }
        int iMax2 = Math.max(iMax, step);
        int i3 = 40;
        if (iMax2 > 40) {
            while (i3 < iMax2) {
                i3 *= 2;
            }
        }
        HealthReportNewView view7 = getView();
        if (view7 != null) {
            view7.setCurrentWeekStepYMaxValue(i3);
        }
        HealthReportNewView view8 = getView();
        if (view8 != null) {
            view8.setCurrentWeekStepRightLabelList(CollectionsKt.mutableListOf("", String.valueOf(i3 / 4), "", "", String.valueOf(i3)));
        }
        HealthReportNewView view9 = getView();
        if (view9 != null) {
            view9.onGetWeekStepSuccess(arrayList);
        }
    }

    private final void loadFourWeekStepData() {
        boolean z;
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        int weekStart = runTimeUtil.getWeekStart();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setFirstDayOfWeek(weekStart);
        calendar.set(7, weekStart);
        calendar.add(4, this.mWeekOffset - 3);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int i = 0;
        int iMax = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 4; i < i4; i4 = 4) {
            String str = DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_YMD);
            Intrinsics.checkExpressionValueIsNotNull(str, "DateUtil.format(calendar…DateUtil.DATE_FORMAT_YMD)");
            String str2 = DateUtil.format(calendar, DateUtil.DATE_FORMAT_DM_1);
            Intrinsics.checkExpressionValueIsNotNull(str2, "DateUtil.format(calendar…ateUtil.DATE_FORMAT_DM_1)");
            calendar.add(5, 6);
            String str3 = DateUtil.format(calendar, DateUtil.DATE_FORMAT_DM_1);
            Intrinsics.checkExpressionValueIsNotNull(str3, "DateUtil.format(calendar…ateUtil.DATE_FORMAT_DM_1)");
            arrayList2.add(str2 + '~' + str3);
            String str4 = DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_YMD);
            Intrinsics.checkExpressionValueIsNotNull(str4, "DateUtil.format(calendar…DateUtil.DATE_FORMAT_YMD)");
            calendar.add(5, -6);
            List<StepDayData> stepDailyDataList = LocalHealthDataManager.getInstance().getStepDailyDataList(str, str4);
            List<StepDayData> list = stepDailyDataList;
            if (list == null || list.isEmpty()) {
                i2 = 0;
            } else {
                int numSteps = 0;
                for (StepDayData stepDayData : stepDailyDataList) {
                    if (stepDayData != null) {
                        numSteps += stepDayData.getNumSteps();
                    }
                }
                i2 = numSteps;
            }
            iMax = Math.max(iMax, i2);
            if (i2 > 0) {
                arrayList.add(new BaseCharBean(-1, i + 1, i2));
            }
            calendar.add(4, 1);
            if (i == 2) {
                i3 = i2;
            }
            i++;
        }
        int i5 = 40;
        if (iMax == 0) {
            i5 = 12000;
        } else if (iMax > 40) {
            while (i5 < iMax) {
                i5 *= 2;
            }
        }
        HealthReportNewView view = getView();
        if (view != null) {
            view.setFourWeekStepYMaxValue(i5);
        }
        HealthReportNewView view2 = getView();
        if (view2 != null) {
            view2.setFourWeekStepBottomLabelList(arrayList2);
        }
        HealthReportNewView view3 = getView();
        if (view3 != null) {
            z = false;
            view3.setFourWeekStepRightLabelList(CollectionsKt.mutableListOf("", String.valueOf(i5 / 4), "", "", String.valueOf(i5)));
        } else {
            z = false;
        }
        HealthReportNewView view4 = getView();
        if (view4 != null) {
            view4.onGetFourWeekStepSuccess(arrayList);
        }
        if (i2 == 0) {
            this.mPercent = Integer.MAX_VALUE;
        } else if (i3 == 0) {
            this.mPercent = 100;
        } else {
            this.mPercent = ((i2 - i3) * 100) / i3;
            if (i2 < i3) {
                z = true;
            }
        }
        HealthReportNewView view5 = getView();
        if (view5 != null) {
            view5.updateFourWeekStepDesc(this.mPercent, z);
        }
    }

    private final void caluteItemChartBottomLabel(Calendar calendar) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        String str = DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_DM);
        Intrinsics.checkExpressionValueIsNotNull(str, "DateUtil.format(calendar…,DateUtil.DATE_FORMAT_DM)");
        arrayList.add(str);
        String str2 = DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_DM_1);
        Intrinsics.checkExpressionValueIsNotNull(str2, "DateUtil.format(calendar…ateUtil.DATE_FORMAT_DM_1)");
        arrayList2.add(str2);
        for (int i = 0; i < 6; i++) {
            calendar.add(5, 1);
            arrayList.add(String.valueOf(calendar.get(5)));
            String str3 = DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_DM_1);
            Intrinsics.checkExpressionValueIsNotNull(str3, "DateUtil.format(calendar…ateUtil.DATE_FORMAT_DM_1)");
            arrayList2.add(str3);
        }
        HealthReportNewView view = getView();
        if (view != null) {
            view.setAllDayCalorieXMaxValue(7);
        }
        HealthReportNewView view2 = getView();
        if (view2 != null) {
            view2.setAllDayCalorieXMinValue(1);
        }
        HealthReportNewView view3 = getView();
        if (view3 != null) {
            view3.setStrengthXMaxValue(7);
        }
        HealthReportNewView view4 = getView();
        if (view4 != null) {
            view4.setStrengthXMinValue(1);
        }
        HealthReportNewView view5 = getView();
        if (view5 != null) {
            view5.setWalkHourXMaxValue(7);
        }
        HealthReportNewView view6 = getView();
        if (view6 != null) {
            view6.setWalkHourXMinValue(1);
        }
        HealthReportNewView view7 = getView();
        if (view7 != null) {
            view7.setCurrentWeekStepXMinValue(1);
        }
        HealthReportNewView view8 = getView();
        if (view8 != null) {
            view8.setCurrentWeekStepXMaxValue(7);
        }
        HealthReportNewView view9 = getView();
        if (view9 != null) {
            view9.setFourWeekStepXMinValue(1);
        }
        HealthReportNewView view10 = getView();
        if (view10 != null) {
            view10.setFourWeekStepXMaxValue(4);
        }
        HealthReportNewView view11 = getView();
        if (view11 != null) {
            view11.setAllDayCalorieYMinValue(0);
        }
        HealthReportNewView view12 = getView();
        if (view12 != null) {
            view12.setStrengthYMinValue(0);
        }
        HealthReportNewView view13 = getView();
        if (view13 != null) {
            view13.setWalkHourYMinValue(0);
        }
        HealthReportNewView view14 = getView();
        if (view14 != null) {
            view14.setAllDayCalorieBottomLabelList(arrayList);
        }
        HealthReportNewView view15 = getView();
        if (view15 != null) {
            view15.setStrengthBottomLabelList(arrayList);
        }
        HealthReportNewView view16 = getView();
        if (view16 != null) {
            view16.setWalkHourBottomLabelList(arrayList);
        }
        HealthReportNewView view17 = getView();
        if (view17 != null) {
            view17.setCurrentWeekStepBottomLabelList(arrayList2);
        }
    }

    public final void setWeekStartDate(String startDate) {
        Intrinsics.checkParameterIsNotNull(startDate, "startDate");
        if (startDate.length() == 0) {
            return;
        }
        Calendar currentCalendar = Calendar.getInstance(Locale.CHINA);
        Calendar startCalendar = Calendar.getInstance(Locale.CHINA);
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        int weekStart = runTimeUtil.getWeekStart();
        Intrinsics.checkExpressionValueIsNotNull(currentCalendar, "currentCalendar");
        currentCalendar.setFirstDayOfWeek(weekStart);
        Intrinsics.checkExpressionValueIsNotNull(startCalendar, "startCalendar");
        startCalendar.setFirstDayOfWeek(weekStart);
        currentCalendar.set(7, weekStart);
        try {
            startCalendar.setTime(DateUtil.string2Date(startDate, DateUtil.DATE_FORMAT_YMD));
            this.mWeekOffset = 0;
            while (true) {
                if (currentCalendar.get(1) == startCalendar.get(1) && currentCalendar.get(2) == startCalendar.get(2) && currentCalendar.get(5) == startCalendar.get(5)) {
                    CommonLogUtil.d(this.TAG, "setWeekStartDate startDate=" + startDate + ",mWeekOffset=" + this.mWeekOffset);
                    return;
                }
                this.mWeekOffset--;
                currentCalendar.add(4, -1);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public final boolean hasNextWeek() {
        return this.mWeekOffset < -1;
    }

    public final void switchToPreviourWeek() {
        this.mWeekOffset--;
        startLoadData();
    }

    public final void switchToNextWeek() {
        if (hasNextWeek()) {
            this.mWeekOffset++;
            startLoadData();
        }
    }

    public final void shotLongScreen(final View topView) {
        Intrinsics.checkParameterIsNotNull(topView, "topView");
        HealthReportNewView view = getView();
        if (view != null) {
            view.screenShotSuccess();
        }
        new AsyncTaskUtil(new AsyncTaskUtil.AsyncTaskCallBackAdapter() { // from class: com.ido.life.module.user.healthreport.HealthReportNewPresenter.shotLongScreen.1
            @Override // com.ido.life.util.AsyncTaskUtil.AsyncTaskCallBackAdapter, com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public Object doInBackground(String... arg0) throws Throwable {
                Intrinsics.checkParameterIsNotNull(arg0, "arg0");
                FileUtil.deleteFile(ShareUtil.path);
                Bitmap bitmapFromView = BitmapUtil.getBitmapFromView(topView);
                if (bitmapFromView != null) {
                    BitmapUtil.saveBitmap(bitmapFromView, ShareUtil.path);
                }
                return 1;
            }

            @Override // com.ido.life.util.AsyncTaskUtil.AsyncTaskCallBackAdapter, com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public void onPostExecute(Object result) {
                super.onPostExecute(result);
                HealthReportNewView healthReportNewViewAccess$getView = HealthReportNewPresenter.access$getView(HealthReportNewPresenter.this);
                if (healthReportNewViewAccess$getView != null) {
                    healthReportNewViewAccess$getView.screenShotSuccess();
                }
            }
        }).execute("");
    }

    public final void saveReportPhoto(final View topView) {
        Intrinsics.checkParameterIsNotNull(topView, "topView");
        new AsyncTaskUtil(new AsyncTaskUtil.AsyncTaskCallBackAdapter() { // from class: com.ido.life.module.user.healthreport.HealthReportNewPresenter.saveReportPhoto.1
            @Override // com.ido.life.util.AsyncTaskUtil.AsyncTaskCallBackAdapter, com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public Object doInBackground(String... arg0) {
                Intrinsics.checkParameterIsNotNull(arg0, "arg0");
                FileUtil.deleteFile(ShareUtil.path);
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl.getLoginRegisterLogPath(), HealthReportNewPresenter.this.getTAG(), "保存周报图片的地址 ： " + ShareUtil.path);
                Bitmap bitmapFromView = BitmapUtil.getBitmapFromView(topView);
                if (bitmapFromView != null) {
                    Context context = topView.getContext();
                    Calendar calendar = Calendar.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(calendar, "Calendar.getInstance()");
                    BitmapUtil.saveBitmap(context, bitmapFromView, calendar.getTime().toString());
                    LogPath logPathImpl2 = LogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(logPathImpl2, "LogPathImpl.getInstance()");
                    String loginRegisterLogPath = logPathImpl2.getLoginRegisterLogPath();
                    String tag = HealthReportNewPresenter.this.getTAG();
                    StringBuilder sb = new StringBuilder();
                    sb.append("保存图片成功:");
                    Calendar calendar2 = Calendar.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(calendar2, "Calendar.getInstance()");
                    sb.append(calendar2.getTime().toString());
                    CommonLogUtil.printAndSave(loginRegisterLogPath, tag, sb.toString());
                } else {
                    LogPath logPathImpl3 = LogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(logPathImpl3, "LogPathImpl.getInstance()");
                    CommonLogUtil.printAndSave(logPathImpl3.getLoginRegisterLogPath(), HealthReportNewPresenter.this.getTAG(), "保存图片失败: bitmap==null");
                }
                return 1;
            }
        }).execute("");
    }
}