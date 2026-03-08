package com.ido.life.module.sport.history;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.SportDetailScrollView;
import com.ido.life.customview.TrackPointView;
import com.ido.life.customview.WeightSeekBar;
import com.ido.life.customview.charter.CubicSportChartBar;
import com.ido.life.customview.charter.CubicSportStepChartBar;
import com.ido.life.customview.viewgroup.BottomSportView;
import com.ido.life.module.sport.view.SportPieChart;

/* JADX INFO: loaded from: classes2.dex */
public class SportHistoryDetailActivity_ViewBinding implements Unbinder {
    private SportHistoryDetailActivity target;
    private View view7f0a01da;
    private View view7f0a01dc;
    private View view7f0a031c;
    private View view7f0a0333;
    private View view7f0a0776;
    private View view7f0a0821;
    private View view7f0a0919;

    public SportHistoryDetailActivity_ViewBinding(SportHistoryDetailActivity sportHistoryDetailActivity) {
        this(sportHistoryDetailActivity, sportHistoryDetailActivity.getWindow().getDecorView());
    }

    public SportHistoryDetailActivity_ViewBinding(final SportHistoryDetailActivity sportHistoryDetailActivity, View view) {
        this.target = sportHistoryDetailActivity;
        sportHistoryDetailActivity.trackPointView = (TrackPointView) Utils.findRequiredViewAsType(view, R.id.track_point_view, "field 'trackPointView'", TrackPointView.class);
        sportHistoryDetailActivity.mScrollView = (SportDetailScrollView) Utils.findRequiredViewAsType(view, R.id.scrollView, "field 'mScrollView'", SportDetailScrollView.class);
        sportHistoryDetailActivity.mRvMapRoot = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rv_map_root, "field 'mRvMapRoot'", RelativeLayout.class);
        sportHistoryDetailActivity.mRlMapRoot = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.rl_map_root, "field 'mRlMapRoot'", FrameLayout.class);
        sportHistoryDetailActivity.mLlBottom = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_bottom, "field 'mLlBottom'", LinearLayout.class);
        sportHistoryDetailActivity.mLlSportDetail = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_sport_detail, "field 'mLlSportDetail'", LinearLayout.class);
        sportHistoryDetailActivity.mTvSportName = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.tv_sport_name, "field 'mTvSportName'", RegularTextView.class);
        sportHistoryDetailActivity.mTvSportTimeStart = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.tv_time_start, "field 'mTvSportTimeStart'", RegularTextView.class);
        sportHistoryDetailActivity.mTvDistance = (MediumTextView) Utils.findRequiredViewAsType(view, R.id.tv_distance, "field 'mTvDistance'", MediumTextView.class);
        sportHistoryDetailActivity.mTvDistanceUnit = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.tv_distance_unit, "field 'mTvDistanceUnit'", RegularTextView.class);
        sportHistoryDetailActivity.mSeekBarStepNumber = (WeightSeekBar) Utils.findRequiredViewAsType(view, R.id.seekBar_step_number, "field 'mSeekBarStepNumber'", WeightSeekBar.class);
        sportHistoryDetailActivity.mTvTargetDiff = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.tv_target_diff, "field 'mTvTargetDiff'", RegularTextView.class);
        sportHistoryDetailActivity.mLlSportDetailOneItem = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_sport_detail_one_item, "field 'mLlSportDetailOneItem'", LinearLayout.class);
        sportHistoryDetailActivity.mLlSportDetailTwoItem = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_sport_detail_two_item, "field 'mLlSportDetailTwoItem'", LinearLayout.class);
        sportHistoryDetailActivity.mLlSportDetailThreeItem = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_sport_detail_three_item, "field 'mLlSportDetailThreeItem'", LinearLayout.class);
        sportHistoryDetailActivity.mLlSportDetailFourItem = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_sport_detail_four_item, "field 'mLlSportDetailFourItem'", LinearLayout.class);
        sportHistoryDetailActivity.mBvSportItemOne = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_sport_item_one, "field 'mBvSportItemOne'", BottomSportView.class);
        sportHistoryDetailActivity.mBvSportItemTwo = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_sport_item_two, "field 'mBvSportItemTwo'", BottomSportView.class);
        sportHistoryDetailActivity.mBvSportItemThree = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_sport_item_three, "field 'mBvSportItemThree'", BottomSportView.class);
        sportHistoryDetailActivity.mBvSportItemFour = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_sport_item_four, "field 'mBvSportItemFour'", BottomSportView.class);
        sportHistoryDetailActivity.mBvSportItemFive = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_sport_item_five, "field 'mBvSportItemFive'", BottomSportView.class);
        sportHistoryDetailActivity.mBvSportItemSix = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_sport_item_six, "field 'mBvSportItemSix'", BottomSportView.class);
        sportHistoryDetailActivity.mBvSportItemSeven = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_sport_item_seven, "field 'mBvSportItemSeven'", BottomSportView.class);
        sportHistoryDetailActivity.mBvSportItemEight = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_sport_item_eight, "field 'mBvSportItemEight'", BottomSportView.class);
        sportHistoryDetailActivity.mTvHeartRate = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.tv_heart_rate, "field 'mTvHeartRate'", RegularTextView.class);
        sportHistoryDetailActivity.mTvHeartRateUnit = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.tv_heart_rate_unit, "field 'mTvHeartRateUnit'", RegularTextView.class);
        sportHistoryDetailActivity.mBvChartSportRateAverage = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_chart_sport_rate_average, "field 'mBvChartSportRateAverage'", BottomSportView.class);
        sportHistoryDetailActivity.mBvChartSportRateMax = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_chart_sport_rate_max, "field 'mBvChartSportRateMax'", BottomSportView.class);
        sportHistoryDetailActivity.mCubicChart = (CubicSportChartBar) Utils.findRequiredViewAsType(view, R.id.cubic_chart, "field 'mCubicChart'", CubicSportChartBar.class);
        sportHistoryDetailActivity.mPcSport = (SportPieChart) Utils.findRequiredViewAsType(view, R.id.pc_sport, "field 'mPcSport'", SportPieChart.class);
        sportHistoryDetailActivity.mLlSportDistance = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_sport_distance, "field 'mLlSportDistance'", LinearLayout.class);
        sportHistoryDetailActivity.mLlSportKmSpeed = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_sport_km_speed, "field 'mLlSportKmSpeed'", LinearLayout.class);
        sportHistoryDetailActivity.mTvMaxExerciseTime = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_max_exercise_time, "field 'mTvMaxExerciseTime'", TextView.class);
        sportHistoryDetailActivity.mTvNoEndurance = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_no_endurance, "field 'mTvNoEndurance'", TextView.class);
        sportHistoryDetailActivity.mTvAerobicEndurance = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_aerobic_endurance, "field 'mTvAerobicEndurance'", TextView.class);
        sportHistoryDetailActivity.mTvBurningHeartRate = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_burning_heart_rate, "field 'mTvBurningHeartRate'", TextView.class);
        sportHistoryDetailActivity.mTvWarmUpHeart = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_warm_up_heart, "field 'mTvWarmUpHeart'", TextView.class);
        sportHistoryDetailActivity.mTvSportItemDistance = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sport_item_distance, "field 'mTvSportItemDistance'", TextView.class);
        sportHistoryDetailActivity.mTvSportSpaceTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sport_pace_title, "field 'mTvSportSpaceTitle'", TextView.class);
        sportHistoryDetailActivity.mTvSportPaceUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sport_pace_unit, "field 'mTvSportPaceUnit'", TextView.class);
        sportHistoryDetailActivity.mTvSportSpeedKm = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sport_item_speed_km, "field 'mTvSportSpeedKm'", TextView.class);
        sportHistoryDetailActivity.mBvSportItemPaceAvg = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_sport_item_pace_avg, "field 'mBvSportItemPaceAvg'", BottomSportView.class);
        sportHistoryDetailActivity.mBvSportItemPaceFaster = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_sport_item_pace_faster, "field 'mBvSportItemPaceFaster'", BottomSportView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.ib_delete, "field 'mIbDelete' and method 'toDelete'");
        sportHistoryDetailActivity.mIbDelete = (ImageView) Utils.castView(viewFindRequiredView, R.id.ib_delete, "field 'mIbDelete'", ImageView.class);
        this.view7f0a01dc = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.history.SportHistoryDetailActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportHistoryDetailActivity.toDelete(view2);
            }
        });
        sportHistoryDetailActivity.mLlSportDetailRate = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_sport_detail_rate_item, "field 'mLlSportDetailRate'", LinearLayout.class);
        sportHistoryDetailActivity.mLlSportFrequencyItem = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_sport_detail_frequency_item, "field 'mLlSportFrequencyItem'", LinearLayout.class);
        sportHistoryDetailActivity.mTvSportFrequencyTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sport_frequency_title, "field 'mTvSportFrequencyTitle'", TextView.class);
        sportHistoryDetailActivity.mTvSportFrequencyUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sport_frequency_unit, "field 'mTvSportFrequencyUnit'", TextView.class);
        sportHistoryDetailActivity.mBvSportFrequencyAvg = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_sport_frequency_avg, "field 'mBvSportFrequencyAvg'", BottomSportView.class);
        sportHistoryDetailActivity.mBvSportFrequencyMax = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_sport_frequency_max, "field 'mBvSportFrequencyMax'", BottomSportView.class);
        sportHistoryDetailActivity.mCubicChartStep = (CubicSportStepChartBar) Utils.findRequiredViewAsType(view, R.id.cubic_chart_step, "field 'mCubicChartStep'", CubicSportStepChartBar.class);
        sportHistoryDetailActivity.mLlSportSpace = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_sport_detail_peace_item, "field 'mLlSportSpace'", LinearLayout.class);
        sportHistoryDetailActivity.mLlSportSpeedItem = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_sport_detail_speed_item, "field 'mLlSportSpeedItem'", LinearLayout.class);
        sportHistoryDetailActivity.mTvSportAvgSpeed = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sport_avg_speed, "field 'mTvSportAvgSpeed'", TextView.class);
        sportHistoryDetailActivity.mTvSportSpeedUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sport_speed_unit, "field 'mTvSportSpeedUnit'", TextView.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.iv_share, "field 'mIvShare' and method 'toShare'");
        sportHistoryDetailActivity.mIvShare = (ImageView) Utils.castView(viewFindRequiredView2, R.id.iv_share, "field 'mIvShare'", ImageView.class);
        this.view7f0a0333 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.history.SportHistoryDetailActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportHistoryDetailActivity.toShare(view2);
            }
        });
        sportHistoryDetailActivity.mLlShareTitle = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_share_title, "field 'mLlShareTitle'", LinearLayout.class);
        sportHistoryDetailActivity.mLlSportSuggest = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_sport_suggest, "field 'mLlSportSuggest'", LinearLayout.class);
        sportHistoryDetailActivity.mLayoutNetworkError = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_network_error, "field 'mLayoutNetworkError'", LinearLayout.class);
        sportHistoryDetailActivity.mLayoutSportRetry = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_sport_retry, "field 'mLayoutSportRetry'", LinearLayout.class);
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.tv_retry, "field 'mTvRetry' and method 'reTry'");
        sportHistoryDetailActivity.mTvRetry = (TextView) Utils.castView(viewFindRequiredView3, R.id.tv_retry, "field 'mTvRetry'", TextView.class);
        this.view7f0a0919 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.history.SportHistoryDetailActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportHistoryDetailActivity.reTry(view2);
            }
        });
        sportHistoryDetailActivity.mRvSportTitle = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rv_sport_title, "field 'mRvSportTitle'", RelativeLayout.class);
        sportHistoryDetailActivity.mTitleBar = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'mTitleBar'", FrameLayout.class);
        sportHistoryDetailActivity.mTitleText = (TextView) Utils.findRequiredViewAsType(view, R.id.title_text, "field 'mTitleText'", TextView.class);
        sportHistoryDetailActivity.mRvMapLoading = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rv_map_loading, "field 'mRvMapLoading'", RelativeLayout.class);
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.ib_back, "method 'toBack'");
        this.view7f0a01da = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.history.SportHistoryDetailActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportHistoryDetailActivity.toBack(view2);
            }
        });
        View viewFindRequiredView5 = Utils.findRequiredView(view, R.id.title_leftBtn, "method 'toLoadingBack'");
        this.view7f0a0776 = viewFindRequiredView5;
        viewFindRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.history.SportHistoryDetailActivity_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportHistoryDetailActivity.toLoadingBack(view2);
            }
        });
        View viewFindRequiredView6 = Utils.findRequiredView(view, R.id.iv_locus_explain, "method 'toExplainLocus'");
        this.view7f0a031c = viewFindRequiredView6;
        viewFindRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.history.SportHistoryDetailActivity_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportHistoryDetailActivity.toExplainLocus(view2);
            }
        });
        View viewFindRequiredView7 = Utils.findRequiredView(view, R.id.tv_data_feedback, "method 'toDataFeedback'");
        this.view7f0a0821 = viewFindRequiredView7;
        viewFindRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.history.SportHistoryDetailActivity_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportHistoryDetailActivity.toDataFeedback(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SportHistoryDetailActivity sportHistoryDetailActivity = this.target;
        if (sportHistoryDetailActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        sportHistoryDetailActivity.trackPointView = null;
        sportHistoryDetailActivity.mScrollView = null;
        sportHistoryDetailActivity.mRvMapRoot = null;
        sportHistoryDetailActivity.mRlMapRoot = null;
        sportHistoryDetailActivity.mLlBottom = null;
        sportHistoryDetailActivity.mLlSportDetail = null;
        sportHistoryDetailActivity.mTvSportName = null;
        sportHistoryDetailActivity.mTvSportTimeStart = null;
        sportHistoryDetailActivity.mTvDistance = null;
        sportHistoryDetailActivity.mTvDistanceUnit = null;
        sportHistoryDetailActivity.mSeekBarStepNumber = null;
        sportHistoryDetailActivity.mTvTargetDiff = null;
        sportHistoryDetailActivity.mLlSportDetailOneItem = null;
        sportHistoryDetailActivity.mLlSportDetailTwoItem = null;
        sportHistoryDetailActivity.mLlSportDetailThreeItem = null;
        sportHistoryDetailActivity.mLlSportDetailFourItem = null;
        sportHistoryDetailActivity.mBvSportItemOne = null;
        sportHistoryDetailActivity.mBvSportItemTwo = null;
        sportHistoryDetailActivity.mBvSportItemThree = null;
        sportHistoryDetailActivity.mBvSportItemFour = null;
        sportHistoryDetailActivity.mBvSportItemFive = null;
        sportHistoryDetailActivity.mBvSportItemSix = null;
        sportHistoryDetailActivity.mBvSportItemSeven = null;
        sportHistoryDetailActivity.mBvSportItemEight = null;
        sportHistoryDetailActivity.mTvHeartRate = null;
        sportHistoryDetailActivity.mTvHeartRateUnit = null;
        sportHistoryDetailActivity.mBvChartSportRateAverage = null;
        sportHistoryDetailActivity.mBvChartSportRateMax = null;
        sportHistoryDetailActivity.mCubicChart = null;
        sportHistoryDetailActivity.mPcSport = null;
        sportHistoryDetailActivity.mLlSportDistance = null;
        sportHistoryDetailActivity.mLlSportKmSpeed = null;
        sportHistoryDetailActivity.mTvMaxExerciseTime = null;
        sportHistoryDetailActivity.mTvNoEndurance = null;
        sportHistoryDetailActivity.mTvAerobicEndurance = null;
        sportHistoryDetailActivity.mTvBurningHeartRate = null;
        sportHistoryDetailActivity.mTvWarmUpHeart = null;
        sportHistoryDetailActivity.mTvSportItemDistance = null;
        sportHistoryDetailActivity.mTvSportSpaceTitle = null;
        sportHistoryDetailActivity.mTvSportPaceUnit = null;
        sportHistoryDetailActivity.mTvSportSpeedKm = null;
        sportHistoryDetailActivity.mBvSportItemPaceAvg = null;
        sportHistoryDetailActivity.mBvSportItemPaceFaster = null;
        sportHistoryDetailActivity.mIbDelete = null;
        sportHistoryDetailActivity.mLlSportDetailRate = null;
        sportHistoryDetailActivity.mLlSportFrequencyItem = null;
        sportHistoryDetailActivity.mTvSportFrequencyTitle = null;
        sportHistoryDetailActivity.mTvSportFrequencyUnit = null;
        sportHistoryDetailActivity.mBvSportFrequencyAvg = null;
        sportHistoryDetailActivity.mBvSportFrequencyMax = null;
        sportHistoryDetailActivity.mCubicChartStep = null;
        sportHistoryDetailActivity.mLlSportSpace = null;
        sportHistoryDetailActivity.mLlSportSpeedItem = null;
        sportHistoryDetailActivity.mTvSportAvgSpeed = null;
        sportHistoryDetailActivity.mTvSportSpeedUnit = null;
        sportHistoryDetailActivity.mIvShare = null;
        sportHistoryDetailActivity.mLlShareTitle = null;
        sportHistoryDetailActivity.mLlSportSuggest = null;
        sportHistoryDetailActivity.mLayoutNetworkError = null;
        sportHistoryDetailActivity.mLayoutSportRetry = null;
        sportHistoryDetailActivity.mTvRetry = null;
        sportHistoryDetailActivity.mRvSportTitle = null;
        sportHistoryDetailActivity.mTitleBar = null;
        sportHistoryDetailActivity.mTitleText = null;
        sportHistoryDetailActivity.mRvMapLoading = null;
        this.view7f0a01dc.setOnClickListener(null);
        this.view7f0a01dc = null;
        this.view7f0a0333.setOnClickListener(null);
        this.view7f0a0333 = null;
        this.view7f0a0919.setOnClickListener(null);
        this.view7f0a0919 = null;
        this.view7f0a01da.setOnClickListener(null);
        this.view7f0a01da = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
        this.view7f0a031c.setOnClickListener(null);
        this.view7f0a031c = null;
        this.view7f0a0821.setOnClickListener(null);
        this.view7f0a0821 = null;
    }
}