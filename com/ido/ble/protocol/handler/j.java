package com.ido.ble.protocol.handler;

import android.text.TextUtils;
import com.ido.ble.callback.EnterDfuModeCallback;
import com.ido.ble.logs.LogTool;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final class j {
    j() {
    }

    public static void a(int i, int i2, int i3) {
    }

    public static void a(int i, byte[] bArr, int i2) {
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[SET_PARA] [EnterDfuModeHandler] json is null");
            EnterDfuModeCallback.a(EnterDfuModeCallback.DfuError.PARA_OTHER);
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f4633a, "[SET_PARA] [EnterDfuModeHandler] json is" + strD);
        int iOptInt = -1;
        try {
            iOptInt = new JSONObject(strD).optInt("err_flag");
        } catch (JSONException e2) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[SET_PARA] [EnterDfuModeHandler]" + e2.getMessage());
        }
        if (iOptInt == 0) {
            EnterDfuModeCallback.a();
        } else {
            EnterDfuModeCallback.a(iOptInt == 1 ? EnterDfuModeCallback.DfuError.LOW_BATTERY : iOptInt == 2 ? EnterDfuModeCallback.DfuError.DEVICE_NOT_SUPPORT : iOptInt == 3 ? EnterDfuModeCallback.DfuError.PARA_ERROR : EnterDfuModeCallback.DfuError.PARA_OTHER);
        }
    }

    public static boolean a(int i) {
        return i == 400 || i == 401;
    }
}