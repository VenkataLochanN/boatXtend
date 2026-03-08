package com.ido.life.module.home.fitness;

import androidx.core.app.NotificationCompat;
import com.ido.life.bean.GradientBarPoint;
import com.ido.life.enums.StageInfoEnum;
import com.ido.life.module.home.chartdetail.vertical.IBaseDetailView;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: IFitnessDetailDetailView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u001f\bf\u0018\u000022\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u001e\u0010\b\u001a\u00020\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\n\u001a\u00020\u000bH&J\u001e\u0010\f\u001a\u00020\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\n\u001a\u00020\u000bH&J\u001e\u0010\r\u001a\u00020\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\n\u001a\u00020\u000bH&J\u001e\u0010\u000e\u001a\u00020\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u001e\u0010\u0010\u001a\u00020\u00052\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\n\u001a\u00020\u000bH&J\u001e\u0010\u0012\u001a\u00020\u00052\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\n\u001a\u00020\u000bH&J\u001e\u0010\u0013\u001a\u00020\u00052\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\n\u001a\u00020\u000bH&J\u001e\u0010\u0014\u001a\u00020\u00052\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u001e\u0010\u0016\u001a\u00020\u00052\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\n\u001a\u00020\u000bH&J\u001e\u0010\u0018\u001a\u00020\u00052\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\n\u001a\u00020\u000bH&J\u001e\u0010\u0019\u001a\u00020\u00052\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\n\u001a\u00020\u000bH&J\u001e\u0010\u001a\u001a\u00020\u00052\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\n\u001a\u00020\u000bH&J\b\u0010\u001b\u001a\u00020\u0005H&J\u0010\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u000bH&J\u0018\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 H&J\u0010\u0010\"\u001a\u00020\u00052\u0006\u0010#\u001a\u00020 H&J \u0010$\u001a\u00020\u00052\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020 H&J\u0018\u0010*\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 H&J \u0010+\u001a\u00020\u00052\u0006\u0010,\u001a\u00020 2\u0006\u0010-\u001a\u00020 2\u0006\u0010.\u001a\u00020 H&J\u0010\u0010/\u001a\u00020\u00052\u0006\u00100\u001a\u00020 H&J \u00101\u001a\u00020\u00052\u0006\u0010,\u001a\u00020 2\u0006\u0010-\u001a\u00020 2\u0006\u0010.\u001a\u00020 H&J\u0010\u00102\u001a\u00020\u00052\u0006\u00100\u001a\u00020 H&J\u0010\u00103\u001a\u00020\u00052\u0006\u00104\u001a\u00020 H&J \u00105\u001a\u00020\u00052\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020 H&J\u0012\u00106\u001a\u00020\u00052\b\u00107\u001a\u0004\u0018\u00010\u0007H&J\u0018\u00108\u001a\u00020\u00052\u0006\u00109\u001a\u00020 2\u0006\u0010:\u001a\u00020 H&J\u0018\u0010;\u001a\u00020\u00052\u0006\u00109\u001a\u00020 2\u0006\u0010:\u001a\u00020 H&J\u0018\u0010<\u001a\u00020\u00052\u0006\u00109\u001a\u00020 2\u0006\u0010:\u001a\u00020 H&J\u0018\u0010=\u001a\u00020\u00052\u0006\u0010>\u001a\u00020 2\u0006\u0010?\u001a\u00020\u0007H&J\u0010\u0010@\u001a\u00020\u00052\u0006\u0010%\u001a\u00020&H&J\u0010\u0010A\u001a\u00020\u00052\u0006\u0010B\u001a\u00020 H&J \u0010C\u001a\u00020\u00052\u0006\u0010,\u001a\u00020 2\u0006\u0010-\u001a\u00020 2\u0006\u0010.\u001a\u00020 H&J \u0010D\u001a\u00020\u00052\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020 H&J\u0010\u0010E\u001a\u00020\u00052\u0006\u00100\u001a\u00020 H&J\u0018\u0010F\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 H&¨\u0006G"}, d2 = {"Lcom/ido/life/module/home/fitness/IFitnessDetailDetailView;", "Lcom/ido/life/module/home/chartdetail/vertical/IBaseDetailView;", "", "Lcom/ido/life/bean/GradientBarPoint;", "onActiveCalorieDataLoadFailed", "", "message", "", "onActiveCalorieDayDataLoadSuccess", "calorieList", "showChartAnimator", "", "onActiveCalorieMonthDataLoadSuccess", "onActiveCalorieWeekDataLoadSuccess", "onActiveCalorieYearDataLoadSuccess", "onActiveTimeDataLoadFailed", "onActiveTimeDayDataLoadSuccess", "activeList", "onActiveTimeMonthDataLoadSuccess", "onActiveTimeWeekDataLoadSuccess", "onActiveTimeYearDataLoadSuccess", "onWalkDataLoadFailed", "onWalkDayDataLoadSuccess", "walkList", "onWalkMonthDataLoadSuccess", "onWalkWeekDataLoadSuccess", "onWalkYearDataLoadSuccess", "refreshImageFitness", "showWearTime", "show", "updateActiveCalorieYMaxmin", "maxValue", "", "minValue", "updateActivePer", "activeTimePer", "updateActiveRecentSituation", "stage", "Lcom/ido/life/enums/StageInfoEnum;", "value", "", "compareState", "updateActiveTimeYMaxmin", "updateActivityCalorieProgressMaxmin", "max", "min", NotificationCompat.CATEGORY_PROGRESS, "updateActivityCalorieTarget", "target", "updateActivityTimeProgressMaxmin", "updateActivityTimeTarget", "updateCaloriePer", "perCalorie", "updateCalorieRecentSituation", "updateDate", "dateDesc", "updateLeftActiveTime", "actualValue", "targetValue", "updateLeftActivityCalorie", "updateLeftWalk", "updateRecentScore", "score", "scoreDesc", "updateRecentStage", "updateWalkPer", "walkPer", "updateWalkProgressMaxmin", "updateWalkRecentSituation", "updateWalkTarget", "updateWalkYMaxmin", "app_release"}, k = 1, mv = {1, 1, 16})
public interface IFitnessDetailDetailView extends IBaseDetailView<List<? extends GradientBarPoint>, List<? extends GradientBarPoint>, List<? extends GradientBarPoint>, List<? extends GradientBarPoint>> {
    void onActiveCalorieDataLoadFailed(String message);

    void onActiveCalorieDayDataLoadSuccess(List<? extends GradientBarPoint> calorieList, boolean showChartAnimator);

    void onActiveCalorieMonthDataLoadSuccess(List<? extends GradientBarPoint> calorieList, boolean showChartAnimator);

    void onActiveCalorieWeekDataLoadSuccess(List<? extends GradientBarPoint> calorieList, boolean showChartAnimator);

    void onActiveCalorieYearDataLoadSuccess(List<? extends GradientBarPoint> calorieList, boolean showChartAnimator);

    void onActiveTimeDataLoadFailed(String message);

    void onActiveTimeDayDataLoadSuccess(List<? extends GradientBarPoint> activeList, boolean showChartAnimator);

    void onActiveTimeMonthDataLoadSuccess(List<? extends GradientBarPoint> activeList, boolean showChartAnimator);

    void onActiveTimeWeekDataLoadSuccess(List<? extends GradientBarPoint> activeList, boolean showChartAnimator);

    void onActiveTimeYearDataLoadSuccess(List<? extends GradientBarPoint> activeList, boolean showChartAnimator);

    void onWalkDataLoadFailed(String message);

    void onWalkDayDataLoadSuccess(List<? extends GradientBarPoint> walkList, boolean showChartAnimator);

    void onWalkMonthDataLoadSuccess(List<? extends GradientBarPoint> walkList, boolean showChartAnimator);

    void onWalkWeekDataLoadSuccess(List<? extends GradientBarPoint> walkList, boolean showChartAnimator);

    void onWalkYearDataLoadSuccess(List<? extends GradientBarPoint> walkList, boolean showChartAnimator);

    void refreshImageFitness();

    void showWearTime(boolean show);

    void updateActiveCalorieYMaxmin(int maxValue, int minValue);

    void updateActivePer(int activeTimePer);

    void updateActiveRecentSituation(StageInfoEnum stage, float value, int compareState);

    void updateActiveTimeYMaxmin(int maxValue, int minValue);

    void updateActivityCalorieProgressMaxmin(int max, int min, int progress);

    void updateActivityCalorieTarget(int target);

    void updateActivityTimeProgressMaxmin(int max, int min, int progress);

    void updateActivityTimeTarget(int target);

    void updateCaloriePer(int perCalorie);

    void updateCalorieRecentSituation(StageInfoEnum stage, float value, int compareState);

    void updateDate(String dateDesc);

    void updateLeftActiveTime(int actualValue, int targetValue);

    void updateLeftActivityCalorie(int actualValue, int targetValue);

    void updateLeftWalk(int actualValue, int targetValue);

    void updateRecentScore(int score, String scoreDesc);

    void updateRecentStage(StageInfoEnum stage);

    void updateWalkPer(int walkPer);

    void updateWalkProgressMaxmin(int max, int min, int progress);

    void updateWalkRecentSituation(StageInfoEnum stage, float value, int compareState);

    void updateWalkTarget(int target);

    void updateWalkYMaxmin(int maxValue, int minValue);
}