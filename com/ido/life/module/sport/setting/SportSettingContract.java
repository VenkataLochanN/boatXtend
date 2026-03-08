package com.ido.life.module.sport.setting;

import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;

/* JADX INFO: loaded from: classes2.dex */
public class SportSettingContract {

    interface Presenter extends BasePresenter {
        void clearTarget();

        void getHistoryData();

        boolean getSupportFunction();

        void setDistanceInterval(int i);

        void setIsCycle(boolean z);

        void setTarget(int i, int i2);
    }

    interface View extends BaseView<Presenter> {
        void setRateRange(String str);

        void setSportDistance(String str);

        void setSportTarget(String str);
    }
}