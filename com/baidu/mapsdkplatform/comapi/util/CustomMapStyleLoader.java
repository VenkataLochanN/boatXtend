package com.baidu.mapsdkplatform.comapi.util;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.JsonReader;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.amazon.identity.auth.map.device.token.Token;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.baidu.mapapi.NetworkUtil;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.http.AsyncHttpClient;
import com.baidu.mapapi.http.HttpClient;
import com.baidu.mapsdkplatform.comjni.util.AppMD5;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CustomMapStyleLoader {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3801a = CustomMapStyleLoader.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static String f3802c;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3803b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f3804d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f3805e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f3806f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private AsyncHttpClient f3807g;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            CustomMapStyleLoader.this.b();
            String strD = CustomMapStyleLoader.this.d();
            if (TextUtils.isEmpty(strD)) {
                Log.e(CustomMapStyleLoader.f3801a, "build request url failed");
            } else {
                CustomMapStyleLoader.this.a(strD);
            }
        }
    }

    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final CustomMapStyleLoader f3809a = new CustomMapStyleLoader(null);
    }

    private CustomMapStyleLoader() {
        this.f3806f = true;
        this.f3807g = new AsyncHttpClient();
    }

    /* synthetic */ CustomMapStyleLoader(c cVar) {
        this();
    }

    private String a(Map<String, String> map) {
        if (map.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String str : map.keySet()) {
            String strEncodeUrlParamsValue = AppMD5.encodeUrlParamsValue(map.get(str));
            if (i != 0) {
                sb.append("&");
            }
            sb.append(str);
            sb.append("=");
            sb.append(strEncodeUrlParamsValue);
            i++;
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        this.f3807g.get(str, new c(this));
    }

    private void a(JSONObject jSONObject) {
        File file = new File(f3802c);
        if (file.exists()) {
            file.delete();
        }
        try {
            if (file.createNewFile()) {
                file.createNewFile();
            }
        } catch (IOException e2) {
            Log.e(f3801a, "create custom file failed", e2);
        }
        String strOptString = jSONObject.optString("json");
        String strOptString2 = jSONObject.optString("md5", "null");
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("json", strOptString);
            jSONObject2.put("md5", strOptString2);
        } catch (JSONException e3) {
            Log.e(f3801a, "build style data failed", e3);
        }
        String string = jSONObject2.toString();
        try {
            FileOutputStream fileOutputStreamOpenFileOutput = this.f3804d.openFileOutput("server_custom_style_file.json", 0);
            fileOutputStreamOpenFileOutput.write(string.getBytes());
            fileOutputStreamOpenFileOutput.flush();
            fileOutputStreamOpenFileOutput.close();
        } catch (IOException e4) {
            Log.e(f3801a, "write style data into file failed", e4);
        }
    }

    private boolean a(int i, String str) {
        if (103 == i && c()) {
            b(i, str);
            return false;
        }
        b(i, str);
        return i == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (!c()) {
            this.f3803b = "null";
            return;
        }
        try {
            FileInputStream fileInputStreamOpenFileInput = this.f3804d.openFileInput("server_custom_style_file.json");
            JsonReader jsonReader = new JsonReader(new InputStreamReader(fileInputStreamOpenFileInput));
            try {
                try {
                    try {
                        jsonReader.beginObject();
                        while (jsonReader.hasNext()) {
                            if (jsonReader.nextName().equals("md5")) {
                                this.f3803b = jsonReader.nextString();
                            } else {
                                jsonReader.skipValue();
                            }
                        }
                        jsonReader.endObject();
                        jsonReader.close();
                        fileInputStreamOpenFileInput.close();
                    } catch (IOException e2) {
                        this.f3803b = "null";
                        Log.e(f3801a, "Read custom style failed", e2);
                        jsonReader.close();
                        fileInputStreamOpenFileInput.close();
                    }
                } catch (IOException e3) {
                    Log.e(f3801a, "Close custom style failed", e3);
                }
            } catch (Throwable th) {
                try {
                    jsonReader.close();
                    fileInputStreamOpenFileInput.close();
                } catch (IOException e4) {
                    Log.e(f3801a, "Close custom style failed", e4);
                }
                throw th;
            }
        } catch (FileNotFoundException e5) {
            this.f3803b = "null";
            Log.e(f3801a, "Open custom style failed", e5);
        }
    }

    private void b(int i, String str) {
        Intent intent = i == 0 ? new Intent(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_LOAD_CUSTOM_STYLE_SUCCESS) : new Intent(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_LOAD_CUSTOM_STYLE_ERROR);
        intent.putExtra(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE, i);
        intent.putExtra(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_MESSAGE, str);
        this.f3804d.sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (a(jSONObject.optInt(NotificationCompat.CATEGORY_STATUS), jSONObject.optString("message"))) {
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(AeUtil.ROOT_DATA_PATH_OLD_NAME);
                if (jSONObjectOptJSONObject == null || jSONObjectOptJSONObject.length() == 0) {
                    Log.e(f3801a, "custom style data is null");
                } else {
                    a(jSONObjectOptJSONObject);
                }
            }
        } catch (JSONException e2) {
            Log.e(f3801a, "parse response result failed", e2);
        }
    }

    private boolean c() {
        return new File(f3802c).exists();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String d() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("style_id", this.f3805e);
        linkedHashMap.put("type", this.f3806f ? "publish" : "edit");
        linkedHashMap.put("md5", this.f3803b);
        linkedHashMap.put(Token.KEY_TOKEN, i.y);
        String str = a(linkedHashMap) + i.c();
        return e() + "?" + (str + "&sign=" + AppMD5.getSignMD5String(str));
    }

    private String e() {
        return HttpClient.isHttpsEnable ? "https://api.map.baidu.com/sdkproxy/v2/lbs_androidsdk/custom/v2/getjsonstyle" : "http://api.map.baidu.com/sdkproxy/v2/lbs_androidsdk/custom/v2/getjsonstyle";
    }

    public static String getCustomStyleFilePath() {
        return f3802c;
    }

    public static CustomMapStyleLoader getInstance() {
        return b.f3809a;
    }

    public void initCustomStyleFilePath(Context context) {
        this.f3804d = context;
        f3802c = this.f3804d.getFilesDir().getAbsolutePath();
        f3802c += "/server_custom_style_file.json";
    }

    public void loadCustomMapStyleFile(String str, boolean z) {
        if (NetworkUtil.isNetworkAvailable(this.f3804d) && !TextUtils.isEmpty(str)) {
            this.f3805e = str;
            this.f3806f = z;
            new Thread(new a(), "Load custom style").start();
        }
    }
}