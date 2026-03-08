package com.ido.alexa.data.capability;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes2.dex */
public class AvsFriendlyNamesBean {

    @SerializedName("@type")
    private String _$Type59;
    private ValueBean value;

    public AvsFriendlyNamesBean(String str, ValueBean valueBean) {
        this._$Type59 = str;
        this.value = valueBean;
    }

    public static class ValueBean {
        private String assetId;
        private String locale;
        private String text;

        public String getText() {
            return this.text;
        }

        public void setText(String str) {
            this.text = str;
        }

        public String getLocale() {
            return this.locale;
        }

        public void setLocale(String str) {
            this.locale = str;
        }

        public String getAssetId() {
            return this.assetId;
        }

        public void setAssetId(String str) {
            this.assetId = str;
        }
    }
}