package com.loc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.text.TextUtils;
import com.amap.api.location.AMapLocationClientOption;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.autonavi.base.amap.mapcore.tools.GLMapStaticValue;
import com.bumptech.glide.load.Key;
import java.util.ArrayList;

/* JADX INFO: compiled from: Aps.java */
/* JADX INFO: loaded from: classes3.dex */
public final class dn {
    static int D = -1;
    private static boolean K = false;
    private static volatile boolean P = false;
    private Handler N;
    private String O;
    private Cdo Q;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Context f5009a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    ConnectivityManager f5010b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    dx f5011c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    dw f5012d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    dz f5013e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    dp f5014f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    eg f5015g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    ArrayList<ScanResult> f5016h = new ArrayList<>();
    a i = null;
    AMapLocationClientOption j = new AMapLocationClientOption();
    ds k = null;
    long l = 0;
    private int I = 0;
    eh m = null;
    boolean n = false;
    private String J = null;
    ee o = null;
    StringBuilder p = new StringBuilder();
    boolean q = true;
    boolean r = true;
    AMapLocationClientOption.GeoLanguage s = AMapLocationClientOption.GeoLanguage.DEFAULT;
    boolean t = true;
    boolean u = false;
    WifiInfo v = null;
    boolean w = true;
    private String L = null;
    StringBuilder x = null;
    boolean y = false;
    public boolean z = false;
    int A = 12;
    private boolean M = true;
    dt B = null;
    boolean C = false;
    dr E = null;
    String F = null;
    IntentFilter G = null;
    LocationManager H = null;

    /* JADX INFO: renamed from: com.loc.dn$1, reason: invalid class name */
    /* JADX INFO: compiled from: Aps.java */
    static /* synthetic */ class AnonymousClass1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f5017a = new int[AMapLocationClientOption.GeoLanguage.values().length];

        static {
            try {
                f5017a[AMapLocationClientOption.GeoLanguage.DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5017a[AMapLocationClientOption.GeoLanguage.ZH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f5017a[AMapLocationClientOption.GeoLanguage.EN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: compiled from: Aps.java */
    class a extends BroadcastReceiver {
        a() {
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
                if (!action.equals("android.net.wifi.SCAN_RESULTS")) {
                    if (!action.equals("android.net.wifi.WIFI_STATE_CHANGED") || dn.this.f5011c == null) {
                        return;
                    }
                    dn.this.f5011c.j();
                    return;
                }
                if (dn.this.f5011c != null) {
                    dn.this.f5011c.i();
                }
                try {
                    if (intent.getExtras() == null || !intent.getExtras().getBoolean("resultsUpdated", true) || dn.this.f5011c == null) {
                        return;
                    }
                    dn.this.f5011c.h();
                } catch (Throwable unused) {
                }
            } catch (Throwable th) {
                ej.a(th, "Aps", "onReceive");
            }
        }
    }

    private static ds a(int i, String str) {
        ds dsVar = new ds("");
        dsVar.setErrorCode(i);
        dsVar.setLocationDetail(str);
        if (i == 15) {
            en.a((String) null, 2151);
        }
        return dsVar;
    }

    private ds a(ds dsVar, aw awVar, dm dmVar) {
        if (awVar != null) {
            try {
                if (awVar.f4809a != null && awVar.f4809a.length != 0) {
                    eg egVar = new eg();
                    String str = new String(awVar.f4809a, Key.STRING_CHARSET_NAME);
                    if (str.contains("\"status\":\"0\"")) {
                        ds dsVarA = egVar.a(str, this.f5009a, awVar, dmVar);
                        dsVarA.h(this.x.toString());
                        return dsVarA;
                    }
                    if (!str.contains("</body></html>")) {
                        return null;
                    }
                    dsVar.setErrorCode(5);
                    if (this.f5011c == null || !this.f5011c.a(this.f5010b)) {
                        dmVar.f("#0502");
                        this.p.append("请求可能被劫持了#0502");
                        en.a((String) null, 2052);
                    } else {
                        dmVar.f("#0501");
                        this.p.append("您连接的是一个需要登录的网络，请确认已经登入网络#0501");
                        en.a((String) null, 2051);
                    }
                    dsVar.setLocationDetail(this.p.toString());
                    return dsVar;
                }
            } catch (Throwable th) {
                dsVar.setErrorCode(4);
                ej.a(th, "Aps", "checkResponseEntity");
                dmVar.f("#0403");
                this.p.append("check response exception ex is" + th.getMessage() + "#0403");
                dsVar.setLocationDetail(this.p.toString());
                return dsVar;
            }
        }
        dsVar.setErrorCode(4);
        this.p.append("网络异常,请求异常#0403");
        dmVar.f("#0403");
        dsVar.h(this.x.toString());
        dsVar.setLocationDetail(this.p.toString());
        if (awVar != null) {
            en.a(awVar.f4812d, 2041);
        }
        return dsVar;
    }

    private StringBuilder a(StringBuilder sb) {
        if (sb == null) {
            sb = new StringBuilder(700);
        } else {
            sb.delete(0, sb.length());
        }
        sb.append(this.f5012d.m());
        sb.append(this.f5011c.m());
        return sb;
    }

    private void a(dm dmVar, ef efVar, String str, String str2, String str3, int i) {
        dmVar.b(str3);
        dmVar.c("FAIL");
        ec.a(this.f5009a).a(false, i);
        efVar.a(str);
        efVar.b(str2);
        if (this.o.a() > ei.q()) {
            efVar.a(ei.q() * 1000);
            efVar.b(ei.q() * 1000);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v1, types: [com.loc.ee] */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v12 */
    /* JADX WARN: Type inference failed for: r14v13 */
    /* JADX WARN: Type inference failed for: r14v2, types: [int] */
    /* JADX WARN: Type inference failed for: r14v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v4 */
    /* JADX WARN: Type inference failed for: r14v5 */
    /* JADX WARN: Type inference failed for: r14v6 */
    /* JADX WARN: Type inference failed for: r14v7, types: [java.lang.CharSequence, java.lang.String] */
    /* JADX WARN: Type inference failed for: r19v0, types: [com.loc.dn] */
    /* JADX WARN: Type inference failed for: r21v0, types: [com.loc.dm] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private ds b(boolean z, dm dmVar) {
        ?? A;
        StringBuilder sb;
        StringBuilder sb2;
        String str;
        ef efVarA;
        String strC;
        String strD;
        boolean z2;
        aw awVarA;
        long jB;
        ec ecVarA;
        int i;
        String str2;
        char c2;
        try {
            if (TextUtils.isEmpty(this.O)) {
                this.O = u.b(n.a(this.f5009a) + AppInfo.DELIM + n.h(this.f5009a));
            }
            StringBuilder sb3 = this.p;
            sb3.append("#id:");
            sb3.append(this.O);
        } catch (Throwable unused) {
        }
        ds dsVar = new ds("");
        try {
            if (this.m == null) {
                this.m = new eh();
            }
            if (this.j == null) {
                this.j = new AMapLocationClientOption();
            }
            this.m.a(this.f5009a, this.j.isNeedAddress(), this.j.isOffset(), this.f5012d, this.f5011c, this.f5010b, this.F);
            byte[] bArrA = this.m.a();
            this.l = ep.b();
            dmVar.a(this.l);
            try {
                ej.c(this.f5009a);
                ?? r11 = this.o;
                Context context = this.f5009a;
                A = ej.a();
                efVarA = r11.a(context, bArrA, A, ej.b(), z);
                strC = efVarA.c();
                strD = efVarA.d();
                l.a(this.f5009a);
                z2 = !TextUtils.isEmpty(strD) && strD.contains("dualstack");
            } catch (Throwable th) {
                th = th;
                A = 4;
            }
            try {
                if (l.a() && l.c() && z2) {
                    dmVar.a("v6");
                    A = l.b() ? null : ec.a(this.f5009a).a(efVarA, ec.f5117b);
                    if (TextUtils.isEmpty(A)) {
                        c2 = 4;
                        awVarA = this.o.a(efVarA);
                        jB = ep.b();
                        dmVar.d("SUCCESS");
                    } else {
                        try {
                            awVarA = this.o.a(efVarA, 2);
                            jB = ep.b();
                            dmVar.b(A);
                            dmVar.c("SUCCESS");
                            c2 = 4;
                        } catch (Throwable unused2) {
                            c2 = 4;
                            a(dmVar, efVarA, strC, strD, A, ec.f5117b);
                            awVarA = l.b() ? this.o.a(efVarA, 4) : this.o.a(efVarA, 1);
                            jB = ep.b();
                            dmVar.d("SUCCESS");
                            ec.a(this.f5009a).a(ec.f5117b);
                        }
                    }
                    ecVarA = ec.a(this.f5009a);
                    i = ec.f5117b;
                    A = c2;
                } else {
                    A = 4;
                    dmVar.a("v4");
                    String strA = ec.a(this.f5009a).a(efVarA, ec.f5116a);
                    if (TextUtils.isEmpty(strA)) {
                        awVarA = this.o.a(efVarA, 1);
                        jB = ep.b();
                        dmVar.d("SUCCESS");
                    } else {
                        try {
                            awVarA = this.o.a(efVarA, 1);
                            jB = ep.b();
                            dmVar.b(strA);
                            dmVar.c("SUCCESS");
                            ec.a(this.f5009a).a(true, ec.f5116a);
                        } catch (Throwable unused3) {
                            a(dmVar, efVarA, strC, strD, strA, ec.f5116a);
                            awVarA = this.o.a(efVarA, 1);
                            jB = ep.b();
                            dmVar.d("SUCCESS");
                            ec.a(this.f5009a).a(ec.f5116a);
                        }
                    }
                    ecVarA = ec.a(this.f5009a);
                    i = ec.f5116a;
                    A = A;
                }
                ecVarA.a(true, i);
                if (this.Q != null) {
                    this.Q.d();
                }
                dmVar.b(jB);
                if (awVarA != null) {
                    if (!TextUtils.isEmpty(awVarA.f4811c)) {
                        this.p.append("#csid:" + awVarA.f4811c);
                    }
                    str2 = awVarA.f4812d;
                    dsVar.h(this.x.toString());
                } else {
                    str2 = "";
                }
                ds dsVarA = a(dsVar, awVarA, dmVar);
                if (dsVarA != null) {
                    return dsVarA;
                }
                byte[] bArrB = dy.b(awVarA.f4809a);
                if (bArrB == null) {
                    dsVar.setErrorCode(5);
                    dmVar.f("#0503");
                    this.p.append("解密数据失败#0503");
                    dsVar.setLocationDetail(this.p.toString());
                    en.a(str2, 2053);
                    return dsVar;
                }
                ds dsVarA2 = this.f5015g.a(dsVar, bArrB, dmVar);
                if (!ep.a(dsVarA2)) {
                    this.J = dsVarA2.b();
                    en.a(str2, !TextUtils.isEmpty(this.J) ? 2062 : 2061);
                    dsVarA2.setErrorCode(6);
                    dmVar.f("#0601");
                    StringBuilder sb4 = this.p;
                    StringBuilder sb5 = new StringBuilder("location faile retype:");
                    sb5.append(dsVarA2.d());
                    sb5.append(" rdesc:");
                    sb5.append(TextUtils.isEmpty(this.J) ? "" : this.J);
                    sb5.append("#0601");
                    sb4.append(sb5.toString());
                    dsVarA2.h(this.x.toString());
                    dsVarA2.setLocationDetail(this.p.toString());
                    return dsVarA2;
                }
                if (dsVarA2.getErrorCode() == 0 && dsVarA2.getLocationType() == 0) {
                    if ("-5".equals(dsVarA2.d()) || "1".equals(dsVarA2.d()) || "2".equals(dsVarA2.d()) || "14".equals(dsVarA2.d()) || "24".equals(dsVarA2.d()) || "-1".equals(dsVarA2.d())) {
                        dsVarA2.setLocationType(5);
                    } else {
                        dsVarA2.setLocationType(6);
                    }
                }
                dsVarA2.setOffset(this.r);
                dsVarA2.a(this.q);
                dsVarA2.f(String.valueOf(this.s));
                dsVarA2.e("new");
                dsVarA2.setLocationDetail(this.p.toString());
                this.F = dsVarA2.a();
                return dsVarA2;
            } catch (Throwable th2) {
                th = th2;
                ep.b();
                dmVar.d("FAIL");
                ec.a(this.f5009a).a(false, ec.f5116a);
                ej.a(th, "Aps", "getApsLoc req");
                en.a("/mobile/binary", th);
                if (ep.d(this.f5009a)) {
                    if (th instanceof j) {
                        j jVar = th;
                        if (jVar.a().contains("网络异常状态码")) {
                            dmVar.f("#0404");
                            StringBuilder sb6 = this.p;
                            sb6.append("网络异常，状态码错误#0404");
                            sb6.append(jVar.f());
                            ds dsVarA3 = a((int) A, this.p.toString());
                            dsVarA3.h(this.x.toString());
                            return dsVarA3;
                        }
                        if (jVar.f() == 23 || Math.abs((ep.b() - this.l) - this.j.getHttpTimeOut()) < 500) {
                            dmVar.f("#0402");
                            sb2 = this.p;
                            str = "网络异常，连接超时#0402";
                        } else {
                            sb = new StringBuilder("#0403,");
                        }
                    } else {
                        sb = new StringBuilder("#0403,");
                    }
                    sb.append(th.getMessage());
                    dmVar.f(sb.toString());
                    this.p.append("网络异常,请求异常#0403");
                    ds dsVarA32 = a((int) A, this.p.toString());
                    dsVarA32.h(this.x.toString());
                    return dsVarA32;
                }
                dmVar.f("#0401");
                sb2 = this.p;
                str = "网络异常，未连接到网络，请连接网络#0401";
                sb2.append(str);
                ds dsVarA322 = a((int) A, this.p.toString());
                dsVarA322.h(this.x.toString());
                return dsVarA322;
            }
        } catch (Throwable th3) {
            dmVar.f("#0301");
            this.p.append("get parames error:" + th3.getMessage() + "#0301");
            en.a((String) null, 2031);
            ds dsVarA4 = a(3, this.p.toString());
            dsVarA4.h(this.x.toString());
            return dsVarA4;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:87:0x0246 A[PHI: r15
  0x0246: PHI (r15v14 java.lang.StringBuilder) = 
  (r15v13 java.lang.StringBuilder)
  (r15v13 java.lang.StringBuilder)
  (r15v16 java.lang.StringBuilder)
  (r15v16 java.lang.StringBuilder)
 binds: [B:84:0x0240, B:86:0x0244, B:78:0x0206, B:80:0x020a] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String c(com.loc.dm r15) {
        /*
            Method dump skipped, instruction units count: 819
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.dn.c(com.loc.dm):java.lang.String");
    }

    private void c(ds dsVar) {
        if (dsVar != null) {
            this.k = dsVar;
        }
    }

    private void i() {
        int i;
        if (this.o != null) {
            try {
                if (this.j == null) {
                    this.j = new AMapLocationClientOption();
                }
                int i2 = 2;
                boolean z = true;
                if (this.j.getGeoLanguage() == null || (i = AnonymousClass1.f5017a[this.j.getGeoLanguage().ordinal()]) == 1) {
                    i2 = 0;
                } else if (i == 2) {
                    i2 = 1;
                } else if (i != 3) {
                    i2 = 0;
                }
                ee eeVar = this.o;
                long httpTimeOut = this.j.getHttpTimeOut();
                if (!this.j.getLocationProtocol().equals(AMapLocationClientOption.AMapLocationProtocol.HTTPS)) {
                    z = false;
                }
                eeVar.a(httpTimeOut, z, i2);
            } catch (Throwable unused) {
            }
        }
    }

    private void j() {
        try {
            if (this.i == null) {
                this.i = new a();
            }
            if (this.G == null) {
                this.G = new IntentFilter();
                this.G.addAction("android.net.wifi.WIFI_STATE_CHANGED");
                this.G.addAction("android.net.wifi.SCAN_RESULTS");
            }
            this.f5009a.registerReceiver(this.i, this.G);
        } catch (Throwable th) {
            ej.a(th, "Aps", "initBroadcastListener");
        }
    }

    private boolean k() {
        this.f5016h = this.f5011c.e();
        ArrayList<ScanResult> arrayList = this.f5016h;
        return arrayList == null || arrayList.size() <= 0;
    }

    public final ds a(double d2, double d3) {
        try {
            String strA = this.o.a(this.f5009a, d2, d3);
            if (!strA.contains("\"status\":\"1\"")) {
                return null;
            }
            ds dsVarA = this.f5015g.a(strA);
            dsVarA.setLatitude(d2);
            dsVarA.setLongitude(d3);
            return dsVarA;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:64|(1:70)(1:66)|71|(1:73)(2:75|(1:77)(9:78|(1:80)|106|81|(2:84|(1:86)(2:87|(1:89)(2:90|(1:92)(1:93))))|94|(3:96|(1:101)(1:100)|102)|103|104))|74|106|81|(2:84|(0)(0))|94|(0)|103|104) */
    /* JADX WARN: Can't wrap try/catch for region: R(16:6|(1:10)|11|(1:13)(4:15|(0)(4:17|(1:19)(1:20)|21|(1:23))|24|(10:35|(2:37|(1:39)(1:40))|109|41|(1:47)(1:46)|48|107|52|56|(2:58|59)(2:60|(2:62|63)(12:64|(1:70)(1:66)|71|(1:73)(2:75|(1:77)(9:78|(1:80)|106|81|(2:84|(1:86)(2:87|(1:89)(2:90|(1:92)(1:93))))|94|(3:96|(1:101)(1:100)|102)|103|104))|74|106|81|(2:84|(0)(0))|94|(0)|103|104)))(4:28|(1:32)|33|34))|14|24|(1:26)|35|(0)|109|41|(4:43|45|47|48)(0)|107|52|56|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00c4, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00c5, code lost:
    
        com.loc.ej.a(r0, "Aps", "getLocation getScanResultsParam");
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00d4, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00d5, code lost:
    
        com.loc.ej.a(r0, "Aps", "getLocation getCgiListParam");
     */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01b7 A[Catch: all -> 0x01d3, TryCatch #0 {all -> 0x01d3, blocks: (B:81:0x01a7, B:84:0x01ad, B:86:0x01b7, B:89:0x01c1, B:92:0x01cb, B:93:0x01d0), top: B:106:0x01a7 }] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01ee  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.loc.ds a(com.loc.dm r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 542
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.dn.a(com.loc.dm):com.loc.ds");
    }

    public final ds a(ds dsVar) {
        this.E.a(this.t);
        return this.E.a(dsVar);
    }

    public final ds a(boolean z, dm dmVar) {
        dmVar.e(z ? "statics" : "first");
        if (this.f5009a == null) {
            dmVar.f("#0101");
            this.p.append("context is null#0101");
            en.a((String) null, GLMapStaticValue.MAP_PARAMETERNAME_SATELLITE);
            return a(1, this.p.toString());
        }
        if (this.f5011c.l()) {
            dmVar.f("#1502");
            return a(15, "networkLocation has been mocked!#1502");
        }
        a();
        if (TextUtils.isEmpty(this.L)) {
            return a(this.A, this.p.toString());
        }
        ds dsVarB = b(z, dmVar);
        if (ep.a(dsVarB) && !P) {
            this.f5013e.a(this.x.toString());
            this.f5013e.a(this.f5012d.d());
            c(dsVarB);
        }
        P = true;
        return dsVarB;
    }

    public final void a() {
        this.o = ee.a(this.f5009a);
        i();
        if (this.f5010b == null) {
            this.f5010b = (ConnectivityManager) ep.a(this.f5009a, "connectivity");
        }
        if (this.m == null) {
            this.m = new eh();
        }
    }

    public final void a(Context context) {
        try {
            if (this.f5009a != null) {
                return;
            }
            this.E = new dr();
            this.f5009a = context.getApplicationContext();
            ep.b(this.f5009a);
            if (this.f5011c == null) {
                this.f5011c = new dx(this.f5009a, (WifiManager) ep.a(this.f5009a, "wifi"));
            }
            if (this.f5012d == null) {
                this.f5012d = new dw(this.f5009a);
            }
            if (this.f5013e == null) {
                this.f5013e = new dz();
            }
            if (this.f5015g == null) {
                this.f5015g = new eg();
            }
        } catch (Throwable th) {
            ej.a(th, "Aps", "initBase");
        }
    }

    public final void a(Handler handler) {
        this.N = handler;
    }

    public final void a(AMapLocationClientOption aMapLocationClientOption) {
        boolean zIsNeedAddress;
        boolean zIsOffset;
        this.j = aMapLocationClientOption;
        if (this.j == null) {
            this.j = new AMapLocationClientOption();
        }
        dx dxVar = this.f5011c;
        if (dxVar != null) {
            this.j.isWifiActiveScan();
            dxVar.a(this.j.isWifiScan(), this.j.isMockEnable(), AMapLocationClientOption.isOpenAlwaysScanWifi(), aMapLocationClientOption.getScanWifiInterval());
        }
        i();
        dz dzVar = this.f5013e;
        if (dzVar != null) {
            dzVar.a(this.j);
        }
        eg egVar = this.f5015g;
        if (egVar != null) {
            egVar.a(this.j);
        }
        AMapLocationClientOption.GeoLanguage geoLanguage = AMapLocationClientOption.GeoLanguage.DEFAULT;
        boolean zIsLocationCacheEnable = true;
        try {
            geoLanguage = this.j.getGeoLanguage();
            zIsNeedAddress = this.j.isNeedAddress();
            try {
                zIsOffset = this.j.isOffset();
                try {
                    zIsLocationCacheEnable = this.j.isLocationCacheEnable();
                    this.u = this.j.isOnceLocationLatest();
                    this.C = this.j.isSensorEnable();
                    if (zIsOffset != this.r || zIsNeedAddress != this.q || zIsLocationCacheEnable != this.t || geoLanguage != this.s) {
                        try {
                            if (this.f5013e != null) {
                                this.f5013e.a();
                            }
                            c((ds) null);
                            this.M = false;
                            if (this.E != null) {
                                this.E.a();
                            }
                        } catch (Throwable th) {
                            ej.a(th, "Aps", "cleanCache");
                        }
                    }
                } catch (Throwable unused) {
                }
            } catch (Throwable unused2) {
                zIsOffset = true;
            }
        } catch (Throwable unused3) {
            zIsNeedAddress = true;
            zIsOffset = true;
        }
        this.r = zIsOffset;
        this.q = zIsNeedAddress;
        this.t = zIsLocationCacheEnable;
        this.s = geoLanguage;
    }

    public final void b() {
        if (this.B == null) {
            this.B = new dt(this.f5009a);
        }
        j();
        this.f5011c.b(false);
        this.f5016h = this.f5011c.e();
        this.f5012d.a(false, k());
        this.f5013e.a(this.f5009a);
        try {
            if (this.f5009a.checkCallingOrSelfPermission(u.c("EYW5kcm9pZC5wZXJtaXNzaW9uLldSSVRFX1NFQ1VSRV9TRVRUSU5HUw==")) == 0) {
                this.n = true;
            }
        } catch (Throwable unused) {
        }
        this.z = true;
    }

    public final void b(dm dmVar) {
        try {
            if (this.y) {
                return;
            }
            if (this.L != null) {
                this.L = null;
            }
            if (this.x != null) {
                this.x.delete(0, this.x.length());
            }
            if (this.u) {
                j();
            }
            this.f5011c.b(this.u);
            this.f5016h = this.f5011c.e();
            this.f5012d.a(true, k());
            this.L = c(dmVar);
            if (!TextUtils.isEmpty(this.L)) {
                this.x = a(this.x);
            }
        } catch (Throwable th) {
            ej.a(th, "Aps", "initFirstLocateParam");
        }
        this.y = true;
    }

    public final void b(ds dsVar) {
        if (ep.a(dsVar)) {
            this.f5013e.a(this.L, this.x, dsVar, this.f5009a, true);
        }
    }

    public final void c() {
        if (this.p.length() > 0) {
            StringBuilder sb = this.p;
            sb.delete(0, sb.length());
        }
    }

    public final void d() {
        this.F = null;
        this.y = false;
        this.z = false;
        dz dzVar = this.f5013e;
        if (dzVar != null) {
            dzVar.b(this.f5009a);
        }
        dr drVar = this.E;
        if (drVar != null) {
            drVar.a();
        }
        if (this.f5015g != null) {
            this.f5015g = null;
        }
        try {
            if (this.f5009a != null && this.i != null) {
                this.f5009a.unregisterReceiver(this.i);
            }
        } finally {
            try {
            } finally {
            }
        }
        dw dwVar = this.f5012d;
        if (dwVar != null) {
            dwVar.h();
        }
        dx dxVar = this.f5011c;
        if (dxVar != null) {
            dxVar.n();
        }
        ArrayList<ScanResult> arrayList = this.f5016h;
        if (arrayList != null) {
            arrayList.clear();
        }
        dt dtVar = this.B;
        if (dtVar != null) {
            dtVar.e();
        }
        this.k = null;
        this.f5009a = null;
        this.x = null;
        this.H = null;
    }

    public final void e() {
        Cdo cdo = this.Q;
        if (cdo != null) {
            cdo.d();
        }
    }

    public final ds f() {
        int i;
        String string;
        if (this.f5011c.l()) {
            i = 15;
            string = "networkLocation has been mocked!#1502";
        } else {
            if (!TextUtils.isEmpty(this.L)) {
                ds dsVarA = this.f5013e.a(this.f5009a, this.L, this.x, true);
                if (ep.a(dsVarA)) {
                    c(dsVarA);
                }
                return dsVarA;
            }
            i = this.A;
            string = this.p.toString();
        }
        return a(i, string);
    }

    public final void g() {
        try {
            if (ei.f5153d && this.f5009a != null) {
                if (this.Q == null) {
                    this.Q = new Cdo(this.f5009a);
                }
                this.Q.a(this.f5012d, this.f5011c, this.N);
            }
        } catch (Throwable th) {
            ab.b(th, "as", "stc");
        }
    }

    public final void h() {
        Cdo cdo = this.Q;
        if (cdo != null) {
            cdo.a();
        }
    }
}