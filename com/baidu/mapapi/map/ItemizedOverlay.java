package com.baidu.mapapi.map;

import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public class ItemizedOverlay extends Overlay {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    MapView f2855a;

    public ItemizedOverlay(Drawable drawable, MapView mapView) {
        this.type = com.baidu.mapsdkplatform.comapi.map.h.marker;
        this.f2855a = mapView;
    }

    public void addItem(OverlayOptions overlayOptions) {
        if (overlayOptions == null || overlayOptions == null) {
            return;
        }
        this.f2855a.getMap().addOverlay(overlayOptions);
    }

    public void reAddAll() {
    }

    public void removeAll() {
        this.f2855a.getMap().clear();
    }
}