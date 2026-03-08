package com.amap.api.maps.model;

import android.text.TextUtils;
import com.amap.api.maps.interfaces.IGlOverlayLayer;
import com.autonavi.amap.mapcore.interfaces.IHeatMapLayer;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class HeatMapLayer extends BaseOverlay {
    private WeakReference<IGlOverlayLayer> glOverlayLayerRef;
    private IHeatMapLayer mHeatMapLayer;
    private HeatMapLayerOptions options;

    public HeatMapLayer(IHeatMapLayer iHeatMapLayer) {
        super("");
        this.mHeatMapLayer = iHeatMapLayer;
    }

    public HeatMapLayer(IGlOverlayLayer iGlOverlayLayer, HeatMapLayerOptions heatMapLayerOptions, String str) {
        super(str);
        this.glOverlayLayerRef = new WeakReference<>(iGlOverlayLayer);
        this.options = heatMapLayerOptions;
        a();
    }

    public void destroy() {
        try {
            if (this.mHeatMapLayer != null) {
                this.mHeatMapLayer.remove();
            } else {
                IGlOverlayLayer iGlOverlayLayer = this.glOverlayLayerRef.get();
                if (iGlOverlayLayer != null) {
                    iGlOverlayLayer.removeOverlay(this.overlayName);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public String getId() {
        try {
            if (this.mHeatMapLayer != null) {
                return this.mHeatMapLayer.getId();
            }
            return this.overlayName;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void setZIndex(float f2) {
        try {
            if (this.mHeatMapLayer != null) {
                this.mHeatMapLayer.setZIndex(f2);
            } else if (this.options != null) {
                this.options.zIndex(f2);
                a();
            }
        } catch (Throwable unused) {
        }
    }

    public float getZIndex() {
        try {
            if (this.mHeatMapLayer != null) {
                return this.mHeatMapLayer.getZIndex();
            }
            if (this.options != null) {
                return this.options.getZIndex();
            }
            return 0.0f;
        } catch (Throwable unused) {
            return 0.0f;
        }
    }

    public void setVisible(boolean z) {
        try {
            if (this.mHeatMapLayer != null) {
                this.mHeatMapLayer.setVisible(z);
            } else if (this.options != null) {
                this.options.visible(z);
                a();
            }
        } catch (Throwable unused) {
        }
    }

    public boolean isVisible() {
        try {
            if (this.mHeatMapLayer != null) {
                return this.mHeatMapLayer.isVisible();
            }
            return this.options != null && this.options.isVisible();
        } catch (Throwable unused) {
            return false;
        }
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof HeatMapLayer)) {
            try {
                if (this.mHeatMapLayer != null) {
                    return this.mHeatMapLayer.equalsRemote(((HeatMapLayer) obj).mHeatMapLayer);
                }
                return super.equals(obj) || ((HeatMapLayer) obj).getId().equals(getId());
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return false;
    }

    public int hashCode() {
        try {
            if (this.mHeatMapLayer != null) {
                return this.mHeatMapLayer.hashCodeRemote();
            }
            return super.hashCode();
        } catch (Throwable unused) {
            return 0;
        }
    }

    public HeatMapItem getHeatMapItem(LatLng latLng) {
        try {
            if (this.mHeatMapLayer != null) {
                return this.mHeatMapLayer.getHeatMapItem(latLng);
            }
            IGlOverlayLayer iGlOverlayLayer = this.glOverlayLayerRef.get();
            if (iGlOverlayLayer == null) {
                return null;
            }
            Object nativeProperties = iGlOverlayLayer.getNativeProperties(this.overlayName, "getHeatMapItem", new Object[]{latLng});
            if (nativeProperties instanceof HeatMapItem) {
                return (HeatMapItem) nativeProperties;
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public HeatMapLayerOptions getOptions() {
        try {
            if (this.mHeatMapLayer != null) {
                return this.mHeatMapLayer.getOptions();
            }
            return this.options;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void setOptions(HeatMapLayerOptions heatMapLayerOptions) {
        try {
            if (this.mHeatMapLayer != null) {
                this.mHeatMapLayer.setOptions(heatMapLayerOptions);
            } else {
                this.options = heatMapLayerOptions;
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a() {
        try {
            IGlOverlayLayer iGlOverlayLayer = this.glOverlayLayerRef.get();
            if (TextUtils.isEmpty(this.overlayName) || iGlOverlayLayer == null) {
                return;
            }
            iGlOverlayLayer.updateOption(this.overlayName, this.options);
        } catch (Throwable unused) {
        }
    }
}