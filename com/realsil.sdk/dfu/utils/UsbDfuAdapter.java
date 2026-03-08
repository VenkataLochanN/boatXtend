package com.realsil.sdk.dfu.utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.ido.ble.protocol.model.Sport100Type;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.core.DfuThreadCallback;
import com.realsil.sdk.dfu.model.DfuConfig;
import com.realsil.sdk.dfu.model.DfuProgressInfo;
import com.realsil.sdk.dfu.model.OtaDeviceInfo;
import com.realsil.sdk.dfu.model.Throughput;
import f.b;
import f.e;
import f.f;
import f.g;
import f.h;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class UsbDfuAdapter implements e {
    public static final int ERROR_TYPE_CONNECTION = 0;
    public static final int ERROR_TYPE_OTA = 1;
    public static final int MSG_DFU_PROCESS_STATE_CHANGE = 1;
    public static final int MSG_OTA_CALLBACK_ERROR = 5;
    public static final int MSG_OTA_CALLBACK_PROCESS_CHANGE = 3;
    public static final int MSG_OTA_CALLBACK_USB_PROCESS_CHANGE = 4;
    public static final int MSG_OTA_CONNECTION_ERROR = 7;
    public static final int MSG_OTA_RECONNECT = 8;
    public static final int MSG_OTA_TARGET_INFO_CHANGE = 2;
    public static final int MSG_STATE_CHANGED = 6;
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
    public static volatile UsbDfuAdapter mInstance;
    public String Ae;
    public OtaDeviceInfo Vc;
    public f hf;

    /* JADX INFO: renamed from: if, reason: not valid java name */
    public b f11if;
    public Context mContext;
    public BluetoothAdapter qc;
    public Throughput uc;
    public UsbDevice ze;
    public int mState = 0;
    public List<DfuHelperCallback> Ce = new CopyOnWriteArrayList();
    public Object mLock = new Object();
    public boolean sc = false;
    public int tc = 257;
    public g Fc = new g() { // from class: com.realsil.sdk.dfu.utils.UsbDfuAdapter.1
        @Override // f.g
        public void onDataReceived(byte[] bArr) {
            super.onDataReceived(bArr);
            h hVarA = h.a(bArr);
            if (hVarA != null && hVarA.f5831a == 1) {
                ByteBuffer byteBufferWrap = ByteBuffer.wrap(hVarA.f5832b);
                byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
                if (byteBufferWrap.remaining() >= 4) {
                    ZLogger.d("bulkBuffer=" + byteBufferWrap.getInt(0));
                }
                UsbDfuAdapter.this.e(512);
            }
        }

        @Override // f.g
        public void onStateChanged(int i) {
            super.onStateChanged(i);
            if (i == 0) {
                UsbDfuAdapter.this.s();
                return;
            }
            if (i != 768) {
                return;
            }
            UsbDfuAdapter.this.B();
            UsbDfuAdapter.this.Vc = new OtaDeviceInfo(2);
            UsbDfuAdapter.this.e(264);
            if (UsbDfuAdapter.this.getUsbPort().a(1, (byte[]) null)) {
                return;
            }
            ZLogger.v(UsbDfuAdapter.this.getOtaDeviceInfo().toString());
            UsbDfuAdapter.this.e(512);
        }
    };
    public InnerHandler mHandler = new InnerHandler(new IInnerHandler() { // from class: com.realsil.sdk.dfu.utils.UsbDfuAdapter.2
        @Override // com.realsil.sdk.dfu.utils.UsbDfuAdapter.IInnerHandler
        public void handleMessage(Message message) {
            UsbDfuAdapter.this.handleMessageImpl(message);
        }
    });
    public DfuThreadCallback xc = new DfuThreadCallback() { // from class: com.realsil.sdk.dfu.utils.UsbDfuAdapter.3
        @Override // com.realsil.sdk.dfu.core.DfuThreadCallback
        public void onDeviceInfoChanged(OtaDeviceInfo otaDeviceInfo) {
            super.onDeviceInfoChanged(otaDeviceInfo);
            UsbDfuAdapter.this.sendMessage(2, otaDeviceInfo);
        }

        @Override // com.realsil.sdk.dfu.core.DfuThreadCallback
        public void onError(int i) {
            super.onError(i);
            UsbDfuAdapter.this.sc = false;
            UsbDfuAdapter.this.sendMessage(5, Integer.valueOf(i));
        }

        @Override // com.realsil.sdk.dfu.core.DfuThreadCallback
        public void onProgressChanged(DfuProgressInfo dfuProgressInfo, Throughput throughput) {
            super.onProgressChanged(dfuProgressInfo, throughput);
            UsbDfuAdapter.this.uc = throughput;
            UsbDfuAdapter.this.sendMessage(3, dfuProgressInfo);
        }

        @Override // com.realsil.sdk.dfu.core.DfuThreadCallback
        public void onStateChanged(int i, Throughput throughput) {
            super.onStateChanged(i, throughput);
            UsbDfuAdapter.this.tc = i;
            UsbDfuAdapter.this.uc = throughput;
            UsbDfuAdapter usbDfuAdapter = UsbDfuAdapter.this;
            usbDfuAdapter.sc = (usbDfuAdapter.tc & 512) == 512;
            UsbDfuAdapter.this.sendMessage(1, Integer.valueOf(i));
        }

        @Override // com.realsil.sdk.dfu.core.DfuThreadCallback
        public void onUsbProgressChanged(DfuProgressInfo dfuProgressInfo) {
            super.onUsbProgressChanged(dfuProgressInfo);
            UsbDfuAdapter.this.sendMessage(4, dfuProgressInfo);
        }
    };

    public static abstract class DfuHelperCallback {
        public void onError(int i, int i2) {
        }

        public void onProcessStateChanged(int i) {
        }

        public void onProgressChanged(DfuProgressInfo dfuProgressInfo) {
        }

        public void onStateChanged(int i) {
        }

        public void onTargetInfoChanged(OtaDeviceInfo otaDeviceInfo) {
        }

        public void onUsbProgressChanged(DfuProgressInfo dfuProgressInfo) {
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
                ZLogger.d("mWeakReference is null");
            }
        }
    }

    public UsbDfuAdapter(Context context) {
        this.mContext = context;
        initialize();
    }

    public static UsbDfuAdapter getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DfuHelper.class) {
                if (mInstance == null) {
                    mInstance = new UsbDfuAdapter(context.getApplicationContext());
                }
            }
        }
        return mInstance;
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
        f fVar = this.hf;
        if (fVar == null) {
            return true;
        }
        fVar.u();
        return true;
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

    public void clearDfuHelperCallback() {
        List<DfuHelperCallback> list = this.Ce;
        if (list != null) {
            list.clear();
            this.Ce = null;
        }
        ZLogger.d("no mDfuHelperCallbacks registed");
    }

    public void close() {
        disconnect();
        destroy();
    }

    public boolean connect(String str) {
        return connect(str, Sport100Type.SPORT_TYPE_MOUNTAINEERING_MACHINE, 2);
    }

    public boolean connect(String str, int i, int i2) {
        e(260);
        getUsbPort().a(this.Fc);
        int iA = getUsbPort().a(str, i, i2);
        return iA == 1 || iA == 0;
    }

    public void destroy() {
        ZLogger.v("destroy");
        this.ze = null;
        this.Ae = null;
        clearDfuHelperCallback();
        abort();
        mInstance = null;
    }

    public void disconnect() {
        getUsbPort().a();
        e(1025);
    }

    public void e(int i) {
        ZLogger.v(String.format("notifyStateChanged 0x%04X >> 0x%04X", Integer.valueOf(this.mState), Integer.valueOf(i)));
        this.mState = i;
        sendMessage(6, Integer.valueOf(this.mState));
    }

    public boolean equals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public int getCurrentOtaState() {
        return this.tc;
    }

    public OtaDeviceInfo getOtaDeviceInfo() {
        if (this.Vc == null) {
            this.Vc = new OtaDeviceInfo(2);
        }
        return this.Vc;
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

    public b getUsbPort() {
        if (this.f11if == null) {
            this.f11if = b.a(this.mContext);
            this.f11if.a(this.Fc);
        }
        return this.f11if;
    }

    public void handleMessageImpl(Message message) {
        switch (message.what) {
            case 1:
                int iIntValue = ((Integer) message.obj).intValue();
                List<DfuHelperCallback> list = this.Ce;
                if (list != null) {
                    Iterator<DfuHelperCallback> it = list.iterator();
                    while (it.hasNext()) {
                        it.next().onProcessStateChanged(iIntValue);
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
                        it4.next().onUsbProgressChanged((DfuProgressInfo) message.obj);
                    }
                    return;
                }
                break;
            case 5:
                List<DfuHelperCallback> list5 = this.Ce;
                if (list5 != null) {
                    Iterator<DfuHelperCallback> it5 = list5.iterator();
                    while (it5.hasNext()) {
                        it5.next().onError(1, ((Integer) message.obj).intValue());
                    }
                    return;
                }
                break;
            case 6:
                List<DfuHelperCallback> list6 = this.Ce;
                if (list6 != null) {
                    Iterator<DfuHelperCallback> it6 = list6.iterator();
                    while (it6.hasNext()) {
                        it6.next().onStateChanged(this.mState);
                    }
                    return;
                }
                break;
            case 7:
                disconnect();
                int iIntValue2 = ((Integer) message.obj).intValue();
                List<DfuHelperCallback> list7 = this.Ce;
                if (list7 != null) {
                    Iterator<DfuHelperCallback> it7 = list7.iterator();
                    while (it7.hasNext()) {
                        it7.next().onError(0, iIntValue2);
                    }
                    return;
                }
                break;
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
        e(1);
        this.qc = BluetoothAdapter.getDefaultAdapter();
        getOtaDeviceInfo().setMode(2);
        this.Ce = new CopyOnWriteArrayList();
        e(2);
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

    public void removeDfuHelperCallback(DfuHelperCallback dfuHelperCallback) {
        List<DfuHelperCallback> list = this.Ce;
        if (list != null) {
            list.remove(dfuHelperCallback);
            ZLogger.d("mDfuHelperCallbacks.size=" + this.Ce.size());
        }
    }

    public final void s() {
        boolean zIsPreparing = isPreparing();
        e(1025);
        if (zIsPreparing) {
            sendMessage(7, 0);
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
        if (dfuConfig == null) {
            ZLogger.d("dfuConfig cannot be null");
            throw new IllegalArgumentException("dfuConfig cannot be null");
        }
        e(513);
        getUsbPort().a((g) null);
        this.hf = new f(this.mContext, this.xc, dfuConfig);
        this.hf.start();
        return true;
    }
}