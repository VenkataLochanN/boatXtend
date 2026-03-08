package com.baidu.mapsdkplatform.comapi.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.amazon.identity.auth.map.device.token.Token;
import com.baidu.android.bbalbs.common.util.CommonParam;
import com.baidu.mapapi.VersionInfo;
import com.baidu.mapsdkplatform.comjni.util.AppMD5;
import com.github.lzyzsd.library.BuildConfig;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.alexa.AlexaCustomSkillConstant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FilenameUtils;

/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static String f3849b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static String f3850c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static String f3851d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static String f3852e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    static int f3853f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    static int f3854g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    static int f3855h;
    static int i;
    static int j;
    static int k;
    static String l;
    static String r;
    static String s;
    public static Context v;
    public static String y;
    private static final String z = i.class.getSimpleName();
    private static com.baidu.mapsdkplatform.comjni.util.a A = new com.baidu.mapsdkplatform.comjni.util.a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static String f3848a = "02";
    static String m = "baidu";
    static String n = "baidu";
    static String o = "";
    static String p = "";
    static String q = "";
    static String t = "-1";
    static String u = "-1";
    public static final int w = Integer.parseInt(Build.VERSION.SDK);
    public static float x = 1.0f;
    private static boolean B = true;
    private static int C = 0;
    private static int D = 0;
    private static Map<String, String> E = new HashMap();

    public static void a() {
        d();
    }

    public static void a(String str) {
        l = str;
        f();
    }

    public static void a(String str, String str2) {
        t = str2;
        u = str;
        f();
    }

    public static byte[] a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray();
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Bundle b() {
        Bundle bundle = new Bundle();
        bundle.putString("cpu", o);
        bundle.putString("resid", f3848a);
        bundle.putString("channel", m);
        bundle.putString("glr", p);
        bundle.putString("glv", q);
        bundle.putString("mb", g());
        bundle.putString("sv", i());
        bundle.putString("os", k());
        bundle.putInt("dpi_x", l());
        bundle.putInt("dpi_y", l());
        bundle.putString("net", l);
        bundle.putString("cuid", p());
        bundle.putByteArray("signature", a(v));
        bundle.putString("pcn", v.getPackageName());
        bundle.putInt("screen_x", h());
        bundle.putInt("screen_y", j());
        com.baidu.mapsdkplatform.comjni.util.a aVar = A;
        if (aVar != null) {
            aVar.a(bundle);
        }
        return bundle;
    }

    public static void b(Context context) {
        Map<String, String> map;
        String str;
        v = context;
        if (context.getFilesDir() != null) {
            r = context.getFilesDir().getAbsolutePath();
        }
        if (context.getCacheDir() != null) {
            s = context.getCacheDir().getAbsolutePath();
        }
        f3850c = Build.MODEL;
        f3851d = "Android" + Build.VERSION.SDK;
        f3849b = context.getPackageName();
        c(context);
        d(context);
        e(context);
        try {
            try {
                LocationManager locationManager = (LocationManager) context.getSystemService(FirebaseAnalytics.Param.LOCATION);
                C = locationManager.isProviderEnabled("gps") ? 1 : 0;
                D = locationManager.isProviderEnabled("network") ? 1 : 0;
                E.put("resid", AppMD5.encodeUrlParamsValue(f3848a));
                E.put("channel", AppMD5.encodeUrlParamsValue(m()));
                E.put("mb", AppMD5.encodeUrlParamsValue(g()));
                E.put("sv", AppMD5.encodeUrlParamsValue(i()));
                E.put("os", AppMD5.encodeUrlParamsValue(k()));
                E.put("dpi", AppMD5.encodeUrlParamsValue(String.format("%d,%d", Integer.valueOf(l()), Integer.valueOf(l()))));
                E.put("cuid", AppMD5.encodeUrlParamsValue(p()));
                E.put("pcn", AppMD5.encodeUrlParamsValue(v.getPackageName()));
                map = E;
                str = String.format("%d,%d", Integer.valueOf(h()), Integer.valueOf(j()));
            } catch (Exception unused) {
                Log.w("baidumapsdk", "LocationManager error");
                E.put("resid", AppMD5.encodeUrlParamsValue(f3848a));
                E.put("channel", AppMD5.encodeUrlParamsValue(m()));
                E.put("mb", AppMD5.encodeUrlParamsValue(g()));
                E.put("sv", AppMD5.encodeUrlParamsValue(i()));
                E.put("os", AppMD5.encodeUrlParamsValue(k()));
                E.put("dpi", AppMD5.encodeUrlParamsValue(String.format("%d,%d", Integer.valueOf(l()), Integer.valueOf(l()))));
                E.put("cuid", AppMD5.encodeUrlParamsValue(p()));
                E.put("pcn", AppMD5.encodeUrlParamsValue(v.getPackageName()));
                map = E;
                str = String.format("%d,%d", Integer.valueOf(h()), Integer.valueOf(j()));
            }
            map.put("screen", AppMD5.encodeUrlParamsValue(str));
            com.baidu.mapsdkplatform.comjni.util.a aVar = A;
            if (aVar != null) {
                aVar.a();
            }
        } catch (Throwable th) {
            E.put("resid", AppMD5.encodeUrlParamsValue(f3848a));
            E.put("channel", AppMD5.encodeUrlParamsValue(m()));
            E.put("mb", AppMD5.encodeUrlParamsValue(g()));
            E.put("sv", AppMD5.encodeUrlParamsValue(i()));
            E.put("os", AppMD5.encodeUrlParamsValue(k()));
            E.put("dpi", AppMD5.encodeUrlParamsValue(String.format("%d,%d", Integer.valueOf(l()), Integer.valueOf(l()))));
            E.put("cuid", AppMD5.encodeUrlParamsValue(p()));
            E.put("pcn", AppMD5.encodeUrlParamsValue(v.getPackageName()));
            E.put("screen", AppMD5.encodeUrlParamsValue(String.format("%d,%d", Integer.valueOf(h()), Integer.valueOf(j()))));
            throw th;
        }
    }

    public static String c() {
        if (E == null) {
            return null;
        }
        Date date = new Date();
        long time = date.getTime() + ((long) (date.getSeconds() * 1000));
        E.put("ctm", AppMD5.encodeUrlParamsValue(String.format("%f", Double.valueOf((time / 1000) + ((time % 1000) / 1000.0d)))));
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : E.entrySet()) {
            sb.append("&");
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
        }
        return sb.toString();
    }

    private static void c(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            f3852e = VersionInfo.getApiVersion();
            if (f3852e != null && !f3852e.equals("")) {
                f3852e = f3852e.replace('_', FilenameUtils.EXTENSION_SEPARATOR);
            }
            f3853f = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            f3852e = BuildConfig.VERSION_NAME;
            f3853f = 1;
        }
    }

    public static void d() {
        com.baidu.mapsdkplatform.comjni.util.a aVar = A;
        if (aVar != null) {
            aVar.b();
        }
    }

    private static void d(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display defaultDisplay = windowManager != null ? windowManager.getDefaultDisplay() : null;
        if (defaultDisplay != null) {
            f3854g = defaultDisplay.getWidth();
            f3855h = defaultDisplay.getHeight();
            defaultDisplay.getMetrics(displayMetrics);
        }
        x = displayMetrics.density;
        i = (int) displayMetrics.xdpi;
        j = (int) displayMetrics.ydpi;
        if (w > 3) {
            k = displayMetrics.densityDpi;
        } else {
            k = 160;
        }
        if (k == 0) {
            k = 160;
        }
    }

    public static String e() {
        return l;
    }

    private static void e(Context context) {
        l = AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE;
    }

    public static void f() {
        E.put("net", AppMD5.encodeUrlParamsValue(e()));
        E.put("appid", AppMD5.encodeUrlParamsValue(t));
        E.put("bduid", "");
        if (A == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("cpu", o);
        bundle.putString("resid", f3848a);
        bundle.putString("channel", m);
        bundle.putString("glr", p);
        bundle.putString("glv", q);
        bundle.putString("mb", g());
        bundle.putString("sv", i());
        bundle.putString("os", k());
        bundle.putInt("dpi_x", l());
        bundle.putInt("dpi_y", l());
        bundle.putString("net", l);
        bundle.putString("cuid", p());
        bundle.putString("pcn", v.getPackageName());
        bundle.putInt("screen_x", h());
        bundle.putInt("screen_y", j());
        bundle.putString("appid", t);
        bundle.putString("duid", u);
        if (!TextUtils.isEmpty(y)) {
            bundle.putString(Token.KEY_TOKEN, y);
        }
        A.a(bundle);
        SysUpdateObservable.getInstance().updatePhoneInfo();
    }

    public static String g() {
        return f3850c;
    }

    public static int h() {
        return f3854g;
    }

    public static String i() {
        return f3852e;
    }

    public static int j() {
        return f3855h;
    }

    public static String k() {
        return f3851d;
    }

    public static int l() {
        return k;
    }

    public static String m() {
        return m;
    }

    public static String n() {
        return f3849b;
    }

    public static String o() {
        return r;
    }

    public static String p() {
        String strA;
        try {
            strA = CommonParam.a(v);
        } catch (Exception unused) {
            strA = "";
        }
        return strA == null ? "" : strA;
    }
}