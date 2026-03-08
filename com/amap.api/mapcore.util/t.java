package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES20;

/* JADX INFO: compiled from: GlOverlayTextureManager.java */
/* JADX INFO: loaded from: classes.dex */
public class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f1784a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1785b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f1786c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f1787d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Bitmap f1788e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Bitmap f1789f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Bitmap f1790g = null;

    public float b() {
        return 1.0f;
    }

    public void a(Context context) {
        Bitmap bitmap = this.f1788e;
        if (bitmap == null || bitmap.isRecycled()) {
            this.f1788e = er.a(context, "amap_sdk_lineTexture.png");
        }
        Bitmap bitmap2 = this.f1789f;
        if (bitmap2 == null || bitmap2.isRecycled()) {
            this.f1789f = er.a(context, "amap_sdk_lineDashTexture_square.png");
        }
        Bitmap bitmap3 = this.f1790g;
        if (bitmap3 == null || bitmap3.isRecycled()) {
            this.f1790g = er.a(context, "amap_sdk_lineDashTexture_circle.png");
        }
        this.f1784a = er.a(this.f1788e);
        this.f1785b = er.a(this.f1789f, true);
        this.f1786c = er.a(this.f1790g, true);
        this.f1787d = er.a(512, 1024);
    }

    public int a() {
        return this.f1784a;
    }

    public int a(int i) {
        if (i == 0) {
            return this.f1785b;
        }
        if (i == 1) {
            return this.f1786c;
        }
        return -1;
    }

    public int c() {
        return this.f1787d;
    }

    public void d() {
        GLES20.glDeleteTextures(4, new int[]{this.f1784a, this.f1785b, this.f1786c, this.f1787d}, 0);
    }

    public void e() {
        Bitmap bitmap = this.f1789f;
        if (bitmap != null && !bitmap.isRecycled()) {
            er.b(this.f1789f);
            this.f1789f = null;
        }
        Bitmap bitmap2 = this.f1790g;
        if (bitmap2 != null && !bitmap2.isRecycled()) {
            er.b(this.f1790g);
            this.f1790g = null;
        }
        Bitmap bitmap3 = this.f1788e;
        if (bitmap3 == null || bitmap3.isRecycled()) {
            return;
        }
        er.b(this.f1788e);
        this.f1788e = null;
    }
}