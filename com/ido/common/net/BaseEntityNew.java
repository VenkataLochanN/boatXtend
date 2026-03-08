package com.ido.common.net;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes2.dex */
public class BaseEntityNew<T> extends BaseEntity {

    @SerializedName("result")
    private T mResult;

    public BaseEntityNew() {
    }

    public BaseEntityNew(T t) {
        this.mResult = t;
    }

    public T getResult() {
        return this.mResult;
    }

    public void setResult(T t) {
        this.mResult = t;
    }
}