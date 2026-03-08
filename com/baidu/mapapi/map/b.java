package com.baidu.mapapi.map;

import com.baidu.mapapi.map.InfoWindow;

/* JADX INFO: loaded from: classes.dex */
class b implements InfoWindow.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ BaiduMap f3034a;

    b(BaiduMap baiduMap) {
        this.f3034a = baiduMap;
    }

    @Override // com.baidu.mapapi.map.InfoWindow.a
    public void a(InfoWindow infoWindow) {
        this.f3034a.hideInfoWindow(infoWindow);
    }

    @Override // com.baidu.mapapi.map.InfoWindow.a
    public void b(InfoWindow infoWindow) {
        this.f3034a.a(infoWindow);
    }
}