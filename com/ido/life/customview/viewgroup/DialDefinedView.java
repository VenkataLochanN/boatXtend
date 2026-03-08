package com.ido.life.customview.viewgroup;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.boat.Xtend.two.R;
import com.ido.life.data.api.entity.DialDefinedEntityNew;

/* JADX INFO: loaded from: classes2.dex */
public class DialDefinedView {
    public static View addView(int i, Context context, Drawable drawable, DialDefinedEntityNew.Colors colors, boolean z, boolean z2) {
        View viewInflate;
        if (z2) {
            viewInflate = LayoutInflater.from(context).inflate(R.layout.item_style_dial_image, (ViewGroup) null);
        } else {
            viewInflate = LayoutInflater.from(context).inflate(R.layout.item_preview_dial_image, (ViewGroup) null);
        }
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.style_img);
        imageView.setImageDrawable(drawable);
        if (z2) {
            if (z) {
                if (colors.getStyle() != null && !TextUtils.isEmpty(colors.getStyle().get(i))) {
                    imageView.setColorFilter(Color.parseColor(colors.getStyle().get(i)));
                }
            } else if (!TextUtils.isEmpty(colors.getStylemodul().get(i))) {
                imageView.setColorFilter(Color.parseColor(colors.getStylemodul().get(i)));
            }
        } else if (z) {
            if (!TextUtils.isEmpty(colors.getStyle().get(i))) {
                imageView.setColorFilter(Color.parseColor(colors.getStyle().get(i)));
            }
        } else if (!TextUtils.isEmpty(colors.getPreviewmodul().get(i))) {
            if (colors.getPreviewmodul().get(i).contains("*")) {
                imageView.setColorFilter(Color.parseColor(colors.getPreviewmodul().get(i).split("\\*")[1]));
            } else {
                imageView.setColorFilter(Color.parseColor(colors.getPreviewmodul().get(i)));
            }
        }
        return viewInflate;
    }

    public static View addView(Context context, Drawable drawable, View view) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.item_preview_dial_image, (ViewGroup) null);
        ((ImageView) viewInflate.findViewById(R.id.style_img)).setImageDrawable(drawable);
        return viewInflate;
    }
}