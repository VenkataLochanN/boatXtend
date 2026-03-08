package com.baidu.location.d;

import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.baidu.location.Jni;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.life.constants.Constants;
import com.ido.life.util.DateUtil;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Locale;
import kotlinx.coroutines.DebugKt;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final h f2312a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final SQLiteDatabase f2313b;
    private boolean u = true;
    private long v = 8000;
    private long w = BootloaderScanner.TIMEOUT;
    private long x = BootloaderScanner.TIMEOUT;
    private long y = BootloaderScanner.TIMEOUT;
    private long z = BootloaderScanner.TIMEOUT;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f2315d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f2316e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f2317f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f2318g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f2319h = false;
    private boolean j = false;
    private boolean k = false;
    private int l = 6;
    private int m = 30;
    private int n = 30;
    private double o = 0.0d;
    private double p = 0.0d;
    private double q = 0.0d;
    private double r = 0.0d;
    private double s = 0.0d;
    private int t = 8;
    private String[] i = new String[0];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final a f2314c = new a();

    /* JADX INFO: Access modifiers changed from: private */
    final class a extends com.baidu.location.g.e {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f2321b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private long f2322c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private long f2323d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f2324e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final String f2325f;

        private a() {
            this.f2321b = 0;
            this.f2324e = false;
            this.f2322c = -1L;
            this.f2323d = -1L;
            this.k = new HashMap();
            this.f2325f = Jni.encodeOfflineLocationUpdateRequest(String.format(Locale.US, "&ver=%s&cuid=%s&prod=%s:%s&sdk=%.2f&mb=%s&os=A%s", "1", com.baidu.location.g.b.a().f2466c, com.baidu.location.g.b.f2461f, com.baidu.location.g.b.f2460e, Float.valueOf(7.72f), Build.MODEL, Build.VERSION.SDK));
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:75:0x01ce  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void b() {
            /*
                Method dump skipped, instruction units count: 524
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.d.f.a.b():void");
        }

        private boolean c() {
            if (this.f2321b < 2) {
                return true;
            }
            if (this.f2322c + DateUtil.DAY >= System.currentTimeMillis()) {
                return false;
            }
            this.f2321b = 0;
            this.f2322c = -1L;
            return true;
        }

        @Override // com.baidu.location.g.e
        public void a() {
            this.k.clear();
            this.k.put("qt", "conf");
            this.k.put("req", this.f2325f);
            this.f2489h = h.f2328b;
        }

        @Override // com.baidu.location.g.e
        public void a(boolean z) {
            int i;
            int i2;
            String str;
            String str2;
            String str3;
            if (z && this.j != null) {
                try {
                    JSONObject jSONObject = new JSONObject(this.j);
                    long j = jSONObject.has("ofl") ? jSONObject.getLong("ofl") : 0L;
                    String string = jSONObject.has("ver") ? jSONObject.getString("ver") : "1";
                    if ((1 & j) == 1) {
                        f.this.f2315d = true;
                    }
                    if ((2 & j) == 2) {
                        f.this.f2316e = true;
                    }
                    if ((4 & j) == 4) {
                        f.this.f2317f = true;
                    }
                    if ((8 & j) == 8) {
                        f.this.f2318g = true;
                    }
                    if ((16 & j) == 16) {
                        f.this.f2319h = true;
                    }
                    if ((32 & j) == 32) {
                        f.this.j = true;
                    }
                    if ((j & 64) == 64) {
                        f.this.k = true;
                    }
                    JSONObject jSONObject2 = new JSONObject();
                    if (jSONObject.has("cplist")) {
                        f.this.i = jSONObject.getString("cplist").split(";");
                        jSONObject2.put("cplist", jSONObject.getString("cplist"));
                    }
                    if (jSONObject.has("bklist")) {
                        f.this.a(jSONObject.getString("bklist").split(";"));
                    }
                    String str4 = string;
                    if (jSONObject.has("para")) {
                        JSONObject jSONObject3 = jSONObject.getJSONObject("para");
                        if (jSONObject3.has("rgcgp")) {
                            str = "ver";
                            f.this.l = jSONObject3.getInt("rgcgp");
                        } else {
                            str = "ver";
                        }
                        if (jSONObject3.has("addrup")) {
                            f.this.n = jSONObject3.getInt("addrup");
                        }
                        if (jSONObject3.has("poiup")) {
                            f.this.m = jSONObject3.getInt("poiup");
                        }
                        if (jSONObject3.has("oflp")) {
                            JSONObject jSONObject4 = jSONObject3.getJSONObject("oflp");
                            if (jSONObject4.has(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE)) {
                                str2 = "poiup";
                                str3 = "addrup";
                                f.this.o = jSONObject4.getDouble(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
                            } else {
                                str2 = "poiup";
                                str3 = "addrup";
                            }
                            if (jSONObject4.has("1")) {
                                f.this.p = jSONObject4.getDouble("1");
                            }
                            if (jSONObject4.has("2")) {
                                f.this.q = jSONObject4.getDouble("2");
                            }
                            if (jSONObject4.has(Constants.DIALDEFNED_VERSION_CONNECT)) {
                                f.this.r = jSONObject4.getDouble(Constants.DIALDEFNED_VERSION_CONNECT);
                            }
                            if (jSONObject4.has(AlexaCustomSkillConstant.EVENT_START_SPORT)) {
                                f.this.s = jSONObject4.getDouble(AlexaCustomSkillConstant.EVENT_START_SPORT);
                            }
                        } else {
                            str2 = "poiup";
                            str3 = "addrup";
                        }
                        if (jSONObject3.has("onlt")) {
                            JSONObject jSONObject5 = jSONObject3.getJSONObject("onlt");
                            if (jSONObject5.has(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE)) {
                                f.this.z = jSONObject5.getLong(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
                            }
                            if (jSONObject5.has("1")) {
                                f.this.y = jSONObject5.getLong("1");
                            }
                            if (jSONObject5.has("2")) {
                                f.this.v = jSONObject5.getLong("2");
                            }
                            if (jSONObject5.has(Constants.DIALDEFNED_VERSION_CONNECT)) {
                                f.this.w = jSONObject5.getLong(Constants.DIALDEFNED_VERSION_CONNECT);
                            }
                            if (jSONObject5.has(AlexaCustomSkillConstant.EVENT_START_SPORT)) {
                                f.this.x = jSONObject5.getLong(AlexaCustomSkillConstant.EVENT_START_SPORT);
                            }
                        }
                        if (jSONObject3.has("minapn")) {
                            f.this.t = jSONObject3.getInt("minapn");
                        }
                    } else {
                        str = "ver";
                        str2 = "poiup";
                        str3 = "addrup";
                    }
                    jSONObject2.put("ol", f.this.f2315d);
                    jSONObject2.put("olv2", f.this.k);
                    jSONObject2.put("fl", f.this.f2316e);
                    jSONObject2.put(DebugKt.DEBUG_PROPERTY_VALUE_ON, f.this.f2317f);
                    jSONObject2.put("wn", f.this.f2318g);
                    jSONObject2.put("oc", f.this.f2319h);
                    this.f2323d = System.currentTimeMillis();
                    jSONObject2.put("t", this.f2323d);
                    jSONObject2.put(str, str4);
                    jSONObject2.put("rgcon", f.this.j);
                    jSONObject2.put("rgcgp", f.this.l);
                    JSONObject jSONObject6 = new JSONObject();
                    jSONObject6.put(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE, f.this.o);
                    jSONObject6.put("1", f.this.p);
                    jSONObject6.put("2", f.this.q);
                    jSONObject6.put(Constants.DIALDEFNED_VERSION_CONNECT, f.this.r);
                    jSONObject6.put(AlexaCustomSkillConstant.EVENT_START_SPORT, f.this.s);
                    jSONObject2.put("oflp", jSONObject6);
                    JSONObject jSONObject7 = new JSONObject();
                    jSONObject7.put(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE, f.this.z);
                    jSONObject7.put("1", f.this.y);
                    jSONObject7.put("2", f.this.v);
                    jSONObject7.put(Constants.DIALDEFNED_VERSION_CONNECT, f.this.w);
                    jSONObject7.put(AlexaCustomSkillConstant.EVENT_START_SPORT, f.this.x);
                    jSONObject2.put("onlt", jSONObject7);
                    jSONObject2.put(str3, f.this.n);
                    jSONObject2.put(str2, f.this.m);
                    jSONObject2.put("minapn", f.this.t);
                    File file = new File(f.this.f2312a.c(), "ofl.config");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write(jSONObject2.toString());
                    fileWriter.close();
                } catch (Exception unused) {
                    i2 = this.f2321b;
                    i = 1;
                    this.f2321b = i2 + i;
                    this.f2322c = System.currentTimeMillis();
                }
                this.f2324e = false;
            }
            i = 1;
            i2 = this.f2321b;
            this.f2321b = i2 + i;
            this.f2322c = System.currentTimeMillis();
            this.f2324e = false;
        }
    }

    f(h hVar, SQLiteDatabase sQLiteDatabase) {
        this.f2312a = hVar;
        this.f2313b = sQLiteDatabase;
        SQLiteDatabase sQLiteDatabase2 = this.f2313b;
        if (sQLiteDatabase2 != null && sQLiteDatabase2.isOpen()) {
            try {
                this.f2313b.execSQL("CREATE TABLE IF NOT EXISTS BLACK (name VARCHAR(100) PRIMARY KEY);");
            } catch (Exception unused) {
            }
        }
        g();
    }

    int a() {
        return this.t;
    }

    long a(String str) {
        return str.equals("2G") ? this.v : str.equals("3G") ? this.w : str.equals("4G") ? this.x : str.equals("WIFI") ? this.y : str.equals("unknown") ? this.z : BootloaderScanner.TIMEOUT;
    }

    void a(String[] strArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < strArr.length; i++) {
            if (i > 0) {
                stringBuffer.append(AppInfo.DELIM);
            }
            stringBuffer.append("(\"");
            stringBuffer.append(strArr[i]);
            stringBuffer.append("\")");
        }
        SQLiteDatabase sQLiteDatabase = this.f2313b;
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen() || stringBuffer.length() <= 0) {
            return;
        }
        try {
            this.f2313b.execSQL(String.format(Locale.US, "INSERT OR IGNORE INTO BLACK VALUES %s;", stringBuffer.toString()));
        } catch (Exception unused) {
        }
    }

    double b() {
        return this.o;
    }

    double c() {
        return this.p;
    }

    double d() {
        return this.q;
    }

    double e() {
        return this.r;
    }

    double f() {
        return this.s;
    }

    void g() {
        this.f2314c.b();
    }

    boolean h() {
        return this.f2315d;
    }

    boolean i() {
        return this.f2317f;
    }

    boolean j() {
        return this.f2318g;
    }

    boolean k() {
        return this.f2316e;
    }

    boolean l() {
        return this.j;
    }

    boolean m() {
        return this.u;
    }

    int n() {
        return this.l;
    }

    String[] o() {
        return this.i;
    }

    int p() {
        return this.n;
    }

    int q() {
        return this.m;
    }
}