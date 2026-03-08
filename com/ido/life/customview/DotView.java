package com.ido.life.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import com.ido.life.R;

/* JADX INFO: loaded from: classes2.dex */
public class DotView extends View {
    private int mCheckedColor;
    private int mCheckedIndex;
    private float mCheckedScale;
    private float mDefaultDotRadius;
    private int mDotCount;
    private int mDotDefaultColor;
    private float mDotMargin;
    private int mDrawInterval;
    private Handler mHandler;
    private int mHeight;
    private Paint mPaint;
    private int mWidth;

    static /* synthetic */ int access$008(DotView dotView) {
        int i = dotView.mCheckedIndex;
        dotView.mCheckedIndex = i + 1;
        return i;
    }

    public DotView(Context context) {
        this(context, null);
    }

    public DotView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DotView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        initAttrs(context, attributeSet);
        initPaint();
    }

    private void initAttrs(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.DotView);
        this.mDotCount = typedArrayObtainStyledAttributes.getInt(4, 3);
        this.mCheckedIndex = typedArrayObtainStyledAttributes.getInt(1, 1);
        this.mDotDefaultColor = typedArrayObtainStyledAttributes.getColor(3, context.getColor(com.boat.Xtend.two.R.color.color_C4C6CD));
        this.mCheckedColor = typedArrayObtainStyledAttributes.getColor(0, context.getColor(com.boat.Xtend.two.R.color.color_E51C23));
        this.mCheckedScale = typedArrayObtainStyledAttributes.getFloat(2, 1.0f);
        this.mDotMargin = typedArrayObtainStyledAttributes.getDimension(5, context.getResources().getDimension(com.boat.Xtend.two.R.dimen.sw_dp_2));
        this.mDrawInterval = typedArrayObtainStyledAttributes.getInt(6, 600);
        typedArrayObtainStyledAttributes.recycle();
    }

    private void initPaint() {
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        this.mPaint.setColor(this.mDotDefaultColor);
    }

    public int getDotCount() {
        return this.mDotCount;
    }

    public void setDotCount(int i) {
        this.mDotCount = i;
    }

    public int getCheckedIndex() {
        return this.mCheckedIndex;
    }

    public void setCheckedIndex(int i, boolean z) {
        this.mCheckedIndex = i;
        if (z) {
            invalidate();
        }
    }

    public int getDotDefaultColor() {
        return this.mDotDefaultColor;
    }

    public void setDotDefaultColor(int i) {
        this.mDotDefaultColor = i;
    }

    public int getCheckedColor() {
        return this.mCheckedColor;
    }

    public void setCheckedColor(int i) {
        this.mCheckedColor = i;
    }

    public float getCheckedScale() {
        return this.mCheckedScale;
    }

    public void setCheckedScale(float f2) {
        this.mCheckedScale = f2;
    }

    public float getDotMargin() {
        return this.mDotMargin;
    }

    public void setDotMargin(float f2) {
        this.mDotMargin = f2;
    }

    public int getDrawInterval() {
        return this.mDrawInterval;
    }

    public void setDrawInterval(int i) {
        this.mDrawInterval = i;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mWidth = getMeasuredWidth();
        this.mHeight = getMeasuredHeight();
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float f2;
        float f3;
        super.onDraw(canvas);
        int i = this.mDotCount;
        if (i <= 0) {
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            return;
        }
        int i2 = this.mCheckedIndex;
        if (i2 >= 0 && i2 < i) {
            float f4 = this.mWidth - (this.mDotMargin * (i - 1));
            float f5 = this.mCheckedScale;
            this.mDefaultDotRadius = (f4 / ((i - 1) + f5)) / 2.0f;
            int i3 = this.mHeight;
            if (i3 < this.mDefaultDotRadius * i2 * 2.0f) {
                this.mDefaultDotRadius = (i3 / 2.0f) / f5;
            }
        } else {
            float f6 = this.mWidth;
            float f7 = this.mDotMargin;
            this.mDefaultDotRadius = ((f6 - (f7 * (r4 - 1))) / this.mDotCount) / 2.0f;
            int i4 = this.mHeight;
            if (i4 < this.mDefaultDotRadius * 2.0f) {
                this.mDefaultDotRadius = i4 / 2.0f;
            }
        }
        for (int i5 = 0; i5 < this.mDotCount; i5++) {
            if (i5 == this.mCheckedIndex) {
                this.mPaint.setColor(this.mCheckedColor);
                f2 = this.mDefaultDotRadius * this.mCheckedScale;
            } else {
                this.mPaint.setColor(this.mDotDefaultColor);
                f2 = this.mDefaultDotRadius;
            }
            if (this.mCheckedIndex < i5) {
                float f8 = this.mDefaultDotRadius;
                float f9 = this.mCheckedScale * f8 * 2.0f;
                float f10 = this.mDotMargin;
                f3 = f9 + f10 + (((f8 * 2.0f) + f10) * (i5 - 1)) + f2;
            } else {
                f3 = (((this.mDefaultDotRadius * 2.0f) + this.mDotMargin) * i5) + f2;
            }
            canvas.drawCircle(f3, this.mHeight / 2.0f, f2, this.mPaint);
        }
    }

    public void startAnimation(int i) {
        this.mDrawInterval = i;
        startAnimation();
    }

    public void startAnimation() {
        if (this.mHandler == null) {
            this.mHandler = new Handler();
        }
        this.mHandler.removeCallbacksAndMessages(null);
        this.mHandler.post(new Runnable() { // from class: com.ido.life.customview.DotView.1
            @Override // java.lang.Runnable
            public void run() {
                DotView.this.invalidate();
                DotView.access$008(DotView.this);
                if (DotView.this.mCheckedIndex < 0 || DotView.this.mCheckedIndex >= DotView.this.mDotCount) {
                    DotView.this.mCheckedIndex = 0;
                }
                DotView.this.mHandler.postDelayed(this, DotView.this.mDrawInterval);
            }
        });
    }

    public void stopAnimation() {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }
}