package com.baidu.location;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
class b implements ServiceConnection {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ LocationClient f2202a;

    b(LocationClient locationClient) {
        this.f2202a = locationClient;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f2202a.f2040g = new Messenger(iBinder);
        if (this.f2202a.f2040g == null) {
            return;
        }
        this.f2202a.f2038e = true;
        Log.d("baidu_location_client", "baidu location connected ...");
        if (this.f2202a.z) {
            this.f2202a.f2041h.obtainMessage(2).sendToTarget();
            return;
        }
        try {
            Message messageObtain = Message.obtain((Handler) null, 11);
            messageObtain.replyTo = this.f2202a.i;
            messageObtain.setData(this.f2202a.d());
            this.f2202a.f2040g.send(messageObtain);
            this.f2202a.f2038e = true;
            if (this.f2202a.f2036c != null) {
                this.f2202a.C.booleanValue();
                this.f2202a.f2041h.obtainMessage(4).sendToTarget();
            }
        } catch (Exception unused) {
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        this.f2202a.f2040g = null;
        this.f2202a.f2038e = false;
    }
}