package com.amap.api.mapcore.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.autonavi.amap.mapcore.Inner_3dMap_location;
import com.autonavi.amap.mapcore.Inner_3dMap_locationOption;
import com.bumptech.glide.load.Key;
import com.realsil.sdk.dfu.model.DfuConfig;

/* JADX INFO: compiled from: MapNetLocation.java */
/* JADX INFO: loaded from: classes.dex */
public final class kt {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Context f1601a;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ka f1605e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private lj f1606f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private kc f1608h;
    private ConnectivityManager i;
    private ke j;
    private Inner_3dMap_locationOption l;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private a f1607g = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    boolean f1602b = false;
    private StringBuilder k = new StringBuilder();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    String f1603c = null;
    private kr m = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    long f1604d = 0;
    private final String n = "\"status\":\"0\"";
    private final String o = "</body></html>";

    /* JADX INFO: compiled from: MapNetLocation.java */
    private class a extends BroadcastReceiver {
        private a() {
        }

        /* synthetic */ a(kt ktVar, byte b2) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (context == null || intent == null) {
                return;
            }
            try {
                String action = intent.getAction();
                if (TextUtils.isEmpty(action)) {
                    return;
                }
                if (action.equals("android.net.wifi.SCAN_RESULTS")) {
                    if (kt.this.f1605e != null) {
                        kt.this.f1605e.c();
                    }
                } else {
                    if (!action.equals("android.net.wifi.WIFI_STATE_CHANGED") || kt.this.f1605e == null) {
                        return;
                    }
                    kt.this.f1605e.d();
                }
            } catch (Throwable th) {
                kg.a(th, "MapNetLocation", "onReceive");
            }
        }
    }

    public kt(Context context) {
        this.f1601a = null;
        this.f1605e = null;
        this.f1606f = null;
        this.f1608h = null;
        this.i = null;
        this.j = null;
        this.l = null;
        try {
            this.f1601a = context.getApplicationContext();
            kk.b(this.f1601a);
            a(this.f1601a);
            this.l = new Inner_3dMap_locationOption();
            if (this.f1605e == null) {
                this.f1605e = new ka(this.f1601a, (WifiManager) kk.a(this.f1601a, "wifi"));
                this.f1605e.a(this.f1602b);
            }
            if (this.f1606f == null) {
                this.f1606f = new lj(this.f1601a);
            }
            if (this.f1608h == null) {
                gj.a(this.f1601a);
                this.f1608h = kc.a(this.f1601a);
            }
            if (this.i == null) {
                this.i = (ConnectivityManager) kk.a(this.f1601a, "connectivity");
            }
            this.j = new ke();
            c();
        } catch (Throwable th) {
            kg.a(th, "MapNetLocation", "<init>");
        }
    }

    private static kr a(kr krVar) {
        return kn.a().a(krVar);
    }

    private void a(Context context) {
        try {
            if (context.checkCallingOrSelfPermission(gt.c("EYW5kcm9pZC5wZXJtaXNzaW9uLldSSVRFX1NFQ1VSRV9TRVRUSU5HUw==")) == 0) {
                this.f1602b = true;
            }
        } catch (Throwable unused) {
        }
    }

    private boolean a(long j) {
        if (kk.b() - j < 800) {
            if ((kv.a(this.m) ? kk.a() - this.m.getTime() : 0L) <= DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT) {
                return true;
            }
        }
        return false;
    }

    private void c() {
        try {
            byte b2 = 0;
            if (this.f1607g == null) {
                this.f1607g = new a(this, b2);
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
            intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
            this.f1601a.registerReceiver(this.f1607g, intentFilter);
            this.f1605e.b(false);
            this.f1606f.b();
        } catch (Throwable th) {
            kg.a(th, "MapNetLocation", "initBroadcastListener");
        }
    }

    private kr d() throws Exception {
        StringBuilder sb;
        String str;
        String str2 = "";
        kr krVar = new kr("");
        ka kaVar = this.f1605e;
        if (kaVar != null && kaVar.g()) {
            krVar.setErrorCode(15);
            return krVar;
        }
        try {
            if (this.j == null) {
                this.j = new ke();
            }
            this.j.a(this.f1601a, this.l.isNeedAddress(), this.l.isOffset(), this.f1606f, this.f1605e, this.i, this.f1603c);
            ku kuVar = new ku();
            byte[] bArr = null;
            try {
                try {
                    is isVarA = this.f1608h.a(this.f1608h.a(this.f1601a, this.j.a(), kg.a(), kg.b()));
                    if (isVarA != null) {
                        bArr = isVarA.f1402a;
                        str2 = isVarA.f1404c;
                    }
                    if (bArr == null || bArr.length == 0) {
                        krVar.setErrorCode(4);
                        this.k.append("please check the network");
                        if (!TextUtils.isEmpty(str2)) {
                            this.k.append(" #csid:" + str2);
                        }
                        krVar.setLocationDetail(this.k.toString());
                        return krVar;
                    }
                    String str3 = new String(bArr, Key.STRING_CHARSET_NAME);
                    if (str3.contains("\"status\":\"0\"")) {
                        return kuVar.a(str3, this.f1601a, isVarA);
                    }
                    if (str3.contains("</body></html>")) {
                        krVar.setErrorCode(5);
                        ka kaVar2 = this.f1605e;
                        if (kaVar2 == null || !kaVar2.a(this.i)) {
                            sb = this.k;
                            str = "request may be intercepted";
                        } else {
                            sb = this.k;
                            str = "make sure you are logged in to the network";
                        }
                        sb.append(str);
                        if (!TextUtils.isEmpty(str2)) {
                            this.k.append(" #csid:" + str2);
                        }
                        krVar.setLocationDetail(this.k.toString());
                        return krVar;
                    }
                    byte[] bArrA = kb.a(bArr);
                    if (bArrA == null) {
                        krVar.setErrorCode(5);
                        this.k.append("decrypt response data error");
                        if (!TextUtils.isEmpty(str2)) {
                            this.k.append(" #csid:" + str2);
                        }
                        krVar.setLocationDetail(this.k.toString());
                        return krVar;
                    }
                    kr krVarA = kuVar.a(bArrA);
                    this.f1603c = krVarA.a();
                    if (krVarA.getErrorCode() != 0) {
                        if (!TextUtils.isEmpty(str2)) {
                            krVarA.setLocationDetail(krVarA.getLocationDetail() + " #csid:" + str2);
                        }
                        return krVarA;
                    }
                    if (!kv.a(krVarA)) {
                        String strB = krVarA.b();
                        krVarA.setErrorCode(6);
                        StringBuilder sb2 = this.k;
                        StringBuilder sb3 = new StringBuilder("location faile retype:");
                        sb3.append(krVarA.d());
                        sb3.append(" rdesc:");
                        if (strB == null) {
                            strB = "null";
                        }
                        sb3.append(strB);
                        sb2.append(sb3.toString());
                        if (!TextUtils.isEmpty(str2)) {
                            this.k.append(" #csid:" + str2);
                        }
                        krVarA.setLocationDetail(this.k.toString());
                        return krVarA;
                    }
                    krVarA.e();
                    if (krVarA.getErrorCode() == 0 && krVarA.getLocationType() == 0) {
                        if ("-5".equals(krVarA.d()) || "1".equals(krVarA.d()) || "2".equals(krVarA.d()) || "14".equals(krVarA.d()) || "24".equals(krVarA.d()) || "-1".equals(krVarA.d())) {
                            krVarA.setLocationType(5);
                        } else {
                            krVarA.setLocationType(6);
                        }
                        this.k.append(krVarA.d());
                        if (!TextUtils.isEmpty(str2)) {
                            this.k.append(" #csid:" + str2);
                        }
                        krVarA.setLocationDetail(this.k.toString());
                    }
                    return krVarA;
                } catch (Throwable th) {
                    kg.a(th, "MapNetLocation", "getApsLoc req");
                    krVar.setErrorCode(4);
                    this.k.append("please check the network");
                    krVar.setLocationDetail(this.k.toString());
                    return krVar;
                }
            } catch (Throwable th2) {
                kg.a(th2, "MapNetLocation", "getApsLoc buildV4Dot2");
                krVar.setErrorCode(3);
                this.k.append("buildV4Dot2 error " + th2.getMessage());
                krVar.setLocationDetail(this.k.toString());
                return krVar;
            }
        } catch (Throwable th3) {
            kg.a(th3, "MapNetLocation", "getApsLoc");
            this.k.append("get parames error:" + th3.getMessage());
            krVar.setErrorCode(3);
            krVar.setLocationDetail(this.k.toString());
            return krVar;
        }
    }

    public final Inner_3dMap_location a() {
        if (this.k.length() > 0) {
            StringBuilder sb = this.k;
            sb.delete(0, sb.length());
        }
        if (a(this.f1604d) && kv.a(this.m)) {
            return this.m;
        }
        this.f1604d = kk.b();
        if (this.f1601a == null) {
            this.k.append("context is null");
            Inner_3dMap_location inner_3dMap_location = new Inner_3dMap_location("");
            inner_3dMap_location.setErrorCode(1);
            inner_3dMap_location.setLocationDetail(this.k.toString());
            return inner_3dMap_location;
        }
        try {
            this.f1606f.b();
        } catch (Throwable th) {
            kg.a(th, "MapNetLocation", "getLocation getCgiListParam");
        }
        try {
            this.f1605e.b(true);
        } catch (Throwable th2) {
            kg.a(th2, "MapNetLocation", "getLocation getScanResultsParam");
        }
        try {
            this.m = d();
            this.m = a(this.m);
        } catch (Throwable th3) {
            kg.a(th3, "MapNetLocation", "getLocation getScanResultsParam");
        }
        return this.m;
    }

    public final void a(Inner_3dMap_locationOption inner_3dMap_locationOption) {
        this.l = inner_3dMap_locationOption;
        if (this.l == null) {
            this.l = new Inner_3dMap_locationOption();
        }
        try {
            ka kaVar = this.f1605e;
            this.l.isWifiActiveScan();
            kaVar.c(this.l.isWifiScan());
        } catch (Throwable unused) {
        }
        try {
            this.f1608h.a(this.l.getHttpTimeOut(), this.l.getLocationProtocol().equals(Inner_3dMap_locationOption.Inner_3dMap_Enum_LocationProtocol.HTTPS));
        } catch (Throwable unused2) {
        }
    }

    public final void b() {
        this.f1602b = false;
        this.f1603c = null;
        try {
            if (this.f1601a != null && this.f1607g != null) {
                this.f1601a.unregisterReceiver(this.f1607g);
            }
            if (this.f1606f != null) {
                this.f1606f.h();
            }
            if (this.f1605e != null) {
                this.f1605e.h();
            }
            this.f1607g = null;
        } catch (Throwable unused) {
            this.f1607g = null;
        }
    }
}