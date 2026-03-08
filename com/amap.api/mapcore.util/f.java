package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import com.amap.api.maps.MapsInitializer;
import com.autonavi.base.ae.gmap.GLMapRender;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.api.mapcore.IGLSurfaceView;

/* JADX INFO: compiled from: AMapGLTextureView.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends n implements IGLSurfaceView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected boolean f803a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private IAMapDelegate f804b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private GLMapRender f805c;

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public SurfaceHolder getHolder() {
        return null;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public void setZOrderOnTop(boolean z) {
    }

    public f(Context context, boolean z) {
        super(context, null);
        this.f804b = null;
        this.f805c = null;
        this.f803a = false;
        dy.a(this, 5, 6, 5, 0, 16, 8);
        this.f804b = new c(this, context, null, z);
    }

    public IAMapDelegate a() {
        return this.f804b;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        try {
            return this.f804b.onTouchEvent(motionEvent);
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public void setEGLConfigChooser(dw dwVar) {
        super.a(dwVar);
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public void setEGLContextFactory(dx dxVar) {
        super.a(dxVar);
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public void onDetachedGLThread() {
        ey.a(ex.f796c, "AMapGLTextureView onDetachedGLThread MapsInitializer.isSupportRecycleView() " + MapsInitializer.isSupportRecycleView());
        if (MapsInitializer.isSupportRecycleView()) {
            b();
            try {
                if (this.f805c != null) {
                    this.f805c.onDetachedFromWindow();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            super.onDetachedFromWindow();
        }
    }

    @Override // com.amap.api.mapcore.util.n, com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public void setRenderer(GLSurfaceView.Renderer renderer) {
        this.f805c = (GLMapRender) renderer;
        super.setRenderer(renderer);
    }

    @Override // com.amap.api.mapcore.util.n
    public void b() {
        ey.a(ex.f796c, "AMapGLTextureView onPause mMapRender.mSurfacedestoryed " + this.f805c.mSurfacedestoryed);
        if (!this.f805c.mSurfacedestoryed) {
            queueEvent(new Runnable() { // from class: com.amap.api.mapcore.util.f.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (f.this.f805c != null) {
                            f.this.f805c.onSurfaceDestory();
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                        er.a(th);
                    }
                }
            });
            int i = 0;
            while (!this.f805c.mSurfacedestoryed) {
                int i2 = i + 1;
                if (i >= 50) {
                    break;
                }
                try {
                    Thread.sleep(20L);
                } catch (InterruptedException unused) {
                }
                i = i2;
            }
        }
        super.b();
    }

    @Override // com.amap.api.mapcore.util.n
    public void c() {
        super.c();
        ey.a(ex.f796c, "AMapGLTextureView onResume");
    }

    @Override // com.amap.api.mapcore.util.n, android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        ey.a(ex.f796c, "AMapGLTextureView onSurfaceTextureDestroyed");
        requestRender();
        try {
            if (MapsInitializer.getTextureDestroyRender()) {
                Thread.sleep(100L);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            er.a(th);
        }
        return super.onSurfaceTextureDestroyed(surfaceTexture);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        ey.a(ex.f796c, "AMapGLTextureView onWindowVisibilityChanged visibility " + i);
        try {
            if (i == 8 || i == 4) {
                if (this.f805c != null) {
                    this.f805c.renderPause();
                    this.f803a = false;
                }
                requestRender();
                return;
            }
            if (i != 0 || this.f805c == null) {
                return;
            }
            this.f805c.renderResume();
        } catch (Throwable th) {
            th.printStackTrace();
            er.a(th);
        }
    }

    @Override // com.amap.api.mapcore.util.n, android.view.TextureView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ey.a(ex.f796c, "AMapGLTextureView onAttachedToWindow");
        try {
            if (this.f805c != null) {
                this.f805c.onAttachedToWindow();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        c();
    }

    @Override // com.amap.api.mapcore.util.n, android.view.View
    protected void onDetachedFromWindow() {
        ey.a(ex.f796c, "AMapGLTextureView onDetachedFromWindow MapsInitializer.isSupportRecycleView() " + MapsInitializer.isSupportRecycleView());
        if (MapsInitializer.isSupportRecycleView()) {
            return;
        }
        b();
        try {
            if (this.f805c != null) {
                this.f805c.onDetachedFromWindow();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        super.onDetachedFromWindow();
    }
}