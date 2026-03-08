package com.baidu.platform.core.c;

import com.autonavi.base.amap.mapcore.AeUtil;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiIndoorInfo;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class b extends com.baidu.platform.base.d {
    private boolean a(String str, PoiIndoorResult poiIndoorResult) {
        SearchResult.ERRORNO errorno;
        if (str != null && !"".equals(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                int iOptInt = jSONObject.optInt("errNo");
                if (iOptInt == 0) {
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(AeUtil.ROOT_DATA_PATH_OLD_NAME);
                    if (jSONObjectOptJSONObject == null) {
                        return false;
                    }
                    JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("poi_list");
                    if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
                        poiIndoorResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                    } else {
                        ArrayList arrayList = new ArrayList();
                        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                            JSONObject jSONObject2 = (JSONObject) jSONArrayOptJSONArray.opt(i);
                            if (jSONObject2 != null) {
                                PoiIndoorInfo poiIndoorInfo = new PoiIndoorInfo();
                                poiIndoorInfo.address = jSONObject2.optString("address");
                                poiIndoorInfo.bid = jSONObject2.optString("bd_id");
                                poiIndoorInfo.cid = jSONObject2.optInt("cid");
                                poiIndoorInfo.discount = jSONObject2.optInt("discount");
                                poiIndoorInfo.floor = jSONObject2.optString("floor");
                                poiIndoorInfo.name = jSONObject2.optString(AppMeasurementSdk.ConditionalUserProperty.NAME);
                                poiIndoorInfo.phone = jSONObject2.optString("phone");
                                poiIndoorInfo.price = jSONObject2.optInt(FirebaseAnalytics.Param.PRICE);
                                poiIndoorInfo.starLevel = jSONObject2.optInt("star_level");
                                poiIndoorInfo.tag = jSONObject2.optString("tag");
                                poiIndoorInfo.uid = jSONObject2.optString("uid");
                                poiIndoorInfo.groupNum = jSONObject2.optInt("tuan_nums");
                                int i2 = Integer.parseInt(jSONObject2.optString("twp"));
                                if ((i2 & 1) == 1) {
                                    poiIndoorInfo.isGroup = true;
                                }
                                if ((i2 & 2) == 1) {
                                    poiIndoorInfo.isTakeOut = true;
                                }
                                if ((i2 & 4) == 1) {
                                    poiIndoorInfo.isWaited = true;
                                }
                                poiIndoorInfo.latLng = CoordUtil.mc2ll(new GeoPoint(jSONObject2.optDouble("pt_y"), jSONObject2.optDouble("pt_x")));
                                arrayList.add(poiIndoorInfo);
                            }
                        }
                        poiIndoorResult.error = SearchResult.ERRORNO.NO_ERROR;
                        poiIndoorResult.setmArrayPoiInfo(arrayList);
                    }
                    poiIndoorResult.pageNum = jSONObjectOptJSONObject.optInt("page_num");
                    poiIndoorResult.poiNum = jSONObjectOptJSONObject.optInt("poi_num");
                    errorno = SearchResult.ERRORNO.NO_ERROR;
                } else {
                    if (iOptInt != 1) {
                        if (iOptInt != 5) {
                            errorno = SearchResult.ERRORNO.POIINDOOR_SERVER_ERROR;
                        }
                        return false;
                    }
                    String strOptString = jSONObject.optString("Msg");
                    if (!strOptString.contains("bid")) {
                        if (strOptString.contains("floor")) {
                            errorno = SearchResult.ERRORNO.POIINDOOR_FLOOR_ERROR;
                        }
                        return false;
                    }
                    errorno = SearchResult.ERRORNO.POIINDOOR_BID_ERROR;
                }
                poiIndoorResult.error = errorno;
                return true;
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    @Override // com.baidu.platform.base.d
    public SearchResult a(String str) {
        PoiIndoorResult poiIndoorResult = new PoiIndoorResult();
        if (str != null && !str.equals("")) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("SDK_InnerError")) {
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("SDK_InnerError");
                    if (jSONObjectOptJSONObject.has("PermissionCheckError")) {
                        poiIndoorResult.error = SearchResult.ERRORNO.PERMISSION_UNFINISHED;
                        return poiIndoorResult;
                    }
                    if (jSONObjectOptJSONObject.has("httpStateError")) {
                        String strOptString = jSONObjectOptJSONObject.optString("httpStateError");
                        poiIndoorResult.error = strOptString.equals("NETWORK_ERROR") ? SearchResult.ERRORNO.NETWORK_ERROR : strOptString.equals("REQUEST_ERROR") ? SearchResult.ERRORNO.REQUEST_ERROR : SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
                        return poiIndoorResult;
                    }
                }
                if (!a(str, poiIndoorResult, false) && !a(str, poiIndoorResult)) {
                    poiIndoorResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                }
                return poiIndoorResult;
            } catch (Exception unused) {
            }
        }
        poiIndoorResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
        return poiIndoorResult;
    }

    @Override // com.baidu.platform.base.d
    public void a(SearchResult searchResult, Object obj) {
        if (obj == null || !(obj instanceof OnGetPoiSearchResultListener)) {
            return;
        }
        ((OnGetPoiSearchResultListener) obj).onGetPoiIndoorResult((PoiIndoorResult) searchResult);
    }
}