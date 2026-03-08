package com.amap.api.maps.model;

import android.graphics.Point;
import com.autonavi.amap.mapcore.VirtualEarthProjection;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BuildingOverlayOptions extends BaseOptions {
    private int[] buildingLatlngsPoints;
    private float zindex;
    private int buildingHeight = -1;
    private int buildingHeightScale = 1;
    private int buildingTopColor = -7829368;
    private int buildingSideColor = -7829368;
    private boolean isVisible = true;
    private List<LatLng> buildingLatlngs = new ArrayList();

    public float getZIndex() {
        return this.zindex;
    }

    public void setZIndex(float f2) {
        this.zindex = f2;
    }

    public void setVisible(boolean z) {
        this.isVisible = z;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    public BuildingOverlayOptions setBuildingHeightScale(int i) {
        this.buildingHeightScale = i;
        return this;
    }

    public int getBuildingHeightScale() {
        return this.buildingHeightScale;
    }

    public BuildingOverlayOptions setBuildingTopColor(int i) {
        this.buildingTopColor = i;
        return this;
    }

    public BuildingOverlayOptions setBuildingSideColor(int i) {
        this.buildingSideColor = i;
        return this;
    }

    public int getBuildingSideColor() {
        return this.buildingSideColor;
    }

    public int getBuildingTopColor() {
        return this.buildingTopColor;
    }

    public BuildingOverlayOptions setBuildingHeight(int i) {
        this.buildingHeight = i;
        return this;
    }

    public int getBuildingHeight() {
        return this.buildingHeight;
    }

    public List<LatLng> getBuildingLatlngs() {
        return this.buildingLatlngs;
    }

    public synchronized int[] getPoints() {
        if (this.buildingLatlngs == null || this.buildingLatlngs.size() <= 0) {
            return new int[0];
        }
        int[] iArr = new int[this.buildingLatlngs.size() * 2];
        int i = 0;
        for (int i2 = 0; i2 < this.buildingLatlngs.size(); i2++) {
            LatLng latLng = this.buildingLatlngs.get(i2);
            if (latLng != null) {
                Point pointLatLongToPixels = VirtualEarthProjection.latLongToPixels(latLng.latitude, latLng.longitude, 20);
                int i3 = i + 1;
                iArr[i] = pointLatLongToPixels.x;
                i = i3 + 1;
                iArr[i3] = pointLatLongToPixels.y;
            }
        }
        return iArr;
    }

    public synchronized BuildingOverlayOptions setBuildingLatlngs(List<LatLng> list) {
        this.buildingLatlngs = list;
        if (list != null && list.size() > 0) {
            this.buildingLatlngsPoints = new int[list.size() * 2];
            int i = 0;
            int i2 = 0;
            while (i < list.size()) {
                LatLng latLng = list.get(i);
                Point pointLatLongToPixels = VirtualEarthProjection.latLongToPixels(latLng.latitude, latLng.longitude, 20);
                int i3 = i2 + 1;
                this.buildingLatlngsPoints[i2] = pointLatLongToPixels.x;
                int i4 = i3 + 1;
                this.buildingLatlngsPoints[i3] = pointLatLongToPixels.y;
                i++;
                i2 = i4;
            }
        }
        return this;
    }
}