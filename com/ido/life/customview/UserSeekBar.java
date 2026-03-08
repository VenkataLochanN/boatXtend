package com.ido.life.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.DipPixelUtil;
import com.ido.life.R;

/* JADX INFO: loaded from: classes2.dex */
public class UserSeekBar extends AppCompatSeekBar {
    private static final String TAG = "UserSeekBar";
    private int bigValue;
    private int bitmapHeight;
    private Bitmap bitmapLow;
    private int bitmapWidth;
    private int circleHeight;
    private Paint circleInPaint;
    private Paint circleOutPaint;
    private int circleWidth;
    private int inColor;
    private boolean isLowerMoving;
    private boolean isUpperMoving;
    private int lineEnd;
    private int lineHeight;
    private int lineLength;
    private Paint linePaint;
    private int lineStart;
    private int lineY;
    private onRangeListener onRangeListener;
    private int outColor;
    private int paddingBottom;
    private int paddingLeft;
    private int paddingRight;
    private int paddingTop;
    private int slideLowX;
    private float smallRange;
    private int smallValue;
    private int textHeight;

    public interface onRangeListener {
        void onRange(float f2);
    }

    private void initPaint() {
    }

    public UserSeekBar(Context context) {
        this(context, null);
    }

    public UserSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public UserSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.lineLength = 400;
        this.inColor = -16776961;
        this.outColor = -16776961;
        this.paddingLeft = DipPixelUtil.dip2px(5.0f);
        this.paddingRight = DipPixelUtil.dip2px(5.0f);
        this.paddingTop = 10;
        this.paddingBottom = 10;
        int i2 = this.paddingLeft;
        this.lineStart = i2;
        this.lineEnd = this.lineLength + i2;
        this.bigValue = 100;
        this.smallValue = 0;
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.UserSeekBar, i, 0);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i3 = 0; i3 < indexCount; i3++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i3);
            if (index == 0) {
                this.bigValue = typedArrayObtainStyledAttributes.getInteger(index, 100);
            } else if (index == 1) {
                this.circleHeight = (int) typedArrayObtainStyledAttributes.getDimension(index, dip2px(getContext(), 20.0f));
            } else if (index == 2) {
                this.circleWidth = (int) typedArrayObtainStyledAttributes.getDimension(index, dip2px(getContext(), 20.0f));
            } else if (index == 4) {
                this.bitmapLow = BitmapFactory.decodeResource(getResources(), typedArrayObtainStyledAttributes.getResourceId(index, 0));
            } else if (index == 5) {
                this.inColor = typedArrayObtainStyledAttributes.getColor(index, ViewCompat.MEASURED_STATE_MASK);
            } else if (index == 6) {
                this.lineHeight = (int) typedArrayObtainStyledAttributes.getDimension(index, dip2px(getContext(), 10.0f));
            } else if (index == 7) {
                this.outColor = typedArrayObtainStyledAttributes.getColor(index, InputDeviceCompat.SOURCE_ANY);
            } else if (index == 9) {
                this.smallValue = typedArrayObtainStyledAttributes.getInteger(index, 100);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        initPaint();
        init();
    }

    private void init() {
        if (this.bitmapLow == null) {
            this.bitmapLow = BitmapFactory.decodeResource(getResources(), com.boat.Xtend.two.R.mipmap.ic_splash_logo);
        }
        this.bitmapHeight = this.bitmapLow.getHeight();
        this.bitmapWidth = this.bitmapLow.getWidth();
        int i = this.circleWidth;
        float f2 = this.circleHeight / this.bitmapHeight;
        Matrix matrix = new Matrix();
        matrix.postScale(i / this.bitmapWidth, f2);
        this.bitmapLow = Bitmap.createBitmap(this.bitmapLow, 0, 0, this.bitmapWidth, this.bitmapHeight, matrix, true);
        this.bitmapHeight = this.bitmapLow.getHeight();
        this.bitmapWidth = this.bitmapLow.getWidth();
        this.slideLowX = this.lineStart;
        this.smallRange = this.smallValue;
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(getMyMeasureWidth(i), getMyMeasureHeight(i2));
    }

    private int getMyMeasureHeight(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return Math.max(size, this.paddingBottom + this.paddingTop + this.bitmapHeight + 10);
        }
        return Math.min(size, this.paddingBottom + this.paddingTop + this.bitmapHeight + 10);
    }

    private int getMyMeasureWidth(int i) {
        int iMin;
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            iMin = Math.max(size, this.paddingLeft + this.paddingRight + (this.bitmapWidth * 2));
        } else {
            iMin = Math.min(size, this.paddingLeft + this.paddingRight + (this.bitmapWidth * 2));
        }
        int i2 = this.paddingLeft;
        int i3 = (iMin - i2) - this.paddingRight;
        int i4 = this.bitmapWidth;
        this.lineLength = i3 - i4;
        this.lineEnd = this.lineLength + i2 + (i4 / 2);
        this.lineStart = i2 + (i4 / 2);
        this.slideLowX = this.lineStart;
        return iMin;
    }

    @Override // androidx.appcompat.widget.AppCompatSeekBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.lineY = (getHeight() - this.paddingBottom) - (this.bitmapHeight / 2);
        this.textHeight = (this.lineY - (r1 / 2)) - 10;
        if (this.linePaint == null) {
            this.linePaint = new Paint();
        }
        this.linePaint.setAntiAlias(true);
        this.linePaint.setStrokeWidth(this.lineHeight);
        this.linePaint.setColor(this.inColor);
        this.linePaint.setStrokeCap(Paint.Cap.ROUND);
        float f2 = this.slideLowX;
        int i = this.lineY;
        canvas.drawLine(f2, i, this.lineEnd, i, this.linePaint);
        this.linePaint.setColor(this.outColor);
        this.linePaint.setStrokeCap(Paint.Cap.ROUND);
        float f3 = this.lineStart;
        int i2 = this.lineY;
        canvas.drawLine(f3, i2, this.slideLowX, i2, this.linePaint);
        CommonLogUtil.d(TAG, "onDraw: " + this.lineStart + AppInfo.DELIM + this.slideLowX);
        drawCircle(canvas);
    }

    private void drawCircle(Canvas canvas) {
        if (this.circleOutPaint == null) {
            this.circleOutPaint = new Paint();
            this.circleOutPaint.setColor(this.outColor);
        }
        CommonLogUtil.d(TAG, "onDraw: " + this.lineY + AppInfo.DELIM + (this.lineY - (this.bitmapHeight / 2)));
        canvas.drawCircle((float) (this.slideLowX + (-16)), (float) this.lineY, 35.0f, this.circleOutPaint);
        if (this.circleInPaint == null) {
            this.circleInPaint = new Paint();
            this.circleInPaint.setColor(this.inColor);
        }
        canvas.drawCircle(this.slideLowX - 16, this.lineY, 18.0f, this.circleInPaint);
    }

    @Override // android.widget.AbsSeekBar, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        int action = motionEvent.getAction();
        if (action == 0) {
            boolean z = Math.abs(y - ((float) this.lineY)) < ((float) (this.bitmapHeight / 2));
            boolean z2 = Math.abs(x - ((float) this.slideLowX)) < ((float) (this.bitmapWidth / 2));
            if (z && z2) {
                this.isLowerMoving = true;
            } else if (x >= this.lineStart && x <= this.slideLowX - (this.bitmapWidth / 2) && z) {
                this.slideLowX = (int) x;
                updateRange();
                postInvalidate();
            }
        } else if (action == 1) {
            this.isUpperMoving = false;
            this.isLowerMoving = false;
        } else if (action == 2) {
            if (this.isLowerMoving) {
                CommonLogUtil.d(TAG, "onTouchEvent: " + x + AppInfo.DELIM + this.lineEnd + AppInfo.DELIM + (this.bitmapWidth / 2));
                if (x <= this.lineEnd + 20) {
                    int i = this.lineStart;
                    if (x >= i - (this.bitmapWidth / 2)) {
                        this.slideLowX = (int) x;
                        if (this.slideLowX < i) {
                            this.slideLowX = i;
                        }
                        updateRange();
                        postInvalidate();
                    }
                }
            } else if (this.isUpperMoving) {
                int i2 = this.slideLowX;
                int i3 = this.bitmapWidth;
                if (x >= i2 + i3 && x <= this.lineEnd + (i3 / 2)) {
                    updateRange();
                    postInvalidate();
                }
            }
        }
        return true;
    }

    private void updateRange() {
        this.smallRange = computRange(this.slideLowX);
        CommonLogUtil.d(TAG, "updateRange: " + this.smallRange);
        onRangeListener onrangelistener = this.onRangeListener;
        if (onrangelistener != null) {
            onrangelistener.onRange(this.smallRange);
        }
    }

    private float computRange(float f2) {
        float f3 = f2 - this.lineStart;
        int i = this.bigValue;
        return ((f3 * (i - r1)) / this.lineLength) + this.smallValue;
    }

    private float computValue(float f2) {
        CommonLogUtil.d(TAG, "computValue: " + this.smallValue + AppInfo.DELIM + this.lineLength + AppInfo.DELIM + this.bigValue + AppInfo.DELIM + this.smallValue + AppInfo.DELIM + this.lineStart);
        return (((f2 - this.smallValue) * this.lineLength) / (this.bigValue - r0)) + this.lineStart;
    }

    public int dip2px(Context context, float f2) {
        return (int) ((f2 * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public void setOnRangeListener(onRangeListener onrangelistener) {
        this.onRangeListener = onrangelistener;
    }

    public void setCurrent(float f2) {
        this.slideLowX = (int) computValue(f2);
        CommonLogUtil.d(TAG, "setCurrent: " + this.slideLowX);
        invalidate();
    }
}