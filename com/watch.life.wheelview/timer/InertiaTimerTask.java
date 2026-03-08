package com.watch.life.wheelview.timer;

import com.watch.life.wheelview.view.WheelView;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes3.dex */
public final class InertiaTimerTask extends TimerTask {
    private float mCurrentVelocityY = 2.1474836E9f;
    private final float mFirstVelocityY;
    private final WheelView mWheelView;

    public InertiaTimerTask(WheelView wheelView, float f2) {
        this.mWheelView = wheelView;
        this.mFirstVelocityY = f2;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        if (this.mCurrentVelocityY == 2.1474836E9f) {
            if (Math.abs(this.mFirstVelocityY) > 2000.0f) {
                this.mCurrentVelocityY = this.mFirstVelocityY <= 0.0f ? -2000.0f : 2000.0f;
            } else {
                this.mCurrentVelocityY = this.mFirstVelocityY;
            }
        }
        if (Math.abs(this.mCurrentVelocityY) >= 0.0f && Math.abs(this.mCurrentVelocityY) <= 20.0f) {
            this.mWheelView.cancelFuture();
            this.mWheelView.getHandler().sendEmptyMessage(2000);
            return;
        }
        int i = (int) (this.mCurrentVelocityY / 100.0f);
        WheelView wheelView = this.mWheelView;
        float f2 = i;
        wheelView.setTotalScrollY(wheelView.getTotalScrollY() - f2);
        if (!this.mWheelView.isLoop()) {
            float itemHeight = this.mWheelView.getItemHeight();
            float totalScrollY = (-this.mWheelView.getInitPosition()) * itemHeight;
            float itemsCount = ((this.mWheelView.getItemsCount() - 1) - this.mWheelView.getInitPosition()) * itemHeight;
            double d2 = ((double) itemHeight) * 0.25d;
            if (((double) this.mWheelView.getTotalScrollY()) - d2 < totalScrollY) {
                totalScrollY = this.mWheelView.getTotalScrollY() + f2;
            } else if (((double) this.mWheelView.getTotalScrollY()) + d2 > itemsCount) {
                itemsCount = this.mWheelView.getTotalScrollY() + f2;
            }
            if (this.mWheelView.getTotalScrollY() <= totalScrollY) {
                this.mCurrentVelocityY = 40.0f;
                this.mWheelView.setTotalScrollY((int) totalScrollY);
            } else if (this.mWheelView.getTotalScrollY() >= itemsCount) {
                this.mWheelView.setTotalScrollY((int) itemsCount);
                this.mCurrentVelocityY = -40.0f;
            }
        }
        float f3 = this.mCurrentVelocityY;
        if (f3 < 0.0f) {
            this.mCurrentVelocityY = f3 + 20.0f;
        } else {
            this.mCurrentVelocityY = f3 - 20.0f;
        }
        this.mWheelView.getHandler().sendEmptyMessage(1000);
    }
}