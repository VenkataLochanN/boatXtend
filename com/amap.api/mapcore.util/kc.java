package com.amap.api.mapcore.util;

import android.content.Context;
import java.util.HashMap;
import java.util.Locale;
import no.nordicsemi.android.dfu.DfuBaseService;

/* JADX INFO: compiled from: LocNetManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class kc {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static kc f1517b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    ij f1518a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f1519c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f1520d = kg.f1548g;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f1521e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f1522f = 0;

    private kc(Context context) {
        this.f1518a = null;
        this.f1519c = null;
        try {
            go.a().a(context);
        } catch (Throwable unused) {
        }
        this.f1519c = context;
        this.f1518a = ij.a();
    }

    public static kc a(Context context) {
        if (f1517b == null) {
            f1517b = new kc(context);
        }
        return f1517b;
    }

    public final is a(kd kdVar) throws Throwable {
        return this.f1518a.b(kdVar, this.f1521e || kk.e(this.f1519c));
    }

    public final kd a(Context context, byte[] bArr, String str, String str2) {
        String str3;
        try {
            HashMap map = new HashMap(16);
            kd kdVar = new kd(context, kg.c());
            try {
                map.put("Content-Type", DfuBaseService.MIME_TYPE_OCTET_STREAM);
                map.put("Accept-Encoding", "gzip");
                map.put("gzipped", "1");
                map.put("Connection", "Keep-Alive");
                map.put("User-Agent", "AMAP_Location_SDK_Android 4.9.0");
                map.put("KEY", gi.f(context));
                map.put("enginever", "5.1");
                String strA = gl.a();
                String strA2 = gl.a(context, strA, "key=" + gi.f(context));
                map.put("ts", strA);
                map.put("scode", strA2);
                map.put("encr", "1");
                kdVar.b(map);
                kdVar.l();
                kdVar.a(String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s&loc_channel=%s", "4.9.0", "loc", 3));
                kdVar.a();
                kdVar.b(str);
                kdVar.c(str2);
                kdVar.b(kk.a(bArr));
                kdVar.setProxy(gr.a(context));
                HashMap map2 = new HashMap(16);
                map2.put("output", "bin");
                map2.put("policy", "3103");
                int i = this.f1522f;
                if (i == 0) {
                    map2.remove("custom");
                } else {
                    if (i != 1) {
                        str3 = i == 2 ? "language:en" : "language:cn";
                        map2.remove("custom");
                    }
                    map2.put("custom", str3);
                }
                kdVar.a(map2);
                kdVar.setConnectionTimeout(this.f1520d);
                kdVar.setSoTimeout(this.f1520d);
                if ((!this.f1521e && !kk.e(context)) || !str.startsWith("http:")) {
                    return kdVar;
                }
                kdVar.b(kdVar.getURL().replace("https:", "https:"));
                return kdVar;
            } catch (Throwable unused) {
                return kdVar;
            }
        } catch (Throwable unused2) {
            return null;
        }
    }

    public final void a(long j, boolean z) {
        try {
            this.f1521e = z;
            try {
                go.a().a(z);
            } catch (Throwable unused) {
            }
            this.f1520d = Long.valueOf(j).intValue();
            this.f1522f = 0;
        } catch (Throwable th) {
            kg.a(th, "LocNetManager", "setOption");
        }
    }
}