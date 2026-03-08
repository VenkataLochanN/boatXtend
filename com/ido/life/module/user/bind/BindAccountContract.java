package com.ido.life.module.user.bind;

import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;
import com.ido.life.database.model.UserInfo;

/* JADX INFO: loaded from: classes3.dex */
public class BindAccountContract {

    interface Presenter extends BasePresenter {
        void checkSubmitEnable(String str);

        void clickResetPassword(String str);

        void doBindAlreadyRegister(Long l, String str, String str2, String str3, String str4, String str5);

        void doJudgeEamilIsExist(String str);

        void judge(UserInfo userInfo);
    }

    interface View extends BaseView<Presenter> {
        void goForgetPassword(String str);

        void goMain();

        void goPasswordCode();

        void goUnRegisterSetPassword();

        void goUserData(int i);

        void goUserTarget();

        void hideLoading();

        void setSubmitEnable(boolean z);

        void showError(String str);

        void showLoading();

        void showSuccess();

        void showSuccess(UserInfo userInfo);
    }
}