package com.ido.life.module.guide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.OnClick;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.ido.common.adapter.GuideFragmentPagerAdapter;
import com.ido.common.base.FixedSpeedScroller;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.utils.StatusBarUtil;
import com.ido.life.base.BaseActivity;
import com.ido.life.module.guide.WelcomeGuideContract;
import com.ido.life.module.user.login.PreLoginAndRegisterActivity;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class WelcomeGuideActivity extends BaseActivity implements WelcomeGuideContract.View {
    private static final String TAG = "WelcomeGuideActivity";
    Handler handler;

    @BindView(R.id.btn_start)
    Button mBtnStart;
    protected List<Fragment> mFragmentList;
    private GuideFirstFragment mGuideFirstFragment;
    private GuideSecondFragment mGuideSecondFragment;
    private GuideThirdFragment mGuideThirdFragment;

    @BindView(R.id.iv_one_dot)
    ImageView mIvOneDot;

    @BindView(R.id.iv_three_dot)
    ImageView mIvThreeDot;

    @BindView(R.id.iv_two_dot)
    ImageView mIvTwoDot;
    private WelcomeGuideContract.Presenter mPresenter;

    @BindView(R.id.guide_viewpager)
    ViewPager mViewPager;
    private GuideFragmentPagerAdapter mViewPagerAdapter;
    ViewPagerSmoothRunnable runnable = new ViewPagerSmoothRunnable();

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_welcome_guide;
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(WelcomeGuideContract.Presenter presenter) {
    }

    public static void startActivity(Activity activity) {
        activity.startActivity(new Intent(activity, (Class<?>) WelcomeGuideActivity.class));
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatusBarColor(getColor(R.color.color_1E1E1E), false);
        initListener();
    }

    @OnClick({R.id.btn_start})
    public void doClickStart(View view) {
        this.mPresenter.toShowPrivicy(getSupportFragmentManager());
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mBtnStart.setText(getLanguageText(R.string.logn_lauch_guide_start));
    }

    private void initListener() {
        this.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.ido.life.module.guide.WelcomeGuideActivity.1
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f2, int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                if (i == 0) {
                    WelcomeGuideActivity.this.mIvOneDot.setVisibility(0);
                    WelcomeGuideActivity.this.mIvTwoDot.setVisibility(0);
                    WelcomeGuideActivity.this.mIvThreeDot.setVisibility(0);
                    WelcomeGuideActivity.this.mIvOneDot.setImageDrawable(ResourceUtil.getDrawable(R.drawable.shape_dot_orange_guide));
                    WelcomeGuideActivity.this.mIvTwoDot.setImageDrawable(ResourceUtil.getDrawable(R.drawable.shape_dot_gray_guide));
                    WelcomeGuideActivity.this.mIvThreeDot.setImageDrawable(ResourceUtil.getDrawable(R.drawable.shape_dot_gray_guide));
                    WelcomeGuideActivity.this.mBtnStart.setVisibility(4);
                    return;
                }
                if (i != 1) {
                    if (i != 2) {
                        return;
                    }
                    WelcomeGuideActivity.this.mIvOneDot.setVisibility(4);
                    WelcomeGuideActivity.this.mIvTwoDot.setVisibility(4);
                    WelcomeGuideActivity.this.mIvThreeDot.setVisibility(4);
                    WelcomeGuideActivity.this.mBtnStart.setVisibility(0);
                    return;
                }
                WelcomeGuideActivity.this.mIvOneDot.setVisibility(0);
                WelcomeGuideActivity.this.mIvTwoDot.setVisibility(0);
                WelcomeGuideActivity.this.mIvThreeDot.setVisibility(0);
                WelcomeGuideActivity.this.mIvOneDot.setImageDrawable(ResourceUtil.getDrawable(R.drawable.shape_dot_gray_guide));
                WelcomeGuideActivity.this.mIvTwoDot.setImageDrawable(ResourceUtil.getDrawable(R.drawable.shape_dot_orange_guide));
                WelcomeGuideActivity.this.mIvThreeDot.setImageDrawable(ResourceUtil.getDrawable(R.drawable.shape_dot_gray_guide));
                WelcomeGuideActivity.this.mBtnStart.setVisibility(4);
            }
        });
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        this.mFragmentList = new ArrayList();
        this.mGuideFirstFragment = GuideFirstFragment.newInstance();
        this.mGuideSecondFragment = GuideSecondFragment.newInstance();
        this.mGuideThirdFragment = GuideThirdFragment.newInstance();
        this.mFragmentList.add(this.mGuideFirstFragment);
        this.mFragmentList.add(this.mGuideSecondFragment);
        this.mFragmentList.add(this.mGuideThirdFragment);
        this.mViewPagerAdapter = new GuideFragmentPagerAdapter(getSupportFragmentManager(), this.mFragmentList);
        this.mViewPager.setAdapter(this.mViewPagerAdapter);
        this.mViewPager.setOffscreenPageLimit(3);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mPresenter = new WelcomeGuidePresenter(this);
    }

    private void initTitleBar() {
        StatusBarUtil.StatusBarLightMode((Activity) this, true);
        StatusBarUtil.transparencyBar(this, true);
    }

    @OnClick({R.id.iv_one_dot, R.id.iv_two_dot, R.id.iv_three_dot})
    public void dotClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_one_dot) {
            this.mViewPager.setCurrentItem(0, false);
        } else if (id == R.id.iv_three_dot) {
            this.mViewPager.setCurrentItem(2, false);
        } else {
            if (id != R.id.iv_two_dot) {
                return;
            }
            this.mViewPager.setCurrentItem(1, false);
        }
    }

    private void startLooper() {
        this.handler = new Handler();
        this.handler.postDelayed(this.runnable, 2000L);
    }

    @Override // com.ido.life.module.guide.WelcomeGuideContract.View
    public void toPreLoginAndRegister() {
        startActivity(new Intent(this, (Class<?>) PreLoginAndRegisterActivity.class));
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "从引导页跳转到: PreLoginAndRegisterActivity");
        ActivityCompat.finishAffinity(this);
    }

    @Override // com.ido.life.module.guide.WelcomeGuideContract.View
    public void quitApp() {
        ActivityCompat.finishAffinity(this);
    }

    class ViewPagerSmoothRunnable implements Runnable {
        ViewPagerSmoothRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            CommonLogUtil.d(WelcomeGuideActivity.TAG, "run: " + (WelcomeGuideActivity.this.mViewPager.getCurrentItem() + 1) + AppInfo.DELIM + WelcomeGuideActivity.this.mFragmentList.size());
            if (WelcomeGuideActivity.this.mViewPager.getCurrentItem() + 1 == WelcomeGuideActivity.this.mFragmentList.size()) {
                WelcomeGuideActivity.this.mViewPager.setCurrentItem(0, false);
            } else {
                WelcomeGuideActivity.this.mViewPager.setCurrentItem(WelcomeGuideActivity.this.mViewPager.getCurrentItem() + 1, true);
            }
            WelcomeGuideActivity.this.handler.postDelayed(WelcomeGuideActivity.this.runnable, 2000L);
        }
    }

    private void controlViewPagerSpeed(Context context, ViewPager viewPager, int i) {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            FixedSpeedScroller fixedSpeedScroller = new FixedSpeedScroller(context, new AccelerateInterpolator());
            fixedSpeedScroller.setmDuration(i);
            declaredField.set(viewPager, fixedSpeedScroller);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }
}