package com.amap.api.mapcore.util;

import android.util.Log;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: OverlayerStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public class fb implements ez {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Map<String, a> f807a = new ConcurrentHashMap();

    /* JADX INFO: compiled from: OverlayerStrategy.java */
    class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        String f808a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f809b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f810c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final AtomicInteger f811d = new AtomicInteger(0);

        public a(int i, String str, String str2) {
            this.f808a = "";
            this.f809b = "";
            this.f808a = str;
            this.f809b = str2;
            this.f810c = i;
        }

        public int a() {
            return this.f811d.incrementAndGet();
        }
    }

    private String b(int i, String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        if (str == null) {
            str = "";
        }
        sb.append(str);
        sb.append(str2 != null ? str2 : "");
        return sb.toString();
    }

    @Override // com.amap.api.mapcore.util.ez
    public void a(int i, String str, String str2) {
        try {
            String strB = b(i, str, str2);
            a aVar = f807a.get(strB);
            if (aVar == null) {
                aVar = new a(i, str, str2);
                f807a.put(strB, aVar);
            }
            if (aVar.a() > 100) {
                a(aVar.f810c, aVar.f808a, aVar.f809b, aVar.f811d.get());
                f807a.remove(strB);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.amap.api.mapcore.util.ez
    public void a() {
        try {
            Iterator<Map.Entry<String, a>> it = f807a.entrySet().iterator();
            while (it.hasNext()) {
                a value = it.next().getValue();
                if (value != null) {
                    a(value.f810c, value.f808a, value.f809b, value.f811d.get());
                }
            }
            f807a.clear();
            hf.a(er.e()).a();
        } catch (Throwable unused) {
        }
    }

    private void a(int i, String str, String str2, int i2) {
        if (i == 0) {
            hf.a(er.e()).a(he.a(str, str2 + " counter " + i2));
        } else {
            hf.a(er.e()).a(he.a(str, str2 + " counter " + i2));
        }
        if (ex.f795b) {
            c(i, str, str2 + " counter " + i2);
        }
    }

    private static void c(int i, String str, String str2) {
        if (i == 0) {
            Log.i("linklog", str + " " + str2);
            return;
        }
        Log.e("linklog", str + " " + str2);
    }
}