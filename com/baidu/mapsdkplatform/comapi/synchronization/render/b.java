package com.baidu.mapsdkplatform.comapi.synchronization.render;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import com.amap.api.maps.utils.SpatialRelationUtil;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.WinRound;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.synchronization.DisplayOptions;
import com.baidu.mapapi.synchronization.RoleOptions;
import com.baidu.mapapi.synchronization.SynchronizationConstants;
import com.baidu.mapsdkplatform.comapi.synchronization.data.RouteLineInfo;
import com.baidu.mapsdkplatform.comapi.synchronization.data.SyncResponseResult;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class b extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3771a = b.class.getSimpleName();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static RoleOptions f3772d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static DisplayOptions f3773e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static Marker f3774f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static volatile SyncResponseResult f3775g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static int f3776h = 1000;
    private static volatile int p = 0;
    private static LatLng r = null;
    private boolean A;
    private Thread B;
    private boolean C;
    private int D;
    private int E;
    private LatLngBounds F;
    private e G;
    private volatile long H;
    private boolean I;
    private volatile boolean J;
    private volatile long K;
    private volatile int L;
    private List<LatLng> M;
    private List<BitmapDescriptor> N;
    private Polyline O;
    private List<Integer> P;
    private volatile boolean Q;
    private int R;
    private String S;
    private String T;
    private boolean U;
    private boolean V;
    private volatile int W;
    private volatile boolean X;
    private double Y;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private BaiduMap f3777b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private com.baidu.mapsdkplatform.comapi.synchronization.render.d f3778c;
    private Marker i;
    private Marker j;
    private Marker k;
    private Marker l;
    private Marker m;
    private Marker n;
    private List<LinkPointPolyLineInfo> o;
    private Thread q;
    private int s;
    private double t;
    private int u;
    private boolean v;
    private volatile boolean w;
    private Thread x;
    private boolean y;
    private boolean z;

    private class a implements Runnable {
        private a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            while (!b.this.C) {
                if (b.f3775g != null && b.f3775g.a() != null) {
                    if (!b.this.X) {
                        b.this.ak();
                        b.this.I = false;
                        b bVar = b.this;
                        bVar.a(bVar.F);
                    }
                    b.this.X = false;
                    try {
                        b.this.K = System.currentTimeMillis();
                        if (b.this.H <= 0) {
                            b.this.H = DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT;
                        }
                        Thread.sleep(b.this.H);
                    } catch (InterruptedException unused) {
                        com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(b.f3771a, "Sleep InterruptedException");
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.baidu.mapsdkplatform.comapi.synchronization.render.b$b, reason: collision with other inner class name */
    private class RunnableC0034b implements Runnable {
        private RunnableC0034b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            LatLng latLng;
            LatLng endPosition;
            while (!b.this.Q) {
                LatLng latLngAc = b.this.ac();
                if (latLngAc == null) {
                    com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(b.f3771a, "Driver position is null, return");
                    return;
                }
                LatLng latLngA = b.this.a(latLngAc);
                if (latLngA == null) {
                    com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(b.f3771a, "Driver position not bind to route");
                    b.c(b.this);
                    if (!b.this.v || b.this.y) {
                        com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(b.f3771a, "Driver position not bind to route times = " + b.this.W);
                        if (2 <= b.this.W) {
                            if (2 >= b.f3776h) {
                                latLng = b.r;
                                endPosition = b.f3772d.getStartPosition();
                            } else if (4 != b.f3776h) {
                                b.this.W = 0;
                                return;
                            } else {
                                latLng = b.r;
                                endPosition = b.f3772d.getEndPosition();
                            }
                            double dA = com.baidu.mapsdkplatform.comapi.synchronization.d.b.a(latLng, endPosition);
                            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(b.f3771a, "Latest driver postion to end position distance = " + dA);
                            if (300.0d >= dA) {
                                if (b.this.O != null) {
                                    b.this.O.remove();
                                }
                                b.this.Q = true;
                                b.this.af();
                            }
                            b.this.W = 0;
                        }
                        b.this.b(latLngAc);
                        b.this.ag();
                        b.this.c(latLngAc);
                        return;
                    }
                    return;
                }
                if (b.this.s == 0) {
                    return;
                }
                b bVar = b.this;
                bVar.t = bVar.ad();
                if (b.this.t > 500.0d) {
                    b.this.b(latLngA);
                    b.this.ag();
                    b.this.c(latLngA);
                    b.this.e(b.p - 1);
                    b.this.L = b.p - 1;
                    return;
                }
                if (b.f3775g != null && b.f3775g.c() != null) {
                    b.f3775g.c().setPoint(null);
                }
                try {
                    b.this.ae();
                } catch (Exception e2) {
                    com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(b.f3771a, "Catch exception when car moving", e2);
                }
                if (b.p >= b.this.o.size()) {
                    b.this.Q = true;
                    b.this.af();
                }
            }
        }
    }

    private class c implements Runnable {
        private c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            BitmapDescriptor passengerIcon = b.f3773e != null ? b.f3773e.getPassengerIcon() : null;
            if (passengerIcon == null) {
                passengerIcon = new DisplayOptions().getPassengerIcon();
            }
            int passengerMarkerZIndex = b.f3773e != null ? b.f3773e.getPassengerMarkerZIndex() : 10;
            while (!b.this.w) {
                MyLocationData locationData = b.this.f3777b.getLocationData();
                if (locationData != null && b.this.a(locationData)) {
                    com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(b.f3771a, "Get location data success");
                    LatLng latLng = new LatLng(locationData.latitude, locationData.longitude);
                    if (b.this.n == null) {
                        MarkerOptions markerOptionsZIndex = new MarkerOptions().position(latLng).anchor(0.5f, 0.5f).rotate(locationData.direction).icon(passengerIcon).zIndex(passengerMarkerZIndex);
                        b bVar = b.this;
                        bVar.n = (Marker) bVar.f3777b.addOverlay(markerOptionsZIndex);
                    } else {
                        b.this.n.setPosition(latLng);
                        b.this.n.setRotate(locationData.direction);
                    }
                }
                try {
                    Thread.sleep(b.this.R * 1000);
                } catch (InterruptedException unused) {
                    com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(b.f3771a, "Sleep interrupt");
                }
            }
        }
    }

    private enum d {
        NO_NEED_RENDER,
        RENDER_NEW_LINE,
        UPDATE_TRAFFIC
    }

    private class e implements BaiduMap.OnSynchronizationListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f3787b = 1;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f3788c = 2;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f3789d = 3;

        e() {
        }

        @Override // com.baidu.mapapi.map.BaiduMap.OnSynchronizationListener
        public void onMapStatusChangeReason(int i) {
            if (this.f3787b == i || this.f3788c == i) {
                long jCurrentTimeMillis = System.currentTimeMillis() - b.this.K;
                if (jCurrentTimeMillis <= 0) {
                    b.this.H = r7.D * 1000;
                } else {
                    b bVar = b.this;
                    bVar.H = ((long) (bVar.D * 1000)) - (b.this.H - jCurrentTimeMillis);
                }
                b.this.J = true;
                return;
            }
            if (this.f3789d == i) {
                b.this.H = r7.E * 1000;
                return;
            }
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(b.f3771a, "Undefined reason type: " + i);
        }
    }

    b(Looper looper) {
        super(looper);
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = new CopyOnWriteArrayList();
        this.s = 0;
        this.t = 0.0d;
        this.u = 5;
        this.v = false;
        this.w = false;
        this.y = false;
        this.z = true;
        this.A = false;
        this.C = true;
        this.D = 10;
        this.E = 10;
        this.F = null;
        this.H = DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT;
        this.I = true;
        this.J = false;
        this.K = 0L;
        this.L = 0;
        this.M = new CopyOnWriteArrayList();
        this.N = new CopyOnWriteArrayList();
        this.O = null;
        this.P = new CopyOnWriteArrayList();
        this.Q = true;
        this.R = 5;
        this.S = null;
        this.T = null;
        this.U = true;
        this.V = false;
        this.W = 0;
        this.X = false;
        this.Y = 0.0d;
        this.q = new Thread(new RunnableC0034b(), "Car moving");
        this.x = new Thread(new c(), "Passenger marker");
        this.B = new Thread(new a(), "Adjust visible span");
    }

    private void A() {
        Marker marker = this.k;
        if (marker != null) {
            marker.remove();
            this.k = null;
        }
        Marker marker2 = this.l;
        if (marker2 != null) {
            marker2.remove();
            this.l = null;
        }
    }

    private void B() {
        Marker marker = this.i;
        if (marker != null) {
            marker.remove();
            this.i = null;
        }
        Marker marker2 = this.j;
        if (marker2 != null) {
            marker2.remove();
            this.j = null;
        }
    }

    private void C() {
        Marker marker = this.n;
        if (marker != null) {
            marker.remove();
            this.n = null;
        }
    }

    private void D() {
        Marker marker = this.m;
        if (marker != null) {
            marker.remove();
            this.m = null;
        }
        Marker marker2 = f3774f;
        if (marker2 != null) {
            marker2.remove();
            f3774f = null;
        }
    }

    private void E() {
        if (!this.v || this.A) {
            return;
        }
        Polyline polyline = this.O;
        if (polyline != null) {
            polyline.remove();
            this.o.clear();
            this.M.clear();
            this.N.clear();
            this.P.clear();
            f3775g = null;
        }
        this.S = null;
        this.T = null;
    }

    private void F() {
        Marker marker = this.j;
        if (marker != null) {
            marker.remove();
            this.j = null;
        }
    }

    private void G() {
        Marker marker = f3774f;
        if (marker != null) {
            marker.remove();
            f3774f = null;
        }
    }

    private void H() {
        if (!this.v || this.y) {
            return;
        }
        this.Q = true;
    }

    private void I() {
        DisplayOptions displayOptions = f3773e;
        if (displayOptions == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3771a, "renderStartPositionMarker DisplayOptions is null");
            return;
        }
        if (!displayOptions.isShowStartPositionMarker()) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3771a, "User set start position marker not show");
            Marker marker = this.i;
            if (marker != null) {
                marker.remove();
                this.i = null;
                return;
            }
            return;
        }
        if (this.i != null && !this.v) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3771a, "Start position marker already render ok");
            return;
        }
        LatLng latLngJ = J();
        if (latLngJ == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "No startPosition");
            return;
        }
        BitmapDescriptor startPositionIcon = f3773e.getStartPositionIcon();
        if (startPositionIcon == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "No startPositionIcon, use default");
            startPositionIcon = new DisplayOptions().getStartPositionIcon();
        }
        if (startPositionIcon == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "There is no startPositionIcon");
            return;
        }
        MarkerOptions markerOptionsPerspective = new MarkerOptions().position(latLngJ).icon(startPositionIcon).zIndex(f3773e.getStartPositionMarkerZIndex()).perspective(false);
        Marker marker2 = this.i;
        if (marker2 == null) {
            this.i = (Marker) this.f3777b.addOverlay(markerOptionsPerspective);
        } else {
            marker2.setIcon(startPositionIcon);
            this.i.setPosition(latLngJ);
        }
    }

    private LatLng J() {
        LatLng startPosition = f3772d.getStartPosition();
        if (startPosition == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "The start position is null");
            com.baidu.mapsdkplatform.comapi.synchronization.render.d dVar = this.f3778c;
            if (dVar != null) {
                dVar.a(100001, "Start position is null");
            }
        }
        return startPosition;
    }

    private void K() {
        DisplayOptions displayOptions = f3773e;
        if (displayOptions == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3771a, "renderStartPositionInfoWindow DisplayOptions is null");
            return;
        }
        if (!displayOptions.isShowStartPositionInfoWindow()) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3771a, "User set start position infoWindow not show");
            Marker marker = this.j;
            if (marker != null) {
                marker.remove();
                this.j = null;
                return;
            }
            return;
        }
        LatLng startPosition = f3772d.getStartPosition();
        if (startPosition == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "No startPosition");
            return;
        }
        View startPositionInfoWindowView = f3773e.getStartPositionInfoWindowView();
        if (startPositionInfoWindowView == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "Start position infoWindow view is null, cannot display");
            Marker marker2 = this.j;
            if (marker2 != null) {
                marker2.remove();
                this.j = null;
                return;
            }
            return;
        }
        MarkerOptions markerOptionsPerspective = new MarkerOptions().position(startPosition).icon(BitmapDescriptorFactory.fromView(startPositionInfoWindowView)).zIndex(f3773e.getStartPositionInfoWindowZIndex()).alpha(0.9f).perspective(false);
        Marker marker3 = this.j;
        if (marker3 == null) {
            this.j = (Marker) this.f3777b.addOverlay(markerOptionsPerspective);
        } else {
            marker3.setPosition(startPosition);
            this.j.setIcon(BitmapDescriptorFactory.fromView(startPositionInfoWindowView));
        }
    }

    private void L() {
        DisplayOptions displayOptions = f3773e;
        if (displayOptions == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3771a, "renderEndPositionMarker DisplayOptions is null");
            return;
        }
        if (!displayOptions.isShowEndPositionMarker()) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3771a, "User set endPositionMarker not show");
            Marker marker = this.k;
            if (marker != null) {
                marker.remove();
                this.k = null;
                return;
            }
            return;
        }
        if (this.k != null && !this.v) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "EndPositionMarker already render ok");
            return;
        }
        LatLng endPosition = f3772d.getEndPosition();
        if (endPosition == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "End position coord is null");
            return;
        }
        BitmapDescriptor endPositionIcon = f3773e.getEndPositionIcon();
        if (endPositionIcon == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "The end position icon is null");
            endPositionIcon = new DisplayOptions().getEndPositionIcon();
        }
        if (endPositionIcon == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "There is no endPositionIcon");
            return;
        }
        MarkerOptions markerOptionsPerspective = new MarkerOptions().position(endPosition).icon(endPositionIcon).zIndex(f3773e.getEndPositionMarkerZIndex()).perspective(false);
        Marker marker2 = this.k;
        if (marker2 == null) {
            this.k = (Marker) this.f3777b.addOverlay(markerOptionsPerspective);
        } else {
            marker2.setIcon(endPositionIcon);
            this.k.setPosition(endPosition);
        }
    }

    private void M() {
        DisplayOptions displayOptions = f3773e;
        if (displayOptions == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3771a, "renderEndPositionInfoWindow DisplayOptions is null");
            return;
        }
        if (!displayOptions.isShowEndPositionInfoWindow()) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3771a, "User set end position infoWindow not show");
            Marker marker = this.l;
            if (marker != null) {
                marker.remove();
                this.l = null;
                return;
            }
            return;
        }
        LatLng endPosition = f3772d.getEndPosition();
        if (endPosition == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "End position coord is null when render end position infoWindow");
            return;
        }
        View endPositionInfoWindowView = f3773e.getEndPositionInfoWindowView();
        if (endPositionInfoWindowView == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "End position infoWindow view is null, cannot display");
            Marker marker2 = this.l;
            if (marker2 != null) {
                marker2.remove();
                this.l = null;
                return;
            }
            return;
        }
        MarkerOptions markerOptionsPerspective = new MarkerOptions().position(endPosition).icon(BitmapDescriptorFactory.fromView(endPositionInfoWindowView)).anchor(0.5f, 1.0f).zIndex(f3773e.getEndPositionInfoWindowZIndex()).perspective(false);
        Marker marker3 = this.l;
        if (marker3 == null) {
            this.l = (Marker) this.f3777b.addOverlay(markerOptionsPerspective);
        } else {
            marker3.setPosition(endPosition);
            this.l.setIcon(BitmapDescriptorFactory.fromView(endPositionInfoWindowView));
        }
    }

    private synchronized void N() {
        if (this.x == null) {
            return;
        }
        if (f3773e != null && f3773e.isShowPassengerIcon()) {
            this.w = false;
            if (Thread.State.NEW == this.x.getState()) {
                this.x.start();
            }
            if (this.v && Thread.State.TERMINATED == this.x.getState()) {
                this.x = null;
                this.x = new Thread(new c(), "Passenger marker");
                this.x.start();
            }
        }
    }

    private void O() {
        DisplayOptions displayOptions = f3773e;
        if (displayOptions == null || displayOptions.isShowPassengerIcon()) {
            N();
            return;
        }
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3771a, "User set not show passenger icon");
        Marker marker = this.n;
        if (marker != null) {
            marker.remove();
            this.n = null;
        }
    }

    private synchronized void P() {
        this.w = true;
    }

    private void Q() {
        DisplayOptions displayOptions = f3773e;
        if (displayOptions == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3771a, "renderCarMarker DisplayOptions is null");
            return;
        }
        if (!displayOptions.isShowCarMarker()) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3771a, "User set carMarker not show");
            Marker marker = this.m;
            if (marker != null) {
                marker.remove();
                this.m = null;
                return;
            }
            return;
        }
        if (this.m != null && !this.v) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3771a, "CarIcon already render ok");
            return;
        }
        LatLng latLngT = T();
        if (latLngT == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "The car(driver) position is null");
            this.f3778c.a(2004, SynchronizationConstants.LBS_STATUS_MESSAGE_QUERY_TRACK_DRIVER_POSITION_FAILED);
            return;
        }
        BitmapDescriptor carIcon = (!f3773e.get3DCarMarkerEnable() || f3773e.get3DCarMarkerIconList() == null || f3773e.get3DCarMarkerIconList().isEmpty()) ? f3773e.getCarIcon() : f3773e.get3DCarMarkerIconList().get(0);
        if (carIcon == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "The car icon is null, use default 2D car icon");
            carIcon = new DisplayOptions().getCarIcon();
        }
        if (carIcon == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "There is no car icon");
            return;
        }
        MarkerOptions markerOptionsPerspective = new MarkerOptions().position(latLngT).icon(carIcon).flat(false).rotate(0.0f).zIndex(f3773e.getCarPositionMarkerZIndex()).anchor(0.5f, 0.5f).perspective(false);
        Marker marker2 = this.m;
        if (marker2 == null) {
            this.m = (Marker) this.f3777b.addOverlay(markerOptionsPerspective);
        } else {
            marker2.setPosition(latLngT);
            this.m.setIcon(carIcon);
        }
    }

    private void R() {
        DisplayOptions displayOptions = f3773e;
        if (displayOptions == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3771a, "renderCarInfoWindow DisplayOptions is null");
            return;
        }
        if (!displayOptions.isShowCarInfoWindow()) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3771a, "User set carInfoWindow not show");
            Marker marker = f3774f;
            if (marker != null) {
                marker.remove();
                f3774f = null;
                return;
            }
            return;
        }
        View carInfoWindowView = f3773e.getCarInfoWindowView();
        if (carInfoWindowView == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "car position infoWindow view is null, cannot display");
            Marker marker2 = f3774f;
            if (marker2 != null) {
                marker2.remove();
                f3774f = null;
                return;
            }
            return;
        }
        LatLng latLngS = S();
        if (latLngS == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "CarPosition is null");
            return;
        }
        MarkerOptions markerOptionsAlpha = new MarkerOptions().position(latLngS).icon(BitmapDescriptorFactory.fromView(carInfoWindowView)).zIndex(f3773e.getCarPositionInfoWindowZIndex()).anchor(0.5f, 1.0f).alpha(0.9f);
        Marker marker3 = f3774f;
        if (marker3 == null) {
            f3774f = (Marker) this.f3777b.addOverlay(markerOptionsAlpha);
        } else {
            marker3.setPosition(latLngS);
            f3774f.setIcon(BitmapDescriptorFactory.fromView(carInfoWindowView));
        }
    }

    private LatLng S() {
        if (this.m == null && f3775g != null) {
            return f3775g.c().getPoint();
        }
        Marker marker = this.m;
        if (marker != null) {
            return marker.getPosition();
        }
        return null;
    }

    private LatLng T() {
        LatLng latLng = r;
        return latLng != null ? latLng : (f3775g == null || f3775g.c() == null || f3775g.c().getPoint() == null) ? f3772d.getDriverPosition() : f3775g.c().getPoint();
    }

    private void U() {
        DisplayOptions displayOptions = f3773e;
        if (displayOptions == null) {
            return;
        }
        if (!displayOptions.isShowRoutePlan()) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3771a, "User set route line not show");
            Polyline polyline = this.O;
            if (polyline != null) {
                polyline.remove();
                this.O = null;
                return;
            }
            return;
        }
        if (f3775g == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "No route line data");
            return;
        }
        d dVarV = V();
        if (d.NO_NEED_RENDER == dVarV) {
            this.z = false;
            return;
        }
        if (d.UPDATE_TRAFFIC == dVarV) {
            this.z = false;
            W();
            return;
        }
        this.z = true;
        if (!this.Q) {
            this.Q = true;
            try {
                Thread.sleep(100L);
            } catch (Exception e2) {
                com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3771a, "Exception caught when renderRouteLine", e2);
            }
        }
        p = 0;
        this.s = 0;
        this.L = 0;
        this.o.clear();
        this.M.clear();
        this.N.clear();
        this.P.clear();
        Polyline polyline2 = this.O;
        if (polyline2 != null) {
            polyline2.remove();
            this.O = null;
        }
        X();
        List<LinkPointPolyLineInfo> list = this.o;
        if (list == null || list.isEmpty()) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "LinkPointPolyline info is null");
        } else {
            Y();
        }
    }

    private d V() {
        String strA = f3775g.a().a();
        String strA2 = f3775g.b().a();
        if (this.v && !this.A) {
            this.S = null;
            this.T = null;
        }
        if (strA != null && (TextUtils.isEmpty(this.S) || !this.S.equals(strA) || this.O == null)) {
            this.S = strA;
            this.T = strA2;
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "Route line or order state changed or no render, need render");
            return d.RENDER_NEW_LINE;
        }
        if (strA2 == null || this.O == null || (!TextUtils.isEmpty(this.T) && this.T.equals(strA2))) {
            return d.NO_NEED_RENDER;
        }
        this.T = strA2;
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "Route line only need update traffic");
        return d.UPDATE_TRAFFIC;
    }

    private void W() {
        ArrayList<Integer> arrayListB = f3775g.b().b();
        if (arrayListB == null || arrayListB.isEmpty()) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "Traffic status data is null");
            return;
        }
        if (!this.Q) {
            this.Q = true;
        }
        if (p - this.s < 0) {
            return;
        }
        try {
            if (arrayListB.size() == this.o.size()) {
                for (int i = p - this.s; i < this.o.size(); i++) {
                    this.P.set(i, arrayListB.get(i));
                }
            } else {
                for (int i2 = p - this.s; i2 < this.o.size(); i2++) {
                    this.P.set(i2, arrayListB.get((arrayListB.size() + i2) - this.o.size()));
                }
            }
            int[] iArr = new int[(this.P.size() - p) + this.s];
            for (int i3 = 0; i3 < (this.P.size() - p) + this.s; i3++) {
                iArr[i3] = this.P.get((p + i3) - this.s).intValue();
            }
            if (iArr.length <= 0) {
                return;
            } else {
                this.O.setIndexs(iArr);
            }
        } catch (Exception e2) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3771a, "Exception caught when updateTrafficStatus", e2);
        }
        if (this.Q) {
            this.Q = false;
        }
    }

    private void X() {
        if (f3775g == null || f3775g.a() == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "Route info or syncResponseResult is null");
            return;
        }
        List<RouteLineInfo.RouteSectionInfo> listB = f3775g.a().b();
        ArrayList<Integer> arrayListB = f3775g.b().b();
        if (listB == null || listB.isEmpty()) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "route section info is null");
            this.f3778c.a(2001, SynchronizationConstants.LBS_STATUS_MESSAGE_ROUTE_PLAN_FAILED);
            return;
        }
        if (!listB.isEmpty() && arrayListB != null && !arrayListB.isEmpty() && arrayListB.size() != listB.size()) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "route section info or traffic status info is invalid");
            return;
        }
        for (int i = 0; i < listB.size(); i++) {
            if (listB.get(i) != null) {
                LatLng latLngA = listB.get(i).a();
                LatLng latLngB = listB.get(i).b();
                int iA = a(i, arrayListB);
                LinkPointPolyLineInfo linkPointPolyLineInfo = new LinkPointPolyLineInfo();
                linkPointPolyLineInfo.a(latLngA);
                linkPointPolyLineInfo.b(latLngB);
                linkPointPolyLineInfo.a(iA);
                this.o.add(linkPointPolyLineInfo);
                this.M.add(latLngA);
            }
        }
        this.M.add(listB.get(listB.size() - 1).b());
    }

    private void Y() {
        if (!this.Q) {
            this.Q = true;
        }
        LatLng point = (f3775g == null || f3775g.c() == null) ? null : f3775g.c().getPoint();
        LatLng latLngA = point != null ? a(point) : null;
        if (latLngA != null) {
            this.s = 0;
            try {
                this.o = this.o.subList(p, this.o.size());
                this.M = this.M.subList(p, this.M.size());
            } catch (Exception e2) {
                com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3771a, "Caught exception when renderRoutePolyLine", e2);
            }
            b(latLngA);
            ag();
            c(latLngA);
            this.L = p;
        }
        p = 0;
        int size = this.M.size();
        if (size < 3) {
            return;
        }
        for (int i = 0; i < size - 1; i++) {
            if (this.M.get(i) != null) {
                this.P.add(Integer.valueOf(this.o.get(i).c()));
            }
        }
        if (f3773e == null) {
            f3773e = new DisplayOptions();
        }
        if (this.N.isEmpty()) {
            this.N.addAll(f3773e.getTrafficTextureList());
        }
        DisplayOptions displayOptions = f3773e;
        if (displayOptions == null) {
            displayOptions = new DisplayOptions();
        }
        PolylineOptions polylineOptionsZIndex = new PolylineOptions().points(this.M).dottedLine(true).width(displayOptions.getRouteLineWidth()).customTextureList(this.N).textureIndex(this.P).zIndex(f3773e.getRouteLineZIndex());
        if (!f3773e.isShowRoutePlan()) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "User set route line not display");
            polylineOptionsZIndex.visible(false);
        }
        this.O = (Polyline) this.f3777b.addOverlay(polylineOptionsZIndex);
        if (this.Q) {
            this.Q = false;
        }
    }

    private void Z() {
        List<LinkPointPolyLineInfo> list;
        if (this.m == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "CarMarker is null");
            return;
        }
        if (r != null || (list = this.o) == null || list.isEmpty()) {
            aa();
            return;
        }
        r = this.o.get(0).a();
        b(r);
        c(r);
    }

    private double a(double d2) {
        if (this.u == 0) {
            this.u = 5;
        }
        return (Math.abs(d2) * ((double) this.u)) / this.t;
    }

    private double a(double d2, double d3) {
        return d3 == Double.MAX_VALUE ? d2 : Math.abs((d2 * d3) / Math.sqrt((d3 * d3) + 1.0d));
    }

    private double a(double d2, LatLng latLng) {
        return latLng.latitude - (d2 * latLng.longitude);
    }

    private int a(int i, ArrayList<Integer> arrayList) {
        if (arrayList == null || arrayList.isEmpty() || i >= arrayList.size()) {
            return 0;
        }
        return arrayList.get(i).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public LatLng a(LatLng latLng) {
        if (3 == f3776h) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3771a, "WAIT_PASSENGER State, no need calculate");
            return null;
        }
        List<LinkPointPolyLineInfo> list = this.o;
        if (list == null || list.isEmpty()) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "mLinkPolyLineInfos size = " + this.o.size());
            return null;
        }
        for (int i = p; i < this.o.size(); i++) {
            LatLng latLngA = this.o.get(i).a();
            LatLng latLngB = this.o.get(i).b();
            if (!a(latLng, latLngA)) {
                if (a(latLng, latLngB)) {
                    this.s = d(i + 1);
                    return latLngB;
                }
                boolean zA = a(latLngA, latLngB, latLng);
                boolean zB = b(latLngA, latLngB, latLng);
                if (!zA || !zB) {
                }
            }
            this.s = d(i);
            return latLngA;
        }
        return null;
    }

    private void a(float f2, LatLng latLng, LatLng latLng2) {
        DisplayOptions displayOptions;
        Marker marker;
        DisplayOptions displayOptions2 = f3773e;
        if (displayOptions2 == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "User not set DisplayOptions, use default 2D marker");
            displayOptions = new DisplayOptions();
        } else {
            List<BitmapDescriptor> list = displayOptions2.get3DCarMarkerIconList();
            if (list != null && !list.isEmpty()) {
                double dAtan2 = (Math.atan2(latLng2.latitude - latLng.latitude, latLng2.longitude - latLng.longitude) / 3.141592653589793d) * 180.0d;
                if (dAtan2 < 0.0d) {
                    dAtan2 += 360.0d;
                }
                if (dAtan2 == this.Y) {
                    return;
                }
                this.Y = dAtan2;
                int size = list.size();
                int i = SpatialRelationUtil.A_CIRCLE_DEGREE / size;
                int i2 = (int) (dAtan2 / ((double) i));
                if (size == i2) {
                    i2 = 0;
                }
                BitmapDescriptor bitmapDescriptor = list.get(i2);
                if (bitmapDescriptor == null || (marker = this.m) == null) {
                    return;
                }
                marker.setIcon(bitmapDescriptor);
                this.m.setRotate(((float) (dAtan2 - ((double) (i2 * i)))) - 1.0f);
                return;
            }
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "User not set 3D car marker list, use default 2D marker");
            displayOptions = new DisplayOptions();
        }
        this.m.setIcon(displayOptions.getCarIcon());
        this.m.setRotate(f2);
    }

    private void a(LatLngBounds.Builder builder) {
        DisplayOptions displayOptions = f3773e;
        if (displayOptions == null || displayOptions.isShowStartPositionMarkerInSpan()) {
            builder.include(J());
        } else {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "User set not show startPositionMarker in span");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(LatLngBounds latLngBounds) {
        int paddingTop;
        int paddingRight;
        int paddingBottom;
        if (this.J && !this.X) {
            this.J = false;
            return;
        }
        MapStatus mapStatus = this.f3777b.getMapStatus();
        if (mapStatus == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "Get map status failed");
            return;
        }
        WinRound winRound = mapStatus.winRound;
        int iAbs = Math.abs(winRound.right - winRound.left);
        int iAbs2 = Math.abs(winRound.bottom - winRound.top);
        int paddingLeft = 50;
        if (f3773e != null) {
            paddingLeft = f3773e.getPaddingLeft();
            paddingTop = f3773e.getPaddingTop();
            paddingRight = f3773e.getPaddingRight();
            paddingBottom = f3773e.getPaddingBottom();
        } else {
            paddingTop = 50;
            paddingRight = 50;
            paddingBottom = 50;
        }
        int i = (iAbs - paddingLeft) - paddingRight;
        int i2 = (iAbs2 - paddingTop) - paddingBottom;
        if (i < 0 || i2 < 0 || i > iAbs || i2 > iAbs2) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "Invalid padding，use default padding");
        }
        this.f3777b.animateMapStatus(MapStatusUpdateFactory.newLatLngBounds(latLngBounds, paddingLeft, paddingTop, paddingRight, paddingBottom));
    }

    private synchronized void a(LatLngBounds latLngBounds, int i, int i2, int i3, int i4) {
        if (f3773e == null) {
            f3773e = new DisplayOptions();
        }
        f3773e.setMapViewPadding(i, i2, i3, i4);
        this.f3777b.animateMapStatus(MapStatusUpdateFactory.newLatLngBounds(latLngBounds, i, i2, i3, i4));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(MyLocationData myLocationData) {
        long jLongValue;
        long jLongValue2;
        try {
            jLongValue = Double.valueOf(myLocationData.latitude).longValue();
            try {
                jLongValue2 = Double.valueOf(myLocationData.longitude).longValue();
            } catch (NumberFormatException unused) {
                com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "Trans latitude and longitude failed");
                jLongValue2 = 0;
            }
        } catch (NumberFormatException unused2) {
            jLongValue = 0;
        }
        return !(0 == jLongValue && 0 == jLongValue2) && jLongValue >= -90 && jLongValue <= 90 && jLongValue2 >= -180 && jLongValue2 <= 180;
    }

    private boolean a(LatLng latLng, LatLng latLng2) {
        return (latLng == null || latLng2 == null) ? latLng == null && latLng2 == null : Math.abs(latLng.latitude - latLng2.latitude) < 1.0E-4d && Math.abs(latLng.longitude - latLng2.longitude) < 1.0E-4d;
    }

    private boolean a(LatLng latLng, LatLng latLng2, double d2) {
        double d3;
        LatLng latLng3;
        boolean z = latLng.latitude > latLng2.latitude;
        double dA = a(d2, latLng);
        double dF = f(latLng, latLng2);
        double dA2 = z ? a(dF, d2) : a(dF, d2) * (-1.0d);
        double dA3 = a(dA2);
        double d4 = latLng.latitude;
        while (true) {
            if ((d4 > latLng2.latitude) != z) {
                return true;
            }
            if (this.Q) {
                return false;
            }
            if (Double.MAX_VALUE == d2) {
                d3 = dA2;
                latLng3 = new LatLng(d4, latLng.longitude);
            } else {
                d3 = dA2;
                latLng3 = new LatLng(d4, (d4 - dA) / d2);
            }
            b(latLng3);
            c(latLng3);
            if (!b(dA3)) {
                return false;
            }
            d4 -= d3;
            dA2 = d3;
        }
    }

    private boolean a(LatLng latLng, LatLng latLng2, LatLng latLng3) {
        double d2 = latLng.latitude;
        double d3 = latLng.longitude;
        double d4 = latLng2.latitude;
        return Math.abs(((latLng3.latitude - d2) * (latLng2.longitude - d3)) - ((d4 - d2) * (latLng3.longitude - d3))) < 1.0E-4d;
    }

    private synchronized void aa() {
        if (this.q == null) {
            return;
        }
        this.Q = false;
        if (Thread.State.NEW == this.q.getState()) {
            this.q.start();
            return;
        }
        if (Thread.State.TERMINATED == this.q.getState()) {
            this.q = null;
            this.q = new Thread(new RunnableC0034b(), "Car moving");
            this.q.start();
        }
    }

    private synchronized void ab() {
        this.Q = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public LatLng ac() {
        if (f3775g == null || f3775g.c() == null || f3775g.c().getPoint() == null || this.V) {
            return null;
        }
        return f3775g.c().getPoint();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public double ad() {
        List<LinkPointPolyLineInfo> list = this.o;
        if (list == null || list.isEmpty() || p > this.o.size()) {
            return 1.0d;
        }
        double dF = 0.0d;
        for (int i = p - this.s; i < p; i++) {
            dF += f(this.o.get(i).a(), this.o.get(i).b());
        }
        return dF;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ae() {
        if (p == 0) {
            return;
        }
        for (int i = p - this.s; i < p; i++) {
            LatLng latLngA = this.o.get(i).a();
            LatLng latLngB = this.o.get(i).b();
            double dF = f(latLngA, latLngB) / 2.0d;
            double d2 = (latLngB.latitude - latLngA.latitude) / dF;
            double d3 = (latLngB.longitude - latLngA.longitude) / dF;
            double dE = e(latLngA, latLngB);
            LatLng latLng = latLngA;
            int i2 = 1;
            while (i2 <= dF) {
                double d4 = latLng.longitude;
                double d5 = dF;
                double d6 = latLng.latitude;
                if (0.0d == dE) {
                    d4 = latLng.longitude + d3;
                } else {
                    if (Double.MAX_VALUE != dE) {
                        d4 = latLng.longitude + d3;
                    }
                    d6 = latLng.latitude + d2;
                }
                LatLng latLng2 = new LatLng(d6, d4);
                List<LatLng> list = this.M;
                if (list != null && !list.isEmpty()) {
                    if (this.z) {
                        this.Q = true;
                        return;
                    }
                    this.M.set(i, latLng2);
                }
                List<Integer> list2 = this.P;
                if (list2 != null && !list2.isEmpty()) {
                    this.P.set(i, Integer.valueOf(this.o.get(i).c()));
                }
                if (!b(latLng, latLng2)) {
                    return;
                }
                this.L = i;
                e(i);
                i2++;
                latLng = latLng2;
                dF = d5;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void af() {
        p = 0;
        this.s = 0;
        this.o.clear();
        this.M.clear();
        this.P.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ag() {
        if (this.m == null) {
            return;
        }
        float fFloatValue = 0.0f;
        try {
            fFloatValue = Double.valueOf(360.0d - f3775g.c().getAngle()).floatValue();
        } catch (NumberFormatException e2) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3771a, "Get DriverPosition Angle failed", e2);
        }
        this.m.setRotate(fFloatValue);
    }

    private synchronized void ah() {
        this.C = true;
    }

    private synchronized void ai() {
        if (this.B == null) {
            return;
        }
        if (this.C) {
            this.C = false;
        }
        if (Thread.State.NEW == this.B.getState()) {
            this.I = true;
            this.B.start();
        }
        if (this.v && Thread.State.TIMED_WAITING == this.B.getState()) {
            this.B.interrupt();
            this.I = true;
        }
        if (Thread.State.TERMINATED == this.B.getState()) {
            this.B = null;
            this.I = true;
            this.B = new Thread(new a(), "Adjust visible span");
            this.B.start();
        }
    }

    private void aj() {
        this.f3777b.setOnSynchronizationListener(this.G);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ak() {
        int i = f3776h;
        if (i != 0) {
            if (i == 1) {
                al();
                return;
            }
            if (i == 2) {
                am();
                return;
            }
            if (i == 3) {
                an();
                return;
            } else if (i == 4) {
                ao();
                return;
            } else if (i != 5) {
                return;
            }
        }
        ah();
    }

    private void al() {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        a(builder);
        d(builder);
        c(builder);
        e(builder);
        this.F = h(builder);
    }

    private void am() {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        a(builder);
        c(builder);
        d(builder);
        e(builder);
        this.F = h(builder);
    }

    private void an() {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        a(builder);
        c(builder);
        d(builder);
        this.F = h(builder);
    }

    private void ao() {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        b(builder);
        c(builder);
        e(builder);
        this.F = h(builder);
    }

    private double b(LatLng latLng, LatLng latLng2, double d2) {
        if (Double.MAX_VALUE == d2) {
            return latLng2.latitude > latLng.latitude ? 360.0d : 180.0d;
        }
        if (0.0d == d2) {
            return latLng2.longitude > latLng.longitude ? 270.0d : 90.0d;
        }
        return (((Math.atan(d2) / 3.141592653589793d) * 180.0d) + ((latLng2.latitude - latLng.latitude) * d2 < 0.0d ? 180.0d : 0.0d)) - 90.0d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(LatLng latLng) {
        Marker marker = this.m;
        if (marker != null) {
            marker.setPosition(latLng);
        }
    }

    private void b(LatLngBounds.Builder builder) {
        DisplayOptions displayOptions = f3773e;
        if (displayOptions == null || displayOptions.isShowEndPositionMarkerInSpan()) {
            builder.include(f3772d.getEndPosition());
        } else {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "User set not show endPositionMarker in span");
        }
    }

    private boolean b(double d2) {
        try {
            Thread.sleep(Double.valueOf((d2 * 1000.0d) + 50.0d).longValue());
            return true;
        } catch (InterruptedException unused) {
            return false;
        } catch (NumberFormatException e2) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3771a, "Calc sleep interval failed", e2);
            return false;
        }
    }

    private boolean b(LatLng latLng, LatLng latLng2) {
        if (this.Q) {
            return false;
        }
        b(latLng);
        c(latLng);
        double dE = e(latLng, latLng2);
        float fB = (float) b(latLng, latLng2, dE);
        if (this.m != null) {
            DisplayOptions displayOptions = f3773e;
            if (displayOptions == null || !displayOptions.get3DCarMarkerEnable()) {
                this.m.setRotate(fB);
            } else {
                a(fB, latLng, latLng2);
            }
        }
        boolean zC = 0.0d == dE ? c(latLng, latLng2) : a(latLng, latLng2, dE);
        if (zC) {
            r = latLng2;
        }
        return zC;
    }

    private boolean b(LatLng latLng, LatLng latLng2, LatLng latLng3) {
        double d2 = latLng.latitude;
        double d3 = latLng.longitude;
        double d4 = latLng2.latitude;
        double d5 = latLng2.longitude;
        double d6 = latLng3.latitude;
        double d7 = latLng3.longitude;
        return Math.min(d2, d4) - 1.0E-4d <= d6 && d6 <= Math.max(d2, d4) + 1.0E-4d && Math.min(d3, d5) - 1.0E-4d <= d7 && d7 <= Math.max(d3, d5) + 1.0E-4d;
    }

    static /* synthetic */ int c(b bVar) {
        int i = bVar.W;
        bVar.W = i + 1;
        return i;
    }

    private void c(int i) {
        this.y = 1000 == f3776h;
        this.A = (1 == f3776h && 2 == i) || (1 == i && 2 == f3776h);
        if (f3776h == i) {
            this.v = false;
        } else {
            f3776h = i;
            this.v = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(LatLng latLng) {
        Marker marker = f3774f;
        if (marker != null) {
            marker.setPosition(latLng);
        }
    }

    private void c(LatLngBounds.Builder builder) {
        DisplayOptions displayOptions = f3773e;
        if (displayOptions != null && !displayOptions.isShowCarMarkerInSpan()) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "User set not show carMarker in span");
        } else {
            Marker marker = this.m;
            builder.include((marker == null || this.I) ? T() : marker.getPosition());
        }
    }

    private boolean c(LatLng latLng, LatLng latLng2) {
        double d2 = d(latLng, latLng2);
        double dA = a(d2);
        for (double d3 = latLng.longitude; d3 <= latLng2.longitude; d3 += d2) {
            if (this.Q) {
                return false;
            }
            LatLng latLng3 = new LatLng(latLng.latitude, d3);
            b(latLng3);
            c(latLng3);
            if (!b(dA)) {
                return false;
            }
        }
        return true;
    }

    private double d(LatLng latLng, LatLng latLng2) {
        return com.baidu.mapsdkplatform.comapi.synchronization.d.b.a(latLng, latLng2);
    }

    private synchronized int d(int i) {
        int i2;
        i2 = i - p;
        p = i;
        return i2;
    }

    private void d(LatLngBounds.Builder builder) {
        LatLng position;
        String str;
        String str2;
        DisplayOptions displayOptions = f3773e;
        if (displayOptions == null || displayOptions.isShowPassengerIconInSpan()) {
            Marker marker = this.n;
            if (marker == null) {
                MyLocationData locationData = this.f3777b.getLocationData();
                if (locationData == null) {
                    str = f3771a;
                    str2 = "No passenger location data";
                } else if (!a(locationData)) {
                    return;
                } else {
                    position = new LatLng(locationData.latitude, locationData.longitude);
                }
            } else {
                position = marker.getPosition();
            }
            builder.include(position);
            return;
        }
        str = f3771a;
        str2 = "User set not show passengerMarker in span";
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(str, str2);
    }

    private double e(LatLng latLng, LatLng latLng2) {
        if (latLng2.longitude == latLng.longitude) {
            return Double.MAX_VALUE;
        }
        return (latLng2.latitude - latLng.latitude) / (latLng2.longitude - latLng.longitude);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        int[] iArr;
        List<LatLng> list = this.M;
        if (list == null || list.isEmpty()) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "Route polyline points is null when remove");
            return;
        }
        if (this.M.size() <= 2 || i >= this.M.size() - 2) {
            Polyline polyline = this.O;
            if (polyline != null) {
                polyline.remove();
                return;
            }
            return;
        }
        List<Integer> list2 = this.P;
        if (list2 == null || list2.isEmpty()) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "No need removeTravelledPolyLine");
            return;
        }
        if (this.z) {
            return;
        }
        try {
            List<Integer> listSubList = this.P.subList(i, this.P.size());
            iArr = new int[listSubList.size()];
            for (int i2 = 0; i2 < listSubList.size(); i2++) {
                iArr[i2] = listSubList.get(i2).intValue();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (this.O != null && !this.Q) {
            this.O.setIndexs(iArr);
            try {
                this.O.setPoints(this.M.subList(i, this.M.size()));
            } catch (Exception e3) {
                com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3771a, "Get subList of PolyLinePointList failed", e3);
            }
        }
    }

    private void e(LatLngBounds.Builder builder) {
        DisplayOptions displayOptions = f3773e;
        if (displayOptions != null && !displayOptions.isShowRoutePlanInSpan()) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "User set not show routeLine in span");
            return;
        }
        List<LinkPointPolyLineInfo> list = this.o;
        if (list == null || list.isEmpty()) {
            g(builder);
        } else {
            f(builder);
        }
    }

    private double f(LatLng latLng, LatLng latLng2) {
        return com.baidu.mapsdkplatform.comapi.synchronization.d.b.a(latLng, latLng2);
    }

    private void f(LatLngBounds.Builder builder) {
        for (int i = this.L; i < this.o.size(); i++) {
            builder.include(this.o.get(i).a());
        }
        builder.include(this.o.get(r0.size() - 1).b());
    }

    private void g(LatLngBounds.Builder builder) {
        if (f3775g == null || f3775g.a() == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "There no routeLine info, no need show in span");
            return;
        }
        List<RouteLineInfo.RouteSectionInfo> listB = f3775g.a().b();
        if (listB == null || listB.isEmpty()) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "There no routeLine position, no need show in span");
            return;
        }
        for (int i = 0; i < listB.size(); i++) {
            builder.include(listB.get(i).a());
        }
        builder.include(listB.get(listB.size() - 1).b());
    }

    private LatLngBounds h(LatLngBounds.Builder builder) {
        return builder.build();
    }

    private void o() {
        if (f3773e == null) {
            return;
        }
        Marker marker = f3774f;
        if (marker != null) {
            marker.remove();
            f3774f = null;
        }
        Marker marker2 = this.m;
        if (marker2 != null) {
            marker2.remove();
            this.m = null;
        }
        Marker marker3 = this.i;
        if (marker3 != null) {
            marker3.remove();
            this.i = null;
        }
        Marker marker4 = this.j;
        if (marker4 != null) {
            marker4.remove();
            this.j = null;
        }
        Marker marker5 = this.l;
        if (marker5 != null) {
            marker5.remove();
            this.l = null;
        }
        f3773e.getStartPositionIcon().recycle();
        f3773e.getCarIcon().recycle();
        if (f3773e.getEndPositionIcon() != null) {
            f3773e.getEndPositionIcon().recycle();
        }
        f3773e = null;
    }

    private void p() {
        p = 0;
        this.u = 0;
        this.Q = true;
        Thread thread = this.q;
        if (thread != null) {
            try {
                thread.join();
            } catch (InterruptedException unused) {
                com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "InterruptedException when release CarMoveThread");
            }
            this.q = null;
        }
    }

    private void q() {
        this.w = true;
        if (this.x != null) {
            this.x = null;
        }
    }

    private void r() {
        this.C = true;
        if (this.B != null) {
            this.B = null;
        }
    }

    private void s() {
        f3772d = null;
        f3775g = null;
        f3776h = 1000;
        this.Q = false;
        this.U = true;
        this.V = false;
        this.o.clear();
        p = 0;
        r = null;
        this.s = 0;
        this.t = 0.0d;
        this.M.clear();
        this.P.clear();
        Polyline polyline = this.O;
        if (polyline != null) {
            polyline.remove();
            this.O = null;
        }
        for (int i = 0; i < this.N.size(); i++) {
            this.N.get(i).recycle();
        }
        this.N.clear();
    }

    private void t() {
        P();
        H();
        ah();
        E();
        A();
        B();
        C();
        D();
        BaiduMap baiduMap = this.f3777b;
        if (baiduMap != null) {
            baiduMap.clear();
        }
    }

    private void u() {
        if (z()) {
            E();
            H();
            ai();
            I();
            K();
            L();
            M();
            O();
            Q();
            R();
            U();
            Z();
        }
    }

    private void v() {
        if (z()) {
            E();
            H();
            ai();
            I();
            K();
            L();
            M();
            O();
            Q();
            R();
            U();
            Z();
        }
    }

    private void w() {
        if (z()) {
            E();
            G();
            F();
            H();
            ai();
            I();
            K();
            L();
            M();
            O();
            Q();
            R();
            Z();
        }
    }

    private void x() {
        if (z()) {
            P();
            E();
            B();
            C();
            H();
            ai();
            I();
            K();
            L();
            M();
            Q();
            R();
            U();
            Z();
        }
    }

    private void y() {
        P();
        H();
        ah();
        A();
        B();
        C();
        D();
    }

    private boolean z() {
        if (f3772d == null || f3773e == null) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "No render data");
            com.baidu.mapsdkplatform.comapi.synchronization.render.d dVar = this.f3778c;
            if (dVar != null) {
                dVar.a(100001, "Get render data failed");
            }
            return false;
        }
        if (this.f3777b != null) {
            return true;
        }
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3771a, "BaiduMap is null");
        com.baidu.mapsdkplatform.comapi.synchronization.render.d dVar2 = this.f3778c;
        if (dVar2 != null) {
            dVar2.a(100002, "BaiduMap instance is null.");
        }
        return false;
    }

    Marker a() {
        return this.i;
    }

    void a(int i) {
        this.D = i;
    }

    void a(int i, int i2, int i3, int i4) {
        this.X = true;
        ak();
        a(this.F, i, i2, i3, i4);
    }

    public void a(BaiduMap baiduMap, RoleOptions roleOptions, DisplayOptions displayOptions) {
        this.f3777b = baiduMap;
        f3772d = roleOptions;
        f3773e = displayOptions;
        this.G = new e();
        aj();
        r = null;
        this.f3777b.getUiSettings().setRotateGesturesEnabled(false);
        this.f3777b.getUiSettings().setCompassEnabled(false);
    }

    synchronized void a(RoleOptions roleOptions, DisplayOptions displayOptions, SyncResponseResult syncResponseResult, int i) {
        f3772d = roleOptions;
        f3773e = displayOptions;
        if (f3773e == null) {
            f3773e = new DisplayOptions();
        }
        f3775g = syncResponseResult;
        this.u = i;
    }

    void a(com.baidu.mapsdkplatform.comapi.synchronization.render.d dVar) {
        this.f3778c = dVar;
    }

    Marker b() {
        return this.k;
    }

    void b(int i) {
        this.E = i;
    }

    Marker c() {
        return this.m;
    }

    public void d() {
        this.V = false;
        if (this.U) {
            this.U = false;
            return;
        }
        N();
        ai();
        aa();
    }

    public void e() {
        this.V = true;
        ab();
        P();
        ah();
    }

    public void f() {
        p();
        q();
        r();
        o();
        s();
    }

    void g() {
        this.X = true;
        ak();
        a(this.F);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.c(f3771a, "The orderState in message is: " + message.what);
        c(message.what);
        int i = message.what;
        if (i == 0) {
            t();
            return;
        }
        if (i == 1) {
            u();
            return;
        }
        if (i == 2) {
            v();
            return;
        }
        if (i == 3) {
            w();
            return;
        }
        if (i == 4) {
            x();
            return;
        }
        if (i == 5) {
            y();
            return;
        }
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.c(f3771a, "Undefined Message type: " + message.what);
    }
}