package com.ido.life.customview.clip;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: classes2.dex */
public class ClipImageLayout extends RelativeLayout {
    private ClipImageBorderView mClipImageView;
    private int mHorizontalPadding;
    public ClipZoomImageView mZoomImageView;

    public ClipImageLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mHorizontalPadding = 30;
        this.mZoomImageView = new ClipZoomImageView(context);
        this.mClipImageView = new ClipImageBorderView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        addView(this.mZoomImageView, layoutParams);
        addView(this.mClipImageView, layoutParams);
        this.mHorizontalPadding = (int) TypedValue.applyDimension(1, this.mHorizontalPadding, getResources().getDisplayMetrics());
        this.mZoomImageView.setHorizontalPadding(this.mHorizontalPadding);
        this.mClipImageView.setHorizontalPadding(this.mHorizontalPadding);
    }

    public void setAspectRatio(int i, int i2) {
        this.mZoomImageView.setHorizontalPadding(this.mHorizontalPadding, i, i2);
        this.mClipImageView.setHorizontalPadding(this.mHorizontalPadding, i, i2);
    }

    public void setHorizontalPadding(int i) {
        this.mHorizontalPadding = i;
    }

    public Bitmap clip() {
        return this.mZoomImageView.clip();
    }
}