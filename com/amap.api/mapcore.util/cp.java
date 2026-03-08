package com.amap.api.mapcore.util;

import android.os.RemoteException;
import com.amap.api.maps.interfaces.IGlOverlayLayer;
import com.amap.api.maps.model.BuildingOverlayOptions;
import com.amap.api.maps.model.LatLng;
import com.autonavi.amap.api.mapcore.overlays.IBuildingDelegate;
import com.autonavi.amap.mapcore.interfaces.IOverlay;
import com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate;
import com.autonavi.base.amap.mapcore.AMapNativeBuildingRenderer;
import com.autonavi.base.amap.mapcore.MapConfig;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: BuildingOverlayDelegateImp.java */
/* JADX INFO: loaded from: classes.dex */
public class cp implements IBuildingDelegate, IOverlayDelegate {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private IGlOverlayLayer f425b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private BuildingOverlayOptions f426c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private List<BuildingOverlayOptions> f428e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private String f430g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float f431h;
    private boolean i;
    private de j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    long f424a = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private List<BuildingOverlayOptions> f427d = new ArrayList();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f429f = true;

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public boolean calMapFPoint() throws RemoteException {
        return false;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public boolean checkInBounds() {
        return true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean equalsRemote(IOverlay iOverlay) throws RemoteException {
        return false;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public int hashCodeRemote() throws RemoteException {
        return 0;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isAboveMaskLayer() {
        return false;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public boolean isDrawFinish() {
        return false;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setAboveMaskLayer(boolean z) {
    }

    public cp(IGlOverlayLayer iGlOverlayLayer) {
        try {
            this.f425b = iGlOverlayLayer;
            if (this.f426c == null) {
                this.f426c = new BuildingOverlayOptions();
                this.f426c.setVisible(true);
                ArrayList arrayList = new ArrayList();
                arrayList.add(new LatLng(84.9d, -179.9d));
                arrayList.add(new LatLng(84.9d, 179.9d));
                arrayList.add(new LatLng(-84.9d, 179.9d));
                arrayList.add(new LatLng(-84.9d, -179.9d));
                this.f426c.setBuildingLatlngs(arrayList);
                this.f426c.setBuildingTopColor(-65536);
                this.f426c.setBuildingSideColor(-12303292);
                this.f426c.setVisible(true);
                this.f426c.setZIndex(1.0f);
                this.f427d.add(this.f426c);
                a(true);
            }
            try {
                this.f430g = getId();
            } catch (Exception e2) {
                hn.c(e2, "BuildingOverlayDelegateImp", "create");
                e2.printStackTrace();
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.api.mapcore.overlays.IBuildingDelegate, com.autonavi.amap.mapcore.interfaces.IOverlay
    public String getId() {
        if (this.f430g == null) {
            this.f430g = this.f425b.createId("Building");
        }
        return this.f430g;
    }

    @Override // com.autonavi.amap.api.mapcore.overlays.IBuildingDelegate, com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setZIndex(float f2) {
        try {
            this.f431h = f2;
            this.f425b.changeOverlayIndex();
            synchronized (this) {
                this.f426c.setZIndex(this.f431h);
            }
            a(true);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.api.mapcore.overlays.IBuildingDelegate, com.autonavi.amap.mapcore.interfaces.IOverlay
    public float getZIndex() {
        return this.f431h;
    }

    @Override // com.autonavi.amap.api.mapcore.overlays.IBuildingDelegate, com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setVisible(boolean z) {
        this.f429f = z;
    }

    @Override // com.autonavi.amap.api.mapcore.overlays.IBuildingDelegate, com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isVisible() {
        return this.f429f;
    }

    @Override // com.autonavi.amap.api.mapcore.overlays.IBuildingDelegate
    public void setDefaultOptions(BuildingOverlayOptions buildingOverlayOptions) {
        if (buildingOverlayOptions != null) {
            synchronized (this) {
                this.f426c = buildingOverlayOptions;
            }
            a(true);
        }
    }

    @Override // com.autonavi.amap.api.mapcore.overlays.IBuildingDelegate
    public void setCustomOptions(List<BuildingOverlayOptions> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        synchronized (this) {
            this.f428e = list;
        }
        a(false);
    }

    @Override // com.autonavi.amap.api.mapcore.overlays.IBuildingDelegate
    public List<BuildingOverlayOptions> getCustomOptions() {
        return this.f428e;
    }

    @Override // com.autonavi.amap.api.mapcore.overlays.IBuildingDelegate
    public BuildingOverlayOptions getDefaultOptions() {
        BuildingOverlayOptions buildingOverlayOptions;
        synchronized (this) {
            buildingOverlayOptions = this.f426c;
        }
        return buildingOverlayOptions;
    }

    private void a(boolean z) {
        try {
            synchronized (this) {
                if (z) {
                    this.f427d.set(0, this.f426c);
                } else {
                    this.f427d.removeAll(this.f428e);
                    this.f427d.set(0, this.f426c);
                    this.f427d.addAll(this.f428e);
                }
                this.i = true;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.api.mapcore.overlays.IBuildingDelegate, com.autonavi.amap.mapcore.interfaces.IOverlay
    public void destroy() {
        synchronized (this) {
            if (this.f424a != -1) {
                AMapNativeBuildingRenderer.nativeDestory(this.f424a);
                if (this.f427d != null) {
                    this.f427d.clear();
                }
                this.f428e = null;
                this.f426c = null;
                this.f424a = -1L;
            }
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public void draw(MapConfig mapConfig) throws RemoteException {
        if (mapConfig == null) {
            return;
        }
        try {
            if (this.f424a != -1) {
                synchronized (this) {
                    if (this.f424a != -1) {
                        if (this.i) {
                            AMapNativeBuildingRenderer.nativeClearBuildingOptions(this.f424a);
                            for (int i = 0; i < this.f427d.size(); i++) {
                                AMapNativeBuildingRenderer.addBuildingOptions(this.f424a, this.f427d.get(i));
                            }
                            this.i = false;
                        }
                        AMapNativeBuildingRenderer.render(this.f424a, mapConfig.getViewMatrix(), mapConfig.getProjectionMatrix(), (int) mapConfig.getSX(), (int) mapConfig.getSY(), mapConfig.getSZ(), mapConfig.getCurTileIds());
                    }
                }
                return;
            }
            this.f424a = AMapNativeBuildingRenderer.nativeCreate();
            if (this.f424a == -1 || this.j == null) {
                return;
            }
            AMapNativeBuildingRenderer.nativeSetGLShaderManager(this.f424a, this.j.a());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void remove() throws RemoteException {
        IGlOverlayLayer iGlOverlayLayer = this.f425b;
        if (iGlOverlayLayer == null || iGlOverlayLayer.removeOverlay(this.f430g, true)) {
            return;
        }
        destroy();
    }

    public void a(de deVar) {
        this.j = deVar;
    }
}