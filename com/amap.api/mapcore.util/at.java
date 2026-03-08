package com.amap.api.mapcore.util;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;
import com.amap.api.mapcore.util.de;
import com.amap.api.maps.model.BitmapDescriptor;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/* JADX INFO: compiled from: FakeInstanceMultiPoint.java */
/* JADX INFO: loaded from: classes.dex */
public class at {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f183a = 200;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    float[] f184b;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private BitmapDescriptor f189g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private FloatBuffer f190h;
    private ShortBuffer i;
    private de.a k;
    private de l;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f187e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f188f = false;
    private int j = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f185c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f186d = 0;

    public at(float[] fArr, aw awVar) {
        this.f184b = null;
        this.f184b = fArr;
    }

    private void a(float[] fArr) {
        if (fArr == null) {
            return;
        }
        if (this.f190h == null) {
            ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(fArr.length * f183a * 4);
            byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
            this.f190h = byteBufferAllocateDirect.asFloatBuffer();
        }
        this.f190h.clear();
        for (int i = 0; i < f183a; i++) {
            int i2 = 0;
            for (float f2 : fArr) {
                if (i2 % 6 == 3) {
                    this.f190h.put(i);
                } else {
                    this.f190h.put(f2);
                }
                i2++;
            }
        }
        this.f190h.position(0);
        if (this.i == null) {
            ByteBuffer byteBufferAllocateDirect2 = ByteBuffer.allocateDirect(f183a * 6 * 2);
            byteBufferAllocateDirect2.order(ByteOrder.nativeOrder());
            this.i = byteBufferAllocateDirect2.asShortBuffer();
            short[] sArr = new short[f183a * 6];
            for (int i3 = 0; i3 < f183a; i3++) {
                int i4 = i3 * 6;
                int i5 = i3 * 4;
                short s = (short) (i5 + 0);
                sArr[i4 + 0] = s;
                sArr[i4 + 1] = (short) (i5 + 1);
                short s2 = (short) (i5 + 2);
                sArr[i4 + 2] = s2;
                sArr[i4 + 3] = s;
                sArr[i4 + 4] = s2;
                sArr[i4 + 5] = (short) (i5 + 3);
            }
            this.i.put(sArr);
            this.i.flip();
        }
        this.f187e = true;
    }

    public void a() {
        float[] fArr = this.f184b;
        if (fArr == null || this.f187e) {
            return;
        }
        a(fArr);
    }

    public void a(BitmapDescriptor bitmapDescriptor) {
        this.f189g = bitmapDescriptor;
    }

    private void a(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        if (this.j == 0) {
            int[] iArr = new int[1];
            GLES20.glGenTextures(1, iArr, 0);
            this.j = iArr[0];
        }
        if (this.j == 0) {
            return;
        }
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.j);
        GLES20.glTexParameterf(3553, 10241, 9728.0f);
        GLES20.glTexParameterf(3553, 10240, 9729.0f);
        GLES20.glTexParameterf(3553, 10242, 33071.0f);
        GLES20.glTexParameterf(3553, 10243, 33071.0f);
        GLUtils.texImage2D(3553, 0, bitmap, 0);
        if (this.j != 0) {
            this.f188f = true;
        }
    }

    public boolean b() {
        return this.f187e;
    }

    public void a(float[] fArr, float[] fArr2, float[] fArr3, float f2, float f3, float f4, float f5, int i) {
        BitmapDescriptor bitmapDescriptor;
        if (!this.f188f && (bitmapDescriptor = this.f189g) != null) {
            a(bitmapDescriptor.getBitmap());
        }
        if (this.j == 0) {
            return;
        }
        de.a aVar = this.k;
        if (aVar == null || aVar.c()) {
            f();
        }
        e();
        GLES20.glUseProgram(this.k.f561d);
        GLES20.glUniform4f(this.k.j, f2, f3, f4, f5);
        GLES20.glUniform3fv(this.k.i, i, fArr3, 0);
        GLES20.glDisable(2929);
        GLES20.glEnable(3042);
        GLES20.glBlendFunc(770, 771);
        GLES20.glBlendColor(1.0f, 1.0f, 1.0f, 1.0f);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.j);
        GLES20.glEnableVertexAttribArray(this.k.f574c);
        GLES20.glBindBuffer(34962, this.f185c);
        GLES20.glVertexAttribPointer(this.k.f574c, 4, 5126, false, 24, 0);
        GLES20.glEnableVertexAttribArray(this.k.f576h);
        GLES20.glVertexAttribPointer(this.k.f576h, 2, 5126, false, 24, 16);
        GLES20.glUniformMatrix4fv(this.k.f575g, 1, false, fArr, 0);
        GLES20.glUniformMatrix4fv(this.k.k, 1, false, fArr2, 0);
        GLES20.glBindBuffer(34963, this.f186d);
        GLES20.glDrawElements(4, i * 6, 5123, 0);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindBuffer(34962, 0);
        GLES20.glDisableVertexAttribArray(this.k.f574c);
        GLES20.glDisableVertexAttribArray(this.k.f576h);
        GLES20.glUseProgram(0);
    }

    private void e() {
        if (this.f185c == 0) {
            int[] iArr = new int[2];
            GLES20.glGenBuffers(2, iArr, 0);
            this.f185c = iArr[0];
            this.f186d = iArr[1];
            GLES20.glBindBuffer(34962, this.f185c);
            GLES20.glBufferData(34962, this.f190h.limit() * 4, this.f190h, 35044);
            GLES20.glBindBuffer(34963, this.f186d);
            GLES20.glBufferData(34963, f183a * 6 * 2, this.i, 35044);
            a("bindVbo");
            this.f190h.clear();
            this.f190h = null;
        }
    }

    public void c() {
        FloatBuffer floatBuffer = this.f190h;
        if (floatBuffer != null) {
            floatBuffer.clear();
        }
        ShortBuffer shortBuffer = this.i;
        if (shortBuffer != null) {
            shortBuffer.clear();
        }
        if (this.f189g != null) {
            this.f189g = null;
        }
        GLES20.glDeleteBuffers(2, new int[]{this.f185c, this.f186d}, 0);
        int i = this.j;
        if (i != 0) {
            GLES20.glDeleteTextures(1, new int[]{i}, 0);
        }
        this.f185c = 0;
        this.f186d = 0;
        this.f184b = null;
        this.f187e = false;
        this.f188f = false;
        this.f185c = 0;
        this.f186d = 0;
        this.l = null;
    }

    public void a(de deVar) {
        this.l = deVar;
    }

    private void f() {
        try {
            if (this.l != null) {
                this.k = (de.a) this.l.a(4);
            }
        } catch (Throwable unused) {
            f183a = 1;
            de deVar = this.l;
            if (deVar != null) {
                this.k = (de.a) deVar.a(4);
            }
        }
    }

    public static synchronized void a(String str) {
        int iGlGetError = GLES20.glGetError();
        if (iGlGetError != 0) {
            Log.e("amap", str + ": glError " + iGlGetError);
            throw new RuntimeException(str + ": glError " + iGlGetError);
        }
    }

    public boolean d() {
        return this.l != null;
    }
}