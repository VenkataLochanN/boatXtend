package com.ido.life.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;

/* JADX INFO: loaded from: classes2.dex */
public class CustomToggleButton extends View implements View.OnTouchListener {
    private Callback callback;
    private float currentX;
    private float dis;
    private boolean isSlipping;
    private boolean isSwitchOn;
    public boolean isTouchEnable;
    private Rect off_Rect;
    private OnSwitchListener onSwitchListener;
    private Rect on_Rect;
    Paint paint;
    private float previousX;
    private int rectPadding;
    private Bitmap slip_Btn;
    private Bitmap switch_off_Bkg;
    private Bitmap switch_on_Bkg;

    public interface Callback {
        boolean handlerEvent();
    }

    public interface OnSwitchListener {
        void onSwitched(View view, boolean z);
    }

    public CustomToggleButton(Context context) {
        super(context);
        this.isTouchEnable = true;
        this.paint = new Paint();
        this.isSlipping = false;
        this.isSwitchOn = false;
        this.rectPadding = 3;
        init();
    }

    public CustomToggleButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isTouchEnable = true;
        this.paint = new Paint();
        this.isSlipping = false;
        this.isSwitchOn = false;
        this.rectPadding = 3;
        init();
    }

    private void init() {
        setOnTouchListener(this);
        setImageResource(R.mipmap.toggle_on, R.mipmap.toggle_off, R.mipmap.toggle_thumb);
    }

    public void setImageResource(int i, int i2, int i3) {
        this.switch_on_Bkg = tintBitmap(BitmapFactory.decodeResource(getResources(), i), getResources().getColor(R.color.color_32D74B), 255);
        this.switch_off_Bkg = tintBitmap(BitmapFactory.decodeResource(getResources(), i2), getResources().getColor(R.color.color_787880), AMapEngineUtils.ARROW_LINE_OUTER_TEXTURE_ID);
        this.slip_Btn = tintBitmap(BitmapFactory.decodeResource(getResources(), i3), getResources().getColor(R.color.white), 255);
        int width = this.switch_off_Bkg.getWidth() - this.slip_Btn.getWidth();
        int i4 = this.rectPadding;
        this.on_Rect = new Rect(width - i4, i4, this.switch_off_Bkg.getWidth() - this.rectPadding, this.slip_Btn.getHeight() - this.rectPadding);
        int i5 = this.rectPadding;
        this.off_Rect = new Rect(i5, i5, this.slip_Btn.getWidth() + this.rectPadding, this.slip_Btn.getHeight() - this.rectPadding);
    }

    public static Bitmap tintBitmap(Bitmap bitmap, int i, int i2) {
        if (bitmap == null) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        paint.setAlpha(i2);
        paint.setColorFilter(new PorterDuffColorFilter(i, PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return bitmapCreateBitmap;
    }

    public boolean getSwitchStatus() {
        return this.isSwitchOn;
    }

    public void setSwitchStatus(boolean z) {
        this.isSwitchOn = z;
        postInvalidate();
    }

    public void setTouchEnable(boolean z) {
        this.isTouchEnable = z;
    }

    protected void updateSwitchStatus(boolean z) {
        this.isSwitchOn = z;
        postInvalidate();
    }

    public Callback getCallback() {
        return this.callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float width;
        super.onDraw(canvas);
        float width2 = 0.0f;
        if (this.isSlipping) {
            if (this.currentX > this.switch_on_Bkg.getWidth()) {
                width = this.switch_on_Bkg.getWidth() - this.slip_Btn.getWidth();
            } else {
                width = this.currentX - (this.slip_Btn.getWidth() / 2);
            }
            if (this.isSlipping && this.currentX < this.switch_on_Bkg.getWidth() / 2) {
                canvas.drawBitmap(this.switch_off_Bkg, 0.0f, (getMeasuredHeight() - this.switch_off_Bkg.getHeight()) / 2, this.paint);
            } else {
                canvas.drawBitmap(this.switch_on_Bkg, 0.0f, (getMeasuredHeight() - this.switch_off_Bkg.getHeight()) / 2, this.paint);
            }
        } else if (this.isSwitchOn) {
            width = this.on_Rect.left;
            canvas.drawBitmap(this.switch_on_Bkg, 0.0f, (getMeasuredHeight() - this.switch_off_Bkg.getHeight()) / 2, this.paint);
        } else {
            width = this.off_Rect.left;
            canvas.drawBitmap(this.switch_off_Bkg, 0.0f, (getMeasuredHeight() - this.switch_off_Bkg.getHeight()) / 2, this.paint);
        }
        if (width >= 0.0f) {
            width2 = width > ((float) (this.switch_on_Bkg.getWidth() - this.slip_Btn.getWidth())) ? this.switch_on_Bkg.getWidth() - this.slip_Btn.getWidth() : width;
        }
        canvas.drawBitmap(this.slip_Btn, width2, (getMeasuredHeight() - this.slip_Btn.getHeight()) / 2, this.paint);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i2);
        if (View.MeasureSpec.getMode(i2) == 0) {
            size = this.switch_on_Bkg.getHeight() + getPaddingBottom() + getPaddingTop();
        }
        setMeasuredDimension(this.switch_on_Bkg.getWidth(), size);
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Callback callback = this.callback;
        if ((callback != null && callback.handlerEvent()) || !this.isTouchEnable) {
            return false;
        }
        boolean z = this.isSwitchOn;
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action == 1) {
                this.isSlipping = false;
                boolean z2 = !this.isSwitchOn;
                this.isSwitchOn = z2;
                OnSwitchListener onSwitchListener = this.onSwitchListener;
                if (onSwitchListener != null) {
                    onSwitchListener.onSwitched(view, z2);
                }
            } else if (action == 2) {
                this.dis = motionEvent.getX() - this.previousX;
                this.currentX = motionEvent.getX();
            } else if (action == 3) {
                this.isSlipping = false;
                boolean z3 = this.currentX >= ((float) this.switch_on_Bkg.getWidth()) / 2.0f;
                CommonLogUtil.d("ACTION_CANCEL ....eventAction = " + motionEvent.getAction() + "***tempStatus = " + z3 + "***isSwitchOn = " + this.isSwitchOn + "***dis = " + this.dis);
                this.isSwitchOn = z3;
                OnSwitchListener onSwitchListener2 = this.onSwitchListener;
                if (onSwitchListener2 != null) {
                    onSwitchListener2.onSwitched(view, z3);
                }
            }
        } else {
            if (motionEvent.getX() > this.switch_on_Bkg.getWidth()) {
                return false;
            }
            this.dis = 0.0f;
            this.isSlipping = true;
            this.previousX = motionEvent.getX();
            this.currentX = this.previousX;
        }
        invalidate();
        return true;
    }

    public void setOnSwitchListener(OnSwitchListener onSwitchListener) {
        this.onSwitchListener = onSwitchListener;
    }
}