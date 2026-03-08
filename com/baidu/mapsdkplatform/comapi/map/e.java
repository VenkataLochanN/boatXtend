package com.baidu.mapsdkplatform.comapi.map;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.autonavi.base.amap.mapcore.tools.GLMapStaticValue;
import com.baidu.mapapi.UIMsg;
import com.baidu.mapapi.common.EnvironmentUtilities;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapBaseIndoorMapInfo;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.model.ParcelItem;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapsdkplatform.comapi.map.j;
import com.baidu.mapsdkplatform.comjni.map.basemap.BaseMapCallback;
import com.baidu.mapsdkplatform.comjni.map.basemap.JNIBaseMap;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class e implements com.baidu.mapsdkplatform.comjni.map.basemap.b {
    private static int N;
    private static int O;
    private static List<JNIBaseMap> ar;
    private aj B;
    private ai C;
    private Context D;
    private List<d> E;
    private x F;
    private g G;
    private ae H;
    private ah I;
    private n J;
    private com.baidu.mapsdkplatform.comapi.map.a K;
    private o L;
    private af M;
    private int P;
    private int Q;
    private int R;
    private VelocityTracker T;
    private long U;
    private long V;
    private long W;
    private long X;
    private int Y;
    private float Z;
    private float aa;
    private boolean ab;
    private long ac;
    private long ad;
    private float ag;
    private float ah;
    private float ai;
    private float aj;
    private f am;
    private String an;
    private int ao;
    private b ap;
    private c aq;
    com.baidu.mapsdkplatform.comjni.map.basemap.a i;
    long j;
    boolean k;
    int l;
    boolean n;
    boolean o;
    boolean p;
    private boolean s;
    private boolean t;
    private static final String r = j.class.getSimpleName();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static float f3560d = 1096.0f;
    static long m = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f3561a = 21.0f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f3562b = 4.0f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f3563c = 21.0f;
    private boolean u = true;
    private boolean v = false;
    private boolean w = false;
    private boolean x = false;
    private boolean y = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    boolean f3564e = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    boolean f3565f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    boolean f3566g = false;
    private boolean z = true;
    private boolean A = false;
    private j.a S = new j.a();
    private boolean ae = false;
    private boolean af = false;
    private long ak = 0;
    private long al = 0;
    private boolean as = false;
    private Queue<a> at = new LinkedList();
    public MapStatusUpdate q = null;
    private boolean au = false;
    private boolean av = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    List<l> f3567h = new ArrayList();

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f3568a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f3569b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f3570c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f3571d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Bundle f3572e;

        public a(long j, int i, int i2, int i3) {
            this.f3568a = j;
            this.f3569b = i;
            this.f3570c = i2;
            this.f3571d = i3;
        }

        public a(Bundle bundle) {
            this.f3572e = bundle;
        }
    }

    public e(Context context, String str, int i) {
        this.D = context;
        this.an = str;
        this.ao = i;
    }

    private void R() {
        if (!this.w && !this.t && !this.s && !this.x) {
            this.f3561a = this.f3563c;
            return;
        }
        if (this.f3561a > 20.0f) {
            this.f3561a = 20.0f;
        }
        if (E().f3518a > 20.0f) {
            ab abVarE = E();
            abVarE.f3518a = 20.0f;
            a(abVarE);
        }
    }

    private void S() {
        if (this.n) {
            return;
        }
        this.n = true;
        this.o = false;
        List<l> list = this.f3567h;
        if (list != null) {
            for (l lVar : list) {
                if (lVar != null) {
                    lVar.a(E());
                }
            }
        }
    }

    private boolean T() {
        if (this.i == null || !this.k) {
            return true;
        }
        this.af = false;
        if (!this.f3564e) {
            return false;
        }
        float f2 = this.al - this.ak;
        float fAbs = (Math.abs(this.ai - this.ag) * 1000.0f) / f2;
        float fAbs2 = (Math.abs(this.aj - this.ah) * 1000.0f) / f2;
        float fSqrt = (float) Math.sqrt((fAbs * fAbs) + (fAbs2 * fAbs2));
        if (fSqrt <= 500.0f) {
            return false;
        }
        A();
        a(34, (int) (fSqrt * 0.6f), (((int) this.aj) << 16) | ((int) this.ai));
        M();
        return true;
    }

    private Activity a(Context context) {
        if (context == null) {
            return null;
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return a(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    private void a(d dVar) {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar == null) {
            return;
        }
        dVar.f3556a = aVar.a(dVar.f3558c, dVar.f3559d, dVar.f3557b);
        this.E.add(dVar);
    }

    private void a(String str, String str2, long j) {
        try {
            Class<?> cls = Class.forName(str);
            cls.getMethod(str2, Long.TYPE).invoke(cls.newInstance(), Long.valueOf(j));
        } catch (Exception unused) {
        }
    }

    private void b(MotionEvent motionEvent) {
        if (this.S.f3594e) {
            return;
        }
        this.ad = motionEvent.getDownTime();
        long j = this.ad;
        if (j - this.ac < 400) {
            j = (Math.abs(motionEvent.getX() - this.Z) >= 120.0f || Math.abs(motionEvent.getY() - this.aa) >= 120.0f) ? this.ad : 0L;
        }
        this.ac = j;
        this.Z = motionEvent.getX();
        this.aa = motionEvent.getY();
        a(4, 0, (((int) motionEvent.getY()) << 16) | ((int) motionEvent.getX()));
        this.ab = true;
    }

    private void b(String str, Bundle bundle) {
        if (this.i == null) {
            return;
        }
        this.G.a(str);
        this.G.a(bundle);
        this.i.b(this.G.f3556a);
    }

    private boolean c(MotionEvent motionEvent) {
        if (this.S.f3594e || System.currentTimeMillis() - m < 300) {
            return true;
        }
        if (this.p) {
            List<l> list = this.f3567h;
            if (list != null) {
                for (l lVar : list) {
                    GeoPoint geoPointB = b((int) motionEvent.getX(), (int) motionEvent.getY());
                    if (lVar != null) {
                        lVar.d(geoPointB);
                    }
                }
            }
            return true;
        }
        float fAbs = Math.abs(motionEvent.getX() - this.Z);
        float fAbs2 = Math.abs(motionEvent.getY() - this.aa);
        float density = (float) (((double) SysOSUtil.getDensity()) > 1.5d ? ((double) SysOSUtil.getDensity()) * 1.5d : SysOSUtil.getDensity());
        if (this.ab && fAbs / density <= 3.0f && fAbs2 / density <= 3.0f) {
            return true;
        }
        this.ab = false;
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        if (this.f3564e) {
            BaiduMap.mapStatusReason = 1 | BaiduMap.mapStatusReason;
            S();
            a(3, 0, (y << 16) | x);
        }
        return false;
    }

    private boolean d(MotionEvent motionEvent) {
        if (this.p) {
            List<l> list = this.f3567h;
            if (list != null) {
                for (l lVar : list) {
                    GeoPoint geoPointB = b((int) motionEvent.getX(), (int) motionEvent.getY());
                    if (lVar != null) {
                        lVar.e(geoPointB);
                    }
                }
            }
            this.p = false;
            return true;
        }
        boolean z = !this.S.f3594e && motionEvent.getEventTime() - this.ad < 400 && Math.abs(motionEvent.getX() - this.Z) < 10.0f && Math.abs(motionEvent.getY() - this.aa) < 10.0f;
        M();
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (z) {
            return false;
        }
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        a(5, 0, (y << 16) | x);
        return true;
    }

    private boolean e(float f2, float f3) {
        if (this.i == null || !this.k) {
            return true;
        }
        this.ae = false;
        GeoPoint geoPointB = b((int) f2, (int) f3);
        if (geoPointB != null) {
            List<l> list = this.f3567h;
            if (list != null) {
                for (l lVar : list) {
                    if (lVar != null) {
                        lVar.b(geoPointB);
                    }
                }
            }
            if (this.f3565f) {
                ab abVarE = E();
                abVarE.f3518a += 1.0f;
                abVarE.f3521d = geoPointB.getLongitudeE6();
                abVarE.f3522e = geoPointB.getLatitudeE6();
                a(abVarE, 300);
                m = System.currentTimeMillis();
                return true;
            }
        }
        return false;
    }

    private boolean e(Bundle bundle) {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar == null) {
            return false;
        }
        return aVar.e(bundle);
    }

    private boolean f(Bundle bundle) {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar;
        if (bundle == null || (aVar = this.i) == null) {
            return false;
        }
        boolean zD = aVar.d(bundle);
        if (zD) {
            f(zD);
            this.i.b(this.B.f3556a);
        }
        return zD;
    }

    private void g(Bundle bundle) {
        int i;
        int i2;
        if (bundle.get("param") == null ? (i = bundle.getInt("type")) != h.ground.ordinal() && i < h.arc.ordinal() : (i2 = (bundle = (Bundle) bundle.get("param")).getInt("type")) != h.ground.ordinal() && i2 < h.arc.ordinal()) {
            h.popup.ordinal();
        }
        bundle.putLong("layer_addr", this.I.f3556a);
    }

    static void m(boolean z) {
        ar = com.baidu.mapsdkplatform.comjni.map.basemap.a.b();
        List<JNIBaseMap> list = ar;
        if (list == null || list.size() == 0) {
            com.baidu.mapsdkplatform.comjni.map.basemap.a.b(0L, z);
            return;
        }
        com.baidu.mapsdkplatform.comjni.map.basemap.a.b(ar.get(0).f3859a, z);
        for (JNIBaseMap jNIBaseMap : ar) {
            if (jNIBaseMap != null) {
                jNIBaseMap.ClearLayer(jNIBaseMap.f3859a, -1L);
            }
        }
    }

    void A() {
        if (this.n || this.o) {
            return;
        }
        this.o = true;
        List<l> list = this.f3567h;
        if (list == null) {
            return;
        }
        for (l lVar : list) {
            if (lVar != null) {
                lVar.a(E());
            }
        }
    }

    void B() {
        this.o = false;
        this.n = false;
        List<l> list = this.f3567h;
        if (list == null) {
            return;
        }
        for (l lVar : list) {
            if (lVar != null) {
                lVar.c(E());
            }
        }
    }

    public boolean C() {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar != null) {
            return aVar.a(this.H.f3556a);
        }
        return false;
    }

    public boolean D() {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar != null) {
            return aVar.a(this.aq.f3556a);
        }
        return false;
    }

    public ab E() {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar == null) {
            return null;
        }
        Bundle bundleH = aVar.h();
        ab abVar = new ab();
        abVar.a(bundleH);
        return abVar;
    }

    public LatLngBounds F() {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar == null) {
            return null;
        }
        Bundle bundleI = aVar.i();
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(CoordUtil.mc2ll(new GeoPoint(bundleI.getInt("minCoory"), bundleI.getInt("maxCoorx")))).include(CoordUtil.mc2ll(new GeoPoint(bundleI.getInt("maxCoory"), bundleI.getInt("minCoorx"))));
        return builder.build();
    }

    public MapStatusUpdate G() {
        return this.q;
    }

    public int H() {
        return this.P;
    }

    public int I() {
        return this.Q;
    }

    ab J() {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar == null) {
            return null;
        }
        Bundle bundleJ = aVar.j();
        ab abVar = new ab();
        abVar.a(bundleJ);
        return abVar;
    }

    public double K() {
        return E().m;
    }

    void L() {
        List<l> list;
        this.n = false;
        if (this.o || (list = this.f3567h) == null) {
            return;
        }
        for (l lVar : list) {
            if (lVar != null) {
                lVar.c(E());
            }
        }
    }

    void M() {
        this.R = 0;
        j.a aVar = this.S;
        aVar.f3594e = false;
        aVar.f3597h = 0.0d;
    }

    public float[] N() {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar == null) {
            return null;
        }
        return aVar.u();
    }

    public float[] O() {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar == null) {
            return null;
        }
        return aVar.v();
    }

    public Queue<a> P() {
        return this.at;
    }

    public void Q() {
        if (this.at.isEmpty()) {
            return;
        }
        a aVarPoll = this.at.poll();
        if (aVarPoll.f3572e == null) {
            com.baidu.mapsdkplatform.comjni.map.basemap.a.a(aVarPoll.f3568a, aVarPoll.f3569b, aVarPoll.f3570c, aVarPoll.f3571d);
        } else if (this.i != null) {
            A();
            this.i.a(aVarPoll.f3572e);
        }
    }

    public float a(int i, int i2, int i3, int i4, int i5, int i6) {
        if (!this.k) {
            return 12.0f;
        }
        if (this.i == null) {
            return 0.0f;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("left", i);
        bundle.putInt("right", i3);
        bundle.putInt("bottom", i4);
        bundle.putInt("top", i2);
        bundle.putInt("hasHW", 1);
        bundle.putInt("width", i5);
        bundle.putInt("height", i6);
        return this.i.c(bundle);
    }

    int a(int i, int i2, int i3) {
        if (!this.as) {
            return com.baidu.mapsdkplatform.comjni.map.basemap.a.a(this.j, i, i2, i3);
        }
        this.at.add(new a(this.j, i, i2, i3));
        return 0;
    }

    @Override // com.baidu.mapsdkplatform.comjni.map.basemap.b
    public int a(Bundle bundle, long j, int i, Bundle bundle2) {
        if (j == this.G.f3556a) {
            bundle.putString("jsondata", this.G.a());
            bundle.putBundle("param", this.G.b());
            return this.G.f3547g;
        }
        if (j == this.F.f3556a) {
            bundle.putString("jsondata", this.F.a());
            bundle.putBundle("param", this.F.b());
            return this.F.f3547g;
        }
        if (j == this.J.f3556a) {
            bundle.putBundle("param", this.L.a(bundle2.getInt("x"), bundle2.getInt("y"), bundle2.getInt("zoom")));
            return this.J.f3547g;
        }
        if (j != this.B.f3556a) {
            return 0;
        }
        bundle.putBundle("param", this.C.a(bundle2.getInt("x"), bundle2.getInt("y"), bundle2.getInt("zoom"), this.D));
        return this.B.f3547g;
    }

    public Point a(GeoPoint geoPoint) {
        return this.M.a(geoPoint);
    }

    void a() {
        this.E = new ArrayList();
        this.am = new f();
        a(this.am);
        this.ap = new b();
        a(this.ap);
        this.J = new n();
        a(this.J);
        this.K = new com.baidu.mapsdkplatform.comapi.map.a();
        a(this.K);
        a(new p());
        this.H = new ae();
        a(this.H);
        this.aq = new c();
        a(this.aq);
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar != null) {
            aVar.e(false);
        }
        this.I = new ah();
        a(this.I);
        this.G = new g();
        a(this.G);
        this.F = new x();
        a(this.F);
    }

    public void a(float f2, float f3) {
        this.f3561a = f2;
        this.f3563c = f2;
        this.f3562b = f3;
    }

    void a(int i) {
        this.i = new com.baidu.mapsdkplatform.comjni.map.basemap.a();
        this.i.a(i);
        this.j = this.i.a();
        a("com.baidu.platform.comapi.wnplatform.walkmap.WNaviBaiduMap", "setId", this.j);
        this.l = SysOSUtil.getDensityDpi() < 180 ? 18 : SysOSUtil.getDensityDpi() < 240 ? 25 : SysOSUtil.getDensityDpi() < 320 ? 37 : 50;
        String moduleFileName = SysOSUtil.getModuleFileName();
        String appSDCardPath = EnvironmentUtilities.getAppSDCardPath();
        String appCachePath = EnvironmentUtilities.getAppCachePath();
        String appSecondCachePath = EnvironmentUtilities.getAppSecondCachePath();
        int mapTmpStgMax = EnvironmentUtilities.getMapTmpStgMax();
        int domTmpStgMax = EnvironmentUtilities.getDomTmpStgMax();
        int itsTmpStgMax = EnvironmentUtilities.getItsTmpStgMax();
        String str = SysOSUtil.getDensityDpi() >= 180 ? "/h/" : "/l/";
        String str2 = moduleFileName + "/cfg";
        String str3 = appSDCardPath + "/vmp";
        String str4 = str2 + "/a/";
        String str5 = str2 + "/a/";
        String str6 = str2 + "/idrres/";
        String str7 = str3 + str;
        String str8 = str3 + str;
        String str9 = appCachePath + "/tmp/";
        String str10 = appSecondCachePath + "/tmp/";
        Activity activityA = a(this.D);
        if (activityA == null) {
            throw new RuntimeException("BDMapSDKException: Please give the right context.");
        }
        Display defaultDisplay = activityA.getWindowManager().getDefaultDisplay();
        this.i.a(str4, str7, str9, str10, str8, str5, this.an, this.ao, str6, defaultDisplay.getWidth(), defaultDisplay.getHeight(), SysOSUtil.getDensityDpi(), mapTmpStgMax, domTmpStgMax, itsTmpStgMax, 0);
    }

    void a(int i, int i2) {
        this.P = i;
        this.Q = i2;
    }

    public void a(long j, long j2, long j3, long j4, boolean z) {
        this.i.a(j, j2, j3, j4, z);
    }

    public void a(Bitmap bitmap) {
        Bundle bundle;
        if (this.i == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put("type", 0);
            jSONObject2.put("x", N);
            jSONObject2.put("y", O);
            jSONObject2.put("hidetime", 1000);
            jSONArray.put(jSONObject2);
            jSONObject.put(AeUtil.ROOT_DATA_PATH_OLD_NAME, jSONArray);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        if (bitmap == null) {
            bundle = null;
        } else {
            Bundle bundle2 = new Bundle();
            ArrayList arrayList = new ArrayList();
            ParcelItem parcelItem = new ParcelItem();
            Bundle bundle3 = new Bundle();
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bitmap.getWidth() * bitmap.getHeight() * 4);
            bitmap.copyPixelsToBuffer(byteBufferAllocate);
            bundle3.putByteArray("imgdata", byteBufferAllocate.array());
            bundle3.putInt("imgindex", bitmap.hashCode());
            bundle3.putInt("imgH", bitmap.getHeight());
            bundle3.putInt("imgW", bitmap.getWidth());
            bundle3.putInt("hasIcon", 1);
            parcelItem.setBundle(bundle3);
            arrayList.add(parcelItem);
            if (arrayList.size() > 0) {
                ParcelItem[] parcelItemArr = new ParcelItem[arrayList.size()];
                for (int i = 0; i < arrayList.size(); i++) {
                    parcelItemArr[i] = (ParcelItem) arrayList.get(i);
                }
                bundle2.putParcelableArray("icondata", parcelItemArr);
            }
            bundle = bundle2;
        }
        b(jSONObject.toString(), bundle);
        this.i.b(this.G.f3556a);
    }

    void a(Handler handler) {
        MessageCenter.registMessage(UIMsg.m_AppUI.MSG_APP_SAVESCREEN, handler);
        MessageCenter.registMessage(39, handler);
        MessageCenter.registMessage(41, handler);
        MessageCenter.registMessage(49, handler);
        MessageCenter.registMessage(65289, handler);
        MessageCenter.registMessage(50, handler);
        MessageCenter.registMessage(GLMapStaticValue.MAPRENDER_CAN_STOP_AND_FULLSCREEN_RENDEROVER, handler);
        BaseMapCallback.addLayerDataInterface(this.j, this);
    }

    public void a(MapStatusUpdate mapStatusUpdate) {
        this.q = mapStatusUpdate;
    }

    public void a(LatLngBounds latLngBounds) {
        if (latLngBounds == null || this.i == null) {
            return;
        }
        LatLng latLng = latLngBounds.northeast;
        LatLng latLng2 = latLngBounds.southwest;
        GeoPoint geoPointLl2mc = CoordUtil.ll2mc(latLng);
        GeoPoint geoPointLl2mc2 = CoordUtil.ll2mc(latLng2);
        int longitudeE6 = (int) geoPointLl2mc.getLongitudeE6();
        int latitudeE6 = (int) geoPointLl2mc2.getLatitudeE6();
        int longitudeE62 = (int) geoPointLl2mc2.getLongitudeE6();
        int latitudeE62 = (int) geoPointLl2mc.getLatitudeE6();
        Bundle bundle = new Bundle();
        bundle.putInt("maxCoorx", longitudeE6);
        bundle.putInt("minCoory", latitudeE6);
        bundle.putInt("minCoorx", longitudeE62);
        bundle.putInt("maxCoory", latitudeE62);
        this.i.b(bundle);
    }

    public void a(ab abVar) {
        if (this.i == null || abVar == null) {
            return;
        }
        Bundle bundleA = abVar.a(this);
        bundleA.putInt("animation", 0);
        bundleA.putInt("animatime", 0);
        this.i.a(bundleA);
    }

    public void a(ab abVar, int i) {
        if (this.i == null || abVar == null) {
            return;
        }
        Bundle bundleA = abVar.a(this);
        bundleA.putInt("animation", 1);
        bundleA.putInt("animatime", i);
        if (this.as) {
            this.at.add(new a(bundleA));
        } else {
            A();
            this.i.a(bundleA);
        }
    }

    public void a(ai aiVar) {
        this.C = aiVar;
    }

    public void a(l lVar) {
        List<l> list;
        if (lVar == null || (list = this.f3567h) == null) {
            return;
        }
        list.add(lVar);
    }

    public void a(o oVar) {
        this.L = oVar;
    }

    void a(z zVar) {
        new ab();
        if (zVar == null) {
            zVar = new z();
        }
        ab abVar = zVar.f3634a;
        this.y = zVar.f3639f;
        this.z = zVar.f3637d;
        this.f3564e = zVar.f3638e;
        this.f3565f = zVar.f3640g;
        this.i.a(abVar.a(this));
        this.i.c(y.DEFAULT.ordinal());
        this.u = zVar.f3635b;
        if (zVar.f3635b) {
            N = (int) (SysOSUtil.getDensity() * 40.0f);
            O = (int) (SysOSUtil.getDensity() * 40.0f);
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("x", N);
                jSONObject2.put("y", O);
                jSONObject2.put("hidetime", 1000);
                jSONArray.put(jSONObject2);
                jSONObject.put(AeUtil.ROOT_DATA_PATH_OLD_NAME, jSONArray);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            this.G.a(jSONObject.toString());
            this.i.a(this.G.f3556a, true);
        } else {
            this.i.a(this.G.f3556a, false);
        }
        int i = zVar.f3636c;
        if (i == 2) {
            a(true);
        }
        if (i == 3) {
            this.i.a(this.am.f3556a, false);
            this.i.a(this.aq.f3556a, false);
            this.i.a(this.H.f3556a, false);
            this.i.e(false);
        }
    }

    public void a(String str, Bundle bundle) {
        if (this.i == null) {
            return;
        }
        this.F.a(str);
        this.F.a(bundle);
        this.i.b(this.F.f3556a);
    }

    public void a(List<Bundle> list) {
        if (this.i == null || list == null) {
            return;
        }
        int size = list.size();
        Bundle[] bundleArr = new Bundle[list.size()];
        for (int i = 0; i < size; i++) {
            g(list.get(i));
            bundleArr[i] = list.get(i);
        }
        this.i.a(bundleArr);
    }

    public void a(boolean z) {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar == null) {
            return;
        }
        if (!aVar.a(this.am.f3556a)) {
            this.i.a(this.am.f3556a, true);
        }
        this.t = z;
        R();
        this.i.a(this.t);
    }

    public boolean a(float f2, float f3, float f4, float f5) {
        float f6;
        boolean z;
        int i;
        int i2;
        int i3 = this.Q;
        float f7 = i3 - f3;
        float f8 = i3 - f5;
        if (!this.S.f3594e) {
            f6 = f8;
        } else {
            if (this.R == 0) {
                if ((this.S.f3592c - f7 <= 0.0f || this.S.f3593d - f8 <= 0.0f) && (this.S.f3592c - f7 >= 0.0f || this.S.f3593d - f8 >= 0.0f)) {
                    z = true;
                    this.R = 2;
                } else {
                    double dAtan2 = Math.atan2(f8 - f7, f4 - f2) - Math.atan2(this.S.f3593d - this.S.f3592c, this.S.f3591b - this.S.f3590a);
                    double dSqrt = Math.sqrt((r10 * r10) + (r5 * r5)) / this.S.f3597h;
                    int iLog = (int) ((Math.log(dSqrt) / Math.log(2.0d)) * 10000.0d);
                    int i4 = (int) ((dAtan2 * 180.0d) / 3.1416d);
                    if ((dSqrt <= 0.0d || (iLog <= 3000 && iLog >= -3000)) && Math.abs(i4) < 10) {
                        z = true;
                        this.R = 1;
                    } else {
                        this.R = 2;
                        z = true;
                    }
                }
                if (this.R == 0) {
                    return z;
                }
            } else {
                z = true;
            }
            if (this.R == z && this.y) {
                if (this.S.f3592c - f7 > 0.0f && this.S.f3593d - f8 > 0.0f) {
                    S();
                    i2 = 83;
                } else if (this.S.f3592c - f7 < 0.0f && this.S.f3593d - f8 < 0.0f) {
                    S();
                    i2 = 87;
                }
                a(1, i2, 0);
            } else {
                int i5 = this.R;
                if (i5 == 2 || i5 == 4 || i5 == 3) {
                    double dAtan22 = Math.atan2(f8 - f7, f4 - f2) - Math.atan2(this.S.f3593d - this.S.f3592c, this.S.f3591b - this.S.f3590a);
                    double dSqrt2 = Math.sqrt((r11 * r11) + (r5 * r5)) / this.S.f3597h;
                    int iLog2 = (int) ((Math.log(dSqrt2) / Math.log(2.0d)) * 10000.0d);
                    double dAtan23 = Math.atan2(this.S.f3596g - this.S.f3592c, this.S.f3595f - this.S.f3590a);
                    double dSqrt3 = Math.sqrt(((this.S.f3595f - this.S.f3590a) * (this.S.f3595f - this.S.f3590a)) + ((this.S.f3596g - this.S.f3592c) * (this.S.f3596g - this.S.f3592c)));
                    double d2 = dAtan23 + dAtan22;
                    f6 = f8;
                    float fCos = (float) ((Math.cos(d2) * dSqrt3 * dSqrt2) + ((double) f2));
                    float fSin = (float) ((dSqrt3 * Math.sin(d2) * dSqrt2) + ((double) f7));
                    int i6 = (int) ((dAtan22 * 180.0d) / 3.1416d);
                    if (dSqrt2 > 0.0d && (3 == this.R || (Math.abs(iLog2) > 2000 && 2 == this.R))) {
                        this.R = 3;
                        float f9 = E().f3518a;
                        if (this.f3565f) {
                            if (dSqrt2 <= 1.0d) {
                                i = 3;
                                if (f9 <= this.f3562b) {
                                    return false;
                                }
                                S();
                            } else {
                                if (f9 >= this.f3561a) {
                                    return false;
                                }
                                S();
                                i = 3;
                            }
                            a(8193, i, iLog2);
                        }
                    } else if (i6 != 0 && (4 == this.R || (Math.abs(i6) > 10 && 2 == this.R))) {
                        this.R = 4;
                        if (this.z) {
                            BaiduMap.mapStatusReason |= 1;
                            S();
                            a(8193, 1, i6);
                        }
                    }
                    j.a aVar = this.S;
                    aVar.f3595f = fCos;
                    aVar.f3596g = fSin;
                }
            }
            f6 = f8;
        }
        if (2 != this.R) {
            j.a aVar2 = this.S;
            aVar2.f3592c = f7;
            aVar2.f3593d = f6;
            aVar2.f3590a = f2;
            aVar2.f3591b = f4;
        }
        if (this.S.f3594e) {
            return true;
        }
        j.a aVar3 = this.S;
        aVar3.f3595f = this.P / 2;
        aVar3.f3596g = this.Q / 2;
        aVar3.f3594e = true;
        if (0.0d != aVar3.f3597h) {
            return true;
        }
        this.S.f3597h = Math.sqrt(((this.S.f3591b - this.S.f3590a) * (this.S.f3591b - this.S.f3590a)) + ((this.S.f3593d - this.S.f3592c) * (this.S.f3593d - this.S.f3592c)));
        return true;
    }

    @Override // com.baidu.mapsdkplatform.comjni.map.basemap.b
    public boolean a(long j) {
        Iterator<d> it = this.E.iterator();
        while (it.hasNext()) {
            if (it.next().f3556a == j) {
                return true;
            }
        }
        return false;
    }

    public boolean a(Point point) {
        if (point == null || this.i == null || point.x < 0 || point.y < 0) {
            return false;
        }
        N = point.x;
        O = point.y;
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("x", N);
            jSONObject2.put("y", O);
            jSONObject2.put("hidetime", 1000);
            jSONArray.put(jSONObject2);
            jSONObject.put(AeUtil.ROOT_DATA_PATH_OLD_NAME, jSONArray);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        this.G.a(jSONObject.toString());
        this.i.b(this.G.f3556a);
        return true;
    }

    public boolean a(Bundle bundle) {
        if (this.i == null) {
            return false;
        }
        this.B = new aj();
        long jA = this.i.a(this.B.f3558c, this.B.f3559d, this.B.f3557b);
        if (jA != 0) {
            aj ajVar = this.B;
            ajVar.f3556a = jA;
            this.E.add(ajVar);
            bundle.putLong("sdktileaddr", jA);
            if (e(bundle) && f(bundle)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:140:0x0351  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0363  */
    /* JADX WARN: Removed duplicated region for block: B:160:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0124  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    boolean a(android.view.MotionEvent r24) {
        /*
            Method dump skipped, instruction units count: 974
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapsdkplatform.comapi.map.e.a(android.view.MotionEvent):boolean");
    }

    public boolean a(String str, String str2) {
        return this.i.a(str, str2);
    }

    public GeoPoint b(int i, int i2) {
        return this.M.a(i, i2);
    }

    void b(float f2, float f3) {
        if (this.S.f3594e) {
            return;
        }
        this.ad = System.currentTimeMillis();
        long j = this.ad;
        if (j - this.ac >= 400) {
            this.ac = j;
        } else if (Math.abs(f2 - this.Z) >= 120.0f || Math.abs(f3 - this.aa) >= 120.0f) {
            j = this.ad;
            this.ac = j;
        } else {
            this.ac = 0L;
            this.ae = true;
        }
        this.Z = f2;
        this.aa = f3;
        a(4, 0, ((int) f2) | (((int) f3) << 16));
        this.ab = true;
    }

    void b(int i) {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar != null) {
            aVar.b(i);
            this.i = null;
        }
    }

    public void b(Bundle bundle) {
        if (this.i == null) {
            return;
        }
        g(bundle);
        this.i.f(bundle);
    }

    void b(Handler handler) {
        MessageCenter.unregistMessage(UIMsg.m_AppUI.MSG_APP_SAVESCREEN, handler);
        MessageCenter.unregistMessage(41, handler);
        MessageCenter.unregistMessage(49, handler);
        MessageCenter.unregistMessage(39, handler);
        MessageCenter.unregistMessage(65289, handler);
        MessageCenter.unregistMessage(50, handler);
        MessageCenter.unregistMessage(GLMapStaticValue.MAPRENDER_CAN_STOP_AND_FULLSCREEN_RENDEROVER, handler);
        BaseMapCallback.removeLayerDataInterface(this.j);
    }

    public void b(boolean z) {
        this.A = z;
    }

    public boolean b() {
        return this.A;
    }

    public void c() {
        if (this.i == null) {
            return;
        }
        Iterator<d> it = this.E.iterator();
        while (it.hasNext()) {
            this.i.a(it.next().f3556a, false);
        }
    }

    public void c(Bundle bundle) {
        if (this.i == null) {
            return;
        }
        g(bundle);
        this.i.g(bundle);
    }

    public void c(boolean z) {
        boolean z2;
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar == null) {
            return;
        }
        if (z) {
            if (this.au) {
                return;
            }
            aVar.a(this.ap.f3556a, this.I.f3556a);
            z2 = true;
        } else {
            if (!this.au) {
                return;
            }
            aVar.a(this.I.f3556a, this.ap.f3556a);
            z2 = false;
        }
        this.au = z2;
    }

    boolean c(float f2, float f3) {
        if (this.S.f3594e || System.currentTimeMillis() - m < 300) {
            return true;
        }
        if (this.p) {
            List<l> list = this.f3567h;
            if (list != null) {
                for (l lVar : list) {
                    GeoPoint geoPointB = b((int) f2, (int) f3);
                    if (lVar != null) {
                        lVar.d(geoPointB);
                    }
                }
            }
            return true;
        }
        float fAbs = Math.abs(f2 - this.Z);
        float fAbs2 = Math.abs(f3 - this.aa);
        float density = (float) (((double) SysOSUtil.getDensity()) > 1.5d ? ((double) SysOSUtil.getDensity()) * 1.5d : SysOSUtil.getDensity());
        if (this.ab && fAbs / density <= 3.0f && fAbs2 / density <= 3.0f) {
            return true;
        }
        this.ab = false;
        int i = (int) f2;
        int i2 = (int) f3;
        if (i < 0) {
            i = 0;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        if (this.f3564e) {
            this.ag = this.ai;
            this.ah = this.aj;
            this.ai = f2;
            this.aj = f3;
            this.ak = this.al;
            this.al = System.currentTimeMillis();
            this.af = true;
            S();
            a(3, 0, (i2 << 16) | i);
        }
        return false;
    }

    boolean c(int i, int i2) {
        return i >= 0 && i <= this.P + 0 && i2 >= 0 && i2 <= this.Q + 0;
    }

    public void d() {
        if (this.i == null) {
            return;
        }
        for (d dVar : this.E) {
            if ((dVar instanceof x) || (dVar instanceof com.baidu.mapsdkplatform.comapi.map.a) || (dVar instanceof n)) {
                this.i.a(dVar.f3556a, false);
            } else {
                this.i.a(dVar.f3556a, true);
            }
        }
        this.i.c(false);
    }

    public void d(Bundle bundle) {
        if (this.i == null) {
            return;
        }
        g(bundle);
        this.i.h(bundle);
    }

    public void d(boolean z) {
        boolean z2;
        if (z) {
            if (this.av) {
                return;
            }
            this.i.a(this.I.f3556a, this.F.f3556a);
            z2 = true;
        } else {
            if (!this.av) {
                return;
            }
            this.i.a(this.F.f3556a, this.I.f3556a);
            z2 = false;
        }
        this.av = z2;
    }

    boolean d(float f2, float f3) {
        if (this.p) {
            List<l> list = this.f3567h;
            if (list != null) {
                for (l lVar : list) {
                    GeoPoint geoPointB = b((int) f2, (int) f3);
                    if (lVar != null) {
                        lVar.e(geoPointB);
                    }
                }
            }
            this.p = false;
            return true;
        }
        if (!this.S.f3594e) {
            if (this.ae) {
                return e(f2, f3);
            }
            if (this.af) {
                return T();
            }
            if (System.currentTimeMillis() - this.ad < 400 && Math.abs(f2 - this.Z) < 10.0f && Math.abs(f3 - this.aa) < 10.0f) {
                M();
                return true;
            }
        }
        M();
        int i = (int) f2;
        int i2 = (int) f3;
        if (i < 0) {
            i = 0;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        a(5, 0, i | (i2 << 16));
        return true;
    }

    public void e(boolean z) {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar == null) {
            return;
        }
        aVar.a(this.G.f3556a, z);
    }

    public boolean e() {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar;
        aj ajVar = this.B;
        if (ajVar == null || (aVar = this.i) == null) {
            return false;
        }
        return aVar.c(ajVar.f3556a);
    }

    void f() {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar == null) {
            return;
        }
        this.M = new af(aVar);
    }

    public void f(boolean z) {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar == null) {
            return;
        }
        aVar.a(this.B.f3556a, z);
    }

    public void g(boolean z) {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar == null) {
            return;
        }
        aVar.a(this.am.f3556a, z);
    }

    public boolean g() {
        return this.s;
    }

    public String h() {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar == null) {
            return null;
        }
        return aVar.e(this.G.f3556a);
    }

    public void h(boolean z) {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar == null) {
            return;
        }
        this.x = z;
        aVar.b(this.x);
    }

    public void i(boolean z) {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar == null) {
            return;
        }
        this.s = z;
        aVar.c(this.s);
    }

    public boolean i() {
        return this.x;
    }

    public void j(boolean z) {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar == null) {
            return;
        }
        aVar.d(z);
    }

    public boolean j() {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar == null) {
            return false;
        }
        return aVar.k();
    }

    public void k(boolean z) {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar == null) {
            return;
        }
        this.u = z;
        aVar.a(this.G.f3556a, z);
    }

    public boolean k() {
        return this.t;
    }

    public void l(boolean z) {
        float f2 = z ? 22.0f : 21.0f;
        this.f3561a = f2;
        this.f3563c = f2;
        this.i.e(z);
        this.i.d(this.ap.f3556a);
        this.i.d(this.aq.f3556a);
    }

    public boolean l() {
        return this.i.a(this.am.f3556a);
    }

    public boolean m() {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar == null) {
            return false;
        }
        return aVar.o();
    }

    public void n() {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar == null) {
            return;
        }
        aVar.d(this.I.f3556a);
    }

    public void n(boolean z) {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar == null) {
            return;
        }
        this.v = z;
        aVar.a(this.F.f3556a, z);
    }

    public void o() {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar == null) {
            return;
        }
        aVar.p();
        this.i.b(this.J.f3556a);
    }

    public void o(boolean z) {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar == null) {
            return;
        }
        this.w = z;
        aVar.a(this.J.f3556a, z);
    }

    public MapBaseIndoorMapInfo p() {
        return this.i.q();
    }

    public void p(boolean z) {
        this.f3564e = z;
    }

    public void q(boolean z) {
        this.f3565f = z;
    }

    public boolean q() {
        return this.i.r();
    }

    public void r(boolean z) {
        this.f3566g = z;
    }

    public boolean r() {
        return this.u;
    }

    public void s(boolean z) {
        this.z = z;
    }

    public boolean s() {
        return this.v;
    }

    public void t() {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar == null) {
            return;
        }
        aVar.b(this.J.f3556a);
    }

    public void t(boolean z) {
        this.y = z;
    }

    void u() {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar == null) {
            return;
        }
        aVar.e();
    }

    public void u(boolean z) {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar != null) {
            aVar.a(this.H.f3556a, z);
        }
    }

    void v() {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar == null) {
            return;
        }
        aVar.f();
    }

    public void v(boolean z) {
        com.baidu.mapsdkplatform.comjni.map.basemap.a aVar = this.i;
        if (aVar != null) {
            aVar.a(this.aq.f3556a, z);
        }
    }

    public void w(boolean z) {
        this.as = z;
    }

    public boolean w() {
        return this.f3564e;
    }

    public boolean x() {
        return this.f3565f;
    }

    public boolean y() {
        return this.z;
    }

    public boolean z() {
        return this.y;
    }
}