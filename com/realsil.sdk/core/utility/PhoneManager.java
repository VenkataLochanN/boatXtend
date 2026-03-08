package com.realsil.sdk.core.utility;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import androidx.core.app.ActivityCompat;
import com.realsil.sdk.core.logger.ZLogger;
import java.io.IOException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes3.dex */
public class PhoneManager {
    public static Object a(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            Method declaredMethod = telephonyManager.getClass().getDeclaredMethod("getITelephony", new Class[0]);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(telephonyManager, new Object[0]);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void acceptRingingCall(Context context) {
        if (context == null) {
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                TelecomManager telecomManager = (TelecomManager) context.getSystemService("telecom");
                if (telecomManager == null) {
                    ZLogger.w("telephonyManager is null");
                    return;
                } else if (ActivityCompat.checkSelfPermission(context, "android.permission.ANSWER_PHONE_CALLS") != 0) {
                    ZLogger.w("no permission: android.permission.ANSWER_PHONE_CALLS");
                    return;
                } else {
                    telecomManager.acceptRingingCall();
                    return;
                }
            }
            Object objA = a(context);
            if (objA != null) {
                ZLogger.v("answerRingingCall");
                Method method = objA.getClass().getMethod("answerRingingCall", new Class[0]);
                method.setAccessible(true);
                method.invoke(objA, new Object[0]);
                return;
            }
            ZLogger.w("getTelephonyObject failed");
            try {
                ZLogger.w("input key: KeyEvent.KEYCODE_HEADSETHOOK ");
                Runtime runtime = Runtime.getRuntime();
                StringBuilder sb = new StringBuilder();
                sb.append("input keyevent ");
                sb.append(Integer.toString(79));
                runtime.exec(sb.toString());
            } catch (IOException e2) {
                ZLogger.e(e2.toString());
                Intent intentPutExtra = new Intent("android.intent.action.MEDIA_BUTTON").putExtra("android.intent.extra.KEY_EVENT", new KeyEvent(0, 79));
                Intent intentPutExtra2 = new Intent("android.intent.action.MEDIA_BUTTON").putExtra("android.intent.extra.KEY_EVENT", new KeyEvent(1, 79));
                context.sendOrderedBroadcast(intentPutExtra, "android.permission.CALL_PRIVILEGED");
                context.sendOrderedBroadcast(intentPutExtra2, "android.permission.CALL_PRIVILEGED");
            }
        } catch (Exception e3) {
            ZLogger.e(e3.toString());
        }
    }

    public static void endCall(Context context) {
        String str;
        if (context == null) {
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                TelecomManager telecomManager = (TelecomManager) context.getSystemService("telecom");
                if (telecomManager != null) {
                    telecomManager.endCall();
                } else {
                    str = "telephonyManager is null";
                    ZLogger.w(str);
                }
            } else {
                Object objA = a(context);
                if (objA != null) {
                    ZLogger.v("endCall");
                    Method method = objA.getClass().getMethod("endCall", new Class[0]);
                    method.setAccessible(true);
                    method.invoke(objA, new Object[0]);
                } else {
                    str = "getTelephonyObject failed";
                    ZLogger.w(str);
                }
            }
        } catch (Exception e2) {
            ZLogger.e(e2.toString());
        }
    }

    public static void startCall(Context context, String str) {
        if (context == null) {
            return;
        }
        if (ActivityCompat.checkSelfPermission(context, "android.permission.CALL_PHONE") != 0) {
            ZLogger.w("电话权限未开启");
            return;
        }
        Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + str));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }
}