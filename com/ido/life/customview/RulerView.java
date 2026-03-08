package com.ido.life.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.google.android.material.timepicker.TimeModel;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.ResourceUtil;
import java.util.Calendar;

/* JADX INFO: loaded from: classes2.dex */
public class RulerView extends View {
    private static final float LINE_SCALE_FOR_HIGHT = 0.25f;
    private static final int LINE_WIDTH = 2;
    private static final float LONG_LINE_SCALE = 1.0f;
    private static final float MEDIUM_LINE_SCALE = 0.7f;
    private static final float SHORT_LINE_SCALE = 0.4f;
    private String[] array;
    private float centerData;
    private String content;
    private String currentUnit;
    private int defaultOffset;
    private float defaultRulerData;
    private int defaultRulerScrollX;
    float downX;
    float downY;
    protected int drawCount;
    private Runnable endDraw;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f4691h;
    private int halfScreenLines;
    private boolean isCalculateFinish;
    private boolean isScroll;
    private boolean isWeight;
    private float lineScale;
    private OnDataSelectedListener listener;
    private float longLineLength;
    private int mArrowLineColor;
    private int mBitHeight;
    private int mBitWidth;
    private boolean mCurrentLineShow;
    private Rect mDestRectBottom;
    private Rect mDestRectTop;
    private int mDip15px;
    protected int mRulerTextColor;
    private int mScaleLineColor;
    private int mScaleLineColor_50;
    private int mShortLine;
    private Rect mSrcRect;
    private int mTitleDataColor;
    private int mTitleTextColor;
    protected int mVelocity;
    private int maxData;
    private int maxLineNum;
    private int maxOffset;
    private int maxScreenLineNum;
    private int minData;
    private float padding;
    private Paint paint;
    private float pre;
    private int smallStepWidth;
    private int spaceWidth;
    private float startX;
    private int stepData;
    private int stepLineNum;
    private float textSize;
    private String title;
    float touchY;
    private String unit;
    private String unitBig;
    private int vHight;
    private int vWidth;
    private VelocityTracker velocityTracker;
    private int w;

    public interface OnDataSelectedListener {
        void OnDataSelected(float f2);
    }

    public RulerView(Context context) {
        super(context);
        this.centerData = 0.0f;
        this.maxData = 1000;
        this.minData = 0;
        this.stepData = 10;
        this.mScaleLineColor = -1;
        this.mScaleLineColor_50 = -2130706433;
        this.mRulerTextColor = -5657684;
        this.mArrowLineColor = -1943502;
        this.mTitleTextColor = -11842741;
        this.mTitleDataColor = -1943502;
        this.isWeight = false;
        this.array = new String[]{ResourceUtil.getString(R.string.public_unit_gong_jin), ResourceUtil.getString(R.string.public_unit_pound)};
        this.padding = 15.0f;
        this.mCurrentLineShow = true;
        this.unit = "";
        this.unitBig = "";
        this.isCalculateFinish = false;
        this.defaultRulerScrollX = 0;
        this.defaultRulerData = 0.0f;
        this.endDraw = new Runnable() { // from class: com.ido.life.customview.RulerView.1
            private int MAX_REDRAW_COUNT = 8;

            @Override // java.lang.Runnable
            public void run() {
                if (RulerView.this.drawCount < this.MAX_REDRAW_COUNT && Math.abs(RulerView.this.mVelocity) > 5000) {
                    double d2 = ((double) RulerView.this.mVelocity) * 0.05d;
                    int i = this.MAX_REDRAW_COUNT;
                    RulerView.this.scrollBy(-((int) ((d2 / ((double) i)) * (((double) (i - RulerView.this.drawCount)) + 0.5d))), 0);
                    RulerView.this.drawCount++;
                    RulerView rulerView = RulerView.this;
                    rulerView.postDelayed(rulerView.endDraw, 0L);
                    return;
                }
                RulerView.this.endScroll();
            }
        };
        init();
    }

    public RulerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.centerData = 0.0f;
        this.maxData = 1000;
        this.minData = 0;
        this.stepData = 10;
        this.mScaleLineColor = -1;
        this.mScaleLineColor_50 = -2130706433;
        this.mRulerTextColor = -5657684;
        this.mArrowLineColor = -1943502;
        this.mTitleTextColor = -11842741;
        this.mTitleDataColor = -1943502;
        this.isWeight = false;
        this.array = new String[]{ResourceUtil.getString(R.string.public_unit_gong_jin), ResourceUtil.getString(R.string.public_unit_pound)};
        this.padding = 15.0f;
        this.mCurrentLineShow = true;
        this.unit = "";
        this.unitBig = "";
        this.isCalculateFinish = false;
        this.defaultRulerScrollX = 0;
        this.defaultRulerData = 0.0f;
        this.endDraw = new Runnable() { // from class: com.ido.life.customview.RulerView.1
            private int MAX_REDRAW_COUNT = 8;

            @Override // java.lang.Runnable
            public void run() {
                if (RulerView.this.drawCount < this.MAX_REDRAW_COUNT && Math.abs(RulerView.this.mVelocity) > 5000) {
                    double d2 = ((double) RulerView.this.mVelocity) * 0.05d;
                    int i = this.MAX_REDRAW_COUNT;
                    RulerView.this.scrollBy(-((int) ((d2 / ((double) i)) * (((double) (i - RulerView.this.drawCount)) + 0.5d))), 0);
                    RulerView.this.drawCount++;
                    RulerView rulerView = RulerView.this;
                    rulerView.postDelayed(rulerView.endDraw, 0L);
                    return;
                }
                RulerView.this.endScroll();
            }
        };
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.ido.life.R.styleable.RulerView);
        this.mScaleLineColor = typedArrayObtainStyledAttributes.getColor(6, -1);
        this.mScaleLineColor_50 = typedArrayObtainStyledAttributes.getColor(6, -2130706433);
        this.mArrowLineColor = typedArrayObtainStyledAttributes.getColor(0, -1943502);
        this.mTitleTextColor = typedArrayObtainStyledAttributes.getColor(11, -11842741);
        this.mTitleDataColor = typedArrayObtainStyledAttributes.getColor(10, -1943502);
        this.mCurrentLineShow = typedArrayObtainStyledAttributes.getBoolean(1, true);
        this.isScroll = typedArrayObtainStyledAttributes.getBoolean(3, false);
        int i = Calendar.getInstance().get(1);
        this.maxData = typedArrayObtainStyledAttributes.getInteger(4, i);
        this.minData = typedArrayObtainStyledAttributes.getInteger(5, i - 150);
        this.stepData = typedArrayObtainStyledAttributes.getInteger(7, 10);
        this.halfScreenLines = typedArrayObtainStyledAttributes.getInteger(2, 20);
        this.stepLineNum = typedArrayObtainStyledAttributes.getInt(8, 5);
        this.unit = typedArrayObtainStyledAttributes.getString(12);
        this.unitBig = typedArrayObtainStyledAttributes.getString(13);
        this.title = typedArrayObtainStyledAttributes.getString(9);
        this.unit = getNewStr(this.unit);
        this.unitBig = getNewStr(this.unitBig);
        this.title = getNewStr(this.title);
        typedArrayObtainStyledAttributes.recycle();
        init();
    }

    public RulerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.centerData = 0.0f;
        this.maxData = 1000;
        this.minData = 0;
        this.stepData = 10;
        this.mScaleLineColor = -1;
        this.mScaleLineColor_50 = -2130706433;
        this.mRulerTextColor = -5657684;
        this.mArrowLineColor = -1943502;
        this.mTitleTextColor = -11842741;
        this.mTitleDataColor = -1943502;
        this.isWeight = false;
        this.array = new String[]{ResourceUtil.getString(R.string.public_unit_gong_jin), ResourceUtil.getString(R.string.public_unit_pound)};
        this.padding = 15.0f;
        this.mCurrentLineShow = true;
        this.unit = "";
        this.unitBig = "";
        this.isCalculateFinish = false;
        this.defaultRulerScrollX = 0;
        this.defaultRulerData = 0.0f;
        this.endDraw = new Runnable() { // from class: com.ido.life.customview.RulerView.1
            private int MAX_REDRAW_COUNT = 8;

            @Override // java.lang.Runnable
            public void run() {
                if (RulerView.this.drawCount < this.MAX_REDRAW_COUNT && Math.abs(RulerView.this.mVelocity) > 5000) {
                    double d2 = ((double) RulerView.this.mVelocity) * 0.05d;
                    int i2 = this.MAX_REDRAW_COUNT;
                    RulerView.this.scrollBy(-((int) ((d2 / ((double) i2)) * (((double) (i2 - RulerView.this.drawCount)) + 0.5d))), 0);
                    RulerView.this.drawCount++;
                    RulerView rulerView = RulerView.this;
                    rulerView.postDelayed(rulerView.endDraw, 0L);
                    return;
                }
                RulerView.this.endScroll();
            }
        };
        init();
    }

    public static float getTextRectWidth(Paint paint, String str) {
        paint.getTextBounds(str, 0, str.length(), new Rect());
        return r0.width();
    }

    public static float getTextHeight(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return fontMetrics.bottom - fontMetrics.top;
    }

    public boolean isWeight() {
        return this.isWeight;
    }

    public void setIsWeight(boolean z) {
        this.isWeight = z;
    }

    public String getCurrentUnit() {
        return this.currentUnit;
    }

    public void setCurrentUnit(String str) {
        this.currentUnit = str;
    }

    public boolean ismCurrentLineShow() {
        return this.mCurrentLineShow;
    }

    public void setmCurrentLineShow(boolean z) {
        this.mCurrentLineShow = z;
    }

    private String getNewStr(String str) {
        return (str == null || str.equals("")) ? "" : str;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        Log.d("ywx", "onSizeChanged(" + i + AppInfo.DELIM + i2 + AppInfo.DELIM + i3 + AppInfo.DELIM + i4 + ")");
        StringBuilder sb = new StringBuilder();
        sb.append("pLeft:");
        sb.append(getPaddingLeft());
        sb.append(" pRight:");
        sb.append(getPaddingRight());
        sb.append(" pBottom:");
        sb.append(getPaddingBottom());
        sb.append(" pTop:");
        sb.append(getPaddingTop());
        Log.d("ywx", sb.toString());
        Log.d("ywx", "getWidth:" + getWidth() + " getHeight:" + getHeight());
        this.w = i;
        this.f4691h = i2;
        calulateData();
    }

    public void calulateData() {
        this.vWidth = (this.w - getPaddingLeft()) - getPaddingRight();
        this.vHight = (this.f4691h - getPaddingBottom()) - getPaddingTop();
        this.longLineLength = this.vHight * 0.25f;
        int i = this.maxData;
        int i2 = this.minData;
        this.maxLineNum = (int) ((((i - i2) / (this.stepData * 1.0f)) * this.stepLineNum * 2) + 1.0f);
        this.maxScreenLineNum = (this.halfScreenLines * 2) + 1;
        int i3 = this.vWidth;
        int i4 = this.maxScreenLineNum;
        this.spaceWidth = (i3 - (i4 * 2)) / (i4 - 1);
        this.smallStepWidth = this.spaceWidth + 2;
        this.defaultOffset = (this.w / 2) - 1;
        this.maxOffset = (this.maxLineNum - 1) * this.smallStepWidth;
        this.textSize = this.longLineLength * 0.2f;
        this.isCalculateFinish = true;
        this.centerData = i2;
        setData(this.defaultRulerData);
    }

    public void initData(String str, String str2, int i, int i2, int i3, int i4) {
        this.mShortLine = i2 % 10;
        this.maxData = i;
        this.minData = i2;
        this.stepData = i3;
        this.stepLineNum = i4;
        this.unit = str;
        this.unitBig = str2;
        calulateData();
        invalidate();
    }

    public void init() {
        this.paint = new Paint(1);
        this.paint.setStrokeWidth(2.0f);
        this.paint.setTextAlign(Paint.Align.CENTER);
        this.mDip15px = DipPixelUtil.dip2px(15.0f);
        this.mSrcRect = new Rect(0, 0, this.mBitWidth, this.mBitHeight);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLines(canvas);
        drawTitle(canvas);
        drawShader(canvas);
    }

    public void drawLines(Canvas canvas) {
        this.paint.setStrokeWidth(2.0f);
        int scrollX = getScrollX();
        int i = this.defaultOffset;
        int i2 = i > scrollX ? 0 : ((scrollX - i) / this.smallStepWidth) + 1;
        int iMax = Math.max(this.maxScreenLineNum + i2, this.maxLineNum);
        int i3 = this.maxLineNum;
        int i4 = iMax >= i3 ? i3 : iMax;
        canvas.save();
        int i5 = this.defaultOffset - scrollX;
        canvas.translate(i5, 0.0f);
        this.lineScale = 1.0f;
        for (int i6 = i2; i6 < i4; i6++) {
            int i7 = this.smallStepWidth;
            int i8 = (i6 * i7) + scrollX;
            int i9 = (i7 * i6) + i5;
            if (i9 < getPaddingLeft() || i9 > getWidth() - getPaddingRight()) {
                this.paint.setColor(0);
            } else {
                this.paint.setColor(this.mScaleLineColor);
                this.paint.setTextSize(this.textSize);
            }
            String text = getText(i6);
            int i10 = this.mShortLine + i6;
            int i11 = this.stepLineNum;
            if (i10 % i11 == 0) {
                if (i10 % (i11 * 2) != 0) {
                    this.lineScale = 0.7f;
                } else {
                    this.lineScale = 1.0f;
                    if (!"lbs".equals(this.unit) || !String.valueOf(this.maxData).equals(text)) {
                        this.paint.setColor(this.mRulerTextColor);
                        canvas.drawText(text, i8 - this.spaceWidth, this.vHight + (this.longLineLength * SHORT_LINE_SCALE), this.paint);
                    }
                }
            } else {
                this.lineScale = SHORT_LINE_SCALE;
            }
            int iIntValue = Integer.valueOf(text).intValue();
            if (!"lbs".equals(this.unit) || iIntValue <= 451) {
                if (i10 % this.stepLineNum == 0) {
                    this.paint.setColor(this.mScaleLineColor);
                } else {
                    this.paint.setColor(this.mScaleLineColor_50);
                }
                float f2 = i8;
                int i12 = this.vHight;
                canvas.drawLine(f2, i12, f2, i12 - (this.longLineLength * this.lineScale), this.paint);
            }
        }
        canvas.restore();
    }

    private void drawTitle(Canvas canvas) {
        int width = ((getWidth() / 2) - 1) + getScrollX();
        int i = (int) (this.vHight - (this.longLineLength * 1.0f));
        this.paint.setColor(this.mArrowLineColor);
        this.paint.setStrokeWidth(5.0f);
        float f2 = width;
        float f3 = i;
        canvas.drawLine(f2, this.vHight, f2, f3, this.paint);
        float f4 = this.longLineLength * 0.1f;
        canvas.drawCircle(f2, f3 - f4, f4, this.paint);
        this.paint.setStrokeWidth(1.0f);
        this.paint.setColor(this.mTitleTextColor);
        float f5 = this.textSize;
        float f6 = 1.4f * f5;
        float f7 = f5 * 2.2f;
        int i2 = (int) (f3 - (this.longLineLength * 1.0f));
        if (this.mCurrentLineShow) {
            Log.i("RulerView", "" + this.mCurrentLineShow + "走外层");
            if (!this.unit.equals("") && !this.unitBig.equals("")) {
                float f8 = this.centerData;
                int i3 = this.stepData;
                int i4 = (int) (f8 / i3);
                int i5 = (int) (f8 % i3);
                float f9 = this.longLineLength * 0.1f;
                this.content = i4 + this.unitBig + i5 + this.unit;
                this.paint.setTextSize(f7);
                String str = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i4));
                String str2 = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i5));
                this.paint.setColor(this.mTitleDataColor);
                this.paint.setTextSize(f7);
                float textRectWidth = getTextRectWidth(this.paint, str);
                float textRectWidth2 = getTextRectWidth(this.paint, str2);
                this.paint.setTextSize(f6);
                float textRectWidth3 = getTextRectWidth(this.paint, this.unitBig);
                float textRectWidth4 = ((((((textRectWidth + f9) + textRectWidth3) + f9) + textRectWidth2) + f9) + getTextRectWidth(this.paint, this.unit)) / 2.0f;
                this.paint.setTextAlign(Paint.Align.LEFT);
                this.paint.setTextSize(f7);
                this.paint.setColor(this.mTitleDataColor);
                int iDescent = (int) (i2 - ((this.paint.descent() + this.paint.ascent()) / 2.0f));
                float f10 = f2 - textRectWidth4;
                float f11 = iDescent;
                canvas.drawText(str, f10, f11, this.paint);
                float f12 = f10 + textRectWidth + f9;
                float f13 = textRectWidth3 + f12 + f9;
                canvas.drawText(str2, f13, f11, this.paint);
                this.paint.setTextSize(f6);
                this.paint.setColor(this.mTitleTextColor);
                canvas.drawText(this.unitBig, f12, f11, this.paint);
                canvas.drawText(this.unit, f13 + textRectWidth2 + f9, f11, this.paint);
                Log.i("RulerView", "" + this.title + "...1");
                if (this.title.equals("")) {
                    return;
                }
                this.paint.setTextAlign(Paint.Align.CENTER);
                int textHeight = (int) (f11 - (getTextHeight(this.paint) * 1.3f));
                this.paint.setTextSize(f6);
                this.paint.setColor(this.mTitleTextColor);
                this.content = this.title;
                Log.i("RulerView", "" + this.title + "...1.2");
                canvas.drawText(this.content, f2, (float) textHeight, this.paint);
                return;
            }
            if ((!this.unit.equals("") && this.unitBig.equals("")) || (this.unit.equals("") && !this.unitBig.equals(""))) {
                if (!this.unit.equals("")) {
                    this.content = this.unit;
                } else {
                    this.content = this.unitBig;
                }
                this.paint.setColor(this.mTitleDataColor);
                this.paint.setTextSize(f6);
                this.paint.setColor(this.mTitleTextColor);
                float f14 = i2;
                canvas.drawText(this.content, f2, f14, this.paint);
                int textHeight2 = (int) (f14 - getTextHeight(this.paint));
                this.paint.setTextSize(f7);
                float f15 = this.centerData;
                int i6 = this.minData;
                if (f15 < i6) {
                    this.centerData = i6;
                }
                this.content = this.centerData + "";
                this.paint.setColor(this.mTitleDataColor);
                float f16 = (float) textHeight2;
                canvas.drawText(this.content, f2, f16, this.paint);
                Log.i("RulerView", "" + this.title + "...2.1");
                if (this.title.equals("")) {
                    return;
                }
                int textHeight3 = (int) (f16 - getTextHeight(this.paint));
                this.paint.setColor(this.mTitleTextColor);
                this.paint.setTextSize(f6);
                this.content = this.title;
                Log.i("RulerView", "" + this.title + "...2.2");
                canvas.drawText(this.content, f2, (float) textHeight3, this.paint);
                return;
            }
            Log.i("RulerView", "" + this.title + "...2.3");
            this.paint.setTextSize(f7);
            float f17 = this.centerData;
            int i7 = this.minData;
            if (f17 < i7) {
                this.centerData = i7;
            }
            this.content = this.centerData + "";
            float f18 = (float) i2;
            canvas.drawText(this.content, f2, f18, this.paint);
            if (this.title.equals("")) {
                return;
            }
            int textHeight4 = (int) (f18 - getTextHeight(this.paint));
            this.paint.setTextSize(f6);
            this.content = this.title;
            canvas.drawText(this.content, f2, textHeight4, this.paint);
            return;
        }
        Log.i("RulerView", "" + this.mCurrentLineShow + "走里层");
        if (!this.unit.equals("") && !this.unitBig.equals("")) {
            float f19 = this.centerData;
            int i8 = this.stepData;
            int i9 = (int) (f19 / i8);
            int i10 = (int) (f19 % i8);
            float f20 = this.longLineLength * 0.1f;
            this.content = i9 + this.unitBig + i10 + this.unit;
            this.paint.setTextSize(f7);
            String str3 = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i9));
            String str4 = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i10));
            this.paint.setTextSize(f7);
            float textRectWidth5 = getTextRectWidth(this.paint, str3);
            float textRectWidth6 = getTextRectWidth(this.paint, str4);
            this.paint.setTextSize(f6);
            float textRectWidth7 = getTextRectWidth(this.paint, this.unitBig);
            float textRectWidth8 = ((((((textRectWidth5 + f20) + textRectWidth7) + f20) + textRectWidth6) + f20) + getTextRectWidth(this.paint, this.unit)) / 2.0f;
            this.paint.setTextAlign(Paint.Align.LEFT);
            this.paint.setTextSize(f7);
            float f21 = f2 - textRectWidth8;
            float f22 = i2;
            canvas.drawText(str3, f21, f22, this.paint);
            float f23 = f21 + textRectWidth5 + f20;
            canvas.drawText(str4, textRectWidth7 + f23 + f20, f22, this.paint);
            this.paint.setTextSize(f6);
            canvas.drawText(this.unitBig, f23, f22, this.paint);
            if (this.title.equals("")) {
                return;
            }
            this.paint.setTextAlign(Paint.Align.CENTER);
            this.paint.setTextSize(f6);
            this.content = this.title;
            canvas.drawText(this.content, f23, f22, this.paint);
            return;
        }
        if ((!this.unit.equals("") && this.unitBig.equals("")) || (this.unit.equals("") && !this.unitBig.equals(""))) {
            this.paint.setTextSize(f7);
            this.paint.setColor(this.mTitleDataColor);
            this.paint.setTextAlign(Paint.Align.CENTER);
            if (this.unit.equals(this.array[1])) {
                this.content = ((int) this.centerData) + "";
            } else {
                this.content = this.centerData + "";
            }
            float f24 = i2;
            canvas.drawText(this.content, f2, f24, this.paint);
            float textRectWidth9 = getTextRectWidth(this.paint, this.content);
            if (!this.unit.equals("")) {
                if (this.unit.equals("kg")) {
                    this.content = this.array[0];
                } else if (this.unit.equals("lbs")) {
                    this.content = this.array[1];
                } else {
                    this.content = this.unit;
                }
            } else {
                this.content = this.unitBig;
            }
            this.paint.setTextAlign(Paint.Align.LEFT);
            this.paint.setTextSize(f6);
            this.paint.setColor(this.mTitleTextColor);
            float f25 = textRectWidth9 / 2.0f;
            canvas.drawText(this.content, f2 + f25 + this.padding, f24, this.paint);
            if (this.title.equals("")) {
                return;
            }
            this.content = this.title;
            this.paint.setTextSize(f6);
            this.paint.setTextAlign(Paint.Align.RIGHT);
            this.paint.setColor(this.mTitleTextColor);
            canvas.drawText(this.content, (f2 - f25) - this.padding, f24, this.paint);
            return;
        }
        this.paint.setTextSize(f7);
        this.paint.setColor(this.mTitleDataColor);
        this.content = this.centerData + "";
        this.paint.setTextAlign(Paint.Align.LEFT);
        float f26 = (float) i2;
        canvas.drawText(this.content, f2, f26, this.paint);
        getTextRectWidth(this.paint, this.content);
        if (this.title.equals("")) {
            return;
        }
        this.content = this.title;
        this.paint.setTextSize(f6);
        this.paint.setColor(this.mTitleTextColor);
        this.paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(this.content, f2, f26, this.paint);
    }

    public void setData(float f2) {
        this.defaultRulerData = f2;
        this.centerData = f2;
        float f3 = this.defaultRulerData - this.minData;
        if (f3 < 0.0f) {
            f3 = 0.0f;
        }
        this.defaultRulerScrollX = (int) ((((f3 * this.stepLineNum) * 2.0f) / this.stepData) * this.smallStepWidth);
        if (this.isCalculateFinish) {
            scrollTo(this.defaultRulerScrollX, 0);
        }
        postDelayed(this.endDraw, 50L);
        invalidate();
    }

    public void setOffset(int i) {
        this.defaultRulerData = i;
        if (this.isCalculateFinish) {
            this.defaultRulerScrollX = i * this.smallStepWidth;
            scrollTo(this.defaultRulerScrollX, 0);
        }
    }

    public float getCenterData() {
        return this.centerData;
    }

    private void drawShader(Canvas canvas) {
        int iComputeHorizontalScrollOffset = computeHorizontalScrollOffset();
        int paddingLeft = getPaddingLeft();
        this.paint.setShader(new LinearGradient((paddingLeft / 2) + iComputeHorizontalScrollOffset, 0.0f, this.vWidth + iComputeHorizontalScrollOffset + (getPaddingRight() / 2) + paddingLeft, 0.0f, new int[]{-14803426, 1260264990, 1973790, 1260264990, -14803426}, new float[]{0.0f, 0.3f, 0.5f, 0.7f, 1.0f}, Shader.TileMode.CLAMP));
        canvas.drawRect(iComputeHorizontalScrollOffset, this.vHight - (this.longLineLength * 1.5f), iComputeHorizontalScrollOffset + getWidth(), this.vHight, this.paint);
        this.paint.setShader(null);
    }

    @Override // android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        this.centerData = getDataByOffsetX(getRealOffset(getScrollX()));
        OnDataSelectedListener onDataSelectedListener = this.listener;
        if (onDataSelectedListener != null) {
            onDataSelectedListener.OnDataSelected(this.centerData);
        }
    }

    public float getDataByOffsetX(float f2) {
        this.centerData = ((int) (((((f2 / this.smallStepWidth) * this.stepData) / (this.stepLineNum * 2)) + this.minData) * 10.0f)) / 10.0f;
        return this.centerData;
    }

    public int getRealOffset(int i) {
        return Math.min(this.maxOffset, Math.max(0, i));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            removeCallbacks(this.endDraw);
            initOrResetVelocityTracker(motionEvent);
            getParent().requestDisallowInterceptTouchEvent(true);
            this.startX = motionEvent.getRawX();
            this.pre = this.startX;
            this.touchY = motionEvent.getRawY();
            this.downX = motionEvent.getX();
            this.downY = motionEvent.getY();
            this.drawCount = 1;
        } else if (action == 1) {
            VelocityTracker velocityTracker = this.velocityTracker;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
                this.velocityTracker.computeCurrentVelocity(1000, ViewConfiguration.get(getContext()).getScaledMaximumFlingVelocity());
                this.mVelocity = (int) this.velocityTracker.getXVelocity();
            }
            postDelayed(this.endDraw, 50L);
            getParent().requestDisallowInterceptTouchEvent(false);
        } else if (action == 2) {
            VelocityTracker velocityTracker2 = this.velocityTracker;
            if (velocityTracker2 != null) {
                velocityTracker2.addMovement(motionEvent);
            }
            getParent().requestDisallowInterceptTouchEvent(true);
            if (Math.abs(motionEvent.getX() - this.downX) > Math.abs(motionEvent.getY() - this.downY) / 2.0f) {
                if (this.isScroll) {
                    scrollBy((int) (this.pre - motionEvent.getRawX()), 0);
                    this.pre = motionEvent.getRawX();
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
            } else {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        } else if (action == 3) {
            endScroll();
        }
        return true;
    }

    public void initOrResetVelocityTracker(MotionEvent motionEvent) {
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
            this.velocityTracker.addMovement(motionEvent);
        } else {
            velocityTracker.clear();
        }
    }

    public void releaseVelocityTracker() {
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker != null) {
            velocityTracker.clear();
            this.velocityTracker.recycle();
            this.velocityTracker = null;
        }
    }

    public void endScroll() {
        int scrollX = getScrollX();
        int i = this.smallStepWidth;
        int i2 = scrollX % i;
        int i3 = scrollX + (((float) i2) > ((float) i) * 0.5f ? i - i2 : -i2);
        "lbs".equals(this.unit);
        int i4 = (int) (((this.maxData - this.minData) / (this.stepData * 1.0f)) * this.stepLineNum * 2.0f * this.smallStepWidth);
        if (i3 < 0) {
            i3 = 0;
        } else if (i3 >= i4) {
            i3 = i4;
        }
        scrollTo(i3, 0);
        releaseVelocityTracker();
        getParent().requestDisallowInterceptTouchEvent(false);
    }

    public String getText(int i) {
        if (!this.unitBig.equals("") && !this.unit.equals("")) {
            StringBuilder sb = new StringBuilder();
            int i2 = this.stepData;
            sb.append((((i * i2) / (this.stepLineNum * 2)) + this.minData) / i2);
            sb.append("");
            return sb.toString();
        }
        return (((i * this.stepData) / (this.stepLineNum * 2)) + this.minData) + "";
    }

    public void setListener(OnDataSelectedListener onDataSelectedListener) {
        this.listener = onDataSelectedListener;
    }

    private int dpToPx(int i) {
        return (int) ((i * getContext().getResources().getDisplayMetrics().density) + ((i >= 0 ? 1 : -1) * 0.5f));
    }
}