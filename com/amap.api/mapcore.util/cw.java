package com.amap.api.mapcore.util;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.RemoteException;
import android.util.Log;
import androidx.core.view.ViewCompat;
import com.amap.api.maps.model.LatLng;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IOverlay;
import com.autonavi.base.ae.gmap.gloverlay.GLOverlay;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.api.mapcore.overlays.INavigateArrowDelegate;
import com.autonavi.base.amap.mapcore.AMapNativeRenderer;
import com.autonavi.base.amap.mapcore.MapConfig;
import com.autonavi.base.amap.mapcore.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/* JADX INFO: compiled from: NavigateArrowDelegateImp.java */
/* JADX INFO: loaded from: classes.dex */
public class cw implements INavigateArrowDelegate {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    float f484c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    float f485d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    float f486e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    float f487f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    boolean f489h;
    float[] i;
    private IAMapDelegate k;
    private String q;
    private float l = 10.0f;
    private int m = ViewCompat.MEASURED_STATE_MASK;
    private int n = ViewCompat.MEASURED_STATE_MASK;
    private float o = 0.0f;
    private boolean p = true;
    private List<IPoint> r = new Vector();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int[] f482a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int[] f483b = null;
    private int s = 0;
    private boolean t = false;
    private boolean u = false;
    private boolean v = false;
    private boolean w = false;
    private Object x = new Object();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    Rect f488g = null;
    int j = 0;
    private String y = null;
    private final int z = Color.argb(0, 0, 0, 0);
    private boolean A = true;

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public boolean calMapFPoint() throws RemoteException {
        return true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isAboveMaskLayer() {
        return false;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setAboveMaskLayer(boolean z) {
    }

    public cw(IAMapDelegate iAMapDelegate) {
        this.f489h = false;
        this.k = iAMapDelegate;
        try {
            this.q = getId();
        } catch (RemoteException e2) {
            hn.c(e2, "NavigateArrowDelegateImp", "create");
            e2.printStackTrace();
        }
        this.f489h = false;
    }

    void a(List<LatLng> list) throws RemoteException {
        synchronized (this.x) {
            this.r.clear();
            if (this.f488g == null) {
                this.f488g = new Rect();
            }
            er.a(this.f488g);
            if (list != null) {
                Object obj = null;
                for (LatLng latLng : list) {
                    if (latLng != null && !latLng.equals(obj)) {
                        IPoint iPointObtain = IPoint.obtain();
                        this.k.latlon2Geo(latLng.latitude, latLng.longitude, iPointObtain);
                        this.r.add(iPointObtain);
                        er.b(this.f488g, iPointObtain.x, iPointObtain.y);
                        obj = latLng;
                    }
                }
            }
            this.s = 0;
            this.f488g.sort();
            int size = this.r.size();
            this.f482a = new int[size];
            this.f483b = new int[size];
            int i = 0;
            for (IPoint iPoint : this.r) {
                this.f482a[i] = iPoint.x;
                this.f483b[i] = iPoint.y;
                i++;
            }
        }
        this.k.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void remove() throws RemoteException {
        if (this.f489h) {
            return;
        }
        IAMapDelegate iAMapDelegate = this.k;
        if (iAMapDelegate != null && iAMapDelegate.getGLMapEngine() != null && this.y != null) {
            this.k.queueEvent(new Runnable() { // from class: com.amap.api.mapcore.util.cw.1
                @Override // java.lang.Runnable
                public void run() {
                    if (cw.this.k == null || cw.this.k.getGLMapEngine() == null) {
                        return;
                    }
                    if (cw.this.y != null) {
                        cw.this.k.getGLMapEngine().removeNativeOverlay(1, cw.this.y);
                    }
                    cw.this.y = null;
                }
            });
        }
        this.k.removeGLOverlay(getId());
        this.k.setRunLowFrame(false);
        this.f489h = true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public String getId() throws RemoteException {
        if (this.q == null) {
            this.q = this.k.createId("NavigateArrow");
        }
        return this.q;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.INavigateArrow
    public void setPoints(List<LatLng> list) throws RemoteException {
        a(list);
        this.A = true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.INavigateArrow
    public List<LatLng> getPoints() throws RemoteException {
        return a();
    }

    private List<LatLng> a() throws RemoteException {
        ArrayList arrayList;
        if (this.r == null) {
            return null;
        }
        synchronized (this.x) {
            arrayList = new ArrayList();
            for (IPoint iPoint : this.r) {
                if (iPoint != null) {
                    DPoint dPointObtain = DPoint.obtain();
                    this.k.geo2Latlng(iPoint.x, iPoint.y, dPointObtain);
                    arrayList.add(new LatLng(dPointObtain.y, dPointObtain.x));
                    dPointObtain.recycle();
                }
            }
        }
        return arrayList;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.INavigateArrow
    public void setWidth(float f2) throws RemoteException {
        this.l = f2;
        this.k.setRunLowFrame(false);
        this.A = true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.INavigateArrow
    public float getWidth() throws RemoteException {
        return this.l;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.INavigateArrow
    public void setTopColor(int i) throws RemoteException {
        this.m = i;
        this.f484c = Color.alpha(i) / 255.0f;
        this.f485d = Color.red(i) / 255.0f;
        this.f486e = Color.green(i) / 255.0f;
        this.f487f = Color.blue(i) / 255.0f;
        this.k.setRunLowFrame(false);
        this.A = true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.INavigateArrow
    public int getTopColor() throws RemoteException {
        return this.m;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.INavigateArrow
    public void setSideColor(int i) throws RemoteException {
        this.n = i;
        this.k.setRunLowFrame(false);
        this.A = true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.INavigateArrow
    public int getSideColor() throws RemoteException {
        return this.n;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setZIndex(float f2) throws RemoteException {
        this.o = f2;
        this.k.changeGLOverlayIndex();
        this.k.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public float getZIndex() throws RemoteException {
        return this.o;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setVisible(boolean z) throws RemoteException {
        this.p = z;
        this.k.setRunLowFrame(false);
        this.A = true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isVisible() throws RemoteException {
        if (this.u) {
            return this.p || this.w;
        }
        return this.p;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean equalsRemote(IOverlay iOverlay) throws RemoteException {
        return equals(iOverlay) || iOverlay.getId().equals(getId());
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public int hashCodeRemote() throws RemoteException {
        return super.hashCode();
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public boolean checkInBounds() {
        Rectangle geoRectangle;
        return (this.f488g == null || (geoRectangle = this.k.getMapConfig().getGeoRectangle()) == null || !geoRectangle.isOverlap(this.f488g)) ? false : true;
    }

    public boolean a(MapConfig mapConfig) throws RemoteException {
        synchronized (this.x) {
            int sx = (int) mapConfig.getSX();
            int sy = (int) mapConfig.getSY();
            int i = 0;
            this.t = false;
            int size = this.r.size();
            if (this.i == null || this.i.length < size * 3) {
                this.i = new float[size * 3];
            }
            this.j = size * 3;
            for (IPoint iPoint : this.r) {
                int i2 = i * 3;
                this.i[i2] = iPoint.x - sx;
                this.i[i2 + 1] = iPoint.y - sy;
                this.i[i2 + 2] = 0.0f;
                i++;
            }
            this.s = this.r.size();
        }
        return true;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public void draw(MapConfig mapConfig) throws RemoteException {
        List<IPoint> list;
        if (this.f489h || (list = this.r) == null || list.size() == 0 || this.l <= 0.0f) {
            return;
        }
        if (!this.u) {
            if (this.y != null && this.v) {
                this.k.getGLMapEngine().updateNativeArrowOverlay(1, this.y, this.f482a, this.f483b, this.m, this.n, this.z, this.l, 111, AMapEngineUtils.ARROW_LINE_OUTER_TEXTURE_ID, 333, false);
                this.A = false;
            }
            a(this.k.getMapConfig());
            if (this.i != null && this.s > 0) {
                AMapNativeRenderer.nativeDrawLineByTextureID(this.i, this.j, this.k.getMapProjection().getMapLenWithWin((int) this.l), this.k.getLineTextureID(), this.k.getLineTextureRatio(), this.f485d, this.f486e, this.f487f, this.f484c, 0.0f, false, true, true, this.k.getFinalMatrix(), 2, 0);
                this.v = false;
                this.w = false;
            }
        } else {
            IAMapDelegate iAMapDelegate = this.k;
            if (iAMapDelegate != null && iAMapDelegate.getGLMapEngine() != null) {
                if (this.y == null) {
                    this.y = this.k.getGLMapEngine().addNativeOverlay(1, GLOverlay.EAMapOverlayTpye.AMAPOVERLAY_ARROW.ordinal(), hashCode());
                }
                if (this.y != null && this.A) {
                    this.k.getGLMapEngine().updateNativeArrowOverlay(1, this.y, this.f482a, this.f483b, this.m, this.n, this.z, this.l, 111, AMapEngineUtils.ARROW_LINE_OUTER_TEXTURE_ID, 333, this.p);
                    this.v = true;
                    this.w = this.p;
                    this.A = false;
                }
            }
        }
        this.t = true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void destroy() {
        try {
            remove();
            if (this.i != null) {
                this.i = null;
            }
        } catch (Throwable th) {
            hn.c(th, "NavigateArrowDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "NavigateArrowDelegateImp destroy");
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public boolean isDrawFinish() {
        return this.t;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.INavigateArrow
    public void set3DModel(boolean z) {
        this.u = z;
        this.w = this.p;
        this.A = true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.INavigateArrow
    public boolean is3DModel() {
        return this.u;
    }
}