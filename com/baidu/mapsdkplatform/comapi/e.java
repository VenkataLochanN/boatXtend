package com.baidu.mapsdkplatform.comapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.baidu.mapapi.NetworkUtil;
import com.baidu.mapsdkplatform.comapi.util.i;

/* JADX INFO: loaded from: classes.dex */
public class e extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3482a = e.class.getSimpleName();

    public void a(Context context) {
        String currentNetMode = NetworkUtil.getCurrentNetMode(context);
        String strE = i.e();
        if (strE == null || strE.equals(currentNetMode)) {
            return;
        }
        i.a(currentNetMode);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        a(context);
        NetworkUtil.updateNetworkProxy(context);
    }
}