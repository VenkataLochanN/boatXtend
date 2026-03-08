package com.ido.alexa.data;

/* JADX INFO: loaded from: classes2.dex */
public class AvsSetGatewayItem extends AvsItem {
    String gateway;

    public AvsSetGatewayItem(String str, String str2) {
        super(str);
        this.gateway = str2;
    }

    public String getGateway() {
        return this.gateway;
    }
}