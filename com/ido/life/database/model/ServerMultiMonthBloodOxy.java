package com.ido.life.database.model;

import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ServerMultiMonthBloodOxy extends ServerMultiMonthBloodOxyTotalData implements Serializable {
    private List<ServerBloodOxyMonthData> datas;

    public List<ServerBloodOxyMonthData> getDatas() {
        return this.datas;
    }

    public void setDatas(List<ServerBloodOxyMonthData> list) {
        this.datas = list;
    }

    @Override // com.ido.life.database.model.ServerMultiMonthBloodOxyTotalData
    public String toString() {
        return "ServerMultiMonthBloodOxy{datas=" + this.datas + '}';
    }
}