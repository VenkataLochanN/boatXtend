package com.ido.ble.protocol.handler;

import com.ido.ble.callback.PhoneMsgNoticeCallBack;
import com.ido.ble.logs.LogTool;

/* JADX INFO: loaded from: classes2.dex */
final class p {
    p() {
    }

    public static void a(int i, int i2, int i3) {
        LogTool.d(com.ido.ble.logs.a.l, "[PhoneMsgNoticeHandler] handleIntResult, evt_type=" + i + ",error=" + i2 + ",value=" + i3);
        switch (i) {
            case 410:
                PhoneMsgNoticeCallBack.a();
                break;
            case 411:
                PhoneMsgNoticeCallBack.b();
                break;
            case 412:
                PhoneMsgNoticeCallBack.c();
                break;
        }
    }

    public static void a(int i, byte[] bArr, int i2) {
        LogTool.d(com.ido.ble.logs.a.l, "[PhoneMsgNoticeHandler] handleJsonResult, type=" + i + ",errorCode=" + i2);
        if (i != 5012) {
            return;
        }
        PhoneMsgNoticeCallBack.a(i2);
    }

    static boolean a(int i) {
        if (i == 5012) {
            return true;
        }
        switch (i) {
            case 410:
            case 411:
            case 412:
            case 413:
            case 414:
                return true;
            default:
                return false;
        }
    }
}