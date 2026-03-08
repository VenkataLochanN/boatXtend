package com.amap.api.mapcore.util;

import android.opengl.GLES20;
import com.autonavi.base.amap.mapcore.gles.AMapNativeGLShaderManager;

/* JADX INFO: compiled from: GlShaderManager.java */
/* JADX INFO: loaded from: classes.dex */
public class de {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private d f564a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private g f565b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c f566c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private e f567d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private e f568e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private a f569f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private b f570g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private long f571h;

    public de() {
        this.f571h = 0L;
        this.f571h = AMapNativeGLShaderManager.nativeCreateGLShaderManager();
    }

    public long a() {
        return this.f571h;
    }

    private synchronized dd c() {
        if (this.f564a == null) {
            this.f564a = new d("texture_normal.glsl");
        }
        return this.f564a;
    }

    private synchronized dd d() {
        if (this.f565b == null) {
            this.f565b = new g("texture.glsl");
        }
        return this.f565b;
    }

    private synchronized dd e() {
        if (this.f566c == null) {
            this.f566c = new c("texture_layer.glsl");
        }
        return this.f566c;
    }

    private synchronized dd f() {
        if (this.f567d == null) {
            this.f567d = new e("point.glsl");
        }
        return this.f567d;
    }

    private synchronized dd g() {
        if (this.f568e == null) {
            this.f568e = new f("point_2.glsl");
        }
        return this.f568e;
    }

    private synchronized a h() {
        if (this.f569f == null) {
            this.f569f = new a();
        }
        return this.f569f;
    }

    public dd a(int i) {
        switch (i) {
            case 0:
                return d();
            case 1:
                return c();
            case 2:
                return e();
            case 3:
                return f();
            case 4:
                return h();
            case 5:
                return i();
            case 6:
                return g();
            default:
                return null;
        }
    }

    private synchronized dd i() {
        if (this.f570g == null) {
            this.f570g = new b();
        }
        return this.f570g;
    }

    public synchronized void b() {
        if (this.f564a != null) {
            this.f564a.b();
            this.f564a = null;
        }
        if (this.f565b != null) {
            this.f565b.b();
            this.f565b = null;
        }
        if (this.f566c != null) {
            this.f566c.b();
            this.f566c = null;
        }
        if (this.f567d != null) {
            this.f567d.b();
            this.f567d = null;
        }
        if (this.f568e != null) {
            this.f568e.b();
            this.f568e = null;
        }
        if (this.f571h != 0) {
            AMapNativeGLShaderManager.nativeDestroyGLShaderManager(this.f571h);
            this.f571h = 0L;
        }
    }

    /* JADX INFO: compiled from: GlShaderManager.java */
    public static class e extends dd {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f592a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f593b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f594c;

        e(String str) {
            if (a(str)) {
                this.f592a = c("aMVPMatrix");
                this.f594c = c("aColor");
                this.f593b = b("aVertex");
            }
        }
    }

    /* JADX INFO: compiled from: GlShaderManager.java */
    public static class f extends e {
        f(String str) {
            super(str);
        }
    }

    /* JADX INFO: compiled from: GlShaderManager.java */
    public static class c extends dd {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f582a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f583b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f584c;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f585g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f586h;

        c(String str) {
            if (a(str)) {
                this.f582a = c("aMVP");
                this.f583b = b("aVertex");
                this.f584c = b("aTextureCoord");
                this.f585g = c("aTransform");
                this.f586h = c("aColor");
            }
        }
    }

    /* JADX INFO: compiled from: GlShaderManager.java */
    public static class g extends dd {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f595a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f596b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f597c;

        g(String str) {
            if (a(str)) {
                this.f595a = c("aMVP");
                this.f596b = b("aVertex");
                this.f597c = b("aTextureCoord");
            }
        }
    }

    /* JADX INFO: compiled from: GlShaderManager.java */
    public static class d extends dd {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f587a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f588b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f589c;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f590g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f591h;

        d(String str) {
            if (a(str)) {
                this.f587a = c("aMVP");
                dy.a("getUniform");
                this.f591h = c("aMapBearing");
                this.f588b = b("aVertex");
                this.f589c = b("aTextureCoord");
                this.f590g = b("aBearingTiltAlpha");
            }
        }
    }

    /* JADX INFO: compiled from: GlShaderManager.java */
    public static class a extends dd {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        String f572a = "precision highp float;\n        attribute vec4 aVertex;\n        attribute vec2 aTexture;\n        uniform vec4 aMapAttribute;\n        uniform mat4 aMVPMatrix;\n        uniform mat4 aProjection;\n        uniform vec3 aInstanceOffset[" + at.f183a + "];\n        varying vec2 texture;\n        mat4 rotationMatrix(vec3 axis, float angle)\n        {\n           axis = normalize(axis);\n           float s = sin(angle);\n           float c = cos(angle);\n           float oc = 1.0 - c;\n           return mat4(oc * axis.x * axis.x + c,           oc * axis.x * axis.y - axis.z * s,  oc * axis.z * axis.x + axis.y * s,  0.0,\n                 oc * axis.x * axis.y + axis.z * s,  oc * axis.y * axis.y + c,           oc * axis.y * axis.z - axis.x * s,  0.0,\n                 oc * axis.z * axis.x - axis.y * s,  oc * axis.y * axis.z + axis.x * s,  oc * axis.z * axis.z + c,           0.0,\n                 0.0,                                0.0,                                0.0,                                1.0);\n        }\n        void main(){\n            int instance = int(aVertex.w);\n            vec3 offset_value = aInstanceOffset[instance];\n            mat4 marker_rotate_mat4 = rotationMatrix(vec3(0,0,1.0), offset_value.z * 0.01745);\n            float map_rotate = -aMapAttribute.z * 0.01745;\n            float map_tilt = aMapAttribute.w * 0.01745;\n            //tilt旋转矩阵\n            mat4 map_tilt_mat4 = rotationMatrix(vec3(1,0,0), map_tilt);\n            //bearing旋转矩阵\n            mat4 map_rotate_mat4 = rotationMatrix(vec3(0,0,1), map_rotate);\n                 \n            //旋转图片\n            vec4 pos_1 = marker_rotate_mat4 * vec4(aVertex.xy * aMapAttribute.xy, 0,1);\n                  \n            //让marker站立，tilt旋转之后z轴值有可能不是0\n            vec4 pos_2 =  map_tilt_mat4 * pos_1;\n                  \n            //旋转 bearing\n            vec4 pos_3 =  map_rotate_mat4 * pos_2;\n            gl_Position = aProjection * aMVPMatrix * vec4(pos_3.xy + offset_value.xy, pos_3.z, 1.0);\n            texture = aTexture;\n        }";

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f573b = "        precision highp float;\n        varying vec2 texture;\n        uniform sampler2D aTextureUnit0;\n        void main(){\n            vec4 tempColor = texture2D(aTextureUnit0, texture);\n            gl_FragColor = tempColor;\n        }";

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f574c;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f575g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f576h;
        public int i;
        public int j;
        public int k;

        public a() {
            if (a(this.f572a, this.f573b)) {
                this.f575g = c("aMVPMatrix");
                this.k = c("aProjection");
                this.i = c("aInstanceOffset");
                this.j = c("aMapAttribute");
                this.f574c = b("aVertex");
                this.f576h = b("aTexture");
            }
        }
    }

    /* JADX INFO: compiled from: GlShaderManager.java */
    public static class b extends dd {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        String f577a = "precision highp float;\n        attribute vec3 aVertex;//顶点数组,三维坐标\n        attribute vec2 aTexture;\n        uniform mat4 aMVPMatrix;//mvp矩阵\n        varying vec2 texture;//\n        void main(){\n            gl_Position = aMVPMatrix * vec4(aVertex, 1.0);\n            texture = aTexture;\n        }";

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f578b = "        precision highp float;\n        varying vec2 texture;//\n        uniform sampler2D aTextureUnit0;//纹理id\n        void main(){\n            gl_FragColor = texture2D(aTextureUnit0, texture);\n        }";

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f579c;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f580g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f581h;

        public b() {
            if (a(this.f577a, this.f578b)) {
                this.f579c = GLES20.glGetAttribLocation(this.f561d, "aVertex");
                this.f581h = GLES20.glGetAttribLocation(this.f561d, "aTexture");
                this.f580g = GLES20.glGetUniformLocation(this.f561d, "aMVPMatrix");
            }
        }
    }
}