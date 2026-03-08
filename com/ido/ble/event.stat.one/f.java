package com.ido.ble.event.stat.one;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static List<com.ido.ble.event.stat.one.faildata.c> f4399a = new ArrayList();

    f() {
    }

    static void a() {
        f4399a.clear();
        for (com.ido.ble.event.stat.one.faildata.c cVar : com.ido.ble.event.stat.one.faildata.a.b().a()) {
            if (cVar != null && !TextUtils.isEmpty(cVar.c())) {
                f4399a.add(cVar);
                com.ido.ble.event.stat.one.faildata.b bVar = new com.ido.ble.event.stat.one.faildata.b();
                bVar.c(cVar.a());
                bVar.a(Long.parseLong(cVar.c()));
                e.a(bVar);
            }
        }
    }

    static void a(com.ido.ble.g.a.b bVar) {
        if (bVar == null || bVar.b() == 0) {
            return;
        }
        com.ido.ble.event.stat.one.faildata.c cVar = new com.ido.ble.event.stat.one.faildata.c();
        cVar.b(bVar.b() + "");
        cVar.a(bVar.a());
        com.ido.ble.event.stat.one.faildata.a.b().a(cVar);
    }

    static void b(com.ido.ble.g.a.b bVar) {
        if (bVar == null || bVar.b() == 0) {
            return;
        }
        ArrayList<com.ido.ble.event.stat.one.faildata.c> arrayList = new ArrayList();
        arrayList.addAll(f4399a);
        for (com.ido.ble.event.stat.one.faildata.c cVar : arrayList) {
            if (cVar != null && !TextUtils.isEmpty(cVar.c())) {
                if (cVar.c().equals(bVar.b() + "")) {
                    f4399a.remove(cVar);
                    com.ido.ble.event.stat.one.faildata.a.b().a(cVar.c());
                }
            }
        }
    }
}