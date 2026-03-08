package com.amap.api.maps.model.particle;

import com.autonavi.base.amap.mapcore.AMapNativeParticleSystem;

/* JADX INFO: loaded from: classes.dex */
public class SinglePointParticleShape extends ParticleShapeModule {
    private float[] point_3;

    public SinglePointParticleShape(float f2, float f3, float f4, boolean z) {
        this.point_3 = new float[3];
        float[] fArr = this.point_3;
        fArr[0] = f2;
        fArr[1] = f3;
        fArr[2] = f4;
        this.isUseRatio = z;
        createNativeInstace();
        this.type = 0;
    }

    @Override // com.autonavi.amap.mapcore.AbstractNativeInstance
    public void createNativeInstace() {
        try {
            this.nativeInstance = AMapNativeParticleSystem.nativeCreateSinglePointParticleShape(this.point_3[0], this.point_3[1], this.point_3[2], this.isUseRatio);
        } catch (Throwable unused) {
        }
    }

    public SinglePointParticleShape(float f2, float f3, float f4) {
        this(f2, f3, f4, false);
    }

    @Override // com.amap.api.maps.model.particle.ParticleShapeModule
    public float[] getPoint() {
        return this.point_3;
    }
}