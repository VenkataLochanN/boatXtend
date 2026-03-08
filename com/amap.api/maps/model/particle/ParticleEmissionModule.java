package com.amap.api.maps.model.particle;

import com.autonavi.amap.mapcore.AbstractNativeInstance;
import com.autonavi.base.amap.mapcore.AMapNativeParticleSystem;

/* JADX INFO: loaded from: classes.dex */
public class ParticleEmissionModule extends AbstractNativeInstance {
    private final int rate;
    private final int rateTime;

    public ParticleEmissionModule(int i, int i2) {
        this.rate = i;
        this.rateTime = i2;
        createNativeInstace();
    }

    @Override // com.autonavi.amap.mapcore.AbstractNativeInstance
    public void createNativeInstace() {
        try {
            this.nativeInstance = AMapNativeParticleSystem.nativeCreateParticleEmissionModule(this.rate, this.rateTime);
        } catch (Throwable unused) {
        }
    }

    @Override // com.autonavi.amap.mapcore.AbstractNativeInstance
    protected void finalize() throws Throwable {
        super.finalize();
        if (this.nativeInstance != 0) {
            AMapNativeParticleSystem.nativeReleaseParticleEmissonModule(this.nativeInstance);
            this.nativeInstance = 0L;
        }
    }
}