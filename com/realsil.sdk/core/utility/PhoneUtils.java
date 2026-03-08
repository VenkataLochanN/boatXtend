package com.realsil.sdk.core.utility;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.core.app.ActivityCompat;
import com.ido.life.util.PhoneUtil;
import com.realsil.sdk.core.logger.ZLogger;
import java.lang.reflect.Method;
import org.apache.commons.io.IOUtils;

/* JADX INFO: loaded from: classes3.dex */
public class PhoneUtils {
    public static String MOBILE_OF_CHINA = "10086";
    public static final int PHONE_TYPE_CDMA = 2;
    public static final int PHONE_TYPE_CMCC = 0;
    public static final int PHONE_TYPE_UNICOM = 1;
    public static String TELCOM_OF_CHINA = "10000";
    public static String UNICOM_OF_CHINA = "10010";

    public static void checkSim(Context context) {
        String string;
        StringBuilder sb;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            ZLogger.d("simStateMain: " + telephonyManager.getSimState(0));
            int simState = telephonyManager.getSimState(1);
            sb = new StringBuilder();
            sb.append("simStateSecond: ");
            sb.append(simState);
        } else {
            if (i < 23) {
                string = "sdk version is too low: " + Build.VERSION.SDK_INT;
                ZLogger.d(string);
            }
            if (ActivityCompat.checkSelfPermission(context, "android.permission.READ_PHONE_STATE") != 0) {
                ZLogger.d("permission denied: android.permission.READ_PHONE_STATE");
                return;
            }
            ZLogger.d("simStateMain: " + telephonyManager.getDeviceId(0));
            String deviceId = telephonyManager.getDeviceId(1);
            sb = new StringBuilder();
            sb.append("simStateSecond: ");
            sb.append(deviceId);
        }
        string = sb.toString();
        ZLogger.d(string);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x002f A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getImei(android.content.Context r1) {
        /*
            java.lang.String r0 = "android.permission.READ_PHONE_STATE"
            int r0 = androidx.core.content.ContextCompat.checkSelfPermission(r1, r0)
            if (r0 != 0) goto L17
            java.lang.String r0 = "phone"
            java.lang.Object r1 = r1.getSystemService(r0)
            android.telephony.TelephonyManager r1 = (android.telephony.TelephonyManager) r1
            if (r1 == 0) goto L1c
            java.lang.String r1 = r1.getDeviceId()
            goto L1d
        L17:
            java.lang.String r1 = "permission not grated, android.permission.READ_PHONE_STATE"
            com.realsil.sdk.core.logger.ZLogger.w(r1)
        L1c:
            r1 = 0
        L1d:
            boolean r0 = android.text.TextUtils.isEmpty(r1)
            if (r0 == 0) goto L25
            java.lang.String r1 = android.os.Build.SERIAL
        L25:
            boolean r0 = android.text.TextUtils.isEmpty(r1)
            if (r0 == 0) goto L2d
            java.lang.String r1 = android.os.Build.HARDWARE
        L2d:
            if (r1 != 0) goto L31
            java.lang.String r1 = ""
        L31:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.utility.PhoneUtils.getImei(android.content.Context):java.lang.String");
    }

    public static String getMCC(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        String networkOperator = telephonyManager.getNetworkOperator();
        return !TextUtils.isEmpty(networkOperator) ? networkOperator : telephonyManager.getSimOperator();
    }

    public static int getPhoneType(Context context) {
        String mcc = getMCC(context);
        if ("46000".equals(mcc) || "46002".equals(mcc) || "46007".equals(mcc) || "46008".equals(mcc) || "45412".equals(mcc)) {
            return 0;
        }
        if ("46001".equals(mcc) || "46006".equals(mcc) || "46009".equals(mcc)) {
            return 1;
        }
        return ("46003".equals(mcc) || "46005".equals(mcc) || "46011".equals(mcc) || "45502".equals(mcc) || "45507".equals(mcc)) ? 2 : -1;
    }

    public static String getSerialNo() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Method method = cls.getMethod("get", String.class);
            Object[] objArr = new Object[1];
            objArr[0] = "ro.serialno";
            return (String) method.invoke(cls, objArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static boolean isSimReady(Context context) {
        int simState = ((TelephonyManager) context.getSystemService("phone")).getSimState();
        ZLogger.d(parseSimState(simState));
        return simState == 5;
    }

    public static boolean isSimSupport(Context context) {
        int simState = ((TelephonyManager) context.getSystemService("phone")).getSimState();
        ZLogger.d(parseSimState(simState));
        return simState != 1;
    }

    public static String parseSimState(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "Unknown" : "SIM_STATE_READY" : "SIM_STATE_NETWORK_LOCKED" : "SIM_STATE_PUK_REQUIRED" : "SIM_STATE_PIN_REQUIRED" : "SIM_STATE_ABSENT" : "SIM_STATE_UNKNOWN";
    }

    public static boolean printAllContacts(Context context) {
        String string;
        try {
            if (context == null) {
                ZLogger.w("context == null");
                return false;
            }
            if (ActivityCompat.checkSelfPermission(context, "android.permission.READ_CONTACTS") != 0) {
                ZLogger.d("permission denied: android.permission.READ_CONTACTS");
                return false;
            }
            ContentResolver contentResolver = context.getContentResolver();
            Cursor cursorQuery = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, new String[]{"_id"}, null, null, null);
            if (cursorQuery != null) {
                while (cursorQuery.moveToNext()) {
                    int i = cursorQuery.getInt(0);
                    StringBuilder sb = new StringBuilder("contractID=");
                    sb.append(i);
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(ContactsContract.Contacts.CONTENT_URI);
                    sb2.append("/");
                    sb2.append(i);
                    sb2.append("/data");
                    Cursor cursorQuery2 = contentResolver.query(Uri.parse(sb2.toString()), new String[]{"mimetype", PhoneUtil.NUM, "data2"}, null, null, null);
                    while (cursorQuery2.moveToNext()) {
                        String string2 = cursorQuery2.getString(cursorQuery2.getColumnIndex(PhoneUtil.NUM));
                        String string3 = cursorQuery2.getString(cursorQuery2.getColumnIndex("mimetype"));
                        if ("vnd.android.cursor.item/name".equals(string3)) {
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append(",name=");
                            sb3.append(string2);
                            string = sb3.toString();
                        } else if ("vnd.android.cursor.item/email_v2".equals(string3)) {
                            StringBuilder sb4 = new StringBuilder();
                            sb4.append(",email=");
                            sb4.append(string2);
                            string = sb4.toString();
                        } else if ("vnd.android.cursor.item/phone_v2".equals(string3)) {
                            StringBuilder sb5 = new StringBuilder();
                            sb5.append(",phone=");
                            sb5.append(string2);
                            string = sb5.toString();
                        }
                        sb.append(string);
                        sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                    }
                    cursorQuery2.close();
                    ZLogger.d(sb.toString());
                }
            }
            cursorQuery.close();
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00c5 A[Catch: Exception -> 0x00ff, TryCatch #0 {Exception -> 0x00ff, blocks: (B:4:0x0009, B:7:0x0011, B:10:0x0019, B:12:0x001d, B:15:0x0028, B:18:0x0062, B:23:0x0070, B:25:0x0077, B:27:0x007f, B:29:0x0097, B:31:0x00a2, B:33:0x00aa, B:35:0x00c5, B:37:0x00cb, B:39:0x00d4, B:40:0x00d9, B:44:0x00e2, B:46:0x00e9, B:43:0x00df, B:17:0x004b, B:20:0x0069, B:47:0x00f7, B:49:0x00fb, B:50:0x00fe), top: B:54:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00dd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String queryContact(android.content.Context r14, java.lang.String r15) {
        /*
            Method dump skipped, instruction units count: 267
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.utility.PhoneUtils.queryContact(android.content.Context, java.lang.String):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00ba A[Catch: Exception -> 0x00e0, TryCatch #0 {Exception -> 0x00e0, blocks: (B:4:0x0008, B:6:0x000e, B:9:0x0019, B:12:0x0053, B:17:0x0061, B:19:0x0068, B:21:0x0070, B:23:0x008c, B:25:0x0097, B:27:0x009f, B:29:0x00ae, B:31:0x00ba, B:33:0x00c0, B:35:0x00c9, B:37:0x00ce, B:40:0x00d4, B:11:0x003c, B:14:0x005a, B:41:0x00d8, B:43:0x00dc, B:44:0x00df), top: B:49:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00d2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String queryContactFromSim(android.content.Context r13, java.lang.String r14) {
        /*
            Method dump skipped, instruction units count: 234
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.utility.PhoneUtils.queryContactFromSim(android.content.Context, java.lang.String):java.lang.String");
    }
}