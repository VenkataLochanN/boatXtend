package com.amap.api.mapcore.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
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
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: HttpLimitUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class im {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile ConcurrentHashMap<String, b> f1360a = new ConcurrentHashMap<>(8);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile List<String> f1361b = Collections.synchronizedList(new ArrayList(8));

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static volatile ConcurrentHashMap<String, c> f1362c = new ConcurrentHashMap<>(8);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Random f1363d = new Random();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static ConcurrentHashMap<String, String> f1364e = new ConcurrentHashMap<>(8);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static List<ja> f1365f = Collections.synchronizedList(new ArrayList(16));

    private static void a(Context context, String str) {
        b bVar;
        try {
            if (f1361b == null) {
                f1361b = Collections.synchronizedList(new ArrayList(8));
            }
            if (context == null || f1361b.contains(str)) {
                return;
            }
            f1361b.add(str);
            String strA = hp.a(context, "Yb3Blbl9odHRwX2NvbnRyb2w", str, (String) null);
            if (TextUtils.isEmpty(strA) || (bVar = (b) gt.a(strA, b.f1370c)) == null) {
                return;
            }
            a(str, bVar);
        } catch (Throwable th) {
            hk.a(th, "hlUtil", "llhl");
        }
    }

    public static synchronized void a(gs gsVar, JSONObject jSONObject) {
        String strA;
        if (gsVar == null) {
            return;
        }
        try {
            strA = gsVar.a();
        } catch (Throwable th) {
            hk.a(th, "hlUtil", "par");
        }
        if (TextUtils.isEmpty(strA)) {
            return;
        }
        if (jSONObject == null) {
            a(strA);
        }
        if (!gj.a(jSONObject.optString("able", null), false)) {
            a(strA);
            return;
        }
        b bVar = new b();
        a(bVar, jSONObject);
        b(bVar, jSONObject);
        if (bVar.f1372b == null && bVar.f1371a == null) {
            a(strA);
        } else {
            a(strA, bVar);
            b(strA, bVar);
        }
    }

    public static synchronized String a(String str, String str2) throws gh {
        try {
            try {
                System.currentTimeMillis();
                if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str)) {
                    a(gj.f1058c, str2);
                    if (f1360a != null && f1360a.size() > 0) {
                        if (!f1360a.containsKey(str2)) {
                            return str;
                        }
                        b bVar = f1360a.get(str2);
                        if (bVar == null) {
                            return str;
                        }
                        if (a(str, bVar, str2)) {
                            throw new gh("服务QPS超限");
                        }
                        return b(str, bVar, str2);
                    }
                    return str;
                }
                return str;
            } catch (gh e2) {
                throw e2;
            } catch (Throwable th) {
                hk.a(th, "hlUtil", "pcr");
                return str;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    /* JADX INFO: compiled from: HttpLimitUtil.java */
    private static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        is f1373a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        long f1374b;

        private c() {
        }
    }

    public static void a(URL url, is isVar) {
        List<String> list;
        if (isVar == null) {
            return;
        }
        try {
            if (f1362c == null) {
                f1362c = new ConcurrentHashMap<>(8);
            }
            if (isVar.f1403b != null && isVar.f1403b.containsKey("nb") && (list = isVar.f1403b.get("nb")) != null && list.size() > 0) {
                String[] strArrSplit = list.get(0).split("#");
                if (strArrSplit.length < 2) {
                    return;
                }
                int i = Integer.parseInt(strArrSplit[0]);
                long j = Integer.parseInt(strArrSplit[1]);
                c cVar = new c();
                cVar.f1373a = isVar;
                if (j <= 0) {
                    j = 30;
                }
                cVar.f1374b = SystemClock.elapsedRealtime() + (j * 1000);
                if (i == 1) {
                    f1362c.put(FileDialDefinedUtil.APP_FILE, cVar);
                } else {
                    if (i != 2 || url == null) {
                        return;
                    }
                    f1362c.put(url.getPath(), cVar);
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static is a(String str, String str2, String str3) {
        Uri uri;
        if (f1362c == null) {
            return null;
        }
        if (f1362c.containsKey(FileDialDefinedUtil.APP_FILE)) {
            c cVar = f1362c.get(FileDialDefinedUtil.APP_FILE);
            if (SystemClock.elapsedRealtime() <= cVar.f1374b) {
                is isVar = cVar.f1373a;
                if (isVar != null) {
                    isVar.f1406e = false;
                }
                a(true, str3, str, 1);
                return isVar;
            }
            f1362c.remove(FileDialDefinedUtil.APP_FILE);
        } else {
            if (!TextUtils.isEmpty(str)) {
                str2 = str;
            }
            if (!TextUtils.isEmpty(str2) && (uri = Uri.parse(str2)) != null) {
                String path = uri.getPath();
                if (f1362c.containsKey(path)) {
                    c cVar2 = f1362c.get(path);
                    if (SystemClock.elapsedRealtime() <= cVar2.f1374b) {
                        is isVar2 = cVar2.f1373a;
                        if (isVar2 != null) {
                            isVar2.f1406e = false;
                        }
                        a(true, str3, str, 2);
                        return isVar2;
                    }
                    f1362c.remove(path);
                }
            }
        }
        return null;
    }

    private static void a(String str, b bVar) {
        try {
            if (f1360a == null) {
                f1360a = new ConcurrentHashMap<>(8);
            }
            f1360a.put(str, bVar);
        } catch (Throwable th) {
            hk.a(th, "hlUtil", "ucr");
        }
    }

    private static void b(String str, b bVar) {
        try {
            String strA = gt.a(bVar);
            SharedPreferences.Editor editorB = hp.b(gj.f1058c, "Yb3Blbl9odHRwX2NvbnRyb2w");
            hp.a(editorB, str, strA);
            hp.a(editorB);
        } catch (Throwable th) {
            hk.a(th, "hlUtil", "ulr");
        }
    }

    private static void a(b bVar, JSONObject jSONObject) {
        try {
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("block");
            if (jSONArrayOptJSONArray == null) {
                return;
            }
            HashMap map = new HashMap(8);
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                if (jSONObjectOptJSONObject != null) {
                    String strOptString = jSONObjectOptJSONObject.optString("api");
                    if (!TextUtils.isEmpty(strOptString)) {
                        if (!strOptString.startsWith("/")) {
                            strOptString = "/" + strOptString;
                        }
                        if (strOptString.endsWith("/")) {
                            strOptString = strOptString.substring(0, strOptString.length() - 1);
                        }
                        JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject.optJSONArray("periods");
                        if (jSONArrayOptJSONArray != null) {
                            ArrayList arrayList = new ArrayList();
                            for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                                JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray2.optJSONObject(i2);
                                if (jSONObjectOptJSONObject2 != null) {
                                    a aVar = new a();
                                    aVar.f1367a = jSONObjectOptJSONObject2.optString("begin");
                                    aVar.f1368b = jSONObjectOptJSONObject2.optInt(com.ido.ble.event.stat.one.d.C);
                                    aVar.f1369c = jSONObjectOptJSONObject2.optDouble("percent");
                                    arrayList.add(aVar);
                                }
                            }
                            map.put(strOptString, arrayList);
                        }
                    }
                }
            }
            bVar.f1371a = map;
        } catch (Throwable th) {
            hk.a(th, "hlUtil", "pbr");
        }
    }

    private static void b(b bVar, JSONObject jSONObject) {
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
            bVar.f1372b = map;
        } catch (Throwable th) {
            hk.a(th, "hlUtil", "pdr");
        }
    }

    private static synchronized void a(String str) {
        try {
            if (f1360a.containsKey(str)) {
                f1360a.remove(str);
            }
            SharedPreferences.Editor editorB = hp.b(gj.f1058c, "Yb3Blbl9odHRwX2NvbnRyb2w");
            hp.a(editorB, str);
            hp.a(editorB);
        } catch (Throwable th) {
            hk.a(th, "hlUtil", "rc");
        }
    }

    /* JADX INFO: compiled from: HttpLimitUtil.java */
    private static class b implements Parcelable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final Parcelable.Creator<b> f1370c = new Parcelable.Creator<b>() { // from class: com.amap.api.mapcore.util.im.b.1
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public b createFromParcel(Parcel parcel) {
                return new b(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public b[] newArray(int i) {
                return new b[i];
            }
        };

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        Map<String, List<a>> f1371a = new HashMap(8);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Map<String, String> f1372b = new HashMap(8);

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            return this.f1371a.equals(bVar.f1371a) && this.f1372b.equals(bVar.f1372b);
        }

        public int hashCode() {
            Map<String, List<a>> map = this.f1371a;
            int iHashCode = map != null ? map.hashCode() : 0;
            Map<String, String> map2 = this.f1372b;
            return iHashCode + (map2 != null ? map2.hashCode() : 0);
        }

        public b() {
        }

        protected b(Parcel parcel) {
            parcel.readMap(this.f1371a, a.class.getClassLoader());
            parcel.readMap(this.f1372b, HashMap.class.getClassLoader());
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeMap(this.f1371a);
            parcel.writeMap(this.f1372b);
        }
    }

    /* JADX INFO: compiled from: HttpLimitUtil.java */
    private static class a implements Parcelable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final Parcelable.Creator<a> f1366d = new Parcelable.Creator<a>() { // from class: com.amap.api.mapcore.util.im.a.1
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public a createFromParcel(Parcel parcel) {
                return new a(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public a[] newArray(int i) {
                return new a[i];
            }
        };

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        String f1367a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1368b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        double f1369c;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public a() {
        }

        protected a(Parcel parcel) {
            this.f1367a = parcel.readString();
            this.f1368b = parcel.readInt();
            this.f1369c = parcel.readDouble();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.f1367a);
            parcel.writeInt(this.f1368b);
            parcel.writeDouble(this.f1369c);
        }
    }

    private static boolean a(String str, b bVar, String str2) {
        Map<String, List<a>> map;
        try {
            map = bVar.f1371a;
        } catch (Throwable th) {
            hk.a(th, "hlUtil", "inb");
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

    private static boolean a(List<a> list) {
        if (list != null && list.size() > 0) {
            Iterator<a> it = list.iterator();
            while (it.hasNext()) {
                if (a(it.next())) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean a(a aVar) {
        if (aVar == null || aVar.f1369c == 1.0d) {
            return false;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (!TextUtils.isEmpty(aVar.f1367a) && aVar.f1368b > 0) {
            long timeInMillis = jCurrentTimeMillis - gt.a(aVar.f1367a, "HH:mm:ss").getTimeInMillis();
            if (timeInMillis > 0 && timeInMillis < aVar.f1368b * 1000) {
                if (aVar.f1369c == 0.0d) {
                    return true;
                }
                if (f1363d == null) {
                    f1363d = new Random();
                }
                f1363d.setSeed(((long) UUID.randomUUID().hashCode()) + jCurrentTimeMillis);
                if (f1363d.nextDouble() > aVar.f1369c) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String b(String str, b bVar, String str2) {
        try {
            Map<String, String> map = bVar.f1372b;
            if (map != null && map.size() > 0) {
                Uri uri = Uri.parse(str);
                String authority = uri.getAuthority();
                if (!map.containsKey(authority)) {
                    return str;
                }
                String str3 = map.get(authority);
                str = uri.buildUpon().authority(str3).toString();
                b(str2, authority, str3);
                return str;
            }
            return str;
        } catch (Throwable th) {
            hk.a(th, "hlUtil", "pdr");
            return str;
        }
    }

    public static void a(boolean z, String str) {
        try {
            Context context = gj.f1058c;
            if (context != null && !TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("timestamp", Long.valueOf(System.currentTimeMillis()));
                if (z) {
                    jSONObject.put("type", hj.f1236g);
                } else {
                    jSONObject.put("type", hj.f1235f);
                }
                jSONObject.put(AppMeasurementSdk.ConditionalUserProperty.NAME, str);
                jSONObject.put("version", hj.a(str));
                String string = jSONObject.toString();
                ja jaVar = new ja(context, "core", "1.0.3", "O005");
                jaVar.a(string);
                jb.a(jaVar, context);
            }
        } catch (Throwable unused) {
        }
    }

    private static void b(String str, String str2, String str3) {
        try {
            Context context = gj.f1058c;
            if (context != null && !TextUtils.isEmpty(str)) {
                if (f1364e == null) {
                    f1364e = new ConcurrentHashMap<>(8);
                }
                synchronized (f1364e) {
                    if (f1364e.containsKey(str2)) {
                        return;
                    }
                    f1364e.put(str2, str3);
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("timestamp", System.currentTimeMillis());
                    jSONObject.put("type", hj.j);
                    jSONObject.put(AppMeasurementSdk.ConditionalUserProperty.NAME, str);
                    jSONObject.put("version", hj.a(str));
                    jSONObject.put("domain", str2 + "#" + str3);
                    String string = jSONObject.toString();
                    if (TextUtils.isEmpty(string)) {
                        return;
                    }
                    ja jaVar = new ja(context, "core", "1.0.3", "O005");
                    jaVar.a(string);
                    jb.a(jaVar, context);
                }
            }
        } catch (Throwable unused) {
        }
    }

    private static void a(boolean z, String str, String str2, int i) {
        try {
            Context context = gj.f1058c;
            if (context != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("timestamp", System.currentTimeMillis());
                String strA = hj.a(str);
                if (z) {
                    jSONObject.put("type", hj.i);
                } else {
                    jSONObject.put("type", hj.f1237h);
                }
                jSONObject.put(AppMeasurementSdk.ConditionalUserProperty.NAME, str);
                jSONObject.put("version", strA);
                jSONObject.put("uri", Uri.parse(str2).getPath());
                jSONObject.put("blockLevel", i);
                String string = jSONObject.toString();
                if (TextUtils.isEmpty(string)) {
                    return;
                }
                ja jaVar = new ja(context, "core", "1.0.3", "O005");
                jaVar.a(string);
                if (f1365f == null) {
                    f1365f = Collections.synchronizedList(new ArrayList(16));
                }
                synchronized (f1365f) {
                    f1365f.add(jaVar);
                    if (f1365f.size() >= 15) {
                        a();
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static void a() {
        try {
            Context context = gj.f1058c;
            if (context == null) {
                return;
            }
            jb.a(b(), context);
        } catch (Throwable unused) {
        }
    }

    public static List<ja> b() {
        ArrayList arrayList = null;
        try {
        } catch (Throwable unused) {
        }
        synchronized (f1365f) {
            try {
                if (f1365f != null && f1365f.size() > 0) {
                    ArrayList arrayList2 = new ArrayList();
                    try {
                        arrayList2.addAll(f1365f);
                        f1365f.clear();
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