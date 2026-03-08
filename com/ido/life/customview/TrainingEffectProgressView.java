package com.ido.life.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.view.InputDeviceCompat;
import com.google.android.gms.fitness.FitnessActivities;
import com.ido.life.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TrainingEffectProgressView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001)B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%H\u0014J\u0018\u0010&\u001a\u00020#2\u0006\u0010'\u001a\u00020\b2\u0006\u0010(\u001a\u00020\bH\u0014R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u000b\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/ido/life/customview/TrainingEffectProgressView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "TRIANGLE_HEIGHT", "", "TRIANGLE_WIDTH", "<set-?>", "mCurrProgress", "getCurrProgress", "()I", "setCurrProgress", "(I)V", "mIndicatorColor", "mIndicatorLabelColor", "mIndicatorLabelSize", "mIndicatorProgressDistance", "mLabelPaddingLeft", "mLabelPaddingRight", "mLabelPaddingTop", "mPaint", "Landroid/graphics/Paint;", "mProgressHeight", "mPropertyList", "", "Lcom/ido/life/customview/TrainingEffectProgressView$DividerProperty;", "getMPropertyList", "()Ljava/util/List;", "setMPropertyList", "(Ljava/util/List;)V", "mlabePaddingBottom", "onDraw", "", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "DividerProperty", "app_release"}, k = 1, mv = {1, 1, 16})
public final class TrainingEffectProgressView extends View {
    private int TRIANGLE_HEIGHT;
    private int TRIANGLE_WIDTH;
    private HashMap _$_findViewCache;
    private int mCurrProgress;
    private int mIndicatorColor;
    private int mIndicatorLabelColor;
    private int mIndicatorLabelSize;
    private int mIndicatorProgressDistance;
    private int mLabelPaddingLeft;
    private int mLabelPaddingRight;
    private int mLabelPaddingTop;
    private Paint mPaint;
    private int mProgressHeight;
    private List<DividerProperty> mPropertyList;
    private int mlabePaddingBottom;

    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TrainingEffectProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attributeSet, "attributeSet");
        this.mProgressHeight = 10;
        this.mIndicatorColor = InputDeviceCompat.SOURCE_ANY;
        this.mIndicatorLabelSize = 12;
        this.mIndicatorLabelColor = -1;
        this.mLabelPaddingTop = 4;
        this.mlabePaddingBottom = 4;
        this.mLabelPaddingRight = 4;
        this.mLabelPaddingLeft = 4;
        this.mIndicatorProgressDistance = 4;
        this.TRIANGLE_HEIGHT = 8;
        this.TRIANGLE_WIDTH = 8;
        this.mPaint = new Paint();
        this.mPropertyList = new ArrayList();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TrainEffectProgressView);
        Intrinsics.checkExpressionValueIsNotNull(typedArrayObtainStyledAttributes, "context.obtainStyledAttr….TrainEffectProgressView)");
        this.mProgressHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(10, this.mProgressHeight);
        this.mIndicatorColor = typedArrayObtainStyledAttributes.getColor(0, this.mIndicatorColor);
        this.mIndicatorLabelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(8, this.mIndicatorLabelSize);
        this.mIndicatorLabelColor = typedArrayObtainStyledAttributes.getColor(2, this.mIndicatorLabelColor);
        this.mIndicatorProgressDistance = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, this.mIndicatorProgressDistance);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, -1);
        if (typedArrayObtainStyledAttributes.hasValue(5)) {
            this.mLabelPaddingLeft = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, 0);
        } else {
            this.mLabelPaddingLeft = dimensionPixelSize;
        }
        if (typedArrayObtainStyledAttributes.hasValue(5)) {
            this.mLabelPaddingLeft = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, 0);
        } else {
            this.mLabelPaddingLeft = dimensionPixelSize;
        }
        if (typedArrayObtainStyledAttributes.hasValue(7)) {
            this.mLabelPaddingTop = typedArrayObtainStyledAttributes.getDimensionPixelSize(7, 0);
        } else {
            this.mLabelPaddingTop = dimensionPixelSize;
        }
        if (typedArrayObtainStyledAttributes.hasValue(6)) {
            this.mLabelPaddingRight = typedArrayObtainStyledAttributes.getDimensionPixelSize(6, 0);
        } else {
            this.mLabelPaddingRight = dimensionPixelSize;
        }
        if (typedArrayObtainStyledAttributes.hasValue(4)) {
            this.mlabePaddingBottom = typedArrayObtainStyledAttributes.getDimensionPixelSize(4, 0);
        } else {
            this.mlabePaddingBottom = dimensionPixelSize;
        }
        typedArrayObtainStyledAttributes.recycle();
        this.TRIANGLE_HEIGHT = getResources().getDimensionPixelSize(com.boat.Xtend.two.R.dimen.sw_dp_6);
        this.TRIANGLE_WIDTH = getResources().getDimensionPixelSize(com.boat.Xtend.two.R.dimen.sw_dp_6);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setDither(true);
        this.mPaint.setAntiAlias(true);
    }

    /* JADX INFO: renamed from: getCurrProgress, reason: from getter */
    public final int getMCurrProgress() {
        return this.mCurrProgress;
    }

    public final void setCurrProgress(int i) {
        this.mCurrProgress = i;
    }

    public final List<DividerProperty> getMPropertyList() {
        return this.mPropertyList;
    }

    public final void setMPropertyList(List<DividerProperty> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.mPropertyList = list;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int color;
        DividerProperty dividerProperty;
        super.onDraw(canvas);
        int size = this.mPropertyList.size();
        if (size == 0) {
            return;
        }
        float space = 0.0f;
        float space2 = 0.0f;
        for (int i = 0; i < size; i++) {
            DividerProperty dividerProperty2 = this.mPropertyList.get(i);
            if (i != size - 1) {
                space2 += dividerProperty2.getSpace();
            }
        }
        int i2 = this.mCurrProgress;
        float width = (getWidth() - space2) / size;
        if (i2 >= 0 && size > i2 && (dividerProperty = this.mPropertyList.get(i2)) != null) {
            String label = dividerProperty.getLabel();
            if (!(label == null || label.length() == 0)) {
                String label2 = dividerProperty.getLabel();
                this.mPaint.setTextSize(this.mIndicatorLabelSize);
                Rect rect = new Rect();
                this.mPaint.getTextBounds(label2, 0, label2.length(), rect);
                float fHeight = rect.height() + this.mLabelPaddingTop + this.mlabePaddingBottom + this.TRIANGLE_HEIGHT;
                float f2 = i2;
                float space3 = (f2 * width) + (width / 2.0f) + (f2 * dividerProperty.getSpace());
                this.mPaint.setColor(this.mIndicatorColor);
                Path path = new Path();
                path.moveTo(space3, fHeight);
                path.lineTo(space3 - (this.TRIANGLE_WIDTH / 2), fHeight - this.TRIANGLE_HEIGHT);
                path.lineTo((this.TRIANGLE_WIDTH / 2) + space3, fHeight - this.TRIANGLE_HEIGHT);
                path.close();
                if (canvas != null) {
                    canvas.drawPath(path, this.mPaint);
                }
                this.mPaint.setColor(this.mIndicatorLabelColor);
                float fWidth = space3 - (rect.width() / 2);
                float fWidth2 = space3 - (rect.width() / 2);
                float fWidth3 = space3 + (rect.width() / 2);
                if (fWidth2 < 0) {
                    fWidth = getResources().getDimensionPixelSize(com.boat.Xtend.two.R.dimen.sw_dp_2);
                }
                if (fWidth3 > getWidth()) {
                    fWidth = (getWidth() - rect.width()) - getResources().getDimensionPixelSize(com.boat.Xtend.two.R.dimen.sw_dp_2);
                }
                float f3 = fWidth;
                if (canvas != null) {
                    canvas.drawText(label2, 0, label2.length(), f3, (this.mLabelPaddingTop / 2) + rect.height(), this.mPaint);
                }
            }
        }
        float height = getHeight() * 1.0f;
        RectF rectF = new RectF();
        rectF.top = height - this.mProgressHeight;
        rectF.bottom = height;
        for (int i3 = 0; i3 < size; i3++) {
            DividerProperty dividerProperty3 = this.mPropertyList.get(i3);
            Paint paint = this.mPaint;
            if (i2 < 0) {
                color = Color.parseColor("#EBEBEB");
            } else {
                color = dividerProperty3.getColor();
            }
            paint.setColor(color);
            if (i3 == 0 || i3 == size - 1) {
                rectF.left = space;
                float f4 = space + width;
                rectF.right = f4;
                if (canvas != null) {
                    canvas.drawRoundRect(rectF, dividerProperty3.getRadius(), dividerProperty3.getRadius(), this.mPaint);
                }
                if (i3 == size - 1) {
                    rectF.left = space;
                    rectF.right = dividerProperty3.getRadius() + space;
                    if (canvas != null) {
                        canvas.drawRect(rectF, this.mPaint);
                    }
                } else if (i3 == 0) {
                    rectF.right = f4;
                    rectF.left = rectF.right - dividerProperty3.getRadius();
                    if (canvas != null) {
                        canvas.drawRect(rectF, this.mPaint);
                    }
                }
            } else {
                rectF.left = space;
                rectF.right = space + width;
                if (canvas != null) {
                    canvas.drawRect(rectF, this.mPaint);
                }
            }
            space += dividerProperty3.getSpace() + width;
        }
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        int size2 = View.MeasureSpec.getSize(heightMeasureSpec);
        if (View.MeasureSpec.getMode(heightMeasureSpec) != 1073741824) {
            size2 = this.TRIANGLE_HEIGHT + this.mProgressHeight + this.mIndicatorProgressDistance + this.mLabelPaddingTop + this.mlabePaddingBottom;
        }
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setTextSize(this.mIndicatorLabelSize);
        Rect rect = new Rect();
        this.mPaint.getTextBounds("Hello", 0, 5, rect);
        setMeasuredDimension(size, size2 + rect.height());
    }

    /* JADX INFO: compiled from: TrainingEffectProgressView.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\bHÆ\u0003J1\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020\u0003HÖ\u0001J\t\u0010!\u001a\u00020\bHÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0013\"\u0004\b\u0017\u0010\u0015¨\u0006\""}, d2 = {"Lcom/ido/life/customview/TrainingEffectProgressView$DividerProperty;", "", "color", "", "radius", "", "space", "label", "", "(IFFLjava/lang/String;)V", "getColor", "()I", "setColor", "(I)V", "getLabel", "()Ljava/lang/String;", "setLabel", "(Ljava/lang/String;)V", "getRadius", "()F", "setRadius", "(F)V", "getSpace", "setSpace", "component1", "component2", "component3", "component4", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class DividerProperty {
        private int color;
        private String label;
        private float radius;
        private float space;

        public static /* synthetic */ DividerProperty copy$default(DividerProperty dividerProperty, int i, float f2, float f3, String str, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = dividerProperty.color;
            }
            if ((i2 & 2) != 0) {
                f2 = dividerProperty.radius;
            }
            if ((i2 & 4) != 0) {
                f3 = dividerProperty.space;
            }
            if ((i2 & 8) != 0) {
                str = dividerProperty.label;
            }
            return dividerProperty.copy(i, f2, f3, str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getColor() {
            return this.color;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final float getRadius() {
            return this.radius;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final float getSpace() {
            return this.space;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getLabel() {
            return this.label;
        }

        public final DividerProperty copy(int color, float radius, float space, String label) {
            Intrinsics.checkParameterIsNotNull(label, "label");
            return new DividerProperty(color, radius, space, label);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DividerProperty)) {
                return false;
            }
            DividerProperty dividerProperty = (DividerProperty) other;
            return this.color == dividerProperty.color && Float.compare(this.radius, dividerProperty.radius) == 0 && Float.compare(this.space, dividerProperty.space) == 0 && Intrinsics.areEqual(this.label, dividerProperty.label);
        }

        public int hashCode() {
            int iHashCode = ((((Integer.valueOf(this.color).hashCode() * 31) + Float.valueOf(this.radius).hashCode()) * 31) + Float.valueOf(this.space).hashCode()) * 31;
            String str = this.label;
            return iHashCode + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "DividerProperty(color=" + this.color + ", radius=" + this.radius + ", space=" + this.space + ", label=" + this.label + ")";
        }

        public DividerProperty(int i, float f2, float f3, String label) {
            Intrinsics.checkParameterIsNotNull(label, "label");
            this.color = i;
            this.radius = f2;
            this.space = f3;
            this.label = label;
        }

        public final int getColor() {
            return this.color;
        }

        public final void setColor(int i) {
            this.color = i;
        }

        public final float getRadius() {
            return this.radius;
        }

        public final void setRadius(float f2) {
            this.radius = f2;
        }

        public final float getSpace() {
            return this.space;
        }

        public final void setSpace(float f2) {
            this.space = f2;
        }

        public final String getLabel() {
            return this.label;
        }

        public final void setLabel(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.label = str;
        }
    }
}