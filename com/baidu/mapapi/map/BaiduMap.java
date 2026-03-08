package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.PointerIconCompat;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapBaseIndoorMapInfo;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.model.ParcelItem;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapsdkplatform.comapi.map.ab;
import com.baidu.mapsdkplatform.comapi.map.ac;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class BaiduMap {
    public static final int MAP_TYPE_NONE = 3;
    public static final int MAP_TYPE_NORMAL = 1;
    public static final int MAP_TYPE_SATELLITE = 2;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f2771e = BaiduMap.class.getSimpleName();
    public static int mapStatusReason;
    private OnMyLocationClickListener A;
    private SnapshotReadyCallback B;
    private OnMapDrawFrameCallback C;
    private OnBaseIndoorMapListener D;
    private OnMapRenderValidDataListener E;
    private OnSynchronizationListener F;
    private TileOverlay G;
    private HeatMap H;
    private Lock I;
    private Lock J;
    private Map<String, InfoWindow> K;
    private Map<InfoWindow, Marker> L;
    private Marker M;
    private MyLocationData N;
    private MyLocationConfiguration O;
    private boolean P;
    private boolean Q;
    private boolean R;
    private boolean S;
    private Point T;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    MapView f2772a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    TextureMapView f2773b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    WearMapView f2774c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    com.baidu.mapsdkplatform.comapi.map.aa f2775d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Projection f2776f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private UiSettings f2777g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private com.baidu.mapsdkplatform.comapi.map.j f2778h;
    private com.baidu.mapsdkplatform.comapi.map.e i;
    private ac j;
    private List<Overlay> k;
    private List<Marker> l;
    private List<Marker> m;
    private List<InfoWindow> n;
    private Overlay.a o;
    private InfoWindow.a p;
    private OnMapStatusChangeListener q;
    private OnMapTouchListener r;
    private OnMapClickListener s;
    private OnMapLoadedCallback t;
    private OnMapRenderCallback u;
    private OnMapDoubleClickListener v;
    private OnMapLongClickListener w;
    private CopyOnWriteArrayList<OnMarkerClickListener> x;
    private CopyOnWriteArrayList<OnPolylineClickListener> y;
    private OnMarkerDragListener z;

    public interface OnBaseIndoorMapListener {
        void onBaseIndoorMapMode(boolean z, MapBaseIndoorMapInfo mapBaseIndoorMapInfo);
    }

    public interface OnMapClickListener {
        void onMapClick(LatLng latLng);

        boolean onMapPoiClick(MapPoi mapPoi);
    }

    public interface OnMapDoubleClickListener {
        void onMapDoubleClick(LatLng latLng);
    }

    public interface OnMapDrawFrameCallback {
        void onMapDrawFrame(MapStatus mapStatus);

        @Deprecated
        void onMapDrawFrame(GL10 gl10, MapStatus mapStatus);
    }

    public interface OnMapLoadedCallback {
        void onMapLoaded();
    }

    public interface OnMapLongClickListener {
        void onMapLongClick(LatLng latLng);
    }

    public interface OnMapRenderCallback {
        void onMapRenderFinished();
    }

    public interface OnMapRenderValidDataListener {
        void onMapRenderValidData(boolean z, int i, String str);
    }

    public interface OnMapStatusChangeListener {
        public static final int REASON_API_ANIMATION = 2;
        public static final int REASON_DEVELOPER_ANIMATION = 3;
        public static final int REASON_GESTURE = 1;

        void onMapStatusChange(MapStatus mapStatus);

        void onMapStatusChangeFinish(MapStatus mapStatus);

        void onMapStatusChangeStart(MapStatus mapStatus);

        void onMapStatusChangeStart(MapStatus mapStatus, int i);
    }

    public interface OnMapTouchListener {
        void onTouch(MotionEvent motionEvent);
    }

    public interface OnMarkerClickListener {
        boolean onMarkerClick(Marker marker);
    }

    public interface OnMarkerDragListener {
        void onMarkerDrag(Marker marker);

        void onMarkerDragEnd(Marker marker);

        void onMarkerDragStart(Marker marker);
    }

    public interface OnMyLocationClickListener {
        boolean onMyLocationClick();
    }

    public interface OnPolylineClickListener {
        boolean onPolylineClick(Polyline polyline);
    }

    public interface OnSynchronizationListener {
        void onMapStatusChangeReason(int i);
    }

    public interface SnapshotReadyCallback {
        void onSnapshotReady(Bitmap bitmap);
    }

    BaiduMap(ac acVar) {
        this.x = new CopyOnWriteArrayList<>();
        this.y = new CopyOnWriteArrayList<>();
        this.I = new ReentrantLock();
        this.J = new ReentrantLock();
        this.j = acVar;
        this.i = this.j.b();
        this.f2775d = com.baidu.mapsdkplatform.comapi.map.aa.TextureView;
        c();
    }

    BaiduMap(com.baidu.mapsdkplatform.comapi.map.j jVar) {
        this.x = new CopyOnWriteArrayList<>();
        this.y = new CopyOnWriteArrayList<>();
        this.I = new ReentrantLock();
        this.J = new ReentrantLock();
        this.f2778h = jVar;
        this.i = this.f2778h.a();
        this.f2775d = com.baidu.mapsdkplatform.comapi.map.aa.GLSurfaceView;
        c();
    }

    private Point a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int iIntValue = 0;
        int iIntValue2 = 0;
        for (String str2 : str.replaceAll("^\\{", "").replaceAll("\\}$", "").split(AppInfo.DELIM)) {
            String[] strArrSplit = str2.replaceAll("\"", "").split(":");
            if ("x".equals(strArrSplit[0])) {
                iIntValue = Integer.valueOf(strArrSplit[1]).intValue();
            }
            if ("y".equals(strArrSplit[0])) {
                iIntValue2 = Integer.valueOf(strArrSplit[1]).intValue();
            }
        }
        return new Point(iIntValue, iIntValue2);
    }

    private ab a(MapStatusUpdate mapStatusUpdate) {
        com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
        if (eVar == null) {
            return null;
        }
        ab abVarE = eVar.E();
        MapStatus mapStatusA = mapStatusUpdate.a(this.i, getMapStatus());
        if (mapStatusA == null) {
            return null;
        }
        return mapStatusA.b(abVarE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(int i) {
        if (i == 0) {
            return "数据请求成功";
        }
        switch (i) {
            case 1004:
                return "网络连接错误";
            case 1005:
                return "请求发送错误";
            case 1006:
                return "响应数据读取失败";
            case 1007:
                return "返回响应数据过大，数据溢出";
            case 1008:
                return "当前网络类型有问题";
            case 1009:
                return "数据不一致";
            case PointerIconCompat.TYPE_ALIAS /* 1010 */:
                return "请求取消";
            case 1011:
                return "网络超时错误";
            case PointerIconCompat.TYPE_NO_DROP /* 1012 */:
                return "网络连接超时";
            case PointerIconCompat.TYPE_ALL_SCROLL /* 1013 */:
                return "网络发送超时";
            case PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW /* 1014 */:
                return "网络接收超时";
            case PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW /* 1015 */:
                return "DNS解析错误";
            case PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW /* 1016 */:
                return "DNS解析超时";
            case PointerIconCompat.TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW /* 1017 */:
                return "网络写错误";
            case PointerIconCompat.TYPE_ZOOM_IN /* 1018 */:
                return "SSL握手错误";
            case PointerIconCompat.TYPE_ZOOM_OUT /* 1019 */:
                return "SSL握手超时";
            default:
                return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:27:0x006e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(com.baidu.mapapi.map.InfoWindow r8) {
        /*
            r7 = this;
            if (r8 != 0) goto L3
            return
        L3:
            java.util.Map<com.baidu.mapapi.map.InfoWindow, com.baidu.mapapi.map.Marker> r0 = r7.L
            java.util.Set r0 = r0.keySet()
            boolean r1 = r0.isEmpty()
            r2 = 0
            if (r1 != 0) goto Laa
            boolean r0 = r0.contains(r8)
            if (r0 != 0) goto L18
            goto Laa
        L18:
            android.view.View r0 = r8.f2848b
            r1 = 1
            if (r0 == 0) goto L6e
            boolean r3 = r8.j
            if (r3 == 0) goto L6e
            r0.destroyDrawingCache()
            com.baidu.mapapi.map.MapViewLayoutParams$Builder r3 = new com.baidu.mapapi.map.MapViewLayoutParams$Builder
            r3.<init>()
            com.baidu.mapapi.map.MapViewLayoutParams$ELayoutMode r4 = com.baidu.mapapi.map.MapViewLayoutParams.ELayoutMode.mapMode
            com.baidu.mapapi.map.MapViewLayoutParams$Builder r3 = r3.layoutMode(r4)
            com.baidu.mapapi.model.LatLng r4 = r8.f2849c
            com.baidu.mapapi.map.MapViewLayoutParams$Builder r3 = r3.position(r4)
            int r4 = r8.f2852f
            com.baidu.mapapi.map.MapViewLayoutParams$Builder r3 = r3.yOffset(r4)
            com.baidu.mapapi.map.MapViewLayoutParams r3 = r3.build()
            int[] r4 = com.baidu.mapapi.map.f.f3039b
            com.baidu.mapsdkplatform.comapi.map.aa r5 = r7.f2775d
            int r5 = r5.ordinal()
            r4 = r4[r5]
            if (r4 == r1) goto L5c
            r5 = 2
            if (r4 == r5) goto L4f
            goto L68
        L4f:
            com.baidu.mapapi.map.MapView r4 = r7.f2772a
            if (r4 == 0) goto L68
            r4.removeView(r0)
            com.baidu.mapapi.map.MapView r4 = r7.f2772a
            r4.addView(r0, r3)
            goto L68
        L5c:
            com.baidu.mapapi.map.TextureMapView r4 = r7.f2773b
            if (r4 == 0) goto L68
            r4.removeView(r0)
            com.baidu.mapapi.map.TextureMapView r4 = r7.f2773b
            r4.addView(r0, r3)
        L68:
            boolean r0 = r8.i
            if (r0 == 0) goto L6e
            r0 = r2
            goto L6f
        L6e:
            r0 = r1
        L6f:
            com.baidu.mapapi.map.BitmapDescriptor r3 = r7.b(r8)
            java.util.Map<com.baidu.mapapi.map.InfoWindow, com.baidu.mapapi.map.Marker> r4 = r7.L
            java.lang.Object r4 = r4.get(r8)
            com.baidu.mapapi.map.Marker r4 = (com.baidu.mapapi.map.Marker) r4
            if (r4 == 0) goto La9
            android.os.Bundle r5 = new android.os.Bundle
            r5.<init>()
            com.baidu.mapapi.map.BitmapDescriptor r6 = r8.f2847a
            if (r6 == 0) goto L99
            com.baidu.mapsdkplatform.comapi.map.h r6 = com.baidu.mapsdkplatform.comapi.map.h.popup
            r4.type = r6
            r4.f2911b = r3
            android.view.View r3 = r8.f2848b
            java.lang.String r6 = "draw_with_view"
            if (r3 == 0) goto L96
            r5.putInt(r6, r1)
            goto L99
        L96:
            r5.putInt(r6, r2)
        L99:
            com.baidu.mapapi.model.LatLng r8 = r8.f2849c
            r4.f2910a = r8
            r4.a(r5)
            com.baidu.mapsdkplatform.comapi.map.e r8 = r7.i
            if (r8 == 0) goto La9
            if (r0 == 0) goto La9
            r8.c(r5)
        La9:
            return
        Laa:
            r7.showInfoWindow(r8, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapapi.map.BaiduMap.a(com.baidu.mapapi.map.InfoWindow):void");
    }

    private final void a(MyLocationData myLocationData, MyLocationConfiguration myLocationConfiguration) {
        int i;
        BaiduMap baiduMap;
        Bundle bundle;
        MapStatus.Builder builderZoom;
        float f2;
        if (myLocationData == null || myLocationConfiguration == null || !isMyLocationEnabled()) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        GeoPoint geoPointLl2mc = CoordUtil.ll2mc(new LatLng(myLocationData.latitude, myLocationData.longitude));
        try {
            jSONObject.put("type", 0);
            jSONObject2.put("ptx", geoPointLl2mc.getLongitudeE6());
            jSONObject2.put("pty", geoPointLl2mc.getLatitudeE6());
            jSONObject2.put("radius", CoordUtil.getMCDistanceByOneLatLngAndRadius(r1, (int) myLocationData.accuracy));
            float f3 = myLocationData.direction;
            if (myLocationConfiguration.enableDirection) {
                f2 = myLocationData.direction % 360.0f;
                if (f2 > 180.0f) {
                    f2 -= 360.0f;
                } else if (f2 < -180.0f) {
                    f2 += 360.0f;
                }
            } else {
                f2 = -1001.0f;
            }
            jSONObject2.put("direction", f2);
            jSONObject2.put("iconarrownor", "NormalLocArrow");
            jSONObject2.put("iconarrownorid", 28);
            try {
                jSONObject2.put("iconarrowfoc", "FocusLocArrow");
                jSONObject2.put("iconarrowfocid", 29);
                jSONObject2.put("lineid", myLocationConfiguration.accuracyCircleStrokeColor);
                jSONObject2.put("areaid", myLocationConfiguration.accuracyCircleFillColor);
                jSONArray.put(jSONObject2);
                jSONObject.put(AeUtil.ROOT_DATA_PATH_OLD_NAME, jSONArray);
                if (myLocationConfiguration.locationMode == MyLocationConfiguration.LocationMode.COMPASS) {
                    jSONObject3.put("ptx", geoPointLl2mc.getLongitudeE6());
                    jSONObject3.put("pty", geoPointLl2mc.getLatitudeE6());
                    i = 0;
                    try {
                        jSONObject3.put("radius", 0);
                        jSONObject3.put("direction", 0);
                        jSONObject3.put("iconarrownor", "direction_wheel");
                        jSONObject3.put("iconarrownorid", 54);
                        jSONObject3.put("iconarrowfoc", "direction_wheel");
                        jSONObject3.put("iconarrowfocid", 54);
                        jSONArray.put(jSONObject3);
                    } catch (JSONException e2) {
                        e = e2;
                        e.printStackTrace();
                    }
                } else {
                    i = 0;
                }
            } catch (JSONException e3) {
                e = e3;
                i = 0;
            }
        } catch (JSONException e4) {
            e = e4;
            i = 0;
        }
        if (myLocationConfiguration.customMarker == null) {
            bundle = null;
            baiduMap = this;
        } else {
            ArrayList<BitmapDescriptor> arrayList = new ArrayList();
            arrayList.add(myLocationConfiguration.customMarker);
            Bundle bundle2 = new Bundle();
            ArrayList arrayList2 = new ArrayList();
            for (BitmapDescriptor bitmapDescriptor : arrayList) {
                ParcelItem parcelItem = new ParcelItem();
                Bundle bundle3 = new Bundle();
                Bitmap bitmap = bitmapDescriptor.f2787a;
                ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bitmap.getWidth() * bitmap.getHeight() * 4);
                bitmap.copyPixelsToBuffer(byteBufferAllocate);
                bundle3.putByteArray("imgdata", byteBufferAllocate.array());
                bundle3.putInt("imgindex", bitmapDescriptor.hashCode());
                bundle3.putInt("imgH", bitmap.getHeight());
                bundle3.putInt("imgW", bitmap.getWidth());
                parcelItem.setBundle(bundle3);
                arrayList2.add(parcelItem);
            }
            if (arrayList2.size() > 0) {
                ParcelItem[] parcelItemArr = new ParcelItem[arrayList2.size()];
                while (i < arrayList2.size()) {
                    parcelItemArr[i] = (ParcelItem) arrayList2.get(i);
                    i++;
                }
                bundle2.putParcelableArray("icondata", parcelItemArr);
            }
            baiduMap = this;
            bundle = bundle2;
        }
        com.baidu.mapsdkplatform.comapi.map.e eVar = baiduMap.i;
        if (eVar != null) {
            eVar.a(jSONObject.toString(), bundle);
        }
        int i2 = f.f3038a[myLocationConfiguration.locationMode.ordinal()];
        if (i2 == 1) {
            builderZoom = new MapStatus.Builder().rotate(myLocationData.direction).overlook(-45.0f).target(new LatLng(myLocationData.latitude, myLocationData.longitude)).targetScreen(getMapStatus().targetScreen).zoom(getMapStatus().zoom);
        } else if (i2 != 2) {
            return;
        } else {
            builderZoom = new MapStatus.Builder().target(new LatLng(myLocationData.latitude, myLocationData.longitude)).zoom(getMapStatus().zoom).rotate(getMapStatus().rotate).overlook(getMapStatus().overlook).targetScreen(getMapStatus().targetScreen);
        }
        baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builderZoom.build()));
    }

    private BitmapDescriptor b(InfoWindow infoWindow) {
        BitmapDescriptor bitmapDescriptorFromView;
        if (infoWindow.f2848b == null || !infoWindow.j) {
            return infoWindow.f2847a;
        }
        if (infoWindow.f2853g) {
            if (infoWindow.f2854h <= 0) {
                infoWindow.f2854h = SysOSUtil.getDensityDpi();
            }
            bitmapDescriptorFromView = BitmapDescriptorFactory.fromViewWithDpi(infoWindow.f2848b, infoWindow.f2854h);
        } else {
            bitmapDescriptorFromView = BitmapDescriptorFactory.fromView(infoWindow.f2848b);
        }
        infoWindow.f2847a = bitmapDescriptorFromView;
        return bitmapDescriptorFromView;
    }

    private void c() {
        this.k = new CopyOnWriteArrayList();
        this.l = new CopyOnWriteArrayList();
        this.m = new CopyOnWriteArrayList();
        this.K = new ConcurrentHashMap();
        this.L = new ConcurrentHashMap();
        this.n = new CopyOnWriteArrayList();
        this.T = new Point((int) (SysOSUtil.getDensity() * 40.0f), (int) (SysOSUtil.getDensity() * 40.0f));
        this.f2777g = new UiSettings(this.i);
        this.o = new a(this);
        this.p = new b(this);
        this.i.a(new c(this));
        this.i.a(new d(this));
        this.i.a(new e(this));
        this.P = this.i.C();
        this.Q = this.i.D();
    }

    void a() {
        com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
        if (eVar == null) {
            return;
        }
        eVar.t();
    }

    void a(HeatMap heatMap) {
        this.I.lock();
        try {
            if (this.H != null && this.i != null && heatMap == this.H) {
                this.H.b();
                this.H.c();
                this.H.f2839a = null;
                this.i.o();
                this.H = null;
                this.i.o(false);
            }
        } finally {
            this.I.unlock();
        }
    }

    void a(TileOverlay tileOverlay) {
        this.J.lock();
        if (tileOverlay != null) {
            try {
                if (this.G == tileOverlay) {
                    tileOverlay.b();
                    tileOverlay.f3004a = null;
                    if (this.i != null) {
                        this.i.f(false);
                    }
                }
            } finally {
                this.G = null;
                this.J.unlock();
            }
        }
    }

    public void addHeatMap(HeatMap heatMap) {
        if (heatMap == null) {
            return;
        }
        this.I.lock();
        try {
            if (heatMap == this.H) {
                return;
            }
            if (this.H != null) {
                this.H.b();
                this.H.c();
                this.H.f2839a = null;
                this.i.o();
            }
            this.H = heatMap;
            this.H.f2839a = this;
            this.i.o(true);
        } finally {
            this.I.unlock();
        }
    }

    public final Overlay addOverlay(OverlayOptions overlayOptions) {
        if (overlayOptions == null) {
            return null;
        }
        Overlay overlayA = overlayOptions.a();
        overlayA.listener = this.o;
        if (overlayA instanceof Marker) {
            Marker marker = (Marker) overlayA;
            marker.w = this.p;
            if (marker.o != null && marker.o.size() != 0) {
                this.l.add(marker);
                com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
                if (eVar != null) {
                    eVar.b(true);
                }
            }
            this.m.add(marker);
            if (marker.v != null) {
                showInfoWindow(marker.v, false);
            }
        }
        Bundle bundle = new Bundle();
        overlayA.a(bundle);
        com.baidu.mapsdkplatform.comapi.map.e eVar2 = this.i;
        if (eVar2 != null) {
            eVar2.b(bundle);
        }
        this.k.add(overlayA);
        return overlayA;
    }

    public final List<Overlay> addOverlays(List<OverlayOptions> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Bundle[] bundleArr = new Bundle[list.size()];
        int i = 0;
        for (OverlayOptions overlayOptions : list) {
            if (overlayOptions != null) {
                Bundle bundle = new Bundle();
                Overlay overlayA = overlayOptions.a();
                overlayA.listener = this.o;
                if (overlayA instanceof Marker) {
                    Marker marker = (Marker) overlayA;
                    marker.w = this.p;
                    if (marker.o != null && marker.o.size() != 0) {
                        this.l.add(marker);
                        com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
                        if (eVar != null) {
                            eVar.b(true);
                        }
                    }
                    this.m.add(marker);
                }
                this.k.add(overlayA);
                arrayList.add(overlayA);
                overlayA.a(bundle);
                bundleArr[i] = bundle;
                i++;
            }
        }
        int length = bundleArr.length / 400;
        for (int i2 = 0; i2 < length + 1; i2++) {
            ArrayList arrayList2 = new ArrayList();
            for (int i3 = 0; i3 < 400; i3++) {
                int i4 = (i2 * 400) + i3;
                if (i4 >= bundleArr.length) {
                    break;
                }
                if (bundleArr[i4] != null) {
                    arrayList2.add(bundleArr[i4]);
                }
            }
            com.baidu.mapsdkplatform.comapi.map.e eVar2 = this.i;
            if (eVar2 != null) {
                eVar2.a(arrayList2);
            }
        }
        return arrayList;
    }

    public TileOverlay addTileLayer(TileOverlayOptions tileOverlayOptions) {
        if (tileOverlayOptions == null) {
            return null;
        }
        TileOverlay tileOverlay = this.G;
        if (tileOverlay != null) {
            tileOverlay.b();
            this.G.f3004a = null;
        }
        com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
        if (eVar == null || !eVar.a(tileOverlayOptions.a())) {
            return null;
        }
        TileOverlay tileOverlayA = tileOverlayOptions.a(this);
        this.G = tileOverlayA;
        return tileOverlayA;
    }

    public final void animateMapStatus(MapStatusUpdate mapStatusUpdate) {
        animateMapStatus(mapStatusUpdate, 300);
    }

    public final void animateMapStatus(MapStatusUpdate mapStatusUpdate, int i) {
        if (mapStatusUpdate == null || i <= 0) {
            return;
        }
        ab abVarA = a(mapStatusUpdate);
        com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
        if (eVar == null) {
            return;
        }
        mapStatusReason |= 256;
        if (this.S) {
            eVar.a(abVarA, i);
        } else {
            eVar.a(abVarA);
        }
    }

    boolean b() {
        com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
        if (eVar == null) {
            return false;
        }
        return eVar.e();
    }

    public void changeLocationLayerOrder(boolean z) {
        this.i.d(z);
    }

    public final void clear() {
        this.k.clear();
        this.l.clear();
        this.m.clear();
        com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
        if (eVar != null) {
            eVar.b(false);
            this.i.n();
        }
        hideInfoWindow();
    }

    public List<InfoWindow> getAllInfoWindows() {
        return this.n;
    }

    public final Point getCompassPosition() {
        com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
        if (eVar != null) {
            return a(eVar.h());
        }
        return null;
    }

    public MapBaseIndoorMapInfo getFocusedBaseIndoorMapInfo() {
        return this.i.p();
    }

    public final MyLocationConfiguration getLocationConfigeration() {
        return getLocationConfiguration();
    }

    public final MyLocationConfiguration getLocationConfiguration() {
        return this.O;
    }

    public final MyLocationData getLocationData() {
        return this.N;
    }

    public final MapStatus getMapStatus() {
        com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
        if (eVar == null) {
            return null;
        }
        return MapStatus.a(eVar.E());
    }

    public final LatLngBounds getMapStatusLimit() {
        com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
        if (eVar == null) {
            return null;
        }
        return eVar.F();
    }

    public final int getMapType() {
        com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
        if (eVar == null) {
            return 1;
        }
        if (eVar.l()) {
            return this.i.k() ? 2 : 1;
        }
        return 3;
    }

    public List<Marker> getMarkersInBounds(LatLngBounds latLngBounds) {
        if (getMapStatus() == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (this.m.size() == 0) {
            return null;
        }
        for (Marker marker : this.m) {
            if (latLngBounds.contains(marker.getPosition())) {
                arrayList.add(marker);
            }
        }
        return arrayList;
    }

    public final float getMaxZoomLevel() {
        com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
        if (eVar == null) {
            return 0.0f;
        }
        return eVar.f3561a;
    }

    public final float getMinZoomLevel() {
        com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
        if (eVar == null) {
            return 0.0f;
        }
        return eVar.f3562b;
    }

    public final Projection getProjection() {
        return this.f2776f;
    }

    public float[] getProjectionMatrix() {
        com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
        if (eVar == null) {
            return null;
        }
        return eVar.N();
    }

    public final UiSettings getUiSettings() {
        return this.f2777g;
    }

    public float[] getViewMatrix() {
        com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
        if (eVar == null) {
            return null;
        }
        return eVar.O();
    }

    public float getZoomToBound(int i, int i2, int i3, int i4, int i5, int i6) {
        com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
        if (eVar == null) {
            return 0.0f;
        }
        return eVar.a(i, i2, i3, i4, i5, i6);
    }

    public com.baidu.mapsdkplatform.comapi.map.j getmGLMapView() {
        return this.f2778h;
    }

    public void hideInfoWindow() {
        View view;
        MapView mapView;
        Collection<InfoWindow> collectionValues = this.K.values();
        if (!collectionValues.isEmpty()) {
            for (InfoWindow infoWindow : collectionValues) {
                if (infoWindow != null && (view = infoWindow.f2848b) != null) {
                    int i = f.f3039b[this.f2775d.ordinal()];
                    if (i == 1) {
                        TextureMapView textureMapView = this.f2773b;
                        if (textureMapView != null) {
                            textureMapView.removeView(view);
                        }
                    } else if (i == 2 && (mapView = this.f2772a) != null) {
                        mapView.removeView(view);
                    }
                }
            }
        }
        for (Overlay overlay : this.k) {
            Set<String> setKeySet = this.K.keySet();
            String str = overlay.y;
            if ((overlay instanceof Marker) && !setKeySet.isEmpty() && setKeySet.contains(str)) {
                overlay.remove();
            }
        }
        this.K.clear();
        this.L.clear();
        this.n.clear();
    }

    public void hideInfoWindow(InfoWindow infoWindow) {
        MapView mapView;
        Set<InfoWindow> setKeySet = this.L.keySet();
        if (infoWindow == null || setKeySet.isEmpty() || !setKeySet.contains(infoWindow)) {
            return;
        }
        View view = infoWindow.f2848b;
        if (view != null) {
            int i = f.f3039b[this.f2775d.ordinal()];
            if (i == 1) {
                TextureMapView textureMapView = this.f2773b;
                if (textureMapView != null) {
                    textureMapView.removeView(view);
                }
            } else if (i == 2 && (mapView = this.f2772a) != null) {
                mapView.removeView(view);
            }
        }
        Marker marker = this.L.get(infoWindow);
        if (marker != null) {
            marker.remove();
            this.K.remove(marker.y);
        }
        this.L.remove(infoWindow);
        this.n.remove(infoWindow);
    }

    public void hideSDKLayer() {
        this.i.c();
    }

    public final boolean isBaiduHeatMapEnabled() {
        com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
        if (eVar == null) {
            return false;
        }
        return eVar.i();
    }

    public boolean isBaseIndoorMapMode() {
        return this.i.q();
    }

    public final boolean isBuildingsEnabled() {
        com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
        if (eVar == null) {
            return false;
        }
        return eVar.m();
    }

    public final boolean isMyLocationEnabled() {
        com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
        if (eVar == null) {
            return false;
        }
        return eVar.s();
    }

    public final boolean isSupportBaiduHeatMap() {
        com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
        if (eVar == null) {
            return false;
        }
        return eVar.j();
    }

    public final boolean isTrafficEnabled() {
        com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
        if (eVar == null) {
            return false;
        }
        return eVar.g();
    }

    public final void removeMarkerClickListener(OnMarkerClickListener onMarkerClickListener) {
        if (this.x.contains(onMarkerClickListener)) {
            this.x.remove(onMarkerClickListener);
        }
    }

    public final void setBaiduHeatMapEnabled(boolean z) {
        com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
        if (eVar != null) {
            eVar.h(z);
        }
    }

    public final void setBuildingsEnabled(boolean z) {
        com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
        if (eVar != null) {
            eVar.j(z);
        }
    }

    public void setCompassEnable(boolean z) {
        this.i.e(z);
    }

    public void setCompassIcon(Bitmap bitmap) {
        if (bitmap == null) {
            throw new IllegalArgumentException("BDMapSDKException: compass's icon can not be null");
        }
        this.i.a(bitmap);
    }

    public void setCompassPosition(Point point) {
        if (this.i.a(point)) {
            this.T = point;
        }
    }

    public boolean setCustomTrafficColor(String str, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
            if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str3) || !TextUtils.isEmpty(str4)) {
                return true;
            }
            this.i.a(Color.parseColor("#ffffffff"), Color.parseColor("#ffffffff"), Color.parseColor("#ffffffff"), Color.parseColor("#ffffffff"), false);
            return true;
        }
        if (str.matches("^#[0-9a-fA-F]{8}$") && str2.matches("^#[0-9a-fA-F]{8}$") && str3.matches("^#[0-9a-fA-F]{8}$") && str4.matches("^#[0-9a-fA-F]{8}$")) {
            this.i.a(Color.parseColor(str), Color.parseColor(str2), Color.parseColor(str3), Color.parseColor(str4), true);
            return true;
        }
        Log.e(f2771e, "the string of the input customTrafficColor is error");
        return false;
    }

    public final void setIndoorEnable(boolean z) {
        com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
        if (eVar != null) {
            this.R = z;
            eVar.l(z);
        }
        OnBaseIndoorMapListener onBaseIndoorMapListener = this.D;
        if (onBaseIndoorMapListener == null || z) {
            return;
        }
        onBaseIndoorMapListener.onBaseIndoorMapMode(false, null);
    }

    public final void setMapStatus(MapStatusUpdate mapStatusUpdate) {
        if (mapStatusUpdate == null) {
            return;
        }
        ab abVarA = a(mapStatusUpdate);
        com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
        if (eVar == null) {
            return;
        }
        eVar.a(abVarA);
        OnMapStatusChangeListener onMapStatusChangeListener = this.q;
        if (onMapStatusChangeListener != null) {
            onMapStatusChangeListener.onMapStatusChange(getMapStatus());
        }
    }

    public final void setMapStatusLimits(LatLngBounds latLngBounds) {
        com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
        if (eVar == null) {
            return;
        }
        eVar.a(latLngBounds);
        setMapStatus(MapStatusUpdateFactory.newLatLngBounds(latLngBounds));
    }

    public final void setMapType(int i) {
        com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
        if (eVar == null) {
            return;
        }
        if (i == 1) {
            eVar.a(false);
            this.i.u(this.P);
            this.i.v(this.Q);
            this.i.g(true);
            this.i.l(this.R);
        } else if (i == 2) {
            eVar.a(true);
            this.i.u(this.P);
            this.i.v(this.Q);
            this.i.g(true);
        } else if (i == 3) {
            if (eVar.C()) {
                this.i.u(false);
            }
            if (this.i.D()) {
                this.i.v(false);
            }
            this.i.g(false);
            this.i.l(false);
        }
        com.baidu.mapsdkplatform.comapi.map.j jVar = this.f2778h;
        if (jVar != null) {
            jVar.a(i);
        }
    }

    public final void setMaxAndMinZoomLevel(float f2, float f3) {
        com.baidu.mapsdkplatform.comapi.map.e eVar;
        if (f2 <= 21.0f && f3 >= 4.0f && f2 >= f3 && (eVar = this.i) != null) {
            eVar.a(f2, f3);
        }
    }

    public final void setMyLocationConfigeration(MyLocationConfiguration myLocationConfiguration) {
        setMyLocationConfiguration(myLocationConfiguration);
    }

    public final void setMyLocationConfiguration(MyLocationConfiguration myLocationConfiguration) {
        this.O = myLocationConfiguration;
        a(this.N, this.O);
    }

    public final void setMyLocationData(MyLocationData myLocationData) {
        this.N = myLocationData;
        if (this.O == null) {
            this.O = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, false, null);
        }
        a(myLocationData, this.O);
    }

    public final void setMyLocationEnabled(boolean z) {
        com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
        if (eVar != null) {
            eVar.n(z);
        }
    }

    public final void setOnBaseIndoorMapListener(OnBaseIndoorMapListener onBaseIndoorMapListener) {
        this.D = onBaseIndoorMapListener;
    }

    public final void setOnMapClickListener(OnMapClickListener onMapClickListener) {
        this.s = onMapClickListener;
    }

    public final void setOnMapDoubleClickListener(OnMapDoubleClickListener onMapDoubleClickListener) {
        this.v = onMapDoubleClickListener;
    }

    public final void setOnMapDrawFrameCallback(OnMapDrawFrameCallback onMapDrawFrameCallback) {
        this.C = onMapDrawFrameCallback;
    }

    public void setOnMapLoadedCallback(OnMapLoadedCallback onMapLoadedCallback) {
        this.t = onMapLoadedCallback;
    }

    public final void setOnMapLongClickListener(OnMapLongClickListener onMapLongClickListener) {
        this.w = onMapLongClickListener;
    }

    public void setOnMapRenderCallbadk(OnMapRenderCallback onMapRenderCallback) {
        this.u = onMapRenderCallback;
    }

    public final void setOnMapRenderValidDataListener(OnMapRenderValidDataListener onMapRenderValidDataListener) {
        this.E = onMapRenderValidDataListener;
    }

    public final void setOnMapStatusChangeListener(OnMapStatusChangeListener onMapStatusChangeListener) {
        this.q = onMapStatusChangeListener;
    }

    public final void setOnMapTouchListener(OnMapTouchListener onMapTouchListener) {
        this.r = onMapTouchListener;
    }

    public final void setOnMarkerClickListener(OnMarkerClickListener onMarkerClickListener) {
        if (onMarkerClickListener == null || this.x.contains(onMarkerClickListener)) {
            return;
        }
        this.x.add(onMarkerClickListener);
    }

    public final void setOnMarkerDragListener(OnMarkerDragListener onMarkerDragListener) {
        this.z = onMarkerDragListener;
    }

    public final void setOnMyLocationClickListener(OnMyLocationClickListener onMyLocationClickListener) {
        this.A = onMyLocationClickListener;
    }

    public final void setOnPolylineClickListener(OnPolylineClickListener onPolylineClickListener) {
        if (onPolylineClickListener != null) {
            this.y.add(onPolylineClickListener);
        }
    }

    public final void setOnSynchronizationListener(OnSynchronizationListener onSynchronizationListener) {
        this.F = onSynchronizationListener;
    }

    public void setOverlayUnderPoi(boolean z) {
        this.i.c(z);
    }

    @Deprecated
    public final void setPadding(int i, int i2, int i3, int i4) {
        com.baidu.mapsdkplatform.comapi.map.e eVar;
        MapView mapView;
        if (i < 0 || i2 < 0 || i3 < 0 || i4 < 0 || (eVar = this.i) == null) {
            return;
        }
        eVar.E();
        int i5 = f.f3039b[this.f2775d.ordinal()];
        if (i5 != 1) {
            if (i5 == 2 && (mapView = this.f2772a) != null) {
                MapStatusUpdate mapStatusUpdateNewMapStatus = MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().targetScreen(new Point(((this.f2772a.getWidth() + i) - i3) / 2, ((this.f2772a.getHeight() + i2) - i4) / 2)).build());
                this.i.a(new Point((int) (i + (this.T.x * (((mapView.getWidth() - i) - i3) / this.f2772a.getWidth()))), (int) (i2 + (this.T.y * (((this.f2772a.getHeight() - i2) - i4) / this.f2772a.getHeight())))));
                setMapStatus(mapStatusUpdateNewMapStatus);
                this.f2772a.setPadding(i, i2, i3, i4);
                this.f2772a.invalidate();
                return;
            }
            return;
        }
        if (this.f2773b == null) {
            return;
        }
        MapStatusUpdate mapStatusUpdateNewMapStatus2 = MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().targetScreen(new Point(((this.f2773b.getWidth() + i) - i3) / 2, ((this.f2773b.getHeight() + i2) - i4) / 2)).build());
        this.i.a(new Point((int) (i + (this.T.x * (((r0.getWidth() - i) - i3) / this.f2773b.getWidth()))), (int) (i2 + (this.T.y * (((this.f2773b.getHeight() - i2) - i4) / this.f2773b.getHeight())))));
        setMapStatus(mapStatusUpdateNewMapStatus2);
        this.f2773b.setPadding(i, i2, i3, i4);
        this.f2773b.invalidate();
    }

    public void setPixelFormatTransparent(boolean z) {
        com.baidu.mapsdkplatform.comapi.map.j jVar = this.f2778h;
        if (jVar == null) {
            return;
        }
        if (z) {
            jVar.d();
        } else {
            jVar.e();
        }
    }

    public final void setTrafficEnabled(boolean z) {
        com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
        if (eVar != null) {
            eVar.i(z);
        }
    }

    public final void setViewPadding(int i, int i2, int i3, int i4) {
        MapView mapView;
        if (i < 0 || i2 < 0 || i3 < 0 || i4 < 0 || this.i == null) {
            return;
        }
        int i5 = f.f3039b[this.f2775d.ordinal()];
        if (i5 != 1) {
            if (i5 == 2 && (mapView = this.f2772a) != null) {
                this.i.a(new Point((int) (i + (this.T.x * (((mapView.getWidth() - i) - i3) / this.f2772a.getWidth()))), (int) (i2 + (this.T.y * (((this.f2772a.getHeight() - i2) - i4) / this.f2772a.getHeight())))));
                this.f2772a.setPadding(i, i2, i3, i4);
                this.f2772a.invalidate();
                return;
            }
            return;
        }
        if (this.f2773b == null) {
            return;
        }
        this.i.a(new Point((int) (i + (this.T.x * (((r0.getWidth() - i) - i3) / this.f2773b.getWidth()))), (int) (i2 + (this.T.y * (((this.f2773b.getHeight() - i2) - i4) / this.f2773b.getHeight())))));
        this.f2773b.setPadding(i, i2, i3, i4);
        this.f2773b.invalidate();
    }

    public void showInfoWindow(InfoWindow infoWindow) {
        showInfoWindow(infoWindow, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x006a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void showInfoWindow(com.baidu.mapapi.map.InfoWindow r7, boolean r8) {
        /*
            Method dump skipped, instruction units count: 218
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapapi.map.BaiduMap.showInfoWindow(com.baidu.mapapi.map.InfoWindow, boolean):void");
    }

    public void showInfoWindows(List<InfoWindow> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Iterator<InfoWindow> it = list.iterator();
        while (it.hasNext()) {
            showInfoWindow(it.next(), false);
        }
    }

    public final void showMapIndoorPoi(boolean z) {
        com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
        if (eVar != null) {
            eVar.v(z);
            this.Q = z;
        }
    }

    public final void showMapPoi(boolean z) {
        com.baidu.mapsdkplatform.comapi.map.e eVar = this.i;
        if (eVar != null) {
            eVar.u(z);
            this.P = z;
        }
    }

    public void showSDKLayer() {
        this.i.d();
    }

    public final void snapshot(SnapshotReadyCallback snapshotReadyCallback) {
        com.baidu.mapsdkplatform.comapi.map.j jVar;
        this.B = snapshotReadyCallback;
        int i = f.f3039b[this.f2775d.ordinal()];
        if (i != 1) {
            if (i == 2 && (jVar = this.f2778h) != null) {
                jVar.a("anything", (Rect) null);
                return;
            }
            return;
        }
        ac acVar = this.j;
        if (acVar != null) {
            acVar.a("anything", null);
        }
    }

    public final void snapshotScope(Rect rect, SnapshotReadyCallback snapshotReadyCallback) {
        com.baidu.mapsdkplatform.comapi.map.j jVar;
        this.B = snapshotReadyCallback;
        int i = f.f3039b[this.f2775d.ordinal()];
        if (i != 1) {
            if (i == 2 && (jVar = this.f2778h) != null) {
                jVar.a("anything", rect);
                return;
            }
            return;
        }
        ac acVar = this.j;
        if (acVar != null) {
            acVar.a("anything", rect);
        }
    }

    public MapBaseIndoorMapInfo.SwitchFloorError switchBaseIndoorMapFloor(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return MapBaseIndoorMapInfo.SwitchFloorError.FLOOR_INFO_ERROR;
        }
        MapBaseIndoorMapInfo focusedBaseIndoorMapInfo = getFocusedBaseIndoorMapInfo();
        if (focusedBaseIndoorMapInfo == null) {
            return MapBaseIndoorMapInfo.SwitchFloorError.SWITCH_ERROR;
        }
        if (!str2.equals(focusedBaseIndoorMapInfo.f2858a)) {
            return MapBaseIndoorMapInfo.SwitchFloorError.FOCUSED_ID_ERROR;
        }
        ArrayList<String> floors = focusedBaseIndoorMapInfo.getFloors();
        return (floors == null || !floors.contains(str)) ? MapBaseIndoorMapInfo.SwitchFloorError.FLOOR_OVERLFLOW : this.i.a(str, str2) ? MapBaseIndoorMapInfo.SwitchFloorError.SWITCH_OK : MapBaseIndoorMapInfo.SwitchFloorError.SWITCH_ERROR;
    }
}