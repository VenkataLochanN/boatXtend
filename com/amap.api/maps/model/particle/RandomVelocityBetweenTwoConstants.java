package com.amap.api.maps.model.particle;

import com.autonavi.base.amap.mapcore.AMapNativeParticleSystem;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public class RandomVelocityBetweenTwoConstants extends VelocityGenerate {
    private Random random = new Random();
    private float x1;
    private float x2;
    private float y1;
    private float y2;
    private float z1;
    private float z2;

    public RandomVelocityBetweenTwoConstants(float f2, float f3, float f4, float f5, float f6, float f7) {
        this.x1 = f2;
        this.y1 = f3;
        this.z1 = f4;
        this.x2 = f5;
        this.y2 = f6;
        this.z2 = f7;
        createNativeInstace();
        this.type = 0;
    }

    @Override // com.autonavi.amap.mapcore.AbstractNativeInstance
    public void createNativeInstace() {
        try {
            this.nativeInstance = AMapNativeParticleSystem.nativeCreateRandomVelocityBetweenTwoConstants(this.x1, this.y1, this.z1, this.x2, this.y2, this.z2);
        } catch (Throwable unused) {
        }
    }

    @Override // com.amap.api.maps.model.particle.VelocityGenerate
    public float getX() {
        float fNextFloat = this.random.nextFloat();
        float f2 = this.x2;
        float f3 = this.x1;
        return (fNextFloat * (f2 - f3)) + f3;
    }

    @Override // com.amap.api.maps.model.particle.VelocityGenerate
    public float getY() {
        float fNextFloat = this.random.nextFloat();
        float f2 = this.y2;
        float f3 = this.y1;
        return (fNextFloat * (f2 - f3)) + f3;
    }

    @Override // com.amap.api.maps.model.particle.VelocityGenerate
    public float getZ() {
        float fNextFloat = this.random.nextFloat();
        float f2 = this.z2;
        float f3 = this.z1;
        return (fNextFloat * (f2 - f3)) + f3;
    }
}