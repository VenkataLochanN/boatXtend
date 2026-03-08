package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.UByte;

/* JADX INFO: loaded from: classes.dex */
public final class BitmapDescriptor {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Bitmap f2787a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Bundle f2788b;

    BitmapDescriptor(Bitmap bitmap) {
        if (bitmap != null) {
            this.f2787a = a(bitmap, bitmap.getWidth(), bitmap.getHeight());
        }
    }

    private Bitmap a(Bitmap bitmap, int i, int i2) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return bitmapCreateBitmap;
    }

    byte[] a() {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(this.f2787a.getWidth() * this.f2787a.getHeight() * 4);
        this.f2787a.copyPixelsToBuffer(byteBufferAllocate);
        return byteBufferAllocate.array();
    }

    Bundle b() {
        if (this.f2787a == null) {
            throw new IllegalStateException("BDMapSDKException: the bitmap has been recycled! you can not use it again");
        }
        if (this.f2788b == null) {
            Bundle bundle = new Bundle();
            bundle.putInt("image_width", this.f2787a.getWidth());
            bundle.putInt("image_height", this.f2787a.getHeight());
            byte[] bArrA = a();
            bundle.putByteArray("image_data", bArrA);
            MessageDigest messageDigest = null;
            try {
                messageDigest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e2) {
                e2.printStackTrace();
            }
            if (messageDigest != null) {
                messageDigest.update(bArrA, 0, bArrA.length);
                byte[] bArrDigest = messageDigest.digest();
                StringBuilder sb = new StringBuilder("");
                for (byte b2 : bArrDigest) {
                    sb.append(Integer.toString((b2 & UByte.MAX_VALUE) + 256, 16).substring(1));
                }
                bundle.putString("image_hashcode", sb.toString());
            }
            this.f2788b = bundle;
        }
        return this.f2788b;
    }

    public Bitmap getBitmap() {
        return this.f2787a;
    }

    public void recycle() {
        Bitmap bitmap = this.f2787a;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        this.f2787a.recycle();
        this.f2787a = null;
    }
}