package com.ido.common.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.realsil.sdk.dfu.DfuException;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.SoftReference;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class BitmapUtil {
    private static final String TAG = "BitmapUtil";

    public static Bitmap getBitmapFromView(View view) {
        if (view == null) {
            return null;
        }
        view.setDrawingCacheEnabled(true);
        Bitmap drawingCache = view.getDrawingCache();
        if (drawingCache != null) {
            return drawingCache;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(bitmapCreateBitmap));
        return bitmapCreateBitmap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r2v0, types: [android.graphics.Bitmap] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0033 -> B:34:0x0036). Please report as a decompilation issue!!! */
    public static void saveBitmap(Bitmap bitmap, String str) throws Throwable {
        if (bitmap == 0 || TextUtils.isEmpty(str)) {
            return;
        }
        ?? r0 = 0;
        FileOutputStream fileOutputStream = null;
        r0 = 0;
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
            r0 = r0;
        }
        try {
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(str);
                try {
                    r0 = 90;
                    bitmap.compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream2);
                    fileOutputStream2.close();
                    fileOutputStream2.flush();
                } catch (Exception e3) {
                    e = e3;
                    fileOutputStream = fileOutputStream2;
                    e.printStackTrace();
                    r0 = fileOutputStream;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                        fileOutputStream.flush();
                        r0 = fileOutputStream;
                    }
                } catch (Throwable th) {
                    th = th;
                    r0 = fileOutputStream2;
                    if (r0 != 0) {
                        try {
                            r0.close();
                            r0.flush();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception e5) {
                e = e5;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static Bitmap getMapAndViewScreenShot(Bitmap bitmap, ViewGroup viewGroup, View view, boolean z, View... viewArr) {
        if (bitmap == null || viewGroup == null || view == null || viewArr == null) {
            return null;
        }
        int width = viewGroup.getWidth();
        int height = viewGroup.getHeight();
        if (z) {
            height = 0;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                height += viewGroup.getChildAt(i).getHeight();
            }
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, view.getLeft(), view.getTop(), (Paint) null);
        }
        for (View view2 : viewArr) {
            if (view2.getVisibility() == 0) {
                view2.setDrawingCacheEnabled(true);
                canvas.drawBitmap(view2.getDrawingCache(), view2.getLeft(), view2.getTop(), (Paint) null);
            }
        }
        return bitmapCreateBitmap;
    }

    public static Bitmap getMapAndViewScreenShot2(Bitmap bitmap, ViewGroup viewGroup, View view, boolean z, View... viewArr) {
        if (bitmap == null || viewGroup == null || view == null || viewArr == null) {
            return null;
        }
        int width = viewGroup.getWidth();
        int height = viewGroup.getHeight();
        if (z) {
            height = 0;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                height += viewGroup.getChildAt(i).getHeight();
            }
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawColor(-986896);
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, view.getLeft(), view.getTop(), (Paint) null);
        }
        for (View view2 : viewArr) {
            if (view2.getVisibility() == 0) {
                new Paint().setStyle(Paint.Style.STROKE);
                canvas.drawBitmap(loadBitmapFromView(view2), view2.getLeft(), view2.getTop(), (Paint) null);
            }
        }
        return bitmapCreateBitmap;
    }

    public static Bitmap getMapScreen(Bitmap bitmap, View view, ViewGroup viewGroup) {
        if (bitmap == null || viewGroup == null || view == null) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(viewGroup.getWidth(), viewGroup.getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawColor(-986896);
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, view.getLeft(), view.getTop(), (Paint) null);
        }
        return bitmapCreateBitmap;
    }

    public static Bitmap loadBitmapFromView(View view) {
        if (view == null) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.RGB_565);
        view.draw(new Canvas(bitmapCreateBitmap));
        return bitmapCreateBitmap;
    }

    public static Bitmap readBitmapFromFile(String str, int i, int i2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        int iRound = 1;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        float f2 = options.outWidth;
        float f3 = options.outHeight;
        float f4 = i2;
        if (f3 > f4 || f2 > i) {
            if (f2 > f3) {
                iRound = Math.round(f3 / f4);
            } else {
                iRound = Math.round(f2 / i);
            }
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = iRound;
        return BitmapFactory.decodeFile(str, options);
    }

    public static Bitmap readBitmapFromFileDescriptor(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFileDescriptor(fileInputStream.getFD(), null, options);
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeFileDescriptor(fileInputStream.getFD(), null, options);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Bitmap readBitmapFromFileDescriptor(String str, int i, int i2) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            BitmapFactory.Options options = new BitmapFactory.Options();
            int iRound = 1;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFileDescriptor(fileInputStream.getFD(), null, options);
            float f2 = options.outWidth;
            float f3 = options.outHeight;
            float f4 = i2;
            if (f3 > f4 || f2 > i) {
                if (f2 > f3) {
                    iRound = Math.round(f3 / f4);
                } else {
                    iRound = Math.round(f2 / i);
                }
            }
            int i3 = 2;
            if (iRound <= 2) {
                i3 = iRound;
            }
            options.inJustDecodeBounds = false;
            options.inSampleSize = i3;
            return BitmapFactory.decodeFileDescriptor(fileInputStream.getFD(), null, options);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Drawable readBitmapFromFileDrawable(String str) {
        return Drawable.createFromPath(str);
    }

    public static Bitmap readBitmapFromInputStream(InputStream inputStream, int i, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        int iRound = 1;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);
        float f2 = options.outWidth;
        float f3 = options.outHeight;
        float f4 = i2;
        if (f3 > f4 || f2 > i) {
            if (f2 > f3) {
                iRound = Math.round(f3 / f4);
            } else {
                iRound = Math.round(f2 / i);
            }
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = iRound;
        return BitmapFactory.decodeStream(inputStream, null, options);
    }

    public static Bitmap readBitmapFromResource(Resources resources, int i, int i2, int i3) {
        InputStream inputStreamOpenRawResource = resources.openRawResource(i);
        BitmapFactory.Options options = new BitmapFactory.Options();
        int iRound = 1;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStreamOpenRawResource, null, options);
        float f2 = options.outWidth;
        float f3 = options.outHeight;
        float f4 = i3;
        if (f3 > f4 || f2 > i2) {
            if (f2 > f3) {
                iRound = Math.round(f3 / f4);
            } else {
                iRound = Math.round(f2 / i2);
            }
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = iRound;
        return BitmapFactory.decodeResource(resources, i, options);
    }

    public static byte[] bitmap2Bytes(Bitmap bitmap) {
        return new ByteArrayOutputStream().toByteArray();
    }

    public static byte[] drawable2Bytes(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        drawableToBitmap(drawable).compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Bitmap readBitmapFromByteArray(byte[] bArr, int i, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        int iRound = 1;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        float f2 = options.outWidth;
        float f3 = options.outHeight;
        float f4 = i2;
        if (f3 > f4 || f2 > i) {
            if (f2 > f3) {
                iRound = Math.round(f3 / f4);
            } else {
                iRound = Math.round(f2 / i);
            }
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = iRound;
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
    }

    public static Bitmap readBitmapFromAssetsFile(Context context, String str) throws Throwable {
        Bitmap bitmap;
        InputStream inputStream = null;
        Bitmap bitmapDecodeStream = null;
        inputStream = null;
        try {
            try {
                InputStream inputStreamOpen = context.getResources().getAssets().open(str);
                try {
                    bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpen);
                    inputStreamOpen.close();
                    if (inputStreamOpen != null) {
                        try {
                            inputStreamOpen.close();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    return bitmapDecodeStream;
                } catch (IOException e3) {
                    e = e3;
                    Bitmap bitmap2 = bitmapDecodeStream;
                    inputStream = inputStreamOpen;
                    bitmap = bitmap2;
                    e.printStackTrace();
                    if (inputStream == null) {
                        return bitmap;
                    }
                    try {
                        inputStream.close();
                        return bitmap;
                    } catch (Exception e4) {
                        e4.printStackTrace();
                        return bitmap;
                    }
                } catch (Throwable th) {
                    th = th;
                    inputStream = inputStreamOpen;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e5) {
                            e5.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e6) {
            e = e6;
            bitmap = null;
        }
    }

    public static int getBitmapSize(Bitmap bitmap) {
        if (bitmap == null) {
            return 0;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            return (bitmap.getAllocationByteCount() / 1024) / 1024;
        }
        if (Build.VERSION.SDK_INT >= 12) {
            return (bitmap.getByteCount() / 1024) / 1024;
        }
        return ((bitmap.getRowBytes() * bitmap.getHeight()) / 1024) / 1024;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.io.BufferedOutputStream] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.io.BufferedOutputStream] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8, types: [android.graphics.Bitmap$CompressFormat] */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r2v8, types: [java.io.FileOutputStream, java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r3v0, types: [android.graphics.Bitmap] */
    public static void writeBitmapToFile(String str, Bitmap bitmap, int i) throws Throwable {
        ?? r0 = 0;
        r0 = 0;
        r0 = 0;
        r0 = 0;
        try {
            try {
                str = new FileOutputStream(new File((String) str));
                try {
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(str);
                    try {
                        r0 = Bitmap.CompressFormat.JPEG;
                        bitmap.compress(r0, i, bufferedOutputStream);
                        bufferedOutputStream.flush();
                        bufferedOutputStream.close();
                        try {
                            bufferedOutputStream.flush();
                            bufferedOutputStream.close();
                        } catch (Exception unused) {
                        }
                    } catch (Exception e2) {
                        e = e2;
                        r0 = bufferedOutputStream;
                        e.printStackTrace();
                        if (r0 != 0) {
                            try {
                                r0.flush();
                                r0.close();
                            } catch (Exception unused2) {
                            }
                        }
                        if (str != 0) {
                        }
                    } catch (Throwable th) {
                        th = th;
                        r0 = bufferedOutputStream;
                        if (r0 != 0) {
                            try {
                                r0.flush();
                                r0.close();
                            } catch (Exception unused3) {
                            }
                        }
                        if (str != 0) {
                            try {
                                str.close();
                                throw th;
                            } catch (Exception unused4) {
                                throw th;
                            }
                        }
                        throw th;
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            } catch (Exception e4) {
                e = e4;
                str = 0;
            } catch (Throwable th2) {
                th = th2;
                str = 0;
            }
            try {
                str.close();
            } catch (Exception unused5) {
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x0094 -> B:50:0x0097). Please report as a decompilation issue!!! */
    public static void savePngBitmap(Bitmap bitmap, File file, boolean z) throws Throwable {
        FileOutputStream fileOutputStream;
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                try {
                    fileOutputStream = new FileOutputStream(file);
                    try {
                        if (z) {
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                            if (byteArrayOutputStream.toByteArray().length > 50) {
                                int i = 100;
                                while (byteArrayOutputStream.toByteArray().length / 1024 > 25) {
                                    byteArrayOutputStream.reset();
                                    bitmap.compress(Bitmap.CompressFormat.PNG, i, byteArrayOutputStream);
                                    i -= 10;
                                }
                            }
                            if (BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), null, null).compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)) {
                                fileOutputStream.flush();
                                fileOutputStream.close();
                            }
                        } else if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)) {
                            fileOutputStream.flush();
                            fileOutputStream.close();
                        }
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    } catch (FileNotFoundException e2) {
                        e = e2;
                        fileOutputStream2 = fileOutputStream;
                        e.printStackTrace();
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.flush();
                            fileOutputStream2.close();
                        }
                    } catch (IOException e3) {
                        e = e3;
                        fileOutputStream2 = fileOutputStream;
                        e.printStackTrace();
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.flush();
                            fileOutputStream2.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.flush();
                                fileOutputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (FileNotFoundException e5) {
                    e = e5;
                } catch (IOException e6) {
                    e = e6;
                }
            } catch (IOException e7) {
                e7.printStackTrace();
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = fileOutputStream2;
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x0094 -> B:50:0x0097). Please report as a decompilation issue!!! */
    public static void saveBitmap(Bitmap bitmap, File file, boolean z) throws Throwable {
        FileOutputStream fileOutputStream;
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                try {
                    fileOutputStream = new FileOutputStream(file);
                    try {
                        if (z) {
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                            if (byteArrayOutputStream.toByteArray().length > 50) {
                                int i = 100;
                                while (byteArrayOutputStream.toByteArray().length / 1024 > 25) {
                                    byteArrayOutputStream.reset();
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
                                    i -= 10;
                                }
                            }
                            if (BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), null, null).compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)) {
                                fileOutputStream.flush();
                                fileOutputStream.close();
                            }
                        } else if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)) {
                            fileOutputStream.flush();
                            fileOutputStream.close();
                        }
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    } catch (FileNotFoundException e2) {
                        e = e2;
                        fileOutputStream2 = fileOutputStream;
                        e.printStackTrace();
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.flush();
                            fileOutputStream2.close();
                        }
                    } catch (IOException e3) {
                        e = e3;
                        fileOutputStream2 = fileOutputStream;
                        e.printStackTrace();
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.flush();
                            fileOutputStream2.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.flush();
                                fileOutputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (FileNotFoundException e5) {
                    e = e5;
                } catch (IOException e6) {
                    e = e6;
                }
            } catch (IOException e7) {
                e7.printStackTrace();
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = fileOutputStream2;
        }
    }

    private static Bitmap compressImage(Bitmap bitmap) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        if (bitmap == null) {
            return null;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (Error unused) {
            byteArrayOutputStream = null;
        } catch (Throwable th) {
            th = th;
            byteArrayOutputStream = null;
        }
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
            try {
                byteArrayOutputStream.close();
            } catch (IOException unused2) {
            }
            return bitmapDecodeStream;
        } catch (Error unused3) {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException unused4) {
                }
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException unused5) {
                }
            }
            throw th;
        }
    }

    public static Bitmap bitmapScale(Bitmap bitmap, float f2) {
        if (bitmap == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(f2, f2);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private static int readPictureDegree(String str) {
        int i;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 1);
            if (attributeInt == 3) {
                i = 180;
            } else if (attributeInt == 6) {
                i = 90;
            } else {
                if (attributeInt != 8) {
                    return 0;
                }
                i = DfuException.ERROR_READ_DEVICE_INFO_ERROR;
            }
            return i;
        } catch (Exception unused) {
            return 0;
        }
    }

    private static Bitmap rotateBitmap(Bitmap bitmap, float f2) {
        if (bitmap == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.postRotate(f2);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    public static Bitmap byteToBitmap(byte[] bArr) {
        Bitmap bitmap = null;
        if (bArr != null && bArr.length != 0) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            bitmap = (Bitmap) new SoftReference(BitmapFactory.decodeStream(byteArrayInputStream, null, options)).get();
            try {
                byteArrayInputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return bitmap;
    }

    public static Bitmap transform2CornerBitmap(Bitmap bitmap, float f2) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        canvas.drawRoundRect(0.0f, 0.0f, width, height, f2, f2, paint);
        return bitmapCreateBitmap;
    }

    public static Bitmap transform2CycleBitmap(Bitmap bitmap, float f2) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f3 = (width >= height ? width : height) / 2.0f;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        CommonLogUtil.d("radius = " + f3);
        canvas.drawCircle(((float) width) / 2.0f, ((float) height) / 2.0f, f3 - f2, paint);
        return bitmapCreateBitmap;
    }

    public static boolean saveBitmap(Context context, Bitmap bitmap, String str) {
        String str2;
        String str3 = Build.BRAND;
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "手机品牌 brand = " + str3);
        if (str3.equals("xiaomi")) {
            str2 = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/" + str;
        } else if (str3.equalsIgnoreCase("Huawei")) {
            str2 = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/" + str;
        } else {
            str2 = Environment.getExternalStorageDirectory().getPath() + "/DCIM/" + str;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            saveSignImage(str, bitmap);
            return true;
        }
        Log.v("saveBitmap brand", "" + str3);
        File file = new File(str2);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream)) {
                fileOutputStream.flush();
                fileOutputStream.close();
                if (Build.VERSION.SDK_INT >= 29) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("_data", file.getAbsolutePath());
                    contentValues.put("mime_type", "image/jpeg");
                    IdoApp.getAppContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                } else {
                    MediaStore.Images.Media.insertImage(IdoApp.getAppContext().getContentResolver(), file.getAbsolutePath(), str, (String) null);
                }
            }
            context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.parse("file://" + str2)));
            return true;
        } catch (FileNotFoundException e2) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "saveBitmap: FileNotFoundException:" + e2.getMessage().toString());
            e2.printStackTrace();
            return false;
        } catch (IOException e3) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "saveBitmap: IOException:" + e3.getMessage().toString());
            e3.printStackTrace();
            return false;
        } catch (Exception e4) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "saveBitmap: IOException:" + e4.getMessage().toString());
            e4.printStackTrace();
            return false;
        }
    }

    public static void saveSignImage(String str, Bitmap bitmap) {
        OutputStream outputStreamOpenOutputStream;
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("_display_name", str);
            if (Build.VERSION.SDK_INT >= 29) {
                contentValues.put("relative_path", "DCIM/");
            } else {
                contentValues.put("_data", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath());
            }
            contentValues.put("mime_type", "image/JPEG");
            Uri uriInsert = IdoApp.getAppContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            if (uriInsert == null || (outputStreamOpenOutputStream = IdoApp.getAppContext().getContentResolver().openOutputStream(uriInsert)) == null) {
                return;
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStreamOpenOutputStream);
            outputStreamOpenOutputStream.flush();
            outputStreamOpenOutputStream.close();
        } catch (Exception e2) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "saveSignImage: " + e2.toString());
        }
    }

    public static Bitmap view2Bitmap(View view, int i, int i2) {
        if (view == null) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Drawable background = view.getBackground();
        if (background != null) {
            background.draw(canvas);
        } else {
            canvas.drawColor(-1);
        }
        view.draw(canvas);
        return bitmapCreateBitmap;
    }

    public static Bitmap zoomImg(Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(i / width, i2 / height);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public static Bitmap scaleDrawable(Drawable drawable, int i, int i2) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return Bitmap.createScaledBitmap(bitmapCreateBitmap, i, i2, true);
    }

    public static Bitmap view2Bitmap(View view) {
        if (view == null) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Drawable background = view.getBackground();
        if (background != null) {
            background.draw(canvas);
        } else {
            canvas.drawColor(-1);
        }
        view.draw(canvas);
        return bitmapCreateBitmap;
    }

    public static Bitmap formatCircle2Rectangle(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setShader(bitmapShader);
        canvas.drawRect(0.0f, 0.0f, bitmapCreateBitmap.getWidth(), bitmapCreateBitmap.getHeight(), paint);
        return bitmapCreateBitmap;
    }

    public static Drawable bitmap2Drawable(Bitmap bitmap, Context context) {
        if (bitmap == null) {
            return null;
        }
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    public static void drawableToFile(Drawable drawable, String str, Bitmap.CompressFormat compressFormat) {
        if (drawable == null) {
            return;
        }
        try {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ((BitmapDrawable) drawable).getBitmap().compress(compressFormat, 100, fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static Bitmap makeTintBitmap(Bitmap bitmap, int i) {
        if (bitmap == null) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(i, PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return bitmapCreateBitmap;
    }

    public static Bitmap mergeBitmap(Bitmap bitmap, Bitmap bitmap2) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawBitmap(bitmap, new Matrix(), null);
        canvas.drawBitmap(bitmap2, 0.0f, 0.0f, (Paint) null);
        return bitmapCreateBitmap;
    }

    public static Bitmap mergeBitmapGroup(ArrayList<Bitmap> arrayList, ArrayList<String> arrayList2) {
        if (arrayList == null) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(arrayList.get(0).getWidth(), arrayList.get(0).getHeight(), arrayList.get(0).getConfig());
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        for (int i = 0; i < arrayList.size(); i++) {
            Paint paint = new Paint();
            if (!TextUtils.isEmpty(arrayList2.get(i))) {
                paint.setColorFilter(new PorterDuffColorFilter(Color.parseColor(arrayList2.get(i)), PorterDuff.Mode.SRC_IN));
            }
            if (i == 0) {
                canvas.drawBitmap(arrayList.get(i), new Matrix(), paint);
                canvas.setDrawFilter(new DrawFilter());
            } else {
                canvas.drawBitmap(arrayList.get(i), 0.0f, 0.0f, paint);
            }
        }
        return bitmapCreateBitmap;
    }
}