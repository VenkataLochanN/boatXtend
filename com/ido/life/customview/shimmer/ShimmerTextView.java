package com.ido.life.customview.shimmer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import com.ido.life.R;
import com.ido.life.customview.shimmer.Shimmer;

/* JADX INFO: loaded from: classes2.dex */
public class ShimmerTextView extends AppCompatTextView {
    private final Paint mContentPaint;
    private final ShimmerDrawable mShimmerDrawable;

    public ShimmerTextView(Context context) {
        super(context);
        this.mContentPaint = new Paint();
        this.mShimmerDrawable = new ShimmerDrawable();
        init(context, null);
    }

    public ShimmerTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContentPaint = new Paint();
        this.mShimmerDrawable = new ShimmerDrawable();
        init(context, attributeSet);
    }

    public ShimmerTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContentPaint = new Paint();
        this.mShimmerDrawable = new ShimmerDrawable();
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        setWillNotDraw(false);
        this.mShimmerDrawable.setCallback(this);
        if (attributeSet == null) {
            setShimmer(new Shimmer.AlphaHighlightBuilder().build());
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ShimmerTextView, 0, 0);
        try {
            setShimmer(((typedArrayObtainStyledAttributes.hasValue(4) && typedArrayObtainStyledAttributes.getBoolean(4, false)) ? new Shimmer.ColorHighlightBuilder() : new Shimmer.AlphaHighlightBuilder()).consumeAttributes(typedArrayObtainStyledAttributes).build());
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public ShimmerTextView setShimmer(Shimmer shimmer) {
        this.mShimmerDrawable.setShimmer(shimmer);
        if (shimmer != null && shimmer.clipToChildren) {
            setLayerType(2, this.mContentPaint);
        } else {
            setLayerType(0, null);
        }
        return this;
    }

    public void startShimmer() {
        this.mShimmerDrawable.startShimmer();
    }

    public void stopShimmer() {
        this.mShimmerDrawable.stopShimmer();
    }

    public boolean isShimmerStarted() {
        return this.mShimmerDrawable.isShimmerStarted();
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mShimmerDrawable.setBounds(0, 0, getWidth(), getHeight());
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mShimmerDrawable.maybeStartShimmer();
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopShimmer();
    }

    @Override // android.view.View
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        this.mShimmerDrawable.draw(canvas);
    }

    @Override // android.widget.TextView, android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.mShimmerDrawable;
    }
}