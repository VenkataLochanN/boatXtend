package com.ido.life.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.DipPixelUtil;

/* JADX INFO: loaded from: classes2.dex */
public class ProgressView extends View {
    private static final String TAG = "ProgressView";
    private final int DEFAULT_FINISHED_COLOR;
    private final int DEFAULT_UNFINISHED_COLOR;
    private float mBarHeight;
    private float mMax;
    private Paint mPaint;
    private String mPrefix;
    private float mProgress;
    private int mReachedBarColor;
    private RectF mReachedRectF;
    private String mSuffix;
    private int mTextColor;
    private boolean mTextVisibility;
    private int mUnreachedBarColor;
    private RectF mUnreachedRectF;

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mMax = 100.0f;
        this.mProgress = 0.0f;
        this.DEFAULT_FINISHED_COLOR = getResources().getColor(R.color.color_line_user_green);
        this.DEFAULT_UNFINISHED_COLOR = getResources().getColor(R.color.color_line_user_gray);
        this.mSuffix = "%";
        this.mPrefix = "";
        this.mUnreachedRectF = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        this.mReachedRectF = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        initAttrs(context, attributeSet);
        initPainters();
    }

    private void initAttrs(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.ido.life.R.styleable.UpdataAPPProgressBar);
        this.mMax = typedArrayObtainStyledAttributes.getInteger(1, (int) this.mMax);
        this.mProgress = typedArrayObtainStyledAttributes.getInteger(3, (int) this.mProgress);
        this.mReachedBarColor = typedArrayObtainStyledAttributes.getColor(4, this.DEFAULT_FINISHED_COLOR);
        this.mUnreachedBarColor = typedArrayObtainStyledAttributes.getColor(8, this.DEFAULT_UNFINISHED_COLOR);
        this.mTextColor = typedArrayObtainStyledAttributes.getColor(6, this.DEFAULT_UNFINISHED_COLOR);
        this.mSuffix = TextUtils.isEmpty(typedArrayObtainStyledAttributes.getString(5)) ? this.mSuffix : typedArrayObtainStyledAttributes.getString(5);
        this.mPrefix = TextUtils.isEmpty(typedArrayObtainStyledAttributes.getString(2)) ? this.mPrefix : typedArrayObtainStyledAttributes.getString(2);
        this.mBarHeight = typedArrayObtainStyledAttributes.getDimension(0, dp2px(2.0f));
        this.mTextVisibility = typedArrayObtainStyledAttributes.getBoolean(7, true);
        typedArrayObtainStyledAttributes.recycle();
    }

    private void initPainters() {
        this.mPaint = new Paint(1);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        calculateDrawRectFWithoutProgressText();
        this.mPaint.setColor(this.mUnreachedBarColor);
        RectF rectF = this.mUnreachedRectF;
        float f2 = this.mBarHeight;
        canvas.drawRoundRect(rectF, f2 / 2.0f, f2 / 2.0f, this.mPaint);
        this.mPaint.setColor(this.mReachedBarColor);
        RectF rectF2 = this.mReachedRectF;
        float f3 = this.mBarHeight;
        canvas.drawRoundRect(rectF2, f3 / 2.0f, f3 / 2.0f, this.mPaint);
        this.mPaint.setColor(this.mTextColor);
        this.mPaint.setTextSize(this.mBarHeight * 0.6f);
        String str = this.mPrefix;
        float fMeasureText = this.mPaint.measureText(str);
        if (!this.mTextVisibility || getProgress() <= 0.0f || this.mReachedRectF.right <= fMeasureText) {
            return;
        }
        CommonLogUtil.d(TAG, "onDraw: " + this.mReachedRectF.right + AppInfo.DELIM + fMeasureText + AppInfo.DELIM + (this.mBarHeight * 0.4f));
        canvas.drawText(str, ((this.mUnreachedRectF.right - fMeasureText) - (this.mBarHeight * 0.4f)) - ((float) DipPixelUtil.dip2px(10.0f)), (float) ((int) ((((float) getHeight()) / 2.0f) - ((this.mPaint.descent() + this.mPaint.ascent()) / 2.0f))), this.mPaint);
    }

    private void calculateDrawRectFWithoutProgressText() {
        this.mReachedRectF.left = getPaddingLeft();
        this.mReachedRectF.top = (getHeight() / 2.0f) - (this.mBarHeight / 2.0f);
        this.mReachedRectF.right = ((((getWidth() - getPaddingLeft()) - getPaddingRight()) / (getMax() * 1.0f)) * getProgress()) + getPaddingLeft();
        this.mReachedRectF.bottom = (getHeight() / 2.0f) + (this.mBarHeight / 2.0f);
        this.mUnreachedRectF.left = getPaddingLeft();
        this.mUnreachedRectF.top = (getHeight() / 2.0f) + ((-this.mBarHeight) / 2.0f);
        this.mUnreachedRectF.right = getWidth() - getPaddingRight();
        this.mUnreachedRectF.bottom = (getHeight() / 2.0f) + (this.mBarHeight / 2.0f);
    }

    public float getMax() {
        return this.mMax;
    }

    public float getProgress() {
        return this.mProgress;
    }

    public void setMax(int i) {
        this.mMax = i;
        invalidate();
    }

    public void setProgress(float f2) {
        this.mProgress = checkProgress(f2);
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
}