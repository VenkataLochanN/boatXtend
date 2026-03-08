package com.ido.life.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.FileSizeUtil;
import com.realsil.sdk.dfu.DfuException;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes3.dex */
public class ImageUtil {
    public static Bitmap view2Bitmap(View view) {
        if (view == null) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        view.setDrawingCacheEnabled(true);
        canvas.drawBitmap(view.getDrawingCache(), view.getLeft(), view.getTop(), (Paint) null);
        return bitmapCreateBitmap;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x00b8 -> B:32:0x00cd). Please report as a decompilation issue!!! */
    public static boolean save(Bitmap bitmap, Bitmap.CompressFormat compressFormat, boolean z, long j) throws Throwable {
        CommonLogUtil.d("BaiduMapModel--->   getWidth:" + bitmap.getWidth() + ",getHeight: " + bitmap.getHeight() + ",fileName:" + j);
        StringBuilder sb = new StringBuilder();
        sb.append("BaiduMapModel---->");
        sb.append(getBitmapSize(bitmap) / 1024);
        sb.append("KB");
        CommonLogUtil.d(sb.toString());
        BufferedOutputStream bufferedOutputStream = null;
        boolean zCompress = false;
        try {
            try {
                try {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(new File(LogPathImpl.getInstance().getPicPath() + j + ".png")));
                    if (compressFormat == null) {
                        try {
                            compressFormat = Bitmap.CompressFormat.PNG;
                        } catch (IOException e2) {
                            e = e2;
                            bufferedOutputStream = bufferedOutputStream2;
                            e.printStackTrace();
                            if (bufferedOutputStream != null) {
                                bufferedOutputStream.close();
                            }
                            return zCompress;
                        } catch (Throwable th) {
                            th = th;
                            bufferedOutputStream = bufferedOutputStream2;
                            if (bufferedOutputStream != null) {
                                try {
                                    bufferedOutputStream.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                    zCompress = bitmap.compress(compressFormat, 100, bufferedOutputStream2);
                    bufferedOutputStream2.flush();
                    CommonLogUtil.d("BaiduMapModel---->+截图耗时:" + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms");
                    if (z && !bitmap.isRecycled()) {
                        bitmap.recycle();
                    }
                    bufferedOutputStream2.close();
                } catch (IOException e4) {
                    e = e4;
                }
            } catch (IOException e5) {
                e5.printStackTrace();
            }
            return zCompress;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static long getBitmapSize(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 12) {
            return bitmap.getByteCount();
        }
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    public static Bitmap zoomImage(Bitmap bitmap, float f2) {
        float width = bitmap.getWidth();
        float height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(f2, f2);
        return Bitmap.createBitmap(bitmap, 0, 0, (int) (((int) width) * f2), (int) (((int) height) * f2), matrix, true);
    }

    public static Bitmap zoomImage2(Bitmap bitmap, int i) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, byteArrayOutputStream);
        int i2 = i * 1024;
        float fSqrt = (float) Math.sqrt(i2 / byteArrayOutputStream.toByteArray().length);
        Matrix matrix = new Matrix();
        matrix.setScale(fSqrt, fSqrt);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        byteArrayOutputStream.reset();
        bitmapCreateBitmap.compress(Bitmap.CompressFormat.JPEG, 85, byteArrayOutputStream);
        Bitmap bitmapCreateBitmap2 = bitmapCreateBitmap;
        while (byteArrayOutputStream.toByteArray().length > i2) {
            CommonLogUtil.d("bitmap:" + (byteArrayOutputStream.toByteArray().length / 1024) + FileSizeUtil.UNIT_MB);
            matrix.setScale(0.9f, 0.9f);
            bitmapCreateBitmap2 = Bitmap.createBitmap(bitmapCreateBitmap2, 0, 0, bitmapCreateBitmap2.getWidth(), bitmapCreateBitmap2.getHeight(), matrix, true);
            byteArrayOutputStream.reset();
            bitmapCreateBitmap2.compress(Bitmap.CompressFormat.JPEG, 85, byteArrayOutputStream);
        }
        return bitmapCreateBitmap2;
    }

    public static Bitmap compressBitmap(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        int i = 100;
        while (byteArrayOutputStream.toByteArray().length / 1024 > 100) {
            byteArrayOutputStream.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
            i -= 10;
            int length = byteArrayOutputStream.toByteArray().length / 1024;
        }
        return BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), null, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.graphics.Bitmap comp(android.graphics.Bitmap r9) {
        /*
            r8 = this;
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.JPEG
            r2 = 100
            r9.compress(r1, r2, r0)
            byte[] r1 = r0.toByteArray()
            int r1 = r1.length
            r2 = 1024(0x400, float:1.435E-42)
            int r1 = r1 / r2
            if (r1 <= r2) goto L20
            r0.reset()
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.JPEG
            r2 = 50
            r9.compress(r1, r2, r0)
        L20:
            java.io.ByteArrayInputStream r9 = new java.io.ByteArrayInputStream
            byte[] r1 = r0.toByteArray()
            r9.<init>(r1)
            android.graphics.BitmapFactory$Options r1 = new android.graphics.BitmapFactory$Options
            r1.<init>()
            r2 = 1
            r1.inJustDecodeBounds = r2
            r3 = 0
            android.graphics.BitmapFactory.decodeStream(r9, r3, r1)
            r9 = 0
            r1.inJustDecodeBounds = r9
            int r9 = r1.outWidth
            int r4 = r1.outHeight
            r5 = 1145569280(0x44480000, float:800.0)
            r6 = 1139802112(0x43f00000, float:480.0)
            if (r9 <= r4) goto L4d
            float r7 = (float) r9
            int r7 = (r7 > r6 ? 1 : (r7 == r6 ? 0 : -1))
            if (r7 <= 0) goto L4d
            int r9 = r1.outWidth
            float r9 = (float) r9
            float r9 = r9 / r6
        L4b:
            int r9 = (int) r9
            goto L5a
        L4d:
            if (r9 >= r4) goto L59
            float r9 = (float) r4
            int r9 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r9 <= 0) goto L59
            int r9 = r1.outHeight
            float r9 = (float) r9
            float r9 = r9 / r5
            goto L4b
        L59:
            r9 = r2
        L5a:
            if (r9 > 0) goto L5d
            r9 = r2
        L5d:
            r1.inSampleSize = r9
            android.graphics.Bitmap$Config r9 = android.graphics.Bitmap.Config.RGB_565
            r1.inPreferredConfig = r9
            java.io.ByteArrayInputStream r9 = new java.io.ByteArrayInputStream
            byte[] r0 = r0.toByteArray()
            r9.<init>(r0)
            android.graphics.Bitmap r9 = android.graphics.BitmapFactory.decodeStream(r9, r3, r1)
            android.graphics.Bitmap r9 = r8.compressImage(r9)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.util.ImageUtil.comp(android.graphics.Bitmap):android.graphics.Bitmap");
    }

    private Bitmap compressImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        int i = 100;
        while (byteArrayOutputStream.toByteArray().length / 1024 > 100) {
            byteArrayOutputStream.reset();
            i -= 10;
            bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
        }
        return BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), null, null);
    }

    public static void saveWallPaper(Bitmap bitmap, String str) {
        FileOutputStream fileOutputStream;
        if (bitmap == null) {
            return;
        }
        try {
            File file = new File(str);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file);
            try {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            } catch (IOException e2) {
                e = e2;
                e.printStackTrace();
            }
        } catch (IOException e3) {
            e = e3;
            fileOutputStream = null;
        }
        if (fileOutputStream != null) {
            try {
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        }
    }

    public static int getBitmapDegree(String str) {
        int i;
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
        } catch (IOException e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static Bitmap rotateBitmapByDegree(Bitmap bitmap, int i) {
        Bitmap bitmapCreateBitmap;
        Matrix matrix = new Matrix();
        matrix.postRotate(i);
        try {
            bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (OutOfMemoryError unused) {
            bitmapCreateBitmap = null;
        }
        if (bitmapCreateBitmap == null) {
            bitmapCreateBitmap = bitmap;
        }
        if (bitmap != bitmapCreateBitmap) {
            bitmap.recycle();
        }
        return bitmapCreateBitmap;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x003d -> B:28:0x0040). Please report as a decompilation issue!!! */
    public static BitmapFactory.Options createOptions(Uri uri, int i, boolean z) throws Throwable {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = true;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inJustDecodeBounds = z;
        options.inSampleSize = i;
        InputStream inputStream = null;
        try {
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            try {
                InputStream inputStreamOpenInputStream = IdoApp.getAppContext().getContentResolver().openInputStream(uri);
                try {
                    BitmapFactory.decodeStream(inputStreamOpenInputStream, null, options);
                    if (inputStreamOpenInputStream != null) {
                        inputStreamOpenInputStream.close();
                    }
                } catch (Exception e3) {
                    inputStream = inputStreamOpenInputStream;
                    e = e3;
                    e.printStackTrace();
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (Throwable th) {
                    inputStream = inputStreamOpenInputStream;
                    th = th;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception e5) {
                e = e5;
            }
            return options;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int getInSampleSize(android.graphics.BitmapFactory.Options r4, float r5, float r6) {
        /*
            int r0 = r4.outWidth
            int r4 = r4.outHeight
            java.lang.String r1 = "getBitmapFormUri()............22"
            com.ido.common.log.CommonLogUtil.d(r1)
            r1 = -1
            r2 = 1
            if (r0 == r1) goto L29
            if (r4 != r1) goto L10
            goto L29
        L10:
            if (r0 <= r4) goto L1a
            float r1 = (float) r0
            int r3 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r3 <= 0) goto L1a
            float r1 = r1 / r6
            int r4 = (int) r1
            goto L25
        L1a:
            if (r0 >= r4) goto L24
            float r4 = (float) r4
            int r6 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r6 <= 0) goto L24
            float r4 = r4 / r5
            int r4 = (int) r4
            goto L25
        L24:
            r4 = r2
        L25:
            if (r4 > 0) goto L28
            r4 = r2
        L28:
            return r4
        L29:
            java.lang.String r4 = "getBitmapFormUri()............nullll"
            com.ido.common.log.CommonLogUtil.d(r4)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.util.ImageUtil.getInSampleSize(android.graphics.BitmapFactory$Options, float, float):int");
    }

    public static Bitmap formatBitmapFromUri(Uri uri, float f2, float f3) throws Throwable {
        Bitmap bitmapDecodeStream = null;
        try {
            BitmapFactory.Options optionsCreateOptions = createOptions(uri, getInSampleSize(createOptions(uri, 1, true), f2, f3), false);
            InputStream inputStreamOpenInputStream = IdoApp.getAppContext().getContentResolver().openInputStream(uri);
            bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpenInputStream, null, optionsCreateOptions);
            if (inputStreamOpenInputStream != null) {
                inputStreamOpenInputStream.close();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return bitmapDecodeStream;
    }
}