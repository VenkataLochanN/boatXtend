package com.baidu.location.g;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes.dex */
public abstract class e {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f2489h = null;
    public int i = 1;
    public String j = null;
    public Map<String, Object> k = null;
    public String l = null;
    public byte[] m = null;
    public byte[] n = null;
    public String o = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static int f2488g = a.f2458g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f2486a = "10.0.0.172";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static int f2487b = 80;
    protected static int p = 0;

    /* JADX WARN: Code restructure failed: missing block: B:53:0x00ac, code lost:
    
        if ("10.0.0.200".equals(r5.trim()) != false) goto L24;
     */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00a4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int a(android.content.Context r4, android.net.NetworkInfo r5) {
        /*
            java.lang.String r4 = "10.0.0.200"
            java.lang.String r0 = "10.0.0.172"
            if (r5 == 0) goto L8b
            java.lang.String r1 = r5.getExtraInfo()
            if (r1 == 0) goto L8b
            java.lang.String r5 = r5.getExtraInfo()
            java.lang.String r5 = r5.toLowerCase()
            if (r5 == 0) goto L8b
            java.lang.String r1 = "cmwap"
            boolean r1 = r5.startsWith(r1)
            java.lang.String r2 = "null"
            java.lang.String r3 = ""
            if (r1 != 0) goto L76
            java.lang.String r1 = "uniwap"
            boolean r1 = r5.startsWith(r1)
            if (r1 != 0) goto L76
            java.lang.String r1 = "3gwap"
            boolean r1 = r5.startsWith(r1)
            if (r1 == 0) goto L33
            goto L76
        L33:
            java.lang.String r1 = "ctwap"
            boolean r1 = r5.startsWith(r1)
            if (r1 == 0) goto L53
            java.lang.String r5 = android.net.Proxy.getDefaultHost()
            if (r5 == 0) goto L4e
            boolean r0 = r5.equals(r3)
            if (r0 != 0) goto L4e
            boolean r0 = r5.equals(r2)
            if (r0 != 0) goto L4e
            r4 = r5
        L4e:
            com.baidu.location.g.e.f2486a = r4
        L50:
            int r4 = com.baidu.location.g.a.f2455d
            return r4
        L53:
            java.lang.String r1 = "cmnet"
            boolean r1 = r5.startsWith(r1)
            if (r1 != 0) goto L73
            java.lang.String r1 = "uninet"
            boolean r1 = r5.startsWith(r1)
            if (r1 != 0) goto L73
            java.lang.String r1 = "ctnet"
            boolean r1 = r5.startsWith(r1)
            if (r1 != 0) goto L73
            java.lang.String r1 = "3gnet"
            boolean r5 = r5.startsWith(r1)
            if (r5 == 0) goto L8b
        L73:
            int r4 = com.baidu.location.g.a.f2456e
            return r4
        L76:
            java.lang.String r4 = android.net.Proxy.getDefaultHost()
            if (r4 == 0) goto L89
            boolean r5 = r4.equals(r3)
            if (r5 != 0) goto L89
            boolean r5 = r4.equals(r2)
            if (r5 != 0) goto L89
            goto L4e
        L89:
            r4 = r0
            goto L4e
        L8b:
            java.lang.String r5 = android.net.Proxy.getDefaultHost()
            if (r5 == 0) goto Laf
            int r1 = r5.length()
            if (r1 <= 0) goto Laf
            java.lang.String r1 = r5.trim()
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto La4
            com.baidu.location.g.e.f2486a = r0
            goto L50
        La4:
            java.lang.String r5 = r5.trim()
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto Laf
            goto L4e
        Laf:
            int r4 = com.baidu.location.g.a.f2456e
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.g.e.a(android.content.Context, android.net.NetworkInfo):int");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        f2488g = c();
    }

    private int c() {
        Context serviceContext = com.baidu.location.f.getServiceContext();
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) serviceContext.getSystemService("connectivity");
            if (connectivityManager == null) {
                return a.f2458g;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                if (activeNetworkInfo.getType() != 1) {
                    return a(serviceContext, activeNetworkInfo);
                }
                String defaultHost = Proxy.getDefaultHost();
                return (defaultHost == null || defaultHost.length() <= 0) ? a.f2457f : a.f2459h;
            }
            return a.f2458g;
        } catch (Exception unused) {
            return a.f2458g;
        }
    }

    public abstract void a();

    public void a(ExecutorService executorService) {
        try {
            executorService.execute(new f(this));
        } catch (Throwable unused) {
            a(false);
        }
    }

    public void a(ExecutorService executorService, String str) {
        try {
            executorService.execute(new i(this, str));
        } catch (Throwable unused) {
            a(false);
        }
    }

    public void a(ExecutorService executorService, boolean z, String str) {
        try {
            executorService.execute(new g(this, str, z));
        } catch (Throwable unused) {
            a(false);
        }
    }

    public abstract void a(boolean z);

    public void b(ExecutorService executorService) {
        a(executorService, false, "loc.map.baidu.com");
    }

    public void c(String str) {
        try {
            new h(this, str).start();
        } catch (Throwable unused) {
            a(false);
        }
    }
}