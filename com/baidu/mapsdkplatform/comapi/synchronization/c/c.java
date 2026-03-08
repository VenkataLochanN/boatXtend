package com.baidu.mapsdkplatform.comapi.synchronization.c;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.baidu.mapapi.JNIInitializer;
import com.baidu.mapsdkplatform.comapi.util.PermissionCheck;
import com.bumptech.glide.load.Key;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    HttpURLConnection f3686a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f3687d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f3688e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f3689f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f3690g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f3691h;
    private e i;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f3685c = c.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f3684b = true;

    public enum a {
        SUCCESS,
        NETWORK_ERROR,
        INNER_ERROR,
        REQUEST_ERROR,
        SERVER_ERROR
    }

    public c(String str, e eVar) {
        this.f3691h = str;
        this.i = eVar;
    }

    private void a(InputStream inputStream, BufferedReader bufferedReader, HttpURLConnection httpURLConnection) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e2) {
                com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3685c, "IOException happened when release res", e2);
            }
        }
        if (bufferedReader != null) {
            bufferedReader.close();
        }
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    private void a(HttpURLConnection httpURLConnection) throws Throwable {
        try {
            httpURLConnection.connect();
            int responseCode = httpURLConnection.getResponseCode();
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3685c, "responseCode is: " + responseCode);
            if (200 != responseCode) {
                a(httpURLConnection, responseCode);
            } else {
                b(httpURLConnection);
            }
        } catch (IOException unused) {
            httpURLConnection.disconnect();
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3685c, "Catch connection exception, INNER_ERROR");
            this.i.a(a.INNER_ERROR);
        }
    }

    private void a(HttpURLConnection httpURLConnection, int i) {
        a aVar = a.SUCCESS;
        a aVar2 = i >= 500 ? a.SERVER_ERROR : i >= 400 ? a.REQUEST_ERROR : a.INNER_ERROR;
        InputStream errorStream = httpURLConnection.getErrorStream();
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3685c, errorStream.toString());
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3685c, "Response error, response code = " + i + ", error = " + aVar2);
        if (errorStream != null) {
            try {
                errorStream.close();
            } catch (IOException e2) {
                com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3685c, "IOException caught", e2);
            }
        }
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
        this.i.a(aVar2);
    }

    private boolean a() {
        NetworkInfo activeNetworkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) JNIInitializer.getCachedContext().getSystemService("connectivity");
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                return false;
            }
            return activeNetworkInfo.isAvailable();
        } catch (Exception e2) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3685c, "Exception happened when check network", e2);
            e2.printStackTrace();
            return false;
        }
    }

    private HttpURLConnection b() {
        HttpURLConnection httpURLConnection;
        try {
            URL url = new URL(this.f3687d);
            if (f3684b) {
                httpURLConnection = (HttpsURLConnection) url.openConnection();
                ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(new d(this));
            } else {
                httpURLConnection = (HttpURLConnection) url.openConnection();
            }
            httpURLConnection.setRequestMethod(this.f3691h);
            httpURLConnection.setDoOutput(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(this.f3689f);
            httpURLConnection.setReadTimeout(this.f3690g);
            return httpURLConnection;
        } catch (Exception e2) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3685c, "url connect failed", e2);
            return null;
        }
    }

    private void b(HttpURLConnection httpURLConnection) throws Throwable {
        BufferedReader bufferedReader;
        Throwable th;
        InputStream inputStream;
        IOException e2;
        StringBuffer stringBuffer;
        try {
            inputStream = httpURLConnection.getInputStream();
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Key.STRING_CHARSET_NAME));
            } catch (IOException e3) {
                bufferedReader = null;
                e2 = e3;
            } catch (Throwable th2) {
                bufferedReader = null;
                th = th2;
            }
        } catch (IOException e4) {
            bufferedReader = null;
            e2 = e4;
            inputStream = null;
        } catch (Throwable th3) {
            bufferedReader = null;
            th = th3;
            inputStream = null;
        }
        try {
            try {
                stringBuffer = new StringBuffer();
            } catch (Throwable th4) {
                th = th4;
            }
            while (true) {
                int i = bufferedReader.read();
                if (i == -1) {
                    this.f3688e = stringBuffer.toString();
                    c(this.f3688e);
                    a(inputStream, bufferedReader, httpURLConnection);
                    this.i.a(this.f3688e);
                    return;
                }
                stringBuffer.append((char) i);
                th = th4;
                a(inputStream, bufferedReader, httpURLConnection);
                throw th;
            }
        } catch (IOException e5) {
            e2 = e5;
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3685c, "Catch exception. INNER_ERROR", e2);
            this.i.a(a.INNER_ERROR);
            a(inputStream, bufferedReader, httpURLConnection);
        }
    }

    private boolean b(String str) {
        if (!TextUtils.isEmpty(str) && this.i != null) {
            return true;
        }
        com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3685c, "RequestUrl or ResultCallback is null. RequestUrl = " + str + "; ResultCallback is: " + this.i);
        this.i.a(a.REQUEST_ERROR);
        return false;
    }

    private void c(String str) {
        if (d(str)) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3685c, "Permission check failed, try again");
            int iPermissionCheck = PermissionCheck.permissionCheck();
            if (iPermissionCheck != 0) {
                com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3685c, "The authorized result is: " + iPermissionCheck);
            }
        }
    }

    private boolean d(String str) {
        return e(str) || f(str);
    }

    private boolean e(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has(NotificationCompat.CATEGORY_STATUS) && !jSONObject.has("status_sp")) {
                return false;
            }
            int i = jSONObject.has(NotificationCompat.CATEGORY_STATUS) ? jSONObject.getInt(NotificationCompat.CATEGORY_STATUS) : jSONObject.getInt("status_sp");
            if (106 != i && 105 != i) {
                return false;
            }
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3685c, "Permission check failed due token");
            return true;
        } catch (JSONException e2) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3685c, "Parse json happened exception", e2);
            return false;
        }
    }

    private boolean f(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("SDK_InnerError") || !jSONObject.optJSONObject("SDK_InnerError").has("PermissionCheckError")) {
                return false;
            }
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3685c, "Permission check error due other");
            return true;
        } catch (JSONException e2) {
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.a(f3685c, "Parse json happened exception", e2);
            return false;
        }
    }

    public void a(int i) {
        this.f3690g = i;
    }

    protected void a(String str) throws Throwable {
        e eVar;
        a aVar;
        if (b(str)) {
            this.f3687d = str;
            com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3685c, "mRequestUrl is: " + this.f3687d);
            if (a()) {
                this.f3686a = b();
                HttpURLConnection httpURLConnection = this.f3686a;
                if (httpURLConnection != null) {
                    a(httpURLConnection);
                    return;
                } else {
                    com.baidu.mapsdkplatform.comapi.synchronization.d.a.b(f3685c, "url connection failed");
                    eVar = this.i;
                    aVar = a.INNER_ERROR;
                }
            } else {
                eVar = this.i;
                aVar = a.NETWORK_ERROR;
            }
            eVar.a(aVar);
        }
    }

    public void b(int i) {
        this.f3689f = i;
    }
}