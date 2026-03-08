package com.amap.api.maps.model.particle;

import com.autonavi.base.amap.mapcore.AMapNativeParticleSystem;

/* JADX INFO: loaded from: classes.dex */
public class CurveSizeOverLife extends SizeOverLife {
    private float x;
    private float y;
    private float z;

    @Override // com.amap.api.maps.model.particle.SizeOverLife
    public float getSizeX(float f2) {
        return 0.0f;
    }

    @Override // com.amap.api.maps.model.particle.SizeOverLife
    public float getSizeY(float f2) {
        return 0.0f;
    }

    @Override // com.amap.api.maps.model.particle.SizeOverLife
    public float getSizeZ(float f2) {
        return 0.0f;
    }

    public CurveSizeOverLife(float f2, float f3, float f4) {
        this.x = f2;
        this.y = f3;
        this.z = f4;
        this.type = 0;
        createNativeInstace();
    }

    @Override // com.autonavi.amap.mapcore.AbstractNativeInstance
    public void createNativeInstace() {
        try {
            this.nativeInstance = AMapNativeParticleSystem.nativeCreateCurveSizeOverLife(this.x, this.y, this.z);
        } catch (Throwable unused) {
        }
    }
}