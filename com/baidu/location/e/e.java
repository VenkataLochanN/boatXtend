package com.baidu.location.e;

import android.content.Context;
import android.location.GnssStatus;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.OnNmeaMessageListener;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.a.t;
import com.baidu.location.a.w;
import com.baidu.location.a.x;
import com.baidu.location.g.k;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.life.util.DateUtil;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.util.ArrayList;
import java.util.Locale;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: loaded from: classes.dex */
public class e {
    private static String C = null;
    private static double G = 100.0d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f2403a = "";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f2404b = "";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static e f2405c;
    private static int p;
    private static int q;
    private static int r;
    private static long s;
    private int E;
    private int F;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f2406d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Location f2408f;
    private GpsStatus i;
    private a j;
    private boolean k;
    private boolean m;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private LocationManager f2407e = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private c f2409g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private C0021e f2410h = null;
    private b l = null;
    private d n = null;
    private OnNmeaMessageListener o = null;
    private long t = 0;
    private boolean u = false;
    private boolean v = false;
    private String w = null;
    private boolean x = false;
    private long y = 0;
    private double z = -1.0d;
    private double A = 0.0d;
    private double B = 0.0d;
    private Handler D = null;
    private long H = 0;
    private long I = 0;
    private ArrayList<ArrayList<Float>> J = new ArrayList<>();
    private ArrayList<ArrayList<Float>> K = new ArrayList<>();

    private class a extends GnssStatus.Callback {
        private a() {
        }

        /* synthetic */ a(e eVar, f fVar) {
            this();
        }

        @Override // android.location.GnssStatus.Callback
        public void onFirstFix(int i) {
        }

        @Override // android.location.GnssStatus.Callback
        public void onSatelliteStatusChanged(GnssStatus gnssStatus) {
            if (e.this.f2407e == null) {
                return;
            }
            e.this.I = System.currentTimeMillis();
            int satelliteCount = gnssStatus.getSatelliteCount();
            e.this.J.clear();
            e.this.K.clear();
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            for (int i4 = 0; i4 < satelliteCount; i4++) {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                i3++;
                if (gnssStatus.usedInFix(i4)) {
                    i++;
                    gnssStatus.getConstellationType(i4);
                    i2++;
                    arrayList.add(Float.valueOf(gnssStatus.getCn0DbHz(i4)));
                    arrayList.add(Float.valueOf(0.0f));
                    arrayList.add(Float.valueOf(gnssStatus.getAzimuthDegrees(i4)));
                    arrayList.add(Float.valueOf(gnssStatus.getElevationDegrees(i4)));
                    arrayList.add(Float.valueOf(1.0f));
                    arrayList.add(Float.valueOf(gnssStatus.getSvid(i4)));
                    e.this.J.add(arrayList);
                    e.this.K.add(arrayList);
                } else {
                    gnssStatus.getConstellationType(i4);
                    arrayList2.add(Float.valueOf(gnssStatus.getCn0DbHz(i4)));
                    arrayList2.add(Float.valueOf(0.0f));
                    arrayList2.add(Float.valueOf(gnssStatus.getAzimuthDegrees(i4)));
                    arrayList2.add(Float.valueOf(gnssStatus.getElevationDegrees(i4)));
                    arrayList2.add(Float.valueOf(0.0f));
                    arrayList2.add(Float.valueOf(gnssStatus.getSvid(i4)));
                    e.this.K.add(arrayList2);
                }
            }
            e.f2403a = e.this.l();
            e.f2404b = e.this.m();
            int unused = e.p = i;
            int unused2 = e.q = i2;
            int unused3 = e.r = i3;
            long unused4 = e.s = System.currentTimeMillis();
        }

        @Override // android.location.GnssStatus.Callback
        public void onStarted() {
        }

        @Override // android.location.GnssStatus.Callback
        public void onStopped() {
            e.this.d((Location) null);
            e.this.b(false);
            int unused = e.p = 0;
            int unused2 = e.q = 0;
        }
    }

    private class b implements GpsStatus.Listener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private long f2413b;

        private b() {
            this.f2413b = 0L;
        }

        /* synthetic */ b(e eVar, f fVar) {
            this();
        }

        @Override // android.location.GpsStatus.Listener
        public void onGpsStatusChanged(int i) {
            if (e.this.f2407e == null) {
                return;
            }
            int i2 = 0;
            if (i == 2) {
                e.this.d((Location) null);
                e.this.b(false);
                int unused = e.p = 0;
                int unused2 = e.q = 0;
                return;
            }
            if (i == 4 && e.this.v) {
                try {
                    if (e.this.i == null) {
                        e.this.i = e.this.f2407e.getGpsStatus(null);
                    } else {
                        e.this.f2407e.getGpsStatus(e.this.i);
                    }
                    e.this.E = 0;
                    e.this.F = 0;
                    double snr = 0.0d;
                    e.this.J.clear();
                    e.this.K.clear();
                    e.this.I = System.currentTimeMillis();
                    int i3 = 0;
                    for (GpsSatellite gpsSatellite : e.this.i.getSatellites()) {
                        ArrayList arrayList = new ArrayList();
                        ArrayList arrayList2 = new ArrayList();
                        if (gpsSatellite.usedInFix()) {
                            i3++;
                            gpsSatellite.getPrn();
                            i2++;
                            snr += (double) gpsSatellite.getSnr();
                            arrayList.add(Float.valueOf(0.0f));
                            arrayList.add(Float.valueOf(gpsSatellite.getSnr()));
                            arrayList.add(Float.valueOf(gpsSatellite.getAzimuth()));
                            arrayList.add(Float.valueOf(gpsSatellite.getElevation()));
                            arrayList.add(Float.valueOf(1.0f));
                            arrayList.add(Float.valueOf(gpsSatellite.getPrn()));
                            e.this.J.add(arrayList);
                            e.this.K.add(arrayList);
                        } else {
                            gpsSatellite.getPrn();
                            arrayList2.add(Float.valueOf(0.0f));
                            arrayList2.add(Float.valueOf(gpsSatellite.getSnr()));
                            arrayList2.add(Float.valueOf(gpsSatellite.getAzimuth()));
                            arrayList2.add(Float.valueOf(gpsSatellite.getElevation()));
                            arrayList2.add(Float.valueOf(0.0f));
                            arrayList2.add(Float.valueOf(gpsSatellite.getPrn()));
                            e.this.K.add(arrayList2);
                        }
                        if (gpsSatellite.getSnr() >= k.H) {
                            e.i(e.this);
                        }
                    }
                    e.f2403a = e.this.l();
                    e.f2404b = e.this.m();
                    if (i2 > 0) {
                        int unused3 = e.q = i2;
                        double unused4 = e.G = snr / ((double) i2);
                    }
                    if (i3 > 0 || System.currentTimeMillis() - this.f2413b > 100) {
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        this.f2413b = jCurrentTimeMillis;
                        int unused5 = e.p = i3;
                    }
                    long unused6 = e.s = System.currentTimeMillis();
                } catch (Exception unused7) {
                }
            }
        }
    }

    private class c implements LocationListener {
        private c() {
        }

        /* synthetic */ c(e eVar, f fVar) {
            this();
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            e.this.y = System.currentTimeMillis();
            e.this.b(true);
            e.this.d(location);
            e.this.u = false;
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
            e.this.d((Location) null);
            e.this.b(false);
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
            if (i == 0) {
                e.this.d((Location) null);
            } else if (i != 1) {
                if (i != 2) {
                    return;
                }
                e.this.u = false;
                return;
            } else {
                e.this.t = System.currentTimeMillis();
                e.this.u = true;
            }
            e.this.b(false);
        }
    }

    private class d implements GpsStatus.NmeaListener {
        private d() {
        }

        /* synthetic */ d(e eVar, f fVar) {
            this();
        }

        @Override // android.location.GpsStatus.NmeaListener
        public void onNmeaReceived(long j, String str) {
            if (e.this.b(str)) {
                e.this.a(str);
            }
        }
    }

    /* JADX INFO: renamed from: com.baidu.location.e.e$e, reason: collision with other inner class name */
    private class C0021e implements LocationListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private long f2417b;

        private C0021e() {
            this.f2417b = 0L;
        }

        /* synthetic */ C0021e(e eVar, f fVar) {
            this();
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            if (!e.this.v && location != null && location.getProvider() == "gps" && System.currentTimeMillis() - this.f2417b >= DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT && w.a(location, false)) {
                this.f2417b = System.currentTimeMillis();
                e.this.D.sendMessage(e.this.D.obtainMessage(4, location));
            }
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
        }
    }

    private e() {
        this.k = false;
        this.m = false;
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                Class.forName("android.location.GnssStatus");
                this.k = true;
            } catch (ClassNotFoundException unused) {
                this.k = false;
            }
        }
        this.m = false;
    }

    public static int a(String str, String str2) {
        char cCharAt = str2.charAt(0);
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (Character.valueOf(cCharAt).equals(Character.valueOf(str.charAt(i2)))) {
                i++;
            }
        }
        return i;
    }

    public static synchronized e a() {
        if (f2405c == null) {
            f2405c = new e();
        }
        return f2405c;
    }

    public static String a(Location location) {
        if (location == null) {
            return null;
        }
        float speed = (float) (((double) location.getSpeed()) * 3.6d);
        if (!location.hasSpeed()) {
            speed = -1.0f;
        }
        return String.format(Locale.CHINA, "&ll=%.5f|%.5f&s=%.1f&d=%.1f&ll_r=%d&ll_n=%d&ll_h=%.2f&ll_t=%d&ll_sn=%d|%d&ll_snr=%.1f", Double.valueOf(location.getLongitude()), Double.valueOf(location.getLatitude()), Float.valueOf(speed), Float.valueOf(location.hasBearing() ? location.getBearing() : -1.0f), Integer.valueOf((int) (location.hasAccuracy() ? location.getAccuracy() : -1.0f)), Integer.valueOf(p), Double.valueOf(location.hasAltitude() ? location.getAltitude() : 555.0d), Long.valueOf(location.getTime() / 1000), Integer.valueOf(p), Integer.valueOf(q), Double.valueOf(G));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, Location location) {
        if (location == null) {
            return;
        }
        String str2 = str + com.baidu.location.a.a.a().d();
        boolean zF = i.a().f();
        t.a(new com.baidu.location.e.a(com.baidu.location.e.b.a().f()));
        t.a(System.currentTimeMillis());
        t.a(new Location(location));
        t.a(str2);
        if (zF) {
            return;
        }
        w.a(t.c(), null, t.d(), str2);
    }

    public static boolean a(Location location, Location location2, boolean z) {
        if (location == location2) {
            return false;
        }
        if (location == null || location2 == null) {
            return true;
        }
        float speed = location2.getSpeed();
        if (z && ((k.v == 3 || !com.baidu.location.g.d.a().a(location2.getLongitude(), location2.getLatitude())) && speed < 5.0f)) {
            return true;
        }
        float fDistanceTo = location2.distanceTo(location);
        return speed > k.L ? fDistanceTo > k.N : speed > k.K ? fDistanceTo > k.M : fDistanceTo > 5.0f;
    }

    public static String b(Location location) {
        String strA = a(location);
        if (strA == null) {
            return strA;
        }
        return strA + "&g_tp=0";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        this.x = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(String str) {
        int i;
        if (str.indexOf("*") != -1 && str.indexOf("$") != -1 && str.indexOf("$") <= str.indexOf("*") && str.length() >= str.indexOf("*")) {
            byte[] bytes = str.substring(0, str.indexOf("*")).getBytes();
            int i2 = bytes[1];
            for (int i3 = 2; i3 < bytes.length; i3++) {
                i2 ^= bytes[i3];
            }
            String str2 = String.format("%02x", Integer.valueOf(i2));
            int iIndexOf = str.indexOf("*");
            if (iIndexOf != -1 && str.length() >= (i = iIndexOf + 3) && str2.equalsIgnoreCase(str.substring(iIndexOf + 1, i))) {
                return true;
            }
        }
        return false;
    }

    public static String c(Location location) {
        String strA = a(location);
        if (strA == null) {
            return strA;
        }
        return strA + C;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Location location) {
        this.D.sendMessage(this.D.obtainMessage(1, location));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Location location) {
        String str = null;
        if (location == null) {
            this.f2408f = null;
            return;
        }
        int i = p;
        if (i == 0) {
            try {
                i = location.getExtras().getInt("satellites");
            } catch (Exception unused) {
            }
        }
        if (i != 0 || k.m) {
            if (this.m && location.getSpeed() == 0.0d && this.A != 0.0d && System.currentTimeMillis() - this.B < 2000.0d) {
                location.setSpeed((float) this.A);
            }
            System.currentTimeMillis();
            this.f2408f = location;
            Location location2 = new Location(this.f2408f);
            int i2 = p;
            if (this.f2408f != null) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                this.f2408f.setTime(jCurrentTimeMillis);
                float speed = (float) (((double) this.f2408f.getSpeed()) * 3.6d);
                if (!this.f2408f.hasSpeed()) {
                    speed = -1.0f;
                }
                if (i2 == 0) {
                    try {
                        i2 = this.f2408f.getExtras().getInt("satellites");
                    } catch (Exception unused2) {
                    }
                }
                str = String.format(Locale.CHINA, "&ll=%.5f|%.5f&s=%.1f&d=%.1f&ll_n=%d&ll_t=%d", Double.valueOf(this.f2408f.getLongitude()), Double.valueOf(this.f2408f.getLatitude()), Float.valueOf(speed), Float.valueOf(this.f2408f.getBearing()), Integer.valueOf(i2), Long.valueOf(jCurrentTimeMillis));
            }
            this.w = str;
            if (j() && this.f2408f != null) {
                if (com.baidu.location.indoor.g.a().e()) {
                    com.baidu.location.indoor.g.a().a(this.f2408f, this.J);
                }
                if (!com.baidu.location.indoor.g.a().f()) {
                    com.baidu.location.indoor.g.a().f();
                    com.baidu.location.a.a.a().a(g());
                }
                if (p > 2 && w.a(this.f2408f, true)) {
                    boolean zF = i.a().f();
                    t.a(new com.baidu.location.e.a(com.baidu.location.e.b.a().f()));
                    t.a(System.currentTimeMillis());
                    t.a(new Location(this.f2408f));
                    t.a(com.baidu.location.a.a.a().d());
                    if (!zF) {
                        x.a().b();
                    }
                }
            }
            x.a().a(location2, p);
        }
    }

    static /* synthetic */ int i(e eVar) {
        int i = eVar.F;
        eVar.F = i + 1;
        return i;
    }

    public static String k() {
        long jCurrentTimeMillis = System.currentTimeMillis() - s;
        if (jCurrentTimeMillis < 0 || jCurrentTimeMillis >= 3000) {
            return null;
        }
        return String.format(Locale.US, "&gsvn=%d&gsfn=%d", Integer.valueOf(r), Integer.valueOf(p));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String l() {
        StringBuilder sb = new StringBuilder();
        if (this.J.size() > 32 || this.J.size() == 0) {
            return sb.toString();
        }
        boolean z = true;
        for (ArrayList<Float> arrayList : this.J) {
            if (arrayList.size() == 6) {
                if (z) {
                    z = false;
                } else {
                    sb.append("|");
                }
                sb.append(String.format("%.1f;", arrayList.get(0)));
                sb.append(String.format("%.1f;", arrayList.get(1)));
                sb.append(String.format("%.0f;", arrayList.get(2)));
                sb.append(String.format("%.0f;", arrayList.get(3)));
                sb.append(String.format("%.0f", arrayList.get(4)));
            }
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String m() {
        StringBuilder sb = new StringBuilder();
        if (this.K.size() == 0) {
            return sb.toString();
        }
        boolean z = true;
        for (ArrayList<Float> arrayList : this.K) {
            if (arrayList.size() == 6) {
                if (z) {
                    z = false;
                } else {
                    sb.append("|");
                }
                sb.append(String.format("%.1f;", arrayList.get(0)));
                sb.append(String.format("%.1f;", arrayList.get(1)));
                sb.append(String.format("%.0f;", arrayList.get(2)));
                sb.append(String.format("%.0f;", arrayList.get(3)));
                sb.append(String.format("%.0f", arrayList.get(4)));
                sb.append(String.format("%.0f", arrayList.get(5)));
            }
        }
        return sb.toString();
    }

    public void a(String str) {
        if (str.length() != 0 && b(str)) {
            if (str.startsWith("$GPPWR,") || str.startsWith("$GNGST,") || str.startsWith("$GPGST,") || str.startsWith("$GLGSV,") || str.startsWith("$GNGSV,") || str.startsWith("$BDGSV,") || str.startsWith("$GPZDA,") || str.startsWith("$GPGSA,") || str.startsWith("$GNVTG,") || str.startsWith("$GPVTG,") || str.startsWith("$GNGSA,") || str.startsWith("$GPNTR,") || str.startsWith("$GNGGA,") || str.startsWith("$GPGGA,") || str.startsWith("$GPRMC,") || str.startsWith("$GPGSV,") || str.startsWith("$BDGSA,")) {
                String[] strArrSplit = str.split(AppInfo.DELIM);
                a(str, AppInfo.DELIM);
                if (strArrSplit != null && strArrSplit.length > 0) {
                    if ((strArrSplit[0].equalsIgnoreCase("$GPRMC") || strArrSplit[0].equalsIgnoreCase("$GNRMC") || strArrSplit[0].equalsIgnoreCase("$GLRMC") || strArrSplit[0].equalsIgnoreCase("$BDRMC")) && strArrSplit.length > 7 && strArrSplit[7].trim().length() > 0) {
                        this.A = ((Double.valueOf(strArrSplit[7]).doubleValue() * 1.852d) / 3600.0d) * 1000.0d;
                        this.B = System.currentTimeMillis();
                    }
                }
            }
        }
    }

    public void a(boolean z) {
        if (z) {
            c();
        } else {
            d();
        }
    }

    public synchronized void b() {
        if (com.baidu.location.f.isServing) {
            this.f2406d = com.baidu.location.f.getServiceContext();
            try {
                this.f2407e = (LocationManager) this.f2406d.getSystemService(FirebaseAnalytics.Param.LOCATION);
                f fVar = null;
                if (this.k) {
                    this.j = new a(this, fVar);
                    this.f2407e.registerGnssStatusCallback(this.j);
                } else {
                    this.l = new b(this, fVar);
                    this.f2407e.addGpsStatusListener(this.l);
                }
                if (this.m) {
                    if (Build.VERSION.SDK_INT >= 24) {
                        this.o = new f(this);
                        this.f2407e.addNmeaListener(this.o);
                    } else {
                        this.n = new d(this, fVar);
                        this.f2407e.addNmeaListener(this.n);
                    }
                }
                this.f2410h = new C0021e(this, fVar);
                this.f2407e.requestLocationUpdates("passive", 9000L, 0.0f, this.f2410h);
            } catch (Exception unused) {
            }
            this.D = new g(this);
        }
    }

    public void c() {
        Log.d(com.baidu.location.g.a.f2452a, "start gps...");
        if (this.v) {
            return;
        }
        try {
            this.f2409g = new c(this, null);
            try {
                this.f2407e.sendExtraCommand("gps", "force_xtra_injection", new Bundle());
            } catch (Exception unused) {
            }
            this.f2407e.requestLocationUpdates("gps", 1000L, 0.0f, this.f2409g);
            this.H = System.currentTimeMillis();
            this.v = true;
        } catch (Exception unused2) {
        }
    }

    public void d() {
        if (this.v) {
            LocationManager locationManager = this.f2407e;
            if (locationManager != null) {
                try {
                    if (this.f2409g != null) {
                        locationManager.removeUpdates(this.f2409g);
                    }
                } catch (Exception unused) {
                }
            }
            k.f2503d = 0;
            k.v = 0;
            this.f2409g = null;
            this.v = false;
            b(false);
        }
    }

    public synchronized void e() {
        d();
        if (this.f2407e == null) {
            return;
        }
        try {
            if (this.l != null) {
                this.f2407e.removeGpsStatusListener(this.l);
            }
            if (this.k && this.j != null) {
                this.f2407e.unregisterGnssStatusCallback(this.j);
            }
            if (this.m && this.n != null) {
                this.f2407e.removeNmeaListener(this.n);
            }
            this.f2407e.removeUpdates(this.f2410h);
        } catch (Exception unused) {
        }
        this.l = null;
        this.f2407e = null;
    }

    public String f() {
        Location location;
        if (!j() || (location = this.f2408f) == null) {
            return null;
        }
        return String.format("%s&idgps_tp=%s", a(location).replaceAll("ll", "idll").replaceAll("&d=", "&idd=").replaceAll("&s", "&ids="), this.f2408f.getProvider());
    }

    public String g() {
        double[] dArrCoorEncrypt;
        boolean z;
        StringBuilder sb;
        String str;
        if (this.f2408f == null) {
            return null;
        }
        String str2 = "{\"result\":{\"time\":\"" + k.a() + "\",\"error\":\"61\"},\"content\":{\"point\":{\"x\":\"%f\",\"y\":\"%f\"},\"radius\":\"%d\",\"d\":\"%f\",\"s\":\"%f\",\"n\":\"%d\"";
        int accuracy = (int) (this.f2408f.hasAccuracy() ? this.f2408f.getAccuracy() : 10.0f);
        float speed = (float) (((double) this.f2408f.getSpeed()) * 3.6d);
        if (!this.f2408f.hasSpeed()) {
            speed = -1.0f;
        }
        double[] dArr = new double[2];
        if (com.baidu.location.g.d.a().a(this.f2408f.getLongitude(), this.f2408f.getLatitude())) {
            dArrCoorEncrypt = Jni.coorEncrypt(this.f2408f.getLongitude(), this.f2408f.getLatitude(), BDLocation.BDLOCATION_WGS84_TO_GCJ02);
            if (dArrCoorEncrypt[0] <= 0.0d && dArrCoorEncrypt[1] <= 0.0d) {
                dArrCoorEncrypt[0] = this.f2408f.getLongitude();
                dArrCoorEncrypt[1] = this.f2408f.getLatitude();
            }
            z = true;
        } else {
            dArr[0] = this.f2408f.getLongitude();
            dArr[1] = this.f2408f.getLatitude();
            dArrCoorEncrypt = Jni.coorEncrypt(this.f2408f.getLongitude(), this.f2408f.getLatitude(), BDLocation.BDLOCATION_WGS84_TO_GCJ02);
            if (dArrCoorEncrypt[0] <= 0.0d && dArrCoorEncrypt[1] <= 0.0d) {
                dArrCoorEncrypt[0] = this.f2408f.getLongitude();
                dArrCoorEncrypt[1] = this.f2408f.getLatitude();
            }
            z = false;
        }
        String str3 = String.format(Locale.CHINA, str2, Double.valueOf(dArrCoorEncrypt[0]), Double.valueOf(dArrCoorEncrypt[1]), Integer.valueOf(accuracy), Float.valueOf(this.f2408f.getBearing()), Float.valueOf(speed), Integer.valueOf(p));
        if (!z) {
            str3 = str3 + ",\"in_cn\":\"0\"";
        }
        if (this.f2408f.hasAltitude()) {
            sb = new StringBuilder();
            sb.append(str3);
            str = String.format(Locale.CHINA, ",\"h\":%.2f}}", Double.valueOf(this.f2408f.getAltitude()));
        } else {
            sb = new StringBuilder();
            sb.append(str3);
            str = "}}";
        }
        sb.append(str);
        return sb.toString();
    }

    public Location h() {
        if (this.f2408f != null && Math.abs(System.currentTimeMillis() - this.f2408f.getTime()) <= DateUtil.MINUTE) {
            return this.f2408f;
        }
        return null;
    }

    public boolean i() {
        try {
            long jCurrentTimeMillis = System.currentTimeMillis() - this.I;
            if (this.f2408f != null && this.f2408f.getLatitude() != 0.0d && this.f2408f.getLongitude() != 0.0d) {
                if (p <= 2 && this.f2408f.getExtras().getInt("satellites", 3) <= 2) {
                    if (Math.abs(jCurrentTimeMillis) < BootloaderScanner.TIMEOUT) {
                    }
                }
                return true;
            }
            return false;
        } catch (Exception unused) {
            Location location = this.f2408f;
            return (location == null || location.getLatitude() == 0.0d || this.f2408f.getLongitude() == 0.0d) ? false : true;
        }
    }

    public boolean j() {
        if (!i() || System.currentTimeMillis() - this.y > DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT) {
            return false;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (!this.u || jCurrentTimeMillis - this.t >= 3000) {
            return this.x;
        }
        return true;
    }
}