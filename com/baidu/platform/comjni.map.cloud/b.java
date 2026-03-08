package com.baidu.platform.comjni.map.cloud;

import com.baidu.mapapi.http.HttpClient;

/* JADX INFO: loaded from: classes.dex */
class b extends HttpClient.ProtoResultCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ a f3926a;

    b(a aVar) {
        this.f3926a = aVar;
    }

    @Override // com.baidu.mapapi.http.HttpClient.ProtoResultCallback
    public void onFailed(HttpClient.HttpStateError httpStateError) {
        a aVar;
        int i;
        if (httpStateError == HttpClient.HttpStateError.NETWORK_ERROR) {
            aVar = this.f3926a;
            i = -3;
        } else {
            aVar = this.f3926a;
            i = 1;
        }
        aVar.a(i);
    }

    @Override // com.baidu.mapapi.http.HttpClient.ProtoResultCallback
    public void onSuccess(String str) {
        this.f3926a.f3925h = str;
        if (this.f3926a.a()) {
            this.f3926a.f(str);
        } else {
            this.f3926a.f3924g.post(new c(this, str));
        }
    }
}