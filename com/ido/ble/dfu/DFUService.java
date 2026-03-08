package com.ido.ble.dfu;

import android.app.Activity;
import no.nordicsemi.android.dfu.DfuBaseService;

/* JADX INFO: loaded from: classes2.dex */
public class DFUService extends DfuBaseService {
    @Override // no.nordicsemi.android.dfu.DfuBaseService
    protected Class<? extends Activity> getNotificationTarget() {
        return null;
    }

    @Override // no.nordicsemi.android.dfu.DfuBaseService
    protected boolean isDebug() {
        return true;
    }
}