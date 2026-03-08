package com.ido.life.module.sport;

import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;

/* JADX INFO: loaded from: classes2.dex */
public class SportBgContract {

    interface Presenter extends BasePresenter {
        void getDeviceStatus();

        void getTotalDistance(int i);

        void showBgByType(int i);

        void showDeviceStatus();

        void startRun();

        void toStartOutSport();
    }

    interface View extends BaseView<Presenter> {
        void goSportReady(int i, boolean z);

        void goSportReadyPermission(int i, boolean z);

        void setGpsStatus(int i);

        void setTotalSportDistance(String str);

        void setTotalSportDistanceVisible(boolean z);

        void showConnectDevice();

        void showDeviceAddDialog();

        void showDeviceConnectDialog();

        void showDisconnectDevice();

        void showInDoorBg();

        void showLocationPermissionDialog();

        void showLocationPermissionDialogForAndroidQ();

        void showLocationServiceDialog();

        void showMessage(String str);

        void showOutDoorBg();
    }
}