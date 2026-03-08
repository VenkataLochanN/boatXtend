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
import android.view.animation.Interpolator;
import com.ido.alexa.AlexaCustomSkillConstant;
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
public class ColorBarChart extends CharterBase {
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
    private int dividerCount;
    private Date firstDateOfSelected;
    private boolean hasBottomLine;
    private boolean hasLeftLabel;
    private boolean hasTopLabel;
    private boolean isAddClickZone;
    private boolean isRound;
    private boolean isShowUnit;
    private float labelAlpha;
    private int labelColor;
    private float labelSize;
    private int leftLabelWidth;
    private int mBarColor;
    private Paint mDividerPaint;
    private Path mPath;
    private Resources mResources;
    private int mValuesCount;
    private float marginWeight;
    public OnItemBarOnClickListener onClickListener;
    private Paint paintBar;
    private boolean paintBarBackground;
    private Paint paintLabel;
    private RectF selRectF;
    private ValueRangePair selValue;
    private int selectedYear;
    private int timeType;
    private boolean topDividerVisible;
    private int topLabelHeight;
    private String unit;

    public interface OnItemBarOnClickListener {
        void onClick(int i);
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

    public ColorBarChart(Context context) {
        this(context, null);
    }

    public ColorBarChart(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ColorBarChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.hasTopLabel = false;
        this.isShowUnit = true;
        this.isAddClickZone = false;
        this.barWeight = 3.0f;
        this.marginWeight = 2.0f;
        this.timeType = 0;
        this.topDividerVisible = true;
        init(context, attributeSet);
    }

    public ColorBarChart(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.hasTopLabel = false;
        this.isShowUnit = true;
        this.isAddClickZone = false;
        this.barWeight = 3.0f;
        this.marginWeight = 2.0f;
        this.timeType = 0;
        this.topDividerVisible = true;
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Charter);
        this.mResources = getResources();
        this.paintBarBackground = typedArrayObtainStyledAttributes.getBoolean(33, true);
        this.mBarColor = typedArrayObtainStyledAttributes.getColor(4, this.mResources.getColor(com.boat.Xtend.two.R.color.white));
        this.barBgColor = typedArrayObtainStyledAttributes.getColor(3, this.mResources.getColor(com.boat.Xtend.two.R.color.translate));
        this.labelColor = typedArrayObtainStyledAttributes.getColor(10, this.mResources.getColor(com.boat.Xtend.two.R.color.white));
        this.labelAlpha = typedArrayObtainStyledAttributes.getFloat(9, 0.6f);
        this.labelSize = typedArrayObtainStyledAttributes.getDimension(11, this.mResources.getDimension(com.boat.Xtend.two.R.dimen.size10dp));
        this.barMargin = typedArrayObtainStyledAttributes.getDimension(5, this.mResources.getDimension(com.boat.Xtend.two.R.dimen.sw_dp_1));
        this.barWidth = typedArrayObtainStyledAttributes.getDimension(7, this.mResources.getDimension(com.boat.Xtend.two.R.dimen.sw_dp_3));
        this.barMinHeightCorrection = typedArrayObtainStyledAttributes.getDimension(6, this.mResources.getDimension(com.boat.Xtend.two.R.dimen.sw_dp_1));
        this.barDefaultHeight = typedArrayObtainStyledAttributes.getDimension(8, this.mResources.getDimension(com.boat.Xtend.two.R.dimen.sw_dp_2));
        this.isRound = typedArrayObtainStyledAttributes.getBoolean(26, false);
        this.hasBottomLine = typedArrayObtainStyledAttributes.getBoolean(44, false);
        this.hasTopLabel = typedArrayObtainStyledAttributes.getBoolean(46, false);
        this.hasLeftLabel = typedArrayObtainStyledAttributes.getBoolean(45, false);
        this.dividerCount = typedArrayObtainStyledAttributes.getInt(43, 2);
        typedArrayObtainStyledAttributes.recycle();
        this.paintBar = new Paint();
        this.paintBar.setAntiAlias(true);
        initLabelPaint();
        this.mDividerPaint = new Paint();
        this.mDividerPaint.setAntiAlias(true);
        this.mDividerPaint.setColor(this.labelColor);
        this.mDividerPaint.setStyle(Paint.Style.STROKE);
        if (this.labelAlpha > 1.0f) {
            this.labelAlpha = 1.0f;
        }
        if (this.labelAlpha < 0.0f) {
            this.labelAlpha = 0.0f;
        }
        this.mDividerPaint.setAlpha((int) (this.labelAlpha * 255.0f));
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
        invalidate();
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

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
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
        drawLeftLabelAndDivider(canvas);
        float f2 = (this.width - this.leftLabelWidth) / this.mValuesCount;
        float f3 = this.barWeight;
        this.barWidth = (f2 * f3) / (f3 + this.marginWeight);
        float f4 = this.width - this.leftLabelWidth;
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
            this.topLabelHeight = ScreenUtil.getTextHeight(this.paintLabel, AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE) * 3;
        }
    }

    private void drawBar(Canvas canvas) {
        this.selRectF = null;
        float f2 = this.maxY != 0 ? ((this.height - this.topLabelHeight) - this.bottomLabelHeight) / this.maxY : 0.0f;
        for (int i = 0; i < this.mValuesCount; i++) {
            ValueRangePair valueRangePair = this.valueListTransition.get(i);
            float f3 = this.barWidth;
            float f4 = this.barMargin;
            float f5 = (i * (f3 + f4)) + this.leftLabelWidth + (f4 / 2.0f);
            this.barRegionList.add(new Region(Math.round(f5 - (getBarMargin() / 2.0f)), this.topLabelHeight, Math.round(this.barWidth + f5 + (getBarMargin() / 2.0f)), (int) (this.height - this.bottomLabelHeight)));
            if (valueRangePair.minValue <= valueRangePair.maxValue) {
                RectF rectF = new RectF();
                rectF.bottom = (this.height - this.bottomLabelHeight) - (valueRangePair.minValue * f2);
                rectF.left = f5;
                rectF.right = rectF.left + this.barWidth;
                if (valueRangePair.maxValue > 0.0f) {
                    rectF.top = (this.height - this.bottomLabelHeight) - (valueRangePair.maxValue * f2);
                    float f6 = rectF.top;
                    int i2 = this.topLabelHeight;
                    rectF.top = f6 < ((float) i2) ? i2 : rectF.top;
                    rectF.top = rectF.bottom - rectF.top < ((float) MathHelper.dip2px(this.mContext, this.barDefaultHeight)) ? rectF.bottom - MathHelper.dip2px(this.mContext, this.barDefaultHeight) : rectF.top;
                } else if (!this.isValuePair) {
                    rectF.top = rectF.bottom - MathHelper.dip2px(this.mContext, this.barDefaultHeight);
                }
                if (valueRangePair.mBarColor > 0) {
                    this.paintBar.setColor(valueRangePair.mBarColor);
                } else {
                    this.paintBar.setColor(this.mBarColor);
                }
                if (valueRangePair.maxValue <= 0.0f) {
                    this.paintBar.setAlpha((int) (this.labelAlpha * 255.0f));
                } else {
                    this.paintBar.setAlpha(255);
                }
                if (this.isRound) {
                    float dimension = this.mResources.getDimension(com.boat.Xtend.two.R.dimen.sw_dp_2);
                    if (this.isValuePair) {
                        dimension = this.mResources.getDimension(com.boat.Xtend.two.R.dimen.sw_dp_5);
                    }
                    canvas.drawRoundRect(rectF, dimension, dimension, this.paintBar);
                } else {
                    canvas.drawRect(rectF.left, rectF.top, rectF.right, rectF.bottom, this.paintBar);
                }
                if (i == getSelected() && valueRangePair.maxValue != 0.0f) {
                    this.selRectF = rectF;
                    this.selValue = valueRangePair;
                }
            }
        }
    }

    private void drawTopLabels(Canvas canvas) {
        String strValueOf;
        float f2;
        if (this.hasTopLabel && this.selRectF != null) {
            this.paintLabel.setTypeface(Typeface.DEFAULT_BOLD);
            this.paintLabel.setTextSize(this.mResources.getDimension(com.boat.Xtend.two.R.dimen.size28sp));
            if (this.isValuePair) {
                strValueOf = String.format(this.mResources.getString(com.boat.Xtend.two.R.string.x_to_x), Float.valueOf(this.selValue.minValue), Float.valueOf(this.selValue.maxValue));
            } else {
                strValueOf = String.valueOf(this.selValue.maxValue);
            }
            int textWidth = (int) (ScreenUtil.getTextWidth(this.paintLabel, strValueOf) + this.mResources.getDimension(com.boat.Xtend.two.R.dimen.sw_dp_5));
            int textHeight = ScreenUtil.getTextHeight(this.paintLabel, strValueOf);
            this.paintLabel.setTypeface(Typeface.DEFAULT);
            this.paintLabel.setTextSize(this.mResources.getDimension(com.boat.Xtend.two.R.dimen.size15sp));
            int textWidth2 = ScreenUtil.getTextWidth(this.paintLabel, this.unit);
            ScreenUtil.getTextHeight(this.paintLabel, this.unit);
            String timeStr = getTimeStr();
            int textWidth3 = ScreenUtil.getTextWidth(this.paintLabel, timeStr);
            int textHeight2 = ScreenUtil.getTextHeight(this.paintLabel, timeStr);
            int i = textWidth2 + textWidth;
            float f3 = textWidth3 > i ? textWidth3 : i;
            float f4 = (this.selRectF.left + this.selRectF.right) / 2.0f;
            float dimension = this.mResources.getDimension(com.boat.Xtend.two.R.dimen.sw_dp_10);
            this.paintLabel.setTextAlign(Paint.Align.LEFT);
            float f5 = f3 / 2.0f;
            if (f5 + dimension > f4) {
                f2 = dimension;
            } else {
                f2 = (f4 + f5) + dimension > this.width ? (this.width - f3) - dimension : f4 - f5;
            }
            float f6 = textHeight2 + textHeight;
            RectF rectF = new RectF(f2 - dimension, 0.0f, f3 + f2 + dimension, f6 + (2.5f * dimension));
            this.paintLabel.setColor(this.mResources.getColor(com.boat.Xtend.two.R.color.color_F2F2F6));
            canvas.drawRoundRect(rectF, dimension, dimension, this.paintLabel);
            this.paintLabel.setTypeface(Typeface.DEFAULT_BOLD);
            if (!TextUtils.isEmpty(strValueOf)) {
                this.paintLabel.setTextSize(this.mResources.getDimension(com.boat.Xtend.two.R.dimen.size28sp));
                this.paintLabel.setColor(this.mResources.getColor(com.boat.Xtend.two.R.color.color_292C2F));
                canvas.drawText(strValueOf, f2, textHeight + dimension, this.paintLabel);
            }
            this.paintLabel.setTypeface(Typeface.DEFAULT);
            this.paintLabel.setTextSize(this.mResources.getDimension(com.boat.Xtend.two.R.dimen.size15sp));
            this.paintLabel.setColor(this.mResources.getColor(com.boat.Xtend.two.R.color.color_A9ABAC));
            if (!TextUtils.isEmpty(this.unit)) {
                canvas.drawText(this.unit, textWidth + f2, textHeight + dimension, this.paintLabel);
            }
            if (!TextUtils.isEmpty(timeStr)) {
                canvas.drawText(timeStr, f2, f6 + (dimension * 1.5f), this.paintLabel);
            }
            this.paintLabel.setStrokeWidth(this.mResources.getDimension(com.boat.Xtend.two.R.dimen.sw_dp_1) * 1.5f);
            canvas.drawLine(f4, rectF.bottom, f4, this.selRectF.top, this.paintLabel);
            canvas.drawLine(f4, this.selRectF.bottom, f4, this.height - this.bottomLabelHeight, this.paintLabel);
        }
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
            return DateUtil.getFormatDate(DateUtil.DATE_FORMAT_MD, DateUtil.getSpecifiedDayBefore(this.firstDateOfSelected, -selected));
        }
        if (i != 3) {
            return "";
        }
        return this.selectedYear + "/" + (selected + 1);
    }

    private void drawLeftLabelAndDivider(Canvas canvas) {
        if (!this.hasLeftLabel || this.dividerCount == 0) {
            return;
        }
        this.paintLabel.setAlpha((int) (this.labelAlpha * 255.0f));
        this.paintLabel.setTextAlign(Paint.Align.LEFT);
        int i = this.dividerCount;
        if (this.autoIncrementDividerCount && this.maxY > this.defaultMaxValue) {
            i += (this.maxY - this.defaultMaxValue) / this.increaseAmount;
        }
        int textWidth = ScreenUtil.getTextWidth(this.paintLabel, String.valueOf(this.maxY));
        int textHeight = ScreenUtil.getTextHeight(this.paintLabel, String.valueOf(this.maxY));
        this.leftLabelWidth = textWidth + 10;
        int iCeil = (int) Math.ceil((this.maxY * 1.0f) / r3);
        float f2 = ((this.height - this.bottomLabelHeight) - this.topLabelHeight) / i;
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 != 0 || this.topDividerVisible) {
                float f3 = (i2 * f2) + this.topLabelHeight;
                int i3 = this.maxY - (iCeil * i2);
                if (i3 != 0) {
                    canvas.drawText(String.valueOf(i3), 0.0f, (textHeight >> 1) + f3, this.paintLabel);
                    if (this.mPath == null) {
                        this.mPath = new Path();
                    }
                    this.mPath.reset();
                    this.mPath.moveTo(this.leftLabelWidth, f3);
                    this.mPath.lineTo(this.width, f3);
                    canvas.drawPath(this.mPath, this.mDividerPaint);
                }
            }
        }
    }

    private void drawBottomLine(Canvas canvas) {
        if (this.hasBottomLine) {
            canvas.drawLine(this.leftLabelWidth, this.height - this.bottomLabelHeight, this.width, this.height - this.bottomLabelHeight, this.mDividerPaint);
        }
    }

    private void drawBottomLabels(Canvas canvas) {
        int length;
        String[] strArr = this.bottomLabels;
        if (strArr == null || (length = strArr.length) == 0) {
            return;
        }
        this.paintLabel.setAlpha((int) (this.labelAlpha * 255.0f));
        if (length == 1) {
            this.paintLabel.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(this.bottomLabels[0], (this.width - this.leftLabelWidth) / 2.0f, this.height - 2.0f, this.paintLabel);
            return;
        }
        int i = length - 1;
        int size = this.valueList.size() / i;
        float f2 = this.width;
        int i2 = this.leftLabelWidth;
        float f3 = (f2 - i2) / i;
        float f4 = (this.barMargin / 2.0f) + (this.barWidth / 2.0f) + i2;
        for (int i3 = 0; i3 < length; i3++) {
            if (this.bottomLabelAlignBar) {
                this.paintLabel.setTextAlign(Paint.Align.CENTER);
                if (i3 == 0) {
                    canvas.drawText(this.bottomLabels[i3], f4, this.height - 2.0f, this.paintLabel);
                } else if (i3 == i) {
                    float f5 = (this.width - (this.barMargin / 2.0f)) - (this.barWidth / 2.0f);
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

    /* JADX WARN: Removed duplicated region for block: B:11:0x0021  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r4) {
        /*
            r3 = this;
            int r0 = r4.getAction()
            r1 = 1
            if (r0 == 0) goto L21
            if (r0 == r1) goto Ld
            r2 = 2
            if (r0 == r2) goto L21
            goto L3a
        Ld:
            float r0 = r4.getX()
            float r4 = r4.getY()
            int r4 = r3.checkBarIndex(r0, r4)
            com.ido.life.customview.charter.ColorBarChart$OnItemBarOnClickListener r0 = r3.onClickListener
            if (r0 == 0) goto L3a
            r0.onClick(r4)
            goto L3a
        L21:
            boolean r0 = r3.hasTopLabel
            if (r0 == 0) goto L3a
            float r0 = r4.getX()
            float r4 = r4.getY()
            int r4 = r3.checkBarIndex(r0, r4)
            int r0 = r3.mSelected
            if (r0 == r4) goto L3a
            r3.mSelected = r4
            r3.invalidate()
        L3a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.customview.charter.ColorBarChart.onTouchEvent(android.view.MotionEvent):boolean");
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

    public void setBottomLabels(String[] strArr) {
        this.bottomLabels = strArr;
        invalidate();
    }

    public void setTimeType(int i) {
        this.timeType = i;
    }
}