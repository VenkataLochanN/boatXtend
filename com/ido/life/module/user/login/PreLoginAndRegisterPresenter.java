package com.ido.life.module.user.login;

import android.text.TextUtils;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.net.http.Result;
import com.ido.life.VeryFitApp;
import com.ido.life.data.api.entity.UserTargetEntity;
import com.ido.life.data.health.HealthRepository;
import com.ido.life.data.health.local.UserTargetPreference;
import com.ido.life.data.health.remote.HealthManager;
import com.ido.life.data.listener.OnResultCallback;
import com.ido.life.data.me.AccountRepository;
import com.ido.life.data.me.remote.AccountManager;
import com.ido.life.data.me.remote.ThirdLoginManager;
import com.ido.life.database.model.UserInfo;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.module.user.login.PreLoginAndRegisterContract;

/* JADX INFO: loaded from: classes3.dex */
public class PreLoginAndRegisterPresenter implements PreLoginAndRegisterContract.Presenter {
    public static final String KEY_LOGIN_TOKEN = "key_login_token";
    public static final String KEY_THIRD_LOGIN_OPENID = "key_third_login_openid";
    public static final String KEY_THIRD_LOGIN_TOKEN = "key_third_login_token";
    private static final String TAG = "PreLoginAndRegisterPresenter";
    private PreLoginAndRegisterContract.View mView;

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public PreLoginAndRegisterPresenter(PreLoginAndRegisterContract.View view) {
        this.mView = view;
    }

    @Override // com.ido.life.module.user.login.PreLoginAndRegisterContract.Presenter
    public void doThirdLogin(int i) {
        this.mView.showLoading();
        CommonLogUtil.d(TAG, "doThirdLogin: " + i);
        AccountRepository.getInstance().thirdLogin(i, new ThirdLoginManager.OnThirdCallback() { // from class: com.ido.life.module.user.login.PreLoginAndRegisterPresenter.1
            @Override // com.ido.life.data.me.remote.ThirdLoginManager.OnThirdCallback
            public void onSuccess(String str, AccountManager.AuthData authData) {
                CommonLogUtil.d(PreLoginAndRegisterPresenter.TAG, "onSuccess: " + authData.toJsonString());
                PreLoginAndRegisterPresenter.this.bindThirdAccount(str, authData);
            }

            @Override // com.ido.life.data.me.remote.ThirdLoginManager.OnThirdCallback
            public void onFailed(String str) {
                PreLoginAndRegisterPresenter.this.mView.showError(str);
                PreLoginAndRegisterPresenter.this.mView.hideLoading();
                CommonLogUtil.d(PreLoginAndRegisterPresenter.TAG, "onFailed: " + str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindThirdAccount(final String str, final AccountManager.AuthData authData) {
        AccountRepository.getInstance().loginThirdThroughAccessToken(authData.access_token, authData.openid, "", str, new OnResultCallback() { // from class: com.ido.life.module.user.login.PreLoginAndRegisterPresenter.2
            @Override // com.ido.life.data.listener.OnResultCallback
            public void onSuccess(Result result) {
                String str2 = (String) result.getData();
                if (str2.length() < 13) {
                    PreLoginAndRegisterPresenter.this.mView.hideLoading();
                    PreLoginAndRegisterPresenter.this.mView.goBind(Long.parseLong(str2), authData.access_token, authData.openid, str);
                } else {
                    AccountRepository.getInstance().saveUserToken(str2);
                    PreLoginAndRegisterPresenter.this.getUserInfo();
                }
            }

            @Override // com.ido.life.data.listener.OnResultCallback
            public void onFailed(String str2) {
                PreLoginAndRegisterPresenter.this.mView.showError(str2);
                CommonLogUtil.d(PreLoginAndRegisterPresenter.TAG, "onFailed: " + str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getUserInfo() {
        AccountRepository.getInstance().getUserInfo(new AccountManager.OnUserCallback() { // from class: com.ido.life.module.user.login.PreLoginAndRegisterPresenter.3
            @Override // com.ido.life.data.me.remote.AccountManager.OnUserCallback
            public void onSuccess(UserInfo userInfo) {
                if (userInfo != null) {
                    AccountRepository.getInstance().saveUserInfo(userInfo);
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), PreLoginAndRegisterPresenter.TAG, "onSuccess获取用户信息: userInfo " + userInfo.toString());
                    PreLoginAndRegisterPresenter.this.judge(userInfo);
                }
            }

            @Override // com.ido.life.data.me.remote.AccountManager.OnUserCallback
            public void onFailed(String str) {
                PreLoginAndRegisterPresenter.this.mView.hideLoading();
                PreLoginAndRegisterPresenter.this.mView.showError(str);
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), PreLoginAndRegisterPresenter.TAG, "onFailed获取用户信息: message " + str);
            }
        });
    }

    @Override // com.ido.life.module.user.login.PreLoginAndRegisterContract.Presenter
    public void judge(UserInfo userInfo) {
        if (TextUtils.isEmpty(userInfo.getDisplayName())) {
            this.mView.goUserData(-1);
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "judge 1  ");
            return;
        }
        if (userInfo.getGender() != 1 && userInfo.getGender() != 2) {
            this.mView.goUserData(-1);
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "judge 2  ");
            return;
        }
        if (TextUtils.isEmpty(userInfo.getBirthday())) {
            this.mView.goUserData(-1);
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "judge 3  ");
        } else if (userInfo.getHeight() < 24.0f) {
            this.mView.goUserData(-1);
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "judge 4  ");
        } else if (userInfo.getWeight() < 20.0f) {
            this.mView.goUserData(-1);
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "judge 5  ");
        } else {
            getUserTarget();
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "judge getUserTarget():  ");
        }
    }

    @Override // com.ido.life.module.user.login.PreLoginAndRegisterContract.Presenter
    public void deleteAllLoginData() {
        VeryFitApp.getApp().notManualClearCache();
    }

    private void getUserTarget() {
        HealthRepository.getInstance().getUserTarget(new HealthManager.OnUserTargetCallback() { // from class: com.ido.life.module.user.login.PreLoginAndRegisterPresenter.4
            @Override // com.ido.life.data.health.remote.HealthManager.OnUserTargetCallback
            public void onSuccess(UserTargetEntity userTargetEntity) {
                int step = userTargetEntity.getResult().getStep();
                int weightInt = userTargetEntity.getResult().getWeightInt();
                if (userTargetEntity == null || userTargetEntity.getResult() == null) {
                    PreLoginAndRegisterPresenter.this.mView.goUserTarget();
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), PreLoginAndRegisterPresenter.TAG, "从服务器获取用户目标设置: onSuccess   goUserTarget()");
                    return;
                }
                UserTargetNew userTarget = UserTargetPreference.getUserTarget();
                if (userTarget == null) {
                    userTarget = new UserTargetNew();
                }
                userTarget.setStep(step);
                userTarget.setWeight(weightInt);
                userTarget.setHasUpload(userTargetEntity.getResult().getHasUpload());
                userTarget.setUserId(userTargetEntity.getResult().getUserId());
                UserTargetPreference.savaUserTarget(userTarget);
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), PreLoginAndRegisterPresenter.TAG, "从服务器获取用户目标设置: target  " + userTarget.toString());
                PreLoginAndRegisterPresenter.this.mView.goMain();
            }

            @Override // com.ido.life.data.health.remote.HealthManager.OnUserTargetCallback
            public void onFailed(String str) {
                PreLoginAndRegisterPresenter.this.mView.showError(str);
                PreLoginAndRegisterPresenter.this.mView.goUserTarget();
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), PreLoginAndRegisterPresenter.TAG, "从服务器获取用户目标设置: onFailed   goUserTarget()");
            }
        });
    }
}