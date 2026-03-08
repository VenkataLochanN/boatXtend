package com.amap.api.mapcore.util;

import com.amap.api.maps.AMapException;

/* JADX INFO: compiled from: AMapCoreException.java */
/* JADX INFO: loaded from: classes.dex */
public class gh extends Exception {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f1041a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f1042b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f1043c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f1044d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f1045e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f1046f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private volatile boolean f1047g;

    public gh(String str) {
        super(str);
        this.f1041a = AMapException.ERROR_UNKNOWN;
        this.f1042b = "";
        this.f1043c = "";
        this.f1044d = "1900";
        this.f1045e = "UnknownError";
        this.f1046f = -1;
        this.f1047g = false;
        this.f1041a = str;
        a(str);
    }

    public gh(String str, String str2, String str3) {
        this(str);
        this.f1042b = str2;
        this.f1043c = str3;
    }

    public String a() {
        return this.f1041a;
    }

    public String b() {
        return this.f1044d;
    }

    public String c() {
        return this.f1045e;
    }

    public String d() {
        return this.f1042b;
    }

    public String e() {
        return this.f1043c;
    }

    public void a(int i) {
        this.f1046f = i;
    }

    public boolean f() {
        return this.f1047g;
    }

    public void a(boolean z) {
        this.f1047g = z;
    }

    private void a(String str) {
        if (AMapException.ERROR_IO.equals(str)) {
            this.f1046f = 21;
            this.f1044d = "1902";
            this.f1045e = "IOException";
            return;
        }
        if (AMapException.ERROR_SOCKET.equals(str)) {
            this.f1046f = 22;
            return;
        }
        if (AMapException.ERROR_SOCKE_TIME_OUT.equals(str)) {
            this.f1046f = 23;
            this.f1044d = "1802";
            this.f1045e = "SocketTimeoutException";
            return;
        }
        if (AMapException.ERROR_INVALID_PARAMETER.equals(str)) {
            this.f1046f = 24;
            this.f1044d = "1901";
            this.f1045e = "IllegalArgumentException";
            return;
        }
        if (AMapException.ERROR_NULL_PARAMETER.equals(str)) {
            this.f1046f = 25;
            this.f1044d = "1903";
            this.f1045e = "NullPointException";
            return;
        }
        if (AMapException.ERROR_URL.equals(str)) {
            this.f1046f = 26;
            this.f1044d = "1803";
            this.f1045e = "MalformedURLException";
            return;
        }
        if (AMapException.ERROR_UNKNOW_HOST.equals(str)) {
            this.f1046f = 27;
            this.f1044d = "1804";
            this.f1045e = "UnknownHostException";
            return;
        }
        if (AMapException.ERROR_UNKNOW_SERVICE.equals(str)) {
            this.f1046f = 28;
            this.f1044d = "1805";
            this.f1045e = "CannotConnectToHostException";
            return;
        }
        if (AMapException.ERROR_PROTOCOL.equals(str)) {
            this.f1046f = 29;
            this.f1044d = "1801";
            this.f1045e = "ProtocolException";
            return;
        }
        if (AMapException.ERROR_CONNECTION.equals(str)) {
            this.f1046f = 30;
            this.f1044d = "1806";
            this.f1045e = "ConnectionException";
            return;
        }
        if ("服务QPS超限".equalsIgnoreCase(str)) {
            this.f1046f = 30;
            this.f1044d = "2001";
            this.f1045e = "ConnectionException";
            return;
        }
        if (AMapException.ERROR_UNKNOWN.equals(str)) {
            this.f1046f = 31;
            return;
        }
        if (AMapException.ERROR_FAILURE_AUTH.equals(str)) {
            this.f1046f = 32;
            return;
        }
        if ("requeust is null".equals(str)) {
            this.f1046f = 1;
            return;
        }
        if ("request url is empty".equals(str)) {
            this.f1046f = 2;
            return;
        }
        if ("response is null".equals(str)) {
            this.f1046f = 3;
            return;
        }
        if ("thread pool has exception".equals(str)) {
            this.f1046f = 4;
            return;
        }
        if ("sdk name is invalid".equals(str)) {
            this.f1046f = 5;
            return;
        }
        if ("sdk info is null".equals(str)) {
            this.f1046f = 6;
            return;
        }
        if ("sdk packages is null".equals(str)) {
            this.f1046f = 7;
            return;
        }
        if ("线程池为空".equals(str)) {
            this.f1046f = 8;
        } else if ("获取对象错误".equals(str)) {
            this.f1046f = 101;
        } else {
            this.f1046f = -1;
        }
    }
}