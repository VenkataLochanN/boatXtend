package com.ido.smartrefresh.layout.header;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.ido.smartrefresh.layout.api.RefreshHeader;
import com.ido.smartrefresh.layoutkernel.constant.SpinnerStyle;
import java.text.DateFormat;
import java.util.Date;

/* JADX INFO: loaded from: classes3.dex */
public class ClassicsHeader extends com.ido.smartrefresh.headerclassics.ClassicsHeader implements RefreshHeader {
    public ClassicsHeader(Context context) {
        super(context);
    }

    public ClassicsHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.ido.smartrefresh.headerclassics.ClassicsHeader
    public ClassicsHeader setLastUpdateTime(Date date) {
        super.setLastUpdateTime(date);
        return this;
    }

    @Override // com.ido.smartrefresh.headerclassics.ClassicsHeader
    public ClassicsHeader setTimeFormat(DateFormat dateFormat) {
        super.setTimeFormat(dateFormat);
        return this;
    }

    @Override // com.ido.smartrefresh.headerclassics.ClassicsHeader
    public ClassicsHeader setLastUpdateText(CharSequence charSequence) {
        super.setLastUpdateText(charSequence);
        return this;
    }

    @Override // com.ido.smartrefresh.headerclassics.ClassicsHeader, com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsHeader setAccentColor(int i) {
        super.setAccentColor(i);
        return this;
    }

    @Override // com.ido.smartrefresh.headerclassics.ClassicsHeader
    public ClassicsHeader setEnableLastTime(boolean z) {
        super.setEnableLastTime(z);
        return this;
    }

    @Override // com.ido.smartrefresh.headerclassics.ClassicsHeader
    public ClassicsHeader setTextSizeTime(float f2) {
        super.setTextSizeTime(f2);
        return this;
    }

    @Override // com.ido.smartrefresh.headerclassics.ClassicsHeader
    public ClassicsHeader setTextSizeTime(int i, float f2) {
        super.setTextSizeTime(i, f2);
        return this;
    }

    @Override // com.ido.smartrefresh.headerclassics.ClassicsHeader
    public ClassicsHeader setTextTimeMarginTop(float f2) {
        super.setTextTimeMarginTop(f2);
        return this;
    }

    @Override // com.ido.smartrefresh.headerclassics.ClassicsHeader
    public ClassicsHeader setTextTimeMarginTopPx(int i) {
        super.setTextTimeMarginTopPx(i);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract, com.ido.smartrefresh.layoutkernel.simple.SimpleComponent, com.ido.smartrefresh.layoutkernel.api.RefreshComponent
    public void setPrimaryColors(int... iArr) {
        super.setPrimaryColors(iArr);
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsHeader setProgressBitmap(Bitmap bitmap) {
        super.setProgressBitmap(bitmap);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsHeader setProgressDrawable(Drawable drawable) {
        super.setProgressDrawable(drawable);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsHeader setProgressResource(int i) {
        super.setProgressResource(i);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsHeader setArrowBitmap(Bitmap bitmap) {
        super.setArrowBitmap(bitmap);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsHeader setArrowDrawable(Drawable drawable) {
        super.setArrowDrawable(drawable);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsHeader setArrowResource(int i) {
        super.setArrowResource(i);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsHeader setSpinnerStyle(SpinnerStyle spinnerStyle) {
        super.setSpinnerStyle(spinnerStyle);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsHeader setPrimaryColor(int i) {
        super.setPrimaryColor(i);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsHeader setPrimaryColorId(int i) {
        super.setPrimaryColorId(i);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsHeader setAccentColorId(int i) {
        super.setAccentColorId(i);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsHeader setFinishDuration(int i) {
        super.setFinishDuration(i);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsHeader setTextSizeTitle(float f2) {
        super.setTextSizeTitle(f2);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsHeader setTextSizeTitle(int i, float f2) {
        super.setTextSizeTitle(i, f2);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsHeader setDrawableMarginRight(float f2) {
        super.setDrawableMarginRight(f2);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsHeader setDrawableMarginRightPx(int i) {
        super.setDrawableMarginRightPx(i);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsHeader setDrawableSize(float f2) {
        super.setDrawableSize(f2);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsHeader setDrawableSizePx(int i) {
        super.setDrawableSizePx(i);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsHeader setDrawableArrowSize(float f2) {
        super.setDrawableArrowSize(f2);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsHeader setDrawableArrowSizePx(int i) {
        super.setDrawableArrowSizePx(i);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsHeader setDrawableProgressSize(float f2) {
        super.setDrawableProgressSize(f2);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsHeader setDrawableProgressSizePx(int i) {
        super.setDrawableProgressSizePx(i);
        return this;
    }
}