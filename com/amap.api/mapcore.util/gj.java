package com.amap.api.mapcore.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.life.util.DateUtil;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.URLDecoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlinx.coroutines.DebugKt;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: AuthConfigManager.java */
/* JADX INFO: loaded from: classes.dex */
public class gj {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f1056a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f1057b = "";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Context f1058c = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static volatile boolean f1060e = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static Vector<e> f1061f = new Vector<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static Map<String, Integer> f1062g = new HashMap();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static String f1063h = null;
    private static long i = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static volatile boolean f1059d = false;
    private static volatile ConcurrentHashMap<String, Long> j = new ConcurrentHashMap<>(8);
    private static volatile ConcurrentHashMap<String, Long> k = new ConcurrentHashMap<>(8);
    private static volatile ConcurrentHashMap<String, d> l = new ConcurrentHashMap<>(8);

    /* JADX INFO: compiled from: AuthConfigManager.java */
    public interface a {
        void a(b bVar);
    }

    /* JADX INFO: compiled from: AuthConfigManager.java */
    public static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static boolean f1086a = true;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static boolean f1087b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static boolean f1088c = true;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static int f1089d = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static boolean f1090e = false;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static int f1091f;
    }

    public static void a(Context context, String str) {
        gi.a(context, str);
    }

    /* JADX INFO: compiled from: AuthConfigManager.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        @Deprecated
        public JSONObject f1065a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        @Deprecated
        public JSONObject f1066b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f1067c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1068d = -1;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f1069e = 0;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public JSONObject f1070f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public a f1071g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public C0010b f1072h;
        private boolean i;

        /* JADX INFO: compiled from: AuthConfigManager.java */
        public static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public boolean f1073a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public boolean f1074b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public JSONObject f1075c;
        }

        /* JADX INFO: renamed from: com.amap.api.mapcore.util.gj$b$b, reason: collision with other inner class name */
        /* JADX INFO: compiled from: AuthConfigManager.java */
        public static class C0010b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public boolean f1076a;
        }
    }

    public static boolean a(String str, boolean z) {
        try {
            if (TextUtils.isEmpty(str)) {
                return z;
            }
            String[] strArrSplit = URLDecoder.decode(str).split("/");
            return strArrSplit[strArrSplit.length - 1].charAt(4) % 2 == 1;
        } catch (Throwable unused) {
            return z;
        }
    }

    public static b a(Context context, gs gsVar, String str, Map<String, String> map) {
        return a(context, gsVar, str, map, false);
    }

    public static b a(Context context, gs gsVar, String str, Map<String, String> map, boolean z) {
        return a(context, gsVar, str, map, z, "DEF_ID", null, null, null);
    }

    public static void a(Context context) {
        if (context != null) {
            f1058c = context.getApplicationContext();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01ee A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01ef  */
    /* JADX WARN: Type inference failed for: r14v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v10, types: [com.amap.api.mapcore.util.gj$b] */
    /* JADX WARN: Type inference failed for: r14v16, types: [com.amap.api.mapcore.util.gj$b] */
    /* JADX WARN: Type inference failed for: r14v17 */
    /* JADX WARN: Type inference failed for: r14v18 */
    /* JADX WARN: Type inference failed for: r14v19 */
    /* JADX WARN: Type inference failed for: r14v2 */
    /* JADX WARN: Type inference failed for: r14v20 */
    /* JADX WARN: Type inference failed for: r14v3 */
    /* JADX WARN: Type inference failed for: r14v4 */
    /* JADX WARN: Type inference failed for: r14v5 */
    /* JADX WARN: Type inference failed for: r14v6 */
    /* JADX WARN: Type inference failed for: r14v7 */
    /* JADX WARN: Type inference failed for: r14v8 */
    /* JADX WARN: Type inference failed for: r14v9, types: [com.amap.api.mapcore.util.gj$b] */
    /* JADX WARN: Type inference failed for: r27v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r27v1 */
    /* JADX WARN: Type inference failed for: r27v10 */
    /* JADX WARN: Type inference failed for: r27v16 */
    /* JADX WARN: Type inference failed for: r27v17 */
    /* JADX WARN: Type inference failed for: r27v18 */
    /* JADX WARN: Type inference failed for: r27v19 */
    /* JADX WARN: Type inference failed for: r27v2 */
    /* JADX WARN: Type inference failed for: r27v20 */
    /* JADX WARN: Type inference failed for: r27v3 */
    /* JADX WARN: Type inference failed for: r27v4 */
    /* JADX WARN: Type inference failed for: r27v5 */
    /* JADX WARN: Type inference failed for: r27v6 */
    /* JADX WARN: Type inference failed for: r27v7 */
    /* JADX WARN: Type inference failed for: r27v8 */
    /* JADX WARN: Type inference failed for: r27v9 */
    /* JADX WARN: Type inference failed for: r2v8, types: [org.json.JSONObject] */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.lang.String] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.amap.api.mapcore.util.gj.b a(android.content.Context r23, com.amap.api.mapcore.util.gs r24, java.lang.String r25, java.util.Map<java.lang.String, java.lang.String> r26, boolean r27, java.lang.String r28, java.lang.String r29, java.lang.String r30, java.lang.String r31) {
        /*
            Method dump skipped, instruction units count: 697
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.gj.a(android.content.Context, com.amap.api.mapcore.util.gs, java.lang.String, java.util.Map, boolean, java.lang.String, java.lang.String, java.lang.String, java.lang.String):com.amap.api.mapcore.util.gj$b");
    }

    private static void a(Context context, gs gsVar, String str, b bVar, JSONObject jSONObject) throws JSONException {
        b.a aVar = new b.a();
        aVar.f1073a = false;
        aVar.f1074b = false;
        bVar.f1071g = aVar;
        try {
            String[] strArrSplit = str.split(";");
            if (strArrSplit != null && strArrSplit.length > 0) {
                for (String str2 : strArrSplit) {
                    if (jSONObject.has(str2)) {
                        bVar.f1070f.putOpt(str2, jSONObject.get(str2));
                    }
                }
            }
        } catch (Throwable th) {
            hk.a(th, "at", "co");
        }
        if (gt.a(jSONObject, "16H")) {
            try {
                bVar.i = a(jSONObject.getJSONObject("16H").optString("able"), false);
            } catch (Throwable th2) {
                hk.a(th2, "AuthConfigManager", "load 16H");
            }
        }
        if (gt.a(jSONObject, "11K")) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("11K");
                aVar.f1073a = a(jSONObject2.getString("able"), false);
                if (jSONObject2.has(DebugKt.DEBUG_PROPERTY_VALUE_OFF)) {
                    aVar.f1075c = jSONObject2.getJSONObject(DebugKt.DEBUG_PROPERTY_VALUE_OFF);
                }
            } catch (Throwable th3) {
                hk.a(th3, "AuthConfigManager", "load 11K");
            }
        }
        if (gt.a(jSONObject, "145")) {
            try {
                bVar.f1065a = jSONObject.getJSONObject("145");
            } catch (Throwable th4) {
                hk.a(th4, "AuthConfigManager", "load 145");
            }
        }
        if (gt.a(jSONObject, "14D")) {
            try {
                bVar.f1066b = jSONObject.getJSONObject("14D");
            } catch (Throwable th5) {
                hk.a(th5, "AuthConfigManager", "load 14D");
            }
        }
        if (gt.a(jSONObject, "151")) {
            try {
                JSONObject jSONObject3 = jSONObject.getJSONObject("151");
                b.C0010b c0010b = new b.C0010b();
                a(jSONObject3, c0010b);
                bVar.f1072h = c0010b;
            } catch (Throwable th6) {
                hk.a(th6, "AuthConfigManager", "load 151");
            }
        }
        if (gt.a(jSONObject, "17S")) {
            try {
                b(context, jSONObject.getJSONObject("17S"));
            } catch (Throwable th7) {
                hk.a(th7, "AuthConfigManager", "load 17S");
            }
        }
        if (gt.a(jSONObject, "15K")) {
            try {
                c(context, jSONObject.getJSONObject("15K"));
            } catch (Throwable th8) {
                hk.a(th8, "AuthConfigManager", "load 15K");
            }
        }
        if (gt.a(jSONObject, "183")) {
            try {
                im.a(gsVar, jSONObject.getJSONObject("183"));
            } catch (Throwable th9) {
                hk.a(th9, "AuthConfigManager", "load 183");
            }
        }
    }

    private static String a(byte[] bArr) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        byte[] bArr2 = new byte[16];
        byte[] bArr3 = new byte[bArr.length - 16];
        System.arraycopy(bArr, 0, bArr2, 0, 16);
        System.arraycopy(bArr, 16, bArr3, 0, bArr.length - 16);
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, gt.c("EQUVT"));
        Cipher cipher = Cipher.getInstance(gt.c("CQUVTL0NCQy9QS0NTNVBhZGRpbmc"));
        cipher.init(2, secretKeySpec, new IvParameterSpec(gt.c()));
        return gt.a(cipher.doFinal(bArr3));
    }

    private static void a(Context context, gs gsVar, Throwable th) {
        a(context, gsVar, th != null ? th.getMessage() : "on exception");
    }

    public static void a(String str, boolean z, boolean z2, boolean z3, long j2) {
        if (TextUtils.isEmpty(str) || f1058c == null) {
            return;
        }
        HashMap map = new HashMap();
        map.put("url", str);
        map.put("downLevel", String.valueOf(z2));
        int iR = gm.r(f1058c);
        String str2 = AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE;
        map.put("ant", iR == 0 ? AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE : "1");
        map.put("type", z ? "6" : AlexaCustomSkillConstant.EVENT_START_SPORT);
        if (!z3) {
            str2 = "1";
        }
        map.put(NotificationCompat.CATEGORY_STATUS, str2);
        map.put(com.ido.ble.event.stat.one.d.C, "" + j2);
        String string = new JSONObject(map).toString();
        if (TextUtils.isEmpty(string)) {
            return;
        }
        try {
            ja jaVar = new ja(f1058c, "core", "1.0", "O002");
            jaVar.a(string);
            jb.a(jaVar, f1058c);
        } catch (gh unused) {
        }
    }

    private static void a(Context context, gs gsVar, String str) {
        HashMap map = new HashMap();
        map.put("amap_sdk_auth_fail", "1");
        map.put("amap_sdk_auth_fail_type", str);
        map.put("amap_sdk_name", gsVar.a());
        map.put("amap_sdk_version", gsVar.c());
        String string = new JSONObject(map).toString();
        if (TextUtils.isEmpty(string)) {
            return;
        }
        try {
            ja jaVar = new ja(context, "core", "1.0", "O001");
            jaVar.a(string);
            jb.a(jaVar, context);
        } catch (gh unused) {
        }
    }

    private static void a(Context context, JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("15K");
            boolean zA = a(jSONObject2.optString("isTargetAble"), false);
            if (!a(jSONObject2.optString("able"), false)) {
                go.a().b(context);
            } else {
                go.a().a(context, zA);
            }
        } catch (Throwable unused) {
        }
    }

    private static void a(JSONObject jSONObject, b.C0010b c0010b) {
        if (c0010b == null || jSONObject == null) {
            return;
        }
        c0010b.f1076a = a(jSONObject.optString("able"), false);
    }

    private static void b(Context context, JSONObject jSONObject) {
        boolean zA;
        if (jSONObject == null || (zA = a(jSONObject.optString("able"), false)) == f1060e) {
            return;
        }
        f1060e = zA;
        a(context, zA);
    }

    private static void c(Context context, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        boolean zA = a(jSONObject.optString("ucf"), f.f1086a);
        boolean zA2 = a(jSONObject.optString("fsv2"), f.f1087b);
        boolean zA3 = a(jSONObject.optString("usc"), f.f1088c);
        int iOptInt = jSONObject.optInt("umv", f.f1089d);
        boolean zA4 = a(jSONObject.optString("ust"), f.f1090e);
        int iOptInt2 = jSONObject.optInt("ustv", f.f1091f);
        if (zA == f.f1086a && zA2 == f.f1087b && zA3 == f.f1088c && iOptInt == f.f1089d && zA4 == f.f1090e && iOptInt2 == f.f1089d) {
            return;
        }
        f.f1086a = zA;
        f.f1087b = zA2;
        f.f1088c = zA3;
        f.f1089d = iOptInt;
        f.f1090e = zA4;
        f.f1091f = iOptInt2;
        c(context);
    }

    /* JADX INFO: compiled from: AuthConfigManager.java */
    static class c extends ik {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private String f1077f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private Map<String, String> f1078g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private boolean f1079h;
        private String i;
        private String j;
        private String k;

        @Override // com.amap.api.mapcore.util.ik
        public byte[] e() {
            return null;
        }

        @Override // com.amap.api.mapcore.util.ik
        protected String g() {
            return "3.0";
        }

        c(Context context, gs gsVar, String str, Map<String, String> map, String str2, String str3, String str4) {
            super(context, gsVar);
            this.f1077f = str;
            this.f1078g = map;
            this.f1079h = Build.VERSION.SDK_INT != 19;
            this.i = str2;
            this.j = str3;
            this.k = str4;
        }

        public boolean a() {
            return this.f1079h;
        }

        @Override // com.amap.api.mapcore.util.iq
        public Map<String, String> getRequestHead() {
            if (TextUtils.isEmpty(this.k)) {
                return null;
            }
            HashMap map = new HashMap();
            map.put("host", this.k);
            return map;
        }

        @Override // com.amap.api.mapcore.util.iq
        public String getURL() {
            String str = this.f1079h ? "https://restsdk.amap.com/v3/iasdkauth" : "http://restsdk.amap.com/v3/iasdkauth";
            try {
                return !TextUtils.isEmpty(this.i) ? str.replace("restsdk.amap.com", this.i) : str;
            } catch (Throwable unused) {
                return str;
            }
        }

        @Override // com.amap.api.mapcore.util.gp, com.amap.api.mapcore.util.iq
        public String getIPV6URL() {
            try {
                String str = this.f1079h ? "https://restsdk.amap.com/v3/iasdkauth" : "http://restsdk.amap.com/v3/iasdkauth";
                try {
                    if (!TextUtils.isEmpty(this.j)) {
                        return str.replace("restsdk.amap.com", this.j);
                    }
                } catch (Throwable unused) {
                }
                Uri uri = Uri.parse(str);
                return uri.buildUpon().authority("dualstack-" + uri.getAuthority()).build().toString();
            } catch (Throwable unused2) {
                return null;
            }
        }

        @Override // com.amap.api.mapcore.util.ik
        public byte[] f() {
            return gt.a(gt.b(l()));
        }

        @Override // com.amap.api.mapcore.util.iq
        protected String getIPDNSName() {
            if (!TextUtils.isEmpty(this.k)) {
                return this.k;
            }
            return super.getIPDNSName();
        }

        private Map<String, String> l() {
            String strW = gm.w(this.f1356d);
            if (!TextUtils.isEmpty(strW)) {
                strW = gq.b(new StringBuilder(strW).reverse().toString());
            }
            HashMap map = new HashMap();
            map.put("authkey", TextUtils.isEmpty(this.f1077f) ? "" : this.f1077f);
            map.put("plattype", "android");
            map.put("product", this.f1357e.a());
            map.put("version", this.f1357e.b());
            map.put("output", "json");
            map.put("androidversion", Build.VERSION.SDK_INT + "");
            map.put("deviceId", strW);
            map.put("manufacture", Build.MANUFACTURER);
            Map<String, String> map2 = this.f1078g;
            if (map2 != null && !map2.isEmpty()) {
                map.putAll(this.f1078g);
            }
            map.put("abitype", gt.a(this.f1356d));
            map.put("ext", this.f1357e.e());
            return map;
        }
    }

    public static boolean a() {
        e eVarB;
        if (f1058c != null) {
            b();
            if (!d()) {
                return false;
            }
            if (c()) {
                return true;
            }
        }
        return f1060e && (eVarB = b(f1058c, "IPV6_CONFIG_NAME")) != null && eVarB.a() < 5;
    }

    private static boolean a(InetAddress inetAddress) {
        return inetAddress.isLoopbackAddress() || inetAddress.isLinkLocalAddress() || inetAddress.isAnyLocalAddress();
    }

    public static void b() {
        try {
            if (f1058c != null) {
                String strV = gm.v(f1058c);
                if (!TextUtils.isEmpty(f1063h) && !TextUtils.isEmpty(strV) && f1063h.equals(strV) && System.currentTimeMillis() - i < DateUtil.MINUTE) {
                    return;
                }
                if (!TextUtils.isEmpty(strV)) {
                    f1063h = strV;
                }
            } else if (System.currentTimeMillis() - i < DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT) {
                return;
            }
            i = System.currentTimeMillis();
            f1062g.clear();
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (!networkInterface.getInterfaceAddresses().isEmpty()) {
                    String displayName = networkInterface.getDisplayName();
                    int i2 = 0;
                    Iterator<InterfaceAddress> it = networkInterface.getInterfaceAddresses().iterator();
                    while (it.hasNext()) {
                        InetAddress address = it.next().getAddress();
                        if (address instanceof Inet6Address) {
                            if (!a((Inet6Address) address)) {
                                i2 |= 2;
                            }
                        } else if (address instanceof Inet4Address) {
                            Inet4Address inet4Address = (Inet4Address) address;
                            if (!a(inet4Address) && !inet4Address.getHostAddress().startsWith(gt.c("FMTkyLjE2OC40My4"))) {
                                i2 |= 1;
                            }
                        }
                    }
                    if (i2 != 0) {
                        if (displayName != null && displayName.startsWith("wlan")) {
                            f1062g.put("WIFI", Integer.valueOf(i2));
                        } else if (displayName != null && displayName.startsWith("rmnet")) {
                            f1062g.put("MOBILE", Integer.valueOf(i2));
                        }
                    }
                }
            }
        } catch (Throwable th) {
            hk.a(th, "at", "ipstack");
        }
    }

    public static boolean c() {
        Integer num;
        Context context = f1058c;
        if (context == null) {
            return false;
        }
        String strV = gm.v(context);
        return (TextUtils.isEmpty(strV) || (num = f1062g.get(strV.toUpperCase())) == null || num.intValue() != 2) ? false : true;
    }

    public static boolean d() {
        Integer num;
        Context context = f1058c;
        if (context == null) {
            return false;
        }
        String strV = gm.v(context);
        return (TextUtils.isEmpty(strV) || (num = f1062g.get(strV.toUpperCase())) == null || num.intValue() < 2) ? false : true;
    }

    public static void a(int i2) {
        if (i2 != 2) {
            return;
        }
        try {
            g();
        } catch (Throwable unused) {
        }
    }

    private static void g() {
        e eVarB = b(f1058c, "IPV6_CONFIG_NAME");
        String strA = gt.a(System.currentTimeMillis(), "yyyyMMdd");
        if (!strA.equals(eVarB.f1084b)) {
            eVarB.a(strA);
            eVarB.f1085c.set(0);
        }
        eVarB.f1085c.incrementAndGet();
        a(f1058c, eVarB);
    }

    public static void b(Context context) {
        if (context == null) {
            return;
        }
        f1060e = hp.a(context, "open_common", "a2", true);
    }

    private static void a(Context context, boolean z) {
        if (context == null) {
            return;
        }
        SharedPreferences.Editor editorB = hp.b(context, "open_common");
        hp.a(editorB, "a2", z);
        hp.a(editorB);
    }

    private static void a(Context context, e eVar) {
        if (eVar == null || TextUtils.isEmpty(eVar.f1083a)) {
            return;
        }
        String strB = eVar.b();
        if (TextUtils.isEmpty(strB) || context == null) {
            return;
        }
        new hp("IPV6_CONFIG_NAME").a(context, "i", strB);
    }

    private static e d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (int i2 = 0; i2 < f1061f.size(); i2++) {
            e eVar = f1061f.get(i2);
            if (eVar != null && str.equals(eVar.f1083a)) {
                return eVar;
            }
        }
        return null;
    }

    private static synchronized e b(Context context, String str) {
        e eVarD = d(str);
        if (eVarD != null) {
            return eVarD;
        }
        if (context == null) {
            return null;
        }
        e eVarB = e.b(new hp(str).a(context, "i"));
        String strA = gt.a(System.currentTimeMillis(), "yyyyMMdd");
        if (eVarB == null) {
            eVarB = new e("IPV6_CONFIG_NAME", strA, 0);
        }
        if (!strA.equals(eVarB.f1084b)) {
            eVarB.a(strA);
            eVarB.f1085c.set(0);
        }
        f1061f.add(eVarB);
        return eVarB;
    }

    /* JADX INFO: compiled from: AuthConfigManager.java */
    private static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f1083a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f1084b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private AtomicInteger f1085c;

        public e(String str, String str2, int i) {
            this.f1083a = str;
            this.f1084b = str2;
            this.f1085c = new AtomicInteger(i);
        }

        public void a(String str) {
            this.f1084b = str;
        }

        public int a() {
            AtomicInteger atomicInteger = this.f1085c;
            if (atomicInteger == null) {
                return 0;
            }
            return atomicInteger.get();
        }

        public String b() {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("a", this.f1083a);
                jSONObject.put("f", this.f1084b);
                jSONObject.put("h", this.f1085c.get());
                return jSONObject.toString();
            } catch (Throwable unused) {
                return "";
            }
        }

        public static e b(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                return new e(jSONObject.optString("a"), jSONObject.optString("f"), jSONObject.optInt("h"));
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    private static void c(Context context) {
        try {
            SharedPreferences.Editor editorB = hp.b(context, "open_common");
            hp.a(editorB, "ucf", f.f1086a);
            hp.a(editorB, "fsv2", f.f1087b);
            hp.a(editorB, "usc", f.f1088c);
            hp.a(editorB, "umv", f.f1089d);
            hp.a(editorB, "ust", f.f1090e);
            hp.a(editorB, "ustv", f.f1091f);
            hp.a(editorB);
        } catch (Throwable unused) {
        }
    }

    public static void e() {
        if (f1059d) {
            return;
        }
        try {
            f1059d = true;
            Context context = f1058c;
            if (context == null) {
                return;
            }
            go.a().a(f1058c);
            b(f1058c);
            f.f1086a = hp.a(context, "open_common", "ucf", f.f1086a);
            f.f1087b = hp.a(context, "open_common", "fsv2", f.f1087b);
            f.f1088c = hp.a(context, "open_common", "usc", f.f1088c);
            f.f1089d = hp.a(context, "open_common", "umv", f.f1089d);
            f.f1090e = hp.a(context, "open_common", "ust", f.f1090e);
            f.f1091f = hp.a(context, "open_common", "ustv", f.f1091f);
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: compiled from: AuthConfigManager.java */
    private static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        gs f1080a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f1081b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        a f1082c;

        private d() {
        }
    }

    public static synchronized boolean a(String str, long j2) {
        boolean z = false;
        try {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            if (j2 > c(str)) {
                long jLongValue = 0;
                if (k != null && k.containsKey(str)) {
                    jLongValue = k.get(str).longValue();
                }
                if (SystemClock.elapsedRealtime() - jLongValue > 30000) {
                    z = true;
                }
            }
        } catch (Throwable unused) {
        }
        return z;
    }

    public static synchronized void b(final String str, boolean z) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            if (k == null) {
                k = new ConcurrentHashMap<>(8);
            }
            k.put(str, Long.valueOf(SystemClock.elapsedRealtime()));
            if (l == null) {
                return;
            }
            if (!l.containsKey(str)) {
                return;
            }
            if (TextUtils.isEmpty(str)) {
                return;
            }
            if (z) {
                im.a(true, str);
            }
            hn.d().submit(new Runnable() { // from class: com.amap.api.mapcore.util.gj.1
                @Override // java.lang.Runnable
                public void run() {
                    d dVar = (d) gj.l.get(str);
                    if (dVar == null) {
                        return;
                    }
                    a aVar = dVar.f1082c;
                    b bVarA = gj.a(gj.f1058c, dVar.f1080a, dVar.f1081b, null);
                    if (bVarA == null || aVar == null) {
                        return;
                    }
                    aVar.a(bVarA);
                }
            });
        } finally {
        }
    }

    public static synchronized boolean a(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            if (!str.equalsIgnoreCase("loc") && !str.equalsIgnoreCase("locf")) {
                if (l == null) {
                    return false;
                }
                if (k == null) {
                    k = new ConcurrentHashMap<>(8);
                }
                if (l.containsKey(str) && !k.containsKey(str)) {
                    k.put(str, Long.valueOf(SystemClock.elapsedRealtime()));
                    return true;
                }
            }
            return false;
        } finally {
        }
        return false;
    }

    public static synchronized void b(String str) {
        if (k == null) {
            return;
        }
        if (k.containsKey(str)) {
            k.remove(str);
        }
    }

    public static synchronized long c(String str) {
        try {
            if (j == null) {
                j = new ConcurrentHashMap<>(8);
            }
            if (j.containsKey(str)) {
                return j.get(str).longValue();
            }
        } finally {
        }
        return 0L;
    }

    private static synchronized void b(String str, long j2) {
        try {
        } finally {
        }
        if (l != null && l.containsKey(str)) {
            if (j == null) {
                j = new ConcurrentHashMap<>(8);
            }
            j.put(str, Long.valueOf(j2));
            if (f1058c != null) {
                SharedPreferences.Editor editorB = hp.b(f1058c, "open_common");
                hp.a(editorB, str, j2);
                hp.a(editorB);
            }
        }
    }

    private static void a(gs gsVar) {
        if (gsVar != null) {
            try {
                if (TextUtils.isEmpty(gsVar.a())) {
                    return;
                }
                String strC = gsVar.c();
                if (TextUtils.isEmpty(strC)) {
                    strC = gsVar.b();
                }
                if (TextUtils.isEmpty(strC)) {
                    return;
                }
                hj.a(gsVar.a(), strC);
            } catch (Throwable unused) {
            }
        }
    }
}