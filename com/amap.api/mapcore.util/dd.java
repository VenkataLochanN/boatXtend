package com.amap.api.mapcore.util;

import android.opengl.GLES20;

/* JADX INFO: compiled from: GlShader.java */
/* JADX INFO: loaded from: classes.dex */
public class dd {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f560a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f561d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f562e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f563f;

    protected boolean a(String str, String str2) {
        this.f561d = b(str, str2);
        return this.f561d != 0;
    }

    protected boolean a(String str) {
        this.f561d = d(str);
        return this.f561d != 0;
    }

    protected int b(String str) {
        return GLES20.glGetAttribLocation(this.f561d, str);
    }

    protected int c(String str) {
        return GLES20.glGetUniformLocation(this.f561d, str);
    }

    public void a() {
        GLES20.glUseProgram(this.f561d);
    }

    public int d(String str) {
        String str2 = "amap_sdk_shaders/" + str;
        String strA = dr.a(str2);
        if (strA == null) {
            throw new IllegalArgumentException("shader file not found: " + str2);
        }
        int iIndexOf = strA.indexOf(36);
        if (iIndexOf < 0 || strA.charAt(iIndexOf + 1) != '$') {
            throw new IllegalArgumentException("not a shader file " + str2);
        }
        return b(strA.substring(0, iIndexOf), strA.substring(iIndexOf + 2));
    }

    public int b(String str, String str2) {
        this.f562e = a(35633, str);
        this.f563f = a(35632, str2);
        int iGlCreateProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(iGlCreateProgram, this.f562e);
        GLES20.glAttachShader(iGlCreateProgram, this.f563f);
        GLES20.glLinkProgram(iGlCreateProgram);
        return iGlCreateProgram;
    }

    public int a(int i, String str) {
        int iGlCreateShader = GLES20.glCreateShader(i);
        GLES20.glShaderSource(iGlCreateShader, str);
        GLES20.glCompileShader(iGlCreateShader);
        return iGlCreateShader;
    }

    public void b() {
        int i = this.f561d;
        if (i >= 0) {
            GLES20.glDeleteProgram(i);
        }
        int i2 = this.f562e;
        if (i2 >= 0) {
            GLES20.glDeleteShader(i2);
        }
        int i3 = this.f563f;
        if (i3 >= 0) {
            GLES20.glDeleteShader(i3);
        }
        this.f560a = true;
    }

    public boolean c() {
        return this.f560a;
    }
}