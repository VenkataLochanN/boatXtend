package com.ido.life.location;

import com.ido.life.base.BaseMessage;
import com.ido.life.bean.LatLngBean;

/* JADX INFO: loaded from: classes2.dex */
public class LocationMessage extends BaseMessage<LatLngBean> {
    public float accurac;
    public String city;
    public String country;
    public int gpsAccuracyStatus;
    public boolean isValid;
    public String speed;
    public int totalDistance;

    public LocationMessage(int i, LatLngBean latLngBean) {
        super(i, latLngBean);
        this.isValid = true;
    }

    public LocationMessage(int i) {
        super(i);
        this.isValid = true;
    }

    @Override // com.ido.life.base.BaseMessage
    public String toString() {
        return "LocationMessage{city='" + this.city + "', accurac=" + this.accurac + ", speed='" + this.speed + "', country='" + this.country + "', totalDistance='" + this.totalDistance + "', data='" + getData() + "', gpsAccuracyStatus=" + this.gpsAccuracyStatus + '}';
    }
}