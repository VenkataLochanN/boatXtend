package com.amap.api.maps.model.particle;

import com.autonavi.base.amap.mapcore.AMapNativeParticleSystem;

/* JADX INFO: loaded from: classes.dex */
public class ConstantRotationOverLife extends RotationOverLife {
    private float rotate;

    public ConstantRotationOverLife(float f2) {
        this.rotate = 0.0f;
        this.rotate = f2;
        this.type = 0;
        createNativeInstace();
    }

    @Override // com.autonavi.amap.mapcore.AbstractNativeInstance
    public void createNativeInstace() {
        try {
            this.nativeInstance = AMapNativeParticleSystem.nativeCreateConstantRotationOverLife(this.rotate);
        } catch (Throwable unused) {
        }
    }

    @Override // com.amap.api.maps.model.particle.RotationOverLife
    public float getRotate() {
        return this.rotate;
    }
}