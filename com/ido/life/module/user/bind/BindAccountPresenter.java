package com.ido.life.module.user.bind;

import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.net.http.Result;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.data.listener.OnResultCallback;
import com.ido.life.data.me.AccountRepository;
import com.ido.life.data.me.remote.AccountManager;
import com.ido.life.database.model.UserInfo;
import com.ido.life.module.user.bind.BindAccountContract;

/* JADX INFO: loaded from: classes3.dex */
public class BindAccountPresenter implements BindAccountContract.Presenter {
    private static final String TAG = "BindAccountPresenter";
    private BindAccountContract.View mView;

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public BindAccountPresenter(BindAccountContract.View view) {
        this.mView = view;
    }

    @Override // com.ido.life.module.user.bind.BindAccountContract.Presenter
    public void checkSubmitEnable(String str) {
        this.mView.setSubmitEnable(!TextUtils.isEmpty(str));
    }

    @Override // com.ido.life.module.user.bind.BindAccountContract.Presenter
    public void doJudgeEamilIsExist(String str) {
        this.mView.showLoading();
        AccountRepository.getInstance().judgeEamilIsExist(str, new OnResultCallback() { // from class: com.ido.life.module.user.bind.BindAccountPresenter.1
            @Override // com.ido.life.data.listener.OnResultCallback
            public void onSuccess(Result result) {
                CommonLogUtil.d(BindAccountPresenter.TAG, "onSuccess: " + result.getData().toString());
                if (((Boolean) result.getData()).booleanValue()) {
                    BindAccountPresenter.this.mView.goPasswordCode();
                } else {
                    BindAccountPresenter.this.mView.goUnRegisterSetPassword();
                    BindAccountPresenter.this.mView.showError(LanguageUtil.getLanguageText(R.string.register_set_password));
                }
            }

            @Override // com.ido.life.data.listener.OnResultCallback
            public void onFailed(String str2) {
                CommonLogUtil.d(BindAccountPresenter.TAG, "onFailed: " + str2);
                BindAccountPresenter.this.mView.showError(str2);
            }
        });
    }

    @Override // com.ido.life.module.user.bind.BindAccountContract.Presenter
    public void doBindAlreadyRegister(Long l, String str, String str2, final String str3, final String str4, final String str5) {
        this.mView.showLoading();
        AccountRepository.getInstance().getBindAlreadyRegisterCallback(l, str, str2, new OnResultCallback() { // from class: com.ido.life.module.user.bind.BindAccountPresenter.2
            @Override // com.ido.life.data.listener.OnResultCallback
            public void onSuccess(Result result) {
                CommonLogUtil.d(BindAccountPresenter.TAG, "onSuccess: " + result.getData().toString());
                BindAccountPresenter.this.bindThirdAccount(str3, str4, str5);
            }

            @Override // com.ido.life.data.listener.OnResultCallback
            public void onFailed(String str6) {
                CommonLogUtil.d(BindAccountPresenter.TAG, "onFailed: " + str6);
                BindAccountPresenter.this.mView.showError(str6);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindThirdAccount(String str, String str2, String str3) {
        AccountRepository.getInstance().loginThirdThroughAccessToken(str, str2, "", str3, new OnResultCallback() { // from class: com.ido.life.module.user.bind.BindAccountPresenter.3
            @Override // com.ido.life.data.listener.OnResultCallback
            public void onSuccess(Result result) {
                AccountRepository.getInstance().saveUserToken((String) result.getData());
                BindAccountPresenter.this.getUserInfo();
            }

            @Override // com.ido.life.data.listener.OnResultCallback
            public void onFailed(String str4) {
                BindAccountPresenter.this.mView.showError(str4);
                CommonLogUtil.d(BindAccountPresenter.TAG, "onFailed: " + str4);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getUserInfo() {
        AccountRepository.getInstance().getUserInfo(new AccountManager.OnUserCallback() { // from class: com.ido.life.module.user.bind.BindAccountPresenter.4
            @Override // com.ido.life.data.me.remote.AccountManager.OnUserCallback
            public void onSuccess(UserInfo userInfo) {
                CommonLogUtil.d(BindAccountPresenter.TAG, "onSuccess: " + userInfo.toString());
                BindAccountPresenter.this.judge(userInfo);
            }

            @Override // com.ido.life.data.me.remote.AccountManager.OnUserCallback
            public void onFailed(String str) {
                CommonLogUtil.d(BindAccountPresenter.TAG, "onFailed: " + str);
                BindAccountPresenter.this.mView.showError(str);
            }
        });
    }

    @Override // com.ido.life.module.user.bind.BindAccountContract.Presenter
    public void clickResetPassword(final String str) {
        if (TextUtils.isEmpty(str)) {
            this.mView.goForgetPassword(null);
        } else {
            AccountRepository.getInstance().validAccount(str, new OnResultCallback() { // from class: com.ido.life.module.user.bind.BindAccountPresenter.5
                @Override // com.ido.life.data.listener.OnResultCallback
                public void onSuccess(Result result) {
                    if (BindAccountPresenter.this.mView == null) {
                        return;
                    }
                    if (((Boolean) result.getData()).booleanValue()) {
                        BindAccountPresenter.this.mView.goForgetPassword(str);
                    } else {
                        BindAccountPresenter.this.mView.goForgetPassword(null);
                    }
                }

                @Override // com.ido.life.data.listener.OnResultCallback
                public void onFailed(String str2) {
                    if (BindAccountPresenter.this.mView != null) {
                        BindAccountPresenter.this.mView.goForgetPassword(null);
                    }
                }
            });
        }
    }

    @Override // com.ido.life.module.user.bind.BindAccountContract.Presenter
    public void judge(UserInfo userInfo) {
        this.mView.showSuccess(userInfo);
    }
}