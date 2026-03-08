package com.ido.ble.protocol.handler;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.ido.ble.callback.DeviceParaChangedCallBack;
import com.ido.ble.callback.DeviceVoiceChangedCallBack;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.DeviceChangedPara;
import com.ido.ble.protocol.model.HornVoice;

/* JADX INFO: loaded from: classes2.dex */
final class g {
    g() {
    }

    public static void a(int i, int i2, int i3) {
    }

    public static void a(int i, byte[] bArr, int i2) {
        if (i == 577 || i == 580) {
            b(bArr);
        } else {
            if (i != 591) {
                return;
            }
            a(bArr);
        }
    }

    private static void a(DeviceChangedPara deviceChangedPara) {
        if (deviceChangedPara != null && deviceChangedPara.dataType == 1) {
            v.a();
        }
    }

    private static void a(byte[] bArr) {
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            LogTool.b("DeviceParaChangedHandler", "handleHornJsonData jsonString is null");
            return;
        }
        LogTool.d("DeviceParaChangedHandler", "handleHornJsonData jsonString is " + strD);
        DeviceVoiceChangedCallBack.onHornVoiceChanged((HornVoice) new Gson().fromJson(strD, HornVoice.class));
    }

    static boolean a(int i) {
        return i == 577 || i == 580 || i == 591;
    }

    private static void b(byte[] bArr) {
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            LogTool.b("DeviceParaChangedHandler", "handleJsonData jsonString is null");
            return;
        }
        LogTool.d("DeviceParaChangedHandler", "handleJsonData jsonString is " + strD);
        DeviceChangedPara deviceChangedPara = (DeviceChangedPara) new Gson().fromJson(strD, DeviceChangedPara.class);
        DeviceParaChangedCallBack.onChanged(deviceChangedPara);
        a(deviceChangedPara);
    }
}