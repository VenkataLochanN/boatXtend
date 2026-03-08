package com.baidu.location;

import android.util.Log;
import com.baidu.mapsdkplatform.comapi.location.CoordinateType;

/* JADX INFO: loaded from: classes.dex */
public abstract class BDNotifyListener {
    public double mLatitude = Double.MIN_VALUE;
    public double mLongitude = Double.MIN_VALUE;
    public float mRadius = 0.0f;
    public float differDistance = 0.0f;
    public String mCoorType = null;
    public double mLatitudeC = Double.MIN_VALUE;
    public double mLongitudeC = Double.MIN_VALUE;
    public int Notified = 0;
    public boolean isAdded = false;
    public com.baidu.location.c.a mNotifyCache = null;

    public void SetNotifyLocation(double d2, double d3, float f2, String str) {
        this.mLatitude = d2;
        this.mLongitude = d3;
        if (f2 < 0.0f) {
            this.mRadius = 200.0f;
        } else {
            this.mRadius = f2;
        }
        if (str.equals(CoordinateType.GCJ02) || str.equals(BDLocation.BDLOCATION_GCJ02_TO_BD09) || str.equals("bd09ll") || str.equals("gps")) {
            this.mCoorType = str;
        } else {
            this.mCoorType = CoordinateType.GCJ02;
        }
        if (this.mCoorType.equals(CoordinateType.GCJ02)) {
            this.mLatitudeC = this.mLatitude;
            this.mLongitudeC = this.mLongitude;
        }
        if (this.isAdded) {
            this.Notified = 0;
            this.mNotifyCache.b(this);
        }
    }

    public void onNotify(BDLocation bDLocation, float f2) {
        Log.d("baidu_location_service", "new location, not far from the destination..." + f2);
    }
}