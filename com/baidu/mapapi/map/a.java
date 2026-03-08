package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.map.Overlay;

/* JADX INFO: loaded from: classes.dex */
class a implements Overlay.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ BaiduMap f3031a;

    a(BaiduMap baiduMap) {
        this.f3031a = baiduMap;
    }

    @Override // com.baidu.mapapi.map.Overlay.a
    public void a(Overlay overlay) {
        if (overlay != null && this.f3031a.k.contains(overlay)) {
            Bundle bundleA = overlay.a();
            if (this.f3031a.i != null) {
                this.f3031a.i.d(bundleA);
            }
            this.f3031a.k.remove(overlay);
        }
        if (overlay != null && this.f3031a.m.contains(overlay)) {
            this.f3031a.m.remove(overlay);
        }
        if (overlay == null || !this.f3031a.l.contains(overlay)) {
            return;
        }
        Marker marker = (Marker) overlay;
        if (marker.o != null) {
            this.f3031a.l.remove(marker);
            if (this.f3031a.l.size() != 0 || this.f3031a.i == null) {
                return;
            }
            this.f3031a.i.b(false);
        }
    }

    @Override // com.baidu.mapapi.map.Overlay.a
    public void b(Overlay overlay) {
        if (overlay != null && this.f3031a.k.contains(overlay)) {
            boolean z = false;
            if (overlay instanceof Marker) {
                Marker marker = (Marker) overlay;
                if (marker.f2911b != null) {
                    if (marker.o != null && marker.o.size() > 1) {
                        Bundle bundle = new Bundle();
                        if (this.f3031a.i != null) {
                            marker.remove();
                            marker.o.clear();
                            this.f3031a.i.b(overlay.a(bundle));
                            this.f3031a.k.add(overlay);
                            z = true;
                        }
                    }
                } else if (marker.o != null && marker.o.size() != 0) {
                    if (this.f3031a.l.contains(marker)) {
                        this.f3031a.l.remove(marker);
                    }
                    this.f3031a.l.add(marker);
                    if (this.f3031a.i != null) {
                        this.f3031a.i.b(true);
                    }
                }
            }
            if (this.f3031a.i != null && !z) {
                this.f3031a.i.c(overlay.a(new Bundle()));
            }
        }
        if (this.f3031a.m.contains(overlay)) {
            this.f3031a.m.remove(overlay);
        }
        if (overlay instanceof Marker) {
            this.f3031a.m.add((Marker) overlay);
        }
    }
}