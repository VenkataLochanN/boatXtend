package com.ido.life.util;

import com.ido.life.bean.LatLngBean;
import com.ido.life.bean.LatLngPoint;
import com.ido.life.location.MapHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class SportTravelUtil {
    double dMax;
    int end;
    int start;
    List<LatLngPoint> mLineInit = new ArrayList();
    ArrayList<LatLngPoint> mLineFilter = new ArrayList<>();

    public SportTravelUtil(List<LatLngBean> list, double d2) {
        if (list == null) {
            throw new IllegalArgumentException("传入的经纬度坐标list == null");
        }
        this.dMax = d2;
        this.start = 0;
        this.end = list.size() - 1;
        for (int i = 0; i < list.size(); i++) {
            this.mLineInit.add(new LatLngPoint(i, list.get(i).m22clone()));
        }
    }

    public ArrayList<LatLngBean> compress() {
        int size = this.mLineInit.size();
        ArrayList<LatLngPoint> arrayListCompressLine = compressLine((LatLngPoint[]) this.mLineInit.toArray(new LatLngPoint[size]), this.mLineFilter, this.start, this.end, this.dMax);
        arrayListCompressLine.add(this.mLineInit.get(0));
        arrayListCompressLine.add(this.mLineInit.get(size - 1));
        Collections.sort(arrayListCompressLine, new Comparator() { // from class: com.ido.life.util.-$$Lambda$SportTravelUtil$slsWkcEIU4lFpa2RcChNgIl7VrQ
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ((LatLngPoint) obj).compareTo((LatLngPoint) obj2);
            }
        });
        ArrayList<LatLngBean> arrayList = new ArrayList<>();
        Iterator<LatLngPoint> it = arrayListCompressLine.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().latLng);
        }
        return arrayList;
    }

    private ArrayList<LatLngPoint> compressLine(LatLngPoint[] latLngPointArr, ArrayList<LatLngPoint> arrayList, int i, int i2, double d2) {
        if (i < i2) {
            double d3 = 0.0d;
            int i3 = 0;
            for (int i4 = i + 1; i4 < i2; i4++) {
                double dDistToSegment = distToSegment(latLngPointArr[i], latLngPointArr[i2], latLngPointArr[i4]);
                if (dDistToSegment > d3) {
                    i3 = i4;
                    d3 = dDistToSegment;
                }
            }
            if (d3 >= d2) {
                arrayList.add(latLngPointArr[i3]);
                compressLine(latLngPointArr, arrayList, i, i3, d2);
                compressLine(latLngPointArr, arrayList, i3, i2, d2);
            }
        }
        return arrayList;
    }

    private double distToSegment(LatLngPoint latLngPoint, LatLngPoint latLngPoint2, LatLngPoint latLngPoint3) {
        double dAbs = Math.abs(MapHelper.calculateLineDistance(latLngPoint.latLng, latLngPoint2.latLng));
        double dAbs2 = Math.abs(MapHelper.calculateLineDistance(latLngPoint.latLng, latLngPoint3.latLng));
        double dAbs3 = Math.abs(MapHelper.calculateLineDistance(latLngPoint2.latLng, latLngPoint3.latLng));
        double d2 = ((dAbs + dAbs2) + dAbs3) / 2.0d;
        return (Math.sqrt(Math.abs((((d2 - dAbs) * d2) * (d2 - dAbs2)) * (d2 - dAbs3))) * 2.0d) / dAbs;
    }
}