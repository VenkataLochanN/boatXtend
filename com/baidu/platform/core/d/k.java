package com.baidu.platform.core.d;

import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.SuggestAddrInfo;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.platform.base.SearchType;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class k extends com.baidu.platform.base.d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    SuggestAddrInfo f3944b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected boolean f3945c;

    private SuggestAddrInfo a(JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject;
        JSONObject jSONObject2;
        if (jSONObject == null || (jSONObjectOptJSONObject = jSONObject.optJSONObject("traffic_pois")) == null) {
            return null;
        }
        JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("option");
        JSONObject jSONObjectOptJSONObject3 = jSONObjectOptJSONObject.optJSONObject(FirebaseAnalytics.Param.CONTENT);
        if (jSONObjectOptJSONObject2 != null && jSONObjectOptJSONObject3 != null) {
            JSONObject jSONObjectOptJSONObject4 = jSONObjectOptJSONObject2.optJSONObject("start_city");
            String strOptString = jSONObjectOptJSONObject4 != null ? jSONObjectOptJSONObject4.optString("cname") : null;
            JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject2.optJSONArray("end_city");
            String strOptString2 = (jSONArrayOptJSONArray == null || (jSONObject2 = (JSONObject) jSONArrayOptJSONArray.opt(0)) == null) ? null : jSONObject2.optString("cname");
            JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject2.optJSONArray("city_list");
            JSONArray jSONArrayOptJSONArray3 = jSONObjectOptJSONObject2.optJSONArray("prio_flag");
            if (jSONArrayOptJSONArray2 != null && jSONArrayOptJSONArray3 != null) {
                int length = jSONArrayOptJSONArray2.length();
                boolean[] zArr = new boolean[length];
                boolean[] zArr2 = new boolean[length];
                for (int i = 0; i < length; i++) {
                    int i2 = Integer.parseInt(jSONArrayOptJSONArray2.optString(i));
                    int i3 = Integer.parseInt(jSONArrayOptJSONArray3.optString(i));
                    boolean z = true;
                    zArr[i] = i2 == 1;
                    if (i3 != 1) {
                        z = false;
                    }
                    zArr2[i] = z;
                }
                SuggestAddrInfo suggestAddrInfo = new SuggestAddrInfo();
                for (int i4 = 0; i4 < length; i4++) {
                    if (!zArr2[i4]) {
                        if (zArr[i4]) {
                            if (i4 == 0) {
                                suggestAddrInfo.setSuggestStartCity(a(jSONObjectOptJSONObject3.optJSONArray("start")));
                            } else if (i4 != length - 1 || i4 <= 0) {
                                suggestAddrInfo.setSuggestWpCity(a(jSONObjectOptJSONObject3, "multi_waypoints"));
                            } else {
                                suggestAddrInfo.setSuggestEndCity(a(jSONObjectOptJSONObject3.optJSONArray("end")));
                            }
                        } else if (i4 == 0) {
                            suggestAddrInfo.setSuggestStartNode(a(jSONObjectOptJSONObject3.optJSONArray("start"), strOptString));
                        } else if (i4 != length - 1 || i4 <= 0) {
                            suggestAddrInfo.setSuggestWpNode(b(jSONObjectOptJSONObject3, "multi_waypoints"));
                        } else {
                            suggestAddrInfo.setSuggestEndNode(a(jSONObjectOptJSONObject3.optJSONArray("end"), strOptString2));
                        }
                    }
                }
                return suggestAddrInfo;
            }
        }
        return null;
    }

    private List<CityInfo> a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = (JSONObject) jSONArray.opt(i);
            if (jSONObject != null) {
                CityInfo cityInfo = new CityInfo();
                cityInfo.num = jSONObject.optInt("num");
                cityInfo.city = jSONObject.optString(AppMeasurementSdk.ConditionalUserProperty.NAME);
                arrayList.add(cityInfo);
            }
        }
        arrayList.trimToSize();
        return arrayList;
    }

    private List<PoiInfo> a(JSONArray jSONArray, String str) {
        if (jSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = (JSONObject) jSONArray.opt(i);
            if (jSONObject != null) {
                PoiInfo poiInfo = new PoiInfo();
                poiInfo.address = jSONObject.optString("addr");
                poiInfo.uid = jSONObject.optString("uid");
                poiInfo.name = jSONObject.optString(AppMeasurementSdk.ConditionalUserProperty.NAME);
                poiInfo.location = CoordUtil.decodeLocation(jSONObject.optString("geo"));
                poiInfo.city = str;
                arrayList.add(poiInfo);
            }
        }
        if (arrayList.size() > 0) {
            return arrayList;
        }
        return null;
    }

    private List<List<CityInfo>> a(JSONObject jSONObject, String str) {
        JSONArray jSONArrayOptJSONArray;
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null || (jSONArrayOptJSONArray = jSONObject.optJSONArray(str)) == null) {
            return null;
        }
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            List<CityInfo> listA = a((JSONArray) jSONArrayOptJSONArray.opt(i));
            if (listA != null) {
                arrayList.add(listA);
            }
        }
        return arrayList;
    }

    private List<List<PoiInfo>> b(JSONObject jSONObject, String str) {
        JSONArray jSONArrayOptJSONArray;
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null || (jSONArrayOptJSONArray = jSONObject.optJSONArray(str)) == null) {
            return null;
        }
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            List<PoiInfo> listA = a(((JSONObject) jSONArrayOptJSONArray.opt(i)).optJSONArray("way_ponits"), "");
            if (listA != null) {
                arrayList.add(listA);
            }
        }
        return arrayList;
    }

    private boolean b(String str) {
        if (str != null && str.length() > 0) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("result");
                if (jSONObjectOptJSONObject == null || jSONObjectOptJSONObject.optInt("type") != 23 || jSONObjectOptJSONObject.optInt(AuthorizationResponseParser.ERROR) != 0) {
                    return false;
                }
                this.f3944b = a(jSONObject);
                return this.f3944b != null;
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    @Override // com.baidu.platform.base.d
    public SearchResult a(String str) {
        SearchType searchTypeA = a();
        if (b(str)) {
            this.f3945c = true;
        } else {
            this.f3945c = false;
        }
        int i = l.f3946a[searchTypeA.ordinal()];
        if (i == 1) {
            TransitRouteResult transitRouteResult = new TransitRouteResult();
            if (!this.f3945c) {
                ((m) this).a(str, transitRouteResult);
                return transitRouteResult;
            }
            transitRouteResult.setSuggestAddrInfo(this.f3944b);
            transitRouteResult.error = SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR;
            return transitRouteResult;
        }
        if (i == 2) {
            DrivingRouteResult drivingRouteResult = new DrivingRouteResult();
            if (!this.f3945c) {
                ((c) this).a(str, drivingRouteResult);
                return drivingRouteResult;
            }
            drivingRouteResult.setSuggestAddrInfo(this.f3944b);
            drivingRouteResult.error = SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR;
            return drivingRouteResult;
        }
        if (i != 3) {
            return null;
        }
        WalkingRouteResult walkingRouteResult = new WalkingRouteResult();
        if (!this.f3945c) {
            ((o) this).a(str, walkingRouteResult);
            return walkingRouteResult;
        }
        walkingRouteResult.setSuggestAddrInfo(this.f3944b);
        walkingRouteResult.error = SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR;
        return walkingRouteResult;
    }

    @Override // com.baidu.platform.base.d
    public void a(SearchResult searchResult, Object obj) {
        if (obj == null || !(obj instanceof OnGetRoutePlanResultListener)) {
            return;
        }
        OnGetRoutePlanResultListener onGetRoutePlanResultListener = (OnGetRoutePlanResultListener) obj;
        int i = l.f3946a[a().ordinal()];
        if (i == 1) {
            onGetRoutePlanResultListener.onGetTransitRouteResult((TransitRouteResult) searchResult);
        } else if (i == 2) {
            onGetRoutePlanResultListener.onGetDrivingRouteResult((DrivingRouteResult) searchResult);
        } else {
            if (i != 3) {
                return;
            }
            onGetRoutePlanResultListener.onGetWalkingRouteResult((WalkingRouteResult) searchResult);
        }
    }
}