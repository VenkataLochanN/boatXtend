package com.ido.ble.callback;

/* JADX INFO: loaded from: classes2.dex */
public class AppControlDeviceCallBack {

    public enum AppControlType {
        MUSIC_PLAY_ENTER,
        MUSIC_PLAY_EXIT,
        CAMERA_ENTER,
        CAMERA_EXIT,
        ONCE_SPORT_ENTER,
        ONCE_SPORT_EXIT,
        FIND_DEVICE_ENTER,
        FIND_DEVICE_EXIT,
        CONTROL_HARDWARE,
        OPEN_ANCS,
        CLOSE_ANCS
    }

    public interface ICallBack {
        void onFailed(AppControlType appControlType);

        void onSuccess(AppControlType appControlType);
    }

    public static void a(final Boolean bool, final AppControlType appControlType) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.AppControlDeviceCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.K().a()) {
                    if (bool.booleanValue()) {
                        iCallBack.onSuccess(appControlType);
                    } else {
                        iCallBack.onFailed(appControlType);
                    }
                }
            }
        });
    }
}