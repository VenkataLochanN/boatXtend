package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.interfaces.IGlOverlayLayer;
import com.amap.api.maps.model.BaseHoleOptions;
import com.amap.api.maps.model.BaseOptions;
import com.amap.api.maps.model.BaseOverlay;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CircleHoleOptions;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.GroundOverlay;
import com.amap.api.maps.model.GroundOverlayOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Polygon;
import com.amap.api.maps.model.PolygonHoleOptions;
import com.amap.api.maps.model.PolygonOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.model.particle.ParticleOverlay;
import com.amap.api.maps.model.particle.ParticleOverlayOptions;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.mapcore.AMapNativeGlOverlayLayer;
import com.autonavi.base.amap.mapcore.MapConfig;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: GlOverlayLayer.java */
/* JADX INFO: loaded from: classes.dex */
public class r implements IGlOverlayLayer, AMapNativeGlOverlayLayer.setRunLowFrameListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    IAMapDelegate f1767a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private de f1769c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f1770d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Object f1771e = new Object();
    private BitmapDescriptor i = null;
    private BitmapDescriptor j = null;
    private BitmapDescriptor k = null;
    private BitmapDescriptor l = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    boolean f1768b = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Map<String, Polyline> f1773g = new HashMap();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private ArrayList<Pair<BaseOverlay, BaseOptions>> f1774h = new ArrayList<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private AMapNativeGlOverlayLayer f1772f = new AMapNativeGlOverlayLayer();

    public void a() {
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public void addRecycleTextureIds(x xVar) {
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public void changeOverlayIndex() {
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public x getTextureItem(BitmapDescriptor bitmapDescriptor) {
        return null;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public boolean removeOverlay(String str, boolean z) {
        return false;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public String createId(String str) {
        String str2;
        synchronized (this.f1771e) {
            this.f1770d++;
            str2 = str + this.f1770d;
        }
        return str2;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public de getGLShaderManager() {
        return this.f1769c;
    }

    public r(IAMapDelegate iAMapDelegate) {
        this.f1767a = iAMapDelegate;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public void setGlShaderManager(de deVar) {
        this.f1769c = deVar;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public synchronized void clear(String str) {
        try {
            if (this.f1772f != null) {
                this.f1772f.clear(str);
            }
            synchronized (this.f1773g) {
                this.f1773g.clear();
            }
            synchronized (this.f1774h) {
                this.f1774h.clear();
            }
        } catch (Throwable th) {
            hn.c(th, "GlOverlayLayer", "clear");
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public synchronized void destroy() {
        try {
            if (this.f1772f == null) {
                return;
            }
            synchronized (this.f1773g) {
                this.f1773g.clear();
            }
            synchronized (this.f1774h) {
                this.f1774h.clear();
            }
            this.f1772f.clear("");
            this.f1772f.destroy();
        } catch (Throwable th) {
            hn.c(th, "GlOverlayLayer", "destroy");
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public synchronized void draw(boolean z, int i) {
        try {
            MapConfig mapConfig = this.f1767a.getMapConfig();
            if (mapConfig == null) {
                return;
            }
            if (this.f1772f != null) {
                this.f1772f.setConfig(mapConfig, Float.valueOf(this.f1767a.getZoomLevel()));
                this.f1772f.render(z, i);
                a();
            }
        } finally {
        }
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public synchronized Polyline getHitOverlay(LatLng latLng) {
        Polyline polyline;
        if (this.f1772f != null) {
            String strContain = this.f1772f.contain(latLng);
            if (TextUtils.isEmpty(strContain)) {
                return null;
            }
            synchronized (this.f1773g) {
                polyline = this.f1773g.get(strContain);
            }
        } else {
            polyline = null;
        }
        return polyline;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public IAMapDelegate getMap() {
        return this.f1767a;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public void setRunLowFrame(boolean z) {
        IAMapDelegate iAMapDelegate = this.f1767a;
        if (iAMapDelegate != null) {
            iAMapDelegate.setRunLowFrame(z);
        }
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public void onCreateAMapInstance() {
        if (this.f1772f == null) {
            this.f1772f = new AMapNativeGlOverlayLayer();
        }
        this.f1772f.createNative();
        this.f1772f.setShaderManager(this.f1769c);
        this.f1772f.setLowFrameListener(this);
        this.f1772f.setAMapEngine(this.f1767a.getGLMapEngine().getNativeInstance());
        for (BitmapDescriptor bitmapDescriptor : new BitmapDescriptor[]{this.i, this.j, this.k, this.l}) {
            this.f1772f.addTexture(bitmapDescriptor.getId(), bitmapDescriptor.getBitmap(), "");
        }
        this.f1772f.initDefaultBitmapSymbols(this.j.getId(), this.k.getId(), this.i.getId(), this.l.getId());
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public float[] getFinalMatrix() {
        IAMapDelegate iAMapDelegate = this.f1767a;
        return iAMapDelegate != null ? iAMapDelegate.getFinalMatrix() : new float[16];
    }

    private void a(String str, BaseOptions baseOptions) {
        try {
            this.f1772f.createOverlay(str, baseOptions);
        } catch (Throwable th) {
            hn.c(th, "GlOverlayLayer", "addOverlay");
            th.printStackTrace();
            Log.d("amapApi", "GlOverlayLayer addOverlay error:" + th.getMessage());
        }
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public void prepareIcon(String str, Object obj) {
        if (this.f1772f == null) {
            return;
        }
        if (obj instanceof GroundOverlayOptions) {
            BitmapDescriptor image = ((GroundOverlayOptions) obj).getImage();
            this.f1772f.addTexture(image.getId(), image.getBitmap(), str);
            return;
        }
        if (obj instanceof ParticleOverlayOptions) {
            BitmapDescriptor icon = ((ParticleOverlayOptions) obj).getIcon();
            this.f1772f.addTexture(icon.getId(), icon.getBitmap(), str);
            return;
        }
        if (obj instanceof PolylineOptions) {
            PolylineOptions polylineOptions = (PolylineOptions) obj;
            List<BitmapDescriptor> customTextureList = polylineOptions.getCustomTextureList();
            if (customTextureList != null) {
                for (BitmapDescriptor bitmapDescriptor : customTextureList) {
                    if (bitmapDescriptor != null) {
                        this.f1772f.addTexture(bitmapDescriptor.getId(), bitmapDescriptor.getBitmap(), str);
                    }
                }
            }
            BitmapDescriptor customTexture = polylineOptions.getCustomTexture();
            if (customTexture != null) {
                this.f1772f.addTexture(customTexture.getId(), customTexture.getBitmap(), str);
            }
            BitmapDescriptor footPrintTexture = polylineOptions.getFootPrintTexture();
            if (footPrintTexture != null) {
                this.f1772f.addTexture(footPrintTexture.getId(), footPrintTexture.getBitmap(), str);
            }
            BitmapDescriptor eraseTexture = polylineOptions.getEraseTexture();
            if (eraseTexture != null) {
                this.f1772f.addTexture(eraseTexture.getId(), eraseTexture.getBitmap(), str);
            }
        }
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public void updateOption(String str, BaseOptions baseOptions) {
        try {
            prepareIcon(str, baseOptions);
            this.f1772f.updateOptions(str, baseOptions);
        } catch (Throwable th) {
            hn.c(th, "GlOverlayLayer", "updateOption");
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public boolean removeOverlay(String str) {
        boolean z;
        AMapNativeGlOverlayLayer aMapNativeGlOverlayLayer = this.f1772f;
        if (aMapNativeGlOverlayLayer != null) {
            aMapNativeGlOverlayLayer.removeOverlay(str);
            z = true;
        } else {
            z = false;
        }
        synchronized (this.f1773g) {
            this.f1773g.remove(str);
        }
        return z;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public int getCurrentParticleNum(String str) {
        AMapNativeGlOverlayLayer aMapNativeGlOverlayLayer = this.f1772f;
        if (aMapNativeGlOverlayLayer != null) {
            return aMapNativeGlOverlayLayer.getCurrentParticleNum(str);
        }
        return 0;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public void loadBitmapDescription(Context context) {
        BitmapDescriptor bitmapDescriptor = this.i;
        if (bitmapDescriptor == null || bitmapDescriptor.getBitmap().isRecycled()) {
            this.i = BitmapDescriptorFactory.fromBitmap(er.a(context, "amap_sdk_lineTexture.png"));
        }
        BitmapDescriptor bitmapDescriptor2 = this.l;
        if (bitmapDescriptor2 == null || bitmapDescriptor2.getBitmap().isRecycled()) {
            this.l = BitmapDescriptorFactory.fromBitmap(er.a(context, "amap_sdk_lineTexture.png"));
        }
        BitmapDescriptor bitmapDescriptor3 = this.j;
        if (bitmapDescriptor3 == null || bitmapDescriptor3.getBitmap().isRecycled()) {
            this.j = BitmapDescriptorFactory.fromBitmap(er.a(context, "amap_sdk_lineDashTexture_square.png"));
        }
        BitmapDescriptor bitmapDescriptor4 = this.k;
        if (bitmapDescriptor4 == null || bitmapDescriptor4.getBitmap().isRecycled()) {
            this.k = BitmapDescriptorFactory.fromBitmap(er.a(context, "amap_sdk_lineDashTexture_circle.png"));
        }
    }

    @Override // com.autonavi.base.amap.mapcore.AMapNativeGlOverlayLayer.setRunLowFrameListener
    public void onSetRunLowFrame(boolean z) {
        setRunLowFrame(z);
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public BaseOverlay addOverlayObject(String str, BaseOverlay baseOverlay, BaseOptions baseOptions) {
        a(str, baseOverlay, baseOptions);
        return baseOverlay;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public LatLng getNearestLatLng(PolylineOptions polylineOptions, LatLng latLng) {
        List<LatLng> points;
        if (latLng != null && polylineOptions != null && (points = polylineOptions.getPoints()) != null && points.size() != 0) {
            float fCalculateLineDistance = 0.0f;
            int i = 0;
            for (int i2 = 0; i2 < points.size(); i2++) {
                try {
                    if (i2 == 0) {
                        fCalculateLineDistance = AMapUtils.calculateLineDistance(latLng, points.get(i2));
                    } else {
                        float fCalculateLineDistance2 = AMapUtils.calculateLineDistance(latLng, points.get(i2));
                        if (fCalculateLineDistance > fCalculateLineDistance2) {
                            i = i2;
                            fCalculateLineDistance = fCalculateLineDistance2;
                        }
                    }
                } catch (Throwable th) {
                    hn.c(th, "PolylineDelegate", "getNearestLatLng");
                    th.printStackTrace();
                }
            }
            return points.get(i);
        }
        return null;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public boolean IsPolygonContainsPoint(PolygonOptions polygonOptions, LatLng latLng) {
        if (latLng == null) {
            return false;
        }
        try {
            List<BaseHoleOptions> holeOptions = polygonOptions.getHoleOptions();
            if (holeOptions != null && holeOptions.size() > 0) {
                Iterator<BaseHoleOptions> it = holeOptions.iterator();
                while (it.hasNext()) {
                    if (er.a(it.next(), latLng)) {
                        return false;
                    }
                }
            }
            return er.a(latLng, polygonOptions.getPoints());
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public void processPolygonHoleOption(PolygonOptions polygonOptions) {
        boolean z;
        try {
            Field declaredField = polygonOptions.getClass().getDeclaredField("isHoleOptionsUpdated");
            declaredField.setAccessible(true);
            z = declaredField.getBoolean(polygonOptions);
        } catch (Throwable th) {
            hn.c(th, "Polygon", "isHoleOptionsUpdated");
            z = false;
        }
        if (!z || polygonOptions.getHoleOptions() == null) {
            return;
        }
        List<BaseHoleOptions> arrayList = new ArrayList<>();
        List<BaseHoleOptions> holeOptions = polygonOptions.getHoleOptions();
        for (int i = 0; i < holeOptions.size(); i++) {
            BaseHoleOptions baseHoleOptions = holeOptions.get(i);
            if (baseHoleOptions instanceof PolygonHoleOptions) {
                PolygonHoleOptions polygonHoleOptions = (PolygonHoleOptions) baseHoleOptions;
                if (er.b(polygonOptions.getPoints(), polygonHoleOptions) && !er.a(arrayList, polygonHoleOptions)) {
                    arrayList.add(polygonHoleOptions);
                }
            } else if (baseHoleOptions instanceof CircleHoleOptions) {
                CircleHoleOptions circleHoleOptions = (CircleHoleOptions) baseHoleOptions;
                if (er.a(polygonOptions.getPoints(), arrayList, circleHoleOptions) && !er.a(arrayList, circleHoleOptions)) {
                    arrayList.add(circleHoleOptions);
                }
            }
        }
        polygonOptions.setHoleOptions(arrayList);
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public boolean IsCircleContainPoint(CircleOptions circleOptions, LatLng latLng) {
        if (latLng != null && circleOptions != null) {
            try {
                synchronized (circleOptions) {
                    List<BaseHoleOptions> holeOptions = circleOptions.getHoleOptions();
                    if (holeOptions != null && holeOptions.size() > 0) {
                        Iterator<BaseHoleOptions> it = holeOptions.iterator();
                        while (it.hasNext()) {
                            if (er.a(it.next(), latLng)) {
                                return false;
                            }
                        }
                    }
                    return circleOptions.getRadius() >= ((double) AMapUtils.calculateLineDistance(circleOptions.getCenter(), latLng));
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return false;
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public void processCircleHoleOption(CircleOptions circleOptions) {
        boolean z;
        try {
            Field declaredField = circleOptions.getClass().getDeclaredField("isHoleOptionsUpdated");
            declaredField.setAccessible(true);
            z = declaredField.getBoolean(circleOptions);
        } catch (Throwable th) {
            hn.c(th, "Circle", "isHoleOptionsUpdated");
            z = false;
        }
        if (!z || circleOptions.getHoleOptions() == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        List<BaseHoleOptions> holeOptions = circleOptions.getHoleOptions();
        for (int i = 0; i < holeOptions.size(); i++) {
            BaseHoleOptions baseHoleOptions = holeOptions.get(i);
            if (baseHoleOptions instanceof PolygonHoleOptions) {
                PolygonHoleOptions polygonHoleOptions = (PolygonHoleOptions) baseHoleOptions;
                if (er.a(circleOptions.getRadius(), circleOptions.getCenter(), arrayList, polygonHoleOptions) && !er.a(arrayList, polygonHoleOptions)) {
                    arrayList.add(polygonHoleOptions);
                }
            } else if (baseHoleOptions instanceof CircleHoleOptions) {
                CircleHoleOptions circleHoleOptions = (CircleHoleOptions) baseHoleOptions;
                if (er.a(circleOptions.getRadius(), circleOptions.getCenter(), circleHoleOptions) && !er.a(arrayList, circleHoleOptions)) {
                    arrayList.add(circleHoleOptions);
                }
            }
        }
        circleOptions.getHoleOptions().clear();
        circleOptions.addHoles(arrayList);
    }

    private void a(BaseOverlay baseOverlay, Object obj) {
        if (baseOverlay == null || obj == null) {
            return;
        }
        try {
            Method declaredMethod = baseOverlay.getClass().getDeclaredMethod("setOptionPointList", Object.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(baseOverlay, obj);
        } catch (Throwable th) {
            hn.c(th, "GlOverlayLayer", "setOptionPointList:" + th.toString());
        }
    }

    @Override // com.amap.api.maps.interfaces.IGlOverlayLayer
    public Object getNativeProperties(String str, String str2, Object[] objArr) {
        AMapNativeGlOverlayLayer aMapNativeGlOverlayLayer = this.f1772f;
        if (aMapNativeGlOverlayLayer != null) {
            return aMapNativeGlOverlayLayer.getNativeProperties(str, str2, objArr);
        }
        return null;
    }

    private void a(String str, BaseOverlay baseOverlay, BaseOptions baseOptions) {
        boolean z = baseOverlay instanceof Polyline;
        if (z) {
            a((Polyline) baseOverlay, baseOptions);
            prepareIcon(str, baseOptions);
        } else if (baseOverlay instanceof Polygon) {
            a((Polygon) baseOverlay, baseOptions);
        } else if ((baseOverlay instanceof ParticleOverlay) || (baseOverlay instanceof GroundOverlay)) {
            prepareIcon(str, baseOptions);
        }
        a(str, baseOptions);
        if (z) {
            synchronized (this.f1773g) {
                this.f1773g.put(str, (Polyline) baseOverlay);
            }
        }
    }
}