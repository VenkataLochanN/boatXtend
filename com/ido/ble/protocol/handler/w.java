package com.ido.ble.protocol.handler;

import com.google.gson.Gson;
import com.ido.ble.callback.V3AppExchangeDataCallBack;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.V3AppExchangeDataDeviceReplayEndData;
import com.ido.ble.protocol.model.V3AppExchangeDataHeartRate;
import com.ido.ble.protocol.model.V3AppExchangeDataIngDeviceReplyData;

/* JADX INFO: loaded from: classes2.dex */
public class w {
    public static void a(int i, int i2, int i3) {
    }

    public static void a(int i, byte[] bArr, int i2) {
        switch (i) {
            case 5021:
                b(bArr);
                break;
            case 5022:
                a(bArr);
                break;
            case 5023:
                c(bArr);
                break;
        }
    }

    private static void a(byte[] bArr) {
        String strD = com.ido.ble.common.c.d(bArr);
        LogTool.d(com.ido.ble.logs.a.o, "[V3DataExchangeHandler] handleEndActivityData" + strD);
        V3AppExchangeDataCallBack.a((V3AppExchangeDataDeviceReplayEndData) new Gson().fromJson(strD, V3AppExchangeDataDeviceReplayEndData.class));
    }

    static boolean a(int i) {
        switch (i) {
            case 5021:
            case 5022:
            case 5023:
                return true;
            default:
                return false;
        }
    }

    private static void b(byte[] bArr) {
        V3AppExchangeDataCallBack.a((V3AppExchangeDataIngDeviceReplyData) new Gson().fromJson(com.ido.ble.common.c.d(bArr), V3AppExchangeDataIngDeviceReplyData.class));
    }

    private static void c(byte[] bArr) {
        V3AppExchangeDataCallBack.a((V3AppExchangeDataHeartRate) new Gson().fromJson(com.ido.ble.common.c.d(bArr), V3AppExchangeDataHeartRate.class));
    }
}