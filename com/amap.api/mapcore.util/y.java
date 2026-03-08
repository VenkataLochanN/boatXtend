package com.amap.api.mapcore.util;

import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.opengl.Matrix;
import com.amap.api.mapcore.util.de;
import com.amap.api.maps.model.CrossOverlay;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: PboPluginTexture.java */
/* JADX INFO: loaded from: classes.dex */
public class y {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final IAMapDelegate f1827c;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private ExecutorService f1832h;
    private boolean j;
    private volatile EGLContext o;
    private volatile EGLConfig p;
    private de.g t;
    private FloatBuffer u;
    private FloatBuffer v;
    private a w;
    private CrossOverlay.GenerateCrossImageListener x;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f1828d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f1829e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f1830f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private BlockingQueue<Runnable> f1831g = new LinkedBlockingQueue();
    private boolean i = false;
    private int k = 0;
    private int l = 0;
    private int m = 0;
    private boolean n = false;
    private EGLDisplay q = EGL14.EGL_NO_DISPLAY;
    private EGLContext r = EGL14.EGL_NO_CONTEXT;
    private EGLSurface s = EGL14.EGL_NO_SURFACE;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    float[] f1825a = {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    float[] f1826b = {-1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, -1.0f, 0.0f, -1.0f, -1.0f, 0.0f};

    /* JADX INFO: compiled from: PboPluginTexture.java */
    public interface a {
        int getTextureID();
    }

    private void a(String str) {
    }

    public y(IAMapDelegate iAMapDelegate) {
        this.f1832h = null;
        this.j = false;
        this.f1827c = iAMapDelegate;
        this.j = false;
        this.f1832h = new ThreadPoolExecutor(1, Runtime.getRuntime().availableProcessors() * 2, 1, TimeUnit.SECONDS, this.f1831g, new ee("AMapPboRenderThread"), new ThreadPoolExecutor.AbortPolicy());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.q = EGL14.eglGetDisplay(0);
        if (this.q == EGL14.EGL_NO_DISPLAY) {
            a("eglGetDisplay failed");
            return;
        }
        int[] iArr = new int[2];
        if (!EGL14.eglInitialize(this.q, iArr, 0, iArr, 1)) {
            this.q = null;
            a("eglInitialize failed");
            return;
        }
        this.r = EGL14.eglCreateContext(this.q, this.p, this.o, new int[]{12440, 2, 12344}, 0);
        if (this.r == EGL14.EGL_NO_CONTEXT) {
            a("eglCreateContext failed");
            return;
        }
        this.s = EGL14.eglCreatePbufferSurface(this.q, this.p, new int[]{12375, this.f1829e, 12374, this.f1830f, 12344}, 0);
        if (this.s == EGL14.EGL_NO_SURFACE) {
            a("eglCreatePbufferSurface failed");
            return;
        }
        EGLDisplay eGLDisplay = this.q;
        EGLSurface eGLSurface = this.s;
        if (!EGL14.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, this.r)) {
            a("eglMakeCurrent failed");
            return;
        }
        GLES20.glFlush();
        a("initOpenGL complete");
        this.i = true;
    }

    public void a(int i, int i2) {
        this.f1829e = i;
        this.f1830f = i2;
        this.o = EGL14.eglGetCurrentContext();
        if (this.o == EGL14.EGL_NO_CONTEXT) {
            a("eglGetCurrentContext failed");
            return;
        }
        EGLDisplay eGLDisplayEglGetCurrentDisplay = EGL14.eglGetCurrentDisplay();
        if (eGLDisplayEglGetCurrentDisplay == EGL14.EGL_NO_DISPLAY) {
            a("sharedEglDisplay failed");
            return;
        }
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        if (!EGL14.eglGetConfigs(eGLDisplayEglGetCurrentDisplay, eGLConfigArr, 0, eGLConfigArr.length, new int[1], 0)) {
            a("eglGetConfigs failed");
            return;
        }
        this.p = eGLConfigArr[0];
        ExecutorService executorService = this.f1832h;
        if (executorService == null || executorService.isShutdown()) {
            return;
        }
        this.f1832h.execute(new Runnable() { // from class: com.amap.api.mapcore.util.y.1
            @Override // java.lang.Runnable
            public void run() {
                y.this.d();
            }
        });
    }

    private void e() {
        IAMapDelegate iAMapDelegate = this.f1827c;
        if (iAMapDelegate != null) {
            this.t = (de.g) iAMapDelegate.getGLShader(0);
        }
    }

    public void a() {
        ExecutorService executorService = this.f1832h;
        if (executorService == null || executorService.isShutdown()) {
            return;
        }
        this.f1832h.execute(new Runnable() { // from class: com.amap.api.mapcore.util.y.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    y.this.n = false;
                    if (y.this.j) {
                        return;
                    }
                    y.this.k = 0;
                    int i = 0;
                    while (!y.this.j && y.this.k < 5 && i < 50) {
                        i++;
                        try {
                            Thread.sleep(16L);
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                        if (!y.this.i) {
                            if (y.this.x != null) {
                                y.this.x.onGenerateComplete(null, -1);
                            }
                            if (!y.this.n) {
                                y.this.n = true;
                                if (y.this.x != null) {
                                    y.this.x.onGenerateComplete(null, -1);
                                }
                            }
                            if (y.this.r != EGL14.EGL_NO_CONTEXT) {
                                EGL14.eglDestroyContext(y.this.q, y.this.r);
                                EGL14.eglDestroySurface(y.this.q, y.this.s);
                                y.this.r = null;
                            }
                            if (y.this.q != EGL14.EGL_NO_DISPLAY) {
                                EGL14.eglTerminate(y.this.q);
                                y.this.q = null;
                            }
                            y.this.r = EGL14.EGL_NO_CONTEXT;
                            y.this.q = EGL14.EGL_NO_DISPLAY;
                            return;
                        }
                        GLES20.glViewport(0, 0, y.this.f1829e, y.this.f1830f);
                        GLES20.glClear(16640);
                        y.this.f();
                    }
                    if (!y.this.n) {
                        y.this.n = true;
                        if (y.this.x != null) {
                            y.this.x.onGenerateComplete(null, -1);
                        }
                    }
                    if (y.this.r != EGL14.EGL_NO_CONTEXT) {
                        EGL14.eglDestroyContext(y.this.q, y.this.r);
                        EGL14.eglDestroySurface(y.this.q, y.this.s);
                        y.this.r = null;
                    }
                    if (y.this.q != EGL14.EGL_NO_DISPLAY) {
                        EGL14.eglTerminate(y.this.q);
                        y.this.q = null;
                    }
                    y.this.r = EGL14.EGL_NO_CONTEXT;
                    y.this.q = EGL14.EGL_NO_DISPLAY;
                } finally {
                    if (!y.this.n) {
                        y.this.n = true;
                        if (y.this.x != null) {
                            y.this.x.onGenerateComplete(null, -1);
                        }
                    }
                    if (y.this.r != EGL14.EGL_NO_CONTEXT) {
                        EGL14.eglDestroyContext(y.this.q, y.this.r);
                        EGL14.eglDestroySurface(y.this.q, y.this.s);
                        y.this.r = null;
                    }
                    if (y.this.q != EGL14.EGL_NO_DISPLAY) {
                        EGL14.eglTerminate(y.this.q);
                        y.this.q = null;
                    }
                    y.this.r = EGL14.EGL_NO_CONTEXT;
                    y.this.q = EGL14.EGL_NO_DISPLAY;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        try {
            if (this.j) {
                return;
            }
            if (this.w == null) {
                a("renderTextureAndReadPixel failed textureHelper is null");
                return;
            }
            if (this.w != null) {
                this.f1828d = this.w.getTextureID();
            }
            if (this.f1828d <= 0) {
                a("renderTextureAndReadPixel failed mTextureID is <= 0 mTextureID " + this.f1828d);
                return;
            }
            a("renderTextureAndReadPixel  mTextureID is  mTextureID " + this.f1828d);
            if (this.t == null || this.t.c()) {
                e();
            }
            if (this.u == null) {
                this.u = er.a(this.f1826b);
            }
            if (this.v == null) {
                this.v = er.a(new float[]{0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f});
            }
            this.t.a();
            GLES20.glDisable(3042);
            GLES20.glBlendFunc(1, 771);
            GLES20.glBlendColor(1.0f, 1.0f, 1.0f, 1.0f);
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(3553, this.f1828d);
            GLES20.glEnableVertexAttribArray(this.t.f596b);
            GLES20.glVertexAttribPointer(this.t.f596b, 3, 5126, false, 12, (Buffer) this.u);
            GLES20.glEnableVertexAttribArray(this.t.f597c);
            GLES20.glVertexAttribPointer(this.t.f597c, 2, 5126, false, 8, (Buffer) this.v);
            Matrix.setIdentityM(this.f1825a, 0);
            Matrix.scaleM(this.f1825a, 0, 1.0f, 1.0f, 1.0f);
            GLES20.glUniformMatrix4fv(this.t.f595a, 1, false, this.f1825a, 0);
            GLES20.glDrawArrays(6, 0, 4);
            GLES20.glDisableVertexAttribArray(this.t.f596b);
            GLES20.glDisableVertexAttribArray(this.t.f597c);
            GLES20.glBindTexture(3553, 0);
            GLES20.glUseProgram(0);
            GLES20.glDisable(3042);
            dy.a("drawTexure");
            GLES20.glFinish();
            this.k++;
            if (this.k == 5) {
                g();
            }
        } catch (Throwable unused) {
            CrossOverlay.GenerateCrossImageListener generateCrossImageListener = this.x;
            if (generateCrossImageListener != null) {
                generateCrossImageListener.onGenerateComplete(null, -1);
            }
        }
    }

    private void g() {
        if (this.x != null) {
            if (this.l == 0) {
                this.l = this.f1829e;
            }
            if (this.m == 0) {
                this.m = this.f1830f;
            }
            int i = this.f1830f;
            int i2 = this.m;
            this.x.onGenerateComplete(er.a(0, i - i2, this.l, i2), this.i ? 0 : -1);
            this.n = true;
        }
    }

    public void b() {
        this.j = true;
        FloatBuffer floatBuffer = this.v;
        if (floatBuffer != null) {
            floatBuffer.clear();
            this.v = null;
        }
        FloatBuffer floatBuffer2 = this.u;
        if (floatBuffer2 != null) {
            floatBuffer2.clear();
            this.u = null;
        }
        this.w = null;
        this.f1832h.shutdownNow();
    }

    public boolean c() {
        return this.j;
    }

    public void a(CrossOverlay.GenerateCrossImageListener generateCrossImageListener) {
        this.x = generateCrossImageListener;
    }

    public void a(a aVar) {
        this.w = aVar;
    }

    public void b(int i, int i2) {
        this.l = i;
        this.m = i2;
    }
}