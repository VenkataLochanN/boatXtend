package com.ido.life.data.api.entity;

import com.google.gson.annotations.SerializedName;
import com.ido.life.database.model.WeightItemBean;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class UploadWeightBean {

    @SerializedName("datas")
    private List<WeightItemBean> mList;

    public UploadWeightBean(List<WeightItemBean> list) {
        this.mList = list;
    }
}