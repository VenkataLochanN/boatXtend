package com.baidu.platform.core.d;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class f extends com.baidu.platform.base.d {
    private LatLng a(JSONObject jSONObject, String str) {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(str);
        if (jSONArrayOptJSONArray == null) {
            return null;
        }
        GeoPoint geoPoint = new GeoPoint(0.0d, 0.0d);
        geoPoint.setLatitudeE6(jSONArrayOptJSONArray.optDouble(1));
        geoPoint.setLongitudeE6(jSONArrayOptJSONArray.optDouble(0));
        return CoordUtil.mc2ll(geoPoint);
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00c3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean a(java.lang.String r27, com.baidu.mapapi.search.route.IndoorRouteResult r28) {
        /*
            Method dump skipped, instruction units count: 555
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.platform.core.d.f.a(java.lang.String, com.baidu.mapapi.search.route.IndoorRouteResult):boolean");
    }

    @Override // com.baidu.platform.base.d
    public SearchResult a(String str) {
        IndoorRouteResult indoorRouteResult = new IndoorRouteResult();
        if (str != null && !str.equals("")) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("SDK_InnerError")) {
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("SDK_InnerError");
                    if (jSONObjectOptJSONObject.has("PermissionCheckError")) {
                        indoorRouteResult.error = SearchResult.ERRORNO.PERMISSION_UNFINISHED;
                        return indoorRouteResult;
                    }
                    if (jSONObjectOptJSONObject.has("httpStateError")) {
                        String strOptString = jSONObjectOptJSONObject.optString("httpStateError");
                        indoorRouteResult.error = strOptString.equals("NETWORK_ERROR") ? SearchResult.ERRORNO.NETWORK_ERROR : strOptString.equals("REQUEST_ERROR") ? SearchResult.ERRORNO.REQUEST_ERROR : SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
                        return indoorRouteResult;
                    }
                }
                if (!a(str, indoorRouteResult)) {
                    indoorRouteResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                }
                return indoorRouteResult;
            } catch (Exception unused) {
            }
        }
        indoorRouteResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
        return indoorRouteResult;
    }

    @Override // com.baidu.platform.base.d
    public void a(SearchResult searchResult, Object obj) {
        if (obj == null || !(obj instanceof OnGetRoutePlanResultListener)) {
            return;
        }
        ((OnGetRoutePlanResultListener) obj).onGetIndoorRouteResult((IndoorRouteResult) searchResult);
    }
}