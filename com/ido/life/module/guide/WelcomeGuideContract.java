package com.ido.life.module.guide;

import androidx.fragment.app.FragmentManager;
import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;

/* JADX INFO: loaded from: classes2.dex */
public interface WelcomeGuideContract {

    public interface Presenter extends BasePresenter {
        void toShowPrivicy(FragmentManager fragmentManager);
    }

    public interface View extends BaseView<Presenter> {
        void quitApp();

        void toPreLoginAndRegister();
    }
}