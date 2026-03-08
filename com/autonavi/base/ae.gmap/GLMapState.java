package com.autonavi.base.ae.gmap;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import com.autonavi.amap.api.mapcore.IGLMapState;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.VirtualEarthProjection;
import com.autonavi.base.amap.mapcore.FPoint;

/* JADX INFO: loaded from: classes.dex */
public class GLMapState implements IGLMapState {
    private boolean isNewInstance;
    private long nativeEngineInstance;
    private long nativeStateInstance;

    public static native float nativeCalMapZoomScalefactor(int i, int i2, float f2);

    public static native float nativeCalculateMapZoomer(long j, int i, int i2, int i3, int i4, int i5);

    public static native float nativeGetCameraDegree(long j);

    public static native float nativeGetGLUnitWithWin(long j, int i);

    public static native float nativeGetMapAngle(long j);

    public static native void nativeGetMapCenter(long j, Point point);

    public static native double nativeGetMapCenterXDouble(long j);

    public static native double nativeGetMapCenterYDouble(long j);

    public static native float nativeGetMapZoomer(long j);

    public static native void nativeGetPixel20Bound(long j, Rect rect, int i, int i2);

    public static native void nativeGetProjectionMatrix(long j, float[] fArr);

    public static native float nativeGetSkyHeight(long j);

    public static native void nativeGetViewMatrix(long j, float[] fArr);

    public static native long nativeNewInstance(int i, long j);

    public static native void nativeP20ToScreenPoint(long j, int i, int i2, int i3, PointF pointF);

    public static native void nativeRecalculate(long j);

    public static native void nativeScreenToP20Point(long j, float f2, float f3, Point point);

    public static native void nativeSetCameraDegree(long j, float f2);

    public static native void nativeSetMapAngle(long j, float f2);

    public static native void nativeSetMapCenter(long j, double d2, double d3);

    private static native void nativeSetMapState(int i, long j, long j2);

    public static native void nativeSetMapZoomer(long j, float f2);

    public static native void nativeStateDestroy(long j);

    public GLMapState(int i, long j) {
        this.nativeStateInstance = 0L;
        this.nativeEngineInstance = 0L;
        this.isNewInstance = false;
        if (j != 0) {
            this.nativeEngineInstance = j;
            this.nativeStateInstance = nativeNewInstance(i, j);
            this.isNewInstance = true;
        }
    }

    public GLMapState(long j, long j2) {
        this.nativeStateInstance = 0L;
        this.nativeEngineInstance = 0L;
        this.isNewInstance = false;
        if (j != 0) {
            this.nativeEngineInstance = j;
            this.nativeStateInstance = j2;
            this.isNewInstance = true;
        }
    }

    public static void lonlat2Geo(double d2, double d3, IPoint iPoint) {
        Point pointLatLongToPixels = VirtualEarthProjection.latLongToPixels(d3, d2, 20);
        iPoint.x = pointLatLongToPixels.x;
        iPoint.y = pointLatLongToPixels.y;
    }

    public static void geo2LonLat(int i, int i2, DPoint dPoint) {
        DPoint dPointPixelsToLatLong = VirtualEarthProjection.pixelsToLatLong(i, i2, 20);
        dPoint.x = dPointPixelsToLatLong.x;
        dPoint.y = dPointPixelsToLatLong.y;
        dPointPixelsToLatLong.recycle();
    }

    @Override // com.autonavi.amap.api.mapcore.IGLMapState
    public void screenToP20Point(int i, int i2, Point point) {
        long j = this.nativeStateInstance;
        if (j != 0) {
            nativeScreenToP20Point(j, i, i2, point);
        }
    }

    public void p20ToScreenPoint(int i, int i2, FPoint fPoint) {
        long j = this.nativeStateInstance;
        if (j != 0) {
            nativeP20ToScreenPoint(j, i, i2, 0, fPoint);
        }
    }

    @Override // com.autonavi.amap.api.mapcore.IGLMapState
    public void getMapGeoCenter(IPoint iPoint) {
        nativeGetMapCenter(this.nativeStateInstance, iPoint);
    }

    @Override // com.autonavi.amap.api.mapcore.IGLMapState
    public DPoint getMapGeoCenter() {
        DPoint dPoint = new DPoint();
        dPoint.x = nativeGetMapCenterXDouble(this.nativeStateInstance);
        dPoint.y = nativeGetMapCenterYDouble(this.nativeStateInstance);
        return dPoint;
    }

    public double getMapGeoCenterX() {
        return nativeGetMapCenterXDouble(this.nativeStateInstance);
    }

    public double getMapGeoCenterY() {
        return nativeGetMapCenterXDouble(this.nativeStateInstance);
    }

    @Override // com.autonavi.amap.api.mapcore.IGLMapState
    public void setMapGeoCenter(double d2, double d3) {
        long j = this.nativeStateInstance;
        if (j != 0) {
            nativeSetMapCenter(j, d2, d3);
        }
    }

    @Override // com.autonavi.amap.api.mapcore.IGLMapState
    public void setCameraDegree(float f2) {
        long j = this.nativeStateInstance;
        if (j != 0) {
            nativeSetCameraDegree(j, f2);
        }
    }

    @Override // com.autonavi.amap.api.mapcore.IGLMapState
    public void setMapAngle(float f2) {
        long j = this.nativeStateInstance;
        if (j != 0) {
            nativeSetMapAngle(j, f2);
        }
    }

    @Override // com.autonavi.amap.api.mapcore.IGLMapState
    public void setMapZoomer(float f2) {
        long j = this.nativeStateInstance;
        if (j != 0) {
            nativeSetMapZoomer(j, f2);
        }
    }

    @Override // com.autonavi.amap.api.mapcore.IGLMapState
    public float getMapZoomer() {
        long j = this.nativeStateInstance;
        if (j != 0) {
            return nativeGetMapZoomer(j);
        }
        return 0.0f;
    }

    @Override // com.autonavi.amap.api.mapcore.IGLMapState
    public float getCameraDegree() {
        long j = this.nativeStateInstance;
        if (j != 0) {
            return nativeGetCameraDegree(j);
        }
        return 0.0f;
    }

    @Override // com.autonavi.amap.api.mapcore.IGLMapState
    public float getMapAngle() {
        long j = this.nativeStateInstance;
        if (j != 0) {
            return nativeGetMapAngle(j);
        }
        return 0.0f;
    }

    @Override // com.autonavi.amap.api.mapcore.IGLMapState
    public void recalculate() {
        long j = this.nativeStateInstance;
        if (j != 0) {
            nativeRecalculate(j);
        }
    }

    @Override // com.autonavi.amap.api.mapcore.IGLMapState
    public void recycle() {
        long j = this.nativeStateInstance;
        if (j != 0 && this.isNewInstance) {
            nativeStateDestroy(j);
        }
        this.nativeStateInstance = 0L;
    }

    public void reset() {
        if (this.nativeStateInstance != 0) {
            recycle();
        }
        long j = this.nativeEngineInstance;
        if (j != 0) {
            this.nativeStateInstance = nativeNewInstance(1, j);
        }
    }

    public float getMapLenWithWin(int i) {
        return getGLUnitWithWin(i);
    }

    public float getGLUnitWithWin(int i) {
        long j = this.nativeStateInstance;
        if (j != 0) {
            return nativeGetGLUnitWithWin(j, i);
        }
        return 0.0f;
    }

    public void getPixel20Bound(Rect rect, int i, int i2) {
        long j = this.nativeStateInstance;
        if (j != 0) {
            nativeGetPixel20Bound(j, rect, i, i2);
        }
    }

    public long getNativeInstance() {
        return this.nativeStateInstance;
    }

    public void getViewMatrix(float[] fArr) {
        if (fArr == null || fArr.length != 16) {
            return;
        }
        nativeGetViewMatrix(this.nativeStateInstance, fArr);
    }

    public void getProjectionMatrix(float[] fArr) {
        if (fArr == null || fArr.length != 16) {
            return;
        }
        nativeGetProjectionMatrix(this.nativeStateInstance, fArr);
    }

    public float getSkyHeight() {
        long j = this.nativeStateInstance;
        if (j != 0) {
            return nativeGetSkyHeight(j);
        }
        return 0.0f;
    }

    @Override // com.autonavi.amap.api.mapcore.IGLMapState
    public float calculateMapZoomer(int i, int i2, int i3, int i4, int i5) {
        long j = this.nativeStateInstance;
        if (j != 0) {
            return nativeCalculateMapZoomer(j, i, i2, i3, i4, i5);
        }
        return 3.0f;
    }

    public void setNativeMapengineState(int i, long j) {
        if (j != 0) {
            long j2 = this.nativeStateInstance;
            if (j2 == 0) {
                return;
            }
            this.nativeEngineInstance = j;
            nativeSetMapState(i, j, j2);
        }
    }

    public static float calMapZoomScalefactor(int i, int i2, int i3) {
        return nativeCalMapZoomScalefactor(i, i2, i3);
    }

    public String toString() {
        return "instance: " + this.nativeStateInstance + " center: " + getMapGeoCenter().x + " , " + getMapGeoCenter().y + " bearing:" + getCameraDegree() + "  tilt:" + getMapAngle() + "  zoom:" + getMapZoomer() + "  ";
    }
}