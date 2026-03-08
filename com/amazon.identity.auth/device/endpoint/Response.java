package com.amazon.identity.auth.device.endpoint;

import com.amazon.identity.auth.device.AuthError;

/* JADX INFO: loaded from: classes.dex */
public interface Response {
    int getStatusCode();

    void parse() throws AuthError;
}