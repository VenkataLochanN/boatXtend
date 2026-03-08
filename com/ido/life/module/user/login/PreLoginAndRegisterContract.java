package com.ido.life.module.user.login;

import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;
import com.ido.life.database.model.UserInfo;

/* JADX INFO: loaded from: classes3.dex */
public interface PreLoginAndRegisterContract {

    public interface Presenter extends BasePresenter {
        void deleteAllLoginData();

        void doThirdLogin(int i);

        void judge(UserInfo userInfo);
    }

    public interface View extends BaseView<Presenter> {
        void goBind(long j, String str, String str2, String str3);

        void goMain();

        void goUserData(int i);

        void goUserTarget();

        void hideLoading();

        void showError(String str);

        void showLoading();

        void showSuccess();
    }
}