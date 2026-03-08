package com.ido.ble.watch.custom;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Handler;
import android.os.Looper;
import com.ido.ble.bluetooth.setting.BluetoothGattSettingListener;
import com.ido.ble.gps.agps.AgpsFileTransConfig;
import com.ido.ble.gps.callback.GpsCallBack;
import com.ido.ble.gps.model.ConnParam;
import com.ido.ble.gps.model.ConnParamReply;
import com.ido.ble.gps.model.ControlGpsReply;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.u;
import com.veryfit.multi.nativeprotocol.Protocol;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
class e {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final String f4670h = "WATCH_PLATE_FILE_TRANSLATE";
    private static final int i = 1000;
    private static e j = new e();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private f f4674d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private C0089e f4675e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f4671a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f4672b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Handler f4673c = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private GpsCallBack.ITranAgpsFileCallBack f4676f = new a();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private GpsCallBack.IDeviceReplySetGpsCallBack f4677g = new b();

    class a implements GpsCallBack.ITranAgpsFileCallBack {
        a() {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ITranAgpsFileCallBack
        public void onFailed(int i) {
            e.this.a("translate progress return code = " + i);
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ITranAgpsFileCallBack
        public void onFailed(int i, Object obj) {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ITranAgpsFileCallBack
        public void onFinish() {
            e.this.e();
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ITranAgpsFileCallBack
        public void onProgress(int i) {
            e.this.a(i);
        }
    }

    class b implements GpsCallBack.IDeviceReplySetGpsCallBack {
        b() {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IDeviceReplySetGpsCallBack
        public void onControlGps(ControlGpsReply controlGpsReply) {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IDeviceReplySetGpsCallBack
        public void onSetConfigGps(boolean z) {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IDeviceReplySetGpsCallBack
        public void onSetConnParam(ConnParamReply connParamReply) {
            e.this.a(connParamReply);
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IDeviceReplySetGpsCallBack
        public void onSetHotStartGpsPara(boolean z) {
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            e.b(e.this);
            e.this.c();
        }
    }

    class d implements BluetoothGattSettingListener.IListener {
        d() {
        }

        @Override // com.ido.ble.bluetooth.setting.BluetoothGattSettingListener.IListener
        public BluetoothGattCharacteristic addParaToCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            bluetoothGattCharacteristic.setWriteType(1);
            return bluetoothGattCharacteristic;
        }
    }

    /* JADX INFO: renamed from: com.ido.ble.watch.custom.e$e, reason: collision with other inner class name */
    static class C0089e extends AgpsFileTransConfig {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f4682a;

        C0089e() {
        }

        @Override // com.ido.ble.gps.agps.AgpsFileTransConfig
        public String toString() {
            return "TransWatchPlateFileConfig{uniqueID='" + this.f4682a + "', filePath='" + this.filePath + "', listener=" + this.listener + ", PRN=" + this.PRN + '}';
        }
    }

    private enum f {
        STATE_NULL,
        CHECK_FILE,
        SET_FAST_SPEED,
        CHECK_FAST_SPEED_STATE,
        TRANSLATE_FILE
    }

    e() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2) {
        b(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ConnParamReply connParamReply) {
        if (connParamReply != null) {
            LogTool.d(f4670h, connParamReply.toString());
        }
        f fVar = this.f4674d;
        if (fVar != f.SET_FAST_SPEED) {
            if (fVar != f.CHECK_FAST_SPEED_STATE) {
                return;
            }
            if (connParamReply == null || connParamReply.currMode != 1) {
                if (this.f4671a < 5) {
                    this.f4673c.postDelayed(new c(), 1000L);
                    return;
                } else {
                    LogTool.b(f4670h, "set fast translate mode failed.");
                    a("set fast speed mode failed");
                    return;
                }
            }
            LogTool.d(f4670h, "set fast translate mode ok.");
        }
        h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        LogTool.b(f4670h, str);
        this.f4675e.listener.onFailed(str);
        f();
    }

    static /* synthetic */ int b(e eVar) {
        int i2 = eVar.f4671a;
        eVar.f4671a = i2 + 1;
        return i2;
    }

    private void b(int i2) {
        this.f4675e.listener.onProgress(i2);
    }

    private boolean b() {
        LogTool.d(f4670h, "check watch plate file.");
        this.f4674d = f.CHECK_FILE;
        return new File(this.f4675e.filePath).exists();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogTool.d(f4670h, "check fast speed state.");
        this.f4674d = f.CHECK_FAST_SPEED_STATE;
        ConnParam connParam = new ConnParam();
        connParam.mode = 0;
        com.ido.ble.h.a.a(connParam);
    }

    public static e d() {
        return j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LogTool.d(f4670h, "translate watch plate file complete.");
        i();
    }

    private void f() {
        LogTool.d(f4670h, "release.");
        BluetoothGattSettingListener.setBluetoothGattSettingListener(null);
        com.ido.ble.gps.callback.a.h().b(this.f4676f);
        com.ido.ble.gps.callback.a.h().b(this.f4677g);
        this.f4675e = null;
        this.f4674d = f.STATE_NULL;
        this.f4673c.removeCallbacksAndMessages(null);
        this.f4672b = false;
        this.f4671a = 0;
    }

    private void g() {
        LogTool.d(f4670h, "set fast translate mode.");
        this.f4674d = f.SET_FAST_SPEED;
        ConnParam connParam = new ConnParam();
        connParam.mode = 1;
        com.ido.ble.h.a.a(connParam);
    }

    private void h() {
        Protocol protocol;
        int i2;
        String str;
        LogTool.d(f4670h, "begin translate watch plate file...");
        this.f4674d = f.TRANSLATE_FILE;
        BluetoothGattSettingListener.setBluetoothGattSettingListener(new d());
        if (this.f4675e.PRN <= 0) {
            protocol = Protocol.getInstance();
            i2 = 1;
        } else {
            protocol = Protocol.getInstance();
            i2 = this.f4675e.PRN;
        }
        protocol.tranDataSetPRN(i2);
        byte[] bArrA = com.ido.ble.common.c.a(this.f4675e.filePath);
        if (bArrA != null && bArrA.length > 0) {
            int iA = u.a(bArrA, this.f4675e.f4682a, 0);
            if (iA != 0) {
                str = "setWatchPlateFileTranParas return code is " + iA;
            }
            Protocol.getInstance().tranDataStart();
        }
        str = "watchPlateData byte data is null";
        a(str);
        Protocol.getInstance().tranDataStart();
    }

    private void i() {
        LogTool.d(f4670h, "translate success!");
        this.f4675e.listener.onSuccess();
        f();
    }

    public void a() {
        if (this.f4672b) {
            LogTool.d(f4670h, "stop.");
            Protocol.getInstance().tranDataStop();
            f();
        }
    }

    public void a(C0089e c0089e) {
        if (this.f4672b) {
            LogTool.b(f4670h, "is in staring state, try to stop and start");
            a();
        }
        LogTool.d(f4670h, "start ...");
        LogTool.d(f4670h, "config is  " + c0089e.toString());
        this.f4675e = c0089e;
        c0089e.listener.onStart();
        if (!b()) {
            a("watch plate is not exist.");
            return;
        }
        this.f4672b = true;
        com.ido.ble.gps.callback.a.h().a(this.f4676f);
        com.ido.ble.gps.callback.a.h().a(this.f4677g);
        g();
    }
}