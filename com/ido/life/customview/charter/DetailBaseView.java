package com.ido.life.customview.charter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.DipPixelUtil;

/* JADX INFO: loaded from: classes2.dex */
public class DetailBaseView extends View {
    private static int TOUCH_SLOP;
    private boolean isHandlerTouch;
    private boolean mCanTouch;
    private IMoveListener moveListener;
    float touchX1;
    private VelocityTracker vTracker;

    public interface IMoveListener {
        void getNext();

        void getPre();
    }

    public IMoveListener getMoveListener() {
        return this.moveListener;
    }

    public void setOnMoveListener(IMoveListener iMoveListener) {
        this.moveListener = iMoveListener;
    }

    public DetailBaseView(Context context) {
        super(context);
        this.mCanTouch = true;
        this.touchX1 = 0.0f;
        this.vTracker = null;
        init();
    }

    public DetailBaseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCanTouch = true;
        this.touchX1 = 0.0f;
        this.vTracker = null;
        init();
    }

    public DetailBaseView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCanTouch = true;
        this.touchX1 = 0.0f;
        this.vTracker = null;
        init();
    }

    private void init() {
        TOUCH_SLOP = DipPixelUtil.dip2px(8.0f);
    }

    public void setCanTouch(boolean z) {
        this.mCanTouch = z;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        IMoveListener iMoveListener;
        IMoveListener iMoveListener2;
        if (!this.mCanTouch) {
            return super.onTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.isHandlerTouch = true;
            this.touchX1 = (int) motionEvent.getX();
            CommonLogUtil.d("touchX1:" + this.touchX1 + ",ev.getPointerCount():" + motionEvent.getPointerCount());
            VelocityTracker velocityTracker = this.vTracker;
            if (velocityTracker == null) {
                this.vTracker = VelocityTracker.obtain();
            } else {
                velocityTracker.clear();
            }
            this.vTracker.addMovement(motionEvent);
            this.vTracker.computeCurrentVelocity(1000);
        } else if (action == 2) {
            int x = (int) motionEvent.getX();
            this.vTracker.addMovement(motionEvent);
            this.vTracker.computeCurrentVelocity(1000);
            float xVelocity = this.vTracker.getXVelocity();
            if (!this.isHandlerTouch) {
                return false;
            }
            if (xVelocity > 200.0f && (iMoveListener2 = this.moveListener) != null) {
                iMoveListener2.getPre();
            }
            if (xVelocity < -200.0f && (iMoveListener = this.moveListener) != null) {
                iMoveListener.getNext();
            }
            this.isHandlerTouch = false;
            CommonLogUtil.d("touchX2-touchX1=" + (x - this.touchX1));
        }
        invalidate();
        return true;
    }
}