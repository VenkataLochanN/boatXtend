package com.ido.life.customview.charter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.ido.life.R;
import com.ido.life.bean.BaseCharBean;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SportSpeedChart extends CustomChatBar<BaseCharBean> {
    private static final String TAG = SportSpeedChart.class.getSimpleName();
    private int mEndColor;
    private int mStartColor;
    private int mYLabelUnitDistance;
    private String mYLabelUnitText;
    private int mYLabelUnitTextColor;
    private int mYLabelUnitTextSize;

    public SportSpeedChart(Context context) {
        this(context, null);
    }

    public SportSpeedChart(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public SportSpeedChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mStartColor = Color.parseColor("#82D972");
        this.mEndColor = Color.parseColor("#82D972");
        this.mYLabelUnitText = null;
        this.mYLabelUnitTextSize = 0;
        this.mYLabelUnitTextColor = -1;
        this.mYLabelUnitDistance = 0;
        initAttrs(attributeSet);
    }

    private void initAttrs(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.SportSpeedChart);
        this.mStartColor = typedArrayObtainStyledAttributes.getColor(1, this.mStartColor);
        this.mEndColor = typedArrayObtainStyledAttributes.getColor(0, this.mEndColor);
        this.mYLabelUnitText = typedArrayObtainStyledAttributes.getString(3);
        this.mYLabelUnitTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, this.mBottomLabelSize);
        this.mYLabelUnitDistance = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, this.mYLabelUnitDistance);
        this.mYLabelUnitTextColor = typedArrayObtainStyledAttributes.getColor(4, this.mBottomLabelColor);
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    protected void drawChat(Canvas canvas) {
        super.drawChat(canvas);
        if (this.mCircleRegion == null || this.mList == null || this.mCircleRegion.size() == 1 || this.mList.size() != this.mCircleRegion.size()) {
            return;
        }
        int height = (getHeight() - measureBottomLineToBottomDistance()) - this.mYGridBottomLineDistance;
        int size = this.mCircleRegion.size();
        Path path = null;
        float fMin = 2.1474836E9f;
        Path path2 = null;
        for (int i = 0; i < size; i++) {
            RectF rectF = this.mCircleRegion.get(i);
            if (rectF.centerY() > 0.0f) {
                fMin = Math.min(fMin, rectF.centerY());
            }
            if (i == 0) {
                path2 = new Path();
                path2.moveTo(rectF.centerX(), rectF.centerY());
                path = new Path();
                path.moveTo(rectF.centerX(), height);
                path.lineTo(rectF.centerX(), rectF.centerY());
            } else if (rectF.centerY() == 0.0f) {
                float f2 = height;
                path.lineTo(this.mCircleRegion.get(i - 1).centerX(), f2);
                int i2 = i;
                while (true) {
                    i2++;
                    if (i2 >= size) {
                        break;
                    }
                    RectF rectF2 = this.mCircleRegion.get(i2);
                    if (rectF2.centerY() != 0.0f) {
                        path2.moveTo(rectF2.centerX(), rectF2.centerY());
                        path.moveTo(rectF2.centerX(), f2);
                        path.lineTo(rectF2.centerX(), rectF2.centerY());
                        break;
                    }
                }
            } else {
                int i3 = i - 1;
                path2.lineTo(rectF.centerX(), this.mCircleRegion.get(i3).centerY());
                path2.lineTo(rectF.centerX(), rectF.centerY());
                path.lineTo(rectF.centerX(), this.mCircleRegion.get(i3).centerY());
                path.lineTo(rectF.centerX(), rectF.centerY());
            }
            if (i == size - 1) {
                RectF rectF3 = this.mCircleRegion.get(i);
                if (rectF3.centerY() > 0.0f) {
                    path.lineTo(rectF3.centerX(), height);
                }
            }
        }
        if (path != null) {
            path.close();
            this.mPaint.reset();
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setAntiAlias(true);
            this.mPaint.setShader(new LinearGradient(0.0f, fMin, 0.0f, height, this.mStartColor, this.mEndColor, Shader.TileMode.REPEAT));
            this.mPaint.setColor(this.mLineColor);
            canvas.drawPath(path, this.mPaint);
        }
        if (path2 != null) {
            this.mPaint.reset();
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPaint.setAntiAlias(true);
            this.mPaint.setColor(this.mLineColor);
            this.mPaint.setStrokeWidth(this.mLineWidth);
            canvas.drawPath(path2, this.mPaint);
        }
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    protected void drawBottomLabel(Canvas canvas) {
        int i;
        if (this.mBottomLabelEnabled && this.mLabelXList != null && this.mXCount == this.mLabelXList.size()) {
            Rect rect = new Rect();
            float fMeasureLeftLineToLeftDistance = measureLeftLineToLeftDistance();
            float width = getWidth() - fMeasureLeftLineToLeftDistance;
            int i2 = 0;
            if (!TextUtils.isEmpty(this.mYLabelUnitText)) {
                this.mPaint.reset();
                this.mPaint.setAntiAlias(true);
                this.mPaint.setStyle(Paint.Style.FILL);
                this.mPaint.setColor(this.mYLabelUnitTextColor);
                if (this.mYLabelUnitTextSize > 0) {
                    this.mPaint.setTextSize(this.mYLabelUnitTextSize);
                } else {
                    this.mPaint.setTextSize(this.mBottomLabelSize);
                }
                Paint paint = this.mPaint;
                String str = this.mYLabelUnitText;
                paint.getTextBounds(str, 0, str.length(), rect);
                width -= rect.width() + this.mYLabelUnitDistance;
            }
            this.mPaint.reset();
            this.mPaint.setAntiAlias(true);
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setColor(this.mBottomLabelColor);
            this.mPaint.setTextSize(this.mBottomLabelSize);
            int size = this.mLabelXList.size();
            Rect rect2 = new Rect();
            if (this.mXCount == 1) {
                String str2 = this.mLabelXList.get(0);
                this.mPaint.getTextBounds(str2, 0, str2.length(), rect2);
                canvas.drawText(str2, ((width / 2.0f) + fMeasureLeftLineToLeftDistance) - (rect2.width() / 2), getHeight() - rect2.height(), this.mPaint);
            } else {
                if (this.mBottomLabelCenter) {
                    i = this.mXCount;
                } else {
                    i = this.mXCount - 1;
                }
                float f2 = width / i;
                int i3 = 0;
                while (i3 < size) {
                    String str3 = this.mLabelXList.get(i3);
                    this.mPaint.getTextBounds(str3, i2, str3.length(), rect2);
                    if (this.mBottomLabelCenter) {
                        canvas.drawText(str3, (float) ((((double) fMeasureLeftLineToLeftDistance) + ((((double) i3) + 0.5d) * ((double) f2))) - ((double) (rect2.width() / 2))), getHeight() - (rect2.height() / 2), this.mPaint);
                    } else if (i3 == 0) {
                        canvas.drawText(str3, fMeasureLeftLineToLeftDistance, getHeight() - (rect2.height() / 2), this.mPaint);
                    } else if (i3 == size - 1) {
                        canvas.drawText(str3, (fMeasureLeftLineToLeftDistance + width) - rect2.width(), getHeight() - (rect2.height() / 2), this.mPaint);
                    } else {
                        canvas.drawText(str3, (fMeasureLeftLineToLeftDistance - rect2.width()) + (i3 * f2), getHeight() - (rect2.height() / 2), this.mPaint);
                    }
                    i3++;
                    i2 = 0;
                }
            }
            if (TextUtils.isEmpty(this.mYLabelUnitText)) {
                return;
            }
            this.mPaint.reset();
            this.mPaint.setAntiAlias(true);
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setColor(this.mYLabelUnitTextColor);
            if (this.mYLabelUnitTextSize > 0) {
                this.mPaint.setTextSize(this.mYLabelUnitTextSize);
            } else {
                this.mPaint.setTextSize(this.mBottomLabelSize);
            }
            canvas.drawText(this.mYLabelUnitText, fMeasureLeftLineToLeftDistance + width + this.mYLabelUnitDistance, (getHeight() - (rect2.height() / 2)) + ((rect2.height() - rect.height()) / 2), this.mPaint);
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
        int height = (getHeight() - measureBottomLineToBottomDistance()) - this.mYGridBottomLineDistance;
        float width = getWidth() - fMeasureLeftLineToLeftDistance;
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
                f3 = (float) (((double) fMeasureLeftLineToLeftDistance) + (((double) f2) / 2.0d) + ((double) ((t.x - this.mXMinValue) * f2)));
            } else {
                f3 = ((t.x - this.mXMinValue) * f2) + fMeasureLeftLineToLeftDistance;
            }
            if (t.y == 0.0f) {
                this.mCircleRegion.add(new RectF(f3, 0.0f, f3, 0.0f));
            } else {
                float f5 = height - ((t.y - this.mYMinValue) * f4);
                this.mCircleRegion.add(new RectF(f3, f5, f3, f5));
            }
        }
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    public void setList(List<BaseCharBean> list) {
        super.setList(list);
    }

    public void setStartColor(int i) {
        this.mStartColor = i;
    }

    public void setEndColor(int i) {
        this.mEndColor = i;
    }
}