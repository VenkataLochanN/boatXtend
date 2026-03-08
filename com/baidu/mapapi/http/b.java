package com.baidu.mapapi.http;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/* JADX INFO: loaded from: classes.dex */
class b implements HostnameVerifier {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ HttpClient f2756a;

    b(HttpClient httpClient) {
        this.f2756a = httpClient;
    }

    @Override // javax.net.ssl.HostnameVerifier
    public boolean verify(String str, SSLSession sSLSession) {
        return HttpsURLConnection.getDefaultHostnameVerifier().verify(str, sSLSession);
    }
}