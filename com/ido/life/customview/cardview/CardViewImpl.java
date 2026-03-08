package com.ido.life.customview.cardview;

import android.content.Context;
import android.content.res.ColorStateList;

/* JADX INFO: loaded from: classes2.dex */
interface CardViewImpl {
    ColorStateList getBackgroundColor(CardViewDelegate cardViewDelegate);

    float getElevation(CardViewDelegate cardViewDelegate);

    float getMaxElevation(CardViewDelegate cardViewDelegate);

    float getMinHeight(CardViewDelegate cardViewDelegate);

    float getMinWidth(CardViewDelegate cardViewDelegate);

    float getRadius(CardViewDelegate cardViewDelegate);

    void initStatic();

    void initialize(CardViewDelegate cardViewDelegate, Context context, ColorStateList colorStateList, float f2, float f3, float f4, int i, int i2);

    void onCompatPaddingChanged(CardViewDelegate cardViewDelegate);

    void onPreventCornerOverlapChanged(CardViewDelegate cardViewDelegate);

    void setBackgroundColor(CardViewDelegate cardViewDelegate, ColorStateList colorStateList);

    void setElevation(CardViewDelegate cardViewDelegate, float f2);

    void setMaxElevation(CardViewDelegate cardViewDelegate, float f2);

    void setRadius(CardViewDelegate cardViewDelegate, float f2);

    void updatePadding(CardViewDelegate cardViewDelegate);
}