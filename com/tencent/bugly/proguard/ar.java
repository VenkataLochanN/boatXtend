package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class ar extends j {
    private static Map<String, String> i = new HashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f5660a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte f5661b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f5662c = "";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f5663d = "";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f5664e = "";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Map<String, String> f5665f = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f5667h = "";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f5666g = true;

    @Override // com.tencent.bugly.proguard.j
    public final void a(i iVar) {
        iVar.a(this.f5660a, 0);
        iVar.a(this.f5661b, 1);
        String str = this.f5662c;
        if (str != null) {
            iVar.a(str, 2);
        }
        String str2 = this.f5663d;
        if (str2 != null) {
            iVar.a(str2, 3);
        }
        String str3 = this.f5664e;
        if (str3 != null) {
            iVar.a(str3, 4);
        }
        Map<String, String> map = this.f5665f;
        if (map != null) {
            iVar.a((Map) map, 5);
        }
        String str4 = this.f5667h;
        if (str4 != null) {
            iVar.a(str4, 6);
        }
        iVar.a(this.f5666g, 7);
    }

    static {
        i.put("", "");
    }

    @Override // com.tencent.bugly.proguard.j
    public final void a(h hVar) {
        this.f5660a = hVar.a(this.f5660a, 0, true);
        this.f5661b = hVar.a(this.f5661b, 1, true);
        this.f5662c = hVar.b(2, false);
        this.f5663d = hVar.b(3, false);
        this.f5664e = hVar.b(4, false);
        this.f5665f = (Map) hVar.a(i, 5, false);
        this.f5667h = hVar.b(6, false);
        boolean z = this.f5666g;
        this.f5666g = hVar.a(7, false);
    }
}