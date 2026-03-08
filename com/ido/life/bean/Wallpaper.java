package com.ido.life.bean;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class Wallpaper implements Serializable {
    private String fileName;
    private int format;
    private String saveFileName;

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public String getSaveFileName() {
        return this.saveFileName;
    }

    public void setSaveFileName(String str) {
        this.saveFileName = str;
    }

    public int getFormat() {
        return this.format;
    }

    public void setFormat(int i) {
        this.format = i;
    }
}