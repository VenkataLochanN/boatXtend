package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class aq extends j implements Cloneable {
    private static /* synthetic */ boolean o = !aq.class.desiredAssertionStatus();
    private static ap m = new ap();
    private static Map<String, String> n = new HashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f5652a = true;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f5653b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f5654c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f5655d = "";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f5656e = "";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ap f5657f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Map<String, String> f5658g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f5659h = 0;
    private String j = "";
    private String k = "";
    private int l = 0;
    public int i = 0;

    static {
        n.put("", "");
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        aq aqVar = (aq) obj;
        return k.a(this.f5652a, aqVar.f5652a) && k.a(this.f5653b, aqVar.f5653b) && k.a(this.f5654c, aqVar.f5654c) && k.a(this.f5655d, aqVar.f5655d) && k.a(this.f5656e, aqVar.f5656e) && k.a(this.f5657f, aqVar.f5657f) && k.a(this.f5658g, aqVar.f5658g) && k.a(this.f5659h, aqVar.f5659h) && k.a(this.j, aqVar.j) && k.a(this.k, aqVar.k) && k.a(this.l, aqVar.l) && k.a(this.i, aqVar.i);
    }

    public final int hashCode() {
        try {
            throw new Exception("Need define key first!");
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public final Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            if (o) {
                return null;
            }
            throw new AssertionError();
        }
    }

    @Override // com.tencent.bugly.proguard.j
    public final void a(i iVar) {
        iVar.a(this.f5652a, 0);
        iVar.a(this.f5653b, 1);
        iVar.a(this.f5654c, 2);
        String str = this.f5655d;
        if (str != null) {
            iVar.a(str, 3);
        }
        String str2 = this.f5656e;
        if (str2 != null) {
            iVar.a(str2, 4);
        }
        ap apVar = this.f5657f;
        if (apVar != null) {
            iVar.a((j) apVar, 5);
        }
        Map<String, String> map = this.f5658g;
        if (map != null) {
            iVar.a((Map) map, 6);
        }
        iVar.a(this.f5659h, 7);
        String str3 = this.j;
        if (str3 != null) {
            iVar.a(str3, 8);
        }
        String str4 = this.k;
        if (str4 != null) {
            iVar.a(str4, 9);
        }
        iVar.a(this.l, 10);
        iVar.a(this.i, 11);
    }

    @Override // com.tencent.bugly.proguard.j
    public final void a(h hVar) {
        boolean z = this.f5652a;
        this.f5652a = hVar.a(0, true);
        boolean z2 = this.f5653b;
        this.f5653b = hVar.a(1, true);
        boolean z3 = this.f5654c;
        this.f5654c = hVar.a(2, true);
        this.f5655d = hVar.b(3, false);
        this.f5656e = hVar.b(4, false);
        this.f5657f = (ap) hVar.a((j) m, 5, false);
        this.f5658g = (Map) hVar.a(n, 6, false);
        this.f5659h = hVar.a(this.f5659h, 7, false);
        this.j = hVar.b(8, false);
        this.k = hVar.b(9, false);
        this.l = hVar.a(this.l, 10, false);
        this.i = hVar.a(this.i, 11, false);
    }

    @Override // com.tencent.bugly.proguard.j
    public final void a(StringBuilder sb, int i) {
        z zVar = new z(sb, i);
        zVar.a(this.f5652a, "enable");
        zVar.a(this.f5653b, "enableUserInfo");
        zVar.a(this.f5654c, "enableQuery");
        zVar.a(this.f5655d, "url");
        zVar.a(this.f5656e, "expUrl");
        zVar.a((j) this.f5657f, "security");
        zVar.a((Map) this.f5658g, "valueMap");
        zVar.a(this.f5659h, "strategylastUpdateTime");
        zVar.a(this.j, "httpsUrl");
        zVar.a(this.k, "httpsExpUrl");
        zVar.a(this.l, "eventRecordCount");
        zVar.a(this.i, "eventTimeInterval");
    }
}