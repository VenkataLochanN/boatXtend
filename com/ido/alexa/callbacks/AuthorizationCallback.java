package com.ido.alexa.callbacks;

/* JADX INFO: loaded from: classes2.dex */
public interface AuthorizationCallback<T> {
    void onCancel();

    void onError(Exception exc);

    void onSuccess(T t);
}