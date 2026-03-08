package com.amap.api.mapcore.util;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.ViewParent;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.api.mapcore.IGLSurfaceView;

/* JADX INFO: compiled from: AMapGLRenderer.java */
/* JADX INFO: loaded from: classes.dex */
public class d implements IGLSurfaceView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected boolean f515a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private IAMapDelegate f516b;

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public int getHeight() {
        return 0;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public SurfaceHolder getHolder() {
        return null;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public ViewParent getParent() {
        return null;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public int getRenderMode() {
        return 0;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public int getWidth() {
        return 0;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public void onDetachedGLThread() {
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public boolean post(Runnable runnable) {
        return false;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public boolean postDelayed(Runnable runnable, long j) {
        return false;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public void requestRender() {
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public void setBackgroundColor(int i) {
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public void setEGLConfigChooser(dw dwVar) {
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public void setEGLContextFactory(dx dxVar) {
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public void setRenderMode(int i) {
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public void setRenderer(GLSurfaceView.Renderer renderer) {
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public void setVisibility(int i) {
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public void setZOrderOnTop(boolean z) {
    }

    public d(Context context) {
        this(context, null);
    }

    public d(Context context, AttributeSet attributeSet) {
        this.f516b = null;
        this.f515a = false;
        this.f516b = new c(this, context, attributeSet);
    }

    public IAMapDelegate a() {
        return this.f516b;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public void queueEvent(Runnable runnable) {
        if (runnable != null) {
            runnable.run();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public boolean isEnabled() {
        return this.f516b != null;
    }
}