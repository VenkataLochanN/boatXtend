package com.ido.life.dialog;

import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class LoginAbroadDialogFragment_ViewBinding implements Unbinder {
    private LoginAbroadDialogFragment target;
    private View view7f0a04b0;
    private View view7f0a04b1;
    private View view7f0a04b4;

    public LoginAbroadDialogFragment_ViewBinding(final LoginAbroadDialogFragment loginAbroadDialogFragment, View view) {
        this.target = loginAbroadDialogFragment;
        loginAbroadDialogFragment.mLlShareLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_share_layout, "field 'mLlShareLayout'", LinearLayout.class);
        loginAbroadDialogFragment.mIbLoginOther = (ImageButton) Utils.findRequiredViewAsType(view, R.id.login_other, "field 'mIbLoginOther'", ImageButton.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.login_facebook, "method 'doClickPlat'");
        this.view7f0a04b0 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.LoginAbroadDialogFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                loginAbroadDialogFragment.doClickPlat(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.login_twitter, "method 'doClickPlat'");
        this.view7f0a04b4 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.LoginAbroadDialogFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                loginAbroadDialogFragment.doClickPlat(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.login_google, "method 'doClickPlat'");
        this.view7f0a04b1 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.LoginAbroadDialogFragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                loginAbroadDialogFragment.doClickPlat(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        LoginAbroadDialogFragment loginAbroadDialogFragment = this.target;
        if (loginAbroadDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        loginAbroadDialogFragment.mLlShareLayout = null;
        loginAbroadDialogFragment.mIbLoginOther = null;
        this.view7f0a04b0.setOnClickListener(null);
        this.view7f0a04b0 = null;
        this.view7f0a04b4.setOnClickListener(null);
        this.view7f0a04b4 = null;
        this.view7f0a04b1.setOnClickListener(null);
        this.view7f0a04b1 = null;
    }
}