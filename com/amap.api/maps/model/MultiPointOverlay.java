package com.amap.api.maps.model;

import com.autonavi.amap.mapcore.interfaces.IMultiPointOverlay;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MultiPointOverlay {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    IMultiPointOverlay f1866a;

    public MultiPointOverlay(IMultiPointOverlay iMultiPointOverlay) {
        this.f1866a = iMultiPointOverlay;
    }

    public void setItems(List<MultiPointItem> list) {
        IMultiPointOverlay iMultiPointOverlay = this.f1866a;
        if (iMultiPointOverlay != null) {
            iMultiPointOverlay.addItems(list);
        }
    }

    public void setAnchor(float f2, float f3) {
        IMultiPointOverlay iMultiPointOverlay = this.f1866a;
        if (iMultiPointOverlay != null) {
            iMultiPointOverlay.setAnchor(f2, f3);
        }
    }

    public void setEnable(boolean z) {
        IMultiPointOverlay iMultiPointOverlay = this.f1866a;
        if (iMultiPointOverlay != null) {
            iMultiPointOverlay.setVisible(z);
        }
    }

    public void remove() {
        IMultiPointOverlay iMultiPointOverlay = this.f1866a;
        if (iMultiPointOverlay != null) {
            iMultiPointOverlay.remove(true);
        }
    }

    public void destroy() {
        IMultiPointOverlay iMultiPointOverlay = this.f1866a;
        if (iMultiPointOverlay != null) {
            iMultiPointOverlay.destroy(true);
        }
    }
}