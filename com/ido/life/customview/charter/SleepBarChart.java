package com.ido.life.customview.charter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.core.view.ViewCompat;
import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.ble.data.manage.database.HealthSleep;
import com.ido.ble.data.manage.database.HealthSleepItem;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NumUtil;
import com.ido.common.utils.ScreenUtil;
import com.ido.life.database.model.SleepDetailModel;
import com.ido.life.util.DateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SleepBarChart extends View {
    private static final long ANIM_DURATION = 300;
    private static int MIN_FLING_VELOCITY = 400;
    private float barHeight;
    private float barMargin;
    private ArrayList<Region> barRegionList;
    private int barWeight;
    private float barWidth;
    private int bottomLabelHeight;
    private String[] bottomLabels;
    private int[] colors;
    private int dataTimeType;
    private Date firstDateOfSelected;
    private final boolean hasTopLabel;
    private List<SleepDetailModel> healthSleepList;
    private int height;
    int increaseCount;
    private boolean isInverted;
    boolean isMoving;
    private boolean isOverlay;
    int lastXVelocity;
    private ValueAnimator mAnimatorY;
    private float mDaySleepBarHeight;
    private Paint mDividerPaint;
    private int mLastBarColor;
    private RectF mLastRectF;
    private int mLeftWidth;
    private int mMaxValue;
    private int mMaximumVelocity;
    private OnItemSelectListener mOnItemSelectListener;
    private final Resources mResources;
    private int mSelected;
    private int mValuesCount;
    private VelocityTracker mVelocityTracker;
    private int marginWeight;
    float scaleY;
    private RectF selectedRectF;
    private SleepDetailModel selectedSleep;
    private int selectedYear;
    private List<int[]> sleepBarDataList;
    private HealthSleep sleepData;
    private List<HealthSleepItem> sleepItemList;
    private Paint sleepPaint;
    private int sleepStartTime;
    private int[] sleepTimes;
    private int topLabelHeight;
    private float topLineHeight;
    private int width;

    public interface OnItemSelectListener {
        void onItemSelected(int i);
    }

    public SleepBarChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.colors = new int[]{getResources().getColor(R.color.color_alive_sleep), getResources().getColor(R.color.color_sleep), getResources().getColor(R.color.color_deep_sleep), getResources().getColor(R.color.color_fast_sleep)};
        this.barWeight = 3;
        this.marginWeight = 2;
        this.mValuesCount = 1;
        this.mSelected = -1;
        this.barRegionList = new ArrayList<>();
        this.sleepBarDataList = new ArrayList();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        MIN_FLING_VELOCITY = viewConfiguration.getScaledMinimumFlingVelocity() * 4;
        CommonLogUtil.d("MIN_FLING_VELOCITY=" + MIN_FLING_VELOCITY);
        this.mVelocityTracker = VelocityTracker.obtain();
        this.mResources = context.getResources();
        this.sleepTimes = new int[2];
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.ido.life.R.styleable.SleepBarChart);
        this.isInverted = typedArrayObtainStyledAttributes.getBoolean(0, false);
        this.hasTopLabel = typedArrayObtainStyledAttributes.getBoolean(1, false);
        typedArrayObtainStyledAttributes.recycle();
        this.topLineHeight = getResources().getDimension(R.dimen.sw_dp_3);
        initPaint();
    }

    private void initPaint() {
        this.sleepPaint = new Paint(1);
        this.sleepPaint.setTextSize(getResources().getDimension(R.dimen.size10dp));
        this.mDividerPaint = new Paint();
        this.mDividerPaint.setAntiAlias(true);
        this.mDividerPaint.setColor(getResources().getColor(R.color.color_text_side_bar_55));
        this.mDividerPaint.setStyle(Paint.Style.STROKE);
        this.mDividerPaint.setAlpha(102);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.width = getMeasuredWidth();
        this.height = getMeasuredHeight();
        this.barHeight = this.height;
        measureTopLabelHeight();
        animateY();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.selectedSleep = null;
        OnItemSelectListener onItemSelectListener = this.mOnItemSelectListener;
        if (onItemSelectListener != null) {
            onItemSelectListener.onItemSelected(-1);
        }
        if (this.isOverlay) {
            List<SleepDetailModel> list = this.healthSleepList;
            if (list == null || list.size() == 0) {
                return;
            } else {
                this.mValuesCount = this.healthSleepList.size();
            }
        } else {
            List<HealthSleepItem> list2 = this.sleepItemList;
            if (list2 == null || list2.size() == 0) {
                drawBottomLabels(canvas);
                return;
            }
        }
        if (this.dataTimeType == 3) {
            this.sleepPaint.setTextSize(getResources().getDimension(R.dimen.size10dp));
            this.mLeftWidth = ScreenUtil.getTextWidth(this.sleepPaint, String.valueOf(this.selectedYear)) + 5;
        }
        drawBottomLabels(canvas);
        drawSleepBar(canvas);
    }

    private void measureTopLabelHeight() {
        if (this.hasTopLabel) {
            float textSize = this.sleepPaint.getTextSize();
            this.sleepPaint.setTextSize(this.mResources.getDimension(R.dimen.size36sp));
            this.topLabelHeight = (ScreenUtil.getTextHeight(this.sleepPaint, AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE) * 4) + Math.round(this.mResources.getDimension(R.dimen.sw_dp_30));
            this.sleepPaint.setTextSize(textSize);
        }
    }

    private void drawBottomLabels(Canvas canvas) {
        this.sleepPaint.reset();
        this.sleepPaint.setAntiAlias(true);
        this.sleepPaint.setColor(getResources().getColor(R.color.white));
        this.sleepPaint.setTextSize(this.mResources.getDimension(R.dimen.size10dp));
        this.sleepPaint.setAlpha(102);
        this.bottomLabelHeight = (int) (ScreenUtil.getTextHeight(this.sleepPaint, AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE) + this.mResources.getDimension(R.dimen.sw_dp_10));
        if (this.isOverlay) {
            drawDateLabel(canvas);
        } else {
            drawSleepTimeHorizon(canvas);
        }
    }

    private void drawDateLabel(Canvas canvas) {
        int length;
        float f2;
        int i = this.width;
        int i2 = this.mLeftWidth;
        int i3 = this.mValuesCount;
        int i4 = this.barWeight;
        this.barWidth = (((i - i2) / i3) * i4) / (i4 + this.marginWeight);
        this.barMargin = ((i - i2) - (this.barWidth * i3)) / i3;
        String[] strArr = this.bottomLabels;
        if (strArr == null || (length = strArr.length) == 0) {
            return;
        }
        if (this.dataTimeType == 3) {
            this.sleepPaint.setTextAlign(Paint.Align.LEFT);
            canvas.drawText(String.valueOf(this.selectedYear), 0.0f, this.height - 2, this.sleepPaint);
        }
        if (length == 1) {
            this.sleepPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(this.bottomLabels[0], this.width / 2, this.height - 2, this.sleepPaint);
        } else {
            int i5 = length - 1;
            int size = this.healthSleepList.size() / i5;
            float f3 = (this.barMargin / 2.0f) + (this.barWidth / 2.0f) + this.mLeftWidth;
            for (int i6 = 0; i6 < length; i6++) {
                this.sleepPaint.setTextAlign(Paint.Align.CENTER);
                int textWidth = ScreenUtil.getTextWidth(this.sleepPaint, this.bottomLabels[i6]);
                if (i6 == 0) {
                    if (textWidth > this.barMargin + this.barWidth) {
                        this.sleepPaint.setTextAlign(Paint.Align.LEFT);
                        f2 = this.mLeftWidth;
                    } else {
                        f2 = f3;
                    }
                    canvas.drawText(this.bottomLabels[i6], f2, this.height - 2, this.sleepPaint);
                } else if (i6 == i5) {
                    float f4 = this.width;
                    float f5 = this.barMargin;
                    float f6 = this.barWidth;
                    float f7 = (f4 - (f5 / 2.0f)) - (f6 / 2.0f);
                    if (textWidth > f5 + f6) {
                        this.sleepPaint.setTextAlign(Paint.Align.RIGHT);
                        f7 = this.width;
                    }
                    canvas.drawText(this.bottomLabels[i6], f7, this.height - 2, this.sleepPaint);
                } else {
                    canvas.drawText(this.bottomLabels[i6], (i6 * size * (this.barWidth + this.barMargin)) + f3, this.height - 2, this.sleepPaint);
                }
            }
        }
        this.mDividerPaint.reset();
        this.mDividerPaint.setStyle(Paint.Style.STROKE);
        this.mDividerPaint.setAntiAlias(true);
        this.mDividerPaint.setColor(getResources().getColor(R.color.white));
        this.mDividerPaint.setAlpha(102);
        int i7 = this.height;
        int i8 = this.bottomLabelHeight;
        canvas.drawLine(0.0f, i7 - i8, this.width, i7 - i8, this.mDividerPaint);
        this.mDividerPaint.setColor(getResources().getColor(R.color.color_text_side_bar_55));
    }

    private void drawSleepTimeHorizon(Canvas canvas) {
        String str;
        String str2;
        HealthSleep healthSleep = this.sleepData;
        if (healthSleep == null) {
            return;
        }
        Date date = DateUtil.getDate(healthSleep.year, this.sleepData.month, this.sleepData.day);
        if (this.sleepData.totalSleepMinutes == 0) {
            str = DateUtil.format(date, DateUtil.DATE_FORMAT_DM) + " 00:00";
            str2 = DateUtil.format(date, DateUtil.DATE_FORMAT_DM) + " 24:00";
        } else {
            int sleepEndedTimeH = (this.sleepData.getSleepEndedTimeH() * 60) + this.sleepData.getSleepEndedTimeM();
            String str3 = DateUtil.format(date, DateUtil.DATE_FORMAT_DM) + " " + NumUtil.numberFormat(this.sleepData.getSleepEndedTimeH()) + ":" + NumUtil.numberFormat(this.sleepData.getSleepEndedTimeM());
            int totalSleepMinutes = this.sleepData.getTotalSleepMinutes();
            if (sleepEndedTimeH <= totalSleepMinutes) {
                sleepEndedTimeH += 1440;
                date = DateUtil.getSpecifiedDayBefore(date, 1);
            }
            this.sleepStartTime = sleepEndedTimeH - totalSleepMinutes;
            str = DateUtil.format(date, DateUtil.DATE_FORMAT_DM) + " " + NumUtil.numberFormat(this.sleepStartTime / 60) + ":" + NumUtil.numberFormat(this.sleepStartTime % 60);
            str2 = str3;
        }
        this.sleepPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(str2, this.width, this.height, this.sleepPaint);
        this.sleepPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(str, 0.0f, this.height, this.sleepPaint);
        this.mDividerPaint.reset();
        this.mDividerPaint.setStyle(Paint.Style.STROKE);
        this.mDividerPaint.setAntiAlias(true);
        this.mDividerPaint.setColor(getResources().getColor(R.color.color_text_side_bar_55));
        int i = this.height;
        int i2 = this.bottomLabelHeight;
        canvas.drawLine(0.0f, i - i2, this.width, i - i2, this.mDividerPaint);
        this.mDividerPaint.setColor(getResources().getColor(R.color.color_text_side_bar_55));
    }

    private void drawNoDataView(Canvas canvas) {
        this.sleepPaint.setColor(getResources().getColor(R.color.color_F5F7FB));
        int i = this.height;
        canvas.drawRect(0.0f, i - 70, this.width, i - 20, this.sleepPaint);
    }

    private void drawSleepBar(Canvas canvas) {
        this.barHeight = (this.height - this.topLabelHeight) - this.bottomLabelHeight;
        this.sleepPaint.setTextAlign(Paint.Align.LEFT);
        if (this.isInverted) {
            drawInvertedSleepBar(canvas);
            return;
        }
        if (this.isOverlay) {
            drawOverlaySleepBar(canvas);
        } else {
            drawErectedSleepBar(canvas);
        }
        drawTopLabel(canvas);
    }

    private void drawOverlaySleepBar(Canvas canvas) {
        List<SleepDetailModel> list = this.healthSleepList;
        if (list == null || list.size() == 0) {
            return;
        }
        this.barRegionList.clear();
        this.selectedRectF = null;
        float f2 = this.barHeight;
        int i = this.mMaxValue;
        if (i == 0) {
            i = 1;
        }
        float f3 = f2 / i;
        char c2 = 0;
        int i2 = 0;
        while (i2 < this.healthSleepList.size()) {
            SleepDetailModel sleepDetailModel = this.healthSleepList.get(i2);
            if (this.hasTopLabel) {
                ArrayList<Region> arrayList = this.barRegionList;
                float f4 = this.barWidth;
                float f5 = this.barMargin;
                float f6 = i2;
                int i3 = this.mLeftWidth;
                arrayList.add(new Region(((int) (((f4 + f5) * f6) - (f5 / 2.0f))) + i3, this.topLabelHeight, ((int) (((f4 + f5) * f6) + f4 + (f5 / 2.0f))) + i3, this.height - this.bottomLabelHeight));
            }
            float f7 = this.barMargin;
            float f8 = this.barWidth;
            float f9 = i2;
            int i4 = this.mLeftWidth;
            int i5 = this.height;
            int i6 = this.bottomLabelHeight;
            RectF rectF = new RectF((f7 / 2.0f) + ((f8 + f7) * f9) + i4, i5 - i6, (f7 / 2.0f) + ((f7 + f8) * f9) + f8 + i4, i5 - i6);
            if (sleepDetailModel == null || sleepDetailModel.totalSleepMinutes == 0) {
                if (i2 == getSelectedPosition()) {
                    this.selectedRectF = rectF;
                    this.selectedRectF.top = this.height - this.bottomLabelHeight;
                    this.selectedSleep = new SleepDetailModel();
                }
            } else {
                float totalSleepMinutes = sleepDetailModel.getTotalSleepMinutes() * f3;
                float f10 = totalSleepMinutes / 100.0f;
                int iRound = Math.round((sleepDetailModel.deepSleepMinutes * 100.0f) / sleepDetailModel.totalSleepMinutes);
                int iRound2 = Math.round((sleepDetailModel.lightSleepMinutes * 100.0f) / sleepDetailModel.totalSleepMinutes);
                int iRound3 = Math.round((sleepDetailModel.eyeMovementSleepMinutes * 100.0f) / sleepDetailModel.totalSleepMinutes);
                rectF.top = (this.height - this.bottomLabelHeight) - totalSleepMinutes;
                float dimension = this.mResources.getDimension(R.dimen.sw_dp_3);
                if (((100 - iRound) - iRound2) - iRound3 > 0) {
                    this.sleepPaint.setColor(this.colors[c2]);
                    rectF.top = rectF.bottom - (this.scaleY * totalSleepMinutes);
                    rectF.top = rectF.top < rectF.bottom - (this.scaleY * totalSleepMinutes) ? rectF.bottom - (this.scaleY * totalSleepMinutes) : rectF.top;
                    if (sleepDetailModel.deepSleepMinutes + sleepDetailModel.lightSleepMinutes + sleepDetailModel.eyeMovementSleepMinutes >= sleepDetailModel.totalSleepMinutes) {
                        rectF.top = rectF.bottom - (this.scaleY * totalSleepMinutes);
                    }
                    canvas.drawRoundRect(rectF, dimension, dimension, this.sleepPaint);
                }
                if (iRound3 > 0) {
                    rectF.top = rectF.bottom - (((100 - r13) * f10) * this.scaleY);
                    this.sleepPaint.setColor(this.colors[3]);
                    canvas.drawRoundRect(rectF, dimension, dimension, this.sleepPaint);
                }
                this.sleepPaint.setColor(this.colors[1]);
                rectF.top = rectF.bottom - (((iRound2 + iRound) * f10) * this.scaleY);
                rectF.top = rectF.top < rectF.bottom - (this.scaleY * totalSleepMinutes) ? rectF.bottom - (this.scaleY * totalSleepMinutes) : rectF.top;
                if (sleepDetailModel.deepSleepMinutes + sleepDetailModel.lightSleepMinutes >= sleepDetailModel.totalSleepMinutes) {
                    rectF.top = rectF.bottom - (this.scaleY * totalSleepMinutes);
                }
                canvas.drawRoundRect(rectF, dimension, dimension, this.sleepPaint);
                this.sleepPaint.setColor(this.colors[2]);
                rectF.top = rectF.bottom - ((iRound * f10) * this.scaleY);
                rectF.top = rectF.top < rectF.bottom - (this.scaleY * totalSleepMinutes) ? rectF.bottom - (this.scaleY * totalSleepMinutes) : rectF.top;
                canvas.drawRoundRect(rectF, 0.0f, 0.0f, this.sleepPaint);
                if (i2 == getSelectedPosition() && sleepDetailModel.totalSleepMinutes != 0) {
                    this.selectedRectF = rectF;
                    this.selectedRectF.top = (this.height - this.bottomLabelHeight) - totalSleepMinutes;
                    this.selectedSleep = sleepDetailModel;
                }
            }
            i2++;
            c2 = 0;
        }
    }

    private void drawTopLabel(Canvas canvas) {
        if (this.hasTopLabel) {
            int i = this.dataTimeType;
            if (i == 0) {
                drawDaySleepStatusLabel(canvas);
            } else if (i == 1 || i == 2 || i == 3) {
                drawTotalSleepTimeLabel(canvas);
            }
        }
    }

    private void drawDaySleepStatusLabel(Canvas canvas) {
        String languageText;
        String str;
        float f2;
        int selectedPosition = getSelectedPosition();
        if (selectedPosition > this.barRegionList.size() - 1 || selectedPosition > this.sleepBarDataList.size() - 1 || selectedPosition < 0) {
            OnItemSelectListener onItemSelectListener = this.mOnItemSelectListener;
            if (onItemSelectListener != null) {
                onItemSelectListener.onItemSelected(-1);
                return;
            }
            return;
        }
        OnItemSelectListener onItemSelectListener2 = this.mOnItemSelectListener;
        if (onItemSelectListener2 != null) {
            onItemSelectListener2.onItemSelected(selectedPosition);
        }
        Rect bounds = this.barRegionList.get(selectedPosition).getBounds();
        int[] iArr = this.sleepBarDataList.get(selectedPosition);
        int i = iArr[0];
        if (i == 1) {
            languageText = LanguageUtil.getLanguageText(R.string.home_sleep_sober);
        } else if (i == 2) {
            languageText = LanguageUtil.getLanguageText(R.string.home_sleep_shallow);
        } else if (i != 3) {
            languageText = i != 4 ? "" : LanguageUtil.getLanguageText(R.string.home_sleep_eye_movement_rapid);
        } else {
            languageText = LanguageUtil.getLanguageText(R.string.home_sleep_deep);
        }
        int i2 = this.sleepStartTime + iArr[1];
        int i3 = iArr[2] + i2;
        if (i2 >= 1440) {
            i2 -= 1440;
        }
        String str2 = NumUtil.numberFormat(i2 / 60) + ":" + NumUtil.numberFormat(i2 % 60);
        if (i3 >= 1440) {
            i3 -= 1440;
        }
        String str3 = str2 + "-" + (NumUtil.numberFormat(i3 / 60) + ":" + NumUtil.numberFormat(i3 % 60));
        String strValueOf = String.valueOf(iArr[2]);
        this.sleepPaint.setTextSize(this.mResources.getDimension(R.dimen.size12sp));
        int textWidth = ScreenUtil.getTextWidth(this.sleepPaint, languageText);
        int textHeight = ScreenUtil.getTextHeight(this.sleepPaint, languageText);
        int textWidth2 = ScreenUtil.getTextWidth(this.sleepPaint, str3);
        int textHeight2 = ScreenUtil.getTextHeight(this.sleepPaint, str3);
        String string = this.mResources.getString(R.string.unit_min);
        int textWidth3 = ScreenUtil.getTextWidth(this.sleepPaint, string);
        this.sleepPaint.setTypeface(Typeface.createFromAsset(this.mResources.getAssets(), "font/MetricTest-Medium.otf"));
        this.sleepPaint.setTextSize(this.mResources.getDimension(R.dimen.size24sp));
        int textWidth4 = ScreenUtil.getTextWidth(this.sleepPaint, strValueOf);
        int textHeight3 = ScreenUtil.getTextHeight(this.sleepPaint, strValueOf);
        float dimension = this.mResources.getDimension(R.dimen.sw_dp_10);
        float f3 = textWidth;
        float f4 = textWidth2;
        float f5 = dimension / 2.0f;
        float fMax = Math.max(f3, Math.max(f4, Math.max(f4, textWidth3 + textWidth4 + f5)));
        float f6 = (bounds.left + bounds.right) / 2;
        this.sleepPaint.setTextAlign(Paint.Align.LEFT);
        float f7 = fMax / 2.0f;
        if (f7 + dimension > f6) {
            f2 = dimension;
            str = languageText;
        } else {
            float f8 = f6 + f7 + dimension;
            int i4 = this.width;
            str = languageText;
            f2 = f8 > ((float) i4) ? (i4 - fMax) - dimension : f6 - f7;
        }
        int i5 = textHeight + textHeight3;
        RectF rectF = new RectF(f2 - dimension, 0.0f, fMax + f2 + dimension, i5 + textHeight2 + (3.0f * dimension));
        this.sleepPaint.setColor(this.mResources.getColor(R.color.color_text_side_bar_55));
        canvas.drawRoundRect(rectF, dimension, dimension, this.sleepPaint);
        this.sleepPaint.setTypeface(Typeface.createFromAsset(this.mResources.getAssets(), "font/MetricTest-Medium.otf"));
        this.sleepPaint.setTextSize(this.mResources.getDimension(R.dimen.size24sp));
        this.sleepPaint.setColor(this.mResources.getColor(R.color.white));
        if (!TextUtils.isEmpty(strValueOf)) {
            canvas.drawText(strValueOf, f2, i5 + (dimension * 1.5f), this.sleepPaint);
        }
        this.sleepPaint.setTypeface(Typeface.DEFAULT);
        this.sleepPaint.setTextSize(this.mResources.getDimension(R.dimen.size12sp));
        this.sleepPaint.setColor(this.mResources.getColor(R.color.white));
        if (!TextUtils.isEmpty(string)) {
            canvas.drawText(string, textWidth4 + f2 + f5, i5 + (1.5f * dimension), this.sleepPaint);
        }
        if (!TextUtils.isEmpty(str)) {
            canvas.drawText(str, f2, textHeight + dimension, this.sleepPaint);
        }
        if (!TextUtils.isEmpty(str3)) {
            canvas.drawText(str3, f2, textHeight + textHeight2 + textHeight3 + (dimension * 2.0f), this.sleepPaint);
        }
        this.mDividerPaint.setStrokeWidth(this.mResources.getDimension(R.dimen.sw_dp_1));
        this.mDividerPaint.setColor(getResources().getColor(R.color.color_text_side_bar_55));
        canvas.drawLine(f6, rectF.bottom, f6, bounds.top, this.mDividerPaint);
    }

    private void drawYearSleepLabel(Canvas canvas) {
        int i;
        int i2;
        float f2;
        if (this.selectedRectF == null) {
            OnItemSelectListener onItemSelectListener = this.mOnItemSelectListener;
            if (onItemSelectListener != null) {
                onItemSelectListener.onItemSelected(-1);
                return;
            }
            return;
        }
        if (this.selectedSleep == null) {
            OnItemSelectListener onItemSelectListener2 = this.mOnItemSelectListener;
            if (onItemSelectListener2 != null) {
                onItemSelectListener2.onItemSelected(-1);
                return;
            }
            return;
        }
        OnItemSelectListener onItemSelectListener3 = this.mOnItemSelectListener;
        if (onItemSelectListener3 != null) {
            onItemSelectListener3.onItemSelected(this.mSelected);
        }
        int i3 = (this.selectedSleep.deepSleepMinutes * 100) / this.selectedSleep.totalSleepMinutes;
        String strValueOf = String.valueOf(i3);
        int i4 = (this.selectedSleep.lightSleepMinutes * 100) / this.selectedSleep.totalSleepMinutes;
        String strValueOf2 = String.valueOf(i4);
        String strValueOf3 = String.valueOf((100 - i3) - i4);
        this.sleepPaint.setTextSize(this.mResources.getDimension(R.dimen.size12sp));
        String str = this.mResources.getString(R.string.home_sleep_deepsleep) + ":";
        int textWidth = ScreenUtil.getTextWidth(this.sleepPaint, str);
        int textHeight = ScreenUtil.getTextHeight(this.sleepPaint, str);
        String str2 = this.mResources.getString(R.string.home_sleep_shallowsleep) + ":";
        int textWidth2 = ScreenUtil.getTextWidth(this.sleepPaint, str2);
        String str3 = this.mResources.getString(R.string.home_sleep_sober) + ":";
        int textWidth3 = ScreenUtil.getTextWidth(this.sleepPaint, str3);
        String timeStr = getTimeStr();
        int textWidth4 = ScreenUtil.getTextWidth(this.sleepPaint, timeStr);
        int textWidth5 = ScreenUtil.getTextWidth(this.sleepPaint, "%");
        float dimension = this.mResources.getDimension(R.dimen.sw_dp_10);
        this.sleepPaint.setTypeface(Typeface.createFromAsset(this.mResources.getAssets(), "font/MetricTest-Medium.otf"));
        this.sleepPaint.setTextSize(this.mResources.getDimension(R.dimen.size24sp));
        int textWidth6 = ScreenUtil.getTextWidth(this.sleepPaint, strValueOf);
        int textWidth7 = ScreenUtil.getTextWidth(this.sleepPaint, strValueOf2);
        int textWidth8 = ScreenUtil.getTextWidth(this.sleepPaint, strValueOf3);
        int textHeight2 = ScreenUtil.getTextHeight(this.sleepPaint, strValueOf);
        this.sleepPaint.setTypeface(Typeface.DEFAULT);
        int i5 = (int) (textWidth + textWidth6 + textWidth5 + dimension);
        float f3 = i5;
        float f4 = textWidth2 + textWidth7 + textWidth5 + dimension;
        int i6 = f3 < f4 ? (int) f4 : i5;
        float f5 = textWidth3 + textWidth8 + textWidth5 + dimension;
        if (i6 < f5) {
            i6 = (int) f5;
        }
        if (i6 >= textWidth4) {
            textWidth4 = i6;
        }
        float f6 = (this.selectedRectF.left + this.selectedRectF.right) / 2.0f;
        this.sleepPaint.setTextAlign(Paint.Align.LEFT);
        float f7 = textWidth4;
        float f8 = f7 / 2.0f;
        if (f8 + dimension > f6) {
            f2 = dimension;
            i2 = textWidth3;
            i = textWidth8;
        } else {
            i = textWidth8;
            i2 = textWidth3;
            f2 = (f6 + f8) + dimension > ((float) this.width) ? (r15 - textWidth4) - dimension : f6 - f8;
        }
        float f9 = 2.0f * dimension;
        int i7 = textHeight2 * 3;
        float f10 = textHeight + i7;
        RectF rectF = new RectF(f2 - dimension, 0.0f, f7 + f2 + f9, f10 + (3.5f * dimension));
        this.sleepPaint.setColor(this.mResources.getColor(R.color.color_text_side_bar_55));
        canvas.drawRoundRect(rectF, dimension, dimension, this.sleepPaint);
        this.sleepPaint.setTextSize(this.mResources.getDimension(R.dimen.size12sp));
        this.sleepPaint.setColor(this.mResources.getColor(R.color.white));
        float f11 = textHeight2 + dimension;
        canvas.drawText(str, f2, f11, this.sleepPaint);
        float f12 = textWidth + f2;
        canvas.drawText("%", textWidth6 + f12 + dimension, f11, this.sleepPaint);
        float f13 = (textHeight2 * 2) + (dimension * 1.5f);
        canvas.drawText(str2, f2, f13, this.sleepPaint);
        float f14 = textWidth2 + f2;
        canvas.drawText("%", textWidth7 + f14 + dimension, f13, this.sleepPaint);
        float f15 = i7 + f9;
        canvas.drawText(str3, f2, f15, this.sleepPaint);
        float f16 = i2 + f2;
        canvas.drawText("%", i + f16 + dimension, f15, this.sleepPaint);
        canvas.drawText(timeStr, f2, f10 + (2.5f * dimension), this.sleepPaint);
        this.sleepPaint.setTypeface(Typeface.createFromAsset(this.mResources.getAssets(), "font/MetricTest-Medium.otf"));
        this.sleepPaint.setTextSize(this.mResources.getDimension(R.dimen.size24sp));
        this.sleepPaint.setColor(this.mResources.getColor(R.color.white));
        float f17 = dimension * 0.5f;
        canvas.drawText(strValueOf, f12 + f17, f11, this.sleepPaint);
        canvas.drawText(strValueOf2, f14 + f17, f13, this.sleepPaint);
        canvas.drawText(strValueOf3, f16 + f17, f15, this.sleepPaint);
        this.sleepPaint.setTypeface(Typeface.DEFAULT);
        this.mDividerPaint.setStrokeWidth(this.mResources.getDimension(R.dimen.sw_dp_1) * 1.5f);
        canvas.drawLine(f6, rectF.bottom, f6, this.topLabelHeight, this.mDividerPaint);
    }

    private void drawTotalSleepTimeLabel(Canvas canvas) {
        float fMax;
        int i;
        float f2;
        float f3;
        RectF rectF;
        int i2;
        if (this.selectedRectF == null) {
            OnItemSelectListener onItemSelectListener = this.mOnItemSelectListener;
            if (onItemSelectListener != null) {
                onItemSelectListener.onItemSelected(-1);
                return;
            }
            return;
        }
        if (this.selectedSleep == null) {
            OnItemSelectListener onItemSelectListener2 = this.mOnItemSelectListener;
            if (onItemSelectListener2 != null) {
                onItemSelectListener2.onItemSelected(-1);
                return;
            }
            return;
        }
        OnItemSelectListener onItemSelectListener3 = this.mOnItemSelectListener;
        if (onItemSelectListener3 != null) {
            onItemSelectListener3.onItemSelected(this.mSelected);
        }
        int i3 = this.selectedSleep.totalSleepMinutes;
        this.sleepPaint.setTextSize(this.mResources.getDimension(R.dimen.size12sp));
        String string = this.mResources.getString(R.string.detail_average_daily);
        int textWidth = ScreenUtil.getTextWidth(this.sleepPaint, string);
        int textHeight = ScreenUtil.getTextHeight(this.sleepPaint, string);
        String string2 = this.mResources.getString(R.string.public_time_hour);
        String string3 = this.mResources.getString(R.string.unit_min);
        int textWidth2 = ScreenUtil.getTextWidth(this.sleepPaint, string2);
        int textWidth3 = ScreenUtil.getTextWidth(this.sleepPaint, string3);
        this.sleepPaint.setTypeface(Typeface.createFromAsset(this.mResources.getAssets(), "font/MetricTest-Medium.otf"));
        this.sleepPaint.setTextSize(this.mResources.getDimension(R.dimen.size24sp));
        int i4 = i3 / 60;
        String strValueOf = String.valueOf(i4);
        String str = "";
        if (i4 == 0) {
            string2 = "";
            strValueOf = string2;
        }
        int i5 = i3 % 60;
        String strValueOf2 = String.valueOf(i5);
        if (i5 == 0) {
            string3 = "";
        } else {
            str = strValueOf2;
        }
        if (i3 == 0) {
            strValueOf = AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE;
        }
        String timeStr = getTimeStr();
        Paint paint = this.sleepPaint;
        String str2 = string3;
        int textWidth4 = (int) (ScreenUtil.getTextWidth(paint, strValueOf + str) + this.mResources.getDimension(R.dimen.sw_dp_10));
        String str3 = string2;
        int textWidth5 = (int) (((float) ScreenUtil.getTextWidth(this.sleepPaint, strValueOf)) + this.mResources.getDimension(R.dimen.sw_dp_5));
        ScreenUtil.getTextWidth(this.sleepPaint, str);
        this.mResources.getDimension(R.dimen.sw_dp_5);
        int textWidth6 = ScreenUtil.getTextWidth(this.sleepPaint, timeStr);
        int textHeight2 = ScreenUtil.getTextHeight(this.sleepPaint, strValueOf);
        this.sleepPaint.setTypeface(Typeface.DEFAULT);
        float dimension = this.mResources.getDimension(R.dimen.sw_dp_10);
        String str4 = str;
        if (this.dataTimeType == 3) {
            fMax = Math.max(textWidth6, Math.max(textWidth, textWidth4 + dimension + textWidth2 + textWidth3));
        } else {
            fMax = Math.max(textWidth6, textWidth4 + dimension + textWidth2 + textWidth3);
        }
        float f4 = (this.selectedRectF.left + this.selectedRectF.right) / 2.0f;
        this.sleepPaint.setTextAlign(Paint.Align.LEFT);
        float f5 = fMax / 2.0f;
        if (f5 + dimension > f4) {
            i = textWidth4;
            f2 = dimension;
        } else {
            float f6 = dimension / 2.0f;
            float f7 = f4 + f5 + f6;
            int i6 = this.width;
            i = textWidth4;
            f2 = f7 > ((float) i6) ? (i6 - fMax) - f6 : f4 - f5;
        }
        if (this.dataTimeType == 3) {
            f3 = f4;
            rectF = new RectF(f2 - dimension, 0.0f, fMax + f2 + (dimension / 2.0f), textHeight2 + (textHeight * 2) + (3.0f * dimension));
        } else {
            f3 = f4;
            rectF = new RectF(f2 - dimension, 0.0f, fMax + f2 + (dimension / 2.0f), textHeight2 + textHeight + (2.5f * dimension));
        }
        this.sleepPaint.setColor(this.mResources.getColor(R.color.color_text_side_bar_55));
        canvas.drawRoundRect(rectF, dimension, dimension, this.sleepPaint);
        if (this.dataTimeType == 3) {
            this.sleepPaint.setTextSize(this.mResources.getDimension(R.dimen.size12sp));
            this.sleepPaint.setColor(this.mResources.getColor(R.color.white));
            if (!TextUtils.isEmpty(string)) {
                canvas.drawText(string, f2, textHeight + dimension, this.sleepPaint);
            }
        }
        this.sleepPaint.setTypeface(Typeface.createFromAsset(this.mResources.getAssets(), "font/MetricTest-Medium.otf"));
        this.sleepPaint.setTextSize(this.mResources.getDimension(R.dimen.size24sp));
        this.sleepPaint.setColor(this.mResources.getColor(R.color.white));
        if (!TextUtils.isEmpty(strValueOf)) {
            if (this.dataTimeType == 3) {
                canvas.drawText(strValueOf, f2, textHeight + textHeight2 + (dimension * 1.5f), this.sleepPaint);
            } else {
                canvas.drawText(strValueOf, f2, textHeight2 + dimension, this.sleepPaint);
            }
        }
        if (i3 > 0) {
            if (TextUtils.isEmpty(str4)) {
                i2 = textWidth5;
            } else if (this.dataTimeType == 3) {
                i2 = textWidth5;
                canvas.drawText(str4, i2 + f2 + textWidth2 + (dimension / 2.0f), textHeight + textHeight2 + (dimension * 1.5f), this.sleepPaint);
            } else {
                i2 = textWidth5;
                canvas.drawText(str4, i2 + f2 + textWidth2 + (dimension / 2.0f), textHeight2 + dimension, this.sleepPaint);
            }
            this.sleepPaint.setTypeface(Typeface.DEFAULT);
            this.sleepPaint.setTextSize(this.mResources.getDimension(R.dimen.size12sp));
            this.sleepPaint.setColor(this.mResources.getColor(R.color.white));
            if (!TextUtils.isEmpty(str3)) {
                if (this.dataTimeType == 3) {
                    canvas.drawText(str3, i2 + f2, textHeight + textHeight2 + (dimension * 1.5f), this.sleepPaint);
                } else {
                    canvas.drawText(str3, i2 + f2, textHeight2 + dimension, this.sleepPaint);
                }
            }
            if (!TextUtils.isEmpty(str2)) {
                if (this.dataTimeType == 3) {
                    canvas.drawText(str2, i + f2 + textWidth2 + (dimension / 2.0f), textHeight + textHeight2 + (1.5f * dimension), this.sleepPaint);
                } else {
                    canvas.drawText(str2, i + f2 + textWidth2 + (dimension / 2.0f), textHeight2 + dimension, this.sleepPaint);
                }
            }
        }
        this.sleepPaint.setTypeface(Typeface.DEFAULT);
        this.sleepPaint.setTextSize(this.mResources.getDimension(R.dimen.size12sp));
        this.sleepPaint.setColor(this.mResources.getColor(R.color.white));
        if (!TextUtils.isEmpty(timeStr)) {
            if (this.dataTimeType == 3) {
                canvas.drawText(timeStr, f2, (textHeight * 2) + textHeight2 + (dimension * 2.0f), this.sleepPaint);
            } else {
                canvas.drawText(timeStr, f2, textHeight + textHeight2 + (dimension * 1.8f), this.sleepPaint);
            }
        }
        this.mDividerPaint.setStrokeWidth(this.mResources.getDimension(R.dimen.sw_dp_1));
        this.mDividerPaint.setColor(getResources().getColor(R.color.color_text_side_bar_55));
        canvas.drawLine(f3, rectF.bottom, f3, this.selectedRectF.top, this.mDividerPaint);
    }

    private void drawInvertedSleepBar(Canvas canvas) {
        if (this.sleepData == null) {
            return;
        }
        float totalSleepMinutes = this.width / r1.getTotalSleepMinutes();
        int offsetMinute = 0;
        for (int i = 0; i < this.sleepItemList.size(); i++) {
            this.sleepPaint.setShader(null);
            this.sleepPaint.setAlpha(255);
            HealthSleepItem healthSleepItem = this.sleepItemList.get(i);
            if (healthSleepItem.getSleepStatus() == 1) {
                this.sleepPaint.setColor(this.colors[0]);
            } else if (healthSleepItem.getSleepStatus() == 2) {
                this.sleepPaint.setColor(this.colors[1]);
            } else if (healthSleepItem.getSleepStatus() == 3) {
                this.sleepPaint.setColor(this.colors[2]);
            }
            this.sleepPaint.setStrokeWidth(healthSleepItem.getOffsetMinute() * totalSleepMinutes);
            if (i == 0) {
                offsetMinute += (int) ((healthSleepItem.getOffsetMinute() * totalSleepMinutes) / 2.0f);
            } else {
                offsetMinute = (int) (offsetMinute + ((this.sleepItemList.get(i - 1).getOffsetMinute() * totalSleepMinutes) / 2.0f) + ((healthSleepItem.getOffsetMinute() * totalSleepMinutes) / 2.0f));
            }
            float f2 = offsetMinute;
            canvas.drawLine(f2, 0.0f, f2, this.topLineHeight, this.sleepPaint);
            this.sleepPaint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, getHeight(), this.sleepPaint.getColor(), ViewCompat.MEASURED_SIZE_MASK, Shader.TileMode.MIRROR));
            this.sleepPaint.setAlpha(180);
            canvas.drawLine(f2, this.topLineHeight, f2, this.height - this.bottomLabelHeight, this.sleepPaint);
        }
    }

    private void drawErectedSleepBar(Canvas canvas) {
        char c2;
        if (this.sleepData == null) {
            return;
        }
        float dimension = this.mResources.getDimension(R.dimen.sw_dp_10);
        this.mDaySleepBarHeight = (this.barHeight - dimension) / 6.0f;
        float totalSleepMinutes = this.width / this.sleepData.getTotalSleepMinutes();
        this.mLastRectF = null;
        this.barRegionList.clear();
        this.sleepBarDataList.clear();
        this.sleepPaint.setShader(null);
        int i = (int) ((this.height - this.bottomLabelHeight) - dimension);
        char c3 = 0;
        float f2 = 0.0f;
        int i2 = 0;
        int offsetMinute = 0;
        int sleepStatus = 0;
        int i3 = 0;
        while (i2 < this.sleepItemList.size()) {
            HealthSleepItem healthSleepItem = this.sleepItemList.get(i2);
            if (i2 == 0) {
                sleepStatus = healthSleepItem.getSleepStatus();
            }
            offsetMinute += healthSleepItem.getOffsetMinute();
            if (i2 < this.sleepItemList.size() - 1 && healthSleepItem.getSleepStatus() == sleepStatus) {
                i3 += healthSleepItem.offsetMinute;
            } else {
                RectF rectF = new RectF();
                rectF.left = f2;
                if (i2 == this.sleepItemList.size() - 1) {
                    if (healthSleepItem.getSleepStatus() == sleepStatus) {
                        i3 += healthSleepItem.offsetMinute;
                        if (i3 > 0) {
                            rectF.right = ((int) (i3 * totalSleepMinutes)) + f2;
                            drawBar(canvas, i, sleepStatus, rectF);
                            List<int[]> list = this.sleepBarDataList;
                            int[] iArr = new int[3];
                            iArr[c3] = sleepStatus;
                            iArr[1] = offsetMinute - i3;
                            iArr[2] = i3;
                            list.add(iArr);
                        }
                    } else {
                        if (i3 > 0) {
                            rectF.right = ((int) (i3 * totalSleepMinutes)) + f2;
                            drawBar(canvas, i, sleepStatus, rectF);
                            List<int[]> list2 = this.sleepBarDataList;
                            int[] iArr2 = new int[3];
                            iArr2[c3] = sleepStatus;
                            iArr2[1] = (offsetMinute - i3) - healthSleepItem.offsetMinute;
                            iArr2[2] = i3;
                            list2.add(iArr2);
                            f2 = rectF.right;
                            rectF.left = f2;
                        }
                        int i4 = healthSleepItem.offsetMinute;
                        sleepStatus = healthSleepItem.getSleepStatus();
                        if (i4 > 0) {
                            rectF.right = ((int) (i4 * totalSleepMinutes)) + f2;
                            drawBar(canvas, i, sleepStatus, rectF);
                            this.sleepBarDataList.add(new int[]{sleepStatus, offsetMinute - i4, i4});
                        }
                        i3 = i4;
                        c2 = 0;
                    }
                } else {
                    if (i3 > 0) {
                        rectF.right = ((int) (i3 * totalSleepMinutes)) + f2;
                        drawBar(canvas, i, sleepStatus, rectF);
                        c2 = 0;
                        this.sleepBarDataList.add(new int[]{sleepStatus, (offsetMinute - i3) - healthSleepItem.offsetMinute, i3});
                        f2 = rectF.right;
                    } else {
                        c2 = 0;
                    }
                    int i5 = healthSleepItem.offsetMinute;
                    sleepStatus = healthSleepItem.getSleepStatus();
                    i3 = i5;
                }
                i2++;
                c3 = c2;
            }
            c2 = c3;
            i2++;
            c3 = c2;
        }
    }

    private void drawBar(Canvas canvas, int i, int i2, RectF rectF) {
        RectF rectF2;
        float f2;
        float f3;
        float f4 = rectF.right;
        int i3 = this.width;
        if (f4 > i3) {
            rectF.right = i3;
        }
        if (i2 == 1) {
            this.sleepPaint.setColor(this.colors[0]);
            float f5 = i;
            float f6 = this.mDaySleepBarHeight;
            float f7 = this.scaleY;
            rectF.top = f5 - ((5.5f * f6) * f7);
            rectF.bottom = f5 - ((f6 * 4.5f) * f7);
        } else if (i2 == 2) {
            this.sleepPaint.setColor(this.colors[1]);
            float f8 = i;
            float f9 = this.mDaySleepBarHeight;
            float f10 = this.scaleY;
            rectF.top = f8 - ((2.5f * f9) * f10);
            rectF.bottom = f8 - ((f9 * 1.5f) * f10);
        } else if (i2 == 3) {
            this.sleepPaint.setColor(this.colors[2]);
            float f11 = i;
            float f12 = this.mDaySleepBarHeight;
            float f13 = this.scaleY;
            rectF.top = f11 - ((f12 * f13) * f13);
            rectF.bottom = f11;
        } else {
            this.sleepPaint.setColor(this.colors[3]);
            float f14 = i;
            float f15 = this.mDaySleepBarHeight;
            float f16 = this.scaleY;
            rectF.top = f14 - ((f15 * 4.0f) * f16);
            rectF.bottom = f14 - ((f15 * 3.0f) * f16);
        }
        float dimension = this.mResources.getDimension(R.dimen.sw_dp_3);
        canvas.drawRoundRect(rectF, dimension, dimension, this.sleepPaint);
        if (this.scaleY == 1.0f && (rectF2 = this.mLastRectF) != null) {
            float f17 = dimension / 4.0f;
            float f18 = rectF2.right - f17;
            float f19 = rectF.left + f17;
            if (this.mLastRectF.top < rectF.top) {
                f2 = this.mLastRectF.bottom - f17;
                f3 = rectF.top + f17;
            } else {
                f2 = this.mLastRectF.top + f17;
                f3 = rectF.bottom - f17;
            }
            float f20 = f2;
            float f21 = f3;
            this.sleepPaint.setShader(new LinearGradient(f18, f20, f19, f21, new int[]{this.mLastBarColor, this.sleepPaint.getColor()}, (float[]) null, Shader.TileMode.MIRROR));
            canvas.drawLine(f18, f20, f19, f21, this.sleepPaint);
            this.sleepPaint.setShader(null);
        }
        this.mLastRectF = new RectF(rectF);
        this.mLastBarColor = this.sleepPaint.getColor();
        if (this.hasTopLabel) {
            this.barRegionList.add(new Region((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom));
        }
    }

    public void setDatas(HealthSleep healthSleep, List<HealthSleepItem> list) {
        this.sleepData = healthSleep;
        this.sleepItemList = list;
        setSelectedPosition(-1);
        if (healthSleep != null) {
            this.sleepTimes[0] = healthSleep.getTotalSleepMinutes() / 60;
            this.sleepTimes[1] = healthSleep.getTotalSleepMinutes() % 60;
        } else {
            this.sleepData = new HealthSleep();
            int[] iArr = this.sleepTimes;
            iArr[0] = 0;
            iArr[1] = 0;
        }
        if (this.sleepItemList == null) {
            this.sleepItemList = new ArrayList();
        }
        animateY();
        invalidate();
    }

    public int getDataTimeType() {
        return this.dataTimeType;
    }

    public Date getFirstDateOfSelected() {
        return this.firstDateOfSelected;
    }

    public void setFirstDateOfSelected(Date date) {
        this.firstDateOfSelected = date;
    }

    public int getSelectedYear() {
        return this.selectedYear;
    }

    public void setSelectedYear(int i) {
        this.selectedYear = i;
    }

    public void setDataTimeType(int i) {
        this.dataTimeType = i;
    }

    public void setBarAndMarginWidthWeight(int i, int i2) {
        this.barWeight = i;
        this.marginWeight = i2;
    }

    public boolean isOverlay() {
        return this.isOverlay;
    }

    public void setOverlay(boolean z) {
        this.isOverlay = z;
    }

    public HealthSleep getSleepData() {
        return this.sleepData;
    }

    private void animateY() {
        if (this.mAnimatorY == null) {
            this.mAnimatorY = ValueAnimator.ofFloat(0.0f, 1.0f);
        }
        this.mAnimatorY.cancel();
        this.mAnimatorY.setDuration(300L);
        this.mAnimatorY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.life.customview.charter.-$$Lambda$SleepBarChart$_9vajrw9NufprDJ73FUNEyl_1dg
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f$0.lambda$animateY$0$SleepBarChart(valueAnimator);
            }
        });
        this.mAnimatorY.start();
    }

    public /* synthetic */ void lambda$animateY$0$SleepBarChart(ValueAnimator valueAnimator) {
        this.scaleY = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        invalidate();
    }

    public void setColors(int[] iArr) {
        this.colors = iArr;
    }

    public void setDatas(List<SleepDetailModel> list) {
        this.isOverlay = true;
        this.healthSleepList = list;
        getMaxValue(list);
        setSelectedPosition(-1);
        animateY();
        invalidate();
    }

    public void setDatasNoAnimator(List<SleepDetailModel> list) {
        this.isOverlay = true;
        this.healthSleepList = list;
        getMaxValue(list);
        setSelectedPosition(-1);
        invalidate();
    }

    private void getMaxValue(List<SleepDetailModel> list) {
        this.mMaxValue = 0;
        if (list == null || list.size() <= 0) {
            return;
        }
        for (SleepDetailModel sleepDetailModel : list) {
            if (sleepDetailModel != null) {
                this.mMaxValue = Math.max(this.mMaxValue, sleepDetailModel.getTotalSleepMinutes());
            }
        }
    }

    public void setBottomLabels(String[] strArr) {
        this.bottomLabels = strArr;
    }

    public void setSelectedPosition(int i) {
        this.mSelected = i;
    }

    public int getSelectedPosition() {
        return this.mSelected;
    }

    private String getTimeStr() {
        int i;
        int selectedPosition = getSelectedPosition();
        if (selectedPosition == -1 || (i = this.dataTimeType) == 0) {
            return "";
        }
        if (i == 1 || i == 2) {
            return DateUtil.getFormatDate(DateUtil.DATE_FORMAT_DM_1, DateUtil.getSpecifiedDayBefore(this.firstDateOfSelected, -selectedPosition));
        }
        if (i != 3) {
            return "";
        }
        return NumUtil.numberFormat(selectedPosition + 1) + "/" + this.selectedYear;
    }

    private int checkBarIndex(float f2, float f3) {
        if (this.barRegionList == null) {
            return -1;
        }
        for (int i = 0; i < this.barRegionList.size(); i++) {
            Rect bounds = this.barRegionList.get(i).getBounds();
            bounds.top = this.topLabelHeight;
            bounds.bottom = this.height - this.bottomLabelHeight;
            if (bounds.contains((int) f2, (int) f3)) {
                return i;
            }
        }
        return -1;
    }

    public void resetSelectedBar() {
        if (this.mSelected != -1) {
            this.mSelected = -1;
            invalidate();
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int iCheckBarIndex;
        if (this.isInverted) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            if (this.hasTopLabel && this.mSelected != (iCheckBarIndex = checkBarIndex(motionEvent.getX(), motionEvent.getY()))) {
                this.mSelected = iCheckBarIndex;
                invalidate();
            }
        } else if (action == 1) {
            reset();
            CommonLogUtil.d("ACTION_UP isMove=" + this.isMoving);
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
            CommonLogUtil.d("xVelocity====" + iAbs + " isMove=" + this.isMoving + " yVelocity=" + iAbs2);
            if (this.hasTopLabel) {
                this.mSelected = iCheckBarIndex;
                invalidate();
            }
        } else if (action == 3) {
            reset();
            releaseVelocityTracker();
            CommonLogUtil.d("ACTION_CANCEL isMove=" + this.isMoving);
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

    public void setOnItemSelectListener(OnItemSelectListener onItemSelectListener) {
        this.mOnItemSelectListener = onItemSelectListener;
    }
}