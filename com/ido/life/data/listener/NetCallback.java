package com.ido.life.data.listener;

/* JADX INFO: loaded from: classes2.dex */
public interface NetCallback<T> {
    void onError(Throwable th, int i, String str);

    void onSuccess(T t);
}