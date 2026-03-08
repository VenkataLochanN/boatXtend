package com.ido.life.module.user.register;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.module.user.view.ViewMeCountryArea;
import com.ido.life.module.user.view.ViewMePassword;
import com.ido.life.module.user.view.ViewMePhone;

/* JADX INFO: loaded from: classes3.dex */
public class RegisterActivity_ViewBinding implements Unbinder {
    private RegisterActivity target;
    private View view7f0a0108;
    private View view7f0a04e0;
    private View view7f0a0776;

    public RegisterActivity_ViewBinding(RegisterActivity registerActivity) {
        this(registerActivity, registerActivity.getWindow().getDecorView());
    }

    public RegisterActivity_ViewBinding(final RegisterActivity registerActivity, View view) {
        this.target = registerActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.title_leftBtn, "field 'mTitleLeftBtn' and method 'doClickBack'");
        registerActivity.mTitleLeftBtn = (ImageButton) Utils.castView(viewFindRequiredView, R.id.title_leftBtn, "field 'mTitleLeftBtn'", ImageButton.class);
        this.view7f0a0776 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.register.RegisterActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                registerActivity.doClickBack(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.country_area_view, "field 'mViewMeCountryArea' and method 'chooseCountry'");
        registerActivity.mViewMeCountryArea = (ViewMeCountryArea) Utils.castView(viewFindRequiredView2, R.id.country_area_view, "field 'mViewMeCountryArea'", ViewMeCountryArea.class);
        this.view7f0a0108 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.register.RegisterActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                registerActivity.chooseCountry(view2);
            }
        });
        registerActivity.mTvRegisterTitle = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_register_title, "field 'mTvRegisterTitle'", RegularTextView.class);
        registerActivity.mPhoneView = (ViewMePhone) Utils.findRequiredViewAsType(view, R.id.phone_view, "field 'mPhoneView'", ViewMePhone.class);
        registerActivity.mPasswordView = (ViewMePassword) Utils.findRequiredViewAsType(view, R.id.password_view, "field 'mPasswordView'", ViewMePassword.class);
        registerActivity.mTvPasswordTip = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_password_tip, "field 'mTvPasswordTip'", TextView.class);
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.me_submit, "field 'mMeSubmit' and method 'doClickSubmit'");
        registerActivity.mMeSubmit = (TextView) Utils.castView(viewFindRequiredView3, R.id.me_submit, "field 'mMeSubmit'", TextView.class);
        this.view7f0a04e0 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.register.RegisterActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                registerActivity.doClickSubmit(view2);
            }
        });
        registerActivity.mCheckBox = (CheckBox) Utils.findRequiredViewAsType(view, R.id.cb_agreement, "field 'mCheckBox'", CheckBox.class);
        registerActivity.mTvPrivateAgreement = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_private_agreement, "field 'mTvPrivateAgreement'", TextView.class);
        registerActivity.mTvCountryArea = (TextView) Utils.findRequiredViewAsType(view, R.id.me_name_et, "field 'mTvCountryArea'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        RegisterActivity registerActivity = this.target;
        if (registerActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        registerActivity.mTitleLeftBtn = null;
        registerActivity.mViewMeCountryArea = null;
        registerActivity.mTvRegisterTitle = null;
        registerActivity.mPhoneView = null;
        registerActivity.mPasswordView = null;
        registerActivity.mTvPasswordTip = null;
        registerActivity.mMeSubmit = null;
        registerActivity.mCheckBox = null;
        registerActivity.mTvPrivateAgreement = null;
        registerActivity.mTvCountryArea = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
        this.view7f0a0108.setOnClickListener(null);
        this.view7f0a0108 = null;
        this.view7f0a04e0.setOnClickListener(null);
        this.view7f0a04e0 = null;
    }
}