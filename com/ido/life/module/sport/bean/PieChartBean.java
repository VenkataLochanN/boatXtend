package com.ido.life.module.sport.bean;

/* JADX INFO: loaded from: classes2.dex */
public class PieChartBean {
    private int color;
    private boolean isCheck;
    private boolean isOpenCheck;
    private float value;

    public PieChartBean() {
    }

    public PieChartBean(int i, float f2) {
        this.color = i;
        this.value = f2;
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int i) {
        this.color = i;
    }

    public float getValue() {
        return this.value;
    }

    public void setValue(float f2) {
        this.value = f2;
    }

    public boolean isOpenCheck() {
        return this.isOpenCheck;
    }

    public void setOpenCheck(boolean z) {
        this.isOpenCheck = z;
    }

    public boolean isCheck() {
        return this.isCheck;
    }

    public void setCheck(boolean z) {
        this.isCheck = z;
    }
}