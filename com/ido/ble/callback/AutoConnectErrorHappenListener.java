package com.ido.ble.callback;

import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class AutoConnectErrorHappenListener {

    public enum ErrorHappenType {
        NOT_FIND_DEVICE,
        GATT_ERROR_FIND_DEVICE,
        GATT_ERROR_OTHER,
        DISCOVER_SERVICE_FAILED,
        ENABLE_NOTIFY_FAILED,
        ENCRYPTED_FAILED
    }

    public interface IListener {
        void onErrorHappen(ErrorHappenType errorHappenType, String str);
    }

    public static void onErrorHappen(final ErrorHappenType errorHappenType, final String str) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.AutoConnectErrorHappenListener.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IListener> it = b.K().e().iterator();
                while (it.hasNext()) {
                    it.next().onErrorHappen(errorHappenType, str);
                }
            }
        });
    }
}