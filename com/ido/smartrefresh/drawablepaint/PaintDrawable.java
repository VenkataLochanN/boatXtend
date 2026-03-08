package com.ido.smartrefresh.drawablepaint;

import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes3.dex */
public abstract class PaintDrawable extends Drawable {
    protected Paint mPaint = new Paint();

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    protected PaintDrawable() {
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(-5592406);
    }

    public void setColor(int i) {
        this.mPaint.setColor(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.mPaint.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }
}