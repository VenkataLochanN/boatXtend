package com.ido.life.util;

import android.content.Context;
import android.text.TextUtils;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.ble.protocol.model.FirmwareAndBt3Version;

/* JADX INFO: loaded from: classes3.dex */
public class DeviceUtil {
    public static String deviceVersionChange(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (str.contains(".")) {
            return str;
        }
        return str + ".0.0";
    }

    public static String getDeviceThirdVersion(FirmwareAndBt3Version firmwareAndBt3Version) {
        Object objValueOf;
        Object objValueOf2;
        if (firmwareAndBt3Version == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(firmwareAndBt3Version.firmware_version1);
        sb.append(".");
        if (firmwareAndBt3Version.firmware_version2 < 10) {
            objValueOf = AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + firmwareAndBt3Version.firmware_version2;
        } else {
            objValueOf = Integer.valueOf(firmwareAndBt3Version.firmware_version2);
        }
        sb.append(objValueOf);
        sb.append(".");
        if (firmwareAndBt3Version.firmware_version3 < 10) {
            objValueOf2 = AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + firmwareAndBt3Version.firmware_version3;
        } else {
            objValueOf2 = Integer.valueOf(firmwareAndBt3Version.firmware_version3);
        }
        sb.append(objValueOf2);
        return sb.toString();
    }

    public static String getDeviceBTMatchVersion(FirmwareAndBt3Version firmwareAndBt3Version) {
        Object objValueOf;
        Object objValueOf2;
        if (firmwareAndBt3Version == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(firmwareAndBt3Version.BT_match_version1);
        sb.append(".");
        if (firmwareAndBt3Version.BT_match_version2 < 10) {
            objValueOf = AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + firmwareAndBt3Version.BT_match_version2;
        } else {
            objValueOf = Integer.valueOf(firmwareAndBt3Version.BT_match_version2);
        }
        sb.append(objValueOf);
        sb.append(".");
        if (firmwareAndBt3Version.BT_match_version3 < 10) {
            objValueOf2 = AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + firmwareAndBt3Version.BT_match_version3;
        } else {
            objValueOf2 = Integer.valueOf(firmwareAndBt3Version.BT_match_version3);
        }
        sb.append(objValueOf2);
        return sb.toString();
    }

    public static String getDeviceBTCurrentVersion(FirmwareAndBt3Version firmwareAndBt3Version) {
        Object objValueOf;
        Object objValueOf2;
        if (firmwareAndBt3Version == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(firmwareAndBt3Version.BT_version1);
        sb.append(".");
        if (firmwareAndBt3Version.BT_version2 < 10) {
            objValueOf = AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + firmwareAndBt3Version.BT_version2;
        } else {
            objValueOf = Integer.valueOf(firmwareAndBt3Version.BT_version2);
        }
        sb.append(objValueOf);
        sb.append(".");
        if (firmwareAndBt3Version.BT_version3 < 10) {
            objValueOf2 = AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE + firmwareAndBt3Version.BT_version3;
        } else {
            objValueOf2 = Integer.valueOf(firmwareAndBt3Version.BT_version3);
        }
        sb.append(objValueOf2);
        return sb.toString();
    }

    public static String getStringFromName(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return context.getResources().getString(context.getResources().getIdentifier(str, "string", context.getPackageName()));
        } catch (Exception unused) {
            return "";
        }
    }
}