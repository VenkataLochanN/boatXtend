package com.baidu.mapapi.map;

import android.content.Context;
import android.os.Bundle;
import com.baidu.mapsdkplatform.comapi.map.ai;

/* JADX INFO: loaded from: classes.dex */
class e implements ai {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ BaiduMap f3037a;

    e(BaiduMap baiduMap) {
        this.f3037a = baiduMap;
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.ai
    public Bundle a(int i, int i2, int i3, Context context) {
        Tile tileA;
        this.f3037a.J.lock();
        try {
            if (this.f3037a.G != null && (tileA = this.f3037a.G.a(i, i2, i3)) != null) {
                return tileA.toBundle();
            }
            this.f3037a.J.unlock();
            return null;
        } finally {
            this.f3037a.J.unlock();
        }
    }
}