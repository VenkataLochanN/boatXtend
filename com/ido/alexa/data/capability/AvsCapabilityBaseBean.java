package com.ido.alexa.data.capability;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes2.dex */
public class AvsCapabilityBaseBean {

    @SerializedName("interface")
    private String interfaceX;
    private String type;
    private String version;

    public AvsCapabilityBaseBean() {
    }

    public AvsCapabilityBaseBean(String str, String str2, String str3) {
        this.type = str;
        this.interfaceX = str2;
        this.version = str3;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getInterfaceX() {
        return this.interfaceX;
    }

    public void setInterfaceX(String str) {
        this.interfaceX = str;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String toString() {
        return "AvsCapabilityBaseBean{type='" + this.type + "', interfaceX='" + this.interfaceX + "', version='" + this.version + "'}";
    }
}