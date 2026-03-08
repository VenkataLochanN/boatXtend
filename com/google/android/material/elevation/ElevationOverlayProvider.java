package com.google.android.material.elevation;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialAttributes;

/* JADX INFO: loaded from: classes2.dex */
public class ElevationOverlayProvider {
    private static final float FORMULA_MULTIPLIER = 4.5f;
    private static final float FORMULA_OFFSET = 2.0f;
    private final int colorSurface;
    private final float displayDensity;
    private final int elevationOverlayColor;
    private final boolean elevationOverlayEnabled;

    public ElevationOverlayProvider(Context context) {
        this.elevationOverlayEnabled = MaterialAttributes.resolveBoolean(context, R.attr.elevationOverlayEnabled, false);
        this.elevationOverlayColor = MaterialColors.getColor(context, R.attr.elevationOverlayColor, 0);
        this.colorSurface = MaterialColors.getColor(context, R.attr.colorSurface, 0);
        this.displayDensity = context.getResources().getDisplayMetrics().density;
    }

    public int compositeOverlayWithThemeSurfaceColorIfNeeded(float f2, View view) {
        return compositeOverlayWithThemeSurfaceColorIfNeeded(f2 + getParentAbsoluteElevation(view));
    }

    public int compositeOverlayWithThemeSurfaceColorIfNeeded(float f2) {
        return compositeOverlayIfNeeded(this.colorSurface, f2);
    }

    public int compositeOverlayIfNeeded(int i, float f2, View view) {
        return compositeOverlayIfNeeded(i, f2 + getParentAbsoluteElevation(view));
    }

    public int compositeOverlayIfNeeded(int i, float f2) {
        return (this.elevationOverlayEnabled && isThemeSurfaceColor(i)) ? compositeOverlay(i, f2) : i;
    }

    public int compositeOverlay(int i, float f2, View view) {
        return compositeOverlay(i, f2 + getParentAbsoluteElevation(view));
    }

    public int compositeOverlay(int i, float f2) {
        float fCalculateOverlayAlphaFraction = calculateOverlayAlphaFraction(f2);
        return ColorUtils.setAlphaComponent(MaterialColors.layer(ColorUtils.setAlphaComponent(i, 255), this.elevationOverlayColor, fCalculateOverlayAlphaFraction), Color.alpha(i));
    }

    public int calculateOverlayAlpha(float f2) {
        return Math.round(calculateOverlayAlphaFraction(f2) * 255.0f);
    }

    public float calculateOverlayAlphaFraction(float f2) {
        if (this.displayDensity <= 0.0f || f2 <= 0.0f) {
            return 0.0f;
        }
        return Math.min(((((float) Math.log1p(f2 / r0)) * FORMULA_MULTIPLIER) + 2.0f) / 100.0f, 1.0f);
    }

    public boolean isThemeElevationOverlayEnabled() {
        return this.elevationOverlayEnabled;
    }

    public int getThemeElevationOverlayColor() {
        return this.elevationOverlayColor;
    }

    public int getThemeSurfaceColor() {
        return this.colorSurface;
    }

    public float getParentAbsoluteElevation(View view) {
        return ViewUtils.getParentAbsoluteElevation(view);
    }

    private boolean isThemeSurfaceColor(int i) {
        return ColorUtils.setAlphaComponent(i, 255) == this.colorSurface;
    }
}