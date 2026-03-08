package com.ido.ble.callback;

import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceUpgradeEventListener {

    public interface IListener {
        void APOLLO_onSOLibError(int i);

        void NODIC_onProgress(int i);
    }

    public static void APOLLO_onSOLibError(final int i) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.DeviceUpgradeEventListener.2
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IListener> it = b.K().o().iterator();
                while (it.hasNext()) {
                    it.next().APOLLO_onSOLibError(i);
                }
            }
        });
    }

    public static void NODIC_onProgress(final int i) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.DeviceUpgradeEventListener.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IListener> it = b.K().o().iterator();
                while (it.hasNext()) {
                    it.next().NODIC_onProgress(i);
                }
            }
        });
    }
}