package com.ido.life.module.user.login;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;
import butterknife.BindView;
import butterknife.OnClick;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.ido.common.dialog.WaitingDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.StatusBarUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.constants.Constants;
import com.ido.life.customview.NormalToast;
import com.ido.life.database.model.UserInfo;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.module.main.MainActivity;
import com.ido.life.module.user.bind.BindAccountActivity;
import com.ido.life.module.user.country.CountryChooseActivity;
import com.ido.life.module.user.login.PreLoginAndRegisterContract;
import com.ido.life.module.user.register.RegisterActivity;
import com.ido.life.module.user.userdata.UserDataActivity;
import com.ido.life.module.user.usertarget.UserTargetActivity;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;

/* JADX INFO: loaded from: classes3.dex */
public class PreLoginAndRegisterActivity extends BaseActivity implements PreLoginAndRegisterContract.View {
    private static final String TAG = "PreLoginAndRegisterActivity";

    @BindView(R.id.btn_login)
    RegularTextView btnLogin;

    @BindView(R.id.tv_other_login)
    RegularTextView btnOtherLogin;

    @BindView(R.id.btn_register)
    RegularTextView btnRegister;
    private PreLoginAndRegisterContract.Presenter mPresenter;

    @BindView(R.id.tv_jump)
    RegularTextView mTvJump;

    @BindView(R.id.video_view)
    VideoView mVideoView;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_pre_login_register;
    }

    public static void startActivity(Activity activity) {
        activity.startActivity(new Intent(activity, (Class<?>) PreLoginAndRegisterActivity.class));
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatusBarColor(getColor(R.color.color_1E1E1E), false);
        initListener();
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        this.mVideoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.welcome);
        this.mVideoView.start();
        this.mVideoView.requestFocus();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTvJump.setText(getLanguageText(R.string.login_skip));
        this.btnLogin.setText(getLanguageText(R.string.login_btn_login));
        this.btnRegister.setText(getLanguageText(R.string.register_btn_register));
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.mVideoView.start();
    }

    private void initTitleBar() {
        StatusBarUtil.StatusBarLightMode(this);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        this.mPresenter = new PreLoginAndRegisterPresenter(this);
        Intent intent = getIntent();
        boolean booleanExtra = intent.getBooleanExtra(Constants.LOGIN_OTHER_PHONE, false);
        boolean booleanExtra2 = intent.getBooleanExtra(Constants.LOGIN_GET_USERINFO_FAIL_THREE, false);
        if (booleanExtra) {
            NormalToast.showToast(getLanguageText(R.string.login_other_phone_tip));
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "用户在其他手机登录，您的登录已失效，请重新登录");
            this.mPresenter.deleteAllLoginData();
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "initData() deleteAllLoginData() ");
            return;
        }
        if (booleanExtra2) {
            NormalToast.showToast(getLanguageText(R.string.alexa_login_fail));
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "登录成功后，在主页连续3次获取个人信息失败：提示登录失败");
        }
    }

    private void initListener() {
        this.mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.ido.life.module.user.login.-$$Lambda$PreLoginAndRegisterActivity$R85dfcxeRxgpAYUAcRLGbcLzeyE
            @Override // android.media.MediaPlayer.OnCompletionListener
            public final void onCompletion(MediaPlayer mediaPlayer) {
                PreLoginAndRegisterActivity.lambda$initListener$0(mediaPlayer);
            }
        });
    }

    static /* synthetic */ void lambda$initListener$0(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    @OnClick({R.id.tv_jump})
    public void toPersonalData(View view) {
        if (clickValid()) {
            RunTimeUtil.getInstance().setUserId(-1L);
            UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(-1L);
            UserTargetNew userTargetNewQueryUserLatestTarget = GreenDaoUtil.queryUserLatestTarget(-1L);
            if (userInfoQueryUserInfo == null || userInfoQueryUserInfo.getWeight() <= 0.0f) {
                Intent intent = new Intent(this, (Class<?>) CountryChooseActivity.class);
                intent.putExtra(Constants.FROM_WHICH_OPEN_COUNTRY, Constants.JUMP_OPEN_COUNTRY);
                startActivity(intent);
                if (userInfoQueryUserInfo == null) {
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "游客 userInfo == null，跳到CountryChooseActivity，重头开始填写资料");
                    return;
                } else {
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "游客 userInfo.getWeight() <= 0，跳到CountryChooseActivity，重头开始填写资料");
                    return;
                }
            }
            if (userInfoQueryUserInfo != null) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "点击跳过获取到DEFAULT_USER_ID的本地游客个人信息:" + userInfoQueryUserInfo.toString());
            }
            if (userTargetNewQueryUserLatestTarget == null) {
                startActivity(new Intent(this, (Class<?>) UserTargetActivity.class));
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "游客 userTargetNew == null，跳到UserTargetActivity，填写目标");
                return;
            }
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "点击跳过获取到DEFAULT_USER_ID的本地游客目标:userTargetNew:" + userTargetNewQueryUserLatestTarget.toString());
            if (userTargetNewQueryUserLatestTarget.getStep() < 5000 || userTargetNewQueryUserLatestTarget.getWeight() < 10.0f) {
                startActivity(new Intent(this, (Class<?>) UserTargetActivity.class));
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "游客 userTargetNew.getStep() < STEP_MIN || userTargetNew.getWeight() < WEIGHT_MIN_KG，跳到UserTargetActivity，填写目标");
            } else {
                MainActivity.startActivity(this, 1);
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "游客个人信息和目标都已经设置完成，直接跳到MainActivity");
            }
        }
    }

    @OnClick({R.id.btn_login})
    public void toLogin(View view) {
        startActivity(new Intent(this, (Class<?>) LoginActivity.class));
    }

    @OnClick({R.id.btn_register})
    public void toRegister(View view) {
        startActivity(new Intent(this, (Class<?>) RegisterActivity.class));
    }

    @Override // com.ido.life.module.user.login.PreLoginAndRegisterContract.View
    public void showLoading() {
        WaitingDialog.showDialogBack(this);
    }

    @Override // com.ido.life.module.user.login.PreLoginAndRegisterContract.View
    public void hideLoading() {
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.user.login.PreLoginAndRegisterContract.View
    public void showError(String str) {
        NormalToast.showToast(str);
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.user.login.PreLoginAndRegisterContract.View
    public void showSuccess() {
        NormalToast.showToast(getLanguageText(R.string.me_login_success));
    }

    @Override // com.ido.life.module.user.login.PreLoginAndRegisterContract.View
    public void goUserData(int i) {
        UserDataActivity.startActivity(this, i);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "跳转到用户信息填写界面，goUserData: " + i);
    }

    @Override // com.ido.life.module.user.login.PreLoginAndRegisterContract.View
    public void goBind(long j, String str, String str2, String str3) {
        BindAccountActivity.startActivityForResult(this, j, str, str2, str3);
    }

    @Override // com.ido.life.module.user.login.PreLoginAndRegisterContract.View
    public void goMain() {
        MainActivity.startActivity(this, 301);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "goMain: EVENT_TYPE_CHECK_HOME_TAB");
    }

    @Override // com.ido.life.module.user.login.PreLoginAndRegisterContract.View
    public void goUserTarget() {
        startActivity(new Intent(this, (Class<?>) UserTargetActivity.class));
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "goUserTarget: UserTargetActivity");
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(PreLoginAndRegisterContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) throws Throwable {
        super.onActivityResult(i, i2, intent);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "onActivityResult: " + i + AppInfo.DELIM + i2);
        if (i == 1002 && i2 == 1003) {
            UserInfo userInfo = (UserInfo) intent.getExtras().getSerializable("extra_user");
            if (userInfo != null) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "onActivityResult: userInfo " + userInfo.toString());
                return;
            }
            return;
        }
        if (i == 1003) {
            if (i2 == 1002) {
                startActivity(new Intent(this, (Class<?>) MainActivity.class));
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "onActivityResult: 跳到 MainActivity");
            }
            supportFinishAfterTransition();
            return;
        }
        handleSignInResult(GoogleSignIn.getSignedInAccountFromIntent(intent));
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "onActivityResult: 启动 Task<GoogleSignInAccount>");
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) throws Throwable {
        try {
            task.getResult(ApiException.class);
        } catch (ApiException e2) {
            e2.printStackTrace();
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, e2.toString());
        }
    }

    @OnClick({R.id.login_twitter, R.id.login_facebook, R.id.login_google})
    public void doClickThirdLogin(View view) {
        this.mPresenter.doThirdLogin(view.getId());
    }
}