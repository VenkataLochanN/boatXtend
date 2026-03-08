package com.ido.life.dialog;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class CloudSyncConfirmDialogFragment_ViewBinding implements Unbinder {
    private CloudSyncConfirmDialogFragment target;
    private View view7f0a07f9;
    private View view7f0a080e;

    public CloudSyncConfirmDialogFragment_ViewBinding(final CloudSyncConfirmDialogFragment cloudSyncConfirmDialogFragment, View view) {
        this.target = cloudSyncConfirmDialogFragment;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.tv_cancel, "field 'mTvCancel' and method 'doClickCancel'");
        cloudSyncConfirmDialogFragment.mTvCancel = (TextView) Utils.castView(viewFindRequiredView, R.id.tv_cancel, "field 'mTvCancel'", TextView.class);
        this.view7f0a07f9 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.CloudSyncConfirmDialogFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                cloudSyncConfirmDialogFragment.doClickCancel(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.tv_confirm, "field 'mTvConfirm' and method 'doClickConfirm'");
        cloudSyncConfirmDialogFragment.mTvConfirm = (TextView) Utils.castView(viewFindRequiredView2, R.id.tv_confirm, "field 'mTvConfirm'", TextView.class);
        this.view7f0a080e = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.CloudSyncConfirmDialogFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                cloudSyncConfirmDialogFragment.doClickConfirm(view2);
            }
        });
        cloudSyncConfirmDialogFragment.mTvTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title, "field 'mTvTitle'", TextView.class);
        cloudSyncConfirmDialogFragment.tvContext = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_context, "field 'tvContext'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        CloudSyncConfirmDialogFragment cloudSyncConfirmDialogFragment = this.target;
        if (cloudSyncConfirmDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        cloudSyncConfirmDialogFragment.mTvCancel = null;
        cloudSyncConfirmDialogFragment.mTvConfirm = null;
        cloudSyncConfirmDialogFragment.mTvTitle = null;
        cloudSyncConfirmDialogFragment.tvContext = null;
        this.view7f0a07f9.setOnClickListener(null);
        this.view7f0a07f9 = null;
        this.view7f0a080e.setOnClickListener(null);
        this.view7f0a080e = null;
    }
}