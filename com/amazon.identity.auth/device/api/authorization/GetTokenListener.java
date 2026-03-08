package com.amazon.identity.auth.device.api.authorization;

import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.api.Listener;

/* JADX INFO: loaded from: classes.dex */
public abstract class GetTokenListener implements Listener<GetTokenResult, AuthError> {
    @Override // com.amazon.identity.auth.device.api.Listener
    public abstract void onError(AuthError authError);

    @Override // com.amazon.identity.auth.device.api.Listener
    public abstract void onSuccess(GetTokenResult getTokenResult);
}