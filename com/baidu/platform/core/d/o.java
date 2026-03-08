package com.baidu.platform.core.d;

import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.TaxiInfo;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.ido.common.constant.LanguageRegion;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class o extends k {
    private RouteNode a(JSONArray jSONArray, List<RouteNode> list) {
        int length;
        if (jSONArray != null && (length = jSONArray.length()) > 0) {
            for (int i = 0; i < length; i++) {
                RouteNode routeNodeA = a(jSONArray.optJSONObject(i));
                if (i == length - 1) {
                    return routeNodeA;
                }
                if (list == null) {
                    list = new ArrayList<>();
                }
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(routeNodeA);
            }
        }
        return null;
    }

    private RouteNode a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        RouteNode routeNode = new RouteNode();
        routeNode.setTitle(jSONObject.optString("wd"));
        routeNode.setUid(jSONObject.optString("uid"));
        routeNode.setLocation(CoordUtil.decodeLocation(jSONObject.optString(LanguageRegion.PT)));
        return routeNode;
    }

    private List<WalkingRouteLine.WalkingStep> a(JSONArray jSONArray) {
        int length;
        if (jSONArray == null || (length = jSONArray.length()) <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                WalkingRouteLine.WalkingStep walkingStep = new WalkingRouteLine.WalkingStep();
                walkingStep.setDirection(jSONObjectOptJSONObject.optInt("direction") * 30);
                walkingStep.setDistance(jSONObjectOptJSONObject.optInt("distance"));
                walkingStep.setDuration(jSONObjectOptJSONObject.optInt(com.ido.ble.event.stat.one.d.C));
                walkingStep.setEntrance(RouteNode.location(CoordUtil.decodeLocation(jSONObjectOptJSONObject.optString("start_location"))));
                walkingStep.setExit(RouteNode.location(CoordUtil.decodeLocation(jSONObjectOptJSONObject.optString("end_location"))));
                String strOptString = jSONObjectOptJSONObject.optString("instructions");
                if (strOptString != null && strOptString.length() >= 4) {
                    strOptString = strOptString.replaceAll("</?[a-z]>", "");
                }
                walkingStep.setInstructions(strOptString);
                walkingStep.setEntranceInstructions(jSONObjectOptJSONObject.optString("start_instructions"));
                walkingStep.setExitInstructions(jSONObjectOptJSONObject.optString("end_instructions"));
                walkingStep.setPathString(jSONObjectOptJSONObject.optString("path"));
                arrayList.add(walkingStep);
            }
        }
        return arrayList;
    }

    private TaxiInfo b(String str) {
        JSONObject jSONObject;
        if (str == null || str.length() == 0) {
            return null;
        }
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e2) {
            e2.printStackTrace();
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        TaxiInfo taxiInfo = new TaxiInfo();
        taxiInfo.setDesc(jSONObject.optString("remark"));
        taxiInfo.setDistance(jSONObject.optInt("distance"));
        taxiInfo.setDuration(jSONObject.optInt(com.ido.ble.event.stat.one.d.C));
        taxiInfo.setTotalPrice((float) jSONObject.optDouble("total_price"));
        taxiInfo.setStartPrice((float) jSONObject.optDouble("start_price"));
        taxiInfo.setPerKMPrice((float) jSONObject.optDouble("km_price"));
        return taxiInfo;
    }

    private boolean b(String str, WalkingRouteResult walkingRouteResult) {
        JSONArray jSONArrayOptJSONArray;
        if (str != null && !"".equals(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (walkingRouteResult == null) {
                    return false;
                }
                if (jSONObject.has("taxi")) {
                    walkingRouteResult.setTaxiInfo(b(jSONObject.optString("taxi")));
                }
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("result");
                if (jSONObjectOptJSONObject == null) {
                    return false;
                }
                int iOptInt = jSONObjectOptJSONObject.optInt(AuthorizationResponseParser.ERROR);
                if (iOptInt != 0) {
                    if (iOptInt != 4) {
                        return false;
                    }
                    walkingRouteResult.error = SearchResult.ERRORNO.ST_EN_TOO_NEAR;
                    return true;
                }
                JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("walk");
                if (jSONObjectOptJSONObject2 == null) {
                    return false;
                }
                JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject2.optJSONArray("routes");
                JSONObject jSONObjectOptJSONObject3 = jSONObjectOptJSONObject2.optJSONObject("option");
                if (jSONObjectOptJSONObject3 == null || jSONArrayOptJSONArray2 == null) {
                    return false;
                }
                RouteNode routeNodeA = a(jSONObjectOptJSONObject3.optJSONObject("start"));
                RouteNode routeNodeA2 = a(jSONObjectOptJSONObject3.optJSONArray("end"), (List<RouteNode>) null);
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < jSONArrayOptJSONArray2.length(); i++) {
                    JSONObject jSONObjectOptJSONObject4 = jSONArrayOptJSONArray2.optJSONObject(i);
                    if (jSONObjectOptJSONObject4 != null && (jSONArrayOptJSONArray = jSONObjectOptJSONObject4.optJSONArray("legs")) != null && jSONArrayOptJSONArray.length() > 0) {
                        for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                            JSONObject jSONObjectOptJSONObject5 = jSONArrayOptJSONArray.optJSONObject(i);
                            if (jSONObjectOptJSONObject5 != null) {
                                WalkingRouteLine walkingRouteLine = new WalkingRouteLine();
                                walkingRouteLine.setStarting(routeNodeA);
                                walkingRouteLine.setTerminal(routeNodeA2);
                                walkingRouteLine.setDistance(jSONObjectOptJSONObject5.optInt("distance"));
                                walkingRouteLine.setDuration(jSONObjectOptJSONObject5.optInt(com.ido.ble.event.stat.one.d.C));
                                walkingRouteLine.setSteps(a(jSONObjectOptJSONObject5.optJSONArray("steps")));
                                arrayList.add(walkingRouteLine);
                            }
                        }
                    }
                }
                walkingRouteResult.setRouteLines(arrayList);
                return true;
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public void a(String str, WalkingRouteResult walkingRouteResult) {
        if (str != null && !str.equals("")) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("SDK_InnerError")) {
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("SDK_InnerError");
                    if (jSONObjectOptJSONObject.has("PermissionCheckError")) {
                        walkingRouteResult.error = SearchResult.ERRORNO.PERMISSION_UNFINISHED;
                        return;
                    } else if (jSONObjectOptJSONObject.has("httpStateError")) {
                        String strOptString = jSONObjectOptJSONObject.optString("httpStateError");
                        walkingRouteResult.error = strOptString.equals("NETWORK_ERROR") ? SearchResult.ERRORNO.NETWORK_ERROR : strOptString.equals("REQUEST_ERROR") ? SearchResult.ERRORNO.REQUEST_ERROR : SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
                        return;
                    }
                }
                if (a(str, walkingRouteResult, false) || b(str, walkingRouteResult)) {
                    return;
                }
                walkingRouteResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                return;
            } catch (Exception unused) {
            }
        }
        walkingRouteResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
    }
}