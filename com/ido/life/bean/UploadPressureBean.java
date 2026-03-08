package com.ido.life.bean;

import com.google.gson.annotations.SerializedName;
import com.ido.life.database.model.HealthPressure;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class UploadPressureBean {

    @SerializedName("datas")
    private List<HealthPressure> mList;

    public UploadPressureBean(List<HealthPressure> list) {
        this.mList = list;
    }
}