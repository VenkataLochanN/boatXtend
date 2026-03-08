package com.baidu.mapapi.utils;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.baidu.mapframework.open.aidl.IComOpenClient;
import com.baidu.mapframework.open.aidl.b;

/* JADX INFO: loaded from: classes.dex */
final class c extends b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ int f3368a;

    c(int i) {
        this.f3368a = i;
    }

    @Override // com.baidu.mapframework.open.aidl.b
    public void a(IBinder iBinder) throws RemoteException {
        Log.d(b.f3362c, "onClientReady");
        if (b.f3364e != null) {
            IComOpenClient unused = b.f3364e = null;
        }
        IComOpenClient unused2 = b.f3364e = IComOpenClient.a.a(iBinder);
        b.a(this.f3368a);
        boolean unused3 = b.t = true;
    }
}