package com.ido.life.module.user.set.cancel;

import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.net.http.Result;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.VeryFitApp;
import com.ido.life.constants.Constants;
import com.ido.life.customview.NormalToast;
import com.ido.life.data.listener.OnResponseCallback;
import com.ido.life.data.listener.OnResultCallback;
import com.ido.life.data.me.AccountRepository;
import com.ido.life.database.model.UserInfo;
import com.ido.life.module.user.CodeType;
import com.ido.life.module.user.set.cancel.CancelConfirmContract;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SPUtils;

/* JADX INFO: loaded from: classes3.dex */
public class CancelConfirmPresenter implements CancelConfirmContract.Presenter {
    private static final String TAG = "CancelConfirmPresenter";
    private String mAccount;
    private CancelConfirmContract.View mView;

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public CancelConfirmPresenter(CancelConfirmContract.View view) {
        this.mView = view;
    }

    @Override // com.ido.life.module.user.set.cancel.CancelConfirmContract.Presenter
    public void initData() {
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(RunTimeUtil.getInstance().getUserId());
        if (userInfoQueryUserInfo != null) {
            this.mAccount = userInfoQueryUserInfo.getEmail();
            this.mView.setAreaVisible(false);
            this.mView.setAraeText("");
            this.mView.showUserAccount(this.mAccount);
        }
    }

    @Override // com.ido.life.module.user.set.cancel.CancelConfirmContract.Presenter
    public void checkSubmitEnable(String str) {
        this.mView.setSubmitEnable(!TextUtils.isEmpty(str));
    }

    @Override // com.ido.life.module.user.set.cancel.CancelConfirmContract.Presenter
    public void doGetCode() {
        this.mView.showLoading();
        this.mView.setGetCodeEnable(false);
        if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
            AccountRepository.getInstance().requestGetCode(this.mView.getCountryCode(), this.mAccount, CodeType.DESTORY.getType(), new OnResponseCallback() { // from class: com.ido.life.module.user.set.cancel.CancelConfirmPresenter.1
                @Override // com.ido.life.data.listener.OnResponseCallback
                public void onSuccess() {
                    CancelConfirmPresenter.this.mView.startCountDown();
                    CommonLogUtil.d(CancelConfirmPresenter.TAG, "onSuccess: 获得验证码成功");
                    CancelConfirmPresenter.this.mView.showGetCodeSuccess();
                }

                @Override // com.ido.life.data.listener.OnResponseCallback
                public void onFailed(String str) {
                    CommonLogUtil.d(CancelConfirmPresenter.TAG, "onFailed: " + str);
                    CancelConfirmPresenter.this.mView.setGetCodeEnable(true);
                    CancelConfirmPresenter.this.mView.showError(str);
                }
            });
        } else {
            this.mView.showError(LanguageUtil.getLanguageText(R.string.public_net_unuse));
            this.mView.setGetCodeEnable(true);
        }
    }

    @Override // com.ido.life.module.user.set.cancel.CancelConfirmContract.Presenter
    public void doCancelUser(String str, String str2) {
        this.mView.showLoading();
        cancelAccount(str);
    }

    private void cancelAccount(String str) {
        if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            NormalToast.showToast(ResourceUtil.getString(R.string.public_net_unuse));
            this.mView.hideLoading();
        } else if (str.length() != 6) {
            this.mView.showError(LanguageUtil.getLanguageText(R.string.me_code_error_past));
        } else {
            AccountRepository.getInstance().cancelAccount(this.mAccount, str, new OnResultCallback() { // from class: com.ido.life.module.user.set.cancel.CancelConfirmPresenter.2
                @Override // com.ido.life.data.listener.OnResultCallback
                public void onSuccess(Result result) {
                    CancelConfirmPresenter.this.mView.hideLoading();
                    VeryFitApp.getApp().clearCache();
                    CommonLogUtil.d(CancelConfirmPresenter.TAG, "onSuccess: 注销成功，跳转到登录注册页。");
                    CancelConfirmPresenter.this.mView.showSuccess();
                    SPUtils.put(Constants.FIRST_CLOUD_SYNC, true);
                    CancelConfirmPresenter.this.mView.goPreLoginAndRegister();
                }

                @Override // com.ido.life.data.listener.OnResultCallback
                public void onFailed(String str2) {
                    CancelConfirmPresenter.this.mView.hideLoading();
                    CancelConfirmPresenter.this.mView.showError(str2);
                    CommonLogUtil.d(CancelConfirmPresenter.TAG, "onFailed: " + str2);
                }
            });
        }
    }
}