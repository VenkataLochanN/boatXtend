package com.amazon.identity.auth.device.endpoint;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.authorization.EndpointDomainBuilder;
import com.amazon.identity.auth.device.authorization.Service;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.device.endpoint.Response;
import com.amazon.identity.auth.device.utils.MAPUtils;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.bumptech.glide.load.Key;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import javax.net.ssl.HttpsURLConnection;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractPandaRequest<T extends Response> extends AbstractHTTPSRequest<T> {
    private static final String AMZN_REQUEST_ID_HEADER = "X-Amzn-RequestId";
    protected static final String ANDROID_OS_NAME = "Android";
    protected static final String APP_NAME = "app_name";
    protected static final String APP_VERSION = "app_version";
    protected static final String DI_HW_NAME = "di.hw.name";
    protected static final String DI_HW_VERSION = "di.hw.version";
    protected static final String DI_OS_NAME = "di.os.name";
    protected static final String DI_OS_VERSION = "di.os.version";
    protected static final String DI_SDK_VERSION = "di.sdk.version";
    private static final String HTTP_USER_AGENT = "User-Agent";
    private static final int MAX_NUM_POST_PARAMS = 10;
    private AppInfo appInfo;
    private String appName;
    private String appVersion;
    private Context context;
    private String libVersion = "3.0.4";
    protected final List<Pair<String, String>> postParameters = new ArrayList(10);
    private static final String LOG_TAG = AbstractPandaRequest.class.getName();
    private static final String DEFAULT_USER_AGENT = "LWAAndroidSDK/3.0.4/Android/" + Build.VERSION.RELEASE + "/" + Build.MODEL;

    protected abstract String getEndPoint();

    protected abstract List<Pair<String, String>> getExtraHeaders();

    protected abstract List<Pair<String, String>> getExtraParameters() throws AuthError;

    protected boolean isSandboxEnabled() {
        return false;
    }

    public AbstractPandaRequest(Context context, AppInfo appInfo) {
        this.context = context;
        this.appInfo = appInfo;
        this.appName = MAPUtils.getAppName(context);
        this.appVersion = MAPUtils.getVersion(context);
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractHTTPSRequest
    protected String getRequestUrl() throws MalformedURLException {
        String endPoint = getEndPoint();
        return new URL(new EndpointDomainBuilder(this.context, this.appInfo).forService(Service.PANDA).forSandbox(isSandboxEnabled()).getDomain() + endPoint).toString();
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractHTTPSRequest
    protected void initializePostParams() throws AuthError {
        addExtraParameters();
        addAppInfoParameters();
        addDeviceParams();
    }

    private void addDeviceParams() {
        if (!TextUtils.isEmpty(Build.MANUFACTURER) && !Build.MANUFACTURER.equals("unknown")) {
            this.postParameters.add(new Pair<>(DI_HW_NAME, Build.MANUFACTURER));
        }
        if (!TextUtils.isEmpty(Build.MODEL) && !Build.MODEL.equals("unknown")) {
            this.postParameters.add(new Pair<>(DI_HW_VERSION, Build.MODEL));
        }
        this.postParameters.add(new Pair<>(DI_OS_NAME, ANDROID_OS_NAME));
        if (!TextUtils.isEmpty(Build.VERSION.RELEASE) && !Build.VERSION.RELEASE.equals("unknown")) {
            this.postParameters.add(new Pair<>(DI_OS_VERSION, Build.VERSION.RELEASE));
        }
        this.postParameters.add(new Pair<>(DI_SDK_VERSION, this.libVersion));
    }

    private void addAppInfoParameters() {
        this.postParameters.add(new Pair<>("app_name", this.appName));
        String str = this.appVersion;
        if (str != null) {
            this.postParameters.add(new Pair<>(APP_VERSION, str));
        }
    }

    private void addExtraParameters() throws AuthError {
        List<Pair<String, String>> extraParameters = getExtraParameters();
        if (extraParameters != null) {
            this.postParameters.addAll(extraParameters);
        }
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractHTTPSRequest
    protected void setHttpMethod(HttpsURLConnection httpsURLConnection) throws ProtocolException {
        httpsURLConnection.setRequestMethod("POST");
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractHTTPSRequest
    protected void initializeHeaders() {
        addDefaultHeaders();
        addExtraHeaders();
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractHTTPSRequest
    protected void writeBody(HttpsURLConnection httpsURLConnection) throws AuthError, IOException {
        httpsURLConnection.setDoOutput(true);
        String strPrepareRequestBody = prepareRequestBody();
        if (TextUtils.isEmpty(strPrepareRequestBody)) {
            return;
        }
        byte[] bytes = strPrepareRequestBody.getBytes(Key.STRING_CHARSET_NAME);
        httpsURLConnection.setFixedLengthStreamingMode(bytes.length);
        OutputStream outputStream = httpsURLConnection.getOutputStream();
        try {
            outputStream.write(bytes);
            try {
                outputStream.flush();
            } catch (IOException e2) {
                MAPLog.e(LOG_TAG, "Couldn't flush write body stream", e2);
            }
            try {
                outputStream.close();
            } catch (IOException e3) {
                MAPLog.e(LOG_TAG, "Couldn't close write body stream", e3);
            }
        } finally {
        }
    }

    protected String prepareRequestBody() throws AuthError, UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Pair<String, String> pair : getPostParameters()) {
            if (!TextUtils.isEmpty((CharSequence) pair.first) && !TextUtils.isEmpty((CharSequence) pair.second)) {
                if (z) {
                    z = false;
                } else {
                    sb.append("&");
                }
                sb.append(URLEncoder.encode((String) pair.first, Key.STRING_CHARSET_NAME));
                sb.append("=");
                sb.append(URLEncoder.encode((String) pair.second, Key.STRING_CHARSET_NAME));
            }
        }
        String string = sb.toString();
        MAPLog.pii(LOG_TAG, "Request body", string);
        return string;
    }

    private void addDefaultHeaders() {
        this.headers.add(new Pair<>(HTTP_USER_AGENT, DEFAULT_USER_AGENT));
        this.headers.add(new Pair<>("Accept-Language", getDeviceLanguage()));
        this.headers.add(new Pair<>("Accept", "application/json"));
        this.headers.add(new Pair<>("Accept-Charset", Key.STRING_CHARSET_NAME));
        this.headers.add(new Pair<>(AMZN_REQUEST_ID_HEADER, UUID.randomUUID().toString()));
    }

    private String getDeviceLanguage() {
        String str = Locale.getDefault().getLanguage() + "-" + Locale.getDefault().getCountry();
        MAPLog.i(LOG_TAG, "Device Language is: " + str);
        return str;
    }

    private void addExtraHeaders() {
        List<Pair<String, String>> extraHeaders = getExtraHeaders();
        if (extraHeaders != null) {
            this.headers.addAll(extraHeaders);
        }
    }

    private List<Pair<String, String>> getPostParameters() {
        for (Pair<String, String> pair : this.postParameters) {
            if (pair != null) {
                MAPLog.pii(LOG_TAG, "Parameter Added to request", "name=" + ((String) pair.first) + " val=" + ((String) pair.second));
            } else {
                MAPLog.e(LOG_TAG, "Parameter Added to request was NULL");
            }
        }
        return this.postParameters;
    }
}