package com.amap.api.maps.model.particle;

import com.autonavi.amap.mapcore.AbstractNativeInstance;
import com.autonavi.base.amap.mapcore.AMapNativeParticleSystem;

/* JADX INFO: loaded from: classes.dex */
public abstract class ParticleShapeModule extends AbstractNativeInstance {
    protected final int TYPE_DEFAULT = -1;
    protected final int TYPE_SINGLEPOINT = 0;
    protected final int TYPE_RECT = 1;
    protected int type = -1;
    protected boolean isUseRatio = false;

    public abstract float[] getPoint();

    @Override // com.autonavi.amap.mapcore.AbstractNativeInstance
    protected void finalize() throws Throwable {
        super.finalize();
        if (this.nativeInstance != 0) {
            AMapNativeParticleSystem.nativeReleaseParticleShapeModule(this.nativeInstance);
            this.nativeInstance = 0L;
        }
    }

    public boolean isUseRatio() {
        return this.isUseRatio;
    }
}