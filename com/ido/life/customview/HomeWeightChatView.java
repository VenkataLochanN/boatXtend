package com.ido.life.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import com.ido.life.R;
import com.ido.life.database.model.UserInfo;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class HomeWeightChatView extends View {
    private int mCircleColor;
    private int mCircleRadius;
    private int mEndColor;
    private int mIndeterminateColor;
    private int mIndeterminateHeight;
    private int mMaxWeight;
    private int mMinWeight;
    private Paint mPaint;
    private int mScreenWidth;
    private int mStartColor;
    private List<Integer> mWeightList;

    public HomeWeightChatView(Context context) {
        this(context, null);
    }

    public HomeWeightChatView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public HomeWeightChatView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mMaxWeight = 250;
        this.mMinWeight = 10;
        this.mStartColor = Color.parseColor("#00E9FF");
        this.mEndColor = -1;
        this.mIndeterminateHeight = 4;
        this.mIndeterminateColor = Color.parseColor("#00CFFF");
        this.mCircleRadius = 8;
        this.mCircleColor = Color.parseColor("#00CFFF");
        init(attributeSet);
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(RunTimeUtil.getInstance().getUserId());
        if ((userInfoQueryUserInfo != null ? userInfoQueryUserInfo.getWeightUnit() : 1) == 1) {
            this.mMaxWeight = 250;
            this.mMinWeight = 10;
        } else {
            this.mMaxWeight = 551;
            this.mMinWeight = 22;
        }
    }

    private void init(AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.HomeWeightChatView);
        this.mMinWeight = typedArrayObtainStyledAttributes.getInt(6, this.mMinWeight);
        this.mMaxWeight = typedArrayObtainStyledAttributes.getInt(5, this.mMaxWeight);
        this.mStartColor = typedArrayObtainStyledAttributes.getColor(7, this.mStartColor);
        this.mEndColor = typedArrayObtainStyledAttributes.getColor(2, this.mEndColor);
        this.mIndeterminateHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(4, this.mIndeterminateHeight);
        this.mIndeterminateColor = typedArrayObtainStyledAttributes.getColor(3, this.mIndeterminateColor);
        this.mCircleRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, this.mCircleRadius);
        this.mCircleColor = typedArrayObtainStyledAttributes.getColor(0, this.mCircleColor);
        typedArrayObtainStyledAttributes.recycle();
        this.mPaint = new Paint();
        this.mScreenWidth = getResources().getDisplayMetrics().widthPixels;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        List<Integer> list = this.mWeightList;
        if (list == null || list.size() == 0) {
            return;
        }
        this.mPaint.reset();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(this.mStartColor);
        int i = this.mCircleRadius;
        this.mPaint.setShader(new LinearGradient(i, i, i, getHeight(), this.mStartColor, this.mEndColor, Shader.TileMode.CLAMP));
        int size = this.mWeightList.size();
        ArrayList<PointF> arrayList = new ArrayList();
        int iMax = Integer.MIN_VALUE;
        Iterator<Integer> it = this.mWeightList.iterator();
        while (it.hasNext()) {
            iMax = Math.max(iMax, it.next().intValue());
        }
        float height = (((getHeight() - (this.mCircleRadius * 2)) * 4.0f) / 5.0f) / iMax;
        float width = getWidth() - (this.mCircleRadius * 2.0f);
        if (size > 1) {
            width /= size - 1;
        }
        if (size > 1) {
            for (int i2 = 0; i2 < size; i2++) {
                arrayList.add(new PointF(this.mCircleRadius + (i2 * width), (getHeight() - this.mCircleRadius) - (this.mWeightList.get(i2).intValue() * height)));
            }
        } else {
            arrayList.add(new PointF(getWidth() - this.mCircleRadius, (getHeight() - this.mCircleRadius) - (this.mWeightList.get(0).intValue() * height)));
        }
        Path path = null;
        Path path2 = null;
        for (PointF pointF : arrayList) {
            if (path == null) {
                path = new Path();
                path.moveTo(pointF.x, getHeight() - this.mCircleRadius);
                path.lineTo(pointF.x, pointF.y);
            } else {
                path.lineTo(pointF.x, pointF.y);
            }
            if (path2 == null) {
                path2 = new Path();
                path2.moveTo(pointF.x, pointF.y);
            } else {
                path2.lineTo(pointF.x, pointF.y);
            }
        }
        if (arrayList.size() == 1) {
            path.lineTo(this.mCircleRadius, ((PointF) arrayList.get(0)).y);
            path.lineTo(this.mCircleRadius, getHeight() - this.mCircleRadius);
            path2.lineTo(this.mCircleRadius, ((PointF) arrayList.get(0)).y);
        } else {
            path.lineTo(((PointF) arrayList.get(arrayList.size() - 1)).x, getHeight() - this.mCircleRadius);
        }
        path.close();
        canvas.drawPath(path, this.mPaint);
        this.mPaint.reset();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setDither(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(this.mIndeterminateColor);
        this.mPaint.setStrokeWidth(this.mIndeterminateHeight);
        canvas.drawPath(path2, this.mPaint);
        this.mPaint.reset();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setDither(true);
        for (PointF pointF2 : arrayList) {
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setColor(this.mCircleColor);
            canvas.drawCircle(pointF2.x, pointF2.y, this.mCircleRadius, this.mPaint);
            this.mPaint.setColor(this.mStartColor);
            canvas.drawCircle(pointF2.x, pointF2.y, this.mCircleRadius - this.mIndeterminateHeight, this.mPaint);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode != 1073741824) {
            size = this.mScreenWidth;
        }
        if (mode2 != 1073741824) {
            size2 = this.mCircleRadius + 100;
        }
        setMeasuredDimension(size, size2);
    }

    public void setMaxWeight(int i) {
        this.mMaxWeight = i;
    }

    public void setMinWeight(int i) {
        this.mMinWeight = i;
    }

    public void setStartColor(int i) {
        this.mStartColor = i;
    }

    public void setEndColor(int i) {
        this.mEndColor = i;
    }

    public void setIndeterminateHeight(int i) {
        this.mIndeterminateHeight = i;
    }

    public void setIndeterminateColor(int i) {
        this.mIndeterminateColor = i;
    }

    public void setCircleRadius(int i) {
        this.mCircleRadius = i;
    }

    public void setCircleColor(int i) {
        this.mCircleColor = i;
    }

    public void setScreenWidth(int i) {
        this.mScreenWidth = i;
    }

    public void setWeightList(List<Integer> list) {
        this.mWeightList = list;
    }

    private void ajustWeightValue() {
        List<Integer> list = this.mWeightList;
        if (list == null || list.size() == 0) {
            return;
        }
        int iMax = Integer.MIN_VALUE;
        int iMin = Integer.MAX_VALUE;
        for (Integer num : this.mWeightList) {
            iMax = Math.max(iMax, num.intValue());
            iMin = Math.min(iMin, num.intValue());
        }
        this.mMaxWeight = Math.max(Math.min(200, this.mMaxWeight), iMax);
        this.mMinWeight = Math.min(Math.max(0, this.mMinWeight), iMin);
    }
}