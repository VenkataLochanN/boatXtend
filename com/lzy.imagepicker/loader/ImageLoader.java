package com.lzy.imagepicker.loader;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;
import java.io.Serializable;

/* JADX INFO: loaded from: classes3.dex */
public interface ImageLoader extends Serializable {
    void clearMemoryCache();

    void displayImage(Activity activity, Uri uri, ImageView imageView, int i, int i2);

    void displayImage(Activity activity, String str, ImageView imageView, int i, int i2);

    void displayImagePreview(Activity activity, Uri uri, ImageView imageView, int i, int i2);

    void displayImagePreview(Activity activity, String str, ImageView imageView, int i, int i2);
}