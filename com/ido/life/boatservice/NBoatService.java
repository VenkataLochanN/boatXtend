package com.ido.life.boatservice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.session.MediaController;
import android.media.session.MediaSessionManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.provider.Telephony;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.ble.protocol.model.V3MessageNotice;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.bean.SwitchStatus;
import com.ido.life.constants.Constants;
import com.ido.life.data.cache.RemindDataManager;
import com.ido.life.module.main.MainActivity;
import com.ido.life.util.MsgNotificationHelper;
import com.ido.life.util.MusicHelper;
import com.ido.life.util.NoticeAppUtil;
import com.ido.life.util.SPHelper;
import com.ido.ntf.NotificationAndCallManager;
import com.ido.ntf.bean.NotificationInfo;
import com.ido.ntf.callback.INotificationBack;
import com.ido.ntf.log.LogBack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class NBoatService extends NotificationListenerService implements MediaSessionManager.OnActiveSessionsChangedListener {
    public static final int FLAG_AUTOGROUP_SUMMARY = 1024;
    private static final String IN_CALL_SERVICE = "android.telecom.InCallService";
    static final int NOTIFICATION_FLAG_FILTER = 1634;
    private static String content;
    private static String lastTextContent;
    String callText;
    String callTitle;
    boolean isInCalling;
    private String mPhonePkgName;
    private String mSmsPkgName;
    MediaSessionManager sessionManager;
    private Map<String, String> cachedMinSortKey = new HashMap();
    private Map<String, String> cachedGroupKey = new HashMap();
    private Map<String, Long> cachedNotificationWhen = new HashMap();
    private Map<String, Runnable> cachedDelayRunnable = new HashMap();
    Handler mDelayHandler = new Handler();
    long mPredelay = 500;
    Handler callHandler = new Handler();
    MediaController mActivedMediaController = null;
    List<MediaController> mMediaControllerList = new ArrayList();
    boolean isAddActiveSessionsChangedListener = false;
    private MusicHelper mMusicHelper = new MusicHelper();

    private String getComposePkg(int i, String str) {
        return i == 2 ? "email" : i == 12 ? Constants.AppPackage.CALENDAR : i == 1 ? Constants.AppPackage.SMS : str;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        resetContextIfNull();
        this.mSmsPkgName = getSmsPkgName();
        this.mPhonePkgName = getPhonePkgName();
        PendingIntent activity = PendingIntent.getActivity(this, 0, new Intent(this, (Class<?>) MainActivity.class), 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, getPackageName());
        builder.setContentTitle("Foreground Service");
        builder.setContentText("Foreground Service Started.");
        builder.setContentIntent(activity);
        builder.setWhen(System.currentTimeMillis());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        NotificationAndCallManager.setLogListener(new LogBack() { // from class: com.ido.life.boatservice.-$$Lambda$NBoatService$VfALeOpFd0i6csw0W483fGpzqao
            @Override // com.ido.ntf.log.LogBack
            public final void printLog(String str) {
                MsgNotificationHelper.saveLog(str + "");
            }
        });
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
            NotificationChannel notificationChannel = new NotificationChannel(getPackageName(), LanguageUtil.getLanguageText(R.string.app_name), 4);
            notificationChannel.setSound(null, null);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        builder.setChannelId(getPackageName());
        startForeground(0, builder.build());
        MsgNotificationHelper.saveLog("NotificationService: onCreate()");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        MsgNotificationHelper.registerMsgReplyListener();
        this.mSmsPkgName = getSmsPkgName();
        this.mPhonePkgName = getPhonePkgName();
        boolean zIsNotificationEnabled = isNotificationEnabled();
        MsgNotificationHelper.saveLog("isNotificationEnabled：" + zIsNotificationEnabled);
        if (zIsNotificationEnabled) {
            maySetupMediaSessionController();
        }
        return super.onStartCommand(intent, i, i2);
    }

    @Override // android.service.notification.NotificationListenerService, android.app.Service
    public IBinder onBind(Intent intent) {
        MsgNotificationHelper.saveLog("NotificationService: onBind()");
        return super.onBind(intent);
    }

    @Override // android.app.Service
    public void onRebind(Intent intent) {
        MsgNotificationHelper.saveLog("NotificationService: onRebind()");
        super.onRebind(intent);
    }

    @Override // android.service.notification.NotificationListenerService
    public void onListenerConnected() {
        MsgNotificationHelper.saveLog("NotificationService: onListenerConnected:");
        super.onListenerConnected();
    }

    @Override // android.service.notification.NotificationListenerService
    public void onListenerDisconnected() {
        MsgNotificationHelper.saveLog("NotificationService: onListenerDisconnected:");
        super.onListenerDisconnected();
    }

    @Override // android.service.notification.NotificationListenerService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        MsgNotificationHelper.saveLog("NotificationService: onDestroy()");
        stopForeground(true);
    }

    @Override // android.service.notification.NotificationListenerService
    public void onNotificationPosted(StatusBarNotification statusBarNotification) {
        resetContextIfNull();
        if (!BLEManager.isConnected()) {
            MsgNotificationHelper.saveLog("onNotificationPosted，device not connected");
            return;
        }
        MsgNotificationHelper.saveLog("------------开始-------------");
        MsgNotificationHelper.saveLog("onNotificationPosted ------->sbn:" + statusBarNotification);
        MsgNotificationHelper.saveLog("--------------------------");
        Notification notification = statusBarNotification.getNotification();
        if (notification == null) {
            MsgNotificationHelper.saveLog("onNotificationPosted ------->sbn:notification null");
            return;
        }
        String packageName = statusBarNotification.getPackageName();
        MsgNotificationHelper.saveLog("pkgName：" + packageName + "，tickerText：" + ((String) notification.tickerText) + "，notification：" + notification.toString());
        MsgNotificationHelper.saveLog("--------------------------");
        SwitchStatus switchStatus = SPHelper.getSwitchStatus();
        if (switchStatus.callReminderSwitched) {
            NotificationAndCallManager.filterCallNotification(statusBarNotification);
        }
        SwitchStatus.NotificationSwitch notificationSwitch = switchStatus.notificationSwitch;
        if (notificationSwitch == null) {
            MsgNotificationHelper.saveLog("notificationSwitch 为null");
            return;
        }
        MsgNotificationHelper.saveLog("NotificationService: status:" + notificationSwitch);
        if (!notificationSwitch.masterSwitched) {
            MsgNotificationHelper.saveLog("总开关未开启");
            return;
        }
        boolean zIsSupportV3Notify = NoticeAppUtil.isSupportV3Notify();
        MsgNotificationHelper.saveLog("onNotificationPosted isSupportV3Notify = " + zIsSupportV3Notify);
        if (zIsSupportV3Notify) {
            handleV3NotificationPost(statusBarNotification, notification);
        } else {
            handleNotificationPost(statusBarNotification, notification, packageName, notificationSwitch);
        }
    }

    private void handleNotificationPost(StatusBarNotification statusBarNotification, Notification notification, String str, SwitchStatus.NotificationSwitch notificationSwitch) {
        if ("com.tencent.mm".equals(str) && notificationSwitch.wechatSwitched) {
            if (notification.flags == 2 || notification.flags == 98) {
                MsgNotificationHelper.saveLog("微信正在语音");
                return;
            } else {
                sendText(notification, 3, 8195);
                return;
            }
        }
        if (Constants.AppPackage.QQ_PKG_LIST.contains(str) && notificationSwitch.qqSwitched) {
            MsgNotificationHelper.saveLog("QQ收到消息:" + notification.flags);
            if (notification.flags == 106) {
                CommonLogUtil.d("QQ正在语音");
                return;
            } else {
                sendText(notification, 4, V3MessageNotice.TYPE_QQ);
                return;
            }
        }
        if ("com.facebook.katana".equals(str) && notificationSwitch.facebookSwitched) {
            MsgNotificationHelper.saveLog("received FaceBook notification");
            sendText(notification, 6, V3MessageNotice.TYPE_FACEBOOK);
            return;
        }
        if ("com.twitter.android".equals(str) && notificationSwitch.twitterSwitched) {
            MsgNotificationHelper.saveLog("received Twitter notification");
            handleNotificationPost(statusBarNotification, 7, V3MessageNotice.TYPE_TWITTER);
            return;
        }
        if ("com.whatsapp".equals(str) && notificationSwitch.whatsAppSwitched) {
            MsgNotificationHelper.saveLog("received WhatsApp notification，notification.flags = " + notification.flags);
            handleNotificationPost(statusBarNotification, 8, V3MessageNotice.TYPE_WHATSAPP);
            return;
        }
        if ("com.linkedin.android".equals(str) && notificationSwitch.linkedinSwitched) {
            MsgNotificationHelper.saveLog("received Linkedin notification");
            handleNotificationPost(statusBarNotification, 11, V3MessageNotice.TYPE_LINKEDIN);
            return;
        }
        if ("com.instagram.android".equals(str) && notificationSwitch.instagramSwitched) {
            MsgNotificationHelper.saveLog("received Instagram notification");
            handleNotificationPost(statusBarNotification, 10, V3MessageNotice.TYPE_INSTAGRAM);
            return;
        }
        if ("com.facebook.orca".equals(str) && notificationSwitch.messengerSwitched) {
            MsgNotificationHelper.saveLog("received Messenger notification，notification.tickerText = " + ((Object) notification.tickerText));
            handleNotificationPost(statusBarNotification, 9, V3MessageNotice.TYPE_MESSENGER);
            return;
        }
        if ("com.microsoft.office.outlook".equals(str) && notificationSwitch.mailOutlookSwitched) {
            MsgNotificationHelper.saveLog("received outlook邮箱 notification");
            handleNotificationPost(statusBarNotification, 21, V3MessageNotice.TYPE_OUTLOOK);
            return;
        }
        if (Constants.AppPackage.YAHOO_PKG_LIST.contains(str) && notificationSwitch.mailYahooSwitched) {
            MsgNotificationHelper.saveLog("received yahoo邮箱 notification");
            handleNotificationPost(statusBarNotification, 34, V3MessageNotice.TYPE_MAIL_YAHOO);
            return;
        }
        if (Constants.AppPackage.GOOGLE_MEET.equals(str) && notificationSwitch.googleMeetSwitched) {
            MsgNotificationHelper.saveLog("received Google Meet提醒 notification");
            handleNotificationPost(statusBarNotification, 64, 64);
            return;
        }
        if ((getEmailPkgNames().contains(str) || str.contains("email")) && notificationSwitch.mailSwitched) {
            MsgNotificationHelper.saveLog("received Email notification");
            handleNotificationPost(statusBarNotification, 2, 8194);
            return;
        }
        if ("com.kakao.talk".equals(str) && notificationSwitch.kakaoTalkSwitched) {
            MsgNotificationHelper.saveLog("received Kakaotalk notification");
            sendText(notification, 19, V3MessageNotice.TYPE_KAKAO_TALK);
            return;
        }
        if ("com.viber.voip".equals(str) && notificationSwitch.viberSwitched) {
            MsgNotificationHelper.saveLog("received Viber notification");
            sendText(notification, 18, V3MessageNotice.TYPE_VIBER);
            return;
        }
        if (Constants.AppPackage.LINE_PKG_LIST.contains(str) && notificationSwitch.lineSwitched) {
            MsgNotificationHelper.saveLog("received Line notification，notification.flags = " + notification.flags);
            if (notification.flags == 529 || notification.flags == 98) {
                return;
            }
            sendText(notification, 17, V3MessageNotice.TYPE_LINE);
            return;
        }
        if ("com.vkontakte.android".equals(str) && notificationSwitch.vkSwitched) {
            MsgNotificationHelper.saveLog("received Vkontakte notification");
            sendText(notification, 16, V3MessageNotice.TYPE_VKONTAKTE);
            return;
        }
        if ((str.contains("com.skype") || str.contains("com.skype.insiders") || str.contains("com.skype.insiders")) && notificationSwitch.skypeSwitched) {
            if (notification.flags == 529) {
                MsgNotificationHelper.saveLog("SKYPE重复被提醒");
                return;
            } else {
                MsgNotificationHelper.saveLog("received Skype notification");
                handleNotificationPost(statusBarNotification, 13, V3MessageNotice.TYPE_SKYPE);
                return;
            }
        }
        if (("com.google.android.gm".equals(str) || "com.google.android.gm.lite".equals(str)) && notificationSwitch.mailSwitched) {
            MsgNotificationHelper.saveLog("received GMail notification");
            handleNotificationPost(statusBarNotification, 20, V3MessageNotice.TYPE_GMAIL);
            return;
        }
        if ("com.snapchat.android".equals(str) && notificationSwitch.snapchatSwitched) {
            MsgNotificationHelper.saveLog("received Snapchat notification");
            sendText(notification, 22, V3MessageNotice.TYPE_SNAPCHAT);
            return;
        }
        if ((str.contains(Constants.AppPackage.CALENDAR) || getCalendarPkgNames().contains(str)) && notificationSwitch.calendarSwitched) {
            MsgNotificationHelper.saveLog("received Calendar notification");
            sendText(notification, 12, V3MessageNotice.TYPE_CALENDAR);
            return;
        }
        if (getMatterPkgNames().contains(str) && notificationSwitch.matterSwitched) {
            MsgNotificationHelper.saveLog("received Matter notification");
            sendText(notification, 63, V3MessageNotice.TYPE_MATTER);
            return;
        }
        if ("com.tumblr".equals(str) && notificationSwitch.tumblrSwitched) {
            MsgNotificationHelper.saveLog("received Tumblr notification");
            sendText(notification, 35, V3MessageNotice.TYPE_TUMBLR);
            return;
        }
        if ("com.google.android.youtube".equals(str) && notificationSwitch.youTubeSwitched) {
            MsgNotificationHelper.saveLog("received Youtube notification");
            sendText(notification, 36, V3MessageNotice.TYPE_YOUTUBE);
            return;
        }
        if ("com.pinterest".equals(str) && notificationSwitch.pinterestSwitched) {
            MsgNotificationHelper.saveLog("received Pinterest notification");
            sendText(notification, 37, V3MessageNotice.TYPE_PINTEREST_YAHOO);
            return;
        }
        if ("org.telegram.messenger".equals(str) && notificationSwitch.telegramSwitched) {
            MsgNotificationHelper.saveLog("received Telegram notification，notification.flags = " + notification.flags);
            if (notification.flags == 529) {
                return;
            }
            handleNotificationPost(statusBarNotification, 23, V3MessageNotice.TYPE_TELEGRAM);
            return;
        }
        if (notificationSwitch.tikTokSwitched && ("com.zhiliaoapp.musically".equals(str) || "com.ss.android.ugc.trill".equals(str))) {
            MsgNotificationHelper.saveLog("received Tiktok notification");
            sendText(notification, 38, V3MessageNotice.TYPE_TIKTOK);
            return;
        }
        if (notificationSwitch.smsSwitched && !TextUtils.isEmpty(this.mSmsPkgName) && str.contains(this.mSmsPkgName)) {
            MsgNotificationHelper.saveLog("received SMS notification，notification.flags = " + notification.flags);
            if (((notification.flags >> 1) & 1) == 1) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getNotificationLogPath(), "IntelligentNotificationService在0.8s内拒绝发送重复短信");
                return;
            } else {
                sendText(notification, 1, 8193);
                return;
            }
        }
        if (notificationSwitch.whatsAppBusinessSwitched && "com.whatsapp.w4b".equals(str)) {
            MsgNotificationHelper.saveLog("received WhatsApp Business notification");
            handleNotificationPost(statusBarNotification, 56, V3MessageNotice.TYPE_WHATSAPP_BUSINESS);
        }
    }

    private void handleV3NotificationPost(final StatusBarNotification statusBarNotification, final Notification notification) {
        final int i;
        boolean zCanNotify;
        if (statusBarNotification == null || notification == null) {
            return;
        }
        MsgNotificationHelper.saveLog("handleV3NotificationPost：" + statusBarNotification.getPackageName());
        String packageName = statusBarNotification.getPackageName();
        if (isSmsApp(packageName)) {
            i = 1;
        } else if (NoticeAppUtil.isEmailApp(packageName)) {
            i = 2;
        } else {
            i = NoticeAppUtil.isCalendarApp(packageName) ? 12 : 0;
        }
        try {
            zCanNotify = RemindDataManager.INSTANCE.getInstance().canNotify(getComposePkg(i, packageName));
        } catch (Exception e2) {
            e2.printStackTrace();
            zCanNotify = true;
        }
        if (zCanNotify) {
            NotificationAndCallManager.filterNotifications(statusBarNotification, true, new INotificationBack() { // from class: com.ido.life.boatservice.-$$Lambda$NBoatService$Ubt_kj3zxUOYRCXhCcJwi2LqVkI
                @Override // com.ido.ntf.callback.INotificationBack
                public final void onFilterNotificationResult(NotificationInfo notificationInfo) {
                    NBoatService.lambda$handleV3NotificationPost$1(notification, statusBarNotification, i, notificationInfo);
                }
            });
            return;
        }
        MsgNotificationHelper.saveLog("handleV3NotificationPost, not allow notify：" + statusBarNotification.getPackageName());
    }

    static /* synthetic */ void lambda$handleV3NotificationPost$1(Notification notification, StatusBarNotification statusBarNotification, int i, NotificationInfo notificationInfo) {
        if (notificationInfo == null || notification == null || statusBarNotification == null) {
            return;
        }
        MsgNotificationHelper.sendNotification2DeviceV3(notification, statusBarNotification.getPackageName(), notificationInfo.name, notificationInfo.content, i);
    }

    private boolean isSmsApp(String str) {
        return !TextUtils.isEmpty(this.mSmsPkgName) && str.contains(this.mSmsPkgName);
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

    private void sendText(Notification notification, int i, int i2) {
        MsgNotificationHelper.formNotificationAndSend2Device(notification, i, i2);
    }

    private void resetContextIfNull() {
        BLEManager.onApplicationCreate(getApplication());
        IdoApp.resetContextIfNull(this);
    }

    private String getSmsPkgName() {
        String defaultSmsPackage;
        try {
            defaultSmsPackage = Telephony.Sms.getDefaultSmsPackage(this);
        } catch (Exception e2) {
            MsgNotificationHelper.saveLog("getSmsPkgName异常：" + e2.toString());
            ResolveInfo resolveInfoResolveActivity = getPackageManager().resolveActivity(new Intent("android.intent.action.SENDTO", Uri.parse("smsto:")), 65536);
            defaultSmsPackage = (resolveInfoResolveActivity == null || resolveInfoResolveActivity.activityInfo == null) ? null : resolveInfoResolveActivity.activityInfo.packageName;
        }
        if (TextUtils.isEmpty(defaultSmsPackage)) {
            defaultSmsPackage = "com.android.mms";
        }
        MsgNotificationHelper.saveLog("getSmsPkgName为：" + defaultSmsPackage);
        return defaultSmsPackage;
    }

    public String getPhonePkgName() {
        String callPkgNameByAllApp;
        Intent intent = new Intent("android.intent.action.DIAL");
        PackageManager packageManager = getPackageManager();
        ResolveInfo resolveInfoResolveActivity = packageManager.resolveActivity(intent, 65536);
        if (resolveInfoResolveActivity != null && resolveInfoResolveActivity.activityInfo != null && resolveInfoResolveActivity.activityInfo.packageName != null) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getNotificationLogPath(), "getPhonePkgName 电话包名：" + resolveInfoResolveActivity.activityInfo.packageName);
            if ("android".equals(resolveInfoResolveActivity.activityInfo.packageName)) {
                callPkgNameByAllApp = getCallPkgNameByAllApp(packageManager);
            } else {
                Intent intent2 = new Intent(IN_CALL_SERVICE);
                intent2.setPackage(resolveInfoResolveActivity.activityInfo.packageName);
                if (packageManager.queryIntentServices(intent2, 131072).size() > 0) {
                    callPkgNameByAllApp = resolveInfoResolveActivity.activityInfo.packageName;
                } else {
                    callPkgNameByAllApp = getCallPkgNameByAllApp(packageManager);
                }
            }
        } else {
            callPkgNameByAllApp = getCallPkgNameByAllApp(packageManager);
        }
        MsgNotificationHelper.saveLog("最终电话包名：" + callPkgNameByAllApp);
        return callPkgNameByAllApp;
    }

    private String getCallPkgNameByAllApp(PackageManager packageManager) {
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(new Intent(IN_CALL_SERVICE), 131072);
        String str = "android.incallui";
        for (int i = 0; i < listQueryIntentServices.size(); i++) {
            str = listQueryIntentServices.get(i).serviceInfo.packageName;
            MsgNotificationHelper.saveLog(" getCallPkgNameByAllApp 电话包名" + str);
            try {
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
                MsgNotificationHelper.saveLog("getCallPkgNameByAllApp exception : " + e2.toString());
            }
            if ((packageManager.getPackageInfo(str, 0).applicationInfo.flags & 1) == 1) {
                return str;
            }
        }
        return str;
    }

    private void sendInCallingText(Notification notification, int i) {
        Object obj;
        this.callTitle = "";
        this.callText = "";
        Bundle bundle = notification.extras;
        this.callTitle = bundle.getString(NotificationCompat.EXTRA_TITLE);
        this.callText = bundle.getString(NotificationCompat.EXTRA_TEXT);
        if (TextUtils.isEmpty(this.callText) && (obj = bundle.get(NotificationCompat.EXTRA_TEXT)) != null) {
            this.callText = obj.toString();
        }
        MsgNotificationHelper.saveLog("mTitle:" + this.callTitle + ",mText:" + this.callText + " ,flags=" + notification.flags + " ,when=" + notification.when + " ,sortkey=" + notification.getSortKey());
        if (this.isInCalling) {
            return;
        }
        this.isInCalling = true;
        this.callHandler.postDelayed(new Runnable() { // from class: com.ido.life.boatservice.-$$Lambda$NBoatService$_dJLV1VVO_FDTC1xJ1V42X3HZVA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$sendInCallingText$2$NBoatService();
            }
        }, i);
    }

    public /* synthetic */ void lambda$sendInCallingText$2$NBoatService() {
        this.isInCalling = false;
        String str = this.callTitle;
        MsgNotificationHelper.sendCallReminder2Device(str, str);
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0084 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0085  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void handleNotificationPost(android.service.notification.StatusBarNotification r11, final int r12, final int r13) {
        /*
            Method dump skipped, instruction units count: 630
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.boatservice.NBoatService.handleNotificationPost(android.service.notification.StatusBarNotification, int, int):void");
    }

    public /* synthetic */ void lambda$handleNotificationPost$3$NBoatService(boolean z, Notification notification, int i, int i2) {
        MsgNotificationHelper.saveLog("NotificationService: runnable执行");
        if (!z) {
            this.mPredelay = 0L;
        }
        sendText(notification, i, i2);
    }

    public static boolean actionWhatsMessage(Notification notification, int i, int i2) {
        try {
            if (Build.VERSION.SDK_INT > 18) {
                Bundle bundle = notification.extras;
                Object obj = bundle.get(NotificationCompat.EXTRA_TEXT_LINES);
                bundle.get(NotificationCompat.EXTRA_BIG_TEXT);
                if ((i2 == 8200 || i2 == 8248) && obj != null && (obj instanceof CharSequence[])) {
                    CharSequence[] charSequenceArr = (CharSequence[]) obj;
                    if (charSequenceArr == null || charSequenceArr.length <= 0) {
                        return true;
                    }
                    StringBuffer stringBuffer = new StringBuffer();
                    for (CharSequence charSequence : charSequenceArr) {
                        stringBuffer.append(charSequence.toString() + AppInfo.DELIM);
                    }
                    content = stringBuffer.toString();
                    MsgNotificationHelper.saveLog("NotificationService: 具体内容:" + content);
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    private boolean maySetupMediaSessionController() {
        try {
            if (!this.isAddActiveSessionsChangedListener) {
                this.sessionManager = (MediaSessionManager) getSystemService("media_session");
                ComponentName componentName = new ComponentName(this, (Class<?>) NBoatService.class);
                this.sessionManager.addOnActiveSessionsChangedListener(this, componentName);
                onActiveSessionsChanged(this.sessionManager.getActiveSessions(componentName));
                CommonLogUtil.d("onActiveSessionsChanged maySetupMediaSessionController");
                this.isAddActiveSessionsChangedListener = true;
            }
        } catch (Exception unused) {
            this.isAddActiveSessionsChangedListener = false;
        }
        return this.isAddActiveSessionsChangedListener;
    }

    @Override // android.media.session.MediaSessionManager.OnActiveSessionsChangedListener
    public void onActiveSessionsChanged(List<MediaController> list) {
        MediaController mediaController;
        if (list != null && list.size() > 0) {
            for (MediaController mediaController2 : list) {
                try {
                    CommonLogUtil.d("music packageName= onActiveSessionsChanged  " + mediaController2.getPackageName() + "  state=" + (mediaController2.getPlaybackState() == null ? "null" : mediaController2.getPlaybackState().getState() + ""));
                } catch (Exception unused) {
                    CommonLogUtil.printAndSave(LogPathImpl.getInstance().getNotificationLogPath(), "findActivedController: 获取 playbackState 异常");
                }
            }
        }
        if (list == null || list.size() == 0) {
            if (!this.mMediaControllerList.isEmpty() && (mediaController = this.mActivedMediaController) != null && this.mMediaControllerList.contains(mediaController)) {
                this.mMediaControllerList.remove(this.mActivedMediaController);
                this.mActivedMediaController = null;
            }
            CommonLogUtil.d("onActiveSessionsChanged mMediaControllerList.remove(mActivedMediaController)");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("onActiveSessionsChanged controllers: ");
        sb.append(list != null ? Integer.valueOf(list.size()) : "null");
        CommonLogUtil.d(sb.toString());
        this.mMusicHelper.handleActiveSessionChanged((List) Objects.requireNonNull(list), false, false);
    }

    public boolean isNotificationEnabled() {
        String string = Settings.Secure.getString(getContentResolver(), "enabled_notification_listeners");
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        return string.contains(getPackageName());
    }

    public ArrayList<String> getMatterPkgNames() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("com.example.android.notepad");
        arrayList.add("com.huawei.notepad");
        arrayList.add("com.android.notes");
        arrayList.add("com.coloros.note");
        arrayList.add("com.miui.notes");
        arrayList.add("com.oneplus.note");
        return arrayList;
    }
}