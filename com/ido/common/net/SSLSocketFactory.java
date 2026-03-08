package com.ido.common.net;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: classes2.dex */
public class SSLSocketFactory {
    public static javax.net.ssl.SSLSocketFactory getUnCheckSSLSocketFactory() {
        TrustManager[] trustManagerArr = {new X509TrustManager() { // from class: com.ido.common.net.SSLSocketFactory.1
            @Override // javax.net.ssl.X509TrustManager
            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            }

            @Override // javax.net.ssl.X509TrustManager
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }};
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, trustManagerArr, new SecureRandom());
            return sSLContext.getSocketFactory();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}