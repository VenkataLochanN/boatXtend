package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: StatisticsUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class ep {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f751a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static boolean f752b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static boolean f753c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static boolean f754d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static boolean f755e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static boolean f756f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static boolean f757g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static boolean f758h = false;
    private static boolean i = false;
    private static HashMap<String, Boolean> j = new HashMap<>();

    public static void a(Context context, boolean z) {
        try {
            String strA = a(z);
            ja jaVar = new ja(context, "3dmap", "7.6.0", "O001");
            jaVar.a(strA);
            jb.a(jaVar, context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static String a(boolean z) {
        try {
            return "{\"Quest\":" + z + "}";
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static void a(Context context, long j2) {
        try {
            HashMap map = new HashMap();
            map.put("amap_3dmap_rendertime", Long.valueOf(j2));
            map.put("amap_3dmap_render_background", 0L);
            a(context, "O005", a(map));
        } catch (Throwable unused) {
        }
    }

    public static void b(Context context, boolean z) {
        if (f751a) {
            return;
        }
        try {
            HashMap map = new HashMap();
            map.put("amap_3dmap_stylemap", Integer.valueOf(z ? 1 : 0));
            a(context, "O006", a(map));
            f751a = true;
        } catch (Throwable unused) {
        }
    }

    public static void c(Context context, boolean z) {
        if (f752b) {
            return;
        }
        try {
            HashMap map = new HashMap();
            map.put("amap_3dmap_indoormap", Integer.valueOf(z ? 1 : 0));
            a(context, "O007", a(map));
            f752b = true;
        } catch (Throwable unused) {
        }
    }

    public static synchronized void a(Context context, String str) {
        if (j != null && !TextUtils.isEmpty(str)) {
            if (j.containsKey(str) && j.get(str).booleanValue()) {
                return;
            }
            HashMap map = new HashMap();
            map.put("amap_3dmap_coordinate", str);
            a(context, "O008", a(map));
            if (!j.containsKey(str)) {
                j.put(str, true);
            }
        }
    }

    public static void a(Context context) {
        if (f753c) {
            return;
        }
        try {
            HashMap map = new HashMap();
            map.put("amap_3dmap_heatmap", 1);
            a(context, "O009", a(map));
            f753c = true;
        } catch (Throwable unused) {
        }
    }

    public static void b(Context context) {
        if (f754d) {
            return;
        }
        try {
            HashMap map = new HashMap();
            map.put("amap_3dmap_offlinemap", 1);
            a(context, "O010", a(map));
            f754d = true;
        } catch (Throwable unused) {
        }
    }

    public static void c(Context context) {
        if (f755e) {
            return;
        }
        try {
            HashMap map = new HashMap();
            map.put("amap_3dmap_particleoverlay", 1);
            a(context, "O011", a(map));
            f755e = true;
        } catch (Throwable unused) {
        }
    }

    public static void d(Context context) {
        if (f757g) {
            return;
        }
        try {
            HashMap map = new HashMap();
            map.put("amap_3dmap_bzmapreview", 1);
            a(context, "O012", a(map));
            f757g = true;
        } catch (Throwable unused) {
        }
    }

    public static void e(Context context) {
        if (f758h) {
            return;
        }
        try {
            HashMap map = new HashMap();
            map.put("amap_3dmap_wxmapreview", 1);
            a(context, "O013", a(map));
            f758h = true;
        } catch (Throwable unused) {
        }
    }

    public static void f(Context context) {
        if (f756f) {
            return;
        }
        try {
            HashMap map = new HashMap();
            map.put("amap_3dmap_renderfps", 1);
            a(context, "O014", a(map));
            f756f = true;
        } catch (Throwable unused) {
        }
    }

    public static void g(Context context) {
        if (i) {
            return;
        }
        try {
            HashMap map = new HashMap();
            map.put("amap_3dmap_buildingoverlay", 1);
            a(context, "O015", a(map));
            i = true;
        } catch (Throwable unused) {
        }
    }

    private static <T> String a(Map<String, T> map) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            for (Map.Entry<String, T> entry : map.entrySet()) {
                sb.append("\"" + entry.getKey() + "\":");
                sb.append(entry.getValue());
                sb.append(AppInfo.DELIM);
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("}");
            return sb.toString();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private static void a(Context context, String str, String str2) {
        if (context == null) {
            return;
        }
        try {
            ja jaVar = new ja(context, "3dmap", "7.6.0", str);
            jaVar.a(str2);
            jb.a(jaVar, context);
        } catch (Throwable unused) {
        }
    }
}