package com.ido.life.module.user.set.cancel;

import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;

/* JADX INFO: loaded from: classes3.dex */
public class CancelConfirmContract {

    interface Presenter extends BasePresenter {
        void checkSubmitEnable(String str);

        void doCancelUser(String str, String str2);

        void doGetCode();

        void initData();
    }

    interface View extends BaseView<Presenter> {
        String getCountryCode();

        void goPreLoginAndRegister();

        void hideLoading();

        void setAraeText(String str);

        void setAreaVisible(boolean z);

        void setGetCodeEnable(boolean z);

        void setSubmitEnable(boolean z);

        void showError(String str);

        void showGetCodeError(String str);

        void showGetCodeSuccess();

        void showLoading();

        void showSuccess();

        void showUserAccount(String str);

        void startCountDown();

        void stopCountDown();
    }
}