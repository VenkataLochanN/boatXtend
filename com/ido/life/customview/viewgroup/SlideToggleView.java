package com.ido.life.customview.viewgroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.customview.widget.ViewDragHelper;
import com.ido.common.utils.DipPixelUtil;
import com.ido.life.R;
import com.ido.life.customview.shimmer.ShimmerTextView;

/* JADX INFO: loaded from: classes2.dex */
public class SlideToggleView extends FrameLayout {
    private int mBlockBottomMargin;
    private int mBlockLeftMargin;
    private int mBlockRightMargin;
    private int mBlockTopMargin;
    private ImageView mBlockView;
    private ViewDragHelper.Callback mDragCallback;
    private int mRemainDistance;
    private ShimmerTextView mShimmerTextView;
    private SlideToggleListener mSlideToggleListener;
    private ViewDragHelper mViewDragHelper;

    public interface SlideToggleListener {
        void onBlockPositionChanged(SlideToggleView slideToggleView, int i, int i2, int i3);

        void onSlideOpen(SlideToggleView slideToggleView);
    }

    public SlideToggleView(Context context) {
        this(context, null);
    }

    public SlideToggleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SlideToggleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDragCallback = new ViewDragHelper.Callback() { // from class: com.ido.life.customview.viewgroup.SlideToggleView.1
            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public boolean tryCaptureView(View view, int i2) {
                return view == SlideToggleView.this.mBlockView;
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void onViewCaptured(View view, int i2) {
                ViewParent parent = view.getParent();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int clampViewPositionHorizontal(View view, int i2, int i3) {
                int paddingLeft = SlideToggleView.this.getPaddingLeft() + SlideToggleView.this.mBlockLeftMargin;
                if (i2 >= paddingLeft) {
                    paddingLeft = i2;
                }
                int measuredWidth = ((SlideToggleView.this.getMeasuredWidth() - SlideToggleView.this.getPaddingRight()) - SlideToggleView.this.mBlockRightMargin) - SlideToggleView.this.mBlockView.getMeasuredWidth();
                return paddingLeft > measuredWidth ? measuredWidth : paddingLeft;
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int clampViewPositionVertical(View view, int i2, int i3) {
                return SlideToggleView.this.getPaddingTop() + SlideToggleView.this.mBlockTopMargin;
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void onViewPositionChanged(View view, int i2, int i3, int i4, int i5) {
                if (SlideToggleView.this.mSlideToggleListener != null) {
                    SlideToggleView.this.mSlideToggleListener.onBlockPositionChanged(SlideToggleView.this, i2, ((((SlideToggleView.this.getMeasuredWidth() - SlideToggleView.this.getPaddingLeft()) - SlideToggleView.this.getPaddingRight()) - SlideToggleView.this.mBlockLeftMargin) - SlideToggleView.this.mBlockRightMargin) - SlideToggleView.this.mBlockView.getMeasuredWidth(), (i2 - SlideToggleView.this.getPaddingLeft()) - SlideToggleView.this.mBlockLeftMargin);
                }
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void onViewReleased(View view, float f2, float f3) {
                if (view == SlideToggleView.this.mBlockView) {
                    if ((((((SlideToggleView.this.getMeasuredWidth() - SlideToggleView.this.getPaddingLeft()) - SlideToggleView.this.getPaddingRight()) - SlideToggleView.this.mBlockLeftMargin) - SlideToggleView.this.mBlockRightMargin) - SlideToggleView.this.mBlockView.getMeasuredWidth()) - ((view.getLeft() - SlideToggleView.this.getPaddingLeft()) - SlideToggleView.this.mBlockLeftMargin) > SlideToggleView.this.mRemainDistance) {
                        SlideToggleView.this.mViewDragHelper.settleCapturedViewAt(SlideToggleView.this.getPaddingLeft() + SlideToggleView.this.mBlockLeftMargin, SlideToggleView.this.getPaddingTop() + SlideToggleView.this.mBlockTopMargin);
                        SlideToggleView.this.invalidate();
                        return;
                    }
                    SlideToggleView.this.openToggle();
                    if (SlideToggleView.this.mSlideToggleListener != null) {
                        SlideToggleView.this.mSlideToggleListener.onSlideOpen(SlideToggleView.this);
                    }
                }
            }
        };
        initView(context, attributeSet, i);
        this.mViewDragHelper = ViewDragHelper.create(this, 1.0f, this.mDragCallback);
    }

    private void initView(Context context, AttributeSet attributeSet, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SlideToggleView, i, 0);
        String string = typedArrayObtainStyledAttributes.getString(6);
        int color = typedArrayObtainStyledAttributes.getColor(7, -1);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(8, DipPixelUtil.dip2px(14.0f));
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(5);
        this.mBlockLeftMargin = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 4);
        this.mBlockRightMargin = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, 4);
        this.mBlockTopMargin = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, 4);
        this.mBlockBottomMargin = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 4);
        this.mRemainDistance = typedArrayObtainStyledAttributes.getDimensionPixelSize(4, 10);
        typedArrayObtainStyledAttributes.recycle();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.mShimmerTextView = new ShimmerTextView(context);
        this.mShimmerTextView.setText(string);
        this.mShimmerTextView.setTextColor(color);
        this.mShimmerTextView.setPadding(DipPixelUtil.dip2px(65.0f), 0, 0, 0);
        this.mShimmerTextView.setTextSize(0, dimensionPixelSize);
        addView(this.mShimmerTextView, layoutParams);
        this.mBlockView = new ImageView(context);
        this.mBlockView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        this.mBlockView.setImageDrawable(drawable);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -1);
        layoutParams2.setMargins(this.mBlockLeftMargin, this.mBlockTopMargin, this.mBlockRightMargin, this.mBlockBottomMargin);
        addView(this.mBlockView, layoutParams2);
    }

    public void setText(String str) {
        this.mShimmerTextView.setText(str);
    }

    public void setTextColor(int i) {
        this.mShimmerTextView.setTextColor(i);
    }

    public void setTextSize(float f2) {
        this.mShimmerTextView.setTextSize(f2);
    }

    public void setBlockDrawable(Drawable drawable) {
        this.mBlockView.setImageDrawable(drawable);
    }

    public void setBlockMargin(int i, int i2, int i3, int i4) {
        this.mBlockLeftMargin = i;
        this.mBlockRightMargin = i3;
        this.mBlockTopMargin = i2;
        this.mBlockBottomMargin = i4;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mBlockView.getLayoutParams();
        layoutParams.setMargins(i, i2, i3, i4);
        this.mBlockView.setLayoutParams(layoutParams);
    }

    public void setRemainDistance(int i) {
        this.mRemainDistance = i;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.mViewDragHelper.shouldInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.mViewDragHelper.processTouchEvent(motionEvent);
        return true;
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.mViewDragHelper.continueSettling(true)) {
            invalidate();
        } else {
            super.computeScroll();
        }
    }

    public void setSlideToggleListener(SlideToggleListener slideToggleListener) {
        this.mSlideToggleListener = slideToggleListener;
    }

    public void openToggle() {
        this.mViewDragHelper.smoothSlideViewTo(this.mBlockView, ((getMeasuredWidth() - getPaddingRight()) - this.mBlockRightMargin) - this.mBlockView.getMeasuredWidth(), getPaddingTop() + this.mBlockTopMargin);
        invalidate();
    }

    public void closeToggle() {
        this.mViewDragHelper.smoothSlideViewTo(this.mBlockView, getPaddingLeft() + this.mBlockLeftMargin, getPaddingTop() + this.mBlockTopMargin);
        invalidate();
    }
}