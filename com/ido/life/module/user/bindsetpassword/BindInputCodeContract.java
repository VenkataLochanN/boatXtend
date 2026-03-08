package com.ido.life.module.user.bindsetpassword;

import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;
import com.ido.life.database.model.UserInfo;

/* JADX INFO: loaded from: classes3.dex */
public interface BindInputCodeContract {

    public interface Presenter extends BasePresenter {
        void checkSubmitEnable(String str);

        void doGetCode(String str, String str2);

        void doThirdLoginAndBindUnRegister(long j, String str, String str2, String str3, String str4);
    }

    public interface View extends BaseView<Presenter> {
        void hideLoading();

        void setSubmitEnable(boolean z);

        void showError(String str);

        void showGetCodeError(String str);

        void showGetCodeSuccess();

        void showLoading();

        void showMessage(String str);

        void showSuccess();

        void showSuccess(UserInfo userInfo);

        void startCountDown();

        void stopCountDown();

        void toThirdLoginAndBindUnRegister();

        void toUserData();
    }
}