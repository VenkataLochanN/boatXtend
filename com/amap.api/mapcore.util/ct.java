package com.amap.api.mapcore.util;

import android.os.RemoteException;
import android.util.Log;
import com.amap.api.maps.interfaces.IGlOverlayLayer;
import com.amap.api.maps.model.HeatMapItem;
import com.amap.api.maps.model.HeatMapLayerOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.WeightedLatLng;
import com.autonavi.amap.mapcore.interfaces.IHeatMapLayer;
import com.autonavi.amap.mapcore.interfaces.IOverlay;
import com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate;
import com.autonavi.base.amap.mapcore.AMapNativeHeatMapLayer;
import com.autonavi.base.amap.mapcore.MapConfig;

/* JADX INFO: compiled from: HeatMapLayerDelegateImp.java */
/* JADX INFO: loaded from: classes.dex */
public class ct implements IHeatMapLayer, IOverlayDelegate {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private IGlOverlayLayer f457b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f459d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f461f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private de f462g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private HeatMapLayerOptions f463h;
    private boolean i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    long f456a = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f458c = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float f460e = 0.0f;

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public boolean calMapFPoint() throws RemoteException {
        return false;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public boolean checkInBounds() {
        return true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean equalsRemote(IOverlay iOverlay) throws RemoteException {
        return false;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public int hashCodeRemote() throws RemoteException {
        return 0;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isAboveMaskLayer() {
        return false;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public boolean isDrawFinish() {
        return false;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setAboveMaskLayer(boolean z) {
    }

    public ct(IGlOverlayLayer iGlOverlayLayer) {
        this.i = false;
        try {
            this.i = false;
            this.f457b = iGlOverlayLayer;
            this.f459d = getId();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public String getId() {
        if (this.f459d == null) {
            this.f459d = this.f457b.createId("HeatMapLayer");
        }
        return this.f459d;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setZIndex(float f2) {
        try {
            this.f460e = f2;
            this.f457b.changeOverlayIndex();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public float getZIndex() {
        return this.f460e;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setVisible(boolean z) {
        this.f458c = z;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isVisible() {
        return this.f458c;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void destroy() {
        synchronized (this) {
            this.i = true;
            if (this.f456a != -1) {
                AMapNativeHeatMapLayer.nativeDestroy(this.f456a);
                this.f456a = -1L;
            }
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public void draw(MapConfig mapConfig) throws RemoteException {
        try {
            if (this.i) {
                return;
            }
            if (this.f457b != null && this.f462g == null) {
                this.f462g = this.f457b.getGLShaderManager();
            }
            if (this.f462g == null || mapConfig == null || !this.f458c) {
                return;
            }
            if (this.f456a != -1) {
                synchronized (this) {
                    if (this.f456a != -1) {
                        if (this.f461f && a()) {
                            double[] dArr = new double[this.f463h.getData().size() * 3];
                            double d2 = Double.NaN;
                            double d3 = Double.NaN;
                            int i = 0;
                            for (WeightedLatLng weightedLatLng : this.f463h.getData()) {
                                if (weightedLatLng != null && weightedLatLng.latLng != null) {
                                    int i2 = i * 3;
                                    dArr[i2 + 0] = weightedLatLng.latLng.latitude;
                                    dArr[i2 + 1] = weightedLatLng.latLng.longitude;
                                    dArr[i2 + 2] = weightedLatLng.intensity;
                                    double d4 = weightedLatLng.latLng.latitude;
                                    if (Double.isNaN(d2)) {
                                        d2 = d4;
                                    }
                                    if (Double.isNaN(d3)) {
                                        d3 = d4;
                                    }
                                    if (d4 > d3) {
                                        d3 = d4;
                                    }
                                    if (d4 >= d2) {
                                        d4 = d2;
                                    }
                                    d2 = d4;
                                } else {
                                    Log.e("mapcore", "read file failed");
                                }
                                i++;
                            }
                            AMapNativeHeatMapLayer.nativeSetOptions(this.f456a, dArr, (int) this.f463h.getMaxIntensity(), this.f463h.getSize(), this.f463h.getGradient().getColors(), this.f463h.getGradient().getStartPoints(), this.f463h.getMaxZoom(), this.f463h.getMinZoom(), this.f463h.getOpacity(), this.f463h.getGap(), this.f463h.getType(), (Double.isNaN(d2) || Double.isNaN(d3)) ? 0.0d : (d2 + d3) / 2.0d);
                            this.f461f = false;
                        }
                        AMapNativeHeatMapLayer.nativeRender(this.f456a, mapConfig.getViewMatrix(), mapConfig.getProjectionMatrix(), (int) mapConfig.getSX(), (int) mapConfig.getSY(), mapConfig.getSZ());
                    }
                }
                return;
            }
            this.f456a = AMapNativeHeatMapLayer.nativeCreate();
            if (this.f456a == -1 || this.f462g == null) {
                return;
            }
            AMapNativeHeatMapLayer.nativeSetGLShaderManager(this.f456a, this.f462g.a());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private boolean a() {
        HeatMapLayerOptions heatMapLayerOptions = this.f463h;
        return (heatMapLayerOptions == null || heatMapLayerOptions.getData() == null || this.f463h.getData().size() <= 0 || this.f463h.getGradient() == null || this.f463h.getGradient().getColors() == null || this.f463h.getGradient().getColors().length <= 0 || this.f463h.getGradient().getStartPoints() == null || this.f463h.getGradient().getStartPoints().length <= 0) ? false : true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void remove() throws RemoteException {
        IGlOverlayLayer iGlOverlayLayer = this.f457b;
        if (iGlOverlayLayer == null || iGlOverlayLayer.removeOverlay(this.f459d, true)) {
            return;
        }
        destroy();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IHeatMapLayer
    public HeatMapItem getHeatMapItem(LatLng latLng) {
        Object objNativeGetHeatMapItem;
        if (latLng == null) {
            return null;
        }
        long j = this.f456a;
        if (j == -1 || (objNativeGetHeatMapItem = AMapNativeHeatMapLayer.nativeGetHeatMapItem(j, latLng.latitude, latLng.longitude)) == null || !(objNativeGetHeatMapItem instanceof HeatMapItem)) {
            return null;
        }
        return (HeatMapItem) objNativeGetHeatMapItem;
    }

    public void a(de deVar) {
        this.f462g = deVar;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IHeatMapLayer
    public HeatMapLayerOptions getOptions() {
        return this.f463h;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IHeatMapLayer
    public void setOptions(HeatMapLayerOptions heatMapLayerOptions) {
        this.f463h = heatMapLayerOptions;
        HeatMapLayerOptions heatMapLayerOptions2 = this.f463h;
        if (heatMapLayerOptions2 != null) {
            this.f460e = heatMapLayerOptions2.getZIndex();
            this.f458c = this.f463h.isVisible();
        }
        this.f461f = true;
    }
}