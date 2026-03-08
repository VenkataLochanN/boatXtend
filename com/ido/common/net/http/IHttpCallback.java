package com.ido.common.net.http;

/* JADX INFO: loaded from: classes2.dex */
public interface IHttpCallback<T> {
    void onFaild(String str);

    void onSuccess(T t);
}