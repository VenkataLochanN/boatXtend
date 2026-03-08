package com.ido.life.module.user.set.modifyemail;

import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;

/* JADX INFO: loaded from: classes3.dex */
public class ModifyEmailContract {

    interface Presenter extends BasePresenter {
        void checkSubmitEnable(String str);

        void doGetCode(String str);

        void doModifyEmail();

        void initData();
    }

    interface View extends BaseView<Presenter> {
        String getNewEmail();

        String getVerificationCode();

        void hideLoading();

        void setGetCodeEnable(boolean z);

        void setSubmitEnable(boolean z);

        void showError(String str);

        void showGetCodeError(String str);

        void showGetCodeSuccess();

        void showLoading();

        void showSuccess(String str);

        void startCountDown();

        void stopCountDown();
    }
}