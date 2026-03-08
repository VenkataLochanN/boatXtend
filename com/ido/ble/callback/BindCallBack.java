package com.ido.ble.callback;

import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class BindCallBack {

    public enum BindFailedError {
        ERROR_OTHER,
        ERROR_DEVICE_ALREADY_IN_BIND_STATE,
        ERROR_ENCRYPTED
    }

    public interface ICallBack {
        void onCancel();

        void onFailed(BindFailedError bindFailedError);

        void onNeedAuth(int i);

        void onReject();

        void onSuccess();
    }

    public static final void a() {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.BindCallBack.4
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().f().iterator();
                while (it.hasNext()) {
                    it.next().onCancel();
                }
            }
        });
    }

    public static final void a(final int i) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.BindCallBack.6
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().f().iterator();
                while (it.hasNext()) {
                    it.next().onNeedAuth(i);
                }
            }
        });
    }

    public static final void a(final BindFailedError bindFailedError) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.BindCallBack.2
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().f().iterator();
                while (it.hasNext()) {
                    it.next().onFailed(bindFailedError);
                }
            }
        });
    }

    public static final void b() {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.BindCallBack.3
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().f().iterator();
                while (it.hasNext()) {
                    it.next().onFailed(BindFailedError.ERROR_OTHER);
                }
            }
        });
    }

    public static final void c() {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.BindCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().f().iterator();
                while (it.hasNext()) {
                    it.next().onSuccess();
                }
            }
        });
    }

    public static final void d() {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.BindCallBack.5
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().f().iterator();
                while (it.hasNext()) {
                    it.next().onReject();
                }
            }
        });
    }
}