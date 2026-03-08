package com.ido.life.database.model;

import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ServerMultiDaysBloodOxy extends ServerDaysBloodOxyData implements Serializable {
    private List<ServerBloodOxyDayData> datas;

    public List<ServerBloodOxyDayData> getDatas() {
        return this.datas;
    }

    public void setDatas(List<ServerBloodOxyDayData> list) {
        this.datas = list;
    }
}