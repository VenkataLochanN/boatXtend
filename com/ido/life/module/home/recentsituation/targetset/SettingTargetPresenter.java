package com.ido.life.module.home.recentsituation.targetset;

import androidx.core.app.NotificationCompat;
import com.boat.Xtend.two.R;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.base.BasePresenter;
import com.ido.life.base.IBaseView;
import com.ido.life.customview.TargetNewSeekBar;
import com.ido.life.database.model.UnitSetting;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.module.home.fitness.FitnessHelperKt;
import com.ido.life.module.home.recentsituation.RecentSituationDetailActivity;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: SettingTargetPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u0010\u0018\u001a\u00020\u0017J\u0010\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0004H\u0002J\u0010\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0004H\u0002J\u0006\u0010\u001c\u001a\u00020\u0004J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\u000e\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0004J\u0010\u0010 \u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0004H\u0002J\b\u0010!\u001a\u00020\tH\u0002J\b\u0010\"\u001a\u00020\tH\u0002J\u0006\u0010#\u001a\u00020\u0004J\u0006\u0010$\u001a\u00020\tJ\f\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\bJ\u0006\u0010'\u001a\u00020\u0013J\b\u0010(\u001a\u00020\tH\u0002J\u0006\u0010)\u001a\u00020\u001eJ\u0006\u0010*\u001a\u00020\u0017R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0005R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000e\"\u0004\b\u0015\u0010\u0005¨\u0006+"}, d2 = {"Lcom/ido/life/module/home/recentsituation/targetset/SettingTargetPresenter;", "Lcom/ido/life/base/BasePresenter;", "Lcom/ido/life/base/IBaseView;", "pageType", "", "(I)V", "mBmr", "mGoalStepList", "", "", "mTargetMaxValue", "mTargetMinValue", "mTargetValue", "getMTargetValue", "()I", "setMTargetValue", "mUnitSetting", "Lcom/ido/life/database/model/UnitSetting;", "mUserTarget", "Lcom/ido/life/database/model/UserTargetNew;", "getPageType", "setPageType", "activeTimeType", "", "calorieType", "caluteActiveTime", NotificationCompat.CATEGORY_PROGRESS, "caluteCalorie", "caluteProgress", "caluteTargetMaxminValue", "", "caluteTargetValue", "caluteWalk", "getActiveTimeDesc", "getCalorieDesc", "getCalorieTarget", "getDescLabel", "getPropertyList", "Lcom/ido/life/customview/TargetNewSeekBar$Property;", "getUserTargetNew", "getWalkDesc", "initData", "walkType", "app_release"}, k = 1, mv = {1, 1, 16})
public final class SettingTargetPresenter extends BasePresenter<IBaseView> {
    private final int mBmr;
    private List<String> mGoalStepList;
    private int mTargetMaxValue;
    private int mTargetMinValue;
    private int mTargetValue;
    private UnitSetting mUnitSetting;
    private UserTargetNew mUserTarget;
    private int pageType;

    public SettingTargetPresenter() {
        this(0, 1, null);
    }

    public /* synthetic */ SettingTargetPresenter(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? -1 : i);
    }

    public final int getPageType() {
        return this.pageType;
    }

    public final void setPageType(int i) {
        this.pageType = i;
    }

    public SettingTargetPresenter(int i) {
        this.pageType = i;
        this.mGoalStepList = new ArrayList();
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        this.mBmr = FitnessHelperKt.caluteBMR(runTimeUtil.getUserId());
        RunTimeUtil runTimeUtil2 = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil2, "RunTimeUtil.getInstance()");
        UserTargetNew userTarget = GreenDaoUtil.queryUserLatestTarget(runTimeUtil2.getUserId());
        if (userTarget == null) {
            RunTimeUtil runTimeUtil3 = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil3, "RunTimeUtil.getInstance()");
            userTarget = RunTimeUtil.generateDefaultUserTargetNew(runTimeUtil3.getUserId());
        }
        Intrinsics.checkExpressionValueIsNotNull(userTarget, "userTarget");
        this.mUserTarget = userTarget;
        RunTimeUtil runTimeUtil4 = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil4, "RunTimeUtil.getInstance()");
        UnitSetting unitSettingQueryUnitSetting = GreenDaoUtil.queryUnitSetting(runTimeUtil4.getUserId());
        Intrinsics.checkExpressionValueIsNotNull(unitSettingQueryUnitSetting, "GreenDaoUtil.queryUnitSe…til.getInstance().userId)");
        this.mUnitSetting = unitSettingQueryUnitSetting;
    }

    public final int getMTargetValue() {
        return this.mTargetValue;
    }

    public final void setMTargetValue(int i) {
        this.mTargetValue = i;
    }

    public final void initData() {
        int walk;
        caluteTargetMaxminValue();
        int i = this.pageType;
        if (i == RecentSituationDetailActivity.INSTANCE.getPAGE_ACTIVITY_CALORIE()) {
            walk = this.mUserTarget.getCalories();
        } else if (i == RecentSituationDetailActivity.INSTANCE.getPAGE_ACTIVITY_TIME()) {
            walk = ((this.mUserTarget.getActivityTime() / 60) / 5) * 5;
        } else {
            walk = this.mUserTarget.getWalk() / 3600;
        }
        this.mTargetValue = walk;
    }

    private final void caluteTargetMaxminValue() {
        int i = this.pageType;
        if (i == RecentSituationDetailActivity.INSTANCE.getPAGE_ACTIVITY_CALORIE()) {
            this.mTargetMinValue = FitnessHelperKt.caluteCalorieMin(this.mBmr);
            this.mTargetMaxValue = FitnessHelperKt.caluteCalorieMax(this.mBmr);
        } else if (i == RecentSituationDetailActivity.INSTANCE.getPAGE_ACTIVITY_TIME()) {
            this.mTargetMinValue = 5;
            this.mTargetMaxValue = 60;
        } else {
            this.mTargetMinValue = 6;
            this.mTargetMaxValue = 14;
        }
    }

    public final List<TargetNewSeekBar.Property> getPropertyList() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        ArrayList arrayList = new ArrayList();
        int i6 = this.pageType;
        int i7 = 0;
        if (i6 == RecentSituationDetailActivity.INSTANCE.getPAGE_ACTIVITY_CALORIE()) {
            if (this.mTargetMinValue <= 10) {
                arrayList.add(new TargetNewSeekBar.Property(1, ResourceUtil.getColor(R.color.color_FFE451), ResourceUtil.getColor(R.color.color_FFE451), 1, 0.0f, 0.0f, 48, null));
                arrayList.add(new TargetNewSeekBar.Property(1, ResourceUtil.getColor(R.color.color_FFAE6A), ResourceUtil.getColor(R.color.color_FFAE6A), 1, 0.0f, 0.0f, 48, null));
                arrayList.add(new TargetNewSeekBar.Property(1, ResourceUtil.getColor(R.color.color_FF7550), ResourceUtil.getColor(R.color.color_FF7550), 1, 0.0f, 0.0f, 48, null));
                arrayList.add(new TargetNewSeekBar.Property(1, ResourceUtil.getColor(R.color.color_FF4826), ResourceUtil.getColor(R.color.color_FF4826), 0, 0.0f, 0.0f, 48, null));
            } else {
                int iRoundToInt = (MathKt.roundToInt(((double) this.mBmr) * 0.25d) / 10) * 10;
                int iRoundToInt2 = (MathKt.roundToInt(((double) this.mBmr) * 0.35d) / 10) * 10;
                int iRoundToInt3 = (MathKt.roundToInt(((double) this.mBmr) * 0.55d) / 10) * 10;
                IntProgression intProgressionStep = RangesKt.step(RangesKt.until(this.mTargetMinValue, this.mTargetMaxValue), 10);
                int first = intProgressionStep.getFirst();
                int last = intProgressionStep.getLast();
                int step = intProgressionStep.getStep();
                if (step < 0 ? first < last : first > last) {
                    i3 = 0;
                    i4 = 0;
                    i5 = 0;
                } else {
                    int i8 = 0;
                    i3 = 0;
                    i4 = 0;
                    while (true) {
                        if (first >= 0 && iRoundToInt >= first) {
                            i7++;
                        } else if (iRoundToInt + 1 <= first && iRoundToInt2 >= first) {
                            i8++;
                        } else if (iRoundToInt2 + 1 <= first && iRoundToInt3 >= first) {
                            i4++;
                        } else {
                            i3++;
                        }
                        if (first == last) {
                            break;
                        }
                        first += step;
                    }
                    i5 = i7;
                    i7 = i8;
                }
                arrayList.add(new TargetNewSeekBar.Property(i5, ResourceUtil.getColor(R.color.color_FFE451), ResourceUtil.getColor(R.color.color_FFE451), 1, 0.0f, 0.0f, 48, null));
                arrayList.add(new TargetNewSeekBar.Property(i7, ResourceUtil.getColor(R.color.color_FFAE6A), ResourceUtil.getColor(R.color.color_FFAE6A), 1, 0.0f, 0.0f, 48, null));
                arrayList.add(new TargetNewSeekBar.Property(i4, ResourceUtil.getColor(R.color.color_FF7550), ResourceUtil.getColor(R.color.color_FF7550), 1, 0.0f, 0.0f, 48, null));
                arrayList.add(new TargetNewSeekBar.Property(i3, ResourceUtil.getColor(R.color.color_FF4826), ResourceUtil.getColor(R.color.color_FF4826), 0, 0.0f, 0.0f, 48, null));
            }
        } else if (i6 == RecentSituationDetailActivity.INSTANCE.getPAGE_ACTIVITY_TIME()) {
            IntProgression intProgressionStep2 = RangesKt.step(RangesKt.until(this.mTargetMinValue, 61), 5);
            int first2 = intProgressionStep2.getFirst();
            int last2 = intProgressionStep2.getLast();
            int step2 = intProgressionStep2.getStep();
            if (step2 < 0 ? first2 < last2 : first2 > last2) {
                i = 0;
                i2 = 0;
            } else {
                i = 0;
                int i9 = 0;
                while (true) {
                    if (first2 < 0 || 10 < first2) {
                        if (11 <= first2 && 20 >= first2) {
                            i7++;
                        } else if (21 <= first2 && 40 >= first2) {
                            i9++;
                        } else {
                            i++;
                        }
                    }
                    if (first2 == last2) {
                        break;
                    }
                    first2 += step2;
                }
                i2 = i7;
                i7 = i9;
            }
            arrayList.add(new TargetNewSeekBar.Property(1, ResourceUtil.getColor(R.color.color_D1F3A7), ResourceUtil.getColor(R.color.color_D1F3A7), 1, 0.0f, 0.0f, 48, null));
            arrayList.add(new TargetNewSeekBar.Property(i2, ResourceUtil.getColor(R.color.color_82ED74), ResourceUtil.getColor(R.color.color_82ED74), 1, 0.0f, 0.0f, 48, null));
            arrayList.add(new TargetNewSeekBar.Property(i7, ResourceUtil.getColor(R.color.color_31E147), ResourceUtil.getColor(R.color.color_31E147), 1, 0.0f, 0.0f, 48, null));
            arrayList.add(new TargetNewSeekBar.Property(i, ResourceUtil.getColor(R.color.color_00BE47), ResourceUtil.getColor(R.color.color_00BE47), 0, 0.0f, 0.0f, 48, null));
        } else {
            arrayList.add(new TargetNewSeekBar.Property(2, ResourceUtil.getColor(R.color.color_9AE4F4), ResourceUtil.getColor(R.color.color_9AE4F4), 1, 0.0f, 0.0f, 48, null));
            arrayList.add(new TargetNewSeekBar.Property(2, ResourceUtil.getColor(R.color.color_67DAF4), ResourceUtil.getColor(R.color.color_67DAF4), 1, 0.0f, 0.0f, 48, null));
            arrayList.add(new TargetNewSeekBar.Property(2, ResourceUtil.getColor(R.color.color_3DB6F1), ResourceUtil.getColor(R.color.color_3DB6F1), 1, 0.0f, 0.0f, 48, null));
            arrayList.add(new TargetNewSeekBar.Property(2, ResourceUtil.getColor(R.color.color_079BEC), ResourceUtil.getColor(R.color.color_079BEC), 0, 0.0f, 0.0f, 48, null));
        }
        return arrayList;
    }

    public final int caluteTargetValue(int progress) {
        int i = this.pageType;
        if (i == RecentSituationDetailActivity.INSTANCE.getPAGE_ACTIVITY_CALORIE()) {
            this.mTargetValue = caluteCalorie(progress);
        } else if (i == RecentSituationDetailActivity.INSTANCE.getPAGE_ACTIVITY_TIME()) {
            this.mTargetValue = caluteActiveTime(progress);
        } else {
            this.mTargetValue = caluteWalk(progress);
        }
        return this.mTargetValue;
    }

    public final int caluteProgress() {
        int i = this.pageType;
        if (i == RecentSituationDetailActivity.INSTANCE.getPAGE_ACTIVITY_CALORIE()) {
            return (this.mTargetValue - this.mTargetMinValue) / 10;
        }
        if (i == RecentSituationDetailActivity.INSTANCE.getPAGE_ACTIVITY_TIME()) {
            return (this.mTargetValue - this.mTargetMinValue) / 5;
        }
        return this.mTargetValue - this.mTargetMinValue;
    }

    private final int caluteCalorie(int progress) {
        return this.mTargetMinValue + (progress * 10);
    }

    private final int caluteActiveTime(int progress) {
        return this.mTargetMinValue + (progress * 5);
    }

    private final int caluteWalk(int progress) {
        return this.mTargetMinValue + progress;
    }

    public final String getDescLabel() {
        int i = this.pageType;
        return i == RecentSituationDetailActivity.INSTANCE.getPAGE_ACTIVITY_CALORIE() ? getCalorieDesc() : i == RecentSituationDetailActivity.INSTANCE.getPAGE_ACTIVITY_TIME() ? getActiveTimeDesc() : getWalkDesc();
    }

    private final String getCalorieDesc() {
        if (MathKt.roundToInt(this.mBmr * 0.2f) < 10) {
            int i = this.mTargetValue;
            if (i >= 0 && 20 > i) {
                String languageText = LanguageUtil.getLanguageText(R.string.fitness_detail_less);
                Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…ring.fitness_detail_less)");
                return languageText;
            }
            if (20 <= i && 30 > i) {
                String languageText2 = LanguageUtil.getLanguageText(R.string.fitness_detail_medium);
                Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…ng.fitness_detail_medium)");
                return languageText2;
            }
            if (30 <= i && 40 > i) {
                String languageText3 = LanguageUtil.getLanguageText(R.string.fitness_detail_much);
                Intrinsics.checkExpressionValueIsNotNull(languageText3, "LanguageUtil.getLanguage…ring.fitness_detail_much)");
                return languageText3;
            }
            String languageText4 = LanguageUtil.getLanguageText(R.string.fitness_detail_more);
            Intrinsics.checkExpressionValueIsNotNull(languageText4, "LanguageUtil.getLanguage…ring.fitness_detail_more)");
            return languageText4;
        }
        int iRoundToInt = (MathKt.roundToInt(((double) this.mBmr) * 0.25d) / 10) * 10;
        int iRoundToInt2 = (MathKt.roundToInt(((double) this.mBmr) * 0.35d) / 10) * 10;
        int iRoundToInt3 = (MathKt.roundToInt(((double) this.mBmr) * 0.55d) / 10) * 10;
        int i2 = this.mTargetValue;
        if (i2 >= 0 && iRoundToInt >= i2) {
            String languageText5 = LanguageUtil.getLanguageText(R.string.fitness_detail_less);
            Intrinsics.checkExpressionValueIsNotNull(languageText5, "LanguageUtil.getLanguage…ring.fitness_detail_less)");
            return languageText5;
        }
        if (iRoundToInt + 1 <= i2 && iRoundToInt2 >= i2) {
            String languageText6 = LanguageUtil.getLanguageText(R.string.fitness_detail_medium);
            Intrinsics.checkExpressionValueIsNotNull(languageText6, "LanguageUtil.getLanguage…ng.fitness_detail_medium)");
            return languageText6;
        }
        if (iRoundToInt2 + 1 <= i2 && iRoundToInt3 >= i2) {
            String languageText7 = LanguageUtil.getLanguageText(R.string.fitness_detail_much);
            Intrinsics.checkExpressionValueIsNotNull(languageText7, "LanguageUtil.getLanguage…ring.fitness_detail_much)");
            return languageText7;
        }
        String languageText8 = LanguageUtil.getLanguageText(R.string.fitness_detail_more);
        Intrinsics.checkExpressionValueIsNotNull(languageText8, "LanguageUtil.getLanguage…ring.fitness_detail_more)");
        return languageText8;
    }

    private final String getActiveTimeDesc() {
        int i = this.mTargetValue;
        if (i >= 0 && 10 > i) {
            String languageText = LanguageUtil.getLanguageText(R.string.fitness_detail_less);
            Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…ring.fitness_detail_less)");
            return languageText;
        }
        if (10 <= i && 20 > i) {
            String languageText2 = LanguageUtil.getLanguageText(R.string.fitness_detail_medium);
            Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…ng.fitness_detail_medium)");
            return languageText2;
        }
        if (20 <= i && 40 > i) {
            String languageText3 = LanguageUtil.getLanguageText(R.string.fitness_detail_much);
            Intrinsics.checkExpressionValueIsNotNull(languageText3, "LanguageUtil.getLanguage…ring.fitness_detail_much)");
            return languageText3;
        }
        String languageText4 = LanguageUtil.getLanguageText(R.string.fitness_detail_more);
        Intrinsics.checkExpressionValueIsNotNull(languageText4, "LanguageUtil.getLanguage…ring.fitness_detail_more)");
        return languageText4;
    }

    private final String getWalkDesc() {
        int i = this.mTargetValue;
        if (i >= 0 && 8 > i) {
            String languageText = LanguageUtil.getLanguageText(R.string.fitness_detail_less);
            Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…ring.fitness_detail_less)");
            return languageText;
        }
        if (8 <= i && 10 > i) {
            String languageText2 = LanguageUtil.getLanguageText(R.string.fitness_detail_medium);
            Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…ng.fitness_detail_medium)");
            return languageText2;
        }
        if (10 <= i && 12 > i) {
            String languageText3 = LanguageUtil.getLanguageText(R.string.fitness_detail_much);
            Intrinsics.checkExpressionValueIsNotNull(languageText3, "LanguageUtil.getLanguage…ring.fitness_detail_much)");
            return languageText3;
        }
        String languageText4 = LanguageUtil.getLanguageText(R.string.fitness_detail_more);
        Intrinsics.checkExpressionValueIsNotNull(languageText4, "LanguageUtil.getLanguage…ring.fitness_detail_more)");
        return languageText4;
    }

    /* JADX INFO: renamed from: getUserTargetNew, reason: from getter */
    public final UserTargetNew getMUserTarget() {
        return this.mUserTarget;
    }

    public final int getCalorieTarget() {
        return this.mUserTarget.getCalories();
    }

    public final boolean calorieType() {
        return this.pageType == RecentSituationDetailActivity.INSTANCE.getPAGE_ACTIVITY_CALORIE();
    }

    public final boolean activeTimeType() {
        return this.pageType == RecentSituationDetailActivity.INSTANCE.getPAGE_ACTIVITY_TIME();
    }

    public final boolean walkType() {
        return this.pageType == RecentSituationDetailActivity.INSTANCE.getPAGE_WALKING();
    }
}