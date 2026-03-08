package com.ido.life.data.api.entity;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceInfo implements Serializable {
    private String alexaProductId;
    private String bluetoothName;
    private String deviceId;
    private String deviceName;
    private boolean enabled;
    private float faceHeight;
    private float faceOffesetLeft;
    private float faceOffsetTop;
    private float faceWidth;
    private int id;
    private String imageUrl;
    private String imageUrl2;
    private String imageUrl3;
    private String publishDate;
    private boolean supportAlexa;
    private int type;

    public float getFaceHeight() {
        return this.faceHeight;
    }

    public void setFaceHeight(float f2) {
        this.faceHeight = f2;
    }

    public float getFaceWidth() {
        return this.faceWidth;
    }

    public void setFaceWidth(float f2) {
        this.faceWidth = f2;
    }

    public float getFaceOffsetTop() {
        return this.faceOffsetTop;
    }

    public void setFaceOffsetTop(float f2) {
        this.faceOffsetTop = f2;
    }

    public float getFaceOffesetLeft() {
        return this.faceOffesetLeft;
    }

    public void setFaceOffesetLeft(float f2) {
        this.faceOffesetLeft = f2;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public String getBluetoothName() {
        return this.bluetoothName;
    }

    public void setBluetoothName(String str) {
        this.bluetoothName = str;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public String getImageUrl2() {
        return this.imageUrl2;
    }

    public void setImageUrl2(String str) {
        this.imageUrl2 = str;
    }

    public String getImageUrl3() {
        return this.imageUrl3;
    }

    public void setImageUrl3(String str) {
        this.imageUrl3 = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean z) {
        this.enabled = z;
    }

    public String getPublishDate() {
        return this.publishDate;
    }

    public void setPublishDate(String str) {
        this.publishDate = str;
    }

    public String getAlexaProductId() {
        return this.alexaProductId;
    }

    public void setAlexaProductId(String str) {
        this.alexaProductId = str;
    }

    public boolean getSupportAlexa() {
        return this.supportAlexa;
    }

    public void setSupportAlexa(boolean z) {
        this.supportAlexa = z;
    }

    public String toString() {
        return "DeviceInfo{id=" + this.id + ", deviceId='" + this.deviceId + "', deviceName='" + this.deviceName + "', bluetoothName='" + this.bluetoothName + "', imageUrl='" + this.imageUrl + "', imageUrl2='" + this.imageUrl2 + "', imageUrl3=" + this.imageUrl3 + ", type=" + this.type + ", enabled=" + this.enabled + ", publishDate='" + this.publishDate + "', alexaProductId='" + this.alexaProductId + "', supportAlexa=" + this.supportAlexa + '}';
    }
}