package com.ido.life.module.sport.history.swim;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.charter.SportSpeedChart;
import com.ido.life.customview.viewgroup.BottomSportView;
import com.ido.life.customview.viewgroup.UnitView;

/* JADX INFO: loaded from: classes2.dex */
public class SportSwimActivity_ViewBinding implements Unbinder {
    private SportSwimActivity target;
    private View view7f0a0333;
    private View view7f0a0338;
    private View view7f0a0776;
    private View view7f0a0778;
    private View view7f0a0821;
    private View view7f0a08c4;
    private View view7f0a0919;

    public SportSwimActivity_ViewBinding(SportSwimActivity sportSwimActivity) {
        this(sportSwimActivity, sportSwimActivity.getWindow().getDecorView());
    }

    public SportSwimActivity_ViewBinding(final SportSwimActivity sportSwimActivity, View view) {
        this.target = sportSwimActivity;
        sportSwimActivity.mSportNameTitleLayout = Utils.findRequiredView(view, R.id.sportNameTitleLayout, "field 'mSportNameTitleLayout'");
        sportSwimActivity.mDurationLayout = Utils.findRequiredView(view, R.id.durationLayout, "field 'mDurationLayout'");
        sportSwimActivity.mLlSportRoot = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_sport_root, "field 'mLlSportRoot'", LinearLayout.class);
        sportSwimActivity.mLlSportContent = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_sport_content, "field 'mLlSportContent'", LinearLayout.class);
        sportSwimActivity.mTitleText = (TextView) Utils.findRequiredViewAsType(view, R.id.title_text, "field 'mTitleText'", TextView.class);
        sportSwimActivity.ivSport = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_sport, "field 'ivSport'", ImageView.class);
        sportSwimActivity.tvSportName = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.tv_sport_indoor_name, "field 'tvSportName'", RegularTextView.class);
        sportSwimActivity.tvSportTimeStart = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.tv_start_time, "field 'tvSportTimeStart'", RegularTextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.iv_sport_about, "field 'ivSportAbout' and method 'onViewClicked'");
        sportSwimActivity.ivSportAbout = (ImageView) Utils.castView(viewFindRequiredView, R.id.iv_sport_about, "field 'ivSportAbout'", ImageView.class);
        this.view7f0a0338 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.history.swim.SportSwimActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportSwimActivity.onViewClicked(view2);
            }
        });
        sportSwimActivity.uvCategory = (UnitView) Utils.findRequiredViewAsType(view, R.id.uv_distance, "field 'uvCategory'", UnitView.class);
        sportSwimActivity.tvcalory = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_calorie, "field 'tvcalory'", TextView.class);
        sportSwimActivity.tvTotalTime = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_total_second, "field 'tvTotalTime'", TextView.class);
        sportSwimActivity.tvHr = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_rate, "field 'tvHr'", TextView.class);
        sportSwimActivity.rlSwimIndoorItem = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_swim_indoor_item, "field 'rlSwimIndoorItem'", LinearLayout.class);
        sportSwimActivity.rlPoolPerformance = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rl_pool_performance, "field 'rlPoolPerformance'", RelativeLayout.class);
        sportSwimActivity.rlChartPace = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rl_chart_pace, "field 'rlChartPace'", RelativeLayout.class);
        sportSwimActivity.rlChartFrequency = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rl_chart_frequency, "field 'rlChartFrequency'", RelativeLayout.class);
        sportSwimActivity.rlChartSwolf = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rl_chart_swolf, "field 'rlChartSwolf'", RelativeLayout.class);
        sportSwimActivity.bvSwimItemOne = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_swim_item_one, "field 'bvSwimItemOne'", BottomSportView.class);
        sportSwimActivity.bvSwimItemTwo = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_swim_item_two, "field 'bvSwimItemTwo'", BottomSportView.class);
        sportSwimActivity.bvSwimItemThree = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_swim_item_three, "field 'bvSwimItemThree'", BottomSportView.class);
        sportSwimActivity.bvSwimItemFour = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_swim_item_four, "field 'bvSwimItemFour'", BottomSportView.class);
        sportSwimActivity.bvSwimItemFive = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_swim_item_five, "field 'bvSwimItemFive'", BottomSportView.class);
        sportSwimActivity.bvSwimItemSix = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.bv_swim_item_six, "field 'bvSwimItemSix'", BottomSportView.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.tv_more, "field 'tvMore' and method 'onViewClicked'");
        sportSwimActivity.tvMore = (TextView) Utils.castView(viewFindRequiredView2, R.id.tv_more, "field 'tvMore'", TextView.class);
        this.view7f0a08c4 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.history.swim.SportSwimActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportSwimActivity.onViewClicked(view2);
            }
        });
        sportSwimActivity.tvPaceValue = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_pace_value, "field 'tvPaceValue'", TextView.class);
        sportSwimActivity.tvFrequencyValue = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_frequency_value, "field 'tvFrequencyValue'", TextView.class);
        sportSwimActivity.tvSwolfValue = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_swolf_value, "field 'tvSwolfValue'", TextView.class);
        sportSwimActivity.chartItemAvgPace = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.chart_item_avg_pace, "field 'chartItemAvgPace'", BottomSportView.class);
        sportSwimActivity.chartItemMaxPace = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.chart_item_max_pace, "field 'chartItemMaxPace'", BottomSportView.class);
        sportSwimActivity.chartItemAvgFrequency = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.chart_item_avg_frequency, "field 'chartItemAvgFrequency'", BottomSportView.class);
        sportSwimActivity.chartItemMaxFrequency = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.chart_item_max_frequency, "field 'chartItemMaxFrequency'", BottomSportView.class);
        sportSwimActivity.chartItemAvgSwolf = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.chart_item_avg_swolf, "field 'chartItemAvgSwolf'", BottomSportView.class);
        sportSwimActivity.chartItemMaxSwolf = (BottomSportView) Utils.findRequiredViewAsType(view, R.id.chart_item_max_swolf, "field 'chartItemMaxSwolf'", BottomSportView.class);
        sportSwimActivity.chartPace = (SportSpeedChart) Utils.findRequiredViewAsType(view, R.id.chart_pace, "field 'chartPace'", SportSpeedChart.class);
        sportSwimActivity.chartFrequency = (SportSpeedChart) Utils.findRequiredViewAsType(view, R.id.chart_frequency, "field 'chartFrequency'", SportSpeedChart.class);
        sportSwimActivity.chartSwolf = (SportSpeedChart) Utils.findRequiredViewAsType(view, R.id.chart_swolf, "field 'chartSwolf'", SportSpeedChart.class);
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.title_rightBtn, "field 'mTitleRightBtn' and method 'toDelete'");
        sportSwimActivity.mTitleRightBtn = (ImageButton) Utils.castView(viewFindRequiredView3, R.id.title_rightBtn, "field 'mTitleRightBtn'", ImageButton.class);
        this.view7f0a0778 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.history.swim.SportSwimActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportSwimActivity.toDelete(view2);
            }
        });
        sportSwimActivity.mLlShareTitle = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_share_title, "field 'mLlShareTitle'", LinearLayout.class);
        sportSwimActivity.mLlSportSuggest = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_sport_suggest, "field 'mLlSportSuggest'", LinearLayout.class);
        sportSwimActivity.mRvQrCode = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rv_qr_code, "field 'mRvQrCode'", RelativeLayout.class);
        sportSwimActivity.mFlTitleBar = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'mFlTitleBar'", FrameLayout.class);
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.iv_share, "field 'mIvShare' and method 'toShare'");
        sportSwimActivity.mIvShare = (ImageView) Utils.castView(viewFindRequiredView4, R.id.iv_share, "field 'mIvShare'", ImageView.class);
        this.view7f0a0333 = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.history.swim.SportSwimActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportSwimActivity.toShare(view2);
            }
        });
        sportSwimActivity.mLayoutNetworkError = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_network_error, "field 'mLayoutNetworkError'", LinearLayout.class);
        sportSwimActivity.mLayoutSportRetry = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_sport_retry, "field 'mLayoutSportRetry'", LinearLayout.class);
        View viewFindRequiredView5 = Utils.findRequiredView(view, R.id.tv_retry, "field 'mTvRetry' and method 'toRetry'");
        sportSwimActivity.mTvRetry = (TextView) Utils.castView(viewFindRequiredView5, R.id.tv_retry, "field 'mTvRetry'", TextView.class);
        this.view7f0a0919 = viewFindRequiredView5;
        viewFindRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.history.swim.SportSwimActivity_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportSwimActivity.toRetry(view2);
            }
        });
        View viewFindRequiredView6 = Utils.findRequiredView(view, R.id.title_leftBtn, "method 'onViewClicked'");
        this.view7f0a0776 = viewFindRequiredView6;
        viewFindRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.history.swim.SportSwimActivity_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportSwimActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView7 = Utils.findRequiredView(view, R.id.tv_data_feedback, "method 'onViewClicked'");
        this.view7f0a0821 = viewFindRequiredView7;
        viewFindRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.history.swim.SportSwimActivity_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportSwimActivity.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SportSwimActivity sportSwimActivity = this.target;
        if (sportSwimActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        sportSwimActivity.mSportNameTitleLayout = null;
        sportSwimActivity.mDurationLayout = null;
        sportSwimActivity.mLlSportRoot = null;
        sportSwimActivity.mLlSportContent = null;
        sportSwimActivity.mTitleText = null;
        sportSwimActivity.ivSport = null;
        sportSwimActivity.tvSportName = null;
        sportSwimActivity.tvSportTimeStart = null;
        sportSwimActivity.ivSportAbout = null;
        sportSwimActivity.uvCategory = null;
        sportSwimActivity.tvcalory = null;
        sportSwimActivity.tvTotalTime = null;
        sportSwimActivity.tvHr = null;
        sportSwimActivity.rlSwimIndoorItem = null;
        sportSwimActivity.rlPoolPerformance = null;
        sportSwimActivity.rlChartPace = null;
        sportSwimActivity.rlChartFrequency = null;
        sportSwimActivity.rlChartSwolf = null;
        sportSwimActivity.bvSwimItemOne = null;
        sportSwimActivity.bvSwimItemTwo = null;
        sportSwimActivity.bvSwimItemThree = null;
        sportSwimActivity.bvSwimItemFour = null;
        sportSwimActivity.bvSwimItemFive = null;
        sportSwimActivity.bvSwimItemSix = null;
        sportSwimActivity.tvMore = null;
        sportSwimActivity.tvPaceValue = null;
        sportSwimActivity.tvFrequencyValue = null;
        sportSwimActivity.tvSwolfValue = null;
        sportSwimActivity.chartItemAvgPace = null;
        sportSwimActivity.chartItemMaxPace = null;
        sportSwimActivity.chartItemAvgFrequency = null;
        sportSwimActivity.chartItemMaxFrequency = null;
        sportSwimActivity.chartItemAvgSwolf = null;
        sportSwimActivity.chartItemMaxSwolf = null;
        sportSwimActivity.chartPace = null;
        sportSwimActivity.chartFrequency = null;
        sportSwimActivity.chartSwolf = null;
        sportSwimActivity.mTitleRightBtn = null;
        sportSwimActivity.mLlShareTitle = null;
        sportSwimActivity.mLlSportSuggest = null;
        sportSwimActivity.mRvQrCode = null;
        sportSwimActivity.mFlTitleBar = null;
        sportSwimActivity.mIvShare = null;
        sportSwimActivity.mLayoutNetworkError = null;
        sportSwimActivity.mLayoutSportRetry = null;
        sportSwimActivity.mTvRetry = null;
        this.view7f0a0338.setOnClickListener(null);
        this.view7f0a0338 = null;
        this.view7f0a08c4.setOnClickListener(null);
        this.view7f0a08c4 = null;
        this.view7f0a0778.setOnClickListener(null);
        this.view7f0a0778 = null;
        this.view7f0a0333.setOnClickListener(null);
        this.view7f0a0333 = null;
        this.view7f0a0919.setOnClickListener(null);
        this.view7f0a0919 = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
        this.view7f0a0821.setOnClickListener(null);
        this.view7f0a0821 = null;
    }
}