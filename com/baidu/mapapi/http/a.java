package com.baidu.mapapi.http;

import com.baidu.mapapi.http.AsyncHttpClient;
import com.baidu.mapapi.http.HttpClient;

/* JADX INFO: loaded from: classes.dex */
class a extends AsyncHttpClient.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ HttpClient.ProtoResultCallback f2753a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ String f2754b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ AsyncHttpClient f2755c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    a(AsyncHttpClient asyncHttpClient, HttpClient.ProtoResultCallback protoResultCallback, String str) {
        super(null);
        this.f2755c = asyncHttpClient;
        this.f2753a = protoResultCallback;
        this.f2754b = str;
    }

    @Override // com.baidu.mapapi.http.AsyncHttpClient.a
    public void a() throws Throwable {
        HttpClient httpClient = new HttpClient("GET", this.f2753a);
        httpClient.setMaxTimeOut(this.f2755c.f2743a);
        httpClient.setReadTimeOut(this.f2755c.f2744b);
        httpClient.request(this.f2754b);
    }
}