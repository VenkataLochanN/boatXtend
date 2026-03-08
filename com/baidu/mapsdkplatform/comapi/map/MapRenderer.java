package com.baidu.mapsdkplatform.comapi.map;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: classes.dex */
public class MapRenderer implements GLSurfaceView.Renderer {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f3507d = MapRenderer.class.getSimpleName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3508a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3509b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3510c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private long f3511e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private a f3512f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final j f3513g;

    public interface a {
        void f();
    }

    public MapRenderer(j jVar, a aVar) {
        this.f3512f = aVar;
        this.f3513g = jVar;
    }

    private void a(GL10 gl10) {
        GLES20.glClear(16640);
        GLES20.glClearColor(0.85f, 0.8f, 0.8f, 0.0f);
    }

    private boolean a() {
        return this.f3511e != 0;
    }

    public static native void nativeInit(long j);

    public static native int nativeRender(long j);

    public static native void nativeResize(long j, int i, int i2);

    public void a(long j) {
        this.f3511e = j;
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        if (!a()) {
            a(gl10);
            return;
        }
        if (this.f3510c <= 1) {
            nativeResize(this.f3511e, this.f3508a, this.f3509b);
            this.f3510c++;
        }
        this.f3512f.f();
        int iNativeRender = nativeRender(this.f3511e);
        if (this.f3513g.a() == null) {
            return;
        }
        if (this.f3513g.a().f3567h != null) {
            for (l lVar : this.f3513g.a().f3567h) {
                if (this.f3513g.a() == null) {
                    return;
                }
                ab abVarJ = this.f3513g.a().J();
                if (lVar != null) {
                    lVar.a(gl10, abVarJ);
                }
            }
        }
        j jVar = this.f3513g;
        if (iNativeRender == 1) {
            jVar.requestRender();
            return;
        }
        if (jVar.a().b()) {
            if (jVar.getRenderMode() != 1) {
                jVar.setRenderMode(1);
            }
        } else if (jVar.getRenderMode() != 0) {
            jVar.setRenderMode(0);
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        long j = this.f3511e;
        if (j != 0) {
            nativeResize(j, i, i2);
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        nativeInit(this.f3511e);
        if (a()) {
            this.f3512f.f();
        }
    }
}