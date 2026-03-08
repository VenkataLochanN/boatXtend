package com.ido.life.customview.charter;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.Shader;
import android.os.Build;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.google.android.material.timepicker.TimeModel;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.ble.data.manage.database.HealthHeartRateItem;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.NumUtil;
import com.ido.life.R;
import com.ido.life.util.DateUtil;
import com.ido.life.util.ViewUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class HeartRateLineGraph extends DetailBaseView implements View.OnTouchListener {
    private static final long DEFAULT_DURATION = 500;
    private static final String TAG = "HeartRateLineGraph";
    private int MAX_VALUE;
    private int MIDDLE_VALUE;
    private int MIN_VALUE;
    private int barColor;
    private float bottomY;
    private float chartWidth;
    private float chartWidthSpan;
    private TouchOrClickListener clickListener;
    private List<HealthHeartRateItem> datas;
    PathEffect effects;
    private boolean fillAreaHasAnim;
    private Paint fillPaint;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f4692h;
    private boolean hasHighest;
    private boolean hasNoDataTip;
    private boolean hasYValue;
    private boolean isFillArea;
    private boolean isInit;
    private boolean isMeasure;
    private boolean isMove;
    private boolean isOver;
    private boolean isSleepHr;
    private List<String> labelList;
    private TextPaint labelXPaint;
    private TextPaint labelYPaint;
    private float labelYWidth;
    private float[] lineHeight;
    private Paint linePaint;
    private float mAnimatorValue;
    Calendar mCalendar;
    private PaintFlagsDrawFilter mFilter;
    private PathMeasure mPathMeasure;
    private ValueAnimator.AnimatorUpdateListener mUpdateListener;
    private Paint maxYSeparatePaint;
    private Paint noDataPaint;
    private String noDataValue;
    private Path path;
    private boolean playAnim;
    private Paint pointPaint;
    private Point[] points;
    private int[] scaleY;
    private int selPosition;
    private Paint selectTextPaint;
    SimpleDateFormat simpleDateFormat;
    private int splitLineColor;
    private int splitNum;
    private int textColor;
    private float textSize;
    float touchX;
    private float touchX2;
    float touchY;
    private int type;
    private ValueAnimator valueAnimator;
    private int w;
    private Paint xSeparatePaint;
    private int yValueColor;

    public interface TouchOrClickListener {
        void doClick();
    }

    public HeartRateLineGraph(Context context) {
        this(context, null);
    }

    public HeartRateLineGraph(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HeartRateLineGraph(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.splitNum = 5;
        this.lineHeight = new float[this.splitNum];
        this.MIN_VALUE = 0;
        this.linePaint = new Paint();
        this.fillPaint = new Paint();
        this.noDataPaint = new Paint();
        this.xSeparatePaint = new Paint();
        this.maxYSeparatePaint = new Paint();
        this.pointPaint = new Paint();
        this.labelXPaint = new TextPaint();
        this.labelYPaint = new TextPaint();
        this.path = new Path();
        this.playAnim = true;
        this.isOver = false;
        this.isFillArea = true;
        this.fillAreaHasAnim = true;
        this.datas = new ArrayList();
        this.touchX = -1.0f;
        this.touchY = -1.0f;
        this.isMeasure = false;
        this.isInit = false;
        this.labelList = new ArrayList();
        this.effects = new DashPathEffect(new float[]{10.0f, 10.0f}, 0.0f);
        this.isSleepHr = false;
        this.simpleDateFormat = new SimpleDateFormat(DateUtil.DATE_FORMAT_YMD);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SportBarChart);
        this.textSize = typedArrayObtainStyledAttributes.getDimension(0, DipPixelUtil.dip2pxF(8.0f));
        this.textColor = typedArrayObtainStyledAttributes.getColor(1, -6710887);
        this.barColor = typedArrayObtainStyledAttributes.getColor(2, -1092544);
        this.splitLineColor = typedArrayObtainStyledAttributes.getColor(10, 536870912);
        this.yValueColor = typedArrayObtainStyledAttributes.getColor(11, -6710887);
        this.hasNoDataTip = typedArrayObtainStyledAttributes.getBoolean(8, false);
        this.noDataValue = typedArrayObtainStyledAttributes.getString(9);
        this.hasYValue = typedArrayObtainStyledAttributes.getBoolean(7, false);
        this.hasHighest = typedArrayObtainStyledAttributes.getBoolean(5, true);
        typedArrayObtainStyledAttributes.recycle();
        init();
        if (Build.VERSION.SDK_INT < 20) {
            setLayerType(1, null);
        }
    }

    private void init() {
        this.linePaint.setAntiAlias(true);
        this.linePaint.setStyle(Paint.Style.STROKE);
        this.linePaint.setStrokeJoin(Paint.Join.ROUND);
        this.linePaint.setStrokeCap(Paint.Cap.ROUND);
        this.linePaint.setDither(true);
        this.linePaint.setShader(null);
        this.linePaint.setStrokeWidth(TypedValue.applyDimension(1, 2.0f, getResources().getDisplayMetrics()));
        this.linePaint.setColor(getResources().getColor(com.boat.Xtend.two.R.color.color_FA2E3E));
        this.fillPaint.setAntiAlias(true);
        this.fillPaint.setStyle(Paint.Style.FILL);
        this.fillPaint.setStrokeJoin(Paint.Join.ROUND);
        this.fillPaint.setStrokeCap(Paint.Cap.ROUND);
        this.fillPaint.setDither(true);
        this.fillPaint.setStrokeWidth(TypedValue.applyDimension(1, 3.0f, getResources().getDisplayMetrics()));
        this.fillPaint.setColor(getResources().getColor(com.boat.Xtend.two.R.color.color_FA2E3E));
        this.fillPaint.setAlpha(180);
        this.pointPaint.setAntiAlias(true);
        this.pointPaint.setStyle(Paint.Style.FILL);
        this.pointPaint.setStrokeJoin(Paint.Join.ROUND);
        this.pointPaint.setStrokeCap(Paint.Cap.ROUND);
        this.pointPaint.setDither(true);
        this.pointPaint.setStrokeWidth(TypedValue.applyDimension(1, 1.0f, getResources().getDisplayMetrics()));
        this.pointPaint.setColor(getResources().getColor(com.boat.Xtend.two.R.color.color_FA2E3E));
        this.xSeparatePaint.setAntiAlias(true);
        this.xSeparatePaint.setStyle(Paint.Style.STROKE);
        this.xSeparatePaint.setStrokeJoin(Paint.Join.ROUND);
        this.xSeparatePaint.setStrokeCap(Paint.Cap.ROUND);
        this.xSeparatePaint.setDither(true);
        this.xSeparatePaint.setStrokeWidth(TypedValue.applyDimension(1, 0.5f, getResources().getDisplayMetrics()));
        this.xSeparatePaint.setColor(this.splitLineColor);
        this.xSeparatePaint.setPathEffect(this.effects);
        this.maxYSeparatePaint.setAntiAlias(true);
        this.maxYSeparatePaint.setStyle(Paint.Style.STROKE);
        this.maxYSeparatePaint.setStrokeJoin(Paint.Join.ROUND);
        this.maxYSeparatePaint.setStrokeCap(Paint.Cap.ROUND);
        this.maxYSeparatePaint.setDither(true);
        this.maxYSeparatePaint.setStrokeWidth(TypedValue.applyDimension(1, 0.5f, getResources().getDisplayMetrics()));
        this.maxYSeparatePaint.setColor(getResources().getColor(com.boat.Xtend.two.R.color.color_FA2E3E));
        this.maxYSeparatePaint.setPathEffect(this.effects);
        this.noDataPaint.setAntiAlias(true);
        this.noDataPaint.setStyle(Paint.Style.FILL);
        this.noDataPaint.setStrokeJoin(Paint.Join.ROUND);
        this.noDataPaint.setStrokeCap(Paint.Cap.ROUND);
        this.noDataPaint.setDither(true);
        this.noDataPaint.setStrokeWidth(TypedValue.applyDimension(1, 3.0f, getResources().getDisplayMetrics()));
        this.noDataPaint.setColor(getResources().getColor(com.boat.Xtend.two.R.color.color_FFEEEF));
        this.labelXPaint.setAntiAlias(true);
        this.labelXPaint.setColor(this.textColor);
        this.labelXPaint.setTextAlign(Paint.Align.CENTER);
        this.labelXPaint.setTextSize(this.textSize);
        this.labelYPaint.setAntiAlias(true);
        this.labelYPaint.setColor(this.yValueColor);
        this.labelYPaint.setTextAlign(Paint.Align.RIGHT);
        this.labelYPaint.setTextSize(this.textSize);
        if (this.hasYValue) {
            this.labelYWidth = ViewUtil.getTextRectWidth(this.labelYPaint, "100");
        }
        this.selectTextPaint = new Paint();
        this.selectTextPaint.setAntiAlias(true);
        this.selectTextPaint.setColor(getResources().getColor(com.boat.Xtend.two.R.color.text_gray));
        this.selectTextPaint.setTextSize(this.textSize);
        this.selectTextPaint.setStyle(Paint.Style.FILL);
        this.mFilter = new PaintFlagsDrawFilter(0, 3);
        setCanTouch(false);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.w = i;
        this.f4692h = i2;
        calculate();
    }

    private void calculate() {
        int i = 0;
        while (true) {
            if (i >= this.splitNum) {
                break;
            }
            this.lineHeight[i] = (this.f4692h / r1) * i;
            i++;
        }
        this.chartWidth = this.w - this.labelYWidth;
        if ((this.isSleepHr || this.type != 0) && this.datas.size() > 0) {
            float f2 = this.chartWidth;
            List<HealthHeartRateItem> list = this.datas;
            this.chartWidthSpan = f2 / list.get(list.size() - 1).getOffsetMinute();
        } else {
            this.chartWidthSpan = this.chartWidth / 1440.0f;
        }
        this.isMeasure = true;
        initView();
    }

    private void reset() {
        this.MAX_VALUE = 0;
        this.datas.clear();
        this.labelList.clear();
        this.isMove = false;
        this.selPosition = -1;
        this.fillPaint.setShader(null);
        this.isInit = false;
    }

    public void setDatas(List<HealthHeartRateItem> list) {
        setDatas(list, 0, null, null);
    }

    public void setDatas(List<HealthHeartRateItem> list, int i, List<String> list2, boolean z, String str) {
        this.isSleepHr = z;
        setDatas(list, i, list2, str);
    }

    public void setDatas(List<HealthHeartRateItem> list, int i, List<String> list2, String str) {
        reset();
        this.type = i;
        if (list2 != null) {
            this.labelList.addAll(list2);
        }
        if (list != null && list.size() > 0) {
            int size = 0;
            for (int i2 = 0; i2 < list.size(); i2++) {
                if (i == 0) {
                    HealthHeartRateItem healthHeartRateItem = list.get(i2);
                    if (healthHeartRateItem.getHeartRaveValue() != 0) {
                        if (i2 == 0) {
                            size = healthHeartRateItem.getOffsetMinute();
                        } else {
                            size += healthHeartRateItem.getOffsetMinute();
                        }
                        healthHeartRateItem.setOffsetMinute(size);
                        if (healthHeartRateItem.getOffsetMinute() <= 1440) {
                            this.datas.add(healthHeartRateItem);
                        }
                        if (this.MAX_VALUE < healthHeartRateItem.getHeartRaveValue()) {
                            this.MAX_VALUE = healthHeartRateItem.getHeartRaveValue();
                        }
                    }
                } else {
                    HealthHeartRateItem healthHeartRateItem2 = list.get(i2);
                    healthHeartRateItem2.setOffsetMinute(size);
                    size += (int) (1440.0f / (list2.size() - 1.0f));
                    this.datas.add(healthHeartRateItem2);
                    if (this.MAX_VALUE < healthHeartRateItem2.getHeartRaveValue()) {
                        this.MAX_VALUE = healthHeartRateItem2.getHeartRaveValue();
                    }
                }
            }
        }
        if (this.hasYValue) {
            this.labelYWidth = ViewUtil.getTextRectWidth(this.labelYPaint, Math.round(this.MAX_VALUE) + AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        }
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < this.datas.size(); i3++) {
            if (this.datas.get(i3).getHeartRaveValue() >= this.MIN_VALUE && this.datas.get(i3).getHeartRaveValue() <= this.MAX_VALUE) {
                arrayList.add(this.datas.get(i3));
            }
        }
        this.MIDDLE_VALUE = this.MIN_VALUE + Math.round((this.MAX_VALUE - r9) / 2);
        this.scaleY = new int[]{this.MAX_VALUE, this.MIDDLE_VALUE, this.MIN_VALUE};
        if (!TextUtils.isEmpty(str)) {
            this.mCalendar = Calendar.getInstance();
            try {
                this.mCalendar.setTime(this.simpleDateFormat.parse(str));
            } catch (ParseException e2) {
                e2.printStackTrace();
            }
        }
        this.datas.clear();
        this.datas.addAll(arrayList);
        initView();
    }

    private void initView() {
        if (!this.isMeasure || this.isInit) {
            return;
        }
        this.chartWidthSpan = this.chartWidth / (this.datas.size() - 1);
        this.isInit = true;
        this.points = new Point[this.datas.size()];
        this.bottomY = this.lineHeight[this.splitNum - 1];
        int heartRaveValue = 0;
        for (int i = 0; i < this.datas.size(); i++) {
            int i2 = (int) this.labelYWidth;
            if (i != 0 || !this.isSleepHr) {
                i2 = (int) ((i * this.chartWidthSpan) + this.labelYWidth);
            }
            this.points[i] = new Point(i2, (int) (this.bottomY - getYValue(this.datas.get(i).getHeartRaveValue())));
            if (heartRaveValue < this.datas.get(i).getHeartRaveValue()) {
                heartRaveValue = this.datas.get(i).getHeartRaveValue();
            }
        }
        initPath();
        initAnimator();
        if (this.playAnim) {
            this.valueAnimator.start();
        }
        this.fillPaint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, getHeight(), this.fillPaint.getColor(), ViewCompat.MEASURED_SIZE_MASK, Shader.TileMode.MIRROR));
        invalidate();
    }

    private int getYValue(int i) {
        float f2;
        int i2 = this.MIDDLE_VALUE;
        if (i <= i2) {
            float[] fArr = this.lineHeight;
            f2 = ((fArr[1] / i2) * (i - this.MIN_VALUE)) + fArr[1];
        } else {
            float[] fArr2 = this.lineHeight;
            f2 = ((fArr2[1] / (this.MAX_VALUE - i2)) * (i - i2)) + fArr2[2];
        }
        return (int) f2;
    }

    private void initPath() {
        if (this.points.length == 0) {
            return;
        }
        Point point = new Point();
        point.set(this.points[r1.length - 1].x, this.points[r2.length - 1].y);
        this.path = new Path();
        this.path.moveTo(point.x, point.y);
        int length = this.points.length - 2;
        while (length >= 0) {
            Point point2 = new Point();
            point2.set(this.points[length].x, this.points[length].y);
            int i = (point.x + point2.x) / 2;
            Point point3 = new Point(i, point.y);
            Point point4 = new Point(i, point2.y);
            this.path.cubicTo(point3.x, point3.y, point4.x, point4.y, point2.x, point2.y);
            length--;
            point = point2;
        }
        this.mPathMeasure = new PathMeasure(this.path, false);
    }

    private void initAnimator() {
        this.mUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.life.customview.charter.-$$Lambda$HeartRateLineGraph$bvtTOjLIF1TOdcFVj5it1RZpmi0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f$0.lambda$initAnimator$0$HeartRateLineGraph(valueAnimator);
            }
        };
        this.valueAnimator = ValueAnimator.ofFloat(1.0f, 0.0f).setDuration(500L);
        this.valueAnimator.addUpdateListener(this.mUpdateListener);
        this.valueAnimator.addListener(new Animator.AnimatorListener() { // from class: com.ido.life.customview.charter.HeartRateLineGraph.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                HeartRateLineGraph.this.isOver = true;
                HeartRateLineGraph.this.invalidate();
            }
        });
    }

    public /* synthetic */ void lambda$initAnimator$0$HeartRateLineGraph(ValueAnimator valueAnimator) {
        this.mAnimatorValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.setDrawFilter(this.mFilter);
        List<HealthHeartRateItem> list = this.datas;
        if (list == null || list.size() <= 0) {
            drawNoDataView(canvas);
            return;
        }
        if (this.datas.size() == 1) {
            this.linePaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, 5.0f, this.linePaint);
            return;
        }
        this.linePaint.setStyle(Paint.Style.STROKE);
        if (this.playAnim) {
            this.path.reset();
            PathMeasure pathMeasure = this.mPathMeasure;
            pathMeasure.getSegment(pathMeasure.getLength() * this.mAnimatorValue, this.mPathMeasure.getLength(), this.path, true);
            canvas.drawPath(this.path, this.linePaint);
            if (this.fillAreaHasAnim) {
                float f2 = ((this.points[this.datas.size() - 1].x - this.points[0].x) * (1.0f - this.mAnimatorValue)) + this.points[0].x;
                if (this.isFillArea) {
                    this.path.lineTo(this.points[0].x, this.bottomY);
                    this.path.lineTo(f2, this.bottomY);
                    this.path.close();
                    canvas.drawPath(this.path, this.fillPaint);
                    return;
                }
                return;
            }
            return;
        }
        canvas.drawPath(this.path, this.linePaint);
    }

    private void drawMaxHeight(Canvas canvas) {
        if (this.datas.size() == 0 || this.selPosition == -1) {
            return;
        }
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(com.boat.Xtend.two.R.color.black));
        paint.setAntiAlias(true);
        paint.setTextSize(DipPixelUtil.dip2pxF(8.0f));
        paint.setTextAlign(Paint.Align.CENTER);
        HealthHeartRateItem healthHeartRateItem = this.datas.get(this.selPosition);
        Point point = new Point();
        if (this.selPosition == 0 && this.isSleepHr) {
            point.set((int) this.labelYWidth, (int) (this.bottomY - getYValue(healthHeartRateItem.getHeartRaveValue())));
        } else {
            point.set((int) minutToX(healthHeartRateItem.getOffsetMinute()), (int) (this.bottomY - getYValue(healthHeartRateItem.getHeartRaveValue())));
        }
        Path path = new Path();
        path.moveTo(point.x, this.lineHeight[1] / 2.0f);
        path.lineTo(point.x, this.bottomY);
        canvas.drawPath(path, this.maxYSeparatePaint);
        float fApplyDimension = TypedValue.applyDimension(1, 4.0f, getResources().getDisplayMetrics());
        float f2 = point.x;
        float f3 = point.x;
        float f4 = this.labelYWidth;
        if (f3 <= f4) {
            f2 = f4 + fApplyDimension;
        } else {
            int i = point.x;
            int i2 = this.w;
            if (i >= i2) {
                f2 = i2 - fApplyDimension;
            }
        }
        this.pointPaint.setStyle(Paint.Style.FILL);
        this.pointPaint.setColor(getResources().getColor(com.boat.Xtend.two.R.color.white));
        float f5 = fApplyDimension / 5.0f;
        canvas.drawCircle(f2, point.y + f5, fApplyDimension, this.pointPaint);
        this.pointPaint.setStyle(Paint.Style.STROKE);
        this.pointPaint.setColor(getResources().getColor(com.boat.Xtend.two.R.color.color_FA2E3E));
        canvas.drawCircle(f2, point.y + f5, fApplyDimension, this.pointPaint);
        if (this.type == 0) {
            drawDayTouchTip(point, canvas);
        } else {
            drawOtherTouchTip(point, canvas);
        }
    }

    private String calculateTime() {
        StringBuffer stringBuffer = new StringBuffer();
        int i = this.type;
        if (i == 0) {
            int offsetMinute = this.datas.get(this.selPosition).getOffsetMinute();
            stringBuffer.append(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf((int) Math.floor(offsetMinute / 60))) + ":" + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(offsetMinute % 60)));
        } else if (i == 3) {
            stringBuffer.append(this.mCalendar.get(1) + "/" + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(this.selPosition + 1)));
        } else {
            stringBuffer.append(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(this.mCalendar.get(2) + 1)) + "/" + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(this.selPosition + 1)));
        }
        return stringBuffer.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void drawDayTouchTip(android.graphics.Point r19, android.graphics.Canvas r20) {
        /*
            Method dump skipped, instruction units count: 239
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.customview.charter.HeartRateLineGraph.drawDayTouchTip(android.graphics.Point, android.graphics.Canvas):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void drawOtherTouchTip(android.graphics.Point r23, android.graphics.Canvas r24) {
        /*
            Method dump skipped, instruction units count: 624
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.customview.charter.HeartRateLineGraph.drawOtherTouchTip(android.graphics.Point, android.graphics.Canvas):void");
    }

    private void drawXLine(Canvas canvas) {
        this.labelXPaint.setColor(this.textColor);
        this.labelXPaint.setTextAlign(Paint.Align.LEFT);
        for (int i = 1; i < this.splitNum; i++) {
            Path path = new Path();
            if (i != 0) {
                path.moveTo(this.labelYWidth, this.lineHeight[i]);
                path.lineTo(this.w, this.lineHeight[i]);
                if (i == this.splitNum - 1) {
                    this.xSeparatePaint.setPathEffect(null);
                } else {
                    this.xSeparatePaint.setPathEffect(this.effects);
                }
                canvas.drawPath(path, this.xSeparatePaint);
            }
        }
        if (!this.hasYValue || this.datas.size() <= 0) {
            return;
        }
        int i2 = 0;
        while (i2 < this.scaleY.length) {
            i2++;
            canvas.drawText(this.scaleY[i2] + "", 0.0f, this.lineHeight[i2] + ViewUtil.getDrawBaselineY(this.labelXPaint), this.labelXPaint);
        }
    }

    private void drawNoDataView(Canvas canvas) {
        if (this.datas.size() == 0) {
            int height = getHeight();
            canvas.drawRect(0.0f, height - 70, this.chartWidth, height - 20, this.noDataPaint);
        }
    }

    private void drawDayXValue(Canvas canvas) {
        if (this.type == 0) {
            this.labelXPaint.setColor(this.textColor);
            float size = this.chartWidth / (this.labelList.size() - 1);
            for (int i = 0; i < this.labelList.size(); i++) {
                float f2 = (i * size) + this.labelYWidth;
                if (i == 0) {
                    this.labelXPaint.setTextAlign(Paint.Align.LEFT);
                } else if (i == this.labelList.size() - 1) {
                    this.labelXPaint.setTextAlign(Paint.Align.RIGHT);
                } else {
                    this.labelXPaint.setTextAlign(Paint.Align.CENTER);
                }
                canvas.drawText(this.labelList.get(i), f2, this.lineHeight[this.splitNum - 1] + (ViewUtil.getTextHeight(this.labelXPaint) * 1.5f), this.labelXPaint);
            }
        }
    }

    private void drawOtherXValue(Canvas canvas) {
        if (this.type != 0) {
            float size = this.chartWidth / (this.labelList.size() - 1);
            for (int i = 0; i < this.labelList.size(); i++) {
                if (this.selPosition == i) {
                    this.labelXPaint.setColor(getResources().getColor(com.boat.Xtend.two.R.color.color_FA2E3E));
                } else {
                    this.labelXPaint.setColor(this.textColor);
                }
                float f2 = (i * size) + this.labelYWidth;
                if (i == 0) {
                    this.labelXPaint.setTextAlign(Paint.Align.LEFT);
                } else if (i == this.labelList.size() - 1) {
                    this.labelXPaint.setTextAlign(Paint.Align.RIGHT);
                } else {
                    this.labelXPaint.setTextAlign(Paint.Align.CENTER);
                }
                if (this.type == 2) {
                    if (NumUtil.parseInt(this.labelList.get(i)).intValue() % 2 != 0) {
                        canvas.drawText(this.labelList.get(i), f2, this.lineHeight[this.splitNum - 1] + (ViewUtil.getTextHeight(this.labelXPaint) * 1.5f), this.labelXPaint);
                    }
                } else {
                    canvas.drawText(this.labelList.get(i), f2, this.lineHeight[this.splitNum - 1] + (ViewUtil.getTextHeight(this.labelXPaint) * 1.5f), this.labelXPaint);
                }
            }
        }
    }

    private void drawTouch(Canvas canvas) {
        int offsetMinute;
        if (this.hasHighest) {
            int size = -1;
            if (this.isMove) {
                int size2 = 0;
                if (this.datas.size() == 1) {
                    this.touchX = minutToX(this.datas.get(0).getOffsetMinute());
                    this.touchY = this.datas.get(0).getHeartRaveValue();
                } else {
                    if (this.datas.size() > 1) {
                        float f2 = this.touchX;
                        if (f2 != -1.0f) {
                            int iXToMinute = xToMinute(f2);
                            if (iXToMinute < 0 || this.datas.get(0).getOffsetMinute() >= iXToMinute) {
                                offsetMinute = this.datas.get(0).getOffsetMinute();
                                this.touchY = this.datas.get(0).getHeartRaveValue();
                            } else {
                                List<HealthHeartRateItem> list = this.datas;
                                if (list.get(list.size() - 1).getOffsetMinute() <= iXToMinute) {
                                    List<HealthHeartRateItem> list2 = this.datas;
                                    offsetMinute = list2.get(list2.size() - 1).getOffsetMinute();
                                    List<HealthHeartRateItem> list3 = this.datas;
                                    this.touchY = list3.get(list3.size() - 1).getHeartRaveValue();
                                    size2 = this.datas.size() - 1;
                                } else {
                                    while (true) {
                                        if (size2 >= this.datas.size() - 1) {
                                            break;
                                        }
                                        if (this.datas.get(size2).getOffsetMinute() <= iXToMinute) {
                                            int i = size2 + 1;
                                            if (this.datas.get(i).getOffsetMinute() >= iXToMinute) {
                                                if (Math.abs(this.datas.get(size2).getOffsetMinute() - iXToMinute) < Math.abs(this.datas.get(i).getOffsetMinute() - iXToMinute)) {
                                                    iXToMinute = this.datas.get(size2).getOffsetMinute();
                                                    this.touchY = this.datas.get(size2).getHeartRaveValue();
                                                    size = size2;
                                                } else {
                                                    iXToMinute = this.datas.get(i).getOffsetMinute();
                                                    this.touchY = this.datas.get(i).getHeartRaveValue();
                                                    size = i;
                                                }
                                            }
                                        }
                                        size2++;
                                    }
                                    size2 = size;
                                    offsetMinute = iXToMinute;
                                }
                            }
                            this.touchX = minutToX(offsetMinute);
                        } else {
                            List<HealthHeartRateItem> list4 = this.datas;
                            this.touchX = minutToX(list4.get(list4.size() - 1).getOffsetMinute());
                            List<HealthHeartRateItem> list5 = this.datas;
                            this.touchY = list5.get(list5.size() - 1).getHeartRaveValue();
                            size = this.datas.size() - 1;
                        }
                    }
                    this.selPosition = size;
                    drawMaxHeight(canvas);
                }
                size = size2;
                this.selPosition = size;
                drawMaxHeight(canvas);
            }
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        TouchOrClickListener touchOrClickListener;
        if (this.clickListener == null) {
            return super.onTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.touchX = motionEvent.getX();
            this.touchX1 = (int) motionEvent.getX();
        } else if (action == 1) {
            this.touchX2 = (int) motionEvent.getX();
            if (Math.abs(this.touchX2 - this.touchX1) < 10.0f && (touchOrClickListener = this.clickListener) != null) {
                touchOrClickListener.doClick();
            }
            this.isMove = true;
        } else if (action == 2) {
            this.touchX = motionEvent.getX();
        }
        invalidate();
        return true;
    }

    public void doClick(TouchOrClickListener touchOrClickListener) {
        this.clickListener = touchOrClickListener;
        setOnTouchListener(new View.OnTouchListener() { // from class: com.ido.life.customview.charter.-$$Lambda$MqSvC1T78-HnhW66E69uNpuwIK0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.onTouch(view, motionEvent);
            }
        });
    }

    private float minutToX(int i) {
        return (i * this.chartWidthSpan) + this.labelYWidth;
    }

    private int xToMinute(float f2) {
        return (int) ((f2 - this.labelYWidth) / this.chartWidthSpan);
    }
}