package com.ido.life.location;

import android.content.Context;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.bean.LatLngBean;

/* JADX INFO: loaded from: classes2.dex */
public class BaiduLocation extends BaseLocationPresenter {
    private static final String TAG = BaiduLocation.class.getSimpleName();
    private LocationClient mClient;
    private LocationClientOption mClientOption;

    @Override // com.ido.life.location.BaseLocationPresenter, com.ido.life.base.IBaseLocation
    public void startLocation(Context context, boolean z) {
        super.startLocation(context, z);
        if (checkPermission()) {
            if (this.mClientOption == null) {
                this.mClientOption = getClientOption();
            }
            if (this.mClient == null) {
                this.mClient = new LocationClient(context, this.mClientOption);
            }
            this.mClient.registerLocationListener(new BDAbstractLocationListener() { // from class: com.ido.life.location.BaiduLocation.1
                @Override // com.baidu.location.BDAbstractLocationListener
                public void onReceiveLocation(BDLocation bDLocation) {
                    if (bDLocation != null) {
                        CommonLogUtil.d(BaiduLocation.TAG, bDLocation.toString());
                        LocationMessage locationMessage = new LocationMessage(109, new LatLngBean(bDLocation.getLatitude(), bDLocation.getLongitude()));
                        locationMessage.city = bDLocation.getCity();
                        locationMessage.country = bDLocation.getCountry();
                        BaiduLocation.this.onLocationChanged(locationMessage);
                        return;
                    }
                    BaiduLocation.this.onLocationChanged(null);
                }
            });
            this.mClient.start();
            return;
        }
        super.onLocationChanged(null);
    }

    private LocationClientOption getClientOption() {
        LocationClientOption locationClientOption = new LocationClientOption();
        locationClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        locationClientOption.setIsNeedAddress(true);
        locationClientOption.setIsNeedAltitude(true);
        locationClientOption.setIsNeedLocationDescribe(true);
        locationClientOption.setOpenGps(true);
        return locationClientOption;
    }

    @Override // com.ido.life.location.BaseLocationPresenter, com.ido.life.base.IBaseLocation
    public void stopLocation() {
        super.stopLocation();
        LocationClient locationClient = this.mClient;
        if (locationClient == null || !locationClient.isStarted()) {
            return;
        }
        this.mClient.stop();
    }

    @Override // com.ido.life.location.BaseLocationPresenter, com.ido.life.base.IBaseLocation
    public String[] permissions() {
        return new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
    }
}