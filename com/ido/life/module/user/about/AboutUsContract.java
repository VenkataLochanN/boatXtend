package com.ido.life.module.user.about;

import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;

/* JADX INFO: loaded from: classes.dex */
public class AboutUsContract {

    interface Presenter extends BasePresenter {
        void initData();
    }

    interface View extends BaseView<Presenter> {
        void onGetNewVersion(String str);
    }
}