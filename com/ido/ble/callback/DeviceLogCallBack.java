package com.ido.ble.callback;

import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceLogCallBack {

    public interface ICallBack {
        void onGetHeatLog(String str);
    }

    public static void a(final String str) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.DeviceLogCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().l().iterator();
                while (it.hasNext()) {
                    it.next().onGetHeatLog(str);
                }
            }
        });
    }
}