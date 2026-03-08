package com.baidu.location.e;

import android.location.Location;
import android.os.Handler;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
class g extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ e f2419a;

    g(e eVar) {
        this.f2419a = eVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        e eVar;
        Location location;
        String str;
        if (com.baidu.location.f.isServing) {
            int i = message.what;
            if (i == 1) {
                this.f2419a.e((Location) message.obj);
                return;
            }
            if (i == 3) {
                eVar = this.f2419a;
                location = (Location) message.obj;
                str = "&og=1";
            } else {
                if (i != 4) {
                    return;
                }
                eVar = this.f2419a;
                location = (Location) message.obj;
                str = "&og=2";
            }
            eVar.a(str, location);
        }
    }
}