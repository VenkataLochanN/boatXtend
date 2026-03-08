package com.ido.life.customview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import androidx.core.view.InputDeviceCompat;
import com.autonavi.base.amap.mapcore.tools.GLMapStaticValue;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.R;
import com.ido.life.location.MapHelper;
import com.ido.life.util.ViewUtil;
import com.realsil.sdk.dfu.DfuException;

/* JADX INFO: loaded from: classes2.dex */
public class ColorArcProgressBar extends View {
    private Paint allArcPaint;
    private int aniSpeed;
    private int bgArcColor;
    private float bgArcWidth;
    private RectF bgRect;
    private float centerX;
    private int[] colors;
    private float curTextSize;
    private float curValuePadding;
    private float curValues;
    private float currentAngle;
    private Paint hintPaint;
    private float hintSize;
    private boolean isAddPrecent;
    boolean isEnd;
    private boolean isNeedContent;
    private boolean isNeedIcon;
    private boolean isNeedUnit;
    private float k;
    private PaintFlagsDrawFilter mDrawFilter;
    private String mGoalStr;
    private int mUnitStrId;
    private String mUnitText;
    private float maxValues;
    private ValueAnimator progressAnimator;
    private Paint progressPaint;
    private float progressWidth;
    private Matrix rotateMatrix;
    private float startAngle;
    private float sweepAngle;
    private SweepGradient sweepGradient;
    private float textSize;
    private Paint vPercentPaint;
    private Paint vTextPaint;
    int width;

    public interface AnimationCallback {
        void end();
    }

    public ColorArcProgressBar(Context context) {
        super(context, null);
        this.startAngle = 270.0f;
        this.sweepAngle = 90.0f;
        this.currentAngle = 0.0f;
        this.colors = new int[]{MapHelper.Standard_Color, InputDeviceCompat.SOURCE_ANY, -65536, -65536};
        this.maxValues = 60.0f;
        this.curValues = 0.0f;
        this.bgArcWidth = dipToPx(2.0f);
        this.progressWidth = dipToPx(10.0f);
        this.textSize = 40.0f;
        this.curTextSize = 40.0f;
        this.hintSize = dipToPx(17.0f);
        this.aniSpeed = 1000;
        this.mUnitStrId = -1;
        this.isEnd = true;
        initView();
    }

    public ColorArcProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.startAngle = 270.0f;
        this.sweepAngle = 90.0f;
        this.currentAngle = 0.0f;
        this.colors = new int[]{MapHelper.Standard_Color, InputDeviceCompat.SOURCE_ANY, -65536, -65536};
        this.maxValues = 60.0f;
        this.curValues = 0.0f;
        this.bgArcWidth = dipToPx(2.0f);
        this.progressWidth = dipToPx(10.0f);
        this.textSize = 40.0f;
        this.curTextSize = 40.0f;
        this.hintSize = dipToPx(17.0f);
        this.aniSpeed = 1000;
        this.mUnitStrId = -1;
        this.isEnd = true;
        initCofig(context, attributeSet);
        initView();
    }

    public ColorArcProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.startAngle = 270.0f;
        this.sweepAngle = 90.0f;
        this.currentAngle = 0.0f;
        this.colors = new int[]{MapHelper.Standard_Color, InputDeviceCompat.SOURCE_ANY, -65536, -65536};
        this.maxValues = 60.0f;
        this.curValues = 0.0f;
        this.bgArcWidth = dipToPx(2.0f);
        this.progressWidth = dipToPx(10.0f);
        this.textSize = 40.0f;
        this.curTextSize = 40.0f;
        this.hintSize = dipToPx(17.0f);
        this.aniSpeed = 1000;
        this.mUnitStrId = -1;
        this.isEnd = true;
        initCofig(context, attributeSet);
        initView();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.width == 0) {
            this.width = i;
            resetTextSize();
        }
    }

    private void initCofig(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ColorArcProgressBar);
        this.bgArcColor = typedArrayObtainStyledAttributes.getColor(0, -7829368);
        int color = typedArrayObtainStyledAttributes.getColor(4, MapHelper.Standard_Color);
        int color2 = typedArrayObtainStyledAttributes.getColor(5, color);
        int color3 = typedArrayObtainStyledAttributes.getColor(6, color);
        this.colors = new int[]{color, color2, color3, color3};
        this.sweepAngle = typedArrayObtainStyledAttributes.getInteger(14, DfuException.ERROR_READ_DEVICE_INFO_ERROR);
        this.bgArcWidth = typedArrayObtainStyledAttributes.getDimension(1, dipToPx(2.0f));
        this.progressWidth = typedArrayObtainStyledAttributes.getDimension(7, dipToPx(10.0f));
        this.isNeedIcon = typedArrayObtainStyledAttributes.getBoolean(10, false);
        this.textSize = dipToPx(typedArrayObtainStyledAttributes.getDimension(2, this.curTextSize));
        this.curTextSize = this.textSize;
        this.isNeedContent = typedArrayObtainStyledAttributes.getBoolean(9, false);
        this.isNeedUnit = typedArrayObtainStyledAttributes.getBoolean(11, false);
        this.isAddPrecent = typedArrayObtainStyledAttributes.getBoolean(8, false);
        this.mUnitStrId = typedArrayObtainStyledAttributes.getResourceId(13, -1);
        this.curValues = typedArrayObtainStyledAttributes.getFloat(3, 0.0f);
        this.maxValues = typedArrayObtainStyledAttributes.getFloat(12, 360.0f);
        setCurrentValue(this.curValues, true);
        setMaxValues((int) this.maxValues);
        typedArrayObtainStyledAttributes.recycle();
        this.mUnitText = LanguageUtil.getLanguageText(this.mUnitStrId);
    }

    public void refreshText() {
        this.mUnitText = LanguageUtil.getLanguageText(this.mUnitStrId);
        invalidate();
    }

    private void initView() {
        this.allArcPaint = new Paint();
        this.allArcPaint.setAntiAlias(true);
        this.allArcPaint.setStyle(Paint.Style.STROKE);
        this.allArcPaint.setStrokeWidth(this.bgArcWidth);
        this.allArcPaint.setColor(this.bgArcColor);
        this.allArcPaint.setStrokeCap(Paint.Cap.ROUND);
        this.progressPaint = new Paint();
        this.progressPaint.setAntiAlias(true);
        this.progressPaint.setStyle(Paint.Style.STROKE);
        this.progressPaint.setStrokeCap(Paint.Cap.ROUND);
        this.progressPaint.setStrokeWidth(this.progressWidth);
        this.progressPaint.setColor(MapHelper.Standard_Color);
        this.vTextPaint = new Paint();
        this.vTextPaint.setTextSize(this.textSize);
        this.vTextPaint.setColor(getResources().getColor(com.boat.Xtend.two.R.color.white));
        this.vTextPaint.setTextAlign(Paint.Align.CENTER);
        this.curValuePadding = (this.textSize * 4.0f) / 7.0f;
        this.vPercentPaint = new Paint();
        this.vPercentPaint.setTextSize(this.textSize);
        this.vPercentPaint.setColor(getResources().getColor(com.boat.Xtend.two.R.color.white));
        this.vPercentPaint.setTextAlign(Paint.Align.CENTER);
        this.hintPaint = new Paint();
        this.hintPaint.setTextSize(this.hintSize);
        this.hintPaint.setAlpha(102);
        this.hintPaint.setColor(getResources().getColor(com.boat.Xtend.two.R.color.translucent));
        this.hintPaint.setTextAlign(Paint.Align.LEFT);
        this.mDrawFilter = new PaintFlagsDrawFilter(0, 3);
        float f2 = this.centerX;
        this.sweepGradient = new SweepGradient(f2, f2, this.colors, (float[]) null);
        this.rotateMatrix = new Matrix();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        canvas.setDrawFilter(this.mDrawFilter);
        this.centerX = getWidth() / 2;
        int i = (int) (this.centerX - (this.progressWidth / 2.0f));
        if (this.bgRect == null) {
            this.bgRect = new RectF();
        }
        RectF rectF = this.bgRect;
        float f2 = this.centerX;
        float f3 = i;
        rectF.set(f2 - f3, f2 - f3, f2 + f3, f2 + f3);
        canvas.drawArc(this.bgRect, this.startAngle, this.sweepAngle, false, this.allArcPaint);
        Matrix matrix = this.rotateMatrix;
        float f4 = this.centerX;
        matrix.setRotate(130.0f, f4, f4);
        this.sweepGradient.setLocalMatrix(this.rotateMatrix);
        this.progressPaint.setShader(this.sweepGradient);
        canvas.drawArc(this.bgRect, this.startAngle, this.currentAngle, false, this.progressPaint);
        if (this.isNeedContent) {
            if (this.isAddPrecent) {
                String str = Math.min((int) ((this.curValues * 100.0f) / this.maxValues), GLMapStaticValue.MAPRENDER_CAN_STOP_AND_FULLSCREEN_RENDEROVER) + "%";
                float f5 = this.centerX;
                canvas.drawText(str, f5, (this.textSize / 3.0f) + f5, this.vPercentPaint);
            } else {
                String strValueOf = String.valueOf((int) this.curValues);
                float f6 = this.centerX;
                canvas.drawText(strValueOf, f6, (this.curValuePadding / 2.0f) + f6, this.vTextPaint);
            }
        }
        if (this.isNeedUnit) {
            this.hintPaint.setTextAlign(Paint.Align.LEFT);
            float fMeasureText = this.hintPaint.measureText(this.mGoalStr);
            Paint paint = this.hintPaint;
            paint.setTextSize((paint.getTextSize() / 3.0f) * 2.0f);
            float fMeasureText2 = this.hintPaint.measureText(" " + this.mUnitText);
            this.hintPaint.setTextSize(this.hintSize);
            float f7 = (fMeasureText2 + fMeasureText) / 2.0f;
            canvas.drawText(this.mGoalStr, this.centerX - f7, (float) ((getHeight() / 5) * 4), this.hintPaint);
            this.hintPaint.setTextAlign(Paint.Align.RIGHT);
            Paint paint2 = this.hintPaint;
            paint2.setTextSize((paint2.getTextSize() / 3.0f) * 2.0f);
            canvas.drawText(this.mUnitText, this.centerX + f7, (getHeight() / 5) * 4, this.hintPaint);
            this.hintPaint.setTextSize(this.hintSize);
        }
    }

    private void resetTextSize() {
        if (this.vTextPaint != null) {
            if (ViewUtil.getTextRectWidth(this.vTextPaint, String.valueOf((int) this.curValues)) > this.width - (this.progressWidth * 2.0f)) {
                this.curTextSize = (this.textSize / 4.0f) * 3.0f;
            } else {
                this.curTextSize = this.textSize;
            }
            this.vTextPaint.setTextSize(this.curTextSize);
        }
    }

    public void setMaxValues(int i) {
        float f2 = i;
        if (this.maxValues == f2) {
            return;
        }
        this.maxValues = f2;
        this.k = this.sweepAngle / f2;
        this.mGoalStr = "/" + i;
        invalidate();
    }

    public float getMaxValue() {
        return this.maxValues;
    }

    public void setCurrentValue(float f2, boolean z) {
        if (f2 != this.curValues || z) {
            if (f2 < 0.0f) {
                f2 = 0.0f;
            }
            if (this.k <= 0.0f) {
                this.k = this.sweepAngle / this.maxValues;
            }
            if (String.valueOf(this.curValues).length() != String.valueOf(f2).length()) {
                this.curValues = f2;
                resetTextSize();
            } else {
                this.curValues = f2;
            }
            float f3 = this.k;
            this.currentAngle = f2 * f3;
            if (z) {
                setAnimation(f2 * f3, this.aniSpeed);
            } else {
                invalidate();
            }
        }
    }

    public int getCurrentValues() {
        return (int) this.curValues;
    }

    public void setTextTypeFace(Typeface typeface) {
        this.vTextPaint.setTypeface(typeface);
    }

    public void setBgArcWidth(int i) {
        this.bgArcWidth = i;
    }

    public void setProgressWidth(int i) {
        this.progressWidth = i;
    }

    public void setTextSize(int i) {
        float f2 = i;
        this.textSize = f2;
        this.curTextSize = f2;
        this.vTextPaint = new Paint();
        this.vTextPaint.setTextSize(f2);
        this.vTextPaint.setColor(getResources().getColor(com.boat.Xtend.two.R.color.white));
        this.vTextPaint.setTextAlign(Paint.Align.CENTER);
        invalidate();
    }

    public void setCurValuePadding(int i) {
        this.curValuePadding = i;
    }

    public void setHintSize(int i) {
        this.hintSize = i;
    }

    private void setAnimation(float f2, int i) {
        ValueAnimator valueAnimator = this.progressAnimator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.progressAnimator.cancel();
        }
        this.progressAnimator = ValueAnimator.ofFloat(0.0f, f2);
        this.progressAnimator.setDuration(i);
        this.progressAnimator.setTarget(Float.valueOf(this.currentAngle));
        this.progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.life.customview.-$$Lambda$ColorArcProgressBar$JFKMx5tGawRBNI301wtVNop_QH4
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                this.f$0.lambda$setAnimation$0$ColorArcProgressBar(valueAnimator2);
            }
        });
        this.progressAnimator.start();
    }

    public /* synthetic */ void lambda$setAnimation$0$ColorArcProgressBar(ValueAnimator valueAnimator) {
        this.currentAngle = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.curValues = Math.round(this.currentAngle / this.k);
        invalidate();
    }

    public void setScaleAnimation(final AnimationCallback animationCallback) {
        if (this.isEnd) {
            this.isEnd = false;
            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, "scaleX", 1.0f, 1.2f, 1.0f);
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this, "scaleY", 1.0f, 1.2f, 1.0f);
            animatorSet.setDuration(800L);
            animatorSet.setInterpolator(new DecelerateInterpolator());
            animatorSet.play(objectAnimatorOfFloat).with(objectAnimatorOfFloat2);
            animatorSet.start();
            animatorSet.addListener(new Animator.AnimatorListener() { // from class: com.ido.life.customview.ColorArcProgressBar.1
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    ColorArcProgressBar.this.isEnd = true;
                    AnimationCallback animationCallback2 = animationCallback;
                    if (animationCallback2 != null) {
                        animationCallback2.end();
                    }
                }
            });
        }
    }

    private int dipToPx(float f2) {
        return (int) ((getContext().getResources().getDisplayMetrics().density * f2) + ((f2 >= 0.0f ? 1 : -1) * 0.5f));
    }
}