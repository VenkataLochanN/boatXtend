package com.ido.life.bean;

import com.google.gson.annotations.SerializedName;
import com.ido.life.database.model.SportDistanceBean;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DistenceDetailUploadBean {

    @SerializedName("datas")
    private List<SportDistanceBean> mList;

    public DistenceDetailUploadBean(List<SportDistanceBean> list) {
        this.mList = list;
    }
}