package com.amap.api.mapcore.util;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.autonavi.amap.mapcore.Inner_3dMap_location;
import com.autonavi.amap.mapcore.Inner_3dMap_locationListener;
import com.autonavi.amap.mapcore.Inner_3dMap_locationOption;

/* JADX INFO: compiled from: MapLocClientUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class km {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Inner_3dMap_location f1565a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    jz f1566b = null;

    /* JADX INFO: renamed from: com.amap.api.mapcore.util.km$1, reason: invalid class name */
    /* JADX INFO: compiled from: MapLocClientUtils.java */
    static /* synthetic */ class AnonymousClass1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1567a = new int[Inner_3dMap_locationOption.Inner_3dMap_Enum_LocationMode.values().length];

        static {
            try {
                f1567a[Inner_3dMap_locationOption.Inner_3dMap_Enum_LocationMode.Battery_Saving.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1567a[Inner_3dMap_locationOption.Inner_3dMap_Enum_LocationMode.Device_Sensors.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1567a[Inner_3dMap_locationOption.Inner_3dMap_Enum_LocationMode.Hight_Accuracy.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static AMapLocationClientOption a(Inner_3dMap_locationOption inner_3dMap_locationOption) {
        AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
        aMapLocationClientOption.setInterval(inner_3dMap_locationOption.getInterval());
        AMapLocationClientOption.AMapLocationMode aMapLocationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy;
        int i = AnonymousClass1.f1567a[inner_3dMap_locationOption.getLocationMode().ordinal()];
        aMapLocationClientOption.setLocationMode(i != 1 ? i != 2 ? AMapLocationClientOption.AMapLocationMode.Hight_Accuracy : AMapLocationClientOption.AMapLocationMode.Device_Sensors : AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        aMapLocationClientOption.setOnceLocation(inner_3dMap_locationOption.isOnceLocation());
        aMapLocationClientOption.setNeedAddress(inner_3dMap_locationOption.isNeedAddress());
        aMapLocationClientOption.setLocationCacheEnable(inner_3dMap_locationOption.isLocationCacheEnable());
        return aMapLocationClientOption;
    }

    public static Inner_3dMap_location a(AMapLocation aMapLocation) {
        Inner_3dMap_location inner_3dMap_location = new Inner_3dMap_location("");
        if (aMapLocation != null) {
            try {
                inner_3dMap_location.setLatitude(aMapLocation.getLatitude());
                inner_3dMap_location.setLongitude(aMapLocation.getLongitude());
                inner_3dMap_location.setAccuracy(aMapLocation.getAccuracy());
                inner_3dMap_location.setBearing(aMapLocation.getBearing());
                inner_3dMap_location.setAltitude(aMapLocation.getAltitude());
                inner_3dMap_location.setProvider(aMapLocation.getProvider());
                inner_3dMap_location.setSpeed(aMapLocation.getSpeed());
                inner_3dMap_location.setTime(aMapLocation.getTime());
                inner_3dMap_location.setErrorCode(aMapLocation.getErrorCode());
                inner_3dMap_location.setErrorInfo(aMapLocation.getErrorInfo());
                inner_3dMap_location.setLocationType(aMapLocation.getLocationType());
                inner_3dMap_location.setLocationDetail(aMapLocation.getLocationDetail());
                inner_3dMap_location.setProvince(aMapLocation.getProvince());
                inner_3dMap_location.setCity(aMapLocation.getCity());
                inner_3dMap_location.setCityCode(aMapLocation.getCityCode());
                inner_3dMap_location.setCountry(aMapLocation.getCountry());
                inner_3dMap_location.setDistrict(aMapLocation.getDistrict());
                inner_3dMap_location.setAddress(aMapLocation.getAddress());
                inner_3dMap_location.setAdCode(aMapLocation.getAdCode());
                inner_3dMap_location.setExtras(aMapLocation.getExtras());
                inner_3dMap_location.setRoad(aMapLocation.getRoad());
            } catch (Throwable th) {
                kg.a(th, "Util", "converterLocation");
            }
        }
        return inner_3dMap_location;
    }

    public static void a(Object obj, Inner_3dMap_locationOption inner_3dMap_locationOption) {
        if (obj == null) {
            return;
        }
        ((AMapLocationClient) obj).setLocationOption(a(inner_3dMap_locationOption));
    }

    public final void a(Object obj, Inner_3dMap_locationListener inner_3dMap_locationListener) {
        if (this.f1566b == null) {
            this.f1566b = new jz();
        }
        this.f1566b.a(inner_3dMap_locationListener);
        ((AMapLocationClient) obj).setLocationListener(this.f1566b);
    }
}