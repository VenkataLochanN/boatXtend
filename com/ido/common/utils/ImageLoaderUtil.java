package com.ido.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.StringSignature;
import com.ido.common.transform.BlurTransformation;
import com.ido.common.transform.FilletTransformation;
import com.ido.common.transform.GlideCircleTransform;
import java.io.File;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes2.dex */
public class ImageLoaderUtil {
    private static String signature = "1";

    public static void resetSignature() {
    }

    public static <T> void loadImg(ImageView imageView, T t) {
        if (imageView == null || t == null) {
            return;
        }
        Glide.with(imageView.getContext()).load(t).centerCrop().override(Integer.MIN_VALUE, Integer.MIN_VALUE).dontAnimate().into(imageView);
    }

    public static <T> void loadImg(ImageView imageView, T t, int i) {
        if (imageView == null || t == null) {
            return;
        }
        Glide.with(imageView.getContext()).load(t).crossFade().centerCrop().placeholder(i).into(imageView);
    }

    public static <T> void loadImg(ImageView imageView, Size size, T t, int i) {
        if (imageView == null || size == null || t == null) {
            return;
        }
        Glide.with(imageView.getContext()).load(t).crossFade().centerCrop().placeholder(i).override(size.getWidth(), size.getHeight()).into(imageView);
    }

    public static <T> void loadImg(ImageView imageView, Size size, T t) {
        if (imageView == null || size == null || t == null) {
            return;
        }
        Glide.with(imageView.getContext()).load(t).crossFade().override(size.getWidth(), size.getHeight()).into(imageView);
    }

    public static <T> void loadImage(ImageView imageView, T t) {
        if (imageView == null || t == null) {
            return;
        }
        Glide.with(imageView.getContext()).load(t).centerCrop().dontAnimate().into(imageView);
    }

    public static <T> void loadImage(ImageView imageView, T t, boolean z, int i) {
        if (imageView == null || t == null) {
            return;
        }
        BitmapRequestBuilder<T, Bitmap> bitmapRequestBuilderDontAnimate = Glide.with(imageView.getContext()).load(t).asBitmap().format(DecodeFormat.PREFER_ARGB_8888).placeholder(i).centerCrop().dontAnimate();
        if (z) {
            bitmapRequestBuilderDontAnimate.signature((Key) new StringSignature(System.currentTimeMillis() + ""));
        }
        bitmapRequestBuilderDontAnimate.into(imageView);
    }

    public static <T> void loadImage(ImageView imageView, T t, int i) {
        if (imageView == null || t == null) {
            return;
        }
        Glide.with(imageView.getContext()).load(t).asBitmap().format(DecodeFormat.PREFER_ARGB_8888).placeholder(i).centerCrop().dontAnimate().into(imageView);
    }

    public static <T> void loadImgDefault(ImageView imageView, T t) {
        if (imageView == null || t == null) {
            return;
        }
        Glide.with(imageView.getContext()).load(t).dontAnimate().dontTransform().centerCrop().into(imageView);
    }

    public static <T> void loadImgCross(ImageView imageView, T t) {
        if (imageView == null || t == null) {
            return;
        }
        Glide.with(imageView.getContext()).load(t).crossFade().into(imageView);
    }

    public static <T> void loadImgFitCenter(ImageView imageView, T t) {
        if (imageView == null || t == null) {
            return;
        }
        Glide.with(imageView.getContext()).load(t).crossFade().centerCrop().fitCenter().into(imageView);
    }

    public static void loadCircleImage(ImageView imageView, String str, int i) {
        if (imageView == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            imageView.setImageResource(i);
        } else {
            Glide.with(imageView.getContext()).load(str).asBitmap().centerCrop().placeholder(i).centerCrop().into(new BitmapImageViewTarget(imageView) { // from class: com.ido.common.utils.ImageLoaderUtil.1
                /* JADX INFO: Access modifiers changed from: protected */
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.bumptech.glide.request.target.BitmapImageViewTarget, com.bumptech.glide.request.target.ImageViewTarget
                public void setResource(Bitmap bitmap) {
                    RoundedBitmapDrawable roundedBitmapDrawableCreate = RoundedBitmapDrawableFactory.create(((ImageView) this.view).getResources(), bitmap);
                    roundedBitmapDrawableCreate.setCircular(true);
                    ((ImageView) this.view).setImageDrawable(roundedBitmapDrawableCreate);
                }
            });
        }
    }

    public static <T> void loadImgCircle(ImageView imageView, T t, int i) {
        if (imageView == null || t == null) {
            return;
        }
        Glide.with(imageView.getContext()).load(t).crossFade().placeholder(i).transform(new CenterCrop(imageView.getContext()), new GlideCircleTransform(imageView.getContext())).into(imageView);
    }

    public static <T> void loadImgCircleMap(Context context, T t, int i, ImageViewTarget<Bitmap> imageViewTarget) {
        if (context == null || t == null || imageViewTarget == null) {
            return;
        }
        Glide.with(context).load(t).asBitmap().placeholder(i).transform(new CenterCrop(context), new GlideCircleTransform(context)).into(imageViewTarget);
    }

    public static <T> void loadImgBlur(ImageView imageView, T t, int i) {
        if (imageView == null || t == null) {
            return;
        }
        Glide.with(imageView.getContext()).load(t).crossFade().placeholder(i).bitmapTransform(new CenterCrop(imageView.getContext()), new BlurTransformation(imageView.getContext(), 25, 4)).into(imageView);
    }

    public static <T> void loadImgFillet(ImageView imageView, T t, boolean z, int i, int i2) {
        if (imageView == null || t == null) {
            return;
        }
        DrawableRequestBuilder<T> drawableRequestBuilderTransform = Glide.with(imageView.getContext()).load(t).centerCrop().crossFade().signature((Key) new StringSignature(signature)).placeholder(i2).transform(new CenterCrop(imageView.getContext()), new FilletTransformation(imageView.getContext(), i));
        if (z) {
            drawableRequestBuilderTransform.signature((Key) new StringSignature(System.currentTimeMillis() + ""));
        }
        drawableRequestBuilderTransform.into(imageView);
    }

    public static <T> void loadImgFillet(ImageView imageView, T t, boolean z, int i, Drawable drawable) {
        if (imageView == null || t == null) {
            return;
        }
        DrawableRequestBuilder<T> drawableRequestBuilderTransform = Glide.with(imageView.getContext()).load(t).centerCrop().dontAnimate().signature((Key) new StringSignature(signature)).placeholder(drawable).transform(new CenterCrop(imageView.getContext()), new FilletTransformation(imageView.getContext(), i));
        if (z) {
            drawableRequestBuilderTransform.signature((Key) new StringSignature(System.currentTimeMillis() + ""));
        }
        drawableRequestBuilderTransform.into(imageView);
    }

    public static <T> void loadImgFillet(ImageView imageView, T t, int i, int i2) {
        if (imageView == null || t == null) {
            return;
        }
        Glide.with(imageView.getContext()).load(t).centerCrop().crossFade().signature((Key) new StringSignature(signature)).placeholder(i2).transform(new CenterCrop(imageView.getContext()), new FilletTransformation(imageView.getContext(), i)).into(imageView);
    }

    public static <T> void loadImgFillet(ImageView imageView, T t, int i, int i2, int i3, int i4, SimpleTarget<GlideDrawable> simpleTarget) {
        if (imageView == null || t == null) {
            return;
        }
        if (i3 == 0 || i4 == 0) {
            loadImgFillet(imageView, t, i, i2);
        } else {
            Glide.with(imageView.getContext()).load(t).override(i3, i4).centerCrop().signature((Key) new StringSignature(signature)).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(i2).transform(new CenterCrop(imageView.getContext()), new FilletTransformation(imageView.getContext(), i)).into(simpleTarget);
        }
    }

    public static <T> void loadImgFillet(ImageView imageView, T t, int i, int i2, int i3, int i4) {
        if (imageView == null || t == null) {
            return;
        }
        if (i3 == 0 || i4 == 0) {
            loadImgFillet(imageView, t, i, i2);
        } else {
            Glide.with(imageView.getContext()).load(t).override(i3, i4).centerCrop().crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(i2).transform(new CenterCrop(imageView.getContext()), new FilletTransformation(imageView.getContext(), i)).into(imageView);
        }
    }

    public static void loadBitmap(ImageView imageView, Bitmap bitmap, int i) {
        if (imageView == null || bitmap == null) {
            return;
        }
        BitmapDrawable bitmapDrawable = new BitmapDrawable(imageView.getResources(), bitmap);
        Glide.with(imageView.getContext()).load("").placeholder((Drawable) bitmapDrawable).error((Drawable) bitmapDrawable).transform(new CenterCrop(imageView.getContext()), new FilletTransformation(imageView.getContext(), i)).into(imageView);
    }

    public static void loadResource(ImageView imageView, int i, int i2) {
        if (imageView == null || i == 0) {
            return;
        }
        Drawable drawable = ResourceUtil.getDrawable(i);
        Glide.with(imageView.getContext()).load("").placeholder(drawable).error(drawable).transform(new CenterCrop(imageView.getContext()), new FilletTransformation(imageView.getContext(), i2)).into(imageView);
    }

    public static void loadBitmap(ImageView imageView, Bitmap bitmap) {
        if (imageView == null || bitmap == null) {
            return;
        }
        BitmapDrawable bitmapDrawable = new BitmapDrawable(imageView.getResources(), bitmap);
        Glide.with(imageView.getContext()).load("").placeholder((Drawable) bitmapDrawable).error((Drawable) bitmapDrawable).into(imageView);
    }

    public static void loadResource(ImageView imageView, int i) {
        if (imageView == null || i == 0) {
            return;
        }
        Drawable drawable = ResourceUtil.getDrawable(i);
        Glide.with(imageView.getContext()).load("").placeholder(drawable).error(drawable).into(imageView);
    }

    public static <T> void loadImgFillet(ImageView imageView, T t, boolean z, int i, int i2, int i3, int i4) {
        if (imageView == null || t == null) {
            return;
        }
        if (i3 == 0 || i4 == 0) {
            loadImgFillet(imageView, t, i, i2);
            return;
        }
        DrawableRequestBuilder<T> drawableRequestBuilderTransform = Glide.with(imageView.getContext()).load(t).override(i3, i4).centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE).crossFade().placeholder(i2).transform(new CenterCrop(imageView.getContext()), new FilletTransformation(imageView.getContext(), i));
        if (z) {
            drawableRequestBuilderTransform.signature((Key) new StringSignature(System.currentTimeMillis() + ""));
        }
        drawableRequestBuilderTransform.into(imageView);
    }

    public static <T> void loadImgFillet(ImageView imageView, T t, int i, int i2, int i3, SimpleTarget<GlideDrawable> simpleTarget) {
        if (imageView == null || t == null) {
            return;
        }
        Glide.with(imageView.getContext()).load(t).crossFade().override(i2, i3).centerCrop().signature((Key) new StringSignature(signature)).dontAnimate().transform(new CenterCrop(imageView.getContext()), new FilletTransformation(imageView.getContext(), i)).into(simpleTarget);
    }

    public static <T> void loadImgFillet(ImageView imageView, T t, boolean z, int i, SimpleTarget<GlideDrawable> simpleTarget) {
        if (imageView == null || t == null) {
            return;
        }
        DrawableRequestBuilder<T> drawableRequestBuilderTransform = Glide.with(imageView.getContext()).load(t).crossFade().dontAnimate().diskCacheStrategy(DiskCacheStrategy.SOURCE).transform(new CenterCrop(imageView.getContext()), new FilletTransformation(imageView.getContext(), i));
        if (z) {
            drawableRequestBuilderTransform.signature((Key) new StringSignature(System.currentTimeMillis() + ""));
        }
        drawableRequestBuilderTransform.into(simpleTarget);
    }

    public static <T> void loadImgFillet(ImageView imageView, T t, boolean z, int i, RequestListener<T, GlideDrawable> requestListener) {
        if (imageView == null || t == null) {
            return;
        }
        DrawableRequestBuilder<T> drawableRequestBuilderTransform = Glide.with(imageView.getContext()).load(t).listener((RequestListener<? super T, GlideDrawable>) requestListener).crossFade().dontAnimate().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.SOURCE).transform(new CenterCrop(imageView.getContext()), new FilletTransformation(imageView.getContext(), i));
        if (z) {
            drawableRequestBuilderTransform.signature((Key) new StringSignature(System.currentTimeMillis() + ""));
        }
        drawableRequestBuilderTransform.into(imageView);
    }

    public static <T> void loadImgFillet(ImageView imageView, T t, int i, SimpleTarget<GlideDrawable> simpleTarget) {
        if (imageView == null || t == null) {
            return;
        }
        Glide.with(imageView.getContext()).load(t).crossFade().dontAnimate().transform(new CenterCrop(imageView.getContext()), new FilletTransformation(imageView.getContext(), i)).into(simpleTarget);
    }

    public static <T> void loadImgFillet(ImageView imageView, T t, SimpleTarget<Bitmap> simpleTarget) {
        if (imageView == null || t == null) {
            return;
        }
        Glide.with(imageView.getContext()).load(t).asBitmap().into(simpleTarget);
    }

    public static <T> void loadImgFillet(Context context, T t, SimpleTarget<Bitmap> simpleTarget) {
        if (context == null || t == null) {
            return;
        }
        Glide.with(context).load(t).asBitmap().signature((Key) new StringSignature(System.currentTimeMillis() + "")).into(simpleTarget);
    }

    public static <T> void loadImgCross(ImageView imageView, T t, int i) {
        if (imageView == null || t == null) {
            return;
        }
        Glide.with(imageView.getContext()).load(t).crossFade().transform(new CenterCrop(imageView.getContext()), new FilletTransformation(imageView.getContext(), i)).into(imageView);
    }

    public static <T> void loadImgFilletMap(Context context, T t, int i, int i2, ImageViewTarget<Bitmap> imageViewTarget) {
        if (context == null || t == null || imageViewTarget == null) {
            return;
        }
        Glide.with(context).load(t).asBitmap().placeholder(i2).transform(new FilletTransformation(context, i)).into(imageViewTarget);
    }

    public static <T> void loadImgFilletUnique(ImageView imageView, T t, int i, int i2) {
        if (imageView == null || t == null) {
            return;
        }
        Glide.with(imageView.getContext()).load(t).crossFade().centerCrop().placeholder(i2).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).placeholder(i2).transform(new CenterCrop(imageView.getContext()), new FilletTransformation(imageView.getContext(), i)).into(imageView);
    }

    public static <T> void loadImgCircleUnique(ImageView imageView, T t, int i) {
        if (imageView == null || t == null) {
            return;
        }
        Glide.with(imageView.getContext()).load(t).crossFade().centerCrop().placeholder(i).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).placeholder(i).transform(new CenterCrop(imageView.getContext()), new GlideCircleTransform(imageView.getContext())).into(imageView);
    }

    public static void loadCircleImageDefault(ImageView imageView, String str, int i) {
        if (imageView == null) {
            return;
        }
        Glide.with(imageView.getContext()).load(str).asBitmap().centerCrop().placeholder(i).centerCrop().into(new BitmapImageViewTarget(imageView) { // from class: com.ido.common.utils.ImageLoaderUtil.2
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.bumptech.glide.request.target.BitmapImageViewTarget, com.bumptech.glide.request.target.ImageViewTarget
            public void setResource(Bitmap bitmap) {
                RoundedBitmapDrawable roundedBitmapDrawableCreate = RoundedBitmapDrawableFactory.create(((ImageView) this.view).getResources(), bitmap);
                roundedBitmapDrawableCreate.setCircular(true);
                ((ImageView) this.view).setImageDrawable(roundedBitmapDrawableCreate);
            }
        });
    }

    public static <T> void loadImgUnique(ImageView imageView, T t) {
        if (imageView == null || t == null) {
            return;
        }
        Glide.with(imageView.getContext()).load(t).crossFade().centerCrop().signature((Key) new StringSignature(String.valueOf(System.currentTimeMillis()))).transform(new CenterCrop(imageView.getContext())).into(imageView);
    }

    public static <T> Bitmap readBitmap(Context context, T t) throws ExecutionException, InterruptedException {
        if (context == null || t == null) {
            return null;
        }
        return Glide.with(context).load(t).asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE).into(Integer.MIN_VALUE, Integer.MIN_VALUE).get();
    }

    public static <T> void readBitmap(Context context, T t, SimpleTarget<Bitmap> simpleTarget) {
        if (context == null || simpleTarget == null || t == null) {
            return;
        }
        Glide.with(context).load(t).asBitmap().into(simpleTarget);
    }

    public static <T> void readCircleBitmap(Context context, T t, SimpleTarget<Bitmap> simpleTarget) {
        if (context == null || simpleTarget == null) {
            return;
        }
        Glide.with(context).load(t).asBitmap().transform(new CenterCrop(context), new GlideCircleTransform(context)).into(simpleTarget);
    }

    public static <T> void downloadImg(Context context, T t, SimpleTarget<File> simpleTarget) {
        if (context == null || t == null || simpleTarget == null) {
            return;
        }
        Glide.with(context).load(t).downloadOnly(simpleTarget);
    }

    public static <T> void loadImgScale(final ImageView imageView, T t, int i) {
        if (imageView == null || t == null) {
            return;
        }
        Glide.with(imageView.getContext()).load(t).placeholder(i).error(i).listener((RequestListener<? super T, GlideDrawable>) new RequestListener<T, GlideDrawable>() { // from class: com.ido.common.utils.ImageLoaderUtil.3
            @Override // com.bumptech.glide.request.RequestListener
            public boolean onException(Exception exc, T t2, Target<GlideDrawable> target, boolean z) {
                ImageView imageView2 = imageView;
                if (imageView2 == null) {
                    return false;
                }
                int minimumHeight = imageView2.getMinimumHeight();
                ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                layoutParams.height = minimumHeight;
                imageView.setLayoutParams(layoutParams);
                return false;
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onResourceReady(GlideDrawable glideDrawable, T t2, Target<GlideDrawable> target, boolean z, boolean z2) {
                ImageView imageView2 = imageView;
                if (imageView2 == null) {
                    return false;
                }
                if (imageView2.getScaleType() != ImageView.ScaleType.FIT_XY) {
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                }
                ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                layoutParams.height = Math.round(glideDrawable.getIntrinsicHeight() * (((imageView.getWidth() - imageView.getPaddingLeft()) - imageView.getPaddingRight()) / glideDrawable.getIntrinsicWidth())) + imageView.getPaddingTop() + imageView.getPaddingBottom();
                imageView.setLayoutParams(layoutParams);
                return false;
            }
        }).into(imageView);
    }

    public static void clearMemory(Context context) {
        if (context == null) {
            return;
        }
        Glide.get(context).clearMemory();
        System.gc();
    }

    public static void clear(View view) {
        if (view != null) {
            Glide.clear(view);
        }
    }
}