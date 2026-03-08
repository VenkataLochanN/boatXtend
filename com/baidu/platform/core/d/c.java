package com.baidu.platform.core.d;

import androidx.core.app.NotificationCompat;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.baidu.mapapi.common.Logger;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.TaxiInfo;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class c extends k {
    private RouteNode a(JSONArray jSONArray, List<RouteNode> list) {
        int length;
        if (jSONArray != null && (length = jSONArray.length()) > 0) {
            for (int i = 0; i < length; i++) {
                JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
                if (jSONObjectOptJSONObject != null) {
                    RouteNode routeNodeA = a(jSONObjectOptJSONObject);
                    if (i == length - 1) {
                        return routeNodeA;
                    }
                    list.add(routeNodeA);
                }
            }
        }
        return null;
    }

    private RouteNode a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        RouteNode routeNode = new RouteNode();
        routeNode.setTitle(jSONObject.optString("wd"));
        routeNode.setUid(jSONObject.optString("uid"));
        GeoPoint geoPoint = new GeoPoint(0.0d, 0.0d);
        if (jSONObject.optJSONArray("spt") != null) {
            geoPoint.setLongitudeE6(r5.optInt(0));
            geoPoint.setLatitudeE6(r5.optInt(1));
        }
        routeNode.setLocation(CoordUtil.mc2ll(geoPoint));
        return routeNode;
    }

    private List<LatLng> a(JSONArray jSONArray) {
        int length;
        if (jSONArray == null || (length = jSONArray.length()) < 6) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        double dOptInt = 0.0d;
        double dOptInt2 = 0.0d;
        for (int i = 5; i < length; i++) {
            if (i % 2 != 0) {
                dOptInt2 += (double) jSONArray.optInt(i);
            } else {
                dOptInt += (double) jSONArray.optInt(i);
                arrayList.add(CoordUtil.mc2ll(new GeoPoint(dOptInt, dOptInt2)));
            }
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.List<com.baidu.mapapi.search.route.DrivingRouteLine.DrivingStep> a(org.json.JSONArray r20, org.json.JSONArray r21) {
        /*
            Method dump skipped, instruction units count: 330
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.platform.core.d.c.a(org.json.JSONArray, org.json.JSONArray):java.util.List");
    }

    private List<TaxiInfo> b(String str) {
        if (str != null && str.length() > 0) {
            ArrayList arrayList = new ArrayList();
            try {
                JSONArray jSONArray = new JSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    if (jSONObject != null) {
                        TaxiInfo taxiInfo = new TaxiInfo();
                        String strOptString = jSONObject.optString("total_price");
                        if (strOptString == null || strOptString.equals("")) {
                            taxiInfo.setTotalPrice(0.0f);
                        } else {
                            taxiInfo.setTotalPrice(Float.parseFloat(strOptString));
                        }
                        arrayList.add(taxiInfo);
                    }
                }
                return arrayList;
            } catch (JSONException e2) {
                if (Logger.debugEnable()) {
                    e2.printStackTrace();
                }
            }
        }
        return null;
    }

    private List<DrivingRouteLine.DrivingStep> b(JSONArray jSONArray, List<DrivingRouteLine.DrivingStep> list) {
        int length;
        if (jSONArray == null || (length = jSONArray.length()) <= 0 || list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                int iOptInt = jSONObjectOptJSONObject.optInt("n");
                int iOptInt2 = jSONObjectOptJSONObject.optInt("s");
                for (int i2 = 0; i2 < iOptInt; i2++) {
                    int i3 = iOptInt2 + i2;
                    if (i3 < list.size()) {
                        arrayList.add(list.get(i3));
                    }
                }
            }
        }
        return arrayList;
    }

    private boolean b(String str, DrivingRouteResult drivingRouteResult) {
        JSONObject jSONObject;
        JSONArray jSONArray;
        boolean z = false;
        if (str == null || "".equals(str)) {
            return false;
        }
        try {
            JSONObject jSONObject2 = new JSONObject(str);
            JSONObject jSONObjectOptJSONObject = jSONObject2.optJSONObject("result");
            if (jSONObjectOptJSONObject == null) {
                return false;
            }
            int iOptInt = jSONObjectOptJSONObject.optInt(AuthorizationResponseParser.ERROR);
            if (iOptInt != 0) {
                if (iOptInt != 4) {
                    return false;
                }
                drivingRouteResult.error = SearchResult.ERRORNO.ST_EN_TOO_NEAR;
                return true;
            }
            JSONObject jSONObjectOptJSONObject2 = jSONObject2.optJSONObject("cars");
            if (jSONObjectOptJSONObject2 == null) {
                return false;
            }
            JSONObject jSONObjectOptJSONObject3 = jSONObjectOptJSONObject2.optJSONObject("option");
            JSONObject jSONObjectOptJSONObject4 = jSONObjectOptJSONObject2.optJSONObject(FirebaseAnalytics.Param.CONTENT);
            if (jSONObjectOptJSONObject3 == null || jSONObjectOptJSONObject4 == null) {
                return false;
            }
            RouteNode routeNodeA = a(jSONObjectOptJSONObject3.optJSONObject("start"));
            ArrayList arrayList = new ArrayList();
            RouteNode routeNodeA2 = a(jSONObjectOptJSONObject3.optJSONArray("end"), arrayList);
            List<DrivingRouteLine.DrivingStep> listA = a(jSONObjectOptJSONObject4.optJSONArray("steps"), jSONObjectOptJSONObject4.optJSONArray("stepts"));
            ArrayList arrayList2 = new ArrayList();
            JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject4.optJSONArray("routes");
            if (jSONArrayOptJSONArray == null) {
                return false;
            }
            int i = 0;
            while (i < jSONArrayOptJSONArray.length()) {
                DrivingRouteLine drivingRouteLine = new DrivingRouteLine();
                JSONObject jSONObjectOptJSONObject5 = jSONArrayOptJSONArray.optJSONObject(i);
                if (jSONObjectOptJSONObject5 == null) {
                    jSONObject = jSONObjectOptJSONObject4;
                    jSONArray = jSONArrayOptJSONArray;
                } else {
                    JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject5.optJSONArray("legs");
                    if (jSONArrayOptJSONArray2 == null) {
                        return z;
                    }
                    int length = jSONArrayOptJSONArray2.length();
                    ArrayList arrayList3 = new ArrayList();
                    jSONObject = jSONObjectOptJSONObject4;
                    jSONArray = jSONArrayOptJSONArray;
                    int iOptInt2 = 0;
                    int i2 = 0;
                    int iOptInt3 = 0;
                    while (i2 < length) {
                        int i3 = length;
                        JSONObject jSONObjectOptJSONObject6 = jSONArrayOptJSONArray2.optJSONObject(i2);
                        JSONArray jSONArray2 = jSONArrayOptJSONArray2;
                        if (jSONObjectOptJSONObject6 != null) {
                            iOptInt3 += jSONObjectOptJSONObject6.optInt("distance");
                            iOptInt2 += jSONObjectOptJSONObject6.optInt(com.ido.ble.event.stat.one.d.C);
                            List<DrivingRouteLine.DrivingStep> listB = b(jSONObjectOptJSONObject6.optJSONArray("stepis"), listA);
                            if (listB != null) {
                                arrayList3.addAll(listB);
                            }
                        }
                        i2++;
                        length = i3;
                        jSONArrayOptJSONArray2 = jSONArray2;
                    }
                    drivingRouteLine.setStarting(routeNodeA);
                    drivingRouteLine.setTerminal(routeNodeA2);
                    if (arrayList.size() == 0) {
                        drivingRouteLine.setWayPoints(null);
                    } else {
                        drivingRouteLine.setWayPoints(arrayList);
                    }
                    drivingRouteLine.setDistance(iOptInt3);
                    drivingRouteLine.setDuration(iOptInt2);
                    drivingRouteLine.setCongestionDistance(jSONObjectOptJSONObject5.optInt("congestion_length"));
                    drivingRouteLine.setLightNum(jSONObjectOptJSONObject5.optInt("light_num"));
                    if (arrayList3.size() == 0) {
                        drivingRouteLine.setSteps(null);
                    } else {
                        drivingRouteLine.setSteps(arrayList3);
                    }
                    arrayList2.add(drivingRouteLine);
                }
                i++;
                jSONArrayOptJSONArray = jSONArray;
                jSONObjectOptJSONObject4 = jSONObject;
                z = false;
            }
            drivingRouteResult.setRouteLines(arrayList2);
            drivingRouteResult.setTaxiInfos(b(jSONObjectOptJSONObject4.optString("taxis")));
            return true;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private int[] b(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("end");
        JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray(NotificationCompat.CATEGORY_STATUS);
        if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray2 == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int length = jSONArrayOptJSONArray.length();
        int length2 = jSONArrayOptJSONArray2.length();
        int i = 0;
        while (i < length) {
            int iOptInt = jSONArrayOptJSONArray.optInt(i);
            int iOptInt2 = i < length2 ? jSONArrayOptJSONArray2.optInt(i) : 0;
            for (int i2 = 0; i2 < iOptInt; i2++) {
                arrayList.add(Integer.valueOf(iOptInt2));
            }
            i++;
        }
        int[] iArr = new int[arrayList.size()];
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            iArr[i3] = ((Integer) arrayList.get(i3)).intValue();
        }
        return iArr;
    }

    public void a(String str, DrivingRouteResult drivingRouteResult) {
        if (str != null && !str.equals("")) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("SDK_InnerError")) {
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("SDK_InnerError");
                    if (jSONObjectOptJSONObject.has("PermissionCheckError")) {
                        drivingRouteResult.error = SearchResult.ERRORNO.PERMISSION_UNFINISHED;
                        return;
                    } else if (jSONObjectOptJSONObject.has("httpStateError")) {
                        String strOptString = jSONObjectOptJSONObject.optString("httpStateError");
                        drivingRouteResult.error = strOptString.equals("NETWORK_ERROR") ? SearchResult.ERRORNO.NETWORK_ERROR : strOptString.equals("REQUEST_ERROR") ? SearchResult.ERRORNO.REQUEST_ERROR : SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
                        return;
                    }
                }
                if (a(str, drivingRouteResult, false) || b(str, drivingRouteResult)) {
                    return;
                }
                drivingRouteResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                return;
            } catch (Exception unused) {
            }
        }
        drivingRouteResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
    }
}