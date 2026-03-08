package com.amap.api.mapcore.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.autonavi.amap.mapcore.Inner_3dMap_location;

/* JADX INFO: compiled from: MapLocationManagerResultHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class kq extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    ko f1583a;

    public kq(Looper looper, ko koVar) {
        super(looper);
        this.f1583a = null;
        this.f1583a = koVar;
    }

    public kq(ko koVar) {
        this.f1583a = null;
        this.f1583a = koVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        if (message.what != 1) {
            return;
        }
        try {
            if (this.f1583a != null) {
                this.f1583a.a((Inner_3dMap_location) message.obj);
            }
        } catch (Throwable th) {
            kg.a(th, "ClientResultHandler", "RESULT_LOCATION_FINISH");
        }
    }
}