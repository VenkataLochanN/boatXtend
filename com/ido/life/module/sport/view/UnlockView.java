package com.ido.life.module.sport.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import androidx.core.app.NotificationCompat;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.DipPixelUtil;
import com.ido.life.R;
import com.veryfit.multi.nativeprotocol.b;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes2.dex */
public class UnlockView extends View {
    private static final String TAG = "UnlockView";
    private ObjectAnimator cancelAnimator;
    private long duration;
    private boolean isCancel;
    private boolean isFinishForScale;
    private UnLockListener lister;
    private int mPadding;
    private Paint mPaint;
    private int mRingColor;
    private float mStrokeWidth;
    private int mXCenter;
    private int mYCenter;
    private int progress;
    ValueAnimator.AnimatorUpdateListener progressAnimListener;
    private ObjectAnimator progressAnimator;
    private Timer timer;
    TimerTask timerTask;
    private Bitmap unLockBitmap;
    private Drawable unLockDrawable;

    interface AnimFinishListener {
        void finish();
    }

    public interface UnLockListener {
        void lockSuccess();
    }

    public UnlockView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isFinishForScale = true;
        this.mPadding = 0;
        this.progressAnimListener = new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.life.module.sport.view.-$$Lambda$UnlockView$fKlAjqykvGzfhVceCb7uos_tc4E
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f$0.lambda$new$2$UnlockView(valueAnimator);
            }
        };
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.UnlockView);
        this.unLockDrawable = typedArrayObtainStyledAttributes.getDrawable(2);
        this.mRingColor = typedArrayObtainStyledAttributes.getColor(0, 0);
        this.mStrokeWidth = typedArrayObtainStyledAttributes.getDimension(1, 10.0f);
        typedArrayObtainStyledAttributes.recycle();
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.unLockBitmap = ((BitmapDrawable) this.unLockDrawable).getBitmap();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mXCenter = getWidth() / 2;
        this.mYCenter = getHeight() / 2;
        this.mPaint.setColor(this.mRingColor);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(this.mStrokeWidth);
        if (this.progress > 0) {
            RectF rectF = new RectF();
            float f2 = this.mStrokeWidth;
            rectF.left = f2 / 2.0f;
            rectF.top = f2 / 2.0f;
            rectF.right = getWidth() - (this.mStrokeWidth / 2.0f);
            rectF.bottom = getHeight() - (this.mStrokeWidth / 2.0f);
            canvas.drawArc(rectF, -90.0f, this.progress, false, this.mPaint);
        }
        drawEndBitmap(canvas, this.mPadding);
    }

    private void drawEndBitmap(Canvas canvas, int i) {
        int i2 = i / 2;
        int i3 = i2 + 0;
        canvas.drawBitmap(this.unLockBitmap, (Rect) null, new Rect(i3, i3, getWidth() - i2, getWidth() - i2), this.mPaint);
    }

    public void setMPadding(int i) {
        this.mPadding = i;
        invalidate();
    }

    private void animateY(int i, int i2, final int i3, final AnimFinishListener animFinishListener) {
        if (!this.isFinishForScale) {
            if (animFinishListener != null) {
                animFinishListener.finish();
            }
        } else {
            this.isFinishForScale = false;
            ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(this, "mPadding", i2, i3);
            objectAnimatorOfInt.setDuration(i);
            objectAnimatorOfInt.setInterpolator(new LinearInterpolator());
            objectAnimatorOfInt.start();
            objectAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.life.module.sport.view.-$$Lambda$UnlockView$FkbF9ncXx_E1sps0r7IZe-uD-78
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    this.f$0.lambda$animateY$0$UnlockView(i3, animFinishListener, valueAnimator);
                }
            });
        }
    }

    public /* synthetic */ void lambda$animateY$0$UnlockView(int i, AnimFinishListener animFinishListener, ValueAnimator valueAnimator) {
        if (((Integer) valueAnimator.getAnimatedValue()).intValue() == i) {
            this.isFinishForScale = true;
            if (animFinishListener == null || this.isCancel) {
                return;
            }
            CommonLogUtil.d("pause progress == finish");
            animFinishListener.finish();
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        int action = motionEvent.getAction();
        if (action == 0) {
            this.isCancel = false;
            CommonLogUtil.d("pause progress == down");
            if (this.progress == 360) {
                setProgress(0);
            }
            animateY(b.Y, this.mPadding, DipPixelUtil.dip2px(12.0f), new AnimFinishListener() { // from class: com.ido.life.module.sport.view.-$$Lambda$UnlockView$kL2bVmdnlzlxxVhKNsH8b-7kgBY
                @Override // com.ido.life.module.sport.view.UnlockView.AnimFinishListener
                public final void finish() {
                    this.f$0.lambda$onTouchEvent$1$UnlockView();
                }
            });
        } else if (action == 1) {
            this.isCancel = true;
            closeTimer();
            CommonLogUtil.d("pause progress ====closeTimer==progress=" + this.progress);
            int i = this.progress;
            if (i == 0) {
                this.isFinishForScale = true;
                clearAnimation();
                animateY(100, this.mPadding, 0, null);
            } else {
                this.duration = i * 3;
                this.cancelAnimator = ObjectAnimator.ofInt(this, NotificationCompat.CATEGORY_PROGRESS, i, 0).setDuration(this.duration);
                this.cancelAnimator.setInterpolator(new LinearInterpolator());
                this.cancelAnimator.addUpdateListener(this.progressAnimListener);
                this.cancelAnimator.start();
            }
        }
        return true;
    }

    public /* synthetic */ void lambda$onTouchEvent$1$UnlockView() {
        if (this.progress <= 360) {
            this.timer = new Timer();
            TimerTask timerTask = this.timerTask;
            if (timerTask != null) {
                timerTask.cancel();
                this.timerTask = null;
            }
            this.timerTask = new AnonymousClass1();
            this.timer.schedule(this.timerTask, 0L, 2L);
            CommonLogUtil.d("pause progress ====startTimer");
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.sport.view.UnlockView$1, reason: invalid class name */
    class AnonymousClass1 extends TimerTask {
        AnonymousClass1() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (UnlockView.this.isCancel) {
                return;
            }
            UnlockView.this.progress++;
            UnlockView.this.postInvalidate();
            if (UnlockView.this.lister == null || UnlockView.this.progress != 360) {
                return;
            }
            UnlockView.this.post(new Runnable() { // from class: com.ido.life.module.sport.view.-$$Lambda$UnlockView$1$xnYQAmQKeUTU0EvXuy9b1Vl4e_Y
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$run$0$UnlockView$1();
                }
            });
            UnlockView.this.closeTimer();
        }

        public /* synthetic */ void lambda$run$0$UnlockView$1() {
            UnlockView.this.lister.lockSuccess();
        }
    }

    public /* synthetic */ void lambda$new$2$UnlockView(ValueAnimator valueAnimator) {
        if (((Integer) valueAnimator.getAnimatedValue()).intValue() == 0) {
            CommonLogUtil.d("pause progress ====cancel");
            setProgress(0);
            animateY(100, this.mPadding, 0, null);
        }
    }

    public void setProgress(int i) {
        this.progress = i;
        invalidate();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        CommonLogUtil.d("onDetachedFromWindow");
        closeTimer();
    }

    public void setUnLockListener(UnLockListener unLockListener) {
        this.lister = unLockListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeTimer() {
        Timer timer = this.timer;
        if (timer != null) {
            timer.cancel();
            this.timer = null;
        }
    }
}