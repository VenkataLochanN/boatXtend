package com.amap.api.maps.model.particle;

import com.autonavi.amap.mapcore.AbstractNativeInstance;
import com.autonavi.base.amap.mapcore.AMapNativeParticleSystem;

/* JADX INFO: loaded from: classes.dex */
public abstract class RotationOverLife extends AbstractNativeInstance {
    protected final int TYPE_DEFAULT = -1;
    protected final int TYPE_CONSTANTROTATIONOVERLIFE = 0;
    protected int type = -1;

    public abstract float getRotate();

    @Override // com.autonavi.amap.mapcore.AbstractNativeInstance
    protected void finalize() throws Throwable {
        super.finalize();
        if (this.nativeInstance != 0) {
            AMapNativeParticleSystem.nativeReleaseRotationOverLife(this.nativeInstance);
            this.nativeInstance = 0L;
        }
    }
}