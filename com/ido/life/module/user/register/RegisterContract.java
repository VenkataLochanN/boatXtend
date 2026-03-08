package com.ido.life.module.user.register;

import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;

/* JADX INFO: loaded from: classes3.dex */
public interface RegisterContract {

    public interface Presenter extends BasePresenter {
        void checkRegisterCode(String str, String str2);

        void checkSubmitEnable(String str, String str2, String str3, boolean z);

        void doGetCode(String str, String str2, String str3);

        void doRegister(String str, String str2, String str3, String str4);

        void doThirdLogin(int i);

        void setLocationCountry(String str);

        void setPhoneNumberRegister(boolean z);
    }

    public interface View extends BaseView<Presenter> {
        String getName();

        String getPassword();

        void goBind(long j, String str, String str2, String str3);

        void goCheckEmail();

        void goMain();

        void goUserData(int i);

        void goUserTarget();

        void hideLoading();

        boolean isAgreeCheckbox();

        boolean isPhoneNumberRegister();

        void setLocationCountryArea(String str);

        void setNameHint(String str);

        void setSubmitEnable(boolean z);

        void setViewNull();

        void showError(String str);

        void showLoading();

        void showMessage(String str);

        void showSuccess();
    }
}