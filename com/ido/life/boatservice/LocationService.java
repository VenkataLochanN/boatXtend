package com.ido.life.boatservice;

import android.content.Context;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.common.log.CommonLogUtil;

/* JADX INFO: loaded from: classes2.dex */
public class LocationService {
    private static final String TAG = "LocationService";
    private AMapLocationClientOption DIYoption;
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;
    protected int UPDATE_INTERVAL = 0;
    private Object objLock = new Object();

    public LocationService(Context context) {
        this.mLocationClient = null;
        synchronized (this.objLock) {
            if (this.mLocationClient == null) {
                setPeriod();
                this.mLocationClient = new AMapLocationClient(context);
                this.mLocationClient.setLocationOption(getDefaultLocationClientOption());
            }
        }
    }

    public boolean registerListener(AMapLocationListener aMapLocationListener) {
        if (aMapLocationListener == null) {
            return false;
        }
        this.mLocationClient.setLocationListener(aMapLocationListener);
        return true;
    }

    public void unregisterListener(AMapLocationListener aMapLocationListener) {
        if (aMapLocationListener != null) {
            this.mLocationClient.unRegisterLocationListener(aMapLocationListener);
        }
    }

    public boolean setLocationOption(AMapLocationClientOption aMapLocationClientOption) {
        if (aMapLocationClientOption == null) {
            return false;
        }
        if (this.mLocationClient.isStarted()) {
            this.mLocationClient.stopLocation();
        }
        this.DIYoption = this.mLocationOption;
        this.mLocationClient.setLocationOption(aMapLocationClientOption);
        return false;
    }

    public AMapLocationClientOption getDefaultLocationClientOption() {
        if (this.mLocationOption == null) {
            this.mLocationOption = new AMapLocationClientOption();
            this.mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            this.mLocationOption.setInterval(this.UPDATE_INTERVAL);
            this.mLocationClient.setLocationOption(this.mLocationOption);
            this.mLocationOption.setMockEnable(true);
        }
        return this.mLocationOption;
    }

    public AMapLocationClientOption getOption() {
        if (this.DIYoption == null) {
            this.DIYoption = new AMapLocationClientOption();
        }
        return this.DIYoption;
    }

    public void start() {
        synchronized (this.objLock) {
            CommonLogUtil.d(TAG, "start: " + this.mLocationClient + AppInfo.DELIM + this.mLocationClient.isStarted());
            if (this.mLocationClient != null) {
                this.mLocationClient.startLocation();
            }
        }
    }

    public void stop() {
        synchronized (this.objLock) {
            if (this.mLocationClient != null && this.mLocationClient.isStarted()) {
                this.mLocationClient.stopLocation();
            }
        }
    }

    public boolean isStart() {
        return this.mLocationClient.isStarted();
    }

    private void setPeriod() {
        this.UPDATE_INTERVAL = 1000;
    }
}