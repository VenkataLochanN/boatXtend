package com.ido.life.module.home.pressure.detail.vertical;

import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder;

/* JADX INFO: loaded from: classes2.dex */
public class PressureDetailTopViewHolder extends BaseDetailTopViewHolder {

    @BindView(R.id.tv_avg)
    public TextView mTvAvg;

    @BindView(R.id.tv_date)
    public TextView mTvDate;

    @BindView(R.id.tv_max_min)
    public TextView mTvMaxmin;

    @BindView(R.id.tv_state)
    public TextView mTvState;

    @BindView(R.id.tv_title_avg)
    public TextView mTvTitleAvg;

    @BindView(R.id.tv_title_maxmin)
    public TextView mTvTitleMaxmin;

    public PressureDetailTopViewHolder(View view) {
        super(view);
        refreshLanguage();
        setDefaultValue();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void refreshLanguage() {
        this.mTvTitleAvg.setText(LanguageUtil.getLanguageText(R.string.home_detail_ave_ios));
        this.mTvTitleMaxmin.setText(LanguageUtil.getLanguageText(R.string.title_max_min));
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void setDefaultValue() {
        this.mTvAvg.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        this.mTvDate.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        this.mTvMaxmin.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        this.mTvState.setText("");
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void showRightLayout() {
        this.mTvTitleMaxmin.setVisibility(0);
        this.mTvMaxmin.setVisibility(0);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void hideRightLayout() {
        this.mTvMaxmin.setVisibility(4);
        this.mTvTitleMaxmin.setVisibility(4);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void showSuccessView(boolean z) {
        stopAnimator();
        this.mLayLoading.setVisibility(8);
        this.mLayLoading.setOnClickListener(null);
        if (z) {
            showRightLayout();
        } else {
            hideRightLayout();
        }
        this.mTvTitleAvg.setVisibility(0);
        this.mTvAvg.setVisibility(0);
        this.mTvState.setVisibility(0);
        this.mTvDate.setVisibility(0);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void showLoadingView() {
        this.mLayLoading.setVisibility(0);
        this.mImgLoading.setVisibility(0);
        this.mTvLoadingState.setVisibility(0);
        this.mTvLoadingState.setText(LanguageUtil.getLanguageText(R.string.chart_detail_data_loading));
        this.mImgLoadFailed.setVisibility(8);
        this.mTvTitleAvg.setVisibility(4);
        this.mTvAvg.setVisibility(4);
        this.mTvState.setVisibility(4);
        this.mTvDate.setVisibility(4);
        this.mLayLoading.setOnClickListener(null);
        hideRightLayout();
        startAnimator();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void showLoadFailedView(View.OnClickListener onClickListener) {
        stopAnimator();
        this.mLayLoading.setVisibility(0);
        this.mLayLoading.setOnClickListener(onClickListener);
        this.mImgLoading.setVisibility(8);
        this.mTvLoadingState.setVisibility(0);
        this.mTvLoadingState.setText(LanguageUtil.getLanguageText(R.string.chart_detail_data_load_failed));
        this.mImgLoadFailed.setVisibility(0);
        this.mTvTitleAvg.setVisibility(4);
        this.mTvAvg.setVisibility(4);
        this.mTvState.setVisibility(4);
        this.mTvDate.setVisibility(4);
        hideRightLayout();
    }
}