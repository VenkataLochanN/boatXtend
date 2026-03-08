package com.ido.life.boatservice;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.ido.alexa.AlexaApi;
import com.ido.alexa.callbacks.AlexaSettingCallBack;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.connect.ConnectFailedReason;
import com.ido.ble.callback.DeviceControlAppCallBack;
import com.ido.ble.callback.DeviceParaChangedCallBack;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.DeviceChangedPara;
import com.ido.ble.protocol.model.PhoneVoice;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.RomUtils;
import com.ido.life.ble.BaseConnCallback;
import com.ido.life.ble.BaseDeviceControlAppCallBack;
import com.ido.life.module.home.HomeFragmentPresenter;
import com.ido.life.util.AudioUtils;

/* JADX INFO: loaded from: classes2.dex */
public class MusicControlService extends Service {
    private AudioManager mAudioManager;
    private final Handler mHandler = new Handler();
    BaseDeviceControlAppCallBack mCallBack = new BaseDeviceControlAppCallBack() { // from class: com.ido.life.boatservice.MusicControlService.1
        @Override // com.ido.life.ble.BaseDeviceControlAppCallBack, com.ido.ble.callback.DeviceControlAppCallBack.ICallBack
        public void onControlEvent(DeviceControlAppCallBack.DeviceControlEventType deviceControlEventType, int i) {
            MusicControlService.saveLog("onControlEvent:" + deviceControlEventType.toString() + ", var2 = " + i);
            if (MusicControlService.isSupportMusicControl()) {
                switch (AnonymousClass4.$SwitchMap$com$ido$ble$callback$DeviceControlAppCallBack$DeviceControlEventType[deviceControlEventType.ordinal()]) {
                    case 1:
                        MusicControlService.saveLog("receive startMusic");
                        MusicControlService.this.startMusic();
                        break;
                    case 2:
                        MusicControlService.saveLog("receive pauseMusic");
                        MusicControlService.this.stopMusic();
                        break;
                    case 3:
                        MusicControlService.this.stopMusic();
                        break;
                    case 4:
                        MusicControlService.this.preMusic();
                        break;
                    case 5:
                        MusicControlService.this.nextMusic();
                        break;
                    case 6:
                        MusicControlService.this.volumeUp();
                        break;
                    case 7:
                        MusicControlService.this.volumeDown();
                        break;
                    case 8:
                        if (RomUtils.isOppo()) {
                            MusicControlService.this.tapControlVolume(i);
                        } else {
                            AudioUtils.setVolume(i);
                        }
                        break;
                }
            }
            MusicControlService.saveLog("不支持音乐控制或控制开关未开启");
        }
    };
    private DeviceParaChangedCallBack.ICallBack mPhoneVoiceCallback = new DeviceParaChangedCallBack.ICallBack() { // from class: com.ido.life.boatservice.-$$Lambda$MusicControlService$Bgu8eSFdPS3-B8IaBjdWexy-Ejk
        @Override // com.ido.ble.callback.DeviceParaChangedCallBack.ICallBack
        public final void onChanged(DeviceChangedPara deviceChangedPara) {
            MusicControlService.lambda$new$0(deviceChangedPara);
        }
    };
    SettingCallBack.ICallBack mSettingCallBack = new SettingCallBack.ICallBack() { // from class: com.ido.life.boatservice.MusicControlService.2
        @Override // com.ido.ble.callback.SettingCallBack.ICallBack
        public void onSuccess(SettingCallBack.SettingType settingType, Object obj) {
            if (settingType == SettingCallBack.SettingType.MUSIC_SWITCH) {
                MusicControlService.saveLog("music switch set success");
                if (LocalDataManager.getMusicSwitch()) {
                    BLEManager.enterMusicMode();
                } else {
                    BLEManager.exitMusicMode();
                }
            }
        }

        @Override // com.ido.ble.callback.SettingCallBack.ICallBack
        public void onFailed(SettingCallBack.SettingType settingType) {
            if (settingType == SettingCallBack.SettingType.MUSIC_SWITCH) {
                MusicControlService.saveLog("music switch set failed");
            }
        }
    };
    AlexaSettingCallBack.ICallBack mAlexaSettingCallBack = new AlexaSettingCallBack.ICallBack() { // from class: com.ido.life.boatservice.-$$Lambda$MusicControlService$hc9IkyiMB9RKxKidgcK6SGoMURE
        @Override // com.ido.alexa.callbacks.AlexaSettingCallBack.ICallBack
        public final void onSet(AlexaSettingCallBack.AlexaSettingType alexaSettingType, Object obj) {
            this.f$0.lambda$new$2$MusicControlService(alexaSettingType, obj);
        }
    };
    BaseConnCallback baseConnCallback = new BaseConnCallback() { // from class: com.ido.life.boatservice.MusicControlService.3
        @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectSuccess(String str) {
            super.onConnectSuccess(str);
            VolumeBroadcastReceiver.sendVoice2Device();
        }

        @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectFailed(ConnectFailedReason connectFailedReason, String str) {
            super.onConnectFailed(connectFailedReason, str);
        }

        @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
        public void onConnectBreak(String str) {
            super.onConnectBreak(str);
        }
    };

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    static /* synthetic */ void lambda$new$0(DeviceChangedPara deviceChangedPara) {
        if (deviceChangedPara != null) {
            int i = deviceChangedPara.notifyType;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tapControlVolume(int i) {
        int streamMaxVolume = this.mAudioManager.getStreamMaxVolume(3);
        int streamVolume = this.mAudioManager.getStreamVolume(3);
        int i2 = ((int) ((i / 100.0f) * streamMaxVolume)) - streamVolume;
        boolean z = i2 > 0;
        CommonLogUtil.printAndSave("音乐最大音量：" + streamMaxVolume + ", 当前音量值：" + streamVolume + ", 设置音量百分比：" + i + "");
        for (int i3 = 0; i3 < Math.abs(i2); i3++) {
            if (z) {
                this.mAudioManager.adjustStreamVolume(3, 1, 1);
            } else {
                this.mAudioManager.adjustStreamVolume(3, -1, 1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void volumeDown() {
        saveLog("receive volumeDown");
        sendMusicKeyEvent(25);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void volumeUp() {
        saveLog("receive volumeUp");
        sendMusicKeyEvent(24);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void nextMusic() {
        saveLog("receive nextMusic");
        if (!this.mAudioManager.isMusicActive()) {
            sendMusicKeyEvent(126);
        }
        sendMusicKeyEvent(87);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startMusic() {
        saveLog("receive startMusic: " + this.mAudioManager.isMusicActive());
        saveLog("startMusic");
        sendMusicKeyEvent(85);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopMusic() {
        saveLog("receive stopMusic：" + this.mAudioManager.isMusicActive());
        if (this.mAudioManager.isMusicActive()) {
            saveLog("stopMusic：");
            sendMusicKeyEvent(127);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void preMusic() {
        saveLog("receive preMusic");
        if (!this.mAudioManager.isMusicActive()) {
            sendMusicKeyEvent(126);
        }
        sendMusicKeyEvent(88);
        CommonLogUtil.d("android.os.Build.MANUFACTURER:" + Build.MANUFACTURER);
        if (Build.MANUFACTURER.equalsIgnoreCase("Meizu")) {
            CommonLogUtil.d("魅族手机再发一次");
            this.mHandler.postDelayed(new Runnable() { // from class: com.ido.life.boatservice.-$$Lambda$MusicControlService$KHbleXEYPhwYfPmbrvv5RleS7U4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$preMusic$1$MusicControlService();
                }
            }, 100L);
        }
    }

    public /* synthetic */ void lambda$preMusic$1$MusicControlService() {
        sendMusicKeyEvent(88);
        if (this.mAudioManager.isMusicActive()) {
            return;
        }
        sendMusicKeyEvent(126);
    }

    public /* synthetic */ void lambda$new$2$MusicControlService(AlexaSettingCallBack.AlexaSettingType alexaSettingType, Object obj) {
        if (alexaSettingType != AlexaSettingCallBack.AlexaSettingType.MUSIC || obj == null) {
            return;
        }
        switch ((AlexaSettingCallBack.AlexaSettingEventType) obj) {
            case START:
                saveLog("receive alexa startMusic");
                if (!this.mAudioManager.isMusicActive()) {
                    sendMusicKeyEvent(126);
                }
                break;
            case PAUSE:
                saveLog("receive alexa stopMusic");
                if (this.mAudioManager.isMusicActive()) {
                    sendMusicKeyEvent(127);
                }
                break;
            case STOP:
                if (this.mAudioManager.isMusicActive()) {
                    sendMusicKeyEvent(127);
                }
                break;
            case PREVIOUS:
                preMusic();
                break;
            case NEXT:
                nextMusic();
                break;
            case VOLUME_UP:
                volumeUp();
                break;
            case VOLUME_DOWN:
                volumeDown();
                break;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.boatservice.MusicControlService$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$ido$ble$callback$DeviceControlAppCallBack$DeviceControlEventType;

        static {
            try {
                $SwitchMap$com$ido$alexa$callbacks$AlexaSettingCallBack$AlexaSettingEventType[AlexaSettingCallBack.AlexaSettingEventType.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ido$alexa$callbacks$AlexaSettingCallBack$AlexaSettingEventType[AlexaSettingCallBack.AlexaSettingEventType.PAUSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$ido$alexa$callbacks$AlexaSettingCallBack$AlexaSettingEventType[AlexaSettingCallBack.AlexaSettingEventType.STOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$ido$alexa$callbacks$AlexaSettingCallBack$AlexaSettingEventType[AlexaSettingCallBack.AlexaSettingEventType.PREVIOUS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$ido$alexa$callbacks$AlexaSettingCallBack$AlexaSettingEventType[AlexaSettingCallBack.AlexaSettingEventType.NEXT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$ido$alexa$callbacks$AlexaSettingCallBack$AlexaSettingEventType[AlexaSettingCallBack.AlexaSettingEventType.VOLUME_UP.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$ido$alexa$callbacks$AlexaSettingCallBack$AlexaSettingEventType[AlexaSettingCallBack.AlexaSettingEventType.VOLUME_DOWN.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            $SwitchMap$com$ido$ble$callback$DeviceControlAppCallBack$DeviceControlEventType = new int[DeviceControlAppCallBack.DeviceControlEventType.values().length];
            try {
                $SwitchMap$com$ido$ble$callback$DeviceControlAppCallBack$DeviceControlEventType[DeviceControlAppCallBack.DeviceControlEventType.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$ido$ble$callback$DeviceControlAppCallBack$DeviceControlEventType[DeviceControlAppCallBack.DeviceControlEventType.PAUSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$ido$ble$callback$DeviceControlAppCallBack$DeviceControlEventType[DeviceControlAppCallBack.DeviceControlEventType.STOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$ido$ble$callback$DeviceControlAppCallBack$DeviceControlEventType[DeviceControlAppCallBack.DeviceControlEventType.PREVIOUS.ordinal()] = 4;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$ido$ble$callback$DeviceControlAppCallBack$DeviceControlEventType[DeviceControlAppCallBack.DeviceControlEventType.NEXT.ordinal()] = 5;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$ido$ble$callback$DeviceControlAppCallBack$DeviceControlEventType[DeviceControlAppCallBack.DeviceControlEventType.VOLUME_UP.ordinal()] = 6;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$ido$ble$callback$DeviceControlAppCallBack$DeviceControlEventType[DeviceControlAppCallBack.DeviceControlEventType.VOLUME_DOWN.ordinal()] = 7;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$ido$ble$callback$DeviceControlAppCallBack$DeviceControlEventType[DeviceControlAppCallBack.DeviceControlEventType.VOLUME_PERCENTAGE.ordinal()] = 8;
            } catch (NoSuchFieldError unused15) {
            }
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mAudioManager = (AudioManager) getSystemService("audio");
        CommonLogUtil.d("onCreate");
        BLEManager.registerSettingCallBack(this.mSettingCallBack);
        BLEManager.registerConnectCallBack(this.baseConnCallback);
        BLEManager.registerDeviceControlAppCallBack(this.mCallBack);
        AlexaApi.registerAlexaSettingCallBack(this.mAlexaSettingCallBack);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.media.VOLUME_CHANGED_ACTION");
        registerReceiver(new VolumeBroadcastReceiver(), intentFilter);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        VolumeBroadcastReceiver.sendVoice2Device();
        return super.onStartCommand(intent, i, i2);
    }

    public boolean sendMusicKeyEvent(int i) {
        long jUptimeMillis = SystemClock.uptimeMillis() - 1;
        KeyEvent keyEvent = new KeyEvent(jUptimeMillis, jUptimeMillis, 0, i, 0);
        dispatchMediaKeyToAudioService(keyEvent, i);
        dispatchMediaKeyToAudioService(KeyEvent.changeAction(keyEvent, 1), i);
        return false;
    }

    private void dispatchMediaKeyToAudioService(KeyEvent keyEvent, int i) {
        if (this.mAudioManager == null) {
            this.mAudioManager = (AudioManager) getSystemService("audio");
        }
        saveLog("dispatchMediaKeyToAudioService keyCode：" + i);
        AudioManager audioManager = this.mAudioManager;
        if (audioManager == null) {
            saveLog("dispatchMediaKeyToAudioService, mAudioManager is null");
            return;
        }
        if (i == 24) {
            audioManager.adjustStreamVolume(3, 1, 1);
            return;
        }
        if (i == 25) {
            audioManager.adjustStreamVolume(3, -1, 1);
            return;
        }
        try {
            audioManager.dispatchMediaKeyEvent(keyEvent);
        } catch (Exception e2) {
            e2.printStackTrace();
            saveLog("dispatchMediaKeyEvent Exception：" + e2.toString());
        }
    }

    private static class VolumeBroadcastReceiver extends BroadcastReceiver {
        private static final String EXTRA_VOLUME_STREAM_TYPE = "android.media.EXTRA_VOLUME_STREAM_TYPE";
        private static final String VOLUME_CHANGED_ACTION = "android.media.VOLUME_CHANGED_ACTION";
        private static int sLastVolume = -1;

        private VolumeBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (VOLUME_CHANGED_ACTION.equals(intent.getAction()) && intent.getIntExtra(EXTRA_VOLUME_STREAM_TYPE, -1) == 3) {
                MusicControlService.saveLog("VolumeBroadcastReceiver 监听到手机媒体音量变化");
                sendVoice2Device();
            }
        }

        public static void sendVoice2Device() {
            SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
            if (supportFunctionInfo == null || !supportFunctionInfo.ex_table_main10_set_phone_voice) {
                return;
            }
            if (!BLEManager.isConnected()) {
                MusicControlService.saveLog("sendVoice2Device 设备未连接，不发送音量");
                return;
            }
            if (!MusicControlService.isSupportMusicControl()) {
                MusicControlService.saveLog("不支持音乐控制或音乐开关未开启");
                return;
            }
            if (HomeFragmentPresenter.mIsSyncing) {
                MusicControlService.saveLog("sendVoice2Device 正在同步数据，不发送音量");
                return;
            }
            try {
                AudioManager audioManager = (AudioManager) IdoApp.getAppContext().getSystemService("audio");
                if (audioManager == null) {
                    MusicControlService.saveLog("sendVoice2Device 音量设置异常 manager is null");
                    return;
                }
                int streamVolume = audioManager.getStreamVolume(3);
                if (sLastVolume == streamVolume) {
                    MusicControlService.saveLog("sendVoice2Device 与上一次音量相同，不设置");
                    return;
                }
                PhoneVoice phoneVoice = new PhoneVoice();
                phoneVoice.total_voice = audioManager.getStreamMaxVolume(3);
                phoneVoice.now_voice = streamVolume;
                MusicControlService.saveLog("sendVoice2Device 音量设置 ：" + phoneVoice.toString());
                BLEManager.setPhoneVoice(phoneVoice);
            } catch (Exception e2) {
                MusicControlService.saveLog("sendVoice2Device 音量设置异常 ：" + e2.toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isSupportMusicControl() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        return supportFunctionInfo != null && supportFunctionInfo.bleControlMusic && LocalDataManager.getMusicSwitch();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void saveLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getNotificationLogPath(), "MusicControlService", str);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        BLEManager.unregisterSettingCallBack(this.mSettingCallBack);
        BLEManager.unregisterConnectCallBack(this.baseConnCallback);
        BLEManager.unregisterDeviceControlAppCallBack(this.mCallBack);
        AlexaApi.unregisterAlexaSettingCallBack(this.mAlexaSettingCallBack);
    }
}