package com.ido.life.module.user.userdata;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.common.adapter.SimpleFragmentPagerAdapter;
import com.ido.common.base.BaseCoreActivity;
import com.ido.common.base.CustomViewPager;
import com.ido.common.dialog.WaitingDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.StatusBarUtil;
import com.ido.life.VeryFitApp;
import com.ido.life.customview.NormalToast;
import com.ido.life.customview.ProgressView;
import com.ido.life.module.bind.ChoiceBlueTypeActivity;
import com.ido.life.module.main.MainActivity;
import com.ido.life.module.user.userdata.UserDataContract;
import com.ido.life.module.user.userdata.birth.BirthFragment;
import com.ido.life.module.user.userdata.height.HeightFragment;
import com.ido.life.module.user.userdata.nickname.NicknameFragment;
import com.ido.life.module.user.userdata.sex.SexFragment;
import com.ido.life.module.user.userdata.weight.WeightFragment;
import com.ido.life.module.user.usertarget.UserTargetActivity;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class UserDataActivity extends BaseCoreActivity implements UserDataContract.View {
    public static final String EXTRA_USER_INDEX = "user_index";
    public static final String EXTRA_USER_INFO_FAIL = "user_info_fail";
    public static final int FROM_REGISTER = 1;
    public static final String FROM_WHERE = "form_where";
    private static final String TAG = "UserDataActivity";
    boolean isGetUserinfoFail;
    private BirthFragment mBirthFragment;
    protected List<Fragment> mFragmentList;
    private HeightFragment mHeightFragment;
    private int mIndex = 0;

    @BindView(R.id.iv_forward)
    ImageButton mIvForward;
    private NicknameFragment mNicknameFragment;
    private SimpleFragmentPagerAdapter mPageAdapter;
    private UserDataContract.Presenter mPresenter;

    @BindView(R.id.progress_view)
    ProgressView mProgressView;
    private SexFragment mSexFragment;

    @BindView(R.id.title_leftBtn)
    ImageButton mTitleLeftBtn;

    @BindView(R.id.user_viewpager)
    CustomViewPager mUserViewpager;
    private WeightFragment mWeightFragment;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_user_data;
    }

    public static void startActivity(Activity activity, int i) {
        Intent intent = new Intent(activity, (Class<?>) UserDataActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_USER_INDEX, i);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    public static void startActivity(Activity activity) {
        activity.startActivity(new Intent(activity, (Class<?>) UserDataActivity.class));
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initTitleBar();
        initFragment();
        initViewPager();
        initListener();
    }

    private void initFragment() {
        this.mNicknameFragment = NicknameFragment.newInstance();
        this.mSexFragment = SexFragment.newInstance();
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.add(2, 1);
        calendar.add(1, -100);
        int[] iArr = {calendar.get(1), 1, 1};
        calendar.add(1, 80);
        this.mBirthFragment = BirthFragment.newInstance(iArr, new int[]{calendar.get(1), calendar.get(2), calendar.get(5)});
        this.mHeightFragment = HeightFragment.newInstance();
        this.mWeightFragment = WeightFragment.newInstance();
        this.mFragmentList = new ArrayList();
        this.mFragmentList.add(this.mNicknameFragment);
        this.mFragmentList.add(this.mSexFragment);
        this.mFragmentList.add(this.mBirthFragment);
        this.mFragmentList.add(this.mHeightFragment);
        this.mFragmentList.add(this.mWeightFragment);
    }

    private void initViewPager() {
        this.mPageAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this.mFragmentList);
        this.mUserViewpager.setScanScroll(false);
        this.mUserViewpager.setAdapter(this.mPageAdapter);
        this.mUserViewpager.setOffscreenPageLimit(this.mFragmentList.size());
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "initViewPager:  mIndex:" + this.mIndex + "mFragmentList :" + this.mFragmentList.size());
        showCurrentPage(this.mIndex);
    }

    private void initListener() {
        this.mNicknameFragment.setOnChangeListener(new NicknameFragment.OnChangeListener() { // from class: com.ido.life.module.user.userdata.-$$Lambda$UserDataActivity$t91MWxVCTDwrbt65leCYoYQ0XYg
            @Override // com.ido.life.module.user.userdata.nickname.NicknameFragment.OnChangeListener
            public final void onForward() {
                this.f$0.lambda$initListener$0$UserDataActivity();
            }
        });
        this.mSexFragment.setOnChangeListener(new SexFragment.OnChangeListener() { // from class: com.ido.life.module.user.userdata.UserDataActivity.1
            @Override // com.ido.life.module.user.userdata.sex.SexFragment.OnChangeListener
            public void onForward() {
                UserDataActivity.this.mPresenter.forward();
            }

            @Override // com.ido.life.module.user.userdata.sex.SexFragment.OnChangeListener
            public void onPageBack() {
                UserDataActivity.this.mPresenter.back();
            }
        });
        this.mBirthFragment.setOnChangeListener(new OnChangeListener() { // from class: com.ido.life.module.user.userdata.UserDataActivity.2
            @Override // com.ido.life.module.user.userdata.OnChangeListener
            public void onPageNext() {
                UserDataActivity.this.mPresenter.forward();
            }

            @Override // com.ido.life.module.user.userdata.OnChangeListener
            public void onPageBack() {
                UserDataActivity.this.mPresenter.back();
            }
        });
        this.mHeightFragment.setmOnChangeListener(new OnChangeListener() { // from class: com.ido.life.module.user.userdata.UserDataActivity.3
            @Override // com.ido.life.module.user.userdata.OnChangeListener
            public void onPageNext() {
                UserDataActivity.this.mPresenter.forward();
            }

            @Override // com.ido.life.module.user.userdata.OnChangeListener
            public void onPageBack() {
                UserDataActivity.this.mPresenter.back();
            }
        });
        this.mWeightFragment.setmOnChangeListener(new OnChangeListener() { // from class: com.ido.life.module.user.userdata.UserDataActivity.4
            @Override // com.ido.life.module.user.userdata.OnChangeListener
            public void onPageNext() {
                UserDataActivity.this.mPresenter.forward();
            }

            @Override // com.ido.life.module.user.userdata.OnChangeListener
            public void onPageBack() {
                UserDataActivity.this.mPresenter.back();
            }
        });
    }

    public /* synthetic */ void lambda$initListener$0$UserDataActivity() {
        this.mPresenter.forward();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        this.mTitleLeftBtn.setVisibility(getIntent().getIntExtra(FROM_WHERE, 0) == 1 ? 4 : 0);
        this.mIvForward.setEnabled(false);
        this.mPresenter = new UserDataPresenter(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.mIndex = extras.getInt(EXTRA_USER_INDEX, -1);
            this.isGetUserinfoFail = extras.getBoolean(EXTRA_USER_INFO_FAIL);
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "initData: " + this.mIndex);
        }
        if (this.isGetUserinfoFail) {
            VeryFitApp.getApp().quitClearCacheThread(new VeryFitApp.ClearCacheCallBack() { // from class: com.ido.life.module.user.userdata.UserDataActivity.5
                @Override // com.ido.life.VeryFitApp.ClearCacheCallBack
                public void clearSuccess() {
                    RunTimeUtil.getInstance().setUserId(-1L);
                    UserDataActivity.this.isGetUserinfoFail = false;
                }
            });
        }
    }

    @OnClick({R.id.title_leftBtn})
    public void backButton(View view) {
        GreenDaoUtil.deleteUserInfo(-1L);
        finish();
    }

    @OnClick({R.id.iv_forward})
    public void forward(View view) {
        this.mPresenter.forward();
    }

    private void initTitleBar() {
        StatusBarUtil.StatusBarLightMode(this);
        setStatusBarColor(getColor(R.color.color_1E1E1E), false);
    }

    @Override // com.ido.life.module.user.userdata.UserDataContract.View
    public void showLoading() {
        WaitingDialog.showDialogBack(this);
    }

    @Override // com.ido.life.module.user.userdata.UserDataContract.View
    public void hideLoading() {
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.user.userdata.UserDataContract.View
    public void showProgress(int i) {
        this.mProgressView.setProgress(i);
    }

    @Override // com.ido.life.module.user.userdata.UserDataContract.View
    public void setEnable(boolean z) {
        this.mIvForward.setEnabled(z);
    }

    @Override // com.ido.life.module.user.userdata.UserDataContract.View
    public int getIndex() {
        return this.mIndex;
    }

    @Override // com.ido.life.module.user.userdata.UserDataContract.View
    public void back() {
        finish();
    }

    @Override // com.ido.life.module.user.userdata.UserDataContract.View
    public void toMain() {
        MainActivity.startActivity(this, 1);
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.life.module.user.userdata.UserDataContract.View
    public void toUserTarget() {
        startActivityForResult(new Intent(this, (Class<?>) UserTargetActivity.class), 1003);
    }

    @Override // com.ido.life.module.user.userdata.UserDataContract.View
    public void showCurrentPage(int i) {
        if (i < 0) {
            i = 0;
        }
        if (i > this.mFragmentList.size() - 1) {
            i = this.mFragmentList.size() - 1;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "showCurrentPage: " + i);
        this.mUserViewpager.setCurrentItem(i);
        this.mIndex = i;
    }

    @Override // com.ido.life.module.user.userdata.UserDataContract.View
    public void showMessage(String str) {
        NormalToast.showToast(str);
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(UserDataContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        this.mPresenter.back();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1003 && i2 == 1002) {
            if (BLEManager.isConnected()) {
                MainActivity.startActivity(this);
            } else {
                Intent intent2 = new Intent(this, (Class<?>) ChoiceBlueTypeActivity.class);
                intent2.putExtra(ChoiceBlueTypeActivity.FROM_WHERE, "register");
                startActivity(intent2);
            }
            supportFinishAfterTransition();
        }
    }
}