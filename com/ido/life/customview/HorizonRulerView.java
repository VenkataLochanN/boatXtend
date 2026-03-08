package com.ido.life.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.ido.life.util.ViewUtil;

/* JADX INFO: loaded from: classes2.dex */
public class HorizonRulerView extends View {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    protected int LINE_WIDTH;
    protected int[] data;
    protected int defaultOffset;
    protected int drawCount;
    private Runnable goOnDraw;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    protected int f4690h;
    protected int mContentColor;
    protected int mDataStep;
    protected int mDevideCount;
    protected int mLightColor;
    protected float mLineLength;
    protected float mMaxData;
    protected float mMinData;
    protected int mRulerColor;
    protected int mRulerColor2;
    protected int mRulerTextColor;
    protected int mShadowColor;
    protected int mShowNumber;
    protected int mSpaceSize;
    protected int mStartNumber;
    protected String[] mUnits;
    protected int mVelocity;
    protected int maxLineCount;
    protected Paint paint;
    protected float pre;
    protected float start;
    protected float textSize;
    protected int tickMarkDataStep;
    protected int tickMarkStep;
    protected String title;
    protected VelocityTracker velocityTracker;
    protected int w;

    public HorizonRulerView(Context context) {
        super(context);
        this.LINE_WIDTH = 2;
        this.mShadowColor = -14803426;
        this.mRulerColor = -1;
        this.mRulerColor2 = -2130706433;
        this.mRulerTextColor = -1;
        this.mLightColor = -1762269;
        this.mContentColor = -1;
        this.mDevideCount = 5;
        this.mShowNumber = 6;
        this.mMinData = 30.0f;
        this.mMaxData = 255.0f;
        this.mDataStep = 10;
        this.tickMarkDataStep = 1;
        this.mUnits = new String[]{"cm"};
        this.goOnDraw = new Runnable() { // from class: com.ido.life.customview.HorizonRulerView.1
            private int MAX_REDRAW_COUNT = 10;

            @Override // java.lang.Runnable
            public void run() {
                if (HorizonRulerView.this.drawCount >= this.MAX_REDRAW_COUNT || Math.abs(HorizonRulerView.this.mVelocity) <= 5000) {
                    HorizonRulerView.this.endScorll();
                    return;
                }
                double d2 = ((double) HorizonRulerView.this.mVelocity) * 0.05d;
                int i = this.MAX_REDRAW_COUNT;
                HorizonRulerView.this.scrollBy(-((int) ((d2 / ((double) i)) * (((double) (i - HorizonRulerView.this.drawCount)) + 0.5d))), 0);
                HorizonRulerView.this.drawCount++;
                HorizonRulerView horizonRulerView = HorizonRulerView.this;
                horizonRulerView.postDelayed(horizonRulerView.goOnDraw, 20L);
            }
        };
        init();
    }

    public HorizonRulerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.LINE_WIDTH = 2;
        this.mShadowColor = -14803426;
        this.mRulerColor = -1;
        this.mRulerColor2 = -2130706433;
        this.mRulerTextColor = -1;
        this.mLightColor = -1762269;
        this.mContentColor = -1;
        this.mDevideCount = 5;
        this.mShowNumber = 6;
        this.mMinData = 30.0f;
        this.mMaxData = 255.0f;
        this.mDataStep = 10;
        this.tickMarkDataStep = 1;
        this.mUnits = new String[]{"cm"};
        this.goOnDraw = new Runnable() { // from class: com.ido.life.customview.HorizonRulerView.1
            private int MAX_REDRAW_COUNT = 10;

            @Override // java.lang.Runnable
            public void run() {
                if (HorizonRulerView.this.drawCount >= this.MAX_REDRAW_COUNT || Math.abs(HorizonRulerView.this.mVelocity) <= 5000) {
                    HorizonRulerView.this.endScorll();
                    return;
                }
                double d2 = ((double) HorizonRulerView.this.mVelocity) * 0.05d;
                int i = this.MAX_REDRAW_COUNT;
                HorizonRulerView.this.scrollBy(-((int) ((d2 / ((double) i)) * (((double) (i - HorizonRulerView.this.drawCount)) + 0.5d))), 0);
                HorizonRulerView.this.drawCount++;
                HorizonRulerView horizonRulerView = HorizonRulerView.this;
                horizonRulerView.postDelayed(horizonRulerView.goOnDraw, 20L);
            }
        };
        init();
    }

    public HorizonRulerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.LINE_WIDTH = 2;
        this.mShadowColor = -14803426;
        this.mRulerColor = -1;
        this.mRulerColor2 = -2130706433;
        this.mRulerTextColor = -1;
        this.mLightColor = -1762269;
        this.mContentColor = -1;
        this.mDevideCount = 5;
        this.mShowNumber = 6;
        this.mMinData = 30.0f;
        this.mMaxData = 255.0f;
        this.mDataStep = 10;
        this.tickMarkDataStep = 1;
        this.mUnits = new String[]{"cm"};
        this.goOnDraw = new Runnable() { // from class: com.ido.life.customview.HorizonRulerView.1
            private int MAX_REDRAW_COUNT = 10;

            @Override // java.lang.Runnable
            public void run() {
                if (HorizonRulerView.this.drawCount >= this.MAX_REDRAW_COUNT || Math.abs(HorizonRulerView.this.mVelocity) <= 5000) {
                    HorizonRulerView.this.endScorll();
                    return;
                }
                double d2 = ((double) HorizonRulerView.this.mVelocity) * 0.05d;
                int i2 = this.MAX_REDRAW_COUNT;
                HorizonRulerView.this.scrollBy(-((int) ((d2 / ((double) i2)) * (((double) (i2 - HorizonRulerView.this.drawCount)) + 0.5d))), 0);
                HorizonRulerView.this.drawCount++;
                HorizonRulerView horizonRulerView = HorizonRulerView.this;
                horizonRulerView.postDelayed(horizonRulerView.goOnDraw, 20L);
            }
        };
        init();
    }

    private void init() {
        this.paint = new Paint(1);
        this.paint.setTextAlign(Paint.Align.CENTER);
        this.paint.setStrokeWidth(this.LINE_WIDTH);
        this.title = getResources().getString(R.string.mine_data_height);
    }

    @Override // android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        this.data = getDataByOffset(getRealScroll(getScrollX()));
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        this.w = (i - getPaddingLeft()) - getPaddingRight();
        this.f4690h = (i2 - getPaddingBottom()) - getPaddingTop();
        Log.d("ywx", "onSizeChanged(" + i + AppInfo.DELIM + i2 + AppInfo.DELIM + i3 + AppInfo.DELIM + i4 + ")");
        StringBuilder sb = new StringBuilder();
        sb.append("pl:");
        sb.append(getPaddingLeft());
        sb.append(" pr:");
        sb.append(getPaddingRight());
        sb.append(" pb:");
        sb.append(getPaddingBottom());
        sb.append(" pt:");
        sb.append(getPaddingTop());
        Log.d("ywx", sb.toString());
        int i5 = this.w;
        int i6 = this.mDevideCount;
        int i7 = this.mShowNumber;
        int i8 = this.LINE_WIDTH;
        this.mSpaceSize = (i5 - ((((i6 * 2) * i7) + 1) * i8)) / (((i6 * 2) * i7) - 1);
        this.mLineLength = this.f4690h * 0.2f;
        this.defaultOffset = (i / 2) - (i8 / 2);
        this.tickMarkStep = this.mSpaceSize + i8;
        this.maxLineCount = (int) ((((this.mMaxData - this.mMinData) / this.mDataStep) * i6 * 2.0f) + 1.0f);
        this.textSize = this.mLineLength * 0.15f;
        this.data = getDataByOffset(getRealScroll(getScrollX()));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        drawTickMark(canvas);
        drawShadow(canvas);
        drawLabel(canvas);
    }

    private void drawShadow(Canvas canvas) {
        float fComputeHorizontalScrollOffset = computeHorizontalScrollOffset();
        this.paint.setShader(new LinearGradient(fComputeHorizontalScrollOffset, 0.0f, (getWidth() / 3) + r0, 0.0f, this.mShadowColor, 0, Shader.TileMode.CLAMP));
        canvas.drawRect(fComputeHorizontalScrollOffset, 0.0f, (getWidth() / 3) + r0, getHeight(), this.paint);
        this.paint.setShader(new LinearGradient(((getWidth() / 3) * 2) + r0, 0.0f, getWidth() + r0, 0.0f, 0, this.mShadowColor, Shader.TileMode.CLAMP));
        canvas.drawRect(((getWidth() / 3) * 2) + r0, 0.0f, getWidth() + r0, getHeight(), this.paint);
        this.paint.setShader(null);
    }

    private void drawLabel(Canvas canvas) {
        this.paint.setColor(this.mLightColor);
        int width = ((getWidth() / 2) - (this.LINE_WIDTH / 2)) + getScrollX();
        int i = this.f4690h;
        float f2 = i - (this.mLineLength * 1.8f);
        float f3 = width;
        canvas.drawLine(f3, i, f3, f2, this.paint);
        float f4 = this.mLineLength * 0.1f;
        canvas.drawCircle(f3 + f4, f2, f4, this.paint);
        float f5 = this.mLineLength;
        float f6 = f2 - (0.3f * f5);
        float f7 = this.textSize;
        float f8 = f7 * 2.0f;
        float f9 = f7 * 1.5f;
        String[] strArr = this.mUnits;
        if (strArr.length == 1) {
            this.paint.setTextSize(f9);
            canvas.drawText(this.title + this.data[0] + this.data[1] + this.mUnits[0], f3, f6, this.paint);
            return;
        }
        if (strArr.length == 2) {
            float f10 = f5 * 0.1f;
            this.paint.setTextSize(f8);
            float textRectWidth = ViewUtil.getTextRectWidth(this.paint, this.data[0] + "");
            float textRectWidth2 = ViewUtil.getTextRectWidth(this.paint, this.data[1] + "");
            this.paint.setTextSize(f9);
            float textRectWidth3 = ViewUtil.getTextRectWidth(this.paint, this.mUnits[0]);
            float textRectWidth4 = textRectWidth + f10 + textRectWidth3 + f10 + textRectWidth2 + f10 + ViewUtil.getTextRectWidth(this.paint, this.mUnits[1]);
            this.paint.setTextAlign(Paint.Align.LEFT);
            this.paint.setTextSize(f8);
            float f11 = f3 - (textRectWidth4 / 2.0f);
            canvas.drawText(this.data[0] + "", f11, f6, this.paint);
            float f12 = f11 + textRectWidth + f10;
            float f13 = textRectWidth3 + f12 + f10;
            canvas.drawText(this.data[1] + "", f13, f6, this.paint);
            this.paint.setTextSize(f9);
            canvas.drawText(this.mUnits[0], f12, f6, this.paint);
            canvas.drawText(this.mUnits[1], f13 + textRectWidth2 + f10, f6, this.paint);
            this.paint.setTextAlign(Paint.Align.CENTER);
            this.paint.setTextSize(f8);
            float textHeight = f6 - ViewUtil.getTextHeight(this.paint);
            this.paint.setTextSize(f9);
            canvas.drawText(this.title, f3, textHeight, this.paint);
        }
    }

    private void drawTickMark(Canvas canvas) {
        float f2;
        Log.d("---", "--------------------------");
        int i = (this.mDevideCount * 2 * this.mShowNumber) + 1;
        int scrollX = getScrollX();
        canvas.save();
        canvas.translate(this.defaultOffset - scrollX, 0.0f);
        canvas.clipRect((getPaddingLeft() - this.defaultOffset) + scrollX, 0, ((this.w + getPaddingLeft()) - this.defaultOffset) + scrollX, getMeasuredHeight());
        Log.d("ywx", "defaultOffset:" + this.defaultOffset);
        this.paint.setColor(this.mRulerColor);
        this.paint.setTextSize(this.textSize);
        int i2 = this.defaultOffset;
        int i3 = scrollX < i2 ? 0 : ((scrollX - i2) / this.tickMarkStep) + 1;
        int iMin = Math.min(i + i3, this.maxLineCount);
        Log.d("drawTickMark", "scrollX:" + scrollX + " +lines:" + (scrollX / this.tickMarkStep));
        Log.d("drawTickMark", "i:" + i3 + " lineCount:" + iMin + " maxLineCount:" + this.maxLineCount);
        while (i3 < iMin) {
            int i4 = (this.tickMarkStep * i3) + scrollX;
            int i5 = this.mDevideCount;
            if (i3 % i5 != 0) {
                f2 = 0.4f;
            } else if ((i3 / i5) % 2 == 0) {
                f2 = 1.0f;
                canvas.drawText(getDataByOffset(i4 - scrollX)[0] + "", i4, this.f4690h - (this.mLineLength * 1.1f), this.paint);
            } else {
                f2 = 0.6f;
            }
            float f3 = i4;
            int i6 = this.f4690h;
            canvas.drawLine(f3, i6, f3, i6 - (this.mLineLength * f2), this.paint);
            Log.d("drawTickMark", "line(" + i4 + AppInfo.DELIM + this.f4690h + ")-(" + i4 + AppInfo.DELIM + (this.f4690h - (this.mLineLength * f2)) + ")");
            i3++;
        }
        canvas.restore();
    }

    protected int[] getDataByOffset(int i) {
        int[] iArr = new int[2];
        int i2 = this.mDevideCount;
        int i3 = this.tickMarkStep;
        if (i2 * 2 * i3 != 0) {
            iArr[0] = (int) (((i / ((i2 * 2) * i3)) * this.mDataStep) + this.mMinData);
            iArr[1] = (i % ((i2 * 2) * i3)) / i3;
            iArr[1] = iArr[1] * this.tickMarkDataStep;
            iArr[1] = iArr[1] + this.mStartNumber;
            if (iArr[1] >= 12) {
                iArr[0] = iArr[0] + 1;
                iArr[1] = iArr[1] - 12;
            }
        }
        return iArr;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            removeCallbacks(this.goOnDraw);
            this.drawCount = 1;
            initOrResetVelocityTracker();
            this.velocityTracker.addMovement(motionEvent);
            this.start = motionEvent.getRawX();
            this.pre = this.start;
        } else if (action == 1) {
            this.velocityTracker.addMovement(motionEvent);
            this.velocityTracker.computeCurrentVelocity(1000, ViewConfiguration.get(getContext()).getScaledMaximumFlingVelocity());
            this.mVelocity = (int) this.velocityTracker.getXVelocity();
            postDelayed(this.goOnDraw, 50L);
        } else if (action == 2) {
            this.velocityTracker.addMovement(motionEvent);
            if (Math.abs(this.start - motionEvent.getRawX()) > 100.0f) {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
            scrollBy((int) (this.pre - motionEvent.getRawX()), 0);
            this.pre = motionEvent.getRawX();
        } else if (action == 3) {
            endScorll();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void endScorll() {
        int scrollX = getScrollX();
        int i = this.tickMarkStep;
        int i2 = scrollX % i;
        scrollTo(getRealScroll(scrollX + (((float) i2) > ((float) i) * 0.5f ? i - i2 : -i2)), 0);
        recycleVelocityTracker();
        getParent().requestDisallowInterceptTouchEvent(false);
    }

    protected int getRealScroll(int i) {
        return Math.min((int) (((this.mMaxData - this.mMinData) / this.mDataStep) * this.mDevideCount * 2.0f * this.tickMarkStep), Math.max(0, i));
    }

    protected void recycleVelocityTracker() {
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.velocityTracker = null;
        }
    }

    protected void initOrResetVelocityTracker() {
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        } else {
            velocityTracker.clear();
        }
    }
}