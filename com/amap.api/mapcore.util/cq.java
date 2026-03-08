package com.amap.api.mapcore.util;

import android.opengl.GLES20;
import android.os.RemoteException;
import android.util.Log;
import androidx.core.view.ViewCompat;
import com.amap.api.mapcore.util.de;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.BaseHoleOptions;
import com.amap.api.maps.model.CircleHoleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.PolygonHoleOptions;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IOverlay;
import com.autonavi.base.ae.gmap.GLMapState;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.api.mapcore.overlays.ICircleDelegate;
import com.autonavi.base.amap.mapcore.FPoint;
import com.autonavi.base.amap.mapcore.MapConfig;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: CircleDelegateImp.java */
/* JADX INFO: loaded from: classes.dex */
public class cq implements ICircleDelegate {
    private String i;
    private IAMapDelegate j;
    private FloatBuffer k;
    private List<BaseHoleOptions> p;
    private List<BaseHoleOptions> q;
    private int r;
    private int s;
    private FloatBuffer t;
    private FloatBuffer u;
    private de.e y;
    private static Object v = new Object();
    private static float z = 4.0075016E7f;
    private static int A = 256;
    private static int B = 20;
    private static double C = 1.0E10d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private LatLng f433b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private double f434c = 0.0d;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f435d = 10.0f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f436e = ViewCompat.MEASURED_STATE_MASK;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f437f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private float f438g = 0.0f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f439h = true;
    private int l = 0;
    private boolean m = false;
    private IPoint n = IPoint.obtain();
    private FPoint o = FPoint.obtain();
    private int w = -1;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    float f432a = 0.0f;
    private boolean x = false;

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
    public void setAboveMaskLayer(boolean z2) {
    }

    public cq(IAMapDelegate iAMapDelegate) {
        this.j = iAMapDelegate;
        try {
            this.i = getId();
        } catch (RemoteException e2) {
            hn.c(e2, "CircleDelegateImp", "create");
            e2.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void remove() throws RemoteException {
        this.j.removeGLOverlay(getId());
        this.j.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public String getId() throws RemoteException {
        if (this.i == null) {
            this.i = this.j.createId("Circle");
        }
        return this.i;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setZIndex(float f2) throws RemoteException {
        this.f438g = f2;
        this.j.changeGLOverlayIndex();
        this.j.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public float getZIndex() throws RemoteException {
        return this.f438g;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setVisible(boolean z2) throws RemoteException {
        this.f439h = z2;
        this.j.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isVisible() throws RemoteException {
        return this.f439h;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean equalsRemote(IOverlay iOverlay) throws RemoteException {
        return equals(iOverlay) || iOverlay.getId().equals(getId());
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ICircle
    public int getDottedLineType() {
        return this.w;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ICircle
    public void setDottedLineType(int i) {
        this.w = i;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public boolean calMapFPoint() throws RemoteException {
        synchronized (v) {
            int i = 0;
            this.m = false;
            if (this.f433b != null) {
                float[] fArr = new float[1086];
                double dB = b(this.f433b.latitude) * this.f434c;
                this.j.getMapProjection();
                this.o.x = this.n.x - ((int) this.j.getMapConfig().getSX());
                this.o.y = this.n.y - ((int) this.j.getMapConfig().getSY());
                fArr[0] = this.o.x;
                fArr[1] = this.o.y;
                fArr[2] = 0.0f;
                while (i < 361) {
                    double d2 = (((double) i) * 3.141592653589793d) / 180.0d;
                    double dSin = Math.sin(d2) * dB;
                    double dCos = Math.cos(d2) * dB;
                    int i2 = (int) (((double) this.n.x) + dSin);
                    int i3 = (int) (((double) this.n.y) + dCos);
                    this.o.x = i2 - ((int) this.j.getMapConfig().getSX());
                    this.o.y = i3 - ((int) this.j.getMapConfig().getSY());
                    i++;
                    int i4 = i * 3;
                    fArr[i4] = this.o.x;
                    fArr[i4 + 1] = this.o.y;
                    fArr[i4 + 2] = 0.0f;
                }
                this.l = fArr.length / 3;
                this.k = er.a(fArr);
            }
        }
        return true;
    }

    private void b() {
        IAMapDelegate iAMapDelegate = this.j;
        if (iAMapDelegate != null) {
            this.y = (de.e) iAMapDelegate.getGLShader(3);
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public void draw(MapConfig mapConfig) throws RemoteException {
        if (this.f433b == null || this.f434c <= 0.0d || !this.f439h) {
            return;
        }
        calMapFPoint();
        c();
        if (this.k != null && this.l > 0) {
            de.e eVar = this.y;
            if (eVar == null || eVar.c()) {
                b();
            }
            this.f432a = this.j.getMapConfig().getMapPerPixelUnitLength();
            int dottedLineTextureID = this.j.getDottedLineTextureID(this.w);
            if (dottedLineTextureID == -1) {
                dottedLineTextureID = this.j.getLineTextureID();
            }
            dy.a(this.y, this.f437f, this.f436e, this.k, this.f435d, this.l, this.j.getFinalMatrix(), this.f432a, dottedLineTextureID, this.j.getLineTextureRatio(), this.x || (this.w != -1), true);
        }
        d();
        this.m = true;
    }

    void a() {
        this.l = 0;
        FloatBuffer floatBuffer = this.k;
        if (floatBuffer != null) {
            floatBuffer.clear();
        }
        this.j.setRunLowFrame(false);
        setHoleOptions(this.q);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ICircle
    public void setCenter(LatLng latLng) throws RemoteException {
        synchronized (v) {
            if (latLng != null) {
                this.f433b = latLng;
                GLMapState.lonlat2Geo(latLng.longitude, latLng.latitude, this.n);
                a();
            }
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ICircle
    public LatLng getCenter() throws RemoteException {
        return this.f433b;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ICircle
    public void setRadius(double d2) throws RemoteException {
        this.f434c = d2;
        a();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ICircle
    public double getRadius() throws RemoteException {
        return this.f434c;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ICircle
    public void setStrokeWidth(float f2) throws RemoteException {
        this.f435d = f2;
        this.j.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ICircle
    public float getStrokeWidth() throws RemoteException {
        return this.f435d;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ICircle
    public void setStrokeColor(int i) throws RemoteException {
        this.f436e = i;
        this.j.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ICircle
    public int getStrokeColor() throws RemoteException {
        return this.f436e;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ICircle
    public void setFillColor(int i) throws RemoteException {
        this.f437f = i;
        this.j.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ICircle
    public int getFillColor() throws RemoteException {
        return this.f437f;
    }

    private float a(double d2) {
        return (float) ((Math.cos((d2 * 3.141592653589793d) / 180.0d) * ((double) z)) / ((double) (A << B)));
    }

    private double b(double d2) {
        return 1.0d / ((double) a(d2));
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void destroy() {
        try {
            this.f433b = null;
            if (this.k != null) {
                this.k.clear();
                this.k = null;
            }
            if (this.t != null) {
                this.t.clear();
                this.t = null;
            }
            if (this.u != null) {
                this.u.clear();
                this.u = null;
            }
            if (this.p != null) {
                this.p.clear();
            }
            if (this.q != null) {
                this.q.clear();
            }
            this.p = null;
            this.q = null;
        } catch (Throwable th) {
            hn.c(th, "CircleDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "CircleDelegateImp destroy");
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ICircle
    public boolean contains(LatLng latLng) throws RemoteException {
        List<BaseHoleOptions> list = this.p;
        if (list != null && list.size() > 0) {
            Iterator<BaseHoleOptions> it = this.p.iterator();
            while (it.hasNext()) {
                if (er.a(it.next(), latLng)) {
                    return false;
                }
            }
        }
        return this.f434c >= ((double) AMapUtils.calculateLineDistance(this.f433b, latLng));
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public boolean isDrawFinish() {
        return this.m;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ICircle
    public void setHoleOptions(List<BaseHoleOptions> list) {
        try {
            this.q = list;
            if (this.p == null) {
                this.p = new ArrayList();
            } else {
                this.p.clear();
            }
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    BaseHoleOptions baseHoleOptions = list.get(i);
                    if (baseHoleOptions instanceof PolygonHoleOptions) {
                        PolygonHoleOptions polygonHoleOptions = (PolygonHoleOptions) baseHoleOptions;
                        if (a(polygonHoleOptions) && !er.a(this.p, polygonHoleOptions)) {
                            this.p.add(polygonHoleOptions);
                        }
                    } else if (baseHoleOptions instanceof CircleHoleOptions) {
                        CircleHoleOptions circleHoleOptions = (CircleHoleOptions) baseHoleOptions;
                        if (b(circleHoleOptions) && !er.a(this.p, circleHoleOptions)) {
                            this.p.add(circleHoleOptions);
                        }
                    }
                }
            }
        } catch (Throwable th) {
            hn.c(th, "PolygonDelegateImp", "setHoleOptions");
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ICircle
    public List<BaseHoleOptions> getHoleOptions() throws RemoteException {
        return this.p;
    }

    public void a(boolean z2) {
        this.x = z2;
    }

    private boolean a(PolygonHoleOptions polygonHoleOptions) {
        boolean zContains = true;
        try {
            List<LatLng> points = polygonHoleOptions.getPoints();
            for (int i = 0; i < points.size() && (zContains = contains(points.get(i))); i++) {
            }
        } catch (Throwable th) {
            hn.c(th, "CircleDelegateImp", "isPolygonInCircle");
            th.printStackTrace();
        }
        return zContains;
    }

    private boolean b(CircleHoleOptions circleHoleOptions) {
        try {
            return ((double) AMapUtils.calculateLineDistance(circleHoleOptions.getCenter(), getCenter())) <= getRadius() - circleHoleOptions.getRadius();
        } catch (Throwable th) {
            hn.c(th, "CircleDelegateImp", "isCircleInCircle");
            th.printStackTrace();
            return true;
        }
    }

    private void c() throws RemoteException {
        MapConfig mapConfig = this.j.getMapConfig();
        List<BaseHoleOptions> list = this.p;
        if (list == null || list.size() <= 0) {
            return;
        }
        GLES20.glClearStencil(0);
        GLES20.glStencilMask(255);
        GLES20.glClear(1024);
        GLES20.glFlush();
        GLES20.glEnable(2960);
        GLES20.glColorMask(false, false, false, false);
        GLES20.glStencilFunc(512, 1, 255);
        GLES20.glStencilOp(7681, 7680, 7680);
        for (int i = 0; i < this.p.size(); i++) {
            BaseHoleOptions baseHoleOptions = this.p.get(i);
            boolean z2 = baseHoleOptions instanceof PolygonHoleOptions;
            if (z2) {
                a(a(((PolygonHoleOptions) baseHoleOptions).getPoints()), (int) mapConfig.getSX(), (int) mapConfig.getSY());
            } else if (baseHoleOptions instanceof CircleHoleOptions) {
                this.j.changeGLOverlayIndex();
                a((CircleHoleOptions) baseHoleOptions);
            }
            if (this.t != null && this.r > 0) {
                de.e eVar = this.y;
                if (eVar == null || eVar.c()) {
                    b();
                }
                if (z2) {
                    dy.a(this.y, -1, this.f437f, this.t, getStrokeWidth(), this.u, this.r, this.s, this.j.getFinalMatrix(), this.j.getLineTextureID(), this.j.getLineTextureRatio(), mapConfig.getMapPerPixelUnitLength(), 3, 0, this.x, false);
                } else if (baseHoleOptions instanceof CircleHoleOptions) {
                    dy.a(this.y, -1, -1, this.t, 10.0f, this.r, this.j.getFinalMatrix(), mapConfig.getMapPerPixelUnitLength(), this.j.getLineTextureID(), this.j.getLineTextureRatio(), this.x, false);
                }
            }
        }
        GLES20.glColorMask(true, true, true, true);
        GLES20.glStencilFunc(517, 1, 255);
        GLES20.glStencilMask(0);
    }

    private void d() throws RemoteException {
        GLES20.glClearStencil(0);
        GLES20.glClear(1024);
        GLES20.glDisable(2960);
        MapConfig mapConfig = this.j.getMapConfig();
        List<BaseHoleOptions> list = this.p;
        if (list == null || list.size() <= 0) {
            return;
        }
        for (int i = 0; i < this.p.size(); i++) {
            BaseHoleOptions baseHoleOptions = this.p.get(i);
            boolean z2 = baseHoleOptions instanceof PolygonHoleOptions;
            if (z2) {
                a(a(((PolygonHoleOptions) baseHoleOptions).getPoints()), (int) mapConfig.getSX(), (int) mapConfig.getSY());
            } else if (baseHoleOptions instanceof CircleHoleOptions) {
                this.j.changeGLOverlayIndex();
                a((CircleHoleOptions) baseHoleOptions);
            }
            if (this.t != null && this.r > 0) {
                de.e eVar = this.y;
                if (eVar == null || eVar.c()) {
                    b();
                }
                boolean z3 = this.x || this.w != -1;
                if (z2) {
                    dy.a(this.y, 0, this.f436e, this.t, this.f435d, this.u, this.r, this.s, this.j.getFinalMatrix(), this.j.getLineTextureID(), this.j.getLineTextureRatio(), mapConfig.getMapPerPixelUnitLength(), 3, 0, z3);
                } else if (baseHoleOptions instanceof CircleHoleOptions) {
                    dy.a(this.y, 0, this.f436e, this.t, this.f435d, this.r, this.j.getFinalMatrix(), mapConfig.getMapPerPixelUnitLength(), this.j.getLineTextureID(), this.j.getLineTextureRatio(), z3);
                }
            }
        }
    }

    static IPoint[] a(IPoint[] iPointArr) {
        int length = iPointArr.length;
        double[] dArr = new double[length * 2];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            dArr[i2] = ((double) iPointArr[i].x) * C;
            dArr[i2 + 1] = ((double) iPointArr[i].y) * C;
        }
        en enVarA = new dv().a(dArr);
        int i3 = enVarA.f749b;
        IPoint[] iPointArr2 = new IPoint[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            iPointArr2[i4] = new IPoint();
            iPointArr2[i4].x = (int) (dArr[enVarA.a(i4) * 2] / C);
            iPointArr2[i4].y = (int) (dArr[(enVarA.a(i4) * 2) + 1] / C);
        }
        return iPointArr2;
    }

    private void a(List<IPoint> list, int i, int i2) throws RemoteException {
        if (list.size() < 2) {
            return;
        }
        float[] fArr = new float[list.size() * 3];
        IPoint[] iPointArr = new IPoint[list.size()];
        int i3 = 0;
        for (IPoint iPoint : list) {
            int i4 = i3 * 3;
            fArr[i4] = iPoint.x - i;
            fArr[i4 + 1] = iPoint.y - i2;
            fArr[i4 + 2] = 0.0f;
            iPointArr[i3] = iPoint;
            i3++;
        }
        IPoint[] iPointArrA = a(iPointArr);
        if (iPointArrA.length == 0) {
            if (C == 1.0E10d) {
                C = 1.0E8d;
            } else {
                C = 1.0E10d;
            }
            iPointArrA = a(iPointArr);
        }
        float[] fArr2 = new float[iPointArrA.length * 3];
        int i5 = 0;
        for (IPoint iPoint2 : iPointArrA) {
            int i6 = i5 * 3;
            fArr2[i6] = iPoint2.x - i;
            fArr2[i6 + 1] = iPoint2.y - i2;
            fArr2[i6 + 2] = 0.0f;
            i5++;
        }
        this.r = iPointArr.length;
        this.s = iPointArrA.length;
        this.t = er.a(fArr);
        this.u = er.a(fArr2);
    }

    private List<IPoint> a(List<LatLng> list) throws RemoteException {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            Object obj = null;
            for (LatLng latLng : list) {
                if (!latLng.equals(obj)) {
                    IPoint iPointObtain = IPoint.obtain();
                    this.j.latlon2Geo(latLng.latitude, latLng.longitude, iPointObtain);
                    arrayList.add(iPointObtain);
                    obj = latLng;
                }
            }
            int size = arrayList.size();
            if (size > 1) {
                IPoint iPoint = (IPoint) arrayList.get(0);
                int i = size - 1;
                IPoint iPoint2 = (IPoint) arrayList.get(i);
                if (iPoint.x == iPoint2.x && iPoint.y == iPoint2.y) {
                    arrayList.remove(i);
                }
            }
        }
        if (er.a(arrayList, 0, arrayList.size())) {
            Collections.reverse(arrayList);
        }
        return arrayList;
    }

    public void a(CircleHoleOptions circleHoleOptions) throws RemoteException {
        if (circleHoleOptions.getCenter() != null) {
            IPoint iPointObtain = IPoint.obtain();
            FPoint fPointObtain = FPoint.obtain();
            GLMapState.lonlat2Geo(circleHoleOptions.getCenter().longitude, circleHoleOptions.getCenter().latitude, iPointObtain);
            float[] fArr = new float[1086];
            double dB = b(circleHoleOptions.getCenter().latitude) * circleHoleOptions.getRadius();
            fPointObtain.x = iPointObtain.x - ((int) this.j.getMapConfig().getSX());
            fPointObtain.y = iPointObtain.y - ((int) this.j.getMapConfig().getSY());
            int i = 0;
            fArr[0] = fPointObtain.x;
            fArr[1] = fPointObtain.y;
            fArr[2] = 0.0f;
            while (i < 361) {
                double d2 = (((double) i) * 3.141592653589793d) / 180.0d;
                double dSin = Math.sin(d2) * dB;
                double dCos = Math.cos(d2) * dB;
                int i2 = (int) (((double) iPointObtain.x) + dSin);
                int i3 = (int) (((double) iPointObtain.y) + dCos);
                fPointObtain.x = i2 - ((int) this.j.getMapConfig().getSX());
                fPointObtain.y = i3 - ((int) this.j.getMapConfig().getSY());
                i++;
                int i4 = i * 3;
                fArr[i4] = fPointObtain.x;
                fArr[i4 + 1] = fPointObtain.y;
                fArr[i4 + 2] = 0.0f;
            }
            this.r = fArr.length / 3;
            this.t = er.a(fArr);
            iPointObtain.recycle();
            fPointObtain.recycle();
        }
    }
}