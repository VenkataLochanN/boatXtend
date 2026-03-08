package com.ido.life.util;

import android.graphics.Bitmap;
import android.util.Log;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
public class AndroidBmpUtil {
    private static final int BMP_WIDTH_OF_TIMES = 4;
    private static final int BYTE_PER_PIXEL = 3;

    private static byte[] writeInt(int i) throws IOException {
        return new byte[]{(byte) (i & 255), (byte) ((65280 & i) >> 8), (byte) ((16711680 & i) >> 16), (byte) ((i & ViewCompat.MEASURED_STATE_MASK) >> 24)};
    }

    private static byte[] writeShort(short s) throws IOException {
        return new byte[]{(byte) (s & 255), (byte) ((s & 65280) >> 8)};
    }

    public static boolean save(Bitmap bitmap, String str) throws IOException {
        boolean z;
        byte[] bArr;
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (bitmap == null || str == null) {
            return false;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i = width * 3;
        int i2 = i % 4;
        if (i2 > 0) {
            byte[] bArr2 = new byte[4 - i2];
            for (int i3 = 0; i3 < bArr2.length; i3++) {
                bArr2[i3] = -1;
            }
            bArr = bArr2;
            z = true;
        } else {
            z = false;
            bArr = null;
        }
        int i4 = width * height;
        int[] iArr = new int[i4];
        int length = (i + (z ? bArr.length : 0)) * height;
        int i5 = length + 54;
        byte[] bArr3 = bArr;
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i5);
        byteBufferAllocate.put((byte) 66);
        byteBufferAllocate.put((byte) 77);
        byteBufferAllocate.put(writeInt(i5));
        byteBufferAllocate.put(writeShort((short) 0));
        byteBufferAllocate.put(writeShort((short) 0));
        byteBufferAllocate.put(writeInt(54));
        byteBufferAllocate.put(writeInt(40));
        byteBufferAllocate.put(writeInt(((z && bArr3.length == 3) ? 1 : 0) + width));
        byteBufferAllocate.put(writeInt(height));
        byteBufferAllocate.put(writeShort((short) 1));
        byteBufferAllocate.put(writeShort((short) 24));
        byteBufferAllocate.put(writeInt(0));
        byteBufferAllocate.put(writeInt(length));
        byteBufferAllocate.put(writeInt(0));
        byteBufferAllocate.put(writeInt(0));
        byteBufferAllocate.put(writeInt(0));
        byteBufferAllocate.put(writeInt(0));
        int i6 = (height - 1) * width;
        int i7 = i4;
        while (height > 0) {
            for (int i8 = i6; i8 < i7; i8++) {
                byteBufferAllocate.put((byte) (iArr[i8] & 255));
                byteBufferAllocate.put((byte) ((iArr[i8] & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8));
                byteBufferAllocate.put((byte) ((iArr[i8] & 16711680) >> 16));
            }
            if (z) {
                byteBufferAllocate.put(bArr3);
            }
            height--;
            int i9 = i6;
            i6 -= width;
            i7 = i9;
        }
        FileOutputStream fileOutputStream = new FileOutputStream(str);
        fileOutputStream.write(byteBufferAllocate.array());
        fileOutputStream.close();
        Log.v("AndroidBmpUtil", (System.currentTimeMillis() - jCurrentTimeMillis) + " ms");
        return true;
    }
}