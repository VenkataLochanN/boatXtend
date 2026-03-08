package com.baidu.mapsdkplatform.comapi.map;

import android.graphics.SurfaceTexture;
import android.opengl.GLUtils;
import java.lang.Thread;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: classes.dex */
public class m extends Thread {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private AtomicBoolean f3599a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private SurfaceTexture f3600b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private a f3601c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private EGL10 f3602d;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private GL10 f3606h;
    private final ac k;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private EGLDisplay f3603e = EGL10.EGL_NO_DISPLAY;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private EGLContext f3604f = EGL10.EGL_NO_CONTEXT;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private EGLSurface f3605g = EGL10.EGL_NO_SURFACE;
    private int i = 1;
    private boolean j = false;

    public interface a {
        int a();
    }

    public m(SurfaceTexture surfaceTexture, a aVar, AtomicBoolean atomicBoolean, ac acVar) {
        this.f3600b = surfaceTexture;
        this.f3601c = aVar;
        this.f3599a = atomicBoolean;
        this.k = acVar;
    }

    private boolean a(int i, int i2, int i3, int i4, int i5, int i6) {
        this.f3602d = (EGL10) EGLContext.getEGL();
        this.f3603e = this.f3602d.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        if (this.f3603e == EGL10.EGL_NO_DISPLAY) {
            throw new RuntimeException("eglGetdisplay failed : " + GLUtils.getEGLErrorString(this.f3602d.eglGetError()));
        }
        if (!this.f3602d.eglInitialize(this.f3603e, new int[2])) {
            throw new RuntimeException("eglInitialize failed : " + GLUtils.getEGLErrorString(this.f3602d.eglGetError()));
        }
        EGLConfig[] eGLConfigArr = new EGLConfig[100];
        int[] iArr = new int[1];
        if (!this.f3602d.eglChooseConfig(this.f3603e, new int[]{12352, 4, 12324, i, 12323, i2, 12322, i3, 12321, i4, 12325, i5, 12326, i6, 12344}, eGLConfigArr, 100, iArr) || iArr[0] <= 0) {
            return false;
        }
        this.f3604f = this.f3602d.eglCreateContext(this.f3603e, eGLConfigArr[0], EGL10.EGL_NO_CONTEXT, new int[]{12440, 2, 12344});
        this.f3605g = this.f3602d.eglCreateWindowSurface(this.f3603e, eGLConfigArr[0], this.f3600b, null);
        if (this.f3605g == EGL10.EGL_NO_SURFACE || this.f3604f == EGL10.EGL_NO_CONTEXT) {
            if (this.f3602d.eglGetError() == 12299) {
                throw new RuntimeException("eglCreateWindowSurface returned EGL_BAD_NATIVE_WINDOW. ");
            }
            GLUtils.getEGLErrorString(this.f3602d.eglGetError());
        }
        EGL10 egl10 = this.f3602d;
        EGLDisplay eGLDisplay = this.f3603e;
        EGLSurface eGLSurface = this.f3605g;
        if (egl10.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, this.f3604f)) {
            this.f3606h = (GL10) this.f3604f.getGL();
            return true;
        }
        throw new RuntimeException("eglMakeCurrent failed : " + GLUtils.getEGLErrorString(this.f3602d.eglGetError()));
    }

    private static boolean b(int i, int i2, int i3, int i4, int i5, int i6) {
        EGL10 egl10 = (EGL10) EGLContext.getEGL();
        EGLDisplay eGLDisplayEglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        egl10.eglInitialize(eGLDisplayEglGetDisplay, new int[2]);
        int[] iArr = new int[1];
        return egl10.eglChooseConfig(eGLDisplayEglGetDisplay, new int[]{12324, i, 12323, i2, 12322, i3, 12321, i4, 12325, i5, 12326, i6, 12344}, new EGLConfig[100], 100, iArr) && iArr[0] > 0;
    }

    private void d() {
        try {
            if (b(5, 6, 5, 0, 24, 0)) {
                a(8, 8, 8, 0, 24, 0);
            } else {
                a(8, 8, 8, 0, 24, 0);
            }
        } catch (IllegalArgumentException unused) {
            a(8, 8, 8, 0, 24, 0);
        }
        if (this.k.b() == null) {
            return;
        }
        MapRenderer.nativeInit(this.k.b().j);
        MapRenderer.nativeResize(this.k.b().j, ac.f3534a, ac.f3535b);
    }

    private void e() {
        if (this.f3605g != EGL10.EGL_NO_SURFACE) {
            this.f3602d.eglMakeCurrent(this.f3603e, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
            this.f3602d.eglDestroySurface(this.f3603e, this.f3605g);
            this.f3605g = EGL10.EGL_NO_SURFACE;
        }
        if (this.f3604f != EGL10.EGL_NO_CONTEXT) {
            this.f3602d.eglDestroyContext(this.f3603e, this.f3604f);
            this.f3604f = EGL10.EGL_NO_CONTEXT;
        }
        if (this.f3603e != EGL10.EGL_NO_DISPLAY) {
            this.f3602d.eglTerminate(this.f3603e);
            this.f3603e = EGL10.EGL_NO_DISPLAY;
        }
    }

    public void a() {
        this.i = 1;
        this.j = false;
        synchronized (this) {
            if (getState() == Thread.State.WAITING) {
                notify();
            }
        }
    }

    public void b() {
        this.i = 0;
        synchronized (this) {
            this.j = true;
        }
    }

    public void c() {
        this.j = true;
        synchronized (this) {
            if (getState() == Thread.State.WAITING) {
                notify();
            }
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        d();
        while (this.f3601c != null) {
            if (this.i != 1 || this.j) {
                try {
                    synchronized (this) {
                        wait();
                    }
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            } else {
                if (this.k.b() == null) {
                    break;
                }
                synchronized (this.k.b()) {
                    synchronized (this) {
                        if (!this.j) {
                            this.i = this.f3601c.a();
                        }
                        e eVarB = this.k.b();
                        if (eVarB != null && eVarB.f3567h != null) {
                            for (l lVar : eVarB.f3567h) {
                                if (lVar != null) {
                                    ab abVarJ = eVarB.J();
                                    if (this.f3606h == null) {
                                        return;
                                    }
                                    if (lVar != null) {
                                        lVar.a(this.f3606h, abVarJ);
                                    }
                                }
                            }
                        }
                        this.f3602d.eglSwapBuffers(this.f3603e, this.f3605g);
                    }
                }
            }
            if (this.j) {
                break;
            }
        }
        e();
    }
}