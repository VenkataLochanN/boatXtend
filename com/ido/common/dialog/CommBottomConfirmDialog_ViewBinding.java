package com.ido.common.dialog;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ido.common.R;

/* JADX INFO: loaded from: classes2.dex */
public class CommBottomConfirmDialog_ViewBinding implements Unbinder {
    private CommBottomConfirmDialog target;
    private View view7f0b0182;
    private View view7f0b0183;

    public CommBottomConfirmDialog_ViewBinding(final CommBottomConfirmDialog commBottomConfirmDialog, View view) {
        this.target = commBottomConfirmDialog;
        commBottomConfirmDialog.mRtvTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.rtv_title, "field 'mRtvTitle'", TextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.tv_cancel, "field 'mTvCancel' and method 'doClickCancel'");
        commBottomConfirmDialog.mTvCancel = (TextView) Utils.castView(viewFindRequiredView, R.id.tv_cancel, "field 'mTvCancel'", TextView.class);
        this.view7f0b0182 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.common.dialog.CommBottomConfirmDialog_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                commBottomConfirmDialog.doClickCancel(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.tv_confirm, "field 'mTvConfirm' and method 'doClickConfirm'");
        commBottomConfirmDialog.mTvConfirm = (TextView) Utils.castView(viewFindRequiredView2, R.id.tv_confirm, "field 'mTvConfirm'", TextView.class);
        this.view7f0b0183 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.common.dialog.CommBottomConfirmDialog_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                commBottomConfirmDialog.doClickConfirm(view2);
            }
        });
        commBottomConfirmDialog.mTvMessage = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_message, "field 'mTvMessage'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        CommBottomConfirmDialog commBottomConfirmDialog = this.target;
        if (commBottomConfirmDialog == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        commBottomConfirmDialog.mRtvTitle = null;
        commBottomConfirmDialog.mTvCancel = null;
        commBottomConfirmDialog.mTvConfirm = null;
        commBottomConfirmDialog.mTvMessage = null;
        this.view7f0b0182.setOnClickListener(null);
        this.view7f0b0182 = null;
        this.view7f0b0183.setOnClickListener(null);
        this.view7f0b0183 = null;
    }
}