package com.baidu.mapapi.utils.route;

import android.content.Context;
import android.util.Log;
import com.baidu.mapapi.navi.IllegalNaviArgumentException;
import com.baidu.mapapi.utils.OpenClientUtil;
import com.baidu.mapapi.utils.b;
import com.baidu.mapapi.utils.poi.IllegalPoiSearchArgumentException;
import com.baidu.mapapi.utils.route.RouteParaOption;

/* JADX INFO: loaded from: classes.dex */
public class BaiduMapRoutePlan {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f3380a = true;

    /* JADX WARN: Removed duplicated region for block: B:31:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00f7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void a(com.baidu.mapapi.utils.route.RouteParaOption r10, android.content.Context r11, int r12) {
        /*
            Method dump skipped, instruction units count: 299
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapapi.utils.route.BaiduMapRoutePlan.a(com.baidu.mapapi.utils.route.RouteParaOption, android.content.Context, int):void");
    }

    public static void finish(Context context) {
        if (context != null) {
            b.a(context);
        }
    }

    public static boolean openBaiduMapDrivingRoute(RouteParaOption routeParaOption, Context context) {
        if (routeParaOption == null || context == null) {
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: para or context can not be null.");
        }
        if (routeParaOption.f3382b == null && routeParaOption.f3381a == null && routeParaOption.f3384d == null && routeParaOption.f3383c == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: startPoint and endPoint and endName and startName not all null.");
        }
        if (routeParaOption.f3383c == null && routeParaOption.f3381a == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: startPoint and startName not all null.");
        }
        if (routeParaOption.f3384d == null && routeParaOption.f3382b == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: endPoint and endName not all null.");
        }
        if (((routeParaOption.f3383c == null || routeParaOption.f3383c.equals("")) && routeParaOption.f3381a == null) || ((routeParaOption.f3384d == null || routeParaOption.f3384d.equals("")) && routeParaOption.f3382b == null)) {
            Log.e(BaiduMapRoutePlan.class.getName(), "poi startName or endName can not be empty string while pt is null");
            return false;
        }
        if (routeParaOption.f3386f == null) {
            routeParaOption.f3386f = RouteParaOption.EBusStrategyType.bus_recommend_way;
        }
        int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
        if (baiduMapVersion == 0) {
            Log.e("baidumapsdk", "BaiduMap app is not installed.");
            if (!f3380a) {
                throw new IllegalPoiSearchArgumentException("BDMapSDKException: BaiduMap app is not installed.");
            }
            a(routeParaOption, context, 0);
            return true;
        }
        if (baiduMapVersion >= 810) {
            return b.a(routeParaOption, context, 0);
        }
        Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.1");
        if (!f3380a) {
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: Baidumap app version is too lowl.Version is greater than 8.1");
        }
        a(routeParaOption, context, 0);
        return true;
    }

    public static boolean openBaiduMapTransitRoute(RouteParaOption routeParaOption, Context context) {
        if (routeParaOption == null || context == null) {
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: para or context can not be null.");
        }
        if (routeParaOption.f3382b == null && routeParaOption.f3381a == null && routeParaOption.f3384d == null && routeParaOption.f3383c == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: startPoint and endPoint and endName and startName not all null.");
        }
        if (routeParaOption.f3383c == null && routeParaOption.f3381a == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: startPoint and startName not all null.");
        }
        if (routeParaOption.f3384d == null && routeParaOption.f3382b == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: endPoint and endName not all null.");
        }
        if (((routeParaOption.f3383c == null || routeParaOption.f3383c.equals("")) && routeParaOption.f3381a == null) || ((routeParaOption.f3384d == null || routeParaOption.f3384d.equals("")) && routeParaOption.f3382b == null)) {
            Log.e(BaiduMapRoutePlan.class.getName(), "poi startName or endName can not be empty string while pt is null");
            return false;
        }
        if (routeParaOption.f3386f == null) {
            routeParaOption.f3386f = RouteParaOption.EBusStrategyType.bus_recommend_way;
        }
        int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
        if (baiduMapVersion == 0) {
            Log.e("baidumapsdk", "BaiduMap app is not installed.");
            if (!f3380a) {
                throw new IllegalPoiSearchArgumentException("BDMapSDKException: BaiduMap app is not installed.");
            }
            a(routeParaOption, context, 1);
            return true;
        }
        if (baiduMapVersion >= 810) {
            return b.a(routeParaOption, context, 1);
        }
        Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.1");
        if (!f3380a) {
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: Baidumap app version is too lowl.Version is greater than 8.1");
        }
        a(routeParaOption, context, 1);
        return true;
    }

    public static boolean openBaiduMapWalkingRoute(RouteParaOption routeParaOption, Context context) {
        if (routeParaOption == null || context == null) {
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: para or context can not be null.");
        }
        if (routeParaOption.f3382b == null && routeParaOption.f3381a == null && routeParaOption.f3384d == null && routeParaOption.f3383c == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: startPoint and endPoint and endName and startName not all null.");
        }
        if (routeParaOption.f3383c == null && routeParaOption.f3381a == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: startPoint and startName not all null.");
        }
        if (routeParaOption.f3384d == null && routeParaOption.f3382b == null) {
            throw new IllegalNaviArgumentException("BDMapSDKException: endPoint and endName not all null.");
        }
        if (((routeParaOption.f3383c == null || routeParaOption.f3383c.equals("")) && routeParaOption.f3381a == null) || ((routeParaOption.f3384d == null || routeParaOption.f3384d.equals("")) && routeParaOption.f3382b == null)) {
            Log.e(BaiduMapRoutePlan.class.getName(), "poi startName or endName can not be empty string while pt is null");
            return false;
        }
        if (routeParaOption.f3386f == null) {
            routeParaOption.f3386f = RouteParaOption.EBusStrategyType.bus_recommend_way;
        }
        int baiduMapVersion = OpenClientUtil.getBaiduMapVersion(context);
        if (baiduMapVersion == 0) {
            Log.e("baidumapsdk", "BaiduMap app is not installed.");
            if (!f3380a) {
                throw new IllegalPoiSearchArgumentException("BDMapSDKException: BaiduMap app is not installed.");
            }
            a(routeParaOption, context, 2);
            return true;
        }
        if (baiduMapVersion >= 810) {
            return b.a(routeParaOption, context, 2);
        }
        Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.1");
        if (!f3380a) {
            throw new IllegalPoiSearchArgumentException("BDMapSDKException: Baidumap app version is too lowl.Version is greater than 8.1");
        }
        a(routeParaOption, context, 2);
        return true;
    }

    public static void setSupportWebRoute(boolean z) {
        f3380a = z;
    }
}