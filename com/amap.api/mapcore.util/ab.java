package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.RemoteException;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.TileOverlay;
import com.amap.api.maps.model.TileOverlayOptions;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.api.mapcore.overlays.ITileOverlayDelegate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: TileOverlayView.java */
/* JADX INFO: loaded from: classes.dex */
public class ab {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    dc f117d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private IAMapDelegate f119f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Context f120g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    List<ITileOverlayDelegate> f114a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    a f115b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    List<Integer> f116c = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    float[] f118e = new float[16];

    /* JADX INFO: compiled from: TileOverlayView.java */
    static class a implements Serializable, Comparator<Object> {
        a() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            ITileOverlayDelegate iTileOverlayDelegate = (ITileOverlayDelegate) obj;
            ITileOverlayDelegate iTileOverlayDelegate2 = (ITileOverlayDelegate) obj2;
            if (iTileOverlayDelegate == null || iTileOverlayDelegate2 == null) {
                return 0;
            }
            try {
                return Float.compare(iTileOverlayDelegate.getZIndex(), iTileOverlayDelegate2.getZIndex());
            } catch (Throwable th) {
                hn.c(th, "TileOverlayView", "compare");
                th.printStackTrace();
                return 0;
            }
        }
    }

    public ab(Context context, IAMapDelegate iAMapDelegate) {
        this.f117d = null;
        this.f119f = iAMapDelegate;
        this.f120g = context;
        TileOverlayOptions tileOverlayOptionsTileProvider = new TileOverlayOptions().tileProvider(new dt(256, 256, this.f119f.getMapConfig()));
        tileOverlayOptionsTileProvider.memCacheSize(10485760);
        tileOverlayOptionsTileProvider.diskCacheSize(20480);
        this.f117d = new dc(tileOverlayOptionsTileProvider, this, true);
    }

    public IAMapDelegate a() {
        return this.f119f;
    }

    public void b() {
        try {
            Iterator<Integer> it = this.f116c.iterator();
            while (it.hasNext()) {
                er.b(it.next().intValue());
            }
            this.f116c.clear();
            if (j() && this.f117d != null) {
                this.f117d.drawTiles();
            }
            synchronized (this.f114a) {
                int size = this.f114a.size();
                for (int i = 0; i < size; i++) {
                    ITileOverlayDelegate iTileOverlayDelegate = this.f114a.get(i);
                    if (iTileOverlayDelegate.isVisible()) {
                        iTileOverlayDelegate.drawTiles();
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    public void c() {
        synchronized (this.f114a) {
            int size = this.f114a.size();
            for (int i = 0; i < size; i++) {
                ITileOverlayDelegate iTileOverlayDelegate = this.f114a.get(i);
                if (iTileOverlayDelegate != null) {
                    iTileOverlayDelegate.destroy(true);
                }
            }
            this.f114a.clear();
        }
    }

    public void d() {
        synchronized (this.f114a) {
            Collections.sort(this.f114a, this.f115b);
        }
    }

    public TileOverlay a(TileOverlayOptions tileOverlayOptions) throws RemoteException {
        if (tileOverlayOptions != null && tileOverlayOptions.getTileProvider() != null) {
            try {
                dc dcVar = new dc(tileOverlayOptions, this, false);
                a(dcVar);
                dcVar.refresh(true);
                this.f119f.setRunLowFrame(false);
                return new TileOverlay(dcVar);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return null;
    }

    public void a(ITileOverlayDelegate iTileOverlayDelegate) {
        synchronized (this.f114a) {
            b(iTileOverlayDelegate);
            this.f114a.add(iTileOverlayDelegate);
        }
        d();
    }

    public boolean b(ITileOverlayDelegate iTileOverlayDelegate) {
        boolean zRemove;
        synchronized (this.f114a) {
            zRemove = this.f114a.remove(iTileOverlayDelegate);
        }
        return zRemove;
    }

    public void a(boolean z) {
        try {
            if (j()) {
                CameraPosition cameraPosition = this.f119f.getCameraPosition();
                if (cameraPosition == null) {
                    return;
                }
                if (cameraPosition.isAbroad && cameraPosition.zoom > 6.0f) {
                    if (this.f119f.getMapType() == 1) {
                        if (this.f117d != null) {
                            this.f117d.refresh(z);
                        }
                    } else if (this.f117d != null) {
                        this.f117d.a();
                    }
                } else if (this.f117d != null) {
                    if (this.f119f.getMapConfig().getMapLanguage().equals("en")) {
                        this.f117d.refresh(z);
                    } else {
                        this.f117d.a();
                    }
                }
            }
            synchronized (this.f114a) {
                int size = this.f114a.size();
                for (int i = 0; i < size; i++) {
                    ITileOverlayDelegate iTileOverlayDelegate = this.f114a.get(i);
                    if (iTileOverlayDelegate != null && iTileOverlayDelegate.isVisible()) {
                        iTileOverlayDelegate.refresh(z);
                    }
                }
            }
        } catch (Throwable th) {
            hn.c(th, "TileOverlayView", "refresh");
        }
    }

    private boolean j() {
        if (this.f119f == null) {
            return false;
        }
        return MapsInitializer.isLoadWorldGridMap() || this.f119f.getMapConfig().getMapLanguage().equals("en");
    }

    public void e() {
        dc dcVar = this.f117d;
        if (dcVar != null) {
            dcVar.onResume();
        }
        synchronized (this.f114a) {
            int size = this.f114a.size();
            for (int i = 0; i < size; i++) {
                ITileOverlayDelegate iTileOverlayDelegate = this.f114a.get(i);
                if (iTileOverlayDelegate != null) {
                    iTileOverlayDelegate.onResume();
                }
            }
        }
    }

    public void b(boolean z) {
        dc dcVar = this.f117d;
        if (dcVar != null) {
            dcVar.onFling(z);
        }
        synchronized (this.f114a) {
            int size = this.f114a.size();
            for (int i = 0; i < size; i++) {
                ITileOverlayDelegate iTileOverlayDelegate = this.f114a.get(i);
                if (iTileOverlayDelegate != null) {
                    iTileOverlayDelegate.onFling(z);
                }
            }
        }
    }

    public Context f() {
        return this.f120g;
    }

    public void a(int i) {
        this.f116c.add(Integer.valueOf(i));
    }

    public void g() {
        c();
        dc dcVar = this.f117d;
        if (dcVar != null) {
            dcVar.onPause();
            this.f117d.destroy(false);
        }
        this.f117d = null;
    }

    public float[] h() {
        IAMapDelegate iAMapDelegate = this.f119f;
        if (iAMapDelegate != null) {
            return iAMapDelegate.getFinalMatrix();
        }
        return this.f118e;
    }

    public void a(String str) {
        dc dcVar = this.f117d;
        if (dcVar != null) {
            dcVar.a(str);
        }
    }

    public void i() {
        dc dcVar = this.f117d;
        if (dcVar != null) {
            dcVar.clearTileCache();
            eh.a(this.f120g, "Map3DCache", "time", (Object) Long.valueOf(System.currentTimeMillis()));
        }
        synchronized (this.f114a) {
            int size = this.f114a.size();
            for (int i = 0; i < size; i++) {
                ITileOverlayDelegate iTileOverlayDelegate = this.f114a.get(i);
                if (iTileOverlayDelegate != null) {
                    iTileOverlayDelegate.clearTileCache();
                }
            }
        }
    }
}