package com.ido.alexa.data.capability;

import com.ido.alexa.data.AvsItem;

/* JADX INFO: loaded from: classes2.dex */
public class AvsAdjustRangeValueItem extends AvsItem {
    private String correlationToken;
    private String endpointId;
    private String instance;
    private String namespace;
    private int rangeValueDelta;
    private boolean rangeValueDeltaDefault;

    public String getNamespace() {
        return this.namespace;
    }

    public void setNamespace(String str) {
        this.namespace = str;
    }

    public boolean isIncrease() {
        return this.rangeValueDelta > 0;
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

    public int getRangeValueDelta() {
        return this.rangeValueDelta;
    }

    public void setRangeValueDelta(int i) {
        this.rangeValueDelta = i;
    }

    public boolean isRangeValueDeltaDefault() {
        return this.rangeValueDeltaDefault;
    }

    public void setRangeValueDeltaDefault(boolean z) {
        this.rangeValueDeltaDefault = z;
    }

    @Override // com.ido.alexa.data.AvsItem
    public String toString() {
        return "AvsAdjustRangeValueItem{instance='" + this.instance + "', correlationToken='" + this.correlationToken + "', endpointId='" + this.endpointId + "', rangeValueDelta=" + this.rangeValueDelta + ", rangeValueDeltaDefault=" + this.rangeValueDeltaDefault + ", namespace='" + this.namespace + "'}";
    }
}