package com.loc;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.bumptech.glide.load.Key;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: compiled from: ApsManager.java */
/* JADX INFO: loaded from: classes3.dex */
public final class e {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    static boolean f5101g = false;
    private List<Messenger> B;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    Context f5106e;
    private boolean t = false;
    private boolean u = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    String f5102a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    b f5103b = null;
    private long v = 0;
    private long w = 0;
    private ds x = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    AMapLocation f5104c = null;
    private long y = 0;
    private int z = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    a f5105d = null;
    private i A = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    dn f5107f = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    HashMap<Messenger, Long> f5108h = new HashMap<>();
    en i = null;
    long j = 0;
    long k = 0;
    String l = null;
    private boolean C = true;
    private String D = "";
    AMapLocationClientOption m = null;
    AMapLocationClientOption n = new AMapLocationClientOption();
    ServerSocket o = null;
    boolean p = false;
    Socket q = null;
    boolean r = false;
    c s = null;
    private final int E = 5000;
    private String F = "jsonp1";

    /* JADX INFO: compiled from: ApsManager.java */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            Bundle data;
            Messenger messenger;
            try {
                data = message.getData();
                try {
                    messenger = message.replyTo;
                    if (data != null) {
                        try {
                            if (!data.isEmpty()) {
                                String string = data.getString("c");
                                e eVar = e.this;
                                if (TextUtils.isEmpty(eVar.l)) {
                                    eVar.l = ej.b(eVar.f5106e);
                                }
                                if (!(!TextUtils.isEmpty(string) && string.equals(eVar.l))) {
                                    if (message.what == 1) {
                                        en.a((String) null, 2102);
                                        ds dsVarA = e.a("invalid handlder scode!!!#1002");
                                        dm dmVar = new dm();
                                        dmVar.f("#1002");
                                        dmVar.e("conitue");
                                        e.this.a(messenger, dsVarA, dsVarA.k(), dmVar);
                                        return;
                                    }
                                    return;
                                }
                            }
                        } catch (Throwable th) {
                            th = th;
                            try {
                                ej.a(th, "ApsServiceCore", "ActionHandler handlerMessage");
                            } catch (Throwable th2) {
                                ej.a(th2, "actionHandler", "handleMessage");
                                return;
                            }
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    messenger = null;
                }
            } catch (Throwable th4) {
                th = th4;
                data = null;
                messenger = null;
            }
            int i = message.what;
            if (i != 0) {
                if (i != 1) {
                    if (i == 2) {
                        if (data != null && !data.isEmpty()) {
                            e.this.a((Bundle) null);
                            e eVar2 = e.this;
                            try {
                                if (!eVar2.r) {
                                    eVar2.s = eVar2.new c();
                                    eVar2.s.start();
                                    eVar2.r = true;
                                }
                            } catch (Throwable th5) {
                                ej.a(th5, "ApsServiceCore", "startSocket");
                            }
                        }
                        return;
                    }
                    if (i == 3) {
                        if (data != null && !data.isEmpty()) {
                            e.this.a((Bundle) null);
                            e.this.b();
                        }
                        return;
                    }
                    switch (i) {
                        case 9:
                            e.this.a(data);
                            e.a(e.this);
                            break;
                        case 10:
                            e.this.a(data);
                            e.this.a(messenger, data);
                            break;
                        case 11:
                            e.this.c();
                            break;
                        case 12:
                            e.a(e.this, messenger);
                            break;
                        case 13:
                            Messenger messenger2 = message.replyTo;
                            if (messenger2 != null && e.this.B != null && !e.this.B.contains(messenger2)) {
                                e.this.B.add(messenger2);
                                if (e.this.B.size() == 1) {
                                    e.this.f();
                                }
                            }
                            break;
                        case 14:
                            Messenger messenger3 = message.replyTo;
                            if (messenger3 != null && e.this.B != null && e.this.B.contains(messenger3)) {
                                e.this.B.remove(messenger3);
                            }
                            if (e.this.B != null && e.this.B.size() == 0) {
                                e.this.f5107f.h();
                            }
                            break;
                    }
                } else {
                    e.this.a(data);
                    e.b(e.this, messenger, data);
                }
            } else {
                e.this.a(data);
                e.a(e.this, messenger, data);
            }
            super.handleMessage(message);
        }
    }

    /* JADX INFO: compiled from: ApsManager.java */
    class b extends HandlerThread {
        public b(String str) {
            super(str);
        }

        @Override // android.os.HandlerThread
        protected final void onLooperPrepared() {
            try {
                try {
                    e.this.A = new i(e.this.f5106e);
                } catch (Throwable th) {
                    ej.a(th, "APSManager$ActionThread", "init 2");
                }
                try {
                    ei.b(e.this.f5106e);
                    ei.a(e.this.f5106e);
                } catch (Throwable th2) {
                    ej.a(th2, "APSManager$ActionThread", "init 3");
                }
                e.this.f5107f = new dn();
                super.onLooperPrepared();
            } catch (Throwable th3) {
                ej.a(th3, "APSManager$ActionThread", "onLooperPrepared");
            }
        }

        @Override // android.os.HandlerThread, java.lang.Thread, java.lang.Runnable
        public final void run() {
            try {
                super.run();
            } catch (Throwable th) {
                ej.a(th, "APSManager$ActionThread", "run");
            }
        }
    }

    /* JADX INFO: compiled from: ApsManager.java */
    class c extends Thread {
        c() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            try {
                if (!e.this.p) {
                    e.this.p = true;
                    e.this.o = new ServerSocket(43689);
                }
                while (e.this.p && e.this.o != null) {
                    e.this.q = e.this.o.accept();
                    e.a(e.this, e.this.q);
                }
            } catch (Throwable th) {
                ej.a(th, "ApsServiceCore", "run");
            }
            super.run();
        }
    }

    public e(Context context) {
        this.f5106e = null;
        this.f5106e = context;
    }

    private static ds a(int i, String str) {
        try {
            ds dsVar = new ds("");
            dsVar.setErrorCode(i);
            dsVar.setLocationDetail(str);
            return dsVar;
        } catch (Throwable th) {
            ej.a(th, "ApsServiceCore", "newInstanceAMapLoc");
            return null;
        }
    }

    static /* synthetic */ ds a(String str) {
        return a(10, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Bundle bundle) {
        try {
            if (this.t) {
                return;
            }
            ej.a(this.f5106e);
            if (bundle != null) {
                this.n = ej.a(bundle.getBundle("optBundle"));
            }
            this.f5107f.a(this.f5106e);
            this.f5107f.a();
            a(this.n);
            this.f5107f.b();
            this.t = true;
            this.C = true;
            this.D = "";
            if (this.B == null || this.B.size() <= 0) {
                return;
            }
            f();
        } catch (Throwable th) {
            this.C = false;
            this.D = th.getMessage();
            ej.a(th, "ApsServiceCore", com.ido.ble.event.stat.one.d.m);
        }
    }

    private static void a(Messenger messenger, int i, Bundle bundle) {
        if (messenger != null) {
            try {
                Message messageObtain = Message.obtain();
                messageObtain.setData(bundle);
                messageObtain.what = i;
                messenger.send(messageObtain);
            } catch (Throwable th) {
                ej.a(th, "ApsServiceCore", "sendMessage");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Messenger messenger, AMapLocation aMapLocation, String str, dm dmVar) {
        Bundle bundle = new Bundle();
        bundle.setClassLoader(AMapLocation.class.getClassLoader());
        bundle.putParcelable("loc", aMapLocation);
        bundle.putString("nb", str);
        bundle.putParcelable("statics", dmVar);
        this.f5108h.put(messenger, Long.valueOf(ep.b()));
        a(messenger, 1, bundle);
    }

    private void a(AMapLocationClientOption aMapLocationClientOption) {
        try {
            if (this.f5107f != null) {
                this.f5107f.a(aMapLocationClientOption);
            }
            if (aMapLocationClientOption != null) {
                f5101g = aMapLocationClientOption.isKillProcess();
                if (this.m != null && (aMapLocationClientOption.isOffset() != this.m.isOffset() || aMapLocationClientOption.isNeedAddress() != this.m.isNeedAddress() || aMapLocationClientOption.isLocationCacheEnable() != this.m.isLocationCacheEnable() || this.m.getGeoLanguage() != aMapLocationClientOption.getGeoLanguage())) {
                    this.w = 0L;
                    this.f5104c = null;
                }
                this.m = aMapLocationClientOption;
            }
        } catch (Throwable th) {
            ej.a(th, "ApsServiceCore", "setExtra");
        }
    }

    static /* synthetic */ void a(e eVar) {
        try {
            ei.c(eVar.f5106e);
        } catch (Throwable th) {
            ej.a(th, "ApsServiceCore", "doCallOtherSer");
        }
    }

    static /* synthetic */ void a(e eVar, Messenger messenger) {
        eVar.f5108h.remove(messenger);
    }

    static /* synthetic */ void a(e eVar, Messenger messenger, Bundle bundle) {
        if (bundle != null) {
            try {
                if (bundle.isEmpty() || eVar.u) {
                    return;
                }
                eVar.u = true;
                try {
                    eVar.f5107f.e();
                    if (ei.k()) {
                        Bundle bundle2 = new Bundle();
                        bundle2.putBoolean("installMockApp", true);
                        a(messenger, 9, bundle2);
                    }
                } catch (Throwable th) {
                    ej.a(th, "ApsServiceCore", "initAuth");
                }
            } catch (Throwable th2) {
                ej.a(th2, "ApsServiceCore", "doInitAuth");
            }
        }
    }

    static /* synthetic */ void a(e eVar, Socket socket) {
        BufferedReader bufferedReader;
        if (socket == null) {
            return;
        }
        try {
            int i = ej.f5165g;
            String str = null;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), Key.STRING_CHARSET_NAME));
            } catch (Throwable th) {
                th = th;
                bufferedReader = null;
            }
            try {
                eVar.a(bufferedReader);
                String strE = eVar.e();
                ej.f5165g = i;
                try {
                    eVar.b(strE);
                } catch (Throwable th2) {
                    try {
                        ej.a(th2, "apm", "inSocetLn part2");
                        try {
                            bufferedReader.close();
                            socket.close();
                        } catch (Throwable th3) {
                            ej.a(th3, "apm", "inSocetLn part3");
                        }
                    } finally {
                        try {
                            bufferedReader.close();
                            socket.close();
                        } catch (Throwable th4) {
                            ej.a(th4, "apm", "inSocetLn part3");
                        }
                    }
                }
            } catch (Throwable th5) {
                th = th5;
                try {
                    str = eVar.F + "&&" + eVar.F + "({'package':'" + eVar.f5102a + "','error_code':1,'error':'params error'})";
                    ej.a(th, "apm", "inSocetLn");
                    ej.f5165g = i;
                    try {
                        eVar.b(str);
                        try {
                            bufferedReader.close();
                            socket.close();
                        } catch (Throwable th6) {
                            ej.a(th6, "apm", "inSocetLn part3");
                        }
                    } catch (Throwable th7) {
                        try {
                            ej.a(th7, "apm", "inSocetLn part2");
                            try {
                                bufferedReader.close();
                                socket.close();
                            } catch (Throwable th8) {
                                ej.a(th8, "apm", "inSocetLn part3");
                            }
                        } finally {
                            try {
                                bufferedReader.close();
                                socket.close();
                            } catch (Throwable th9) {
                                ej.a(th9, "apm", "inSocetLn part3");
                            }
                        }
                    }
                } catch (Throwable th10) {
                    ej.f5165g = i;
                    try {
                        eVar.b(str);
                        try {
                            bufferedReader.close();
                            socket.close();
                        } catch (Throwable th11) {
                            th = th11;
                            ej.a(th, "apm", "inSocetLn part3");
                            throw th10;
                        }
                    } catch (Throwable th12) {
                        try {
                            ej.a(th12, "apm", "inSocetLn part2");
                            try {
                                bufferedReader.close();
                                socket.close();
                            } catch (Throwable th13) {
                                th = th13;
                                ej.a(th, "apm", "inSocetLn part3");
                                throw th10;
                            }
                        } finally {
                            try {
                                bufferedReader.close();
                                socket.close();
                            } catch (Throwable th14) {
                                ej.a(th14, "apm", "inSocetLn part3");
                            }
                        }
                    }
                    throw th10;
                }
            }
        } catch (Throwable th15) {
            ej.a(th15, "apm", "inSocetLn part4");
        }
    }

    private void a(BufferedReader bufferedReader) throws Exception {
        String[] strArrSplit;
        String[] strArrSplit2;
        String[] strArrSplit3;
        String line = bufferedReader.readLine();
        int i = 30000;
        if (line != null && line.length() > 0 && (strArrSplit = line.split(" ")) != null && strArrSplit.length > 1 && (strArrSplit2 = strArrSplit[1].split("\\?")) != null && strArrSplit2.length > 1 && (strArrSplit3 = strArrSplit2[1].split("&")) != null && strArrSplit3.length > 0) {
            int iG = 30000;
            for (String str : strArrSplit3) {
                String[] strArrSplit4 = str.split("=");
                if (strArrSplit4 != null && strArrSplit4.length > 1) {
                    if ("to".equals(strArrSplit4[0])) {
                        iG = ep.g(strArrSplit4[1]);
                    }
                    if ("callback".equals(strArrSplit4[0])) {
                        this.F = strArrSplit4[1];
                    }
                }
            }
            i = iG;
        }
        ej.f5165g = i;
    }

    private AMapLocationClientOption b(Bundle bundle) {
        AMapLocationClientOption aMapLocationClientOptionA = ej.a(bundle.getBundle("optBundle"));
        a(aMapLocationClientOptionA);
        try {
            String string = bundle.getString("d");
            if (!TextUtils.isEmpty(string)) {
                n.a(string);
            }
        } catch (Throwable th) {
            try {
                ej.a(th, "APSManager", "doLocation setUmidToken");
            } catch (Throwable th2) {
                ej.a(th2, "APSManager", "parseBundle");
            }
        }
        return aMapLocationClientOptionA;
    }

    static /* synthetic */ void b(e eVar, Messenger messenger, Bundle bundle) {
        AMapLocation aMapLocationA;
        if (bundle != null) {
            try {
                if (bundle.isEmpty()) {
                    return;
                }
                dm dmVar = new dm();
                dmVar.e("conitue");
                AMapLocationClientOption aMapLocationClientOptionB = eVar.b(bundle);
                if (eVar.f5108h.containsKey(messenger) && !aMapLocationClientOptionB.isOnceLocation()) {
                    if (ep.b() - eVar.f5108h.get(messenger).longValue() < 800) {
                        return;
                    }
                }
                String strK = null;
                if (!eVar.C) {
                    eVar.x = a(9, "init error : " + eVar.D + "#0901");
                    dmVar.f("#0901");
                    eVar.a(messenger, eVar.x, eVar.x.k(), dmVar);
                    en.a((String) null, 2091);
                    return;
                }
                long jB = ep.b();
                if (ep.a(eVar.x) && jB - eVar.w < 600) {
                    eVar.a(messenger, eVar.x, eVar.x.k(), dmVar);
                    return;
                }
                dmVar.c(ep.b());
                try {
                    eVar.x = eVar.f5107f.a(dmVar);
                    if (eVar.x.getLocationType() != 6) {
                        eVar.x.getLocationType();
                    }
                    eVar.x = eVar.f5107f.a(eVar.x);
                } catch (Throwable th) {
                    en.a((String) null, 2081);
                    dmVar.f("#0801");
                    eVar.x = a(8, "loc error : " + th.getMessage() + "#0801");
                    ej.a(th, "ApsServiceCore", "run part2");
                }
                if (ep.a(eVar.x)) {
                    eVar.w = ep.b();
                }
                if (eVar.x == null) {
                    eVar.x = a(8, "loc is null#0801");
                    dmVar.f("#0801");
                }
                if (eVar.x != null) {
                    strK = eVar.x.k();
                    aMapLocationA = eVar.x.m7clone();
                } else {
                    aMapLocationA = null;
                }
                try {
                    if (aMapLocationClientOptionB.isLocationCacheEnable() && eVar.A != null) {
                        aMapLocationA = eVar.A.a(aMapLocationA, strK, aMapLocationClientOptionB.getLastLocationLifeCycle());
                    }
                } catch (Throwable th2) {
                    ej.a(th2, "ApsServiceCore", "fixLastLocation");
                }
                eVar.a(messenger, aMapLocationA, strK, dmVar);
            } catch (Throwable th3) {
                ej.a(th3, "ApsServiceCore", "doLocation");
            }
        }
    }

    private void b(String str) throws IOException {
        PrintStream printStream = new PrintStream(this.q.getOutputStream(), true, Key.STRING_CHARSET_NAME);
        printStream.println("HTTP/1.0 200 OK");
        printStream.println("Content-Length:" + str.getBytes(Key.STRING_CHARSET_NAME).length);
        printStream.println();
        printStream.println(str);
        printStream.close();
    }

    public static void d() {
        f5101g = false;
    }

    private String e() {
        StringBuilder sb;
        String str;
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (ep.e(this.f5106e)) {
            sb = new StringBuilder();
            sb.append(this.F);
            sb.append("&&");
            sb.append(this.F);
            sb.append("({'package':'");
            sb.append(this.f5102a);
            str = "','error_code':36,'error':'app is background'})";
        } else {
            ds dsVar = this.x;
            if (dsVar == null || jCurrentTimeMillis - dsVar.getTime() > BootloaderScanner.TIMEOUT) {
                try {
                    this.x = this.f5107f.a(new dm());
                } catch (Throwable th) {
                    ej.a(th, "ApsServiceCore", "getSocketLocResult");
                }
            }
            ds dsVar2 = this.x;
            if (dsVar2 == null) {
                sb = new StringBuilder();
                sb.append(this.F);
                sb.append("&&");
                sb.append(this.F);
                sb.append("({'package':'");
                sb.append(this.f5102a);
                str = "','error_code':8,'error':'unknown error'})";
            } else if (dsVar2.getErrorCode() != 0) {
                sb = new StringBuilder();
                sb.append(this.F);
                sb.append("&&");
                sb.append(this.F);
                sb.append("({'package':'");
                sb.append(this.f5102a);
                sb.append("','error_code':");
                sb.append(this.x.getErrorCode());
                sb.append(",'error':'");
                sb.append(this.x.getErrorInfo());
                str = "'})";
            } else {
                sb = new StringBuilder();
                sb.append(this.F);
                sb.append("&&");
                sb.append(this.F);
                sb.append("({'package':'");
                sb.append(this.f5102a);
                sb.append("','error_code':0,'error':'','location':{'y':");
                sb.append(this.x.getLatitude());
                sb.append(",'precision':");
                sb.append(this.x.getAccuracy());
                sb.append(",'x':");
                sb.append(this.x.getLongitude());
                str = "},'version_code':'5.2.0','version':'5.2.0'})";
            }
        }
        sb.append(str);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        try {
            if (this.f5107f == null || this.f5107f == null) {
                return;
            }
            this.f5107f.a(this.f5105d);
            this.f5107f.g();
        } catch (Throwable th) {
            ej.a(th, "ApsServiceCore", "startColl");
        }
    }

    public final void a() {
        try {
            this.i = new en();
            this.f5103b = new b("amapLocCoreThread");
            this.f5103b.setPriority(5);
            this.f5103b.start();
            this.f5105d = new a(this.f5103b.getLooper());
            this.B = new ArrayList();
        } catch (Throwable th) {
            ej.a(th, "ApsServiceCore", "onCreate");
        }
    }

    final void a(Messenger messenger, Bundle bundle) {
        float fA;
        if (bundle != null) {
            try {
                if (!bundle.isEmpty() && ei.h()) {
                    double d2 = bundle.getDouble("lat");
                    double d3 = bundle.getDouble("lon");
                    b(bundle);
                    if (this.f5104c != null) {
                        fA = ep.a(new double[]{d2, d3, this.f5104c.getLatitude(), this.f5104c.getLongitude()});
                        if (fA < ei.i() * 3) {
                            Bundle bundle2 = new Bundle();
                            bundle2.setClassLoader(AMapLocation.class.getClassLoader());
                            bundle2.putInt("I_MAX_GEO_DIS", ei.i() * 3);
                            bundle2.putInt("I_MIN_GEO_DIS", ei.i());
                            bundle2.putParcelable("loc", this.f5104c);
                            a(messenger, 6, bundle2);
                        }
                    } else {
                        fA = -1.0f;
                    }
                    if (fA == -1.0f || fA > ei.i()) {
                        a(bundle);
                        this.f5104c = this.f5107f.a(d2, d3);
                    }
                }
            } catch (Throwable th) {
                ej.a(th, "ApsServiceCore", "doLocationGeo");
            }
        }
    }

    public final void b() {
        try {
            if (this.q != null) {
                this.q.close();
            }
        } catch (Throwable th) {
            ej.a(th, "ApsServiceCore", "doStopScocket 1");
        }
        try {
            if (this.o != null) {
                this.o.close();
            }
        } catch (Throwable th2) {
            ej.a(th2, "ApsServiceCore", "doStopScocket 2");
        }
        try {
            if (this.s != null) {
                this.s.interrupt();
            }
        } catch (Throwable unused) {
        }
        this.s = null;
        this.o = null;
        this.p = false;
        this.r = false;
    }

    public final void c() {
        b bVar;
        try {
            if (this.f5108h != null) {
                this.f5108h.clear();
                this.f5108h = null;
            }
            try {
                if (this.B != null) {
                    this.B.clear();
                }
            } catch (Throwable th) {
                ej.a(th, "apm", "des1");
            }
            if (this.f5105d != null) {
                this.f5105d.removeCallbacksAndMessages(null);
            }
            if (this.f5103b != null) {
                if (Build.VERSION.SDK_INT >= 18) {
                    try {
                        em.a(this.f5103b, (Class<?>) HandlerThread.class, "quitSafely", new Object[0]);
                    } catch (Throwable unused) {
                        bVar = this.f5103b;
                        bVar.quit();
                    }
                } else {
                    bVar = this.f5103b;
                }
                bVar.quit();
            }
            this.f5103b = null;
            this.f5105d = null;
            if (this.A != null) {
                this.A.c();
                this.A = null;
            }
            b();
            this.t = false;
            this.u = false;
            this.f5107f.d();
            if (this.i != null && this.j != 0 && this.k != 0) {
                long jB = ep.b() - this.j;
                en.a(this.f5106e, this.i.c(this.f5106e), this.i.d(this.f5106e), this.k, jB);
                this.i.e(this.f5106e);
            }
            en.a(this.f5106e);
            ab.b();
            if (f5101g) {
                Process.killProcess(Process.myPid());
            }
        } catch (Throwable th2) {
            ej.a(th2, "apm", "tdest");
        }
    }
}