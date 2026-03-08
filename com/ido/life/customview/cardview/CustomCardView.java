package com.ido.life.customview.cardview;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes2.dex */
public class CustomCardView extends FrameLayout {
    private static final int[] COLOR_BACKGROUND_ATTR = {R.attr.colorBackground};
    private static final CardViewImpl IMPL = new CardViewBaseImpl();
    private final CardViewDelegate mCardViewDelegate;
    private boolean mCompatPadding;
    final Rect mContentPadding;
    private boolean mPreventCornerOverlap;
    final Rect mShadowBounds;
    private int mShadowEndColor;
    private int mShadowStartColor;
    int mUserSetMinHeight;
    int mUserSetMinWidth;

    @Override // android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
    }

    @Override // android.view.View
    public void setPaddingRelative(int i, int i2, int i3, int i4) {
    }

    static {
        IMPL.initStatic();
    }

    public CustomCardView(Context context) {
        this(context, null);
    }

    public CustomCardView(Context context, AttributeSet attributeSet) {
        int color;
        ColorStateList colorStateListValueOf;
        super(context, attributeSet);
        this.mShadowStartColor = -1;
        this.mShadowEndColor = -3355444;
        this.mContentPadding = new Rect();
        this.mShadowBounds = new Rect();
        this.mCardViewDelegate = new CardViewDelegate() { // from class: com.ido.life.customview.cardview.CustomCardView.1
            private Drawable mCardBackground;

            @Override // com.ido.life.customview.cardview.CardViewDelegate
            public void setCardBackground(Drawable drawable) {
                this.mCardBackground = drawable;
                CustomCardView.this.setBackgroundDrawable(drawable);
            }

            @Override // com.ido.life.customview.cardview.CardViewDelegate
            public boolean getUseCompatPadding() {
                return CustomCardView.this.getUseCompatPadding();
            }

            @Override // com.ido.life.customview.cardview.CardViewDelegate
            public boolean getPreventCornerOverlap() {
                return CustomCardView.this.getPreventCornerOverlap();
            }

            @Override // com.ido.life.customview.cardview.CardViewDelegate
            public void setShadowPadding(int i, int i2, int i3, int i4) {
                CustomCardView.this.mShadowBounds.set(i, i2, i3, i4);
                CustomCardView customCardView = CustomCardView.this;
                CustomCardView.super.setPadding(i + customCardView.mContentPadding.left, i2 + CustomCardView.this.mContentPadding.top, i3 + CustomCardView.this.mContentPadding.right, i4 + CustomCardView.this.mContentPadding.bottom);
            }

            @Override // com.ido.life.customview.cardview.CardViewDelegate
            public void setMinWidthHeightInternal(int i, int i2) {
                if (i > CustomCardView.this.mUserSetMinWidth) {
                    CustomCardView.super.setMinimumWidth(i);
                }
                if (i2 > CustomCardView.this.mUserSetMinHeight) {
                    CustomCardView.super.setMinimumHeight(i2);
                }
            }

            @Override // com.ido.life.customview.cardview.CardViewDelegate
            public Drawable getCardBackground() {
                return this.mCardBackground;
            }

            @Override // com.ido.life.customview.cardview.CardViewDelegate
            public View getCardView() {
                return CustomCardView.this;
            }
        };
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.ido.life.R.styleable.CustomCardView);
        if (typedArrayObtainStyledAttributes.hasValue(0)) {
            colorStateListValueOf = ColorStateList.valueOf(typedArrayObtainStyledAttributes.getColor(0, -1));
        } else {
            TypedArray typedArrayObtainStyledAttributes2 = getContext().obtainStyledAttributes(COLOR_BACKGROUND_ATTR);
            int color2 = typedArrayObtainStyledAttributes2.getColor(0, 0);
            typedArrayObtainStyledAttributes2.recycle();
            float[] fArr = new float[3];
            Color.colorToHSV(color2, fArr);
            if (fArr[2] > 0.5f) {
                color = getResources().getColor(com.boat.Xtend.two.R.color.cardview_light_background);
            } else {
                color = getResources().getColor(com.boat.Xtend.two.R.color.cardview_dark_background);
            }
            colorStateListValueOf = ColorStateList.valueOf(color);
        }
        float dimension = typedArrayObtainStyledAttributes.getDimension(1, 0.0f);
        float dimension2 = typedArrayObtainStyledAttributes.getDimension(2, 0.0f);
        float dimension3 = typedArrayObtainStyledAttributes.getDimension(4, 0.0f);
        this.mCompatPadding = typedArrayObtainStyledAttributes.getBoolean(7, false);
        this.mPreventCornerOverlap = typedArrayObtainStyledAttributes.getBoolean(5, true);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(8, 0);
        this.mContentPadding.left = typedArrayObtainStyledAttributes.getDimensionPixelSize(10, dimensionPixelSize);
        this.mContentPadding.top = typedArrayObtainStyledAttributes.getDimensionPixelSize(12, dimensionPixelSize);
        this.mContentPadding.right = typedArrayObtainStyledAttributes.getDimensionPixelSize(11, dimensionPixelSize);
        this.mContentPadding.bottom = typedArrayObtainStyledAttributes.getDimensionPixelSize(9, dimensionPixelSize);
        float f2 = dimension2 > dimension3 ? dimension2 : dimension3;
        this.mUserSetMinWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(14, 0);
        this.mUserSetMinHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(13, 0);
        this.mShadowStartColor = typedArrayObtainStyledAttributes.getColor(6, this.mShadowStartColor);
        this.mShadowEndColor = typedArrayObtainStyledAttributes.getColor(3, this.mShadowEndColor);
        typedArrayObtainStyledAttributes.recycle();
        IMPL.initialize(this.mCardViewDelegate, context, colorStateListValueOf, dimension, dimension2, f2, this.mShadowStartColor, this.mShadowEndColor);
    }

    public boolean getUseCompatPadding() {
        return this.mCompatPadding;
    }

    public void setUseCompatPadding(boolean z) {
        if (this.mCompatPadding != z) {
            this.mCompatPadding = z;
            IMPL.onCompatPaddingChanged(this.mCardViewDelegate);
        }
    }

    public void setContentPadding(int i, int i2, int i3, int i4) {
        this.mContentPadding.set(i, i2, i3, i4);
        IMPL.updatePadding(this.mCardViewDelegate);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (!(IMPL instanceof CardViewApi21Impl)) {
            int mode = View.MeasureSpec.getMode(i);
            if (mode == Integer.MIN_VALUE || mode == 1073741824) {
                i = View.MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil(IMPL.getMinWidth(this.mCardViewDelegate)), View.MeasureSpec.getSize(i)), mode);
            }
            int mode2 = View.MeasureSpec.getMode(i2);
            if (mode2 == Integer.MIN_VALUE || mode2 == 1073741824) {
                i2 = View.MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil(IMPL.getMinHeight(this.mCardViewDelegate)), View.MeasureSpec.getSize(i2)), mode2);
            }
            super.onMeasure(i, i2);
            return;
        }
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    public void setMinimumWidth(int i) {
        this.mUserSetMinWidth = i;
        super.setMinimumWidth(i);
    }

    @Override // android.view.View
    public void setMinimumHeight(int i) {
        this.mUserSetMinHeight = i;
        super.setMinimumHeight(i);
    }

    public void setCardBackgroundColor(int i) {
        IMPL.setBackgroundColor(this.mCardViewDelegate, ColorStateList.valueOf(i));
    }

    public void setCardBackgroundColor(ColorStateList colorStateList) {
        IMPL.setBackgroundColor(this.mCardViewDelegate, colorStateList);
    }

    public ColorStateList getCardBackgroundColor() {
        return IMPL.getBackgroundColor(this.mCardViewDelegate);
    }

    public int getContentPaddingLeft() {
        return this.mContentPadding.left;
    }

    public int getContentPaddingRight() {
        return this.mContentPadding.right;
    }

    public int getContentPaddingTop() {
        return this.mContentPadding.top;
    }

    public int getContentPaddingBottom() {
        return this.mContentPadding.bottom;
    }

    public void setRadius(float f2) {
        IMPL.setRadius(this.mCardViewDelegate, f2);
    }

    public float getRadius() {
        return IMPL.getRadius(this.mCardViewDelegate);
    }

    public void setCardElevation(float f2) {
        IMPL.setElevation(this.mCardViewDelegate, f2);
    }

    public float getCardElevation() {
        return IMPL.getElevation(this.mCardViewDelegate);
    }

    public void setMaxCardElevation(float f2) {
        IMPL.setMaxElevation(this.mCardViewDelegate, f2);
    }

    public float getMaxCardElevation() {
        return IMPL.getMaxElevation(this.mCardViewDelegate);
    }

    public boolean getPreventCornerOverlap() {
        return this.mPreventCornerOverlap;
    }

    public void setPreventCornerOverlap(boolean z) {
        if (z != this.mPreventCornerOverlap) {
            this.mPreventCornerOverlap = z;
            IMPL.onPreventCornerOverlapChanged(this.mCardViewDelegate);
        }
    }
}