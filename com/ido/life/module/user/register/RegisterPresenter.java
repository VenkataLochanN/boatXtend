package com.ido.life.module.user.register;

import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.net.http.Result;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.utils.ValidateUtil;
import com.ido.life.VeryFitApp;
import com.ido.life.constants.Constants;
import com.ido.life.customview.NormalToast;
import com.ido.life.data.health.HealthRepository;
import com.ido.life.data.listener.OnResponseCallback;
import com.ido.life.data.listener.OnResultCallback;
import com.ido.life.data.listener.OnResultLoginRegisterCallback;
import com.ido.life.data.me.AccountRepository;
import com.ido.life.data.me.remote.AccountManager;
import com.ido.life.data.me.remote.ThirdLoginManager;
import com.ido.life.database.model.UserInfo;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.module.user.register.RegisterContract;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SPUtils;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class RegisterPresenter implements RegisterContract.Presenter {
    private static final String TAG = "RegisterPresenter";
    private RegisterContract.View mView;
    private OnResponseCallback requestCodeCallBack = new OnResponseCallback() { // from class: com.ido.life.module.user.register.RegisterPresenter.1
        @Override // com.ido.life.data.listener.OnResponseCallback
        public void onSuccess() {
            RegisterPresenter.this.mView.hideLoading();
            RegisterPresenter.this.mView.showMessage(ResourceUtil.getString(R.string.register_get_code_tip));
            CommonLogUtil.d(RegisterPresenter.TAG, "onSuccess: 获得验证码成功");
        }

        @Override // com.ido.life.data.listener.OnResponseCallback
        public void onFailed(String str) {
            RegisterPresenter.this.mView.showError(str);
            RegisterPresenter.this.mView.hideLoading();
            CommonLogUtil.d(RegisterPresenter.TAG, "onFailed: " + str);
        }
    };

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public RegisterPresenter(RegisterContract.View view) {
        this.mView = view;
    }

    @Override // com.ido.life.module.user.register.RegisterContract.Presenter
    public void setPhoneNumberRegister(boolean z) {
        this.mView.setViewNull();
        this.mView.setSubmitEnable(false);
    }

    @Override // com.ido.life.module.user.register.RegisterContract.Presenter
    public void checkSubmitEnable(String str, String str2, String str3, boolean z) {
        this.mView.setSubmitEnable((TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || str3.length() < 6 || !z || TextUtils.isEmpty(str)) ? false : true);
    }

    @Override // com.ido.life.module.user.register.RegisterContract.Presenter
    public void doGetCode(final String str, final String str2, final String str3) {
        this.mView.showLoading();
        if (!ValidateUtil.checkEmail(str2)) {
            this.mView.showError(ResourceUtil.getString(R.string.me_password_email_wrong));
        } else if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            NormalToast.showToast(LanguageUtil.getLanguageText(R.string.public_net_unuse));
            this.mView.hideLoading();
        } else {
            AccountRepository.getInstance().judgeEamilIsExist(str2, new OnResultCallback() { // from class: com.ido.life.module.user.register.RegisterPresenter.2
                @Override // com.ido.life.data.listener.OnResultCallback
                public void onSuccess(Result result) {
                    if (((Boolean) result.getData()).booleanValue()) {
                        RegisterPresenter.this.mView.showMessage(LanguageUtil.getLanguageText(R.string.me_error_account_exist));
                        RegisterPresenter.this.mView.hideLoading();
                    } else {
                        AccountRepository.getInstance().requestGetCode(str, str2, str3, RegisterPresenter.this.requestCodeCallBack);
                    }
                }

                @Override // com.ido.life.data.listener.OnResultCallback
                public void onFailed(String str4) {
                    RegisterPresenter.this.mView.showError(str4);
                    RegisterPresenter.this.mView.hideLoading();
                }
            });
        }
    }

    @Override // com.ido.life.module.user.register.RegisterContract.Presenter
    public void doRegister(String str, String str2, String str3, String str4) {
        String name = this.mView.getName();
        String password = this.mView.getPassword();
        if (!ValidateUtil.checkEmail(name)) {
            this.mView.showError(ResourceUtil.getString(R.string.me_enter_right_email));
            return;
        }
        if (!ValidateUtil.checkPassword(password)) {
            this.mView.showError(ResourceUtil.getString(R.string.register_tip_password_format));
        } else if (!this.mView.isAgreeCheckbox()) {
            this.mView.showError(ResourceUtil.getString(R.string.register_agree_protocol_privicy));
        } else {
            checkRegisterCode(name, Constants.CHECK_CODE_REG);
        }
    }

    @Override // com.ido.life.module.user.register.RegisterContract.Presenter
    public void doThirdLogin(int i) {
        AccountRepository.getInstance().thirdLogin(i, new ThirdLoginManager.OnThirdCallback() { // from class: com.ido.life.module.user.register.RegisterPresenter.3
            @Override // com.ido.life.data.me.remote.ThirdLoginManager.OnThirdCallback
            public void onSuccess(String str, AccountManager.AuthData authData) {
                CommonLogUtil.d(RegisterPresenter.TAG, "onSuccess: " + authData.toJsonString());
                RegisterPresenter.this.bindThirdAccount(str, authData);
            }

            @Override // com.ido.life.data.me.remote.ThirdLoginManager.OnThirdCallback
            public void onFailed(String str) {
                CommonLogUtil.d(RegisterPresenter.TAG, "onFailed: " + str);
            }
        });
    }

    @Override // com.ido.life.module.user.register.RegisterContract.Presenter
    public void setLocationCountry(String str) {
        this.mView.setLocationCountryArea(str);
    }

    @Override // com.ido.life.module.user.register.RegisterContract.Presenter
    public void checkRegisterCode(String str, String str2) {
        this.mView.showLoading();
        if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            NormalToast.showToast(LanguageUtil.getLanguageText(R.string.public_net_unuse));
            this.mView.hideLoading();
            return;
        }
        String str3 = (String) SPUtils.get(Constants.CHOOSE_COUNTRY_CODE, "");
        if (TextUtils.isEmpty(str3)) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "注册过程中，国家码为空。");
            return;
        }
        register(str3, this.mView.getName(), this.mView.getPassword(), "country_" + str3, "", Locale.getDefault().getLanguage(), "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindThirdAccount(final String str, final AccountManager.AuthData authData) {
        this.mView.showLoading();
        AccountRepository.getInstance().loginThirdThroughAccessToken(authData.access_token, authData.openid, "", str, new OnResultCallback() { // from class: com.ido.life.module.user.register.RegisterPresenter.4
            @Override // com.ido.life.data.listener.OnResultCallback
            public void onSuccess(Result result) {
                String str2 = (String) result.getData();
                if (str2.length() < 13) {
                    RegisterPresenter.this.mView.hideLoading();
                    RegisterPresenter.this.mView.goBind(Long.parseLong(str2), authData.access_token, authData.openid, str);
                } else {
                    AccountRepository.getInstance().saveUserToken(str2);
                    RegisterPresenter.this.getUserInfo();
                }
            }

            @Override // com.ido.life.data.listener.OnResultCallback
            public void onFailed(String str2) {
                RegisterPresenter.this.mView.showError(str2);
                CommonLogUtil.d(RegisterPresenter.TAG, "onFailed: " + str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getUserInfo() {
        AccountRepository.getInstance().getUserInfo(new AccountManager.OnUserCallback() { // from class: com.ido.life.module.user.register.RegisterPresenter.5
            @Override // com.ido.life.data.me.remote.AccountManager.OnUserCallback
            public void onSuccess(UserInfo userInfo) {
                CommonLogUtil.d(RegisterPresenter.TAG, "onSuccess: " + userInfo.toString());
                RegisterPresenter.this.judge(userInfo);
            }

            @Override // com.ido.life.data.me.remote.AccountManager.OnUserCallback
            public void onFailed(String str) {
                CommonLogUtil.d(RegisterPresenter.TAG, "onFailed: " + str);
                RegisterPresenter.this.mView.showError(str);
            }
        });
    }

    public void judge(UserInfo userInfo) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "judge: " + userInfo.toString());
        if (userInfo.getGender() == 1 || userInfo.getGender() == 2) {
            AccountRepository.getInstance().updateGender(userInfo.getGender());
            if (!TextUtils.isEmpty(userInfo.getBirthday())) {
                AccountRepository.getInstance().updateBirthday(userInfo.getBirthday());
                if (userInfo.getHeight() > 0.0f) {
                    AccountRepository.getInstance().updateHeight((int) userInfo.getHeight(), userInfo.getHeightUnit());
                    if (userInfo.getWeight() > 0.0f) {
                        AccountRepository.getInstance().updateWeight(userInfo.getWeight(), userInfo.getWeightUnit());
                        UserTargetNew userTarget = HealthRepository.getInstance().getUserTarget(userInfo.getUserId());
                        if (userTarget != null) {
                            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "judge: " + userTarget.toString());
                            this.mView.goMain();
                            return;
                        }
                        this.mView.goUserTarget();
                        return;
                    }
                    this.mView.goUserData(2);
                    return;
                }
                this.mView.goUserData(1);
                return;
            }
            this.mView.goUserData(0);
            return;
        }
        this.mView.goUserData(-1);
    }

    private void register(final String str, final String str2, final String str3, final String str4, final String str5, String str6, String str7) {
        AccountRepository.getInstance().requestSignUp(str, str2, str3, str4, str5, str6, str7, new OnResultLoginRegisterCallback() { // from class: com.ido.life.module.user.register.RegisterPresenter.6
            @Override // com.ido.life.data.listener.OnResultLoginRegisterCallback
            public void onSuccess(Result result) {
                AccountRepository.getInstance();
                AccountRepository.setIsNewUser(true);
                UserInfo userInfo = new UserInfo();
                long j = Long.parseLong((String) result.getData());
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), RegisterPresenter.TAG, "注册成功后 服务器返回用户的userID：" + j);
                userInfo.setEmail(str2);
                userInfo.setUserId(j);
                userInfo.setCountry(str4);
                userInfo.setCity(str5);
                userInfo.setAreaCode(str);
                RunTimeUtil.getInstance().setUserId(j);
                userInfo.setWeightUnit(1);
                userInfo.setHeightUnit(1);
                AccountRepository.getInstance().saveUserInfo(userInfo);
                RegisterPresenter.this.toGetToken(str2, str3);
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), RegisterPresenter.TAG, "onSuccess: 注册成功，保存的用户信息是userInfo--" + userInfo.toString());
            }

            @Override // com.ido.life.data.listener.OnResultLoginRegisterCallback
            public void onFailed(int i, String str8) {
                RegisterPresenter.this.mView.hideLoading();
                RegisterPresenter.this.mView.showError(str8);
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), RegisterPresenter.TAG, "onFailed: 注册失败" + str8);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toGetToken(String str, String str2) {
        AccountRepository.getInstance().requestSignIn("", str, str2, new OnResultLoginRegisterCallback() { // from class: com.ido.life.module.user.register.RegisterPresenter.7
            @Override // com.ido.life.data.listener.OnResultLoginRegisterCallback
            public void onSuccess(Result result) {
                VeryFitApp.isFirstTokenInvalid.set(0);
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), RegisterPresenter.TAG, "把isFirstTokenInvalid 复位为0， 把isFirstTokenInvalid = " + VeryFitApp.isFirstTokenInvalid.get());
                RegisterPresenter.this.mView.hideLoading();
                String str3 = (String) result.getData();
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), RegisterPresenter.TAG, "注册并登录成功后 服务器返回的token：" + str3);
                AccountRepository.getInstance().saveUserToken(str3);
                RegisterPresenter.this.mView.goCheckEmail();
            }

            @Override // com.ido.life.data.listener.OnResultLoginRegisterCallback
            public void onFailed(int i, String str3) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), RegisterPresenter.TAG, "注册后登录失败onFailed: " + str3);
                RegisterPresenter.this.mView.hideLoading();
                RegisterPresenter.this.mView.showError(str3);
            }
        });
    }
}