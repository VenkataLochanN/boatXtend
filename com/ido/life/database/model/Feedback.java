package com.ido.life.database.model;

/* JADX INFO: loaded from: classes2.dex */
public class Feedback {
    private String appVersion;
    private String body;
    private String contact;
    private String country;
    private String deviceSId;
    private String feedbackType;
    private int id;
    private String image1Url;
    private String image2Url;
    private String image3Url;
    private String language;
    private String linkUrl;
    private String mobileType;
    private int osName;
    private String osVersion;
    private String otaVersio;
    private String sourceMac;
    private int typeId;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getTypeId() {
        return this.typeId;
    }

    public void setTypeId(int i) {
        this.typeId = i;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String str) {
        this.body = str;
    }

    public String getImage1Url() {
        return this.image1Url;
    }

    public void setImage1Url(String str) {
        this.image1Url = str;
    }

    public String getImage2Url() {
        return this.image2Url;
    }

    public void setImage2Url(String str) {
        this.image2Url = str;
    }

    public String getImage3Url() {
        return this.image3Url;
    }

    public void setImage3Url(String str) {
        this.image3Url = str;
    }

    public String getLinkUrl() {
        return this.linkUrl;
    }

    public void setLinkUrl(String str) {
        this.linkUrl = str;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public String getDeviceSId() {
        return this.deviceSId;
    }

    public void setDeviceSId(String str) {
        this.deviceSId = str;
    }

    public String getOtaVersio() {
        return this.otaVersio;
    }

    public void setOtaVersio(String str) {
        this.otaVersio = str;
    }

    public String getMobileType() {
        return this.mobileType;
    }

    public void setMobileType(String str) {
        this.mobileType = str;
    }

    public int getOsName() {
        return this.osName;
    }

    public void setOsName(int i) {
        this.osName = i;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public void setOsVersion(String str) {
        this.osVersion = str;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public String getSourceMac() {
        return this.sourceMac;
    }

    public void setSourceMac(String str) {
        this.sourceMac = str;
    }

    public String getFeedbackType() {
        return this.feedbackType;
    }

    public void setFeedbackType(String str) {
        this.feedbackType = str;
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String str) {
        this.contact = str;
    }

    public String toString() {
        return "Feedback{id=" + this.id + ", typeId=" + this.typeId + ", body='" + this.body + "', image1Url='" + this.image1Url + "', image2Url='" + this.image2Url + "', image3Url='" + this.image3Url + "', linkUrl='" + this.linkUrl + "', language='" + this.language + "', country='" + this.country + "', deviceSId='" + this.deviceSId + "', otaVersio='" + this.otaVersio + "', mobileType='" + this.mobileType + "', osName=" + this.osName + ", osVersion='" + this.osVersion + "', appVersion='" + this.appVersion + "', sourceMac='" + this.sourceMac + "', feedbackType='" + this.feedbackType + "', contact='" + this.contact + "'}";
    }
}