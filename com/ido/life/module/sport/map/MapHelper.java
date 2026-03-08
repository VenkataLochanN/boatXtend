package com.ido.life.module.sport.map;

import com.boat.Xtend.two.R;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.bean.LatLngBean;
import com.ido.life.location.GpsCoordinateUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MapHelper {
    static String MAP_SOUCE_KEY = "MAP_SOUCE_KEY";
    static int MAP_TYPE_BAIDU = 0;
    static int MAP_TYPE_GAODE = 1;
    static int MAP_TYPE_GOOGLE = 2;
    private static final String TAG = "MapHelper";

    public static void gpsToBaidu(LatLngBean latLngBean) {
    }

    public static double getDistance(LatLngBean latLngBean, LatLngBean latLngBean2) {
        return calculateLineDistance(latLngBean, latLngBean2);
    }

    public static double LantitudeLongitudeDist(double d2, double d3, double d4, double d5) {
        return calculateLineDistance(new LatLngBean(d3, d2), new LatLngBean(d5, d4));
    }

    public static LatLngBean translate(LatLngBean latLngBean) {
        double[] dArrCalWGS84toGCJ02 = GpsCoordinateUtils.calWGS84toGCJ02(latLngBean.latitude, latLngBean.longitude);
        LatLngBean latLngBeanM22clone = latLngBean.m22clone();
        latLngBeanM22clone.setLatitude(dArrCalWGS84toGCJ02[0]);
        latLngBeanM22clone.setLongitude(dArrCalWGS84toGCJ02[1]);
        return latLngBeanM22clone;
    }

    public static float calculateLineDistance(LatLngBean latLngBean, LatLngBean latLngBean2) {
        if (latLngBean == null || latLngBean2 == null) {
            return 0.0f;
        }
        try {
            double d2 = latLngBean.longitude;
            double d3 = d2 * 0.01745329251994329d;
            double d4 = latLngBean.latitude * 0.01745329251994329d;
            double d5 = latLngBean2.longitude * 0.01745329251994329d;
            double d6 = latLngBean2.latitude * 0.01745329251994329d;
            double dSin = Math.sin(d3);
            double dSin2 = Math.sin(d4);
            double dCos = Math.cos(d3);
            double dCos2 = Math.cos(d4);
            double dSin3 = Math.sin(d5);
            double dSin4 = Math.sin(d6);
            double dCos3 = Math.cos(d5);
            double dCos4 = Math.cos(d6);
            double[] dArr = {dCos * dCos2, dCos2 * dSin, dSin2};
            double[] dArr2 = {dCos3 * dCos4, dCos4 * dSin3, dSin4};
            return (float) (Math.asin(Math.sqrt((((dArr[0] - dArr2[0]) * (dArr[0] - dArr2[0])) + ((dArr[1] - dArr2[1]) * (dArr[1] - dArr2[1]))) + ((dArr[2] - dArr2[2]) * (dArr[2] - dArr2[2]))) / 2.0d) * 1.27420015798544E7d);
        } catch (Throwable th) {
            th.printStackTrace();
            return 0.0f;
        }
    }

    public static void gaoDeToBaidu(LatLngBean latLngBean) {
        double longitude = latLngBean.getLongitude();
        double latitude = latLngBean.getLatitude();
        double dSqrt = Math.sqrt((longitude * longitude) + (latitude * latitude)) + (Math.sin(latitude * 52.35987755982988d) * 2.0E-5d);
        double dAtan2 = Math.atan2(latitude, longitude) + (Math.cos(longitude * 52.35987755982988d) * 3.0E-6d);
        latLngBean.longitude = (Math.cos(dAtan2) * dSqrt) + 0.0065d;
        latLngBean.latitude = (dSqrt * Math.sin(dAtan2)) + 0.006d;
    }

    public static int getMapSource() {
        return MAP_TYPE_GOOGLE;
    }

    public static List<String> mapList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(ResourceUtil.getString(R.string.sport_map_baidu));
        arrayList.add(ResourceUtil.getString(R.string.sport_map_gaode));
        arrayList.add(ResourceUtil.getString(R.string.sport_map_google));
        return arrayList;
    }

    public static String getMapSourceStr() {
        int mapSource = getMapSource();
        if (mapSource == MAP_TYPE_BAIDU) {
            return ResourceUtil.getString(R.string.sport_map_baidu);
        }
        if (mapSource == MAP_TYPE_GAODE) {
            return ResourceUtil.getString(R.string.sport_map_gaode);
        }
        return ResourceUtil.getString(R.string.sport_map_google);
    }
}