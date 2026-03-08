package com.lzy.imagepicker.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import com.realsil.sdk.dfu.DfuException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes3.dex */
public class BitmapUtil {
    private BitmapUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
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

    public static int getBitmapDegree(InputStream inputStream) {
        int i;
        try {
            int attributeInt = new ExifInterface(inputStream).getAttributeInt("Orientation", 1);
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
        Matrix matrix = new Matrix();
        matrix.postRotate(i);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (!bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return bitmapCreateBitmap;
    }

    public static Uri getRotatedUri(Activity activity, String str) {
        int bitmapDegree = getBitmapDegree(str);
        if (bitmapDegree != 0) {
            return Uri.parse(MediaStore.Images.Media.insertImage(activity.getContentResolver(), rotateBitmapByDegree(BitmapFactory.decodeFile(str), bitmapDegree), (String) null, (String) null));
        }
        return Uri.fromFile(new File(str));
    }

    public static Bitmap rotateBitmapByDegree(String str, int i) {
        return rotateBitmapByDegree(BitmapFactory.decodeFile(str), i);
    }
}