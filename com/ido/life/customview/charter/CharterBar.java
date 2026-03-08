package com.ido.life.customview.charter;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.animation.Interpolator;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.MathHelper;
import com.ido.common.utils.NumUtil;
import com.ido.common.utils.ScreenUtil;
import com.ido.life.R;
import com.ido.life.bean.ValueRangePair;
import com.ido.life.util.DateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CharterBar extends CharterBase {
    private static int MIN_FLING_VELOCITY = 400;
    private static final String TAG = "CharterBar";
    private boolean autoIncrementDividerCount;
    private int barBackgroundColor;
    private int barBgColor;
    private float barDefaultHeight;
    private float barMargin;
    private float barMinHeightCorrection;
    private List<Region> barRegionList;
    private float barWeight;
    private float barWidth;
    private boolean bottomLabelAlignBar;
    private int bottomLabelHeight;
    private String[] bottomLabels;
    private float criticalHeight;
    private int dividerCount;
    private Date firstDateOfSelected;
    private boolean hasBottomLine;
    private boolean hasLeftLabel;
    private boolean hasTopLabel;
    int increaseCount;
    private boolean isAddClickZone;
    boolean isMoving;
    private boolean isRound;
    private boolean isShowUnit;
    private float labelAlpha;
    private int labelColor;
    private float labelSize;
    int lastXVelocity;
    private int leftLabelWidth;
    private int mBarColor;
    private int mBottomLineStyle;
    private boolean mCanClick;
    private int mDefaultBarColor;
    private Paint mDividerPaint;
    private int mMaximumVelocity;
    private Path mPath;
    private int mRealDividerCount;
    private Resources mResources;
    private int mTargetBarColor;
    private List<OnItemBarTouchListener> mTouchListenerList;
    private int mValuesCount;
    private VelocityTracker mVelocityTracker;
    private float marginWeight;
    private OnItemBarOnClickListener onClickListener;
    private Paint paintBar;
    private boolean paintBarBackground;
    private Paint paintLabel;
    private int rightLabelWidth;
    private RectF selRectF;
    private ValueRangePair selValue;
    private int selectedYear;
    private boolean topDividerVisible;
    private int topLabelHeight;
    private String unit;
    private int xLineColor;

    public interface OnItemBarOnClickListener {
        void onClick(int i);
    }

    public interface OnItemBarTouchListener {
        void onItemBarTouch(int i);
    }

    public void setColorsBackground(int i) {
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ long getAnimDuration() {
        return super.getAnimDuration();
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ int getCriticalValue() {
        return super.getCriticalValue();
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ int getDefaultMaxValue() {
        return super.getDefaultMaxValue();
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ int getGridLinesColor() {
        return super.getGridLinesColor();
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ int getGridLinesCount() {
        return super.getGridLinesCount();
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ float getGridLinesStrokeSize() {
        return super.getGridLinesStrokeSize();
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ int getIncreaseAmount() {
        return super.getIncreaseAmount();
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ float getMaxY() {
        return super.getMaxY();
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ float getMinY() {
        return super.getMinY();
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ Paint getPaintGrid() {
        return super.getPaintGrid();
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ int getSelected() {
        return super.getSelected();
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ int getTopSpace() {
        return super.getTopSpace();
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ float[] getValues() {
        return super.getValues();
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ boolean isAnim() {
        return super.isAnim();
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ boolean isShowGridLinesX() {
        return super.isShowGridLinesX();
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ boolean isShowGridLinesY() {
        return super.isShowGridLinesY();
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ void replayAnim(boolean z) {
        super.replayAnim(z);
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ void resetSelectedBar() {
        super.resetSelectedBar();
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ void resetValues() {
        super.resetValues();
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ void setAnim(boolean z) {
        super.setAnim(z);
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ void setAnimDuration(long j) {
        super.setAnimDuration(j);
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ void setAnimInterpolator(Interpolator interpolator) {
        super.setAnimInterpolator(interpolator);
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ void setAnimListener(CharterAnimListener charterAnimListener) {
        super.setAnimListener(charterAnimListener);
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ void setCriticalValue(int i) {
        super.setCriticalValue(i);
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ void setDefaultMaxValue(int i) {
        super.setDefaultMaxValue(i);
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ void setGridLinesColor(int i) {
        super.setGridLinesColor(i);
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ void setGridLinesCount(int i) {
        super.setGridLinesCount(i);
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ void setGridLinesStrokeSize(float f2) {
        super.setGridLinesStrokeSize(f2);
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ void setIncreaseAmount(int i) {
        super.setIncreaseAmount(i);
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ void setIsValuePair(boolean z) {
        super.setIsValuePair(z);
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ void setMaxY(int i) {
        super.setMaxY(i);
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ void setMinY(float f2) {
        super.setMinY(f2);
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ void setPaintGrid(Paint paint) {
        super.setPaintGrid(paint);
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ void setSelected(int i) {
        super.setSelected(i);
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ void setShowGridLines(boolean z) {
        super.setShowGridLines(z);
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ void setShowGridLinesX(boolean z) {
        super.setShowGridLinesX(z);
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ void setShowGridLinesY(boolean z) {
        super.setShowGridLinesY(z);
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ void setTopSpace(int i) {
        super.setTopSpace(i);
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ void setValues(List list) {
        super.setValues((List<ValueRangePair>) list);
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ void setValues(float[] fArr) {
        super.setValues(fArr);
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ void setValuesNoAnimator(List list) {
        super.setValuesNoAnimator((List<ValueRangePair>) list);
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ void setValuesNoAnimator(float[] fArr) {
        super.setValuesNoAnimator(fArr);
    }

    @Override // com.ido.life.customview.charter.CharterBase
    public /* bridge */ /* synthetic */ void show() {
        super.show();
    }

    public CharterBar(Context context) {
        this(context, null);
    }

    public CharterBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CharterBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.hasTopLabel = false;
        this.isShowUnit = true;
        this.isAddClickZone = false;
        this.mTargetBarColor = -47872;
        this.mCanClick = true;
        this.barWeight = 3.0f;
        this.marginWeight = 2.0f;
        this.topDividerVisible = true;
        this.mTouchListenerList = new ArrayList();
        init(context, attributeSet);
    }

    public CharterBar(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.hasTopLabel = false;
        this.isShowUnit = true;
        this.isAddClickZone = false;
        this.mTargetBarColor = -47872;
        this.mCanClick = true;
        this.barWeight = 3.0f;
        this.marginWeight = 2.0f;
        this.topDividerVisible = true;
        this.mTouchListenerList = new ArrayList();
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mVelocityTracker = VelocityTracker.obtain();
        MIN_FLING_VELOCITY = viewConfiguration.getScaledMinimumFlingVelocity() * 4;
        CommonLogUtil.d("MIN_FLING_VELOCITY=" + MIN_FLING_VELOCITY);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Charter);
        this.mResources = getResources();
        this.paintBarBackground = typedArrayObtainStyledAttributes.getBoolean(33, true);
        this.xLineColor = typedArrayObtainStyledAttributes.getColor(30, this.mResources.getColor(com.boat.Xtend.two.R.color.color_text_side_bar_55));
        this.mBarColor = typedArrayObtainStyledAttributes.getColor(4, this.mResources.getColor(com.boat.Xtend.two.R.color.white));
        this.barBgColor = typedArrayObtainStyledAttributes.getColor(3, this.mResources.getColor(com.boat.Xtend.two.R.color.translate));
        this.labelColor = typedArrayObtainStyledAttributes.getColor(10, this.mResources.getColor(com.boat.Xtend.two.R.color.white));
        this.labelAlpha = typedArrayObtainStyledAttributes.getFloat(9, 0.5f);
        this.labelSize = typedArrayObtainStyledAttributes.getDimension(11, this.mResources.getDimension(com.boat.Xtend.two.R.dimen.size10dp));
        this.barMargin = typedArrayObtainStyledAttributes.getDimension(5, this.mResources.getDimension(com.boat.Xtend.two.R.dimen.sw_dp_1));
        this.barWidth = typedArrayObtainStyledAttributes.getDimension(7, this.mResources.getDimension(com.boat.Xtend.two.R.dimen.sw_dp_3));
        this.barMinHeightCorrection = typedArrayObtainStyledAttributes.getDimension(6, this.mResources.getDimension(com.boat.Xtend.two.R.dimen.sw_dp_1));
        this.barDefaultHeight = typedArrayObtainStyledAttributes.getDimension(8, this.mResources.getDimension(com.boat.Xtend.two.R.dimen.sw_dp_1));
        this.isRound = typedArrayObtainStyledAttributes.getBoolean(26, false);
        this.hasBottomLine = typedArrayObtainStyledAttributes.getBoolean(44, false);
        this.hasTopLabel = typedArrayObtainStyledAttributes.getBoolean(46, false);
        this.hasLeftLabel = typedArrayObtainStyledAttributes.getBoolean(45, false);
        this.dividerCount = typedArrayObtainStyledAttributes.getInt(43, 2);
        this.mBottomLineStyle = typedArrayObtainStyledAttributes.getInt(12, 2);
        typedArrayObtainStyledAttributes.recycle();
        this.paintBar = new Paint();
        this.paintBar.setAntiAlias(true);
        initLabelPaint();
        this.mDividerPaint = new Paint();
    }

    public int getTargetValue() {
        return this.mTargetValue;
    }

    public void setTargetValue(int i, boolean z) {
        this.mTargetValue = i;
        if (z) {
            if (this.values != null && this.values.length > 0) {
                getMaxMinValues(this.values);
            } else if (this.valueList != null && this.valueList.size() > 0) {
                getMaxMinValues(this.valueList);
            }
            invalidate();
        }
    }

    public int getType() {
        return this.mType;
    }

    public void setType(int i) {
        this.mType = i;
    }

    private void drawBottomLineStyle() {
        this.mDividerPaint.reset();
        this.mDividerPaint.setAntiAlias(true);
        this.mDividerPaint.setColor(this.xLineColor);
        this.mDividerPaint.setStyle(Paint.Style.STROKE);
        if (this.mBottomLineStyle == 2) {
            if (this.labelAlpha > 1.0f) {
                this.labelAlpha = 1.0f;
            }
            if (this.labelAlpha < 0.0f) {
                this.labelAlpha = 0.0f;
            }
            this.mDividerPaint.setPathEffect(new DashPathEffect(new float[]{15.0f, 5.0f}, 5.0f));
        }
    }

    private void drawDividerStyle() {
        this.mDividerPaint.reset();
        this.mDividerPaint.setAntiAlias(true);
        this.mDividerPaint.setColor(this.xLineColor);
        this.mDividerPaint.setStyle(Paint.Style.STROKE);
        if (this.labelAlpha > 1.0f) {
            this.labelAlpha = 1.0f;
        }
        if (this.labelAlpha < 0.0f) {
            this.labelAlpha = 0.0f;
        }
        this.mDividerPaint.setPathEffect(new DashPathEffect(new float[]{15.0f, 5.0f}, 5.0f));
    }

    private void initLabelPaint() {
        if (this.paintLabel == null) {
            this.paintLabel = new Paint();
        }
        this.paintLabel.setAntiAlias(true);
        this.paintLabel.setColor(this.labelColor);
        this.paintLabel.setTextSize(this.labelSize);
        this.paintLabel.setAlpha((int) (this.labelAlpha * 255.0f));
        this.paintLabel.setTextAlign(Paint.Align.LEFT);
    }

    public boolean isAutoIncrementDividerCount() {
        return this.autoIncrementDividerCount;
    }

    public void setAutoIncrementDividerCount(boolean z) {
        this.autoIncrementDividerCount = z;
    }

    public boolean isBottomLabelAlignBar() {
        return this.bottomLabelAlignBar;
    }

    public void setBottomLabelAlignBar(boolean z) {
        this.bottomLabelAlignBar = z;
    }

    public int getDividerCount() {
        return this.dividerCount;
    }

    public void setDividerCount(int i) {
        this.dividerCount = i;
    }

    public void setBarColor(int i) {
        this.mBarColor = this.mResources.getColor(i);
    }

    public void setTargetBarColor(int i) {
        this.mTargetBarColor = this.mResources.getColor(i);
    }

    public void setDefaultBarColor(int i) {
        this.mDefaultBarColor = this.mResources.getColor(i);
    }

    public boolean isHasBottomLine() {
        return this.hasBottomLine;
    }

    public void setHasBottomLine(boolean z) {
        this.hasBottomLine = z;
    }

    public float getLabelAlpha() {
        return this.labelAlpha;
    }

    public void setLabelAlpha(float f2) {
        this.labelAlpha = f2;
    }

    public boolean isHasLeftLabel() {
        return this.hasLeftLabel;
    }

    public void setHasLeftLabel(boolean z) {
        this.hasLeftLabel = z;
    }

    public void setHasTopLabel(boolean z) {
        this.hasTopLabel = z;
    }

    public void isShowUnit(boolean z) {
        this.isShowUnit = z;
    }

    public void isAddClickZone(boolean z) {
        this.isAddClickZone = z;
    }

    public void setBarDefaultHeight(int i) {
        this.barDefaultHeight = i;
    }

    public int getBarBackground() {
        return this.barBgColor;
    }

    public float getBarMargin() {
        return this.barMargin;
    }

    public void setBarMargin(float f2) {
        this.barMargin = f2;
    }

    public boolean isPaintBarBackground() {
        return this.paintBarBackground;
    }

    public void setPaintBarBackground(boolean z) {
        this.paintBarBackground = z;
    }

    public int getBarBackgroundColor() {
        return this.barBackgroundColor;
    }

    public void setBarBackgroundColor(int i) {
        this.barBackgroundColor = i;
    }

    public float getBarMinHeightCorrection() {
        return this.barMinHeightCorrection;
    }

    public void setBarMinHeightCorrection(float f2) {
        if (f2 <= 0.0f) {
            return;
        }
        this.barMinHeightCorrection = f2;
    }

    public void setBarAndMarginWidthWeight(int i, int i2) {
        this.barWeight = i;
        this.marginWeight = i2;
    }

    public void setBarWidth(int i) {
        this.barWidth = getResources().getDimension(i);
    }

    @Override // com.ido.life.customview.charter.CharterBase, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.valueList == null || this.valueList.size() == 0) {
            return;
        }
        if (this.barRegionList == null) {
            this.barRegionList = new ArrayList();
        }
        this.barRegionList.clear();
        this.mValuesCount = this.valueList.size();
        initLabelPaint();
        measureTopLabelHeight();
        measureBottomLabelHeight();
        drawLabelAndDivider(canvas);
        float f2 = ((this.width - this.leftLabelWidth) - this.rightLabelWidth) / this.mValuesCount;
        float f3 = this.barWeight;
        this.barWidth = (f2 * f3) / (f3 + this.marginWeight);
        float f4 = (this.width - this.leftLabelWidth) - this.rightLabelWidth;
        float f5 = this.barWidth;
        int i = this.mValuesCount;
        this.barMargin = (f4 - (f5 * i)) / i;
        drawBottomLabels(canvas);
        drawBottomLine(canvas);
        drawBar(canvas);
        drawTopLabels(canvas);
    }

    private void measureBottomLabelHeight() {
        String[] strArr = this.bottomLabels;
        if (strArr == null || strArr.length == 0) {
            return;
        }
        this.paintLabel.setTextSize(this.labelSize);
        this.bottomLabelHeight = (int) (ScreenUtil.getTextHeight(this.paintLabel, this.bottomLabels[0]) + getResources().getDimension(com.boat.Xtend.two.R.dimen.sw_dp_10));
    }

    private void measureTopLabelHeight() {
        if (this.hasTopLabel) {
            this.paintLabel.setTextSize(this.mResources.getDimension(com.boat.Xtend.two.R.dimen.size36sp));
            this.topLabelHeight = ScreenUtil.getTextHeight(this.paintLabel, AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE) * 4;
        }
    }

    protected void drawBar(Canvas canvas) {
        float f2;
        int i;
        int i2;
        float f3;
        int i3;
        this.selRectF = null;
        if (this.maxY > 0) {
            if (this.criticalValue > 0 && this.criticalValue < this.maxY && this.mRealDividerCount > 1) {
                f3 = (this.height - this.topLabelHeight) - this.bottomLabelHeight;
                i3 = this.maxY - this.criticalValue;
            } else {
                f3 = (this.height - this.topLabelHeight) - this.bottomLabelHeight;
                i3 = this.maxY;
            }
            f2 = f3 / i3;
        } else {
            f2 = 0.0f;
        }
        for (int i4 = 0; i4 < this.mValuesCount; i4++) {
            ValueRangePair valueRangePair = this.valueListTransition.get(i4);
            float f4 = this.barWidth;
            float f5 = this.barMargin;
            float f6 = (i4 * (f4 + f5)) + this.leftLabelWidth + (f5 / 2.0f);
            this.barRegionList.add(new Region(Math.round(f6 - (getBarMargin() / 2.0f)), this.topLabelHeight, Math.round(this.barWidth + f6 + (getBarMargin() / 2.0f)), (int) (this.height - this.bottomLabelHeight)));
            if (valueRangePair.maxValue < 0.0f || valueRangePair.minValue <= valueRangePair.maxValue) {
                RectF rectF = new RectF();
                rectF.bottom = this.height - this.bottomLabelHeight;
                rectF.left = f6;
                rectF.right = rectF.left + this.barWidth;
                rectF.top = this.height - this.bottomLabelHeight;
                if (i4 == getSelected() && (valueRangePair.minValue != 0.0f || valueRangePair.maxValue != 0.0f)) {
                    this.selRectF = rectF;
                    this.selValue = valueRangePair;
                }
                if (valueRangePair.minValue < this.criticalValue) {
                    valueRangePair.maxValue = 0.0f;
                    valueRangePair.minValue = 0.0f;
                } else {
                    rectF.bottom = ((this.height - this.bottomLabelHeight) - ((valueRangePair.minValue - this.criticalValue) * f2)) - this.criticalHeight;
                    if (valueRangePair.maxValue > 0.0f) {
                        if (valueRangePair.minValue == valueRangePair.maxValue) {
                            float fMin = Math.min(0.5f * f2, MathHelper.dip2px(this.mContext, this.mResources.getDimension(com.boat.Xtend.two.R.dimen.sw_dp_2)));
                            if (valueRangePair.maxValue >= this.maxY) {
                                rectF.top = ((this.height - this.bottomLabelHeight) - ((valueRangePair.maxValue - this.criticalValue) * f2)) - this.criticalHeight;
                                rectF.bottom = (((this.height - this.bottomLabelHeight) - ((valueRangePair.minValue - this.criticalValue) * f2)) - this.criticalHeight) + fMin;
                            } else {
                                rectF.top = (((this.height - this.bottomLabelHeight) - ((valueRangePair.maxValue - this.criticalValue) * f2)) - this.criticalHeight) - fMin;
                            }
                        } else {
                            rectF.top = ((this.height - this.bottomLabelHeight) - ((valueRangePair.maxValue - this.criticalValue) * f2)) - this.criticalHeight;
                        }
                        float f7 = rectF.top;
                        int i5 = this.topLabelHeight;
                        rectF.top = f7 < ((float) i5) ? i5 : rectF.top;
                        rectF.top = rectF.bottom - rectF.top < ((float) MathHelper.dip2px(this.mContext, this.barDefaultHeight)) ? rectF.bottom - MathHelper.dip2px(this.mContext, this.barDefaultHeight) : rectF.top;
                        if (this.mType == 4 && this.timeType == 0) {
                            rectF.top = ((this.height - this.bottomLabelHeight) - (((this.maxY - this.criticalValue) * f2) * 0.6f)) - this.criticalHeight;
                        }
                    } else if (!this.isValuePair) {
                        if (this.mType == 4 && this.timeType == 0) {
                            rectF.top = ((this.height - this.bottomLabelHeight) - (((this.maxY - this.criticalValue) * f2) * 0.6f)) - this.criticalHeight;
                        } else {
                            rectF.top = rectF.bottom - MathHelper.dip2px(this.mContext, this.barDefaultHeight);
                        }
                    }
                    if (valueRangePair.mBarColor != 0) {
                        this.paintBar.setColor(valueRangePair.mBarColor);
                    } else {
                        this.paintBar.setColor(this.mBarColor);
                    }
                    if (this.mType == 4 && this.timeType == 0) {
                        if (valueRangePair.maxValue == -2.0f && (i2 = this.mDefaultBarColor) != 0) {
                            this.paintBar.setColor(i2);
                            this.paintBar.setAlpha(229);
                        } else if (valueRangePair.maxValue < this.maxY) {
                            this.paintBar.setAlpha(51);
                        } else {
                            this.paintBar.setAlpha(229);
                        }
                    } else if (valueRangePair.maxValue <= 0.0f) {
                        if (this.mType != 4) {
                            int i6 = this.mDefaultBarColor;
                            if (i6 != 0) {
                                this.paintBar.setColor(i6);
                            } else {
                                this.paintBar.setAlpha((int) (this.labelAlpha * 255.0f));
                            }
                        } else if (valueRangePair.maxValue < 0.0f && (i = this.mDefaultBarColor) != 0) {
                            this.paintBar.setColor(i);
                        } else {
                            this.paintBar.setAlpha((int) (this.labelAlpha * 255.0f));
                        }
                    } else if (valueRangePair.maxValue >= this.mTargetValue && this.mTargetValue != 0) {
                        this.paintBar.setColor(this.mTargetBarColor);
                    } else if ((valueRangePair.maxValue > 0.0f && this.mTargetValue == 0) || valueRangePair.maxValue < this.mTargetValue) {
                        this.paintBar.setColor(this.mBarColor);
                    }
                    if (this.isRound) {
                        float dimension = this.mResources.getDimension(com.boat.Xtend.two.R.dimen.sw_dp_3);
                        if (this.isValuePair) {
                            float dimension2 = this.mResources.getDimension(com.boat.Xtend.two.R.dimen.sw_dp_5);
                            canvas.drawRoundRect(rectF, dimension2, dimension2, this.paintBar);
                        } else {
                            canvas.drawRect(rectF.left, rectF.top + dimension, rectF.right, rectF.bottom, this.paintBar);
                            float f8 = 2.0f * dimension;
                            if ((rectF.right - rectF.left) - f8 <= 0.0f) {
                                canvas.drawArc(rectF.left, rectF.top, rectF.right, rectF.top + f8, -180.0f, 180.0f, true, this.paintBar);
                            } else {
                                canvas.drawRect(rectF.left + dimension, rectF.top, rectF.right - dimension, rectF.top + dimension, this.paintBar);
                                canvas.drawArc(rectF.left, rectF.top, rectF.left + f8, rectF.top + f8, -180.0f, 90.0f, true, this.paintBar);
                                canvas.drawArc(rectF.right - f8, rectF.top, rectF.right, rectF.top + f8, -90.0f, 90.0f, true, this.paintBar);
                            }
                        }
                    } else {
                        canvas.drawRect(rectF.left, rectF.top, rectF.right, rectF.bottom, this.paintBar);
                    }
                    if (i4 == getSelected()) {
                        this.selRectF = rectF;
                        this.selValue = valueRangePair;
                    }
                }
            }
        }
    }

    private void drawTopLabels(Canvas canvas) {
        String strValueOf;
        float f2;
        float f3;
        String str;
        RectF rectF;
        if (this.hasTopLabel) {
            for (OnItemBarTouchListener onItemBarTouchListener : this.mTouchListenerList) {
                if (onItemBarTouchListener != null) {
                    onItemBarTouchListener.onItemBarTouch(this.selRectF == null ? -1 : this.mSelected);
                }
            }
            if (this.selRectF == null || this.selValue == null) {
                return;
            }
            this.paintLabel.setTypeface(Typeface.createFromAsset(this.mResources.getAssets(), "font/MetricTest-Medium.otf"));
            this.paintLabel.setTextSize(this.mResources.getDimension(com.boat.Xtend.two.R.dimen.size24sp));
            ValueRangePair valueRangePairCloneObj = this.selValue.cloneObj();
            if (valueRangePairCloneObj.maxValue < 0.0f) {
                valueRangePairCloneObj.maxValue = 0.0f;
            }
            if (valueRangePairCloneObj.minValue < 0.0f) {
                valueRangePairCloneObj.minValue = 0.0f;
            }
            String string = (this.mType == 4 && this.timeType == 3) ? this.mContext.getString(com.boat.Xtend.two.R.string.detail_average_daily) : "";
            String str2 = this.unit;
            if (this.isValuePair) {
                if (valueRangePairCloneObj.minValue == valueRangePairCloneObj.maxValue) {
                    strValueOf = String.valueOf(Math.round(this.selValue.maxValue));
                } else {
                    strValueOf = String.format(this.mResources.getString(com.boat.Xtend.two.R.string.x_to_x), Integer.valueOf(Math.round(this.selValue.minValue)), Integer.valueOf(Math.round(this.selValue.maxValue)));
                }
            } else if (this.mType == 4) {
                if (this.timeType != 0) {
                    if (this.timeType != 3) {
                        if (this.selValue.maxValue != -2.0f) {
                            strValueOf = String.valueOf(Math.round(valueRangePairCloneObj.maxValue));
                        }
                        str2 = "";
                    } else if (this.selValue.maxValue == -2.0f) {
                        string = " ";
                        str2 = "";
                    } else {
                        strValueOf = NumUtil.float2String(valueRangePairCloneObj.maxValue, !NumUtil.isInteger(this.selValue.maxValue) ? 1 : 0);
                    }
                } else if (this.selValue.maxValue == this.maxY) {
                    strValueOf = this.mContext.getString(com.boat.Xtend.two.R.string.detail_qualified);
                } else {
                    strValueOf = this.selValue.maxValue == -2.0f ? "--" : this.mContext.getString(com.boat.Xtend.two.R.string.detail_disqualified);
                }
            } else {
                strValueOf = String.valueOf(Math.round(valueRangePairCloneObj.maxValue));
            }
            int textWidth = (int) (ScreenUtil.getTextWidth(this.paintLabel, strValueOf) + this.mResources.getDimension(com.boat.Xtend.two.R.dimen.sw_dp_5));
            int textHeight = ScreenUtil.getTextHeight(this.paintLabel, strValueOf);
            if (this.mType == 4 && "--".equals(strValueOf)) {
                textHeight = ScreenUtil.getTextHeight(this.paintLabel, AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            }
            this.paintLabel.setTypeface(Typeface.DEFAULT);
            this.paintLabel.setTextSize(this.mResources.getDimension(com.boat.Xtend.two.R.dimen.size12sp));
            int textWidth2 = ScreenUtil.getTextWidth(this.paintLabel, string);
            int textWidth3 = ScreenUtil.getTextWidth(this.paintLabel, this.unit);
            ScreenUtil.getTextHeight(this.paintLabel, this.unit);
            String timeStr = getTimeStr();
            int textWidth4 = ScreenUtil.getTextWidth(this.paintLabel, timeStr);
            int textHeight2 = ScreenUtil.getTextHeight(this.paintLabel, timeStr);
            float fMax = Math.max(textWidth2, Math.max(textWidth4, textWidth3 + textWidth));
            float f4 = (this.selRectF.left + this.selRectF.right) / 2.0f;
            float dimension = this.mResources.getDimension(com.boat.Xtend.two.R.dimen.sw_dp_10);
            this.paintLabel.setTextAlign(Paint.Align.LEFT);
            float f5 = fMax / 2.0f;
            if (f5 + dimension > f4) {
                f2 = dimension;
            } else {
                f2 = (f4 + f5) + dimension > this.width ? (this.width - fMax) - dimension : f4 - f5;
            }
            if (TextUtils.isEmpty(string)) {
                f3 = f4;
                str = timeStr;
                rectF = new RectF(f2 - dimension, 0.0f, fMax + f2 + dimension, textHeight + textHeight2 + (2.5f * dimension));
            } else {
                f3 = f4;
                str = timeStr;
                rectF = new RectF(f2 - dimension, 0.0f, fMax + f2 + dimension, (textHeight2 * 2) + textHeight + (3.0f * dimension));
            }
            this.paintLabel.setColor(this.mResources.getColor(com.boat.Xtend.two.R.color.color_text_side_bar_55));
            canvas.drawRoundRect(rectF, dimension, dimension, this.paintLabel);
            this.paintLabel.setTypeface(Typeface.createFromAsset(this.mResources.getAssets(), "font/MetricTest-Medium.otf"));
            if (!TextUtils.isEmpty(strValueOf)) {
                this.paintLabel.setTextSize(this.mResources.getDimension(com.boat.Xtend.two.R.dimen.size24sp));
                this.paintLabel.setColor(this.mResources.getColor(com.boat.Xtend.two.R.color.white));
                if (TextUtils.isEmpty(string)) {
                    canvas.drawText(strValueOf, f2, textHeight + dimension, this.paintLabel);
                } else {
                    canvas.drawText(strValueOf, f2, textHeight + textHeight2 + (dimension * 1.5f), this.paintLabel);
                }
            }
            this.paintLabel.setTypeface(Typeface.DEFAULT);
            this.paintLabel.setTextSize(this.mResources.getDimension(com.boat.Xtend.two.R.dimen.size12sp));
            this.paintLabel.setColor(this.mResources.getColor(com.boat.Xtend.two.R.color.color_B3_FFFFFF));
            if ((this.mType != 10 || this.selValue.maxValue != 0.0f) && !TextUtils.isEmpty(str2)) {
                if (TextUtils.isEmpty(string)) {
                    canvas.drawText(str2, textWidth + f2, textHeight + dimension, this.paintLabel);
                } else {
                    canvas.drawText(str2, textWidth + f2, textHeight + textHeight2 + (dimension * 1.5f), this.paintLabel);
                }
            }
            if (!TextUtils.isEmpty(string)) {
                canvas.drawText(string, f2, textHeight2 + dimension, this.paintLabel);
            }
            if (!TextUtils.isEmpty(str)) {
                if (TextUtils.isEmpty(string)) {
                    canvas.drawText(str, f2, textHeight + textHeight2 + (dimension * 1.5f), this.paintLabel);
                } else {
                    canvas.drawText(str, f2, textHeight + (textHeight2 * 2) + (dimension * 2.0f), this.paintLabel);
                }
            }
            this.paintLabel.setColor(getResources().getColor(com.boat.Xtend.two.R.color.color_text_side_bar_55));
            this.paintLabel.setStrokeWidth(this.mResources.getDimension(com.boat.Xtend.two.R.dimen.sw_dp_1));
            canvas.drawLine(f3, rectF.bottom, f3, this.selRectF.top, this.paintLabel);
        }
    }

    private void drawSelBar(Canvas canvas) {
        RectF rectF;
        ValueRangePair valueRangePair;
        if ((this.isValuePair && (valueRangePair = this.selValue) != null && valueRangePair.maxValue == 0.0f) || (rectF = this.selRectF) == null) {
            return;
        }
        if (this.isRound) {
            float dimension = this.mResources.getDimension(com.boat.Xtend.two.R.dimen.sw_dp_3);
            if (this.isValuePair) {
                float dimension2 = this.mResources.getDimension(com.boat.Xtend.two.R.dimen.sw_dp_5);
                canvas.drawRoundRect(this.selRectF, dimension2, dimension2, this.paintBar);
                return;
            }
            canvas.drawRect(this.selRectF.left, this.selRectF.top + dimension, this.selRectF.right, this.selRectF.bottom, this.paintBar);
            float f2 = dimension * 2.0f;
            if ((this.selRectF.right - this.selRectF.left) - f2 <= 0.0f) {
                canvas.drawArc(this.selRectF.left, this.selRectF.top, this.selRectF.right, this.selRectF.top + f2, -180.0f, 180.0f, true, this.paintBar);
                return;
            }
            canvas.drawRect(this.selRectF.left + dimension, this.selRectF.top, this.selRectF.right - dimension, this.selRectF.top + dimension, this.paintBar);
            canvas.drawArc(this.selRectF.left, this.selRectF.top, this.selRectF.left + f2, this.selRectF.top + f2, -180.0f, 90.0f, true, this.paintBar);
            canvas.drawArc(this.selRectF.right - f2, this.selRectF.top, this.selRectF.right, this.selRectF.top + f2, -90.0f, 90.0f, true, this.paintBar);
            return;
        }
        canvas.drawRect(rectF.left, this.selRectF.top, this.selRectF.right, this.selRectF.bottom, this.paintBar);
    }

    private String getTimeStr() {
        int selected = getSelected();
        if (selected == -1) {
            return "";
        }
        int i = this.timeType;
        if (i == 0) {
            return NumUtil.numberFormat(selected) + ":00-" + NumUtil.numberFormat(selected + 1) + ":00";
        }
        if (i == 1 || i == 2) {
            return DateUtil.getFormatDate(DateUtil.DATE_FORMAT_DM_1, DateUtil.getSpecifiedDayBefore(this.firstDateOfSelected, -selected));
        }
        if (i != 3) {
            return "";
        }
        return NumUtil.numberFormat(selected + 1) + "/" + this.selectedYear;
    }

    private void drawLabelAndDivider(Canvas canvas) {
        if (!this.hasLeftLabel && this.dividerCount == 0 && this.mTargetValue == 0) {
            this.leftLabelWidth = 0;
            return;
        }
        drawDividerStyle();
        this.paintLabel.setAlpha((int) (this.labelAlpha * 255.0f));
        this.paintLabel.setTextAlign(Paint.Align.LEFT);
        this.mRealDividerCount = this.dividerCount;
        if (this.autoIncrementDividerCount && this.maxY > this.defaultMaxValue) {
            this.mRealDividerCount += (this.maxY - this.defaultMaxValue) / this.increaseAmount;
        }
        int textWidth = ScreenUtil.getTextWidth(this.paintLabel, String.valueOf(this.maxY));
        int textHeight = ScreenUtil.getTextHeight(this.paintLabel, String.valueOf(this.maxY));
        if (this.timeType == 3) {
            textWidth = Math.max(textWidth, ScreenUtil.getTextWidth(this.paintLabel, String.valueOf(this.selectedYear)));
        }
        this.leftLabelWidth = textWidth + 10;
        if (this.mTargetValue > 0) {
            this.rightLabelWidth = ScreenUtil.getTextWidth(this.paintLabel, String.valueOf(this.mTargetValue)) + 15;
        }
        float f2 = ((this.height - this.bottomLabelHeight) - this.topLabelHeight) / this.mRealDividerCount;
        int iCeil = (int) Math.ceil(((this.maxY - this.criticalValue) * 1.0f) / this.mRealDividerCount);
        for (int i = 0; i < this.mRealDividerCount + 1; i++) {
            if (i != 0 || this.topDividerVisible) {
                float f3 = (i * f2) + this.topLabelHeight;
                int iRound = Math.round(this.maxY - (iCeil * i));
                if (iRound == 0) {
                    continue;
                } else {
                    canvas.drawText(String.valueOf(iRound), 0.0f, (textHeight >> 1) + f3, this.paintLabel);
                    if (i == this.mRealDividerCount) {
                        break;
                    }
                    if (this.mPath == null) {
                        this.mPath = new Path();
                    }
                    this.mPath.reset();
                    this.mPath.moveTo(this.leftLabelWidth, f3);
                    this.mPath.lineTo(this.width - this.rightLabelWidth, f3);
                    canvas.drawPath(this.mPath, this.mDividerPaint);
                }
            }
        }
        if (this.mTargetValue <= 0 || this.mTargetValue > this.maxY) {
            return;
        }
        float f4 = ((f2 / iCeil) * (this.maxY - this.mTargetValue)) + this.topLabelHeight;
        this.mDividerPaint.setColor(this.mBarColor);
        this.paintLabel.setColor(this.mBarColor);
        canvas.drawText(String.valueOf(this.mTargetValue), (this.width - this.rightLabelWidth) + 10.0f, (textHeight >> 1) + f4, this.paintLabel);
        canvas.drawLine(this.leftLabelWidth, f4, this.width - this.rightLabelWidth, f4, this.mDividerPaint);
        initLabelPaint();
    }

    private void drawBottomLine(Canvas canvas) {
        if (this.hasBottomLine) {
            drawBottomLineStyle();
            canvas.drawLine(this.leftLabelWidth, this.height - this.bottomLabelHeight, this.width - this.rightLabelWidth, this.height - this.bottomLabelHeight, this.mDividerPaint);
        }
    }

    private void drawBottomLabels(Canvas canvas) {
        int length;
        String[] strArr = this.bottomLabels;
        if (strArr == null || (length = strArr.length) == 0) {
            return;
        }
        this.paintLabel.setAlpha((int) (this.labelAlpha * 255.0f));
        if (this.timeType == 3) {
            this.paintLabel.setTextAlign(Paint.Align.LEFT);
            canvas.drawText(String.valueOf(this.selectedYear), 0.0f, this.height - 2.0f, this.paintLabel);
        }
        if (length == 1) {
            this.paintLabel.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(this.bottomLabels[0], ((this.width - this.leftLabelWidth) - this.rightLabelWidth) / 2.0f, this.height - 2.0f, this.paintLabel);
            return;
        }
        int i = length - 1;
        int size = this.valueList.size() / i;
        float f2 = this.width;
        int i2 = this.leftLabelWidth;
        float f3 = ((f2 - i2) - this.rightLabelWidth) / i;
        float f4 = (this.barMargin / 2.0f) + (this.barWidth / 2.0f) + i2;
        for (int i3 = 0; i3 < length; i3++) {
            if (this.bottomLabelAlignBar) {
                this.paintLabel.setTextAlign(Paint.Align.CENTER);
                if (i3 == 0) {
                    canvas.drawText(this.bottomLabels[i3], f4, this.height - 2.0f, this.paintLabel);
                } else if (i3 == i) {
                    float f5 = ((this.width - this.rightLabelWidth) - (this.barMargin / 2.0f)) - (this.barWidth / 2.0f);
                    if (ScreenUtil.getTextWidth(this.paintLabel, this.bottomLabels[i3]) / 2.0f > (this.barMargin / 2.0f) + (this.barWidth / 2.0f)) {
                        this.paintLabel.setTextAlign(Paint.Align.RIGHT);
                        f5 = this.width;
                    }
                    canvas.drawText(this.bottomLabels[i3], f5, this.height - 2.0f, this.paintLabel);
                } else {
                    canvas.drawText(this.bottomLabels[i3], (i3 * size * (this.barWidth + this.barMargin)) + f4, this.height - 2.0f, this.paintLabel);
                }
            } else {
                if (i3 == 0) {
                    this.paintLabel.setTextAlign(Paint.Align.LEFT);
                } else if (i3 == i) {
                    this.paintLabel.setTextAlign(Paint.Align.RIGHT);
                } else {
                    this.paintLabel.setTextAlign(Paint.Align.CENTER);
                }
                canvas.drawText(this.bottomLabels[i3], (i3 * f3) + this.leftLabelWidth, this.height - 2.0f, this.paintLabel);
            }
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int iCheckBarIndex;
        if (!this.mCanClick) {
            return super.onTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            if (this.hasTopLabel && this.mSelected != (iCheckBarIndex = checkBarIndex(motionEvent.getX(), motionEvent.getY()))) {
                this.mSelected = iCheckBarIndex;
                invalidate();
            }
        } else if (action == 1) {
            reset();
            int iCheckBarIndex2 = checkBarIndex(motionEvent.getX(), motionEvent.getY());
            OnItemBarOnClickListener onItemBarOnClickListener = this.onClickListener;
            if (onItemBarOnClickListener != null) {
                onItemBarOnClickListener.onClick(iCheckBarIndex2);
            }
        } else if (action == 2) {
            if (this.mVelocityTracker == null) {
                this.mVelocityTracker = VelocityTracker.obtain();
            }
            this.mVelocityTracker.addMovement(motionEvent);
            VelocityTracker velocityTracker = this.mVelocityTracker;
            velocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
            int iAbs = (int) Math.abs(velocityTracker.getXVelocity(motionEvent.getPointerId(0)));
            int iAbs2 = (int) Math.abs(velocityTracker.getYVelocity(motionEvent.getPointerId(0)));
            if (!this.isMoving) {
                if (iAbs >= this.lastXVelocity) {
                    this.increaseCount++;
                    this.lastXVelocity = iAbs;
                } else {
                    this.isMoving = true;
                    this.increaseCount = 5;
                }
            }
            if (iAbs < MIN_FLING_VELOCITY && ((iAbs > iAbs2 || iAbs2 == 0) && this.increaseCount >= 5)) {
                this.isMoving = true;
                getParent().requestDisallowInterceptTouchEvent(true);
            } else if (!this.isMoving) {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
            if (this.hasTopLabel) {
                this.mSelected = iCheckBarIndex;
                invalidate();
            }
        } else if (action == 3) {
            reset();
            releaseVelocityTracker();
        }
        return true;
    }

    private void reset() {
        this.isMoving = false;
        this.lastXVelocity = 0;
        this.increaseCount = 0;
    }

    private void releaseVelocityTracker() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.clear();
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    private int checkBarIndex(float f2, float f3) {
        if (this.barRegionList == null) {
            return -1;
        }
        for (int i = 0; i < this.barRegionList.size(); i++) {
            if (this.barRegionList.get(i).contains((int) f2, (int) f3)) {
                return i;
            }
        }
        return -1;
    }

    public void setUnit(String str) {
        this.unit = str;
    }

    public void setFirstDateOfSelected(Date date) {
        this.firstDateOfSelected = date;
    }

    public void setSelectedYear(int i) {
        this.selectedYear = i;
    }

    public void setTopDividerVisible(boolean z) {
        this.topDividerVisible = z;
    }

    public void setOnItemBarClickListener(OnItemBarOnClickListener onItemBarOnClickListener) {
        this.onClickListener = onItemBarOnClickListener;
    }

    public void setOnItemBarTouchListener(OnItemBarTouchListener onItemBarTouchListener) {
        if (onItemBarTouchListener != null) {
            this.mTouchListenerList.add(onItemBarTouchListener);
        }
    }

    public void setBottomLabels(String[] strArr) {
        this.bottomLabels = strArr;
        invalidate();
    }

    public void setCanClick(boolean z) {
        this.mCanClick = z;
    }

    public void setTimeType(int i) {
        this.timeType = i;
    }
}