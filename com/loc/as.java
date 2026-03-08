package com.loc;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.ido.common.utils.FileDialDefinedUtil;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: HttpLimitUtil.java */
/* JADX INFO: loaded from: classes3.dex */
public final class as {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile ConcurrentHashMap<String, c> f4776a = new ConcurrentHashMap<>(8);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile List<String> f4777b = Collections.synchronizedList(new ArrayList(8));

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static volatile ConcurrentHashMap<String, b> f4778c = new ConcurrentHashMap<>(8);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Random f4779d = new Random();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static ConcurrentHashMap<String, String> f4780e = new ConcurrentHashMap<>(8);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static List<bb> f4781f = Collections.synchronizedList(new ArrayList(16));

    /* JADX INFO: compiled from: HttpLimitUtil.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        String f4782a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f4783b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        double f4784c;

        private a() {
        }

        /* synthetic */ a(byte b2) {
            this();
        }
    }

    /* JADX INFO: compiled from: HttpLimitUtil.java */
    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        aw f4785a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        long f4786b;

        private b() {
        }

        /* synthetic */ b(byte b2) {
            this();
        }
    }

    /* JADX INFO: compiled from: HttpLimitUtil.java */
    private static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        Map<String, List<a>> f4787a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Map<String, String> f4788b;

        private c() {
            this.f4787a = new HashMap(8);
            this.f4788b = new HashMap(8);
        }

        /* synthetic */ c(byte b2) {
            this();
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                c cVar = (c) obj;
                if (this.f4787a.equals(cVar.f4787a) && this.f4788b.equals(cVar.f4788b)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            Map<String, List<a>> map = this.f4787a;
            int iHashCode = map != null ? map.hashCode() : 0;
            Map<String, String> map2 = this.f4788b;
            return iHashCode + (map2 != null ? map2.hashCode() : 0);
        }
    }

    public static aw a(String str, String str2, String str3) {
        Uri uri;
        if (f4778c == null) {
            return null;
        }
        if (f4778c.containsKey(FileDialDefinedUtil.APP_FILE)) {
            b bVar = f4778c.get(FileDialDefinedUtil.APP_FILE);
            if (SystemClock.elapsedRealtime() <= bVar.f4786b) {
                aw awVar = bVar.f4785a;
                if (awVar != null) {
                    awVar.f4813e = false;
                }
                a(true, str3, str, 1);
                return awVar;
            }
            f4778c.remove(FileDialDefinedUtil.APP_FILE);
        } else {
            if (!TextUtils.isEmpty(str)) {
                str2 = str;
            }
            if (!TextUtils.isEmpty(str2) && (uri = Uri.parse(str2)) != null) {
                String path = uri.getPath();
                if (f4778c.containsKey(path)) {
                    b bVar2 = f4778c.get(path);
                    if (SystemClock.elapsedRealtime() <= bVar2.f4786b) {
                        aw awVar2 = bVar2.f4785a;
                        if (awVar2 != null) {
                            awVar2.f4813e = false;
                        }
                        a(true, str3, str, 2);
                        return awVar2;
                    }
                    f4778c.remove(path);
                }
            }
        }
        return null;
    }

    public static synchronized String a(String str, String str2) throws j {
        try {
            try {
                System.currentTimeMillis();
                if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str)) {
                    Context context = l.f5246c;
                    try {
                        if (f4777b == null) {
                            f4777b = Collections.synchronizedList(new ArrayList(8));
                        }
                        if (context != null && !f4777b.contains(str2)) {
                            f4777b.add(str2);
                            String strB = x.b(context, "Yb3Blbl9odHRwX2NvbnRyb2w", str2);
                            if (!TextUtils.isEmpty(strB)) {
                                a(str2, new JSONObject(strB));
                            }
                        }
                    } catch (Throwable th) {
                        y.a(th, "hlUtil", "llhl");
                    }
                    if (f4776a != null && f4776a.size() > 0) {
                        if (!f4776a.containsKey(str2)) {
                            return str;
                        }
                        c cVar = f4776a.get(str2);
                        if (cVar == null) {
                            return str;
                        }
                        if (a(str, cVar, str2)) {
                            throw new j("服务QPS超限");
                        }
                        return b(str, cVar, str2);
                    }
                    return str;
                }
                return str;
            } catch (j e2) {
                throw e2;
            } catch (Throwable th2) {
                y.a(th2, "hlUtil", "pcr");
                return str;
            }
        } finally {
        }
    }

    public static void a() {
        try {
            Context context = l.f5246c;
            if (context == null) {
                return;
            }
            bc.a(b(), context);
        } catch (Throwable unused) {
        }
    }

    private static void a(c cVar, JSONObject jSONObject) {
        JSONArray jSONArrayNames;
        try {
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("domainMap");
            if (jSONObjectOptJSONObject == null || (jSONArrayNames = jSONObjectOptJSONObject.names()) == null) {
                return;
            }
            HashMap map = new HashMap(8);
            int length = jSONArrayNames.length();
            for (int i = 0; i < length; i++) {
                String strOptString = jSONArrayNames.optString(i);
                map.put(strOptString, jSONObjectOptJSONObject.optString(strOptString));
            }
            cVar.f4788b = map;
        } catch (Throwable th) {
            y.a(th, "hlUtil", "pdr");
        }
    }

    public static synchronized void a(t tVar, JSONObject jSONObject) {
        if (tVar == null) {
            return;
        }
        try {
            String strA = tVar.a();
            if (TextUtils.isEmpty(strA)) {
                return;
            }
            if (jSONObject == null) {
                a(strA);
            }
            if (!l.a(jSONObject.optString("able", null), false)) {
                a(strA);
            } else {
                x.a(l.f5246c, "Yb3Blbl9odHRwX2NvbnRyb2w", strA, jSONObject.toString());
                a(strA, jSONObject);
            }
        } catch (Throwable th) {
            y.a(th, "hlUtil", "par");
        }
    }

    private static synchronized void a(String str) {
        try {
            if (f4776a.containsKey(str)) {
                f4776a.remove(str);
            }
            SharedPreferences.Editor editorB = x.b(l.f5246c, "Yb3Blbl9odHRwX2NvbnRyb2w");
            x.a(editorB, str);
            x.a(editorB);
        } catch (Throwable th) {
            y.a(th, "hlUtil", "rc");
        }
    }

    private static void a(String str, JSONObject jSONObject) {
        try {
            byte b2 = 0;
            c cVar = new c(b2);
            try {
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("block");
                if (jSONArrayOptJSONArray != null) {
                    HashMap map = new HashMap(8);
                    int i = 0;
                    while (i < jSONArrayOptJSONArray.length()) {
                        JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                        if (jSONObjectOptJSONObject != null) {
                            String strOptString = jSONObjectOptJSONObject.optString("api");
                            if (!TextUtils.isEmpty(strOptString)) {
                                if (!strOptString.startsWith("/")) {
                                    strOptString = "/" + strOptString;
                                }
                                if (strOptString.endsWith("/")) {
                                    strOptString = strOptString.substring(b2, strOptString.length() - 1);
                                }
                                JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject.optJSONArray("periods");
                                if (jSONArrayOptJSONArray != null) {
                                    ArrayList arrayList = new ArrayList();
                                    int i2 = b2;
                                    while (i2 < jSONArrayOptJSONArray2.length()) {
                                        JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray2.optJSONObject(i2);
                                        if (jSONObjectOptJSONObject2 != null) {
                                            a aVar = new a(b2);
                                            aVar.f4782a = jSONObjectOptJSONObject2.optString("begin");
                                            aVar.f4783b = jSONObjectOptJSONObject2.optInt(com.ido.ble.event.stat.one.d.C);
                                            aVar.f4784c = jSONObjectOptJSONObject2.optDouble("percent");
                                            arrayList.add(aVar);
                                        }
                                        i2++;
                                        b2 = 0;
                                    }
                                    map.put(strOptString, arrayList);
                                }
                            }
                        }
                        i++;
                        b2 = 0;
                    }
                    cVar.f4787a = map;
                }
            } catch (Throwable th) {
                y.a(th, "hlUtil", "pbr");
            }
            a(cVar, jSONObject);
            if (cVar.f4788b == null && cVar.f4787a == null) {
                a(str);
                return;
            }
            try {
                if (f4776a == null) {
                    f4776a = new ConcurrentHashMap<>(8);
                }
                f4776a.put(str, cVar);
            } catch (Throwable th2) {
                y.a(th2, "hlUtil", "ucr");
            }
        } catch (Throwable unused) {
        }
    }

    public static void a(URL url, aw awVar) {
        List<String> list;
        try {
            if (f4778c == null) {
                f4778c = new ConcurrentHashMap<>(8);
            }
            if (awVar.f4810b != null && awVar.f4810b.containsKey("nb") && (list = awVar.f4810b.get("nb")) != null && list.size() > 0) {
                byte b2 = 0;
                String[] strArrSplit = list.get(0).split("#");
                if (strArrSplit.length < 2) {
                    return;
                }
                int i = Integer.parseInt(strArrSplit[0]);
                long j = Integer.parseInt(strArrSplit[1]);
                b bVar = new b(b2);
                bVar.f4785a = awVar;
                if (j <= 0) {
                    j = 30;
                }
                bVar.f4786b = SystemClock.elapsedRealtime() + (j * 1000);
                if (i == 1) {
                    f4778c.put(FileDialDefinedUtil.APP_FILE, bVar);
                } else {
                    if (i != 2 || url == null) {
                        return;
                    }
                    f4778c.put(url.getPath(), bVar);
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static void a(boolean z, String str) {
        try {
            Context context = l.f5246c;
            if (context != null && !TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("timestamp", Long.valueOf(System.currentTimeMillis()));
                jSONObject.put("type", z ? v.f5334g : v.f5333f);
                jSONObject.put(AppMeasurementSdk.ConditionalUserProperty.NAME, str);
                jSONObject.put("version", v.a(str));
                String string = jSONObject.toString();
                bb bbVar = new bb(context, "core", "4.1.0", "O005");
                bbVar.a(string);
                bc.a(bbVar, context);
            }
        } catch (Throwable unused) {
        }
    }

    private static void a(boolean z, String str, String str2, int i) {
        String str3;
        Integer num;
        try {
            Context context = l.f5246c;
            if (context != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("timestamp", System.currentTimeMillis());
                String strA = v.a(str);
                if (z) {
                    str3 = "type";
                    num = v.i;
                } else {
                    str3 = "type";
                    num = v.f5335h;
                }
                jSONObject.put(str3, num);
                jSONObject.put(AppMeasurementSdk.ConditionalUserProperty.NAME, str);
                jSONObject.put("version", strA);
                jSONObject.put("uri", Uri.parse(str2).getPath());
                jSONObject.put("blockLevel", i);
                String string = jSONObject.toString();
                if (TextUtils.isEmpty(string)) {
                    return;
                }
                bb bbVar = new bb(context, "core", "4.1.0", "O005");
                bbVar.a(string);
                if (f4781f == null) {
                    f4781f = Collections.synchronizedList(new ArrayList(16));
                }
                synchronized (f4781f) {
                    f4781f.add(bbVar);
                    if (f4781f.size() >= 15) {
                        a();
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    private static boolean a(String str, c cVar, String str2) {
        Map<String, List<a>> map;
        try {
            map = cVar.f4787a;
        } catch (Throwable th) {
            y.a(th, "hlUtil", "inb");
        }
        if (map != null && map.size() > 0) {
            if (map.containsKey("*")) {
                Iterator<Map.Entry<String, List<a>>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    if (a(it.next().getValue())) {
                        a(false, str2, str, 1);
                        return true;
                    }
                }
            } else {
                String path = Uri.parse(str).getPath();
                if (map.containsKey(path) && a(map.get(path))) {
                    a(false, str2, str, 2);
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0084  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean a(java.util.List<com.loc.as.a> r9) {
        /*
            r0 = 0
            if (r9 == 0) goto L88
            int r1 = r9.size()
            if (r1 > 0) goto Lb
            goto L88
        Lb:
            java.util.Iterator r9 = r9.iterator()
        Lf:
            boolean r1 = r9.hasNext()
            if (r1 == 0) goto L88
            java.lang.Object r1 = r9.next()
            com.loc.as$a r1 = (com.loc.as.a) r1
            r2 = 1
            if (r1 == 0) goto L84
            double r3 = r1.f4784c
            r5 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 == 0) goto L84
            long r3 = java.lang.System.currentTimeMillis()
            java.lang.String r5 = r1.f4782a
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 != 0) goto L84
            int r5 = r1.f4783b
            if (r5 <= 0) goto L84
            java.lang.String r5 = r1.f4782a
            java.lang.String r6 = "HH:mm:ss"
            java.util.Calendar r5 = com.loc.u.a(r5, r6)
            long r5 = r5.getTimeInMillis()
            long r5 = r3 - r5
            r7 = 0
            int r7 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r7 <= 0) goto L84
            int r7 = r1.f4783b
            int r7 = r7 * 1000
            long r7 = (long) r7
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 >= 0) goto L84
            double r5 = r1.f4784c
            r7 = 0
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 != 0) goto L5d
        L5b:
            r1 = r2
            goto L85
        L5d:
            java.util.Random r5 = com.loc.as.f4779d
            if (r5 != 0) goto L68
            java.util.Random r5 = new java.util.Random
            r5.<init>()
            com.loc.as.f4779d = r5
        L68:
            java.util.Random r5 = com.loc.as.f4779d
            java.util.UUID r6 = java.util.UUID.randomUUID()
            int r6 = r6.hashCode()
            long r6 = (long) r6
            long r6 = r6 + r3
            r5.setSeed(r6)
            java.util.Random r3 = com.loc.as.f4779d
            double r3 = r3.nextDouble()
            double r5 = r1.f4784c
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 <= 0) goto L84
            goto L5b
        L84:
            r1 = r0
        L85:
            if (r1 == 0) goto Lf
            return r2
        L88:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.as.a(java.util.List):boolean");
    }

    private static String b(String str, c cVar, String str2) {
        Map<String, String> map;
        try {
            map = cVar.f4788b;
        } catch (Throwable th) {
            y.a(th, "hlUtil", "pdr");
        }
        if (map != null && map.size() > 0) {
            Uri uri = Uri.parse(str);
            String authority = uri.getAuthority();
            if (map.containsKey(authority)) {
                String str3 = map.get(authority);
                str = uri.buildUpon().authority(str3).toString();
                try {
                    Context context = l.f5246c;
                    if (context != null && !TextUtils.isEmpty(str2)) {
                        if (f4780e == null) {
                            f4780e = new ConcurrentHashMap<>(8);
                        }
                        synchronized (f4780e) {
                            if (!f4780e.containsKey(authority)) {
                                f4780e.put(authority, str3);
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("timestamp", System.currentTimeMillis());
                                jSONObject.put("type", v.j);
                                jSONObject.put(AppMeasurementSdk.ConditionalUserProperty.NAME, str2);
                                jSONObject.put("version", v.a(str2));
                                jSONObject.put("domain", authority + "#" + str3);
                                String string = jSONObject.toString();
                                if (!TextUtils.isEmpty(string)) {
                                    bb bbVar = new bb(context, "core", "4.1.0", "O005");
                                    bbVar.a(string);
                                    bc.a(bbVar, context);
                                }
                            }
                        }
                    }
                } catch (Throwable unused) {
                }
            }
            return str;
        }
        return str;
    }

    public static List<bb> b() {
        ArrayList arrayList = null;
        try {
        } catch (Throwable unused) {
        }
        synchronized (f4781f) {
            try {
                if (f4781f != null && f4781f.size() > 0) {
                    ArrayList arrayList2 = new ArrayList();
                    try {
                        arrayList2.addAll(f4781f);
                        f4781f.clear();
                        arrayList = arrayList2;
                    } catch (Throwable th) {
                        th = th;
                        arrayList = arrayList2;
                        throw th;
                    }
                }
                return arrayList;
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }
}