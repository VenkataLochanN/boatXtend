package com.ido.life.module.sport.view;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import androidx.core.view.ViewCompat;
import com.ido.life.R;
import com.ido.life.module.sport.bean.PieChartBean;
import com.ido.life.module.sport.util.BigDecimalUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SportPieChart extends View {
    private boolean isOpenAnimation;
    private boolean isOpenCheckStatus;
    private boolean isShowCenterCircle;
    private boolean isShowCenterText;
    private boolean isShowPercentage;
    private int mCenterBgColor;
    private Paint mCenterPaint;
    private int mCenterRadius;
    private String mCenterText;
    private int mCenterTextColor;
    private int mCenterTextSize;
    private TextPaint mCenterTvPaint;
    private float mCenterX;
    private float mCenterY;
    private List<PieChartBean> mDatas;
    private int mIsOneData;
    private int mOuterAddRadius;
    private int mOuterRadius;
    private int mPercentageLong;
    private int mPercentageTvSize;
    private int mPercentageWidth;
    private List<Region> mRegions;
    private int outCircleFrameColor;
    private boolean outCircleFrameEnable;
    private float outCircleFrameWidth;
    private float percent;
    private TimeInterpolator pointInterpolator;
    float space;
    private int spaceColor;
    private float sum;

    public SportPieChart(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SportPieChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.percent = 0.0f;
        this.mIsOneData = 0;
        this.pointInterpolator = new DecelerateInterpolator();
        this.space = 1.0f;
        init(context, attributeSet, i);
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        this.mDatas = new ArrayList();
        this.mRegions = new ArrayList();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PieChart, i, 0);
        this.outCircleFrameEnable = typedArrayObtainStyledAttributes.getBoolean(16, false);
        this.space = typedArrayObtainStyledAttributes.getBoolean(14, false) ? 1.0f : 0.0f;
        this.spaceColor = typedArrayObtainStyledAttributes.getColor(18, -7829368);
        this.outCircleFrameColor = typedArrayObtainStyledAttributes.getColor(15, -7829368);
        this.outCircleFrameWidth = typedArrayObtainStyledAttributes.getDimension(17, context.getResources().getDimension(com.boat.Xtend.two.R.dimen.sw_dp_5));
        this.isShowCenterCircle = typedArrayObtainStyledAttributes.getBoolean(11, true);
        this.isShowCenterText = typedArrayObtainStyledAttributes.getBoolean(12, true);
        this.isShowPercentage = typedArrayObtainStyledAttributes.getBoolean(13, true);
        this.isOpenCheckStatus = typedArrayObtainStyledAttributes.getBoolean(10, true);
        this.mCenterTextColor = typedArrayObtainStyledAttributes.getColor(3, ViewCompat.MEASURED_STATE_MASK);
        this.mCenterText = typedArrayObtainStyledAttributes.getString(2);
        this.mCenterBgColor = typedArrayObtainStyledAttributes.getColor(0, -1);
        this.mCenterTextSize = sp2px(context, typedArrayObtainStyledAttributes.getInt(4, 15));
        this.mCenterRadius = dip2px(context, typedArrayObtainStyledAttributes.getInt(1, 30));
        this.mOuterRadius = dip2px(context, typedArrayObtainStyledAttributes.getInt(6, 60));
        this.mOuterAddRadius = dip2px(context, typedArrayObtainStyledAttributes.getInt(5, 10));
        this.mPercentageLong = dip2px(context, typedArrayObtainStyledAttributes.getInt(7, 10));
        this.mPercentageWidth = dip2px(context, typedArrayObtainStyledAttributes.getInt(9, 1));
        this.mPercentageTvSize = sp2px(context, typedArrayObtainStyledAttributes.getInt(8, 12));
        typedArrayObtainStyledAttributes.recycle();
        this.mCenterPaint = new Paint();
        this.mCenterPaint.setAntiAlias(true);
        this.mCenterPaint.setColor(this.mCenterBgColor);
        this.mCenterTvPaint = new TextPaint();
        this.mCenterTvPaint.setAntiAlias(true);
        this.mCenterTvPaint.setTextAlign(Paint.Align.CENTER);
        this.mCenterTvPaint.setTextSize(this.mCenterTextSize);
        this.mCenterTvPaint.setColor(this.mCenterTextColor);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mIsOneData = 0;
        if (this.mDatas.size() == 0) {
            return;
        }
        if (this.outCircleFrameEnable) {
            drawOutCircleFrame(canvas);
        }
        if (this.space > 0.0f) {
            drawSpaceCircleFrame(canvas);
        }
        drawArcPath(canvas);
        if (this.isShowCenterCircle) {
            drawCenterCircle(canvas);
        }
    }

    private void drawArcPath(Canvas canvas) {
        Path path;
        float f2;
        float f3;
        this.mRegions.clear();
        Paint paint = new Paint();
        float f4 = -90.0f;
        for (int i = 0; i < this.mDatas.size(); i++) {
            float f5 = this.mOuterRadius;
            if (this.isOpenCheckStatus && this.mDatas.get(i).isCheck()) {
                f5 += this.mOuterAddRadius;
            }
            float f6 = this.mCenterX;
            float f7 = this.mCenterY;
            RectF rectF = new RectF(f6 - f5, f7 - f5, f6 + f5, f7 + f5);
            Paint paint2 = new Paint();
            paint.setAntiAlias(true);
            Path path2 = new Path();
            paint2.setAntiAlias(true);
            paint2.setColor(this.mDatas.get(i).getColor());
            path2.moveTo(this.mCenterX, this.mCenterY);
            float value = this.mDatas.get(i).getValue();
            float f8 = this.sum;
            float f9 = (value / f8) * 360.0f;
            float f10 = (this.space / f8) * 360.0f;
            if (this.isOpenAnimation) {
                f9 *= this.percent;
            }
            float f11 = f9;
            if (this.mDatas.size() == 1) {
                canvas.drawArc(rectF, 360.0f, 360.0f, false, paint2);
            } else {
                if (i == 0) {
                    f4 += f10;
                }
                float f12 = f4;
                path2.arcTo(rectF, f12, f11);
                RectF rectF2 = new RectF();
                path2.computeBounds(rectF2, true);
                path2.close();
                Region region = new Region();
                region.set((int) rectF2.left, (int) rectF2.top, (int) rectF2.right, (int) rectF2.bottom);
                region.setPath(path2, region);
                this.mRegions.add(region);
                if (this.isShowPercentage) {
                    double d2 = f5;
                    double d3 = (f11 / 2.0f) + f12;
                    path = path2;
                    float fCos = (float) (d2 * Math.cos(Math.toRadians(d3)));
                    float fSin = (float) (d2 * Math.sin(Math.toRadians(d3)));
                    float fCos2 = (float) (((double) (this.mPercentageLong + f5)) * Math.cos(Math.toRadians(d3)));
                    f2 = f10;
                    float fSin2 = (float) (((double) (f5 + this.mPercentageLong)) * Math.sin(Math.toRadians(d3)));
                    paint2.setStrokeWidth(this.mPercentageWidth);
                    float f13 = this.mCenterX;
                    float f14 = this.mCenterY;
                    f3 = f12;
                    canvas.drawLine(fCos + f13, f14 + fSin, f13 + fCos2, f14 + fSin2, paint2);
                    try {
                        String str = BigDecimalUtil.mul(BigDecimalUtil.round(this.mDatas.get(i).getValue() / this.sum, 4), 100.0d) + "%";
                        paint2.setTextSize(this.mPercentageTvSize);
                        if (fCos2 > 0.0f) {
                            canvas.drawLine(this.mCenterX + fCos2, this.mCenterY + fSin2, this.mPercentageLong + this.mCenterX + fCos2, this.mCenterY + fSin2, paint2);
                            canvas.drawText(str, this.mCenterX + fCos2 + this.mPercentageLong, this.mCenterY + fSin2, paint2);
                        } else {
                            canvas.drawLine(this.mCenterX + fCos2, this.mCenterY + fSin2, (this.mCenterX + fCos2) - this.mPercentageLong, this.mCenterY + fSin2, paint2);
                            canvas.drawText(str, ((this.mCenterX + fCos2) - this.mPercentageLong) - paint2.measureText(str), this.mCenterY + fSin2, paint2);
                        }
                    } catch (IllegalAccessException e2) {
                        e2.printStackTrace();
                    }
                } else {
                    path = path2;
                    f2 = f10;
                    f3 = f12;
                }
                float f15 = f3 + f11;
                if (i != this.mDatas.size() - 1) {
                    f15 += f2;
                }
                canvas.drawPath(path, paint2);
                f4 = f15;
            }
        }
    }

    public void startAnimation(int i) {
        this.isOpenAnimation = true;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.setDuration(i);
        valueAnimatorOfFloat.setInterpolator(this.pointInterpolator);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.life.module.sport.view.SportPieChart.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                SportPieChart.this.percent = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                SportPieChart.this.invalidate();
            }
        });
        valueAnimatorOfFloat.start();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(getDefaultSize(50, i), getDefaultSize(50, i2));
    }

    private void drawCenterCircle(Canvas canvas) {
        canvas.drawCircle(this.mCenterX, this.mCenterY, this.mCenterRadius, this.mCenterPaint);
        if (this.isShowCenterText) {
            canvas.save();
            StaticLayout staticLayout = new StaticLayout(this.mCenterText, this.mCenterTvPaint, this.mCenterRadius * 2, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
            canvas.translate(this.mCenterX, this.mCenterY - (staticLayout.getHeight() / 2.0f));
            staticLayout.draw(canvas);
            canvas.restore();
        }
    }

    private void drawOutCircleFrame(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(this.outCircleFrameColor);
        canvas.drawCircle(this.mCenterX, this.mCenterY, this.mOuterRadius + this.outCircleFrameWidth, paint);
    }

    private void drawSpaceCircleFrame(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(this.spaceColor);
        canvas.drawCircle(this.mCenterX, this.mCenterY, this.mOuterRadius, paint);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mCenterX = i / 2;
        this.mCenterY = i2 / 2;
    }

    private static int dip2px(Context context, float f2) {
        return (int) ((f2 * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private static int sp2px(Context context, float f2) {
        return (int) ((f2 * context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public void setData(List<PieChartBean> list) {
        this.mDatas.clear();
        this.mDatas.addAll(list);
        this.sum = 0.0f;
        for (int i = 0; i < list.size(); i++) {
            this.sum += list.get(i).getValue();
        }
        if (list.size() > 1) {
            this.sum += list.size() * this.space;
        }
        invalidate();
    }

    public void setShowCenterText(String str) {
        this.mCenterText = str;
        invalidate();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 && this.isOpenCheckStatus) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            for (int i = 0; i < this.mRegions.size(); i++) {
                if (this.mRegions.get(i).contains((int) x, (int) y)) {
                    this.mDatas.get(i).setCheck(!this.mDatas.get(i).isCheck());
                    invalidate();
                } else {
                    this.mDatas.get(i).setCheck(false);
                }
            }
        }
        return super.onTouchEvent(motionEvent);
    }
}