package com.baidu.platform.core.b;

import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.life.module.user.country.CountryChooseActivity;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class e extends com.baidu.platform.base.d {
    private PoiInfo.ParentPoiInfo a(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() == 0) {
            return null;
        }
        PoiInfo.ParentPoiInfo parentPoiInfo = new PoiInfo.ParentPoiInfo();
        parentPoiInfo.setParentPoiAddress(jSONObject.optString("addr"));
        parentPoiInfo.setParentPoiDirection(jSONObject.optString("direction"));
        parentPoiInfo.setParentPoiDistance(jSONObject.optInt("distance"));
        parentPoiInfo.setParentPoiName(jSONObject.optString(AppMeasurementSdk.ConditionalUserProperty.NAME));
        parentPoiInfo.setParentPoiTag(jSONObject.optString("tag"));
        parentPoiInfo.setParentPoiUid(jSONObject.optString("uid"));
        parentPoiInfo.setParentPoiLocation(c(jSONObject, "point"));
        return parentPoiInfo;
    }

    private ReverseGeoCodeResult.AddressComponent a(JSONObject jSONObject, String str) {
        JSONObject jSONObjectOptJSONObject;
        if (jSONObject == null || TextUtils.isEmpty(str) || (jSONObjectOptJSONObject = jSONObject.optJSONObject(str)) == null) {
            return null;
        }
        ReverseGeoCodeResult.AddressComponent addressComponent = new ReverseGeoCodeResult.AddressComponent();
        addressComponent.city = jSONObjectOptJSONObject.optString("city");
        addressComponent.setTown(jSONObjectOptJSONObject.optString("town"));
        addressComponent.district = jSONObjectOptJSONObject.optString("district");
        addressComponent.province = jSONObjectOptJSONObject.optString("province");
        addressComponent.adcode = jSONObjectOptJSONObject.optInt("adcode");
        addressComponent.street = jSONObjectOptJSONObject.optString("street");
        addressComponent.streetNumber = jSONObjectOptJSONObject.optString("street_number");
        addressComponent.countryName = jSONObjectOptJSONObject.optString(CountryChooseActivity.COUNTRY);
        addressComponent.countryCode = jSONObjectOptJSONObject.optInt("country_code");
        addressComponent.setDirection(jSONObjectOptJSONObject.optString("direction"));
        addressComponent.setDistance(jSONObjectOptJSONObject.optString("distance"));
        return addressComponent;
    }

    private List<PoiInfo> a(JSONObject jSONObject, String str, String str2) {
        JSONArray jSONArrayOptJSONArray;
        if (jSONObject == null || str == null || "".equals(str) || (jSONArrayOptJSONArray = jSONObject.optJSONArray(str)) == null || jSONArrayOptJSONArray.length() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                PoiInfo poiInfo = new PoiInfo();
                poiInfo.setAddress(jSONObjectOptJSONObject.optString("addr"));
                poiInfo.setPhoneNum(jSONObjectOptJSONObject.optString("tel"));
                poiInfo.setUid(jSONObjectOptJSONObject.optString("uid"));
                poiInfo.setPostCode(jSONObjectOptJSONObject.optString("zip"));
                poiInfo.setName(jSONObjectOptJSONObject.optString(AppMeasurementSdk.ConditionalUserProperty.NAME));
                poiInfo.setLocation(c(jSONObjectOptJSONObject, "point"));
                poiInfo.setCity(str2);
                poiInfo.setDirection(jSONObjectOptJSONObject.optString("direction"));
                poiInfo.setDistance(jSONObjectOptJSONObject.optInt("distance"));
                poiInfo.setParentPoi(a(jSONObjectOptJSONObject.optJSONObject("parent_poi")));
                arrayList.add(poiInfo);
            }
        }
        return arrayList;
    }

    private boolean a(String str, ReverseGeoCodeResult reverseGeoCodeResult) {
        if (str != null) {
            try {
                if (str.length() > 0) {
                    JSONObject jSONObject = new JSONObject(str);
                    int iOptInt = jSONObject.optInt(NotificationCompat.CATEGORY_STATUS);
                    if (iOptInt != 0) {
                        reverseGeoCodeResult.error = iOptInt != 1 ? iOptInt != 2 ? SearchResult.ERRORNO.RESULT_NOT_FOUND : SearchResult.ERRORNO.SEARCH_OPTION_ERROR : SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
                        return false;
                    }
                    if (a(jSONObject, reverseGeoCodeResult)) {
                        return true;
                    }
                    reverseGeoCodeResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                    return false;
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
                reverseGeoCodeResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                return false;
            }
        }
        reverseGeoCodeResult.error = SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
        return false;
    }

    private boolean a(JSONObject jSONObject, ReverseGeoCodeResult reverseGeoCodeResult) {
        JSONObject jSONObjectOptJSONObject;
        if (jSONObject == null || (jSONObjectOptJSONObject = jSONObject.optJSONObject("result")) == null) {
            return false;
        }
        reverseGeoCodeResult.setCityCode(jSONObjectOptJSONObject.optInt("cityCode"));
        reverseGeoCodeResult.setAddress(jSONObjectOptJSONObject.optString("formatted_address"));
        reverseGeoCodeResult.setBusinessCircle(jSONObjectOptJSONObject.optString("business"));
        reverseGeoCodeResult.setAddressDetail(a(jSONObjectOptJSONObject, "addressComponent"));
        reverseGeoCodeResult.setLocation(d(jSONObjectOptJSONObject, FirebaseAnalytics.Param.LOCATION));
        reverseGeoCodeResult.setPoiList(a(jSONObjectOptJSONObject, "pois", reverseGeoCodeResult.getAddressDetail() != null ? reverseGeoCodeResult.getAddressDetail().city : ""));
        reverseGeoCodeResult.setSematicDescription(jSONObjectOptJSONObject.optString("sematic_description"));
        reverseGeoCodeResult.setPoiRegionsInfoList(b(jSONObjectOptJSONObject, "poiRegions"));
        reverseGeoCodeResult.error = SearchResult.ERRORNO.NO_ERROR;
        return true;
    }

    private List<ReverseGeoCodeResult.PoiRegionsInfo> b(JSONObject jSONObject, String str) {
        JSONArray jSONArrayOptJSONArray;
        if (jSONObject == null || TextUtils.isEmpty(str) || (jSONArrayOptJSONArray = jSONObject.optJSONArray(str)) == null || jSONArrayOptJSONArray.length() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                ReverseGeoCodeResult.PoiRegionsInfo poiRegionsInfo = new ReverseGeoCodeResult.PoiRegionsInfo();
                poiRegionsInfo.setDirectionDesc(jSONObjectOptJSONObject.optString("direction_desc"));
                poiRegionsInfo.setRegionName(jSONObjectOptJSONObject.optString(AppMeasurementSdk.ConditionalUserProperty.NAME));
                poiRegionsInfo.setRegionTag(jSONObjectOptJSONObject.optString("tag"));
                arrayList.add(poiRegionsInfo);
            }
        }
        return arrayList;
    }

    private LatLng c(JSONObject jSONObject, String str) {
        JSONObject jSONObjectOptJSONObject;
        if (jSONObject == null || str == null || "".equals(str) || (jSONObjectOptJSONObject = jSONObject.optJSONObject(str)) == null) {
            return null;
        }
        LatLng latLng = new LatLng(jSONObjectOptJSONObject.optDouble("y"), jSONObjectOptJSONObject.optDouble("x"));
        return SDKInitializer.getCoordType() == CoordType.GCJ02 ? CoordTrans.baiduToGcj(latLng) : latLng;
    }

    private LatLng d(JSONObject jSONObject, String str) {
        JSONObject jSONObjectOptJSONObject;
        if (jSONObject == null || str == null || "".equals(str) || (jSONObjectOptJSONObject = jSONObject.optJSONObject(str)) == null) {
            return null;
        }
        LatLng latLng = new LatLng(jSONObjectOptJSONObject.optDouble("lat"), jSONObjectOptJSONObject.optDouble("lng"));
        return SDKInitializer.getCoordType() == CoordType.GCJ02 ? CoordTrans.baiduToGcj(latLng) : latLng;
    }

    @Override // com.baidu.platform.base.d
    public SearchResult a(String str) {
        ReverseGeoCodeResult reverseGeoCodeResult = new ReverseGeoCodeResult();
        if (str != null && !str.equals("")) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("SDK_InnerError")) {
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("SDK_InnerError");
                    if (jSONObjectOptJSONObject.has("PermissionCheckError")) {
                        reverseGeoCodeResult.error = SearchResult.ERRORNO.PERMISSION_UNFINISHED;
                        return reverseGeoCodeResult;
                    }
                    if (jSONObjectOptJSONObject.has("httpStateError")) {
                        String strOptString = jSONObjectOptJSONObject.optString("httpStateError");
                        reverseGeoCodeResult.error = strOptString.equals("NETWORK_ERROR") ? SearchResult.ERRORNO.NETWORK_ERROR : strOptString.equals("REQUEST_ERROR") ? SearchResult.ERRORNO.REQUEST_ERROR : SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
                        return reverseGeoCodeResult;
                    }
                }
                if (!a(str, (SearchResult) reverseGeoCodeResult, true)) {
                    a(str, reverseGeoCodeResult);
                }
                return reverseGeoCodeResult;
            } catch (Exception unused) {
            }
        }
        reverseGeoCodeResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
        return reverseGeoCodeResult;
    }

    @Override // com.baidu.platform.base.d
    public void a(SearchResult searchResult, Object obj) {
        if (obj == null || !(obj instanceof OnGetGeoCoderResultListener)) {
            return;
        }
        ((OnGetGeoCoderResultListener) obj).onGetReverseGeoCodeResult((ReverseGeoCodeResult) searchResult);
    }
}