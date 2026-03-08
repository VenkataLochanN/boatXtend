package com.baidu.location;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import com.baidu.location.a.c;
import com.baidu.location.a.j;
import com.baidu.location.a.k;
import com.bumptech.glide.load.Key;
import com.ido.life.constants.Constants;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class LocationClient implements c.a {
    public static final int CONNECT_HOT_SPOT_FALSE = 0;
    public static final int CONNECT_HOT_SPOT_TRUE = 1;
    public static final int CONNECT_HOT_SPOT_UNKNOWN = -1;
    public static final int LOC_DIAGNOSTIC_TYPE_BETTER_OPEN_GPS = 1;
    public static final int LOC_DIAGNOSTIC_TYPE_BETTER_OPEN_WIFI = 2;
    public static final int LOC_DIAGNOSTIC_TYPE_FAIL_UNKNOWN = 9;
    public static final int LOC_DIAGNOSTIC_TYPE_NEED_CHECK_LOC_PERMISSION = 4;
    public static final int LOC_DIAGNOSTIC_TYPE_NEED_CHECK_NET = 3;
    public static final int LOC_DIAGNOSTIC_TYPE_NEED_CLOSE_FLYMODE = 7;
    public static final int LOC_DIAGNOSTIC_TYPE_NEED_INSERT_SIMCARD_OR_OPEN_WIFI = 6;
    public static final int LOC_DIAGNOSTIC_TYPE_NEED_OPEN_PHONE_LOC_SWITCH = 5;
    public static final int LOC_DIAGNOSTIC_TYPE_SERVER_FAIL = 8;
    private Boolean A;
    private Boolean B;
    private Boolean C;
    private boolean D;
    private com.baidu.location.a.c E;
    private boolean F;
    private boolean G;
    private boolean H;
    private ServiceConnection I;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f2034a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f2035b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private LocationClientOption f2036c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private LocationClientOption f2037d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f2038e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Context f2039f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Messenger f2040g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private a f2041h;
    private final Messenger i;
    private ArrayList<BDLocationListener> j;
    private ArrayList<BDAbstractLocationListener> k;
    private BDLocation l;
    private boolean m;
    private boolean n;
    private boolean o;
    private b p;
    private boolean q;
    private final Object r;
    private long s;
    private long t;
    private com.baidu.location.c.a u;
    private BDLocationListener v;
    private String w;
    private String x;
    private boolean y;
    private boolean z;

    /* JADX INFO: Access modifiers changed from: private */
    static class a extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final WeakReference<LocationClient> f2042a;

        a(Looper looper, LocationClient locationClient) {
            super(looper);
            this.f2042a = new WeakReference<>(locationClient);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            LocationClient locationClient = this.f2042a.get();
            if (locationClient == null) {
                return;
            }
            int i = message.what;
            int i2 = 21;
            boolean z = true;
            if (i != 21) {
                try {
                    if (i == 303) {
                        Bundle data = message.getData();
                        int i3 = data.getInt("loctype");
                        int i4 = data.getInt("diagtype");
                        byte[] byteArray = data.getByteArray("diagmessage");
                        if (i3 <= 0 || i4 <= 0 || byteArray == null || locationClient.k == null) {
                            return;
                        }
                        Iterator it = locationClient.k.iterator();
                        while (it.hasNext()) {
                            ((BDAbstractLocationListener) it.next()).onLocDiagnosticMessage(i3, i4, new String(byteArray, Key.STRING_CHARSET_NAME));
                        }
                        return;
                    }
                    if (i == 406) {
                        Bundle data2 = message.getData();
                        byte[] byteArray2 = data2.getByteArray("mac");
                        String str = byteArray2 != null ? new String(byteArray2, Key.STRING_CHARSET_NAME) : null;
                        int i5 = data2.getInt("hotspot", -1);
                        if (locationClient.k != null) {
                            Iterator it2 = locationClient.k.iterator();
                            while (it2.hasNext()) {
                                ((BDAbstractLocationListener) it2.next()).onConnectHotSpotMessage(str, i5);
                            }
                            return;
                        }
                        return;
                    }
                    if (i == 701) {
                        locationClient.a((BDLocation) message.obj);
                        return;
                    }
                    if (i == 1300) {
                        locationClient.f(message);
                        return;
                    }
                    if (i == 1400) {
                        locationClient.g(message);
                        return;
                    }
                    i2 = 26;
                    if (i != 26) {
                        if (i == 27) {
                            locationClient.i(message);
                            return;
                        }
                        if (i != 54) {
                            z = false;
                            if (i != 55) {
                                if (i == 703) {
                                    Bundle data3 = message.getData();
                                    int i6 = data3.getInt("id", 0);
                                    if (i6 > 0) {
                                        locationClient.a(i6, (Notification) data3.getParcelable("notification"));
                                        return;
                                    }
                                    return;
                                }
                                if (i == 704) {
                                    locationClient.a(message.getData().getBoolean("removenotify"));
                                    return;
                                }
                                switch (i) {
                                    case 1:
                                        locationClient.b();
                                        break;
                                    case 2:
                                        locationClient.c();
                                        break;
                                    case 3:
                                        locationClient.c(message);
                                        break;
                                    case 4:
                                        locationClient.f();
                                        break;
                                    case 5:
                                        locationClient.e(message);
                                        break;
                                    case 6:
                                        locationClient.h(message);
                                        break;
                                    case 7:
                                        break;
                                    case 8:
                                        locationClient.d(message);
                                        break;
                                    case 9:
                                        locationClient.a(message);
                                        break;
                                    case 10:
                                        locationClient.b(message);
                                        break;
                                    case 11:
                                        locationClient.e();
                                        break;
                                    case 12:
                                        locationClient.a();
                                        break;
                                    default:
                                        super.handleMessage(message);
                                        break;
                                }
                                return;
                            }
                            if (!locationClient.f2036c.location_change_notify) {
                                return;
                            }
                        } else if (!locationClient.f2036c.location_change_notify) {
                            return;
                        }
                        locationClient.q = z;
                        return;
                    }
                } catch (Exception unused) {
                    return;
                }
            } else {
                Bundle data4 = message.getData();
                data4.setClassLoader(BDLocation.class.getClassLoader());
                BDLocation bDLocation = (BDLocation) data4.getParcelable("locStr");
                if (!locationClient.G && locationClient.F && bDLocation.getLocType() == 66) {
                    return;
                }
                if (!locationClient.G && locationClient.F) {
                    locationClient.G = true;
                    return;
                } else if (!locationClient.G) {
                    locationClient.G = true;
                }
            }
            locationClient.a(message, i2);
        }
    }

    private class b implements Runnable {
        private b() {
        }

        /* synthetic */ b(LocationClient locationClient, com.baidu.location.b bVar) {
            this();
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (LocationClient.this.r) {
                LocationClient.this.o = false;
                if (LocationClient.this.f2040g != null && LocationClient.this.i != null) {
                    if ((LocationClient.this.j != null && LocationClient.this.j.size() >= 1) || (LocationClient.this.k != null && LocationClient.this.k.size() >= 1)) {
                        if (!LocationClient.this.n) {
                            LocationClient.this.f2041h.obtainMessage(4).sendToTarget();
                            return;
                        }
                        if (LocationClient.this.p == null) {
                            LocationClient.this.p = LocationClient.this.new b();
                        }
                        LocationClient.this.f2041h.postDelayed(LocationClient.this.p, LocationClient.this.f2036c.scanSpan);
                    }
                }
            }
        }
    }

    public LocationClient(Context context) {
        this.f2034a = 0L;
        this.f2035b = null;
        this.f2036c = new LocationClientOption();
        this.f2037d = new LocationClientOption();
        this.f2038e = false;
        this.f2039f = null;
        this.f2040g = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = false;
        this.n = false;
        this.o = false;
        this.p = null;
        this.q = false;
        this.r = new Object();
        this.s = 0L;
        this.t = 0L;
        this.u = null;
        this.v = null;
        this.w = null;
        this.y = false;
        this.z = true;
        this.A = false;
        this.B = false;
        this.C = true;
        this.E = null;
        this.F = false;
        this.G = false;
        this.H = false;
        this.I = new com.baidu.location.b(this);
        this.f2039f = context;
        this.f2036c = new LocationClientOption();
        this.f2041h = new a(Looper.getMainLooper(), this);
        this.i = new Messenger(this.f2041h);
    }

    public LocationClient(Context context, LocationClientOption locationClientOption) {
        this.f2034a = 0L;
        this.f2035b = null;
        this.f2036c = new LocationClientOption();
        this.f2037d = new LocationClientOption();
        this.f2038e = false;
        this.f2039f = null;
        this.f2040g = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = false;
        this.n = false;
        this.o = false;
        this.p = null;
        this.q = false;
        this.r = new Object();
        this.s = 0L;
        this.t = 0L;
        this.u = null;
        this.v = null;
        this.w = null;
        this.y = false;
        this.z = true;
        this.A = false;
        this.B = false;
        this.C = true;
        this.E = null;
        this.F = false;
        this.G = false;
        this.H = false;
        this.I = new com.baidu.location.b(this);
        this.f2039f = context;
        this.f2036c = locationClientOption;
        this.f2037d = new LocationClientOption(locationClientOption);
        this.f2041h = new a(Looper.getMainLooper(), this);
        this.i = new Messenger(this.f2041h);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        Message messageObtain = Message.obtain((Handler) null, 28);
        try {
            messageObtain.replyTo = this.i;
            this.f2040g.send(messageObtain);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, Notification notification) {
        try {
            Intent intent = new Intent(this.f2039f, (Class<?>) f.class);
            intent.putExtra("notification", notification);
            intent.putExtra("id", i);
            intent.putExtra("command", 1);
            if (Build.VERSION.SDK_INT >= 26) {
                this.f2039f.startForegroundService(intent);
            } else {
                this.f2039f.startService(intent);
            }
            this.H = true;
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Message message) {
        if (message == null || message.obj == null) {
            return;
        }
        BDNotifyListener bDNotifyListener = (BDNotifyListener) message.obj;
        if (this.u == null) {
            this.u = new com.baidu.location.c.a(this.f2039f, this);
        }
        this.u.a(bDNotifyListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Message message, int i) {
        if (this.f2038e) {
            try {
                Bundle data = message.getData();
                data.setClassLoader(BDLocation.class.getClassLoader());
                this.l = (BDLocation) data.getParcelable("locStr");
                if (this.l.getLocType() == 61) {
                    this.s = System.currentTimeMillis();
                }
                b(i);
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BDLocation bDLocation) {
        if (this.z) {
            return;
        }
        this.l = bDLocation;
        if (!this.G && bDLocation.getLocType() == 161) {
            this.F = true;
        }
        ArrayList<BDLocationListener> arrayList = this.j;
        if (arrayList != null) {
            Iterator<BDLocationListener> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().onReceiveLocation(bDLocation);
            }
        }
        ArrayList<BDAbstractLocationListener> arrayList2 = this.k;
        if (arrayList2 != null) {
            Iterator<BDAbstractLocationListener> it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                it2.next().onReceiveLocation(bDLocation);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        try {
            Intent intent = new Intent(this.f2039f, (Class<?>) f.class);
            intent.putExtra("removenotify", z);
            intent.putExtra("command", 2);
            this.f2039f.startService(intent);
            this.H = true;
        } catch (Exception unused) {
        }
    }

    private boolean a(int i) {
        if (this.f2040g != null && this.f2038e) {
            try {
                this.f2040g.send(Message.obtain((Handler) null, i));
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.f2038e) {
            return;
        }
        if (this.C.booleanValue()) {
            try {
                new c(this).start();
            } catch (Throwable unused) {
            }
            this.C = false;
        }
        this.f2035b = this.f2039f.getPackageName();
        this.w = this.f2035b + "_bdls_v2.9";
        Intent intent = new Intent(this.f2039f, (Class<?>) f.class);
        try {
            intent.putExtra("debug_dev", this.D);
        } catch (Exception unused2) {
        }
        if (this.f2036c == null) {
            this.f2036c = new LocationClientOption();
        }
        intent.putExtra("cache_exception", this.f2036c.isIgnoreCacheException);
        intent.putExtra("kill_process", this.f2036c.isIgnoreKillProcess);
        try {
            this.f2039f.bindService(intent, this.I, 1);
        } catch (Exception e2) {
            e2.printStackTrace();
            this.f2038e = false;
        }
    }

    private void b(int i) {
        if (this.l.getCoorType() == null) {
            this.l.setCoorType(this.f2036c.coorType);
        }
        if (this.m || ((this.f2036c.location_change_notify && this.l.getLocType() == 61) || this.l.getLocType() == 66 || this.l.getLocType() == 67 || this.y || this.l.getLocType() == 161)) {
            ArrayList<BDLocationListener> arrayList = this.j;
            if (arrayList != null) {
                Iterator<BDLocationListener> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().onReceiveLocation(this.l);
                }
            }
            ArrayList<BDAbstractLocationListener> arrayList2 = this.k;
            if (arrayList2 != null) {
                Iterator<BDAbstractLocationListener> it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    it2.next().onReceiveLocation(this.l);
                }
            }
            if (this.l.getLocType() == 66 || this.l.getLocType() == 67) {
                return;
            }
            this.m = false;
            this.t = System.currentTimeMillis();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Message message) {
        if (message == null || message.obj == null) {
            return;
        }
        BDNotifyListener bDNotifyListener = (BDNotifyListener) message.obj;
        com.baidu.location.c.a aVar = this.u;
        if (aVar != null) {
            aVar.c(bDNotifyListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (!this.f2038e || this.f2040g == null) {
            return;
        }
        Message messageObtain = Message.obtain((Handler) null, 12);
        messageObtain.replyTo = this.i;
        try {
            this.f2040g.send(messageObtain);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            this.f2039f.unbindService(this.I);
            if (this.H) {
                try {
                    this.f2039f.stopService(new Intent(this.f2039f, (Class<?>) f.class));
                } catch (Exception unused) {
                }
                this.H = false;
            }
        } catch (Exception unused2) {
        }
        synchronized (this.r) {
            try {
                if (this.o) {
                    this.f2041h.removeCallbacks(this.p);
                    this.o = false;
                }
            } catch (Exception unused3) {
            }
        }
        com.baidu.location.c.a aVar = this.u;
        if (aVar != null) {
            aVar.a();
        }
        this.f2040g = null;
        this.n = false;
        this.y = false;
        this.f2038e = false;
        this.F = false;
        this.G = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Message message) {
        this.n = false;
        if (message == null || message.obj == null) {
            return;
        }
        LocationClientOption locationClientOption = (LocationClientOption) message.obj;
        if (this.f2036c.optionEquals(locationClientOption)) {
            return;
        }
        com.baidu.location.b bVar = null;
        if (this.f2036c.scanSpan != locationClientOption.scanSpan) {
            try {
                synchronized (this.r) {
                    if (this.o) {
                        this.f2041h.removeCallbacks(this.p);
                        this.o = false;
                    }
                    if (locationClientOption.scanSpan >= 1000 && !this.o) {
                        if (this.p == null) {
                            this.p = new b(this, bVar);
                        }
                        this.f2041h.postDelayed(this.p, locationClientOption.scanSpan);
                        this.o = true;
                    }
                }
            } catch (Exception unused) {
            }
        }
        this.f2036c = new LocationClientOption(locationClientOption);
        if (this.f2040g == null) {
            return;
        }
        try {
            Message messageObtain = Message.obtain((Handler) null, 15);
            messageObtain.replyTo = this.i;
            messageObtain.setData(d());
            this.f2040g.send(messageObtain);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bundle d() {
        if (this.f2036c == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("packName", this.f2035b);
        bundle.putString("prodName", this.f2036c.prodName);
        bundle.putString("coorType", this.f2036c.coorType);
        bundle.putString("addrType", this.f2036c.addrType);
        bundle.putBoolean("openGPS", this.f2036c.openGps);
        bundle.putBoolean("location_change_notify", this.f2036c.location_change_notify);
        bundle.putInt("scanSpan", this.f2036c.scanSpan);
        bundle.putBoolean("enableSimulateGps", this.f2036c.enableSimulateGps);
        bundle.putInt("timeOut", this.f2036c.timeOut);
        bundle.putInt("priority", this.f2036c.priority);
        bundle.putBoolean("map", this.A.booleanValue());
        bundle.putBoolean("import", this.B.booleanValue());
        bundle.putBoolean("needDirect", this.f2036c.mIsNeedDeviceDirect);
        bundle.putBoolean("isneedaptag", this.f2036c.isNeedAptag);
        bundle.putBoolean("isneedpoiregion", this.f2036c.isNeedPoiRegion);
        bundle.putBoolean("isneedregular", this.f2036c.isNeedRegular);
        bundle.putBoolean("isneedaptagd", this.f2036c.isNeedAptagd);
        bundle.putBoolean("isneedaltitude", this.f2036c.isNeedAltitude);
        bundle.putBoolean("isneednewrgc", this.f2036c.isNeedNewVersionRgc);
        bundle.putInt("autoNotifyMaxInterval", this.f2036c.a());
        bundle.putInt("autoNotifyMinTimeInterval", this.f2036c.getAutoNotifyMinTimeInterval());
        bundle.putInt("autoNotifyMinDistance", this.f2036c.getAutoNotifyMinDistance());
        bundle.putFloat("autoNotifyLocSensitivity", this.f2036c.b());
        bundle.putInt("wifitimeout", this.f2036c.wifiCacheTimeOut);
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Message message) {
        if (message == null || message.obj == null) {
            return;
        }
        this.v = (BDLocationListener) message.obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.f2040g == null) {
            return;
        }
        Message messageObtain = Message.obtain((Handler) null, 22);
        try {
            messageObtain.replyTo = this.i;
            this.f2040g.send(messageObtain);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Message message) {
        if (message == null || message.obj == null) {
            return;
        }
        BDLocationListener bDLocationListener = (BDLocationListener) message.obj;
        if (this.j == null) {
            this.j = new ArrayList<>();
        }
        if (this.j.contains(bDLocationListener)) {
            return;
        }
        this.j.add(bDLocationListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        LocationClientOption locationClientOption;
        if (this.f2040g == null) {
            return;
        }
        com.baidu.location.b bVar = null;
        if ((System.currentTimeMillis() - this.s > 3000 || (((locationClientOption = this.f2036c) != null && !locationClientOption.location_change_notify) || this.n)) && (!this.y || System.currentTimeMillis() - this.t > 20000 || this.n)) {
            Message messageObtain = Message.obtain((Handler) null, 22);
            if (this.n) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("isWaitingLocTag", this.n);
                this.n = false;
                messageObtain.setData(bundle);
            }
            try {
                messageObtain.replyTo = this.i;
                this.f2040g.send(messageObtain);
                this.f2034a = System.currentTimeMillis();
                this.m = true;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        synchronized (this.r) {
            if (this.f2036c != null && this.f2036c.scanSpan >= 1000 && !this.o) {
                if (this.p == null) {
                    this.p = new b(this, bVar);
                }
                this.f2041h.postDelayed(this.p, this.f2036c.scanSpan);
                this.o = true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(Message message) {
        if (message == null || message.obj == null) {
            return;
        }
        BDAbstractLocationListener bDAbstractLocationListener = (BDAbstractLocationListener) message.obj;
        if (this.k == null) {
            this.k = new ArrayList<>();
        }
        if (this.k.contains(bDAbstractLocationListener)) {
            return;
        }
        this.k.add(bDAbstractLocationListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(Message message) {
        if (message == null || message.obj == null) {
            return;
        }
        BDAbstractLocationListener bDAbstractLocationListener = (BDAbstractLocationListener) message.obj;
        ArrayList<BDAbstractLocationListener> arrayList = this.k;
        if (arrayList == null || !arrayList.contains(bDAbstractLocationListener)) {
            return;
        }
        this.k.remove(bDAbstractLocationListener);
    }

    public static BDLocation getBDLocationInCoorType(BDLocation bDLocation, String str) {
        BDLocation bDLocation2 = new BDLocation(bDLocation);
        double[] dArrCoorEncrypt = Jni.coorEncrypt(bDLocation.getLongitude(), bDLocation.getLatitude(), str);
        bDLocation2.setLatitude(dArrCoorEncrypt[1]);
        bDLocation2.setLongitude(dArrCoorEncrypt[0]);
        return bDLocation2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(Message message) {
        if (message == null || message.obj == null) {
            return;
        }
        BDLocationListener bDLocationListener = (BDLocationListener) message.obj;
        ArrayList<BDLocationListener> arrayList = this.j;
        if (arrayList == null || !arrayList.contains(bDLocationListener)) {
            return;
        }
        this.j.remove(bDLocationListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(Message message) {
        try {
            Bundle data = message.getData();
            data.setClassLoader(BDLocation.class.getClassLoader());
            BDLocation bDLocation = (BDLocation) data.getParcelable("locStr");
            if (this.v != null) {
                if (this.f2036c != null && this.f2036c.isDisableCache() && bDLocation.getLocType() == 65) {
                    return;
                }
                this.v.onReceiveLocation(bDLocation);
            }
        } catch (Exception unused) {
        }
    }

    public void disableAssistantLocation() {
        k.a().b();
    }

    public void disableLocInForeground(boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("removenotify", z);
        Message messageObtainMessage = this.f2041h.obtainMessage(704);
        messageObtainMessage.setData(bundle);
        messageObtainMessage.sendToTarget();
    }

    public void enableAssistantLocation(WebView webView) {
        k.a().a(this.f2039f, webView, this);
    }

    public void enableLocInForeground(int i, Notification notification) {
        if (i <= 0 || notification == null) {
            Log.e("baidu_location_Client", "can not startLocInForeground if the param is unlegal");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("id", i);
        bundle.putParcelable("notification", notification);
        Message messageObtainMessage = this.f2041h.obtainMessage(703);
        messageObtainMessage.setData(bundle);
        messageObtainMessage.sendToTarget();
    }

    public String getAccessKey() {
        try {
            this.x = j.b(this.f2039f);
            if (TextUtils.isEmpty(this.x)) {
                throw new IllegalStateException("please setting key from Manifest.xml");
            }
            return String.format("KEY=%s", this.x);
        } catch (Exception unused) {
            return null;
        }
    }

    public BDLocation getLastKnownLocation() {
        return this.l;
    }

    public LocationClientOption getLocOption() {
        return this.f2036c;
    }

    public String getVersion() {
        return "7.7.2";
    }

    public boolean isStarted() {
        return this.f2038e;
    }

    @Override // com.baidu.location.a.c.a
    public void onReceiveLocation(BDLocation bDLocation) {
        if ((!this.G || this.F) && bDLocation != null) {
            Message messageObtainMessage = this.f2041h.obtainMessage(701);
            messageObtainMessage.obj = bDLocation;
            messageObtainMessage.sendToTarget();
        }
    }

    public void registerLocationListener(BDAbstractLocationListener bDAbstractLocationListener) {
        if (bDAbstractLocationListener == null) {
            throw new IllegalStateException("please set a non-null listener");
        }
        Message messageObtainMessage = this.f2041h.obtainMessage(1300);
        messageObtainMessage.obj = bDAbstractLocationListener;
        messageObtainMessage.sendToTarget();
    }

    public void registerLocationListener(BDLocationListener bDLocationListener) {
        if (bDLocationListener == null) {
            throw new IllegalStateException("please set a non-null listener");
        }
        Message messageObtainMessage = this.f2041h.obtainMessage(5);
        messageObtainMessage.obj = bDLocationListener;
        messageObtainMessage.sendToTarget();
    }

    public void registerNotify(BDNotifyListener bDNotifyListener) {
        Message messageObtainMessage = this.f2041h.obtainMessage(9);
        messageObtainMessage.obj = bDNotifyListener;
        messageObtainMessage.sendToTarget();
    }

    public void registerNotifyLocationListener(BDLocationListener bDLocationListener) {
        Message messageObtainMessage = this.f2041h.obtainMessage(8);
        messageObtainMessage.obj = bDLocationListener;
        messageObtainMessage.sendToTarget();
    }

    public void removeNotifyEvent(BDNotifyListener bDNotifyListener) {
        Message messageObtainMessage = this.f2041h.obtainMessage(10);
        messageObtainMessage.obj = bDNotifyListener;
        messageObtainMessage.sendToTarget();
    }

    public boolean requestHotSpotState() {
        if (this.f2040g != null && this.f2038e) {
            try {
                this.f2040g.send(Message.obtain((Handler) null, Constants.BindErrorCode.CONNECTED_FAILED_DISCOVER_SERVICE_FAILED));
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public int requestLocation() {
        ArrayList<BDAbstractLocationListener> arrayList;
        if (this.f2040g == null || this.i == null) {
            return 1;
        }
        ArrayList<BDLocationListener> arrayList2 = this.j;
        if ((arrayList2 == null || arrayList2.size() < 1) && ((arrayList = this.k) == null || arrayList.size() < 1)) {
            return 2;
        }
        if (System.currentTimeMillis() - this.f2034a < 1000) {
            return 6;
        }
        this.n = true;
        Message messageObtainMessage = this.f2041h.obtainMessage(4);
        messageObtainMessage.arg1 = 0;
        messageObtainMessage.sendToTarget();
        return 0;
    }

    public void requestNotifyLocation() {
        this.f2041h.obtainMessage(11).sendToTarget();
    }

    public int requestOfflineLocation() {
        ArrayList<BDAbstractLocationListener> arrayList;
        if (this.f2040g == null || this.i == null) {
            return 1;
        }
        ArrayList<BDLocationListener> arrayList2 = this.j;
        if ((arrayList2 == null || arrayList2.size() < 1) && ((arrayList = this.k) == null || arrayList.size() < 1)) {
            return 2;
        }
        this.f2041h.obtainMessage(12).sendToTarget();
        return 0;
    }

    public void restart() {
        stop();
        this.z = false;
        this.f2041h.sendEmptyMessageDelayed(1, 1000L);
    }

    public void setLocOption(LocationClientOption locationClientOption) {
        if (locationClientOption == null) {
            locationClientOption = new LocationClientOption();
        }
        if (locationClientOption.a() > 0) {
            locationClientOption.setScanSpan(0);
            locationClientOption.setLocationNotify(true);
        }
        this.f2037d = new LocationClientOption(locationClientOption);
        Message messageObtainMessage = this.f2041h.obtainMessage(3);
        messageObtainMessage.obj = locationClientOption;
        messageObtainMessage.sendToTarget();
    }

    public void start() {
        this.z = false;
        if (com.baidu.location.g.k.b()) {
            return;
        }
        this.f2041h.obtainMessage(1).sendToTarget();
    }

    public boolean startIndoorMode() {
        boolean zA = a(110);
        if (zA) {
            this.y = true;
        }
        return zA;
    }

    public void stop() {
        this.z = true;
        this.f2041h.obtainMessage(2).sendToTarget();
        this.E = null;
    }

    public boolean stopIndoorMode() {
        boolean zA = a(111);
        if (zA) {
            this.y = false;
        }
        return zA;
    }

    public void unRegisterLocationListener(BDAbstractLocationListener bDAbstractLocationListener) {
        if (bDAbstractLocationListener == null) {
            throw new IllegalStateException("please set a non-null listener");
        }
        Message messageObtainMessage = this.f2041h.obtainMessage(1400);
        messageObtainMessage.obj = bDAbstractLocationListener;
        messageObtainMessage.sendToTarget();
    }

    public void unRegisterLocationListener(BDLocationListener bDLocationListener) {
        if (bDLocationListener == null) {
            throw new IllegalStateException("please set a non-null listener");
        }
        Message messageObtainMessage = this.f2041h.obtainMessage(6);
        messageObtainMessage.obj = bDLocationListener;
        messageObtainMessage.sendToTarget();
    }

    public boolean updateLocation(Location location) {
        if (this.f2040g == null || this.i == null || location == null) {
            return false;
        }
        try {
            Message messageObtain = Message.obtain((Handler) null, 57);
            messageObtain.obj = location;
            this.f2040g.send(messageObtain);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }
}