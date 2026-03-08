package com.ido.alexa.bean;

import com.ido.alexa.callbacks.AlexaSettingCallBack;

/* JADX INFO: loaded from: classes2.dex */
public class EndpointIDBean {
    private String endpointId;
    private AlexaSettingCallBack.AlexaSettingType settingType;

    public String getEndpointId() {
        return this.endpointId;
    }

    public void setEndpointId(String str) {
        this.endpointId = str;
    }

    public AlexaSettingCallBack.AlexaSettingType getSettingType() {
        return this.settingType;
    }

    public void setSettingType(AlexaSettingCallBack.AlexaSettingType alexaSettingType) {
        this.settingType = alexaSettingType;
    }

    public EndpointIDBean(String str, AlexaSettingCallBack.AlexaSettingType alexaSettingType) {
        this.endpointId = str;
        this.settingType = alexaSettingType;
    }

    public String toString() {
        return "EndpointIDBean{endpointId='" + this.endpointId + "', settingType=" + this.settingType + '}';
    }
}