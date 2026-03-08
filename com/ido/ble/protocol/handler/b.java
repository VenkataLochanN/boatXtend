package com.ido.ble.protocol.handler;

import com.google.gson.Gson;
import com.ido.ble.callback.AppSendAllPhoneContactsCallBack;
import com.ido.ble.callback.AppSendDataCallBack;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.AllPhoneContacts;
import com.ido.ble.protocol.model.WeatherInfoV3;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class b {
    public static void a(int i, int i2, int i3) {
        if (i != 153) {
            return;
        }
        a(i2, AppSendDataCallBack.DataType.WEATHER);
    }

    private static void a(int i, AppSendDataCallBack.DataType dataType) {
        AppSendDataCallBack.a(i == 0, dataType);
    }

    public static void a(int i, byte[] bArr, int i2) {
        String strD = com.ido.ble.common.c.d(bArr);
        if (i == 5045) {
            LogTool.d(com.ido.ble.logs.a.f4633a, "[APP_SEND_DATA] [handle Device Return set v3 weather JsonString] " + strD);
            a(strD, AppSendDataCallBack.DataType.WEATHER_V3);
            return;
        }
        if (i == 5518) {
            LogTool.d(com.ido.ble.logs.a.f4633a, "[APP_SEND_DATA] [handle Device Return set all phone contacts] " + strD);
            a(strD);
            return;
        }
        if (i != 6504) {
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f4633a, "[APP_SEND_DATA] [handle Device Return set sun time JsonString] " + strD);
        b(strD, AppSendDataCallBack.DataType.WEATHER_SUN_TIME);
    }

    private static void a(String str) {
        LogTool.d(com.ido.ble.logs.a.f4633a, "[OPERATE_PARA] [handle Device Return set AllPhoneContacts JsonString] " + str);
        AllPhoneContacts.Response response = (AllPhoneContacts.Response) new Gson().fromJson(str, AllPhoneContacts.Response.class);
        if (response == null) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[OPERATE_PARA] [handle Device Return AllPhoneContacts JsonString] response == null");
        } else {
            AppSendAllPhoneContactsCallBack.onCallBack(response.path);
        }
    }

    private static void a(String str, AppSendDataCallBack.DataType dataType) {
        LogTool.d(com.ido.ble.logs.a.f4633a, "[OPERATE_PARA] [handle Device Return V3Weather JsonString] " + str);
        WeatherInfoV3.Response response = (WeatherInfoV3.Response) new Gson().fromJson(str, WeatherInfoV3.Response.class);
        if (response == null) {
            LogTool.b(com.ido.ble.logs.a.f4633a, "[OPERATE_PARA] [handle Device Return V3Weather JsonString] response == null");
        } else {
            AppSendDataCallBack.a(response.err_code == 0, dataType);
        }
    }

    static boolean a(int i) {
        return i == 153 || i == 5045 || i == 5518 || i == 6504;
    }

    private static void b(String str, AppSendDataCallBack.DataType dataType) {
        int iOptInt;
        try {
            iOptInt = new JSONObject(str).optInt("is_success");
        } catch (JSONException e2) {
            LogTool.b(com.ido.ble.logs.a.f4633a, com.ido.ble.logs.a.p + e2.getMessage());
            iOptInt = 0;
        }
        AppSendDataCallBack.a(iOptInt == 1, dataType);
    }
}