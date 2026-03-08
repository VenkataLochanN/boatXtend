package com.ido.life.dialog;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class NewSplashConfirmDialogFragment_ViewBinding implements Unbinder {
    private NewSplashConfirmDialogFragment target;
    private View view7f0a07f9;
    private View view7f0a080e;

    public NewSplashConfirmDialogFragment_ViewBinding(final NewSplashConfirmDialogFragment newSplashConfirmDialogFragment, View view) {
        this.target = newSplashConfirmDialogFragment;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.tv_cancel, "field 'mTvCancel' and method 'doClickCancel'");
        newSplashConfirmDialogFragment.mTvCancel = (TextView) Utils.castView(viewFindRequiredView, R.id.tv_cancel, "field 'mTvCancel'", TextView.class);
        this.view7f0a07f9 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.NewSplashConfirmDialogFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                newSplashConfirmDialogFragment.doClickCancel(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.tv_confirm, "field 'mTvConfirm' and method 'doClickConfirm'");
        newSplashConfirmDialogFragment.mTvConfirm = (TextView) Utils.castView(viewFindRequiredView2, R.id.tv_confirm, "field 'mTvConfirm'", TextView.class);
        this.view7f0a080e = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.NewSplashConfirmDialogFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                newSplashConfirmDialogFragment.doClickConfirm(view2);
            }
        });
        newSplashConfirmDialogFragment.mRadioButton = (CheckBox) Utils.findRequiredViewAsType(view, R.id.rb_protocol_policy, "field 'mRadioButton'", CheckBox.class);
        newSplashConfirmDialogFragment.mTvTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title, "field 'mTvTitle'", TextView.class);
        newSplashConfirmDialogFragment.mTvContext = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_context, "field 'mTvContext'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        NewSplashConfirmDialogFragment newSplashConfirmDialogFragment = this.target;
        if (newSplashConfirmDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        newSplashConfirmDialogFragment.mTvCancel = null;
        newSplashConfirmDialogFragment.mTvConfirm = null;
        newSplashConfirmDialogFragment.mRadioButton = null;
        newSplashConfirmDialogFragment.mTvTitle = null;
        newSplashConfirmDialogFragment.mTvContext = null;
        this.view7f0a07f9.setOnClickListener(null);
        this.view7f0a07f9 = null;
        this.view7f0a080e.setOnClickListener(null);
        this.view7f0a080e = null;
    }
}