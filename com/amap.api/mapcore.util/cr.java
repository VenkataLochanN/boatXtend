package com.amap.api.mapcore.util;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.os.RemoteException;
import android.view.animation.AnimationUtils;
import com.amap.api.mapcore.util.de;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.GL3DModelOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.animation.Animation;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.animation.GLAnimation;
import com.autonavi.amap.mapcore.animation.GLAnimationSet;
import com.autonavi.amap.mapcore.animation.GLTransformation;
import com.autonavi.amap.mapcore.animation.GLTranslateAnimation;
import com.autonavi.amap.mapcore.interfaces.IglModel;
import com.autonavi.base.ae.gmap.GLMapState;
import com.autonavi.base.amap.api.mapcore.BaseOverlayImp;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.mapcore.FPoint;
import java.util.List;

/* JADX INFO: compiled from: Gl3DModelImp.java */
/* JADX INFO: loaded from: classes.dex */
public class cr extends BaseOverlayImp implements IglModel {
    private String H;
    private String I;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    float[] f440a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    float[] f441b;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f445f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private q f447h;
    private BitmapDescriptor i;
    private IAMapDelegate j;
    private int k;
    private int l;
    private LatLng m;
    private GLAnimation n;
    private Bitmap q;
    private de.b r;
    private float s;
    private Object t;
    private o y;
    private int z;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f444e = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private float[] f446g = new float[16];
    private boolean o = true;
    private boolean p = true;
    private float u = 18.0f;
    private float v = -1.0f;
    private float w = 0.0f;
    private boolean x = false;
    private boolean A = false;
    private boolean B = false;
    private FPoint C = FPoint.obtain();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Rect f442c = new Rect(0, 0, 0, 0);
    private int D = 0;
    private int E = 0;
    private float F = 0.5f;
    private float G = 0.5f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    float f443d = 1.0f;
    private float J = -1.0f;

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public IPoint getAnchor() {
        return null;
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public int getInfoWindowOffsetX() {
        return 0;
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public int getInfoWindowOffsetY() {
        return 0;
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public LatLng getRealPosition() {
        return null;
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public float getRotateAngle() {
        return 0.0f;
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public boolean isDestory() {
        return false;
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public boolean isInfoWindowEnable() {
        return true;
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public boolean isViewMode() {
        return false;
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public void setInfoWindowOffset(int i, int i2) throws RemoteException {
    }

    public cr(o oVar, GL3DModelOptions gL3DModelOptions, IAMapDelegate iAMapDelegate) {
        this.f440a = new float[16];
        this.f441b = new float[16];
        if (gL3DModelOptions == null || iAMapDelegate == null) {
            return;
        }
        this.y = oVar;
        this.j = iAMapDelegate;
        this.i = gL3DModelOptions.getBitmapDescriptor();
        List<Float> textrue = gL3DModelOptions.getTextrue();
        List<Float> vertext = gL3DModelOptions.getVertext();
        this.m = gL3DModelOptions.getLatLng();
        this.s = gL3DModelOptions.getAngle();
        setModelFixedLength(gL3DModelOptions.getModelFixedLength());
        if (this.m != null) {
            IPoint iPointObtain = IPoint.obtain();
            GLMapState.lonlat2Geo(this.m.longitude, this.m.latitude, iPointObtain);
            this.k = iPointObtain.x;
            this.l = iPointObtain.y;
        }
        if (textrue != null && textrue.size() > 0 && vertext != null) {
            if ((vertext.size() > 0) & (this.i != null)) {
                this.f447h = new q(vertext, textrue);
                this.f447h.a(this.s);
            }
        }
        this.f440a = new float[16];
        this.f441b = new float[4];
    }

    public void a() {
        try {
            if (this.f447h != null) {
                if (this.r == null) {
                    this.r = (de.b) this.j.getGLShader(5);
                }
                if (this.v == -1.0f) {
                    this.v = this.j.getUnitLengthByZoom((int) this.u);
                }
                if (this.f444e) {
                    this.z = a(this.i.getBitmap());
                    this.f447h.a(this.z);
                    this.f444e = false;
                }
                d();
                float sx = this.k - ((int) this.j.getMapConfig().getSX());
                this.C.x = sx;
                float sy = this.l - ((int) this.j.getMapConfig().getSY());
                this.C.y = sy;
                Matrix.setIdentityM(this.f446g, 0);
                Matrix.multiplyMM(this.f446g, 0, this.j.getProjectionMatrix(), 0, this.j.getViewMatrix(), 0);
                Matrix.translateM(this.f446g, 0, sx, sy, 0.0f);
                if (this.x) {
                    this.f443d = c();
                } else {
                    this.f443d = b();
                }
                Matrix.scaleM(this.f446g, 0, this.f443d, this.f443d, this.f443d);
                this.f447h.a(this.r, this.f446g);
                if (this.B) {
                    this.j.redrawInfoWindow();
                    this.B = false;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private float b() {
        float f2;
        float mapPerPixelUnitLength = this.j.getMapConfig().getMapPerPixelUnitLength();
        if (this.j.getMapConfig().getSZ() >= this.u) {
            this.J = mapPerPixelUnitLength;
            f2 = this.J;
        } else {
            f2 = this.v;
        }
        return mapPerPixelUnitLength / f2;
    }

    private float c() {
        return (this.j.getMapConfig().getMapPerPixelUnitLength() * this.w) / this.f447h.a();
    }

    private int a(Bitmap bitmap) {
        if (bitmap != null) {
            this.q = bitmap;
        }
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        GLES20.glBindTexture(3553, iArr[0]);
        GLES20.glTexParameterf(3553, 10241, 9728.0f);
        GLES20.glTexParameterf(3553, 10240, 9729.0f);
        GLES20.glTexParameterf(3553, 10242, 33071.0f);
        GLES20.glTexParameterf(3553, 10243, 33071.0f);
        GLUtils.texImage2D(3553, 0, this.q, 0);
        return iArr[0];
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public void setPosition(LatLng latLng) {
        if (latLng != null) {
            this.m = latLng;
            IPoint iPointObtain = IPoint.obtain();
            GLMapState.lonlat2Geo(latLng.longitude, latLng.latitude, iPointObtain);
            this.k = iPointObtain.x;
            this.l = iPointObtain.y;
            iPointObtain.recycle();
        }
        this.B = true;
        this.j.setRunLowFrame(false);
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public void setRotateAngle(float f2) {
        this.s = f2;
        if (this.f447h != null) {
            this.f447h.a(this.s - this.j.getMapConfig().getSR());
        }
        this.B = true;
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public LatLng getPosition() {
        return this.m;
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public void setAnimation(Animation animation) {
        if (animation == null) {
            return;
        }
        this.n = animation.glAnimation;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IglModel
    public boolean startAnimation() {
        GLAnimation gLAnimation = this.n;
        if (gLAnimation != null) {
            if (gLAnimation instanceof GLAnimationSet) {
                GLAnimationSet gLAnimationSet = (GLAnimationSet) gLAnimation;
                for (GLAnimation gLAnimation2 : gLAnimationSet.getAnimations()) {
                    a(gLAnimation2);
                    gLAnimation2.setDuration(gLAnimationSet.getDuration());
                }
            } else {
                a(gLAnimation);
            }
            this.o = false;
            this.n.start();
        }
        return false;
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public boolean remove() {
        IAMapDelegate iAMapDelegate = this.j;
        if (iAMapDelegate == null) {
            return true;
        }
        iAMapDelegate.removeGLModel(this.f445f);
        return true;
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public void setVisible(boolean z) {
        this.p = z;
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public boolean isVisible() {
        return this.p;
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public void setObject(Object obj) {
        this.t = obj;
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public Object getObject() {
        return this.t;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IglModel
    public void setZoomLimit(float f2) {
        this.u = f2;
        this.v = this.j.getUnitLengthByZoom((int) this.u);
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public void destroy() {
        Bitmap bitmap = this.q;
        if (bitmap != null) {
            er.b(bitmap);
        }
        o oVar = this.y;
        if (oVar != null) {
            oVar.a(this.z);
        }
        q qVar = this.f447h;
        if (qVar != null) {
            qVar.c();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public void setGeoPoint(IPoint iPoint) {
        if (iPoint != null) {
            this.k = iPoint.x;
            this.l = iPoint.y;
            DPoint dPointObtain = DPoint.obtain();
            GLMapState.geo2LonLat(this.k, this.l, dPointObtain);
            this.m = new LatLng(dPointObtain.y, dPointObtain.x, false);
            dPointObtain.recycle();
        }
        this.j.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IglModel
    public void showInfoWindow() {
        try {
            this.j.showInfoWindow(this);
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
    }

    private void a(GLAnimation gLAnimation) {
        if (gLAnimation instanceof GLTranslateAnimation) {
            GLTranslateAnimation gLTranslateAnimation = (GLTranslateAnimation) gLAnimation;
            gLTranslateAnimation.mFromXDelta = this.k;
            gLTranslateAnimation.mFromYDelta = this.l;
            IPoint iPointObtain = IPoint.obtain();
            GLMapState.lonlat2Geo(gLTranslateAnimation.mToXDelta, gLTranslateAnimation.mToYDelta, iPointObtain);
            gLTranslateAnimation.mToXDelta = iPointObtain.x;
            gLTranslateAnimation.mToYDelta = iPointObtain.y;
            iPointObtain.recycle();
        }
    }

    private void d() {
        GLAnimation gLAnimation;
        if (!this.o && (gLAnimation = this.n) != null && !gLAnimation.hasEnded()) {
            e();
            GLTransformation gLTransformation = new GLTransformation();
            this.n.getTransformation(AnimationUtils.currentAnimationTimeMillis(), gLTransformation);
            if (Double.isNaN(gLTransformation.x) || Double.isNaN(gLTransformation.y)) {
                return;
            }
            double d2 = gLTransformation.x;
            double d3 = gLTransformation.y;
            this.k = (int) d2;
            this.l = (int) d3;
            return;
        }
        this.o = true;
    }

    private void e() {
        IAMapDelegate iAMapDelegate = this.j;
        if (iAMapDelegate != null) {
            iAMapDelegate.setRunLowFrame(false);
        }
    }

    public void a(String str) {
        this.f445f = str;
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public boolean isContains() {
        return this.y.a(this);
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public FPoint getGeoPosition() {
        return FPoint.obtain(this.k, this.l);
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public IPoint getScreenPosition() {
        return IPoint.obtain(0, 0);
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public BitmapDescriptor getBitmapDescriptor() {
        return this.i;
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public void setInfoWindowShown(boolean z) {
        this.A = z;
        this.B = true;
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public int getWidth() {
        return (int) ((this.f447h.b() * this.f443d) / this.j.getMapConfig().getMapPerPixelUnitLength());
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public int getHeight() {
        return (int) ((this.f447h.a() * this.f443d) / this.j.getMapConfig().getMapPerPixelUnitLength());
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public int getRealInfoWindowOffsetX() {
        return this.D;
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public int getRealInfoWindowOffsetY() {
        return this.E;
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public String getId() {
        return this.f445f;
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public boolean checkInBounds() {
        return this.j.getMapConfig().getGeoRectangle().contains(this.k, this.l);
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public void setTitle(String str) {
        this.I = str;
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public void setSnippet(String str) {
        this.H = str;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IglModel
    public void setModelFixedLength(int i) {
        if (i > 0) {
            this.w = i;
            this.x = true;
        } else {
            this.w = 0.0f;
            this.x = false;
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public String getTitle() {
        return this.I;
    }

    @Override // com.autonavi.base.amap.api.mapcore.BaseOverlayImp, com.autonavi.amap.mapcore.interfaces.IglModel
    public String getSnippet() {
        return this.H;
    }

    @Override // com.autonavi.base.amap.api.mapcore.InfoWindowCalculate
    public Rect getRect() {
        try {
            GLMapState mapProjection = this.j.getMapProjection();
            int width = getWidth();
            int height = getHeight();
            FPoint fPointObtain = FPoint.obtain();
            mapProjection.p20ToScreenPoint(this.k, this.l, fPointObtain);
            Matrix.setIdentityM(this.f440a, 0);
            Matrix.rotateM(this.f440a, 0, -this.s, 0.0f, 0.0f, 1.0f);
            Matrix.rotateM(this.f440a, 0, this.j.getMapConfig().getSC(), 1.0f, 0.0f, 0.0f);
            Matrix.rotateM(this.f440a, 0, this.j.getMapConfig().getSR(), 0.0f, 0.0f, 1.0f);
            float[] fArr = new float[4];
            float f2 = -width;
            this.f441b[0] = this.F * f2;
            float f3 = height;
            this.f441b[1] = this.G * f3;
            this.f441b[2] = 0.0f;
            this.f441b[3] = 1.0f;
            Matrix.multiplyMV(fArr, 0, this.f440a, 0, this.f441b, 0);
            this.f442c.set((int) (fPointObtain.x + fArr[0]), (int) (fPointObtain.y - fArr[1]), (int) (fPointObtain.x + fArr[0]), (int) (fPointObtain.y - fArr[1]));
            float f4 = width;
            this.f441b[0] = (1.0f - this.F) * f4;
            this.f441b[1] = f3 * this.G;
            this.f441b[2] = 0.0f;
            this.f441b[3] = 1.0f;
            Matrix.multiplyMV(fArr, 0, this.f440a, 0, this.f441b, 0);
            this.f442c.union((int) (fPointObtain.x + fArr[0]), (int) (fPointObtain.y - fArr[1]));
            this.f441b[0] = f4 * (1.0f - this.F);
            float f5 = -height;
            this.f441b[1] = (1.0f - this.G) * f5;
            this.f441b[2] = 0.0f;
            this.f441b[3] = 1.0f;
            Matrix.multiplyMV(fArr, 0, this.f440a, 0, this.f441b, 0);
            this.f442c.union((int) (fPointObtain.x + fArr[0]), (int) (fPointObtain.y - fArr[1]));
            this.f441b[0] = f2 * this.F;
            this.f441b[1] = f5 * (1.0f - this.G);
            this.f441b[2] = 0.0f;
            this.f441b[3] = 1.0f;
            Matrix.multiplyMV(fArr, 0, this.f440a, 0, this.f441b, 0);
            this.f442c.union((int) (fPointObtain.x + fArr[0]), (int) (fPointObtain.y - fArr[1]));
            this.D = this.f442c.centerX() - ((int) fPointObtain.x);
            this.E = this.f442c.top - ((int) fPointObtain.y);
            fPointObtain.recycle();
            return this.f442c;
        } catch (Throwable th) {
            hn.c(th, "MarkerDelegateImp", "getRect");
            th.printStackTrace();
            return new Rect(0, 0, 0, 0);
        }
    }
}