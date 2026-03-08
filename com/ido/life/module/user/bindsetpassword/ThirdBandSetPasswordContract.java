package com.ido.life.module.user.bindsetpassword;

import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;
import com.ido.life.database.model.UserInfo;

/* JADX INFO: loaded from: classes3.dex */
public interface ThirdBandSetPasswordContract {

    public interface Presenter extends BasePresenter {
        void checkSubmitEnable(String str, String str2);

        void doResetPassword(String str, String str2, String str3);
    }

    public interface View extends BaseView<Presenter> {
        void setSubmitEnable(boolean z);

        void showError(String str);

        void showLoading();

        void showSuccess();

        void showSuccess(UserInfo userInfo);
    }
}