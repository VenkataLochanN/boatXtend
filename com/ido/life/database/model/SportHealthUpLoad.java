package com.ido.life.database.model;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SportHealthUpLoad {
    private List<SportHealth> datas;

    public SportHealthUpLoad(List<SportHealth> list) {
        this.datas = list;
    }

    public List<SportHealth> getDatas() {
        return this.datas;
    }

    public void setDatas(List<SportHealth> list) {
        this.datas = list;
    }
}