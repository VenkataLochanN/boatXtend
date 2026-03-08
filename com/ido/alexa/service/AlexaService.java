package com.ido.alexa.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.ido.alexa.AlexaApi;
import com.ido.alexa.AlexaApp;
import com.ido.alexa.AlexaConstant;
import com.ido.alexa.R;
import com.ido.alexa.bean.AvsException;
import com.ido.alexa.callbacks.AlexaSettingCallBack;
import com.ido.alexa.callbacks.BaseVoiceCallBack;
import com.ido.alexa.callbacks.ChunkPostCallback;
import com.ido.alexa.callbacks.DeviceStatusCallBack;
import com.ido.alexa.callbacks.HandleDirectiveCallback;
import com.ido.alexa.callbacks.IAlexaCallBack;
import com.ido.alexa.callbacks.ImplAsyncCallback;
import com.ido.alexa.data.ApiResponse;
import com.ido.alexa.data.AvsEnqueuedItem;
import com.ido.alexa.data.AvsExpectSpeechItem;
import com.ido.alexa.data.AvsItem;
import com.ido.alexa.data.AvsSpeakItem;
import com.ido.alexa.data.AvsTemplateItem;
import com.ido.alexa.data.Directive;
import com.ido.alexa.data.Event;
import com.ido.alexa.log.AlexaLogPathImpl;
import com.ido.alexa.log.AlexaLogUtil;
import com.ido.alexa.log.AlexaLogWriter;
import com.ido.alexa.manager.AlexaAudioEventManger;
import com.ido.alexa.manager.AlexaManager;
import com.ido.alexa.manager.LoadSmartHomeManager;
import com.ido.alexa.manager.SpManager;
import com.ido.alexa.util.AlexaCustomSkillUtil;
import com.ido.alexa.util.AlexaNewAlarmUtil;
import com.ido.alexa.util.AlexaSendCmdToDeviceUtil;
import com.ido.alexa.util.AudioPlayer;
import com.ido.alexa.util.ComUtil;
import com.ido.alexa.util.NetworkUtil;
import com.ido.alexa.util.UserInactivityTimeTaskUtil;
import com.ido.ble.BLEManager;
import com.ido.ble.callback.VoiceCallBack;
import com.ido.ble.protocol.model.VoiceData;
import com.ido.ble.protocol.model.VoiceLoginState;
import com.ido.ble.protocol.model.VoicePcmSectionData;
import com.ido.ble.protocol.model.VoiceRecognizeState;
import com.ido.ble.protocol.model.VoiceToText;
import com.ido.ble.protocol.model.VoiceTranState;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.commons.io.IOUtils;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaService extends Service implements HandleDirectiveCallback {
    private static final String ACTION_NET_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";
    private static final int APP_CONTROL_STOP = 8;
    private static final int APP_CONTROL_STOP_CLOSE_STREAM = 1000;
    public static final String AUDIO_DATA = "recordBytes";
    private static final int CHANGE_ALEXA_ALARM = 11;
    private static final int EXIT_ALEXA_CMD = 10;
    private static final int MAX_VOLUME = 100;
    private static final int MIN_VOLUME = 0;
    private static final int PARSE_TIMEOUT_LEN = 50000;
    private boolean isTimeOutForUnReceiveVoice;
    private int loginFailCount;
    private String mAudioEventRequestId;
    private AudioPlayer mAudioPlayer;
    private ChunkPostCallback mChunkPostCallback;
    private int recordFailedCount;
    private NetworkInfo.State lastNetStatus = NetworkInfo.State.UNKNOWN;
    private boolean isReplyRangeControlerResult = true;
    private boolean isReplyToggleControlerResult = true;
    private boolean isThinking = false;
    private final Handler mHandler = new Handler();
    private final ByteArrayOutputStream totalVoiceData = new ByteArrayOutputStream();
    private byte[] mp3Audio = null;
    private int lastVolume = -1;
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.ido.alexa.service.AlexaService.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || TextUtils.isEmpty(intent.getAction())) {
                return;
            }
            if (TextUtils.equals(AlexaService.ACTION_NET_CHANGE, intent.getAction())) {
                boolean zIsConnected = NetworkUtil.isConnected(context);
                AlexaLogUtil.d(" isConnected=" + zIsConnected + "  lastNetStatus=" + AlexaService.this.lastNetStatus);
                if (!zIsConnected && (AlexaService.this.lastNetStatus == NetworkInfo.State.UNKNOWN || AlexaService.this.lastNetStatus != NetworkInfo.State.DISCONNECTED)) {
                    AlexaService.this.setVoiceLoginState(2);
                }
                AlexaService.this.lastNetStatus = zIsConnected ? NetworkInfo.State.CONNECTED : NetworkInfo.State.DISCONNECTED;
                return;
            }
            if (TextUtils.equals("android.intent.action.TIMEZONE_CHANGED", intent.getAction())) {
                TimeZone timeZone = TimeZone.getDefault();
                AlexaLogUtil.d("时区发生改变==" + timeZone.getID());
                AlexaApi.setTimeZone(timeZone.getID());
            }
        }
    };
    DeviceStatusCallBack deviceStatusCallBack = new DeviceStatusCallBack() { // from class: com.ido.alexa.service.AlexaService.2
        @Override // com.ido.alexa.callbacks.DeviceStatusCallBack
        public void reboot() {
            AlexaAudioEventManger.getInstance().lastSyncStateTime = 0L;
            AlexaLogUtil.printAndSave("设备重启了");
            AlexaSendCmdToDeviceUtil.switchDeviceForAlexa(false);
        }

        @Override // com.ido.alexa.callbacks.DeviceStatusCallBack
        public void onConnectSuccess(String str) {
            AlexaLogUtil.printAndSave("onConnectSuccess=" + str);
            AlexaSendCmdToDeviceUtil.updateDeviceId();
            AlexaSendCmdToDeviceUtil.switchDeviceForAlexa(false);
        }

        @Override // com.ido.alexa.callbacks.DeviceStatusCallBack
        public void onConnectFailed() {
            AlexaLogUtil.printAndSave("onConnectFailed");
            AlexaSendCmdToDeviceUtil.offline();
        }

        @Override // com.ido.alexa.callbacks.DeviceStatusCallBack
        public void onConnectBreak() {
            AlexaLogUtil.printAndSave("onConnectBreak");
            AlexaSendCmdToDeviceUtil.offline();
        }

        @Override // com.ido.alexa.callbacks.DeviceStatusCallBack
        public void onSetDevicefirmwareVersion(long j) {
            AlexaLogUtil.printAndSave("onSetDevicefirmwareVersion---firmwareVersion=" + j);
            SpManager.setFirmwareVersion(j);
            AlexaAudioEventManger.getInstance().sendEvent(Event.getSoftwareInfoEvent(j), null);
        }

        @Override // com.ido.alexa.callbacks.DeviceStatusCallBack
        public void onHornVoiceChanged(int i) {
            if (i == 0 || i == 100) {
                if (AlexaService.this.lastVolume != i) {
                    AlexaLogUtil.printAndSave("onHornVoiceChanged---volume=" + i);
                    AlexaAudioEventManger.getInstance().sendEvent(Event.getVolumeChangedEvent((long) i), null);
                }
            } else {
                AlexaLogUtil.printAndSave("onHornVoiceChanged---volume=" + i);
                AlexaAudioEventManger.getInstance().sendEvent(Event.getVolumeChangedEvent((long) i), null);
            }
            AlexaService.this.lastVolume = i;
        }
    };
    BaseVoiceCallBack baseVoiceCallBack = new BaseVoiceCallBack() { // from class: com.ido.alexa.service.AlexaService.3
        @Override // com.ido.alexa.callbacks.BaseVoiceCallBack, com.ido.ble.callback.VoiceCallBack.ICallBack
        public void onGetPcmSectionData(VoicePcmSectionData voicePcmSectionData) {
            super.onGetPcmSectionData(voicePcmSectionData);
            if (voicePcmSectionData.pcm_voice_data != null) {
                if (AlexaService.this.mChunkPostCallback != null) {
                    AlexaService.this.mChunkPostCallback.addVoiceData(voicePcmSectionData.pcm_voice_data);
                }
                try {
                    AlexaService.this.totalVoiceData.write(voicePcmSectionData.pcm_voice_data);
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }

        @Override // com.ido.alexa.callbacks.BaseVoiceCallBack, com.ido.ble.callback.VoiceCallBack.ICallBack
        public void onVoiceTranState(VoiceTranState voiceTranState) throws Throwable {
            super.onVoiceTranState(voiceTranState);
            AlexaService.this.handlerVoiceTranState(voiceTranState);
        }

        @Override // com.ido.alexa.callbacks.BaseVoiceCallBack, com.ido.ble.callback.VoiceCallBack.ICallBack
        public void onControlResult(VoiceCallBack.VoiceControlType voiceControlType, boolean z) {
            super.onControlResult(voiceControlType, z);
            if (voiceControlType == VoiceCallBack.VoiceControlType.VOICE_TO_TEXT) {
                AlexaLogUtil.printAndSave("发送语音文本===完成，发语音");
                if (AlexaService.this.mp3Audio != null) {
                    AlexaService.this.mAudioPlayer.saveAudio(AlexaService.this.mp3Audio);
                }
            }
        }
    };
    ImplAsyncCallback<ApiResponse, Throwable> mRecordEventCallback = new ImplAsyncCallback<ApiResponse, Throwable>() { // from class: com.ido.alexa.service.AlexaService.5
        @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
        public void start(String str) {
            super.start(str);
            AlexaService.this.mAudioEventRequestId = str;
        }

        @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
        public void startParse() {
            super.startParse();
            AlexaApi.onStartPrase();
        }

        @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
        public void success(ApiResponse apiResponse) {
            AlexaService.this.reset();
            AlexaLogUtil.d("sendRecord success");
            if (apiResponse.getResponseCode() == 200) {
                AlexaService.this.onAlexaResponse(apiResponse.getAvsItems());
                return;
            }
            AlexaLogUtil.d(apiResponse.getMessage() + " isTimeOutForUnReceiveVoice=" + AlexaService.this.isTimeOutForUnReceiveVoice);
            if (!AlexaService.this.isTimeOutForUnReceiveVoice) {
                AlexaService.this.setVoiceRecognizeState(1);
                AlexaApi.handlerAudioResult("");
            }
            AlexaService.access$1408(AlexaService.this);
        }

        @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
        public void failure(Throwable th) {
            AlexaLogUtil.printAndSave("sendRecord failure : " + th.getMessage() + "  isTimeOutForUnReceiveVoice=" + AlexaService.this.isTimeOutForUnReceiveVoice);
            if (!AlexaService.this.isTimeOutForUnReceiveVoice && AlexaService.this.isThinking) {
                if (!TextUtils.isEmpty(th.getMessage()) && th.getMessage().contains("timeout")) {
                    AlexaService.this.setVoiceRecognizeState(2);
                } else if (TextUtils.isEmpty(th.getMessage()) || !th.getMessage().contains("Canceled")) {
                    AlexaService.this.setVoiceRecognizeState(1);
                }
            }
            AlexaService.access$1408(AlexaService.this);
            AlexaService.this.reset();
            AlexaApi.handlerAudioResult("sendRecord failure : " + th.getMessage());
        }

        @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
        public void authorize() {
            AlexaService.this.reset();
            AlexaLogUtil.printAndSave("authorize");
            AlexaService.this.eauthorizerAmazon();
        }
    };
    Runnable timeOutRunable = new Runnable() { // from class: com.ido.alexa.service.AlexaService.6
        @Override // java.lang.Runnable
        public void run() {
            AlexaLogUtil.printAndSave("解析超时--cancelCall");
            AlexaApi.cancelCall();
            AlexaService.this.setVoiceRecognizeState(2);
            AlexaApi.handlerAudioResult("timout");
        }
    };

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    static /* synthetic */ int access$1408(AlexaService alexaService) {
        int i = alexaService.recordFailedCount;
        alexaService.recordFailedCount = i + 1;
        return i;
    }

    static /* synthetic */ int access$808(AlexaService alexaService) {
        int i = alexaService.loginFailCount;
        alexaService.loginFailCount = i + 1;
        return i;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        AlexaLogUtil.printAndSave("AlexaService onCreate");
        createNotificationChannel();
        BLEManager.registerVoiceCallBack(this.baseVoiceCallBack);
        AlexaApi.registerDeviceStatusCallBack(this.deviceStatusCallBack);
        login(true);
        AlexaApi.registerHandleDirectiveCallback(this);
        registerNetworkBroadcast();
        LoadSmartHomeManager.init();
        this.mAudioPlayer = new AudioPlayer();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, getPackageName());
            NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
            if (notificationManager == null) {
                return;
            }
            NotificationChannel notificationChannel = new NotificationChannel(getPackageName(), getString(R.string.app_name), 3);
            notificationChannel.setSound(null, null);
            notificationManager.createNotificationChannel(notificationChannel);
            builder.setChannelId(getPackageName());
            startForeground(1, builder.build());
        }
    }

    private void registerNetworkBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_NET_CHANGE);
        intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        registerReceiver(this.broadcastReceiver, intentFilter);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) throws Throwable {
        createNotificationChannel();
        if (intent != null && intent.getExtras() != null) {
            AlexaLogUtil.d("AlexaLogin onStartCommand");
            this.isTimeOutForUnReceiveVoice = false;
            VoiceData voiceData = new VoiceData();
            voiceData.voiceFile = intent.getExtras().getByteArray(AUDIO_DATA);
            sendCompleteVoiceData(voiceData);
            makeAlexaVoiceFile(voiceData);
        }
        return super.onStartCommand(intent, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlerVoiceTranState(VoiceTranState voiceTranState) throws Throwable {
        this.isTimeOutForUnReceiveVoice = false;
        AlexaLogUtil.printAndSave("onVoiceTranState : " + voiceTranState.state);
        int i = voiceTranState.state;
        if (i == 1) {
            this.isThinking = false;
            this.isReplyRangeControlerResult = true;
            this.isReplyToggleControlerResult = true;
            this.mp3Audio = null;
            this.totalVoiceData.reset();
            chunkSend();
            AlexaApi.onStartAlexa();
            UserInactivityTimeTaskUtil.stop();
            return;
        }
        if (i != 2) {
            if (i == 3) {
                this.isTimeOutForUnReceiveVoice = true;
                AlexaApi.cancelCall();
                setVoiceRecognizeState(2);
                AlexaApi.handlerAudioResult("");
                AlexaApi.onEndAlexa();
                return;
            }
            if (i == 5) {
                this.isThinking = false;
                login(false);
                AlexaApi.onStartAlexa();
                return;
            } else if (i != 8 && i != 1000) {
                if (i != 10) {
                    if (i != 11) {
                        return;
                    }
                    AlexaNewAlarmUtil.getInstance().getDeviceAlexaAlarm();
                    return;
                } else {
                    this.isTimeOutForUnReceiveVoice = true;
                    AlexaApi.cancelCall();
                    AlexaApi.handlerAudioResult("");
                    AlexaApi.onEndAlexa();
                    UserInactivityTimeTaskUtil.start();
                    return;
                }
            }
        }
        AlexaApi.onEndAlexa();
        if (this.isThinking) {
            return;
        }
        this.isThinking = true;
        this.mAudioEventRequestId = "";
        VoiceData voiceData = new VoiceData();
        voiceData.size = this.totalVoiceData.toByteArray().length;
        voiceData.voiceFile = this.totalVoiceData.toByteArray();
        ChunkPostCallback chunkPostCallback = this.mChunkPostCallback;
        if (chunkPostCallback != null) {
            chunkPostCallback.endRecording(voiceTranState.state);
        }
        makeAlexaVoiceFile(voiceData);
    }

    private void sendCompleteVoiceData(VoiceData voiceData) {
        AlexaLogUtil.d("start send Complete voiceData");
        if (this.isTimeOutForUnReceiveVoice) {
            return;
        }
        if (NetworkUtil.isConnected(this)) {
            sendVoice(voiceData.voiceFile);
            startDownChannel();
        } else {
            setVoiceLoginState(2);
        }
    }

    private void login(final boolean z) {
        if (NetworkUtil.isConnected(this)) {
            if (AlexaApp.getAppContext() == null) {
                AlexaApp.setAppContext(this);
            }
            AlexaApi.getToken(this, new IAlexaCallBack() { // from class: com.ido.alexa.service.AlexaService.4
                @Override // com.ido.alexa.callbacks.IAlexaCallBack
                public void success(String str) {
                    AlexaService.this.loginFailCount = 0;
                    AlexaLogUtil.printAndSave("login onSuccess  isFirstLogin= " + z);
                    if (!z) {
                        AlexaService.this.setVoiceLoginState(0);
                    } else {
                        AlexaAudioEventManger.getInstance().syncGateWay();
                        UserInactivityTimeTaskUtil.start();
                    }
                }

                @Override // com.ido.alexa.callbacks.IAlexaCallBack
                public void failure(AvsException avsException) {
                    AlexaLogUtil.d("login onFailure");
                    if (z) {
                        return;
                    }
                    AlexaSettingCallBack.onCallback(AlexaSettingCallBack.AlexaSettingType.ALEXA_UN_LOGIN, "");
                    AlexaService.access$808(AlexaService.this);
                    AlexaService.this.setVoiceLoginState(1);
                    if (AlexaService.this.loginFailCount >= 3) {
                        AlexaService.this.loginFailCount = 0;
                        AlexaSettingCallBack.onCallback(AlexaSettingCallBack.AlexaSettingType.ALEXA_HELP, "");
                    }
                }
            });
        } else {
            if (z) {
                return;
            }
            setVoiceLoginState(2);
        }
    }

    private void startDownChannel() {
        Intent intent = new Intent(this, (Class<?>) AlexaDownChannelService.class);
        if (this.recordFailedCount > 2) {
            this.recordFailedCount = 0;
            intent.putExtra(AlexaDownChannelService.NEED_CREATE_DOWNCHANNEL, true);
            AlexaLogUtil.printAndSave("recordFailedCount > 2 ");
        }
        ComUtil.startService(this, intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVoiceLoginState(int i) {
        if (BLEManager.isConnected()) {
            VoiceLoginState voiceLoginState = new VoiceLoginState();
            voiceLoginState.log_state = i;
            BLEManager.setVoiceLoginState(voiceLoginState);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVoiceRecognizeState(int i) {
        if (BLEManager.isConnected()) {
            VoiceRecognizeState voiceRecognizeState = new VoiceRecognizeState();
            voiceRecognizeState.phone_state = i;
            BLEManager.setVoiceRecognizeState(voiceRecognizeState);
            AlexaLogUtil.d("phoneState=" + i);
        }
    }

    private void sendVoice(byte[] bArr) {
        this.mHandler.removeCallbacks(this.timeOutRunable);
        this.mHandler.postDelayed(this.timeOutRunable, 50000L);
        AlexaApi.sendRecord(AlexaConstant.AUDIO_PCM, bArr, this.mRecordEventCallback);
    }

    private void chunkSend() {
        AlexaLogUtil.d("start chunk send voiceData");
        this.mHandler.removeCallbacks(this.timeOutRunable);
        this.mHandler.postDelayed(this.timeOutRunable, 50000L);
        this.mChunkPostCallback = AlexaApi.chunkSendRecordRequest(AlexaConstant.AUDIO_PCM, this.mRecordEventCallback);
        startDownChannel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reset() {
        this.mHandler.removeCallbacksAndMessages(null);
        this.isThinking = false;
        this.mAudioEventRequestId = "";
    }

    @Override // com.ido.alexa.callbacks.HandleDirectiveCallback
    public void handlerRangeControlerResult() {
        this.isReplyRangeControlerResult = false;
    }

    @Override // com.ido.alexa.callbacks.HandleDirectiveCallback
    public void handlerToggleControlerResult(boolean z) {
        this.isReplyToggleControlerResult = !z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAlexaResponse(List<AvsItem> list) {
        Directive.Payload payLoad;
        StringBuilder sb;
        int i;
        String string;
        String mainTitle;
        boolean z = AlexaSendCmdToDeviceUtil.getSupportFunctionInfo().V3_alexa_set_weather;
        this.mp3Audio = null;
        if (list == null || list.size() <= 0) {
            payLoad = null;
            sb = null;
            i = 0;
            string = "";
            mainTitle = string;
        } else {
            payLoad = null;
            sb = null;
            i = 0;
            string = "";
            mainTitle = string;
            for (AvsItem avsItem : list) {
                if (avsItem instanceof AvsTemplateItem) {
                    AvsTemplateItem avsTemplateItem = (AvsTemplateItem) avsItem;
                    if (avsTemplateItem.isBodyType()) {
                        Directive.Payload payLoad2 = avsTemplateItem.getPayLoad();
                        mainTitle = payLoad2.getTitle().getMainTitle();
                        String textField = payLoad2.getTextField();
                        payLoad2.getTitle().getSubTitle();
                        string = textField;
                    } else if (avsTemplateItem.isListType()) {
                        Directive.Payload payLoad3 = avsTemplateItem.getPayLoad();
                        StringBuilder sb2 = new StringBuilder();
                        if (payLoad3.getListItems() != null && payLoad3.getListItems().size() > 0) {
                            sb2.append(payLoad3.getTitle().getMainTitle());
                            sb2.append(IOUtils.LINE_SEPARATOR_UNIX);
                            for (Directive.Payload.ListItem listItem : payLoad3.getListItems()) {
                                sb2.append(listItem.getLeftTextField());
                                sb2.append(listItem.getRightTextField());
                                sb2.append(IOUtils.LINE_SEPARATOR_UNIX);
                            }
                            AlexaLogUtil.d("AvsListTemplateItem = " + sb2.toString());
                        }
                        sb = sb2;
                    } else if (avsTemplateItem.isWeatherType()) {
                        payLoad = avsTemplateItem.getPayLoad();
                    }
                } else if (avsItem instanceof AvsEnqueuedItem) {
                    AvsEnqueuedItem avsEnqueuedItem = (AvsEnqueuedItem) avsItem;
                    Directive.Payload payLoad4 = avsEnqueuedItem.getPayLoad();
                    if (parseCaption(payLoad4.getCaption()).length() > 0) {
                        string = parseCaption(payLoad4.getCaption());
                    }
                    if (avsEnqueuedItem.getAudioData() != null) {
                        AlexaLogUtil.d("avsEnqueuedItem.getAudioData()");
                        this.mp3Audio = avsEnqueuedItem.getAudioData();
                    }
                } else if (avsItem instanceof AvsExpectSpeechItem) {
                    i = 1;
                } else if (avsItem instanceof AvsSpeakItem) {
                    AvsSpeakItem avsSpeakItem = (AvsSpeakItem) avsItem;
                    AlexaLogUtil.d("AvsSpeakItem = " + avsSpeakItem.toString());
                    if (this.mp3Audio == null && avsSpeakItem.getAudio() != null) {
                        this.mp3Audio = avsSpeakItem.getAudio();
                    }
                }
            }
        }
        if (i != 0) {
            this.mp3Audio = null;
        }
        if (!BLEManager.isConnected() || this.isTimeOutForUnReceiveVoice || !this.isReplyRangeControlerResult) {
            this.mp3Audio = null;
        } else if (z && payLoad != null) {
            AlexaCustomSkillUtil.sendWeather(payLoad, this.mp3Audio, this.mAudioPlayer);
        } else {
            if (sb != null) {
                string = sb.toString();
            }
            if (TextUtils.isEmpty(string)) {
                setVoiceRecognizeState(1);
            } else {
                VoiceToText voiceToText = new VoiceToText();
                voiceToText.title = "";
                voiceToText.text_content = string;
                voiceToText.flag_is_continue = i;
                if (this.isReplyToggleControlerResult) {
                    BLEManager.setVoiceToText(voiceToText);
                }
            }
        }
        AlexaLogUtil.printAndSave("isV3Weather=" + z + ",title= " + mainTitle + "  content= " + string + " ,isConnected=" + BLEManager.isConnected() + " ,isTimeOutForUnReceiveVoice=" + this.isTimeOutForUnReceiveVoice + " , isReplyRangeControlerResult=" + this.isReplyRangeControlerResult + " , isReplyToggleControlerResult=" + this.isReplyToggleControlerResult);
        this.isReplyRangeControlerResult = true;
        this.isReplyToggleControlerResult = true;
        if (TextUtils.isEmpty(string)) {
            AlexaApi.handlerAudioResult("content is empty");
            this.recordFailedCount++;
            return;
        }
        AlexaApi.handlerAudioResult("title= " + mainTitle + "  content= " + string);
        if (string.contains("reagiert nicht") || string.contains("reagiert leider nicht") || string.contains("not responding") || string.contains("isn't responding") || string.contains("ne répond pas") || string.contains("no responde") || string.contains("non risponde") || string.contains("não está respondendo") || string.contains("が応答していません")) {
            this.recordFailedCount = 3;
        } else {
            this.recordFailedCount = 0;
        }
    }

    private String parseCaption(Directive.Payload.Caption caption) {
        return (caption == null || TextUtils.isEmpty(caption.getContent())) ? "" : caption.getContent();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        BLEManager.unregisterVoiceCallBack(this.baseVoiceCallBack);
        AlexaApi.unregisterDeviceStatusCallBack();
        AlexaApi.unregisterHandleDirectiveCallback(this);
        AlexaLogUtil.printAndSave("  alexaService onDestroy");
        unregisterReceiver(this.broadcastReceiver);
    }

    @Override // com.ido.alexa.callbacks.HandleDirectiveCallback
    public void handlerTimer(String str, int i) {
        AlexaSendCmdToDeviceUtil.setTimer(str, i);
    }

    @Override // com.ido.alexa.callbacks.HandleDirectiveCallback
    public void handlerAlarm(String str, Date date, long j) throws Throwable {
        stopRecord(this.mAudioEventRequestId);
        AlexaSendCmdToDeviceUtil.setAlexaAlarm(str, date, j);
    }

    @Override // com.ido.alexa.callbacks.HandleDirectiveCallback
    public void handlerReminder(String str, Date date, long j, String str2) {
        AlexaSendCmdToDeviceUtil.setReminder(str, date, j, str2);
    }

    @Override // com.ido.alexa.callbacks.HandleDirectiveCallback
    public void cancelAlarm(String str) {
        AlexaSendCmdToDeviceUtil.deletAlert(str);
    }

    @Override // com.ido.alexa.callbacks.HandleDirectiveCallback
    public void cancelAlarms(List<String> list) {
        AlexaSendCmdToDeviceUtil.deletAlerts(list);
    }

    @Override // com.ido.alexa.callbacks.HandleDirectiveCallback
    public void eauthorizerAmazon() {
        AlexaLogUtil.printAndSave("eauthorizerAmazon");
        AlexaManager.getInstance().logout(this, null);
        AlexaApi.onAlexaLogout();
        setVoiceLoginState(1);
    }

    @Override // com.ido.alexa.callbacks.HandleDirectiveCallback
    public void stopRecord(String str) throws Throwable {
        if (TextUtils.equals(this.mAudioEventRequestId, str)) {
            this.mAudioEventRequestId = "";
            AlexaLogUtil.d("app下发停止录音指令");
            VoiceTranState voiceTranState = new VoiceTranState();
            voiceTranState.state = 1000;
            handlerVoiceTranState(voiceTranState);
            BLEManager.stopVoiceRecognize();
        }
    }

    @Override // com.ido.alexa.callbacks.HandleDirectiveCallback
    public void handlerNotification(boolean z) {
        AlexaSendCmdToDeviceUtil.setNotifyStatus(z);
    }

    private void makeAlexaVoiceFile(VoiceData voiceData) throws Throwable {
        if (AlexaApp.isIsTest()) {
            String alexaPCMPath = AlexaLogPathImpl.getInstance().getAlexaPCMPath();
            String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(new Date()) + AlexaLogWriter.PCM_FILE_PREFIX_NAME;
            if (voiceData == null || voiceData.voiceFile == null || voiceData.voiceFile.length <= 0) {
                return;
            }
            File file = new File(alexaPCMPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(alexaPCMPath + str);
            if (!file2.exists()) {
                try {
                    file2.createNewFile();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            FileOutputStream fileOutputStream = null;
            try {
                try {
                    try {
                        FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
                        try {
                            fileOutputStream2.write(voiceData.voiceFile);
                            AlexaApi.onPcmFileName(str);
                            fileOutputStream2.close();
                        } catch (Exception e3) {
                            e = e3;
                            fileOutputStream = fileOutputStream2;
                            e.printStackTrace();
                            if (fileOutputStream == null) {
                            } else {
                                fileOutputStream.close();
                            }
                        } catch (Throwable th) {
                            th = th;
                            fileOutputStream = fileOutputStream2;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Exception e5) {
                    e = e5;
                }
            } catch (IOException e6) {
                e6.printStackTrace();
            }
        }
    }
}