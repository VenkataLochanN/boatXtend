package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class aj extends j implements Cloneable {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static ArrayList<String> f5617c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5618a = "";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ArrayList<String> f5619b = null;

    @Override // com.tencent.bugly.proguard.j
    public final void a(StringBuilder sb, int i) {
    }

    @Override // com.tencent.bugly.proguard.j
    public final void a(i iVar) {
        iVar.a(this.f5618a, 0);
        ArrayList<String> arrayList = this.f5619b;
        if (arrayList != null) {
            iVar.a((Collection) arrayList, 1);
        }
    }

    @Override // com.tencent.bugly.proguard.j
    public final void a(h hVar) {
        this.f5618a = hVar.b(0, true);
        if (f5617c == null) {
            f5617c = new ArrayList<>();
            f5617c.add("");
        }
        this.f5619b = (ArrayList) hVar.a(f5617c, 1, false);
    }
}