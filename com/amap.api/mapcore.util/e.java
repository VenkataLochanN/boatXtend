package com.amap.api.mapcore.util;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.amap.api.maps.MapsInitializer;
import com.autonavi.base.ae.gmap.GLMapRender;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.api.mapcore.IGLSurfaceView;

/* JADX INFO: compiled from: AMapGLSurfaceView.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends GLSurfaceView implements IGLSurfaceView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected boolean f704a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private IAMapDelegate f705b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private GLMapRender f706c;

    public e(Context context, boolean z) {
        this(context, null, z);
    }

    public e(Context context, AttributeSet attributeSet, boolean z) {
        super(context, attributeSet);
        this.f705b = null;
        this.f706c = null;
        this.f704a = false;
        dy.a(this, 5, 6, 5, 0, 16, 8);
        this.f705b = new c(this, context, attributeSet, z);
    }

    public IAMapDelegate a() {
        return this.f705b;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        try {
            return this.f705b.onTouchEvent(motionEvent);
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    @Override // android.opengl.GLSurfaceView, com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public void setRenderer(GLSurfaceView.Renderer renderer) {
        this.f706c = (GLMapRender) renderer;
        super.setRenderer(renderer);
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public void setEGLConfigChooser(dw dwVar) {
        super.setEGLConfigChooser((GLSurfaceView.EGLConfigChooser) dwVar);
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public void setEGLContextFactory(dx dxVar) {
        super.setEGLContextFactory((GLSurfaceView.EGLContextFactory) dxVar);
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public void onDetachedGLThread() {
        ey.a(ex.f796c, "AMapGLSurfaceView onDetachedGLThread MapsInitializer.isSupportRecycleView() " + MapsInitializer.isSupportRecycleView());
        if (MapsInitializer.isSupportRecycleView()) {
            onPause();
            try {
                if (this.f706c != null) {
                    this.f706c.onDetachedFromWindow();
                }
            } catch (Throwable th) {
                th.printStackTrace();
                er.a(th);
            }
            super.onDetachedFromWindow();
        }
    }

    @Override // android.opengl.GLSurfaceView
    public void onPause() {
        ey.a(ex.f796c, "AMapGLSurfaceView onPause mMapRender.mSurfacedestoryed " + this.f706c.mSurfacedestoryed);
        if (!this.f706c.mSurfacedestoryed) {
            queueEvent(new Runnable() { // from class: com.amap.api.mapcore.util.e.1
                @Override // java.lang.Runnable
                public void run() {
                    if (e.this.f706c != null) {
                        try {
                            e.this.f706c.onSurfaceDestory();
                        } catch (Throwable th) {
                            th.printStackTrace();
                            er.a(th);
                        }
                    }
                }
            });
            int i = 0;
            while (!this.f706c.mSurfacedestoryed) {
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
        super.onPause();
    }

    @Override // android.opengl.GLSurfaceView
    public void onResume() {
        super.onResume();
        ey.a(ex.f796c, "AMapGLSurfaceView onPause");
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        ey.a(ex.f796c, "AMapGLSurfaceView onWindowVisibilityChanged visibility " + i);
        try {
            if (i == 8 || i == 4) {
                if (this.f706c != null) {
                    this.f706c.renderPause();
                    this.f704a = false;
                }
            } else {
                if (i != 0) {
                    return;
                }
                if (this.f706c != null) {
                    this.f706c.renderResume();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
            er.a(th);
        }
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ey.a(ex.f796c, "AMapGLSurfaceView onAttachedToWindow");
        try {
            if (this.f706c != null) {
                this.f706c.onAttachedToWindow();
            }
        } catch (Throwable th) {
            th.printStackTrace();
            er.a(th);
        }
        onResume();
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceView, android.view.View
    protected void onDetachedFromWindow() {
        ey.a(ex.f796c, "AMapGLSurfaceView onDetachedFromWindow MapsInitializer.isSupportRecycleView() " + MapsInitializer.isSupportRecycleView());
        if (MapsInitializer.isSupportRecycleView()) {
            return;
        }
        onPause();
        try {
            if (this.f706c != null) {
                this.f706c.onDetachedFromWindow();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        super.onDetachedFromWindow();
    }
}