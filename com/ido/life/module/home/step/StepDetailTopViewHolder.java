package com.ido.life.module.home.step;

import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder;

/* JADX INFO: loaded from: classes2.dex */
public class StepDetailTopViewHolder extends BaseDetailTopViewHolder {

    @BindView(R.id.tv_avg_step)
    public TextView mTvAvgStep;

    @BindView(R.id.tv_date)
    public TextView mTvDate;

    @BindView(R.id.tv_left_unit)
    public TextView mTvLeftUnit;

    @BindView(R.id.tv_right_unit)
    public TextView mTvRightUnit;

    @BindView(R.id.tv_title_avg)
    public TextView mTvTitleAvg;

    @BindView(R.id.tv_title_total)
    public TextView mTvTitleTotal;

    @BindView(R.id.tv_total_step)
    public TextView mTvTotalStep;

    public StepDetailTopViewHolder(View view) {
        super(view);
        setDefaultValue();
        refreshLanguage();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void refreshLanguage() {
        this.mTvTitleTotal.setText(LanguageUtil.getLanguageText(R.string.total));
        this.mTvLeftUnit.setText(LanguageUtil.getLanguageText(R.string.public_sport_step));
        this.mTvRightUnit.setText(LanguageUtil.getLanguageText(R.string.public_sport_step));
        this.mTvTitleAvg.setText(LanguageUtil.getLanguageText(R.string.home_detail_ave_ios));
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void hideRightLayout() {
        this.mTvTitleAvg.setVisibility(4);
        this.mTvAvgStep.setVisibility(4);
        this.mTvRightUnit.setVisibility(4);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void showRightLayout() {
        this.mTvTitleAvg.setVisibility(0);
        this.mTvAvgStep.setVisibility(0);
        this.mTvRightUnit.setVisibility(0);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void showSuccessView(boolean z) {
        stopAnimator();
        this.mLayLoading.setVisibility(8);
        this.mLayLoading.setOnClickListener(null);
        this.mTvTitleTotal.setVisibility(0);
        this.mTvTotalStep.setVisibility(0);
        this.mTvLeftUnit.setVisibility(0);
        this.mTvDate.setVisibility(0);
        if (z) {
            showRightLayout();
        } else {
            hideRightLayout();
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void showLoadFailedView(View.OnClickListener onClickListener) {
        stopAnimator();
        this.mLayLoading.setVisibility(0);
        this.mImgLoading.setVisibility(8);
        this.mTvLoadingState.setVisibility(0);
        this.mTvLoadingState.setText(LanguageUtil.getLanguageText(R.string.chart_detail_data_load_failed));
        this.mImgLoadFailed.setVisibility(0);
        this.mTvTitleTotal.setVisibility(4);
        this.mTvTotalStep.setVisibility(4);
        this.mTvLeftUnit.setVisibility(4);
        this.mTvDate.setVisibility(4);
        this.mLayLoading.setOnClickListener(onClickListener);
        hideRightLayout();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void showLoadingView() {
        this.mLayLoading.setVisibility(0);
        this.mImgLoading.setVisibility(0);
        this.mTvLoadingState.setVisibility(0);
        this.mTvLoadingState.setText(LanguageUtil.getLanguageText(R.string.chart_detail_data_loading));
        this.mImgLoadFailed.setVisibility(8);
        this.mTvTitleTotal.setVisibility(4);
        this.mTvTotalStep.setVisibility(4);
        this.mTvLeftUnit.setVisibility(4);
        this.mTvDate.setVisibility(4);
        this.mLayLoading.setOnClickListener(null);
        hideRightLayout();
        startAnimator();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void setDefaultValue() {
        this.mTvTotalStep.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        this.mTvDate.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        this.mTvAvgStep.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
    }
}