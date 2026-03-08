package com.ido.life.database.model;

/* JADX INFO: loaded from: classes2.dex */
public class UserAgreementAndPrivacyVersionData {
    private String pageType;
    private Long version;

    public String getPageType() {
        return this.pageType;
    }

    public void setPageType(String str) {
        this.pageType = str;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long l) {
        this.version = l;
    }

    public String toString() {
        return "UserAgreementAndPrivacyVersionData{pageType='" + this.pageType + "', version=" + this.version + '}';
    }
}