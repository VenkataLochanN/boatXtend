package com.baidu.mapapi.http;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.baidu.mapapi.JNIInitializer;
import com.baidu.mapapi.common.Logger;
import com.baidu.mapsdkplatform.comapi.util.PermissionCheck;
import com.baidu.mapsdkplatform.comapi.util.i;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class HttpClient {
    public static boolean isHttpsEnable = true;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    HttpURLConnection f2746a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f2747b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f2748c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f2749d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f2750e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f2751f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private ProtoResultCallback f2752g;

    public enum HttpStateError {
        NO_ERROR,
        NETWORK_ERROR,
        INNER_ERROR,
        REQUEST_ERROR,
        SERVER_ERROR
    }

    public static abstract class ProtoResultCallback {
        public abstract void onFailed(HttpStateError httpStateError);

        public abstract void onSuccess(String str);
    }

    public HttpClient(String str, ProtoResultCallback protoResultCallback) {
        this.f2751f = str;
        this.f2752g = protoResultCallback;
    }

    private HttpURLConnection a() {
        HttpURLConnection httpURLConnection;
        try {
            URL url = new URL(this.f2747b);
            if (isHttpsEnable) {
                httpURLConnection = (HttpsURLConnection) url.openConnection();
                ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(new b(this));
            } else {
                httpURLConnection = (HttpURLConnection) url.openConnection();
            }
            httpURLConnection.setRequestMethod(this.f2751f);
            httpURLConnection.setDoOutput(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(this.f2749d);
            httpURLConnection.setReadTimeout(this.f2750e);
            return httpURLConnection;
        } catch (Exception e2) {
            Log.e("HttpClient", "url connect failed");
            if (Logger.debugEnable()) {
                e2.printStackTrace();
                return null;
            }
            Logger.logW("HttpClient", e2.getMessage());
            return null;
        }
    }

    private void a(String str) {
        int iPermissionCheck;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(NotificationCompat.CATEGORY_STATUS) || jSONObject.has("status_sp")) {
                int i = jSONObject.has(NotificationCompat.CATEGORY_STATUS) ? jSONObject.getInt(NotificationCompat.CATEGORY_STATUS) : jSONObject.getInt("status_sp");
                if ((i == 105 || i == 106) && (iPermissionCheck = PermissionCheck.permissionCheck()) != 0) {
                    Log.e("HttpClient", "permissionCheck result is: " + iPermissionCheck);
                }
            }
        } catch (JSONException e2) {
            Log.e("HttpClient", "Parse json happened exception");
            e2.printStackTrace();
        }
    }

    public static String getAuthToken() {
        return i.y;
    }

    public static String getPhoneInfo() {
        return i.c();
    }

    protected boolean checkNetwork() {
        NetworkInfo activeNetworkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) JNIInitializer.getCachedContext().getSystemService("connectivity");
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                return false;
            }
            return activeNetworkInfo.isAvailable();
        } catch (Exception e2) {
            if (Logger.debugEnable()) {
                e2.printStackTrace();
            } else {
                Logger.logW("HttpClient", e2.getMessage());
            }
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0152 A[Catch: Exception -> 0x0158, TryCatch #3 {Exception -> 0x0158, blocks: (B:14:0x0039, B:26:0x0079, B:27:0x007f, B:29:0x0083, B:78:0x0148, B:79:0x014e, B:81:0x0152, B:82:0x0157, B:70:0x0133, B:71:0x0139, B:73:0x013d, B:55:0x0103, B:57:0x0107), top: B:92:0x0039 }] */
    /* JADX WARN: Type inference failed for: r1v10, types: [int] */
    /* JADX WARN: Type inference failed for: r2v9, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r7v10, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v13, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v21 */
    /* JADX WARN: Type inference failed for: r7v23 */
    /* JADX WARN: Type inference failed for: r7v34 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void request(java.lang.String r7) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 375
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapapi.http.HttpClient.request(java.lang.String):void");
    }

    public void setMaxTimeOut(int i) {
        this.f2749d = i;
    }

    public void setReadTimeOut(int i) {
        this.f2750e = i;
    }
}