package com.ido.life.module.user.bindsetpassword;

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
public class ThirdBandSetPasswordActivity_ViewBinding implements Unbinder {
    private ThirdBandSetPasswordActivity target;
    private View view7f0a04e0;
    private View view7f0a0776;

    public ThirdBandSetPasswordActivity_ViewBinding(ThirdBandSetPasswordActivity thirdBandSetPasswordActivity) {
        this(thirdBandSetPasswordActivity, thirdBandSetPasswordActivity.getWindow().getDecorView());
    }

    public ThirdBandSetPasswordActivity_ViewBinding(final ThirdBandSetPasswordActivity thirdBandSetPasswordActivity, View view) {
        this.target = thirdBandSetPasswordActivity;
        thirdBandSetPasswordActivity.mTvReset = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.title_reset, "field 'mTvReset'", RegularTextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.title_leftBtn, "field 'mtitleLeftBtn' and method 'toBack'");
        thirdBandSetPasswordActivity.mtitleLeftBtn = (ImageButton) Utils.castView(viewFindRequiredView, R.id.title_leftBtn, "field 'mtitleLeftBtn'", ImageButton.class);
        this.view7f0a0776 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.bindsetpassword.ThirdBandSetPasswordActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                thirdBandSetPasswordActivity.toBack(view2);
            }
        });
        thirdBandSetPasswordActivity.mPasswordViewFirst = (ViewMePassword) Utils.findRequiredViewAsType(view, R.id.reset_password_first, "field 'mPasswordViewFirst'", ViewMePassword.class);
        thirdBandSetPasswordActivity.mPasswordViewSecond = (ViewMePassword) Utils.findRequiredViewAsType(view, R.id.reset_password_second, "field 'mPasswordViewSecond'", ViewMePassword.class);
        thirdBandSetPasswordActivity.mTvPasswordTip = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_password_tip, "field 'mTvPasswordTip'", TextView.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.me_submit, "field 'mMeSubmit' and method 'toSetNewPassword'");
        thirdBandSetPasswordActivity.mMeSubmit = (TextView) Utils.castView(viewFindRequiredView2, R.id.me_submit, "field 'mMeSubmit'", TextView.class);
        this.view7f0a04e0 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.bindsetpassword.ThirdBandSetPasswordActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                thirdBandSetPasswordActivity.toSetNewPassword(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        ThirdBandSetPasswordActivity thirdBandSetPasswordActivity = this.target;
        if (thirdBandSetPasswordActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        thirdBandSetPasswordActivity.mTvReset = null;
        thirdBandSetPasswordActivity.mtitleLeftBtn = null;
        thirdBandSetPasswordActivity.mPasswordViewFirst = null;
        thirdBandSetPasswordActivity.mPasswordViewSecond = null;
        thirdBandSetPasswordActivity.mTvPasswordTip = null;
        thirdBandSetPasswordActivity.mMeSubmit = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
        this.view7f0a04e0.setOnClickListener(null);
        this.view7f0a04e0 = null;
    }
}