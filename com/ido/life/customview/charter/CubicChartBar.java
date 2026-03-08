package com.ido.life.customview.charter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import com.ido.life.R;
import com.ido.life.bean.BaseCharBean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CubicChartBar<T extends BaseCharBean> extends CustomChatBar<T> {
    protected int mGradientEndColor;
    protected int mGradientStartColor;
    protected float mMaxTop;
    protected int mRateLineColor;
    protected int mRateLineHeight;
    protected List<List<RectF>> mRegionList;
    protected Shader mShader;

    public CubicChartBar(Context context) {
        this(context, null);
    }

    public CubicChartBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public CubicChartBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mRateLineColor = Color.parseColor("#FC4B69");
        this.mGradientStartColor = Color.parseColor("#FF5460");
        this.mGradientEndColor = Color.parseColor("#FFFFFF");
        initAttrs(attributeSet);
    }

    private void initAttrs(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.CubicChartBar);
        this.mRateLineHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, this.mRateLineHeight);
        this.mRateLineColor = typedArrayObtainStyledAttributes.getColor(2, this.mRateLineColor);
        this.mGradientStartColor = typedArrayObtainStyledAttributes.getColor(1, this.mGradientStartColor);
        this.mGradientEndColor = typedArrayObtainStyledAttributes.getColor(0, this.mGradientEndColor);
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    protected void drawChat(Canvas canvas) {
        List<List<RectF>> list;
        int iSave;
        Path path;
        Path path2;
        int i;
        int i2;
        int i3;
        super.drawChat(canvas);
        if (this.mList == null || this.mList.size() == 0 || (list = this.mRegionList) == null || list.size() == 0) {
            return;
        }
        this.mPaint.reset();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setStrokeWidth(this.mRateLineHeight);
        this.mPaint.setColor(this.mRateLineColor);
        ArrayList<PointF> arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        float height = (getHeight() - measureBottomLineToBottomDistance()) - this.mYGridBottomLineDistance;
        for (List<RectF> list2 : this.mRegionList) {
            int size = list2.size();
            int i4 = 0;
            Path path3 = null;
            while (true) {
                i3 = size - 2;
                if (i4 >= i3) {
                    break;
                }
                int i5 = i4 + 1;
                RectF rectF = list2.get(i4);
                if (path3 == null) {
                    path3 = new Path();
                    path3.moveTo(rectF.left, rectF.top);
                    i4 = i5;
                } else {
                    int i6 = i5 + 1;
                    RectF rectF2 = list2.get(i5);
                    int i7 = i6 + 1;
                    RectF rectF3 = list2.get(i6);
                    path3.cubicTo(rectF.left, rectF.top, rectF2.left, rectF2.top, rectF3.left, rectF3.top);
                    i4 = i7;
                }
            }
            int i8 = size - i4;
            if (i8 != 1) {
                if (i8 != 2) {
                    if (i8 == 3) {
                        int i9 = i4 + 1;
                        RectF rectF4 = list2.get(i9);
                        RectF rectF5 = list2.get(i9);
                        if (path3 == null) {
                            path3 = new Path();
                            path3.moveTo(rectF4.left, rectF4.top);
                        }
                        path3.cubicTo(rectF4.left, rectF4.top, rectF4.left + ((rectF5.left - rectF4.left) * 0.75f), rectF4.top + ((rectF5.top - rectF4.top) * 0.75f), rectF5.left, rectF5.top);
                    }
                } else if (i4 == 0 && path3 == null) {
                    RectF rectF6 = list2.get(0);
                    RectF rectF7 = list2.get(1);
                    Path path4 = new Path();
                    path4.moveTo(rectF6.left, rectF6.top);
                    path4.cubicTo(rectF6.left, rectF6.top, rectF6.left + ((rectF7.left - rectF6.left) * 0.75f), rectF6.top + ((rectF7.top - rectF6.top) * 0.75f), rectF7.left, rectF7.top);
                    path3 = path4;
                } else {
                    RectF rectF8 = list2.get(i3);
                    if (path3 == null) {
                        path3 = new Path();
                        if (rectF8.right + this.mCircleRadius > getWidth()) {
                            arrayList.add(new PointF(getWidth() - this.mCircleRadius, rectF8.top));
                        } else {
                            arrayList.add(new PointF(rectF8.left, rectF8.top));
                        }
                    } else {
                        path3.lineTo(rectF8.left, rectF8.top);
                    }
                }
            } else if (i4 == 0 && path3 == null) {
                RectF rectF9 = list2.get(0);
                path3 = new Path();
                path3.moveTo(rectF9.left, rectF9.top);
                if (rectF9.right + this.mCircleRadius > getWidth()) {
                    arrayList.add(new PointF(getWidth() - this.mCircleRadius, rectF9.top));
                } else {
                    arrayList.add(new PointF(rectF9.left, rectF9.top));
                }
            }
            if (path3 != null) {
                arrayList2.add(path3);
            }
        }
        if (arrayList2.size() == 0) {
            return;
        }
        float fMeasureBottomLineToBottomDistance = measureBottomLineToBottomDistance();
        if (this.mAnimator == null || !this.mAnimator.isRunning() || this.mAnimatorRadius < 1) {
            iSave = -1;
        } else {
            RectF rectF10 = new RectF();
            rectF10.left = 0.0f;
            rectF10.top = 0.0f;
            rectF10.right = fMeasureBottomLineToBottomDistance + ((float) ((((double) ((getWidth() - fMeasureBottomLineToBottomDistance) * this.mAnimatorRadius)) * 1.0d) / 100.0d));
            rectF10.bottom = height;
            canvas.clipRect(rectF10);
            iSave = canvas.save();
        }
        int size2 = arrayList2.size();
        int i10 = 0;
        while (i10 < size2) {
            Path path5 = (Path) arrayList2.get(i10);
            Path path6 = new Path(path5);
            path5.setFillType(Path.FillType.WINDING);
            List<RectF> list3 = this.mRegionList.get(i10);
            if (list3.size() == 1) {
                i = i10;
                i2 = size2;
            } else {
                RectF rectF11 = list3.get(list3.size() - 1);
                RectF rectF12 = list3.get(0);
                path5.lineTo(rectF11.left, height);
                path5.lineTo(rectF12.left, height);
                if (this.mShader == null) {
                    path = path6;
                    path2 = path5;
                    i = i10;
                    i2 = size2;
                    this.mShader = new LinearGradient(0.0f, this.mMaxTop, 0.0f, height, this.mGradientStartColor, this.mGradientEndColor, Shader.TileMode.MIRROR);
                } else {
                    path = path6;
                    path2 = path5;
                    i = i10;
                    i2 = size2;
                }
                this.mPaint.setShader(this.mShader);
                this.mPaint.setStyle(Paint.Style.FILL);
                canvas.drawPath(path2, this.mPaint);
                this.mPaint.setShader(null);
                this.mPaint.setStyle(Paint.Style.STROKE);
                this.mPaint.setStrokeWidth(this.mRateLineHeight);
                canvas.drawPath(path, this.mPaint);
            }
            i10 = i + 1;
            size2 = i2;
        }
        this.mPaint.setStyle(Paint.Style.FILL);
        if (arrayList.size() > 0) {
            for (PointF pointF : arrayList) {
                canvas.drawCircle(pointF.x, pointF.y, this.mCircleRadius, this.mPaint);
            }
        }
        if (iSave > -1) {
            canvas.restoreToCount(iSave);
        }
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
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

    @Override // com.ido.life.customview.charter.CustomChatBar
    protected void caluteCirclePosition() {
        ArrayList arrayList;
        List<List<RectF>> list = this.mRegionList;
        if (list == null) {
            this.mRegionList = new ArrayList();
        } else {
            list.clear();
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
        loop0: while (true) {
            arrayList = null;
            for (T t : this.mList) {
                if (((BaseCharBean) t).x == 0.0f && ((BaseCharBean) t).y == 0.0f) {
                    if (arrayList != null && arrayList.size() > 0) {
                        this.mRegionList.add(arrayList);
                    }
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
            break loop0;
        }
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        this.mRegionList.add(arrayList);
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    protected boolean canTouch() {
        List<List<RectF>> list;
        return ((this.mAnimator != null && (this.mAnimator.isRunning() || this.mAnimator.isStarted())) || (list = this.mRegionList) == null || list.size() == 0 || this.mClickListener == null) ? false : true;
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    protected void onChartClick(int i) {
        List<List<RectF>> list;
        super.onChartClick(i);
        if (this.mClickListener == null || (list = this.mRegionList) == null || list.size() == 0) {
            return;
        }
        RectF rectF = null;
        int i2 = i + 1;
        int i3 = -1;
        for (List<RectF> list2 : this.mRegionList) {
            int size = list2.size();
            if (i2 > size) {
                i2 -= size;
                i++;
            }
            Iterator<RectF> it = list2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                RectF next = it.next();
                i3++;
                if (i3 == i) {
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

    @Override // com.ido.life.customview.charter.CustomChatBar
    public int queryNearestTouchIndex(int i) {
        List<List<RectF>> list = this.mRegionList;
        if (list == null || list.size() == 0) {
            return -1;
        }
        int size = this.mRegionList.size();
        int i2 = -1;
        float f2 = Float.MAX_VALUE;
        int i3 = -1;
        int i4 = 0;
        while (i4 < size) {
            List<RectF> list2 = this.mRegionList.get(i4);
            int size2 = list2.size();
            float f3 = f2;
            int i5 = i3;
            for (int i6 = 0; i6 < size2; i6++) {
                i2++;
                RectF rectF = list2.get(i6);
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

    public void setRateLineHeight(int i) {
        this.mRateLineHeight = i;
    }

    public void setGradientStartColor(int i) {
        this.mGradientStartColor = i;
        this.mShader = null;
    }

    public void setGradientEndColor(int i) {
        this.mGradientEndColor = i;
        this.mShader = null;
    }

    public void setShader(Shader shader) {
        this.mShader = shader;
    }

    public void setRateLineColor(int i) {
        this.mRateLineColor = i;
    }

    @Override // com.ido.life.customview.charter.CustomChatBar
    public void clearList() {
        super.clearList();
        List<List<RectF>> list = this.mRegionList;
        if (list == null || list.size() <= 0) {
            return;
        }
        this.mRegionList.clear();
    }
}