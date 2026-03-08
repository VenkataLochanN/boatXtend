package com.ido.life.module.sport;

import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;
import com.ido.life.bean.SortBean;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SportContract {

    interface Presenter extends BasePresenter {
        void getCardData();

        void getDeviceStatus();

        void showDeviceStatus();

        void startLocation();

        void stopLocation();
    }

    interface View extends BaseView<Presenter> {
        int getSportType();

        void setGpsStatus(int i);

        void setSportChoose(List<SortBean> list);

        void setSportType(int i);

        void showConnectDevice();

        void showDisconnectDevice();

        void showLocationServiceDialog();
    }
}