package com.ido.life.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import com.google.android.gms.fitness.FitnessActivities;
import com.ido.life.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecentSituationProgressView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001BB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u00109\u001a\u00020\b2\u0006\u0010:\u001a\u00020\u000bH\u0002J\u0012\u0010;\u001a\u00020<2\b\u0010=\u001a\u0004\u0018\u00010>H\u0014J\u0018\u0010?\u001a\u00020<2\u0006\u0010@\u001a\u00020\b2\u0006\u0010A\u001a\u00020\bH\u0014R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u0010\u0010&\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010*\u001a\b\u0012\u0004\u0012\u00020,0+X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001c\u00101\u001a\u0004\u0018\u00010\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u001a\"\u0004\b3\u0010\u001cR\u001a\u00104\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010#\"\u0004\b6\u0010%R\u000e\u00107\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006C"}, d2 = {"Lcom/ido/life/customview/RecentSituationProgressView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "TRIANGLE_HEIGHT", "", "TRIANGLE_WIDTH", "mCurrProgress", "", "getMCurrProgress", "()F", "setMCurrProgress", "(F)V", "mIndicatorColor", "mIndicatorLabelColor", "mIndicatorLabelSize", "mIndicatorProgressDistance", "mLabelPaddingLeft", "mLabelPaddingRight", "mLabelPaddingTop", "mLeftLabel", "", "getMLeftLabel", "()Ljava/lang/String;", "setMLeftLabel", "(Ljava/lang/String;)V", "mLeftLabelDistance", "mLeftLabelSize", "mMaxLabelDistance", "mMaxLevelLabel", "mMinLabelColor", "getMMinLabelColor", "()I", "setMMinLabelColor", "(I)V", "mMinLevelLabel", "mPaint", "Landroid/graphics/Paint;", "mProgressHeight", "mPropertyList", "", "Lcom/ido/life/customview/RecentSituationProgressView$DividerProperty;", "getMPropertyList", "()Ljava/util/List;", "setMPropertyList", "(Ljava/util/List;)V", "mRightLabel", "getMRightLabel", "setMRightLabel", "mRightLabelColor", "getMRightLabelColor", "setMRightLabelColor", "mRightLabelSize", "mlabePaddingBottom", "findProgressProperty", "value", "onDraw", "", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "DividerProperty", "app_release"}, k = 1, mv = {1, 1, 16})
public final class RecentSituationProgressView extends View {
    private int TRIANGLE_HEIGHT;
    private int TRIANGLE_WIDTH;
    private HashMap _$_findViewCache;
    private float mCurrProgress;
    private int mIndicatorColor;
    private int mIndicatorLabelColor;
    private int mIndicatorLabelSize;
    private int mIndicatorProgressDistance;
    private int mLabelPaddingLeft;
    private int mLabelPaddingRight;
    private int mLabelPaddingTop;
    private String mLeftLabel;
    private int mLeftLabelDistance;
    private int mLeftLabelSize;
    private int mMaxLabelDistance;
    private String mMaxLevelLabel;
    private int mMinLabelColor;
    private String mMinLevelLabel;
    private Paint mPaint;
    private int mProgressHeight;
    private List<DividerProperty> mPropertyList;
    private String mRightLabel;
    private int mRightLabelColor;
    private int mRightLabelSize;
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
    public RecentSituationProgressView(Context context, AttributeSet attributeSet) {
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
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RecentSituationProgressView);
        Intrinsics.checkExpressionValueIsNotNull(typedArrayObtainStyledAttributes, "context.obtainStyledAttr…entSituationProgressView)");
        this.mProgressHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(16, this.mProgressHeight);
        this.mCurrProgress = typedArrayObtainStyledAttributes.getFloat(15, this.mCurrProgress);
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
        this.mMinLevelLabel = typedArrayObtainStyledAttributes.getString(14);
        this.mMaxLevelLabel = typedArrayObtainStyledAttributes.getString(13);
        this.mRightLabel = typedArrayObtainStyledAttributes.getString(17);
        this.mRightLabelColor = typedArrayObtainStyledAttributes.getColor(18, ViewCompat.MEASURED_STATE_MASK);
        this.mRightLabelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(20, 0);
        this.mLeftLabel = typedArrayObtainStyledAttributes.getString(9);
        this.mMinLabelColor = typedArrayObtainStyledAttributes.getColor(10, ViewCompat.MEASURED_STATE_MASK);
        this.mLeftLabelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(12, 0);
        this.mLeftLabelDistance = typedArrayObtainStyledAttributes.getDimensionPixelSize(11, this.mLeftLabelDistance);
        this.mMaxLabelDistance = typedArrayObtainStyledAttributes.getDimensionPixelSize(19, this.mMaxLabelDistance);
        typedArrayObtainStyledAttributes.recycle();
        this.TRIANGLE_HEIGHT = getResources().getDimensionPixelSize(com.boat.Xtend.two.R.dimen.sw_dp_6);
        this.TRIANGLE_WIDTH = getResources().getDimensionPixelSize(com.boat.Xtend.two.R.dimen.sw_dp_6);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setDither(true);
        this.mPaint.setAntiAlias(true);
    }

    public final float getMCurrProgress() {
        return this.mCurrProgress;
    }

    public final void setMCurrProgress(float f2) {
        this.mCurrProgress = f2;
    }

    public final String getMRightLabel() {
        return this.mRightLabel;
    }

    public final void setMRightLabel(String str) {
        this.mRightLabel = str;
    }

    public final int getMRightLabelColor() {
        return this.mRightLabelColor;
    }

    public final void setMRightLabelColor(int i) {
        this.mRightLabelColor = i;
    }

    public final String getMLeftLabel() {
        return this.mLeftLabel;
    }

    public final void setMLeftLabel(String str) {
        this.mLeftLabel = str;
    }

    public final int getMMinLabelColor() {
        return this.mMinLabelColor;
    }

    public final void setMMinLabelColor(int i) {
        this.mMinLabelColor = i;
    }

    public final List<DividerProperty> getMPropertyList() {
        return this.mPropertyList;
    }

    public final void setMPropertyList(List<DividerProperty> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.mPropertyList = list;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0234  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x02a6  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x02c8  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x01a2  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onDraw(android.graphics.Canvas r22) {
        /*
            Method dump skipped, instruction units count: 900
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.customview.RecentSituationProgressView.onDraw(android.graphics.Canvas):void");
    }

    private final int findProgressProperty(float value) {
        if (this.mPropertyList.isEmpty()) {
            return -1;
        }
        int size = this.mPropertyList.size();
        for (int i = 0; i < size; i++) {
            DividerProperty dividerProperty = this.mPropertyList.get(i);
            if (i == 0) {
                if (value < dividerProperty.getValueEnd()) {
                    return i;
                }
            } else if (i == size - 1) {
                if (value >= dividerProperty.getValueStart()) {
                    return i;
                }
            } else {
                if (value >= dividerProperty.getValueStart() && value < dividerProperty.getValueEnd()) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        int size2 = View.MeasureSpec.getSize(heightMeasureSpec);
        if (View.MeasureSpec.getMode(heightMeasureSpec) != 1073741824) {
            int iMax = this.mProgressHeight;
            String str = this.mLeftLabel;
            boolean z = true;
            if (!(str == null || str.length() == 0)) {
                this.mPaint.setTextSize(this.mLeftLabelSize);
                Rect rect = new Rect();
                Paint paint = this.mPaint;
                String str2 = this.mLeftLabel;
                if (str2 == null) {
                    Intrinsics.throwNpe();
                }
                String str3 = this.mLeftLabel;
                if (str3 == null) {
                    Intrinsics.throwNpe();
                }
                paint.getTextBounds(str2, 0, str3.length(), rect);
                iMax = Math.max(iMax, rect.height());
            }
            String str4 = this.mRightLabel;
            if (str4 != null && str4.length() != 0) {
                z = false;
            }
            if (!z) {
                this.mPaint.setTextSize(this.mRightLabelSize);
                Rect rect2 = new Rect();
                Paint paint2 = this.mPaint;
                String str5 = this.mRightLabel;
                if (str5 == null) {
                    Intrinsics.throwNpe();
                }
                String str6 = this.mRightLabel;
                if (str6 == null) {
                    Intrinsics.throwNpe();
                }
                paint2.getTextBounds(str5, 0, str6.length(), rect2);
                iMax = Math.max(iMax, rect2.height());
            }
            size2 = this.TRIANGLE_HEIGHT + iMax + this.mIndicatorProgressDistance + this.mLabelPaddingTop + this.mlabePaddingBottom;
        }
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setTextSize(this.mIndicatorLabelSize);
        Rect rect3 = new Rect();
        this.mPaint.getTextBounds("Hello", 0, 5, rect3);
        setMeasuredDimension(size, size2 + rect3.height());
    }

    /* JADX INFO: compiled from: RecentSituationProgressView.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005¢\u0006\u0002\u0010\u000bJ\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\bHÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\t\u0010#\u001a\u00020\u0005HÆ\u0003JE\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u0005HÆ\u0001J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020\u0003HÖ\u0001J\t\u0010)\u001a\u00020\bHÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0015\"\u0004\b\u0019\u0010\u0017R\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0015\"\u0004\b\u001b\u0010\u0017R\u001a\u0010\t\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u0017¨\u0006*"}, d2 = {"Lcom/ido/life/customview/RecentSituationProgressView$DividerProperty;", "", "color", "", "radius", "", "space", "label", "", "valueStart", "valueEnd", "(IFFLjava/lang/String;FF)V", "getColor", "()I", "setColor", "(I)V", "getLabel", "()Ljava/lang/String;", "setLabel", "(Ljava/lang/String;)V", "getRadius", "()F", "setRadius", "(F)V", "getSpace", "setSpace", "getValueEnd", "setValueEnd", "getValueStart", "setValueStart", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class DividerProperty {
        private int color;
        private String label;
        private float radius;
        private float space;
        private float valueEnd;
        private float valueStart;

        public static /* synthetic */ DividerProperty copy$default(DividerProperty dividerProperty, int i, float f2, float f3, String str, float f4, float f5, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = dividerProperty.color;
            }
            if ((i2 & 2) != 0) {
                f2 = dividerProperty.radius;
            }
            float f6 = f2;
            if ((i2 & 4) != 0) {
                f3 = dividerProperty.space;
            }
            float f7 = f3;
            if ((i2 & 8) != 0) {
                str = dividerProperty.label;
            }
            String str2 = str;
            if ((i2 & 16) != 0) {
                f4 = dividerProperty.valueStart;
            }
            float f8 = f4;
            if ((i2 & 32) != 0) {
                f5 = dividerProperty.valueEnd;
            }
            return dividerProperty.copy(i, f6, f7, str2, f8, f5);
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

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final float getValueStart() {
            return this.valueStart;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final float getValueEnd() {
            return this.valueEnd;
        }

        public final DividerProperty copy(int color, float radius, float space, String label, float valueStart, float valueEnd) {
            Intrinsics.checkParameterIsNotNull(label, "label");
            return new DividerProperty(color, radius, space, label, valueStart, valueEnd);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DividerProperty)) {
                return false;
            }
            DividerProperty dividerProperty = (DividerProperty) other;
            return this.color == dividerProperty.color && Float.compare(this.radius, dividerProperty.radius) == 0 && Float.compare(this.space, dividerProperty.space) == 0 && Intrinsics.areEqual(this.label, dividerProperty.label) && Float.compare(this.valueStart, dividerProperty.valueStart) == 0 && Float.compare(this.valueEnd, dividerProperty.valueEnd) == 0;
        }

        public int hashCode() {
            int iHashCode = ((((Integer.valueOf(this.color).hashCode() * 31) + Float.valueOf(this.radius).hashCode()) * 31) + Float.valueOf(this.space).hashCode()) * 31;
            String str = this.label;
            return ((((iHashCode + (str != null ? str.hashCode() : 0)) * 31) + Float.valueOf(this.valueStart).hashCode()) * 31) + Float.valueOf(this.valueEnd).hashCode();
        }

        public String toString() {
            return "DividerProperty(color=" + this.color + ", radius=" + this.radius + ", space=" + this.space + ", label=" + this.label + ", valueStart=" + this.valueStart + ", valueEnd=" + this.valueEnd + ")";
        }

        public DividerProperty(int i, float f2, float f3, String label, float f4, float f5) {
            Intrinsics.checkParameterIsNotNull(label, "label");
            this.color = i;
            this.radius = f2;
            this.space = f3;
            this.label = label;
            this.valueStart = f4;
            this.valueEnd = f5;
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

        public final float getValueStart() {
            return this.valueStart;
        }

        public final void setValueStart(float f2) {
            this.valueStart = f2;
        }

        public final float getValueEnd() {
            return this.valueEnd;
        }

        public final void setValueEnd(float f2) {
            this.valueEnd = f2;
        }
    }
}