package com.amap.api.mapcore.util;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.os.Build;
import android.os.RemoteException;
import android.util.Log;
import com.amap.api.mapcore.util.de;
import com.amap.api.maps.interfaces.IGlOverlayLayer;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IOverlay;
import com.autonavi.base.ae.gmap.GLMapState;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.api.mapcore.overlays.IGroundOverlayDelegate;
import com.autonavi.base.amap.mapcore.MapConfig;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: GroundOverlayDelegateImp.java */
/* JADX INFO: loaded from: classes.dex */
public class cs implements IGroundOverlayDelegate {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    float[] f448a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    de.c f449b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private IAMapDelegate f450c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private BitmapDescriptor f451d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private LatLng f452e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private float f453f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private float f454g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private LatLngBounds f455h;
    private float i;
    private float j;
    private boolean k;
    private float l;
    private float m;
    private float n;
    private float o;
    private String p;
    private FloatBuffer q;
    private FloatBuffer r;
    private int s;
    private boolean t;
    private boolean u;
    private List<x> v;
    private IGlOverlayLayer w;

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public boolean checkInBounds() {
        return true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isAboveMaskLayer() {
        return false;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setAboveMaskLayer(boolean z) {
    }

    public cs(IAMapDelegate iAMapDelegate, IGlOverlayLayer iGlOverlayLayer) {
        this(iAMapDelegate);
        this.w = iGlOverlayLayer;
    }

    private cs(IAMapDelegate iAMapDelegate) {
        this.k = true;
        this.l = 0.0f;
        this.m = 1.0f;
        this.n = 0.5f;
        this.o = 0.5f;
        this.q = null;
        this.t = false;
        this.u = false;
        this.v = new ArrayList();
        this.f448a = null;
        this.f450c = iAMapDelegate;
        try {
            this.p = getId();
        } catch (RemoteException e2) {
            hn.c(e2, "GroundOverlayDelegateImp", "create");
            e2.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void remove() throws RemoteException {
        this.f450c.removeGLOverlay(getId());
        this.f450c.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public String getId() throws RemoteException {
        if (this.p == null) {
            this.p = this.f450c.createId("GroundOverlay");
        }
        return this.p;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setZIndex(float f2) throws RemoteException {
        this.j = f2;
        this.f450c.changeGLOverlayIndex();
        this.f450c.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public float getZIndex() throws RemoteException {
        return this.j;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setVisible(boolean z) throws RemoteException {
        this.k = z;
        this.f450c.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isVisible() throws RemoteException {
        return this.k;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean equalsRemote(IOverlay iOverlay) throws RemoteException {
        return equals(iOverlay) || iOverlay.getId().equals(getId());
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public int hashCodeRemote() throws RemoteException {
        return super.hashCode();
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public boolean calMapFPoint() throws RemoteException {
        synchronized (this) {
            if (this.f448a != null) {
                return false;
            }
            this.u = false;
            if (this.f452e == null) {
                b();
                return true;
            }
            if (this.f455h == null) {
                a();
                return true;
            }
            c();
            return true;
        }
    }

    private void a() {
        LatLng latLng = this.f452e;
        if (latLng == null) {
            return;
        }
        double dCos = ((double) this.f453f) / ((Math.cos(latLng.latitude * 0.01745329251994329d) * 6371000.79d) * 0.01745329251994329d);
        double d2 = ((double) this.f454g) / 111194.94043265979d;
        try {
            this.f455h = new LatLngBounds(new LatLng(this.f452e.latitude - (((double) (1.0f - this.o)) * d2), this.f452e.longitude - (((double) this.n) * dCos)), new LatLng(this.f452e.latitude + (((double) this.o) * d2), this.f452e.longitude + (((double) (1.0f - this.n)) * dCos)));
        } catch (Throwable th) {
            th.printStackTrace();
        }
        c();
    }

    private synchronized void b() {
        if (this.f455h == null) {
            return;
        }
        LatLng latLng = this.f455h.southwest;
        LatLng latLng2 = this.f455h.northeast;
        this.f452e = new LatLng(latLng.latitude + (((double) (1.0f - this.o)) * (latLng2.latitude - latLng.latitude)), latLng.longitude + (((double) this.n) * (latLng2.longitude - latLng.longitude)));
        this.f453f = (float) (Math.cos(this.f452e.latitude * 0.01745329251994329d) * 6371000.79d * (latLng2.longitude - latLng.longitude) * 0.01745329251994329d);
        this.f454g = (float) ((latLng2.latitude - latLng.latitude) * 6371000.79d * 0.01745329251994329d);
        c();
    }

    private synchronized void c() {
        if (this.f455h == null) {
            return;
        }
        this.f448a = new float[16];
        IPoint iPointObtain = IPoint.obtain();
        IPoint iPointObtain2 = IPoint.obtain();
        IPoint iPointObtain3 = IPoint.obtain();
        IPoint iPointObtain4 = IPoint.obtain();
        GLMapState.lonlat2Geo(this.f455h.southwest.longitude, this.f455h.southwest.latitude, iPointObtain);
        GLMapState.lonlat2Geo(this.f455h.northeast.longitude, this.f455h.southwest.latitude, iPointObtain2);
        GLMapState.lonlat2Geo(this.f455h.northeast.longitude, this.f455h.northeast.latitude, iPointObtain3);
        GLMapState.lonlat2Geo(this.f455h.southwest.longitude, this.f455h.northeast.latitude, iPointObtain4);
        if (this.i != 0.0f) {
            double d2 = iPointObtain2.x - iPointObtain.x;
            double d3 = iPointObtain2.y - iPointObtain3.y;
            DPoint dPointObtain = DPoint.obtain();
            dPointObtain.x = ((double) iPointObtain.x) + (((double) this.n) * d2);
            dPointObtain.y = ((double) iPointObtain.y) - (((double) (1.0f - this.o)) * d3);
            a(dPointObtain, 0.0d, 0.0d, d2, d3, iPointObtain);
            a(dPointObtain, d2, 0.0d, d2, d3, iPointObtain2);
            a(dPointObtain, d2, d3, d2, d3, iPointObtain3);
            a(dPointObtain, 0.0d, d3, d2, d3, iPointObtain4);
            dPointObtain.recycle();
        }
        this.f448a[0] = iPointObtain.x / 10000;
        this.f448a[1] = iPointObtain.y / 10000;
        this.f448a[2] = iPointObtain.x % 10000;
        this.f448a[3] = iPointObtain.y % 10000;
        this.f448a[4] = iPointObtain2.x / 10000;
        this.f448a[5] = iPointObtain2.y / 10000;
        this.f448a[6] = iPointObtain2.x % 10000;
        this.f448a[7] = iPointObtain2.y % 10000;
        this.f448a[8] = iPointObtain3.x / 10000;
        this.f448a[9] = iPointObtain3.y / 10000;
        this.f448a[10] = iPointObtain3.x % 10000;
        this.f448a[11] = iPointObtain3.y % 10000;
        this.f448a[12] = iPointObtain4.x / 10000;
        this.f448a[13] = iPointObtain4.y / 10000;
        this.f448a[14] = iPointObtain4.x % 10000;
        this.f448a[15] = iPointObtain4.y % 10000;
        if (this.q == null) {
            this.q = er.a(this.f448a);
        } else {
            this.q = er.a(this.f448a, this.q);
        }
        iPointObtain4.recycle();
        iPointObtain.recycle();
        iPointObtain2.recycle();
        iPointObtain3.recycle();
    }

    private void a(DPoint dPoint, double d2, double d3, double d4, double d5, IPoint iPoint) {
        double d6 = d2 - (d4 * ((double) this.n));
        double d7 = (d5 * ((double) (1.0f - this.o))) - d3;
        double d8 = ((double) (-this.i)) * 0.01745329251994329d;
        iPoint.x = (int) (dPoint.x + (Math.cos(d8) * d6) + (Math.sin(d8) * d7));
        iPoint.y = (int) (dPoint.y + ((d7 * Math.cos(d8)) - (d6 * Math.sin(d8))));
    }

    private void d() {
        BitmapDescriptor bitmapDescriptor = this.f451d;
        if (bitmapDescriptor == null && (bitmapDescriptor == null || bitmapDescriptor.getBitmap() == null)) {
            return;
        }
        int width = this.f451d.getWidth();
        float width2 = width / this.f451d.getBitmap().getWidth();
        float height = this.f451d.getHeight() / this.f451d.getBitmap().getHeight();
        this.r = er.a(new float[]{0.0f, height, width2, height, width2, 0.0f, 0.0f, 0.0f});
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public void draw(MapConfig mapConfig) throws RemoteException {
        synchronized (this) {
            if (!this.k || (this.f452e == null && this.f455h == null) || this.f451d == null) {
                return;
            }
            calMapFPoint();
            if (!this.t) {
                this.s = a(Build.VERSION.SDK_INT >= 12, this.f451d);
                this.t = true;
            }
            if (this.f453f == 0.0f && this.f454g == 0.0f) {
                return;
            }
            synchronized (this) {
                a(this.s, this.q, this.r);
            }
            this.u = true;
        }
    }

    private void e() {
        IGlOverlayLayer iGlOverlayLayer;
        List<x> list = this.v;
        if (list != null) {
            for (x xVar : list) {
                if (xVar != null && (iGlOverlayLayer = this.w) != null) {
                    iGlOverlayLayer.addRecycleTextureIds(xVar);
                }
            }
            this.v.clear();
        }
    }

    private void a(x xVar) {
        if (xVar != null) {
            this.v.add(xVar);
            xVar.m();
        }
    }

    private int a(boolean z, BitmapDescriptor bitmapDescriptor) {
        x xVar;
        e();
        if (z) {
            xVar = this.w.getTextureItem(bitmapDescriptor);
            if (xVar != null) {
                int iK = xVar.k();
                a(xVar);
                return iK;
            }
        } else {
            xVar = null;
        }
        int iF = 0;
        if (xVar == null) {
            xVar = new x(bitmapDescriptor, 0);
        }
        Bitmap bitmap = bitmapDescriptor.getBitmap();
        if (bitmap != null && !bitmap.isRecycled()) {
            iF = f();
            xVar.a(iF);
            if (z) {
                this.f450c.addTextureItem(xVar);
            }
            a(xVar);
            er.b(iF, bitmap, true);
        }
        return iF;
    }

    private int f() {
        int[] iArr = {0};
        GLES20.glGenTextures(1, iArr, 0);
        return iArr[0];
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void destroy() {
        Bitmap bitmap;
        try {
            remove();
            if (this.v != null && this.v.size() > 0) {
                for (int i = 0; i < this.v.size(); i++) {
                    x xVar = this.v.get(i);
                    if (xVar != null) {
                        if (this.w != null) {
                            this.w.addRecycleTextureIds(xVar);
                        }
                        if (this.f450c != null) {
                            this.f450c.removeTextureItem(xVar.p());
                        }
                    }
                }
                this.v.clear();
            }
            if (this.f451d != null && (bitmap = this.f451d.getBitmap()) != null) {
                er.b(bitmap);
                this.f451d = null;
            }
            if (this.r != null) {
                this.r.clear();
                this.r = null;
            }
            synchronized (this) {
                if (this.q != null) {
                    this.q.clear();
                    this.q = null;
                }
                this.f455h = null;
            }
            this.f452e = null;
        } catch (Throwable th) {
            hn.c(th, "GroundOverlayDelegateImp", "destroy");
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IGroundOverlay
    public void setPosition(LatLng latLng) throws RemoteException {
        this.f452e = latLng;
        a();
        this.f450c.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IGroundOverlay
    public LatLng getPosition() throws RemoteException {
        return this.f452e;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IGroundOverlay
    public void setDimensions(float f2) throws RemoteException {
        if (f2 <= 0.0f) {
            Log.w("GroundOverlayDelegateImp", "Width must be non-negative");
        }
        if (this.t && this.f453f != f2) {
            this.f453f = f2;
            this.f454g = f2;
            a();
        } else {
            this.f453f = f2;
            this.f454g = f2;
        }
        this.f450c.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IGroundOverlay
    public void setDimensions(float f2, float f3) throws RemoteException {
        if (f2 <= 0.0f || f3 <= 0.0f) {
            Log.w("GroundOverlayDelegateImp", "Width and Height must be non-negative");
        }
        if (this.t && this.f453f != f2 && this.f454g != f3) {
            this.f453f = f2;
            this.f454g = f3;
            a();
        } else {
            this.f453f = f2;
            this.f454g = f3;
        }
        this.f450c.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IGroundOverlay
    public float getWidth() throws RemoteException {
        return this.f453f;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IGroundOverlay
    public float getHeight() throws RemoteException {
        return this.f454g;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IGroundOverlay
    public void setPositionFromBounds(LatLngBounds latLngBounds) throws RemoteException {
        if (latLngBounds == null) {
            return;
        }
        this.f455h = latLngBounds;
        b();
        this.f450c.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IGroundOverlay
    public LatLngBounds getBounds() throws RemoteException {
        return this.f455h;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IGroundOverlay
    public void setBearing(float f2) throws RemoteException {
        float f3 = ((f2 % 360.0f) + 360.0f) % 360.0f;
        if (Math.abs(this.i - f3) > 1.0E-7d) {
            this.i = f3;
            c();
        }
        this.f450c.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IGroundOverlay
    public float getBearing() throws RemoteException {
        return this.i;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IGroundOverlay
    public void setTransparency(float f2) throws RemoteException {
        this.l = (float) Math.min(1.0d, Math.max(0.0d, f2));
        this.m = 1.0f - f2;
        this.f450c.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IGroundOverlay
    public float getTransparency() throws RemoteException {
        return this.l;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IGroundOverlay
    public void setImage(BitmapDescriptor bitmapDescriptor) throws RemoteException {
        if (bitmapDescriptor == null || bitmapDescriptor.getBitmap() == null || bitmapDescriptor.getBitmap().isRecycled()) {
            return;
        }
        this.f451d = bitmapDescriptor;
        d();
        if (this.t) {
            this.t = false;
        }
        this.f450c.setRunLowFrame(false);
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IGroundOverlayDelegate
    public void setAnchor(float f2, float f3) throws RemoteException {
        this.n = f2;
        this.o = f3;
        this.f450c.setRunLowFrame(false);
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IGroundOverlayDelegate
    public void reLoadTexture() {
        this.t = false;
        this.s = 0;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public boolean isDrawFinish() {
        return this.u;
    }

    private void g() {
        IAMapDelegate iAMapDelegate = this.f450c;
        if (iAMapDelegate != null) {
            this.f449b = (de.c) iAMapDelegate.getGLShader(2);
        }
    }

    private void a(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (floatBuffer == null || floatBuffer2 == null) {
            return;
        }
        de.c cVar = this.f449b;
        if (cVar == null || cVar.c()) {
            g();
        }
        this.f449b.a();
        GLES20.glEnable(3042);
        GLES20.glBlendFunc(1, 771);
        float f2 = this.m;
        GLES20.glBlendColor(f2 * 1.0f, f2 * 1.0f, f2 * 1.0f, f2);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, i);
        GLES20.glEnableVertexAttribArray(this.f449b.f583b);
        GLES20.glVertexAttribPointer(this.f449b.f583b, 4, 5126, false, 16, (Buffer) floatBuffer);
        GLES20.glEnableVertexAttribArray(this.f449b.f584c);
        GLES20.glVertexAttribPointer(this.f449b.f584c, 2, 5126, false, 8, (Buffer) floatBuffer2);
        GLES20.glUniform4f(this.f449b.f585g, ((int) this.f450c.getMapConfig().getSX()) / 10000, ((int) this.f450c.getMapConfig().getSY()) / 10000, ((int) this.f450c.getMapConfig().getSX()) % 10000, ((int) this.f450c.getMapConfig().getSY()) % 10000);
        int i2 = this.f449b.f586h;
        float f3 = this.m;
        GLES20.glUniform4f(i2, f3 * 1.0f, f3 * 1.0f, 1.0f * f3, f3);
        GLES20.glUniformMatrix4fv(this.f449b.f582a, 1, false, this.f450c.getFinalMatrix(), 0);
        GLES20.glDrawArrays(6, 0, 4);
        GLES20.glBindTexture(3553, 0);
        GLES20.glDisableVertexAttribArray(this.f449b.f583b);
        GLES20.glDisableVertexAttribArray(this.f449b.f584c);
        GLES20.glDisable(3042);
        GLES20.glUseProgram(0);
    }
}