package com.amazon.identity.auth.device.authorization.api;

import android.content.Context;
import com.amazon.identity.auth.device.appid.ThirdPartyAppIdentifier;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.map.device.utils.MAPLog;

/* JADX INFO: loaded from: classes.dex */
public final class AppIdentifierHelper {
    private static final String LOG_TAG = AppIdentifierHelper.class.getName();

    private AppIdentifierHelper() {
    }

    public static boolean isAPIKeyValid(String str, Context context) {
        MAPLog.d(LOG_TAG, "isAPIKeyValid for " + str);
        return new ThirdPartyAppIdentifier().isAPIKeyValid(str, context);
    }

    public static AppInfo getAppInfo(String str, Context context) {
        MAPLog.d(LOG_TAG, "getAppInfo for " + str);
        return new ThirdPartyAppIdentifier().getAppInfo(str, context);
    }
}