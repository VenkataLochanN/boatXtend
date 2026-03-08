package com.baidu.mapapi.utils;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.common.AppTools;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapapi.navi.NaviParaOption;
import com.baidu.mapapi.utils.poi.DispathcPoiData;
import com.baidu.mapapi.utils.poi.PoiParaOption;
import com.baidu.mapapi.utils.route.RouteParaOption;
import com.baidu.mapframework.open.aidl.IComOpenClient;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static com.baidu.mapframework.open.aidl.a f3363d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static IComOpenClient f3364e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static int f3365f;
    private static RouteParaOption.EBusStrategyType o;
    private static Thread v;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f3362c = b.class.getName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f3360a = -1;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static String f3366g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static String f3367h = null;
    private static String i = null;
    private static List<DispathcPoiData> j = new ArrayList();
    private static LatLng k = null;
    private static LatLng l = null;
    private static String m = null;
    private static String n = null;
    private static String p = null;
    private static String q = null;
    private static LatLng r = null;
    private static int s = 0;
    private static boolean t = false;
    private static boolean u = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static ServiceConnection f3361b = new d();

    public static String a() {
        return AppTools.getBaiduMapToken();
    }

    public static void a(int i2, Context context) {
        switch (i2) {
            case 0:
            case 1:
            case 2:
                c(context, i2);
                break;
            case 3:
                c(context);
                break;
            case 4:
                d(context);
                break;
            case 5:
                e(context);
                break;
            case 7:
                f(context);
                break;
            case 8:
                g(context);
                break;
            case 9:
                h(context);
                break;
        }
    }

    public static void a(Context context) {
        if (u) {
            context.unbindService(f3361b);
            u = false;
        }
    }

    private static void a(List<DispathcPoiData> list, Context context) {
        f3366g = context.getPackageName();
        f3367h = b(context);
        i = "";
        List<DispathcPoiData> list2 = j;
        if (list2 != null) {
            list2.clear();
        }
        Iterator<DispathcPoiData> it = list.iterator();
        while (it.hasNext()) {
            j.add(it.next());
        }
    }

    public static boolean a(int i2) {
        switch (i2) {
            case 0:
            case 1:
            case 2:
                return g();
            case 3:
                return h();
            case 4:
                return m();
            case 5:
                return j();
            case 6:
                return i();
            case 7:
                return k();
            case 8:
                return l();
            default:
                return false;
        }
    }

    public static boolean a(Context context, int i2) {
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!com.baidu.platform.comapi.a.a.a(context)) {
            Log.d(f3362c, "package sign verify failed");
            return false;
        }
        t = false;
        switch (i2) {
            case 0:
                f3360a = 0;
                break;
            case 1:
                f3360a = 1;
                break;
            case 2:
                f3360a = 2;
                break;
            case 3:
                f3360a = 3;
                break;
            case 4:
                f3360a = 4;
                break;
            case 5:
                f3360a = 5;
                break;
            case 6:
                f3360a = 6;
                break;
            case 7:
                f3360a = 7;
                break;
            case 8:
                f3360a = 8;
                break;
            case 9:
                f3360a = 9;
                break;
        }
        if (i2 == 9) {
            u = false;
        }
        if (f3363d == null || !u) {
            b(context, i2);
        } else {
            if (f3364e != null) {
                t = true;
                return a(i2);
            }
            f3363d.a(new c(i2));
        }
        return true;
    }

    public static boolean a(NaviParaOption naviParaOption, Context context, int i2) {
        b(naviParaOption, context, i2);
        return a(context, i2);
    }

    public static boolean a(PoiParaOption poiParaOption, Context context, int i2) {
        b(poiParaOption, context, i2);
        return a(context, i2);
    }

    public static boolean a(RouteParaOption routeParaOption, Context context, int i2) {
        b(routeParaOption, context, i2);
        return a(context, i2);
    }

    public static boolean a(List<DispathcPoiData> list, Context context, int i2) {
        a(list, context);
        return a(context, i2);
    }

    public static String b(Context context) {
        PackageManager packageManager;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getPackageManager();
            try {
                applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        } catch (PackageManager.NameNotFoundException unused2) {
            packageManager = null;
        }
        return (String) packageManager.getApplicationLabel(applicationInfo);
    }

    private static void b(Context context, int i2) {
        Intent intent = new Intent();
        String strA = a();
        if (strA == null) {
            return;
        }
        intent.putExtra("api_token", strA);
        intent.setAction("com.baidu.map.action.OPEN_SERVICE");
        intent.setPackage("com.baidu.BaiduMap");
        if (i2 != 9) {
            u = context.bindService(intent, f3361b, 1);
        }
        if (!u) {
            Log.e("baidumapsdk", "bind service failed，call openapi");
            a(i2, context);
        } else {
            v = new Thread(new f(context, i2));
            v.setDaemon(true);
            v.start();
        }
    }

    private static void b(NaviParaOption naviParaOption, Context context, int i2) {
        f3366g = context.getPackageName();
        m = null;
        k = null;
        n = null;
        l = null;
        if (naviParaOption.getStartPoint() != null) {
            k = naviParaOption.getStartPoint();
        }
        if (naviParaOption.getEndPoint() != null) {
            l = naviParaOption.getEndPoint();
        }
        if (naviParaOption.getStartName() != null) {
            m = naviParaOption.getStartName();
        }
        if (naviParaOption.getEndName() != null) {
            n = naviParaOption.getEndName();
        }
    }

    private static void b(PoiParaOption poiParaOption, Context context, int i2) {
        p = null;
        q = null;
        r = null;
        s = 0;
        f3366g = context.getPackageName();
        if (poiParaOption.getUid() != null) {
            p = poiParaOption.getUid();
        }
        if (poiParaOption.getKey() != null) {
            q = poiParaOption.getKey();
        }
        if (poiParaOption.getCenter() != null) {
            r = poiParaOption.getCenter();
        }
        if (poiParaOption.getRadius() != 0) {
            s = poiParaOption.getRadius();
        }
    }

    private static void b(RouteParaOption routeParaOption, Context context, int i2) {
        int i3;
        m = null;
        k = null;
        n = null;
        l = null;
        f3366g = context.getPackageName();
        if (routeParaOption.getStartPoint() != null) {
            k = routeParaOption.getStartPoint();
        }
        if (routeParaOption.getEndPoint() != null) {
            l = routeParaOption.getEndPoint();
        }
        if (routeParaOption.getStartName() != null) {
            m = routeParaOption.getStartName();
        }
        if (routeParaOption.getEndName() != null) {
            n = routeParaOption.getEndName();
        }
        if (routeParaOption.getBusStrategyType() != null) {
            o = routeParaOption.getBusStrategyType();
        }
        if (i2 != 0) {
            i3 = 1;
            if (i2 != 1) {
                i3 = 2;
                if (i2 != 2) {
                    return;
                }
            }
        } else {
            i3 = 0;
        }
        f3365f = i3;
    }

    private static void c(Context context) {
        Thread thread = v;
        if (thread != null) {
            thread.interrupt();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("baidumap://map/place/detail?");
        sb.append("uid=");
        sb.append(p);
        sb.append("&show_type=");
        sb.append("detail_page");
        sb.append("&src=");
        sb.append("sdk_[" + f3366g + "]");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(sb.toString()));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00c6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void c(android.content.Context r8, int r9) {
        /*
            Method dump skipped, instruction units count: 279
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapapi.utils.b.c(android.content.Context, int):void");
    }

    private static void d(Context context) {
        Thread thread = v;
        if (thread != null) {
            thread.interrupt();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("baidumap://map/nearbysearch?");
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            r = CoordTrans.gcjToBaidu(r);
        }
        sb.append("center=");
        sb.append(r.latitude);
        sb.append(AppInfo.DELIM);
        sb.append(r.longitude);
        sb.append("&query=");
        sb.append(q);
        sb.append("&radius=");
        sb.append(s);
        sb.append("&src=");
        sb.append("sdk_[" + f3366g + "]");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(sb.toString()));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    private static void e(Context context) {
        Thread thread = v;
        if (thread != null) {
            thread.interrupt();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("baidumap://map/navi?");
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            k = CoordTrans.gcjToBaidu(k);
            l = CoordTrans.gcjToBaidu(l);
        }
        sb.append("origin=");
        sb.append(k.latitude);
        sb.append(AppInfo.DELIM);
        sb.append(k.longitude);
        sb.append("&location=");
        sb.append(l.latitude);
        sb.append(AppInfo.DELIM);
        sb.append(l.longitude);
        sb.append("&src=");
        sb.append("sdk_[" + f3366g + "]");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(sb.toString()));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    private static void f(Context context) {
        Thread thread = v;
        if (thread != null) {
            thread.interrupt();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("baidumap://map/walknavi?");
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            k = CoordTrans.gcjToBaidu(k);
            l = CoordTrans.gcjToBaidu(l);
        }
        sb.append("origin=");
        sb.append(k.latitude);
        sb.append(AppInfo.DELIM);
        sb.append(k.longitude);
        sb.append("&destination=");
        sb.append(l.latitude);
        sb.append(AppInfo.DELIM);
        sb.append(l.longitude);
        sb.append("&src=");
        sb.append("sdk_[" + f3366g + "]");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(sb.toString()));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    private static void g(Context context) {
        Thread thread = v;
        if (thread != null) {
            thread.interrupt();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("baidumap://map/bikenavi?");
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            k = CoordTrans.gcjToBaidu(k);
            l = CoordTrans.gcjToBaidu(l);
        }
        sb.append("origin=");
        sb.append(k.latitude);
        sb.append(AppInfo.DELIM);
        sb.append(k.longitude);
        sb.append("&destination=");
        sb.append(l.latitude);
        sb.append(AppInfo.DELIM);
        sb.append(l.longitude);
        sb.append("&src=");
        sb.append("sdk_[" + f3366g + "]");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(sb.toString()));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    private static boolean g() {
        String strA;
        try {
            Log.d(f3362c, "callDispatchTakeOutRoute");
            strA = f3364e.a("map.android.baidu.mainmap");
        } catch (RemoteException e2) {
            Log.d(f3362c, "callDispatchTakeOut exception", e2);
        }
        if (strA == null) {
            Log.d(f3362c, "callDispatchTakeOut com not found");
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString("target", "route_search_page");
        Bundle bundle2 = new Bundle();
        bundle2.putInt("route_type", f3365f);
        bundle2.putInt("bus_strategy", o.ordinal());
        bundle2.putInt("cross_city_bus_strategy", 5);
        if (k != null) {
            bundle2.putInt("start_type", 1);
            bundle2.putInt("start_longitude", (int) CoordUtil.ll2mc(k).getLongitudeE6());
            bundle2.putInt("start_latitude", (int) CoordUtil.ll2mc(k).getLatitudeE6());
        } else {
            bundle2.putInt("start_type", 2);
            bundle2.putInt("start_longitude", 0);
            bundle2.putInt("start_latitude", 0);
        }
        if (m != null) {
            bundle2.putString("start_keyword", m);
        } else {
            bundle2.putString("start_keyword", "地图上的点");
        }
        bundle2.putString("start_uid", "");
        if (l != null) {
            bundle2.putInt("end_type", 1);
            bundle2.putInt("end_longitude", (int) CoordUtil.ll2mc(l).getLongitudeE6());
            bundle2.putInt("end_latitude", (int) CoordUtil.ll2mc(l).getLatitudeE6());
        } else {
            bundle2.putInt("end_type", 2);
            bundle2.putInt("end_longitude", 0);
            bundle2.putInt("end_latitude", 0);
        }
        if (n != null) {
            bundle2.putString("end_keyword", n);
        } else {
            bundle2.putString("end_keyword", "地图上的点");
        }
        bundle2.putString("end_uid", "");
        bundle.putBundle("base_params", bundle2);
        Bundle bundle3 = new Bundle();
        bundle3.putString("launch_from", "sdk_[" + f3366g + "]");
        bundle.putBundle("ext_params", bundle3);
        return f3364e.a("map.android.baidu.mainmap", strA, bundle);
    }

    private static void h(Context context) {
        Thread thread = v;
        if (thread != null) {
            thread.interrupt();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("baidumap://map/walknavi?");
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            k = CoordTrans.gcjToBaidu(k);
            l = CoordTrans.gcjToBaidu(l);
        }
        sb.append("origin=");
        sb.append(k.latitude);
        sb.append(AppInfo.DELIM);
        sb.append(k.longitude);
        sb.append("&destination=");
        sb.append(l.latitude);
        sb.append(AppInfo.DELIM);
        sb.append(l.longitude);
        sb.append("&mode=");
        sb.append("walking_ar");
        sb.append("&src=");
        sb.append("sdk_[" + f3366g + "]");
        Log.e("test", sb.toString());
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(sb.toString()));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    private static boolean h() {
        try {
            Log.d(f3362c, "callDispatchTakeOutPoiDetials");
            String strA = f3364e.a("map.android.baidu.mainmap");
            if (strA == null) {
                Log.d(f3362c, "callDispatchTakeOut com not found");
                return false;
            }
            Bundle bundle = new Bundle();
            bundle.putString("target", "request_poi_detail_page");
            Bundle bundle2 = new Bundle();
            bundle2.putString("uid", p != null ? p : "");
            bundle.putBundle("base_params", bundle2);
            Bundle bundle3 = new Bundle();
            bundle3.putString("launch_from", "sdk_[" + f3366g + "]");
            bundle.putBundle("ext_params", bundle3);
            return f3364e.a("map.android.baidu.mainmap", strA, bundle);
        } catch (RemoteException e2) {
            Log.d(f3362c, "callDispatchTakeOut exception", e2);
            return false;
        }
    }

    private static boolean i() {
        List<DispathcPoiData> list = j;
        if (list != null && list.size() > 0) {
            try {
                Log.d(f3362c, "callDispatchPoiToBaiduMap");
                String strA = f3364e.a("map.android.baidu.mainmap");
                if (strA != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("target", "favorite_page");
                    Bundle bundle2 = new Bundle();
                    JSONArray jSONArray = new JSONArray();
                    int i2 = 0;
                    for (int i3 = 0; i3 < j.size(); i3++) {
                        if (j.get(i3).name != null && !j.get(i3).name.equals("") && j.get(i3).pt != null) {
                            JSONObject jSONObject = new JSONObject();
                            try {
                                jSONObject.put(AppMeasurementSdk.ConditionalUserProperty.NAME, j.get(i3).name);
                                GeoPoint geoPointLl2mc = CoordUtil.ll2mc(j.get(i3).pt);
                                jSONObject.put("ptx", geoPointLl2mc.getLongitudeE6());
                                jSONObject.put("pty", geoPointLl2mc.getLatitudeE6());
                                jSONObject.put("addr", j.get(i3).addr);
                                jSONObject.put("uid", j.get(i3).uid);
                                i2++;
                                jSONArray.put(jSONObject);
                            } catch (JSONException e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                    if (i2 == 0) {
                        return false;
                    }
                    bundle2.putString(AeUtil.ROOT_DATA_PATH_OLD_NAME, jSONArray.toString());
                    bundle2.putString("from", f3367h);
                    bundle2.putString("pkg", f3366g);
                    bundle2.putString("cls", i);
                    bundle2.putInt("count", i2);
                    bundle.putBundle("base_params", bundle2);
                    Bundle bundle3 = new Bundle();
                    bundle3.putString("launch_from", "sdk_[" + f3366g + "]");
                    bundle.putBundle("ext_params", bundle3);
                    return f3364e.a("map.android.baidu.mainmap", strA, bundle);
                }
                Log.d(f3362c, "callDispatchPoiToBaiduMap com not found");
            } catch (RemoteException e3) {
                Log.d(f3362c, "callDispatchPoiToBaiduMap exception", e3);
            }
        }
        return false;
    }

    private static boolean j() {
        String strA;
        try {
            Log.d(f3362c, "callDispatchTakeOutRouteNavi");
            strA = f3364e.a("map.android.baidu.mainmap");
        } catch (RemoteException e2) {
            Log.d(f3362c, "callDispatchTakeOut exception", e2);
        }
        if (strA == null) {
            Log.d(f3362c, "callDispatchTakeOut com not found");
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString("target", "navigation_page");
        Bundle bundle2 = new Bundle();
        bundle2.putString("coord_type", "bd09ll");
        bundle2.putString("type", "DIS");
        StringBuffer stringBuffer = new StringBuffer();
        if (m != null) {
            stringBuffer.append("name:" + m + "|");
        }
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            k = CoordTrans.gcjToBaidu(k);
        }
        stringBuffer.append(String.format("latlng:%f,%f", Double.valueOf(k.latitude), Double.valueOf(k.longitude)));
        StringBuffer stringBuffer2 = new StringBuffer();
        if (n != null) {
            stringBuffer2.append("name:" + n + "|");
        }
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            l = CoordTrans.gcjToBaidu(l);
        }
        stringBuffer2.append(String.format("latlng:%f,%f", Double.valueOf(l.latitude), Double.valueOf(l.longitude)));
        bundle2.putString("origin", stringBuffer.toString());
        bundle2.putString(FirebaseAnalytics.Param.DESTINATION, stringBuffer2.toString());
        bundle.putBundle("base_params", bundle2);
        Bundle bundle3 = new Bundle();
        bundle3.putString("launch_from", "sdk_[" + f3366g + "]");
        bundle.putBundle("ext_params", bundle3);
        return f3364e.a("map.android.baidu.mainmap", strA, bundle);
    }

    private static boolean k() {
        String strA;
        try {
            Log.d(f3362c, "callDispatchTakeOutRouteNavi");
            strA = f3364e.a("map.android.baidu.mainmap");
        } catch (Exception e2) {
            Log.d(f3362c, "callDispatchTakeOut exception", e2);
        }
        if (strA == null) {
            Log.d(f3362c, "callDispatchTakeOut com not found");
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString("target", "walknavi_page");
        Bundle bundle2 = new Bundle();
        bundle2.putString("coord_type", "bd09ll");
        StringBuffer stringBuffer = new StringBuffer();
        if (m != null) {
            stringBuffer.append("name:" + m + "|");
        }
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            k = CoordTrans.gcjToBaidu(k);
        }
        stringBuffer.append(String.format("latlng:%f,%f", Double.valueOf(k.latitude), Double.valueOf(k.longitude)));
        StringBuffer stringBuffer2 = new StringBuffer();
        if (n != null) {
            stringBuffer2.append("name:" + n + "|");
        }
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            l = CoordTrans.gcjToBaidu(l);
        }
        stringBuffer2.append(String.format("latlng:%f,%f", Double.valueOf(l.latitude), Double.valueOf(l.longitude)));
        bundle2.putString("origin", stringBuffer.toString());
        bundle2.putString(FirebaseAnalytics.Param.DESTINATION, stringBuffer2.toString());
        bundle.putBundle("base_params", bundle2);
        Bundle bundle3 = new Bundle();
        bundle3.putString("launch_from", "sdk_[" + f3366g + "]");
        bundle.putBundle("ext_params", bundle3);
        return f3364e.a("map.android.baidu.mainmap", strA, bundle);
    }

    private static boolean l() {
        String strA;
        try {
            Log.d(f3362c, "callDispatchTakeOutRouteRidingNavi");
            strA = f3364e.a("map.android.baidu.mainmap");
        } catch (RemoteException e2) {
            Log.d(f3362c, "callDispatchTakeOut exception", e2);
        }
        if (strA == null) {
            Log.d(f3362c, "callDispatchTakeOut com not found");
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString("target", "bikenavi_page");
        Bundle bundle2 = new Bundle();
        bundle2.putString("coord_type", "bd09ll");
        StringBuffer stringBuffer = new StringBuffer();
        if (m != null) {
            stringBuffer.append("name:" + m + "|");
        }
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            k = CoordTrans.gcjToBaidu(k);
        }
        stringBuffer.append(String.format("latlng:%f,%f", Double.valueOf(k.latitude), Double.valueOf(k.longitude)));
        StringBuffer stringBuffer2 = new StringBuffer();
        if (n != null) {
            stringBuffer2.append("name:" + n + "|");
        }
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            l = CoordTrans.gcjToBaidu(l);
        }
        stringBuffer2.append(String.format("latlng:%f,%f", Double.valueOf(l.latitude), Double.valueOf(l.longitude)));
        bundle2.putString("origin", stringBuffer.toString());
        bundle2.putString(FirebaseAnalytics.Param.DESTINATION, stringBuffer2.toString());
        bundle.putBundle("base_params", bundle2);
        Bundle bundle3 = new Bundle();
        bundle3.putString("launch_from", "sdk_[" + f3366g + "]");
        bundle.putBundle("ext_params", bundle3);
        return f3364e.a("map.android.baidu.mainmap", strA, bundle);
    }

    private static boolean m() {
        try {
            Log.d(f3362c, "callDispatchTakeOutPoiNearbySearch");
            String strA = f3364e.a("map.android.baidu.mainmap");
            if (strA == null) {
                Log.d(f3362c, "callDispatchTakeOut com not found");
                return false;
            }
            Bundle bundle = new Bundle();
            bundle.putString("target", "poi_search_page");
            Bundle bundle2 = new Bundle();
            if (q != null) {
                bundle2.putString("search_key", q);
            } else {
                bundle2.putString("search_key", "");
            }
            if (r != null) {
                bundle2.putInt("center_pt_x", (int) CoordUtil.ll2mc(r).getLongitudeE6());
                bundle2.putInt("center_pt_y", (int) CoordUtil.ll2mc(r).getLatitudeE6());
            } else {
                bundle2.putString("search_key", "");
            }
            if (s != 0) {
                bundle2.putInt("search_radius", s);
            } else {
                bundle2.putInt("search_radius", 1000);
            }
            bundle2.putBoolean("is_direct_search", true);
            bundle2.putBoolean("is_direct_area_search", true);
            bundle.putBundle("base_params", bundle2);
            Bundle bundle3 = new Bundle();
            bundle3.putString("launch_from", "sdk_[" + f3366g + "]");
            bundle.putBundle("ext_params", bundle3);
            return f3364e.a("map.android.baidu.mainmap", strA, bundle);
        } catch (RemoteException e2) {
            Log.d(f3362c, "callDispatchTakeOut exception", e2);
            return false;
        }
    }
}