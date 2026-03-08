package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class al extends j {
    private static ArrayList<ak> A;
    private static Map<String, String> B;
    private static Map<String, String> C;
    private static Map<String, String> v = new HashMap();
    private static aj w;
    private static ai x;
    private static ArrayList<ai> y;
    private static ArrayList<ai> z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f5624a = "";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f5625b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f5626c = "";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f5627d = "";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f5628e = "";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f5629f = "";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f5630g = "";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Map<String, String> f5631h = null;
    public String i = "";
    public aj j = null;
    public int k = 0;
    public String l = "";
    public String m = "";
    public ai n = null;
    public ArrayList<ai> o = null;
    public ArrayList<ai> p = null;
    public ArrayList<ak> q = null;
    public Map<String, String> r = null;
    public Map<String, String> s = null;
    private String t = "";
    private boolean u = true;

    @Override // com.tencent.bugly.proguard.j
    public final void a(i iVar) {
        iVar.a(this.f5624a, 0);
        iVar.a(this.f5625b, 1);
        iVar.a(this.f5626c, 2);
        String str = this.f5627d;
        if (str != null) {
            iVar.a(str, 3);
        }
        String str2 = this.f5628e;
        if (str2 != null) {
            iVar.a(str2, 4);
        }
        String str3 = this.f5629f;
        if (str3 != null) {
            iVar.a(str3, 5);
        }
        String str4 = this.f5630g;
        if (str4 != null) {
            iVar.a(str4, 6);
        }
        Map<String, String> map = this.f5631h;
        if (map != null) {
            iVar.a((Map) map, 7);
        }
        String str5 = this.i;
        if (str5 != null) {
            iVar.a(str5, 8);
        }
        aj ajVar = this.j;
        if (ajVar != null) {
            iVar.a((j) ajVar, 9);
        }
        iVar.a(this.k, 10);
        String str6 = this.l;
        if (str6 != null) {
            iVar.a(str6, 11);
        }
        String str7 = this.m;
        if (str7 != null) {
            iVar.a(str7, 12);
        }
        ai aiVar = this.n;
        if (aiVar != null) {
            iVar.a((j) aiVar, 13);
        }
        ArrayList<ai> arrayList = this.o;
        if (arrayList != null) {
            iVar.a((Collection) arrayList, 14);
        }
        ArrayList<ai> arrayList2 = this.p;
        if (arrayList2 != null) {
            iVar.a((Collection) arrayList2, 15);
        }
        ArrayList<ak> arrayList3 = this.q;
        if (arrayList3 != null) {
            iVar.a((Collection) arrayList3, 16);
        }
        Map<String, String> map2 = this.r;
        if (map2 != null) {
            iVar.a((Map) map2, 17);
        }
        Map<String, String> map3 = this.s;
        if (map3 != null) {
            iVar.a((Map) map3, 18);
        }
        String str8 = this.t;
        if (str8 != null) {
            iVar.a(str8, 19);
        }
        iVar.a(this.u, 20);
    }

    static {
        v.put("", "");
        w = new aj();
        x = new ai();
        y = new ArrayList<>();
        y.add(new ai());
        z = new ArrayList<>();
        z.add(new ai());
        A = new ArrayList<>();
        A.add(new ak());
        B = new HashMap();
        B.put("", "");
        C = new HashMap();
        C.put("", "");
    }

    @Override // com.tencent.bugly.proguard.j
    public final void a(h hVar) {
        this.f5624a = hVar.b(0, true);
        this.f5625b = hVar.a(this.f5625b, 1, true);
        this.f5626c = hVar.b(2, true);
        this.f5627d = hVar.b(3, false);
        this.f5628e = hVar.b(4, false);
        this.f5629f = hVar.b(5, false);
        this.f5630g = hVar.b(6, false);
        this.f5631h = (Map) hVar.a(v, 7, false);
        this.i = hVar.b(8, false);
        this.j = (aj) hVar.a((j) w, 9, false);
        this.k = hVar.a(this.k, 10, false);
        this.l = hVar.b(11, false);
        this.m = hVar.b(12, false);
        this.n = (ai) hVar.a((j) x, 13, false);
        this.o = (ArrayList) hVar.a(y, 14, false);
        this.p = (ArrayList) hVar.a(z, 15, false);
        this.q = (ArrayList) hVar.a(A, 16, false);
        this.r = (Map) hVar.a(B, 17, false);
        this.s = (Map) hVar.a(C, 18, false);
        this.t = hVar.b(19, false);
        boolean z2 = this.u;
        this.u = hVar.a(20, false);
    }
}