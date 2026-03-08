package com.ido.life.module.user.bind;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.module.user.view.ViewMeGetCode;
import com.ido.life.module.user.view.ViewMePassword;
import com.ido.life.module.user.view.ViewMePhone;

/* JADX INFO: loaded from: classes3.dex */
public class BindRegisterAccountActivity_ViewBinding implements Unbinder {
    private BindRegisterAccountActivity target;
    private View view7f0a04e0;
    private View view7f0a0776;

    public BindRegisterAccountActivity_ViewBinding(BindRegisterAccountActivity bindRegisterAccountActivity) {
        this(bindRegisterAccountActivity, bindRegisterAccountActivity.getWindow().getDecorView());
    }

    public BindRegisterAccountActivity_ViewBinding(final BindRegisterAccountActivity bindRegisterAccountActivity, View view) {
        this.target = bindRegisterAccountActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.title_leftBtn, "field 'mTitleLeftBtn' and method 'doClickBack'");
        bindRegisterAccountActivity.mTitleLeftBtn = (ImageButton) Utils.castView(viewFindRequiredView, R.id.title_leftBtn, "field 'mTitleLeftBtn'", ImageButton.class);
        this.view7f0a0776 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.bind.BindRegisterAccountActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                bindRegisterAccountActivity.doClickBack(view2);
            }
        });
        bindRegisterAccountActivity.mPhoneView = (ViewMePhone) Utils.findRequiredViewAsType(view, R.id.phone_view, "field 'mPhoneView'", ViewMePhone.class);
        bindRegisterAccountActivity.mGetCodeView = (ViewMeGetCode) Utils.findRequiredViewAsType(view, R.id.get_code_view, "field 'mGetCodeView'", ViewMeGetCode.class);
        bindRegisterAccountActivity.mPasswordView = (ViewMePassword) Utils.findRequiredViewAsType(view, R.id.password_view, "field 'mPasswordView'", ViewMePassword.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.me_submit, "field 'mMeSubmit' and method 'doClickSubmit'");
        bindRegisterAccountActivity.mMeSubmit = (TextView) Utils.castView(viewFindRequiredView2, R.id.me_submit, "field 'mMeSubmit'", TextView.class);
        this.view7f0a04e0 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.bind.BindRegisterAccountActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                bindRegisterAccountActivity.doClickSubmit(view2);
            }
        });
        bindRegisterAccountActivity.mTvAgreement = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_agreement, "field 'mTvAgreement'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        BindRegisterAccountActivity bindRegisterAccountActivity = this.target;
        if (bindRegisterAccountActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        bindRegisterAccountActivity.mTitleLeftBtn = null;
        bindRegisterAccountActivity.mPhoneView = null;
        bindRegisterAccountActivity.mGetCodeView = null;
        bindRegisterAccountActivity.mPasswordView = null;
        bindRegisterAccountActivity.mMeSubmit = null;
        bindRegisterAccountActivity.mTvAgreement = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
        this.view7f0a04e0.setOnClickListener(null);
        this.view7f0a04e0 = null;
    }
}