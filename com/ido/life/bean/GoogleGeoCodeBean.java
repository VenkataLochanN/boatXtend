package com.ido.life.bean;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class GoogleGeoCodeBean {

    @SerializedName("formatted_address")
    private String mAddressDetail;

    @SerializedName("address_components")
    private List<GoogleGeoCodeItemBean> mList;

    public List<GoogleGeoCodeItemBean> getList() {
        return this.mList;
    }

    public void setList(List<GoogleGeoCodeItemBean> list) {
        this.mList = list;
    }

    public String getAddressDetail() {
        return this.mAddressDetail;
    }

    public void setAddressDetail(String str) {
        this.mAddressDetail = str;
    }
}