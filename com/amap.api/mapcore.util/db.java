package com.amap.api.mapcore.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.opengl.GLES20;
import android.os.Build;
import android.os.RemoteException;
import android.util.Log;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.TextOptions;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IMarkerAction;
import com.autonavi.amap.mapcore.interfaces.IOverlayImage;
import com.autonavi.base.ae.gmap.GLMapState;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.api.mapcore.overlays.ITextDelegate;
import com.autonavi.base.amap.mapcore.FPoint;
import com.autonavi.base.amap.mapcore.Rectangle;
import com.autonavi.base.amap.mapcore.interfaces.IAnimation;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: TextDelegateImp.java */
/* JADX INFO: loaded from: classes.dex */
public class db implements ITextDelegate {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static int f530a;
    private int A;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f536g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private BitmapDescriptor f537h;
    private int i;
    private int j;
    private String k;
    private LatLng l;
    private boolean o;
    private v p;
    private Object q;
    private String r;
    private int s;
    private int t;
    private int u;
    private Typeface v;
    private float w;
    private int z;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private float f531b = 0.0f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f532c = 0.0f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f533d = 4;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f534e = 32;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private FPoint f535f = FPoint.obtain();
    private float m = 0.5f;
    private float n = 1.0f;
    private Rect x = new Rect();
    private Paint y = new Paint();
    private boolean B = false;
    private List<x> C = new ArrayList();
    private boolean D = false;
    private boolean E = false;
    private float[] F = {-1.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, -1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate
    public void drawMarker(IAMapDelegate iAMapDelegate) {
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate
    public IAnimation getIAnimation() {
        return null;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate
    public IMarkerAction getIMarkerAction() {
        return null;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate
    public Rect getRect() {
        return null;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate
    public boolean isAllowLow() {
        return true;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate
    public boolean isBelowMaskLayer() {
        return false;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate
    public boolean isInfoWindowShown() {
        return false;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public void setAnchor(float f2, float f3) {
    }

    private static String a(String str) {
        f530a++;
        return str + f530a;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public void setRotateAngle(float f2) {
        this.f532c = f2;
        this.f531b = (((-f2) % 360.0f) + 360.0f) % 360.0f;
        b();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public void destroy(boolean z) {
        try {
            this.D = true;
            if (z) {
                remove();
            }
            if (this.C != null && this.C.size() > 0) {
                for (int i = 0; i < this.C.size(); i++) {
                    x xVar = this.C.get(i);
                    if (xVar != null && this.p != null) {
                        this.p.a(xVar);
                        if (this.p.c() != null) {
                            this.p.c().removeTextureItem(xVar.p());
                        }
                    }
                }
                this.C.clear();
            }
            if (this.f537h != null) {
                this.f537h.recycle();
                this.f537h = null;
            }
            this.l = null;
            this.q = null;
        } catch (Throwable th) {
            hn.c(th, "TextDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "TextDelegateImp destroy");
        }
    }

    public db(TextOptions textOptions, v vVar) throws RemoteException {
        this.o = true;
        this.p = vVar;
        if (textOptions.getPosition() != null) {
            this.l = textOptions.getPosition();
        }
        setAlign(textOptions.getAlignX(), textOptions.getAlignY());
        this.o = textOptions.isVisible();
        this.r = textOptions.getText();
        this.s = textOptions.getBackgroundColor();
        this.t = textOptions.getFontColor();
        this.u = textOptions.getFontSize();
        this.q = textOptions.getObject();
        this.w = textOptions.getZIndex();
        this.v = textOptions.getTypeface();
        this.k = getId();
        setRotateAngle(textOptions.getRotate());
        a();
        calFPoint();
    }

    private void a() {
        String str = this.r;
        if (str == null || str.trim().length() <= 0) {
            return;
        }
        try {
            this.y.setTypeface(this.v);
            this.y.setSubpixelText(true);
            this.y.setAntiAlias(true);
            this.y.setStrokeWidth(5.0f);
            this.y.setStrokeCap(Paint.Cap.ROUND);
            this.y.setTextSize(this.u);
            this.y.setTextAlign(Paint.Align.CENTER);
            this.y.setColor(this.t);
            Paint.FontMetrics fontMetrics = this.y.getFontMetrics();
            int i = (int) (fontMetrics.descent - fontMetrics.ascent);
            int i2 = (int) (((i - fontMetrics.bottom) - fontMetrics.top) / 2.0f);
            this.y.getTextBounds(this.r, 0, this.r.length(), this.x);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(this.x.width() + 6, i, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            canvas.drawColor(this.s);
            canvas.drawText(this.r, this.x.centerX() + 3, i2, this.y);
            this.f537h = BitmapDescriptorFactory.fromBitmap(bitmapCreateBitmap);
            this.i = this.f537h.getWidth();
            this.j = this.f537h.getHeight();
        } catch (Throwable th) {
            hn.c(th, "TextDelegateImp", "initBitmap");
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public boolean remove() {
        b();
        this.o = false;
        return this.p.a(this);
    }

    private void b() {
        if (this.p.c() != null) {
            this.p.c().setRunLowFrame(false);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public LatLng getPosition() {
        return this.l;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public String getId() {
        if (this.k == null) {
            this.k = a("Text");
        }
        return this.k;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public void setPosition(LatLng latLng) {
        this.l = latLng;
        calFPoint();
        b();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public void setVisible(boolean z) {
        if (this.o == z) {
            return;
        }
        this.o = z;
        b();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public boolean isVisible() {
        return this.o;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public void setZIndex(float f2) {
        this.w = f2;
        this.p.f();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public float getZIndex() {
        return this.w;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public float getAnchorU() {
        return this.m;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public float getAnchorV() {
        return this.n;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public boolean equalsRemote(IOverlayImage iOverlayImage) throws RemoteException {
        return equals(iOverlayImage) || iOverlayImage.getId().equals(getId());
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public int hashCodeRemote() {
        return super.hashCode();
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate
    public boolean calFPoint() {
        if (this.l == null) {
            return false;
        }
        IPoint iPointObtain = IPoint.obtain();
        GLMapState.lonlat2Geo(this.l.longitude, this.l.latitude, iPointObtain);
        this.z = iPointObtain.x;
        this.A = iPointObtain.y;
        this.p.c().getLatLng2Map(this.l.latitude, this.l.longitude, this.f535f);
        iPointObtain.recycle();
        return true;
    }

    private void a(IAMapDelegate iAMapDelegate, float[] fArr, int i, float f2) throws RemoteException {
        float f3 = this.i * f2;
        float f4 = f2 * this.j;
        float f5 = this.f535f.x;
        float f6 = this.f535f.y;
        float sc = iAMapDelegate.getMapConfig().getSC();
        float[] fArr2 = this.F;
        float f7 = this.m;
        fArr2[0] = f5 - (f3 * f7);
        float f8 = this.n;
        fArr2[1] = ((1.0f - f8) * f4) + f6;
        fArr2[2] = f5;
        fArr2[3] = f6;
        float f9 = this.f531b;
        fArr2[6] = f9;
        fArr2[7] = sc;
        fArr2[9] = ((1.0f - f7) * f3) + f5;
        fArr2[10] = ((1.0f - f8) * f4) + f6;
        fArr2[11] = f5;
        fArr2[12] = f6;
        fArr2[15] = f9;
        fArr2[16] = sc;
        fArr2[18] = ((1.0f - f7) * f3) + f5;
        fArr2[19] = f6 - (f4 * f8);
        fArr2[20] = f5;
        fArr2[21] = f6;
        fArr2[24] = f9;
        fArr2[25] = sc;
        fArr2[27] = f5 - (f3 * f7);
        fArr2[28] = f6 - (f4 * f8);
        fArr2[29] = f5;
        fArr2[30] = f6;
        fArr2[33] = f9;
        fArr2[34] = sc;
        System.arraycopy(fArr2, 0, fArr, i, fArr2.length);
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate
    public void drawMarker(IAMapDelegate iAMapDelegate, float[] fArr, int i, float f2) {
        if (!this.o || this.D || this.l == null || this.f537h == null) {
            return;
        }
        this.f535f.x = this.z - ((int) iAMapDelegate.getMapConfig().getSX());
        this.f535f.y = this.A - ((int) iAMapDelegate.getMapConfig().getSY());
        try {
            a(iAMapDelegate, fArr, i, f2);
        } catch (Throwable th) {
            hn.c(th, "TextDelegateImp", "drawMarker");
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate
    public void loadTexture(IAMapDelegate iAMapDelegate) {
        if (this.E) {
            return;
        }
        try {
            this.f536g = a(Build.VERSION.SDK_INT >= 12, this.f537h);
            this.E = true;
        } catch (Throwable th) {
            hn.c(th, "TextDelegateImp", "loadtexture");
            th.printStackTrace();
        }
    }

    private void c() {
        v vVar;
        List<x> list = this.C;
        if (list != null) {
            for (x xVar : list) {
                if (xVar != null && (vVar = this.p) != null) {
                    vVar.a(xVar);
                }
            }
            this.C.clear();
        }
    }

    private void a(x xVar) {
        if (xVar != null) {
            this.C.add(xVar);
            xVar.m();
        }
    }

    private int a(boolean z, BitmapDescriptor bitmapDescriptor) {
        x xVar;
        Bitmap bitmap;
        c();
        if (z) {
            xVar = this.p.c().getTextureItem(bitmapDescriptor);
            if (xVar != null) {
                int iK = xVar.k();
                a(xVar);
                return iK;
            }
        } else {
            xVar = null;
        }
        int iD = 0;
        if (xVar == null) {
            xVar = new x(bitmapDescriptor, 0);
        }
        if (bitmapDescriptor != null && (bitmap = bitmapDescriptor.getBitmap()) != null && !bitmap.isRecycled()) {
            iD = d();
            xVar.a(iD);
            if (z) {
                this.p.c().addTextureItem(xVar);
            }
            a(xVar);
            er.b(iD, bitmap, true);
        }
        return iD;
    }

    private int d() {
        int[] iArr = {0};
        GLES20.glGenTextures(1, iArr, 0);
        return iArr[0];
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public void setObject(Object obj) {
        this.q = obj;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public Object getObject() {
        return this.q;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate
    public int getTextureId() {
        try {
            return this.f536g;
        } catch (Throwable unused) {
            return 0;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public float getRotateAngle() {
        return this.f532c;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IText
    public void setText(String str) throws RemoteException {
        this.r = str;
        e();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IText
    public String getText() throws RemoteException {
        return this.r;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IText
    public void setBackgroundColor(int i) throws RemoteException {
        this.s = i;
        e();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IText
    public int getBackgroundColor() throws RemoteException {
        return this.s;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IText
    public void setFontColor(int i) throws RemoteException {
        this.t = i;
        e();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IText
    public int getFontColor() throws RemoteException {
        return this.t;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IText
    public void setFontSize(int i) throws RemoteException {
        this.u = i;
        e();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IText
    public int getFontSize() throws RemoteException {
        return this.u;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IText
    public void setTypeface(Typeface typeface) throws RemoteException {
        this.v = typeface;
        e();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IText
    public Typeface getTypeface() throws RemoteException {
        return this.v;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IText
    public void setAlign(int i, int i2) throws RemoteException {
        this.f533d = i;
        if (i == 1) {
            this.m = 0.0f;
        } else if (i == 2) {
            this.m = 1.0f;
        } else if (i == 4) {
            this.m = 0.5f;
        } else {
            this.m = 0.5f;
        }
        this.f534e = i2;
        if (i2 == 8) {
            this.n = 0.0f;
        } else if (i2 == 16) {
            this.n = 1.0f;
        } else if (i2 == 32) {
            this.n = 0.5f;
        } else {
            this.n = 0.5f;
        }
        b();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IText
    public int getAlignX() throws RemoteException {
        return this.f533d;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IText
    public int getAlignY() {
        return this.f534e;
    }

    private synchronized void e() {
        a();
        this.E = false;
        b();
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate
    public boolean checkInBounds() {
        Rectangle geoRectangle = this.p.c().getMapConfig().getGeoRectangle();
        return geoRectangle != null && geoRectangle.contains(this.z, this.A);
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate
    public void reLoadTexture() {
        this.E = false;
        this.f536g = 0;
        a();
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate
    public void setOnTap(boolean z) {
        this.B = z;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate
    public boolean isOnTap() {
        return this.B;
    }
}