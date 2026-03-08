package com.ido.life.module.user.userdata;

import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;

/* JADX INFO: loaded from: classes3.dex */
public interface UserDataContract {

    public interface Presenter extends BasePresenter {
        void back();

        void forward();
    }

    public interface View extends BaseView<Presenter> {
        void back();

        int getIndex();

        void hideLoading();

        void setEnable(boolean z);

        void showCurrentPage(int i);

        void showLoading();

        void showMessage(String str);

        void showProgress(int i);

        void toMain();

        void toUserTarget();
    }
}