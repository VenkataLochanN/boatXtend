package com.amap.api.maps.model;

import android.text.TextUtils;
import com.amap.api.maps.interfaces.IGlOverlayLayer;
import com.autonavi.amap.mapcore.interfaces.ICircle;
import java.lang.ref.WeakReference;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class Circle extends BaseOverlay {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    WeakReference<IGlOverlayLayer> f1853a;
    private ICircle iCircleDel;
    private CircleOptions options;

    public Circle(ICircle iCircle) {
        super("");
        this.iCircleDel = iCircle;
    }

    public Circle(IGlOverlayLayer iGlOverlayLayer, CircleOptions circleOptions, String str) {
        super(str);
        this.f1853a = new WeakReference<>(iGlOverlayLayer);
        this.options = circleOptions;
    }

    public void remove() {
        try {
            if (this.iCircleDel != null) {
                this.iCircleDel.remove();
            } else {
                IGlOverlayLayer iGlOverlayLayer = this.f1853a.get();
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
            if (this.iCircleDel != null) {
                return this.iCircleDel.getId();
            }
            return this.overlayName;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void setCenter(LatLng latLng) {
        try {
            if (this.iCircleDel != null) {
                this.iCircleDel.setCenter(latLng);
            } else if (this.options != null) {
                this.options.center(latLng);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public LatLng getCenter() {
        try {
            if (this.iCircleDel != null) {
                return this.iCircleDel.getCenter();
            }
            if (this.options != null) {
                return this.options.getCenter();
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void setRadius(double d2) {
        try {
            if (this.iCircleDel != null) {
                this.iCircleDel.setRadius(d2);
            } else if (this.options != null) {
                this.options.radius(d2);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public double getRadius() {
        try {
            if (this.iCircleDel != null) {
                return this.iCircleDel.getRadius();
            }
            if (this.options != null) {
                return this.options.getRadius();
            }
            return 0.0d;
        } catch (Throwable th) {
            th.printStackTrace();
            return 0.0d;
        }
    }

    public void setStrokeWidth(float f2) {
        try {
            if (this.iCircleDel != null) {
                this.iCircleDel.setStrokeWidth(f2);
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
            if (this.iCircleDel != null) {
                return this.iCircleDel.getStrokeWidth();
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
            if (this.iCircleDel != null) {
                this.iCircleDel.setStrokeColor(i);
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
            if (this.iCircleDel != null) {
                return this.iCircleDel.getStrokeColor();
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

    public void setFillColor(int i) {
        try {
            if (this.iCircleDel != null) {
                this.iCircleDel.setFillColor(i);
            } else if (this.options != null) {
                this.options.fillColor(i);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public int getFillColor() {
        try {
            if (this.iCircleDel != null) {
                return this.iCircleDel.getFillColor();
            }
            if (this.options != null) {
                return this.options.getFillColor();
            }
            return 0;
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public void setZIndex(float f2) {
        try {
            if (this.iCircleDel != null) {
                this.iCircleDel.setZIndex(f2);
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
            if (this.iCircleDel != null) {
                return this.iCircleDel.getZIndex();
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
            if (this.iCircleDel != null) {
                this.iCircleDel.setVisible(z);
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
            if (this.iCircleDel != null) {
                return this.iCircleDel.isVisible();
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
        if (obj != null && (obj instanceof Circle)) {
            try {
                if (this.iCircleDel != null) {
                    return this.iCircleDel.equalsRemote(((Circle) obj).iCircleDel);
                }
                return super.equals(obj) || ((Circle) obj).getId() == getId();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return false;
    }

    public int hashCode() {
        try {
            if (this.iCircleDel != null) {
                return this.iCircleDel.hashCodeRemote();
            }
            return super.hashCode();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public boolean contains(LatLng latLng) {
        try {
            if (this.iCircleDel != null) {
                return this.iCircleDel.contains(latLng);
            }
            IGlOverlayLayer iGlOverlayLayer = this.f1853a.get();
            if (iGlOverlayLayer != null) {
                return iGlOverlayLayer.IsCircleContainPoint(this.options, latLng);
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public void setHoleOptions(List<BaseHoleOptions> list) {
        try {
            if (this.iCircleDel != null) {
                this.iCircleDel.setHoleOptions(list);
            } else if (list != null) {
                synchronized (list) {
                    this.options.getHoleOptions().clear();
                    this.options.addHoles(list);
                    a();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public List<BaseHoleOptions> getHoleOptions() {
        try {
            if (this.iCircleDel != null) {
                return this.iCircleDel.getHoleOptions();
            }
            if (this.options != null) {
                return this.options.getHoleOptions();
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void setStrokeDottedLineType(int i) {
        try {
            if (this.iCircleDel != null) {
                this.iCircleDel.setDottedLineType(i);
            } else if (this.options != null) {
                this.options.setStrokeDottedLineType(i);
                a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public int getStrokeDottedLineType() {
        try {
            if (this.iCircleDel != null) {
                return this.iCircleDel.getDottedLineType();
            }
            if (this.options != null) {
                return this.options.getStrokeDottedLineType();
            }
            return -1;
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    private void a() {
        try {
            IGlOverlayLayer iGlOverlayLayer = this.f1853a.get();
            if (iGlOverlayLayer != null) {
                iGlOverlayLayer.processCircleHoleOption(this.options);
            }
            if (TextUtils.isEmpty(this.overlayName) || iGlOverlayLayer == null) {
                return;
            }
            iGlOverlayLayer.updateOption(this.overlayName, this.options);
        } catch (Throwable unused) {
        }
    }
}