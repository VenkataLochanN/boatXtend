package com.ido.life.bean;

import com.ido.life.database.model.ServerHeartRateDayData;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ServerHeartRateDayUploadBean {
    private List<ServerHeartRateDayData> datas;

    public ServerHeartRateDayUploadBean(List<ServerHeartRateDayData> list) {
        this.datas = list;
    }

    public List<ServerHeartRateDayData> getDatas() {
        return this.datas;
    }

    public void setDatas(List<ServerHeartRateDayData> list) {
        this.datas = list;
    }
}