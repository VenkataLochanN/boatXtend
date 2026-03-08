package com.ido.life.module.user.set;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.viewgroup.OptionView;

/* JADX INFO: loaded from: classes3.dex */
public class SettingActivity_ViewBinding implements Unbinder {
    private SettingActivity target;
    private View view7f0a0565;
    private View view7f0a0566;
    private View view7f0a0568;
    private View view7f0a056a;
    private View view7f0a0575;
    private View view7f0a0775;
    private View view7f0a0776;

    public SettingActivity_ViewBinding(SettingActivity settingActivity) {
        this(settingActivity, settingActivity.getWindow().getDecorView());
    }

    public SettingActivity_ViewBinding(final SettingActivity settingActivity, View view) {
        this.target = settingActivity;
        settingActivity.mTitleText = (TextView) Utils.findRequiredViewAsType(view, R.id.title_text, "field 'mTitleText'", TextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.opt_account_security, "field 'mOpt_account_security' and method 'toAccountSecurity'");
        settingActivity.mOpt_account_security = (OptionView) Utils.castView(viewFindRequiredView, R.id.opt_account_security, "field 'mOpt_account_security'", OptionView.class);
        this.view7f0a0565 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.set.SettingActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                settingActivity.toAccountSecurity(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.opt_language, "field 'mOpt_language' and method 'toLanguageSet'");
        settingActivity.mOpt_language = (OptionView) Utils.castView(viewFindRequiredView2, R.id.opt_language, "field 'mOpt_language'", OptionView.class);
        this.view7f0a0568 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.set.SettingActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                settingActivity.toLanguageSet(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.opt_unit, "field 'mOpt_unit' and method 'toUnitSet'");
        settingActivity.mOpt_unit = (OptionView) Utils.castView(viewFindRequiredView3, R.id.opt_unit, "field 'mOpt_unit'", OptionView.class);
        this.view7f0a0575 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.set.SettingActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                settingActivity.toUnitSet(view2);
            }
        });
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.opt_data_sharing, "field 'mOpt_data_sharing' and method 'toDataShare'");
        settingActivity.mOpt_data_sharing = (OptionView) Utils.castView(viewFindRequiredView4, R.id.opt_data_sharing, "field 'mOpt_data_sharing'", OptionView.class);
        this.view7f0a0566 = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.set.SettingActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                settingActivity.toDataShare(view2);
            }
        });
        View viewFindRequiredView5 = Utils.findRequiredView(view, R.id.opt_remove_cache, "field 'mOptRemoveCache' and method 'toRemoveCache'");
        settingActivity.mOptRemoveCache = (OptionView) Utils.castView(viewFindRequiredView5, R.id.opt_remove_cache, "field 'mOptRemoveCache'", OptionView.class);
        this.view7f0a056a = viewFindRequiredView5;
        viewFindRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.set.SettingActivity_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                settingActivity.toRemoveCache(view2);
            }
        });
        View viewFindRequiredView6 = Utils.findRequiredView(view, R.id.title_bar, "field 'mFrameLayout' and method 'toUploadData'");
        settingActivity.mFrameLayout = (FrameLayout) Utils.castView(viewFindRequiredView6, R.id.title_bar, "field 'mFrameLayout'", FrameLayout.class);
        this.view7f0a0775 = viewFindRequiredView6;
        viewFindRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.set.SettingActivity_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                settingActivity.toUploadData(view2);
            }
        });
        View viewFindRequiredView7 = Utils.findRequiredView(view, R.id.title_leftBtn, "method 'back'");
        this.view7f0a0776 = viewFindRequiredView7;
        viewFindRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.set.SettingActivity_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                settingActivity.back(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SettingActivity settingActivity = this.target;
        if (settingActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        settingActivity.mTitleText = null;
        settingActivity.mOpt_account_security = null;
        settingActivity.mOpt_language = null;
        settingActivity.mOpt_unit = null;
        settingActivity.mOpt_data_sharing = null;
        settingActivity.mOptRemoveCache = null;
        settingActivity.mFrameLayout = null;
        this.view7f0a0565.setOnClickListener(null);
        this.view7f0a0565 = null;
        this.view7f0a0568.setOnClickListener(null);
        this.view7f0a0568 = null;
        this.view7f0a0575.setOnClickListener(null);
        this.view7f0a0575 = null;
        this.view7f0a0566.setOnClickListener(null);
        this.view7f0a0566 = null;
        this.view7f0a056a.setOnClickListener(null);
        this.view7f0a056a = null;
        this.view7f0a0775.setOnClickListener(null);
        this.view7f0a0775 = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
    }
}