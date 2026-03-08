package com.ido.alexa.manager;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.api.Listener;
import com.amazon.identity.auth.device.api.authorization.AuthorizationManager;
import com.google.gson.Gson;
import com.ido.alexa.AlexaApp;
import com.ido.alexa.AlexaConstant;
import com.ido.alexa.bean.AvsException;
import com.ido.alexa.callbacks.AsyncCallback;
import com.ido.alexa.callbacks.AuthorizationCallback;
import com.ido.alexa.callbacks.IAlexaCallBack;
import com.ido.alexa.callbacks.ImplAsyncCallback;
import com.ido.alexa.data.AvsCapability;
import com.ido.alexa.data.Directive;
import com.ido.alexa.data.Event;
import com.ido.alexa.data.capability.AvsAlertsCapabilityBean;
import com.ido.alexa.data.capability.AvsCapabilityBaseBean;
import com.ido.alexa.data.capability.AvsSystemCapabilityBean;
import com.ido.alexa.log.AlexaLogUtil;
import com.ido.alexa.service.AlexaDownChannelService;
import com.ido.alexa.util.AsyncTaskUtil;
import com.ido.alexa.util.ClientUtil;
import com.ido.alexa.util.ComUtil;
import com.ido.alexa.util.Util;
import com.ido.life.constants.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaManager {
    private static final String TAG = "AlexaManager";
    private static AlexaManager sAlexaManager;
    private static String sToken;
    String lastAlexaUrl;
    private AmazonAuthorizeManager mAuthorizeManager;
    private String mDirectivesUrl;
    private String mPingUrl;
    private final Context mContext = AlexaApp.getAppContext();
    private String mAlexaUrl = SpManager.getAlexaEndpoint();

    private AlexaManager() {
        if (TextUtils.isEmpty(this.mAlexaUrl)) {
            String alexaLanguage = SpManager.getAlexaLanguage();
            switchAlexaUrl(alexaLanguage.length() == 0 ? AlexaApp.getDefaultLanguage() : alexaLanguage);
        } else {
            updateUrl();
        }
        this.mAuthorizeManager = new AmazonAuthorizeManager();
        if (Util.getPreferences(this.mContext).contains(Util.IDENTIFIER)) {
            return;
        }
        Util.getPreferences(this.mContext).edit().putString(Util.IDENTIFIER, AmazonAuthorizeManager.createCodeVerifier(30)).apply();
    }

    public static AlexaManager getInstance() {
        if (sAlexaManager == null) {
            sAlexaManager = new AlexaManager();
        }
        return sAlexaManager;
    }

    public void setToken(String str) {
        sToken = str;
        AlexaAudioEventManger.getInstance().setToken(str);
        AlexaAlarmEventManager.getInstance().setToken(str);
    }

    public String getToken() {
        return sToken;
    }

    public String getPingUrl() {
        return this.mPingUrl;
    }

    public String getDirectivesUrl() {
        return this.mDirectivesUrl;
    }

    public AmazonAuthorizeManager getAuthorizeManager() {
        if (this.mAuthorizeManager == null) {
            this.mAuthorizeManager = new AmazonAuthorizeManager();
        }
        return this.mAuthorizeManager;
    }

    public void authorize(String str, AuthorizationCallback authorizationCallback) {
        this.mAuthorizeManager.authorizeUser(str, authorizationCallback);
    }

    public void CBLAuthorize(String str, String str2, AuthorizationCallback authorizationCallback) {
        this.mAuthorizeManager.CBLAuthorize(str, str2, authorizationCallback);
    }

    public void createAlexaUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            str = AlexaConstant.REGION_NA;
        }
        this.mAlexaUrl = String.format(Locale.getDefault(), AlexaConstant.ALEXA_URL, str);
        updateUrl();
    }

    public void setEndPoint(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mAlexaUrl = str;
        updateUrl();
    }

    private void updateUrl() {
        this.mPingUrl = this.mAlexaUrl + AlexaConstant.ALEXA_VERSION + AlexaConstant.ALEXA_PING;
        this.mDirectivesUrl = this.mAlexaUrl + AlexaConstant.ALEXA_VERSION + AlexaConstant.ALEXA_DIRECTIVES;
        AlexaAudioEventManger.getInstance().setEventUrl(this.mAlexaUrl + AlexaConstant.ALEXA_VERSION + AlexaConstant.ALEXA_EVENTS);
        AlexaAlarmEventManager.getInstance().setAlarmEventUrl(this.mAlexaUrl);
        if (!TextUtils.isEmpty(this.lastAlexaUrl) && !TextUtils.equals(this.lastAlexaUrl, this.mAlexaUrl)) {
            Intent intent = new Intent(this.mContext, (Class<?>) AlexaDownChannelService.class);
            intent.putExtra(AlexaDownChannelService.RE_CREATE_DOWNCHANNEL, true);
            ComUtil.startService(this.mContext, intent);
        }
        String str = this.mAlexaUrl;
        this.lastAlexaUrl = str;
        SpManager.setAlexaEndpoint(str);
        AlexaLogUtil.printAndSave("mAlexaUrl=" + this.mAlexaUrl);
    }

    public void orderCapabilitiesRequest() {
        orderCapabilitiesRequest(50, 3);
    }

    public void orderCapabilitiesRequest(final int i, final int i2) {
        new AsyncTaskUtil(new AsyncTaskUtil.IAsyncTaskCallBack() { // from class: com.ido.alexa.manager.AlexaManager.1
            @Override // com.ido.alexa.util.AsyncTaskUtil.IAsyncTaskCallBack
            public void onPostExecute(Object obj) {
            }

            @Override // com.ido.alexa.util.AsyncTaskUtil.IAsyncTaskCallBack
            public Object doInBackground(String... strArr) {
                if (TextUtils.isEmpty(AlexaManager.sToken)) {
                    return null;
                }
                try {
                    AvsCapability avsCapability = new AvsCapability();
                    avsCapability.setEnvelopeVersion("20160207");
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(new AvsCapabilityBaseBean("AlexaInterface", "InteractionModel", "1.2"));
                    arrayList.add(new AvsCapabilityBaseBean("AlexaInterface", "Alexa", Constants.DIALDEFNED_VERSION_CONNECT));
                    arrayList.add(new AvsCapabilityBaseBean("AlexaInterface", "Alexa.ApiGateway", "1.0"));
                    arrayList.add(new AvsCapabilityBaseBean("AlexaInterface", "SpeechSynthesizer", "1.3"));
                    arrayList.add(new AvsCapabilityBaseBean("AlexaInterface", Directive.TYPE_AUDIOPLAYER, "1.4"));
                    arrayList.add(new AvsCapabilityBaseBean("AlexaInterface", "SpeechRecognizer", "2.3"));
                    arrayList.add(new AvsCapabilityBaseBean("AlexaInterface", "Notifications", "1.0"));
                    arrayList.add(new AvsCapabilityBaseBean("AlexaInterface", "Geolocation", "1.1"));
                    arrayList.add(new AvsCapabilityBaseBean("AlexaInterface", "TemplateRuntime", "1.0"));
                    AvsAlertsCapabilityBean.ConfigurationsBean configurationsBean = new AvsAlertsCapabilityBean.ConfigurationsBean();
                    configurationsBean.setMaximumAlerts(new AvsAlertsCapabilityBean.ConfigurationsBean.MaximumAlertsBean(i + i2, i, i2));
                    arrayList.add(new AvsAlertsCapabilityBean("AlexaInterface", "Alerts", "1.3", configurationsBean));
                    AvsSystemCapabilityBean.ConfigurationsBean configurationsBean2 = new AvsSystemCapabilityBean.ConfigurationsBean();
                    configurationsBean2.setLocales(Arrays.asList(AlexaConstant.LANGUAGE_DE_DE, AlexaConstant.LANGUAGE_EN_AU, AlexaConstant.LANGUAGE_EN_CA, AlexaConstant.LANGUAGE_EN_GB, AlexaConstant.LANGUAGE_EN_IN, AlexaConstant.LANGUAGE_EN_US, AlexaConstant.LANGUAGE_ES_ES, AlexaConstant.LANGUAGE_ES_MX, AlexaConstant.LANGUAGE_ES_US, AlexaConstant.LANGUAGE_FR_CA, AlexaConstant.LANGUAGE_FR_FR, AlexaConstant.LANGUAGE_IT_IT, AlexaConstant.LANGUAGE_JA_JP, AlexaConstant.LANGUAGE_PT_BR));
                    arrayList.add(new AvsSystemCapabilityBean("AlexaInterface", "System", "2.1", configurationsBean2));
                    avsCapability.setCapabilities(arrayList);
                    RequestBody requestBodyCreate = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(avsCapability));
                    HashMap map = new HashMap();
                    map.put("Content-Type", "application/json");
                    map.put("x-amz-access-token", AlexaManager.sToken);
                    map.put("Content-Length", String.valueOf(requestBodyCreate.contentLength()));
                    Request.Builder builderHeaders = new Request.Builder().headers(Headers.of(map));
                    builderHeaders.url("https://api.amazonalexa.com/v1/devices/@self/capabilities");
                    try {
                        Response responseExecute = ClientUtil.getTLS12OkHttpClient().newCall(builderHeaders.put(requestBodyCreate).build()).execute();
                        AlexaLogUtil.d(AlexaManager.TAG, "sendCapabilitiesRequest response.code()==" + responseExecute.code());
                        ResponseBody responseBodyBody = responseExecute.body();
                        if (responseBodyBody == null) {
                            return null;
                        }
                        AlexaLogUtil.d(AlexaManager.TAG, "body==" + responseBodyBody.string());
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                return null;
            }
        }).execute("");
    }

    public void switchLanguage(final String str, final AsyncCallback<String, AvsException> asyncCallback) {
        final ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        AlexaAudioEventManger.getInstance().sendEvent(Event.getSystemLocalesChangeEvent(arrayList), new ImplAsyncCallback<String, AvsException>() { // from class: com.ido.alexa.manager.AlexaManager.2
            @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
            public void success(String str2) {
                super.success(str2);
                SpManager.setAlexaLanguage(str);
                AlexaAudioEventManger.getInstance().sendEvent(Event.getSystemLocalesReportEvent(arrayList), null);
                AsyncCallback asyncCallback2 = asyncCallback;
                if (asyncCallback2 != null) {
                    asyncCallback2.success(null);
                }
            }

            @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
            public void failure(AvsException avsException) {
                super.failure(avsException);
                AsyncCallback asyncCallback2 = asyncCallback;
                if (asyncCallback2 != null) {
                    asyncCallback2.failure(avsException);
                }
            }
        });
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void switchAlexaUrl(java.lang.String r2) {
        /*
            r1 = this;
            int r0 = r2.hashCode()
            switch(r0) {
                case 96597976: goto L44;
                case 96598018: goto L3a;
                case 96598594: goto L30;
                case 96747306: goto L26;
                case 96747549: goto L1c;
                case 97640703: goto L12;
                case 100828572: goto L8;
                default: goto L7;
            }
        L7:
            goto L4e
        L8:
            java.lang.String r0 = "ja-JP"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L4e
            r2 = 1
            goto L4f
        L12:
            java.lang.String r0 = "fr-CA"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L4e
            r2 = 3
            goto L4f
        L1c:
            java.lang.String r0 = "es-US"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L4e
            r2 = 5
            goto L4f
        L26:
            java.lang.String r0 = "es-MX"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L4e
            r2 = 4
            goto L4f
        L30:
            java.lang.String r0 = "en-US"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L4e
            r2 = 6
            goto L4f
        L3a:
            java.lang.String r0 = "en-CA"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L4e
            r2 = 2
            goto L4f
        L44:
            java.lang.String r0 = "en-AU"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L4e
            r2 = 0
            goto L4f
        L4e:
            r2 = -1
        L4f:
            java.lang.String r0 = "AlexaManager"
            switch(r2) {
                case 0: goto L6c;
                case 1: goto L6c;
                case 2: goto L60;
                case 3: goto L60;
                case 4: goto L60;
                case 5: goto L60;
                case 6: goto L60;
                default: goto L54;
            }
        L54:
            java.lang.String r2 = "eu"
            r1.createAlexaUrl(r2)
            java.lang.String r2 = "switchAlexaUrl--eu-欧洲"
            com.ido.alexa.log.AlexaLogUtil.d(r0, r2)
            goto L77
        L60:
            java.lang.String r2 = "na"
            r1.createAlexaUrl(r2)
            java.lang.String r2 = "switchAlexaUrl--na-北美"
            com.ido.alexa.log.AlexaLogUtil.d(r0, r2)
            goto L77
        L6c:
            java.lang.String r2 = "fe"
            r1.createAlexaUrl(r2)
            java.lang.String r2 = "switchAlexaUrl--fe-亚洲"
            com.ido.alexa.log.AlexaLogUtil.d(r0, r2)
        L77:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.alexa.manager.AlexaManager.switchAlexaUrl(java.lang.String):void");
    }

    public void logout(final Context context, final IAlexaCallBack iAlexaCallBack) {
        try {
            AuthorizationManager.signOut(context, new Listener<Void, AuthError>() { // from class: com.ido.alexa.manager.AlexaManager.3
                @Override // com.amazon.identity.auth.device.api.Listener
                public void onSuccess(Void r2) {
                    AlexaLogUtil.printAndSave("signOut success");
                    TokenManager.clearToken(context);
                    AlexaManager.getInstance().setToken("");
                    IAlexaCallBack iAlexaCallBack2 = iAlexaCallBack;
                    if (iAlexaCallBack2 != null) {
                        iAlexaCallBack2.success("");
                    }
                }

                @Override // com.amazon.identity.auth.device.api.Listener
                public void onError(AuthError authError) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("signOut failed =");
                    sb.append(authError == null ? "" : authError.toString());
                    AlexaLogUtil.printAndSave(sb.toString());
                    IAlexaCallBack iAlexaCallBack2 = iAlexaCallBack;
                    if (iAlexaCallBack2 != null) {
                        iAlexaCallBack2.failure(new AvsException(authError != null ? authError.toString() : ""));
                    }
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
            if (iAlexaCallBack != null) {
                iAlexaCallBack.failure(new AvsException(e2.getMessage()));
            }
        }
    }
}