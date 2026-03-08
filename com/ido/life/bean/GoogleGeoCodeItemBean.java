package com.ido.life.bean;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class GoogleGeoCodeItemBean {

    @SerializedName("long_name")
    private String mLongName;

    @SerializedName("short_name")
    private String mShortName;

    @SerializedName("types")
    private List<String> mTypes;

    public String getLongName() {
        return this.mLongName;
    }

    public void setLongName(String str) {
        this.mLongName = str;
    }

    public String getShortName() {
        return this.mShortName;
    }

    public void setShortName(String str) {
        this.mShortName = str;
    }

    public List<String> getTypes() {
        return this.mTypes;
    }

    public void setTypes(List<String> list) {
        this.mTypes = list;
    }
}