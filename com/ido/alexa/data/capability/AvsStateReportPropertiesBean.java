package com.ido.alexa.data.capability;

/* JADX INFO: loaded from: classes2.dex */
public class AvsStateReportPropertiesBean {
    private String instance;
    private String name;
    private String namespace;
    private String timeOfSample;
    private int uncertaintyInMilliseconds;
    private Object value;

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

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object obj) {
        this.value = obj;
    }

    public String getTimeOfSample() {
        return this.timeOfSample;
    }

    public void setTimeOfSample(String str) {
        this.timeOfSample = str;
    }

    public int getUncertaintyInMilliseconds() {
        return this.uncertaintyInMilliseconds;
    }

    public void setUncertaintyInMilliseconds(int i) {
        this.uncertaintyInMilliseconds = i;
    }
}