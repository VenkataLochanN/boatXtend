package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class ao extends j {
    private static byte[] i = {0};
    private static Map<String, String> j = new HashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte f5642a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5643b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f5644c = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f5647f = "";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f5645d = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private String f5648g = "";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f5646e = "";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Map<String, String> f5649h = null;

    @Override // com.tencent.bugly.proguard.j
    public final void a(i iVar) {
        iVar.a(this.f5642a, 0);
        iVar.a(this.f5643b, 1);
        byte[] bArr = this.f5644c;
        if (bArr != null) {
            iVar.a(bArr, 2);
        }
        String str = this.f5647f;
        if (str != null) {
            iVar.a(str, 3);
        }
        iVar.a(this.f5645d, 4);
        String str2 = this.f5648g;
        if (str2 != null) {
            iVar.a(str2, 5);
        }
        String str3 = this.f5646e;
        if (str3 != null) {
            iVar.a(str3, 6);
        }
        Map<String, String> map = this.f5649h;
        if (map != null) {
            iVar.a((Map) map, 7);
        }
    }

    static {
        j.put("", "");
    }

    @Override // com.tencent.bugly.proguard.j
    public final void a(h hVar) {
        this.f5642a = hVar.a(this.f5642a, 0, true);
        this.f5643b = hVar.a(this.f5643b, 1, true);
        byte[] bArr = i;
        this.f5644c = hVar.c(2, false);
        this.f5647f = hVar.b(3, false);
        this.f5645d = hVar.a(this.f5645d, 4, false);
        this.f5648g = hVar.b(5, false);
        this.f5646e = hVar.b(6, false);
        this.f5649h = (Map) hVar.a(j, 7, false);
    }
}