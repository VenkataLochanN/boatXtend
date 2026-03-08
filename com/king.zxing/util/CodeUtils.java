package com.king.zxing.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.text.TextUtils;
import androidx.core.view.ViewCompat;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.king.zxing.DecodeFormatManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/* JADX INFO: loaded from: classes3.dex */
public final class CodeUtils {
    private CodeUtils() {
        throw new AssertionError();
    }

    public static Bitmap createQRCode(String str, int i) {
        return createQRCode(str, i, null);
    }

    public static Bitmap createQRCode(String str, int i, Bitmap bitmap) {
        return createQRCode(str, i, bitmap, 0.2f);
    }

    public static Bitmap createQRCode(String str, int i, Bitmap bitmap, float f2) {
        HashMap map = new HashMap();
        map.put(EncodeHintType.CHARACTER_SET, "utf-8");
        map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        map.put(EncodeHintType.MARGIN, 1);
        return createQRCode(str, i, bitmap, f2, map);
    }

    public static Bitmap createQRCode(String str, int i, Bitmap bitmap, float f2, Map<EncodeHintType, ?> map) {
        try {
            BitMatrix bitMatrixEncode = new QRCodeWriter().encode(str, BarcodeFormat.QR_CODE, i, i, map);
            int[] iArr = new int[i * i];
            for (int i2 = 0; i2 < i; i2++) {
                for (int i3 = 0; i3 < i; i3++) {
                    if (bitMatrixEncode.get(i3, i2)) {
                        iArr[(i2 * i) + i3] = -16777216;
                    } else {
                        iArr[(i2 * i) + i3] = -1;
                    }
                }
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
            bitmapCreateBitmap.setPixels(iArr, 0, i, 0, 0, i, i);
            return bitmap != null ? addLogo(bitmapCreateBitmap, bitmap, f2) : bitmapCreateBitmap;
        } catch (WriterException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static Bitmap addLogo(Bitmap bitmap, Bitmap bitmap2, float f2) {
        if (bitmap == null) {
            return null;
        }
        if (bitmap2 == null) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int width2 = bitmap2.getWidth();
        int height2 = bitmap2.getHeight();
        if (width == 0 || height == 0) {
            return null;
        }
        if (width2 == 0 || height2 == 0) {
            return bitmap;
        }
        float f3 = (width * f2) / width2;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
            canvas.scale(f3, f3, width / 2, height / 2);
            canvas.drawBitmap(bitmap2, (width - width2) / 2, (height - height2) / 2, (Paint) null);
            canvas.save();
            canvas.restore();
            return bitmapCreateBitmap;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String parseQRCode(String str) {
        HashMap map = new HashMap();
        map.put(DecodeHintType.CHARACTER_SET, "utf-8");
        return parseQRCode(str, map);
    }

    public static String parseQRCode(String str, Map<DecodeHintType, ?> map) {
        Result resultDecode;
        try {
            QRCodeReader qRCodeReader = new QRCodeReader();
            RGBLuminanceSource rGBLuminanceSource = getRGBLuminanceSource(compressBitmap(str));
            if (rGBLuminanceSource != null) {
                try {
                    try {
                        resultDecode = qRCodeReader.decode(new BinaryBitmap(new HybridBinarizer(rGBLuminanceSource)), map);
                    } catch (Exception unused) {
                        try {
                            resultDecode = qRCodeReader.decode(new BinaryBitmap(new GlobalHistogramBinarizer(rGBLuminanceSource)));
                        } catch (NotFoundException unused2) {
                            resultDecode = null;
                        }
                    }
                } finally {
                    qRCodeReader.reset();
                }
            } else {
                resultDecode = null;
            }
            return resultDecode.getText();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String parseCode(String str) {
        HashMap map = new HashMap();
        Vector vector = new Vector();
        vector.addAll(DecodeFormatManager.ONE_D_FORMATS);
        vector.addAll(DecodeFormatManager.QR_CODE_FORMATS);
        vector.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);
        vector.addAll(DecodeFormatManager.AZTEC_FORMATS);
        vector.addAll(DecodeFormatManager.PDF417_FORMATS);
        map.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        map.put(DecodeHintType.POSSIBLE_FORMATS, vector);
        return parseCode(str, map);
    }

    public static String parseCode(String str, Map<DecodeHintType, Object> map) {
        Result resultDecodeWithState;
        try {
            MultiFormatReader multiFormatReader = new MultiFormatReader();
            multiFormatReader.setHints(map);
            RGBLuminanceSource rGBLuminanceSource = getRGBLuminanceSource(compressBitmap(str));
            if (rGBLuminanceSource != null) {
                try {
                    try {
                        resultDecodeWithState = multiFormatReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(rGBLuminanceSource)));
                    } catch (Exception unused) {
                        try {
                            resultDecodeWithState = multiFormatReader.decodeWithState(new BinaryBitmap(new GlobalHistogramBinarizer(rGBLuminanceSource)));
                        } catch (Exception unused2) {
                            resultDecodeWithState = null;
                        }
                    }
                } finally {
                    multiFormatReader.reset();
                }
            } else {
                resultDecodeWithState = null;
            }
            return resultDecodeWithState.getText();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.graphics.Bitmap compressBitmap(java.lang.String r6) {
        /*
            android.graphics.BitmapFactory$Options r0 = new android.graphics.BitmapFactory$Options
            r0.<init>()
            r1 = 1
            r0.inJustDecodeBounds = r1
            android.graphics.BitmapFactory.decodeFile(r6, r0)
            int r2 = r0.outWidth
            int r3 = r0.outHeight
            if (r2 <= r3) goto L1e
            float r4 = (float) r2
            r5 = 1145569280(0x44480000, float:800.0)
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto L1e
            int r2 = r0.outWidth
            float r2 = (float) r2
            float r2 = r2 / r5
        L1c:
            int r2 = (int) r2
            goto L2d
        L1e:
            if (r2 >= r3) goto L2c
            float r2 = (float) r3
            r3 = 1139802112(0x43f00000, float:480.0)
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 <= 0) goto L2c
            int r2 = r0.outHeight
            float r2 = (float) r2
            float r2 = r2 / r3
            goto L1c
        L2c:
            r2 = r1
        L2d:
            if (r2 > 0) goto L30
            goto L31
        L30:
            r1 = r2
        L31:
            r0.inSampleSize = r1
            r1 = 0
            r0.inJustDecodeBounds = r1
            android.graphics.Bitmap r6 = android.graphics.BitmapFactory.decodeFile(r6, r0)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.king.zxing.util.CodeUtils.compressBitmap(java.lang.String):android.graphics.Bitmap");
    }

    private static RGBLuminanceSource getRGBLuminanceSource(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[width * height];
        bitmap.getPixels(iArr, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        return new RGBLuminanceSource(width, height, iArr);
    }

    public static Bitmap createBarCode(String str, BarcodeFormat barcodeFormat, int i, int i2) {
        return createBarCode(str, barcodeFormat, i, i2, null);
    }

    public static Bitmap createBarCode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) {
        return createBarCode(str, barcodeFormat, i, i2, map, false, 40, ViewCompat.MEASURED_STATE_MASK);
    }

    public static Bitmap createBarCode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map, boolean z) {
        return createBarCode(str, barcodeFormat, i, i2, map, z, 40, ViewCompat.MEASURED_STATE_MASK);
    }

    public static Bitmap createBarCode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map, boolean z, int i3, int i4) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            BitMatrix bitMatrixEncode = new MultiFormatWriter().encode(str, barcodeFormat, i, i2, map);
            int width = bitMatrixEncode.getWidth();
            int height = bitMatrixEncode.getHeight();
            int[] iArr = new int[width * height];
            for (int i5 = 0; i5 < height; i5++) {
                int i6 = i5 * width;
                for (int i7 = 0; i7 < width; i7++) {
                    iArr[i6 + i7] = bitMatrixEncode.get(i7, i5) ? ViewCompat.MEASURED_STATE_MASK : -1;
                }
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmapCreateBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
            return z ? addCode(bitmapCreateBitmap, str, i3, i4, i3 / 2) : bitmapCreateBitmap;
        } catch (WriterException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static Bitmap addCode(Bitmap bitmap, String str, int i, int i2, int i3) {
        if (bitmap == null) {
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= 0 || height <= 0) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height + i + (i3 * 2), Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
            TextPaint textPaint = new TextPaint();
            textPaint.setTextSize(i);
            textPaint.setColor(i2);
            textPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(str, width / 2, height + (i / 2) + i3, textPaint);
            canvas.save();
            canvas.restore();
            return bitmapCreateBitmap;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}