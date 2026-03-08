package com.ido.life.customview.viewgroup;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.boat.Xtend.two.R;
import com.ido.life.data.api.entity.DialDefinedFunctionEntity;

/* JADX INFO: loaded from: classes2.dex */
public class DialDefinedFunctionView {
    public static View addView(int i, Context context, Drawable drawable, DialDefinedFunctionEntity.PaletteColor paletteColor, boolean z) {
        View viewInflate;
        if (z) {
            viewInflate = LayoutInflater.from(context).inflate(R.layout.item_style_dial_image, (ViewGroup) null);
        } else {
            viewInflate = LayoutInflater.from(context).inflate(R.layout.item_preview_dial_image, (ViewGroup) null);
        }
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.style_img);
        imageView.setImageDrawable(drawable);
        if (paletteColor != null) {
            if (paletteColor.getColors().contains("*")) {
                imageView.setColorFilter(Color.parseColor(paletteColor.getColors().split("\\*")[i]));
            } else {
                imageView.setColorFilter(Color.parseColor(paletteColor.getColors()));
            }
        }
        return viewInflate;
    }

    public static View addView(Context context, Drawable drawable) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.item_preview_dial_image, (ViewGroup) null);
        ((ImageView) viewInflate.findViewById(R.id.style_img)).setImageDrawable(drawable);
        return viewInflate;
    }

    public static View addViewFromColor(Context context, String str) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.item_preview_dial_image, (ViewGroup) null);
        ((ImageView) viewInflate.findViewById(R.id.style_img)).setBackgroundColor(Color.parseColor(str));
        return viewInflate;
    }

    public static View addShadeView(Context context, int i, int i2) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.item_preview_dial_image, (ViewGroup) null);
        RelativeLayout relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.dial_image_rl);
        ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
        relativeLayout.setBackgroundResource(R.drawable.dial_function_edit_shape_bg);
        layoutParams.width = i;
        layoutParams.height = i2;
        relativeLayout.setLayoutParams(layoutParams);
        return viewInflate;
    }

    public static View addView(Context context, Drawable drawable, View view) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.item_preview_dial_image, (ViewGroup) null);
        ((ImageView) viewInflate.findViewById(R.id.style_img)).setImageDrawable(drawable);
        return viewInflate;
    }
}