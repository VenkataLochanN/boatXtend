package com.ido.life.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.ido.common.utils.DipPixelUtil;

/* JADX INFO: loaded from: classes2.dex */
public class DialRoundRelativelayout extends RelativeLayout {
    private int RADIUS;
    private PaintFlagsDrawFilter mPaintFlagsDrawFilter;
    private Path mPath;
    private float[] mRadius;
    private RectF mRectF;

    public DialRoundRelativelayout(Context context) {
        this(context, null);
    }

    public DialRoundRelativelayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DialRoundRelativelayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.RADIUS = 14;
        this.mRadius = new float[]{DipPixelUtil.dip2px(this.RADIUS), DipPixelUtil.dip2px(this.RADIUS), DipPixelUtil.dip2px(this.RADIUS), DipPixelUtil.dip2px(this.RADIUS), DipPixelUtil.dip2px(this.RADIUS), DipPixelUtil.dip2px(this.RADIUS), DipPixelUtil.dip2px(this.RADIUS), DipPixelUtil.dip2px(this.RADIUS)};
        init();
    }

    private void init() {
        this.mPath = new Path();
        this.mPaintFlagsDrawFilter = new PaintFlagsDrawFilter(0, 3);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mRectF == null) {
            this.mRectF = new RectF();
            this.mRectF.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
        }
        this.mPath.addRoundRect(this.mRectF, this.mRadius, Path.Direction.CW);
        canvas.setDrawFilter(this.mPaintFlagsDrawFilter);
        canvas.clipPath(this.mPath);
        super.onDraw(canvas);
    }
}