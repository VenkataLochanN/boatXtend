package com.ido.life.module.sport.power;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class SportSettingPowerActivity_ViewBinding implements Unbinder {
    private SportSettingPowerActivity target;
    private View view7f0a0776;
    private View view7f0a092e;
    private View view7f0a0930;
    private View view7f0a0931;

    public SportSettingPowerActivity_ViewBinding(SportSettingPowerActivity sportSettingPowerActivity) {
        this(sportSettingPowerActivity, sportSettingPowerActivity.getWindow().getDecorView());
    }

    public SportSettingPowerActivity_ViewBinding(final SportSettingPowerActivity sportSettingPowerActivity, View view) {
        this.target = sportSettingPowerActivity;
        sportSettingPowerActivity.mTitleText = (TextView) Utils.findRequiredViewAsType(view, R.id.title_text, "field 'mTitleText'", TextView.class);
        sportSettingPowerActivity.mTvSuggest = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_suggest, "field 'mTvSuggest'", TextView.class);
        sportSettingPowerActivity.mTvAssociatedTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_associated_title, "field 'mTvAssociatedTitle'", TextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.tv_set_associated, "field 'mTvSetAssociated' and method 'toAssociate'");
        sportSettingPowerActivity.mTvSetAssociated = (TextView) Utils.castView(viewFindRequiredView, R.id.tv_set_associated, "field 'mTvSetAssociated'", TextView.class);
        this.view7f0a092e = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.power.SportSettingPowerActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportSettingPowerActivity.toAssociate(view2);
            }
        });
        sportSettingPowerActivity.mTvEffect = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_associated_content, "field 'mTvEffect'", TextView.class);
        sportSettingPowerActivity.mLlLaunch = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_launch, "field 'mLlLaunch'", LinearLayout.class);
        sportSettingPowerActivity.mTvLaunchTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_launch_title, "field 'mTvLaunchTitle'", TextView.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.tv_set_launch, "field 'mTvSetLaunch' and method 'toSetStart'");
        sportSettingPowerActivity.mTvSetLaunch = (TextView) Utils.castView(viewFindRequiredView2, R.id.tv_set_launch, "field 'mTvSetLaunch'", TextView.class);
        this.view7f0a0930 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.power.SportSettingPowerActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportSettingPowerActivity.toSetStart(view2);
            }
        });
        sportSettingPowerActivity.mTvLaunchContent = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_launch_content, "field 'mTvLaunchContent'", TextView.class);
        sportSettingPowerActivity.mLlPower = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_power, "field 'mLlPower'", LinearLayout.class);
        sportSettingPowerActivity.mTvTitlePower = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title_power, "field 'mTvTitlePower'", TextView.class);
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.tv_set_power, "field 'mTvSetPower' and method 'toPower'");
        sportSettingPowerActivity.mTvSetPower = (TextView) Utils.castView(viewFindRequiredView3, R.id.tv_set_power, "field 'mTvSetPower'", TextView.class);
        this.view7f0a0931 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.power.SportSettingPowerActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportSettingPowerActivity.toPower(view2);
            }
        });
        sportSettingPowerActivity.mTvContentPower = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_content_power, "field 'mTvContentPower'", TextView.class);
        sportSettingPowerActivity.mLlClear = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_clear, "field 'mLlClear'", LinearLayout.class);
        sportSettingPowerActivity.mTvTitleClear = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title_clear, "field 'mTvTitleClear'", TextView.class);
        sportSettingPowerActivity.mTvSetClear = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_set_clear, "field 'mTvSetClear'", TextView.class);
        sportSettingPowerActivity.mTvContentClear = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_content_clear, "field 'mTvContentClear'", TextView.class);
        sportSettingPowerActivity.mTvStep = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_step, "field 'mTvStep'", TextView.class);
        sportSettingPowerActivity.mTvStepOne = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_step_one, "field 'mTvStepOne'", TextView.class);
        sportSettingPowerActivity.mTvStepTwo = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_step_two, "field 'mTvStepTwo'", TextView.class);
        sportSettingPowerActivity.mIvStepOne = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_step_one, "field 'mIvStepOne'", ImageView.class);
        sportSettingPowerActivity.mIvStepTwo = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_step_two, "field 'mIvStepTwo'", ImageView.class);
        sportSettingPowerActivity.mIvStepThree = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_step_three, "field 'mIvStepThree'", ImageView.class);
        sportSettingPowerActivity.mIvStepFour = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_step_four, "field 'mIvStepFour'", ImageView.class);
        sportSettingPowerActivity.mRgSportPowerTabOne = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.rg_sport_power_tab_one, "field 'mRgSportPowerTabOne'", RadioGroup.class);
        sportSettingPowerActivity.mRbSportPowerOne = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_sport_power_one, "field 'mRbSportPowerOne'", RadioButton.class);
        sportSettingPowerActivity.mRbSportPowerTwo = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_sport_power_two, "field 'mRbSportPowerTwo'", RadioButton.class);
        sportSettingPowerActivity.mRgSportPowerTabTwo = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.rg_sport_power_tab_two, "field 'mRgSportPowerTabTwo'", RadioGroup.class);
        sportSettingPowerActivity.mRbSportPowerThree = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_sport_power_three, "field 'mRbSportPowerThree'", RadioButton.class);
        sportSettingPowerActivity.mRbSportPowerFour = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_sport_power_four, "field 'mRbSportPowerFour'", RadioButton.class);
        sportSettingPowerActivity.mViewLaunch = Utils.findRequiredView(view, R.id.view_launch, "field 'mViewLaunch'");
        sportSettingPowerActivity.mViewAssociated = Utils.findRequiredView(view, R.id.view_associated, "field 'mViewAssociated'");
        sportSettingPowerActivity.mViewPower = Utils.findRequiredView(view, R.id.view_power, "field 'mViewPower'");
        sportSettingPowerActivity.mViewClear = Utils.findRequiredView(view, R.id.view_clear, "field 'mViewClear'");
        sportSettingPowerActivity.mLlStepOneTwo = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_step_one_two, "field 'mLlStepOneTwo'", LinearLayout.class);
        sportSettingPowerActivity.mIvStepFive = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_step_five, "field 'mIvStepFive'", ImageView.class);
        sportSettingPowerActivity.mIvStepSix = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_step_six, "field 'mIvStepSix'", ImageView.class);
        sportSettingPowerActivity.mLlStepTwo = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_step_two, "field 'mLlStepTwo'", LinearLayout.class);
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.title_leftBtn, "method 'tpBack'");
        this.view7f0a0776 = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.power.SportSettingPowerActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportSettingPowerActivity.tpBack(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SportSettingPowerActivity sportSettingPowerActivity = this.target;
        if (sportSettingPowerActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        sportSettingPowerActivity.mTitleText = null;
        sportSettingPowerActivity.mTvSuggest = null;
        sportSettingPowerActivity.mTvAssociatedTitle = null;
        sportSettingPowerActivity.mTvSetAssociated = null;
        sportSettingPowerActivity.mTvEffect = null;
        sportSettingPowerActivity.mLlLaunch = null;
        sportSettingPowerActivity.mTvLaunchTitle = null;
        sportSettingPowerActivity.mTvSetLaunch = null;
        sportSettingPowerActivity.mTvLaunchContent = null;
        sportSettingPowerActivity.mLlPower = null;
        sportSettingPowerActivity.mTvTitlePower = null;
        sportSettingPowerActivity.mTvSetPower = null;
        sportSettingPowerActivity.mTvContentPower = null;
        sportSettingPowerActivity.mLlClear = null;
        sportSettingPowerActivity.mTvTitleClear = null;
        sportSettingPowerActivity.mTvSetClear = null;
        sportSettingPowerActivity.mTvContentClear = null;
        sportSettingPowerActivity.mTvStep = null;
        sportSettingPowerActivity.mTvStepOne = null;
        sportSettingPowerActivity.mTvStepTwo = null;
        sportSettingPowerActivity.mIvStepOne = null;
        sportSettingPowerActivity.mIvStepTwo = null;
        sportSettingPowerActivity.mIvStepThree = null;
        sportSettingPowerActivity.mIvStepFour = null;
        sportSettingPowerActivity.mRgSportPowerTabOne = null;
        sportSettingPowerActivity.mRbSportPowerOne = null;
        sportSettingPowerActivity.mRbSportPowerTwo = null;
        sportSettingPowerActivity.mRgSportPowerTabTwo = null;
        sportSettingPowerActivity.mRbSportPowerThree = null;
        sportSettingPowerActivity.mRbSportPowerFour = null;
        sportSettingPowerActivity.mViewLaunch = null;
        sportSettingPowerActivity.mViewAssociated = null;
        sportSettingPowerActivity.mViewPower = null;
        sportSettingPowerActivity.mViewClear = null;
        sportSettingPowerActivity.mLlStepOneTwo = null;
        sportSettingPowerActivity.mIvStepFive = null;
        sportSettingPowerActivity.mIvStepSix = null;
        sportSettingPowerActivity.mLlStepTwo = null;
        this.view7f0a092e.setOnClickListener(null);
        this.view7f0a092e = null;
        this.view7f0a0930.setOnClickListener(null);
        this.view7f0a0930 = null;
        this.view7f0a0931.setOnClickListener(null);
        this.view7f0a0931 = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
    }
}