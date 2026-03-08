package com.ido.life.module.user.bind;

import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;

/* JADX INFO: loaded from: classes3.dex */
public class BindRegisterAccountContract {

    interface Presenter extends BasePresenter {
        void checkSubmitEnable(String str, String str2, String str3);

        void doGetCode(String str, String str2, String str3);

        void doRegister(long j);

        void setPhoneNumberRegister(boolean z);
    }

    interface View extends BaseView<Presenter> {
        String getCode();

        String getCountryCode();

        String getName();

        String getPassword();

        void goMain();

        void goUserData(int i);

        void goUserTarget();

        void hideLoading();

        void hideNameCountry();

        boolean isPhoneNumberRegister();

        void setNameHint(String str);

        void setSubmitEnable(boolean z);

        void setViewNull();

        void showError(String str);

        void showLoading();

        void showMessage(String str);

        void showNameCountry();

        void showSuccess();

        void startCountDown();

        void stopCountDown();
    }
}