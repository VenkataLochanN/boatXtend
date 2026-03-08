package com.ido.life.module.user.about;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.viewgroup.CustomItemLabelView;

/* JADX INFO: loaded from: classes.dex */
public class AboutUsActivity_ViewBinding implements Unbinder {
    private AboutUsActivity target;
    private View view7f0a031e;
    private View view7f0a0567;
    private View view7f0a0569;
    private View view7f0a0576;
    private View view7f0a0776;

    public AboutUsActivity_ViewBinding(AboutUsActivity aboutUsActivity) {
        this(aboutUsActivity, aboutUsActivity.getWindow().getDecorView());
    }

    public AboutUsActivity_ViewBinding(final AboutUsActivity aboutUsActivity, View view) {
        this.target = aboutUsActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.title_leftBtn, "field 'mTitleLeftBtn' and method 'toBack'");
        aboutUsActivity.mTitleLeftBtn = (ImageButton) Utils.castView(viewFindRequiredView, R.id.title_leftBtn, "field 'mTitleLeftBtn'", ImageButton.class);
        this.view7f0a0776 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.about.AboutUsActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                aboutUsActivity.toBack(view2);
            }
        });
        aboutUsActivity.mTitleText = (TextView) Utils.findRequiredViewAsType(view, R.id.title_text, "field 'mTitleText'", TextView.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.iv_logo_about, "field 'mIvLogoAbout' and method 'logoClick'");
        aboutUsActivity.mIvLogoAbout = (ImageView) Utils.castView(viewFindRequiredView2, R.id.iv_logo_about, "field 'mIvLogoAbout'", ImageView.class);
        this.view7f0a031e = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.about.AboutUsActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                aboutUsActivity.logoClick(view2);
            }
        });
        aboutUsActivity.mTvAppName = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_app_name, "field 'mTvAppName'", TextView.class);
        aboutUsActivity.mTvVersion = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_version, "field 'mTvVersion'", TextView.class);
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.opt_user_agreement, "field 'mOptUserAgreement' and method 'toUserAgreement'");
        aboutUsActivity.mOptUserAgreement = (CustomItemLabelView) Utils.castView(viewFindRequiredView3, R.id.opt_user_agreement, "field 'mOptUserAgreement'", CustomItemLabelView.class);
        this.view7f0a0576 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.about.AboutUsActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                aboutUsActivity.toUserAgreement(view2);
            }
        });
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.opt_privacy_policy, "field 'mOptPrivacyPolicy' and method 'toPrivacyPolicy'");
        aboutUsActivity.mOptPrivacyPolicy = (CustomItemLabelView) Utils.castView(viewFindRequiredView4, R.id.opt_privacy_policy, "field 'mOptPrivacyPolicy'", CustomItemLabelView.class);
        this.view7f0a0569 = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.about.AboutUsActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                aboutUsActivity.toPrivacyPolicy(view2);
            }
        });
        View viewFindRequiredView5 = Utils.findRequiredView(view, R.id.opt_disagree_policy, "field 'mOptDisagreePolicy' and method 'disagreePolicy'");
        aboutUsActivity.mOptDisagreePolicy = (CustomItemLabelView) Utils.castView(viewFindRequiredView5, R.id.opt_disagree_policy, "field 'mOptDisagreePolicy'", CustomItemLabelView.class);
        this.view7f0a0567 = viewFindRequiredView5;
        viewFindRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.about.AboutUsActivity_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                aboutUsActivity.disagreePolicy(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        AboutUsActivity aboutUsActivity = this.target;
        if (aboutUsActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        aboutUsActivity.mTitleLeftBtn = null;
        aboutUsActivity.mTitleText = null;
        aboutUsActivity.mIvLogoAbout = null;
        aboutUsActivity.mTvAppName = null;
        aboutUsActivity.mTvVersion = null;
        aboutUsActivity.mOptUserAgreement = null;
        aboutUsActivity.mOptPrivacyPolicy = null;
        aboutUsActivity.mOptDisagreePolicy = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
        this.view7f0a031e.setOnClickListener(null);
        this.view7f0a031e = null;
        this.view7f0a0576.setOnClickListener(null);
        this.view7f0a0576 = null;
        this.view7f0a0569.setOnClickListener(null);
        this.view7f0a0569 = null;
        this.view7f0a0567.setOnClickListener(null);
        this.view7f0a0567 = null;
    }
}