package com.ido.alexa.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.ido.alexa.R;
import com.ido.alexa.ResponseHandler;
import com.ido.alexa.bean.AvsException;
import com.ido.alexa.callbacks.ImplAsyncCallback;
import com.ido.alexa.data.Directive;
import com.ido.alexa.log.AlexaLogUtil;
import com.ido.alexa.manager.AlexaAudioEventManger;
import com.ido.alexa.manager.AlexaManager;
import com.ido.alexa.net.ApiParser;
import com.ido.alexa.util.AlexaSendCmdToDeviceUtil;
import com.ido.alexa.util.ClientUtil;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSource;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaDownChannelService extends Service {
    private static final String BOUNDARY = "--------abcde123";
    private static final String CONTENT_TYPE = "Content-Type: application/json";
    public static final String IS_SYNC_NOTIFY_STATUS = "isSyncNotifyStatus";
    public static final String NEED_CREATE_DOWNCHANNEL = "needCreateDownchannle";
    public static final String NEED_CREATE_SMARTHOME = "needCreateSmartHome";
    public static final String OFFLINE_STATUS = "offLineStatus";
    public static final String RE_CREATE_DOWNCHANNEL = "reCreateDownchannle";
    private static final String TAG = "Alexa-DownChannel";
    private AlexaManager alexaManager;
    private NotificationCompat.Builder builder;
    private Call currentCall;
    private Response downChannelResponse;
    private PingTimerTask mPingTimerTask;
    boolean needCreate;
    private int retryCount;
    private Handler runnableHandler;
    private Timer updateTimer;
    private final int delayTime = 120000;
    private boolean hasResult = true;
    private boolean isSyncNotifyState = true;
    boolean needCreateSmartHome = true;
    Runnable delayRecreateRunnable = new Runnable() { // from class: com.ido.alexa.service.AlexaDownChannelService.2
        @Override // java.lang.Runnable
        public void run() {
            AlexaDownChannelService.this.reCreateDownChannel();
        }
    };
    Runnable delayRecreateSmartHomeRunnable = new Runnable() { // from class: com.ido.alexa.service.AlexaDownChannelService.3
        @Override // java.lang.Runnable
        public void run() {
            AlexaDownChannelService.this.createSmartHome();
        }
    };
    Runnable timeoutRunnable = new Runnable() { // from class: com.ido.alexa.service.AlexaDownChannelService.4
        @Override // java.lang.Runnable
        public void run() {
            AlexaDownChannelService.this.log("create downChannel timeout");
            if (AlexaDownChannelService.this.currentCall != null) {
                AlexaDownChannelService.this.currentCall.cancel();
            }
        }
    };
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.ido.alexa.service.AlexaDownChannelService.7
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            boolean zIsNetConnected = AlexaDownChannelService.this.isNetConnected();
            AlexaDownChannelService.this.log("isNetConnected=" + zIsNetConnected);
            if (!zIsNetConnected) {
                AlexaDownChannelService.this.downChannelResponse = null;
                AlexaDownChannelService.this.hasResult = true;
            } else if (AlexaDownChannelService.this.downChannelResponse == null && AlexaDownChannelService.this.hasResult) {
                AlexaDownChannelService.this.reCreateDownChannel();
            }
        }
    };

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    static /* synthetic */ int access$1208(AlexaDownChannelService alexaDownChannelService) {
        int i = alexaDownChannelService.retryCount;
        alexaDownChannelService.retryCount = i + 1;
        return i;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        log(" Launched downChannelService");
        createChannel();
        this.alexaManager = AlexaManager.getInstance();
        this.runnableHandler = new Handler(Looper.getMainLooper());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(this.broadcastReceiver, intentFilter);
    }

    private class PingTimerTask extends TimerTask {
        private PingTimerTask() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            AlexaDownChannelService.this.alexaManager.getAuthorizeManager().checkLoggedIn(AlexaDownChannelService.this, new ImplAsyncCallback<Boolean, Throwable>() { // from class: com.ido.alexa.service.AlexaDownChannelService.PingTimerTask.1
                @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
                public void success(Boolean bool) {
                    super.success(bool);
                    ClientUtil.getTLS12OkHttpClient().newCall(new Request.Builder().url(AlexaDownChannelService.this.alexaManager.getPingUrl()).get().addHeader("Authorization", "Bearer " + AlexaDownChannelService.this.alexaManager.getToken()).build()).enqueue(new Callback() { // from class: com.ido.alexa.service.AlexaDownChannelService.PingTimerTask.1.1
                        @Override // okhttp3.Callback
                        public void onFailure(Call call, IOException iOException) {
                            AlexaDownChannelService.this.log(" ping-Failed-" + iOException.getMessage());
                            AlexaDownChannelService.this.reCreateDownChannel();
                        }

                        @Override // okhttp3.Callback
                        public void onResponse(Call call, Response response) {
                            AlexaDownChannelService.this.log(" ping-Success");
                        }
                    });
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void startPing() {
        try {
            if (this.mPingTimerTask != null) {
                this.mPingTimerTask.cancel();
                this.mPingTimerTask = null;
            }
            this.mPingTimerTask = new PingTimerTask();
            this.updateTimer = new Timer();
            this.updateTimer.schedule(this.mPingTimerTask, 120000L, 120000L);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        log("startPing");
    }

    private void stopPing() {
        Timer timer = this.updateTimer;
        if (timer != null) {
            timer.cancel();
            this.updateTimer = null;
        }
        log("stopPing");
    }

    private void createChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            if (this.builder == null) {
                this.builder = new NotificationCompat.Builder(this, getPackageName());
                NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
                if (notificationManager == null) {
                    return;
                }
                NotificationChannel notificationChannel = new NotificationChannel(getPackageName(), getString(R.string.app_name), 3);
                notificationChannel.setSound(null, null);
                notificationManager.createNotificationChannel(notificationChannel);
                this.builder.setChannelId(getPackageName());
            }
            startForeground(1, this.builder.build());
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        boolean booleanExtra;
        boolean booleanExtra2;
        createChannel();
        StringBuilder sb = new StringBuilder();
        sb.append("onStartCommand downChannelResponse = ");
        Response response = this.downChannelResponse;
        sb.append(response != null ? Integer.valueOf(response.code()) : "null");
        sb.append("  hasResult=");
        sb.append(this.hasResult);
        log(sb.toString());
        if (intent == null || intent.getExtras() == null) {
            booleanExtra = false;
            booleanExtra2 = false;
        } else {
            this.isSyncNotifyState = intent.getBooleanExtra(IS_SYNC_NOTIFY_STATUS, false);
            booleanExtra = intent.getBooleanExtra(RE_CREATE_DOWNCHANNEL, false);
            this.needCreate = intent.getBooleanExtra(NEED_CREATE_DOWNCHANNEL, false);
            booleanExtra2 = intent.getBooleanExtra(OFFLINE_STATUS, false);
            if (intent.getBooleanExtra(NEED_CREATE_SMARTHOME, false)) {
                this.needCreateSmartHome = true;
            }
        }
        if (booleanExtra) {
            log("Reauthorize downChannel need recreate");
            this.needCreateSmartHome = true;
            this.isSyncNotifyState = true;
            this.retryCount = 0;
            AlexaSendCmdToDeviceUtil.setNotifyStatus(true);
            AlexaAudioEventManger.getInstance().lastSyncStateTime = 0L;
            reCreateDownChannel();
        } else if (this.needCreate) {
            this.needCreate = false;
            log("响应数据一直为空， downChannel need recreate");
            reCreateDownChannel();
            this.retryCount = 0;
        } else if (booleanExtra2) {
            log("send offline");
            cancelCall();
        } else if (this.downChannelResponse != null) {
            syncNotifyStatus();
            createSmartHome();
        } else if (this.hasResult) {
            log("downChannelResponse is null, downChannel need recreate");
            reCreateDownChannel();
        }
        return super.onStartCommand(intent, i, i2);
    }

    private void createDownChannel() {
        log("start createDownChannel");
        this.hasResult = false;
        cancelCall();
        this.runnableHandler.postDelayed(this.timeoutRunnable, 30000);
        this.alexaManager.getAuthorizeManager().checkLoggedIn(this, new ImplAsyncCallback<Boolean, Throwable>() { // from class: com.ido.alexa.service.AlexaDownChannelService.1
            @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
            public void success(Boolean bool) {
                if (!bool.booleanValue()) {
                    AlexaDownChannelService.this.hasResult = true;
                    return;
                }
                AlexaDownChannelService.this.currentCall = ClientUtil.getTLS12OkHttpClient().newCall(new Request.Builder().url(AlexaDownChannelService.this.alexaManager.getDirectivesUrl()).get().addHeader("Authorization", "Bearer " + AlexaDownChannelService.this.alexaManager.getToken()).cacheControl(new CacheControl.Builder().noCache().build()).build());
                AlexaDownChannelService.this.currentCall.enqueue(new Callback() { // from class: com.ido.alexa.service.AlexaDownChannelService.1.1
                    @Override // okhttp3.Callback
                    public void onFailure(Call call, IOException iOException) {
                        AlexaDownChannelService alexaDownChannelService = AlexaDownChannelService.this;
                        StringBuilder sb = new StringBuilder();
                        sb.append(" getDirectives failed =");
                        sb.append(iOException != null ? iOException.getMessage() : "");
                        alexaDownChannelService.log(sb.toString());
                        AlexaDownChannelService.this.handlerExption();
                    }

                    @Override // okhttp3.Callback
                    public void onResponse(Call call, Response response) {
                        AlexaDownChannelService.this.hasResult = true;
                        AlexaDownChannelService.this.log(" createDownChannel onResponse code=====" + response.code());
                        if (response.code() == 403) {
                            ResponseHandler.getInstance().eauthorizerAmazon();
                            return;
                        }
                        if (response.code() == 200) {
                            AlexaDownChannelService.this.createSmartHome();
                            AlexaDownChannelService.this.syncNotifyStatus();
                        }
                        AlexaDownChannelService.this.downChannelResponse = response;
                        AlexaDownChannelService.this.startPing();
                        AlexaDownChannelService.this.runnableHandler.removeCallbacks(AlexaDownChannelService.this.timeoutRunnable);
                        BufferedSource bufferedSourceSource = response.body().source();
                        while (true) {
                            String utf8Line = "";
                            try {
                                try {
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                    AlexaDownChannelService.this.log(" HandleDirectiveCallback=IOException =" + e2.getMessage());
                                    response.close();
                                    AlexaDownChannelService.this.handlerExption();
                                }
                                if (bufferedSourceSource.exhausted()) {
                                    return;
                                }
                                utf8Line = bufferedSourceSource.readUtf8Line();
                                if (!TextUtils.isEmpty(utf8Line)) {
                                    AlexaDownChannelService.this.log(" HandleDirectiveCallback=" + utf8Line);
                                    if (!TextUtils.equals(utf8Line, AlexaDownChannelService.BOUNDARY) && !TextUtils.equals(utf8Line, AlexaDownChannelService.CONTENT_TYPE)) {
                                        try {
                                            Directive directive = ApiParser.getDirective(utf8Line);
                                            if (directive != null) {
                                                ResponseHandler.getInstance().handleDirective(directive);
                                            } else {
                                                AlexaLogUtil.d(AlexaDownChannelService.TAG, "HandleDirectiveCallback==directive = null");
                                            }
                                        } catch (Exception e3) {
                                            e3.printStackTrace();
                                            AlexaDownChannelService.this.log(" HandleDirectiveCallback=parseException =" + e3.getMessage());
                                        }
                                    }
                                }
                            } catch (Exception e4) {
                                e4.printStackTrace();
                                AlexaDownChannelService.this.log(" HandleDirectiveCallback= Exception =" + e4.getMessage());
                                return;
                            }
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlerExption() {
        this.currentCall = null;
        this.hasResult = true;
        this.runnableHandler.removeCallbacks(this.delayRecreateRunnable);
        this.runnableHandler.postDelayed(this.delayRecreateRunnable, DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
        this.runnableHandler.removeCallbacks(this.timeoutRunnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void reCreateDownChannel() {
        NetworkInfo activeNetworkInfo;
        this.runnableHandler.removeCallbacks(this.delayRecreateRunnable);
        this.downChannelResponse = null;
        this.hasResult = true;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService("connectivity");
        boolean z = false;
        if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getState() == NetworkInfo.State.CONNECTED) {
            z = true;
        }
        log(" isConnectedNet=" + z + " reCreateDownChannel ");
        if (z) {
            createDownChannel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createSmartHome() {
        if (this.needCreateSmartHome) {
            log("send createSmartHome request");
            this.needCreateSmartHome = false;
            AlexaAudioEventManger.getInstance().discovery(new ImplAsyncCallback<String, AvsException>() { // from class: com.ido.alexa.service.AlexaDownChannelService.5
                @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
                public void failure(AvsException avsException) {
                    AlexaDownChannelService.this.log("send createSmartHome request failed, after 30s retry");
                    AlexaDownChannelService alexaDownChannelService = AlexaDownChannelService.this;
                    alexaDownChannelService.needCreateSmartHome = true;
                    AlexaDownChannelService.access$1208(alexaDownChannelService);
                    if (AlexaDownChannelService.this.retryCount <= 4) {
                        AlexaDownChannelService.this.runnableHandler.removeCallbacks(AlexaDownChannelService.this.delayRecreateSmartHomeRunnable);
                        AlexaDownChannelService.this.runnableHandler.postDelayed(AlexaDownChannelService.this.delayRecreateSmartHomeRunnable, 30000L);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void syncNotifyStatus() {
        if (this.isSyncNotifyState) {
            log("sync notify status");
            AlexaAudioEventManger.getInstance().sendSyncStateEvent(new ImplAsyncCallback<String, AvsException>() { // from class: com.ido.alexa.service.AlexaDownChannelService.6
                @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
                public void success(String str) {
                    super.success(str);
                    AlexaDownChannelService.this.isSyncNotifyState = false;
                    AlexaDownChannelService.this.log(" getSynchronizeStateEvent success");
                }
            });
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        cancelCall();
        stopPing();
        unregisterReceiver(this.broadcastReceiver);
        log("AlexaDownChannelService onDestroy");
    }

    private void cancelCall() {
        Call call = this.currentCall;
        if (call == null || call.isCanceled()) {
            return;
        }
        this.currentCall.cancel();
        log("AlexaDownChannelService cancel Call");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void log(String str) {
        AlexaLogUtil.printAndSave(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isNetConnected() {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService("connectivity");
        return connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getState() == NetworkInfo.State.CONNECTED;
    }
}