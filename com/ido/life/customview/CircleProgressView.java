package com.ido.life.customview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import androidx.core.view.ViewCompat;
import com.ido.life.R;

/* JADX INFO: loaded from: classes2.dex */
public class CircleProgressView extends View {
    private static final float endAngle = 360.0f;
    private static final float startAngle = -90.0f;
    private ObjectAnimator animProgress;
    private int cicleBgColor;
    private Paint cicleBgPaintOut;
    private int cicleColor;
    private Paint ciclePaintOut;
    private float circleBgWidth;
    private float circleWidth;
    private int height;
    private boolean isProgressing;
    private boolean isRecoverProgress;
    private float lastAnimatedValue;
    private RectF oval;
    private OnLongPressStatusListener statusListener;
    private float sweepAngle;
    private int width;

    public interface OnLongPressStatusListener {
        void onCancel();

        void onFinish();

        void onStart();
    }

    public CircleProgressView(Context context) {
        super(context);
        this.lastAnimatedValue = 0.0f;
        this.isRecoverProgress = true;
        initView(context, null);
    }

    public CircleProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.lastAnimatedValue = 0.0f;
        this.isRecoverProgress = true;
        initView(context, attributeSet);
    }

    public CircleProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.lastAnimatedValue = 0.0f;
        this.isRecoverProgress = true;
        initView(context, attributeSet);
    }

    private void initView(Context context, AttributeSet attributeSet) {
        this.ciclePaintOut = new Paint();
        this.ciclePaintOut.setStyle(Paint.Style.STROKE);
        this.ciclePaintOut.setAntiAlias(true);
        this.cicleBgPaintOut = new Paint();
        this.cicleBgPaintOut.setStyle(Paint.Style.STROKE);
        this.cicleBgPaintOut.setAntiAlias(true);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CircleProgressView, 0, 0);
            this.cicleColor = typedArrayObtainStyledAttributes.getColor(2, ViewCompat.MEASURED_STATE_MASK);
            this.circleWidth = typedArrayObtainStyledAttributes.getDimension(3, getResources().getDimension(com.boat.Xtend.two.R.dimen.sw_dp_2));
            this.cicleBgColor = typedArrayObtainStyledAttributes.getColor(0, ViewCompat.MEASURED_STATE_MASK);
            this.circleBgWidth = typedArrayObtainStyledAttributes.getDimension(1, getResources().getDimension(com.boat.Xtend.two.R.dimen.sw_dp_2));
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.height = getMeasuredHeight();
        this.width = getMeasuredWidth();
        if (this.oval == null) {
            this.oval = new RectF();
        }
        RectF rectF = this.oval;
        float f2 = this.circleWidth;
        int i3 = this.width;
        rectF.set(f2 / 2.0f, f2 / 2.0f, i3 - (f2 / 2.0f), i3 - (f2 / 2.0f));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        this.ciclePaintOut.setStrokeWidth(this.circleWidth);
        this.cicleBgPaintOut.setStrokeWidth(this.circleBgWidth);
        this.cicleBgPaintOut.setColor(this.cicleBgColor);
        if (this.sweepAngle > 0.0f) {
            canvas.drawArc(this.oval, 0.0f, endAngle, false, this.cicleBgPaintOut);
        }
        this.ciclePaintOut.setColor(this.cicleColor);
        canvas.drawArc(this.oval, startAngle, this.sweepAngle, false, this.ciclePaintOut);
        super.onDraw(canvas);
    }

    private void startProgress(float f2, final boolean z) {
        this.animProgress = ObjectAnimator.ofFloat(this, "sweepAngle", this.sweepAngle, f2);
        this.animProgress.setDuration((long) ((Math.abs(this.sweepAngle - f2) / endAngle) * 800.0f));
        this.animProgress.setInterpolator(new AccelerateDecelerateInterpolator());
        this.animProgress.start();
        this.animProgress.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.life.customview.CircleProgressView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                if (fFloatValue == CircleProgressView.endAngle && CircleProgressView.this.lastAnimatedValue != CircleProgressView.endAngle && CircleProgressView.this.statusListener != null) {
                    CircleProgressView.this.statusListener.onFinish();
                }
                if (!z && fFloatValue == 0.0f && CircleProgressView.this.statusListener != null) {
                    CircleProgressView.this.statusListener.onCancel();
                }
                CircleProgressView.this.lastAnimatedValue = fFloatValue;
            }
        });
    }

    public float getSweepAngle() {
        return this.sweepAngle;
    }

    public void setSweepAngle(float f2) {
        if (this.sweepAngle != f2) {
            this.sweepAngle = f2;
            invalidate();
        }
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            if (!this.isProgressing) {
                OnLongPressStatusListener onLongPressStatusListener = this.statusListener;
                if (onLongPressStatusListener != null) {
                    onLongPressStatusListener.onStart();
                }
                this.isProgressing = true;
                ObjectAnimator objectAnimator = this.animProgress;
                if (objectAnimator != null) {
                    objectAnimator.cancel();
                }
                clearAnimation();
                startProgress(endAngle, true);
            }
        } else if (action == 1 && (this.lastAnimatedValue < endAngle || this.isRecoverProgress)) {
            recoverProgreess();
        }
        return true;
    }

    public void setOnLongPressStatusListener(OnLongPressStatusListener onLongPressStatusListener) {
        this.statusListener = onLongPressStatusListener;
    }

    public void recoverProgreess() {
        ObjectAnimator objectAnimator = this.animProgress;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        clearAnimation();
        this.isProgressing = false;
        startProgress(0.0f, false);
    }

    public void setRecoverProgress(boolean z) {
        this.isRecoverProgress = z;
    }
}