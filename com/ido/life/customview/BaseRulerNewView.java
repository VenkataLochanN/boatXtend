package com.ido.life.customview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.ScreenUtil;
import com.ido.life.util.ViewUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class BaseRulerNewView extends HorizonRulerView {
    private int defaultRulerData;
    private int defaultRulerScrollX;
    private Runnable goOnDraw;
    private boolean isCalculateFinish;
    public boolean isLb;
    public boolean isWeightSt;
    int lineStartx;
    int lineStopx;
    private int mBitHeight;
    private int mBitWidth;
    private Rect mDestRectLeft;
    private Rect mDestRectRight;
    private int mDip2px;
    private Rect mSrcRect;
    int maxLineLenght;
    private float padding;
    int paddingLeft;

    public BaseRulerNewView(Context context) {
        super(context);
        this.isCalculateFinish = false;
        this.padding = 10.0f;
        this.isLb = false;
        this.isWeightSt = false;
        this.goOnDraw = new Runnable() { // from class: com.ido.life.customview.BaseRulerNewView.1
            private int MAX_REDRAW_COUNT = 10;

            @Override // java.lang.Runnable
            public void run() {
                if (BaseRulerNewView.this.drawCount >= this.MAX_REDRAW_COUNT || Math.abs(BaseRulerNewView.this.mVelocity) <= 5000) {
                    BaseRulerNewView.this.endScorll();
                    return;
                }
                double d2 = ((double) BaseRulerNewView.this.mVelocity) * 0.05d;
                int i = this.MAX_REDRAW_COUNT;
                BaseRulerNewView.this.scrollBy(0, -((int) ((d2 / ((double) i)) * (((double) (i - BaseRulerNewView.this.drawCount)) + 0.5d))));
                BaseRulerNewView.this.drawCount++;
                BaseRulerNewView baseRulerNewView = BaseRulerNewView.this;
                baseRulerNewView.postDelayed(baseRulerNewView.goOnDraw, 200 / this.MAX_REDRAW_COUNT);
            }
        };
        init(context);
    }

    public BaseRulerNewView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isCalculateFinish = false;
        this.padding = 10.0f;
        this.isLb = false;
        this.isWeightSt = false;
        this.goOnDraw = new Runnable() { // from class: com.ido.life.customview.BaseRulerNewView.1
            private int MAX_REDRAW_COUNT = 10;

            @Override // java.lang.Runnable
            public void run() {
                if (BaseRulerNewView.this.drawCount >= this.MAX_REDRAW_COUNT || Math.abs(BaseRulerNewView.this.mVelocity) <= 5000) {
                    BaseRulerNewView.this.endScorll();
                    return;
                }
                double d2 = ((double) BaseRulerNewView.this.mVelocity) * 0.05d;
                int i = this.MAX_REDRAW_COUNT;
                BaseRulerNewView.this.scrollBy(0, -((int) ((d2 / ((double) i)) * (((double) (i - BaseRulerNewView.this.drawCount)) + 0.5d))));
                BaseRulerNewView.this.drawCount++;
                BaseRulerNewView baseRulerNewView = BaseRulerNewView.this;
                baseRulerNewView.postDelayed(baseRulerNewView.goOnDraw, 200 / this.MAX_REDRAW_COUNT);
            }
        };
        init(context);
    }

    public BaseRulerNewView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isCalculateFinish = false;
        this.padding = 10.0f;
        this.isLb = false;
        this.isWeightSt = false;
        this.goOnDraw = new Runnable() { // from class: com.ido.life.customview.BaseRulerNewView.1
            private int MAX_REDRAW_COUNT = 10;

            @Override // java.lang.Runnable
            public void run() {
                if (BaseRulerNewView.this.drawCount >= this.MAX_REDRAW_COUNT || Math.abs(BaseRulerNewView.this.mVelocity) <= 5000) {
                    BaseRulerNewView.this.endScorll();
                    return;
                }
                double d2 = ((double) BaseRulerNewView.this.mVelocity) * 0.05d;
                int i2 = this.MAX_REDRAW_COUNT;
                BaseRulerNewView.this.scrollBy(0, -((int) ((d2 / ((double) i2)) * (((double) (i2 - BaseRulerNewView.this.drawCount)) + 0.5d))));
                BaseRulerNewView.this.drawCount++;
                BaseRulerNewView baseRulerNewView = BaseRulerNewView.this;
                baseRulerNewView.postDelayed(baseRulerNewView.goOnDraw, 200 / this.MAX_REDRAW_COUNT);
            }
        };
        init(context);
    }

    private void init(Context context) {
        this.paint = new Paint(1);
        this.paint.setTextAlign(Paint.Align.CENTER);
        this.paint.setStrokeWidth(this.LINE_WIDTH);
        ScreenUtil.initScreen((Activity) context);
        this.mDip2px = DipPixelUtil.dip2px(15.0f);
        this.mSrcRect = new Rect(0, 0, this.mBitWidth, this.mBitHeight);
    }

    @Override // com.ido.life.customview.HorizonRulerView, android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        this.data = getDataByOffset(-getRealScroll(getScrollY()));
        Log.d("View", "data = " + Arrays.toString(this.data));
    }

    @Override // com.ido.life.customview.HorizonRulerView, android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        this.w = (i - getPaddingLeft()) - getPaddingRight();
        this.f4690h = (i2 - getPaddingBottom()) - getPaddingTop();
        calulateData();
        this.isCalculateFinish = true;
        setData(this.defaultRulerData);
    }

    public void calulateData() {
        this.mSpaceSize = (this.f4690h - ((((this.mDevideCount * 2) * this.mShowNumber) + 1) * this.LINE_WIDTH)) / (((this.mDevideCount * 2) * this.mShowNumber) - 1);
        this.mLineLength = this.w * 0.2f;
        this.defaultOffset = (this.f4690h / 2) + 30;
        this.tickMarkStep = this.mSpaceSize + this.LINE_WIDTH;
        this.maxLineCount = (int) (((((this.mMaxData - this.mMinData) * 1.0f) / this.mDataStep) * this.mDevideCount * 2.0f) + 1.0f);
        CommonLogUtil.d("maxLineCount===>" + this.maxLineCount);
        this.textSize = this.mLineLength * 0.2f;
        this.paint.setTextSize(this.textSize);
        this.data = getDataByOffset(-getRealScroll(getScrollY()));
        Log.d("View", "calulateData  data = " + Arrays.toString(this.data));
    }

    public void initData(String[] strArr, float f2, float f3, int i, int i2, int i3) {
        this.mStartNumber = ((int) (10.0f * f3)) % 10;
        this.mUnits = strArr;
        this.mMaxData = f2;
        this.mMinData = f3;
        this.mDevideCount = i;
        this.mDataStep = i2;
        this.tickMarkDataStep = i3;
        calulateData();
        invalidate();
    }

    public void setData(int i) {
        this.defaultRulerData = i;
        if (this.isCalculateFinish) {
            this.defaultRulerScrollX = i * this.tickMarkStep;
            scrollTo(0, -this.defaultRulerScrollX);
        }
        postDelayed(this.goOnDraw, 50L);
    }

    public void setData2(int i) {
        this.defaultRulerData = i;
        if (this.isCalculateFinish) {
            this.defaultRulerScrollX = i * this.tickMarkStep;
            scrollTo(0, -this.defaultRulerScrollX);
            recycleVelocityTracker();
            getParent().requestDisallowInterceptTouchEvent(false);
        }
    }

    @Override // com.ido.life.customview.HorizonRulerView, android.view.View
    protected void onDraw(Canvas canvas) {
        drawTickMark(canvas);
        drawShadow(canvas);
        drawLabel(canvas);
    }

    public int getUnitSize() {
        return this.mUnits.length;
    }

    private void drawLabel(Canvas canvas) {
        this.paint.setColor(this.mLightColor);
        this.paint.setTextAlign(Paint.Align.CENTER);
        this.paint.setStrokeWidth(5.0f);
        int scrollY = this.defaultOffset + getScrollY();
        int i = this.w;
        float f2 = this.mLineLength;
        float f3 = this.mLineLength * 0.1f;
        float f4 = scrollY;
        canvas.drawLine(this.lineStartx, f4, this.paddingLeft + this.maxLineLenght + f3, f4, this.paint);
        canvas.drawCircle(this.lineStopx + (2.0f * f3), f4, f3, this.paint);
        float f5 = this.mLineLength;
        float f6 = this.textSize * 1.8f;
        float f7 = this.textSize;
        float f8 = this.textSize * 1.3f;
        float f9 = this.mLineLength;
        float f10 = this.lineStopx + (f3 * 6.0f);
        if (this.mUnits.length == 1) {
            this.paint.setTextSize(f6);
            this.paint.setTextAlign(Paint.Align.LEFT);
            this.paint.setStrokeWidth(this.LINE_WIDTH);
            this.paint.setColor(this.mContentColor);
            this.paint.setTextSize(f6);
            canvas.drawText((this.data[0] + this.data[1]) + "", f10, DipPixelUtil.dip2px(5.0f) + scrollY, this.paint);
            Log.i("自定义控件的高度", (scrollY + 100) + "y+100     y:" + scrollY);
            ViewUtil.getTextRectWidth(this.paint, (this.data[0] + this.data[1]) + "");
            this.paint.setTextSize(this.textSize);
            this.paint.setTextAlign(Paint.Align.LEFT);
            canvas.drawText(this.mUnits[0], f10 + 50.0f + ((float) DipPixelUtil.dip2px(40.0f)), (float) (scrollY + DipPixelUtil.dip2px(5.0f)), this.paint);
            return;
        }
        if (this.mUnits.length == 2) {
            this.paint.setTextSize(f6);
            this.paint.setTextSize(f8);
            this.paint.setTextAlign(Paint.Align.LEFT);
            this.paint.setTextSize(f6);
            this.paint.setColor(this.mContentColor);
            this.paint.setStrokeWidth(this.LINE_WIDTH);
            canvas.drawText(this.data[0] + "'" + this.data[1] + "\"", f10, DipPixelUtil.dip2px(5.0f) + scrollY, this.paint);
            Paint paint = this.paint;
            StringBuilder sb = new StringBuilder();
            sb.append(this.data[0] + this.data[1]);
            sb.append("");
            ViewUtil.getTextRectWidth(paint, sb.toString());
            Log.i("111111111111111111", "cm的时候 x的值   " + f10);
            this.paint.setTextSize(this.textSize);
            this.paint.setTextAlign(Paint.Align.LEFT);
            canvas.drawText(this.mUnits[0] + this.mUnits[1], f10 + ((float) DipPixelUtil.dip2px(40.0f)) + DipPixelUtil.dip2px(20.0f), scrollY + DipPixelUtil.dip2px(5.0f), this.paint);
        }
    }

    private void drawShadow(Canvas canvas) {
        float scrollY = getScrollY();
        this.paint.setShader(new LinearGradient(0.0f, scrollY, 0.0f, (getHeight() / 3) + r0, this.mShadowColor, 14737632, Shader.TileMode.CLAMP));
        canvas.drawRect(0.0f, scrollY, getWidth(), (getHeight() / 3) + r0, this.paint);
        this.paint.setShader(new LinearGradient(0.0f, ((getHeight() / 3) * 2) + r0, 0.0f, getHeight() + r0, 14737632, this.mShadowColor, Shader.TileMode.CLAMP));
        canvas.drawRect(0.0f, ((getHeight() / 3) * 2) + r0, getWidth(), getHeight() + r0, this.paint);
        this.paint.setShader(null);
    }

    private void drawTickMark(Canvas canvas) {
        float f2;
        int i = (this.mDevideCount * 2 * this.mShowNumber) + 1;
        int scrollY = getScrollY();
        canvas.save();
        this.paddingLeft = getPaddingLeft() + ScreenUtil.dip2px(50.0f) + this.mDip2px;
        float f3 = 0.0f;
        canvas.translate(0.0f, this.defaultOffset - scrollY);
        this.paint.setColor(this.mRulerColor);
        this.paint.setTextSize(this.textSize);
        this.paint.setTextAlign(Paint.Align.RIGHT);
        float f4 = 2.0f;
        float f5 = (-(this.paint.ascent() + this.paint.descent())) / 2.0f;
        int i2 = -scrollY;
        int i3 = i2 < this.defaultOffset ? 0 : ((i2 - this.defaultOffset) / this.tickMarkStep) + 2;
        int i4 = i + i3;
        int i5 = this.mUnits.length == 1 ? 0 : 2;
        this.maxLineLenght = ((int) (((double) this.mLineLength) * 0.6d)) + 100;
        CommonLogUtil.d("lineCount1===>" + i4);
        int iMin = Math.min(i4, this.maxLineCount) + i5;
        CommonLogUtil.d("lineCount2===>" + iMin);
        int i6 = this.paddingLeft;
        this.lineStopx = this.maxLineLenght + i6;
        this.lineStartx = (int) (((float) i6) + (this.mLineLength * 0.1f));
        int i7 = i3;
        while (i7 < iMin) {
            int i8 = this.mStartNumber + i7;
            int i9 = ((-i7) * this.tickMarkStep) + scrollY;
            if (i8 % this.mDevideCount != 0) {
                f2 = 0.4f;
            } else if (((i8 * 1.0f) / this.mDevideCount) % f4 == f3) {
                int i10 = (-i9) + scrollY;
                int[] dataByOffset = getDataByOffset(i10);
                this.paint.setColor(this.mRulerTextColor);
                if (this.isWeightSt) {
                    if (dataByOffset[0] + dataByOffset[1] <= 32) {
                        float f6 = i9 - f5;
                        canvas.rotate(-90.0f, this.paddingLeft, f6);
                        canvas.drawText(getDataByOffset(i10)[0] + "", this.paddingLeft, f6, this.paint);
                        canvas.rotate(90.0f, (float) this.paddingLeft, f6);
                    }
                } else if (dataByOffset[0] + dataByOffset[1] <= 551) {
                    float f7 = i9 - f5;
                    canvas.rotate(-90.0f, this.paddingLeft, f7);
                    canvas.drawText(getDataByOffset(i10)[0] + "", this.paddingLeft, f7, this.paint);
                    canvas.rotate(90.0f, (float) this.paddingLeft, f7);
                }
                this.paint.setColor(this.mRulerColor);
                f2 = 0.9f;
            } else {
                f2 = 0.6f;
                this.paint.setColor(this.mRulerColor2);
            }
            int[] dataByOffset2 = getDataByOffset((-i9) + scrollY);
            if (i8 % this.mDevideCount == 0) {
                this.paint.setColor(this.mRulerColor);
            } else {
                this.paint.setColor(this.mRulerColor2);
            }
            if (this.isWeightSt) {
                if (dataByOffset2[0] + dataByOffset2[1] <= 32) {
                    int i11 = this.lineStartx;
                    float f8 = i9;
                    canvas.drawLine(i11, f8, i11 + (this.mLineLength * f2), f8, this.paint);
                }
            } else if (dataByOffset2[0] + dataByOffset2[1] <= 551) {
                int i12 = this.lineStartx;
                float f9 = i9;
                canvas.drawLine(i12, f9, i12 + (this.mLineLength * f2), f9, this.paint);
            }
            i7++;
            f3 = 0.0f;
            f4 = 2.0f;
        }
        canvas.restore();
    }

    @Override // com.ido.life.customview.HorizonRulerView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            removeCallbacks(this.goOnDraw);
            this.drawCount = 1;
            initOrResetVelocityTracker();
            this.velocityTracker.addMovement(motionEvent);
            this.start = motionEvent.getRawY();
            this.pre = this.start;
        } else if (action != 1) {
            if (action != 2) {
                if (action == 3) {
                    endScorll();
                }
            } else {
                if (this.velocityTracker == null) {
                    return false;
                }
                this.velocityTracker.addMovement(motionEvent);
                if (Math.abs(this.start - motionEvent.getRawY()) > 100.0f) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                scrollBy(0, (int) (this.pre - motionEvent.getRawY()));
                this.pre = motionEvent.getRawY();
            }
        } else {
            if (this.velocityTracker == null) {
                return false;
            }
            this.velocityTracker.addMovement(motionEvent);
            this.velocityTracker.computeCurrentVelocity(1000, ViewConfiguration.get(getContext()).getScaledMaximumFlingVelocity());
            this.mVelocity = (int) this.velocityTracker.getYVelocity();
            postDelayed(this.goOnDraw, 50L);
        }
        return true;
    }

    public int[] getData() {
        return this.data;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void endScorll() {
        int scrollY = getScrollY();
        if (this.tickMarkStep > 0) {
            int iAbs = Math.abs(scrollY % this.tickMarkStep);
            scrollY = getRealScroll(scrollY - (((float) iAbs) > ((float) this.tickMarkStep) * 0.5f ? this.tickMarkStep - iAbs : -iAbs));
        }
        scrollTo(0, scrollY);
        recycleVelocityTracker();
        getParent().requestDisallowInterceptTouchEvent(false);
    }

    @Override // com.ido.life.customview.HorizonRulerView
    protected int getRealScroll(int i) {
        return Math.max(-((int) ((((((this.mMaxData - this.mMinData) * 1.0f) / this.mDataStep) * this.mDevideCount * 2.0f) + (this.mUnits.length == 1 ? 0 : 2)) * this.tickMarkStep)), Math.min(0, i));
    }
}