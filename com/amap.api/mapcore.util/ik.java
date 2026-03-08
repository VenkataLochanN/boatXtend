package com.amap.api.mapcore.util;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: BinaryRequest.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class ik extends gp {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected Context f1356d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected gs f1357e;

    public abstract byte[] e();

    public abstract byte[] f();

    protected String g() {
        return "2.1";
    }

    public boolean h() {
        return true;
    }

    protected boolean k() {
        return false;
    }

    public ik(Context context, gs gsVar) {
        if (context != null) {
            this.f1356d = context.getApplicationContext();
        }
        this.f1357e = gsVar;
    }

    @Override // com.amap.api.mapcore.util.iq
    public Map<String, String> getParams() {
        String strF = gi.f(this.f1356d);
        String strA = gl.a();
        String strA2 = gl.a(this.f1356d, strA, "key=" + strF);
        HashMap map = new HashMap();
        map.put("ts", strA);
        map.put("key", strF);
        map.put("scode", strA2);
        return map;
    }

    @Override // com.amap.api.mapcore.util.iq
    public final byte[] getEntityBytes() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(a());
            byteArrayOutputStream.write(i());
            byteArrayOutputStream.write(l());
            byteArrayOutputStream.write(m());
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            try {
                hk.a(th, "bre", "geb");
                try {
                    byteArrayOutputStream.close();
                    return null;
                } catch (Throwable th2) {
                    hk.a(th2, "bre", "geb");
                    return null;
                }
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    hk.a(th3, "bre", "geb");
                }
            }
        }
    }

    private byte[] a() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(gt.a("PANDORA$"));
            byteArrayOutputStream.write(new byte[]{1});
            byteArrayOutputStream.write(new byte[]{0});
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            try {
                hk.a(th, "bre", "gbh");
                try {
                    byteArrayOutputStream.close();
                    return null;
                } catch (Throwable th2) {
                    hk.a(th2, "bre", "gbh");
                    return null;
                }
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    hk.a(th3, "bre", "gbh");
                }
            }
        }
    }

    public byte[] i() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(new byte[]{3});
            if (h()) {
                byte[] bArrA = gl.a(this.f1356d, k(), n());
                byteArrayOutputStream.write(a(bArrA));
                byteArrayOutputStream.write(bArrA);
            } else {
                byteArrayOutputStream.write(new byte[]{0, 0});
            }
            byte[] bArrA2 = gt.a(g());
            if (bArrA2 != null && bArrA2.length > 0) {
                byteArrayOutputStream.write(a(bArrA2));
                byteArrayOutputStream.write(bArrA2);
            } else {
                byteArrayOutputStream.write(new byte[]{0, 0});
            }
            byte[] bArrA3 = gt.a(j());
            if (bArrA3 != null && bArrA3.length > 0) {
                byteArrayOutputStream.write(a(bArrA3));
                byteArrayOutputStream.write(bArrA3);
            } else {
                byteArrayOutputStream.write(new byte[]{0, 0});
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            try {
                hk.a(th, "bre", "gpd");
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th2) {
                    hk.a(th2, "bre", "gred");
                }
                return new byte[]{0};
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    hk.a(th3, "bre", "gred");
                }
            }
        }
    }

    public String j() {
        return String.format("platform=Android&sdkversion=%s&product=%s", this.f1357e.c(), this.f1357e.a());
    }

    protected byte[] a(byte[] bArr) {
        return gt.a(bArr.length, 2);
    }

    private byte[] l() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArrE = e();
            if (bArrE != null && bArrE.length != 0) {
                byteArrayOutputStream.write(new byte[]{1});
                byteArrayOutputStream.write(a(bArrE));
                byteArrayOutputStream.write(bArrE);
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(new byte[]{0});
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th) {
                hk.a(th, "bre", "grrd");
            }
            return byteArray;
        } catch (Throwable th2) {
            try {
                hk.a(th2, "bre", "grrd");
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    hk.a(th3, "bre", "grrd");
                }
                return new byte[]{0};
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th4) {
                    hk.a(th4, "bre", "grrd");
                }
            }
        }
    }

    private byte[] m() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArrF = f();
            if (bArrF != null && bArrF.length != 0) {
                byteArrayOutputStream.write(new byte[]{1});
                byte[] bArrA = gl.a(this.f1356d, bArrF);
                byteArrayOutputStream.write(a(bArrA));
                byteArrayOutputStream.write(bArrA);
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(new byte[]{0});
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th) {
                hk.a(th, "bre", "gred");
            }
            return byteArray;
        } catch (Throwable th2) {
            try {
                hk.a(th2, "bre", "gred");
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    hk.a(th3, "bre", "gred");
                }
                return new byte[]{0};
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th4) {
                    hk.a(th4, "bre", "gred");
                }
            }
        }
    }

    private boolean n() {
        gs gsVar = this.f1357e;
        return gsVar != null && "navi".equals(gsVar.a());
    }
}