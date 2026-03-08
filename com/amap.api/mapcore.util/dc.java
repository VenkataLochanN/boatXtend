package com.amap.api.mapcore.util;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import com.amap.api.mapcore.util.de;
import com.amap.api.mapcore.util.dq;
import com.amap.api.mapcore.util.eu;
import com.amap.api.mapcore.util.ev;
import com.amap.api.maps.model.TileOverlayOptions;
import com.amap.api.maps.model.TileProvider;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.ITileOverlay;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.api.mapcore.overlays.ITileOverlayDelegate;
import com.autonavi.base.amap.mapcore.MapConfig;
import java.lang.ref.WeakReference;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: TileOverlayDelegateImp.java */
/* JADX INFO: loaded from: classes.dex */
public class dc implements ITileOverlayDelegate {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static int f538h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    de.g f539a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ab f540b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TileProvider f541c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Float f542d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f543e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f544f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private IAMapDelegate f545g;
    private int i;
    private int j;
    private int k;
    private es l;
    private List<a> m = new ArrayList();
    private boolean n = false;
    private b o = null;
    private String p;
    private FloatBuffer q;

    private static String b(String str) {
        f538h++;
        return str + f538h;
    }

    public dc(TileOverlayOptions tileOverlayOptions, ab abVar, boolean z) {
        ev.a aVar;
        this.f544f = false;
        this.i = 256;
        this.j = 256;
        this.k = -1;
        this.p = null;
        this.q = null;
        this.f540b = abVar;
        this.f541c = tileOverlayOptions.getTileProvider();
        this.i = this.f541c.getTileWidth();
        this.j = this.f541c.getTileHeight();
        this.q = er.a(new float[]{0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f});
        this.f542d = Float.valueOf(tileOverlayOptions.getZIndex());
        this.f543e = tileOverlayOptions.isVisible();
        this.f544f = z;
        if (this.f544f) {
            this.p = "TileOverlay0";
        } else {
            this.p = getId();
        }
        this.f545g = this.f540b.a();
        this.k = Integer.parseInt(this.p.substring(11));
        try {
            if (z) {
                aVar = new ev.a(this.f540b.f(), this.p, abVar.a().getMapConfig().getMapLanguage());
            } else {
                aVar = new ev.a(this.f540b.f(), this.p);
            }
            aVar.a(tileOverlayOptions.getMemoryCacheEnabled());
            if (this.f544f) {
                aVar.i = false;
            }
            aVar.b(tileOverlayOptions.getDiskCacheEnabled());
            aVar.a(tileOverlayOptions.getMemCacheSize());
            aVar.a(tileOverlayOptions.getDiskCacheSize());
            String diskCacheDir = tileOverlayOptions.getDiskCacheDir();
            if (diskCacheDir != null && !"".equals(diskCacheDir)) {
                aVar.a(diskCacheDir);
            }
            this.l = new es(this.f540b.f(), this.i, this.j);
            this.l.a(this.f541c);
            this.l.a(aVar);
            this.l.a(new eu.c() { // from class: com.amap.api.mapcore.util.dc.1
                @Override // com.amap.api.mapcore.util.eu.c
                public void a() {
                    dc.this.f545g.resetRenderTimeLongLong();
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ITileOverlay
    public void remove() {
        this.f540b.b(this);
        this.f545g.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ITileOverlay
    public void destroy(boolean z) {
        b();
        synchronized (this.m) {
            int size = this.m.size();
            for (int i = 0; i < size; i++) {
                this.m.get(i).b();
            }
            this.m.clear();
        }
        es esVar = this.l;
        if (esVar != null) {
            esVar.d(z);
            this.l.b(true);
            this.l.a((TileProvider) null);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ITileOverlay
    public void clearTileCache() {
        es esVar = this.l;
        if (esVar != null) {
            esVar.f();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ITileOverlay
    public String getId() {
        if (this.p == null) {
            this.p = b("TileOverlay");
        }
        return this.p;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ITileOverlay
    public void setZIndex(float f2) {
        this.f542d = Float.valueOf(f2);
        this.f540b.d();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ITileOverlay
    public float getZIndex() {
        return this.f542d.floatValue();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ITileOverlay
    public void setVisible(boolean z) {
        this.f543e = z;
        this.f545g.setRunLowFrame(false);
        if (z) {
            refresh(true);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ITileOverlay
    public boolean isVisible() {
        return this.f543e;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ITileOverlay
    public boolean equalsRemote(ITileOverlay iTileOverlay) {
        return equals(iTileOverlay) || iTileOverlay.getId().equals(getId());
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ITileOverlay
    public int hashCodeRemote() {
        return super.hashCode();
    }

    private boolean a(a aVar) {
        float f2 = aVar.f549c;
        int i = this.i;
        int i2 = this.j;
        int i3 = aVar.f551e.x;
        int i4 = 1 << (20 - ((int) f2));
        int i5 = i2 * i4;
        int i6 = aVar.f551e.y + i5;
        MapConfig mapConfig = this.f545g.getMapConfig();
        double d2 = i3;
        double d3 = i6;
        double d4 = i3 + (i4 * i);
        double d5 = i6 - i5;
        float[] fArr = {(float) (d2 - mapConfig.getSX()), (float) (d3 - mapConfig.getSY()), 0.0f, (float) (d4 - mapConfig.getSX()), (float) (d3 - mapConfig.getSY()), 0.0f, (float) (d4 - mapConfig.getSX()), (float) (d5 - mapConfig.getSY()), 0.0f, (float) (d2 - mapConfig.getSX()), (float) (d5 - mapConfig.getSY()), 0.0f};
        if (aVar.f554h == null) {
            aVar.f554h = er.a(fArr);
        } else {
            aVar.f554h = er.a(fArr, aVar.f554h);
        }
        return true;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.ITileOverlayDelegate
    public void drawTiles() {
        List<a> list = this.m;
        if (list != null) {
            synchronized (list) {
                if (this.m.size() == 0) {
                    return;
                }
                int size = this.m.size();
                for (int i = 0; i < size; i++) {
                    a aVar = this.m.get(i);
                    if (!aVar.f553g) {
                        try {
                            IPoint iPoint = aVar.f551e;
                            if (aVar.i != null && !aVar.i.isRecycled() && iPoint != null) {
                                aVar.f552f = er.a(aVar.i);
                                if (aVar.f552f != 0) {
                                    aVar.f553g = true;
                                }
                                aVar.i = null;
                            }
                        } catch (Throwable th) {
                            hn.c(th, "TileOverlayDelegateImp", "drawTiles");
                        }
                    }
                    if (aVar.f553g) {
                        a(aVar);
                        a(aVar.f552f, aVar.f554h, this.q);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:33:0x01a7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<com.amap.api.mapcore.util.dc.a> b(com.autonavi.base.amap.api.mapcore.IAMapDelegate r26, int r27, int r28, int r29, int r30, com.amap.api.mapcore.util.ab r31, com.amap.api.mapcore.util.es r32) {
        /*
            Method dump skipped, instruction units count: 619
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.dc.b(com.autonavi.base.amap.api.mapcore.IAMapDelegate, int, int, int, int, com.amap.api.mapcore.util.ab, com.amap.api.mapcore.util.es):java.util.ArrayList");
    }

    public void a() {
        List<a> list = this.m;
        if (list != null) {
            synchronized (list) {
                this.m.clear();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00af A[Catch: all -> 0x00c2, TryCatch #0 {, blocks: (B:8:0x0008, B:9:0x000c, B:11:0x0013, B:12:0x001d, B:14:0x0023, B:16:0x002f, B:18:0x0033, B:21:0x003f, B:22:0x0043, B:24:0x004d, B:27:0x0056, B:29:0x005c, B:32:0x0060, B:57:0x00bb, B:36:0x006b, B:38:0x0080, B:41:0x0087, B:43:0x008b, B:46:0x0098, B:48:0x009e, B:50:0x00a2, B:53:0x00af, B:56:0x00b8, B:58:0x00be, B:60:0x00c0), top: B:65:0x0008 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean b(com.autonavi.base.amap.api.mapcore.IAMapDelegate r7, java.util.List<com.amap.api.mapcore.util.dc.a> r8, int r9, boolean r10, java.util.List<com.amap.api.mapcore.util.dc.a> r11, boolean r12, com.amap.api.mapcore.util.ab r13, com.amap.api.mapcore.util.es r14) {
        /*
            r0 = 0
            if (r8 != 0) goto L4
            return r0
        L4:
            if (r11 != 0) goto L7
            return r0
        L7:
            monitor-enter(r11)
            java.util.Iterator r1 = r11.iterator()     // Catch: java.lang.Throwable -> Lc2
        Lc:
            boolean r2 = r1.hasNext()     // Catch: java.lang.Throwable -> Lc2
            r3 = 1
            if (r2 == 0) goto L43
            java.lang.Object r2 = r1.next()     // Catch: java.lang.Throwable -> Lc2
            com.amap.api.mapcore.util.dc$a r2 = (com.amap.api.mapcore.util.dc.a) r2     // Catch: java.lang.Throwable -> Lc2
            java.util.Iterator r4 = r8.iterator()     // Catch: java.lang.Throwable -> Lc2
        L1d:
            boolean r5 = r4.hasNext()     // Catch: java.lang.Throwable -> Lc2
            if (r5 == 0) goto L3c
            java.lang.Object r5 = r4.next()     // Catch: java.lang.Throwable -> Lc2
            com.amap.api.mapcore.util.dc$a r5 = (com.amap.api.mapcore.util.dc.a) r5     // Catch: java.lang.Throwable -> Lc2
            boolean r6 = r2.equals(r5)     // Catch: java.lang.Throwable -> Lc2
            if (r6 == 0) goto L1d
            boolean r6 = r2.f553g     // Catch: java.lang.Throwable -> Lc2
            if (r6 == 0) goto L1d
            boolean r4 = r2.f553g     // Catch: java.lang.Throwable -> Lc2
            r5.f553g = r4     // Catch: java.lang.Throwable -> Lc2
            int r4 = r2.f552f     // Catch: java.lang.Throwable -> Lc2
            r5.f552f = r4     // Catch: java.lang.Throwable -> Lc2
            goto L3d
        L3c:
            r3 = r0
        L3d:
            if (r3 != 0) goto Lc
            r2.b()     // Catch: java.lang.Throwable -> Lc2
            goto Lc
        L43:
            r11.clear()     // Catch: java.lang.Throwable -> Lc2
            float r1 = r7.getMaxZoomLevel()     // Catch: java.lang.Throwable -> Lc2
            int r1 = (int) r1     // Catch: java.lang.Throwable -> Lc2
            if (r9 > r1) goto Lc0
            float r7 = r7.getMinZoomLevel()     // Catch: java.lang.Throwable -> Lc2
            int r7 = (int) r7     // Catch: java.lang.Throwable -> Lc2
            if (r9 >= r7) goto L56
            goto Lc0
        L56:
            int r7 = r8.size()     // Catch: java.lang.Throwable -> Lc2
            if (r7 > 0) goto L5e
            monitor-exit(r11)     // Catch: java.lang.Throwable -> Lc2
            return r0
        L5e:
            if (r0 >= r7) goto Lbe
            java.lang.Object r9 = r8.get(r0)     // Catch: java.lang.Throwable -> Lc2
            com.amap.api.mapcore.util.dc$a r9 = (com.amap.api.mapcore.util.dc.a) r9     // Catch: java.lang.Throwable -> Lc2
            if (r9 != 0) goto L69
            goto Lbb
        L69:
            if (r12 == 0) goto Laf
            com.autonavi.base.amap.api.mapcore.IAMapDelegate r1 = r13.a()     // Catch: java.lang.Throwable -> Lc2
            com.autonavi.base.amap.mapcore.MapConfig r1 = r1.getMapConfig()     // Catch: java.lang.Throwable -> Lc2
            java.lang.String r1 = r1.getMapLanguage()     // Catch: java.lang.Throwable -> Lc2
            java.lang.String r2 = "zh_cn"
            boolean r1 = r1.equals(r2)     // Catch: java.lang.Throwable -> Lc2
            r2 = 6
            if (r1 == 0) goto L98
            boolean r1 = com.amap.api.maps.MapsInitializer.isLoadWorldGridMap()     // Catch: java.lang.Throwable -> Lc2
            if (r1 != 0) goto L87
            goto Lbb
        L87:
            int r1 = r9.f549c     // Catch: java.lang.Throwable -> Lc2
            if (r1 < r2) goto Lbb
            int r1 = r9.f547a     // Catch: java.lang.Throwable -> Lc2
            int r2 = r9.f548b     // Catch: java.lang.Throwable -> Lc2
            int r4 = r9.f549c     // Catch: java.lang.Throwable -> Lc2
            boolean r1 = com.amap.api.mapcore.util.ek.a(r1, r2, r4)     // Catch: java.lang.Throwable -> Lc2
            if (r1 == 0) goto Laf
            goto Lbb
        L98:
            boolean r1 = com.amap.api.maps.MapsInitializer.isLoadWorldGridMap()     // Catch: java.lang.Throwable -> Lc2
            if (r1 != 0) goto Laf
            int r1 = r9.f549c     // Catch: java.lang.Throwable -> Lc2
            if (r1 < r2) goto Laf
            int r1 = r9.f547a     // Catch: java.lang.Throwable -> Lc2
            int r2 = r9.f548b     // Catch: java.lang.Throwable -> Lc2
            int r4 = r9.f549c     // Catch: java.lang.Throwable -> Lc2
            boolean r1 = com.amap.api.mapcore.util.ek.a(r1, r2, r4)     // Catch: java.lang.Throwable -> Lc2
            if (r1 != 0) goto Laf
            goto Lbb
        Laf:
            r11.add(r9)     // Catch: java.lang.Throwable -> Lc2
            boolean r1 = r9.f553g     // Catch: java.lang.Throwable -> Lc2
            if (r1 != 0) goto Lbb
            if (r14 == 0) goto Lbb
            r14.a(r10, r9)     // Catch: java.lang.Throwable -> Lc2
        Lbb:
            int r0 = r0 + 1
            goto L5e
        Lbe:
            monitor-exit(r11)     // Catch: java.lang.Throwable -> Lc2
            return r3
        Lc0:
            monitor-exit(r11)     // Catch: java.lang.Throwable -> Lc2
            return r0
        Lc2:
            r7 = move-exception
            monitor-exit(r11)     // Catch: java.lang.Throwable -> Lc2
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.dc.b(com.autonavi.base.amap.api.mapcore.IAMapDelegate, java.util.List, int, boolean, java.util.List, boolean, com.amap.api.mapcore.util.ab, com.amap.api.mapcore.util.es):boolean");
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.ITileOverlayDelegate
    public void refresh(boolean z) {
        if (this.n) {
            return;
        }
        try {
            b();
            a(z);
        } catch (Throwable th) {
            th.printStackTrace();
            hn.c(th, "TileOverlayDelegateImp", "refresh");
        }
    }

    private void b() {
        b bVar = this.o;
        if (bVar == null || bVar.a() != dq.e.RUNNING) {
            return;
        }
        this.o.a(true);
    }

    private void a(boolean z) {
        try {
            this.o = new b(z, this.f545g, this.i, this.j, this.k, this.m, this.f544f, this.f540b, this.l);
            this.o.c((Object[]) new Void[0]);
        } catch (Throwable unused) {
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.ITileOverlayDelegate
    public void onPause() {
        b();
        synchronized (this.m) {
            int size = this.m.size();
            for (int i = 0; i < size; i++) {
                this.m.get(i).b();
            }
            this.m.clear();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.ITileOverlayDelegate
    public void onResume() {
        es esVar = this.l;
        if (esVar != null) {
            esVar.a(false);
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.ITileOverlayDelegate
    public void onFling(boolean z) {
        if (this.n != z) {
            this.n = z;
            es esVar = this.l;
            if (esVar != null) {
                esVar.b(z);
            }
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.ITileOverlayDelegate
    public void reLoadTexture() {
        List<a> list = this.m;
        if (list != null) {
            synchronized (list) {
                if (this.m.size() == 0) {
                    return;
                }
                for (a aVar : this.m) {
                    aVar.f553g = false;
                    aVar.f552f = 0;
                }
            }
        }
    }

    public void a(String str) {
        b();
        a();
        es esVar = this.l;
        if (esVar != null) {
            esVar.b(true);
            this.l.a(str);
            this.l.b(false);
        }
        a(true);
    }

    /* JADX INFO: compiled from: TileOverlayDelegateImp.java */
    private static class b extends dq<Void, Void, List<a>> {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f555d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f556e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private int f557f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private int f558g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private int f559h;
        private WeakReference<IAMapDelegate> i;
        private List<a> j;
        private boolean k;
        private WeakReference<ab> l;
        private WeakReference<es> m;

        public b(boolean z, IAMapDelegate iAMapDelegate, int i, int i2, int i3, List<a> list, boolean z2, ab abVar, es esVar) {
            this.f557f = 256;
            this.f558g = 256;
            this.f559h = 0;
            this.f556e = z;
            this.i = new WeakReference<>(iAMapDelegate);
            this.f557f = i;
            this.f558g = i2;
            this.f559h = i3;
            this.j = list;
            this.k = z2;
            this.l = new WeakReference<>(abVar);
            this.m = new WeakReference<>(esVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amap.api.mapcore.util.dq
        public List<a> a(Void... voidArr) {
            try {
                IAMapDelegate iAMapDelegate = this.i.get();
                if (iAMapDelegate == null) {
                    return null;
                }
                int mapWidth = iAMapDelegate.getMapWidth();
                int mapHeight = iAMapDelegate.getMapHeight();
                this.f555d = (int) iAMapDelegate.getZoomLevel();
                if (mapWidth > 0 && mapHeight > 0) {
                    return dc.b(iAMapDelegate, this.f555d, this.f557f, this.f558g, this.f559h, this.l.get(), this.m.get());
                }
                return null;
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amap.api.mapcore.util.dq
        public void a(List<a> list) {
            if (list == null) {
                return;
            }
            try {
                if (list.size() <= 0) {
                    return;
                }
                dc.b(this.i.get(), list, this.f555d, this.f556e, this.j, this.k, this.l.get(), this.m.get());
                list.clear();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: TileOverlayDelegateImp.java */
    public static class a implements Cloneable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f547a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f548b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f549c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f550d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public IPoint f551e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f552f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f553g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public FloatBuffer f554h;
        public Bitmap i;
        public eu.a j;
        public int k;
        private IAMapDelegate l;
        private ab m;
        private es n;

        public a(int i, int i2, int i3, int i4, IAMapDelegate iAMapDelegate, ab abVar, es esVar) {
            this.f552f = 0;
            this.f553g = false;
            this.f554h = null;
            this.i = null;
            this.j = null;
            this.k = 0;
            this.f547a = i;
            this.f548b = i2;
            this.f549c = i3;
            this.f550d = i4;
            this.l = iAMapDelegate;
            this.m = abVar;
            this.n = esVar;
        }

        public a(a aVar) {
            this.f552f = 0;
            this.f553g = false;
            this.f554h = null;
            this.i = null;
            this.j = null;
            this.k = 0;
            this.f547a = aVar.f547a;
            this.f548b = aVar.f548b;
            this.f549c = aVar.f549c;
            this.f550d = aVar.f550d;
            this.f551e = aVar.f551e;
            this.f554h = aVar.f554h;
            this.k = 0;
            this.m = aVar.m;
            this.l = aVar.l;
            this.n = aVar.n;
        }

        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a clone() {
            try {
                a aVar = (a) super.clone();
                aVar.f547a = this.f547a;
                aVar.f548b = this.f548b;
                aVar.f549c = this.f549c;
                aVar.f550d = this.f550d;
                aVar.f551e = (IPoint) this.f551e.clone();
                aVar.f554h = this.f554h.asReadOnlyBuffer();
                this.k = 0;
            } catch (CloneNotSupportedException e2) {
                e2.printStackTrace();
            }
            return new a(this);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.f547a == aVar.f547a && this.f548b == aVar.f548b && this.f549c == aVar.f549c && this.f550d == aVar.f550d;
        }

        public int hashCode() {
            return (this.f547a * 7) + (this.f548b * 11) + (this.f549c * 13) + this.f550d;
        }

        public String toString() {
            return this.f547a + "-" + this.f548b + "-" + this.f549c + "-" + this.f550d;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0035 A[Catch: all -> 0x0049, TryCatch #1 {, blocks: (B:5:0x0005, B:11:0x0018, B:13:0x0026, B:15:0x002f, B:16:0x0035, B:18:0x0039, B:20:0x0042, B:8:0x000c), top: B:28:0x0005, inners: #0 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public synchronized void a(android.graphics.Bitmap r5) {
            /*
                r4 = this;
                monitor-enter(r4)
                r0 = 3
                r1 = 1
                if (r5 == 0) goto L35
                boolean r2 = r5.isRecycled()     // Catch: java.lang.Throwable -> L49
                if (r2 != 0) goto L35
                r2 = 0
                r4.j = r2     // Catch: java.lang.Throwable -> L17
                r4.i = r5     // Catch: java.lang.Throwable -> L17
                com.autonavi.base.amap.api.mapcore.IAMapDelegate r5 = r4.l     // Catch: java.lang.Throwable -> L17
                r2 = 0
                r5.setRunLowFrame(r2)     // Catch: java.lang.Throwable -> L17
                goto L47
            L17:
                r5 = move-exception
                java.lang.String r2 = "TileOverlayDelegateImp"
                java.lang.String r3 = "setBitmap"
                com.amap.api.mapcore.util.hn.c(r5, r2, r3)     // Catch: java.lang.Throwable -> L49
                r5.printStackTrace()     // Catch: java.lang.Throwable -> L49
                int r5 = r4.k     // Catch: java.lang.Throwable -> L49
                if (r5 >= r0) goto L47
                int r5 = r4.k     // Catch: java.lang.Throwable -> L49
                int r5 = r5 + r1
                r4.k = r5     // Catch: java.lang.Throwable -> L49
                com.amap.api.mapcore.util.es r5 = r4.n     // Catch: java.lang.Throwable -> L49
                if (r5 == 0) goto L47
                com.amap.api.mapcore.util.es r5 = r4.n     // Catch: java.lang.Throwable -> L49
                r5.a(r1, r4)     // Catch: java.lang.Throwable -> L49
                goto L47
            L35:
                int r5 = r4.k     // Catch: java.lang.Throwable -> L49
                if (r5 >= r0) goto L47
                int r5 = r4.k     // Catch: java.lang.Throwable -> L49
                int r5 = r5 + r1
                r4.k = r5     // Catch: java.lang.Throwable -> L49
                com.amap.api.mapcore.util.es r5 = r4.n     // Catch: java.lang.Throwable -> L49
                if (r5 == 0) goto L47
                com.amap.api.mapcore.util.es r5 = r4.n     // Catch: java.lang.Throwable -> L49
                r5.a(r1, r4)     // Catch: java.lang.Throwable -> L49
            L47:
                monitor-exit(r4)
                return
            L49:
                r5 = move-exception
                monitor-exit(r4)
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.dc.a.a(android.graphics.Bitmap):void");
        }

        public void b() {
            try {
                eu.a(this);
                if (this.f553g) {
                    this.m.a(this.f552f);
                }
                this.f553g = false;
                this.f552f = 0;
                if (this.i != null && !this.i.isRecycled()) {
                    er.b(this.i);
                }
                this.i = null;
                if (this.f554h != null) {
                    this.f554h.clear();
                }
                this.f554h = null;
                this.j = null;
                this.k = 0;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private void c() {
        ab abVar = this.f540b;
        if (abVar == null || abVar.a() == null) {
            return;
        }
        this.f539a = (de.g) this.f540b.a().getGLShader(0);
    }

    private void a(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (floatBuffer == null || floatBuffer2 == null || i == 0) {
            return;
        }
        de.g gVar = this.f539a;
        if (gVar == null || gVar.c()) {
            c();
        }
        this.f539a.a();
        GLES20.glEnable(3042);
        GLES20.glBlendFunc(1, 771);
        GLES20.glBlendColor(1.0f, 1.0f, 1.0f, 1.0f);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, i);
        GLES20.glEnableVertexAttribArray(this.f539a.f596b);
        GLES20.glVertexAttribPointer(this.f539a.f596b, 3, 5126, false, 12, (Buffer) floatBuffer);
        GLES20.glEnableVertexAttribArray(this.f539a.f597c);
        GLES20.glVertexAttribPointer(this.f539a.f597c, 2, 5126, false, 8, (Buffer) floatBuffer2);
        GLES20.glUniformMatrix4fv(this.f539a.f595a, 1, false, this.f540b.h(), 0);
        GLES20.glDrawArrays(6, 0, 4);
        GLES20.glDisableVertexAttribArray(this.f539a.f596b);
        GLES20.glDisableVertexAttribArray(this.f539a.f597c);
        GLES20.glBindTexture(3553, 0);
        GLES20.glUseProgram(0);
        GLES20.glDisable(3042);
    }
}