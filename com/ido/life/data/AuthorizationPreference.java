package com.ido.life.data;

import android.content.Context;
import android.text.TextUtils;
import com.ido.common.IdoApp;
import com.ido.common.base.BasePreference;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;

/* JADX INFO: loaded from: classes2.dex */
public class AuthorizationPreference extends BasePreference {
    private static final String IS_NEW_USER = "isNewUser";
    private static final String NAME = "author_preference";
    private static final String TAG = "AuthorizationPreference";
    private static final String TAG_TOKEN = "token";
    private static final String TAG_USER_UUID = "user_uuid";

    public static void setToken(Context context, String str) {
        saveString(context, NAME, "token", str);
    }

    public static void setUuid(Context context, String str) {
        saveString(context, NAME, TAG_USER_UUID, str);
    }

    public static String getUUID(Context context) {
        return getString(context, NAME, TAG_USER_UUID, "");
    }

    public static String getToken(Context context) {
        return getString(context, NAME, "token", "");
    }

    public static void setIsNewUser(Context context, boolean z) {
        saveBoolean(context, NAME, IS_NEW_USER, z);
    }

    public static boolean getIsNewUser(Context context) {
        return getBoolean(context, NAME, IS_NEW_USER, false);
    }

    public static boolean isUserSignIn(Context context) {
        if (context == null) {
            return false;
        }
        return !TextUtils.isEmpty(getToken(context));
    }

    public static void clear() {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "清空用户的缓存数据name：author_preference");
        clear(IdoApp.getAppContext(), NAME);
    }
}