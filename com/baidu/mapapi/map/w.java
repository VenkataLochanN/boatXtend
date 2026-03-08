package com.baidu.mapapi.map;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
class w implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ int f3071a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ int f3072b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ int f3073c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ String f3074d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final /* synthetic */ TileOverlay f3075e;

    w(TileOverlay tileOverlay, int i, int i2, int i3, String str) {
        this.f3075e = tileOverlay;
        this.f3071a = i;
        this.f3072b = i2;
        this.f3073c = i3;
        this.f3074d = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        String str;
        String str2;
        Tile tile = ((FileTileProvider) this.f3075e.f3008g).getTile(this.f3071a, this.f3072b, this.f3073c);
        if (tile == null) {
            str = TileOverlay.f3002b;
            str2 = "FileTile pic is null";
        } else {
            if (tile.width == 256 && tile.height == 256) {
                this.f3075e.a(this.f3071a + "_" + this.f3072b + "_" + this.f3073c, tile);
                this.f3075e.f3007e.remove(this.f3074d);
            }
            str = TileOverlay.f3002b;
            str2 = "FileTile pic must be 256 * 256";
        }
        Log.e(str, str2);
        this.f3075e.f3007e.remove(this.f3074d);
    }
}