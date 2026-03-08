package com.ido.life.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import com.ido.ble.event.stat.one.d;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.PermissionUtil;
import com.ido.life.constants.Constants;

/* JADX INFO: loaded from: classes3.dex */
public class SMSPhoneUtil {
    public static int getMissSmsCount(Context context) {
        Cursor cursorQuery = context.getContentResolver().query(Uri.parse("content://sms"), null, "type = 1 and read = 0", null, null);
        if (cursorQuery == null) {
            return 0;
        }
        int count = cursorQuery.getCount();
        cursorQuery.close();
        return count;
    }

    public static int getMissCallCount(Context context) {
        String[] strArr;
        long j;
        String str;
        int count = 0;
        if (context == null) {
            return 0;
        }
        try {
            if (!PermissionUtil.checkSelfPermission(context, "android.permission.READ_CALL_LOG")) {
                return -1;
            }
            int i = Build.VERSION.SDK_INT;
            String str2 = d.C;
            int i2 = 24;
            if (i >= 24) {
                strArr = new String[]{"type", "new", "date", "number", d.C, "last_modified"};
            } else {
                strArr = new String[]{"type", "new", "date", "number", d.C};
            }
            Cursor cursorQuery = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, strArr, " type=? and new=?", new String[]{Constants.DIALDEFNED_VERSION_CONNECT, "1"}, "date desc");
            if (cursorQuery != null) {
                count = cursorQuery.getCount();
                if (count > 0) {
                    saveLog("未接来电数量： " + count);
                    while (cursorQuery.moveToNext()) {
                        String string = cursorQuery.getString(cursorQuery.getColumnIndex("number"));
                        if (Build.VERSION.SDK_INT >= i2) {
                            str = str2;
                            j = cursorQuery.getLong(cursorQuery.getColumnIndex("last_modified"));
                        } else {
                            long j2 = cursorQuery.getLong(cursorQuery.getColumnIndex("date"));
                            long j3 = cursorQuery.getLong(cursorQuery.getColumnIndex(str2));
                            j = j3 > 0 ? j2 + (1000 * j3) : 0L;
                            StringBuilder sb = new StringBuilder();
                            sb.append("number = ");
                            sb.append(string);
                            str = str2;
                            sb.append(", date = ");
                            sb.append(DateUtil.format(j2, DateUtil.DATE_FORMAT_YMDHmssss));
                            sb.append(", duration = ");
                            sb.append(j3);
                            sb.append(", hookDate = ");
                            sb.append(DateUtil.format(j, DateUtil.DATE_FORMAT_YMDHmssss));
                            saveLog(sb.toString());
                        }
                        saveLog("number = " + string + ", hookDate = " + DateUtil.format(j, DateUtil.DATE_FORMAT_YMDHmssss));
                        if (j > 0 && System.currentTimeMillis() - j > 3000) {
                            saveLog(string + " 非当前来电 " + DateUtil.format(j, DateUtil.DATE_FORMAT_YMDHmssss));
                            count += -1;
                        }
                        str2 = str;
                        i2 = 24;
                    }
                }
                cursorQuery.close();
            }
            saveLog("有效未接来电数量： " + count);
        } catch (Exception e2) {
            e2.printStackTrace();
            saveLog("getMissCallCount: " + e2.getMessage());
        }
        return count;
    }

    private static void saveLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getNotificationLogPath(), "DeviceAssistService", str);
    }

    public static String getContactNameFromPhoneBook(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        boolean z = ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") != 0;
        CommonLogUtil.d("isPermissions:" + z);
        boolean z2 = ContextCompat.checkSelfPermission(context, "android.permission.READ_CONTACTS") != 0;
        CommonLogUtil.d("isPermissions:" + z);
        if (z || z2) {
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