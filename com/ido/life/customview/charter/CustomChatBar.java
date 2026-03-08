package com.ido.life.customview.charter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.R;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.bean.GoalLineInfo;
import com.ido.life.customview.CustomChartDetailViewPager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class CustomChatBar<T extends BaseCharBean> extends View implements View.OnTouchListener {
    private static final int MIN_FLING_VELOCITY = 1500;
    private static final String TAG = CustomChatBar.class.getSimpleName();
    public static final int UN_FOUND_INDEX = -2;
    private float defaultHeight;
    protected ValueAnimator mAnimator;
    protected int mAnimatorDuration;
    protected int mAnimatorRadius;
    private boolean mAutoXMaxinValue;
    private boolean mAutoYMaxinValue;
    public boolean mBottomLabelCenter;
    public int mBottomLabelColor;
    protected boolean mBottomLabelEnabled;
    protected int mBottomLabelLineDistance;
    protected int mBottomLabelSize;
    protected int mBottomLineColor;
    protected boolean mBottomLineEnabled;
    protected int mBottomLineHeight;
    public String mBottomTitle;
    protected int mBottomTitleColor;
    protected int mBottomTitleSize;
    protected int mBottomTitleToYDistance;
    protected int mCircleBorderWidth;
    public int mCircleColor;
    protected int mCircleRadius;
    public List<RectF> mCircleRegion;
    protected int mCircleSelectedColor;
    public int mCircleStrokeColor;
    public int mCircleStrokeSelectedColor;
    protected Paint.Style mCircleStyle;
    protected ChartClickListener mClickListener;
    protected int mDownX;
    protected int mDownY;
    protected boolean mDrawXGridLine;
    protected boolean mDrawYGridLine;
    public int mGoalLineColor;
    protected int mGoalLineDistance;
    public List<GoalLineInfo> mGoalLineList;
    protected int mGoalLineWidth;
    public int mGoalXLabelTextColor;
    protected int mGoalXLabelTextSize;
    protected int mGridXColor;
    protected int mGridXHeight;
    protected int mGridYColor;
    protected int mGridYWidth;
    private boolean mIsMoving;
    protected List<String> mLabelXList;
    public List<String> mLabelYLeftList;
    protected boolean mLabelYRightLableEnabled;
    public List<String> mLabelYRightList;
    protected Paint.Align mLeftLabelAlign;
    public int mLeftLabelColor;
    protected boolean mLeftLabelEnabled;
    protected int mLeftLabelLineDistance;
    protected int mLeftLabelSize;
    protected int mLeftLineColor;
    protected boolean mLeftLineEnabled;
    protected int mLeftLineWidth;
    public int mLineColor;
    protected int mLineWidth;
    public List<T> mList;
    private int mMaximumVelocity;
    protected int mMoveX;
    private int mMoveY;
    public int mOxygenRectBgColor;
    public boolean mOxygenRectBgEnable;
    protected Paint mPaint;
    protected Paint.Align mRightLabelAlign;
    protected int mRightLabelColor;
    protected int mRightLabelSize;
    protected int mScreenHeight;
    protected int mScreenWidth;
    protected Paint mSolidPaint;
    private boolean mSupportSelectInMove;
    protected int mTouchOffset;
    protected int mUpX;
    protected int mUpY;
    private VelocityTracker mVelocityTracker;
    protected int mXCount;
    protected int mXGoalLabelLineDistance;
    public CaluteXGridLineCallback mXGridLineCallback;
    protected boolean mXLineVisul;
    public int mXMaxValue;
    public int mXMinValue;
    protected int mYGridBottomLineDistance;
    protected int mYLeftCount;
    public float mYMaxValue;
    public float mYMinValue;
    protected int mYRightCount;

    public interface CaluteXGridLineCallback {
        float calculateXGridLineValue(View view, int i);
    }

    public interface ChartClickListener {
        void onChartClick(View view, RectF rectF, int i);
    }

    protected abstract void caluteCirclePosition();

    protected void drawRightLine(Canvas canvas) {
    }

    protected void drawTipView(Canvas canvas) {
    }

    protected void drawYGridLine(Canvas canvas) {
    }

    protected void onChartClick(int i) {
    }

    public void setDefaultHeight(float f2) {
        this.defaultHeight = f2;
    }

    public float getDefaultHeight() {
        return this.defaultHeight;
    }

    public CustomChatBar(Context context) {
        this(context, null);
    }

    public CustomChatBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public CustomChatBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBottomLineEnabled = true;
        this.mBottomLineColor = -3355444;
        this.mBottomLineHeight = 2;
        this.mBottomLabelEnabled = true;
        this.mBottomLabelSize = 12;
        this.mOxygenRectBgEnable = false;
        this.mOxygenRectBgColor = Color.parseColor("#ffffeef2");
        int i2 = this.mBottomLineColor;
        this.mBottomLabelColor = i2;
        this.mLeftLineEnabled = false;
        this.mLeftLineColor = i2;
        this.mLeftLineWidth = 2;
        this.mLeftLabelEnabled = true;
        this.mLeftLabelSize = 12;
        this.mLeftLabelColor = i2;
        this.mRightLabelSize = this.mLeftLabelSize;
        this.mRightLabelColor = i2;
        this.mGridXColor = i2;
        this.mGridXHeight = this.mBottomLineHeight;
        this.mGridYColor = i2;
        this.mCircleRadius = 4;
        this.mCircleBorderWidth = 2;
        this.mCircleStrokeColor = Color.parseColor("#67DAEC");
        this.mCircleStrokeSelectedColor = Color.parseColor("#FC5D68");
        this.mCircleColor = 0;
        this.mCircleSelectedColor = 0;
        this.mLineColor = -3355444;
        this.mLineWidth = 2;
        this.mGoalLineList = new ArrayList();
        this.mGoalLineColor = -3355444;
        this.mGoalLineDistance = 10;
        this.mCircleRegion = new ArrayList();
        this.mList = Collections.synchronizedList(new ArrayList());
        this.mXCount = 5;
        this.mYLeftCount = 0;
        this.mYRightCount = 0;
        this.mLeftLabelAlign = Paint.Align.RIGHT;
        this.mRightLabelAlign = Paint.Align.RIGHT;
        this.mYGridBottomLineDistance = 0;
        this.mXGoalLabelLineDistance = 4;
        this.mAnimatorDuration = 1000;
        this.mTouchOffset = 20;
        this.mAutoYMaxinValue = false;
        this.mAutoXMaxinValue = false;
        this.mGoalXLabelTextSize = 24;
        this.mGoalXLabelTextColor = Color.parseColor("#00CFFF");
        this.mBottomTitleColor = -1;
        this.mBottomTitleSize = -1;
        this.mBottomTitleToYDistance = 0;
        this.mSupportSelectInMove = true;
        this.mXLineVisul = true;
        init(attributeSet);
    }

    protected void init(AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        setDrawingCacheEnabled(true);
        this.mScreenWidth = getResources().getDisplayMetrics().widthPixels;
        this.mScreenHeight = getResources().getDisplayMetrics().heightPixels;
        this.mMaximumVelocity = ViewConfiguration.get(getContext()).getScaledMaximumFlingVelocity();
        this.mVelocityTracker = VelocityTracker.obtain();
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.CustomChatBar);
        this.mBottomLineEnabled = typedArrayObtainStyledAttributes.getBoolean(12, this.mBottomLineEnabled);
        this.mBottomLineColor = typedArrayObtainStyledAttributes.getColor(11, this.mBottomLineColor);
        this.mBottomLineHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(13, this.mBottomLineHeight);
        this.mBottomLabelEnabled = typedArrayObtainStyledAttributes.getBoolean(4, this.mBottomLabelEnabled);
        this.mBottomLabelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(6, this.mBottomLabelSize);
        this.mBottomLabelColor = typedArrayObtainStyledAttributes.getColor(3, this.mBottomLabelColor);
        this.mLeftLineEnabled = typedArrayObtainStyledAttributes.getBoolean(29, this.mLeftLineEnabled);
        this.mLeftLineColor = typedArrayObtainStyledAttributes.getColor(28, this.mLeftLineColor);
        this.mLeftLineWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(30, this.mLeftLineWidth);
        this.mLeftLabelEnabled = typedArrayObtainStyledAttributes.getBoolean(25, this.mLeftLabelEnabled);
        this.mLeftLabelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(27, this.mLeftLabelSize);
        this.mLeftLabelColor = typedArrayObtainStyledAttributes.getColor(24, this.mLeftLabelColor);
        this.mLabelYRightLableEnabled = typedArrayObtainStyledAttributes.getBoolean(36, this.mLabelYRightLableEnabled);
        this.mRightLabelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(37, this.mRightLabelSize);
        this.mRightLabelColor = typedArrayObtainStyledAttributes.getColor(35, this.mRightLabelColor);
        this.mDrawXGridLine = typedArrayObtainStyledAttributes.getBoolean(1, this.mDrawXGridLine);
        this.mDrawYGridLine = typedArrayObtainStyledAttributes.getBoolean(2, this.mDrawYGridLine);
        this.mGridXColor = typedArrayObtainStyledAttributes.getColor(43, this.mGridXColor);
        this.mGridXHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(45, this.mGridXHeight);
        this.mGridYColor = typedArrayObtainStyledAttributes.getColor(47, this.mGridYColor);
        this.mGridYWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(48, this.mGridYWidth);
        this.mCircleRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(15, this.mCircleRadius);
        this.mCircleBorderWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(14, this.mCircleBorderWidth);
        this.mCircleStrokeColor = typedArrayObtainStyledAttributes.getColor(18, this.mCircleStrokeColor);
        this.mCircleStrokeSelectedColor = typedArrayObtainStyledAttributes.getColor(19, this.mCircleStrokeSelectedColor);
        this.mCircleColor = typedArrayObtainStyledAttributes.getColor(16, this.mCircleColor);
        this.mCircleSelectedColor = typedArrayObtainStyledAttributes.getColor(17, this.mCircleSelectedColor);
        this.mLineColor = typedArrayObtainStyledAttributes.getColor(31, this.mLineColor);
        this.mLineWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(32, this.mLineWidth);
        this.mGoalLineColor = typedArrayObtainStyledAttributes.getColor(22, this.mGoalLineColor);
        this.mGoalLineWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(23, this.mGoalLineWidth);
        this.mGoalLineDistance = typedArrayObtainStyledAttributes.getDimensionPixelSize(21, this.mGoalLineDistance);
        this.mXCount = typedArrayObtainStyledAttributes.getInt(39, this.mXCount);
        this.mXLineVisul = typedArrayObtainStyledAttributes.getBoolean(44, this.mXLineVisul);
        this.mYLeftCount = typedArrayObtainStyledAttributes.getInt(49, this.mYLeftCount);
        this.mYRightCount = typedArrayObtainStyledAttributes.getInt(51, this.mYRightCount);
        this.mBottomLabelLineDistance = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, this.mBottomLabelLineDistance);
        this.mLeftLabelLineDistance = typedArrayObtainStyledAttributes.getDimensionPixelSize(26, this.mLeftLabelLineDistance);
        this.mYGridBottomLineDistance = typedArrayObtainStyledAttributes.getDimensionPixelSize(52, this.mYGridBottomLineDistance);
        this.mBottomLabelCenter = typedArrayObtainStyledAttributes.getBoolean(0, this.mBottomLabelCenter);
        this.mAutoXMaxinValue = typedArrayObtainStyledAttributes.getBoolean(46, this.mAutoXMaxinValue);
        this.mAutoYMaxinValue = typedArrayObtainStyledAttributes.getBoolean(50, this.mAutoYMaxinValue);
        this.mGoalXLabelTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(41, this.mGoalXLabelTextSize);
        this.mGoalXLabelTextColor = typedArrayObtainStyledAttributes.getColor(40, this.mGoalXLabelTextColor);
        this.mXGoalLabelLineDistance = typedArrayObtainStyledAttributes.getDimensionPixelSize(42, this.mXGoalLabelLineDistance);
        this.mBottomTitle = typedArrayObtainStyledAttributes.getString(7);
        this.mBottomTitleColor = typedArrayObtainStyledAttributes.getColor(8, this.mBottomTitleColor);
        this.mBottomTitleSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(9, this.mBottomTitleSize);
        this.mBottomTitleToYDistance = typedArrayObtainStyledAttributes.getDimensionPixelSize(10, this.mBottomTitleToYDistance);
        this.mSupportSelectInMove = typedArrayObtainStyledAttributes.getBoolean(38, this.mSupportSelectInMove);
        this.mOxygenRectBgEnable = typedArrayObtainStyledAttributes.getBoolean(34, this.mOxygenRectBgEnable);
        this.mOxygenRectBgColor = typedArrayObtainStyledAttributes.getColor(33, this.mOxygenRectBgColor);
        if (!this.mBottomLineEnabled) {
            this.mBottomLineHeight = 0;
        }
        int i = typedArrayObtainStyledAttributes.getInt(53, 2);
        int i2 = typedArrayObtainStyledAttributes.getInt(54, 2);
        if (i == 1) {
            this.mLeftLabelAlign = Paint.Align.LEFT;
        } else if (i == 2) {
            this.mLeftLabelAlign = Paint.Align.RIGHT;
        } else {
            this.mLeftLabelAlign = Paint.Align.CENTER;
        }
        if (i2 == 1) {
            this.mRightLabelAlign = Paint.Align.LEFT;
        } else if (i2 == 2) {
            this.mRightLabelAlign = Paint.Align.RIGHT;
        } else if (i2 == 3) {
            this.mRightLabelAlign = Paint.Align.CENTER;
        }
        int i3 = typedArrayObtainStyledAttributes.getInt(20, 1);
        typedArrayObtainStyledAttributes.recycle();
        if (i3 == 1) {
            this.mCircleStyle = Paint.Style.FILL;
        } else if (i3 == 2) {
            this.mCircleStyle = Paint.Style.STROKE;
        } else if (i3 == 3) {
            this.mCircleStyle = Paint.Style.FILL_AND_STROKE;
        }
        this.mPaint = new Paint();
        setLayerType(1, null);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBottomLabel(canvas);
        drawBottomLine(canvas);
        drawLeftLabel(canvas);
        drawLeftLine(canvas);
        drawRightLabel(canvas);
        drawRightLine(canvas);
        drawXGridLine(canvas);
        drawYGridLine(canvas);
        drawChat(canvas);
        drawGoalLabelLine(canvas);
        drawTipView(canvas);
    }

    protected void drawRightLabel(Canvas canvas) {
        List<String> list;
        if (!this.mLabelYRightLableEnabled || (list = this.mLabelYRightList) == null || list.size() == 0 || this.mYRightCount != this.mLabelYRightList.size()) {
            return;
        }
        this.mPaint.reset();
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setTextSize(this.mRightLabelSize);
        Rect rect = new Rect();
        this.mPaint.getTextBounds("AA", 0, 2, rect);
        int iHeight = rect.height();
        this.mPaint.reset();
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(this.mRightLabelColor);
        this.mPaint.setTextSize(this.mRightLabelSize);
        float height = getHeight() - measureBottomLineToBottomDistance();
        if (this.mDrawXGridLine) {
            height -= this.mYGridBottomLineDistance;
        }
        int size = this.mLabelYRightList.size();
        Rect rect2 = new Rect();
        if (size == 1) {
            String str = this.mLabelYRightList.get(0);
            this.mPaint.getTextBounds(str, 0, str.length(), rect2);
            int i = AnonymousClass1.$SwitchMap$android$graphics$Paint$Align[this.mRightLabelAlign.ordinal()];
            if (i == 1) {
                canvas.drawText(str, 0.0f, (height / 2.0f) + (rect2.height() / 2), this.mPaint);
                return;
            } else if (i == 2) {
                canvas.drawText(str, measureRightLabelMaxWidth() - rect2.width(), (height / 2.0f) + (rect2.height() / 2), this.mPaint);
                return;
            } else {
                if (i != 3) {
                    return;
                }
                canvas.drawText(str, (measureRightLabelMaxWidth() / 2) - (rect2.width() / 2), (height / 2.0f) + (rect2.height() / 2), this.mPaint);
                return;
            }
        }
        float f2 = (float) (((double) (height - (iHeight / 2))) * 1.0d);
        if (this.mYRightCount > 1) {
            f2 /= r9 - 1;
        }
        for (int i2 = 0; i2 < size; i2++) {
            String str2 = this.mLabelYRightList.get(i2);
            this.mPaint.getTextBounds(str2, 0, str2.length(), rect2);
            int i3 = AnonymousClass1.$SwitchMap$android$graphics$Paint$Align[this.mRightLabelAlign.ordinal()];
            if (i3 == 1) {
                canvas.drawText(str2, 0.0f, (height - (i2 * f2)) + rect2.height(), this.mPaint);
            } else if (i3 == 2) {
                canvas.drawText(str2, getWidth() - measureRightLabelMaxWidth(), (height - (i2 * f2)) + rect2.height(), this.mPaint);
            } else if (i3 == 3) {
                canvas.drawText(str2, (measureRightLabelMaxWidth() / 2) - (rect2.width() / 2), (height - (i2 * f2)) + rect2.height(), this.mPaint);
            }
        }
    }

    protected void drawBottomLine(Canvas canvas) {
        if (this.mBottomLineEnabled) {
            this.mPaint.reset();
            this.mPaint.setAntiAlias(true);
            this.mPaint.setColor(this.mBottomLineColor);
            this.mPaint.setStrokeWidth(this.mBottomLineHeight);
            this.mPaint.setStyle(Paint.Style.STROKE);
            float height = getHeight() - measureBottomLineToBottomDistance();
            canvas.drawLine(measureLeftLineToLeftDistance(), height, getWidth(), height, this.mPaint);
        }
    }

    protected void drawBottomLabel(Canvas canvas) {
        List<String> list;
        float width;
        if (!this.mBottomLabelEnabled || (list = this.mLabelXList) == null || this.mXCount != list.size() || this.mXCount == 0) {
            return;
        }
        this.mPaint.reset();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(this.mBottomLabelColor);
        this.mPaint.setTextSize(this.mBottomLabelSize);
        float fMeasureLeftLineToLeftDistance = measureLeftLineToLeftDistance();
        int size = this.mLabelXList.size();
        Rect rect = new Rect();
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(com.boat.Xtend.two.R.dimen.sw_dp_2);
        if (this.mXCount == 1) {
            String str = this.mLabelXList.get(0);
            this.mPaint.getTextBounds(str, 0, str.length(), rect);
            canvas.drawText(str, (((getWidth() - fMeasureLeftLineToLeftDistance) / 2.0f) + fMeasureLeftLineToLeftDistance) - (rect.width() / 2), (getHeight() - getPaddingBottom()) - dimensionPixelSize, this.mPaint);
        } else {
            if (this.mBottomLabelCenter) {
                width = (getWidth() - fMeasureLeftLineToLeftDistance) / this.mXCount;
            } else {
                width = (getWidth() - fMeasureLeftLineToLeftDistance) / (this.mXCount - 1);
            }
            for (int i = 0; i < size; i++) {
                String str2 = this.mLabelXList.get(i);
                this.mPaint.getTextBounds(str2, 0, str2.length(), rect);
                if (this.mBottomLabelCenter) {
                    canvas.drawText(str2, (((i + 0.5f) * width) + fMeasureLeftLineToLeftDistance) - (rect.width() / 2), (getHeight() - getPaddingBottom()) - dimensionPixelSize, this.mPaint);
                } else if (i == 0) {
                    canvas.drawText(str2, fMeasureLeftLineToLeftDistance, (getHeight() - getPaddingBottom()) - dimensionPixelSize, this.mPaint);
                } else if (i == size - 1) {
                    canvas.drawText(str2, getWidth() - rect.width(), (getHeight() - getPaddingBottom()) - dimensionPixelSize, this.mPaint);
                } else {
                    canvas.drawText(str2, (fMeasureLeftLineToLeftDistance - (rect.width() / 2)) + (i * width), (getHeight() - getPaddingBottom()) - dimensionPixelSize, this.mPaint);
                }
            }
        }
        if (TextUtils.isEmpty(this.mBottomTitle)) {
            return;
        }
        Rect rectMeasureBottomLableTitleRect = measureBottomLableTitleRect();
        int i2 = this.mBottomTitleSize;
        if (i2 > 0) {
            this.mPaint.setTextSize(i2);
        }
        int i3 = this.mBottomTitleColor;
        if (i3 != -1) {
            this.mPaint.setColor(i3);
        }
        canvas.drawText(this.mBottomTitle, (fMeasureLeftLineToLeftDistance - rectMeasureBottomLableTitleRect.width()) - this.mBottomTitleToYDistance, (getHeight() - getPaddingBottom()) - dimensionPixelSize, this.mPaint);
    }

    protected void drawLeftLine(Canvas canvas) {
        if (this.mLeftLineEnabled) {
            this.mPaint.reset();
            this.mPaint.setAntiAlias(true);
            this.mPaint.setColor(this.mLeftLineColor);
            this.mPaint.setStrokeWidth(this.mLeftLineWidth);
            this.mPaint.setStyle(Paint.Style.STROKE);
            float height = getHeight() - measureBottomLineToBottomDistance();
            float fMeasureLeftLineToLeftDistance = measureLeftLineToLeftDistance() - this.mLeftLineWidth;
            canvas.drawLine(fMeasureLeftLineToLeftDistance, height, fMeasureLeftLineToLeftDistance, getHeight(), this.mPaint);
        }
    }

    protected void drawLeftLabel(Canvas canvas) {
        if (this.mXGridLineCallback == null) {
            drawLeftLabelPer(canvas);
        } else {
            drawLeftLabelCustom(canvas);
        }
    }

    protected void drawLeftLabelPer(Canvas canvas) {
        List<String> list;
        float f2;
        if (!this.mLeftLabelEnabled || (list = this.mLabelYLeftList) == null || list.size() == 0 || this.mYLeftCount != this.mLabelYLeftList.size()) {
            return;
        }
        this.mPaint.reset();
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setTextSize(this.mLeftLabelSize);
        Rect rect = new Rect();
        int i = 0;
        this.mPaint.getTextBounds("AA", 0, 2, rect);
        int iHeight = rect.height();
        this.mPaint.reset();
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(this.mLeftLabelColor);
        this.mPaint.setTextSize(this.mLeftLabelSize);
        float height = getHeight() - measureBottomLineToBottomDistance();
        if (this.mDrawXGridLine) {
            height -= this.mYGridBottomLineDistance;
        }
        int size = this.mLabelYLeftList.size();
        Rect rect2 = new Rect();
        if (size == 1) {
            String str = this.mLabelYLeftList.get(0);
            this.mPaint.getTextBounds(str, 0, str.length(), rect2);
            int i2 = AnonymousClass1.$SwitchMap$android$graphics$Paint$Align[this.mLeftLabelAlign.ordinal()];
            if (i2 == 1) {
                canvas.drawText(str, 0.0f, (height / 2.0f) + (rect2.height() / 2), this.mPaint);
                return;
            } else if (i2 == 2) {
                canvas.drawText(str, measureLeftLabelMaxWidth() - this.mPaint.measureText(str), (height / 2.0f) + (rect2.height() / 2), this.mPaint);
                return;
            } else {
                if (i2 != 3) {
                    return;
                }
                canvas.drawText(str, (measureLeftLabelMaxWidth() / 2.0f) - (this.mPaint.measureText(str) / 2.0f), (height / 2.0f) + (rect2.height() / 2), this.mPaint);
                return;
            }
        }
        if (size > 1) {
            f2 = (float) ((((double) (height - (iHeight / 2))) * 1.0d) / ((double) (size - 1)));
        } else {
            f2 = height - (iHeight / 2);
        }
        int i3 = 0;
        while (i3 < size) {
            String str2 = this.mLabelYLeftList.get(i3);
            this.mPaint.getTextBounds(str2, i, str2.length(), rect2);
            int i4 = AnonymousClass1.$SwitchMap$android$graphics$Paint$Align[this.mLeftLabelAlign.ordinal()];
            if (i4 == 1) {
                canvas.drawText(str2, 0.0f, (height - (i3 * f2)) + (rect2.height() / 2), this.mPaint);
            } else if (i4 == 2) {
                canvas.drawText(str2, measureLeftLabelMaxWidth() - rect2.width(), (height - (i3 * f2)) + (rect2.height() / 2), this.mPaint);
            } else if (i4 == 3) {
                canvas.drawText(str2, (measureLeftLabelMaxWidth() / 2.0f) - (rect2.width() / 2), (height - (i3 * f2)) + (rect2.height() / 2), this.mPaint);
            }
            i3++;
            i = 0;
        }
    }

    private void drawLeftLabelCustom(Canvas canvas) {
        List<String> list;
        float f2;
        if (!this.mLeftLabelEnabled || (list = this.mLabelYLeftList) == null || list.size() == 0 || this.mYLeftCount != this.mLabelYLeftList.size()) {
            return;
        }
        this.mPaint.reset();
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setTextSize(this.mLeftLabelSize);
        Rect rect = new Rect();
        int i = 0;
        this.mPaint.getTextBounds("AA", 0, 2, rect);
        int iHeight = rect.height();
        this.mPaint.reset();
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(this.mLeftLabelColor);
        this.mPaint.setTextSize(this.mLeftLabelSize);
        float height = getHeight() - measureBottomLineToBottomDistance();
        if (this.mDrawXGridLine) {
            height -= this.mYGridBottomLineDistance;
        }
        int size = this.mLabelYLeftList.size();
        Rect rect2 = new Rect();
        if (size == 1) {
            String str = this.mLabelYLeftList.get(0);
            this.mPaint.getTextBounds(str, 0, str.length(), rect2);
            int i2 = AnonymousClass1.$SwitchMap$android$graphics$Paint$Align[this.mLeftLabelAlign.ordinal()];
            if (i2 == 1) {
                canvas.drawText(str, 0.0f, (height / 2.0f) + (rect2.height() / 2), this.mPaint);
                return;
            } else if (i2 == 2) {
                canvas.drawText(str, measureLeftLabelMaxWidth() - this.mPaint.measureText(str), (height / 2.0f) + (rect2.height() / 2), this.mPaint);
                return;
            } else {
                if (i2 != 3) {
                    return;
                }
                canvas.drawText(str, (measureLeftLabelMaxWidth() / 2.0f) - (this.mPaint.measureText(str) / 2.0f), (height / 2.0f) + (rect2.height() / 2), this.mPaint);
                return;
            }
        }
        float f3 = this.mYMaxValue;
        float f4 = this.mYMinValue;
        if (f3 - f4 > 0.0f) {
            f2 = (float) ((((double) (height - (iHeight / 2))) * 1.0d) / ((double) (f3 - f4)));
        } else {
            f2 = height - (iHeight / 2);
        }
        int i3 = 0;
        while (i3 < size) {
            String str2 = this.mLabelYLeftList.get(i3);
            this.mPaint.getTextBounds(str2, i, str2.length(), rect2);
            int i4 = AnonymousClass1.$SwitchMap$android$graphics$Paint$Align[this.mLeftLabelAlign.ordinal()];
            if (i4 == 1) {
                canvas.drawText(str2, 0.0f, (height - ((this.mXGridLineCallback.calculateXGridLineValue(this, i3) - this.mYMinValue) * f2)) + (rect2.height() / 2), this.mPaint);
            } else if (i4 == 2) {
                canvas.drawText(str2, measureLeftLabelMaxWidth() - rect2.width(), (height - ((this.mXGridLineCallback.calculateXGridLineValue(this, i3) - this.mYMinValue) * f2)) + (rect2.height() / 2), this.mPaint);
            } else if (i4 == 3) {
                canvas.drawText(str2, (measureLeftLabelMaxWidth() / 2.0f) - (rect2.width() / 2), (height - ((this.mXGridLineCallback.calculateXGridLineValue(this, i3) - this.mYMinValue) * f2)) + (rect2.height() / 2), this.mPaint);
            }
            i3++;
            i = 0;
        }
    }

    protected void drawXGridLine(Canvas canvas) {
        if (this.mXGridLineCallback == null) {
            drawXGridLineByPer(canvas);
        } else {
            drawXGridLineByUnPer(canvas);
        }
    }

    protected void drawXGridLineByPer(Canvas canvas) {
        if (this.mDrawXGridLine) {
            if (this.mYLeftCount >= 1 || this.mYRightCount >= 1) {
                this.mPaint.reset();
                this.mPaint.setTextSize(this.mLeftLabelSize);
                this.mPaint.setStyle(Paint.Style.FILL);
                Rect rect = new Rect();
                this.mPaint.getTextBounds("AA", 0, 2, rect);
                int iMeasureBottomLineToBottomDistance = measureBottomLineToBottomDistance();
                float height = ((getHeight() - iMeasureBottomLineToBottomDistance) - this.mYGridBottomLineDistance) - (rect.height() / 2);
                int iMax = Math.max(this.mYLeftCount, this.mYRightCount);
                if (iMax > 1) {
                    height = (float) ((((double) height) * 1.0d) / ((double) (iMax - 1)));
                }
                this.mPaint.reset();
                this.mPaint.setAntiAlias(true);
                this.mPaint.setStyle(Paint.Style.STROKE);
                if (this.mXLineVisul) {
                    this.mPaint.setPathEffect(new DashPathEffect(new float[]{15.0f, 5.0f}, 5.0f));
                } else {
                    this.mPaint.setPathEffect(null);
                }
                this.mPaint.setColor(this.mGridXColor);
                this.mPaint.setStrokeWidth(this.mGridXHeight);
                float fMeasureLeftLineToLeftDistance = measureLeftLineToLeftDistance();
                float height2 = (getHeight() - iMeasureBottomLineToBottomDistance) - this.mYGridBottomLineDistance;
                for (int i = 0; i < iMax; i++) {
                    float f2 = height2 - (i * height);
                    canvas.drawLine(fMeasureLeftLineToLeftDistance, f2, getWidth(), f2, this.mPaint);
                }
            }
        }
    }

    protected void drawXGridLineByUnPer(Canvas canvas) {
        List<String> list;
        if (this.mDrawXGridLine) {
            if ((this.mYLeftCount >= 1 || this.mYRightCount >= 1) && (list = this.mLabelYLeftList) != null && this.mYLeftCount == list.size()) {
                this.mPaint.reset();
                this.mPaint.setTextSize(this.mLeftLabelSize);
                this.mPaint.setStyle(Paint.Style.FILL);
                Rect rect = new Rect();
                this.mPaint.getTextBounds("AA", 0, 2, rect);
                int iMeasureBottomLineToBottomDistance = measureBottomLineToBottomDistance();
                float height = ((getHeight() - iMeasureBottomLineToBottomDistance) - this.mYGridBottomLineDistance) - (rect.height() / 2);
                float f2 = this.mYMaxValue;
                float f3 = this.mYMinValue;
                if (f2 - f3 > 1.0f) {
                    height = (float) ((((double) height) * 1.0d) / ((double) (f2 - f3)));
                }
                this.mPaint.reset();
                this.mPaint.setAntiAlias(true);
                this.mPaint.setStyle(Paint.Style.STROKE);
                if (this.mXLineVisul) {
                    this.mPaint.setPathEffect(new DashPathEffect(new float[]{15.0f, 5.0f}, 5.0f));
                } else {
                    this.mPaint.setPathEffect(null);
                }
                this.mPaint.setColor(this.mGridXColor);
                this.mPaint.setStrokeWidth(this.mGridXHeight);
                float fMeasureLeftLineToLeftDistance = measureLeftLineToLeftDistance();
                float height2 = (getHeight() - iMeasureBottomLineToBottomDistance) - this.mYGridBottomLineDistance;
                int iMax = Math.max(this.mYLeftCount, this.mYRightCount);
                for (int i = 0; i < iMax; i++) {
                    float fCalculateXGridLineValue = height2 - ((this.mXGridLineCallback.calculateXGridLineValue(this, i) - this.mYMinValue) * height);
                    canvas.drawLine(fMeasureLeftLineToLeftDistance, fCalculateXGridLineValue, getWidth(), fCalculateXGridLineValue, this.mPaint);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01b3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void drawGoalLabelLine(android.graphics.Canvas r20) {
        /*
            Method dump skipped, instruction units count: 503
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.customview.charter.CustomChatBar.drawGoalLabelLine(android.graphics.Canvas):void");
    }

    protected void drawChat(Canvas canvas) {
        caluteCirclePosition();
    }

    protected void calculateGoalLinePosition() {
        float f2;
        double d2;
        int i;
        List<GoalLineInfo> list = this.mGoalLineList;
        if (list == null || list.size() <= 0) {
            return;
        }
        adjustLabelMaxineValue();
        float fMeasureLeftLineToLeftDistance = measureLeftLineToLeftDistance();
        this.mPaint.reset();
        this.mPaint.setTextSize(this.mLeftLabelSize);
        this.mPaint.setStyle(Paint.Style.FILL);
        Rect rect = new Rect();
        this.mPaint.getTextBounds("AA", 0, 2, rect);
        int height = (getHeight() - measureBottomLineToBottomDistance()) - (rect.height() / 2);
        float height2 = (getHeight() - measureBottomLineToBottomDistance()) - this.mGoalLineWidth;
        if (this.mDrawXGridLine) {
            height -= this.mGridXHeight + this.mYGridBottomLineDistance;
            height2 -= r3 + r5;
        }
        float width = getWidth() - fMeasureLeftLineToLeftDistance;
        int circleSize = getCircleSize();
        if (!this.mBottomLabelCenter) {
            fMeasureLeftLineToLeftDistance += circleSize;
            width -= circleSize * 2;
        }
        int i2 = this.mXMaxValue;
        int i3 = this.mXMinValue;
        if (i2 - i3 > 0) {
            if (this.mBottomLabelCenter) {
                d2 = ((double) width) * 1.0d;
                i = (i2 - i3) + 1;
            } else {
                d2 = ((double) width) * 1.0d;
                i = i2 - i3;
            }
            f2 = (float) (d2 / ((double) i));
        } else {
            f2 = 0.0f;
        }
        float f3 = this.mYMaxValue;
        float f4 = this.mYMinValue;
        float f5 = f3 - f4 > 0.0f ? (float) ((((double) height) * 1.0d) / ((double) (f3 - f4))) : 0.0f;
        for (GoalLineInfo goalLineInfo : this.mGoalLineList) {
            if (goalLineInfo.getLineOrientation() == 0) {
                goalLineInfo.setPosition(((this.mGoalLineWidth / 2) + height2) - ((goalLineInfo.getValue() - this.mYMinValue) * f5));
            } else {
                goalLineInfo.setPosition(((fMeasureLeftLineToLeftDistance - (this.mGoalLineWidth / 2)) + ((goalLineInfo.getValue() - this.mYMinValue) * f2)) - (this.mGoalLineWidth / 2));
            }
        }
    }

    /* JADX INFO: renamed from: com.ido.life.customview.charter.CustomChatBar$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$graphics$Paint$Align;
        static final /* synthetic */ int[] $SwitchMap$android$graphics$Paint$Style = new int[Paint.Style.values().length];

        static {
            try {
                $SwitchMap$android$graphics$Paint$Style[Paint.Style.STROKE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$graphics$Paint$Style[Paint.Style.FILL_AND_STROKE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $SwitchMap$android$graphics$Paint$Align = new int[Paint.Align.values().length];
            try {
                $SwitchMap$android$graphics$Paint$Align[Paint.Align.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$graphics$Paint$Align[Paint.Align.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$android$graphics$Paint$Align[Paint.Align.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public int getCircleSize() {
        int i = AnonymousClass1.$SwitchMap$android$graphics$Paint$Style[this.mCircleStyle.ordinal()];
        if (i == 1 || i == 2) {
            return this.mCircleBorderWidth + this.mCircleRadius;
        }
        return this.mCircleRadius;
    }

    protected float measureLeftLineToLeftDistance() {
        List<String> list;
        float fMax = 0.0f;
        float f2 = this.mLeftLineEnabled ? this.mLeftLineWidth + 0.0f : 0.0f;
        if (this.mLeftLabelEnabled && (list = this.mLabelYLeftList) != null && list.size() > 0) {
            fMax = measureLeftLabelMaxWidth() + this.mLeftLabelLineDistance;
        }
        if (measureBottomLableTitleRect() != null) {
            fMax = Math.max(fMax, r2.width() + this.mBottomTitleToYDistance);
        }
        return f2 + fMax;
    }

    protected Rect measureBottomLableTitleRect() {
        if (TextUtils.isEmpty(this.mBottomTitle)) {
            return null;
        }
        Rect rect = new Rect();
        Paint paint = new Paint();
        int i = this.mBottomTitleSize;
        if (i > 0) {
            paint.setTextSize(i);
        } else {
            paint.setTextSize(this.mBottomLabelSize);
        }
        String str = this.mBottomTitle;
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect;
    }

    protected int measureRightLineToLeftDistance() {
        List<String> list;
        int i = this.mLeftLineEnabled ? 0 + this.mLeftLineWidth : 0;
        return (!this.mLabelYRightLableEnabled || (list = this.mLabelYRightList) == null || list.size() <= 0) ? i : i + this.mLeftLabelLineDistance + measureRightLabelMaxWidth();
    }

    protected float measureLeftLabelMaxWidth() {
        List<String> list;
        float fMax = 0.0f;
        if (this.mLeftLabelEnabled && (list = this.mLabelYLeftList) != null && list.size() > 0) {
            Paint paint = new Paint();
            paint.setTextSize(this.mLeftLabelSize);
            Iterator<String> it = this.mLabelYLeftList.iterator();
            while (it.hasNext()) {
                fMax = Math.max(fMax, paint.measureText(it.next()));
            }
        }
        return fMax;
    }

    protected int measureRightLabelMaxWidth() {
        int iMax = 0;
        if (this.mLabelYRightLableEnabled && this.mLabelYRightList.size() > 0) {
            Paint paint = new Paint();
            paint.setTextSize(this.mRightLabelSize);
            Iterator<String> it = this.mLabelYRightList.iterator();
            while (it.hasNext()) {
                iMax = (int) Math.max(iMax, paint.measureText(it.next()));
            }
        }
        return iMax;
    }

    private Pair<Float, Float> getFontInfo(int i, String str) {
        Paint paint = new Paint();
        paint.setTextSize(i);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return new Pair<>(Float.valueOf(paint.measureText(str)), Float.valueOf(Math.abs(fontMetrics.ascent) + Math.abs(fontMetrics.descent)));
    }

    protected int measureBottomLineToBottomDistance() {
        List<String> list;
        int iHeight = this.mBottomLineEnabled ? this.mBottomLineHeight + 0 : 0;
        if (this.mBottomLabelEnabled && (list = this.mLabelXList) != null && list.size() > 0) {
            int i = iHeight + this.mBottomLabelLineDistance;
            Paint paint = new Paint();
            paint.setTextSize(this.mBottomLabelSize);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL);
            Rect rect = new Rect();
            paint.getTextBounds("AA", 0, 2, rect);
            iHeight = i + rect.height();
        }
        return iHeight + getPaddingBottom();
    }

    protected void adjustLabelMaxineValue() {
        List<T> list;
        if ((!isAutoXMaxinValue() && !isAutoYMaxinValue()) || (list = this.mList) == null || list.size() == 0) {
            return;
        }
        int iMax = Integer.MIN_VALUE;
        int iMin = Integer.MAX_VALUE;
        int iMax2 = Integer.MIN_VALUE;
        int iMin2 = Integer.MAX_VALUE;
        for (T t : this.mList) {
            iMin = (int) Math.min(iMin, ((PointF) t).x);
            iMax = (int) Math.max(iMax, ((PointF) t).x);
            iMin2 = (int) Math.min(iMin2, ((PointF) t).y);
            iMax2 = (int) Math.max(iMax2, ((PointF) t).y);
        }
        if (this.mAutoXMaxinValue) {
            this.mXMinValue = iMin;
            this.mXMaxValue = iMax;
        }
        if (this.mAutoYMaxinValue) {
            this.mYMinValue = iMin2;
            this.mYMaxValue = iMax2;
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode != 1073741824) {
            if (size >= 0) {
                size = Math.min(this.mScreenWidth, size);
            } else {
                size = this.mScreenWidth;
            }
        }
        if (mode2 != 1073741824) {
            size2 = measureChartHeight();
        }
        setMeasuredDimension(size, size2);
    }

    private int measureChartHeight() {
        List<String> list;
        int iHeight = 0;
        if (this.mBottomLabelEnabled && (list = this.mLabelXList) != null && list.size() > 0) {
            new Paint().setTextSize(this.mBottomLabelSize);
            Rect rect = new Rect();
            this.mPaint.getTextBounds("AA", 0, 2, rect);
            iHeight = 0 + rect.height();
        }
        if (this.mBottomLabelEnabled) {
            iHeight += this.mBottomLabelLineDistance;
        }
        if (this.mBottomLineEnabled) {
            iHeight += this.mBottomLineHeight;
        }
        return iHeight + ((this.mLineWidth + 30) * Math.max(this.mYLeftCount, this.mYRightCount));
    }

    public void setList(List<T> list) {
        synchronized (this) {
            if (this.mList == null) {
                this.mList = Collections.synchronizedList(new ArrayList());
            } else {
                this.mList.clear();
            }
            if (this.mCircleRegion != null && this.mCircleRegion.size() > 0) {
                this.mCircleRegion.clear();
            }
            if (list != null && list.size() > 0) {
                this.mList.addAll(list);
            }
        }
    }

    public void setXMaxValue(int i) {
        this.mXMaxValue = i;
    }

    public void setXMinValue(int i) {
        this.mXMinValue = i;
    }

    public void setYMaxValue(float f2) {
        this.mYMaxValue = f2;
    }

    public void setYMinValue(float f2) {
        this.mYMinValue = f2;
    }

    public void setCircleRadius(int i) {
        this.mCircleRadius = i;
    }

    public void setLabelXList(List<String> list) {
        this.mLabelXList = list;
        if (list != null && list.size() > 0) {
            this.mXCount = list.size();
        } else {
            this.mXCount = 0;
        }
    }

    public void setLabelYLeftList(List<String> list) {
        this.mLabelYLeftList = list;
        if (list != null && list.size() > 0) {
            this.mYLeftCount = this.mLabelYLeftList.size();
        } else {
            this.mYLeftCount = 0;
        }
    }

    public List<String> getLabelYLeftList() {
        return this.mLabelYLeftList;
    }

    public void setLabelYRightList(List<String> list) {
        this.mLabelYRightList = list;
        if (list != null && list.size() > 0) {
            this.mYRightCount = this.mLabelYRightList.size();
        } else {
            this.mYRightCount = 0;
        }
    }

    public void setBottomLabelCenter(boolean z) {
        this.mBottomLabelCenter = z;
    }

    public void setClickListener(ChartClickListener chartClickListener) {
        this.mClickListener = chartClickListener;
        if (this.mClickListener != null) {
            setOnTouchListener(new View.OnTouchListener() { // from class: com.ido.life.customview.charter.-$$Lambda$gZMJWduJrBL2XKIA0rQIAz-NDqI
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return this.f$0.onTouch(view, motionEvent);
                }
            });
        }
    }

    public float getChartBottom() {
        float fMeasureBottomLineToBottomDistance = measureBottomLineToBottomDistance();
        if (this.mDrawXGridLine) {
            fMeasureBottomLineToBottomDistance += this.mYGridBottomLineDistance;
        }
        return (getHeight() - fMeasureBottomLineToBottomDistance) - (getLabelYMaxLabelHeight() / 2.0f);
    }

    public float getBottomHeight() {
        float fMeasureBottomLineToBottomDistance = measureBottomLineToBottomDistance();
        return this.mDrawXGridLine ? fMeasureBottomLineToBottomDistance + this.mYGridBottomLineDistance : fMeasureBottomLineToBottomDistance;
    }

    public float getChartHeight() {
        float fMeasureBottomLineToBottomDistance = measureBottomLineToBottomDistance();
        if (this.mDrawXGridLine) {
            fMeasureBottomLineToBottomDistance += this.mYGridBottomLineDistance;
        }
        return (getHeight() - fMeasureBottomLineToBottomDistance) - getLabelYMaxLabelHeight();
    }

    private float getLabelYMaxLabelHeight() {
        List<String> list;
        List<String> list2 = this.mLabelYLeftList;
        if ((list2 == null || list2.size() == 0) && ((list = this.mLabelYRightList) == null || list.size() == 0)) {
            return 0.0f;
        }
        this.mPaint.setTextSize(Math.max(this.mLeftLabelSize, this.mRightLabelSize));
        Paint.FontMetrics fontMetrics = this.mPaint.getFontMetrics();
        return Math.abs(fontMetrics.top) + Math.abs(fontMetrics.bottom);
    }

    public Paint getPaint() {
        return this.mPaint;
    }

    public List<RectF> getCircleRegion() {
        return this.mCircleRegion;
    }

    public List<T> getList() {
        return this.mList;
    }

    protected boolean canTouch() {
        List<RectF> list;
        ValueAnimator valueAnimator = this.mAnimator;
        return ((valueAnimator != null && (valueAnimator.isRunning() || this.mAnimator.isStarted())) || (list = this.mCircleRegion) == null || list.size() == 0 || this.mClickListener == null) ? false : true;
    }

    private boolean touchValidPoint(float f2, float f3) {
        CommonLogUtil.d(TAG, "x=" + f2 + ",y=" + f3 + ",width=" + getWidth() + ",height=" + getHeight());
        return f2 < ((float) getWidth()) && f3 < ((float) getHeight());
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x009d  */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouch(android.view.View r5, android.view.MotionEvent r6) {
        /*
            Method dump skipped, instruction units count: 244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.customview.charter.CustomChatBar.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    private void setParentScrollState(boolean z) {
        ViewParent parent = getParent();
        while (parent instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) parent;
            if (viewGroup instanceof CustomChartDetailViewPager) {
                ((CustomChartDetailViewPager) viewGroup).setScrollAble(z);
                return;
            }
            parent = viewGroup.getParent();
        }
    }

    public int queryNearestTouchIndex(int i) {
        List<RectF> list = this.mCircleRegion;
        int i2 = -1;
        if (list != null && list.size() > 0) {
            int size = this.mCircleRegion.size();
            float fAbs = Float.MAX_VALUE;
            for (int i3 = 0; i3 < size; i3++) {
                RectF rectF = this.mCircleRegion.get(i3);
                float f2 = i;
                if (Math.abs(f2 - rectF.left) + Math.abs(f2 - rectF.right) < fAbs) {
                    fAbs = Math.abs(f2 - rectF.left) + Math.abs(f2 - rectF.right);
                    i2 = i3;
                }
            }
        }
        return i2;
    }

    public int queryDataIndex(int i) {
        List<RectF> list = this.mCircleRegion;
        if (list != null && list.size() > 0) {
            int size = this.mCircleRegion.size();
            for (int i2 = 0; i2 < size; i2++) {
                RectF rectF = this.mCircleRegion.get(i2);
                float f2 = i;
                if (f2 >= rectF.left && f2 <= rectF.right) {
                    return i2;
                }
            }
        }
        return -2;
    }

    public void refreshChart(boolean z) {
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator != null && (valueAnimator.isRunning() || this.mAnimator.isStarted())) {
            this.mAnimator.cancel();
        }
        if (z) {
            this.mAnimator = getAnimator();
            this.mAnimator.start();
        } else {
            invalidate();
        }
    }

    private ValueAnimator getAnimator() {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(1, 100);
        valueAnimator.setDuration(this.mAnimatorDuration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.life.customview.charter.-$$Lambda$CustomChatBar$FKRJXdlHNDvV7lyZ8xvfdhobLXc
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                this.f$0.lambda$getAnimator$0$CustomChatBar(valueAnimator2);
            }
        });
        return valueAnimator;
    }

    public /* synthetic */ void lambda$getAnimator$0$CustomChatBar(ValueAnimator valueAnimator) {
        this.mAnimatorRadius = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        invalidate();
    }

    public void setLineColor(int i) {
        this.mLineColor = i;
    }

    public void setCircleBorderWidth(int i) {
        this.mCircleBorderWidth = i;
    }

    public void setCircleStrokeColor(int i) {
        this.mCircleStrokeColor = i;
    }

    public void setCircleStrokeSelectedColor(int i) {
        this.mCircleStrokeSelectedColor = i;
    }

    public void setCircleColor(int i) {
        this.mCircleColor = i;
    }

    public void setCircleSelectedColor(int i) {
        this.mCircleSelectedColor = i;
    }

    public boolean isAutoYMaxinValue() {
        return this.mAutoYMaxinValue;
    }

    public void setAutoYMaxinValue(boolean z) {
        this.mAutoYMaxinValue = z;
    }

    public boolean isAutoXMaxinValue() {
        return this.mAutoXMaxinValue;
    }

    public void setAutoXMaxinValue(boolean z) {
        this.mAutoXMaxinValue = z;
    }

    public void setGoalLineList(List<GoalLineInfo> list) {
        this.mGoalLineList.clear();
        if (list == null || list.size() <= 0) {
            return;
        }
        this.mGoalLineList.addAll(list);
    }

    public void setGoalLineColor(int i) {
        this.mGoalLineColor = i;
    }

    public CaluteXGridLineCallback getXGridLineCallback() {
        return this.mXGridLineCallback;
    }

    public void setXGridLineCallback(CaluteXGridLineCallback caluteXGridLineCallback) {
        this.mXGridLineCallback = caluteXGridLineCallback;
    }

    public void setBottomTitle(String str) {
        this.mBottomTitle = str;
    }

    public void setBottomTitleColor(int i) {
        this.mBottomTitleColor = i;
    }

    public void clearList() {
        List<T> list = this.mList;
        if (list != null && list.size() > 0) {
            this.mList.clear();
        }
        List<RectF> list2 = this.mCircleRegion;
        if (list2 != null && list2.size() > 0) {
            this.mCircleRegion.clear();
        }
        List<GoalLineInfo> list3 = this.mGoalLineList;
        if (list3 == null || list3.size() <= 0) {
            return;
        }
        this.mGoalLineList.clear();
    }
}