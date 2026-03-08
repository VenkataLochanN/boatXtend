package com.ido.life.module.user.resetpassword;

import android.text.TextUtils;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.net.http.Result;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.utils.ValidateUtil;
import com.ido.life.constants.Constants;
import com.ido.life.customview.NormalToast;
import com.ido.life.data.listener.OnResponseCallback;
import com.ido.life.data.listener.OnResultCallback;
import com.ido.life.data.me.AccountRepository;
import com.ido.life.data.me.remote.AccountManager;
import com.ido.life.enums.ServerEnum;
import com.ido.life.module.user.CodeType;
import com.ido.life.module.user.resetpassword.ResetPassContract;
import com.ido.life.util.SPUtils;

/* JADX INFO: loaded from: classes3.dex */
public class ResetPassPresenter implements ResetPassContract.Presenter {
    private static final String TAG = "ResetPassPresenter";
    private ResetPassContract.View mView;

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public ResetPassPresenter(ResetPassContract.View view) {
        this.mView = view;
    }

    @Override // com.ido.life.module.user.resetpassword.ResetPassContract.Presenter
    public void checkSubmitEnable(String str, String str2) {
        CommonLogUtil.d(TAG, "checkSubmitEnable: " + str + AppInfo.DELIM + str2);
        this.mView.setSubmitEnable((TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || str2.length() != 6) ? false : true);
    }

    @Override // com.ido.life.module.user.resetpassword.ResetPassContract.Presenter
    public void doGetCode(final String str) {
        if (!ValidateUtil.checkEmail(str)) {
            this.mView.showError(ResourceUtil.getString(R.string.me_password_email_wrong));
        } else if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            NormalToast.showToast(ResourceUtil.getString(R.string.public_net_unuse));
        } else {
            this.mView.showLoading();
            AccountRepository.getInstance().validAccount(str, new OnResultCallback() { // from class: com.ido.life.module.user.resetpassword.ResetPassPresenter.1
                @Override // com.ido.life.data.listener.OnResultCallback
                public void onSuccess(Result result) {
                    if (((Boolean) result.getData()).booleanValue()) {
                        ResetPassPresenter.this.printAndSave("该账号已经注册过了，开始去检查账号所在区域。");
                        ResetPassPresenter.this.getAccountArea(str);
                        return;
                    }
                    ResetPassPresenter.this.printAndSave("该账号还没有注册，取消找回密码流程。");
                    if (ResetPassPresenter.this.mView != null) {
                        ResetPassPresenter.this.mView.showError(ResourceUtil.getString(R.string.login_account_not_register));
                        ResetPassPresenter.this.mView.hideLoading();
                    }
                }

                @Override // com.ido.life.data.listener.OnResultCallback
                public void onFailed(String str2) {
                    ResetPassPresenter.this.printAndSave("找回密码过程中，检测账号是否存在发生错误 error=" + str2);
                    if (ResetPassPresenter.this.mView != null) {
                        ResetPassPresenter.this.mView.hideLoading();
                        ResetPassPresenter.this.mView.showError(str2);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getAccountArea(final String str) {
        AccountManager.checkAccountArea(str, new OnResultCallback() { // from class: com.ido.life.module.user.resetpassword.ResetPassPresenter.2
            @Override // com.ido.life.data.listener.OnResultCallback
            public void onSuccess(Result result) {
                ServerEnum serverEnum;
                String str2 = (String) result.getData();
                ResetPassPresenter.this.printAndSave("服务器返回 账号acccount=" + str + ",所在服务器区域为" + str2);
                if (Constants.USA_SERVICE.equalsIgnoreCase(str2)) {
                    serverEnum = ServerEnum.USA;
                } else if ("cn".equalsIgnoreCase(str2)) {
                    serverEnum = ServerEnum.China;
                } else if (Constants.INDIA_SERVICE.equalsIgnoreCase(str2)) {
                    serverEnum = ServerEnum.India;
                } else {
                    serverEnum = "eu".equalsIgnoreCase(str2) ? ServerEnum.Europe : null;
                }
                if (serverEnum != null) {
                    SPUtils.put(Constants.SERVER_CODE, Integer.valueOf(serverEnum.Code));
                    SPUtils.put(Constants.REQUEST_COUNTRY_CODE, "");
                }
                ResetPassPresenter.this.getCode(str, CodeType.FORGOT.getType());
            }

            @Override // com.ido.life.data.listener.OnResultCallback
            public void onFailed(String str2) {
                ResetPassPresenter.this.printAndSave("找回密码过程中，检查账号所在服务器出错 error=" + str2);
                if (ResetPassPresenter.this.mView != null) {
                    ResetPassPresenter.this.mView.hideLoading();
                    ResetPassPresenter.this.mView.showError(ResourceUtil.getString(R.string.me_password_email_wrong));
                }
            }
        });
    }

    @Override // com.ido.life.module.user.resetpassword.ResetPassContract.Presenter
    public void checkCodeIsRight(String str, String str2, String str3) {
        AccountRepository.getInstance().requestCheckCode(str, str2, str3, new OnResponseCallback() { // from class: com.ido.life.module.user.resetpassword.ResetPassPresenter.3
            @Override // com.ido.life.data.listener.OnResponseCallback
            public void onSuccess() {
                ResetPassPresenter.this.mView.hideLoading();
                ResetPassPresenter.this.mView.toSetNewPassword();
                CommonLogUtil.d(ResetPassPresenter.TAG, "onSuccess: 校验验证码正确");
            }

            @Override // com.ido.life.data.listener.OnResponseCallback
            public void onFailed(String str4) {
                CommonLogUtil.d(ResetPassPresenter.TAG, "onFailed: " + str4);
                ResetPassPresenter.this.mView.showError(str4);
            }
        });
    }

    @Override // com.ido.life.module.user.resetpassword.ResetPassContract.Presenter
    public void doCheckAccountIsExist(String str) {
        if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            NormalToast.showToast(LanguageUtil.getLanguageText(R.string.public_net_unuse));
            this.mView.hideLoading();
        } else {
            AccountRepository.getInstance().validAccount(str, new OnResultCallback() { // from class: com.ido.life.module.user.resetpassword.ResetPassPresenter.4
                @Override // com.ido.life.data.listener.OnResultCallback
                public void onSuccess(Result result) {
                    boolean zBooleanValue = ((Boolean) result.getData()).booleanValue();
                    if (zBooleanValue) {
                        ResetPassPresenter.this.mView.toCheckCode();
                    } else {
                        ResetPassPresenter.this.mView.showError(ResourceUtil.getString(R.string.login_account_not_register));
                    }
                    CommonLogUtil.d(ResetPassPresenter.TAG, "onSuccess: " + zBooleanValue);
                }

                @Override // com.ido.life.data.listener.OnResultCallback
                public void onFailed(String str2) {
                    ResetPassPresenter.this.mView.showError(str2);
                    CommonLogUtil.d(ResetPassPresenter.TAG, "onFailed: " + str2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getCode(String str, String str2) {
        AccountRepository.getInstance().requestGetCode("", str, str2, new OnResponseCallback() { // from class: com.ido.life.module.user.resetpassword.ResetPassPresenter.5
            @Override // com.ido.life.data.listener.OnResponseCallback
            public void onSuccess() {
                ResetPassPresenter.this.printAndSave("找回密码，验证码获取成功。");
                if (ResetPassPresenter.this.mView != null) {
                    ResetPassPresenter.this.mView.hideLoading();
                    ResetPassPresenter.this.mView.startCountDown();
                    ResetPassPresenter.this.mView.showGetCodeSuccess();
                }
            }

            @Override // com.ido.life.data.listener.OnResponseCallback
            public void onFailed(String str3) {
                ResetPassPresenter.this.printAndSave("找回密码过程中，验证码获取失败 error=" + str3);
                if (ResetPassPresenter.this.mView != null) {
                    ResetPassPresenter.this.mView.hideLoading();
                    ResetPassPresenter.this.mView.showError(str3);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void printAndSave(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, str);
    }
}