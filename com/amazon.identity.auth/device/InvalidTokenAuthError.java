package com.amazon.identity.auth.device;

import com.amazon.identity.auth.device.AuthError;

/* JADX INFO: loaded from: classes.dex */
public class InvalidTokenAuthError extends AuthError {
    private static final long serialVersionUID = 3252084163277945567L;

    public InvalidTokenAuthError(String str) {
        super(str, AuthError.ERROR_TYPE.ERROR_INVALID_TOKEN);
    }
}