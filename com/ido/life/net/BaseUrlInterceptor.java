package com.ido.life.net;

import android.os.Build;
import android.text.TextUtils;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.BuildConfig;
import com.ido.life.constants.Constants;
import com.ido.life.data.AuthorizationPreference;
import com.ido.life.database.model.UserInfo;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SPHelper;
import com.ido.life.util.SPUtils;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: loaded from: classes3.dex */
public class BaseUrlInterceptor implements Interceptor {
    private static final String TAG = "BaseUrlInterceptor";
    private static final String chinaCountryCode = "86";
    private static String[] countryLists = {Constants.USA_SAMOA_CODE, Constants.USA_VIRGIN_ISLANDS_CODE, Constants.USA_CODE};
    private static final String indiaCountryCode = "91";

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        return addPlatform(chain, chain.request());
    }

    private Response addPlatform(Interceptor.Chain chain, Request request) throws IOException {
        HttpUrl httpUrl;
        HttpUrl httpUrlUrl = request.url();
        Request.Builder builderNewBuilder = request.newBuilder();
        saveServerLog("addPlatform  oldHttpUrl: " + httpUrlUrl);
        List<String> listHeaders = request.headers(BaseUrl.URL_NAME);
        if (listHeaders.size() > 0) {
            String str = listHeaders.get(0);
            if (BaseUrl.URL_NAME_USER.equals(str)) {
                httpUrl = HttpUrl.parse(formatBasUrl(BaseUrl.URL_NAME_USER));
            } else if (BaseUrl.URL_NAME_HEALTH.equals(str)) {
                httpUrl = HttpUrl.parse(formatBasUrl(BaseUrl.URL_NAME_HEALTH));
            } else if ("device".equals(str)) {
                httpUrl = HttpUrl.parse(formatBasUrl("device"));
            } else {
                if (BaseUrl.URL_NAME_GOOGLE_MAP.equals("url_name:" + str)) {
                    httpUrl = HttpUrl.parse(BaseUrl.HOST_GOOGLE_MAP);
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("url_name:");
                    sb.append(str);
                    httpUrl = BaseUrl.URL_NAME_APP_LOG.equals(sb.toString()) ? HttpUrl.parse(formatBasUrl(BaseUrl.URL_NAME_APP_LOG)) : httpUrlUrl;
                }
            }
            saveServerLog("addPlatform  newBaseUrl: " + httpUrl);
            BLEDevice deviceInfo = getDeviceInfo();
            HttpUrl httpUrlBuild = httpUrlUrl.newBuilder().scheme(httpUrl.scheme()).host(httpUrl.host()).port(httpUrl.port()).addQueryParameter("os.type", String.valueOf(2)).addQueryParameter("os.version", Build.VERSION.RELEASE).addQueryParameter("d.name", String.valueOf(deviceInfo.mDeviceId)).addQueryParameter("d.version", String.valueOf(deviceInfo.version)).addQueryParameter("m.name", Build.MODEL).addQueryParameter("app.version", BuildConfig.VERSION_NAME).addQueryParameter("nid", AuthorizationPreference.getUUID(IdoApp.getAppContext())).build();
            saveServerLog("addPlatform  最终httpUrl: " + httpUrlBuild.toString());
            return chain.proceed(builderNewBuilder.url(httpUrlBuild).build());
        }
        return chain.proceed(request);
    }

    public static String getLocaleParams() {
        UserInfo userInfoQueryUserInfo;
        String currentCountryCode = SPHelper.getCurrentCountryCode();
        String lowerCase = "";
        if (TextUtils.isEmpty(currentCountryCode) && (userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(RunTimeUtil.getInstance().getUserId())) != null) {
            try {
                currentCountryCode = RunTimeUtil.getCountryCodeByCountryName(LanguageUtil.getLanguageText(ResourceUtil.getResources().getIdentifier("country_".concat(userInfoQueryUserInfo.getAreaCode().trim()), "string", IdoApp.getAppContext().getPackageName())));
                SPHelper.setCurrentCountryCode(currentCountryCode);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        try {
            Locale locale = Locale.getDefault();
            if (locale != null) {
                lowerCase = locale.getLanguage();
                if (TextUtils.isEmpty(currentCountryCode)) {
                    currentCountryCode = locale.getCountry();
                }
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        if (!TextUtils.isEmpty(currentCountryCode)) {
            currentCountryCode = currentCountryCode.trim().toUpperCase(Locale.CHINA);
        }
        if (!TextUtils.isEmpty(lowerCase)) {
            lowerCase = lowerCase.trim().toLowerCase(Locale.CHINA);
        }
        return lowerCase + "_" + currentCountryCode;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x007b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String formatBasUrl(java.lang.String r8) {
        /*
            Method dump skipped, instruction units count: 284
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.net.BaseUrlInterceptor.formatBasUrl(java.lang.String):java.lang.String");
    }

    private static void saveServerLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getServerLogPath(), TAG, str);
    }

    public static String getServiceBaseUrl(String str) {
        String str2 = (String) SPUtils.get(Constants.REQUEST_COUNTRY_CODE, "");
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        List listAsList = Arrays.asList(countryLists);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "getServiceBaseUrl --- mCountryCode =  " + str2);
        if (listAsList.contains(str2)) {
            return str.replace("://", "://us.");
        }
        if (str2.equals("91")) {
            return str.replace("://", "://in-");
        }
        return str2.equals("86") ? str.replace("://", "://cn-") : str;
    }

    public static Map<String, String> getPublicParams() {
        BLEDevice deviceInfo = getDeviceInfo();
        String uuid = AuthorizationPreference.getUUID(IdoApp.getAppContext());
        HashMap map = new HashMap();
        map.put("os.type", String.valueOf(2));
        map.put("os.version", Build.VERSION.RELEASE);
        map.put("d.name", String.valueOf(deviceInfo.mDeviceId));
        map.put("d.version", String.valueOf(deviceInfo.version));
        map.put("m.name", Build.MODEL);
        map.put("app.version", BuildConfig.VERSION_NAME);
        if (!TextUtils.isEmpty(uuid)) {
            map.put("nid", uuid);
        }
        map.put("locale", getLocaleParams());
        return map;
    }

    public static BLEDevice getDeviceInfo() {
        try {
            BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
            if (currentDeviceInfo == null) {
                currentDeviceInfo = new BLEDevice();
            }
            BasicInfo basicInfo = LocalDataManager.getBasicInfo();
            if (basicInfo != null) {
                currentDeviceInfo.mDeviceId = basicInfo.deivceId;
                currentDeviceInfo.version = basicInfo.firmwareVersion;
            }
            return currentDeviceInfo;
        } catch (Exception e2) {
            e2.printStackTrace();
            return new BLEDevice();
        }
    }
}