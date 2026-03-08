package com.ido.life.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.PermissionUtil;
import com.ido.life.VeryFitApp;
import com.ido.life.module.device.contract.PhoneDto;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class PhoneUtil {
    public static final String NAME = "display_name";
    public static final String NUM = "data1";
    public static String TAG = PhoneUtil.class.getSimpleName();
    private static Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

    public static void endCall(Context context) {
        try {
            Object telephonyObject = getTelephonyObject(context);
            if (telephonyObject != null) {
                Method method = telephonyObject.getClass().getMethod("endCall", new Class[0]);
                method.setAccessible(true);
                method.invoke(telephonyObject, new Object[0]);
            } else {
                endCall2(context);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            printLog("endCall，error" + e2.toString());
            endCall2(context);
        }
    }

    public static void endCall2(Context context) {
        TelecomManager telecomManager;
        if (context == null) {
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= 28 && (telecomManager = (TelecomManager) context.getSystemService("telecom")) != null && PermissionUtil.checkSelfPermission(context, "android.permission.ANSWER_PHONE_CALLS")) {
                telecomManager.endCall();
                return;
            }
        } catch (Exception e2) {
            printLog("endCall2，error" + e2.toString());
        }
        endCall3(context);
    }

    public static void endCall3(Context context) {
        Object telephonyObject = getTelephonyObject(context);
        Class<?> cls = telephonyObject != null ? telephonyObject.getClass() : null;
        if (cls == null) {
            return;
        }
        try {
            Method method = cls.getMethod("endCallForSubscriber", Integer.TYPE);
            method.setAccessible(true);
            for (int i = 0; i < 20; i++) {
                if (((Boolean) method.invoke(telephonyObject, Integer.valueOf(i))).booleanValue()) {
                    printLog("endCall2 endCallForSubscriber，int success");
                    return;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            printLog("endCall3 endCallForSubscriber，int error " + e2.toString());
            try {
                Method method2 = cls.getMethod("endCallForSubscriber", Long.TYPE);
                method2.setAccessible(true);
                for (int i2 = 0; i2 < 20; i2++) {
                    if (((Boolean) method2.invoke(telephonyObject, Integer.valueOf(i2))).booleanValue()) {
                        printLog("endCall3 endCallForSubscriber，long success");
                        return;
                    }
                }
            } catch (Exception e3) {
                e3.printStackTrace();
                printLog("endCall3 endCallForSubscriber，long error " + e2.toString());
            }
        }
    }

    private static Object getTelephonyObject(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            Method declaredMethod = telephonyManager.getClass().getDeclaredMethod("getITelephony", new Class[0]);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(telephonyManager, new Object[0]);
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
            return null;
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
            return null;
        } catch (SecurityException e5) {
            e5.printStackTrace();
            return null;
        } catch (InvocationTargetException e6) {
            e6.printStackTrace();
            return null;
        }
    }

    public static void answerRingingCallWithReflect(Context context) {
        try {
            Object telephonyObject = getTelephonyObject(context);
            if (telephonyObject != null) {
                Method method = telephonyObject.getClass().getMethod("answerRingingCall", new Class[0]);
                method.setAccessible(true);
                method.invoke(telephonyObject, new Object[0]);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static void answerRingingCallWithBroadcast(Context context) {
        boolean zIsWiredHeadsetOn = ((AudioManager) context.getSystemService("audio")).isWiredHeadsetOn();
        if (!zIsWiredHeadsetOn) {
            CommonLogUtil.d("伪造一个有线耳机插入------1=" + zIsWiredHeadsetOn);
            Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
            intent.putExtra("android.intent.extra.KEY_EVENT", new KeyEvent(0, 79));
            IdoApp.getAppContext().sendOrderedBroadcast(intent, "android.permission.CALL_PRIVILEGED");
            Intent intent2 = new Intent("android.intent.action.MEDIA_BUTTON");
            intent2.putExtra("android.intent.extra.KEY_EVENT", new KeyEvent(1, 79));
            IdoApp.getAppContext().sendOrderedBroadcast(intent2, "android.permission.CALL_PRIVILEGED");
            return;
        }
        CommonLogUtil.d("伪造一个有线耳机插入-----2=" + zIsWiredHeadsetOn);
        Intent intent3 = new Intent("android.intent.action.MEDIA_BUTTON");
        intent3.putExtra("android.intent.extra.KEY_EVENT", new KeyEvent(1, 79));
        context.sendOrderedBroadcast(intent3, null);
    }

    public static void answerRingingCall(Context context) {
        answerRingingCallWithReflect(context);
    }

    public static void callPhone(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + str));
            if (PermissionUtil.checkSelfPermission(context, "android.permission.CALL_PHONE")) {
                context.startActivity(intent);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void dialPhone(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            context.startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + str)));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static void printLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getBugLogPath(), TAG, str);
    }

    public static void hideKeyboard(Activity activity) {
        View currentFocus = activity.getCurrentFocus();
        if (currentFocus == null) {
            return;
        }
        ((InputMethodManager) Objects.requireNonNull((InputMethodManager) activity.getSystemService("input_method"))).hideSoftInputFromWindow(((View) Objects.requireNonNull(currentFocus)).getWindowToken(), 2);
    }

    public static List<PhoneDto> getPhone() {
        ArrayList arrayList = new ArrayList();
        if (PermissionUtil.checkSelfPermission(VeryFitApp.getApp().getApplicationContext(), "android.permission.READ_CONTACTS")) {
            Cursor cursorQuery = VeryFitApp.getApp().getApplicationContext().getContentResolver().query(phoneUri, new String[]{NUM, NAME}, null, null, null);
            while (cursorQuery.moveToNext()) {
                PhoneDto phoneDto = new PhoneDto(cursorQuery.getString(cursorQuery.getColumnIndex(NAME)), cursorQuery.getString(cursorQuery.getColumnIndex(NUM)), false, false);
                arrayList.add(phoneDto);
                if (!arrayList.contains(phoneDto)) {
                    arrayList.add(phoneDto);
                }
            }
        }
        return arrayList;
    }
}