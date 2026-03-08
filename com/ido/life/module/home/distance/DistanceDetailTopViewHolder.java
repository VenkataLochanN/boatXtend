package com.ido.life.module.home.distance;

import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.database.model.UnitSetting;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;

/* JADX INFO: loaded from: classes2.dex */
public class DistanceDetailTopViewHolder extends BaseDetailTopViewHolder {

    @BindView(R.id.tv_avg_distance)
    public TextView mTvAvgDistance;

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

    @BindView(R.id.tv_total_distance)
    public TextView mTvTotalDistance;

    public DistanceDetailTopViewHolder(View view) {
        super(view);
        setDefaultValue();
        refreshLanguage();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void refreshLanguage() {
        this.mTvTitleTotal.setText(LanguageUtil.getLanguageText(R.string.total));
        UnitSetting unitSettingQueryUnitSetting = GreenDaoUtil.queryUnitSetting(RunTimeUtil.getInstance().getUserId());
        if ((unitSettingQueryUnitSetting != null ? unitSettingQueryUnitSetting.getUnit() : 1) == 1) {
            this.mTvLeftUnit.setText(LanguageUtil.getLanguageText(R.string.km_short));
            this.mTvRightUnit.setText(LanguageUtil.getLanguageText(R.string.km_short));
        } else {
            this.mTvLeftUnit.setText(LanguageUtil.getLanguageText(R.string.mile_short));
            this.mTvRightUnit.setText(LanguageUtil.getLanguageText(R.string.mile_short));
        }
        this.mTvTitleAvg.setText(LanguageUtil.getLanguageText(R.string.home_detail_ave_ios));
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void hideRightLayout() {
        this.mTvTitleAvg.setVisibility(4);
        this.mTvAvgDistance.setVisibility(4);
        this.mTvRightUnit.setVisibility(4);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void showRightLayout() {
        this.mTvTitleAvg.setVisibility(0);
        this.mTvAvgDistance.setVisibility(0);
        this.mTvRightUnit.setVisibility(0);
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
        this.mTvTitleTotal.setVisibility(0);
        this.mTvTotalDistance.setVisibility(0);
        this.mTvLeftUnit.setVisibility(0);
        this.mTvDate.setVisibility(0);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void showLoadingView() {
        hideRightLayout();
        this.mTvTitleTotal.setVisibility(4);
        this.mTvTotalDistance.setVisibility(4);
        this.mTvLeftUnit.setVisibility(4);
        this.mTvDate.setVisibility(4);
        this.mImgLoadFailed.setVisibility(8);
        this.mLayLoading.setVisibility(0);
        this.mImgLoading.setVisibility(0);
        this.mTvLoadingState.setVisibility(0);
        this.mTvLoadingState.setText(LanguageUtil.getLanguageText(R.string.chart_detail_data_loading));
        this.mLayLoading.setOnClickListener(null);
        startAnimator();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder
    public void showLoadFailedView(View.OnClickListener onClickListener) {
        stopAnimator();
        hideRightLayout();
        this.mLayLoading.setOnClickListener(onClickListener);
        this.mTvTitleTotal.setVisibility(4);
        this.mTvTotalDistance.setVisibility(4);
        this.mTvLeftUnit.setVisibility(4);
        this.mTvDate.setVisibility(4);
        this.mImgLoading.setVisibility(8);
        this.mLayLoading.setVisibility(0);
        this.mImgLoadFailed.setVisibility(0);
        this.mTvLoadingState.setVisibility(0);
        this.mTvLoadingState.setText(LanguageUtil.getLanguageText(R.string.chart_detail_data_load_failed));
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void setDefaultValue() {
        this.mTvTotalDistance.setText("0.00");
        this.mTvDate.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        this.mTvAvgDistance.setText("0.00");
    }
}