package com.baidu.mapapi.navi;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.baidu.mapapi.VersionInfo;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapapi.utils.OpenClientUtil;
import com.baidu.mapapi.utils.b;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class BaiduMapNavigation {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f3092a = true;

    private static String a(Context context) {
        PackageManager packageManager;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getApplicationContext().getPackageManager();
            try {
                applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        } catch (PackageManager.NameNotFoundException unused2) {
            packageManager = null;
        }
        return (String) packageManager.getApplicationLabel(applicationInfo);
    }

    private static void a(NaviParaOption naviParaOption, Context context) throws IllegalNaviArgumentException {
        if (naviParaOption == null || context == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: para or context can not be null.");
        }
        if (naviParaOption.f3093a == null || naviParaOption.f3095c == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: you must set start and end point.");
        }
        GeoPoint geoPointLl2mc = CoordUtil.ll2mc(naviParaOption.f3093a);
        GeoPoint geoPointLl2mc2 = CoordUtil.ll2mc(naviParaOption.f3095c);
        StringBuilder sb = new StringBuilder();
        sb.append("http://app.navi.baidu.com/mobile/#navi/naving/");
        sb.append("&sy=0");
        sb.append("&endp=");
        sb.append("&start=");
        sb.append("&startwd=");
        sb.append("&endwd=");
        sb.append("&fromprod=map_sdk");
        sb.append("&app_version=");
        sb.append(VersionInfo.VERSION_INFO);
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put("type", "1");
            if (naviParaOption.f3094b == null || naviParaOption.f3094b.equals("")) {
                jSONObject.put("keyword", "");
            } else {
                jSONObject.put("keyword", naviParaOption.f3094b);
            }
            jSONObject.put("xy", String.valueOf(geoPointLl2mc.getLongitudeE6()) + AppInfo.DELIM + String.valueOf(geoPointLl2mc.getLatitudeE6()));
            jSONArray.put(jSONObject);
            jSONObject2.put("type", "1");
            if (naviParaOption.f3096d == null || naviParaOption.f3096d.equals("")) {
                jSONObject.put("keyword", "");
            } else {
                jSONObject.put("keyword", naviParaOption.f3096d);
            }
            jSONObject2.put("xy", String.valueOf(geoPointLl2mc2.getLongitudeE6()) + AppInfo.DELIM + String.valueOf(geoPointLl2mc2.getLatitudeE6()));
            jSONArray.put(jSONObject2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        if (jSONArray.length() > 0) {
            sb.append("&positions=");
            sb.append(jSONArray.toString());
        }
        sb.append("&ctrl_type=");
        sb.append("&mrsl=");
        sb.append("/vt=map&state=entry");
        Uri uri = Uri.parse(sb.toString());
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setFlags(268435456);
        intent.setData(uri);
        context.startActivity(intent);
    }

    public static void finish(Context context) {
        if (context != null) {
            b.a(context);
        }
    }

    public static boolean openBaiduMapBikeNavi(NaviParaOption naviParaOption, Context context) {
        String str;
        if (naviParaOption == null || context == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: para or context can not be null.");
        }
        if (naviParaOption.f3095c == null || naviParaOption.f3093a == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: start point or end point can not be null.");
        }
        int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
        if (baiduMapVersion == 0) {
            str = "BaiduMap app is not installed.";
        } else {
            if (baiduMapVersion >= 869) {
                return b.a(naviParaOption, context, 8);
            }
            str = "Baidumap app version is too lowl.Version is greater than 8.6.6";
        }
        Log.e("baidumapsdk", str);
        return false;
    }

    public static boolean openBaiduMapNavi(NaviParaOption naviParaOption, Context context) {
        if (naviParaOption == null || context == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: para or context can not be null.");
        }
        if (naviParaOption.f3095c == null || naviParaOption.f3093a == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: start point or end point can not be null.");
        }
        int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
        if (baiduMapVersion == 0) {
            Log.e("baidumapsdk", "BaiduMap app is not installed.");
            if (!f3092a) {
                throw new BaiduMapAppNotSupportNaviException("BDMapSDKException: BaiduMap app is not installed.");
            }
            a(naviParaOption, context);
            return true;
        }
        if (baiduMapVersion >= 830) {
            return b.a(naviParaOption, context, 5);
        }
        Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.2");
        if (!f3092a) {
            throw new BaiduMapAppNotSupportNaviException("BDMapSDKException: Baidumap app version is too lowl.Version is greater than 8.2");
        }
        a(naviParaOption, context);
        return true;
    }

    public static boolean openBaiduMapWalkNavi(NaviParaOption naviParaOption, Context context) {
        String str;
        if (naviParaOption == null || context == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: para or context can not be null.");
        }
        if (naviParaOption.f3095c == null || naviParaOption.f3093a == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: start point or end point can not be null.");
        }
        int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
        if (baiduMapVersion == 0) {
            str = "BaiduMap app is not installed.";
        } else {
            if (baiduMapVersion >= 869) {
                return b.a(naviParaOption, context, 7);
            }
            str = "Baidumap app version is too lowl.Version is greater than 8.6.6";
        }
        Log.e("baidumapsdk", str);
        return false;
    }

    public static boolean openBaiduMapWalkNaviAR(NaviParaOption naviParaOption, Context context) {
        String str;
        if (naviParaOption == null || context == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: para or context can not be null.");
        }
        if (naviParaOption.f3095c == null || naviParaOption.f3093a == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: start point or end point can not be null.");
        }
        int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
        if (baiduMapVersion == 0) {
            str = "BaiduMap app is not installed.";
        } else {
            if (baiduMapVersion >= 869) {
                return b.a(naviParaOption, context, 9);
            }
            str = "Baidumap app version is too lowl.Version is greater than 8.6.6";
        }
        Log.e("baidumapsdk", str);
        return false;
    }

    @Deprecated
    public static void openWebBaiduMapNavi(NaviParaOption naviParaOption, Context context) throws IllegalNaviArgumentException {
        Uri uri;
        Intent intent;
        if (naviParaOption == null || context == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: para or context can not be null.");
        }
        if (naviParaOption.f3093a != null && naviParaOption.f3095c != null) {
            GeoPoint geoPointLl2mc = CoordUtil.ll2mc(naviParaOption.f3093a);
            GeoPoint geoPointLl2mc2 = CoordUtil.ll2mc(naviParaOption.f3095c);
            uri = Uri.parse("http://daohang.map.baidu.com/mobile/#navi/naving/start=" + geoPointLl2mc.getLongitudeE6() + AppInfo.DELIM + geoPointLl2mc.getLatitudeE6() + "&endp=" + geoPointLl2mc2.getLongitudeE6() + AppInfo.DELIM + geoPointLl2mc2.getLatitudeE6() + "&fromprod=" + a(context) + "/vt=map&state=entry");
            intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setFlags(268435456);
        } else {
            if (naviParaOption.f3094b == null || naviParaOption.f3094b.equals("") || naviParaOption.f3096d == null || naviParaOption.f3096d.equals("")) {
                throw new IllegalNaviArgumentException("BDMapSDKException: you must set start and end point or set the start and end name.");
            }
            uri = Uri.parse("http://daohang.map.baidu.com/mobile/#search/search/qt=nav&sn=2$$$$$$" + naviParaOption.f3094b + "$$$$$$&en=2$$$$$$" + naviParaOption.f3096d + "$$$$$$&fromprod=" + a(context));
            intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
        }
        intent.setData(uri);
        context.startActivity(intent);
    }

    public static void setSupportWebNavi(boolean z) {
        f3092a = z;
    }
}