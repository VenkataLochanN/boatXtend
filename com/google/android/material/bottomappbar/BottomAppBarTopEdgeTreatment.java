package com.google.android.material.bottomappbar;

import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.ShapePath;

/* JADX INFO: loaded from: classes2.dex */
public class BottomAppBarTopEdgeTreatment extends EdgeTreatment implements Cloneable {
    private static final int ANGLE_LEFT = 180;
    private static final int ANGLE_UP = 270;
    private static final int ARC_HALF = 180;
    private static final int ARC_QUARTER = 90;
    private float cradleVerticalOffset;
    private float fabDiameter;
    private float fabMargin;
    private float horizontalOffset;
    private float roundedCornerRadius;

    public BottomAppBarTopEdgeTreatment(float f2, float f3, float f4) {
        this.fabMargin = f2;
        this.roundedCornerRadius = f3;
        setCradleVerticalOffset(f4);
        this.horizontalOffset = 0.0f;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public void getEdgePath(float f2, float f3, float f4, ShapePath shapePath) {
        float f5 = this.fabDiameter;
        if (f5 == 0.0f) {
            shapePath.lineTo(f2, 0.0f);
            return;
        }
        float f6 = ((this.fabMargin * 2.0f) + f5) / 2.0f;
        float f7 = f4 * this.roundedCornerRadius;
        float f8 = f3 + this.horizontalOffset;
        float f9 = (this.cradleVerticalOffset * f4) + ((1.0f - f4) * f6);
        if (f9 / f6 >= 1.0f) {
            shapePath.lineTo(f2, 0.0f);
            return;
        }
        float f10 = f6 + f7;
        float f11 = f9 + f7;
        float fSqrt = (float) Math.sqrt((f10 * f10) - (f11 * f11));
        float f12 = f8 - fSqrt;
        float f13 = f8 + fSqrt;
        float degrees = (float) Math.toDegrees(Math.atan(fSqrt / f11));
        float f14 = 90.0f - degrees;
        shapePath.lineTo(f12, 0.0f);
        float f15 = f7 * 2.0f;
        shapePath.addArc(f12 - f7, 0.0f, f12 + f7, f15, 270.0f, degrees);
        shapePath.addArc(f8 - f6, (-f6) - f9, f8 + f6, f6 - f9, 180.0f - f14, (f14 * 2.0f) - 180.0f);
        shapePath.addArc(f13 - f7, 0.0f, f13 + f7, f15, 270.0f - degrees, degrees);
        shapePath.lineTo(f2, 0.0f);
    }

    public float getFabDiameter() {
        return this.fabDiameter;
    }

    public void setFabDiameter(float f2) {
        this.fabDiameter = f2;
    }

    void setHorizontalOffset(float f2) {
        this.horizontalOffset = f2;
    }

    public float getHorizontalOffset() {
        return this.horizontalOffset;
    }

    float getCradleVerticalOffset() {
        return this.cradleVerticalOffset;
    }

    void setCradleVerticalOffset(float f2) {
        if (f2 < 0.0f) {
            throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
        }
        this.cradleVerticalOffset = f2;
    }

    float getFabCradleMargin() {
        return this.fabMargin;
    }

    void setFabCradleMargin(float f2) {
        this.fabMargin = f2;
    }

    float getFabCradleRoundedCornerRadius() {
        return this.roundedCornerRadius;
    }

    void setFabCradleRoundedCornerRadius(float f2) {
        this.roundedCornerRadius = f2;
    }
}