package com.ido.ntf.Utils;

import android.app.Notification;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RemoteViews;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.ido.life.util.PhoneUtil;
import com.ido.ntf.log.LogHandle;
import com.ido.ntf.notification.AppPkgNameUtil;
import com.ido.ntf.phone.PhoneStateHandle;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class NotificationUtil {
    public static int CallTimeMinute = 0;
    public static int CallTimeSecond = 0;
    private static final String TAG = " NotificationSDK  NotificationUtil";
    static String callText;
    static String callTitle;
    Notification notification;
    public static List<TextView> textViewList = new ArrayList();
    public static List<TextView> textList = new ArrayList();
    public static boolean isCommingPhone = false;
    public static boolean needCallRemind = false;
    public static boolean isHandleCallRemind = false;
    public static boolean isSaveNotification = false;
    public static List<Notification> mNotifications = new ArrayList();
    public static boolean isSendCall = false;
    public static Handler callHandler = new Handler();

    public static void addNotifications(Notification notification, String str, String str2) {
        if (!TextUtils.isEmpty(str) && str.contains(str2)) {
            mNotifications.clear();
            isSaveNotification = false;
            isHandleCallRemind = true;
            sendInCallingText(notification, 0);
            return;
        }
        if (!isSaveNotification || AppPkgNameUtil.isKnownAppPkgName().contains(str2)) {
            return;
        }
        LogHandle.getInstance().printLog(" NotificationSDK  NotificationUtil 因为电话的包名问题。通知秒数" + TimeUtil.getTime().second);
        int i = TimeUtil.getTime().second;
        if (CallTimeMinute != TimeUtil.getTime().minute) {
            i += 60;
        }
        if (i - CallTimeSecond < 3) {
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationUtil 因为电话的包名问题。来电通知3秒以内的存起来，存起来的通知" + notification.extras.getString(NotificationCompat.EXTRA_TITLE));
            mNotifications.add(notification);
        }
    }

    public static void CALL_STATE_IDLE() {
        if (isCommingPhone) {
            needCallRemind = false;
        }
        needCallRemind = false;
        callHandler.removeCallbacksAndMessages(null);
        callHandler.removeCallbacksAndMessages(null);
    }

    public static void CALL_STATE_OFFHOOK() {
        isSendCall = true;
        Log.d("timestamp", "取消手环的提示的时间点" + TimeUtil.getTime());
        needCallRemind = false;
        callHandler.removeCallbacksAndMessages(null);
        callHandler.removeCallbacksAndMessages(null);
    }

    public static void CALL_STATE_RINGING(final String str, final String str2) {
        isHandleCallRemind = false;
        isSaveNotification = true;
        CallTimeSecond = TimeUtil.getTime().second;
        CallTimeMinute = TimeUtil.getTime().minute;
        LogHandle.getInstance().printLog(" NotificationSDK  NotificationUtil timestamp接听来电时间" + CallTimeMinute + CallTimeSecond);
        LogHandle.getInstance().printLog(" NotificationSDK  NotificationUtil timestamp接听来电时间点" + TimeUtil.getTime());
        isCommingPhone = true;
        needCallRemind = true;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = str;
        }
        LogHandle.getInstance().printLog(" NotificationSDK  NotificationUtil 来电信息incomingNumber" + str + "    contactName" + str2);
        callHandler.removeCallbacksAndMessages(null);
        callHandler.postDelayed(new Runnable() { // from class: com.ido.ntf.Utils.-$$Lambda$NotificationUtil$XDiY4ToflZRchpYzdqNM3n8O42k
            @Override // java.lang.Runnable
            public final void run() {
                NotificationUtil.sendCallReminder2Device(str, str2);
            }
        }, 100L);
    }

    public static void sendInCallingText(Notification notification, int i) {
        Object obj;
        callTitle = "";
        callText = "";
        Bundle bundle = notification.extras;
        callTitle = bundle.getString(NotificationCompat.EXTRA_TITLE);
        callText = bundle.getString(NotificationCompat.EXTRA_TEXT);
        if (TextUtils.isEmpty(callText) && (obj = bundle.get(NotificationCompat.EXTRA_TEXT)) != null) {
            callText = obj.toString();
        }
        String str = "mTitle:" + callTitle + ",mText:" + callText + " ,flags=" + notification.flags + " ,when=" + notification.when;
        LogHandle.getInstance().printLog(" NotificationSDK  NotificationUtil " + str);
        new ArrayList().clear();
        final List<String> incomingInfo = getIncomingInfo(callTitle, notification);
        LogHandle.getInstance().printLog(" NotificationSDK  NotificationUtil 准备处理数据" + incomingInfo.get(0) + " ---contactName:" + incomingInfo.get(1) + " ---tempName:");
        callHandler.removeCallbacksAndMessages(null);
        mNotifications.clear();
        callHandler.postDelayed(new Runnable() { // from class: com.ido.ntf.Utils.-$$Lambda$NotificationUtil$-G3MFrSuUd9LIHmI814ulFoj4gg
            @Override // java.lang.Runnable
            public final void run() {
                List list = incomingInfo;
                NotificationUtil.sendCallReminder2Device((String) list.get(0), (String) list.get(1));
            }
        }, 300L);
    }

    public static void sendCallReminder2Device(String str, String str2) {
        String str3 = TextUtils.isEmpty(str2) ? str : str2;
        if (TextUtils.isEmpty(str3)) {
            str3 = "***";
        }
        LogHandle.getInstance().printLog(" NotificationSDK  NotificationUtil 来电的电话和联系人" + str + " ---contactName:" + str2 + " ---tempName:");
        if (needCallRemind) {
            needCallRemind = false;
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationUtil 返回来电   phoneNumber: " + str + " ---contactName:" + str2 + " ---tempName:" + str3);
            PhoneStateHandle.getInstance().getPhoneStateBack().onCallNameAndPhoneNumber(str3, str);
        }
    }

    public static boolean notificationIsContactNumber(Notification notification) {
        RemoteViews contentView = getContentView(notification);
        if (contentView == null) {
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationUtil NotificationUtil:contentView == null");
            return false;
        }
        Bundle bundle = notification.extras;
        String string = bundle.getString(NotificationCompat.EXTRA_TITLE);
        String string2 = bundle.getString(NotificationCompat.EXTRA_TEXT);
        if (isContactNumber(string) || isContacts(string)) {
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationUtil callTitle" + string);
            return true;
        }
        if (isContactNumber(string2) || isContacts(string2)) {
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationUtil callText" + string2);
            return true;
        }
        textList.clear();
        getTextList((ViewGroup) contentView.apply(CommUtils.mContext, null));
        LogHandle.getInstance().printLog(" NotificationSDK  NotificationUtil NotificationUtil:contentView != null");
        StringBuilder sb = new StringBuilder();
        for (TextView textView : textList) {
            String string3 = textView.getText().toString();
            sb.append(textView.getId());
            sb.append(":");
            sb.append(string3);
            sb.append(", ");
            if (isContactNumber(string3)) {
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationUtil NotificationUtil:" + sb.toString());
                return true;
            }
            if (isContacts(string3)) {
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationUtil NotificationUtil:" + sb.toString());
                return true;
            }
        }
        LogHandle.getInstance().printLog(" NotificationSDK  NotificationUtil NotificationUtil:" + sb.toString());
        return false;
    }

    public static boolean isContacts(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        LogHandle.getInstance().printLog(" NotificationSDK  NotificationUtil source==" + str);
        Context context = CommUtils.mContext;
        if (ContextCompat.checkSelfPermission(context, "android.permission.READ_CONTACTS") != 0) {
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationUtil NotificationUtil:无权限");
            return false;
        }
        Cursor cursorQuery = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while (cursorQuery.moveToNext()) {
            String string = cursorQuery.getString(cursorQuery.getColumnIndex(PhoneUtil.NAME));
            if (TextUtils.isEmpty(string)) {
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationUtil 联系人名字为空");
                return false;
            }
            if (string.equals(str)) {
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationUtil NotificationUtil:cursor 匹配上的联系人" + string);
                return true;
            }
        }
        cursorQuery.close();
        LogHandle.getInstance().printLog(" NotificationSDK  NotificationUtil NotificationUtil:没有 匹配上的联系人" + str);
        return false;
    }

    public static boolean isContactNumber(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String strReplace = str.trim().replace(" ", "").replace("+", "");
        return TextUtils.isDigitsOnly(strReplace) && strReplace.length() >= 4;
    }

    private static void getTextViewList(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            if (viewGroup.getChildAt(i) instanceof TextView) {
                textViewList.add((TextView) viewGroup.getChildAt(i));
            } else if (viewGroup.getChildAt(i) instanceof ViewGroup) {
                getTextViewList((ViewGroup) viewGroup.getChildAt(i));
            }
        }
    }

    private static void getTextList(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            if (viewGroup.getChildAt(i) instanceof TextView) {
                textList.add((TextView) viewGroup.getChildAt(i));
            } else if (viewGroup.getChildAt(i) instanceof ViewGroup) {
                getTextViewList((ViewGroup) viewGroup.getChildAt(i));
            }
        }
    }

    public static List<String> getIncomingInfo(String str, Notification notification) {
        try {
            return analyseIncomingInfo(str, getContentView(notification));
        } catch (Exception e2) {
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationUtil NotificationUtil:" + e2.getMessage());
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            arrayList.add(str);
            return arrayList;
        }
    }

    public static RemoteViews getContentView(Notification notification) {
        if (notification.contentView != null) {
            return notification.contentView;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            return Notification.Builder.recoverBuilder(CommUtils.mContext, notification).createContentView();
        }
        return null;
    }

    private static List<String> analyseIncomingInfo(String str, RemoteViews remoteViews) {
        ArrayList arrayList = new ArrayList();
        if (remoteViews == null) {
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationUtil NotificationUtil:remoteViews = null");
            arrayList.add(str);
            arrayList.add(str);
            return arrayList;
        }
        if (isValidInfo(str)) {
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationUtil NotificationUtil:isValidInfo");
            arrayList.add(str);
            arrayList.add(CommUtils.getContactNameFromPhoneBook(CommUtils.mContext, str));
            return arrayList;
        }
        textViewList.clear();
        getTextViewList((ViewGroup) remoteViews.apply(CommUtils.mContext, null));
        StringBuilder sb = new StringBuilder();
        for (TextView textView : textViewList) {
            String string = textView.getText().toString();
            sb.append(textView.getId());
            sb.append(":");
            sb.append(string);
            sb.append(", ");
            if (isContactNumber(string)) {
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationUtil find number = " + string);
                arrayList.add(string);
                arrayList.add(CommUtils.getContactNameFromPhoneBook(CommUtils.mContext, string));
                if (!TextUtils.isEmpty(string)) {
                    return arrayList;
                }
            } else if (isContacts(string)) {
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationUtil find contact name = " + string);
                arrayList.add(string);
                arrayList.add(string);
                if (!TextUtils.isEmpty(string)) {
                    return arrayList;
                }
            } else {
                continue;
            }
        }
        arrayList.add(str);
        arrayList.add(str);
        LogHandle.getInstance().printLog(" NotificationSDK  NotificationUtil NotificationUtil:" + sb.toString());
        return arrayList;
    }

    private static boolean isValidInfo(String str) {
        return isContacts(str) || isContactNumber(str);
    }

    public static String getContactNameByPhoneNumber(String str) {
        Context context = CommUtils.mContext;
        String[] strArr = {PhoneUtil.NAME, PhoneUtil.NUM};
        Cursor cursorQuery = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, strArr, "data1 = '" + str + "'", null, null);
        if (cursorQuery == null) {
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationUtil cursor 匹配上的联系人名字");
            return null;
        }
        if (cursorQuery.getCount() > 0) {
            cursorQuery.moveToPosition(0);
            String string = cursorQuery.getString(cursorQuery.getColumnIndex(PhoneUtil.NAME));
            cursorQuery.close();
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationUtil cursor 匹配上的联系人名字" + string);
            return string;
        }
        cursorQuery.close();
        return null;
    }
}