package com.baidu.platform.core.c;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiDetailInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class d extends com.baidu.platform.base.d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3936b = d.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f3937c = false;

    private LatLng a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        double dOptDouble = jSONObject.optDouble("lat");
        double dOptDouble2 = jSONObject.optDouble("lng");
        return SDKInitializer.getCoordType() == CoordType.GCJ02 ? CoordTrans.baiduToGcj(new LatLng(dOptDouble, dOptDouble2)) : new LatLng(dOptDouble, dOptDouble2);
    }

    private boolean a(String str, SearchResult searchResult) {
        JSONArray jSONArrayOptJSONArray;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.length() == 0 || jSONObject.optInt(NotificationCompat.CATEGORY_STATUS) != 0 || (jSONArrayOptJSONArray = jSONObject.optJSONArray("result")) == null || jSONArrayOptJSONArray.length() == 0) {
                return false;
            }
            return this.f3937c ? a(jSONArrayOptJSONArray, (PoiDetailSearchResult) searchResult) : a(jSONArrayOptJSONArray, (PoiDetailResult) searchResult);
        } catch (JSONException e2) {
            Log.e(f3936b, "Parse detail search result error", e2);
            return false;
        }
    }

    private boolean a(JSONArray jSONArray, PoiDetailResult poiDetailResult) {
        JSONObject jSONObject = (JSONObject) jSONArray.opt(0);
        if (jSONObject == null || jSONObject.length() == 0) {
            return false;
        }
        poiDetailResult.setName(jSONObject.optString(AppMeasurementSdk.ConditionalUserProperty.NAME));
        poiDetailResult.setLocation(a(jSONObject.optJSONObject(FirebaseAnalytics.Param.LOCATION)));
        poiDetailResult.setAddress(jSONObject.optString("address"));
        poiDetailResult.setTelephone(jSONObject.optString("telephone"));
        poiDetailResult.setUid(jSONObject.optString("uid"));
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("detail_info");
        if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.length() != 0) {
            poiDetailResult.setTag(jSONObjectOptJSONObject.optString("tag"));
            poiDetailResult.setDetailUrl(jSONObjectOptJSONObject.optString("detail_url"));
            poiDetailResult.setType(jSONObjectOptJSONObject.optString("type"));
            poiDetailResult.setPrice(jSONObjectOptJSONObject.optDouble(FirebaseAnalytics.Param.PRICE, 0.0d));
            poiDetailResult.setOverallRating(jSONObjectOptJSONObject.optDouble("overall_rating", 0.0d));
            poiDetailResult.setTasteRating(jSONObjectOptJSONObject.optDouble("taste_rating", 0.0d));
            poiDetailResult.setServiceRating(jSONObjectOptJSONObject.optDouble("service_rating", 0.0d));
            poiDetailResult.setEnvironmentRating(jSONObjectOptJSONObject.optDouble("environment_rating", 0.0d));
            poiDetailResult.setFacilityRating(jSONObjectOptJSONObject.optDouble("facility_rating", 0.0d));
            poiDetailResult.setHygieneRating(jSONObjectOptJSONObject.optDouble("hygiene_rating", 0.0d));
            poiDetailResult.setTechnologyRating(jSONObjectOptJSONObject.optDouble("technology_rating", 0.0d));
            poiDetailResult.setImageNum(jSONObjectOptJSONObject.optInt("image_num"));
            poiDetailResult.setGrouponNum(jSONObjectOptJSONObject.optInt("groupon_num", 0));
            poiDetailResult.setCommentNum(jSONObjectOptJSONObject.optInt("comment_num", 0));
            poiDetailResult.setDiscountNum(jSONObjectOptJSONObject.optInt("discount_num", 0));
            poiDetailResult.setFavoriteNum(jSONObjectOptJSONObject.optInt("favorite_num", 0));
            poiDetailResult.setCheckinNum(jSONObjectOptJSONObject.optInt("checkin_num", 0));
            poiDetailResult.setShopHours(jSONObjectOptJSONObject.optString("shop_hours"));
        }
        poiDetailResult.error = SearchResult.ERRORNO.NO_ERROR;
        return true;
    }

    private boolean a(JSONArray jSONArray, PoiDetailSearchResult poiDetailSearchResult) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = (JSONObject) jSONArray.opt(i);
            if (jSONObject != null && jSONObject.length() != 0) {
                PoiDetailInfo poiDetailInfo = new PoiDetailInfo();
                poiDetailInfo.setName(jSONObject.optString(AppMeasurementSdk.ConditionalUserProperty.NAME));
                poiDetailInfo.setLocation(a(jSONObject.optJSONObject(FirebaseAnalytics.Param.LOCATION)));
                poiDetailInfo.setAddress(jSONObject.optString("address"));
                poiDetailInfo.setProvince(jSONObject.optString("province"));
                poiDetailInfo.setCity(jSONObject.optString("city"));
                poiDetailInfo.setArea(jSONObject.optString("area"));
                poiDetailInfo.setTelephone(jSONObject.optString("telephone"));
                poiDetailInfo.setUid(jSONObject.optString("uid"));
                poiDetailInfo.setStreetId(jSONObject.optString("setStreetId"));
                poiDetailInfo.setDetail(jSONObject.optString("detail"));
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("detail_info");
                if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.length() != 0) {
                    poiDetailInfo.setDistance(jSONObjectOptJSONObject.optInt("distance", 0));
                    poiDetailInfo.setType(jSONObjectOptJSONObject.optString("type"));
                    poiDetailInfo.setTag(jSONObjectOptJSONObject.optString("tag"));
                    poiDetailInfo.setDetailUrl(jSONObjectOptJSONObject.optString("detail_url"));
                    poiDetailInfo.setPrice(jSONObjectOptJSONObject.optDouble(FirebaseAnalytics.Param.PRICE, 0.0d));
                    poiDetailInfo.setShopHours(jSONObjectOptJSONObject.optString("shop_hours"));
                    poiDetailInfo.setOverallRating(jSONObjectOptJSONObject.optDouble("overall_rating", 0.0d));
                    poiDetailInfo.setTasteRating(jSONObjectOptJSONObject.optDouble("taste_rating", 0.0d));
                    poiDetailInfo.setServiceRating(jSONObjectOptJSONObject.optDouble("service_rating", 0.0d));
                    poiDetailInfo.setEnvironmentRating(jSONObjectOptJSONObject.optDouble("environment_rating", 0.0d));
                    poiDetailInfo.setFacilityRating(jSONObjectOptJSONObject.optDouble("facility_rating", 0.0d));
                    poiDetailInfo.setHygieneRating(jSONObjectOptJSONObject.optDouble("hygiene_rating", 0.0d));
                    poiDetailInfo.setTechnologyRating(jSONObjectOptJSONObject.optDouble("technology_rating", 0.0d));
                    poiDetailInfo.setImageNum(jSONObjectOptJSONObject.optInt("image_num"));
                    poiDetailInfo.setGrouponNum(jSONObjectOptJSONObject.optInt("groupon_num", 0));
                    poiDetailInfo.setCommentNum(jSONObjectOptJSONObject.optInt("comment_num", 0));
                    poiDetailInfo.setDiscountNum(jSONObjectOptJSONObject.optInt("discount_num", 0));
                    poiDetailInfo.setFavoriteNum(jSONObjectOptJSONObject.optInt("favorite_num", 0));
                    poiDetailInfo.setCheckinNum(jSONObjectOptJSONObject.optInt("checkin_num", 0));
                }
                arrayList.add(poiDetailInfo);
            }
        }
        poiDetailSearchResult.setPoiDetailInfoList(arrayList);
        poiDetailSearchResult.error = SearchResult.ERRORNO.NO_ERROR;
        return true;
    }

    @Override // com.baidu.platform.base.d
    public SearchResult a(String str) {
        SearchResult.ERRORNO errorno;
        SearchResult poiDetailSearchResult = this.f3937c ? new PoiDetailSearchResult() : new PoiDetailResult();
        if (str == null || str.isEmpty()) {
            errorno = SearchResult.ERRORNO.RESULT_NOT_FOUND;
        } else {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.length() == 0) {
                    poiDetailSearchResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                    return poiDetailSearchResult;
                }
                if (!jSONObject.has("SDK_InnerError")) {
                    if (!a(str, poiDetailSearchResult)) {
                        poiDetailSearchResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                    }
                    return poiDetailSearchResult;
                }
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("SDK_InnerError");
                if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.length() != 0) {
                    if (!jSONObjectOptJSONObject.has("PermissionCheckError")) {
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
                            poiDetailSearchResult.error = b2 != 0 ? b2 != 1 ? SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR : SearchResult.ERRORNO.REQUEST_ERROR : SearchResult.ERRORNO.NETWORK_ERROR;
                        }
                        return poiDetailSearchResult;
                    }
                    errorno = SearchResult.ERRORNO.PERMISSION_UNFINISHED;
                }
            } catch (JSONException e2) {
                Log.e(f3936b, "Parse detail search result failed", e2);
            }
            errorno = SearchResult.ERRORNO.RESULT_NOT_FOUND;
        }
        poiDetailSearchResult.error = errorno;
        return poiDetailSearchResult;
    }

    @Override // com.baidu.platform.base.d
    public void a(SearchResult searchResult, Object obj) {
        if (obj == null || !(obj instanceof OnGetPoiSearchResultListener)) {
            return;
        }
        OnGetPoiSearchResultListener onGetPoiSearchResultListener = (OnGetPoiSearchResultListener) obj;
        if (this.f3937c) {
            onGetPoiSearchResultListener.onGetPoiDetailResult((PoiDetailSearchResult) searchResult);
        } else {
            onGetPoiSearchResultListener.onGetPoiDetailResult((PoiDetailResult) searchResult);
        }
    }

    void a(boolean z) {
        this.f3937c = z;
    }
}