package com.ido.ble.dfu.nodic.firmware;

import android.os.Build;
import com.alibaba.fastjson.JSONObject;
import com.ido.ble.dfu.nodic.firmware.CheckNewVersionResponse;
import com.ido.ble.dfu.nodic.firmware.FirmwareListener;
import com.ido.ble.logs.LogTool;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* JADX INFO: loaded from: classes2.dex */
class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f4360a = "http://119.23.239.138:9999/firmware/getLatest";

    a() {
    }

    private static CheckNewVersionResponse a(int i, int i2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("firmwareId", (Object) Integer.valueOf(i));
        jSONObject.put("version", (Object) Integer.valueOf(i2));
        return a("", jSONObject.toJSONString());
    }

    private static CheckNewVersionResponse a(CheckNewVersionPara checkNewVersionPara) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("age", (Object) Integer.valueOf(checkNewVersionPara.filterPara.age));
        jSONObject.put("gender", (Object) Integer.valueOf(checkNewVersionPara.filterPara.gender));
        jSONObject.put("firmwareId", (Object) Integer.valueOf(checkNewVersionPara.deviceId));
        jSONObject.put("version", (Object) Integer.valueOf(checkNewVersionPara.firmwareVersion));
        jSONObject.put("mac", (Object) checkNewVersionPara.filterPara.macAddress);
        jSONObject.put("environment", (Object) Integer.valueOf(checkNewVersionPara.filterPara.environment));
        jSONObject.put("os", (Object) "1");
        jSONObject.put("mobileBrand", (Object) Build.MANUFACTURER);
        return a(f4360a, jSONObject.toJSONString());
    }

    private static CheckNewVersionResponse a(String str, String str2) {
        RequestBody requestBodyCreate = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), str2);
        try {
            Response responseExecute = new OkHttpClient().newCall(new Request.Builder().url(str).addHeader("content-type", "application/json").post(requestBodyCreate).build()).execute();
            if (responseExecute.isSuccessful()) {
                return d.a(responseExecute.body().string());
            }
            LogTool.b(b.f4361a, responseExecute.networkResponse().toString());
            return null;
        } catch (IOException e2) {
            LogTool.b(b.f4361a, e2.toString());
            return null;
        }
    }

    static void a(CheckNewVersionPara checkNewVersionPara, FirmwareListener.ICheckNewVersionListener iCheckNewVersionListener) {
        if (checkNewVersionPara == null) {
            iCheckNewVersionListener.onCheckFailed();
            return;
        }
        CheckNewVersionResponse checkNewVersionResponseA = checkNewVersionPara.filterPara == null ? a(checkNewVersionPara.deviceId, checkNewVersionPara.firmwareVersion) : a(checkNewVersionPara);
        if (checkNewVersionResponseA == null) {
            iCheckNewVersionListener.onCheckFailed();
            return;
        }
        if (checkNewVersionResponseA.resultCode != 1) {
            iCheckNewVersionListener.onCheckFailed();
            return;
        }
        CheckNewVersionResponse.NewVersionInfo newVersionInfo = checkNewVersionResponseA.data;
        if (newVersionInfo == null) {
            iCheckNewVersionListener.onNoNewVersion();
        } else {
            iCheckNewVersionListener.onHasNewVersion(newVersionInfo);
        }
    }
}