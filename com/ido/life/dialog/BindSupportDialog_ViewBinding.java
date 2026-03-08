package com.ido.life.dialog;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class BindSupportDialog_ViewBinding implements Unbinder {
    private BindSupportDialog target;
    private View view7f0a0880;
    private View view7f0a09d1;

    public BindSupportDialog_ViewBinding(final BindSupportDialog bindSupportDialog, View view) {
        this.target = bindSupportDialog;
        bindSupportDialog.mRtvTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.rtv_title, "field 'mRtvTitle'", TextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.tv_later_on, "field 'mTvCancel' and method 'doClickConfirm'");
        bindSupportDialog.mTvCancel = (TextView) Utils.castView(viewFindRequiredView, R.id.tv_later_on, "field 'mTvCancel'", TextView.class);
        this.view7f0a0880 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.BindSupportDialog_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                bindSupportDialog.doClickConfirm(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.tv_to_download, "field 'mTvConfirm' and method 'doClickCancel'");
        bindSupportDialog.mTvConfirm = (TextView) Utils.castView(viewFindRequiredView2, R.id.tv_to_download, "field 'mTvConfirm'", TextView.class);
        this.view7f0a09d1 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.BindSupportDialog_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                bindSupportDialog.doClickCancel(view2);
            }
        });
        bindSupportDialog.mTvMessage = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_message, "field 'mTvMessage'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        BindSupportDialog bindSupportDialog = this.target;
        if (bindSupportDialog == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        bindSupportDialog.mRtvTitle = null;
        bindSupportDialog.mTvCancel = null;
        bindSupportDialog.mTvConfirm = null;
        bindSupportDialog.mTvMessage = null;
        this.view7f0a0880.setOnClickListener(null);
        this.view7f0a0880 = null;
        this.view7f0a09d1.setOnClickListener(null);
        this.view7f0a09d1 = null;
    }
}