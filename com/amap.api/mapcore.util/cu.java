package com.amap.api.mapcore.util;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.os.RemoteException;
import android.util.Log;
import android.view.animation.AnimationUtils;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.animation.Animation;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.animation.GLAnimation;
import com.autonavi.amap.mapcore.animation.GLAnimationSet;
import com.autonavi.amap.mapcore.animation.GLTransformation;
import com.autonavi.amap.mapcore.animation.GLTranslateAnimation;
import com.autonavi.amap.mapcore.interfaces.IMarkerAction;
import com.autonavi.amap.mapcore.interfaces.IOverlayImage;
import com.autonavi.base.ae.gmap.GLMapState;
import com.autonavi.base.amap.api.mapcore.BaseOverlayImp;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.api.mapcore.overlays.IMarkerDelegate;
import com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate;
import com.autonavi.base.amap.mapcore.FPoint;
import com.autonavi.base.amap.mapcore.Rectangle;
import com.autonavi.base.amap.mapcore.interfaces.IAnimation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: MarkerDelegateImp.java */
/* JADX INFO: loaded from: classes.dex */
public class cu extends BaseOverlayImp implements IMarkerAction, IMarkerDelegate, IAnimation {
    private static int i;
    private MarkerOptions D;
    private float P;
    private float Q;
    private x T;
    private String W;
    private LatLng X;
    private LatLng Y;
    private String Z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    float[] f464a;
    private String aa;
    private v af;
    private Object ag;
    private int aq;
    private int ar;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    float[] f465b;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    GLAnimation f469f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    GLAnimation f470g;
    private boolean j;
    private float k;
    private int v;
    private int w;
    private boolean l = false;
    private boolean m = false;
    private boolean n = false;
    private float o = 0.0f;
    private float p = 0.0f;
    private boolean q = false;
    private int r = 0;
    private int s = 0;
    private int t = 0;
    private int u = 0;
    private FPoint x = FPoint.obtain();
    private float[] y = {-1.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, -1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private float z = 0.0f;
    private float A = 1.0f;
    private float B = 1.0f;
    private float C = 1.0f;
    private boolean E = false;
    private boolean F = true;
    private int G = 5;
    private boolean H = true;
    private boolean I = true;
    private boolean J = false;
    private boolean K = false;
    private boolean L = false;
    private boolean M = true;
    private FPoint N = FPoint.obtain();
    private Point O = new Point();
    private int R = 0;
    private int S = 0;
    private x[] U = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Rect f466c = new Rect(0, 0, 0, 0);
    private boolean V = false;
    private float ab = 0.5f;
    private float ac = 1.0f;
    private boolean ad = false;
    private boolean ae = true;
    private boolean ah = false;
    private List<BitmapDescriptor> ai = new CopyOnWriteArrayList();
    private boolean aj = false;
    private boolean ak = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    GLTransformation f467d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    GLTransformation f468e = null;
    private boolean al = false;
    private boolean am = true;
    private int an = 0;
    private int ao = 20;
    private boolean ap = false;
    private long as = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    Object f471h = new Object();
    private float at = Float.MAX_VALUE;
    private float au = Float.MIN_VALUE;
    private float av = Float.MIN_VALUE;
    private float aw = Float.MAX_VALUE;

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate
    public void drawMarker(IAMapDelegate iAMapDelegate) {
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate
    public IAnimation getIAnimation() {
        return this;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker, com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate
    public IMarkerAction getIMarkerAction() {
        return this;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarkerAction
    public void setRotateAngleNotUpdate(float f2) {
    }

    private static String a(String str) {
        i++;
        return str + i;
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public void setRotateAngle(float f2) {
        this.D.rotateAngle(f2);
        this.p = f2;
        this.o = (((-f2) % 360.0f) + 360.0f) % 360.0f;
        this.n = true;
        c();
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public boolean isDestory() {
        return this.V;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public void destroy(boolean z) {
        try {
            this.V = true;
            if (z) {
                remove();
            }
            if (this.af != null) {
                for (int i2 = 0; this.U != null && i2 < this.U.length; i2++) {
                    x xVar = this.U[i2];
                    if (xVar != null) {
                        this.af.a(xVar);
                        this.af.c().removeTextureItem(xVar.p());
                    }
                }
            }
            for (int i3 = 0; this.ai != null && i3 < this.ai.size(); i3++) {
                this.ai.get(i3).recycle();
            }
            this.X = null;
            this.ag = null;
            this.U = null;
        } catch (Throwable th) {
            hn.c(th, "MarkerDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "MarkerDelegateImp destroy");
        }
    }

    synchronized void a() {
        if (this.ai != null) {
            this.ai.clear();
        }
    }

    public synchronized void a(ArrayList<BitmapDescriptor> arrayList) {
        BitmapDescriptor bitmapDescriptor;
        a();
        if (arrayList != null) {
            for (BitmapDescriptor bitmapDescriptor2 : arrayList) {
                if (bitmapDescriptor2 != null) {
                    this.ai.add(bitmapDescriptor2);
                }
            }
        }
        if (this.ai.size() == 0) {
            this.ai.add(BitmapDescriptorFactory.defaultMarker());
        }
        if (this.ai.size() > 0 && (bitmapDescriptor = this.ai.get(0)) != null) {
            this.R = bitmapDescriptor.getWidth();
            this.S = bitmapDescriptor.getHeight();
        }
    }

    public cu(MarkerOptions markerOptions, v vVar) {
        this.af = vVar;
        setMarkerOptions(markerOptions);
    }

    public IPoint b() {
        if (this.X == null && !this.ap) {
            return null;
        }
        FPoint fPointObtain = FPoint.obtain();
        this.af.c().getMapProjection().p20ToScreenPoint(this.v, this.w, fPointObtain);
        return IPoint.obtain((int) fPointObtain.x, (int) fPointObtain.y);
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public int getWidth() {
        try {
            return this.R;
        } catch (Throwable unused) {
            return 0;
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public int getHeight() {
        try {
            return this.S;
        } catch (Throwable unused) {
            return 0;
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IMarkerDelegate
    public FPoint anchorUVoff() {
        FPoint fPointObtain = FPoint.obtain();
        List<BitmapDescriptor> list = this.ai;
        if (list != null && list.size() != 0) {
            fPointObtain.x = getWidth() * this.ab;
            fPointObtain.y = getHeight() * this.ac;
        }
        return fPointObtain;
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public boolean isContains() {
        return this.af.c(this);
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public IPoint getAnchor() {
        IPoint iPointB = b();
        if (iPointB == null) {
            return null;
        }
        return iPointB;
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public Rect getRect() {
        if (this.y == null) {
            this.f466c.set(0, 0, 0, 0);
            return this.f466c;
        }
        try {
            GLMapState mapProjection = this.af.c().getMapProjection();
            if (mapProjection == null) {
                return new Rect(0, 0, 0, 0);
            }
            int width = getWidth();
            int height = getHeight();
            FPoint fPointObtain = FPoint.obtain();
            if (this.ap) {
                fPointObtain.x = this.aq;
                fPointObtain.y = this.ar;
            } else {
                mapProjection.p20ToScreenPoint(this.v, this.w, fPointObtain);
            }
            Matrix.setIdentityM(this.f464a, 0);
            Matrix.rotateM(this.f464a, 0, -this.o, 0.0f, 0.0f, 1.0f);
            if (this.q) {
                Matrix.rotateM(this.f464a, 0, this.af.c().getMapConfig().getSC(), 1.0f, 0.0f, 0.0f);
                Matrix.rotateM(this.f464a, 0, this.af.c().getMapConfig().getSR(), 0.0f, 0.0f, 1.0f);
            }
            float[] fArr = new float[4];
            float f2 = -width;
            this.f465b[0] = this.ab * f2;
            float f3 = height;
            this.f465b[1] = this.ac * f3;
            this.f465b[2] = 0.0f;
            this.f465b[3] = 1.0f;
            Matrix.multiplyMV(fArr, 0, this.f464a, 0, this.f465b, 0);
            this.f466c.set((int) (fPointObtain.x + fArr[0]), (int) (fPointObtain.y - fArr[1]), (int) (fPointObtain.x + fArr[0]), (int) (fPointObtain.y - fArr[1]));
            float f4 = width;
            this.f465b[0] = (1.0f - this.ab) * f4;
            this.f465b[1] = f3 * this.ac;
            this.f465b[2] = 0.0f;
            this.f465b[3] = 1.0f;
            Matrix.multiplyMV(fArr, 0, this.f464a, 0, this.f465b, 0);
            this.f466c.union((int) (fPointObtain.x + fArr[0]), (int) (fPointObtain.y - fArr[1]));
            this.f465b[0] = f4 * (1.0f - this.ab);
            float f5 = -height;
            this.f465b[1] = (1.0f - this.ac) * f5;
            this.f465b[2] = 0.0f;
            this.f465b[3] = 1.0f;
            Matrix.multiplyMV(fArr, 0, this.f464a, 0, this.f465b, 0);
            this.f466c.union((int) (fPointObtain.x + fArr[0]), (int) (fPointObtain.y - fArr[1]));
            this.f465b[0] = f2 * this.ab;
            this.f465b[1] = f5 * (1.0f - this.ac);
            this.f465b[2] = 0.0f;
            this.f465b[3] = 1.0f;
            Matrix.multiplyMV(fArr, 0, this.f464a, 0, this.f465b, 0);
            this.f466c.union((int) (fPointObtain.x + fArr[0]), (int) (fPointObtain.y - fArr[1]));
            this.t = (int) (this.f466c.centerX() - fPointObtain.x);
            this.u = (int) (this.f466c.top - fPointObtain.y);
            fPointObtain.recycle();
            return this.f466c;
        } catch (Throwable th) {
            hn.c(th, "MarkerDelegateImp", "getRect");
            th.printStackTrace();
            return new Rect(0, 0, 0, 0);
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public boolean remove() {
        c();
        this.ae = false;
        v vVar = this.af;
        if (vVar != null) {
            return vVar.a((IOverlayImageDelegate) this);
        }
        return false;
    }

    private void c() {
        if (this.af.c() != null) {
            this.af.c().setRunLowFrame(false);
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public LatLng getPosition() {
        if (this.ap && this.x != null) {
            DPoint dPointObtain = DPoint.obtain();
            IPoint iPointObtain = IPoint.obtain();
            calFPoint();
            if (this.af.c() == null) {
                return this.X;
            }
            this.af.c().map2Geo(this.x.x, this.x.y, iPointObtain);
            GLMapState.geo2LonLat(iPointObtain.x, iPointObtain.y, dPointObtain);
            LatLng latLng = new LatLng(dPointObtain.y, dPointObtain.x);
            iPointObtain.recycle();
            dPointObtain.recycle();
            return latLng;
        }
        return this.X;
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public String getId() {
        if (this.W == null) {
            this.W = a("Marker");
        }
        return this.W;
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public void setPosition(LatLng latLng) {
        if (latLng == null) {
            hn.c(new AMapException("非法坐标值 latlng is null"), "setPosition", "Marker");
            return;
        }
        this.X = latLng;
        IPoint iPointObtain = IPoint.obtain();
        if (this.aj) {
            try {
                double[] dArrA = jy.a(latLng.longitude, latLng.latitude);
                this.Y = new LatLng(dArrA[1], dArrA[0]);
                GLMapState.lonlat2Geo(dArrA[0], dArrA[1], iPointObtain);
            } catch (Throwable unused) {
                this.Y = latLng;
            }
        } else {
            GLMapState.lonlat2Geo(latLng.longitude, latLng.latitude, iPointObtain);
        }
        this.v = iPointObtain.x;
        this.w = iPointObtain.y;
        this.ap = false;
        calFPoint();
        c();
        this.n = true;
        iPointObtain.recycle();
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public void setTitle(String str) {
        this.Z = str;
        c();
        this.D.title(str);
        ea.a().a(this.X, this.Z, this.aa);
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public String getTitle() {
        return this.Z;
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public void setSnippet(String str) {
        this.aa = str;
        c();
        this.D.snippet(str);
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public String getSnippet() {
        return this.aa;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public void setDraggable(boolean z) {
        this.ad = z;
        this.D.draggable(z);
        c();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public synchronized void setIcons(ArrayList<BitmapDescriptor> arrayList) {
        if (arrayList != null) {
            try {
                if (this.ai != null) {
                    this.j = false;
                    a(arrayList);
                    this.ak = false;
                    this.l = false;
                    this.J = false;
                    c();
                    this.n = true;
                }
            } finally {
            }
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public synchronized ArrayList<BitmapDescriptor> getIcons() {
        if (this.ai == null || this.ai.size() <= 0) {
            return null;
        }
        ArrayList<BitmapDescriptor> arrayList = new ArrayList<>();
        Iterator<BitmapDescriptor> it = this.ai.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public void setIcon(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor != null) {
            try {
                if (this.ai == null) {
                    return;
                }
                synchronized (this) {
                    this.j = false;
                    this.ai.clear();
                    this.ai.add(bitmapDescriptor);
                    this.J = false;
                    this.ak = false;
                    this.l = false;
                    c();
                    this.n = true;
                    this.R = bitmapDescriptor.getWidth();
                    this.S = bitmapDescriptor.getHeight();
                }
            } catch (Throwable th) {
                hn.c(th, "MarkerDelegateImp", "setIcon");
                th.printStackTrace();
            }
        }
    }

    private void d() {
        try {
            if (this.T.a()) {
                this.y[4] = this.T.d();
                this.y[5] = this.T.c();
                this.y[13] = this.T.b();
                this.y[14] = this.T.c();
                this.y[22] = this.T.b();
                this.y[23] = this.T.e();
                this.y[31] = this.T.d();
                this.y[32] = this.T.e();
            } else {
                this.y[4] = this.T.g();
                this.y[5] = this.T.i();
                this.y[13] = this.T.h();
                this.y[14] = this.T.i();
                this.y[22] = this.T.h();
                this.y[23] = this.T.f();
                this.y[31] = this.T.g();
                this.y[32] = this.T.f();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public synchronized BitmapDescriptor getBitmapDescriptor() {
        try {
            if (this.ai == null) {
                return null;
            }
            if (this.ai.size() == 0) {
                a();
                this.ai.add(BitmapDescriptorFactory.defaultMarker());
            } else if (this.ai.get(0) == null) {
                this.ai.clear();
                return getBitmapDescriptor();
            }
            return this.ai.get(0);
        } catch (Throwable th) {
            hn.c(th, "MarkerDelegateImp", "getBitmapDescriptor");
            th.printStackTrace();
            return null;
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IMarker
    public boolean isDraggable() {
        return this.ad;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public boolean isRemoved() {
        try {
            return !this.af.c(this);
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public void showInfoWindow() {
        if (this.ae && isContains() && !isRemoved() && isInfoWindowEnable()) {
            this.af.a((BaseOverlayImp) this);
            c();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public void hideInfoWindow() {
        if (isInfoWindowShown()) {
            this.af.b(this);
            c();
            this.m = false;
        }
        this.n = false;
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public void setInfoWindowShown(boolean z) {
        this.m = z;
        this.n = true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker, com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate
    public boolean isInfoWindowShown() {
        return this.m;
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public void setVisible(boolean z) {
        if (this.ae == z) {
            return;
        }
        this.D.visible(z);
        this.ae = z;
        if (!z) {
            this.L = false;
            if (isInfoWindowShown()) {
                this.af.b(this);
            }
        }
        c();
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public boolean isVisible() {
        return this.ae;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public void setAnchor(float f2, float f3) {
        if (this.ab == f2 && this.ac == f3) {
            return;
        }
        this.D.anchor(f2, f3);
        this.ab = f2;
        this.ac = f3;
        this.n = true;
        c();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public float getAnchorU() {
        return this.ab;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public float getAnchorV() {
        return this.ac;
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
        try {
            if (this.af != null && this.af.c() != null && this.af.c().getMapProjection() != null) {
                if (this.x == null) {
                    this.x = FPoint.obtain();
                }
                if (this.ap) {
                    IPoint iPointObtain = IPoint.obtain();
                    this.af.c().getPixel2Geo(this.aq, this.ar, iPointObtain);
                    this.v = iPointObtain.x;
                    this.w = iPointObtain.y;
                    iPointObtain.recycle();
                    this.af.c().geo2Map(this.v, this.w, this.x);
                } else {
                    this.af.c().geo2Map(this.v, this.w, this.x);
                }
                return true;
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    private void a(IAMapDelegate iAMapDelegate, float f2, int i2, int i3) throws RemoteException {
        float f3 = ((int) (this.A * i2)) * f2;
        float f4 = ((int) (this.B * i3)) * f2;
        float f5 = this.x.x;
        float f6 = this.x.y;
        float sc = iAMapDelegate.getMapConfig().getSC();
        float sr = this.o;
        List<BitmapDescriptor> list = this.ai;
        if (list != null && list.size() > 0) {
            if (this.j) {
                int length = this.U.length;
                float f7 = this.o;
                float f8 = this.k;
                int i4 = (int) (f7 / f8);
                if (i4 > length) {
                    i4 = 0;
                } else {
                    sr = f7 % f8;
                }
                this.T = this.U[(i4 + length) % length];
            } else {
                this.an++;
                if (this.an >= this.ao * this.ai.size()) {
                    this.an = 0;
                }
                if (this.ao == 0) {
                    this.ao = 1;
                }
                this.T = this.U[this.an / this.ao];
                if (!this.am) {
                    c();
                }
            }
        }
        if (this.q) {
            sr -= iAMapDelegate.getMapConfig().getSR();
            sc = 0.0f;
        }
        float f9 = this.C;
        if (f9 < 0.0f) {
            f9 = 0.0f;
        }
        if (f9 > 1.0f) {
            f9 = 1.0f;
        }
        float[] fArr = this.y;
        float f10 = this.ab;
        fArr[0] = f5 - (f3 * f10);
        float f11 = this.ac;
        fArr[1] = ((1.0f - f11) * f4) + f6;
        fArr[2] = f5;
        fArr[3] = f6;
        fArr[6] = sr;
        fArr[7] = sc;
        fArr[8] = f9;
        fArr[9] = ((1.0f - f10) * f3) + f5;
        fArr[10] = ((1.0f - f11) * f4) + f6;
        fArr[11] = f5;
        fArr[12] = f6;
        fArr[15] = sr;
        fArr[16] = sc;
        fArr[17] = f9;
        fArr[18] = ((1.0f - f10) * f3) + f5;
        fArr[19] = f6 - (f4 * f11);
        fArr[20] = f5;
        fArr[21] = f6;
        fArr[24] = sr;
        fArr[25] = sc;
        fArr[26] = f9;
        fArr[27] = f5 - (f3 * f10);
        fArr[28] = f6 - (f4 * f11);
        fArr[29] = f5;
        fArr[30] = f6;
        fArr[33] = sr;
        fArr[34] = sc;
        fArr[35] = f9;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate
    public void drawMarker(IAMapDelegate iAMapDelegate, float[] fArr, int i2, float f2) {
        if (this.V || (this.X == null && !this.ap) || this.ai == null) {
            return;
        }
        try {
            if (!this.l) {
                this.as = System.currentTimeMillis();
                this.l = true;
            }
            if (this.ap && this.I) {
                IPoint iPointObtain = IPoint.obtain();
                iAMapDelegate.getPixel2Geo(this.aq, this.ar, iPointObtain);
                this.v = iPointObtain.x;
                this.w = iPointObtain.y;
                iPointObtain.recycle();
            }
            this.x.x = this.v - ((int) iAMapDelegate.getMapConfig().getSX());
            if (this.x.x > 1.3421773E8f) {
                this.x.x -= 2.6843546E8f;
            } else if (this.x.x < -1.3421773E8f) {
                this.x.x += 2.6843546E8f;
            }
            this.x.y = this.w - ((int) iAMapDelegate.getMapConfig().getSY());
            int width = getWidth();
            int height = getHeight();
            e();
            a(iAMapDelegate, f2, width, height);
            if (!this.J || !this.am) {
                d();
                this.J = true;
            }
            a(fArr, i2);
            if (this.n && isInfoWindowShown()) {
                this.af.c().redrawInfoWindow();
                if (System.currentTimeMillis() - this.as > 1000) {
                    this.n = false;
                }
            }
        } catch (Throwable th) {
            hn.c(th, "MarkerDelegateImp", "drawMarker");
        }
    }

    private void a(float[] fArr, int i2) {
        x[] xVarArr = this.U;
        if (xVarArr == null || xVarArr.length <= 0) {
            return;
        }
        float[] fArr2 = this.y;
        System.arraycopy(fArr2, 0, fArr, i2, fArr2.length);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x005b A[Catch: all -> 0x00e9, TryCatch #1 {all -> 0x00e9, blocks: (B:5:0x0005, B:7:0x000a, B:9:0x0010, B:11:0x0014, B:12:0x0019, B:13:0x001c, B:15:0x0023, B:19:0x0037, B:20:0x003e, B:22:0x0044, B:24:0x004c, B:26:0x0052, B:30:0x005b, B:32:0x0062, B:34:0x0068, B:36:0x006e, B:38:0x0086, B:40:0x008f, B:41:0x0092, B:42:0x0096, B:44:0x009e, B:47:0x00c5, B:45:0x00b9, B:48:0x00c8, B:49:0x00d3, B:51:0x00db, B:53:0x00e0, B:52:0x00de), top: B:65:0x0005, outer: #0 }] */
    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void loadTexture(com.autonavi.base.amap.api.mapcore.IAMapDelegate r13) {
        /*
            Method dump skipped, instruction units count: 247
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.cu.loadTexture(com.autonavi.base.amap.api.mapcore.IAMapDelegate):void");
    }

    private void e() {
        GLAnimation gLAnimation;
        if (!this.M && (gLAnimation = this.f469f) != null && !gLAnimation.hasEnded()) {
            c();
            synchronized (this.f471h) {
                if (this.f468e == null || this.al) {
                    this.f468e = new GLTransformation();
                    this.f468e.scaleX = this.A;
                    this.f468e.scaleY = this.B;
                    this.f468e.rotate = this.o;
                    this.f468e.y = this.w;
                    this.f468e.x = this.v;
                    this.f468e.alpha = this.C;
                    this.al = false;
                }
                if (this.f467d == null) {
                    this.f467d = new GLTransformation();
                }
                this.f467d.clear();
                this.f469f.getTransformation(AnimationUtils.currentAnimationTimeMillis(), this.f467d);
                if (this.f467d != null) {
                    if (!Double.isNaN(this.f467d.scaleX) && !Double.isNaN(this.f467d.scaleY)) {
                        this.A = (float) this.f467d.scaleX;
                        this.B = (float) this.f467d.scaleY;
                    }
                    if (!Double.isNaN(this.f467d.rotate)) {
                        setRotateAngle((float) this.f467d.rotate);
                    }
                    if (!Double.isNaN(this.f467d.x) && !Double.isNaN(this.f467d.y)) {
                        a(this.f467d.x, this.f467d.y);
                    }
                    if (!Double.isNaN(this.f467d.alpha)) {
                        this.C = (float) this.f467d.alpha;
                    }
                }
            }
            this.n = true;
            this.am = false;
            return;
        }
        if (this.f469f != null && (this.f467d != null || this.f468e != null)) {
            GLTransformation gLTransformation = this.f467d;
            if (gLTransformation != null && !Double.isNaN(gLTransformation.scaleX) && !Double.isNaN(this.f467d.scaleY)) {
                this.A = (float) this.f467d.scaleX;
                this.B = (float) this.f467d.scaleY;
            } else {
                GLTransformation gLTransformation2 = this.f468e;
                if (gLTransformation2 != null && !Double.isNaN(gLTransformation2.scaleX) && !Double.isNaN(this.f468e.scaleY) && (this.A != this.f468e.scaleX || this.B != this.f468e.scaleY)) {
                    this.A = (float) this.f468e.scaleX;
                    this.B = (float) this.f468e.scaleY;
                }
            }
            GLTransformation gLTransformation3 = this.f467d;
            if (gLTransformation3 != null && !Double.isNaN(gLTransformation3.rotate)) {
                setRotateAngle((float) this.f467d.rotate);
            } else {
                GLTransformation gLTransformation4 = this.f468e;
                if (gLTransformation4 != null && !Double.isNaN(gLTransformation4.rotate) && this.o != this.f468e.rotate) {
                    setRotateAngle((float) this.f468e.rotate);
                }
            }
            GLTransformation gLTransformation5 = this.f467d;
            if (gLTransformation5 != null && !Double.isNaN(gLTransformation5.x) && !Double.isNaN(this.f467d.y)) {
                a(this.f467d.x, this.f467d.y);
            } else {
                GLTransformation gLTransformation6 = this.f468e;
                if (gLTransformation6 != null && !Double.isNaN(gLTransformation6.x) && !Double.isNaN(this.f468e.y) && (this.v != this.f468e.x || this.w != this.f468e.y)) {
                    a(this.f468e.x, this.f468e.y);
                }
            }
            GLTransformation gLTransformation7 = this.f467d;
            if (gLTransformation7 != null && !Double.isNaN(gLTransformation7.alpha)) {
                this.C = (float) this.f467d.alpha;
            } else {
                GLTransformation gLTransformation8 = this.f468e;
                if (gLTransformation8 != null && !Double.isNaN(gLTransformation8.alpha) && this.C != this.f468e.alpha) {
                    this.C = (float) this.f468e.alpha;
                }
            }
        }
        this.M = true;
        this.f467d = null;
        this.f468e = null;
        List<BitmapDescriptor> list = this.ai;
        if (list == null || list.size() != 1) {
            return;
        }
        this.am = true;
    }

    private void a(double d2, double d3) {
        if (this.ap) {
            IPoint iPointObtain = IPoint.obtain();
            this.af.c().getPixel2Geo((int) d2, (int) d3, iPointObtain);
            a(iPointObtain.x, iPointObtain.y);
            iPointObtain.recycle();
            this.ap = true;
            return;
        }
        a((int) d2, (int) d3);
    }

    private int f() {
        int[] iArr = {0};
        GLES20.glGenTextures(1, iArr, 0);
        return iArr[0];
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate
    public boolean isAllowLow() {
        return this.am;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public void setPeriod(int i2) {
        if (i2 <= 1) {
            this.ao = 1;
        } else {
            this.ao = i2;
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public void setObject(Object obj) {
        this.ag = obj;
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public Object getObject() {
        return this.ag;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public void setPerspective(boolean z) {
        this.ah = z;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public boolean isPerspective() {
        return this.ah;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate
    public int getTextureId() {
        try {
            if (this.ai != null && this.ai.size() > 0) {
                return this.T.k();
            }
            return 0;
        } catch (Throwable unused) {
            return 0;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public int getPeriod() {
        return this.ao;
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public LatLng getRealPosition() {
        try {
            if (!this.ap) {
                return this.aj ? this.Y : this.X;
            }
            DPoint dPointObtain = DPoint.obtain();
            this.af.c().getPixel2LatLng(this.aq, this.ar, dPointObtain);
            LatLng latLng = new LatLng(dPointObtain.y, dPointObtain.y);
            dPointObtain.recycle();
            return latLng;
        } catch (Throwable th) {
            hn.c(th, "MarkerDelegateImp", "getRealPosition");
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public void set2Top() {
        this.af.a((IMarkerDelegate) this);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public void setFlat(boolean z) throws RemoteException {
        this.q = z;
        c();
        this.D.setFlat(z);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public boolean isFlat() {
        return this.q;
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public float getRotateAngle() {
        c();
        return this.p;
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public void setInfoWindowOffset(int i2, int i3) throws RemoteException {
        this.r = i2;
        this.s = i3;
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public int getInfoWindowOffsetX() {
        return this.r;
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public int getInfoWindowOffsetY() {
        return this.s;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public void setPositionByPixels(int i2, int i3) {
        this.aq = i2;
        this.ar = i3;
        this.ap = true;
        calFPoint();
        c();
        this.n = true;
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public int getRealInfoWindowOffsetX() {
        return this.t;
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public int getRealInfoWindowOffsetY() {
        return this.u;
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public FPoint getGeoPosition() {
        IPoint geoPoint = getGeoPoint();
        return FPoint.obtain(geoPoint.x, geoPoint.y);
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public IPoint getScreenPosition() {
        return IPoint.obtain(this.aq, this.ar);
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public boolean isViewMode() {
        return this.ap;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public void setZIndex(float f2) {
        this.z = f2;
        this.D.zIndex(f2);
        if (this.L) {
            this.L = false;
            this.af.a();
        }
        this.af.f();
        c();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public float getZIndex() {
        return this.z;
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public boolean checkInBounds() {
        if (this.ap) {
            return true;
        }
        try {
            if (this.x == null) {
                return false;
            }
            if (!this.M) {
                return true;
            }
            this.O.x = this.v;
            this.O.y = this.w;
            Rectangle geoRectangle = this.af.c().getMapConfig().getGeoRectangle();
            if (geoRectangle.contains(this.v, this.w)) {
                return true;
            }
            g();
            int i2 = (int) (this.A * this.P);
            int i3 = (int) (this.B * this.Q);
            int i4 = (int) (this.v - (i2 * this.ab));
            int i5 = (int) (this.w - (i3 * this.ac));
            if (geoRectangle.contains(i4, i5)) {
                return true;
            }
            return geoRectangle.isOverlap(i4, i5, i2, i3);
        } catch (Throwable th) {
            hn.c(th, "MarkerDelegateImp", "checkInBounds");
            return false;
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public void setGeoPoint(IPoint iPoint) {
        this.ap = false;
        a(iPoint.x, iPoint.y);
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public void destroy() {
        destroy(true);
    }

    private void a(int i2, int i3) {
        this.v = i2;
        this.w = i3;
        DPoint dPointObtain = DPoint.obtain();
        GLMapState.geo2LonLat(this.v, this.w, dPointObtain);
        this.X = new LatLng(dPointObtain.y, dPointObtain.x, false);
        v vVar = this.af;
        if (vVar != null && vVar.c() != null) {
            this.x.x = this.v - ((int) this.af.c().getMapConfig().getSX());
            this.x.y = this.w - ((int) this.af.c().getMapConfig().getSY());
        }
        dPointObtain.recycle();
        c();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public IPoint getGeoPoint() {
        IPoint iPointObtain = IPoint.obtain();
        if (this.ap) {
            this.af.c().getPixel2Geo(this.aq, this.ar, iPointObtain);
            return iPointObtain;
        }
        iPointObtain.set(this.v, this.w);
        return iPointObtain;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate
    public synchronized void reLoadTexture() {
        this.ak = false;
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public void setAnimation(Animation animation) {
        IAnimation iAnimation = getIAnimation();
        if (iAnimation != null) {
            iAnimation.setAnimation(animation == null ? null : animation.glAnimation);
        }
    }

    @Override // com.autonavi.base.amap.mapcore.interfaces.IAnimation
    public void setAnimation(GLAnimation gLAnimation) {
        if (gLAnimation == null) {
            return;
        }
        this.f470g = gLAnimation;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker, com.autonavi.base.amap.mapcore.interfaces.IAnimation
    public boolean startAnimation() {
        if (this.f470g != null) {
            synchronized (this.f471h) {
                if (this.f470g instanceof GLAnimationSet) {
                    GLAnimationSet gLAnimationSet = (GLAnimationSet) this.f470g;
                    for (GLAnimation gLAnimation : gLAnimationSet.getAnimations()) {
                        a(gLAnimation);
                        gLAnimation.setDuration(gLAnimationSet.getDuration());
                    }
                } else {
                    a(this.f470g);
                }
                this.M = false;
                this.f469f = this.f470g;
                this.al = true;
                this.f469f.start();
            }
            c();
        }
        return false;
    }

    private void a(GLAnimation gLAnimation) {
        if (gLAnimation instanceof GLTranslateAnimation) {
            if (this.ap) {
                this.X = getPosition();
                setPosition(this.X);
                this.ap = true;
            }
            if (this.ap) {
                GLTranslateAnimation gLTranslateAnimation = (GLTranslateAnimation) gLAnimation;
                gLTranslateAnimation.mFromXDelta = this.aq;
                gLTranslateAnimation.mFromYDelta = this.ar;
                IPoint iPointObtain = IPoint.obtain();
                this.af.c().getLatLng2Pixel(gLTranslateAnimation.mToYDelta, gLTranslateAnimation.mToXDelta, iPointObtain);
                gLTranslateAnimation.mToXDelta = iPointObtain.x;
                gLTranslateAnimation.mToYDelta = iPointObtain.y;
                iPointObtain.recycle();
                return;
            }
            GLTranslateAnimation gLTranslateAnimation2 = (GLTranslateAnimation) gLAnimation;
            gLTranslateAnimation2.mFromXDelta = this.v;
            gLTranslateAnimation2.mFromYDelta = this.w;
            IPoint iPointObtain2 = IPoint.obtain();
            GLMapState.lonlat2Geo(gLTranslateAnimation2.mToXDelta, gLTranslateAnimation2.mToYDelta, iPointObtain2);
            gLTranslateAnimation2.mToXDelta = iPointObtain2.x;
            gLTranslateAnimation2.mToYDelta = iPointObtain2.y;
            iPointObtain2.recycle();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker, com.autonavi.base.amap.mapcore.interfaces.IAnimation
    public void setAnimationListener(Animation.AnimationListener animationListener) {
        GLAnimation gLAnimation = this.f470g;
        if (gLAnimation != null) {
            gLAnimation.setAnimationListener(animationListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarkerAction
    public float getAlpha() {
        return this.C;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarkerAction
    public void setAlpha(float f2) {
        this.C = f2;
        this.D.alpha(f2);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarkerAction
    public int getDisplayLevel() {
        return this.G;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarkerAction
    public MarkerOptions getOptions() {
        return this.D;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarkerAction
    public void setMarkerOptions(MarkerOptions markerOptions) {
        if (markerOptions == null) {
            return;
        }
        this.D = markerOptions;
        this.X = this.D.getPosition();
        IPoint iPointObtain = IPoint.obtain();
        this.aj = this.D.isGps();
        if (this.D.getPosition() != null) {
            if (this.aj) {
                try {
                    double[] dArrA = jy.a(this.D.getPosition().longitude, this.D.getPosition().latitude);
                    this.Y = new LatLng(dArrA[1], dArrA[0]);
                    GLMapState.lonlat2Geo(dArrA[0], dArrA[1], iPointObtain);
                } catch (Throwable th) {
                    hn.c(th, "MarkerDelegateImp", "create");
                    this.Y = this.D.getPosition();
                }
            } else {
                GLMapState.lonlat2Geo(this.X.longitude, this.X.latitude, iPointObtain);
            }
        }
        this.v = iPointObtain.x;
        this.w = iPointObtain.y;
        this.ab = this.D.getAnchorU();
        this.ac = this.D.getAnchorV();
        this.r = this.D.getInfoWindowOffsetX();
        this.s = this.D.getInfoWindowOffsetY();
        this.ao = this.D.getPeriod();
        this.z = this.D.getZIndex();
        this.K = this.D.isBelowMaskLayer();
        calFPoint();
        setIcons(this.D.getIcons());
        this.j = this.D.isRotatingMode();
        this.k = this.D.getAngleOffset();
        this.ae = this.D.isVisible();
        this.aa = this.D.getSnippet();
        this.Z = this.D.getTitle();
        this.ad = this.D.isDraggable();
        this.W = getId();
        this.ah = this.D.isPerspective();
        this.q = this.D.isFlat();
        this.K = this.D.isBelowMaskLayer();
        this.C = this.D.getAlpha();
        setRotateAngle(this.D.getRotateAngle());
        this.G = this.D.getDisplayLevel();
        this.E = this.D.isInfoWindowAutoOverturn();
        this.F = this.D.isInfoWindowEnable();
        this.f464a = new float[16];
        this.f465b = new float[4];
        iPointObtain.recycle();
        ea.a().a(this.X, this.Z, this.aa);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarkerAction
    public boolean isClickable() {
        return this.H;
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IMarkerAction
    public boolean isInfoWindowAutoOverturn() {
        return this.E;
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public boolean isInfoWindowEnable() {
        return this.F;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate
    public void setOnTap(boolean z) {
        this.L = z;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate
    public boolean isOnTap() {
        return this.L;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarkerAction
    public void setInfoWindowEnable(boolean z) {
        this.F = z;
        if (!z) {
            hideInfoWindow();
        }
        this.D.infoWindowEnable(z);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarkerAction
    public void setAutoOverturnInfoWindow(boolean z) {
        this.E = z;
        this.D.autoOverturnInfoWindow(z);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarkerAction
    public void setClickable(boolean z) {
        this.H = z;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarkerAction
    public void setDisplayLevel(int i2) {
        this.G = i2;
        this.D.displayLevel(i2);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarkerAction
    public void setFixingPointEnable(boolean z) {
        this.I = z;
        if (!z) {
            boolean z2 = this.ap;
            this.X = getPosition();
            setPosition(this.X);
            if (z2) {
                this.ap = true;
                return;
            }
            return;
        }
        if (!this.ap || this.X == null) {
            return;
        }
        FPoint fPointObtain = FPoint.obtain();
        this.af.c().getMapProjection().p20ToScreenPoint(this.v, this.w, fPointObtain);
        this.aq = (int) fPointObtain.x;
        this.ar = (int) fPointObtain.y;
        fPointObtain.recycle();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarkerAction
    public void setPositionNotUpdate(LatLng latLng) {
        setPosition(latLng);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public void setBelowMaskLayer(boolean z) {
        this.K = z;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayImageDelegate
    public boolean isBelowMaskLayer() {
        return this.K;
    }

    private void g() {
        if (this.af.c() == null || this.af.c().getMapConfig() == null) {
            return;
        }
        this.P = this.af.c().getMapConfig().getMapPerPixelUnitLength() * getWidth();
        this.Q = this.af.c().getMapConfig().getMapPerPixelUnitLength() * getHeight();
    }

    private Bitmap a(Bitmap bitmap) {
        return (bitmap == null || bitmap.getConfig() == Bitmap.Config.ARGB_8888) ? bitmap : bitmap.copy(Bitmap.Config.ARGB_8888, true);
    }
}