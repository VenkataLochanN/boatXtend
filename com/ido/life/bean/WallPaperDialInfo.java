package com.ido.life.bean;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class WallPaperDialInfo implements Serializable {
    private int fileSize;
    private int id;
    private String imageUrl;
    private String linkUrl;
    private String otaFaceName;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getLinkUrl() {
        return this.linkUrl;
    }

    public void setLinkUrl(String str) {
        this.linkUrl = str;
    }

    public int getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(int i) {
        this.fileSize = i;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public String getOtaFaceName() {
        return this.otaFaceName;
    }

    public void setOtaFaceName(String str) {
        this.otaFaceName = str;
    }

    public String toString() {
        return "WallPaperDialInfo{id=" + this.id + ", linkUrl='" + this.linkUrl + "', fileSize=" + this.fileSize + ", imageUrl='" + this.imageUrl + "', otaFaceName='" + this.otaFaceName + "'}";
    }
}