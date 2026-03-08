package com.ido.life.customview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomItemDecoration.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010\"\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\u0007J(\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020+H\u0016J\u000e\u0010,\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\fJ\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\fJ\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\fJ \u0010-\u001a\u00020$2\u0006\u0010.\u001a\u00020/2\u0006\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020+H\u0016J\u000e\u00100\u001a\u00020\u00002\u0006\u00101\u001a\u00020\u0007J&\u00100\u001a\u00020\u00002\u0006\u0010 \u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u00102\u001a\u00020\nR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/ido/life/customview/CustomItemDecoration;", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "()V", "rv", "Landroidx/recyclerview/widget/RecyclerView;", "(Landroidx/recyclerview/widget/RecyclerView;)V", "bottomLeftRadius", "", "bottomRightRadius", "hasSetCorner", "", "mDividerHeight", "", "mPaint", "Landroid/graphics/Paint;", "marginLeft", "getMarginLeft", "()F", "setMarginLeft", "(F)V", "marginRight", "getMarginRight", "setMarginRight", "path", "Landroid/graphics/Path;", "rectF", "Landroid/graphics/RectF;", "showLast", "getShowLast", "()Z", "setShowLast", "(Z)V", "topLeftRadius", "topRightRadius", "color", "getItemOffsets", "", "outRect", "Landroid/graphics/Rect;", "view", "Landroid/view/View;", "parent", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "height", "onDraw", "c", "Landroid/graphics/Canvas;", "setCornerRadius", "radius", "show", "app_release"}, k = 1, mv = {1, 1, 16})
public final class CustomItemDecoration extends RecyclerView.ItemDecoration {
    private int bottomLeftRadius;
    private int bottomRightRadius;
    private boolean hasSetCorner;
    private float mDividerHeight;
    private Paint mPaint;
    private float marginLeft;
    private float marginRight;
    private Path path;
    private RectF rectF;
    private boolean showLast;
    private int topLeftRadius;
    private int topRightRadius;

    public final float getMarginLeft() {
        return this.marginLeft;
    }

    public final void setMarginLeft(float f2) {
        this.marginLeft = f2;
    }

    public final float getMarginRight() {
        return this.marginRight;
    }

    public final void setMarginRight(float f2) {
        this.marginRight = f2;
    }

    public final boolean getShowLast() {
        return this.showLast;
    }

    public final void setShowLast(boolean z) {
        this.showLast = z;
    }

    public CustomItemDecoration() {
        this.mDividerHeight = 1.0f;
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(-7829368);
    }

    public CustomItemDecoration(final RecyclerView rv) {
        Intrinsics.checkParameterIsNotNull(rv, "rv");
        this.mDividerHeight = 1.0f;
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(-7829368);
        rv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.ido.life.customview.CustomItemDecoration$listener$1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (this.this$0.hasSetCorner) {
                    float measuredHeight = rv.getMeasuredHeight();
                    if (this.this$0.rectF != null) {
                        RectF rectF = this.this$0.rectF;
                        if (rectF == null) {
                            Intrinsics.throwNpe();
                        }
                        if (rectF.height() == measuredHeight) {
                            return;
                        }
                    }
                    this.this$0.rectF = new RectF(0.0f, 0.0f, rv.getMeasuredWidth(), measuredHeight);
                    this.this$0.path = new Path();
                    Path path = this.this$0.path;
                    if (path != null) {
                        path.reset();
                    }
                    Path path2 = this.this$0.path;
                    if (path2 != null) {
                        RectF rectF2 = this.this$0.rectF;
                        if (rectF2 == null) {
                            Intrinsics.throwNpe();
                        }
                        path2.addRoundRect(rectF2, new float[]{this.this$0.topLeftRadius, this.this$0.topLeftRadius, this.this$0.topRightRadius, this.this$0.topRightRadius, this.this$0.bottomLeftRadius, this.this$0.bottomLeftRadius, this.this$0.bottomRightRadius, this.this$0.bottomRightRadius}, Path.Direction.CCW);
                    }
                    rv.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
    }

    public final CustomItemDecoration setCornerRadius(int radius) {
        this.topLeftRadius = radius;
        this.topRightRadius = radius;
        this.bottomLeftRadius = radius;
        this.bottomRightRadius = radius;
        this.hasSetCorner = true;
        return this;
    }

    public final CustomItemDecoration setCornerRadius(int topLeftRadius, int topRightRadius, int bottomLeftRadius, int bottomRightRadius) {
        this.topLeftRadius = topLeftRadius;
        this.topRightRadius = topRightRadius;
        this.bottomLeftRadius = bottomLeftRadius;
        this.bottomRightRadius = bottomRightRadius;
        this.hasSetCorner = true;
        return this;
    }

    public final CustomItemDecoration color(int color) {
        this.mPaint.setColor(color);
        return this;
    }

    public final CustomItemDecoration marginLeft(float marginLeft) {
        this.marginLeft = marginLeft;
        return this;
    }

    public final CustomItemDecoration marginRight(float marginRight) {
        this.marginRight = marginRight;
        return this;
    }

    public final CustomItemDecoration height(float height) {
        this.mDividerHeight = height;
        return this;
    }

    public final CustomItemDecoration showLast(boolean show) {
        this.showLast = show;
        return this;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        Intrinsics.checkParameterIsNotNull(outRect, "outRect");
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        Intrinsics.checkParameterIsNotNull(state, "state");
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) != 0) {
            outRect.top = (int) this.mDividerHeight;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas c2, RecyclerView parent, RecyclerView.State state) {
        Intrinsics.checkParameterIsNotNull(c2, "c");
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        Intrinsics.checkParameterIsNotNull(state, "state");
        super.onDraw(c2, parent, state);
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            if (parent.getChildAdapterPosition(view) != childCount - 1 || this.showLast) {
                Intrinsics.checkExpressionValueIsNotNull(view, "view");
                c2.drawRect(parent.getPaddingLeft() + this.marginLeft, view.getBottom(), (parent.getWidth() - parent.getPaddingRight()) - this.marginRight, view.getBottom() + this.mDividerHeight, this.mPaint);
            }
        }
        RectF rectF = this.rectF;
        if (rectF != null) {
            c2.clipRect(rectF);
            Path path = this.path;
            if (path != null) {
                if (Build.VERSION.SDK_INT >= 28) {
                    c2.clipPath(path);
                } else {
                    c2.clipPath(path, Region.Op.REPLACE);
                }
            }
        }
    }
}