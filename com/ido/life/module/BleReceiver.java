package com.ido.life.module;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.util.ConnectLogHelper;
import com.ido.life.util.eventbus.EventBusHelper;

/* JADX INFO: loaded from: classes2.dex */
public class BleReceiver extends BroadcastReceiver {
    private static final String TAG = BleReceiver.class.getSimpleName();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (((action.hashCode() == -1530327060 && action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) ? (byte) 0 : (byte) -1) != 0) {
            return;
        }
        int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0);
        CommonLogUtil.d("blueState:" + intExtra);
        switch (intExtra) {
            case 10:
                printLog("蓝牙关闭了。。。");
                EventBusHelper.post(-100);
                break;
            case 12:
                printLog("蓝牙打开了。。。");
                EventBusHelper.post(100);
                break;
        }
    }

    private void printLog(String str) {
        ConnectLogHelper.saveLog(TAG, str);
    }
}