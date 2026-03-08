package com.ido.alexa.data;

import com.ido.alexa.data.Directive;

/* JADX INFO: loaded from: classes2.dex */
public class AvsTemplateItem extends AvsItem {
    private Directive.Payload mPayLoad;

    public AvsTemplateItem(String str, Directive.Payload payload) {
        super(str);
        this.mPayLoad = payload;
    }

    public Directive.Payload getPayLoad() {
        return this.mPayLoad;
    }

    public void setPayLoad(Directive.Payload payload) {
        this.mPayLoad = payload;
    }

    public boolean isWeatherType() {
        return this.mPayLoad.getType().equals(Directive.TYPE_WATHER_TEMPLATE);
    }

    public boolean isBodyType() {
        return this.mPayLoad.getType().contains(Directive.TYPE_BODY_TEMPLATE);
    }

    public boolean isListType() {
        return this.mPayLoad.getType().contains(Directive.TYPE_LIST_TEMPLATE);
    }
}