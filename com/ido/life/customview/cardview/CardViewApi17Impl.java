package com.ido.life.customview.cardview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import com.ido.life.customview.cardview.RoundRectDrawableWithShadow;

/* JADX INFO: loaded from: classes2.dex */
class CardViewApi17Impl extends CardViewBaseImpl {
    CardViewApi17Impl() {
    }

    @Override // com.ido.life.customview.cardview.CardViewBaseImpl, com.ido.life.customview.cardview.CardViewImpl
    public void initStatic() {
        RoundRectDrawableWithShadow.sRoundRectHelper = new RoundRectDrawableWithShadow.RoundRectHelper() { // from class: com.ido.life.customview.cardview.CardViewApi17Impl.1
            @Override // com.ido.life.customview.cardview.RoundRectDrawableWithShadow.RoundRectHelper
            public void drawRoundRect(Canvas canvas, RectF rectF, float f2, Paint paint) {
                canvas.drawRoundRect(rectF, f2, f2, paint);
            }
        };
    }
}