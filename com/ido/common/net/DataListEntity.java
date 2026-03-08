package com.ido.common.net;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class DataListEntity<T> extends BaseEntity {
    private ArrayList<T> result;

    public ArrayList<T> getResult() {
        return this.result;
    }

    public void setResult(ArrayList<T> arrayList) {
        this.result = arrayList;
    }
}