package com.ido.life.module.bind;

import com.ido.ble.BLEManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.ScanCallBack;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.PermissionUtil;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.ble.BleHelper;
import com.ido.life.data.device.remote.DeviceManager;
import com.ido.life.database.model.DeviceWhiteListEntity;
import com.ido.life.module.bind.SearchPresenter;
import com.ido.life.util.FunctionHelper;
import com.ido.life.util.SPHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SearchPresenter extends BaseCmdPresenter<ISearchView> {
    private boolean isSearching;
    private List<BLEDevice> mDeviceList;
    private int mType;
    private final ScanCallBack.ICallBack scanCallBack = new AnonymousClass2();

    public boolean isSearching() {
        return this.isSearching;
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void init() {
        super.init();
        DeviceManager.requestDeviceWhiteList(new DeviceManager.OnDeviceCallback<List<DeviceWhiteListEntity.DeviceInfo>>() { // from class: com.ido.life.module.bind.SearchPresenter.1
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(List<DeviceWhiteListEntity.DeviceInfo> list) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getBindLogPath(), "SearchPresenter", "requestDeviceWhiteList onSuccess ： " + GsonUtil.toJson(list));
                SPHelper.saveDeviceWhiteList(list);
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getBindLogPath(), "SearchPresenter", "requestDeviceWhiteList onFailed :" + str);
            }
        });
    }

    public void scanDevice(int i) {
        this.mType = i;
        if (isBleEnable()) {
            if (this.isSearching) {
                return;
            }
            BLEManager.registerScanCallBack(this.scanCallBack);
            BLEManager.startScanDevices();
            this.isSearching = true;
            return;
        }
        if (isAttachView()) {
            ((ISearchView) getView()).OnNeedOpenBle();
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.bind.SearchPresenter$2, reason: invalid class name */
    class AnonymousClass2 implements ScanCallBack.ICallBack {
        AnonymousClass2() {
        }

        @Override // com.ido.ble.callback.ScanCallBack.ICallBack
        public void onStart() {
            if (SearchPresenter.this.mDeviceList == null) {
                SearchPresenter.this.mDeviceList = new ArrayList();
            }
            SearchPresenter.this.mDeviceList.clear();
            if (SearchPresenter.this.isAttachView()) {
                ((ISearchView) SearchPresenter.this.getView()).onSearchStart();
            }
        }

        @Override // com.ido.ble.callback.ScanCallBack.ICallBack
        public void onFindDevice(BLEDevice bLEDevice) {
            if (SearchPresenter.this.mDeviceList == null) {
                SearchPresenter.this.mDeviceList = new ArrayList();
            }
            if ((SearchPresenter.this.isBindAndConnected() && SearchPresenter.this.getDeviceInfo().mDeviceAddress.equals(bLEDevice.mDeviceAddress)) || !FunctionHelper.searchDevice().contains(Integer.valueOf(bLEDevice.mDeviceId)) || SearchPresenter.this.mDeviceList.contains(bLEDevice)) {
                return;
            }
            if (bLEDevice.mIsInDfuMode) {
                SearchPresenter.this.mDeviceList.add(bLEDevice);
                Collections.sort(SearchPresenter.this.mDeviceList, new Comparator() { // from class: com.ido.life.module.bind.-$$Lambda$SearchPresenter$2$ybpcYGb0BTlJU7ZupKoDvE4T29Y
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        return SearchPresenter.AnonymousClass2.lambda$onFindDevice$0((BLEDevice) obj, (BLEDevice) obj2);
                    }
                });
                if (SearchPresenter.this.isAttachView()) {
                    ((ISearchView) SearchPresenter.this.getView()).onSearchedDevice(bLEDevice, SearchPresenter.this.mDeviceList);
                    ((ISearchView) SearchPresenter.this.getView()).onSearchedDFUDevice(bLEDevice);
                    return;
                }
                return;
            }
            if (SearchPresenter.this.mType == 1) {
                if (SearchPresenter.this.mType == bLEDevice.type || bLEDevice.type == -1) {
                    SearchPresenter.this.mDeviceList.add(bLEDevice);
                    Collections.sort(SearchPresenter.this.mDeviceList, new Comparator() { // from class: com.ido.life.module.bind.-$$Lambda$SearchPresenter$2$m6yYMYXEk9FwgUCBPB9UXDRUus4
                        @Override // java.util.Comparator
                        public final int compare(Object obj, Object obj2) {
                            return SearchPresenter.AnonymousClass2.lambda$onFindDevice$1((BLEDevice) obj, (BLEDevice) obj2);
                        }
                    });
                    if (SearchPresenter.this.isAttachView()) {
                        ((ISearchView) SearchPresenter.this.getView()).onSearchedDevice(bLEDevice, SearchPresenter.this.mDeviceList);
                        return;
                    }
                    return;
                }
                return;
            }
            if (bLEDevice.type != 1) {
                SearchPresenter.this.mDeviceList.add(bLEDevice);
                Collections.sort(SearchPresenter.this.mDeviceList, new Comparator() { // from class: com.ido.life.module.bind.-$$Lambda$SearchPresenter$2$bSvLtHHXvaeASEV3JKupO_Tm9Rk
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        return SearchPresenter.AnonymousClass2.lambda$onFindDevice$2((BLEDevice) obj, (BLEDevice) obj2);
                    }
                });
                if (SearchPresenter.this.isAttachView()) {
                    ((ISearchView) SearchPresenter.this.getView()).onSearchedDevice(bLEDevice, SearchPresenter.this.mDeviceList);
                }
            }
        }

        static /* synthetic */ int lambda$onFindDevice$0(BLEDevice bLEDevice, BLEDevice bLEDevice2) {
            return bLEDevice2.mRssi - bLEDevice.mRssi;
        }

        static /* synthetic */ int lambda$onFindDevice$1(BLEDevice bLEDevice, BLEDevice bLEDevice2) {
            return bLEDevice2.mRssi - bLEDevice.mRssi;
        }

        static /* synthetic */ int lambda$onFindDevice$2(BLEDevice bLEDevice, BLEDevice bLEDevice2) {
            return bLEDevice2.mRssi - bLEDevice.mRssi;
        }

        @Override // com.ido.ble.callback.ScanCallBack.ICallBack
        public void onScanFinished() {
            SearchPresenter.this.isSearching = false;
            BLEManager.unregisterScanCallBack(SearchPresenter.this.scanCallBack);
            if (SearchPresenter.this.isAttachView()) {
                if (SearchPresenter.this.mDeviceList != null) {
                    if (SearchPresenter.this.mDeviceList.size() > 0) {
                        ((ISearchView) SearchPresenter.this.getView()).onSearchFinished();
                        return;
                    }
                    if (!PermissionUtil.checkSelfPermission(IdoApp.getAppContext(), PermissionUtil.getLocationPermission())) {
                        ((ISearchView) SearchPresenter.this.getView()).onNeedLocationPermission();
                        return;
                    } else if (BleHelper.isOpenGPS(IdoApp.getAppContext())) {
                        ((ISearchView) SearchPresenter.this.getView()).onSearchFailed();
                        return;
                    } else {
                        ((ISearchView) SearchPresenter.this.getView()).onNeedOpenGps();
                        return;
                    }
                }
                if (!SearchPresenter.this.isBleEnable()) {
                    ((ISearchView) SearchPresenter.this.getView()).OnNeedOpenBle();
                } else {
                    SearchPresenter searchPresenter = SearchPresenter.this;
                    searchPresenter.scanDevice(searchPresenter.mType);
                }
            }
        }
    }

    public void stopScan() {
        this.isSearching = false;
        BLEManager.unregisterScanCallBack(this.scanCallBack);
        BLEManager.stopScanDevices();
    }

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void detachView() {
        super.detachView();
        stopScan();
    }
}