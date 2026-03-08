package com.ido.life.module.home.rate.vertical;

import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder;

/* JADX INFO: loaded from: classes2.dex */
public class RateDetailTopViewHolder extends BaseDetailTopViewHolder {

    @BindView(R.id.tv_avg)
    public TextView mTvAvg;

    @BindView(R.id.tv_date)
    public TextView mTvDate;

    @BindView(R.id.tv_title_avg)
    public TextView mTvTitleAvg;

    @BindView(R.id.tv_unit)
    public TextView mTvUnit;

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void hideRightLayout() {
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void showRightLayout() {
    }

    public RateDetailTopViewHolder(View view) {
        super(view);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void refreshLanguage() {
        this.mTvTitleAvg.setText(LanguageUtil.getLanguageText(R.string.home_detail_ave_ios));
        this.mTvUnit.setText(LanguageUtil.getLanguageText(R.string.device_heart_rate_unit));
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void setDefaultValue() {
        this.mTvAvg.setText("--");
        this.mTvDate.setText("--");
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void showLoadingView() {
        this.mTvTitleAvg.setVisibility(4);
        this.mTvAvg.setVisibility(4);
        this.mTvUnit.setVisibility(4);
        this.mTvDate.setVisibility(4);
        this.mLayLoading.setVisibility(0);
        this.mImgLoading.setVisibility(0);
        this.mTvLoadingState.setVisibility(0);
        this.mImgLoadFailed.setVisibility(8);
        this.mTvLoadingState.setText(LanguageUtil.getLanguageText(R.string.chart_detail_data_loading));
        this.mLayLoading.setOnClickListener(null);
        startAnimator();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void showSuccessView(boolean z) {
        stopAnimator();
        this.mTvTitleAvg.setVisibility(0);
        this.mTvAvg.setVisibility(0);
        this.mTvUnit.setVisibility(0);
        this.mTvDate.setVisibility(0);
        this.mImgLoading.setVisibility(8);
        this.mLayLoading.setVisibility(8);
        this.mTvLoadingState.setVisibility(8);
        this.mImgLoadFailed.setVisibility(8);
        this.mLayLoading.setOnClickListener(null);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void showLoadFailedView(View.OnClickListener onClickListener) {
        stopAnimator();
        this.mTvTitleAvg.setVisibility(4);
        this.mTvAvg.setVisibility(4);
        this.mTvUnit.setVisibility(4);
        this.mTvDate.setVisibility(4);
        this.mImgLoading.setVisibility(8);
        this.mLayLoading.setVisibility(0);
        this.mTvLoadingState.setVisibility(0);
        this.mImgLoadFailed.setVisibility(0);
        this.mLayLoading.setOnClickListener(onClickListener);
        this.mTvLoadingState.setText(LanguageUtil.getLanguageText(R.string.chart_detail_data_load_failed));
    }
}