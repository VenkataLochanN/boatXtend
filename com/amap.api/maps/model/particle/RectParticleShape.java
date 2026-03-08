package com.amap.api.maps.model.particle;

import com.autonavi.base.amap.mapcore.AMapNativeParticleSystem;

/* JADX INFO: loaded from: classes.dex */
public class RectParticleShape extends ParticleShapeModule {
    private final float bottom;
    private final float left;
    private final float right;
    private final float top;

    @Override // com.amap.api.maps.model.particle.ParticleShapeModule
    public float[] getPoint() {
        return null;
    }

    public RectParticleShape(float f2, float f3, float f4, float f5, boolean z) {
        this.left = f2;
        this.top = f3;
        this.right = f4;
        this.bottom = f5;
        this.isUseRatio = z;
        createNativeInstace();
        this.type = 1;
    }

    @Override // com.autonavi.amap.mapcore.AbstractNativeInstance
    public void createNativeInstace() {
        try {
            this.nativeInstance = AMapNativeParticleSystem.nativeCreateRectParticleShape(this.left, this.top, this.right, this.bottom, this.isUseRatio);
        } catch (Throwable unused) {
        }
    }
}