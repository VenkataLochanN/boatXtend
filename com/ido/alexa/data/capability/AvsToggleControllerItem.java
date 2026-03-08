package com.ido.alexa.data.capability;

import android.text.TextUtils;
import com.ido.alexa.data.AvsItem;

/* JADX INFO: loaded from: classes2.dex */
public class AvsToggleControllerItem extends AvsItem {
    public static final String STATE_OFF = "TurnOff";
    public static final String STATE_ON = "TurnOn";
    private String correlationToken;
    private String endpointId;
    private String instance;
    private String name;
    private String namespace;

    public String getNamespace() {
        return this.namespace;
    }

    public void setNamespace(String str) {
        this.namespace = str;
    }

    public boolean isOn() {
        return TextUtils.equals(getName(), "TurnOn");
    }

    public String getInstance() {
        return this.instance;
    }

    public void setInstance(String str) {
        this.instance = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getCorrelationToken() {
        return this.correlationToken;
    }

    public void setCorrelationToken(String str) {
        this.correlationToken = str;
    }

    public String getEndpointId() {
        return this.endpointId;
    }

    public void setEndpointId(String str) {
        this.endpointId = str;
    }

    @Override // com.ido.alexa.data.AvsItem
    public String toString() {
        return "AvsToggleControllerItem{instance='" + this.instance + "', name='" + this.name + "', namespace='" + this.namespace + "', correlationToken='" + this.correlationToken + "', endpointId='" + this.endpointId + "'}";
    }
}