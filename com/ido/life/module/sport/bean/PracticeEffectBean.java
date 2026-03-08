package com.ido.life.module.sport.bean;

/* JADX INFO: loaded from: classes2.dex */
public class PracticeEffectBean {
    private String color;
    private String name;
    private String progress_text;

    public String getName() {
        return this.name;
    }

    public String getProgress_text() {
        return this.progress_text;
    }

    public void setProgress_text(String str) {
        this.progress_text = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public PracticeEffectBean(String str, String str2) {
        this.name = str;
        this.color = str2;
    }

    public PracticeEffectBean(String str, String str2, String str3) {
        this.name = str;
        this.color = str2;
        this.progress_text = str3;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String str) {
        this.color = str;
    }
}