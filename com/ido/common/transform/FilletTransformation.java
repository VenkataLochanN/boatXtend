package com.ido.common.transform;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/* JADX INFO: loaded from: classes2.dex */
public class FilletTransformation extends BitmapTransformation {
    private float radius;

    public FilletTransformation(Context context) {
        this(context, 10);
    }

    public FilletTransformation(BitmapPool bitmapPool) {
        super(bitmapPool);
    }

    public FilletTransformation(Context context, int i) {
        super(context);
        this.radius = i;
    }

    @Override // com.bumptech.glide.load.resource.bitmap.BitmapTransformation
    protected Bitmap transform(BitmapPool bitmapPool, Bitmap bitmap, int i, int i2) {
        return roundCrop(bitmapPool, bitmap);
    }

    @Override // com.bumptech.glide.load.Transformation
    public String getId() {
        return getClass().getName() + Math.round(this.radius);
    }

    private Bitmap roundCrop(BitmapPool bitmapPool, Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        Bitmap bitmapCreateBitmap = bitmapPool.get(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        if (bitmapCreateBitmap == null) {
            bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0.0f, 0.0f, bitmap.getWidth(), bitmap.getHeight());
        float f2 = this.radius;
        canvas.drawRoundRect(rectF, f2, f2, paint);
        return bitmapCreateBitmap;
    }
}