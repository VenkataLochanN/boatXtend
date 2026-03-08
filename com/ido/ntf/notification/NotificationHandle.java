package com.ido.ntf.notification;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Telephony;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.life.constants.Constants;
import com.ido.ntf.Utils.CommUtils;
import com.ido.ntf.Utils.NotificationUtil;
import com.ido.ntf.Utils.PkgNameUtil;
import com.ido.ntf.bean.NotificationInfo;
import com.ido.ntf.callback.INotificationBack;
import com.ido.ntf.log.LogHandle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: loaded from: classes3.dex */
public class NotificationHandle {
    public static final int FLAG_AUTOGROUP_SUMMARY = 1024;
    private static final String IN_CALL_SERVICE = "android.telecom.InCallService";
    static final int NOTIFICATION_FLAG_FILTER = 1634;
    private static NotificationHandle NotificationHandle = null;
    private static final String TAG = " NotificationSDK  NotificationHandle";
    private static String content;
    private static String lastTextContent;
    private static Context mContext;
    private Map<String, String> cachedMinSortKey = new HashMap();
    private Map<String, Long> cachedNotificationWhen = new HashMap();
    private Map<String, Runnable> cachedDelayRunnable = new HashMap();
    Handler mDelayHandler = new Handler();
    long mPredelay = 500;

    private NotificationHandle() {
    }

    public static synchronized NotificationHandle getInstance(Context context) {
        if (NotificationHandle == null) {
            mContext = context;
            NotificationHandle = new NotificationHandle();
        }
        return NotificationHandle;
    }

    public void filterNotifications(StatusBarNotification statusBarNotification, int i, boolean z, INotificationBack iNotificationBack) {
        try {
            Notification notification = statusBarNotification.getNotification();
            CommUtils.setIsNotificationException(true);
            CommUtils.put(mContext, "notification_time", Long.valueOf(System.currentTimeMillis()));
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle 当前时间：" + System.currentTimeMillis());
            if (notification == null) {
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle 通知为空");
                return;
            }
            if (i == 2) {
                if (!TextUtils.isEmpty(notification.extras.getString(NotificationCompat.EXTRA_TITLE)) && notification.extras.getString(NotificationCompat.EXTRA_TITLE).contains("服务")) {
                    LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle 收到判断服务是否异常的通知");
                }
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle type == 2 return");
                return;
            }
            String packageName = statusBarNotification.getPackageName();
            if (i == 1) {
                if (CommUtils.isOpenPhoneStateListener) {
                    NotificationUtil.addNotifications(notification, getPhonePkgName(), packageName);
                }
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle type == 1 return");
                return;
            }
            String str = (String) notification.tickerText;
            String string = notification.extras.getString(NotificationCompat.EXTRA_TITLE);
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle tickerText:" + str + ";title=" + string);
            CommUtils.setIsNotificationException(true);
            if (packageName.equals("com.github.kr328.clash")) {
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandlecom.github.kr328.clash返回");
                return;
            }
            if (Build.VERSION.SDK_INT >= 26) {
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle 收到通知---title" + string + "----id" + notification.getChannelId());
            }
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle pkgName：" + packageName + "，tickerText：" + str + "，notification：" + notification.toString());
            if ("com.tencent.mm".equals(packageName)) {
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle 微信收到消息:" + notification.flags);
                iNotificationBack.onFilterNotificationResult(getWXMessageInfo(notification, packageName));
                return;
            }
            if (PkgNameUtil.AppPackage.QQ_PKG_LIST.contains(packageName)) {
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle QQ收到消息:" + notification.flags);
                iNotificationBack.onFilterNotificationResult(getQQMessageInfo(notification, packageName));
                return;
            }
            if ("com.facebook.katana".equals(packageName)) {
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle received FaceBook notification");
                iNotificationBack.onFilterNotificationResult(getFaceBookMessageInfo(notification, packageName));
                return;
            }
            if (PkgNameUtil.AppPackage.TWITTER_LIST.contains(packageName)) {
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle received Twitter notification");
                handleTwitterNotificationPost(iNotificationBack, statusBarNotification, packageName);
                return;
            }
            if ("com.whatsapp".equals(packageName)) {
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle received WhatsApp notification，notification.flags = " + notification.flags);
                handleWhatsAppNotificationPost(iNotificationBack, statusBarNotification, packageName);
                return;
            }
            if ("com.linkedin.android".equals(packageName)) {
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle received Linkedin notification");
                iNotificationBack.onFilterNotificationResult(getLinKeDinMessageInfo(notification, packageName));
                return;
            }
            if ("com.instagram.android".equals(packageName)) {
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle received Instagram notification");
                iNotificationBack.onFilterNotificationResult(getInsTaGramMessageInfo(notification, packageName));
                return;
            }
            if ("com.facebook.orca".equals(packageName)) {
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle received Messenger notification，notification.tickerText = " + ((Object) notification.tickerText));
                handleMessengerNotificationPost(iNotificationBack, statusBarNotification, packageName);
                return;
            }
            if (!getEmailPkgNames().contains(packageName) && !packageName.contains("email")) {
                if ("com.kakao.talk".equals(packageName)) {
                    LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle received Kakaotalk notification");
                    iNotificationBack.onFilterNotificationResult(getKaKaoTalkMessageInfo(notification, packageName));
                    return;
                }
                if ("com.viber.voip".equals(packageName)) {
                    LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle received Viber notification");
                    iNotificationBack.onFilterNotificationResult(getViBerMessageInfo(notification, packageName));
                    return;
                }
                if (PkgNameUtil.AppPackage.LINE_PKG_LIST.contains(packageName)) {
                    LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle received Line notification，notification.flags = " + notification.flags);
                    iNotificationBack.onFilterNotificationResult(getLinePkgListMessageInfo(notification, packageName));
                    return;
                }
                if ("com.vkontakte.android".equals(packageName)) {
                    LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle received Vkontakte notification");
                    iNotificationBack.onFilterNotificationResult(getVkonTakteMessageInfo(notification, packageName));
                    return;
                }
                if (!packageName.contains("com.skype") && !packageName.contains("com.skype.insiders")) {
                    if (!"com.google.android.gm".equals(packageName) && !"com.google.android.gm.lite".equals(packageName)) {
                        if ("com.snapchat.android".equals(packageName)) {
                            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle received Snapchat notification");
                            iNotificationBack.onFilterNotificationResult(getSnapchatMessageInfo(notification, packageName));
                            return;
                        }
                        if (!packageName.contains(Constants.AppPackage.CALENDAR) && !getCalendarPkgNames().contains(packageName)) {
                            if ("com.tumblr".equals(packageName)) {
                                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle received Tumblr notification");
                                iNotificationBack.onFilterNotificationResult(getTumBlrMessageInfo(notification, packageName));
                                return;
                            }
                            if ("com.google.android.youtube".equals(packageName)) {
                                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle received Youtube notification");
                                iNotificationBack.onFilterNotificationResult(getYouTuBeMessageInfo(notification, packageName));
                                return;
                            }
                            if ("com.pinterest".equals(packageName)) {
                                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle received Pinterest notification");
                                iNotificationBack.onFilterNotificationResult(getPinteRestMessageInfo(notification, packageName));
                                return;
                            }
                            if ("org.telegram.messenger".equals(packageName)) {
                                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle received Telegram notification，notification.flags = " + notification.flags);
                                handleTelegramNotificationPost(iNotificationBack, statusBarNotification, packageName);
                                return;
                            }
                            if (!"com.zhiliaoapp.musically".equals(packageName) && !"com.ss.android.ugc.trill".equals(packageName)) {
                                if (!packageName.contains(getSmsPkgName()) && !PkgNameUtil.AppPackage.SMS_PKG_LIST.contains(packageName)) {
                                    if ("com.whatsapp.w4b".equals(packageName)) {
                                        LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle received WhatsApp Business notification");
                                        handleWhatsAppNotificationPost(iNotificationBack, statusBarNotification, packageName);
                                        return;
                                    } else {
                                        if (z) {
                                            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle received " + packageName);
                                            iNotificationBack.onFilterNotificationResult(getMessageInfo(notification, packageName));
                                            return;
                                        }
                                        return;
                                    }
                                }
                                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle received SMS notification，notification.flags = " + notification.flags);
                                iNotificationBack.onFilterNotificationResult(getSmsMessageInfo(notification, packageName));
                                return;
                            }
                            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle received Tiktok notification");
                            iNotificationBack.onFilterNotificationResult(getTikTokMessageInfo(notification, packageName));
                            return;
                        }
                        LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle received Calendar notification");
                        iNotificationBack.onFilterNotificationResult(getCalendarMessageInfo(notification, packageName));
                        return;
                    }
                    LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle received GMail notification");
                    handleGmailNotificationPost(iNotificationBack, statusBarNotification, packageName);
                    return;
                }
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle received Skype notification");
                handleSkyPeNotificationPost(iNotificationBack, statusBarNotification, packageName);
                return;
            }
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle received Email notification");
            handleEmailNotificationPost(iNotificationBack, statusBarNotification, packageName);
        } catch (Exception e2) {
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle 通知处理异常");
            e2.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0080 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String handleNotificationPost(android.service.notification.StatusBarNotification r8, int r9, int r10, java.lang.String r11) {
        /*
            Method dump skipped, instruction units count: 419
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.ntf.notification.NotificationHandle.handleNotificationPost(android.service.notification.StatusBarNotification, int, int, java.lang.String):java.lang.String");
    }

    public /* synthetic */ void lambda$handleNotificationPost$0$NotificationHandle() {
        this.mPredelay = 0L;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00e4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00e5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String handleWhatsAppNotificationPost(final com.ido.ntf.callback.INotificationBack r12, android.service.notification.StatusBarNotification r13, final java.lang.String r14) {
        /*
            Method dump skipped, instruction units count: 748
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.ntf.notification.NotificationHandle.handleWhatsAppNotificationPost(com.ido.ntf.callback.INotificationBack, android.service.notification.StatusBarNotification, java.lang.String):java.lang.String");
    }

    public /* synthetic */ void lambda$handleWhatsAppNotificationPost$1$NotificationHandle(boolean z, INotificationBack iNotificationBack, Notification notification, String str) {
        LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle NotificationService: runnable执行");
        if (!z) {
            this.mPredelay = 0L;
        }
        iNotificationBack.onFilterNotificationResult(getWhatsAppMessageInfo(notification, str));
    }

    public static boolean actionWhatsMessage(Notification notification) {
        try {
            if (Build.VERSION.SDK_INT > 18) {
                Bundle bundle = notification.extras;
                Object obj = bundle.get(NotificationCompat.EXTRA_TEXT_LINES);
                bundle.get(NotificationCompat.EXTRA_BIG_TEXT);
                if (obj != null && (obj instanceof CharSequence[])) {
                    CharSequence[] charSequenceArr = (CharSequence[]) obj;
                    if (charSequenceArr == null || charSequenceArr.length <= 0) {
                        return true;
                    }
                    StringBuffer stringBuffer = new StringBuffer();
                    for (CharSequence charSequence : charSequenceArr) {
                        stringBuffer.append(charSequence.toString() + AppInfo.DELIM);
                    }
                    content = stringBuffer.toString();
                    LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle NotificationService: 具体内容:" + content);
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    private String handleTwitterNotificationPost(final INotificationBack iNotificationBack, StatusBarNotification statusBarNotification, final String str) {
        boolean zEquals;
        String str2;
        final Notification notification = statusBarNotification.getNotification();
        if (notification != null) {
            if (!str.contains("com.skype") && !str.contains("com.skype.insiders") && !str.contains("com.skype.insiders") && (notification.flags & NOTIFICATION_FLAG_FILTER) != 0 && !str.contains("org.telegram.messenger")) {
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle 逻辑判断对skype不适用，skype的来电和视频符合条件会被误判,return null");
                return null;
            }
            String groupKey = statusBarNotification.getGroupKey();
            String sortKey = statusBarNotification.getNotification().getSortKey();
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle groupKey====：" + groupKey);
            if (!TextUtils.isEmpty(groupKey)) {
                boolean zEquals2 = TextUtils.equals("org.telegram.messenger", str);
                if (zEquals2) {
                    str2 = str;
                } else {
                    str2 = str + groupKey;
                }
                String str3 = this.cachedMinSortKey.get(str2);
                if (TextUtils.isEmpty(str3) || sortKey == null) {
                    this.mPredelay = 1200L;
                } else {
                    if (str3 != null && str3.compareTo(sortKey) <= 0) {
                        if (str3 == null || (str3.compareTo(sortKey) == 0 && zEquals2)) {
                            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle minSortedKey,return null");
                            return null;
                        }
                    }
                    zEquals = TextUtils.equals(str3, sortKey);
                    this.cachedMinSortKey.put(str2, str3);
                }
                str3 = sortKey;
                zEquals = TextUtils.equals(str3, sortKey);
                this.cachedMinSortKey.put(str2, str3);
            } else {
                this.mPredelay = 300L;
                Long l = this.cachedNotificationWhen.get(statusBarNotification.getPackageName());
                zEquals = l == null || statusBarNotification.getNotification().when > l.longValue();
            }
            if (zEquals) {
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle shouldPost = true");
                Runnable runnable = this.cachedDelayRunnable.get(statusBarNotification.getPackageName());
                if (runnable != null) {
                    this.mDelayHandler.removeCallbacks(runnable);
                }
                Runnable runnable2 = new Runnable() { // from class: com.ido.ntf.notification.-$$Lambda$NotificationHandle$ohb5b37Otzgs8_9_3njEkAIQKPA
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$handleTwitterNotificationPost$2$NotificationHandle(iNotificationBack, notification, str);
                    }
                };
                this.mDelayHandler.postDelayed(runnable2, this.mPredelay);
                this.cachedDelayRunnable.put(statusBarNotification.getPackageName(), runnable2);
                this.cachedNotificationWhen.put(statusBarNotification.getPackageName(), Long.valueOf(statusBarNotification.getNotification().when));
            } else {
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle shouldPost = false");
            }
        }
        return null;
    }

    public /* synthetic */ void lambda$handleTwitterNotificationPost$2$NotificationHandle(INotificationBack iNotificationBack, Notification notification, String str) {
        this.mPredelay = 0L;
        iNotificationBack.onFilterNotificationResult(getTwiTTerMessageInfo(notification, str));
    }

    private String handleLinKeDinNotificationPost(final INotificationBack iNotificationBack, StatusBarNotification statusBarNotification, final String str) {
        boolean zEquals;
        final Notification notification = statusBarNotification.getNotification();
        if (notification == null || !((notification.flags & NOTIFICATION_FLAG_FILTER) == 0 || str.contains("org.telegram.messenger"))) {
            return null;
        }
        String groupKey = statusBarNotification.getGroupKey();
        String sortKey = statusBarNotification.getNotification().getSortKey();
        LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle groupKey====：" + groupKey);
        if (!TextUtils.isEmpty(groupKey)) {
            String str2 = str + groupKey;
            String str3 = this.cachedMinSortKey.get(str2);
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle isGmail = false");
            if (TextUtils.isEmpty(str3) || sortKey == null) {
                this.mPredelay = 1200L;
            } else {
                if (str3 == null || str3.compareTo(sortKey) > 0) {
                }
                zEquals = TextUtils.equals(str3, sortKey);
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle  minSortedKey = " + str3 + "\t sortkey = " + sortKey + "  shouldPost= " + zEquals);
                this.cachedMinSortKey.put(str2, str3);
            }
            str3 = sortKey;
            zEquals = TextUtils.equals(str3, sortKey);
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle  minSortedKey = " + str3 + "\t sortkey = " + sortKey + "  shouldPost= " + zEquals);
            this.cachedMinSortKey.put(str2, str3);
        } else {
            this.mPredelay = 300L;
            Long l = this.cachedNotificationWhen.get(statusBarNotification.getPackageName());
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle  lastWhen = " + l + "\t when = " + statusBarNotification.getNotification().when);
            zEquals = l == null || statusBarNotification.getNotification().when > l.longValue();
        }
        if (zEquals) {
            Runnable runnable = this.cachedDelayRunnable.get(statusBarNotification.getPackageName());
            if (runnable != null) {
                this.mDelayHandler.removeCallbacks(runnable);
            }
            Runnable runnable2 = new Runnable() { // from class: com.ido.ntf.notification.-$$Lambda$NotificationHandle$dd7cSg2ODh0gsu_3qryNNu8Hfck
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$handleLinKeDinNotificationPost$3$NotificationHandle(iNotificationBack, notification, str);
                }
            };
            this.mDelayHandler.postDelayed(runnable2, this.mPredelay);
            this.cachedDelayRunnable.put(statusBarNotification.getPackageName(), runnable2);
            this.cachedNotificationWhen.put(statusBarNotification.getPackageName(), Long.valueOf(statusBarNotification.getNotification().when));
        } else {
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle shouldPost = false");
        }
        return null;
    }

    public /* synthetic */ void lambda$handleLinKeDinNotificationPost$3$NotificationHandle(INotificationBack iNotificationBack, Notification notification, String str) {
        this.mPredelay = 0L;
        iNotificationBack.onFilterNotificationResult(getLinKeDinMessageInfo(notification, str));
    }

    private String handleMessengerNotificationPost(final INotificationBack iNotificationBack, StatusBarNotification statusBarNotification, final String str) {
        boolean zEquals;
        final Notification notification = statusBarNotification.getNotification();
        if (notification == null || !((notification.flags & NOTIFICATION_FLAG_FILTER) == 0 || str.contains("org.telegram.messenger"))) {
            return null;
        }
        String groupKey = statusBarNotification.getGroupKey();
        String sortKey = statusBarNotification.getNotification().getSortKey();
        LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle groupKey====：" + groupKey);
        if (!TextUtils.isEmpty(groupKey)) {
            String str2 = str + groupKey;
            String str3 = this.cachedMinSortKey.get(str2);
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle isGmail = false");
            if (TextUtils.isEmpty(str3) || sortKey == null) {
                this.mPredelay = 1200L;
            } else {
                if (str3 == null || str3.compareTo(sortKey) > 0) {
                }
                zEquals = TextUtils.equals(str3, sortKey);
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle  minSortedKey = " + str3 + "\t sortkey = " + sortKey + "  shouldPost= " + zEquals);
                this.cachedMinSortKey.put(str2, str3);
            }
            str3 = sortKey;
            zEquals = TextUtils.equals(str3, sortKey);
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle  minSortedKey = " + str3 + "\t sortkey = " + sortKey + "  shouldPost= " + zEquals);
            this.cachedMinSortKey.put(str2, str3);
        } else {
            this.mPredelay = 300L;
            Long l = this.cachedNotificationWhen.get(statusBarNotification.getPackageName());
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle  lastWhen = " + l + "\t when = " + statusBarNotification.getNotification().when);
            zEquals = l == null || statusBarNotification.getNotification().when > l.longValue();
        }
        if (zEquals) {
            Runnable runnable = this.cachedDelayRunnable.get(statusBarNotification.getPackageName());
            if (runnable != null) {
                this.mDelayHandler.removeCallbacks(runnable);
            }
            Runnable runnable2 = new Runnable() { // from class: com.ido.ntf.notification.-$$Lambda$NotificationHandle$XRGQvZBnMhPiDYG0oM1TmU8KQ1E
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$handleMessengerNotificationPost$4$NotificationHandle(iNotificationBack, notification, str);
                }
            };
            this.mDelayHandler.postDelayed(runnable2, this.mPredelay);
            this.cachedDelayRunnable.put(statusBarNotification.getPackageName(), runnable2);
            this.cachedNotificationWhen.put(statusBarNotification.getPackageName(), Long.valueOf(statusBarNotification.getNotification().when));
        } else {
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle shouldPost = false");
        }
        return null;
    }

    public /* synthetic */ void lambda$handleMessengerNotificationPost$4$NotificationHandle(INotificationBack iNotificationBack, Notification notification, String str) {
        this.mPredelay = 0L;
        iNotificationBack.onFilterNotificationResult(getMessengerMessageInfo(notification, str));
    }

    private String handleEmailNotificationPost(final INotificationBack iNotificationBack, StatusBarNotification statusBarNotification, final String str) {
        final Notification notification = statusBarNotification.getNotification();
        if (notification == null || !((notification.flags & NOTIFICATION_FLAG_FILTER) == 0 || str.contains("org.telegram.messenger"))) {
            return null;
        }
        String groupKey = statusBarNotification.getGroupKey();
        String sortKey = statusBarNotification.getNotification().getSortKey();
        LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle groupKey====：" + groupKey);
        if (!TextUtils.isEmpty(groupKey)) {
            String str2 = str + groupKey;
            String str3 = this.cachedMinSortKey.get(str2);
            if (TextUtils.equals("com.google.android.gm", statusBarNotification.getPackageName()) || TextUtils.equals("com.google.android.gm.lite", statusBarNotification.getPackageName())) {
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle isGmail = true");
                zEquals = str3 == null || sortKey == null || str3.compareTo(sortKey) == 1;
                if (zEquals) {
                    str3 = sortKey;
                }
                this.mPredelay = 2000L;
            } else {
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle isGmail = false");
                if (TextUtils.isEmpty(str3) || sortKey == null) {
                    this.mPredelay = 1200L;
                } else {
                    if (str3 == null || str3.compareTo(sortKey) > 0) {
                    }
                    zEquals = TextUtils.equals(str3, sortKey);
                }
                str3 = sortKey;
                zEquals = TextUtils.equals(str3, sortKey);
            }
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle  minSortedKey = " + str3 + "\t sortkey = " + sortKey + "  shouldPost= " + zEquals);
            this.cachedMinSortKey.put(str2, str3);
        } else {
            this.mPredelay = 300L;
            Long l = this.cachedNotificationWhen.get(statusBarNotification.getPackageName());
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle  lastWhen = " + l + "\t when = " + statusBarNotification.getNotification().when);
            if (l == null || statusBarNotification.getNotification().when > l.longValue()) {
                zEquals = true;
            }
        }
        if (zEquals) {
            Runnable runnable = this.cachedDelayRunnable.get(statusBarNotification.getPackageName());
            if (runnable != null) {
                this.mDelayHandler.removeCallbacks(runnable);
            }
            Runnable runnable2 = new Runnable() { // from class: com.ido.ntf.notification.-$$Lambda$NotificationHandle$z1kN8iveghGlmSBTrMhXWv_lmb8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$handleEmailNotificationPost$5$NotificationHandle(iNotificationBack, notification, str);
                }
            };
            this.mDelayHandler.postDelayed(runnable2, this.mPredelay);
            this.cachedDelayRunnable.put(statusBarNotification.getPackageName(), runnable2);
            this.cachedNotificationWhen.put(statusBarNotification.getPackageName(), Long.valueOf(statusBarNotification.getNotification().when));
        } else {
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle shouldPost = false");
        }
        return null;
    }

    public /* synthetic */ void lambda$handleEmailNotificationPost$5$NotificationHandle(INotificationBack iNotificationBack, Notification notification, String str) {
        this.mPredelay = 0L;
        iNotificationBack.onFilterNotificationResult(getEmailMessageInfo(notification, str));
    }

    private String handleSkyPeNotificationPost(final INotificationBack iNotificationBack, StatusBarNotification statusBarNotification, final String str) {
        boolean zEquals;
        final Notification notification = statusBarNotification.getNotification();
        if (notification.flags == 529) {
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle SKYPE重复被提醒");
            return null;
        }
        if (notification != null) {
            String groupKey = statusBarNotification.getGroupKey();
            String sortKey = statusBarNotification.getNotification().getSortKey();
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle groupKey====：" + groupKey);
            if (!TextUtils.isEmpty(groupKey)) {
                String str2 = str + groupKey;
                String str3 = this.cachedMinSortKey.get(str2);
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle SkyPe = SkyPe");
                if (TextUtils.isEmpty(str3) || sortKey == null) {
                    this.mPredelay = 1200L;
                } else {
                    if (str3 == null || str3.compareTo(sortKey) > 0) {
                    }
                    zEquals = TextUtils.equals(str3, sortKey);
                    this.cachedMinSortKey.put(str2, str3);
                }
                str3 = sortKey;
                zEquals = TextUtils.equals(str3, sortKey);
                this.cachedMinSortKey.put(str2, str3);
            } else {
                this.mPredelay = 300L;
                Long l = this.cachedNotificationWhen.get(statusBarNotification.getPackageName());
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle  lastWhen = " + l + "\t when = " + statusBarNotification.getNotification().when);
                zEquals = l == null || statusBarNotification.getNotification().when > l.longValue();
            }
            if (zEquals) {
                Runnable runnable = this.cachedDelayRunnable.get(statusBarNotification.getPackageName());
                if (runnable != null) {
                    this.mDelayHandler.removeCallbacks(runnable);
                }
                Runnable runnable2 = new Runnable() { // from class: com.ido.ntf.notification.-$$Lambda$NotificationHandle$bgNivUDw54WqvUIvtBu-SYZC75c
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$handleSkyPeNotificationPost$6$NotificationHandle(iNotificationBack, notification, str);
                    }
                };
                this.mDelayHandler.postDelayed(runnable2, this.mPredelay);
                this.cachedDelayRunnable.put(statusBarNotification.getPackageName(), runnable2);
                this.cachedNotificationWhen.put(statusBarNotification.getPackageName(), Long.valueOf(statusBarNotification.getNotification().when));
            } else {
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle shouldPost = false");
            }
        }
        return null;
    }

    public /* synthetic */ void lambda$handleSkyPeNotificationPost$6$NotificationHandle(INotificationBack iNotificationBack, Notification notification, String str) {
        this.mPredelay = 0L;
        iNotificationBack.onFilterNotificationResult(getMessageInfo(notification, str));
    }

    private String handleGmailNotificationPost(final INotificationBack iNotificationBack, StatusBarNotification statusBarNotification, final String str) {
        final Notification notification = statusBarNotification.getNotification();
        if (notification == null || (notification.flags & NOTIFICATION_FLAG_FILTER) != 0) {
            return null;
        }
        String groupKey = statusBarNotification.getGroupKey();
        String sortKey = statusBarNotification.getNotification().getSortKey();
        LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle groupKey====：" + groupKey);
        if (!TextUtils.isEmpty(groupKey)) {
            String str2 = str + groupKey;
            String str3 = this.cachedMinSortKey.get(str2);
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle isGmail = true");
            z = str3 == null || sortKey == null || str3.compareTo(sortKey) == 1;
            if (z) {
                str3 = sortKey;
            }
            this.mPredelay = 2000L;
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle  minSortedKey = " + str3 + "\t sortkey = " + sortKey + "  shouldPost= " + z);
            this.cachedMinSortKey.put(str2, str3);
        } else {
            this.mPredelay = 300L;
            Long l = this.cachedNotificationWhen.get(statusBarNotification.getPackageName());
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle  lastWhen = " + l + "\t when = " + statusBarNotification.getNotification().when);
            if (l == null || statusBarNotification.getNotification().when > l.longValue()) {
                z = true;
            }
        }
        if (z) {
            Runnable runnable = this.cachedDelayRunnable.get(statusBarNotification.getPackageName());
            if (runnable != null) {
                this.mDelayHandler.removeCallbacks(runnable);
            }
            Runnable runnable2 = new Runnable() { // from class: com.ido.ntf.notification.-$$Lambda$NotificationHandle$jP19XyurnXV44DLFLdiJ-WYoMew
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$handleGmailNotificationPost$7$NotificationHandle(iNotificationBack, notification, str);
                }
            };
            this.mDelayHandler.postDelayed(runnable2, this.mPredelay);
            this.cachedDelayRunnable.put(statusBarNotification.getPackageName(), runnable2);
            this.cachedNotificationWhen.put(statusBarNotification.getPackageName(), Long.valueOf(statusBarNotification.getNotification().when));
        } else {
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle shouldPost = false");
        }
        return null;
    }

    public /* synthetic */ void lambda$handleGmailNotificationPost$7$NotificationHandle(INotificationBack iNotificationBack, Notification notification, String str) {
        this.mPredelay = 0L;
        iNotificationBack.onFilterNotificationResult(getMessageInfo(notification, str));
    }

    private String handleTelegramNotificationPost(final INotificationBack iNotificationBack, StatusBarNotification statusBarNotification, final String str) {
        boolean zEquals;
        final Notification notification = statusBarNotification.getNotification();
        if (notification.flags != 98 && notification != null) {
            String groupKey = statusBarNotification.getGroupKey();
            String sortKey = statusBarNotification.getNotification().getSortKey();
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle groupKey====：" + groupKey + "--sortKey--" + groupKey);
            if (!TextUtils.isEmpty(groupKey)) {
                String str2 = this.cachedMinSortKey.get(str);
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle Telegram = Telegram");
                if (TextUtils.isEmpty(str2) || sortKey == null) {
                    this.mPredelay = 1200L;
                } else {
                    if (str2 != null && str2.compareTo(sortKey) <= 0) {
                        if (str2 == null || str2.compareTo(sortKey) == 0) {
                            return null;
                        }
                    }
                    zEquals = TextUtils.equals(str2, sortKey);
                    this.cachedMinSortKey.put(str, str2);
                }
                str2 = sortKey;
                zEquals = TextUtils.equals(str2, sortKey);
                this.cachedMinSortKey.put(str, str2);
            } else {
                this.mPredelay = 300L;
                Long l = this.cachedNotificationWhen.get(statusBarNotification.getPackageName());
                zEquals = l == null || statusBarNotification.getNotification().when > l.longValue();
            }
            if (zEquals) {
                Runnable runnable = this.cachedDelayRunnable.get(statusBarNotification.getPackageName());
                if (runnable != null) {
                    this.mDelayHandler.removeCallbacks(runnable);
                }
                Runnable runnable2 = new Runnable() { // from class: com.ido.ntf.notification.-$$Lambda$NotificationHandle$zSOyJ_zHeOuEfA4YCLX0yP-ldEg
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$handleTelegramNotificationPost$8$NotificationHandle(iNotificationBack, notification, str);
                    }
                };
                this.mDelayHandler.postDelayed(runnable2, this.mPredelay);
                this.cachedDelayRunnable.put(statusBarNotification.getPackageName(), runnable2);
                this.cachedNotificationWhen.put(statusBarNotification.getPackageName(), Long.valueOf(statusBarNotification.getNotification().when));
            } else {
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle shouldPost = false");
            }
        }
        return null;
    }

    public /* synthetic */ void lambda$handleTelegramNotificationPost$8$NotificationHandle(INotificationBack iNotificationBack, Notification notification, String str) {
        this.mPredelay = 0L;
        iNotificationBack.onFilterNotificationResult(getMessageInfo(notification, str));
    }

    private String handleLineNotificationPost(final INotificationBack iNotificationBack, StatusBarNotification statusBarNotification, final String str) {
        boolean zEquals;
        final Notification notification = statusBarNotification.getNotification();
        if (notification.flags == 529 || notification.flags == 536) {
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle notification.flags" + notification.flags);
            return null;
        }
        if (notification != null) {
            String groupKey = statusBarNotification.getGroupKey();
            String sortKey = statusBarNotification.getNotification().getSortKey();
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle groupKey====：" + groupKey + "--sortKey--" + groupKey);
            if (!TextUtils.isEmpty(groupKey)) {
                String str2 = this.cachedMinSortKey.get(str);
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle minSortedKey====：" + str2);
                if (TextUtils.isEmpty(str2) || sortKey == null) {
                    this.mPredelay = BootloaderScanner.TIMEOUT;
                } else {
                    if (str2 != null && str2.compareTo(sortKey) <= 0) {
                        if (str2 == null || str2.compareTo(sortKey) == 0) {
                            return null;
                        }
                    }
                    zEquals = TextUtils.equals(str2, sortKey);
                    this.cachedMinSortKey.put(str, str2);
                }
                str2 = sortKey;
                zEquals = TextUtils.equals(str2, sortKey);
                this.cachedMinSortKey.put(str, str2);
            } else {
                this.mPredelay = 300L;
                Long l = this.cachedNotificationWhen.get(statusBarNotification.getPackageName());
                zEquals = l == null || statusBarNotification.getNotification().when > l.longValue();
            }
            if (zEquals) {
                Runnable runnable = this.cachedDelayRunnable.get(statusBarNotification.getPackageName());
                if (runnable != null) {
                    this.mDelayHandler.removeCallbacks(runnable);
                }
                Runnable runnable2 = new Runnable() { // from class: com.ido.ntf.notification.-$$Lambda$NotificationHandle$lY7WhpjbM0jerdbTXv2jscSuOQE
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$handleLineNotificationPost$9$NotificationHandle(iNotificationBack, notification, str);
                    }
                };
                this.mDelayHandler.postDelayed(runnable2, this.mPredelay);
                this.cachedDelayRunnable.put(statusBarNotification.getPackageName(), runnable2);
                this.cachedNotificationWhen.put(statusBarNotification.getPackageName(), Long.valueOf(statusBarNotification.getNotification().when));
            } else {
                LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle shouldPost = false");
            }
        }
        return null;
    }

    public /* synthetic */ void lambda$handleLineNotificationPost$9$NotificationHandle(INotificationBack iNotificationBack, Notification notification, String str) {
        this.mPredelay = 0L;
        iNotificationBack.onFilterNotificationResult(getMessageInfo(notification, str));
    }

    public ArrayList<String> getCalendarPkgNames() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("com.android.calendar");
        arrayList.add("com.htc.calendar");
        arrayList.add(Constants.AppPackage.CALENDAR);
        return arrayList;
    }

    public ArrayList<String> getEmailPkgNames() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("com.tencent.androidqqmail");
        arrayList.add("com.netease.mobimail");
        arrayList.add("com.netease.mail");
        arrayList.add("com.google.android.gm");
        arrayList.add("com.my.mail");
        arrayList.add("com.microsoft.office.outlook");
        arrayList.add("com.trtf.blue");
        arrayList.add("me.bluemail.mail");
        arrayList.add("com.motorola.email");
        arrayList.add("com.htc.android.mail");
        arrayList.add("com.google.android.apps.inbox");
        arrayList.add("com.asus.email");
        arrayList.add("jp.co.yahoo.android.ymail");
        arrayList.add("com.fuzixx.dokidokipostbox");
        arrayList.add("ru.mail.mailapp");
        arrayList.add("air.kukulive.mailnow");
        arrayList.add("com.mail.emails");
        arrayList.add("com.nhn.android.mail");
        arrayList.add("com.zoho.mail");
        arrayList.add("com.syntomo.email");
        arrayList.add("com.corp21cn.mail189");
        arrayList.add("com.email.email");
        arrayList.add("com.motorola.blur.email");
        arrayList.add("com.jdex.gmail");
        return arrayList;
    }

    private NotificationInfo getMessageInfo(Notification notification, String str) {
        return MsgNotificationHelper.formNotificationGetMessageInfo(notification, str);
    }

    private NotificationInfo getMessengerMessageInfo(Notification notification, String str) {
        return MsgNotificationHelper.formMessengerNotificationGetMessageInfo(notification, str);
    }

    private NotificationInfo getQQMessageInfo(Notification notification, String str) {
        return MsgNotificationHelper.formQQNotificationGetMessageInfo(notification, str);
    }

    private NotificationInfo getWXMessageInfo(Notification notification, String str) {
        return MsgNotificationHelper.formWXNotificationGetMessageInfo(notification, str);
    }

    private NotificationInfo getFaceBookMessageInfo(Notification notification, String str) {
        return MsgNotificationHelper.formFaceBookNotificationGetMessageInfo(notification, str);
    }

    private NotificationInfo getTwiTTerMessageInfo(Notification notification, String str) {
        return MsgNotificationHelper.formTwiTTerNotificationGetMessageInfo(notification, str);
    }

    private NotificationInfo getWhatsAppMessageInfo(Notification notification, String str) {
        return MsgNotificationHelper.formWhatsAppNotificationGetMessageInfo(notification, str);
    }

    private NotificationInfo getLinKeDinMessageInfo(Notification notification, String str) {
        return MsgNotificationHelper.formLinKeDinNotificationGetMessageInfo(notification, str);
    }

    private NotificationInfo getEmailMessageInfo(Notification notification, String str) {
        return MsgNotificationHelper.formEmailNotificationGetMessageInfo(notification, str);
    }

    private NotificationInfo getInsTaGramMessageInfo(Notification notification, String str) {
        return MsgNotificationHelper.formInsTaGramNotificationGetMessageInfo(notification, str);
    }

    private NotificationInfo getKaKaoTalkMessageInfo(Notification notification, String str) {
        return MsgNotificationHelper.formKaKaoTalkNotificationGetMessageInfo(notification, str);
    }

    private NotificationInfo getViBerMessageInfo(Notification notification, String str) {
        return MsgNotificationHelper.formViBerNotificationGetMessageInfo(notification, str);
    }

    private NotificationInfo getLinePkgListMessageInfo(Notification notification, String str) {
        return MsgNotificationHelper.formLinePkgListNotificationGetMessageInfo(notification, str);
    }

    private NotificationInfo getVkonTakteMessageInfo(Notification notification, String str) {
        return MsgNotificationHelper.formVkonTakteNotificationGetMessageInfo(notification, str);
    }

    private NotificationInfo getSnapchatMessageInfo(Notification notification, String str) {
        return MsgNotificationHelper.formSnapchatNotificationGetMessageInfo(notification, str);
    }

    private NotificationInfo getCalendarMessageInfo(Notification notification, String str) {
        return MsgNotificationHelper.formCalendarNotificationGetMessageInfo(notification, str);
    }

    private NotificationInfo getTumBlrMessageInfo(Notification notification, String str) {
        return MsgNotificationHelper.formTumBlrNotificationGetMessageInfo(notification, str);
    }

    private NotificationInfo getYouTuBeMessageInfo(Notification notification, String str) {
        return MsgNotificationHelper.formYouTuBeNotificationGetMessageInfo(notification, str);
    }

    private NotificationInfo getPinteRestMessageInfo(Notification notification, String str) {
        return MsgNotificationHelper.formPinteRestNotificationGetMessageInfo(notification, str);
    }

    private NotificationInfo getTikTokMessageInfo(Notification notification, String str) {
        return MsgNotificationHelper.formTikTokNotificationGetMessageInfo(notification, str);
    }

    private NotificationInfo getSmsMessageInfo(Notification notification, String str) {
        return MsgNotificationHelper.formSmsNotificationGetMessageInfo(notification, str);
    }

    private NotificationInfo getWhatsappBusinessMessageInfo(Notification notification, String str) {
        return MsgNotificationHelper.formWhatsappBusinessNotificationGetMessageInfo(notification, str);
    }

    private static String getPhonePkgName() {
        Intent intent = new Intent("android.intent.action.DIAL");
        PackageManager packageManager = mContext.getPackageManager();
        ResolveInfo resolveInfoResolveActivity = packageManager.resolveActivity(intent, 65536);
        if (resolveInfoResolveActivity != null && resolveInfoResolveActivity.activityInfo != null && resolveInfoResolveActivity.activityInfo.packageName != null) {
            if ("android".equals(resolveInfoResolveActivity.activityInfo.packageName)) {
                return getCallPkgNameByAllApp(packageManager);
            }
            Intent intent2 = new Intent(IN_CALL_SERVICE);
            intent2.setPackage(resolveInfoResolveActivity.activityInfo.packageName);
            if (packageManager.queryIntentServices(intent2, 131072).size() > 0) {
                return resolveInfoResolveActivity.activityInfo.packageName;
            }
            return getCallPkgNameByAllApp(packageManager);
        }
        return getCallPkgNameByAllApp(packageManager);
    }

    private static String getCallPkgNameByAllApp(PackageManager packageManager) {
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(new Intent(IN_CALL_SERVICE), 131072);
        String str = "android.incallui";
        for (int i = 0; i < listQueryIntentServices.size(); i++) {
            str = listQueryIntentServices.get(i).serviceInfo.packageName;
            try {
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
            }
            if ((packageManager.getPackageInfo(str, 0).applicationInfo.flags & 1) == 1) {
                return str;
            }
        }
        return str;
    }

    private String getSmsPkgName() {
        try {
            String defaultSmsPackage = Telephony.Sms.getDefaultSmsPackage(mContext);
            String str = TextUtils.isEmpty(defaultSmsPackage) ? "com.android.mms" : defaultSmsPackage;
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle getSmsPkgName为：" + str);
            return str;
        } catch (Exception e2) {
            LogHandle.getInstance().printLog(" NotificationSDK  NotificationHandle getSmsPkgName异常：" + e2.toString());
            ResolveInfo resolveInfoResolveActivity = mContext.getPackageManager().resolveActivity(new Intent("android.intent.action.SENDTO", Uri.parse("smsto:")), 65536);
            String str2 = (resolveInfoResolveActivity == null || resolveInfoResolveActivity.activityInfo == null) ? "com.android.mms" : resolveInfoResolveActivity.activityInfo.packageName;
            return TextUtils.isEmpty(str2) ? "com.android.mms" : str2;
        }
    }
}