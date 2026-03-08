package com.baidu.platform.comapi.pano;

import com.baidu.mapapi.http.HttpClient;
import com.baidu.platform.comapi.pano.a;

/* JADX INFO: loaded from: classes.dex */
class b extends HttpClient.ProtoResultCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ a.InterfaceC0037a f3913a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ a f3914b;

    b(a aVar, a.InterfaceC0037a interfaceC0037a) {
        this.f3914b = aVar;
        this.f3913a = interfaceC0037a;
    }

    @Override // com.baidu.mapapi.http.HttpClient.ProtoResultCallback
    public void onFailed(HttpClient.HttpStateError httpStateError) {
        this.f3913a.a(httpStateError);
    }

    @Override // com.baidu.mapapi.http.HttpClient.ProtoResultCallback
    public void onSuccess(String str) {
        this.f3913a.a(this.f3914b.a(str));
    }
}