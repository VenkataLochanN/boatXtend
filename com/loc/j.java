package com.loc;

import com.amap.api.maps.AMapException;

/* JADX INFO: compiled from: AMapCoreException.java */
/* JADX INFO: loaded from: classes3.dex */
public final class j extends Exception {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5229a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f5230b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f5231c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f5232d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f5233e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f5234f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private volatile boolean f5235g;

    public j(String str) {
        super(str);
        this.f5229a = AMapException.ERROR_UNKNOWN;
        this.f5230b = "";
        this.f5231c = "";
        this.f5232d = "1900";
        this.f5233e = "UnknownError";
        this.f5234f = -1;
        this.f5235g = false;
        this.f5229a = str;
        if (AMapException.ERROR_IO.equals(str)) {
            this.f5234f = 21;
            this.f5232d = "1902";
            this.f5233e = "IOException";
            return;
        }
        if (AMapException.ERROR_SOCKET.equals(str)) {
            this.f5234f = 22;
            return;
        }
        if (AMapException.ERROR_SOCKE_TIME_OUT.equals(str)) {
            this.f5234f = 23;
            this.f5232d = "1802";
            this.f5233e = "SocketTimeoutException";
            return;
        }
        if (AMapException.ERROR_INVALID_PARAMETER.equals(str)) {
            this.f5234f = 24;
            this.f5232d = "1901";
            this.f5233e = "IllegalArgumentException";
            return;
        }
        if (AMapException.ERROR_NULL_PARAMETER.equals(str)) {
            this.f5234f = 25;
            this.f5232d = "1903";
            this.f5233e = "NullPointException";
            return;
        }
        if (AMapException.ERROR_URL.equals(str)) {
            this.f5234f = 26;
            this.f5232d = "1803";
            this.f5233e = "MalformedURLException";
            return;
        }
        if (AMapException.ERROR_UNKNOW_HOST.equals(str)) {
            this.f5234f = 27;
            this.f5232d = "1804";
            this.f5233e = "UnknownHostException";
            return;
        }
        if (AMapException.ERROR_UNKNOW_SERVICE.equals(str)) {
            this.f5234f = 28;
            this.f5232d = "1805";
            this.f5233e = "CannotConnectToHostException";
            return;
        }
        if (AMapException.ERROR_PROTOCOL.equals(str)) {
            this.f5234f = 29;
            this.f5232d = "1801";
            this.f5233e = "ProtocolException";
            return;
        }
        if (AMapException.ERROR_CONNECTION.equals(str)) {
            this.f5234f = 30;
            this.f5232d = "1806";
            this.f5233e = "ConnectionException";
            return;
        }
        if ("服务QPS超限".equalsIgnoreCase(str)) {
            this.f5234f = 30;
            this.f5232d = "2001";
            this.f5233e = "ConnectionException";
            return;
        }
        if (AMapException.ERROR_UNKNOWN.equals(str)) {
            this.f5234f = 31;
            return;
        }
        if (AMapException.ERROR_FAILURE_AUTH.equals(str)) {
            this.f5234f = 32;
            return;
        }
        if ("requeust is null".equals(str)) {
            this.f5234f = 1;
            return;
        }
        if ("request url is empty".equals(str)) {
            this.f5234f = 2;
            return;
        }
        if ("response is null".equals(str)) {
            this.f5234f = 3;
            return;
        }
        if ("thread pool has exception".equals(str)) {
            this.f5234f = 4;
            return;
        }
        if ("sdk name is invalid".equals(str)) {
            this.f5234f = 5;
            return;
        }
        if ("sdk info is null".equals(str)) {
            this.f5234f = 6;
            return;
        }
        if ("sdk packages is null".equals(str)) {
            this.f5234f = 7;
            return;
        }
        if ("线程池为空".equals(str)) {
            this.f5234f = 8;
        } else if ("获取对象错误".equals(str)) {
            this.f5234f = 101;
        } else {
            this.f5234f = -1;
        }
    }

    public j(String str, String str2, String str3) {
        this(str);
        this.f5230b = str2;
        this.f5231c = str3;
    }

    public final String a() {
        return this.f5229a;
    }

    public final void a(int i) {
        this.f5234f = i;
    }

    public final String b() {
        return this.f5232d;
    }

    public final String c() {
        return this.f5233e;
    }

    public final String d() {
        return this.f5230b;
    }

    public final String e() {
        return this.f5231c;
    }

    public final int f() {
        return this.f5234f;
    }

    public final boolean g() {
        return this.f5235g;
    }

    public final void h() {
        this.f5235g = true;
    }
}