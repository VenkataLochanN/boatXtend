package com.ido.life.data.api.entity;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class WeeklyReportStatusUploadEntity {

    @SerializedName("datas")
    private List<WeeklyReportStatusEntity> mList;

    public WeeklyReportStatusUploadEntity(List<WeeklyReportStatusEntity> list) {
        this.mList = list;
    }

    public List<WeeklyReportStatusEntity> getList() {
        return this.mList;
    }

    public void setList(List<WeeklyReportStatusEntity> list) {
        this.mList = list;
    }
}