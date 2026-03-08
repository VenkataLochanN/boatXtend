package com.ido.life.module.sport.setting.rate;

import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.life.customview.CustomToggleButton;
import com.ido.life.customview.viewgroup.OptionView;

/* JADX INFO: loaded from: classes2.dex */
public class SportRateActivity_ViewBinding implements Unbinder {
    private SportRateActivity target;
    private View view7f0a056e;
    private View view7f0a0776;
    private View view7f0a08fc;
    private View view7f0a08fd;

    public SportRateActivity_ViewBinding(SportRateActivity sportRateActivity) {
        this(sportRateActivity, sportRateActivity.getWindow().getDecorView());
    }

    public SportRateActivity_ViewBinding(final SportRateActivity sportRateActivity, View view) {
        this.target = sportRateActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.title_leftBtn, "field 'mTitleLeftBtn' and method 'toBack'");
        sportRateActivity.mTitleLeftBtn = (ImageButton) Utils.castView(viewFindRequiredView, R.id.title_leftBtn, "field 'mTitleLeftBtn'", ImageButton.class);
        this.view7f0a0776 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.setting.rate.SportRateActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportRateActivity.toBack(view2);
            }
        });
        sportRateActivity.mTitleText = (TextView) Utils.findRequiredViewAsType(view, R.id.title_text, "field 'mTitleText'", TextView.class);
        sportRateActivity.mToggle = (CustomToggleButton) Utils.findRequiredViewAsType(view, R.id.toggle, "field 'mToggle'", CustomToggleButton.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.opt_sport_heart_rate, "field 'mOptSportHeartRate' and method 'toSportHeart'");
        sportRateActivity.mOptSportHeartRate = (OptionView) Utils.castView(viewFindRequiredView2, R.id.opt_sport_heart_rate, "field 'mOptSportHeartRate'", OptionView.class);
        this.view7f0a056e = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.setting.rate.SportRateActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportRateActivity.toSportHeart(view2);
            }
        });
        sportRateActivity.mTvRateUpperLimitWarning = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_rate_upper_limit_warning, "field 'mTvRateUpperLimitWarning'", TextView.class);
        sportRateActivity.mTvRateUpperLimitWarningNow = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_rate_upper_limit_warning_now, "field 'mTvRateUpperLimitWarningNow'", TextView.class);
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.tv_rate_max, "field 'mTvRateMax' and method 'toSetRateMax'");
        sportRateActivity.mTvRateMax = (MediumTextView) Utils.castView(viewFindRequiredView3, R.id.tv_rate_max, "field 'mTvRateMax'", MediumTextView.class);
        this.view7f0a08fc = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.setting.rate.SportRateActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportRateActivity.toSetRateMax(view2);
            }
        });
        sportRateActivity.mOptSportLimit = (OptionView) Utils.findRequiredViewAsType(view, R.id.opt_sport_limit, "field 'mOptSportLimit'", OptionView.class);
        sportRateActivity.mOptSportAnaerobicEndurance = (OptionView) Utils.findRequiredViewAsType(view, R.id.opt_sport_anaerobic_endurance, "field 'mOptSportAnaerobicEndurance'", OptionView.class);
        sportRateActivity.mOptSportAerobicEndurance = (OptionView) Utils.findRequiredViewAsType(view, R.id.opt_sport_aerobic_endurance, "field 'mOptSportAerobicEndurance'", OptionView.class);
        sportRateActivity.mOptSportBurningGrease = (OptionView) Utils.findRequiredViewAsType(view, R.id.opt_sport_burning_grease, "field 'mOptSportBurningGrease'", OptionView.class);
        sportRateActivity.mOptSportWarmUp = (OptionView) Utils.findRequiredViewAsType(view, R.id.opt_sport_warm_up, "field 'mOptSportWarmUp'", OptionView.class);
        sportRateActivity.mLlGbRate = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_bg_rate, "field 'mLlGbRate'", LinearLayout.class);
        sportRateActivity.mRvRateUpperLimitWarning = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rv_rate_upper_limit_warning, "field 'mRvRateUpperLimitWarning'", RelativeLayout.class);
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.tv_rate_max_title, "method 'toRateMax'");
        this.view7f0a08fd = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.setting.rate.SportRateActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportRateActivity.toRateMax(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SportRateActivity sportRateActivity = this.target;
        if (sportRateActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        sportRateActivity.mTitleLeftBtn = null;
        sportRateActivity.mTitleText = null;
        sportRateActivity.mToggle = null;
        sportRateActivity.mOptSportHeartRate = null;
        sportRateActivity.mTvRateUpperLimitWarning = null;
        sportRateActivity.mTvRateUpperLimitWarningNow = null;
        sportRateActivity.mTvRateMax = null;
        sportRateActivity.mOptSportLimit = null;
        sportRateActivity.mOptSportAnaerobicEndurance = null;
        sportRateActivity.mOptSportAerobicEndurance = null;
        sportRateActivity.mOptSportBurningGrease = null;
        sportRateActivity.mOptSportWarmUp = null;
        sportRateActivity.mLlGbRate = null;
        sportRateActivity.mRvRateUpperLimitWarning = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
        this.view7f0a056e.setOnClickListener(null);
        this.view7f0a056e = null;
        this.view7f0a08fc.setOnClickListener(null);
        this.view7f0a08fc = null;
        this.view7f0a08fd.setOnClickListener(null);
        this.view7f0a08fd = null;
    }
}