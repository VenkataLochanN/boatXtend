package com.ido.alexa.manager;

import com.ido.alexa.AlexaApp;
import com.ido.alexa.AlexaConstant;
import com.ido.alexa.util.Util;

/* JADX INFO: loaded from: classes2.dex */
public class SpManager {
    public static void setClientId(String str) {
        Util.put("clientId", str);
    }

    public static String getClientId() {
        return (String) Util.get("clientId", "");
    }

    public static void setProductId(String str) {
        Util.put(AlexaConstant.SPConstant.KEY_PRODUCTID, str);
    }

    public static String getProductId() {
        return (String) Util.get(AlexaConstant.SPConstant.KEY_PRODUCTID, "");
    }

    public static void setDeviceSerialNumber(String str) {
        Util.put(AlexaConstant.SPConstant.KEY_DEVICESERIALNUMBER, str);
    }

    public static String getDeviceSerialNumber() {
        return (String) Util.get(AlexaConstant.SPConstant.KEY_DEVICESERIALNUMBER, "");
    }

    public static void setBleName(String str) {
        Util.put(AlexaConstant.SPConstant.KEY_BLE_NAME, str);
    }

    public static String getBleName() {
        return (String) Util.get(AlexaConstant.SPConstant.KEY_BLE_NAME, "");
    }

    public static void setMacAdress(String str) {
        Util.put(AlexaConstant.SPConstant.KEY_MACADRESS, str);
    }

    public static String getMacAdress() {
        return (String) Util.get(AlexaConstant.SPConstant.KEY_MACADRESS, "");
    }

    public static void setAccessToken(String str) {
        Util.put("access_token", str);
    }

    public static String getAccessToken() {
        return (String) Util.get("access_token", "");
    }

    public static void setRefreshToken(String str) {
        Util.put("refresh_token", str);
    }

    public static String getRefreshToken() {
        return (String) Util.get("refresh_token", "");
    }

    public static void setTokenExpires(long j) {
        Util.put(AlexaConstant.SPConstant.KEY_TOKEN_EXPIRES, Long.valueOf(j));
    }

    public static long getTokenExpires() {
        return ((Long) Util.get(AlexaConstant.SPConstant.KEY_TOKEN_EXPIRES, 0L)).longValue();
    }

    public static void setFirmwareVersion(long j) {
        Util.put(AlexaConstant.SPConstant.KEY_DEVICE_FIRMWAREVERSION, Long.valueOf(j));
    }

    public static long getFirmwareVersion() {
        return ((Long) Util.get(AlexaConstant.SPConstant.KEY_DEVICE_FIRMWAREVERSION, 0L)).longValue();
    }

    public static void setEndpointId(String str) {
        Util.put(AlexaConstant.SPConstant.KEY_ENDPOINTID, str);
    }

    public static String getEndpointId() {
        return (String) Util.get(AlexaConstant.SPConstant.KEY_ENDPOINTID, "");
    }

    public static String getAlexaLanguage() {
        return Util.getPreferences(AlexaApp.getAppContext()).getString(AlexaConstant.SPConstant.ALEXA_LANGUAGE, "");
    }

    public static void setAlexaLanguage(String str) {
        Util.getPreferences(AlexaApp.getAppContext()).edit().putString(AlexaConstant.SPConstant.ALEXA_LANGUAGE, str).apply();
    }

    public static String getAlexaEndpoint() {
        return Util.getPreferences(AlexaApp.getAppContext()).getString(AlexaConstant.SPConstant.ALEXA_ENDPOINT, "");
    }

    public static void setAlexaEndpoint(String str) {
        Util.getPreferences(AlexaApp.getAppContext()).edit().putString(AlexaConstant.SPConstant.ALEXA_ENDPOINT, str).apply();
    }
}