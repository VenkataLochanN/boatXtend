package com.ido.life.module.splash;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.common.utils.PermissionUtil;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.BaseAnimationListener;
import com.ido.life.boatservice.GDLocationManager;
import com.ido.life.constants.Constants;
import com.ido.life.data.AuthorizationPreference;
import com.ido.life.data.me.remote.AccountManager;
import com.ido.life.database.model.AgreementPrivacyVersion;
import com.ido.life.database.model.UserAgreementAndPrivacyVersionData;
import com.ido.life.database.model.UserInfo;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.dialog.NewSplashConfirmDialogFragment;
import com.ido.life.module.guide.WelcomeGuideActivity;
import com.ido.life.module.main.MainActivity;
import com.ido.life.module.sport.bean.LocationMessage;
import com.ido.life.module.user.login.PreLoginAndRegisterActivity;
import com.ido.life.module.user.usertarget.UserTargetActivity;
import com.ido.life.net.BaseUrl;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RemoteLanguageHelper;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SPHelper;
import com.ido.life.util.SPUtils;
import com.ido.life.util.eventbus.EventBusHelper;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SplashActivity extends BaseActivity implements GDLocationManager.LocationStringListener {
    private static final long ANIM_DURATION = 2000;
    private static final String TAG = "SplashActivity";
    private static final int VERSION_COUNT = 2;

    @BindView(R.id.layout_splash_root)
    RelativeLayout mLayoutRoot;
    private Long privacyPolicyVersion;
    private String token;
    private Long userAgreenmentVersion;
    private UserInfo userInfo;
    private UserTargetNew userTargetNew;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "启动页  SplashActivity  initData() ");
        if ((getIntent().getFlags() & 4194304) != 0) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "启动页  FLAG_ACTIVITY_BROUGHT_TO_FRONT ");
            finish();
            return;
        }
        BaseUrl.switchServer();
        if (!checkSelfPermission(PermissionUtil.getStoragePermission())) {
            requestPermissions(100, PermissionUtil.getStoragePermission());
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "initData()  requestPermissions ");
        } else {
            initAnimation();
            requestNewVersion();
        }
        GDLocationManager.getInstance(IdoApp.getAppContext()).startLocation(this);
        RemoteLanguageHelper.isLoading = false;
    }

    private void initAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.6f, 1.0f);
        alphaAnimation.setDuration(2000L);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setAnimationListener(new BaseAnimationListener() { // from class: com.ido.life.module.splash.SplashActivity.1
            @Override // com.ido.life.base.BaseAnimationListener, android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                super.onAnimationEnd(animation);
                if (((Boolean) SPUtils.get(Constants.FIRST_START_APP, true)).booleanValue()) {
                    SplashActivity splashActivity = SplashActivity.this;
                    splashActivity.startActivityByUser(new Intent(splashActivity, (Class<?>) WelcomeGuideActivity.class));
                } else if (((Long) SPUtils.get(Constants.AGREE_POLICY_TIME, 0L)).longValue() != 0) {
                    SplashActivity.this.notFirstStartApp();
                } else {
                    SplashActivity.this.toShowPrivicy();
                }
            }
        });
        this.mLayoutRoot.startAnimation(alphaAnimation);
    }

    private void requestNewVersion() {
        if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
            AccountManager.getPrivicyNewVersion(new AccountManager.OnCommCallback<List<UserAgreementAndPrivacyVersionData>>() { // from class: com.ido.life.module.splash.SplashActivity.2
                @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
                public void onSuccess(List<UserAgreementAndPrivacyVersionData> list) {
                    if (list == null) {
                        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), SplashActivity.TAG, "requestNewVersion()  onSuccess:  userAgreementAndPrivacyVersionData == null)");
                        return;
                    }
                    if (list.size() >= 2) {
                        AgreementPrivacyVersion agreementPrivacyVersionQueryAgreementPrivacyVersion = GreenDaoUtil.queryAgreementPrivacyVersion();
                        for (UserAgreementAndPrivacyVersionData userAgreementAndPrivacyVersionData : list) {
                            String pageType = userAgreementAndPrivacyVersionData.getPageType();
                            byte b2 = -1;
                            int iHashCode = pageType.hashCode();
                            if (iHashCode != -1631306122) {
                                if (iHashCode == 1376469481 && pageType.equals(Constants.PRIVACY_POLICY)) {
                                    b2 = 1;
                                }
                            } else if (pageType.equals(Constants.USER_AGREEMENT)) {
                                b2 = 0;
                            }
                            if (b2 == 0) {
                                SplashActivity.this.userAgreenmentVersion = userAgreementAndPrivacyVersionData.getVersion();
                            } else if (b2 == 1) {
                                SplashActivity.this.privacyPolicyVersion = userAgreementAndPrivacyVersionData.getVersion();
                            }
                        }
                        if (agreementPrivacyVersionQueryAgreementPrivacyVersion != null) {
                            agreementPrivacyVersionQueryAgreementPrivacyVersion.setUSER_AGREEMENT(SplashActivity.this.userAgreenmentVersion);
                            agreementPrivacyVersionQueryAgreementPrivacyVersion.setPRIVACY_POLICY(SplashActivity.this.privacyPolicyVersion);
                            agreementPrivacyVersionQueryAgreementPrivacyVersion.update();
                            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), SplashActivity.TAG, "requestNewVersion()  onSuccess: agreementPrivacyVersion 不为null，更新 " + agreementPrivacyVersionQueryAgreementPrivacyVersion);
                            return;
                        }
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        AgreementPrivacyVersion agreementPrivacyVersion = new AgreementPrivacyVersion(SplashActivity.this.userAgreenmentVersion, SplashActivity.this.privacyPolicyVersion, 0L, 0L, 0L, jCurrentTimeMillis, jCurrentTimeMillis);
                        GreenDaoUtil.addAgreementPrivacyVersion(agreementPrivacyVersion);
                        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), SplashActivity.TAG, "requestNewVersion()  onSuccess: agreementPrivacyVersion == null，新建 " + agreementPrivacyVersion);
                        return;
                    }
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), SplashActivity.TAG, "requestNewVersion()  onSuccess: userAgreementAndPrivacyVersionData.size() 小于2 ");
                }

                @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
                public void onFailed(String str) {
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), SplashActivity.TAG, "requestNewVersion()  onFailed: " + str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notFirstStartApp() {
        this.token = AuthorizationPreference.getToken(this);
        this.userInfo = GreenDaoUtil.queryLatestUserInfo();
        this.userTargetNew = GreenDaoUtil.queryUserLatestTarget(-1L);
        if (TextUtils.isEmpty(this.token)) {
            UserInfo userInfo = this.userInfo;
            if (userInfo != null && this.userTargetNew != null && userInfo.getUserId() == -1) {
                this.userInfo.update();
                RunTimeUtil.getInstance().setUserId(this.userInfo.getUserId());
                startActivityByUser(new Intent(this, (Class<?>) MainActivity.class));
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "token 为空，用户是游客模式，资料填写完整，启动页跳转到 MainActivity");
                EventBusHelper.post(Constants.EventConstants.EVENT_GET_USER_INFO_SUCCESS);
                return;
            }
            UserInfo userInfo2 = this.userInfo;
            if (userInfo2 != null && this.userTargetNew == null && userInfo2.getUserId() == -1) {
                this.userInfo.update();
                RunTimeUtil.getInstance().setUserId(this.userInfo.getUserId());
                Intent intent = new Intent(this, (Class<?>) UserTargetActivity.class);
                intent.putExtra(Constants.IS_FROM_SPLASH, true);
                startActivityByUser(intent);
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "token 为空，用户是游客模式，未填写目标，启动页跳转到 UserTargetActivity");
                return;
            }
            startActivityByUser(new Intent(this, (Class<?>) PreLoginAndRegisterActivity.class));
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "token 为空，用户非游客模式,启动页跳转到 PreLoginAndRegisterActivity");
            return;
        }
        if (this.userInfo != null) {
            RunTimeUtil.getInstance().setUserId(this.userInfo.getUserId());
        }
        startActivityByUser(new Intent(this, (Class<?>) MainActivity.class));
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "token 不为空，用户已经登录过，启动页直接跳转到 MainActivity");
        EventBusHelper.post(Constants.EventConstants.EVENT_GET_USER_INFO_SUCCESS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startActivityByUser(Intent intent) {
        startActivity(intent);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "startActivityByUser() 跳转到其他界面");
        supportFinishAfterTransition();
    }

    @Override // com.ido.common.base.BaseCoreActivity, com.ido.common.utils.PermissionUtil.RequestResult
    public void requestPermissionsSuccess(int i) {
        initAnimation();
        requestNewVersion();
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "requestPermissionsSuccess() 请求文件权限成功" + i);
    }

    @Override // com.ido.common.base.BaseCoreActivity, com.ido.common.utils.PermissionUtil.RequestResult
    public void requestPermissionsFail(int i) {
        initAnimation();
        requestNewVersion();
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "requestPermissionsFail() 请求文件权限失败" + i);
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "onDestroy() 销毁splash界面");
        GDLocationManager.getInstance(IdoApp.getAppContext()).stopLocation(this);
    }

    @Override // com.ido.life.boatservice.GDLocationManager.LocationStringListener
    public void onReceiveLocation(LocationMessage locationMessage) {
        if (locationMessage != null) {
            if ((locationMessage.latitude == 0.0d && locationMessage.longitude == 0.0d) || TextUtils.isEmpty(locationMessage.country)) {
                return;
            }
            SPHelper.saveLocation(locationMessage.getLatitude(), locationMessage.getLongitude());
        }
    }

    public void toShowPrivicy() {
        NewSplashConfirmDialogFragment.newInstance(LanguageUtil.getLanguageText(R.string.logn_welcome_APPName_ios), LanguageUtil.getLanguageText(R.string.logn_launch_mineuseragreement_userprivacypolicyuser_ios), LanguageUtil.getLanguageText(R.string.login_agree_continue), LanguageUtil.getLanguageText(R.string.login_not_agree), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.splash.SplashActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SPUtils.put(Constants.AGREE_POLICY_TIME, Long.valueOf(System.currentTimeMillis()));
                AuthorizationPreference.setToken(SplashActivity.this, "");
                SplashActivity splashActivity = SplashActivity.this;
                splashActivity.startActivity(new Intent(splashActivity.getBaseContext(), (Class<?>) PreLoginAndRegisterActivity.class));
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), SplashActivity.TAG, "引导页勾选确认协议后,跳转到登录注册页 ");
                ActivityCompat.finishAffinity(SplashActivity.this);
            }
        }).setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.splash.SplashActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SplashActivity.this.finish();
            }
        }).show(getSupportFragmentManager());
    }
}