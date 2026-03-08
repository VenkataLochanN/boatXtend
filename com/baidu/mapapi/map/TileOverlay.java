package com.baidu.mapapi.map;

import android.util.Log;
import com.baidu.mapapi.common.Logger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: loaded from: classes.dex */
public final class TileOverlay {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3002b = TileOverlay.class.getSimpleName();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static int f3003f = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    BaiduMap f3004a;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private TileProvider f3008g;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private HashMap<String, Tile> f3006d = new HashMap<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private HashSet<String> f3007e = new HashSet<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ExecutorService f3005c = Executors.newFixedThreadPool(1);

    public TileOverlay(BaiduMap baiduMap, TileProvider tileProvider) {
        this.f3004a = baiduMap;
        this.f3008g = tileProvider;
    }

    private synchronized Tile a(String str) {
        if (!this.f3006d.containsKey(str)) {
            return null;
        }
        Tile tile = this.f3006d.get(str);
        this.f3006d.remove(str);
        return tile;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(String str, Tile tile) {
        this.f3006d.put(str, tile);
    }

    private synchronized boolean b(String str) {
        return this.f3007e.contains(str);
    }

    private synchronized void c(String str) {
        this.f3007e.add(str);
    }

    Tile a(int i, int i2, int i3) {
        String str;
        String str2;
        String str3 = i + "_" + i2 + "_" + i3;
        Tile tileA = a(str3);
        if (tileA != null) {
            return tileA;
        }
        BaiduMap baiduMap = this.f3004a;
        if (baiduMap != null && f3003f == 0) {
            MapStatus mapStatus = baiduMap.getMapStatus();
            f3003f = (((mapStatus.f2869a.j.right - mapStatus.f2869a.j.left) / 256) + 2) * (((mapStatus.f2869a.j.bottom - mapStatus.f2869a.j.top) / 256) + 2);
        }
        if (this.f3006d.size() > f3003f) {
            a();
        }
        if (b(str3) || this.f3005c.isShutdown()) {
            return null;
        }
        try {
            c(str3);
            this.f3005c.execute(new w(this, i, i2, i3, str3));
            return null;
        } catch (RejectedExecutionException unused) {
            str = f3002b;
            str2 = "ThreadPool excepiton";
            Log.e(str, str2);
            return null;
        } catch (Exception unused2) {
            str = f3002b;
            str2 = "fileDir is not legal";
            Log.e(str, str2);
            return null;
        }
    }

    synchronized void a() {
        Logger.logE(f3002b, "clearTaskSet");
        this.f3007e.clear();
        this.f3006d.clear();
    }

    void b() {
        this.f3005c.shutdownNow();
    }

    public boolean clearTileCache() {
        BaiduMap baiduMap = this.f3004a;
        if (baiduMap == null) {
            return false;
        }
        return baiduMap.b();
    }

    public void removeTileOverlay() {
        BaiduMap baiduMap = this.f3004a;
        if (baiduMap == null) {
            return;
        }
        baiduMap.a(this);
    }
}