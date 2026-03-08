package com.ido.life.module.user.resetpassword;

import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;
import com.ido.life.database.model.UserInfo;

/* JADX INFO: loaded from: classes3.dex */
public interface ResetPassContract {

    public interface Presenter extends BasePresenter {
        void checkCodeIsRight(String str, String str2, String str3);

        void checkSubmitEnable(String str, String str2);

        void doCheckAccountIsExist(String str);

        void doGetCode(String str);
    }

    public interface View extends BaseView<Presenter> {
        void hideLoading();

        void setSubmitEnable(boolean z);

        void showError(String str);

        void showGetCodeError(String str);

        void showGetCodeSuccess();

        void showLoading();

        void showSuccess();

        void showSuccess(UserInfo userInfo);

        void startCountDown();

        void stopCountDown();

        void toCheckCode();

        void toSetNewPassword();
    }
}