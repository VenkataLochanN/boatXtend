package com.amap.api.mapcore.util;

import android.os.RemoteException;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.MultiPointItem;
import com.amap.api.maps.model.MultiPointOverlayOptions;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IMultiPointOverlay;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.mapcore.MapConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: MultiPointOverlayManagerLayer.java */
/* JADX INFO: loaded from: classes.dex */
public class ax {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    de f208a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private List<av> f209b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private AMap.OnMultiPointClickListener f210c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private IAMapDelegate f211d;

    public ax(IAMapDelegate iAMapDelegate) {
        this.f211d = iAMapDelegate;
    }

    public de a() {
        this.f208a = this.f211d.getGLShaderManager();
        return this.f208a;
    }

    public synchronized void b() {
        this.f210c = null;
        try {
            synchronized (this.f209b) {
                Iterator<av> it = this.f209b.iterator();
                while (it.hasNext()) {
                    it.next().destroy(false);
                }
                this.f209b.clear();
            }
        } catch (Throwable th) {
            hn.c(th, "MultiPointOverlayManagerLayer", "destory");
            th.printStackTrace();
        }
    }

    public synchronized void c() {
        try {
            synchronized (this.f209b) {
                this.f209b.clear();
            }
        } catch (Throwable th) {
            hn.c(th, "MultiPointOverlayManagerLayer", "clear");
            th.printStackTrace();
        }
    }

    public synchronized IMultiPointOverlay a(MultiPointOverlayOptions multiPointOverlayOptions) throws RemoteException {
        if (multiPointOverlayOptions == null) {
            return null;
        }
        aw awVar = new aw(multiPointOverlayOptions, this);
        a((av) awVar);
        return awVar;
    }

    private void a(av avVar) throws RemoteException {
        synchronized (this.f209b) {
            this.f209b.add(avVar);
        }
    }

    public void a(MapConfig mapConfig, float[] fArr, float[] fArr2) {
        try {
            synchronized (this.f209b) {
                Iterator<av> it = this.f209b.iterator();
                while (it.hasNext()) {
                    it.next().a(mapConfig, fArr, fArr2);
                }
            }
        } catch (Throwable th) {
            hn.c(th, "MultiPointOverlayManagerLayer", "draw");
            th.printStackTrace();
        }
    }

    public boolean a(IPoint iPoint) {
        MultiPointItem multiPointItemOnClick;
        if (this.f210c == null) {
            return false;
        }
        synchronized (this.f209b) {
            for (av avVar : this.f209b) {
                if (avVar != null && (multiPointItemOnClick = avVar.onClick(iPoint)) != null) {
                    return this.f210c != null ? this.f210c.onPointClick(multiPointItemOnClick) : false;
                }
            }
            return false;
        }
    }

    public void a(AMap.OnMultiPointClickListener onMultiPointClickListener) {
        this.f210c = onMultiPointClickListener;
    }

    public void d() {
        IAMapDelegate iAMapDelegate = this.f211d;
        if (iAMapDelegate != null) {
            iAMapDelegate.setRunLowFrame(false);
        }
    }

    public void a(aw awVar) {
        this.f209b.remove(awVar);
    }
}