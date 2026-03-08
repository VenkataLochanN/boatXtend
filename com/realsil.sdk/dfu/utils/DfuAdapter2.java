package com.realsil.sdk.dfu.utils;

import a.a;
import a.b;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.realsil.sdk.core.bluetooth.BluetoothProfileCallback;
import com.realsil.sdk.core.bluetooth.BluetoothProfileManager;
import com.realsil.sdk.core.bluetooth.RtkBluetoothManager;
import com.realsil.sdk.core.bluetooth.RtkBluetoothManagerCallback;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.DfuException;
import com.realsil.sdk.dfu.DfuService;
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
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes3.dex */
public abstract class DfuAdapter2 {
    public static final int ERROR_TYPE_CONNECTION = 0;
    public static final int ERROR_TYPE_OTA = 1;
    public static final int MSG_DFU_PROCESS_STATE_CHANGE = 1;
    public static final int MSG_OTA_CALLBACK_ERROR = 4;
    public static final int MSG_OTA_CALLBACK_PROCESS_CHANGE = 3;
    public static final int MSG_OTA_CONNECTION_ERROR = 6;
    public static final int MSG_OTA_RECONNECT = 7;
    public static final int MSG_OTA_TARGET_INFO_CHANGE = 2;
    public static final int MSG_STATE_CHANGED = 5;
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
    public OtaDeviceInfo Vc;
    public Context mContext;
    public a mService;
    public BluetoothAdapter qc;
    public BluetoothDevice ze;
    public int ye = 10;
    public int mState = 0;
    public List<DfuHelperCallback> Ce = new ArrayList();
    public Object mLock = new Object();
    public final InnerDfuAdapterCallback se = new InnerDfuAdapterCallback() { // from class: com.realsil.sdk.dfu.utils.DfuAdapter2.1
        @Override // com.realsil.sdk.dfu.utils.DfuAdapter2.InnerDfuAdapterCallback, android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            ZLogger.v("onServiceConnected: className=" + componentName.getClassName() + ", packageName=" + componentName.getPackageName());
            DfuAdapter2.this.mService = a.AbstractBinderC0000a.asInterface(iBinder);
            if (DfuAdapter2.this.mService == null) {
                DfuAdapter2.this.e(0);
                ZLogger.d("rebind DfuService...");
                DfuAdapter2.this.z();
                return;
            }
            try {
                if (DfuAdapter2.this.mService.a("DfuAdapter", DfuAdapter2.this.mCallback)) {
                    DfuAdapter2.this.e(2);
                } else {
                    ZLogger.d("registerCallback failed, need to unbind");
                    DfuAdapter2.this.A();
                }
            } catch (RemoteException e2) {
                ZLogger.e(e2.toString());
                DfuAdapter2.this.A();
            }
        }

        @Override // com.realsil.sdk.dfu.utils.DfuAdapter2.InnerDfuAdapterCallback, android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            ZLogger.d("Proxy object disconnected with an extreme situations");
            try {
                if (DfuAdapter2.this.mService != null) {
                    DfuAdapter2.this.mService.b("DfuAdapter", DfuAdapter2.this.mCallback);
                }
            } catch (RemoteException e2) {
                ZLogger.e(e2.toString());
            }
            DfuAdapter2.this.mService = null;
            DfuAdapter2.this.e(0);
            DfuAdapter2.this.z();
        }
    };
    public InnerHandler mHandler = new InnerHandler(new IInnerHandler() { // from class: com.realsil.sdk.dfu.utils.DfuAdapter2.2
        @Override // com.realsil.sdk.dfu.utils.DfuAdapter2.IInnerHandler
        public void handleMessage(Message message) {
            DfuAdapter2.this.handleMessageImpl(message);
        }
    });
    public IInnerHandler zc = new IInnerHandler() { // from class: com.realsil.sdk.dfu.utils.DfuAdapter2.3
        @Override // com.realsil.sdk.dfu.utils.DfuAdapter2.IInnerHandler
        public void handleMessage(Message message) {
            DfuAdapter2.this.handleMessageImpl(message);
        }
    };
    public int ve = 2;
    public Handler Ee = new Handler();
    public Runnable Fe = new Runnable() { // from class: com.realsil.sdk.dfu.utils.DfuAdapter2.4
        @Override // java.lang.Runnable
        public void run() {
            ZLogger.i("retry to connect device, reconnectTimes =" + DfuAdapter2.this.ve);
            DfuAdapter2 dfuAdapter2 = DfuAdapter2.this;
            dfuAdapter2.connectDevice(dfuAdapter2.Ae, false);
        }
    };
    public RtkBluetoothManagerCallback wc = new RtkBluetoothManagerCallback() { // from class: com.realsil.sdk.dfu.utils.DfuAdapter2.5
        @Override // com.realsil.sdk.core.bluetooth.RtkBluetoothManagerCallback
        public void onBluetoothStateChaned(BluetoothDevice bluetoothDevice, int i) {
            super.onBluetoothStateChaned(bluetoothDevice, i);
        }

        @Override // com.realsil.sdk.core.bluetooth.RtkBluetoothManagerCallback
        public void onBondStateChanged(BluetoothDevice bluetoothDevice, int i) {
            super.onBondStateChanged(bluetoothDevice, i);
            String str = DfuAdapter2.this.Ae;
            if (str == null || !str.equals(bluetoothDevice.getAddress())) {
                ZLogger.v("配对设备和当前连接设备不一致");
            } else {
                DfuAdapter2.this.processBondStateChanged(i);
            }
        }
    };
    public BluetoothProfileCallback Ge = new BluetoothProfileCallback() { // from class: com.realsil.sdk.dfu.utils.DfuAdapter2.6
        @Override // com.realsil.sdk.core.bluetooth.BluetoothProfileCallback
        public void onHidStateChanged(BluetoothDevice bluetoothDevice, int i) {
            super.onHidStateChanged(bluetoothDevice, i);
            String str = DfuAdapter2.this.Ae;
            if (str == null || !str.equals(bluetoothDevice.getAddress())) {
                ZLogger.v("设备和当前连接设备不一致");
            }
        }
    };
    public b.a mCallback = new b.a() { // from class: com.realsil.sdk.dfu.utils.DfuAdapter2.7
        @Override // a.b
        public void onError(int i) {
            ZLogger.v(String.format("onError: 0x%04X", Integer.valueOf(i)));
            DfuAdapter2.this.sendMessage(4, Integer.valueOf(i));
        }

        @Override // a.b
        public void onProcessStateChanged(int i) {
            DfuAdapter2.this.sendMessage(1, Integer.valueOf(i));
        }

        @Override // a.b
        public void onProgressChanged(DfuProgressInfo dfuProgressInfo) {
            DfuAdapter2.this.sendMessage(3, dfuProgressInfo);
        }

        @Override // a.b
        public void onTargetInfoChanged(OtaDeviceInfo otaDeviceInfo) {
            DfuAdapter2.this.sendMessage(2, otaDeviceInfo);
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

    private class InnerDfuAdapterCallback implements ServiceConnection {
        public InnerDfuAdapterCallback() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
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
                ZLogger.d("mWeakReference is null");
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

    public final void A() {
        synchronized (this.se) {
            if (this.mService != null) {
                ZLogger.d("doUnbind");
                try {
                    this.mService.b("DfuAdapter", this.mCallback);
                    this.mService = null;
                    this.mContext.unbindService(this.se);
                } catch (Exception e2) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unable to unbind DfuService: ");
                    sb.append(e2.toString());
                    ZLogger.e(sb.toString());
                }
            }
        }
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

    public boolean abort() {
        a aVar = this.mService;
        if (aVar == null) {
            ZLogger.w("dfu has not been initialized");
            initialize();
            return false;
        }
        try {
            return aVar.abort();
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean activeImage(boolean z) {
        a aVar = this.mService;
        if (aVar == null) {
            ZLogger.w("dfu has not been initialized");
            initialize();
            return false;
        }
        try {
            return aVar.activeImage(z);
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void addDfuHelperCallback(DfuHelperCallback dfuHelperCallback) {
        if (dfuHelperCallback == null) {
            return;
        }
        if (this.Ce == null) {
            this.Ce = new CopyOnWriteArrayList();
        }
        if (!this.Ce.contains(dfuHelperCallback)) {
            this.Ce.add(dfuHelperCallback);
        }
        ZLogger.d("mDfuHelperCallbacks.size=" + this.Ce.size());
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
            BinInfo binInfoLoadImageBinInfo = BinFactory.loadImageBinInfo(new LoadParams.Builder().setFilePath(file.getPath()).setOtaDeviceInfo(this.Vc).setVersionCheckEnabled(z).build());
            if (binInfoLoadImageBinInfo != null && binInfoLoadImageBinInfo.supportBinInputStreams != null) {
                return binInfoLoadImageBinInfo.supportBinInputStreams.size() >= 1 && this.Vc.getBatteryLevel() >= i;
            }
            return false;
        } catch (DfuException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void clearDfuHelperCallback() {
        List<DfuHelperCallback> list = this.Ce;
        if (list != null) {
            list.clear();
            this.Ce = null;
        }
        ZLogger.v("mDfuHelperCallbacks cleaned");
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
        if (this.mService == null) {
            ZLogger.w(true, "dfu has not been initialized");
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
        Handler handler = this.Ee;
        if (handler != null) {
            handler.removeCallbacks(null);
        }
        clearDfuHelperCallback();
        A();
        RtkBluetoothManager.getInstance().removeManagerCallback(this.wc);
        BluetoothProfileManager.getInstance().removeManagerCallback(this.Ge);
    }

    public void disconnect() {
    }

    public void e(int i) {
        ZLogger.v(String.format("notifyStateChanged 0x%04X >> 0x%04X", Integer.valueOf(this.mState), Integer.valueOf(i)));
        this.mState = i;
        sendMessage(5, Integer.valueOf(this.mState));
    }

    public boolean equals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public int getBondState(String str) {
        BluetoothDevice remoteDevice;
        if (this.qc == null || (remoteDevice = getRemoteDevice(str)) == null) {
            return 10;
        }
        return remoteDevice.getBondState();
    }

    public int getCurrentOtaState() {
        a aVar = this.mService;
        if (aVar == null) {
            ZLogger.w("dfu has not been initialized");
            initialize();
            return -1;
        }
        try {
            return aVar.getCurrentOtaState();
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return -1;
        }
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

    public void handleMessageImpl(Message message) {
        switch (message.what) {
            case 1:
                int iIntValue = ((Integer) message.obj).intValue();
                List<DfuHelperCallback> list = this.Ce;
                if (list != null) {
                    Iterator<DfuHelperCallback> it = list.iterator();
                    while (it.hasNext()) {
                        it.next().onProcessStateChanged(iIntValue, null);
                    }
                    return;
                }
                break;
            case 2:
                List<DfuHelperCallback> list2 = this.Ce;
                if (list2 != null) {
                    Iterator<DfuHelperCallback> it2 = list2.iterator();
                    while (it2.hasNext()) {
                        it2.next().onTargetInfoChanged((OtaDeviceInfo) message.obj);
                    }
                    return;
                }
                break;
            case 3:
                List<DfuHelperCallback> list3 = this.Ce;
                if (list3 != null) {
                    Iterator<DfuHelperCallback> it3 = list3.iterator();
                    while (it3.hasNext()) {
                        it3.next().onProgressChanged((DfuProgressInfo) message.obj);
                    }
                    return;
                }
                break;
            case 4:
                List<DfuHelperCallback> list4 = this.Ce;
                if (list4 != null) {
                    Iterator<DfuHelperCallback> it4 = list4.iterator();
                    while (it4.hasNext()) {
                        it4.next().onError(1, ((Integer) message.obj).intValue());
                    }
                    return;
                }
                break;
            case 5:
                List<DfuHelperCallback> list5 = this.Ce;
                if (list5 != null) {
                    Iterator<DfuHelperCallback> it5 = list5.iterator();
                    while (it5.hasNext()) {
                        it5.next().onStateChanged(this.mState);
                    }
                    return;
                }
                break;
            case 6:
                disconnect();
                int iIntValue2 = ((Integer) message.obj).intValue();
                List<DfuHelperCallback> list6 = this.Ce;
                if (list6 != null) {
                    Iterator<DfuHelperCallback> it6 = list6.iterator();
                    while (it6.hasNext()) {
                        it6.next().onError(0, iIntValue2);
                    }
                    return;
                }
                break;
            case 7:
                reconnect();
                return;
            default:
                return;
        }
        ZLogger.v("no callback registed");
    }

    public void initialize() {
        if (this.mState == 1) {
            ZLogger.w("STATE_INIT_BINDING_SERVICE ...");
            return;
        }
        this.qc = BluetoothAdapter.getDefaultAdapter();
        getOtaDeviceInfo().setMode(2);
        this.De = new ArrayList();
        this.Ce = new CopyOnWriteArrayList();
        RtkBluetoothManager.getInstance().addManagerCallback(this.wc);
        BluetoothProfileManager.getInstance().addManagerCallback(this.Ge);
        e(1);
        z();
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
        List<DfuHelperCallback> list = this.Ce;
        if (list != null) {
            list.remove(dfuHelperCallback);
            ZLogger.d("mDfuHelperCallbacks.size=" + this.Ce.size());
        }
    }

    public void sendMessage(int i) {
        InnerHandler innerHandler = this.mHandler;
        if (innerHandler != null) {
            innerHandler.sendMessage(innerHandler.obtainMessage(i));
        } else {
            ZLogger.d("mHandler is null");
        }
    }

    public void sendMessage(int i, int i2, int i3, Object obj) {
        InnerHandler innerHandler = this.mHandler;
        if (innerHandler != null) {
            innerHandler.sendMessage(innerHandler.obtainMessage(i, i2, i3, obj));
        } else {
            ZLogger.d("mHandler is null");
        }
    }

    public void sendMessage(int i, Object obj) {
        InnerHandler innerHandler = this.mHandler;
        if (innerHandler != null) {
            innerHandler.sendMessage(innerHandler.obtainMessage(i, obj));
        } else {
            ZLogger.d("mHandler is null");
        }
    }

    public boolean startOtaProcess(DfuConfig dfuConfig) {
        if (this.mService == null) {
            ZLogger.w("DfuProxy didn't ready");
            initialize();
            return false;
        }
        if (dfuConfig != null) {
            return true;
        }
        ZLogger.d("dfuConfig cannot be null");
        throw new IllegalArgumentException("dfuConfig cannot be null");
    }

    public final boolean z() {
        try {
            ZLogger.v("doBind");
            Intent intent = new Intent(this.mContext, (Class<?>) DfuService.class);
            intent.setAction(a.class.getName());
            return this.mContext.bindService(intent, this.se, 1);
        } catch (Exception e2) {
            ZLogger.e("Unable to bind DfuService " + e2.toString());
            return false;
        }
    }
}