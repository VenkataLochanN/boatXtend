package com.baidu.mapapi.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.common.SysOSUtil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class BitmapDescriptorFactory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final /* synthetic */ boolean f2789a = !BitmapDescriptorFactory.class.desiredAssertionStatus();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f2790b = "BaiduMapSDK-" + BitmapDescriptorFactory.class.getSimpleName();

    public static BitmapDescriptor fromAsset(String str) {
        Context context = BMapManager.getContext();
        if (context == null) {
            return null;
        }
        try {
            Bitmap bitmapA = com.baidu.mapsdkplatform.comapi.commonutils.a.a(str, context);
            BitmapDescriptor bitmapDescriptorFromBitmap = fromBitmap(bitmapA);
            if (!f2789a && bitmapA == null) {
                throw new AssertionError();
            }
            bitmapA.recycle();
            return bitmapDescriptorFromBitmap;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x005f A[Catch: Exception -> 0x0063, TRY_LEAVE, TryCatch #0 {Exception -> 0x0063, blocks: (B:5:0x0008, B:8:0x000f, B:10:0x0017, B:11:0x0031, B:16:0x005a, B:18:0x005f, B:14:0x003a, B:15:0x0055), top: B:23:0x0008 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.baidu.mapapi.map.BitmapDescriptor fromAssetWithDpi(java.lang.String r9) {
        /*
            android.content.Context r0 = com.baidu.mapapi.BMapManager.getContext()
            r1 = 0
            if (r0 != 0) goto L8
            return r1
        L8:
            android.graphics.Bitmap r9 = com.baidu.mapsdkplatform.comapi.commonutils.a.a(r9, r0)     // Catch: java.lang.Exception -> L63
            if (r9 != 0) goto Lf
            return r1
        Lf:
            int r0 = com.baidu.mapapi.common.SysOSUtil.getDensityDpi()     // Catch: java.lang.Exception -> L63
            r2 = 480(0x1e0, float:6.73E-43)
            if (r0 <= r2) goto L36
            android.graphics.Matrix r7 = new android.graphics.Matrix     // Catch: java.lang.Exception -> L63
            r7.<init>()     // Catch: java.lang.Exception -> L63
            r0 = 1073741824(0x40000000, float:2.0)
            r7.postScale(r0, r0)     // Catch: java.lang.Exception -> L63
            r3 = 0
            r4 = 0
            int r5 = r9.getWidth()     // Catch: java.lang.Exception -> L63
            int r6 = r9.getHeight()     // Catch: java.lang.Exception -> L63
            r8 = 1
            r2 = r9
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createBitmap(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Exception -> L63
        L31:
            com.baidu.mapapi.map.BitmapDescriptor r2 = fromBitmap(r0)     // Catch: java.lang.Exception -> L63
            goto L5a
        L36:
            r2 = 320(0x140, float:4.48E-43)
            if (r0 <= r2) goto L55
            android.graphics.Matrix r7 = new android.graphics.Matrix     // Catch: java.lang.Exception -> L63
            r7.<init>()     // Catch: java.lang.Exception -> L63
            r0 = 1069547520(0x3fc00000, float:1.5)
            r7.postScale(r0, r0)     // Catch: java.lang.Exception -> L63
            r3 = 0
            r4 = 0
            int r5 = r9.getWidth()     // Catch: java.lang.Exception -> L63
            int r6 = r9.getHeight()     // Catch: java.lang.Exception -> L63
            r8 = 1
            r2 = r9
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createBitmap(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Exception -> L63
            goto L31
        L55:
            com.baidu.mapapi.map.BitmapDescriptor r2 = fromBitmap(r9)     // Catch: java.lang.Exception -> L63
            r0 = r1
        L5a:
            r9.recycle()     // Catch: java.lang.Exception -> L63
            if (r0 == 0) goto L62
            r0.recycle()     // Catch: java.lang.Exception -> L63
        L62:
            return r2
        L63:
            r9 = move-exception
            r9.printStackTrace()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapapi.map.BitmapDescriptorFactory.fromAssetWithDpi(java.lang.String):com.baidu.mapapi.map.BitmapDescriptor");
    }

    public static BitmapDescriptor fromBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return new BitmapDescriptor(bitmap);
    }

    public static BitmapDescriptor fromFile(String str) {
        Context context;
        String str2;
        String str3;
        if (str == null || str.equals("") || (context = BMapManager.getContext()) == null) {
            return null;
        }
        try {
            FileInputStream fileInputStreamOpenFileInput = context.openFileInput(str);
            Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(fileInputStreamOpenFileInput);
            fileInputStreamOpenFileInput.close();
            if (bitmapDecodeStream != null) {
                BitmapDescriptor bitmapDescriptorFromBitmap = fromBitmap(bitmapDecodeStream);
                bitmapDecodeStream.recycle();
                return bitmapDescriptorFromBitmap;
            }
        } catch (FileNotFoundException e2) {
            e = e2;
            str2 = f2790b;
            str3 = "FileNotFoundException happened";
            Log.e(str2, str3, e);
        } catch (IOException e3) {
            e = e3;
            str2 = f2790b;
            str3 = "IOException happened";
            Log.e(str2, str3, e);
        }
        return null;
    }

    public static BitmapDescriptor fromFileWithDpi(String str, int i) {
        Context context;
        String str2;
        String str3;
        if (str == null || str.equals("") || (context = BMapManager.getContext()) == null) {
            return null;
        }
        try {
            FileInputStream fileInputStreamOpenFileInput = context.openFileInput(str);
            Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(fileInputStreamOpenFileInput);
            fileInputStreamOpenFileInput.close();
            if (bitmapDecodeStream != null) {
                if (i <= 0) {
                    i = SysOSUtil.getDensityDpi();
                }
                bitmapDecodeStream.setDensity(i);
                BitmapDescriptor bitmapDescriptorFromBitmap = fromBitmap(bitmapDecodeStream);
                bitmapDecodeStream.recycle();
                return bitmapDescriptorFromBitmap;
            }
        } catch (FileNotFoundException e2) {
            e = e2;
            str2 = f2790b;
            str3 = "FileNotFoundException happened";
            Log.e(str2, str3, e);
        } catch (IOException e3) {
            e = e3;
            str2 = f2790b;
            str3 = "IOException happened";
            Log.e(str2, str3, e);
        }
        return null;
    }

    public static BitmapDescriptor fromPath(String str) {
        Bitmap bitmapDecodeFile;
        if (TextUtils.isEmpty(str) || (bitmapDecodeFile = BitmapFactory.decodeFile(str)) == null) {
            return null;
        }
        BitmapDescriptor bitmapDescriptorFromBitmap = fromBitmap(bitmapDecodeFile);
        bitmapDecodeFile.recycle();
        return bitmapDescriptorFromBitmap;
    }

    public static BitmapDescriptor fromPathWithDpi(String str, int i) {
        Bitmap bitmapDecodeFile;
        if (TextUtils.isEmpty(str) || (bitmapDecodeFile = BitmapFactory.decodeFile(str)) == null) {
            return null;
        }
        if (i <= 0) {
            i = SysOSUtil.getDensityDpi();
        }
        bitmapDecodeFile.setDensity(i);
        BitmapDescriptor bitmapDescriptorFromBitmap = fromBitmap(bitmapDecodeFile);
        bitmapDecodeFile.recycle();
        return bitmapDescriptorFromBitmap;
    }

    public static BitmapDescriptor fromResource(int i) {
        Bitmap bitmapDecodeResource;
        Context context = BMapManager.getContext();
        if (context == null || (bitmapDecodeResource = BitmapFactory.decodeResource(context.getResources(), i)) == null) {
            return null;
        }
        BitmapDescriptor bitmapDescriptorFromBitmap = fromBitmap(bitmapDecodeResource);
        bitmapDecodeResource.recycle();
        return bitmapDescriptorFromBitmap;
    }

    public static BitmapDescriptor fromResourceWithDpi(int i, int i2) {
        Bitmap bitmapDecodeResource;
        Context context = BMapManager.getContext();
        if (context == null || (bitmapDecodeResource = BitmapFactory.decodeResource(context.getResources(), i)) == null) {
            return null;
        }
        if (i2 <= 0) {
            i2 = SysOSUtil.getDensityDpi();
        }
        bitmapDecodeResource.setDensity(i2);
        BitmapDescriptor bitmapDescriptorFromBitmap = fromBitmap(bitmapDecodeResource);
        bitmapDecodeResource.recycle();
        return bitmapDescriptorFromBitmap;
    }

    public static BitmapDescriptor fromView(View view) {
        if (view == null) {
            return null;
        }
        view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap drawingCache = view.getDrawingCache();
        BitmapDescriptor bitmapDescriptorFromBitmap = fromBitmap(drawingCache);
        if (drawingCache != null) {
            drawingCache.recycle();
        }
        view.destroyDrawingCache();
        return bitmapDescriptorFromBitmap;
    }

    public static BitmapDescriptor fromViewWithDpi(View view, int i) {
        if (view == null) {
            return null;
        }
        view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap drawingCache = view.getDrawingCache();
        if (drawingCache == null) {
            Log.e(f2790b, "Get bitmap failed");
            return null;
        }
        if (i <= 0) {
            i = SysOSUtil.getDensityDpi();
        }
        drawingCache.setDensity(i);
        BitmapDescriptor bitmapDescriptorFromBitmap = fromBitmap(drawingCache);
        if (drawingCache != null) {
            drawingCache.recycle();
        }
        view.destroyDrawingCache();
        return bitmapDescriptorFromBitmap;
    }
}