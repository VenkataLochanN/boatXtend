package com.ido.alexa.data.capability;

import com.ido.alexa.data.AvsItem;

/* JADX INFO: loaded from: classes2.dex */
public class AvsSetRangeValueItem extends AvsItem {
    private String correlationToken;
    private String endpointId;
    private String instance;
    private String namespace;
    private int rangeValue;

    public String getNamespace() {
        return this.namespace;
    }

    public void setNamespace(String str) {
        this.namespace = str;
    }

    public String getInstance() {
        return this.instance;
    }

    public void setInstance(String str) {
        this.instance = str;
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

    public int getRangeValue() {
        return this.rangeValue;
    }

    public void setRangeValue(int i) {
        this.rangeValue = i;
    }

    public AvsSetRangeValueItem(String str, String str2, String str3, int i) {
        this.instance = str;
        this.correlationToken = str2;
        this.endpointId = str3;
        this.rangeValue = i;
    }

    @Override // com.ido.alexa.data.AvsItem
    public String toString() {
        return "AvsSetRangeValueItem{instance='" + this.instance + "', correlationToken='" + this.correlationToken + "', endpointId='" + this.endpointId + "', rangeValue=" + this.rangeValue + ", namespace='" + this.namespace + "'}";
    }
}