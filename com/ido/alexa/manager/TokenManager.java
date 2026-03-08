package com.ido.alexa.manager;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.amazon.identity.auth.device.api.authorization.AuthorizeResult;
import com.google.gson.Gson;
import com.ido.alexa.AlexaApp;
import com.ido.alexa.bean.AvsException;
import com.ido.alexa.callbacks.TokenCallback;
import com.ido.alexa.callbacks.TokenResponseCallback;
import com.ido.alexa.data.TokenResponse;
import com.ido.alexa.data.UserCodeResponse;
import com.ido.alexa.log.AlexaLogUtil;
import com.ido.alexa.service.AlexaDownChannelService;
import com.ido.alexa.util.ClientUtil;
import com.ido.alexa.util.ComUtil;
import com.ido.alexa.util.Util;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: loaded from: classes2.dex */
public class TokenManager {
    private static final String ARG_CLIENT_ID = "client_id";
    private static final String ARG_CODE = "code";
    private static final String ARG_CODE_VERIFIER = "code_verifier";
    private static final String ARG_GRANT_TYPE = "grant_type";
    private static final String ARG_REDIRECT_URI = "redirect_uri";
    private static final String ARG_REFRESH_TOKEN = "refresh_token";
    private static final String TAG = "Alexa ";
    private static Timer timer;
    private static TimerTask timerTask;

    public static void getAccessToken(final Context context, String str, AuthorizeResult authorizeResult, final TokenResponseCallback tokenResponseCallback) {
        FormBody.Builder builderAdd = new FormBody.Builder().add(ARG_GRANT_TYPE, "authorization_code").add("code", authorizeResult.getAuthorizationCode());
        builderAdd.add(ARG_REDIRECT_URI, authorizeResult.getRedirectURI());
        builderAdd.add("client_id", authorizeResult.getClientId());
        builderAdd.add(ARG_CODE_VERIFIER, str);
        OkHttpClient tLS12OkHttpClient = ClientUtil.getTLS12OkHttpClient();
        Request requestBuild = new Request.Builder().url("https://api.amazon.com/auth/O2/token").post(builderAdd.build()).build();
        final Handler handler = new Handler(Looper.getMainLooper());
        tLS12OkHttpClient.newCall(requestBuild).enqueue(new Callback() { // from class: com.ido.alexa.manager.TokenManager.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, final IOException iOException) {
                iOException.printStackTrace();
                if (tokenResponseCallback != null) {
                    handler.post(new Runnable() { // from class: com.ido.alexa.manager.TokenManager.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            tokenResponseCallback.onFailure(iOException);
                        }
                    });
                }
                AlexaLogUtil.printAndSave("Alexa authorize  getAccessToken failed " + iOException.toString());
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws Exception {
                if (response.code() == 200 && response.body() != null) {
                    String strString = response.body().string();
                    AlexaLogUtil.printAndSave(strString);
                    final TokenResponse tokenResponse = (TokenResponse) new Gson().fromJson(strString, TokenResponse.class);
                    TokenManager.saveTokens(context, tokenResponse);
                    AlexaLogUtil.printAndSave("Alexa authorize  reStartDownChannel");
                    TokenManager.reCreateDownChannel(context);
                    if (tokenResponseCallback != null) {
                        handler.post(new Runnable() { // from class: com.ido.alexa.manager.TokenManager.1.2
                            @Override // java.lang.Runnable
                            public void run() {
                                tokenResponseCallback.onSuccess(tokenResponse);
                            }
                        });
                        return;
                    }
                    return;
                }
                AlexaLogUtil.printAndSave("Alexa authorize  getAccessToken failed " + response.toString());
                if (tokenResponseCallback != null) {
                    handler.post(new Runnable() { // from class: com.ido.alexa.manager.TokenManager.1.3
                        @Override // java.lang.Runnable
                        public void run() {
                            tokenResponseCallback.onFailure(new Exception("getAccessToken failed"));
                        }
                    });
                }
            }
        });
    }

    public static void getAccessToken(Context context, TokenCallback tokenCallback) {
        if (TextUtils.isEmpty(SpManager.getProductId())) {
            SpManager.setAccessToken("");
        }
        String accessToken = SpManager.getAccessToken();
        if (!TextUtils.isEmpty(accessToken)) {
            long tokenExpires = SpManager.getTokenExpires() - System.currentTimeMillis();
            if (tokenExpires > 0) {
                AlexaManager.getInstance().setToken(accessToken);
                tokenCallback.onSuccess(accessToken);
                AlexaLogUtil.printAndSave("Alexa token未过期");
                if (timer == null) {
                    autoRefreshToken(context);
                }
                if (tokenExpires > 300000) {
                    return;
                } else {
                    AlexaLogUtil.printAndSave("Alexa 提前刷新token");
                }
            }
            String refreshToken = SpManager.getRefreshToken();
            if (!TextUtils.isEmpty(refreshToken)) {
                AlexaLogUtil.printAndSave("Alexa token已过期，refresh token");
                getRefreshToken(context, tokenCallback, refreshToken);
                return;
            }
        }
        AlexaLogUtil.printAndSave("Alexa User is not logged in and no refresh token found. productID=" + SpManager.getProductId());
        tokenCallback.onFailure(new IllegalStateException("User is not logged in and no refresh token found."));
    }

    public static void clearToken(Context context) {
        String endpointId = SpManager.getEndpointId();
        String macAdress = SpManager.getMacAdress();
        String bleName = SpManager.getBleName();
        Util.getPreferences(context.getApplicationContext()).edit().clear().apply();
        SpManager.setEndpointId(endpointId);
        SpManager.setMacAdress(macAdress);
        SpManager.setBleName(bleName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void getRefreshToken(final Context context, final TokenCallback tokenCallback, String str) {
        final Handler handler = new Handler(Looper.getMainLooper());
        if (TextUtils.isEmpty(str)) {
            if (tokenCallback != null) {
                handler.post(new Runnable() { // from class: com.ido.alexa.manager.TokenManager.2
                    @Override // java.lang.Runnable
                    public void run() {
                        tokenCallback.onFailure(new AvsException("refreshToken is empty"));
                    }
                });
            }
        } else {
            FormBody.Builder builderAdd = new FormBody.Builder().add(ARG_GRANT_TYPE, "refresh_token").add("refresh_token", str);
            builderAdd.add("client_id", SpManager.getClientId());
            ClientUtil.getTLS12OkHttpClient().newCall(new Request.Builder().url("https://api.amazon.com/auth/O2/token").post(builderAdd.build()).build()).enqueue(new Callback() { // from class: com.ido.alexa.manager.TokenManager.3
                @Override // okhttp3.Callback
                public void onFailure(Call call, final IOException iOException) {
                    iOException.printStackTrace();
                    if (tokenCallback != null) {
                        handler.post(new Runnable() { // from class: com.ido.alexa.manager.TokenManager.3.1
                            @Override // java.lang.Runnable
                            public void run() {
                                tokenCallback.onFailure(iOException);
                            }
                        });
                    }
                    AlexaLogUtil.printAndSave("Alexa refreshToken failed " + iOException.getMessage());
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, Response response) throws Exception {
                    if (response.code() == 200 && response.body() != null) {
                        String strString = response.body().string();
                        AlexaLogUtil.printAndSave("Alexa refreshToken success " + strString);
                        final TokenResponse tokenResponse = (TokenResponse) new Gson().fromJson(strString, TokenResponse.class);
                        TokenManager.saveTokens(context, tokenResponse);
                        AlexaLogUtil.printAndSave("Alexa refreshToken  reStartDownChannel");
                        TokenManager.reCreateDownChannel(context);
                        if (tokenCallback != null) {
                            handler.post(new Runnable() { // from class: com.ido.alexa.manager.TokenManager.3.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    tokenCallback.onSuccess(tokenResponse.access_token);
                                }
                            });
                            return;
                        }
                        return;
                    }
                    AlexaLogUtil.printAndSave("Alexa refreshToken failed " + response.toString());
                    if (tokenCallback != null) {
                        handler.post(new Runnable() { // from class: com.ido.alexa.manager.TokenManager.3.3
                            @Override // java.lang.Runnable
                            public void run() {
                                tokenCallback.onFailure(new Throwable("refreshToken failed"));
                            }
                        });
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void reCreateDownChannel(Context context) {
        Intent intent = new Intent(context, (Class<?>) AlexaDownChannelService.class);
        intent.putExtra(AlexaDownChannelService.RE_CREATE_DOWNCHANNEL, true);
        ComUtil.startService(context, intent);
    }

    private static void autoRefreshToken(final Context context) {
        try {
            if (timer != null) {
                timer.cancel();
                timer.purge();
                timer = null;
            }
            if (timerTask != null) {
                timerTask.cancel();
                timerTask = null;
            }
            timerTask = new TimerTask() { // from class: com.ido.alexa.manager.TokenManager.4
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    AlexaLogUtil.printAndSave("Alexa autoRefreshToken");
                    TokenManager.getRefreshToken(context, null, SpManager.getRefreshToken());
                }
            };
            long tokenExpires = (SpManager.getTokenExpires() - System.currentTimeMillis()) - 300000;
            AlexaLogUtil.printAndSave("Alexa delay=" + tokenExpires);
            timer = new Timer();
            if (tokenExpires > 0) {
                timer.schedule(timerTask, tokenExpires);
            } else {
                timer.schedule(timerTask, 0L);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void saveTokens(Context context, TokenResponse tokenResponse) {
        String str = tokenResponse.refresh_token;
        String str2 = tokenResponse.access_token;
        SpManager.setAccessToken(str2);
        SpManager.setRefreshToken(str);
        SpManager.setTokenExpires(System.currentTimeMillis() + (tokenResponse.expires_in * 1000));
        AlexaLogUtil.printAndSave("Alexa PREF_TOKEN_EXPIRES=" + tokenResponse.expires_in + "  PREF_REFRESH_TOKEN=" + str);
        AlexaManager.getInstance().setToken(str2);
        autoRefreshToken(context);
    }

    public static void requestDeviceTokens(final UserCodeResponse userCodeResponse, final TokenCallback tokenCallback) {
        ClientUtil.getTLS12OkHttpClient().newCall(new Request.Builder().url("https://api.amazon.com/auth/O2/token").post(new FormBody.Builder().add(ARG_GRANT_TYPE, "device_code").add("device_code", userCodeResponse.getDevice_code()).add("user_code", userCodeResponse.getUser_code()).build()).build()).enqueue(new Callback() { // from class: com.ido.alexa.manager.TokenManager.5
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                iOException.printStackTrace();
                AlexaLogUtil.printAndSave("Alexa requestDeviceTokens failed " + iOException.toString());
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws Exception {
                if (response.code() == 200 && response.body() != null) {
                    SpManager.setClientId(userCodeResponse.getClientId());
                    SpManager.setProductId(userCodeResponse.getProductId());
                    SpManager.setDeviceSerialNumber(userCodeResponse.getDeviceSerialNumber());
                    String strString = response.body().string();
                    AlexaLogUtil.printAndSave(strString);
                    TokenResponse tokenResponse = (TokenResponse) new Gson().fromJson(strString, TokenResponse.class);
                    TokenManager.saveTokens(AlexaApp.getAppContext(), tokenResponse);
                    AlexaLogUtil.printAndSave("Alexa requestDeviceTokens  reStartDownChannel");
                    TokenManager.reCreateDownChannel(AlexaApp.getAppContext());
                    TokenCallback tokenCallback2 = tokenCallback;
                    if (tokenCallback2 != null) {
                        tokenCallback2.onSuccess(tokenResponse.access_token);
                        return;
                    }
                    return;
                }
                AlexaLogUtil.printAndSave("Alexa requestDeviceTokens  getAccessToken failed " + response.toString());
                TokenCallback tokenCallback3 = tokenCallback;
                if (tokenCallback3 != null) {
                    tokenCallback3.onFailure(new Exception("requestDeviceTokens failed"));
                }
            }
        });
    }
}