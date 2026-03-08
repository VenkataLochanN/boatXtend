package com.ido.alexa.data;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class UserCodeResponse implements Serializable {
    private String clientId;
    private String deviceSerialNumber;
    private String device_code;
    private Integer expires_in;
    private Integer interval;
    private String productId;
    private String user_code;
    private String verification_uri;

    public String getUser_code() {
        return this.user_code;
    }

    public void setUser_code(String str) {
        this.user_code = str;
    }

    public String getDevice_code() {
        return this.device_code;
    }

    public void setDevice_code(String str) {
        this.device_code = str;
    }

    public Integer getInterval() {
        return this.interval;
    }

    public void setInterval(Integer num) {
        this.interval = num;
    }

    public String getVerification_uri() {
        return this.verification_uri;
    }

    public void setVerification_uri(String str) {
        this.verification_uri = str;
    }

    public Integer getExpires_in() {
        return this.expires_in;
    }

    public void setExpires_in(Integer num) {
        this.expires_in = num;
    }

    public void setClientId(String str) {
        this.clientId = str;
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setDeviceSerialNumber(String str) {
        this.deviceSerialNumber = str;
    }

    public String getDeviceSerialNumber() {
        return this.deviceSerialNumber;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public String toString() {
        return "UserCodeResponse{user_code='" + this.user_code + "', device_code='" + this.device_code + "', interval=" + this.interval + ", verification_uri='" + this.verification_uri + "', expires_in=" + this.expires_in + ", clientId='" + this.clientId + "', deviceSerialNumber='" + this.deviceSerialNumber + "', productId='" + this.productId + "'}";
    }
}