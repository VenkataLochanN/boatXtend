package com.ido.alexa.bean;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaV3Alarm {
    private ArrayList<AlexaV3AlarmItem> item;
    private int version;

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int i) {
        this.version = i;
    }

    public ArrayList<AlexaV3AlarmItem> getItem() {
        return this.item;
    }

    public void setItem(ArrayList<AlexaV3AlarmItem> arrayList) {
        this.item = arrayList;
    }

    public String toString() {
        return "AlexaV3Alarm{version=" + this.version + ", item=" + this.item + '}';
    }
}