package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.amap.api.mapcore.util.ho;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: AMapLogManager.java */
/* JADX INFO: loaded from: classes.dex */
public class hf {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f1200a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private gs f1201b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f1202c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f1203d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f1204e = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f1205f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private List<String> f1206g = new ArrayList();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private hh f1207h = new hh();
    private hh i = new hh(10);
    private ho.a j = new ho.a() { // from class: com.amap.api.mapcore.util.hf.1
        @Override // com.amap.api.mapcore.util.ho.a
        public void a(int i) {
            if (i > 0 && hf.this.e() != null) {
                ((hg) hf.this.f().f1414f).a(i);
                long jB = ((hg) hf.this.f().f1414f).b();
                hf.this.a(AuthorizationResponseParser.ERROR, "" + jB);
                hf.this.e().postDelayed(new Runnable() { // from class: com.amap.api.mapcore.util.hf.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        hf.this.c(false);
                    }
                }, 660000L);
            }
        }
    };
    private ho.a k = new ho.a() { // from class: com.amap.api.mapcore.util.hf.2
        @Override // com.amap.api.mapcore.util.ho.a
        public void a(int i) {
            if (i <= 0) {
                return;
            }
            ((hg) hf.this.h().f1414f).a(i);
            long jB = ((hg) hf.this.h().f1414f).b();
            hf.this.a("info", "" + jB);
            if (hf.this.e() == null) {
                return;
            }
            hf.this.e().postDelayed(new Runnable() { // from class: com.amap.api.mapcore.util.hf.2.1
                @Override // java.lang.Runnable
                public void run() {
                    hf.this.d(false);
                }
            }, 660000L);
        }
    };
    private Handler l = null;
    private iv m = null;
    private iv n = null;

    /* JADX INFO: compiled from: AMapLogManager.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static Map<String, hf> f1212a = new HashMap();
    }

    public static hf a(gs gsVar) {
        if (gsVar == null || TextUtils.isEmpty(gsVar.a())) {
            return null;
        }
        if (a.f1212a.get(gsVar.a()) == null) {
            a.f1212a.put(gsVar.a(), new hf(gsVar));
        }
        return a.f1212a.get(gsVar.a());
    }

    public hf(gs gsVar) {
        this.f1201b = gsVar;
    }

    public void a(Context context) {
        this.f1200a = context.getApplicationContext();
    }

    public void a(boolean z, boolean z2, boolean z3, boolean z4, List<String> list) {
        this.f1202c = z;
        this.f1203d = z2;
        this.f1204e = z3;
        this.f1205f = z4;
        this.f1206g = list;
        g();
        i();
    }

    public void a(boolean z) {
        if (d()) {
            b(z);
        }
    }

    public void a(he heVar) {
        if (d() && this.f1202c && he.a(heVar) && !b(heVar)) {
            if (this.f1204e || heVar.a() != he.f1194a) {
                hh hhVarB = b(heVar.a());
                if (hhVarB.a(heVar.b())) {
                    String strA = he.a(this.f1200a, hhVarB.a());
                    if (this.f1200a == null || TextUtils.isEmpty(strA) || "[]".equals(strA)) {
                        return;
                    }
                    ho.a(this.f1200a, this.f1201b, heVar.d(), c(heVar.a()), strA);
                    b(false);
                    hhVarB.b();
                }
                hhVarB.a(heVar);
            }
        }
    }

    public void a() {
        if (d()) {
            a(he.f1195b);
            a(he.f1194a);
        }
    }

    private void a(int i) {
        Context context;
        hh hhVarB = b(i);
        String strA = he.a(this.f1200a, hhVarB.a());
        if (TextUtils.isEmpty(strA) || "[]".equals(strA) || (context = this.f1200a) == null) {
            return;
        }
        ho.a(context, this.f1201b, he.a(i), c(i), strA);
        hhVarB.b();
    }

    private boolean d() {
        return this.f1200a != null;
    }

    private boolean b(he heVar) {
        if (heVar == null) {
            return true;
        }
        List<String> list = this.f1206g;
        if (list != null && list.size() != 0) {
            for (int i = 0; i < this.f1206g.size(); i++) {
                if (!TextUtils.isEmpty(this.f1206g.get(i)) && heVar.b().contains(this.f1206g.get(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    private hh b(int i) {
        if (i == he.f1195b) {
            return this.i;
        }
        return this.f1207h;
    }

    private void b(boolean z) {
        c(z);
        d(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        iv ivVarC = c(he.f1195b);
        if (z) {
            ((hg) ivVarC.f1414f).a(z);
        }
        Context context = this.f1200a;
        if (context == null) {
            return;
        }
        ho.a(context, ivVarC, this.j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        iv ivVarC = c(he.f1194a);
        if (z) {
            ((hg) ivVarC.f1414f).a(z);
        }
        Context context = this.f1200a;
        if (context == null) {
            return;
        }
        ho.a(context, ivVarC, this.k);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Handler e() {
        Context context = this.f1200a;
        if (context == null || context == null) {
            return null;
        }
        if (this.l == null) {
            this.l = new Handler(context.getMainLooper());
        }
        return this.l;
    }

    private iv c(int i) {
        if (i == he.f1195b) {
            if (this.n == null) {
                this.n = f();
            }
            return this.n;
        }
        if (this.m == null) {
            this.m = h();
        }
        return this.m;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public iv f() {
        iv ivVar = this.n;
        if (ivVar != null) {
            return ivVar;
        }
        g();
        return this.n;
    }

    private iv g() {
        if (this.f1200a == null) {
            return null;
        }
        this.n = new iv();
        this.n.f1409a = c();
        iv ivVar = this.n;
        ivVar.f1410b = 512000000L;
        ivVar.f1412d = GoogleSignInStatusCodes.SIGN_IN_FAILED;
        ivVar.f1411c = "1";
        ivVar.f1416h = -1;
        ivVar.i = "elkey";
        long jA = a(AuthorizationResponseParser.ERROR);
        this.n.f1414f = new hg(true, 600000, new jq(this.f1200a, this.f1203d), jA, 10000000);
        iv ivVar2 = this.n;
        ivVar2.f1415g = null;
        return ivVar2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public iv h() {
        iv ivVar = this.m;
        if (ivVar != null) {
            return ivVar;
        }
        i();
        return this.m;
    }

    private long a(String str) {
        try {
            String str2 = new SimpleDateFormat("yyyyMMdd").format(new Date());
            return Long.parseLong(hi.a(this.f1201b).a(this.f1200a, "", "", str2 + str));
        } catch (Throwable unused) {
            return 0L;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
        try {
            String str3 = new SimpleDateFormat("yyyyMMdd").format(new Date());
            hi.a(this.f1201b).a(this.f1200a, "", "", str3 + str, str2);
        } catch (Throwable unused) {
        }
    }

    private iv i() {
        if (this.f1200a == null) {
            return null;
        }
        this.m = new iv();
        this.m.f1409a = b();
        iv ivVar = this.m;
        ivVar.f1410b = 512000000L;
        ivVar.f1412d = GoogleSignInStatusCodes.SIGN_IN_FAILED;
        ivVar.f1411c = "1";
        ivVar.f1416h = -1;
        ivVar.i = "inlkey";
        long jA = a("info");
        this.m.f1414f = new hg(this.f1205f, 600000, new jq(this.f1200a, this.f1203d), jA, 30000000);
        iv ivVar2 = this.m;
        ivVar2.f1415g = null;
        return ivVar2;
    }

    public String b() {
        Context context = this.f1200a;
        if (context == null) {
            return null;
        }
        return a(context, "CAF9B6B99962BF5C2264824231D7A40C", this.f1201b);
    }

    public String c() {
        Context context = this.f1200a;
        if (context == null) {
            return null;
        }
        return a(context, "CB5E100E5A9A3E7F6D1FD97512215282", this.f1201b);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String a(android.content.Context r3, java.lang.String r4, com.amap.api.mapcore.util.gs r5) {
        /*
            r2 = this;
            r0 = 0
            if (r3 != 0) goto L4
            return r0
        L4:
            if (r5 == 0) goto L19
            java.lang.String r1 = r5.a()     // Catch: java.lang.Throwable -> L4a
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> L4a
            if (r1 != 0) goto L19
            java.lang.String r5 = r5.a()     // Catch: java.lang.Throwable -> L4a
            java.lang.String r5 = com.amap.api.mapcore.util.gq.b(r5)     // Catch: java.lang.Throwable -> L4a
            goto L1b
        L19:
            java.lang.String r5 = "a"
        L1b:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4a
            r1.<init>()     // Catch: java.lang.Throwable -> L4a
            java.io.File r3 = r3.getFilesDir()     // Catch: java.lang.Throwable -> L4a
            java.lang.String r3 = r3.getAbsolutePath()     // Catch: java.lang.Throwable -> L4a
            r1.append(r3)     // Catch: java.lang.Throwable -> L4a
            java.lang.String r3 = java.io.File.separator     // Catch: java.lang.Throwable -> L4a
            r1.append(r3)     // Catch: java.lang.Throwable -> L4a
            java.lang.String r3 = "EBDEC84EF205FEA2DF0719DEB822869E"
            r1.append(r3)     // Catch: java.lang.Throwable -> L4a
            java.lang.String r3 = java.io.File.separator     // Catch: java.lang.Throwable -> L4a
            r1.append(r3)     // Catch: java.lang.Throwable -> L4a
            r1.append(r4)     // Catch: java.lang.Throwable -> L4a
            java.lang.String r3 = java.io.File.separator     // Catch: java.lang.Throwable -> L4a
            r1.append(r3)     // Catch: java.lang.Throwable -> L4a
            r1.append(r5)     // Catch: java.lang.Throwable -> L4a
            java.lang.String r3 = r1.toString()     // Catch: java.lang.Throwable -> L4a
            return r3
        L4a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.hf.a(android.content.Context, java.lang.String, com.amap.api.mapcore.util.gs):java.lang.String");
    }
}