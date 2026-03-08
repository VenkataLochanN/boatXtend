package com.baidu.mapapi.cloud;

import androidx.core.app.NotificationCompat;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
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
public class CloudRgcResult {
    public AddressCompents addressCompents;
    public String customLocationDescription;
    public List<CloudPoiInfo> customPois;
    public String formattedAddress;
    public LatLng location;
    public String message;
    public List<PoiInfo> pois;
    public String recommendedLocationDescription;
    public int status;

    public class AddressCompents {
        public int adminAreaCode;
        public String city;
        public String country;
        public String countryCode;
        public String district;
        public String province;
        public String street;
        public String streetNumber;

        public AddressCompents() {
        }

        void a(JSONObject jSONObject) throws JSONException {
            if (jSONObject != null) {
                this.country = jSONObject.optString(CountryChooseActivity.COUNTRY);
                this.province = jSONObject.optString("province");
                this.city = jSONObject.optString("city");
                this.district = jSONObject.optString("district");
                this.street = jSONObject.optString("street");
                this.streetNumber = jSONObject.optString("street_number");
                this.adminAreaCode = jSONObject.optInt("admin_area_code");
                this.countryCode = jSONObject.optString("country_code");
            }
        }
    }

    public class PoiInfo {
        public String address;
        public String direction;
        public int distance;
        public LatLng location;
        public String name;
        public String tag;
        public String uid;

        public PoiInfo() {
        }

        public void parseFromJSON(JSONObject jSONObject) throws JSONException {
            if (jSONObject != null) {
                this.name = jSONObject.optString(AppMeasurementSdk.ConditionalUserProperty.NAME);
                this.uid = jSONObject.optString("id");
                this.address = jSONObject.optString("address");
                this.tag = jSONObject.optString("tag");
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(FirebaseAnalytics.Param.LOCATION);
                if (jSONObjectOptJSONObject != null) {
                    this.location = new LatLng(jSONObjectOptJSONObject.optDouble("lat"), jSONObjectOptJSONObject.optDouble("lng"));
                    if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                        this.location = CoordTrans.baiduToGcj(this.location);
                    }
                }
                this.direction = jSONObject.optString("direction");
                this.distance = jSONObject.optInt("distance");
            }
        }
    }

    public void parseFromJSON(JSONObject jSONObject) throws JSONException {
        try {
            this.status = jSONObject.optInt(NotificationCompat.CATEGORY_STATUS);
            this.message = jSONObject.optString("message");
            if (this.status == 6 || this.status == 7 || this.status == 8 || this.status == 9) {
                this.status = 1;
            }
            if (this.status != 0) {
                return;
            }
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(FirebaseAnalytics.Param.LOCATION);
            if (jSONObjectOptJSONObject != null) {
                this.location = new LatLng(jSONObjectOptJSONObject.optDouble("lat"), jSONObjectOptJSONObject.optDouble("lng"));
                if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                    this.location = CoordTrans.baiduToGcj(this.location);
                }
            }
            JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("address_component");
            if (jSONObjectOptJSONObject2 != null) {
                this.addressCompents = new AddressCompents();
                this.addressCompents.a(jSONObjectOptJSONObject2);
            }
            this.formattedAddress = jSONObject.optString("formatted_address");
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("pois");
            if (jSONArrayOptJSONArray != null) {
                this.pois = new ArrayList();
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    JSONObject jSONObjectOptJSONObject3 = jSONArrayOptJSONArray.optJSONObject(i);
                    if (jSONObjectOptJSONObject3 != null) {
                        PoiInfo poiInfo = new PoiInfo();
                        poiInfo.parseFromJSON(jSONObjectOptJSONObject3);
                        this.pois.add(poiInfo);
                    }
                }
            }
            JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("custom_pois");
            if (jSONArrayOptJSONArray2 != null) {
                this.customPois = new ArrayList();
                for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                    JSONObject jSONObjectOptJSONObject4 = jSONArrayOptJSONArray2.optJSONObject(i2);
                    if (jSONObjectOptJSONObject4 != null) {
                        CloudPoiInfo cloudPoiInfo = new CloudPoiInfo();
                        cloudPoiInfo.b(jSONObjectOptJSONObject4);
                        this.customPois.add(cloudPoiInfo);
                    }
                }
            }
            this.customLocationDescription = jSONObject.optString("custom_location_description");
            this.recommendedLocationDescription = jSONObject.optString("recommended_location_description");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }
}