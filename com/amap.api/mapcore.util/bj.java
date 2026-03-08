package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.maps.AMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: TaskManager.java */
/* JADX INFO: loaded from: classes.dex */
public class bj {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static bj f268a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private jr f269b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private LinkedHashMap<String, js> f270c = new LinkedHashMap<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f271d = true;

    public static bj a(int i) {
        return a(true, i);
    }

    private static synchronized bj a(boolean z, int i) {
        try {
            if (f268a == null) {
                f268a = new bj(z, i);
            } else if (z && f268a.f269b == null) {
                f268a.f269b = jr.a(i);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return f268a;
    }

    private bj(boolean z, int i) {
        if (z) {
            try {
                this.f269b = jr.a(i);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void a() {
        synchronized (this.f270c) {
            if (this.f270c.size() < 1) {
                return;
            }
            for (Map.Entry<String, js> entry : this.f270c.entrySet()) {
                entry.getKey();
                ((bf) entry.getValue()).a();
            }
            this.f270c.clear();
        }
    }

    public void a(bi biVar) {
        synchronized (this.f270c) {
            bf bfVar = (bf) this.f270c.get(biVar.b());
            if (bfVar == null) {
                return;
            }
            bfVar.a();
            this.f270c.remove(biVar.b());
        }
    }

    public void a(bi biVar, Context context, AMap aMap) throws gh {
        jr jrVar = this.f269b;
        if (!this.f270c.containsKey(biVar.b())) {
            bf bfVar = new bf((bz) biVar, context.getApplicationContext(), aMap);
            synchronized (this.f270c) {
                this.f270c.put(biVar.b(), bfVar);
            }
        }
        this.f269b.a(this.f270c.get(biVar.b()));
    }

    public void b() {
        a();
        jr.a();
        this.f269b = null;
        c();
    }

    public static void c() {
        f268a = null;
    }

    public void b(bi biVar) {
        bf bfVar = (bf) this.f270c.get(biVar.b());
        if (bfVar != null) {
            synchronized (this.f270c) {
                bfVar.b();
                this.f270c.remove(biVar.b());
            }
        }
    }
}