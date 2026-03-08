package com.autonavi.base.amap.mapcore.message;

import com.autonavi.ae.gmap.maploader.Pools;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.base.ae.gmap.GLMapState;

/* JADX INFO: loaded from: classes.dex */
public class RotateGestureMapMessage extends AbstractGestureMapMessage {
    private static final Pools.SynchronizedPool<RotateGestureMapMessage> M_POOL = new Pools.SynchronizedPool<>(256);
    public float angleDelta;
    public int pivotX;
    public int pivotY;

    @Override // com.autonavi.base.amap.mapcore.message.AbstractGestureMapMessage, com.autonavi.base.ae.gmap.AbstractMapMessage
    public int getType() {
        return 2;
    }

    public static RotateGestureMapMessage obtain(int i, float f2, int i2, int i3) {
        RotateGestureMapMessage rotateGestureMapMessageAcquire = M_POOL.acquire();
        if (rotateGestureMapMessageAcquire == null) {
            return new RotateGestureMapMessage(i, f2, i2, i3);
        }
        rotateGestureMapMessageAcquire.reset();
        rotateGestureMapMessageAcquire.setParams(i, f2, i2, i3);
        return rotateGestureMapMessageAcquire;
    }

    public void recycle() {
        M_POOL.release(this);
    }

    public static void destory() {
        M_POOL.destory();
    }

    private void setParams(int i, float f2, int i2, int i3) {
        setState(i);
        this.angleDelta = f2;
        this.pivotX = i2;
        this.pivotY = i3;
    }

    public RotateGestureMapMessage(int i, float f2, int i2, int i3) {
        super(i);
        this.pivotX = 0;
        this.pivotY = 0;
        this.angleDelta = 0.0f;
        setParams(i, f2, i2, i3);
        this.angleDelta = f2;
        this.pivotX = i2;
        this.pivotY = i3;
    }

    @Override // com.autonavi.base.amap.mapcore.message.AbstractGestureMapMessage
    public void runCameraUpdate(GLMapState gLMapState) {
        IPoint iPointObtain;
        float mapAngle = gLMapState.getMapAngle() + this.angleDelta;
        if (this.isGestureScaleByMapCenter) {
            gLMapState.setMapAngle(mapAngle);
            gLMapState.recalculate();
            return;
        }
        int i = this.pivotX;
        int i2 = this.pivotY;
        if (this.isUseAnchor) {
            i = this.anchorX;
            i2 = this.anchorY;
        }
        IPoint iPointObtain2 = null;
        if (i > 0 || i2 > 0) {
            iPointObtain2 = IPoint.obtain();
            iPointObtain = IPoint.obtain();
            win2geo(gLMapState, i, i2, iPointObtain2);
            gLMapState.setMapGeoCenter(iPointObtain2.x, iPointObtain2.y);
        } else {
            iPointObtain = null;
        }
        gLMapState.setMapAngle(mapAngle);
        gLMapState.recalculate();
        if (i > 0 || i2 > 0) {
            win2geo(gLMapState, i, i2, iPointObtain);
            if (iPointObtain2 != null) {
                gLMapState.setMapGeoCenter((iPointObtain2.x * 2) - iPointObtain.x, (iPointObtain2.y * 2) - iPointObtain.y);
            }
            gLMapState.recalculate();
        }
        if (iPointObtain2 != null) {
            iPointObtain2.recycle();
        }
        if (iPointObtain != null) {
            iPointObtain.recycle();
        }
    }
}