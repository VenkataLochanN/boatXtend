package com.ido.common.widget.viewgroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import com.ido.common.R;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class FlowLayout extends ViewGroup {
    private static final int DEFAULT_CHILD_SPACING = 0;
    private static final int DEFAULT_CHILD_SPACING_FOR_LAST_ROW = -65538;
    private static final boolean DEFAULT_FLOW = true;
    private static final int DEFAULT_MAX_ROWS = Integer.MAX_VALUE;
    private static final float DEFAULT_ROW_SPACING = 0.0f;
    private static final boolean DEFAULT_RTL = false;
    private static final String LOG_TAG = FlowLayout.class.getSimpleName();
    private static final int ROW_VERTICAL_GRAVITY_AUTO = -65536;
    public static final int SPACING_ALIGN = -65537;
    public static final int SPACING_AUTO = -65536;
    private static final int SPACING_UNDEFINED = -65538;
    private static final int UNSPECIFIED_GRAVITY = -1;
    private float mAdjustedRowSpacing;
    private List<Integer> mChildNumForRow;
    private int mChildSpacing;
    private int mChildSpacingForLastRow;
    private int mExactMeasuredHeight;
    private boolean mFlow;
    private int mGravity;
    private List<Integer> mHeightForRow;
    private List<Float> mHorizontalSpacingForRow;
    private int mMaxRows;
    private int mMinChildSpacing;
    private float mRowSpacing;
    private int mRowVerticalGravity;
    private boolean mRtl;
    private List<Integer> mWidthForRow;

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mFlow = true;
        this.mChildSpacing = 0;
        this.mMinChildSpacing = 0;
        this.mChildSpacingForLastRow = -65538;
        this.mRowSpacing = 0.0f;
        this.mAdjustedRowSpacing = 0.0f;
        this.mRtl = false;
        this.mMaxRows = Integer.MAX_VALUE;
        this.mGravity = -1;
        this.mRowVerticalGravity = -65536;
        this.mHorizontalSpacingForRow = new ArrayList();
        this.mHeightForRow = new ArrayList();
        this.mWidthForRow = new ArrayList();
        this.mChildNumForRow = new ArrayList();
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.FlowLayout, 0, 0);
        try {
            this.mFlow = typedArrayObtainStyledAttributes.getBoolean(R.styleable.FlowLayout_flFlow, true);
            try {
                this.mChildSpacing = typedArrayObtainStyledAttributes.getInt(R.styleable.FlowLayout_flChildSpacing, 0);
            } catch (NumberFormatException unused) {
                this.mChildSpacing = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.FlowLayout_flChildSpacing, (int) dpToPx(0.0f));
            }
            try {
                this.mMinChildSpacing = typedArrayObtainStyledAttributes.getInt(R.styleable.FlowLayout_flMinChildSpacing, 0);
            } catch (NumberFormatException unused2) {
                this.mMinChildSpacing = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.FlowLayout_flMinChildSpacing, (int) dpToPx(0.0f));
            }
            try {
                this.mChildSpacingForLastRow = typedArrayObtainStyledAttributes.getInt(R.styleable.FlowLayout_flChildSpacingForLastRow, -65538);
            } catch (NumberFormatException unused3) {
                this.mChildSpacingForLastRow = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.FlowLayout_flChildSpacingForLastRow, (int) dpToPx(0.0f));
            }
            try {
                this.mRowSpacing = typedArrayObtainStyledAttributes.getInt(R.styleable.FlowLayout_flRowSpacing, 0);
            } catch (NumberFormatException unused4) {
                this.mRowSpacing = typedArrayObtainStyledAttributes.getDimension(R.styleable.FlowLayout_flRowSpacing, dpToPx(0.0f));
            }
            this.mMaxRows = typedArrayObtainStyledAttributes.getInt(R.styleable.FlowLayout_flMaxRows, Integer.MAX_VALUE);
            this.mRtl = typedArrayObtainStyledAttributes.getBoolean(R.styleable.FlowLayout_flRtl, false);
            this.mGravity = typedArrayObtainStyledAttributes.getInt(R.styleable.FlowLayout_android_gravity, -1);
            this.mRowVerticalGravity = typedArrayObtainStyledAttributes.getInt(R.styleable.FlowLayout_flRowVerticalGravity, -65536);
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        int iMin;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        float f2;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        View view;
        int i15;
        int i16;
        int measuredWidth;
        int i17;
        int i18;
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i2);
        this.mHorizontalSpacingForRow.clear();
        this.mHeightForRow.clear();
        this.mWidthForRow.clear();
        this.mChildNumForRow.clear();
        int childCount = getChildCount();
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        boolean z = mode != 0 && this.mFlow;
        int i19 = -65536;
        int i20 = (this.mChildSpacing == -65536 && mode == 0) ? 0 : this.mChildSpacing;
        float f3 = i20 == -65536 ? this.mMinChildSpacing : i20;
        int i21 = 0;
        int i22 = 0;
        int i23 = 0;
        int iMax = 0;
        int i24 = 0;
        int i25 = 0;
        int iMax2 = 0;
        while (i23 < childCount) {
            float f4 = f3;
            View childAt = getChildAt(i23);
            int i26 = i21;
            if (childAt.getVisibility() == 8) {
                i5 = i23;
                i17 = i20;
                i7 = mode;
                i8 = mode2;
                i9 = childCount;
                f2 = f4;
                i11 = -65536;
                measuredWidth = i22;
                i12 = size;
                i18 = i26;
                i13 = size2;
            } else {
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    i9 = childCount;
                    i10 = i26;
                    i13 = size2;
                    i14 = i22;
                    i5 = i23;
                    i8 = mode2;
                    f2 = f4;
                    i12 = size;
                    view = childAt;
                    i6 = i20;
                    i7 = mode;
                    i11 = -65536;
                    measureChildWithMargins(childAt, i, 0, i2, i25);
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    i15 = marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
                    i16 = marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
                } else {
                    i5 = i23;
                    i6 = i20;
                    i7 = mode;
                    i8 = mode2;
                    i9 = childCount;
                    f2 = f4;
                    i10 = i26;
                    i11 = -65536;
                    i12 = size;
                    i13 = size2;
                    i14 = i22;
                    view = childAt;
                    measureChild(view, i, i2);
                    i15 = 0;
                    i16 = 0;
                }
                measuredWidth = view.getMeasuredWidth() + i15;
                int measuredHeight = view.getMeasuredHeight() + i16;
                if (z && i24 + measuredWidth > paddingLeft) {
                    i17 = i6;
                    this.mHorizontalSpacingForRow.add(Float.valueOf(getSpacingForRow(i17, paddingLeft, i14, i10)));
                    this.mChildNumForRow.add(Integer.valueOf(i10));
                    this.mHeightForRow.add(Integer.valueOf(iMax2));
                    int i27 = (int) f2;
                    this.mWidthForRow.add(Integer.valueOf(i24 - i27));
                    if (this.mHorizontalSpacingForRow.size() <= this.mMaxRows) {
                        i25 += iMax2;
                    }
                    iMax2 = measuredHeight;
                    iMax = Math.max(iMax, i24);
                    i24 = i27 + measuredWidth;
                    i18 = 1;
                } else {
                    i17 = i6;
                    i18 = i10 + 1;
                    int i28 = (int) (i24 + measuredWidth + f2);
                    measuredWidth += i14;
                    iMax2 = Math.max(iMax2, measuredHeight);
                    i24 = i28;
                }
            }
            i23 = i5 + 1;
            i22 = measuredWidth;
            i20 = i17;
            i21 = i18;
            i19 = i11;
            f3 = f2;
            size = i12;
            size2 = i13;
            mode = i7;
            childCount = i9;
            mode2 = i8;
        }
        int i29 = i21;
        int i30 = size;
        int i31 = mode;
        int i32 = size2;
        int i33 = mode2;
        int i34 = i22;
        float f5 = f3;
        int i35 = i19;
        int i36 = iMax2;
        int i37 = i20;
        int i38 = this.mChildSpacingForLastRow;
        if (i38 == -65537) {
            if (this.mHorizontalSpacingForRow.size() >= 1) {
                List<Float> list = this.mHorizontalSpacingForRow;
                list.add(list.get(list.size() - 1));
            } else {
                this.mHorizontalSpacingForRow.add(Float.valueOf(getSpacingForRow(i37, paddingLeft, i34, i29)));
            }
        } else if (i38 != -65538) {
            this.mHorizontalSpacingForRow.add(Float.valueOf(getSpacingForRow(i38, paddingLeft, i34, i29)));
        } else {
            this.mHorizontalSpacingForRow.add(Float.valueOf(getSpacingForRow(i37, paddingLeft, i34, i29)));
        }
        this.mChildNumForRow.add(Integer.valueOf(i29));
        this.mHeightForRow.add(Integer.valueOf(i36));
        this.mWidthForRow.add(Integer.valueOf(i24 - ((int) f5)));
        if (this.mHorizontalSpacingForRow.size() <= this.mMaxRows) {
            i25 += i36;
        }
        int iMax3 = Math.max(iMax, i24);
        if (i37 == i35) {
            i3 = i30;
            iMin = i3;
        } else if (i31 == 0) {
            iMin = iMax3 + getPaddingLeft() + getPaddingRight();
            i3 = i30;
        } else {
            i3 = i30;
            iMin = Math.min(iMax3 + getPaddingLeft() + getPaddingRight(), i3);
        }
        int paddingTop = i25 + getPaddingTop() + getPaddingBottom();
        int iMin2 = Math.min(this.mHorizontalSpacingForRow.size(), this.mMaxRows);
        float f6 = (this.mRowSpacing == -65536.0f && i33 == 0) ? 0.0f : this.mRowSpacing;
        if (f6 == -65536.0f) {
            if (iMin2 > 1) {
                this.mAdjustedRowSpacing = (i32 - paddingTop) / (iMin2 - 1);
            } else {
                this.mAdjustedRowSpacing = 0.0f;
            }
            i4 = i32;
            paddingTop = i4;
        } else {
            this.mAdjustedRowSpacing = f6;
            if (iMin2 > 1) {
                int i39 = (int) (paddingTop + (this.mAdjustedRowSpacing * (iMin2 - 1)));
                if (i33 == 0) {
                    paddingTop = i39;
                    i4 = i32;
                } else {
                    i4 = i32;
                    paddingTop = Math.min(i39, i4);
                }
            } else {
                i4 = i32;
            }
        }
        this.mExactMeasuredHeight = paddingTop;
        if (i31 == 1073741824) {
            iMin = i3;
        }
        if (i33 != 1073741824) {
            i4 = paddingTop;
        }
        setMeasuredDimension(iMin, i4);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0057  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onLayout(boolean r23, int r24, int r25, int r26, int r27) {
        /*
            Method dump skipped, instruction units count: 369
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.common.widget.viewgroup.FlowLayout.onLayout(boolean, int, int, int, int):void");
    }

    private int getHorizontalGravityOffsetForRow(int i, int i2, int i3, int i4) {
        if (this.mChildSpacing == -65536 || i4 >= this.mWidthForRow.size() || i4 >= this.mChildNumForRow.size() || this.mChildNumForRow.get(i4).intValue() <= 0) {
            return 0;
        }
        if (i == 1) {
            return ((i2 - i3) - this.mWidthForRow.get(i4).intValue()) / 2;
        }
        if (i != 5) {
            return 0;
        }
        return (i2 - i3) - this.mWidthForRow.get(i4).intValue();
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new ViewGroup.MarginLayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    public boolean isFlow() {
        return this.mFlow;
    }

    public void setFlow(boolean z) {
        this.mFlow = z;
        requestLayout();
    }

    public int getChildSpacing() {
        return this.mChildSpacing;
    }

    public void setChildSpacing(int i) {
        this.mChildSpacing = i;
        requestLayout();
    }

    public int getChildSpacingForLastRow() {
        return this.mChildSpacingForLastRow;
    }

    public void setChildSpacingForLastRow(int i) {
        this.mChildSpacingForLastRow = i;
        requestLayout();
    }

    public float getRowSpacing() {
        return this.mRowSpacing;
    }

    public void setRowSpacing(float f2) {
        this.mRowSpacing = f2;
        requestLayout();
    }

    public int getMaxRows() {
        return this.mMaxRows;
    }

    public void setMaxRows(int i) {
        this.mMaxRows = i;
        requestLayout();
    }

    public void setGravity(int i) {
        if (this.mGravity != i) {
            this.mGravity = i;
            requestLayout();
        }
    }

    public void setRowVerticalGravity(int i) {
        if (this.mRowVerticalGravity != i) {
            this.mRowVerticalGravity = i;
            requestLayout();
        }
    }

    public boolean isRtl() {
        return this.mRtl;
    }

    public void setRtl(boolean z) {
        this.mRtl = z;
        requestLayout();
    }

    public int getMinChildSpacing() {
        return this.mMinChildSpacing;
    }

    public void setMinChildSpacing(int i) {
        this.mMinChildSpacing = i;
        requestLayout();
    }

    private float getSpacingForRow(int i, int i2, int i3, int i4) {
        if (i != -65536) {
            return i;
        }
        if (i4 > 1) {
            return (i2 - i3) / (i4 - 1);
        }
        return 0.0f;
    }

    private float dpToPx(float f2) {
        return TypedValue.applyDimension(1, f2, getResources().getDisplayMetrics());
    }
}