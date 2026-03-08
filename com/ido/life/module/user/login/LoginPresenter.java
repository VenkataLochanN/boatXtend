package com.ido.life.module.user.login;

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
import com.ido.life.data.listener.OnResultCallback;
import com.ido.life.data.listener.OnResultLoginRegisterCallback;
import com.ido.life.data.me.AccountRepository;
import com.ido.life.data.me.remote.AccountManager;
import com.ido.life.data.me.remote.ThirdLoginManager;
import com.ido.life.database.model.UserInfo;
import com.ido.life.module.user.login.LoginContract;
import com.ido.life.syncdownload.DataDownLoadService;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SPUtils;
import com.ido.life.util.eventbus.EventBusHelper;

/* JADX INFO: loaded from: classes3.dex */
public class LoginPresenter implements LoginContract.Presenter {
    public static final String KEY_LOGIN_TOKEN = "key_login_token";
    public static final String KEY_THIRD_LOGIN_OPENID = "key_third_login_openid";
    public static final String KEY_THIRD_LOGIN_TOKEN = "key_third_login_token";
    private static final String TAG = "LoginPresenter";
    private LoginContract.View mView;

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public LoginPresenter(LoginContract.View view) {
        this.mView = view;
        DataDownLoadService.stop();
    }

    @Override // com.ido.life.module.user.login.LoginContract.Presenter
    public void checkSubmitEnable(String str, String str2) {
        this.mView.setSubmitEnable((TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || str2.length() < 6) ? false : true);
    }

    @Override // com.ido.life.module.user.login.LoginContract.Presenter
    public void doLogin(String str, final String str2, String str3) {
        this.mView.showLoading();
        if (!ValidateUtil.checkEmail(str2)) {
            this.mView.showError(-1, ResourceUtil.getString(R.string.me_password_email_wrong));
        } else if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            NormalToast.showToast(LanguageUtil.getLanguageText(R.string.public_net_unuse));
            this.mView.hideLoading();
        } else {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "点击登录按钮后的有效登录请求标记");
            AccountRepository.getInstance().requestSignIn(str, str2, str3, new OnResultLoginRegisterCallback() { // from class: com.ido.life.module.user.login.LoginPresenter.1
                @Override // com.ido.life.data.listener.OnResultLoginRegisterCallback
                public void onSuccess(Result result) {
                    VeryFitApp.isFirstTokenInvalid.set(0);
                    AccountRepository.getInstance();
                    AccountRepository.setIsNewUser(false);
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), LoginPresenter.TAG, "把isFirstTokenInvalid 复位为0， 把isFirstTokenInvalid = " + VeryFitApp.isFirstTokenInvalid.get());
                    SPUtils.put(Constants.LOGIN_SUCCESS_ACCOUNT, str2);
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), LoginPresenter.TAG, "onSuccess  登录成功后保存的账户邮箱：" + str2 + ",保存的国家码 ：" + SPUtils.get(Constants.CHOOSE_COUNTRY_CODE, "1681688"));
                    if (result.getData().toString() != null) {
                        AccountRepository.getInstance().saveUserToken(result.getData().toString());
                        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), LoginPresenter.TAG, "登录成功服务器返回的token：" + result.getData().toString());
                    }
                    UserInfo userInfoQueryLatestUserInfo = GreenDaoUtil.queryLatestUserInfo();
                    if (userInfoQueryLatestUserInfo != null && userInfoQueryLatestUserInfo.getUserId() == -1) {
                        GreenDaoUtil.deleteUserInfo(-1L);
                        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), LoginPresenter.TAG, "登录前是游客模式，先把游客UserInfo数据删除");
                    }
                    UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(str2);
                    if (userInfoQueryUserInfo != null) {
                        RunTimeUtil.getInstance().setUserId(userInfoQueryUserInfo.getUserId());
                        EventBusHelper.post(Constants.EventConstants.EVENT_GET_USER_INFO_SUCCESS);
                        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), LoginPresenter.TAG, "登录成功后 本地数据库保存的mUserInfo不为空：" + userInfoQueryUserInfo.toString());
                    } else {
                        RunTimeUtil.getInstance().setUserId(-2L);
                        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), LoginPresenter.TAG, "登录成功后 本地数据库保存的mUserInfo为空：");
                    }
                    LoginPresenter.this.mView.goMain();
                }

                @Override // com.ido.life.data.listener.OnResultLoginRegisterCallback
                public void onFailed(int i, String str4) {
                    LoginPresenter.this.mView.hideLoading();
                    LoginPresenter.this.mView.showError(i, str4);
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), LoginPresenter.TAG, "onFailed 登录失败：errorCode=" + i + "message=" + str4 + "，失败后保存的账户邮箱：" + str2 + ",保存的国家码 ：" + SPUtils.get(Constants.CHOOSE_COUNTRY_CODE, "5215211"));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getUserInfo() {
        AccountRepository.getInstance().getUserInfo(new AccountManager.OnUserCallback() { // from class: com.ido.life.module.user.login.LoginPresenter.2
            @Override // com.ido.life.data.me.remote.AccountManager.OnUserCallback
            public void onSuccess(UserInfo userInfo) {
                if (userInfo != null) {
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), LoginPresenter.TAG, "getUserInfo() onSuccess: " + userInfo.toString());
                }
                LoginPresenter.this.judge(userInfo);
            }

            @Override // com.ido.life.data.me.remote.AccountManager.OnUserCallback
            public void onFailed(String str) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), LoginPresenter.TAG, "getUserInfo() onFailed: " + str);
                LoginPresenter.this.mView.showError(-1, str);
            }
        });
    }

    @Override // com.ido.life.module.user.login.LoginContract.Presenter
    public void judge(UserInfo userInfo) {
        if (userInfo != null) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "judge: " + userInfo.toString());
        }
        TextUtils.equals(AccountRepository.getInstance().getUserInfo().getEmail(), userInfo.getEmail());
        if (!TextUtils.isEmpty(userInfo.getDisplayName())) {
            AccountRepository.getInstance().updateDisplayName(userInfo.getDisplayName(), userInfo.getAvatarUrl(), !userInfo.isServerImageUrl());
            if (userInfo.getGender() == 1 || userInfo.getGender() == 2) {
                AccountRepository.getInstance().updateGender(userInfo.getGender());
                if (!TextUtils.isEmpty(userInfo.getBirthday())) {
                    AccountRepository.getInstance().updateBirthday(userInfo.getBirthday());
                    if (userInfo.getHeight() > 0.0f) {
                        AccountRepository.getInstance().updateHeight((int) userInfo.getHeight(), userInfo.getHeightUnit());
                        if (userInfo.getWeight() > 0.0f) {
                            AccountRepository.getInstance().updateWeight(userInfo.getWeight(), userInfo.getWeightUnit());
                            this.mView.goMain();
                            return;
                        } else {
                            this.mView.goUserData(3);
                            return;
                        }
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

    @Override // com.ido.life.module.user.login.LoginContract.Presenter
    public void clickResetPassword(final String str) {
        if (TextUtils.isEmpty(str)) {
            this.mView.goForgetPassword(null);
        } else {
            AccountRepository.getInstance().validAccount(str, new OnResultCallback() { // from class: com.ido.life.module.user.login.LoginPresenter.3
                @Override // com.ido.life.data.listener.OnResultCallback
                public void onSuccess(Result result) {
                    if (LoginPresenter.this.mView == null) {
                        return;
                    }
                    if (((Boolean) result.getData()).booleanValue()) {
                        LoginPresenter.this.mView.goForgetPassword(str);
                    } else {
                        LoginPresenter.this.mView.goForgetPassword(null);
                    }
                }

                @Override // com.ido.life.data.listener.OnResultCallback
                public void onFailed(String str2) {
                    if (LoginPresenter.this.mView != null) {
                        LoginPresenter.this.mView.goForgetPassword(null);
                    }
                }
            });
        }
    }

    @Override // com.ido.life.module.user.login.LoginContract.Presenter
    public void doThirdLogin(int i) {
        CommonLogUtil.d(TAG, "doThirdLogin: " + i);
        AccountRepository.getInstance().thirdLogin(i, new ThirdLoginManager.OnThirdCallback() { // from class: com.ido.life.module.user.login.LoginPresenter.4
            @Override // com.ido.life.data.me.remote.ThirdLoginManager.OnThirdCallback
            public void onSuccess(String str, AccountManager.AuthData authData) {
                CommonLogUtil.d(LoginPresenter.TAG, "onSuccess: " + authData.toJsonString());
                LoginPresenter.this.bindThirdAccount(str, authData);
            }

            @Override // com.ido.life.data.me.remote.ThirdLoginManager.OnThirdCallback
            public void onFailed(String str) {
                LoginPresenter.this.mView.showError(-1, str);
                CommonLogUtil.d(LoginPresenter.TAG, "onFailed: " + str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindThirdAccount(final String str, final AccountManager.AuthData authData) {
        this.mView.showLoading();
        AccountRepository.getInstance().loginThirdThroughAccessToken(authData.access_token, authData.openid, "", str, new OnResultCallback() { // from class: com.ido.life.module.user.login.LoginPresenter.5
            @Override // com.ido.life.data.listener.OnResultCallback
            public void onSuccess(Result result) {
                String str2 = (String) result.getData();
                if (str2.length() < 13) {
                    LoginPresenter.this.mView.hideLoading();
                    LoginPresenter.this.mView.goBind(Long.parseLong(str2), authData.access_token, authData.openid, str);
                } else {
                    AccountRepository.getInstance().saveUserToken(str2);
                    LoginPresenter.this.getUserInfo();
                }
            }

            @Override // com.ido.life.data.listener.OnResultCallback
            public void onFailed(String str2) {
                LoginPresenter.this.mView.showError(-1, str2);
                CommonLogUtil.d(LoginPresenter.TAG, "onFailed: " + str2);
            }
        });
    }
}