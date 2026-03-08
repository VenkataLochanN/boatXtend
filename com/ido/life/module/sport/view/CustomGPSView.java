package com.ido.life.module.sport.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.ido.life.R;
import com.ido.life.util.ViewUtil;

/* JADX INFO: loaded from: classes2.dex */
public class CustomGPSView extends View {
    private static final String TAG = "CustomGPSView";
    private float baseLine;
    private float bottom;
    private float commonH;
    private String gps;
    private Paint gpsPaint;
    private int gpsSignalBgColor;
    private int gpsSignalCommonColor;
    private float gpsSignalMaxHeight;
    private int gpsSignalStrongColor;
    private int gpsSignalWeakColor;
    private String gpsSingalText;
    private float gpsSingalWidth;
    private int gpsTextColor;
    private float gpsTextSize;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f4693h;
    private float left;
    private float right;
    private int signalValue;
    private float strongH;
    private float strongHMax;
    private float top;
    private int w;
    private float weakH;

    public CustomGPSView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.gpsSignalMaxHeight = 28.0f;
        this.weakH = 20.0f;
        this.commonH = 24.0f;
        this.strongH = 28.0f;
        this.strongHMax = 32.0f;
        this.signalValue = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CustomGPSView);
        this.gps = typedArrayObtainStyledAttributes.getString(9);
        this.gpsTextSize = typedArrayObtainStyledAttributes.getDimension(10, 16.0f);
        this.gpsTextColor = typedArrayObtainStyledAttributes.getColor(11, 0);
        this.gpsSignalWeakColor = typedArrayObtainStyledAttributes.getColor(8, 0);
        this.gpsSignalCommonColor = typedArrayObtainStyledAttributes.getColor(6, 0);
        this.gpsSignalStrongColor = typedArrayObtainStyledAttributes.getColor(7, 0);
        this.gpsSignalBgColor = typedArrayObtainStyledAttributes.getColor(1, 0);
        this.gpsSingalWidth = typedArrayObtainStyledAttributes.getDimension(5, 4.0f);
        this.gpsSingalText = typedArrayObtainStyledAttributes.getString(4);
        typedArrayObtainStyledAttributes.recycle();
        this.gpsPaint = new Paint();
        this.gpsPaint.setAntiAlias(true);
        this.gpsPaint.setTextSize(this.gpsTextSize);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.w = i;
        this.f4693h = i2;
        this.baseLine = i2 - calculationSurplusHeight(this.gpsSignalMaxHeight);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.gpsPaint.setTextAlign(Paint.Align.CENTER);
        this.gpsPaint.setColor(this.gpsTextColor);
        canvas.drawText(this.gps, this.w / 3, this.f4693h - calculationSurplusHeight(ViewUtil.getTextRectHeight(this.gpsPaint, this.gps)), this.gpsPaint);
        int i = this.w;
        this.gpsPaint.setTextAlign(Paint.Align.RIGHT);
        int i2 = this.signalValue;
        if (i2 == 0) {
            int i3 = this.gpsSignalWeakColor;
            int i4 = this.gpsSignalBgColor;
            drawGPSSignalStrength(canvas, i3, i4, i4);
        } else if (i2 == 2) {
            int i5 = this.gpsSignalCommonColor;
            drawGPSSignalStrength(canvas, i5, i5, this.gpsSignalBgColor);
        } else if (i2 == 1) {
            int i6 = this.gpsSignalStrongColor;
            drawGPSSignalStrength(canvas, i6, i6, i6);
        }
    }

    private void drawGPSSignalStrength(Canvas canvas, int i, int i2, int i3) {
        float f2 = (this.w * 2) / 3;
        this.gpsPaint.setTextAlign(Paint.Align.CENTER);
        this.gpsPaint.setColor(i2);
        float f3 = this.gpsSingalWidth;
        this.left = f2 - (f3 / 2.0f);
        float f4 = this.baseLine;
        this.top = f4 - this.commonH;
        this.right = (f3 / 2.0f) + f2;
        this.bottom = f4;
        canvas.drawRoundRect(this.left, this.top, this.right, this.bottom, 3.0f, 3.0f, this.gpsPaint);
        this.gpsPaint.setTextAlign(Paint.Align.RIGHT);
        this.gpsPaint.setColor(i);
        float f5 = this.gpsSingalWidth;
        this.left = (f2 - (f5 / 2.0f)) - (f5 * 2.0f);
        float f6 = this.baseLine;
        this.top = f6 - this.weakH;
        this.right = (f2 - (f5 / 2.0f)) - f5;
        this.bottom = f6;
        canvas.drawRoundRect(this.left, this.top, this.right, this.bottom, 3.0f, 3.0f, this.gpsPaint);
        this.gpsPaint.setTextAlign(Paint.Align.LEFT);
        this.gpsPaint.setColor(i3);
        float f7 = this.gpsSingalWidth;
        this.left = (f7 / 2.0f) + f2 + f7;
        float f8 = this.baseLine;
        this.top = f8 - this.strongH;
        this.right = (f7 / 2.0f) + f2 + (f7 * 2.0f);
        this.bottom = f8;
        canvas.drawRoundRect(this.left, this.top, this.right, this.bottom, 3.0f, 3.0f, this.gpsPaint);
        this.gpsPaint.setTextAlign(Paint.Align.LEFT);
        this.gpsPaint.setColor(i3);
        float f9 = this.gpsSingalWidth;
        this.left = ((f9 * 3.0f) / 2.0f) + f2 + (f9 * 2.0f);
        float f10 = this.baseLine;
        this.top = f10 - this.strongHMax;
        this.right = f2 + ((f9 * 3.0f) / 2.0f) + (f9 * 3.0f);
        this.bottom = f10;
        canvas.drawRoundRect(this.left, this.top, this.right, this.bottom, 3.0f, 3.0f, this.gpsPaint);
    }

    private float calculationSurplusHeight(float f2) {
        return (this.f4693h - f2) / 2.0f;
    }

    public void setGPSSignalStrength(int i) {
        this.signalValue = i;
        invalidate();
    }

    private Bitmap drawable2Bitmap(Drawable drawable) {
        return ((BitmapDrawable) drawable).getBitmap();
    }
}