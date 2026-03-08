package com.amazon.identity.auth.device.authorization;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.identity.auth.map.device.utils.MAPLog;

/* JADX INFO: loaded from: classes.dex */
public class PackageIntentReceiver extends BroadcastReceiver {
    private static final String LOG_TAG = PackageIntentReceiver.class.getName();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        MAPLog.i(LOG_TAG, "Package Intent Received. Clearing Service Data. action=" + intent.getAction());
        ThirdPartyServiceHelper.clearCachedService(context);
    }
}