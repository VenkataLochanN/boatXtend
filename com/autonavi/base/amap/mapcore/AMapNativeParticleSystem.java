package com.autonavi.base.amap.mapcore;

/* JADX INFO: loaded from: classes.dex */
public class AMapNativeParticleSystem {
    public static native int getCurrentParticleNum(long j);

    public static native long nativeCreate();

    public static native long nativeCreateConstantRotationOverLife(float f2);

    public static native long nativeCreateCurveSizeOverLife(float f2, float f3, float f4);

    public static native long nativeCreateParticleEmissionModule(int i, int i2);

    public static native long nativeCreateParticleOverLifeModule();

    public static native long nativeCreateRandomColorBetWeenTwoConstants(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9);

    public static native long nativeCreateRandomVelocityBetweenTwoConstants(float f2, float f3, float f4, float f5, float f6, float f7);

    public static native long nativeCreateRectParticleShape(float f2, float f3, float f4, float f5, boolean z);

    public static native long nativeCreateSinglePointParticleShape(float f2, float f3, float f4, boolean z);

    public static native long nativeDestroy(long j);

    public static native void nativeReleaseColorGenerate(long j);

    public static native void nativeReleaseParticleEmissonModule(long j);

    public static native void nativeReleaseParticleOverLifeModule(long j);

    public static native void nativeReleaseParticleShapeModule(long j);

    public static native void nativeReleaseRotationOverLife(long j);

    public static native void nativeReleaseSizeOverLife(long j);

    public static native void nativeReleaseVelocityOverLife(long j);

    public static native void nativeRender(long j, float[] fArr, float[] fArr2, int i, int i2, float f2, float f3, float f4);

    public static native void nativeSetGLShaderManager(long j, long j2);

    public static native void nativeSetOverLifeItem(long j, long j2, int i);

    public static native void nativeSetTextureId(long j, int i);

    public static native void setDuration(long j, long j2);

    public static native void setLoop(long j, boolean z);

    public static native void setMaxParticles(long j, int i);

    public static native void setParticleEmission(long j, long j2);

    public static native void setParticleLifeTime(long j, long j2);

    public static native void setParticleOverLifeModule(long j, long j2);

    public static native void setParticleShapeModule(long j, long j2);

    public static native void setParticleStartSpeed(long j, long j2);

    public static native void setPreWram(long j, boolean z);

    public static native void setStartColor(long j, long j2);

    public static native void setStartParticleSize(long j, float f2, float f3);
}