package com.ido.life.viewholder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.LinearInterpolator;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.bean.MainData;
import com.ido.life.customview.NormalToast;
import com.ido.life.customview.cardview.CustomCardView;
import com.ido.life.module.home.IHomeView;
import com.ido.life.util.GreenDaoUtil;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseHomeItemViewHolder extends BaseHomeViewHolder {

    @BindView(R.id.card_out)
    protected CustomCardView mCardView;
    protected boolean mHasData;
    private int mHeaderCount;
    private ValueAnimator mLoadingAnimator;

    protected abstract String getDownloadDataName();

    protected abstract void setClickListener(View.OnClickListener onClickListener);

    public BaseHomeItemViewHolder(View view, IHomeView iHomeView) {
        super(view, iHomeView);
        this.mHasData = false;
    }

    @Override // com.ido.life.viewholder.BaseHomeViewHolder
    public void bindData(MainData mainData) {
        super.bindData(mainData);
        checkAnimator();
    }

    protected boolean showLeftSpace() {
        if (this.mHomeView != null) {
            this.mHeaderCount = this.mHomeView.getHeaderCount();
        }
        return (getAdapterPosition() - this.mHeaderCount) % 2 > 0;
    }

    protected void startLoadingAnimator() {
        ValueAnimator valueAnimator = this.mLoadingAnimator;
        if (valueAnimator == null || !(valueAnimator.isRunning() || this.mLoadingAnimator.isStarted())) {
            if (this.mLoadingAnimator == null) {
                this.mLoadingAnimator = getLoadingAnimator();
            }
            this.mLoadingAnimator.start();
        }
    }

    protected void stopLoadingAnimator() {
        ValueAnimator valueAnimator = this.mLoadingAnimator;
        if (valueAnimator != null) {
            if (valueAnimator.isStarted() || this.mLoadingAnimator.isRunning()) {
                this.mLoadingAnimator.cancel();
            }
        }
    }

    protected ValueAnimator getLoadingAnimator() {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(1500L);
        valueAnimator.setRepeatMode(1);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setTarget(this.mCardView);
        valueAnimator.setValues(PropertyValuesHolder.ofFloat("sizes", -0.02f, 0.02f), PropertyValuesHolder.ofFloat("alphas", -0.5f, 0.5f));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.life.viewholder.-$$Lambda$BaseHomeItemViewHolder$i2GAGq_BhUsX55c6meqIVIjwbGY
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                this.f$0.lambda$getLoadingAnimator$0$BaseHomeItemViewHolder(valueAnimator2);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.ido.life.viewholder.BaseHomeItemViewHolder.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                super.onAnimationCancel(animator);
                if (BaseHomeItemViewHolder.this.itemView != null) {
                    BaseHomeItemViewHolder.this.itemView.setScaleY(1.0f);
                    BaseHomeItemViewHolder.this.itemView.setScaleX(1.0f);
                }
                if (BaseHomeItemViewHolder.this.mCardView != null) {
                    BaseHomeItemViewHolder.this.mCardView.setAlpha(1.0f);
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                if (BaseHomeItemViewHolder.this.itemView != null) {
                    BaseHomeItemViewHolder.this.itemView.setScaleY(1.0f);
                    BaseHomeItemViewHolder.this.itemView.setScaleX(1.0f);
                }
                if (BaseHomeItemViewHolder.this.mCardView != null) {
                    BaseHomeItemViewHolder.this.mCardView.setAlpha(1.0f);
                }
            }
        });
        return valueAnimator;
    }

    public /* synthetic */ void lambda$getLoadingAnimator$0$BaseHomeItemViewHolder(ValueAnimator valueAnimator) {
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
                this.mCardView.setAlpha(0.5f - fFloatValue2);
            } else {
                this.mCardView.setAlpha(fFloatValue2 + 0.5f);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void checkAnimator() {
        if (this.mHomeView != null && this.mHomeView.hasLogin()) {
            if (hasData()) {
                stopLoadingAnimator();
                setClickListener(this.mHomeView);
                return;
            }
            int iQueryLatestDataLoadState = GreenDaoUtil.queryLatestDataLoadState(getUserId(), getDownloadDataName());
            if (iQueryLatestDataLoadState == 3 || iQueryLatestDataLoadState == 4) {
                stopLoadingAnimator();
                setClickListener(this.mHomeView);
                return;
            } else {
                setClickListener(getDefaultClickListener());
                startLoadingAnimator();
                return;
            }
        }
        setClickListener(this.mHomeView);
        stopLoadingAnimator();
    }

    public boolean hasData() {
        return this.mHasData;
    }

    protected View.OnClickListener getDefaultClickListener() {
        return new View.OnClickListener() { // from class: com.ido.life.viewholder.-$$Lambda$BaseHomeItemViewHolder$FIIHOOanvGdeT1-UY7fTKbUe_OY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NormalToast.showToast(LanguageUtil.getLanguageText(R.string.history_syncing_pls_wait));
            }
        };
    }

    protected void printAndSaveLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLogPath(), getClass().getSimpleName(), str);
    }
}