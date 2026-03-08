package com.ido.life.data.device.remote;

import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.amap.api.maps.model.MyLocationStyle;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.google.gson.JsonObject;
import com.ido.alexa.AlexaConstant;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.net.BaseEntity;
import com.ido.common.net.BaseEntityNew;
import com.ido.common.utils.AppUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.bean.AppBLEDevice;
import com.ido.life.bean.LatLngBean;
import com.ido.life.bean.UsageDialBean;
import com.ido.life.bean.WallPaperDialInfo;
import com.ido.life.ble.DeviceConfigHelper;
import com.ido.life.data.api.DeviceApi;
import com.ido.life.data.api.entity.DeviceInfo;
import com.ido.life.data.api.entity.DeviceListEntity;
import com.ido.life.data.api.entity.DialInfoEntity;
import com.ido.life.data.api.entity.DialMarket;
import com.ido.life.data.api.entity.DialMarketDetail;
import com.ido.life.data.api.entity.DialState;
import com.ido.life.data.api.entity.MyDialListEntity;
import com.ido.life.data.api.entity.OtaEntity;
import com.ido.life.data.api.entity.RemoteLanguage;
import com.ido.life.data.api.entity.TopDialPlateEntity;
import com.ido.life.data.listener.ApiCallback;
import com.ido.life.database.model.DeviceWhiteListEntity;
import com.ido.life.database.model.DialUpdateEntity;
import com.ido.life.database.model.WeatherEntity;
import com.ido.life.database.model.middleModel.DialEntity;
import com.ido.life.module.main.MainPresenter;
import com.ido.life.util.DialDefinedUtil;
import com.ido.life.util.SPHelper;
import java.io.File;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.apache.commons.fileupload.FileUploadBase;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceManager {
    private static final String TAG = DeviceManager.class.getSimpleName();

    public interface OnDeviceCallback<T> {
        void onFailed(int i, String str);

        void onSuccess(T t);
    }

    private static String formatParameter(String str) {
        return str == null ? "" : str;
    }

    public static void uploadDevice(int i, String str, String str2, String str3, final OnDeviceCallback<Boolean> onDeviceCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("deviceId", Integer.valueOf(i));
        jsonObject.addProperty("deviceName", str);
        jsonObject.addProperty("otaVersion", String.valueOf(str2));
        jsonObject.addProperty("mac", str3);
        jsonObject.addProperty("phonePower", AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        jsonObject.addProperty("phoneOs", (Number) 2);
        jsonObject.addProperty("phoneModel", AppUtil.getPhoneModel());
        jsonObject.addProperty("osVersion", AppUtil.getSystemVersionName());
        jsonObject.addProperty("phoneImei", AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        DeviceApi.api.uploadDevice(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<BaseEntity>() { // from class: com.ido.life.data.device.remote.DeviceManager.1
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntity baseEntity) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onSuccess(Boolean.valueOf(baseEntity.status == 200));
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i2, String str4) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onFailed(i2, str4);
                }
            }
        });
    }

    public static void requestDeviceList(final OnDeviceCallback<List<DeviceListEntity.DeviceInfo>> onDeviceCallback) {
        DeviceApi.api.requestDeviceList(RequestBody.create(MediaType.parse("application/json"), "")).enqueue(new ApiCallback<DeviceListEntity>() { // from class: com.ido.life.data.device.remote.DeviceManager.2
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(DeviceListEntity deviceListEntity) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onSuccess(deviceListEntity.getResult());
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onFailed(i, str);
                }
            }
        });
    }

    public static void requestLanguageList(OnDeviceCallback<List<RemoteLanguage.LanguageInfo>> onDeviceCallback) {
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (currentDeviceInfo == null) {
            currentDeviceInfo = new BLEDevice();
        }
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        if (basicInfo != null) {
            currentDeviceInfo.mDeviceId = basicInfo.deivceId;
            currentDeviceInfo.version = basicInfo.firmwareVersion;
        }
        DeviceApi.api.requestLanguageList(currentDeviceInfo.mDeviceName, String.valueOf(currentDeviceInfo.mDeviceId), String.valueOf(currentDeviceInfo.version)).enqueue(getApiCallBack(onDeviceCallback));
    }

    public static void requestLanguageList(String str, OnDeviceCallback<List<RemoteLanguage.LanguageInfo>> onDeviceCallback) {
        BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
        if (currentDeviceInfo == null) {
            currentDeviceInfo = new BLEDevice();
        }
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        if (basicInfo != null) {
            currentDeviceInfo.mDeviceId = basicInfo.deivceId;
            currentDeviceInfo.version = basicInfo.firmwareVersion;
        }
        if (!DeviceConfigHelper.getSupportFunctionInfo().V3_v2_02_EB_firmware_bt_version_01_create) {
            str = String.valueOf(currentDeviceInfo.version);
        }
        DeviceApi.api.requestLanguageList(currentDeviceInfo.mDeviceName, String.valueOf(currentDeviceInfo.mDeviceId), str).enqueue(getApiCallBack(onDeviceCallback));
    }

    private static ApiCallback<RemoteLanguage> getApiCallBack(final OnDeviceCallback<List<RemoteLanguage.LanguageInfo>> onDeviceCallback) {
        return new ApiCallback<RemoteLanguage>() { // from class: com.ido.life.data.device.remote.DeviceManager.3
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(RemoteLanguage remoteLanguage) {
                CommonLogUtil.d("onSuccess: " + remoteLanguage.toString());
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onSuccess(remoteLanguage.getLanguageList());
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                CommonLogUtil.d("onError: " + str);
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onFailed(i, str);
                }
            }
        };
    }

    public static void deleteDevice(final String str, final OnDeviceCallback<String> onDeviceCallback) {
        DeviceApi.api.deleteDevice(str).enqueue(new ApiCallback<BaseEntity>() { // from class: com.ido.life.data.device.remote.DeviceManager.4
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntity baseEntity) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onSuccess(str);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str2) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onFailed(i, str2);
                }
            }
        });
    }

    public static void requestTopDialPlate(AppBLEDevice appBLEDevice, String str, String str2, String str3, final OnDeviceCallback<List<TopDialPlateEntity.DialPlate>> onDeviceCallback) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "DeviceInfoPresenter", "requestTopDialPlatedialPlates ：otaVersion" + appBLEDevice.getDeviceThirdVersion());
        DeviceApi.api.requestTopDialPlate(appBLEDevice.mDeviceName, String.valueOf(appBLEDevice.mDeviceId), str, str2, str3, appBLEDevice.getDeviceThirdVersion()).enqueue(new ApiCallback<TopDialPlateEntity>() { // from class: com.ido.life.data.device.remote.DeviceManager.5
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(TopDialPlateEntity topDialPlateEntity) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onSuccess(topDialPlateEntity.getResult());
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str4) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onFailed(i, str4);
                }
            }
        });
    }

    public static void checkFirmwareInfo(AppBLEDevice appBLEDevice, final OnDeviceCallback<OtaEntity.OtaInfo> onDeviceCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("language", LanguageUtil.getSystemLanguage());
        jsonObject.addProperty("mac", appBLEDevice.mDeviceAddress);
        jsonObject.addProperty("deviceName", appBLEDevice.mDeviceName);
        jsonObject.addProperty("deviceId", Integer.valueOf(appBLEDevice.mDeviceId));
        jsonObject.addProperty("otaVersion", appBLEDevice.getDeviceThirdVersion());
        jsonObject.addProperty("phoneModel", AppUtil.getPhoneModel());
        jsonObject.addProperty("appVersion", AppUtil.getVersionName(IdoApp.getAppContext()));
        DeviceApi.api.checkOtaInfo(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<OtaEntity>() { // from class: com.ido.life.data.device.remote.DeviceManager.6
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(OtaEntity otaEntity) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onSuccess(otaEntity.getResult());
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onFailed(i, str);
                }
            }
        });
    }

    public static void checkDfuFirmwareInfo(AppBLEDevice appBLEDevice, int i, final OnDeviceCallback<OtaEntity.OtaInfo> onDeviceCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("language", LanguageUtil.getSystemLanguage());
        jsonObject.addProperty("mac", appBLEDevice.mDeviceAddress);
        jsonObject.addProperty("deviceName", appBLEDevice.mDeviceName);
        jsonObject.addProperty("deviceId", Integer.valueOf(appBLEDevice.mDeviceId));
        jsonObject.addProperty("otaVersion", appBLEDevice.getDeviceThirdVersion());
        jsonObject.addProperty("phoneModel", AppUtil.getPhoneModel());
        jsonObject.addProperty("type", Integer.valueOf(i));
        DeviceApi.api.checkDfuOtaInfo(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<OtaEntity>() { // from class: com.ido.life.data.device.remote.DeviceManager.7
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(OtaEntity otaEntity) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onSuccess(otaEntity.getResult());
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i2, String str) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onFailed(i2, str);
                }
            }
        });
    }

    public static void requestWeatherFromServer(String str, final OnDeviceCallback<WeatherEntity.ServerWeather> onDeviceCallback) {
        LatLngBean location = SPHelper.getLocation();
        if (location == null) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getWeatherLogPath(), TAG, "---------未定位到用户位置，无法获取天气数据--------");
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getWeatherLogPath(), TAG, "requestWeatherFromServer，location ：" + location);
        DeviceApi.api.requestWeatherData(String.valueOf(location.longitude), String.valueOf(location.latitude), 1, str).enqueue(new ApiCallback<WeatherEntity>() { // from class: com.ido.life.data.device.remote.DeviceManager.8
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(WeatherEntity weatherEntity) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onSuccess(weatherEntity.result);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str2) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onFailed(i, str2);
                }
            }
        });
    }

    public static void requestDeviceWhiteList(final OnDeviceCallback<List<DeviceWhiteListEntity.DeviceInfo>> onDeviceCallback) {
        DeviceApi.api.requestDeviceWhiteList().enqueue(new ApiCallback<DeviceWhiteListEntity>() { // from class: com.ido.life.data.device.remote.DeviceManager.9
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(DeviceWhiteListEntity deviceWhiteListEntity) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onSuccess(deviceWhiteListEntity.getResult());
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onFailed(i, str);
                }
            }
        });
    }

    public static void getDeviceTypeById(final AppBLEDevice appBLEDevice, final String str, final OnDeviceCallback<List<MyDialListEntity.DialInfo>> onDeviceCallback) {
        if (appBLEDevice == null) {
            return;
        }
        DialDefinedUtil.getDialDefinedVersion(new OnDeviceCallback<String>() { // from class: com.ido.life.data.device.remote.DeviceManager.10
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(String str2) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    DeviceManager.requestMyDialList(appBLEDevice, str, onDeviceCallback2, str2);
                }
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str2) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    DeviceManager.requestMyDialList(appBLEDevice, str, onDeviceCallback2, null);
                }
            }
        });
    }

    public static void requestMyDialList(AppBLEDevice appBLEDevice, String str, final OnDeviceCallback<List<MyDialListEntity.DialInfo>> onDeviceCallback, String str2) {
        DeviceApi.api.requestMyDialList(appBLEDevice.mDeviceName, appBLEDevice.mDeviceId, appBLEDevice.mDeviceAddress, str, str2, DialDefinedUtil.appFaceVersion, appBLEDevice.getDeviceThirdVersion()).enqueue(new ApiCallback<MyDialListEntity>() { // from class: com.ido.life.data.device.remote.DeviceManager.11
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(MyDialListEntity myDialListEntity) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onSuccess(myDialListEntity.result);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str3) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onFailed(i, str3);
                }
            }
        });
    }

    public static void requestDialInfo(String str, long j, String str2, String str3, int i, final OnDeviceCallback<MyDialListEntity.DialInfo> onDeviceCallback) {
        DeviceApi.api.requestDialInfo(j, str, str2, str3, String.valueOf(i)).enqueue(new ApiCallback<DialEntity>() { // from class: com.ido.life.data.device.remote.DeviceManager.12
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(DialEntity dialEntity) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onSuccess(dialEntity.result);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i2, String str4) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onFailed(i2, str4);
                }
            }
        });
    }

    public static void addDial(BLEDevice bLEDevice, long j, final OnDeviceCallback<Boolean> onDeviceCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("deviceName", bLEDevice.mDeviceName);
        jsonObject.addProperty("id", Long.valueOf(j));
        jsonObject.addProperty("deviceId", Integer.valueOf(bLEDevice.mDeviceId));
        DeviceApi.api.addDial(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<DialUpdateEntity>() { // from class: com.ido.life.data.device.remote.DeviceManager.13
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(DialUpdateEntity dialUpdateEntity) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onSuccess(Boolean.valueOf(dialUpdateEntity.result));
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onFailed(i, str);
                }
            }
        });
    }

    public static void addDialCollect(BLEDevice bLEDevice, long j, final OnDeviceCallback<Boolean> onDeviceCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("faceId", Long.valueOf(j));
        jsonObject.addProperty("deviceName", bLEDevice.mDeviceName);
        jsonObject.addProperty("mac", bLEDevice.mDeviceAddress);
        jsonObject.addProperty("deviceId", Integer.valueOf(bLEDevice.mDeviceId));
        jsonObject.addProperty("mac", bLEDevice.mDeviceAddress);
        jsonObject.addProperty("userDeviceFaceType", "CUSTOMIZED_FACE");
        DeviceApi.api.addDialCollect(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<DialUpdateEntity>() { // from class: com.ido.life.data.device.remote.DeviceManager.14
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(DialUpdateEntity dialUpdateEntity) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onSuccess(Boolean.valueOf(dialUpdateEntity.result));
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onFailed(i, str);
                }
            }
        });
    }

    public static void cancelDialCollect(BLEDevice bLEDevice, long j, final OnDeviceCallback<Boolean> onDeviceCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("faceId", Long.valueOf(j));
        jsonObject.addProperty("deviceName", bLEDevice.mDeviceName);
        jsonObject.addProperty("mac", bLEDevice.mDeviceAddress);
        jsonObject.addProperty("deviceId", Integer.valueOf(bLEDevice.mDeviceId));
        jsonObject.addProperty("mac", bLEDevice.mDeviceAddress);
        jsonObject.addProperty("userDeviceFaceType", "CUSTOMIZED_FACE");
        DeviceApi.api.cancelDialCollect(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<DialUpdateEntity>() { // from class: com.ido.life.data.device.remote.DeviceManager.15
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(DialUpdateEntity dialUpdateEntity) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onSuccess(Boolean.valueOf(dialUpdateEntity.result));
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onFailed(i, str);
                }
            }
        });
    }

    public static void checkDialUpdate(BLEDevice bLEDevice, long j, final OnDeviceCallback<Boolean> onDeviceCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("deviceName", bLEDevice.mDeviceName);
        jsonObject.addProperty("timeStamp", Long.valueOf(j));
        jsonObject.addProperty("deviceId", Integer.valueOf(bLEDevice.mDeviceId));
        DeviceApi.api.checkDialUpdate(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<DialUpdateEntity>() { // from class: com.ido.life.data.device.remote.DeviceManager.16
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(DialUpdateEntity dialUpdateEntity) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onSuccess(Boolean.valueOf(dialUpdateEntity.result));
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onFailed(i, str);
                }
            }
        });
    }

    public static void deleteDial(BLEDevice bLEDevice, long j, final OnDeviceCallback<Boolean> onDeviceCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("deviceName", bLEDevice.mDeviceName);
        jsonObject.addProperty("deviceId", Integer.valueOf(bLEDevice.mDeviceId));
        jsonObject.addProperty("id", Long.valueOf(j));
        DeviceApi.api.deleteDial(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<DialUpdateEntity>() { // from class: com.ido.life.data.device.remote.DeviceManager.17
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(DialUpdateEntity dialUpdateEntity) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onSuccess(Boolean.valueOf(dialUpdateEntity.result));
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onFailed(i, str);
                }
            }
        });
    }

    public static void getDeviceTypeByIdDialMarket(final BLEDevice bLEDevice, final OnDeviceCallback<List<DialMarket.DialType>> onDeviceCallback) {
        DialDefinedUtil.getDialDefinedVersion(new OnDeviceCallback<String>() { // from class: com.ido.life.data.device.remote.DeviceManager.18
            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onSuccess(String str) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    DeviceManager.requestDialMarketListNew(bLEDevice, onDeviceCallback2, str);
                }
            }

            @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
            public void onFailed(int i, String str) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    DeviceManager.requestDialMarketListNew(bLEDevice, onDeviceCallback2, null);
                }
            }
        });
    }

    public static void getDeviceTypeByIdDialMarketDetail(final BLEDevice bLEDevice, final OnDeviceCallback<DialMarketDetail.DialInfoDetail> onDeviceCallback, final int i, final int i2, final int i3) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), TAG, "getDeviceTypeByIdDialMarketDetail: mSupportVersion" + DialDefinedUtil.mSupportVersion);
        if (DialDefinedUtil.mSupportVersion.equals("-1")) {
            DialDefinedUtil.getDialDefinedVersion(new OnDeviceCallback<String>() { // from class: com.ido.life.data.device.remote.DeviceManager.19
                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onSuccess(String str) {
                    OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                    if (onDeviceCallback2 != null) {
                        int i4 = i;
                        if (i4 == -2) {
                            DeviceManager.requestRecommandDial(bLEDevice, onDeviceCallback2, str, i2, i3);
                        } else if (i4 == -1) {
                            DeviceManager.requestOnlineDial(bLEDevice, onDeviceCallback2, str, i2, i3);
                        } else {
                            DeviceManager.requestDialMarketListByid(bLEDevice, onDeviceCallback2, str, i4, i2, i3);
                        }
                    }
                }

                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onFailed(int i4, String str) {
                    OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                    if (onDeviceCallback2 != null) {
                        int i5 = i;
                        if (i5 == -2) {
                            DeviceManager.requestRecommandDial(bLEDevice, onDeviceCallback2, null, i2, i3);
                        } else if (i5 == -1) {
                            DeviceManager.requestOnlineDial(bLEDevice, onDeviceCallback2, null, i2, i3);
                        } else {
                            DeviceManager.requestDialMarketListByid(bLEDevice, onDeviceCallback2, null, i5, i2, i3);
                        }
                    }
                }
            });
            return;
        }
        if (onDeviceCallback != null) {
            if (i == -2) {
                requestRecommandDial(bLEDevice, onDeviceCallback, DialDefinedUtil.mSupportVersion, i2, i3);
            } else if (i == -1) {
                requestOnlineDial(bLEDevice, onDeviceCallback, DialDefinedUtil.mSupportVersion, i2, i3);
            } else {
                requestDialMarketListByid(bLEDevice, onDeviceCallback, DialDefinedUtil.mSupportVersion, i, i2, i3);
            }
        }
    }

    public static void getMyDialRecord(final BLEDevice bLEDevice, final OnDeviceCallback<DialMarketDetail.DialInfoDetail> onDeviceCallback, final int i, final int i2) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), TAG, "getDeviceTypeByIdDialMarketDetail: mSupportVersion" + DialDefinedUtil.mSupportVersion);
        if (DialDefinedUtil.mSupportVersion.equals("-1")) {
            DialDefinedUtil.getDialDefinedVersion(new OnDeviceCallback<String>() { // from class: com.ido.life.data.device.remote.DeviceManager.20
                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onSuccess(String str) {
                    OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                    if (onDeviceCallback2 != null) {
                        DeviceManager.requestMyDialRecord(bLEDevice, onDeviceCallback2, str, i, i2);
                    }
                }

                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onFailed(int i3, String str) {
                    OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                    if (onDeviceCallback2 != null) {
                        DeviceManager.requestMyDialRecord(bLEDevice, onDeviceCallback2, DialDefinedUtil.mSupportVersion, i, i2);
                    }
                }
            });
        } else if (onDeviceCallback != null) {
            requestMyDialRecord(bLEDevice, onDeviceCallback, DialDefinedUtil.mSupportVersion, i, i2);
        }
    }

    public static void requestDialMarketList(BLEDevice bLEDevice, final OnDeviceCallback<List<DialMarket.DialType>> onDeviceCallback, String str) {
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "requestDialMarketList device：" + bLEDevice);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "requestDialMarketList appFaceVersion：" + str);
        DeviceApi.api.requestDialMarketList(bLEDevice.mDeviceName, String.valueOf(bLEDevice.mDeviceId), LanguageUtil.getSystemLanguage(), basicInfo == null ? AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE : String.valueOf(basicInfo.user_defined_dial_main_version), str, String.valueOf(bLEDevice.version)).enqueue(new ApiCallback<DialMarket>() { // from class: com.ido.life.data.device.remote.DeviceManager.21
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(DialMarket dialMarket) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "requestDialMarketList onSuccess：" + dialMarket);
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onSuccess(dialMarket.getResult());
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str2) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "requestDialMarketList onError：" + str2);
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onFailed(i, str2);
                }
            }
        });
    }

    public static void requestDialMarketListNew(BLEDevice bLEDevice, final OnDeviceCallback<List<DialMarket.DialType>> onDeviceCallback, String str) {
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "requestDialMarketListNew device：" + bLEDevice);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "requestDialMarketListNew appFaceVersion：" + str);
        DeviceApi.api.requestDialMarketListNew(bLEDevice.mDeviceName, String.valueOf(bLEDevice.mDeviceId), LanguageUtil.getSystemLanguage(), basicInfo == null ? AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE : String.valueOf(basicInfo.user_defined_dial_main_version), str, String.valueOf(bLEDevice.version), 7).enqueue(new ApiCallback<DialMarket>() { // from class: com.ido.life.data.device.remote.DeviceManager.22
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(DialMarket dialMarket) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "requestDialMarketListNew onSuccess：" + dialMarket);
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onSuccess(dialMarket.getResult());
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str2) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "requestDialMarketListNew onError：" + str2);
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onFailed(i, str2);
                }
            }
        });
    }

    public static void requestDialMarketListByid(BLEDevice bLEDevice, final OnDeviceCallback<DialMarketDetail.DialInfoDetail> onDeviceCallback, String str, int i, int i2, int i3) {
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "requestDialMarketListByid device：" + bLEDevice);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "requestDialMarketListByid appFaceVersion：" + str);
        DeviceApi.api.requestDialMarketListById(bLEDevice.mDeviceName, String.valueOf(bLEDevice.mDeviceId), LanguageUtil.getSystemLanguage(), basicInfo == null ? AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE : String.valueOf(basicInfo.user_defined_dial_main_version), str, String.valueOf(bLEDevice.version), i2, i3, i).enqueue(new ApiCallback<DialMarketDetail>() { // from class: com.ido.life.data.device.remote.DeviceManager.23
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(DialMarketDetail dialMarketDetail) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "requestDialMarketListByid onSuccess：" + dialMarketDetail);
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onSuccess(dialMarketDetail.getResult());
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i4, String str2) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "requestDialMarketListByid onError：" + str2);
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onFailed(i4, str2);
                }
            }
        });
    }

    public static void requestMyDialRecord(BLEDevice bLEDevice, final OnDeviceCallback<DialMarketDetail.DialInfoDetail> onDeviceCallback, String str, int i, int i2) {
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "requestDialMarketListByid device：" + bLEDevice);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "requestDialMarketListByid appFaceVersion：" + str);
        DeviceApi.api.requestMyDialRecord(bLEDevice.mDeviceName, String.valueOf(bLEDevice.mDeviceId), LanguageUtil.getSystemLanguage(), basicInfo == null ? AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE : String.valueOf(basicInfo.user_defined_dial_main_version), str, i, i2, bLEDevice.mDeviceAddress).enqueue(new ApiCallback<DialMarketDetail>() { // from class: com.ido.life.data.device.remote.DeviceManager.24
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(DialMarketDetail dialMarketDetail) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "requestDialMarketListByid onSuccess：" + dialMarketDetail);
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onSuccess(dialMarketDetail.getResult());
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i3, String str2) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "requestDialMarketListByid onError：" + str2);
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onFailed(i3, str2);
                }
            }
        });
    }

    public static void requestOnlineDial(BLEDevice bLEDevice, final OnDeviceCallback<DialMarketDetail.DialInfoDetail> onDeviceCallback, String str, int i, int i2) {
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "requestOnlineDial device：" + bLEDevice);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "requestOnlineDial appFaceVersion：" + str);
        DeviceApi.api.requestOnlineDial(bLEDevice.mDeviceName, String.valueOf(bLEDevice.mDeviceId), LanguageUtil.getSystemLanguage(), basicInfo == null ? AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE : String.valueOf(basicInfo.user_defined_dial_main_version), str, String.valueOf(bLEDevice.version), i, i2).enqueue(new ApiCallback<DialMarketDetail>() { // from class: com.ido.life.data.device.remote.DeviceManager.25
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(DialMarketDetail dialMarketDetail) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "requestOnlineDial onSuccess：" + dialMarketDetail);
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onSuccess(dialMarketDetail.getResult());
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i3, String str2) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "requestOnlineDial onError：" + str2);
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onFailed(i3, str2);
                }
            }
        });
    }

    public static void requestRecommandDial(BLEDevice bLEDevice, final OnDeviceCallback<DialMarketDetail.DialInfoDetail> onDeviceCallback, String str, int i, int i2) {
        BasicInfo basicInfo = LocalDataManager.getBasicInfo();
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "requestRecommandDial device：" + bLEDevice);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "requestRecommandDial appFaceVersion：" + str);
        DeviceApi.api.requestRecomandDial(bLEDevice.mDeviceName, String.valueOf(bLEDevice.mDeviceId), LanguageUtil.getSystemLanguage(), basicInfo == null ? AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE : String.valueOf(basicInfo.user_defined_dial_main_version), str, String.valueOf(bLEDevice.version), i, i2).enqueue(new ApiCallback<DialMarketDetail>() { // from class: com.ido.life.data.device.remote.DeviceManager.26
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(DialMarketDetail dialMarketDetail) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "requestRecommandDial onSuccess：" + dialMarketDetail);
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onSuccess(dialMarketDetail.getResult());
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i3, String str2) {
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getDialLogPath(), "requestRecommandDial onError：" + str2);
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onFailed(i3, str2);
                }
            }
        });
    }

    public static void requestDialState(BLEDevice bLEDevice, long j, final OnDeviceCallback<Boolean> onDeviceCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("deviceName", bLEDevice.mDeviceName);
        jsonObject.addProperty("deviceId", Integer.valueOf(bLEDevice.mDeviceId));
        jsonObject.addProperty("id", Long.valueOf(j));
        DeviceApi.api.requestDialState(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<DialState>() { // from class: com.ido.life.data.device.remote.DeviceManager.27
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(DialState dialState) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onSuccess(Boolean.valueOf(dialState.getResult() != null));
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onFailed(i, str);
                }
            }
        });
    }

    public static void requestDialCollectList(BLEDevice bLEDevice, final OnDeviceCallback<DialMarketDetail.DialInfoDetail> onDeviceCallback, int i, int i2) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("deviceName", bLEDevice.mDeviceName);
        jsonObject.addProperty("deviceId", Integer.valueOf(bLEDevice.mDeviceId));
        jsonObject.addProperty("language", LanguageUtil.getSystemLanguage());
        jsonObject.addProperty("mac", bLEDevice.mDeviceAddress);
        jsonObject.addProperty("pageId", Integer.valueOf(i));
        jsonObject.addProperty("pageSize", Integer.valueOf(i2));
        DeviceApi.api.requestDialCollect(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<DialMarketDetail>() { // from class: com.ido.life.data.device.remote.DeviceManager.28
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(DialMarketDetail dialMarketDetail) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onSuccess(dialMarketDetail.getResult());
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i3, String str) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onFailed(i3, str);
                }
            }
        });
    }

    public static void updateOtaLog(OtaEntity.OtaInfo otaInfo, String str, String str2, int i, int i2, boolean z, final OnDeviceCallback<Boolean> onDeviceCallback) {
        if (otaInfo == null) {
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("otaId", Integer.valueOf(otaInfo.getId()));
        jsonObject.addProperty("mac", str);
        jsonObject.addProperty("deviceName", otaInfo.getDeviceModel());
        jsonObject.addProperty("deviceId", otaInfo.getDeviceId());
        jsonObject.addProperty("currentVersion", str2);
        jsonObject.addProperty("targetVersion", otaInfo.getVersion());
        jsonObject.addProperty("mobileBrand", otaInfo.getDeviceModel());
        jsonObject.addProperty("appVersion", AppUtil.getVersionName(IdoApp.getAppContext()));
        jsonObject.addProperty(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(i));
        jsonObject.addProperty(AuthorizationResponseParser.ERROR, (Boolean) false);
        jsonObject.addProperty(MyLocationStyle.ERROR_CODE, Integer.valueOf(i2));
        jsonObject.addProperty("message", "");
        jsonObject.addProperty("sid", otaInfo.getSid());
        final String string = jsonObject.toString();
        RequestBody requestBodyCreate = RequestBody.create(MediaType.parse("application/json"), string);
        if (z) {
            SPHelper.saveDfuOtaCacheLog(string);
            MainPresenter.otaCacheLogUploading = true;
            DeviceApi.api.updateDfuOtaLog(requestBodyCreate).enqueue(new ApiCallback<BaseEntityNew<Boolean>>() { // from class: com.ido.life.data.device.remote.DeviceManager.29
                @Override // com.ido.life.data.listener.NetCallback
                public void onSuccess(BaseEntityNew<Boolean> baseEntityNew) {
                    Boolean result = baseEntityNew.getResult();
                    if (result.booleanValue()) {
                        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getOtaLogPath(), "DeviceManager", "updateDfuOtaLog onSuccess，removeDfuOtaCacheLog");
                        SPHelper.removeDfuOtaCacheLog(string, true);
                    }
                    MainPresenter.otaCacheLogUploading = false;
                    OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                    if (onDeviceCallback2 != null) {
                        onDeviceCallback2.onSuccess(result);
                    }
                }

                @Override // com.ido.life.data.listener.NetCallback
                public void onError(Throwable th, int i3, String str3) {
                    MainPresenter.otaCacheLogUploading = false;
                    OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                    if (onDeviceCallback2 != null) {
                        onDeviceCallback2.onFailed(i3, str3);
                    }
                }
            });
        } else {
            SPHelper.saveOtaCacheLog(string);
            MainPresenter.dfuOtaCacheLogUploading = true;
            DeviceApi.api.updateOtaLog(requestBodyCreate).enqueue(new ApiCallback<BaseEntityNew<Boolean>>() { // from class: com.ido.life.data.device.remote.DeviceManager.30
                @Override // com.ido.life.data.listener.NetCallback
                public void onSuccess(BaseEntityNew<Boolean> baseEntityNew) {
                    Boolean result = baseEntityNew.getResult();
                    if (result.booleanValue()) {
                        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getOtaLogPath(), "DeviceManager", "updateOtaLog onSuccess，removeOtaCacheLog");
                        SPHelper.removeOtaCacheLog(string, true);
                    }
                    MainPresenter.dfuOtaCacheLogUploading = false;
                    OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                    if (onDeviceCallback2 != null) {
                        onDeviceCallback2.onSuccess(result);
                    }
                }

                @Override // com.ido.life.data.listener.NetCallback
                public void onError(Throwable th, int i3, String str3) {
                    MainPresenter.dfuOtaCacheLogUploading = false;
                    OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                    if (onDeviceCallback2 != null) {
                        onDeviceCallback2.onFailed(i3, str3);
                    }
                }
            });
        }
    }

    public static void updateOtaCacheLog(boolean z, final String str, final OnDeviceCallback<Boolean> onDeviceCallback) {
        RequestBody requestBodyCreate = RequestBody.create(MediaType.parse("application/json"), str);
        if (z) {
            DeviceApi.api.updateDfuOtaLog(requestBodyCreate).enqueue(new ApiCallback<BaseEntityNew<Boolean>>() { // from class: com.ido.life.data.device.remote.DeviceManager.31
                @Override // com.ido.life.data.listener.NetCallback
                public void onSuccess(BaseEntityNew<Boolean> baseEntityNew) {
                    Boolean result = baseEntityNew.getResult();
                    if (result.booleanValue()) {
                        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getOtaLogPath(), "DeviceManager", "updateDfuOtaCacheLog onSuccess，removeDfuOtaCacheLog");
                        SPHelper.removeDfuOtaCacheLog(str, false);
                    }
                    OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                    if (onDeviceCallback2 != null) {
                        onDeviceCallback2.onSuccess(result);
                    }
                }

                @Override // com.ido.life.data.listener.NetCallback
                public void onError(Throwable th, int i, String str2) {
                    OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                    if (onDeviceCallback2 != null) {
                        onDeviceCallback2.onFailed(i, str2);
                    }
                }
            });
        } else {
            DeviceApi.api.updateOtaLog(requestBodyCreate).enqueue(new ApiCallback<BaseEntityNew<Boolean>>() { // from class: com.ido.life.data.device.remote.DeviceManager.32
                @Override // com.ido.life.data.listener.NetCallback
                public void onSuccess(BaseEntityNew<Boolean> baseEntityNew) {
                    Boolean result = baseEntityNew.getResult();
                    if (result.booleanValue()) {
                        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getOtaLogPath(), "DeviceManager", "updateOtaCacheLog onSuccess，removeOtaCacheLog");
                        SPHelper.removeOtaCacheLog(str, false);
                    }
                    OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                    if (onDeviceCallback2 != null) {
                        onDeviceCallback2.onSuccess(result);
                    }
                }

                @Override // com.ido.life.data.listener.NetCallback
                public void onError(Throwable th, int i, String str2) {
                    OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                    if (onDeviceCallback2 != null) {
                        onDeviceCallback2.onFailed(i, str2);
                    }
                }
            });
        }
    }

    public static void uploadDialLog(long j, String str, int i, String str2, final OnDeviceCallback<Boolean> onDeviceCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("faceId", Long.valueOf(j));
        jsonObject.addProperty("mac", str);
        jsonObject.addProperty(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(i));
        jsonObject.addProperty("sid", str2);
        DeviceApi.api.uploadDialLog(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<BaseEntityNew<Boolean>>() { // from class: com.ido.life.data.device.remote.DeviceManager.33
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntityNew<Boolean> baseEntityNew) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onSuccess(baseEntityNew.getResult());
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i2, String str3) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onFailed(i2, str3);
                }
            }
        });
    }

    public static void uploadLanguageLog(long j, BLEDevice bLEDevice, int i, String str, final OnDeviceCallback<Boolean> onDeviceCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("langId", Long.valueOf(j));
        jsonObject.addProperty("deviceName", bLEDevice.mDeviceName);
        jsonObject.addProperty("deviceId", Integer.valueOf(bLEDevice.mDeviceId));
        jsonObject.addProperty("mac", bLEDevice.mDeviceAddress);
        jsonObject.addProperty(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(i));
        jsonObject.addProperty("sid", str);
        final String string = jsonObject.toString();
        SPHelper.saveLanguageCacheLog(string);
        RequestBody requestBodyCreate = RequestBody.create(MediaType.parse("application/json"), string);
        MainPresenter.languageCacheLogUploading = true;
        DeviceApi.api.uploadLanguageLog(requestBodyCreate).enqueue(new ApiCallback<BaseEntityNew<Boolean>>() { // from class: com.ido.life.data.device.remote.DeviceManager.34
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntityNew<Boolean> baseEntityNew) {
                MainPresenter.languageCacheLogUploading = false;
                Boolean result = baseEntityNew.getResult();
                if (result.booleanValue()) {
                    SPHelper.removeLanguageCacheLog(string, true);
                }
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onSuccess(result);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i2, String str2) {
                MainPresenter.languageCacheLogUploading = false;
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onFailed(i2, str2);
                }
            }
        });
    }

    public static void uploadLanguageCacheLog(final String str, final OnDeviceCallback<Boolean> onDeviceCallback) {
        DeviceApi.api.uploadLanguageLog(RequestBody.create(MediaType.parse("application/json"), str)).enqueue(new ApiCallback<BaseEntityNew<Boolean>>() { // from class: com.ido.life.data.device.remote.DeviceManager.35
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntityNew<Boolean> baseEntityNew) {
                Boolean result = baseEntityNew.getResult();
                if (result.booleanValue()) {
                    SPHelper.removeLanguageCacheLog(str, false);
                }
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onSuccess(result);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str2) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onFailed(i, str2);
                }
            }
        });
    }

    public static void checkFlashInfo(AppBLEDevice appBLEDevice, int i, final OnDeviceCallback<OtaEntity.OtaInfo> onDeviceCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("language", LanguageUtil.getSystemLanguage());
        jsonObject.addProperty("mac", appBLEDevice.mDeviceAddress);
        jsonObject.addProperty("deviceName", appBLEDevice.mDeviceName);
        jsonObject.addProperty("deviceId", Integer.valueOf(appBLEDevice.mDeviceId));
        jsonObject.addProperty("otaVersion", appBLEDevice.getDeviceThirdVersion());
        jsonObject.addProperty("fontVersion", Integer.valueOf(i));
        DeviceApi.api.checkFlashInfo(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<OtaEntity>() { // from class: com.ido.life.data.device.remote.DeviceManager.36
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(OtaEntity otaEntity) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onSuccess(otaEntity.getResult());
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i2, String str) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onFailed(i2, str);
                }
            }
        });
    }

    public static void checkModuleSystemInfo(BLEDevice bLEDevice, String str, final OnDeviceCallback<OtaEntity.OtaInfo> onDeviceCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mac", bLEDevice.mDeviceAddress);
        jsonObject.addProperty("deviceId", Integer.valueOf(bLEDevice.mDeviceId));
        jsonObject.addProperty(AlexaConstant.SPConstant.KEY_DEVICE_FIRMWAREVERSION, str);
        DeviceApi.api.checkModuleSystemInfo(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<OtaEntity>() { // from class: com.ido.life.data.device.remote.DeviceManager.37
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(OtaEntity otaEntity) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onSuccess(otaEntity.getResult());
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str2) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onFailed(i, str2);
                }
            }
        });
    }

    public static void updateFlashLog(OtaEntity.OtaInfo otaInfo, String str, int i, int i2, final OnDeviceCallback<Boolean> onDeviceCallback) {
        if (otaInfo == null) {
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("otaId", Integer.valueOf(otaInfo.getId()));
        jsonObject.addProperty("mac", str);
        jsonObject.addProperty("deviceName", otaInfo.getDeviceModel());
        jsonObject.addProperty("deviceId", otaInfo.getDeviceId());
        jsonObject.addProperty("currentVersion", Integer.valueOf(i));
        jsonObject.addProperty("targetVersion", otaInfo.getVersion());
        jsonObject.addProperty("mobileBrand", otaInfo.getDeviceModel());
        jsonObject.addProperty("appVersion", AppUtil.getVersionName(IdoApp.getAppContext()));
        jsonObject.addProperty(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(i2));
        jsonObject.addProperty(AuthorizationResponseParser.ERROR, (Boolean) false);
        jsonObject.addProperty("message", "");
        jsonObject.addProperty("sid", otaInfo.getSid());
        final String string = jsonObject.toString();
        SPHelper.saveFlashCacheLog(string);
        MainPresenter.flashCacheLogUploading = true;
        DeviceApi.api.updateFlashLog(RequestBody.create(MediaType.parse("application/json"), string)).enqueue(new ApiCallback<BaseEntityNew<Boolean>>() { // from class: com.ido.life.data.device.remote.DeviceManager.38
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntityNew<Boolean> baseEntityNew) {
                Boolean result = baseEntityNew.getResult();
                if (result.booleanValue()) {
                    SPHelper.removeFlashCacheLog(string, true);
                }
                MainPresenter.flashCacheLogUploading = false;
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onSuccess(result);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i3, String str2) {
                MainPresenter.flashCacheLogUploading = false;
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onFailed(i3, str2);
                }
            }
        });
    }

    public static void updateFlashCacheLog(final String str, final OnDeviceCallback<Boolean> onDeviceCallback) {
        DeviceApi.api.updateFlashLog(RequestBody.create(MediaType.parse("application/json"), str)).enqueue(new ApiCallback<BaseEntityNew<Boolean>>() { // from class: com.ido.life.data.device.remote.DeviceManager.39
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntityNew<Boolean> baseEntityNew) {
                Boolean result = baseEntityNew.getResult();
                if (result.booleanValue()) {
                    SPHelper.removeFlashCacheLog(str, false);
                }
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onSuccess(result);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str2) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 != null) {
                    onDeviceCallback2.onFailed(i, str2);
                }
            }
        });
    }

    public static void queryDeviceInfoList(final OnDeviceCallback<List<DeviceInfo>> onDeviceCallback, String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return;
        }
        String strConcat = "";
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str)) {
                if (!TextUtils.isEmpty(strConcat)) {
                    strConcat = strConcat.concat(AppInfo.DELIM).concat(str);
                } else {
                    strConcat = strConcat.concat(str);
                }
            }
        }
        DeviceApi.api.queryDeviceInfoList(strConcat).enqueue(new ApiCallback<BaseEntityNew<List<DeviceInfo>>>() { // from class: com.ido.life.data.device.remote.DeviceManager.40
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntityNew<List<DeviceInfo>> baseEntityNew) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 == null) {
                    return;
                }
                onDeviceCallback2.onSuccess(baseEntityNew.getResult());
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str2) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 == null) {
                    return;
                }
                onDeviceCallback2.onFailed(i, str2);
            }
        });
    }

    public static void uploadWallpaperDial(String str, String str2, BLEDevice bLEDevice, final OnDeviceCallback<WallPaperDialInfo> onDeviceCallback) {
        if (bLEDevice == null) {
            return;
        }
        File file = new File(str);
        DeviceApi.api.uploadWallWallpaperDial(new MultipartBody.Builder().addFormDataPart("file", formatParameter(file.getName()), RequestBody.create(MediaType.parse(FileUploadBase.MULTIPART_FORM_DATA), file)).addFormDataPart("otaFaceName", formatParameter(str2)).addFormDataPart("deviceName", formatParameter(bLEDevice.mDeviceName)).addFormDataPart("deviceId", formatParameter(String.valueOf(bLEDevice.mDeviceId))).addFormDataPart("mac", formatParameter(bLEDevice.mDeviceAddress)).build()).enqueue(new ApiCallback<BaseEntityNew<WallPaperDialInfo>>() { // from class: com.ido.life.data.device.remote.DeviceManager.41
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntityNew<WallPaperDialInfo> baseEntityNew) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 == null) {
                    return;
                }
                onDeviceCallback2.onSuccess(baseEntityNew.getResult());
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str3) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 == null) {
                    return;
                }
                onDeviceCallback2.onFailed(i, str3);
            }
        });
    }

    public static void requestBuiltInDialList(String str, int i, final OnDeviceCallback<List<MyDialListEntity.DialInfo>> onDeviceCallback) {
        DeviceApi.api.requestBuiltInDialList(str, String.valueOf(i), LanguageUtil.getSystemLanguage()).enqueue(new ApiCallback<MyDialListEntity>() { // from class: com.ido.life.data.device.remote.DeviceManager.42
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(MyDialListEntity myDialListEntity) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 == null) {
                    return;
                }
                onDeviceCallback2.onSuccess(myDialListEntity.result);
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i2, String str2) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 == null) {
                    return;
                }
                onDeviceCallback2.onFailed(i2, str2);
            }
        });
    }

    public static void requestDialInfosFromNames(final AppBLEDevice appBLEDevice, final List<String> list, final OnDeviceCallback<List<MyDialListEntity.DialInfo>> onDeviceCallback) {
        if (!DialDefinedUtil.mSupportVersion.equals("-1")) {
            requestDialInfoSFromNames(appBLEDevice, DialDefinedUtil.appFaceVersion, DialDefinedUtil.mSupportVersion, list, onDeviceCallback);
        } else {
            DialDefinedUtil.getDialDefinedVersion(new OnDeviceCallback<String>() { // from class: com.ido.life.data.device.remote.DeviceManager.43
                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onSuccess(String str) {
                    if (onDeviceCallback != null) {
                        DialDefinedUtil.appFaceVersion = str;
                        DeviceManager.requestDialInfoSFromNames(appBLEDevice, DialDefinedUtil.appFaceVersion, DialDefinedUtil.mSupportVersion, list, onDeviceCallback);
                    }
                }

                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onFailed(int i, String str) {
                    OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void requestDialInfoSFromNames(AppBLEDevice appBLEDevice, String str, String str2, List<String> list, final OnDeviceCallback<List<MyDialListEntity.DialInfo>> onDeviceCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("deviceId", (Object) Integer.valueOf(appBLEDevice.mDeviceId));
            jSONObject.put("deviceName", (Object) appBLEDevice.mDeviceName);
            jSONObject.put("supportFaceVersion", (Object) str2);
            jSONObject.put("appFaceVersion", (Object) str);
            jSONObject.put("otaVersion", (Object) appBLEDevice.getDeviceThirdVersion());
            jSONObject.put("language", (Object) LanguageUtil.getSystemLanguage());
            jSONObject.put("faceNames", JSONArray.toJSON(list));
        } catch (JSONException e2) {
            System.out.println("JSONException :" + e2.getMessage());
        }
        DeviceApi.api.requestMyDialInfoFromNames(RequestBody.create(MediaType.parse("application/json"), jSONObject.toString())).enqueue(new ApiCallback<MyDialListEntity>() { // from class: com.ido.life.data.device.remote.DeviceManager.44
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(MyDialListEntity myDialListEntity) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 == null) {
                    return;
                }
                onDeviceCallback2.onSuccess(myDialListEntity.result);
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str3) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 == null) {
                    return;
                }
                onDeviceCallback2.onFailed(i, str3);
            }
        });
    }

    public static void requestDialInfoFromName(String str, String str2, String str3, final OnDeviceCallback<DialMarket.DialType.Dial> onDeviceCallback) {
        DeviceApi.api.requestDialInfo(str, str2, str3, LanguageUtil.getSystemLanguage()).enqueue(new ApiCallback<DialInfoEntity>() { // from class: com.ido.life.data.device.remote.DeviceManager.45
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(DialInfoEntity dialInfoEntity) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 == null) {
                    return;
                }
                onDeviceCallback2.onSuccess(dialInfoEntity.getResult());
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str4) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 == null) {
                    return;
                }
                onDeviceCallback2.onFailed(i, str4);
            }
        });
    }

    public static void requestInstructionInfo(int i, final OnDeviceCallback<String> onDeviceCallback) {
        DeviceApi.api.requestInstructionInfo(String.valueOf(i)).enqueue(new ApiCallback<BaseEntityNew<String>>() { // from class: com.ido.life.data.device.remote.DeviceManager.46
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntityNew<String> baseEntityNew) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 == null) {
                    return;
                }
                onDeviceCallback2.onSuccess(baseEntityNew.getResult());
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i2, String str) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 == null) {
                    return;
                }
                onDeviceCallback2.onFailed(i2, str);
            }
        });
    }

    public static void getWallpaperUsageList(String str, String str2, String str3, final OnDeviceCallback<List<UsageDialBean>> onDeviceCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("deviceId", str);
        jsonObject.addProperty("deviceName", str2);
        jsonObject.addProperty("mac", str3);
        DeviceApi.api.getWallpaperUsageList(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<BaseEntityNew<List<UsageDialBean>>>() { // from class: com.ido.life.data.device.remote.DeviceManager.47
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntityNew<List<UsageDialBean>> baseEntityNew) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 == null) {
                    return;
                }
                onDeviceCallback2.onSuccess(baseEntityNew.getResult());
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str4) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 == null) {
                    return;
                }
                onDeviceCallback2.onFailed(i, str4);
            }
        });
    }

    public static void updateWallpaperUsage(String str, String str2, String str3, long j, final OnDeviceCallback<Object> onDeviceCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("deviceId", str);
        jsonObject.addProperty("deviceName", str2);
        jsonObject.addProperty("mac", str3);
        jsonObject.addProperty("wallpaperId", Long.valueOf(j));
        DeviceApi.api.updateWallpaperUsage(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<BaseEntityNew<Object>>() { // from class: com.ido.life.data.device.remote.DeviceManager.48
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntityNew<Object> baseEntityNew) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 == null) {
                    return;
                }
                onDeviceCallback2.onSuccess(baseEntityNew.getResult());
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str4) {
                OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                if (onDeviceCallback2 == null) {
                    return;
                }
                onDeviceCallback2.onFailed(i, str4);
            }
        });
    }
}