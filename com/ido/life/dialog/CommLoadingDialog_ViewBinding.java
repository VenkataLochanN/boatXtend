package com.ido.life.dialog;

import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;

/* JADX INFO: loaded from: classes2.dex */
public class CommLoadingDialog_ViewBinding implements Unbinder {
    private CommLoadingDialog target;

    public CommLoadingDialog_ViewBinding(CommLoadingDialog commLoadingDialog, View view) {
        this.target = commLoadingDialog;
        commLoadingDialog.mIvLoading = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_loading, "field 'mIvLoading'", ImageView.class);
        commLoadingDialog.mTvTitle = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.tv_title, "field 'mTvTitle'", RegularTextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        CommLoadingDialog commLoadingDialog = this.target;
        if (commLoadingDialog == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        commLoadingDialog.mIvLoading = null;
        commLoadingDialog.mTvTitle = null;
    }
}