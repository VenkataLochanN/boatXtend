package com.ido.life.module.main;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.ido.alexa.util.ComUtil;
import com.ido.ble.LocalDataManager;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.AppUtil;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.common.utils.RomUtils;
import com.ido.common.utils.ScreenUtil;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.BaseMessage;
import com.ido.life.bean.SwitchStatus;
import com.ido.life.ble.SyncDeviceDataProxy;
import com.ido.life.boatservice.AlexaDataService;
import com.ido.life.boatservice.DeviceAssistService;
import com.ido.life.boatservice.MusicControlService;
import com.ido.life.boatservice.NBoatService;
import com.ido.life.constants.Constants;
import com.ido.life.customview.NormalToast;
import com.ido.life.customview.NotifyRadioButton;
import com.ido.life.data.AuthorizationPreference;
import com.ido.life.data.cache.MotionTypeManager;
import com.ido.life.database.model.PrivateSafeSetting;
import com.ido.life.dialog.BackProtectDialogFragment;
import com.ido.life.dialog.BindSupportDialog;
import com.ido.life.dialog.MainQuicklySettingDialog;
import com.ido.life.module.device.fragment.DeviceFragment;
import com.ido.life.module.home.HomeFragment;
import com.ido.life.module.sport.SportFragment;
import com.ido.life.module.user.UserFragment;
import com.ido.life.module.user.set.data.googlefit.GoogleFitPresenter;
import com.ido.life.module.user.userdata.UserDataActivity;
import com.ido.life.module.user.usertarget.UserTargetActivity;
import com.ido.life.syncdownload.DataDownLoadService;
import com.ido.life.util.AlarmManagerUtils;
import com.ido.life.util.DateUtil;
import com.ido.life.util.DialogHelper;
import com.ido.life.util.DialogUtils;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.MsgNotificationHelper;
import com.ido.life.util.NoticeAppUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SPUtils;
import com.ido.life.util.ServiceUtils;
import com.ido.life.util.eventbus.EventBusHelper;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes2.dex */
public class MainActivity extends BaseActivity<MainPresenter> implements MainView, RadioGroup.OnCheckedChangeListener {
    public static final String EXTRA_INDEX = "index";
    private static final String TAG = "MainActivity";

    @BindView(R.id.bottom_tabs)
    LinearLayout mBottomTabs;
    private Dialog mConfirSyncHistoryDialog;
    private Fragment mCurrentFragment;
    private DeviceFragment mDeviceFragment;
    private HomeFragment mHomeFragment;
    private ObjectAnimator mInitConfigAnimator;
    private Dialog mInitConfigDialog;
    private Dialog mInitConfigFailedDialog;
    private PopupWindow mPopupWindow;
    private MainQuicklySettingDialog mQuicklySettingDialog;

    @BindView(R.id.radio_group)
    RadioGroup mRadioGroup;

    @BindView(R.id.rb_tab_device)
    NotifyRadioButton mRbTabDevice;

    @BindView(R.id.rb_tab_home)
    NotifyRadioButton mRbTabHome;

    @BindView(R.id.rb_tab_sport)
    NotifyRadioButton mRbTabSport;

    @BindView(R.id.rb_tab_user)
    NotifyRadioButton mRbTabUser;
    private Bundle mSavedInstanceState;
    private SportFragment mSportFragment;
    private UserFragment mUserFragment;
    private MainPageType mPageType = MainPageType.HOME;
    private long lastOnBackTime = 0;
    private boolean mHasShow = false;
    private boolean mExceptionRecreate = false;

    static /* synthetic */ boolean lambda$getConfirmSyncHistoryDialog$5(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        return i == 4;
    }

    static /* synthetic */ boolean lambda$getInitConfigDialog$2(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        return i == 4;
    }

    static /* synthetic */ boolean lambda$getInitConfigFailedDialog$3(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        return i == 4;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    public boolean isStatusBgWhite() {
        return false;
    }

    @Override // com.ido.life.base.BaseActivity
    protected boolean needEventBus() {
        return true;
    }

    public static void startActivity(Activity activity) {
        activity.startActivity(new Intent(activity, (Class<?>) MainActivity.class));
    }

    public static void startActivity(Activity activity, int i) {
        Intent intent = new Intent(activity, (Class<?>) MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("index", i);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        this.mSavedInstanceState = bundle;
        super.onCreate(bundle);
        ScreenUtil.initScreen(this);
        SPUtils.put(UserFragment.SHOW_SHARE, false);
        DataDownLoadService.stop();
        DataDownLoadService.checkAndAjustDownloadStateWhenStartPull(false);
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mRbTabHome.setText(getLanguageText(R.string.nav_home_title));
        this.mRbTabSport.setText(getLanguageText(R.string.sport_training));
        this.mRbTabDevice.setText(getLanguageText(R.string.nav_device_title));
        this.mRbTabUser.setText(getLanguageText(R.string.nav_mine_title));
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        if (this.mSavedInstanceState != null) {
            this.mHomeFragment = (HomeFragment) getSupportFragmentManager().getFragment(this.mSavedInstanceState, HomeFragment.class.getSimpleName());
            this.mSportFragment = (SportFragment) getSupportFragmentManager().getFragment(this.mSavedInstanceState, SportFragment.class.getSimpleName());
            this.mDeviceFragment = (DeviceFragment) getSupportFragmentManager().getFragment(this.mSavedInstanceState, DeviceFragment.class.getSimpleName());
            this.mUserFragment = (UserFragment) getSupportFragmentManager().getFragment(this.mSavedInstanceState, UserFragment.class.getSimpleName());
        }
        setStatusBarColor(getColor(R.color.black));
    }

    @Override // com.ido.life.base.BaseActivity, android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        ((MainPresenter) this.mPresenter).initConfig();
        ((MainPresenter) this.mPresenter).checkService();
        ((MainPresenter) this.mPresenter).uploadDevices();
        ComUtil.startService(this, AlexaDataService.class);
        StartAlarmManager();
        NoticeAppUtil.init();
    }

    private void showWrongBindDialog() {
        BindSupportDialog.newInstance(LanguageUtil.getLanguageText(R.string.device_get_better_experience), LanguageUtil.getLanguageText(R.string.device_only_support_pro_bind), LanguageUtil.getLanguageText(R.string.device_go_to_download), LanguageUtil.getLanguageText(R.string.action_delay), true, false).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.main.-$$Lambda$MainActivity$uaNVfZYk3dtLGbhH4wFQJZ0nWcQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$showWrongBindDialog$0$MainActivity(view);
            }
        }).show(getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$showWrongBindDialog$0$MainActivity(View view) {
        jump2VeryFitPro();
    }

    private void jump2VeryFitPro() {
        try {
            Intent intent = new Intent();
            intent.setClassName("com.veryfit2hr.second", "com.ido.veryfitpro.module.bind.WelcomeActivity");
            startActivity(intent);
        } catch (Exception e2) {
            e2.printStackTrace();
            AppUtil.toAPPMarket(this, "com.veryfit2hr.second");
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.mHasShow) {
            return;
        }
        this.mHasShow = true;
        if (RomUtils.isOppo() || RomUtils.isVivo() || RomUtils.isXiaomi() || RomUtils.isHuawei()) {
            showRemindBg();
        }
    }

    private void showRemindBg() {
        boolean zBooleanValue = ((Boolean) SPUtils.get(Constants.USER_BACKGROUND_AGREE_REMIND, false)).booleanValue();
        long jLongValue = ((Long) SPUtils.get(Constants.USER_BACKGROUND_AGREE_TIME, 0L)).longValue();
        CommonLogUtil.d(TAG, "showRemindBg: " + System.currentTimeMillis() + AppInfo.DELIM + jLongValue + AppInfo.DELIM + (System.currentTimeMillis() - jLongValue));
        if (zBooleanValue || System.currentTimeMillis() - jLongValue < DateUtil.DAY) {
            return;
        }
        if (this.mPopupWindow == null) {
            this.mPopupWindow = new PopupWindow();
            View viewInflate = LayoutInflater.from(this).inflate(R.layout.pop_bg_protect_layout, (ViewGroup) null);
            ImageView imageView = (ImageView) viewInflate.findViewById(R.id.iv_close);
            CheckBox checkBox = (CheckBox) viewInflate.findViewById(R.id.cb_remind);
            TextView textView = (TextView) viewInflate.findViewById(R.id.tv_set);
            this.mPopupWindow.setContentView(viewInflate);
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.main.MainActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SPUtils.put(Constants.USER_BACKGROUND_AGREE_TIME, Long.valueOf(System.currentTimeMillis()));
                    MainActivity.this.mPopupWindow.dismiss();
                }
            });
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.ido.life.module.main.MainActivity.2
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (z) {
                        SPUtils.put(Constants.USER_BACKGROUND_AGREE_REMIND, true);
                    }
                }
            });
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.main.MainActivity.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    MainActivity.this.showSettingProtect();
                    SPUtils.put(Constants.USER_BACKGROUND_AGREE_TIME, Long.valueOf(System.currentTimeMillis()));
                    MainActivity.this.mPopupWindow.dismiss();
                }
            });
            SPUtils.put(Constants.USER_BACKGROUND_AGREE_TIME, 0L);
            this.mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
            this.mPopupWindow.setOutsideTouchable(true);
            this.mPopupWindow.setFocusable(true);
            this.mPopupWindow.setAnimationStyle(R.style.pop_more);
            this.mPopupWindow.setWidth(-1);
            this.mPopupWindow.setHeight(-2);
        }
        if (this.mPopupWindow.isShowing()) {
            this.mPopupWindow.dismiss();
        } else {
            this.mPopupWindow.showAtLocation(this.mBottomTabs, 80, 0, DipPixelUtil.dip2px(60.0f));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSettingProtect() {
        BackProtectDialogFragment backProtectDialogFragmentNewInstance = BackProtectDialogFragment.newInstance();
        backProtectDialogFragmentNewInstance.setCancelable(false);
        backProtectDialogFragmentNewInstance.show(getSupportFragmentManager());
    }

    private void getUserInfo() {
        if (TextUtils.isEmpty(AuthorizationPreference.getToken(this))) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "登录成功后第一次打开MainActivity,token不为空，去判断获取个人信息");
        ((MainPresenter) this.mPresenter).getUserInfo();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        if (this.mHomeFragment != null) {
            getSupportFragmentManager().putFragment(bundle, this.mHomeFragment.getClass().getSimpleName(), this.mHomeFragment);
        }
        if (this.mSportFragment != null) {
            getSupportFragmentManager().putFragment(bundle, this.mSportFragment.getClass().getSimpleName(), this.mSportFragment);
        }
        if (this.mDeviceFragment != null) {
            getSupportFragmentManager().putFragment(bundle, this.mDeviceFragment.getClass().getSimpleName(), this.mDeviceFragment);
        }
        if (this.mUserFragment != null) {
            getSupportFragmentManager().putFragment(bundle, this.mUserFragment.getClass().getSimpleName(), this.mUserFragment);
        }
        super.onSaveInstanceState(bundle);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        ((MainPresenter) this.mPresenter).initDeviceWhiteList();
        ((MainPresenter) this.mPresenter).requestDeviceWhiteList();
        PrivateSafeSetting privateSafeSettingQueryPrivateSafeSetting = GreenDaoUtil.queryPrivateSafeSetting(RunTimeUtil.getInstance().getUserId());
        if (privateSafeSettingQueryPrivateSafeSetting != null && privateSafeSettingQueryPrivateSafeSetting.isSaveToGoogleFit()) {
            GoogleFitPresenter.getInstance().connectGoogle(this);
        }
        ((MainPresenter) this.mPresenter).addBleConnectStatusListener();
        ((MainPresenter) this.mPresenter).addDeviceInfoCallback();
        ((MainPresenter) this.mPresenter).addUnbindCallback();
        updatePage();
        this.mRadioGroup.setOnCheckedChangeListener(this);
        ((MainPresenter) this.mPresenter).uploadDeviceExceptionLog();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bundle extras = intent.getExtras();
        if (extras != null) {
            int i = extras.getInt("index", -1);
            if (i == 3) {
                this.mRbTabDevice.setChecked(true);
                return;
            }
            if (i == 301) {
                this.mRbTabHome.setChecked(true);
            } else if (i == 1) {
                if (!TextUtils.isEmpty(AuthorizationPreference.getToken(this))) {
                    getUserInfo();
                }
                this.mRbTabHome.setChecked(true);
            }
        }
    }

    private void initSwitchStatus() {
        SwitchStatus switchStatus = ((MainPresenter) this.mPresenter).getSwitchStatus();
        SwitchStatus.NotificationSwitch notificationSwitch = switchStatus.notificationSwitch;
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getNotificationLogPath(), TAG, "initSwitchStatus: " + switchStatus.toString());
        if (switchStatus.callReminderSwitched || LocalDataManager.getFindPhoneSwitch() || (notificationSwitch != null && notificationSwitch.smsSwitched)) {
            startService(new Intent(this, (Class<?>) DeviceAssistService.class));
        }
        if (LocalDataManager.getMusicSwitch()) {
            startService(new Intent(this, (Class<?>) MusicControlService.class));
        }
        startService(new Intent(this, (Class<?>) NBoatService.class));
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        SwitchStatus switchStatus = ((MainPresenter) this.mPresenter).getSwitchStatus();
        SwitchStatus.NotificationSwitch notificationSwitch = switchStatus.notificationSwitch;
        if (switchStatus.callReminderSwitched || (notificationSwitch != null && notificationSwitch.masterSwitched)) {
            MsgNotificationHelper.bindNotificationService();
        }
        initSwitchStatus();
        ((MainPresenter) this.mPresenter).autoConnectDevice();
        ((MainPresenter) this.mPresenter).uploadCacheLog();
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getBugLogPath(), "绑定了新设备，调用快速弹框");
        showQuicklySettingDialog();
    }

    public void showQuicklySettingDialog() {
        if (!((MainPresenter) this.mPresenter).isBindNewDevice()) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getBugLogPath(), "不是绑定新设备，取消快速弹框");
            return;
        }
        if (((MainPresenter) this.mPresenter).needInitConfig) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getBugLogPath(), "不需要初始化配置，取消快速弹框");
            return;
        }
        if (this.mQuicklySettingDialog == null) {
            this.mQuicklySettingDialog = MainQuicklySettingDialog.getInstance();
        }
        if (!this.mQuicklySettingDialog.isDialogShowing()) {
            this.mQuicklySettingDialog.show(getSupportFragmentManager());
        } else {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getBugLogPath(), "已经显示，不需要重复快速弹框");
        }
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb_tab_device /* 2131363278 */:
                this.mPageType = MainPageType.DEVICE;
                break;
            case R.id.rb_tab_home /* 2131363279 */:
                this.mPageType = MainPageType.HOME;
                break;
            case R.id.rb_tab_sport /* 2131363280 */:
                this.mPageType = MainPageType.SPORT;
                break;
            case R.id.rb_tab_user /* 2131363281 */:
                this.mPageType = MainPageType.USER;
                break;
        }
        updatePage();
    }

    /* JADX INFO: renamed from: com.ido.life.module.main.MainActivity$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$ido$life$module$main$MainPageType = new int[MainPageType.values().length];

        static {
            try {
                $SwitchMap$com$ido$life$module$main$MainPageType[MainPageType.HOME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ido$life$module$main$MainPageType[MainPageType.SPORT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$ido$life$module$main$MainPageType[MainPageType.DEVICE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$ido$life$module$main$MainPageType[MainPageType.USER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void updatePage() {
        int i = AnonymousClass4.$SwitchMap$com$ido$life$module$main$MainPageType[this.mPageType.ordinal()];
        if (i == 1) {
            if (this.mHomeFragment == null) {
                this.mHomeFragment = new HomeFragment();
            }
            addOrShow(this.mHomeFragment);
            return;
        }
        if (i == 2) {
            if (this.mSportFragment == null) {
                this.mSportFragment = new SportFragment();
            }
            addOrShow(this.mSportFragment);
        } else if (i == 3) {
            if (this.mDeviceFragment == null) {
                this.mDeviceFragment = new DeviceFragment();
            }
            addOrShow(this.mDeviceFragment);
        } else {
            if (i != 4) {
                return;
            }
            if (this.mUserFragment == null) {
                this.mUserFragment = new UserFragment();
            }
            addOrShow(this.mUserFragment);
        }
    }

    private void StartAlarmManager() {
        AlarmManagerUtils alarmManagerUtils = AlarmManagerUtils.getInstance(this);
        alarmManagerUtils.createGetUpAlarmManager();
        alarmManagerUtils.getUpAlarmManagerStartWork();
    }

    private void hideLastFragment(FragmentTransaction fragmentTransaction) {
        Fragment fragment = this.mCurrentFragment;
        if (fragment != null) {
            fragmentTransaction.hide(fragment);
        }
    }

    private void addOrShow(Fragment fragment) {
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        hideLastFragment(fragmentTransactionBeginTransaction);
        try {
            if (!fragment.isAdded()) {
                fragmentTransactionBeginTransaction.add(R.id.fragment_container, fragment, null);
            } else {
                fragmentTransactionBeginTransaction.show(fragment);
            }
            fragmentTransactionBeginTransaction.commitAllowingStateLoss();
            getSupportFragmentManager().executePendingTransactions();
            this.mCurrentFragment = fragment;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (((MainPresenter) this.mPresenter).mNotificationPermissionDialog == null || !isNotificationEnabled()) {
            return;
        }
        DialogHelper.dismissDialogFragment(DialogHelper.TYPE_NOTIFICATION_PERMISSION());
        ((MainPresenter) this.mPresenter).mNotificationPermissionDialog = null;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        GoogleFitPresenter.getInstance().handleSignInResult(i, intent, this);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (System.currentTimeMillis() - this.lastOnBackTime < 2000) {
            moveTaskToBack(true);
            exitApp();
        } else {
            this.lastOnBackTime = System.currentTimeMillis();
            showToast(R.string.exit_app);
        }
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.life.util.eventbus.IHandlerEventBus
    public void handleMessage(BaseMessage baseMessage) {
        if (baseMessage == null) {
            return;
        }
        super.handleMessage(baseMessage);
        int type = baseMessage.getType();
        if (type == 303) {
            this.mRbTabDevice.setChecked(true);
            return;
        }
        if (type == 814) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "收到EVENT_QUIT_LOGIN_SUCCESS_FINISH，退出登录后杀死Mainactivity" + baseMessage);
            finish();
            return;
        }
        if (type != 827) {
            if (type != 854) {
                if (type == 894) {
                    CommonLogUtil.printAndSave("需要补传运动图标");
                    DialogUtils.INSTANCE.showGlobalOneBtnTipsDialog(getLanguageText(R.string.tips), getLanguageText(R.string.motion_types_icon_replenish), getLanguageText(R.string.i_see), new Function0() { // from class: com.ido.life.module.main.-$$Lambda$MainActivity$eEHUGJ8RmCTM9xbD6h9uuKRVhjc
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivity.lambda$handleMessage$1();
                        }
                    });
                    return;
                }
                if (type == 305) {
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "收到EVENT_TYPE_OTHER_LOGIN_FINISH，杀死Mainactivity：" + baseMessage);
                    finish();
                    return;
                }
                if (type != 306) {
                    return;
                }
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "EXIT_APP，杀死Mainactivity" + baseMessage);
                finishAffinity();
                System.exit(0);
                return;
            }
        } else if (!this.mRbTabSport.isChecked()) {
            this.mRbTabSport.setChecked(true);
        }
        SwitchStatus switchStatus = ((MainPresenter) this.mPresenter).getSwitchStatus();
        SwitchStatus.NotificationSwitch notificationSwitch = switchStatus.notificationSwitch;
        if ((switchStatus.callReminderSwitched || LocalDataManager.getFindPhoneSwitch() || (notificationSwitch != null && notificationSwitch.smsSwitched)) && !ServiceUtils.isServiceRunning(this, "com.ido.life.boatservice.DeviceAssistService")) {
            startService(new Intent(this, (Class<?>) DeviceAssistService.class));
        }
        if (!LocalDataManager.getMusicSwitch() || ServiceUtils.isServiceRunning(this, "com.ido.life.boatservice.MusicControlService")) {
            return;
        }
        startService(new Intent(this, (Class<?>) MusicControlService.class));
    }

    static /* synthetic */ Unit lambda$handleMessage$1() {
        MotionTypeManager.INSTANCE.getInstance().replenishIcons();
        return null;
    }

    @Override // com.ido.life.base.BaseActivity
    protected void fromBackToForeground() {
        super.fromBackToForeground();
        EventBusHelper.post(Constants.EventConstants.EVENT_BACK_FROM_BACK);
    }

    @Override // com.ido.life.module.main.MainView
    public void jump2UserDataActivity() {
        Intent intent = new Intent(this, (Class<?>) UserDataActivity.class);
        intent.putExtra(UserDataActivity.FROM_WHERE, 1);
        startActivity(intent);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "从主页跳转到: UserDataActivity1");
        finish();
    }

    @Override // com.ido.life.module.main.MainView
    public void jump2UserDataActivityUserFailed(boolean z) {
        if (!z) {
            NormalToast.showToast(getLanguageText(R.string.login_get_userinfo_fail), 2000);
        }
        Intent intent = new Intent(this, (Class<?>) UserDataActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(UserDataActivity.EXTRA_USER_INDEX, -1);
        bundle.putBoolean(UserDataActivity.EXTRA_USER_INFO_FAIL, true);
        intent.putExtras(bundle);
        startActivity(intent);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "从主页跳转到: UserDataActivity2");
        finish();
    }

    @Override // com.ido.life.module.main.MainView
    public void jump2UserTargetActivity() {
        Intent intent = new Intent(this, (Class<?>) UserTargetActivity.class);
        intent.putExtra(Constants.IS_FROM_SPLASH, true);
        startActivity(intent);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "从主页跳转到: UserTargetActivity");
        finish();
    }

    @Override // com.ido.life.module.main.MainView
    public void startInitConfig() {
        showInitConfigDialog();
    }

    @Override // com.ido.life.module.main.MainView
    public void initConfigFailed() {
        Dialog dialog = this.mInitConfigDialog;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        hideInitConfigDialog();
        showInitConfigFailedDialog();
    }

    @Override // com.ido.life.module.main.MainView
    public void showHistoryDataOverLoadTip() {
        CommonLogUtil.d(TAG, "显示历史数据过大提醒");
        hideInitConfigDialog();
        showConfirmSyncDialog();
    }

    @Override // com.ido.life.module.main.MainView
    public void initConfigSuccess() {
        hideInitConfigDialog();
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getBugLogPath(), "初始化配置成功，调用快速弹框");
        showQuicklySettingDialog();
    }

    @Override // com.ido.life.module.main.MainView
    public void onBindWrongDevice() {
        showWrongBindDialog();
    }

    @Override // com.ido.life.module.main.MainView
    public void jumpNotificationSettingPage() {
        MsgNotificationHelper.saveLog("NotificationActivity，jump2SettingActivity");
        try {
            Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            intent.addFlags(268435456);
            startActivityForResult(intent, 500);
        } catch (ActivityNotFoundException e2) {
            MsgNotificationHelper.saveLog("ActivityNotFoundException ：" + e2.toString());
            try {
                Intent intent2 = new Intent();
                intent2.addFlags(268435456);
                intent2.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$NotificationAccessSettingsActivity"));
                intent2.putExtra(":settings:show_fragment", "NotificationAccessSettings");
                startActivityForResult(intent2, 500);
            } catch (Exception e3) {
                MsgNotificationHelper.saveLog("Exception ：" + e3.toString());
                e3.printStackTrace();
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getNotificationLogPath(), "--------------对不起，您的手机暂不支持------------->>");
            }
            e2.printStackTrace();
        }
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        SyncDeviceDataProxy.getInstance().clearDeviceDataListener();
        DataDownLoadService.stop();
    }

    private void showInitConfigDialog() {
        Dialog dialog = this.mInitConfigDialog;
        if (dialog == null || !dialog.isShowing()) {
            if (this.mInitConfigDialog == null) {
                this.mInitConfigDialog = getInitConfigDialog();
            }
            this.mInitConfigDialog.show();
            this.mInitConfigAnimator.start();
        }
    }

    private void hideInitConfigDialog() {
        Dialog dialog = this.mInitConfigDialog;
        if (dialog != null && dialog.isShowing()) {
            this.mInitConfigDialog.dismiss();
        }
        ObjectAnimator objectAnimator = this.mInitConfigAnimator;
        if (objectAnimator != null && (objectAnimator.isStarted() || this.mInitConfigAnimator.isRunning())) {
            this.mInitConfigAnimator.cancel();
        }
        this.mInitConfigAnimator = null;
        this.mInitConfigDialog = null;
    }

    private void showInitConfigFailedDialog() {
        Dialog dialog = this.mInitConfigFailedDialog;
        if (dialog == null || !dialog.isShowing()) {
            if (this.mInitConfigFailedDialog == null) {
                this.mInitConfigFailedDialog = getInitConfigFailedDialog();
            }
            this.mInitConfigFailedDialog.show();
        }
    }

    private void hideInitConfigFailedDialog() {
        Dialog dialog = this.mInitConfigFailedDialog;
        if (dialog != null && dialog.isShowing()) {
            this.mInitConfigFailedDialog.dismiss();
        }
        this.mInitConfigFailedDialog = null;
    }

    private void hideConfirmSyncDialog() {
        Dialog dialog = this.mConfirSyncHistoryDialog;
        if (dialog != null && dialog.isShowing()) {
            this.mConfirSyncHistoryDialog.dismiss();
        }
        this.mConfirSyncHistoryDialog = null;
    }

    private void showConfirmSyncDialog() {
        Dialog dialog = this.mConfirSyncHistoryDialog;
        if (dialog == null || !dialog.isShowing()) {
            if (this.mConfirSyncHistoryDialog == null) {
                this.mConfirSyncHistoryDialog = getConfirmSyncHistoryDialog();
            }
            this.mConfirSyncHistoryDialog.show();
        }
    }

    private Dialog getInitConfigDialog() {
        View viewInflate = getLayoutInflater().inflate(R.layout.dialog_init_config_layout, (ViewGroup) null);
        Dialog dialog = new Dialog(this, R.style.data_sync_dialog_translate);
        dialog.setContentView(viewInflate);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        layoutParams.width = (getResources().getDisplayMetrics().widthPixels * 3) / 5;
        viewInflate.setLayoutParams(layoutParams);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.img_progress);
        ((TextView) viewInflate.findViewById(R.id.tv_init_config)).setText(getLanguageText(R.string.init_config_loading));
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.ido.life.module.main.-$$Lambda$MainActivity$7aF0ScovwnaPATLi2BF530se4ms
            @Override // android.content.DialogInterface.OnKeyListener
            public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return MainActivity.lambda$getInitConfigDialog$2(dialogInterface, i, keyEvent);
            }
        });
        this.mInitConfigAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0.0f, 360.0f);
        this.mInitConfigAnimator.setDuration(1000L);
        this.mInitConfigAnimator.setInterpolator(new LinearInterpolator());
        this.mInitConfigAnimator.setRepeatCount(-1);
        this.mInitConfigAnimator.setRepeatMode(1);
        return dialog;
    }

    private Dialog getInitConfigFailedDialog() {
        View viewInflate = getLayoutInflater().inflate(R.layout.dialog_init_config_failed_layout, (ViewGroup) null);
        Dialog dialog = new Dialog(this, R.style.data_sync_dialog_translate);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -2);
        layoutParams.width = (getResources().getDisplayMetrics().widthPixels * 4) / 5;
        dialog.setContentView(viewInflate, layoutParams);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.ido.life.module.main.-$$Lambda$MainActivity$wKpVsOHEASiYT9HB2Pj72C51Ou0
            @Override // android.content.DialogInterface.OnKeyListener
            public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return MainActivity.lambda$getInitConfigFailedDialog$3(dialogInterface, i, keyEvent);
            }
        });
        ((TextView) viewInflate.findViewById(R.id.tv_init_config_failed)).setText(getLanguageText(R.string.init_config_failed));
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_action_retry);
        textView.setText(getLanguageText(R.string.device_retry));
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.main.-$$Lambda$MainActivity$I1SpAM11Fk0i_-MqAF_jSUHrkIM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$getInitConfigFailedDialog$4$MainActivity(view);
            }
        });
        return dialog;
    }

    public /* synthetic */ void lambda$getInitConfigFailedDialog$4$MainActivity(View view) {
        hideInitConfigFailedDialog();
        ((MainPresenter) this.mPresenter).getUserInfo();
    }

    private Dialog getConfirmSyncHistoryDialog() {
        View viewInflate = getLayoutInflater().inflate(R.layout.dialog_confirm_sync_history_layout, (ViewGroup) null);
        Dialog dialog = new Dialog(this, R.style.data_sync_dialog_translate);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -2);
        layoutParams.width = (getResources().getDisplayMetrics().widthPixels * 4) / 5;
        dialog.setContentView(viewInflate, layoutParams);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.ido.life.module.main.-$$Lambda$MainActivity$aT23Ggo0mnwSgTlDEevZqewfpZA
            @Override // android.content.DialogInterface.OnKeyListener
            public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return MainActivity.lambda$getConfirmSyncHistoryDialog$5(dialogInterface, i, keyEvent);
            }
        });
        ((TextView) viewInflate.findViewById(R.id.tv_title)).setText(getLanguageText(R.string.confirm_sync_history_data_title));
        ((TextView) viewInflate.findViewById(R.id.tv_message)).setText(getLanguageText(R.string.confirm_sync_history_data_message));
        ((TextView) viewInflate.findViewById(R.id.tv_tip)).setText(getLanguageText(R.string.sync_all_history_tip));
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_action_delay);
        textView.setText(getLanguageText(R.string.action_delay));
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.tv_action_continue);
        textView2.setText(getLanguageText(R.string.action_continue));
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.main.-$$Lambda$MainActivity$fTLhgFuOFkQd9Qo-eCnKJ8F1hKk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$getConfirmSyncHistoryDialog$6$MainActivity(view);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.main.-$$Lambda$MainActivity$7DiXme7NybkdJc9fYG4oHDsfOqE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$getConfirmSyncHistoryDialog$7$MainActivity(view);
            }
        });
        return dialog;
    }

    public /* synthetic */ void lambda$getConfirmSyncHistoryDialog$6$MainActivity(View view) {
        hideConfirmSyncDialog();
        ((MainPresenter) this.mPresenter).cancelLoadHistoryData();
    }

    public /* synthetic */ void lambda$getConfirmSyncHistoryDialog$7$MainActivity(View view) {
        if (NetworkUtil.isConnected(this)) {
            hideConfirmSyncDialog();
            ((MainPresenter) this.mPresenter).startLoadHistoryData();
        } else {
            NormalToast.showToast(getLanguageText(R.string.public_net_unuse));
        }
    }
}