package com.loc;

import android.app.Application;
import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import androidx.core.view.PointerIconCompat;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.AMapLocationQualityReport;
import com.amap.api.location.APSService;
import com.amap.api.location.UmidtokenInfo;
import com.ido.alexa.AlexaCustomSkillConstant;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: AmapLocationManager.java */
/* JADX INFO: loaded from: classes3.dex */
public final class d {
    private static boolean D = true;
    private static boolean F = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static volatile boolean f4947f = false;
    private Context A;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c f4949b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    g f4950c;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    i f4954h;
    Intent k;
    b m;
    en q;
    a x;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    AMapLocationClientOption f4948a = new AMapLocationClientOption();
    private boolean B = false;
    private volatile boolean C = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    ArrayList<AMapLocationListener> f4951d = new ArrayList<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    boolean f4952e = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f4953g = true;
    Messenger i = null;
    Messenger j = null;
    int l = 0;
    private boolean E = true;
    boolean n = false;
    AMapLocationClientOption.AMapLocationMode o = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy;
    Object p = new Object();
    boolean r = false;
    e s = null;
    private h G = null;
    String t = null;
    private ServiceConnection H = new ServiceConnection() { // from class: com.loc.d.1
        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                d.this.i = new Messenger(iBinder);
                d.this.B = true;
                d.this.r = true;
            } catch (Throwable th) {
                ej.a(th, "ALManager", "onServiceConnected");
            }
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            d dVar = d.this;
            dVar.i = null;
            dVar.B = false;
        }
    };
    AMapLocationQualityReport u = null;
    boolean v = false;
    boolean w = false;
    private volatile boolean I = false;
    String y = null;
    boolean z = false;

    /* JADX INFO: renamed from: com.loc.d$2, reason: invalid class name */
    /* JADX INFO: compiled from: AmapLocationManager.java */
    static /* synthetic */ class AnonymousClass2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f4956a = new int[AMapLocationClientOption.AMapLocationMode.values().length];

        static {
            try {
                f4956a[AMapLocationClientOption.AMapLocationMode.Battery_Saving.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4956a[AMapLocationClientOption.AMapLocationMode.Device_Sensors.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4956a[AMapLocationClientOption.AMapLocationMode.Hight_Accuracy.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: compiled from: AmapLocationManager.java */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0 */
        /* JADX WARN: Type inference failed for: r0v1 */
        /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r0v7 */
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            ?? r0 = 0;
            try {
                super.handleMessage(message);
                int i = message.what;
                if (i == 11) {
                    d.this.a(message.getData());
                    return;
                }
                if (i == 12) {
                    d.a(d.this, message);
                    return;
                }
                try {
                    switch (i) {
                        case 1002:
                            d.a(d.this, (AMapLocationListener) message.obj);
                            break;
                        case 1003:
                            d.this.j();
                            d.this.a(13, (Bundle) null);
                            break;
                        case 1004:
                            d.this.k();
                            d.this.a(14, (Bundle) null);
                            break;
                        case 1005:
                            d.b(d.this, (AMapLocationListener) message.obj);
                            break;
                        case 1006:
                        case 1007:
                            break;
                        case 1008:
                            d.h(d.this);
                            break;
                        case 1009:
                            d.i(d.this);
                            break;
                        case PointerIconCompat.TYPE_ALIAS /* 1010 */:
                            break;
                        case 1011:
                            d.this.a(14, (Bundle) null);
                            d.this.h();
                            break;
                        default:
                            switch (i) {
                                case PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW /* 1014 */:
                                    d.b(d.this, message);
                                    break;
                                case PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW /* 1015 */:
                                    d.this.f4950c.a(d.this.f4948a);
                                    d.this.a(1025, (Object) null, 300000L);
                                    break;
                                case PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW /* 1016 */:
                                    if (!d.this.f4950c.b()) {
                                        d.e(d.this);
                                    } else {
                                        d.this.a(PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW, (Object) null, 1000L);
                                    }
                                    break;
                                case PointerIconCompat.TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW /* 1017 */:
                                    d.this.f4950c.a();
                                    d.this.a(1025);
                                    break;
                                case PointerIconCompat.TYPE_ZOOM_IN /* 1018 */:
                                    d.this.f4948a = (AMapLocationClientOption) message.obj;
                                    if (d.this.f4948a != null) {
                                        d.g(d.this);
                                    }
                                    break;
                                case 1023:
                                    d.c(d.this, message);
                                    break;
                                case 1024:
                                    d.d(d.this, message);
                                    break;
                                case 1025:
                                    g gVar = d.this.f4950c;
                                    boolean z = false;
                                    if (gVar.f5201c != null && !gVar.f5201c.isOnceLocation() && ep.b() - gVar.f5202d > 300000) {
                                        z = true;
                                    }
                                    if (z) {
                                        d.this.f4950c.a();
                                        d.this.f4950c.a(d.this.f4948a);
                                    }
                                    d.this.a(1025, (Object) null, 300000L);
                                    break;
                            }
                            break;
                    }
                } catch (Throwable th) {
                    r0 = message;
                    th = th;
                    if (r0 == 0) {
                        r0 = "handleMessage";
                    }
                    ej.a(th, "AMapLocationManage$MHandlerr", r0);
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    /* JADX INFO: compiled from: AmapLocationManager.java */
    static class b extends HandlerThread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        d f4958a;

        public b(String str, d dVar) {
            super(str);
            this.f4958a = null;
            this.f4958a = dVar;
        }

        @Override // android.os.HandlerThread
        protected final void onLooperPrepared() {
            try {
                this.f4958a.f4954h.a();
                this.f4958a.m();
                dw.j();
                if (this.f4958a != null && this.f4958a.A != null) {
                    ei.b(this.f4958a.A);
                    ei.a(this.f4958a.A);
                }
                super.onLooperPrepared();
            } catch (Throwable unused) {
            }
        }

        @Override // android.os.HandlerThread, java.lang.Thread, java.lang.Runnable
        public final void run() {
            try {
                super.run();
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: compiled from: AmapLocationManager.java */
    public class c extends Handler {
        public c() {
        }

        public c(Looper looper) {
            super(looper);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            String str = null;
            try {
                super.handleMessage(message);
                if (d.this.n) {
                }
                switch (message.what) {
                    case 1:
                        Message messageObtainMessage = d.this.x.obtainMessage();
                        messageObtainMessage.what = 11;
                        messageObtainMessage.setData(message.getData());
                        d.this.x.sendMessage(messageObtainMessage);
                        break;
                    case 2:
                        Message messageObtain = Message.obtain();
                        messageObtain.what = 12;
                        messageObtain.obj = message.obj;
                        d.this.x.sendMessage(messageObtain);
                        break;
                    case 5:
                        Bundle data = message.getData();
                        data.putBundle("optBundle", ej.a(d.this.f4948a));
                        d.this.a(10, data);
                        break;
                    case 6:
                        str = "handleMessage RESULT_GPS_GEO_SUCCESS";
                        Bundle data2 = message.getData();
                        if (d.this.f4950c != null) {
                            g gVar = d.this.f4950c;
                            if (data2 != null) {
                                try {
                                    data2.setClassLoader(AMapLocation.class.getClassLoader());
                                    gVar.f5205g = data2.getInt("I_MAX_GEO_DIS");
                                    gVar.f5206h = data2.getInt("I_MIN_GEO_DIS");
                                    AMapLocation aMapLocation = (AMapLocation) data2.getParcelable("loc");
                                    if (!TextUtils.isEmpty(aMapLocation.getAdCode())) {
                                        synchronized (gVar.o) {
                                            g.y = aMapLocation;
                                        }
                                    }
                                } catch (Throwable th) {
                                    ej.a(th, "GpsLocation", "setLastGeoLocation");
                                    return;
                                }
                            }
                        }
                        break;
                    case 7:
                        Bundle data3 = message.getData();
                        d.this.E = data3.getBoolean("ngpsAble");
                        break;
                    case 8:
                        en.a((String) null, 2141);
                        Message messageObtain2 = Message.obtain();
                        messageObtain2.what = 12;
                        messageObtain2.obj = message.obj;
                        d.this.x.sendMessage(messageObtain2);
                        break;
                    case 9:
                        boolean unused = d.F = message.getData().getBoolean("installMockApp");
                        break;
                    case 10:
                        d.a(d.this, (AMapLocation) message.obj);
                        break;
                }
            } catch (Throwable th2) {
                if (str == null) {
                    str = "handleMessage";
                }
                ej.a(th2, "AmapLocationManager$MainHandler", str);
            }
        }
    }

    public d(Context context, Intent intent, Looper looper) {
        this.f4950c = null;
        this.k = null;
        this.m = null;
        this.q = null;
        this.x = null;
        this.A = context;
        this.k = intent;
        try {
            this.f4949b = looper == null ? Looper.myLooper() == null ? new c(this.A.getMainLooper()) : new c() : new c(looper);
        } catch (Throwable th) {
            ej.a(th, "ALManager", "init 1");
        }
        try {
            try {
                this.f4954h = new i(this.A);
            } catch (Throwable th2) {
                ej.a(th2, "ALManager", "init 2");
            }
            this.m = new b("amapLocManagerThread", this);
            this.m.setPriority(5);
            this.m.start();
            this.x = a(this.m.getLooper());
        } catch (Throwable th3) {
            ej.a(th3, "ALManager", "init 5");
        }
        try {
            this.f4950c = new g(this.A, this.f4949b);
        } catch (Throwable th4) {
            ej.a(th4, "ALManager", "init 3");
        }
        if (this.q == null) {
            this.q = new en();
        }
    }

    private a a(Looper looper) {
        a aVar;
        synchronized (this.p) {
            this.x = new a(looper);
            aVar = this.x;
        }
        return aVar;
    }

    private ds a(dn dnVar) {
        if (!this.f4948a.isLocationCacheEnable()) {
            return null;
        }
        try {
            return dnVar.f();
        } catch (Throwable th) {
            ej.a(th, "ALManager", "doFirstCacheLoc");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        synchronized (this.p) {
            if (this.x != null) {
                this.x.removeMessages(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, Bundle bundle) {
        if (bundle == null) {
            try {
                bundle = new Bundle();
            } catch (Throwable th) {
                boolean z = (th instanceof IllegalStateException) && th.getMessage().contains("sending message to a Handler on a dead thread");
                if ((th instanceof RemoteException) || z) {
                    this.i = null;
                    this.B = false;
                }
                ej.a(th, "ALManager", "sendLocMessage");
                return;
            }
        }
        if (TextUtils.isEmpty(this.t)) {
            this.t = ej.b(this.A);
        }
        bundle.putString("c", this.t);
        Message messageObtain = Message.obtain();
        messageObtain.what = i;
        messageObtain.setData(bundle);
        messageObtain.replyTo = this.j;
        if (this.i != null) {
            this.i.send(messageObtain);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, Object obj, long j) {
        synchronized (this.p) {
            if (this.x != null) {
                Message messageObtain = Message.obtain();
                messageObtain.what = i;
                if (obj instanceof Bundle) {
                    messageObtain.setData((Bundle) obj);
                } else {
                    messageObtain.obj = obj;
                }
                this.x.sendMessageDelayed(messageObtain, j);
            }
        }
    }

    private void a(Intent intent, boolean z) {
        if (this.A != null) {
            if (Build.VERSION.SDK_INT < 26 || !z) {
                this.A.startService(intent);
            } else if (!o()) {
                Log.e("amapapi", "-------------调用后台定位服务，缺少权限：android.permission.FOREGROUND_SERVICE--------------");
                return;
            } else {
                try {
                    this.A.getClass().getMethod("startForegroundService", Intent.class).invoke(this.A, intent);
                } catch (Throwable unused) {
                    this.A.startService(intent);
                }
            }
            this.z = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Bundle bundle) {
        dm dmVar;
        AMapLocation aMapLocation;
        AMapLocation aMapLocationA = null;
        if (bundle != null) {
            try {
                bundle.setClassLoader(AMapLocation.class.getClassLoader());
                aMapLocation = (AMapLocation) bundle.getParcelable("loc");
                this.y = bundle.getString("nb");
                dmVar = (dm) bundle.getParcelable("statics");
                if (aMapLocation != null) {
                    try {
                        if (aMapLocation.getErrorCode() == 0 && this.f4950c != null) {
                            this.f4950c.w = 0;
                            if (!TextUtils.isEmpty(aMapLocation.getAdCode())) {
                                g.y = aMapLocation;
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        ej.a(th, "AmapLocationManager", "resultLbsLocationSuccess");
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                dmVar = null;
                ej.a(th, "AmapLocationManager", "resultLbsLocationSuccess");
            }
        } else {
            dmVar = null;
            aMapLocation = null;
        }
        aMapLocationA = this.f4950c != null ? this.f4950c.a(aMapLocation, this.y) : aMapLocation;
        a(aMapLocationA, dmVar);
    }

    private synchronized void a(AMapLocation aMapLocation, dm dmVar) {
        if (aMapLocation == null) {
            try {
                aMapLocation = new AMapLocation("");
                aMapLocation.setErrorCode(8);
                aMapLocation.setLocationDetail("amapLocation is null#0801");
            } catch (Throwable th) {
                ej.a(th, "ALManager", "handlerLocation part3");
                return;
            }
        }
        if (!"gps".equalsIgnoreCase(aMapLocation.getProvider())) {
            aMapLocation.setProvider("lbs");
        }
        if (this.u == null) {
            this.u = new AMapLocationQualityReport();
        }
        this.u.setLocationMode(this.f4948a.getLocationMode());
        if (this.f4950c != null) {
            this.u.setGPSSatellites(this.f4950c.d());
            this.u.setGpsStatus(this.f4950c.c());
        }
        this.u.setWifiAble(ep.h(this.A));
        this.u.setNetworkType(ep.i(this.A));
        if (aMapLocation.getLocationType() == 1 || "gps".equalsIgnoreCase(aMapLocation.getProvider())) {
            this.u.setNetUseTime(0L);
        }
        if (dmVar != null) {
            this.u.setNetUseTime(dmVar.a());
        }
        this.u.setInstallHighDangerMockApp(F);
        aMapLocation.setLocationQualityReport(this.u);
        try {
            if (this.C) {
                String str = this.y;
                Bundle bundle = new Bundle();
                bundle.putParcelable("loc", aMapLocation);
                bundle.putString("lastLocNb", str);
                a(PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW, bundle, 0L);
                if (dmVar != null) {
                    dmVar.d(ep.b());
                }
                en.a(this.A, aMapLocation, dmVar);
                en.a(this.A, aMapLocation);
                AMapLocation aMapLocationM7clone = aMapLocation.m7clone();
                Message messageObtainMessage = this.f4949b.obtainMessage();
                messageObtainMessage.what = 10;
                messageObtainMessage.obj = aMapLocationM7clone;
                this.f4949b.sendMessage(messageObtainMessage);
            }
        } catch (Throwable th2) {
            ej.a(th2, "ALManager", "handlerLocation part2");
        }
        if (this.n) {
            return;
        }
        if (this.f4948a.isOnceLocation()) {
            k();
            a(14, (Bundle) null);
        }
    }

    static /* synthetic */ void a(d dVar, Message message) {
        try {
            AMapLocation aMapLocation = (AMapLocation) message.obj;
            if (dVar.f4953g && dVar.i != null) {
                Bundle bundle = new Bundle();
                bundle.putBundle("optBundle", ej.a(dVar.f4948a));
                dVar.a(0, bundle);
                if (dVar.C) {
                    dVar.a(13, (Bundle) null);
                }
                dVar.f4953g = false;
            }
            dVar.a(aMapLocation, (dm) null);
            dVar.a(1025);
            dVar.a(1025, (Object) null, 300000L);
        } catch (Throwable th) {
            ej.a(th, "ALManager", "resultGpsLocationSuccess");
        }
    }

    static /* synthetic */ void a(d dVar, AMapLocation aMapLocation) {
        try {
            if (aMapLocation.getErrorCode() != 0) {
                aMapLocation.setLocationType(0);
            }
            if (aMapLocation.getErrorCode() == 0) {
                double latitude = aMapLocation.getLatitude();
                double longitude = aMapLocation.getLongitude();
                if ((latitude == 0.0d && longitude == 0.0d) || latitude < -90.0d || latitude > 90.0d || longitude < -180.0d || longitude > 180.0d) {
                    en.a("errorLatLng", aMapLocation.toStr());
                    aMapLocation.setLocationType(0);
                    aMapLocation.setErrorCode(8);
                    aMapLocation.setLocationDetail("LatLng is error#0802");
                }
            }
            if ("gps".equalsIgnoreCase(aMapLocation.getProvider()) || !dVar.f4950c.b()) {
                aMapLocation.setAltitude(ep.c(aMapLocation.getAltitude()));
                aMapLocation.setBearing(ep.a(aMapLocation.getBearing()));
                aMapLocation.setSpeed(ep.a(aMapLocation.getSpeed()));
                Iterator<AMapLocationListener> it = dVar.f4951d.iterator();
                while (it.hasNext()) {
                    try {
                        it.next().onLocationChanged(aMapLocation);
                    } catch (Throwable unused) {
                    }
                }
            }
        } catch (Throwable unused2) {
        }
    }

    static /* synthetic */ void a(d dVar, AMapLocationListener aMapLocationListener) {
        if (aMapLocationListener == null) {
            throw new IllegalArgumentException("listener参数不能为null");
        }
        if (dVar.f4951d == null) {
            dVar.f4951d = new ArrayList<>();
        }
        if (dVar.f4951d.contains(aMapLocationListener)) {
            return;
        }
        dVar.f4951d.add(aMapLocationListener);
    }

    private ds b(dn dnVar) {
        ds dsVarA;
        boolean z;
        String strK;
        dm dmVar = new dm();
        AMapLocation aMapLocationA = null;
        try {
            dmVar.c(ep.b());
            try {
                String apikey = AMapLocationClientOption.getAPIKEY();
                if (!TextUtils.isEmpty(apikey)) {
                    l.a(this.A, apikey);
                }
            } catch (Throwable th) {
                ej.a(th, "ALManager", "apsLocation setAuthKey");
            }
            try {
                String umidtoken = UmidtokenInfo.getUmidtoken();
                if (!TextUtils.isEmpty(umidtoken)) {
                    n.a(umidtoken);
                }
            } catch (Throwable th2) {
                ej.a(th2, "ALManager", "apsLocation setUmidToken");
            }
            try {
                dnVar.a(this.A);
                dnVar.a(this.f4948a);
                dnVar.b(dmVar);
            } catch (Throwable th3) {
                ej.a(th3, "ALManager", "initApsBase");
            }
            boolean zL = ei.l();
            dsVarA = a(dnVar);
            if (dsVarA == null) {
                try {
                    try {
                        dsVarA = dnVar.a(!zL, dmVar);
                        if (dsVarA != null) {
                            try {
                                if (dsVarA.getErrorCode() == 0) {
                                    dnVar.b(dsVarA);
                                }
                            } catch (Throwable th4) {
                                ej.a(th4, "ALManager", "apsLocation:doFirstAddCache");
                            }
                        }
                    } catch (Throwable th5) {
                        ej.a(th5, "ALManager", "apsLocation:doFirstNetLocate");
                    }
                    z = true;
                } catch (Throwable th6) {
                    th = th6;
                    try {
                        ej.a(th, "ALManager", "apsLocation");
                    } finally {
                        try {
                            dnVar.d();
                        } catch (Throwable unused) {
                        }
                    }
                }
            } else {
                z = false;
            }
            if (dsVarA != null) {
                strK = dsVarA.k();
                aMapLocationA = dsVarA.m7clone();
            } else {
                strK = null;
            }
            try {
                if (this.f4948a.isLocationCacheEnable() && this.f4954h != null) {
                    aMapLocationA = this.f4954h.a(aMapLocationA, strK, this.f4948a.getLastLocationLifeCycle());
                }
            } catch (Throwable th7) {
                ej.a(th7, "ALManager", "fixLastLocation");
            }
            try {
                Bundle bundle = new Bundle();
                if (aMapLocationA != null) {
                    bundle.putParcelable("loc", aMapLocationA);
                    bundle.putString("nb", dsVarA.k());
                    bundle.putParcelable("statics", dmVar);
                }
                a(bundle);
            } catch (Throwable th8) {
                ej.a(th8, "ALManager", "apsLocation:callback");
            }
            if (z && zL && !f4947f) {
                f4947f = true;
                try {
                    dnVar.c();
                    dnVar.a(new AMapLocationClientOption().setNeedAddress(false));
                    dnVar.a(true, new dm());
                } catch (Throwable th9) {
                    ej.a(th9, "ALManager", "apsLocation:doFirstNetLocate 2");
                }
            }
        } catch (Throwable th10) {
            th = th10;
            dsVarA = null;
            ej.a(th, "ALManager", "apsLocation");
            return dsVarA;
        }
        return dsVarA;
    }

    static /* synthetic */ void b(d dVar, Message message) {
        try {
            Bundle data = message.getData();
            AMapLocation aMapLocation = (AMapLocation) data.getParcelable("loc");
            String string = data.getString("lastLocNb");
            if (aMapLocation != null) {
                AMapLocation aMapLocationA = null;
                try {
                    if (i.f5221b != null) {
                        aMapLocationA = i.f5221b.a();
                    } else if (dVar.f4954h != null) {
                        aMapLocationA = dVar.f4954h.b();
                    }
                    en.a(aMapLocationA, aMapLocation);
                } catch (Throwable unused) {
                }
            }
            if (dVar.f4954h.a(aMapLocation, string)) {
                dVar.f4954h.d();
            }
        } catch (Throwable th) {
            ej.a(th, "ALManager", "doSaveLastLocation");
        }
    }

    static /* synthetic */ void b(d dVar, AMapLocationListener aMapLocationListener) {
        if (!dVar.f4951d.isEmpty() && dVar.f4951d.contains(aMapLocationListener)) {
            dVar.f4951d.remove(aMapLocationListener);
        }
        if (dVar.f4951d.isEmpty()) {
            dVar.k();
        }
    }

    static /* synthetic */ void c(d dVar, Message message) {
        if (message == null) {
            return;
        }
        try {
            Bundle data = message.getData();
            if (data == null) {
                return;
            }
            int i = data.getInt("i", 0);
            Notification notification = (Notification) data.getParcelable("h");
            Intent intentN = dVar.n();
            intentN.putExtra("i", i);
            intentN.putExtra("h", notification);
            intentN.putExtra("g", 1);
            dVar.a(intentN, true);
        } catch (Throwable th) {
            ej.a(th, "ALManager", "doEnableBackgroundLocation");
        }
    }

    static /* synthetic */ void d(d dVar, Message message) {
        if (message == null) {
            return;
        }
        try {
            Bundle data = message.getData();
            if (data == null) {
                return;
            }
            boolean z = data.getBoolean(z.j, true);
            Intent intentN = dVar.n();
            intentN.putExtra(z.j, z);
            intentN.putExtra("g", 2);
            dVar.a(intentN, false);
        } catch (Throwable th) {
            ej.a(th, "ALManager", "doDisableBackgroundLocation");
        }
    }

    static /* synthetic */ void e(d dVar) {
        try {
            if (D || !(dVar.r || dVar.I)) {
                D = false;
                dVar.I = true;
                ds dsVarB = dVar.b(new dn());
                if (dVar.i()) {
                    Bundle bundle = new Bundle();
                    String str = AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE;
                    if (dsVarB != null && (dsVarB.getLocationType() == 2 || dsVarB.getLocationType() == 4)) {
                        str = "1";
                    }
                    bundle.putBundle("optBundle", ej.a(dVar.f4948a));
                    bundle.putString("isCacheLoc", str);
                    dVar.a(0, bundle);
                    if (dVar.C) {
                        dVar.a(13, (Bundle) null);
                    }
                }
            } else {
                try {
                    if (dVar.r && !dVar.B && !dVar.w) {
                        dVar.w = true;
                        dVar.m();
                    }
                } catch (Throwable th) {
                    dVar.w = true;
                    ej.a(th, "ALManager", "doLBSLocation reStartService");
                }
                if (dVar.i()) {
                    dVar.w = false;
                    Bundle bundle2 = new Bundle();
                    bundle2.putBundle("optBundle", ej.a(dVar.f4948a));
                    bundle2.putString("d", UmidtokenInfo.getUmidtoken());
                    if (!dVar.f4950c.b()) {
                        dVar.a(1, bundle2);
                    }
                }
            }
        } catch (Throwable th2) {
            try {
                ej.a(th2, "ALManager", "doLBSLocation");
                try {
                    if (dVar.f4948a.isOnceLocation()) {
                        return;
                    }
                    dVar.l();
                } catch (Throwable unused) {
                }
            } finally {
                try {
                    if (!dVar.f4948a.isOnceLocation()) {
                        dVar.l();
                    }
                } catch (Throwable unused2) {
                }
            }
        }
    }

    static /* synthetic */ void g(d dVar) {
        en enVar;
        Context context;
        int i;
        g gVar = dVar.f4950c;
        AMapLocationClientOption aMapLocationClientOption = dVar.f4948a;
        if (aMapLocationClientOption == null) {
            aMapLocationClientOption = new AMapLocationClientOption();
        }
        gVar.f5201c = aMapLocationClientOption;
        if (gVar.f5201c.getLocationMode() != AMapLocationClientOption.AMapLocationMode.Device_Sensors && gVar.f5199a != null) {
            gVar.f5199a.removeMessages(8);
        }
        if (gVar.r != gVar.f5201c.getGeoLanguage()) {
            synchronized (gVar.o) {
                g.y = null;
            }
        }
        gVar.r = gVar.f5201c.getGeoLanguage();
        if (dVar.C && !dVar.f4948a.getLocationMode().equals(dVar.o)) {
            dVar.k();
            dVar.j();
        }
        dVar.o = dVar.f4948a.getLocationMode();
        if (dVar.q != null) {
            if (dVar.f4948a.isOnceLocation()) {
                enVar = dVar.q;
                context = dVar.A;
                i = 0;
            } else {
                enVar = dVar.q;
                context = dVar.A;
                i = 1;
            }
            enVar.a(context, i);
            dVar.q.a(dVar.A, dVar.f4948a);
        }
    }

    static /* synthetic */ void h(d dVar) {
        try {
            if (dVar.i != null) {
                dVar.l = 0;
                Bundle bundle = new Bundle();
                bundle.putBundle("optBundle", ej.a(dVar.f4948a));
                dVar.a(2, bundle);
                return;
            }
            dVar.l++;
            if (dVar.l < 10) {
                dVar.a(1008, (Object) null, 50L);
            }
        } catch (Throwable th) {
            ej.a(th, "ALManager", "startAssistantLocationImpl");
        }
    }

    static /* synthetic */ void i(d dVar) {
        try {
            Bundle bundle = new Bundle();
            bundle.putBundle("optBundle", ej.a(dVar.f4948a));
            dVar.a(3, bundle);
        } catch (Throwable th) {
            ej.a(th, "ALManager", "stopAssistantLocationImpl");
        }
    }

    private boolean i() {
        boolean z = false;
        int i = 0;
        while (this.i == null) {
            try {
                Thread.sleep(100L);
                i++;
                if (i >= 50) {
                    break;
                }
            } catch (Throwable th) {
                ej.a(th, "ALManager", "checkAPSManager");
            }
        }
        if (this.i == null) {
            Message messageObtain = Message.obtain();
            Bundle bundle = new Bundle();
            AMapLocation aMapLocation = new AMapLocation("");
            aMapLocation.setErrorCode(10);
            aMapLocation.setLocationDetail(!ep.l(this.A.getApplicationContext()) ? "请检查配置文件是否配置服务，并且manifest中service标签是否配置在application标签内#1003" : "启动ApsServcie失败#1001");
            bundle.putParcelable("loc", aMapLocation);
            messageObtain.setData(bundle);
            messageObtain.what = 1;
            this.f4949b.sendMessage(messageObtain);
        } else {
            z = true;
        }
        if (!z) {
            en.a((String) null, !ep.l(this.A.getApplicationContext()) ? 2103 : 2101);
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void j() {
        if (this.f4948a == null) {
            this.f4948a = new AMapLocationClientOption();
        }
        if (this.C) {
            return;
        }
        this.C = true;
        int i = AnonymousClass2.f4956a[this.f4948a.getLocationMode().ordinal()];
        long gpsFirstTimeout = 0;
        if (i == 1) {
            a(PointerIconCompat.TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW, (Object) null, 0L);
            a(PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW, (Object) null, 0L);
        } else {
            if (i == 2) {
                a(PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW);
                a(PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW, (Object) null, 0L);
                return;
            }
            if (i == 3) {
                a(PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW, (Object) null, 0L);
                if (this.f4948a.isGpsFirst() && this.f4948a.isOnceLocation()) {
                    gpsFirstTimeout = this.f4948a.getGpsFirstTimeout();
                }
                a(PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW, (Object) null, gpsFirstTimeout);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        try {
            a(1025);
            if (this.f4950c != null) {
                this.f4950c.a();
            }
            a(PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW);
            this.C = false;
            this.l = 0;
        } catch (Throwable th) {
            ej.a(th, "ALManager", "stopLocation");
        }
    }

    private void l() {
        if (this.f4948a.getLocationMode() != AMapLocationClientOption.AMapLocationMode.Device_Sensors) {
            a(PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW, (Object) null, this.f4948a.getInterval() >= 1000 ? this.f4948a.getInterval() : 1000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        try {
            if (this.j == null) {
                this.j = new Messenger(this.f4949b);
            }
            try {
                this.A.bindService(n(), this.H, 1);
            } catch (Throwable th) {
                ej.a(th, "ALManager", "startServiceImpl");
            }
        } catch (Throwable unused) {
        }
    }

    private Intent n() {
        String apikey;
        if (this.k == null) {
            this.k = new Intent(this.A, (Class<?>) APSService.class);
        }
        try {
            apikey = !TextUtils.isEmpty(AMapLocationClientOption.getAPIKEY()) ? AMapLocationClientOption.getAPIKEY() : k.f(this.A);
        } catch (Throwable th) {
            ej.a(th, "ALManager", "startServiceImpl p2");
            apikey = "";
        }
        this.k.putExtra("a", apikey);
        this.k.putExtra("b", k.c(this.A));
        this.k.putExtra("d", UmidtokenInfo.getUmidtoken());
        return this.k;
    }

    private boolean o() {
        if (ep.k(this.A)) {
            int iB = -1;
            try {
                iB = em.b(((Application) this.A.getApplicationContext()).getBaseContext(), "checkSelfPermission", "android.permission.FOREGROUND_SERVICE");
            } catch (Throwable unused) {
            }
            if (iB != 0) {
                return false;
            }
        }
        return true;
    }

    public final void a(int i, Notification notification) {
        if (i == 0 || notification == null) {
            return;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("i", i);
            bundle.putParcelable("h", notification);
            a(1023, bundle, 0L);
        } catch (Throwable th) {
            ej.a(th, "ALManager", "disableBackgroundLocation");
        }
    }

    public final void a(WebView webView) {
        if (this.G == null) {
            this.G = new h(this.A, webView);
        }
        this.G.a();
    }

    public final void a(AMapLocationClientOption aMapLocationClientOption) {
        try {
            a(PointerIconCompat.TYPE_ZOOM_IN, aMapLocationClientOption.m8clone(), 0L);
        } catch (Throwable th) {
            ej.a(th, "ALManager", "setLocationOption");
        }
    }

    public final void a(AMapLocationListener aMapLocationListener) {
        try {
            a(1002, aMapLocationListener, 0L);
        } catch (Throwable th) {
            ej.a(th, "ALManager", "setLocationListener");
        }
    }

    public final void a(boolean z) {
        try {
            Bundle bundle = new Bundle();
            bundle.putBoolean(z.j, z);
            a(1024, bundle, 0L);
        } catch (Throwable th) {
            ej.a(th, "ALManager", "disableBackgroundLocation");
        }
    }

    public final boolean a() {
        return this.B;
    }

    public final void b() {
        try {
            a(1003, (Object) null, 0L);
        } catch (Throwable th) {
            ej.a(th, "ALManager", "startLocation");
        }
    }

    public final void b(AMapLocationListener aMapLocationListener) {
        try {
            a(1005, aMapLocationListener, 0L);
        } catch (Throwable th) {
            ej.a(th, "ALManager", "unRegisterLocationListener");
        }
    }

    public final void c() {
        try {
            a(1004, (Object) null, 0L);
        } catch (Throwable th) {
            ej.a(th, "ALManager", "stopLocation");
        }
    }

    public final void d() {
        try {
            if (this.G != null) {
                this.G.b();
                this.G = null;
            }
            a(1011, (Object) null, 0L);
            this.n = true;
        } catch (Throwable th) {
            ej.a(th, "ALManager", "onDestroy");
        }
    }

    public final AMapLocation e() {
        AMapLocation aMapLocationB = null;
        try {
            if (this.f4954h != null && (aMapLocationB = this.f4954h.b()) != null) {
                aMapLocationB.setTrustedLevel(3);
            }
        } catch (Throwable th) {
            ej.a(th, "ALManager", "getLastKnownLocation");
        }
        return aMapLocationB;
    }

    public final void f() {
        try {
            a(1008, (Object) null, 0L);
        } catch (Throwable th) {
            ej.a(th, "ALManager", "startAssistantLocation");
        }
    }

    public final void g() {
        try {
            if (this.G != null) {
                this.G.b();
                this.G = null;
            }
            a(1009, (Object) null, 0L);
        } catch (Throwable th) {
            ej.a(th, "ALManager", "stopAssistantLocation");
        }
    }

    final void h() {
        a(12, (Bundle) null);
        this.f4953g = true;
        this.B = false;
        this.r = false;
        k();
        en enVar = this.q;
        if (enVar != null) {
            enVar.b(this.A);
        }
        en.a(this.A);
        e eVar = this.s;
        if (eVar != null) {
            eVar.f5105d.sendEmptyMessage(11);
        } else {
            ServiceConnection serviceConnection = this.H;
            if (serviceConnection != null) {
                this.A.unbindService(serviceConnection);
            }
        }
        try {
            if (this.z) {
                this.A.stopService(n());
            }
        } catch (Throwable unused) {
        }
        this.z = false;
        ArrayList<AMapLocationListener> arrayList = this.f4951d;
        if (arrayList != null) {
            arrayList.clear();
            this.f4951d = null;
        }
        this.H = null;
        synchronized (this.p) {
            if (this.x != null) {
                this.x.removeCallbacksAndMessages(null);
            }
            this.x = null;
        }
        if (this.m != null) {
            if (Build.VERSION.SDK_INT >= 18) {
                try {
                    em.a(this.m, (Class<?>) HandlerThread.class, "quitSafely", new Object[0]);
                } catch (Throwable unused2) {
                    this.m.quit();
                }
            } else {
                this.m.quit();
            }
        }
        this.m = null;
        c cVar = this.f4949b;
        if (cVar != null) {
            cVar.removeCallbacksAndMessages(null);
        }
        i iVar = this.f4954h;
        if (iVar != null) {
            iVar.c();
            this.f4954h = null;
        }
    }
}