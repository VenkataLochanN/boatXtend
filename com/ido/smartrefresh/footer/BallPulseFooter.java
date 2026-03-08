package com.ido.smartrefresh.footer;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import androidx.core.graphics.ColorUtils;
import com.ido.smartrefresh.R;
import com.ido.smartrefresh.layout.api.RefreshFooter;
import com.ido.smartrefresh.layoutkernel.api.RefreshLayout;
import com.ido.smartrefresh.layoutkernel.constant.SpinnerStyle;
import com.ido.smartrefresh.layoutkernel.simple.SimpleComponent;
import com.ido.smartrefresh.layoutkernel.util.SmartUtil;

/* JADX INFO: loaded from: classes3.dex */
public class BallPulseFooter extends SimpleComponent implements RefreshFooter {
    protected int mAnimatingColor;
    protected float mCircleSpacing;
    protected TimeInterpolator mInterpolator;
    protected boolean mIsStarted;
    protected boolean mManualAnimationColor;
    protected boolean mManualNormalColor;
    protected int mNormalColor;
    protected Paint mPaint;
    protected long mStartTime;

    public BallPulseFooter(Context context) {
        this(context, null);
    }

    public BallPulseFooter(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.mNormalColor = -1118482;
        this.mAnimatingColor = -1615546;
        this.mStartTime = 0L;
        this.mIsStarted = false;
        this.mInterpolator = new AccelerateDecelerateInterpolator();
        setMinimumHeight(SmartUtil.dp2px(60.0f));
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BallPulseFooter);
        this.mPaint = new Paint();
        this.mPaint.setColor(-1);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setAntiAlias(true);
        this.mSpinnerStyle = SpinnerStyle.Translate;
        this.mSpinnerStyle = SpinnerStyle.values[typedArrayObtainStyledAttributes.getInt(R.styleable.BallPulseFooter_srlClassicsSpinnerStyle, this.mSpinnerStyle.ordinal)];
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.BallPulseFooter_srlNormalColor)) {
            setNormalColor(typedArrayObtainStyledAttributes.getColor(R.styleable.BallPulseFooter_srlNormalColor, 0));
        }
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.BallPulseFooter_srlAnimatingColor)) {
            setAnimatingColor(typedArrayObtainStyledAttributes.getColor(R.styleable.BallPulseFooter_srlAnimatingColor, 0));
        }
        typedArrayObtainStyledAttributes.recycle();
        this.mCircleSpacing = SmartUtil.dp2px(4.0f);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        float fMin = Math.min(width, height);
        float f2 = this.mCircleSpacing;
        float f3 = (fMin - (f2 * 2.0f)) / 6.0f;
        float f4 = f3 * 2.0f;
        float f5 = (width / 2.0f) - (f2 + f4);
        float f6 = height / 2.0f;
        long jCurrentTimeMillis = System.currentTimeMillis();
        int i = 0;
        while (i < 3) {
            int i2 = i + 1;
            float interpolation = this.mInterpolator.getInterpolation((jCurrentTimeMillis - this.mStartTime) - ((long) (i2 * 120)) > 0 ? (r10 % 750) / 750.0f : 0.0f);
            canvas.save();
            float f7 = i;
            canvas.translate((f4 * f7) + f5 + (this.mCircleSpacing * f7), f6);
            if (interpolation < 0.5d) {
                float f8 = 1.0f - ((interpolation * 2.0f) * 0.7f);
                canvas.scale(f8, f8);
            } else {
                float f9 = ((interpolation * 2.0f) * 0.7f) - 0.4f;
                canvas.scale(f9, f9);
            }
            canvas.drawCircle(0.0f, 0.0f, f3, this.mPaint);
            canvas.restore();
            i = i2;
        }
        super.dispatchDraw(canvas);
        if (this.mIsStarted) {
            invalidate();
        }
    }

    @Override // com.ido.smartrefresh.layoutkernel.simple.SimpleComponent, com.ido.smartrefresh.layoutkernel.api.RefreshComponent
    public void onStartAnimator(RefreshLayout refreshLayout, int i, int i2) {
        if (this.mIsStarted) {
            return;
        }
        invalidate();
        this.mIsStarted = true;
        this.mStartTime = System.currentTimeMillis();
        this.mPaint.setColor(this.mAnimatingColor);
    }

    @Override // com.ido.smartrefresh.layoutkernel.simple.SimpleComponent, com.ido.smartrefresh.layoutkernel.api.RefreshComponent
    public int onFinish(RefreshLayout refreshLayout, boolean z) {
        this.mIsStarted = false;
        this.mStartTime = 0L;
        this.mPaint.setColor(this.mNormalColor);
        return 0;
    }

    @Override // com.ido.smartrefresh.layoutkernel.simple.SimpleComponent, com.ido.smartrefresh.layoutkernel.api.RefreshComponent
    @Deprecated
    public void setPrimaryColors(int... iArr) {
        if (!this.mManualAnimationColor && iArr.length > 1) {
            setAnimatingColor(iArr[0]);
            this.mManualAnimationColor = false;
        }
        if (this.mManualNormalColor) {
            return;
        }
        if (iArr.length > 1) {
            setNormalColor(iArr[1]);
        } else if (iArr.length > 0) {
            setNormalColor(ColorUtils.compositeColors(-1711276033, iArr[0]));
        }
        this.mManualNormalColor = false;
    }

    public BallPulseFooter setSpinnerStyle(SpinnerStyle spinnerStyle) {
        this.mSpinnerStyle = spinnerStyle;
        return this;
    }

    public BallPulseFooter setNormalColor(int i) {
        this.mNormalColor = i;
        this.mManualNormalColor = true;
        if (!this.mIsStarted) {
            this.mPaint.setColor(i);
        }
        return this;
    }

    public BallPulseFooter setAnimatingColor(int i) {
        this.mAnimatingColor = i;
        this.mManualAnimationColor = true;
        if (this.mIsStarted) {
            this.mPaint.setColor(i);
        }
        return this;
    }
}