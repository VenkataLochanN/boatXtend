package com.ido.life.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ScreenUtil;
import com.ido.life.R;
import com.ido.life.bean.MenstrualCycle;
import com.ido.life.util.DateUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class MenstrualCycleView extends View {
    public static final int CYCLE_AREA_COLOR_DEFAULT = 2131100192;
    public static final int EASY_PREGNANCY_AREA_COLOR_DEFAULT = 2131099753;
    public static final int LABEL_COLOR_DEFAULT = 2131099967;
    public static final int MENSTRUAL_AREA_COLOR_DEFAULT = 2131100108;
    public static final int TITLE_COLOR_DEFAULT = 2131100573;
    private float mBarHeight;
    private Context mContext;
    private float mCornerRadius;
    private int mCycleAreaColor;
    private RectF mCycleRectF;
    private int mDateColor;
    private int mDateHeight;
    private Map<Integer, Date> mDateMap;
    private Paint mDatePaint;
    private float mDateSize;
    private int mEasyPregnancyAreaColor;
    private int mEasyPregnancyIndex;
    private RectF mEasyPregnancyRectF;
    private float mHeight;
    private float mLineSpacing;
    private int mMenstrualAreaColor;
    private MenstrualCycle mMenstrualCycle;
    private RectF mMenstrualRectF;
    private int mMonthColor;
    private int mMonthHeight;
    private Paint mMonthPaint;
    private float mMonthSize;
    private int mOvulationIndex;
    private float mPadding;
    private float mPerDayWidth;
    private Paint mRectPaint;
    private float mWidth;

    public MenstrualCycleView(Context context) {
        this(context, null);
    }

    public MenstrualCycleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MenstrualCycleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        this.mDateMap = new HashMap();
        initAttrs(context, attributeSet);
        initPaint();
        initRectF();
    }

    private void initAttrs(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MenstrualCycleView);
        this.mCycleAreaColor = typedArrayObtainStyledAttributes.getColor(1, context.getColor(com.boat.Xtend.two.R.color.color_FDDEEB));
        this.mMenstrualAreaColor = typedArrayObtainStyledAttributes.getColor(6, context.getColor(com.boat.Xtend.two.R.color.color_F1247C));
        this.mEasyPregnancyAreaColor = typedArrayObtainStyledAttributes.getColor(5, context.getColor(com.boat.Xtend.two.R.color.color_15B9ED));
        this.mMonthColor = typedArrayObtainStyledAttributes.getColor(7, context.getColor(com.boat.Xtend.two.R.color.white));
        this.mDateColor = typedArrayObtainStyledAttributes.getColor(3, context.getColor(com.boat.Xtend.two.R.color.color_B3_FFFFFF));
        this.mCornerRadius = typedArrayObtainStyledAttributes.getDimension(0, getResources().getDimension(com.boat.Xtend.two.R.dimen.sw_dp_6));
        this.mMonthSize = typedArrayObtainStyledAttributes.getDimension(8, getResources().getDimension(com.boat.Xtend.two.R.dimen.size12sp));
        this.mDateSize = typedArrayObtainStyledAttributes.getDimension(4, getResources().getDimension(com.boat.Xtend.two.R.dimen.size12sp));
        this.mBarHeight = typedArrayObtainStyledAttributes.getDimension(2, getResources().getDimension(com.boat.Xtend.two.R.dimen.sw_dp_13));
        typedArrayObtainStyledAttributes.recycle();
        this.mPadding = getResources().getDimension(com.boat.Xtend.two.R.dimen.sw_dp_10);
    }

    private void initPaint() {
        this.mMonthPaint = new Paint();
        this.mMonthPaint.setColor(this.mMonthColor);
        this.mMonthPaint.setTextSize(this.mMonthSize);
        this.mMonthPaint.setAntiAlias(true);
        this.mMonthPaint.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "font/MetricTest-Medium.otf"));
        this.mDatePaint = new Paint();
        this.mDatePaint.setColor(this.mDateColor);
        this.mDatePaint.setTextSize(this.mDateSize);
        this.mDatePaint.setAntiAlias(true);
        this.mDatePaint.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "font/MetricTest-Regular.otf"));
        this.mRectPaint = new Paint();
        this.mRectPaint.setAntiAlias(true);
    }

    private void initRectF() {
        this.mCycleRectF = new RectF();
        this.mMenstrualRectF = new RectF();
        this.mEasyPregnancyRectF = new RectF();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mWidth = getMeasuredWidth() - 20;
        this.mHeight = getMeasuredHeight();
        invalidate();
    }

    private void measureLineHeight() {
        this.mMonthHeight = ScreenUtil.getTextHeight(this.mMonthPaint, AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        this.mDateHeight = ScreenUtil.getTextHeight(this.mDatePaint, AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        this.mLineSpacing = getResources().getDimension(com.boat.Xtend.two.R.dimen.sw_dp_12);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        measureLineHeight();
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int i3 = this.mMonthHeight;
        int i4 = this.mDateHeight;
        setMeasuredDimension(size, Math.max(size2, Math.round(i3 + i4 + this.mBarHeight + Math.max(i3, i4) + (this.mLineSpacing * 3.0f) + (this.mPadding * 2.0f))));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        MenstrualCycle menstrualCycle = this.mMenstrualCycle;
        if (menstrualCycle == null) {
            return;
        }
        if (menstrualCycle.cycleLength > 0) {
            this.mPerDayWidth = this.mWidth / this.mMenstrualCycle.cycleLength;
        }
        drawDate(canvas);
        drawRect(canvas);
    }

    private void drawDate(Canvas canvas) {
        float f2;
        float fIntValue;
        Iterator<Integer> it = this.mDateMap.keySet().iterator();
        int i = 1;
        while (true) {
            float f3 = 0.0f;
            if (it.hasNext()) {
                Integer next = it.next();
                int[] yearMonthDay = DateUtil.getYearMonthDay(this.mDateMap.get(next));
                if (next.intValue() == 1) {
                    this.mMonthPaint.setTextAlign(Paint.Align.LEFT);
                    this.mDatePaint.setTextAlign(Paint.Align.LEFT);
                    i = yearMonthDay[1];
                } else if (next.intValue() == this.mMenstrualCycle.cycleLength) {
                    this.mMonthPaint.setTextAlign(Paint.Align.RIGHT);
                    this.mDatePaint.setTextAlign(Paint.Align.RIGHT);
                    f3 = this.mWidth;
                } else {
                    if (i == yearMonthDay[1] - 1 && yearMonthDay[2] == 1) {
                        this.mMonthPaint.setTextAlign(Paint.Align.CENTER);
                        this.mDatePaint.setTextAlign(Paint.Align.CENTER);
                        f2 = this.mPerDayWidth;
                        fIntValue = next.intValue() + 1.5f;
                    } else {
                        this.mMonthPaint.setTextAlign(Paint.Align.CENTER);
                        this.mDatePaint.setTextAlign(Paint.Align.CENTER);
                        f2 = this.mPerDayWidth;
                        fIntValue = next.intValue() - 0.5f;
                    }
                    f3 = f2 * fIntValue;
                }
                if (next.intValue() == 1 || yearMonthDay[2] == 1) {
                    canvas.drawText(formatMonth(yearMonthDay[1]), f3, this.mPadding + this.mMonthHeight, this.mMonthPaint);
                }
                canvas.drawText(String.valueOf(yearMonthDay[2]), f3, this.mPadding + this.mMonthHeight + this.mLineSpacing + this.mDateHeight, this.mDatePaint);
                if (next.intValue() > 8) {
                    canvas.drawText(String.valueOf(next), f3, this.mPadding + (this.mMonthHeight * 2) + this.mDateHeight + this.mBarHeight + (this.mLineSpacing * 3.0f), this.mDatePaint);
                }
            } else {
                this.mMonthPaint.setTextAlign(Paint.Align.LEFT);
                canvas.drawText(this.mContext.getString(com.boat.Xtend.two.R.string.device_menstrual_cycle), 0.0f, this.mPadding + (this.mMonthHeight * 2) + this.mDateHeight + this.mBarHeight + (this.mLineSpacing * 3.0f), this.mMonthPaint);
                return;
            }
        }
    }

    private String formatMonth(int i) {
        int i2;
        switch (i) {
            case 1:
                i2 = com.boat.Xtend.two.R.string.menstrual_month_jan;
                break;
            case 2:
                i2 = com.boat.Xtend.two.R.string.menstrual_month_feb;
                break;
            case 3:
                i2 = com.boat.Xtend.two.R.string.menstrual_month_mar;
                break;
            case 4:
                i2 = com.boat.Xtend.two.R.string.menstrual_month_apr;
                break;
            case 5:
                i2 = com.boat.Xtend.two.R.string.menstrual_month_may;
                break;
            case 6:
                i2 = com.boat.Xtend.two.R.string.menstrual_month_jun;
                break;
            case 7:
                i2 = com.boat.Xtend.two.R.string.menstrual_month_jul;
                break;
            case 8:
                i2 = com.boat.Xtend.two.R.string.menstrual_month_aug;
                break;
            case 9:
                i2 = com.boat.Xtend.two.R.string.menstrual_month_sep;
                break;
            case 10:
                i2 = com.boat.Xtend.two.R.string.menstrual_month_oct;
                break;
            case 11:
                i2 = com.boat.Xtend.two.R.string.menstrual_month_nov;
                break;
            default:
                i2 = com.boat.Xtend.two.R.string.menstrual_month_dec;
                break;
        }
        return LanguageUtil.getLanguageText(i2);
    }

    private void drawRect(Canvas canvas) {
        float f2 = this.mPadding + this.mMonthHeight + this.mDateHeight + (this.mLineSpacing * 2.0f);
        RectF rectF = this.mCycleRectF;
        rectF.top = f2;
        rectF.right = this.mWidth;
        rectF.bottom = this.mBarHeight + f2;
        this.mRectPaint.setColor(this.mCycleAreaColor);
        RectF rectF2 = this.mCycleRectF;
        float f3 = this.mCornerRadius;
        canvas.drawRoundRect(rectF2, f3, f3, this.mRectPaint);
        RectF rectF3 = this.mMenstrualRectF;
        rectF3.top = f2;
        rectF3.right = this.mPerDayWidth * this.mMenstrualCycle.menstrualLength;
        this.mMenstrualRectF.bottom = this.mBarHeight + f2;
        this.mRectPaint.setColor(this.mMenstrualAreaColor);
        RectF rectF4 = this.mMenstrualRectF;
        float f4 = this.mCornerRadius;
        canvas.drawRoundRect(rectF4, f4, f4, this.mRectPaint);
        RectF rectF5 = this.mEasyPregnancyRectF;
        float f5 = this.mPerDayWidth;
        rectF5.left = (this.mEasyPregnancyIndex - 1) * f5;
        rectF5.top = f2;
        rectF5.right = f5 * this.mOvulationIndex;
        rectF5.bottom = f2 + this.mBarHeight;
        this.mRectPaint.setColor(this.mEasyPregnancyAreaColor);
        RectF rectF6 = this.mEasyPregnancyRectF;
        float f6 = this.mCornerRadius;
        canvas.drawRoundRect(rectF6, f6, f6, this.mRectPaint);
    }

    public void setData(MenstrualCycle menstrualCycle) {
        this.mMenstrualCycle = menstrualCycle;
        getDatesToBeDraw();
        invalidate();
    }

    private void getDatesToBeDraw() {
        this.mDateMap.clear();
        Date date = this.mMenstrualCycle.startDate;
        this.mDateMap.put(1, date);
        this.mDateMap.put(Integer.valueOf(this.mMenstrualCycle.menstrualLength), DateUtil.getSpecifiedDayBefore(date, -(this.mMenstrualCycle.menstrualLength - 1)));
        this.mEasyPregnancyIndex = DateUtil.daysBetween(date, this.mMenstrualCycle.easyPregnancyStartDate) + 1;
        this.mDateMap.put(Integer.valueOf(this.mEasyPregnancyIndex), this.mMenstrualCycle.easyPregnancyStartDate);
        this.mOvulationIndex = DateUtil.daysBetween(date, this.mMenstrualCycle.ovulationDate) + 1;
        this.mDateMap.put(Integer.valueOf(this.mOvulationIndex), this.mMenstrualCycle.ovulationDate);
        this.mDateMap.put(Integer.valueOf(this.mMenstrualCycle.cycleLength), this.mMenstrualCycle.endDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        for (int i = 0; i < this.mMenstrualCycle.cycleLength; i++) {
            if (calendar.get(5) == 1) {
                this.mDateMap.put(Integer.valueOf(i + 1), DateUtil.getSpecifiedDayBefore(date, -i));
            }
            calendar.add(5, 1);
        }
    }
}