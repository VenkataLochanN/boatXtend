package com.google.android.material.shadow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import androidx.appcompat.graphics.drawable.DrawableWrapper;
import androidx.core.content.ContextCompat;
import com.google.android.material.R;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class ShadowDrawableWrapper extends DrawableWrapper {
    static final double COS_45 = Math.cos(Math.toRadians(45.0d));
    static final float SHADOW_BOTTOM_SCALE = 1.0f;
    static final float SHADOW_HORIZ_SCALE = 0.5f;
    static final float SHADOW_MULTIPLIER = 1.5f;
    static final float SHADOW_TOP_SCALE = 0.25f;
    private boolean addPaddingForCorners;
    final RectF contentBounds;
    float cornerRadius;
    final Paint cornerShadowPaint;
    Path cornerShadowPath;
    private boolean dirty;
    final Paint edgeShadowPaint;
    float maxShadowSize;
    private boolean printedShadowClipWarning;
    float rawMaxShadowSize;
    float rawShadowSize;
    private float rotation;
    private final int shadowEndColor;
    private final int shadowMiddleColor;
    float shadowSize;
    private final int shadowStartColor;

    @Override // androidx.appcompat.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public ShadowDrawableWrapper(Context context, Drawable drawable, float f2, float f3, float f4) {
        super(drawable);
        this.dirty = true;
        this.addPaddingForCorners = true;
        this.printedShadowClipWarning = false;
        this.shadowStartColor = ContextCompat.getColor(context, R.color.design_fab_shadow_start_color);
        this.shadowMiddleColor = ContextCompat.getColor(context, R.color.design_fab_shadow_mid_color);
        this.shadowEndColor = ContextCompat.getColor(context, R.color.design_fab_shadow_end_color);
        this.cornerShadowPaint = new Paint(5);
        this.cornerShadowPaint.setStyle(Paint.Style.FILL);
        this.cornerRadius = Math.round(f2);
        this.contentBounds = new RectF();
        this.edgeShadowPaint = new Paint(this.cornerShadowPaint);
        this.edgeShadowPaint.setAntiAlias(false);
        setShadowSize(f3, f4);
    }

    private static int toEven(float f2) {
        int iRound = Math.round(f2);
        return iRound % 2 == 1 ? iRound - 1 : iRound;
    }

    public void setAddPaddingForCorners(boolean z) {
        this.addPaddingForCorners = z;
        invalidateSelf();
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        super.setAlpha(i);
        this.cornerShadowPaint.setAlpha(i);
        this.edgeShadowPaint.setAlpha(i);
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        this.dirty = true;
    }

    public void setShadowSize(float f2, float f3) {
        if (f2 < 0.0f || f3 < 0.0f) {
            throw new IllegalArgumentException("invalid shadow size");
        }
        float even = toEven(f2);
        float even2 = toEven(f3);
        if (even > even2) {
            if (!this.printedShadowClipWarning) {
                this.printedShadowClipWarning = true;
            }
            even = even2;
        }
        if (this.rawShadowSize == even && this.rawMaxShadowSize == even2) {
            return;
        }
        this.rawShadowSize = even;
        this.rawMaxShadowSize = even2;
        this.shadowSize = Math.round(even * SHADOW_MULTIPLIER);
        this.maxShadowSize = even2;
        this.dirty = true;
        invalidateSelf();
    }

    public void setShadowSize(float f2) {
        setShadowSize(f2, this.rawMaxShadowSize);
    }

    public float getShadowSize() {
        return this.rawShadowSize;
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        int iCeil = (int) Math.ceil(calculateVerticalPadding(this.rawMaxShadowSize, this.cornerRadius, this.addPaddingForCorners));
        int iCeil2 = (int) Math.ceil(calculateHorizontalPadding(this.rawMaxShadowSize, this.cornerRadius, this.addPaddingForCorners));
        rect.set(iCeil2, iCeil, iCeil2, iCeil);
        return true;
    }

    public static float calculateVerticalPadding(float f2, float f3, boolean z) {
        return z ? (float) (((double) (f2 * SHADOW_MULTIPLIER)) + ((1.0d - COS_45) * ((double) f3))) : f2 * SHADOW_MULTIPLIER;
    }

    public static float calculateHorizontalPadding(float f2, float f3, boolean z) {
        return z ? (float) (((double) f2) + ((1.0d - COS_45) * ((double) f3))) : f2;
    }

    public void setCornerRadius(float f2) {
        float fRound = Math.round(f2);
        if (this.cornerRadius == fRound) {
            return;
        }
        this.cornerRadius = fRound;
        this.dirty = true;
        invalidateSelf();
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.dirty) {
            buildComponents(getBounds());
            this.dirty = false;
        }
        drawShadow(canvas);
        super.draw(canvas);
    }

    public final void setRotation(float f2) {
        if (this.rotation != f2) {
            this.rotation = f2;
            invalidateSelf();
        }
    }

    private void drawShadow(Canvas canvas) {
        int i;
        float f2;
        int i2;
        float f3;
        float f4;
        float f5;
        int iSave = canvas.save();
        canvas.rotate(this.rotation, this.contentBounds.centerX(), this.contentBounds.centerY());
        float f6 = this.cornerRadius;
        float f7 = (-f6) - this.shadowSize;
        float f8 = f6 * 2.0f;
        boolean z = this.contentBounds.width() - f8 > 0.0f;
        boolean z2 = this.contentBounds.height() - f8 > 0.0f;
        float f9 = this.rawShadowSize;
        float f10 = f6 / ((f9 - (0.5f * f9)) + f6);
        float f11 = f6 / ((f9 - (0.25f * f9)) + f6);
        float f12 = f6 / ((f9 - (f9 * 1.0f)) + f6);
        int iSave2 = canvas.save();
        canvas.translate(this.contentBounds.left + f6, this.contentBounds.top + f6);
        canvas.scale(f10, f11);
        canvas.drawPath(this.cornerShadowPath, this.cornerShadowPaint);
        if (z) {
            canvas.scale(1.0f / f10, 1.0f);
            i = iSave2;
            f2 = f12;
            i2 = iSave;
            f3 = f11;
            canvas.drawRect(0.0f, f7, this.contentBounds.width() - f8, -this.cornerRadius, this.edgeShadowPaint);
        } else {
            i = iSave2;
            f2 = f12;
            i2 = iSave;
            f3 = f11;
        }
        canvas.restoreToCount(i);
        int iSave3 = canvas.save();
        canvas.translate(this.contentBounds.right - f6, this.contentBounds.bottom - f6);
        float f13 = f2;
        canvas.scale(f10, f13);
        canvas.rotate(180.0f);
        canvas.drawPath(this.cornerShadowPath, this.cornerShadowPaint);
        if (z) {
            canvas.scale(1.0f / f10, 1.0f);
            f4 = f3;
            f5 = f13;
            canvas.drawRect(0.0f, f7, this.contentBounds.width() - f8, (-this.cornerRadius) + this.shadowSize, this.edgeShadowPaint);
        } else {
            f4 = f3;
            f5 = f13;
        }
        canvas.restoreToCount(iSave3);
        int iSave4 = canvas.save();
        canvas.translate(this.contentBounds.left + f6, this.contentBounds.bottom - f6);
        canvas.scale(f10, f5);
        canvas.rotate(270.0f);
        canvas.drawPath(this.cornerShadowPath, this.cornerShadowPaint);
        if (z2) {
            canvas.scale(1.0f / f5, 1.0f);
            canvas.drawRect(0.0f, f7, this.contentBounds.height() - f8, -this.cornerRadius, this.edgeShadowPaint);
        }
        canvas.restoreToCount(iSave4);
        int iSave5 = canvas.save();
        canvas.translate(this.contentBounds.right - f6, this.contentBounds.top + f6);
        float f14 = f4;
        canvas.scale(f10, f14);
        canvas.rotate(90.0f);
        canvas.drawPath(this.cornerShadowPath, this.cornerShadowPaint);
        if (z2) {
            canvas.scale(1.0f / f14, 1.0f);
            canvas.drawRect(0.0f, f7, this.contentBounds.height() - f8, -this.cornerRadius, this.edgeShadowPaint);
        }
        canvas.restoreToCount(iSave5);
        canvas.restoreToCount(i2);
    }

    private void buildShadowCorners() {
        float f2 = this.cornerRadius;
        RectF rectF = new RectF(-f2, -f2, f2, f2);
        RectF rectF2 = new RectF(rectF);
        float f3 = this.shadowSize;
        rectF2.inset(-f3, -f3);
        Path path = this.cornerShadowPath;
        if (path == null) {
            this.cornerShadowPath = new Path();
        } else {
            path.reset();
        }
        this.cornerShadowPath.setFillType(Path.FillType.EVEN_ODD);
        this.cornerShadowPath.moveTo(-this.cornerRadius, 0.0f);
        this.cornerShadowPath.rLineTo(-this.shadowSize, 0.0f);
        this.cornerShadowPath.arcTo(rectF2, 180.0f, 90.0f, false);
        this.cornerShadowPath.arcTo(rectF, 270.0f, -90.0f, false);
        this.cornerShadowPath.close();
        float f4 = -rectF2.top;
        if (f4 > 0.0f) {
            float f5 = this.cornerRadius / f4;
            this.cornerShadowPaint.setShader(new RadialGradient(0.0f, 0.0f, f4, new int[]{0, this.shadowStartColor, this.shadowMiddleColor, this.shadowEndColor}, new float[]{0.0f, f5, ((1.0f - f5) / 2.0f) + f5, 1.0f}, Shader.TileMode.CLAMP));
        }
        this.edgeShadowPaint.setShader(new LinearGradient(0.0f, rectF.top, 0.0f, rectF2.top, new int[]{this.shadowStartColor, this.shadowMiddleColor, this.shadowEndColor}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP));
        this.edgeShadowPaint.setAntiAlias(false);
    }

    private void buildComponents(Rect rect) {
        float f2 = this.rawMaxShadowSize * SHADOW_MULTIPLIER;
        this.contentBounds.set(rect.left + this.rawMaxShadowSize, rect.top + f2, rect.right - this.rawMaxShadowSize, rect.bottom - f2);
        getWrappedDrawable().setBounds((int) this.contentBounds.left, (int) this.contentBounds.top, (int) this.contentBounds.right, (int) this.contentBounds.bottom);
        buildShadowCorners();
    }

    public float getCornerRadius() {
        return this.cornerRadius;
    }

    public void setMaxShadowSize(float f2) {
        setShadowSize(this.rawShadowSize, f2);
    }

    public float getMaxShadowSize() {
        return this.rawMaxShadowSize;
    }

    public float getMinWidth() {
        float f2 = this.rawMaxShadowSize;
        return (Math.max(f2, this.cornerRadius + (f2 / 2.0f)) * 2.0f) + (this.rawMaxShadowSize * 2.0f);
    }

    public float getMinHeight() {
        float f2 = this.rawMaxShadowSize;
        return (Math.max(f2, this.cornerRadius + ((f2 * SHADOW_MULTIPLIER) / 2.0f)) * 2.0f) + (this.rawMaxShadowSize * SHADOW_MULTIPLIER * 2.0f);
    }
}