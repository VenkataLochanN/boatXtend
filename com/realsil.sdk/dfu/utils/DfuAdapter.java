package com.realsil.sdk.dfu.utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.realsil.sdk.core.bluetooth.BluetoothProfileCallback;
import com.realsil.sdk.core.bluetooth.BluetoothProfileManager;
import com.realsil.sdk.core.bluetooth.RtkBluetoothManager;
import com.realsil.sdk.core.bluetooth.RtkBluetoothManagerCallback;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.DfuException;
import com.realsil.sdk.dfu.RtkDfu;
import com.realsil.sdk.dfu.image.BinFactory;
import com.realsil.sdk.dfu.image.BinIndicator;
import com.realsil.sdk.dfu.image.LoadParams;
import com.realsil.sdk.dfu.image.SubFileInfo;
import com.realsil.sdk.dfu.model.BinInfo;
import com.realsil.sdk.dfu.model.DfuConfig;
import com.realsil.sdk.dfu.model.DfuProgressInfo;
import com.realsil.sdk.dfu.model.FileTypeInfo;
import com.realsil.sdk.dfu.model.OtaDeviceInfo;
import com.realsil.sdk.dfu.model.OtaModeInfo;
import com.realsil.sdk.dfu.model.Throughput;
import com.realsil.sdk.dfu.utils.ConnectParams;
import h.a;
import h.b;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class DfuAdapter {
    public static final int STATE_ABORTED = 2048;
    public static final int STATE_CONNECTING = 260;
    public static final int STATE_CONNECT_FAILED = 1026;
    public static final int STATE_DISCONNECTED = 1025;
    public static final int STATE_DISCONNECTING = 1024;
    public static final int STATE_DISCOVERY_SERVICE = 263;
    public static final int STATE_HID_CONNECTING = 257;
    public static final int STATE_HID_PENDING_CREATE_BOND = 259;
    public static final int STATE_HID_PENDING_REMOVE_BOND = 258;
    public static final int STATE_INIT = 0;
    public static final int STATE_INIT_BINDING_SERVICE = 1;
    public static final int STATE_INIT_OK = 2;
    public static final int STATE_OTA_IDLE = 514;
    public static final int STATE_OTA_PROCESSING = 513;
    public static final int STATE_PENDDING_DISCOVERY_SERVICE = 262;
    public static final int STATE_PREPARED = 512;
    public static final int STATE_PREPARING = 256;
    public static final int STATE_PROCESS_PAIRING_REQUEST = 261;
    public static final int STATE_READ_BATTERY_INFO = 266;
    public static final int STATE_READ_DEVICE_INFO = 264;
    public static final int STATE_READ_IMAGE_INFO = 265;
    public String Ae;
    public ConnectParams Be;
    public List<OtaModeInfo> De;
    public RtkBluetoothManager He;
    public DfuHelperCallback Je;
    public OtaDeviceInfo Vc;
    public Context mContext;
    public BluetoothAdapter qc;
    public BluetoothProfileManager rc;
    public BluetoothDevice ze;
    public boolean D = true;
    public a Ie = null;
    public int ye = 10;
    public int mState = 0;
    public Object mLock = new Object();
    public IInnerHandler zc = new IInnerHandler() { // from class: com.realsil.sdk.dfu.utils.DfuAdapter.1
        @Override // com.realsil.sdk.dfu.utils.DfuAdapter.IInnerHandler
        public void handleMessage(Message message) {
        }
    };
    public int ve = 2;
    public Handler Ee = new Handler();
    public Runnable Fe = new Runnable() { // from class: com.realsil.sdk.dfu.utils.DfuAdapter.2
        @Override // java.lang.Runnable
        public void run() {
            ZLogger.i("retry to connect device, reconnectTimes =" + DfuAdapter.this.ve);
            DfuAdapter dfuAdapter = DfuAdapter.this;
            dfuAdapter.connectDevice(dfuAdapter.Ae, false);
        }
    };
    public RtkBluetoothManagerCallback wc = new RtkBluetoothManagerCallback() { // from class: com.realsil.sdk.dfu.utils.DfuAdapter.3
        @Override // com.realsil.sdk.core.bluetooth.RtkBluetoothManagerCallback
        public void onBluetoothStateChaned(BluetoothDevice bluetoothDevice, int i) {
            super.onBluetoothStateChaned(bluetoothDevice, i);
        }

        @Override // com.realsil.sdk.core.bluetooth.RtkBluetoothManagerCallback
        public void onBondStateChanged(BluetoothDevice bluetoothDevice, int i) {
            super.onBondStateChanged(bluetoothDevice, i);
            String str = DfuAdapter.this.Ae;
            if (str == null) {
                return;
            }
            if (str.equals(bluetoothDevice.getAddress())) {
                DfuAdapter.this.processBondStateChanged(i);
            } else {
                ZLogger.v("配对设备和当前连接设备不一致");
            }
        }
    };
    public BluetoothProfileCallback Ge = new BluetoothProfileCallback() { // from class: com.realsil.sdk.dfu.utils.DfuAdapter.4
        @Override // com.realsil.sdk.core.bluetooth.BluetoothProfileCallback
        public void onHidStateChanged(BluetoothDevice bluetoothDevice, int i) {
            super.onHidStateChanged(bluetoothDevice, i);
        }
    };
    public b Ke = new b() { // from class: com.realsil.sdk.dfu.utils.DfuAdapter.5
        @Override // com.realsil.sdk.dfu.core.DfuThreadCallback
        public void onDeviceInfoChanged(OtaDeviceInfo otaDeviceInfo) {
            super.onDeviceInfoChanged(otaDeviceInfo);
            DfuHelperCallback dfuHelperCallback = DfuAdapter.this.Je;
            if (dfuHelperCallback != null) {
                dfuHelperCallback.onTargetInfoChanged(otaDeviceInfo);
            } else {
                ZLogger.v("no callback registed");
            }
        }

        @Override // com.realsil.sdk.dfu.core.DfuThreadCallback
        public void onError(int i) {
            DfuAdapter.this.notifyError(i);
        }

        @Override // com.realsil.sdk.dfu.core.DfuThreadCallback
        public void onProgressChanged(DfuProgressInfo dfuProgressInfo, Throughput throughput) {
            super.onProgressChanged(dfuProgressInfo, throughput);
            DfuHelperCallback dfuHelperCallback = DfuAdapter.this.Je;
            if (dfuHelperCallback != null) {
                dfuHelperCallback.onProgressChanged(dfuProgressInfo);
            } else {
                ZLogger.v("no callback registed");
            }
        }

        @Override // h.b
        public void onServiceConnectionStateChange(boolean z, a aVar) {
            DfuAdapter dfuAdapter;
            int i;
            if (z) {
                ZLogger.i("onServiceConnectionStateChange connected");
                dfuAdapter = DfuAdapter.this;
                dfuAdapter.Ie = aVar;
                i = 2;
            } else {
                ZLogger.w("onServiceConnectionStateChange disconnected");
                dfuAdapter = DfuAdapter.this;
                dfuAdapter.Ie = null;
                i = 0;
            }
            dfuAdapter.e(i);
        }

        @Override // com.realsil.sdk.dfu.core.DfuThreadCallback
        public void onStateChanged(int i, Throughput throughput) {
            super.onStateChanged(i, throughput);
            DfuHelperCallback dfuHelperCallback = DfuAdapter.this.Je;
            if (dfuHelperCallback != null) {
                dfuHelperCallback.onProcessStateChanged(i, throughput);
            } else {
                ZLogger.v("no callback registed");
            }
        }
    };

    public static abstract class DfuHelperCallback {
        public void onError(int i, int i2) {
        }

        public void onProcessStateChanged(int i, Throughput throughput) {
        }

        public void onProgressChanged(DfuProgressInfo dfuProgressInfo) {
        }

        public void onStateChanged(int i) {
        }

        public void onTargetInfoChanged(OtaDeviceInfo otaDeviceInfo) {
        }
    }

    private interface IInnerHandler {
        void handleMessage(Message message);
    }

    private static class InnerHandler extends Handler {
        public IInnerHandler zc;

        public InnerHandler(IInnerHandler iInnerHandler) {
            this.zc = iInnerHandler;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            IInnerHandler iInnerHandler = this.zc;
            if (iInnerHandler != null) {
                iInnerHandler.handleMessage(message);
            } else {
                ZLogger.d("mIInnerHandler is null");
            }
        }
    }

    private static class InnerHandler2 extends Handler {
        public WeakReference<IInnerHandler> yc;

        public InnerHandler2(IInnerHandler iInnerHandler) {
            this.yc = new WeakReference<>(iInnerHandler);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            IInnerHandler iInnerHandler = this.yc.get();
            if (iInnerHandler != null) {
                iInnerHandler.handleMessage(message);
            } else {
                ZLogger.d("mWeakReference is null");
            }
        }
    }

    public static List<FileTypeInfo> getSupportedFileContents(BinInfo binInfo) {
        FileTypeInfo fileTypeInfo;
        ArrayList arrayList = new ArrayList();
        List<SubFileInfo> list = binInfo != null ? binInfo.supportSubFileInfos : null;
        if (list != null && list.size() > 0) {
            for (SubFileInfo subFileInfo : list) {
                int i = binInfo.icType;
                if (i <= 3) {
                    int i2 = subFileInfo.bitNumber;
                    fileTypeInfo = new FileTypeInfo(i2, BinIndicator.parseBitNumber(i, i2));
                } else {
                    fileTypeInfo = new FileTypeInfo(subFileInfo.bitNumber, BinIndicator.parseSubBinId(i, subFileInfo.binId));
                }
                arrayList.add(fileTypeInfo);
            }
        }
        return arrayList;
    }

    public void B() {
        try {
            synchronized (this.mLock) {
                this.mLock.notifyAll();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            ZLogger.e(e2.toString());
        }
    }

    public void C() {
        this.D = RtkDfu.DEBUG_ENABLE;
        this.qc = BluetoothAdapter.getDefaultAdapter();
        getOtaDeviceInfo().setMode(2);
        this.De = new ArrayList();
        this.rc = BluetoothProfileManager.getInstance();
        if (this.rc == null) {
            BluetoothProfileManager.initial(this.mContext);
            this.rc = BluetoothProfileManager.getInstance();
        }
        BluetoothProfileManager bluetoothProfileManager = this.rc;
        if (bluetoothProfileManager != null) {
            bluetoothProfileManager.addManagerCallback(this.Ge);
        } else {
            ZLogger.w("BluetoothProfileManager not initialized");
        }
        this.He = RtkBluetoothManager.getInstance();
        if (this.He == null) {
            BluetoothProfileManager.initial(this.mContext);
            this.He = RtkBluetoothManager.getInstance();
        }
        RtkBluetoothManager rtkBluetoothManager = this.He;
        if (rtkBluetoothManager != null) {
            rtkBluetoothManager.addManagerCallback(this.wc);
        } else {
            ZLogger.w("BluetoothProfileManager not initialized");
        }
    }

    public void a(DfuException dfuException) {
        if (f(dfuException.getErrCode())) {
            reconnect();
            return;
        }
        disconnect();
        DfuHelperCallback dfuHelperCallback = this.Je;
        if (dfuHelperCallback != null) {
            dfuHelperCallback.onError(dfuException.getErrType(), dfuException.getErrCode());
        } else {
            ZLogger.v("no callback registed");
        }
    }

    public boolean abort() {
        a aVar = this.Ie;
        if (aVar != null) {
            return aVar.b();
        }
        ZLogger.w("dfu has not been initialized");
        C();
        return false;
    }

    public boolean activeImage(boolean z) {
        a aVar = this.Ie;
        if (aVar != null) {
            return aVar.a(z);
        }
        ZLogger.w("dfu has not been initialized");
        C();
        return false;
    }

    public void addDfuHelperCallback(DfuHelperCallback dfuHelperCallback) {
        this.Je = dfuHelperCallback;
    }

    public boolean checkBatteryLevel(int i) {
        String str;
        OtaDeviceInfo otaDeviceInfo = this.Vc;
        if (otaDeviceInfo == null) {
            str = "ignore preverify, please call connectDevice() method to connect and get otaDeviceInfo first.";
        } else {
            if (otaDeviceInfo.isBasSupported()) {
                return this.Vc.getBatteryLevel() >= i;
            }
            str = "ignore preverify, bas not supported";
        }
        ZLogger.w(str);
        return true;
    }

    public boolean checkImage(String str, boolean z, OtaDeviceInfo otaDeviceInfo) {
        return BinFactory.loadImageBinInfo(new LoadParams.Builder().setFilePath(str).setOtaDeviceInfo(otaDeviceInfo).setVersionCheckEnabled(z).build()) != null;
    }

    public boolean checkUpgrade(File file, int i) {
        return checkUpgrade(file, true, i);
    }

    public boolean checkUpgrade(File file, boolean z, int i) {
        if (this.Vc == null) {
            ZLogger.w("please reConnectToDevice() method to connect and get otaDeviceInfo first.");
            return false;
        }
        try {
            return checkImage(file.getPath(), z, this.Vc) && checkBatteryLevel(i);
        } catch (DfuException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void close() {
        disconnect();
        destroy();
    }

    public boolean connectDevice(BluetoothDevice bluetoothDevice, boolean z) {
        this.Be = new ConnectParams.Builder().address(bluetoothDevice.getAddress()).hid(z).reconnectTimes(2).build();
        return connectDevice(this.Be);
    }

    public boolean connectDevice(ConnectParams connectParams) {
        if (this.Ie == null) {
            ZLogger.w(this.D, "dfu has not been initialized");
            initialize();
        }
        if (connectParams == null) {
            ZLogger.w("ConnectParams can not be null");
            return false;
        }
        this.Be = connectParams;
        this.ve = Math.max(connectParams.getReconnectTimes(), 2);
        return true;
    }

    public boolean connectDevice(String str) {
        this.Be = new ConnectParams.Builder().address(str).hid(false).reconnectTimes(2).build();
        return connectDevice(this.Be);
    }

    public boolean connectDevice(String str, boolean z) {
        this.Be = new ConnectParams.Builder().address(str).hid(z).reconnectTimes(2).build();
        return connectDevice(this.Be);
    }

    public boolean connectDevice(String str, boolean z, int i) {
        this.Be = new ConnectParams.Builder().address(str).hid(z).reconnectTimes(i).build();
        return connectDevice(this.Be);
    }

    public void destroy() {
        ZLogger.v("destroy");
        this.ze = null;
        this.Ae = null;
        this.mState = 0;
        Handler handler = this.Ee;
        if (handler != null) {
            handler.removeCallbacks(null);
        }
        this.Je = null;
        a aVar = this.Ie;
        if (aVar != null) {
            aVar.c();
        }
        RtkBluetoothManager rtkBluetoothManager = this.He;
        if (rtkBluetoothManager != null) {
            rtkBluetoothManager.removeManagerCallback(this.wc);
        }
        BluetoothProfileManager bluetoothProfileManager = this.rc;
        if (bluetoothProfileManager != null) {
            bluetoothProfileManager.removeManagerCallback(this.Ge);
        }
    }

    public void disconnect() {
    }

    public void e(int i) {
        ZLogger.v(String.format("notifyStateChanged 0x%04X >> 0x%04X", Integer.valueOf(this.mState), Integer.valueOf(i)));
        this.mState = i;
        DfuHelperCallback dfuHelperCallback = this.Je;
        if (dfuHelperCallback != null) {
            dfuHelperCallback.onStateChanged(this.mState);
        } else {
            ZLogger.v("no callback registed");
        }
    }

    public boolean equals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public boolean f(int i) {
        if (this.ve <= 0) {
            return false;
        }
        return i == 0 || i == 1 || i == 6;
    }

    public int getBondState(String str) {
        BluetoothDevice remoteDevice;
        if (this.qc == null || (remoteDevice = getRemoteDevice(str)) == null) {
            return 10;
        }
        return remoteDevice.getBondState();
    }

    public int getCurrentOtaState() {
        a aVar = this.Ie;
        if (aVar != null) {
            return aVar.d();
        }
        ZLogger.w("dfu has not been initialized");
        C();
        return -1;
    }

    public boolean getDfuProxy() {
        return a.a(this.mContext, this.Ke);
    }

    public OtaDeviceInfo getOtaDeviceInfo() {
        if (this.Vc == null) {
            this.Vc = new OtaDeviceInfo(2);
        }
        return this.Vc;
    }

    public OtaModeInfo getPriorityWorkMode() {
        return getPriorityWorkMode(16);
    }

    public OtaModeInfo getPriorityWorkMode(int i) {
        List<OtaModeInfo> list = this.De;
        if (list == null || list.size() <= 0) {
            return new OtaModeInfo(0);
        }
        for (OtaModeInfo otaModeInfo : this.De) {
            if (otaModeInfo.getWorkmode() == i) {
                return otaModeInfo;
            }
        }
        return this.De.get(0);
    }

    public BluetoothDevice getRemoteDevice(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return this.qc.getRemoteDevice(str);
        } catch (Exception e2) {
            ZLogger.e(e2.toString());
            return null;
        }
    }

    public int getState() {
        return this.mState;
    }

    public List<OtaModeInfo> getSupportedModes() {
        return this.De;
    }

    public boolean initialize() {
        return initialize(this.Je);
    }

    public boolean initialize(DfuHelperCallback dfuHelperCallback) {
        this.Je = dfuHelperCallback;
        if (this.mState == 1) {
            ZLogger.w("STATE_INIT_BINDING_SERVICE ...");
            return false;
        }
        if (this.Ie != null) {
            e(2);
            ZLogger.d("dfu already binded");
            return true;
        }
        e(1);
        boolean dfuProxy = getDfuProxy();
        ZLogger.v("getDfuProxy: " + dfuProxy);
        return dfuProxy;
    }

    public boolean isBluetoothSupported() {
        return this.qc != null;
    }

    public boolean isIdle() {
        return (getCurrentOtaState() & 256) == 256;
    }

    public boolean isInitalized() {
        return this.mState >= 2;
    }

    public boolean isPreparing() {
        int i = this.mState & 256;
        this.mState = i;
        return i == 256;
    }

    public void notifyError(int i) {
        ZLogger.v(String.format("onError: 0x%04X", Integer.valueOf(i)));
        DfuHelperCallback dfuHelperCallback = this.Je;
        if (dfuHelperCallback != null) {
            dfuHelperCallback.onError(65536, i);
        } else {
            ZLogger.v("no callback registed");
        }
    }

    public void processBondStateChanged(int i) {
        this.ye = i;
        if (this.ye == 12) {
            B();
        }
    }

    public void processHidStateChanged(int i) {
    }

    public void reconnect() {
        this.ve--;
    }

    public void removeDfuHelperCallback(DfuHelperCallback dfuHelperCallback) {
        this.Je = null;
    }

    public boolean startOtaProcedure(DfuConfig dfuConfig, boolean z) {
        if (dfuConfig == null) {
            ZLogger.w("dfuConfig cannot be null");
            throw new IllegalArgumentException("dfuConfig cannot be null");
        }
        if (this.Ie == null) {
            ZLogger.w("DfuProxy didn't ready");
            initialize();
            return false;
        }
        if (!z) {
            return true;
        }
        if (this.Vc == null) {
            ZLogger.w("ignore preverify, please call connectDevice() method to connect and get otaDeviceInfo first.");
            return true;
        }
        try {
            if (!checkImage(dfuConfig.getFilePath(), dfuConfig.isVersionCheckEnabled(), this.Vc)) {
                ZLogger.w("checkImage failed");
                notifyError(4097);
                return false;
            }
            if (!dfuConfig.isBatteryCheckEnabled() || checkBatteryLevel(dfuConfig.getLowBatteryThreshold())) {
                return true;
            }
            ZLogger.w("checkBatteryLevel failed");
            notifyError(DfuException.ERROR_BATTERY_LEVEL_LOW);
            return false;
        } catch (DfuException e2) {
            e2.printStackTrace();
            notifyError(e2.getErrorNumber());
            return false;
        }
    }

    public boolean startOtaProcess(DfuConfig dfuConfig) {
        return startOtaProcedure(dfuConfig, true);
    }
}