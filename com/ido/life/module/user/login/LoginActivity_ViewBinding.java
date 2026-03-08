package com.ido.life.module.user.login;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.module.user.view.ViewMePassword;
import com.ido.life.module.user.view.ViewMePhone;

/* JADX INFO: loaded from: classes3.dex */
public class LoginActivity_ViewBinding implements Unbinder {
    private LoginActivity target;
    private View view7f0a0083;
    private View view7f0a04b0;
    private View view7f0a04b1;
    private View view7f0a04b2;
    private View view7f0a04b3;
    private View view7f0a04b4;
    private View view7f0a04b5;
    private View view7f0a04e0;
    private View view7f0a0776;

    public LoginActivity_ViewBinding(LoginActivity loginActivity) {
        this(loginActivity, loginActivity.getWindow().getDecorView());
    }

    public LoginActivity_ViewBinding(final LoginActivity loginActivity, View view) {
        this.target = loginActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.title_leftBtn, "field 'mTitleLeftBtn' and method 'doClickBack'");
        loginActivity.mTitleLeftBtn = (ImageButton) Utils.castView(viewFindRequiredView, R.id.title_leftBtn, "field 'mTitleLeftBtn'", ImageButton.class);
        this.view7f0a0776 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.login.LoginActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                loginActivity.doClickBack(view2);
            }
        });
        loginActivity.mPhoneView = (ViewMePhone) Utils.findRequiredViewAsType(view, R.id.phone_view, "field 'mPhoneView'", ViewMePhone.class);
        loginActivity.mPasswordView = (ViewMePassword) Utils.findRequiredViewAsType(view, R.id.password_view, "field 'mPasswordView'", ViewMePassword.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.btn_forgetpass, "field 'mBtnForgetpass' and method 'doClickForgetPass'");
        loginActivity.mBtnForgetpass = (TextView) Utils.castView(viewFindRequiredView2, R.id.btn_forgetpass, "field 'mBtnForgetpass'", TextView.class);
        this.view7f0a0083 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.login.LoginActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                loginActivity.doClickForgetPass(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.me_submit, "field 'mMeSubmit' and method 'doClickSubmit'");
        loginActivity.mMeSubmit = (TextView) Utils.castView(viewFindRequiredView3, R.id.me_submit, "field 'mMeSubmit'", TextView.class);
        this.view7f0a04e0 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.login.LoginActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                loginActivity.doClickSubmit(view2);
            }
        });
        loginActivity.mTvTitle = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_login, "field 'mTvTitle'", RegularTextView.class);
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.login_weixin, "method 'doClickThirdLogin'");
        this.view7f0a04b5 = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.login.LoginActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                loginActivity.doClickThirdLogin(view2);
            }
        });
        View viewFindRequiredView5 = Utils.findRequiredView(view, R.id.login_qq, "method 'doClickThirdLogin'");
        this.view7f0a04b3 = viewFindRequiredView5;
        viewFindRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.login.LoginActivity_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                loginActivity.doClickThirdLogin(view2);
            }
        });
        View viewFindRequiredView6 = Utils.findRequiredView(view, R.id.login_twitter, "method 'doClickThirdLogin'");
        this.view7f0a04b4 = viewFindRequiredView6;
        viewFindRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.login.LoginActivity_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                loginActivity.doClickThirdLogin(view2);
            }
        });
        View viewFindRequiredView7 = Utils.findRequiredView(view, R.id.login_facebook, "method 'doClickThirdLogin'");
        this.view7f0a04b0 = viewFindRequiredView7;
        viewFindRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.login.LoginActivity_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                loginActivity.doClickThirdLogin(view2);
            }
        });
        View viewFindRequiredView8 = Utils.findRequiredView(view, R.id.login_google, "method 'doClickThirdLogin'");
        this.view7f0a04b1 = viewFindRequiredView8;
        viewFindRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.login.LoginActivity_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                loginActivity.doClickThirdLogin(view2);
            }
        });
        View viewFindRequiredView9 = Utils.findRequiredView(view, R.id.login_other, "method 'toShowOtherLogin'");
        this.view7f0a04b2 = viewFindRequiredView9;
        viewFindRequiredView9.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.login.LoginActivity_ViewBinding.9
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                loginActivity.toShowOtherLogin(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        LoginActivity loginActivity = this.target;
        if (loginActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        loginActivity.mTitleLeftBtn = null;
        loginActivity.mPhoneView = null;
        loginActivity.mPasswordView = null;
        loginActivity.mBtnForgetpass = null;
        loginActivity.mMeSubmit = null;
        loginActivity.mTvTitle = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
        this.view7f0a0083.setOnClickListener(null);
        this.view7f0a0083 = null;
        this.view7f0a04e0.setOnClickListener(null);
        this.view7f0a04e0 = null;
        this.view7f0a04b5.setOnClickListener(null);
        this.view7f0a04b5 = null;
        this.view7f0a04b3.setOnClickListener(null);
        this.view7f0a04b3 = null;
        this.view7f0a04b4.setOnClickListener(null);
        this.view7f0a04b4 = null;
        this.view7f0a04b0.setOnClickListener(null);
        this.view7f0a04b0 = null;
        this.view7f0a04b1.setOnClickListener(null);
        this.view7f0a04b1 = null;
        this.view7f0a04b2.setOnClickListener(null);
        this.view7f0a04b2 = null;
    }
}