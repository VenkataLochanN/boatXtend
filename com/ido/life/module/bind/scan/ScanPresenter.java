package com.ido.life.module.bind.scan;

import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.ble.bluetooth.connect.ConnectFailedReason;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.ConnectCallBack;
import com.ido.ble.callback.ScanCallBack;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.ble.BleHelper;
import com.ido.life.module.bind.scan.ScanContract;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ScanPresenter implements ScanContract.Presenter {
    private static final String TAG = "ScanPresenter";
    private List<BLEDevice> mDeviceList;
    private String mMac;
    private ScanContract.View mView;
    private ConnectCallBack.ICallBack connectCallBack = new ConnectCallBack.ICallBack() { // from class: com.ido.life.module.bind.scan.ScanPresenter.1
        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectStart(String str) {
            CommonLogUtil.d(ScanPresenter.TAG, "onConnectStart: ");
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnecting(String str) {
            CommonLogUtil.d(ScanPresenter.TAG, "onConnecting: ");
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onRetry(int i, String str) {
            CommonLogUtil.d(ScanPresenter.TAG, "onRetry: ");
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectSuccess(String str) {
            CommonLogUtil.d(ScanPresenter.TAG, "onConnectSuccess: ");
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectFailed(ConnectFailedReason connectFailedReason, String str) {
            CommonLogUtil.d(ScanPresenter.TAG, "onConnectFailed: ");
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectBreak(String str) {
            CommonLogUtil.d(ScanPresenter.TAG, "onConnectBreak: ");
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onInDfuMode(BLEDevice bLEDevice) {
            CommonLogUtil.d(ScanPresenter.TAG, "onInDfuMode: ");
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onDeviceInNotBindStatus(String str) {
            CommonLogUtil.d(ScanPresenter.TAG, "onDeviceInNotBindStatus: ");
        }

        @Override // com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onInitCompleted(String str) {
            CommonLogUtil.d(ScanPresenter.TAG, "onInitCompleted: ");
        }
    };
    private ScanCallBack.ICallBack scanCallBack = new ScanCallBack.ICallBack() { // from class: com.ido.life.module.bind.scan.ScanPresenter.2
        @Override // com.ido.ble.callback.ScanCallBack.ICallBack
        public void onStart() {
            CommonLogUtil.d(ScanPresenter.TAG, "onDownloadStart: ");
            if (ScanPresenter.this.mDeviceList == null) {
                ScanPresenter.this.mDeviceList = new ArrayList();
            }
            ScanPresenter.this.mDeviceList.clear();
        }

        @Override // com.ido.ble.callback.ScanCallBack.ICallBack
        public void onFindDevice(BLEDevice bLEDevice) {
            CommonLogUtil.d(ScanPresenter.TAG, "onFindDevice: " + bLEDevice.toString());
            if (ScanPresenter.this.mDeviceList == null) {
                ScanPresenter.this.mDeviceList = new ArrayList();
            }
            if (ScanPresenter.this.mDeviceList.contains(bLEDevice)) {
                return;
            }
            if (bLEDevice.mDeviceAddress.equals(ScanPresenter.this.mMac)) {
                ScanPresenter.this.mView.goBindActivity(bLEDevice);
            }
            ScanPresenter.this.mDeviceList.add(bLEDevice);
        }

        @Override // com.ido.ble.callback.ScanCallBack.ICallBack
        public void onScanFinished() {
            CommonLogUtil.d(ScanPresenter.TAG, "onScanFinished: ");
            BLEManager.unregisterScanCallBack(ScanPresenter.this.scanCallBack);
        }
    };

    @Override // com.ido.life.module.bind.scan.ScanContract.Presenter
    public void onAnalyzeFailed() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public ScanPresenter(ScanContract.View view) {
        this.mView = view;
    }

    @Override // com.ido.life.module.bind.scan.ScanContract.Presenter
    public void doAnalyzeSuccess(String str) {
        CommonLogUtil.d(TAG, "doAnalyzeSuccess: " + str);
        if (!TextUtils.isEmpty(str)) {
            analyzeString(str);
        } else {
            onAnalyzeFailed();
        }
    }

    private void analyzeString(String str) {
        CommonLogUtil.d(TAG, "analyzeString:扫描前 " + str);
        CommonLogUtil.printAndSave("ScanPresenteranalyzeString:扫描前 " + str);
        if (!TextUtils.isEmpty(str)) {
            if (str.contains("?m=")) {
                this.mMac = getMac(str);
                CommonLogUtil.printAndSave("ScanPresenteranalyzeString:解析后 " + this.mMac);
                if (TextUtils.isEmpty(this.mMac) || !stringIsMac(this.mMac)) {
                    this.mView.showMessage(LanguageUtil.getLanguageText(R.string.scan_fail));
                    this.mView.goBack();
                    return;
                }
                BLEDevice bLEDevice = new BLEDevice();
                bLEDevice.mDeviceAddress = this.mMac;
                CommonLogUtil.d(TAG, "analyzeString: 解析后" + this.mMac);
                this.mView.goBind(bLEDevice);
                return;
            }
            if (str.contains("https://life-content.idoocloud.com/page/help_explain")) {
                this.mView.toWebView(str);
                return;
            } else {
                this.mView.showMessage(LanguageUtil.getLanguageText(R.string.scan_error));
                return;
            }
        }
        this.mView.showErrorCode();
    }

    private boolean stringIsMac(String str) {
        return str.matches("([A-Fa-f0-9]{2}:){5}[A-Fa-f0-9]{2}");
    }

    private String getMac(String str) {
        String[] strArrSplit = str.split("m=");
        return strArrSplit.length > 1 ? strArrSplit[1].toUpperCase() : "";
    }

    public void scanAndConnectDevice(String str) {
        if (isBleEnable()) {
            BLEManager.registerScanCallBack(this.scanCallBack);
            BLEManager.registerConnectCallBack(this.connectCallBack);
            BLEManager.scanAndConnect(this.mMac);
            return;
        }
        this.mView.onNeedOpenBle();
    }

    @Override // com.ido.life.module.bind.scan.ScanContract.Presenter
    public void scanDevice() {
        if (isBleEnable()) {
            BLEManager.registerScanCallBack(this.scanCallBack);
            BLEManager.startScanDevices();
        } else {
            this.mView.onNeedOpenBle();
        }
    }

    public boolean isBleEnable() {
        return BleHelper.isBluetoothOpen();
    }

    @Override // com.ido.life.module.bind.scan.ScanContract.Presenter
    public void stopScan() {
        BLEManager.unregisterScanCallBack(this.scanCallBack);
        BLEManager.stopScanDevices();
    }
}