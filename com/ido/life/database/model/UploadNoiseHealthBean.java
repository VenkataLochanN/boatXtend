package com.ido.life.database.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class UploadNoiseHealthBean {

    @SerializedName("datas")
    private List<HealthVolumeData> mList;

    public UploadNoiseHealthBean(List<HealthVolumeData> list) {
        this.mList = list;
    }
}