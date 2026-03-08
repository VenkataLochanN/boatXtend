package com.ido.life.module.user.emailcheck;

import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;

/* JADX INFO: loaded from: classes3.dex */
public class CheckEmailContract {

    interface Presenter extends BasePresenter {
        void checkSubmitEnable(String str);

        void doCheckEmailCode(String str);

        void doGetCode();

        void initData(boolean z);
    }

    interface View extends BaseView<Presenter> {
        void goUserData();

        void hideLoading();

        void setGetCodeEnable(boolean z);

        void setSubmitEnable(boolean z);

        void showError(String str);

        void showGetCodeError(String str);

        void showGetCodeSuccess();

        void showLoading();

        void showSuccess();

        void startCountDown();

        void stopCountDown();
    }
}