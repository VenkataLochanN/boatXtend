package com.ido.life.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.boat.Xtend.two.R;
import com.google.android.gms.fitness.FitnessActivities;
import com.ido.life.location.MapHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: RadiusProgressBar.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001TB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\b\u0010F\u001a\u0004\u0018\u00010\u0012J\b\u0010G\u001a\u00020\u000bH\u0002J\b\u0010H\u001a\u00020\u000bH\u0002J\b\u0010I\u001a\u00020\u000bH\u0002J\b\u0010J\u001a\u00020\u000bH\u0002J\b\u0010K\u001a\u00020*H\u0002J\b\u0010L\u001a\u00020*H\u0002J\u0010\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020PH\u0014J\u0018\u0010Q\u001a\u00020N2\u0006\u0010R\u001a\u00020\u000b2\u0006\u0010S\u001a\u00020\u000bH\u0014R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR \u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\r\"\u0004\b\u001a\u0010\u000fR\u000e\u0010\u001b\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\r\"\u0004\b\u001f\u0010\u000fR\u001a\u0010 \u001a\u00020!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001a\u0010&\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\r\"\u0004\b(\u0010\u000fR\u000e\u0010)\u001a\u00020*X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020*X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010-\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\r\"\u0004\b/\u0010\u000fR\u000e\u00100\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u000203X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u000205X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00108\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\r\"\u0004\b:\u0010\u000fR\u000e\u0010;\u001a\u00020<X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u000205X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010C\u001a\u00020!2\u0006\u0010B\u001a\u00020!8G@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010#\"\u0004\bE\u0010%¨\u0006U"}, d2 = {"Lcom/ido/life/customview/RadiusProgressBar;", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "mClipPath", "Landroid/graphics/Path;", "mCurrentProgress", "", "getMCurrentProgress", "()I", "setMCurrentProgress", "(I)V", "mDividerPropertyList", "", "Lcom/ido/life/customview/RadiusProgressBar$DividerProperty;", "getMDividerPropertyList", "()Ljava/util/List;", "setMDividerPropertyList", "(Ljava/util/List;)V", "mDividerSpace", "mIndicatorColor", "getMIndicatorColor", "setMIndicatorColor", "mIndicatorHeight", "mIndicatorLabelColor", "mIndicatorLabelDistance", "getMIndicatorLabelDistance", "setMIndicatorLabelDistance", "mIndicatorLabelEnable", "", "getMIndicatorLabelEnable", "()Z", "setMIndicatorLabelEnable", "(Z)V", "mIndicatorLabelSize", "getMIndicatorLabelSize", "setMIndicatorLabelSize", "mIndicatorMaxHeight", "", "mIndicatorMaxWidth", "mIndicatorProgressDistance", "mIndicatorRadius", "getMIndicatorRadius", "setMIndicatorRadius", "mIndicatorWidth", "mLabelProgressDistance", "mLabelRect", "Landroid/graphics/Rect;", "mLeftBottomLabel", "", "mLeftBottomLabelColor", "mLeftBottomLabelSize", "mMaxProgress", "getMMaxProgress", "setMMaxProgress", "mPaint", "Landroid/graphics/Paint;", "mProgressHeight", "mProgressRadius", "mRightBottomLabel", "mRightBottomLabelColor", "mRightBottomLabelSize", "<set-?>", "mShowIndicator", "showIndicator", "enableIndicator", "getCurrDivider", "getIndicationProgressDistance", "getIndicatorHeight", "getIndicatorWidth", "getLabelProgressDistance", "measureIndicatorLabelHeightMax", "measureIndicatorLabelWidthMax", "onDraw", "", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "DividerProperty", "app_release"}, k = 1, mv = {1, 1, 16})
public final class RadiusProgressBar extends View {
    private HashMap _$_findViewCache;
    private final Path mClipPath;
    private int mCurrentProgress;
    private List<DividerProperty> mDividerPropertyList;
    private int mDividerSpace;
    private int mIndicatorColor;
    private int mIndicatorHeight;
    private int mIndicatorLabelColor;
    private int mIndicatorLabelDistance;
    private boolean mIndicatorLabelEnable;
    private int mIndicatorLabelSize;
    private float mIndicatorMaxHeight;
    private float mIndicatorMaxWidth;
    private int mIndicatorProgressDistance;
    private int mIndicatorRadius;
    private int mIndicatorWidth;
    private int mLabelProgressDistance;
    private final Rect mLabelRect;
    private String mLeftBottomLabel;
    private int mLeftBottomLabelColor;
    private int mLeftBottomLabelSize;
    private int mMaxProgress;
    private Paint mPaint;
    private int mProgressHeight;
    private int mProgressRadius;
    private String mRightBottomLabel;
    private int mRightBottomLabelColor;
    private int mRightBottomLabelSize;
    private boolean mShowIndicator;

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
    public RadiusProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.mMaxProgress = 100;
        this.mIndicatorWidth = 20;
        this.mIndicatorHeight = 20;
        this.mIndicatorColor = ViewCompat.MEASURED_STATE_MASK;
        this.mLeftBottomLabel = "";
        this.mLeftBottomLabelColor = MapHelper.Standard_Color;
        this.mRightBottomLabel = "";
        this.mRightBottomLabelColor = -16776961;
        this.mIndicatorRadius = 5;
        this.mDividerPropertyList = new ArrayList();
        this.mLabelRect = new Rect();
        this.mDividerSpace = getResources().getDimensionPixelSize(R.dimen.sw_dp_1);
        this.mShowIndicator = true;
        this.mIndicatorLabelSize = getResources().getDimensionPixelSize(R.dimen.sw_dp_12);
        this.mIndicatorLabelColor = -3355444;
        this.mPaint = new Paint();
        this.mClipPath = new Path();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.ido.life.R.styleable.RadiusProgressBar);
        Intrinsics.checkExpressionValueIsNotNull(typedArrayObtainStyledAttributes, "context.obtainStyledAttr…leable.RadiusProgressBar)");
        this.mCurrentProgress = typedArrayObtainStyledAttributes.getInt(0, this.mCurrentProgress);
        this.mMaxProgress = typedArrayObtainStyledAttributes.getInt(15, this.mMaxProgress);
        this.mIndicatorWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(10, this.mIndicatorWidth);
        this.mIndicatorHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, this.mIndicatorHeight);
        this.mIndicatorColor = typedArrayObtainStyledAttributes.getColor(1, this.mIndicatorColor);
        this.mIndicatorRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(9, this.mIndicatorRadius);
        String string = typedArrayObtainStyledAttributes.getString(12);
        String str = string;
        if (!(str == null || str.length() == 0)) {
            this.mLeftBottomLabel = string;
        }
        this.mLeftBottomLabelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(14, this.mLeftBottomLabelSize);
        this.mLeftBottomLabelColor = typedArrayObtainStyledAttributes.getColor(13, this.mLeftBottomLabelColor);
        String string2 = typedArrayObtainStyledAttributes.getString(19);
        String str2 = string2;
        if (!(str2 == null || str2.length() == 0)) {
            this.mRightBottomLabel = string2;
        }
        this.mRightBottomLabelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(21, this.mRightBottomLabelSize);
        this.mRightBottomLabelColor = typedArrayObtainStyledAttributes.getColor(20, this.mRightBottomLabelColor);
        this.mLabelProgressDistance = typedArrayObtainStyledAttributes.getDimensionPixelSize(11, this.mLabelProgressDistance);
        this.mIndicatorProgressDistance = typedArrayObtainStyledAttributes.getDimensionPixelSize(8, this.mIndicatorProgressDistance);
        this.mProgressHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(16, this.mProgressHeight);
        this.mShowIndicator = typedArrayObtainStyledAttributes.getBoolean(2, true);
        this.mIndicatorLabelEnable = typedArrayObtainStyledAttributes.getBoolean(6, this.mIndicatorLabelEnable);
        this.mIndicatorLabelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(7, this.mIndicatorLabelSize);
        this.mIndicatorLabelColor = typedArrayObtainStyledAttributes.getColor(4, this.mIndicatorLabelColor);
        this.mIndicatorLabelDistance = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, this.mIndicatorLabelDistance);
        this.mProgressRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(17, this.mProgressRadius);
        this.mDividerSpace = typedArrayObtainStyledAttributes.getDimensionPixelSize(18, this.mDividerSpace);
        if (!this.mIndicatorLabelEnable) {
            this.mIndicatorLabelSize = 0;
            this.mIndicatorLabelDistance = 0;
        }
        typedArrayObtainStyledAttributes.recycle();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setDither(true);
        this.mPaint.setStyle(Paint.Style.FILL);
    }

    public final int getMCurrentProgress() {
        return this.mCurrentProgress;
    }

    public final void setMCurrentProgress(int i) {
        this.mCurrentProgress = i;
    }

    public final int getMMaxProgress() {
        return this.mMaxProgress;
    }

    public final void setMMaxProgress(int i) {
        this.mMaxProgress = i;
    }

    public final int getMIndicatorColor() {
        return this.mIndicatorColor;
    }

    public final void setMIndicatorColor(int i) {
        this.mIndicatorColor = i;
    }

    public final int getMIndicatorRadius() {
        return this.mIndicatorRadius;
    }

    public final void setMIndicatorRadius(int i) {
        this.mIndicatorRadius = i;
    }

    public final List<DividerProperty> getMDividerPropertyList() {
        return this.mDividerPropertyList;
    }

    public final void setMDividerPropertyList(List<DividerProperty> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.mDividerPropertyList = list;
    }

    public final void enableIndicator(boolean z) {
        this.mShowIndicator = z;
    }

    /* JADX INFO: renamed from: showIndicator, reason: from getter */
    public final boolean getMShowIndicator() {
        return this.mShowIndicator;
    }

    public final boolean getMIndicatorLabelEnable() {
        return this.mIndicatorLabelEnable;
    }

    public final void setMIndicatorLabelEnable(boolean z) {
        this.mIndicatorLabelEnable = z;
    }

    public final int getMIndicatorLabelSize() {
        return this.mIndicatorLabelSize;
    }

    public final void setMIndicatorLabelSize(int i) {
        this.mIndicatorLabelSize = i;
    }

    public final int getMIndicatorLabelDistance() {
        return this.mIndicatorLabelDistance;
    }

    public final void setMIndicatorLabelDistance(int i) {
        this.mIndicatorLabelDistance = i;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RadiusProgressBar(Context context) {
        this(context, null);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        boolean z;
        int i;
        int i2;
        int size;
        int i3;
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        super.onDraw(canvas);
        if (this.mDividerPropertyList.isEmpty()) {
            return;
        }
        int size2 = this.mDividerPropertyList.size();
        int i4 = 0;
        while (true) {
            if (i4 >= size2) {
                z = false;
                break;
            }
            DividerProperty dividerProperty = this.mDividerPropertyList.get(i4);
            if (dividerProperty.getStartValue() <= 0.0f && dividerProperty.getEndValue() <= 0.0f) {
                z = true;
                break;
            }
            i4++;
        }
        float f2 = size2;
        float f3 = 1.0f / f2;
        int width = (getWidth() - getIndicatorWidth()) - ((size2 - 1) * this.mDividerSpace);
        this.mClipPath.reset();
        int i5 = 2;
        if (this.mProgressRadius > 0) {
            Path path = this.mClipPath;
            float f4 = 2;
            float indicatorWidth = (this.mIndicatorMaxWidth / f4) + (getIndicatorWidth() / 2.0f);
            float indicatorHeight = this.mIndicatorMaxHeight + getIndicatorHeight() + getIndicationProgressDistance() + (this.mIndicatorLabelEnable ? this.mIndicatorLabelDistance : 0);
            float width2 = (getWidth() - (this.mIndicatorMaxWidth / f4)) - (getIndicatorWidth() / 2.0f);
            float indicatorHeight2 = this.mIndicatorMaxHeight + getIndicatorHeight() + getIndicationProgressDistance() + (this.mIndicatorLabelEnable ? this.mIndicatorLabelDistance : 0) + this.mProgressHeight;
            int i6 = this.mProgressRadius;
            path.addRoundRect(indicatorWidth, indicatorHeight, width2, indicatorHeight2, new float[]{i6, i6, i6, i6, i6, i6, i6, i6}, Path.Direction.CCW);
            int iSave = canvas.save();
            canvas.clipPath(this.mClipPath);
            i = iSave;
        } else {
            i = -1;
        }
        int i7 = 0;
        float f5 = 0.0f;
        while (i7 < size2) {
            DividerProperty dividerProperty2 = this.mDividerPropertyList.get(i7);
            float percent = z ? f3 : dividerProperty2.getPercent();
            this.mPaint.setColor(dividerProperty2.getColor());
            float f6 = i5;
            float f7 = width;
            float indicatorWidth2 = (this.mDividerSpace * i7) + (this.mIndicatorMaxWidth / f6) + (getIndicatorWidth() / 2.0f) + (f7 * f5);
            int i8 = width;
            float indicatorHeight3 = (this.mIndicatorLabelEnable ? this.mIndicatorLabelDistance : 0) + this.mIndicatorMaxHeight + getIndicatorHeight() + getIndicationProgressDistance();
            float indicatorWidth3 = (this.mIndicatorMaxWidth / f6) + (getIndicatorWidth() / 2.0f);
            float f8 = f5 + percent;
            float f9 = indicatorWidth3 + (f7 * f8) + (this.mDividerSpace * i7);
            float indicatorHeight4 = this.mIndicatorMaxHeight + getIndicatorHeight() + getIndicationProgressDistance() + (this.mIndicatorLabelEnable ? this.mIndicatorLabelDistance : 0) + this.mProgressHeight;
            if (dividerProperty2.getColorArray() != null && dividerProperty2.getPositionArray() != null) {
                Paint paint = this.mPaint;
                int[] colorArray = dividerProperty2.getColorArray();
                if (colorArray == null) {
                    Intrinsics.throwNpe();
                }
                paint.setShader(new LinearGradient(indicatorWidth2, indicatorHeight3, f9, indicatorHeight3, colorArray, dividerProperty2.getPositionArray(), Shader.TileMode.CLAMP));
            } else {
                this.mPaint.setShader((Shader) null);
            }
            canvas.drawRect(indicatorWidth2, indicatorHeight3, f9, indicatorHeight4, this.mPaint);
            i7++;
            width = i8;
            f5 = f8;
            i5 = 2;
        }
        if (i != -1) {
            canvas.restoreToCount(i);
        }
        if (this.mLeftBottomLabel.length() > 0) {
            this.mPaint.setColor(this.mLeftBottomLabelColor);
            this.mPaint.setTextSize(this.mLeftBottomLabelSize);
            i2 = 2;
            canvas.drawText(this.mLeftBottomLabel, (this.mIndicatorMaxWidth / 2) + (getIndicatorWidth() / 2.0f), getHeight() - Math.abs(this.mPaint.getFontMetrics().descent), this.mPaint);
        } else {
            i2 = 2;
        }
        if (this.mRightBottomLabel.length() > 0) {
            this.mPaint.setColor(this.mRightBottomLabelColor);
            this.mPaint.setTextSize(this.mRightBottomLabelSize);
            Paint paint2 = this.mPaint;
            String str = this.mRightBottomLabel;
            paint2.getTextBounds(str, 0, str.length(), this.mLabelRect);
            canvas.drawText(this.mRightBottomLabel, ((getWidth() - (this.mIndicatorMaxWidth / i2)) + ((-getIndicatorWidth()) / 2.0f)) - this.mLabelRect.width(), getHeight() - Math.abs(this.mPaint.getFontMetrics().descent), this.mPaint);
        }
        if (this.mShowIndicator) {
            float f10 = (this.mCurrentProgress * 1.0f) / this.mMaxProgress;
            if (f10 > 1.0f) {
                f10 = 1.0f;
            }
            if (!z) {
                int i9 = 0;
                while (true) {
                    if (i9 >= size2) {
                        size = -1;
                        break;
                    }
                    DividerProperty dividerProperty3 = this.mDividerPropertyList.get(i9);
                    if (this.mCurrentProgress >= dividerProperty3.getStartValue() && this.mCurrentProgress < dividerProperty3.getEndValue()) {
                        size = i9;
                        break;
                    }
                    i9++;
                }
            } else {
                int i10 = -1;
                float f11 = 0.0f;
                int i11 = 0;
                while (i11 < size2) {
                    f11 += f3;
                    if (f11 > f10) {
                        break;
                    }
                    int i12 = i11;
                    i11++;
                    i10 = i12;
                }
                size = i10;
            }
            if (size == -1) {
                i3 = 0;
                if (this.mCurrentProgress < this.mDividerPropertyList.get(0).getStartValue()) {
                    size = 0;
                } else {
                    float f12 = this.mCurrentProgress;
                    List<DividerProperty> list = this.mDividerPropertyList;
                    if (f12 >= list.get(list.size() - 1).getEndValue()) {
                        size = this.mDividerPropertyList.size() - 1;
                    }
                }
            } else {
                i3 = 0;
            }
            int size3 = this.mDividerPropertyList.size();
            if (size >= 0 && size3 > size) {
                DividerProperty dividerProperty4 = this.mDividerPropertyList.get(size);
                float f13 = size;
                float fMin = Math.min(1.0f, (f3 * f13) + ((Math.max(0.0f, this.mCurrentProgress - dividerProperty4.getStartValue()) / (dividerProperty4.getEndValue() - dividerProperty4.getStartValue())) * f3));
                int iSave2 = canvas.save();
                this.mPaint.setColor(this.mIndicatorColor);
                float fMax = Math.max(this.mIndicatorMaxWidth, getIndicatorWidth());
                this.mClipPath.reset();
                float f14 = i2;
                float width3 = (fMax / f14) + ((((getWidth() - fMax) - f2) + 1) * fMin) + f13;
                this.mClipPath.moveTo(width3, this.mIndicatorMaxHeight + getIndicatorHeight() + (this.mIndicatorLabelEnable ? this.mIndicatorLabelDistance : i3));
                this.mClipPath.lineTo(width3 - (getIndicatorWidth() / 2.0f), this.mIndicatorMaxHeight + (this.mIndicatorLabelEnable ? this.mIndicatorLabelDistance : i3));
                this.mClipPath.lineTo((getIndicatorWidth() / 2.0f) + width3, this.mIndicatorMaxHeight + (this.mIndicatorLabelEnable ? this.mIndicatorLabelDistance : i3));
                canvas.drawPath(this.mClipPath, this.mPaint);
                canvas.restoreToCount(iSave2);
                if (this.mIndicatorLabelEnable) {
                    String indicatorLabel = dividerProperty4.getIndicatorLabel();
                    if (indicatorLabel == null || indicatorLabel.length() == 0) {
                        i3 = 1;
                    }
                    if (i3 == 0) {
                        this.mPaint.setTextSize(this.mIndicatorLabelSize);
                        this.mPaint.setColor(this.mIndicatorLabelColor);
                        String indicatorLabel2 = dividerProperty4.getIndicatorLabel();
                        if (indicatorLabel2 == null) {
                            Intrinsics.throwNpe();
                        }
                        canvas.drawText(indicatorLabel2, Math.max(0.0f, width3 - (this.mPaint.measureText(dividerProperty4.getIndicatorLabel()) / f14)), this.mIndicatorMaxHeight, this.mPaint);
                    }
                }
            }
        }
    }

    private final int getIndicatorWidth() {
        if (this.mShowIndicator) {
            return this.mIndicatorWidth;
        }
        return 0;
    }

    private final int getIndicatorHeight() {
        if (this.mShowIndicator) {
            return this.mIndicatorWidth;
        }
        return 0;
    }

    private final int getIndicationProgressDistance() {
        if (this.mShowIndicator) {
            return this.mIndicatorProgressDistance;
        }
        return 0;
    }

    private final int getLabelProgressDistance() {
        if (this.mShowIndicator) {
            return this.mLabelProgressDistance;
        }
        return 0;
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        String str;
        int iMax;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        if (this.mIndicatorLabelEnable) {
            this.mIndicatorMaxHeight = measureIndicatorLabelHeightMax();
            this.mIndicatorMaxWidth = measureIndicatorLabelWidthMax();
        } else {
            this.mIndicatorMaxHeight = 0.0f;
            this.mIndicatorMaxWidth = 0.0f;
        }
        float f2 = this.mProgressHeight;
        if (this.mIndicatorLabelEnable) {
            f2 += this.mIndicatorLabelDistance + this.mIndicatorMaxHeight;
        }
        if (this.mLeftBottomLabel.length() > 0) {
            iMax = this.mLeftBottomLabelSize;
            str = this.mLeftBottomLabel;
        } else {
            str = "";
            iMax = 0;
        }
        if (this.mRightBottomLabel.length() > 0) {
            iMax = Math.max(iMax, this.mRightBottomLabelSize);
            str = this.mRightBottomLabel;
        }
        float indicatorHeight = f2 + getIndicatorHeight() + getLabelProgressDistance();
        if (str.length() > 0) {
            this.mPaint.setTextSize(iMax);
            Paint.FontMetrics fontMetrics = this.mPaint.getFontMetrics();
            indicatorHeight += Math.abs(fontMetrics.top) + Math.abs(fontMetrics.bottom) + getLabelProgressDistance();
        }
        setMeasuredDimension(size, MathKt.roundToInt(indicatorHeight));
    }

    private final float measureIndicatorLabelHeightMax() {
        float fMax = 0.0f;
        if (this.mDividerPropertyList.isEmpty()) {
            return 0.0f;
        }
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(this.mIndicatorLabelSize);
        Rect rect = new Rect();
        for (DividerProperty dividerProperty : this.mDividerPropertyList) {
            String indicatorLabel = dividerProperty.getIndicatorLabel();
            if (!(indicatorLabel == null || indicatorLabel.length() == 0)) {
                String indicatorLabel2 = dividerProperty.getIndicatorLabel();
                if (indicatorLabel2 == null) {
                    Intrinsics.throwNpe();
                }
                String indicatorLabel3 = dividerProperty.getIndicatorLabel();
                if (indicatorLabel3 == null) {
                    Intrinsics.throwNpe();
                }
                paint.getTextBounds(indicatorLabel2, 0, indicatorLabel3.length(), rect);
                fMax = Math.max(fMax, rect.height());
            }
        }
        return fMax;
    }

    private final float measureIndicatorLabelWidthMax() {
        float fMax = 0.0f;
        if (this.mDividerPropertyList.isEmpty()) {
            return 0.0f;
        }
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(this.mIndicatorLabelSize);
        for (DividerProperty dividerProperty : this.mDividerPropertyList) {
            String indicatorLabel = dividerProperty.getIndicatorLabel();
            if (!(indicatorLabel == null || indicatorLabel.length() == 0)) {
                fMax = Math.max(fMax, this.mPaint.measureText(dividerProperty.getIndicatorLabel()));
            }
        }
        return fMax;
    }

    public final DividerProperty getCurrDivider() {
        if (this.mDividerPropertyList.isEmpty()) {
            return null;
        }
        DividerProperty dividerProperty = (DividerProperty) null;
        for (DividerProperty dividerProperty2 : this.mDividerPropertyList) {
            if (this.mCurrentProgress >= dividerProperty2.getStartValue() && this.mCurrentProgress <= dividerProperty2.getEndValue()) {
                return dividerProperty2;
            }
        }
        return dividerProperty;
    }

    /* JADX INFO: compiled from: RadiusProgressBar.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0014\n\u0002\b\"\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eJ\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0005HÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\rHÆ\u0003JU\u0010.\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rHÆ\u0001J\u0013\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00102\u001a\u00020\u0003HÖ\u0001J\t\u00103\u001a\u00020\tHÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0018\"\u0004\b \u0010\u001aR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0018\"\u0004\b&\u0010\u001a¨\u00064"}, d2 = {"Lcom/ido/life/customview/RadiusProgressBar$DividerProperty;", "", "color", "", "startValue", "", "endValue", "percent", "indicatorLabel", "", "colorArray", "", "positionArray", "", "(IFFFLjava/lang/String;[I[F)V", "getColor", "()I", "setColor", "(I)V", "getColorArray", "()[I", "setColorArray", "([I)V", "getEndValue", "()F", "setEndValue", "(F)V", "getIndicatorLabel", "()Ljava/lang/String;", "setIndicatorLabel", "(Ljava/lang/String;)V", "getPercent", "setPercent", "getPositionArray", "()[F", "setPositionArray", "([F)V", "getStartValue", "setStartValue", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class DividerProperty {
        private int color;
        private int[] colorArray;
        private float endValue;
        private String indicatorLabel;
        private float percent;
        private float[] positionArray;
        private float startValue;

        public static /* synthetic */ DividerProperty copy$default(DividerProperty dividerProperty, int i, float f2, float f3, float f4, String str, int[] iArr, float[] fArr, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = dividerProperty.color;
            }
            if ((i2 & 2) != 0) {
                f2 = dividerProperty.startValue;
            }
            float f5 = f2;
            if ((i2 & 4) != 0) {
                f3 = dividerProperty.endValue;
            }
            float f6 = f3;
            if ((i2 & 8) != 0) {
                f4 = dividerProperty.percent;
            }
            float f7 = f4;
            if ((i2 & 16) != 0) {
                str = dividerProperty.indicatorLabel;
            }
            String str2 = str;
            if ((i2 & 32) != 0) {
                iArr = dividerProperty.colorArray;
            }
            int[] iArr2 = iArr;
            if ((i2 & 64) != 0) {
                fArr = dividerProperty.positionArray;
            }
            return dividerProperty.copy(i, f5, f6, f7, str2, iArr2, fArr);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getColor() {
            return this.color;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final float getStartValue() {
            return this.startValue;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final float getEndValue() {
            return this.endValue;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final float getPercent() {
            return this.percent;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getIndicatorLabel() {
            return this.indicatorLabel;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final int[] getColorArray() {
            return this.colorArray;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final float[] getPositionArray() {
            return this.positionArray;
        }

        public final DividerProperty copy(int color, float startValue, float endValue, float percent, String indicatorLabel, int[] colorArray, float[] positionArray) {
            return new DividerProperty(color, startValue, endValue, percent, indicatorLabel, colorArray, positionArray);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DividerProperty)) {
                return false;
            }
            DividerProperty dividerProperty = (DividerProperty) other;
            return this.color == dividerProperty.color && Float.compare(this.startValue, dividerProperty.startValue) == 0 && Float.compare(this.endValue, dividerProperty.endValue) == 0 && Float.compare(this.percent, dividerProperty.percent) == 0 && Intrinsics.areEqual(this.indicatorLabel, dividerProperty.indicatorLabel) && Intrinsics.areEqual(this.colorArray, dividerProperty.colorArray) && Intrinsics.areEqual(this.positionArray, dividerProperty.positionArray);
        }

        public int hashCode() {
            int iHashCode = ((((((Integer.valueOf(this.color).hashCode() * 31) + Float.valueOf(this.startValue).hashCode()) * 31) + Float.valueOf(this.endValue).hashCode()) * 31) + Float.valueOf(this.percent).hashCode()) * 31;
            String str = this.indicatorLabel;
            int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
            int[] iArr = this.colorArray;
            int iHashCode3 = (iHashCode2 + (iArr != null ? Arrays.hashCode(iArr) : 0)) * 31;
            float[] fArr = this.positionArray;
            return iHashCode3 + (fArr != null ? Arrays.hashCode(fArr) : 0);
        }

        public String toString() {
            return "DividerProperty(color=" + this.color + ", startValue=" + this.startValue + ", endValue=" + this.endValue + ", percent=" + this.percent + ", indicatorLabel=" + this.indicatorLabel + ", colorArray=" + Arrays.toString(this.colorArray) + ", positionArray=" + Arrays.toString(this.positionArray) + ")";
        }

        public DividerProperty(int i, float f2, float f3, float f4, String str, int[] iArr, float[] fArr) {
            this.color = i;
            this.startValue = f2;
            this.endValue = f3;
            this.percent = f4;
            this.indicatorLabel = str;
            this.colorArray = iArr;
            this.positionArray = fArr;
        }

        public final int getColor() {
            return this.color;
        }

        public final void setColor(int i) {
            this.color = i;
        }

        public final float getStartValue() {
            return this.startValue;
        }

        public final void setStartValue(float f2) {
            this.startValue = f2;
        }

        public final float getEndValue() {
            return this.endValue;
        }

        public final void setEndValue(float f2) {
            this.endValue = f2;
        }

        public final float getPercent() {
            return this.percent;
        }

        public final void setPercent(float f2) {
            this.percent = f2;
        }

        public /* synthetic */ DividerProperty(int i, float f2, float f3, float f4, String str, int[] iArr, float[] fArr, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, f2, f3, (i2 & 8) != 0 ? 0.0f : f4, (i2 & 16) != 0 ? (String) null : str, (i2 & 32) != 0 ? (int[]) null : iArr, (i2 & 64) != 0 ? (float[]) null : fArr);
        }

        public final String getIndicatorLabel() {
            return this.indicatorLabel;
        }

        public final void setIndicatorLabel(String str) {
            this.indicatorLabel = str;
        }

        public final int[] getColorArray() {
            return this.colorArray;
        }

        public final void setColorArray(int[] iArr) {
            this.colorArray = iArr;
        }

        public final float[] getPositionArray() {
            return this.positionArray;
        }

        public final void setPositionArray(float[] fArr) {
            this.positionArray = fArr;
        }
    }
}