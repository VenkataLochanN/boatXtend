package com.ido.life.module.user.lang;

import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;
import com.ido.life.module.user.lang.LanguageActivity;

/* JADX INFO: loaded from: classes3.dex */
public class LanguageContract {

    interface Presenter extends BasePresenter {
        void doChangeLanguage(String str);

        void getMultilLanguage(String str, LanguageActivity.FinishCallBack finishCallBack);

        void save();
    }

    interface View extends BaseView<Presenter> {
        String getLanguage();

        void selected(int i);
    }
}