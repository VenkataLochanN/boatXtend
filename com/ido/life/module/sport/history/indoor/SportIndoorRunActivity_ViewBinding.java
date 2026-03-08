package com.ido.life.module.sport.history.indoor;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.WeightSeekBar;
import com.ido.life.customview.charter.CubicChartBar;
import com.ido.life.customview.charter.CubicSportChartBar;
import com.ido.life.customview.viewgroup.BottomSportView;
import com.ido.life.customview.viewgroup.UnitView;
import com.ido.life.module.sport.view.SportPieChart;

/* JADX INFO: loaded from: classes2.dex */
public class SportIndoorRunActivity_ViewBinding implements Unbinder {
    private SportIndoorRunActivity target;
    private View view7f0a0333;
    private View view7f0a0776;
    private View view7f0a0778;
    private View view7f0a0821;
    private View view7f0a0919;

    public SportIndoorRunActivity_ViewBinding(SportIndoorRunActivity sportIndoorRunActivity) {
        this(sportIndoorRunActivity, sportIndoorRunActivity.getWindow().getDecorView());
    }

    public SportIndoorRunActivity_ViewBinding(final SportIndoorRunActivity sportIndoorRunActivity, View view) {
        this.target = sportIndoorRunActivity;
        sportIndoorRunActivity.mLlSportRoot = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_sport_root, "field 'mLlSportRoot'", LinearLayout.class);
        sportIndoorRunActivity.mLlContent = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_content, "field 'mLlContent'", LinearLayout.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.title_leftBtn, "field 'mTitleLeftBtn' and method 'toBack'");
        sportIndoorRunActivity.mTitleLeftBtn = (ImageButton) Utils.castView(viewFindRequiredView, R.id.title_leftBtn, "field 'mTitleLeftBtn'", ImageButton.class);
        this.view7f0a0776 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.history.indoor.SportIndoorRunActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportIndoorRunActivity.toBack(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.title_rightBtn, "field 'mTitleRightBtn' and method 'toDelete'");
        sportIndoorRunActivity.mTitleRightBtn = (ImageButton) Utils.castView(viewFindRequiredView2, R.id.title_rightBtn, "field 'mTitleRightBtn'", ImageButton.class);
        this.view7f0a0778 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.history.indoor.SportIndoorRunActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportIndoorRunActivity.toDelete(view2);
            }
        });
        sportIndoorRunActivity.mTvSportName = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.tv_sport_name, "field 'mTvSportName'", RegularTextView.class);
        sportIndoorRunActivity.mTvSportTimeStart = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.tv_sport_time_start, "field 'mTvSportTimeStart'", RegularTextView.class);
        sportIndoorRunActivity.mUvDistance = (UnitView) Utils.findRequiredViewAsType(view, R.id.uv_category, "field 'mUvDistance'", UnitView.class);
        sportIndoorRunActivity.mLlSportDetailOneItem = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_sport_detail_one_item, "field 'mLlSportDetailOneItem'", LinearLayout.class);
        sportIndoorRunActivity.mLlSportDetailTwoItem = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_sport_detail_two_item, "field 'mLlSportDetailTwoItem'", LinearLayout.class);
        sportIndoorRunActivity.mLlSportDetailThreeItem = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_sport_detail_three_item, "field 'mLlSportDetailThreeItem'", LinearLayout.class);
        sportIndoorRunActivity.mLlSportDetailFourItem = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_sport_detail_four_item, "field 'mLlSportDetailFourItem'", LinearLayout.class);
        sportIndoorRunActivity.mBvSportItemOne = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_sport_item_one, "field 'mBvSportItemOne'", BottomSportView.class);
        sportIndoorRunActivity.mBvSportItemTwo = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_sport_item_two, "field 'mBvSportItemTwo'", BottomSportView.class);
        sportIndoorRunActivity.mBvSportItemThree = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_sport_item_three, "field 'mBvSportItemThree'", BottomSportView.class);
        sportIndoorRunActivity.mBvSportItemFour = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_sport_item_four, "field 'mBvSportItemFour'", BottomSportView.class);
        sportIndoorRunActivity.mBvSportItemFive = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_sport_item_five, "field 'mBvSportItemFive'", BottomSportView.class);
        sportIndoorRunActivity.mBvSportItemSix = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_sport_item_six, "field 'mBvSportItemSix'", BottomSportView.class);
        sportIndoorRunActivity.mBvSportItemSeven = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_sport_item_seven, "field 'mBvSportItemSeven'", BottomSportView.class);
        sportIndoorRunActivity.mBvSportItemEight = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_sport_item_eight, "field 'mBvSportItemEight'", BottomSportView.class);
        sportIndoorRunActivity.mTvHeartRate = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.tv_heart_rate, "field 'mTvHeartRate'", RegularTextView.class);
        sportIndoorRunActivity.mTvHeartRateUnit = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.tv_heart_rate_unit, "field 'mTvHeartRateUnit'", RegularTextView.class);
        sportIndoorRunActivity.mBvChartSportRateAverage = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_chart_sport_rate_average, "field 'mBvChartSportRateAverage'", BottomSportView.class);
        sportIndoorRunActivity.mBvChartSportRateMax = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_chart_sport_rate_max, "field 'mBvChartSportRateMax'", BottomSportView.class);
        sportIndoorRunActivity.mCubicChart = (CubicSportChartBar) Utils.findRequiredViewAsType(view, R.id.cubic_chart, "field 'mCubicChart'", CubicSportChartBar.class);
        sportIndoorRunActivity.mPcSport = (SportPieChart) Utils.findRequiredViewAsType(view, R.id.pc_sport, "field 'mPcSport'", SportPieChart.class);
        sportIndoorRunActivity.mTvMaxExerciseTime = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_max_exercise_time, "field 'mTvMaxExerciseTime'", TextView.class);
        sportIndoorRunActivity.mTvNoEndurance = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_no_endurance, "field 'mTvNoEndurance'", TextView.class);
        sportIndoorRunActivity.mTvAerobicEndurance = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_aerobic_endurance, "field 'mTvAerobicEndurance'", TextView.class);
        sportIndoorRunActivity.mTvBurningHeartRate = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_burning_heart_rate, "field 'mTvBurningHeartRate'", TextView.class);
        sportIndoorRunActivity.mTvWarmUpHeart = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_warm_up_heart, "field 'mTvWarmUpHeart'", TextView.class);
        sportIndoorRunActivity.mTvSportPace = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sport_item_pace, "field 'mTvSportPace'", TextView.class);
        sportIndoorRunActivity.mTvSportSpeedTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sport_pace_title, "field 'mTvSportSpeedTitle'", TextView.class);
        sportIndoorRunActivity.mTvSportPaceUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sport_pace_unit, "field 'mTvSportPaceUnit'", TextView.class);
        sportIndoorRunActivity.mBvSportItemPaceAvg = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_sport_item_pace_avg, "field 'mBvSportItemPaceAvg'", BottomSportView.class);
        sportIndoorRunActivity.mBvSportItemPaceFaster = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_sport_item_pace_faster, "field 'mBvSportItemPaceFaster'", BottomSportView.class);
        sportIndoorRunActivity.mLlSportDistance = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_sport_distance, "field 'mLlSportDistance'", LinearLayout.class);
        sportIndoorRunActivity.mTvDataDoubt = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.tv_data_doubt, "field 'mTvDataDoubt'", RegularTextView.class);
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.tv_data_feedback, "field 'mTvDataFeedback' and method 'toFeedback'");
        sportIndoorRunActivity.mTvDataFeedback = (RegularTextView) Utils.castView(viewFindRequiredView3, R.id.tv_data_feedback, "field 'mTvDataFeedback'", RegularTextView.class);
        this.view7f0a0821 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.history.indoor.SportIndoorRunActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportIndoorRunActivity.toFeedback(view2);
            }
        });
        sportIndoorRunActivity.mLlSportDetailRate = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_sport_detail_rate_item, "field 'mLlSportDetailRate'", LinearLayout.class);
        sportIndoorRunActivity.mSeekBarStepNumber = (WeightSeekBar) Utils.findRequiredViewAsType(view, R.id.seekBar_step_number, "field 'mSeekBarStepNumber'", WeightSeekBar.class);
        sportIndoorRunActivity.mTvTargetDiff = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.tv_target_diff, "field 'mTvTargetDiff'", RegularTextView.class);
        sportIndoorRunActivity.mLlSportPaceItem = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_sport_detail_peace_item, "field 'mLlSportPaceItem'", LinearLayout.class);
        sportIndoorRunActivity.mLlSportFrequencyItem = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_sport_detail_frequency_item, "field 'mLlSportFrequencyItem'", LinearLayout.class);
        sportIndoorRunActivity.mTvSportFrequencyTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sport_frequency_title, "field 'mTvSportFrequencyTitle'", TextView.class);
        sportIndoorRunActivity.mTvSportFrequencyUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sport_frequency_unit, "field 'mTvSportFrequencyUnit'", TextView.class);
        sportIndoorRunActivity.mBvSportFrequencyAvg = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_sport_frequency_avg, "field 'mBvSportFrequencyAvg'", BottomSportView.class);
        sportIndoorRunActivity.mBvSportFrequencyMax = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_sport_frequency_max, "field 'mBvSportFrequencyMax'", BottomSportView.class);
        sportIndoorRunActivity.mCubicChartStep = (CubicChartBar) Utils.findRequiredViewAsType(view, R.id.cubic_chart_step, "field 'mCubicChartStep'", CubicChartBar.class);
        sportIndoorRunActivity.mTvSportItemDistance = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sport_item_distance, "field 'mTvSportItemDistance'", TextView.class);
        sportIndoorRunActivity.mLlSportSpeedItem = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_sport_detail_speed_item, "field 'mLlSportSpeedItem'", LinearLayout.class);
        sportIndoorRunActivity.mTvSportAvgSpeed = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sport_avg_speed, "field 'mTvSportAvgSpeed'", TextView.class);
        sportIndoorRunActivity.mTvSportSpeedUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sport_speed_unit, "field 'mTvSportSpeedUnit'", TextView.class);
        sportIndoorRunActivity.mLlSportKmSpeed = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_sport_km_speed, "field 'mLlSportKmSpeed'", LinearLayout.class);
        sportIndoorRunActivity.mTvSportSpeedKm = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sport_item_speed_km, "field 'mTvSportSpeedKm'", TextView.class);
        sportIndoorRunActivity.mLlShareTitle = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_share_title, "field 'mLlShareTitle'", LinearLayout.class);
        sportIndoorRunActivity.mLlSportSuggest = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_sport_suggest, "field 'mLlSportSuggest'", LinearLayout.class);
        sportIndoorRunActivity.mfLTitleBar = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'mfLTitleBar'", FrameLayout.class);
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.iv_share, "field 'mIvShare' and method 'toShare'");
        sportIndoorRunActivity.mIvShare = (ImageView) Utils.castView(viewFindRequiredView4, R.id.iv_share, "field 'mIvShare'", ImageView.class);
        this.view7f0a0333 = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.history.indoor.SportIndoorRunActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportIndoorRunActivity.toShare(view2);
            }
        });
        sportIndoorRunActivity.mSvContent = (ScrollView) Utils.findRequiredViewAsType(view, R.id.sv_content, "field 'mSvContent'", ScrollView.class);
        sportIndoorRunActivity.mSportContent = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_sport_content, "field 'mSportContent'", LinearLayout.class);
        sportIndoorRunActivity.mLayoutNetworkError = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_network_error, "field 'mLayoutNetworkError'", LinearLayout.class);
        sportIndoorRunActivity.mLayoutSportRetry = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_sport_retry, "field 'mLayoutSportRetry'", LinearLayout.class);
        View viewFindRequiredView5 = Utils.findRequiredView(view, R.id.tv_retry, "field 'mTvRetry' and method 'toRetry'");
        sportIndoorRunActivity.mTvRetry = (TextView) Utils.castView(viewFindRequiredView5, R.id.tv_retry, "field 'mTvRetry'", TextView.class);
        this.view7f0a0919 = viewFindRequiredView5;
        viewFindRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.history.indoor.SportIndoorRunActivity_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportIndoorRunActivity.toRetry(view2);
            }
        });
        sportIndoorRunActivity.mTitleText = (TextView) Utils.findRequiredViewAsType(view, R.id.title_text, "field 'mTitleText'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SportIndoorRunActivity sportIndoorRunActivity = this.target;
        if (sportIndoorRunActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        sportIndoorRunActivity.mLlSportRoot = null;
        sportIndoorRunActivity.mLlContent = null;
        sportIndoorRunActivity.mTitleLeftBtn = null;
        sportIndoorRunActivity.mTitleRightBtn = null;
        sportIndoorRunActivity.mTvSportName = null;
        sportIndoorRunActivity.mTvSportTimeStart = null;
        sportIndoorRunActivity.mUvDistance = null;
        sportIndoorRunActivity.mLlSportDetailOneItem = null;
        sportIndoorRunActivity.mLlSportDetailTwoItem = null;
        sportIndoorRunActivity.mLlSportDetailThreeItem = null;
        sportIndoorRunActivity.mLlSportDetailFourItem = null;
        sportIndoorRunActivity.mBvSportItemOne = null;
        sportIndoorRunActivity.mBvSportItemTwo = null;
        sportIndoorRunActivity.mBvSportItemThree = null;
        sportIndoorRunActivity.mBvSportItemFour = null;
        sportIndoorRunActivity.mBvSportItemFive = null;
        sportIndoorRunActivity.mBvSportItemSix = null;
        sportIndoorRunActivity.mBvSportItemSeven = null;
        sportIndoorRunActivity.mBvSportItemEight = null;
        sportIndoorRunActivity.mTvHeartRate = null;
        sportIndoorRunActivity.mTvHeartRateUnit = null;
        sportIndoorRunActivity.mBvChartSportRateAverage = null;
        sportIndoorRunActivity.mBvChartSportRateMax = null;
        sportIndoorRunActivity.mCubicChart = null;
        sportIndoorRunActivity.mPcSport = null;
        sportIndoorRunActivity.mTvMaxExerciseTime = null;
        sportIndoorRunActivity.mTvNoEndurance = null;
        sportIndoorRunActivity.mTvAerobicEndurance = null;
        sportIndoorRunActivity.mTvBurningHeartRate = null;
        sportIndoorRunActivity.mTvWarmUpHeart = null;
        sportIndoorRunActivity.mTvSportPace = null;
        sportIndoorRunActivity.mTvSportSpeedTitle = null;
        sportIndoorRunActivity.mTvSportPaceUnit = null;
        sportIndoorRunActivity.mBvSportItemPaceAvg = null;
        sportIndoorRunActivity.mBvSportItemPaceFaster = null;
        sportIndoorRunActivity.mLlSportDistance = null;
        sportIndoorRunActivity.mTvDataDoubt = null;
        sportIndoorRunActivity.mTvDataFeedback = null;
        sportIndoorRunActivity.mLlSportDetailRate = null;
        sportIndoorRunActivity.mSeekBarStepNumber = null;
        sportIndoorRunActivity.mTvTargetDiff = null;
        sportIndoorRunActivity.mLlSportPaceItem = null;
        sportIndoorRunActivity.mLlSportFrequencyItem = null;
        sportIndoorRunActivity.mTvSportFrequencyTitle = null;
        sportIndoorRunActivity.mTvSportFrequencyUnit = null;
        sportIndoorRunActivity.mBvSportFrequencyAvg = null;
        sportIndoorRunActivity.mBvSportFrequencyMax = null;
        sportIndoorRunActivity.mCubicChartStep = null;
        sportIndoorRunActivity.mTvSportItemDistance = null;
        sportIndoorRunActivity.mLlSportSpeedItem = null;
        sportIndoorRunActivity.mTvSportAvgSpeed = null;
        sportIndoorRunActivity.mTvSportSpeedUnit = null;
        sportIndoorRunActivity.mLlSportKmSpeed = null;
        sportIndoorRunActivity.mTvSportSpeedKm = null;
        sportIndoorRunActivity.mLlShareTitle = null;
        sportIndoorRunActivity.mLlSportSuggest = null;
        sportIndoorRunActivity.mfLTitleBar = null;
        sportIndoorRunActivity.mIvShare = null;
        sportIndoorRunActivity.mSvContent = null;
        sportIndoorRunActivity.mSportContent = null;
        sportIndoorRunActivity.mLayoutNetworkError = null;
        sportIndoorRunActivity.mLayoutSportRetry = null;
        sportIndoorRunActivity.mTvRetry = null;
        sportIndoorRunActivity.mTitleText = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
        this.view7f0a0778.setOnClickListener(null);
        this.view7f0a0778 = null;
        this.view7f0a0821.setOnClickListener(null);
        this.view7f0a0821 = null;
        this.view7f0a0333.setOnClickListener(null);
        this.view7f0a0333 = null;
        this.view7f0a0919.setOnClickListener(null);
        this.view7f0a0919 = null;
    }
}