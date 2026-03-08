package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.amap.api.maps.model.BasePointOverlay;
import com.amap.api.maps.model.GL3DModel;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.animation.Animation;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.animation.GLAnimation;
import com.autonavi.amap.mapcore.animation.GLTransformation;
import com.autonavi.amap.mapcore.interfaces.IInfoWindowManager;
import com.autonavi.amap.mapcore.interfaces.IMarker;
import com.autonavi.amap.mapcore.interfaces.IMarkerAction;
import com.autonavi.amap.mapcore.interfaces.IOverlay;
import com.autonavi.base.ae.gmap.GLMapState;
import com.autonavi.base.amap.api.mapcore.BaseOverlayImp;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.api.mapcore.infowindow.IInfoWindowAction;
import com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate;
import com.autonavi.base.amap.mapcore.FPoint;
import com.autonavi.base.amap.mapcore.MapConfig;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: compiled from: PopupOverlay.java */
/* JADX INFO: loaded from: classes.dex */
public class da implements IInfoWindowManager, IInfoWindowAction, IOverlayDelegate {
    private GLAnimation H;
    private GLAnimation I;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    IAMapDelegate f517a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    a f519c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    ar f522f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Context f523g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private BaseOverlayImp f524h;
    private FPoint n;
    private FloatBuffer r;
    private boolean u;
    private Bitmap v;
    private Bitmap w;
    private int z;
    private boolean i = false;
    private int j = 0;
    private int k = 0;
    private int l = 0;
    private int m = 0;
    private FloatBuffer o = null;
    private boolean q = true;
    private float s = 0.5f;
    private float t = 1.0f;
    private Rect x = new Rect();
    private float y = 0.0f;
    private boolean A = true;
    private Bitmap B = null;
    private Bitmap C = null;
    private Bitmap D = null;
    private Bitmap E = null;
    private boolean F = false;
    private boolean G = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    float[] f518b = new float[12];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    float[] f520d = {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    long f521e = 0;
    private boolean J = false;
    private boolean K = true;
    private String p = getId();

    private void o() {
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public boolean calMapFPoint() throws RemoteException {
        return false;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public boolean checkInBounds() {
        return true;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public void draw(MapConfig mapConfig) throws RemoteException {
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public float getZIndex() {
        return 0.0f;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isAboveMaskLayer() {
        return false;
    }

    @Override // com.autonavi.base.amap.api.mapcore.overlays.IOverlayDelegate
    public boolean isDrawFinish() {
        return false;
    }

    @Override // com.autonavi.base.amap.api.mapcore.infowindow.IInfoWindowAction
    public boolean isInfoWindowShown() {
        return false;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void remove() throws RemoteException {
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setAboveMaskLayer(boolean z) {
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IInfoWindowManager
    public void setInfoWindowAnimation(Animation animation, Animation.AnimationListener animationListener) {
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IInfoWindowManager
    public void setInfoWindowBackColor(int i) {
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IInfoWindowManager
    public void setInfoWindowBackEnable(boolean z) {
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IInfoWindowManager
    public void setInfoWindowBackScale(float f2, float f3) {
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IInfoWindowManager
    public void setInfoWindowMovingAnimation(Animation animation) {
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setZIndex(float f2) {
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IInfoWindowManager
    public void startAnimation() {
    }

    public boolean a() {
        return this.A;
    }

    public void a(boolean z) {
        this.A = z;
    }

    public synchronized void a(Bitmap bitmap) {
        if (bitmap != null) {
            if (!bitmap.isRecycled()) {
                this.B = bitmap;
            }
        }
    }

    private synchronized void c(Bitmap bitmap) {
        if (bitmap != null) {
            if (!bitmap.isRecycled()) {
                er.b(bitmap);
            }
        }
    }

    private synchronized void d(Bitmap bitmap) {
        if (bitmap != null) {
            if (!bitmap.isRecycled()) {
                c(this.C);
                this.C = bitmap;
            }
        }
    }

    private synchronized void e(Bitmap bitmap) {
        if (bitmap != null) {
            if (!bitmap.isRecycled()) {
                c(this.D);
                this.D = bitmap;
            }
        }
    }

    private synchronized void f(Bitmap bitmap) {
        if (bitmap != null) {
            if (!bitmap.isRecycled()) {
                c(this.E);
                this.E = bitmap;
            }
        }
    }

    private synchronized Bitmap h() {
        return this.B;
    }

    private synchronized Bitmap i() {
        return this.D;
    }

    public da(IAMapDelegate iAMapDelegate, Context context) {
        this.f517a = null;
        this.f523g = context;
        this.f517a = iAMapDelegate;
    }

    public int b() {
        try {
            synchronized (this) {
                if (this.v == null || this.v.isRecycled()) {
                    return 0;
                }
                return this.v.getWidth();
            }
        } catch (Throwable unused) {
            return 0;
        }
    }

    public int c() {
        try {
            if (this.v == null || this.v.isRecycled()) {
                return 0;
            }
            return this.v.getHeight();
        } catch (Throwable unused) {
            return 0;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public String getId() {
        if (this.p == null) {
            this.p = "PopupOverlay";
        }
        return this.p;
    }

    public synchronized void b(Bitmap bitmap) {
        if (bitmap != null) {
            try {
                if (!bitmap.isRecycled()) {
                    if (this.v != null && this.v.hashCode() == bitmap.hashCode()) {
                        return;
                    }
                    if (this.v != null && ((this.B == null && this.C == null && this.D == null && this.E == null) || !g(this.v))) {
                        c(this.w);
                        this.w = this.v;
                    }
                    this.G = false;
                    this.v = bitmap;
                }
            } catch (Throwable unused) {
            }
        }
    }

    private boolean g(Bitmap bitmap) {
        if (this.B != null && bitmap.hashCode() == this.B.hashCode()) {
            return true;
        }
        if (this.D != null && bitmap.hashCode() == this.D.hashCode()) {
            return true;
        }
        if (this.C == null || bitmap.hashCode() != this.C.hashCode()) {
            return this.E != null && bitmap.hashCode() == this.E.hashCode();
        }
        return true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setVisible(boolean z) {
        if (!this.q && z) {
            this.u = true;
        }
        this.q = z;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isVisible() {
        return this.q;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean equalsRemote(IOverlay iOverlay) throws RemoteException {
        return equals(iOverlay) || iOverlay.getId().equals(getId());
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public int hashCodeRemote() {
        return super.hashCode();
    }

    public boolean a(int i, int i2) {
        Bitmap bitmap;
        GLMapState mapProjection = this.f517a.getMapProjection();
        if (this.n != null && mapProjection != null) {
            IPoint iPointObtain = IPoint.obtain();
            if (this.f517a.getMapConfig() != null && mapProjection != null) {
                FPoint fPointObtain = FPoint.obtain();
                mapProjection.p20ToScreenPoint((int) this.n.x, (int) this.n.y, fPointObtain);
                iPointObtain.x = (int) fPointObtain.x;
                iPointObtain.y = (int) fPointObtain.y;
                fPointObtain.recycle();
            }
            int iB = b();
            int iC = c();
            int i3 = (int) ((iPointObtain.x + this.j) - (iB * this.s));
            int i4 = (int) (iPointObtain.y + this.k + (iC * (1.0f - this.t)));
            iPointObtain.recycle();
            if (i3 - iB > i || i3 < (-iB) * 2 || i4 < (-iC) * 2 || i4 - iC > i2 || (bitmap = this.v) == null) {
                return false;
            }
            int width = bitmap.getWidth();
            int height = this.v.getHeight();
            if (this.r == null) {
                this.r = er.a(new float[]{0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f});
            }
            int i5 = (int) (((double) (1.0f - this.y)) * 0.5d * ((double) width));
            float[] fArr = this.f518b;
            int i6 = i3 + i5;
            float f2 = i6;
            fArr[0] = f2;
            Rect rect = this.x;
            rect.left = i6;
            int i7 = i2 - i4;
            float f3 = i7;
            fArr[1] = f3;
            fArr[2] = 0.0f;
            int i8 = i3 + width;
            float f4 = i8 - i5;
            fArr[3] = f4;
            fArr[4] = f3;
            rect.top = i4 - height;
            fArr[5] = 0.0f;
            fArr[6] = f4;
            rect.right = i8;
            float f5 = i7 + height;
            fArr[7] = f5;
            rect.bottom = i4;
            fArr[8] = 0.0f;
            fArr[9] = f2;
            fArr[10] = f5;
            fArr[11] = 0.0f;
            FloatBuffer floatBuffer = this.o;
            if (floatBuffer == null) {
                this.o = er.a(fArr);
            } else {
                this.o = er.a(fArr, floatBuffer);
            }
            return true;
        }
        return false;
    }

    public void d() {
        this.f519c = new a("texture.glsl");
    }

    /* JADX INFO: compiled from: PopupOverlay.java */
    static class a extends dd {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f527a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f528b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f529c;

        a(String str) {
            if (a(str)) {
                this.f527a = c("aMVP");
                this.f528b = b("aVertex");
                this.f529c = b("aTextureCoord");
            }
        }
    }

    private void a(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (floatBuffer == null || floatBuffer2 == null || i == 0) {
            return;
        }
        if (this.f519c == null) {
            d();
        }
        this.f519c.a();
        GLES20.glEnable(3042);
        GLES20.glBlendFunc(1, 771);
        GLES20.glBlendColor(1.0f, 1.0f, 1.0f, 1.0f);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, i);
        GLES20.glEnableVertexAttribArray(this.f519c.f528b);
        GLES20.glVertexAttribPointer(this.f519c.f528b, 3, 5126, false, 12, (Buffer) floatBuffer);
        GLES20.glEnableVertexAttribArray(this.f519c.f529c);
        GLES20.glVertexAttribPointer(this.f519c.f529c, 2, 5126, false, 8, (Buffer) floatBuffer2);
        GLES20.glUniformMatrix4fv(this.f519c.f527a, 1, false, this.f520d, 0);
        GLES20.glDrawArrays(6, 0, 4);
        GLES20.glDisableVertexAttribArray(this.f519c.f528b);
        GLES20.glDisableVertexAttribArray(this.f519c.f529c);
        GLES20.glBindTexture(3553, 0);
        GLES20.glUseProgram(0);
        GLES20.glDisable(3042);
    }

    public void b(int i, int i2) {
        if (!this.q || this.n == null || this.v == null) {
            return;
        }
        e();
        this.v.isRecycled();
        if (!this.G && !this.v.isRecycled()) {
            try {
                if (this.z != 0) {
                    GLES20.glDeleteTextures(1, new int[]{this.z}, 0);
                } else {
                    this.z = k();
                }
                synchronized (this) {
                    if (this.v != null && !this.v.isRecycled()) {
                        er.b(this.z, this.v, false);
                        this.G = true;
                    }
                }
            } catch (Throwable th) {
                hn.c(th, "PopupOverlay", "drawMarker");
                th.printStackTrace();
                return;
            }
        }
        j();
        if (a(i, i2)) {
            Matrix.setIdentityM(this.f520d, 0);
            Matrix.orthoM(this.f520d, 0, 0.0f, i, 0.0f, i2, 1.0f, -1.0f);
            a(this.z, this.o, this.r);
            if (this.u) {
                this.u = false;
                o();
            }
        }
    }

    protected void e() {
        long j;
        long jC;
        synchronized (this) {
            j = 100;
            if (this.f522f != null) {
                if (this.f524h instanceof cu) {
                    jC = this.f522f.c((BasePointOverlay) new Marker((IMarker) this.f524h));
                } else {
                    jC = this.f522f.c(new GL3DModel((cr) this.f524h));
                }
                if (jC <= 0) {
                    j = LongCompanionObject.MAX_VALUE;
                } else if (jC > 100) {
                    j = jC;
                }
            } else {
                j = 0;
            }
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j2 = this.f521e;
        if (jCurrentTimeMillis - j2 > j) {
            if (j2 != 0) {
                try {
                    showInfoWindow(this.f524h);
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
            this.f521e = jCurrentTimeMillis;
        }
    }

    private void j() {
        GLAnimation gLAnimation;
        if (!this.K && (gLAnimation = this.I) != null && !gLAnimation.hasEnded()) {
            this.J = true;
            GLTransformation gLTransformation = new GLTransformation();
            this.I.getTransformation(AnimationUtils.currentAnimationTimeMillis(), gLTransformation);
            if (Double.isNaN(gLTransformation.scaleX) || Double.isNaN(gLTransformation.scaleY)) {
                return;
            }
            this.y = (float) gLTransformation.scaleX;
            return;
        }
        GLAnimation gLAnimation2 = this.H;
        if (gLAnimation2 != null && !gLAnimation2.hasEnded()) {
            this.K = false;
            this.J = true;
            this.j = this.l;
            this.k = this.m;
            GLTransformation gLTransformation2 = new GLTransformation();
            this.H.getTransformation(AnimationUtils.currentAnimationTimeMillis(), gLTransformation2);
            if (Double.isNaN(gLTransformation2.scaleX) || Double.isNaN(gLTransformation2.scaleY)) {
                return;
            }
            this.y = (float) gLTransformation2.scaleX;
            return;
        }
        this.y = 1.0f;
        this.J = false;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IInfoWindowManager
    public void setInfoWindowAppearAnimation(Animation animation) {
        GLAnimation gLAnimation = this.I;
        if (gLAnimation != null && gLAnimation.equals(animation.glAnimation)) {
            try {
                this.H = animation.glAnimation.mo16clone();
                return;
            } catch (Throwable th) {
                hn.c(th, "PopupOverlay", "setInfoWindowDisappearAnimation");
                return;
            }
        }
        this.H = animation.glAnimation;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IInfoWindowManager
    public void setInfoWindowDisappearAnimation(Animation animation) {
        GLAnimation gLAnimation = this.H;
        if (gLAnimation != null && gLAnimation.equals(animation.glAnimation)) {
            try {
                this.I = animation.glAnimation.mo16clone();
                return;
            } catch (Throwable th) {
                hn.c(th, "PopupOverlay", "setInfoWindowDisappearAnimation");
                return;
            }
        }
        this.I = animation.glAnimation;
    }

    private int k() {
        int[] iArr = {0};
        GLES20.glGenTextures(1, iArr, 0);
        return iArr[0];
    }

    public void c(int i, int i2) throws RemoteException {
        if (this.J) {
            this.l = i;
            this.m = i2;
        } else {
            this.j = i;
            this.k = i2;
            this.l = i;
            this.m = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        if (z) {
            b(h());
        } else {
            b(i());
        }
    }

    private void c(final boolean z) {
        GLAnimation gLAnimation = this.I;
        if (gLAnimation != null) {
            this.K = false;
            this.J = true;
            gLAnimation.startNow();
            this.I.setAnimationListener(new Animation.AnimationListener() { // from class: com.amap.api.mapcore.util.da.1
                @Override // com.amap.api.maps.model.animation.Animation.AnimationListener
                public void onAnimationStart() {
                }

                @Override // com.amap.api.maps.model.animation.Animation.AnimationListener
                public void onAnimationEnd() {
                    if (da.this.H != null) {
                        da.this.J = true;
                        da.this.H.startNow();
                        da.this.b(z);
                    }
                }
            });
            return;
        }
        GLAnimation gLAnimation2 = this.H;
        if (gLAnimation2 != null) {
            this.J = true;
            gLAnimation2.startNow();
            b(z);
            return;
        }
        b(z);
    }

    private void l() {
        if (this.A && this.v != null) {
            c(false);
        } else {
            b(i());
        }
        a(false);
    }

    private void m() {
        if (!this.A && this.v != null) {
            c(true);
        } else {
            b(h());
        }
        a(true);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void destroy() {
        if (this.i) {
            try {
                remove();
                n();
                if (this.r != null) {
                    this.r.clear();
                    this.r = null;
                }
                if (this.o != null) {
                    this.o.clear();
                    this.o = null;
                }
                this.n = null;
                this.z = 0;
            } catch (Throwable th) {
                hn.c(th, "PopupOverlay", "realDestroy");
                th.printStackTrace();
            }
        }
    }

    private synchronized void n() {
        Bitmap bitmap;
        if (this.v != null && (bitmap = this.v) != null) {
            er.b(bitmap);
            this.v = null;
        }
        if (this.w != null && !this.w.isRecycled()) {
            er.b(this.w);
            this.w = null;
        }
        if (this.B != null && !this.B.isRecycled()) {
            er.b(this.B);
            this.B = null;
        }
        if (this.C != null && !this.C.isRecycled()) {
            er.b(this.C);
            this.C = null;
        }
        if (this.D != null && !this.D.isRecycled()) {
            er.b(this.D);
            this.D = null;
        }
        if (this.E != null && !this.E.isRecycled()) {
            er.b(this.E);
            this.E = null;
        }
    }

    public void a(FPoint fPoint) {
        this.n = fPoint;
    }

    public boolean f() {
        return this.J;
    }

    @Override // com.autonavi.base.amap.api.mapcore.infowindow.IInfoWindowAction
    public synchronized void showInfoWindow(BaseOverlayImp baseOverlayImp) throws RemoteException {
        if (baseOverlayImp == null) {
            return;
        }
        if (baseOverlayImp.isInfoWindowEnable()) {
            if (this.f524h != null && !this.f524h.getId().equals(baseOverlayImp.getId())) {
                hideInfoWindow();
            }
            if (this.f522f != null) {
                this.f524h = baseOverlayImp;
                baseOverlayImp.setInfoWindowShown(true);
                setVisible(true);
                g();
            }
            this.F = true;
        }
    }

    protected void g() {
        View viewB;
        View viewB2;
        try {
            if (this.f524h instanceof cu) {
                Marker marker = new Marker((IMarker) this.f524h);
                if (this.f522f != null) {
                    Bitmap bitmapA = a(this.f522f.a((BasePointOverlay) marker));
                    if (bitmapA == null && (viewB2 = this.f522f.b((BasePointOverlay) marker)) != null) {
                        if (viewB2.getBackground() == null) {
                            viewB2.setBackground(this.f522f.g());
                        }
                        bitmapA = a(viewB2);
                    }
                    a(bitmapA);
                    d(a(this.f522f.a(marker)));
                    e(a(this.f522f.b(marker)));
                    f(a(this.f522f.c(marker)));
                    return;
                }
                return;
            }
            if (this.f522f != null) {
                GL3DModel gL3DModel = new GL3DModel((cr) this.f524h);
                Bitmap bitmapA2 = a(this.f522f.a(gL3DModel));
                if (bitmapA2 == null && (viewB = this.f522f.b(gL3DModel)) != null) {
                    if (viewB.getBackground() == null) {
                        viewB.setBackground(this.f522f.g());
                    }
                    bitmapA2 = a(viewB);
                }
                a(bitmapA2);
            }
        } catch (Throwable th) {
            hn.c(th, "PopupOverlay", "getInfoWindow");
            th.printStackTrace();
            ey.c(ex.f797d, "image infowindow update failed " + th.getMessage());
        }
    }

    private Bitmap a(View view) {
        Context context;
        if (view == null) {
            return null;
        }
        if ((view instanceof RelativeLayout) && (context = this.f523g) != null) {
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(1);
            linearLayout.addView(view);
            view = linearLayout;
        }
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(0);
        return er.a(view);
    }

    private Rect p() {
        return new Rect(this.x.left, this.x.top, this.x.right, this.x.top + r());
    }

    private Rect q() {
        return new Rect(this.x.left, this.x.top, this.x.right, this.x.top + s());
    }

    private int r() {
        Bitmap bitmap = this.B;
        if (bitmap == null || bitmap.isRecycled()) {
            return 0;
        }
        return this.B.getHeight();
    }

    private int s() {
        Bitmap bitmap = this.D;
        if (bitmap == null || bitmap.isRecycled()) {
            return 0;
        }
        return this.D.getHeight();
    }

    @Override // com.autonavi.base.amap.api.mapcore.infowindow.IInfoWindowAction
    public void redrawInfoWindow() {
        try {
            synchronized (this) {
                if (this.f524h != null && this.f524h.checkInBounds()) {
                    boolean z = true;
                    setVisible(true);
                    Rect rect = this.f524h.getRect();
                    int realInfoWindowOffsetX = this.f524h.getRealInfoWindowOffsetX() + this.f524h.getInfoWindowOffsetX();
                    int realInfoWindowOffsetY = this.f524h.getRealInfoWindowOffsetY() + this.f524h.getInfoWindowOffsetY() + 2;
                    if (this.f524h instanceof cu) {
                        synchronized (this) {
                            if (!f() || (this.v == null && !(this.B == null && this.D == null))) {
                                IMarkerAction iMarkerAction = ((cu) this.f524h).getIMarkerAction();
                                if (iMarkerAction != null && !iMarkerAction.isInfoWindowEnable()) {
                                    setVisible(false);
                                    return;
                                }
                                setVisible(true);
                                if (iMarkerAction != null && iMarkerAction.isInfoWindowAutoOverturn()) {
                                    Rect rectP = p();
                                    Rect rectQ = q();
                                    if (a()) {
                                        rectQ.offset(0, rect.height() + rectP.height() + 2);
                                    } else {
                                        rectP.offset(0, -(rect.height() + rectP.height() + 2));
                                    }
                                    int iCheckMarkerInRect = this.f517a.checkMarkerInRect(iMarkerAction, rectP);
                                    int iCheckMarkerInRect2 = this.f517a.checkMarkerInRect(iMarkerAction, rectQ);
                                    if (iCheckMarkerInRect <= 0 || (iCheckMarkerInRect2 != 0 && (iCheckMarkerInRect2 <= 0 || iCheckMarkerInRect >= iCheckMarkerInRect2))) {
                                        z = false;
                                    }
                                    if (z) {
                                        realInfoWindowOffsetY = this.f524h.getRealInfoWindowOffsetY() + this.f524h.getInfoWindowOffsetY() + 2 + rect.height() + rectQ.height();
                                        l();
                                    } else {
                                        m();
                                    }
                                    a(this.f524h.getGeoPosition());
                                    c(realInfoWindowOffsetX, realInfoWindowOffsetY);
                                } else {
                                    a(this.f524h.getGeoPosition());
                                    c(realInfoWindowOffsetX, realInfoWindowOffsetY);
                                    m();
                                }
                            }
                            return;
                        }
                    }
                    if (f()) {
                        if (this.v != null) {
                            return;
                        }
                        if (this.B == null && this.D == null) {
                            return;
                        }
                    }
                    if (!this.f524h.isInfoWindowEnable()) {
                        setVisible(false);
                        return;
                    }
                    setVisible(true);
                    a(this.f524h.getGeoPosition());
                    c(realInfoWindowOffsetX, realInfoWindowOffsetY);
                    m();
                    return;
                }
                setVisible(false);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.infowindow.IInfoWindowAction
    public boolean onInfoWindowTap(MotionEvent motionEvent) {
        return this.q && this.f524h != null && this.F && er.a(this.x, (int) motionEvent.getX(), (int) motionEvent.getY());
    }

    @Override // com.autonavi.base.amap.api.mapcore.infowindow.IInfoWindowAction
    public synchronized void hideInfoWindow() {
        setVisible(false);
        n();
        this.F = false;
    }

    @Override // com.autonavi.base.amap.api.mapcore.infowindow.IInfoWindowAction
    public void setInfoWindowAdapterManager(ar arVar) {
        synchronized (this) {
            this.f522f = arVar;
        }
    }
}