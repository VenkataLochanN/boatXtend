package com.ido.life.util;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.DeviceParaChangedCallBack;
import com.ido.ble.protocol.model.DeviceChangedPara;
import com.ido.ble.protocol.model.IncomingCallInfo;
import com.ido.ble.protocol.model.NewMessageInfo;
import com.ido.ble.protocol.model.NotificationPara;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.protocol.model.V3MessageNotice;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.AppUtil;
import com.ido.common.utils.StringUtil;
import com.ido.life.bean.AppNameBean;
import com.ido.life.bean.TranIconBean;
import com.ido.life.boatservice.NBoatService;
import com.ido.life.constants.Constants;
import com.ido.life.data.cache.AppNameLanguageManager;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class MsgNotificationHelper {
    public static final byte WECHAT = 3;
    private static String lastCalendarContent = null;
    private static long lastCalendarTimestamp = 0;
    private static String lastSmsContent = null;
    private static long lastSmsTimestamp = 0;
    private static DeviceParaChangedCallBack.ICallBack mMsgReplyICallBack = new DeviceParaChangedCallBack.ICallBack() { // from class: com.ido.life.util.-$$Lambda$MsgNotificationHelper$XAzlZwfx2YgSgquJ2n1FwV2RSno
        @Override // com.ido.ble.callback.DeviceParaChangedCallBack.ICallBack
        public final void onChanged(DeviceChangedPara deviceChangedPara) {
            MsgNotificationHelper.lambda$static$0(deviceChangedPara);
        }
    };
    private static int msgID = 0;
    public static boolean needCallRemind = false;
    private static Map<Integer, Notification> sNotificationMap;

    static /* synthetic */ void lambda$static$0(DeviceChangedPara deviceChangedPara) {
        List<String> quickMsgList;
        StringBuilder sb = new StringBuilder();
        sb.append(" mMsgReplyICallBack=");
        sb.append(deviceChangedPara == null ? "null" : deviceChangedPara.toString());
        CommonLogUtil.d(sb.toString());
        if (deviceChangedPara == null || deviceChangedPara.msg_ID <= 0 || deviceChangedPara.msg_notice <= 0 || (quickMsgList = SPHelper.getQuickMsgList()) == null || quickMsgList.size() <= deviceChangedPara.msg_notice) {
            return;
        }
        replyMsg(deviceChangedPara.msg_ID, quickMsgList.get(deviceChangedPara.msg_notice - 1));
    }

    public static void bindNotificationService() {
        boolean zIsServiceRunning = AppUtil.isServiceRunning(IdoApp.getAppContext(), NBoatService.class.getName());
        saveLog("NotificationService 是否运行 " + zIsServiceRunning);
        if (zIsServiceRunning) {
            return;
        }
        resetNotificationService();
    }

    private static void resetNotificationService() {
        Context appContext = IdoApp.getAppContext();
        PackageManager packageManager = appContext.getPackageManager();
        saveLog("resetNotificationService NotificationService");
        packageManager.setComponentEnabledSetting(new ComponentName(appContext, (Class<?>) NBoatService.class), 2, 1);
        packageManager.setComponentEnabledSetting(new ComponentName(appContext, (Class<?>) NBoatService.class), 1, 1);
    }

    public static boolean isSupportV3Notify() {
        return NoticeAppUtil.isSupportV3Notify();
    }

    public static boolean isSupportOldV3Notify() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        if (supportFunctionInfo == null) {
            supportFunctionInfo = new SupportFunctionInfo();
        }
        return supportFunctionInfo.ex_table_main10_v3_notify_msg;
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x0106 A[Catch: Exception -> 0x017b, TryCatch #0 {Exception -> 0x017b, blocks: (B:3:0x0025, B:6:0x002f, B:8:0x0039, B:10:0x004f, B:32:0x00a3, B:34:0x00a7, B:36:0x00b4, B:38:0x00c0, B:40:0x00c3, B:42:0x00c6, B:44:0x00ca, B:46:0x00d4, B:53:0x0100, B:55:0x0106, B:56:0x010c, B:58:0x0112, B:62:0x011b, B:64:0x0125, B:47:0x00d7, B:48:0x00dc, B:49:0x00e2, B:51:0x00ea, B:52:0x00fc, B:14:0x0065, B:16:0x0069, B:18:0x006f, B:20:0x0072, B:22:0x0075, B:24:0x0079, B:26:0x0087, B:27:0x008f, B:28:0x0098, B:9:0x004b, B:66:0x0135, B:70:0x0145, B:72:0x014b, B:76:0x0154, B:78:0x0164, B:69:0x013f), top: B:118:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0112 A[Catch: Exception -> 0x017b, TryCatch #0 {Exception -> 0x017b, blocks: (B:3:0x0025, B:6:0x002f, B:8:0x0039, B:10:0x004f, B:32:0x00a3, B:34:0x00a7, B:36:0x00b4, B:38:0x00c0, B:40:0x00c3, B:42:0x00c6, B:44:0x00ca, B:46:0x00d4, B:53:0x0100, B:55:0x0106, B:56:0x010c, B:58:0x0112, B:62:0x011b, B:64:0x0125, B:47:0x00d7, B:48:0x00dc, B:49:0x00e2, B:51:0x00ea, B:52:0x00fc, B:14:0x0065, B:16:0x0069, B:18:0x006f, B:20:0x0072, B:22:0x0075, B:24:0x0079, B:26:0x0087, B:27:0x008f, B:28:0x0098, B:9:0x004b, B:66:0x0135, B:70:0x0145, B:72:0x014b, B:76:0x0154, B:78:0x0164, B:69:0x013f), top: B:118:0x0025 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void formNotificationAndSend2Device(android.app.Notification r18, int r19, int r20) {
        /*
            Method dump skipped, instruction units count: 542
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.util.MsgNotificationHelper.formNotificationAndSend2Device(android.app.Notification, int, int):void");
    }

    public static boolean isSendMsg(int i, Notification notification) {
        return i != 8 ? (i == 11 && TextUtils.isEmpty(notification.category)) ? false : true : (notification.publicVersion == null || TextUtils.isEmpty(notification.publicVersion.category)) ? false : true;
    }

    public static void sendNotificationDevice(int i, int i2, String str, String str2) {
        if (!BLEManager.isConnected()) {
            saveLog("sendNotification2Device，device not connected");
            return;
        }
        if (isSupportOldV3Notify()) {
            V3MessageNotice v3MessageNotice = new V3MessageNotice();
            v3MessageNotice.evtType = i2;
            v3MessageNotice.contact = str;
            v3MessageNotice.dataText = str2;
            saveLog("发送V3提醒内容：" + v3MessageNotice.toString());
            BLEManager.setV3MessageNotice(v3MessageNotice);
            return;
        }
        NewMessageInfo newMessageInfo = new NewMessageInfo();
        newMessageInfo.type = i;
        newMessageInfo.content = str2;
        newMessageInfo.name = str;
        saveLog("发送提醒内容：" + newMessageInfo.toString());
        BLEManager.setNewMessageDetailInfo(newMessageInfo);
    }

    public static void sendCallReminder2DeviceNew(String str, String str2) {
        String str3 = TextUtils.isEmpty(str2) ? str : str2;
        if (TextUtils.isEmpty(str3)) {
            str3 = " ";
        }
        saveLog("发送命令到手环");
        saveLog("sendData   phoneNumber: " + str + " ---contactName:" + str2 + " ---tempName:" + str3);
        IncomingCallInfo incomingCallInfo = new IncomingCallInfo();
        if (!TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str) && str3.trim().replace(" ", "").contains(str.trim().replace(" ", ""))) {
            incomingCallInfo.name = str3;
            saveLog("sendData   name: " + str3);
        } else {
            incomingCallInfo.name = str3;
            incomingCallInfo.phoneNumber = str;
            saveLog("sendData   phoneNumber: " + str + " ---tempName:" + str3);
        }
        BLEManager.setIncomingCallInfo(incomingCallInfo);
    }

    public static void sendNotification2Device(int i, int i2, String str, String str2, String str3, boolean z) {
        if (!BLEManager.isConnected()) {
            saveLog("sendNotification2Device，device not connected");
            return;
        }
        if (isRepeatedNotification(str, str3, i)) {
            return;
        }
        if (isSupportOldV3Notify()) {
            V3MessageNotice v3MessageNotice = new V3MessageNotice();
            v3MessageNotice.evtType = i2;
            v3MessageNotice.contact = str;
            v3MessageNotice.phoneNumber = str2;
            v3MessageNotice.dataText = str3;
            if (z) {
                v3MessageNotice.msgID = msgID;
            }
            saveLog("发送V3提醒内容：" + v3MessageNotice.toString());
            BLEManager.setV3MessageNotice(v3MessageNotice);
            return;
        }
        NewMessageInfo newMessageInfo = new NewMessageInfo();
        newMessageInfo.type = i;
        newMessageInfo.content = str3;
        newMessageInfo.number = str2;
        newMessageInfo.name = str;
        saveLog("发送提醒内容：" + newMessageInfo.toString());
        BLEManager.setNewMessageDetailInfo(newMessageInfo);
    }

    public static boolean isQuickReplyOpened() {
        return SPHelper.isQuickMsgReplySwitchOpened();
    }

    public static boolean isSupportQuickReply(Notification notification) {
        return getReplyAction(notification) != null;
    }

    private static boolean isRepeatedNotification(String str, String str2, int i) {
        if (i == 1) {
            if (isRepeatedSms(str2)) {
                saveLog("短信重复，不重新发");
                return true;
            }
            lastSmsContent = str2;
            lastSmsTimestamp = System.currentTimeMillis();
            return false;
        }
        if (i != 12) {
            return false;
        }
        saveLog("日历内容：" + str + str2);
        if (isRepeatedCalendar(str + str2)) {
            saveLog("重复日历，不提醒");
            return true;
        }
        lastCalendarContent = str + str2;
        lastCalendarTimestamp = System.currentTimeMillis();
        return false;
    }

    public static void sendNotification2DeviceV3(Notification notification, String str, String str2, String str3, int i) {
        boolean z;
        if (!BLEManager.isConnected()) {
            saveLog("sendNotification2DeviceV3，device not connected");
            return;
        }
        if (!isSupportV3Notify()) {
            saveLog("sendNotification2DeviceV3 not support v3 notification!");
            return;
        }
        boolean zIsSendMsg = isSendMsg(i, notification);
        boolean zIsSupportQuickReply = isSupportQuickReply(notification);
        if (zIsSendMsg && isSupportV3Notify() && zIsSupportQuickReply) {
            saveNotification(notification);
        }
        int size = 1;
        if (i == 2) {
            str = "email";
        } else if (i == 12) {
            str = Constants.AppPackage.CALENDAR;
        } else if (i == 1) {
            str = Constants.AppPackage.SMS;
        }
        if (isRepeatedNotification(str2, str3, i)) {
            return;
        }
        TranIconBean tranIconBeanUpdateNoticeApp = NoticeAppUtil.updateNoticeApp(str);
        CommonLogUtil.d("sendNotification2DeviceV3 entity = " + tranIconBeanUpdateNoticeApp + ", pkgName = " + str + ", type = " + i + ", msgID = " + msgID + ", isSupportReply = " + zIsSupportQuickReply);
        if (tranIconBeanUpdateNoticeApp == null) {
            return;
        }
        NotificationPara notificationPara = new NotificationPara();
        notificationPara.notify_type = tranIconBeanUpdateNoticeApp.getType();
        if (zIsSupportQuickReply && isQuickReplyOpened()) {
            notificationPara.msg_ID = msgID;
        } else {
            saveLog("不支持快捷回复或快捷回复开关没有打开：isSupportReply = " + zIsSupportQuickReply + ", isQuickReplyOpened = " + isQuickReplyOpened());
        }
        notificationPara.contact = AppNameLanguageManager.convertChinese2Pinyin(str2);
        notificationPara.msg_data = str3;
        notificationPara.evt_type = 1;
        String appName = tranIconBeanUpdateNoticeApp.getAppName();
        List<AppNameBean> appNames = TextUtils.isEmpty(str) ? null : NoticeAppUtil.getAppNames(str, appName);
        if (ListUtils.INSTANCE.isNotEmpty(appNames)) {
            z = true;
            size = appNames.size();
        } else {
            z = false;
        }
        notificationPara.app_items_len = size;
        notificationPara.items = new ArrayList();
        for (int i2 = 0; i2 < size; i2++) {
            NotificationPara.AppNames appNames2 = new NotificationPara.AppNames();
            if (z) {
                AppNameBean appNameBean = appNames.get(i2);
                appNames2.language = appNameBean.getLanguage_type();
                appNames2.name = appNameBean.getName();
            } else {
                saveLog("应用：" + str + " 没有取到名称列表，使用默认名称：" + appName);
                appNames2.language = i2 + 1;
                appNames2.name = appName;
            }
            notificationPara.items.add(appNames2);
        }
        CommonLogUtil.d("sendNotification2DeviceV3 v3MessageNotice = " + notificationPara);
        saveLog("应用名：" + tranIconBeanUpdateNoticeApp.getAppName() + ", 包名：" + str + ", 是否支持快捷回复：" + zIsSupportQuickReply);
        BLEManager.sendNotification(notificationPara);
    }

    public static void sendCallReminder2Device(String str, String str2) {
        String targetText = TextUtils.isEmpty(str2) ? str : str2;
        if (TextUtils.isEmpty(targetText)) {
            targetText = " ";
        }
        if (!FunctionHelper.isSupportOver14chars()) {
            targetText = StringUtil.getTargetText(14, targetText);
        }
        if (needCallRemind) {
            saveLog("发送命令到手环");
            saveLog("sendData   phoneNumber: " + str + " ---contactName:" + str2 + " ---tempName:" + targetText);
            IncomingCallInfo incomingCallInfo = new IncomingCallInfo();
            incomingCallInfo.name = targetText;
            BLEManager.setIncomingCallInfo(incomingCallInfo);
            needCallRemind = false;
        }
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

    public static void replyMsg(int i, String str) {
        Notification notification;
        Map<Integer, Notification> map = sNotificationMap;
        if (map == null || (notification = map.get(Integer.valueOf(i))) == null) {
            return;
        }
        testReply(notification, str);
    }

    public static void registerMsgReplyListener() {
        if (isSupportV3Notify()) {
            CommonLogUtil.d("注册mMsgReplyICallBack");
            BLEManager.unregisterDeviceParaChangedCallBack(mMsgReplyICallBack);
            BLEManager.registerDeviceParaChangedCallBack(mMsgReplyICallBack);
        }
    }

    private static void testReply(Notification notification, String str) {
        try {
            PendingIntent pendingIntent = notification.contentIntent;
            Notification.Action replyAction = getReplyAction(notification);
            if (replyAction != null) {
                RemoteInput remoteInput = replyAction.getRemoteInputs()[0];
                String resultKey = remoteInput.getResultKey();
                if (pendingIntent != null) {
                    CommonLogUtil.d("带快捷回复的通知===action.getRemoteInputs() length=" + replyAction.getRemoteInputs().length + "   ResultKey=" + remoteInput.getResultKey());
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString(resultKey, str);
                    RemoteInput.addResultsToIntent(new RemoteInput[]{new RemoteInput.Builder(resultKey).build()}, intent, bundle);
                    try {
                        replyAction.actionIntent.send(IdoApp.getAppContext(), 0, intent);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            } else {
                CommonLogUtil.d("不带快捷回复的通知");
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    private static Notification.Action getReplyAction(Notification notification) {
        Notification.Action[] actionArr = notification.actions;
        if (actionArr == null) {
            return null;
        }
        for (Notification.Action action : actionArr) {
            if (action != null && action.getRemoteInputs() != null && action.getRemoteInputs().length > 0) {
                return action;
            }
        }
        return null;
    }

    private static RemoteInput getFirstRemoteInputWithTextReply(Notification.Action action) {
        throw new UnsupportedOperationException("Method not decompiled: com.watch.life.notifications.parsing.statusbar.StatusBarNotificationUtility.getFirstRemoteInputWithTextReply(android.app.Notification$Action):android.app.RemoteInput");
    }

    public static void saveLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getNotificationLogPath(), "MsgNotificationHelper", str);
    }
}