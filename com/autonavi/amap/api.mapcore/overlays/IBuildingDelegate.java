package com.autonavi.amap.api.mapcore.overlays;

import com.amap.api.maps.model.BuildingOverlayOptions;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IBuildingDelegate {
    void destroy();

    List<BuildingOverlayOptions> getCustomOptions();

    BuildingOverlayOptions getDefaultOptions();

    String getId();

    float getZIndex();

    boolean isVisible();

    void setCustomOptions(List<BuildingOverlayOptions> list);

    void setDefaultOptions(BuildingOverlayOptions buildingOverlayOptions);

    void setVisible(boolean z);

    void setZIndex(float f2);
}