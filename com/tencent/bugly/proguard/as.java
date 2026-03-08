package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class as extends j implements Cloneable {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static ArrayList<ar> f5668f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static Map<String, String> f5669g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte f5670a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f5671b = "";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f5672c = "";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ArrayList<ar> f5673d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Map<String, String> f5674e = null;

    @Override // com.tencent.bugly.proguard.j
    public final void a(StringBuilder sb, int i) {
    }

    @Override // com.tencent.bugly.proguard.j
    public final void a(i iVar) {
        iVar.a(this.f5670a, 0);
        String str = this.f5671b;
        if (str != null) {
            iVar.a(str, 1);
        }
        String str2 = this.f5672c;
        if (str2 != null) {
            iVar.a(str2, 2);
        }
        ArrayList<ar> arrayList = this.f5673d;
        if (arrayList != null) {
            iVar.a((Collection) arrayList, 3);
        }
        Map<String, String> map = this.f5674e;
        if (map != null) {
            iVar.a((Map) map, 4);
        }
    }

    @Override // com.tencent.bugly.proguard.j
    public final void a(h hVar) {
        this.f5670a = hVar.a(this.f5670a, 0, true);
        this.f5671b = hVar.b(1, false);
        this.f5672c = hVar.b(2, false);
        if (f5668f == null) {
            f5668f = new ArrayList<>();
            f5668f.add(new ar());
        }
        this.f5673d = (ArrayList) hVar.a(f5668f, 3, false);
        if (f5669g == null) {
            f5669g = new HashMap();
            f5669g.put("", "");
        }
        this.f5674e = (Map) hVar.a(f5669g, 4, false);
    }
}