package com.ido.life.module.user.bind;

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
public class BindInputPasswordActivity_ViewBinding implements Unbinder {
    private BindInputPasswordActivity target;
    private View view7f0a0083;
    private View view7f0a04e1;
    private View view7f0a0776;

    public BindInputPasswordActivity_ViewBinding(BindInputPasswordActivity bindInputPasswordActivity) {
        this(bindInputPasswordActivity, bindInputPasswordActivity.getWindow().getDecorView());
    }

    public BindInputPasswordActivity_ViewBinding(final BindInputPasswordActivity bindInputPasswordActivity, View view) {
        this.target = bindInputPasswordActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.title_leftBtn, "field 'mTitleLeftBtn' and method 'doClickBack'");
        bindInputPasswordActivity.mTitleLeftBtn = (ImageButton) Utils.castView(viewFindRequiredView, R.id.title_leftBtn, "field 'mTitleLeftBtn'", ImageButton.class);
        this.view7f0a0776 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.bind.BindInputPasswordActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                bindInputPasswordActivity.doClickBack(view2);
            }
        });
        bindInputPasswordActivity.mPhoneView = (ViewMePhone) Utils.findRequiredViewAsType(view, R.id.phone_view, "field 'mPhoneView'", ViewMePhone.class);
        bindInputPasswordActivity.mPasswordView = (ViewMePassword) Utils.findRequiredViewAsType(view, R.id.password_view, "field 'mPasswordView'", ViewMePassword.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.btn_forgetpass, "field 'mBtnForgetpass' and method 'doClickForgetPass'");
        bindInputPasswordActivity.mBtnForgetpass = (TextView) Utils.castView(viewFindRequiredView2, R.id.btn_forgetpass, "field 'mBtnForgetpass'", TextView.class);
        this.view7f0a0083 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.bind.BindInputPasswordActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                bindInputPasswordActivity.doClickForgetPass(view2);
            }
        });
        bindInputPasswordActivity.mTvPasswordTitle = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_password_title, "field 'mTvPasswordTitle'", RegularTextView.class);
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.me_submit_bind_account, "field 'mMeSubmit' and method 'doClickSubmitBindAccount'");
        bindInputPasswordActivity.mMeSubmit = (TextView) Utils.castView(viewFindRequiredView3, R.id.me_submit_bind_account, "field 'mMeSubmit'", TextView.class);
        this.view7f0a04e1 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.bind.BindInputPasswordActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                bindInputPasswordActivity.doClickSubmitBindAccount(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        BindInputPasswordActivity bindInputPasswordActivity = this.target;
        if (bindInputPasswordActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        bindInputPasswordActivity.mTitleLeftBtn = null;
        bindInputPasswordActivity.mPhoneView = null;
        bindInputPasswordActivity.mPasswordView = null;
        bindInputPasswordActivity.mBtnForgetpass = null;
        bindInputPasswordActivity.mTvPasswordTitle = null;
        bindInputPasswordActivity.mMeSubmit = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
        this.view7f0a0083.setOnClickListener(null);
        this.view7f0a0083 = null;
        this.view7f0a04e1.setOnClickListener(null);
        this.view7f0a04e1 = null;
    }
}