package com.ido.life.bean;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class GoogleGeoCodeResult {

    @SerializedName("results")
    private List<GoogleGeoCodeBean> mList;

    public List<GoogleGeoCodeBean> getList() {
        return this.mList;
    }

    public void setList(List<GoogleGeoCodeBean> list) {
        this.mList = list;
    }
}