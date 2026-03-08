package com.ido.life.boatservice;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Vibrator;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.DeviceControlAppCallBack;
import com.ido.ble.protocol.model.NewMessageInfo;
import com.ido.ble.protocol.model.NotificationPara;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.protocol.model.V3MessageNotice;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.PermissionUtil;
import com.ido.life.base.BaseMessage;
import com.ido.life.bean.AppNameBean;
import com.ido.life.bean.SwitchStatus;
import com.ido.life.bean.TranIconBean;
import com.ido.life.ble.BaseDeviceControlAppCallBack;
import com.ido.life.constants.Constants;
import com.ido.life.data.listener.Callback;
import com.ido.life.util.FunctionHelper;
import com.ido.life.util.ListUtils;
import com.ido.life.util.MsgNotificationHelper;
import com.ido.life.util.NoticeAppUtil;
import com.ido.life.util.PhoneUtil;
import com.ido.life.util.RxUtils;
import com.ido.life.util.SMSPhoneUtil;
import com.ido.life.util.SPHelper;
import com.ido.life.util.eventbus.EventBusWrapper;
import com.ido.life.util.eventbus.IHandlerEventBus;
import com.ido.ntf.NotificationAndCallManager;
import com.ido.ntf.callback.ICallPhoneInfoBack;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceAssistService extends Service implements IHandlerEventBus {
    private static final String ACTION_VOLUME_CHANGED = "android.media.VOLUME_CHANGED_ACTION";
    private static final int TYPE_MISSED_CALL = 58;
    private static final int TYPE_MSG_BASE = 8192;
    private static int lastCallState;
    private boolean hasFirstRegisterPhone;
    private BroadcastReceiver mBroadcastReceiver;
    private MediaPlayer mMediaPlayer;
    private Vibrator mVib;
    private PhoneStateListener phoneStateListener;
    private SmsObserver smsObserver;
    private TelephonyManager tpm;
    EventBusWrapper wrapper;
    private boolean isComingPhone = false;
    private Uri SMS_URI = Uri.parse("content://sms/");
    private Uri SMS_INBOX = Uri.parse("content://sms/inbox");
    private boolean isRingOrVibrate = true;
    private Handler handler = new Handler();
    private Handler callHandler = new Handler();
    private long exitTime = 0;
    Runnable vibrateAndMediaRunnable = new Runnable() { // from class: com.ido.life.boatservice.DeviceAssistService.1
        @Override // java.lang.Runnable
        public void run() {
            if (DeviceAssistService.this.isRingOrVibrate) {
                if (System.currentTimeMillis() - DeviceAssistService.this.exitTime >= 30000) {
                    if (DeviceAssistService.this.mMediaPlayer != null && DeviceAssistService.this.mMediaPlayer.isPlaying()) {
                        DeviceAssistService.this.mMediaPlayer.stop();
                        DeviceAssistService.this.mMediaPlayer.release();
                        DeviceAssistService.this.mMediaPlayer = null;
                    }
                    if (DeviceAssistService.this.mVib != null) {
                        DeviceAssistService.this.mVib.cancel();
                        DeviceAssistService.this.mVib = null;
                    }
                }
                DeviceAssistService.this.handler.postDelayed(this, 1000L);
                return;
            }
            DeviceAssistService.this.handler.removeCallbacks(this);
        }
    };
    BaseDeviceControlAppCallBack callBack = new BaseDeviceControlAppCallBack() { // from class: com.ido.life.boatservice.DeviceAssistService.3
        @Override // com.ido.life.ble.BaseDeviceControlAppCallBack, com.ido.ble.callback.DeviceControlAppCallBack.ICallBack
        public void onFindPhone(boolean z, long j) {
            super.onFindPhone(z, j);
            DeviceAssistService.this.saveLog("收到寻找手机的命令：" + z);
            if (LocalDataManager.getFindPhoneSwitch()) {
                if (z) {
                    DeviceAssistService.this.isRingOrVibrate = true;
                    DeviceAssistService.this.playRingtone(true);
                } else {
                    DeviceAssistService.this.stopRingtone();
                }
            }
        }

        @Override // com.ido.life.ble.BaseDeviceControlAppCallBack, com.ido.ble.callback.DeviceControlAppCallBack.ICallBack
        public void onAntiLostNotice(boolean z, long j) {
            super.onAntiLostNotice(z, j);
        }

        @Override // com.ido.life.ble.BaseDeviceControlAppCallBack, com.ido.ble.callback.DeviceControlAppCallBack.ICallBack
        public void onOneKeySOS(boolean z, long j) {
            super.onOneKeySOS(z, j);
        }

        @Override // com.ido.life.ble.BaseDeviceControlAppCallBack, com.ido.ble.callback.DeviceControlAppCallBack.ICallBack
        public void onControlEvent(DeviceControlAppCallBack.DeviceControlEventType deviceControlEventType, int i) {
            if (deviceControlEventType == DeviceControlAppCallBack.DeviceControlEventType.ANSWER_PHONE) {
                PhoneUtil.answerRingingCall(DeviceAssistService.this.getApplicationContext());
                DeviceAssistService.this.saveLog("接收到接听电话的命令");
            } else if (deviceControlEventType == DeviceControlAppCallBack.DeviceControlEventType.REJECT_PHONE) {
                DeviceAssistService.this.saveLog("接收到拒接电话的命令");
                if (DeviceAssistService.this.isSendCall && FunctionHelper.is302Or303()) {
                    DeviceAssistService.this.saveLog("接收到拒接电话的命令，正在接听电话，不处理");
                    DeviceAssistService.this.isSendCall = false;
                } else {
                    PhoneUtil.endCall(DeviceAssistService.this.getApplicationContext());
                }
            }
        }
    };
    long lastDate = -1;
    private boolean isSendCall = false;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.wrapper = new EventBusWrapper();
        this.wrapper.register(this);
        initBroadcastReceiver();
        if (Build.VERSION.SDK_INT > 29) {
            this.phoneStateListener = new MyPhoneStateListener();
        }
    }

    private void initBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_VOLUME_CHANGED);
        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        intentFilter.addAction("android.intent.action.MEDIA_BUTTON");
        if (this.mBroadcastReceiver == null) {
            this.mBroadcastReceiver = new BroadcastReceiver() { // from class: com.ido.life.boatservice.DeviceAssistService.2
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if (intent.getAction().equals(DeviceAssistService.ACTION_VOLUME_CHANGED)) {
                        DeviceAssistService.this.stopRingtone();
                    }
                }
            };
        }
        registerReceiver(this.mBroadcastReceiver, intentFilter);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        saveLog("开启DeviceAssistService服务");
        boolean zCheckSelfPermission = PermissionUtil.checkSelfPermission(getBaseContext(), PermissionUtil.getOnlyPhonePermission());
        saveLog("hasPhonePermission：" + zCheckSelfPermission);
        if (zCheckSelfPermission) {
            if (Build.VERSION.SDK_INT <= 29) {
                this.phoneStateListener = new MyPhoneStateListener();
            }
            this.tpm = (TelephonyManager) getSystemService("phone");
            registerPhoneListener();
        }
        boolean zCheckSelfPermission2 = PermissionUtil.checkSelfPermission(getBaseContext(), PermissionUtil.getOnlySmsPermission());
        saveLog("hasPhonePermission：" + zCheckSelfPermission2);
        if (zCheckSelfPermission2) {
            this.smsObserver = new SmsObserver(this.handler);
            try {
                getContentResolver().registerContentObserver(this.SMS_URI, true, this.smsObserver);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        BLEManager.unregisterDeviceControlAppCallBack(this.callBack);
        BLEManager.registerDeviceControlAppCallBack(this.callBack);
        return super.onStartCommand(intent, i, i2);
    }

    @Override // com.ido.life.util.eventbus.IHandlerEventBus
    public void handleMessage(BaseMessage baseMessage) {
        if (baseMessage.getType() == 500) {
            stopRingtone();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopRingtone() {
        this.isRingOrVibrate = false;
        try {
            if (this.mMediaPlayer != null && this.mMediaPlayer.isPlaying()) {
                this.mMediaPlayer.stop();
                this.mMediaPlayer.release();
                this.mMediaPlayer = null;
            }
            if (this.mVib == null || !this.mVib.hasVibrator()) {
                return;
            }
            this.mVib.cancel();
            this.mVib = null;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playRingtone(boolean z) {
        boolean zIsPlaying;
        this.handler.removeCallbacks(this.vibrateAndMediaRunnable);
        if (this.mMediaPlayer == null) {
            this.mMediaPlayer = new MediaPlayer();
        }
        if (this.mVib == null && z) {
            this.mVib = (Vibrator) getSystemService("vibrator");
        }
        try {
            zIsPlaying = this.mMediaPlayer.isPlaying();
        } catch (Exception unused) {
            this.mMediaPlayer = null;
            this.mMediaPlayer = new MediaPlayer();
            zIsPlaying = false;
        }
        if (zIsPlaying) {
            this.mMediaPlayer.stop();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mMediaPlayer = new MediaPlayer();
        }
        try {
            this.mMediaPlayer.setDataSource(this, getSystemDefaultRingtoneUri());
            this.mMediaPlayer.setLooping(true);
            this.mMediaPlayer.prepare();
            this.exitTime = System.currentTimeMillis();
            this.mMediaPlayer.start();
            this.handler.postDelayed(this.vibrateAndMediaRunnable, 0L);
        } catch (Exception e2) {
            e2.printStackTrace();
            saveLog("寻找手机，播放铃声异常：" + e2.toString());
        }
        if (z) {
            this.mVib.vibrate(new long[]{500, 2000}, 0);
        }
    }

    private Uri getSystemDefaultRingtoneUri() {
        return RingtoneManager.getActualDefaultRingtoneUri(this, 1);
    }

    private void registerPhoneListener() {
        PhoneStateListener phoneStateListener;
        saveLog("注册电话状态监听");
        TelephonyManager telephonyManager = this.tpm;
        if (telephonyManager == null || (phoneStateListener = this.phoneStateListener) == null) {
            return;
        }
        telephonyManager.listen(phoneStateListener, 32);
    }

    private void unregisterPhoneListener() {
        PhoneStateListener phoneStateListener;
        TelephonyManager telephonyManager = this.tpm;
        if (telephonyManager == null || (phoneStateListener = this.phoneStateListener) == null) {
            return;
        }
        telephonyManager.listen(phoneStateListener, 0);
    }

    public void getSmsFromPhone() {
        Cursor cursorQuery;
        CommonLogUtil.d("getSmsFromPhone");
        ContentResolver contentResolver = getContentResolver();
        String[] strArr = {"body", "address", "person", "read", "date", "_id"};
        if (ContextCompat.checkSelfPermission(IdoApp.getAppContext(), "android.permission.READ_SMS") == 0 && (cursorQuery = contentResolver.query(this.SMS_INBOX, strArr, "read=? and type=?", new String[]{AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE, "1"}, "date desc limit 1")) != null) {
            boolean z = false;
            String string = "138";
            long j = 0;
            String string2 = "0438";
            while (cursorQuery.moveToNext()) {
                string = cursorQuery.getString(cursorQuery.getColumnIndex("address"));
                string2 = cursorQuery.getString(cursorQuery.getColumnIndex("body"));
                j = cursorQuery.getLong(cursorQuery.getColumnIndex("date"));
                z = true;
                saveLog("date:" + j + "; lastDate:" + this.lastDate + "; body:" + string2);
            }
            cursorQuery.close();
            if (!z) {
                saveLog("没有未读短信.....");
                return;
            }
            if (this.lastDate == j) {
                return;
            }
            this.lastDate = j;
            long jCurrentTimeMillis = System.currentTimeMillis();
            saveLog("date:" + j + "; currDate:" + jCurrentTimeMillis + "; ddd:" + ((jCurrentTimeMillis - j) / 1000));
            String contactNameFromPhoneBook = SMSPhoneUtil.getContactNameFromPhoneBook(this, string);
            if (contactNameFromPhoneBook.equals("")) {
                contactNameFromPhoneBook = string;
            }
            saveLog("phoneNumber:" + string + ",contact:" + contactNameFromPhoneBook + ",body:" + string2);
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        saveLog("onDestroy......");
        unregisterPhoneListener();
        if (this.smsObserver != null) {
            getContentResolver().unregisterContentObserver(this.smsObserver);
        }
        BLEManager.unregisterDeviceControlAppCallBack(this.callBack);
        BroadcastReceiver broadcastReceiver = this.mBroadcastReceiver;
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
        this.wrapper.unregister();
    }

    private class MyPhoneStateListener extends PhoneStateListener {
        private MyPhoneStateListener() {
        }

        @Override // android.telephony.PhoneStateListener
        public void onCallForwardingIndicatorChanged(boolean z) {
            super.onCallForwardingIndicatorChanged(z);
            CommonLogUtil.d("onCallForwardingIndicatorChanged");
        }

        @Override // android.telephony.PhoneStateListener
        public void onCallStateChanged(int i, String str) {
            super.onCallStateChanged(i, str);
            DeviceAssistService.this.saveLog("接收到电话状态变化onCallStateChanged ：TelephonyManager   State = " + i + "\tincomingNumber====" + str);
            String contactNameFromPhoneBook = SMSPhoneUtil.getContactNameFromPhoneBook(DeviceAssistService.this, str);
            if (!DeviceAssistService.this.hasFirstRegisterPhone) {
                DeviceAssistService.this.hasFirstRegisterPhone = true;
                return;
            }
            SwitchStatus switchStatus = SPHelper.getSwitchStatus();
            DeviceAssistService.this.saveLog("来电提醒开关状态:" + switchStatus.callReminderSwitched);
            if (switchStatus.callReminderSwitched) {
                DeviceAssistService.this.handleCallReminder(i, str, contactNameFromPhoneBook, switchStatus);
            }
            if (DeviceAssistService.lastCallState == 1 && i == 0) {
                DeviceAssistService.this.checkSendMissCall(str, contactNameFromPhoneBook, switchStatus);
            }
            int unused = DeviceAssistService.lastCallState = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkSendMissCall(final String str, final String str2, final SwitchStatus switchStatus) {
        RxUtils.delay(1000L, new Function0() { // from class: com.ido.life.boatservice.-$$Lambda$DeviceAssistService$xIt8gZ0howVZHxgza1f8_UpyiX0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Integer.valueOf(SMSPhoneUtil.getMissCallCount(IdoApp.getAppContext()));
            }
        }, new Callback<Integer>() { // from class: com.ido.life.boatservice.DeviceAssistService.4
            @Override // com.ido.life.data.listener.Callback
            public void onSuccess(Integer num) {
                if (num != null && num.intValue() > 0) {
                    DeviceAssistService.this.sendMissedCall(str, str2, switchStatus);
                } else {
                    DeviceAssistService.this.saveLog("未接来电数量为0");
                }
            }

            @Override // com.ido.life.data.listener.Callback
            public void onFailed(String str3) {
                DeviceAssistService.this.saveLog("未接来电数量获取异常");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCallReminder(int i, final String str, final String str2, final SwitchStatus switchStatus) {
        if (i == 0) {
            saveLog("空闲");
            if (this.isComingPhone) {
                this.isComingPhone = false;
                if (getSupportFunctionInfo().v2_set_notice_missed_call) {
                    saveLog("支持v2_set_notice_missed_call");
                    BLEManager.missedInComingCall();
                } else {
                    saveLog("不支持v2_set_notice_missed_call");
                    BLEManager.setStopInComingCall();
                }
            }
            NotificationAndCallManager.setCallStateIdle(0);
            this.callHandler.removeCallbacksAndMessages(null);
            return;
        }
        if (i == 1) {
            saveLog("来电");
            NotificationAndCallManager.setCallStateRingingStateListener(1, str, new ICallPhoneInfoBack() { // from class: com.ido.life.boatservice.DeviceAssistService.5
                @Override // com.ido.ntf.callback.ICallPhoneInfoBack
                public void onCallNameAndPhoneNumber(final String str3, final String str4) {
                    if ((TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) || switchStatus.callDelayReminderSwitched) {
                        DeviceAssistService.this.saveLog("来电onCallStateChanged延迟");
                        DeviceAssistService.this.callHandler.removeCallbacksAndMessages(null);
                        DeviceAssistService.this.callHandler.postDelayed(new Runnable() { // from class: com.ido.life.boatservice.DeviceAssistService.5.1
                            @Override // java.lang.Runnable
                            public void run() {
                                DeviceAssistService.this.saveLog("来电onCallStateChanged延迟三秒");
                                MsgNotificationHelper.sendCallReminder2DeviceNew(str4, str3);
                                DeviceAssistService.this.isComingPhone = true;
                            }
                        }, 3000L);
                    } else {
                        DeviceAssistService.this.saveLog("来电onCallStateChanged及时");
                        MsgNotificationHelper.sendCallReminder2DeviceNew(str4, str3);
                        DeviceAssistService.this.isComingPhone = true;
                    }
                }
            });
        } else {
            if (i != 2) {
                return;
            }
            saveLog("摘机");
            if (this.isComingPhone) {
                this.isComingPhone = false;
                BLEManager.setStopInComingCall();
            }
            this.isSendCall = true;
            NotificationAndCallManager.setCallStateOffHook(2);
            this.callHandler.removeCallbacksAndMessages(null);
        }
    }

    public SupportFunctionInfo getSupportFunctionInfo() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        return supportFunctionInfo == null ? new SupportFunctionInfo() : supportFunctionInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMissedCall(String str, String str2, SwitchStatus switchStatus) {
        boolean z;
        SwitchStatus.NotificationSwitch notificationSwitch = switchStatus.notificationSwitch;
        if (notificationSwitch == null) {
            MsgNotificationHelper.saveLog("notificationSwitch 为null");
            return;
        }
        if (isSupportV3Notify()) {
            TranIconBean tranIconBeanUpdateNoticeApp = NoticeAppUtil.updateNoticeApp(Constants.AppPackage.MISS_CALL);
            if (tranIconBeanUpdateNoticeApp == null) {
                return;
            }
            NotificationPara notificationPara = new NotificationPara();
            notificationPara.notify_type = tranIconBeanUpdateNoticeApp.getType();
            notificationPara.contact = str2;
            notificationPara.msg_data = LanguageUtil.getLanguageText(R.string.missed_call);
            notificationPara.phone_number = str;
            int size = 1;
            notificationPara.evt_type = 1;
            String appName = tranIconBeanUpdateNoticeApp.getAppName();
            List<AppNameBean> appNames = TextUtils.isEmpty(Constants.AppPackage.MISS_CALL) ? null : NoticeAppUtil.getAppNames(Constants.AppPackage.MISS_CALL, appName);
            if (ListUtils.INSTANCE.isNotEmpty(appNames)) {
                z = true;
                size = appNames.size();
            } else {
                z = false;
            }
            notificationPara.app_items_len = size;
            notificationPara.items = new ArrayList();
            for (int i = 0; i < size; i++) {
                NotificationPara.AppNames appNames2 = new NotificationPara.AppNames();
                if (z) {
                    AppNameBean appNameBean = appNames.get(i);
                    appNames2.language = appNameBean.getLanguage_type();
                    appNames2.name = appNameBean.getName();
                } else {
                    saveLog("应用：" + Constants.AppPackage.MISS_CALL + " 没有取到名称列表，使用默认名称：" + appName);
                    appNames2.language = i + 1;
                    appNames2.name = appName;
                }
                notificationPara.items.add(appNames2);
            }
            saveLog("发送提醒内容 sendNotification2DeviceV3 v3MessageNotice = " + notificationPara);
            BLEManager.sendNotification(notificationPara);
            return;
        }
        if (notificationSwitch.missedCall) {
            if (MsgNotificationHelper.isSupportOldV3Notify()) {
                V3MessageNotice v3MessageNotice = new V3MessageNotice();
                v3MessageNotice.evtType = V3MessageNotice.TYPE_MISSED_CALL;
                v3MessageNotice.contact = str2;
                v3MessageNotice.phoneNumber = str;
                v3MessageNotice.dataText = LanguageUtil.getLanguageText(R.string.missed_call);
                saveLog("发送V3提醒内容：" + v3MessageNotice.toString());
                BLEManager.setV3MessageNotice(v3MessageNotice);
                return;
            }
            NewMessageInfo newMessageInfo = new NewMessageInfo();
            newMessageInfo.type = 58;
            newMessageInfo.content = LanguageUtil.getLanguageText(R.string.missed_call);
            newMessageInfo.number = str;
            newMessageInfo.name = str2;
            saveLog("发送提醒内容：" + newMessageInfo.toString());
            BLEManager.setNewMessageDetailInfo(newMessageInfo);
        }
    }

    public static boolean isSupportV3Notify() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        if (supportFunctionInfo == null) {
            supportFunctionInfo = new SupportFunctionInfo();
        }
        return supportFunctionInfo.V3_support_set_v3_notify_add_app_name;
    }

    class SmsObserver extends ContentObserver {
        public SmsObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            SwitchStatus.NotificationSwitch notificationSwitch = SPHelper.getSwitchStatus().notificationSwitch;
            if (notificationSwitch == null || !notificationSwitch.masterSwitched) {
                DeviceAssistService.this.saveLog("收到短信onChange...selfChange:" + z + "\n通知的总开关为：false");
                return;
            }
            DeviceAssistService.this.saveLog("收到短信onChange...selfChange:" + z + "\n短信通知的开关为：" + notificationSwitch.smsSwitched);
            if (notificationSwitch.smsSwitched) {
                DeviceAssistService.this.handler.post(new Runnable() { // from class: com.ido.life.boatservice.-$$Lambda$DeviceAssistService$SmsObserver$uv8HLUKPYe0l4D5ZaKFNxlBgXzY
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onChange$0$DeviceAssistService$SmsObserver();
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onChange$0$DeviceAssistService$SmsObserver() {
            try {
                DeviceAssistService.this.getSmsFromPhone();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return super.deliverSelfNotifications();
        }
    }

    public void saveLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getNotificationLogPath(), "DeviceAssistService", str);
    }
}