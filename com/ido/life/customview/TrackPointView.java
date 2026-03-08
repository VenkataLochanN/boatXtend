package com.ido.life.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.boat.Xtend.two.R;
import com.ido.common.utils.DipPixelUtil;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class TrackPointView extends View {
    private float[] mCurrentPosition;
    private int mHeight;
    private boolean mIsDetail;
    private Bitmap mLightBallBitmap;
    private Paint mLightBallPaint;
    private Paint mLinePaint;
    private Path mLinePath;
    private Bitmap mStartBitmap;
    private Paint mStartPaint;
    private Point mStartPoint;
    private Rect mStartRect;
    private int mWidth;

    public interface OnTrailChangeListener {
        void onFinish();
    }

    public TrackPointView(Context context) {
        this(context, null);
    }

    public TrackPointView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TrackPointView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCurrentPosition = new float[2];
        initPaint();
    }

    private void initPaint() {
        this.mLinePaint = new Paint();
        this.mLinePaint.setColor(getResources().getColor(R.color.color_9dfe25));
        this.mLinePaint.setStyle(Paint.Style.STROKE);
        this.mLinePaint.setStrokeWidth(DipPixelUtil.dip2px(6.0f));
        this.mLinePaint.setStrokeCap(Paint.Cap.ROUND);
        this.mLinePaint.setAntiAlias(true);
        this.mLightBallPaint = new Paint();
        this.mStartPaint = new Paint();
        this.mStartPaint.setAntiAlias(true);
        this.mStartPaint.setFilterBitmap(true);
        this.mLinePath = new Path();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(this.mLinePath, this.mLinePaint);
        Bitmap bitmap = this.mLightBallBitmap;
        if (bitmap != null && this.mStartRect != null) {
            int width = bitmap.getWidth() / 2;
            float f2 = width;
            RectF rectF = new RectF(0.0f, 0.0f, f2, this.mLightBallBitmap.getHeight() / 2);
            float[] fArr = this.mCurrentPosition;
            rectF.left = fArr[0] - f2;
            rectF.right = fArr[0] + f2;
            rectF.top = fArr[1] - (r2 * 2);
            rectF.bottom = fArr[1];
            canvas.drawBitmap(this.mLightBallBitmap, (Rect) null, rectF, this.mLightBallPaint);
        }
        Bitmap bitmap2 = this.mStartBitmap;
        if (bitmap2 == null || this.mStartPoint == null) {
            return;
        }
        if (this.mStartRect == null) {
            int width2 = bitmap2.getWidth() / 2;
            int height = this.mStartBitmap.getHeight() / 2;
            this.mStartRect = new Rect();
            this.mStartRect.left = this.mStartPoint.x - width2;
            this.mStartRect.right = this.mStartPoint.x + width2;
            this.mStartRect.top = this.mStartPoint.y - (height * 2);
            this.mStartRect.bottom = this.mStartPoint.y;
        }
        canvas.drawBitmap(this.mStartBitmap, (Rect) null, this.mStartRect, this.mStartPaint);
    }

    public void setDetail(boolean z) {
        this.mIsDetail = z;
    }

    public void drawSportLine(final List<Point> list, int i, int i2, final OnTrailChangeListener onTrailChangeListener) {
        if (list.size() <= 1) {
            onTrailChangeListener.onFinish();
            return;
        }
        Path path = new Path();
        for (int i3 = 0; i3 < list.size(); i3++) {
            if (i3 == 0) {
                path.moveTo(list.get(i3).x, list.get(i3).y);
            } else {
                path.lineTo(list.get(i3).x, list.get(i3).y);
            }
        }
        final PathMeasure pathMeasure = new PathMeasure(path, false);
        final float length = pathMeasure.getLength();
        this.mStartPoint = new Point(list.get(0).x, list.get(0).y);
        if (this.mIsDetail) {
            this.mStartBitmap = BitmapFactory.decodeResource(getResources(), i);
            this.mLightBallBitmap = BitmapFactory.decodeResource(getResources(), i2);
        } else {
            this.mStartBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_sport_locus_small_start);
            this.mLightBallBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_sport_locus_small_end);
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, length);
        valueAnimatorOfFloat.setDuration(3000L);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.life.customview.-$$Lambda$TrackPointView$hCeGES-0oU_M418iRrZJ2DFe02c
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f$0.lambda$drawSportLine$0$TrackPointView(pathMeasure, list, length, onTrailChangeListener, valueAnimator);
            }
        });
        valueAnimatorOfFloat.start();
    }

    public /* synthetic */ void lambda$drawSportLine$0$TrackPointView(PathMeasure pathMeasure, List list, float f2, OnTrailChangeListener onTrailChangeListener, ValueAnimator valueAnimator) {
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        pathMeasure.getPosTan(fFloatValue, this.mCurrentPosition, null);
        if (fFloatValue == 0.0f) {
            this.mLinePath.moveTo(((Point) list.get(0)).x, ((Point) list.get(0)).y);
        }
        pathMeasure.getSegment(0.0f, fFloatValue, this.mLinePath, true);
        invalidate();
        if (fFloatValue != f2 || onTrailChangeListener == null) {
            return;
        }
        onTrailChangeListener.onFinish();
    }
}