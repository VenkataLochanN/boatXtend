package com.ido.common.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import com.ido.common.utils.DipPixelUtil;

/* JADX INFO: loaded from: classes2.dex */
public class RotateTextView extends AppCompatTextView {
    private int bitmapWidth;
    private int direction;
    private Bitmap leftBitmap;

    public RotateTextView(Context context) {
        super(context);
        this.direction = 3;
    }

    public RotateTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.direction = 3;
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    protected void onMeasure(int i, int i2) {
        int i3 = this.direction;
        if (i3 == 0 || i3 == 2) {
            super.onMeasure(i2, i);
            setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth() + this.bitmapWidth);
        } else {
            super.onMeasure(i, i2);
            setMeasuredDimension(getMeasuredWidth() + this.bitmapWidth, getMeasuredHeight());
        }
    }

    public int getDirection() {
        return this.direction;
    }

    public void setDirection(int i) {
        this.direction = i;
        postInvalidate();
    }

    public void setLeftCompoundDrawables(int i) {
        this.leftBitmap = ((BitmapDrawable) getResources().getDrawable(i)).getBitmap();
        this.bitmapWidth = this.leftBitmap.getWidth() + DipPixelUtil.dip2px(6.0f);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        int width;
        int topPadding;
        TextPaint paint = getPaint();
        paint.setColor(getCurrentTextColor());
        paint.drawableState = getDrawableState();
        canvas.save();
        int i = this.direction;
        if (i == 0) {
            canvas.translate(0.0f, getHeight());
            canvas.rotate(-90.0f);
        } else if (i == 1) {
            canvas.translate(getWidth(), getHeight());
            canvas.rotate(180.0f);
        } else if (i == 2) {
            canvas.translate(getWidth(), 0.0f);
            canvas.rotate(90.0f);
        }
        canvas.translate(getCompoundPaddingLeft(), getExtendedPaddingTop());
        if (this.leftBitmap != null) {
            int i2 = this.direction;
            if (i2 == 0 || i2 == 2) {
                width = (getWidth() - (getExtendedPaddingTop() * 2)) - this.leftBitmap.getHeight();
                topPadding = getLayout().getTopPadding();
            } else {
                width = (getHeight() - (getExtendedPaddingTop() * 2)) - this.leftBitmap.getHeight();
                topPadding = getLayout().getTopPadding();
            }
            canvas.drawBitmap(this.leftBitmap, 0.0f, width + topPadding, paint);
            canvas.translate(this.bitmapWidth, 0.0f);
        }
        getLayout().draw(canvas);
        canvas.restore();
    }
}