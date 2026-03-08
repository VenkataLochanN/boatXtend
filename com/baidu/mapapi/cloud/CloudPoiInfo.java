package com.baidu.mapapi.cloud;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.life.dialog.CommonDialog;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CloudPoiInfo {
    public String address;
    public String city;
    public String direction;
    public int distance;
    public String district;
    public Map<String, Object> extras;
    public int geotableId;
    public double latitude;
    public double longitude;
    public String poiId;
    public String province;
    public String tags;
    public String title;
    public int uid;
    public int weight;

    void a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return;
        }
        this.uid = jSONObject.optInt("uid");
        this.poiId = jSONObject.optString("uid");
        jSONObject.remove("uid");
        this.geotableId = jSONObject.optInt("geotable_id");
        jSONObject.remove("geotable_id");
        this.title = jSONObject.optString(CommonDialog.EXTRA_TITLE);
        jSONObject.remove(CommonDialog.EXTRA_TITLE);
        this.address = jSONObject.optString("address");
        jSONObject.remove("address");
        this.province = jSONObject.optString("province");
        jSONObject.remove("province");
        this.city = jSONObject.optString("city");
        jSONObject.remove("city");
        this.district = jSONObject.optString("district");
        jSONObject.remove("district");
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(FirebaseAnalytics.Param.LOCATION);
        if (jSONArrayOptJSONArray != null) {
            this.longitude = jSONArrayOptJSONArray.optDouble(0);
            this.latitude = jSONArrayOptJSONArray.optDouble(1);
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                LatLng latLngBaiduToGcj = CoordTrans.baiduToGcj(new LatLng(this.latitude, this.longitude));
                this.longitude = latLngBaiduToGcj.longitude;
                this.latitude = latLngBaiduToGcj.latitude;
            }
        }
        jSONObject.remove(FirebaseAnalytics.Param.LOCATION);
        this.tags = jSONObject.optString("tags");
        jSONObject.remove("tags");
        this.distance = jSONObject.optInt("distance");
        jSONObject.remove("distance");
        this.weight = jSONObject.optInt("weight");
        jSONObject.remove("weight");
        this.extras = new HashMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            this.extras.put(next, jSONObject.opt(next));
        }
    }

    void b(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return;
        }
        this.title = jSONObject.optString(AppMeasurementSdk.ConditionalUserProperty.NAME);
        this.address = jSONObject.optString("address");
        this.tags = jSONObject.optString("tag");
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(FirebaseAnalytics.Param.LOCATION);
        if (jSONObjectOptJSONObject != null) {
            this.longitude = jSONObjectOptJSONObject.optDouble("lng");
            this.latitude = jSONObjectOptJSONObject.optDouble("lat");
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                LatLng latLngBaiduToGcj = CoordTrans.baiduToGcj(new LatLng(this.latitude, this.longitude));
                this.longitude = latLngBaiduToGcj.longitude;
                this.latitude = latLngBaiduToGcj.latitude;
            }
        }
        this.direction = jSONObject.optString("direction");
        this.distance = jSONObject.optInt("distance");
    }
}