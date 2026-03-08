package com.ido.ble.callback;

import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class AppSendAllPhoneContactsCallBack {

    public interface ICallBack {
        void onCallBack(String str);
    }

    public static void onCallBack(final String str) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.AppSendAllPhoneContactsCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().c().iterator();
                while (it.hasNext()) {
                    it.next().onCallBack(str);
                }
            }
        });
    }
}