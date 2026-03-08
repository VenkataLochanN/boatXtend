package com.ido.life.customview.cardview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
class CardViewApi21Impl implements CardViewImpl {
    @Override // com.ido.life.customview.cardview.CardViewImpl
    public void initStatic() {
    }

    CardViewApi21Impl() {
    }

    @Override // com.ido.life.customview.cardview.CardViewImpl
    public void initialize(CardViewDelegate cardViewDelegate, Context context, ColorStateList colorStateList, float f2, float f3, float f4, int i, int i2) {
        cardViewDelegate.setCardBackground(new RoundRectDrawable(colorStateList, f2));
        View cardView = cardViewDelegate.getCardView();
        cardView.setClipToOutline(true);
        cardView.setElevation(f3);
        setMaxElevation(cardViewDelegate, f4);
    }

    @Override // com.ido.life.customview.cardview.CardViewImpl
    public void setRadius(CardViewDelegate cardViewDelegate, float f2) {
        getCardBackground(cardViewDelegate).setRadius(f2);
    }

    @Override // com.ido.life.customview.cardview.CardViewImpl
    public void setMaxElevation(CardViewDelegate cardViewDelegate, float f2) {
        getCardBackground(cardViewDelegate).setPadding(f2, cardViewDelegate.getUseCompatPadding(), cardViewDelegate.getPreventCornerOverlap());
        updatePadding(cardViewDelegate);
    }

    @Override // com.ido.life.customview.cardview.CardViewImpl
    public float getMaxElevation(CardViewDelegate cardViewDelegate) {
        return getCardBackground(cardViewDelegate).getPadding();
    }

    @Override // com.ido.life.customview.cardview.CardViewImpl
    public float getMinWidth(CardViewDelegate cardViewDelegate) {
        return getRadius(cardViewDelegate) * 2.0f;
    }

    @Override // com.ido.life.customview.cardview.CardViewImpl
    public float getMinHeight(CardViewDelegate cardViewDelegate) {
        return getRadius(cardViewDelegate) * 2.0f;
    }

    @Override // com.ido.life.customview.cardview.CardViewImpl
    public float getRadius(CardViewDelegate cardViewDelegate) {
        return getCardBackground(cardViewDelegate).getRadius();
    }

    @Override // com.ido.life.customview.cardview.CardViewImpl
    public void setElevation(CardViewDelegate cardViewDelegate, float f2) {
        cardViewDelegate.getCardView().setElevation(f2);
    }

    @Override // com.ido.life.customview.cardview.CardViewImpl
    public float getElevation(CardViewDelegate cardViewDelegate) {
        return cardViewDelegate.getCardView().getElevation();
    }

    @Override // com.ido.life.customview.cardview.CardViewImpl
    public void updatePadding(CardViewDelegate cardViewDelegate) {
        if (!cardViewDelegate.getUseCompatPadding()) {
            cardViewDelegate.setShadowPadding(0, 0, 0, 0);
            return;
        }
        float maxElevation = getMaxElevation(cardViewDelegate);
        float radius = getRadius(cardViewDelegate);
        int iCeil = (int) Math.ceil(RoundRectDrawableWithShadow.calculateHorizontalPadding(maxElevation, radius, cardViewDelegate.getPreventCornerOverlap()));
        int iCeil2 = (int) Math.ceil(RoundRectDrawableWithShadow.calculateVerticalPadding(maxElevation, radius, cardViewDelegate.getPreventCornerOverlap()));
        cardViewDelegate.setShadowPadding(iCeil, iCeil2, iCeil, iCeil2);
    }

    @Override // com.ido.life.customview.cardview.CardViewImpl
    public void onCompatPaddingChanged(CardViewDelegate cardViewDelegate) {
        setMaxElevation(cardViewDelegate, getMaxElevation(cardViewDelegate));
    }

    @Override // com.ido.life.customview.cardview.CardViewImpl
    public void onPreventCornerOverlapChanged(CardViewDelegate cardViewDelegate) {
        setMaxElevation(cardViewDelegate, getMaxElevation(cardViewDelegate));
    }

    @Override // com.ido.life.customview.cardview.CardViewImpl
    public void setBackgroundColor(CardViewDelegate cardViewDelegate, ColorStateList colorStateList) {
        getCardBackground(cardViewDelegate).setColor(colorStateList);
    }

    @Override // com.ido.life.customview.cardview.CardViewImpl
    public ColorStateList getBackgroundColor(CardViewDelegate cardViewDelegate) {
        return getCardBackground(cardViewDelegate).getColor();
    }

    private RoundRectDrawable getCardBackground(CardViewDelegate cardViewDelegate) {
        return (RoundRectDrawable) cardViewDelegate.getCardBackground();
    }
}