package com.ido.life.module.home.calorie;

import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder;

/* JADX INFO: loaded from: classes2.dex */
public class CalorieDetailTopViewHolder extends BaseDetailTopViewHolder {

    @BindView(R.id.tv_avg_calorie)
    public TextView mTvAvgCalorie;

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

    @BindView(R.id.tv_total_calorie)
    public TextView mTvTotalCalorie;

    public CalorieDetailTopViewHolder(View view) {
        super(view);
        setDefaultValue();
        refreshLanguage();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void refreshLanguage() {
        this.mTvTitleTotal.setText(LanguageUtil.getLanguageText(R.string.total));
        this.mTvLeftUnit.setText(LanguageUtil.getLanguageText(R.string.home_calorie_unit));
        this.mTvRightUnit.setText(LanguageUtil.getLanguageText(R.string.home_calorie_unit));
        this.mTvTitleAvg.setText(LanguageUtil.getLanguageText(R.string.home_detail_ave_ios));
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void hideRightLayout() {
        this.mTvTitleAvg.setVisibility(4);
        this.mTvAvgCalorie.setVisibility(4);
        this.mTvRightUnit.setVisibility(4);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void showRightLayout() {
        this.mTvTitleAvg.setVisibility(0);
        this.mTvAvgCalorie.setVisibility(0);
        this.mTvRightUnit.setVisibility(0);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void showSuccessView(boolean z) {
        stopAnimator();
        this.mLayLoading.setVisibility(8);
        this.mLayLoading.setOnClickListener(null);
        this.mTvTitleTotal.setVisibility(0);
        this.mTvTotalCalorie.setVisibility(0);
        this.mTvLeftUnit.setVisibility(0);
        this.mTvDate.setVisibility(0);
        if (z) {
            showRightLayout();
        } else {
            hideRightLayout();
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void showLoadingView() {
        this.mLayLoading.setVisibility(0);
        this.mLayLoading.setOnClickListener(null);
        this.mImgLoading.setVisibility(0);
        this.mTvLoadingState.setVisibility(0);
        this.mImgLoadFailed.setVisibility(8);
        this.mTvLoadingState.setText(LanguageUtil.getLanguageText(R.string.chart_detail_data_loading));
        this.mTvTitleTotal.setVisibility(4);
        this.mTvTotalCalorie.setVisibility(4);
        this.mTvLeftUnit.setVisibility(4);
        this.mTvDate.setVisibility(4);
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
        this.mImgLoadFailed.setVisibility(0);
        this.mTvLoadingState.setText(LanguageUtil.getLanguageText(R.string.chart_detail_data_load_failed));
        this.mTvTitleTotal.setVisibility(4);
        this.mTvTotalCalorie.setVisibility(4);
        this.mTvLeftUnit.setVisibility(4);
        this.mTvDate.setVisibility(4);
        hideRightLayout();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void setDefaultValue() {
        this.mTvTotalCalorie.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        this.mTvDate.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        this.mTvAvgCalorie.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
    }
}