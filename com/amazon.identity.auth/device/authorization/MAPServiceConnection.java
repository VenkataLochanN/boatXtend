package com.amazon.identity.auth.device.authorization;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.map.device.utils.MAPLog;

/* JADX INFO: loaded from: classes.dex */
public abstract class MAPServiceConnection<T> implements ServiceConnection {
    private static final String LOG_TAG = MAPServiceConnection.class.getName();
    protected AmazonServiceListener mListener;
    protected IInterface mService = null;

    public abstract IInterface getServiceInterface(IBinder iBinder);

    public abstract Class<T> getServiceInterfaceClass();

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        MAPLog.i(LOG_TAG, "onServiceConnected called");
        if (isValidService(iBinder)) {
            this.mService = getServiceInterface(iBinder);
            this.mListener.onBindSuccess(this.mService);
        } else {
            this.mListener.onBindError(new AuthError("Returned service's interface doesn't match authorization service", AuthError.ERROR_TYPE.ERROR_UNKNOWN));
        }
    }

    public void setServiceListener(AmazonServiceListener amazonServiceListener) {
        if (amazonServiceListener == null) {
            throw new IllegalArgumentException("listener cannot be null!");
        }
        this.mListener = amazonServiceListener;
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        MAPLog.i(LOG_TAG, "onServiceDisconnected called");
        this.mService = null;
    }

    protected boolean isValidService(IBinder iBinder) {
        try {
            return iBinder.getInterfaceDescriptor().equals(getServiceInterfaceClass().getName());
        } catch (Exception e2) {
            MAPLog.e(LOG_TAG, "" + e2.getMessage(), e2);
            return false;
        }
    }
}