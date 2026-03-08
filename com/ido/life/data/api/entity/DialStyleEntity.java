package com.ido.life.data.api.entity;

import android.graphics.drawable.Drawable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DialStyleEntity {
    private int canChangeColor;
    private String description;
    private List<Drawable> styleimg;

    public int getCanChangeColor() {
        return this.canChangeColor;
    }

    public void setCanChangeColor(int i) {
        this.canChangeColor = i;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public List<Drawable> getStyleimg() {
        return this.styleimg;
    }

    public void setStyleimg(List<Drawable> list) {
        this.styleimg = list;
    }
}