package com.amazon.identity.auth.device.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class ThirdPartyResourceParser {
    private static final String API_KEY_FILE = "api_key.txt";
    public static final String KEY_API_KEY = "APIKey";
    public static final String KEY_API_KEY_OLD = "AmazonAPIKey";
    private static final String LOG_TAG = ThirdPartyResourceParser.class.getName();
    public static final String UTF8_BYTE_ORDER_MARK = "\ufeff";
    private static final String UTF_8 = "UTF-8";
    private final String _apiKey = parseApiKey();
    private final Context _context;
    private final String _packageName;

    protected String getApiKeyFile() {
        return API_KEY_FILE;
    }

    public ThirdPartyResourceParser(Context context, String str) {
        this._packageName = str;
        this._context = context;
    }

    private String parseApiKey() throws Throwable {
        InputStream inputStreamOpen;
        Context context = this._context;
        try {
            if (context != null) {
                try {
                    inputStreamOpen = context.getPackageManager().getResourcesForApplication(getPackageName()).getAssets().open(getApiKeyFile());
                } catch (Throwable th) {
                    th = th;
                    inputStreamOpen = null;
                }
                try {
                    MAPLog.i(LOG_TAG, "Attempting to parse API Key from assets directory");
                    String string = readString(inputStreamOpen);
                    if (inputStreamOpen != null) {
                        inputStreamOpen.close();
                    }
                    return string;
                } catch (Throwable th2) {
                    th = th2;
                    if (inputStreamOpen != null) {
                        inputStreamOpen.close();
                    }
                    throw th;
                }
            }
        } catch (PackageManager.NameNotFoundException e2) {
            MAPLog.i(LOG_TAG, "Unable to get api key asset document: " + e2.getMessage());
        } catch (IOException e3) {
            MAPLog.i(LOG_TAG, "Unable to get api key asset document: " + e3.getMessage());
        }
        return null;
    }

    protected String getPackageName() {
        return this._packageName;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00d9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00b7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0098 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00f8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:79:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static java.lang.String readString(java.io.InputStream r9) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 278
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.utils.ThirdPartyResourceParser.readString(java.io.InputStream):java.lang.String");
    }

    public String getApiKey() {
        if (!isApiKeyInAssest()) {
            MAPLog.w(LOG_TAG, "Unable to get API Key from Assests");
            String stringValueFromMetaData = getStringValueFromMetaData(KEY_API_KEY);
            return stringValueFromMetaData != null ? stringValueFromMetaData : getStringValueFromMetaData(KEY_API_KEY_OLD);
        }
        return this._apiKey;
    }

    public boolean isApiKeyInAssest() {
        return this._apiKey != null;
    }

    private String getStringValueFromMetaData(String str) {
        if (this._context == null) {
            return null;
        }
        MAPLog.i(LOG_TAG, "Attempting to parse API Key from meta data in Android manifest");
        try {
            ApplicationInfo applicationInfo = this._context.getPackageManager().getApplicationInfo(this._packageName, 128);
            if (applicationInfo.metaData != null) {
                return applicationInfo.metaData.getString(str);
            }
            return null;
        } catch (PackageManager.NameNotFoundException e2) {
            MAPLog.w(LOG_TAG, "(key=" + str + ") " + e2.getMessage());
            return null;
        }
    }
}