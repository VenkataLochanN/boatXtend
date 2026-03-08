package com.ido.life.module.user.bind;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.module.user.view.ViewMePassword;
import com.ido.life.module.user.view.ViewMePhone;

/* JADX INFO: loaded from: classes3.dex */
public class BindInputCodeActivity_ViewBinding implements Unbinder {
    private BindInputCodeActivity target;
    private View view7f0a0083;
    private View view7f0a04e1;
    private View view7f0a0776;

    public BindInputCodeActivity_ViewBinding(BindInputCodeActivity bindInputCodeActivity) {
        this(bindInputCodeActivity, bindInputCodeActivity.getWindow().getDecorView());
    }

    public BindInputCodeActivity_ViewBinding(final BindInputCodeActivity bindInputCodeActivity, View view) {
        this.target = bindInputCodeActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.title_leftBtn, "field 'mTitleLeftBtn' and method 'doClickBack'");
        bindInputCodeActivity.mTitleLeftBtn = (ImageButton) Utils.castView(viewFindRequiredView, R.id.title_leftBtn, "field 'mTitleLeftBtn'", ImageButton.class);
        this.view7f0a0776 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.bind.BindInputCodeActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                bindInputCodeActivity.doClickBack(view2);
            }
        });
        bindInputCodeActivity.mPhoneView = (ViewMePhone) Utils.findRequiredViewAsType(view, R.id.phone_view, "field 'mPhoneView'", ViewMePhone.class);
        bindInputCodeActivity.mPasswordView = (ViewMePassword) Utils.findRequiredViewAsType(view, R.id.password_view, "field 'mPasswordView'", ViewMePassword.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.btn_forgetpass, "field 'mBtnForgetpass' and method 'doClickForgetPass'");
        bindInputCodeActivity.mBtnForgetpass = (TextView) Utils.castView(viewFindRequiredView2, R.id.btn_forgetpass, "field 'mBtnForgetpass'", TextView.class);
        this.view7f0a0083 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.bind.BindInputCodeActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                bindInputCodeActivity.doClickForgetPass(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.me_submit_bind_account, "field 'mMeSubmit' and method 'doClickSubmitBindAccount'");
        bindInputCodeActivity.mMeSubmit = (TextView) Utils.castView(viewFindRequiredView3, R.id.me_submit_bind_account, "field 'mMeSubmit'", TextView.class);
        this.view7f0a04e1 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.bind.BindInputCodeActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                bindInputCodeActivity.doClickSubmitBindAccount(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        BindInputCodeActivity bindInputCodeActivity = this.target;
        if (bindInputCodeActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        bindInputCodeActivity.mTitleLeftBtn = null;
        bindInputCodeActivity.mPhoneView = null;
        bindInputCodeActivity.mPasswordView = null;
        bindInputCodeActivity.mBtnForgetpass = null;
        bindInputCodeActivity.mMeSubmit = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
        this.view7f0a0083.setOnClickListener(null);
        this.view7f0a0083 = null;
        this.view7f0a04e1.setOnClickListener(null);
        this.view7f0a04e1 = null;
    }
}