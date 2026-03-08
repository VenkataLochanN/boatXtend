package com.baidu.platform.base;

import com.baidu.mapapi.http.HttpClient;

/* JADX INFO: loaded from: classes.dex */
class b extends HttpClient.ProtoResultCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ d f3895a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ Object f3896b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ a f3897c;

    b(a aVar, d dVar, Object obj) {
        this.f3897c = aVar;
        this.f3895a = dVar;
        this.f3896b = obj;
    }

    @Override // com.baidu.mapapi.http.HttpClient.ProtoResultCallback
    public void onFailed(HttpClient.HttpStateError httpStateError) {
        this.f3897c.a(httpStateError, this.f3895a, this.f3896b);
    }

    @Override // com.baidu.mapapi.http.HttpClient.ProtoResultCallback
    public void onSuccess(String str) {
        this.f3897c.a(str);
        a aVar = this.f3897c;
        aVar.a(str, this.f3895a, this.f3896b, aVar.f3891b, this);
    }
}