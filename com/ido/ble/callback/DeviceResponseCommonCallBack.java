package com.ido.ble.callback;

import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceResponseCommonCallBack {

    public interface ICallBack {
        void onResponse(int i, String str);
    }

    @Deprecated
    public static void onResponse(final int i, final String str) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.DeviceResponseCommonCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().n().iterator();
                while (it.hasNext()) {
                    it.next().onResponse(i, str);
                }
            }
        });
    }
}