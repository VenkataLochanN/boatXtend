package com.loc;

import android.content.Context;
import java.util.HashMap;
import java.util.Locale;
import no.nordicsemi.android.dfu.DfuBaseService;

/* JADX INFO: compiled from: LocNetManager.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ee {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static ee f5131b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    aq f5132a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f5133c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f5134d = ej.f5165g;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f5135e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f5136f = 0;

    private ee(Context context) {
        this.f5132a = null;
        this.f5133c = null;
        try {
            p.a().a(context);
        } catch (Throwable unused) {
        }
        this.f5133c = context;
        this.f5132a = aq.a();
    }

    public static ee a(Context context) {
        if (f5131b == null) {
            f5131b = new ee(context);
        }
        return f5131b;
    }

    public final int a() {
        return this.f5134d;
    }

    public final aw a(ef efVar) throws Throwable {
        return aq.a(efVar, this.f5135e || ep.k(this.f5133c));
    }

    public final aw a(ef efVar, int i) throws Throwable {
        return aq.a(efVar, this.f5135e || ep.k(this.f5133c), i);
    }

    public final ef a(Context context, byte[] bArr, String str, String str2, boolean z) {
        String str3;
        try {
            HashMap map = new HashMap(16);
            ef efVar = new ef(context, ej.c());
            try {
                map.put("Content-Type", DfuBaseService.MIME_TYPE_OCTET_STREAM);
                map.put("Accept-Encoding", "gzip");
                map.put("gzipped", "1");
                map.put("Connection", "Keep-Alive");
                map.put("User-Agent", "AMAP_Location_SDK_Android 5.2.0");
                map.put("KEY", k.f(context));
                map.put("enginever", "5.1");
                String strA = m.a();
                String strA2 = m.a(context, strA, "key=" + k.f(context));
                map.put("ts", strA);
                map.put("scode", strA2);
                map.put("encr", "1");
                efVar.f5137f = map;
                String str4 = z ? "loc" : "locf";
                efVar.n = true;
                efVar.l = String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s&loc_channel=%s", "5.2.0", str4, 3);
                efVar.k = z;
                efVar.f5138g = str;
                efVar.f5139h = str2;
                efVar.i = ep.a(bArr);
                efVar.a(s.a(context));
                HashMap map2 = new HashMap(16);
                map2.put("output", "bin");
                map2.put("policy", "3103");
                int i = this.f5136f;
                if (i == 0) {
                    map2.remove("custom");
                } else {
                    if (i != 1) {
                        str3 = i == 2 ? "language:en" : "language:cn";
                        map2.remove("custom");
                    }
                    map2.put("custom", str3);
                }
                efVar.m = map2;
                efVar.a(this.f5134d);
                efVar.b(this.f5134d);
                if ((!this.f5135e && !ep.k(context)) || !str.startsWith("http:")) {
                    return efVar;
                }
                efVar.f5138g = efVar.c().replace("https:", "https:");
                return efVar;
            } catch (Throwable unused) {
                return efVar;
            }
        } catch (Throwable unused2) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00f7 A[Catch: all -> 0x0114, TryCatch #0 {all -> 0x0114, blocks: (B:31:0x00eb, B:33:0x00f7, B:35:0x010c, B:34:0x0106), top: B:50:0x00eb, outer: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0106 A[Catch: all -> 0x0114, TryCatch #0 {all -> 0x0114, blocks: (B:31:0x00eb, B:33:0x00f7, B:35:0x010c, B:34:0x0106), top: B:50:0x00eb, outer: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0090 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String a(android.content.Context r10, double r11, double r13) {
        /*
            Method dump skipped, instruction units count: 299
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ee.a(android.content.Context, double, double):java.lang.String");
    }

    public final void a(long j, boolean z, int i) {
        try {
            this.f5135e = z;
            try {
                p.a().a(z);
            } catch (Throwable unused) {
            }
            this.f5134d = Long.valueOf(j).intValue();
            this.f5136f = i;
        } catch (Throwable th) {
            ej.a(th, "LocNetManager", "setOption");
        }
    }
}