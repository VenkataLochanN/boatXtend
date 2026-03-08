package com.baidu.lbsapi.auth;

import com.baidu.lbsapi.auth.c;

/* JADX INFO: loaded from: classes.dex */
class k implements c.a<String> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f2011a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ LBSAuthManager f2012b;

    k(LBSAuthManager lBSAuthManager, String str) {
        this.f2012b = lBSAuthManager;
        this.f2011a = str;
    }

    @Override // com.baidu.lbsapi.auth.c.a
    public void a(String str) {
        this.f2012b.a(str, this.f2011a);
    }
}