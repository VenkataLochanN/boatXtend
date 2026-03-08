package com.amap.api.mapcore.util;

import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.amap.api.mapcore.util.gj;
import com.amap.api.mapcore.util.ij;
import com.amap.api.maps.AMapException;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.ref.SoftReference;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

/* JADX INFO: compiled from: HttpUrlUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class in {
    private static SoftReference<SSLContext> k;
    private static SoftReference<io> m;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f1375a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1376b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f1377c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private SSLContext f1378d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Proxy f1379e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private volatile boolean f1380f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private long f1381g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private long f1382h;
    private String i;
    private b j;
    private ij.a l;
    private boolean n;
    private String o;

    public static boolean a(int i) {
        return i == 2;
    }

    private void b() {
        try {
            this.i = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
        } catch (Throwable th) {
            hk.a(th, "ht", "ic");
        }
    }

    in(iq iqVar, boolean z) {
        this(iqVar, z, null);
    }

    in(iq iqVar, boolean z, ij.a aVar) {
        this(iqVar.f1399a, iqVar.f1400b, iqVar.f1401c, z, aVar);
        a(iqVar);
    }

    in(int i, int i2, Proxy proxy, boolean z) {
        this(i, i2, proxy, z, null);
    }

    in(int i, int i2, Proxy proxy, boolean z, ij.a aVar) {
        this.f1380f = false;
        this.f1381g = -1L;
        this.f1382h = 0L;
        this.n = false;
        this.o = "";
        this.f1375a = i;
        this.f1376b = i2;
        this.f1379e = proxy;
        this.f1377c = go.a().b(z);
        gj.e();
        if (go.c()) {
            this.f1377c = false;
        }
        this.l = aVar;
        b();
        if (this.f1377c) {
            try {
                if (k == null || k.get() == null) {
                    k = new SoftReference<>(SSLContext.getInstance("TLS"));
                }
            } catch (Throwable unused) {
            }
            SSLContext sSLContext = k != null ? k.get() : null;
            if (sSLContext == null) {
                try {
                    sSLContext = SSLContext.getInstance("TLS");
                } catch (Throwable th) {
                    hk.a(th, "ht", "ne");
                }
            }
            sSLContext.init(null, null, null);
            this.f1378d = sSLContext;
        }
        this.j = new b();
    }

    void a() {
        this.f1380f = true;
    }

    void a(long j) {
        this.f1382h = j;
    }

    void b(long j) {
        this.f1381g = j;
    }

    private String a(String str, Map<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String strA = a(map);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        if (strA != null) {
            stringBuffer.append("?");
            stringBuffer.append(strA);
        }
        return stringBuffer.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01b5 A[Catch: all -> 0x0165, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x0165, blocks: (B:89:0x0160, B:126:0x01b5), top: B:158:0x0013 }] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x01aa A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0191 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0199 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:205:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void a(java.lang.String r20, java.lang.String r21, boolean r22, java.lang.String r23, java.util.Map<java.lang.String, java.lang.String> r24, java.util.Map<java.lang.String, java.lang.String> r25, byte[] r26, com.amap.api.mapcore.util.il.a r27, int r28) {
        /*
            Method dump skipped, instruction units count: 491
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.in.a(java.lang.String, java.lang.String, boolean, java.lang.String, java.util.Map, java.util.Map, byte[], com.amap.api.mapcore.util.il$a, int):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    Map<String, String> a(String str, String str2, boolean z, String str3, Map<String, String> map, Map<String, String> map2, boolean z2, int i) throws gh {
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                String strA = a(str, map2);
                String strA2 = a(str2, map2);
                try {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    a aVarA = a(strA, strA2, z, str3, map, false, i);
                    try {
                        HttpURLConnection httpURLConnection2 = aVarA.f1383a;
                        if (httpURLConnection2.getResponseCode() >= 400) {
                            throw new gh("http读取header失败");
                        }
                        try {
                            a(aVarA, false, jCurrentTimeMillis, System.currentTimeMillis());
                            HashMap map3 = new HashMap();
                            for (int i2 = 0; i2 < 50; i2++) {
                                String headerFieldKey = httpURLConnection2.getHeaderFieldKey(i2);
                                if (headerFieldKey == null) {
                                    break;
                                }
                                map3.put(headerFieldKey.toLowerCase(), httpURLConnection2.getHeaderField(headerFieldKey));
                            }
                            if (httpURLConnection2 != null) {
                                try {
                                    httpURLConnection2.disconnect();
                                } catch (Throwable th) {
                                    hk.a(th, "hth", "mgr");
                                }
                            }
                            return map3;
                        } catch (gh e2) {
                            e = e2;
                            throw e;
                        } catch (InterruptedIOException unused) {
                            throw new gh(AMapException.ERROR_UNKNOWN);
                        } catch (ConnectException unused2) {
                            throw new gh(AMapException.ERROR_CONNECTION);
                        } catch (MalformedURLException unused3) {
                            throw new gh(AMapException.ERROR_URL);
                        } catch (SocketException unused4) {
                            throw new gh(AMapException.ERROR_SOCKET);
                        } catch (SocketTimeoutException unused5) {
                            throw new gh(AMapException.ERROR_SOCKE_TIME_OUT);
                        } catch (UnknownHostException unused6) {
                            throw new gh(AMapException.ERROR_UNKNOW_HOST);
                        } catch (IOException unused7) {
                            throw new gh(AMapException.ERROR_IO);
                        } catch (Throwable th2) {
                            th = th2;
                            th.printStackTrace();
                            throw new gh(AMapException.ERROR_UNKNOWN);
                        }
                    } catch (gh e3) {
                        throw e3;
                    } catch (InterruptedIOException unused8) {
                        throw new gh(AMapException.ERROR_UNKNOWN);
                    } catch (ConnectException unused9) {
                        throw new gh(AMapException.ERROR_CONNECTION);
                    } catch (MalformedURLException unused10) {
                        throw new gh(AMapException.ERROR_URL);
                    } catch (SocketException unused11) {
                        throw new gh(AMapException.ERROR_SOCKET);
                    } catch (SocketTimeoutException unused12) {
                        throw new gh(AMapException.ERROR_SOCKE_TIME_OUT);
                    } catch (UnknownHostException unused13) {
                        throw new gh(AMapException.ERROR_UNKNOW_HOST);
                    } catch (IOException unused14) {
                        throw new gh(AMapException.ERROR_IO);
                    } catch (Throwable th3) {
                        th = th3;
                        th.printStackTrace();
                        throw new gh(AMapException.ERROR_UNKNOWN);
                    }
                } catch (gh e4) {
                    throw e4;
                } catch (InterruptedIOException unused15) {
                } catch (ConnectException unused16) {
                } catch (MalformedURLException unused17) {
                } catch (SocketException unused18) {
                } catch (SocketTimeoutException unused19) {
                } catch (UnknownHostException unused20) {
                } catch (IOException unused21) {
                } catch (Throwable th4) {
                    th = th4;
                }
            } catch (gh e5) {
                e = e5;
            } catch (ConnectException unused22) {
            } catch (MalformedURLException unused23) {
            } catch (SocketException unused24) {
            } catch (SocketTimeoutException unused25) {
            } catch (InterruptedIOException unused26) {
            } catch (UnknownHostException unused27) {
            } catch (IOException unused28) {
            } catch (Throwable th5) {
                th = th5;
            }
        } catch (Throwable th6) {
            if (1 != 0) {
                try {
                    a((a) str);
                    b((a) str);
                } catch (Throwable unused29) {
                }
            }
            if (0 == 0) {
                throw th6;
            }
            try {
                httpURLConnection.disconnect();
                throw th6;
            } catch (Throwable th7) {
                hk.a(th7, "hth", "mgr");
                throw th6;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    is b(String str, String str2, boolean z, String str3, Map<String, String> map, Map<String, String> map2, boolean z2, int i) throws gh {
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                String strA = a(str, map2);
                String strA2 = a(str2, map2);
                is isVarA = im.a(strA, strA2, this.o);
                if (isVarA != null) {
                    return isVarA;
                }
                a aVarA = a(strA, strA2, z, str3, map, false, i, true);
                try {
                    httpURLConnection = aVarA.f1383a;
                    is isVarA2 = a(aVarA, z2);
                    if (httpURLConnection != null) {
                        try {
                            httpURLConnection.disconnect();
                        } catch (Throwable th) {
                            hk.a(th, "ht", "mgr");
                        }
                    }
                    return isVarA2;
                } catch (gh e2) {
                    e = e2;
                } catch (InterruptedIOException unused) {
                    throw new gh(AMapException.ERROR_UNKNOWN);
                } catch (ConnectException unused2) {
                    throw new gh(AMapException.ERROR_CONNECTION);
                } catch (MalformedURLException unused3) {
                    throw new gh(AMapException.ERROR_URL);
                } catch (SocketException unused4) {
                    throw new gh(AMapException.ERROR_SOCKET);
                } catch (SocketTimeoutException unused5) {
                    throw new gh(AMapException.ERROR_SOCKE_TIME_OUT);
                } catch (UnknownHostException unused6) {
                    throw new gh(AMapException.ERROR_UNKNOW_HOST);
                } catch (IOException unused7) {
                    throw new gh(AMapException.ERROR_IO);
                } catch (Throwable th2) {
                    th = th2;
                    th.printStackTrace();
                    throw new gh(AMapException.ERROR_UNKNOWN);
                }
            } catch (gh e3) {
                e = e3;
            } catch (InterruptedIOException unused8) {
            } catch (ConnectException unused9) {
            } catch (MalformedURLException unused10) {
            } catch (SocketException unused11) {
            } catch (SocketTimeoutException unused12) {
            } catch (UnknownHostException unused13) {
            } catch (IOException unused14) {
            } catch (Throwable th3) {
                th = th3;
            }
            if (e.f()) {
            }
            throw e;
        } catch (Throwable th4) {
            if (1 != 0) {
                try {
                    gj.a(i);
                    b((a) null);
                } catch (Throwable unused15) {
                }
            }
            if (str == 0) {
                throw th4;
            }
            try {
                str.disconnect();
                throw th4;
            } catch (Throwable th5) {
                hk.a(th5, "ht", "mgr");
                throw th4;
            }
        }
    }

    public static int a(int i, iq iqVar) {
        try {
            if (gj.c()) {
                return 4;
            }
            if ((iqVar == null || iqVar.isSupportIPV6()) && i == 2) {
                if (i == b(i)) {
                    return 2;
                }
            }
        } catch (Throwable th) {
            hn.c(th, "htu", "gt");
        }
        return 1;
    }

    public static int b(int i) {
        if (i != 2 || gj.a()) {
            return i;
        }
        return 1;
    }

    private String a(String str, String str2, int i) {
        if (i == 2 || i == 4) {
            try {
                if (!TextUtils.isEmpty(str2)) {
                    return str2;
                }
            } catch (Throwable unused) {
            }
        }
        return str;
    }

    private void c(int i) {
        try {
            if (i == 2) {
                this.f1375a = Math.max(this.f1375a - 5000, 5000);
                this.f1376b = Math.max(this.f1376b - 5000, 5000);
            } else {
                if (i != 3) {
                    return;
                }
                this.f1375a = 5000;
                this.f1376b = 5000;
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x00d4: MOVE (r16 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:74:0x00d4 */
    is a(String str, String str2, boolean z, String str3, Map<String, String> map, byte[] bArr, boolean z2, int i) throws gh {
        a aVar;
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                is isVarA = im.a(str, str2, this.o);
                if (isVarA != null) {
                    return isVarA;
                }
                a aVarA = a(str, str2, z, str3, map, true, i, true);
                try {
                    HttpURLConnection httpURLConnection2 = aVarA.f1383a;
                    if (bArr != null && bArr.length > 0) {
                        DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection2.getOutputStream());
                        dataOutputStream.write(bArr);
                        dataOutputStream.close();
                    }
                    is isVarA2 = a(aVarA, z2);
                    if (httpURLConnection2 != null) {
                        try {
                            httpURLConnection2.disconnect();
                        } catch (Throwable th) {
                            hk.a(th, "ht", "mPt");
                        }
                    }
                    return isVarA2;
                } catch (gh e2) {
                    e = e2;
                } catch (InterruptedIOException unused) {
                    throw new gh(AMapException.ERROR_UNKNOWN);
                } catch (ConnectException e3) {
                    e = e3;
                    e.printStackTrace();
                    throw new gh(AMapException.ERROR_CONNECTION);
                } catch (MalformedURLException e4) {
                    e = e4;
                    e.printStackTrace();
                    throw new gh(AMapException.ERROR_URL);
                } catch (SocketException e5) {
                    e = e5;
                    e.printStackTrace();
                    throw new gh(AMapException.ERROR_SOCKET);
                } catch (SocketTimeoutException e6) {
                    e = e6;
                    e.printStackTrace();
                    throw new gh(AMapException.ERROR_SOCKE_TIME_OUT);
                } catch (UnknownHostException e7) {
                    e = e7;
                    e.printStackTrace();
                    throw new gh(AMapException.ERROR_UNKNOW_HOST);
                } catch (IOException e8) {
                    e = e8;
                    e.printStackTrace();
                    throw new gh(AMapException.ERROR_IO);
                } catch (Throwable th2) {
                    th = th2;
                    hk.a(th, "ht", "mPt");
                    throw new gh(AMapException.ERROR_UNKNOWN);
                }
            } catch (gh e9) {
                e = e9;
            } catch (InterruptedIOException unused2) {
            } catch (ConnectException e10) {
                e = e10;
            } catch (MalformedURLException e11) {
                e = e11;
            } catch (SocketException e12) {
                e = e12;
            } catch (SocketTimeoutException e13) {
                e = e13;
            } catch (UnknownHostException e14) {
                e = e14;
            } catch (IOException e15) {
                e = e15;
            } catch (Throwable th3) {
                th = th3;
            }
            if (e.f()) {
            }
            hk.a(e, "ht", "mPt");
            throw e;
        } catch (Throwable th4) {
            if (1 != 0) {
                try {
                    gj.a(i);
                    b(aVar);
                } catch (Throwable unused3) {
                }
            }
            if (0 == 0) {
                throw th4;
            }
            try {
                httpURLConnection.disconnect();
                throw th4;
            } catch (Throwable th5) {
                hk.a(th5, "ht", "mPt");
                throw th4;
            }
        }
    }

    private String a(int i, String str, Map<String, String> map) {
        String str2 = i != 1 ? "" : ij.f1354b;
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        Uri uri = Uri.parse(str);
        String host = uri.getHost();
        String string = uri.buildUpon().encodedAuthority(str2).build().toString();
        if (map != null) {
            map.put("targetHost", host);
        }
        if (this.f1377c) {
            this.j.b(str2);
        }
        return string;
    }

    /* JADX INFO: compiled from: HttpUrlUtil.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public HttpURLConnection f1383a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1384b;

        public a(HttpURLConnection httpURLConnection, int i) {
            this.f1383a = httpURLConnection;
            this.f1384b = i;
        }
    }

    private io c() {
        try {
            if (m == null || m.get() == null) {
                m = new SoftReference<>(new io(gj.f1058c, this.f1378d));
            }
            io ioVar = k != null ? m.get() : null;
            return ioVar == null ? new io(gj.f1058c, this.f1378d) : ioVar;
        } catch (Throwable th) {
            hn.c(th, "ht", "gsf");
            return null;
        }
    }

    a a(String str, String str2, boolean z, String str3, Map<String, String> map, boolean z2, int i) throws IOException, gh {
        return a(str, str2, z, str3, map, z2, i, false);
    }

    a a(String str, String str2, boolean z, String str3, Map<String, String> map, boolean z2, int i, boolean z3) throws IOException, gh {
        HttpURLConnection httpURLConnection;
        io ioVarC;
        String strA = im.a(a(str, str2, i), this.o);
        c(i);
        if (map == null) {
            map = new HashMap<>();
        }
        c cVarA = this.j.a();
        if (z && !TextUtils.isEmpty(str3)) {
            cVarA = this.j.a(str3);
        }
        if (z3 && !strA.contains("/v3/iasdkauth") && !TextUtils.isEmpty(this.o) && gj.a(this.o)) {
            this.n = true;
            map.put("lct", String.valueOf(gj.c(this.o)));
        }
        String strA2 = a(ij.f1353a, strA, map);
        if (this.f1377c) {
            strA2 = go.a(strA2);
        }
        URL url = new URL(strA2);
        ij.a aVar = this.l;
        URLConnection uRLConnectionA = aVar != null ? aVar.a(this.f1379e, url) : null;
        if (uRLConnectionA == null) {
            Proxy proxy = this.f1379e;
            if (proxy != null) {
                uRLConnectionA = url.openConnection(proxy);
            } else {
                uRLConnectionA = url.openConnection();
            }
        }
        if (this.f1377c) {
            httpURLConnection = (HttpsURLConnection) uRLConnectionA;
            if (gj.f.f1086a && (ioVarC = c()) != null) {
                ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(ioVarC);
                ioVarC.a();
            } else {
                ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(this.f1378d.getSocketFactory());
            }
            ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(cVarA);
        } else {
            httpURLConnection = (HttpURLConnection) uRLConnectionA;
        }
        if (Build.VERSION.SDK != null && Build.VERSION.SDK_INT > 13) {
            httpURLConnection.setRequestProperty("Connection", "close");
        }
        a(map, httpURLConnection);
        if (z2) {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
        } else {
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
        }
        return new a(httpURLConnection, i);
    }

    private String a(HttpURLConnection httpURLConnection) {
        List<String> list;
        if (httpURLConnection == null) {
            return "";
        }
        try {
            Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
            if (headerFields != null && (list = headerFields.get("gsid")) != null && list.size() > 0) {
                return list.get(0);
            }
        } catch (Throwable unused) {
        }
        return "";
    }

    private void a(a aVar) {
        if (aVar == null) {
            return;
        }
        gj.a(aVar.f1384b);
    }

    private void b(a aVar) {
        a(aVar, true, 0L, 0L);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(com.amap.api.mapcore.util.in.a r10, boolean r11, long r12, long r14) {
        /*
            r9 = this;
            r0 = 0
            r1 = 0
            java.net.HttpURLConnection r2 = r10.f1383a     // Catch: java.lang.Throwable -> L21
            java.net.URL r2 = r2.getURL()     // Catch: java.lang.Throwable -> L21
            java.lang.String r1 = r2.toString()     // Catch: java.lang.Throwable -> L21
            int r2 = r10.f1384b     // Catch: java.lang.Throwable -> L21
            r3 = 3
            r4 = 1
            if (r2 != r3) goto L14
            r2 = r4
            goto L15
        L14:
            r2 = r0
        L15:
            int r3 = r10.f1384b     // Catch: java.lang.Throwable -> L22
            r5 = 2
            if (r3 == r5) goto L1f
            int r10 = r10.f1384b     // Catch: java.lang.Throwable -> L22
            r3 = 4
            if (r10 != r3) goto L22
        L1f:
            r0 = r4
            goto L22
        L21:
            r2 = r0
        L22:
            r4 = r0
            r3 = r1
            r5 = r2
            boolean r10 = android.text.TextUtils.isEmpty(r3)
            if (r10 == 0) goto L2c
            return
        L2c:
            r0 = 0
            long r14 = r14 - r12
            long r7 = java.lang.Math.max(r0, r14)
            r6 = r11
            com.amap.api.mapcore.util.gj.a(r3, r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.in.a(com.amap.api.mapcore.util.in$a, boolean, long, long):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0192 A[Catch: all -> 0x01ad, TryCatch #9 {all -> 0x01ad, blocks: (B:99:0x0185, B:101:0x0192, B:103:0x019c, B:105:0x01a8, B:106:0x01ac), top: B:141:0x0185 }] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01bc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01c7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01b1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01d2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:166:? A[ADDED_TO_REGION, Catch: all -> 0x01ad, REMOVE, SYNTHETIC, TRY_LEAVE, TryCatch #9 {all -> 0x01ad, blocks: (B:99:0x0185, B:101:0x0192, B:103:0x019c, B:105:0x01a8, B:106:0x01ac), top: B:141:0x0185 }] */
    /* JADX WARN: Removed duplicated region for block: B:169:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.amap.api.mapcore.util.is a(com.amap.api.mapcore.util.in.a r20, boolean r21) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 476
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.in.a(com.amap.api.mapcore.util.in$a, boolean):com.amap.api.mapcore.util.is");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0073  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean a(java.util.Map<java.lang.String, java.util.List<java.lang.String>> r7, boolean r8) {
        /*
            r6 = this;
            java.lang.String r0 = "#"
            java.lang.String r1 = "lct"
            r2 = 0
            java.lang.String r3 = "sc"
            java.lang.Object r3 = r7.get(r3)     // Catch: java.lang.Throwable -> L7b
            java.util.List r3 = (java.util.List) r3     // Catch: java.lang.Throwable -> L7b
            r4 = 1
            if (r3 == 0) goto L3c
            int r5 = r3.size()     // Catch: java.lang.Throwable -> L7b
            if (r5 <= 0) goto L3c
            java.lang.Object r3 = r3.get(r2)     // Catch: java.lang.Throwable -> L7b
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Throwable -> L7b
            boolean r5 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Throwable -> L7b
            if (r5 != 0) goto L3c
            boolean r5 = r3.contains(r0)     // Catch: java.lang.Throwable -> L7b
            if (r5 != 0) goto L2a
        L28:
            r0 = r4
            goto L3d
        L2a:
            java.lang.String[] r0 = r3.split(r0)     // Catch: java.lang.Throwable -> L7b
            int r3 = r0.length     // Catch: java.lang.Throwable -> L7b
            if (r3 <= r4) goto L3c
            java.lang.String r3 = "1"
            r0 = r0[r4]     // Catch: java.lang.Throwable -> L7b
            boolean r0 = r3.equals(r0)     // Catch: java.lang.Throwable -> L7b
            if (r0 == 0) goto L3c
            goto L28
        L3c:
            r0 = r2
        L3d:
            if (r0 != 0) goto L40
            return r2
        L40:
            if (r8 == 0) goto L7a
            if (r0 == 0) goto L73
            boolean r8 = r7.containsKey(r1)     // Catch: java.lang.Throwable -> L7b
            if (r8 == 0) goto L73
            java.lang.Object r7 = r7.get(r1)     // Catch: java.lang.Throwable -> L7b
            java.util.List r7 = (java.util.List) r7     // Catch: java.lang.Throwable -> L7b
            if (r7 == 0) goto L73
            int r8 = r7.size()     // Catch: java.lang.Throwable -> L7b
            if (r8 <= 0) goto L73
            java.lang.Object r7 = r7.get(r2)     // Catch: java.lang.Throwable -> L7b
            java.lang.String r7 = (java.lang.String) r7     // Catch: java.lang.Throwable -> L7b
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Throwable -> L7b
            if (r8 != 0) goto L73
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch: java.lang.Throwable -> L7b
            long r7 = r7.longValue()     // Catch: java.lang.Throwable -> L7b
            java.lang.String r1 = r6.o     // Catch: java.lang.Throwable -> L7b
            boolean r7 = com.amap.api.mapcore.util.gj.a(r1, r7)     // Catch: java.lang.Throwable -> L7b
            goto L74
        L73:
            r7 = r2
        L74:
            if (r0 == 0) goto L7b
            if (r7 == 0) goto L7b
            r2 = r4
            goto L7b
        L7a:
            r2 = r0
        L7b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.in.a(java.util.Map, boolean):boolean");
    }

    private void a(Map<String, String> map, HttpURLConnection httpURLConnection) {
        if (map != null) {
            for (String str : map.keySet()) {
                httpURLConnection.addRequestProperty(str, map.get(str));
            }
        }
        try {
            httpURLConnection.addRequestProperty("csid", this.i);
        } catch (Throwable th) {
            hk.a(th, "ht", "adh");
        }
        httpURLConnection.setConnectTimeout(this.f1375a);
        httpURLConnection.setReadTimeout(this.f1376b);
    }

    /* JADX INFO: compiled from: HttpUrlUtil.java */
    private static class c implements HostnameVerifier {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f1387a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f1388b;

        private c() {
        }

        public void a(String str) {
            this.f1387a = str;
        }

        public void b(String str) {
            this.f1388b = str;
        }

        public String a() {
            return this.f1388b;
        }

        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            HostnameVerifier defaultHostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
            if (!TextUtils.isEmpty(this.f1387a)) {
                return this.f1387a.equals(str);
            }
            if (!TextUtils.isEmpty(this.f1388b)) {
                return defaultHostnameVerifier.verify(this.f1388b, sSLSession);
            }
            return defaultHostnameVerifier.verify(str, sSLSession);
        }
    }

    /* JADX INFO: compiled from: HttpUrlUtil.java */
    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Vector<c> f1385a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private volatile c f1386b;

        private b() {
            this.f1385a = new Vector<>();
            this.f1386b = new c();
        }

        public c a() {
            return this.f1386b;
        }

        public c a(String str) {
            if (TextUtils.isEmpty(str)) {
                return this.f1386b;
            }
            for (int i = 0; i < this.f1385a.size(); i++) {
                c cVar = this.f1385a.get(i);
                if (cVar != null && cVar.a().equals(str)) {
                    return cVar;
                }
            }
            c cVar2 = new c();
            cVar2.b(str);
            this.f1385a.add(cVar2);
            return cVar2;
        }

        public void b(String str) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.f1386b.a(str);
        }
    }

    static String a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value == null) {
                value = "";
            }
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(URLEncoder.encode(key));
            sb.append("=");
            sb.append(URLEncoder.encode(value));
        }
        return sb.toString();
    }

    private void a(iq iqVar) {
        if (iqVar == null) {
            return;
        }
        try {
            if (iqVar instanceof ik) {
                this.o = a(((ik) iqVar).j());
            } else {
                this.o = b(iqVar.getRequestHead());
            }
        } catch (Throwable th) {
            hk.a(th, "ht", "pnfr");
        }
    }

    private String b(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        try {
            if (map.containsKey("platinfo")) {
                return a(map.get("platinfo"));
            }
            return null;
        } catch (Throwable th) {
            hk.a(th, "ht", "pnfh");
            return null;
        }
    }

    private String a(String str) {
        String str2;
        String strTrim = "";
        try {
            if (!TextUtils.isEmpty(str)) {
                String[] strArrSplit = str.split("&");
                if (strArrSplit.length > 1) {
                    int length = strArrSplit.length;
                    int i = 0;
                    String str3 = "";
                    while (true) {
                        if (i >= length) {
                            str2 = "";
                            break;
                        }
                        str2 = strArrSplit[i];
                        if (str2.contains("sdkversion")) {
                            str3 = str2;
                        }
                        if (str2.contains("product")) {
                            break;
                        }
                        i++;
                    }
                    if (!TextUtils.isEmpty(str2)) {
                        String[] strArrSplit2 = str2.split("=");
                        if (strArrSplit2.length > 1) {
                            strTrim = strArrSplit2[1].trim();
                            if (!TextUtils.isEmpty(str3) && TextUtils.isEmpty(hj.a(strTrim))) {
                                String[] strArrSplit3 = str3.split("=");
                                if (strArrSplit3.length > 1) {
                                    hj.a(strTrim, strArrSplit3[1].trim());
                                }
                            }
                        }
                    }
                }
            }
        } catch (Throwable th) {
            hk.a(th, "ht", "pnfp");
        }
        return strTrim;
    }
}