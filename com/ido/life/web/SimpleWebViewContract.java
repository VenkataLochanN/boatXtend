package com.ido.life.web;

import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;

/* JADX INFO: loaded from: classes3.dex */
public class SimpleWebViewContract {

    interface Presenter extends BasePresenter {
        void downloadH5(int i);
    }

    interface View extends BaseView<Presenter> {
        void onGetH5(String str);
    }
}