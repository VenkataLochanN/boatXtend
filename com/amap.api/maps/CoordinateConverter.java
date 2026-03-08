package com.amap.api.maps;

import android.content.Context;
import com.amap.api.mapcore.util.ai;
import com.amap.api.mapcore.util.ek;
import com.amap.api.mapcore.util.ep;
import com.amap.api.mapcore.util.hn;
import com.amap.api.maps.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public class CoordinateConverter {
    private static final String TAG = "CoordinateConverter";
    private Context ctx;
    private CoordType coordType = null;
    private LatLng sourceLatLng = null;

    public enum CoordType {
        BAIDU,
        MAPBAR,
        GPS,
        MAPABC,
        SOSOMAP,
        ALIYUN,
        GOOGLE
    }

    public CoordinateConverter(Context context) {
        this.ctx = context;
    }

    public CoordinateConverter from(CoordType coordType) {
        this.coordType = coordType;
        return this;
    }

    public CoordinateConverter coord(LatLng latLng) {
        this.sourceLatLng = latLng;
        return this;
    }

    public LatLng convert() {
        LatLng latLngA = null;
        if (this.coordType == null || this.sourceLatLng == null) {
            return null;
        }
        try {
            String str = "";
            switch (this.coordType) {
                case BAIDU:
                    latLngA = ai.a(this.sourceLatLng);
                    str = "baidu";
                    break;
                case MAPBAR:
                    latLngA = ai.b(this.ctx, this.sourceLatLng);
                    str = "mapbar";
                    break;
                case MAPABC:
                    str = "mapabc";
                    latLngA = this.sourceLatLng;
                    break;
                case SOSOMAP:
                    str = "sosomap";
                    latLngA = this.sourceLatLng;
                    break;
                case ALIYUN:
                    str = "aliyun";
                    latLngA = this.sourceLatLng;
                    break;
                case GOOGLE:
                    str = "google";
                    latLngA = this.sourceLatLng;
                    break;
                case GPS:
                    str = "gps";
                    latLngA = ai.a(this.ctx, this.sourceLatLng);
                    break;
            }
            ep.a(this.ctx, str);
            return latLngA;
        } catch (Throwable th) {
            th.printStackTrace();
            hn.c(th, TAG, "convert");
            return this.sourceLatLng;
        }
    }

    public static boolean isAMapDataAvailable(double d2, double d3) {
        return ek.a(d2, d3);
    }
}