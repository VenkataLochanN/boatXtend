package com.ido.life.module.sport.bean;

import com.ido.life.bean.LatLngBean;

/* JADX INFO: loaded from: classes2.dex */
public class LocationMessage extends LatLngBean {
    public float accurac;
    public String city;
    public String country;
    public int gpsAccuracyStatus;
    public boolean isValid = true;
    public float speed;
    public int totalDistance;

    @Override // com.ido.life.bean.LatLngBean
    public String toString() {
        return "LocationMessage{city='" + this.city + "', accurac=" + this.accurac + ", speed='" + this.speed + "', country='" + this.country + "', gpsAccuracyStatus=" + this.gpsAccuracyStatus + ", isValid=" + this.isValid + ", totalDistance=" + this.totalDistance + ", latitude=" + this.latitude + ", longitude=" + this.longitude + ", altitude=" + this.altitude + ", isGps=" + this.isGps + ", currentTimeMillis='" + this.currentTimeMillis + "'}";
    }
}