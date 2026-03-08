package com.ido.life.module.user.set.modify;

import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;

/* JADX INFO: loaded from: classes3.dex */
public class ModifyPwdContract {

    interface Presenter extends BasePresenter {
        void checkSubmitEnable(String str, String str2, String str3);

        void doResetPassword(String str, String str2, String str3);

        void initData();
    }

    interface View extends BaseView<Presenter> {
        void hideLoading();

        void setSubmitEnable(boolean z);

        void showError(String str);

        void showLoading();

        void showSuccess();
    }
}