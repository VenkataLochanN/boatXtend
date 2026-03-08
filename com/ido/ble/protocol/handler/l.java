package com.ido.ble.protocol.handler;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.ido.ble.callback.ConnectCallBack;
import com.ido.ble.callback.GetDeviceInfoCallBack;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.ActivityDataCount;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.ble.protocol.model.BatteryInfo;
import com.ido.ble.protocol.model.CanDownLangInfo;
import com.ido.ble.protocol.model.CanDownLangInfoV3;
import com.ido.ble.protocol.model.DeviceSummarySoftVersionInfo;
import com.ido.ble.protocol.model.FlashBinInfo;
import com.ido.ble.protocol.model.HIDInfo;
import com.ido.ble.protocol.model.LiveData;
import com.ido.ble.protocol.model.MacAddressInfo;
import com.ido.ble.protocol.model.NoticeReminderSwitchStatus;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final class l {
    l() {
    }

    private static String a(JSONArray jSONArray) {
        byte[] bArr = new byte[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            bArr[i] = (byte) jSONArray.getInt(i);
        }
        String strB = com.ido.ble.common.c.b(bArr);
        if (TextUtils.isEmpty(strB)) {
            return null;
        }
        return strB.replace(" ", ":").substring(0, r4.length() - 1).toUpperCase();
    }

    public static void a(int i, int i2, int i3) {
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS == com.veryfit.multi.nativeprotocol.a.a(i2)) {
        }
        LogTool.b(com.ido.ble.logs.a.f4633a, "[GET_INFO] get info failed! evt_type =" + i + ", errorCode = " + com.veryfit.multi.nativeprotocol.a.a(i2));
        if (i == 300) {
            k(null, i2);
            return;
        }
        if (i == 301) {
            b(null, i2);
            return;
        }
        if (i == 310) {
            i(null, i2);
            return;
        }
        switch (i) {
            case 303:
                o(null, i2);
                break;
            case 304:
                j(null, i2);
                break;
            case 305:
                g(null, i2);
                break;
        }
    }

    public static void a(int i, byte[] bArr, int i2) {
        if (i == 300) {
            k(bArr, i2);
        }
        if (i == 301) {
            b(bArr, i2);
            return;
        }
        if (i == 309) {
            a(bArr, i2);
            return;
        }
        if (i == 310) {
            i(bArr, i2);
            return;
        }
        if (i == 315) {
            f(bArr, i2);
            return;
        }
        if (i == 319) {
            d(bArr, i2);
            return;
        }
        if (i == 5024) {
            e(bArr, i2);
            return;
        }
        if (i == 321) {
            c(bArr, i2);
            return;
        }
        if (i == 322) {
            h(bArr, i2);
            return;
        }
        switch (i) {
            case 303:
                o(bArr, i2);
                break;
            case 304:
                j(bArr, i2);
                break;
            case 305:
                g(bArr, i2);
                break;
            case 306:
                l(bArr, i2);
                break;
        }
    }

    private static void a(BasicInfo basicInfo) {
        if (basicInfo != null && com.ido.ble.bluetooth.a.g() && basicInfo.pairFlag == 0) {
            ConnectCallBack.e(com.ido.ble.bluetooth.a.e());
        }
    }

    private static void a(byte[] bArr, int i) {
        String strD = com.ido.ble.common.c.d(bArr);
        LogTool.d(com.ido.ble.logs.a.f4633a, "[GET_INFO] [handleActivityCount] " + strD);
        GetDeviceInfoCallBack.a(TextUtils.isEmpty(strD) ? null : (ActivityDataCount) com.ido.ble.common.k.c(strD, ActivityDataCount.class));
    }

    public static boolean a(int i) {
        if (i == 300 || i == 301 || i == 315 || i == 319 || i == 5024 || i == 321 || i == 322) {
            return true;
        }
        switch (i) {
            case 303:
            case 304:
            case 305:
            case 306:
            case 307:
            case 308:
            case com.veryfit.multi.nativeprotocol.b.T0 /* 309 */:
            case com.veryfit.multi.nativeprotocol.b.U0 /* 310 */:
                return true;
            default:
                return false;
        }
    }

    private static void b(byte[] bArr, int i) {
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            GetDeviceInfoCallBack.a((BasicInfo) null);
            return;
        }
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[GET_INFO] [handleBasicInfo] json is null");
            GetDeviceInfoCallBack.a((BasicInfo) null);
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f4633a, "[GET_INFO] [handleBasicInfo] json is" + strD);
        BasicInfo basicInfo = (BasicInfo) new Gson().fromJson(strD, BasicInfo.class);
        GetDeviceInfoCallBack.a(basicInfo);
        a(basicInfo);
    }

    private static void c(byte[] bArr, int i) {
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            GetDeviceInfoCallBack.a((BatteryInfo) null);
            return;
        }
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[GET_INFO] [handleBatteryInfo] json is null");
            GetDeviceInfoCallBack.a((BatteryInfo) null);
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f4633a, "[GET_INFO] [handleBatteryInfo] json is" + strD);
        GetDeviceInfoCallBack.a((BatteryInfo) new Gson().fromJson(strD, BatteryInfo.class));
    }

    private static void d(byte[] bArr, int i) {
        CanDownLangInfo canDownLangInfo;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            canDownLangInfo = null;
        } else {
            String strD = com.ido.ble.common.c.d(bArr);
            if (TextUtils.isEmpty(strD)) {
                LogTool.b(com.ido.ble.logs.a.f4633a, "[GET_INFO] [handleCanDownloadLangInfo] json is null");
                return;
            }
            LogTool.d(com.ido.ble.logs.a.f4633a, "[GET_INFO] [handleCanDownloadLangInfo] json is" + strD);
            canDownLangInfo = (CanDownLangInfo) new Gson().fromJson(strD, CanDownLangInfo.class);
        }
        GetDeviceInfoCallBack.a(canDownLangInfo);
    }

    private static void e(byte[] bArr, int i) {
        CanDownLangInfoV3 canDownLangInfoV3;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            canDownLangInfoV3 = null;
        } else {
            String strD = com.ido.ble.common.c.d(bArr);
            if (TextUtils.isEmpty(strD)) {
                LogTool.b(com.ido.ble.logs.a.f4633a, "[GET_INFO] [handleCanDownloadLangInfoV3] json is null");
                return;
            }
            LogTool.d(com.ido.ble.logs.a.f4633a, "[GET_INFO] [handleCanDownloadLangInfoV3] json is" + strD);
            canDownLangInfoV3 = (CanDownLangInfoV3) new Gson().fromJson(strD, CanDownLangInfoV3.class);
        }
        GetDeviceInfoCallBack.a(canDownLangInfoV3);
    }

    private static void f(byte[] bArr, int i) {
        GetDeviceInfoCallBack.a(com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i) ? null : (DeviceSummarySoftVersionInfo) new Gson().fromJson(com.ido.ble.common.c.d(bArr), DeviceSummarySoftVersionInfo.class));
    }

    private static void g(byte[] bArr, int i) {
    }

    private static void h(byte[] bArr, int i) {
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            GetDeviceInfoCallBack.a((FlashBinInfo) null);
            return;
        }
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[GET_INFO] [handleFlashBinInfo] json is null");
            GetDeviceInfoCallBack.a((BasicInfo) null);
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f4633a, "[GET_INFO] [handleFlashBinInfo] json is" + strD);
        GetDeviceInfoCallBack.a((FlashBinInfo) new Gson().fromJson(strD, FlashBinInfo.class));
    }

    private static void i(byte[] bArr, int i) {
        GetDeviceInfoCallBack.a(com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i) ? null : (HIDInfo) new Gson().fromJson(com.ido.ble.common.c.d(bArr), HIDInfo.class));
    }

    private static void j(byte[] bArr, int i) {
        GetDeviceInfoCallBack.a(com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i) ? null : (LiveData) new Gson().fromJson(com.ido.ble.common.c.d(bArr), LiveData.class));
    }

    private static void k(byte[] bArr, int i) {
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS == com.veryfit.multi.nativeprotocol.a.a(i)) {
            String strD = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f4633a, "[GET_INFO] [handleMacAddress] json is" + strD);
            if (TextUtils.isEmpty(strD)) {
                LogTool.d(com.ido.ble.logs.a.f4633a, "[GET_INFO] [handleMacAddress] json is null");
                GetDeviceInfoCallBack.a((MacAddressInfo) null);
                return;
            }
            try {
                MacAddressInfo macAddressInfo = new MacAddressInfo();
                JSONObject jSONObject = new JSONObject(strD);
                if (jSONObject.has("macAddr")) {
                    macAddressInfo.bleAddress = a(jSONObject.getJSONArray("macAddr"));
                }
                if (jSONObject.has("btAddr")) {
                    macAddressInfo.btAddress = a(jSONObject.getJSONArray("btAddr"));
                }
                GetDeviceInfoCallBack.a(macAddressInfo);
                return;
            } catch (Exception unused) {
            }
        }
        GetDeviceInfoCallBack.a((MacAddressInfo) null);
    }

    private static void l(byte[] bArr, int i) {
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            GetDeviceInfoCallBack.a((NoticeReminderSwitchStatus) null);
            return;
        }
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[GET_INFO] [handleNoticeReminderSwitchStatus] json is null");
            GetDeviceInfoCallBack.a((NoticeReminderSwitchStatus) null);
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f4633a, "[GET_INFO] [handleNoticeReminderSwitchStatus] json is" + strD);
        GetDeviceInfoCallBack.a((NoticeReminderSwitchStatus) new Gson().fromJson(strD, NoticeReminderSwitchStatus.class));
    }

    private static void m(byte[] bArr, int i) {
    }

    private static void n(byte[] bArr, int i) {
    }

    private static void o(byte[] bArr, int i) {
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            GetDeviceInfoCallBack.a((SupportFunctionInfo) null);
            LogTool.d(com.ido.ble.logs.a.f4633a, "[GET_INFO] [handleSupportFunctionInfo] get failed, errorCode=" + i);
            return;
        }
        String strD = com.ido.ble.common.c.d(bArr);
        LogTool.d(com.ido.ble.logs.a.f4633a, "[GET_INFO] [handleSupportFunctionInfo] json is" + strD);
        SupportFunctionInfo supportFunctionInfo = (SupportFunctionInfo) new Gson().fromJson(strD, SupportFunctionInfo.class);
        if (supportFunctionInfo == null) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[GET_INFO] [handleSupportFunctionInfo] transform json failed!");
        } else {
            com.ido.ble.f.a.f.a.c0().a(supportFunctionInfo);
        }
        GetDeviceInfoCallBack.a(supportFunctionInfo);
    }
}