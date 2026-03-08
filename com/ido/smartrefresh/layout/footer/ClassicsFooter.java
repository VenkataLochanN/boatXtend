package com.ido.smartrefresh.layout.footer;

import android.content.Context;
import android.util.AttributeSet;
import com.ido.smartrefresh.layout.api.RefreshFooter;
import com.ido.smartrefresh.layoutkernel.constant.SpinnerStyle;

/* JADX INFO: loaded from: classes3.dex */
public class ClassicsFooter extends com.ido.smartrefresh.footerclassics.footer.ClassicsFooter implements RefreshFooter {
    public ClassicsFooter(Context context) {
        super(context);
    }

    public ClassicsFooter(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsFooter setSpinnerStyle(SpinnerStyle spinnerStyle) {
        super.setSpinnerStyle(spinnerStyle);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsFooter setPrimaryColor(int i) {
        super.setPrimaryColor(i);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsFooter setAccentColor(int i) {
        super.setAccentColor(i);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsFooter setPrimaryColorId(int i) {
        super.setPrimaryColorId(i);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsFooter setAccentColorId(int i) {
        super.setAccentColorId(i);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsFooter setFinishDuration(int i) {
        super.setFinishDuration(i);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsFooter setTextSizeTitle(float f2) {
        super.setTextSizeTitle(f2);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsFooter setTextSizeTitle(int i, float f2) {
        super.setTextSizeTitle(i, f2);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsFooter setDrawableMarginRight(float f2) {
        super.setDrawableMarginRight(f2);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsFooter setDrawableMarginRightPx(int i) {
        super.setDrawableMarginRightPx(i);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsFooter setDrawableSize(float f2) {
        super.setDrawableSize(f2);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsFooter setDrawableSizePx(int i) {
        super.setDrawableSizePx(i);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsFooter setDrawableArrowSize(float f2) {
        super.setDrawableArrowSize(f2);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsFooter setDrawableArrowSizePx(int i) {
        super.setDrawableArrowSizePx(i);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsFooter setDrawableProgressSize(float f2) {
        super.setDrawableProgressSize(f2);
        return this;
    }

    @Override // com.ido.smartrefresh.footerclassics.classics.ClassicsAbstract
    public ClassicsFooter setDrawableProgressSizePx(int i) {
        super.setDrawableProgressSizePx(i);
        return this;
    }
}