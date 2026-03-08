package com.amap.api.mapcore.util;

import android.graphics.Color;
import android.opengl.GLES20;
import android.util.Log;
import android.view.SurfaceHolder;
import com.amap.api.mapcore.util.de;
import com.autonavi.base.amap.api.mapcore.IGLSurfaceView;
import com.autonavi.base.amap.mapcore.AMapNativeRenderer;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;

/* JADX INFO: compiled from: GlesUtility.java */
/* JADX INFO: loaded from: classes.dex */
public class dy {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static float[] f689a = new float[1024];

    public static void a(String str) {
        int iGlGetError = GLES20.glGetError();
        if (iGlGetError == 0) {
            return;
        }
        Log.e("amap", str + ": glError " + iGlGetError);
        throw new RuntimeException(str + ": glError " + iGlGetError);
    }

    public static void a(de.e eVar, int i, int i2, FloatBuffer floatBuffer, float f2, FloatBuffer floatBuffer2, int i3, int i4, float[] fArr, int i5, float f3, float f4, int i6, int i7, boolean z, boolean z2) {
        b(eVar, 4, i, floatBuffer2, 1.0f, i4, fArr);
        if (z2) {
            a(eVar, i, i2, floatBuffer, f2, floatBuffer2, i3, i4, fArr, i5, f3, f4, i6, i7, z);
        }
    }

    public static void a(de.e eVar, int i, int i2, FloatBuffer floatBuffer, float f2, int i3, float[] fArr, float f3, int i4, float f4, boolean z, boolean z2) {
        b(eVar, 6, i, floatBuffer, 1.0f, i3, fArr);
        if (z2) {
            a(eVar, i, i2, floatBuffer, f2, i3, fArr, f3, i4, f4, z);
        }
    }

    public static void a(de.e eVar, int i, int i2, FloatBuffer floatBuffer, float f2, int i3, float[] fArr, float f3, int i4, float f4, boolean z) {
        if (z && i4 != -1) {
            a(eVar, i, i2, floatBuffer, f2 * f3, i3, fArr, i4, f4);
        } else {
            a(eVar, i, i2, floatBuffer, f2, i3, fArr);
        }
    }

    public static void a(de.e eVar, int i, int i2, FloatBuffer floatBuffer, float f2, FloatBuffer floatBuffer2, int i3, int i4, float[] fArr, int i5, float f3, float f4, int i6, int i7, boolean z) {
        if (z && i5 != -1) {
            float f5 = f4 * f2;
            try {
                float fAlpha = Color.alpha(i2) / 255.0f;
                float fRed = Color.red(i2) / 255.0f;
                float fGreen = Color.green(i2) / 255.0f;
                float fBlue = Color.blue(i2) / 255.0f;
                if (i3 < 3) {
                    return;
                }
                int i8 = i3 * 3;
                if (floatBuffer != null && floatBuffer.limit() >= i8) {
                    if (f689a == null || f689a.length < i8) {
                        f689a = new float[i8];
                    }
                    floatBuffer.get(f689a, 0, i8);
                    AMapNativeRenderer.nativeDrawLineByTextureID(f689a, i8, f5, i5, f3, fRed, fGreen, fBlue, fAlpha, 0.0f, false, true, false, fArr, i6, i7, true);
                    return;
                }
                return;
            } catch (Throwable unused) {
                b(eVar, 2, i2, floatBuffer, f2, i3, fArr);
                return;
            }
        }
        b(eVar, 2, i2, floatBuffer, f2, i3, fArr);
    }

    private static void a(de.e eVar, int i, int i2, FloatBuffer floatBuffer, float f2, int i3, float[] fArr, int i4, float f3) {
        try {
            float fAlpha = Color.alpha(i2) / 255.0f;
            float fRed = Color.red(i2) / 255.0f;
            float fGreen = Color.green(i2) / 255.0f;
            float fBlue = Color.blue(i2) / 255.0f;
            if (i3 < 3) {
                return;
            }
            int i5 = (i3 - 1) * 3;
            if (floatBuffer != null && floatBuffer.limit() >= i5 + 3) {
                if (f689a == null || f689a.length < i5) {
                    f689a = new float[i5];
                }
                for (int i6 = 0; i6 < i5; i6++) {
                    f689a[i6] = floatBuffer.get(i6 + 3);
                }
                AMapNativeRenderer.nativeDrawLineByTextureID(f689a, i5, f2, i4, f3, fRed, fGreen, fBlue, fAlpha, 0.0f, true, true, false, fArr, 3, 0, true);
            }
        } catch (Throwable th) {
            hn.c(th, "GlesUtility", "drawCircleLine");
        }
    }

    private static void a(de.e eVar, int i, int i2, FloatBuffer floatBuffer, float f2, int i3, float[] fArr) {
        a(eVar, 2, i2, floatBuffer, f2, 1, i3 - 1, fArr);
    }

    private static void b(de.e eVar, int i, int i2, FloatBuffer floatBuffer, float f2, int i3, float[] fArr) {
        a(eVar, i, i2, floatBuffer, f2, 0, i3, fArr);
    }

    private static void a(de.e eVar, int i, int i2, FloatBuffer floatBuffer, float f2, int i3, int i4, float[] fArr) {
        if (f2 == 0.0f || eVar == null) {
            return;
        }
        eVar.a();
        GLES20.glEnable(3042);
        GLES20.glDisable(2929);
        GLES20.glBlendFunc(770, 771);
        GLES20.glLineWidth(f2);
        GLES20.glEnableVertexAttribArray(eVar.f593b);
        GLES20.glVertexAttribPointer(eVar.f593b, 3, 5126, false, 0, (Buffer) floatBuffer);
        GLES20.glUniform4fv(eVar.f594c, 1, new float[]{Color.red(i2) / 255.0f, Color.green(i2) / 255.0f, Color.blue(i2) / 255.0f, Color.alpha(i2) / 255.0f}, 0);
        GLES20.glUniformMatrix4fv(eVar.f592a, 1, false, fArr, 0);
        GLES20.glDrawArrays(i, i3, i4);
        GLES20.glDisableVertexAttribArray(eVar.f593b);
        GLES20.glDisable(3042);
        GLES20.glUseProgram(0);
    }

    public static void a(IGLSurfaceView iGLSurfaceView, int i, int i2, int i3, int i4, int i5, int i6) {
        SurfaceHolder holder;
        if (i4 > 0 && (holder = iGLSurfaceView.getHolder()) != null) {
            holder.setFormat(-3);
        }
        iGLSurfaceView.setEGLContextFactory(new b());
        iGLSurfaceView.setEGLConfigChooser(new a(i, i2, i3, i4, i5, i6));
    }

    /* JADX INFO: compiled from: GlesUtility.java */
    public static class b extends dx {
        @Override // com.amap.api.mapcore.util.dx, android.opengl.GLSurfaceView.EGLContextFactory, com.amap.api.mapcore.util.n.f
        public EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            try {
                return egl10.eglCreateContext(eGLDisplay, eGLConfig, EGL10.EGL_NO_CONTEXT, new int[]{12440, 2, 12344});
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }

        @Override // com.amap.api.mapcore.util.dx, android.opengl.GLSurfaceView.EGLContextFactory, com.amap.api.mapcore.util.n.f
        public void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
            egl10.eglDestroyContext(eGLDisplay, eGLContext);
        }
    }

    /* JADX INFO: compiled from: GlesUtility.java */
    private static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int[] f698a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int[] f699b;

        private c() {
            this.f698a = null;
            this.f699b = new int[1];
        }
    }

    /* JADX INFO: compiled from: GlesUtility.java */
    public static class a extends dw {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private static int f690g = 4;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected int f691a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        protected int f692b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        protected int f693c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        protected int f694d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        protected int f695e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        protected int f696f;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private int[] f697h = new int[1];

        public a(int i, int i2, int i3, int i4, int i5, int i6) {
            this.f691a = i;
            this.f692b = i2;
            this.f693c = i3;
            this.f694d = i4;
            this.f695e = i5;
            this.f696f = i6;
        }

        private int[] a(int i, boolean z) {
            return i == 2 ? new int[]{12324, this.f691a, 12323, this.f692b, 12322, this.f693c, 12321, this.f694d, 12325, this.f695e, 12326, this.f696f, 12338, z ? 1 : 0, 12352, f690g, 12344} : z ? new int[]{12324, this.f691a, 12323, this.f692b, 12322, this.f693c, 12338, 1, 12344} : new int[]{12324, this.f691a, 12323, this.f692b, 12322, this.f693c, 12344};
        }

        private c a(EGL10 egl10, EGLDisplay eGLDisplay) {
            c cVar = new c();
            cVar.f698a = a(2, true);
            egl10.eglChooseConfig(eGLDisplay, cVar.f698a, null, 0, cVar.f699b);
            if (cVar.f699b[0] <= 0) {
                cVar.f698a = a(2, false);
                egl10.eglChooseConfig(eGLDisplay, cVar.f698a, null, 0, cVar.f699b);
                if (cVar.f699b[0] <= 0) {
                    return null;
                }
            }
            return cVar;
        }

        @Override // com.amap.api.mapcore.util.dw, android.opengl.GLSurfaceView.EGLConfigChooser, com.amap.api.mapcore.util.n.e
        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
            c cVarA = a(egl10, eGLDisplay);
            if (cVarA == null || cVarA.f698a == null) {
                return null;
            }
            EGLConfig[] eGLConfigArr = new EGLConfig[cVarA.f699b[0]];
            egl10.eglChooseConfig(eGLDisplay, cVarA.f698a, eGLConfigArr, cVarA.f699b[0], cVarA.f699b);
            EGLConfig eGLConfigA = a(egl10, eGLDisplay, eGLConfigArr);
            if (eGLConfigA != null) {
                return eGLConfigA;
            }
            this.f691a = 8;
            this.f692b = 8;
            this.f693c = 8;
            c cVarA2 = a(egl10, eGLDisplay);
            if (cVarA2 == null || cVarA2.f698a == null) {
                return eGLConfigA;
            }
            EGLConfig[] eGLConfigArr2 = new EGLConfig[cVarA2.f699b[0]];
            egl10.eglChooseConfig(eGLDisplay, cVarA2.f698a, eGLConfigArr2, cVarA2.f699b[0], cVarA2.f699b);
            return a(egl10, eGLDisplay, eGLConfigArr2);
        }

        public EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
            for (EGLConfig eGLConfig : eGLConfigArr) {
                int iA = a(egl10, eGLDisplay, eGLConfig, 12325, 0);
                int iA2 = a(egl10, eGLDisplay, eGLConfig, 12326, 0);
                if (iA >= this.f695e && iA2 >= this.f696f) {
                    int iA3 = a(egl10, eGLDisplay, eGLConfig, 12324, 0);
                    int iA4 = a(egl10, eGLDisplay, eGLConfig, 12323, 0);
                    int iA5 = a(egl10, eGLDisplay, eGLConfig, 12322, 0);
                    int iA6 = a(egl10, eGLDisplay, eGLConfig, 12321, 0);
                    if (iA3 == this.f691a && iA4 == this.f692b && iA5 == this.f693c && iA6 == this.f694d) {
                        return eGLConfig;
                    }
                }
            }
            return null;
        }

        private int a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int i2) {
            return egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, this.f697h) ? this.f697h[0] : i2;
        }
    }
}