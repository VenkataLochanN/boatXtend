package com.baidu.platform.core.d;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.TaxiInfo;
import com.baidu.mapapi.search.core.VehicleInfo;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.ido.common.constant.LanguageRegion;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class m extends k {
    private RouteNode a(JSONObject jSONObject, String str) {
        if (jSONObject == null || str == null || "".equals(str)) {
            return null;
        }
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(str);
        RouteNode routeNode = new RouteNode();
        routeNode.setTitle(jSONObjectOptJSONObject.optString("wd"));
        routeNode.setUid(jSONObjectOptJSONObject.optString("uid"));
        routeNode.setLocation(CoordUtil.decodeLocation(jSONObjectOptJSONObject.optString(LanguageRegion.PT)));
        return routeNode;
    }

    private TaxiInfo a(JSONObject jSONObject) {
        float fOptDouble;
        float fOptDouble2;
        float fOptDouble3;
        if (jSONObject == null) {
            return null;
        }
        TaxiInfo taxiInfo = new TaxiInfo();
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("detail");
        if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
            return null;
        }
        int length = jSONArrayOptJSONArray.length();
        int i = 0;
        while (true) {
            fOptDouble = 0.0f;
            if (i >= length) {
                fOptDouble2 = 0.0f;
                fOptDouble3 = 0.0f;
                break;
            }
            JSONObject jSONObject2 = (JSONObject) jSONArrayOptJSONArray.opt(i);
            if (jSONObject2 != null && jSONObject2.optString("desc").contains("白天")) {
                fOptDouble2 = (float) jSONObject2.optDouble("km_price");
                fOptDouble3 = (float) jSONObject2.optDouble("start_price");
                fOptDouble = (float) jSONObject2.optDouble("total_price");
                break;
            }
            i++;
        }
        taxiInfo.setDesc(jSONObject.optString("remark"));
        taxiInfo.setDistance(jSONObject.optInt("distance"));
        taxiInfo.setDuration(jSONObject.optInt(com.ido.ble.event.stat.one.d.C));
        taxiInfo.setTotalPrice(fOptDouble);
        taxiInfo.setStartPrice(fOptDouble3);
        taxiInfo.setPerKMPrice(fOptDouble2);
        return taxiInfo;
    }

    private String b(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        char[] charArray = str.toCharArray();
        boolean z = false;
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == '<') {
                z = true;
            } else if (charArray[i] == '>') {
                z = false;
            } else if (!z) {
                sb.append(charArray[i]);
            }
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x01c8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean b(java.lang.String r19, com.baidu.mapapi.search.route.TransitRouteResult r20) {
        /*
            Method dump skipped, instruction units count: 485
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.platform.core.d.m.b(java.lang.String, com.baidu.mapapi.search.route.TransitRouteResult):boolean");
    }

    private VehicleInfo c(String str) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e2) {
            e2.printStackTrace();
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        VehicleInfo vehicleInfo = new VehicleInfo();
        vehicleInfo.setZonePrice(jSONObject.optInt("zone_price"));
        vehicleInfo.setTotalPrice(jSONObject.optInt("total_price"));
        vehicleInfo.setTitle(jSONObject.optString(AppMeasurementSdk.ConditionalUserProperty.NAME));
        vehicleInfo.setPassStationNum(jSONObject.optInt("stop_num"));
        vehicleInfo.setUid(jSONObject.optString("uid"));
        return vehicleInfo;
    }

    public void a(String str, TransitRouteResult transitRouteResult) {
        if (str != null && !str.equals("")) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("SDK_InnerError")) {
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("SDK_InnerError");
                    if (jSONObjectOptJSONObject.has("PermissionCheckError")) {
                        transitRouteResult.error = SearchResult.ERRORNO.PERMISSION_UNFINISHED;
                        return;
                    } else if (jSONObjectOptJSONObject.has("httpStateError")) {
                        String strOptString = jSONObjectOptJSONObject.optString("httpStateError");
                        transitRouteResult.error = strOptString.equals("NETWORK_ERROR") ? SearchResult.ERRORNO.NETWORK_ERROR : strOptString.equals("REQUEST_ERROR") ? SearchResult.ERRORNO.REQUEST_ERROR : SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
                        return;
                    }
                }
                if (a(str, transitRouteResult, false) || b(str, transitRouteResult)) {
                    return;
                }
                transitRouteResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                return;
            } catch (Exception unused) {
            }
        }
        transitRouteResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
    }
}