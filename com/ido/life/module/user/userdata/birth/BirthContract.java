package com.ido.life.module.user.userdata.birth;

import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;

/* JADX INFO: loaded from: classes3.dex */
public interface BirthContract {

    public interface Presenter extends BasePresenter {
        void checkAge(String str, String str2, String str3);

        void getAge(String str, String str2, String str3);

        void initAge();

        void saveBirthday(String str);
    }

    public interface View extends BaseView<Presenter> {
        void clearDay();

        void clearMonth();

        void clearYear();

        String getDay();

        String getMonth();

        String getYear();

        void hideLoading();

        void setDay(String str);

        void setForward();

        void setForwardEnable(boolean z);

        void setMonth(String str);

        void setYear(String str);

        void showLoading();

        void showMessage();

        void showMessage(String str);
    }
}