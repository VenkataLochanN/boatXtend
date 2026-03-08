package com.ido.ble.callback;

import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class EnterDfuModeCallback {

    public enum DfuError {
        LOW_BATTERY,
        DEVICE_NOT_SUPPORT,
        PARA_ERROR,
        PARA_OTHER
    }

    public interface ICallBack {
        void onError(DfuError dfuError);

        void onSuccess();
    }

    public static void a() {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.EnterDfuModeCallback.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().q().iterator();
                while (it.hasNext()) {
                    it.next().onSuccess();
                }
            }
        });
    }

    public static void a(final DfuError dfuError) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.EnterDfuModeCallback.2
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().q().iterator();
                while (it.hasNext()) {
                    it.next().onError(dfuError);
                }
            }
        });
    }
}