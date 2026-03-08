package com.ido.ble.file.transfer.spp;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ido.ble.bluetooth.bt.ISPPConnectStateListener;
import com.ido.ble.callback.DeviceResponseCommonCallBack;
import com.ido.ble.common.k;
import com.ido.ble.file.transfer.IFileTransferListener;
import com.ido.ble.file.transfer.c;
import com.ido.ble.gps.callback.GpsCallBack;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.u;
import com.ido.ble.protocol.model.Mp3ToMp3Para;
import com.veryfit.multi.nativeprotocol.Protocol;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class a {
    private static final String p = "FILE_TRANSFER_SPP";
    private static a q = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f4502b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private com.ido.ble.file.transfer.d f4503c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private IFileTransferListener f4504d;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private SPPFileTransferConfig f4507g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private ISPPConnectStateListener f4508h;
    private g i;
    private String j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f4501a = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f4505e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Handler f4506f = new Handler(Looper.getMainLooper());
    private GpsCallBack.IMp3ConvertCallBack k = new C0082a();
    private final GpsCallBack.ISPPTranFileCallBack l = new b();
    private final ISPPConnectStateListener m = new c();
    private DeviceResponseCommonCallBack.ICallBack n = new d();
    boolean o = false;

    /* JADX INFO: renamed from: com.ido.ble.file.transfer.spp.a$a, reason: collision with other inner class name */
    class C0082a implements GpsCallBack.IMp3ConvertCallBack {
        C0082a() {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IMp3ConvertCallBack
        public void onConvertFailed() {
            a.this.c();
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IMp3ConvertCallBack
        public void onConvertSuccess() {
            if (!new File(a.this.j).exists()) {
                LogTool.d("FILE_TRANSFER_SPP", "targetFile is not exist");
                a.this.c();
                return;
            }
            a.this.f4507g.filePath = a.this.j;
            LogTool.d("FILE_TRANSFER_SPP", "onConvertSuccess final mFileTransferConfig = " + a.this.f4507g);
            a aVar = a.this;
            aVar.b(aVar.f4507g);
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IMp3ConvertCallBack
        public void onNoNeedConvert() {
            LogTool.d("FILE_TRANSFER_SPP", "onNoNeedConvert final mFileTransferConfig = " + a.this.f4507g);
            a aVar = a.this;
            aVar.b(aVar.f4507g);
        }
    }

    class b implements GpsCallBack.ISPPTranFileCallBack {
        b() {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ISPPTranFileCallBack
        public void onFailed(int i) {
            if (a.this.f4505e) {
                if (a.this.f4503c != null) {
                    a.this.f4503c.a();
                }
                a.this.b("transfer progress return code = " + i);
            }
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ISPPTranFileCallBack
        public void onFinish() {
            if (a.this.f4505e) {
                if (a.this.f4503c != null) {
                    a.this.f4503c.a();
                }
                a.this.g();
            }
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ISPPTranFileCallBack
        public void onProgress(int i) {
            if (a.this.f4505e) {
                if (a.this.f4503c != null) {
                    a.this.f4503c.a();
                }
                a.this.a(i);
            }
        }
    }

    class c implements ISPPConnectStateListener {
        c() {
        }

        @Override // com.ido.ble.bluetooth.bt.ISPPConnectStateListener
        public void onBreak() {
            LogTool.b("FILE_TRANSFER_SPP", "spp connect break.");
            if (a.this.f4508h != null) {
                a.this.f4508h.onBreak();
            }
            if (a.this.f4505e) {
                a.this.c("spp connect break.");
            }
        }

        @Override // com.ido.ble.bluetooth.bt.ISPPConnectStateListener
        public void onFailed() {
            a.this.c("spp connect failed.");
            if (a.this.f4508h != null) {
                a.this.f4508h.onFailed();
            }
        }

        @Override // com.ido.ble.bluetooth.bt.ISPPConnectStateListener
        public void onStart() {
            LogTool.d("FILE_TRANSFER_SPP", "spp connect start");
            if (a.this.f4508h != null) {
                a.this.f4508h.onStart();
            }
        }

        @Override // com.ido.ble.bluetooth.bt.ISPPConnectStateListener
        public void onSuccess() {
            LogTool.d("FILE_TRANSFER_SPP", "spp connect success");
            if (a.this.f4508h != null) {
                a.this.f4508h.onStart();
            }
            a.this.d();
        }
    }

    class d implements DeviceResponseCommonCallBack.ICallBack {
        d() {
        }

        @Override // com.ido.ble.callback.DeviceResponseCommonCallBack.ICallBack
        public void onResponse(int i, String str) {
            if (i == 7951 && a.this.o) {
                LogTool.d("FILE_TRANSFER_SPP", "get mtu ok," + str);
                a.this.k();
                a.this.o = false;
            }
        }
    }

    class e implements c.b {
        e() {
        }

        @Override // com.ido.ble.file.transfer.c.b
        public void onTimeOut() {
            if (a.this.i == g.SPP_CONNECT) {
                a.this.c("spp connect failed.");
                return;
            }
            if (a.this.i == g.TRANSFER_FILE) {
                a.this.f();
                a.this.b("trans file time out.");
            } else if (a.this.i == g.GET_MTU) {
                a.this.f();
                a.this.k();
            }
        }
    }

    class f implements Runnable {
        f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (a.this.f4504d == null || a.this.f4502b) {
                return;
            }
            LogTool.d("FILE_TRANSFER_SPP", "notify success!");
            a.this.f4504d.onProgress(100);
            a.this.f4504d.onSuccess();
            a.this.f4504d = null;
        }
    }

    private enum g {
        STATE_NULL,
        CHECK_FILE,
        SPP_CONNECT,
        GET_MTU,
        TRANSFER_FILE
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        LogTool.d("FILE_TRANSFER_SPP", "progress = " + i);
        b(i);
    }

    private void b(int i) {
        IFileTransferListener iFileTransferListener = this.f4504d;
        if (iFileTransferListener != null) {
            iFileTransferListener.onProgress(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        int i = this.f4501a;
        if (i >= this.f4507g.maxRetryTimes) {
            LogTool.d("FILE_TRANSFER_SPP", "try to trans again. out of max times.");
            c(str);
            return;
        }
        this.f4501a = i + 1;
        LogTool.b("FILE_TRANSFER_SPP", str);
        LogTool.d("FILE_TRANSFER_SPP", "try to trans again. times = " + this.f4501a);
        k();
    }

    private boolean b() {
        LogTool.d("FILE_TRANSFER_SPP", "check file.");
        this.i = g.CHECK_FILE;
        return new File(this.f4507g.filePath).exists();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        IFileTransferListener iFileTransferListener = this.f4504d;
        if (iFileTransferListener != null) {
            iFileTransferListener.onFailed("mp3 file onConvertFailed");
        } else {
            LogTool.d("FILE_TRANSFER_SPP", "transferListener = null,onConvertFailed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        LogTool.b("FILE_TRANSFER_SPP", str);
        LogTool.d("FILE_TRANSFER_SPP", "transfer failed!");
        h();
        IFileTransferListener iFileTransferListener = this.f4504d;
        if (iFileTransferListener != null) {
            iFileTransferListener.onFailed(str);
            this.f4504d = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogTool.d("FILE_TRANSFER_SPP", "to get mtu.");
        this.o = true;
        this.i = g.GET_MTU;
        com.ido.ble.callback.b.K().b(this.n);
        com.ido.ble.callback.b.K().a(this.n);
        com.ido.ble.i.a.a.d(7951);
    }

    public static a e() {
        return q;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.f4503c = new com.ido.ble.file.transfer.c();
        this.f4503c.a(new e());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        LogTool.d("FILE_TRANSFER_SPP", "transfer file complete.");
        l();
    }

    private void h() {
        LogTool.d("FILE_TRANSFER_SPP", "release.");
        com.ido.ble.gps.callback.a.h().b(this.l);
        com.ido.ble.gps.callback.a.h().b(this.k);
        this.i = g.STATE_NULL;
        this.f4506f.removeCallbacksAndMessages(null);
        this.f4505e = false;
        this.o = false;
        this.f4501a = 0;
        com.ido.ble.file.transfer.d dVar = this.f4503c;
        if (dVar != null) {
            dVar.b();
        }
        this.f4503c = null;
    }

    private void i() {
        this.f4502b = true;
        if (!this.f4505e) {
            this.f4506f.removeCallbacksAndMessages(null);
            LogTool.d("FILE_TRANSFER_SPP", "stop1.");
        } else {
            LogTool.d("FILE_TRANSFER_SPP", "stop.");
            u.t();
            h();
        }
    }

    private void j() {
        this.i = g.SPP_CONNECT;
        com.ido.ble.bluetooth.bt.c.a(this.m);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        LogTool.d("FILE_TRANSFER_SPP", "begin transfer file...");
        this.i = g.TRANSFER_FILE;
        if (this.f4507g.PRN <= 0) {
            Protocol.getInstance().tranDatasppSetPRN(50);
            LogTool.d("FILE_TRANSFER_SPP", "tranDatasppSetPRN prn = 50");
        } else {
            Protocol.getInstance().tranDatasppSetPRN(this.f4507g.PRN);
        }
        byte[] bArrA = com.ido.ble.common.c.a(this.f4507g.filePath);
        if (bArrA == null || bArrA.length <= 0) {
            c("byte data is null");
        } else {
            SPPFileTransferConfig sPPFileTransferConfig = this.f4507g;
            int iA = u.a(bArrA, sPPFileTransferConfig.dataType, sPPFileTransferConfig.firmwareSpecName.getBytes(), this.f4507g.zipType);
            if (iA != 0) {
                b("tranDataSetBuff return code is " + iA);
            }
        }
        LogTool.d("FILE_TRANSFER_SPP", "tranDataStart return code = " + Protocol.getInstance().tranDatasppStart());
    }

    private void l() {
        LogTool.d("FILE_TRANSFER_SPP", "transfer success!");
        h();
        this.f4506f.postDelayed(new f(), 1000L);
    }

    public void a() {
        this.f4502b = true;
        if (!this.f4505e) {
            this.f4506f.removeCallbacksAndMessages(null);
            LogTool.d("FILE_TRANSFER_SPP", "stopByUser1.");
        } else {
            LogTool.d("FILE_TRANSFER_SPP", "stopByUser.");
            Protocol.getInstance().tranDataSppManualStop();
            h();
        }
    }

    public void a(SPPFileTransferConfig sPPFileTransferConfig) {
        this.f4507g = sPPFileTransferConfig;
        this.f4504d = this.f4507g.iFileTransferListener;
        if (TextUtils.isEmpty(sPPFileTransferConfig.firmwareSpecName)) {
            LogTool.d("FILE_TRANSFER_SPP", "firmwareSpecName is null");
            IFileTransferListener iFileTransferListener = this.f4504d;
            if (iFileTransferListener != null) {
                iFileTransferListener.onFailed("firmwareSpecName is null");
                return;
            }
            return;
        }
        String[] strArrSplit = sPPFileTransferConfig.firmwareSpecName.split("\\.");
        if (strArrSplit.length == 1) {
            LogTool.d("FILE_TRANSFER_SPP", "firmwareSpecName format is wrong");
            IFileTransferListener iFileTransferListener2 = this.f4504d;
            if (iFileTransferListener2 != null) {
                iFileTransferListener2.onFailed("firmwareSpecName format is wrong");
                return;
            }
            return;
        }
        if (!TextUtils.equals(strArrSplit[strArrSplit.length - 1], "mp3")) {
            b(sPPFileTransferConfig);
        } else {
            com.ido.ble.gps.callback.a.h().a(this.k);
            a(sPPFileTransferConfig.filePath);
        }
    }

    public void a(SPPFileTransferConfig sPPFileTransferConfig, ISPPConnectStateListener iSPPConnectStateListener) {
        this.f4508h = iSPPConnectStateListener;
        a(sPPFileTransferConfig);
    }

    public void a(String str) {
        File file = new File(str);
        if (!file.exists()) {
            LogTool.d("FILE_TRANSFER_SPP", "[mp3ToMp3] file not exists:" + str);
            IFileTransferListener iFileTransferListener = this.f4504d;
            if (iFileTransferListener != null) {
                iFileTransferListener.onFailed("[mp3ToMp3] file not exists:" + str);
                return;
            }
            return;
        }
        this.j = str.replace(file.getName(), "") + "tempMp3File.mp3";
        Mp3ToMp3Para mp3ToMp3Para = new Mp3ToMp3Para();
        mp3ToMp3Para.mp3in = str;
        mp3ToMp3Para.mp3out = this.j;
        LogTool.d("FILE_TRANSFER_SPP", "[mp3ToMp3] " + mp3ToMp3Para.toString());
        LogTool.d("FILE_TRANSFER_SPP", "[mp3ToMp3] " + u.b(com.ido.ble.common.c.b(k.a(mp3ToMp3Para)), com.veryfit.multi.nativeprotocol.b.b4));
    }

    public void b(SPPFileTransferConfig sPPFileTransferConfig) {
        LogTool.d("FILE_TRANSFER_SPP", "start ... " + sPPFileTransferConfig.toString());
        if (this.f4505e) {
            LogTool.d("FILE_TRANSFER_SPP", "is in staring state, ignore ...");
            return;
        }
        f();
        this.f4507g = sPPFileTransferConfig;
        this.f4504d = this.f4507g.iFileTransferListener;
        this.f4504d.onStart();
        if (!b()) {
            c("file is not exist.");
            return;
        }
        this.f4505e = true;
        this.f4502b = false;
        com.ido.ble.gps.callback.a.h().a(this.l);
        if (com.ido.ble.bluetooth.bt.c.c()) {
            d();
        } else {
            j();
        }
    }

    public void b(SPPFileTransferConfig sPPFileTransferConfig, ISPPConnectStateListener iSPPConnectStateListener) {
        this.f4508h = iSPPConnectStateListener;
        b(sPPFileTransferConfig);
    }
}