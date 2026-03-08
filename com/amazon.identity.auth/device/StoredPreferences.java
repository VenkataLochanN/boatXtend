package com.amazon.identity.auth.device;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.identity.auth.device.api.authorization.Region;

/* JADX INFO: loaded from: classes.dex */
public final class StoredPreferences {
    private static final String KEY_REGION_MODE = "com.amazon.lwa.regionMode";
    private static final String KEY_SANDBOX_MODE = "com.amazon.lwa.sandboxMode";
    private static final String KEY_TOKEN_FROM_SSO = "com.amazon.lwa.isTokenObtainedFromSSO";
    private static final String LWA_SHARED_PREFS = "com.amazon.lwa.LWASharedPreferences";

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(LWA_SHARED_PREFS, 0);
    }

    public static void setTokenObtainedFromSSO(Context context, boolean z) {
        getPreferences(context).edit().putBoolean(KEY_TOKEN_FROM_SSO, z).commit();
    }

    public static boolean isTokenObtainedFromSSO(Context context) {
        return getPreferences(context).getBoolean(KEY_TOKEN_FROM_SSO, false);
    }

    public static void setSandboxMode(Context context, boolean z) {
        getPreferences(context).edit().putBoolean(KEY_SANDBOX_MODE, z).commit();
    }

    public static boolean isSandboxMode(Context context) {
        return getPreferences(context).getBoolean(KEY_SANDBOX_MODE, false);
    }

    public static void setRegion(Context context, Region region) {
        getPreferences(context).edit().putString(KEY_REGION_MODE, region.toString()).commit();
    }

    public static Region getRegion(Context context) {
        return Region.valueOf(getPreferences(context).getString(KEY_REGION_MODE, Region.AUTO.toString()));
    }
}