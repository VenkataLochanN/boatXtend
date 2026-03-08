package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLDebugHelper;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;
import com.amap.api.maps.MapsInitializer;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: compiled from: GLTextureView.java */
/* JADX INFO: loaded from: classes.dex */
public class n extends TextureView implements TextureView.SurfaceTextureListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final j f1688a = new j();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final WeakReference<n> f1689b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private i f1690c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private GLSurfaceView.Renderer f1691d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f1692e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private e f1693f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private f f1694g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private g f1695h;
    private k i;
    private int j;
    private int k;
    private boolean l;

    /* JADX INFO: compiled from: GLTextureView.java */
    public interface e {
        EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay);
    }

    /* JADX INFO: compiled from: GLTextureView.java */
    public interface f {
        EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig);

        void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext);
    }

    /* JADX INFO: compiled from: GLTextureView.java */
    public interface g {
        EGLSurface a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj);

        void a(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface);
    }

    /* JADX INFO: compiled from: GLTextureView.java */
    public interface k {
        GL a(GL gl);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public n(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1689b = new WeakReference<>(this);
        a();
    }

    protected void finalize() throws Throwable {
        try {
            if (this.f1690c != null) {
                this.f1690c.h();
            }
        } finally {
            super.finalize();
        }
    }

    private void a() {
        setSurfaceTextureListener(this);
    }

    public void setRenderer(GLSurfaceView.Renderer renderer) {
        e();
        if (this.f1693f == null) {
            this.f1693f = new m(true);
        }
        if (this.f1694g == null) {
            this.f1694g = new c();
        }
        if (this.f1695h == null) {
            this.f1695h = new d();
        }
        this.f1691d = renderer;
        this.f1690c = new i(this.f1689b);
        this.f1690c.start();
    }

    public void a(f fVar) {
        e();
        this.f1694g = fVar;
    }

    public void a(e eVar) {
        e();
        this.f1693f = eVar;
    }

    public void setRenderMode(int i2) {
        this.f1690c.a(i2);
    }

    public void requestRender() {
        this.f1690c.c();
    }

    public int getRenderMode() {
        return this.f1690c.b();
    }

    public void b() {
        this.f1690c.f();
    }

    public void c() {
        this.f1690c.g();
    }

    public void queueEvent(Runnable runnable) {
        this.f1690c.a(runnable);
    }

    @Override // android.view.TextureView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f1692e && this.f1691d != null) {
            i iVar = this.f1690c;
            int iB = iVar != null ? iVar.b() : 1;
            this.f1690c = new i(this.f1689b);
            if (iB != 1) {
                this.f1690c.a(iB);
            }
            this.f1690c.start();
        }
        this.f1692e = false;
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        i iVar = this.f1690c;
        if (iVar != null) {
            iVar.h();
        }
        this.f1692e = true;
        super.onDetachedFromWindow();
    }

    /* JADX INFO: compiled from: GLTextureView.java */
    private class c implements f {
        private c() {
        }

        @Override // com.amap.api.mapcore.util.n.f
        public EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            int[] iArr = {12440, n.this.k, 12344};
            EGLContext eGLContext = EGL10.EGL_NO_CONTEXT;
            if (n.this.k == 0) {
                iArr = null;
            }
            return egl10.eglCreateContext(eGLDisplay, eGLConfig, eGLContext, iArr);
        }

        @Override // com.amap.api.mapcore.util.n.f
        public void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
            if (egl10.eglDestroyContext(eGLDisplay, eGLContext)) {
                return;
            }
            Log.e("DefaultContextFactory", "display:" + eGLDisplay + " context: " + eGLContext);
            h.a("eglDestroyContex", egl10.eglGetError());
        }
    }

    /* JADX INFO: compiled from: GLTextureView.java */
    private static class d implements g {
        private d() {
        }

        @Override // com.amap.api.mapcore.util.n.g
        public EGLSurface a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj) {
            try {
                return egl10.eglCreateWindowSurface(eGLDisplay, eGLConfig, obj, null);
            } catch (IllegalArgumentException e2) {
                Log.e("GLSurfaceView", "eglCreateWindowSurface", e2);
                return null;
            }
        }

        @Override // com.amap.api.mapcore.util.n.g
        public void a(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface) {
            egl10.eglDestroySurface(eGLDisplay, eGLSurface);
        }
    }

    /* JADX INFO: compiled from: GLTextureView.java */
    private abstract class a implements e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected int[] f1696a;

        abstract EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr);

        public a(int[] iArr) {
            this.f1696a = a(iArr);
        }

        @Override // com.amap.api.mapcore.util.n.e
        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
            int[] iArr = new int[1];
            if (!egl10.eglChooseConfig(eGLDisplay, this.f1696a, null, 0, iArr)) {
                throw new IllegalArgumentException("eglChooseConfig failed");
            }
            int i = iArr[0];
            if (i <= 0) {
                throw new IllegalArgumentException("No configs match configSpec");
            }
            EGLConfig[] eGLConfigArr = new EGLConfig[i];
            if (!egl10.eglChooseConfig(eGLDisplay, this.f1696a, eGLConfigArr, i, iArr)) {
                throw new IllegalArgumentException("eglChooseConfig#2 failed");
            }
            EGLConfig eGLConfigA = a(egl10, eGLDisplay, eGLConfigArr);
            if (eGLConfigA != null) {
                return eGLConfigA;
            }
            throw new IllegalArgumentException("No config chosen");
        }

        private int[] a(int[] iArr) {
            if (n.this.k != 2 && n.this.k != 3) {
                return iArr;
            }
            int length = iArr.length;
            int[] iArr2 = new int[length + 2];
            int i = length - 1;
            System.arraycopy(iArr, 0, iArr2, 0, i);
            iArr2[i] = 12352;
            if (n.this.k == 2) {
                iArr2[length] = 4;
            } else {
                iArr2[length] = 64;
            }
            iArr2[length + 1] = 12344;
            return iArr2;
        }
    }

    /* JADX INFO: compiled from: GLTextureView.java */
    private class b extends a {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        protected int f1698c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        protected int f1699d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        protected int f1700e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        protected int f1701f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        protected int f1702g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        protected int f1703h;
        private int[] j;

        public b(int i, int i2, int i3, int i4, int i5, int i6) {
            super(new int[]{12324, i, 12323, i2, 12322, i3, 12321, i4, 12325, i5, 12326, i6, 12344});
            this.j = new int[1];
            this.f1698c = i;
            this.f1699d = i2;
            this.f1700e = i3;
            this.f1701f = i4;
            this.f1702g = i5;
            this.f1703h = i6;
        }

        @Override // com.amap.api.mapcore.util.n.a
        public EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
            for (EGLConfig eGLConfig : eGLConfigArr) {
                int iA = a(egl10, eGLDisplay, eGLConfig, 12325, 0);
                int iA2 = a(egl10, eGLDisplay, eGLConfig, 12326, 0);
                if (iA >= this.f1702g && iA2 >= this.f1703h) {
                    int iA3 = a(egl10, eGLDisplay, eGLConfig, 12324, 0);
                    int iA4 = a(egl10, eGLDisplay, eGLConfig, 12323, 0);
                    int iA5 = a(egl10, eGLDisplay, eGLConfig, 12322, 0);
                    int iA6 = a(egl10, eGLDisplay, eGLConfig, 12321, 0);
                    if (iA3 == this.f1698c && iA4 == this.f1699d && iA5 == this.f1700e && iA6 == this.f1701f) {
                        return eGLConfig;
                    }
                }
            }
            return null;
        }

        private int a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int i2) {
            return egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, this.j) ? this.j[0] : i2;
        }
    }

    /* JADX INFO: compiled from: GLTextureView.java */
    private class m extends b {
        public m(boolean z) {
            super(8, 8, 8, 0, z ? 16 : 0, 0);
        }
    }

    /* JADX INFO: compiled from: GLTextureView.java */
    private static class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        EGL10 f1705a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        EGLDisplay f1706b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        EGLSurface f1707c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        EGLConfig f1708d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        EGLContext f1709e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private WeakReference<n> f1710f;

        public h(WeakReference<n> weakReference) {
            this.f1710f = weakReference;
        }

        public void a() {
            this.f1705a = (EGL10) EGLContext.getEGL();
            this.f1706b = this.f1705a.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            if (this.f1706b == EGL10.EGL_NO_DISPLAY) {
                throw new RuntimeException("eglGetDisplay failed");
            }
            if (!this.f1705a.eglInitialize(this.f1706b, new int[2])) {
                throw new RuntimeException("eglInitialize failed");
            }
            n nVar = this.f1710f.get();
            if (nVar != null) {
                this.f1708d = nVar.f1693f.chooseConfig(this.f1705a, this.f1706b);
                this.f1709e = nVar.f1694g.createContext(this.f1705a, this.f1706b, this.f1708d);
            } else {
                this.f1708d = null;
                this.f1709e = null;
            }
            EGLContext eGLContext = this.f1709e;
            if (eGLContext == null || eGLContext == EGL10.EGL_NO_CONTEXT) {
                this.f1709e = null;
                a("createContext");
            }
            this.f1707c = null;
        }

        public boolean b() {
            if (this.f1705a == null) {
                throw new RuntimeException("egl not initialized");
            }
            if (this.f1706b == null) {
                throw new RuntimeException("eglDisplay not initialized");
            }
            if (this.f1708d == null) {
                throw new RuntimeException("mEglConfig not initialized");
            }
            g();
            n nVar = this.f1710f.get();
            if (nVar != null) {
                this.f1707c = nVar.f1695h.a(this.f1705a, this.f1706b, this.f1708d, nVar.getSurfaceTexture());
            } else {
                this.f1707c = null;
            }
            EGLSurface eGLSurface = this.f1707c;
            if (eGLSurface == null || eGLSurface == EGL10.EGL_NO_SURFACE) {
                if (this.f1705a.eglGetError() == 12299) {
                    Log.e("EglHelper", "createWindowSurface returned EGL_BAD_NATIVE_WINDOW.");
                }
                return false;
            }
            EGL10 egl10 = this.f1705a;
            EGLDisplay eGLDisplay = this.f1706b;
            EGLSurface eGLSurface2 = this.f1707c;
            if (egl10.eglMakeCurrent(eGLDisplay, eGLSurface2, eGLSurface2, this.f1709e)) {
                return true;
            }
            a("EGLHelper", "eglMakeCurrent", this.f1705a.eglGetError());
            return false;
        }

        GL c() {
            GL gl = this.f1709e.getGL();
            n nVar = this.f1710f.get();
            if (nVar == null) {
                return gl;
            }
            if (nVar.i != null) {
                gl = nVar.i.a(gl);
            }
            if ((nVar.j & 3) != 0) {
                return GLDebugHelper.wrap(gl, (nVar.j & 1) != 0 ? 1 : 0, (nVar.j & 2) != 0 ? new l() : null);
            }
            return gl;
        }

        public int d() {
            if (this.f1705a.eglSwapBuffers(this.f1706b, this.f1707c)) {
                return 12288;
            }
            return this.f1705a.eglGetError();
        }

        public void e() {
            g();
        }

        private void g() {
            EGLSurface eGLSurface = this.f1707c;
            if (eGLSurface == null || eGLSurface == EGL10.EGL_NO_SURFACE) {
                return;
            }
            this.f1705a.eglMakeCurrent(this.f1706b, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
            n nVar = this.f1710f.get();
            if (nVar != null) {
                nVar.f1695h.a(this.f1705a, this.f1706b, this.f1707c);
            }
            this.f1707c = null;
        }

        public void f() {
            if (this.f1709e != null) {
                n nVar = this.f1710f.get();
                if (nVar != null) {
                    nVar.f1694g.destroyContext(this.f1705a, this.f1706b, this.f1709e);
                }
                this.f1709e = null;
            }
            EGLDisplay eGLDisplay = this.f1706b;
            if (eGLDisplay != null) {
                this.f1705a.eglTerminate(eGLDisplay);
                this.f1706b = null;
            }
        }

        private void a(String str) {
            a(str, this.f1705a.eglGetError());
        }

        public static void a(String str, int i) {
            throw new RuntimeException(b(str, i));
        }

        public static void a(String str, String str2, int i) {
            Log.w(str, b(str2, i));
        }

        public static String b(String str, int i) {
            return str + " failed: " + i;
        }
    }

    /* JADX INFO: compiled from: GLTextureView.java */
    static class i extends Thread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f1711a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f1712b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f1713c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f1714d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f1715e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private boolean f1716f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private boolean f1717g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private boolean f1718h;
        private boolean i;
        private boolean j;
        private boolean k;
        private boolean p;
        private h s;
        private WeakReference<n> t;
        private ArrayList<Runnable> q = new ArrayList<>();
        private boolean r = true;
        private int l = 0;
        private int m = 0;
        private boolean o = true;
        private int n = 1;

        i(WeakReference<n> weakReference) {
            this.t = weakReference;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            setName("GLThread " + getId());
            try {
                n();
            } catch (InterruptedException unused) {
            } catch (Throwable th) {
                n.f1688a.a(this);
                throw th;
            }
            n.f1688a.a(this);
        }

        private void l() {
            if (this.i) {
                this.i = false;
                this.s.e();
            }
        }

        private void m() {
            if (this.f1718h) {
                this.s.f();
                this.f1718h = false;
                n.f1688a.c(this);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:162:0x0229 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void n() throws java.lang.InterruptedException {
            /*
                Method dump skipped, instruction units count: 564
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.n.i.n():void");
        }

        public boolean a() {
            return this.f1718h && this.i && o();
        }

        private boolean o() {
            return !this.f1714d && this.f1715e && !this.f1716f && this.l > 0 && this.m > 0 && (this.o || this.n == 1);
        }

        public void a(int i) {
            if (i >= 0 && i <= 1) {
                synchronized (n.f1688a) {
                    this.n = i;
                    n.f1688a.notifyAll();
                }
                return;
            }
            throw new IllegalArgumentException("renderMode");
        }

        public int b() {
            int i;
            synchronized (n.f1688a) {
                i = this.n;
            }
            return i;
        }

        public void c() {
            synchronized (n.f1688a) {
                this.o = true;
                n.f1688a.notifyAll();
            }
        }

        public void d() {
            synchronized (n.f1688a) {
                this.f1715e = true;
                this.j = false;
                n.f1688a.notifyAll();
                while (this.f1717g && !this.j && !this.f1712b) {
                    try {
                        n.f1688a.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void e() {
            synchronized (n.f1688a) {
                this.f1715e = false;
                n.f1688a.notifyAll();
                while (!this.f1717g && !this.f1712b) {
                    try {
                        if (MapsInitializer.getTextureViewDestorySync()) {
                            n.f1688a.wait();
                        } else {
                            n.f1688a.wait(2000L);
                        }
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void f() {
            synchronized (n.f1688a) {
                this.f1713c = true;
                n.f1688a.notifyAll();
                while (!this.f1712b && !this.f1714d) {
                    try {
                        if (MapsInitializer.getTextureViewDestorySync()) {
                            n.f1688a.wait();
                        } else {
                            n.f1688a.wait(2000L);
                        }
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void g() {
            synchronized (n.f1688a) {
                this.f1713c = false;
                this.o = true;
                this.p = false;
                n.f1688a.notifyAll();
                while (!this.f1712b && this.f1714d && !this.p) {
                    try {
                        n.f1688a.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void a(int i, int i2) {
            synchronized (n.f1688a) {
                this.l = i;
                this.m = i2;
                this.r = true;
                this.o = true;
                this.p = false;
                n.f1688a.notifyAll();
                while (!this.f1712b && !this.f1714d && !this.p && a()) {
                    try {
                        n.f1688a.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void h() {
            synchronized (n.f1688a) {
                this.f1711a = true;
                n.f1688a.notifyAll();
                while (!this.f1712b) {
                    try {
                        n.f1688a.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void i() {
            this.k = true;
            n.f1688a.notifyAll();
        }

        public void a(Runnable runnable) {
            if (runnable != null) {
                synchronized (n.f1688a) {
                    this.q.add(runnable);
                    n.f1688a.notifyAll();
                }
                return;
            }
            throw new IllegalArgumentException("r must not be null");
        }

        public int j() {
            int i;
            synchronized (n.f1688a) {
                i = this.l;
            }
            return i;
        }

        public int k() {
            int i;
            synchronized (n.f1688a) {
                i = this.m;
            }
            return i;
        }
    }

    /* JADX INFO: compiled from: GLTextureView.java */
    static class l extends Writer {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private StringBuilder f1726a = new StringBuilder();

        l() {
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            a();
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
            a();
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i, int i2) {
            for (int i3 = 0; i3 < i2; i3++) {
                char c2 = cArr[i + i3];
                if (c2 == '\n') {
                    a();
                } else {
                    this.f1726a.append(c2);
                }
            }
        }

        private void a() {
            if (this.f1726a.length() > 0) {
                Log.v("GLSurfaceView", this.f1726a.toString());
                StringBuilder sb = this.f1726a;
                sb.delete(0, sb.length());
            }
        }
    }

    private void e() {
        if (this.f1690c != null) {
            throw new IllegalStateException("setRenderer has already been called for this instance.");
        }
    }

    /* JADX INFO: compiled from: GLTextureView.java */
    private static class j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static String f1719a = "GLThreadManager";

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f1720b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f1721c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f1722d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f1723e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private boolean f1724f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private i f1725g;

        private j() {
        }

        public synchronized void a(i iVar) {
            iVar.f1712b = true;
            if (this.f1725g == iVar) {
                this.f1725g = null;
            }
            notifyAll();
        }

        public boolean b(i iVar) {
            i iVar2 = this.f1725g;
            if (iVar2 == iVar || iVar2 == null) {
                this.f1725g = iVar;
                notifyAll();
                return true;
            }
            c();
            if (this.f1723e) {
                return true;
            }
            i iVar3 = this.f1725g;
            if (iVar3 == null) {
                return false;
            }
            iVar3.i();
            return false;
        }

        public void c(i iVar) {
            if (this.f1725g == iVar) {
                this.f1725g = null;
            }
            notifyAll();
        }

        public synchronized boolean a() {
            return this.f1724f;
        }

        public synchronized boolean b() {
            c();
            return !this.f1723e;
        }

        public synchronized void a(GL10 gl10) {
            if (!this.f1722d && gl10 != null) {
                c();
                String strGlGetString = gl10.glGetString(7937);
                if (this.f1721c < 131072) {
                    this.f1723e = !strGlGetString.startsWith("Q3Dimension MSM7500 ");
                    notifyAll();
                }
                this.f1724f = this.f1723e ? false : true;
                this.f1722d = true;
            }
        }

        private void c() {
            if (this.f1720b) {
                return;
            }
            this.f1721c = 131072;
            if (this.f1721c >= 131072) {
                this.f1723e = true;
            }
            this.f1720b = true;
        }
    }

    private boolean f() {
        return Build.VERSION.SDK_INT < 23;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i3) {
        this.f1690c.d();
        if (f() || MapsInitializer.getTextureSizeChangedInvoked()) {
            onSurfaceTextureSizeChanged(surfaceTexture, i2, i3);
        } else {
            if (this.f1690c.j() == i2 && this.f1690c.k() == i3) {
                return;
            }
            onSurfaceTextureSizeChanged(surfaceTexture, i2, i3);
        }
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.f1690c.e();
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i2, int i3) {
        this.f1690c.a(i2, i3);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        onSurfaceTextureSizeChanged(getSurfaceTexture(), i4 - i2, i5 - i3);
        super.onLayout(z, i2, i3, i4, i5);
    }
}