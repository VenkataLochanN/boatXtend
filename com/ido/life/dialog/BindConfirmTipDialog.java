package com.ido.life.dialog;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import androidx.appcompat.widget.AppCompatImageView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.common.widget.textview.RegularTextView;

/* JADX INFO: loaded from: classes2.dex */
public class BindConfirmTipDialog extends BaseDialogFragment {
    private static final String DEVICE_MAC = "device_mac";
    private static final String DEVICE_SHAPE = "device_shape";
    private static final String MAC_FORMAT = "Mac: ";

    @BindView(R.id.iv_device)
    AppCompatImageView mIvDevice;

    @BindView(R.id.rtv_mac)
    RegularTextView mTvMac;

    @BindView(R.id.mtv_bind_tip)
    MediumTextView mTvTip;

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_bind_confirm_tip;
    }

    public static BindConfirmTipDialog getInstance(int i, String str) {
        Bundle bundle = new Bundle();
        bundle.putInt(DEVICE_SHAPE, i);
        bundle.putString(DEVICE_MAC, str);
        BindConfirmTipDialog bindConfirmTipDialog = new BindConfirmTipDialog();
        bindConfirmTipDialog.setArguments(bundle);
        bindConfirmTipDialog.setStyle(1, 2131886083);
        bindConfirmTipDialog.setCancelable(false);
        return bindConfirmTipDialog;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mTvMac.setText(formatMac(arguments.getString(DEVICE_MAC)));
            this.mIvDevice.setImageResource(arguments.getInt(DEVICE_SHAPE) == 1 ? R.mipmap.icon_watch_circle : R.mipmap.icon_watch_rectangle);
        }
        initTip();
    }

    private String formatMac(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String strReplaceAll = str.replaceAll(":", "").replaceAll("：", "");
        if (strReplaceAll.length() > 4) {
            strReplaceAll = strReplaceAll.substring(strReplaceAll.length() - 4);
        }
        return MAC_FORMAT.concat(strReplaceAll);
    }

    private void initTip() {
        String string = getString(R.string.device_bind_confirm_tip);
        int iIndexOf = string.contains("[img]") ? string.indexOf("[img]") : -1;
        if (iIndexOf >= 0) {
            SpannableString spannableString = new SpannableString(string);
            Drawable drawable = ResourceUtil.getDrawable(R.mipmap.icon_bind_confirm);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            spannableString.setSpan(new ImageSpan(drawable), iIndexOf, iIndexOf + 5, 33);
            this.mTvTip.setText(spannableString);
            return;
        }
        this.mTvTip.setText(string);
    }
}