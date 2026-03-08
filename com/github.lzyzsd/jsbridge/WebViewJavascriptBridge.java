package com.github.lzyzsd.jsbridge;

/* JADX INFO: loaded from: classes.dex */
public interface WebViewJavascriptBridge {
    void send(String str);

    void send(String str, CallBackFunction callBackFunction);
}