package com.ido.life.customview.shimmer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import androidx.core.view.ViewCompat;
import com.ido.life.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public class Shimmer {
    private static final int COMPONENT_COUNT = 4;
    long repeatDelay;
    final float[] positions = new float[4];
    final int[] colors = new int[4];
    final RectF bounds = new RectF();
    int direction = 0;
    int highlightColor = -1;
    int baseColor = 1291845631;
    int shape = 0;
    int fixedWidth = 0;
    int fixedHeight = 0;
    float widthRatio = 1.0f;
    float heightRatio = 1.0f;
    float intensity = 0.0f;
    float dropoff = 0.5f;
    float tilt = 20.0f;
    boolean clipToChildren = true;
    boolean autoStart = true;
    boolean alphaShimmer = true;
    int repeatCount = -1;
    int repeatMode = 1;
    long animationDuration = 1000;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Direction {
        public static final int BOTTOM_TO_TOP = 3;
        public static final int LEFT_TO_RIGHT = 0;
        public static final int RIGHT_TO_LEFT = 2;
        public static final int TOP_TO_BOTTOM = 1;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Shape {
        public static final int LINEAR = 0;
        public static final int RADIAL = 1;
    }

    Shimmer() {
    }

    int width(int i) {
        int i2 = this.fixedWidth;
        return i2 > 0 ? i2 : Math.round(this.widthRatio * i);
    }

    int height(int i) {
        int i2 = this.fixedHeight;
        return i2 > 0 ? i2 : Math.round(this.heightRatio * i);
    }

    void updateColors() {
        if (this.shape != 1) {
            int[] iArr = this.colors;
            int i = this.baseColor;
            iArr[0] = i;
            int i2 = this.highlightColor;
            iArr[1] = i2;
            iArr[2] = i2;
            iArr[3] = i;
            return;
        }
        int[] iArr2 = this.colors;
        int i3 = this.highlightColor;
        iArr2[0] = i3;
        iArr2[1] = i3;
        int i4 = this.baseColor;
        iArr2[2] = i4;
        iArr2[3] = i4;
    }

    void updatePositions() {
        if (this.shape != 1) {
            this.positions[0] = Math.max(((1.0f - this.intensity) - this.dropoff) / 2.0f, 0.0f);
            this.positions[1] = Math.max(((1.0f - this.intensity) - 0.001f) / 2.0f, 0.0f);
            this.positions[2] = Math.min(((this.intensity + 1.0f) + 0.001f) / 2.0f, 1.0f);
            this.positions[3] = Math.min(((this.intensity + 1.0f) + this.dropoff) / 2.0f, 1.0f);
            return;
        }
        float[] fArr = this.positions;
        fArr[0] = 0.0f;
        fArr[1] = Math.min(this.intensity, 1.0f);
        this.positions[2] = Math.min(this.intensity + this.dropoff, 1.0f);
        this.positions[3] = 1.0f;
    }

    void updateBounds(int i, int i2) {
        double dMax = Math.max(i, i2);
        float f2 = -(Math.round(((float) ((dMax / Math.sin(1.5707963267948966d - Math.toRadians(this.tilt % 90.0f))) - dMax)) / 2.0f) * 3);
        this.bounds.set(f2, f2, width(i) + r0, height(i2) + r0);
    }

    public static abstract class Builder<T extends Builder<T>> {
        final Shimmer mShimmer = new Shimmer();

        protected abstract T getThis();

        public T consumeAttributes(Context context, AttributeSet attributeSet) {
            return (T) consumeAttributes(context.obtainStyledAttributes(attributeSet, R.styleable.ShimmerFrameLayout, 0, 0));
        }

        T consumeAttributes(TypedArray typedArray) {
            if (typedArray.hasValue(3)) {
                setClipToChildren(typedArray.getBoolean(3, this.mShimmer.clipToChildren));
            }
            if (typedArray.hasValue(0)) {
                setAutoStart(typedArray.getBoolean(0, this.mShimmer.autoStart));
            }
            if (typedArray.hasValue(1)) {
                setBaseAlpha(typedArray.getFloat(1, 0.3f));
            }
            if (typedArray.hasValue(11)) {
                setHighlightAlpha(typedArray.getFloat(11, 1.0f));
            }
            if (typedArray.hasValue(7)) {
                setDuration(typedArray.getInt(7, (int) this.mShimmer.animationDuration));
            }
            if (typedArray.hasValue(14)) {
                setRepeatCount(typedArray.getInt(14, this.mShimmer.repeatCount));
            }
            if (typedArray.hasValue(15)) {
                setRepeatDelay(typedArray.getInt(15, (int) this.mShimmer.repeatDelay));
            }
            if (typedArray.hasValue(16)) {
                setRepeatMode(typedArray.getInt(16, this.mShimmer.repeatMode));
            }
            if (typedArray.hasValue(5)) {
                int i = typedArray.getInt(5, this.mShimmer.direction);
                if (i == 1) {
                    setDirection(1);
                } else if (i == 2) {
                    setDirection(2);
                } else if (i != 3) {
                    setDirection(0);
                } else {
                    setDirection(3);
                }
            }
            if (typedArray.hasValue(17)) {
                if (typedArray.getInt(17, this.mShimmer.shape) != 1) {
                    setShape(0);
                } else {
                    setShape(1);
                }
            }
            if (typedArray.hasValue(6)) {
                setDropoff(typedArray.getFloat(6, this.mShimmer.dropoff));
            }
            if (typedArray.hasValue(9)) {
                setFixedWidth(typedArray.getDimensionPixelSize(9, this.mShimmer.fixedWidth));
            }
            if (typedArray.hasValue(8)) {
                setFixedHeight(typedArray.getDimensionPixelSize(8, this.mShimmer.fixedHeight));
            }
            if (typedArray.hasValue(13)) {
                setIntensity(typedArray.getFloat(13, this.mShimmer.intensity));
            }
            if (typedArray.hasValue(19)) {
                setWidthRatio(typedArray.getFloat(19, this.mShimmer.widthRatio));
            }
            if (typedArray.hasValue(10)) {
                setHeightRatio(typedArray.getFloat(10, this.mShimmer.heightRatio));
            }
            if (typedArray.hasValue(18)) {
                setTilt(typedArray.getFloat(18, this.mShimmer.tilt));
            }
            return (T) getThis();
        }

        public T setDirection(int i) {
            this.mShimmer.direction = i;
            return (T) getThis();
        }

        public T setShape(int i) {
            this.mShimmer.shape = i;
            return (T) getThis();
        }

        public T setFixedWidth(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("Given invalid width: " + i);
            }
            this.mShimmer.fixedWidth = i;
            return (T) getThis();
        }

        public T setFixedHeight(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("Given invalid height: " + i);
            }
            this.mShimmer.fixedHeight = i;
            return (T) getThis();
        }

        public T setWidthRatio(float f2) {
            if (f2 < 0.0f) {
                throw new IllegalArgumentException("Given invalid width ratio: " + f2);
            }
            this.mShimmer.widthRatio = f2;
            return (T) getThis();
        }

        public T setHeightRatio(float f2) {
            if (f2 < 0.0f) {
                throw new IllegalArgumentException("Given invalid height ratio: " + f2);
            }
            this.mShimmer.heightRatio = f2;
            return (T) getThis();
        }

        public T setIntensity(float f2) {
            if (f2 < 0.0f) {
                throw new IllegalArgumentException("Given invalid intensity value: " + f2);
            }
            this.mShimmer.intensity = f2;
            return (T) getThis();
        }

        public T setDropoff(float f2) {
            if (f2 < 0.0f) {
                throw new IllegalArgumentException("Given invalid dropoff value: " + f2);
            }
            this.mShimmer.dropoff = f2;
            return (T) getThis();
        }

        public T setTilt(float f2) {
            this.mShimmer.tilt = f2;
            return (T) getThis();
        }

        public T setBaseAlpha(float f2) {
            int iClamp = (int) (clamp(0.0f, 1.0f, f2) * 255.0f);
            Shimmer shimmer = this.mShimmer;
            shimmer.baseColor = (iClamp << 24) | (shimmer.baseColor & ViewCompat.MEASURED_SIZE_MASK);
            return (T) getThis();
        }

        public T setHighlightAlpha(float f2) {
            int iClamp = (int) (clamp(0.0f, 1.0f, f2) * 255.0f);
            Shimmer shimmer = this.mShimmer;
            shimmer.highlightColor = (iClamp << 24) | (shimmer.highlightColor & ViewCompat.MEASURED_SIZE_MASK);
            return (T) getThis();
        }

        public T setClipToChildren(boolean z) {
            this.mShimmer.clipToChildren = z;
            return (T) getThis();
        }

        public T setAutoStart(boolean z) {
            this.mShimmer.autoStart = z;
            return (T) getThis();
        }

        public T setRepeatCount(int i) {
            this.mShimmer.repeatCount = i;
            return (T) getThis();
        }

        public T setRepeatMode(int i) {
            this.mShimmer.repeatMode = i;
            return (T) getThis();
        }

        public T setRepeatDelay(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("Given a negative repeat delay: " + j);
            }
            this.mShimmer.repeatDelay = j;
            return (T) getThis();
        }

        public T setDuration(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("Given a negative duration: " + j);
            }
            this.mShimmer.animationDuration = j;
            return (T) getThis();
        }

        public Shimmer build() {
            this.mShimmer.updateColors();
            this.mShimmer.updatePositions();
            return this.mShimmer;
        }

        private static float clamp(float f2, float f3, float f4) {
            return Math.min(f3, Math.max(f2, f4));
        }
    }

    public static class AlphaHighlightBuilder extends Builder<AlphaHighlightBuilder> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.ido.life.customview.shimmer.Shimmer.Builder
        public AlphaHighlightBuilder getThis() {
            return this;
        }

        public AlphaHighlightBuilder() {
            this.mShimmer.alphaShimmer = true;
        }
    }

    public static class ColorHighlightBuilder extends Builder<ColorHighlightBuilder> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.ido.life.customview.shimmer.Shimmer.Builder
        public ColorHighlightBuilder getThis() {
            return this;
        }

        public ColorHighlightBuilder() {
            this.mShimmer.alphaShimmer = false;
        }

        public ColorHighlightBuilder setHighlightColor(int i) {
            this.mShimmer.highlightColor = i;
            return getThis();
        }

        public ColorHighlightBuilder setBaseColor(int i) {
            this.mShimmer.baseColor = (i & ViewCompat.MEASURED_SIZE_MASK) | (this.mShimmer.baseColor & ViewCompat.MEASURED_STATE_MASK);
            return getThis();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.ido.life.customview.shimmer.Shimmer.Builder
        public ColorHighlightBuilder consumeAttributes(TypedArray typedArray) {
            super.consumeAttributes(typedArray);
            if (typedArray.hasValue(2)) {
                setBaseColor(typedArray.getColor(2, this.mShimmer.baseColor));
            }
            if (typedArray.hasValue(12)) {
                setHighlightColor(typedArray.getColor(12, this.mShimmer.highlightColor));
            }
            return getThis();
        }
    }
}