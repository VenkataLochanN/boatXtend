package com.ido.life.module.bind.scan;

import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.common.base.BasePresenter;
import com.ido.common.base.BaseView;

/* JADX INFO: loaded from: classes2.dex */
public class ScanContract {

    public interface Presenter extends BasePresenter {
        void doAnalyzeSuccess(String str);

        void onAnalyzeFailed();

        void scanDevice();

        void stopScan();
    }

    public interface View extends BaseView<Presenter> {
        void goBack();

        void goBind(BLEDevice bLEDevice);

        void goBindActivity(BLEDevice bLEDevice);

        void onNeedOpenBle();

        void onSearchFailed();

        void onSearchFinished();

        void showErrorCode();

        void showMessage(String str);

        void toWebView(String str);
    }
}