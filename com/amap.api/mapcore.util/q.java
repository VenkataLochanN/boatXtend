package com.amap.api.mapcore.util;

import android.opengl.GLES20;
import android.opengl.Matrix;
import com.amap.api.mapcore.util.de;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: GlModelCore.java */
/* JADX INFO: loaded from: classes.dex */
public class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    List<Float> f1759a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    List<Float> f1760b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private FloatBuffer f1761c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private FloatBuffer f1762d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f1763e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private float f1764f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private float f1765g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float f1766h;

    public q(List<Float> list, List<Float> list2) {
        this.f1759a = new ArrayList();
        this.f1760b = new ArrayList();
        this.f1765g = 0.0f;
        this.f1766h = 0.0f;
        this.f1759a = list;
        this.f1760b = list2;
        if (this.f1761c == null) {
            ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(list.size() * 4);
            byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
            this.f1761c = byteBufferAllocateDirect.asFloatBuffer();
        }
        this.f1761c.clear();
        float fMax = 0.0f;
        float fMax2 = 0.0f;
        float fMin = 1000000.0f;
        float fMin2 = 1000000.0f;
        int i = 1;
        float fMax3 = 0.0f;
        for (int i2 = 1; i2 < list.size() + 1; i2++) {
            Float f2 = list.get(i2 - 1);
            this.f1761c.put(f2.floatValue());
            if (i == 1) {
                fMax3 = Math.max(f2.floatValue(), fMax3);
                fMin = Math.min(f2.floatValue(), fMin);
            }
            if (i == 2) {
                fMax = Math.max(f2.floatValue(), fMax);
                fMin2 = Math.min(f2.floatValue(), fMin2);
            }
            if (i == 3) {
                fMax2 = Math.max(fMax2, f2.floatValue());
                i = 0;
            }
            i++;
        }
        float fAbs = Math.abs(fMax3 - fMin);
        float fAbs2 = Math.abs(fMax - fMin2);
        this.f1765g = fAbs > fAbs2 ? fAbs : fAbs2;
        this.f1766h = fAbs > fAbs2 ? fAbs2 : fAbs;
        this.f1761c.position(0);
        if (this.f1762d == null) {
            ByteBuffer byteBufferAllocateDirect2 = ByteBuffer.allocateDirect(list2.size() * 4);
            byteBufferAllocateDirect2.order(ByteOrder.nativeOrder());
            this.f1762d = byteBufferAllocateDirect2.asFloatBuffer();
        }
        this.f1762d.clear();
        Iterator<Float> it = list2.iterator();
        while (it.hasNext()) {
            this.f1762d.put(it.next().floatValue());
        }
        this.f1762d.position(0);
    }

    public float a() {
        return this.f1765g;
    }

    public float b() {
        return this.f1766h;
    }

    public void a(int i) {
        this.f1763e = i;
    }

    public void a(float f2) {
        this.f1764f = -f2;
    }

    public void a(de.b bVar, float[] fArr) {
        Matrix.rotateM(fArr, 0, this.f1764f, 0.0f, 0.0f, 1.0f);
        GLES20.glUseProgram(bVar.f561d);
        GLES20.glClear(256);
        GLES20.glEnable(2929);
        GLES20.glDepthMask(true);
        GLES20.glEnable(3042);
        GLES20.glBlendFunc(770, 771);
        GLES20.glBlendColor(1.0f, 1.0f, 1.0f, 1.0f);
        GLES20.glBindTexture(3553, this.f1763e);
        GLES20.glEnableVertexAttribArray(bVar.f581h);
        GLES20.glVertexAttribPointer(bVar.f581h, 2, 5126, false, 8, (Buffer) this.f1762d);
        GLES20.glEnableVertexAttribArray(bVar.f579c);
        GLES20.glVertexAttribPointer(bVar.f579c, 3, 5126, false, 12, (Buffer) this.f1761c);
        GLES20.glUniformMatrix4fv(bVar.f580g, 1, false, fArr, 0);
        GLES20.glDrawArrays(4, 0, this.f1759a.size() / 3);
        GLES20.glBindTexture(3553, 0);
        GLES20.glDisable(2929);
        GLES20.glDisableVertexAttribArray(bVar.f579c);
        GLES20.glDisableVertexAttribArray(bVar.f581h);
        GLES20.glUseProgram(0);
    }

    public void c() {
        this.f1759a.clear();
        this.f1762d.clear();
    }
}