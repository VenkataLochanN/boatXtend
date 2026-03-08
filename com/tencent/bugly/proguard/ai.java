package com.tencent.bugly.proguard;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class ai extends j implements Cloneable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f5612a = "";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f5615d = "";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f5613b = "";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f5616e = "";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f5614c = "";

    @Override // com.tencent.bugly.proguard.j
    public final void a(StringBuilder sb, int i) {
    }

    @Override // com.tencent.bugly.proguard.j
    public final void a(i iVar) {
        iVar.a(this.f5612a, 0);
        String str = this.f5615d;
        if (str != null) {
            iVar.a(str, 1);
        }
        String str2 = this.f5613b;
        if (str2 != null) {
            iVar.a(str2, 2);
        }
        String str3 = this.f5616e;
        if (str3 != null) {
            iVar.a(str3, 3);
        }
        String str4 = this.f5614c;
        if (str4 != null) {
            iVar.a(str4, 4);
        }
    }

    @Override // com.tencent.bugly.proguard.j
    public final void a(h hVar) {
        this.f5612a = hVar.b(0, true);
        this.f5615d = hVar.b(1, false);
        this.f5613b = hVar.b(2, false);
        this.f5616e = hVar.b(3, false);
        this.f5614c = hVar.b(4, false);
    }
}