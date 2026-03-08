package com.ido.life.module.user.resetpassword;

import android.text.TextUtils;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.net.http.Result;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.data.listener.OnResultCallback;
import com.ido.life.data.me.AccountRepository;
import com.ido.life.module.user.resetpassword.SetNewPasswordContract;

/* JADX INFO: loaded from: classes3.dex */
public class SetNewPasswordPresenter implements SetNewPasswordContract.Presenter {
    private static final String TAG = "SetNewPasswordPresenter";
    private SetNewPasswordContract.View mView;

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public SetNewPasswordPresenter(SetNewPasswordContract.View view) {
        this.mView = view;
    }

    @Override // com.ido.life.module.user.resetpassword.SetNewPasswordContract.Presenter
    public void checkSubmitEnable(String str, String str2) {
        CommonLogUtil.d(TAG, "checkSubmitEnable: " + str + AppInfo.DELIM + str2);
        this.mView.setSubmitEnable(!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && str.length() >= 6 && str2.length() >= 6 && str.length() == str2.length());
    }

    @Override // com.ido.life.module.user.resetpassword.SetNewPasswordContract.Presenter
    public void doResetPassword(String str, String str2, String str3) {
        this.mView.showLoading();
        requestResetPassword(str, str2, str3);
    }

    private void requestResetPassword(String str, String str2, String str3) {
        AccountRepository.getInstance().requestPasswordReset(str, str2, str3, new OnResultCallback() { // from class: com.ido.life.module.user.resetpassword.SetNewPasswordPresenter.1
            @Override // com.ido.life.data.listener.OnResultCallback
            public void onSuccess(Result result) {
                SetNewPasswordPresenter.this.mView.showSuccess();
            }

            @Override // com.ido.life.data.listener.OnResultCallback
            public void onFailed(String str4) {
                if (str4.equals(LanguageUtil.getLanguageText(R.string.me_code_error_over))) {
                    SetNewPasswordPresenter.this.mView.goback(str4);
                } else {
                    SetNewPasswordPresenter.this.mView.showError(str4);
                }
            }
        });
    }
}