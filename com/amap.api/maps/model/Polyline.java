package com.amap.api.maps.model;

import android.text.TextUtils;
import com.amap.api.mapcore.util.hn;
import com.amap.api.maps.interfaces.IGlOverlayLayer;
import com.autonavi.amap.mapcore.interfaces.IPolyline;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Polyline extends BaseOverlay {
    private WeakReference<IGlOverlayLayer> glOverlayLayerRef;
    private PolylineOptions options;
    private IPolyline polylineDelegate;

    public Polyline(IGlOverlayLayer iGlOverlayLayer, PolylineOptions polylineOptions) {
        super("");
        this.glOverlayLayerRef = new WeakReference<>(iGlOverlayLayer);
        this.options = polylineOptions;
    }

    public Polyline(IGlOverlayLayer iGlOverlayLayer, PolylineOptions polylineOptions, String str) {
        super(str);
        this.glOverlayLayerRef = new WeakReference<>(iGlOverlayLayer);
        this.options = polylineOptions;
    }

    public Polyline(IPolyline iPolyline) {
        super("");
        this.polylineDelegate = iPolyline;
    }

    public void remove() {
        try {
            if (this.polylineDelegate != null) {
                this.polylineDelegate.remove();
                return;
            }
            IGlOverlayLayer iGlOverlayLayer = this.glOverlayLayerRef.get();
            if (iGlOverlayLayer != null) {
                iGlOverlayLayer.removeOverlay(this.overlayName);
            }
            this.overlayName = null;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public String getId() {
        try {
            if (this.polylineDelegate != null) {
                return this.polylineDelegate.getId();
            }
            return this.overlayName;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void setPoints(List<LatLng> list) {
        try {
            if (this.polylineDelegate != null) {
                this.polylineDelegate.setPoints(list);
                return;
            }
            synchronized (this) {
                if (this.options != null) {
                    this.options.setPoints(list);
                    a();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public List<LatLng> getPoints() {
        try {
            if (this.polylineDelegate != null) {
                return this.polylineDelegate.getPoints();
            }
            if (this.options != null) {
                return this.options.getPoints();
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void setGeodesic(boolean z) {
        try {
            if (this.polylineDelegate != null) {
                if (this.polylineDelegate.isGeodesic() != z) {
                    List<LatLng> points = getPoints();
                    this.polylineDelegate.setGeodesic(z);
                    setPoints(points);
                }
            } else if (this.options != null) {
                this.options.geodesic(z);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean isGeodesic() {
        IPolyline iPolyline = this.polylineDelegate;
        if (iPolyline != null) {
            return iPolyline.isGeodesic();
        }
        PolylineOptions polylineOptions = this.options;
        return polylineOptions != null && polylineOptions.isGeodesic();
    }

    public void setDottedLine(boolean z) {
        IPolyline iPolyline = this.polylineDelegate;
        if (iPolyline != null) {
            iPolyline.setDottedLine(z);
            return;
        }
        PolylineOptions polylineOptions = this.options;
        if (polylineOptions != null) {
            polylineOptions.setDottedLine(z);
            a();
        }
    }

    public boolean isDottedLine() {
        IPolyline iPolyline = this.polylineDelegate;
        if (iPolyline != null) {
            return iPolyline.isDottedLine();
        }
        PolylineOptions polylineOptions = this.options;
        if (polylineOptions != null) {
            return polylineOptions.isDottedLine();
        }
        return false;
    }

    public void setWidth(float f2) {
        try {
            if (this.polylineDelegate != null) {
                this.polylineDelegate.setWidth(f2);
            } else if (this.options != null) {
                this.options.width(f2);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public float getWidth() {
        try {
            if (this.polylineDelegate != null) {
                return this.polylineDelegate.getWidth();
            }
            if (this.options != null) {
                return this.options.getWidth();
            }
            return 0.0f;
        } catch (Throwable th) {
            th.printStackTrace();
            return 0.0f;
        }
    }

    public void setColor(int i) {
        try {
            if (this.polylineDelegate != null) {
                this.polylineDelegate.setColor(i);
            } else if (this.options != null) {
                this.options.color(i);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public int getColor() {
        try {
            if (this.polylineDelegate != null) {
                return this.polylineDelegate.getColor();
            }
            if (this.options != null) {
                return this.options.getColor();
            }
            return 0;
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public void setZIndex(float f2) {
        try {
            if (this.polylineDelegate != null) {
                this.polylineDelegate.setZIndex(f2);
            } else if (this.options != null) {
                this.options.zIndex(f2);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public float getZIndex() {
        try {
            if (this.polylineDelegate != null) {
                return this.polylineDelegate.getZIndex();
            }
            if (this.options != null) {
                return this.options.getZIndex();
            }
            return 0.0f;
        } catch (Throwable th) {
            th.printStackTrace();
            return 0.0f;
        }
    }

    public void setVisible(boolean z) {
        try {
            if (this.polylineDelegate != null) {
                this.polylineDelegate.setVisible(z);
            } else if (this.options != null) {
                this.options.visible(z);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean isVisible() {
        try {
            if (this.polylineDelegate != null) {
                return this.polylineDelegate.isVisible();
            }
            if (this.options != null) {
                return this.options.isVisible();
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Polyline)) {
            return false;
        }
        try {
            if (this.polylineDelegate != null) {
                return this.polylineDelegate.equalsRemote(((Polyline) obj).polylineDelegate);
            }
            return super.equals(obj) || ((Polyline) obj).getId() == getId();
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public int hashCode() {
        try {
            if (this.polylineDelegate != null) {
                return this.polylineDelegate.hashCodeRemote();
            }
            return super.hashCode();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public LatLng getNearestLatLng(LatLng latLng) {
        IPolyline iPolyline = this.polylineDelegate;
        if (iPolyline != null) {
            return iPolyline.getNearestLatLng(latLng);
        }
        IGlOverlayLayer iGlOverlayLayer = this.glOverlayLayerRef.get();
        if (iGlOverlayLayer != null) {
            return iGlOverlayLayer.getNearestLatLng(this.options, latLng);
        }
        return null;
    }

    public void setTransparency(float f2) {
        IPolyline iPolyline = this.polylineDelegate;
        if (iPolyline != null) {
            iPolyline.setTransparency(f2);
            return;
        }
        PolylineOptions polylineOptions = this.options;
        if (polylineOptions != null) {
            polylineOptions.transparency(f2);
            a();
        }
    }

    public void setAboveMaskLayer(boolean z) {
        IPolyline iPolyline = this.polylineDelegate;
        if (iPolyline != null) {
            iPolyline.setAboveMaskLayer(z);
            return;
        }
        PolylineOptions polylineOptions = this.options;
        if (polylineOptions != null) {
            polylineOptions.aboveMaskLayer(z);
            a();
        }
    }

    public void setCustomTexture(BitmapDescriptor bitmapDescriptor) {
        IPolyline iPolyline = this.polylineDelegate;
        if (iPolyline != null) {
            iPolyline.setCustomTexture(bitmapDescriptor);
            return;
        }
        PolylineOptions polylineOptions = this.options;
        if (polylineOptions != null) {
            polylineOptions.setCustomTexture(bitmapDescriptor);
            a();
        }
    }

    public void setOptions(PolylineOptions polylineOptions) {
        IPolyline iPolyline = this.polylineDelegate;
        if (iPolyline != null) {
            iPolyline.setOptions(polylineOptions);
        } else {
            this.options = polylineOptions;
            a();
        }
    }

    public PolylineOptions getOptions() {
        IPolyline iPolyline = this.polylineDelegate;
        if (iPolyline != null) {
            return iPolyline.getOptions();
        }
        return this.options;
    }

    public void setCustemTextureIndex(List<Integer> list) {
        IPolyline iPolyline = this.polylineDelegate;
        if (iPolyline != null) {
            iPolyline.setCustemTextureIndex(list);
            return;
        }
        synchronized (this) {
            if (this.options != null) {
                this.options.setCustomTextureIndex(list);
                a();
            }
        }
    }

    public void setShownRatio(float f2) {
        try {
            if (this.polylineDelegate != null) {
                this.polylineDelegate.setShownRatio(f2);
            } else if (this.options != null) {
                this.options.setShownRatio(f2);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setShownRange(float f2, float f3) {
        try {
            if (this.polylineDelegate != null) {
                this.polylineDelegate.setShowRange(f2, f3);
            } else if (this.options != null) {
                this.options.setShownRange(f2, f3);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public float getShownRatio() {
        try {
            if (this.polylineDelegate != null) {
                return this.polylineDelegate.getShownRatio();
            }
            if (this.options != null) {
                return this.options.getShownRatio();
            }
            return -1.0f;
        } catch (Throwable th) {
            th.printStackTrace();
            return -1.0f;
        }
    }

    public void showPolylineRangeEnabled(boolean z) {
        try {
            if (this.polylineDelegate == null && this.options != null) {
                this.options.showPolylineRangeEnabled(z);
                a();
            }
        } catch (Throwable unused) {
        }
    }

    public boolean isShowPolylineRangeEnable() {
        try {
            if (this.polylineDelegate == null && this.options != null) {
                return this.options.isShowPolylineRangeEnable();
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public void setPolylineShowRange(float f2, float f3) {
        try {
            if (this.polylineDelegate == null && this.options != null) {
                this.options.setPolylineShowRange(f2, f3);
                a();
            }
        } catch (Throwable unused) {
        }
    }

    public float getPolylineShownRangeBegin() {
        try {
            if (this.polylineDelegate == null && this.options != null) {
                return this.options.getPolylineShownRangeBegin();
            }
            return 0.0f;
        } catch (Throwable th) {
            th.printStackTrace();
            return 0.0f;
        }
    }

    public float getPolylineShownRangeEnd() {
        try {
            if (this.polylineDelegate == null && this.options != null) {
                return this.options.getPolylineShownRangeEnd();
            }
            return 0.0f;
        } catch (Throwable th) {
            th.printStackTrace();
            return 0.0f;
        }
    }

    public void setFootPrintTexture(BitmapDescriptor bitmapDescriptor) {
        try {
            if (this.polylineDelegate == null && this.options != null) {
                this.options.setFootPrintTexture(bitmapDescriptor);
                a();
            }
        } catch (Throwable unused) {
        }
    }

    public BitmapDescriptor getFootPrintTexture() {
        try {
            if (this.polylineDelegate == null && this.options != null) {
                return this.options.getFootPrintTexture();
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void setFootPrintGap(float f2) {
        try {
            if (this.polylineDelegate == null && this.options != null) {
                this.options.setFootPrintGap(f2);
                a();
            }
        } catch (Throwable unused) {
        }
    }

    public float getFootPrintGap() {
        try {
            if (this.polylineDelegate == null && this.options != null) {
                return this.options.getFootPrintGap();
            }
            return 0.0f;
        } catch (Throwable th) {
            th.printStackTrace();
            return 0.0f;
        }
    }

    public void setEraseTexture(boolean z, BitmapDescriptor bitmapDescriptor) {
        try {
            if (this.polylineDelegate == null && this.options != null) {
                this.options.setEraseTexture(z, bitmapDescriptor);
                a();
            }
        } catch (Throwable unused) {
        }
    }

    public BitmapDescriptor getEraseTexture() {
        try {
            if (this.polylineDelegate == null && this.options != null) {
                return this.options.getEraseTexture();
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public boolean getEraseVisible() {
        try {
            if (this.polylineDelegate == null && this.options != null) {
                return this.options.getEraseVisible();
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public void setEraseColor(boolean z, int i) {
        try {
            if (this.polylineDelegate == null && this.options != null) {
                this.options.setEraseColor(z, i);
                a();
            }
        } catch (Throwable unused) {
        }
    }

    public int getEraseColor() {
        try {
            if (this.polylineDelegate == null && this.options != null) {
                return this.options.getEraseColor();
            }
            return 0;
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    private void a() {
        try {
            synchronized (this) {
                IGlOverlayLayer iGlOverlayLayer = this.glOverlayLayerRef.get();
                if (!TextUtils.isEmpty(this.overlayName) && iGlOverlayLayer != null) {
                    setOptionPointList(this.options);
                    if (iGlOverlayLayer != null) {
                        iGlOverlayLayer.updateOption(this.overlayName, this.options);
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    public void setCustomTextureList(List<BitmapDescriptor> list) {
        try {
            if (this.polylineDelegate != null) {
                this.polylineDelegate.setCustomTextureList(list);
            } else {
                this.options.setCustomTextureList(list);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    protected void setOptionPointList(Object obj) {
        try {
            Class<?> cls = obj.getClass();
            Field declaredField = cls.getDeclaredField("isPointsUpdated");
            if (declaredField == null) {
                return;
            }
            declaredField.setAccessible(true);
            if (declaredField.getBoolean(obj)) {
                List<LatLng> points = ((PolylineOptions) obj).getPoints();
                double[] dArr = new double[points.size() * 2];
                for (int i = 0; i < points.size(); i++) {
                    int i2 = i * 2;
                    dArr[i2] = points.get(i).latitude;
                    dArr[i2 + 1] = points.get(i).longitude;
                }
                Field declaredField2 = cls.getDeclaredField("pointList");
                if (declaredField2 == null) {
                    return;
                }
                declaredField2.setAccessible(true);
                declaredField2.set(obj, dArr);
            }
        } catch (Throwable th) {
            hn.c(th, "Polyline", "setOptionPointList");
        }
    }
}