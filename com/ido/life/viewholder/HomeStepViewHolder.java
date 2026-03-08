package com.ido.life.viewholder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.util.Pair;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.customview.charter.HomeStepCharterBar;
import com.ido.life.customview.maincard.MainPannelCircleView;
import com.ido.life.database.model.StepDayData;
import com.ido.life.database.model.UnitSetting;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.module.home.IHomeView;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.UnitUtil;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class HomeStepViewHolder extends BaseHomeItemViewHolder {
    private ValueAnimator mAnimator;

    @BindView(R.id.chat_step)
    public HomeStepCharterBar mChatStep;

    @BindView(R.id.lay_distance)
    public LinearLayout mLayDistance;

    @BindView(R.id.lay_step)
    public LinearLayout mLayStep;

    @BindView(R.id.lay_step_top)
    public LinearLayout mLayStepTop;

    @BindView(R.id.step_progress_circle)
    MainPannelCircleView mStepCircleView;

    @BindView(R.id.tv_data_change)
    TextView mTvDataChange;

    @BindView(R.id.tv_distance)
    public TextView mTvDistance;

    @BindView(R.id.tv_title_distance)
    public TextView mTvDistanceTitle;

    @BindView(R.id.tv_distance_unit)
    public TextView mTvDistanceUnit;

    @BindView(R.id.tv_step)
    public TextView mTvStep;

    @BindView(R.id.tv_step_unit)
    public TextView mTvStepUnit;

    public HomeStepViewHolder(View view, IHomeView iHomeView) {
        super(view, iHomeView);
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder
    protected String getDownloadDataName() {
        return StepDayData.class.getSimpleName();
    }

    @Override // com.ido.life.viewholder.BaseHomeViewHolder
    public void refreshPage() {
        int iIntValue;
        this.mTvStepUnit.setText(LanguageUtil.getLanguageText(R.string.public_sport_step));
        this.mTvDistanceTitle.setText(LanguageUtil.getLanguageText(R.string.sport_distance));
        UnitSetting unitSettingQueryUnitSetting = GreenDaoUtil.queryUnitSetting(RunTimeUtil.getInstance().getUserId());
        int unit = unitSettingQueryUnitSetting != null ? unitSettingQueryUnitSetting.getUnit() : 1;
        if (unit == 1) {
            this.mTvDistanceUnit.setText(LanguageUtil.getLanguageText(R.string.km_short));
        } else {
            this.mTvDistanceUnit.setText(LanguageUtil.getLanguageText(R.string.mile_short));
        }
        this.mChatStep.setXLabelArray(new String[]{"00:00", "12:00", "24:00"});
        Pair<Pair<Integer, Boolean>, List<Point>> todayStepData = this.mHomeView.getTodayStepData();
        this.mStepCircleView.setCenterImageRes(R.mipmap.home_steps);
        this.mHasData = false;
        if (todayStepData != null && todayStepData.first != null && todayStepData.second != null && ((List) todayStepData.second).size() > 0) {
            this.mHasData = true;
            if (!this.mShowAnimator) {
                List<Point> values = this.mChatStep.getValues();
                this.mShowAnimator = values == null || values.size() == 0;
            }
            float totalDistance = this.mHomeView.getTotalDistance();
            if (totalDistance <= 0.0f) {
                this.mTvDistanceUnit.setText("");
                this.mTvDistance.setText("--");
            } else if (unit == 1) {
                this.mTvDistance.setText(String.format("%.2f", Float.valueOf(totalDistance)));
            } else {
                this.mTvDistance.setText(String.format("%.2f", Float.valueOf(UnitUtil.km2mile(totalDistance))));
            }
            List<Point> list = (List) todayStepData.second;
            int iMin = 0;
            int iMax = 0;
            for (Point point : list) {
                iMax = Math.max(iMax, point.y);
                iMin = Math.min(iMin, point.y);
            }
            this.mChatStep.setYLabelMinValue(iMin);
            this.mChatStep.setYLabelMaxValue(iMax);
            this.mChatStep.setValues(list);
            UserTargetNew userTargetNewQueryUserLatestTarget = GreenDaoUtil.queryUserLatestTarget(RunTimeUtil.getInstance().getUserId());
            int step = userTargetNewQueryUserLatestTarget != null ? userTargetNewQueryUserLatestTarget.getStep() : 10000;
            if (step <= 0) {
                step = 10000;
            }
            this.mStepCircleView.setMaxProgress(step);
            this.mStepCircleView.setProgress(((Integer) ((Pair) todayStepData.first).first).intValue());
            if (!this.mFirstRefresh && ((Boolean) ((Pair) todayStepData.first).second).booleanValue()) {
                try {
                    iIntValue = ((Integer) ((Pair) todayStepData.first).first).intValue() - ((Integer) this.mTvStep.getTag()).intValue();
                } catch (Exception unused) {
                    iIntValue = ((Integer) ((Pair) todayStepData.first).first).intValue();
                }
                if (iIntValue <= 0) {
                    this.mTvDataChange.setVisibility(4);
                } else {
                    this.mTvDataChange.setPadding(0, 0, 0, 0);
                    this.mTvDataChange.setText("+" + iIntValue);
                    this.mTvDataChange.setVisibility(0);
                    startAnimator();
                }
            } else {
                this.mTvDataChange.setVisibility(4);
            }
            this.mTvStep.setText(String.valueOf(((Pair) todayStepData.first).first));
            this.mTvStep.setTag(((Pair) todayStepData.first).first);
            this.mChatStep.refreshChart(this.mShowAnimator);
        } else {
            this.mStepCircleView.setMaxProgress(100);
            this.mStepCircleView.setProgress(0);
            this.mTvStep.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            this.mTvDataChange.setVisibility(4);
            this.mTvDistance.setText("--");
            this.mTvDistanceUnit.setText("");
            this.mChatStep.setValues(null);
            this.mChatStep.refreshChart(false);
        }
        this.mFirstRefresh = false;
    }

    private ValueAnimator getAnimator() {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(2000L);
        valueAnimator.setTarget(this.mTvDataChange);
        valueAnimator.setFloatValues(-20.0f, 20.0f);
        valueAnimator.setRepeatMode(1);
        valueAnimator.setStartDelay(500L);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.life.viewholder.-$$Lambda$HomeStepViewHolder$36U_OSxPWG4E-oEk7dfYzwdlev4
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                this.f$0.lambda$getAnimator$0$HomeStepViewHolder(valueAnimator2);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.ido.life.viewholder.HomeStepViewHolder.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                HomeStepViewHolder.this.mTvDataChange.setVisibility(0);
                HomeStepViewHolder.this.mTvDataChange.setPadding(0, 0, 0, 0);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                if (HomeStepViewHolder.this.mTvDataChange != null) {
                    HomeStepViewHolder.this.mTvDataChange.setVisibility(4);
                    HomeStepViewHolder.this.mTvDataChange.setPadding(0, 0, 0, 0);
                }
            }
        });
        return valueAnimator;
    }

    public /* synthetic */ void lambda$getAnimator$0$HomeStepViewHolder(ValueAnimator valueAnimator) {
        if (this.mTvDataChange != null) {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            if (fFloatValue <= -10.0f) {
                this.mTvDataChange.setPadding(0, ((int) (-fFloatValue)) - 10, 0, 0);
                this.mTvDataChange.setAlpha((fFloatValue + 20.0f) / 10.0f);
            } else if (fFloatValue >= 10.0f) {
                this.mTvDataChange.setPadding(0, 0, 0, ((int) fFloatValue) - 10);
                this.mTvDataChange.setAlpha(1.0f - (fFloatValue / 20.0f));
            } else {
                this.mTvDataChange.setPadding(0, 0, 0, 0);
                this.mTvDataChange.setAlpha(1.0f);
            }
        }
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder
    protected ValueAnimator getLoadingAnimator() {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(1500L);
        valueAnimator.setRepeatMode(1);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setTarget(this.mCardView);
        valueAnimator.setValues(PropertyValuesHolder.ofFloat("sizes", -0.02f, 0.02f), PropertyValuesHolder.ofFloat("alphas", -0.15f, 0.15f));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.life.viewholder.-$$Lambda$HomeStepViewHolder$xyppbIBhZw8V6-I4DaEIBJjDtHM
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                this.f$0.lambda$getLoadingAnimator$1$HomeStepViewHolder(valueAnimator2);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.ido.life.viewholder.HomeStepViewHolder.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                super.onAnimationCancel(animator);
                if (HomeStepViewHolder.this.itemView != null) {
                    HomeStepViewHolder.this.itemView.setScaleY(1.0f);
                    HomeStepViewHolder.this.itemView.setScaleX(1.0f);
                }
                if (HomeStepViewHolder.this.mCardView != null) {
                    HomeStepViewHolder.this.mCardView.setAlpha(1.0f);
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                if (HomeStepViewHolder.this.itemView != null) {
                    HomeStepViewHolder.this.itemView.setScaleY(1.0f);
                    HomeStepViewHolder.this.itemView.setScaleX(1.0f);
                }
                if (HomeStepViewHolder.this.mCardView != null) {
                    HomeStepViewHolder.this.mCardView.setAlpha(1.0f);
                }
            }
        });
        return valueAnimator;
    }

    public /* synthetic */ void lambda$getLoadingAnimator$1$HomeStepViewHolder(ValueAnimator valueAnimator) {
        if (this.itemView == null || this.mCardView == null) {
            stopLoadingAnimator();
            return;
        }
        try {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue("sizes")).floatValue();
            if (fFloatValue <= 0.0f) {
                float f2 = 0.98f - fFloatValue;
                this.itemView.setScaleX(f2);
                this.itemView.setScaleY(f2);
            } else {
                float f3 = fFloatValue + 0.98f;
                this.itemView.setScaleX(f3);
                this.itemView.setScaleY(f3);
            }
            float fFloatValue2 = ((Float) valueAnimator.getAnimatedValue("alphas")).floatValue();
            if (fFloatValue2 <= 0.0f) {
                this.mCardView.setAlpha(0.85f - fFloatValue2);
            } else {
                this.mCardView.setAlpha(fFloatValue2 + 0.85f);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder
    protected void setClickListener(View.OnClickListener onClickListener) {
        this.mStepCircleView.setOnClickListener(onClickListener);
        this.mLayStepTop.setOnClickListener(onClickListener);
        this.mChatStep.setOnClickListener(onClickListener);
        this.mLayDistance.setOnClickListener(onClickListener);
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