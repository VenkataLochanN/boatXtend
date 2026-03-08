package com.baidu.lbsapi.auth;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/* JADX INFO: loaded from: classes.dex */
class h implements HostnameVerifier {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ g f2003a;

    h(g gVar) {
        this.f2003a = gVar;
    }

    @Override // javax.net.ssl.HostnameVerifier
    public boolean verify(String str, SSLSession sSLSession) {
        if ("api.map.baidu.com".equals(str)) {
            return true;
        }
        return HttpsURLConnection.getDefaultHostnameVerifier().verify(str, sSLSession);
    }
}