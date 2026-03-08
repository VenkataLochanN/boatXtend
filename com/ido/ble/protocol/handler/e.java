package com.ido.ble.protocol.handler;

import android.text.TextUtils;
import com.ido.ble.callback.DeviceControlAppCallBack;
import com.ido.ble.logs.LogTool;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final class e {
    e() {
    }

    public static void a(int i, int i2, int i3) {
        DeviceControlAppCallBack.DeviceControlEventType deviceControlEventType;
        LogTool.d(com.ido.ble.logs.a.f4633a, "[DeviceControlHandler] type = " + i);
        switch (i) {
            case com.veryfit.multi.nativeprotocol.b.R1 /* 550 */:
            case 566:
            case 567:
            case 568:
            case 569:
            default:
                return;
            case 551:
                deviceControlEventType = DeviceControlAppCallBack.DeviceControlEventType.START;
                break;
            case com.veryfit.multi.nativeprotocol.b.T1 /* 552 */:
                deviceControlEventType = DeviceControlAppCallBack.DeviceControlEventType.PAUSE;
                break;
            case com.veryfit.multi.nativeprotocol.b.U1 /* 553 */:
                deviceControlEventType = DeviceControlAppCallBack.DeviceControlEventType.STOP;
                break;
            case com.veryfit.multi.nativeprotocol.b.V1 /* 554 */:
                deviceControlEventType = DeviceControlAppCallBack.DeviceControlEventType.PREVIOUS;
                break;
            case com.veryfit.multi.nativeprotocol.b.W1 /* 555 */:
                deviceControlEventType = DeviceControlAppCallBack.DeviceControlEventType.NEXT;
                break;
            case com.veryfit.multi.nativeprotocol.b.X1 /* 556 */:
                deviceControlEventType = DeviceControlAppCallBack.DeviceControlEventType.TAKE_ONE_PHOTO;
                break;
            case com.veryfit.multi.nativeprotocol.b.Y1 /* 557 */:
                deviceControlEventType = DeviceControlAppCallBack.DeviceControlEventType.TAKE_MULTI_PHOTO;
                break;
            case com.veryfit.multi.nativeprotocol.b.Z1 /* 558 */:
                deviceControlEventType = DeviceControlAppCallBack.DeviceControlEventType.VOLUME_UP;
                break;
            case com.veryfit.multi.nativeprotocol.b.a2 /* 559 */:
                deviceControlEventType = DeviceControlAppCallBack.DeviceControlEventType.VOLUME_DOWN;
                break;
            case com.veryfit.multi.nativeprotocol.b.b2 /* 560 */:
                deviceControlEventType = DeviceControlAppCallBack.DeviceControlEventType.OPEN_CAMERA;
                break;
            case com.veryfit.multi.nativeprotocol.b.c2 /* 561 */:
                deviceControlEventType = DeviceControlAppCallBack.DeviceControlEventType.CLOSE_CAMERA;
                break;
            case com.veryfit.multi.nativeprotocol.b.d2 /* 562 */:
                deviceControlEventType = DeviceControlAppCallBack.DeviceControlEventType.ANSWER_PHONE;
                break;
            case com.veryfit.multi.nativeprotocol.b.e2 /* 563 */:
                deviceControlEventType = DeviceControlAppCallBack.DeviceControlEventType.REJECT_PHONE;
                break;
            case com.veryfit.multi.nativeprotocol.b.f2 /* 564 */:
                deviceControlEventType = DeviceControlAppCallBack.DeviceControlEventType.MUTE_PHONE;
                break;
            case com.veryfit.multi.nativeprotocol.b.g2 /* 565 */:
                deviceControlEventType = DeviceControlAppCallBack.DeviceControlEventType.VOLUME_PERCENTAGE;
                break;
            case com.veryfit.multi.nativeprotocol.b.h2 /* 570 */:
                DeviceControlAppCallBack.b(true, 0L);
                return;
            case com.veryfit.multi.nativeprotocol.b.i2 /* 571 */:
                DeviceControlAppCallBack.b(false, 0L);
                return;
            case com.veryfit.multi.nativeprotocol.b.j2 /* 572 */:
                DeviceControlAppCallBack.a(true, 0L);
                return;
            case com.veryfit.multi.nativeprotocol.b.k2 /* 573 */:
                DeviceControlAppCallBack.a(false, 0L);
                return;
            case com.veryfit.multi.nativeprotocol.b.l2 /* 574 */:
                DeviceControlAppCallBack.c(i3 == 0, 0L);
                return;
        }
        DeviceControlAppCallBack.a(deviceControlEventType, i3);
    }

    public static void a(int i, byte[] bArr, int i2) {
        if (i == 581) {
            a(bArr);
        }
    }

    private static void a(byte[] bArr) {
        DeviceControlAppCallBack.DeviceControlEventType deviceControlEventType;
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[handleControlSystemCamera] json is null");
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f4633a, "[handleControlSystemCamera] json is" + strD);
        try {
            int iOptInt = new JSONObject(strD).optInt("type");
            if (iOptInt == 0) {
                deviceControlEventType = DeviceControlAppCallBack.DeviceControlEventType.OPEN_CAMERA;
            } else if (iOptInt == 1) {
                deviceControlEventType = DeviceControlAppCallBack.DeviceControlEventType.TAKE_ONE_PHOTO;
            } else if (iOptInt == 2) {
                deviceControlEventType = DeviceControlAppCallBack.DeviceControlEventType.CLOSE_CAMERA;
            } else if (iOptInt != 3) {
                return;
            } else {
                deviceControlEventType = DeviceControlAppCallBack.DeviceControlEventType.REQUEST_PAIRED;
            }
            DeviceControlAppCallBack.a(deviceControlEventType, 0);
        } catch (JSONException e2) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[handleControlSystemCamera] " + e2.getMessage());
        }
    }

    static boolean a(int i) {
        if (i == 581) {
            return true;
        }
        switch (i) {
            case com.veryfit.multi.nativeprotocol.b.R1 /* 550 */:
            case 551:
            case com.veryfit.multi.nativeprotocol.b.T1 /* 552 */:
            case com.veryfit.multi.nativeprotocol.b.U1 /* 553 */:
            case com.veryfit.multi.nativeprotocol.b.V1 /* 554 */:
            case com.veryfit.multi.nativeprotocol.b.W1 /* 555 */:
            case com.veryfit.multi.nativeprotocol.b.X1 /* 556 */:
            case com.veryfit.multi.nativeprotocol.b.Y1 /* 557 */:
            case com.veryfit.multi.nativeprotocol.b.Z1 /* 558 */:
            case com.veryfit.multi.nativeprotocol.b.a2 /* 559 */:
            case com.veryfit.multi.nativeprotocol.b.b2 /* 560 */:
            case com.veryfit.multi.nativeprotocol.b.c2 /* 561 */:
            case com.veryfit.multi.nativeprotocol.b.d2 /* 562 */:
            case com.veryfit.multi.nativeprotocol.b.e2 /* 563 */:
            case com.veryfit.multi.nativeprotocol.b.f2 /* 564 */:
            case com.veryfit.multi.nativeprotocol.b.g2 /* 565 */:
                return true;
            default:
                switch (i) {
                    case com.veryfit.multi.nativeprotocol.b.h2 /* 570 */:
                    case com.veryfit.multi.nativeprotocol.b.i2 /* 571 */:
                    case com.veryfit.multi.nativeprotocol.b.j2 /* 572 */:
                    case com.veryfit.multi.nativeprotocol.b.k2 /* 573 */:
                    case com.veryfit.multi.nativeprotocol.b.l2 /* 574 */:
                    case com.veryfit.multi.nativeprotocol.b.m2 /* 575 */:
                    case com.veryfit.multi.nativeprotocol.b.n2 /* 576 */:
                        return true;
                    default:
                        return false;
                }
        }
    }
}