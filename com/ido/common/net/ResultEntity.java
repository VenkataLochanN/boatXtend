package com.ido.common.net;

import com.ido.common.net.http.Result;

/* JADX INFO: loaded from: classes2.dex */
public class ResultEntity extends BaseEntity {
    private Result result;

    public Result getResult() {
        return this.result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String toString() {
        return "ResultEntity{result='" + this.result + "'}";
    }
}