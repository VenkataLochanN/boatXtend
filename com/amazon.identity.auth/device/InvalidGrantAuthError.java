package com.amazon.identity.auth.device;

import com.amazon.identity.auth.device.AuthError;

/* JADX INFO: loaded from: classes.dex */
public class InvalidGrantAuthError extends AuthError {
    private static final long serialVersionUID = 1;

    public InvalidGrantAuthError(String str) {
        super(str, AuthError.ERROR_TYPE.ERROR_INVALID_GRANT);
    }
}