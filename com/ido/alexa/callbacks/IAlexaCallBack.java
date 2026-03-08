package com.ido.alexa.callbacks;

import com.ido.alexa.bean.AvsException;

/* JADX INFO: loaded from: classes2.dex */
public interface IAlexaCallBack {
    void failure(AvsException avsException);

    void success(String str);
}