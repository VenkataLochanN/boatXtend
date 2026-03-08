package com.autonavi.amap.mapcore;

import com.autonavi.ae.gmap.maploader.Pools;

/* JADX INFO: loaded from: classes.dex */
public class DPoint {
    private static final Pools.SynchronizedPool<DPoint> M_POOL = new Pools.SynchronizedPool<>(32);
    public double x;
    public double y;

    public static DPoint obtain() {
        DPoint dPointAcquire = M_POOL.acquire();
        if (dPointAcquire == null) {
            return new DPoint();
        }
        dPointAcquire.set(0.0d, 0.0d);
        return dPointAcquire;
    }

    public static DPoint obtain(double d2, double d3) {
        DPoint dPointAcquire = M_POOL.acquire();
        if (dPointAcquire == null) {
            return new DPoint(d2, d3);
        }
        dPointAcquire.set(d2, d3);
        return dPointAcquire;
    }

    public void recycle() {
        M_POOL.release(this);
    }

    public DPoint() {
    }

    public DPoint(double d2, double d3) {
        this.x = d2;
        this.y = d3;
    }

    private void set(double d2, double d3) {
        this.x = d2;
        this.y = d3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DPoint)) {
            return false;
        }
        DPoint dPoint = (DPoint) obj;
        return Double.doubleToLongBits(this.x) == Double.doubleToLongBits(dPoint.x) && Double.doubleToLongBits(this.y) == Double.doubleToLongBits(dPoint.y);
    }
}