package com.ido.ble.dfu.nodic.firmware;

/* JADX INFO: loaded from: classes2.dex */
public class CheckNewVersionResponse {
    public NewVersionInfo data;
    public String message;
    public int resultCode;

    public static class NewVersionInfo {
        String descriptionChinese;
        String descriptionEnglish;
        boolean forceUpdate;
        public String url;
        int version;

        public String toString() {
            return "NewVersionInfo{url='" + this.url + "', forceUpdate=" + this.forceUpdate + ", descriptionChinese='" + this.descriptionChinese + "', descriptionEnglish='" + this.descriptionEnglish + "', version=" + this.version + '}';
        }
    }
}