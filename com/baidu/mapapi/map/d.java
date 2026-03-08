package com.baidu.mapapi.map;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
class d implements com.baidu.mapsdkplatform.comapi.map.o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ BaiduMap f3036a;

    d(BaiduMap baiduMap) {
        this.f3036a = baiduMap;
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.o
    public Bundle a(int i, int i2, int i3) {
        Tile tileA;
        this.f3036a.I.lock();
        try {
            if (this.f3036a.H != null && (tileA = this.f3036a.H.a(i, i2, i3)) != null) {
                return tileA.toBundle();
            }
            this.f3036a.I.unlock();
            return null;
        } finally {
            this.f3036a.I.unlock();
        }
    }
}