package com.ido.life.module.sport.ready;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseCoreActivity;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ScreenUtil;
import com.ido.life.ble.BleSdkWrapper;
import com.ido.life.module.sport.ready.SportRunReadyContract;
import com.ido.life.module.sport.run.SportRunActivity;
import java.util.ArrayList;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes2.dex */
public class SportRunReadyActivity extends BaseCoreActivity implements SportRunReadyContract.View {
    public static final int DEFAULT_TIME = 60;
    private static final String EXTRA_CIRCULAR_CENTER_X = "circular_center_x";
    private static final String EXTRA_CIRCULAR_CENTER_Y = "circular_center_y";
    private static final String EXTRA_SPORT_OUT = "sport_out";
    private static final String EXTRA_TYPE = "sport_type";
    private static final String TAG = "SportRunReadyActivity";

    @BindView(R.id.alphaImageView)
    View alphaImageView;
    Animator.AnimatorListener animationListener;
    private boolean mIsOurDoor;
    private SportRunReadyContract.Presenter mPresenter;
    private int mSportType;

    @BindView(R.id.tv_ready)
    TextView mTvReady;
    private int revealX;
    private int revealY;

    @BindView(R.id.root_layout)
    View rootLayout;
    private int animationCount = 4;
    private boolean isDelay = true;
    private Handler mUpdateHandler = new Handler();
    private long mChangeIndex = 0;
    private final int UPDATE_DATA_INTERVAL = 1000;
    private boolean mIsCharge = false;
    RunTimerTask runTimerTask = new RunTimerTask();
    private Runnable mStartSportRunnable = new Runnable() { // from class: com.ido.life.module.sport.ready.SportRunReadyActivity.1
        @Override // java.lang.Runnable
        public void run() {
            if (SportRunReadyActivity.this.mIsCharge) {
                SportRunReadyActivity.this.mPresenter.startRun(SportRunReadyActivity.this.mSportType, true);
                return;
            }
            if (BleSdkWrapper.isConnected()) {
                SportRunReadyActivity.this.mPresenter.startRun(SportRunReadyActivity.this.mSportType, false);
                SportRunReadyActivity.this.startUpdateTimer();
            } else if (!BleSdkWrapper.isBind() || BleSdkWrapper.isConnected()) {
                SportRunReadyActivity.this.mPresenter.startRun(SportRunReadyActivity.this.mSportType, true);
            } else {
                SportRunReadyActivity.this.showRemindNoConnectDialog();
            }
        }
    };
    int i = 0;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_sport_ready;
    }

    static /* synthetic */ long access$108(SportRunReadyActivity sportRunReadyActivity) {
        long j = sportRunReadyActivity.mChangeIndex;
        sportRunReadyActivity.mChangeIndex = 1 + j;
        return j;
    }

    static /* synthetic */ int access$1110(SportRunReadyActivity sportRunReadyActivity) {
        int i = sportRunReadyActivity.animationCount;
        sportRunReadyActivity.animationCount = i - 1;
        return i;
    }

    private class RunTimerTask extends TimerTask {
        private RunTimerTask() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            SportRunReadyActivity.access$108(SportRunReadyActivity.this);
            if (SportRunReadyActivity.this.mChangeIndex >= 60) {
                ActivityCompat.finishAfterTransition(SportRunReadyActivity.this);
            }
            SportRunReadyActivity.this.mUpdateHandler.postDelayed(this, 1000L);
        }
    }

    public static void startActivity(Activity activity, int i, boolean z) {
        Intent intent = new Intent(activity, (Class<?>) SportRunReadyActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("sport_type", i);
        bundle.putBoolean(EXTRA_SPORT_OUT, z);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    public void startUpdateTimer() {
        this.mUpdateHandler.removeCallbacks(this.runTimerTask);
        this.mUpdateHandler.post(this.runTimerTask);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showRemindNoConnectDialog() {
        final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(LanguageUtil.getLanguageText(R.string.sport_device_ble), LanguageUtil.getLanguageText(R.string.sport_device_ble_disconnect_two), LanguageUtil.getLanguageText(R.string.sport_device_ble_disconnect_yes), LanguageUtil.getLanguageText(R.string.sport_device_ble_disconnect_no), true);
        commBottomConfirmDialogNewInstance.setCancelable(false);
        commBottomConfirmDialogNewInstance.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.ready.SportRunReadyActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
                ActivityCompat.finishAfterTransition(SportRunReadyActivity.this);
            }
        });
        commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.ready.SportRunReadyActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
                SportRunReadyActivity.this.mPresenter.startRun(SportRunReadyActivity.this.mSportType, true);
            }
        });
        getSupportFragmentManager().beginTransaction().add(commBottomConfirmDialogNewInstance, "").commitAllowingStateLoss();
    }

    public static void startActivity(Activity activity, int i, boolean z, View view, String str) {
        int x = (int) (view.getX() + (view.getWidth() / 2));
        int y = (int) (view.getY() + (view.getHeight() / 2));
        Intent intent = new Intent(activity, (Class<?>) SportRunReadyActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("sport_type", i);
        bundle.putBoolean(EXTRA_SPORT_OUT, z);
        bundle.putInt(EXTRA_CIRCULAR_CENTER_X, x);
        bundle.putInt(EXTRA_CIRCULAR_CENTER_Y, y);
        intent.putExtras(bundle);
        ActivityCompat.startActivity(activity, intent, ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, str).toBundle());
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mPresenter.setOnStartListener();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        this.mPresenter = new SportRunReadyPresenter(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.mSportType = extras.getInt("sport_type");
            this.mIsOurDoor = extras.getBoolean(EXTRA_SPORT_OUT, true);
        }
        this.mPresenter.playTipsMusic();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initViews() {
        super.initViews();
        if (Build.VERSION.SDK_INT >= 21 && getIntent().hasExtra(EXTRA_CIRCULAR_CENTER_X) && getIntent().hasExtra(EXTRA_CIRCULAR_CENTER_Y)) {
            this.rootLayout.setVisibility(4);
            this.revealX = getIntent().getIntExtra(EXTRA_CIRCULAR_CENTER_X, 0);
            this.revealY = getIntent().getIntExtra(EXTRA_CIRCULAR_CENTER_Y, 0);
            ViewTreeObserver viewTreeObserver = this.rootLayout.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.ido.life.module.sport.ready.SportRunReadyActivity.4
                    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                    public void onGlobalLayout() {
                        SportRunReadyActivity.this.circularRevealActivity();
                        SportRunReadyActivity.this.rootLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
                return;
            }
            return;
        }
        this.rootLayout.setVisibility(0);
        startCountDownAnimator();
        this.mUpdateHandler.postDelayed(this.mStartSportRunnable, 3000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void circularRevealActivity() {
        Animator animatorCreateCircularReveal = ViewAnimationUtils.createCircularReveal(this.rootLayout, this.revealX, this.revealY, 0.0f, Math.max(this.rootLayout.getWidth(), this.rootLayout.getHeight()));
        animatorCreateCircularReveal.setDuration(400L);
        animatorCreateCircularReveal.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorCreateCircularReveal.addListener(new Animator.AnimatorListener() { // from class: com.ido.life.module.sport.ready.SportRunReadyActivity.5
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                SportRunReadyActivity.this.startCountDownAnimator();
                SportRunReadyActivity.this.mUpdateHandler.postDelayed(SportRunReadyActivity.this.mStartSportRunnable, 3000L);
            }
        });
        this.rootLayout.setVisibility(0);
        animatorCreateCircularReveal.start();
    }

    private AnimatorSet createAnim(View view, int i) {
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "ScaleX", 0.5f, 1.2f);
        objectAnimatorOfFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        long j = i;
        objectAnimatorOfFloat.setDuration(j);
        arrayList.add(objectAnimatorOfFloat);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view, "ScaleY", 0.5f, 1.2f);
        objectAnimatorOfFloat2.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimatorOfFloat2.setDuration(j);
        arrayList.add(objectAnimatorOfFloat2);
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f);
        objectAnimatorOfFloat3.setInterpolator(new LinearInterpolator());
        objectAnimatorOfFloat3.setDuration(j);
        arrayList.add(objectAnimatorOfFloat3);
        animatorSet.playTogether(arrayList);
        return animatorSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AnimatorSet createAnim2(View view, int i) {
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "ScaleX", 1.2f, 1.8f);
        objectAnimatorOfFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        long j = i;
        objectAnimatorOfFloat.setDuration(j);
        arrayList.add(objectAnimatorOfFloat);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view, "ScaleY", 1.2f, 1.8f);
        objectAnimatorOfFloat2.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimatorOfFloat2.setDuration(j);
        arrayList.add(objectAnimatorOfFloat2);
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.0f);
        objectAnimatorOfFloat3.setInterpolator(new LinearInterpolator());
        objectAnimatorOfFloat3.setDuration(j);
        arrayList.add(objectAnimatorOfFloat3);
        animatorSet.playTogether(arrayList);
        return animatorSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AnimatorSet createAnim3(View view, int i) {
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "ScaleX", 0.0f, 1.2f, 1.8f);
        objectAnimatorOfFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        long j = i;
        objectAnimatorOfFloat.setDuration(j);
        arrayList.add(objectAnimatorOfFloat);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view, "ScaleY", 0.0f, 1.2f, 1.8f);
        objectAnimatorOfFloat2.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimatorOfFloat2.setDuration(j);
        arrayList.add(objectAnimatorOfFloat2);
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f, 0.0f);
        objectAnimatorOfFloat3.setInterpolator(new LinearInterpolator());
        objectAnimatorOfFloat3.setDuration(j);
        arrayList.add(objectAnimatorOfFloat3);
        animatorSet.playTogether(arrayList);
        return animatorSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startCountDownAnimator() {
        AnimatorSet animatorSetCreateAnim = createAnim(this.mTvReady, 100);
        animatorSetCreateAnim.start();
        this.animationListener = new Animator.AnimatorListener() { // from class: com.ido.life.module.sport.ready.SportRunReadyActivity.6
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                if (ScreenUtil.isLeEcoPhone() && SportRunReadyActivity.this.isDelay) {
                    SportRunReadyActivity.this.animationCount++;
                    SportRunReadyActivity.this.isDelay = false;
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                SportRunReadyActivity.this.i++;
                if (SportRunReadyActivity.this.i == 1) {
                    SportRunReadyActivity.this.mUpdateHandler.postDelayed(new Runnable() { // from class: com.ido.life.module.sport.ready.SportRunReadyActivity.6.1
                        @Override // java.lang.Runnable
                        public void run() {
                            AnimatorSet animatorSetCreateAnim2 = SportRunReadyActivity.this.createAnim2(SportRunReadyActivity.this.mTvReady, 300);
                            animatorSetCreateAnim2.start();
                            animatorSetCreateAnim2.addListener(SportRunReadyActivity.this.animationListener);
                            SportRunReadyActivity.this.createAnim3(SportRunReadyActivity.this.alphaImageView, 300).start();
                        }
                    }, 500L);
                    return;
                }
                SportRunReadyActivity.access$1110(SportRunReadyActivity.this);
                CommonLogUtil.d("count===" + SportRunReadyActivity.this.animationCount);
                if (SportRunReadyActivity.this.animationCount > 0) {
                    SportRunReadyActivity.this.mTvReady.clearAnimation();
                    if (SportRunReadyActivity.this.animationCount == 1) {
                        SportRunReadyActivity.this.mTvReady.setText("GO");
                    } else {
                        SportRunReadyActivity.this.mTvReady.setText((SportRunReadyActivity.this.animationCount - 1) + "");
                    }
                    SportRunReadyActivity sportRunReadyActivity = SportRunReadyActivity.this;
                    sportRunReadyActivity.i = 0;
                    sportRunReadyActivity.startCountDownAnimator();
                    return;
                }
                SportRunReadyActivity.this.mTvReady.setVisibility(8);
                SportRunReadyActivity.this.alphaImageView.setVisibility(8);
            }
        };
        animatorSetCreateAnim.addListener(this.animationListener);
    }

    private void jumpRunActivity() {
        this.mTvReady.setText("");
        this.mTvReady.clearAnimation();
        finish();
        SportRunActivity.startActivity(this, this.mSportType, this.mIsOurDoor);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Handler handler = this.mUpdateHandler;
        if (handler != null) {
            handler.removeCallbacks(this.runTimerTask);
        }
        this.mPresenter.release();
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(SportRunReadyContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override // com.ido.life.module.sport.ready.SportRunReadyContract.View
    public void showSportStartSuccess() {
        jumpRunActivity();
    }

    @Override // com.ido.life.module.sport.ready.SportRunReadyContract.View
    public void showSportStartFailedLowPower() {
        CommonLogUtil.printAndSave(TAG, "showSportStartFailedLowPower: 低电量");
        Handler handler = this.mUpdateHandler;
        if (handler != null) {
            handler.removeCallbacks(this.runTimerTask);
        }
        if (this.mIsOurDoor) {
            final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(LanguageUtil.getLanguageText(R.string.sport_low_power_title), LanguageUtil.getLanguageText(R.string.sport_low_power_two), LanguageUtil.getLanguageText(R.string.sport_low_power_yes), LanguageUtil.getLanguageText(R.string.sport_low_power_no), true);
            commBottomConfirmDialogNewInstance.setCancelable(false);
            commBottomConfirmDialogNewInstance.show(getSupportFragmentManager());
            commBottomConfirmDialogNewInstance.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.ready.SportRunReadyActivity.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
                    SportRunReadyActivity.this.finish();
                }
            });
            commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.ready.SportRunReadyActivity.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
                    SportRunReadyActivity.this.mPresenter.startRun(SportRunReadyActivity.this.mSportType, true);
                }
            });
            return;
        }
        final CommBottomConfirmDialog commBottomConfirmDialogNewInstance2 = CommBottomConfirmDialog.newInstance(LanguageUtil.getLanguageText(R.string.sport_low_power_title), LanguageUtil.getLanguageText(R.string.sport_low_power_one), LanguageUtil.getLanguageText(R.string.sync_ok), LanguageUtil.getLanguageText(R.string.sync_ok), false);
        commBottomConfirmDialogNewInstance2.setCancelable(false);
        commBottomConfirmDialogNewInstance2.show(getSupportFragmentManager());
        commBottomConfirmDialogNewInstance2.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.ready.SportRunReadyActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                commBottomConfirmDialogNewInstance2.dismissAllowingStateLoss();
                SportRunReadyActivity.this.finish();
            }
        });
    }

    @Override // com.ido.life.module.sport.ready.SportRunReadyContract.View
    public void showSportStartFail() {
        Handler handler = this.mUpdateHandler;
        if (handler != null) {
            handler.removeCallbacks(this.runTimerTask);
        }
        final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(LanguageUtil.getLanguageText(R.string.sport_dialog_changeing), LanguageUtil.getLanguageText(R.string.public_yes), LanguageUtil.getLanguageText(R.string.public_no), true);
        commBottomConfirmDialogNewInstance.setCancelable(false);
        commBottomConfirmDialogNewInstance.show(getSupportFragmentManager());
        commBottomConfirmDialogNewInstance.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.ready.SportRunReadyActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
                SportRunReadyActivity.this.finish();
            }
        });
        commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.ready.SportRunReadyActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
                SportRunReadyActivity.this.mPresenter.forceStartRun();
            }
        });
    }

    @Override // com.ido.life.module.sport.ready.SportRunReadyContract.View
    public void showSportStartFailedChargePower() {
        showToast(getString(R.string.sport_device_charge_power), 0);
        if (this.mIsOurDoor) {
            this.mPresenter.startRun(this.mSportType, true);
            return;
        }
        final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(LanguageUtil.getLanguageText(R.string.sport_charge_power_title), LanguageUtil.getLanguageText(R.string.sport_charge_power_message), LanguageUtil.getLanguageText(R.string.i_see), LanguageUtil.getLanguageText(R.string.i_see), false);
        commBottomConfirmDialogNewInstance.setCancelable(false);
        commBottomConfirmDialogNewInstance.show(getSupportFragmentManager());
        commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.ready.SportRunReadyActivity.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
                SportRunReadyActivity.this.finish();
            }
        });
    }

    @Override // com.ido.life.module.sport.ready.SportRunReadyContract.View
    public void showSportStartFailedInCalling() {
        if (this.mIsOurDoor) {
            this.mPresenter.startRun(this.mSportType, true);
        } else {
            showToast(LanguageUtil.getLanguageText(R.string.sport_start_incalling), 0);
            finish();
        }
    }

    @Override // com.ido.life.module.sport.ready.SportRunReadyContract.View
    public void showSportStartError(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        showToast(str);
        supportFinishAfterTransition();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.mChangeIndex < 10) {
            return;
        }
        super.onBackPressed();
    }
}