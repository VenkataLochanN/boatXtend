package com.ido.life.boatservice;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.NumUtil;
import com.ido.life.bean.LatLngBean;
import com.ido.life.constants.Constants;
import com.ido.life.module.sport.bean.LocationMessage;
import com.ido.life.util.DateUtil;
import com.ido.life.util.SPHelper;
import com.ido.life.util.SPUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class GDLocationManager {
    private static final String TAG = "GDLocationManager";
    private static volatile GDLocationManager instance = null;
    public static boolean isTest = false;
    private LocationService locationService;
    List<LocationStringListener> locationStringListeners = new ArrayList();
    double lat = 22.67586d;
    double lng = 113.97072399999999d;
    int count = 0;
    AMapLocationListener bdAbstractLocationListener = new AMapLocationListener() { // from class: com.ido.life.boatservice.-$$Lambda$GDLocationManager$W03s8a3R6XXWeHa9UB__UgHk690
        @Override // com.amap.api.location.AMapLocationListener
        public final void onLocationChanged(AMapLocation aMapLocation) {
            this.f$0.lambda$new$0$GDLocationManager(aMapLocation);
        }
    };

    public interface LocationStringListener {
        void onReceiveLocation(LocationMessage locationMessage);
    }

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

    private GDLocationManager(Context context) {
        this.locationService = new LocationService(context.getApplicationContext());
    }

    public static GDLocationManager getInstance(Context context) {
        if (instance == null) {
            synchronized (GDLocationManager.class) {
                if (instance == null) {
                    instance = new GDLocationManager(context);
                }
            }
        }
        return instance;
    }

    public void startLocation(LocationStringListener locationStringListener) {
        stopLocation(locationStringListener);
        addBDLocationListener(locationStringListener);
        this.locationService.registerListener(this.bdAbstractLocationListener);
        LocationService locationService = this.locationService;
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        this.locationService.start();
        CommonLogUtil.d("startLocation");
    }

    private void addBDLocationListener(LocationStringListener locationStringListener) {
        if (this.locationStringListeners.contains(locationStringListener)) {
            return;
        }
        this.locationStringListeners.add(locationStringListener);
    }

    public /* synthetic */ void lambda$new$0$GDLocationManager(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            CommonLogUtil.d(TAG, "onLocationChanged: " + aMapLocation.toString() + AppInfo.DELIM + aMapLocation.getLocationType() + AppInfo.DELIM + aMapLocation.getGpsAccuracyStatus());
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), TAG, "定位信息:" + aMapLocation.toString() + "定位的类型," + aMapLocation.getLocationType() + "定位的强度," + aMapLocation.getGpsAccuracyStatus() + "定位的可信度，" + aMapLocation.getTrustedLevel());
            StringBuilder sb = new StringBuilder();
            sb.append(NumUtil.formatPoint(aMapLocation.getLongitude(), 6));
            sb.append(AppInfo.DELIM);
            sb.append(NumUtil.formatPoint(aMapLocation.getLatitude(), 6));
            SPUtils.put(Constants.POINT_KEY, sb.toString());
            if (isTest) {
                int i = this.count;
                if (i == 0) {
                    this.lat = aMapLocation.getLatitude();
                    this.lng = aMapLocation.getLongitude();
                } else if (i % 2 == 0) {
                    this.lat += (Math.random() * 0.001d) + 5.0E-5d;
                    this.lng += (Math.random() * 0.002d) + 7.0E-6d;
                } else {
                    this.lat += (Math.random() * 5.0E-4d) + 1.7E-5d;
                    this.lng += (Math.random() * 1.0E-4d) + 7.0E-5d;
                }
                this.count++;
            } else {
                this.lat = aMapLocation.getLatitude();
                this.lng = aMapLocation.getLongitude();
            }
            LocationMessage locationMessage = new LocationMessage();
            SPHelper.saveLocation(this.lat, this.lng);
            locationMessage.latitude = this.lat;
            locationMessage.longitude = this.lng;
            LatLngBean latLngBean = new LatLngBean();
            latLngBean.setLatitude(this.lat);
            latLngBean.setLongitude(this.lng);
            locationMessage.accurac = aMapLocation.getAccuracy();
            locationMessage.city = aMapLocation.getCity();
            locationMessage.country = aMapLocation.getCountry();
            locationMessage.speed = aMapLocation.getSpeed();
            locationMessage.currentTimeMillis = DateUtil.getFormatDate("yyyy-MM-dd HH:mm:ss", aMapLocation.getTime());
            locationMessage.gpsAccuracyStatus = translateGps(aMapLocation.getGpsAccuracyStatus());
            if (!TextUtils.isEmpty(locationMessage.country)) {
                SPHelper.setIsChina("中国".contentEquals(locationMessage.country));
            }
            for (LocationStringListener locationStringListener : this.locationStringListeners) {
                CommonLogUtil.d("locationStringListeners=" + locationStringListener);
                locationStringListener.onReceiveLocation(locationMessage);
            }
        }
    }

    public void stopLocation(LocationStringListener locationStringListener) {
        if (this.locationStringListeners.contains(locationStringListener)) {
            CommonLogUtil.d("stopLocation");
            this.locationService.unregisterListener(this.bdAbstractLocationListener);
            this.locationService.stop();
            this.locationStringListeners.remove(locationStringListener);
        }
    }
}