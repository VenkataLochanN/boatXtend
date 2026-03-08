package com.amap.api.mapcore.util;

import android.graphics.Color;
import android.os.RemoteException;
import android.util.Log;
import androidx.core.view.ViewCompat;
import com.amap.api.maps.model.LatLng;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IOverlay;
import com.autonavi.base.ae.gmap.GLMapState;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.api.mapcore.overlays.IArcDelegate;
import com.autonavi.base.amap.mapcore.AMapNativeRenderer;
import com.autonavi.base.amap.mapcore.FPoint;
import com.autonavi.base.amap.mapcore.MapConfig;

/* JADX INFO: compiled from: ArcDelegateImp.java */
/* JADX INFO: loaded from: classes.dex */
public class co implements IArcDelegate {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    float f416a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    float f417b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    float f418c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    float f419d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private LatLng f420e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private LatLng f421f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private LatLng f422g;
    private String l;
    private IAMapDelegate m;
    private float[] n;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float f423h = 10.0f;
    private int i = ViewCompat.MEASURED_STATE_MASK;
    private float j = 0.0f;
    private boolean k = true;
    private int o = 0;
    private boolean p = false;
    private double q = 0.0d;
    private double r = 0.0d;
    private double s = 0.0d;
    private double t = 0.0d;
    private double u = 0.0d;

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public boolean checkInBounds() {
        return true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public int hashCodeRemote() throws RemoteException {
        return 0;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isAboveMaskLayer() {
        return false;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setAboveMaskLayer(boolean z) {
    }

    public co(IAMapDelegate iAMapDelegate) {
        this.m = iAMapDelegate;
        try {
            this.l = getId();
        } catch (RemoteException e2) {
            hn.c(e2, "ArcDelegateImp", "create");
            e2.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void remove() throws RemoteException {
        this.m.removeGLOverlay(getId());
        this.m.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public String getId() throws RemoteException {
        if (this.l == null) {
            this.l = this.m.createId("Arc");
        }
        return this.l;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setZIndex(float f2) throws RemoteException {
        this.j = f2;
        this.m.changeGLOverlayIndex();
        this.m.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public float getZIndex() throws RemoteException {
        return this.j;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setVisible(boolean z) throws RemoteException {
        this.k = z;
        this.m.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isVisible() throws RemoteException {
        return this.k;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean equalsRemote(IOverlay iOverlay) throws RemoteException {
        return equals(iOverlay) || iOverlay.getId().equals(getId());
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public boolean calMapFPoint() throws RemoteException {
        FPoint[] fPointArr;
        int i;
        if (this.f420e == null || this.f421f == null || this.f422g == null || !this.k) {
            return false;
        }
        try {
            this.p = false;
            GLMapState mapProjection = this.m.getMapProjection();
            if (!a()) {
                b();
                return true;
            }
            DPoint dPointObtain = DPoint.obtain(this.t, this.u);
            int iAbs = (int) ((Math.abs(this.s - this.r) * 180.0d) / 3.141592653589793d);
            if (iAbs == 0) {
                b();
                return true;
            }
            double d2 = (this.s - this.r) / ((double) iAbs);
            FPoint[] fPointArr2 = new FPoint[iAbs + 1];
            this.n = new float[fPointArr2.length * 3];
            int i2 = 0;
            while (i2 <= iAbs) {
                if (i2 == iAbs) {
                    FPoint fPointObtain = FPoint.obtain();
                    this.m.getLatLng2Map(this.f422g.latitude, this.f422g.longitude, fPointObtain);
                    fPointArr2[i2] = fPointObtain;
                    fPointArr = fPointArr2;
                    i = i2;
                } else {
                    fPointArr = fPointArr2;
                    i = i2;
                    fPointArr[i] = a(mapProjection, (((double) i2) * d2) + this.r, dPointObtain.x, dPointObtain.y);
                }
                fPointArr[i] = a(mapProjection, (((double) i) * d2) + this.r, dPointObtain.x, dPointObtain.y);
                int i3 = i * 3;
                this.n[i3] = fPointArr[i].x;
                this.n[i3 + 1] = fPointArr[i].y;
                this.n[i3 + 2] = 0.0f;
                i2 = i + 1;
                fPointArr2 = fPointArr;
            }
            dPointObtain.recycle();
            this.o = fPointArr2.length;
            return true;
        } catch (Throwable th) {
            hn.c(th, "ArcDelegateImp", "calMapFPoint");
            th.printStackTrace();
            return false;
        }
    }

    private FPoint a(GLMapState gLMapState, double d2, double d3, double d4) {
        int iCos = (int) (d3 + (Math.cos(d2) * this.q));
        int i = (int) (d4 + ((-Math.sin(d2)) * this.q));
        FPoint fPointObtain = FPoint.obtain();
        if (this.m.getMapConfig() != null) {
            fPointObtain.x = iCos - ((int) r8.getSX());
            fPointObtain.y = i - ((int) r8.getSY());
        }
        return fPointObtain;
    }

    private boolean a() {
        IPoint iPointObtain = IPoint.obtain();
        this.m.latlon2Geo(this.f420e.latitude, this.f420e.longitude, iPointObtain);
        IPoint iPointObtain2 = IPoint.obtain();
        this.m.latlon2Geo(this.f421f.latitude, this.f421f.longitude, iPointObtain2);
        IPoint iPointObtain3 = IPoint.obtain();
        this.m.latlon2Geo(this.f422g.latitude, this.f422g.longitude, iPointObtain3);
        double d2 = iPointObtain.x;
        double d3 = iPointObtain.y;
        double d4 = iPointObtain2.x;
        double d5 = iPointObtain2.y;
        double d6 = iPointObtain3.x;
        double d7 = iPointObtain3.y;
        double d8 = d4 - d2;
        double d9 = d7 - d3;
        double d10 = d6 - d2;
        double d11 = d5 - d3;
        double d12 = ((d8 * 2.0d) * d9) - ((d10 * 2.0d) * d11);
        double d13 = ((d11 * 2.0d) * d10) - ((2.0d * d9) * d8);
        if (d12 != 0.0d && d13 != 0.0d) {
            double d14 = d5 * d5;
            double d15 = d3 * d3;
            double d16 = d4 * d4;
            double d17 = d2 * d2;
            double d18 = d7 * d7;
            double d19 = d6 * d6;
            this.t = ((d9 * (((d14 - d15) + d16) - d17)) + (d11 * (((d15 - d18) + d17) - d19))) / d12;
            this.u = ((d10 * (((d16 - d17) + d14) - d15)) + (d8 * (((d17 - d19) + d15) - d18))) / d13;
            if (!Double.isNaN(this.t) && !Double.isNaN(this.u) && !Double.isInfinite(this.t) && !Double.isInfinite(this.u)) {
                double d20 = this.t;
                double d21 = (d2 - d20) * (d2 - d20);
                double d22 = this.u;
                this.q = Math.sqrt(d21 + ((d3 - d22) * (d3 - d22)));
                this.r = a(this.t, this.u, d2, d3);
                double dA = a(this.t, this.u, d4, d5);
                this.s = a(this.t, this.u, d6, d7);
                double d23 = this.r;
                double d24 = this.s;
                if (d23 < d24) {
                    if (dA <= d23 || dA >= d24) {
                        this.s -= 6.283185307179586d;
                    }
                } else if (dA <= d24 || dA >= d23) {
                    this.s += 6.283185307179586d;
                }
                iPointObtain.recycle();
                iPointObtain2.recycle();
                iPointObtain3.recycle();
                return true;
            }
        }
        return false;
    }

    private double a(double d2, double d3, double d4, double d5) {
        double dSignum = (d3 - d5) / this.q;
        if (Math.abs(dSignum) > 1.0d) {
            dSignum = Math.signum(dSignum);
        }
        double dAsin = Math.asin(dSignum);
        return dAsin >= 0.0d ? d4 < d2 ? 3.141592653589793d - Math.abs(dAsin) : dAsin : d4 < d2 ? 3.141592653589793d - dAsin : dAsin + 6.283185307179586d;
    }

    private void b() {
        this.n = new float[fPointArr.length * 3];
        FPoint fPointObtain = FPoint.obtain();
        this.m.getLatLng2Map(this.f420e.latitude, this.f420e.longitude, fPointObtain);
        FPoint fPointObtain2 = FPoint.obtain();
        this.m.getLatLng2Map(this.f421f.latitude, this.f421f.longitude, fPointObtain2);
        FPoint fPointObtain3 = FPoint.obtain();
        this.m.getLatLng2Map(this.f422g.latitude, this.f422g.longitude, fPointObtain3);
        FPoint[] fPointArr = {fPointObtain, fPointObtain2, fPointObtain3};
        for (int i = 0; i < 3; i++) {
            int i2 = i * 3;
            this.n[i2] = fPointArr[i].x;
            this.n[i2 + 1] = fPointArr[i].y;
            this.n[i2 + 2] = 0.0f;
        }
        this.o = fPointArr.length;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public void draw(MapConfig mapConfig) throws RemoteException {
        if (this.f420e == null || this.f421f == null || this.f422g == null || !this.k) {
            return;
        }
        calMapFPoint();
        if (this.n != null && this.o > 0) {
            float mapLenWithWin = this.m.getMapProjection().getMapLenWithWin((int) this.f423h);
            this.m.getMapProjection().getMapLenWithWin(1);
            float[] fArr = this.n;
            AMapNativeRenderer.nativeDrawLineByTextureID(fArr, fArr.length, mapLenWithWin, this.m.getLineTextureID(), this.m.getLineTextureRatio(), this.f417b, this.f418c, this.f419d, this.f416a, 0.0f, false, true, false, this.m.getFinalMatrix(), 3, 0);
        }
        this.p = true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IArc
    public void setStrokeWidth(float f2) throws RemoteException {
        this.f423h = f2;
        this.m.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IArc
    public float getStrokeWidth() throws RemoteException {
        return this.f423h;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IArc
    public void setStrokeColor(int i) throws RemoteException {
        this.i = i;
        this.f416a = Color.alpha(i) / 255.0f;
        this.f417b = Color.red(i) / 255.0f;
        this.f418c = Color.green(i) / 255.0f;
        this.f419d = Color.blue(i) / 255.0f;
        this.m.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IArc
    public int getStrokeColor() throws RemoteException {
        return this.i;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void destroy() {
        try {
            this.f420e = null;
            this.f421f = null;
            this.f422g = null;
        } catch (Throwable th) {
            hn.c(th, "ArcDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "ArcDelegateImp destroy");
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public boolean isDrawFinish() {
        return this.p;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IArcDelegate
    public void setStart(LatLng latLng) {
        this.f420e = latLng;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IArcDelegate
    public void setPassed(LatLng latLng) {
        this.f421f = latLng;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IArcDelegate
    public void setEnd(LatLng latLng) {
        this.f422g = latLng;
    }
}