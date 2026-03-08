package com.baidu.mapapi.search.poi;

/* JADX INFO: loaded from: classes.dex */
public class PoiDetailSearchOption {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f3179a = "";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3180b = "";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f3181c = false;

    public String getUid() {
        return this.f3179a;
    }

    public String getUids() {
        return this.f3180b;
    }

    public boolean isSearchByUids() {
        return this.f3181c;
    }

    public PoiDetailSearchOption poiUid(String str) {
        this.f3181c = false;
        this.f3179a = str;
        return this;
    }

    public PoiDetailSearchOption poiUids(String str) {
        this.f3181c = true;
        this.f3180b = str;
        return this;
    }
}