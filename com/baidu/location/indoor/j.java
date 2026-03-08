package com.baidu.location.indoor;

import com.baidu.location.indoor.mapversion.c.a;

/* JADX INFO: loaded from: classes.dex */
class j implements a.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f2588a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ String f2589b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ g f2590c;

    j(g gVar, String str, String str2) {
        this.f2590c = gVar;
        this.f2588a = str;
        this.f2589b = str2;
    }

    @Override // com.baidu.location.indoor.mapversion.c.a.c
    public void a(boolean z, String str) {
        this.f2590c.ab = z;
        if (z) {
            this.f2590c.ac = com.baidu.location.indoor.mapversion.b.a.a(this.f2589b);
        }
    }
}