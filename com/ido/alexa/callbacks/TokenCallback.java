package com.ido.alexa.callbacks;

/* JADX INFO: loaded from: classes2.dex */
public interface TokenCallback {
    void onFailure(Throwable th);

    void onSuccess(String str);
}