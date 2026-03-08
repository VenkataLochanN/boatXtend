package com.loc;

import android.content.Context;
import android.net.Proxy;
import android.os.Build;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.util.List;

/* JADX INFO: compiled from: ProxyUtil.java */
/* JADX INFO: loaded from: classes3.dex */
public final class s {
    private static String a() {
        String defaultHost;
        try {
            defaultHost = Proxy.getDefaultHost();
        } catch (Throwable th) {
            ab.b(th, "pu", "gdh");
            defaultHost = null;
        }
        return defaultHost == null ? "null" : defaultHost;
    }

    public static java.net.Proxy a(Context context) {
        try {
            return Build.VERSION.SDK_INT >= 11 ? a(context, new URI("http://restsdk.amap.com")) : b(context);
        } catch (Throwable th) {
            ab.b(th, "pu", "gp");
            return null;
        }
    }

    private static java.net.Proxy a(Context context, URI uri) {
        java.net.Proxy proxy;
        if (c(context)) {
            try {
                List<java.net.Proxy> listSelect = ProxySelector.getDefault().select(uri);
                if (listSelect == null || listSelect.isEmpty() || (proxy = listSelect.get(0)) == null) {
                    return null;
                }
                if (proxy.type() == Proxy.Type.DIRECT) {
                    return null;
                }
                return proxy;
            } catch (Throwable th) {
                ab.b(th, "pu", "gpsc");
            }
        }
        return null;
    }

    private static int b() {
        try {
            return android.net.Proxy.getDefaultPort();
        } catch (Throwable th) {
            ab.b(th, "pu", "gdp");
            return -1;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0174 A[Catch: all -> 0x0170, TRY_LEAVE, TryCatch #5 {all -> 0x0170, blocks: (B:115:0x0165, B:122:0x0174), top: B:137:0x0165 }] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x00e8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0165 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:143:0x015a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00fe A[Catch: all -> 0x0189, TryCatch #3 {all -> 0x0189, blocks: (B:71:0x00de, B:80:0x00f3, B:82:0x00fe, B:84:0x0112, B:86:0x0118, B:91:0x0126, B:95:0x0132, B:97:0x0138, B:99:0x013e, B:104:0x014c), top: B:134:0x0028 }] */
    /* JADX WARN: Type inference failed for: r0v25 */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v20 */
    /* JADX WARN: Type inference failed for: r10v21 */
    /* JADX WARN: Type inference failed for: r18v0 */
    /* JADX WARN: Type inference failed for: r18v1 */
    /* JADX WARN: Type inference failed for: r18v10 */
    /* JADX WARN: Type inference failed for: r18v11 */
    /* JADX WARN: Type inference failed for: r18v15 */
    /* JADX WARN: Type inference failed for: r18v16 */
    /* JADX WARN: Type inference failed for: r18v17 */
    /* JADX WARN: Type inference failed for: r18v18 */
    /* JADX WARN: Type inference failed for: r18v19 */
    /* JADX WARN: Type inference failed for: r18v2 */
    /* JADX WARN: Type inference failed for: r18v20 */
    /* JADX WARN: Type inference failed for: r18v3 */
    /* JADX WARN: Type inference failed for: r18v4 */
    /* JADX WARN: Type inference failed for: r18v5 */
    /* JADX WARN: Type inference failed for: r18v6 */
    /* JADX WARN: Type inference failed for: r18v7 */
    /* JADX WARN: Type inference failed for: r18v8 */
    /* JADX WARN: Type inference failed for: r18v9 */
    /* JADX WARN: Type inference failed for: r9v0, types: [android.content.ContentResolver, android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.net.Proxy b(android.content.Context r19) {
        /*
            Method dump skipped, instruction units count: 408
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.s.b(android.content.Context):java.net.Proxy");
    }

    private static boolean c(Context context) {
        return n.q(context) == 0;
    }
}