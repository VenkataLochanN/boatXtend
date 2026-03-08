package com.ido.life.customview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.R;

/* JADX INFO: loaded from: classes2.dex */
public class WaveLoadingView extends View {
    private static final float DEFAULT_AMPLITUDE_RATIO = 0.1f;
    private static final float DEFAULT_AMPLITUDE_VALUE = 50.0f;
    private static final float DEFAULT_BORDER_WIDTH = 0.0f;
    private static final float DEFAULT_TITLE_TOP_SIZE = 18.0f;
    private static final float DEFAULT_WATER_LEVEL_RATIO = 0.5f;
    private static final float DEFAULT_WAVE_LENGTH_RATIO = 1.0f;
    private static final int DEFAULT_WAVE_PROGRESS_VALUE = 0;
    private static final float DEFAULT_WAVE_SHIFT_RATIO = 0.0f;
    private float amplitudeRatioAttr;
    private float mAmplitudeRatio;
    Bitmap mBitmapFail;
    Bitmap mBitmapInit;
    Bitmap mBitmapSuccess;
    private Paint mBorderPaint;
    private int mCircleColor;
    private Paint mCirclePaint;
    private Context mContext;
    private float mDefaultWaterLevel;
    private int mHeight;
    private int mProgressValue;
    private int mRingBgColor;
    private Paint mRingPaintBg;
    private float mRingRadius;
    private Matrix mShaderMatrix;
    private float mStrokeWidth;
    private Paint mTopTitlePaint;
    private float mTotalProgress;
    private float mTxtHeight;
    private float mWaterLevelRatio;
    private int mWaveBgColor;
    private Paint mWaveBgPaint;
    private int mWaveColor;
    private Paint mWavePaint;
    private BitmapShader mWaveShader;
    private float mWaveShiftRatio;
    private int mWidth;
    private RectF oval;
    private RectF oval1;
    private int status;
    private ObjectAnimator waterLevelAnim;
    private float waveRadius;
    private ObjectAnimator waveShiftAnim;
    private static final int DEFAULT_WAVE_COLOR = Color.parseColor("#4d4047");
    private static final int DEFAULT_WAVE_BACKGROUND_COLOR = Color.parseColor("#242327");
    private static final int DEFAULT_TITLE_COLOR = Color.parseColor("#212121");

    public WaveLoadingView(Context context) {
        this(context, null);
    }

    public WaveLoadingView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WaveLoadingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBitmapSuccess = null;
        this.mBitmapFail = null;
        this.mBitmapInit = null;
        this.mTotalProgress = 100.0f;
        this.mWaterLevelRatio = 0.0f;
        this.mWaveShiftRatio = 0.0f;
        this.mProgressValue = 0;
        init(context, attributeSet, i);
    }

    public void setmRingBgColor(int i) {
        this.mRingBgColor = i;
        this.mRingPaintBg.setColor(i);
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        this.mContext = context;
        this.status = 0;
        this.mShaderMatrix = new Matrix();
        this.mWavePaint = new Paint();
        this.mWavePaint.setAntiAlias(true);
        this.mWaveBgPaint = new Paint();
        this.mWaveBgPaint.setAntiAlias(true);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.WaveLoadingView, i, 0);
        this.mWaveColor = typedArrayObtainStyledAttributes.getColor(8, DEFAULT_WAVE_COLOR);
        this.mWaveBgColor = typedArrayObtainStyledAttributes.getColor(9, DEFAULT_WAVE_BACKGROUND_COLOR);
        this.mWaveBgPaint.setColor(this.mWaveBgColor);
        this.amplitudeRatioAttr = typedArrayObtainStyledAttributes.getFloat(7, 50.0f) / 1000.0f;
        float f2 = this.amplitudeRatioAttr;
        if (f2 > 0.1f) {
            f2 = 0.1f;
        }
        this.mAmplitudeRatio = f2;
        this.mProgressValue = typedArrayObtainStyledAttributes.getInteger(3, 0);
        setProgressValue(this.mProgressValue);
        this.mStrokeWidth = typedArrayObtainStyledAttributes.getDimension(1, dp2px(0.0f));
        this.mCircleColor = typedArrayObtainStyledAttributes.getColor(2, this.mContext.getColor(com.boat.Xtend.two.R.color.translate));
        this.mRingBgColor = typedArrayObtainStyledAttributes.getColor(4, this.mContext.getColor(com.boat.Xtend.two.R.color.translate));
        this.oval1 = new RectF();
        this.oval = new RectF();
        this.mCirclePaint = new Paint();
        this.mCirclePaint.setAntiAlias(true);
        this.mCirclePaint.setColor(this.mCircleColor);
        this.mCirclePaint.setStyle(Paint.Style.FILL);
        this.mRingPaintBg = new Paint();
        this.mRingPaintBg.setAntiAlias(true);
        this.mRingPaintBg.setColor(this.mRingBgColor);
        this.mRingPaintBg.setStyle(Paint.Style.STROKE);
        this.mRingPaintBg.setStrokeWidth(this.mStrokeWidth);
        this.mRingPaintBg.setColor(this.mRingBgColor);
        this.mBorderPaint = new Paint();
        this.mBorderPaint.setAntiAlias(true);
        this.mBorderPaint.setStyle(Paint.Style.STROKE);
        this.mBorderPaint.setStrokeWidth(this.mStrokeWidth);
        this.mBorderPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mBorderPaint.setColor(typedArrayObtainStyledAttributes.getColor(0, DEFAULT_WAVE_COLOR));
        this.mTopTitlePaint = new Paint();
        this.mTopTitlePaint.setColor(typedArrayObtainStyledAttributes.getColor(5, DEFAULT_TITLE_COLOR));
        this.mTopTitlePaint.setStyle(Paint.Style.FILL);
        this.mTopTitlePaint.setTextAlign(Paint.Align.CENTER);
        this.mTopTitlePaint.setAntiAlias(true);
        this.mTopTitlePaint.setTextSize(typedArrayObtainStyledAttributes.getDimension(6, sp2px(DEFAULT_TITLE_TOP_SIZE)));
        Paint.FontMetrics fontMetrics = this.mTopTitlePaint.getFontMetrics();
        this.mTxtHeight = (int) Math.ceil(fontMetrics.descent - fontMetrics.ascent);
        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(com.boat.Xtend.two.R.mipmap.icon_ota_upgrade)).getBitmap();
        this.mBitmapInit = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), new Matrix(), true);
        Bitmap bitmap2 = ((BitmapDrawable) getResources().getDrawable(com.boat.Xtend.two.R.mipmap.icon_ota_success)).getBitmap();
        this.mBitmapSuccess = Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.getWidth(), bitmap2.getHeight(), new Matrix(), true);
        Bitmap bitmap3 = ((BitmapDrawable) getResources().getDrawable(com.boat.Xtend.two.R.mipmap.icon_ota_failed)).getBitmap();
        this.mBitmapFail = Bitmap.createBitmap(bitmap3, 0, 0, bitmap3.getWidth(), bitmap3.getHeight(), new Matrix(), true);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (this.mWaveShader != null) {
            if (this.mWavePaint.getShader() == null) {
                this.mWavePaint.setShader(this.mWaveShader);
            }
            this.mShaderMatrix.setScale(1.0f, this.mAmplitudeRatio / 0.1f, 0.0f, this.mDefaultWaterLevel);
            this.mShaderMatrix.postTranslate(this.mWaveShiftRatio * this.mWidth, (0.5f - this.mWaterLevelRatio) * this.mHeight);
            this.mWaveShader.setLocalMatrix(this.mShaderMatrix);
            float f2 = this.mStrokeWidth;
            if (f2 > 0.0f) {
                int i = this.mWidth;
                canvas.drawCircle(i / 2.0f, i / 2.0f, (i / 2.0f) - f2, this.mCirclePaint);
            }
            int i2 = this.mWidth;
            canvas.drawCircle(i2 / 2.0f, i2 / 2.0f, (i2 / 2.0f) - (this.mStrokeWidth / 2.0f), this.mRingPaintBg);
            RectF rectF = this.oval1;
            int i3 = this.mWidth;
            float f3 = this.mRingRadius;
            rectF.left = (i3 / 2.0f) - f3;
            rectF.top = (i3 / 2.0f) - f3;
            rectF.right = (f3 * 2.0f) + ((i3 / 2.0f) - f3);
            rectF.bottom = (f3 * 2.0f) + ((i3 / 2.0f) - f3);
            canvas.drawArc(rectF, 0.0f, 360.0f, false, this.mRingPaintBg);
            canvas.drawCircle(this.mWidth / 2.0f, this.mHeight / 2.0f, this.waveRadius, this.mWaveBgPaint);
            RectF rectF2 = this.oval;
            int i4 = this.mWidth;
            float f4 = this.mRingRadius;
            rectF2.left = (i4 / 2.0f) - f4;
            rectF2.top = (i4 / 2.0f) - f4;
            rectF2.right = (f4 * 2.0f) + ((i4 / 2.0f) - f4);
            rectF2.bottom = (f4 * 2.0f) + ((i4 / 2.0f) - f4);
            int i5 = this.status;
            if (i5 == 1) {
                drawUpgrading(canvas);
                return;
            }
            if (i5 == 2) {
                drawSuccess(canvas);
                return;
            } else if (i5 == -1) {
                drawFailed(canvas);
                return;
            } else {
                drawPrepare(canvas);
                return;
            }
        }
        this.mWavePaint.setShader(null);
    }

    public void setStatus(int i, boolean z) {
        if (this.status == i) {
            return;
        }
        this.status = i;
        if (z) {
            waveQuitAnim();
        } else {
            invalidate();
        }
    }

    private void drawFailed(Canvas canvas) {
        canvas.drawArc(this.oval, -90.0f, 360.0f, false, this.mBorderPaint);
        canvas.drawBitmap(this.mBitmapFail, (this.mWidth / 2.0f) - (r0.getWidth() / 2.0f), (this.mHeight / 2.0f) - (this.mBitmapFail.getHeight() / 2.0f), this.mBorderPaint);
    }

    private void drawSuccess(Canvas canvas) {
        canvas.drawArc(this.oval, -90.0f, 360.0f, false, this.mBorderPaint);
        canvas.drawBitmap(this.mBitmapSuccess, (this.mWidth / 2.0f) - (r0.getWidth() / 2.0f), (this.mHeight / 2.0f) - (this.mBitmapSuccess.getHeight() / 2.0f), this.mBorderPaint);
    }

    private void drawPrepare(Canvas canvas) {
        canvas.drawArc(this.oval, -90.0f, 360.0f, false, this.mBorderPaint);
        canvas.drawBitmap(this.mBitmapInit, (this.mWidth / 2.0f) - (r0.getWidth() / 2.0f), (this.mHeight / 2.0f) - (this.mBitmapInit.getHeight() / 2.0f), this.mBorderPaint);
    }

    private void drawUpgrading(Canvas canvas) {
        canvas.drawCircle(this.mWidth / 2.0f, this.mHeight / 2.0f, this.waveRadius, this.mWavePaint);
        int i = this.mProgressValue;
        if (i > 0) {
            canvas.drawArc(this.oval, -90.0f, (i * 360.0f) / this.mTotalProgress, false, this.mBorderPaint);
        }
        CommonLogUtil.d("drawUpgrading progress = " + this.mProgressValue);
        canvas.drawText(this.mProgressValue + "%", this.mWidth / 2, (this.mHeight * 2) / 5, this.mTopTitlePaint);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mWidth = getWidth();
        this.mHeight = getHeight();
        updateWaveShader();
    }

    private void updateWaveShader() {
        int measuredWidth = getMeasuredWidth();
        float measuredHeight = getMeasuredHeight();
        float f2 = this.mStrokeWidth;
        int i = (int) (measuredHeight + f2);
        if (measuredWidth <= 0 || i <= 0) {
            return;
        }
        double d2 = 6.283185307179586d / ((double) measuredWidth);
        float f3 = i;
        float f4 = 0.1f * f3;
        this.mDefaultWaterLevel = ((f3 * 0.5f) - (f2 * 2.0f)) + f2 + 3.0f;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(measuredWidth, i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        paint.setStrokeWidth(2.0f);
        paint.setAntiAlias(true);
        int i2 = measuredWidth + 1;
        int i3 = i + 1;
        float[] fArr = new float[i2];
        paint.setColor(adjustAlpha(this.mWaveColor, 0.3f));
        int i4 = 0;
        while (i4 < i2) {
            double d3 = d2;
            float fSin = (float) (((double) this.mDefaultWaterLevel) + (((double) f4) * Math.sin(((double) i4) * d2)));
            float f5 = i4;
            int i5 = i4;
            canvas.drawLine(f5, fSin, f5, i3, paint);
            fArr[i5] = fSin;
            i4 = i5 + 1;
            i2 = i2;
            d2 = d3;
        }
        int i6 = i2;
        paint.setColor(this.mWaveColor);
        int i7 = measuredWidth / 4;
        for (int i8 = 0; i8 < i6; i8++) {
            float f6 = i8;
            canvas.drawLine(f6, fArr[(i8 + i7) % i6], f6, i3, paint);
        }
        this.mWaveShader = new BitmapShader(bitmapCreateBitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
        this.mWavePaint.setShader(this.mWaveShader);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int iMeasureWidth = measureWidth(i);
        int iMeasureHeight = measureHeight(i2);
        if (iMeasureWidth >= iMeasureHeight) {
            iMeasureWidth = iMeasureHeight;
        }
        setMeasuredDimension(iMeasureWidth, iMeasureWidth);
        this.mWidth = getWidth();
        this.mHeight = getHeight();
        this.mRingRadius = (getMeasuredWidth() / 2.0f) - (this.mStrokeWidth / 2.0f);
        this.waveRadius = (getMeasuredWidth() / 2.0f) - (this.mStrokeWidth * 3.0f);
    }

    private int measureWidth(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        return (mode == 1073741824 || mode == Integer.MIN_VALUE) ? size : this.mWidth;
    }

    private int measureHeight(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode != 1073741824 && mode != Integer.MIN_VALUE) {
            size = this.mHeight;
        }
        return size + 2;
    }

    public void setProgressValue(int i) {
        if (i == 100) {
            cancelWaveShiftAnimation();
        } else {
            startWaveShiftAnimation();
        }
        ObjectAnimator objectAnimator = this.waterLevelAnim;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        this.mProgressValue = i;
        startWaveAnim(1500L, this.mProgressValue / this.mTotalProgress, null);
    }

    private void startWaveAnim(long j, float f2, AnimatorListenerAdapter animatorListenerAdapter) {
        if (this.waterLevelAnim == null) {
            this.waterLevelAnim = ObjectAnimator.ofFloat(this, "waterLevelRatio", this.mWaterLevelRatio);
            this.waterLevelAnim.setInterpolator(new DecelerateInterpolator());
        }
        if (this.waterLevelAnim.isRunning()) {
            this.waterLevelAnim.end();
        }
        this.waterLevelAnim.setDuration(j);
        this.waterLevelAnim.setFloatValues(f2);
        if (animatorListenerAdapter != null) {
            this.waterLevelAnim.addListener(animatorListenerAdapter);
        }
        this.waterLevelAnim.start();
    }

    private void waveQuitAnim() {
        if (this.mProgressValue == 0) {
            return;
        }
        startWaveAnim(800L, 0.0f, new AnimatorListenerAdapter() { // from class: com.ido.life.customview.WaveLoadingView.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                WaveLoadingView.this.invalidate();
            }
        });
    }

    public void setWaterLevelRatio(float f2) {
        if (Math.abs(this.mWaterLevelRatio - f2) >= 1.0E-6d) {
            this.mWaterLevelRatio = f2;
            invalidate();
        }
    }

    public float getWaterLevelRatio() {
        return this.mWaterLevelRatio;
    }

    public int getProgressValue() {
        return this.mProgressValue;
    }

    public void setWaveShiftRatio(float f2) {
        if (this.mWaveShiftRatio != f2) {
            this.mWaveShiftRatio = f2;
            invalidate();
        }
    }

    public float getWaveShiftRatio() {
        return this.mWaveShiftRatio;
    }

    public void startWaveShiftAnimation() {
        if (this.waveShiftAnim == null) {
            initAnimation();
        }
        ObjectAnimator objectAnimator = this.waveShiftAnim;
        if (objectAnimator == null || objectAnimator.isRunning()) {
            return;
        }
        this.waveShiftAnim.start();
    }

    public void endWaveShiftAnimation() {
        ObjectAnimator objectAnimator = this.waveShiftAnim;
        if (objectAnimator != null) {
            objectAnimator.end();
        }
    }

    public void cancelWaveShiftAnimation() {
        ObjectAnimator objectAnimator = this.waveShiftAnim;
        if (objectAnimator == null || !objectAnimator.isRunning()) {
            return;
        }
        this.waveShiftAnim.cancel();
    }

    public void pauseAnimation() {
        ObjectAnimator objectAnimator;
        if (Build.VERSION.SDK_INT < 19 || (objectAnimator = this.waveShiftAnim) == null) {
            return;
        }
        objectAnimator.pause();
    }

    public void resumeAnimation() {
        ObjectAnimator objectAnimator;
        if (Build.VERSION.SDK_INT < 19 || (objectAnimator = this.waveShiftAnim) == null) {
            return;
        }
        objectAnimator.resume();
    }

    public void setAnimDuration(long j) {
        this.waveShiftAnim.setDuration(j);
    }

    private void initAnimation() {
        this.waveShiftAnim = ObjectAnimator.ofFloat(this, "waveShiftRatio", 0.0f, 1.0f);
        this.waveShiftAnim.setRepeatCount(-1);
        this.waveShiftAnim.setDuration(1000L);
        this.waveShiftAnim.setInterpolator(new LinearInterpolator());
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        startWaveShiftAnimation();
        super.onAttachedToWindow();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        cancelWaveShiftAnimation();
        super.onDetachedFromWindow();
    }

    private int adjustAlpha(int i, float f2) {
        return Color.argb(Math.round(Color.alpha(i) * f2), Color.red(i), Color.green(i), Color.blue(i));
    }

    private int sp2px(float f2) {
        return (int) ((f2 * this.mContext.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    private int dp2px(float f2) {
        return (int) ((f2 * this.mContext.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private Path getEquilateralTriangle(Point point, int i, int i2, int i3) {
        Point point2;
        Point point3;
        Point point4 = null;
        if (i3 == 0) {
            point4 = new Point(point.x + i, point.y);
            int i4 = point.x + (i / 2);
            double d2 = i2;
            point3 = new Point(i4, (int) (d2 - ((Math.sqrt(3.0d) / 2.0d) * d2)));
        } else if (i3 == 1) {
            point4 = new Point(point.x, point.y - i2);
            point3 = new Point(point.x + i, point.y - i2);
            point.x += i / 2;
            point.y = (int) ((Math.sqrt(3.0d) / 2.0d) * ((double) i2));
        } else {
            if (i3 == 2) {
                point4 = new Point(point.x, point.y - i2);
                point2 = new Point((int) ((Math.sqrt(3.0d) / 2.0d) * ((double) i)), point.y / 2);
            } else if (i3 == 3) {
                point4 = new Point(point.x + i, point.y - i2);
                point2 = new Point(point.x + i, point.y);
                double d3 = i;
                point.x = (int) (d3 - ((Math.sqrt(3.0d) / 2.0d) * d3));
                point.y /= 2;
            } else {
                point2 = null;
            }
            Path path = new Path();
            path.moveTo(point.x, point.y);
            path.lineTo(point4.x, point4.y);
            path.lineTo(point2.x, point2.y);
            return path;
        }
        point2 = point3;
        Path path2 = new Path();
        path2.moveTo(point.x, point.y);
        path2.lineTo(point4.x, point4.y);
        path2.lineTo(point2.x, point2.y);
        return path2;
    }
}