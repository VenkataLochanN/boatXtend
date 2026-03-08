package com.ido.ble.callback;

/* JADX INFO: loaded from: classes2.dex */
public class AppSendDataCallBack {

    public enum DataType {
        WEATHER,
        WEATHER_V3,
        WEATHER_SUN_TIME
    }

    public interface ICallBack {
        void onFailed(DataType dataType);

        void onSuccess(DataType dataType);
    }

    public static void a(final boolean z, final DataType dataType) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.AppSendDataCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.K().d()) {
                    if (z) {
                        iCallBack.onSuccess(dataType);
                    } else {
                        iCallBack.onFailed(dataType);
                    }
                }
            }
        });
    }
}