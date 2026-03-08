package com.ido.life.module.user.resetpassword;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.module.user.view.ViewMeGetCode;
import com.ido.life.module.user.view.ViewMePhone;

/* JADX INFO: loaded from: classes3.dex */
public class ResetPassActivity_ViewBinding implements Unbinder {
    private ResetPassActivity target;
    private View view7f0a04e0;
    private View view7f0a0776;

    public ResetPassActivity_ViewBinding(ResetPassActivity resetPassActivity) {
        this(resetPassActivity, resetPassActivity.getWindow().getDecorView());
    }

    public ResetPassActivity_ViewBinding(final ResetPassActivity resetPassActivity, View view) {
        this.target = resetPassActivity;
        resetPassActivity.mTvReset = (TextView) Utils.findRequiredViewAsType(view, R.id.title_reset, "field 'mTvReset'", TextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.title_leftBtn, "field 'mtitleLeftBtn' and method 'toBack'");
        resetPassActivity.mtitleLeftBtn = (ImageButton) Utils.castView(viewFindRequiredView, R.id.title_leftBtn, "field 'mtitleLeftBtn'", ImageButton.class);
        this.view7f0a0776 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.resetpassword.ResetPassActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                resetPassActivity.toBack(view2);
            }
        });
        resetPassActivity.mNameView = (ViewMePhone) Utils.findRequiredViewAsType(view, R.id.name_view, "field 'mNameView'", ViewMePhone.class);
        resetPassActivity.mGetCodeView = (ViewMeGetCode) Utils.findRequiredViewAsType(view, R.id.get_code_view, "field 'mGetCodeView'", ViewMeGetCode.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.me_submit, "field 'mMeSubmit' and method 'toForget'");
        resetPassActivity.mMeSubmit = (TextView) Utils.castView(viewFindRequiredView2, R.id.me_submit, "field 'mMeSubmit'", TextView.class);
        this.view7f0a04e0 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.resetpassword.ResetPassActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                resetPassActivity.toForget(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        ResetPassActivity resetPassActivity = this.target;
        if (resetPassActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        resetPassActivity.mTvReset = null;
        resetPassActivity.mtitleLeftBtn = null;
        resetPassActivity.mNameView = null;
        resetPassActivity.mGetCodeView = null;
        resetPassActivity.mMeSubmit = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
        this.view7f0a04e0.setOnClickListener(null);
        this.view7f0a04e0 = null;
    }
}