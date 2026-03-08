package com.ido.life.customview.cardview;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
class RoundRectDrawableWithShadow extends Drawable {
    private static final double COS_45 = Math.cos(Math.toRadians(45.0d));
    private static final float SHADOW_MULTIPLIER = 1.5f;
    static RoundRectHelper sRoundRectHelper;
    private ColorStateList mBackground;
    private final RectF mCardBounds;
    private float mCornerRadius;
    private Paint mCornerShadowPaint;
    private Path mCornerShadowPath;
    private Paint mEdgeShadowPaint;
    private float mRawMaxShadowSize;
    private float mRawShadowSize;
    private int mShadowEndColor;
    private float mShadowSize;
    private int mShadowStartColor;
    private boolean mDirty = true;
    private boolean mAddPaddingForCorners = true;
    private boolean mPrintedShadowClipWarning = false;
    private final int mInsetShadow = 2;
    private Paint mPaint = new Paint(5);

    interface RoundRectHelper {
        void drawRoundRect(Canvas canvas, RectF rectF, float f2, Paint paint);
    }

    private void drawShadow(Canvas canvas) {
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    RoundRectDrawableWithShadow(Resources resources, ColorStateList colorStateList, float f2, float f3, float f4) {
        this.mShadowStartColor = resources.getColor(R.color.cardview_shadow_start_color);
        this.mShadowEndColor = resources.getColor(R.color.cardview_shadow_end_color);
        setBackground(colorStateList);
        this.mCornerShadowPaint = new Paint(5);
        this.mCornerShadowPaint.setStyle(Paint.Style.FILL);
        this.mCornerRadius = (int) (f2 + 0.5f);
        this.mCardBounds = new RectF();
        this.mEdgeShadowPaint = new Paint(this.mCornerShadowPaint);
        this.mEdgeShadowPaint.setAntiAlias(false);
        setShadowSize(f3, f4);
    }

    private void setBackground(ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        this.mBackground = colorStateList;
        this.mPaint.setColor(this.mBackground.getColorForState(getState(), this.mBackground.getDefaultColor()));
    }

    private int toEven(float f2) {
        int i = (int) (f2 + 0.5f);
        return i % 2 == 1 ? i - 1 : i;
    }

    void setAddPaddingForCorners(boolean z) {
        this.mAddPaddingForCorners = z;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.mPaint.setAlpha(i);
        this.mCornerShadowPaint.setAlpha(i);
        this.mEdgeShadowPaint.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.mDirty = true;
    }

    private void setShadowSize(float f2, float f3) {
        if (f2 < 0.0f) {
            throw new IllegalArgumentException("Invalid shadow size " + f2 + ". Must be >= 0");
        }
        if (f3 < 0.0f) {
            throw new IllegalArgumentException("Invalid max shadow size " + f3 + ". Must be >= 0");
        }
        float even = toEven(f2);
        float even2 = toEven(f3);
        if (even > even2) {
            if (!this.mPrintedShadowClipWarning) {
                this.mPrintedShadowClipWarning = true;
            }
            even = even2;
        }
        if (this.mRawShadowSize == even && this.mRawMaxShadowSize == even2) {
            return;
        }
        this.mRawShadowSize = even;
        this.mRawMaxShadowSize = even2;
        this.mShadowSize = (int) ((even * SHADOW_MULTIPLIER) + this.mInsetShadow + 0.5f);
        this.mDirty = true;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        int iCeil = (int) Math.ceil(calculateVerticalPadding(this.mRawMaxShadowSize, this.mCornerRadius, this.mAddPaddingForCorners));
        int iCeil2 = (int) Math.ceil(calculateHorizontalPadding(this.mRawMaxShadowSize, this.mCornerRadius, this.mAddPaddingForCorners));
        rect.set(iCeil2, iCeil, iCeil2, iCeil);
        return true;
    }

    static float calculateVerticalPadding(float f2, float f3, boolean z) {
        return z ? (float) (((double) (f2 * SHADOW_MULTIPLIER)) + ((1.0d - COS_45) * ((double) f3))) : f2 * SHADOW_MULTIPLIER;
    }

    static float calculateHorizontalPadding(float f2, float f3, boolean z) {
        return z ? (float) (((double) f2) + ((1.0d - COS_45) * ((double) f3))) : f2;
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        ColorStateList colorStateList = this.mBackground;
        int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
        if (this.mPaint.getColor() == colorForState) {
            return false;
        }
        this.mPaint.setColor(colorForState);
        this.mDirty = true;
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList = this.mBackground;
        return (colorStateList != null && colorStateList.isStateful()) || super.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    void setCornerRadius(float f2) {
        if (f2 < 0.0f) {
            throw new IllegalArgumentException("Invalid radius " + f2 + ". Must be >= 0");
        }
        float f3 = (int) (f2 + 0.5f);
        if (this.mCornerRadius == f3) {
            return;
        }
        this.mCornerRadius = f3;
        this.mDirty = true;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.mDirty) {
            buildComponents(getBounds());
            this.mDirty = false;
        }
        canvas.translate(0.0f, this.mRawShadowSize / 2.0f);
        drawShadow(canvas);
        canvas.translate(0.0f, (-this.mRawShadowSize) / 2.0f);
        sRoundRectHelper.drawRoundRect(canvas, this.mCardBounds, this.mCornerRadius, this.mPaint);
    }

    private void buildShadowCorners() {
        float f2 = this.mCornerRadius;
        RectF rectF = new RectF(-f2, -f2, f2, f2);
        RectF rectF2 = new RectF(rectF);
        float f3 = this.mShadowSize;
        rectF2.inset(-f3, -f3);
        Path path = this.mCornerShadowPath;
        if (path == null) {
            this.mCornerShadowPath = new Path();
        } else {
            path.reset();
        }
        this.mCornerShadowPath.setFillType(Path.FillType.EVEN_ODD);
        this.mCornerShadowPath.moveTo(-this.mCornerRadius, 0.0f);
        this.mCornerShadowPath.rLineTo(-this.mShadowSize, 0.0f);
        this.mCornerShadowPath.arcTo(rectF2, 180.0f, 90.0f, false);
        this.mCornerShadowPath.arcTo(rectF, 270.0f, -90.0f, false);
        this.mCornerShadowPath.close();
        float f4 = this.mCornerRadius;
        float f5 = this.mShadowSize;
        float f6 = f4 / (f4 + f5);
        Paint paint = this.mCornerShadowPaint;
        float f7 = f4 + f5;
        int i = this.mShadowStartColor;
        paint.setShader(new RadialGradient(0.0f, 0.0f, f7, new int[]{i, i, this.mShadowEndColor}, new float[]{0.0f, f6, 1.0f}, Shader.TileMode.CLAMP));
        Paint paint2 = this.mEdgeShadowPaint;
        float f8 = this.mCornerRadius;
        float f9 = this.mShadowSize;
        paint2.setShader(new LinearGradient(0.0f, (-f8) + f9, 0.0f, (-f8) - f9, new int[]{this.mShadowStartColor, this.mShadowEndColor}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP));
        this.mEdgeShadowPaint.setAntiAlias(false);
    }

    private void buildComponents(Rect rect) {
        float f2 = this.mRawMaxShadowSize * SHADOW_MULTIPLIER;
        this.mCardBounds.set(rect.left + this.mRawMaxShadowSize, rect.top + f2, rect.right - this.mRawMaxShadowSize, rect.bottom - f2);
        buildShadowCorners();
    }

    float getCornerRadius() {
        return this.mCornerRadius;
    }

    void getMaxShadowAndCornerPadding(Rect rect) {
        getPadding(rect);
    }

    void setShadowSize(float f2) {
        setShadowSize(f2, this.mRawMaxShadowSize);
    }

    void setMaxShadowSize(float f2) {
        setShadowSize(this.mRawShadowSize, f2);
    }

    float getShadowSize() {
        return this.mRawShadowSize;
    }

    float getMaxShadowSize() {
        return this.mRawMaxShadowSize;
    }

    float getMinWidth() {
        float f2 = this.mRawMaxShadowSize;
        return (Math.max(f2, this.mCornerRadius + this.mInsetShadow + (f2 / 2.0f)) * 2.0f) + ((this.mRawMaxShadowSize + this.mInsetShadow) * 2.0f);
    }

    float getMinHeight() {
        float f2 = this.mRawMaxShadowSize;
        return (Math.max(f2, this.mCornerRadius + this.mInsetShadow + ((f2 * SHADOW_MULTIPLIER) / 2.0f)) * 2.0f) + (((this.mRawMaxShadowSize * SHADOW_MULTIPLIER) + this.mInsetShadow) * 2.0f);
    }

    void setColor(ColorStateList colorStateList) {
        setBackground(colorStateList);
        invalidateSelf();
    }

    public int getShadowStartColor() {
        return this.mShadowStartColor;
    }

    public int getShadowEndColor() {
        return this.mShadowEndColor;
    }

    public void setShadowStartColor(int i) {
        this.mShadowStartColor = i;
    }

    public void setShadowEndColor(int i) {
        this.mShadowEndColor = i;
    }

    ColorStateList getColor() {
        return this.mBackground;
    }
}