package com.amap.api.maps.model;

import com.autonavi.amap.mapcore.interfaces.ITileOverlay;

/* JADX INFO: loaded from: classes.dex */
public final class TileOverlay {
    private ITileOverlay mTileOverlayDelegate;

    public TileOverlay(ITileOverlay iTileOverlay) {
        this.mTileOverlayDelegate = iTileOverlay;
    }

    public void remove() {
        this.mTileOverlayDelegate.remove();
    }

    public void clearTileCache() {
        this.mTileOverlayDelegate.clearTileCache();
    }

    public String getId() {
        return this.mTileOverlayDelegate.getId();
    }

    public void setZIndex(float f2) {
        this.mTileOverlayDelegate.setZIndex(f2);
    }

    public float getZIndex() {
        return this.mTileOverlayDelegate.getZIndex();
    }

    public void setVisible(boolean z) {
        this.mTileOverlayDelegate.setVisible(z);
    }

    public boolean isVisible() {
        return this.mTileOverlayDelegate.isVisible();
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof TileOverlay)) {
            try {
                return this.mTileOverlayDelegate.equalsRemote(((TileOverlay) obj).mTileOverlayDelegate);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return false;
    }

    public int hashCode() {
        return this.mTileOverlayDelegate.hashCodeRemote();
    }
}