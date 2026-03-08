package com.ido.life.dialog;

import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.common.widget.textview.RegularTextView;

/* JADX INFO: loaded from: classes2.dex */
public class BindConfirmTipDialog_ViewBinding implements Unbinder {
    private BindConfirmTipDialog target;

    public BindConfirmTipDialog_ViewBinding(BindConfirmTipDialog bindConfirmTipDialog, View view) {
        this.target = bindConfirmTipDialog;
        bindConfirmTipDialog.mTvTip = (MediumTextView) Utils.findRequiredViewAsType(view, R.id.mtv_bind_tip, "field 'mTvTip'", MediumTextView.class);
        bindConfirmTipDialog.mIvDevice = (AppCompatImageView) Utils.findRequiredViewAsType(view, R.id.iv_device, "field 'mIvDevice'", AppCompatImageView.class);
        bindConfirmTipDialog.mTvMac = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_mac, "field 'mTvMac'", RegularTextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        BindConfirmTipDialog bindConfirmTipDialog = this.target;
        if (bindConfirmTipDialog == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        bindConfirmTipDialog.mTvTip = null;
        bindConfirmTipDialog.mIvDevice = null;
        bindConfirmTipDialog.mTvMac = null;
    }
}