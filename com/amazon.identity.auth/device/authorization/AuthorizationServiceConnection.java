package com.amazon.identity.auth.device.authorization;

import android.os.IBinder;
import android.os.IInterface;
import com.amazon.identity.auth.device.authorization.AmazonAuthorizationServiceInterface;
import com.amazon.identity.auth.map.device.utils.MAPLog;

/* JADX INFO: loaded from: classes.dex */
public class AuthorizationServiceConnection extends MAPServiceConnection<AmazonAuthorizationServiceInterface> {
    private static final String LOG_TAG = AuthorizationServiceConnection.class.getName();

    public AuthorizationServiceConnection() {
        MAPLog.i(LOG_TAG, "AuthorizationServiceConnection created");
    }

    @Override // com.amazon.identity.auth.device.authorization.MAPServiceConnection
    public Class<AmazonAuthorizationServiceInterface> getServiceInterfaceClass() {
        return AmazonAuthorizationServiceInterface.class;
    }

    @Override // com.amazon.identity.auth.device.authorization.MAPServiceConnection
    public IInterface getServiceInterface(IBinder iBinder) {
        return AmazonAuthorizationServiceInterface.Stub.asInterface(iBinder);
    }
}