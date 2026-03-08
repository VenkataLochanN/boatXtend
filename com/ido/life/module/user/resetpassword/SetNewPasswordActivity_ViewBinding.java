package com.ido.life.module.user.resetpassword;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.module.user.view.ViewMePassword;

/* JADX INFO: loaded from: classes3.dex */
public class SetNewPasswordActivity_ViewBinding implements Unbinder {
    private SetNewPasswordActivity target;
    private View view7f0a04e0;
    private View view7f0a0776;

    public SetNewPasswordActivity_ViewBinding(SetNewPasswordActivity setNewPasswordActivity) {
        this(setNewPasswordActivity, setNewPasswordActivity.getWindow().getDecorView());
    }

    public SetNewPasswordActivity_ViewBinding(final SetNewPasswordActivity setNewPasswordActivity, View view) {
        this.target = setNewPasswordActivity;
        setNewPasswordActivity.mTvReset = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.title_reset, "field 'mTvReset'", RegularTextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.title_leftBtn, "field 'mtitleLeftBtn' and method 'toBack'");
        setNewPasswordActivity.mtitleLeftBtn = (ImageButton) Utils.castView(viewFindRequiredView, R.id.title_leftBtn, "field 'mtitleLeftBtn'", ImageButton.class);
        this.view7f0a0776 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.resetpassword.SetNewPasswordActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                setNewPasswordActivity.toBack(view2);
            }
        });
        setNewPasswordActivity.mPasswordViewFirst = (ViewMePassword) Utils.findRequiredViewAsType(view, R.id.reset_password_first, "field 'mPasswordViewFirst'", ViewMePassword.class);
        setNewPasswordActivity.mPasswordViewSecond = (ViewMePassword) Utils.findRequiredViewAsType(view, R.id.reset_password_second, "field 'mPasswordViewSecond'", ViewMePassword.class);
        setNewPasswordActivity.mTvPasswordTip = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_password_tip, "field 'mTvPasswordTip'", TextView.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.me_submit, "field 'mMeSubmit' and method 'toSetNewPassword'");
        setNewPasswordActivity.mMeSubmit = (TextView) Utils.castView(viewFindRequiredView2, R.id.me_submit, "field 'mMeSubmit'", TextView.class);
        this.view7f0a04e0 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.resetpassword.SetNewPasswordActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                setNewPasswordActivity.toSetNewPassword(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SetNewPasswordActivity setNewPasswordActivity = this.target;
        if (setNewPasswordActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        setNewPasswordActivity.mTvReset = null;
        setNewPasswordActivity.mtitleLeftBtn = null;
        setNewPasswordActivity.mPasswordViewFirst = null;
        setNewPasswordActivity.mPasswordViewSecond = null;
        setNewPasswordActivity.mTvPasswordTip = null;
        setNewPasswordActivity.mMeSubmit = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
        this.view7f0a04e0.setOnClickListener(null);
        this.view7f0a04e0 = null;
    }
}