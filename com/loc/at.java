package com.loc;

import android.text.TextUtils;
import com.loc.aq;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

/* JADX INFO: compiled from: HttpUrlUtil.java */
/* JADX INFO: loaded from: classes3.dex */
public final class at {
    private static SoftReference<SSLContext> k;
    private static SoftReference<au> m;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f4789a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f4790b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f4791c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private SSLContext f4792d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Proxy f4793e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private volatile boolean f4794f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private long f4795g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private long f4796h;
    private String i;
    private b j;
    private aq.a l;
    private String n;
    private boolean o;
    private String p;

    /* JADX INFO: compiled from: HttpUrlUtil.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public HttpURLConnection f4797a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f4798b;

        public a(HttpURLConnection httpURLConnection, int i) {
            this.f4797a = httpURLConnection;
            this.f4798b = i;
        }
    }

    /* JADX INFO: compiled from: HttpUrlUtil.java */
    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Vector<c> f4799a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private volatile c f4800b;

        private b() {
            this.f4799a = new Vector<>();
            this.f4800b = new c((byte) 0);
        }

        /* synthetic */ b(byte b2) {
            this();
        }

        public final c a() {
            return this.f4800b;
        }

        public final c a(String str) {
            if (TextUtils.isEmpty(str)) {
                return this.f4800b;
            }
            byte b2 = 0;
            for (int i = 0; i < this.f4799a.size(); i++) {
                c cVar = this.f4799a.get(i);
                if (cVar != null && cVar.a().equals(str)) {
                    return cVar;
                }
            }
            c cVar2 = new c(b2);
            cVar2.b(str);
            this.f4799a.add(cVar2);
            return cVar2;
        }

        public final void b(String str) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.f4800b.a(str);
        }
    }

    /* JADX INFO: compiled from: HttpUrlUtil.java */
    private static class c implements HostnameVerifier {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f4801a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f4802b;

        private c() {
        }

        /* synthetic */ c(byte b2) {
            this();
        }

        public final String a() {
            return this.f4802b;
        }

        public final void a(String str) {
            this.f4801a = str;
        }

        public final void b(String str) {
            this.f4802b = str;
        }

        @Override // javax.net.ssl.HostnameVerifier
        public final boolean verify(String str, SSLSession sSLSession) {
            HostnameVerifier defaultHostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
            return !TextUtils.isEmpty(this.f4801a) ? this.f4801a.equals(str) : !TextUtils.isEmpty(this.f4802b) ? defaultHostnameVerifier.verify(this.f4802b, sSLSession) : defaultHostnameVerifier.verify(str, sSLSession);
        }
    }

    private at(int i, int i2, Proxy proxy, boolean z) {
        byte b2 = 0;
        this.f4794f = false;
        this.f4795g = -1L;
        this.f4796h = 0L;
        this.o = false;
        this.p = "";
        this.f4789a = i;
        this.f4790b = i2;
        this.f4793e = proxy;
        this.f4791c = p.a().b(z);
        l.d();
        if (p.b()) {
            this.f4791c = false;
        }
        this.l = null;
        try {
            this.i = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
        } catch (Throwable th) {
            y.a(th, "ht", "ic");
        }
        if (this.f4791c) {
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
                } catch (Throwable th2) {
                    y.a(th2, "ht", "ne");
                }
            }
            sSLContext.init(null, null, null);
            this.f4792d = sSLContext;
        }
        this.j = new b(b2);
    }

    at(av avVar, boolean z) {
        this(avVar, z, (byte) 0);
    }

    private at(av avVar, boolean z, byte b2) {
        this(avVar.f4806c, avVar.f4807d, avVar.f4808e, z);
        if (avVar == null) {
            return;
        }
        try {
            this.p = avVar.p();
            if (TextUtils.isEmpty(this.p)) {
                if (avVar instanceof ar) {
                    this.p = a(((ar) avVar).k());
                } else {
                    this.p = b(avVar.b());
                }
            }
        } catch (Throwable th) {
            y.a(th, "ht", "pnfr");
        }
    }

    public static int a(av avVar) {
        try {
            if (l.b()) {
                return 4;
            }
            if (avVar != null && !avVar.i()) {
                return 1;
            }
            if (2 == (!l.a() ? (char) 1 : (char) 2)) {
                return 2;
            }
        } catch (Throwable th) {
            ab.b(th, "htu", "gt");
        }
        return 1;
    }

    private au a() {
        try {
            if (m == null || m.get() == null) {
                m = new SoftReference<>(new au(l.f5246c, this.f4792d));
            }
            au auVar = k != null ? m.get() : null;
            return auVar == null ? new au(l.f5246c, this.f4792d) : auVar;
        } catch (Throwable th) {
            ab.b(th, "ht", "gsf");
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x01b8 A[Catch: all -> 0x01d2, TryCatch #10 {all -> 0x01d2, blocks: (B:108:0x01ab, B:110:0x01b8, B:112:0x01c2, B:114:0x01ce, B:115:0x01d1), top: B:150:0x01ab }] */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01e5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01f0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01da A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:160:0x01fb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:172:? A[ADDED_TO_REGION, Catch: all -> 0x01d2, REMOVE, SYNTHETIC, TRY_LEAVE, TryCatch #10 {all -> 0x01d2, blocks: (B:108:0x01ab, B:110:0x01b8, B:112:0x01c2, B:114:0x01ce, B:115:0x01d1), top: B:150:0x01ab }] */
    /* JADX WARN: Removed duplicated region for block: B:175:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.loc.aw a(com.loc.at.a r19) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 517
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.at.a(com.loc.at$a):com.loc.aw");
    }

    private static String a(String str) {
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
                            if (!TextUtils.isEmpty(str3) && TextUtils.isEmpty(v.a(strTrim))) {
                                String[] strArrSplit3 = str3.split("=");
                                if (strArrSplit3.length > 1) {
                                    v.a(strTrim, strArrSplit3[1].trim());
                                }
                            }
                        }
                    }
                }
            }
        } catch (Throwable th) {
            y.a(th, "ht", "pnfp");
        }
        return strTrim;
    }

    private static String a(String str, String str2, int i) {
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

    /* JADX WARN: Removed duplicated region for block: B:14:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void a(com.loc.at.a r9, boolean r10, long r11, long r13) {
        /*
            r0 = 0
            java.net.HttpURLConnection r1 = r9.f4797a     // Catch: java.lang.Throwable -> L20
            java.net.URL r1 = r1.getURL()     // Catch: java.lang.Throwable -> L20
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L20
            int r2 = r9.f4798b     // Catch: java.lang.Throwable -> L21
            r3 = 3
            r4 = 1
            if (r2 != r3) goto L13
            r2 = r4
            goto L14
        L13:
            r2 = r0
        L14:
            int r3 = r9.f4798b     // Catch: java.lang.Throwable -> L22
            r5 = 2
            if (r3 == r5) goto L1e
            int r9 = r9.f4798b     // Catch: java.lang.Throwable -> L22
            r3 = 4
            if (r9 != r3) goto L22
        L1e:
            r0 = r4
            goto L22
        L20:
            r1 = 0
        L21:
            r2 = r0
        L22:
            r4 = r0
            r3 = r1
            r5 = r2
            boolean r9 = android.text.TextUtils.isEmpty(r3)
            if (r9 == 0) goto L2c
            return
        L2c:
            r0 = 0
            long r13 = r13 - r11
            long r7 = java.lang.Math.max(r0, r13)
            r6 = r10
            com.loc.l.a(r3, r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.at.a(com.loc.at$a, boolean, long, long):void");
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
            y.a(th, "ht", "adh");
        }
        httpURLConnection.setConnectTimeout(this.f4789a);
        httpURLConnection.setReadTimeout(this.f4790b);
    }

    public static boolean a(int i) {
        return i == 2;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean a(java.util.Map<java.lang.String, java.util.List<java.lang.String>> r7, boolean r8) {
        /*
            r6 = this;
            java.lang.String r0 = "#"
            java.lang.String r1 = "lct"
            r2 = 1
            r3 = 0
            java.lang.String r4 = "sc"
            java.lang.Object r4 = r7.get(r4)     // Catch: java.lang.Throwable -> L72
            java.util.List r4 = (java.util.List) r4     // Catch: java.lang.Throwable -> L72
            if (r4 == 0) goto L3c
            int r5 = r4.size()     // Catch: java.lang.Throwable -> L72
            if (r5 <= 0) goto L3c
            java.lang.Object r4 = r4.get(r3)     // Catch: java.lang.Throwable -> L72
            java.lang.String r4 = (java.lang.String) r4     // Catch: java.lang.Throwable -> L72
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Throwable -> L72
            if (r5 != 0) goto L3c
            boolean r5 = r4.contains(r0)     // Catch: java.lang.Throwable -> L72
            if (r5 != 0) goto L2a
        L28:
            r0 = r2
            goto L3d
        L2a:
            java.lang.String[] r0 = r4.split(r0)     // Catch: java.lang.Throwable -> L72
            int r4 = r0.length     // Catch: java.lang.Throwable -> L72
            if (r4 <= r2) goto L3c
            java.lang.String r4 = "1"
            r0 = r0[r2]     // Catch: java.lang.Throwable -> L72
            boolean r0 = r4.equals(r0)     // Catch: java.lang.Throwable -> L72
            if (r0 == 0) goto L3c
            goto L28
        L3c:
            r0 = r3
        L3d:
            if (r0 != 0) goto L40
            return r3
        L40:
            if (r8 == 0) goto L73
            boolean r8 = r7.containsKey(r1)     // Catch: java.lang.Throwable -> L72
            if (r8 == 0) goto L72
            java.lang.Object r7 = r7.get(r1)     // Catch: java.lang.Throwable -> L72
            java.util.List r7 = (java.util.List) r7     // Catch: java.lang.Throwable -> L72
            if (r7 == 0) goto L72
            int r8 = r7.size()     // Catch: java.lang.Throwable -> L72
            if (r8 <= 0) goto L72
            java.lang.Object r7 = r7.get(r3)     // Catch: java.lang.Throwable -> L72
            java.lang.String r7 = (java.lang.String) r7     // Catch: java.lang.Throwable -> L72
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Throwable -> L72
            if (r8 != 0) goto L72
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch: java.lang.Throwable -> L72
            long r7 = r7.longValue()     // Catch: java.lang.Throwable -> L72
            java.lang.String r0 = r6.p     // Catch: java.lang.Throwable -> L72
            boolean r7 = com.loc.l.a(r0, r7)     // Catch: java.lang.Throwable -> L72
            r2 = r7
            goto L73
        L72:
            r2 = r3
        L73:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.at.a(java.util.Map, boolean):boolean");
    }

    private static String b(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        try {
            if (map.containsKey("platinfo")) {
                return a(map.get("platinfo"));
            }
            return null;
        } catch (Throwable th) {
            y.a(th, "ht", "pnfh");
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0160 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x003d A[Catch: all -> 0x0179, j -> 0x0184, IOException -> 0x018f, InterruptedIOException -> 0x019c, SocketTimeoutException -> 0x01a3, SocketException -> 0x01b0, UnknownHostException -> 0x01bd, MalformedURLException -> 0x01ca, ConnectException -> 0x01d7, TRY_ENTER, TryCatch #9 {j -> 0x0184, InterruptedIOException -> 0x019c, ConnectException -> 0x01d7, MalformedURLException -> 0x01ca, SocketException -> 0x01b0, SocketTimeoutException -> 0x01a3, UnknownHostException -> 0x01bd, IOException -> 0x018f, all -> 0x0179, blocks: (B:3:0x0008, B:6:0x0011, B:15:0x003d, B:16:0x0042, B:18:0x004c, B:20:0x0052, B:21:0x0058, B:23:0x0060, B:25:0x0068, B:27:0x0070, B:28:0x0081, B:32:0x008a, B:34:0x0090, B:36:0x00aa, B:37:0x00af, B:39:0x00b3, B:40:0x00b8, B:42:0x00bc, B:43:0x00c0, B:45:0x00c9, B:48:0x00d3, B:50:0x00d7, B:51:0x00de, B:52:0x00e2, B:54:0x00e6, B:56:0x00ec, B:58:0x00f2, B:62:0x0113, B:64:0x011c, B:66:0x0120, B:68:0x0126, B:69:0x012d, B:59:0x00fc, B:60:0x0105, B:61:0x0109, B:63:0x011a, B:31:0x0088), top: B:145:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0088 A[Catch: all -> 0x0179, j -> 0x0184, IOException -> 0x018f, InterruptedIOException -> 0x019c, SocketTimeoutException -> 0x01a3, SocketException -> 0x01b0, UnknownHostException -> 0x01bd, MalformedURLException -> 0x01ca, ConnectException -> 0x01d7, TryCatch #9 {j -> 0x0184, InterruptedIOException -> 0x019c, ConnectException -> 0x01d7, MalformedURLException -> 0x01ca, SocketException -> 0x01b0, SocketTimeoutException -> 0x01a3, UnknownHostException -> 0x01bd, IOException -> 0x018f, all -> 0x0179, blocks: (B:3:0x0008, B:6:0x0011, B:15:0x003d, B:16:0x0042, B:18:0x004c, B:20:0x0052, B:21:0x0058, B:23:0x0060, B:25:0x0068, B:27:0x0070, B:28:0x0081, B:32:0x008a, B:34:0x0090, B:36:0x00aa, B:37:0x00af, B:39:0x00b3, B:40:0x00b8, B:42:0x00bc, B:43:0x00c0, B:45:0x00c9, B:48:0x00d3, B:50:0x00d7, B:51:0x00de, B:52:0x00e2, B:54:0x00e6, B:56:0x00ec, B:58:0x00f2, B:62:0x0113, B:64:0x011c, B:66:0x0120, B:68:0x0126, B:69:0x012d, B:59:0x00fc, B:60:0x0105, B:61:0x0109, B:63:0x011a, B:31:0x0088), top: B:145:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0090 A[Catch: all -> 0x0179, j -> 0x0184, IOException -> 0x018f, InterruptedIOException -> 0x019c, SocketTimeoutException -> 0x01a3, SocketException -> 0x01b0, UnknownHostException -> 0x01bd, MalformedURLException -> 0x01ca, ConnectException -> 0x01d7, TryCatch #9 {j -> 0x0184, InterruptedIOException -> 0x019c, ConnectException -> 0x01d7, MalformedURLException -> 0x01ca, SocketException -> 0x01b0, SocketTimeoutException -> 0x01a3, UnknownHostException -> 0x01bd, IOException -> 0x018f, all -> 0x0179, blocks: (B:3:0x0008, B:6:0x0011, B:15:0x003d, B:16:0x0042, B:18:0x004c, B:20:0x0052, B:21:0x0058, B:23:0x0060, B:25:0x0068, B:27:0x0070, B:28:0x0081, B:32:0x008a, B:34:0x0090, B:36:0x00aa, B:37:0x00af, B:39:0x00b3, B:40:0x00b8, B:42:0x00bc, B:43:0x00c0, B:45:0x00c9, B:48:0x00d3, B:50:0x00d7, B:51:0x00de, B:52:0x00e2, B:54:0x00e6, B:56:0x00ec, B:58:0x00f2, B:62:0x0113, B:64:0x011c, B:66:0x0120, B:68:0x0126, B:69:0x012d, B:59:0x00fc, B:60:0x0105, B:61:0x0109, B:63:0x011a, B:31:0x0088), top: B:145:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00bc A[Catch: all -> 0x0179, j -> 0x0184, IOException -> 0x018f, InterruptedIOException -> 0x019c, SocketTimeoutException -> 0x01a3, SocketException -> 0x01b0, UnknownHostException -> 0x01bd, MalformedURLException -> 0x01ca, ConnectException -> 0x01d7, TryCatch #9 {j -> 0x0184, InterruptedIOException -> 0x019c, ConnectException -> 0x01d7, MalformedURLException -> 0x01ca, SocketException -> 0x01b0, SocketTimeoutException -> 0x01a3, UnknownHostException -> 0x01bd, IOException -> 0x018f, all -> 0x0179, blocks: (B:3:0x0008, B:6:0x0011, B:15:0x003d, B:16:0x0042, B:18:0x004c, B:20:0x0052, B:21:0x0058, B:23:0x0060, B:25:0x0068, B:27:0x0070, B:28:0x0081, B:32:0x008a, B:34:0x0090, B:36:0x00aa, B:37:0x00af, B:39:0x00b3, B:40:0x00b8, B:42:0x00bc, B:43:0x00c0, B:45:0x00c9, B:48:0x00d3, B:50:0x00d7, B:51:0x00de, B:52:0x00e2, B:54:0x00e6, B:56:0x00ec, B:58:0x00f2, B:62:0x0113, B:64:0x011c, B:66:0x0120, B:68:0x0126, B:69:0x012d, B:59:0x00fc, B:60:0x0105, B:61:0x0109, B:63:0x011a, B:31:0x0088), top: B:145:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00c9 A[Catch: all -> 0x0179, j -> 0x0184, IOException -> 0x018f, InterruptedIOException -> 0x019c, SocketTimeoutException -> 0x01a3, SocketException -> 0x01b0, UnknownHostException -> 0x01bd, MalformedURLException -> 0x01ca, ConnectException -> 0x01d7, TryCatch #9 {j -> 0x0184, InterruptedIOException -> 0x019c, ConnectException -> 0x01d7, MalformedURLException -> 0x01ca, SocketException -> 0x01b0, SocketTimeoutException -> 0x01a3, UnknownHostException -> 0x01bd, IOException -> 0x018f, all -> 0x0179, blocks: (B:3:0x0008, B:6:0x0011, B:15:0x003d, B:16:0x0042, B:18:0x004c, B:20:0x0052, B:21:0x0058, B:23:0x0060, B:25:0x0068, B:27:0x0070, B:28:0x0081, B:32:0x008a, B:34:0x0090, B:36:0x00aa, B:37:0x00af, B:39:0x00b3, B:40:0x00b8, B:42:0x00bc, B:43:0x00c0, B:45:0x00c9, B:48:0x00d3, B:50:0x00d7, B:51:0x00de, B:52:0x00e2, B:54:0x00e6, B:56:0x00ec, B:58:0x00f2, B:62:0x0113, B:64:0x011c, B:66:0x0120, B:68:0x0126, B:69:0x012d, B:59:0x00fc, B:60:0x0105, B:61:0x0109, B:63:0x011a, B:31:0x0088), top: B:145:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00d3 A[Catch: all -> 0x0179, j -> 0x0184, IOException -> 0x018f, InterruptedIOException -> 0x019c, SocketTimeoutException -> 0x01a3, SocketException -> 0x01b0, UnknownHostException -> 0x01bd, MalformedURLException -> 0x01ca, ConnectException -> 0x01d7, TryCatch #9 {j -> 0x0184, InterruptedIOException -> 0x019c, ConnectException -> 0x01d7, MalformedURLException -> 0x01ca, SocketException -> 0x01b0, SocketTimeoutException -> 0x01a3, UnknownHostException -> 0x01bd, IOException -> 0x018f, all -> 0x0179, blocks: (B:3:0x0008, B:6:0x0011, B:15:0x003d, B:16:0x0042, B:18:0x004c, B:20:0x0052, B:21:0x0058, B:23:0x0060, B:25:0x0068, B:27:0x0070, B:28:0x0081, B:32:0x008a, B:34:0x0090, B:36:0x00aa, B:37:0x00af, B:39:0x00b3, B:40:0x00b8, B:42:0x00bc, B:43:0x00c0, B:45:0x00c9, B:48:0x00d3, B:50:0x00d7, B:51:0x00de, B:52:0x00e2, B:54:0x00e6, B:56:0x00ec, B:58:0x00f2, B:62:0x0113, B:64:0x011c, B:66:0x0120, B:68:0x0126, B:69:0x012d, B:59:0x00fc, B:60:0x0105, B:61:0x0109, B:63:0x011a, B:31:0x0088), top: B:145:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00e6 A[Catch: all -> 0x0179, j -> 0x0184, IOException -> 0x018f, InterruptedIOException -> 0x019c, SocketTimeoutException -> 0x01a3, SocketException -> 0x01b0, UnknownHostException -> 0x01bd, MalformedURLException -> 0x01ca, ConnectException -> 0x01d7, TryCatch #9 {j -> 0x0184, InterruptedIOException -> 0x019c, ConnectException -> 0x01d7, MalformedURLException -> 0x01ca, SocketException -> 0x01b0, SocketTimeoutException -> 0x01a3, UnknownHostException -> 0x01bd, IOException -> 0x018f, all -> 0x0179, blocks: (B:3:0x0008, B:6:0x0011, B:15:0x003d, B:16:0x0042, B:18:0x004c, B:20:0x0052, B:21:0x0058, B:23:0x0060, B:25:0x0068, B:27:0x0070, B:28:0x0081, B:32:0x008a, B:34:0x0090, B:36:0x00aa, B:37:0x00af, B:39:0x00b3, B:40:0x00b8, B:42:0x00bc, B:43:0x00c0, B:45:0x00c9, B:48:0x00d3, B:50:0x00d7, B:51:0x00de, B:52:0x00e2, B:54:0x00e6, B:56:0x00ec, B:58:0x00f2, B:62:0x0113, B:64:0x011c, B:66:0x0120, B:68:0x0126, B:69:0x012d, B:59:0x00fc, B:60:0x0105, B:61:0x0109, B:63:0x011a, B:31:0x0088), top: B:145:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x011a A[Catch: all -> 0x0179, j -> 0x0184, IOException -> 0x018f, InterruptedIOException -> 0x019c, SocketTimeoutException -> 0x01a3, SocketException -> 0x01b0, UnknownHostException -> 0x01bd, MalformedURLException -> 0x01ca, ConnectException -> 0x01d7, TryCatch #9 {j -> 0x0184, InterruptedIOException -> 0x019c, ConnectException -> 0x01d7, MalformedURLException -> 0x01ca, SocketException -> 0x01b0, SocketTimeoutException -> 0x01a3, UnknownHostException -> 0x01bd, IOException -> 0x018f, all -> 0x0179, blocks: (B:3:0x0008, B:6:0x0011, B:15:0x003d, B:16:0x0042, B:18:0x004c, B:20:0x0052, B:21:0x0058, B:23:0x0060, B:25:0x0068, B:27:0x0070, B:28:0x0081, B:32:0x008a, B:34:0x0090, B:36:0x00aa, B:37:0x00af, B:39:0x00b3, B:40:0x00b8, B:42:0x00bc, B:43:0x00c0, B:45:0x00c9, B:48:0x00d3, B:50:0x00d7, B:51:0x00de, B:52:0x00e2, B:54:0x00e6, B:56:0x00ec, B:58:0x00f2, B:62:0x0113, B:64:0x011c, B:66:0x0120, B:68:0x0126, B:69:0x012d, B:59:0x00fc, B:60:0x0105, B:61:0x0109, B:63:0x011a, B:31:0x0088), top: B:145:0x0008 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final com.loc.aw a(java.lang.String r8, java.lang.String r9, boolean r10, java.lang.String r11, java.util.Map<java.lang.String, java.lang.String> r12, byte[] r13, int r14) throws com.loc.j {
        /*
            Method dump skipped, instruction units count: 509
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.at.a(java.lang.String, java.lang.String, boolean, java.lang.String, java.util.Map, byte[], int):com.loc.aw");
    }
}