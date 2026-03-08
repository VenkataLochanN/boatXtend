package com.ido.life.location;

import android.content.Context;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.StringUtil;
import com.ido.life.bean.LatLngBean;

/* JADX INFO: loaded from: classes2.dex */
public class GDLocation extends BaseLocationPresenter implements AMapLocationListener {
    private static final String TAG = GDLocation.class.getSimpleName();
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;

    private int translateGps(int i) {
        if (i != -1) {
            if (i == 0) {
                return 2;
            }
            if (i == 1) {
                return 1;
            }
        }
        return 0;
    }

    @Override // com.ido.life.location.BaseLocationPresenter, com.ido.life.base.IBaseLocation
    public void startLocation(Context context, boolean z) {
        super.startLocation(context, z);
        if (checkPermission()) {
            if (this.mLocationClient == null) {
                this.mLocationClient = new AMapLocationClient(context);
            }
            if (this.mLocationOption == null) {
                this.mLocationOption = getLocationOption();
                this.mLocationOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.ZH);
                this.mLocationClient.setLocationOption(this.mLocationOption);
            }
            this.mLocationClient.setLocationListener(this);
            this.mLocationClient.startLocation();
            return;
        }
        CommonLogUtil.d(TAG, "开启定位失败,没有获取相关权限");
    }

    private AMapLocationClientOption getLocationOption() {
        AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
        aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        aMapLocationClientOption.setInterval(this.mUpdateInterval);
        aMapLocationClientOption.setMockEnable(true);
        aMapLocationClientOption.setNeedAddress(true);
        return aMapLocationClientOption;
    }

    @Override // com.ido.life.location.BaseLocationPresenter, com.ido.life.base.IBaseLocation
    public void stopLocation() {
        super.stopLocation();
        CommonLogUtil.d("stopLocation");
        AMapLocationClient aMapLocationClient = this.mLocationClient;
        if (aMapLocationClient != null && aMapLocationClient.isStarted()) {
            this.mLocationClient.stopLocation();
            this.mLocationClient.onDestroy();
        }
        this.mLocationClient = null;
        this.mLocationOption = null;
    }

    @Override // com.amap.api.location.AMapLocationListener
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
            CommonLogUtil.d(aMapLocation.toString());
            LocationMessage locationMessage = new LocationMessage(109, new LatLngBean(aMapLocation.getLatitude(), aMapLocation.getLongitude()));
            locationMessage.accurac = aMapLocation.getAccuracy();
            locationMessage.city = aMapLocation.getCity();
            locationMessage.country = aMapLocation.getCountry();
            locationMessage.speed = StringUtil.format("%.2f", Float.valueOf(aMapLocation.getSpeed()));
            locationMessage.gpsAccuracyStatus = translateGps(aMapLocation.getGpsAccuracyStatus());
            CommonLogUtil.d(TAG, aMapLocation.toStr());
            onLocationChanged(locationMessage);
            return;
        }
        super.onLocationChanged((LocationMessage) null);
    }
}