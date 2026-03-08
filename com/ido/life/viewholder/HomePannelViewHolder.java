package com.ido.life.viewholder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.customview.maincard.MainPannelCircleView;
import com.ido.life.database.model.ActiveTimeDayData;
import com.ido.life.database.model.CalorieDayData;
import com.ido.life.database.model.WalkDayData;
import com.ido.life.module.home.IHomeView;
import com.ido.life.util.GreenDaoUtil;

/* JADX INFO: loaded from: classes3.dex */
public class HomePannelViewHolder extends BaseHomeItemViewHolder {

    @BindView(R.id.ll_press_bottom)
    public LinearLayout llPressBottom;

    @BindView(R.id.ll_press_top)
    public LinearLayout llPressTop;

    @BindView(R.id.ll_walk_bottom)
    public LinearLayout llWalkBottom;

    @BindView(R.id.ll_walk_top)
    public LinearLayout llWalkTop;

    @BindView(R.id.ll_activity_bottom)
    public LinearLayout ll_activity_bottom;

    @BindView(R.id.ll_activity_top)
    public LinearLayout ll_activity_top;

    @BindView(R.id.img_active)
    public MainPannelCircleView mActiveCircleView;
    private ValueAnimator mAnimator;
    private boolean mHasActiveData;
    private boolean mHasCalorieData;
    private boolean mHasWalkData;

    @BindView(R.id.img_press)
    public MainPannelCircleView mPressCircleView;

    @BindView(R.id.tv_active)
    public TextView mTvActiveTitle;

    @BindView(R.id.tv_calorie_data_change)
    TextView mTvCalorieDataChange;

    @BindView(R.id.tv_calory)
    public TextView mTvCalory;

    @BindView(R.id.tv_calory_unit)
    public TextView mTvCaloryUnit;

    @BindView(R.id.tv_min)
    public TextView mTvMin;

    @BindView(R.id.tv_min_unit)
    public TextView mTvMinUnit;

    @BindView(R.id.tv_press)
    public TextView mTvPressTitle;

    @BindView(R.id.tv_strength_data_change)
    TextView mTvStrengthDataChange;

    @BindView(R.id.tv_hour)
    public TextView mTvWalkHour;

    @BindView(R.id.tv_hour_data_change)
    TextView mTvWalkHourDataChange;

    @BindView(R.id.tv_walk)
    public TextView mTvWalkTitle;

    @BindView(R.id.tv_hour_unit)
    public TextView mTvWalkUnit;

    @BindView(R.id.img_walk)
    public MainPannelCircleView mWalkCircleView;

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder
    protected String getDownloadDataName() {
        return null;
    }

    public HomePannelViewHolder(View view, IHomeView iHomeView) {
        super(view, iHomeView);
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder
    public void checkAnimator() {
        if (this.mHomeView != null && this.mHomeView.hasLogin() && (!this.mHasCalorieData || !this.mHasActiveData || !this.mHasWalkData)) {
            int iQueryLatestDataLoadState = GreenDaoUtil.queryLatestDataLoadState(getUserId(), CalorieDayData.class.getSimpleName());
            int iQueryLatestDataLoadState2 = GreenDaoUtil.queryLatestDataLoadState(getUserId(), ActiveTimeDayData.class.getSimpleName());
            int iQueryLatestDataLoadState3 = GreenDaoUtil.queryLatestDataLoadState(getUserId(), WalkDayData.class.getSimpleName());
            if ((iQueryLatestDataLoadState != 4 && iQueryLatestDataLoadState != 3) || ((iQueryLatestDataLoadState2 != 4 && iQueryLatestDataLoadState2 != 3) || (iQueryLatestDataLoadState3 != 4 && iQueryLatestDataLoadState3 != 3))) {
                startLoadingAnimator();
            } else {
                stopLoadingAnimator();
            }
        } else {
            stopLoadingAnimator();
        }
        setClickListener(null);
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder
    protected void setClickListener(View.OnClickListener onClickListener) {
        int iQueryLatestDataLoadState;
        int iQueryLatestDataLoadState2;
        if (this.mHasActiveData || !this.mHomeView.hasLogin() || (iQueryLatestDataLoadState = GreenDaoUtil.queryLatestDataLoadState(getUserId(), ActiveTimeDayData.class.getSimpleName())) == 3 || iQueryLatestDataLoadState == 4) {
            this.mPressCircleView.setOnClickListener(this.mHomeView);
            this.llPressTop.setOnClickListener(this.mHomeView);
            this.llPressBottom.setOnClickListener(this.mHomeView);
        } else {
            this.mPressCircleView.setOnClickListener(getDefaultClickListener());
            this.llPressTop.setOnClickListener(getDefaultClickListener());
            this.llPressBottom.setOnClickListener(getDefaultClickListener());
        }
        if (this.mHasCalorieData) {
            this.mActiveCircleView.setOnClickListener(this.mHomeView);
            this.ll_activity_bottom.setOnClickListener(this.mHomeView);
            this.ll_activity_top.setOnClickListener(this.mHomeView);
        } else if (!this.mHomeView.hasLogin() || (iQueryLatestDataLoadState2 = GreenDaoUtil.queryLatestDataLoadState(getUserId(), CalorieDayData.class.getSimpleName())) == 3 || iQueryLatestDataLoadState2 == 4) {
            this.mActiveCircleView.setOnClickListener(this.mHomeView);
            this.ll_activity_top.setOnClickListener(this.mHomeView);
            this.ll_activity_bottom.setOnClickListener(this.mHomeView);
        } else {
            this.mActiveCircleView.setOnClickListener(getDefaultClickListener());
            this.ll_activity_top.setOnClickListener(getDefaultClickListener());
            this.ll_activity_bottom.setOnClickListener(getDefaultClickListener());
        }
        if (this.mHasWalkData) {
            this.mWalkCircleView.setOnClickListener(this.mHomeView);
            this.llWalkTop.setOnClickListener(this.mHomeView);
            this.llWalkBottom.setOnClickListener(this.mHomeView);
            return;
        }
        if (this.mHomeView.hasLogin()) {
            int iQueryLatestDataLoadState3 = GreenDaoUtil.queryLatestDataLoadState(getUserId(), WalkDayData.class.getSimpleName());
            if (iQueryLatestDataLoadState3 == 3 || iQueryLatestDataLoadState3 == 4) {
                this.mWalkCircleView.setOnClickListener(this.mHomeView);
                this.llWalkTop.setOnClickListener(this.mHomeView);
                this.llWalkBottom.setOnClickListener(this.mHomeView);
                return;
            } else {
                this.mWalkCircleView.setOnClickListener(getDefaultClickListener());
                this.llWalkTop.setOnClickListener(getDefaultClickListener());
                this.llWalkBottom.setOnClickListener(getDefaultClickListener());
                return;
            }
        }
        this.mWalkCircleView.setOnClickListener(this.mHomeView);
        this.llWalkTop.setOnClickListener(this.mHomeView);
        this.llWalkBottom.setOnClickListener(this.mHomeView);
    }

    @Override // com.ido.life.viewholder.BaseHomeViewHolder
    public void refreshPage() {
        boolean z;
        int iIntValue;
        int iIntValue2;
        int iIntValue3;
        CommonLogUtil.printAndSave("开始绑定面板数据");
        this.mTvActiveTitle.setText(LanguageUtil.getLanguageText(R.string.title_active));
        this.mTvCaloryUnit.setText(LanguageUtil.getLanguageText(R.string.public_heat_calorie_short));
        this.mTvPressTitle.setText(LanguageUtil.getLanguageText(R.string.home_sport_strength_higher));
        this.mTvMinUnit.setText(LanguageUtil.getLanguageText(R.string.min_unit));
        this.mTvWalkTitle.setText(LanguageUtil.getLanguageText(R.string.title_walk));
        this.mTvWalkUnit.setText(LanguageUtil.getLanguageText(R.string.public_time_hour));
        this.mActiveCircleView.setCenterImageRes(R.mipmap.home_calories);
        this.mPressCircleView.setCenterImageRes(R.mipmap.home_strength);
        this.mWalkCircleView.setCenterImageRes(R.mipmap.home_walk);
        Pair<Pair<Boolean, Integer>, Integer> todayActive = this.mHomeView.getTodayActive();
        Pair<Pair<Boolean, Integer>, Integer> todayActiveTime = this.mHomeView.getTodayActiveTime();
        Pair<Pair<Boolean, Integer>, Integer> todayWalk = this.mHomeView.getTodayWalk();
        boolean z2 = true;
        if (todayActive == null || todayActive.first == null || ((Integer) ((Pair) todayActive.first).second).intValue() <= 0) {
            this.mTvCalory.setText("--");
            this.mTvCaloryUnit.setText("");
            this.mTvCalorieDataChange.setVisibility(8);
            this.mTvCalory.setTag(0);
            this.mActiveCircleView.setMaxProgress(0);
            this.mActiveCircleView.setProgress(0);
            if (todayActive != null && todayActive.first != null && ((Integer) ((Pair) todayActive.first).second).intValue() != -1) {
                this.mHasCalorieData = true;
            } else {
                this.mHasActiveData = false;
            }
            z = false;
        } else {
            this.mHasCalorieData = true;
            if (!this.mFirstRefresh && ((Boolean) ((Pair) todayActive.first).first).booleanValue()) {
                try {
                    iIntValue3 = ((Integer) this.mTvCalory.getTag()).intValue();
                } catch (Exception unused) {
                    iIntValue3 = 0;
                }
                int iAbs = Math.abs(((Integer) ((Pair) todayActive.first).second).intValue() - iIntValue3);
                if (iAbs == 0) {
                    this.mTvCalorieDataChange.setVisibility(8);
                } else {
                    this.mTvCalorieDataChange.setText("+" + iAbs);
                    this.mTvCalorieDataChange.setVisibility(0);
                    z = true;
                    this.mActiveCircleView.setMaxProgress(((Integer) todayActive.second).intValue());
                    this.mActiveCircleView.setProgress(((Integer) ((Pair) todayActive.first).second).intValue());
                    this.mTvCalory.setTag(((Pair) todayActive.first).second);
                    this.mTvCalory.setText(String.valueOf(((Pair) todayActive.first).second));
                }
            } else {
                this.mTvCalorieDataChange.setVisibility(8);
            }
            z = false;
            this.mActiveCircleView.setMaxProgress(((Integer) todayActive.second).intValue());
            this.mActiveCircleView.setProgress(((Integer) ((Pair) todayActive.first).second).intValue());
            this.mTvCalory.setTag(((Pair) todayActive.first).second);
            this.mTvCalory.setText(String.valueOf(((Pair) todayActive.first).second));
        }
        if (todayActiveTime == null || todayActiveTime.first == null || ((Integer) ((Pair) todayActiveTime.first).second).intValue() <= 0) {
            if (todayActiveTime != null && todayActiveTime.first != null && ((Integer) ((Pair) todayActiveTime.first).second).intValue() != -1) {
                this.mHasActiveData = true;
            } else {
                this.mHasActiveData = false;
            }
            this.mTvMin.setText("--");
            this.mTvMinUnit.setText("");
            this.mPressCircleView.setMaxProgress(0);
            this.mPressCircleView.setProgress(0);
        } else {
            this.mHasActiveData = true;
            if (!this.mFirstRefresh && ((Boolean) ((Pair) todayActiveTime.first).first).booleanValue()) {
                try {
                    iIntValue2 = ((Integer) this.mTvMin.getTag()).intValue();
                } catch (Exception unused2) {
                    iIntValue2 = 0;
                }
                int iAbs2 = Math.abs(((Integer) ((Pair) todayActiveTime.first).second).intValue() - iIntValue2);
                if (iAbs2 == 0) {
                    this.mTvStrengthDataChange.setVisibility(8);
                } else {
                    this.mTvStrengthDataChange.setText("+" + iAbs2);
                    this.mTvStrengthDataChange.setVisibility(0);
                    z = true;
                }
            } else {
                this.mTvStrengthDataChange.setVisibility(8);
            }
            this.mTvMin.setText(String.valueOf(((Pair) todayActiveTime.first).second));
            this.mTvMin.setTag(((Pair) todayActiveTime.first).second);
            this.mPressCircleView.setMaxProgress(((Integer) todayActiveTime.second).intValue());
            this.mPressCircleView.setProgress(((Integer) ((Pair) todayActiveTime.first).second).intValue());
        }
        if (todayWalk == null || todayWalk.first == null || ((Integer) ((Pair) todayWalk.first).second).intValue() <= 0) {
            if (todayWalk != null && todayWalk.first != null && ((Integer) ((Pair) todayWalk.first).second).intValue() != -1) {
                this.mHasWalkData = true;
            } else {
                this.mHasWalkData = false;
            }
            this.mTvWalkHour.setText("--");
            this.mTvWalkUnit.setText("");
            this.mWalkCircleView.setMaxProgress(0);
            this.mWalkCircleView.setProgress(0);
        } else {
            this.mHasWalkData = true;
            if (!this.mFirstRefresh && ((Boolean) ((Pair) todayWalk.first).first).booleanValue()) {
                try {
                    iIntValue = ((Integer) this.mTvWalkHour.getTag()).intValue();
                } catch (Exception unused3) {
                    iIntValue = 0;
                }
                int iAbs3 = Math.abs(((Integer) ((Pair) todayWalk.first).second).intValue() - iIntValue);
                if (iAbs3 == 0) {
                    this.mTvWalkHourDataChange.setVisibility(8);
                    z2 = z;
                } else {
                    this.mTvWalkHourDataChange.setText("+" + iAbs3);
                    this.mTvWalkHourDataChange.setVisibility(0);
                }
                z = z2;
            } else {
                this.mTvWalkHourDataChange.setVisibility(8);
            }
            this.mTvWalkHour.setTag(((Pair) todayWalk.first).second);
            this.mTvWalkHour.setText(String.valueOf(((Pair) todayWalk.first).second));
            this.mWalkCircleView.setMaxProgress(((Integer) todayWalk.second).intValue());
            this.mWalkCircleView.setProgress(((Integer) ((Pair) todayWalk.first).second).intValue());
        }
        if (z) {
            startAnimator();
        }
        this.mFirstRefresh = false;
    }

    private ValueAnimator getAnimator() {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(2000L);
        valueAnimator.setStartDelay(500L);
        valueAnimator.setTarget(this.mTvCalorieDataChange);
        valueAnimator.setFloatValues(-20.0f, 20.0f);
        valueAnimator.setRepeatMode(1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.life.viewholder.-$$Lambda$HomePannelViewHolder$tZek4N6IM1VGRE9qLAyxd9ptMEQ
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                this.f$0.lambda$getAnimator$0$HomePannelViewHolder(valueAnimator2);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.ido.life.viewholder.HomePannelViewHolder.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                if (HomePannelViewHolder.this.mTvCalorieDataChange.getVisibility() == 0) {
                    HomePannelViewHolder.this.mTvCalorieDataChange.setPadding(0, 0, 0, 0);
                }
                if (HomePannelViewHolder.this.mTvStrengthDataChange.getVisibility() == 0) {
                    HomePannelViewHolder.this.mTvStrengthDataChange.setPadding(0, 0, 0, 0);
                }
                if (HomePannelViewHolder.this.mTvWalkHourDataChange.getVisibility() == 0) {
                    HomePannelViewHolder.this.mTvWalkHourDataChange.setPadding(0, 0, 0, 0);
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                if (HomePannelViewHolder.this.mTvCalorieDataChange != null && HomePannelViewHolder.this.mTvCalorieDataChange.getVisibility() == 0) {
                    HomePannelViewHolder.this.mTvCalorieDataChange.setVisibility(8);
                    HomePannelViewHolder.this.mTvCalorieDataChange.setPadding(0, 0, 0, 0);
                }
                if (HomePannelViewHolder.this.mTvStrengthDataChange != null && HomePannelViewHolder.this.mTvStrengthDataChange.getVisibility() == 0) {
                    HomePannelViewHolder.this.mTvStrengthDataChange.setVisibility(8);
                    HomePannelViewHolder.this.mTvStrengthDataChange.setPadding(0, 0, 0, 0);
                }
                if (HomePannelViewHolder.this.mTvWalkHourDataChange == null || HomePannelViewHolder.this.mTvWalkHourDataChange.getVisibility() != 0) {
                    return;
                }
                HomePannelViewHolder.this.mTvWalkHourDataChange.setVisibility(8);
                HomePannelViewHolder.this.mTvWalkHourDataChange.setPadding(0, 0, 0, 0);
            }
        });
        return valueAnimator;
    }

    public /* synthetic */ void lambda$getAnimator$0$HomePannelViewHolder(ValueAnimator valueAnimator) {
        TextView textView = this.mTvCalorieDataChange;
        if (textView != null && textView.getVisibility() == 0) {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            if (fFloatValue <= -10.0f) {
                this.mTvCalorieDataChange.setPadding(0, ((int) (-fFloatValue)) - 10, 0, 0);
                this.mTvCalorieDataChange.setAlpha((fFloatValue + 20.0f) / 10.0f);
            } else if (fFloatValue >= 10.0f) {
                this.mTvCalorieDataChange.setPadding(0, 0, 0, ((int) fFloatValue) - 10);
                this.mTvCalorieDataChange.setAlpha(1.0f - (fFloatValue / 20.0f));
            } else {
                this.mTvCalorieDataChange.setPadding(0, 0, 0, 0);
                this.mTvCalorieDataChange.setAlpha(1.0f);
            }
        }
        TextView textView2 = this.mTvStrengthDataChange;
        if (textView2 != null && textView2.getVisibility() == 0) {
            float fFloatValue2 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            if (fFloatValue2 <= -10.0f) {
                this.mTvStrengthDataChange.setPadding(0, ((int) (-fFloatValue2)) - 10, 0, 0);
                this.mTvStrengthDataChange.setAlpha((fFloatValue2 + 20.0f) / 10.0f);
            } else if (fFloatValue2 >= 10.0f) {
                this.mTvStrengthDataChange.setPadding(0, 0, 0, ((int) fFloatValue2) - 10);
                this.mTvStrengthDataChange.setAlpha(1.0f - (fFloatValue2 / 20.0f));
            } else {
                this.mTvStrengthDataChange.setPadding(0, 0, 0, 0);
                this.mTvStrengthDataChange.setAlpha(1.0f);
            }
        }
        TextView textView3 = this.mTvWalkHourDataChange;
        if (textView3 == null || textView3.getVisibility() != 0) {
            return;
        }
        float fFloatValue3 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        if (fFloatValue3 <= -10.0f) {
            this.mTvWalkHourDataChange.setPadding(0, ((int) (-fFloatValue3)) - 10, 0, 0);
            this.mTvWalkHourDataChange.setAlpha((fFloatValue3 + 20.0f) / 10.0f);
        } else if (fFloatValue3 >= 10.0f) {
            this.mTvWalkHourDataChange.setPadding(0, 0, 0, ((int) fFloatValue3) - 10);
            this.mTvWalkHourDataChange.setAlpha(1.0f - (fFloatValue3 / 20.0f));
        } else {
            this.mTvWalkHourDataChange.setPadding(0, 0, 0, 0);
            this.mTvWalkHourDataChange.setAlpha(1.0f);
        }
    }

    private void startAnimator() {
        if (this.mAnimator == null) {
            this.mAnimator = getAnimator();
        }
        if (this.mAnimator.isStarted() || this.mAnimator.isRunning()) {
            this.mAnimator.cancel();
        }
        this.mAnimator.start();
    }
}