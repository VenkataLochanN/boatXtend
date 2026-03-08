package com.ido.life.module.sport.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.ido.common.utils.DipPixelUtil;

/* JADX INFO: loaded from: classes2.dex */
public class SportStatusProgressView extends View {
    private static final String TAG = "SportStatusProgressView";
    private final int DEFAULT_BG_COLOR;
    private final int DEFAULT_FINISHED_COLOR;
    private final int DEFAULT_UNFINISHED_COLOR;
    private float leftTextPadding;
    private float mBarHeight;
    private RectF mBgRectF;
    private String mEndDescribe;
    private int mEndTextColor;
    private int mEndTextSize;
    private float mMarginEnd;
    private float mMax;
    private Paint mPaint;
    private Paint mPaintEndText;
    private String mPrefix;
    private float mProgress;
    private String mProgressText;
    private int mProgressTextColor;
    private int mProgressTextSize;
    private float mRadius;
    private int mReachedBarColor;
    private int mReachedBarPreColor;
    private RectF mReachedRectF;
    private String mStartText;
    private int mStartTextColor;
    private String mSuffix;
    private float mTextSize;
    private boolean mTextVisibility;
    private int mUnreachedBarColor;
    private float rightTextPadding;

    public void setData() {
    }

    public void setMarginEnd(int i) {
    }

    public SportStatusProgressView(Context context) {
        this(context, null);
    }

    public SportStatusProgressView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SportStatusProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mMax = 100.0f;
        this.mProgress = 0.0f;
        this.mRadius = DipPixelUtil.dip2px(16.0f);
        this.DEFAULT_FINISHED_COLOR = getResources().getColor(R.color.color_2BEECC);
        this.DEFAULT_UNFINISHED_COLOR = getResources().getColor(R.color.color_F8F8FA);
        this.DEFAULT_BG_COLOR = getResources().getColor(R.color.color_F8F8FA);
        this.mSuffix = "%";
        this.mPrefix = "";
        this.mStartText = "";
        this.mProgressText = "";
        this.mEndDescribe = "最快";
        this.mBgRectF = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        this.mReachedRectF = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        this.mEndTextColor = getResources().getColor(R.color.color_82868F);
        this.mProgressTextColor = getResources().getColor(R.color.color_006D5A);
        this.mEndTextSize = getResources().getDimensionPixelSize(R.dimen.size10sp);
        this.mProgressTextSize = DipPixelUtil.sp2px(12.0f);
        this.leftTextPadding = dp2px(8.0f);
        this.rightTextPadding = dp2px(8.0f);
        this.mMarginEnd = DipPixelUtil.dip2px(0.0f);
        initAttrs(context, attributeSet);
        initPainters();
    }

    private void initAttrs(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.ido.life.R.styleable.SportProgressBar);
        this.mMax = typedArrayObtainStyledAttributes.getInteger(1, (int) this.mMax);
        this.mProgress = typedArrayObtainStyledAttributes.getInteger(3, (int) this.mProgress);
        this.mReachedBarColor = typedArrayObtainStyledAttributes.getColor(6, this.DEFAULT_FINISHED_COLOR);
        this.mReachedBarPreColor = typedArrayObtainStyledAttributes.getColor(7, this.DEFAULT_FINISHED_COLOR);
        this.mUnreachedBarColor = typedArrayObtainStyledAttributes.getColor(12, this.DEFAULT_UNFINISHED_COLOR);
        this.mStartTextColor = typedArrayObtainStyledAttributes.getColor(9, this.DEFAULT_UNFINISHED_COLOR);
        this.mTextSize = typedArrayObtainStyledAttributes.getDimension(10, getResources().getDimension(R.dimen.size12sp));
        this.mStartText = TextUtils.isEmpty(typedArrayObtainStyledAttributes.getString(5)) ? this.mStartText : typedArrayObtainStyledAttributes.getString(5);
        this.mSuffix = TextUtils.isEmpty(typedArrayObtainStyledAttributes.getString(8)) ? this.mSuffix : typedArrayObtainStyledAttributes.getString(8);
        this.mPrefix = TextUtils.isEmpty(typedArrayObtainStyledAttributes.getString(2)) ? this.mPrefix : typedArrayObtainStyledAttributes.getString(2);
        this.mBarHeight = typedArrayObtainStyledAttributes.getDimension(0, getResources().getDimension(R.dimen.sw_dp_22));
        this.mTextVisibility = typedArrayObtainStyledAttributes.getBoolean(11, true);
        this.mRadius = typedArrayObtainStyledAttributes.getDimension(4, getResources().getDimension(R.dimen.sw_dp_10));
        typedArrayObtainStyledAttributes.recycle();
    }

    private void initPainters() {
        this.mPaint = new Paint(1);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mPaintEndText = new Paint(1);
        this.mPaintEndText.setAntiAlias(true);
        this.mPaintEndText.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode != 1073741824) {
            size = getMeasuredWidth();
        }
        if (mode2 != 1073741824) {
            size2 = getMeasuredHeight();
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        calculateDrawRectFWithoutProgressText();
        this.mPaint.setColor(this.mUnreachedBarColor);
        canvas.drawRoundRect(this.mBgRectF, dp2px(this.mRadius), dp2px(this.mRadius), this.mPaint);
        this.mPaint.setColor(this.mReachedBarColor);
        canvas.drawRoundRect(this.mReachedRectF, dp2px(this.mRadius), dp2px(this.mRadius), this.mPaint);
        this.mPaint.setShader(null);
        this.mPaint.setColor(this.mStartTextColor);
        this.mPaint.setTextSize(this.mTextSize);
        this.mPaint.setFakeBoldText(true);
        String str = this.mStartText;
        if (this.mTextVisibility) {
            canvas.drawText(str, this.mReachedRectF.left + this.leftTextPadding, (int) ((getHeight() / 2.0f) - ((this.mPaint.descent() + this.mPaint.ascent()) / 2.0f)), this.mPaint);
        }
        drawProgressText(canvas);
        drawEndText(canvas);
    }

    private void drawEndText(Canvas canvas) {
        this.mPaintEndText.setColor(this.mEndTextColor);
        this.mPaintEndText.setTextSize(this.mEndTextSize);
        this.mPaintEndText.setFakeBoldText(true);
        if (TextUtils.isEmpty(this.mEndDescribe)) {
            return;
        }
        canvas.drawText(this.mEndDescribe, (getWidth() - this.rightTextPadding) - this.mPaintEndText.measureText(this.mEndDescribe), (int) ((getHeight() / 2.0f) - ((this.mPaintEndText.descent() + this.mPaintEndText.ascent()) / 2.0f)), this.mPaintEndText);
    }

    private void drawProgressText(Canvas canvas) {
        float width;
        this.mPaintEndText.setColor(this.mProgressTextColor);
        this.mPaintEndText.setTextSize(this.mProgressTextSize);
        this.mPaintEndText.setFakeBoldText(true);
        if (TextUtils.isEmpty(this.mProgressText)) {
            return;
        }
        if (TextUtils.isEmpty(this.mEndDescribe)) {
            width = 0.0f;
        } else {
            width = (getWidth() - this.rightTextPadding) - this.mPaintEndText.measureText(this.mEndDescribe);
        }
        float fMeasureText = this.mPaintEndText.measureText(this.mProgressText);
        float f2 = (this.mReachedRectF.right - this.rightTextPadding) - fMeasureText;
        if (width != 0.0f && this.mReachedRectF.right > width) {
            f2 = width - fMeasureText;
        }
        canvas.drawText(this.mProgressText, f2, (int) ((getHeight() / 2.0f) - ((this.mPaintEndText.descent() + this.mPaintEndText.ascent()) / 2.0f)), this.mPaintEndText);
    }

    private void calculateDrawRectFWithoutProgressText() {
        this.mPaintEndText.setTextSize(this.mEndTextSize);
        float fMeasureText = this.mPaintEndText.measureText(this.mProgressText);
        this.mPaint.setTextSize(this.mTextSize);
        float fMeasureText2 = this.mPaint.measureText(this.mStartText) + dp2px(10.0f);
        this.mReachedRectF.left = getPaddingLeft();
        this.mReachedRectF.top = (getHeight() / 2.0f) - (this.mBarHeight / 2.0f);
        this.mReachedRectF.right = (((((((getWidth() - this.leftTextPadding) - this.rightTextPadding) - fMeasureText) - fMeasureText2) - this.mMarginEnd) / (getMax() * 1.0f)) * getProgress()) + this.leftTextPadding + fMeasureText + fMeasureText2 + this.rightTextPadding;
        this.mReachedRectF.bottom = (getHeight() / 2.0f) + (this.mBarHeight / 2.0f);
        Log.d(TAG, "calculateDrawRectFWithoutProgressText: " + fMeasureText2 + AppInfo.DELIM + fMeasureText + AppInfo.DELIM + this.mProgressText + AppInfo.DELIM + this.mStartText + AppInfo.DELIM + this.mReachedRectF.right);
        this.mBgRectF.left = (float) getPaddingLeft();
        this.mBgRectF.top = (((float) getHeight()) / 2.0f) + ((-this.mBarHeight) / 2.0f);
        this.mBgRectF.right = ((float) (getWidth() - getPaddingRight())) - this.mMarginEnd;
        this.mBgRectF.bottom = (((float) getHeight()) / 2.0f) + (this.mBarHeight / 2.0f);
    }

    public float getMax() {
        return this.mMax;
    }

    public float getProgress() {
        return this.mProgress;
    }

    public void setMax(int i) {
        this.mMax = i;
    }

    public void setStartTextColor(int i) {
        this.mStartTextColor = i;
    }

    public void setEndTextColor(int i) {
        this.mEndTextColor = i;
    }

    public void setProgressTextColor(int i) {
        this.mProgressTextColor = i;
    }

    public void setProgressColor(int i) {
        this.mReachedBarColor = i;
    }

    public void setProgressBgColor(int i) {
        this.mUnreachedBarColor = i;
    }

    public void setProgress(float f2, String str, String str2, String str3) {
        this.mProgress = checkProgress(f2);
        this.mStartText = str;
        this.mProgressText = str2;
        this.mEndDescribe = str3;
        this.mRadius = getResources().getDimensionPixelSize(R.dimen.sw_dp_10);
        this.mBarHeight = getResources().getDimensionPixelSize(R.dimen.sw_dp_22);
        invalidate();
    }

    private float checkProgress(float f2) {
        if (f2 < 0.0f) {
            return 0.0f;
        }
        float f3 = this.mMax;
        return f2 > f3 ? f3 : f2;
    }

    private int dp2px(float f2) {
        return (int) ((f2 * getContext().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public void setReachedBarColor(int i, int i2) {
        this.mReachedBarColor = i2;
        this.mReachedBarPreColor = i;
        invalidate();
    }
}