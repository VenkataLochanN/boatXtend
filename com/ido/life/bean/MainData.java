package com.ido.life.bean;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class MainData implements Serializable {
    public static final int TYPE_AMBIENT_VOLUME = 11;
    public static final int TYPE_BLOOD = 7;
    public static final int TYPE_CARD_EDIT = 10;
    public static final int TYPE_HEADER_DEVICE_STATE = 0;
    public static final int TYPE_HEART_RATE = 5;
    public static final int TYPE_MENSTRUAL = 9;
    public static final int TYPE_OXYGEN_UPTAKE = 12;
    public static final int TYPE_PANNEL = 2;
    public static final int TYPE_PRESSURE = 6;
    public static final int TYPE_SLEEP = 4;
    public static final int TYPE_SPORT_RECORD = 3;
    public static final int TYPE_STEP = 1;
    public static final int TYPE_WEIGHT = 8;
    private int mViewType;

    public MainData() {
    }

    public MainData(int i) {
        this.mViewType = i;
    }

    public int getViewType() {
        return this.mViewType;
    }

    public void setViewType(int i) {
        this.mViewType = i;
    }
}