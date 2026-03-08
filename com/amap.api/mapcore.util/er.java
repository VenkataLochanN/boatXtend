package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.os.Build;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amap.api.mapcore.util.gs;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.model.BaseHoleOptions;
import com.amap.api.maps.model.CircleHoleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.PolygonHoleOptions;
import com.amap.api.maps.utils.SpatialRelationUtil;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.autonavi.amap.api.mapcore.IGLMapState;
import com.autonavi.amap.mapcore.AbstractCameraUpdateMessage;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.VirtualEarthProjection;
import com.autonavi.amap.mapcore.interfaces.IMapConfig;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.autonavi.base.amap.mapcore.FPoint;
import com.autonavi.base.amap.mapcore.FPoint3;
import com.autonavi.base.amap.mapcore.FileUtil;
import com.ido.life.location.MapHelper;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: Util.java */
/* JADX INFO: loaded from: classes.dex */
public class er {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static FPoint[] f762a = {FPoint.obtain(), FPoint.obtain(), FPoint.obtain(), FPoint.obtain()};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static List<Float> f763b = new ArrayList(4);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static List<Float> f764c = new ArrayList(4);

    public static double a(double d2, double d3, double d4, double d5, double d6, double d7) {
        return ((d4 - d2) * (d7 - d3)) - ((d6 - d2) * (d5 - d3));
    }

    public static boolean a(double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9) {
        double d10 = d4 - d2;
        double d11 = d9 - d7;
        double d12 = d5 - d3;
        double d13 = d8 - d6;
        double d14 = (d10 * d11) - (d12 * d13);
        if (d14 != 0.0d) {
            double d15 = d3 - d7;
            double d16 = d2 - d6;
            double d17 = ((d13 * d15) - (d11 * d16)) / d14;
            double d18 = ((d15 * d10) - (d16 * d12)) / d14;
            if (d17 >= 0.0d && d17 <= 1.0d && d18 >= 0.0d && d18 <= 1.0d) {
                return true;
            }
        }
        return false;
    }

    public static Bitmap a(Context context, String str) {
        try {
            InputStream inputStreamOpen = el.a(context).open(str);
            Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpen);
            inputStreamOpen.close();
            return bitmapDecodeStream;
        } catch (Throwable th) {
            hn.c(th, "Util", "fromAsset");
            a(th);
            return null;
        }
    }

    public static void a(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    public static String a(String str, Object obj) {
        return str + "=" + String.valueOf(obj);
    }

    public static float a(IMapConfig iMapConfig, float f2, float f3) {
        boolean z;
        if (iMapConfig != null) {
            boolean zIsAbroadEnable = iMapConfig.isAbroadEnable();
            z = iMapConfig.getAbroadState() != 1;
            z = zIsAbroadEnable;
        } else {
            z = false;
        }
        if (z && z) {
            float f4 = 40;
            return f2 > f4 ? f4 : f2;
        }
        if (f2 <= 40.0f) {
            return f2;
        }
        float f5 = f3 > 15.0f ? f3 <= 16.0f ? 56 : f3 <= 17.0f ? 66 : f3 <= 18.0f ? 74 : f3 <= 18.0f ? 78 : 80 : 40;
        return f2 > f5 ? f5 : f2;
    }

    public static float a(IMapConfig iMapConfig, float f2) {
        if (iMapConfig != null) {
            if (f2 > iMapConfig.getMaxZoomLevel()) {
                return iMapConfig.getMaxZoomLevel();
            }
            return f2 < iMapConfig.getMinZoomLevel() ? iMapConfig.getMinZoomLevel() : f2;
        }
        if (f2 > 20.0f) {
            return 20.0f;
        }
        if (f2 < 3.0f) {
            return 3.0f;
        }
        return f2;
    }

    public static FloatBuffer a(float[] fArr) {
        try {
            ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(fArr.length * 4);
            byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
            FloatBuffer floatBufferAsFloatBuffer = byteBufferAllocateDirect.asFloatBuffer();
            floatBufferAsFloatBuffer.put(fArr);
            floatBufferAsFloatBuffer.position(0);
            return floatBufferAsFloatBuffer;
        } catch (Throwable th) {
            hn.c(th, "Util", "makeFloatBuffer1");
            th.printStackTrace();
            return null;
        }
    }

    public static FloatBuffer a(float[] fArr, FloatBuffer floatBuffer) {
        try {
            floatBuffer.clear();
            floatBuffer.put(fArr);
            floatBuffer.position(0);
            return floatBuffer;
        } catch (Throwable th) {
            hn.c(th, "Util", "makeFloatBuffer2");
            th.printStackTrace();
            return null;
        }
    }

    public static int a(int i, int i2) {
        return a(0, Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888), true);
    }

    public static int a(Bitmap bitmap) {
        return a(bitmap, false);
    }

    public static int a(Bitmap bitmap, boolean z) {
        return a(0, bitmap, z);
    }

    public static int a(int i, Bitmap bitmap, boolean z) {
        int iB = b(i, bitmap, z);
        b(bitmap);
        return iB;
    }

    public static int b(int i, Bitmap bitmap, boolean z) {
        if (bitmap == null || bitmap.isRecycled()) {
            return 0;
        }
        if (i == 0) {
            int[] iArr = {0};
            GLES20.glGenTextures(1, iArr, 0);
            i = iArr[0];
        }
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, i);
        GLES20.glTexParameterf(3553, 10241, 9729.0f);
        GLES20.glTexParameterf(3553, 10240, 9729.0f);
        if (z) {
            GLES20.glTexParameterf(3553, 10242, 10497.0f);
            GLES20.glTexParameterf(3553, 10243, 10497.0f);
        } else {
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
        }
        GLUtils.texImage2D(3553, 0, bitmap, 0);
        return i;
    }

    public static int a(int i, Bitmap bitmap, int i2, int i3) {
        if (bitmap == null || bitmap.isRecycled()) {
            return 0;
        }
        if (i == 0) {
            int[] iArr = {0};
            GLES20.glGenTextures(1, iArr, 0);
            i = iArr[0];
        }
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, i);
        GLUtils.texSubImage2D(3553, 0, i2, i3, bitmap);
        return i;
    }

    public static String a(String... strArr) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String str : strArr) {
            sb.append(str);
            if (i != strArr.length - 1) {
                sb.append(AppInfo.DELIM);
            }
            i++;
        }
        return sb.toString();
    }

    public static int a(Object[] objArr) {
        return Arrays.hashCode(objArr);
    }

    public static Bitmap a(Bitmap bitmap, float f2) {
        if (bitmap == null) {
            return null;
        }
        return Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * f2), (int) (bitmap.getHeight() * f2), true);
    }

    public static String a(Context context) {
        File file = new File(FileUtil.getMapBaseStorage(context), AeUtil.ROOT_DATA_PATH_NAME);
        if (!file.exists()) {
            file.mkdir();
        }
        File file2 = new File(file.toString() + File.separator);
        if (!file2.exists()) {
            file2.mkdir();
        }
        return file.toString() + File.separator;
    }

    public static String b(Context context) {
        return FileUtil.getMapBaseStorage(context) + File.separator + AeUtil.ROOT_DATA_PATH_OLD_NAME + File.separator;
    }

    public static String c(Context context) {
        String strA = a(context);
        if (strA == null) {
            return null;
        }
        File file = new File(strA, "VMAP2");
        if (!file.exists()) {
            file.mkdir();
        }
        return file.toString() + File.separator;
    }

    public static String a(int i) {
        if (i < 1000) {
            return i + "m";
        }
        return (i / 1000) + "km";
    }

    public static boolean d(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        NetworkInfo.State state;
        return (context == null || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || (state = activeNetworkInfo.getState()) == null || state == NetworkInfo.State.DISCONNECTED || state == NetworkInfo.State.DISCONNECTING) ? false : true;
    }

    public static boolean a() {
        return Build.VERSION.SDK_INT >= 8;
    }

    public static boolean b() {
        return Build.VERSION.SDK_INT >= 9;
    }

    public static boolean c() {
        return Build.VERSION.SDK_INT >= 11;
    }

    public static boolean d() {
        return Build.VERSION.SDK_INT >= 12;
    }

    public static void b(int i) {
        GLES20.glDeleteTextures(1, new int[]{i}, 0);
    }

    public static String a(InputStream inputStream) {
        try {
            return new String(b(inputStream), "utf-8");
        } catch (Throwable th) {
            hn.c(th, "Util", "decodeAssetResData");
            th.printStackTrace();
            return null;
        }
    }

    public static byte[] b(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[2048];
        while (true) {
            int i = inputStream.read(bArr, 0, 2048);
            if (i != -1) {
                byteArrayOutputStream.write(bArr, 0, i);
            } else {
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x00eb A[Catch: IOException -> 0x00d9, TRY_ENTER, TRY_LEAVE, TryCatch #0 {IOException -> 0x00d9, blocks: (B:106:0x00eb, B:95:0x00d5, B:88:0x00c9), top: B:108:0x00c7, inners: #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0091 A[Catch: IOException -> 0x002b, TRY_ENTER, TRY_LEAVE, TryCatch #12 {IOException -> 0x002b, blocks: (B:10:0x0026, B:18:0x0037, B:61:0x0091, B:52:0x0080, B:83:0x00be, B:74:0x00ad, B:45:0x0074, B:9:0x0023), top: B:122:0x0008, inners: #1, #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00be A[Catch: IOException -> 0x002b, TRY_ENTER, TRY_LEAVE, TryCatch #12 {IOException -> 0x002b, blocks: (B:10:0x0026, B:18:0x0037, B:61:0x0091, B:52:0x0080, B:83:0x00be, B:74:0x00ad, B:45:0x0074, B:9:0x0023), top: B:122:0x0008, inners: #1, #9 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(java.io.File r7) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 239
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.er.a(java.io.File):java.lang.String");
    }

    public static boolean a(LatLng latLng, List<LatLng> list) throws Throwable {
        boolean z;
        double d2;
        double d3;
        int i;
        double d4;
        double d5;
        List<LatLng> list2 = list;
        double d6 = latLng.longitude;
        double d7 = latLng.latitude;
        double d8 = latLng.latitude;
        boolean z2 = false;
        if (list.size() < 3) {
            return false;
        }
        if (list2.get(0).equals(list2.get(list.size() - 1))) {
            z = false;
        } else {
            list2.add(list2.get(0));
            z = true;
        }
        int i2 = 0;
        int i3 = 0;
        while (i2 < list.size() - 1) {
            try {
                d2 = list2.get(i2).longitude;
                d3 = list2.get(i2).latitude;
                i = i2 + 1;
                d4 = list2.get(i).longitude;
            } catch (Throwable th) {
                th = th;
            }
            try {
                double d9 = list2.get(i).latitude;
                boolean z3 = z2;
                double d10 = d8;
                double d11 = d7;
                if (b(d6, d7, d2, d3, d4, d9)) {
                    if (z) {
                        list.remove(list.size() - 1);
                    }
                    return true;
                }
                list2 = list;
                if (Math.abs(d9 - d3) >= 1.0E-9d) {
                    if (b(d2, d3, d6, d11, 180.0d, d10)) {
                        if (d3 > d9) {
                            i3++;
                        }
                    } else if (!b(d4, d9, d6, d11, 180.0d, d10)) {
                        d5 = d6;
                        if (a(d2, d3, d4, d9, d6, d11, 180.0d, d10)) {
                            i3++;
                        }
                    } else if (d9 > d3) {
                        i3++;
                    }
                    i2 = i;
                    d6 = d5;
                    z2 = z3;
                    d8 = d10;
                    d7 = d11;
                }
                d5 = d6;
                i2 = i;
                d6 = d5;
                z2 = z3;
                d8 = d10;
                d7 = d11;
            } catch (Throwable th2) {
                th = th2;
                list2 = list;
                if (z) {
                    list2.remove(list.size() - 1);
                }
                throw th;
            }
        }
        boolean z4 = z2;
        if (i3 % 2 != 0) {
            z4 = true;
        }
        if (z) {
            list2.remove(list.size() - 1);
        }
        return z4;
    }

    public static boolean b(double d2, double d3, double d4, double d5, double d6, double d7) {
        return Math.abs(a(d2, d3, d4, d5, d6, d7)) < 1.0E-9d && (d2 - d4) * (d2 - d6) <= 0.0d && (d3 - d5) * (d3 - d7) <= 0.0d;
    }

    public static boolean a(BaseHoleOptions baseHoleOptions, LatLng latLng) {
        if (baseHoleOptions instanceof CircleHoleOptions) {
            CircleHoleOptions circleHoleOptions = (CircleHoleOptions) baseHoleOptions;
            LatLng center = circleHoleOptions.getCenter();
            return center != null && ((double) AMapUtils.calculateLineDistance(center, latLng)) <= circleHoleOptions.getRadius();
        }
        List<LatLng> points = ((PolygonHoleOptions) baseHoleOptions).getPoints();
        if (points == null || points.size() == 0) {
            return false;
        }
        return a(latLng, points);
    }

    public static List<FPoint> a(FPoint[] fPointArr, List<FPoint> list, boolean z) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList(list);
        for (byte b2 = 0; b2 < 4; b2 = (byte) (b2 + 1)) {
            arrayList.clear();
            int size = arrayList2.size();
            int i = 0;
            while (true) {
                if (i >= (z ? size : size - 1)) {
                    break;
                }
                FPoint fPoint = (FPoint) arrayList2.get(i % size);
                int i2 = i + 1;
                FPoint fPoint2 = (FPoint) arrayList2.get(i2 % size);
                if (i == 0 && a(fPoint, fPointArr[b2], fPointArr[(b2 + 1) % fPointArr.length])) {
                    arrayList.add(fPoint);
                }
                int i3 = b2 + 1;
                if (a(fPoint, fPointArr[b2], fPointArr[i3 % fPointArr.length])) {
                    if (a(fPoint2, fPointArr[b2], fPointArr[i3 % fPointArr.length])) {
                        arrayList.add(fPoint2);
                    } else {
                        arrayList.add(a(fPointArr[b2], fPointArr[i3 % fPointArr.length], fPoint, fPoint2));
                    }
                } else if (a(fPoint2, fPointArr[b2], fPointArr[i3 % fPointArr.length])) {
                    arrayList.add(a(fPointArr[b2], fPointArr[i3 % fPointArr.length], fPoint, fPoint2));
                    arrayList.add(fPoint2);
                }
                i = i2;
            }
            arrayList2.clear();
            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                arrayList2.add(arrayList.get(i4));
            }
        }
        return arrayList2;
    }

    public static List<FPoint> b(FPoint[] fPointArr, List<FPoint> list, boolean z) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList(list);
        for (byte b2 = 0; b2 < 4; b2 = (byte) (b2 + 1)) {
            arrayList.clear();
            int size = arrayList2.size();
            int i = 0;
            while (true) {
                if (i >= (z ? size : size - 1)) {
                    break;
                }
                FPoint3 fPoint3 = (FPoint3) arrayList2.get(i % size);
                int i2 = i + 1;
                FPoint3 fPoint32 = (FPoint3) arrayList2.get(i2 % size);
                if (i == 0 && a(fPoint3, fPointArr[b2], fPointArr[(b2 + 1) % fPointArr.length])) {
                    arrayList.add(fPoint3);
                }
                int i3 = b2 + 1;
                if (a(fPoint3, fPointArr[b2], fPointArr[i3 % fPointArr.length])) {
                    if (a(fPoint32, fPointArr[b2], fPointArr[i3 % fPointArr.length])) {
                        arrayList.add(fPoint32);
                    } else {
                        arrayList.add(a(fPointArr[b2], fPointArr[i3 % fPointArr.length], fPoint3, fPoint32, fPoint32.colorIndex));
                    }
                } else if (a(fPoint32, fPointArr[b2], fPointArr[i3 % fPointArr.length])) {
                    arrayList.add(a(fPointArr[b2], fPointArr[i3 % fPointArr.length], fPoint3, fPoint32, fPoint3.colorIndex));
                    arrayList.add(fPoint32);
                }
                i = i2;
            }
            arrayList2.clear();
            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                arrayList2.add(arrayList.get(i4));
            }
        }
        return arrayList2;
    }

    public static FPoint[] a(IAMapDelegate iAMapDelegate, boolean z) {
        int i;
        int i2;
        float skyHeight = iAMapDelegate.getSkyHeight();
        if (z) {
            i = 100;
            i2 = 10;
        } else {
            i = 0;
            i2 = 0;
        }
        FPoint fPointObtain = FPoint.obtain();
        int i3 = -i;
        int i4 = (int) (skyHeight - i2);
        iAMapDelegate.pixel2Map(i3, i4, fPointObtain);
        f762a[0].set(fPointObtain.x, fPointObtain.y);
        FPoint fPointObtain2 = FPoint.obtain();
        iAMapDelegate.pixel2Map(iAMapDelegate.getMapWidth() + i, i4, fPointObtain2);
        f762a[1].set(fPointObtain2.x, fPointObtain2.y);
        FPoint fPointObtain3 = FPoint.obtain();
        iAMapDelegate.pixel2Map(iAMapDelegate.getMapWidth() + i, iAMapDelegate.getMapHeight() + i, fPointObtain3);
        f762a[2].set(fPointObtain3.x, fPointObtain3.y);
        FPoint fPointObtain4 = FPoint.obtain();
        iAMapDelegate.pixel2Map(i3, iAMapDelegate.getMapHeight() + i, fPointObtain4);
        f762a[3].set(fPointObtain4.x, fPointObtain4.y);
        fPointObtain.recycle();
        fPointObtain2.recycle();
        fPointObtain3.recycle();
        fPointObtain4.recycle();
        return f762a;
    }

    private static FPoint3 a(FPoint fPoint, FPoint fPoint2, FPoint3 fPoint3, FPoint3 fPoint32, int i) {
        FPoint3 fPoint33 = new FPoint3(0.0f, 0.0f, i);
        double d2 = ((fPoint2.y - fPoint.y) * (fPoint.x - fPoint3.x)) - ((fPoint2.x - fPoint.x) * (fPoint.y - fPoint3.y));
        double d3 = ((fPoint2.y - fPoint.y) * (fPoint32.x - fPoint3.x)) - ((fPoint2.x - fPoint.x) * (fPoint32.y - fPoint3.y));
        fPoint33.x = (float) (((double) fPoint3.x) + ((((double) (fPoint32.x - fPoint3.x)) * d2) / d3));
        fPoint33.y = (float) (((double) fPoint3.y) + ((((double) (fPoint32.y - fPoint3.y)) * d2) / d3));
        return fPoint33;
    }

    private static FPoint a(FPoint fPoint, FPoint fPoint2, FPoint fPoint3, FPoint fPoint4) {
        FPoint fPointObtain = FPoint.obtain(0.0f, 0.0f);
        double d2 = ((fPoint2.y - fPoint.y) * (fPoint.x - fPoint3.x)) - ((fPoint2.x - fPoint.x) * (fPoint.y - fPoint3.y));
        double d3 = ((fPoint2.y - fPoint.y) * (fPoint4.x - fPoint3.x)) - ((fPoint2.x - fPoint.x) * (fPoint4.y - fPoint3.y));
        fPointObtain.x = (float) (((double) fPoint3.x) + ((((double) (fPoint4.x - fPoint3.x)) * d2) / d3));
        fPointObtain.y = (float) (((double) fPoint3.y) + ((((double) (fPoint4.y - fPoint3.y)) * d2) / d3));
        return fPointObtain;
    }

    public static boolean a(FPoint fPoint, FPoint[] fPointArr) {
        if (fPointArr == null) {
            return false;
        }
        byte b2 = 0;
        while (b2 < fPointArr.length) {
            FPoint fPoint2 = fPointArr[b2];
            int i = b2 + 1;
            if (!a(fPoint, fPoint2, fPointArr[i % fPointArr.length])) {
                return false;
            }
            b2 = (byte) i;
        }
        return true;
    }

    private static boolean a(FPoint fPoint, FPoint fPoint2, FPoint fPoint3) {
        return ((double) (((fPoint3.x - fPoint2.x) * (fPoint.y - fPoint2.y)) - ((fPoint.x - fPoint2.x) * (fPoint3.y - fPoint2.y)))) >= 0.0d;
    }

    public static List<IPoint> a(IPoint[] iPointArr, List<IPoint> list, boolean z) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList(list);
        for (byte b2 = 0; b2 < 4; b2 = (byte) (b2 + 1)) {
            arrayList.clear();
            int size = arrayList2.size();
            int i = 0;
            while (true) {
                if (i >= (z ? size : size - 1)) {
                    break;
                }
                IPoint iPoint = (IPoint) arrayList2.get(i % size);
                int i2 = i + 1;
                IPoint iPoint2 = (IPoint) arrayList2.get(i2 % size);
                if (i == 0 && a(iPoint, iPointArr[b2], iPointArr[(b2 + 1) % iPointArr.length])) {
                    arrayList.add(iPoint);
                }
                int i3 = b2 + 1;
                if (a(iPoint, iPointArr[b2], iPointArr[i3 % iPointArr.length])) {
                    if (a(iPoint2, iPointArr[b2], iPointArr[i3 % iPointArr.length])) {
                        arrayList.add(iPoint2);
                    } else {
                        arrayList.add(a(iPointArr[b2], iPointArr[i3 % iPointArr.length], iPoint, iPoint2));
                    }
                } else if (a(iPoint2, iPointArr[b2], iPointArr[i3 % iPointArr.length])) {
                    arrayList.add(a(iPointArr[b2], iPointArr[i3 % iPointArr.length], iPoint, iPoint2));
                    arrayList.add(iPoint2);
                }
                i = i2;
            }
            arrayList2.clear();
            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                arrayList2.add(arrayList.get(i4));
            }
        }
        return arrayList2;
    }

    private static IPoint a(IPoint iPoint, IPoint iPoint2, IPoint iPoint3, IPoint iPoint4) {
        IPoint iPointObtain = IPoint.obtain(0, 0);
        double d2 = (((double) (iPoint2.y - iPoint.y)) * ((double) (iPoint.x - iPoint3.x))) - (((double) (iPoint2.x - iPoint.x)) * ((double) (iPoint.y - iPoint3.y)));
        double d3 = (((double) (iPoint2.y - iPoint.y)) * ((double) (iPoint4.x - iPoint3.x))) - (((double) (iPoint2.x - iPoint.x)) * ((double) (iPoint4.y - iPoint3.y)));
        iPointObtain.x = (int) (((double) iPoint3.x) + ((((double) (iPoint4.x - iPoint3.x)) * d2) / d3));
        iPointObtain.y = (int) (((double) iPoint3.y) + ((((double) (iPoint4.y - iPoint3.y)) * d2) / d3));
        return iPointObtain;
    }

    public static boolean a(List<IPoint> list, int i, int i2) {
        if (i2 < 3) {
            return false;
        }
        int i3 = i2 - 1;
        double d2 = 0.0d;
        for (int i4 = 0; i4 < i2; i4++) {
            IPoint iPoint = list.get(i3);
            IPoint iPoint2 = list.get(i4);
            d2 += ((((double) iPoint.x) / 1000000.0d) * (((double) iPoint2.y) / 1000000.0d)) - ((((double) iPoint2.x) / 1000000.0d) * (((double) iPoint.y) / 1000000.0d));
            i3 = i4;
        }
        return d2 < 0.0d;
    }

    private static boolean a(IPoint iPoint, IPoint iPoint2, IPoint iPoint3) {
        return a(iPoint.x, iPoint.y, iPoint2, iPoint3);
    }

    private static boolean a(int i, int i2, IPoint iPoint, IPoint iPoint2) {
        return (((double) (iPoint2.x - iPoint.x)) * ((double) (i2 - iPoint.y))) - (((double) (i - iPoint.x)) * ((double) (iPoint2.y - iPoint.y))) >= 0.0d;
    }

    public static Bitmap a(int i, int i2, int i3, int i4) {
        int i5 = i3 * i4;
        try {
            int[] iArr = new int[i5];
            int[] iArr2 = new int[i5];
            IntBuffer intBufferWrap = IntBuffer.wrap(iArr);
            intBufferWrap.position(0);
            GLES20.glReadPixels(i, i2, i3, i4, 6408, 5121, intBufferWrap);
            for (int i6 = 0; i6 < i4; i6++) {
                for (int i7 = 0; i7 < i3; i7++) {
                    int i8 = iArr[(i6 * i3) + i7];
                    iArr2[(((i4 - i6) - 1) * i3) + i7] = (i8 & MapHelper.Standard_Color) | ((i8 << 16) & 16711680) | ((i8 >> 16) & 255);
                }
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i3, i4, Bitmap.Config.ARGB_8888);
            bitmapCreateBitmap.setPixels(iArr2, 0, i3, 0, 0, i3, i4);
            return bitmapCreateBitmap;
        } catch (Throwable th) {
            hn.c(th, "AMapDelegateImpGLSurfaceView", "SavePixels");
            th.printStackTrace();
            return null;
        }
    }

    public static gs e() {
        try {
            if (m.f1687e == null) {
                m.f1687e = new gs.a("3dmap", "7.6.0", m.f1685c).a(new String[]{"com.amap.api.maps", "com.amap.api.mapcore", "com.autonavi.amap.mapcore", "com.autonavi.amap", "com.autonavi.ae", "com.autonavi.base", "com.autonavi.patch", "com.amap.api.3dmap.admic", "com.amap.api.trace", "com.amap.api.trace.core"}).a("7.6.0").a();
            }
            return m.f1687e;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static void c(View view) {
        int i = 0;
        if (!(view instanceof ViewGroup)) {
            if (view instanceof TextView) {
                ((TextView) view).setHorizontallyScrolling(false);
            }
        } else {
            while (true) {
                ViewGroup viewGroup = (ViewGroup) view;
                if (i >= viewGroup.getChildCount()) {
                    return;
                }
                c(viewGroup.getChildAt(i));
                i++;
            }
        }
    }

    public static Bitmap a(View view) {
        try {
            c(view);
            view.destroyDrawingCache();
            view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
            Bitmap drawingCache = view.getDrawingCache();
            if (drawingCache != null) {
                return drawingCache.copy(Bitmap.Config.ARGB_8888, false);
            }
            return null;
        } catch (Throwable th) {
            hn.c(th, "Utils", "getBitmapFromView");
            th.printStackTrace();
            return null;
        }
    }

    public static DPoint a(LatLng latLng) {
        double d2 = (latLng.longitude / 360.0d) + 0.5d;
        double dSin = Math.sin(Math.toRadians(latLng.latitude));
        return DPoint.obtain(d2 * 1.0d, (((Math.log((dSin + 1.0d) / (1.0d - dSin)) * 0.5d) / (-6.283185307179586d)) + 0.5d) * 1.0d);
    }

    public static boolean a(Rect rect, int i, int i2) {
        return rect.contains(i, i2);
    }

    public static Pair<Float, IPoint> a(AbstractCameraUpdateMessage abstractCameraUpdateMessage, IGLMapState iGLMapState, IMapConfig iMapConfig) {
        return a(iMapConfig, Math.max(abstractCameraUpdateMessage.paddingLeft, 1), Math.max(abstractCameraUpdateMessage.paddingRight, 1), Math.max(abstractCameraUpdateMessage.paddingTop, 1), Math.max(abstractCameraUpdateMessage.paddingBottom, 1), abstractCameraUpdateMessage.bounds, abstractCameraUpdateMessage.width, abstractCameraUpdateMessage.height);
    }

    public static Pair<Float, IPoint> a(IMapConfig iMapConfig, int i, int i2, int i3, int i4, LatLngBounds latLngBounds, int i5, int i6) {
        int i7;
        float f2;
        float f3;
        int i8;
        if (latLngBounds == null || latLngBounds.northeast == null || latLngBounds.southwest == null || iMapConfig == null) {
            return null;
        }
        Point pointLatLongToPixels = VirtualEarthProjection.latLongToPixels(latLngBounds.northeast.latitude, latLngBounds.northeast.longitude, 20);
        Point pointLatLongToPixels2 = VirtualEarthProjection.latLongToPixels(latLngBounds.southwest.latitude, latLngBounds.southwest.longitude, 20);
        int i9 = pointLatLongToPixels.x - pointLatLongToPixels2.x;
        int i10 = pointLatLongToPixels2.y - pointLatLongToPixels.y;
        int i11 = i5 - (i + i2);
        int i12 = i6 - (i3 + i4);
        if (i9 < 0 && i10 < 0) {
            return null;
        }
        int i13 = i9 <= 0 ? 1 : i9;
        int i14 = i10 <= 0 ? 1 : i10;
        Pair<Float, Boolean> pairA = a(iMapConfig, pointLatLongToPixels.x, pointLatLongToPixels.y, pointLatLongToPixels2.x, pointLatLongToPixels2.y, i11 <= 0 ? 1 : i11, i12 <= 0 ? 1 : i12);
        float fFloatValue = ((Float) pairA.first).floatValue();
        boolean zBooleanValue = ((Boolean) pairA.second).booleanValue();
        float fA = a(iMapConfig.getMapZoomScale(), fFloatValue, i13);
        float fA2 = a(iMapConfig.getMapZoomScale(), fFloatValue, i14);
        if (fFloatValue >= iMapConfig.getMaxZoomLevel()) {
            i7 = (int) (pointLatLongToPixels2.x + ((((i2 - i) + fA) * i13) / (fA * 2.0f)));
            i8 = pointLatLongToPixels.y;
        } else if (zBooleanValue) {
            i7 = (int) (pointLatLongToPixels2.x + ((((i5 / 2) - i) / fA) * i13));
            i8 = pointLatLongToPixels.y;
        } else {
            i7 = (int) (pointLatLongToPixels2.x + ((((i2 - i) + fA) * i13) / (fA * 2.0f)));
            f2 = pointLatLongToPixels.y;
            f3 = (((i6 / 2) - i3) / fA2) * i14;
            return new Pair<>(Float.valueOf(fFloatValue), IPoint.obtain((int) (i7 + a(iMapConfig.getMapZoomScale(), fFloatValue, iMapConfig.getAnchorX() - (iMapConfig.getMapWidth() >> 1))), (int) (((int) (f2 + f3)) + a(iMapConfig.getMapZoomScale(), fFloatValue, iMapConfig.getAnchorY() - (iMapConfig.getMapHeight() >> 1)))));
        }
        f2 = i8;
        f3 = (((i4 - i3) + fA2) * i14) / (fA2 * 2.0f);
        return new Pair<>(Float.valueOf(fFloatValue), IPoint.obtain((int) (i7 + a(iMapConfig.getMapZoomScale(), fFloatValue, iMapConfig.getAnchorX() - (iMapConfig.getMapWidth() >> 1))), (int) (((int) (f2 + f3)) + a(iMapConfig.getMapZoomScale(), fFloatValue, iMapConfig.getAnchorY() - (iMapConfig.getMapHeight() >> 1)))));
    }

    public static double a(float f2, double d2, double d3) {
        return 20.0d - (Math.log(d3 / (d2 * ((double) f2))) / Math.log(2.0d));
    }

    private static float a(float f2, float f3, double d2) {
        return (float) (d2 / (Math.pow(2.0d, 20.0f - f3) * ((double) f2)));
    }

    private static float a(float f2, float f3, float f4) {
        return (float) (((double) f4) * Math.pow(2.0d, 20.0f - f3) * ((double) f2));
    }

    public static Pair<Float, Boolean> a(IMapConfig iMapConfig, int i, int i2, int i3, int i4, int i5, int i6) {
        float fMin;
        iMapConfig.getSZ();
        if (i == i3 && i2 == i4) {
            fMin = iMapConfig.getMaxZoomLevel();
        } else {
            float fA = (float) a(iMapConfig.getMapZoomScale(), i6, Math.abs(i4 - i2));
            float fA2 = (float) a(iMapConfig.getMapZoomScale(), i5, Math.abs(i3 - i));
            float fMin2 = Math.min(fA2, fA);
            z = fMin2 == fA2;
            fMin = Math.min(iMapConfig.getMaxZoomLevel(), Math.max(iMapConfig.getMinZoomLevel(), fMin2));
        }
        return new Pair<>(Float.valueOf(fMin), Boolean.valueOf(z));
    }

    public static float b(IMapConfig iMapConfig, int i, int i2, int i3, int i4, int i5, int i6) {
        float sz = iMapConfig.getSZ();
        if (i == i3 || i2 == i4) {
            return sz;
        }
        return Math.max((float) a(iMapConfig.getMapZoomScale(), i5, Math.abs(i3 - i)), (float) a(iMapConfig.getMapZoomScale(), i6, Math.abs(i4 - i2)));
    }

    public static boolean b(int i, int i2) {
        if (i > 0 && i2 > 0) {
            return true;
        }
        Log.w("3dmap", "the map must have a size");
        return false;
    }

    public static float a(IGLMapState iGLMapState, int i, int i2, double d2, double d3, int i3) {
        IPoint iPointObtain = IPoint.obtain();
        VirtualEarthProjection.latLongToPixels(d2, d3, 20, iPointObtain);
        float fA = a(iGLMapState, i, i2, iPointObtain.x, iPointObtain.y, i3);
        iPointObtain.recycle();
        return fA;
    }

    public static float a(IGLMapState iGLMapState, int i, int i2, int i3, int i4, int i5) {
        if (iGLMapState != null) {
            return iGLMapState.calculateMapZoomer(i, i2, i3, i4, i5);
        }
        return 3.0f;
    }

    public static synchronized int[] a(int i, int i2, int i3, int i4, IMapConfig iMapConfig, IGLMapState iGLMapState, int i5, int i6) {
        int mapWidth;
        int mapHeight;
        mapWidth = iMapConfig.getMapWidth();
        mapHeight = iMapConfig.getMapHeight();
        return new int[]{(int) Math.max(i3 + a(iMapConfig.getMapZoomScale(), iGLMapState.getMapZoomer(), iMapConfig.getAnchorX()), Math.min(i5, i - a(iMapConfig.getMapZoomScale(), iGLMapState.getMapZoomer(), mapWidth - r3))), (int) Math.max(i2 + a(iMapConfig.getMapZoomScale(), iGLMapState.getMapZoomer(), iMapConfig.getAnchorY()), Math.min(i6, i4 - a(iMapConfig.getMapZoomScale(), iGLMapState.getMapZoomer(), mapHeight - r4)))};
    }

    public static void a(Rect rect) {
        if (rect != null) {
            rect.set(Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
    }

    public static void b(Rect rect, int i, int i2) {
        if (rect != null) {
            if (i < rect.left) {
                rect.left = i;
            }
            if (i > rect.right) {
                rect.right = i;
            }
            if (i2 > rect.top) {
                rect.top = i2;
            }
            if (i2 < rect.bottom) {
                rect.bottom = i2;
            }
        }
    }

    public static byte[] a(byte[] bArr, int[] iArr) {
        try {
            int i = 0;
            Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
            int i2 = 1;
            Bitmap bitmapCopy = bitmapDecodeByteArray.copy(bitmapDecodeByteArray.getConfig(), true);
            bitmapDecodeByteArray.getWidth();
            bitmapDecodeByteArray.getHeight();
            int i3 = 6;
            int i4 = 6;
            while (i2 < 72) {
                int i5 = i3;
                int i6 = i;
                for (int i7 = 8; i7 < 12; i7++) {
                    bitmapDecodeByteArray.getPixel(i7, i2);
                    if (i2 < 4 * i4) {
                        bitmapCopy.setPixel(i7, i2, iArr[i6]);
                    } else {
                        i6++;
                        i5--;
                        i4 += i5;
                    }
                }
                i2++;
                i = i6;
                i3 = i5;
            }
            byte[] bArrC = c(bitmapCopy);
            if (bArrC == null) {
                bArrC = bArr;
            }
            b(bitmapCopy);
            b(bitmapDecodeByteArray);
            return bArrC;
        } catch (Throwable th) {
            th.printStackTrace();
            return bArr;
        }
    }

    public static byte[] a(byte[] bArr, int i) {
        return a(bArr, i, i, true);
    }

    public static byte[] a(byte[] bArr, int i, int i2, boolean z) {
        try {
            Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
            Bitmap bitmapCopy = bitmapDecodeByteArray.copy(bitmapDecodeByteArray.getConfig(), true);
            int width = bitmapDecodeByteArray.getWidth();
            int height = bitmapDecodeByteArray.getHeight();
            for (int i3 = 0; i3 < width; i3++) {
                for (int i4 = 0; i4 < height; i4++) {
                    if (i3 != 0 && i4 != 0) {
                        bitmapCopy.setPixel(i3, i4, i);
                    } else if (!z) {
                        bitmapCopy.setPixel(i3, i4, i2);
                    }
                }
            }
            byte[] bArrC = c(bitmapCopy);
            if (bArrC == null) {
                bArrC = bArr;
            }
            b(bitmapCopy);
            b(bitmapDecodeByteArray);
            return bArrC;
        } catch (Throwable th) {
            th.printStackTrace();
            return bArr;
        }
    }

    private static byte[] c(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                return byteArray;
            } catch (Throwable unused) {
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                }
                return null;
            }
        } catch (Throwable unused2) {
            byteArrayOutputStream = null;
        }
    }

    public static boolean a(List<BaseHoleOptions> list, PolygonHoleOptions polygonHoleOptions) {
        boolean zB = false;
        for (int i = 0; i < list.size(); i++) {
            BaseHoleOptions baseHoleOptions = list.get(i);
            if (baseHoleOptions instanceof PolygonHoleOptions) {
                zB = a(((PolygonHoleOptions) baseHoleOptions).getPoints(), polygonHoleOptions.getPoints());
                if (zB) {
                    return true;
                }
            } else if (baseHoleOptions instanceof CircleHoleOptions) {
                zB = b(polygonHoleOptions.getPoints(), (CircleHoleOptions) baseHoleOptions);
                if (zB) {
                    return true;
                }
            } else {
                continue;
            }
        }
        return zB;
    }

    public static boolean a(List<BaseHoleOptions> list, CircleHoleOptions circleHoleOptions) {
        boolean zA = false;
        for (int i = 0; i < list.size(); i++) {
            BaseHoleOptions baseHoleOptions = list.get(i);
            if (baseHoleOptions instanceof PolygonHoleOptions) {
                zA = b(((PolygonHoleOptions) baseHoleOptions).getPoints(), circleHoleOptions);
                if (zA) {
                    return true;
                }
            } else if ((baseHoleOptions instanceof CircleHoleOptions) && (zA = a(circleHoleOptions, (CircleHoleOptions) baseHoleOptions))) {
                return true;
            }
        }
        return zA;
    }

    public static boolean a(CircleHoleOptions circleHoleOptions, CircleHoleOptions circleHoleOptions2) {
        try {
            return ((double) AMapUtils.calculateLineDistance(circleHoleOptions2.getCenter(), circleHoleOptions.getCenter())) < circleHoleOptions.getRadius() + circleHoleOptions2.getRadius();
        } catch (Throwable th) {
            hn.c(th, "Util", "isPolygon2CircleIntersect");
            th.printStackTrace();
            return false;
        }
    }

    public static boolean b(List<LatLng> list, CircleHoleOptions circleHoleOptions) {
        int i;
        try {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < list.size(); i2++) {
                arrayList.add(list.get(i2));
            }
            arrayList.add(list.get(0));
            ArrayList arrayList2 = new ArrayList();
            int i3 = 0;
            while (i3 < arrayList.size() && (i = i3 + 1) < arrayList.size()) {
                if (circleHoleOptions.getRadius() < AMapUtils.calculateLineDistance(circleHoleOptions.getCenter(), (LatLng) arrayList.get(i3)) && circleHoleOptions.getRadius() < AMapUtils.calculateLineDistance(circleHoleOptions.getCenter(), (LatLng) arrayList.get(i))) {
                    arrayList2.clear();
                    arrayList2.add(arrayList.get(i3));
                    arrayList2.add(arrayList.get(i));
                    if (circleHoleOptions.getRadius() >= ((double) AMapUtils.calculateLineDistance(circleHoleOptions.getCenter(), (LatLng) SpatialRelationUtil.calShortestDistancePoint(arrayList2, circleHoleOptions.getCenter()).second))) {
                        return true;
                    }
                    i3 = i;
                }
                return true;
            }
        } catch (Throwable th) {
            hn.c(th, "Util", "isPolygon2CircleIntersect");
            th.printStackTrace();
        }
        return false;
    }

    public static boolean a(List<LatLng> list, List<LatLng> list2) {
        for (int i = 0; i < list2.size(); i++) {
            try {
                if (a(list2.get(i), list)) {
                    return true;
                }
            } catch (Throwable th) {
                hn.c(th, "Util", "isPolygon2PolygonIntersect");
                th.printStackTrace();
                return false;
            }
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (a(list.get(i2), list2)) {
                return true;
            }
        }
        return b(list, list2);
    }

    private static boolean b(List<LatLng> list, List<LatLng> list2) {
        int i;
        int i2;
        int i3 = 0;
        while (i3 < list.size() && (i = i3 + 1) < list.size()) {
            try {
                int i4 = 0;
                while (i4 < list2.size() && (i2 = i4 + 1) < list2.size()) {
                    boolean zA = em.a(list.get(i3), list.get(i), list2.get(i4), list2.get(i2));
                    if (zA) {
                        return zA;
                    }
                    i4 = i2;
                }
                i3 = i;
            } catch (Throwable th) {
                hn.c(th, "Util", "isSegmentsIntersect");
                th.printStackTrace();
            }
        }
        return false;
    }

    public static boolean e(Context context) {
        File file = new File(b(context));
        if (file.exists()) {
            return FileUtil.deleteFile(file);
        }
        return true;
    }

    public static float a(DPoint dPoint, DPoint dPoint2) {
        if (dPoint == null || dPoint2 == null) {
            return 0.0f;
        }
        double d2 = dPoint.x;
        double d3 = dPoint2.x;
        return (float) ((Math.atan2(dPoint2.y - dPoint.y, d3 - d2) / 3.141592653589793d) * 180.0d);
    }

    public static boolean b(List<LatLng> list, PolygonHoleOptions polygonHoleOptions) {
        boolean zA;
        if (list == null || polygonHoleOptions == null) {
            return false;
        }
        try {
            List<LatLng> points = polygonHoleOptions.getPoints();
            zA = false;
            for (int i = 0; i < points.size() && (zA = a(points.get(i), list)); i++) {
                try {
                } catch (Throwable th) {
                    th = th;
                    hn.c(th, "PolygonDelegateImp", "isPolygonInPolygon");
                    th.printStackTrace();
                }
            }
        } catch (Throwable th2) {
            th = th2;
            zA = false;
        }
        return zA;
    }

    public static boolean a(List<LatLng> list, List<BaseHoleOptions> list2, CircleHoleOptions circleHoleOptions) {
        try {
            if (b(list, circleHoleOptions)) {
                return false;
            }
            return a(list, list2, circleHoleOptions.getCenter());
        } catch (Throwable th) {
            hn.c(th, "PolygonDelegateImp", "isCircleInPolygon");
            th.printStackTrace();
            return false;
        }
    }

    public static boolean a(List<LatLng> list, List<BaseHoleOptions> list2, LatLng latLng) throws RemoteException {
        if (latLng == null) {
            return false;
        }
        if (list2 != null) {
            try {
                if (list2.size() > 0) {
                    Iterator<BaseHoleOptions> it = list2.iterator();
                    while (it.hasNext()) {
                        if (a(it.next(), latLng)) {
                            return false;
                        }
                    }
                }
            } catch (Throwable th) {
                hn.c(th, "PolygonDelegateImp", "contains");
                th.printStackTrace();
                return false;
            }
        }
        return a(latLng, list);
    }

    public static boolean a(double d2, LatLng latLng, List<BaseHoleOptions> list, PolygonHoleOptions polygonHoleOptions) {
        boolean zA = true;
        try {
            List<LatLng> points = polygonHoleOptions.getPoints();
            for (int i = 0; i < points.size() && (zA = a(d2, latLng, list, points.get(i))); i++) {
            }
        } catch (Throwable th) {
            hn.c(th, "CircleDelegateImp", "isPolygonInCircle");
            th.printStackTrace();
        }
        return zA;
    }

    public static boolean a(double d2, LatLng latLng, CircleHoleOptions circleHoleOptions) {
        try {
            return ((double) AMapUtils.calculateLineDistance(circleHoleOptions.getCenter(), latLng)) <= d2 - circleHoleOptions.getRadius();
        } catch (Throwable th) {
            hn.c(th, "CircleDelegateImp", "isCircleInCircle");
            th.printStackTrace();
            return true;
        }
    }

    public static boolean a(double d2, LatLng latLng, List<BaseHoleOptions> list, LatLng latLng2) throws RemoteException {
        if (list != null && list.size() > 0) {
            Iterator<BaseHoleOptions> it = list.iterator();
            while (it.hasNext()) {
                if (a(it.next(), latLng2)) {
                    return false;
                }
            }
        }
        return d2 >= ((double) AMapUtils.calculateLineDistance(latLng, latLng2));
    }

    public static void b(Bitmap bitmap) {
        if (bitmap == null || Build.VERSION.SDK_INT > 10 || bitmap.isRecycled()) {
            return;
        }
        bitmap.recycle();
    }

    public static void a(Throwable th) {
        try {
            if (MapsInitializer.getExceptionLogger() != null) {
                MapsInitializer.getExceptionLogger().onException(th);
            }
        } catch (Throwable unused) {
        }
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            Uri uri = Uri.parse(str);
            if (uri.getAuthority().startsWith("dualstack-")) {
                return str;
            }
            return uri.buildUpon().authority("dualstack-" + uri.getAuthority()).build().toString();
        } catch (Throwable unused) {
            return str;
        }
    }

    public static String b(View view) {
        StringBuilder sb = new StringBuilder();
        if (view != null) {
            try {
                if (view instanceof TextView) {
                    sb = new StringBuilder(((TextView) view).getText().toString());
                }
                if (view instanceof ViewGroup) {
                    int childCount = ((ViewGroup) view).getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        String strB = b(((ViewGroup) view).getChildAt(i));
                        if (!TextUtils.isEmpty(strB)) {
                            sb.append("--");
                            sb.append(strB);
                        }
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return sb.toString();
    }
}