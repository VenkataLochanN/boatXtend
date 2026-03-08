package com.ido.life.module.sport.ready;

import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;

/* JADX INFO: loaded from: classes2.dex */
public class SportRunReadyContract {

    interface Presenter extends BasePresenter {
        void clearListener();

        void forceEndRun();

        void forceStartRun();

        void playTipsMusic();

        void setOnStartListener();

        void startRun(int i, boolean z);
    }

    interface View extends BaseView<Presenter> {
        void showSportStartError(String str);

        void showSportStartFail();

        void showSportStartFailedChargePower();

        void showSportStartFailedInCalling();

        void showSportStartFailedLowPower();

        void showSportStartSuccess();
    }
}