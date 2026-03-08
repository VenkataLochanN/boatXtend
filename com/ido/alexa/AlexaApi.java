package com.ido.alexa;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.ido.alexa.bean.AlexaLoginParas;
import com.ido.alexa.bean.AvsException;
import com.ido.alexa.callbacks.AlexaSettingCallBack;
import com.ido.alexa.callbacks.AsyncCallback;
import com.ido.alexa.callbacks.AudioResultCallback;
import com.ido.alexa.callbacks.AuthorizationCallback;
import com.ido.alexa.callbacks.ChunkPostCallback;
import com.ido.alexa.callbacks.DeviceStatusCallBack;
import com.ido.alexa.callbacks.HandleDirectiveCallback;
import com.ido.alexa.callbacks.IAlexaCallBack;
import com.ido.alexa.callbacks.ImplAsyncCallback;
import com.ido.alexa.callbacks.QueryDataCallback;
import com.ido.alexa.callbacks.TokenCallback;
import com.ido.alexa.data.ApiResponse;
import com.ido.alexa.data.AvsAlarmItem;
import com.ido.alexa.data.Event;
import com.ido.alexa.data.UserCodeResponse;
import com.ido.alexa.log.AlexaLogUtil;
import com.ido.alexa.manager.AlexaAlarmEventManager;
import com.ido.alexa.manager.AlexaAudioEventManger;
import com.ido.alexa.manager.AlexaManager;
import com.ido.alexa.manager.SpManager;
import com.ido.alexa.manager.TokenManager;
import com.ido.alexa.service.AlexaDownChannelService;
import com.ido.alexa.util.AlexaSendCmdToDeviceUtil;
import com.ido.alexa.util.ComUtil;
import com.ido.ble.BLEManager;
import com.ido.ble.protocol.model.VoiceLoginState;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaApi {
    static ArrayList<AlexaSettingCallBack.ICallBack> mAlexaSettingCallBacks = new ArrayList<>();
    static AudioResultCallback mAudioResultCallback;
    static DeviceStatusCallBack mDeviceStatusCallBack;
    static QueryDataCallback mQueryDataCallback;

    public static void registerAlexaSettingCallBack(AlexaSettingCallBack.ICallBack iCallBack) {
        ArrayList<AlexaSettingCallBack.ICallBack> arrayList = mAlexaSettingCallBacks;
        if (arrayList == null || arrayList.contains(iCallBack)) {
            return;
        }
        mAlexaSettingCallBacks.add(iCallBack);
    }

    public static void unregisterAlexaSettingCallBack(AlexaSettingCallBack.ICallBack iCallBack) {
        ArrayList<AlexaSettingCallBack.ICallBack> arrayList = mAlexaSettingCallBacks;
        if (arrayList == null || !arrayList.contains(iCallBack)) {
            return;
        }
        mAlexaSettingCallBacks.remove(iCallBack);
    }

    public static List<AlexaSettingCallBack.ICallBack> getAlexaSettingCallBackList() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(mAlexaSettingCallBacks);
        return arrayList;
    }

    public static void registerDeviceStatusCallBack(DeviceStatusCallBack deviceStatusCallBack) {
        mDeviceStatusCallBack = deviceStatusCallBack;
    }

    public static void unregisterDeviceStatusCallBack() {
        mDeviceStatusCallBack = null;
    }

    public static void reboot() {
        DeviceStatusCallBack deviceStatusCallBack = mDeviceStatusCallBack;
        if (deviceStatusCallBack != null) {
            deviceStatusCallBack.reboot();
        }
    }

    public static void connectSuccess(String str) {
        DeviceStatusCallBack deviceStatusCallBack = mDeviceStatusCallBack;
        if (deviceStatusCallBack != null) {
            deviceStatusCallBack.onConnectSuccess(str);
        }
    }

    public static void connectFailed() {
        DeviceStatusCallBack deviceStatusCallBack = mDeviceStatusCallBack;
        if (deviceStatusCallBack != null) {
            deviceStatusCallBack.onConnectFailed();
        }
    }

    public static void connectBreak() {
        DeviceStatusCallBack deviceStatusCallBack = mDeviceStatusCallBack;
        if (deviceStatusCallBack != null) {
            deviceStatusCallBack.onConnectBreak();
        }
    }

    public static void setDeviceFirmwareVersion(long j) {
        DeviceStatusCallBack deviceStatusCallBack = mDeviceStatusCallBack;
        if (deviceStatusCallBack != null) {
            deviceStatusCallBack.onSetDevicefirmwareVersion(j);
        }
    }

    public static void hornVoiceChanged(int i) {
        DeviceStatusCallBack deviceStatusCallBack = mDeviceStatusCallBack;
        if (deviceStatusCallBack != null) {
            deviceStatusCallBack.onHornVoiceChanged(i);
        }
    }

    public static void registerQueryDataCallback(QueryDataCallback queryDataCallback) {
        mQueryDataCallback = queryDataCallback;
    }

    public static void unregisterQueryDataCallback() {
        mQueryDataCallback = null;
    }

    public static void handlerQueryResult(long j) {
        QueryDataCallback queryDataCallback = mQueryDataCallback;
        if (queryDataCallback != null) {
            queryDataCallback.callbackData(j);
        }
    }

    public static void registerAudioResultCallback(AudioResultCallback audioResultCallback) {
        mAudioResultCallback = audioResultCallback;
    }

    public static void unregisterAudioResultCallback() {
        mAudioResultCallback = null;
    }

    public static void handlerAudioResult(String str) {
        AudioResultCallback audioResultCallback = mAudioResultCallback;
        if (audioResultCallback != null) {
            audioResultCallback.onResponse(str);
        }
    }

    public static void onStartAlexa() {
        AudioResultCallback audioResultCallback = mAudioResultCallback;
        if (audioResultCallback != null) {
            audioResultCallback.onAlexaStart();
        }
    }

    public static void onEndAlexa() {
        AudioResultCallback audioResultCallback = mAudioResultCallback;
        if (audioResultCallback != null) {
            audioResultCallback.onAlexaEnd();
        }
    }

    public static void onStartPrase() {
        AudioResultCallback audioResultCallback = mAudioResultCallback;
        if (audioResultCallback != null) {
            audioResultCallback.onStartPrase();
        }
    }

    public static void onAlexaLogout() {
        AudioResultCallback audioResultCallback = mAudioResultCallback;
        if (audioResultCallback != null) {
            audioResultCallback.onAlexaLogout();
        }
    }

    public static void onPcmFileName(String str) {
        AudioResultCallback audioResultCallback = mAudioResultCallback;
        if (audioResultCallback != null) {
            audioResultCallback.onPcmFileName(str);
        }
    }

    public static void onMp3FileName(String str) {
        AudioResultCallback audioResultCallback = mAudioResultCallback;
        if (audioResultCallback != null) {
            audioResultCallback.onMP3FileName(str);
        }
    }

    public static void registerHandleDirectiveCallback(HandleDirectiveCallback handleDirectiveCallback) {
        ResponseHandler.getInstance().registerHandleDirectiveCallback(handleDirectiveCallback);
    }

    public static void unregisterHandleDirectiveCallback(HandleDirectiveCallback handleDirectiveCallback) {
        ResponseHandler.getInstance().unregisterHandleDirectiveCallback(handleDirectiveCallback);
    }

    public static void authorize(AlexaLoginParas alexaLoginParas, AuthorizationCallback authorizationCallback) {
        if (alexaLoginParas == null) {
            AlexaLogUtil.d("loginParas==null");
            if (authorizationCallback != null) {
                authorizationCallback.onError(new Exception("loginParas==null"));
                return;
            }
            return;
        }
        if (!TextUtils.isEmpty(alexaLoginParas.getMacAddress())) {
            SpManager.setMacAdress(alexaLoginParas.getMacAddress());
        }
        if (!TextUtils.isEmpty(alexaLoginParas.getDeviceName())) {
            SpManager.setBleName(alexaLoginParas.getDeviceName());
        }
        AlexaManager.getInstance().authorize(alexaLoginParas.getProductId(), authorizationCallback);
    }

    public static void CBLAuthorize(AlexaLoginParas alexaLoginParas, AuthorizationCallback authorizationCallback) {
        if (alexaLoginParas == null) {
            AlexaLogUtil.d("CBLAuthorize-loginParas==null");
            if (authorizationCallback != null) {
                authorizationCallback.onError(new Exception("CBLAuthorize-loginParas==null"));
                return;
            }
            return;
        }
        if (!TextUtils.isEmpty(alexaLoginParas.getMacAddress())) {
            SpManager.setMacAdress(alexaLoginParas.getMacAddress());
        }
        if (!TextUtils.isEmpty(alexaLoginParas.getDeviceName())) {
            SpManager.setBleName(alexaLoginParas.getDeviceName());
        }
        AlexaManager.getInstance().CBLAuthorize(alexaLoginParas.getProductId(), alexaLoginParas.getClientId(), authorizationCallback);
    }

    public static void getToken(Context context, final IAlexaCallBack iAlexaCallBack) {
        TokenManager.getAccessToken(context, new TokenCallback() { // from class: com.ido.alexa.AlexaApi.1
            @Override // com.ido.alexa.callbacks.TokenCallback
            public void onSuccess(String str) {
                IAlexaCallBack iAlexaCallBack2 = iAlexaCallBack;
                if (iAlexaCallBack2 != null) {
                    iAlexaCallBack2.success(str);
                }
            }

            @Override // com.ido.alexa.callbacks.TokenCallback
            public void onFailure(Throwable th) {
                IAlexaCallBack iAlexaCallBack2 = iAlexaCallBack;
                if (iAlexaCallBack2 != null) {
                    iAlexaCallBack2.failure(new AvsException(th.getMessage()));
                }
            }
        });
    }

    public static void requestDeviceTokenByCBLAuth(UserCodeResponse userCodeResponse, final IAlexaCallBack iAlexaCallBack) {
        TokenManager.requestDeviceTokens(userCodeResponse, new TokenCallback() { // from class: com.ido.alexa.AlexaApi.2
            @Override // com.ido.alexa.callbacks.TokenCallback
            public void onSuccess(String str) {
                if (SpManager.getFirmwareVersion() != 0) {
                    AlexaAudioEventManger.getInstance().sendEvent(Event.getSoftwareInfoEvent(SpManager.getFirmwareVersion()), null);
                }
                IAlexaCallBack iAlexaCallBack2 = iAlexaCallBack;
                if (iAlexaCallBack2 != null) {
                    iAlexaCallBack2.success(str);
                }
            }

            @Override // com.ido.alexa.callbacks.TokenCallback
            public void onFailure(Throwable th) {
                IAlexaCallBack iAlexaCallBack2 = iAlexaCallBack;
                if (iAlexaCallBack2 != null) {
                    iAlexaCallBack2.failure(new AvsException(th.getMessage()));
                }
            }
        });
    }

    public static void logoutWithAmazon(Context context, final IAlexaCallBack iAlexaCallBack) {
        AlexaAudioEventManger.getInstance().deleteEndpoint(null);
        AlexaManager.getInstance().logout(context, new IAlexaCallBack() { // from class: com.ido.alexa.AlexaApi.3
            @Override // com.ido.alexa.callbacks.IAlexaCallBack
            public void success(String str) {
                if (BLEManager.isConnected()) {
                    VoiceLoginState voiceLoginState = new VoiceLoginState();
                    voiceLoginState.log_state = 1;
                    BLEManager.setVoiceLoginState(voiceLoginState);
                }
                IAlexaCallBack iAlexaCallBack2 = iAlexaCallBack;
                if (iAlexaCallBack2 != null) {
                    iAlexaCallBack2.success(str);
                }
            }

            @Override // com.ido.alexa.callbacks.IAlexaCallBack
            public void failure(AvsException avsException) {
                IAlexaCallBack iAlexaCallBack2 = iAlexaCallBack;
                if (iAlexaCallBack2 != null) {
                    iAlexaCallBack2.failure(avsException);
                }
            }
        });
    }

    public static ChunkPostCallback chunkSendRecordRequest(String str, AsyncCallback<ApiResponse, Throwable> asyncCallback) {
        return AlexaAudioEventManger.getInstance().chunkSendRecordRequest(str, asyncCallback);
    }

    public static void sendRecord(String str, byte[] bArr, AsyncCallback<ApiResponse, Throwable> asyncCallback) {
        AlexaAudioEventManger.getInstance().sendRecordRequest(str, bArr, asyncCallback);
    }

    public static void cancelCall() {
        AlexaAudioEventManger.getInstance().cancelCall();
    }

    public static void switchLangauge(String str, AsyncCallback<String, AvsException> asyncCallback) {
        AlexaManager.getInstance().switchLanguage(str, asyncCallback);
    }

    public static void switchDeviceForAlexa() {
        AlexaSendCmdToDeviceUtil.switchDeviceForAlexa(true);
    }

    public static void uploadSkill() {
        Intent intent = new Intent(AlexaApp.getAppContext(), (Class<?>) AlexaDownChannelService.class);
        intent.putExtra(AlexaDownChannelService.NEED_CREATE_SMARTHOME, true);
        ComUtil.startService(AlexaApp.getAppContext(), intent);
    }

    public static void setTimeZone(final String str) {
        AlexaAudioEventManger.getInstance().sendEvent(Event.getSystemTimeZoneChangeEvent(str), new ImplAsyncCallback<String, AvsException>() { // from class: com.ido.alexa.AlexaApi.4
            @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
            public void success(String str2) {
                super.success(str2);
                AlexaAudioEventManger.getInstance().sendEvent(Event.getSystemTimeZoneReportEvent(str), null);
            }
        });
    }

    public static void getAlarmByToken(String str, AsyncCallback<AvsAlarmItem.AlexaAlarmsBean, Throwable> asyncCallback) {
        AlexaAlarmEventManager.getInstance().getAlarmByToken(str, asyncCallback);
    }

    public static void getAllAlarms(AsyncCallback<AvsAlarmItem, Throwable> asyncCallback) {
        AlexaAlarmEventManager.getInstance().getAllAlarms(asyncCallback);
    }

    public static void deleteAllAlarms(AsyncCallback<String, Throwable> asyncCallback) {
        AlexaAlarmEventManager.getInstance().deleteAllAlarms(asyncCallback);
    }

    public static void deleteAlarm(String str, AsyncCallback<String, Throwable> asyncCallback) {
        AlexaAlarmEventManager.getInstance().deleteAlarmByToken(str, asyncCallback);
    }
}