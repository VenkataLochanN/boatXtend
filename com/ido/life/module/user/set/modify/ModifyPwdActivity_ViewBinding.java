package com.ido.life.module.user.set.modify;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.module.user.view.ViewMePassword;

/* JADX INFO: loaded from: classes3.dex */
public class ModifyPwdActivity_ViewBinding implements Unbinder {
    private ModifyPwdActivity target;
    private View view7f0a0776;
    private View view7f0a0859;
    private View view7f0a097e;

    public ModifyPwdActivity_ViewBinding(ModifyPwdActivity modifyPwdActivity) {
        this(modifyPwdActivity, modifyPwdActivity.getWindow().getDecorView());
    }

    public ModifyPwdActivity_ViewBinding(final ModifyPwdActivity modifyPwdActivity, View view) {
        this.target = modifyPwdActivity;
        modifyPwdActivity.mTitleText = (TextView) Utils.findRequiredViewAsType(view, R.id.title_text, "field 'mTitleText'", TextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.title_leftBtn, "field 'mtitleLeftBtn' and method 'toBack'");
        modifyPwdActivity.mtitleLeftBtn = (ImageButton) Utils.castView(viewFindRequiredView, R.id.title_leftBtn, "field 'mtitleLeftBtn'", ImageButton.class);
        this.view7f0a0776 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.set.modify.ModifyPwdActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                modifyPwdActivity.toBack(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.tv_forget_password, "field 'mTvForgetPassword' and method 'toForgetPassword'");
        modifyPwdActivity.mTvForgetPassword = (TextView) Utils.castView(viewFindRequiredView2, R.id.tv_forget_password, "field 'mTvForgetPassword'", TextView.class);
        this.view7f0a0859 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.set.modify.ModifyPwdActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                modifyPwdActivity.toForgetPassword(view2);
            }
        });
        modifyPwdActivity.mResetPasswordOld = (ViewMePassword) Utils.findRequiredViewAsType(view, R.id.reset_password_old, "field 'mResetPasswordOld'", ViewMePassword.class);
        modifyPwdActivity.mResetPasswordFirst = (ViewMePassword) Utils.findRequiredViewAsType(view, R.id.reset_password_first, "field 'mResetPasswordFirst'", ViewMePassword.class);
        modifyPwdActivity.mResetPasswordSecond = (ViewMePassword) Utils.findRequiredViewAsType(view, R.id.reset_password_second, "field 'mResetPasswordSecond'", ViewMePassword.class);
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.tv_submit, "field 'mMeSubmit' and method 'toSubmit'");
        modifyPwdActivity.mMeSubmit = (TextView) Utils.castView(viewFindRequiredView3, R.id.tv_submit, "field 'mMeSubmit'", TextView.class);
        this.view7f0a097e = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.set.modify.ModifyPwdActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                modifyPwdActivity.toSubmit(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        ModifyPwdActivity modifyPwdActivity = this.target;
        if (modifyPwdActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        modifyPwdActivity.mTitleText = null;
        modifyPwdActivity.mtitleLeftBtn = null;
        modifyPwdActivity.mTvForgetPassword = null;
        modifyPwdActivity.mResetPasswordOld = null;
        modifyPwdActivity.mResetPasswordFirst = null;
        modifyPwdActivity.mResetPasswordSecond = null;
        modifyPwdActivity.mMeSubmit = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
        this.view7f0a0859.setOnClickListener(null);
        this.view7f0a0859 = null;
        this.view7f0a097e.setOnClickListener(null);
        this.view7f0a097e = null;
    }
}