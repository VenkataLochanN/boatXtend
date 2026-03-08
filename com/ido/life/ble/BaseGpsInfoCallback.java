package com.ido.life.ble;

import com.ido.ble.gps.callback.GpsCallBack;
import com.ido.ble.gps.model.GPSInfo;
import com.ido.ble.gps.model.GpsHotStartParam;
import com.ido.ble.gps.model.GpsStatus;

/* JADX INFO: loaded from: classes2.dex */
public class BaseGpsInfoCallback implements GpsCallBack.IGetGpsInfoCallBack {
    @Override // com.ido.ble.gps.callback.GpsCallBack.IGetGpsInfoCallBack
    public void onGetGpsInfo(GPSInfo gPSInfo) {
    }

    @Override // com.ido.ble.gps.callback.GpsCallBack.IGetGpsInfoCallBack
    public void onGetGpsStatus(GpsStatus gpsStatus) {
    }

    @Override // com.ido.ble.gps.callback.GpsCallBack.IGetGpsInfoCallBack
    public void onGetHotStartGpsPara(GpsHotStartParam gpsHotStartParam) {
    }
}