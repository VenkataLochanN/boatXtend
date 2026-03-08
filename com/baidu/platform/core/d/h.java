package com.baidu.platform.core.d;

import androidx.core.app.NotificationCompat;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.BusInfo;
import com.baidu.mapapi.search.core.CoachInfo;
import com.baidu.mapapi.search.core.PlaneInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.PriceInfo;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.TaxiInfo;
import com.baidu.mapapi.search.core.TrainInfo;
import com.baidu.mapapi.search.core.TransitResultNode;
import com.baidu.mapapi.search.route.MassTransitRouteLine;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.SuggestAddrInfo;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class h extends com.baidu.platform.base.d {
    private TransitResultNode a(int i, JSONObject jSONObject) {
        LatLng latLng = null;
        if (jSONObject == null) {
            return null;
        }
        String strOptString = jSONObject.optString("wd");
        String strOptString2 = jSONObject.optString("city_name");
        int iOptInt = jSONObject.optInt(i == 1 ? "city_code" : "city_id");
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(FirebaseAnalytics.Param.LOCATION);
        if (jSONObjectOptJSONObject != null) {
            latLng = new LatLng(jSONObjectOptJSONObject.optDouble("lat"), jSONObjectOptJSONObject.optDouble("lng"));
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                latLng = CoordTrans.baiduToGcj(latLng);
            }
        }
        return new TransitResultNode(iOptInt, strOptString2, latLng, strOptString);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private MassTransitRouteLine.TransitStep a(JSONObject jSONObject) {
        MassTransitRouteLine.TransitStep.StepVehicleInfoType stepVehicleInfoType;
        if (jSONObject == null) {
            return null;
        }
        MassTransitRouteLine.TransitStep transitStep = new MassTransitRouteLine.TransitStep();
        transitStep.setDistance((int) jSONObject.optDouble("distance"));
        transitStep.setDuration((int) jSONObject.optDouble(com.ido.ble.event.stat.one.d.C));
        transitStep.setInstructions(jSONObject.optString("instructions"));
        transitStep.setPathString(jSONObject.optString("path"));
        transitStep.setTrafficConditions(b(jSONObject.optJSONArray("traffic_condition")));
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("start_location");
        if (jSONObjectOptJSONObject != null) {
            LatLng latLng = new LatLng(jSONObjectOptJSONObject.optDouble("lat"), jSONObjectOptJSONObject.optDouble("lng"));
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                latLng = CoordTrans.baiduToGcj(latLng);
            }
            transitStep.setStartLocation(latLng);
        }
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("end_location");
        if (jSONObjectOptJSONObject2 != null) {
            LatLng latLng2 = new LatLng(jSONObjectOptJSONObject2.optDouble("lat"), jSONObjectOptJSONObject2.optDouble("lng"));
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                latLng2 = CoordTrans.baiduToGcj(latLng2);
            }
            transitStep.setEndLocation(latLng2);
        }
        JSONObject jSONObjectOptJSONObject3 = jSONObject.optJSONObject("vehicle_info");
        if (jSONObjectOptJSONObject3 != null) {
            int iOptInt = jSONObjectOptJSONObject3.optInt("type");
            JSONObject jSONObjectOptJSONObject4 = jSONObjectOptJSONObject3.optJSONObject("detail");
            switch (iOptInt) {
                case 1:
                    transitStep.setVehileType(MassTransitRouteLine.TransitStep.StepVehicleInfoType.ESTEP_TRAIN);
                    if (jSONObjectOptJSONObject4 != null) {
                        TrainInfo trainInfo = new TrainInfo();
                        trainInfo.setName(jSONObjectOptJSONObject4.optString(AppMeasurementSdk.ConditionalUserProperty.NAME));
                        trainInfo.a(jSONObjectOptJSONObject4.optDouble(FirebaseAnalytics.Param.PRICE));
                        trainInfo.a(jSONObjectOptJSONObject4.optString("booking"));
                        trainInfo.setDepartureStation(jSONObjectOptJSONObject4.optString("departure_station"));
                        trainInfo.setArriveStation(jSONObjectOptJSONObject4.optString("arrive_station"));
                        trainInfo.setDepartureTime(jSONObjectOptJSONObject4.optString("departure_time"));
                        trainInfo.setArriveTime(jSONObjectOptJSONObject4.optString("arrive_time"));
                        transitStep.setTrainInfo(trainInfo);
                    }
                    break;
                case 2:
                    transitStep.setVehileType(MassTransitRouteLine.TransitStep.StepVehicleInfoType.ESTEP_PLANE);
                    if (jSONObjectOptJSONObject4 != null) {
                        PlaneInfo planeInfo = new PlaneInfo();
                        planeInfo.setName(jSONObjectOptJSONObject4.optString(AppMeasurementSdk.ConditionalUserProperty.NAME));
                        planeInfo.setPrice(jSONObjectOptJSONObject4.optDouble(FirebaseAnalytics.Param.PRICE));
                        planeInfo.setDiscount(jSONObjectOptJSONObject4.optDouble("discount"));
                        planeInfo.setAirlines(jSONObjectOptJSONObject4.optString("airlines"));
                        planeInfo.setBooking(jSONObjectOptJSONObject4.optString("booking"));
                        planeInfo.setDepartureStation(jSONObjectOptJSONObject4.optString("departure_station"));
                        planeInfo.setArriveStation(jSONObjectOptJSONObject4.optString("arrive_station"));
                        planeInfo.setDepartureTime(jSONObjectOptJSONObject4.optString("departure_time"));
                        planeInfo.setArriveTime(jSONObjectOptJSONObject4.optString("arrive_time"));
                        transitStep.setPlaneInfo(planeInfo);
                    }
                    break;
                case 3:
                    transitStep.setVehileType(MassTransitRouteLine.TransitStep.StepVehicleInfoType.ESTEP_BUS);
                    if (jSONObjectOptJSONObject4 != null) {
                        BusInfo busInfo = new BusInfo();
                        busInfo.setName(jSONObjectOptJSONObject4.optString(AppMeasurementSdk.ConditionalUserProperty.NAME));
                        busInfo.setType(jSONObjectOptJSONObject4.optInt("type"));
                        busInfo.setStopNum(jSONObjectOptJSONObject4.optInt("stop_num"));
                        busInfo.setDepartureStation(jSONObjectOptJSONObject4.optString("on_station"));
                        busInfo.setArriveStation(jSONObjectOptJSONObject4.optString("off_station"));
                        busInfo.setDepartureTime(jSONObjectOptJSONObject4.optString("first_time"));
                        busInfo.setArriveTime(jSONObjectOptJSONObject4.optString("last_time"));
                        transitStep.setBusInfo(busInfo);
                    }
                    break;
                case 4:
                    stepVehicleInfoType = MassTransitRouteLine.TransitStep.StepVehicleInfoType.ESTEP_DRIVING;
                    transitStep.setVehileType(stepVehicleInfoType);
                    break;
                case 5:
                    stepVehicleInfoType = MassTransitRouteLine.TransitStep.StepVehicleInfoType.ESTEP_WALK;
                    transitStep.setVehileType(stepVehicleInfoType);
                    break;
                case 6:
                    transitStep.setVehileType(MassTransitRouteLine.TransitStep.StepVehicleInfoType.ESTEP_COACH);
                    if (jSONObjectOptJSONObject4 != null) {
                        CoachInfo coachInfo = new CoachInfo();
                        coachInfo.setName(jSONObjectOptJSONObject4.optString(AppMeasurementSdk.ConditionalUserProperty.NAME));
                        coachInfo.setPrice(jSONObjectOptJSONObject4.optDouble(FirebaseAnalytics.Param.PRICE));
                        coachInfo.setBooking(jSONObjectOptJSONObject4.optString("booking"));
                        coachInfo.setProviderName(jSONObjectOptJSONObject4.optString("provider_name"));
                        coachInfo.setProviderUrl(jSONObjectOptJSONObject4.optString("provider_url"));
                        coachInfo.setDepartureStation(jSONObjectOptJSONObject4.optString("departure_station"));
                        coachInfo.setArriveStation(jSONObjectOptJSONObject4.optString("arrive_station"));
                        coachInfo.setDepartureTime(jSONObjectOptJSONObject4.optString("departure_time"));
                        coachInfo.setArriveTime(jSONObjectOptJSONObject4.optString("arrive_time"));
                        transitStep.setCoachInfo(coachInfo);
                    }
                    break;
            }
        }
        return transitStep;
    }

    private List<List<MassTransitRouteLine.TransitStep>> a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null || jSONArray.length() < 0) {
            return null;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONArray jSONArrayOptJSONArray = jSONArray.optJSONArray(i);
            if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
                ArrayList arrayList2 = new ArrayList();
                for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                    JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i2);
                    if (jSONObjectOptJSONObject != null) {
                        arrayList2.add(a(jSONObjectOptJSONObject));
                    }
                }
                arrayList.add(arrayList2);
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

    private SuggestAddrInfo b(JSONObject jSONObject) {
        SuggestAddrInfo suggestAddrInfo = new SuggestAddrInfo();
        suggestAddrInfo.setSuggestStartNode(d(jSONObject.optJSONArray("origin_list")));
        suggestAddrInfo.setSuggestEndNode(d(jSONObject.optJSONArray("destination_list")));
        return suggestAddrInfo;
    }

    private List<MassTransitRouteLine.TransitStep.TrafficCondition> b(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() < 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                MassTransitRouteLine.TransitStep.TrafficCondition trafficCondition = new MassTransitRouteLine.TransitStep.TrafficCondition();
                trafficCondition.setTrafficStatus(jSONObjectOptJSONObject.optInt(NotificationCompat.CATEGORY_STATUS));
                trafficCondition.setTrafficGeoCnt(jSONObjectOptJSONObject.optInt("geo_cnt"));
                arrayList.add(trafficCondition);
            }
        }
        return arrayList;
    }

    private List<PriceInfo> c(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            PriceInfo priceInfo = new PriceInfo();
            JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                priceInfo.setTicketType(jSONObjectOptJSONObject.optInt("ticket_type"));
                priceInfo.setTicketPrice(jSONObjectOptJSONObject.optDouble("ticket_price"));
            }
            arrayList.add(priceInfo);
        }
        return arrayList;
    }

    private List<PoiInfo> d(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = (JSONObject) jSONArray.opt(i);
            if (jSONObject != null) {
                PoiInfo poiInfo = new PoiInfo();
                poiInfo.address = jSONObject.optString("address");
                poiInfo.uid = jSONObject.optString("uid");
                poiInfo.name = jSONObject.optString(AppMeasurementSdk.ConditionalUserProperty.NAME);
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(FirebaseAnalytics.Param.LOCATION);
                if (jSONObjectOptJSONObject != null) {
                    poiInfo.location = new LatLng(jSONObjectOptJSONObject.optDouble("lat"), jSONObjectOptJSONObject.optDouble("lng"));
                    if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                        poiInfo.location = CoordTrans.baiduToGcj(poiInfo.location);
                    }
                }
                arrayList.add(poiInfo);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }

    @Override // com.baidu.platform.base.d
    public SearchResult a(String str) {
        MassTransitRouteResult massTransitRouteResult = new MassTransitRouteResult();
        if (str != null && !str.equals("")) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("SDK_InnerError")) {
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("SDK_InnerError");
                    if (jSONObjectOptJSONObject.has("PermissionCheckError")) {
                        massTransitRouteResult.error = SearchResult.ERRORNO.PERMISSION_UNFINISHED;
                        return massTransitRouteResult;
                    }
                    if (jSONObjectOptJSONObject.has("httpStateError")) {
                        String strOptString = jSONObjectOptJSONObject.optString("httpStateError");
                        massTransitRouteResult.error = strOptString.equals("NETWORK_ERROR") ? SearchResult.ERRORNO.NETWORK_ERROR : strOptString.equals("REQUEST_ERROR") ? SearchResult.ERRORNO.REQUEST_ERROR : SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
                        return massTransitRouteResult;
                    }
                }
                if (!a(str, massTransitRouteResult, false) && !a(str, massTransitRouteResult)) {
                    massTransitRouteResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
                }
                return massTransitRouteResult;
            } catch (Exception unused) {
            }
        }
        massTransitRouteResult.error = SearchResult.ERRORNO.RESULT_NOT_FOUND;
        return massTransitRouteResult;
    }

    @Override // com.baidu.platform.base.d
    public void a(SearchResult searchResult, Object obj) {
        if (obj == null || !(obj instanceof OnGetRoutePlanResultListener)) {
            return;
        }
        ((OnGetRoutePlanResultListener) obj).onGetMassTransitRouteResult((MassTransitRouteResult) searchResult);
    }

    public boolean a(String str, MassTransitRouteResult massTransitRouteResult) {
        SearchResult.ERRORNO errorno;
        SearchResult.ERRORNO errorno2;
        if (str != null && str.length() > 0) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                int iOptInt = jSONObject.optInt("status_sdk");
                if (iOptInt != 0) {
                    if (iOptInt == 1) {
                        errorno2 = SearchResult.ERRORNO.MASS_TRANSIT_SERVER_ERROR;
                    } else if (iOptInt == 2) {
                        errorno2 = SearchResult.ERRORNO.MASS_TRANSIT_OPTION_ERROR;
                    } else {
                        if (iOptInt != 1002) {
                            return false;
                        }
                        errorno2 = SearchResult.ERRORNO.MASS_TRANSIT_NO_POI_ERROR;
                    }
                    massTransitRouteResult.error = errorno2;
                    return true;
                }
                int iOptInt2 = jSONObject.optInt("type");
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("result");
                if (jSONObjectOptJSONObject == null) {
                    return false;
                }
                if (iOptInt2 != 1) {
                    if (iOptInt2 == 2) {
                        TransitResultNode transitResultNodeA = a(iOptInt2, jSONObjectOptJSONObject.optJSONObject("origin"));
                        massTransitRouteResult.setOrigin(transitResultNodeA);
                        TransitResultNode transitResultNodeA2 = a(iOptInt2, jSONObjectOptJSONObject.optJSONObject(FirebaseAnalytics.Param.DESTINATION));
                        massTransitRouteResult.setDestination(transitResultNodeA2);
                        massTransitRouteResult.setTotal(jSONObjectOptJSONObject.optInt("total"));
                        massTransitRouteResult.setTaxiInfo(b(jSONObjectOptJSONObject.optString("taxi")));
                        JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("routes");
                        if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
                            return false;
                        }
                        ArrayList arrayList = new ArrayList();
                        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                            JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray.optJSONObject(i);
                            if (jSONObjectOptJSONObject2 != null) {
                                MassTransitRouteLine massTransitRouteLine = new MassTransitRouteLine();
                                massTransitRouteLine.setDistance(jSONObjectOptJSONObject2.optInt("distance"));
                                massTransitRouteLine.setDuration(jSONObjectOptJSONObject2.optInt(com.ido.ble.event.stat.one.d.C));
                                massTransitRouteLine.setArriveTime(jSONObjectOptJSONObject2.optString("arrive_time"));
                                massTransitRouteLine.setPrice(jSONObjectOptJSONObject2.optDouble(FirebaseAnalytics.Param.PRICE));
                                massTransitRouteLine.setPriceInfo(c(jSONObjectOptJSONObject2.optJSONArray("price_detail")));
                                if (transitResultNodeA != null) {
                                    RouteNode routeNode = new RouteNode();
                                    routeNode.setLocation(transitResultNodeA.getLocation());
                                    massTransitRouteLine.setStarting(routeNode);
                                }
                                if (transitResultNodeA2 != null) {
                                    RouteNode routeNode2 = new RouteNode();
                                    routeNode2.setLocation(transitResultNodeA2.getLocation());
                                    massTransitRouteLine.setTerminal(routeNode2);
                                }
                                JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject2.optJSONArray("steps");
                                if (jSONArrayOptJSONArray2 != null && jSONArrayOptJSONArray2.length() > 0) {
                                    massTransitRouteLine.setNewSteps(a(jSONArrayOptJSONArray2));
                                    arrayList.add(massTransitRouteLine);
                                }
                            }
                        }
                        massTransitRouteResult.setRoutelines(arrayList);
                        errorno = SearchResult.ERRORNO.NO_ERROR;
                    }
                    return true;
                }
                massTransitRouteResult.setOrigin(a(iOptInt2, jSONObjectOptJSONObject.optJSONObject("origin_info")));
                massTransitRouteResult.setDestination(a(iOptInt2, jSONObjectOptJSONObject.optJSONObject("destination_info")));
                massTransitRouteResult.setSuggestAddrInfo(b(jSONObjectOptJSONObject));
                errorno = SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR;
                massTransitRouteResult.error = errorno;
                return true;
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }
}