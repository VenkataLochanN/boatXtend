package com.baidu.platform.core.c;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiChildrenInfo;
import com.baidu.mapapi.search.core.PoiDetailInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
import com.baidu.platform.base.SearchType;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class g extends com.baidu.platform.base.d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3939b = g.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3940c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3941d;

    g(int i, int i2) {
        this.f3940c = i;
        this.f3941d = i2;
    }

    private LatLng a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        double dOptDouble = jSONObject.optDouble("lat");
        double dOptDouble2 = jSONObject.optDouble("lng");
        return SDKInitializer.getCoordType() == CoordType.GCJ02 ? CoordTrans.baiduToGcj(new LatLng(dOptDouble, dOptDouble2)) : new LatLng(dOptDouble, dOptDouble2);
    }

    private boolean a(String str, PoiResult poiResult) {
        if (str != null && !str.equals("") && !str.isEmpty()) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                int iOptInt = jSONObject.optInt(NotificationCompat.CATEGORY_STATUS);
                if (iOptInt == 0) {
                    return a(jSONObject, poiResult);
                }
                poiResult.error = iOptInt != 1 ? iOptInt != 2 ? SearchResult.ERRORNO.RESULT_NOT_FOUND : SearchResult.ERRORNO.SEARCH_OPTION_ERROR : SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
                return false;
            } catch (JSONException e2) {
                Log.e(f3939b, "Parse poi search failed", e2);
                poiResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
            }
        }
        return false;
    }

    private boolean a(JSONObject jSONObject, PoiResult poiResult) {
        if (jSONObject != null && jSONObject.length() != 0) {
            poiResult.error = SearchResult.ERRORNO.NO_ERROR;
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("results");
            if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
                int iOptInt = jSONObject.optInt("total");
                poiResult.setTotalPoiNum(iOptInt);
                int length = jSONArrayOptJSONArray.length();
                poiResult.setCurrentPageCapacity(length);
                poiResult.setCurrentPageNum(this.f3940c);
                if (length != 0) {
                    int i = this.f3941d;
                    poiResult.setTotalPageNum((iOptInt / i) + (iOptInt % i > 0 ? 1 : 0));
                }
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                    JSONObject jSONObject2 = (JSONObject) jSONArrayOptJSONArray.opt(i2);
                    if (jSONObject2 != null && jSONObject2.length() != 0) {
                        PoiInfo poiInfo = new PoiInfo();
                        poiInfo.setName(jSONObject2.optString(AppMeasurementSdk.ConditionalUserProperty.NAME));
                        poiInfo.setAddress(jSONObject2.optString("address"));
                        poiInfo.setProvince(jSONObject2.optString("province"));
                        poiInfo.setCity(jSONObject2.optString("city"));
                        poiInfo.setArea(jSONObject2.optString("area"));
                        poiInfo.setStreetId(jSONObject2.optString("street_id"));
                        poiInfo.setUid(jSONObject2.optString("uid"));
                        poiInfo.setPhoneNum(jSONObject2.optString("telephone"));
                        poiInfo.setDetail(jSONObject2.optInt("detail"));
                        poiInfo.setLocation(a(jSONObject2.optJSONObject(FirebaseAnalytics.Param.LOCATION)));
                        String strOptString = jSONObject2.optString("detail_info");
                        if (strOptString != null && strOptString.length() != 0) {
                            poiInfo.setPoiDetailInfo(b(strOptString));
                        }
                        arrayList.add(poiInfo);
                    }
                }
                poiResult.setPoiInfo(arrayList);
                return true;
            }
            poiResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
        }
        return false;
    }

    private PoiDetailInfo b(String str) {
        PoiDetailInfo poiDetailInfo = new PoiDetailInfo();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.length() == 0) {
                return null;
            }
            poiDetailInfo.setDistance(jSONObject.optInt("distance", 0));
            poiDetailInfo.setTag(jSONObject.optString("tag"));
            poiDetailInfo.setDetailUrl(jSONObject.optString("detail_url"));
            poiDetailInfo.setType(jSONObject.optString("type"));
            poiDetailInfo.setPrice(jSONObject.optDouble(FirebaseAnalytics.Param.PRICE, 0.0d));
            poiDetailInfo.setOverallRating(jSONObject.optDouble("overall_rating", 0.0d));
            poiDetailInfo.setTasteRating(jSONObject.optDouble("taste_rating", 0.0d));
            poiDetailInfo.setServiceRating(jSONObject.optDouble("service_rating", 0.0d));
            poiDetailInfo.setEnvironmentRating(jSONObject.optDouble("environment_rating", 0.0d));
            poiDetailInfo.setFacilityRating(jSONObject.optDouble("facility_rating", 0.0d));
            poiDetailInfo.setHygieneRating(jSONObject.optDouble("hygiene_rating", 0.0d));
            poiDetailInfo.setTechnologyRating(jSONObject.optDouble("technology_rating", 0.0d));
            poiDetailInfo.setImageNum(jSONObject.optInt("image_num"));
            poiDetailInfo.setGrouponNum(jSONObject.optInt("groupon_num"));
            poiDetailInfo.setCommentNum(jSONObject.optInt("comment_num"));
            poiDetailInfo.setDiscountNum(jSONObject.optInt("discount_num"));
            poiDetailInfo.setFavoriteNum(jSONObject.optInt("favorite_num"));
            poiDetailInfo.setCheckinNum(jSONObject.optInt("checkin_num"));
            poiDetailInfo.setShopHours(jSONObject.optString("shop_hours"));
            poiDetailInfo.naviLocation = a(jSONObject.optJSONObject("navi_location"));
            SearchType searchTypeA = a();
            if (SearchType.POI_IN_CITY_SEARCH == searchTypeA || SearchType.POI_NEAR_BY_SEARCH == searchTypeA) {
                poiDetailInfo.setPoiChildrenInfoList(b(jSONObject));
            }
            return poiDetailInfo;
        } catch (JSONException e2) {
            Log.e(f3939b, "Parse poi search detail info failed", e2);
            return null;
        }
    }

    private List<PoiChildrenInfo> b(JSONObject jSONObject) {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("children");
        if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.length() != 0) {
                PoiChildrenInfo poiChildrenInfo = new PoiChildrenInfo();
                poiChildrenInfo.setUid(jSONObjectOptJSONObject.optString("uid"));
                poiChildrenInfo.setName(jSONObjectOptJSONObject.optString(AppMeasurementSdk.ConditionalUserProperty.NAME));
                poiChildrenInfo.setShowName(jSONObjectOptJSONObject.optString("show_name"));
                poiChildrenInfo.setTag(jSONObjectOptJSONObject.optString("tag"));
                poiChildrenInfo.setLocation(a(jSONObjectOptJSONObject.optJSONObject(FirebaseAnalytics.Param.LOCATION)));
                poiChildrenInfo.setAddress(jSONObjectOptJSONObject.optString("address"));
                arrayList.add(poiChildrenInfo);
            }
        }
        return arrayList;
    }

    @Override // com.baidu.platform.base.d
    public SearchResult a(String str) {
        SearchResult.ERRORNO errorno;
        PoiResult poiResult = new PoiResult();
        if (str == null || str.equals("") || str.isEmpty()) {
            errorno = SearchResult.ERRORNO.RESULT_NOT_FOUND;
        } else {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("SDK_InnerError")) {
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("SDK_InnerError");
                    if (jSONObjectOptJSONObject.has("PermissionCheckError")) {
                        errorno = SearchResult.ERRORNO.PERMISSION_UNFINISHED;
                    } else if (jSONObjectOptJSONObject.has("httpStateError")) {
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
                        poiResult.error = b2 != 0 ? b2 != 1 ? SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR : SearchResult.ERRORNO.REQUEST_ERROR : SearchResult.ERRORNO.NETWORK_ERROR;
                        return poiResult;
                    }
                }
                if (a(str, poiResult, false)) {
                    return poiResult;
                }
                poiResult.error = a(str, poiResult) ? SearchResult.ERRORNO.NO_ERROR : SearchResult.ERRORNO.RESULT_NOT_FOUND;
                return poiResult;
            } catch (JSONException e2) {
                Log.e(f3939b, "Parse poi search error", e2);
                errorno = SearchResult.ERRORNO.RESULT_NOT_FOUND;
            }
        }
        poiResult.error = errorno;
        return poiResult;
    }

    @Override // com.baidu.platform.base.d
    public void a(SearchResult searchResult, Object obj) {
        if (obj == null || !(obj instanceof OnGetPoiSearchResultListener)) {
            return;
        }
        int i = h.f3942a[a().ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            ((OnGetPoiSearchResultListener) obj).onGetPoiResult((PoiResult) searchResult);
        }
    }
}