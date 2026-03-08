package com.amazon.identity.auth.device.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.device.dataobject.Scope;
import com.amazon.identity.auth.device.datastore.DatabaseHelper;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.ido.alexa.AlexaCustomSkillConstant;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import kotlin.UByte;

/* JADX INFO: loaded from: classes.dex */
public final class MAPUtils {
    private static final String AMAZON_HOST = ".amazon.";
    private static final String HOST_TYPE = "host.type";
    private static final String LOG_TAG = MAPUtils.class.getName();
    private static SQLiteDatabase MAPdatabase = null;
    private static final String PATH_AP = "/ap/";
    private static final String PATH_AP_FORGOT_PASSWORD = "/ap/forgotpassword";
    private static final String PATH_GP = "/gp/yourstore/home";
    private static final String PROTOCOL = "http";

    private enum SCOPE_MODIFIER {
        LOCAL,
        REMOTE,
        ALL
    }

    public static String getDeviceId() {
        return "some-device-id";
    }

    private MAPUtils() throws Exception {
        throw new Exception("This class is not instantiable!");
    }

    public static synchronized SQLiteDatabase getMAPdatabase(Context context) {
        if (MAPdatabase == null) {
            try {
                MAPdatabase = new DatabaseHelper(context).getWritableDatabase();
            } catch (SQLiteException unused) {
                deleteDatabase(context);
                MAPdatabase = new DatabaseHelper(context).getWritableDatabase();
            }
        }
        return MAPdatabase;
    }

    public static SQLiteDatabase deleteDatabase(Context context) {
        try {
            MAPLog.d(LOG_TAG, "deleteDatabase so we can create it from scratch");
            boolean zDeleteDatabase = context.deleteDatabase(DatabaseHelper.MAP_DB_NAME);
            MAPLog.d(LOG_TAG, "deleteDatabase was successful : " + zDeleteDatabase);
        } catch (SQLiteException e2) {
            MAPLog.d(LOG_TAG, "deleteDatabase exception: " + e2.getMessage());
        }
        return MAPdatabase;
    }

    public static void resetDatabaseInstance() {
        MAPdatabase = null;
    }

    public static String toDelimitedString(String[] strArr, String str) {
        if (strArr == null || strArr.length <= 0) {
            return null;
        }
        int i = 0;
        String string = "";
        while (i < strArr.length) {
            StringBuilder sb = new StringBuilder();
            sb.append(string);
            sb.append(strArr[i].trim());
            sb.append(i == strArr.length + (-1) ? "" : str);
            string = sb.toString();
            i++;
        }
        return string;
    }

    public static String[] toStringArray(String str, String str2) {
        if (str == null || str.trim().length() <= 0) {
            return null;
        }
        return str.trim().split("[" + str2 + "]");
    }

    public static Set<String> getSetDifference(String[] strArr, String[] strArr2) {
        if (strArr == null) {
            return new HashSet();
        }
        if (strArr2 == null) {
            return new HashSet(Arrays.asList(strArr));
        }
        HashSet hashSet = new HashSet(Arrays.asList(strArr));
        hashSet.removeAll(Arrays.asList(strArr2));
        return hashSet;
    }

    public static boolean contains(String[] strArr, String str) {
        if (strArr == null) {
            return false;
        }
        for (String str2 : strArr) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean areScopesRemoteAndValid(AppInfo appInfo, String[] strArr) {
        return areScopesValid(appInfo, strArr, SCOPE_MODIFIER.REMOTE);
    }

    public static boolean areScopesLocalAndValid(AppInfo appInfo, String[] strArr) {
        return areScopesValid(appInfo, strArr, SCOPE_MODIFIER.LOCAL);
    }

    public static boolean areScopesValid(AppInfo appInfo, String[] strArr) {
        return areScopesValid(appInfo, strArr, SCOPE_MODIFIER.ALL);
    }

    private static boolean areScopesValid(AppInfo appInfo, String[] strArr, SCOPE_MODIFIER scope_modifier) {
        MAPLog.i(LOG_TAG, "areScopesValid : modifier=" + scope_modifier.name() + " scopes=" + Arrays.toString(strArr));
        if (strArr == null || strArr.length == 0) {
            MAPLog.w(LOG_TAG, "Scopes are invalid: array is either null or empty");
            return false;
        }
        if (appInfo == null || appInfo.getAllowedScopes() == null) {
            MAPLog.w(LOG_TAG, "Scopes are invalid: either appInfo is null or allowedScopes is null");
            return false;
        }
        HashSet hashSet = new HashSet(Arrays.asList(appInfo.getAllowedScopes()));
        MAPLog.i(LOG_TAG, "allowedScopeSet : " + hashSet);
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            String str = strArr[i];
            if (str == null || !hashSet.contains(str)) {
                MAPLog.w(LOG_TAG, "Invalid scope: " + str + " (it's either null or not part of the allowed set)");
                return false;
            }
            if (scope_modifier == SCOPE_MODIFIER.REMOTE && Scope.isLocal(str)) {
                MAPLog.w(LOG_TAG, "Invalid scope: " + str + " is local!");
                return false;
            }
            if (scope_modifier == SCOPE_MODIFIER.LOCAL && !Scope.isLocal(str)) {
                MAPLog.w(LOG_TAG, "Invalid scope: " + str + " is remote!");
            }
        }
        return true;
    }

    public static String toHexString(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & UByte.MAX_VALUE);
            if (hexString.length() == 1) {
                stringBuffer.append(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString();
    }

    public static boolean isMAPUrl(String str) {
        if (str == null) {
            MAPLog.i(LOG_TAG, "URL is null");
            return false;
        }
        try {
            URL url = new URL(str);
            String protocol = url.getProtocol();
            if (!TextUtils.isEmpty(protocol) && protocol.contains(PROTOCOL)) {
                String host = url.getHost();
                if (!TextUtils.isEmpty(host) && host.contains(AMAZON_HOST)) {
                    String path = url.getPath();
                    boolean zIsEmpty = TextUtils.isEmpty(path);
                    boolean zStartsWith = path.startsWith(PATH_AP);
                    boolean zEquals = path.equals(PATH_GP);
                    boolean zEquals2 = path.equals(PATH_AP_FORGOT_PASSWORD);
                    MAPLog.d(LOG_TAG, " isEmpty=" + zIsEmpty + "startsWithAP=" + zStartsWith + "equalsGP=" + zEquals + "equalsForgotPassword=" + zEquals2);
                    if (!zIsEmpty && ((zStartsWith && !zEquals2) || zEquals)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (MalformedURLException unused) {
            MAPLog.pii(LOG_TAG, "MalformedURLException", " url=" + str);
            return false;
        }
    }

    public static boolean isDevo(Context context) {
        if (context == null) {
            return false;
        }
        return getHostType(context, context.getPackageName()).equalsIgnoreCase("development");
    }

    public static String getHostType(Context context, String str) {
        String string = "www";
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 128);
            if (applicationInfo.metaData == null || !applicationInfo.metaData.containsKey(HOST_TYPE)) {
                return "www";
            }
            string = applicationInfo.metaData.getString(HOST_TYPE);
            MAPLog.pii(LOG_TAG, "Host Type", "hostType=" + string + " package=" + str);
            return string;
        } catch (PackageManager.NameNotFoundException unused) {
            MAPLog.d(LOG_TAG, "No host type found in package " + str);
            return string;
        }
    }

    public static String getVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e2) {
            MAPLog.w(LOG_TAG, "Unable to get verison info from app" + e2.getMessage());
            return "N/A";
        }
    }

    public static String getAppName(Context context) {
        ApplicationInfo applicationInfo;
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        try {
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException unused) {
            applicationInfo = null;
        }
        return (String) (applicationInfo != null ? packageManager.getApplicationLabel(applicationInfo) : context.getPackageName());
    }
}