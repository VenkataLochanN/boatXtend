package com.ido.life.module.user.set;

import android.content.Context;
import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;

/* JADX INFO: loaded from: classes3.dex */
public class SettingContract {

    interface Presenter extends BasePresenter {
        void changeUnit(int i);

        void doClearCache(Context context);

        void getCacheSize(Context context);

        boolean isUserLogin();
    }

    interface View extends BaseView<Presenter> {
        void showCacheSize(String str);
    }
}