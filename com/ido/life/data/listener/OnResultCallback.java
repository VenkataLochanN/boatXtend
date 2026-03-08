package com.ido.life.data.listener;

import com.ido.common.net.http.Result;

/* JADX INFO: loaded from: classes2.dex */
public interface OnResultCallback {
    void onFailed(String str);

    void onSuccess(Result result);
}