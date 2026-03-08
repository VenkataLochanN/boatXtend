package com.ido.ble.callback;

/* JADX INFO: loaded from: classes2.dex */
public class OtherProtocolCallBack {

    public interface ICallBack {
        void onFailed(SettingType settingType);

        void onSuccess(SettingType settingType);
    }

    public enum SettingType {
        MENSTRUAL,
        MENSTRUAL_REMIND,
        CALORIE_DISTANCE_GOAL,
        SPO2,
        PRESSURE,
        SPORT_MODE_SORT
    }

    public static void onSetCallBack(final Boolean bool, final SettingType settingType) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.OtherProtocolCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.K().v()) {
                    if (bool.booleanValue()) {
                        iCallBack.onSuccess(settingType);
                    } else {
                        iCallBack.onFailed(settingType);
                    }
                }
            }
        });
    }
}