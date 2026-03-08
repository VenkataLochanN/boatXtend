package com.amazon.identity.auth.device.api;

/* JADX INFO: loaded from: classes.dex */
public interface Listener<T, U> {
    void onError(U u);

    void onSuccess(T t);
}