package com.ido.life.module.user.userdata.height;

import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;

/* JADX INFO: loaded from: classes3.dex */
public class HeightContract {

    interface Presenter extends BasePresenter {
        void checkHeight(int[] iArr);

        void getHeight(String str);

        int getHeightCm();

        int getHeightInch();

        void initHeight();

        void setHeightType(int i, int[] iArr);
    }

    interface View extends BaseView<Presenter> {
        void changeForwardEnable(boolean z);

        int getHeight();

        void hideLoading();

        void setCurrentIndex(int i);

        void setForward();

        void setHeight(int i, int i2);

        void setRulerView(int i);

        void showLoading();

        void showMessage(String str);
    }
}