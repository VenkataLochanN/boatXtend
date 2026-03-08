package com.ido.life.module.home.pressure.ajust;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.ido.life.base.BasePresenter;
import com.ido.life.enums.PressureEnum;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PressureAjustPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\f\u001a\u00020\tJ\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/ido/life/module/home/pressure/ajust/PressureAjustPresenter;", "Lcom/ido/life/base/BasePresenter;", "Lcom/ido/life/module/home/pressure/ajust/PressureAjustView;", "()V", "mAjustProgress", "", "mHandle", "Landroid/os/Handler;", "mPressureAjusting", "", "mUpdateDelay", "", "isAjusting", "startAjust", "", "stopAjust", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class PressureAjustPresenter extends BasePresenter<PressureAjustView> {
    public static final int DURATION_MAX = 30;
    public static final int PRESSURE_AJUST_MAX = 183;
    private int mAjustProgress;
    private boolean mPressureAjusting;
    private final long mUpdateDelay = 163;
    private Handler mHandle = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.ido.life.module.home.pressure.ajust.PressureAjustPresenter$mHandle$1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message it) {
            Intrinsics.checkParameterIsNotNull(it, "it");
            this.this$0.mAjustProgress++;
            if (this.this$0.mAjustProgress >= 183) {
                this.this$0.mPressureAjusting = false;
                if (this.this$0.isAttachView()) {
                    PressureAjustView view = this.this$0.getView();
                    if (view != null) {
                        float f2 = 30;
                        view.updateAjustProgress(this.this$0.mAjustProgress, (int) (f2 - ((this.this$0.mAjustProgress / 183) * f2)));
                    }
                    PressureAjustView view2 = this.this$0.getView();
                    if (view2 != null) {
                        view2.ajustSuccess(PressureEnum.HIGH.Max * ((int) Math.random()));
                    }
                }
            } else if (this.this$0.isAttachView()) {
                PressureAjustView view3 = this.this$0.getView();
                if (view3 != null) {
                    float f3 = 30;
                    view3.updateAjustProgress(this.this$0.mAjustProgress, (int) (f3 - ((this.this$0.mAjustProgress / 183) * f3)));
                }
                it.getTarget().sendEmptyMessageDelayed(1, this.this$0.mUpdateDelay);
            }
            return true;
        }
    });

    public final void startAjust() {
        if (isAttachView()) {
            this.mPressureAjusting = true;
            PressureAjustView view = getView();
            if (view != null) {
                view.updateAjustProgress(this.mAjustProgress, 30);
            }
            this.mHandle.sendEmptyMessageDelayed(1, this.mUpdateDelay);
        }
    }

    public final void stopAjust() {
        this.mHandle.removeMessages(1);
    }

    /* JADX INFO: renamed from: isAjusting, reason: from getter */
    public final boolean getMPressureAjusting() {
        return this.mPressureAjusting;
    }
}