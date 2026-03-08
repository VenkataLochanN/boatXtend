package com.ido.ble.callback;

import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class UnbindCallBack {

    public interface ICallBack {
        void onFailed();

        void onSuccess();
    }

    public static void a() {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.UnbindCallBack.2
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().G().iterator();
                while (it.hasNext()) {
                    it.next().onFailed();
                }
            }
        });
    }

    public static void b() {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.UnbindCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().G().iterator();
                while (it.hasNext()) {
                    it.next().onSuccess();
                }
            }
        });
    }
}