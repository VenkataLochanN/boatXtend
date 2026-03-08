package com.ido.life.database.model;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ServerMultiDaysSleep extends ServerDaysSleepData {
    private List<ServerSleepDayData> datas;

    public List<ServerSleepDayData> getDatas() {
        return this.datas;
    }

    public void setDatas(List<ServerSleepDayData> list) {
        this.datas = list;
    }
}