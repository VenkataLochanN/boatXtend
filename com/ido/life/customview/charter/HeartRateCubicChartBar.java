package com.ido.life.customview.charter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Pair;
import com.ido.life.R;
import com.ido.life.bean.BaseCharBean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class HeartRateCubicChartBar<T extends BaseCharBean> extends CubicChartBar<T> {
    public HeartRateCubicChartBar(Context context) {
        this(context, null);
    }

    public HeartRateCubicChartBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public HeartRateCubicChartBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initAttrs(attributeSet);
    }

    private void initAttrs(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.CubicChartBar);
        this.mRateLineHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, this.mRateLineHeight);
        this.mRateLineColor = typedArrayObtainStyledAttributes.getColor(2, this.mRateLineColor);
        this.mGradientStartColor = typedArrayObtainStyledAttributes.getColor(1, this.mGradientStartColor);
        this.mGradientEndColor = typedArrayObtainStyledAttributes.getColor(0, this.mGradientEndColor);
    }

    @Override // com.ido.life.customview.charter.CubicChartBar, com.ido.life.customview.charter.CustomChatBar
    protected void drawChat(Canvas canvas) {
        int iSave;
        Path path;
        Path path2;
        int i;
        int i2;
        int i3;
        caluteCirclePosition();
        if (this.mList == null || this.mList.size() == 0 || this.mRegionList == null || this.mRegionList.size() == 0) {
            return;
        }
        this.mPaint.reset();
        int i4 = 1;
        this.mPaint.setAntiAlias(true);
        this.mPaint.setDither(true);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setStrokeWidth(this.mRateLineHeight);
        this.mPaint.setColor(this.mRateLineColor);
        ArrayList<PointF> arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        float height = (getHeight() - measureBottomLineToBottomDistance()) - this.mYGridBottomLineDistance;
        int size = this.mRegionList.size();
        int i5 = 0;
        Pair pair = null;
        for (int i6 = 0; i6 < size; i6++) {
            List<RectF> list = this.mRegionList.get(i6);
            int size2 = list.size();
            if (size2 > 1) {
                Pair pair2 = pair;
                for (int i7 = 0; i7 < size2; i7++) {
                    RectF rectF = list.get(i7);
                    if (i7 == 0) {
                        pair2 = new Pair(Integer.valueOf(i6), new Path());
                        ((Path) pair2.second).moveTo(rectF.left, rectF.top);
                    } else {
                        ((Path) pair2.second).lineTo(rectF.left, rectF.top);
                    }
                }
                arrayList2.add(pair2);
                pair = pair2;
            } else if (size2 == 1) {
                RectF rectF2 = list.get(0);
                arrayList.add(new PointF(rectF2.left, rectF2.top));
            }
        }
        float fMeasureBottomLineToBottomDistance = measureBottomLineToBottomDistance();
        if (this.mAnimator == null || !this.mAnimator.isRunning() || this.mAnimatorRadius < 1) {
            iSave = -1;
        } else {
            RectF rectF3 = new RectF();
            rectF3.left = 0.0f;
            rectF3.top = 0.0f;
            rectF3.right = fMeasureBottomLineToBottomDistance + ((float) ((((double) ((getWidth() - fMeasureBottomLineToBottomDistance) * this.mAnimatorRadius)) * 1.0d) / 100.0d));
            rectF3.bottom = height;
            canvas.clipRect(rectF3);
            iSave = canvas.save();
        }
        if (arrayList2.size() > 0) {
            int size3 = arrayList2.size();
            int i8 = 0;
            while (i8 < size3) {
                Pair pair3 = (Pair) arrayList2.get(i8);
                List<RectF> list2 = this.mRegionList.get(((Integer) pair3.first).intValue());
                Path path3 = new Path((Path) pair3.second);
                path3.setFillType(Path.FillType.EVEN_ODD);
                Path path4 = new Path((Path) pair3.second);
                path4.setFillType(Path.FillType.WINDING);
                RectF rectF4 = list2.get(list2.size() - i4);
                RectF rectF5 = list2.get(i5);
                path4.lineTo(rectF4.left, height);
                path4.lineTo(rectF5.left, height);
                path4.close();
                if (this.mShader == null) {
                    path = path4;
                    path2 = path3;
                    i = i8;
                    i2 = size3;
                    i3 = iSave;
                    this.mShader = new LinearGradient(0.0f, this.mMaxTop, 0.0f, height, this.mGradientStartColor, this.mGradientEndColor, Shader.TileMode.MIRROR);
                } else {
                    path = path4;
                    path2 = path3;
                    i = i8;
                    i2 = size3;
                    i3 = iSave;
                }
                this.mPaint.setShader(this.mShader);
                this.mPaint.setStyle(Paint.Style.FILL);
                canvas.drawPath(path, this.mPaint);
                this.mPaint.setShader(null);
                this.mPaint.setStyle(Paint.Style.STROKE);
                this.mPaint.setStrokeWidth(this.mRateLineHeight);
                canvas.drawPath(path2, this.mPaint);
                i8 = i + 1;
                size3 = i2;
                iSave = i3;
                i4 = 1;
                i5 = 0;
            }
        }
        int i9 = iSave;
        this.mPaint.setStyle(Paint.Style.FILL);
        if (arrayList.size() > 0) {
            for (PointF pointF : arrayList) {
                canvas.drawCircle(pointF.x, pointF.y, this.mCircleRadius, this.mPaint);
            }
        }
        if (i9 > -1) {
            canvas.restoreToCount(i9);
        }
    }

    @Override // com.ido.life.customview.charter.CubicChartBar, com.ido.life.customview.charter.CustomChatBar
    protected float measureLeftLineToLeftDistance() {
        float fMax = 0.0f;
        float f2 = this.mLeftLineEnabled ? this.mLeftLineWidth + 0.0f : 0.0f;
        if (this.mLeftLabelEnabled && this.mLabelYLeftList != null && this.mLabelYLeftList.size() > 0) {
            fMax = measureLeftLabelMaxWidth() + this.mLeftLabelLineDistance;
        }
        if (measureBottomLableTitleRect() != null) {
            fMax = Math.max(fMax, r2.width() + this.mBottomTitleToYDistance);
        }
        return f2 + fMax + this.mCircleRadius + this.mCircleBorderWidth;
    }

    @Override // com.ido.life.customview.charter.CubicChartBar, com.ido.life.customview.charter.CustomChatBar
    protected void caluteCirclePosition() {
        if (this.mRegionList == null) {
            this.mRegionList = new ArrayList();
        } else {
            this.mRegionList.clear();
        }
        if (this.mList == null || this.mList.size() == 0) {
            return;
        }
        adjustLabelMaxineValue();
        this.mMaxTop = 2.1474836E9f;
        float fMeasureLeftLineToLeftDistance = measureLeftLineToLeftDistance();
        this.mPaint.reset();
        this.mPaint.setTextSize(this.mLeftLabelSize);
        this.mPaint.setStyle(Paint.Style.FILL);
        Rect rect = new Rect();
        this.mPaint.getTextBounds("AA", 0, 2, rect);
        int height = (getHeight() - measureBottomLineToBottomDistance()) - (rect.height() / 2);
        if (this.mDrawXGridLine) {
            height -= this.mYGridBottomLineDistance;
        }
        float width = getWidth() - fMeasureLeftLineToLeftDistance;
        int iHeight = (rect.height() / 2) + height;
        float f2 = this.mXMaxValue - this.mXMinValue > 0 ? (float) ((((double) width) * 1.0d) / ((double) (this.mXMaxValue - this.mXMinValue))) : 0.0f;
        float f3 = this.mYMaxValue - this.mYMinValue > 0.0f ? (float) ((((double) height) * 1.0d) / ((double) (this.mYMaxValue - this.mYMinValue))) : 0.0f;
        int size = this.mList.size();
        ArrayList arrayList = null;
        for (int i = 0; i < size; i++) {
            T t = this.mList.get(i);
            if (t != null) {
                if (((BaseCharBean) t).x == 0.0f && ((BaseCharBean) t).y == 0.0f) {
                    if (arrayList != null && arrayList.size() > 0) {
                        this.mRegionList.add(arrayList);
                    }
                    arrayList = null;
                } else if (((BaseCharBean) t).x >= this.mXMinValue && ((BaseCharBean) t).x <= this.mXMaxValue && ((BaseCharBean) t).y >= this.mYMinValue && ((BaseCharBean) t).y <= this.mYMaxValue) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    float f4 = ((((BaseCharBean) t).x - this.mXMinValue) * f2) + fMeasureLeftLineToLeftDistance;
                    float f5 = iHeight;
                    float f6 = f5 - ((((BaseCharBean) t).y - this.mYMinValue) * f3);
                    this.mMaxTop = Math.min(this.mMaxTop, f6);
                    arrayList.add(new RectF(f4, f6, f4, f5));
                }
            }
        }
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        this.mRegionList.add(arrayList);
    }

    @Override // com.ido.life.customview.charter.CubicChartBar, com.ido.life.customview.charter.CustomChatBar
    protected boolean canTouch() {
        return ((this.mAnimator != null && (this.mAnimator.isRunning() || this.mAnimator.isStarted())) || this.mRegionList == null || this.mRegionList.size() == 0 || this.mClickListener == null) ? false : true;
    }

    @Override // com.ido.life.customview.charter.CubicChartBar, com.ido.life.customview.charter.CustomChatBar
    protected void onChartClick(int i) {
        super.onChartClick(i);
        if (this.mClickListener == null || this.mRegionList == null || this.mRegionList.size() == 0) {
            return;
        }
        RectF rectF = null;
        int i2 = 0;
        Iterator<List<RectF>> it = this.mRegionList.iterator();
        int i3 = -1;
        while (it.hasNext()) {
            i2++;
            Iterator<RectF> it2 = it.next().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                RectF next = it2.next();
                i3++;
                if (i3 == i) {
                    i = (i + i2) - 1;
                    rectF = next;
                    break;
                }
            }
            if (rectF != null) {
                break;
            }
        }
        if (rectF == null || i <= -1 || this.mList.size() <= i) {
            return;
        }
        this.mClickListener.onChartClick(this, rectF, i);
    }

    @Override // com.ido.life.customview.charter.CubicChartBar, com.ido.life.customview.charter.CustomChatBar
    public int queryNearestTouchIndex(int i) {
        if (this.mRegionList == null || this.mRegionList.size() == 0) {
            return -1;
        }
        int size = this.mRegionList.size();
        int i2 = -1;
        float f2 = Float.MAX_VALUE;
        int i3 = -1;
        int i4 = 0;
        while (i4 < size) {
            List<RectF> list = this.mRegionList.get(i4);
            int size2 = list.size();
            float f3 = f2;
            int i5 = i3;
            for (int i6 = 0; i6 < size2; i6++) {
                i2++;
                RectF rectF = list.get(i6);
                float f4 = i;
                float fAbs = Math.abs(f4 - rectF.right) + Math.abs(f4 - rectF.left);
                if (fAbs < f3) {
                    i5 = i2;
                    f3 = fAbs;
                }
            }
            i4++;
            i3 = i5;
            f2 = f3;
        }
        return i3;
    }

    @Override // com.ido.life.customview.charter.CubicChartBar
    public void setRateLineHeight(int i) {
        this.mRateLineHeight = i;
    }

    @Override // com.ido.life.customview.charter.CubicChartBar
    public void setGradientStartColor(int i) {
        this.mGradientStartColor = i;
        this.mShader = null;
    }

    @Override // com.ido.life.customview.charter.CubicChartBar
    public void setGradientEndColor(int i) {
        this.mGradientEndColor = i;
        this.mShader = null;
    }

    @Override // com.ido.life.customview.charter.CubicChartBar
    public void setShader(Shader shader) {
        this.mShader = shader;
    }

    @Override // com.ido.life.customview.charter.CubicChartBar
    public void setRateLineColor(int i) {
        this.mRateLineColor = i;
    }
}