package com.ido.life.module.sport.setting;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.viewgroup.OptionView;

/* JADX INFO: loaded from: classes2.dex */
public class SportSettingActivity_ViewBinding implements Unbinder {
    private SportSettingActivity target;
    private View view7f0a0570;
    private View view7f0a0571;
    private View view7f0a0572;
    private View view7f0a0573;
    private View view7f0a0776;

    public SportSettingActivity_ViewBinding(SportSettingActivity sportSettingActivity) {
        this(sportSettingActivity, sportSettingActivity.getWindow().getDecorView());
    }

    public SportSettingActivity_ViewBinding(final SportSettingActivity sportSettingActivity, View view) {
        this.target = sportSettingActivity;
        sportSettingActivity.mTitleText = (TextView) Utils.findRequiredViewAsType(view, R.id.title_text, "field 'mTitleText'", TextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.opt_sport_target, "field 'mOptSportTarget' and method 'setSportTarget'");
        sportSettingActivity.mOptSportTarget = (OptionView) Utils.castView(viewFindRequiredView, R.id.opt_sport_target, "field 'mOptSportTarget'", OptionView.class);
        this.view7f0a0572 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.setting.SportSettingActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportSettingActivity.setSportTarget(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.opt_sport_voice, "field 'mOptSportVoice' and method 'setSportVoice'");
        sportSettingActivity.mOptSportVoice = (OptionView) Utils.castView(viewFindRequiredView2, R.id.opt_sport_voice, "field 'mOptSportVoice'", OptionView.class);
        this.view7f0a0573 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.setting.SportSettingActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportSettingActivity.setSportVoice(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.opt_sport_setting, "field 'mOptSportSetting' and method 'toSportSetting'");
        sportSettingActivity.mOptSportSetting = (OptionView) Utils.castView(viewFindRequiredView3, R.id.opt_sport_setting, "field 'mOptSportSetting'", OptionView.class);
        this.view7f0a0571 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.setting.SportSettingActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportSettingActivity.toSportSetting(view2);
            }
        });
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.opt_sport_rate, "field 'mOptSportRate' and method 'setSportRate'");
        sportSettingActivity.mOptSportRate = (OptionView) Utils.castView(viewFindRequiredView4, R.id.opt_sport_rate, "field 'mOptSportRate'", OptionView.class);
        this.view7f0a0570 = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.setting.SportSettingActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportSettingActivity.setSportRate(view2);
            }
        });
        sportSettingActivity.mViewSportLine = Utils.findRequiredView(view, R.id.view_sport_setting_line, "field 'mViewSportLine'");
        View viewFindRequiredView5 = Utils.findRequiredView(view, R.id.title_leftBtn, "method 'toBack'");
        this.view7f0a0776 = viewFindRequiredView5;
        viewFindRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.setting.SportSettingActivity_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportSettingActivity.toBack(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SportSettingActivity sportSettingActivity = this.target;
        if (sportSettingActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        sportSettingActivity.mTitleText = null;
        sportSettingActivity.mOptSportTarget = null;
        sportSettingActivity.mOptSportVoice = null;
        sportSettingActivity.mOptSportSetting = null;
        sportSettingActivity.mOptSportRate = null;
        sportSettingActivity.mViewSportLine = null;
        this.view7f0a0572.setOnClickListener(null);
        this.view7f0a0572 = null;
        this.view7f0a0573.setOnClickListener(null);
        this.view7f0a0573 = null;
        this.view7f0a0571.setOnClickListener(null);
        this.view7f0a0571 = null;
        this.view7f0a0570.setOnClickListener(null);
        this.view7f0a0570 = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
    }
}