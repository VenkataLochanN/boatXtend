package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.google.android.material.progressindicator.BaseProgressIndicatorSpec;

/* JADX INFO: loaded from: classes2.dex */
abstract class DrawingDelegate<S extends BaseProgressIndicatorSpec> {
    protected DrawableWithAnimatedVisibilityChange drawable;
    S spec;

    abstract void adjustCanvas(Canvas canvas, float f2);

    abstract void fillIndicator(Canvas canvas, Paint paint, float f2, float f3, int i);

    abstract void fillTrack(Canvas canvas, Paint paint);

    abstract int getPreferredHeight();

    abstract int getPreferredWidth();

    public DrawingDelegate(S s) {
        this.spec = s;
    }

    protected void registerDrawable(DrawableWithAnimatedVisibilityChange drawableWithAnimatedVisibilityChange) {
        this.drawable = drawableWithAnimatedVisibilityChange;
    }

    void validateSpecAndAdjustCanvas(Canvas canvas, float f2) {
        this.spec.validateSpec();
        adjustCanvas(canvas, f2);
    }
}