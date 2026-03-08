package com.amap.api.mapcore.util;

import android.graphics.Color;
import android.graphics.Rect;
import android.opengl.GLES20;
import android.os.RemoteException;
import androidx.core.view.ViewCompat;
import com.amap.api.mapcore.util.de;
import com.amap.api.maps.model.AMapPara;
import com.amap.api.maps.model.BaseHoleOptions;
import com.amap.api.maps.model.CircleHoleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.PolygonHoleOptions;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IOverlay;
import com.autonavi.base.ae.gmap.GLMapState;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.api.mapcore.overlays.IPolygonDelegate;
import com.autonavi.base.amap.mapcore.FPoint;
import com.autonavi.base.amap.mapcore.MapConfig;
import com.autonavi.base.amap.mapcore.Rectangle;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/* JADX INFO: compiled from: PolygonDelegateImp.java */
/* JADX INFO: loaded from: classes.dex */
public class cy implements IPolygonDelegate {
    private static float A = 4.0075016E7f;
    private static int B = 256;
    private static int C = 20;
    private static double H = 1.0E10d;
    private int D;
    private int E;
    private FloatBuffer F;
    private FloatBuffer G;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private IAMapDelegate f500b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f503e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f504f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private float f505g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f506h;
    private int i;
    private List<LatLng> j;
    private List<LatLng> k;
    private List<BaseHoleOptions> n;
    private FloatBuffer o;
    private FloatBuffer p;
    private de.e z;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f501c = 0.0f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f502d = true;
    private List<IPoint> l = new Vector();
    private List<BaseHoleOptions> m = new Vector();
    private int q = 0;
    private int r = 0;
    private boolean s = false;
    private float t = 0.0f;
    private Object u = new Object();
    private boolean v = false;
    private AMapPara.LineJoinType w = AMapPara.LineJoinType.LineJoinBevel;
    private AMapPara.LineCapType x = AMapPara.LineCapType.LineCapRound;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Rect f499a = null;
    private float y = 0.0f;

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public boolean calMapFPoint() throws RemoteException {
        return false;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isAboveMaskLayer() {
        return false;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setAboveMaskLayer(boolean z) {
    }

    public cy(IAMapDelegate iAMapDelegate) {
        this.f500b = iAMapDelegate;
        try {
            this.f504f = getId();
        } catch (RemoteException e2) {
            hn.c(e2, "PolygonDelegateImp", "create");
            e2.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void remove() throws RemoteException {
        this.f500b.removeGLOverlay(getId());
        this.f500b.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public String getId() throws RemoteException {
        if (this.f504f == null) {
            this.f504f = this.f500b.createId("Polygon");
        }
        return this.f504f;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolygon
    public void setPoints(List<LatLng> list) throws RemoteException {
        synchronized (this.u) {
            this.k = list;
            a(list);
            this.f500b.setRunLowFrame(false);
            setHoleOptions(this.n);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolygon
    public List<LatLng> getPoints() throws RemoteException {
        return this.k;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setZIndex(float f2) throws RemoteException {
        this.f501c = f2;
        this.f500b.changeGLOverlayIndex();
        this.f500b.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public float getZIndex() throws RemoteException {
        return this.f501c;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setVisible(boolean z) throws RemoteException {
        this.f502d = z;
        this.f500b.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isVisible() throws RemoteException {
        return this.f502d;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean equalsRemote(IOverlay iOverlay) throws RemoteException {
        return equals(iOverlay) || iOverlay.getId().equals(getId());
    }

    private void a(List<LatLng> list) throws RemoteException {
        LatLngBounds.Builder builder = LatLngBounds.builder();
        if (this.f499a == null) {
            this.f499a = new Rect();
        }
        er.a(this.f499a);
        this.l.clear();
        if (list != null) {
            Object obj = null;
            for (LatLng latLng : list) {
                if (!latLng.equals(obj)) {
                    IPoint iPointObtain = IPoint.obtain();
                    this.f500b.latlon2Geo(latLng.latitude, latLng.longitude, iPointObtain);
                    this.l.add(iPointObtain);
                    er.b(this.f499a, iPointObtain.x, iPointObtain.y);
                    builder.include(latLng);
                    obj = latLng;
                }
            }
            int size = this.l.size();
            if (size > 1) {
                IPoint iPoint = this.l.get(0);
                int i = size - 1;
                IPoint iPoint2 = this.l.get(i);
                if (iPoint.x == iPoint2.x && iPoint.y == iPoint2.y) {
                    this.l.remove(i);
                }
            }
        }
        this.f499a.sort();
        FloatBuffer floatBuffer = this.o;
        if (floatBuffer != null) {
            floatBuffer.clear();
        }
        FloatBuffer floatBuffer2 = this.p;
        if (floatBuffer2 != null) {
            floatBuffer2.clear();
        }
        List<IPoint> list2 = this.l;
        if (er.a(list2, 0, list2.size())) {
            Collections.reverse(this.l);
        }
        this.q = 0;
        this.r = 0;
        this.f500b.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public int hashCodeRemote() throws RemoteException {
        return super.hashCode();
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public boolean checkInBounds() {
        return this.f500b.getMapConfig().getGeoRectangle().isOverlap(this.f499a);
    }

    private void a() {
        IAMapDelegate iAMapDelegate = this.f500b;
        if (iAMapDelegate != null) {
            this.z = (de.e) iAMapDelegate.getGLShader(3);
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public void draw(MapConfig mapConfig) throws RemoteException {
        List<IPoint> list = this.l;
        if (list == null || list.size() == 0) {
            return;
        }
        Rectangle geoRectangle = mapConfig.getGeoRectangle();
        geoRectangle.getClipRect();
        List<IPoint> list2 = this.l;
        a(geoRectangle);
        b();
        if (list2.size() > 2) {
            b(list2, (int) mapConfig.getSX(), (int) mapConfig.getSY());
            if (this.o != null && this.p != null && this.q > 0 && this.r > 0) {
                de.e eVar = this.z;
                if (eVar == null || eVar.c()) {
                    a();
                }
                dy.a(this.z, this.f506h, this.i, this.o, this.f505g, this.p, this.q, this.r, this.f500b.getFinalMatrix(), this.f500b.getLineTextureID(), this.f500b.getLineTextureRatio(), mapConfig.getMapPerPixelUnitLength(), this.x.getTypeValue(), this.w.getTypeValue(), this.v, true);
            }
        }
        c();
        this.s = true;
    }

    private void b() throws RemoteException {
        MapConfig mapConfig = this.f500b.getMapConfig();
        List<BaseHoleOptions> list = this.m;
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
        for (int i = 0; i < this.m.size(); i++) {
            BaseHoleOptions baseHoleOptions = this.m.get(i);
            boolean z = baseHoleOptions instanceof PolygonHoleOptions;
            if (z) {
                a(b(((PolygonHoleOptions) baseHoleOptions).getPoints()), (int) mapConfig.getSX(), (int) mapConfig.getSY());
            } else if (baseHoleOptions instanceof CircleHoleOptions) {
                this.f500b.changeGLOverlayIndex();
                a((CircleHoleOptions) baseHoleOptions);
            }
            if (this.F != null && this.D > 0) {
                de.e eVar = this.z;
                if (eVar == null || eVar.c()) {
                    a();
                }
                if (z) {
                    dy.a(this.z, -1, ViewCompat.MEASURED_STATE_MASK, this.F, this.f505g, this.G, this.D, this.E, this.f500b.getFinalMatrix(), this.f500b.getLineTextureID(), this.f500b.getLineTextureRatio(), mapConfig.getMapPerPixelUnitLength(), this.x.getTypeValue(), this.w.getTypeValue(), this.v, false);
                } else if (baseHoleOptions instanceof CircleHoleOptions) {
                    dy.a(this.z, Color.argb(200, 80, 1, 1), Color.argb(200, 1, 1, 1), this.F, 5.0f, this.D, this.f500b.getFinalMatrix(), mapConfig.getMapPerPixelUnitLength(), this.f500b.getLineTextureID(), this.f500b.getLineTextureRatio(), this.v, false);
                }
            }
        }
        GLES20.glColorMask(true, true, true, true);
        GLES20.glStencilFunc(517, 1, 255);
        GLES20.glStencilMask(0);
    }

    private void c() throws RemoteException {
        GLES20.glClearStencil(0);
        GLES20.glClear(1024);
        GLES20.glDisable(2960);
        MapConfig mapConfig = this.f500b.getMapConfig();
        List<BaseHoleOptions> list = this.m;
        if (list == null || list.size() <= 0) {
            return;
        }
        for (int i = 0; i < this.m.size(); i++) {
            BaseHoleOptions baseHoleOptions = this.m.get(i);
            boolean z = baseHoleOptions instanceof PolygonHoleOptions;
            if (z) {
                a(b(((PolygonHoleOptions) baseHoleOptions).getPoints()), (int) mapConfig.getSX(), (int) mapConfig.getSY());
            } else if (baseHoleOptions instanceof CircleHoleOptions) {
                this.f500b.changeGLOverlayIndex();
                a((CircleHoleOptions) baseHoleOptions);
            }
            if (this.F != null && this.D > 0) {
                de.e eVar = this.z;
                if (eVar == null || eVar.c()) {
                    a();
                }
                if (z) {
                    dy.a(this.z, 0, this.i, this.F, this.f505g, this.G, this.D, this.E, mapConfig.getMvpMatrix(), this.f500b.getLineTextureID(), this.f500b.getLineTextureRatio(), mapConfig.getMapPerPixelUnitLength(), this.x.getTypeValue(), this.w.getTypeValue(), this.v);
                } else if (baseHoleOptions instanceof CircleHoleOptions) {
                    dy.a(this.z, 0, this.i, this.F, this.f505g, this.D, this.f500b.getFinalMatrix(), mapConfig.getMapPerPixelUnitLength(), this.f500b.getLineTextureID(), this.f500b.getLineTextureRatio(), this.v);
                }
            }
        }
    }

    private float a(double d2) {
        return (float) ((Math.cos((d2 * 3.141592653589793d) / 180.0d) * ((double) A)) / ((double) (B << C)));
    }

    private double b(double d2) {
        return 1.0d / ((double) a(d2));
    }

    public void a(CircleHoleOptions circleHoleOptions) throws RemoteException {
        if (circleHoleOptions.getCenter() != null) {
            IPoint iPointObtain = IPoint.obtain();
            FPoint fPointObtain = FPoint.obtain();
            GLMapState.lonlat2Geo(circleHoleOptions.getCenter().longitude, circleHoleOptions.getCenter().latitude, iPointObtain);
            float[] fArr = new float[1086];
            double dB = b(circleHoleOptions.getCenter().latitude) * circleHoleOptions.getRadius();
            int sx = (int) this.f500b.getMapConfig().getSX();
            int sy = (int) this.f500b.getMapConfig().getSY();
            fPointObtain.x = iPointObtain.x - sx;
            fPointObtain.y = iPointObtain.y - sy;
            int i = 0;
            fArr[0] = fPointObtain.x;
            fArr[1] = fPointObtain.y;
            fArr[2] = 0.0f;
            while (i < 361) {
                double d2 = (((double) i) * 3.141592653589793d) / 180.0d;
                double dSin = Math.sin(d2) * dB;
                double dCos = Math.cos(d2) * dB;
                int i2 = i;
                int i3 = (int) (((double) iPointObtain.x) + dSin);
                int i4 = (int) (((double) iPointObtain.y) + dCos);
                fPointObtain.x = i3 - sx;
                fPointObtain.y = i4 - sy;
                fPointObtain.x = i3 - ((int) this.f500b.getMapConfig().getSX());
                fPointObtain.y = i4 - ((int) this.f500b.getMapConfig().getSY());
                i = i2 + 1;
                int i5 = i * 3;
                fArr[i5] = fPointObtain.x;
                fArr[i5 + 1] = fPointObtain.y;
                fArr[i5 + 2] = 0.0f;
            }
            this.D = fArr.length / 3;
            this.F = er.a(fArr);
            iPointObtain.recycle();
            fPointObtain.recycle();
        }
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
            if (H == 1.0E10d) {
                H = 1.0E8d;
            } else {
                H = 1.0E10d;
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
        this.D = iPointArr.length;
        this.E = iPointArrA.length;
        this.F = er.a(fArr);
        this.G = er.a(fArr2);
    }

    private boolean a(Rectangle rectangle) {
        this.y = this.f500b.getZoomLevel();
        d();
        if (this.y > 10 && rectangle != null) {
            try {
                return !rectangle.contains(this.f499a);
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    private void b(List<IPoint> list, int i, int i2) throws RemoteException {
        int i3;
        d();
        ArrayList<IPoint> arrayList = new ArrayList();
        int size = list.size();
        if (size < 2) {
            return;
        }
        IPoint iPoint = list.get(0);
        arrayList.add(iPoint);
        IPoint iPoint2 = iPoint;
        int i4 = 1;
        while (true) {
            i3 = size - 1;
            if (i4 >= i3) {
                break;
            }
            IPoint iPoint3 = list.get(i4);
            if (a(iPoint2, iPoint3)) {
                arrayList.add(iPoint3);
                iPoint2 = iPoint3;
            }
            i4++;
        }
        arrayList.add(list.get(i3));
        float[] fArr = new float[arrayList.size() * 3];
        IPoint[] iPointArr = new IPoint[arrayList.size()];
        int i5 = 0;
        for (IPoint iPoint4 : arrayList) {
            int i6 = i5 * 3;
            fArr[i6] = iPoint4.x - i;
            fArr[i6 + 1] = iPoint4.y - i2;
            fArr[i6 + 2] = 0.0f;
            iPointArr[i5] = iPoint4;
            i5++;
        }
        IPoint[] iPointArrA = a(iPointArr);
        if (iPointArrA.length == 0) {
            if (H == 1.0E10d) {
                H = 1.0E8d;
            } else {
                H = 1.0E10d;
            }
            iPointArrA = a(iPointArr);
        }
        float[] fArr2 = new float[iPointArrA.length * 3];
        int i7 = 0;
        for (IPoint iPoint5 : iPointArrA) {
            int i8 = i7 * 3;
            fArr2[i8] = iPoint5.x - i;
            fArr2[i8 + 1] = iPoint5.y - i2;
            fArr2[i8 + 2] = 0.0f;
            i7++;
        }
        this.q = iPointArr.length;
        this.r = iPointArrA.length;
        this.o = er.a(fArr);
        this.p = er.a(fArr2);
    }

    private List<IPoint> b(List<LatLng> list) throws RemoteException {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            Object obj = null;
            for (LatLng latLng : list) {
                if (!latLng.equals(obj)) {
                    IPoint iPointObtain = IPoint.obtain();
                    this.f500b.latlon2Geo(latLng.latitude, latLng.longitude, iPointObtain);
                    arrayList.add(iPointObtain);
                    er.b(this.f499a, iPointObtain.x, iPointObtain.y);
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

    private boolean a(IPoint iPoint, IPoint iPoint2) {
        return ((float) (iPoint2.x - iPoint.x)) >= this.t || ((float) (iPoint2.x - iPoint.x)) <= (-this.t) || ((float) (iPoint2.y - iPoint.y)) >= this.t || ((float) (iPoint2.y - iPoint.y)) <= (-this.t);
    }

    private void d() {
        float zoomLevel = this.f500b.getZoomLevel();
        if (this.l.size() <= 5000) {
            this.t = this.f500b.getMapProjection().getMapLenWithWin(2);
        } else if (zoomLevel <= 12) {
            float f2 = (this.f505g / 2.0f) + (zoomLevel / 2.0f);
            this.t = this.f500b.getMapProjection().getMapLenWithWin((int) (f2 <= 200.0f ? f2 : 200.0f));
        } else {
            this.t = this.f500b.getMapProjection().getMapLenWithWin(10);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolygon
    public void setStrokeWidth(float f2) throws RemoteException {
        this.f505g = f2;
        this.f500b.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolygon
    public float getStrokeWidth() throws RemoteException {
        return this.f505g;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolygon
    public void setFillColor(int i) throws RemoteException {
        this.f506h = i;
        this.f500b.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolygon
    public int getFillColor() throws RemoteException {
        return this.f506h;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolygon
    public void setStrokeColor(int i) throws RemoteException {
        this.i = i;
        this.f500b.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolygon
    public int getStrokeColor() throws RemoteException {
        return this.i;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IPolygonDelegate
    public void setHoles(List<LatLng> list) throws RemoteException {
        this.j = list;
        this.f500b.setRunLowFrame(false);
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IPolygonDelegate
    public List<LatLng> getHoles() {
        return this.j;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IPolygonDelegate
    public void setGeodesic(boolean z) {
        this.f503e = z;
        this.f500b.setRunLowFrame(false);
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IPolygonDelegate
    public boolean isGeodesic() {
        return this.f503e;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolygon
    public void setHoleOptions(List<BaseHoleOptions> list) {
        try {
            this.n = list;
            if (this.m == null) {
                this.m = new ArrayList();
            } else {
                this.m.clear();
            }
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    BaseHoleOptions baseHoleOptions = list.get(i);
                    if (baseHoleOptions instanceof PolygonHoleOptions) {
                        PolygonHoleOptions polygonHoleOptions = (PolygonHoleOptions) baseHoleOptions;
                        if (a(polygonHoleOptions) && !er.a(this.m, polygonHoleOptions)) {
                            this.m.add(polygonHoleOptions);
                        }
                    } else if (baseHoleOptions instanceof CircleHoleOptions) {
                        CircleHoleOptions circleHoleOptions = (CircleHoleOptions) baseHoleOptions;
                        if (b(circleHoleOptions) && !er.a(this.m, circleHoleOptions)) {
                            this.m.add(circleHoleOptions);
                        }
                    }
                }
            } else {
                this.m.clear();
            }
        } catch (Throwable th) {
            hn.c(th, "PolygonDelegateImp", "setHoleOptions");
            th.printStackTrace();
        }
        this.f500b.setRunLowFrame(false);
    }

    private boolean b(CircleHoleOptions circleHoleOptions) {
        try {
            if (er.b(getPoints(), circleHoleOptions)) {
                return false;
            }
            return contains(circleHoleOptions.getCenter());
        } catch (Throwable th) {
            hn.c(th, "PolygonDelegateImp", "isCircleInPolygon");
            th.printStackTrace();
            return false;
        }
    }

    private boolean a(PolygonHoleOptions polygonHoleOptions) {
        boolean zA = true;
        try {
            List<LatLng> points = polygonHoleOptions.getPoints();
            for (int i = 0; i < points.size() && (zA = er.a(points.get(i), getPoints())); i++) {
            }
        } catch (Throwable th) {
            hn.c(th, "PolygonDelegateImp", "isPolygonInPolygon");
            th.printStackTrace();
        }
        return zA;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolygon
    public List<BaseHoleOptions> getHoleOptions() {
        return this.m;
    }

    public static IPoint[] a(IPoint[] iPointArr) {
        int length = iPointArr.length;
        double[] dArr = new double[length * 2];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            dArr[i2] = ((double) iPointArr[i].x) * H;
            dArr[i2 + 1] = ((double) iPointArr[i].y) * H;
        }
        en enVarA = new dv().a(dArr);
        int i3 = enVarA.f749b;
        IPoint[] iPointArr2 = new IPoint[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            iPointArr2[i4] = new IPoint();
            iPointArr2[i4].x = (int) (dArr[enVarA.a(i4) * 2] / H);
            iPointArr2[i4].y = (int) (dArr[(enVarA.a(i4) * 2) + 1] / H);
        }
        return iPointArr2;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void destroy() {
        try {
            if (this.o != null) {
                this.o.clear();
                this.o = null;
            }
            if (this.p != null) {
                this.p = null;
            }
            if (this.F != null) {
                this.F.clear();
                this.F = null;
            }
            if (this.G != null) {
                this.G.clear();
                this.G = null;
            }
            if (this.m != null) {
                this.m.clear();
            }
            if (this.n != null) {
                this.n.clear();
            }
            this.m = null;
            this.n = null;
        } catch (Throwable th) {
            hn.c(th, "PolygonDelegateImp", "destroy");
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolygon
    public boolean contains(LatLng latLng) throws RemoteException {
        if (latLng == null) {
            return false;
        }
        try {
            if (this.m != null && this.m.size() > 0) {
                Iterator<BaseHoleOptions> it = this.m.iterator();
                while (it.hasNext()) {
                    if (er.a(it.next(), latLng)) {
                        return false;
                    }
                }
            }
            return er.a(latLng, getPoints());
        } catch (Throwable th) {
            hn.c(th, "PolygonDelegateImp", "contains");
            th.printStackTrace();
            return false;
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public boolean isDrawFinish() {
        return this.s;
    }

    public void a(AMapPara.LineJoinType lineJoinType) {
        this.w = lineJoinType;
    }

    public void a(boolean z) {
        this.v = z;
    }
}