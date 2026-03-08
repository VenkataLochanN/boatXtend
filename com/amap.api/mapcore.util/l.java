package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.mapcore.util.il;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.Map;

/* JADX INFO: compiled from: AuthTaskDownload.java */
/* JADX INFO: loaded from: classes.dex */
public class l implements il.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    a f1629a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f1630b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private RandomAccessFile f1631c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private it f1632d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f1633e;

    @Override // com.amap.api.mapcore.util.il.a
    public void onStop() {
    }

    public l(Context context, a aVar, gs gsVar) {
        this.f1630b = context.getApplicationContext();
        if (aVar == null) {
            return;
        }
        this.f1629a = aVar;
        this.f1632d = new it(new b(aVar));
        this.f1633e = aVar.c();
    }

    public void a() {
        try {
            if (!b() || this.f1632d == null) {
                return;
            }
            this.f1632d.a(this);
        } catch (Throwable th) {
            hn.c(th, "AuthTaskDownload", "startDownload()");
        }
    }

    private boolean b() {
        c cVarE = this.f1629a.e();
        return (cVarE != null && cVarE.c() && eh.a(this.f1630b, cVarE.a(), cVarE.b(), "").equalsIgnoreCase(this.f1629a.b())) ? false : true;
    }

    @Override // com.amap.api.mapcore.util.il.a
    public void onDownload(byte[] bArr, long j) {
        try {
            if (this.f1631c == null) {
                File file = new File(this.f1633e);
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                this.f1631c = new RandomAccessFile(file, "rw");
            }
            this.f1631c.seek(j);
            this.f1631c.write(bArr);
        } catch (Throwable th) {
            hn.c(th, "AuthTaskDownload", "onDownload()");
        }
    }

    @Override // com.amap.api.mapcore.util.il.a
    public void onFinish() {
        try {
        } catch (Throwable th) {
            hn.c(th, "AuthTaskDownload", "onFinish()");
        }
        if (this.f1631c == null) {
            return;
        }
        try {
            this.f1631c.close();
        } catch (Throwable th2) {
            hn.c(th2, "AuthTaskDownload", "onFinish3");
        }
        String strB = this.f1629a.b();
        String strA = gq.a(this.f1633e);
        if (strA != null && strB.equalsIgnoreCase(strA)) {
            String strD = this.f1629a.d();
            try {
                br brVar = new br();
                File file = new File(this.f1633e);
                brVar.a(file, new File(strD), -1L, bx.a(file), null);
                c cVarE = this.f1629a.e();
                if (cVarE != null && cVarE.c()) {
                    eh.a(this.f1630b, cVarE.a(), cVarE.b(), (Object) strA);
                }
                new File(this.f1633e).delete();
                return;
            } catch (Throwable th3) {
                hn.c(th3, "AuthTaskDownload", "onFinish1");
                return;
            }
        }
        try {
            new File(this.f1633e).delete();
            return;
        } catch (Throwable th4) {
            hn.c(th4, "AuthTaskDownload", "onFinish");
            return;
        }
        hn.c(th, "AuthTaskDownload", "onFinish()");
    }

    @Override // com.amap.api.mapcore.util.il.a
    public void onException(Throwable th) {
        try {
            if (this.f1631c == null) {
                return;
            }
            this.f1631c.close();
        } catch (Throwable th2) {
            hn.c(th2, "AuthTaskDownload", "onException()");
        }
    }

    /* JADX INFO: compiled from: AuthTaskDownload.java */
    static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected String f1641a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        protected String f1642b;

        public c(String str, String str2) {
            this.f1641a = str;
            this.f1642b = str2;
        }

        public String a() {
            return this.f1641a;
        }

        public String b() {
            return this.f1642b;
        }

        public boolean c() {
            return (TextUtils.isEmpty(this.f1641a) || TextUtils.isEmpty(this.f1642b)) ? false : true;
        }
    }

    /* JADX INFO: compiled from: AuthTaskDownload.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected String f1634a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        protected String f1635b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        protected String f1636c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        protected String f1637d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        protected String f1638e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        protected c f1639f;

        public a(String str, String str2, String str3, String str4) {
            this.f1634a = str;
            this.f1635b = str2;
            this.f1636c = str3;
            this.f1637d = str4 + ".tmp";
            this.f1638e = str4;
        }

        public String a() {
            return this.f1634a;
        }

        public String b() {
            return this.f1635b;
        }

        public String c() {
            return this.f1637d;
        }

        public String d() {
            return this.f1638e;
        }

        public void a(c cVar) {
            this.f1639f = cVar;
        }

        public c e() {
            return this.f1639f;
        }
    }

    /* JADX INFO: compiled from: AuthTaskDownload.java */
    static class d extends a {
        public d(String str, String str2, String str3, String str4) {
            super(str, str2, str3, str4);
        }

        public void a(String str, String str2) {
            a(new c(str, str2));
        }
    }

    /* JADX INFO: compiled from: AuthTaskDownload.java */
    static class b extends dp {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final a f1640d;

        @Override // com.amap.api.mapcore.util.dp, com.amap.api.mapcore.util.iq
        public Map<String, String> getParams() {
            return null;
        }

        @Override // com.amap.api.mapcore.util.iq
        public Map<String, String> getRequestHead() {
            return null;
        }

        @Override // com.amap.api.mapcore.util.iq
        public boolean isSupportIPV6() {
            return false;
        }

        b(a aVar) {
            this.f1640d = aVar;
        }

        @Override // com.amap.api.mapcore.util.iq
        public String getURL() {
            a aVar = this.f1640d;
            if (aVar != null) {
                return aVar.a();
            }
            return null;
        }

        @Override // com.amap.api.mapcore.util.iq
        public String getIPV6URL() {
            return getURL();
        }
    }
}