package com.ido.life.module.home.chartdetail.vertical;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.amap.api.maps.utils.SpatialRelationUtil;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseDetailTopViewHolder extends BaseDetailViewHolder {

    @BindView(R.id.img_data_load_failed)
    public ImageView mImgLoadFailed;

    @BindView(R.id.img_data_loading)
    public ImageView mImgLoading;

    @BindView(R.id.lay_loading)
    public LinearLayout mLayLoading;
    private ValueAnimator mLoadingAnimator;

    @BindView(R.id.tv_data_loading_state)
    public TextView mTvLoadingState;

    public abstract void hideRightLayout();

    public abstract void showLoadFailedView(View.OnClickListener onClickListener);

    public abstract void showLoadingView();

    public abstract void showRightLayout();

    public abstract void showSuccessView(boolean z);

    public BaseDetailTopViewHolder(View view) {
        super(view);
    }

    protected ValueAnimator getLoadingAnimator() {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setTarget(this.mImgLoading);
        valueAnimator.setDuration(500L);
        valueAnimator.setIntValues(0, SpatialRelationUtil.A_CIRCLE_DEGREE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(-1);
        valueAnimator.setRepeatMode(1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.life.module.home.chartdetail.vertical.-$$Lambda$BaseDetailTopViewHolder$KOLKS66cKxsRpajgOimsUIVuiP4
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                this.f$0.lambda$getLoadingAnimator$0$BaseDetailTopViewHolder(valueAnimator2);
            }
        });
        return valueAnimator;
    }

    public /* synthetic */ void lambda$getLoadingAnimator$0$BaseDetailTopViewHolder(ValueAnimator valueAnimator) {
        ImageView imageView = this.mImgLoading;
        if (imageView == null || imageView.getVisibility() != 0) {
            return;
        }
        this.mImgLoading.setRotation(((Integer) valueAnimator.getAnimatedValue()).intValue());
    }

    protected void startAnimator() {
        if (this.mLoadingAnimator == null) {
            this.mLoadingAnimator = getLoadingAnimator();
        }
        if (this.mLoadingAnimator.isStarted() || this.mLoadingAnimator.isRunning()) {
            return;
        }
        this.mLoadingAnimator.start();
    }

    protected void stopAnimator() {
        ValueAnimator valueAnimator = this.mLoadingAnimator;
        if (valueAnimator == null) {
            return;
        }
        if (valueAnimator.isStarted() || this.mLoadingAnimator.isRunning()) {
            this.mLoadingAnimator.cancel();
        }
    }
}