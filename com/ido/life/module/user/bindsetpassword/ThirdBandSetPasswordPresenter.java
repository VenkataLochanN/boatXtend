package com.ido.life.module.user.bindsetpassword;

import android.text.TextUtils;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.net.http.Result;
import com.ido.life.data.listener.OnResultCallback;
import com.ido.life.data.listener.OnResultLoginRegisterCallback;
import com.ido.life.data.me.AccountRepository;
import com.ido.life.data.me.remote.AccountManager;
import com.ido.life.database.model.UserInfo;
import com.ido.life.module.user.bindsetpassword.ThirdBandSetPasswordContract;

/* JADX INFO: loaded from: classes3.dex */
public class ThirdBandSetPasswordPresenter implements ThirdBandSetPasswordContract.Presenter {
    private static final String TAG = "ThirdBandSetPasswordPresenter";
    private ThirdBandSetPasswordContract.View mView;

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public ThirdBandSetPasswordPresenter(ThirdBandSetPasswordContract.View view) {
        this.mView = view;
    }

    @Override // com.ido.life.module.user.bindsetpassword.ThirdBandSetPasswordContract.Presenter
    public void checkSubmitEnable(String str, String str2) {
        CommonLogUtil.d(TAG, "checkSubmitEnable: " + str + AppInfo.DELIM + str2);
        this.mView.setSubmitEnable(!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && str.length() >= 6 && str2.length() >= 6 && str.length() == str2.length());
    }

    @Override // com.ido.life.module.user.bindsetpassword.ThirdBandSetPasswordContract.Presenter
    public void doResetPassword(String str, String str2, String str3) {
        this.mView.showLoading();
        requestResetPassword(str, str2, str3);
    }

    private void requestResetPassword(String str, String str2, String str3) {
        AccountRepository.getInstance().requestPasswordReset(str, str2, str3, new OnResultCallback() { // from class: com.ido.life.module.user.bindsetpassword.ThirdBandSetPasswordPresenter.1
            @Override // com.ido.life.data.listener.OnResultCallback
            public void onSuccess(Result result) {
                ThirdBandSetPasswordPresenter.this.mView.showSuccess();
            }

            @Override // com.ido.life.data.listener.OnResultCallback
            public void onFailed(String str4) {
                ThirdBandSetPasswordPresenter.this.mView.showError(str4);
            }
        });
    }

    private void doLogin(String str, String str2, String str3) {
        AccountRepository.getInstance().requestSignIn(str3, str, str2, new OnResultLoginRegisterCallback() { // from class: com.ido.life.module.user.bindsetpassword.ThirdBandSetPasswordPresenter.2
            @Override // com.ido.life.data.listener.OnResultLoginRegisterCallback
            public void onSuccess(Result result) {
                AccountRepository.getInstance().saveUserToken(result.getData().toString());
                ThirdBandSetPasswordPresenter.this.getUserInfo();
            }

            @Override // com.ido.life.data.listener.OnResultLoginRegisterCallback
            public void onFailed(int i, String str4) {
                ThirdBandSetPasswordPresenter.this.mView.showError(str4);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getUserInfo() {
        AccountRepository.getInstance().getUserInfo(new AccountManager.OnUserCallback() { // from class: com.ido.life.module.user.bindsetpassword.ThirdBandSetPasswordPresenter.3
            @Override // com.ido.life.data.me.remote.AccountManager.OnUserCallback
            public void onSuccess(UserInfo userInfo) {
                if (userInfo != null) {
                    CommonLogUtil.d(ThirdBandSetPasswordPresenter.TAG, "onSuccess: " + userInfo.toString());
                }
                ThirdBandSetPasswordPresenter.this.mView.showSuccess(userInfo);
            }

            @Override // com.ido.life.data.me.remote.AccountManager.OnUserCallback
            public void onFailed(String str) {
                if (str != null) {
                    CommonLogUtil.d(ThirdBandSetPasswordPresenter.TAG, "onFailed: " + str);
                }
                ThirdBandSetPasswordPresenter.this.mView.showError(str);
            }
        });
    }
}