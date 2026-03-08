package com.baidu.location.indoor.mapversion;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class IndoorJni {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f2603a = false;

    static {
        try {
            System.loadLibrary("indoor");
            f2603a = true;
            System.err.println("load indoor lib success.");
        } catch (Throwable th) {
            System.err.println("Cannot load indoor lib");
            th.printStackTrace();
        }
    }

    public static String a(File file, Bitmap bitmap, double d2, float[] fArr) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[width * height];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        File file2 = new File(file, "resize.jpg");
        try {
            if ((file2.exists() && !file2.delete()) || !file2.createNewFile()) {
                return null;
            }
            preprocessImage(iArr, width, height, d2, fArr[0], fArr[1], fArr[2], file2.getAbsolutePath());
            return file2.getAbsolutePath();
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean a(String str, File file) {
        try {
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str);
            int width = bitmapDecodeFile.getWidth();
            int height = bitmapDecodeFile.getHeight();
            int[] iArr = new int[width * height];
            bitmapDecodeFile.getPixels(iArr, 0, width, 0, 0, width, height);
            File file2 = new File(file, "compress.jpg");
            if ((file2.exists() && !file2.delete()) || !file2.createNewFile()) {
                return false;
            }
            compressImage(iArr, width, height, file2.getAbsolutePath());
            return true;
        } catch (Error | Exception unused) {
            return false;
        }
    }

    public static native void compressImage(int[] iArr, int i, int i2, String str);

    public static native void initPf();

    public static native float[] pgo();

    public static native void phs(int i, float f2, float f3, float f4, long j);

    public static native void preprocessImage(int[] iArr, int i, int i2, double d2, double d3, double d4, double d5, String str);

    public static native void resetPf();

    public static native double[] setPfDr(double d2, double d3, long j);

    public static native void setPfGeoMap(double[][] dArr, String str, int i, int i2);

    public static native void setPfGeomag(double d2);

    public static native double[] setPfGps(double d2, double d3, double d4, double d5, double d6, long j);

    public static native void setPfRdnt(String str, short[][] sArr, double d2, double d3, int i, int i2, double d4, double d5);

    public static native double[] setPfWf(double d2, double d3, double d4, long j);

    public static native void stopPdr();
}