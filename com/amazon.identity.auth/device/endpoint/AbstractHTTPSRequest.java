package com.amazon.identity.auth.device.endpoint;

import android.net.SSLCertificateSocketFactory;
import android.text.TextUtils;
import android.util.Pair;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.endpoint.Response;
import com.amazon.identity.auth.device.utils.DefaultLibraryInfo;
import com.amazon.identity.auth.device.utils.NetworkUtils;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractHTTPSRequest<T extends Response> {
    public static final int HTTPS_TIMEOUT_MILLISECONDS = 30000;
    public static final int NUM_RETRY_ATTEMPTS = 3;
    protected static final String UTF8 = "UTF-8";
    protected final List<Pair<String, String>> headers = new ArrayList();
    private static final String LOG_TAG = AbstractHTTPSRequest.class.getName();
    private static final HostnameVerifier TRUST_ALL_HOSTNAME_VERIFIER = getAllTrustingHostNameVerifier();
    private static final SSLSocketFactory TRUST_ALL_HOSTS_SOCKET_FACTORY = getAllTrustingSSLSocketFactory();

    protected abstract T generateResponse(HttpResponse httpResponse);

    protected abstract String getRequestUrl() throws MalformedURLException;

    protected abstract void initializeHeaders();

    protected void initializePostParams() throws AuthError {
    }

    protected abstract void logRequest();

    protected abstract void setHttpMethod(HttpsURLConnection httpsURLConnection) throws ProtocolException;

    protected void writeBody(HttpsURLConnection httpsURLConnection) throws AuthError, IOException {
    }

    List<Pair<String, String>> getHeaders() {
        return this.headers;
    }

    public final T submit() throws AuthError {
        try {
            initializeHeaders();
            initializePostParams();
            HttpsURLConnection httpsURLConnectionInitializeHttp = initializeHttp(getRequestUrl());
            logRequestInfo(httpsURLConnectionInitializeHttp);
            writeBody(httpsURLConnectionInitializeHttp);
            MAPLog.i(LOG_TAG, "Request url: " + httpsURLConnectionInitializeHttp.getURL());
            return (T) retryAndGetResponse(httpsURLConnectionInitializeHttp);
        } catch (IllegalStateException e2) {
            MAPLog.e(LOG_TAG, "Received IllegalStateException error when executing token request:" + e2.toString(), e2);
            throw new AuthError("Received communication error when executing token request", e2, AuthError.ERROR_TYPE.ERROR_COM);
        } catch (MalformedURLException e3) {
            MAPLog.e(LOG_TAG, "Invalid URL", e3);
            throw new AuthError("MalformedURLException", e3, AuthError.ERROR_TYPE.ERROR_BAD_PARAM);
        } catch (IOException e4) {
            MAPLog.e(LOG_TAG, "Received IO error when executing token request:" + e4.toString(), e4);
            throw new AuthError("Received communication error when executing token request", e4, AuthError.ERROR_TYPE.ERROR_IO);
        }
    }

    protected T retryAndGetResponse(HttpsURLConnection httpsURLConnection) throws AuthError, IOException {
        HttpResponse response = null;
        int i = 0;
        while (i < 3) {
            response = HttpResponse.readResponse(httpsURLConnection);
            MAPLog.pii(LOG_TAG, "Get response.", "Response code: " + response.getResponseCode());
            if (!NetworkUtils.hasReceived500(response.getResponseCode())) {
                break;
            }
            httpsURLConnection = initializeHttp(getRequestUrl());
            writeBody(httpsURLConnection);
            String str = LOG_TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Received ");
            sb.append(response.getResponseCode());
            sb.append(" error on request attempt ");
            i++;
            sb.append(i);
            sb.append(" of ");
            sb.append(3);
            MAPLog.w(str, sb.toString());
        }
        return (T) generateResponse(response);
    }

    private void logRequestInfo(HttpsURLConnection httpsURLConnection) {
        MAPLog.pii(LOG_TAG, "Http request method", httpsURLConnection.getRequestMethod());
        Map requestProperties = httpsURLConnection.getRequestProperties();
        if (requestProperties != null) {
            MAPLog.i(LOG_TAG, "Number of Headers : " + requestProperties.size());
            for (Map.Entry entry : requestProperties.entrySet()) {
                String str = (String) entry.getKey();
                List list = (List) entry.getValue();
                if (list != null && list.size() > 0) {
                    MAPLog.pii(LOG_TAG, "Header used for request: name=" + str, "val=" + TextUtils.join(", ", list));
                }
            }
        } else {
            MAPLog.i(LOG_TAG, "No Headers");
        }
        logRequest();
    }

    private static HostnameVerifier getAllTrustingHostNameVerifier() {
        return new AllowAllHostnameVerifier();
    }

    private static SSLSocketFactory getAllTrustingSSLSocketFactory() {
        return SSLCertificateSocketFactory.getInsecure(0, null);
    }

    protected HttpsURLConnection initializeHttp(String str) throws AuthError, IOException {
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection();
        if (DefaultLibraryInfo.isDevo()) {
            MAPLog.d(LOG_TAG, "Trusting all ssl connetions.");
            httpsURLConnection.setSSLSocketFactory(TRUST_ALL_HOSTS_SOCKET_FACTORY);
            httpsURLConnection.setHostnameVerifier(TRUST_ALL_HOSTNAME_VERIFIER);
        }
        setHttpMethod(httpsURLConnection);
        httpsURLConnection.setConnectTimeout(30000);
        writeHeaders(httpsURLConnection);
        return httpsURLConnection;
    }

    protected void writeHeaders(HttpsURLConnection httpsURLConnection) {
        for (Pair<String, String> pair : this.headers) {
            httpsURLConnection.setRequestProperty((String) pair.first, (String) pair.second);
        }
    }
}