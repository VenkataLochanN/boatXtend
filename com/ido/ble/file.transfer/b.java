package com.ido.ble.file.transfer;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ido.ble.callback.DeviceGattCallBack;
import com.ido.ble.callback.DeviceUpgradeEventListener;
import com.ido.ble.common.k;
import com.ido.ble.file.transfer.c;
import com.ido.ble.gps.callback.GpsCallBack;
import com.ido.ble.gps.model.ConnParam;
import com.ido.ble.gps.model.ConnParamReply;
import com.ido.ble.gps.model.ControlGpsReply;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.u;
import com.ido.ble.protocol.model.Mp3ToMp3Para;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.veryfit.multi.nativeprotocol.Protocol;
import java.io.File;
import kotlin.UByte;

/* JADX INFO: loaded from: classes2.dex */
public class b {
    private static b A = new b();
    private static final String v = "FILE_TRANSFER";
    private static final int w = 1000;
    private static final int x = 10;
    private static final int y = 3;
    private static final int z = 3;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f4477g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f4478h;
    private com.ido.ble.file.transfer.d j;
    private IFileTransferListener k;
    private i n;
    private FileTransferConfig o;
    private String p;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f4471a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f4472b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f4473c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f4474d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f4475e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f4476f = 0;
    private boolean i = false;
    private boolean l = false;
    private Handler m = new Handler(Looper.getMainLooper());
    private String q = "";
    private GpsCallBack.IMp3ConvertCallBack r = new a();
    private DeviceGattCallBack.ICallBack s = new C0081b();
    private GpsCallBack.ITranAgpsFileCallBack t = new c();
    private GpsCallBack.IDeviceReplySetGpsCallBack u = new d();

    class a implements GpsCallBack.IMp3ConvertCallBack {
        a() {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IMp3ConvertCallBack
        public void onConvertFailed() {
            b.this.f();
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IMp3ConvertCallBack
        public void onConvertSuccess() {
            if (!new File(b.this.p).exists()) {
                LogTool.d("FILE_TRANSFER", "targetFile is not exist");
                b.this.f();
                return;
            }
            b.this.o.filePath = b.this.p;
            LogTool.d("FILE_TRANSFER", "onConvertSuccess final mFileTransferConfig = " + b.this.o);
            b bVar = b.this;
            bVar.b(bVar.o);
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IMp3ConvertCallBack
        public void onNoNeedConvert() {
            LogTool.d("FILE_TRANSFER", "onNoNeedConvert final mFileTransferConfig = " + b.this.o);
            b bVar = b.this;
            bVar.b(bVar.o);
        }
    }

    /* JADX INFO: renamed from: com.ido.ble.file.transfer.b$b, reason: collision with other inner class name */
    class C0081b implements DeviceGattCallBack.ICallBack {
        C0081b() {
        }

        @Override // com.ido.ble.callback.DeviceGattCallBack.ICallBack
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            if (bluetoothGattCharacteristic != null && bluetoothGattCharacteristic.getValue() != null && bluetoothGattCharacteristic.getValue().length > 0 && (bluetoothGattCharacteristic.getValue()[0] & UByte.MAX_VALUE) == 209) {
                b.this.j.a();
            }
        }

        @Override // com.ido.ble.callback.DeviceGattCallBack.ICallBack
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        }
    }

    class c implements GpsCallBack.ITranAgpsFileCallBack {
        c() {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ITranAgpsFileCallBack
        public void onFailed(int i) {
            if (b.this.l) {
                DeviceUpgradeEventListener.APOLLO_onSOLibError(i);
                b.this.j.a();
                b.this.q = "transfer progress return code = " + i;
                b bVar = b.this;
                bVar.b(bVar.q);
            }
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ITranAgpsFileCallBack
        public void onFailed(int i, Object obj) {
            if (b.this.l) {
                DeviceUpgradeEventListener.APOLLO_onSOLibError(i);
                b.this.j.a();
                b.this.q = "transfer progress return code = " + i + ",value = " + obj;
                b bVar = b.this;
                bVar.c(bVar.q);
            }
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ITranAgpsFileCallBack
        public void onFinish() {
            if (b.this.l) {
                b.this.j.a();
                b.this.i();
            }
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ITranAgpsFileCallBack
        public void onProgress(int i) {
            if (b.this.l) {
                b.this.j.a();
                b.this.a(i);
            }
        }
    }

    class d implements GpsCallBack.IDeviceReplySetGpsCallBack {

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.n();
            }
        }

        d() {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IDeviceReplySetGpsCallBack
        public void onControlGps(ControlGpsReply controlGpsReply) {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IDeviceReplySetGpsCallBack
        public void onSetConfigGps(boolean z) {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IDeviceReplySetGpsCallBack
        public void onSetConnParam(ConnParamReply connParamReply) {
            if (b.this.l) {
                if (!b.this.i) {
                    LogTool.d("FILE_TRANSFER", "handleSetConnParamReply");
                    b.this.a(connParamReply);
                } else if (connParamReply.currMode == 2) {
                    LogTool.d("FILE_TRANSFER", "currMode == 2 , set slow success. callback failed for end");
                    b.this.q();
                } else {
                    LogTool.d("FILE_TRANSFER", "currMode != 2 , setTransferSpeedToSlowForEnd");
                    b.this.m.postDelayed(new a(), 1000L);
                }
            }
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IDeviceReplySetGpsCallBack
        public void onSetHotStartGpsPara(boolean z) {
        }
    }

    class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.c();
        }
    }

    class f implements Runnable {
        f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.d();
        }
    }

    class g implements c.b {
        g() {
        }

        @Override // com.ido.ble.file.transfer.c.b
        public void onTimeOut() {
            if (b.this.n == i.SET_FAST_SPEED) {
                b.this.h();
                b.this.l();
                return;
            }
            if (b.this.n == i.CHECK_FAST_SPEED_STATE) {
                b.this.h();
                b.this.c();
                return;
            }
            if (b.this.n == i.TRANSFER_FILE) {
                b.this.h();
                b.this.b("trans file time out.");
            } else if (b.this.n == i.SET_SLOW_SPEED) {
                b.this.h();
                b.this.m();
            } else if (b.this.n == i.CHECK_SLOW_SPEED_STATE) {
                b.this.h();
                b.this.d();
            }
        }
    }

    class h implements Runnable {
        h() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (b.this.k == null || b.this.f4477g) {
                return;
            }
            LogTool.d("FILE_TRANSFER", "notify success!");
            b.this.k.onProgress(100);
            b.this.k.onSuccess();
            b.this.k = null;
        }
    }

    private enum i {
        STATE_NULL,
        CHECK_FILE,
        SET_FAST_SPEED,
        CHECK_FAST_SPEED_STATE,
        TRANSFER_FILE,
        SET_SLOW_SPEED,
        CHECK_SLOW_SPEED_STATE
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2) {
        LogTool.d("FILE_TRANSFER", "progress = " + i2);
        b(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ConnParamReply connParamReply) {
        Handler handler;
        Runnable fVar;
        String str;
        String str2;
        this.j.a();
        if (connParamReply != null) {
            LogTool.d("FILE_TRANSFER", connParamReply.toString());
        }
        i iVar = this.n;
        if (iVar == i.SET_FAST_SPEED) {
            if (connParamReply == null) {
                str2 = "set fast transfer mode failed, return info is null, try to set again";
            } else {
                if (connParamReply.errorCode == 0) {
                    c();
                    return;
                }
                str2 = "set fast transfer mode return invalid code = " + connParamReply.errorCode + ",try to set again";
            }
            LogTool.b("FILE_TRANSFER", str2);
            l();
            return;
        }
        if (iVar == i.CHECK_FAST_SPEED_STATE) {
            if (connParamReply != null && connParamReply.currMode == 1) {
                LogTool.d("FILE_TRANSFER", "set fast transfer mode ok.");
                p();
                return;
            } else {
                handler = this.m;
                fVar = new e();
            }
        } else {
            if (iVar == i.SET_SLOW_SPEED) {
                if (connParamReply == null) {
                    str = "set slow transfer mode failed, return info is null, try to set again";
                } else {
                    if (connParamReply.errorCode == 0) {
                        d();
                        return;
                    }
                    str = "set slow transfer mode return invalid code = " + connParamReply.errorCode + ",try to set again";
                }
                LogTool.b("FILE_TRANSFER", str);
                m();
                return;
            }
            if (iVar != i.CHECK_SLOW_SPEED_STATE) {
                return;
            }
            if (connParamReply != null && connParamReply.currMode == 2) {
                LogTool.d("FILE_TRANSFER", "set slow transfer mode ok.");
                r();
                return;
            } else {
                handler = this.m;
                fVar = new f();
            }
        }
        handler.postDelayed(fVar, 1000L);
    }

    private void b(int i2) {
        IFileTransferListener iFileTransferListener = this.k;
        if (iFileTransferListener != null) {
            iFileTransferListener.onProgress(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        int i2 = this.f4476f;
        if (i2 >= this.o.maxRetryTimes) {
            LogTool.d("FILE_TRANSFER", "try to trans again. out of max times.");
            c(str);
            return;
        }
        this.f4476f = i2 + 1;
        LogTool.b("FILE_TRANSFER", str);
        LogTool.d("FILE_TRANSFER", "try to trans again. times = " + this.f4476f);
        if (!this.f4478h) {
            u.z();
        }
        p();
    }

    private boolean b() {
        LogTool.d("FILE_TRANSFER", "check file.");
        this.n = i.CHECK_FILE;
        return new File(this.o.filePath).exists();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        int i2 = this.f4471a;
        if (i2 > 10) {
            LogTool.b("FILE_TRANSFER", "check fast speed mode times out of max times!");
            LogTool.b("FILE_TRANSFER", "force transfer file...");
            p();
        } else {
            this.f4471a = i2 + 1;
            LogTool.d("FILE_TRANSFER", "check fast speed state.");
            this.n = i.CHECK_FAST_SPEED_STATE;
            ConnParam connParam = new ConnParam();
            connParam.mode = 0;
            com.ido.ble.h.a.a(connParam);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        LogTool.b("FILE_TRANSFER", str);
        this.q = str;
        this.i = true;
        this.f4475e = 0;
        e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (!com.ido.ble.bluetooth.a.h()) {
            LogTool.b("FILE_TRANSFER", "check slow transfer mode failed. ble is disconnect");
            r();
            return;
        }
        int i2 = this.f4473c;
        if (i2 > 3) {
            LogTool.b("FILE_TRANSFER", "check slow transfer mode out of max times.");
            r();
            return;
        }
        this.f4473c = i2 + 1;
        LogTool.d("FILE_TRANSFER", "check slow transfer mode.");
        this.n = i.CHECK_SLOW_SPEED_STATE;
        ConnParam connParam = new ConnParam();
        connParam.mode = 0;
        com.ido.ble.h.a.a(connParam);
    }

    private void e() {
        int i2 = this.f4472b;
        if (i2 > 3) {
            LogTool.b("FILE_TRANSFER", "check speed mode times for end out of max times!");
            LogTool.b("FILE_TRANSFER", "force transfer failed for end.");
            q();
        } else {
            this.f4472b = i2 + 1;
            LogTool.d("FILE_TRANSFER", "check speed mode for end.");
            ConnParam connParam = new ConnParam();
            connParam.mode = 0;
            com.ido.ble.h.a.a(connParam);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        IFileTransferListener iFileTransferListener = this.k;
        if (iFileTransferListener != null) {
            iFileTransferListener.onFailed("mp3 file onConvertFailed");
        } else {
            LogTool.d("FILE_TRANSFER", "transferListener = null,onConvertFailed");
        }
    }

    public static b g() {
        return A;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        this.j = new com.ido.ble.file.transfer.c();
        this.j.a(new g());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        LogTool.d("FILE_TRANSFER", "transfer file complete.");
        if (this.o.isNeedChangeSpeedMode) {
            m();
        } else {
            r();
        }
    }

    private void j() {
        com.ido.ble.callback.b.K().a(this.s);
    }

    private void k() {
        LogTool.d("FILE_TRANSFER", "release.");
        com.ido.ble.gps.callback.a.h().b(this.t);
        com.ido.ble.gps.callback.a.h().b(this.u);
        com.ido.ble.gps.callback.a.h().b(this.r);
        com.ido.ble.callback.b.K().b(this.s);
        this.n = i.STATE_NULL;
        this.m.removeCallbacksAndMessages(null);
        this.l = false;
        this.f4478h = false;
        this.i = false;
        this.f4473c = 0;
        this.f4471a = 0;
        this.f4474d = 0;
        this.f4475e = 0;
        this.f4476f = 0;
        this.f4472b = 0;
        this.q = "";
        this.j.b();
        this.j = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        int i2 = this.f4474d;
        if (i2 > 3) {
            LogTool.b("FILE_TRANSFER", "set fast speed mode times out of max times!");
            LogTool.b("FILE_TRANSFER", "force transfer file...");
            p();
        } else {
            this.f4474d = i2 + 1;
            LogTool.d("FILE_TRANSFER", "set fast transfer mode.");
            this.n = i.SET_FAST_SPEED;
            ConnParam connParam = new ConnParam();
            connParam.mode = 1;
            com.ido.ble.h.a.a(connParam);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (!com.ido.ble.bluetooth.a.h()) {
            LogTool.b("FILE_TRANSFER", "set slow transfer mode failed. ble is disconnect");
            r();
            return;
        }
        int i2 = this.f4475e;
        if (i2 > 3) {
            LogTool.b("FILE_TRANSFER", "set slow transfer mode out of max times.");
            r();
            return;
        }
        this.f4475e = i2 + 1;
        LogTool.d("FILE_TRANSFER", "set slow transfer mode.");
        this.n = i.SET_SLOW_SPEED;
        ConnParam connParam = new ConnParam();
        connParam.mode = 2;
        com.ido.ble.h.a.a(connParam);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        if (!com.ido.ble.bluetooth.a.h()) {
            LogTool.b("FILE_TRANSFER", "set slow transfer mode failed for end. ble is disconnect");
            q();
            return;
        }
        int i2 = this.f4475e;
        if (i2 > 3) {
            LogTool.b("FILE_TRANSFER", "set slow transfer mode out of max times for end.");
            q();
            return;
        }
        this.f4475e = i2 + 1;
        LogTool.d("FILE_TRANSFER", "set slow transfer mode for end.");
        ConnParam connParam = new ConnParam();
        connParam.mode = 2;
        com.ido.ble.h.a.a(connParam);
    }

    private void o() {
        this.f4477g = true;
        if (!this.l) {
            this.m.removeCallbacksAndMessages(null);
            LogTool.d("FILE_TRANSFER", "stop1.");
        } else {
            LogTool.d("FILE_TRANSFER", "stop.");
            if (!this.f4478h) {
                u.z();
            }
            k();
        }
    }

    private void p() {
        Protocol protocol;
        int i2;
        LogTool.d("FILE_TRANSFER", "begin transfer file...");
        this.n = i.TRANSFER_FILE;
        if (this.o.PRN <= 0) {
            protocol = Protocol.getInstance();
            i2 = 10;
        } else {
            protocol = Protocol.getInstance();
            i2 = this.o.PRN;
        }
        protocol.tranDataSetPRN(i2);
        byte[] bArrA = com.ido.ble.common.c.a(this.o.filePath);
        if (bArrA == null || bArrA.length <= 0) {
            c("byte data is null");
        } else {
            FileTransferConfig fileTransferConfig = this.o;
            int i3 = fileTransferConfig.dataType;
            byte[] bytes = fileTransferConfig.firmwareSpecName.getBytes();
            FileTransferConfig fileTransferConfig2 = this.o;
            int iA = u.a(bArrA, i3, bytes, fileTransferConfig2.zipType, fileTransferConfig2.oriSize);
            if (iA != 0) {
                DeviceUpgradeEventListener.APOLLO_onSOLibError(iA);
                b("tranDataSetBuff return code is " + iA);
            }
        }
        LogTool.d("FILE_TRANSFER", "tranDataStart return code = " + Protocol.getInstance().tranDataStart());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        LogTool.d("FILE_TRANSFER", "transfer failed!");
        if (!this.f4478h) {
            u.z();
        }
        IFileTransferListener iFileTransferListener = this.k;
        if (iFileTransferListener != null) {
            iFileTransferListener.onFailed(this.q);
            this.k = null;
        }
        k();
    }

    private void r() {
        LogTool.d("FILE_TRANSFER", "transfer success!");
        if (!this.f4478h) {
            u.z();
        }
        k();
        this.m.postDelayed(new h(), 1000L);
    }

    public void a() {
        this.f4477g = true;
        if (!this.l) {
            this.m.removeCallbacksAndMessages(null);
            LogTool.d("FILE_TRANSFER", "stopByUser1.");
        } else {
            LogTool.d("FILE_TRANSFER", "stopByUser.");
            Protocol.getInstance().tranDataManualStop();
            k();
        }
    }

    public void a(FileTransferConfig fileTransferConfig) {
        String str;
        this.o = fileTransferConfig;
        this.k = this.o.iFileTransferListener;
        if (TextUtils.isEmpty(fileTransferConfig.firmwareSpecName)) {
            str = "firmwareSpecName is null";
        } else {
            String[] strArrSplit = fileTransferConfig.firmwareSpecName.split("\\.");
            if (strArrSplit.length != 1) {
                if (!TextUtils.equals(strArrSplit[strArrSplit.length - 1], "mp3")) {
                    b(fileTransferConfig);
                    return;
                } else {
                    com.ido.ble.gps.callback.a.h().a(this.r);
                    a(fileTransferConfig.filePath);
                    return;
                }
            }
            str = "firmwareSpecName format is wrong";
        }
        LogTool.d("FILE_TRANSFER", str);
    }

    public void a(String str) {
        String str2;
        File file = new File(str);
        if (file.exists()) {
            this.p = str.replace(file.getName(), "") + "tempMp3File.mp3";
            Mp3ToMp3Para mp3ToMp3Para = new Mp3ToMp3Para();
            mp3ToMp3Para.mp3in = str;
            mp3ToMp3Para.mp3out = this.p;
            mp3ToMp3Para.size = (int) file.length();
            LogTool.d("FILE_TRANSFER", "[mp3ToMp3] " + mp3ToMp3Para.toString());
            str2 = "[mp3ToMp3] " + u.b(com.ido.ble.common.c.b(k.a(mp3ToMp3Para)), com.veryfit.multi.nativeprotocol.b.b4);
        } else {
            str2 = "[mp3ToMp3] file not exists:" + str;
        }
        LogTool.d("FILE_TRANSFER", str2);
    }

    public void b(FileTransferConfig fileTransferConfig) {
        LogTool.d("FILE_TRANSFER", "start ... " + fileTransferConfig.toString());
        if (this.l) {
            LogTool.d("FILE_TRANSFER", "is in staring state, ignore ...");
            return;
        }
        SupportFunctionInfo supportFunctionInfoV = com.ido.ble.f.a.f.a.c0().V();
        if (supportFunctionInfoV != null) {
            this.f4478h = supportFunctionInfoV.V3_support_data_tran_continue;
            LogTool.d("FILE_TRANSFER", "isSupportBreakPoint = " + this.f4478h);
        }
        j();
        h();
        this.o = fileTransferConfig;
        this.k = this.o.iFileTransferListener;
        this.k.onStart();
        if (!b()) {
            this.q = "file is not exist.";
            LogTool.d("FILE_TRANSFER", this.q);
            q();
        } else {
            this.l = true;
            this.f4477g = false;
            com.ido.ble.gps.callback.a.h().a(this.t);
            com.ido.ble.gps.callback.a.h().a(this.u);
            l();
        }
    }
}