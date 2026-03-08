package com.ido.common.utils;

import android.graphics.Bitmap;
import android.view.View;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class BmpUtil {
    private static byte[] addBMPImageHeader(int i) {
        return new byte[]{66, 77, (byte) (i >> 0), (byte) (i >> 8), (byte) (i >> 16), (byte) (i >> 24), 0, 0, 0, 0, 54, 0, 0, 0};
    }

    private static byte[] addBMPImageInfosHeader(int i, int i2) {
        return new byte[]{40, 0, 0, 0, (byte) (i >> 0), (byte) (i >> 8), (byte) (i >> 16), (byte) (i >> 24), (byte) (i2 >> 0), (byte) (i2 >> 8), (byte) (i2 >> 16), (byte) (i2 >> 24), 1, 0, 24, 0, 0, 0, 0, 0, 0, 0, 0, 0, -32, 1, 0, 0, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    }

    public static void viewToABmp(View view, File file) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap drawingCache = view.getDrawingCache();
        if (drawingCache != null) {
            int width = drawingCache.getWidth();
            int height = drawingCache.getHeight();
            int[] iArr = new int[width * height];
            drawingCache.getPixels(iArr, 0, width, 0, 0, width, height);
            byte[] bArrAddBMP_RGB_888 = addBMP_RGB_888(iArr, width, height);
            byte[] bArrAddBMPImageHeader = addBMPImageHeader(bArrAddBMP_RGB_888.length);
            byte[] bArrAddBMPImageInfosHeader = addBMPImageInfosHeader(width, height);
            byte[] bArr = new byte[bArrAddBMP_RGB_888.length + 54];
            System.arraycopy(bArrAddBMPImageHeader, 0, bArr, 0, bArrAddBMPImageHeader.length);
            System.arraycopy(bArrAddBMPImageInfosHeader, 0, bArr, 14, bArrAddBMPImageInfosHeader.length);
            System.arraycopy(bArrAddBMP_RGB_888, 0, bArr, 54, bArrAddBMP_RGB_888.length);
            try {
                new FileOutputStream(file).write(bArr);
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
    }

    private static byte[] addBMP_RGB_888(int[] iArr, int i, int i2) {
        int length = iArr.length;
        System.out.println(iArr.length);
        byte[] bArr = new byte[i2 * i * 3];
        int i3 = length - 1;
        int i4 = 0;
        while (i3 >= i) {
            int i5 = i3 - i;
            for (int i6 = i5 + 1; i6 <= i3; i6++) {
                bArr[i4] = (byte) (iArr[i6] >> 0);
                bArr[i4 + 1] = (byte) (iArr[i6] >> 8);
                i4 += 2;
            }
            i3 = i5;
        }
        return bArr;
    }
}