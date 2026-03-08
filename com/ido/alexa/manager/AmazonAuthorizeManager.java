package com.ido.alexa.manager;

import android.content.Context;
import android.util.Base64;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.api.authorization.AuthCancellation;
import com.amazon.identity.auth.device.api.authorization.AuthorizationManager1;
import com.amazon.identity.auth.device.api.authorization.AuthorizeListener;
import com.amazon.identity.auth.device.api.authorization.AuthorizeRequest;
import com.amazon.identity.auth.device.api.authorization.AuthorizeResult;
import com.amazon.identity.auth.device.api.authorization.ScopeFactory;
import com.amazon.identity.auth.device.api.workflow.RequestContext;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.amazon.identity.auth.map.device.AccountManagerConstants;
import com.google.gson.Gson;
import com.ido.alexa.AlexaApi;
import com.ido.alexa.AlexaApp;
import com.ido.alexa.callbacks.AsyncCallback;
import com.ido.alexa.callbacks.AuthorizationCallback;
import com.ido.alexa.callbacks.TokenCallback;
import com.ido.alexa.callbacks.TokenResponseCallback;
import com.ido.alexa.data.Event;
import com.ido.alexa.data.TokenResponse;
import com.ido.alexa.data.UserCodeResponse;
import com.ido.alexa.log.AlexaLogUtil;
import com.ido.alexa.util.ClientUtil;
import com.ido.alexa.util.Util;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class AmazonAuthorizeManager {
    private static final String CODE_CHALLENGE_METHOD = "S256";
    private static final String CODE_VERIFIER = "code_verifier";
    private static final String KEY_DEVICE_SERIALNUMBER = "deviceSerialNumber";
    private static final String KEY_PRODUCTID = "productID";
    private static final String KEY_PRODUCT_INSTANCEA_TTRIBUTES = "productInstanceAttributes";
    private static final String KEY_SCOPE_1 = "alexa:voice_service:pre_auth";
    private static final String KEY_SCOPE_2 = "alexa:all";
    private static final String TAG = "AuthorizationHandler";
    private AuthorizationCallback mCallback;
    private String mClientId;
    private String mDeviceSerialNumber;
    private String mProductId;
    private RequestContext mRequestContext;
    private final AuthorizeListener authListener = new AuthorizeListener() { // from class: com.ido.alexa.manager.AmazonAuthorizeManager.3
        @Override // com.amazon.identity.auth.device.api.authorization.AuthorizeListener, com.amazon.identity.auth.device.interactive.InteractiveListener, com.amazon.identity.auth.device.api.CancellableListener, com.amazon.identity.auth.device.api.Listener
        public void onSuccess(AuthorizeResult authorizeResult) {
            if (authorizeResult == null) {
                if (AmazonAuthorizeManager.this.mCallback != null) {
                    AmazonAuthorizeManager.this.mCallback.onError(new Exception("AuthorizeResult is Empty"));
                    return;
                }
                return;
            }
            AlexaLogUtil.printAndSave("Alexa auth get Authorization code success,start get getAccessToken " + authorizeResult.getAuthorizationCode() + " " + authorizeResult.getClientId());
            AlexaLogUtil.i(AmazonAuthorizeManager.TAG, "Authorization successful AuthorizeResult =" + authorizeResult.getAuthorizationCode() + "  " + authorizeResult.getRedirectURI() + "  " + authorizeResult.getAccessToken() + "  " + authorizeResult.getClientId());
            SpManager.setClientId(authorizeResult.getClientId());
            SpManager.setProductId(AmazonAuthorizeManager.this.mProductId);
            SpManager.setDeviceSerialNumber(AmazonAuthorizeManager.this.mDeviceSerialNumber);
            TokenManager.getAccessToken(AmazonAuthorizeManager.this.mContext, AmazonAuthorizeManager.this.getCodeVerifier(), authorizeResult, new TokenResponseCallback() { // from class: com.ido.alexa.manager.AmazonAuthorizeManager.3.1
                @Override // com.ido.alexa.callbacks.TokenResponseCallback
                public void onSuccess(TokenResponse tokenResponse) {
                    AlexaApi.setTimeZone(TimeZone.getDefault().getID());
                    AlexaManager.getInstance().orderCapabilitiesRequest();
                    AlexaAudioEventManger.getInstance().syncGateWay();
                    if (SpManager.getFirmwareVersion() != 0) {
                        AlexaAudioEventManger.getInstance().sendEvent(Event.getSoftwareInfoEvent(SpManager.getFirmwareVersion()), null);
                    }
                    if (AmazonAuthorizeManager.this.mCallback != null) {
                        AmazonAuthorizeManager.this.mCallback.onSuccess(null);
                    }
                }

                @Override // com.ido.alexa.callbacks.TokenResponseCallback
                public void onFailure(Exception exc) {
                    if (AmazonAuthorizeManager.this.mCallback != null) {
                        AmazonAuthorizeManager.this.mCallback.onError(exc);
                    }
                }
            });
        }

        @Override // com.amazon.identity.auth.device.api.authorization.AuthorizeListener, com.amazon.identity.auth.device.interactive.InteractiveListener, com.amazon.identity.auth.device.api.CancellableListener, com.amazon.identity.auth.device.api.Listener
        public void onError(AuthError authError) {
            AlexaLogUtil.printAndSave("Alexa auth AuthError during authorization:" + authError.getMessage());
            if (AmazonAuthorizeManager.this.mCallback != null) {
                AmazonAuthorizeManager.this.mCallback.onError(new Exception(authError.getMessage()));
            }
        }

        @Override // com.amazon.identity.auth.device.api.authorization.AuthorizeListener, com.amazon.identity.auth.device.interactive.InteractiveListener, com.amazon.identity.auth.device.api.CancellableListener
        public void onCancel(AuthCancellation authCancellation) {
            AlexaLogUtil.printAndSave("Alexa auth AuthonCancel" + authCancellation.getDescription());
            if (AmazonAuthorizeManager.this.mCallback != null) {
                AmazonAuthorizeManager.this.mCallback.onCancel();
            }
        }
    };
    private Context mContext = AlexaApp.getAppContext();

    AmazonAuthorizeManager() {
        try {
            this.mRequestContext = RequestContext.create(this.mContext);
        } catch (IllegalArgumentException unused) {
            AlexaLogUtil.e(TAG, "Unable to Use Amazon Authorization Manager. APIKey is incorrect or does not exist. Does assets/api_key.txt exist in the main application?");
        }
    }

    public void checkLoggedIn(Context context, final AsyncCallback<Boolean, Throwable> asyncCallback) {
        TokenManager.getAccessToken(context, new TokenCallback() { // from class: com.ido.alexa.manager.AmazonAuthorizeManager.1
            @Override // com.ido.alexa.callbacks.TokenCallback
            public void onSuccess(String str) {
                asyncCallback.success(true);
            }

            @Override // com.ido.alexa.callbacks.TokenCallback
            public void onFailure(Throwable th) {
                asyncCallback.success(false);
                asyncCallback.failure(th);
            }
        });
    }

    void authorizeUser(String str, AuthorizationCallback authorizationCallback) {
        this.mCallback = authorizationCallback;
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        this.mProductId = str;
        this.mDeviceSerialNumber = SpManager.getMacAdress();
        try {
            jSONObject2.put("deviceSerialNumber", this.mDeviceSerialNumber);
            jSONObject.put(KEY_PRODUCT_INSTANCEA_TTRIBUTES, jSONObject2);
            jSONObject.put(KEY_PRODUCTID, str);
            AlexaLogUtil.d("Alexa authorizeUser scopeData=" + jSONObject.toString());
            AuthorizationManager1.authorize(new AuthorizeRequest.Builder(this.mRequestContext).addScopes(ScopeFactory.scopeNamed(KEY_SCOPE_1), ScopeFactory.scopeNamed(KEY_SCOPE_2, jSONObject)).forGrantType(AuthorizeRequest.GrantType.AUTHORIZATION_CODE).withProofKeyParameters(getCodeChallenge(), CODE_CHALLENGE_METHOD).build());
            this.mRequestContext.registerListener(this.authListener);
        } catch (JSONException e2) {
            AuthorizationCallback authorizationCallback2 = this.mCallback;
            if (authorizationCallback2 != null) {
                authorizationCallback2.onError(new Exception("JSONException:" + e2.getMessage()));
            }
        }
    }

    void CBLAuthorize(String str, String str2, final AuthorizationCallback<UserCodeResponse> authorizationCallback) {
        JSONObject jSONObject = new JSONObject();
        this.mProductId = str;
        this.mClientId = str2;
        this.mDeviceSerialNumber = SpManager.getMacAdress();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("deviceSerialNumber", this.mDeviceSerialNumber);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(KEY_PRODUCTID, this.mProductId);
            jSONObject3.put(KEY_PRODUCT_INSTANCEA_TTRIBUTES, jSONObject2);
            jSONObject.put(KEY_SCOPE_2, jSONObject3);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("response_type", "device_code");
        builder.add(AccountManagerConstants.CLIENT_ID_LABEL, this.mClientId);
        builder.add(AuthorizationResponseParser.SCOPE, KEY_SCOPE_2);
        builder.add("scope_data", jSONObject.toString());
        String string = Locale.getDefault().toString();
        AlexaLogUtil.printAndSave("Alexa CBLAuthorize Locale.getDefault().toString()=" + string + " ,scope_data=" + jSONObject.toString());
        ClientUtil.getTLS12OkHttpClient().newCall(new Request.Builder().url("https://api.amazon.com/auth/O2/create/codepair").header("Accept-Language", string.replace("_", "-")).post(builder.build()).build()).enqueue(new Callback() { // from class: com.ido.alexa.manager.AmazonAuthorizeManager.2
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                iOException.printStackTrace();
                AlexaLogUtil.printAndSave("Alexa CBLAuthorize failed " + iOException.toString());
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws Exception {
                if (response.code() == 200 && response.body() != null) {
                    String strString = response.body().string();
                    AlexaLogUtil.printAndSave("Alexa CBLAuthorize=" + strString);
                    UserCodeResponse userCodeResponse = (UserCodeResponse) new Gson().fromJson(strString, UserCodeResponse.class);
                    if (authorizationCallback != null) {
                        userCodeResponse.setClientId(AmazonAuthorizeManager.this.mClientId);
                        userCodeResponse.setDeviceSerialNumber(AmazonAuthorizeManager.this.mDeviceSerialNumber);
                        userCodeResponse.setProductId(AmazonAuthorizeManager.this.mProductId);
                        authorizationCallback.onSuccess(userCodeResponse);
                        return;
                    }
                    return;
                }
                AlexaLogUtil.printAndSave("Alexa CBLAuthorize failed= " + response.toString());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getCodeVerifier() {
        if (Util.getPreferences(this.mContext).contains(CODE_VERIFIER)) {
            return Util.getPreferences(this.mContext).getString(CODE_VERIFIER, "");
        }
        String strCreateCodeVerifier = createCodeVerifier();
        Util.getPreferences(this.mContext).edit().putString(CODE_VERIFIER, strCreateCodeVerifier).apply();
        return strCreateCodeVerifier;
    }

    private String getCodeChallenge() {
        return base64UrlEncode(getHash(getCodeVerifier()));
    }

    private static String createCodeVerifier() {
        return createCodeVerifier(128);
    }

    static String createCodeVerifier(int i) {
        char[] charArray = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(charArray[random.nextInt(charArray.length)]);
        }
        return sb.toString();
    }

    private static String base64UrlEncode(byte[] bArr) {
        return Base64.encodeToString(bArr, 0).split("=")[0].replace('+', '-').replace(IOUtils.DIR_SEPARATOR_UNIX, '_');
    }

    private static byte[] getHash(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.reset();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
        }
        return messageDigest.digest(str.getBytes());
    }
}