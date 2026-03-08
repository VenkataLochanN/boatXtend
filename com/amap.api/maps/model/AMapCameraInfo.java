package com.amap.api.maps.model;

/* JADX INFO: loaded from: classes.dex */
public class AMapCameraInfo {
    private float aspectRatio;
    private float fov;
    private float positionX;
    private float positionY;
    private float positionZ;
    private float rotate;

    public AMapCameraInfo(float f2, float f3, float f4, float f5, float f6, float f7) {
        this.fov = 0.0f;
        this.aspectRatio = 1.0f;
        this.rotate = 0.0f;
        this.positionX = 0.0f;
        this.positionY = 0.0f;
        this.positionZ = 0.0f;
        this.fov = f2;
        this.aspectRatio = f3;
        this.rotate = f4;
        this.positionX = f5;
        this.positionY = f6;
        this.positionZ = f7;
    }

    public float getFov() {
        return this.fov;
    }

    public float getAspectRatio() {
        return this.aspectRatio;
    }

    public float getRotate() {
        return this.rotate;
    }

    public float getX() {
        return this.positionX;
    }

    public float getY() {
        return this.positionY;
    }

    public float getZ() {
        return this.positionZ;
    }

    public String toString() {
        return "[fov:" + this.fov + " aspectRatio:" + this.aspectRatio + " rotate:" + this.rotate + " pos_x:" + this.positionX + " pos_y:" + this.positionY + " pos_z:" + this.positionZ + "]";
    }
}