package com.ido.life.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.boat.Xtend.two.R;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.ResourceUtil;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class SideBar extends View {
    private int mChoose;
    private OnTouchSessionChangedListener mOnTouchSessionChangedListener;
    protected Paint mPaint;
    public ArrayList<String> mSessions;
    private int offset;
    private int sessionHeight;

    public interface OnTouchSessionChangedListener {
        void onTouchSessionChanged(String str);
    }

    public SideBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.sessionHeight = DipPixelUtil.dip2px(10.0f);
        this.offset = ResourceUtil.getDimens(R.dimen.sw_dp_8);
        this.mSessions = new ArrayList<>();
        this.mChoose = -1;
        this.mPaint = new Paint();
    }

    public SideBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.sessionHeight = DipPixelUtil.dip2px(10.0f);
        this.offset = ResourceUtil.getDimens(R.dimen.sw_dp_8);
        this.mSessions = new ArrayList<>();
        this.mChoose = -1;
        this.mPaint = new Paint();
    }

    public SideBar(Context context) {
        super(context);
        this.sessionHeight = DipPixelUtil.dip2px(10.0f);
        this.offset = ResourceUtil.getDimens(R.dimen.sw_dp_8);
        this.mSessions = new ArrayList<>();
        this.mChoose = -1;
        this.mPaint = new Paint();
    }

    public void setSideBarSessions(ArrayList<String> arrayList) {
        if (arrayList == null) {
            return;
        }
        this.mSessions = arrayList;
        postInvalidate();
    }

    public void setOnTouchingSessionChangedListener(OnTouchSessionChangedListener onTouchSessionChangedListener) {
        this.mOnTouchSessionChangedListener = onTouchSessionChangedListener;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getHeight();
        int width = getWidth();
        for (int i = 0; i < this.mSessions.size(); i++) {
            setPaintColor(false);
            this.mPaint.setTypeface(Typeface.DEFAULT_BOLD);
            this.mPaint.setAntiAlias(true);
            this.mPaint.setTextSize(ResourceUtil.getDimens(R.dimen.com_text_small_mind));
            if (i == this.mChoose) {
                setPaintColor(true);
                this.mPaint.setFakeBoldText(true);
            }
            float fMeasureText = (width / 2) - (this.mPaint.measureText(this.mSessions.get(i)) / 2.0f);
            int i2 = this.offset;
            canvas.drawText(this.mSessions.get(i).toUpperCase(), fMeasureText, (i2 * i) + (this.sessionHeight * i) + i2, this.mPaint);
            this.mPaint.reset();
        }
    }

    protected void setPaintColor(boolean z) {
        if (z) {
            this.mPaint.setTextSize(ResourceUtil.getDimens(R.dimen.com_text_mind));
        } else {
            this.mPaint.setColor(ResourceUtil.getColor(R.color.color_text_side_bar_55));
        }
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        int i = this.mChoose;
        getHeight();
        OnTouchSessionChangedListener onTouchSessionChangedListener = this.mOnTouchSessionChangedListener;
        int i2 = (int) (y / (this.sessionHeight + this.offset));
        if (action == 1) {
            this.mChoose = -1;
            invalidate();
        } else if (i != i2 && i2 >= 0 && i2 < this.mSessions.size()) {
            if (onTouchSessionChangedListener != null) {
                onTouchSessionChangedListener.onTouchSessionChanged(this.mSessions.get(i2));
            }
            this.mChoose = i2;
            invalidate();
        }
        return true;
    }
}