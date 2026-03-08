package com.baidu.location.a;

import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
class y extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ x f2201a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    y(x xVar, Looper looper) {
        super(looper);
        this.f2201a = xVar;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x00cf -> B:39:0x00d2). Please report as a decompilation issue!!! */
    @Override // android.os.Handler
    public void handleMessage(Message message) {
        com.baidu.location.e.a aVarC;
        com.baidu.location.e.h hVarO;
        Location locationD;
        String strA;
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        switch (message.what) {
            case 1:
                Bundle data = message.getData();
                Location location = (Location) data.getParcelable("loc");
                data.getInt("satnum");
                if (location != null) {
                    d.a().a(location);
                }
                return;
            case 2:
                aVarC = t.c();
                hVarO = com.baidu.location.e.i.a().o();
                locationD = t.d();
                strA = t.a();
                break;
            case 3:
                aVarC = t.c();
                hVarO = null;
                locationD = t.d();
                strA = a.a().d();
                break;
            case 4:
                boolean zJ = com.baidu.location.e.i.j();
                if (com.baidu.location.g.k.b()) {
                    zJ = false;
                }
                if (zJ) {
                    zJ = h.a().d() != 1;
                }
                if (zJ) {
                    if (com.baidu.location.b.d.a().e()) {
                        com.baidu.location.d.h.a().m();
                        com.baidu.location.d.h.a().i();
                    }
                    com.baidu.location.b.h.a().c();
                    if (com.baidu.location.b.d.a().e()) {
                        w.a().c();
                    }
                }
                if (this.f2201a.f2199d != null) {
                    this.f2201a.f2199d.sendEmptyMessageDelayed(4, com.baidu.location.g.k.R);
                }
                return;
            case 5:
                com.baidu.location.b.h.a().b();
                return;
            case 6:
            default:
                return;
            case 7:
                w.a().c();
                com.baidu.location.b.h.a().c();
                return;
            case 8:
                message.getData();
                return;
        }
        w.a(aVarC, hVarO, locationD, strA);
    }
}