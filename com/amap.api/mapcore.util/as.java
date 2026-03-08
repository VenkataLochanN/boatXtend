package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Bundle;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.model.MyLocationStyle;
import com.autonavi.amap.mapcore.Inner_3dMap_location;
import com.autonavi.amap.mapcore.Inner_3dMap_locationListener;
import com.autonavi.amap.mapcore.Inner_3dMap_locationOption;

/* JADX INFO: compiled from: AMapLocationSource.java */
/* JADX INFO: loaded from: classes.dex */
public class as implements LocationSource, Inner_3dMap_locationListener {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private LocationSource.OnLocationChangedListener f179d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private fo f180e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Inner_3dMap_locationOption f181f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Context f182g;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Bundle f178c = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    boolean f176a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    long f177b = 2000;

    public as(Context context) {
        this.f182g = context;
    }

    @Override // com.amap.api.maps.LocationSource
    public void activate(LocationSource.OnLocationChangedListener onLocationChangedListener) {
        this.f179d = onLocationChangedListener;
        if (this.f180e == null) {
            this.f180e = new fo(this.f182g);
            this.f181f = new Inner_3dMap_locationOption();
            this.f180e.a(this);
            this.f181f.setInterval(this.f177b);
            this.f181f.setOnceLocation(this.f176a);
            this.f181f.setLocationMode(Inner_3dMap_locationOption.Inner_3dMap_Enum_LocationMode.Hight_Accuracy);
            this.f180e.a(this.f181f);
            this.f180e.a();
        }
    }

    @Override // com.amap.api.maps.LocationSource
    public void deactivate() {
        this.f179d = null;
        fo foVar = this.f180e;
        if (foVar != null) {
            foVar.b();
            this.f180e.c();
        }
        this.f180e = null;
    }

    @Override // com.autonavi.amap.mapcore.Inner_3dMap_locationListener
    public void onLocationChanged(Inner_3dMap_location inner_3dMap_location) {
        try {
            if (this.f179d == null || inner_3dMap_location == null) {
                return;
            }
            this.f178c = inner_3dMap_location.getExtras();
            if (this.f178c == null) {
                this.f178c = new Bundle();
            }
            this.f178c.putInt(MyLocationStyle.ERROR_CODE, inner_3dMap_location.getErrorCode());
            this.f178c.putString(MyLocationStyle.ERROR_INFO, inner_3dMap_location.getErrorInfo());
            this.f178c.putInt(MyLocationStyle.LOCATION_TYPE, inner_3dMap_location.getLocationType());
            this.f178c.putFloat("Accuracy", inner_3dMap_location.getAccuracy());
            this.f178c.putString("AdCode", inner_3dMap_location.getAdCode());
            this.f178c.putString("Address", inner_3dMap_location.getAddress());
            this.f178c.putString("AoiName", inner_3dMap_location.getAoiName());
            this.f178c.putString("City", inner_3dMap_location.getCity());
            this.f178c.putString("CityCode", inner_3dMap_location.getCityCode());
            this.f178c.putString("Country", inner_3dMap_location.getCountry());
            this.f178c.putString("District", inner_3dMap_location.getDistrict());
            this.f178c.putString("Street", inner_3dMap_location.getStreet());
            this.f178c.putString("StreetNum", inner_3dMap_location.getStreetNum());
            this.f178c.putString("PoiName", inner_3dMap_location.getPoiName());
            this.f178c.putString("Province", inner_3dMap_location.getProvince());
            this.f178c.putFloat("Speed", inner_3dMap_location.getSpeed());
            this.f178c.putString("Floor", inner_3dMap_location.getFloor());
            this.f178c.putFloat("Bearing", inner_3dMap_location.getBearing());
            this.f178c.putString("BuildingId", inner_3dMap_location.getBuildingId());
            this.f178c.putDouble("Altitude", inner_3dMap_location.getAltitude());
            inner_3dMap_location.setExtras(this.f178c);
            this.f179d.onLocationChanged(inner_3dMap_location);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(long j) {
        Inner_3dMap_locationOption inner_3dMap_locationOption = this.f181f;
        if (inner_3dMap_locationOption != null && this.f180e != null && inner_3dMap_locationOption.getInterval() != j) {
            this.f181f.setInterval(j);
            this.f180e.a(this.f181f);
        }
        this.f177b = j;
    }

    public void a(int i) {
        if (i == 1 || i == 0) {
            a(true);
        } else {
            a(false);
        }
    }

    private void a(boolean z) {
        fo foVar;
        if (this.f181f != null && (foVar = this.f180e) != null) {
            foVar.c();
            this.f180e = new fo(this.f182g);
            this.f180e.a(this);
            this.f181f.setOnceLocation(z);
            if (!z) {
                this.f181f.setInterval(this.f177b);
            }
            this.f180e.a(this.f181f);
            this.f180e.a();
        }
        this.f176a = z;
    }
}