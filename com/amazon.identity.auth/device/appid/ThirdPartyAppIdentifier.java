package com.amazon.identity.auth.device.appid;

import android.content.Context;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.map.device.utils.MAPLog;

/* JADX INFO: loaded from: classes.dex */
public final class ThirdPartyAppIdentifier extends AbstractAppIdentifier {
    private static final String LOG_TAG = ThirdPartyAppIdentifier.class.getName();
    private static final String REDIRECT_URI_PREFIX = "amzn://";

    @Override // com.amazon.identity.auth.device.appid.AbstractAppIdentifier, com.amazon.identity.auth.device.appid.AppIdentifier
    public String getPackageName(String str, Context context) {
        return null;
    }

    @Override // com.amazon.identity.auth.device.appid.AbstractAppIdentifier, com.amazon.identity.auth.device.appid.AppIdentifier
    public String getPackageNameByVariant(String str, Context context) {
        return null;
    }

    @Override // com.amazon.identity.auth.device.appid.AbstractAppIdentifier, com.amazon.identity.auth.device.appid.AppIdentifier
    public String[] getPackageNames(String str, Context context) {
        return null;
    }

    @Override // com.amazon.identity.auth.device.appid.AbstractAppIdentifier, com.amazon.identity.auth.device.appid.AppIdentifier
    public String getAppFamilyId(String str, Context context) {
        MAPLog.i(LOG_TAG, "getAppFamilyId : packageName=" + str);
        if (str == null) {
            MAPLog.w(LOG_TAG, "packageName can't be null!");
            return null;
        }
        AppInfo appInfo = getAppInfo(str, context);
        if (appInfo == null) {
            return null;
        }
        return appInfo.getAppFamilyId();
    }

    @Override // com.amazon.identity.auth.device.appid.AbstractAppIdentifier, com.amazon.identity.auth.device.appid.AppIdentifier
    public String getAppVariantId(String str, Context context) {
        MAPLog.i(LOG_TAG, "getAppVariantId : packageName=" + str);
        if (str == null) {
            MAPLog.w(LOG_TAG, "packageName can't be null!");
            return null;
        }
        AppInfo appInfo = getAppInfo(str, context);
        if (appInfo == null) {
            return null;
        }
        return appInfo.getAppVariantId();
    }

    @Override // com.amazon.identity.auth.device.appid.AppIdentifier
    public String[] getAllowedScopes(String str, Context context) {
        MAPLog.i(LOG_TAG, "getAllowedScopes : packageName=" + str);
        if (str == null) {
            MAPLog.w(LOG_TAG, "packageName can't be null!");
            return null;
        }
        AppInfo appInfo = getAppInfo(str, context);
        if (appInfo == null) {
            return null;
        }
        return appInfo.getAllowedScopes();
    }

    @Override // com.amazon.identity.auth.device.appid.AppIdentifier
    public String[] getAppPermissions(String str, Context context) {
        MAPLog.i(LOG_TAG, "getAppPermissions : packageName=" + str);
        if (str == null) {
            MAPLog.w(LOG_TAG, "packageName can't be null!");
            return null;
        }
        AppInfo appInfo = getAppInfo(str, context);
        if (appInfo == null) {
            return null;
        }
        return appInfo.getGrantedPermissions();
    }

    public AppInfo getAppInfoByApiKey(String str, String str2, Context context) {
        MAPLog.i(LOG_TAG, "getAppInfo : packageName=" + str);
        if (str == null) {
            MAPLog.w(LOG_TAG, "packageName can't be null!");
            return null;
        }
        return APIKeyDecoder.doDecode(str, str2, false, context);
    }

    public boolean isAPIKeyValidFormat(String str, String str2, Context context) {
        MAPLog.pii(LOG_TAG, "isAPIKeyValid : packageName=" + str, "apiKey=" + str2);
        if (str == null) {
            MAPLog.w(LOG_TAG, "packageName can't be null!");
            return false;
        }
        if (str2 != null) {
            return APIKeyDecoder.doDecode(str, str2, false, context) != null;
        }
        MAPLog.w(LOG_TAG, "apiKey can't be null!");
        return false;
    }

    public String getRedirectUrl(Context context) {
        return REDIRECT_URI_PREFIX + context.getPackageName();
    }
}