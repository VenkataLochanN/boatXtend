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
public class BindAccountActivity_ViewBinding implements Unbinder {
    private BindAccountActivity target;
    private View view7f0a0083;
    private View view7f0a04e1;
    private View view7f0a0776;

    public BindAccountActivity_ViewBinding(BindAccountActivity bindAccountActivity) {
        this(bindAccountActivity, bindAccountActivity.getWindow().getDecorView());
    }

    public BindAccountActivity_ViewBinding(final BindAccountActivity bindAccountActivity, View view) {
        this.target = bindAccountActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.title_leftBtn, "field 'mTitleLeftBtn' and method 'doClickBack'");
        bindAccountActivity.mTitleLeftBtn = (ImageButton) Utils.castView(viewFindRequiredView, R.id.title_leftBtn, "field 'mTitleLeftBtn'", ImageButton.class);
        this.view7f0a0776 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.bind.BindAccountActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                bindAccountActivity.doClickBack(view2);
            }
        });
        bindAccountActivity.mPhoneView = (ViewMePhone) Utils.findRequiredViewAsType(view, R.id.phone_view, "field 'mPhoneView'", ViewMePhone.class);
        bindAccountActivity.mPasswordView = (ViewMePassword) Utils.findRequiredViewAsType(view, R.id.password_view, "field 'mPasswordView'", ViewMePassword.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.btn_forgetpass, "field 'mBtnForgetpass' and method 'doClickForgetPass'");
        bindAccountActivity.mBtnForgetpass = (TextView) Utils.castView(viewFindRequiredView2, R.id.btn_forgetpass, "field 'mBtnForgetpass'", TextView.class);
        this.view7f0a0083 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.bind.BindAccountActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                bindAccountActivity.doClickForgetPass(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.me_submit_bind_account, "field 'mMeSubmit' and method 'doClickSubmitBindAccount'");
        bindAccountActivity.mMeSubmit = (TextView) Utils.castView(viewFindRequiredView3, R.id.me_submit_bind_account, "field 'mMeSubmit'", TextView.class);
        this.view7f0a04e1 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.bind.BindAccountActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                bindAccountActivity.doClickSubmitBindAccount(view2);
            }
        });
        bindAccountActivity.mTVBindTitle = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_bind_title, "field 'mTVBindTitle'", RegularTextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        BindAccountActivity bindAccountActivity = this.target;
        if (bindAccountActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        bindAccountActivity.mTitleLeftBtn = null;
        bindAccountActivity.mPhoneView = null;
        bindAccountActivity.mPasswordView = null;
        bindAccountActivity.mBtnForgetpass = null;
        bindAccountActivity.mMeSubmit = null;
        bindAccountActivity.mTVBindTitle = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
        this.view7f0a0083.setOnClickListener(null);
        this.view7f0a0083 = null;
        this.view7f0a04e1.setOnClickListener(null);
        this.view7f0a04e1 = null;
    }
}