package com.baidu.lbsapi.auth;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.bumptech.glide.load.Key;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import net.sqlcipher.database.SQLiteDatabase;

/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f1999a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f2000b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private HashMap<String, String> f2001c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f2002d = null;

    public g(Context context) {
        this.f1999a = context;
    }

    private String a(Context context) {
        NetworkInfo activeNetworkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isAvailable()) {
                String extraInfo = activeNetworkInfo.getExtraInfo();
                return (extraInfo == null || !(extraInfo.trim().toLowerCase().equals("cmwap") || extraInfo.trim().toLowerCase().equals("uniwap") || extraInfo.trim().toLowerCase().equals("3gwap") || extraInfo.trim().toLowerCase().equals("ctwap"))) ? "wifi" : extraInfo.trim().toLowerCase().equals("ctwap") ? "ctwap" : "cmwap";
            }
            return null;
        } catch (Exception e2) {
            if (a.f1989a) {
                e2.printStackTrace();
            }
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x0167 A[Catch: all -> 0x012e, TryCatch #9 {all -> 0x012e, blocks: (B:7:0x002f, B:89:0x0133, B:91:0x0137, B:92:0x013a, B:102:0x0163, B:104:0x0167, B:105:0x016a, B:115:0x0192, B:117:0x0196, B:118:0x0199), top: B:148:0x002f }] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0196 A[Catch: all -> 0x012e, TryCatch #9 {all -> 0x012e, blocks: (B:7:0x002f, B:89:0x0133, B:91:0x0137, B:92:0x013a, B:102:0x0163, B:104:0x0167, B:105:0x016a, B:115:0x0192, B:117:0x0196, B:118:0x0199), top: B:148:0x002f }] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x01c3 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01b6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0185 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:151:0x00f8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0157 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00b3 A[Catch: all -> 0x0107, TryCatch #4 {all -> 0x0107, blocks: (B:45:0x00af, B:47:0x00b3, B:48:0x00ce), top: B:146:0x00af }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00f3 A[Catch: Exception -> 0x0118, IOException -> 0x011a, MalformedURLException -> 0x011c, all -> 0x011e, TRY_LEAVE, TryCatch #24 {all -> 0x011e, blocks: (B:8:0x0033, B:64:0x010c, B:66:0x0114, B:67:0x0117, B:51:0x00eb, B:53:0x00f3, B:29:0x0090, B:31:0x0098), top: B:157:0x0033 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0102 A[PHI: r6 r10 r14
  0x0102: PHI (r6v1 boolean) = (r6v0 boolean), (r6v0 boolean), (r6v0 boolean), (r6v3 boolean) binds: [B:98:0x015e, B:111:0x018c, B:124:0x01bd, B:59:0x0100] A[DONT_GENERATE, DONT_INLINE]
  0x0102: PHI (r10v6 int) = (r10v3 int), (r10v4 int), (r10v5 int), (r10v22 int) binds: [B:98:0x015e, B:111:0x018c, B:124:0x01bd, B:59:0x0100] A[DONT_GENERATE, DONT_INLINE]
  0x0102: PHI (r14v21 'e' java.io.IOException) = 
  (r14v9 'e' java.io.IOException)
  (r14v14 'e' java.io.IOException)
  (r14v19 'e' java.io.IOException)
  (r14v43 'e' java.io.IOException)
 binds: [B:98:0x015e, B:111:0x018c, B:124:0x01bd, B:59:0x0100] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x010a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0114 A[Catch: Exception -> 0x0118, IOException -> 0x011a, MalformedURLException -> 0x011c, all -> 0x011e, TryCatch #24 {all -> 0x011e, blocks: (B:8:0x0033, B:64:0x010c, B:66:0x0114, B:67:0x0117, B:51:0x00eb, B:53:0x00f3, B:29:0x0090, B:31:0x0098), top: B:157:0x0033 }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0137 A[Catch: all -> 0x012e, TryCatch #9 {all -> 0x012e, blocks: (B:7:0x002f, B:89:0x0133, B:91:0x0137, B:92:0x013a, B:102:0x0163, B:104:0x0167, B:105:0x016a, B:115:0x0192, B:117:0x0196, B:118:0x0199), top: B:148:0x002f }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(javax.net.ssl.HttpsURLConnection r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 551
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.lbsapi.auth.g.a(javax.net.ssl.HttpsURLConnection):void");
    }

    private static String b(HashMap<String, String> map) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (z) {
                z = false;
            } else {
                sb.append("&");
            }
            sb.append(URLEncoder.encode(entry.getKey(), Key.STRING_CHARSET_NAME));
            sb.append("=");
            sb.append(URLEncoder.encode(entry.getValue(), Key.STRING_CHARSET_NAME));
        }
        return sb.toString();
    }

    private HttpsURLConnection b() {
        String str;
        try {
            URL url = new URL(this.f2000b);
            a.a("https URL: " + this.f2000b);
            String strA = a(this.f1999a);
            if (strA != null && !strA.equals("")) {
                a.a("checkNetwork = " + strA);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) (strA.equals("cmwap") ? url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.172", 80))) : strA.equals("ctwap") ? url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.200", 80))) : url.openConnection());
                httpsURLConnection.setHostnameVerifier(new h(this));
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setConnectTimeout(SQLiteDatabase.SQLITE_MAX_LIKE_PATTERN_LENGTH);
                httpsURLConnection.setReadTimeout(SQLiteDatabase.SQLITE_MAX_LIKE_PATTERN_LENGTH);
                return httpsURLConnection;
            }
            a.c("Current network is not available.");
            this.f2002d = ErrorMessage.a(-10, "Current network is not available.");
            return null;
        } catch (MalformedURLException e2) {
            if (a.f1989a) {
                e2.printStackTrace();
                a.a(e2.getMessage());
            }
            str = "Auth server could not be parsed as a URL.";
            this.f2002d = ErrorMessage.a(-11, str);
            return null;
        } catch (Exception e3) {
            if (a.f1989a) {
                e3.printStackTrace();
                a.a(e3.getMessage());
            }
            str = "Init httpsurlconnection failed.";
            this.f2002d = ErrorMessage.a(-11, str);
            return null;
        }
    }

    private HashMap<String, String> c(HashMap<String, String> map) {
        HashMap<String, String> map2 = new HashMap<>();
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String string = it.next().toString();
            map2.put(string, map.get(string));
        }
        return map2;
    }

    protected String a(HashMap<String, String> map) throws Throwable {
        this.f2001c = c(map);
        this.f2000b = this.f2001c.get("url");
        HttpsURLConnection httpsURLConnectionB = b();
        if (httpsURLConnectionB == null) {
            a.c("syncConnect failed,httpsURLConnection is null");
        } else {
            a(httpsURLConnectionB);
        }
        return this.f2002d;
    }

    protected boolean a() {
        NetworkInfo activeNetworkInfo;
        a.a("checkNetwork start");
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.f1999a.getSystemService("connectivity");
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                return false;
            }
            if (!activeNetworkInfo.isAvailable()) {
                return false;
            }
            a.a("checkNetwork end");
            return true;
        } catch (Exception e2) {
            if (a.f1989a) {
                e2.printStackTrace();
            }
            return false;
        }
    }
}