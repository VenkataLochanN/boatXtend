package com.amazon.identity.auth.device.endpoint;

import android.text.TextUtils;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.bumptech.glide.load.Key;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

/* JADX INFO: loaded from: classes.dex */
public class HttpResponse {
    private static final String LOG_TAG = HttpResponse.class.getSimpleName();
    private final String responseBody;
    private final int responseCode;
    private final Map<String, String> responseHeaders;

    public static HttpResponse readResponse(HttpsURLConnection httpsURLConnection) throws IOException {
        try {
            return new HttpResponse(httpsURLConnection.getResponseCode(), retrieveResponseBody(httpsURLConnection), retrieveResponseHeaders(httpsURLConnection));
        } finally {
            httpsURLConnection.disconnect();
        }
    }

    private HttpResponse(int i, String str, Map<String, String> map) {
        this.responseCode = i;
        this.responseBody = str;
        this.responseHeaders = map;
    }

    private static String retrieveResponseBody(HttpsURLConnection httpsURLConnection) throws Throwable {
        InputStream errorStream;
        try {
            errorStream = httpsURLConnection.getInputStream();
        } catch (IOException unused) {
            errorStream = httpsURLConnection.getErrorStream();
        }
        if (errorStream == null) {
            return null;
        }
        String inputStreamToString = readInputStreamToString(errorStream);
        MAPLog.pii(LOG_TAG, "Response received", String.format("Request to %s received response %s", httpsURLConnection.getURL().toString(), inputStreamToString));
        return inputStreamToString;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.lang.String] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x0055 -> B:34:0x005a). Please report as a decompilation issue!!! */
    private static String readInputStreamToString(InputStream inputStream) throws Throwable {
        String line;
        Object obj;
        String str = "Cannot close BufferedReader";
        ?? r2 = 0;
        BufferedReader bufferedReader = null;
        r2 = 0;
        if (inputStream == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try {
            try {
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader((InputStream) inputStream, Key.STRING_CHARSET_NAME));
                    while (true) {
                        try {
                            line = bufferedReader2.readLine();
                            if (line != null) {
                                sb.append(line);
                            } else {
                                try {
                                    break;
                                } catch (IOException e2) {
                                    MAPLog.e(LOG_TAG, "Cannot close BufferedReader", e2);
                                    obj = e2;
                                }
                            }
                        } catch (IOException e3) {
                            e = e3;
                            bufferedReader = bufferedReader2;
                            MAPLog.e(LOG_TAG, "Cannot parse response stream", e);
                            Object obj2 = bufferedReader;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                    obj2 = bufferedReader;
                                } catch (IOException e4) {
                                    MAPLog.e(LOG_TAG, "Cannot close BufferedReader", e4);
                                    obj2 = e4;
                                }
                            }
                            inputStream.close();
                            r2 = obj2;
                        } catch (Throwable th) {
                            th = th;
                            r2 = bufferedReader2;
                            if (r2 != 0) {
                                try {
                                    r2.close();
                                } catch (IOException e5) {
                                    MAPLog.e(LOG_TAG, str, e5);
                                }
                            }
                            try {
                                inputStream.close();
                                throw th;
                            } catch (IOException e6) {
                                MAPLog.e(LOG_TAG, "Cannot close response stream", e6);
                                throw th;
                            }
                        }
                    }
                    bufferedReader2.close();
                    obj = line;
                    inputStream.close();
                    r2 = obj;
                } catch (IOException e7) {
                    e = e7;
                }
            } catch (IOException e8) {
                str = LOG_TAG;
                MAPLog.e(str, "Cannot close response stream", e8);
                r2 = r2;
            }
            inputStream = sb.toString();
            return inputStream;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static Map<String, String> retrieveResponseHeaders(HttpURLConnection httpURLConnection) {
        HashMap map = new HashMap();
        for (Map.Entry<String, List<String>> entry : httpURLConnection.getHeaderFields().entrySet()) {
            String strJoin = TextUtils.join(", ", entry.getValue());
            map.put(entry.getKey(), strJoin);
            MAPLog.pii(LOG_TAG, "Header from response: name=" + entry.getKey(), "val=" + strJoin);
        }
        return map;
    }

    public String getRedirectLocation() {
        return this.responseHeaders.get("Location");
    }

    public int getResponseCode() {
        return this.responseCode;
    }

    public String getResponseBody() {
        return this.responseBody;
    }

    public Map<String, String> getResponseHeaders() {
        return this.responseHeaders;
    }
}