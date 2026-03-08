package com.ido.life.module.user.bind;

import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.net.http.Result;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.utils.ValidateUtil;
import com.ido.life.data.listener.OnResponseCallback;
import com.ido.life.data.listener.OnResultCallback;
import com.ido.life.data.listener.OnResultLoginRegisterCallback;
import com.ido.life.data.me.AccountRepository;
import com.ido.life.module.user.bind.BindRegisterAccountContract;

/* JADX INFO: loaded from: classes3.dex */
public class BindRegisterAccountPresenter implements BindRegisterAccountContract.Presenter {
    private static final String TAG = "BindRegisterAccountPres";
    private BindRegisterAccountContract.View mView;

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public BindRegisterAccountPresenter(BindRegisterAccountContract.View view) {
        this.mView = view;
    }

    @Override // com.ido.life.module.user.bind.BindRegisterAccountContract.Presenter
    public void setPhoneNumberRegister(boolean z) {
        this.mView.setViewNull();
        this.mView.stopCountDown();
        this.mView.setSubmitEnable(false);
    }

    @Override // com.ido.life.module.user.bind.BindRegisterAccountContract.Presenter
    public void checkSubmitEnable(String str, String str2, String str3) {
        this.mView.setSubmitEnable((TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || str3.length() <= 6) ? false : true);
    }

    @Override // com.ido.life.module.user.bind.BindRegisterAccountContract.Presenter
    public void doGetCode(String str, String str2, String str3) {
        AccountRepository.getInstance().requestGetCode(str, str2, str3, new OnResponseCallback() { // from class: com.ido.life.module.user.bind.BindRegisterAccountPresenter.1
            @Override // com.ido.life.data.listener.OnResponseCallback
            public void onSuccess() {
                BindRegisterAccountPresenter.this.mView.startCountDown();
                CommonLogUtil.d(BindRegisterAccountPresenter.TAG, "onSuccess: 获得验证码成功");
            }

            @Override // com.ido.life.data.listener.OnResponseCallback
            public void onFailed(String str4) {
                BindRegisterAccountPresenter.this.mView.showError(str4);
                CommonLogUtil.d(BindRegisterAccountPresenter.TAG, "onFailed: " + str4);
            }
        });
    }

    @Override // com.ido.life.module.user.bind.BindRegisterAccountContract.Presenter
    public void doRegister(long j) {
        String countryCode = this.mView.getCountryCode();
        String name = this.mView.getName();
        String password = this.mView.getPassword();
        String code = this.mView.getCode();
        if (this.mView.isPhoneNumberRegister() && !ValidateUtil.checkMobileNO(name)) {
            this.mView.showError(ResourceUtil.getString(R.string.me_enter_right_mobile));
            return;
        }
        if (!this.mView.isPhoneNumberRegister() && !ValidateUtil.checkEmail(name)) {
            this.mView.showError(ResourceUtil.getString(R.string.me_enter_right_email));
            return;
        }
        if (this.mView.isPhoneNumberRegister() && TextUtils.isEmpty(code)) {
            this.mView.showError(ResourceUtil.getString(R.string.mine_tip_input_verification));
        } else if (!ValidateUtil.checkPassword(password)) {
            this.mView.showError(ResourceUtil.getString(R.string.register_tip_password_format));
        } else {
            this.mView.showLoading();
            register(j, countryCode, name, code, password);
        }
    }

    private void register(long j, String str, final String str2, String str3, final String str4) {
        this.mView.showLoading();
        AccountRepository.getInstance().requestBindSignUp(j, str, str2, str3, str4, new OnResultCallback() { // from class: com.ido.life.module.user.bind.BindRegisterAccountPresenter.2
            @Override // com.ido.life.data.listener.OnResultCallback
            public void onSuccess(Result result) {
                BindRegisterAccountPresenter.this.toGetToken(str2, str4);
                CommonLogUtil.d(BindRegisterAccountPresenter.TAG, "onSuccess: 注册绑定成功");
            }

            @Override // com.ido.life.data.listener.OnResultCallback
            public void onFailed(String str5) {
                BindRegisterAccountPresenter.this.mView.showError(str5);
                CommonLogUtil.d(BindRegisterAccountPresenter.TAG, "onFailed: 注册失败" + str5);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toGetToken(String str, String str2) {
        AccountRepository.getInstance().requestSignIn("86", str, str2, new OnResultLoginRegisterCallback() { // from class: com.ido.life.module.user.bind.BindRegisterAccountPresenter.3
            @Override // com.ido.life.data.listener.OnResultLoginRegisterCallback
            public void onSuccess(Result result) {
                BindRegisterAccountPresenter.this.mView.hideLoading();
                AccountRepository.getInstance().saveUserToken((String) result.getData());
                BindRegisterAccountPresenter.this.mView.goUserData(-1);
            }

            @Override // com.ido.life.data.listener.OnResultLoginRegisterCallback
            public void onFailed(int i, String str3) {
                CommonLogUtil.d(BindRegisterAccountPresenter.TAG, "onFailed: " + str3);
                BindRegisterAccountPresenter.this.mView.hideLoading();
                BindRegisterAccountPresenter.this.mView.showError(str3);
            }
        });
    }
}