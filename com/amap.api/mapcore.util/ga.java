package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.maps.AMapException;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: compiled from: AbstractBasicHandler.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class ga<T, V> extends dp {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected T f1006d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected int f1007e = 1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected Context f1008f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected String f1009g;

    protected abstract String a();

    protected abstract V b(String str) throws fz;

    protected V f() {
        return null;
    }

    public ga(Context context, T t) {
        a(context, t);
    }

    private void a(Context context, T t) {
        this.f1008f = context;
        this.f1006d = t;
        this.f1007e = 1;
        setSoTimeout(30000);
        setConnectionTimeout(30000);
    }

    protected V b(byte[] bArr) throws fz {
        String str;
        try {
            str = new String(bArr, "utf-8");
        } catch (Exception e2) {
            e2.printStackTrace();
            str = null;
        }
        if (str == null || "".equals(str)) {
            return null;
        }
        gc.a(str, this.f1009g);
        return b(str);
    }

    public V e() throws fz {
        if (this.f1006d == null) {
            return null;
        }
        try {
            return g();
        } catch (fz e2) {
            er.a(e2);
            throw e2;
        }
    }

    private V g() throws fz {
        V vA = null;
        int i = 0;
        while (i < this.f1007e) {
            try {
                setProxy(gr.a(this.f1008f));
                vA = a(makeHttpRequest());
                i = this.f1007e;
            } catch (fz e2) {
                i++;
                if (i >= this.f1007e) {
                    throw new fz(e2.a());
                }
            } catch (gh e3) {
                i++;
                if (i < this.f1007e) {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException unused) {
                        if (AMapException.ERROR_CONNECTION.equals(e3.getMessage()) || AMapException.ERROR_SOCKET.equals(e3.getMessage()) || AMapException.ERROR_UNKNOW_SERVICE.equals(e3.getMessage())) {
                            throw new fz("http或socket连接失败 - ConnectionException");
                        }
                        throw new fz(e3.a());
                    }
                } else {
                    f();
                    if (AMapException.ERROR_CONNECTION.equals(e3.getMessage()) || AMapException.ERROR_SOCKET.equals(e3.getMessage()) || AMapException.ERROR_UNKNOWN.equals(e3.a()) || AMapException.ERROR_UNKNOW_SERVICE.equals(e3.getMessage())) {
                        throw new fz("http或socket连接失败 - ConnectionException");
                    }
                    throw new fz(e3.a());
                }
            }
        }
        return vA;
    }

    private V a(byte[] bArr) throws fz {
        return b(bArr);
    }

    @Override // com.amap.api.mapcore.util.iq
    public Map<String, String> getRequestHead() {
        gs gsVarE = er.e();
        String strB = gsVarE != null ? gsVarE.b() : null;
        Hashtable hashtable = new Hashtable(16);
        hashtable.put("User-Agent", m.f1685c);
        hashtable.put("Accept-Encoding", "gzip");
        hashtable.put("platinfo", String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s", strB, "3dmap"));
        hashtable.put("X-INFO", gl.b(this.f1008f));
        hashtable.put("key", gi.f(this.f1008f));
        hashtable.put("logversion", "2.1");
        return hashtable;
    }
}