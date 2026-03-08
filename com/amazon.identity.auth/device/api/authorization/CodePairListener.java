package com.amazon.identity.auth.device.api.authorization;

import com.amazon.identity.auth.device.CodePairError;
import com.amazon.identity.auth.device.api.Listener;

/* JADX INFO: loaded from: classes.dex */
public abstract class CodePairListener implements Listener<CodePairResult, CodePairError> {
    @Override // com.amazon.identity.auth.device.api.Listener
    public abstract void onError(CodePairError codePairError);

    @Override // com.amazon.identity.auth.device.api.Listener
    public abstract void onSuccess(CodePairResult codePairResult);
}