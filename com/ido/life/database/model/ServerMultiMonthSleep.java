package com.ido.life.database.model;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ServerMultiMonthSleep extends ServerMultiMonthSleepTotalData {
    private List<ServerSleepMonthData> datas;

    public List<ServerSleepMonthData> getDatas() {
        return this.datas;
    }

    public void setDatas(List<ServerSleepMonthData> list) {
        this.datas = list;
    }
}