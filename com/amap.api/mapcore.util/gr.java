package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.util.List;

/* JADX INFO: compiled from: ProxyUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class gr {
    public static Proxy a(Context context) {
        Proxy proxyB;
        try {
            if (Build.VERSION.SDK_INT >= 11) {
                proxyB = a(context, new URI("http://restsdk.amap.com"));
            } else {
                proxyB = b(context);
            }
            return proxyB;
        } catch (Throwable th) {
            hn.c(th, "pu", "gp");
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x015d A[Catch: all -> 0x0169, TRY_LEAVE, TryCatch #3 {all -> 0x0169, blocks: (B:113:0x0157, B:115:0x015d), top: B:130:0x0157 }] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x00e2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x00c6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:134:0x014e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00f8 A[Catch: all -> 0x0173, TryCatch #8 {all -> 0x0173, blocks: (B:70:0x00d8, B:79:0x00ed, B:81:0x00f8, B:83:0x010c, B:85:0x0112, B:90:0x011e, B:94:0x0128, B:96:0x012e, B:98:0x0134, B:103:0x0140), top: B:136:0x0028 }] */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v20 */
    /* JADX WARN: Type inference failed for: r10v21 */
    /* JADX WARN: Type inference failed for: r17v0 */
    /* JADX WARN: Type inference failed for: r17v1 */
    /* JADX WARN: Type inference failed for: r17v10 */
    /* JADX WARN: Type inference failed for: r17v13 */
    /* JADX WARN: Type inference failed for: r17v14 */
    /* JADX WARN: Type inference failed for: r17v17 */
    /* JADX WARN: Type inference failed for: r17v18 */
    /* JADX WARN: Type inference failed for: r17v19 */
    /* JADX WARN: Type inference failed for: r17v2 */
    /* JADX WARN: Type inference failed for: r17v20 */
    /* JADX WARN: Type inference failed for: r17v3 */
    /* JADX WARN: Type inference failed for: r17v4 */
    /* JADX WARN: Type inference failed for: r17v5 */
    /* JADX WARN: Type inference failed for: r17v6 */
    /* JADX WARN: Type inference failed for: r17v7 */
    /* JADX WARN: Type inference failed for: r17v8 */
    /* JADX WARN: Type inference failed for: r17v9 */
    /* JADX WARN: Type inference failed for: r9v0, types: [android.content.ContentResolver, android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.net.Proxy b(android.content.Context r18) {
        /*
            Method dump skipped, instruction units count: 386
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.gr.b(android.content.Context):java.net.Proxy");
    }

    public static String a(String str) {
        return gt.c(str);
    }

    private static boolean a(String str, int i) {
        return (str == null || str.length() <= 0 || i == -1) ? false : true;
    }

    private static String a() {
        String defaultHost;
        try {
            defaultHost = android.net.Proxy.getDefaultHost();
        } catch (Throwable th) {
            hn.c(th, "pu", "gdh");
            defaultHost = null;
        }
        return defaultHost == null ? "null" : defaultHost;
    }

    private static Proxy a(Context context, URI uri) {
        Proxy proxy;
        if (c(context)) {
            try {
                List<Proxy> listSelect = ProxySelector.getDefault().select(uri);
                if (listSelect == null || listSelect.isEmpty() || (proxy = listSelect.get(0)) == null) {
                    return null;
                }
                if (proxy.type() == Proxy.Type.DIRECT) {
                    return null;
                }
                return proxy;
            } catch (Throwable th) {
                hn.c(th, "pu", "gpsc");
            }
        }
        return null;
    }

    private static boolean c(Context context) {
        return gm.r(context) == 0;
    }

    private static int b() {
        try {
            return android.net.Proxy.getDefaultPort();
        } catch (Throwable th) {
            hn.c(th, "pu", "gdp");
            return -1;
        }
    }
}