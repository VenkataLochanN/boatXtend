package com.baidu.platform.core.busline;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.busline.BusLineResult;
import com.baidu.mapapi.search.busline.OnGetBusLineSearchResultListener;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.platform.base.d;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.life.util.DateUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class a extends d {
    @Override // com.baidu.platform.base.d
    public SearchResult a(String str) {
        BusLineResult busLineResult = new BusLineResult();
        if (str != null && !str.equals("")) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("SDK_InnerError")) {
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("SDK_InnerError");
                    if (jSONObjectOptJSONObject.has("PermissionCheckError")) {
                        busLineResult.error = SearchResult.ERRORNO.PERMISSION_UNFINISHED;
                        return busLineResult;
                    }
                    if (jSONObjectOptJSONObject.has("httpStateError")) {
                        String strOptString = jSONObjectOptJSONObject.optString("httpStateError");
                        busLineResult.error = strOptString.equals("NETWORK_ERROR") ? SearchResult.ERRORNO.NETWORK_ERROR : strOptString.equals("REQUEST_ERROR") ? SearchResult.ERRORNO.REQUEST_ERROR : SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
                        return busLineResult;
                    }
                }
                if (!a(str, busLineResult, false) && !a(str, busLineResult)) {
                    busLineResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                }
                return busLineResult;
            } catch (Exception unused) {
            }
        }
        busLineResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
        return busLineResult;
    }

    @Override // com.baidu.platform.base.d
    public void a(SearchResult searchResult, Object obj) {
        if (obj == null || !(obj instanceof OnGetBusLineSearchResultListener)) {
            return;
        }
        ((OnGetBusLineSearchResultListener) obj).onGetBusLineResult((BusLineResult) searchResult);
    }

    public boolean a(String str, BusLineResult busLineResult) {
        if (str != null && !"".equals(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("result");
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(FirebaseAnalytics.Param.CONTENT);
                if (jSONObjectOptJSONObject == null || jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
                    return false;
                }
                JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray.optJSONObject(0);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.DATE_FORMAT_Hm);
                try {
                    busLineResult.setStartTime(simpleDateFormat.parse(jSONObjectOptJSONObject2.optString("startTime")));
                    busLineResult.setEndTime(simpleDateFormat.parse(jSONObjectOptJSONObject2.optString("endTime")));
                } catch (ParseException e2) {
                    e2.printStackTrace();
                }
                busLineResult.setBusLineName(jSONObjectOptJSONObject2.optString(AppMeasurementSdk.ConditionalUserProperty.NAME));
                busLineResult.setMonthTicket(jSONObjectOptJSONObject2.optInt("isMonTicket") == 1);
                busLineResult.setUid(jSONObjectOptJSONObject2.optString("uid"));
                busLineResult.setBasePrice(jSONObjectOptJSONObject2.optInt("ticketPrice") / 100.0f);
                busLineResult.setLineDirection(jSONObjectOptJSONObject2.optString("line_direction"));
                busLineResult.setMaxPrice(jSONObjectOptJSONObject2.optInt("maxPrice") / 100.0f);
                ArrayList arrayList = new ArrayList();
                List<List<LatLng>> listDecodeLocationList2D = CoordUtil.decodeLocationList2D(jSONObjectOptJSONObject2.optString("geo"));
                if (listDecodeLocationList2D != null) {
                    for (List<LatLng> list : listDecodeLocationList2D) {
                        BusLineResult.BusStep busStep = new BusLineResult.BusStep();
                        busStep.setWayPoints(list);
                        arrayList.add(busStep);
                    }
                }
                if (arrayList.size() > 0) {
                    busLineResult.setSteps(arrayList);
                }
                JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject2.optJSONArray("stations");
                if (jSONArrayOptJSONArray2 != null) {
                    ArrayList arrayList2 = new ArrayList();
                    for (int i = 0; i < jSONArrayOptJSONArray2.length(); i++) {
                        JSONObject jSONObjectOptJSONObject3 = jSONArrayOptJSONArray2.optJSONObject(i);
                        if (jSONObjectOptJSONObject3 != null) {
                            BusLineResult.BusStation busStation = new BusLineResult.BusStation();
                            busStation.setTitle(jSONObjectOptJSONObject3.optString(AppMeasurementSdk.ConditionalUserProperty.NAME));
                            busStation.setLocation(CoordUtil.decodeLocation(jSONObjectOptJSONObject3.optString("geo")));
                            busStation.setUid(jSONObjectOptJSONObject3.optString("uid"));
                            arrayList2.add(busStation);
                        }
                    }
                    if (arrayList2.size() > 0) {
                        busLineResult.setStations(arrayList2);
                    }
                }
                busLineResult.error = SearchResult.ERRORNO.NO_ERROR;
                return true;
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        }
        return false;
    }
}