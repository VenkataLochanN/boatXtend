package com.ido.life.base;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lzy.imagepicker.loader.ImageLoader;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class GlideImageLoader implements ImageLoader {
    @Override // com.lzy.imagepicker.loader.ImageLoader
    public void clearMemoryCache() {
    }

    @Override // com.lzy.imagepicker.loader.ImageLoader
    public void displayImage(Activity activity, String str, ImageView imageView, int i, int i2) {
        Glide.with(activity).load(Uri.fromFile(new File(str))).diskCacheStrategy(DiskCacheStrategy.RESULT).into(imageView);
    }

    @Override // com.lzy.imagepicker.loader.ImageLoader
    public void displayImagePreview(Activity activity, String str, ImageView imageView, int i, int i2) {
        Glide.with(activity).load(Uri.fromFile(new File(str))).diskCacheStrategy(DiskCacheStrategy.RESULT).into(imageView);
    }

    @Override // com.lzy.imagepicker.loader.ImageLoader
    public void displayImage(Activity activity, Uri uri, ImageView imageView, int i, int i2) {
        Glide.with(activity).load(uri).diskCacheStrategy(DiskCacheStrategy.RESULT).into(imageView);
    }

    @Override // com.lzy.imagepicker.loader.ImageLoader
    public void displayImagePreview(Activity activity, Uri uri, ImageView imageView, int i, int i2) {
        Glide.with(activity).load(uri).diskCacheStrategy(DiskCacheStrategy.RESULT).into(imageView);
    }
}