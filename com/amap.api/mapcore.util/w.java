package com.amap.api.mapcore.util;

import com.autonavi.amap.mapcore.animation.GLAlphaAnimation;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/* JADX INFO: compiled from: MaskLayer.java */
/* JADX INFO: loaded from: classes.dex */
public class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public FloatBuffer f1809a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ShortBuffer f1810b;
    private GLAlphaAnimation i;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float f1813e = 0.0f;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private float f1814f = 0.0f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private float f1815g = 0.0f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float f1816h = 0.7f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    float[] f1811c = {-1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    short[] f1812d = {0, 1, 3, 0, 3, 2};

    public w() {
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(this.f1812d.length * 2);
        byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
        this.f1810b = byteBufferAllocateDirect.asShortBuffer();
        this.f1810b.put(this.f1812d);
        this.f1810b.position(0);
        ByteBuffer byteBufferAllocateDirect2 = ByteBuffer.allocateDirect(this.f1811c.length * 4);
        byteBufferAllocateDirect2.order(ByteOrder.nativeOrder());
        this.f1809a = byteBufferAllocateDirect2.asFloatBuffer();
        this.f1809a.put(this.f1811c);
        this.f1809a.position(0);
    }

    public void a(int i, int i2, int i3, int i4) {
        this.f1813e = i / 255.0f;
        this.f1814f = i2 / 255.0f;
        this.f1815g = i3 / 255.0f;
        this.f1816h = i4 / 255.0f;
    }

    public void a(GLAlphaAnimation gLAlphaAnimation) {
        GLAlphaAnimation gLAlphaAnimation2 = this.i;
        if (gLAlphaAnimation2 != null && !gLAlphaAnimation2.hasEnded()) {
            this.i.cancel();
        }
        if (gLAlphaAnimation == null) {
            return;
        }
        this.i = gLAlphaAnimation;
        this.i.start();
    }
}