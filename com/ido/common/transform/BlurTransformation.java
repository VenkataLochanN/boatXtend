package com.ido.common.transform;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.renderscript.RSRuntimeException;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.ido.common.transform.internal.FastBlur;
import com.ido.common.transform.internal.RSBlur;

/* JADX INFO: loaded from: classes2.dex */
public class BlurTransformation implements Transformation<Bitmap> {
    private static int DEFAULT_DOWN_SAMPLING = 1;
    private static int MAX_RADIUS = 25;
    private BitmapPool mBitmapPool;
    private Context mContext;
    private int mRadius;
    private int mSampling;

    public BlurTransformation(Context context) {
        this(context, Glide.get(context).getBitmapPool(), MAX_RADIUS, DEFAULT_DOWN_SAMPLING);
    }

    public BlurTransformation(Context context, BitmapPool bitmapPool) {
        this(context, bitmapPool, MAX_RADIUS, DEFAULT_DOWN_SAMPLING);
    }

    public BlurTransformation(Context context, BitmapPool bitmapPool, int i) {
        this(context, bitmapPool, i, DEFAULT_DOWN_SAMPLING);
    }

    public BlurTransformation(Context context, int i) {
        this(context, Glide.get(context).getBitmapPool(), i, DEFAULT_DOWN_SAMPLING);
    }

    public BlurTransformation(Context context, int i, int i2) {
        this(context, Glide.get(context).getBitmapPool(), i, i2);
    }

    public BlurTransformation(Context context, BitmapPool bitmapPool, int i, int i2) {
        this.mContext = context.getApplicationContext();
        this.mBitmapPool = bitmapPool;
        this.mRadius = i;
        this.mSampling = i2;
    }

    @Override // com.bumptech.glide.load.Transformation
    public Resource<Bitmap> transform(Resource<Bitmap> resource, int i, int i2) throws Throwable {
        Bitmap bitmapBlur;
        Bitmap bitmap = resource.get();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i3 = this.mSampling;
        int i4 = width / i3;
        int i5 = height / i3;
        Bitmap bitmapCreateBitmap = this.mBitmapPool.get(i4, i5, Bitmap.Config.ARGB_8888);
        if (bitmapCreateBitmap == null) {
            bitmapCreateBitmap = Bitmap.createBitmap(i4, i5, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        int i6 = this.mSampling;
        canvas.scale(1.0f / i6, 1.0f / i6);
        Paint paint = new Paint();
        paint.setFlags(2);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                bitmapBlur = RSBlur.blur(this.mContext, bitmapCreateBitmap, this.mRadius);
            } catch (RSRuntimeException unused) {
                bitmapBlur = FastBlur.blur(bitmapCreateBitmap, this.mRadius, true);
            }
        } else {
            bitmapBlur = FastBlur.blur(bitmapCreateBitmap, this.mRadius, true);
        }
        return BitmapResource.obtain(bitmapBlur, this.mBitmapPool);
    }

    @Override // com.bumptech.glide.load.Transformation
    public String getId() {
        return "BlurTransformation(radius=" + this.mRadius + ", sampling=" + this.mSampling + ")";
    }
}