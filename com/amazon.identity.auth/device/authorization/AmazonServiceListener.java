package com.amazon.identity.auth.device.authorization;

import android.os.IInterface;
import com.amazon.identity.auth.device.AuthError;

/* JADX INFO: loaded from: classes.dex */
public interface AmazonServiceListener {
    void onBindError(AuthError authError);

    void onBindSuccess(IInterface iInterface);
}