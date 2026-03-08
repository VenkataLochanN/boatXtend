package com.baidu.platform.core.b;

import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
import com.google.firebase.analytics.FirebaseAnalytics;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class b extends com.baidu.platform.base.d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3933b = b.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f3934c;

    private LatLng a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        double dOptDouble = jSONObject.optDouble("lat");
        double dOptDouble2 = jSONObject.optDouble("lng");
        return SDKInitializer.getCoordType() == CoordType.GCJ02 ? CoordTrans.baiduToGcj(new LatLng(dOptDouble, dOptDouble2)) : new LatLng(dOptDouble, dOptDouble2);
    }

    private boolean a(String str, GeoCodeResult geoCodeResult) {
        if (TextUtils.isEmpty(str) || geoCodeResult == null) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int iOptInt = jSONObject.optInt(NotificationCompat.CATEGORY_STATUS);
            if (iOptInt != 0) {
                geoCodeResult.error = iOptInt != 1 ? iOptInt != 2 ? SearchResult.ERRORNO.RESULT_NOT_FOUND : SearchResult.ERRORNO.SEARCH_OPTION_ERROR : SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
                return false;
            }
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("result");
            if (jSONObjectOptJSONObject == null) {
                geoCodeResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                return false;
            }
            geoCodeResult.setLocation(a(jSONObjectOptJSONObject.optJSONObject(FirebaseAnalytics.Param.LOCATION)));
            geoCodeResult.setAddress(this.f3934c);
            geoCodeResult.setPrecise(jSONObjectOptJSONObject.optInt("precise"));
            geoCodeResult.setConfidence(jSONObjectOptJSONObject.optInt("confidence"));
            geoCodeResult.setLevel(jSONObjectOptJSONObject.optString(FirebaseAnalytics.Param.LEVEL));
            geoCodeResult.error = SearchResult.ERRORNO.NO_ERROR;
            return true;
        } catch (JSONException e2) {
            geoCodeResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
            Log.e(f3933b, "Parse GeoCodeResult catch JSONException", e2);
            return true;
        }
    }

    @Override // com.baidu.platform.base.d
    public SearchResult a(String str) {
        SearchResult.ERRORNO errorno;
        JSONObject jSONObject;
        GeoCodeResult geoCodeResult = new GeoCodeResult();
        if (str == null || str.equals("")) {
            errorno = SearchResult.ERRORNO.RESULT_NOT_FOUND;
        } else {
            try {
                jSONObject = new JSONObject(str);
            } catch (JSONException e2) {
                Log.e(f3933b, "JSONException caught", e2);
            }
            if (!jSONObject.has("SDK_InnerError")) {
                if (!a(str, geoCodeResult, false) && !a(str, geoCodeResult)) {
                    geoCodeResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                }
                return geoCodeResult;
            }
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("SDK_InnerError");
            if (jSONObjectOptJSONObject.has("PermissionCheckError")) {
                errorno = SearchResult.ERRORNO.PERMISSION_UNFINISHED;
            } else {
                if (jSONObjectOptJSONObject.has("httpStateError")) {
                    String strOptString = jSONObjectOptJSONObject.optString("httpStateError");
                    byte b2 = -1;
                    int iHashCode = strOptString.hashCode();
                    if (iHashCode != -879828873) {
                        if (iHashCode == 1470557208 && strOptString.equals("REQUEST_ERROR")) {
                            b2 = 1;
                        }
                    } else if (strOptString.equals("NETWORK_ERROR")) {
                        b2 = 0;
                    }
                    geoCodeResult.error = b2 != 0 ? b2 != 1 ? SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR : SearchResult.ERRORNO.REQUEST_ERROR : SearchResult.ERRORNO.NETWORK_ERROR;
                    return geoCodeResult;
                }
                errorno = SearchResult.ERRORNO.RESULT_NOT_FOUND;
            }
        }
        geoCodeResult.error = errorno;
        return geoCodeResult;
    }

    @Override // com.baidu.platform.base.d
    public void a(SearchResult searchResult, Object obj) {
        if (obj == null || !(obj instanceof OnGetGeoCoderResultListener)) {
            return;
        }
        ((OnGetGeoCoderResultListener) obj).onGetGeoCodeResult((GeoCodeResult) searchResult);
    }

    public void b(String str) {
        this.f3934c = str;
    }
}