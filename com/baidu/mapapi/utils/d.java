package com.baidu.mapapi.utils;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.baidu.mapframework.open.aidl.a;

/* JADX INFO: loaded from: classes.dex */
final class d implements ServiceConnection {
    d() {
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (b.v != null) {
            b.v.interrupt();
        }
        Log.d(b.f3362c, "onServiceConnected " + componentName);
        try {
            if (b.f3363d != null) {
                com.baidu.mapframework.open.aidl.a unused = b.f3363d = null;
            }
            com.baidu.mapframework.open.aidl.a unused2 = b.f3363d = a.AbstractBinderC0028a.a(iBinder);
            b.f3363d.a(new e(this));
        } catch (RemoteException e2) {
            Log.d(b.f3362c, "getComOpenClient ", e2);
            if (b.f3363d != null) {
                com.baidu.mapframework.open.aidl.a unused3 = b.f3363d = null;
            }
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        Log.d(b.f3362c, "onServiceDisconnected " + componentName);
        if (b.f3363d != null) {
            com.baidu.mapframework.open.aidl.a unused = b.f3363d = null;
            boolean unused2 = b.u = false;
        }
    }
}