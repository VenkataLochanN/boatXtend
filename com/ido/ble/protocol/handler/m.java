package com.ido.ble.protocol.handler;

import com.google.gson.Gson;
import com.ido.ble.callback.GetDeviceParaCallBack;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.ActivitySwitch;
import com.ido.ble.protocol.model.AllHealthMonitorSwitch;
import com.ido.ble.protocol.model.BtA2dpHfpStatus;
import com.ido.ble.protocol.model.CalorieAndDistanceGoal;
import com.ido.ble.protocol.model.DeviceUpgradeState;
import com.ido.ble.protocol.model.FirmwareAndBt3Version;
import com.ido.ble.protocol.model.MenuList;
import com.ido.ble.protocol.model.NotDisturbPara;
import com.ido.ble.protocol.model.PressCalibrationValue;
import com.ido.ble.protocol.model.ScreenBrightness;
import com.ido.ble.protocol.model.SupportSportInfoV3;
import com.ido.ble.protocol.model.UpHandGesture;
import com.ido.ble.protocol.model.WalkReminder;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final class m {
    m() {
    }

    public static void a(int i, int i2, int i3) {
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS == com.veryfit.multi.nativeprotocol.a.a(i2)) {
        }
        LogTool.b(com.ido.ble.logs.a.f4633a, "[GET_PARA] get device para failed! evt_type =" + i + ", errorCode = " + com.veryfit.multi.nativeprotocol.a.a(i2));
        if (i == 316) {
            i(null, i2);
            return;
        }
        if (i == 335) {
            a(null, i2);
            return;
        }
        if (i == 5016) {
            l(null, i2);
            return;
        }
        if (i == 5018) {
            b(null, i2);
            return;
        }
        switch (i) {
            case com.veryfit.multi.nativeprotocol.b.g1 /* 325 */:
                j(null, i2);
                break;
            case com.veryfit.multi.nativeprotocol.b.h1 /* 326 */:
                m(null, i2);
                break;
            case com.veryfit.multi.nativeprotocol.b.i1 /* 327 */:
                n(null, i2);
                break;
            default:
                switch (i) {
                    case com.veryfit.multi.nativeprotocol.b.j1 /* 331 */:
                        k(null, i2);
                        break;
                    case com.veryfit.multi.nativeprotocol.b.k1 /* 332 */:
                        o(null, i2);
                        break;
                    case 333:
                        c(null, i2);
                        break;
                }
                break;
        }
    }

    public static void a(int i, byte[] bArr, int i2) {
        if (i == 316) {
            i(bArr, i2);
        }
        if (i == 323) {
            h(bArr, i2);
            return;
        }
        if (i == 5016) {
            l(bArr, i2);
            return;
        }
        if (i == 5018) {
            b(bArr, i2);
            return;
        }
        if (i == 352) {
            e(bArr, i2);
            return;
        }
        if (i == 353) {
            f(bArr, i2);
            return;
        }
        switch (i) {
            case com.veryfit.multi.nativeprotocol.b.g1 /* 325 */:
                j(bArr, i2);
                break;
            case com.veryfit.multi.nativeprotocol.b.h1 /* 326 */:
                m(bArr, i2);
                break;
            case com.veryfit.multi.nativeprotocol.b.i1 /* 327 */:
                n(bArr, i2);
                break;
            default:
                switch (i) {
                    case com.veryfit.multi.nativeprotocol.b.j1 /* 331 */:
                        k(bArr, i2);
                        break;
                    case com.veryfit.multi.nativeprotocol.b.k1 /* 332 */:
                        o(bArr, i2);
                        break;
                    case 333:
                        c(bArr, i2);
                        break;
                    default:
                        switch (i) {
                            case com.veryfit.multi.nativeprotocol.b.n1 /* 335 */:
                                a(bArr, i2);
                                break;
                            case com.veryfit.multi.nativeprotocol.b.o1 /* 336 */:
                                d(bArr, i2);
                                break;
                            case com.veryfit.multi.nativeprotocol.b.p1 /* 337 */:
                                g(bArr, i2);
                                break;
                        }
                        break;
                }
                break;
        }
    }

    private static void a(byte[] bArr, int i) {
        ActivitySwitch activitySwitch;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            activitySwitch = null;
        } else {
            String strD = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f4633a, "[GET_PARA] [handleActivitySwitch] jsonString is " + strD);
            activitySwitch = (ActivitySwitch) new Gson().fromJson(strD, ActivitySwitch.class);
        }
        GetDeviceParaCallBack.onGetActivitySwitch(activitySwitch);
    }

    public static boolean a(int i) {
        if (i == 316 || i == 323 || i == 5016 || i == 5018 || i == 352 || i == 353) {
            return true;
        }
        switch (i) {
            case com.veryfit.multi.nativeprotocol.b.g1 /* 325 */:
            case com.veryfit.multi.nativeprotocol.b.h1 /* 326 */:
            case com.veryfit.multi.nativeprotocol.b.i1 /* 327 */:
                return true;
            default:
                switch (i) {
                    case com.veryfit.multi.nativeprotocol.b.j1 /* 331 */:
                    case com.veryfit.multi.nativeprotocol.b.k1 /* 332 */:
                    case 333:
                        return true;
                    default:
                        switch (i) {
                            case com.veryfit.multi.nativeprotocol.b.n1 /* 335 */:
                            case com.veryfit.multi.nativeprotocol.b.o1 /* 336 */:
                            case com.veryfit.multi.nativeprotocol.b.p1 /* 337 */:
                                return true;
                            default:
                                return false;
                        }
                }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void b(byte[] r1, int r2) {
        /*
            com.veryfit.multi.nativeprotocol.a r0 = com.veryfit.multi.nativeprotocol.a.SUCCESS
            com.veryfit.multi.nativeprotocol.a r2 = com.veryfit.multi.nativeprotocol.a.a(r2)
            if (r0 == r2) goto L9
            goto L32
        L9:
            java.lang.String r1 = com.ido.ble.common.c.d(r1)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r0 = "[GET_PARA] [handleAlarmV3] jsonString is "
            r2.append(r0)
            r2.append(r1)
            java.lang.String r2 = r2.toString()
            java.lang.String r0 = "IDO_CMD"
            com.ido.ble.logs.LogTool.d(r0, r2)
            com.google.gson.Gson r2 = new com.google.gson.Gson
            r2.<init>()
            java.lang.Class<com.ido.ble.protocol.model.AlarmV3CmdParaWrapper$AlarmGetResponse> r0 = com.ido.ble.protocol.model.AlarmV3CmdParaWrapper.AlarmGetResponse.class
            java.lang.Object r1 = r2.fromJson(r1, r0)
            com.ido.ble.protocol.model.AlarmV3CmdParaWrapper$AlarmGetResponse r1 = (com.ido.ble.protocol.model.AlarmV3CmdParaWrapper.AlarmGetResponse) r1
            if (r1 != 0) goto L37
        L32:
            r1 = 0
        L33:
            com.ido.ble.callback.GetDeviceParaCallBack.onGetAlarmV3(r1)
            goto L3a
        L37:
            java.util.List<com.ido.ble.protocol.model.AlarmV3> r1 = r1.item
            goto L33
        L3a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.ble.protocol.handler.m.b(byte[], int):void");
    }

    private static void c(byte[] bArr, int i) {
        AllHealthMonitorSwitch allHealthMonitorSwitch;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            allHealthMonitorSwitch = null;
        } else {
            String strD = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f4633a, "[GET_PARA] [handleAllHealthMonitorState] jsonString is " + strD);
            allHealthMonitorSwitch = (AllHealthMonitorSwitch) new Gson().fromJson(strD, AllHealthMonitorSwitch.class);
        }
        GetDeviceParaCallBack.onGetAllHealthMonitorSwitch(allHealthMonitorSwitch);
    }

    private static void d(byte[] bArr, int i) {
        FirmwareAndBt3Version firmwareAndBt3Version;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            firmwareAndBt3Version = null;
        } else {
            String strD = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f4633a, "[GET_PARA] [handleFirmwareAndBt3Version] jsonString is " + strD);
            firmwareAndBt3Version = (FirmwareAndBt3Version) new Gson().fromJson(strD, FirmwareAndBt3Version.class);
        }
        GetDeviceParaCallBack.onGetFirmwareAndBt3Version(firmwareAndBt3Version);
    }

    private static void e(byte[] bArr, int i) {
        BtA2dpHfpStatus btA2dpHfpStatus;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            btA2dpHfpStatus = null;
        } else {
            String strD = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f4633a, "[GET_PARA] [handleGetBtA2dpHfpStatus] jsonString is " + strD);
            btA2dpHfpStatus = (BtA2dpHfpStatus) new Gson().fromJson(strD, BtA2dpHfpStatus.class);
        }
        GetDeviceParaCallBack.onGetBtA2dpHfpStatus(btA2dpHfpStatus);
    }

    private static void f(byte[] bArr, int i) {
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            GetDeviceParaCallBack.onGetContactReceiveTime(false);
            return;
        }
        String strD = com.ido.ble.common.c.d(bArr);
        LogTool.d(com.ido.ble.logs.a.f4633a, "[GET_PARA] [handleGetContactReceiveTime] jsonString is " + strD);
        try {
            if (new JSONObject(strD).getInt("result") == 1) {
                GetDeviceParaCallBack.onGetContactReceiveTime(true);
            } else {
                GetDeviceParaCallBack.onGetContactReceiveTime(false);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private static void g(byte[] bArr, int i) {
        PressCalibrationValue pressCalibrationValue;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            pressCalibrationValue = null;
        } else {
            String strD = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f4633a, "[GET_PARA] [handleGetPressValue] jsonString is " + strD);
            pressCalibrationValue = (PressCalibrationValue) new Gson().fromJson(strD, PressCalibrationValue.class);
        }
        GetDeviceParaCallBack.onGetPressCalibrationValue(pressCalibrationValue);
    }

    private static void h(byte[] bArr, int i) {
        MenuList.DeviceReturnInfo deviceReturnInfo;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            deviceReturnInfo = null;
        } else {
            String strD = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f4633a, "[GET_PARA] [handleMenuList] jsonString is " + strD);
            deviceReturnInfo = (MenuList.DeviceReturnInfo) new Gson().fromJson(strD, MenuList.DeviceReturnInfo.class);
        }
        GetDeviceParaCallBack.onGetMenuList(deviceReturnInfo);
    }

    private static void i(byte[] bArr, int i) {
        NotDisturbPara notDisturbPara;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            notDisturbPara = null;
        } else {
            String strD = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f4633a, "[GET_PARA] [handleNotDisturbPara] jsonString is " + strD);
            notDisturbPara = (NotDisturbPara) new Gson().fromJson(strD, NotDisturbPara.class);
        }
        GetDeviceParaCallBack.onGetDoNotDisturbPara(notDisturbPara);
    }

    private static void j(byte[] bArr, int i) {
        ScreenBrightness screenBrightness;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            screenBrightness = null;
        } else {
            String strD = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f4633a, "[GET_PARA] [handleScreenBrightness] jsonString is " + strD);
            screenBrightness = (ScreenBrightness) new Gson().fromJson(strD, ScreenBrightness.class);
        }
        GetDeviceParaCallBack.onGetScreenBrightness(screenBrightness);
    }

    private static void k(byte[] bArr, int i) {
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            GetDeviceParaCallBack.onGetSportThreeCircleGoal(null, com.ido.ble.bluetooth.a.e());
            return;
        }
        String strD = com.ido.ble.common.c.d(bArr);
        LogTool.d(com.ido.ble.logs.a.f4633a, "[GET_PARA] [handleSportThreeCircleGoal] jsonString is " + strD);
        GetDeviceParaCallBack.onGetSportThreeCircleGoal((CalorieAndDistanceGoal) new Gson().fromJson(strD, CalorieAndDistanceGoal.class), com.ido.ble.bluetooth.a.e());
    }

    private static void l(byte[] bArr, int i) {
        SupportSportInfoV3 supportSportInfoV3;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            supportSportInfoV3 = null;
        } else {
            String strD = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f4633a, "[GET_PARA] [handleSupportSportInfoV3] jsonString is " + strD);
            supportSportInfoV3 = (SupportSportInfoV3) new Gson().fromJson(strD, SupportSportInfoV3.class);
        }
        GetDeviceParaCallBack.onGetSupportSportInfoV3(supportSportInfoV3);
    }

    private static void m(byte[] bArr, int i) {
        UpHandGesture upHandGesture;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            upHandGesture = null;
        } else {
            String strD = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f4633a, "[GET_PARA] [handleUpHandGesture] jsonString is " + strD);
            upHandGesture = (UpHandGesture) new Gson().fromJson(strD, UpHandGesture.class);
        }
        GetDeviceParaCallBack.onGetUpHandGesture(upHandGesture);
    }

    private static void n(byte[] bArr, int i) {
        DeviceUpgradeState deviceUpgradeState;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            deviceUpgradeState = null;
        } else {
            String strD = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f4633a, "[GET_PARA] [handleUpgradeState] jsonString is " + strD);
            deviceUpgradeState = (DeviceUpgradeState) new Gson().fromJson(strD, DeviceUpgradeState.class);
        }
        GetDeviceParaCallBack.onGetDeviceUpgradeState(deviceUpgradeState);
    }

    private static void o(byte[] bArr, int i) {
        WalkReminder walkReminder;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            walkReminder = null;
        } else {
            String strD = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f4633a, "[GET_PARA] [handleWalkReminder] jsonString is " + strD);
            walkReminder = (WalkReminder) new Gson().fromJson(strD, WalkReminder.class);
        }
        GetDeviceParaCallBack.onGetWalkReminder(walkReminder);
    }
}