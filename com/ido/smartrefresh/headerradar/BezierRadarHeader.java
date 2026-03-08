package com.ido.smartrefresh.headerradar;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.amap.api.maps.utils.SpatialRelationUtil;
import com.ido.smartrefresh.R;
import com.ido.smartrefresh.layoutkernel.api.RefreshHeader;
import com.ido.smartrefresh.layoutkernel.api.RefreshLayout;
import com.ido.smartrefresh.layoutkernel.constant.RefreshState;
import com.ido.smartrefresh.layoutkernel.constant.SpinnerStyle;
import com.ido.smartrefresh.layoutkernel.simple.SimpleComponent;
import com.ido.smartrefresh.layoutkernel.util.SmartUtil;
import com.realsil.sdk.dfu.DfuException;

/* JADX INFO: loaded from: classes3.dex */
public class BezierRadarHeader extends SimpleComponent implements RefreshHeader {
    protected static final byte PROPERTY_DOT_ALPHA = 2;
    protected static final byte PROPERTY_RADAR_ANGLE = 4;
    protected static final byte PROPERTY_RADAR_SCALE = 0;
    protected static final byte PROPERTY_RIPPLE_RADIUS = 3;
    protected static final byte PROPERTY_WAVE_HEIGHT = 1;
    protected int mAccentColor;
    protected Animator mAnimatorSet;
    protected float mDotAlpha;
    protected float mDotFraction;
    protected float mDotRadius;
    protected boolean mEnableHorizontalDrag;
    protected boolean mManualAccentColor;
    protected boolean mManualPrimaryColor;
    protected Paint mPaint;
    protected Path mPath;
    protected int mPrimaryColor;
    protected int mRadarAngle;
    protected float mRadarCircle;
    protected float mRadarRadius;
    protected RectF mRadarRect;
    protected float mRadarScale;
    protected float mRippleRadius;
    protected int mWaveHeight;
    protected int mWaveOffsetX;
    protected int mWaveOffsetY;
    protected boolean mWavePulling;
    protected int mWaveTop;

    public BezierRadarHeader(Context context) {
        this(context, null);
    }

    public BezierRadarHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.mEnableHorizontalDrag = false;
        this.mWaveOffsetX = -1;
        this.mWaveOffsetY = 0;
        this.mRadarAngle = 0;
        this.mRadarRadius = 0.0f;
        this.mRadarCircle = 0.0f;
        this.mRadarScale = 0.0f;
        this.mRadarRect = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        this.mSpinnerStyle = SpinnerStyle.FixedBehind;
        this.mPath = new Path();
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mDotRadius = SmartUtil.dp2px(7.0f);
        this.mRadarRadius = SmartUtil.dp2px(20.0f);
        this.mRadarCircle = SmartUtil.dp2px(7.0f);
        this.mPaint.setStrokeWidth(SmartUtil.dp2px(3.0f));
        setMinimumHeight(SmartUtil.dp2px(100.0f));
        if (isInEditMode()) {
            this.mWaveTop = 1000;
            this.mRadarScale = 1.0f;
            this.mRadarAngle = DfuException.ERROR_READ_DEVICE_INFO_ERROR;
        } else {
            this.mRadarScale = 0.0f;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BezierRadarHeader);
        this.mEnableHorizontalDrag = typedArrayObtainStyledAttributes.getBoolean(R.styleable.BezierRadarHeader_srlEnableHorizontalDrag, this.mEnableHorizontalDrag);
        setAccentColor(typedArrayObtainStyledAttributes.getColor(R.styleable.BezierRadarHeader_srlAccentColor, -1));
        setPrimaryColor(typedArrayObtainStyledAttributes.getColor(R.styleable.BezierRadarHeader_srlPrimaryColor, -14540254));
        this.mManualAccentColor = typedArrayObtainStyledAttributes.hasValue(R.styleable.BezierRadarHeader_srlAccentColor);
        this.mManualPrimaryColor = typedArrayObtainStyledAttributes.hasValue(R.styleable.BezierRadarHeader_srlPrimaryColor);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Animator animator = this.mAnimatorSet;
        if (animator != null) {
            animator.removeAllListeners();
            this.mAnimatorSet.end();
            this.mAnimatorSet = null;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        int width = getWidth();
        int height = isInEditMode() ? getHeight() : this.mWaveOffsetY;
        drawWave(canvas, width);
        drawDot(canvas, width, height);
        drawRadar(canvas, width, height);
        drawRipple(canvas, width, height);
        super.dispatchDraw(canvas);
    }

    protected void drawWave(Canvas canvas, int i) {
        this.mPath.reset();
        this.mPath.lineTo(0.0f, this.mWaveTop);
        Path path = this.mPath;
        int i2 = this.mWaveOffsetX;
        float f2 = i2 >= 0 ? i2 : i / 2.0f;
        float f3 = i;
        path.quadTo(f2, this.mWaveHeight + r3, f3, this.mWaveTop);
        this.mPath.lineTo(f3, 0.0f);
        this.mPaint.setColor(this.mPrimaryColor);
        canvas.drawPath(this.mPath, this.mPaint);
    }

    protected void drawDot(Canvas canvas, int i, int i2) {
        if (this.mDotAlpha > 0.0f) {
            this.mPaint.setColor(this.mAccentColor);
            float fPx2dp = SmartUtil.px2dp(i2);
            float f2 = i;
            float f3 = 7.0f;
            float f4 = (f2 * 1.0f) / 7.0f;
            float f5 = this.mDotFraction;
            float f6 = (f4 * f5) - (f5 > 1.0f ? ((f5 - 1.0f) * f4) / f5 : 0.0f);
            float f7 = i2;
            float f8 = this.mDotFraction;
            float f9 = f7 - (f8 > 1.0f ? (((f8 - 1.0f) * f7) / 2.0f) / f8 : 0.0f);
            int i3 = 0;
            while (i3 < 7) {
                float f10 = (i3 + 1.0f) - 4.0f;
                this.mPaint.setAlpha((int) (((double) (this.mDotAlpha * (1.0f - ((Math.abs(f10) / f3) * 2.0f)) * 255.0f)) * (1.0d - (1.0d / Math.pow((((double) fPx2dp) / 800.0d) + 1.0d, 15.0d)))));
                float f11 = this.mDotRadius * (1.0f - (1.0f / ((fPx2dp / 10.0f) + 1.0f)));
                canvas.drawCircle(((f2 / 2.0f) - (f11 / 2.0f)) + (f6 * f10), f9 / 2.0f, f11, this.mPaint);
                i3++;
                f3 = 7.0f;
            }
            this.mPaint.setAlpha(255);
        }
    }

    protected void drawRadar(Canvas canvas, int i, int i2) {
        if (this.mAnimatorSet != null || isInEditMode()) {
            float f2 = this.mRadarRadius;
            float f3 = this.mRadarScale;
            float f4 = f2 * f3;
            float f5 = this.mRadarCircle * f3;
            this.mPaint.setColor(this.mAccentColor);
            this.mPaint.setStyle(Paint.Style.FILL);
            float f6 = i / 2.0f;
            float f7 = i2 / 2.0f;
            canvas.drawCircle(f6, f7, f4, this.mPaint);
            this.mPaint.setStyle(Paint.Style.STROKE);
            float f8 = f5 + f4;
            canvas.drawCircle(f6, f7, f8, this.mPaint);
            this.mPaint.setColor((this.mPrimaryColor & ViewCompat.MEASURED_SIZE_MASK) | 1426063360);
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mRadarRect.set(f6 - f4, f7 - f4, f6 + f4, f4 + f7);
            canvas.drawArc(this.mRadarRect, 270.0f, this.mRadarAngle, true, this.mPaint);
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mRadarRect.set(f6 - f8, f7 - f8, f6 + f8, f7 + f8);
            canvas.drawArc(this.mRadarRect, 270.0f, this.mRadarAngle, false, this.mPaint);
            this.mPaint.setStyle(Paint.Style.FILL);
        }
    }

    protected void drawRipple(Canvas canvas, int i, int i2) {
        if (this.mRippleRadius > 0.0f) {
            this.mPaint.setColor(this.mAccentColor);
            canvas.drawCircle(i / 2.0f, i2 / 2.0f, this.mRippleRadius, this.mPaint);
        }
    }

    @Override // com.ido.smartrefresh.layoutkernel.simple.SimpleComponent, com.ido.smartrefresh.layoutkernel.api.RefreshComponent
    public void onMoving(boolean z, float f2, int i, int i2, int i3) {
        this.mWaveOffsetY = i;
        if (z || this.mWavePulling) {
            this.mWavePulling = true;
            this.mWaveTop = Math.min(i2, i);
            this.mWaveHeight = (int) (Math.max(0, i - i2) * 1.9f);
            this.mDotFraction = f2;
            invalidate();
        }
    }

    @Override // com.ido.smartrefresh.layoutkernel.simple.SimpleComponent, com.ido.smartrefresh.layoutkernel.api.RefreshComponent
    public void onReleased(RefreshLayout refreshLayout, int i, int i2) {
        this.mWaveTop = i - 1;
        this.mWavePulling = false;
        SmartUtil smartUtil = new SmartUtil(SmartUtil.INTERPOLATOR_DECELERATE);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
        valueAnimatorOfFloat.setInterpolator(smartUtil);
        valueAnimatorOfFloat.addUpdateListener(new AnimatorUpdater((byte) 2));
        ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.setInterpolator(smartUtil);
        valueAnimatorOfFloat2.addUpdateListener(new AnimatorUpdater((byte) 0));
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(0, SpatialRelationUtil.A_CIRCLE_DEGREE);
        valueAnimatorOfInt.setDuration(720L);
        valueAnimatorOfInt.setRepeatCount(-1);
        valueAnimatorOfInt.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimatorOfInt.addUpdateListener(new AnimatorUpdater((byte) 4));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(valueAnimatorOfFloat, valueAnimatorOfFloat2, valueAnimatorOfInt);
        animatorSet.start();
        int i3 = this.mWaveHeight;
        ValueAnimator valueAnimatorOfInt2 = ValueAnimator.ofInt(i3, 0, -((int) (i3 * 0.8f)), 0, -((int) (i3 * 0.4f)), 0);
        valueAnimatorOfInt2.addUpdateListener(new AnimatorUpdater((byte) 1));
        valueAnimatorOfInt2.setInterpolator(new SmartUtil(SmartUtil.INTERPOLATOR_DECELERATE));
        valueAnimatorOfInt2.setDuration(800L);
        valueAnimatorOfInt2.start();
        this.mAnimatorSet = animatorSet;
    }

    @Override // com.ido.smartrefresh.layoutkernel.simple.SimpleComponent, com.ido.smartrefresh.layoutkernel.api.RefreshComponent
    public int onFinish(RefreshLayout refreshLayout, boolean z) {
        Animator animator = this.mAnimatorSet;
        if (animator != null) {
            animator.removeAllListeners();
            this.mAnimatorSet.end();
            this.mAnimatorSet = null;
        }
        int width = getWidth();
        int i = this.mWaveOffsetY;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(this.mRadarRadius, (float) Math.sqrt((width * width) + (i * i)));
        valueAnimatorOfFloat.setDuration(400L);
        valueAnimatorOfFloat.addUpdateListener(new AnimatorUpdater((byte) 3));
        valueAnimatorOfFloat.start();
        return 400;
    }

    /* JADX INFO: renamed from: com.ido.smartrefresh.headerradar.BezierRadarHeader$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$ido$smartrefresh$layoutkernel$constant$RefreshState = new int[RefreshState.values().length];

        static {
            try {
                $SwitchMap$com$ido$smartrefresh$layoutkernel$constant$RefreshState[RefreshState.None.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ido$smartrefresh$layoutkernel$constant$RefreshState[RefreshState.PullDownToRefresh.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // com.ido.smartrefresh.layoutkernel.simple.SimpleComponent, com.ido.smartrefresh.layoutkernel.listener.OnStateChangedListener
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState refreshState, RefreshState refreshState2) {
        int i = AnonymousClass1.$SwitchMap$com$ido$smartrefresh$layoutkernel$constant$RefreshState[refreshState2.ordinal()];
        if (i == 1 || i == 2) {
            this.mDotAlpha = 1.0f;
            this.mRadarScale = 0.0f;
            this.mRippleRadius = 0.0f;
        }
    }

    @Override // com.ido.smartrefresh.layoutkernel.simple.SimpleComponent, com.ido.smartrefresh.layoutkernel.api.RefreshComponent
    @Deprecated
    public void setPrimaryColors(int... iArr) {
        if (iArr.length > 0 && !this.mManualPrimaryColor) {
            setPrimaryColor(iArr[0]);
            this.mManualPrimaryColor = false;
        }
        if (iArr.length <= 1 || this.mManualAccentColor) {
            return;
        }
        setAccentColor(iArr[1]);
        this.mManualAccentColor = false;
    }

    @Override // com.ido.smartrefresh.layoutkernel.simple.SimpleComponent, com.ido.smartrefresh.layoutkernel.api.RefreshComponent
    public boolean isSupportHorizontalDrag() {
        return this.mEnableHorizontalDrag;
    }

    @Override // com.ido.smartrefresh.layoutkernel.simple.SimpleComponent, com.ido.smartrefresh.layoutkernel.api.RefreshComponent
    public void onHorizontalDrag(float f2, int i, int i2) {
        this.mWaveOffsetX = i;
        invalidate();
    }

    public BezierRadarHeader setPrimaryColor(int i) {
        this.mPrimaryColor = i;
        this.mManualPrimaryColor = true;
        return this;
    }

    public BezierRadarHeader setAccentColor(int i) {
        this.mAccentColor = i;
        this.mManualAccentColor = true;
        return this;
    }

    public BezierRadarHeader setPrimaryColorId(int i) {
        setPrimaryColor(ContextCompat.getColor(getContext(), i));
        return this;
    }

    public BezierRadarHeader setAccentColorId(int i) {
        setAccentColor(ContextCompat.getColor(getContext(), i));
        return this;
    }

    public BezierRadarHeader setEnableHorizontalDrag(boolean z) {
        this.mEnableHorizontalDrag = z;
        if (!z) {
            this.mWaveOffsetX = -1;
        }
        return this;
    }

    protected class AnimatorUpdater implements ValueAnimator.AnimatorUpdateListener {
        byte propertyName;

        AnimatorUpdater(byte b2) {
            this.propertyName = b2;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            byte b2 = this.propertyName;
            if (b2 == 0) {
                BezierRadarHeader.this.mRadarScale = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            } else if (1 == b2) {
                if (BezierRadarHeader.this.mWavePulling) {
                    valueAnimator.cancel();
                    return;
                } else {
                    BezierRadarHeader.this.mWaveHeight = ((Integer) valueAnimator.getAnimatedValue()).intValue() / 2;
                }
            } else if (2 == b2) {
                BezierRadarHeader.this.mDotAlpha = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            } else if (3 == b2) {
                BezierRadarHeader.this.mRippleRadius = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            } else if (4 == b2) {
                BezierRadarHeader.this.mRadarAngle = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            }
            BezierRadarHeader.this.invalidate();
        }
    }
}