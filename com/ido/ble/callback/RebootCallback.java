package com.ido.ble.callback;

/* JADX INFO: loaded from: classes2.dex */
public class RebootCallback {

    public interface ICallBack {
        void onFailed();

        void onSuccess();
    }

    public static final void a(final Boolean bool) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.RebootCallback.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.K().y()) {
                    if (bool.booleanValue()) {
                        iCallBack.onSuccess();
                    } else {
                        iCallBack.onFailed();
                    }
                }
            }
        });
    }
}