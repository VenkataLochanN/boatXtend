package com.ido.life.module.user.userdata.sex;

import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;

/* JADX INFO: loaded from: classes3.dex */
public class SexContract {

    interface Presenter extends BasePresenter {
        void initSex();

        void saveSex(int i);
    }

    interface View extends BaseView<Presenter> {
        void changeForwardEnable(boolean z);

        void hideLoading();

        void setManSelect();

        void setWomanSelect();

        void showLoading();

        void showMessage(String str);

        void toForward();
    }
}