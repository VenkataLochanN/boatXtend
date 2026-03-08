package com.autonavi.base.amap.mapcore;

import android.graphics.PointF;
import com.autonavi.ae.gmap.maploader.Pools;

/* JADX INFO: loaded from: classes.dex */
public class FPoint extends PointF {
    private static final Pools.SynchronizedPool<FPoint> M_POOL = new Pools.SynchronizedPool<>(32);

    public static FPoint obtain() {
        FPoint fPointAcquire = M_POOL.acquire();
        if (fPointAcquire == null) {
            return new FPoint();
        }
        fPointAcquire.set(0.0f, 0.0f);
        return fPointAcquire;
    }

    public static FPoint obtain(float f2, float f3) {
        FPoint fPointAcquire = M_POOL.acquire();
        if (fPointAcquire == null) {
            return new FPoint(f2, f3);
        }
        fPointAcquire.set(f2, f3);
        return fPointAcquire;
    }

    public void recycle() {
        M_POOL.release(this);
    }

    public FPoint() {
    }

    public FPoint(float f2, float f3) {
        this.x = f2;
        this.y = f3;
    }

    @Override // android.graphics.PointF
    public boolean equals(Object obj) {
        FPoint fPoint = (FPoint) obj;
        return fPoint != null && this.x == fPoint.x && this.y == fPoint.y;
    }

    @Override // android.graphics.PointF
    public int hashCode() {
        Float.floatToIntBits(this.x);
        return Float.floatToIntBits(this.y) * 37;
    }
}