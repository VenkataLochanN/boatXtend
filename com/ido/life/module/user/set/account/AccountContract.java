package com.ido.life.module.user.set.account;

import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;

/* JADX INFO: loaded from: classes3.dex */
public class AccountContract {

    interface Presenter extends BasePresenter {
        void cancelThirdAccount(String str);

        void getOpenId(String str);

        void initData();

        void loginOut();

        void queryThirdAccount();
    }

    interface View extends BaseView<Presenter> {
        void goBack();

        void hideLoading();

        void loginOutFailed();

        void loginOutSuccess();

        void setCheckFacebook(boolean z);

        void setCheckGoogle(boolean z);

        void setCheckQQ(boolean z);

        void setCheckTwitter(boolean z);

        void setCheckWeChat(boolean z);

        void showLoading();

        void showMessage(String str);

        void showUserAccount(String str);
    }
}