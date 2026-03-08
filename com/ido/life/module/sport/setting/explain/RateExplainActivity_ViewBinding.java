package com.ido.life.module.sport.setting.explain;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.viewgroup.BottomRateView;
import com.ido.life.customview.viewgroup.LeftMiddleRightView;
import com.ido.life.customview.viewgroup.OptionView;

/* JADX INFO: loaded from: classes2.dex */
public class RateExplainActivity_ViewBinding implements Unbinder {
    private RateExplainActivity target;
    private View view7f0a0776;

    public RateExplainActivity_ViewBinding(RateExplainActivity rateExplainActivity) {
        this(rateExplainActivity, rateExplainActivity.getWindow().getDecorView());
    }

    public RateExplainActivity_ViewBinding(final RateExplainActivity rateExplainActivity, View view) {
        this.target = rateExplainActivity;
        rateExplainActivity.mTitleText = (TextView) Utils.findRequiredViewAsType(view, R.id.title_text, "field 'mTitleText'", TextView.class);
        rateExplainActivity.mOptSportHeartRate = (OptionView) Utils.findRequiredViewAsType(view, R.id.opt_sport_heart_rate, "field 'mOptSportHeartRate'", OptionView.class);
        rateExplainActivity.mTvRateRange = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_rate_range, "field 'mTvRateRange'", TextView.class);
        rateExplainActivity.mViewLmrTitle = (LeftMiddleRightView) Utils.findRequiredViewAsType(view, R.id.view_lmr_title, "field 'mViewLmrTitle'", LeftMiddleRightView.class);
        rateExplainActivity.mViewLmtWarmUp = (LeftMiddleRightView) Utils.findRequiredViewAsType(view, R.id.view_lmt_warm_up, "field 'mViewLmtWarmUp'", LeftMiddleRightView.class);
        rateExplainActivity.mViewLmrBurningGrease = (LeftMiddleRightView) Utils.findRequiredViewAsType(view, R.id.view_lmr_burning_grease, "field 'mViewLmrBurningGrease'", LeftMiddleRightView.class);
        rateExplainActivity.mViewLmrAerobicEndurance = (LeftMiddleRightView) Utils.findRequiredViewAsType(view, R.id.view_lmr_aerobic_endurance, "field 'mViewLmrAerobicEndurance'", LeftMiddleRightView.class);
        rateExplainActivity.mViewLmrAnaerobicEndurance = (LeftMiddleRightView) Utils.findRequiredViewAsType(view, R.id.view_lmr_anaerobic_endurance, "field 'mViewLmrAnaerobicEndurance'", LeftMiddleRightView.class);
        rateExplainActivity.mViewLmrLimit = (LeftMiddleRightView) Utils.findRequiredViewAsType(view, R.id.view_lmr_limit, "field 'mViewLmrLimit'", LeftMiddleRightView.class);
        rateExplainActivity.mTvRateRangeExplain = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_rate_range_explain, "field 'mTvRateRangeExplain'", TextView.class);
        rateExplainActivity.mViewBottomWarmUp = (BottomRateView) Utils.findRequiredViewAsType(view, R.id.view_bottom_warm_up, "field 'mViewBottomWarmUp'", BottomRateView.class);
        rateExplainActivity.mViewBottomBurningGrease = (BottomRateView) Utils.findRequiredViewAsType(view, R.id.view_bottom_burning_grease, "field 'mViewBottomBurningGrease'", BottomRateView.class);
        rateExplainActivity.mViewBottomAerobicEndurance = (BottomRateView) Utils.findRequiredViewAsType(view, R.id.view_bottom_aerobic_endurance, "field 'mViewBottomAerobicEndurance'", BottomRateView.class);
        rateExplainActivity.mViewBottomAnaerobicEndurance = (BottomRateView) Utils.findRequiredViewAsType(view, R.id.view_bottom_anaerobic_endurance, "field 'mViewBottomAnaerobicEndurance'", BottomRateView.class);
        rateExplainActivity.mViewBottomLimit = (BottomRateView) Utils.findRequiredViewAsType(view, R.id.view_bottom_limit, "field 'mViewBottomLimit'", BottomRateView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.title_leftBtn, "method 'back'");
        this.view7f0a0776 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.setting.explain.RateExplainActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                rateExplainActivity.back(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        RateExplainActivity rateExplainActivity = this.target;
        if (rateExplainActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        rateExplainActivity.mTitleText = null;
        rateExplainActivity.mOptSportHeartRate = null;
        rateExplainActivity.mTvRateRange = null;
        rateExplainActivity.mViewLmrTitle = null;
        rateExplainActivity.mViewLmtWarmUp = null;
        rateExplainActivity.mViewLmrBurningGrease = null;
        rateExplainActivity.mViewLmrAerobicEndurance = null;
        rateExplainActivity.mViewLmrAnaerobicEndurance = null;
        rateExplainActivity.mViewLmrLimit = null;
        rateExplainActivity.mTvRateRangeExplain = null;
        rateExplainActivity.mViewBottomWarmUp = null;
        rateExplainActivity.mViewBottomBurningGrease = null;
        rateExplainActivity.mViewBottomAerobicEndurance = null;
        rateExplainActivity.mViewBottomAnaerobicEndurance = null;
        rateExplainActivity.mViewBottomLimit = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
    }
}