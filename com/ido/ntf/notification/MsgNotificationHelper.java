package com.ido.ntf.notification;

import android.app.Notification;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.ido.ntf.bean.NotificationInfo;
import com.ido.ntf.log.LogHandle;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class MsgNotificationHelper {
    private static final String TAG = " NotificationSDK  MsgNotificationHelper";
    private static String lastCalendarContent;
    private static long lastCalendarTimestamp;
    private static String lastSmsContent;
    private static long lastSmsTimestamp;
    private static long lineMsTime;
    private static int msgID;
    private static Map<Integer, Notification> sNotificationMap;

    public static NotificationInfo formNotificationGetMessageInfo(Notification notification, String str) {
        String strSubstring;
        String string;
        String string2;
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper formNotificationGetMessageInfo notification.flags = " + notification.flags);
        if ((str.equals("com.xtc.watch") && notification.flags == 114) || str.equals("com.google.android.dialer")) {
            return null;
        }
        try {
            if (Build.VERSION.SDK_INT > 18) {
                Bundle bundle = notification.extras;
                CharSequence string3 = bundle.getString(NotificationCompat.EXTRA_TITLE);
                if (string3 instanceof SpannableString) {
                    string = ((SpannableString) string3).subSequence(0, ((SpannableString) string3).length()).toString();
                } else {
                    string = bundle.getString(NotificationCompat.EXTRA_TITLE);
                }
                CharSequence charSequence = bundle.getCharSequence(NotificationCompat.EXTRA_TEXT);
                if (charSequence instanceof SpannableString) {
                    string2 = ((SpannableString) charSequence).subSequence(0, ((SpannableString) charSequence).length()).toString();
                } else {
                    string2 = bundle.getString(NotificationCompat.EXTRA_TEXT);
                }
                if (TextUtils.isEmpty(string2)) {
                    string2 = notification.tickerText.toString();
                }
                strSubstring = string2;
            } else {
                strSubstring = TextUtils.isEmpty(notification.tickerText) ? null : notification.tickerText.toString();
                string = null;
            }
        } catch (Exception unused) {
            strSubstring = "";
            string = strSubstring;
        }
        if (TextUtils.isEmpty(string)) {
            string = "";
        }
        if (TextUtils.isEmpty(strSubstring)) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，text为null，不提醒");
            return null;
        }
        if (!TextUtils.isEmpty(string) && strSubstring.startsWith(string)) {
            strSubstring = strSubstring.substring(string.length());
        }
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，title：" + string + "，text：" + strSubstring);
        if (string.equalsIgnoreCase("QQ")) {
            return null;
        }
        if (TextUtils.isEmpty(strSubstring) && TextUtils.isEmpty(string)) {
            return null;
        }
        return getMessageInfo(string, strSubstring, str);
    }

    public static NotificationInfo formQQNotificationGetMessageInfo(Notification notification, String str) {
        String strSubstring;
        String string;
        String string2;
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper formQQNotificationGetMessageInfo " + notification.flags);
        if (notification.flags == 106) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper QQ正在语音");
            return null;
        }
        if (notification.flags == 231) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper QQ邀请视频重复");
            return null;
        }
        try {
            if (Build.VERSION.SDK_INT > 18) {
                Bundle bundle = notification.extras;
                CharSequence string3 = bundle.getString(NotificationCompat.EXTRA_TITLE);
                if (string3 instanceof SpannableString) {
                    string = ((SpannableString) string3).subSequence(0, ((SpannableString) string3).length()).toString();
                } else {
                    string = bundle.getString(NotificationCompat.EXTRA_TITLE);
                }
                CharSequence charSequence = bundle.getCharSequence(NotificationCompat.EXTRA_TEXT);
                if (charSequence instanceof SpannableString) {
                    string2 = ((SpannableString) charSequence).subSequence(0, ((SpannableString) charSequence).length()).toString();
                } else {
                    string2 = bundle.getString(NotificationCompat.EXTRA_TEXT);
                }
                if (TextUtils.isEmpty(string2)) {
                    string2 = notification.tickerText.toString();
                }
                strSubstring = string2;
            } else {
                strSubstring = TextUtils.isEmpty(notification.tickerText) ? null : notification.tickerText.toString();
                string = null;
            }
        } catch (Exception unused) {
            strSubstring = "";
            string = strSubstring;
        }
        if (TextUtils.isEmpty(string)) {
            string = "";
        }
        if (TextUtils.isEmpty(strSubstring)) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，text为null，不提醒");
            return null;
        }
        if (!TextUtils.isEmpty(string) && strSubstring.startsWith(string)) {
            strSubstring = strSubstring.substring(string.length());
        }
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，title：" + string + "，text：" + strSubstring);
        if (string.equalsIgnoreCase("QQ")) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，QQ内部消息，，，，不返回");
            return null;
        }
        if (TextUtils.isEmpty(strSubstring) && TextUtils.isEmpty(string)) {
            return null;
        }
        return getMessageInfo(string, strSubstring, str);
    }

    public static NotificationInfo formWXNotificationGetMessageInfo(Notification notification, String str) {
        String strSubstring;
        String strSubstring2;
        String string;
        String string2;
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper formWXNotificationGetMessageInfo " + notification.flags);
        if (notification.flags == 2 || notification.flags == 98) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 微信正在语音");
            return null;
        }
        try {
            if (Build.VERSION.SDK_INT > 18) {
                Bundle bundle = notification.extras;
                CharSequence string3 = bundle.getString(NotificationCompat.EXTRA_TITLE);
                if (string3 instanceof SpannableString) {
                    string = ((SpannableString) string3).subSequence(0, ((SpannableString) string3).length()).toString();
                } else {
                    string = bundle.getString(NotificationCompat.EXTRA_TITLE);
                }
                CharSequence charSequence = bundle.getCharSequence(NotificationCompat.EXTRA_TEXT);
                if (charSequence instanceof SpannableString) {
                    string2 = ((SpannableString) charSequence).subSequence(0, ((SpannableString) charSequence).length()).toString();
                } else {
                    string2 = bundle.getString(NotificationCompat.EXTRA_TEXT);
                }
                if (TextUtils.isEmpty(string2)) {
                    string2 = notification.tickerText.toString();
                }
                if (!TextUtils.isEmpty(string2) && !TextUtils.isEmpty(string) && string2.startsWith(string.concat(":"))) {
                    string2 = string2.substring(string.concat(":").length());
                }
                strSubstring2 = string2;
                strSubstring = string;
            } else {
                String string4 = TextUtils.isEmpty(notification.tickerText) ? null : notification.tickerText.toString();
                if (TextUtils.isEmpty(string4) || !string4.contains(":")) {
                    strSubstring2 = string4;
                    strSubstring = null;
                } else {
                    String[] strArrSplit = string4.split(":");
                    String str2 = strArrSplit[0];
                    strSubstring2 = strArrSplit[1];
                    strSubstring = strArrSplit[0].contains("]") ? strArrSplit[0].substring(strArrSplit[0].indexOf("]") + 1) : str2;
                }
            }
        } catch (Exception unused) {
            strSubstring = "";
            strSubstring2 = strSubstring;
        }
        if (TextUtils.isEmpty(strSubstring)) {
            strSubstring = "";
        }
        if (TextUtils.isEmpty(strSubstring2)) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，text为null，不提醒");
            return null;
        }
        if (!TextUtils.isEmpty(strSubstring) && strSubstring2.startsWith(strSubstring)) {
            strSubstring2 = strSubstring2.substring(strSubstring.length());
        }
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，title：" + strSubstring + "，text：" + strSubstring2);
        if (strSubstring.equalsIgnoreCase("QQ")) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，QQ内部消息，，，，不返回");
            return null;
        }
        if (TextUtils.isEmpty(strSubstring2) && TextUtils.isEmpty(strSubstring)) {
            return null;
        }
        return getMessageInfo(strSubstring, strSubstring2, str);
    }

    public static NotificationInfo formFaceBookNotificationGetMessageInfo(Notification notification, String str) {
        String strSubstring;
        String string;
        String string2;
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper formFaceBookNotificationGetMessageInfo " + notification.flags);
        try {
            if (Build.VERSION.SDK_INT > 18) {
                Bundle bundle = notification.extras;
                CharSequence string3 = bundle.getString(NotificationCompat.EXTRA_TITLE);
                if (string3 instanceof SpannableString) {
                    string = ((SpannableString) string3).subSequence(0, ((SpannableString) string3).length()).toString();
                } else {
                    string = bundle.getString(NotificationCompat.EXTRA_TITLE);
                }
                CharSequence charSequence = bundle.getCharSequence(NotificationCompat.EXTRA_TEXT);
                if (charSequence instanceof SpannableString) {
                    string2 = ((SpannableString) charSequence).subSequence(0, ((SpannableString) charSequence).length()).toString();
                } else {
                    string2 = bundle.getString(NotificationCompat.EXTRA_TEXT);
                }
                if (TextUtils.isEmpty(string2)) {
                    string2 = notification.tickerText.toString();
                }
                strSubstring = string2;
            } else {
                strSubstring = TextUtils.isEmpty(notification.tickerText) ? null : notification.tickerText.toString();
                string = null;
            }
        } catch (Exception unused) {
            strSubstring = "";
            string = strSubstring;
        }
        if (TextUtils.isEmpty(string)) {
            string = "";
        }
        if (TextUtils.isEmpty(strSubstring)) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，text为null，不提醒");
            return null;
        }
        if (!TextUtils.isEmpty(string) && strSubstring.startsWith(string)) {
            strSubstring = strSubstring.substring(string.length());
        }
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，title：" + string + "，text：" + strSubstring);
        if (string.equalsIgnoreCase("QQ")) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，QQ内部消息，，，，不返回");
            return null;
        }
        if (TextUtils.isEmpty(strSubstring) && TextUtils.isEmpty(string)) {
            return null;
        }
        return getMessageInfo(string, strSubstring, str);
    }

    public static NotificationInfo formTwiTTerNotificationGetMessageInfo(Notification notification, String str) {
        String strSubstring;
        String string;
        String string2;
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper formTwiTTerNotificationGetMessageInfo type===>" + notification.extras);
        try {
            if (Build.VERSION.SDK_INT > 18) {
                Bundle bundle = notification.extras;
                CharSequence string3 = bundle.getString(NotificationCompat.EXTRA_TITLE);
                if (string3 instanceof SpannableString) {
                    string = ((SpannableString) string3).subSequence(0, ((SpannableString) string3).length()).toString();
                } else {
                    string = bundle.getString(NotificationCompat.EXTRA_TITLE);
                }
                CharSequence charSequence = bundle.getCharSequence(NotificationCompat.EXTRA_TEXT);
                if (charSequence instanceof SpannableString) {
                    string2 = ((SpannableString) charSequence).subSequence(0, ((SpannableString) charSequence).length()).toString();
                } else {
                    string2 = bundle.getString(NotificationCompat.EXTRA_TEXT);
                }
                if (TextUtils.isEmpty(string2)) {
                    string2 = notification.tickerText.toString();
                }
                strSubstring = string2;
            } else {
                strSubstring = TextUtils.isEmpty(notification.tickerText) ? null : notification.tickerText.toString();
                string = null;
            }
        } catch (Exception unused) {
            strSubstring = "";
            string = strSubstring;
        }
        if (TextUtils.isEmpty(string)) {
            string = "";
        }
        if (TextUtils.isEmpty(strSubstring)) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，text为null，不提醒");
            return null;
        }
        if (!TextUtils.isEmpty(string) && strSubstring.startsWith(string)) {
            strSubstring = strSubstring.substring(string.length());
        }
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，title：" + string + "，text：" + strSubstring);
        if (string.equalsIgnoreCase("QQ")) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，QQ内部消息，，，，不返回");
            return null;
        }
        if (TextUtils.isEmpty(strSubstring) && TextUtils.isEmpty(string)) {
            return null;
        }
        return getMessageInfo(string, strSubstring, str);
    }

    public static NotificationInfo formWhatsAppNotificationGetMessageInfo(Notification notification, String str) {
        String strSubstring;
        String string;
        String string2;
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper formWhatsAppNotificationGetMessageInfo " + notification.flags);
        try {
            if (Build.VERSION.SDK_INT > 18) {
                Bundle bundle = notification.extras;
                CharSequence string3 = bundle.getString(NotificationCompat.EXTRA_TITLE);
                if (string3 instanceof SpannableString) {
                    string = ((SpannableString) string3).subSequence(0, ((SpannableString) string3).length()).toString();
                } else {
                    string = bundle.getString(NotificationCompat.EXTRA_TITLE);
                }
                Object obj = bundle.get(NotificationCompat.EXTRA_TEXT_LINES);
                bundle.get(NotificationCompat.EXTRA_BIG_TEXT);
                if (obj != null && (obj instanceof CharSequence[])) {
                    CharSequence[] charSequenceArr = (CharSequence[]) obj;
                    if (charSequenceArr != null && charSequenceArr.length > 0) {
                        if (charSequenceArr.length >= 2 && lastSmsContent != null && lastSmsContent.equals(charSequenceArr[1].toString())) {
                            string2 = charSequenceArr[0].toString();
                        } else {
                            string2 = charSequenceArr[charSequenceArr.length - 1].toString();
                        }
                    } else {
                        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 没获取到有效信息1");
                        string2 = null;
                    }
                } else {
                    CharSequence charSequence = bundle.getCharSequence(NotificationCompat.EXTRA_TEXT);
                    if (charSequence instanceof SpannableString) {
                        string2 = ((SpannableString) charSequence).subSequence(0, ((SpannableString) charSequence).length()).toString();
                    } else {
                        string2 = bundle.getString(NotificationCompat.EXTRA_TEXT);
                    }
                }
                if (TextUtils.isEmpty(string2)) {
                    string2 = notification.tickerText.toString();
                }
                strSubstring = string2;
            } else {
                strSubstring = TextUtils.isEmpty(notification.tickerText) ? null : notification.tickerText.toString();
                string = null;
            }
        } catch (Exception unused) {
            strSubstring = "";
            string = strSubstring;
        }
        if (TextUtils.isEmpty(string)) {
            string = "";
        }
        if (TextUtils.isEmpty(strSubstring)) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，text为null，不提醒");
            return null;
        }
        if (!TextUtils.isEmpty(string) && strSubstring.startsWith(string)) {
            strSubstring = strSubstring.substring(string.length());
        }
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，title：" + string + "，text：" + strSubstring);
        if (string.equalsIgnoreCase("QQ")) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，QQ内部消息，，，，不发送");
            return null;
        }
        if (TextUtils.isEmpty(strSubstring) && TextUtils.isEmpty(string)) {
            return null;
        }
        return getMessageInfo(string, strSubstring, str);
    }

    public static NotificationInfo formLinKeDinNotificationGetMessageInfo(Notification notification, String str) {
        String strSubstring;
        String string;
        String string2;
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper formLinKeDinNotification " + notification.flags);
        if (notification.flags == 0) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper LINKEDIN消息重复");
            return null;
        }
        try {
            if (Build.VERSION.SDK_INT > 18) {
                Bundle bundle = notification.extras;
                CharSequence string3 = bundle.getString(NotificationCompat.EXTRA_TITLE);
                if (string3 instanceof SpannableString) {
                    string = ((SpannableString) string3).subSequence(0, ((SpannableString) string3).length()).toString();
                } else {
                    string = bundle.getString(NotificationCompat.EXTRA_TITLE);
                }
                CharSequence charSequence = bundle.getCharSequence(NotificationCompat.EXTRA_TEXT);
                if (charSequence instanceof SpannableString) {
                    string2 = ((SpannableString) charSequence).subSequence(0, ((SpannableString) charSequence).length()).toString();
                } else {
                    string2 = bundle.getString(NotificationCompat.EXTRA_TEXT);
                }
                if (TextUtils.isEmpty(string2)) {
                    string2 = notification.tickerText.toString();
                }
                strSubstring = string2;
            } else {
                strSubstring = TextUtils.isEmpty(notification.tickerText) ? null : notification.tickerText.toString();
                string = null;
            }
        } catch (Exception unused) {
            strSubstring = "";
            string = strSubstring;
        }
        if (TextUtils.isEmpty(string)) {
            string = "";
        }
        if (TextUtils.isEmpty(strSubstring)) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，text为null，不提醒");
            return null;
        }
        if (!TextUtils.isEmpty(string) && strSubstring.startsWith(string)) {
            strSubstring = strSubstring.substring(string.length());
        }
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，title：" + string + "，text：" + strSubstring);
        if (string.equalsIgnoreCase("QQ")) {
            return null;
        }
        if (TextUtils.isEmpty(strSubstring) && TextUtils.isEmpty(string)) {
            return null;
        }
        return getMessageInfo(string, strSubstring, str);
    }

    public static NotificationInfo formEmailNotificationGetMessageInfo(Notification notification, String str) {
        String strSubstring;
        String string;
        String string2;
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper formEmailNotificationGetMessageInfo " + notification.flags);
        try {
            if (Build.VERSION.SDK_INT > 18) {
                Bundle bundle = notification.extras;
                CharSequence string3 = bundle.getString(NotificationCompat.EXTRA_TITLE);
                if (string3 instanceof SpannableString) {
                    string = ((SpannableString) string3).subSequence(0, ((SpannableString) string3).length()).toString();
                } else {
                    string = bundle.getString(NotificationCompat.EXTRA_TITLE);
                }
                CharSequence charSequence = bundle.getCharSequence(NotificationCompat.EXTRA_TEXT);
                if (charSequence instanceof SpannableString) {
                    string2 = ((SpannableString) charSequence).subSequence(0, ((SpannableString) charSequence).length()).toString();
                } else {
                    string2 = bundle.getString(NotificationCompat.EXTRA_TEXT);
                }
                if (TextUtils.isEmpty(string2)) {
                    string2 = notification.tickerText.toString();
                }
                strSubstring = string2;
            } else {
                strSubstring = TextUtils.isEmpty(notification.tickerText) ? null : notification.tickerText.toString();
                string = null;
            }
        } catch (Exception unused) {
            strSubstring = "";
            string = strSubstring;
        }
        if (TextUtils.isEmpty(string)) {
            string = "";
        }
        if (TextUtils.isEmpty(strSubstring)) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，text为null，不提醒");
            return null;
        }
        if (!TextUtils.isEmpty(string) && strSubstring.startsWith(string)) {
            strSubstring = strSubstring.substring(string.length());
        }
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，title：" + string + "，text：" + strSubstring);
        if (string.equalsIgnoreCase("QQ")) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，QQ内部消息，，，，不返回");
            return null;
        }
        return getMessageInfo(string, strSubstring, str);
    }

    public static NotificationInfo formInsTaGramNotificationGetMessageInfo(Notification notification, String str) {
        String strSubstring;
        String string;
        String string2;
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper formNotificationAndSend2Device " + notification.flags);
        try {
            if (Build.VERSION.SDK_INT > 18) {
                Bundle bundle = notification.extras;
                CharSequence string3 = bundle.getString(NotificationCompat.EXTRA_TITLE);
                if (string3 instanceof SpannableString) {
                    string = ((SpannableString) string3).subSequence(0, ((SpannableString) string3).length()).toString();
                } else {
                    string = bundle.getString(NotificationCompat.EXTRA_TITLE);
                }
                CharSequence charSequence = bundle.getCharSequence(NotificationCompat.EXTRA_TEXT);
                if (charSequence instanceof SpannableString) {
                    string2 = ((SpannableString) charSequence).subSequence(0, ((SpannableString) charSequence).length()).toString();
                } else {
                    string2 = bundle.getString(NotificationCompat.EXTRA_TEXT);
                }
                if (TextUtils.isEmpty(string2)) {
                    string2 = notification.tickerText.toString();
                }
                strSubstring = string2;
            } else {
                strSubstring = TextUtils.isEmpty(notification.tickerText) ? null : notification.tickerText.toString();
                string = null;
            }
        } catch (Exception unused) {
            strSubstring = "";
            string = strSubstring;
        }
        if (TextUtils.isEmpty(string)) {
            string = "";
        }
        if (TextUtils.isEmpty(strSubstring)) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，text为null，不提醒");
            return null;
        }
        if (!TextUtils.isEmpty(string) && strSubstring.startsWith(string)) {
            strSubstring = strSubstring.substring(string.length());
        }
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，title：" + string + "，text：" + strSubstring);
        if (string.equalsIgnoreCase("QQ")) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，QQ内部消息，，，，不返回");
            return null;
        }
        if (TextUtils.isEmpty(strSubstring) && TextUtils.isEmpty(string)) {
            return null;
        }
        return getMessageInfo(string, strSubstring, str);
    }

    public static NotificationInfo formMessengerNotificationGetMessageInfo(Notification notification, String str) {
        String strSubstring;
        String string;
        String string2;
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper formMessengerNotificationGetMessageInfo " + notification.flags);
        try {
            if (Build.VERSION.SDK_INT > 18) {
                Bundle bundle = notification.extras;
                CharSequence string3 = bundle.getString(NotificationCompat.EXTRA_TITLE);
                if (string3 instanceof SpannableString) {
                    string = ((SpannableString) string3).subSequence(0, ((SpannableString) string3).length()).toString();
                } else {
                    string = bundle.getString(NotificationCompat.EXTRA_TITLE);
                }
                CharSequence charSequence = bundle.getCharSequence(NotificationCompat.EXTRA_TEXT);
                if (charSequence instanceof SpannableString) {
                    string2 = ((SpannableString) charSequence).subSequence(0, ((SpannableString) charSequence).length()).toString();
                } else {
                    string2 = bundle.getString(NotificationCompat.EXTRA_TEXT);
                }
                if (TextUtils.isEmpty(string2)) {
                    string2 = notification.tickerText.toString();
                }
                strSubstring = string2;
            } else {
                strSubstring = TextUtils.isEmpty(notification.tickerText) ? null : notification.tickerText.toString();
                string = null;
            }
        } catch (Exception unused) {
            strSubstring = "";
            string = strSubstring;
        }
        if (TextUtils.isEmpty(string)) {
            string = "";
        }
        if (TextUtils.isEmpty(strSubstring)) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，text为null，不提醒");
            return null;
        }
        if (!TextUtils.isEmpty(string) && strSubstring.startsWith(string)) {
            strSubstring = strSubstring.substring(string.length());
        }
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，title：" + string + "，text：" + strSubstring);
        if (string.equalsIgnoreCase("QQ")) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，QQ内部消息，，，，不返回");
            return null;
        }
        if (TextUtils.isEmpty(strSubstring) && TextUtils.isEmpty(string)) {
            return null;
        }
        return getMessageInfo(string, strSubstring, str);
    }

    public static NotificationInfo formKaKaoTalkNotificationGetMessageInfo(Notification notification, String str) {
        String strSubstring;
        String string;
        String string2;
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper formNotificationAndSend2Device " + notification.flags);
        try {
            if (Build.VERSION.SDK_INT > 18) {
                Bundle bundle = notification.extras;
                CharSequence string3 = bundle.getString(NotificationCompat.EXTRA_TITLE);
                if (string3 instanceof SpannableString) {
                    string = ((SpannableString) string3).subSequence(0, ((SpannableString) string3).length()).toString();
                } else {
                    string = bundle.getString(NotificationCompat.EXTRA_TITLE);
                }
                CharSequence charSequence = bundle.getCharSequence(NotificationCompat.EXTRA_TEXT);
                if (charSequence instanceof SpannableString) {
                    string2 = ((SpannableString) charSequence).subSequence(0, ((SpannableString) charSequence).length()).toString();
                } else {
                    string2 = bundle.getString(NotificationCompat.EXTRA_TEXT);
                }
                if (TextUtils.isEmpty(string2)) {
                    string2 = notification.tickerText.toString();
                }
                strSubstring = string2;
            } else {
                strSubstring = TextUtils.isEmpty(notification.tickerText) ? null : notification.tickerText.toString();
                string = null;
            }
        } catch (Exception unused) {
            strSubstring = "";
            string = strSubstring;
        }
        if (TextUtils.isEmpty(string)) {
            string = "";
        }
        if (TextUtils.isEmpty(strSubstring)) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，text为null，不提醒");
            return null;
        }
        if (!TextUtils.isEmpty(string) && strSubstring.startsWith(string)) {
            strSubstring = strSubstring.substring(string.length());
        }
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，title：" + string + "，text：" + strSubstring);
        if (string.equalsIgnoreCase("QQ")) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，QQ内部消息，，，，不返回");
            return null;
        }
        if (TextUtils.isEmpty(strSubstring) && TextUtils.isEmpty(string)) {
            return null;
        }
        return getMessageInfo(string, strSubstring, str);
    }

    public static NotificationInfo formViBerNotificationGetMessageInfo(Notification notification, String str) {
        String strSubstring;
        String string;
        String string2;
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper formViBerNotificationAndSend2Device " + notification.flags);
        if (notification.flags == 106 || notification.flags == 98 || notification.flags == 10 || notification.flags == 2) {
            return null;
        }
        try {
            if (Build.VERSION.SDK_INT > 18) {
                Bundle bundle = notification.extras;
                CharSequence string3 = bundle.getString(NotificationCompat.EXTRA_TITLE);
                if (string3 instanceof SpannableString) {
                    string = ((SpannableString) string3).subSequence(0, ((SpannableString) string3).length()).toString();
                } else {
                    string = bundle.getString(NotificationCompat.EXTRA_TITLE);
                }
                CharSequence charSequence = bundle.getCharSequence(NotificationCompat.EXTRA_TEXT);
                if (charSequence instanceof SpannableString) {
                    string2 = ((SpannableString) charSequence).subSequence(0, ((SpannableString) charSequence).length()).toString();
                } else {
                    string2 = bundle.getString(NotificationCompat.EXTRA_TEXT);
                }
                if (TextUtils.isEmpty(string2)) {
                    string2 = notification.tickerText.toString();
                }
                strSubstring = string2;
            } else {
                strSubstring = TextUtils.isEmpty(notification.tickerText) ? null : notification.tickerText.toString();
                string = null;
            }
        } catch (Exception unused) {
            strSubstring = "";
            string = strSubstring;
        }
        if (TextUtils.isEmpty(string)) {
            string = "";
        }
        if (TextUtils.isEmpty(strSubstring)) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，text为null，不提醒");
            return null;
        }
        if (!TextUtils.isEmpty(string) && strSubstring.startsWith(string)) {
            strSubstring = strSubstring.substring(string.length());
        }
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，title：" + string + "，text：" + strSubstring);
        if (string.equalsIgnoreCase("QQ")) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，QQ内部消息，，，，不返回");
            return null;
        }
        if (TextUtils.isEmpty(strSubstring) && TextUtils.isEmpty(string)) {
            return null;
        }
        return getMessageInfo(string, strSubstring, str);
    }

    public static NotificationInfo formLinePkgListNotificationGetMessageInfo(Notification notification, String str) {
        String strSubstring;
        String string;
        String string2;
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper formNotificationAndSend2Device " + notification.flags);
        if (notification.flags == 536) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper notification.flags" + notification.flags);
            return null;
        }
        if (lineMsTime != 0 && System.currentTimeMillis() - lineMsTime > 30000 && (notification.flags == 17 || notification.flags == 529)) {
            LogHandle logHandle = LogHandle.getInstance();
            StringBuilder sb = new StringBuilder();
            sb.append(" NotificationSDK  MsgNotificationHelper System.currentTimeMillis()-lineMsTime===");
            sb.append(System.currentTimeMillis() - lineMsTime > 30000);
            logHandle.printLog(sb.toString());
            return null;
        }
        lineMsTime = System.currentTimeMillis();
        try {
            if (Build.VERSION.SDK_INT > 18) {
                Bundle bundle = notification.extras;
                CharSequence string3 = bundle.getString(NotificationCompat.EXTRA_TITLE);
                if (string3 instanceof SpannableString) {
                    string = ((SpannableString) string3).subSequence(0, ((SpannableString) string3).length()).toString();
                } else {
                    string = bundle.getString(NotificationCompat.EXTRA_TITLE);
                }
                CharSequence charSequence = bundle.getCharSequence(NotificationCompat.EXTRA_TEXT);
                if (charSequence instanceof SpannableString) {
                    string2 = ((SpannableString) charSequence).subSequence(0, ((SpannableString) charSequence).length()).toString();
                } else {
                    string2 = bundle.getString(NotificationCompat.EXTRA_TEXT);
                }
                if (TextUtils.isEmpty(string2)) {
                    string2 = notification.tickerText.toString();
                }
                strSubstring = string2;
            } else {
                strSubstring = TextUtils.isEmpty(notification.tickerText) ? null : notification.tickerText.toString();
                string = null;
            }
        } catch (Exception unused) {
            strSubstring = "";
            string = strSubstring;
        }
        if (TextUtils.isEmpty(string)) {
            string = "";
        }
        if (TextUtils.isEmpty(strSubstring)) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，text为null，不提醒");
            return null;
        }
        if (!TextUtils.isEmpty(string) && strSubstring.startsWith(string)) {
            strSubstring = strSubstring.substring(string.length());
        }
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，title：" + string + "，text：" + strSubstring);
        if (string.equalsIgnoreCase("QQ")) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，QQ内部消息，，，，不返回");
            return null;
        }
        if (TextUtils.isEmpty(strSubstring) && TextUtils.isEmpty(string)) {
            return null;
        }
        return getMessageInfo(string, strSubstring, str);
    }

    public static NotificationInfo formVkonTakteNotificationGetMessageInfo(Notification notification, String str) {
        String strSubstring;
        String string;
        String string2;
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper formNotificationAndSend2Device " + notification.flags);
        try {
            if (Build.VERSION.SDK_INT > 18) {
                Bundle bundle = notification.extras;
                CharSequence string3 = bundle.getString(NotificationCompat.EXTRA_TITLE);
                if (string3 instanceof SpannableString) {
                    string = ((SpannableString) string3).subSequence(0, ((SpannableString) string3).length()).toString();
                } else {
                    string = bundle.getString(NotificationCompat.EXTRA_TITLE);
                }
                CharSequence charSequence = bundle.getCharSequence(NotificationCompat.EXTRA_TEXT);
                if (charSequence instanceof SpannableString) {
                    string2 = ((SpannableString) charSequence).subSequence(0, ((SpannableString) charSequence).length()).toString();
                } else {
                    string2 = bundle.getString(NotificationCompat.EXTRA_TEXT);
                }
                if (TextUtils.isEmpty(string2)) {
                    string2 = notification.tickerText.toString();
                }
                strSubstring = string2;
            } else {
                strSubstring = TextUtils.isEmpty(notification.tickerText) ? null : notification.tickerText.toString();
                string = null;
            }
        } catch (Exception unused) {
            strSubstring = "";
            string = strSubstring;
        }
        if (TextUtils.isEmpty(string)) {
            string = "";
        }
        if (TextUtils.isEmpty(strSubstring)) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，text为null，不提醒");
            return null;
        }
        if (!TextUtils.isEmpty(string) && strSubstring.startsWith(string)) {
            strSubstring = strSubstring.substring(string.length());
        }
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，title：" + string + "，text：" + strSubstring);
        if (string.equalsIgnoreCase("QQ")) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，QQ内部消息，，，，不返回");
            return null;
        }
        if (TextUtils.isEmpty(strSubstring) && TextUtils.isEmpty(string)) {
            return null;
        }
        return getMessageInfo(string, strSubstring, str);
    }

    public static NotificationInfo formSnapchatNotificationGetMessageInfo(Notification notification, String str) {
        String strSubstring;
        String string;
        String str2;
        String string2;
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper formSnapchatnotification.flags" + notification.flags);
        try {
            if (Build.VERSION.SDK_INT > 18) {
                Bundle bundle = notification.extras;
                CharSequence string3 = bundle.getString(NotificationCompat.EXTRA_TITLE);
                if (string3 instanceof SpannableString) {
                    string = ((SpannableString) string3).subSequence(0, ((SpannableString) string3).length()).toString();
                } else {
                    string = bundle.getString(NotificationCompat.EXTRA_TITLE);
                }
                CharSequence charSequence = bundle.getCharSequence(NotificationCompat.EXTRA_TEXT);
                if (charSequence instanceof SpannableString) {
                    string2 = ((SpannableString) charSequence).subSequence(0, ((SpannableString) charSequence).length()).toString();
                } else {
                    string2 = bundle.getString(NotificationCompat.EXTRA_TEXT);
                }
                if (TextUtils.isEmpty(string2)) {
                    string2 = notification.tickerText.toString();
                }
                strSubstring = string2;
            } else {
                strSubstring = TextUtils.isEmpty(notification.tickerText) ? null : notification.tickerText.toString();
                string = null;
            }
        } catch (Exception unused) {
            strSubstring = "";
            string = strSubstring;
        }
        if (TextUtils.isEmpty(string)) {
            string = "";
        }
        if (TextUtils.isEmpty(strSubstring)) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，text为null，不提醒");
            return null;
        }
        if (!TextUtils.isEmpty(string) && strSubstring.startsWith(string)) {
            strSubstring = strSubstring.substring(string.length());
        }
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，title：" + string + "，text：" + strSubstring);
        if (string.equalsIgnoreCase("QQ")) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，QQ内部消息，，，，不返回");
            return null;
        }
        if (TextUtils.isEmpty(strSubstring) && TextUtils.isEmpty(string)) {
            return null;
        }
        if (TextUtils.isEmpty(strSubstring)) {
            str2 = "snapchat";
        } else {
            String str3 = string;
            string = strSubstring;
            str2 = str3;
        }
        return getMessageInfo(str2, string, str);
    }

    public static NotificationInfo formCalendarNotificationGetMessageInfo(Notification notification, String str) {
        String strSubstring;
        String string;
        String string2;
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper formNotificationAndSend2Device " + notification.flags);
        try {
            if (Build.VERSION.SDK_INT > 18) {
                Bundle bundle = notification.extras;
                CharSequence string3 = bundle.getString(NotificationCompat.EXTRA_TITLE);
                if (string3 instanceof SpannableString) {
                    string = ((SpannableString) string3).subSequence(0, ((SpannableString) string3).length()).toString();
                } else {
                    string = bundle.getString(NotificationCompat.EXTRA_TITLE);
                }
                CharSequence charSequence = bundle.getCharSequence(NotificationCompat.EXTRA_TEXT);
                if (charSequence instanceof SpannableString) {
                    string2 = ((SpannableString) charSequence).subSequence(0, ((SpannableString) charSequence).length()).toString();
                } else {
                    string2 = bundle.getString(NotificationCompat.EXTRA_TEXT);
                }
                if (TextUtils.isEmpty(string2)) {
                    string2 = notification.tickerText.toString();
                }
                strSubstring = string2;
            } else {
                strSubstring = TextUtils.isEmpty(notification.tickerText) ? null : notification.tickerText.toString();
                string = null;
            }
        } catch (Exception unused) {
            strSubstring = "";
            string = strSubstring;
        }
        if (TextUtils.isEmpty(string)) {
            string = "";
        }
        if (TextUtils.isEmpty(strSubstring)) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，text为null，不提醒");
            return null;
        }
        if (!TextUtils.isEmpty(string) && strSubstring.startsWith(string)) {
            strSubstring = strSubstring.substring(string.length());
        }
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，title：" + string + "，text：" + strSubstring);
        if (string.equalsIgnoreCase("QQ")) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，QQ内部消息，，，，不返回");
            return null;
        }
        if (TextUtils.isEmpty(strSubstring) && TextUtils.isEmpty(string)) {
            return null;
        }
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 日历内容：" + string + strSubstring);
        if (isRepeatedCalendar(string + strSubstring)) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 重复日历，不提醒");
            return null;
        }
        lastCalendarContent = string + strSubstring;
        lastCalendarTimestamp = System.currentTimeMillis();
        return getMessageInfo(string, strSubstring, str);
    }

    public static NotificationInfo formTumBlrNotificationGetMessageInfo(Notification notification, String str) {
        String strSubstring;
        String string;
        String string2;
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper formNotificationAndSend2Device " + notification.flags);
        try {
            if (Build.VERSION.SDK_INT > 18) {
                Bundle bundle = notification.extras;
                CharSequence string3 = bundle.getString(NotificationCompat.EXTRA_TITLE);
                if (string3 instanceof SpannableString) {
                    string = ((SpannableString) string3).subSequence(0, ((SpannableString) string3).length()).toString();
                } else {
                    string = bundle.getString(NotificationCompat.EXTRA_TITLE);
                }
                CharSequence charSequence = bundle.getCharSequence(NotificationCompat.EXTRA_TEXT);
                if (charSequence instanceof SpannableString) {
                    string2 = ((SpannableString) charSequence).subSequence(0, ((SpannableString) charSequence).length()).toString();
                } else {
                    string2 = bundle.getString(NotificationCompat.EXTRA_TEXT);
                }
                if (TextUtils.isEmpty(string2)) {
                    string2 = notification.tickerText.toString();
                }
                strSubstring = string2;
            } else {
                strSubstring = TextUtils.isEmpty(notification.tickerText) ? null : notification.tickerText.toString();
                string = null;
            }
        } catch (Exception unused) {
            strSubstring = "";
            string = strSubstring;
        }
        if (TextUtils.isEmpty(string)) {
            string = "";
        }
        if (TextUtils.isEmpty(strSubstring)) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，text为null，不提醒");
            return null;
        }
        if (!TextUtils.isEmpty(string) && strSubstring.startsWith(string)) {
            strSubstring = strSubstring.substring(string.length());
        }
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，title：" + string + "，text：" + strSubstring);
        if (string.equalsIgnoreCase("QQ")) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，QQ内部消息，，，，不返回");
            return null;
        }
        if (TextUtils.isEmpty(strSubstring) && TextUtils.isEmpty(string)) {
            return null;
        }
        return getMessageInfo(string, strSubstring, str);
    }

    public static NotificationInfo formYouTuBeNotificationGetMessageInfo(Notification notification, String str) {
        String strSubstring;
        String string;
        String string2;
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper formNotificationAndSend2Device " + notification.flags);
        try {
            if (Build.VERSION.SDK_INT > 18) {
                Bundle bundle = notification.extras;
                CharSequence string3 = bundle.getString(NotificationCompat.EXTRA_TITLE);
                if (string3 instanceof SpannableString) {
                    string = ((SpannableString) string3).subSequence(0, ((SpannableString) string3).length()).toString();
                } else {
                    string = bundle.getString(NotificationCompat.EXTRA_TITLE);
                }
                CharSequence charSequence = bundle.getCharSequence(NotificationCompat.EXTRA_TEXT);
                if (charSequence instanceof SpannableString) {
                    string2 = ((SpannableString) charSequence).subSequence(0, ((SpannableString) charSequence).length()).toString();
                } else {
                    string2 = bundle.getString(NotificationCompat.EXTRA_TEXT);
                }
                if (TextUtils.isEmpty(string2)) {
                    string2 = notification.tickerText.toString();
                }
                strSubstring = string2;
            } else {
                strSubstring = TextUtils.isEmpty(notification.tickerText) ? null : notification.tickerText.toString();
                string = null;
            }
        } catch (Exception unused) {
            strSubstring = "";
            string = strSubstring;
        }
        if (TextUtils.isEmpty(string)) {
            string = "";
        }
        if (TextUtils.isEmpty(strSubstring)) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，text为null，不提醒");
            return null;
        }
        if (!TextUtils.isEmpty(string) && strSubstring.startsWith(string)) {
            strSubstring = strSubstring.substring(string.length());
        }
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，title：" + string + "，text：" + strSubstring);
        if (string.equalsIgnoreCase("QQ")) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，QQ内部消息，，，，不返回");
            return null;
        }
        if (TextUtils.isEmpty(strSubstring) && TextUtils.isEmpty(string)) {
            return null;
        }
        return getMessageInfo(string, strSubstring, str);
    }

    public static NotificationInfo formPinteRestNotificationGetMessageInfo(Notification notification, String str) {
        String strSubstring;
        String string;
        String string2;
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper formNotificationAndSend2Device " + notification.flags);
        try {
            if (Build.VERSION.SDK_INT > 18) {
                Bundle bundle = notification.extras;
                CharSequence string3 = bundle.getString(NotificationCompat.EXTRA_TITLE);
                if (string3 instanceof SpannableString) {
                    string = ((SpannableString) string3).subSequence(0, ((SpannableString) string3).length()).toString();
                } else {
                    string = bundle.getString(NotificationCompat.EXTRA_TITLE);
                }
                CharSequence charSequence = bundle.getCharSequence(NotificationCompat.EXTRA_TEXT);
                if (charSequence instanceof SpannableString) {
                    string2 = ((SpannableString) charSequence).subSequence(0, ((SpannableString) charSequence).length()).toString();
                } else {
                    string2 = bundle.getString(NotificationCompat.EXTRA_TEXT);
                }
                if (TextUtils.isEmpty(string2)) {
                    string2 = notification.tickerText.toString();
                }
                strSubstring = string2;
            } else {
                strSubstring = TextUtils.isEmpty(notification.tickerText) ? null : notification.tickerText.toString();
                string = null;
            }
        } catch (Exception unused) {
            strSubstring = "";
            string = strSubstring;
        }
        if (TextUtils.isEmpty(string)) {
            string = "";
        }
        if (TextUtils.isEmpty(strSubstring)) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，text为null，不提醒");
            return null;
        }
        if (!TextUtils.isEmpty(string) && strSubstring.startsWith(string)) {
            strSubstring = strSubstring.substring(string.length());
        }
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，title：" + string + "，text：" + strSubstring);
        if (string.equalsIgnoreCase("QQ")) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，QQ内部消息，，，，不返回");
            return null;
        }
        if (TextUtils.isEmpty(strSubstring) && TextUtils.isEmpty(string)) {
            return null;
        }
        return getMessageInfo(string, strSubstring, str);
    }

    public static NotificationInfo formTikTokNotificationGetMessageInfo(Notification notification, String str) {
        String strSubstring;
        String string;
        String string2;
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper formNotificationAndSend2Device " + notification.flags);
        try {
            if (Build.VERSION.SDK_INT > 18) {
                Bundle bundle = notification.extras;
                CharSequence string3 = bundle.getString(NotificationCompat.EXTRA_TITLE);
                if (string3 instanceof SpannableString) {
                    string = ((SpannableString) string3).subSequence(0, ((SpannableString) string3).length()).toString();
                } else {
                    string = bundle.getString(NotificationCompat.EXTRA_TITLE);
                }
                CharSequence charSequence = bundle.getCharSequence(NotificationCompat.EXTRA_TEXT);
                if (charSequence instanceof SpannableString) {
                    string2 = ((SpannableString) charSequence).subSequence(0, ((SpannableString) charSequence).length()).toString();
                } else {
                    string2 = bundle.getString(NotificationCompat.EXTRA_TEXT);
                }
                if (TextUtils.isEmpty(string2)) {
                    string2 = notification.tickerText.toString();
                }
                strSubstring = string2;
            } else {
                strSubstring = TextUtils.isEmpty(notification.tickerText) ? null : notification.tickerText.toString();
                string = null;
            }
        } catch (Exception unused) {
            strSubstring = "";
            string = strSubstring;
        }
        if (TextUtils.isEmpty(string)) {
            string = "";
        }
        if (TextUtils.isEmpty(strSubstring)) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，text为null，不提醒");
            return null;
        }
        if (!TextUtils.isEmpty(string) && strSubstring.startsWith(string)) {
            strSubstring = strSubstring.substring(string.length());
        }
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，title：" + string + "，text：" + strSubstring);
        if (string.equalsIgnoreCase("QQ")) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，QQ内部消息，，，，不返回");
            return null;
        }
        if (TextUtils.isEmpty(strSubstring) && TextUtils.isEmpty(string)) {
            return null;
        }
        return getMessageInfo(string, strSubstring, str);
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x0112 A[Catch: Exception -> 0x012c, TryCatch #0 {Exception -> 0x012c, blocks: (B:7:0x0034, B:9:0x003a, B:11:0x0045, B:13:0x005b, B:15:0x006a, B:17:0x006e, B:19:0x0074, B:21:0x0077, B:23:0x007a, B:25:0x007e, B:27:0x008c, B:53:0x010c, B:55:0x0112, B:28:0x0094, B:29:0x009e, B:32:0x00ab, B:34:0x00af, B:36:0x00bc, B:38:0x00c8, B:40:0x00cb, B:42:0x00ce, B:44:0x00d2, B:46:0x00dc, B:47:0x00df, B:48:0x00e4, B:49:0x00ee, B:51:0x00f6, B:52:0x0108, B:12:0x0057, B:57:0x011a, B:60:0x0124), top: B:87:0x0034 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.ido.ntf.bean.NotificationInfo formSmsNotificationGetMessageInfo(android.app.Notification r12, java.lang.String r13) {
        /*
            Method dump skipped, instruction units count: 414
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.ntf.notification.MsgNotificationHelper.formSmsNotificationGetMessageInfo(android.app.Notification, java.lang.String):com.ido.ntf.bean.NotificationInfo");
    }

    public static NotificationInfo formWhatsappBusinessNotificationGetMessageInfo(Notification notification, String str) {
        String strSubstring;
        String string;
        String string2;
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper formWhatsappBusinessNotificationGetMessageInfo " + notification.flags);
        try {
            if (Build.VERSION.SDK_INT > 18) {
                Bundle bundle = notification.extras;
                CharSequence string3 = bundle.getString(NotificationCompat.EXTRA_TITLE);
                if (string3 instanceof SpannableString) {
                    string = ((SpannableString) string3).subSequence(0, ((SpannableString) string3).length()).toString();
                } else {
                    string = bundle.getString(NotificationCompat.EXTRA_TITLE);
                }
                CharSequence charSequence = bundle.getCharSequence(NotificationCompat.EXTRA_TEXT);
                if (charSequence instanceof SpannableString) {
                    string2 = ((SpannableString) charSequence).subSequence(0, ((SpannableString) charSequence).length()).toString();
                } else {
                    string2 = bundle.getString(NotificationCompat.EXTRA_TEXT);
                }
                if (TextUtils.isEmpty(string2)) {
                    string2 = notification.tickerText.toString();
                }
                strSubstring = string2;
            } else {
                strSubstring = TextUtils.isEmpty(notification.tickerText) ? null : notification.tickerText.toString();
                string = null;
            }
        } catch (Exception unused) {
            strSubstring = "";
            string = strSubstring;
        }
        if (TextUtils.isEmpty(string)) {
            string = "";
        }
        if (TextUtils.isEmpty(strSubstring)) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，text为null，不提醒");
            return null;
        }
        if (!TextUtils.isEmpty(string) && strSubstring.startsWith(string)) {
            strSubstring = strSubstring.substring(string.length());
        }
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，title：" + string + "，text：" + strSubstring);
        if (string.equalsIgnoreCase("QQ")) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 智能提醒的信息，QQ内部消息，，，，不返回");
            return null;
        }
        if (TextUtils.isEmpty(strSubstring) && TextUtils.isEmpty(string)) {
            return null;
        }
        return getMessageInfo(string, strSubstring, str);
    }

    public static NotificationInfo getMessageInfo(String str, String str2, String str3) {
        NotificationInfo notificationInfo = new NotificationInfo();
        notificationInfo.name = str + "";
        notificationInfo.content = str2 + "";
        notificationInfo.pkgName = str3 + "";
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 返回提醒内容：" + notificationInfo.toString());
        return notificationInfo;
    }

    public static NotificationInfo getSMSNotificationInfo(String str, String str2, String str3) {
        if (isRepeatedSms(str2)) {
            LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 短信重复，不重新发");
            return null;
        }
        lastSmsContent = str2;
        lastSmsTimestamp = System.currentTimeMillis();
        NotificationInfo notificationInfo = new NotificationInfo();
        notificationInfo.name = str;
        notificationInfo.content = str2;
        notificationInfo.pkgName = str3;
        LogHandle.getInstance().printLog(" NotificationSDK  MsgNotificationHelper 返回提醒内容：" + notificationInfo.toString());
        return notificationInfo;
    }

    private static boolean isRepeatedSms(String str) {
        return System.currentTimeMillis() - lastSmsTimestamp <= DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT && !TextUtils.isEmpty(lastSmsContent) && !TextUtils.isEmpty(str) && lastSmsContent.equals(str);
    }

    private static boolean isRepeatedCalendar(String str) {
        return System.currentTimeMillis() - lastCalendarTimestamp <= 30000 && !TextUtils.isEmpty(lastCalendarContent) && !TextUtils.isEmpty(str) && lastCalendarContent.equals(str);
    }

    private static void saveNotification(Notification notification) {
        if (sNotificationMap == null) {
            sNotificationMap = new HashMap();
        }
        msgID++;
        sNotificationMap.put(Integer.valueOf(msgID), notification);
    }
}