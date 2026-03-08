package com.amazon.identity.auth.device.api;

/* JADX INFO: loaded from: classes.dex */
public class InvalidIntegrationException extends RuntimeException {
    private static final long serialVersionUID = -1241308386328434475L;

    public InvalidIntegrationException() {
    }

    public InvalidIntegrationException(String str) {
        super(str);
    }

    public InvalidIntegrationException(String str, Throwable th) {
        super(str, th);
    }
}