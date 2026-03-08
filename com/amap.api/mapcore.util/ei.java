package com.amap.api.mapcore.util;

import android.graphics.Bitmap;

/* JADX INFO: compiled from: PureScreenCheckTool.java */
/* JADX INFO: loaded from: classes.dex */
public class ei {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f734a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static boolean f735b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static boolean f736c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f737d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f738e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f739f = 20;

    public static void a(boolean z) {
        f734a = z;
    }

    public static boolean a() {
        return f734a;
    }

    public static void b(boolean z) {
        f735b = z;
    }

    public static boolean b() {
        return f735b;
    }

    public static void c(boolean z) {
        f736c = z;
    }

    public static boolean c() {
        return f736c;
    }

    public boolean d() {
        return this.f737d;
    }

    public void e() {
        this.f738e++;
    }

    public boolean f() {
        return this.f738e >= this.f739f;
    }

    public boolean a(Bitmap bitmap) {
        if (bitmap != null) {
            try {
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                int i = -1;
                for (int i2 = (int) (width / 4.0f); i2 < (width * 3) / 4.0f; i2++) {
                    for (int i3 = (int) (height / 4.0f); i3 < (height * 3) / 4.0f; i3++) {
                        int pixel = bitmap.getPixel(i2, i3);
                        if (i == -1) {
                            i = pixel;
                        }
                        if (pixel != i) {
                            return false;
                        }
                        if (pixel != -16777216) {
                            return false;
                        }
                    }
                }
            } finally {
                try {
                } finally {
                }
            }
        }
        return true;
    }

    public void g() {
        hn.c(new Exception("BlackScreen"), "PureScreenCheckTool", "uploadInfo");
    }
}