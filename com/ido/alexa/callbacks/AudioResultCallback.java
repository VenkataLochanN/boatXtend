package com.ido.alexa.callbacks;

/* JADX INFO: loaded from: classes2.dex */
public interface AudioResultCallback {
    void onAlexaEnd();

    void onAlexaLogout();

    void onAlexaStart();

    void onMP3FileName(String str);

    void onPcmFileName(String str);

    void onResponse(String str);

    void onStartPrase();
}