package com.ido.life.customview.viewgroup;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.ScreenUtil;
import com.ido.life.customview.viewgroup.SlideToggleView;
import com.ido.life.module.sport.view.UnlockView;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class SportControlView extends LinearLayout implements View.OnClickListener {
    private static final int DELAY_DURATION = 500;
    private static final String TAG = "SportControlViewAnim";
    private static final int animDuration = 350;
    private long clickNowTime;
    private int controllerStatus;
    private boolean isAlphaAnim;
    private boolean isTransAnim;
    private ImageView mIbPause;
    private ImageButton mIbStart;
    private ImageView mIvLock;
    private ImageView mIvSportSound;
    private OnSportPauseListener mOnSportPauseListener;
    private SlideToggleView mSlideToggleView;
    private float mSoundAlpha;
    private int mStatus;
    private UnlockView mStopView;
    private long nowTime;
    private float padding;

    public interface OnSportPauseListener {
        void onPause();

        void onRestart();

        void onSoundOnOff();

        void onStop();
    }

    public void setOnSportPauseListener(OnSportPauseListener onSportPauseListener) {
        this.mOnSportPauseListener = onSportPauseListener;
    }

    public SportControlView(Context context) {
        this(context, null);
    }

    public SportControlView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SportControlView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mStatus = -1;
        initView(context);
    }

    private void initView(Context context) {
        this.nowTime = System.currentTimeMillis();
        LayoutInflater.from(context).inflate(R.layout.layout_sport_unlock, this);
        this.mIbPause = (ImageView) findViewById(R.id.ib_pause);
        this.mIbStart = (ImageButton) findViewById(R.id.ib_start);
        this.mIvLock = (ImageView) findViewById(R.id.iv_lock_off);
        this.mIbStart = (ImageButton) findViewById(R.id.ib_start);
        this.mStopView = (UnlockView) findViewById(R.id.unlock_end_view);
        this.mIvSportSound = (ImageView) findViewById(R.id.iv_sport_sound);
        this.mSlideToggleView = (SlideToggleView) findViewById(R.id.slideToggleView);
        this.mIbStart.setOnClickListener(this);
        this.mIbPause.setOnClickListener(this);
        this.mIvLock.setOnClickListener(this);
        this.mIvSportSound.setOnClickListener(this);
        this.mStopView.setUnLockListener(new UnlockView.UnLockListener() { // from class: com.ido.life.customview.viewgroup.-$$Lambda$SportControlView$qgfN9SIJcCE5xe7mlnic_ekSd9s
            @Override // com.ido.life.module.sport.view.UnlockView.UnLockListener
            public final void lockSuccess() {
                this.f$0.lambda$initView$0$SportControlView();
            }
        });
        this.mSlideToggleView.setSlideToggleListener(new SlideToggleView.SlideToggleListener() { // from class: com.ido.life.customview.viewgroup.SportControlView.1
            @Override // com.ido.life.customview.viewgroup.SlideToggleView.SlideToggleListener
            public void onBlockPositionChanged(SlideToggleView slideToggleView, int i, int i2, int i3) {
            }

            @Override // com.ido.life.customview.viewgroup.SlideToggleView.SlideToggleListener
            public void onSlideOpen(SlideToggleView slideToggleView) {
                SportControlView.this.mSlideToggleView.setVisibility(8);
                if (SportControlView.this.controllerStatus == 0) {
                    SportControlView.this.mIvLock.setVisibility(0);
                    SportControlView.this.mIvSportSound.setVisibility(0);
                    SportControlView.this.onPause();
                } else {
                    SportControlView.this.mIvLock.setVisibility(0);
                    SportControlView.this.mIbPause.setVisibility(0);
                    SportControlView.this.mIvSportSound.setVisibility(0);
                }
            }
        });
    }

    public /* synthetic */ void lambda$initView$0$SportControlView() {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), TAG, "停止运动 mOnSportPauseListener=" + this.mOnSportPauseListener);
        OnSportPauseListener onSportPauseListener = this.mOnSportPauseListener;
        if (onSportPauseListener != null) {
            onSportPauseListener.onStop();
        }
    }

    public void setSoundAlpha(float f2) {
        this.mSoundAlpha = f2;
    }

    public void setSportStatus(int i) {
        if (i == 0) {
            onPause();
        } else if (i == 1) {
            onContinue();
        } else if (i == 2) {
            this.mIbPause.setVisibility(8);
            this.mIvSportSound.setVisibility(8);
            this.mIvLock.setVisibility(8);
        }
        this.controllerStatus = i;
    }

    public void onContinue() {
        if (System.currentTimeMillis() - this.nowTime < 350) {
            this.mIvLock.clearAnimation();
            this.mIvSportSound.clearAnimation();
            this.mStopView.clearAnimation();
            this.mIbStart.clearAnimation();
            this.mIbPause.clearAnimation();
        }
        this.nowTime = System.currentTimeMillis();
        if (this.mStatus == 1) {
            return;
        }
        if (this.mSlideToggleView.getVisibility() == 0) {
            this.controllerStatus = 2;
            return;
        }
        final float screenW = ((ScreenUtil.getScreenW() / 2) - this.padding) - DipPixelUtil.dip2px(55.0f);
        createAnim(this.mIvLock, animDuration, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f).start();
        createAnim(this.mIvSportSound, animDuration, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, this.mSoundAlpha).start();
        ObjectAnimator translateAnimation = getTranslateAnimation(this.mIbStart, animDuration, screenW, 0.0f);
        translateAnimation.start();
        getTranslateAnimation(this.mStopView, animDuration, -screenW, 0.0f).start();
        translateAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ido.life.customview.viewgroup.-$$Lambda$SportControlView$egrwftfSks3ehwdQeCZ0H3_iHeo
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f$0.lambda$onContinue$1$SportControlView(screenW, valueAnimator);
            }
        });
        alphaAnimation(this.mIbPause, 1000, 0.0f, 1.0f).start();
        this.mStatus = 1;
    }

    public /* synthetic */ void lambda$onContinue$1$SportControlView(float f2, ValueAnimator valueAnimator) {
        if (this.isTransAnim) {
            if (((Float) valueAnimator.getAnimatedValue()).floatValue() <= 0.0f) {
                this.isTransAnim = false;
            }
        } else if (((Float) valueAnimator.getAnimatedValue()).floatValue() <= f2 / 3.0f) {
            this.isTransAnim = true;
            alphaAnimation(this.mStopView, animDuration, 1.0f, 0.0f).start();
            alphaAnimation(this.mIbStart, animDuration, 1.0f, 0.0f).start();
        }
    }

    public void onPause() {
        if (System.currentTimeMillis() - this.nowTime < 350) {
            this.mIbPause.clearAnimation();
            this.mStopView.clearAnimation();
            this.mIbStart.clearAnimation();
            this.mIvLock.clearAnimation();
            this.mIvSportSound.clearAnimation();
        }
        this.nowTime = System.currentTimeMillis();
        if (this.mStatus == 0) {
            return;
        }
        String sportLogPath = LogPathImpl.getInstance().getSportLogPath();
        StringBuilder sb = new StringBuilder();
        sb.append("onPause1: ");
        sb.append(this.mSlideToggleView.getVisibility() == 0);
        CommonLogUtil.printAndSave(sportLogPath, TAG, sb.toString());
        if (this.mSlideToggleView.getVisibility() == 0) {
            this.controllerStatus = 2;
            return;
        }
        String sportLogPath2 = LogPathImpl.getInstance().getSportLogPath();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("onPause2: ");
        sb2.append(this.mSlideToggleView.getVisibility() == 0);
        CommonLogUtil.printAndSave(sportLogPath2, TAG, sb2.toString());
        this.padding = (ScreenUtil.getScreenW() * 120) / 720;
        this.mIvSportSound.post(new Runnable() { // from class: com.ido.life.customview.viewgroup.-$$Lambda$SportControlView$52JxqNDtT7mmVubVR3IS7UDN2fI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onPause$2$SportControlView();
            }
        });
    }

    public /* synthetic */ void lambda$onPause$2$SportControlView() {
        alphaAnimation(this.mIbPause, animDuration, 1.0f, 0.0f).start();
        alphaAnimation(this.mStopView, animDuration, 0.0f, 1.0f).start();
        alphaAnimation(this.mIbStart, animDuration, 0.0f, 1.0f).start();
        createAnim(this.mIvLock, animDuration, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f).start();
        createAnim(this.mIvSportSound, animDuration, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f).start();
        getTranslateAnimation(this.mStopView, animDuration, 0.0f, -(((ScreenUtil.getScreenW() / 2) - this.padding) - DipPixelUtil.dip2px(55.0f))).start();
        getTranslateAnimation(this.mIbStart, animDuration, 0.0f, ((ScreenUtil.getScreenW() / 2) - this.padding) - DipPixelUtil.dip2px(55.0f)).start();
        this.mStatus = 0;
    }

    private ObjectAnimator getTranslateAnimation(View view, int i, float f2, float f3) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), TAG, "getTranslateAnimation: " + i + AppInfo.DELIM + f2 + AppInfo.DELIM + f3);
        view.clearAnimation();
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "translationX", f2, f3);
        objectAnimatorOfFloat.setDuration((long) i);
        objectAnimatorOfFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        return objectAnimatorOfFloat;
    }

    private AnimatorSet createAnim(View view, int i, float f2, float f3, float f4, float f5, float f6, float f7) {
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "ScaleX", f2, f3);
        objectAnimatorOfFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        long j = i;
        objectAnimatorOfFloat.setDuration(j);
        arrayList.add(objectAnimatorOfFloat);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view, "ScaleY", f4, f5);
        objectAnimatorOfFloat2.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimatorOfFloat2.setDuration(j);
        arrayList.add(objectAnimatorOfFloat2);
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(view, "alpha", f6, f7);
        objectAnimatorOfFloat3.setInterpolator(new LinearInterpolator());
        objectAnimatorOfFloat3.setDuration(j);
        arrayList.add(objectAnimatorOfFloat3);
        animatorSet.playTogether(arrayList);
        return animatorSet;
    }

    private ObjectAnimator alphaAnimation(View view, int i, float f2, float f3) {
        view.clearAnimation();
        if (f3 == 0.0f) {
            view.setVisibility(8);
        } else {
            view.setAlpha(f2);
            view.setVisibility(0);
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "Alpha", f2, f3);
        objectAnimatorOfFloat.setDuration(i);
        objectAnimatorOfFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        return objectAnimatorOfFloat;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_pause /* 2131362270 */:
                if (System.currentTimeMillis() - this.clickNowTime >= 500) {
                    this.clickNowTime = System.currentTimeMillis();
                    OnSportPauseListener onSportPauseListener = this.mOnSportPauseListener;
                    if (onSportPauseListener != null) {
                        onSportPauseListener.onPause();
                    }
                    break;
                }
                break;
            case R.id.ib_start /* 2131362271 */:
                if (System.currentTimeMillis() - this.clickNowTime >= 500) {
                    this.clickNowTime = System.currentTimeMillis();
                    OnSportPauseListener onSportPauseListener2 = this.mOnSportPauseListener;
                    if (onSportPauseListener2 != null) {
                        onSportPauseListener2.onRestart();
                    }
                    break;
                }
                break;
            case R.id.iv_lock_off /* 2131362587 */:
                if (System.currentTimeMillis() - this.clickNowTime >= 500 && this.mStatus != 0) {
                    this.clickNowTime = System.currentTimeMillis();
                    this.mSlideToggleView.setVisibility(0);
                    setSportStatus(2);
                    break;
                }
                break;
            case R.id.iv_sport_sound /* 2131362617 */:
                if (System.currentTimeMillis() - this.clickNowTime >= 500) {
                    this.clickNowTime = System.currentTimeMillis();
                    OnSportPauseListener onSportPauseListener3 = this.mOnSportPauseListener;
                    if (onSportPauseListener3 != null) {
                        onSportPauseListener3.onSoundOnOff();
                    }
                    break;
                }
                break;
        }
    }
}