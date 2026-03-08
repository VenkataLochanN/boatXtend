package com.baidu.platform.core.a;

import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.district.DistrictResult;
import com.baidu.mapapi.search.district.OnGetDistricSearchResultListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class b extends com.baidu.platform.base.d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    boolean f3929b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    String f3930c = null;

    private boolean a(String str, DistrictResult districtResult) {
        JSONObject jSONObjectOptJSONObject;
        JSONArray jSONArrayOptJSONArray;
        JSONArray jSONArray;
        JSONArray jSONArrayOptJSONArray2;
        int length;
        JSONArray jSONArray2;
        JSONArray jSONArray3;
        int i;
        int i2 = 0;
        if (str == null || "".equals(str) || districtResult == null) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("result");
            JSONObject jSONObjectOptJSONObject3 = jSONObject.optJSONObject("city_result");
            if (jSONObjectOptJSONObject2 == null || jSONObjectOptJSONObject3 == null) {
                return false;
            }
            if (jSONObjectOptJSONObject2.optInt(AuthorizationResponseParser.ERROR) != 0 || (jSONObjectOptJSONObject = jSONObjectOptJSONObject3.optJSONObject(FirebaseAnalytics.Param.CONTENT)) == null) {
                return false;
            }
            JSONObject jSONObjectOptJSONObject4 = jSONObjectOptJSONObject.optJSONObject("sgeo");
            if (jSONObjectOptJSONObject4 != null && (jSONArrayOptJSONArray = jSONObjectOptJSONObject4.optJSONArray("geo_elements")) != null && jSONArrayOptJSONArray.length() > 0) {
                ArrayList arrayList = new ArrayList();
                int i3 = 0;
                while (i3 < jSONArrayOptJSONArray.length()) {
                    JSONObject jSONObjectOptJSONObject5 = jSONArrayOptJSONArray.optJSONObject(i3);
                    if (jSONObjectOptJSONObject5 == null || (jSONArrayOptJSONArray2 = jSONObjectOptJSONObject5.optJSONArray("point")) == null || (length = jSONArrayOptJSONArray2.length()) <= 0) {
                        jSONArray = jSONArrayOptJSONArray;
                    } else {
                        ArrayList arrayList2 = new ArrayList();
                        int i4 = i2;
                        int i5 = i4;
                        int i6 = i5;
                        while (i4 < length) {
                            int iOptInt = jSONArrayOptJSONArray2.optInt(i4);
                            if (i4 % 2 == 0) {
                                i6 += iOptInt;
                                jSONArray2 = jSONArrayOptJSONArray;
                                jSONArray3 = jSONArrayOptJSONArray2;
                                i = length;
                            } else {
                                i5 += iOptInt;
                                jSONArray2 = jSONArrayOptJSONArray;
                                jSONArray3 = jSONArrayOptJSONArray2;
                                i = length;
                                arrayList2.add(CoordUtil.mc2ll(new GeoPoint(i5, i6)));
                            }
                            i4++;
                            jSONArrayOptJSONArray = jSONArray2;
                            jSONArrayOptJSONArray2 = jSONArray3;
                            length = i;
                        }
                        jSONArray = jSONArrayOptJSONArray;
                        arrayList.add(arrayList2);
                    }
                    i3++;
                    jSONArrayOptJSONArray = jSONArray;
                    i2 = 0;
                }
                if (arrayList.size() > 0) {
                    districtResult.setPolylines(arrayList);
                    districtResult.setCenterPt(CoordUtil.decodeLocation(jSONObjectOptJSONObject.optString("geo")));
                    districtResult.setCityCode(jSONObjectOptJSONObject.optInt(AuthorizationResponseParser.CODE));
                    districtResult.setCityName(jSONObjectOptJSONObject.optString("cname"));
                    districtResult.error = SearchResult.ERRORNO.NO_ERROR;
                    return true;
                }
            }
            districtResult.setCityName(jSONObjectOptJSONObject.optString("uid"));
            this.f3930c = jSONObjectOptJSONObject.optString("cname");
            districtResult.setCenterPt(CoordUtil.decodeLocation(jSONObjectOptJSONObject.optString("geo")));
            districtResult.setCityCode(jSONObjectOptJSONObject.optInt(AuthorizationResponseParser.CODE));
            return false;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private boolean b(String str, DistrictResult districtResult) {
        List<List<LatLng>> listDecodeLocationList2D;
        if (str != null && !str.equals("") && districtResult != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("result");
                JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject(FirebaseAnalytics.Param.CONTENT);
                if (jSONObjectOptJSONObject == null || jSONObjectOptJSONObject2 == null || jSONObjectOptJSONObject.optInt(AuthorizationResponseParser.ERROR) != 0) {
                    return false;
                }
                ArrayList arrayList = new ArrayList();
                if (this.f3930c != null) {
                    try {
                        listDecodeLocationList2D = CoordUtil.decodeLocationList2D(jSONObjectOptJSONObject2.optString("geo"));
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        listDecodeLocationList2D = null;
                    }
                } else {
                    listDecodeLocationList2D = null;
                }
                if (listDecodeLocationList2D != null) {
                    for (List<LatLng> list : listDecodeLocationList2D) {
                        ArrayList arrayList2 = new ArrayList();
                        Iterator<LatLng> it = list.iterator();
                        while (it.hasNext()) {
                            arrayList2.add(it.next());
                        }
                        arrayList.add(arrayList2);
                    }
                }
                if (arrayList.size() > 0) {
                    districtResult.setPolylines(arrayList);
                }
                districtResult.setCityName(this.f3930c);
                districtResult.error = SearchResult.ERRORNO.NO_ERROR;
                this.f3930c = null;
                return true;
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        }
        return false;
    }

    @Override // com.baidu.platform.base.d
    public SearchResult a(String str) {
        DistrictResult districtResult = new DistrictResult();
        if (str != null && !str.equals("")) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("SDK_InnerError")) {
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("SDK_InnerError");
                    if (jSONObjectOptJSONObject.has("PermissionCheckError")) {
                        districtResult.error = SearchResult.ERRORNO.PERMISSION_UNFINISHED;
                        return districtResult;
                    }
                    if (jSONObjectOptJSONObject.has("httpStateError")) {
                        String strOptString = jSONObjectOptJSONObject.optString("httpStateError");
                        districtResult.error = strOptString.equals("NETWORK_ERROR") ? SearchResult.ERRORNO.NETWORK_ERROR : strOptString.equals("REQUEST_ERROR") ? SearchResult.ERRORNO.REQUEST_ERROR : SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
                        return districtResult;
                    }
                }
                if (!a(str, districtResult, false)) {
                    if (this.f3929b) {
                        b(str, districtResult);
                    } else if (!a(str, districtResult)) {
                        districtResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                    }
                }
                return districtResult;
            } catch (Exception unused) {
            }
        }
        districtResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
        return districtResult;
    }

    @Override // com.baidu.platform.base.d
    public void a(SearchResult searchResult, Object obj) {
        if (obj == null || !(obj instanceof OnGetDistricSearchResultListener)) {
            return;
        }
        ((OnGetDistricSearchResultListener) obj).onGetDistrictResult((DistrictResult) searchResult);
    }

    public void a(boolean z) {
        this.f3929b = z;
    }
}