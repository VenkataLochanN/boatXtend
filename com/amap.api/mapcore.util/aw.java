package com.amap.api.mapcore.util;

import android.graphics.Rect;
import android.opengl.Matrix;
import android.os.RemoteException;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.MultiPointItem;
import com.amap.api.maps.model.MultiPointOverlayOptions;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapProjection;
import com.autonavi.base.amap.mapcore.MapConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: MultiPointOverlayDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public class aw implements av {
    private static int E;
    private String B;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    List<MultiPointItem> f204h;
    IPoint m;
    ax n;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    BitmapDescriptor f197a = BitmapDescriptorFactory.defaultMarker();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    BitmapDescriptor f198b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    float f199c = 0.0f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    float f200d = 0.0f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    float f201e = 0.0f;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    float f202f = 0.5f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    float f203g = 0.5f;
    ay i = null;
    au j = null;
    au k = new au(0, 1, 0, 1);
    List<MultiPointItem> l = new ArrayList();
    private float[] C = {-0.5f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.5f, 0.0f, 0.0f, 0.0f, 1.0f, 0.5f, 0.5f, 0.0f, 0.0f, 1.0f, 1.0f, 0.5f, -0.5f, 0.0f, 0.0f, 1.0f, 0.0f};
    private boolean D = true;
    List<at> o = new ArrayList();
    private ExecutorService F = null;
    private List<String> G = new ArrayList();
    private float[] H = new float[at.f183a * 3];
    float[] p = new float[16];
    float[] q = new float[4];
    float[] r = new float[4];
    Rect s = new Rect();
    au t = null;
    au u = null;
    int v = 0;
    int w = 0;
    float[] x = new float[12];
    String y = "precision highp float;\nattribute vec3 aVertex;//顶点数组,三维坐标\nuniform mat4 aMVPMatrix;//mvp矩阵\nvoid main(){\n  gl_Position = aMVPMatrix * vec4(aVertex, 1.0);\n}";
    String z = "//有颜色 没有纹理\nprecision highp float;\nvoid main(){\n  gl_FragColor = vec4(0,0,1,1.0);\n}";
    int A = -1;

    public aw(MultiPointOverlayOptions multiPointOverlayOptions, ax axVar) {
        this.n = axVar;
        a(multiPointOverlayOptions);
        at atVar = new at(a(), this);
        atVar.a(axVar.a());
        atVar.a(this.f198b);
        this.o.add(atVar);
    }

    private float[] a() {
        float[] fArr = this.C;
        if (fArr == null) {
            return null;
        }
        float[] fArr2 = (float[]) fArr.clone();
        float f2 = this.f202f - 0.5f;
        float f3 = this.f203g - 0.5f;
        fArr2[0] = fArr2[0] + f2;
        fArr2[1] = fArr2[1] - f3;
        fArr2[6] = fArr2[6] + f2;
        fArr2[7] = fArr2[7] - f3;
        fArr2[12] = fArr2[12] + f2;
        fArr2[13] = fArr2[13] - f3;
        fArr2[18] = fArr2[18] + f2;
        fArr2[19] = fArr2[19] - f3;
        return fArr2;
    }

    private static String a(String str) {
        E++;
        return str + E;
    }

    private void a(MultiPointOverlayOptions multiPointOverlayOptions) {
        if (multiPointOverlayOptions != null) {
            if (multiPointOverlayOptions.getIcon() != null && multiPointOverlayOptions.getIcon().getBitmap() != null && !multiPointOverlayOptions.getIcon().getBitmap().isRecycled()) {
                this.f198b = multiPointOverlayOptions.getIcon();
            } else {
                this.f198b = this.f197a;
            }
            this.f202f = multiPointOverlayOptions.getAnchorU();
            this.f203g = multiPointOverlayOptions.getAnchorV();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMultiPointOverlay
    public void addItems(List<MultiPointItem> list) {
        au auVarB;
        if (list != null) {
            try {
                if (list.size() == 0) {
                    return;
                }
                synchronized (this) {
                    if (this.f204h == null) {
                        this.f204h = new ArrayList();
                    }
                    this.f204h.clear();
                    this.f204h.addAll(list);
                    int size = this.f204h.size();
                    for (int i = 0; i < size; i++) {
                        if (this.f204h == null) {
                            return;
                        }
                        MultiPointItem multiPointItem = this.f204h.get(i);
                        if (multiPointItem != null && multiPointItem.getLatLng() != null && multiPointItem.getIPoint() == null) {
                            IPoint iPoint = new IPoint();
                            MapProjection.lonlat2Geo(multiPointItem.getLatLng().longitude, multiPointItem.getLatLng().latitude, iPoint);
                            multiPointItem.setIPoint(iPoint);
                        }
                    }
                    if (this.i == null && (auVarB = b()) != null) {
                        this.i = new ay(auVarB);
                    }
                    if (this.f204h != null) {
                        int size2 = this.f204h.size();
                        for (int i2 = 0; i2 < size2; i2++) {
                            MultiPointItem multiPointItem2 = this.f204h.get(i2);
                            if (multiPointItem2 != null && multiPointItem2.getIPoint() != null && this.i != null) {
                                this.i.a(multiPointItem2);
                            }
                        }
                    }
                    d();
                }
            } catch (Throwable th) {
                hn.c(th, "MultiPointOverlayDelegate", "addItems");
            }
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMultiPointOverlay
    public void addItem(MultiPointItem multiPointItem) {
        d();
    }

    private au b() {
        List<MultiPointItem> list = this.f204h;
        if (list == null || list.size() == 0) {
            return null;
        }
        Iterator<MultiPointItem> it = this.f204h.iterator();
        MultiPointItem next = it.next();
        int i = next.getIPoint().x;
        int i2 = next.getIPoint().x;
        int i3 = next.getIPoint().y;
        int i4 = next.getIPoint().y;
        while (it.hasNext()) {
            MultiPointItem next2 = it.next();
            int i5 = next2.getIPoint().x;
            int i6 = next2.getIPoint().y;
            if (i5 < i) {
                i = i5;
            }
            if (i5 > i2) {
                i2 = i5;
            }
            if (i6 < i3) {
                i3 = i6;
            }
            if (i6 > i4) {
                i4 = i6;
            }
        }
        return new au(i, i2, i3, i4);
    }

    @Override // com.amap.api.mapcore.util.av
    public void a(MapConfig mapConfig, float[] fArr, float[] fArr2) {
        int i;
        try {
            if (this.D) {
                c();
                if (this.o.size() < 1 || this.i == null || mapConfig == null) {
                    return;
                }
                float sr = mapConfig.getSR();
                float sc = mapConfig.getSC();
                if (mapConfig.getChangeRatio() != 1.0d || this.l.size() == 0) {
                    synchronized (this.l) {
                        a(mapConfig);
                        this.l.clear();
                        this.f199c = mapConfig.getMapPerPixelUnitLength();
                        this.f200d = this.f199c * this.f198b.getWidth();
                        this.f201e = this.f199c * this.f198b.getHeight();
                        double d2 = this.f200d * this.f201e * 16.0f;
                        a(this.f200d, this.f201e, sr, sc);
                        this.i.a(this.j, this.l, d2);
                    }
                }
                if (this.m == null) {
                    this.m = new IPoint();
                }
                if (this.m != null && mapConfig != null) {
                    this.m.x = (int) mapConfig.getSX();
                    this.m.y = (int) mapConfig.getSY();
                }
                at atVar = this.o.get(0);
                synchronized (this.l) {
                    Iterator<MultiPointItem> it = this.l.iterator();
                    loop0: while (true) {
                        i = 0;
                        while (it.hasNext()) {
                            IPoint iPoint = it.next().getIPoint();
                            if (iPoint != null) {
                                int i2 = iPoint.x - this.m.x;
                                int i3 = iPoint.y - this.m.y;
                                if (atVar != null && atVar.b()) {
                                    if (!atVar.d() && this.n != null) {
                                        atVar.a(this.n.a());
                                    }
                                    int i4 = i * 3;
                                    this.H[i4 + 0] = i2;
                                    this.H[i4 + 1] = i3;
                                    this.H[i4 + 2] = 0.0f;
                                    i++;
                                    if (i >= at.f183a) {
                                        break;
                                    }
                                }
                            }
                        }
                        atVar.a(fArr, fArr2, this.H, this.f200d, this.f201e, sr, sc, i);
                    }
                }
                if (i > 0) {
                    atVar.a(fArr, fArr2, this.H, this.f200d, this.f201e, sr, sc, i);
                }
            }
        } catch (Throwable th) {
            hn.c(th, "MultiPointOverlayDelegate", "draw");
        }
    }

    private void c() {
        if (this.F == null) {
            this.F = new ThreadPoolExecutor(1, 2, 1, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ee("MultiPointOverlay"), new ThreadPoolExecutor.AbortPolicy());
        }
        for (final at atVar : this.o) {
            if (atVar != null && !atVar.b()) {
                final String str = atVar.hashCode() + "";
                if (!this.G.contains(str)) {
                    this.G.add(str);
                    this.F.execute(new Runnable() { // from class: com.amap.api.mapcore.util.aw.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (atVar.b()) {
                                return;
                            }
                            try {
                                atVar.a();
                                aw.this.G.remove(str);
                            } catch (Throwable unused) {
                            }
                        }
                    });
                }
            }
        }
    }

    private void a(MapConfig mapConfig) {
        if (mapConfig != null) {
            Rect rect = mapConfig.getGeoRectangle().getRect();
            au auVar = this.j;
            if (auVar == null) {
                this.j = new au(rect.left, rect.right, rect.top, rect.bottom);
            } else {
                auVar.a(rect.left, rect.right, rect.top, rect.bottom);
            }
        }
    }

    private void a(float f2, float f3, float f4, float f5) {
        if (this.k == null) {
            this.k = new au(0, 1, 0, 1);
        }
        this.s.set(0, 0, 0, 0);
        IPoint iPoint = new IPoint();
        float f6 = this.f202f;
        float f7 = this.f203g;
        Matrix.setIdentityM(this.p, 0);
        Matrix.rotateM(this.p, 0, -f4, 0.0f, 0.0f, 1.0f);
        float[] fArr = this.r;
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        fArr[2] = 0.0f;
        fArr[3] = 0.0f;
        float[] fArr2 = this.q;
        float f8 = (-f2) * f6;
        fArr2[0] = f8;
        float f9 = f3 * f7;
        fArr2[1] = f9;
        fArr2[2] = 0.0f;
        fArr2[3] = 1.0f;
        Matrix.multiplyMV(fArr, 0, this.p, 0, fArr2, 0);
        this.s.set((int) (iPoint.x + this.r[0]), (int) (iPoint.y - this.r[1]), (int) (iPoint.x + this.r[0]), (int) (iPoint.y - this.r[1]));
        float[] fArr3 = this.q;
        float f10 = f2 * (1.0f - f6);
        fArr3[0] = f10;
        fArr3[1] = f9;
        fArr3[2] = 0.0f;
        fArr3[3] = 1.0f;
        Matrix.multiplyMV(this.r, 0, this.p, 0, fArr3, 0);
        this.s.union((int) (iPoint.x + this.r[0]), (int) (iPoint.y - this.r[1]));
        float[] fArr4 = this.q;
        fArr4[0] = f10;
        float f11 = (-f3) * (1.0f - f7);
        fArr4[1] = f11;
        fArr4[2] = 0.0f;
        fArr4[3] = 1.0f;
        Matrix.multiplyMV(this.r, 0, this.p, 0, fArr4, 0);
        this.s.union((int) (iPoint.x + this.r[0]), (int) (iPoint.y - this.r[1]));
        float[] fArr5 = this.q;
        fArr5[0] = f8;
        fArr5[1] = f11;
        fArr5[2] = 0.0f;
        fArr5[3] = 1.0f;
        Matrix.multiplyMV(this.r, 0, this.p, 0, fArr5, 0);
        this.s.union((int) (iPoint.x + this.r[0]), (int) (iPoint.y - this.r[1]));
        this.k.a(this.s.left, this.s.right, this.s.top, this.s.bottom);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMultiPointOverlay
    public MultiPointItem onClick(IPoint iPoint) {
        if (!this.D || this.i == null) {
            return null;
        }
        if (this.t == null) {
            this.t = new au(0, 1, 0, 1);
        }
        int i = (int) (this.f199c * 8.0f);
        this.t.a(iPoint.x - i, iPoint.x + i, iPoint.y - i, iPoint.y + i);
        synchronized (this.l) {
            for (int size = this.l.size() - 1; size >= 0; size--) {
                MultiPointItem multiPointItem = this.l.get(size);
                IPoint iPoint2 = multiPointItem.getIPoint();
                if (iPoint2 != null) {
                    if (this.k == null) {
                        return null;
                    }
                    if (this.u == null) {
                        this.u = new au(0, 1, 0, 1);
                    }
                    this.u.a(iPoint2.x + this.k.f191a, iPoint2.x + this.k.f193c, iPoint2.y + this.k.f192b, iPoint2.y + this.k.f194d);
                    if (this.u.a(this.t)) {
                        return multiPointItem;
                    }
                }
            }
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMultiPointOverlay
    public void setAnchor(float f2, float f3) {
        this.f202f = f2;
        this.f203g = f3;
        d();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMultiPointOverlay
    public String getId() throws RemoteException {
        if (this.B == null) {
            this.B = a("MultiPointOverlay");
        }
        return this.B;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMultiPointOverlay
    public void remove(boolean z) {
        this.D = false;
        try {
            this.v = 0;
            this.w = 0;
            if (this.f197a != null) {
                this.f197a.recycle();
            }
            synchronized (this) {
                if (this.f204h != null) {
                    this.f204h.clear();
                    this.f204h = null;
                }
            }
            if (this.i != null) {
                this.i.a();
                this.i = null;
            }
            if (this.l != null) {
                this.l.clear();
            }
            if (this.F != null) {
                this.F.shutdownNow();
                this.F = null;
            }
            if (this.G != null) {
                this.G.clear();
            }
            if (this.o != null) {
                for (at atVar : this.o) {
                    if (atVar != null) {
                        atVar.c();
                    }
                }
                this.o.clear();
            }
            if (z && this.n != null) {
                this.n.a(this);
                this.n.d();
            }
            this.n = null;
            this.C = null;
        } catch (Throwable unused) {
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMultiPointOverlay
    public void setVisible(boolean z) {
        if (this.D != z) {
            d();
        }
        this.D = z;
    }

    private void d() {
        ax axVar = this.n;
        if (axVar != null) {
            axVar.d();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMultiPointOverlay
    public void destroy(boolean z) {
        remove(z);
        BitmapDescriptor bitmapDescriptor = this.f198b;
        if (bitmapDescriptor != null) {
            bitmapDescriptor.recycle();
        }
    }
}