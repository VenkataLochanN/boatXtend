package com.ido.life.boatreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.ido.life.module.splash.SplashActivity;

/* JADX INFO: loaded from: classes2.dex */
public class SelfStartingReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            Intent intent2 = new Intent(context, (Class<?>) SplashActivity.class);
            intent2.addFlags(268435456);
            context.startActivity(intent2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}