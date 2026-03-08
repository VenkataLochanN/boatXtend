package com.ido.life.dialog;

import android.os.Bundle;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.widget.textview.RegularTextView;

/* JADX INFO: loaded from: classes2.dex */
public class CommLoadingDialog extends BaseDialogFragment {
    private static final String FAILED_TEXT = "FAILED_TEXT";
    private static final String LOADING_TEXT = "LOADING_TEXT";
    private static final String SUCCESS_TEXT = "SUCCESS_TEXT";
    private String mFailedText;

    @BindView(R.id.iv_loading)
    ImageView mIvLoading;
    private String mSuccessText;

    @BindView(R.id.tv_title)
    RegularTextView mTvTitle;

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_comm_loading;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getWindowAnimations() {
        return R.style.DialogAnimSlideInBottom;
    }

    public static CommLoadingDialog getInstance(String str, String str2, String str3) {
        return getInstance(str, str2, str3, false);
    }

    public static CommLoadingDialog getInstance(String str, String str2, String str3, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString(LOADING_TEXT, str);
        bundle.putString(SUCCESS_TEXT, str2);
        bundle.putString(FAILED_TEXT, str3);
        CommLoadingDialog commLoadingDialog = new CommLoadingDialog();
        commLoadingDialog.setArguments(bundle);
        commLoadingDialog.setStyle(1, 2131886083);
        commLoadingDialog.setCancelable(z);
        return commLoadingDialog;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mTvTitle.setText(arguments.getString(LOADING_TEXT));
            this.mSuccessText = arguments.getString(SUCCESS_TEXT);
            this.mFailedText = arguments.getString(FAILED_TEXT);
        }
        startAnimation();
    }

    public void startAnimation() {
        this.mIvLoading.clearAnimation();
        this.mIvLoading.setImageResource(R.mipmap.icon_loading);
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 1800.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(4000L);
        rotateAnimation.setRepeatCount(-1);
        this.mIvLoading.startAnimation(rotateAnimation);
    }

    public void setComplete(boolean z) {
        ImageView imageView = this.mIvLoading;
        if (imageView != null) {
            imageView.clearAnimation();
            this.mIvLoading.setImageResource(z ? R.mipmap.icon_loading_success : R.mipmap.icon_loading_failed);
        }
        RegularTextView regularTextView = this.mTvTitle;
        if (regularTextView != null) {
            regularTextView.setText(z ? this.mSuccessText : this.mFailedText);
            this.mTvTitle.postDelayed(new $$Lambda$B0LyGxQeeZul5njRnGGBDKr4eH0(this), 1000L);
        }
    }

    public void setComplete(boolean z, String str) {
        ImageView imageView = this.mIvLoading;
        if (imageView != null) {
            imageView.clearAnimation();
            this.mIvLoading.setImageResource(z ? R.mipmap.icon_loading_success : R.mipmap.icon_loading_failed);
        }
        RegularTextView regularTextView = this.mTvTitle;
        if (regularTextView != null) {
            regularTextView.setText(str);
            this.mTvTitle.postDelayed(new $$Lambda$B0LyGxQeeZul5njRnGGBDKr4eH0(this), 1000L);
        }
    }
}