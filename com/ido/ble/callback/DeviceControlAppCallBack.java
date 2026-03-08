package com.ido.ble.callback;

import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceControlAppCallBack {

    public enum DeviceControlEventType {
        START,
        PAUSE,
        STOP,
        PREVIOUS,
        NEXT,
        TAKE_ONE_PHOTO,
        TAKE_MULTI_PHOTO,
        VOLUME_UP,
        VOLUME_DOWN,
        OPEN_CAMERA,
        CLOSE_CAMERA,
        ANSWER_PHONE,
        REJECT_PHONE,
        MUTE_PHONE,
        REQUEST_PAIRED,
        VOLUME_PERCENTAGE
    }

    public interface ICallBack {
        void onAntiLostNotice(boolean z, long j);

        void onControlEvent(DeviceControlEventType deviceControlEventType, int i);

        void onFindPhone(boolean z, long j);

        void onOneKeySOS(boolean z, long j);
    }

    public enum ReplyDeviceControlState {
        SUCCESS,
        FAILED
    }

    public static final void a(final DeviceControlEventType deviceControlEventType, final int i) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.DeviceControlAppCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().i().iterator();
                while (it.hasNext()) {
                    it.next().onControlEvent(deviceControlEventType, i);
                }
            }
        });
    }

    public static final void a(final boolean z, final long j) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.DeviceControlAppCallBack.4
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().i().iterator();
                while (it.hasNext()) {
                    it.next().onAntiLostNotice(z, j);
                }
            }
        });
    }

    public static final void b(final boolean z, final long j) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.DeviceControlAppCallBack.2
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().i().iterator();
                while (it.hasNext()) {
                    it.next().onFindPhone(z, j);
                }
            }
        });
    }

    public static final void c(final boolean z, final long j) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.DeviceControlAppCallBack.3
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().i().iterator();
                while (it.hasNext()) {
                    it.next().onOneKeySOS(z, j);
                }
            }
        });
    }
}