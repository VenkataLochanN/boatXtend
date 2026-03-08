package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.opengl.EGL14;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import com.amap.api.mapcore.util.b;
import com.amap.api.mapcore.util.di;
import com.amap.api.mapcore.util.fh;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CustomRenderer;
import com.amap.api.maps.InfoWindowAnimationManager;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.Projection;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.interfaces.IGlOverlayLayer;
import com.amap.api.maps.model.AMapCameraInfo;
import com.amap.api.maps.model.AMapGestureListener;
import com.amap.api.maps.model.Arc;
import com.amap.api.maps.model.ArcOptions;
import com.amap.api.maps.model.BaseOptions;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BuildingOverlay;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.CrossOverlay;
import com.amap.api.maps.model.CrossOverlayOptions;
import com.amap.api.maps.model.CustomMapStyleOptions;
import com.amap.api.maps.model.GL3DModel;
import com.amap.api.maps.model.GL3DModelOptions;
import com.amap.api.maps.model.GroundOverlay;
import com.amap.api.maps.model.GroundOverlayOptions;
import com.amap.api.maps.model.HeatMapLayer;
import com.amap.api.maps.model.HeatMapLayerOptions;
import com.amap.api.maps.model.HeatmapTileProvider;
import com.amap.api.maps.model.IndoorBuildingInfo;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MultiPointOverlay;
import com.amap.api.maps.model.MultiPointOverlayOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.MyTrafficStyle;
import com.amap.api.maps.model.NavigateArrow;
import com.amap.api.maps.model.NavigateArrowOptions;
import com.amap.api.maps.model.Poi;
import com.amap.api.maps.model.Polygon;
import com.amap.api.maps.model.PolygonOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.model.RouteOverlay;
import com.amap.api.maps.model.Text;
import com.amap.api.maps.model.TextOptions;
import com.amap.api.maps.model.TileOverlay;
import com.amap.api.maps.model.TileOverlayOptions;
import com.amap.api.maps.model.TileProvider;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.particle.ParticleOverlay;
import com.amap.api.maps.model.particle.ParticleOverlayOptions;
import com.autonavi.amap.mapcore.AbstractCameraUpdateMessage;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.VirtualEarthProjection;
import com.autonavi.amap.mapcore.animation.GLAlphaAnimation;
import com.autonavi.amap.mapcore.interfaces.IMarkerAction;
import com.autonavi.amap.mapcore.interfaces.IMultiPointOverlay;
import com.autonavi.base.ae.gmap.GLMapEngine;
import com.autonavi.base.ae.gmap.GLMapRender;
import com.autonavi.base.ae.gmap.GLMapState;
import com.autonavi.base.ae.gmap.gesture.EAMapPlatformGestureInfo;
import com.autonavi.base.ae.gmap.glinterface.MapLabelItem;
import com.autonavi.base.ae.gmap.gloverlay.BaseMapOverlay;
import com.autonavi.base.ae.gmap.gloverlay.CrossVectorOverlay;
import com.autonavi.base.ae.gmap.gloverlay.GLOverlayBundle;
import com.autonavi.base.ae.gmap.gloverlay.GLTextureProperty;
import com.autonavi.base.ae.gmap.listener.AMapWidgetListener;
import com.autonavi.base.ae.gmap.style.StyleItem;
import com.autonavi.base.amap.api.mapcore.BaseOverlayImp;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.api.mapcore.IGLSurfaceView;
import com.autonavi.base.amap.api.mapcore.IProjectionDelegate;
import com.autonavi.base.amap.api.mapcore.IUiSettingsDelegate;
import com.autonavi.base.amap.api.mapcore.overlays.IMarkerDelegate;
import com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate;
import com.autonavi.base.amap.mapcore.AMapNativeRenderer;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.autonavi.base.amap.mapcore.FPoint;
import com.autonavi.base.amap.mapcore.MapConfig;
import com.autonavi.base.amap.mapcore.Rectangle;
import com.autonavi.base.amap.mapcore.interfaces.IAMapListener;
import com.autonavi.base.amap.mapcore.message.AbstractGestureMapMessage;
import com.autonavi.base.amap.mapcore.tools.GLConvertUtil;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: compiled from: AMapDelegateImp.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements b.a, di.a, IAMapDelegate, IAMapListener {
    private IProjectionDelegate A;
    private final ac B;
    private boolean C;
    private final IGLSurfaceView D;
    private fj E;
    private ab F;
    private Object G;
    private final IGlOverlayLayer H;
    private boolean I;
    private int J;
    private boolean K;
    private o L;
    private boolean M;
    private boolean N;
    private boolean O;
    private cv P;
    private LocationSource Q;
    private boolean R;
    private Marker S;
    private BaseOverlayImp T;
    private boolean U;
    private boolean V;
    private boolean W;
    private boolean X;
    private boolean Y;
    private boolean Z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected boolean f317a;
    private volatile boolean aA;
    private volatile boolean aB;
    private boolean aC;
    private boolean aD;
    private Lock aE;
    private int aF;
    private int aG;
    private int aH;
    private b aI;
    private de aJ;
    private t aK;
    private ax aL;
    private com.amap.api.mapcore.util.b aM;
    private long aN;
    private a aO;
    private a aP;
    private a aQ;
    private a aR;
    private a aS;
    private a aT;
    private a aU;
    private a aV;
    private a aW;
    private a aX;
    private Runnable aY;
    private a aZ;
    private Rect aa;
    private int ab;
    private MyTrafficStyle ac;
    private Thread ad;
    private Thread ae;
    private boolean af;
    private boolean ag;
    private boolean ah;
    private int ai;
    private CustomRenderer aj;
    private final w ak;
    private int al;
    private int am;
    private List<x> an;
    private dg ao;
    private di ap;
    private long aq;
    private GLMapRender ar;
    private p as;
    private boolean at;
    private float au;
    private float av;
    private float aw;
    private boolean ax;
    private boolean ay;
    private boolean az;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final v f318b;
    private a ba;
    private jx bb;
    private String bc;
    private String bd;
    private boolean be;
    private boolean bf;
    private EAMapPlatformGestureInfo bg;
    private long bh;
    private aq bi;
    private IPoint[] bj;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected MapConfig f319c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected aq f320d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    ei f321e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected Context f322f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected GLMapEngine f323g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f324h;
    public int i;
    protected final Handler j;
    Point k;
    Rect l;
    protected String m;
    float[] n;
    float[] o;
    float[] p;
    float[] q;
    String r;
    String s;
    int t;
    private g u;
    private h v;
    private AMapGestureListener w;
    private ar x;
    private da y;
    private UiSettings z;

    private void j(int i) {
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public RouteOverlay addNaviRouteOverlay() {
        return null;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public int checkMarkerInRect(IMarkerAction iMarkerAction, Rect rect) {
        return 0;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public AMapCameraInfo getCamerInfo() {
        return null;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public boolean isLockMapCameraDegree(int i) {
        return false;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void reloadMap() {
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setZOrderOnTop(boolean z) throws RemoteException {
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void setMapWidgetListener(AMapWidgetListener aMapWidgetListener) {
        try {
            if (this.v != null) {
                this.v.a(AMapWidgetListener.class.hashCode(), aMapWidgetListener);
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(CameraPosition cameraPosition) {
        if (this.f319c.getMapLanguage().equals("en")) {
            boolean zB = b(cameraPosition);
            if (zB != this.Z) {
                this.Z = zB;
                b(1, this.Z);
                return;
            }
            return;
        }
        if (this.Z) {
            return;
        }
        this.Z = true;
        b(1, this.Z);
    }

    private boolean b(CameraPosition cameraPosition) {
        if (cameraPosition.zoom < 6.0f) {
            return false;
        }
        if (cameraPosition.isAbroad) {
            return true;
        }
        if (this.f319c == null) {
            return false;
        }
        try {
            return !ek.a(r3.getGeoRectangle().getClipRect());
        } catch (Throwable th) {
            th.printStackTrace();
            er.a(th);
            return false;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setVisibilityEx(int i) {
        IGLSurfaceView iGLSurfaceView = this.D;
        if (iGLSurfaceView != null) {
            try {
                iGLSurfaceView.setVisibility(i);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void onActivityPause() {
        this.M = true;
        c(this.J);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void onActivityResume() {
        this.M = false;
        int engineIDWithType = this.J;
        if (engineIDWithType == 0) {
            engineIDWithType = this.f323g.getEngineIDWithType(0);
        }
        d(engineIDWithType);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void queueEvent(Runnable runnable) {
        long id;
        try {
            try {
                id = Thread.currentThread().getId();
            } catch (Throwable th) {
                er.a(th);
                hn.c(th, "AMapdelegateImp", "queueEvent");
                id = -1;
            }
            if (id != -1 && id == this.aq) {
                runnable.run();
            } else if (this.f323g != null) {
                this.D.queueEvent(runnable);
            }
        } catch (Throwable th2) {
            er.a(th2);
            hn.c(th2, "AMapdelegateImp", "queueEvent");
        }
    }

    /* JADX INFO: compiled from: AMapDelegateImp.java */
    private static abstract class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f390b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f391c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f392d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f393e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f394f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        int f395g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        int f396h;
        int i;

        private a() {
            this.f390b = false;
            this.f391c = false;
            this.f396h = 0;
            this.i = 0;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f390b = false;
        }
    }

    public c(IGLSurfaceView iGLSurfaceView, Context context, AttributeSet attributeSet) {
        this(iGLSurfaceView, context, attributeSet, false);
    }

    public c(IGLSurfaceView iGLSurfaceView, Context context, AttributeSet attributeSet, boolean z) {
        this.u = null;
        this.v = new h();
        this.y = null;
        this.f317a = false;
        this.C = false;
        this.F = null;
        this.G = new Object();
        this.I = false;
        this.K = false;
        this.M = false;
        this.f319c = new MapConfig(true);
        this.N = false;
        this.O = false;
        this.R = false;
        this.S = null;
        this.T = null;
        this.U = false;
        this.V = false;
        this.W = false;
        this.X = false;
        this.Y = false;
        this.Z = true;
        this.aa = new Rect();
        this.ab = 1;
        this.ac = null;
        this.af = false;
        this.ag = false;
        this.ah = false;
        this.ai = 0;
        this.al = -1;
        this.am = -1;
        this.an = new ArrayList();
        this.f321e = null;
        this.aq = -1L;
        this.at = false;
        this.au = 0.0f;
        this.av = 1.0f;
        this.aw = 1.0f;
        this.ax = true;
        this.ay = false;
        this.az = false;
        this.aA = false;
        this.aB = false;
        this.aC = false;
        this.aD = false;
        this.aE = new ReentrantLock();
        this.aF = 0;
        this.j = new Handler(Looper.getMainLooper()) { // from class: com.amap.api.mapcore.util.c.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i;
                if (message == null || c.this.K) {
                    return;
                }
                try {
                    i = message.what;
                } catch (Throwable th) {
                    hn.c(th, "AMapDelegateImp", "handleMessage");
                    th.printStackTrace();
                }
                if (i == 2) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Key验证失败：[");
                    if (message.obj != null) {
                        sb.append(message.obj);
                    } else {
                        sb.append(gj.f1057b);
                    }
                    sb.append("]");
                    Log.w("amapsdk", sb.toString());
                    return;
                }
                boolean z2 = true;
                int i2 = 0;
                switch (i) {
                    case 10:
                        CameraPosition cameraPosition = (CameraPosition) message.obj;
                        try {
                            List listA = c.this.v.a(AMap.OnCameraChangeListener.class.hashCode());
                            if (cameraPosition != null && listA != null && listA.size() > 0) {
                                synchronized (listA) {
                                    Iterator it = listA.iterator();
                                    while (it.hasNext()) {
                                        ((AMap.OnCameraChangeListener) it.next()).onCameraChange(cameraPosition);
                                    }
                                }
                            }
                            break;
                        } catch (Throwable th2) {
                            er.a(th2);
                        }
                        c.this.f319c.addChangedCounter();
                        return;
                    case 11:
                        try {
                            CameraPosition cameraPosition2 = c.this.getCameraPosition();
                            if (cameraPosition2 != null && c.this.E != null) {
                                c.this.E.a(cameraPosition2);
                            }
                            c.this.a(cameraPosition2);
                            if (c.this.az) {
                                c.this.az = false;
                                if (c.this.F != null) {
                                    c.this.F.b(false);
                                }
                                c.this.a(true);
                            }
                            if (c.this.X) {
                                c.this.redrawInfoWindow();
                                c.this.X = false;
                            }
                            c.this.a(true, cameraPosition2);
                            return;
                        } catch (Throwable th3) {
                            hn.c(th3, "AMapDelegateImp", "CameraUpdateFinish");
                            er.a(th3);
                            return;
                        }
                    case 12:
                        if (c.this.E != null) {
                            c.this.E.a(Float.valueOf(c.this.getZoomLevel()));
                            return;
                        }
                        return;
                    case 13:
                        if (c.this.E != null) {
                            c.this.E.k();
                            return;
                        }
                        return;
                    case 14:
                        try {
                            List listA2 = c.this.v.a(AMap.OnMapTouchListener.class.hashCode());
                            if (listA2 == null || listA2.size() <= 0) {
                                return;
                            }
                            synchronized (listA2) {
                                Iterator it2 = listA2.iterator();
                                while (it2.hasNext()) {
                                    ((AMap.OnMapTouchListener) it2.next()).onTouch((MotionEvent) message.obj);
                                }
                                break;
                            }
                            return;
                        } catch (Throwable th4) {
                            hn.c(th4, "AMapDelegateImp", "onTouchHandler");
                            th4.printStackTrace();
                            return;
                        }
                    case 15:
                        Bitmap bitmap = (Bitmap) message.obj;
                        int i3 = message.arg1;
                        try {
                            if (bitmap == null || c.this.E == null) {
                                List listA3 = c.this.v.a(AMap.onMapPrintScreenListener.class.hashCode());
                                if (listA3 != null && listA3.size() > 0) {
                                    synchronized (listA3) {
                                        for (int i4 = 0; i4 < listA3.size(); i4++) {
                                            ((AMap.onMapPrintScreenListener) listA3.get(i4)).onMapPrint(null);
                                        }
                                    }
                                }
                                List listA4 = c.this.v.a(AMap.OnMapScreenShotListener.class.hashCode());
                                if (listA4 != null && listA4.size() > 0) {
                                    synchronized (listA4) {
                                        while (i2 < listA4.size()) {
                                            ((AMap.OnMapScreenShotListener) listA4.get(i2)).onMapScreenShot(null);
                                            ((AMap.OnMapScreenShotListener) listA4.get(i2)).onMapScreenShot(null, i3);
                                            i2++;
                                        }
                                    }
                                }
                            } else {
                                Canvas canvas = new Canvas(bitmap);
                                fm fmVarG = c.this.E.g();
                                if (fmVarG != null) {
                                    fmVarG.onDraw(canvas);
                                }
                                c.this.E.a(canvas);
                                List listA5 = c.this.v.a(AMap.onMapPrintScreenListener.class.hashCode());
                                if (listA5 != null && listA5.size() > 0) {
                                    synchronized (listA5) {
                                        for (int i5 = 0; i5 < listA5.size(); i5++) {
                                            ((AMap.onMapPrintScreenListener) listA5.get(i5)).onMapPrint(new BitmapDrawable(c.this.f322f.getResources(), bitmap));
                                        }
                                    }
                                }
                                List listA6 = c.this.v.a(AMap.OnMapScreenShotListener.class.hashCode());
                                if (listA6 != null && listA6.size() > 0) {
                                    synchronized (listA6) {
                                        while (i2 < listA6.size()) {
                                            ((AMap.OnMapScreenShotListener) listA6.get(i2)).onMapScreenShot(bitmap);
                                            ((AMap.OnMapScreenShotListener) listA6.get(i2)).onMapScreenShot(bitmap, i3);
                                            i2++;
                                        }
                                    }
                                }
                            }
                            break;
                        } catch (Throwable unused) {
                        }
                        if (c.this.v != null) {
                            c.this.v.a(Integer.valueOf(AMap.onMapPrintScreenListener.class.hashCode()));
                        }
                        if (c.this.v != null) {
                            c.this.v.a(Integer.valueOf(AMap.OnMapScreenShotListener.class.hashCode()));
                            return;
                        }
                        return;
                    case 16:
                        try {
                            List listA7 = c.this.v.a(AMap.OnMapLoadedListener.class.hashCode());
                            if (listA7 != null) {
                                synchronized (listA7) {
                                    while (i2 < listA7.size()) {
                                        ((AMap.OnMapLoadedListener) listA7.get(i2)).onMapLoaded();
                                        i2++;
                                    }
                                }
                            }
                            break;
                        } catch (Throwable th5) {
                            hn.c(th5, "AMapDelegateImp", "onMapLoaded");
                            th5.printStackTrace();
                            er.a(th5);
                        }
                        if (c.this.E != null) {
                            c.this.E.l();
                            return;
                        }
                        return;
                    case 17:
                        if (c.this.f323g.isInMapAnimation(1) && c.this.F != null) {
                            c.this.F.b(false);
                        }
                        if (c.this.F != null) {
                            ab abVar = c.this.F;
                            if (message.arg1 == 0) {
                                z2 = false;
                            }
                            abVar.a(z2);
                            return;
                        }
                        return;
                    case 18:
                        if (c.this.x == null || !c.this.O) {
                            return;
                        }
                        c.this.x.c();
                        return;
                    case 19:
                        List listA8 = c.this.v.a(AMap.OnMapClickListener.class.hashCode());
                        if (listA8 != null) {
                            DPoint dPointObtain = DPoint.obtain();
                            c.this.getPixel2LatLng(message.arg1, message.arg2, dPointObtain);
                            try {
                                synchronized (listA8) {
                                    Iterator it3 = listA8.iterator();
                                    while (it3.hasNext()) {
                                        ((AMap.OnMapClickListener) it3.next()).onMapClick(new LatLng(dPointObtain.y, dPointObtain.x));
                                    }
                                    break;
                                }
                                dPointObtain.recycle();
                                return;
                            } catch (Throwable th6) {
                                hn.c(th6, "AMapDelegateImp", "OnMapClickListener.onMapClick");
                                th6.printStackTrace();
                                return;
                            }
                        }
                        return;
                    case 20:
                        try {
                            List listA9 = c.this.v.a(AMap.OnPOIClickListener.class.hashCode());
                            if (listA9 == null || listA9.size() <= 0) {
                                return;
                            }
                            synchronized (listA9) {
                                while (i2 < listA9.size()) {
                                    ((AMap.OnPOIClickListener) listA9.get(i2)).onPOIClick((Poi) message.obj);
                                    i2++;
                                    break;
                                }
                                break;
                            }
                            return;
                        } catch (Throwable th7) {
                            hn.c(th7, "AMapDelegateImp", "OnPOIClickListener.onPOIClick");
                            th7.printStackTrace();
                            return;
                        }
                    default:
                        return;
                }
                hn.c(th, "AMapDelegateImp", "handleMessage");
                th.printStackTrace();
            }
        };
        this.aO = new a() { // from class: com.amap.api.mapcore.util.c.11
            @Override // com.amap.api.mapcore.util.c.a, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    c.this.setTrafficEnabled(this.f391c);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        };
        this.aP = new a() { // from class: com.amap.api.mapcore.util.c.21
            @Override // com.amap.api.mapcore.util.c.a, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    c.this.setCenterToPixel(c.this.aG, c.this.aH);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        };
        this.aQ = new a() { // from class: com.amap.api.mapcore.util.c.31
            @Override // com.amap.api.mapcore.util.c.a, java.lang.Runnable
            public void run() {
                super.run();
                c.this.b(this.f395g, this.f392d, this.f393e, this.f394f);
            }
        };
        this.aR = new a() { // from class: com.amap.api.mapcore.util.c.32
            @Override // com.amap.api.mapcore.util.c.a, java.lang.Runnable
            public void run() {
                super.run();
                c.this.setMapCustomEnable(this.f391c);
            }
        };
        this.aS = new a() { // from class: com.amap.api.mapcore.util.c.33
            @Override // com.amap.api.mapcore.util.c.a, java.lang.Runnable
            public void run() {
                super.run();
                c.this.a(this.f395g, this.f391c);
            }
        };
        this.aT = new a() { // from class: com.amap.api.mapcore.util.c.34
            @Override // com.amap.api.mapcore.util.c.a, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    c.this.setMapTextEnable(this.f391c);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        };
        this.aU = new a() { // from class: com.amap.api.mapcore.util.c.35
            @Override // com.amap.api.mapcore.util.c.a, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    c.this.setRoadArrowEnable(this.f391c);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        };
        this.aV = new a() { // from class: com.amap.api.mapcore.util.c.36
            @Override // com.amap.api.mapcore.util.c.a, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    c.this.setNaviLabelEnable(this.f391c, this.f396h, this.i);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        };
        this.aW = new a() { // from class: com.amap.api.mapcore.util.c.2
            @Override // com.amap.api.mapcore.util.c.a, java.lang.Runnable
            public void run() {
                super.run();
                c.this.b(this.f395g, this.f391c);
            }
        };
        this.aX = new a() { // from class: com.amap.api.mapcore.util.c.3
            @Override // com.amap.api.mapcore.util.c.a, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    c.this.setIndoorEnabled(this.f391c);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        };
        this.aY = new Runnable() { // from class: com.amap.api.mapcore.util.c.4
            @Override // java.lang.Runnable
            public void run() {
                fm fmVarG;
                if (c.this.E == null || (fmVarG = c.this.E.g()) == null) {
                    return;
                }
                fmVarG.d();
            }
        };
        this.aZ = new a() { // from class: com.amap.api.mapcore.util.c.5
            @Override // com.amap.api.mapcore.util.c.a, java.lang.Runnable
            public void run() {
                super.run();
                c.this.c(this.f395g, this.f391c);
            }
        };
        this.ba = new a() { // from class: com.amap.api.mapcore.util.c.6
            @Override // com.amap.api.mapcore.util.c.a, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    c.this.setMyTrafficStyle(c.this.ac);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        };
        this.bc = "";
        this.bd = "";
        this.be = false;
        this.bf = false;
        this.bg = new EAMapPlatformGestureInfo();
        this.k = new Point();
        this.l = new Rect();
        this.bh = 0L;
        this.m = null;
        this.bi = null;
        this.n = new float[16];
        this.o = new float[16];
        this.p = new float[16];
        this.bj = null;
        this.q = new float[12];
        this.r = "precision highp float;\nattribute vec3 aVertex;//顶点数组,三维坐标\nuniform mat4 aMVPMatrix;//mvp矩阵\nvoid main(){\n  gl_Position = aMVPMatrix * vec4(aVertex, 1.0);\n}";
        this.s = "//有颜色 没有纹理\nprecision highp float;\nvoid main(){\n  gl_FragColor = vec4(1.0,0,0,1.0);\n}";
        this.t = -1;
        this.f322f = context;
        ey.a(context);
        ey.a(ex.f796c, "init map delegate");
        this.bb = new jx();
        this.bb.a(ju.a());
        this.bb.a(this.f322f, this, z);
        hn.a(this.f322f);
        ea.a().a(this.f322f);
        m.f1684b = gi.c(context);
        dr.a(this.f322f);
        this.as = new p(this);
        this.f323g = new GLMapEngine(this.f322f, this);
        this.ar = new GLMapRender(this);
        this.D = iGLSurfaceView;
        iGLSurfaceView.setRenderer(this.ar);
        this.B = new ac(this);
        this.E = new fj(this.f322f, this);
        this.E.a(new C0007c());
        this.aI = new b();
        if (!(eh.a(context, "amap_param", "overlay_use_old_type", (Boolean) false).booleanValue() | (!MapsInitializer.getPolyline2Enable()))) {
            this.H = new r(this);
        } else {
            this.H = new s(this);
        }
        this.f318b = new v(this.f322f, this);
        this.L = new o(this.f322f, this);
        this.D.setRenderMode(0);
        this.ar.setRenderFps(15.0f);
        this.f323g.setMapListener(this);
        this.A = new z(this);
        this.u = new g(this);
        this.y = new da(this, context);
        this.x = new ar(this.f322f);
        this.x.a(this.E);
        this.x.b(this.y);
        this.ak = new w();
        this.ad = new k(this.f322f, this);
        this.Q = new as(this.f322f);
        this.aL = new ax(this);
        this.aK = new t();
        this.ao = new dg(this.f322f, this);
        this.ap = new di(this.f322f);
        this.ap.a(this);
        b(z);
        MapConfig mapConfig = this.f319c;
        this.aM = new com.amap.api.mapcore.util.b(this, this.f322f, mapConfig != null ? mapConfig.isAbroadEnable() : false);
        this.aM.a(this);
    }

    private void b(boolean z) {
        jx jxVar = this.bb;
        if (jxVar != null) {
            Object objA = jxVar.a("getAbroadEnable");
            if (objA != null && (objA instanceof Boolean)) {
                MapConfig mapConfig = this.f319c;
                if (mapConfig != null) {
                    mapConfig.setAbroadEnable(z && ((Boolean) objA).booleanValue());
                }
                if (z && ((Boolean) objA).booleanValue()) {
                    MapsInitializer.setSupportRecycleView(false);
                }
            }
            Object objA2 = this.bb.a("getLogoEnable");
            if (objA2 != null && (objA2 instanceof Boolean)) {
                this.E.a(((Boolean) objA2).booleanValue());
            }
            Object objA3 = this.bb.a("getMapZindex");
            if (objA2 == null || !(objA2 instanceof Integer)) {
                return;
            }
            this.ai = ((Integer) objA3).intValue();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public jx getAMapExtraInterfaceManager() {
        return this.bb;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void setMapEnable(boolean z) {
        MapConfig mapConfig = this.f319c;
        if (mapConfig != null) {
            mapConfig.setMapEnable(z);
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public GLMapEngine getGLMapEngine() {
        return this.f323g;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void setGestureStatus(int i, int i2) {
        if (this.aF == 0 || i2 != 5) {
            this.aF = i2;
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public float getPreciseLevel(int i) {
        MapConfig mapConfig = this.f319c;
        if (mapConfig != null) {
            return mapConfig.getSZ();
        }
        return 0.0f;
    }

    public float a(int i) {
        if (this.f319c != null) {
            return getMapConfig().getSZ();
        }
        return 0.0f;
    }

    public boolean a(int i, int i2, int i3) {
        AbstractCameraUpdateMessage abstractCameraUpdateMessageA;
        if (!this.aA || ((int) a(i)) >= this.f319c.getMaxZoomLevel()) {
            return false;
        }
        try {
            if (!this.N && !this.B.isZoomInByScreenCenter()) {
                this.k.x = i2;
                this.k.y = i3;
                abstractCameraUpdateMessageA = ah.a(1.0f, this.k);
            } else {
                abstractCameraUpdateMessageA = ah.a(1.0f, (Point) null);
            }
            animateCamera(abstractCameraUpdateMessageA);
        } catch (Throwable th) {
            hn.c(th, "AMapDelegateImp", "onDoubleTap");
            th.printStackTrace();
        }
        resetRenderTime();
        return true;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void zoomOut(int i) {
        if (this.aA && ((int) a(i)) > this.f319c.getMinZoomLevel()) {
            try {
                animateCamera(ah.b());
            } catch (Throwable th) {
                hn.c(th, "AMapDelegateImp", "onDoubleTap");
                th.printStackTrace();
            }
            resetRenderTime();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public boolean isLockMapAngle(int i) {
        return a(i, 7);
    }

    private void h(int i) {
        if (this.aA) {
            this.as.a();
            this.at = true;
            this.ay = true;
            try {
                stopAnimation();
            } catch (RemoteException unused) {
            }
        }
    }

    private void i(int i) {
        this.at = true;
        this.ay = false;
        if (this.V) {
            this.V = false;
        }
        if (this.U) {
            this.U = false;
        }
        if (this.W) {
            this.W = false;
        }
        this.R = false;
        try {
            List listA = this.v.a(AMap.OnMarkerDragListener.class.hashCode());
            if (listA == null || listA.size() <= 0 || this.S == null) {
                return;
            }
            synchronized (listA) {
                for (int i2 = 0; i2 < listA.size(); i2++) {
                    ((AMap.OnMarkerDragListener) listA.get(i2)).onMarkerDragEnd(this.S);
                }
            }
            this.S = null;
        } catch (Throwable th) {
            hn.c(th, "AMapDelegateImp", "OnMarkerDragListener.onMarkerDragEnd");
            th.printStackTrace();
        }
    }

    private void a(MotionEvent motionEvent) throws RemoteException {
        if (!this.R || this.S == null || this.T == null) {
            return;
        }
        int x = (int) motionEvent.getX();
        int y = (int) (motionEvent.getY() - 60.0f);
        LatLng realPosition = this.T.getRealPosition();
        if (realPosition != null) {
            LatLng position = this.T.getPosition();
            DPoint dPointObtain = DPoint.obtain();
            getPixel2LatLng(x, y, dPointObtain);
            LatLng latLng = new LatLng((position.latitude + dPointObtain.y) - realPosition.latitude, (position.longitude + dPointObtain.x) - realPosition.longitude);
            dPointObtain.recycle();
            this.S.setPosition(latLng);
            try {
                List listA = this.v.a(AMap.OnMarkerDragListener.class.hashCode());
                if (listA == null || listA.size() <= 0) {
                    return;
                }
                synchronized (listA) {
                    for (int i = 0; i < listA.size(); i++) {
                        ((AMap.OnMarkerDragListener) listA.get(i)).onMarkerDrag(this.S);
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void clearTileCache() {
        ab abVar = this.F;
        if (abVar != null) {
            abVar.i();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public GLMapState getMapProjection() {
        GLMapEngine gLMapEngine = this.f323g;
        if (gLMapEngine != null) {
            return gLMapEngine.getMapState(1);
        }
        return null;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public int getLineTextureID() {
        t tVar = this.aK;
        if (tVar != null) {
            return tVar.a();
        }
        return 0;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public float getLineTextureRatio() {
        t tVar = this.aK;
        if (tVar != null) {
            return tVar.b();
        }
        return 1.0f;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public int getDottedLineTextureID(int i) {
        t tVar = this.aK;
        if (tVar != null) {
            return tVar.a(i);
        }
        return 0;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public int getBaseOverlayTextureID() {
        t tVar = this.aK;
        if (tVar != null) {
            return tVar.c();
        }
        return 0;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void showMyLocationOverlay(Location location) throws RemoteException {
        if (location == null) {
            return;
        }
        try {
            if (this.I && this.Q != null) {
                if (this.P == null) {
                    this.P = new cv(this, this.f322f);
                }
                if (location.getLongitude() != 0.0d && location.getLatitude() != 0.0d) {
                    this.P.a(location);
                }
                List listA = this.v.a(AMap.OnMyLocationChangeListener.class.hashCode());
                if (listA != null && listA.size() > 0) {
                    synchronized (listA) {
                        for (int i = 0; i < listA.size(); i++) {
                            ((AMap.OnMyLocationChangeListener) listA.get(i)).onMyLocationChange(location);
                        }
                    }
                }
                resetRenderTime();
                return;
            }
            if (this.P != null) {
                this.P.c();
            }
            this.P = null;
        } catch (Throwable th) {
            hn.c(th, "AMapDelegateImp", "showMyLocationOverlay");
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public boolean removeGLOverlay(String str) throws RemoteException {
        resetRenderTime();
        return this.H.removeOverlay(str);
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public boolean removeMarker(String str) {
        try {
            IOverlayImageDelegate iOverlayImageDelegateA = this.f318b.a(str);
            if (iOverlayImageDelegateA == null) {
                return false;
            }
            resetRenderTime();
            return this.f318b.a(iOverlayImageDelegateA);
        } catch (Throwable th) {
            hn.c(th, "AMapDelegateImp", "removeMarker");
            th.printStackTrace();
            return false;
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public boolean removeGLModel(String str) {
        try {
            this.L.a(str);
            return false;
        } catch (Throwable th) {
            hn.c(th, "AMapDelegateImp", "removeGLModel");
            th.printStackTrace();
            return false;
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void changeGLOverlayIndex() {
        this.H.changeOverlayIndex();
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public float checkZoomLevel(float f2) throws RemoteException {
        return er.a(this.f319c, f2);
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void latlon2Geo(double d2, double d3, IPoint iPoint) {
        Point pointLatLongToPixels = VirtualEarthProjection.latLongToPixels(d2, d3, 20);
        iPoint.x = pointLatLongToPixels.x;
        iPoint.y = pointLatLongToPixels.y;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void geo2Map(int i, int i2, FPoint fPoint) {
        fPoint.x = (int) (((double) i) - this.f319c.getSX());
        fPoint.y = (int) (((double) i2) - this.f319c.getSY());
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void geo2Latlng(int i, int i2, DPoint dPoint) {
        DPoint dPointPixelsToLatLong = VirtualEarthProjection.pixelsToLatLong(i, i2, 20);
        dPoint.x = dPointPixelsToLatLong.x;
        dPoint.y = dPointPixelsToLatLong.y;
        dPointPixelsToLatLong.recycle();
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public float getZoomLevel() {
        return a(this.J);
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public IUiSettingsDelegate getUiSettings() {
        return this.B;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public IProjectionDelegate getProjection() throws RemoteException {
        return this.A;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public AMap.OnCameraChangeListener getOnCameraChangeListener() throws RemoteException {
        try {
            List listA = this.v.a(AMap.OnCameraChangeListener.class.hashCode());
            if (listA == null && listA.size() != 0) {
                return (AMap.OnCameraChangeListener) listA.get(0);
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void showInfoWindow(BaseOverlayImp baseOverlayImp) throws RemoteException {
        ar arVar;
        if (baseOverlayImp == null || (arVar = this.x) == null) {
            return;
        }
        try {
            arVar.a(baseOverlayImp);
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public boolean isInfoWindowShown(IMarkerDelegate iMarkerDelegate) throws RemoteException {
        ar arVar = this.x;
        if (arVar != null) {
            return arVar.f();
        }
        return false;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void hideInfoWindow() {
        ar arVar = this.x;
        if (arVar != null) {
            arVar.e();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void getLatLng2Map(double d2, double d3, FPoint fPoint) {
        IPoint iPointObtain = IPoint.obtain();
        latlon2Geo(d2, d3, iPointObtain);
        geo2Map(iPointObtain.x, iPointObtain.y, fPoint);
        iPointObtain.recycle();
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void getPixel2LatLng(int i, int i2, DPoint dPoint) {
        GLMapEngine gLMapEngine;
        GLMapState mapState;
        if (!this.aA || (gLMapEngine = this.f323g) == null || (mapState = gLMapEngine.getMapState(1)) == null) {
            return;
        }
        IPoint iPointObtain = IPoint.obtain();
        mapState.screenToP20Point(i, i2, iPointObtain);
        DPoint dPointPixelsToLatLong = VirtualEarthProjection.pixelsToLatLong(iPointObtain.x, iPointObtain.y, 20);
        dPoint.x = dPointPixelsToLatLong.x;
        dPoint.y = dPointPixelsToLatLong.y;
        iPointObtain.recycle();
        dPointPixelsToLatLong.recycle();
    }

    protected void a(GLMapState gLMapState, int i, int i2, DPoint dPoint) {
        if (!this.aA || this.f323g == null) {
            return;
        }
        gLMapState.screenToP20Point(i, i2, new Point());
        DPoint dPointPixelsToLatLong = VirtualEarthProjection.pixelsToLatLong(r0.x, r0.y, 20);
        dPoint.x = dPointPixelsToLatLong.x;
        dPoint.y = dPointPixelsToLatLong.y;
        dPointPixelsToLatLong.recycle();
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void getLatLng2Pixel(double d2, double d3, IPoint iPoint) {
        if (!this.aA || this.f323g == null) {
            return;
        }
        try {
            Point pointLatLongToPixels = VirtualEarthProjection.latLongToPixels(d2, d3, 20);
            FPoint fPointObtain = FPoint.obtain();
            a(pointLatLongToPixels.x, pointLatLongToPixels.y, fPointObtain);
            float f2 = -10000;
            if (fPointObtain.x == f2 && fPointObtain.y == f2) {
                GLMapState gLMapState = (GLMapState) this.f323g.getNewMapState(1);
                gLMapState.setCameraDegree(0.0f);
                gLMapState.recalculate();
                gLMapState.p20ToScreenPoint(pointLatLongToPixels.x, pointLatLongToPixels.y, fPointObtain);
                gLMapState.recycle();
            }
            iPoint.x = (int) fPointObtain.x;
            iPoint.y = (int) fPointObtain.y;
            fPointObtain.recycle();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void getPixel2Geo(int i, int i2, IPoint iPoint) {
        GLMapEngine gLMapEngine;
        GLMapState mapState;
        if (!this.aA || (gLMapEngine = this.f323g) == null || (mapState = gLMapEngine.getMapState(1)) == null) {
            return;
        }
        mapState.screenToP20Point(i, i2, iPoint);
    }

    public void a(int i, int i2, FPoint fPoint) {
        GLMapEngine gLMapEngine;
        GLMapState mapState;
        if (!this.aA || (gLMapEngine = this.f323g) == null || (mapState = gLMapEngine.getMapState(1)) == null) {
            return;
        }
        mapState.p20ToScreenPoint(i, i2, fPoint);
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void redrawInfoWindow() {
        if (this.aA) {
            this.j.sendEmptyMessage(18);
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void showZoomControlsEnabled(boolean z) {
        fj fjVar;
        if (this.K || (fjVar = this.E) == null) {
            return;
        }
        fjVar.b(Boolean.valueOf(z));
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void showIndoorSwitchControlsEnabled(boolean z) {
        fj fjVar;
        if (this.K || (fjVar = this.E) == null) {
            return;
        }
        fjVar.a(Boolean.valueOf(z));
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void showMyLocationButtonEnabled(boolean z) {
        fj fjVar;
        if (this.K || (fjVar = this.E) == null) {
            return;
        }
        fjVar.c(Boolean.valueOf(z));
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void showCompassEnabled(boolean z) {
        fj fjVar;
        if (this.K || (fjVar = this.E) == null) {
            return;
        }
        fjVar.d(Boolean.valueOf(z));
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void showScaleEnabled(boolean z) {
        fj fjVar;
        if (this.K || (fjVar = this.E) == null) {
            return;
        }
        fjVar.e(Boolean.valueOf(z));
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void setZoomPosition(int i) {
        fj fjVar;
        if (this.K || (fjVar = this.E) == null) {
            return;
        }
        fjVar.a(Integer.valueOf(i));
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public FPoint[] getMapRect() {
        if (this.f319c.getMapRect() == null) {
            this.f319c.setMapRect(er.a((IAMapDelegate) this, true));
        }
        return this.f319c.getMapRect();
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public Rect getRect() {
        return this.aa;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public LatLngBounds getMapBounds(LatLng latLng, float f2, float f3, float f4) {
        int mapWidth = getMapWidth();
        int mapHeight = getMapHeight();
        if (mapWidth <= 0 || mapHeight <= 0 || this.K) {
            return null;
        }
        float fA = er.a(this.f319c, f2);
        GLMapState gLMapState = new GLMapState(1, this.f323g.getNativeInstance());
        if (latLng != null) {
            IPoint iPointObtain = IPoint.obtain();
            latlon2Geo(latLng.latitude, latLng.longitude, iPointObtain);
            gLMapState.setCameraDegree(f4);
            gLMapState.setMapAngle(f3);
            gLMapState.setMapGeoCenter(iPointObtain.x, iPointObtain.y);
            gLMapState.setMapZoomer(fA);
            gLMapState.recalculate();
            iPointObtain.recycle();
        }
        DPoint dPointObtain = DPoint.obtain();
        a(gLMapState, 0, 0, dPointObtain);
        LatLng latLng2 = new LatLng(dPointObtain.y, dPointObtain.x, false);
        a(gLMapState, mapWidth, mapHeight, dPointObtain);
        LatLng latLng3 = new LatLng(dPointObtain.y, dPointObtain.x, false);
        dPointObtain.recycle();
        gLMapState.recycle();
        return LatLngBounds.builder().include(latLng3).include(latLng2).build();
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void onResume() {
        try {
            this.ar.setRenderFps(15.0f);
            this.D.setRenderMode(0);
            if (this.F != null) {
                this.F.e();
            }
            if (this.P != null) {
                this.P.b();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void onPause() {
        e();
    }

    private void e() {
        GLMapState gLMapState;
        GLMapEngine gLMapEngine = this.f323g;
        if (gLMapEngine == null || (gLMapState = (GLMapState) gLMapEngine.getNewMapState(1)) == null) {
            return;
        }
        IPoint iPointObtain = IPoint.obtain();
        gLMapState.recalculate();
        gLMapState.getMapGeoCenter(iPointObtain);
        this.f319c.setSX(iPointObtain.x);
        this.f319c.setSY(iPointObtain.y);
        this.f319c.setSZ(gLMapState.getMapZoomer());
        this.f319c.setSC(gLMapState.getCameraDegree());
        this.f319c.setSR(gLMapState.getMapAngle());
        gLMapState.recycle();
        iPointObtain.recycle();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.M || !this.aA || !this.ax) {
            return false;
        }
        EAMapPlatformGestureInfo eAMapPlatformGestureInfo = this.bg;
        eAMapPlatformGestureInfo.mGestureState = 3;
        eAMapPlatformGestureInfo.mGestureType = 8;
        eAMapPlatformGestureInfo.mLocation = new float[]{motionEvent.getX(), motionEvent.getY()};
        int engineIDWithGestureInfo = getEngineIDWithGestureInfo(this.bg);
        b();
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            c();
            h(engineIDWithGestureInfo);
        } else if (action == 1) {
            i(engineIDWithGestureInfo);
        }
        if (motionEvent.getAction() == 2 && this.R) {
            try {
                a(motionEvent);
            } catch (Throwable th) {
                hn.c(th, "AMapDelegateImp", "onDragMarker");
                th.printStackTrace();
            }
            return true;
        }
        if (this.at) {
            try {
                this.as.a(motionEvent);
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
        try {
            List listA = this.v.a(AMap.OnMapTouchListener.class.hashCode());
            if (listA != null && listA.size() > 0) {
                this.j.removeMessages(14);
                Message messageObtainMessage = this.j.obtainMessage();
                messageObtainMessage.what = 14;
                messageObtainMessage.obj = MotionEvent.obtain(motionEvent);
                messageObtainMessage.sendToTarget();
            }
        } catch (Throwable unused) {
        }
        return true;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void pixel2Map(int i, int i2, PointF pointF) {
        if (!this.aA || this.M || this.f323g == null) {
            return;
        }
        IPoint iPointObtain = IPoint.obtain();
        getPixel2Geo(i, i2, iPointObtain);
        pointF.x = iPointObtain.x - ((float) this.f319c.getSX());
        pointF.y = iPointObtain.y - ((float) this.f319c.getSY());
        iPointObtain.recycle();
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void map2Geo(float f2, float f3, IPoint iPoint) {
        iPoint.x = (int) (((double) f2) + this.f319c.getSX());
        iPoint.y = (int) (((double) f3) + this.f319c.getSY());
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public float toMapLenWithWin(int i) {
        GLMapEngine gLMapEngine;
        if (!this.aA || this.M || (gLMapEngine = this.f323g) == null) {
            return 0.0f;
        }
        return gLMapEngine.getMapState(1).getGLUnitWithWin(i);
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public CameraPosition getCameraPositionPrj(boolean z) {
        LatLng latLngF;
        try {
            if (this.f319c == null) {
                return null;
            }
            if (this.aA && !this.M && this.f323g != null) {
                if (z) {
                    DPoint dPointObtain = DPoint.obtain();
                    getPixel2LatLng(this.f319c.getAnchorX(), this.f319c.getAnchorY(), dPointObtain);
                    latLngF = new LatLng(dPointObtain.y, dPointObtain.x, false);
                    dPointObtain.recycle();
                } else {
                    latLngF = f();
                }
                return CameraPosition.builder().target(latLngF).bearing(this.f319c.getSR()).tilt(this.f319c.getSC()).zoom(this.f319c.getSZ()).build();
            }
            DPoint dPointObtain2 = DPoint.obtain();
            geo2Latlng((int) this.f319c.getSX(), (int) this.f319c.getSY(), dPointObtain2);
            LatLng latLng = new LatLng(dPointObtain2.y, dPointObtain2.x);
            dPointObtain2.recycle();
            return CameraPosition.builder().target(latLng).bearing(this.f319c.getSR()).tilt(this.f319c.getSC()).zoom(this.f319c.getSZ()).build();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private LatLng f() {
        MapConfig mapConfig = this.f319c;
        if (mapConfig == null) {
            return null;
        }
        DPoint dPointPixelsToLatLong = VirtualEarthProjection.pixelsToLatLong(mapConfig.getSX(), this.f319c.getSY(), 20);
        LatLng latLng = new LatLng(dPointPixelsToLatLong.y, dPointPixelsToLatLong.x, false);
        dPointPixelsToLatLong.recycle();
        return latLng;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public boolean isUseAnchor() {
        return this.N;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public Point getWaterMarkerPositon() {
        fj fjVar = this.E;
        if (fjVar != null) {
            return fjVar.a();
        }
        return new Point();
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public View getGLMapView() {
        Object obj = this.D;
        if (obj instanceof View) {
            return (View) obj;
        }
        return null;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public boolean canShowIndoorSwitch() {
        aq aqVar;
        if (getZoomLevel() < 17 || (aqVar = this.f320d) == null || aqVar.f163g == null) {
            return false;
        }
        FPoint fPointObtain = FPoint.obtain();
        a(this.f320d.f163g.x, this.f320d.f163g.y, fPointObtain);
        return this.aa.contains((int) fPointObtain.x, (int) fPointObtain.y);
    }

    private synchronized void g() {
        synchronized (this.an) {
            int size = this.an.size();
            for (int i = 0; i < size; i++) {
                this.an.get(i).j().recycle();
            }
            this.an.clear();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void addTextureItem(x xVar) {
        if (xVar == null || xVar.k() == 0) {
            return;
        }
        synchronized (this.an) {
            this.an.add(xVar);
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void removeTextureItem(String str) {
        synchronized (this.an) {
            int size = this.an.size();
            int i = -1;
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    break;
                }
                if (this.an.get(i2).p().equals(str)) {
                    i = i2;
                    break;
                }
                i2++;
            }
            if (i >= 0) {
                this.an.remove(i);
            }
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public x getTextureItem(BitmapDescriptor bitmapDescriptor) {
        return getTextureItem(bitmapDescriptor, false);
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public x getTextureItem(BitmapDescriptor bitmapDescriptor, boolean z) {
        if (bitmapDescriptor == null || bitmapDescriptor.getBitmap() == null || bitmapDescriptor.getBitmap().isRecycled()) {
            return null;
        }
        synchronized (this.an) {
            for (int i = 0; i < this.an.size(); i++) {
                x xVar = this.an.get(i);
                if ((!z || xVar.k() != getBaseOverlayTextureID()) && xVar.j().equals(bitmapDescriptor)) {
                    return xVar;
                }
            }
            return null;
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public int getLogoPosition() {
        try {
            return this.B.getLogoPosition();
        } catch (RemoteException e2) {
            hn.c(e2, "AMapDelegateImp", "getLogoPosition");
            e2.printStackTrace();
            return 0;
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void setLogoPosition(int i) {
        fj fjVar = this.E;
        if (fjVar != null) {
            fjVar.b(Integer.valueOf(i));
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void setLogoBottomMargin(int i) {
        fj fjVar = this.E;
        if (fjVar != null) {
            fjVar.c(Integer.valueOf(i));
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void setLogoLeftMargin(int i) {
        fj fjVar = this.E;
        if (fjVar != null) {
            fjVar.d(Integer.valueOf(i));
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public float getLogoMarginRate(int i) {
        fj fjVar = this.E;
        if (fjVar != null) {
            return fjVar.a(i);
        }
        return 0.0f;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void setLogoMarginRate(int i, float f2) {
        fj fjVar = this.E;
        if (fjVar != null) {
            fjVar.a(Integer.valueOf(i), Float.valueOf(f2));
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public int getMaskLayerType() {
        return this.al;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void post(Runnable runnable) {
        IGLSurfaceView iGLSurfaceView = this.D;
        if (iGLSurfaceView != null) {
            iGLSurfaceView.post(runnable);
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void onLongPress(int i, MotionEvent motionEvent) {
        int i2 = 0;
        try {
            this.U = false;
            b(i);
            this.T = this.f318b.a(motionEvent);
            if (this.T != null && this.T.isDraggable()) {
                this.S = new Marker((cu) this.T);
                LatLng position = this.S.getPosition();
                LatLng realPosition = this.T.getRealPosition();
                if (position != null && realPosition != null) {
                    IPoint iPointObtain = IPoint.obtain();
                    getLatLng2Pixel(realPosition.latitude, realPosition.longitude, iPointObtain);
                    iPointObtain.y -= 60;
                    DPoint dPointObtain = DPoint.obtain();
                    getPixel2LatLng(iPointObtain.x, iPointObtain.y, dPointObtain);
                    this.S.setPosition(new LatLng((position.latitude + dPointObtain.y) - realPosition.latitude, (position.longitude + dPointObtain.x) - realPosition.longitude));
                    this.f318b.a((IMarkerDelegate) this.T);
                    try {
                        List listA = this.v.a(AMap.OnMarkerDragListener.class.hashCode());
                        if (listA != null && listA.size() > 0) {
                            synchronized (listA) {
                                while (i2 < listA.size()) {
                                    ((AMap.OnMarkerDragListener) listA.get(i2)).onMarkerDragStart(this.S);
                                    i2++;
                                }
                            }
                        }
                    } catch (Throwable th) {
                        hn.c(th, "AMapDelegateImp", "onMarkerDragStart");
                        th.printStackTrace();
                    }
                    this.R = true;
                    iPointObtain.recycle();
                    dPointObtain.recycle();
                }
            } else {
                List listA2 = this.v.a(AMap.OnMapLongClickListener.class.hashCode());
                if (listA2 != null && listA2.size() > 0) {
                    DPoint dPointObtain2 = DPoint.obtain();
                    getPixel2LatLng((int) motionEvent.getX(), (int) motionEvent.getY(), dPointObtain2);
                    synchronized (listA2) {
                        while (i2 < listA2.size()) {
                            ((AMap.OnMapLongClickListener) listA2.get(i2)).onMapLongClick(new LatLng(dPointObtain2.y, dPointObtain2.x));
                            i2++;
                        }
                    }
                    this.V = true;
                    dPointObtain2.recycle();
                }
            }
            this.ar.resetTickCount(30);
        } catch (Throwable th2) {
            hn.c(th2, "AMapDelegateImp", "onLongPress");
            th2.printStackTrace();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public boolean onDoubleTap(int i, MotionEvent motionEvent) {
        if (!this.aA) {
            return false;
        }
        a(i, (int) motionEvent.getX(), (int) motionEvent.getY());
        return false;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public boolean onSingleTapConfirmed(int i, MotionEvent motionEvent) {
        if (!this.aA) {
            return false;
        }
        try {
            b(i);
            if (g(motionEvent) || e(motionEvent) || f(motionEvent) || d(motionEvent)) {
                return true;
            }
            b(motionEvent);
            return true;
        } catch (Throwable th) {
            hn.c(th, "AMapDelegateImp", "onSingleTapUp");
            th.printStackTrace();
            return true;
        }
    }

    private void b(final MotionEvent motionEvent) {
        queueEvent(new Runnable() { // from class: com.amap.api.mapcore.util.c.7
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Message messageObtain = Message.obtain();
                    Poi poiB = null;
                    if (c.this.f319c != null && c.this.f319c.isTouchPoiEnable()) {
                        poiB = c.this.b((int) motionEvent.getX(), (int) motionEvent.getY(), 25);
                    }
                    List listA = c.this.v.a(AMap.OnPOIClickListener.class.hashCode());
                    if (listA == null || listA.size() <= 0 || poiB == null) {
                        c.this.c(motionEvent);
                        return;
                    }
                    messageObtain.what = 20;
                    messageObtain.obj = poiB;
                    c.this.j.sendMessage(messageObtain);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final MotionEvent motionEvent) {
        this.j.post(new Runnable() { // from class: com.amap.api.mapcore.util.c.8
            @Override // java.lang.Runnable
            public void run() {
                Message messageObtain = Message.obtain();
                messageObtain.what = 19;
                messageObtain.arg1 = (int) motionEvent.getX();
                messageObtain.arg2 = (int) motionEvent.getY();
                c.this.j.sendMessage(messageObtain);
            }
        });
    }

    private boolean d(MotionEvent motionEvent) {
        try {
            List listA = this.v.a(AMap.OnPolylineClickListener.class.hashCode());
            if (listA != null && listA.size() > 0) {
                DPoint dPointObtain = DPoint.obtain();
                getPixel2LatLng((int) motionEvent.getX(), (int) motionEvent.getY(), dPointObtain);
                LatLng latLng = new LatLng(dPointObtain.y, dPointObtain.x);
                dPointObtain.recycle();
                Polyline hitOverlay = this.H.getHitOverlay(latLng);
                if (hitOverlay != null) {
                    synchronized (listA) {
                        Iterator it = listA.iterator();
                        while (it.hasNext()) {
                            ((AMap.OnPolylineClickListener) it.next()).onPolylineClick(hitOverlay);
                        }
                    }
                    return false;
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    private boolean e(MotionEvent motionEvent) throws RemoteException {
        LatLng realPosition;
        if (this.f318b.b(motionEvent)) {
            BaseOverlayImp baseOverlayImpD = this.f318b.d();
            boolean z = true;
            if (baseOverlayImpD == null) {
                return true;
            }
            try {
                Marker marker = new Marker((cu) baseOverlayImpD);
                this.f318b.a((IMarkerDelegate) baseOverlayImpD);
                List listA = this.v.a(AMap.OnMarkerClickListener.class.hashCode());
                if (listA != null && listA.size() > 0) {
                    synchronized (listA) {
                        if (listA.size() == 1) {
                            boolean zOnMarkerClick = ((AMap.OnMarkerClickListener) listA.get(0)).onMarkerClick(marker);
                            if (!zOnMarkerClick && this.f318b.g() > 0) {
                                z = zOnMarkerClick;
                            }
                            return true;
                        }
                        Iterator it = listA.iterator();
                        boolean zOnMarkerClick2 = false;
                        while (it.hasNext()) {
                            zOnMarkerClick2 |= ((AMap.OnMarkerClickListener) it.next()).onMarkerClick(marker);
                        }
                        if (!zOnMarkerClick2 && this.f318b.g() > 0) {
                            z = zOnMarkerClick2;
                        }
                        return true;
                    }
                }
                showInfoWindow((cu) baseOverlayImpD);
                if (!baseOverlayImpD.isViewMode() && (realPosition = baseOverlayImpD.getRealPosition()) != null) {
                    IPoint iPointObtain = IPoint.obtain();
                    latlon2Geo(realPosition.latitude, realPosition.longitude, iPointObtain);
                    moveCamera(ah.a(iPointObtain));
                }
                return z;
            } catch (Throwable th) {
                hn.c(th, "AMapDelegateImp", "onMarkerTap");
                th.printStackTrace();
            }
        }
        return false;
    }

    private boolean f(MotionEvent motionEvent) {
        if (this.aL == null) {
            return false;
        }
        IPoint iPointObtain = IPoint.obtain();
        if (this.f323g != null) {
            getPixel2Geo((int) motionEvent.getX(), (int) motionEvent.getY(), iPointObtain);
        }
        boolean zA = this.aL.a(iPointObtain);
        iPointObtain.recycle();
        return zA;
    }

    private boolean g(MotionEvent motionEvent) throws RemoteException {
        try {
            List listA = this.v.a(AMap.OnInfoWindowClickListener.class.hashCode());
            if (this.x != null && this.x.a(motionEvent)) {
                if (listA != null && listA.size() > 0) {
                    BaseOverlayImp baseOverlayImpD = this.f318b.d();
                    if (!baseOverlayImpD.isVisible() && baseOverlayImpD.isInfoWindowEnable()) {
                        return true;
                    }
                    Marker marker = new Marker((cu) baseOverlayImpD);
                    synchronized (listA) {
                        for (int i = 0; i < listA.size(); i++) {
                            ((AMap.OnInfoWindowClickListener) listA.get(i)).onInfoWindowClick(marker);
                        }
                    }
                }
                return true;
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void drawFrame(GL10 gl10) {
        if (this.K || this.f323g == null || EGL14.eglGetCurrentContext() == EGL14.EGL_NO_CONTEXT) {
            return;
        }
        MapConfig mapConfig = this.f319c;
        if (mapConfig != null && !mapConfig.isMapEnable()) {
            GLES20.glClear(16640);
            return;
        }
        a(1, gl10);
        this.f323g.renderAMap();
        this.f323g.pushRendererState();
        CustomRenderer customRenderer = this.aj;
        if (customRenderer != null) {
            customRenderer.onDrawFrame(gl10);
        }
        com.amap.api.mapcore.util.b bVar = this.aM;
        if (bVar != null) {
            bVar.a();
        }
        a(gl10);
        i();
        if (!this.aC) {
            this.aC = true;
        }
        this.f323g.popRendererState();
        if (ei.a()) {
            try {
                if (this.D instanceof f) {
                    if (this.f321e == null) {
                        this.f321e = new ei();
                    }
                    this.f321e.e();
                    if (!this.f321e.f() || this.f321e.d()) {
                        return;
                    }
                    if (this.f321e.a(((f) this.D).getBitmap())) {
                        if (ei.b()) {
                            removecache();
                        }
                        if (ei.c()) {
                            this.f321e.g();
                        }
                        ey.b(ex.f800g, "pure screen: found pure check");
                    }
                }
            } catch (Throwable th) {
                hn.c(th, "AMapDelegateImp", "PureScreenCheckTool.checkBlackScreen");
            }
        }
    }

    private void a(int i, GL10 gl10) {
        int i2 = this.am;
        if (i2 != -1) {
            this.ar.setRenderFps(i2);
            resetRenderTime();
        } else if (this.f323g.isInMapAction(i) || this.ay) {
            this.ar.setRenderFps(40.0f);
        } else if (this.f323g.isInMapAnimation(i)) {
            this.ar.setRenderFps(30.0f);
            this.ar.resetTickCount(15);
        } else {
            this.ar.setRenderFps(15.0f);
        }
        if (this.f319c.isWorldMapEnable() != MapsInitializer.isLoadWorldGridMap()) {
            a(true);
            this.f319c.setWorldMapEnable(MapsInitializer.isLoadWorldGridMap());
        }
    }

    private void a(GL10 gl10) {
        if (this.Y) {
            boolean zCanStopMapRender = this.f323g.canStopMapRender(1);
            Message messageObtainMessage = this.j.obtainMessage(15, er.a(0, 0, getMapWidth(), getMapHeight()));
            messageObtainMessage.arg1 = zCanStopMapRender ? 1 : 0;
            messageObtainMessage.sendToTarget();
            this.Y = false;
        }
    }

    public void a() {
        List listA;
        List listA2;
        List listA3;
        if (this.f319c.getMapRect() == null || this.ah) {
            h();
            this.ah = false;
        }
        boolean z = true;
        this.f323g.getCurTileIDs(1, this.f319c.getCurTileIds());
        GLMapState mapState = this.f323g.getMapState(1);
        if (mapState != null) {
            mapState.getViewMatrix(this.f319c.getViewMatrix());
            mapState.getProjectionMatrix(this.f319c.getProjectionMatrix());
            this.f319c.updateFinalMatrix();
            DPoint mapGeoCenter = mapState.getMapGeoCenter();
            this.f319c.setSX(mapGeoCenter.x);
            this.f319c.setSY(mapGeoCenter.y);
            this.f319c.setSZ(mapState.getMapZoomer());
            this.f319c.setSC(mapState.getCameraDegree());
            this.f319c.setSR(mapState.getMapAngle());
            if (this.f319c.isMapStateChange()) {
                this.f319c.setSkyHeight(mapState.getSkyHeight());
                DPoint dPointPixelsToLatLong = VirtualEarthProjection.pixelsToLatLong(mapGeoCenter.x, mapGeoCenter.y, 20);
                CameraPosition cameraPosition = new CameraPosition(new LatLng(dPointPixelsToLatLong.y, dPointPixelsToLatLong.x, false), this.f319c.getSZ(), this.f319c.getSC(), this.f319c.getSR());
                dPointPixelsToLatLong.recycle();
                Message messageObtainMessage = this.j.obtainMessage();
                messageObtainMessage.what = 10;
                messageObtainMessage.obj = cameraPosition;
                this.j.sendMessage(messageObtainMessage);
                this.az = true;
                redrawInfoWindow();
                h();
                try {
                    if (this.B.isZoomControlsEnabled() && this.f319c.isNeedUpdateZoomControllerState() && (listA3 = this.v.a(AMapWidgetListener.class.hashCode())) != null && listA3.size() > 0) {
                        synchronized (listA3) {
                            for (int i = 0; i < listA3.size(); i++) {
                                ((AMapWidgetListener) listA3.get(i)).invalidateZoomController(this.f319c.getSZ());
                            }
                        }
                    }
                    if (this.f319c.getChangeGridRatio() != 1.0d) {
                        a(true);
                    }
                    if (!this.B.isCompassEnabled() || (!this.f319c.isTiltChanged() && !this.f319c.isBearingChanged())) {
                        z = false;
                    }
                    if (z && (listA2 = this.v.a(AMapWidgetListener.class.hashCode())) != null && listA2.size() > 0) {
                        synchronized (listA2) {
                            for (int i2 = 0; i2 < listA2.size(); i2++) {
                                ((AMapWidgetListener) listA2.get(i2)).invalidateCompassView();
                            }
                        }
                    }
                    if (!this.B.isScaleControlsEnabled() || (listA = this.v.a(AMapWidgetListener.class.hashCode())) == null || listA.size() <= 0) {
                        return;
                    }
                    synchronized (listA) {
                        for (int i3 = 0; i3 < listA.size(); i3++) {
                            ((AMapWidgetListener) listA.get(i3)).invalidateScaleView();
                        }
                    }
                    return;
                } catch (Throwable th) {
                    th.printStackTrace();
                    return;
                }
            }
            if (!this.ay && this.f323g.getAnimateionsCount() == 0 && this.f323g.getStateMessageCount() == 0) {
                onChangeFinish();
            }
        }
    }

    private void h() {
        try {
            this.f319c.setMapRect(er.a((IAMapDelegate) this, true));
            GLMapState gLMapState = (GLMapState) this.f323g.getNewMapState(1);
            if (gLMapState != null) {
                gLMapState.recalculate();
                gLMapState.getPixel20Bound(this.l, getMapWidth(), getMapHeight());
                this.f319c.getGeoRectangle().updateRect(this.l, (int) this.f319c.getSX(), (int) this.f319c.getSY());
                this.f319c.setMapPerPixelUnitLength(gLMapState.getGLUnitWithWin(1));
                gLMapState.recycle();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void i() {
        if (!this.O) {
            this.j.sendEmptyMessage(16);
            this.O = true;
            a(true);
        }
        long j = this.bh;
        if (j < 2) {
            this.bh = j + 1;
            return;
        }
        final ff ffVarD = this.E.d();
        if (ffVarD == null || ffVarD.getVisibility() == 8) {
            return;
        }
        ep.a(this.f322f, System.currentTimeMillis() - this.aN);
        this.j.post(new Runnable() { // from class: com.amap.api.mapcore.util.c.9
            @Override // java.lang.Runnable
            public void run() {
                if (c.this.M) {
                    return;
                }
                try {
                    if (c.this.f320d != null) {
                        c.this.setIndoorBuildingInfo(c.this.f320d);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                ffVarD.a(false);
            }
        });
        this.f323g.setStyleChangeGradualEnable(this.J, true);
    }

    void a(boolean z) {
        this.j.obtainMessage(17, z ? 1 : 0, 0).sendToTarget();
    }

    public void b(final int i) {
        queueEvent(new Runnable() { // from class: com.amap.api.mapcore.util.c.10
            @Override // java.lang.Runnable
            public void run() {
                if (!c.this.aA || c.this.f323g == null) {
                    return;
                }
                c.this.f323g.setHighlightSubwayEnable(i, false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Poi b(int i, int i2, int i3) {
        if (!this.aA) {
            return null;
        }
        try {
            ArrayList<MapLabelItem> arrayListA = a(1, i, i2, i3);
            MapLabelItem mapLabelItem = (arrayListA == null || arrayListA.size() <= 0) ? null : arrayListA.get(0);
            if (mapLabelItem != null) {
                DPoint dPointPixelsToLatLong = VirtualEarthProjection.pixelsToLatLong(mapLabelItem.pixel20X, mapLabelItem.pixel20Y, 20);
                Poi poi = new Poi(mapLabelItem.name, new LatLng(dPointPixelsToLatLong.y, dPointPixelsToLatLong.x, false), mapLabelItem.poiid);
                dPointPixelsToLatLong.recycle();
                return poi;
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    public ArrayList<MapLabelItem> a(int i, int i2, int i3, int i4) {
        if (!this.aA) {
            return null;
        }
        ArrayList<MapLabelItem> arrayList = new ArrayList<>();
        byte[] labelBuffer = this.f323g.getLabelBuffer(i, i2, i3, i4);
        if (labelBuffer == null) {
            return null;
        }
        int i5 = GLConvertUtil.getInt(labelBuffer, 0) >= 1 ? 1 : 0;
        int i6 = 0;
        int i7 = 4;
        while (i6 < i5) {
            MapLabelItem mapLabelItem = new MapLabelItem();
            int i8 = GLConvertUtil.getInt(labelBuffer, i7);
            int i9 = i7 + 4;
            int i10 = GLConvertUtil.getInt(labelBuffer, i9);
            int i11 = i9 + 4;
            mapLabelItem.x = i8;
            mapLabelItem.y = this.D.getHeight() - i10;
            mapLabelItem.pixel20X = GLConvertUtil.getInt(labelBuffer, i11);
            int i12 = i11 + 4;
            mapLabelItem.pixel20Y = GLConvertUtil.getInt(labelBuffer, i12);
            int i13 = i12 + 4;
            mapLabelItem.pixel20Z = GLConvertUtil.getInt(labelBuffer, i13);
            int i14 = i13 + 4;
            mapLabelItem.type = GLConvertUtil.getInt(labelBuffer, i14);
            int i15 = i14 + 4;
            mapLabelItem.mSublayerId = GLConvertUtil.getInt(labelBuffer, i15);
            int i16 = i15 + 4;
            mapLabelItem.timeStamp = GLConvertUtil.getInt(labelBuffer, i16);
            int i17 = i16 + 4;
            mapLabelItem.mIsFouces = labelBuffer[i17] != 0;
            int i18 = i17 + 1;
            if (labelBuffer[i18] == 0) {
                mapLabelItem.poiid = null;
            } else {
                String str = "";
                for (int i19 = 0; i19 < 20; i19++) {
                    int i20 = i19 + i18;
                    if (labelBuffer[i20] == 0) {
                        break;
                    }
                    str = str + ((char) labelBuffer[i20]);
                }
                mapLabelItem.poiid = str;
            }
            int i21 = i18 + 20;
            int i22 = i21 + 1;
            byte b2 = labelBuffer[i21];
            StringBuffer stringBuffer = new StringBuffer();
            int i23 = i22;
            for (int i24 = 0; i24 < b2; i24++) {
                stringBuffer.append((char) GLConvertUtil.getShort(labelBuffer, i23));
                i23 += 2;
            }
            mapLabelItem.name = stringBuffer.toString();
            arrayList.add(mapLabelItem);
            i6++;
            i7 = i23;
        }
        return arrayList;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public float getMapAngle(int i) {
        MapConfig mapConfig = this.f319c;
        if (mapConfig != null) {
            return mapConfig.getSR();
        }
        return 0.0f;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void getGeoCenter(int i, IPoint iPoint) {
        MapConfig mapConfig = this.f319c;
        if (mapConfig != null) {
            iPoint.x = (int) mapConfig.getSX();
            iPoint.y = (int) this.f319c.getSY();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public float getCameraDegree(int i) {
        MapConfig mapConfig = this.f319c;
        if (mapConfig != null) {
            return mapConfig.getSC();
        }
        return 0.0f;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void addGestureMapMessage(int i, AbstractGestureMapMessage abstractGestureMapMessage) {
        if (!this.aA || this.f323g == null) {
            return;
        }
        try {
            abstractGestureMapMessage.isUseAnchor = this.N;
            abstractGestureMapMessage.anchorX = this.f319c.getAnchorX();
            abstractGestureMapMessage.anchorY = this.f319c.getAnchorY();
            this.f323g.addGestureMessage(i, abstractGestureMapMessage, this.B.isGestureScaleByMapCenter(), this.f319c.getAnchorX(), this.f319c.getAnchorY());
        } catch (RemoteException unused) {
        }
    }

    public void c(int i) {
        GLMapRender gLMapRender = this.ar;
        if (gLMapRender != null) {
            gLMapRender.renderPause();
        }
        f(i);
    }

    public void d(int i) {
        f(i);
        GLMapRender gLMapRender = this.ar;
        if (gLMapRender != null) {
            gLMapRender.renderResume();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void resetRenderTimeLongLong() {
        GLMapRender gLMapRender = this.ar;
        if (gLMapRender != null) {
            gLMapRender.resetTickCount(30);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void resetRenderTime() {
        GLMapRender gLMapRender = this.ar;
        if (gLMapRender != null) {
            gLMapRender.resetTickCount(2);
        }
    }

    public void b() {
        GLMapRender gLMapRender = this.ar;
        if (gLMapRender != null) {
            gLMapRender.resetTickCount(2);
        }
    }

    public void c() {
        GLMapRender gLMapRender;
        if (!this.aA || (gLMapRender = this.ar) == null || gLMapRender.isRenderPause()) {
            return;
        }
        requestRender();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void requestRender() {
        GLMapRender gLMapRender = this.ar;
        if (gLMapRender == null || gLMapRender.isRenderPause()) {
            return;
        }
        this.D.requestRender();
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void changeMapLogo(int i, boolean z) {
        if (this.K) {
            return;
        }
        try {
            List listA = this.v.a(AMapWidgetListener.class.hashCode());
            if (listA == null || listA.size() <= 0) {
                return;
            }
            this.E.g(Boolean.valueOf(!z));
        } catch (Throwable unused) {
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public int getRenderMode() {
        return this.D.getRenderMode();
    }

    private void j() {
        if (this.af) {
            return;
        }
        try {
            this.ad.setName("AuthThread");
            this.ad.start();
            this.af = true;
        } catch (Throwable th) {
            th.printStackTrace();
            er.a(th);
        }
    }

    private void k() {
        if (this.ag) {
            return;
        }
        try {
            if (this.ae == null) {
                this.ae = new i(this.f322f, this);
            }
            this.ae.setName("AuthProThread");
            this.ae.start();
            this.ag = true;
        } catch (Throwable th) {
            th.printStackTrace();
            er.a(th);
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public float getMapZoomScale() {
        return this.av;
    }

    public synchronized void b(int i, int i2, int i3, int i4) {
        a(i, i2, i3, i4, false, false, (StyleItem[]) null);
    }

    public synchronized void a(final int i, final int i2, final int i3, final int i4, final boolean z, final boolean z2, final StyleItem[] styleItemArr) {
        if (this.aB && this.aA && this.f317a) {
            e(i3);
            queueEvent(new Runnable() { // from class: com.amap.api.mapcore.util.c.12
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        c.this.f323g.setMapModeAndStyle(i, i2, i3, i4, z, z2, styleItemArr);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
        } else {
            this.aQ.f395g = i;
            this.aQ.f392d = i2;
            this.aQ.f393e = i3;
            this.aQ.f394f = i4;
            this.aQ.f390b = true;
        }
    }

    protected void e(int i) {
        fj fjVar = this.E;
        if (fjVar != null) {
            if (i == 0) {
                if (fjVar.b()) {
                    this.E.g(false);
                    this.E.c();
                    return;
                }
                return;
            }
            if (fjVar.b()) {
                return;
            }
            this.E.g(true);
            this.E.c();
        }
    }

    public void f(final int i) {
        queueEvent(new Runnable() { // from class: com.amap.api.mapcore.util.c.13
            @Override // java.lang.Runnable
            public void run() {
                try {
                    c.this.f323g.clearAllMessages(i);
                    c.this.f323g.clearAnimations(i, true);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    public void a(final int i, final boolean z) {
        if (this.aA && this.aB) {
            resetRenderTime();
            queueEvent(new Runnable() { // from class: com.amap.api.mapcore.util.c.14
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        c.this.f323g.setBuildingEnable(i, z);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
        } else {
            a aVar = this.aS;
            aVar.f391c = z;
            aVar.f390b = true;
            aVar.f395g = i;
        }
    }

    public void b(final int i, final boolean z) {
        if (this.aA && this.aB) {
            resetRenderTime();
            queueEvent(new Runnable() { // from class: com.amap.api.mapcore.util.c.15
                @Override // java.lang.Runnable
                public void run() {
                    if (c.this.f323g != null) {
                        if (z) {
                            c.this.f323g.setAllContentEnable(i, true);
                        } else {
                            c.this.f323g.setAllContentEnable(i, false);
                        }
                        c.this.f323g.setSimple3DEnable(i, false);
                    }
                }
            });
        } else {
            a aVar = this.aW;
            aVar.f391c = z;
            aVar.f390b = true;
            aVar.f395g = i;
        }
    }

    public void c(final int i, final boolean z) {
        if (this.aA && this.aB) {
            resetRenderTime();
            queueEvent(new Runnable() { // from class: com.amap.api.mapcore.util.c.16
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (z) {
                            c.this.f323g.setBuildingTextureEnable(i, true);
                        } else {
                            c.this.f323g.setBuildingTextureEnable(i, false);
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
        } else {
            a aVar = this.aZ;
            aVar.f391c = z;
            aVar.f390b = true;
            aVar.f395g = i;
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public synchronized void createSurface(int i, GL10 gl10, EGLConfig eGLConfig) {
        ey.a(ex.f796c, "createSurface");
        this.aN = System.currentTimeMillis();
        if (this.ab == 3) {
            this.E.d().a(ff.f832b);
        } else {
            this.E.d().a(ff.f831a);
        }
        this.aB = false;
        this.f324h = this.D.getWidth();
        this.i = this.D.getHeight();
        this.aD = false;
        try {
            boolean zLoadLib = AeUtil.loadLib(this.f322f);
            ey.a(ex.f796c, "load lib complete");
            AeUtil.initCrashHandle(this.f322f, zLoadLib);
            GLMapEngine.InitParam initParamInitResource = AeUtil.initResource(this.f322f);
            ey.a(ex.f796c, "load res complete");
            this.f323g.createAMapInstance(initParamInitResource);
            ey.a(ex.f796c, "create engine complete");
            j(i);
            synchronized (this.G) {
                if (this.F == null) {
                    this.F = new ab(this.f322f, this);
                }
            }
            this.aJ = new de();
            ey.a(ex.f796c, "init shader complete");
            this.H.setGlShaderManager(this.aJ);
            if (this.bb != null) {
                this.bb.a("setGLShaderManager", this.aJ);
            }
            this.aA = true;
            this.m = gl10.glGetString(7937);
        } catch (Throwable th) {
            er.a(th);
            hn.c(th, "AMapDElegateImp", "createSurface");
            ey.b(ex.f796c, "createSurface failed " + th.getMessage());
        }
        GLMapState mapState = this.f323g.getMapState(1);
        if (mapState != null && mapState.getNativeInstance() != 0) {
            mapState.setMapGeoCenter((int) this.f319c.getSX(), (int) this.f319c.getSY());
            mapState.setMapAngle(this.f319c.getSR());
            mapState.setMapZoomer(this.f319c.getSZ());
            mapState.setCameraDegree(this.f319c.getSC());
        }
        this.aK.a(this.f322f);
        this.H.loadBitmapDescription(this.f322f);
        j();
        if (this.aj != null) {
            this.aj.onSurfaceCreated(gl10, eGLConfig);
        }
        if (this.bb != null) {
            this.bb.a(gl10, eGLConfig);
        }
        d();
        this.H.onCreateAMapInstance();
    }

    protected void d() {
        AMapNativeRenderer.nativeDrawLineInit();
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void changeSurface(int i, GL10 gl10, int i2, int i3) {
        WindowManager windowManager;
        ey.a(ex.f796c, "changeSurface " + i2 + " " + i3);
        this.aD = false;
        if (!this.aA) {
            createSurface(i, gl10, null);
        }
        p pVar = this.as;
        if (pVar != null && this.f322f != null && ((this.f324h != pVar.b() || this.i != this.as.c()) && (windowManager = (WindowManager) this.f322f.getSystemService("window")) != null)) {
            Display defaultDisplay = windowManager.getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            if (defaultDisplay != null) {
                defaultDisplay.getRealMetrics(displayMetrics);
                this.as.a(displayMetrics.widthPixels, displayMetrics.heightPixels);
            }
        }
        this.f324h = i2;
        this.i = i3;
        this.ah = true;
        this.aa = new Rect(0, 0, i2, i3);
        this.J = a(i, new Rect(0, 0, this.f324h, this.i), this.f324h, this.i);
        ey.a(ex.f796c, "create engine with frame complete");
        if (!this.aB) {
            MapConfig mapConfig = this.f319c;
            if (mapConfig != null) {
                mapConfig.setMapZoomScale(this.av);
                this.f319c.setMapWidth(i2);
                this.f319c.setMapHeight(i3);
            }
            this.f323g.setIndoorEnable(this.J, false);
            this.f323g.setSimple3DEnable(this.J, false);
            this.f323g.setStyleChangeGradualEnable(this.J, false);
            this.f323g.initNativeTexture(this.J);
            this.f323g.setMapOpenLayer("{\"bounds\" : [{\"x2\" : 235405312,\"x1\" : 188874751,\"y2\" : 85065727,\"y1\" : 122421247}],\"sublyr\" : [{\"type\" : 4,\"sid\" : 9000006,\"zlevel\" : 2}],\"id\" : 9006,\"minzoom\" : 6,\"update_period\" : 90,\"maxzoom\" : 20,\"cachemode\" : 2,\"url\" : \"http://mpsapi.amap.com//ws/mps/lyrdata/ugc/\"}");
        }
        synchronized (this) {
            this.aB = true;
        }
        if (!this.N) {
            this.f319c.setAnchorX(i2 >> 1);
            this.f319c.setAnchorY(i3 >> 1);
        } else {
            this.f319c.setAnchorX(Math.max(1, Math.min(this.aG, i2 - 1)));
            this.f319c.setAnchorY(Math.max(1, Math.min(this.aH, i3 - 1)));
        }
        this.f323g.setProjectionCenter(this.J, this.f319c.getAnchorX(), this.f319c.getAnchorY());
        this.f317a = true;
        if (this.aW.f390b) {
            this.aW.run();
        }
        if (this.aQ.f390b) {
            this.aQ.run();
        }
        if (this.aR.f390b) {
            this.aR.run();
        }
        if (this.aO.f390b) {
            this.aO.run();
        }
        if (this.aS.f390b) {
            this.aS.run();
        }
        if (this.aZ.f390b) {
            this.aZ.run();
        }
        if (this.aT.f390b) {
            this.aT.run();
        }
        if (this.aU.f390b) {
            this.aU.run();
        }
        if (this.aV.f390b) {
            this.aV.run();
        }
        if (this.aX.f390b) {
            this.aX.run();
        }
        if (this.aP.f390b) {
            this.aP.run();
        }
        if (this.ba.f390b) {
            this.ba.run();
        }
        CustomRenderer customRenderer = this.aj;
        if (customRenderer != null) {
            customRenderer.onSurfaceChanged(gl10, i2, i3);
        }
        jx jxVar = this.bb;
        if (jxVar != null) {
            jxVar.a(gl10, i2, i3);
        }
        Handler handler = this.j;
        if (handler != null) {
            handler.post(this.aY);
        }
        redrawInfoWindow();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void destroySurface(int i) {
        this.aE.lock();
        try {
            if (this.aA) {
                if (EGL14.eglGetCurrentContext() != EGL14.EGL_NO_CONTEXT && (this.D instanceof f)) {
                    n();
                } else {
                    n();
                }
                if (this.f323g != null) {
                    if (this.f323g.getOverlayBundle(this.J) != null) {
                        this.f323g.getOverlayBundle(this.J).removeAll(true);
                    }
                    this.f323g.destroyAMapEngine();
                    this.f323g = null;
                    ey.a(ex.f796c, "destroy engine complete");
                }
                if (EGL14.eglGetCurrentContext() != EGL14.EGL_NO_CONTEXT) {
                    this.L.d();
                }
                if (this.bb != null) {
                    this.bb.a();
                }
            }
            this.aA = false;
            this.aB = false;
            this.aD = false;
        } finally {
            try {
            } finally {
            }
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public Context getContext() {
        return this.f322f;
    }

    public int a(int i, Rect rect, int i2, int i3) {
        GLMapEngine gLMapEngine = this.f323g;
        if (gLMapEngine == null || i < 0 || rect == null) {
            return 0;
        }
        int engineIDWithType = gLMapEngine.getEngineIDWithType(i);
        if (!this.f323g.isEngineCreated(engineIDWithType)) {
            int i4 = this.f322f.getResources().getDisplayMetrics().densityDpi;
            float f2 = this.f322f.getResources().getDisplayMetrics().density;
            this.av = GLMapState.calMapZoomScalefactor(i2, i3, i4);
            GLMapEngine.MapViewInitParam mapViewInitParam = new GLMapEngine.MapViewInitParam();
            mapViewInitParam.engineId = engineIDWithType;
            mapViewInitParam.x = rect.left;
            mapViewInitParam.y = rect.top;
            mapViewInitParam.width = rect.width();
            mapViewInitParam.height = rect.height();
            mapViewInitParam.screenWidth = i2;
            mapViewInitParam.screenHeight = i3;
            mapViewInitParam.screenScale = f2;
            mapViewInitParam.textScale = this.aw * f2;
            mapViewInitParam.mapZoomScale = this.av;
            mapViewInitParam.taskThreadCount = 3;
            this.f323g.createAMapEngineWithFrame(mapViewInitParam);
            GLMapState mapState = this.f323g.getMapState(engineIDWithType);
            mapState.setMapZoomer(this.f319c.getSZ());
            mapState.setCameraDegree(this.f319c.getSC());
            mapState.setMapAngle(this.f319c.getSR());
            mapState.setMapGeoCenter(this.f319c.getSX(), this.f319c.getSY());
            this.f323g.setMapState(engineIDWithType, mapState);
            this.f323g.setOvelayBundle(engineIDWithType, new GLOverlayBundle<>(engineIDWithType, this));
            return engineIDWithType;
        }
        a(engineIDWithType, rect.left, rect.top, rect.width(), rect.height(), i2, i3);
        return engineIDWithType;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public int getEngineIDWithGestureInfo(EAMapPlatformGestureInfo eAMapPlatformGestureInfo) {
        GLMapEngine gLMapEngine = this.f323g;
        if (gLMapEngine != null) {
            return gLMapEngine.getEngineIDWithGestureInfo(eAMapPlatformGestureInfo);
        }
        return 1;
    }

    public void a(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        GLMapEngine gLMapEngine = this.f323g;
        if (gLMapEngine != null) {
            gLMapEngine.setServiceViewRect(i, i2, i3, i4, i5, i6, i7);
        }
    }

    private boolean a(int i, int i2) {
        GLMapEngine gLMapEngine = this.f323g;
        if (gLMapEngine != null) {
            return gLMapEngine.getSrvViewStateBoolValue(i, i2);
        }
        return false;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public CameraPosition getCameraPosition() throws RemoteException {
        return getCameraPositionPrj(this.N);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public float getMaxZoomLevel() {
        try {
            if (this.f319c != null) {
                return this.f319c.getMaxZoomLevel();
            }
            return 20.0f;
        } catch (Throwable th) {
            er.a(th);
            return 20.0f;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public float getMinZoomLevel() {
        try {
            if (this.f319c != null) {
                return this.f319c.getMinZoomLevel();
            }
            return 3.0f;
        } catch (Throwable th) {
            er.a(th);
            return 3.0f;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void moveCamera(CameraUpdate cameraUpdate) throws RemoteException {
        if (cameraUpdate == null) {
            return;
        }
        try {
            moveCamera(cameraUpdate.getCameraUpdateFactoryDelegate());
        } catch (Throwable th) {
            er.a(th);
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void moveCamera(AbstractCameraUpdateMessage abstractCameraUpdateMessage) throws RemoteException {
        GLMapEngine gLMapEngine = this.f323g;
        if (gLMapEngine == null || this.K) {
            return;
        }
        try {
            if (this.M && gLMapEngine.getStateMessageCount() > 0) {
                AbstractCameraUpdateMessage abstractCameraUpdateMessageC = ah.c();
                abstractCameraUpdateMessageC.nowType = AbstractCameraUpdateMessage.Type.changeGeoCenterZoomTiltBearing;
                abstractCameraUpdateMessageC.geoPoint = new DPoint(this.f319c.getSX(), this.f319c.getSY());
                abstractCameraUpdateMessageC.zoom = this.f319c.getSZ();
                abstractCameraUpdateMessageC.bearing = this.f319c.getSR();
                abstractCameraUpdateMessageC.tilt = this.f319c.getSC();
                this.f323g.addMessage(abstractCameraUpdateMessage, false);
                while (this.f323g.getStateMessageCount() > 0) {
                    AbstractCameraUpdateMessage stateMessage = this.f323g.getStateMessage();
                    if (stateMessage != null) {
                        stateMessage.mergeCameraUpdateDelegate(abstractCameraUpdateMessageC);
                    }
                }
                abstractCameraUpdateMessage = abstractCameraUpdateMessageC;
            }
        } catch (Throwable th) {
            er.a(th);
        }
        resetRenderTime();
        this.f323g.clearAnimations(1, false);
        abstractCameraUpdateMessage.isChangeFinished = true;
        a(abstractCameraUpdateMessage);
        this.f323g.addMessage(abstractCameraUpdateMessage, false);
    }

    private void a(AbstractCameraUpdateMessage abstractCameraUpdateMessage) {
        boolean z = this.N;
        abstractCameraUpdateMessage.isUseAnchor = z;
        if (z) {
            abstractCameraUpdateMessage.anchorX = this.f319c.getAnchorX();
            abstractCameraUpdateMessage.anchorY = this.f319c.getAnchorY();
        }
        if (abstractCameraUpdateMessage.width == 0) {
            abstractCameraUpdateMessage.width = getMapWidth();
        }
        if (abstractCameraUpdateMessage.height == 0) {
            abstractCameraUpdateMessage.height = getMapHeight();
        }
        abstractCameraUpdateMessage.mapConfig = this.f319c;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void animateCamera(CameraUpdate cameraUpdate) throws RemoteException {
        if (cameraUpdate == null) {
            return;
        }
        animateCamera(cameraUpdate.getCameraUpdateFactoryDelegate());
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void animateCamera(AbstractCameraUpdateMessage abstractCameraUpdateMessage) throws RemoteException {
        animateCameraWithDurationAndCallback(abstractCameraUpdateMessage, 250L, (AMap.CancelableCallback) null);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void animateCameraWithCallback(CameraUpdate cameraUpdate, AMap.CancelableCallback cancelableCallback) throws RemoteException {
        if (cameraUpdate == null) {
            return;
        }
        animateCameraWithDurationAndCallback(cameraUpdate, 250L, cancelableCallback);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void animateCameraWithDurationAndCallback(CameraUpdate cameraUpdate, long j, AMap.CancelableCallback cancelableCallback) {
        if (cameraUpdate == null) {
            return;
        }
        animateCameraWithDurationAndCallback(cameraUpdate.getCameraUpdateFactoryDelegate(), j, cancelableCallback);
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void animateCameraWithDurationAndCallback(AbstractCameraUpdateMessage abstractCameraUpdateMessage, long j, AMap.CancelableCallback cancelableCallback) {
        if (abstractCameraUpdateMessage == null || this.K || this.f323g == null) {
            return;
        }
        abstractCameraUpdateMessage.mCallback = cancelableCallback;
        abstractCameraUpdateMessage.mDuration = j;
        if (this.M || getMapHeight() == 0 || getMapWidth() == 0) {
            try {
                moveCamera(abstractCameraUpdateMessage);
                if (abstractCameraUpdateMessage.mCallback != null) {
                    abstractCameraUpdateMessage.mCallback.onFinish();
                    return;
                }
                return;
            } catch (Throwable th) {
                th.printStackTrace();
                er.a(th);
                return;
            }
        }
        try {
            this.f323g.interruptAnimation();
            resetRenderTime();
            a(abstractCameraUpdateMessage);
            this.f323g.addMessage(abstractCameraUpdateMessage, true);
        } catch (Throwable th2) {
            er.a(th2);
            th2.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void stopAnimation() throws RemoteException {
        try {
            if (this.f323g != null) {
                this.f323g.interruptAnimation();
            }
            resetRenderTime();
        } catch (Throwable th) {
            er.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public Polyline addPolyline(PolylineOptions polylineOptions) throws RemoteException {
        if (polylineOptions == null) {
            return null;
        }
        try {
            resetRenderTime();
            String strCreateId = this.H.createId("POLYLINE");
            return (Polyline) this.H.addOverlayObject(strCreateId, new Polyline(this.H, polylineOptions, strCreateId), polylineOptions);
        } catch (Throwable th) {
            er.a(th);
            ey.a(ex.f797d, "addPolyline failed " + th.getMessage(), polylineOptions);
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public BuildingOverlay addBuildingOverlay() {
        try {
            ep.g(this.f322f);
            String strCreateId = this.H.createId("BUILDINGOVERLAY");
            BuildingOverlay buildingOverlay = new BuildingOverlay(this.H, strCreateId);
            Field declaredField = buildingOverlay.getClass().getDeclaredField("buildingOverlayTotalOptions");
            if (declaredField == null) {
                return null;
            }
            resetRenderTime();
            declaredField.setAccessible(true);
            Object obj = declaredField.get(buildingOverlay);
            return (this.H == null || !(obj instanceof BaseOptions)) ? buildingOverlay : (BuildingOverlay) this.H.addOverlayObject(strCreateId, buildingOverlay, (BaseOptions) obj);
        } catch (Exception e2) {
            e2.printStackTrace();
            er.a(e2);
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public GL3DModel addGLModel(GL3DModelOptions gL3DModelOptions) {
        return this.L.a(gL3DModelOptions);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public ParticleOverlay addParticleOverlay(ParticleOverlayOptions particleOverlayOptions) {
        if (particleOverlayOptions == null) {
            return null;
        }
        try {
            resetRenderTime();
            ep.c(this.f322f);
            String strCreateId = this.H.createId("PARTICLEOVERLAY");
            return (ParticleOverlay) this.H.addOverlayObject(strCreateId, new ParticleOverlay(this.H, particleOverlayOptions, strCreateId), particleOverlayOptions);
        } catch (Throwable th) {
            er.a(th);
            th.printStackTrace();
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public NavigateArrow addNavigateArrow(NavigateArrowOptions navigateArrowOptions) throws RemoteException {
        if (navigateArrowOptions == null) {
            return null;
        }
        try {
            resetRenderTime();
            String strCreateId = this.H.createId("NAVIGATEARROW");
            NavigateArrow navigateArrow = new NavigateArrow(this.H, navigateArrowOptions, strCreateId);
            return this.H != null ? (NavigateArrow) this.H.addOverlayObject(strCreateId, navigateArrow, navigateArrowOptions) : navigateArrow;
        } catch (Throwable th) {
            er.a(th);
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public Polygon addPolygon(PolygonOptions polygonOptions) throws RemoteException {
        if (polygonOptions == null) {
            return null;
        }
        try {
            resetRenderTime();
            String strCreateId = this.H.createId("POLYGON");
            Polygon polygon = new Polygon(this.H, polygonOptions, strCreateId);
            return this.H != null ? (Polygon) this.H.addOverlayObject(strCreateId, polygon, polygonOptions) : polygon;
        } catch (Throwable th) {
            er.a(th);
            ey.a(ex.f797d, "addPolygon failed " + th.getMessage(), polygonOptions);
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public Circle addCircle(CircleOptions circleOptions) throws RemoteException {
        if (circleOptions == null) {
            return null;
        }
        try {
            resetRenderTime();
            String strCreateId = this.H.createId("CIRCLE");
            Circle circle = new Circle(this.H, circleOptions, strCreateId);
            return this.H != null ? (Circle) this.H.addOverlayObject(strCreateId, circle, circleOptions) : circle;
        } catch (Throwable th) {
            er.a(th);
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public Arc addArc(ArcOptions arcOptions) throws RemoteException {
        if (arcOptions == null) {
            return null;
        }
        try {
            resetRenderTime();
            String strCreateId = this.H.createId("ARC");
            Arc arc = new Arc(this.H, arcOptions, strCreateId);
            return this.H != null ? (Arc) this.H.addOverlayObject(strCreateId, arc, arcOptions) : arc;
        } catch (Throwable th) {
            er.a(th);
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public GroundOverlay addGroundOverlay(GroundOverlayOptions groundOverlayOptions) throws RemoteException {
        if (groundOverlayOptions == null) {
            return null;
        }
        try {
            resetRenderTime();
            String strCreateId = this.H.createId("GROUNDOVERLAY");
            GroundOverlay groundOverlay = new GroundOverlay(this.H, groundOverlayOptions, strCreateId);
            return this.H != null ? (GroundOverlay) this.H.addOverlayObject(strCreateId, groundOverlay, groundOverlayOptions) : groundOverlay;
        } catch (Throwable th) {
            er.a(th);
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public MultiPointOverlay addMultiPointOverlay(MultiPointOverlayOptions multiPointOverlayOptions) throws RemoteException {
        try {
            resetRenderTime();
            IMultiPointOverlay iMultiPointOverlayA = this.aL.a(multiPointOverlayOptions);
            if (iMultiPointOverlayA != null) {
                return new MultiPointOverlay(iMultiPointOverlayA);
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public Marker addMarker(MarkerOptions markerOptions) throws RemoteException {
        try {
            resetRenderTime();
            return this.f318b.a(markerOptions);
        } catch (Throwable th) {
            er.a(th);
            ey.a(ex.f797d, "addMarker failed " + th.getMessage(), markerOptions);
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public Text addText(TextOptions textOptions) throws RemoteException {
        try {
            resetRenderTime();
            return this.f318b.a(textOptions);
        } catch (Throwable th) {
            er.a(th);
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public ArrayList<Marker> addMarkers(ArrayList<MarkerOptions> arrayList, boolean z) throws RemoteException {
        try {
            resetRenderTime();
            return this.f318b.a(arrayList, z);
        } catch (Throwable th) {
            er.a(th);
            ey.a(ex.f797d, "addMarkers failed " + th.getMessage(), arrayList);
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public TileOverlay addTileOverlay(TileOverlayOptions tileOverlayOptions) throws RemoteException {
        try {
            synchronized (this.G) {
                if (this.F == null) {
                    this.F = new ab(this.f322f, this);
                }
            }
            if (this.F == null) {
                return null;
            }
            TileProvider tileProvider = tileOverlayOptions.getTileProvider();
            if (tileProvider != null && (tileProvider instanceof HeatmapTileProvider)) {
                ep.a(this.f322f);
            }
            return this.F.a(tileOverlayOptions);
        } catch (Throwable th) {
            er.a(th);
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public HeatMapLayer addHeatMapLayer(HeatMapLayerOptions heatMapLayerOptions) throws RemoteException {
        try {
            resetRenderTime();
            if (heatMapLayerOptions == null) {
                return null;
            }
            String strCreateId = this.H.createId("HEATMAPLAYER");
            return (HeatMapLayer) this.H.addOverlayObject(strCreateId, new HeatMapLayer(this.H, heatMapLayerOptions, strCreateId), heatMapLayerOptions);
        } catch (Throwable th) {
            er.a(th);
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void clear() throws RemoteException {
        try {
            clear(false);
        } catch (Throwable th) {
            hn.c(th, "AMapDelegateImp", "clear");
            er.a(th);
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void clear(boolean z) throws RemoteException {
        try {
            hideInfoWindow();
            String strD = null;
            String strE = "";
            if (this.P != null) {
                if (z) {
                    strD = this.P.d();
                    strE = this.P.e();
                } else {
                    this.P.f();
                }
            }
            this.H.clear(strE);
            if (this.F != null) {
                this.F.c();
            }
            this.f318b.b(strD);
            this.L.b();
            if (this.E != null) {
                this.E.j();
            }
            if (this.aL != null) {
                this.aL.c();
            }
            queueEvent(new Runnable() { // from class: com.amap.api.mapcore.util.c.17
                @Override // java.lang.Runnable
                public void run() {
                    if (c.this.f323g == null || c.this.K) {
                        return;
                    }
                    c.this.f323g.removeNativeAllOverlay(c.this.J);
                }
            });
            resetRenderTime();
        } catch (Throwable th) {
            hn.c(th, "AMapDelegateImp", "clear");
            er.a(th);
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public int getMapType() throws RemoteException {
        return this.ab;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setMapType(int i) throws RemoteException {
        MapConfig mapConfig;
        if (i != this.ab || ((mapConfig = this.f319c) != null && mapConfig.isCustomStyleEnable())) {
            this.ab = i;
            g(this.ab);
        }
    }

    public void g(int i) {
        int i2;
        int i3;
        int i4;
        this.ab = i;
        if (i == 1) {
            i3 = 0;
            i4 = 0;
            i2 = i4;
        } else if (i == 2) {
            i3 = 1;
            i4 = 0;
            i2 = i4;
        } else if (i == 3) {
            i2 = 4;
            i4 = 1;
            i3 = 0;
        } else if (i == 4) {
            i2 = 4;
            i3 = 0;
            i4 = 0;
        } else if (i == 5) {
            i2 = 5;
            i3 = 2;
            i4 = 0;
        } else {
            try {
                this.ab = 1;
                i3 = 0;
                i4 = 0;
                i2 = i4;
            } catch (Throwable th) {
                hn.c(th, "AMapDelegateImp", "setMaptype");
                th.printStackTrace();
                er.a(th);
                return;
            }
        }
        this.f319c.setMapStyleMode(i3);
        this.f319c.setMapStyleTime(i4);
        this.f319c.setMapStyleState(i2);
        if (this.f319c.isCustomStyleEnable()) {
            if (this.aM != null && this.aM.d()) {
                this.aM.e();
            } else {
                a(1, i3, i4, i2, true, false, (StyleItem[]) null);
                this.f319c.setCustomStyleEnable(false);
            }
            this.B.setLogoEnable(true);
        } else {
            if (this.f319c.getMapLanguage().equals("en")) {
                setMapLanguage("zh_cn");
            }
            b(1, i3, i4, i2);
        }
        resetRenderTime();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public boolean isTrafficEnabled() throws RemoteException {
        return this.f319c.isTrafficEnabled();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setTrafficEnabled(final boolean z) throws RemoteException {
        try {
            if (this.aA && !this.K) {
                queueEvent(new Runnable() { // from class: com.amap.api.mapcore.util.c.18
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            if (c.this.f319c.isTrafficEnabled() != z) {
                                c.this.f319c.setTrafficEnabled(z);
                                c.this.ar.setTrafficMode(z);
                                boolean z2 = z;
                                c.this.f323g.setTrafficEnable(1, z);
                                c.this.resetRenderTime();
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                            er.a(th);
                        }
                    }
                });
            } else {
                this.aO.f391c = z;
                this.aO.f390b = true;
                this.aO.f395g = 1;
            }
        } catch (Throwable th) {
            er.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public boolean isIndoorEnabled() throws RemoteException {
        return this.f319c.isIndoorEnable();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setIndoorEnabled(final boolean z) throws RemoteException {
        List listA;
        try {
            if (this.aA && !this.K) {
                this.f319c.setIndoorEnable(z);
                resetRenderTime();
                if (z) {
                    if (this.f323g != null) {
                        this.f323g.setIndoorEnable(1, true);
                    }
                } else {
                    if (this.f323g != null) {
                        this.f323g.setIndoorEnable(1, false);
                    }
                    this.f319c.maxZoomLevel = this.f319c.isSetLimitZoomLevel() ? this.f319c.getMaxZoomLevel() : 20.0f;
                    try {
                        if (this.B.isZoomControlsEnabled() && (listA = this.v.a(AMapWidgetListener.class.hashCode())) != null && listA.size() > 0) {
                            synchronized (listA) {
                                for (int i = 0; i < listA.size(); i++) {
                                    ((AMapWidgetListener) listA.get(i)).invalidateZoomController(this.f319c.getSZ());
                                }
                            }
                        }
                    } catch (Throwable unused) {
                    }
                }
                ep.c(this.f322f, z);
                if (this.B.isIndoorSwitchEnabled()) {
                    this.j.post(new Runnable() { // from class: com.amap.api.mapcore.util.c.19
                        @Override // java.lang.Runnable
                        public void run() {
                            if (!z) {
                                if (c.this.E != null) {
                                    c.this.E.i(false);
                                    return;
                                }
                                return;
                            }
                            c.this.showIndoorSwitchControlsEnabled(true);
                        }
                    });
                    return;
                }
                return;
            }
            this.aX.f391c = z;
            this.aX.f390b = true;
            this.aX.f395g = 1;
        } catch (Throwable th) {
            er.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void set3DBuildingEnabled(boolean z) throws RemoteException {
        try {
            c(1);
            a(1, z);
            d(1);
        } catch (Throwable th) {
            er.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public boolean isMyLocationEnabled() throws RemoteException {
        return this.I;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setMyLocationEnabled(boolean z) throws RemoteException {
        if (this.K) {
            return;
        }
        try {
            if (this.E != null) {
                this.E.f();
                if (this.Q == null) {
                    this.E.h(false);
                } else if (z) {
                    this.Q.activate(this.u);
                    this.E.h(true);
                    if (this.P == null) {
                        this.P = new cv(this, this.f322f);
                    }
                } else {
                    if (this.P != null) {
                        this.P.c();
                        this.P = null;
                    }
                    this.Q.deactivate();
                }
            }
            if (!z) {
                this.B.setMyLocationButtonEnabled(z);
            }
            this.I = z;
            resetRenderTime();
        } catch (Throwable th) {
            hn.c(th, "AMapDelegateImp", "setMyLocationEnabled");
            th.printStackTrace();
            er.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setLoadOfflineData(final boolean z) throws RemoteException {
        queueEvent(new Runnable() { // from class: com.amap.api.mapcore.util.c.20
            @Override // java.lang.Runnable
            public void run() {
                if (c.this.f323g != null) {
                    c.this.f323g.setOfflineDataEnable(1, z);
                }
            }
        });
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setMyLocationStyle(MyLocationStyle myLocationStyle) throws RemoteException {
        if (this.K) {
            return;
        }
        try {
            if (this.P == null) {
                this.P = new cv(this, this.f322f);
            }
            if (this.P != null) {
                long j = 1000;
                if (myLocationStyle.getInterval() < j) {
                    myLocationStyle.interval(j);
                }
                if (this.Q != null && (this.Q instanceof as)) {
                    ((as) this.Q).a(myLocationStyle.getInterval());
                    ((as) this.Q).a(myLocationStyle.getMyLocationType());
                }
                this.P.a(myLocationStyle);
            }
        } catch (Throwable th) {
            er.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setMyLocationType(int i) throws RemoteException {
        try {
            if (this.P == null || this.P.a() == null) {
                return;
            }
            this.P.a().myLocationType(i);
            setMyLocationStyle(this.P.a());
        } catch (Throwable th) {
            er.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public List<Marker> getMapScreenMarkers() throws RemoteException {
        if (!er.b(getMapWidth(), getMapHeight())) {
            return new ArrayList();
        }
        return this.f318b.e();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setMapTextEnable(final boolean z) throws RemoteException {
        try {
            if (this.aA && this.aB) {
                resetRenderTime();
                queueEvent(new Runnable() { // from class: com.amap.api.mapcore.util.c.22
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            c.this.f323g.setLabelEnable(1, z);
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
            } else {
                this.aT.f391c = z;
                this.aT.f390b = true;
                this.aT.f395g = 1;
            }
        } catch (Throwable th) {
            er.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setRoadArrowEnable(final boolean z) throws RemoteException {
        try {
            if (this.aA && this.aB) {
                resetRenderTime();
                queueEvent(new Runnable() { // from class: com.amap.api.mapcore.util.c.23
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            c.this.f323g.setRoadArrowEnable(1, z);
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
            } else {
                this.aU.f391c = z;
                this.aU.f390b = true;
                this.aU.f395g = 1;
            }
        } catch (Throwable th) {
            er.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setNaviLabelEnable(final boolean z, final int i, final int i2) throws RemoteException {
        try {
            if (this.aA && this.aB) {
                resetRenderTime();
                queueEvent(new Runnable() { // from class: com.amap.api.mapcore.util.c.24
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            c.this.f323g.setNaviLabelEnable(1, z, i, i2);
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
            } else {
                this.aV.f391c = z;
                this.aV.f396h = i;
                this.aV.i = i2;
                this.aV.f390b = true;
                this.aV.f395g = 1;
            }
        } catch (Throwable th) {
            er.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setMyTrafficStyle(MyTrafficStyle myTrafficStyle) throws RemoteException {
        if (this.K) {
            return;
        }
        try {
            this.ac = myTrafficStyle;
            if (this.aA && this.aB && myTrafficStyle != null) {
                resetRenderTime();
                queueEvent(new Runnable() { // from class: com.amap.api.mapcore.util.c.25
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            c.this.f323g.setTrafficStyle(1, c.this.ac.getSmoothColor(), c.this.ac.getSlowColor(), c.this.ac.getCongestedColor(), c.this.ac.getSeriousCongestedColor());
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
            } else {
                this.ba.f391c = false;
                this.ba.f390b = true;
                this.ba.f395g = 1;
            }
        } catch (Throwable th) {
            er.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public Location getMyLocation() throws RemoteException {
        if (this.Q != null) {
            return this.u.f1004a;
        }
        return null;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setLocationSource(LocationSource locationSource) throws RemoteException {
        try {
            if (this.K) {
                return;
            }
            if (this.Q != null && (this.Q instanceof as)) {
                this.Q.deactivate();
            }
            this.Q = locationSource;
            if (locationSource != null) {
                this.E.h(true);
            } else {
                this.E.h(false);
            }
        } catch (Throwable th) {
            hn.c(th, "AMapDelegateImp", "setLocationSource");
            th.printStackTrace();
            er.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setMyLocationRotateAngle(float f2) throws RemoteException {
        try {
            if (this.P != null) {
                this.P.a(f2);
            }
        } catch (Throwable th) {
            er.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public UiSettings getAMapUiSettings() throws RemoteException {
        if (this.z == null) {
            this.z = new UiSettings(this.B);
        }
        return this.z;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public Projection getAMapProjection() throws RemoteException {
        return new Projection(this.A);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setOnMapClickListener(AMap.OnMapClickListener onMapClickListener) throws RemoteException {
        h hVar = this.v;
        if (hVar != null) {
            hVar.a(AMap.OnMapClickListener.class.hashCode(), onMapClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setOnMapTouchListener(AMap.OnMapTouchListener onMapTouchListener) throws RemoteException {
        h hVar = this.v;
        if (hVar != null) {
            hVar.a(AMap.OnMapTouchListener.class.hashCode(), onMapTouchListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setOnPOIClickListener(AMap.OnPOIClickListener onPOIClickListener) throws RemoteException {
        h hVar = this.v;
        if (hVar != null) {
            hVar.a(AMap.OnPOIClickListener.class.hashCode(), onPOIClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setOnMapLongClickListener(AMap.OnMapLongClickListener onMapLongClickListener) throws RemoteException {
        h hVar = this.v;
        if (hVar != null) {
            hVar.a(AMap.OnMapLongClickListener.class.hashCode(), onMapLongClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setOnMarkerClickListener(AMap.OnMarkerClickListener onMarkerClickListener) throws RemoteException {
        h hVar = this.v;
        if (hVar != null) {
            hVar.a(AMap.OnMarkerClickListener.class.hashCode(), onMarkerClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setOnPolylineClickListener(AMap.OnPolylineClickListener onPolylineClickListener) throws RemoteException {
        h hVar = this.v;
        if (hVar != null) {
            hVar.a(AMap.OnPolylineClickListener.class.hashCode(), onPolylineClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setOnMarkerDragListener(AMap.OnMarkerDragListener onMarkerDragListener) throws RemoteException {
        h hVar = this.v;
        if (hVar != null) {
            hVar.a(AMap.OnMarkerDragListener.class.hashCode(), onMarkerDragListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setOnMaploadedListener(AMap.OnMapLoadedListener onMapLoadedListener) throws RemoteException {
        h hVar = this.v;
        if (hVar != null) {
            hVar.a(AMap.OnMapLoadedListener.class.hashCode(), onMapLoadedListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setOnCameraChangeListener(AMap.OnCameraChangeListener onCameraChangeListener) throws RemoteException {
        h hVar = this.v;
        if (hVar != null) {
            hVar.a(AMap.OnCameraChangeListener.class.hashCode(), onCameraChangeListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setOnInfoWindowClickListener(AMap.OnInfoWindowClickListener onInfoWindowClickListener) throws RemoteException {
        h hVar = this.v;
        if (hVar != null) {
            hVar.a(AMap.OnInfoWindowClickListener.class.hashCode(), onInfoWindowClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setOnIndoorBuildingActiveListener(AMap.OnIndoorBuildingActiveListener onIndoorBuildingActiveListener) throws RemoteException {
        h hVar = this.v;
        if (hVar != null) {
            hVar.a(AMap.OnIndoorBuildingActiveListener.class.hashCode(), onIndoorBuildingActiveListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setOnMyLocationChangeListener(AMap.OnMyLocationChangeListener onMyLocationChangeListener) {
        h hVar = this.v;
        if (hVar != null) {
            hVar.a(AMap.OnMyLocationChangeListener.class.hashCode(), onMyLocationChangeListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setInfoWindowAdapter(AMap.InfoWindowAdapter infoWindowAdapter) throws RemoteException {
        ar arVar;
        if (this.K || (arVar = this.x) == null) {
            return;
        }
        arVar.a(infoWindowAdapter);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setInfoWindowAdapter(AMap.CommonInfoWindowAdapter commonInfoWindowAdapter) throws RemoteException {
        ar arVar;
        if (this.K || (arVar = this.x) == null) {
            return;
        }
        arVar.a(commonInfoWindowAdapter);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setOnMultiPointClickListener(AMap.OnMultiPointClickListener onMultiPointClickListener) {
        ax axVar = this.aL;
        if (axVar != null) {
            axVar.a(onMultiPointClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public String getMapContentApprovalNumber() {
        MapConfig mapConfig = this.f319c;
        if (mapConfig == null || mapConfig.isCustomStyleEnable()) {
            return null;
        }
        ep.d(this.f322f);
        String strA = eh.a(this.f322f, "approval_number", "mc", "");
        return !TextUtils.isEmpty(strA) ? strA : "GS(2019)6378号 | GS(2017)2550号";
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public String getSatelliteImageApprovalNumber() {
        ep.e(this.f322f);
        String strA = eh.a(this.f322f, "approval_number", "si", "");
        return !TextUtils.isEmpty(strA) ? strA : "GS(2020)617号";
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setMapLanguage(String str) {
        MapConfig mapConfig;
        if (TextUtils.isEmpty(str) || (mapConfig = this.f319c) == null || mapConfig.isCustomStyleEnable() || this.f319c.getMapLanguage().equals(str)) {
            return;
        }
        if (!str.equals("en")) {
            this.f319c.setMapLanguage("zh_cn");
            this.ai = 0;
        } else {
            if (this.ab != 1) {
                try {
                    setMapType(1);
                } catch (Throwable th) {
                    er.a(th);
                    th.printStackTrace();
                }
            }
            this.f319c.setMapLanguage("en");
            this.ai = -10000;
        }
        try {
            a(getCameraPosition());
            synchronized (this.G) {
                if (this.F == null) {
                    this.F = new ab(this.f322f, this);
                }
            }
            if (this.F != null) {
                this.F.a(this.f319c.getMapLanguage());
            }
        } catch (Throwable th2) {
            er.a(th2);
            th2.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setWorldVectorMapStyle(String str) {
        if (a(false, true) || TextUtils.isEmpty(str) || this.f319c == null || this.bd.equals(str)) {
            return;
        }
        this.bd = str;
        jx jxVar = this.bb;
        if (jxVar != null) {
            jxVar.a("setWorldVectorMapStyle", str);
        }
        resetRenderTime();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public String getWorldVectorMapStyle() {
        return this.bd;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public String getCurrentWorldVectorMapStyle() {
        try {
            if (this.bb == null) {
                return "";
            }
            Object objA = this.bb.a("getCurMapStyleKey");
            return objA instanceof String ? (String) objA : "";
        } catch (Throwable th) {
            er.a(th);
            return "";
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void accelerateNetworkInChinese(boolean z) {
        jx jxVar = this.bb;
        if (jxVar != null) {
            jxVar.a("accelerateNetworkInChinese", Boolean.valueOf(z));
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public String getWorldVectorMapLanguage() {
        return this.bc;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void getMapPrintScreen(AMap.onMapPrintScreenListener onmapprintscreenlistener) {
        try {
            this.v.a(Integer.valueOf(AMap.onMapPrintScreenListener.class.hashCode()), onmapprintscreenlistener);
            this.Y = true;
            resetRenderTime();
        } catch (Throwable th) {
            er.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void getMapScreenShot(AMap.OnMapScreenShotListener onMapScreenShotListener) {
        try {
            this.v.a(Integer.valueOf(AMap.OnMapScreenShotListener.class.hashCode()), onMapScreenShotListener);
            this.Y = true;
            resetRenderTime();
        } catch (Throwable th) {
            er.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public float getScalePerPixel() throws RemoteException {
        try {
            return ((float) ((((Math.cos((getCameraPosition().target.latitude * 3.141592653589793d) / 180.0d) * 2.0d) * 3.141592653589793d) * 6378137.0d) / (Math.pow(2.0d, getZoomLevel()) * 256.0d))) * getMapZoomScale();
        } catch (Throwable th) {
            hn.c(th, "AMapDelegateImp", "getScalePerPixel");
            er.a(th);
            th.printStackTrace();
            return 0.0f;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setRunLowFrame(boolean z) {
        if (z) {
            return;
        }
        try {
            if (this.am != -1) {
                return;
            }
            c();
        } catch (Throwable th) {
            er.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void removecache() throws RemoteException {
        removecache(null);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void removecache(AMap.OnCacheRemoveListener onCacheRemoveListener) throws RemoteException {
        if (this.j == null || this.f323g == null) {
            return;
        }
        try {
            d dVar = new d(this.f322f, onCacheRemoveListener);
            this.j.removeCallbacks(dVar);
            this.j.post(dVar);
        } catch (Throwable th) {
            hn.c(th, "AMapDelegateImp", "removecache");
            th.printStackTrace();
            er.a(th);
        }
    }

    /* JADX INFO: compiled from: AMapDelegateImp.java */
    private class d implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Context f404b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private AMap.OnCacheRemoveListener f405c;

        public d(Context context, AMap.OnCacheRemoveListener onCacheRemoveListener) {
            this.f404b = context;
            this.f405c = onCacheRemoveListener;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0037  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0041  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                r6 = this;
                r0 = 0
                r1 = 1
                android.content.Context r2 = r6.f404b     // Catch: java.lang.Throwable -> L63
                android.content.Context r2 = r2.getApplicationContext()     // Catch: java.lang.Throwable -> L63
                java.lang.String r3 = com.amap.api.mapcore.util.er.c(r2)     // Catch: java.lang.Throwable -> L63
                java.lang.String r4 = com.amap.api.mapcore.util.er.a(r2)     // Catch: java.lang.Throwable -> L63
                java.io.File r5 = new java.io.File     // Catch: java.lang.Throwable -> L63
                r5.<init>(r3)     // Catch: java.lang.Throwable -> L63
                boolean r3 = r5.exists()     // Catch: java.lang.Throwable -> L63
                if (r3 == 0) goto L24
                boolean r3 = com.autonavi.base.amap.mapcore.FileUtil.deleteFile(r5)     // Catch: java.lang.Throwable -> L63
                if (r3 == 0) goto L22
                goto L24
            L22:
                r3 = r0
                goto L25
            L24:
                r3 = r1
            L25:
                if (r3 == 0) goto L37
                java.io.File r5 = new java.io.File     // Catch: java.lang.Throwable -> L34
                r5.<init>(r4)     // Catch: java.lang.Throwable -> L34
                boolean r3 = com.autonavi.base.amap.mapcore.FileUtil.deleteFile(r5)     // Catch: java.lang.Throwable -> L34
                if (r3 == 0) goto L37
                r3 = r1
                goto L38
            L34:
                r2 = move-exception
                r1 = r3
                goto L64
            L37:
                r3 = r0
            L38:
                if (r3 == 0) goto L41
                boolean r2 = com.amap.api.mapcore.util.er.e(r2)     // Catch: java.lang.Throwable -> L34
                if (r2 == 0) goto L41
                goto L42
            L41:
                r1 = r0
            L42:
                com.amap.api.mapcore.util.c r2 = com.amap.api.mapcore.util.c.this     // Catch: java.lang.Throwable -> L63
                com.amap.api.mapcore.util.ab r2 = com.amap.api.mapcore.util.c.e(r2)     // Catch: java.lang.Throwable -> L63
                if (r2 == 0) goto L53
                com.amap.api.mapcore.util.c r2 = com.amap.api.mapcore.util.c.this     // Catch: java.lang.Throwable -> L63
                com.amap.api.mapcore.util.ab r2 = com.amap.api.mapcore.util.c.e(r2)     // Catch: java.lang.Throwable -> L63
                r2.i()     // Catch: java.lang.Throwable -> L63
            L53:
                com.amap.api.mapcore.util.c r0 = com.amap.api.mapcore.util.c.this     // Catch: java.lang.Throwable -> L7e
                com.autonavi.base.ae.gmap.GLMapEngine r0 = r0.f323g     // Catch: java.lang.Throwable -> L7e
                if (r0 == 0) goto L82
                com.amap.api.maps.AMap$OnCacheRemoveListener r0 = r6.f405c     // Catch: java.lang.Throwable -> L7e
                if (r0 == 0) goto L82
                com.amap.api.maps.AMap$OnCacheRemoveListener r0 = r6.f405c     // Catch: java.lang.Throwable -> L7e
                r0.onRemoveCacheFinish(r1)     // Catch: java.lang.Throwable -> L7e
                goto L82
            L63:
                r2 = move-exception
            L64:
                com.amap.api.mapcore.util.er.a(r2)     // Catch: java.lang.Throwable -> L83
                java.lang.String r3 = "AMapDelegateImp"
                java.lang.String r4 = "RemoveCacheRunnable"
                com.amap.api.mapcore.util.hn.c(r2, r3, r4)     // Catch: java.lang.Throwable -> L83
                com.amap.api.mapcore.util.c r1 = com.amap.api.mapcore.util.c.this     // Catch: java.lang.Throwable -> L7e
                com.autonavi.base.ae.gmap.GLMapEngine r1 = r1.f323g     // Catch: java.lang.Throwable -> L7e
                if (r1 == 0) goto L82
                com.amap.api.maps.AMap$OnCacheRemoveListener r1 = r6.f405c     // Catch: java.lang.Throwable -> L7e
                if (r1 == 0) goto L82
                com.amap.api.maps.AMap$OnCacheRemoveListener r1 = r6.f405c     // Catch: java.lang.Throwable -> L7e
                r1.onRemoveCacheFinish(r0)     // Catch: java.lang.Throwable -> L7e
                goto L82
            L7e:
                r0 = move-exception
                r0.printStackTrace()
            L82:
                return
            L83:
                r0 = move-exception
                com.amap.api.mapcore.util.c r2 = com.amap.api.mapcore.util.c.this     // Catch: java.lang.Throwable -> L94
                com.autonavi.base.ae.gmap.GLMapEngine r2 = r2.f323g     // Catch: java.lang.Throwable -> L94
                if (r2 == 0) goto L98
                com.amap.api.maps.AMap$OnCacheRemoveListener r2 = r6.f405c     // Catch: java.lang.Throwable -> L94
                if (r2 == 0) goto L98
                com.amap.api.maps.AMap$OnCacheRemoveListener r2 = r6.f405c     // Catch: java.lang.Throwable -> L94
                r2.onRemoveCacheFinish(r1)     // Catch: java.lang.Throwable -> L94
                goto L98
            L94:
                r1 = move-exception
                r1.printStackTrace()
            L98:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.c.d.run():void");
        }

        public boolean equals(Object obj) {
            return obj instanceof d;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setCustomRenderer(CustomRenderer customRenderer) throws RemoteException {
        this.aj = customRenderer;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setCenterToPixel(int i, int i2) throws RemoteException {
        this.N = true;
        this.aG = i;
        this.aH = i2;
        if (this.aB && this.aA) {
            if (this.f319c.getAnchorX() == this.aG && this.f319c.getAnchorY() == this.aH) {
                return;
            }
            this.f319c.setAnchorX(this.aG);
            this.f319c.setAnchorY(this.aH);
            queueEvent(new Runnable() { // from class: com.amap.api.mapcore.util.c.26
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        c.this.f319c.setAnchorX(Math.max(0, Math.min(c.this.aG, c.this.f324h)));
                        c.this.f319c.setAnchorY(Math.max(0, Math.min(c.this.aH, c.this.i)));
                        c.this.f323g.setProjectionCenter(1, c.this.f319c.getAnchorX(), c.this.f319c.getAnchorY());
                        c.this.ah = true;
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setMapTextZIndex(int i) throws RemoteException {
        this.ai = i;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public int getMapTextZIndex() throws RemoteException {
        return this.ai;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setRenderFps(int i) {
        try {
            if (i == -1) {
                this.am = i;
            } else {
                this.am = Math.max(10, Math.min(i, 40));
            }
            ep.f(this.f322f);
        } catch (Throwable th) {
            er.a(th);
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setIndoorBuildingInfo(IndoorBuildingInfo indoorBuildingInfo) throws RemoteException {
        if (this.K || indoorBuildingInfo == null || indoorBuildingInfo.activeFloorName == null || indoorBuildingInfo.poiid == null) {
            return;
        }
        this.f320d = (aq) indoorBuildingInfo;
        resetRenderTime();
        queueEvent(new Runnable() { // from class: com.amap.api.mapcore.util.c.27
            @Override // java.lang.Runnable
            public void run() {
                if (c.this.f323g != null) {
                    c.this.f323g.setIndoorBuildingToBeActive(1, c.this.f320d.activeFloorName, c.this.f320d.activeFloorIndex, c.this.f320d.poiid);
                }
            }
        });
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setAMapGestureListener(AMapGestureListener aMapGestureListener) {
        p pVar = this.as;
        if (pVar != null) {
            this.w = aMapGestureListener;
            pVar.a(aMapGestureListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public float getZoomToSpanLevel(LatLng latLng, LatLng latLng2) {
        try {
            MapConfig mapConfig = getMapConfig();
            if (latLng != null && latLng2 != null && this.aA && !this.K) {
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                builder.include(latLng);
                builder.include(latLng2);
                GLMapState gLMapState = new GLMapState(1, this.f323g.getNativeInstance());
                Pair<Float, IPoint> pairA = er.a(mapConfig, 0, 0, 0, 0, builder.build(), getMapWidth(), getMapHeight());
                gLMapState.recycle();
                if (pairA != null) {
                    return ((Float) pairA.first).floatValue();
                }
                return gLMapState.getMapZoomer();
            }
            return mapConfig.getSZ();
        } catch (Throwable th) {
            er.a(th);
            return 0.0f;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public Pair<Float, LatLng> calculateZoomToSpanLevel(int i, int i2, int i3, int i4, LatLng latLng, LatLng latLng2) {
        if (latLng != null && latLng2 != null && i == i2 && i2 == i3 && i3 == i4 && latLng.latitude == latLng2.latitude && latLng.longitude == latLng2.longitude) {
            return new Pair<>(Float.valueOf(getMaxZoomLevel()), latLng);
        }
        MapConfig mapConfig = getMapConfig();
        if (latLng != null && latLng2 != null && this.aA && !this.K) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            builder.include(latLng);
            builder.include(latLng2);
            GLMapState gLMapState = new GLMapState(1, this.f323g.getNativeInstance());
            Pair<Float, IPoint> pairA = er.a(mapConfig, i, i2, i3, i4, builder.build(), getMapWidth(), getMapHeight());
            gLMapState.recycle();
            if (pairA == null) {
                return null;
            }
            DPoint dPointObtain = DPoint.obtain();
            GLMapState.geo2LonLat(((IPoint) pairA.second).x, ((IPoint) pairA.second).y, dPointObtain);
            Pair<Float, LatLng> pair = new Pair<>(pairA.first, new LatLng(dPointObtain.y, dPointObtain.x));
            dPointObtain.recycle();
            return pair;
        }
        DPoint dPointObtain2 = DPoint.obtain();
        GLMapState.geo2LonLat((int) mapConfig.getSX(), (int) mapConfig.getSY(), dPointObtain2);
        Pair<Float, LatLng> pair2 = new Pair<>(Float.valueOf(mapConfig.getSZ()), new LatLng(dPointObtain2.y, dPointObtain2.x));
        dPointObtain2.recycle();
        return pair2;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public InfoWindowAnimationManager getInfoWindowAnimationManager() {
        return new InfoWindowAnimationManager(this.y);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setMaskLayerParams(int i, int i2, int i3, int i4, final int i5, long j) {
        GLAlphaAnimation gLAlphaAnimation;
        try {
            if (this.ak != null) {
                float f2 = i4 / 255.0f;
                if (i5 == -1) {
                    gLAlphaAnimation = new GLAlphaAnimation(f2, 0.0f);
                    gLAlphaAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.amap.api.mapcore.util.c.28
                        @Override // com.amap.api.maps.model.animation.Animation.AnimationListener
                        public void onAnimationStart() {
                        }

                        @Override // com.amap.api.maps.model.animation.Animation.AnimationListener
                        public void onAnimationEnd() {
                            c.this.j.post(new Runnable() { // from class: com.amap.api.mapcore.util.c.28.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    c.this.al = i5;
                                    if (c.this.E != null) {
                                        c.this.E.j(true);
                                    }
                                }
                            });
                        }
                    });
                } else {
                    this.al = i5;
                    gLAlphaAnimation = new GLAlphaAnimation(0.0f, f2);
                    if (f2 > 0.2f) {
                        if (this.E != null) {
                            this.E.j(false);
                        }
                    } else if (this.E != null) {
                        this.E.j(true);
                    }
                }
                gLAlphaAnimation.setInterpolator(new LinearInterpolator());
                gLAlphaAnimation.setDuration(j);
                this.ak.a(i, i2, i3, i4);
                this.ak.a(gLAlphaAnimation);
            }
        } catch (Throwable th) {
            er.a(th);
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setMaxZoomLevel(float f2) {
        this.f319c.setMaxZoomLevel(f2);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setMinZoomLevel(float f2) {
        this.f319c.setMinZoomLevel(f2);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void resetMinMaxZoomPreference() {
        List listA;
        this.f319c.resetMinMaxZoomPreference();
        try {
            if (!this.B.isZoomControlsEnabled() || !this.f319c.isNeedUpdateZoomControllerState() || (listA = this.v.a(AMapWidgetListener.class.hashCode())) == null || listA.size() <= 0) {
                return;
            }
            synchronized (listA) {
                for (int i = 0; i < listA.size(); i++) {
                    ((AMapWidgetListener) listA.get(i)).invalidateZoomController(this.f319c.getSZ());
                }
            }
        } catch (Throwable th) {
            er.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setMapStatusLimits(LatLngBounds latLngBounds) {
        try {
            this.f319c.setLimitLatLngBounds(latLngBounds);
            l();
        } catch (Throwable th) {
            th.printStackTrace();
            er.a(th);
        }
    }

    private boolean a(LatLngBounds latLngBounds) {
        return (latLngBounds == null || latLngBounds.northeast == null || latLngBounds.southwest == null) ? false : true;
    }

    private void l() {
        try {
            LatLngBounds limitLatLngBounds = this.f319c.getLimitLatLngBounds();
            if (this.f323g != null && a(limitLatLngBounds)) {
                GLMapState gLMapState = new GLMapState(1, this.f323g.getNativeInstance());
                IPoint iPointObtain = IPoint.obtain();
                GLMapState.lonlat2Geo(limitLatLngBounds.northeast.longitude, limitLatLngBounds.northeast.latitude, iPointObtain);
                IPoint iPointObtain2 = IPoint.obtain();
                GLMapState.lonlat2Geo(limitLatLngBounds.southwest.longitude, limitLatLngBounds.southwest.latitude, iPointObtain2);
                this.f319c.setLimitIPoints(new IPoint[]{iPointObtain, iPointObtain2});
                gLMapState.recycle();
                return;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.f319c.setLimitIPoints(null);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public Handler getMainHandler() {
        return this.j;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void onChangeFinish() {
        Message messageObtainMessage = this.j.obtainMessage();
        messageObtainMessage.what = 11;
        this.j.sendMessage(messageObtainMessage);
    }

    protected void a(boolean z, CameraPosition cameraPosition) {
        MapConfig mapConfig = this.f319c;
        if (mapConfig == null || mapConfig.getChangedCounter() == 0) {
            return;
        }
        try {
            if (!this.ay && this.f323g.getAnimateionsCount() == 0 && this.f323g.getStateMessageCount() == 0) {
                if (this.w != null) {
                    this.w.onMapStable();
                }
                List listA = this.v.a(AMap.OnCameraChangeListener.class.hashCode());
                if (listA == null || listA.size() == 0 || !this.D.isEnabled()) {
                    return;
                }
                if (cameraPosition == null) {
                    try {
                        cameraPosition = getCameraPosition();
                    } catch (Throwable th) {
                        hn.c(th, "AMapDelegateImp", "cameraChangeFinish");
                        th.printStackTrace();
                    }
                }
                try {
                    synchronized (listA) {
                        Iterator it = listA.iterator();
                        while (it.hasNext()) {
                            ((AMap.OnCameraChangeListener) it.next()).onCameraChangeFinish(cameraPosition);
                        }
                    }
                } catch (Throwable unused) {
                }
                this.f319c.resetChangedCounter();
            }
        } catch (Throwable th2) {
            th2.printStackTrace();
            er.a(th2);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setZoomScaleParam(float f2) {
        this.av = f2;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void onFling() {
        ab abVar = this.F;
        if (abVar != null) {
            abVar.b(true);
        }
        this.X = true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public int getMapWidth() {
        return this.f324h;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public int getMapHeight() {
        return this.i;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public float getCameraAngle() {
        return getCameraDegree(this.J);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public float getSkyHeight() {
        return this.f319c.getSkyHeight();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public boolean isMaploaded() {
        return this.O;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public MapConfig getMapConfig() {
        return this.f319c;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public View getView() throws RemoteException {
        return this.E;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void onIndoorBuildingActivity(int i, byte[] bArr) {
        aq aqVar;
        if (bArr != null) {
            try {
                aqVar = new aq();
                byte b2 = bArr[0];
                aqVar.f157a = new String(bArr, 1, b2, "utf-8");
                int i2 = 1 + b2;
                int i3 = i2 + 1;
                byte b3 = bArr[i2];
                aqVar.f158b = new String(bArr, i3, b3, "utf-8");
                int i4 = i3 + b3;
                int i5 = i4 + 1;
                byte b4 = bArr[i4];
                aqVar.activeFloorName = new String(bArr, i5, b4, "utf-8");
                int i6 = i5 + b4;
                aqVar.activeFloorIndex = GLConvertUtil.getInt(bArr, i6);
                int i7 = i6 + 4;
                int i8 = i7 + 1;
                byte b5 = bArr[i7];
                aqVar.poiid = new String(bArr, i8, b5, "utf-8");
                int i9 = i8 + b5;
                int i10 = i9 + 1;
                byte b6 = bArr[i9];
                aqVar.f164h = new String(bArr, i10, b6, "utf-8");
                int i11 = i10 + b6;
                aqVar.f159c = GLConvertUtil.getInt(bArr, i11);
                int i12 = i11 + 4;
                aqVar.floor_indexs = new int[aqVar.f159c];
                aqVar.floor_names = new String[aqVar.f159c];
                aqVar.f160d = new String[aqVar.f159c];
                for (int i13 = 0; i13 < aqVar.f159c; i13++) {
                    aqVar.floor_indexs[i13] = GLConvertUtil.getInt(bArr, i12);
                    int i14 = i12 + 4;
                    int i15 = i14 + 1;
                    byte b7 = bArr[i14];
                    if (b7 > 0) {
                        aqVar.floor_names[i13] = new String(bArr, i15, b7, "utf-8");
                        i15 += b7;
                    }
                    i12 = i15 + 1;
                    byte b8 = bArr[i15];
                    if (b8 > 0) {
                        aqVar.f160d[i13] = new String(bArr, i12, b8, "utf-8");
                        i12 += b8;
                    }
                }
                aqVar.f161e = GLConvertUtil.getInt(bArr, i12);
                int i16 = i12 + 4;
                if (aqVar.f161e > 0) {
                    aqVar.f162f = new int[aqVar.f161e];
                    for (int i17 = 0; i17 < aqVar.f161e; i17++) {
                        aqVar.f162f[i17] = GLConvertUtil.getInt(bArr, i16);
                        i16 += 4;
                    }
                }
            } catch (Throwable th) {
                er.a(th);
                th.printStackTrace();
                return;
            }
        } else {
            aqVar = null;
        }
        this.bi = aqVar;
        post(new Runnable() { // from class: com.amap.api.mapcore.util.c.29
            @Override // java.lang.Runnable
            public void run() {
                if (c.this.aI != null) {
                    c.this.aI.a(c.this.bi);
                }
            }
        });
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void destroy() {
        this.K = true;
        ey.a(ex.f796c, "destroy map");
        try {
            if (this.aL != null) {
                this.aL.b();
            }
            if (this.Q != null) {
                this.Q.deactivate();
            }
            this.Q = null;
            this.aI = null;
            if (this.ar != null) {
                this.ar.renderPause();
            }
            if (this.aK != null) {
                this.aK.e();
            }
            if (this.as != null) {
                this.as.a((AMapGestureListener) null);
                this.as.d();
                this.as = null;
            }
            if (this.H != null) {
                this.H.destroy();
            }
            if (this.f318b != null) {
                this.f318b.i();
            }
            if (this.F != null) {
                this.F.g();
            }
            g();
            if (this.ad != null) {
                this.ad.interrupt();
                this.ad = null;
            }
            if (this.ae != null) {
                this.ae.interrupt();
                this.ae = null;
            }
            if (this.ao != null) {
                this.ao.a();
                this.ao = null;
            }
            if (this.ap != null) {
                this.ap.a((di.a) null);
                this.ap.a();
                this.ap = null;
            }
            ea.b();
            if (this.f323g != null) {
                this.f323g.setMapListener(null);
                this.f323g.releaseNetworkState();
                queueEvent(new Runnable() { // from class: com.amap.api.mapcore.util.c.30
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            c.this.destroySurface(c.this.J);
                        } catch (Throwable th) {
                            th.printStackTrace();
                            er.a(th);
                        }
                    }
                });
                int i = 0;
                while (this.f323g != null) {
                    int i2 = i + 1;
                    if (i >= 50) {
                        break;
                    }
                    try {
                        Thread.sleep(20L);
                    } catch (InterruptedException e2) {
                        er.a(e2);
                    }
                    i = i2;
                }
            }
            if (this.L != null) {
                this.L.c();
            }
            if (this.x != null) {
                this.x.b();
            }
            if (this.D != null) {
                try {
                    this.D.onDetachedGLThread();
                } catch (Exception e3) {
                    e3.printStackTrace();
                    er.a(e3);
                }
            }
            if (this.E != null) {
                this.E.i();
                this.E = null;
            }
            if (this.P != null) {
                this.P.c();
                this.P = null;
            }
            this.Q = null;
            this.u = null;
            m();
            this.ac = null;
            ey.a();
            hn.b();
        } catch (Throwable th) {
            hn.c(th, "AMapDelegateImp", "destroy");
            er.a(th);
            th.printStackTrace();
        }
    }

    private void m() {
        h hVar = this.v;
        if (hVar != null) {
            hVar.a();
        }
    }

    /* JADX INFO: renamed from: com.amap.api.mapcore.util.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AMapDelegateImp.java */
    private class C0007c implements fh.a {
        private C0007c() {
        }

        @Override // com.amap.api.mapcore.util.fh.a
        public void a(int i) {
            if (c.this.f320d != null) {
                c.this.f320d.activeFloorIndex = c.this.f320d.floor_indexs[i];
                c.this.f320d.activeFloorName = c.this.f320d.floor_names[i];
                try {
                    c.this.setIndoorBuildingInfo(c.this.f320d);
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: compiled from: AMapDelegateImp.java */
    class b {
        b() {
        }

        public void a(aq aqVar) {
            List listA;
            List listA2;
            if (c.this.f319c == null || !c.this.f319c.isIndoorEnable()) {
                return;
            }
            final fh fhVarE = c.this.E.e();
            if (aqVar == null) {
                try {
                    List listA3 = c.this.v.a(AMap.OnIndoorBuildingActiveListener.class.hashCode());
                    if (listA3 != null && listA3.size() > 0) {
                        synchronized (listA3) {
                            for (int i = 0; i < listA3.size(); i++) {
                                ((AMap.OnIndoorBuildingActiveListener) listA3.get(i)).OnIndoorBuilding(aqVar);
                            }
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                if (c.this.f320d != null) {
                    c.this.f320d.f163g = null;
                }
                if (fhVarE.d()) {
                    c.this.j.post(new Runnable() { // from class: com.amap.api.mapcore.util.c.b.1
                        @Override // java.lang.Runnable
                        public void run() {
                            fhVarE.a(false);
                        }
                    });
                }
                c.this.f319c.maxZoomLevel = c.this.f319c.isSetLimitZoomLevel() ? c.this.f319c.getMaxZoomLevel() : 20.0f;
                try {
                    if (!c.this.B.isZoomControlsEnabled() || (listA = c.this.v.a(AMapWidgetListener.class.hashCode())) == null || listA.size() <= 0) {
                        return;
                    }
                    synchronized (listA) {
                        for (int i2 = 0; i2 < listA.size(); i2++) {
                            ((AMapWidgetListener) listA.get(i2)).invalidateZoomController(c.this.f319c.getSZ());
                        }
                    }
                    return;
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
            }
            if (aqVar != null && aqVar.floor_indexs != null && aqVar.floor_names != null && aqVar.floor_indexs.length == aqVar.floor_names.length) {
                int i3 = 0;
                while (true) {
                    if (i3 >= aqVar.floor_indexs.length) {
                        break;
                    }
                    if (aqVar.activeFloorIndex == aqVar.floor_indexs[i3]) {
                        aqVar.activeFloorName = aqVar.floor_names[i3];
                        break;
                    }
                    i3++;
                }
            }
            if (aqVar == null || c.this.f320d == null || c.this.f320d.activeFloorIndex == aqVar.activeFloorIndex || !fhVarE.d()) {
                if (aqVar != null && (c.this.f320d == null || !c.this.f320d.poiid.equals(aqVar.poiid) || c.this.f320d.f163g == null)) {
                    c cVar = c.this;
                    cVar.f320d = aqVar;
                    if (cVar.f319c != null) {
                        if (c.this.f320d.f163g == null) {
                            c.this.f320d.f163g = new Point();
                        }
                        DPoint mapGeoCenter = c.this.f319c.getMapGeoCenter();
                        if (mapGeoCenter != null) {
                            c.this.f320d.f163g.x = (int) mapGeoCenter.x;
                            c.this.f320d.f163g.y = (int) mapGeoCenter.y;
                        }
                    }
                }
                try {
                    List listA4 = c.this.v.a(AMap.OnIndoorBuildingActiveListener.class.hashCode());
                    if (listA4 != null && listA4.size() > 0) {
                        synchronized (listA4) {
                            for (int i4 = 0; i4 < listA4.size(); i4++) {
                                ((AMap.OnIndoorBuildingActiveListener) listA4.get(i4)).OnIndoorBuilding(aqVar);
                            }
                        }
                    }
                    c.this.f319c.maxZoomLevel = c.this.f319c.isSetLimitZoomLevel() ? c.this.f319c.getMaxZoomLevel() : 20.0f;
                    if (c.this.B.isZoomControlsEnabled() && (listA2 = c.this.v.a(AMapWidgetListener.class.hashCode())) != null && listA2.size() > 0) {
                        synchronized (listA2) {
                            for (int i5 = 0; i5 < listA2.size(); i5++) {
                                ((AMapWidgetListener) listA2.get(i5)).invalidateZoomController(c.this.f319c.getSZ());
                            }
                        }
                    }
                    if (!c.this.B.isIndoorSwitchEnabled()) {
                        if (c.this.B.isIndoorSwitchEnabled() || !fhVarE.d()) {
                            return;
                        }
                        c.this.B.setIndoorSwitchEnabled(false);
                        return;
                    }
                    if (!fhVarE.d()) {
                        c.this.B.setIndoorSwitchEnabled(true);
                    }
                    c.this.j.post(new Runnable() { // from class: com.amap.api.mapcore.util.c.b.2
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                fhVarE.a(c.this.f320d.floor_names);
                                fhVarE.a(c.this.f320d.activeFloorName);
                                if (fhVarE.d()) {
                                    return;
                                }
                                fhVarE.a(true);
                            } catch (Throwable th3) {
                                th3.printStackTrace();
                            }
                        }
                    });
                } catch (Throwable th3) {
                    th3.printStackTrace();
                }
            }
        }
    }

    @Override // com.autonavi.base.amap.mapcore.interfaces.IAMapListener
    public void beforeDrawLabel(int i, GLMapState gLMapState) {
        a();
        GLMapEngine gLMapEngine = this.f323g;
        if (gLMapEngine != null) {
            gLMapEngine.pushRendererState();
        }
        this.H.draw(true, this.ai);
        GLMapEngine gLMapEngine2 = this.f323g;
        if (gLMapEngine2 != null) {
            gLMapEngine2.popRendererState();
        }
    }

    @Override // com.autonavi.base.amap.mapcore.interfaces.IAMapListener
    public void afterDrawLabel(int i, GLMapState gLMapState) {
        a();
        jx jxVar = this.bb;
        if (jxVar != null) {
            jxVar.a(gLMapState, this.f319c);
        }
        GLMapEngine gLMapEngine = this.f323g;
        if (gLMapEngine != null) {
            gLMapEngine.pushRendererState();
        }
        ab abVar = this.F;
        if (abVar != null) {
            abVar.b();
        }
        this.H.draw(false, this.ai);
        ax axVar = this.aL;
        if (axVar != null) {
            axVar.a(this.f319c, getViewMatrix(), getProjectionMatrix());
        }
        o oVar = this.L;
        if (oVar != null) {
            oVar.a();
        }
        v vVar = this.f318b;
        if (vVar != null) {
            vVar.a(false);
        }
        da daVar = this.y;
        if (daVar != null) {
            daVar.b(getMapWidth(), getMapHeight());
        }
        GLMapEngine gLMapEngine2 = this.f323g;
        if (gLMapEngine2 != null) {
            gLMapEngine2.popRendererState();
        }
    }

    @Override // com.autonavi.base.amap.mapcore.interfaces.IAMapListener
    public void afterRendererOver(int i, GLMapState gLMapState) {
        GLMapEngine gLMapEngine = this.f323g;
        if (gLMapEngine != null) {
            gLMapEngine.pushRendererState();
        }
        v vVar = this.f318b;
        if (vVar != null) {
            vVar.a(true);
        }
        GLMapEngine gLMapEngine2 = this.f323g;
        if (gLMapEngine2 != null) {
            gLMapEngine2.popRendererState();
        }
    }

    @Override // com.autonavi.base.amap.mapcore.interfaces.IAMapListener
    public void afterDrawFrame(int i, GLMapState gLMapState) {
        float mapZoomer = gLMapState.getMapZoomer();
        GLMapEngine gLMapEngine = this.f323g;
        if (!(gLMapEngine != null && (gLMapEngine.isInMapAction(i) || this.f323g.isInMapAnimation(i)))) {
            int i2 = this.am;
            if (i2 != -1) {
                this.ar.setRenderFps(i2);
            } else {
                this.ar.setRenderFps(15.0f);
            }
            if (this.au != mapZoomer) {
                this.au = mapZoomer;
            }
        }
        if (this.aD) {
            return;
        }
        this.aD = true;
    }

    @Override // com.autonavi.base.amap.mapcore.interfaces.IAMapListener
    public void afterAnimation() {
        redrawInfoWindow();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public long createGLOverlay(int i) {
        GLMapEngine gLMapEngine = this.f323g;
        if (gLMapEngine != null) {
            return gLMapEngine.createOverlay(1, i);
        }
        return 0L;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public long getGlOverlayMgrPtr() {
        GLMapEngine gLMapEngine = this.f323g;
        if (gLMapEngine != null) {
            return gLMapEngine.getGlOverlayMgrPtr(1);
        }
        return 0L;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public CrossOverlay addCrossVector(CrossOverlayOptions crossOverlayOptions) {
        if (crossOverlayOptions == null || crossOverlayOptions.getRes() == null) {
            return null;
        }
        CrossVectorOverlay crossVectorOverlay = new CrossVectorOverlay(1, getContext(), this);
        if (crossOverlayOptions != null) {
            crossVectorOverlay.setAttribute(crossOverlayOptions.getAttribute());
        }
        GLMapEngine gLMapEngine = this.f323g;
        if (gLMapEngine != null) {
            gLMapEngine.getOverlayBundle(1).addOverlay(crossVectorOverlay);
            crossVectorOverlay.resumeMarker(crossOverlayOptions.getRes());
        }
        return new CrossOverlay(crossOverlayOptions, crossVectorOverlay);
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void addOverlayTexture(int i, GLTextureProperty gLTextureProperty) {
        GLOverlayBundle overlayBundle;
        try {
            if (this.f323g != null && (overlayBundle = this.f323g.getOverlayBundle(i)) != null && gLTextureProperty != null && gLTextureProperty.mBitmap != null) {
                this.f323g.addOverlayTexture(i, gLTextureProperty);
                overlayBundle.addOverlayTextureItem(gLTextureProperty.mId, gLTextureProperty.mAnchor, gLTextureProperty.mXRatio, gLTextureProperty.mYRatio, gLTextureProperty.mBitmap.getWidth(), gLTextureProperty.mBitmap.getHeight());
            }
        } catch (Throwable th) {
            er.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setCustomMapStylePath(String str) {
        if (TextUtils.isEmpty(str) || str.equals(this.f319c.getCustomStylePath())) {
            return;
        }
        this.f319c.setCustomStylePath(str);
        this.C = true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setCustomMapStyleID(String str) {
        if (TextUtils.isEmpty(str) || str.equals(this.f319c.getCustomStyleID())) {
            return;
        }
        this.f319c.setCustomStyleID(str);
        this.C = true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setCustomTextureResourcePath(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f319c.setCustomTextureResourcePath(str);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setCustomMapStyle(CustomMapStyleOptions customMapStyleOptions) {
        if (customMapStyleOptions != null) {
            try {
                if (a(true, false)) {
                    return;
                }
                if (customMapStyleOptions.isEnable() && (customMapStyleOptions.getStyleId() != null || customMapStyleOptions.getStyleTexturePath() != null || customMapStyleOptions.getStyleTextureData() != null)) {
                    k();
                }
                this.aM.c();
                this.aM.a(customMapStyleOptions);
                if (this.bb != null) {
                    this.bb.a("setCustomMapStyle", customMapStyleOptions);
                }
            } catch (Throwable th) {
                er.a(th);
                return;
            }
        }
        resetRenderTime();
    }

    @Override // com.amap.api.mapcore.util.b.a
    public void a(byte[] bArr) {
        jx jxVar = this.bb;
        if (jxVar != null) {
            jxVar.a("onAbroadStyleComplete", bArr);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public MyLocationStyle getMyLocationStyle() throws RemoteException {
        cv cvVar = this.P;
        if (cvVar != null) {
            return cvVar.a();
        }
        return null;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void reloadMapCustomStyle() {
        com.amap.api.mapcore.util.b bVar = this.aM;
        if (bVar != null) {
            bVar.b();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void setMapCustomEnable(boolean z, boolean z2) {
        if (this.aA && !this.K) {
            boolean z3 = z2 ? z2 : false;
            if (TextUtils.isEmpty(this.f319c.getCustomStylePath()) && TextUtils.isEmpty(this.f319c.getCustomStyleID())) {
                return;
            }
            if (z) {
                try {
                    if (this.f319c.isProFunctionAuthEnable() && !TextUtils.isEmpty(this.f319c.getCustomStyleID()) && this.ao != null) {
                        this.ao.a(this.f319c.getCustomStyleID());
                        this.ao.b();
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                    er.a(th);
                    return;
                }
            }
            if (z2 || this.C || (this.f319c.isCustomStyleEnable() ^ z)) {
                a(z, (byte[]) null, z3);
            }
            this.C = false;
            return;
        }
        a aVar = this.aR;
        aVar.f390b = true;
        aVar.f391c = z;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setMapCustomEnable(boolean z) {
        if (z) {
            k();
        }
        setMapCustomEnable(z, false);
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void setCustomMapStyle(boolean z, byte[] bArr) {
        a(z, bArr, false);
    }

    public void a(boolean z, byte[] bArr, boolean z2) {
        Cdo cdoA;
        try {
            this.f319c.setCustomStyleEnable(z);
            boolean z3 = false;
            if (this.f319c.isHideLogoEnable()) {
                this.B.setLogoEnable(!z);
            }
            if (z) {
                c(1, true);
                dn dnVar = new dn(this.f322f);
                if (this.ac != null && this.ac.getTrafficRoadBackgroundColor() != -1) {
                    dnVar.a(this.ac.getTrafficRoadBackgroundColor());
                }
                if (this.f319c.isProFunctionAuthEnable() && !TextUtils.isEmpty(this.f319c.getCustomTextureResourcePath())) {
                    z3 = true;
                }
                StyleItem[] styleItemArrC = null;
                if (bArr != null) {
                    cdoA = dnVar.a(bArr, z3);
                    if (cdoA != null && (styleItemArrC = cdoA.c()) != null) {
                        this.f319c.setUseProFunction(true);
                    }
                } else {
                    cdoA = null;
                }
                if (styleItemArrC == null && (cdoA = dnVar.a(this.f319c.getCustomStylePath(), z3)) != null) {
                    styleItemArrC = cdoA.c();
                }
                if (dnVar.a() != 0) {
                    this.f319c.setCustomBackgroundColor(dnVar.a());
                }
                if (cdoA != null && cdoA.d() != null) {
                    if (this.ap != null) {
                        this.ap.a((String) cdoA.d());
                        this.ap.a(cdoA);
                        this.ap.b();
                        return;
                    }
                    return;
                }
                a(styleItemArrC, z2);
                return;
            }
            c(1, false);
            a(1, this.f319c.getMapStyleMode(), this.f319c.getMapStyleTime(), this.f319c.getMapStyleState(), true, false, (StyleItem[]) null);
        } catch (Throwable th) {
            er.a(th);
        }
    }

    @Override // com.amap.api.mapcore.util.di.a
    public void a(String str, Cdo cdo) {
        setCustomTextureResourcePath(str);
        if (!this.f319c.isCustomStyleEnable() || cdo == null) {
            return;
        }
        a(cdo.c(), false);
    }

    protected void a(StyleItem[] styleItemArr, boolean z) {
        if (z || (styleItemArr != null && styleItemArr.length > 0)) {
            a(1, 0, 0, 0, true, true, styleItemArr);
            ep.a(this.f322f, true);
        } else {
            ep.a(this.f322f, false);
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void removeEngineGLOverlay(BaseMapOverlay baseMapOverlay) {
        GLMapEngine gLMapEngine = this.f323g;
        if (gLMapEngine != null) {
            gLMapEngine.getOverlayBundle(1).removeOverlay(baseMapOverlay);
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public float[] getFinalMatrix() {
        MapConfig mapConfig = this.f319c;
        if (mapConfig != null) {
            return mapConfig.getMvpMatrix();
        }
        return this.n;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public String createId(String str) {
        IGlOverlayLayer iGlOverlayLayer = this.H;
        if (iGlOverlayLayer != null) {
            return iGlOverlayLayer.createId(str);
        }
        return null;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void showLogoEnabled(boolean z) {
        if (this.K) {
            return;
        }
        this.E.f(Boolean.valueOf(z));
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public float[] getViewMatrix() {
        MapConfig mapConfig = this.f319c;
        if (mapConfig != null) {
            return mapConfig.getViewMatrix();
        }
        return this.o;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public float[] getProjectionMatrix() {
        MapConfig mapConfig = this.f319c;
        if (mapConfig != null) {
            return mapConfig.getProjectionMatrix();
        }
        return this.p;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void changeSurface(GL10 gl10, int i, int i2) {
        try {
            changeSurface(1, gl10, i, i2);
        } catch (Throwable th) {
            th.printStackTrace();
            er.a(th);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void createSurface(GL10 gl10, EGLConfig eGLConfig) {
        try {
            this.aq = Thread.currentThread().getId();
        } catch (Throwable th) {
            th.printStackTrace();
            er.a(th);
        }
        try {
            createSurface(1, gl10, eGLConfig);
        } catch (Throwable th2) {
            th2.printStackTrace();
            er.a(th2);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void renderSurface(GL10 gl10) {
        drawFrame(gl10);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public boolean canStopMapRender() {
        GLMapEngine gLMapEngine = this.f323g;
        if (gLMapEngine != null) {
            gLMapEngine.canStopMapRender(1);
        }
        return true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void getLatLngRect(DPoint[] dPointArr) {
        try {
            Rectangle geoRectangle = this.f319c.getGeoRectangle();
            if (geoRectangle != null) {
                IPoint[] clipRect = geoRectangle.getClipRect();
                for (int i = 0; i < 4; i++) {
                    GLMapState.geo2LonLat(clipRect[i].x, clipRect[i].y, dPointArr[i]);
                }
            }
        } catch (Throwable th) {
            er.a(th);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x009d A[PHI: r4
  0x009d: PHI (r4v5 float) = (r4v4 float), (r4v4 float), (r4v12 float) binds: [B:22:0x0096, B:24:0x009a, B:19:0x008f] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void checkMapState(com.autonavi.amap.api.mapcore.IGLMapState r17) {
        /*
            Method dump skipped, instruction units count: 262
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.c.checkMapState(com.autonavi.amap.api.mapcore.IGLMapState):void");
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setRenderMode(int i) {
        try {
            if (this.D != null) {
                this.D.setRenderMode(i);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public dd getGLShader(int i) {
        de deVar = this.aJ;
        if (deVar == null) {
            return null;
        }
        return deVar.a(i);
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public de getGLShaderManager() {
        return this.aJ;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void changeSize(int i, int i2) {
        MapConfig mapConfig = this.f319c;
        if (mapConfig != null) {
            this.f324h = i;
            this.i = i2;
            mapConfig.setMapWidth(i);
            this.f319c.setMapHeight(i2);
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void setHideLogoEnble(boolean z) {
        MapConfig mapConfig = this.f319c;
        if (mapConfig != null) {
            mapConfig.setHideLogoEnble(z);
            if (this.f319c.isCustomStyleEnable()) {
                this.B.setLogoEnable(!z);
            }
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void changeLogoIconStyle(String str, boolean z, int i) {
        fj fjVar = this.E;
        if (fjVar != null) {
            fjVar.a(str, Boolean.valueOf(z), Integer.valueOf(i));
        }
        ac acVar = this.B;
        if (acVar != null) {
            acVar.requestRefreshLogo();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public void refreshLogo() {
        fj fjVar = this.E;
        if (fjVar != null) {
            fjVar.c();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IAMapDelegate
    public float getUnitLengthByZoom(int i) {
        GLMapState gLMapState = new GLMapState(1, this.f323g.getNativeInstance());
        gLMapState.setMapZoomer(i);
        gLMapState.recalculate();
        float gLUnitWithWin = gLMapState.getGLUnitWithWin(1);
        gLMapState.recycle();
        return gLUnitWithWin;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setTouchPoiEnable(boolean z) {
        MapConfig mapConfig = this.f319c;
        if (mapConfig != null) {
            mapConfig.setTouchPoiEnable(z);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public boolean isTouchPoiEnable() {
        MapConfig mapConfig = this.f319c;
        if (mapConfig != null) {
            return mapConfig.isTouchPoiEnable();
        }
        return true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public int getSY() {
        MapConfig mapConfig = this.f319c;
        if (mapConfig != null) {
            return (int) mapConfig.getSY();
        }
        return -1;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public int getSX() {
        MapConfig mapConfig = this.f319c;
        if (mapConfig != null) {
            return (int) mapConfig.getSX();
        }
        return -1;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public long getNativeMapController() {
        GLMapEngine gLMapEngine = this.f323g;
        if (gLMapEngine != null) {
            return gLMapEngine.getNativeMapController(1);
        }
        return 0L;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void addOnCameraChangeListener(AMap.OnCameraChangeListener onCameraChangeListener) throws RemoteException {
        h hVar = this.v;
        if (hVar != null) {
            hVar.a(Integer.valueOf(AMap.OnCameraChangeListener.class.hashCode()), onCameraChangeListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void addOnMapClickListener(AMap.OnMapClickListener onMapClickListener) throws RemoteException {
        h hVar = this.v;
        if (hVar != null) {
            hVar.a(Integer.valueOf(AMap.OnMapClickListener.class.hashCode()), onMapClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void addOnMarkerDragListener(AMap.OnMarkerDragListener onMarkerDragListener) {
        h hVar = this.v;
        if (hVar != null) {
            hVar.a(Integer.valueOf(AMap.OnMarkerDragListener.class.hashCode()), onMarkerDragListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void addOnMapLoadedListener(AMap.OnMapLoadedListener onMapLoadedListener) {
        h hVar = this.v;
        if (hVar != null) {
            hVar.a(Integer.valueOf(AMap.OnMapLoadedListener.class.hashCode()), onMapLoadedListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void addOnMapTouchListener(AMap.OnMapTouchListener onMapTouchListener) throws RemoteException {
        h hVar = this.v;
        if (hVar != null) {
            hVar.a(Integer.valueOf(AMap.OnMapTouchListener.class.hashCode()), onMapTouchListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void addOnMarkerClickListener(AMap.OnMarkerClickListener onMarkerClickListener) throws RemoteException {
        h hVar = this.v;
        if (hVar != null) {
            hVar.a(Integer.valueOf(AMap.OnMarkerClickListener.class.hashCode()), onMarkerClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void addOnPolylineClickListener(AMap.OnPolylineClickListener onPolylineClickListener) throws RemoteException {
        h hVar = this.v;
        if (hVar != null) {
            hVar.a(Integer.valueOf(AMap.OnPolylineClickListener.class.hashCode()), onPolylineClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void addOnPOIClickListener(AMap.OnPOIClickListener onPOIClickListener) throws RemoteException {
        h hVar = this.v;
        if (hVar != null) {
            hVar.a(Integer.valueOf(AMap.OnPOIClickListener.class.hashCode()), onPOIClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void addOnMapLongClickListener(AMap.OnMapLongClickListener onMapLongClickListener) throws RemoteException {
        h hVar = this.v;
        if (hVar != null) {
            hVar.a(Integer.valueOf(AMap.OnMapLongClickListener.class.hashCode()), onMapLongClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void addOnInfoWindowClickListener(AMap.OnInfoWindowClickListener onInfoWindowClickListener) throws RemoteException {
        h hVar = this.v;
        if (hVar != null) {
            hVar.a(Integer.valueOf(AMap.OnInfoWindowClickListener.class.hashCode()), onInfoWindowClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void addOnIndoorBuildingActiveListener(AMap.OnIndoorBuildingActiveListener onIndoorBuildingActiveListener) throws RemoteException {
        h hVar = this.v;
        if (hVar != null) {
            hVar.a(Integer.valueOf(AMap.OnIndoorBuildingActiveListener.class.hashCode()), onIndoorBuildingActiveListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void addOnMyLocationChangeListener(AMap.OnMyLocationChangeListener onMyLocationChangeListener) throws RemoteException {
        h hVar = this.v;
        if (hVar != null) {
            hVar.a(Integer.valueOf(AMap.OnMyLocationChangeListener.class.hashCode()), onMyLocationChangeListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void removeOnCameraChangeListener(AMap.OnCameraChangeListener onCameraChangeListener) throws RemoteException {
        h hVar = this.v;
        if (hVar != null) {
            hVar.b(Integer.valueOf(AMap.OnCameraChangeListener.class.hashCode()), onCameraChangeListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void removeOnMapClickListener(AMap.OnMapClickListener onMapClickListener) throws RemoteException {
        h hVar = this.v;
        if (hVar != null) {
            hVar.b(Integer.valueOf(AMap.OnMapClickListener.class.hashCode()), onMapClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void removeOnMarkerDragListener(AMap.OnMarkerDragListener onMarkerDragListener) {
        h hVar = this.v;
        if (hVar != null) {
            hVar.b(Integer.valueOf(AMap.OnMarkerDragListener.class.hashCode()), onMarkerDragListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void removeOnMapLoadedListener(AMap.OnMapLoadedListener onMapLoadedListener) {
        h hVar = this.v;
        if (hVar != null) {
            hVar.b(Integer.valueOf(AMap.OnMapLoadedListener.class.hashCode()), onMapLoadedListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void removeOnMapTouchListener(AMap.OnMapTouchListener onMapTouchListener) throws RemoteException {
        h hVar = this.v;
        if (hVar != null) {
            hVar.b(Integer.valueOf(AMap.OnMapTouchListener.class.hashCode()), onMapTouchListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void removeOnMarkerClickListener(AMap.OnMarkerClickListener onMarkerClickListener) throws RemoteException {
        h hVar = this.v;
        if (hVar != null) {
            hVar.b(Integer.valueOf(AMap.OnMarkerClickListener.class.hashCode()), onMarkerClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void removeOnPolylineClickListener(AMap.OnPolylineClickListener onPolylineClickListener) throws RemoteException {
        h hVar = this.v;
        if (hVar != null) {
            hVar.b(Integer.valueOf(AMap.OnPolylineClickListener.class.hashCode()), onPolylineClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void removeOnPOIClickListener(AMap.OnPOIClickListener onPOIClickListener) throws RemoteException {
        h hVar = this.v;
        if (hVar != null) {
            hVar.b(Integer.valueOf(AMap.OnPOIClickListener.class.hashCode()), onPOIClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void removeOnMapLongClickListener(AMap.OnMapLongClickListener onMapLongClickListener) throws RemoteException {
        h hVar = this.v;
        if (hVar != null) {
            hVar.b(Integer.valueOf(AMap.OnMapLongClickListener.class.hashCode()), onMapLongClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void removeOnInfoWindowClickListener(AMap.OnInfoWindowClickListener onInfoWindowClickListener) throws RemoteException {
        h hVar = this.v;
        if (hVar != null) {
            hVar.b(Integer.valueOf(AMap.OnInfoWindowClickListener.class.hashCode()), onInfoWindowClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void removeOnIndoorBuildingActiveListener(AMap.OnIndoorBuildingActiveListener onIndoorBuildingActiveListener) throws RemoteException {
        h hVar = this.v;
        if (hVar != null) {
            hVar.b(Integer.valueOf(AMap.OnIndoorBuildingActiveListener.class.hashCode()), onIndoorBuildingActiveListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void removeOnMyLocationChangeListener(AMap.OnMyLocationChangeListener onMyLocationChangeListener) throws RemoteException {
        h hVar = this.v;
        if (hVar != null) {
            hVar.b(Integer.valueOf(AMap.OnMyLocationChangeListener.class.hashCode()), onMyLocationChangeListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void loadWorldVectorMap(boolean z) {
        MapConfig mapConfig = this.f319c;
        if (mapConfig != null) {
            mapConfig.setAbroadEnable(z);
        }
    }

    private boolean a(boolean z, boolean z2) {
        if (z) {
            if (this.bf) {
                ds.a("setCustomMapStyle 和 setWorldVectorMapStyle 不能同时使用，setCustomMapStyle将不会生效");
                return true;
            }
            this.be = true;
        }
        if (!z2) {
            return false;
        }
        if (this.be) {
            ds.a("setCustomMapStyle 和 setWorldVectorMapStyle 不能同时使用，setWorldVectorMapStyle将不会生效");
            return true;
        }
        this.bf = true;
        return false;
    }

    private void n() {
        t tVar = this.aK;
        if (tVar != null) {
            tVar.d();
        }
        de deVar = this.aJ;
        if (deVar != null) {
            deVar.b();
            this.aJ = null;
        }
        Log.d("qyd", "mGLSurfaceView instanceof AMapGLTextureView:" + (this.D instanceof f));
    }
}