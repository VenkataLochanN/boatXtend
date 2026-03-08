package com.ido.life.module.user.bindsetpassword;

import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.net.http.Result;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.data.listener.OnResponseCallback;
import com.ido.life.data.listener.OnResultCallback;
import com.ido.life.data.listener.OnResultLoginRegisterCallback;
import com.ido.life.data.me.AccountRepository;
import com.ido.life.data.me.remote.AccountManager;
import com.ido.life.database.model.UserInfo;
import com.ido.life.module.user.bindsetpassword.BindInputCodeContract;

/* JADX INFO: loaded from: classes3.dex */
public class BindInputCodePresenter implements BindInputCodeContract.Presenter {
    private static final String TAG = "BindInputCodePresenter";
    private BindInputCodeContract.View mView;

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public BindInputCodePresenter(BindInputCodeContract.View view) {
        this.mView = view;
    }

    @Override // com.ido.life.module.user.bindsetpassword.BindInputCodeContract.Presenter
    public void checkSubmitEnable(String str) {
        CommonLogUtil.d(TAG, "checkSubmitEnable: " + str);
        this.mView.setSubmitEnable(!TextUtils.isEmpty(str) && str.length() == 6);
    }

    @Override // com.ido.life.module.user.bindsetpassword.BindInputCodeContract.Presenter
    public void doGetCode(String str, String str2) {
        this.mView.showLoading();
        AccountRepository.getInstance().requestGetCode("", str, str2, new OnResponseCallback() { // from class: com.ido.life.module.user.bindsetpassword.BindInputCodePresenter.1
            @Override // com.ido.life.data.listener.OnResponseCallback
            public void onSuccess() {
                BindInputCodePresenter.this.mView.hideLoading();
                BindInputCodePresenter.this.mView.startCountDown();
                BindInputCodePresenter.this.mView.showMessage(LanguageUtil.getLanguageText(R.string.public_code_sent_success));
                CommonLogUtil.d(BindInputCodePresenter.TAG, "onSuccess: 获得验证码成功");
            }

            @Override // com.ido.life.data.listener.OnResponseCallback
            public void onFailed(String str3) {
                BindInputCodePresenter.this.mView.showError(str3);
                CommonLogUtil.d(BindInputCodePresenter.TAG, "onFailed: " + str3);
            }
        });
    }

    public void getCode(String str, String str2, String str3) {
        AccountRepository.getInstance().requestGetCode(str, str2, str3, new OnResponseCallback() { // from class: com.ido.life.module.user.bindsetpassword.BindInputCodePresenter.2
            @Override // com.ido.life.data.listener.OnResponseCallback
            public void onSuccess() {
                BindInputCodePresenter.this.mView.startCountDown();
                CommonLogUtil.d(BindInputCodePresenter.TAG, "onSuccess: 获得验证码成功");
                BindInputCodePresenter.this.mView.showGetCodeSuccess();
            }

            @Override // com.ido.life.data.listener.OnResponseCallback
            public void onFailed(String str4) {
                CommonLogUtil.d(BindInputCodePresenter.TAG, "onFailed: " + str4);
                BindInputCodePresenter.this.mView.showError(str4);
            }
        });
    }

    @Override // com.ido.life.module.user.bindsetpassword.BindInputCodeContract.Presenter
    public void doThirdLoginAndBindUnRegister(long j, String str, String str2, String str3, String str4) {
        this.mView.showLoading();
        AccountRepository.getInstance().requestBindSignUp(j, str, str2, str3, str4, new OnResultCallback() { // from class: com.ido.life.module.user.bindsetpassword.BindInputCodePresenter.3
            @Override // com.ido.life.data.listener.OnResultCallback
            public void onSuccess(Result result) {
                BindInputCodePresenter.this.mView.hideLoading();
                BindInputCodePresenter.this.mView.toUserData();
            }

            @Override // com.ido.life.data.listener.OnResultCallback
            public void onFailed(String str5) {
                BindInputCodePresenter.this.mView.hideLoading();
                BindInputCodePresenter.this.mView.showError(str5);
            }
        });
    }

    private void requestResetPassword(final String str, String str2, final String str3, String str4, final String str5) {
        AccountRepository.getInstance().requestPasswordReset(str, str2, str3, new OnResultCallback() { // from class: com.ido.life.module.user.bindsetpassword.BindInputCodePresenter.4
            @Override // com.ido.life.data.listener.OnResultCallback
            public void onSuccess(Result result) {
                BindInputCodePresenter.this.doLogin(str, str3, str5);
            }

            @Override // com.ido.life.data.listener.OnResultCallback
            public void onFailed(String str6) {
                BindInputCodePresenter.this.mView.showError(str6);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doLogin(String str, String str2, String str3) {
        AccountRepository.getInstance().requestSignIn(str3, str, str2, new OnResultLoginRegisterCallback() { // from class: com.ido.life.module.user.bindsetpassword.BindInputCodePresenter.5
            @Override // com.ido.life.data.listener.OnResultLoginRegisterCallback
            public void onSuccess(Result result) {
                AccountRepository.getInstance().saveUserToken(result.getData().toString());
                BindInputCodePresenter.this.getUserInfo();
            }

            @Override // com.ido.life.data.listener.OnResultLoginRegisterCallback
            public void onFailed(int i, String str4) {
                BindInputCodePresenter.this.mView.showError(str4);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getUserInfo() {
        AccountRepository.getInstance().getUserInfo(new AccountManager.OnUserCallback() { // from class: com.ido.life.module.user.bindsetpassword.BindInputCodePresenter.6
            @Override // com.ido.life.data.me.remote.AccountManager.OnUserCallback
            public void onSuccess(UserInfo userInfo) {
                CommonLogUtil.d(BindInputCodePresenter.TAG, "onSuccess: " + userInfo.toString());
                BindInputCodePresenter.this.mView.showSuccess(userInfo);
            }

            @Override // com.ido.life.data.me.remote.AccountManager.OnUserCallback
            public void onFailed(String str) {
                CommonLogUtil.d(BindInputCodePresenter.TAG, "onFailed: " + str);
                BindInputCodePresenter.this.mView.showError(str);
            }
        });
    }
}