package com.ido.life.customview.charter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import com.ido.life.bean.BaseCharBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class LineChartBar<T extends BaseCharBean> extends CustomChatBar<T> {
    private static final String TAG = LineChartBar.class.getSimpleName();
    public int mSelectedIndex;

    public LineChartBar(Context context) {
        super(context);
        this.mSelectedIndex = -1;
    }

    public LineChartBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mSelectedIndex = -1;
    }

    public LineChartBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mSelectedIndex = -1;
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    protected void drawChat(Canvas canvas) {
        int iSave;
        super.drawChat(canvas);
        if (this.mList == null || this.mList.size() == 0 || this.mCircleRegion == null || this.mCircleRegion.size() == 0) {
            return;
        }
        Path path = new Path();
        int i = 0;
        if (this.mOxygenRectBgEnable) {
            this.mPaint.reset();
            ArrayList arrayList = new ArrayList();
            Iterator<RectF> it = this.mCircleRegion.iterator();
            while (it.hasNext()) {
                arrayList.add(Float.valueOf(it.next().centerY()));
            }
            Collections.sort(arrayList);
            RectF rectF = new RectF();
            Paint paint = new Paint();
            paint.setColor(this.mOxygenRectBgColor);
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5.0f);
            if (arrayList.size() == 1) {
                rectF.top = ((Float) arrayList.get(0)).floatValue() - 20.0f;
                rectF.bottom = ((Float) arrayList.get(0)).floatValue() + 20.0f;
                rectF.left = this.mXMinValue;
                rectF.right = this.mXMaxValue;
                canvas.drawRect(rectF, paint);
            } else if (arrayList.size() > 1) {
                rectF.top = ((Float) arrayList.get(0)).floatValue() - 20.0f;
                rectF.bottom = ((Float) arrayList.get(arrayList.size() - 1)).floatValue() + 20.0f;
                rectF.left = measureLeftLineToLeftDistance();
                rectF.right = getWidth();
                canvas.drawRect(rectF, paint);
            }
            paint.reset();
            paint.setAntiAlias(true);
            paint.setColor(this.mCircleStrokeColor);
            paint.setStrokeWidth(6.0f);
            paint.setStyle(Paint.Style.STROKE);
            paint.setPathEffect(new DashPathEffect(new float[]{20.0f, 10.0f}, 0.0f));
            path.moveTo(measureLeftLineToLeftDistance(), ((Float) arrayList.get(0)).floatValue() - 20.0f);
            path.lineTo(getWidth(), ((Float) arrayList.get(0)).floatValue() - 20.0f);
            canvas.drawPath(path, paint);
            path.reset();
            path.moveTo(measureLeftLineToLeftDistance(), ((Float) arrayList.get(arrayList.size() - 1)).floatValue() + 20.0f);
            path.lineTo(getWidth(), ((Float) arrayList.get(arrayList.size() - 1)).floatValue() + 20.0f);
            canvas.drawPath(path, paint);
            path.reset();
        }
        this.mPaint.reset();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(this.mLineWidth);
        this.mPaint.setColor(this.mLineColor);
        Path path2 = null;
        int size = this.mCircleRegion.size();
        if (!this.mOxygenRectBgEnable) {
            Path path3 = null;
            for (int i2 = 0; i2 < size; i2++) {
                RectF rectF2 = this.mCircleRegion.get(i2);
                if (path3 == null) {
                    path3 = new Path();
                    path3.moveTo(rectF2.centerX(), rectF2.centerY());
                } else if (i2 == 0 && path3 != null) {
                    path3.moveTo(rectF2.centerX(), rectF2.centerY());
                } else {
                    path3.lineTo(rectF2.centerX(), rectF2.centerY());
                }
            }
            path2 = path3;
        }
        if (this.mAnimator == null || !this.mAnimator.isRunning() || this.mAnimatorRadius < 1) {
            iSave = -1;
        } else {
            RectF rectF3 = new RectF();
            if (path2 != null) {
                path2.computeBounds(rectF3, true);
            }
            rectF3.left = 0.0f;
            rectF3.top = 0.0f;
            rectF3.right = (float) ((((double) (rectF3.width() * this.mAnimatorRadius)) * 1.0d) / 100.0d);
            rectF3.bottom = getHeight();
            canvas.clipRect(rectF3);
            iSave = canvas.save();
        }
        if (path2 != null) {
            canvas.drawPath(path2, this.mPaint);
        }
        this.mPaint.reset();
        this.mPaint.setAntiAlias(true);
        int i3 = AnonymousClass1.$SwitchMap$android$graphics$Paint$Style[this.mCircleStyle.ordinal()];
        if (i3 == 1) {
            this.mPaint.setStyle(Paint.Style.FILL);
            while (i < size) {
                if (this.mSelectedIndex == i) {
                    this.mPaint.setColor(this.mCircleSelectedColor);
                } else {
                    this.mPaint.setColor(this.mCircleColor);
                }
                RectF rectF4 = this.mCircleRegion.get(i);
                canvas.drawCircle(rectF4.centerX(), rectF4.centerY(), this.mCircleRadius, this.mPaint);
                i++;
            }
        } else if (i3 == 2) {
            this.mPaint.setStyle(Paint.Style.FILL);
            for (int i4 = 0; i4 < size; i4++) {
                if (this.mSelectedIndex == i4) {
                    this.mPaint.setColor(this.mCircleStrokeSelectedColor);
                } else {
                    this.mPaint.setColor(this.mCircleStrokeColor);
                }
                RectF rectF5 = this.mCircleRegion.get(i4);
                canvas.drawCircle(rectF5.centerX(), rectF5.centerY(), this.mCircleRadius + this.mCircleBorderWidth, this.mPaint);
            }
            while (i < size) {
                if (this.mSelectedIndex == i) {
                    this.mPaint.setColor(this.mCircleSelectedColor);
                } else {
                    this.mPaint.setColor(this.mCircleColor);
                }
                RectF rectF6 = this.mCircleRegion.get(i);
                canvas.drawCircle(rectF6.centerX(), rectF6.centerY(), this.mCircleRadius, this.mPaint);
                i++;
            }
        } else if (i3 == 3) {
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPaint.setStrokeWidth(this.mCircleBorderWidth);
            while (i < size) {
                if (this.mSelectedIndex == i) {
                    this.mPaint.setColor(this.mCircleStrokeSelectedColor);
                } else {
                    this.mPaint.setColor(this.mCircleStrokeColor);
                }
                RectF rectF7 = this.mCircleRegion.get(i);
                canvas.drawCircle(rectF7.centerX(), rectF7.centerY(), this.mCircleRadius, this.mPaint);
                i++;
            }
        }
        if (iSave > -1) {
            canvas.restoreToCount(iSave);
        }
        this.mSelectedIndex = -1;
    }

    /* JADX INFO: renamed from: com.ido.life.customview.charter.LineChartBar$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$graphics$Paint$Style = new int[Paint.Style.values().length];

        static {
            try {
                $SwitchMap$android$graphics$Paint$Style[Paint.Style.FILL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$graphics$Paint$Style[Paint.Style.FILL_AND_STROKE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$graphics$Paint$Style[Paint.Style.STROKE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    protected void caluteCirclePosition() {
        float f2;
        float f3;
        double d2;
        int i;
        this.mCircleRegion.clear();
        if (this.mList == null || this.mList.size() == 0) {
            return;
        }
        adjustLabelMaxineValue();
        float fMeasureLeftLineToLeftDistance = measureLeftLineToLeftDistance();
        int circleSize = getCircleSize();
        this.mPaint.reset();
        this.mPaint.setTextSize(this.mLeftLabelSize);
        this.mPaint.setStyle(Paint.Style.FILL);
        Rect rect = new Rect();
        this.mPaint.getTextBounds("AA", 0, 2, rect);
        int height = (getHeight() - measureBottomLineToBottomDistance()) - (rect.height() / 2);
        int iHeight = (rect.height() / 2) + height;
        if (this.mDrawXGridLine) {
            height -= this.mYGridBottomLineDistance;
            iHeight -= this.mYGridBottomLineDistance;
        }
        float width = getWidth() - fMeasureLeftLineToLeftDistance;
        if (!this.mBottomLabelCenter) {
            fMeasureLeftLineToLeftDistance += circleSize;
            width -= circleSize * 2;
        }
        if (this.mXMaxValue - this.mXMinValue > 0) {
            if (this.mBottomLabelCenter) {
                d2 = ((double) width) * 1.0d;
                i = (this.mXMaxValue - this.mXMinValue) + 1;
            } else {
                d2 = ((double) width) * 1.0d;
                i = this.mXMaxValue - this.mXMinValue;
            }
            f2 = (float) (d2 / ((double) i));
        } else {
            f2 = 0.0f;
        }
        float f4 = this.mYMaxValue - this.mYMinValue > 0.0f ? (float) ((((double) height) * 1.0d) / ((double) (this.mYMaxValue - this.mYMinValue))) : 0.0f;
        if (this.mList == null || this.mList.size() <= 0) {
            return;
        }
        for (T t : this.mList) {
            if (this.mBottomLabelCenter) {
                f3 = (float) (((double) fMeasureLeftLineToLeftDistance) + (((double) f2) / 2.0d) + ((double) ((((PointF) t).x - this.mXMinValue) * f2)));
            } else {
                f3 = ((((PointF) t).x - this.mXMinValue) * f2) + fMeasureLeftLineToLeftDistance;
            }
            float f5 = iHeight - ((((PointF) t).y - this.mYMinValue) * f4);
            float f6 = circleSize;
            this.mCircleRegion.add(new RectF(f3 - f6, f5 - f6, f3 + f6, f5 + f6));
        }
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    protected void onChartClick(int i) {
        super.onChartClick(i);
        if (this.mCircleRegion == null || this.mCircleRegion.size() < i + 1 || i < 0) {
            return;
        }
        RectF rectF = this.mCircleRegion.get(i);
        this.mSelectedIndex = i;
        this.mClickListener.onChartClick(this, rectF, i);
        refreshChart(false);
    }
}