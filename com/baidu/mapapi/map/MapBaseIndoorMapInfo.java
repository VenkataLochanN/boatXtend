package com.baidu.mapapi.map;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class MapBaseIndoorMapInfo {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f2857d = MapBaseIndoorMapInfo.class.getSimpleName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    String f2858a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    String f2859b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ArrayList<String> f2860c;

    public enum SwitchFloorError {
        SWITCH_OK,
        FLOOR_INFO_ERROR,
        FLOOR_OVERLFLOW,
        FOCUSED_ID_ERROR,
        SWITCH_ERROR
    }

    public MapBaseIndoorMapInfo() {
    }

    public MapBaseIndoorMapInfo(MapBaseIndoorMapInfo mapBaseIndoorMapInfo) {
        this.f2858a = mapBaseIndoorMapInfo.f2858a;
        this.f2859b = mapBaseIndoorMapInfo.f2859b;
        this.f2860c = mapBaseIndoorMapInfo.f2860c;
    }

    public MapBaseIndoorMapInfo(String str, String str2, ArrayList<String> arrayList) {
        this.f2858a = str;
        this.f2859b = str2;
        this.f2860c = arrayList;
    }

    public String getCurFloor() {
        return this.f2859b;
    }

    public ArrayList<String> getFloors() {
        return this.f2860c;
    }

    public String getID() {
        return this.f2858a;
    }
}