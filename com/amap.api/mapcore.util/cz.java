package com.amap.api.mapcore.util;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.opengl.GLES20;
import android.os.Build;
import android.os.RemoteException;
import android.util.Log;
import androidx.core.view.ViewCompat;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.interfaces.IGlOverlayLayer;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.PolylineOptions;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IOverlay;
import com.autonavi.base.amap.api.mapcore.overlays.IPolylineDelegate;
import com.autonavi.base.amap.mapcore.AMapNativeRenderer;
import com.autonavi.base.amap.mapcore.FPoint;
import com.autonavi.base.amap.mapcore.FPoint3;
import com.autonavi.base.amap.mapcore.FPointBounds;
import com.autonavi.base.amap.mapcore.MapConfig;
import com.autonavi.base.amap.mapcore.Rectangle;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.CharCompanionObject;

/* JADX INFO: compiled from: PolylineDelegateImp.java */
/* JADX INFO: loaded from: classes.dex */
public class cz implements IPolylineDelegate {
    private float K;
    private float L;
    private float M;
    private float N;
    private float[] Q;
    private int[] R;
    private int[] S;
    private PolylineOptions V;
    private de Z;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private IGlOverlayLayer f511e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f512f;
    private FloatBuffer p;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private List<IPoint> f513g = new ArrayList();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private List<FPoint> f514h = new ArrayList();
    private List<LatLng> i = new ArrayList();
    private List<BitmapDescriptor> j = new ArrayList();
    private List<x> k = new ArrayList();
    private List<Integer> l = new ArrayList();
    private List<Integer> m = new ArrayList();
    private List<Integer> n = new ArrayList();
    private List<Integer> o = new ArrayList();
    private BitmapDescriptor q = null;
    private Object r = new Object();
    private boolean s = true;
    private boolean t = true;
    private boolean u = false;
    private boolean v = false;
    private boolean w = false;
    private boolean x = true;
    private boolean y = false;
    private boolean z = false;
    private boolean A = true;
    private int B = 0;
    private int C = 0;
    private float D = 1.0f;
    private int E = ViewCompat.MEASURED_STATE_MASK;
    private int F = 0;
    private int G = 0;
    private float H = 10.0f;
    private float I = 0.0f;
    private float J = 0.0f;
    private float O = 1.0f;
    private float P = 0.0f;
    private boolean T = false;
    private FPointBounds U = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Rect f507a = null;
    private int W = 0;
    private PolylineOptions.LineJoinType X = PolylineOptions.LineJoinType.LineJoinBevel;
    private PolylineOptions.LineCapType Y = PolylineOptions.LineCapType.LineCapRound;
    private boolean aa = false;
    private float ab = -1.0f;
    private float ac = -1.0f;
    private float ad = -1.0f;
    private int ae = -1;
    private List<IPoint> af = new ArrayList();
    private boolean ag = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f508b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ArrayList<FPoint> f509c = new ArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    long f510d = 0;

    public void a(boolean z) {
        this.A = z;
        this.f511e.getMap().setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public void setGeodesic(boolean z) throws RemoteException {
        this.u = z;
        this.f511e.getMap().setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public boolean isGeodesic() {
        return this.u;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public void setDottedLine(boolean z) {
        int i = this.B;
        if (i == 2 || i == 0) {
            this.v = z;
            if (z && this.t) {
                this.B = 2;
            } else if (!z && this.t) {
                this.B = 0;
            }
            this.f511e.getMap().setRunLowFrame(false);
        }
    }

    public void a(int i) {
        this.G = i;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public boolean isDottedLine() {
        return this.v;
    }

    public cz(IGlOverlayLayer iGlOverlayLayer, PolylineOptions polylineOptions) {
        this.f511e = iGlOverlayLayer;
        setOptions(polylineOptions);
        try {
            this.f512f = getId();
        } catch (RemoteException e2) {
            hn.c(e2, "PolylineDelegateImp", "create");
            e2.printStackTrace();
        }
    }

    void a(List<LatLng> list) throws RemoteException {
        boolean z;
        ArrayList arrayList = new ArrayList();
        LatLngBounds.Builder builder = LatLngBounds.builder();
        if (list != null) {
            LatLng latLng = null;
            z = false;
            for (LatLng latLng2 : list) {
                if (!this.u) {
                    IPoint iPointObtain = IPoint.obtain();
                    this.f511e.getMap().latlon2Geo(latLng2.latitude, latLng2.longitude, iPointObtain);
                    arrayList.add(iPointObtain);
                    builder.include(latLng2);
                } else if (latLng != null) {
                    if (Math.abs(latLng2.longitude - latLng.longitude) < 0.01d) {
                        IPoint iPointObtain2 = IPoint.obtain();
                        this.f511e.getMap().latlon2Geo(latLng.latitude, latLng.longitude, iPointObtain2);
                        arrayList.add(iPointObtain2);
                        builder.include(latLng);
                        IPoint iPointObtain3 = IPoint.obtain();
                        this.f511e.getMap().latlon2Geo(latLng2.latitude, latLng2.longitude, iPointObtain3);
                        arrayList.add(iPointObtain3);
                        builder.include(latLng2);
                    } else {
                        a(latLng, latLng2, arrayList, builder);
                    }
                }
                if (latLng2 != null) {
                    if (!z && latLng2.longitude < -180.0d) {
                        this.aa = true;
                        z = true;
                    }
                    if (!this.aa && latLng2.longitude > 180.0d) {
                        this.aa = true;
                    }
                }
                latLng = latLng2;
            }
        } else {
            z = false;
        }
        this.f513g = arrayList;
        this.F = 0;
        if (this.f507a == null) {
            this.f507a = new Rect();
        }
        er.a(this.f507a);
        for (IPoint iPoint : this.f513g) {
            if (z) {
                iPoint.x += 268435456;
            }
            er.b(this.f507a, iPoint.x, iPoint.y);
        }
        this.f507a.sort();
        this.f511e.getMap().setRunLowFrame(false);
    }

    IPoint a(IPoint iPoint, IPoint iPoint2, IPoint iPoint3, double d2, int i) {
        IPoint iPointObtain = IPoint.obtain();
        double d3 = iPoint2.x - iPoint.x;
        double d4 = iPoint2.y - iPoint.y;
        iPointObtain.y = (int) (((((double) i) * d2) / Math.sqrt(((d4 * d4) / (d3 * d3)) + 1.0d)) + ((double) iPoint3.y));
        iPointObtain.x = (int) (((((double) (iPoint3.y - iPointObtain.y)) * d4) / d3) + ((double) iPoint3.x));
        return iPointObtain;
    }

    void a(List<IPoint> list, List<IPoint> list2, double d2) {
        if (list.size() != 3) {
            return;
        }
        int i = 10;
        int i2 = 0;
        int i3 = 0;
        while (i3 <= i) {
            float f2 = i3;
            float f3 = f2 / 10.0f;
            IPoint iPointObtain = IPoint.obtain();
            double d3 = 1.0d - ((double) f3);
            double d4 = d3 * d3;
            double d5 = ((double) (2.0f * f3)) * d3;
            float f4 = f3 * f3;
            double d6 = (((double) list.get(i2).x) * d4) + (((double) list.get(1).x) * d5 * d2) + ((double) (list.get(2).x * f4));
            double d7 = (((double) list.get(i2).y) * d4) + (((double) list.get(1).y) * d5 * d2) + ((double) (list.get(2).y * f4));
            double d8 = d4 + (d5 * d2) + ((double) f4);
            iPointObtain.x = (int) (d6 / d8);
            iPointObtain.y = (int) (d7 / d8);
            list2.add(iPointObtain);
            i3 = (int) (f2 + 1.0f);
            i = 10;
            i2 = 0;
        }
    }

    void a(LatLng latLng, LatLng latLng2, List<IPoint> list, LatLngBounds.Builder builder) {
        double dAbs = (Math.abs(latLng.longitude - latLng2.longitude) * 3.141592653589793d) / 180.0d;
        LatLng latLng3 = new LatLng((latLng2.latitude + latLng.latitude) / 2.0d, (latLng2.longitude + latLng.longitude) / 2.0d, false);
        builder.include(latLng).include(latLng3).include(latLng2);
        int i = latLng3.latitude > 0.0d ? -1 : 1;
        IPoint iPointObtain = IPoint.obtain();
        this.f511e.getMap().latlon2Geo(latLng.latitude, latLng.longitude, iPointObtain);
        IPoint iPointObtain2 = IPoint.obtain();
        this.f511e.getMap().latlon2Geo(latLng2.latitude, latLng2.longitude, iPointObtain2);
        IPoint iPointObtain3 = IPoint.obtain();
        this.f511e.getMap().latlon2Geo(latLng3.latitude, latLng3.longitude, iPointObtain3);
        double d2 = dAbs * 0.5d;
        double dCos = Math.cos(d2);
        IPoint iPointA = a(iPointObtain, iPointObtain2, iPointObtain3, Math.hypot(iPointObtain.x - iPointObtain2.x, iPointObtain.y - iPointObtain2.y) * 0.5d * Math.tan(d2), i);
        ArrayList arrayList = new ArrayList();
        arrayList.add(iPointObtain);
        arrayList.add(iPointA);
        arrayList.add(iPointObtain2);
        a(arrayList, list, dCos);
        iPointObtain.recycle();
        iPointA.recycle();
        iPointObtain2.recycle();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void remove() throws RemoteException {
        this.ag = true;
        this.f511e.removeOverlay(getId());
        setVisible(false);
        this.f511e.getMap().setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public String getId() throws RemoteException {
        if (this.f512f == null) {
            this.f512f = this.f511e.createId("Polyline");
        }
        return this.f512f;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public void setPoints(List<LatLng> list) throws RemoteException {
        try {
            this.i = list;
            synchronized (this.r) {
                a(list);
            }
            this.x = true;
            this.f511e.getMap().setRunLowFrame(false);
            this.V.setPoints(list);
            setShownRatio(this.V.getShownRatio());
            setShowRange(this.V.getShownRangeBegin(), this.V.getShownRangeEnd());
        } catch (Throwable th) {
            hn.c(th, "PolylineDelegateImp", "setPoints");
            this.f513g.clear();
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public List<LatLng> getPoints() throws RemoteException {
        return this.i;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public void setWidth(float f2) throws RemoteException {
        this.H = f2;
        this.f511e.getMap().setRunLowFrame(false);
        this.V.width(f2);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public float getWidth() throws RemoteException {
        return this.H;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public void setColor(int i) {
        int i2 = this.B;
        if (i2 == 0 || i2 == 2) {
            this.E = i;
            this.K = Color.alpha(i) / 255.0f;
            this.L = Color.red(i) / 255.0f;
            this.M = Color.green(i) / 255.0f;
            this.N = Color.blue(i) / 255.0f;
            if (this.t) {
                if (this.v) {
                    this.B = 2;
                } else {
                    this.B = 0;
                }
            }
            this.f511e.getMap().setRunLowFrame(false);
        }
        this.V.color(i);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public int getColor() throws RemoteException {
        return this.E;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setZIndex(float f2) throws RemoteException {
        this.I = f2;
        this.f511e.changeOverlayIndex();
        this.f511e.getMap().setRunLowFrame(false);
        PolylineOptions polylineOptions = this.V;
        if (polylineOptions != null) {
            polylineOptions.zIndex(f2);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public float getZIndex() throws RemoteException {
        return this.I;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setVisible(boolean z) throws RemoteException {
        this.s = z;
        this.f511e.getMap().setRunLowFrame(false);
        PolylineOptions polylineOptions = this.V;
        if (polylineOptions != null) {
            polylineOptions.visible(z);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isVisible() throws RemoteException {
        return this.s;
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
        if (this.aa) {
            return true;
        }
        Rectangle geoRectangle = this.f511e.getMap().getMapConfig().getGeoRectangle();
        Rect rect = this.f507a;
        return rect == null || geoRectangle == null || geoRectangle.isOverlap(rect);
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public boolean calMapFPoint() throws RemoteException {
        if (this.ab != -1.0f || this.ac != -1.0f || this.ad != -1.0f) {
            b(this.af);
            return true;
        }
        b(this.f513g);
        return true;
    }

    public boolean b(List<IPoint> list) {
        synchronized (this.r) {
            FPointBounds.Builder builder = new FPointBounds.Builder();
            this.f514h.clear();
            this.z = false;
            this.Q = new float[list.size() * 3];
            this.f508b = this.Q.length;
            Iterator<IPoint> it = list.iterator();
            int i = 0;
            while (true) {
                boolean z = true;
                if (!it.hasNext()) {
                    break;
                }
                IPoint next = it.next();
                FPoint3 fPoint3 = new FPoint3();
                this.f511e.getMap().geo2Map(next.x, next.y, fPoint3);
                int i2 = i * 3;
                this.Q[i2] = fPoint3.x;
                this.Q[i2 + 1] = fPoint3.y;
                this.Q[i2 + 2] = 0.0f;
                if (this.l != null) {
                    synchronized (this.l) {
                        if (this.l == null || this.l.size() <= i) {
                            z = false;
                        } else if (this.ae <= 0) {
                            fPoint3.setColorIndex(this.l.get(i).intValue());
                        } else if (this.ae + i < this.l.size()) {
                            fPoint3.setColorIndex(this.l.get(this.ae + i).intValue());
                        }
                    }
                    synchronized (this.m) {
                        if (!z) {
                            if (this.m != null && this.m.size() > i) {
                                if (this.ae <= 0) {
                                    fPoint3.setColorIndex(this.m.get(i).intValue());
                                } else if (this.ae + i < this.m.size()) {
                                    fPoint3.setColorIndex(this.m.get(this.ae + i).intValue());
                                }
                            }
                        }
                    }
                }
                this.f514h.add(fPoint3);
                builder.include(fPoint3);
                i++;
            }
            this.U = builder.build();
            if (!this.A) {
                this.p = er.a(this.Q);
            }
            this.F = list.size();
            a();
        }
        return true;
    }

    private void a() {
        float mapPerPixelUnitLength = this.f511e.getMap().getMapConfig().getMapPerPixelUnitLength();
        if (this.F > 5000) {
            float f2 = this.J;
            if (f2 <= 12) {
                float f3 = (this.H / 2.0f) + (f2 / 2.0f);
                if (f3 > 200.0f) {
                    f3 = 200.0f;
                }
                this.P = mapPerPixelUnitLength * f3;
                return;
            }
            this.P = mapPerPixelUnitLength * 10.0f;
            return;
        }
        this.P = mapPerPixelUnitLength * 2.0f;
    }

    private void c(List<FPoint> list) throws RemoteException {
        int i;
        this.f509c.clear();
        int size = list.size();
        if (size < 2) {
            return;
        }
        int i2 = 0;
        FPoint fPoint = list.get(0);
        this.f509c.add(fPoint);
        FPoint fPoint2 = fPoint;
        int i3 = 1;
        while (true) {
            i = size - 1;
            if (i3 >= i) {
                break;
            }
            FPoint fPoint3 = list.get(i3);
            if (i3 == 1 || a(fPoint2, fPoint3)) {
                this.f509c.add(fPoint3);
                fPoint2 = fPoint3;
            } else {
                ArrayList<FPoint> arrayList = this.f509c;
                arrayList.set(arrayList.size() - 1, fPoint3);
            }
            i3++;
        }
        this.f509c.add(list.get(i));
        int size2 = this.f509c.size() * 3;
        this.f508b = size2;
        float[] fArr = this.Q;
        if (fArr == null || fArr.length < this.f508b) {
            this.Q = new float[size2];
        }
        int i4 = this.B;
        if (i4 == 5 || i4 == 3 || i4 == 4) {
            int[] iArr = new int[this.f509c.size()];
            ArrayList arrayList2 = new ArrayList();
            int i5 = 0;
            int i6 = 0;
            for (int i7 = 0; i7 < size2 / 3; i7++) {
                FPoint3 fPoint32 = (FPoint3) this.f509c.get(i7);
                int i8 = i7 * 3;
                this.Q[i8] = fPoint32.x;
                this.Q[i8 + 1] = fPoint32.y;
                this.Q[i8 + 2] = 0.0f;
                int i9 = fPoint32.colorIndex;
                if (i7 == 0) {
                    arrayList2.add(Integer.valueOf(i9));
                } else if (i9 != i5) {
                    if (i9 == -1) {
                        i9 = i5;
                    }
                    arrayList2.add(Integer.valueOf(i9));
                }
                iArr[i6] = i7;
                i6++;
                i5 = i9;
            }
            this.R = new int[arrayList2.size()];
            int[] iArr2 = this.R;
            System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
            this.n = arrayList2;
            this.o = arrayList2;
            return;
        }
        for (FPoint fPoint4 : this.f509c) {
            int i10 = i2 * 3;
            this.Q[i10] = fPoint4.x;
            this.Q[i10 + 1] = fPoint4.y;
            this.Q[i10 + 2] = 0.0f;
            i2++;
        }
    }

    private boolean a(FPoint fPoint, FPoint fPoint2) {
        return ((fPoint instanceof FPoint3) && (fPoint2 instanceof FPoint3) && ((FPoint3) fPoint).colorIndex != ((FPoint3) fPoint2).colorIndex) || Math.abs(fPoint2.x - fPoint.x) >= this.P || Math.abs(fPoint2.y - fPoint.y) >= this.P;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public void setCustomTexture(BitmapDescriptor bitmapDescriptor) {
        long jNanoTime = System.nanoTime();
        if (jNanoTime - this.f510d < 16) {
            return;
        }
        this.f510d = jNanoTime;
        if (bitmapDescriptor == null) {
            return;
        }
        synchronized (this) {
            if (bitmapDescriptor.equals(this.q)) {
                return;
            }
            this.t = false;
            this.w = false;
            this.B = 1;
            this.q = bitmapDescriptor;
            this.f511e.getMap().setRunLowFrame(false);
            if (this.V != null) {
                this.V.setCustomTexture(bitmapDescriptor);
            }
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public void draw(MapConfig mapConfig) throws RemoteException {
        List<IPoint> list;
        char c2;
        if (this.ag) {
            return;
        }
        synchronized (this.r) {
            if (this.ab != -1.0f || this.ac != -1.0f || this.ad != -1.0f) {
                list = this.af;
            } else {
                list = this.f513g;
            }
            if (list != null && list.size() != 0 && this.H > 0.0f) {
                if (this.f511e.getMap() == null) {
                    return;
                }
                int sx = (int) mapConfig.getSX();
                int sy = (int) mapConfig.getSY();
                int beyond180Mode = mapConfig.getGeoRectangle().getBeyond180Mode();
                int size = this.f514h.size();
                int size2 = list.size();
                if (size == size2) {
                    for (int i = 0; i < size2; i++) {
                        IPoint iPoint = list.get(i);
                        FPoint fPoint = this.f514h.get(i);
                        fPoint.x = iPoint.x - sx;
                        fPoint.y = iPoint.y - sy;
                        if (this.aa && (beyond180Mode < 0 || mapConfig.getSX() < 1.34217728E8d)) {
                            fPoint.x -= 2.6843546E8f;
                        }
                    }
                } else {
                    this.f514h.clear();
                    int i2 = 0;
                    for (int i3 = 0; i3 < size2; i3++) {
                        IPoint iPoint2 = list.get(i3);
                        FPoint3 fPoint3 = new FPoint3();
                        if (this.l != null) {
                            synchronized (this.l) {
                                if (this.ae <= 0) {
                                    if (this.l != null && this.l.size() > i2) {
                                        fPoint3.setColorIndex(this.l.get(i2).intValue());
                                    }
                                } else if (this.l != null && this.l.size() > this.ae + i2) {
                                    fPoint3.setColorIndex(this.l.get(this.ae + i2).intValue());
                                }
                            }
                        }
                        fPoint3.x = iPoint2.x - sx;
                        fPoint3.y = iPoint2.y - sy;
                        if (!this.aa || (beyond180Mode >= 0 && mapConfig.getSX() >= 1.34217728E8d)) {
                            c2 = CharCompanionObject.MIN_VALUE;
                        } else {
                            float f2 = fPoint3.x;
                            c2 = CharCompanionObject.MIN_VALUE;
                            fPoint3.x = f2 - 2.6843546E8f;
                        }
                        this.f514h.add(fPoint3);
                        i2++;
                    }
                }
                if (this.x) {
                    calMapFPoint();
                    this.x = false;
                } else {
                    if (this.y) {
                        synchronized (this.r) {
                            int size3 = this.f514h.size();
                            synchronized (this.l) {
                                int size4 = this.l.size();
                                for (int i4 = 0; i4 < size3; i4++) {
                                    if (size4 > i4 && this.ae <= 0) {
                                        ((FPoint3) this.f514h.get(i4)).setColorIndex(this.l.get(i4).intValue());
                                    } else if (size4 > this.ae + i4 && this.ae > 0) {
                                        ((FPoint3) this.f514h.get(i4)).setColorIndex(this.l.get(this.ae + i4).intValue());
                                    }
                                }
                            }
                        }
                    }
                }
                if (this.Q != null && this.F > 0) {
                    a(this.f511e.getMap().getMapConfig());
                }
                this.z = true;
            }
        }
    }

    private void a(MapConfig mapConfig) {
        float mapLenWithWin = this.f511e.getMap().getMapProjection().getMapLenWithWin((int) this.H);
        int i = this.B;
        if (i == 0) {
            f(mapLenWithWin, mapConfig);
            return;
        }
        if (i == 1) {
            if (this.A) {
                d(mapLenWithWin, mapConfig);
                return;
            } else {
                f(mapLenWithWin, mapConfig);
                return;
            }
        }
        if (i == 2) {
            if (this.G == -1) {
                f(mapLenWithWin, mapConfig);
                return;
            } else {
                e(mapLenWithWin, mapConfig);
                return;
            }
        }
        if (i == 3) {
            c(mapLenWithWin, mapConfig);
            return;
        }
        if (i == 4) {
            b(mapLenWithWin, mapConfig);
        } else {
            if (i != 5) {
                return;
            }
            if (this.A) {
                a(mapLenWithWin, mapConfig);
            } else {
                c(mapLenWithWin, mapConfig);
            }
        }
    }

    private void a(float f2, MapConfig mapConfig) {
        List<FPoint> listB;
        int[] iArr;
        if (this.ag) {
            return;
        }
        if (!this.w) {
            try {
                if (this.j != null) {
                    this.S = new int[this.j.size()];
                    boolean z = Build.VERSION.SDK_INT >= 12;
                    b();
                    Iterator<BitmapDescriptor> it = this.j.iterator();
                    int i = 0;
                    int iK = 0;
                    while (it.hasNext()) {
                        x xVarA = a(z, it.next(), false);
                        if (xVarA != null) {
                            iK = xVarA.k();
                            this.D = xVarA.l();
                        }
                        this.S[i] = iK;
                        i++;
                    }
                    this.w = true;
                }
            } catch (Throwable th) {
                hn.c(th, "MarkerDelegateImp", "loadtexture");
                return;
            }
        }
        FPoint[] clipMapRect = mapConfig.getGeoRectangle().getClipMapRect();
        try {
            List<FPoint> list = this.f514h;
            if (a(clipMapRect)) {
                synchronized (this.r) {
                    listB = er.b(clipMapRect, this.f514h, false);
                }
            } else {
                listB = list;
            }
            if (listB.size() >= 2) {
                c(listB);
                synchronized (this.n) {
                    iArr = new int[this.n.size()];
                    for (int i2 = 0; i2 < iArr.length; i2++) {
                        int iIntValue = this.n.get(i2).intValue();
                        if (iIntValue < 0) {
                            iIntValue = 0;
                        }
                        iArr[i2] = this.S[iIntValue];
                    }
                }
                if (true && (this.R != null)) {
                    AMapNativeRenderer.nativeDrawLineByMultiTextureID(this.Q, this.f508b, f2, iArr, this.D, iArr.length, this.R, this.R.length, 1.0f - this.O, this.f511e.getFinalMatrix(), this.Y.getTypeValue(), this.X.getTypeValue());
                }
            }
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    private void b() {
        IGlOverlayLayer iGlOverlayLayer;
        List<x> list = this.k;
        if (list != null) {
            for (x xVar : list) {
                if (xVar != null && (iGlOverlayLayer = this.f511e) != null) {
                    iGlOverlayLayer.addRecycleTextureIds(xVar);
                }
            }
            this.k.clear();
        }
    }

    private void a(x xVar) {
        if (xVar != null) {
            this.k.add(xVar);
            xVar.m();
        }
    }

    private x a(boolean z, BitmapDescriptor bitmapDescriptor, boolean z2) {
        if (z2) {
            b();
        }
        x xVar = null;
        if (z && (xVar = this.f511e.getTextureItem(bitmapDescriptor)) != null && xVar.k() > 0) {
            xVar.k();
            a(xVar);
            return xVar;
        }
        if (xVar == null) {
            xVar = new x(bitmapDescriptor, 0);
        }
        Bitmap bitmap = bitmapDescriptor.getBitmap();
        if (bitmap != null && !bitmap.isRecycled()) {
            int iC = c();
            if (z) {
                xVar.a(iC);
                this.f511e.getMap().addTextureItem(xVar);
            }
            a(xVar);
            er.b(iC, bitmap, true);
        }
        return xVar;
    }

    private void b(float f2, MapConfig mapConfig) {
        List<FPoint> listB;
        synchronized (this.m) {
            int[] iArr = new int[this.m.size()];
            for (int i = 0; i < this.m.size(); i++) {
                iArr[i] = this.m.get(i).intValue();
            }
        }
        FPoint[] clipMapRect = mapConfig.getGeoRectangle().getClipMapRect();
        try {
            List<FPoint> list = this.f514h;
            if (a(clipMapRect)) {
                synchronized (this.r) {
                    listB = er.b(clipMapRect, this.f514h, false);
                }
            } else {
                listB = list;
            }
            if (listB.size() >= 2) {
                c(listB);
                int[] iArr2 = new int[this.o.size()];
                for (int i2 = 0; i2 < iArr2.length; i2++) {
                    iArr2[i2] = this.o.get(i2).intValue();
                }
                if (true && (this.R != null)) {
                    AMapNativeRenderer.nativeDrawGradientColorLine(this.Q, this.f508b, f2, iArr2, iArr2.length, this.R, this.R.length, this.f511e.getMap().getLineTextureID(), this.f511e.getFinalMatrix(), this.Y.getTypeValue(), this.X.getTypeValue());
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void c(float f2, MapConfig mapConfig) {
        List<FPoint> listB;
        synchronized (this.m) {
            int[] iArr = new int[this.m.size()];
            for (int i = 0; i < this.m.size(); i++) {
                iArr[i] = this.m.get(i).intValue();
            }
        }
        FPoint[] clipMapRect = mapConfig.getGeoRectangle().getClipMapRect();
        try {
            List<FPoint> list = this.f514h;
            if (a(clipMapRect)) {
                synchronized (this.r) {
                    listB = er.b(clipMapRect, this.f514h, false);
                }
            } else {
                listB = list;
            }
            if (listB.size() >= 2) {
                c(listB);
                int[] iArr2 = new int[this.o.size()];
                for (int i2 = 0; i2 < iArr2.length; i2++) {
                    iArr2[i2] = this.o.get(i2).intValue();
                }
                if (true && (this.R != null)) {
                    AMapNativeRenderer.nativeDrawLineByMultiColor(this.Q, this.f508b, f2, this.f511e.getMap().getLineTextureID(), iArr2, iArr2.length, this.R, this.R.length, this.f511e.getFinalMatrix(), this.Y.getTypeValue(), this.X.getTypeValue());
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void d(float f2, MapConfig mapConfig) {
        List<FPoint> listA;
        int iK;
        if (!this.w) {
            synchronized (this) {
                try {
                    if (this.q != null) {
                        x xVarA = a(Build.VERSION.SDK_INT >= 12, this.q, true);
                        if (xVarA != null) {
                            iK = xVarA.k();
                            this.D = xVarA.l();
                        } else {
                            iK = 0;
                        }
                        this.C = iK;
                        this.w = true;
                    }
                } catch (Throwable th) {
                    hn.c(th, "MarkerDelegateImp", "loadtexture");
                    return;
                }
            }
        }
        try {
            if (mapConfig.getChangeRatio() == 1.0d && this.Q != null) {
                this.W++;
                if (this.W > 2) {
                    AMapNativeRenderer.nativeDrawLineByTextureID(this.Q, this.f508b, f2, this.C, this.D, this.L, this.M, this.N, this.K, 1.0f - this.O, false, false, false, this.f511e.getFinalMatrix(), this.Y.getTypeValue(), this.X.getTypeValue());
                    return;
                }
            }
            this.W = 0;
            FPoint[] clipMapRect = mapConfig.getGeoRectangle().getClipMapRect();
            List<FPoint> list = this.f514h;
            if (a(clipMapRect)) {
                synchronized (this.r) {
                    listA = er.a(clipMapRect, this.f514h, false);
                }
            } else {
                listA = list;
            }
            if (listA.size() >= 2) {
                c(listA);
                AMapNativeRenderer.nativeDrawLineByTextureID(this.Q, this.f508b, f2, this.C, this.D, this.L, this.M, this.N, this.K, 1.0f - this.O, false, false, false, this.f511e.getFinalMatrix(), this.Y.getTypeValue(), this.X.getTypeValue());
            }
        } catch (Throwable unused) {
        }
    }

    private void e(float f2, MapConfig mapConfig) {
        int iK;
        if (!this.w) {
            synchronized (this) {
                try {
                    if (this.q != null) {
                        x xVarA = a(Build.VERSION.SDK_INT >= 12, this.q, true);
                        if (xVarA != null) {
                            iK = xVarA.k();
                            this.D = xVarA.l();
                        } else {
                            iK = 0;
                        }
                        this.C = iK;
                        this.w = true;
                    }
                } catch (Throwable th) {
                    hn.c(th, "MarkerDelegateImp", "loadtexture");
                    return;
                }
            }
        }
        try {
            List<FPoint> listA = this.f514h;
            if (this.f511e.getMap() == null) {
                return;
            }
            if (mapConfig.getChangeRatio() == 1.0d && this.Q != null) {
                this.W++;
                if (this.W > 2) {
                    AMapNativeRenderer.nativeDrawLineByTextureID(this.Q, this.f508b, f2, this.f511e.getMap().getDottedLineTextureID(this.G), this.f511e.getMap().getLineTextureRatio(), this.L, this.M, this.N, this.K, 0.0f, true, true, false, this.f511e.getFinalMatrix(), this.Y.getTypeValue(), this.X.getTypeValue());
                    return;
                }
            }
            this.W = 0;
            FPoint[] clipMapRect = mapConfig.getGeoRectangle().getClipMapRect();
            if (a(clipMapRect)) {
                synchronized (this.r) {
                    listA = er.a(clipMapRect, this.f514h, false);
                }
            }
            if (listA.size() >= 2) {
                c(listA);
                AMapNativeRenderer.nativeDrawLineByTextureID(this.Q, this.f508b, f2, this.f511e.getMap().getDottedLineTextureID(this.G), this.f511e.getMap().getLineTextureRatio(), this.L, this.M, this.N, this.K, 0.0f, true, true, false, this.f511e.getFinalMatrix(), this.Y.getTypeValue(), this.X.getTypeValue());
            }
        } catch (Throwable unused) {
        }
    }

    private void f(float f2, MapConfig mapConfig) {
        try {
            List<FPoint> listA = this.f514h;
            if (this.f511e.getMap() == null) {
                return;
            }
            if (mapConfig.getChangeRatio() == 1.0d && this.Q != null) {
                this.W++;
                if (this.W > 2) {
                    AMapNativeRenderer.nativeDrawLineByTextureID(this.Q, this.f508b, f2, this.f511e.getMap().getLineTextureID(), this.f511e.getMap().getLineTextureRatio(), this.L, this.M, this.N, this.K, 0.0f, false, true, false, this.f511e.getFinalMatrix(), this.Y.getTypeValue(), this.X.getTypeValue());
                    return;
                }
            }
            this.W = 0;
            FPoint[] clipMapRect = mapConfig.getGeoRectangle().getClipMapRect();
            if (a(clipMapRect)) {
                synchronized (this.r) {
                    listA = er.a(clipMapRect, this.f514h, false);
                }
            }
            if (listA.size() >= 2) {
                c(listA);
                AMapNativeRenderer.nativeDrawLineByTextureID(this.Q, this.f508b, f2, this.f511e.getMap().getLineTextureID(), this.f511e.getMap().getLineTextureRatio(), this.L, this.M, this.N, this.K, 0.0f, false, true, false, this.f511e.getFinalMatrix(), this.Y.getTypeValue(), this.X.getTypeValue());
            }
        } catch (Throwable unused) {
        }
    }

    private int c() {
        int[] iArr = {0};
        GLES20.glGenTextures(1, iArr, 0);
        return iArr[0];
    }

    private boolean a(FPoint[] fPointArr) {
        this.J = this.f511e.getMap().getZoomLevel();
        a();
        if (this.J <= (this.f513g.size() > 10000 ? 7 : 3)) {
            return false;
        }
        try {
            if (this.f511e.getMap() != null) {
                if (er.a(this.U.northeast, fPointArr)) {
                    return !er.a(this.U.southwest, fPointArr);
                }
                return true;
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void destroy() {
        try {
            remove();
            if (this.k != null && this.k.size() > 0) {
                for (int i = 0; i < this.k.size(); i++) {
                    x xVar = this.k.get(i);
                    if (xVar != null) {
                        this.f511e.addRecycleTextureIds(xVar);
                        this.f511e.getMap().removeTextureItem(xVar.p());
                    }
                }
                this.k.clear();
            }
            if (this.Q != null) {
                this.Q = null;
            }
            if (this.p != null) {
                this.p.clear();
                this.p = null;
            }
            if (this.j != null && this.j.size() > 0) {
                Iterator<BitmapDescriptor> it = this.j.iterator();
                while (it.hasNext()) {
                    it.next().recycle();
                }
            }
            synchronized (this) {
                if (this.q != null) {
                    this.q.recycle();
                }
            }
            synchronized (this.m) {
                if (this.m != null) {
                    this.m.clear();
                }
            }
            if (this.l != null) {
                synchronized (this.l) {
                    this.l.clear();
                    this.l = null;
                }
            }
            if (this.i != null) {
                this.i.clear();
                this.i = null;
            }
            this.V = null;
        } catch (Throwable th) {
            hn.c(th, "PolylineDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "PolylineDelegateImp destroy");
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public boolean isDrawFinish() {
        return this.z;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public LatLng getNearestLatLng(LatLng latLng) {
        List<LatLng> list;
        if (latLng != null && (list = this.i) != null && list.size() != 0) {
            float fCalculateLineDistance = 0.0f;
            int i = 0;
            for (int i2 = 0; i2 < this.i.size(); i2++) {
                try {
                    if (i2 == 0) {
                        fCalculateLineDistance = AMapUtils.calculateLineDistance(latLng, this.i.get(i2));
                    } else {
                        float fCalculateLineDistance2 = AMapUtils.calculateLineDistance(latLng, this.i.get(i2));
                        if (fCalculateLineDistance > fCalculateLineDistance2) {
                            i = i2;
                            fCalculateLineDistance = fCalculateLineDistance2;
                        }
                    }
                } catch (Throwable th) {
                    hn.c(th, "PolylineDelegateImp", "getNearestLatLng");
                    th.printStackTrace();
                }
            }
            return this.i.get(i);
        }
        return null;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IPolylineDelegate
    public boolean contains(LatLng latLng) {
        float[] fArr = this.Q;
        float[] fArr2 = new float[fArr.length];
        System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
        if (fArr2.length / 3 < 2) {
            return false;
        }
        try {
            ArrayList<FPoint> arrayListD = d();
            if (arrayListD != null) {
                if (arrayListD.size() >= 1) {
                    double mapLenWithWin = this.f511e.getMap().getMapProjection().getMapLenWithWin(((int) this.H) / 4);
                    double mapLenWithWin2 = this.f511e.getMap().getMapProjection().getMapLenWithWin(5);
                    FPoint fPointA = a(latLng);
                    FPoint fPoint = null;
                    int i = 0;
                    while (i < arrayListD.size() - 1) {
                        if (i == 0) {
                            fPoint = arrayListD.get(i);
                        }
                        i++;
                        FPoint fPoint2 = arrayListD.get(i);
                        if ((mapLenWithWin2 + mapLenWithWin) - a(fPointA, fPoint, fPoint2) >= 0.0d) {
                            arrayListD.clear();
                            return true;
                        }
                        fPoint = fPoint2;
                    }
                    arrayListD.clear();
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    private ArrayList<FPoint> d() {
        ArrayList<FPoint> arrayList = new ArrayList<>();
        int i = 0;
        while (true) {
            float[] fArr = this.Q;
            if (i >= fArr.length) {
                return arrayList;
            }
            float f2 = fArr[i];
            int i2 = i + 1;
            arrayList.add(FPoint.obtain(f2, fArr[i2]));
            i = i2 + 1 + 1;
        }
    }

    private double a(FPoint fPoint, FPoint fPoint2, FPoint fPoint3) {
        return a(fPoint.x, fPoint.y, fPoint2.x, fPoint2.y, fPoint3.x, fPoint3.y);
    }

    private FPoint a(LatLng latLng) {
        IPoint iPointObtain = IPoint.obtain();
        this.f511e.getMap().latlon2Geo(latLng.latitude, latLng.longitude, iPointObtain);
        FPoint fPointObtain = FPoint.obtain();
        this.f511e.getMap().geo2Map(iPointObtain.x, iPointObtain.y, fPointObtain);
        iPointObtain.recycle();
        return fPointObtain;
    }

    private double a(double d2, double d3, double d4, double d5, double d6, double d7) {
        double d8 = d6 - d4;
        double d9 = d2 - d4;
        double d10 = d7 - d5;
        double d11 = d3 - d5;
        double d12 = (d8 * d9) + (d10 * d11);
        if (d12 <= 0.0d) {
            return Math.sqrt((d9 * d9) + (d11 * d11));
        }
        double d13 = (d8 * d8) + (d10 * d10);
        if (d12 >= d13) {
            double d14 = d2 - d6;
            double d15 = d3 - d7;
            return Math.sqrt((d14 * d14) + (d15 * d15));
        }
        double d16 = d12 / d13;
        double d17 = d2 - (d4 + (d8 * d16));
        double d18 = (d5 + (d10 * d16)) - d3;
        return Math.sqrt((d17 * d17) + (d18 * d18));
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public void setTransparency(float f2) {
        this.O = (float) Math.min(1.0d, Math.max(0.0d, f2));
        this.f511e.getMap().setRunLowFrame(false);
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IPolylineDelegate, com.autonavi.amap.mapcore.interfaces.IPolyline
    public void setCustomTextureList(List<BitmapDescriptor> list) {
        d(list);
        setCustemTextureIndex(this.V.getCustomTextureIndex());
        reLoadTexture();
    }

    private void d(List<BitmapDescriptor> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        if (list.size() > 1) {
            this.t = false;
            this.B = 5;
            this.j = list;
            this.f511e.getMap().setRunLowFrame(false);
            return;
        }
        setCustomTexture(list.get(0));
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public void setCustemTextureIndex(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        try {
            synchronized (this.l) {
                this.l.clear();
                this.l.addAll(list);
                this.n = e(list);
                this.y = true;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IPolylineDelegate
    public void setColorValues(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        try {
            synchronized (this.m) {
                this.m.clear();
                this.m.addAll(list);
            }
        } catch (Throwable unused) {
        }
        if (list.size() > 1) {
            this.t = false;
            this.o = e(list);
            this.B = 3;
            this.f511e.getMap().setRunLowFrame(false);
            return;
        }
        setColor(list.get(0).intValue());
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IPolylineDelegate
    public void useGradient(boolean z) {
        List<Integer> list;
        if (!z || (list = this.m) == null || list.size() <= 1) {
            return;
        }
        this.B = 4;
        this.f511e.getMap().setRunLowFrame(false);
    }

    private List<Integer> e(List<Integer> list) {
        int[] iArr = new int[list.size()];
        ArrayList arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            int iIntValue = list.get(i3).intValue();
            if (i3 == 0 || iIntValue != i) {
                arrayList.add(Integer.valueOf(iIntValue));
                iArr[i2] = i3;
                i2++;
                i = iIntValue;
            }
        }
        this.R = new int[arrayList.size()];
        int[] iArr2 = this.R;
        System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
        return arrayList;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IPolylineDelegate
    public void reLoadTexture() {
        this.w = false;
        this.C = 0;
        this.D = 1.0f;
        int[] iArr = this.S;
        if (iArr != null) {
            Arrays.fill(iArr, 0);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setAboveMaskLayer(boolean z) {
        this.T = z;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isAboveMaskLayer() {
        return this.T;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public void setOptions(PolylineOptions polylineOptions) {
        if (polylineOptions == null) {
            return;
        }
        this.V = polylineOptions;
        try {
            setColor(polylineOptions.getColor());
            setGeodesic(polylineOptions.isGeodesic());
            setDottedLine(polylineOptions.isDottedLine());
            a(polylineOptions.getDottedLineType());
            setAboveMaskLayer(polylineOptions.isAboveMaskLayer());
            setVisible(polylineOptions.isVisible());
            setWidth(polylineOptions.getWidth());
            setZIndex(polylineOptions.getZIndex());
            a(polylineOptions.isUseTexture());
            setTransparency(polylineOptions.getTransparency());
            a(polylineOptions.getLineCapType());
            a(polylineOptions.getLineJoinType());
            if (polylineOptions.getColorValues() != null) {
                setColorValues(polylineOptions.getColorValues());
                useGradient(polylineOptions.isUseGradient());
            }
            if (polylineOptions.getCustomTexture() != null) {
                setCustomTexture(polylineOptions.getCustomTexture());
                reLoadTexture();
            }
            if (polylineOptions.getCustomTextureList() != null) {
                d(polylineOptions.getCustomTextureList());
                setCustemTextureIndex(polylineOptions.getCustomTextureIndex());
                reLoadTexture();
            }
            setPoints(polylineOptions.getPoints());
            setShownRatio(polylineOptions.getShownRatio());
            setShowRange(polylineOptions.getShownRangeBegin(), polylineOptions.getShownRangeEnd());
        } catch (RemoteException e2) {
            hn.c(e2, "PolylineDelegateImp", "setOptions");
            e2.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public PolylineOptions getOptions() {
        return this.V;
    }

    public void a(PolylineOptions.LineJoinType lineJoinType) {
        this.X = lineJoinType;
    }

    public void a(PolylineOptions.LineCapType lineCapType) {
        this.Y = lineCapType;
    }

    public void a(de deVar) {
        this.Z = deVar;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public void setShownRatio(float f2) {
        this.ab = f2;
        synchronized (this.r) {
            int size = this.f513g.size();
            if (size < 2) {
                this.af.clear();
                return;
            }
            float f3 = this.ab;
            if (f3 < 0.0f) {
                f3 = 0.0f;
            } else if (f3 >= size) {
                f3 = size - 1;
            }
            if (this.u) {
                if (this.i.size() < 2) {
                    return;
                } else {
                    f3 = (f3 / (r5 - 1)) * (size - 1);
                }
            }
            this.af.clear();
            int iFloor = (int) Math.floor(f3);
            IPoint iPoint = null;
            int i = 0;
            while (true) {
                if (i >= size) {
                    break;
                }
                IPoint iPoint2 = this.f513g.get(i);
                if (i > iFloor) {
                    float f4 = f3 - iFloor;
                    if (f2 != 0.0f && iPoint != null) {
                        IPoint iPoint3 = new IPoint();
                        iPoint3.x = (int) (iPoint.x + ((iPoint2.x - iPoint.x) * f4));
                        iPoint3.y = (int) (iPoint.y + ((iPoint2.y - iPoint.y) * f4));
                        this.af.add(iPoint3);
                    }
                } else {
                    this.af.add(iPoint2);
                    i++;
                    iPoint = iPoint2;
                }
            }
            this.x = true;
            this.f511e.getMap().setRunLowFrame(false);
            this.V.setShownRatio(f2);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public float getShownRatio() {
        return this.ab;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public void setShowRange(float f2, float f3) {
        int i;
        double d2;
        float f4 = f2;
        float f5 = f3;
        this.ac = f4;
        this.ad = f5;
        synchronized (this.r) {
            int size = this.f513g.size();
            if (size < 2) {
                this.af.clear();
                return;
            }
            if (f4 > f5) {
                this.af.clear();
                Log.d("mapcore", "setShownRange return begin < end");
                return;
            }
            if (f4 < 0.0f || Float.isNaN(f2)) {
                f4 = 0.0f;
            } else if (f4 >= size) {
                f4 = size - 1;
            }
            if (f5 < 0.0f) {
                f5 = 0.0f;
            } else if (f5 >= size || Float.isNaN(f3)) {
                f5 = size - 1;
            }
            if (this.u) {
                int size2 = this.i.size();
                if (size2 < 2) {
                    Log.d("mapcore", "setShownRatio return m_polylineOptions.m_latLngPoints.size() < MIN_POLYLINE_COUNT");
                    return;
                }
                float f6 = size2 - 1;
                float f7 = size - 1;
                f4 = (f4 / f6) * f7;
                f5 = (f5 / f6) * f7;
            }
            this.af.clear();
            int iFloor = (int) Math.floor(f4);
            int iFloor2 = (int) Math.floor(f5);
            double d3 = f4 - iFloor;
            double d4 = f5 - iFloor2;
            IPoint iPoint = null;
            IPoint iPoint2 = null;
            int i2 = iFloor;
            while (true) {
                if (i2 >= size) {
                    break;
                }
                IPoint iPoint3 = this.f513g.get(i2);
                if (iFloor >= i2) {
                    i = size;
                    d2 = d4;
                    iPoint = iPoint3;
                    iPoint2 = iPoint;
                } else if (iFloor >= i2 || iFloor != i2 - 1) {
                    i = size;
                    d2 = d4;
                    if (iFloor < i2 && iFloor2 >= i2) {
                        this.af.add(iPoint3);
                        iPoint2 = iPoint3;
                    } else if (iFloor2 < i2) {
                        IPoint iPoint4 = new IPoint();
                        iPoint4.x = (int) (((double) iPoint2.x) + (((double) (iPoint3.x - iPoint2.x)) * d2));
                        iPoint4.y = (int) (((double) iPoint2.y) + (((double) (iPoint3.y - iPoint2.y)) * d2));
                        this.af.add(iPoint4);
                        break;
                    }
                } else {
                    IPoint iPoint5 = new IPoint();
                    i = size;
                    d2 = d4;
                    iPoint5.x = (int) (((double) iPoint.x) + (((double) (iPoint3.x - iPoint.x)) * d3));
                    iPoint5.y = (int) (((double) iPoint.y) + (((double) (iPoint3.y - iPoint.y)) * d3));
                    this.af.add(iPoint5);
                    if (iFloor2 == iFloor) {
                        IPoint iPoint6 = new IPoint();
                        iPoint6.x = (int) (((double) iPoint.x) + (((double) (iPoint3.x - iPoint.x)) * d2));
                        iPoint6.y = (int) (((double) iPoint.y) + (((double) (iPoint3.y - iPoint.y)) * d2));
                        this.af.add(iPoint6);
                        break;
                    }
                    this.af.add(iPoint3);
                    iPoint2 = iPoint3;
                }
                i2++;
                size = i;
                d4 = d2;
            }
            if (iFloor >= 0) {
                this.ae = iFloor;
            }
            this.x = true;
            this.f511e.getMap().setRunLowFrame(false);
            this.V.setShownRange(this.ac, this.ad);
        }
    }
}