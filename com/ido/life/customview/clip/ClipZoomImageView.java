package com.ido.life.customview.clip;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;

/* JADX INFO: loaded from: classes2.dex */
public class ClipZoomImageView extends AppCompatImageView implements ScaleGestureDetector.OnScaleGestureListener, View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener {
    private float initScale;
    private boolean isAutoScale;
    private boolean isCanDrag;
    private int lastPointerCount;
    private GestureDetector mGestureDetector;
    private int mHeightRation;
    private int mHorizontalPadding;
    private float mLastX;
    private float mLastY;
    private ScaleGestureDetector mScaleGestureDetector;
    private final Matrix mScaleMatrix;
    private int mTouchSlop;
    private int mVerticalPadding;
    private int mWidthRation;
    private final float[] matrixValues;
    private boolean once;
    private static final String TAG = ClipZoomImageView.class.getSimpleName();
    public static float SCALE_MAX = 4.0f;
    private static float SCALE_MID = 2.0f;

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        return true;
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
    }

    public ClipZoomImageView(Context context) {
        this(context, null);
    }

    public ClipZoomImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.initScale = 1.0f;
        this.once = true;
        this.matrixValues = new float[9];
        this.mScaleGestureDetector = null;
        this.mScaleMatrix = new Matrix();
        this.mWidthRation = 1;
        this.mHeightRation = 1;
        setScaleType(ImageView.ScaleType.MATRIX);
        this.mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() { // from class: com.ido.life.customview.clip.ClipZoomImageView.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                if (ClipZoomImageView.this.isAutoScale) {
                    return true;
                }
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                if (ClipZoomImageView.this.getScale() < ClipZoomImageView.SCALE_MID) {
                    ClipZoomImageView clipZoomImageView = ClipZoomImageView.this;
                    clipZoomImageView.postDelayed(clipZoomImageView.new AutoScaleRunnable(ClipZoomImageView.SCALE_MID, x, y), 16L);
                    ClipZoomImageView.this.isAutoScale = true;
                } else {
                    ClipZoomImageView clipZoomImageView2 = ClipZoomImageView.this;
                    clipZoomImageView2.postDelayed(clipZoomImageView2.new AutoScaleRunnable(clipZoomImageView2.initScale, x, y), 16L);
                    ClipZoomImageView.this.isAutoScale = true;
                }
                return true;
            }
        });
        this.mScaleGestureDetector = new ScaleGestureDetector(context, this);
        setOnTouchListener(this);
    }

    private class AutoScaleRunnable implements Runnable {
        static final float BIGGER = 1.07f;
        static final float SMALLER = 0.93f;
        private float mTargetScale;
        private float tmpScale;
        private float x;
        private float y;

        public AutoScaleRunnable(float f2, float f3, float f4) {
            this.mTargetScale = f2;
            this.x = f3;
            this.y = f4;
            if (ClipZoomImageView.this.getScale() < this.mTargetScale) {
                this.tmpScale = BIGGER;
            } else {
                this.tmpScale = SMALLER;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            Matrix matrix = ClipZoomImageView.this.mScaleMatrix;
            float f2 = this.tmpScale;
            matrix.postScale(f2, f2, this.x, this.y);
            ClipZoomImageView.this.checkBorder();
            ClipZoomImageView clipZoomImageView = ClipZoomImageView.this;
            clipZoomImageView.setImageMatrix(clipZoomImageView.mScaleMatrix);
            float scale = ClipZoomImageView.this.getScale();
            if ((this.tmpScale > 1.0f && scale < this.mTargetScale) || (this.tmpScale < 1.0f && this.mTargetScale < scale)) {
                ClipZoomImageView.this.postDelayed(this, 16L);
                return;
            }
            float f3 = this.mTargetScale / scale;
            ClipZoomImageView.this.mScaleMatrix.postScale(f3, f3, this.x, this.y);
            ClipZoomImageView.this.checkBorder();
            ClipZoomImageView clipZoomImageView2 = ClipZoomImageView.this;
            clipZoomImageView2.setImageMatrix(clipZoomImageView2.mScaleMatrix);
            ClipZoomImageView.this.isAutoScale = false;
        }
    }

    @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        float scale = getScale();
        float scaleFactor = scaleGestureDetector.getScaleFactor();
        if (getDrawable() == null) {
            return true;
        }
        if ((scale < SCALE_MAX && scaleFactor > 1.0f) || (scale > this.initScale && scaleFactor < 1.0f)) {
            float f2 = scaleFactor * scale;
            float f3 = this.initScale;
            if (f2 < f3) {
                scaleFactor = f3 / scale;
            }
            float f4 = scaleFactor * scale;
            float f5 = SCALE_MAX;
            if (f4 > f5) {
                scaleFactor = f5 / scale;
            }
            this.mScaleMatrix.postScale(scaleFactor, scaleFactor, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
            checkBorder();
            setImageMatrix(this.mScaleMatrix);
        }
        return true;
    }

    private RectF getMatrixRectF() {
        Matrix matrix = this.mScaleMatrix;
        RectF rectF = new RectF();
        if (getDrawable() != null) {
            rectF.set(0.0f, 0.0f, r2.getIntrinsicWidth(), r2.getIntrinsicHeight());
            matrix.mapRect(rectF);
        }
        return rectF;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0099  */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouch(android.view.View r10, android.view.MotionEvent r11) {
        /*
            r9 = this;
            android.view.GestureDetector r10 = r9.mGestureDetector
            boolean r10 = r10.onTouchEvent(r11)
            r0 = 1
            if (r10 == 0) goto La
            return r0
        La:
            android.view.ScaleGestureDetector r10 = r9.mScaleGestureDetector
            r10.onTouchEvent(r11)
            int r10 = r11.getPointerCount()
            r1 = 0
            r2 = 0
            r3 = r1
            r4 = r2
            r5 = r4
        L18:
            if (r3 >= r10) goto L27
            float r6 = r11.getX(r3)
            float r4 = r4 + r6
            float r6 = r11.getY(r3)
            float r5 = r5 + r6
            int r3 = r3 + 1
            goto L18
        L27:
            float r3 = (float) r10
            float r4 = r4 / r3
            float r5 = r5 / r3
            int r3 = r9.lastPointerCount
            if (r10 == r3) goto L34
            r9.isCanDrag = r1
            r9.mLastX = r4
            r9.mLastY = r5
        L34:
            r9.lastPointerCount = r10
            int r10 = r11.getAction()
            if (r10 == r0) goto L99
            r11 = 2
            if (r10 == r11) goto L43
            r11 = 3
            if (r10 == r11) goto L99
            goto L9b
        L43:
            float r10 = r9.mLastX
            float r10 = r4 - r10
            float r1 = r9.mLastY
            float r1 = r5 - r1
            boolean r3 = r9.isCanDrag
            if (r3 != 0) goto L55
            boolean r3 = r9.isCanDrag(r10, r1)
            r9.isCanDrag = r3
        L55:
            boolean r3 = r9.isCanDrag
            if (r3 == 0) goto L94
            android.graphics.drawable.Drawable r3 = r9.getDrawable()
            if (r3 == 0) goto L94
            android.graphics.RectF r3 = r9.getMatrixRectF()
            float r6 = r3.width()
            int r7 = r9.getWidth()
            int r8 = r9.mHorizontalPadding
            int r8 = r8 * r11
            int r7 = r7 - r8
            float r7 = (float) r7
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 > 0) goto L75
            r10 = r2
        L75:
            float r3 = r3.height()
            int r6 = r9.getHeight()
            int r7 = r9.mVerticalPadding
            int r7 = r7 * r11
            int r6 = r6 - r7
            float r11 = (float) r6
            int r11 = (r3 > r11 ? 1 : (r3 == r11 ? 0 : -1))
            if (r11 > 0) goto L87
            r1 = r2
        L87:
            android.graphics.Matrix r11 = r9.mScaleMatrix
            r11.postTranslate(r10, r1)
            r9.checkBorder()
            android.graphics.Matrix r10 = r9.mScaleMatrix
            r9.setImageMatrix(r10)
        L94:
            r9.mLastX = r4
            r9.mLastY = r5
            goto L9b
        L99:
            r9.lastPointerCount = r1
        L9b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.customview.clip.ClipZoomImageView.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public final float getScale() {
        this.mScaleMatrix.getValues(this.matrixValues);
        return this.matrixValues[0];
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        Drawable drawable;
        if (!this.once || (drawable = getDrawable()) == null) {
            return;
        }
        int width = getWidth();
        this.mVerticalPadding = (int) ((getHeight() - ((((width - (this.mHorizontalPadding * 2)) * 1.0f) / this.mWidthRation) * this.mHeightRation)) / 2.0f);
        if (this.mVerticalPadding < 0) {
            this.mVerticalPadding = 0;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        float width2 = (intrinsicWidth >= getWidth() - (this.mHorizontalPadding * 2) || intrinsicHeight <= getHeight() - (this.mVerticalPadding * 2)) ? 1.0f : ((getWidth() * 1.0f) - (this.mHorizontalPadding * 2)) / intrinsicWidth;
        if (intrinsicHeight < getHeight() - (this.mVerticalPadding * 2) && intrinsicWidth > getWidth() - (this.mHorizontalPadding * 2)) {
            width2 = ((getHeight() * 1.0f) - (this.mVerticalPadding * 2)) / intrinsicHeight;
        }
        if (intrinsicWidth < getWidth() - (this.mHorizontalPadding * 2) && intrinsicHeight < getHeight() - (this.mVerticalPadding * 2)) {
            width2 = Math.max(((getWidth() * 1.0f) - (this.mHorizontalPadding * 2)) / intrinsicWidth, ((getHeight() * 1.0f) - (this.mVerticalPadding * 2)) / intrinsicHeight);
        }
        this.initScale = width2;
        float f2 = this.initScale;
        SCALE_MID = 2.0f * f2;
        SCALE_MAX = f2 * 4.0f;
        this.mScaleMatrix.postTranslate((width - intrinsicWidth) / 2, (r2 - intrinsicHeight) / 2);
        this.mScaleMatrix.postScale(width2, width2, getWidth() / 2, getHeight() / 2);
        setImageMatrix(this.mScaleMatrix);
        this.once = false;
    }

    public Bitmap clip() {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.RGB_565);
        draw(new Canvas(bitmapCreateBitmap));
        return Bitmap.createBitmap(bitmapCreateBitmap, this.mHorizontalPadding, this.mVerticalPadding, getWidth() - (this.mHorizontalPadding * 2), getHeight() - (this.mVerticalPadding * 2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkBorder() {
        float f2;
        RectF matrixRectF = getMatrixRectF();
        int width = getWidth();
        int height = getHeight();
        Log.e(TAG, "rect.width() =  " + matrixRectF.width() + " , width - 2 * mHorizontalPadding =" + (width - (this.mHorizontalPadding * 2)));
        if (((double) matrixRectF.width()) + 0.01d >= width - (this.mHorizontalPadding * 2)) {
            float f3 = matrixRectF.left > ((float) this.mHorizontalPadding) ? (-matrixRectF.left) + this.mHorizontalPadding : 0.0f;
            f2 = matrixRectF.right < ((float) (width - this.mHorizontalPadding)) ? (width - r8) - matrixRectF.right : f3;
        } else {
            f2 = 0.0f;
        }
        if (((double) matrixRectF.height()) + 0.01d >= height - (this.mVerticalPadding * 2)) {
            f = matrixRectF.top > ((float) this.mVerticalPadding) ? this.mVerticalPadding + (-matrixRectF.top) : 0.0f;
            float f4 = matrixRectF.bottom;
            int i = this.mVerticalPadding;
            if (f4 < height - i) {
                f = (height - i) - matrixRectF.bottom;
            }
        }
        this.mScaleMatrix.postTranslate(f2, f);
    }

    private boolean isCanDrag(float f2, float f3) {
        return Math.sqrt((double) ((f2 * f2) + (f3 * f3))) >= ((double) this.mTouchSlop);
    }

    public void setHorizontalPadding(int i) {
        if (i < 0) {
            i = 0;
        }
        this.mHorizontalPadding = i;
    }

    public void setHorizontalPadding(int i, int i2, int i3) {
        if (i < 0) {
            i = 0;
        }
        this.mHorizontalPadding = i;
        this.mWidthRation = i2;
        this.mHeightRation = i3;
    }
}