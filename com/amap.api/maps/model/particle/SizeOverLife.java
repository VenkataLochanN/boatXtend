package com.amap.api.maps.model.particle;

import com.autonavi.amap.mapcore.AbstractNativeInstance;
import com.autonavi.base.amap.mapcore.AMapNativeParticleSystem;

/* JADX INFO: loaded from: classes.dex */
public abstract class SizeOverLife extends AbstractNativeInstance {
    protected final int TYPE_DEFAULT = -1;
    protected final int TYPE_CURVESIZEOVERLIFE = 0;
    protected int type = -1;
    public final int DEFAULT_SIZE = 0;

    public abstract float getSizeX(float f2);

    public abstract float getSizeY(float f2);

    public abstract float getSizeZ(float f2);

    @Override // com.autonavi.amap.mapcore.AbstractNativeInstance
    protected void finalize() throws Throwable {
        super.finalize();
        if (this.nativeInstance != 0) {
            AMapNativeParticleSystem.nativeReleaseSizeOverLife(this.nativeInstance);
            this.nativeInstance = 0L;
        }
    }
}