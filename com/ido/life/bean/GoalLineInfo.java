package com.ido.life.bean;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public class GoalLineInfo {
    private String mGoalLabel;
    private int mLineOrientation;
    private float mPosition;
    private int mValue;

    @Retention(RetentionPolicy.SOURCE)
    public @interface GoalLineInfoFlags {
    }

    public GoalLineInfo(int i, int i2, float f2, String str) {
        this.mLineOrientation = i;
        this.mValue = i2;
        this.mPosition = f2;
        this.mGoalLabel = str;
    }

    public int getLineOrientation() {
        return this.mLineOrientation;
    }

    public void setLineOrientation(int i) {
        this.mLineOrientation = i;
    }

    public int getValue() {
        return this.mValue;
    }

    public void setValue(int i) {
        this.mValue = i;
    }

    public String getGoalLabel() {
        return this.mGoalLabel;
    }

    public void setGoalLabel(String str) {
        this.mGoalLabel = str;
    }

    public float getPosition() {
        return this.mPosition;
    }

    public void setPosition(float f2) {
        this.mPosition = f2;
    }
}