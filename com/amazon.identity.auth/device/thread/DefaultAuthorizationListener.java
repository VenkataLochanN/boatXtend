package com.amazon.identity.auth.device.thread;

import android.os.Bundle;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.authorization.api.AuthorizationListener;
import com.amazon.identity.auth.map.device.utils.MAPLog;

/* JADX INFO: loaded from: classes.dex */
public class DefaultAuthorizationListener implements AuthorizationListener {
    private static final String LOG_TAG = DefaultAuthorizationListener.class.getName();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.identity.auth.device.api.Listener
    public void onSuccess(Bundle bundle) {
        MAPLog.i(LOG_TAG, "onSuccess");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.identity.auth.device.api.Listener
    public void onError(AuthError authError) {
        MAPLog.i(LOG_TAG, "onError");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.identity.auth.device.api.CancellableListener
    public void onCancel(Bundle bundle) {
        MAPLog.i(LOG_TAG, "onCancel");
    }
}