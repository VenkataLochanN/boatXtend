package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class am extends j implements Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static ArrayList<al> f5632b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ArrayList<al> f5633a = null;

    @Override // com.tencent.bugly.proguard.j
    public final void a(StringBuilder sb, int i) {
    }

    @Override // com.tencent.bugly.proguard.j
    public final void a(i iVar) {
        iVar.a((Collection) this.f5633a, 0);
    }

    @Override // com.tencent.bugly.proguard.j
    public final void a(h hVar) {
        if (f5632b == null) {
            f5632b = new ArrayList<>();
            f5632b.add(new al());
        }
        this.f5633a = (ArrayList) hVar.a(f5632b, 0, true);
    }
}