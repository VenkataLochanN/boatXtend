package com.amap.api.fence;

import android.app.PendingIntent;
import android.content.Context;
import com.amap.api.location.DPoint;
import com.loc.a;
import com.loc.ej;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class GeoFenceClient {
    public static final int GEOFENCE_IN = 1;
    public static final int GEOFENCE_OUT = 2;
    public static final int GEOFENCE_STAYED = 4;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Context f41a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    a f42b;

    public GeoFenceClient(Context context) {
        this.f41a = null;
        this.f42b = null;
        try {
            if (context == null) {
                throw new IllegalArgumentException("Context参数不能为null");
            }
            this.f41a = context.getApplicationContext();
            this.f42b = new a(this.f41a);
        } catch (Throwable th) {
            ej.a(th, "GeoFenceClient", "<init>");
        }
    }

    public void addGeoFence(DPoint dPoint, float f2, String str) {
        try {
            this.f42b.a(dPoint, f2, str);
        } catch (Throwable th) {
            ej.a(th, "GeoFenceClient", "addGeoFence round");
        }
    }

    public void addGeoFence(String str, String str2) {
        try {
            this.f42b.a(str, str2);
        } catch (Throwable th) {
            ej.a(th, "GeoFenceClient", "addGeoFence district");
        }
    }

    public void addGeoFence(String str, String str2, DPoint dPoint, float f2, int i, String str3) {
        try {
            this.f42b.a(str, str2, dPoint, f2, i, str3);
        } catch (Throwable th) {
            ej.a(th, "GeoFenceClient", "addGeoFence searche");
        }
    }

    public void addGeoFence(String str, String str2, String str3, int i, String str4) {
        try {
            this.f42b.a(str, str2, str3, i, str4);
        } catch (Throwable th) {
            ej.a(th, "GeoFenceClient", "addGeoFence searche");
        }
    }

    public void addGeoFence(List<DPoint> list, String str) {
        try {
            this.f42b.a(list, str);
        } catch (Throwable th) {
            ej.a(th, "GeoFenceClient", "addGeoFence polygon");
        }
    }

    public PendingIntent createPendingIntent(String str) {
        try {
            return this.f42b.a(str);
        } catch (Throwable th) {
            ej.a(th, "GeoFenceClient", "creatPendingIntent");
            return null;
        }
    }

    public List<GeoFence> getAllGeoFence() {
        ArrayList arrayList = new ArrayList();
        try {
            return this.f42b.b();
        } catch (Throwable th) {
            ej.a(th, "GeoFenceClient", "getGeoFenceList");
            return arrayList;
        }
    }

    public boolean isPause() {
        try {
            return this.f42b.j();
        } catch (Throwable th) {
            ej.a(th, "GeoFenceClient", "isPause");
            return true;
        }
    }

    public void pauseGeoFence() {
        try {
            this.f42b.g();
        } catch (Throwable th) {
            ej.a(th, "GeoFenceClient", "pauseGeoFence");
        }
    }

    public void removeGeoFence() {
        try {
            this.f42b.a();
        } catch (Throwable th) {
            ej.a(th, "GeoFenceClient", "removeGeoFence");
        }
    }

    public boolean removeGeoFence(GeoFence geoFence) {
        try {
            return this.f42b.a(geoFence);
        } catch (Throwable th) {
            ej.a(th, "GeoFenceClient", "removeGeoFence1");
            return false;
        }
    }

    public void resumeGeoFence() {
        try {
            this.f42b.i();
        } catch (Throwable th) {
            ej.a(th, "GeoFenceClient", "resumeGeoFence");
        }
    }

    public void setActivateAction(int i) {
        try {
            this.f42b.a(i);
        } catch (Throwable th) {
            ej.a(th, "GeoFenceClient", "setActivatesAction");
        }
    }

    public void setGeoFenceAble(String str, boolean z) {
        try {
            this.f42b.a(str, z);
        } catch (Throwable th) {
            ej.a(th, "GeoFenceClient", "setGeoFenceAble");
        }
    }

    public void setGeoFenceListener(GeoFenceListener geoFenceListener) {
        try {
            this.f42b.a(geoFenceListener);
        } catch (Throwable th) {
            ej.a(th, "GeoFenceClient", "setGeoFenceListener");
        }
    }
}