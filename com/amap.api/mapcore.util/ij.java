package com.amap.api.mapcore.util;

import com.amap.api.maps.AMapException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

/* JADX INFO: compiled from: BaseNetManager.java */
/* JADX INFO: loaded from: classes.dex */
public class ij {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f1353a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f1354b = "";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static ij f1355c;

    /* JADX INFO: compiled from: BaseNetManager.java */
    public interface a {
        URLConnection a(Proxy proxy, URL url);
    }

    public static ij a() {
        if (f1355c == null) {
            f1355c = new ij();
        }
        return f1355c;
    }

    public byte[] a(iq iqVar) throws gh {
        try {
            is isVarB = b(iqVar, true);
            if (isVarB != null) {
                return isVarB.f1402a;
            }
            return null;
        } catch (gh e2) {
            throw e2;
        }
    }

    public byte[] b(iq iqVar) throws gh {
        try {
            is isVarB = b(iqVar, false);
            if (isVarB != null) {
                return isVarB.f1402a;
            }
            return null;
        } catch (gh e2) {
            throw e2;
        } catch (Throwable th) {
            hk.a(th, "bm", "msp");
            throw new gh(AMapException.ERROR_UNKNOWN);
        }
    }

    protected void c(iq iqVar) throws gh {
        if (iqVar == null) {
            throw new gh("requeust is null");
        }
        if (iqVar.getURL() == null || "".equals(iqVar.getURL())) {
            throw new gh("request url is empty");
        }
    }

    public byte[] d(iq iqVar) throws gh {
        try {
            is isVarA = a(iqVar, false);
            if (isVarA != null) {
                return isVarA.f1402a;
            }
            return null;
        } catch (gh e2) {
            throw e2;
        }
    }

    public byte[] e(iq iqVar) throws gh {
        try {
            is isVarA = a(iqVar, true);
            if (isVarA != null) {
                return isVarA.f1402a;
            }
            return null;
        } catch (gh e2) {
            throw e2;
        }
    }

    public is a(iq iqVar, boolean z) throws gh {
        is isVarA;
        int iA = in.a(2, iqVar);
        try {
            isVarA = a(iqVar, z, iA);
        } catch (gh e2) {
            if (e2.f() || !in.a(iA)) {
                throw e2;
            }
            isVarA = null;
        }
        if ((isVarA != null && isVarA.f1402a != null && isVarA.f1402a.length > 0) || !in.a(iA)) {
            return isVarA;
        }
        try {
            return a(iqVar, z, 3);
        } catch (gh e3) {
            throw e3;
        }
    }

    public is b(iq iqVar, boolean z) throws gh {
        is isVarA;
        int iA = in.a(2, iqVar);
        try {
            isVarA = a(iqVar, z, iA);
        } catch (gh e2) {
            if (!in.a(iA)) {
                throw e2;
            }
            isVarA = null;
        }
        if ((isVarA != null && isVarA.f1402a != null && isVarA.f1402a.length > 0) || !in.a(iA)) {
            return isVarA;
        }
        try {
            return a(iqVar, z, 3);
        } catch (gh e3) {
            throw e3;
        }
    }

    public is a(iq iqVar, boolean z, int i) throws gh {
        try {
            c(iqVar);
            return new in(iqVar, z).a(iqVar.b(), iqVar.c(), iqVar.isIPRequest(), iqVar.getIPDNSName(), iqVar.getRequestHead(), iqVar.d(), iqVar.isIgnoreGZip(), i);
        } catch (gh e2) {
            throw e2;
        } catch (Throwable th) {
            th.printStackTrace();
            throw new gh(AMapException.ERROR_UNKNOWN);
        }
    }
}