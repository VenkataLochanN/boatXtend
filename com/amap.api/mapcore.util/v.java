package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.opengl.GLES20;
import android.os.RemoteException;
import android.util.Log;
import android.view.MotionEvent;
import com.amap.api.mapcore.util.de;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Text;
import com.amap.api.maps.model.TextOptions;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IMarker;
import com.autonavi.base.amap.api.mapcore.BaseOverlayImp;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.api.mapcore.overlays.IMarkerDelegate;
import com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate;
import com.autonavi.base.amap.mapcore.MapConfig;
import com.realsil.sdk.dfu.DfuConstants;
import java.io.Serializable;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: compiled from: MapOverlayImageView.java */
/* JADX INFO: loaded from: classes.dex */
public final class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    IAMapDelegate f1798a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public de.d f1800c;
    private IPoint k;
    private BaseOverlayImp l;
    private IMarkerDelegate m;
    private FloatBuffer p;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private List<IOverlayImageDelegate> f1803f = new ArrayList(500);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private List<x> f1804g = new ArrayList();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private List<IOverlayImageDelegate> f1805h = new ArrayList();
    private a i = new a();
    private boolean j = true;
    private volatile boolean q = false;
    private int[] r = new int[1];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    float[] f1799b = new float[180000];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1801d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f1802e = 0;
    private Runnable s = new Runnable() { // from class: com.amap.api.mapcore.util.v.2
        @Override // java.lang.Runnable
        public void run() {
            synchronized (v.this.f1803f) {
                v.this.j();
            }
        }
    };
    private fc n = new fc(512, 1024);
    private ef o = new ef();

    public v(Context context, IAMapDelegate iAMapDelegate) {
        this.f1798a = iAMapDelegate;
    }

    public Marker a(MarkerOptions markerOptions) throws RemoteException {
        Marker marker;
        if (markerOptions == null) {
            return null;
        }
        cu cuVar = new cu(markerOptions, this);
        synchronized (this.f1803f) {
            d(cuVar);
            ea.a(this.f1803f.size());
            marker = new Marker(cuVar);
        }
        return marker;
    }

    public ArrayList<Marker> a(ArrayList<MarkerOptions> arrayList, boolean z) throws RemoteException {
        MarkerOptions markerOptions;
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }
        ArrayList<Marker> arrayList2 = new ArrayList<>();
        try {
            if (arrayList.size() == 1 && (markerOptions = arrayList.get(0)) != null) {
                arrayList2.add(a(markerOptions));
                if (z && markerOptions.getPosition() != null) {
                    this.f1798a.moveCamera(ah.a(markerOptions.getPosition(), 18.0f));
                }
            } else {
                final LatLngBounds.Builder builder = LatLngBounds.builder();
                for (int i = 0; i < arrayList.size(); i++) {
                    MarkerOptions markerOptions2 = arrayList.get(i);
                    if (arrayList.get(i) != null) {
                        arrayList2.add(a(markerOptions2));
                        if (markerOptions2.getPosition() != null) {
                            builder.include(markerOptions2.getPosition());
                        }
                    }
                }
                if (z && arrayList2.size() > 0) {
                    this.f1798a.getMainHandler().postDelayed(new Runnable() { // from class: com.amap.api.mapcore.util.v.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                v.this.f1798a.moveCamera(ah.a(builder.build(), 50));
                            } catch (Throwable unused) {
                            }
                        }
                    }, 50L);
                }
            }
            return arrayList2;
        } catch (Throwable th) {
            hn.c(th, "AMapDelegateImpGLSurfaceView", "addMarkers");
            th.printStackTrace();
            return arrayList2;
        }
    }

    public Text a(TextOptions textOptions) throws RemoteException {
        Text text;
        if (textOptions == null) {
            return null;
        }
        synchronized (this.f1803f) {
            db dbVar = new db(textOptions, this);
            d(dbVar);
            text = new Text(dbVar);
        }
        return text;
    }

    private void d(IOverlayImageDelegate iOverlayImageDelegate) {
        try {
            this.f1803f.add(iOverlayImageDelegate);
            f();
        } catch (Throwable th) {
            hn.c(th, "MapOverlayImageView", "addMarker");
        }
    }

    public boolean a(IOverlayImageDelegate iOverlayImageDelegate) {
        boolean zRemove;
        synchronized (this.f1803f) {
            try {
                if (this.m != null && this.m.getId().equals(iOverlayImageDelegate.getId())) {
                    this.m = null;
                }
                b(iOverlayImageDelegate);
            } finally {
            }
            zRemove = this.f1803f.remove(iOverlayImageDelegate);
        }
        return zRemove;
    }

    public void a(IMarkerDelegate iMarkerDelegate) {
        try {
            if (this.m != null) {
                if (iMarkerDelegate != null && iMarkerDelegate.getId().equals(this.m.getId())) {
                    return;
                } else {
                    this.m.setOnTap(false);
                }
            }
            if (this.f1803f.contains(iMarkerDelegate)) {
                if (iMarkerDelegate != null) {
                    iMarkerDelegate.setOnTap(true);
                }
                this.m = iMarkerDelegate;
            }
        } catch (Throwable th) {
            hn.c(th, "MapOverlayImageView", "set2Top");
        }
    }

    public void a() {
        this.m = null;
    }

    public void a(BaseOverlayImp baseOverlayImp) {
        if (this.k == null) {
            this.k = IPoint.obtain();
        }
        Rect rect = baseOverlayImp.getRect();
        this.k = IPoint.obtain(rect.left + (rect.width() / 2), rect.top);
        this.l = baseOverlayImp;
        try {
            this.f1798a.showInfoWindow(this.l);
        } catch (Throwable th) {
            hn.c(th, "MapOverlayImageView", "showInfoWindow");
            th.printStackTrace();
            ey.c(ex.f797d, "infowindow show failed " + th.getMessage());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0011 A[Catch: all -> 0x0028, TryCatch #0 {all -> 0x0028, blocks: (B:4:0x0003, B:6:0x0009, B:7:0x0011, B:9:0x0015, B:11:0x0025), top: B:17:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate r4) {
        /*
            r3 = this;
            r0 = 0
            if (r4 == 0) goto L11
            boolean r1 = r4.isInfoWindowShown()     // Catch: java.lang.Throwable -> L28
            if (r1 == 0) goto L11
            com.autonavi.base.amap.api.mapcore.IAMapDelegate r4 = r3.f1798a     // Catch: java.lang.Throwable -> L28
            r4.hideInfoWindow()     // Catch: java.lang.Throwable -> L28
            r3.l = r0     // Catch: java.lang.Throwable -> L28
            goto L46
        L11:
            com.autonavi.base.amap.api.mapcore.BaseOverlayImp r1 = r3.l     // Catch: java.lang.Throwable -> L28
            if (r1 == 0) goto L46
            com.autonavi.base.amap.api.mapcore.BaseOverlayImp r1 = r3.l     // Catch: java.lang.Throwable -> L28
            java.lang.String r1 = r1.getId()     // Catch: java.lang.Throwable -> L28
            java.lang.String r4 = r4.getId()     // Catch: java.lang.Throwable -> L28
            boolean r4 = r1.equals(r4)     // Catch: java.lang.Throwable -> L28
            if (r4 == 0) goto L46
            r3.l = r0     // Catch: java.lang.Throwable -> L28
            goto L46
        L28:
            r4 = move-exception
            r4.printStackTrace()
            java.lang.String r0 = com.amap.api.mapcore.util.ex.f797d
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "infowindow hide failed "
            r1.append(r2)
            java.lang.String r4 = r4.getMessage()
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            com.amap.api.mapcore.util.ey.c(r0, r4)
        L46:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.v.b(com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        try {
            Collections.sort(this.f1803f, this.i);
        } catch (Throwable th) {
            hn.c(th, "MapOverlayImageView", "changeOverlayIndex");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0057 A[Catch: all -> 0x00fd, TryCatch #3 {, blocks: (B:10:0x001e, B:12:0x0029, B:14:0x002b, B:16:0x0039, B:18:0x0045, B:37:0x0080, B:24:0x0057, B:26:0x005d, B:29:0x0064, B:31:0x0070, B:36:0x007d, B:21:0x004e, B:38:0x0083, B:40:0x0087, B:42:0x008f, B:43:0x0096, B:45:0x009e, B:47:0x00ab, B:48:0x00b3, B:65:0x00f5, B:67:0x00f8, B:68:0x00fb, B:49:0x00b4, B:51:0x00bb, B:55:0x00cd, B:57:0x00da, B:58:0x00e1, B:60:0x00e9, B:61:0x00ef, B:62:0x00f0, B:52:0x00c0, B:54:0x00c6, B:33:0x0076), top: B:81:0x001e, outer: #0, inners: #1, #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(boolean r12) {
        /*
            Method dump skipped, instruction units count: 261
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.v.a(boolean):void");
    }

    private void a(int i, int i2, int i3, int i4) {
        if (i2 == 0 || i == 0) {
            return;
        }
        this.p = this.o.c(i2 * 36);
        this.p.put(this.f1799b, i3, i4);
        this.p.flip();
        a(i2);
        a(i, i4, i2, this.p, this.f1798a.getMapConfig());
        this.o.a();
    }

    private void a(int i) {
        if (i > 5000) {
            i = 5000;
        }
        if (this.f1801d == 0) {
            int[] iArr = new int[2];
            GLES20.glGenBuffers(2, iArr, 0);
            this.f1801d = iArr[0];
            this.f1802e = iArr[1];
            ShortBuffer shortBufferB = this.o.b(30000);
            short[] sArr = new short[30000];
            for (int i2 = 0; i2 < 5000; i2++) {
                int i3 = i2 * 6;
                int i4 = i2 * 4;
                short s = (short) (i4 + 0);
                sArr[i3 + 0] = s;
                sArr[i3 + 1] = (short) (i4 + 1);
                short s2 = (short) (i4 + 2);
                sArr[i3 + 2] = s2;
                sArr[i3 + 3] = s;
                sArr[i3 + 4] = s2;
                sArr[i3 + 5] = (short) (i4 + 3);
            }
            shortBufferB.put(sArr);
            shortBufferB.flip();
            GLES20.glBindBuffer(34963, this.f1802e);
            GLES20.glBufferData(34963, DfuConstants.DFU_UPLOAD_IMAGE_TIMEOUT, shortBufferB, 35044);
        }
        GLES20.glBindBuffer(34962, this.f1801d);
        GLES20.glBufferData(34962, i * 36 * 4, this.p, 35044);
    }

    private void k() {
        IAMapDelegate iAMapDelegate;
        if (this.f1800c != null || (iAMapDelegate = this.f1798a) == null) {
            return;
        }
        this.f1800c = (de.d) iAMapDelegate.getGLShader(1);
    }

    private void a(int i, int i2, int i3, FloatBuffer floatBuffer, MapConfig mapConfig) {
        if (i == 0 || floatBuffer == null || i3 == 0) {
            return;
        }
        de.d dVar = this.f1800c;
        if (dVar == null || dVar.c()) {
            k();
        }
        de.d dVar2 = this.f1800c;
        if (dVar2 == null || this.f1801d == 0 || dVar2.f589c < 0 || this.f1802e == 0) {
            return;
        }
        this.f1800c.a();
        GLES20.glUniform1f(this.f1800c.f591h, mapConfig.getSR());
        GLES20.glEnableVertexAttribArray(this.f1800c.f588b);
        GLES20.glBindBuffer(34962, this.f1801d);
        GLES20.glVertexAttribPointer(this.f1800c.f588b, 4, 5126, false, 36, 0);
        GLES20.glEnable(3042);
        GLES20.glBlendFunc(1, 771);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, i);
        GLES20.glEnableVertexAttribArray(this.f1800c.f589c);
        GLES20.glBindBuffer(34962, this.f1801d);
        GLES20.glVertexAttribPointer(this.f1800c.f589c, 2, 5126, false, 36, 16);
        GLES20.glEnableVertexAttribArray(this.f1800c.f590g);
        GLES20.glBindBuffer(34962, this.f1801d);
        GLES20.glVertexAttribPointer(this.f1800c.f590g, 3, 5126, false, 36, 24);
        GLES20.glUniformMatrix4fv(this.f1800c.f587a, 1, false, b(), 0);
        GLES20.glBindBuffer(34963, this.f1802e);
        GLES20.glDrawElements(4, i3 * 6, 5123, 0);
        GLES20.glBindBuffer(34962, 0);
        GLES20.glBindBuffer(34963, 0);
        GLES20.glBindTexture(3553, 0);
        GLES20.glDisableVertexAttribArray(this.f1800c.f588b);
        GLES20.glDisableVertexAttribArray(this.f1800c.f589c);
        GLES20.glDisable(3042);
        GLES20.glUseProgram(0);
    }

    public synchronized boolean a(Bitmap bitmap, x xVar) {
        if (this.n.a(bitmap.getWidth() + 1, bitmap.getHeight() + 1, xVar.p()) == null) {
            return false;
        }
        xVar.f(r6.f825a / this.n.a());
        xVar.e(r6.f826b / this.n.b());
        xVar.g(((r6.f825a + r6.f827c) - 1) / this.n.a());
        xVar.h(((r6.f826b + r6.f828d) - 1) / this.n.b());
        xVar.c((r6.f825a + 0.5f) / this.n.a());
        xVar.d((r6.f826b + 0.5f) / this.n.b());
        xVar.a((((r6.f825a + r6.f827c) - 1) - 0.5f) / this.n.a());
        xVar.b((((r6.f826b + r6.f828d) - 1) - 0.5f) / this.n.b());
        xVar.a(true);
        return true;
    }

    public float[] b() {
        IAMapDelegate iAMapDelegate = this.f1798a;
        return iAMapDelegate != null ? iAMapDelegate.getFinalMatrix() : new float[16];
    }

    public IAMapDelegate c() {
        return this.f1798a;
    }

    public IOverlayImageDelegate a(String str) throws RemoteException {
        synchronized (this.f1803f) {
            int size = this.f1803f.size();
            for (int i = 0; i < size; i++) {
                IOverlayImageDelegate iOverlayImageDelegate = this.f1803f.get(i);
                if (iOverlayImageDelegate != null && iOverlayImageDelegate.getId().equals(str)) {
                    return iOverlayImageDelegate;
                }
            }
            return null;
        }
    }

    public BaseOverlayImp d() {
        return this.l;
    }

    public BaseOverlayImp a(MotionEvent motionEvent) {
        synchronized (this.f1803f) {
            for (int size = this.f1803f.size() - 1; size >= 0; size--) {
                IOverlayImageDelegate iOverlayImageDelegate = this.f1803f.get(size);
                if ((iOverlayImageDelegate instanceof cu) && er.a(iOverlayImageDelegate.getRect(), (int) motionEvent.getX(), (int) motionEvent.getY())) {
                    this.l = (cu) iOverlayImageDelegate;
                    return this.l;
                }
            }
            return null;
        }
    }

    public boolean b(MotionEvent motionEvent) throws RemoteException {
        boolean z;
        Rect rect;
        boolean zA;
        synchronized (this.f1803f) {
            z = false;
            int size = this.f1803f.size() - 1;
            while (true) {
                if (size < 0) {
                    break;
                }
                IOverlayImageDelegate iOverlayImageDelegate = this.f1803f.get(size);
                if ((iOverlayImageDelegate instanceof cu) && iOverlayImageDelegate.isVisible() && ((cu) iOverlayImageDelegate).isClickable() && (zA = er.a((rect = iOverlayImageDelegate.getRect()), (int) motionEvent.getX(), (int) motionEvent.getY()))) {
                    this.k = IPoint.obtain(rect.left + (rect.width() / 2), rect.top);
                    this.l = (cu) iOverlayImageDelegate;
                    z = zA;
                    break;
                }
                size--;
            }
        }
        return z;
    }

    public List<Marker> e() {
        ArrayList arrayList;
        synchronized (this.f1803f) {
            arrayList = new ArrayList();
            try {
                for (IOverlayImageDelegate iOverlayImageDelegate : this.f1803f) {
                    if ((iOverlayImageDelegate instanceof cu) && iOverlayImageDelegate.checkInBounds()) {
                        arrayList.add(new Marker((IMarker) iOverlayImageDelegate));
                    }
                }
            } catch (Throwable th) {
                hn.c(th, "MapOverlayImageView", "getMapScreenMarkers");
                th.printStackTrace();
            }
        }
        return arrayList;
    }

    public void f() {
        this.q = true;
    }

    public boolean c(IOverlayImageDelegate iOverlayImageDelegate) {
        boolean zContains;
        synchronized (this.f1803f) {
            zContains = this.f1803f.contains(iOverlayImageDelegate);
        }
        return zContains;
    }

    protected int g() {
        int size;
        synchronized (this.f1803f) {
            size = this.f1803f.size();
        }
        return size;
    }

    public void b(String str) {
        boolean z;
        int i = 0;
        if (str != null) {
            try {
                z = str.trim().length() == 0;
            } catch (Throwable th) {
                hn.c(th, "MapOverlayImageView", "clear");
                th.printStackTrace();
                return;
            }
        }
        IOverlayImageDelegate iOverlayImageDelegate = null;
        this.l = null;
        this.k = null;
        this.m = null;
        synchronized (this.f1803f) {
            this.f1805h.clear();
            if (z) {
                this.f1803f.clear();
            } else {
                int size = this.f1803f.size();
                while (true) {
                    if (i >= size) {
                        break;
                    }
                    IOverlayImageDelegate iOverlayImageDelegate2 = this.f1803f.get(i);
                    if (str.equals(iOverlayImageDelegate2.getId())) {
                        iOverlayImageDelegate = iOverlayImageDelegate2;
                        break;
                    }
                    i++;
                }
                this.f1803f.clear();
                if (iOverlayImageDelegate != null) {
                    this.f1803f.add(iOverlayImageDelegate);
                    if (iOverlayImageDelegate.isOnTap() && (iOverlayImageDelegate instanceof IMarkerDelegate)) {
                        this.m = (IMarkerDelegate) iOverlayImageDelegate;
                    }
                }
            }
        }
    }

    public void a(x xVar) {
        synchronized (this.f1804g) {
            if (xVar != null) {
                this.f1804g.add(xVar);
            }
        }
    }

    public void h() {
        synchronized (this.f1804g) {
            int baseOverlayTextureID = this.f1798a.getBaseOverlayTextureID();
            for (int i = 0; i < this.f1804g.size(); i++) {
                x xVar = this.f1804g.get(i);
                if (xVar != null) {
                    xVar.n();
                    if (xVar.o() <= 0) {
                        if (xVar.k() == baseOverlayTextureID) {
                            this.n.a(xVar.p());
                        } else {
                            this.r[0] = xVar.k();
                            GLES20.glDeleteTextures(1, this.r, 0);
                            xVar.a(0);
                        }
                        if (this.f1798a != null) {
                            this.f1798a.removeTextureItem(xVar.p());
                        }
                    }
                }
            }
            this.f1804g.clear();
        }
    }

    public void i() {
        try {
            for (IOverlayImageDelegate iOverlayImageDelegate : this.f1803f) {
                if (iOverlayImageDelegate != null) {
                    iOverlayImageDelegate.destroy(false);
                }
            }
            b((String) null);
            this.f1798a = null;
        } catch (Throwable th) {
            hn.c(th, "MapOverlayImageView", "destroy");
            th.printStackTrace();
            Log.d("amapApi", "MapOverlayImageView clear erro" + th.getMessage());
        }
    }

    /* JADX INFO: compiled from: MapOverlayImageView.java */
    static class a implements Serializable, Comparator<Object> {
        a() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            IOverlayImageDelegate iOverlayImageDelegate = (IOverlayImageDelegate) obj;
            IOverlayImageDelegate iOverlayImageDelegate2 = (IOverlayImageDelegate) obj2;
            if (iOverlayImageDelegate == null || iOverlayImageDelegate2 == null) {
                return 0;
            }
            try {
                return Float.compare(iOverlayImageDelegate.getZIndex(), iOverlayImageDelegate2.getZIndex());
            } catch (Throwable th) {
                hn.c(th, "MapOverlayImageView", "compare");
                th.printStackTrace();
                return 0;
            }
        }
    }
}