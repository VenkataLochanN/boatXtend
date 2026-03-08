package com.amap.api.mapcore.util;

import android.content.Context;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import com.amap.api.maps.interfaces.IGlOverlayLayer;
import com.amap.api.maps.model.Arc;
import com.amap.api.maps.model.ArcOptions;
import com.amap.api.maps.model.BaseOptions;
import com.amap.api.maps.model.BaseOverlay;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BuildingOverlay;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.GroundOverlay;
import com.amap.api.maps.model.GroundOverlayOptions;
import com.amap.api.maps.model.HeatMapLayer;
import com.amap.api.maps.model.HeatMapLayerOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.NavigateArrow;
import com.amap.api.maps.model.NavigateArrowOptions;
import com.amap.api.maps.model.Polygon;
import com.amap.api.maps.model.PolygonOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.model.particle.ParticleOverlay;
import com.amap.api.maps.model.particle.ParticleOverlayOptions;
import com.autonavi.amap.api.mapcore.overlays.IBuildingDelegate;
import com.autonavi.amap.api.mapcore.overlays.IParticleLatyer;
import com.autonavi.amap.mapcore.interfaces.IHeatMapLayer;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.api.mapcore.overlays.IArcDelegate;
import com.autonavi.base.amap.api.mapcore.overlays.ICircleDelegate;
import com.autonavi.base.amap.api.mapcore.overlays.IGroundOverlayDelegate;
import com.autonavi.base.amap.api.mapcore.overlays.INavigateArrowDelegate;
import com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate;
import com.autonavi.base.amap.api.mapcore.overlays.IPolygonDelegate;
import com.autonavi.base.amap.api.mapcore.overlays.IPolylineDelegate;
import com.autonavi.base.amap.mapcore.MapConfig;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/* JADX INFO: compiled from: GlOverlayLayerOld.java */
/* JADX INFO: loaded from: classes.dex */
public class s implements IGlOverlayLayer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    IAMapDelegate f1775a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private de f1777c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f1778d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private List<IOverlayDelegate> f1779e = new Vector(500);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private List<x> f1780f = new ArrayList();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int[] f1781g = new int[1];

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private volatile boolean f1782h = false;
    private Handler i = new Handler(Looper.getMainLooper());
    private Runnable j = new Runnable() { // from class: com.amap.api.mapcore.util.s.1
        @Override // java.lang.Runnable
        public synchronized void run() {
            try {
                synchronized (s.this) {
                    if (s.this.f1779e != null && s.this.f1779e.size() > 0) {
                        Collections.sort(s.this.f1779e, s.this.f1776b);
                    }
                }
            } catch (Throwable th) {
                hn.c(th, "MapOverlayImageView", "changeOverlayIndex");
            }
        }
    };

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    a f1776b = new a();

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public boolean IsCircleContainPoint(CircleOptions circleOptions, LatLng latLng) {
        return false;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public boolean IsPolygonContainsPoint(PolygonOptions polygonOptions, LatLng latLng) {
        return false;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public int getCurrentParticleNum(String str) {
        return 0;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public Object getNativeProperties(String str, String str2, Object[] objArr) {
        return null;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public LatLng getNearestLatLng(PolylineOptions polylineOptions, LatLng latLng) {
        return null;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public void loadBitmapDescription(Context context) {
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public void onCreateAMapInstance() {
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public void prepareIcon(String str, Object obj) {
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public void processCircleHoleOption(CircleOptions circleOptions) {
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public void processPolygonHoleOption(PolygonOptions polygonOptions) {
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public void updateOption(String str, BaseOptions baseOptions) {
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public synchronized String createId(String str) {
        this.f1778d++;
        return str + this.f1778d;
    }

    public synchronized IBuildingDelegate a() throws RemoteException {
        cp cpVar;
        cpVar = new cp(this);
        cpVar.a(this.f1777c);
        a(cpVar);
        return cpVar;
    }

    public synchronized IHeatMapLayer a(HeatMapLayerOptions heatMapLayerOptions) throws RemoteException {
        ct ctVar;
        ctVar = new ct(this);
        ctVar.a(this.f1777c);
        ctVar.setOptions(heatMapLayerOptions);
        a(ctVar);
        return ctVar;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public de getGLShaderManager() {
        return this.f1777c;
    }

    /* JADX INFO: compiled from: GlOverlayLayerOld.java */
    static class a implements Serializable, Comparator<Object> {
        a() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            IOverlayDelegate iOverlayDelegate = (IOverlayDelegate) obj;
            IOverlayDelegate iOverlayDelegate2 = (IOverlayDelegate) obj2;
            if (iOverlayDelegate == null || iOverlayDelegate2 == null) {
                return 0;
            }
            try {
                if (iOverlayDelegate.getZIndex() > iOverlayDelegate2.getZIndex()) {
                    return 1;
                }
                return iOverlayDelegate.getZIndex() < iOverlayDelegate2.getZIndex() ? -1 : 0;
            } catch (Throwable th) {
                hn.c(th, "GlOverlayLayer", "compare");
                th.printStackTrace();
                return 0;
            }
        }
    }

    public s(IAMapDelegate iAMapDelegate) {
        this.f1775a = iAMapDelegate;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public void setGlShaderManager(de deVar) {
        this.f1777c = deVar;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public synchronized void clear(String str) {
        try {
            d();
            if (str == null || str.trim().length() == 0) {
                this.f1779e.clear();
                b();
            } else {
                IOverlayDelegate iOverlayDelegate = null;
                Iterator<IOverlayDelegate> it = this.f1779e.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    IOverlayDelegate next = it.next();
                    if (str.equals(next.getId())) {
                        iOverlayDelegate = next;
                        break;
                    }
                }
                this.f1779e.clear();
                if (iOverlayDelegate != null) {
                    this.f1779e.add(iOverlayDelegate);
                }
            }
        } catch (Throwable th) {
            hn.c(th, "GlOverlayLayer", "clear");
            th.printStackTrace();
        }
    }

    private void d() {
        for (IOverlayDelegate iOverlayDelegate : this.f1779e) {
            if (iOverlayDelegate != null && ((iOverlayDelegate instanceof cp) || (iOverlayDelegate instanceof ct))) {
                iOverlayDelegate.destroy();
            }
        }
    }

    public synchronized void b() {
        this.f1778d = 0;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public synchronized void destroy() {
        try {
            Iterator<IOverlayDelegate> it = this.f1779e.iterator();
            while (it.hasNext()) {
                it.next().destroy();
            }
            clear(null);
        } finally {
        }
    }

    synchronized IOverlayDelegate a(String str) throws RemoteException {
        for (IOverlayDelegate iOverlayDelegate : this.f1779e) {
            if (iOverlayDelegate != null && iOverlayDelegate.getId().equals(str)) {
                return iOverlayDelegate;
            }
        }
        return null;
    }

    public synchronized IPolylineDelegate a(PolylineOptions polylineOptions) throws RemoteException {
        if (polylineOptions == null) {
            return null;
        }
        cz czVar = new cz(this, polylineOptions);
        if (this.f1777c != null) {
            czVar.a(this.f1777c);
        }
        a(czVar);
        return czVar;
    }

    public synchronized INavigateArrowDelegate a(NavigateArrowOptions navigateArrowOptions) throws RemoteException {
        if (navigateArrowOptions == null) {
            return null;
        }
        cw cwVar = new cw(this.f1775a);
        cwVar.setTopColor(navigateArrowOptions.getTopColor());
        cwVar.setSideColor(navigateArrowOptions.getSideColor());
        cwVar.setPoints(navigateArrowOptions.getPoints());
        cwVar.setVisible(navigateArrowOptions.isVisible());
        cwVar.setWidth(navigateArrowOptions.getWidth());
        cwVar.setZIndex(navigateArrowOptions.getZIndex());
        cwVar.set3DModel(navigateArrowOptions.is3DModel());
        a(cwVar);
        return cwVar;
    }

    public synchronized IPolygonDelegate a(PolygonOptions polygonOptions) throws RemoteException {
        if (polygonOptions == null) {
            return null;
        }
        cy cyVar = new cy(this.f1775a);
        cyVar.setFillColor(polygonOptions.getFillColor());
        cyVar.setPoints(polygonOptions.getPoints());
        cyVar.setHoleOptions(polygonOptions.getHoleOptions());
        cyVar.setVisible(polygonOptions.isVisible());
        cyVar.setStrokeWidth(polygonOptions.getStrokeWidth());
        cyVar.setStrokeColor(polygonOptions.getStrokeColor());
        cyVar.setZIndex(polygonOptions.getZIndex());
        cyVar.a(polygonOptions.getLineJoinType());
        cyVar.a(polygonOptions.isUsePolylineStroke());
        a(cyVar);
        return cyVar;
    }

    public synchronized ICircleDelegate a(CircleOptions circleOptions) throws RemoteException {
        if (circleOptions == null) {
            return null;
        }
        cq cqVar = new cq(this.f1775a);
        cqVar.setFillColor(circleOptions.getFillColor());
        cqVar.setCenter(circleOptions.getCenter());
        cqVar.setVisible(circleOptions.isVisible());
        cqVar.setHoleOptions(circleOptions.getHoleOptions());
        cqVar.setStrokeWidth(circleOptions.getStrokeWidth());
        cqVar.setZIndex(circleOptions.getZIndex());
        cqVar.setStrokeColor(circleOptions.getStrokeColor());
        cqVar.setRadius(circleOptions.getRadius());
        cqVar.setDottedLineType(circleOptions.getStrokeDottedLineType());
        cqVar.a(circleOptions.isUsePolylineStroke());
        a(cqVar);
        return cqVar;
    }

    public synchronized IArcDelegate a(ArcOptions arcOptions) throws RemoteException {
        if (arcOptions == null) {
            return null;
        }
        co coVar = new co(this.f1775a);
        coVar.setStrokeColor(arcOptions.getStrokeColor());
        coVar.setStart(arcOptions.getStart());
        coVar.setPassed(arcOptions.getPassed());
        coVar.setEnd(arcOptions.getEnd());
        coVar.setVisible(arcOptions.isVisible());
        coVar.setStrokeWidth(arcOptions.getStrokeWidth());
        coVar.setZIndex(arcOptions.getZIndex());
        a(coVar);
        return coVar;
    }

    public synchronized IGroundOverlayDelegate a(GroundOverlayOptions groundOverlayOptions) throws RemoteException {
        if (groundOverlayOptions == null) {
            return null;
        }
        cs csVar = new cs(this.f1775a, this);
        csVar.setAnchor(groundOverlayOptions.getAnchorU(), groundOverlayOptions.getAnchorV());
        csVar.setDimensions(groundOverlayOptions.getWidth(), groundOverlayOptions.getHeight());
        csVar.setImage(groundOverlayOptions.getImage());
        csVar.setPosition(groundOverlayOptions.getLocation());
        csVar.setPositionFromBounds(groundOverlayOptions.getBounds());
        csVar.setBearing(groundOverlayOptions.getBearing());
        csVar.setTransparency(groundOverlayOptions.getTransparency());
        csVar.setVisible(groundOverlayOptions.isVisible());
        csVar.setZIndex(groundOverlayOptions.getZIndex());
        a(csVar);
        return csVar;
    }

    public synchronized IParticleLatyer a(ParticleOverlayOptions particleOverlayOptions) throws RemoteException {
        if (particleOverlayOptions == null) {
            return null;
        }
        cx cxVar = new cx(this);
        cxVar.a(particleOverlayOptions);
        a(cxVar);
        return cxVar;
    }

    private void a(IOverlayDelegate iOverlayDelegate) throws RemoteException {
        this.f1779e.add(iOverlayDelegate);
        changeOverlayIndex();
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public boolean removeOverlay(String str) throws RemoteException {
        return removeOverlay(str, false);
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public synchronized boolean removeOverlay(String str, boolean z) throws RemoteException {
        IOverlayDelegate iOverlayDelegateA = a(str);
        if (iOverlayDelegateA == null) {
            return false;
        }
        if (z) {
            iOverlayDelegateA.destroy();
        }
        return this.f1779e.remove(iOverlayDelegateA);
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public synchronized void changeOverlayIndex() {
        this.f1782h = true;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public synchronized void draw(boolean z, int i) {
        try {
            c();
            MapConfig mapConfig = this.f1775a.getMapConfig();
            if (mapConfig == null) {
                return;
            }
            if (this.f1782h) {
                this.j.run();
                this.f1782h = false;
            }
            int size = this.f1779e.size();
            for (IOverlayDelegate iOverlayDelegate : this.f1779e) {
                if (iOverlayDelegate.isVisible()) {
                    if (size > 20) {
                        if (iOverlayDelegate.checkInBounds()) {
                            if (z) {
                                if (iOverlayDelegate.getZIndex() <= i) {
                                    iOverlayDelegate.draw(mapConfig);
                                }
                            } else if (iOverlayDelegate.getZIndex() > i) {
                                iOverlayDelegate.draw(mapConfig);
                            }
                        }
                    } else if (z) {
                        if (iOverlayDelegate.getZIndex() <= i) {
                            iOverlayDelegate.draw(mapConfig);
                        }
                    } else if (iOverlayDelegate.getZIndex() > i) {
                        iOverlayDelegate.draw(mapConfig);
                    }
                }
            }
        } catch (Throwable th) {
            hn.c(th, "GlOverlayLayer", "draw");
        }
    }

    public void c() {
        synchronized (this.f1780f) {
            for (int i = 0; i < this.f1780f.size(); i++) {
                x xVar = this.f1780f.get(i);
                if (xVar != null) {
                    xVar.n();
                    if (xVar.o() <= 0) {
                        this.f1781g[0] = xVar.k();
                        GLES20.glDeleteTextures(1, this.f1781g, 0);
                        xVar.a(0);
                        if (this.f1775a != null) {
                            this.f1775a.removeTextureItem(xVar.p());
                        }
                    }
                }
            }
            this.f1780f.clear();
        }
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public void addRecycleTextureIds(x xVar) {
        synchronized (this.f1780f) {
            if (xVar != null) {
                this.f1780f.add(xVar);
            }
        }
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public x getTextureItem(BitmapDescriptor bitmapDescriptor) {
        IAMapDelegate iAMapDelegate = this.f1775a;
        if (iAMapDelegate != null) {
            return iAMapDelegate.getTextureItem(bitmapDescriptor, true);
        }
        return null;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public Polyline getHitOverlay(LatLng latLng) {
        IOverlayDelegate iOverlayDelegateA = a(latLng);
        if (iOverlayDelegateA != null) {
            return new Polyline((IPolylineDelegate) iOverlayDelegateA);
        }
        return null;
    }

    public synchronized IOverlayDelegate a(LatLng latLng) {
        for (IOverlayDelegate iOverlayDelegate : this.f1779e) {
            if (iOverlayDelegate != null && iOverlayDelegate.isDrawFinish() && (iOverlayDelegate instanceof IPolylineDelegate) && ((IPolylineDelegate) iOverlayDelegate).contains(latLng)) {
                return iOverlayDelegate;
            }
        }
        return null;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public IAMapDelegate getMap() {
        return this.f1775a;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public float[] getFinalMatrix() {
        IAMapDelegate iAMapDelegate = this.f1775a;
        return iAMapDelegate != null ? iAMapDelegate.getFinalMatrix() : new float[16];
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public void setRunLowFrame(boolean z) {
        IAMapDelegate iAMapDelegate = this.f1775a;
        if (iAMapDelegate != null) {
            iAMapDelegate.setRunLowFrame(z);
        }
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public BaseOverlay addOverlayObject(String str, BaseOverlay baseOverlay, BaseOptions baseOptions) {
        IBuildingDelegate iBuildingDelegateA;
        BaseOverlay buildingOverlay;
        try {
            if (baseOverlay instanceof Polyline) {
                IPolylineDelegate iPolylineDelegateA = a((PolylineOptions) baseOptions);
                if (iPolylineDelegateA == null) {
                    return baseOverlay;
                }
                buildingOverlay = new Polyline(iPolylineDelegateA);
            } else if (baseOverlay instanceof NavigateArrow) {
                INavigateArrowDelegate iNavigateArrowDelegateA = a((NavigateArrowOptions) baseOptions);
                if (iNavigateArrowDelegateA == null) {
                    return baseOverlay;
                }
                buildingOverlay = new NavigateArrow(iNavigateArrowDelegateA);
            } else if (baseOverlay instanceof Polygon) {
                IPolygonDelegate iPolygonDelegateA = a((PolygonOptions) baseOptions);
                if (iPolygonDelegateA == null) {
                    return baseOverlay;
                }
                buildingOverlay = new Polygon(iPolygonDelegateA);
            } else if (baseOverlay instanceof Circle) {
                ICircleDelegate iCircleDelegateA = a((CircleOptions) baseOptions);
                if (iCircleDelegateA == null) {
                    return baseOverlay;
                }
                buildingOverlay = new Circle(iCircleDelegateA);
            } else if (baseOverlay instanceof Arc) {
                IArcDelegate iArcDelegateA = a((ArcOptions) baseOptions);
                if (iArcDelegateA == null) {
                    return baseOverlay;
                }
                buildingOverlay = new Arc(iArcDelegateA);
            } else if (baseOverlay instanceof GroundOverlay) {
                IGroundOverlayDelegate iGroundOverlayDelegateA = a((GroundOverlayOptions) baseOptions);
                if (iGroundOverlayDelegateA == null) {
                    return baseOverlay;
                }
                buildingOverlay = new GroundOverlay(iGroundOverlayDelegateA);
            } else if (baseOverlay instanceof ParticleOverlay) {
                IParticleLatyer iParticleLatyerA = a((ParticleOverlayOptions) baseOptions);
                if (iParticleLatyerA == null) {
                    return baseOverlay;
                }
                buildingOverlay = new ParticleOverlay(iParticleLatyerA);
            } else if (baseOverlay instanceof HeatMapLayer) {
                IHeatMapLayer iHeatMapLayerA = a((HeatMapLayerOptions) baseOptions);
                if (iHeatMapLayerA == null) {
                    return baseOverlay;
                }
                buildingOverlay = new HeatMapLayer(iHeatMapLayerA);
            } else {
                if (!(baseOverlay instanceof BuildingOverlay) || (iBuildingDelegateA = a()) == null) {
                    return baseOverlay;
                }
                buildingOverlay = new BuildingOverlay(iBuildingDelegateA);
            }
            return buildingOverlay;
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return baseOverlay;
        }
    }
}