package com.ido.alexa.data;

/* JADX INFO: loaded from: classes2.dex */
public class AvsReportStateItem extends AvsItem {
    private String correlationToken;
    private String endpointId;
    private String instance;
    private String messageId;

    public String getInstance() {
        return this.instance;
    }

    public void setInstance(String str) {
        this.instance = str;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public void setMessageId(String str) {
        this.messageId = str;
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
        return "AvsReportStateItem{messageId='" + this.messageId + "', correlationToken='" + this.correlationToken + "', endpointId='" + this.endpointId + "', instance='" + this.instance + "'}";
    }
}