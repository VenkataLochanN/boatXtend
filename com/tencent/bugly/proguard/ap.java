package com.tencent.bugly.proguard;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class ap extends j implements Cloneable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f5650a = "";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5651b = "";

    @Override // com.tencent.bugly.proguard.j
    public final void a(StringBuilder sb, int i) {
    }

    @Override // com.tencent.bugly.proguard.j
    public final void a(i iVar) {
        iVar.a(this.f5650a, 0);
        iVar.a(this.f5651b, 1);
    }

    @Override // com.tencent.bugly.proguard.j
    public final void a(h hVar) {
        this.f5650a = hVar.b(0, true);
        this.f5651b = hVar.b(1, true);
    }
}