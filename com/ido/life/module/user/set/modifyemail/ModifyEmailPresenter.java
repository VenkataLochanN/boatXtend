package com.ido.life.module.user.set.modifyemail;

import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.net.http.Result;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.common.utils.ValidateUtil;
import com.ido.life.constants.Constants;
import com.ido.life.customview.NormalToast;
import com.ido.life.data.listener.OnResponseCallback;
import com.ido.life.data.listener.OnResultCallback;
import com.ido.life.data.me.AccountRepository;
import com.ido.life.module.user.CodeType;
import com.ido.life.module.user.set.modifyemail.ModifyEmailContract;
import com.ido.life.util.SPUtils;

/* JADX INFO: loaded from: classes3.dex */
public class ModifyEmailPresenter implements ModifyEmailContract.Presenter {
    private static final String TAG = "ModifyEmailPresenter";
    private ModifyEmailContract.View mView;
    private String newEmail;

    @Override // com.ido.life.module.user.set.modifyemail.ModifyEmailContract.Presenter
    public void initData() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public ModifyEmailPresenter(ModifyEmailContract.View view) {
        this.mView = view;
    }

    @Override // com.ido.life.module.user.set.modifyemail.ModifyEmailContract.Presenter
    public void checkSubmitEnable(String str) {
        this.mView.setSubmitEnable(!TextUtils.isEmpty(str) && str.length() == 6);
    }

    @Override // com.ido.life.module.user.set.modifyemail.ModifyEmailContract.Presenter
    public void doGetCode(String str) {
        this.mView.showLoading();
        this.mView.setGetCodeEnable(false);
        this.newEmail = this.mView.getNewEmail();
        if (!ValidateUtil.checkEmail(this.newEmail)) {
            this.mView.showError(LanguageUtil.getLanguageText(R.string.me_enter_right_email));
            this.mView.setGetCodeEnable(true);
        } else if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            this.mView.showError(LanguageUtil.getLanguageText(R.string.public_net_unuse));
            this.mView.setGetCodeEnable(true);
        } else {
            AccountRepository.getInstance().judgeEamilIsExist(this.newEmail, new OnResultCallback() { // from class: com.ido.life.module.user.set.modifyemail.ModifyEmailPresenter.1
                @Override // com.ido.life.data.listener.OnResultCallback
                public void onSuccess(Result result) {
                    if (!((Boolean) result.getData()).booleanValue()) {
                        ModifyEmailPresenter.this.requesCode();
                        return;
                    }
                    NormalToast.showToast(LanguageUtil.getLanguageText(R.string.me_error_account_exist));
                    ModifyEmailPresenter.this.mView.setGetCodeEnable(true);
                    ModifyEmailPresenter.this.mView.hideLoading();
                }

                @Override // com.ido.life.data.listener.OnResultCallback
                public void onFailed(String str2) {
                    ModifyEmailPresenter.this.mView.showError(str2);
                    ModifyEmailPresenter.this.mView.hideLoading();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requesCode() {
        AccountRepository.getInstance().requestGetCode("", this.newEmail, CodeType.REG.getType(), new OnResponseCallback() { // from class: com.ido.life.module.user.set.modifyemail.ModifyEmailPresenter.2
            @Override // com.ido.life.data.listener.OnResponseCallback
            public void onSuccess() {
                ModifyEmailPresenter.this.mView.startCountDown();
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), ModifyEmailPresenter.TAG, "onSuccess: 获得验证码成功");
                ModifyEmailPresenter.this.mView.showGetCodeSuccess();
            }

            @Override // com.ido.life.data.listener.OnResponseCallback
            public void onFailed(String str) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), ModifyEmailPresenter.TAG, "onFailed: " + str);
                ModifyEmailPresenter.this.mView.setGetCodeEnable(true);
                ModifyEmailPresenter.this.mView.showError(str);
            }
        });
    }

    @Override // com.ido.life.module.user.set.modifyemail.ModifyEmailContract.Presenter
    public void doModifyEmail() {
        this.mView.showLoading();
        modifyEmail();
    }

    private void modifyEmail() {
        this.newEmail = this.mView.getNewEmail();
        if (!ValidateUtil.checkEmail(this.newEmail)) {
            this.mView.showError(LanguageUtil.getLanguageText(R.string.me_enter_right_email));
            this.mView.setGetCodeEnable(true);
        } else if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            NormalToast.showToast(LanguageUtil.getLanguageText(R.string.public_net_unuse));
            this.mView.hideLoading();
        } else {
            AccountRepository.getInstance().modifyEmail(this.newEmail, this.mView.getVerificationCode(), new OnResultCallback() { // from class: com.ido.life.module.user.set.modifyemail.ModifyEmailPresenter.3
                @Override // com.ido.life.data.listener.OnResultCallback
                public void onSuccess(Result result) {
                    ModifyEmailPresenter.this.mView.hideLoading();
                    SPUtils.put(Constants.LOGIN_SUCCESS_ACCOUNT, ModifyEmailPresenter.this.newEmail);
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), ModifyEmailPresenter.TAG, "onSuccess: 修改邮箱成功");
                    ModifyEmailPresenter.this.mView.showSuccess(ModifyEmailPresenter.this.newEmail);
                }

                @Override // com.ido.life.data.listener.OnResultCallback
                public void onFailed(String str) {
                    ModifyEmailPresenter.this.mView.hideLoading();
                    ModifyEmailPresenter.this.mView.showError(str);
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), ModifyEmailPresenter.TAG, "onFailed: 修改邮箱失败" + str);
                }
            });
        }
    }
}