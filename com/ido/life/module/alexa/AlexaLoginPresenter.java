package com.ido.life.module.alexa;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaApi;
import com.ido.alexa.bean.AlexaLanguageEnum;
import com.ido.alexa.bean.AlexaLoginParas;
import com.ido.alexa.bean.AvsException;
import com.ido.alexa.callbacks.AuthorizationCallback;
import com.ido.alexa.callbacks.BaseVoiceCallBack;
import com.ido.alexa.callbacks.IAlexaCallBack;
import com.ido.alexa.callbacks.ImplAsyncCallback;
import com.ido.alexa.data.UserCodeResponse;
import com.ido.alexa.log.AlexaLogUtil;
import com.ido.alexa.service.AlexaService;
import com.ido.alexa.util.AlexaNewAlarmUtil;
import com.ido.alexa.util.ComUtil;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.callback.DeviceResponseCommonCallBack;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.common.IdoApp;
import com.ido.common.constant.LanguageRegion;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.AppUtil;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.base.BaseMessage;
import com.ido.life.base.BasePresenter;
import com.ido.life.bean.LatLngBean;
import com.ido.life.ble.DeviceConfigHelper;
import com.ido.life.constants.Constants;
import com.ido.life.data.api.entity.DeviceInfo;
import com.ido.life.data.api.entity.DeviceListEntity;
import com.ido.life.data.device.remote.DeviceManager;
import com.ido.life.util.AppLogUploadManager;
import com.ido.life.util.SPHelper;
import com.ido.life.util.SPUtils;
import com.ido.life.util.eventbus.EventBusHelper;
import com.ido.record.RawAudioRecorder;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaLoginPresenter extends BasePresenter<IAlexaView> {
    private static final int LOST_PACKAGE_EVENT = 7505;
    private AlexaLoginType mAlexaLoginType;
    private RawAudioRecorder mRecorder;
    private String clientID = "amzn1.application-oa2-client.58ce095dbe8941a4ad6cf0794742c01c";
    BaseVoiceCallBack baseVoiceCallBack = new BaseVoiceCallBack() { // from class: com.ido.life.module.alexa.AlexaLoginPresenter.1
        @Override // com.ido.alexa.callbacks.BaseVoiceCallBack, com.ido.ble.callback.VoiceCallBack.ICallBack
        public void onGetDefaultLang(int i) {
            super.onGetDefaultLang(i);
            BLEManager.unregisterVoiceCallBack(this);
            if (AlexaLoginPresenter.this.isAttachView()) {
                ((IAlexaView) AlexaLoginPresenter.this.getView()).loadDefaultAlexaLanguage(AlexaLanguageEnum.getmLanguage(i));
            }
        }
    };
    private boolean isRecording = false;
    private AuthorizationCallback mAuthorizationCallback = new AuthorizationCallback<UserCodeResponse>() { // from class: com.ido.life.module.alexa.AlexaLoginPresenter.5
        @Override // com.ido.alexa.callbacks.AuthorizationCallback
        public void onCancel() {
            CommonLogUtil.d("AuthorizationCallback onCancel");
            if (AlexaLoginPresenter.this.isAttachView()) {
                ((IAlexaView) AlexaLoginPresenter.this.getView()).onAuthorizeCancel();
            }
        }

        @Override // com.ido.alexa.callbacks.AuthorizationCallback
        public void onSuccess(UserCodeResponse userCodeResponse) {
            CommonLogUtil.d("AuthorizationCallback  onSuccess");
            if (AlexaLoginPresenter.this.isAttachView()) {
                ((IAlexaView) AlexaLoginPresenter.this.getView()).onAuthorizeSuccess(userCodeResponse);
            }
            AlexaNewAlarmUtil.getInstance().syncAlexaAccountAlarms();
        }

        @Override // com.ido.alexa.callbacks.AuthorizationCallback
        public void onError(Exception exc) {
            CommonLogUtil.d("AuthorizationCallback onError" + exc);
            if (AlexaLoginPresenter.this.isAttachView()) {
                ((IAlexaView) AlexaLoginPresenter.this.getView()).onAuthorizeFailed(exc);
            }
        }
    };
    DeviceResponseCommonCallBack.ICallBack iCallBack = new DeviceResponseCommonCallBack.ICallBack() { // from class: com.ido.life.module.alexa.AlexaLoginPresenter.8
        @Override // com.ido.ble.callback.DeviceResponseCommonCallBack.ICallBack
        public void onResponse(int i, String str) {
            int i2;
            int i3;
            int i4;
            if (i != AlexaLoginPresenter.LOST_PACKAGE_EVENT) {
                return;
            }
            int i5 = 0;
            try {
                if (TextUtils.isEmpty(str)) {
                    i4 = 0;
                    i2 = 0;
                } else {
                    JSONObject jSONObject = new JSONObject(str);
                    int i6 = jSONObject.has("size_lost_package") ? jSONObject.getInt("size_lost_package") : 0;
                    try {
                        i2 = jSONObject.has("size_all_package") ? jSONObject.getInt("size_all_package") : 0;
                        if (i2 != 0 && i6 != 0) {
                            i5 = (int) (((i6 * 1.0f) / i2) * 100.0f);
                        }
                        if (i6 != 0) {
                            try {
                                AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.ALEXA_LOST_PKG, "", null);
                            } catch (JSONException e2) {
                                i3 = i5;
                                i5 = i6;
                                e = e2;
                                e.printStackTrace();
                                i4 = i3;
                            }
                        }
                        int i7 = i6;
                        i4 = i5;
                        i5 = i7;
                    } catch (JSONException e3) {
                        i3 = 0;
                        i5 = i6;
                        e = e3;
                        i2 = 0;
                    }
                }
            } catch (JSONException e4) {
                e = e4;
                i2 = 0;
                i3 = 0;
            }
            if (AlexaLoginPresenter.this.isAttachView()) {
                ((IAlexaView) AlexaLoginPresenter.this.getView()).onLostPackageData("丢包数=" + i5 + " 总包数=" + i2 + "  丢包率=" + i4 + "%\n");
            }
        }
    };

    public void testAuthorize(String str, AlexaLoginType alexaLoginType) {
        this.mAlexaLoginType = alexaLoginType;
        BLEDevice deviceInfo = getDeviceInfo();
        AlexaLogUtil.printAndSave("testAuthorize  mProductId=" + str + " bleDevice=" + deviceInfo.toString());
        AlexaLoginParas alexaLoginParas = new AlexaLoginParas();
        alexaLoginParas.setProductId(str);
        alexaLoginParas.setMacAddress(deviceInfo.mDeviceAddress);
        alexaLoginParas.setDeviceName(deviceInfo.mDeviceName);
        alexaLoginParas.setClientId(this.clientID);
        if (this.mAlexaLoginType == AlexaLoginType.SDK) {
            AlexaApi.authorize(alexaLoginParas, this.mAuthorizationCallback);
        } else {
            AlexaApi.CBLAuthorize(alexaLoginParas, this.mAuthorizationCallback);
        }
    }

    public void authorize(AlexaLoginType alexaLoginType) {
        this.mAlexaLoginType = alexaLoginType;
        AlexaLogUtil.d("V3_support_get_alexa_default_language=" + DeviceConfigHelper.getSupportFunctionInfo().V3_support_get_alexa_default_language);
        if (DeviceConfigHelper.getSupportFunctionInfo().V3_support_get_alexa_default_language) {
            BLEManager.registerVoiceCallBack(this.baseVoiceCallBack);
            BLEManager.getVoiceDefaultLang();
        }
        auth(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void auth(boolean z) {
        String alexaProductId;
        String bluetoothName;
        String str;
        List<DeviceInfo> deviceInfoList = SPHelper.getDeviceInfoList();
        AlexaLogUtil.printAndSave("alexa authorize:" + deviceInfoList.toString());
        BLEDevice deviceInfo = getDeviceInfo();
        Iterator<DeviceInfo> it = deviceInfoList.iterator();
        while (true) {
            alexaProductId = "";
            if (!it.hasNext()) {
                break;
            }
            DeviceInfo next = it.next();
            if (TextUtils.equals(next.getDeviceId(), deviceInfo.mDeviceId + "")) {
                if (next.getSupportAlexa()) {
                    alexaProductId = next.getAlexaProductId();
                    bluetoothName = next.getBluetoothName();
                    str = deviceInfo.mDeviceAddress;
                }
            }
        }
        bluetoothName = "";
        str = bluetoothName;
        if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            this.mAuthorizationCallback.onError(new Exception(LanguageUtil.getLanguageText(R.string.logn_all_pleaseCheckNetWork_ios)));
            return;
        }
        if (TextUtils.isEmpty(alexaProductId)) {
            AlexaLogUtil.printAndSave("mProductId is empty " + z);
            if (z) {
                queryDeviceInfo();
                return;
            } else {
                this.mAuthorizationCallback.onError(new Exception(LanguageUtil.getLanguageText(R.string.no_alexa_productid)));
                return;
            }
        }
        AlexaLoginParas alexaLoginParas = new AlexaLoginParas();
        alexaLoginParas.setProductId(alexaProductId);
        alexaLoginParas.setMacAddress(str);
        alexaLoginParas.setDeviceName(bluetoothName);
        alexaLoginParas.setClientId(this.clientID);
        AlexaLogUtil.printAndSave("mProductId=" + alexaProductId + " ,mac=" + str + " ,bleName=" + bluetoothName);
        if (this.mAlexaLoginType == AlexaLoginType.SDK) {
            AlexaApi.authorize(alexaLoginParas, this.mAuthorizationCallback);
        } else {
            AlexaApi.CBLAuthorize(alexaLoginParas, this.mAuthorizationCallback);
        }
        new Thread(new Runnable() { // from class: com.ido.life.module.alexa.-$$Lambda$AlexaLoginPresenter$Fc9YtqEcMJrIdVKmJQxuLWbjMKY
            @Override // java.lang.Runnable
            public final void run() {
                AlexaLoginPresenter.lambda$auth$0();
            }
        }).start();
    }

    static /* synthetic */ void lambda$auth$0() {
        AlexaLogUtil.printAndSave("浏览器列表：" + AppUtil.getDefaultBrowserList(IdoApp.getAppContext()));
        AlexaLogUtil.printAndSave("AmazonAppName= " + AppUtil.getAmazonAppName(IdoApp.getAppContext()));
    }

    private void queryDeviceInfo() {
        String[] strArr;
        if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
            this.mAuthorizationCallback.onError(new Exception(LanguageUtil.getLanguageText(R.string.logn_all_pleaseCheckNetWork_ios)));
            return;
        }
        StringBuilder sb = new StringBuilder();
        List<DeviceListEntity.DeviceInfo> deviceList = SPHelper.getDeviceList();
        if (deviceList.isEmpty()) {
            BLEDevice deviceInfo = getDeviceInfo();
            sb.append(deviceInfo.mDeviceId + "");
            strArr = new String[]{deviceInfo.mDeviceId + ""};
            AlexaLogUtil.printAndSave("DeviceList is empty");
        } else {
            strArr = new String[deviceList.size()];
            for (int i = 0; i < deviceList.size(); i++) {
                DeviceListEntity.DeviceInfo deviceInfo2 = deviceList.get(i);
                if (deviceInfo2 != null) {
                    strArr[i] = deviceInfo2.getDeviceId();
                    sb.append(deviceInfo2.getDeviceId() + AppInfo.DELIM);
                }
            }
        }
        AlexaLogUtil.printAndSave("queryDeviceInfoList ids=" + sb.toString());
        DeviceManager.queryDeviceInfoList(new DeviceManager.OnDeviceCallback<List<DeviceInfo>>() { // from class: com.ido.life.module.alexa.AlexaLoginPresenter.2
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(List<DeviceInfo> list) {
                if (list == null || list.isEmpty()) {
                    AlexaLogUtil.printAndSave("queryDeviceInfoList success empty");
                } else {
                    AlexaLogUtil.printAndSave("queryDeviceInfoList success :" + GsonUtil.toJson(list));
                }
                SPHelper.saveDeviceInfoList(list);
                EventBusHelper.post(new BaseMessage(Constants.EventConstants.EVENT_REQUEST_DEVICE_INFO_COMPLETE, true));
                AlexaLoginPresenter.this.auth(false);
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i2, String str) {
                AlexaLogUtil.printAndSave("queryDeviceInfoList onFailed :" + str);
                AlexaLoginPresenter.this.mAuthorizationCallback.onError(new Exception(LanguageUtil.getLanguageText(R.string.logn_all_pleaseCheckNetWork_ios)));
            }
        }, strArr);
    }

    public BLEDevice getDeviceInfo() {
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (currentDeviceInfo == null) {
            currentDeviceInfo = new BLEDevice();
        }
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        if (basicInfo != null) {
            currentDeviceInfo.mDeviceId = basicInfo.deivceId;
            currentDeviceInfo.version = basicInfo.firmwareVersion;
        }
        return currentDeviceInfo;
    }

    public void getToken() {
        AlexaApi.getToken(IdoApp.getAppContext(), new IAlexaCallBack() { // from class: com.ido.life.module.alexa.AlexaLoginPresenter.3
            @Override // com.ido.alexa.callbacks.IAlexaCallBack
            public void success(String str) {
                if (AlexaLoginPresenter.this.isAttachView()) {
                    ((IAlexaView) AlexaLoginPresenter.this.getView()).onGetTokenSuccess(str);
                }
            }

            @Override // com.ido.alexa.callbacks.IAlexaCallBack
            public void failure(AvsException avsException) {
                if (AlexaLoginPresenter.this.isAttachView()) {
                    ((IAlexaView) AlexaLoginPresenter.this.getView()).onGetTokenFailed(avsException);
                }
            }
        });
    }

    public void logoutWithAmazon() {
        if (this.mAlexaLoginType == AlexaLoginType.CBL) {
            clearCookies();
        }
        AlexaApi.logoutWithAmazon(IdoApp.getAppContext(), new IAlexaCallBack() { // from class: com.ido.life.module.alexa.AlexaLoginPresenter.4
            @Override // com.ido.alexa.callbacks.IAlexaCallBack
            public void success(String str) {
                if (AlexaLoginPresenter.this.isAttachView()) {
                    ((IAlexaView) AlexaLoginPresenter.this.getView()).onLogoutSuccess();
                }
            }

            @Override // com.ido.alexa.callbacks.IAlexaCallBack
            public void failure(AvsException avsException) {
                if (AlexaLoginPresenter.this.isAttachView()) {
                    ((IAlexaView) AlexaLoginPresenter.this.getView()).onLogoutFailed(avsException);
                }
            }
        });
    }

    private void clearCookies() {
        CookieSyncManager.createInstance(IdoApp.getAppContext());
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();
        cookieManager.removeAllCookies(null);
        CookieManager.getInstance().flush();
        cookieManager.removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }

    public void requestDeviceTokenByCBLAuth(UserCodeResponse userCodeResponse) {
        AlexaApi.requestDeviceTokenByCBLAuth(userCodeResponse, new IAlexaCallBack() { // from class: com.ido.life.module.alexa.AlexaLoginPresenter.6
            @Override // com.ido.alexa.callbacks.IAlexaCallBack
            public void success(String str) {
                if (AlexaLoginPresenter.this.isAttachView()) {
                    ((IAlexaView) AlexaLoginPresenter.this.getView()).onGetDeviceTokenByCBLAuthSuccess();
                }
            }

            @Override // com.ido.alexa.callbacks.IAlexaCallBack
            public void failure(AvsException avsException) {
                if (AlexaLoginPresenter.this.isAttachView()) {
                    ((IAlexaView) AlexaLoginPresenter.this.getView()).onGetDeviceTokenByCBLAuthFailed(avsException);
                }
            }
        });
    }

    public void startRecord() {
        if (this.isRecording) {
            return;
        }
        if (this.mRecorder == null) {
            this.mRecorder = new RawAudioRecorder();
        }
        this.mRecorder.start();
        this.isRecording = true;
    }

    public void stopRecord() {
        if (this.mRecorder != null) {
            EventBusHelper.post(Constants.EventConstants.EVENT_ALEXA_PRASE_START);
            new Thread(new Runnable() { // from class: com.ido.life.module.alexa.-$$Lambda$AlexaLoginPresenter$lNwemu0GNbs309Tk8vpK2z5xQeo
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$stopRecord$1$AlexaLoginPresenter();
                }
            }).start();
        }
    }

    public /* synthetic */ void lambda$stopRecord$1$AlexaLoginPresenter() {
        byte[] completeRecording = this.mRecorder.getCompleteRecording();
        this.mRecorder.stop();
        this.mRecorder.release();
        this.mRecorder = null;
        this.isRecording = false;
        CommonLogUtil.d("alexa----recordBytes=" + completeRecording.length);
        Intent intent = new Intent(IdoApp.getAppContext(), (Class<?>) AlexaService.class);
        intent.putExtra(AlexaService.AUDIO_DATA, completeRecording);
        ComUtil.startService(IdoApp.getAppContext(), intent);
    }

    public void switchLanguage(String str) {
        AlexaApi.switchLangauge(str, new ImplAsyncCallback<String, AvsException>() { // from class: com.ido.life.module.alexa.AlexaLoginPresenter.7
            @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
            public void success(String str2) {
                if (AlexaLoginPresenter.this.isAttachView()) {
                    ((IAlexaView) AlexaLoginPresenter.this.getView()).switchLanguageResult(true);
                }
            }

            @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
            public void failure(AvsException avsException) {
                if (AlexaLoginPresenter.this.isAttachView()) {
                    ((IAlexaView) AlexaLoginPresenter.this.getView()).switchLanguageResult(false);
                }
            }
        });
    }

    public void loadCountryCode() {
        Geocoder geocoder = new Geocoder(IdoApp.getAppContext(), new Locale(LanguageRegion.ZH));
        LatLngBean location = SPHelper.getLocation();
        try {
            List<Address> fromLocation = geocoder.getFromLocation(location.latitude, location.longitude, 1);
            if (fromLocation == null || fromLocation.size() <= 0) {
                return;
            }
            Address address = fromLocation.get(0);
            SPUtils.put("country_code", address.getCountryCode());
            AlexaLogUtil.printAndSave("Alexa 定位到的国家==" + address.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void registerLostPackageCallBack() {
        BLEManager.registerDeviceResponseCommonCallBack(this.iCallBack);
    }

    public void unRegisterLostPackageCallBack() {
        BLEManager.unregisterDeviceResponseCommonCallBack(this.iCallBack);
    }

    public void uploadLoginFailEvent() {
        AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.ALEXA_LOGIN_FAIL, "", null);
        AlexaLogUtil.printAndSave("uploadLoginFailEvent");
    }
}