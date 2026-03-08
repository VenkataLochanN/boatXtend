package com.ido.ntf.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import com.ido.life.util.PhoneUtil;
import com.ido.ntf.log.LogHandle;

/* JADX INFO: loaded from: classes3.dex */
public class CommUtils {
    private static String FILE_NAME = "idoTest";
    private static final String TAG = " NotificationSDK  CommUtils";
    public static boolean isNotificationException;
    public static boolean isOpenExceptionListener;
    public static boolean isOpenPhoneStateListener;
    public static Context mContext;
    private String mPhonePkgName;
    private String mSmsPkgName;

    public static boolean isIsNotificationException() {
        return isNotificationException;
    }

    public static void setIsNotificationException(boolean z) {
        isNotificationException = z;
    }

    public static boolean isIsOpenPhoneStateListener() {
        return isOpenPhoneStateListener;
    }

    public static void setIsOpenPhoneStateListener(boolean z) {
        isOpenPhoneStateListener = z;
    }

    public static boolean isIsOpenExceptionListener() {
        return isOpenExceptionListener;
    }

    public static void setIsOpenExceptionListener(boolean z) {
        isOpenExceptionListener = z;
    }

    public static void put(Context context, String str, Object obj) {
        if (obj == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(FILE_NAME, 0).edit();
        editorEdit.putLong(str, ((Long) obj).longValue());
        editorEdit.apply();
    }

    public static Object get(Context context, String str, Object obj) {
        return Long.valueOf(context.getSharedPreferences(FILE_NAME, 0).getLong(str, ((Long) obj).longValue()));
    }

    public static String getContactNameFromPhoneBook(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION");
        if (ContextCompat.checkSelfPermission(context, "android.permission.READ_CONTACTS") != 0) {
            LogHandle.getInstance().printLog(" NotificationSDK  CommUtils 没有权限");
            return "";
        }
        Cursor cursorQuery = context.getContentResolver().query(Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(str)), new String[]{PhoneUtil.NAME, "number"}, null, null, null);
        if (cursorQuery == null || !cursorQuery.moveToFirst()) {
            return "";
        }
        String string = cursorQuery.getString(cursorQuery.getColumnIndex(PhoneUtil.NAME));
        cursorQuery.close();
        return string;
    }
}