package com.amap.api.mapcore.util;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: DexSoManager.java */
/* JADX INFO: loaded from: classes.dex */
public class hv {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private gs f1291a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private volatile int f1292b = -1;

    /* JADX INFO: compiled from: DexSoManager.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static Map<String, hv> f1293a = new HashMap();
    }

    public static hv a(gs gsVar) {
        if (a.f1293a.get(gsVar.a()) == null) {
            a.f1293a.put(gsVar.a(), new hv(gsVar));
        }
        return a.f1293a.get(gsVar.a());
    }

    private hv(gs gsVar) {
        this.f1291a = gsVar;
    }

    public void a(Context context, boolean z, boolean z2) {
        gs gsVar = this.f1291a;
        StringBuilder sb = new StringBuilder();
        sb.append(z);
        hz.a(context, gsVar, "sckey", sb.toString());
        if (z) {
            gs gsVar2 = this.f1291a;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(z2);
            hz.a(context, gsVar2, "scisf", sb2.toString());
        }
    }

    public boolean a(Context context) {
        try {
            return Boolean.parseBoolean(hz.a(context, this.f1291a, "sckey"));
        } catch (Throwable unused) {
            return false;
        }
    }

    public boolean b(Context context) {
        try {
            return Boolean.parseBoolean(hz.a(context, this.f1291a, "scisf"));
        } catch (Throwable unused) {
            return true;
        }
    }
}