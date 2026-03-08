package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.bumptech.glide.load.Key;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: compiled from: LastLocationManager.java */
/* JADX INFO: loaded from: classes3.dex */
public final class i {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static ea f5221b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static ae f5222e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    static long f5223g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    String f5224a = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ea f5225c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    ea f5226d = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    long f5227f = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    boolean f5228h = false;
    private Context i;

    public i(Context context) {
        this.i = context.getApplicationContext();
    }

    private void e() {
        if (f5221b == null || ep.b() - f5223g > 180000) {
            ea eaVarF = f();
            f5223g = ep.b();
            if (eaVarF == null || !ep.a(eaVarF.a())) {
                return;
            }
            f5221b = eaVarF;
        }
    }

    private ea f() {
        Throwable th;
        ea eaVar;
        byte[] bArrB;
        byte[] bArrB2;
        String str = null;
        if (this.i == null) {
            return null;
        }
        a();
        try {
        } catch (Throwable th2) {
            th = th2;
            eaVar = null;
        }
        if (f5222e == null) {
            return null;
        }
        List listA = f5222e.a("_id=1", ea.class);
        if (listA.size() > 0) {
            eaVar = (ea) listA.get(0);
            try {
                byte[] bArrB3 = o.b(eaVar.c());
                String str2 = (bArrB3 == null || bArrB3.length <= 0 || (bArrB2 = dy.b(bArrB3, this.f5224a)) == null || bArrB2.length <= 0) ? null : new String(bArrB2, Key.STRING_CHARSET_NAME);
                byte[] bArrB4 = o.b(eaVar.b());
                if (bArrB4 != null && bArrB4.length > 0 && (bArrB = dy.b(bArrB4, this.f5224a)) != null && bArrB.length > 0) {
                    str = new String(bArrB, Key.STRING_CHARSET_NAME);
                }
                eaVar.a(str);
                str = str2;
            } catch (Throwable th3) {
                th = th3;
                ej.a(th, "LastLocationManager", "readLastFix");
            }
        } else {
            eaVar = null;
        }
        if (!TextUtils.isEmpty(str)) {
            AMapLocation aMapLocation = new AMapLocation("");
            ej.a(aMapLocation, new JSONObject(str));
            if (ep.b(aMapLocation)) {
                eaVar.a(aMapLocation);
            }
        }
        return eaVar;
        ej.a(th, "LastLocationManager", "readLastFix");
        return eaVar;
    }

    public final AMapLocation a(AMapLocation aMapLocation, String str, long j) {
        if (aMapLocation == null || aMapLocation.getErrorCode() == 0 || aMapLocation.getLocationType() == 1 || aMapLocation.getErrorCode() == 7) {
            return aMapLocation;
        }
        try {
            e();
        } catch (Throwable th) {
            th = th;
        }
        if (f5221b != null && f5221b.a() != null) {
            boolean zA = false;
            if (TextUtils.isEmpty(str)) {
                long jB = ep.b() - f5221b.d();
                if (jB >= 0 && jB <= j) {
                    zA = true;
                }
                aMapLocation.setTrustedLevel(3);
            } else {
                zA = ep.a(f5221b.b(), str);
                aMapLocation.setTrustedLevel(2);
            }
            if (!zA) {
                return aMapLocation;
            }
            AMapLocation aMapLocationA = f5221b.a();
            try {
                aMapLocationA.setLocationType(9);
                aMapLocationA.setFixLastLocation(true);
                aMapLocationA.setLocationDetail(aMapLocation.getLocationDetail());
                return aMapLocationA;
            } catch (Throwable th2) {
                th = th2;
                aMapLocation = aMapLocationA;
            }
            ej.a(th, "LastLocationManager", "fixLastLocation");
            return aMapLocation;
        }
        return aMapLocation;
    }

    public final void a() {
        if (this.f5228h) {
            return;
        }
        try {
            if (this.f5224a == null) {
                this.f5224a = dy.a("MD5", n.x(this.i));
            }
            if (f5222e == null) {
                f5222e = new ae(this.i, ae.a((Class<? extends ad>) eb.class));
            }
        } catch (Throwable th) {
            ej.a(th, "LastLocationManager", "<init>:DBOperation");
        }
        this.f5228h = true;
    }

    public final boolean a(AMapLocation aMapLocation, String str) {
        if (this.i != null && aMapLocation != null && ep.a(aMapLocation) && aMapLocation.getLocationType() != 2 && !aMapLocation.isMock() && !aMapLocation.isFixLastLocation()) {
            ea eaVar = new ea();
            eaVar.a(aMapLocation);
            if (aMapLocation.getLocationType() == 1) {
                eaVar.a((String) null);
            } else {
                eaVar.a(str);
            }
            try {
                f5221b = eaVar;
                f5223g = ep.b();
                this.f5225c = eaVar;
                if (this.f5226d != null && ep.a(this.f5226d.a(), eaVar.a()) <= 500.0f) {
                    return false;
                }
                if (ep.b() - this.f5227f > 30000) {
                    return true;
                }
            } catch (Throwable th) {
                ej.a(th, "LastLocationManager", "setLastFix");
            }
        }
        return false;
    }

    public final AMapLocation b() {
        e();
        ea eaVar = f5221b;
        if (eaVar != null && ep.a(eaVar.a())) {
            return f5221b.a();
        }
        return null;
    }

    public final void c() {
        try {
            d();
            this.f5227f = 0L;
            this.f5228h = false;
            this.f5225c = null;
            this.f5226d = null;
        } catch (Throwable th) {
            ej.a(th, "LastLocationManager", "destroy");
        }
    }

    public final void d() {
        String strB;
        try {
            a();
            if (this.f5225c != null && ep.a(this.f5225c.a()) && f5222e != null && this.f5225c != this.f5226d && this.f5225c.d() == 0) {
                String str = this.f5225c.a().toStr();
                String strB2 = this.f5225c.b();
                this.f5226d = this.f5225c;
                String strB3 = null;
                if (TextUtils.isEmpty(str)) {
                    strB = null;
                } else {
                    strB = o.b(dy.a(str.getBytes(Key.STRING_CHARSET_NAME), this.f5224a));
                    if (!TextUtils.isEmpty(strB2)) {
                        strB3 = o.b(dy.a(strB2.getBytes(Key.STRING_CHARSET_NAME), this.f5224a));
                    }
                }
                if (TextUtils.isEmpty(strB)) {
                    return;
                }
                ea eaVar = new ea();
                eaVar.b(strB);
                eaVar.a(ep.b());
                eaVar.a(strB3);
                f5222e.a(eaVar, "_id=1");
                this.f5227f = ep.b();
                if (f5221b != null) {
                    f5221b.a(ep.b());
                }
            }
        } catch (Throwable th) {
            ej.a(th, "LastLocationManager", "saveLastFix");
        }
    }
}