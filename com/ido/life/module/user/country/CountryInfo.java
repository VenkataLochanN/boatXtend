package com.ido.life.module.user.country;

import java.io.Serializable;

/* JADX INFO: loaded from: classes3.dex */
public class CountryInfo implements Cloneable, Serializable {
    public static final int TYPE_DEFAULT = 0;
    public static final int TYPE_INVALID = 48;
    public static final int TYPE_ITEM = 32;
    public String session;
    public int type;
    public String countryCode = "";
    public String countryName = "";
    public String countryFlag = "";
    public boolean isChoose = false;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public CountryInfo m30clone() {
        CountryInfo countryInfo = new CountryInfo();
        countryInfo.type = this.type;
        countryInfo.session = this.session;
        countryInfo.countryCode = this.countryCode;
        countryInfo.countryName = this.countryName;
        countryInfo.countryFlag = this.countryFlag;
        countryInfo.isChoose = false;
        return countryInfo;
    }

    public String toString() {
        return "CountryInfo{type=" + this.type + ", session='" + this.session + "', countryCode='" + this.countryCode + "', countryName='" + this.countryName + "', countryFlag='" + this.countryFlag + "', isChoose=" + this.isChoose + '}';
    }
}