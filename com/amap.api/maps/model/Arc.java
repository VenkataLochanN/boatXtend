package com.amap.api.maps.model;

import android.text.TextUtils;
import com.amap.api.maps.interfaces.IGlOverlayLayer;
import com.autonavi.amap.mapcore.interfaces.IArc;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class Arc extends BaseOverlay {
    private WeakReference<IGlOverlayLayer> glOverlayLayerRef;
    private IArc iArcDel;
    private ArcOptions options;

    public Arc(IArc iArc) {
        super("");
        this.iArcDel = iArc;
    }

    public Arc(IGlOverlayLayer iGlOverlayLayer, ArcOptions arcOptions, String str) {
        super(str);
        this.glOverlayLayerRef = new WeakReference<>(iGlOverlayLayer);
        this.options = arcOptions;
    }

    public void remove() {
        try {
            if (this.iArcDel != null) {
                this.iArcDel.remove();
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
            if (this.iArcDel != null) {
                return this.iArcDel.getId();
            }
            return this.overlayName;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void setStrokeWidth(float f2) {
        try {
            if (this.iArcDel != null) {
                this.iArcDel.setStrokeWidth(f2);
            } else if (this.options != null) {
                this.options.strokeWidth(f2);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public float getStrokeWidth() {
        try {
            if (this.iArcDel != null) {
                return this.iArcDel.getStrokeWidth();
            }
            if (this.options != null) {
                return this.options.getStrokeWidth();
            }
            return 0.0f;
        } catch (Throwable th) {
            th.printStackTrace();
            return 0.0f;
        }
    }

    public void setStrokeColor(int i) {
        try {
            if (this.iArcDel != null) {
                this.iArcDel.setStrokeColor(i);
            } else if (this.options != null) {
                this.options.strokeColor(i);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public int getStrokeColor() {
        try {
            if (this.iArcDel != null) {
                return this.iArcDel.getStrokeColor();
            }
            if (this.options != null) {
                return this.options.getStrokeColor();
            }
            return 0;
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public void setZIndex(float f2) {
        try {
            if (this.iArcDel != null) {
                this.iArcDel.setZIndex(f2);
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
            if (this.iArcDel != null) {
                return this.iArcDel.getZIndex();
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
            if (this.iArcDel != null) {
                this.iArcDel.setVisible(z);
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
            if (this.iArcDel != null) {
                return this.iArcDel.isVisible();
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
        if (obj != null && (obj instanceof Arc)) {
            try {
                if (this.iArcDel != null) {
                    return this.iArcDel.equalsRemote(((Arc) obj).iArcDel);
                }
                return super.equals(obj) || ((Arc) obj).getId() == getId();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return false;
    }

    public int hashCode() {
        try {
            if (this.iArcDel != null) {
                return this.iArcDel.hashCodeRemote();
            }
            return super.hashCode();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
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