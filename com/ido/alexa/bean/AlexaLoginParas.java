package com.ido.alexa.bean;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaLoginParas {
    private String clientId;
    private String deviceName;
    private String macAddress;
    private String productId;

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public String getMacAddress() {
        return this.macAddress;
    }

    public void setMacAddress(String str) {
        this.macAddress = str;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String str) {
        this.clientId = str;
    }

    public String toString() {
        return "AlexaLoginParas{productId='" + this.productId + "', macAddress='" + this.macAddress + "', deviceName='" + this.deviceName + "', clientId='" + this.clientId + "'}";
    }
}