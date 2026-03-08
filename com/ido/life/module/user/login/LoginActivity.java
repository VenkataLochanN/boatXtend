package com.ido.life.module.user.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.OnClick;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.ido.common.dialog.WaitingDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.InputMethodUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.PassWordInputFilter;
import com.ido.common.utils.SpaceFilter;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.constants.Constants;
import com.ido.life.customview.NormalToast;
import com.ido.life.database.model.UserInfo;
import com.ido.life.dialog.LoginAbroadDialogFragment;
import com.ido.life.module.main.MainActivity;
import com.ido.life.module.user.bind.BindAccountActivity;
import com.ido.life.module.user.login.LoginContract;
import com.ido.life.module.user.resetpassword.ResetPassActivity;
import com.ido.life.module.user.userdata.UserDataActivity;
import com.ido.life.module.user.usertarget.UserTargetActivity;
import com.ido.life.module.user.view.OnTextChangedListener;
import com.ido.life.module.user.view.ViewMePassword;
import com.ido.life.module.user.view.ViewMePhone;
import com.ido.life.util.SPUtils;

/* JADX INFO: loaded from: classes3.dex */
public class LoginActivity extends BaseActivity implements LoginContract.View {
    private static final String TAG = "LoginActivity";

    @BindView(R.id.btn_forgetpass)
    TextView mBtnForgetpass;

    @BindView(R.id.me_submit)
    TextView mMeSubmit;

    @BindView(R.id.password_view)
    ViewMePassword mPasswordView;

    @BindView(R.id.phone_view)
    ViewMePhone mPhoneView;
    private LoginContract.Presenter mPresenter;

    @BindView(R.id.title_leftBtn)
    ImageButton mTitleLeftBtn;

    @BindView(R.id.rtv_login)
    RegularTextView mTvTitle;
    private String mCountryCode = "";
    private int requestNum = 2;
    private String userName = "";
    private String userPassword = "";

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    public static void startActivity(Activity activity) {
        activity.startActivity(new Intent(activity, (Class<?>) LoginActivity.class));
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
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTvTitle.setText(getLanguageText(R.string.login_btn_login));
        this.mPhoneView.setNameHint((CharSequence) getLanguageText(R.string.register_input_phone_email));
        this.mPasswordView.setNameHint(getLanguageText(R.string.login_input_password));
        this.mBtnForgetpass.setText(LanguageUtil.getLanguageText(R.string.login_forget_password));
        this.mMeSubmit.setText(LanguageUtil.getLanguageText(R.string.login_btn_login));
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        this.mPresenter = new LoginPresenter(this);
    }

    private void initListener() {
        this.mPasswordView.addFilter(new PassWordInputFilter(), new InputFilter.LengthFilter(16));
        this.mPhoneView.addFilter(new SpaceFilter(), new InputFilter.LengthFilter(64));
        this.mPhoneView.setOnTextChangedListener(new OnTextChangedListener() { // from class: com.ido.life.module.user.login.-$$Lambda$LoginActivity$_HQaxVM026RUcFZm0BgjhvlZXOk
            @Override // com.ido.life.module.user.view.OnTextChangedListener
            public final void changed(String str) {
                this.f$0.lambda$initListener$0$LoginActivity(str);
            }
        });
        this.mPasswordView.setOnTextChangedListener(new OnTextChangedListener() { // from class: com.ido.life.module.user.login.-$$Lambda$LoginActivity$kmUjaAxekYTxUzGyrgPglkZWVPU
            @Override // com.ido.life.module.user.view.OnTextChangedListener
            public final void changed(String str) {
                this.f$0.lambda$initListener$1$LoginActivity(str);
            }
        });
    }

    public /* synthetic */ void lambda$initListener$0$LoginActivity(String str) {
        this.mPresenter.checkSubmitEnable(this.mPhoneView.getPhone(), this.mPasswordView.getPassword());
    }

    public /* synthetic */ void lambda$initListener$1$LoginActivity(String str) {
        this.mPresenter.checkSubmitEnable(this.mPhoneView.getPhone(), this.mPasswordView.getPassword());
    }

    @Override // com.ido.life.module.user.login.LoginContract.View
    public void setSubmitEnable(boolean z) {
        this.mMeSubmit.setEnabled(z);
    }

    @Override // com.ido.life.module.user.login.LoginContract.View
    public void showLoading() {
        if (isDestroyed()) {
            return;
        }
        InputMethodUtil.hiddenInput(this, this.mPhoneView);
        InputMethodUtil.hiddenInput(this, this.mPasswordView);
        WaitingDialog.showDialogBack(this);
    }

    @Override // com.ido.life.module.user.login.LoginContract.View
    public void hideLoading() {
        if (isDestroyed()) {
            return;
        }
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.user.login.LoginContract.View
    public void showError(int i, String str) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "doClickSubmit  errorCode: " + i + "message: " + str);
        WaitingDialog.hideDialog();
        this.requestNum = this.requestNum - 1;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (i != 100030) {
            NormalToast.showToast(str);
            return;
        }
        byte b2 = -1;
        int iHashCode = str.hashCode();
        if (iHashCode != 3179) {
            if (iHashCode != 3365) {
                if (iHashCode == 3742 && str.equals(Constants.USA_SERVICE)) {
                    b2 = 2;
                }
            } else if (str.equals(Constants.INDIA_SERVICE)) {
                b2 = 1;
            }
        } else if (str.equals("cn")) {
            b2 = 0;
        }
        if (b2 == 0) {
            SPUtils.put(Constants.REQUEST_COUNTRY_CODE, "86");
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "通过国家码切换为中国服务器");
        } else if (b2 == 1) {
            SPUtils.put(Constants.REQUEST_COUNTRY_CODE, Constants.INDIA_CODE);
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "通过国家码切换为印度服务器");
        } else if (b2 == 2) {
            SPUtils.put(Constants.REQUEST_COUNTRY_CODE, Constants.USA_CODE);
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "通过国家码切换为美国服务器");
        } else {
            SPUtils.put(Constants.REQUEST_COUNTRY_CODE, "33");
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "默认为欧洲服务器，用法国 33 指代");
        }
        if (this.requestNum > 0) {
            showLoading();
            this.mPresenter.doLogin(this.mCountryCode, this.userName, this.userPassword);
        } else {
            NormalToast.showToast(str);
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "doClickSubmit，开始请求登录");
    }

    @Override // com.ido.life.module.user.login.LoginContract.View
    public void showSuccess() {
        NormalToast.showToast(getLanguageText(R.string.me_login_success));
    }

    @Override // com.ido.life.module.user.login.LoginContract.View
    public void goForgetPassword(String str) {
        ResetPassActivity.startActivityForResult(this, str);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "goForgetPassword: 跳转到忘记密码");
    }

    @Override // com.ido.life.module.user.login.LoginContract.View
    public void goMain() {
        MainActivity.startActivity(this, 1);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "goMain: 跳转到主页");
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.life.module.user.login.LoginContract.View
    public void goUserData(int i) {
        UserDataActivity.startActivity(this, i);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "goUserData: " + i);
    }

    @Override // com.ido.life.module.user.login.LoginContract.View
    public void goUserTarget() {
        startActivityForResult(new Intent(this, (Class<?>) UserTargetActivity.class), 1003);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "goUserTarget:跳转到用户目标 ");
    }

    @Override // com.ido.life.module.user.login.LoginContract.View
    public void goBind(long j, String str, String str2, String str3) {
        BindAccountActivity.startActivityForResult(this, j, str, str2, str3);
    }

    @OnClick({R.id.title_leftBtn})
    public void doClickBack(View view) {
        ActivityCompat.finishAfterTransition(this);
    }

    @OnClick({R.id.me_submit})
    public void doClickSubmit(View view) {
        this.requestNum = 2;
        this.userName = this.mPhoneView.getPhone();
        this.userPassword = this.mPasswordView.getPassword();
        this.mPresenter.doLogin(this.mCountryCode, this.userName, this.userPassword);
    }

    @OnClick({R.id.btn_forgetpass})
    public void doClickForgetPass(View view) {
        this.mPresenter.clickResetPassword(this.mPhoneView.getPhone());
    }

    @OnClick({R.id.login_weixin, R.id.login_qq, R.id.login_twitter, R.id.login_facebook, R.id.login_google})
    public void doClickThirdLogin(View view) {
        this.mPresenter.doThirdLogin(view.getId());
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(LoginContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @OnClick({R.id.login_other})
    public void toShowOtherLogin(View view) {
        LoginAbroadDialogFragment loginAbroadDialogFragmentNewInstance = LoginAbroadDialogFragment.newInstance();
        loginAbroadDialogFragmentNewInstance.show(getSupportFragmentManager());
        loginAbroadDialogFragmentNewInstance.setOnShareResultListener(new LoginAbroadDialogFragment.OnShareChooseListener() { // from class: com.ido.life.module.user.login.LoginActivity.1
            @Override // com.ido.life.dialog.LoginAbroadDialogFragment.OnShareChooseListener
            public void onSharePlatChoose(int i) {
                LoginActivity.this.mPresenter.doThirdLogin(i);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "onActivityResult: " + i + AppInfo.DELIM + i2);
        this.mCountryCode = this.mPhoneView.onActivityResultToGetCountrydode(i, i2, intent);
        if (i == 1002 && i2 == 1003 && intent != null) {
            this.mPresenter.judge((UserInfo) intent.getExtras().getSerializable("extra_user"));
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "mPresenter.judge(userInfo): ");
        } else if (i == 1003) {
            if (i2 == 1002) {
                startActivity(new Intent(this, (Class<?>) MainActivity.class));
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "onActivityResult 跳转到MainActivity ");
            }
            supportFinishAfterTransition();
        }
    }
}