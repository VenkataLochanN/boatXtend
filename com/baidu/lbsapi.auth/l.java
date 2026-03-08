package com.baidu.lbsapi.auth;

import com.baidu.lbsapi.auth.e;

/* JADX INFO: loaded from: classes.dex */
class l implements e.a<String> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f2013a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ LBSAuthManager f2014b;

    l(LBSAuthManager lBSAuthManager, String str) {
        this.f2014b = lBSAuthManager;
        this.f2013a = str;
    }

    @Override // com.baidu.lbsapi.auth.e.a
    public void a(String str) {
        this.f2014b.a(str, this.f2013a);
    }
}