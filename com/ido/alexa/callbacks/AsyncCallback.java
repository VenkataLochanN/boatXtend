package com.ido.alexa.callbacks;

/* JADX INFO: loaded from: classes2.dex */
public interface AsyncCallback<D, E> {
    void authorize();

    void complete();

    void failure(E e2);

    void start(String str);

    void startParse();

    void success(D d2);
}