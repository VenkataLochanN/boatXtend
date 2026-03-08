package com.ido.life.module.user.emailcheck;

import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.net.http.Result;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.constants.Constants;
import com.ido.life.customview.NormalToast;
import com.ido.life.data.listener.OnResponseCallback;
import com.ido.life.data.listener.OnResultCallback;
import com.ido.life.data.me.AccountRepository;
import com.ido.life.database.model.UserInfo;
import com.ido.life.module.user.CodeType;
import com.ido.life.module.user.emailcheck.CheckEmailContract;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SPUtils;

/* JADX INFO: loaded from: classes3.dex */
public class CheckEmailPresenter implements CheckEmailContract.Presenter {
    private static final String TAG = "CheckEmailPresenter";
    private boolean isFromHome;
    private String mAccount;
    private CheckEmailContract.View mView;

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public CheckEmailPresenter(CheckEmailContract.View view) {
        this.mView = view;
    }

    @Override // com.ido.life.module.user.emailcheck.CheckEmailContract.Presenter
    public void initData(boolean z) {
        this.isFromHome = z;
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(RunTimeUtil.getInstance().getUserId());
        if (userInfoQueryUserInfo != null) {
            this.mAccount = userInfoQueryUserInfo.getEmail();
        }
        doGetCode();
    }

    @Override // com.ido.life.module.user.emailcheck.CheckEmailContract.Presenter
    public void checkSubmitEnable(String str) {
        this.mView.setSubmitEnable(!TextUtils.isEmpty(str));
    }

    @Override // com.ido.life.module.user.emailcheck.CheckEmailContract.Presenter
    public void doGetCode() {
        this.mView.showLoading();
        this.mView.setGetCodeEnable(false);
        if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            this.mView.showError(LanguageUtil.getLanguageText(R.string.public_net_unuse));
            this.mView.setGetCodeEnable(true);
            return;
        }
        if (!this.isFromHome) {
            requestVerifyCode();
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long jLongValue = ((Long) SPUtils.get(Constants.CHECK_EMAIL_CODE_KEY, 0L)).longValue();
        long j = jCurrentTimeMillis - jLongValue;
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "currenT: " + jCurrentTimeMillis + ",lastT: " + jLongValue + ",passTime: " + j);
        if (j > DateUtil.MINUTE) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "passTime > 60000 ");
            requestVerifyCode();
        } else {
            this.mView.hideLoading();
            this.mView.startCountDown();
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "passTime 相隔时间小于60s ");
        }
    }

    private void requestVerifyCode() {
        AccountRepository.getInstance().requestGetCode("", this.mAccount, CodeType.REG.getType(), new OnResponseCallback() { // from class: com.ido.life.module.user.emailcheck.CheckEmailPresenter.1
            @Override // com.ido.life.data.listener.OnResponseCallback
            public void onSuccess() {
                SPUtils.put(Constants.CHECK_EMAIL_CODE_KEY, Long.valueOf(System.currentTimeMillis()));
                CheckEmailPresenter.this.mView.startCountDown();
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), CheckEmailPresenter.TAG, "onSuccess:  + 获得验证码成功 ");
                CheckEmailPresenter.this.mView.showGetCodeSuccess();
            }

            @Override // com.ido.life.data.listener.OnResponseCallback
            public void onFailed(String str) {
                SPUtils.put(Constants.CHECK_EMAIL_CODE_KEY, 0L);
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), CheckEmailPresenter.TAG, "onFailed:  + 获得验证码失败 ");
                CheckEmailPresenter.this.mView.setGetCodeEnable(true);
                CheckEmailPresenter.this.mView.showError(str);
            }
        });
    }

    @Override // com.ido.life.module.user.emailcheck.CheckEmailContract.Presenter
    public void doCheckEmailCode(String str) {
        this.mView.showLoading();
        checkEmailCode(str);
    }

    private void checkEmailCode(String str) {
        if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            NormalToast.showToast(LanguageUtil.getLanguageText(R.string.public_net_unuse));
            this.mView.hideLoading();
        } else if (str.length() != 6) {
            this.mView.showError(LanguageUtil.getLanguageText(R.string.me_code_error_past));
        } else {
            AccountRepository.getInstance().checkEmail(str, new OnResultCallback() { // from class: com.ido.life.module.user.emailcheck.CheckEmailPresenter.2
                @Override // com.ido.life.data.listener.OnResultCallback
                public void onSuccess(Result result) {
                    CheckEmailPresenter.this.mView.hideLoading();
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), CheckEmailPresenter.TAG, "onSuccess: 校验邮箱成功，跳转到注册填写个人资料页。");
                    CheckEmailPresenter.this.mView.showSuccess();
                }

                @Override // com.ido.life.data.listener.OnResultCallback
                public void onFailed(String str2) {
                    CheckEmailPresenter.this.mView.hideLoading();
                    CheckEmailPresenter.this.mView.showError(str2);
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), CheckEmailPresenter.TAG, "onFailed: 校验邮箱失败" + str2);
                }
            });
        }
    }
}