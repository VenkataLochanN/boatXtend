package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.mapcore.util.ca;
import com.amap.api.mapcore.util.il;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.MapsInitializer;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: compiled from: NetFileFetch.java */
/* JADX INFO: loaded from: classes.dex */
public class bu implements il.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    bv f292a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    long f295d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    bp f297f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    a f299h;
    private Context i;
    private ca j;
    private String k;
    private it l;
    private bq m;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    long f293b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    long f294c = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    boolean f296e = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    long f298g = 0;
    private boolean n = false;

    /* JADX INFO: compiled from: NetFileFetch.java */
    public interface a {
        void c();
    }

    public bu(bv bvVar, String str, Context context, ca caVar) throws IOException {
        this.f292a = null;
        this.f297f = bp.a(context.getApplicationContext());
        this.f292a = bvVar;
        this.i = context;
        this.k = str;
        this.j = caVar;
        d();
    }

    private void c() throws IOException {
        cb cbVar = new cb(this.k);
        cbVar.setConnectionTimeout(30000);
        cbVar.setSoTimeout(30000);
        this.l = new it(cbVar, this.f293b, this.f294c, MapsInitializer.getProtocol() == 2);
        this.m = new bq(this.f292a.b() + File.separator + this.f292a.c(), this.f293b);
    }

    private void d() {
        File file = new File(this.f292a.b() + this.f292a.c());
        if (file.exists()) {
            this.f296e = false;
            this.f293b = file.length();
            try {
                this.f295d = g();
                this.f294c = this.f295d;
                return;
            } catch (IOException unused) {
                ca caVar = this.j;
                if (caVar != null) {
                    caVar.a(ca.a.file_io_exception);
                    return;
                }
                return;
            }
        }
        this.f293b = 0L;
        this.f294c = 0L;
    }

    public void a() {
        try {
            if (er.d(this.i)) {
                f();
                if (gk.f1092a != 1) {
                    if (this.j != null) {
                        this.j.a(ca.a.amap_exception);
                        return;
                    }
                    return;
                }
                if (!e()) {
                    this.f296e = true;
                }
                if (this.f296e) {
                    this.f295d = g();
                    if (this.f295d == -1) {
                        bx.a("File Length is not known!");
                    } else if (this.f295d == -2) {
                        bx.a("File is not access!");
                    } else {
                        this.f294c = this.f295d;
                    }
                    this.f293b = 0L;
                }
                if (this.j != null) {
                    this.j.n();
                }
                if (this.f293b >= this.f294c) {
                    onFinish();
                    return;
                } else {
                    c();
                    this.l.a(this);
                    return;
                }
            }
            if (this.j != null) {
                this.j.a(ca.a.network_exception);
            }
        } catch (AMapException e2) {
            hn.c(e2, "SiteFileFetch", "download");
            ca caVar = this.j;
            if (caVar != null) {
                caVar.a(ca.a.amap_exception);
            }
        } catch (IOException unused) {
            ca caVar2 = this.j;
            if (caVar2 != null) {
                caVar2.a(ca.a.file_io_exception);
            }
        }
    }

    private boolean e() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f292a.b());
        sb.append(File.separator);
        sb.append(this.f292a.c());
        return new File(sb.toString()).length() >= 10;
    }

    private void f() throws AMapException {
        if (gk.f1092a != 1) {
            for (int i = 0; i < 3; i++) {
                try {
                } catch (Throwable th) {
                    hn.c(th, "SiteFileFetch", "authOffLineDownLoad");
                    th.printStackTrace();
                }
                if (gk.a(this.i, er.e())) {
                    return;
                }
            }
        }
    }

    /* JADX INFO: compiled from: NetFileFetch.java */
    private static class b extends dp {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final String f300d;

        @Override // com.amap.api.mapcore.util.iq
        public Map<String, String> getRequestHead() {
            return null;
        }

        @Override // com.amap.api.mapcore.util.iq
        public boolean isSupportIPV6() {
            return false;
        }

        public b(String str) {
            this.f300d = str;
        }

        @Override // com.amap.api.mapcore.util.iq
        public String getURL() {
            return this.f300d;
        }

        @Override // com.amap.api.mapcore.util.iq
        public String getIPV6URL() {
            return getURL();
        }
    }

    private long g() throws IOException {
        Map<String, String> mapC;
        try {
            mapC = ip.b().c(new b(this.f292a.a()), MapsInitializer.getProtocol() == 2);
        } catch (gh e2) {
            e2.printStackTrace();
            mapC = null;
        }
        int i = -1;
        if (mapC != null) {
            for (String str : mapC.keySet()) {
                if ("Content-Length".equalsIgnoreCase(str)) {
                    i = Integer.parseInt(mapC.get(str));
                }
            }
        }
        return i;
    }

    private void h() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (this.f292a == null || jCurrentTimeMillis - this.f298g <= 500) {
            return;
        }
        i();
        this.f298g = jCurrentTimeMillis;
        a(this.f293b);
    }

    private void i() {
        this.f297f.a(this.f292a.e(), this.f292a.d(), this.f295d, this.f293b, this.f294c);
    }

    private void a(long j) {
        ca caVar;
        long j2 = this.f295d;
        if (j2 <= 0 || (caVar = this.j) == null) {
            return;
        }
        caVar.a(j2, j);
        this.f298g = System.currentTimeMillis();
    }

    public void b() {
        it itVar = this.l;
        if (itVar != null) {
            itVar.a();
        }
    }

    @Override // com.amap.api.mapcore.util.il.a
    public void onStop() {
        if (this.n) {
            return;
        }
        ca caVar = this.j;
        if (caVar != null) {
            caVar.p();
        }
        i();
    }

    @Override // com.amap.api.mapcore.util.il.a
    public void onFinish() {
        h();
        ca caVar = this.j;
        if (caVar != null) {
            caVar.o();
        }
        bq bqVar = this.m;
        if (bqVar != null) {
            bqVar.a();
        }
        a aVar = this.f299h;
        if (aVar != null) {
            aVar.c();
        }
    }

    @Override // com.amap.api.mapcore.util.il.a
    public void onException(Throwable th) {
        bq bqVar;
        this.n = true;
        b();
        ca caVar = this.j;
        if (caVar != null) {
            caVar.a(ca.a.network_exception);
        }
        if ((th instanceof IOException) || (bqVar = this.m) == null) {
            return;
        }
        bqVar.a();
    }

    @Override // com.amap.api.mapcore.util.il.a
    public void onDownload(byte[] bArr, long j) {
        try {
            this.m.a(bArr);
            this.f293b = j;
            h();
        } catch (IOException e2) {
            e2.printStackTrace();
            hn.c(e2, "fileAccessI", "fileAccessI.write(byte[] data)");
            ca caVar = this.j;
            if (caVar != null) {
                caVar.a(ca.a.file_io_exception);
            }
            it itVar = this.l;
            if (itVar != null) {
                itVar.a();
            }
        }
    }

    public void a(a aVar) {
        this.f299h = aVar;
    }
}