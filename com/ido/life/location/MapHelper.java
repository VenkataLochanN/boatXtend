package com.ido.life.location;

import android.util.Pair;
import androidx.core.view.InputDeviceCompat;
import com.ido.life.bean.LatLngBean;
import com.ido.life.util.DateUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.UnitUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MapHelper {
    public static final int INVALID_TRAVEL_COLOR = 1295532630;
    public static final int INVALID_TRAVEL_DISTANCE = 300;
    private static final String TAG = "MapHelper";
    public static final List<Pair<Float, Float>> runSpeed = new ArrayList<Pair<Float, Float>>() { // from class: com.ido.life.location.MapHelper.1
        {
            Float fValueOf = Float.valueOf(0.0f);
            Float fValueOf2 = Float.valueOf(3.0f);
            add(new Pair(fValueOf, fValueOf2));
            Float fValueOf3 = Float.valueOf(5.0f);
            add(new Pair(fValueOf2, fValueOf3));
            Float fValueOf4 = Float.valueOf(6.0f);
            add(new Pair(fValueOf3, fValueOf4));
            Float fValueOf5 = Float.valueOf(7.0f);
            add(new Pair(fValueOf4, fValueOf5));
            Float fValueOf6 = Float.valueOf(8.0f);
            add(new Pair(fValueOf5, fValueOf6));
            Float fValueOf7 = Float.valueOf(9.0f);
            add(new Pair(fValueOf6, fValueOf7));
            Float fValueOf8 = Float.valueOf(10.0f);
            add(new Pair(fValueOf7, fValueOf8));
            add(new Pair(fValueOf8, Float.valueOf(Float.MAX_VALUE)));
        }
    };
    public static final List<Pair<Float, Float>> bikeSpeed = new ArrayList<Pair<Float, Float>>() { // from class: com.ido.life.location.MapHelper.2
        {
            Float fValueOf = Float.valueOf(0.0f);
            Float fValueOf2 = Float.valueOf(21.0f);
            add(new Pair(fValueOf, fValueOf2));
            Float fValueOf3 = Float.valueOf(22.0f);
            add(new Pair(fValueOf2, fValueOf3));
            Float fValueOf4 = Float.valueOf(23.0f);
            add(new Pair(fValueOf3, fValueOf4));
            Float fValueOf5 = Float.valueOf(24.0f);
            add(new Pair(fValueOf4, fValueOf5));
            Float fValueOf6 = Float.valueOf(25.0f);
            add(new Pair(fValueOf5, fValueOf6));
            Float fValueOf7 = Float.valueOf(26.0f);
            add(new Pair(fValueOf6, fValueOf7));
            Float fValueOf8 = Float.valueOf(30.0f);
            add(new Pair(fValueOf7, fValueOf8));
            add(new Pair(fValueOf8, Float.valueOf(Float.MAX_VALUE)));
        }
    };
    public static final List<Pair<Float, Float>> walkSpeed = new ArrayList<Pair<Float, Float>>() { // from class: com.ido.life.location.MapHelper.3
        {
            Float fValueOf = Float.valueOf(0.0f);
            Float fValueOf2 = Float.valueOf(3.0f);
            add(new Pair(fValueOf, fValueOf2));
            Float fValueOf3 = Float.valueOf(4.0f);
            add(new Pair(fValueOf2, fValueOf3));
            Float fValueOf4 = Float.valueOf(5.0f);
            add(new Pair(fValueOf3, fValueOf4));
            Float fValueOf5 = Float.valueOf(6.0f);
            add(new Pair(fValueOf4, fValueOf5));
            Float fValueOf6 = Float.valueOf(7.0f);
            add(new Pair(fValueOf5, fValueOf6));
            Float fValueOf7 = Float.valueOf(7.5f);
            add(new Pair(fValueOf6, fValueOf7));
            Float fValueOf8 = Float.valueOf(8.0f);
            add(new Pair(fValueOf7, fValueOf8));
            add(new Pair(fValueOf8, Float.valueOf(Float.MAX_VALUE)));
        }
    };
    public static final int Standard_Color = -16711936;
    public static final int[] colorArray = {Standard_Color, -8913152, -4456704, InputDeviceCompat.SOURCE_ANY, -17664, -30720, -43759, -65536};

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

    public static List<Integer> completeColorByMile(List<LatLngBean> list, int i, int i2, int i3, List<Integer> list2, List<Float> list3, boolean z) {
        int size;
        long j;
        List<LatLngBean> list4 = list;
        if (list4 == null || list.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int unitSet = RunTimeUtil.getInstance().getUnitSet();
        float f2 = i2 / 1000.0f;
        if ((unitSet == 2 ? UnitUtil.getKm2mile(f2) : f2) <= 1.0f) {
            if (i > 0) {
                setColorByPaceLow1KM(i3, list2, list, list3, arrayList, z);
            }
            return arrayList;
        }
        long longFromDateStr = 0;
        int i4 = 1;
        int i5 = 1;
        float f3 = 0.0f;
        long j2 = 0;
        float f4 = 0.0f;
        float f5 = 0.0f;
        while (i5 < list.size()) {
            int i6 = i5 - 1;
            float fCalculateLineDistance = calculateLineDistance(list4.get(i6), list4.get(i5));
            f3 += fCalculateLineDistance;
            try {
                longFromDateStr = (long) (longFromDateStr + ((DateUtil.getLongFromDateStr(list4.get(i5).currentTimeMillis) - DateUtil.getLongFromDateStr(list4.get(i6).currentTimeMillis)) / 1000.0f));
                if (fCalculateLineDistance > 300.0f) {
                    arrayList.add(Integer.valueOf(i6));
                    arrayList.add(Integer.valueOf(i5));
                    if (list3 != null) {
                        list3.add(Float.valueOf(0.0f));
                        list3.add(Float.valueOf(fCalculateLineDistance));
                    }
                } else {
                    float km2mile = f3 / 1000.0f;
                    if (unitSet == 2) {
                        km2mile = UnitUtil.getKm2mile(km2mile);
                    }
                    int km2mile2 = -1;
                    if (km2mile >= i4) {
                        if (j2 == 0) {
                            j = longFromDateStr;
                            f4 = f3;
                        } else {
                            f4 = f3 - f4;
                            j = longFromDateStr - j2;
                        }
                        if (j > 0) {
                            i4++;
                            km2mile2 = (int) ((unitSet == 2 ? UnitUtil.getKm2mile(f4 / 1000.0f) : f4 / 1000.0f) / (j / 3600.0f));
                        }
                        j2 = j;
                    } else if (i5 == list.size() - 1 && i > 0 && i2 > 0) {
                        km2mile2 = (int) (unitSet == 2 ? UnitUtil.getKm2mile(f2) / (i / 3600.0f) : f2 / (i / 3600.0f));
                    }
                    if (km2mile2 >= 0) {
                        if (list3 != null) {
                            list3.add(Float.valueOf(fCalculateLineDistance + f5));
                        }
                        setColorByPace(km2mile2, i3, list2);
                        if (z && (size = list2.size()) < i5 + 1) {
                            int i7 = size - 1;
                            int iIntValue = list2.get(i7).intValue();
                            list2.remove(i7);
                            int i8 = i5 - (size - 1);
                            for (int i9 = 0; i9 <= i8; i9++) {
                                list2.add(Integer.valueOf(iIntValue));
                            }
                        }
                        f5 = 0.0f;
                    } else {
                        f5 += fCalculateLineDistance;
                    }
                }
                i5++;
                list4 = list;
            } catch (Exception unused) {
            }
        }
        if (list3 != null && list3.size() > 0) {
            int size2 = list3.size();
            for (int i10 = 0; i10 < size2; i10++) {
                list3.set(i10, Float.valueOf(list3.get(i10).floatValue() / f3));
            }
        }
        return arrayList;
    }

    public static void setColorByPace(int i, int i2, List<Integer> list) {
        List<Pair<Float, Float>> list2;
        if (list == null) {
            return;
        }
        if (i2 == 48) {
            list2 = runSpeed;
        } else if (i2 == 50) {
            list2 = bikeSpeed;
        } else {
            list2 = walkSpeed;
        }
        if (list2 == null) {
            return;
        }
        int size = list2.size();
        for (int i3 = 0; i3 < size; i3++) {
            Pair<Float, Float> pair = list2.get(i3);
            float f2 = i;
            if (f2 >= ((Float) pair.first).floatValue() && f2 < ((Float) pair.second).floatValue()) {
                list.add(Integer.valueOf(colorArray[i3]));
                return;
            }
        }
    }

    public static void setColorByPaceLow1KM(int i, List<Integer> list, List<LatLngBean> list2, List<Float> list3, List<Integer> list4, boolean z) {
        List<Pair<Float, Float>> list5;
        if (list2 == null || list2.size() == 0) {
            return;
        }
        if (i == 48) {
            list5 = runSpeed;
        } else if (i == 50) {
            list5 = bikeSpeed;
        } else {
            list5 = walkSpeed;
        }
        if (list5 == null) {
            return;
        }
        int size = list2.size();
        float f2 = 0.0f;
        for (int i2 = 1; i2 < size; i2++) {
            int i3 = i2 - 1;
            float fCalculateLineDistance = calculateLineDistance(list2.get(i3), list2.get(i2));
            f2 += fCalculateLineDistance;
            if (fCalculateLineDistance >= 300.0f) {
                if (list4 != null) {
                    list4.add(Integer.valueOf(i3));
                    list4.add(Integer.valueOf(i2));
                }
                if (list != null) {
                    list.add(1295532630);
                    if (!z) {
                        list.add(1295532630);
                    }
                }
                if (list3 != null) {
                    list3.add(Float.valueOf(0.0f));
                }
            } else if (list != null) {
                list.add(Integer.valueOf(Standard_Color));
            }
            if (list3 != null) {
                list3.add(Float.valueOf(fCalculateLineDistance));
            }
        }
        if (list3 == null || list3.size() <= 0 || f2 <= 0.0f) {
            return;
        }
        int size2 = list3.size();
        for (int i4 = 0; i4 < size2; i4++) {
            list3.set(i4, Float.valueOf(list3.get(i4).floatValue() / f2));
        }
    }
}