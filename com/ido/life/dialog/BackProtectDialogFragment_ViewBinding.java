package com.ido.life.dialog;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class BackProtectDialogFragment_ViewBinding implements Unbinder {
    private BackProtectDialogFragment target;
    private View view7f0a07f9;
    private View view7f0a092e;
    private View view7f0a0930;
    private View view7f0a0931;

    public BackProtectDialogFragment_ViewBinding(final BackProtectDialogFragment backProtectDialogFragment, View view) {
        this.target = backProtectDialogFragment;
        backProtectDialogFragment.mLlBackLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_back_layout, "field 'mLlBackLayout'", LinearLayout.class);
        backProtectDialogFragment.mTvTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title, "field 'mTvTitle'", TextView.class);
        backProtectDialogFragment.mTvContent = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_content, "field 'mTvContent'", TextView.class);
        backProtectDialogFragment.mLlLaunch = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_launch, "field 'mLlLaunch'", LinearLayout.class);
        backProtectDialogFragment.mTvLaunchTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_launch_title, "field 'mTvLaunchTitle'", TextView.class);
        backProtectDialogFragment.mLlAssociated = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_associated, "field 'mLlAssociated'", LinearLayout.class);
        backProtectDialogFragment.mTvAssociatedTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_associated_title, "field 'mTvAssociatedTitle'", TextView.class);
        backProtectDialogFragment.mLlPower = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_power, "field 'mLlPower'", LinearLayout.class);
        backProtectDialogFragment.mTvTitlePower = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title_power, "field 'mTvTitlePower'", TextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.tv_cancel, "method 'toCancel'");
        this.view7f0a07f9 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.BackProtectDialogFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                backProtectDialogFragment.toCancel(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.tv_set_power, "method 'toPower'");
        this.view7f0a0931 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.BackProtectDialogFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                backProtectDialogFragment.toPower(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.tv_set_associated, "method 'toAssociate'");
        this.view7f0a092e = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.BackProtectDialogFragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                backProtectDialogFragment.toAssociate(view2);
            }
        });
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.tv_set_launch, "method 'toSetStart'");
        this.view7f0a0930 = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.BackProtectDialogFragment_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                backProtectDialogFragment.toSetStart(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        BackProtectDialogFragment backProtectDialogFragment = this.target;
        if (backProtectDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        backProtectDialogFragment.mLlBackLayout = null;
        backProtectDialogFragment.mTvTitle = null;
        backProtectDialogFragment.mTvContent = null;
        backProtectDialogFragment.mLlLaunch = null;
        backProtectDialogFragment.mTvLaunchTitle = null;
        backProtectDialogFragment.mLlAssociated = null;
        backProtectDialogFragment.mTvAssociatedTitle = null;
        backProtectDialogFragment.mLlPower = null;
        backProtectDialogFragment.mTvTitlePower = null;
        this.view7f0a07f9.setOnClickListener(null);
        this.view7f0a07f9 = null;
        this.view7f0a0931.setOnClickListener(null);
        this.view7f0a0931 = null;
        this.view7f0a092e.setOnClickListener(null);
        this.view7f0a092e = null;
        this.view7f0a0930.setOnClickListener(null);
        this.view7f0a0930 = null;
    }
}