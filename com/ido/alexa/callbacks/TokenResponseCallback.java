package com.ido.alexa.callbacks;

import com.ido.alexa.data.TokenResponse;

/* JADX INFO: loaded from: classes2.dex */
public interface TokenResponseCallback {
    void onFailure(Exception exc);

    void onSuccess(TokenResponse tokenResponse);
}