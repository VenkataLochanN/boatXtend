package com.amap.api.maps.utils;

import android.util.Pair;
import com.amap.api.mapcore.util.er;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.autonavi.amap.mapcore.DPoint;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SpatialRelationUtil {
    public static final int A_CIRCLE_DEGREE = 360;
    public static final int A_HALF_CIRCLE_DEGREE = 180;
    public static final int MIN_OFFSET_DEGREE = 50;
    public static final int MIN_POLYLINE_POINT_SIZE = 2;

    public static Pair<Integer, LatLng> calShortestDistancePoint(List<LatLng> list, LatLng latLng, float f2, double d2) {
        if (list != null && latLng != null) {
            try {
                if (list.size() != 0) {
                    ArrayList arrayList = new ArrayList();
                    int i = 0;
                    for (LatLng latLng2 : list) {
                        arrayList.add(DPoint.obtain(latLng2.latitude, latLng2.longitude));
                        if (latLng2.equals(latLng)) {
                            return new Pair<>(Integer.valueOf(i), latLng);
                        }
                        i++;
                    }
                    Pair<Integer, DPoint> pairCalShortestDistancePoint = calShortestDistancePoint(arrayList, DPoint.obtain(latLng.latitude, latLng.longitude), f2);
                    if (pairCalShortestDistancePoint != null) {
                        DPoint dPoint = (DPoint) pairCalShortestDistancePoint.second;
                        if (AMapUtils.calculateLineDistance(new LatLng(dPoint.x, dPoint.y), latLng) < d2) {
                            return new Pair<>(pairCalShortestDistancePoint.first, new LatLng(((DPoint) pairCalShortestDistancePoint.second).x, ((DPoint) pairCalShortestDistancePoint.second).y));
                        }
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return null;
    }

    public static Pair<Integer, LatLng> calShortestDistancePoint(List<LatLng> list, LatLng latLng) {
        if (list != null && latLng != null) {
            try {
                if (list.size() != 0) {
                    ArrayList arrayList = new ArrayList();
                    int i = 0;
                    for (LatLng latLng2 : list) {
                        arrayList.add(DPoint.obtain(latLng2.latitude, latLng2.longitude));
                        if (latLng2.equals(latLng)) {
                            return new Pair<>(Integer.valueOf(i), latLng);
                        }
                        i++;
                    }
                    Pair<Integer, DPoint> pairCalShortestDistancePoint = calShortestDistancePoint(arrayList, DPoint.obtain(latLng.latitude, latLng.longitude));
                    if (pairCalShortestDistancePoint != null) {
                        return new Pair<>(pairCalShortestDistancePoint.first, new LatLng(((DPoint) pairCalShortestDistancePoint.second).x, ((DPoint) pairCalShortestDistancePoint.second).y));
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return null;
    }

    public static Pair<Integer, DPoint> calShortestDistancePoint(List<DPoint> list, DPoint dPoint) {
        return calShortestDistancePoint(list, dPoint, -1.0f);
    }

    public static Pair<Integer, DPoint> calShortestDistancePoint(List<DPoint> list, DPoint dPoint, float f2) {
        int i;
        double dDoubleValue;
        List<DPoint> list2 = list;
        DPoint dPoint2 = dPoint;
        if (list2 == null || dPoint2 == null || list.size() == 0 || list.size() < 2) {
            return null;
        }
        DPoint dPoint3 = list2.get(0);
        int size = list.size();
        int i2 = 1;
        double d2 = 0.0d;
        DPoint dPoint4 = dPoint3;
        Pair<Integer, DPoint> pair = null;
        int i3 = 1;
        while (true) {
            int i4 = size - 1;
            if (i3 > i4) {
                return pair;
            }
            DPoint dPoint5 = list2.get(i3);
            if (i3 == i4 && dPoint5.equals(dPoint2)) {
                return new Pair<>(Integer.valueOf(i3), dPoint2);
            }
            if (!checkRotateIsMatch(dPoint4, dPoint5, f2)) {
                i = i3;
                dDoubleValue = d2;
            } else {
                if (dPoint4.equals(dPoint2)) {
                    return new Pair<>(Integer.valueOf(i3 - i2), dPoint2);
                }
                double d3 = d2;
                i = i3;
                Pair<Integer, DPoint> pair2 = pair;
                Pair<Double, DPoint> pairPointToSegDist = pointToSegDist(dPoint2.x, dPoint2.y, dPoint4.x, dPoint4.y, dPoint5.x, dPoint5.y);
                if (pair2 == null) {
                    dDoubleValue = ((Double) pairPointToSegDist.first).doubleValue();
                    pair = new Pair<>(Integer.valueOf(i - 1), pairPointToSegDist.second);
                } else if (d3 > ((Double) pairPointToSegDist.first).doubleValue()) {
                    dDoubleValue = ((Double) pairPointToSegDist.first).doubleValue();
                    pair = new Pair<>(Integer.valueOf(i - 1), pairPointToSegDist.second);
                } else {
                    dDoubleValue = d3;
                    pair = pair2;
                }
            }
            i2 = 1;
            d2 = dDoubleValue;
            dPoint4 = dPoint5;
            dPoint2 = dPoint;
            i3 = i + 1;
            list2 = list;
        }
    }

    private static boolean checkRotateIsMatch(DPoint dPoint, DPoint dPoint2, float f2) {
        if (f2 == -1.0f) {
            return true;
        }
        if (dPoint != null && dPoint2 != null) {
            float fAbs = Math.abs((er.a(dPoint, dPoint2) + 360.0f) - f2) % 360.0f;
            if (fAbs > 180.0f) {
                fAbs = 360.0f - fAbs;
            }
            if (fAbs < 50.0f) {
                return true;
            }
        }
        return false;
    }

    private static Pair<Double, DPoint> pointToSegDist(double d2, double d3, double d4, double d5, double d6, double d7) {
        double d8 = d6 - d4;
        double d9 = d2 - d4;
        double d10 = d7 - d5;
        double d11 = d3 - d5;
        double d12 = (d8 * d9) + (d10 * d11);
        if (d12 <= 0.0d) {
            return new Pair<>(Double.valueOf(Math.sqrt((d9 * d9) + (d11 * d11))), new DPoint(d4, d5));
        }
        double d13 = (d8 * d8) + (d10 * d10);
        if (d12 >= d13) {
            double d14 = d2 - d6;
            double d15 = d3 - d7;
            return new Pair<>(Double.valueOf(Math.sqrt((d14 * d14) + (d15 * d15))), new DPoint(d6, d7));
        }
        double d16 = d12 / d13;
        double d17 = d4 + (d8 * d16);
        double d18 = d5 + (d10 * d16);
        double d19 = d2 - d17;
        double d20 = d18 - d3;
        return new Pair<>(Double.valueOf(Math.sqrt((d19 * d19) + (d20 * d20))), new DPoint(d17, d18));
    }
}