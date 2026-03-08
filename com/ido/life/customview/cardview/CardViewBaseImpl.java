package com.ido.life.customview.cardview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.ido.life.customview.cardview.RoundRectDrawableWithShadow;

/* JADX INFO: loaded from: classes2.dex */
class CardViewBaseImpl implements CardViewImpl {
    final RectF mCornerRect = new RectF();
    private RoundRectDrawableWithShadow mRoundRectShadow = null;

    @Override // com.ido.life.customview.cardview.CardViewImpl
    public void onCompatPaddingChanged(CardViewDelegate cardViewDelegate) {
    }

    CardViewBaseImpl() {
    }

    @Override // com.ido.life.customview.cardview.CardViewImpl
    public void initStatic() {
        RoundRectDrawableWithShadow.sRoundRectHelper = new RoundRectDrawableWithShadow.RoundRectHelper() { // from class: com.ido.life.customview.cardview.CardViewBaseImpl.1
            @Override // com.ido.life.customview.cardview.RoundRectDrawableWithShadow.RoundRectHelper
            public void drawRoundRect(Canvas canvas, RectF rectF, float f2, Paint paint) {
                float f3 = 2.0f * f2;
                float fWidth = (rectF.width() - f3) - 1.0f;
                float fHeight = (rectF.height() - f3) - 1.0f;
                if (f2 >= 1.0f) {
                    float f4 = f2 + 0.5f;
                    float f5 = -f4;
                    CardViewBaseImpl.this.mCornerRect.set(f5, f5, f4, f4);
                    int iSave = canvas.save();
                    canvas.translate(rectF.left + f4, rectF.top + f4);
                    canvas.drawArc(CardViewBaseImpl.this.mCornerRect, 180.0f, 90.0f, true, paint);
                    canvas.translate(fWidth, 0.0f);
                    canvas.rotate(90.0f);
                    canvas.drawArc(CardViewBaseImpl.this.mCornerRect, 180.0f, 90.0f, true, paint);
                    canvas.translate(fHeight, 0.0f);
                    canvas.rotate(90.0f);
                    canvas.drawArc(CardViewBaseImpl.this.mCornerRect, 180.0f, 90.0f, true, paint);
                    canvas.translate(fWidth, 0.0f);
                    canvas.rotate(90.0f);
                    canvas.drawArc(CardViewBaseImpl.this.mCornerRect, 180.0f, 90.0f, true, paint);
                    canvas.restoreToCount(iSave);
                    canvas.drawRect((rectF.left + f4) - 1.0f, rectF.top, (rectF.right - f4) + 1.0f, rectF.top + f4, paint);
                    canvas.drawRect((rectF.left + f4) - 1.0f, rectF.bottom - f4, (rectF.right - f4) + 1.0f, rectF.bottom, paint);
                }
                canvas.drawRect(rectF.left, rectF.top + f2, rectF.right, rectF.bottom - f2, paint);
            }
        };
    }

    @Override // com.ido.life.customview.cardview.CardViewImpl
    public void initialize(CardViewDelegate cardViewDelegate, Context context, ColorStateList colorStateList, float f2, float f3, float f4, int i, int i2) {
        this.mRoundRectShadow = createBackground(context, colorStateList, f2, f3, f4);
        this.mRoundRectShadow.setAddPaddingForCorners(cardViewDelegate.getPreventCornerOverlap());
        this.mRoundRectShadow.setShadowStartColor(i);
        this.mRoundRectShadow.setShadowEndColor(i2);
        cardViewDelegate.setCardBackground(this.mRoundRectShadow);
        updatePadding(cardViewDelegate);
    }

    private RoundRectDrawableWithShadow createBackground(Context context, ColorStateList colorStateList, float f2, float f3, float f4) {
        return new RoundRectDrawableWithShadow(context.getResources(), colorStateList, f2, f3, f4);
    }

    @Override // com.ido.life.customview.cardview.CardViewImpl
    public void updatePadding(CardViewDelegate cardViewDelegate) {
        Rect rect = new Rect();
        getShadowBackground(cardViewDelegate).getMaxShadowAndCornerPadding(rect);
        cardViewDelegate.setMinWidthHeightInternal((int) Math.ceil(getMinWidth(cardViewDelegate)), (int) Math.ceil(getMinHeight(cardViewDelegate)));
        cardViewDelegate.setShadowPadding(rect.left, rect.top, rect.right, rect.bottom);
    }

    @Override // com.ido.life.customview.cardview.CardViewImpl
    public void onPreventCornerOverlapChanged(CardViewDelegate cardViewDelegate) {
        getShadowBackground(cardViewDelegate).setAddPaddingForCorners(cardViewDelegate.getPreventCornerOverlap());
        updatePadding(cardViewDelegate);
    }

    @Override // com.ido.life.customview.cardview.CardViewImpl
    public void setBackgroundColor(CardViewDelegate cardViewDelegate, ColorStateList colorStateList) {
        getShadowBackground(cardViewDelegate).setColor(colorStateList);
    }

    @Override // com.ido.life.customview.cardview.CardViewImpl
    public ColorStateList getBackgroundColor(CardViewDelegate cardViewDelegate) {
        return getShadowBackground(cardViewDelegate).getColor();
    }

    @Override // com.ido.life.customview.cardview.CardViewImpl
    public void setRadius(CardViewDelegate cardViewDelegate, float f2) {
        getShadowBackground(cardViewDelegate).setCornerRadius(f2);
        updatePadding(cardViewDelegate);
    }

    @Override // com.ido.life.customview.cardview.CardViewImpl
    public float getRadius(CardViewDelegate cardViewDelegate) {
        return getShadowBackground(cardViewDelegate).getCornerRadius();
    }

    @Override // com.ido.life.customview.cardview.CardViewImpl
    public void setElevation(CardViewDelegate cardViewDelegate, float f2) {
        getShadowBackground(cardViewDelegate).setShadowSize(f2);
    }

    @Override // com.ido.life.customview.cardview.CardViewImpl
    public float getElevation(CardViewDelegate cardViewDelegate) {
        return getShadowBackground(cardViewDelegate).getShadowSize();
    }

    @Override // com.ido.life.customview.cardview.CardViewImpl
    public void setMaxElevation(CardViewDelegate cardViewDelegate, float f2) {
        getShadowBackground(cardViewDelegate).setMaxShadowSize(f2);
        updatePadding(cardViewDelegate);
    }

    @Override // com.ido.life.customview.cardview.CardViewImpl
    public float getMaxElevation(CardViewDelegate cardViewDelegate) {
        return getShadowBackground(cardViewDelegate).getMaxShadowSize();
    }

    @Override // com.ido.life.customview.cardview.CardViewImpl
    public float getMinWidth(CardViewDelegate cardViewDelegate) {
        return getShadowBackground(cardViewDelegate).getMinWidth();
    }

    @Override // com.ido.life.customview.cardview.CardViewImpl
    public float getMinHeight(CardViewDelegate cardViewDelegate) {
        return getShadowBackground(cardViewDelegate).getMinHeight();
    }

    private RoundRectDrawableWithShadow getShadowBackground(CardViewDelegate cardViewDelegate) {
        return (RoundRectDrawableWithShadow) cardViewDelegate.getCardBackground();
    }

    public void setShadowStartColor(CardViewDelegate cardViewDelegate, int i) {
        RoundRectDrawableWithShadow roundRectDrawableWithShadow = this.mRoundRectShadow;
        if (roundRectDrawableWithShadow != null) {
            roundRectDrawableWithShadow.setShadowStartColor(i);
            cardViewDelegate.setCardBackground(this.mRoundRectShadow);
            updatePadding(cardViewDelegate);
        }
    }

    public void setShadowEndColor(CardViewDelegate cardViewDelegate, int i) {
        RoundRectDrawableWithShadow roundRectDrawableWithShadow = this.mRoundRectShadow;
        if (roundRectDrawableWithShadow != null) {
            roundRectDrawableWithShadow.setShadowEndColor(i);
            cardViewDelegate.setCardBackground(this.mRoundRectShadow);
            updatePadding(cardViewDelegate);
        }
    }
}