package com.ido.life.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.ble.protocol.model.Sport100Type;
import com.ido.life.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class LifeCycleProgressView extends View {
    private List<Integer> mAngleList;
    private Bitmap mBitmap;
    private List<Integer> mColorList;
    private int mCount;
    private int mCurProgress;
    private int mImgHeight;
    private int mImgPaddingBottom;
    private int mImgPaddingLeft;
    private int mImgPaddingRight;
    private int mImgPaddingTop;
    private int mImgResId;
    private int mImgWidth;
    private int mIndeterminateColor;
    private int mIndeterminateRadius;
    private int mMaxProgress;
    private Paint mPaint;
    private int mProgressWidth;

    public LifeCycleProgressView(Context context) {
        this(context, null);
    }

    public LifeCycleProgressView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public LifeCycleProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mImgWidth = 100;
        this.mImgHeight = 100;
        this.mImgPaddingLeft = 20;
        this.mImgPaddingTop = 20;
        this.mImgPaddingRight = 20;
        this.mImgPaddingBottom = 20;
        this.mMaxProgress = 100;
        this.mProgressWidth = 12;
        this.mIndeterminateColor = ViewCompat.MEASURED_STATE_MASK;
        this.mCount = 4;
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        int i;
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.LifeCycleProgressView);
        String string = typedArrayObtainStyledAttributes.getString(7);
        String string2 = typedArrayObtainStyledAttributes.getString(11);
        this.mImgResId = typedArrayObtainStyledAttributes.getResourceId(0, -1);
        this.mImgWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(6, this.mImgWidth);
        this.mImgHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, this.mImgHeight);
        this.mImgPaddingLeft = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, this.mImgPaddingLeft);
        this.mImgPaddingTop = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, this.mImgPaddingTop);
        this.mImgPaddingRight = typedArrayObtainStyledAttributes.getDimensionPixelSize(4, this.mImgPaddingRight);
        this.mImgPaddingBottom = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, this.mImgPaddingBottom);
        this.mMaxProgress = typedArrayObtainStyledAttributes.getInt(10, this.mMaxProgress);
        this.mCurProgress = typedArrayObtainStyledAttributes.getInt(12, this.mCurProgress);
        this.mProgressWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(13, this.mProgressWidth);
        this.mIndeterminateColor = typedArrayObtainStyledAttributes.getColor(8, this.mIndeterminateColor);
        this.mIndeterminateRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(9, (this.mProgressWidth * 4) / 5);
        typedArrayObtainStyledAttributes.recycle();
        this.mPaint = new Paint();
        this.mColorList = new ArrayList();
        this.mAngleList = new ArrayList();
        if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
            String[] strArrSplit = string.split(AppInfo.DELIM);
            String[] strArrSplit2 = string2.split(AppInfo.DELIM);
            if (strArrSplit.length == strArrSplit2.length) {
                int length = strArrSplit.length;
                for (int i2 = 0; i2 < length; i2++) {
                    try {
                        this.mColorList.add(Integer.valueOf(Color.parseColor(strArrSplit[i2])));
                        this.mAngleList.add(Integer.valueOf(Integer.parseInt(strArrSplit2[i2])));
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        if (this.mColorList.size() == 0 || this.mAngleList.size() == 0 || this.mColorList.size() != this.mAngleList.size()) {
            this.mColorList.clear();
            this.mAngleList.clear();
            this.mColorList.add(Integer.valueOf(Color.parseColor("#F1247C")));
            this.mColorList.add(Integer.valueOf(Color.parseColor("#FDDEEB")));
            this.mColorList.add(Integer.valueOf(Color.parseColor("#15B9ED")));
            this.mColorList.add(Integer.valueOf(Color.parseColor("#FDDEEB")));
            this.mAngleList.add(60);
            this.mAngleList.add(60);
            this.mAngleList.add(110);
            this.mAngleList.add(Integer.valueOf(Sport100Type.SPORT_TYPE_MOUNTAINEERING_MACHINE));
        }
        int i3 = this.mImgResId;
        if (i3 > 0) {
            int i4 = this.mImgWidth;
            if (i4 <= 0 || (i = this.mImgHeight) <= 0) {
                this.mBitmap = getBitmap(this.mImgResId, getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels);
            } else {
                this.mBitmap = getBitmap(i3, i4, i);
            }
        }
        this.mCount = this.mColorList.size();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mPaint.reset();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(this.mProgressWidth);
        float f2 = 360.0f / this.mMaxProgress;
        float fIntValue = -90.0f;
        for (int i = 0; i < this.mCount; i++) {
            this.mPaint.setColor(this.mColorList.get(i).intValue());
            int i2 = this.mProgressWidth;
            canvas.drawArc(i2, i2, getWidth() - this.mProgressWidth, getHeight() - this.mProgressWidth, fIntValue, f2 * this.mAngleList.get(i).intValue(), false, this.mPaint);
            fIntValue += this.mAngleList.get(i).intValue() * f2;
        }
        this.mPaint.setColor(-65536);
        if (this.mBitmap != null) {
            this.mPaint.reset();
            this.mPaint.setAntiAlias(true);
            this.mPaint.setStyle(Paint.Style.FILL);
            canvas.drawBitmap(this.mBitmap, (getWidth() / 2) - (this.mBitmap.getWidth() / 2), (getHeight() / 2) - (this.mBitmap.getHeight() / 2), this.mPaint);
        }
        this.mPaint.reset();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(this.mIndeterminateColor);
        this.mPaint.setStrokeWidth(this.mIndeterminateRadius);
        int i3 = this.mProgressWidth;
        canvas.drawArc(i3, i3, getWidth() - this.mProgressWidth, getHeight() - this.mProgressWidth, ((this.mCurProgress * 360.0f) / this.mMaxProgress) - 90.0f, 5.0f, false, this.mPaint);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        View.MeasureSpec.getMode(i);
        View.MeasureSpec.getMode(i2);
        int iMin = Math.min(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
        if (iMin < getMinimumWidth()) {
            iMin = getMinimumWidth();
        }
        setMeasuredDimension(iMin, iMin);
    }

    public void setAngleList(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        this.mMaxProgress = 0;
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            this.mMaxProgress += it.next().intValue();
        }
        List<Integer> list2 = this.mAngleList;
        if (list2 == null) {
            this.mAngleList = new ArrayList();
        } else {
            list2.clear();
        }
        this.mAngleList.addAll(list);
    }

    public void setProgress(int i) {
        if (i == this.mCurProgress) {
            return;
        }
        if (i < 0) {
            i = 0;
        } else {
            int i2 = this.mMaxProgress;
            if (i > i2) {
                i = i2;
            }
        }
        this.mCurProgress = i;
        invalidate();
    }

    public void setMaxProgress(int i) {
        if (this.mMaxProgress == i || i <= 0) {
            return;
        }
        this.mMaxProgress = i;
        if (this.mCurProgress > i) {
            this.mCurProgress = i;
        }
        invalidate();
    }

    public int getProgress() {
        return this.mCurProgress;
    }

    private Bitmap getBitmap(int i, int i2, int i3) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        int i4 = 1;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), i, options);
        while (true) {
            if (options.outHeight / i4 <= i3 && options.outHeight / i4 <= i3) {
                options.inSampleSize = i4;
                options.inJustDecodeBounds = false;
                return BitmapFactory.decodeResource(getResources(), i, options);
            }
            i4 *= 2;
        }
    }
}