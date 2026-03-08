package com.ido.life.customview.shimmer;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes2.dex */
public final class ShimmerDrawable extends Drawable {
    private Shimmer mShimmer;
    private ValueAnimator mValueAnimator;
    private final ValueAnimator.AnimatorUpdateListener mUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.life.customview.shimmer.ShimmerDrawable.1
        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            ShimmerDrawable.this.invalidateSelf();
        }
    };
    private final Paint mShimmerPaint = new Paint();
    private final Rect mDrawRect = new Rect();
    private final Matrix mShaderMatrix = new Matrix();

    private float offset(float f2, float f3, float f4) {
        return f2 + ((f3 - f2) * f4);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public ShimmerDrawable() {
        this.mShimmerPaint.setAntiAlias(true);
    }

    public void setShimmer(Shimmer shimmer) {
        this.mShimmer = shimmer;
        Shimmer shimmer2 = this.mShimmer;
        if (shimmer2 != null) {
            this.mShimmerPaint.setXfermode(new PorterDuffXfermode(shimmer2.alphaShimmer ? PorterDuff.Mode.DST_IN : PorterDuff.Mode.SRC_IN));
        }
        updateShader();
        updateValueAnimator();
        invalidateSelf();
    }

    public void startShimmer() {
        if (this.mValueAnimator == null || isShimmerStarted() || getCallback() == null) {
            return;
        }
        this.mValueAnimator.start();
    }

    public void stopShimmer() {
        if (this.mValueAnimator == null || !isShimmerStarted()) {
            return;
        }
        this.mValueAnimator.cancel();
    }

    public boolean isShimmerStarted() {
        ValueAnimator valueAnimator = this.mValueAnimator;
        return valueAnimator != null && valueAnimator.isStarted();
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.mDrawRect.set(0, 0, rect.width(), rect.height());
        updateShader();
        maybeStartShimmer();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        float fOffset;
        float fOffset2;
        if (this.mShimmer == null || this.mShimmerPaint.getShader() == null) {
            return;
        }
        float fTan = (float) Math.tan(Math.toRadians(this.mShimmer.tilt));
        float fHeight = this.mDrawRect.height() + (this.mDrawRect.width() * fTan);
        float fWidth = this.mDrawRect.width() + (fTan * this.mDrawRect.height());
        ValueAnimator valueAnimator = this.mValueAnimator;
        float f2 = 0.0f;
        float animatedFraction = valueAnimator != null ? valueAnimator.getAnimatedFraction() : 0.0f;
        int i = this.mShimmer.direction;
        if (i == 1) {
            fOffset = offset(-fHeight, fHeight, animatedFraction);
        } else {
            if (i == 2) {
                fOffset2 = offset(fWidth, -fWidth, animatedFraction);
            } else if (i != 3) {
                fOffset2 = offset(-fWidth, fWidth, animatedFraction);
            } else {
                fOffset = offset(fHeight, -fHeight, animatedFraction);
            }
            this.mShaderMatrix.reset();
            this.mShaderMatrix.setRotate(this.mShimmer.tilt, this.mDrawRect.width() / 2.0f, this.mDrawRect.height() / 2.0f);
            this.mShaderMatrix.postTranslate(fOffset2, f2);
            this.mShimmerPaint.getShader().setLocalMatrix(this.mShaderMatrix);
            canvas.drawRect(this.mDrawRect, this.mShimmerPaint);
        }
        f2 = fOffset;
        fOffset2 = 0.0f;
        this.mShaderMatrix.reset();
        this.mShaderMatrix.setRotate(this.mShimmer.tilt, this.mDrawRect.width() / 2.0f, this.mDrawRect.height() / 2.0f);
        this.mShaderMatrix.postTranslate(fOffset2, f2);
        this.mShimmerPaint.getShader().setLocalMatrix(this.mShaderMatrix);
        canvas.drawRect(this.mDrawRect, this.mShimmerPaint);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Shimmer shimmer = this.mShimmer;
        return (shimmer == null || !(shimmer.clipToChildren || this.mShimmer.alphaShimmer)) ? -1 : -3;
    }

    private void updateValueAnimator() {
        boolean zIsStarted;
        if (this.mShimmer == null) {
            return;
        }
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator != null) {
            zIsStarted = valueAnimator.isStarted();
            this.mValueAnimator.cancel();
            this.mValueAnimator.removeAllUpdateListeners();
        } else {
            zIsStarted = false;
        }
        this.mValueAnimator = ValueAnimator.ofFloat(0.0f, (this.mShimmer.repeatDelay / this.mShimmer.animationDuration) + 1.0f);
        this.mValueAnimator.setRepeatMode(this.mShimmer.repeatMode);
        this.mValueAnimator.setRepeatCount(this.mShimmer.repeatCount);
        this.mValueAnimator.setDuration(this.mShimmer.animationDuration + this.mShimmer.repeatDelay);
        this.mValueAnimator.addUpdateListener(this.mUpdateListener);
        if (zIsStarted) {
            this.mValueAnimator.start();
        }
    }

    void maybeStartShimmer() {
        Shimmer shimmer;
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator == null || valueAnimator.isStarted() || (shimmer = this.mShimmer) == null || !shimmer.autoStart || getCallback() == null) {
            return;
        }
        this.mValueAnimator.start();
    }

    private void updateShader() {
        Shimmer shimmer;
        Shader radialGradient;
        Rect bounds = getBounds();
        int iWidth = bounds.width();
        int iHeight = bounds.height();
        if (iWidth == 0 || iHeight == 0 || (shimmer = this.mShimmer) == null) {
            return;
        }
        int iWidth2 = shimmer.width(iWidth);
        int iHeight2 = this.mShimmer.height(iHeight);
        boolean z = true;
        if (this.mShimmer.shape != 1) {
            if (this.mShimmer.direction != 1 && this.mShimmer.direction != 3) {
                z = false;
            }
            if (z) {
                iWidth2 = 0;
            }
            if (!z) {
                iHeight2 = 0;
            }
            radialGradient = new LinearGradient(0.0f, 0.0f, iWidth2, iHeight2, this.mShimmer.colors, this.mShimmer.positions, Shader.TileMode.CLAMP);
        } else {
            radialGradient = new RadialGradient(iWidth2 / 2.0f, iHeight2 / 2.0f, (float) (((double) Math.max(iWidth2, iHeight2)) / Math.sqrt(2.0d)), this.mShimmer.colors, this.mShimmer.positions, Shader.TileMode.CLAMP);
        }
        this.mShimmerPaint.setShader(radialGradient);
    }
}