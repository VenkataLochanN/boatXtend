package com.ido.ble.gps.agps;

import android.os.Handler;
import android.os.Looper;
import com.ido.ble.gps.callback.GpsCallBack;
import com.ido.ble.gps.model.ConnParam;
import com.ido.ble.gps.model.ConnParamReply;
import com.ido.ble.gps.model.ControlGps;
import com.ido.ble.gps.model.ControlGpsReply;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.handler.u;
import com.veryfit.multi.nativeprotocol.Protocol;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class a {
    private static final String m = "A_GPS_FILE_TRANSLATE";
    private static final int n = 1000;
    private static final int o = 5;
    private static final int p = 1500;
    private static final int q = 30;
    private static a r = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private IAGpsTranslateStateListener f4576d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f4577e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f4578f;
    private g i;
    private AgpsFileTransConfig j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f4573a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f4574b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f4575c = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f4579g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Handler f4580h = new Handler(Looper.getMainLooper());
    private GpsCallBack.ITranAgpsFileCallBack k = new C0086a();
    private GpsCallBack.IDeviceReplySetGpsCallBack l = new b();

    /* JADX INFO: renamed from: com.ido.ble.gps.agps.a$a, reason: collision with other inner class name */
    class C0086a implements GpsCallBack.ITranAgpsFileCallBack {
        C0086a() {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ITranAgpsFileCallBack
        public void onFailed(int i) {
            a.this.a("translate progress return code = " + i);
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ITranAgpsFileCallBack
        public void onFailed(int i, Object obj) {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ITranAgpsFileCallBack
        public void onFinish() {
            a.this.g();
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.ITranAgpsFileCallBack
        public void onProgress(int i) {
            a.this.a(i);
        }
    }

    class b implements GpsCallBack.IDeviceReplySetGpsCallBack {
        b() {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IDeviceReplySetGpsCallBack
        public void onControlGps(ControlGpsReply controlGpsReply) {
            a.this.a(controlGpsReply);
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IDeviceReplySetGpsCallBack
        public void onSetConfigGps(boolean z) {
        }

        @Override // com.ido.ble.gps.callback.GpsCallBack.IDeviceReplySetGpsCallBack
        public void onSetConnParam(ConnParamReply connParamReply) {
            a.this.a(connParamReply);
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
            a.this.c();
        }
    }

    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.d();
        }
    }

    class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.e();
        }
    }

    class f implements Runnable {
        f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            LogTool.d(a.m, "translate success!");
            a.this.f4576d.onProgress(100);
            a.this.f4576d.onSuccess();
            a.this.h();
        }
    }

    private enum g {
        STATE_NULL,
        CHECK_FILE,
        SET_FAST_SPEED,
        CHECK_FAST_SPEED_STATE,
        TRANSLATE_FILE,
        WRITE_CHIP,
        CHECK_WRITE_CHIP_STATE,
        SET_SLOW_SPEED,
        CHECK_SLOW_SPEED_STATE
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        if (i <= 99) {
            b(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ConnParamReply connParamReply) {
        Handler handler;
        Runnable dVar;
        StringBuilder sb;
        String str;
        String string;
        if (connParamReply != null) {
            LogTool.d(m, connParamReply.toString());
        }
        g gVar = this.i;
        if (gVar != g.SET_FAST_SPEED) {
            if (gVar == g.CHECK_FAST_SPEED_STATE) {
                if (connParamReply != null && connParamReply.currMode == 1) {
                    LogTool.d(m, "set fast translate mode ok.");
                    k();
                    return;
                } else {
                    handler = this.f4580h;
                    dVar = new c();
                }
            } else if (gVar == g.SET_SLOW_SPEED) {
                if (connParamReply == null) {
                    string = "set slow translate mode failed, return info is null";
                } else {
                    if (connParamReply.errorCode == 0) {
                        d();
                        return;
                    }
                    sb = new StringBuilder();
                    str = "set slow translate mode return invalid code = ";
                    sb.append(str);
                    sb.append(connParamReply.errorCode);
                    string = sb.toString();
                }
            } else {
                if (gVar != g.CHECK_SLOW_SPEED_STATE) {
                    return;
                }
                if (connParamReply != null && connParamReply.currMode == 2) {
                    LogTool.d(m, "set slow translate mode ok.");
                    l();
                    return;
                } else {
                    handler = this.f4580h;
                    dVar = new d();
                }
            }
            handler.postDelayed(dVar, 1000L);
            return;
        }
        if (connParamReply == null) {
            string = "set fast translate mode failed, return info is null";
        } else {
            if (connParamReply.errorCode == 0) {
                c();
                return;
            }
            sb = new StringBuilder();
            str = "set fast translate mode return invalid code = ";
            sb.append(str);
            sb.append(connParamReply.errorCode);
            string = sb.toString();
        }
        a(string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ControlGpsReply controlGpsReply) {
        StringBuilder sb;
        String string;
        if (controlGpsReply != null) {
            LogTool.d(m, controlGpsReply.toString());
        }
        g gVar = this.i;
        if (gVar == g.WRITE_CHIP) {
            if (controlGpsReply == null) {
                string = "write chip failed, return info is null.";
                a(string);
            } else {
                if (controlGpsReply.errorCode == 0) {
                    e();
                    return;
                }
                sb = new StringBuilder();
            }
        } else {
            if (gVar != g.CHECK_WRITE_CHIP_STATE) {
                return;
            }
            if (controlGpsReply == null || controlGpsReply.status != 2) {
                this.f4580h.postDelayed(new e(), 1500L);
                return;
            } else {
                if (controlGpsReply.errorCode == 0) {
                    LogTool.d(m, "write chip ok.");
                    j();
                    return;
                }
                sb = new StringBuilder();
            }
        }
        sb.append("write chip return invalid code = ");
        sb.append(controlGpsReply.errorCode);
        string = sb.toString();
        a(string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        LogTool.b(m, str);
        this.f4576d.onFailed(str);
        h();
    }

    private void b(int i) {
        this.f4576d.onProgress(i);
    }

    private boolean b() {
        LogTool.d(m, "check aGps file.");
        this.i = g.CHECK_FILE;
        return new File(this.f4577e).exists();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        int i = this.f4573a;
        if (i > 30) {
            a("check fast speed mode times out of max times!");
            return;
        }
        this.f4573a = i + 1;
        LogTool.d(m, "check fast speed state.");
        this.i = g.CHECK_FAST_SPEED_STATE;
        ConnParam connParam = new ConnParam();
        connParam.mode = 0;
        com.ido.ble.h.a.a(connParam);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        int i = this.f4574b;
        if (i > 5) {
            a("check slow speed mode times out of max times!");
            return;
        }
        this.f4574b = i + 1;
        LogTool.d(m, "check slow translate mode.");
        this.i = g.CHECK_SLOW_SPEED_STATE;
        ConnParam connParam = new ConnParam();
        connParam.mode = 0;
        com.ido.ble.h.a.a(connParam);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        int i = this.f4575c;
        if (i > 30) {
            a("check write chip times out of max times!");
            return;
        }
        this.f4575c = i + 1;
        LogTool.d(m, "check write chip state...");
        this.i = g.CHECK_WRITE_CHIP_STATE;
        ControlGps controlGps = new ControlGps();
        controlGps.operate = 2;
        controlGps.type = 3;
        com.ido.ble.h.a.a(controlGps);
    }

    public static a f() {
        return r;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        LogTool.d(m, "translate aGps file complete.");
        if (this.j.fileType == 2) {
            l();
        } else {
            m();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        LogTool.d(m, "release.");
        com.ido.ble.gps.callback.a.h().b(this.k);
        com.ido.ble.gps.callback.a.h().b(this.l);
        this.f4576d = null;
        this.i = g.STATE_NULL;
        this.f4580h.removeCallbacksAndMessages(null);
        this.f4579g = false;
        this.f4574b = 0;
        this.f4573a = 0;
        this.f4575c = 0;
    }

    private void i() {
        LogTool.d(m, "set fast translate mode.");
        this.i = g.SET_FAST_SPEED;
        ConnParam connParam = new ConnParam();
        connParam.mode = 1;
        com.ido.ble.h.a.a(connParam);
    }

    private void j() {
        LogTool.d(m, "set slow translate mode.");
        this.i = g.SET_SLOW_SPEED;
        ConnParam connParam = new ConnParam();
        connParam.mode = 2;
        com.ido.ble.h.a.a(connParam);
    }

    private void k() {
        Protocol protocol;
        int i;
        String str;
        LogTool.d(m, "begin translate aGps file...");
        this.i = g.TRANSLATE_FILE;
        if (this.f4578f <= 0) {
            protocol = Protocol.getInstance();
            i = 1;
        } else {
            protocol = Protocol.getInstance();
            i = this.f4578f;
        }
        protocol.tranDataSetPRN(i);
        byte[] bArrA = com.ido.ble.common.c.a(this.f4577e);
        if (bArrA != null && bArrA.length > 0) {
            int iC = u.c(bArrA);
            if (iC != 0) {
                str = "tranDataSetBuff return code is " + iC;
            }
            Protocol.getInstance().tranDataStart();
        }
        str = "aGps byte data is null";
        a(str);
        Protocol.getInstance().tranDataStart();
    }

    private void l() {
        if (this.j.fileType == 1) {
            LogTool.d(m, "to set gps default para.");
            com.ido.ble.h.a.f();
        }
        this.f4580h.postDelayed(new f(), 2000L);
    }

    private void m() {
        LogTool.d(m, "start to write to chip...");
        this.i = g.WRITE_CHIP;
        ControlGps controlGps = new ControlGps();
        controlGps.operate = 1;
        controlGps.type = 3;
        com.ido.ble.h.a.a(controlGps);
    }

    public void a() {
        if (this.f4579g) {
            LogTool.d(m, "stop.");
            Protocol.getInstance().tranDataStop();
            h();
        }
    }

    public void a(AgpsFileTransConfig agpsFileTransConfig) {
        LogTool.d(m, "start ... " + agpsFileTransConfig.toString());
        if (this.f4579g) {
            LogTool.d(m, "is in staring state, ignore ...");
            return;
        }
        this.j = agpsFileTransConfig;
        this.f4576d = agpsFileTransConfig.listener;
        this.f4577e = agpsFileTransConfig.filePath;
        this.f4578f = agpsFileTransConfig.PRN;
        this.f4576d.onStart();
        if (!b()) {
            a("aGps file is not exist.");
            return;
        }
        this.f4579g = true;
        com.ido.ble.gps.callback.a.h().a(this.k);
        com.ido.ble.gps.callback.a.h().a(this.l);
        i();
    }

    @Deprecated
    public void a(String str, IAGpsTranslateStateListener iAGpsTranslateStateListener) {
        AgpsFileTransConfig agpsFileTransConfig = new AgpsFileTransConfig();
        agpsFileTransConfig.filePath = str;
        agpsFileTransConfig.listener = iAGpsTranslateStateListener;
        a(agpsFileTransConfig);
    }
}