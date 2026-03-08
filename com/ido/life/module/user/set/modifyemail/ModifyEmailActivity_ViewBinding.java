package com.ido.life.module.user.set.modifyemail;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.module.user.view.ViewMeGetCode;
import com.ido.life.module.user.view.ViewMePhone;

/* JADX INFO: loaded from: classes3.dex */
public class ModifyEmailActivity_ViewBinding implements Unbinder {
    private ModifyEmailActivity target;
    private View view7f0a0776;
    private View view7f0a097e;

    public ModifyEmailActivity_ViewBinding(ModifyEmailActivity modifyEmailActivity) {
        this(modifyEmailActivity, modifyEmailActivity.getWindow().getDecorView());
    }

    public ModifyEmailActivity_ViewBinding(final ModifyEmailActivity modifyEmailActivity, View view) {
        this.target = modifyEmailActivity;
        modifyEmailActivity.mTitleText = (TextView) Utils.findRequiredViewAsType(view, R.id.title_text, "field 'mTitleText'", TextView.class);
        modifyEmailActivity.mTvEmailTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_register_email_title, "field 'mTvEmailTitle'", TextView.class);
        modifyEmailActivity.mVMPhone = (ViewMePhone) Utils.findRequiredViewAsType(view, R.id.phone_view, "field 'mVMPhone'", ViewMePhone.class);
        modifyEmailActivity.mGetCodeView = (ViewMeGetCode) Utils.findRequiredViewAsType(view, R.id.view_get_code, "field 'mGetCodeView'", ViewMeGetCode.class);
        modifyEmailActivity.mModifyEmailTip = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_modify_email_tip, "field 'mModifyEmailTip'", TextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.tv_submit, "field 'mMeSubmit' and method 'toSubmit'");
        modifyEmailActivity.mMeSubmit = (TextView) Utils.castView(viewFindRequiredView, R.id.tv_submit, "field 'mMeSubmit'", TextView.class);
        this.view7f0a097e = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.set.modifyemail.ModifyEmailActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                modifyEmailActivity.toSubmit(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.title_leftBtn, "method 'toBack'");
        this.view7f0a0776 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.set.modifyemail.ModifyEmailActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                modifyEmailActivity.toBack(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        ModifyEmailActivity modifyEmailActivity = this.target;
        if (modifyEmailActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        modifyEmailActivity.mTitleText = null;
        modifyEmailActivity.mTvEmailTitle = null;
        modifyEmailActivity.mVMPhone = null;
        modifyEmailActivity.mGetCodeView = null;
        modifyEmailActivity.mModifyEmailTip = null;
        modifyEmailActivity.mMeSubmit = null;
        this.view7f0a097e.setOnClickListener(null);
        this.view7f0a097e = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
    }
}