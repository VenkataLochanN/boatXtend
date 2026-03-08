package com.baidu.location.indoor;

import com.baidu.location.indoor.d;
import com.baidu.location.indoor.g;

/* JADX INFO: loaded from: classes.dex */
class k implements d.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ g.i f2591a;

    k(g.i iVar) {
        this.f2591a = iVar;
    }

    @Override // com.baidu.location.indoor.d.b
    public void a(boolean z, String str, String str2, String str3) {
        if (z) {
            g.this.R = "&ibuuid=" + str + "&ibname=" + str2 + "&ibfls=" + str3;
        }
    }
}