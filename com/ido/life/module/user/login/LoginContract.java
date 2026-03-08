package com.ido.life.module.user.login;

import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;
import com.ido.life.database.model.UserInfo;

/* JADX INFO: loaded from: classes3.dex */
public interface LoginContract {

    public interface Presenter extends BasePresenter {
        void checkSubmitEnable(String str, String str2);

        void clickResetPassword(String str);

        void doLogin(String str, String str2, String str3);

        void doThirdLogin(int i);

        void judge(UserInfo userInfo);
    }

    public interface View extends BaseView<Presenter> {
        void goBind(long j, String str, String str2, String str3);

        void goForgetPassword(String str);

        void goMain();

        void goUserData(int i);

        void goUserTarget();

        void hideLoading();

        void setSubmitEnable(boolean z);

        void showError(int i, String str);

        void showLoading();

        void showSuccess();
    }
}