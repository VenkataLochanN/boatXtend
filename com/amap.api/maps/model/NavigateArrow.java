package com.amap.api.maps.model;

import android.text.TextUtils;
import com.amap.api.maps.interfaces.IGlOverlayLayer;
import com.autonavi.amap.mapcore.interfaces.INavigateArrow;
import java.lang.ref.WeakReference;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class NavigateArrow extends BaseOverlay {
    private WeakReference<IGlOverlayLayer> glOverlayLayerRef;
    private INavigateArrow navigateArrowDelegate;
    private NavigateArrowOptions options;

    public NavigateArrow(INavigateArrow iNavigateArrow) {
        super("");
        this.navigateArrowDelegate = iNavigateArrow;
    }

    public NavigateArrow(IGlOverlayLayer iGlOverlayLayer, NavigateArrowOptions navigateArrowOptions, String str) {
        super(str);
        this.glOverlayLayerRef = new WeakReference<>(iGlOverlayLayer);
        this.options = navigateArrowOptions;
    }

    public void remove() {
        try {
            if (this.navigateArrowDelegate != null) {
                this.navigateArrowDelegate.remove();
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
            if (this.navigateArrowDelegate != null) {
                return this.navigateArrowDelegate.getId();
            }
            return this.overlayName;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void setPoints(List<LatLng> list) {
        try {
            if (this.navigateArrowDelegate != null) {
                this.navigateArrowDelegate.setPoints(list);
            } else if (this.options != null) {
                this.options.setPoints(list);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public List<LatLng> getPoints() {
        try {
            if (this.navigateArrowDelegate != null) {
                return this.navigateArrowDelegate.getPoints();
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

    public void setWidth(float f2) {
        try {
            if (this.navigateArrowDelegate != null) {
                this.navigateArrowDelegate.setWidth(f2);
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
            if (this.navigateArrowDelegate != null) {
                return this.navigateArrowDelegate.getWidth();
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

    public void setTopColor(int i) {
        try {
            if (this.navigateArrowDelegate != null) {
                this.navigateArrowDelegate.setTopColor(i);
            } else if (this.options != null) {
                this.options.topColor(i);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public int getTopColor() {
        try {
            if (this.navigateArrowDelegate != null) {
                return this.navigateArrowDelegate.getTopColor();
            }
            if (this.options != null) {
                return this.options.getTopColor();
            }
            return 0;
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public void setSideColor(int i) {
        try {
            if (this.navigateArrowDelegate != null) {
                this.navigateArrowDelegate.setSideColor(i);
            } else if (this.options != null) {
                this.options.sideColor(i);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Deprecated
    public int getSideColor() {
        try {
            if (this.navigateArrowDelegate != null) {
                return this.navigateArrowDelegate.getSideColor();
            }
            if (this.options != null) {
                return this.options.getSideColor();
            }
            return 0;
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public void setZIndex(float f2) {
        try {
            if (this.navigateArrowDelegate != null) {
                this.navigateArrowDelegate.setZIndex(f2);
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
            if (this.navigateArrowDelegate != null) {
                return this.navigateArrowDelegate.getZIndex();
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
            if (this.navigateArrowDelegate != null) {
                this.navigateArrowDelegate.setVisible(z);
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
            if (this.navigateArrowDelegate != null) {
                return this.navigateArrowDelegate.isVisible();
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

    public void set3DModel(boolean z) {
        try {
            if (this.navigateArrowDelegate != null) {
                this.navigateArrowDelegate.set3DModel(z);
            } else if (this.options != null) {
                this.options.set3DModel(z);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean is3DModel() {
        try {
            if (this.navigateArrowDelegate != null) {
                return this.navigateArrowDelegate.is3DModel();
            }
            if (this.options != null) {
                return this.options.is3DModel();
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof NavigateArrow)) {
            try {
                if (this.navigateArrowDelegate != null) {
                    return this.navigateArrowDelegate.equalsRemote(((NavigateArrow) obj).navigateArrowDelegate);
                }
                return super.equals(obj) || ((NavigateArrow) obj).getId() == getId();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return false;
    }

    public int hashCode() {
        try {
            if (this.navigateArrowDelegate != null) {
                return this.navigateArrowDelegate.hashCodeRemote();
            }
            return super.hashCode();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    private void a() {
        IGlOverlayLayer iGlOverlayLayer = this.glOverlayLayerRef.get();
        if (TextUtils.isEmpty(this.overlayName) || iGlOverlayLayer == null) {
            return;
        }
        iGlOverlayLayer.updateOption(this.overlayName, this.options);
    }
}