package com.ido.life.data.me.remote;

import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.common.IdoApp;
import com.ido.common.env.LanguagePreference;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.net.BaseEntity;
import com.ido.common.net.BaseEntityNew;
import com.ido.common.net.ErrorCode;
import com.ido.common.net.http.Result;
import com.ido.common.utils.DeviceUtil;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.utils.TimeUtil;
import com.ido.common.utils.ValidateUtil;
import com.ido.life.BuildConfig;
import com.ido.life.bean.PrivateSettingSaveBean;
import com.ido.life.bean.SavePrivateSafeSettingBean;
import com.ido.life.data.api.AccountApi;
import com.ido.life.data.api.entity.AppInfoEntity;
import com.ido.life.data.api.entity.FeedbackEntity;
import com.ido.life.data.api.entity.ResultBEntity;
import com.ido.life.data.api.entity.ResultSEntity;
import com.ido.life.data.api.entity.ThirdLoginEntity;
import com.ido.life.data.api.entity.UploadMedal;
import com.ido.life.data.api.entity.UserInfoEntity;
import com.ido.life.data.api.entity.UserTargetEntity;
import com.ido.life.data.api.entity.UserWeightEntity;
import com.ido.life.data.listener.ApiCallback;
import com.ido.life.data.listener.OnResponseCallback;
import com.ido.life.data.listener.OnResultCallback;
import com.ido.life.data.listener.OnResultLoginRegisterCallback;
import com.ido.life.database.model.Feedback;
import com.ido.life.database.model.SortBeanUpload;
import com.ido.life.database.model.ThirdLogin;
import com.ido.life.database.model.UserAgreementAndPrivacyVersionData;
import com.ido.life.database.model.UserInfo;
import com.ido.life.database.model.UserMedalInfo;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.database.model.middleModel.SortBeanEntity;
import com.ido.life.database.model.middleModel.UserAgreementAndPrivacyVersionEntity;
import com.ido.life.module.user.bindsetpassword.BindInputCodeActivity;
import com.ido.life.module.user.country.CountryChooseActivity;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SPHelper;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import no.nordicsemi.android.dfu.DfuBaseService;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class AccountManager {
    private static final String TAG = "AccountManager";

    public interface CommonCallback<T> {
        void onFailed(int i, String str);

        void onSuccess(T t);
    }

    public interface OnCommCallback<T> {
        void onFailed(String str);

        void onSuccess(T t);
    }

    public interface OnUserCallback {
        void onFailed(String str);

        void onSuccess(UserInfo userInfo);
    }

    public interface OnUserFeedbackCallback {
        void onFailed(String str);

        void onSuccess(Feedback feedback);
    }

    public interface OnUserTargetCallback {
        void onFailed(String str);

        void onSuccess(UserTargetNew userTargetNew);
    }

    public static void requestSignIn(String str, String str2, String str3, OnResultLoginRegisterCallback onResultLoginRegisterCallback) {
        if (TextUtils.isEmpty(str2)) {
            onResultLoginRegisterCallback.onFailed(-1, ResourceUtil.getString(R.string.me_input_correct_name));
        } else if (TextUtils.isEmpty(str3)) {
            onResultLoginRegisterCallback.onFailed(-1, ResourceUtil.getString(R.string.login_input_password));
        } else {
            AccountApi.api.login("", str2, str3).enqueue(getResultHttpExceptionSApiCallBack(onResultLoginRegisterCallback));
        }
    }

    public static void bindAlreadyRegister(Long l, String str, String str2, OnResultCallback onResultCallback) {
        if (TextUtils.isEmpty(str2)) {
            onResultCallback.onFailed(ResourceUtil.getString(R.string.login_input_password));
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("oauthId", l);
            jSONObject.put("account", str);
            jSONObject.put(BindInputCodeActivity.PASSWORD, str2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        AccountApi.api.loginThirdBindAlreadyRegister(RequestBody.create(MediaType.parse("application/json"), jSONObject.toString())).enqueue(getResultSApiCallBack(onResultCallback));
    }

    public static void requestBindSignUp(long j, String str, String str2, String str3, String str4, OnResultCallback onResultCallback) {
        if (!ValidateUtil.checkName(str2)) {
            onResultCallback.onFailed(ResourceUtil.getString(R.string.me_input_correct_name));
            return;
        }
        if (!ValidateUtil.checkPassword(str4)) {
            onResultCallback.onFailed(ResourceUtil.getString(R.string.register_tip_password_format));
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            str = str.replace("+", "").replaceAll(" ", "");
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("oauthId", j);
            jSONObject.put("areaCode", str);
            jSONObject.put("account", str2);
            jSONObject.put("verifyCode", str3);
            jSONObject.put(BindInputCodeActivity.PASSWORD, str4);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        CommonLogUtil.d(TAG, "requestSignUp: " + jSONObject.toString());
        AccountApi.api.requestBindRegisterAccount(RequestBody.create(MediaType.parse("application/json"), jSONObject.toString())).enqueue(getResultSApiCallBack(onResultCallback));
    }

    public static void loginThirdThroughAccessToken(String str, String str2, String str3, String str4, OnResultCallback onResultCallback) {
        AccountApi.api.loginThirdThroughAccessToken(str, str2, str3, str4).enqueue(getResultSApiCallBack(onResultCallback));
    }

    public static class AuthData {
        public String access_token;
        public String avatar;
        public String nickname;
        public String openid;
        public String sex;
        public String tokenSecret;

        public AuthData(String str, String str2, String str3, String str4, String str5) {
            this.openid = str;
            this.access_token = str2;
            this.nickname = str3;
            this.avatar = str4;
            this.sex = str5;
        }

        public String toJsonString() {
            return new Gson().toJson(this);
        }
    }

    public static void judgeEamilIsExist(String str, final OnResultCallback onResultCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("account", str);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        AccountApi.api.getEamilIsExist(RequestBody.create(MediaType.parse("application/json"), jSONObject.toString())).enqueue(new ApiCallback<ResultBEntity>() { // from class: com.ido.life.data.me.remote.AccountManager.1
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(ResultBEntity resultBEntity) {
                Result result = new Result();
                result.setData(Boolean.valueOf(resultBEntity.isResult()));
                OnResultCallback onResultCallback2 = onResultCallback;
                if (onResultCallback2 != null) {
                    onResultCallback2.onSuccess(result);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str2) {
                OnResultCallback onResultCallback2 = onResultCallback;
                if (onResultCallback2 != null) {
                    onResultCallback2.onFailed(str2);
                }
            }
        });
    }

    public static void getUserTarget(final OnUserTargetCallback onUserTargetCallback) {
        AccountApi.api.getUserTarget().enqueue(new ApiCallback<UserTargetEntity>() { // from class: com.ido.life.data.me.remote.AccountManager.2
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(UserTargetEntity userTargetEntity) {
                OnUserTargetCallback onUserTargetCallback2 = onUserTargetCallback;
                if (onUserTargetCallback2 != null) {
                    onUserTargetCallback2.onSuccess(AccountManager.convertUserTargetResponse(userTargetEntity));
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnUserTargetCallback onUserTargetCallback2 = onUserTargetCallback;
                if (onUserTargetCallback2 != null) {
                    onUserTargetCallback2.onFailed(str);
                }
            }
        });
    }

    public static void getUserInfo(final OnUserCallback onUserCallback) {
        AccountApi.api.getUserInfo().enqueue(new ApiCallback<UserInfoEntity>() { // from class: com.ido.life.data.me.remote.AccountManager.3
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(UserInfoEntity userInfoEntity) {
                OnUserCallback onUserCallback2 = onUserCallback;
                if (onUserCallback2 != null) {
                    if (userInfoEntity != null) {
                        onUserCallback2.onSuccess(userInfoEntity.getResult());
                    } else {
                        onUserCallback2.onSuccess(null);
                    }
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnUserCallback onUserCallback2 = onUserCallback;
                if (onUserCallback2 != null) {
                    onUserCallback2.onFailed(str);
                }
            }
        });
    }

    public static ApiCallback<FeedbackEntity> getUserFeedback(final OnResultCallback onResultCallback) {
        return new ApiCallback<FeedbackEntity>() { // from class: com.ido.life.data.me.remote.AccountManager.4
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(FeedbackEntity feedbackEntity) {
                Feedback result = feedbackEntity.getResult();
                Result result2 = new Result();
                result2.setData(result);
                onResultCallback.onSuccess(result2);
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                onResultCallback.onFailed(str);
            }
        };
    }

    public static void requestSignUp(String str, String str2, String str3, String str4, String str5, String str6, String str7, OnResultLoginRegisterCallback onResultLoginRegisterCallback) {
        if (!ValidateUtil.checkName(str2)) {
            onResultLoginRegisterCallback.onFailed(-1, ResourceUtil.getString(R.string.me_input_correct_name));
            return;
        }
        if (!ValidateUtil.checkPassword(str3)) {
            onResultLoginRegisterCallback.onFailed(-1, ResourceUtil.getString(R.string.register_tip_password_format));
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            str = str.replace("+", "").replaceAll(" ", "");
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("areaCode", str);
            jSONObject.put("account", str2);
            jSONObject.put(BindInputCodeActivity.PASSWORD, str3);
            jSONObject.put(CountryChooseActivity.COUNTRY, str4);
            jSONObject.put("city", str5);
            jSONObject.put("language", str6);
            jSONObject.put(LanguagePreference.Language_Region, str7);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "请求注册的信息requestSignUp: " + jSONObject.toString());
        AccountApi.api.register(RequestBody.create(MediaType.parse("application/json"), jSONObject.toString())).enqueue(getResultHttpExceptionSApiCallBack(onResultLoginRegisterCallback));
    }

    public static void requestGetCode(String str, String str2, String str3, OnResponseCallback onResponseCallback) {
        if (!TextUtils.isEmpty(str)) {
            str = str.replace("+", "").replaceAll(" ", "");
        }
        String systemLanguage = LanguageUtil.getSystemLanguage();
        AccountApi.api.sendCode(str, str2, str3, systemLanguage).enqueue(getApiCallBack(onResponseCallback));
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "发送验证码时上传的手机系统语言code：" + systemLanguage);
    }

    public static void requestCheckCode(String str, String str2, String str3, OnResponseCallback onResponseCallback) {
        AccountApi.api.checkCode(str, str2, str3).enqueue(getApiCallBack(onResponseCallback));
    }

    public static void validAccount(String str, OnResultCallback onResultCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("account", str);
        AccountApi.api.validAccount(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(getResultBApiCallBack(onResultCallback));
    }

    public static void checkAccountArea(String str, OnResultCallback onResultCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("account", str);
        AccountApi.api.checkAccountArea(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(getCheckAccountArea(onResultCallback));
    }

    public static void requestPasswordReset(String str, String str2, String str3, OnResultCallback onResultCallback) {
        if (!ValidateUtil.checkName(str)) {
            onResultCallback.onFailed(ResourceUtil.getString(R.string.me_input_correct_name));
            return;
        }
        if (!ValidateUtil.checkCode(str3)) {
            onResultCallback.onFailed(ResourceUtil.getString(R.string.mine_tip_input_verification));
            return;
        }
        if (!ValidateUtil.checkPassword(str2)) {
            onResultCallback.onFailed(ResourceUtil.getString(R.string.register_tip_password_format));
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("account", str);
        jsonObject.addProperty("nPassword", str2);
        jsonObject.addProperty("verifyCode", str3);
        RequestBody requestBodyCreate = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "请求重置密码的参数：" + jsonObject.toString());
        AccountApi.api.requestPasswordReset(requestBodyCreate).enqueue(getResultSApiCallBack(onResultCallback));
    }

    public static void requestPasswordModify(String str, String str2, OnResultCallback onResultCallback) {
        if (!ValidateUtil.checkPassword(str)) {
            onResultCallback.onFailed(ResourceUtil.getString(R.string.register_tip_password_format));
            return;
        }
        if (!ValidateUtil.checkPassword(str2)) {
            onResultCallback.onFailed(ResourceUtil.getString(R.string.register_tip_password_format));
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(BindInputCodeActivity.PASSWORD, str);
        jsonObject.addProperty("nPassword", str2);
        RequestBody requestBodyCreate = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "请求修改密码的参数：" + jsonObject.toString());
        AccountApi.api.requestPasswordModify(requestBodyCreate).enqueue(getResultSApiCallBack(onResultCallback));
    }

    public static void updateGender(int i, OnResultCallback onResultCallback) {
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(RunTimeUtil.getInstance().getUserId());
        if (userInfoQueryUserInfo == null) {
            onResultCallback.onFailed(null);
        } else {
            userInfoQueryUserInfo.setGender(i);
            AccountApi.api.updateUserInfo(userInfoQueryUserInfo).enqueue(getResultIApiCallBack(onResultCallback));
        }
    }

    public static void updateUserDisplayName(String str, OnResultCallback onResultCallback) {
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(RunTimeUtil.getInstance().getUserId());
        if (userInfoQueryUserInfo == null) {
            onResultCallback.onFailed(null);
        } else {
            userInfoQueryUserInfo.setDisplayName(str);
            AccountApi.api.updateUserInfo(userInfoQueryUserInfo).enqueue(getResultIApiCallBack(onResultCallback));
        }
    }

    public static void updateHeight(int i, int i2, OnResultCallback onResultCallback) {
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(RunTimeUtil.getInstance().getUserId());
        if (userInfoQueryUserInfo == null) {
            onResultCallback.onFailed(null);
            return;
        }
        userInfoQueryUserInfo.setHeight(i);
        userInfoQueryUserInfo.setHeightUnit(i2);
        AccountApi.api.updateUserInfo(userInfoQueryUserInfo).enqueue(getResultIApiCallBack(onResultCallback));
    }

    public static void updateWeight(int i, int i2, OnResultCallback onResultCallback) {
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(RunTimeUtil.getInstance().getUserId());
        if (userInfoQueryUserInfo == null) {
            onResultCallback.onFailed(null);
            return;
        }
        userInfoQueryUserInfo.setWeightUnit(i2);
        userInfoQueryUserInfo.setWeight(i);
        AccountApi.api.updateUserInfo(userInfoQueryUserInfo).enqueue(getResultIApiCallBack(onResultCallback));
    }

    public static void cancelAccount(String str, String str2, OnResultCallback onResultCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("account", str);
        jsonObject.addProperty("verifyCode", str2);
        RequestBody requestBodyCreate = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "请求注销账号的参数：" + jsonObject.toString());
        AccountApi.api.destoryAccount(requestBodyCreate).enqueue(getResultSApiCallBack(onResultCallback));
    }

    public static void checkEmail(String str, OnResultCallback onResultCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("verifyCode", str);
        RequestBody requestBodyCreate = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "请求验证邮箱的参数：" + jsonObject.toString());
        AccountApi.api.checkEmail(requestBodyCreate).enqueue(getResultSApiCallBack(onResultCallback));
    }

    public static void modifyEmail(String str, String str2, OnResultCallback onResultCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("account", str);
        jsonObject.addProperty("verifyCode", str2);
        RequestBody requestBodyCreate = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "请求修改邮箱账号的参数：" + jsonObject.toString());
        AccountApi.api.modifyEmail(requestBodyCreate).enqueue(getResultSApiCallBack(onResultCallback));
    }

    public static void cancelThirdAccount(String str, String str2, final OnCommCallback<Boolean> onCommCallback) {
        AccountApi.api.cancelThirdAccount(str, str2).enqueue(new ApiCallback<ResultBEntity>() { // from class: com.ido.life.data.me.remote.AccountManager.5
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(ResultBEntity resultBEntity) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onSuccess(Boolean.valueOf(resultBEntity.isResult()));
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str3) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onFailed(str3);
                }
            }
        });
    }

    public static void bindThirdAccount(String str, String str2, final OnCommCallback<String> onCommCallback) {
        AccountApi.api.bindThirdAccount(str, str2).enqueue(new ApiCallback<ResultSEntity>() { // from class: com.ido.life.data.me.remote.AccountManager.6
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(ResultSEntity resultSEntity) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onSuccess(resultSEntity.getResult());
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str3) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onFailed(str3);
                }
            }
        });
    }

    public static void getUserMedal(final OnCommCallback<BaseEntityNew<List<UserMedalInfo>>> onCommCallback) {
        AccountApi.api.getUserMedal().enqueue(new ApiCallback<BaseEntityNew<List<UserMedalInfo>>>() { // from class: com.ido.life.data.me.remote.AccountManager.7
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntityNew<List<UserMedalInfo>> baseEntityNew) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onSuccess(baseEntityNew);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onFailed(str);
                }
            }
        });
    }

    public static void saveUserMedal(final UploadMedal uploadMedal, final OnCommCallback<BaseEntity> onCommCallback) {
        if (uploadMedal != null) {
            if (uploadMedal.getDatas() == null && uploadMedal.getDatas().size() == 0) {
                return;
            }
            CommonLogUtil.d(TAG, GsonUtil.toJson(uploadMedal));
            AccountApi.api.saveUserMedal(uploadMedal).enqueue(new ApiCallback<BaseEntity>() { // from class: com.ido.life.data.me.remote.AccountManager.8
                @Override // com.ido.life.data.listener.NetCallback
                public void onSuccess(BaseEntity baseEntity) {
                    OnCommCallback onCommCallback2 = onCommCallback;
                    if (onCommCallback2 != null) {
                        onCommCallback2.onSuccess(baseEntity);
                        Iterator<UploadMedal.UploadMedalItem> it = uploadMedal.getDatas().iterator();
                        while (it.hasNext()) {
                            UserMedalInfo userMedalInfoQueryUserMedalInfo = GreenDaoUtil.queryUserMedalInfo(RunTimeUtil.getInstance().getUserId(), it.next().getMedalId());
                            if (userMedalInfoQueryUserMedalInfo != null) {
                                userMedalInfoQueryUserMedalInfo.setUploadSuccess(true);
                                userMedalInfoQueryUserMedalInfo.update();
                            }
                        }
                    }
                }

                @Override // com.ido.life.data.listener.NetCallback
                public void onError(Throwable th, int i, String str) {
                    OnCommCallback onCommCallback2 = onCommCallback;
                    if (onCommCallback2 != null) {
                        onCommCallback2.onFailed(str);
                    }
                }
            });
        }
    }

    public static void updateHead(String str, OnResultCallback onResultCallback) {
        File file = new File(str);
        AccountApi.api.uploadFile(MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse(DfuBaseService.MIME_TYPE_OCTET_STREAM), file))).enqueue(getResultSApiCallBack(onResultCallback));
    }

    public static void updateFileFdImage(String str, OnResultCallback onResultCallback) {
        File file = new File(str);
        AccountApi.api.uploadFile(MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse(DfuBaseService.MIME_TYPE_OCTET_STREAM), file))).enqueue(getResultSApiCallBack(onResultCallback));
    }

    public static void updateFileFd(String str, OnResultCallback onResultCallback) {
        File file = new File(str);
        AccountApi.api.uploadFileFdImg(MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse(DfuBaseService.MIME_TYPE_OCTET_STREAM), file))).enqueue(getResultSApiCallBack(onResultCallback));
    }

    public static void updateUserBirthday(String str, OnResultCallback onResultCallback) {
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(RunTimeUtil.getInstance().getUserId());
        if (userInfoQueryUserInfo == null) {
            onResultCallback.onFailed(null);
        } else {
            userInfoQueryUserInfo.setBirthday(str);
            AccountApi.api.updateUserInfo(userInfoQueryUserInfo).enqueue(getResultIApiCallBack(onResultCallback));
        }
    }

    public static void updateUserInfo(UserInfo userInfo, OnResultCallback onResultCallback) {
        if (userInfo != null) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "更新用户信息 userInfo：" + userInfo.toString());
        }
        AccountApi.api.updateUserInfo(userInfo).enqueue(getResultIApiCallBack(onResultCallback));
    }

    public static void updateUserInfoFirstLogin(String str, String str2, int i, int i2, int i3, float f2, int i4, String str3, OnResultCallback onResultCallback) {
        UserInfo userInfo = new UserInfo();
        userInfo.setAvatarUrl(str);
        userInfo.setDisplayName(str2);
        userInfo.setGender(i);
        userInfo.setHeight(i2);
        userInfo.setHeightUnit(i3);
        userInfo.setWeight(f2);
        userInfo.setWeightUnit(i4);
        userInfo.setBirthday(str3);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "更新用户注册成功后第一次设置的所有个人信息 userInfo：" + userInfo.toString());
        AccountApi.api.updateUserInfo(userInfo).enqueue(getResultIApiCallBack(onResultCallback));
    }

    public static void createFeedback(Feedback feedback, OnResultCallback onResultCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("typeId", feedback.getTypeId());
            jSONObject.put("body", feedback.getBody());
            jSONObject.put("image1Url", feedback.getImage1Url());
            jSONObject.put("image2Url", feedback.getImage2Url());
            jSONObject.put("image3Url", feedback.getImage3Url());
            jSONObject.put("contact", feedback.getContact());
            jSONObject.put("linkUrl", feedback.getLinkUrl());
            jSONObject.put("language", "cn");
            BLEDevice currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo();
            BasicInfo basicInfo = LocalDataManager.getBasicInfo();
            if (basicInfo != null && currentDeviceInfo != null) {
                currentDeviceInfo.version = basicInfo.firmwareVersion;
            }
            if (currentDeviceInfo == null) {
                currentDeviceInfo = SPHelper.getUserDevice();
            }
            if (currentDeviceInfo != null) {
                jSONObject.put("deviceId", currentDeviceInfo.mDeviceId);
                jSONObject.put("otaVersion", currentDeviceInfo.version);
                jSONObject.put("sourceMac", currentDeviceInfo.mDeviceAddress);
                jSONObject.put("deviceName", currentDeviceInfo.mDeviceName);
                jSONObject.put("bluetoothName", currentDeviceInfo.mDeviceName);
            }
            jSONObject.put("mobileType", DeviceUtil.getModel());
            jSONObject.put("osName", 2);
            jSONObject.put("osVersion", DeviceUtil.getSdkVersion());
            jSONObject.put("appVersion", BuildConfig.VERSION_NAME);
            jSONObject.put("phoneIEMI", DeviceUtil.getIMEI(IdoApp.getAppContext()));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        CommonLogUtil.d(TAG, "createFeedback: " + jSONObject.toString());
        AccountApi.api.createFeedback(RequestBody.create(MediaType.parse("application/json"), jSONObject.toString())).enqueue(getUserFeedback(onResultCallback));
    }

    public static void queryThirdAccount(String str, final OnCommCallback<List<ThirdLogin>> onCommCallback) {
        AccountApi.api.queryThirdAccount(str).enqueue(new ApiCallback<ThirdLoginEntity>() { // from class: com.ido.life.data.me.remote.AccountManager.9
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(ThirdLoginEntity thirdLoginEntity) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onSuccess(thirdLoginEntity.result);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str2) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onFailed(str2);
                }
            }
        });
    }

    public static void requestAppVersionInfo(final OnCommCallback<AppInfoEntity.AppInfo> onCommCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("version", BuildConfig.VERSION_NAME);
        jsonObject.addProperty("language", LanguagePreference.getLanguageName(IdoApp.getAppContext()));
        AccountApi.api.checkAppVersionInfo(RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())).enqueue(new ApiCallback<AppInfoEntity>() { // from class: com.ido.life.data.me.remote.AccountManager.10
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(AppInfoEntity appInfoEntity) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onSuccess(appInfoEntity.result);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onFailed(str);
                }
            }
        });
    }

    public static void savePriveteSafeSetting(SavePrivateSafeSettingBean savePrivateSafeSettingBean, final OnCommCallback<BaseEntity> onCommCallback) {
        AccountApi.api.savePriveteSafeSetting(savePrivateSafeSettingBean).enqueue(new ApiCallback<BaseEntity>() { // from class: com.ido.life.data.me.remote.AccountManager.11
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntity baseEntity) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onSuccess(baseEntity);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onFailed(str);
                }
            }
        });
    }

    public static void savePrivateSwitchSetting(PrivateSettingSaveBean privateSettingSaveBean, final OnCommCallback<BaseEntity> onCommCallback) {
        AccountApi.api.savePrivateSwitchSetting(privateSettingSaveBean).enqueue(new ApiCallback<BaseEntity>() { // from class: com.ido.life.data.me.remote.AccountManager.12
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntity baseEntity) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onSuccess(baseEntity);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onFailed(str);
                }
            }
        });
    }

    public static void savePrivateIndexCardSetting(PrivateSettingSaveBean privateSettingSaveBean, final OnCommCallback<BaseEntity> onCommCallback) {
        AccountApi.api.savePrivateIndexCardSetting(privateSettingSaveBean).enqueue(new ApiCallback<BaseEntity>() { // from class: com.ido.life.data.me.remote.AccountManager.13
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntity baseEntity) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onSuccess(baseEntity);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onFailed(str);
                }
            }
        });
    }

    public static void savePrivateSportTypeSetting(PrivateSettingSaveBean privateSettingSaveBean, final OnCommCallback<BaseEntity> onCommCallback) {
        AccountApi.api.savePrivateSportTypeSetting(privateSettingSaveBean).enqueue(new ApiCallback<BaseEntity>() { // from class: com.ido.life.data.me.remote.AccountManager.14
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntity baseEntity) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onSuccess(baseEntity);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onFailed(str);
                }
            }
        });
    }

    public static void saveSystemUnitSetting(PrivateSettingSaveBean privateSettingSaveBean, final OnCommCallback<BaseEntity> onCommCallback) {
        AccountApi.api.saveSystemUnitSetting(privateSettingSaveBean).enqueue(new ApiCallback<BaseEntity>() { // from class: com.ido.life.data.me.remote.AccountManager.15
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntity baseEntity) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onSuccess(baseEntity);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onFailed(str);
                }
            }
        });
    }

    public static void saveTempSetting(PrivateSettingSaveBean privateSettingSaveBean, final OnCommCallback<BaseEntity> onCommCallback) {
        AccountApi.api.saveTempSetting(privateSettingSaveBean).enqueue(new ApiCallback<BaseEntity>() { // from class: com.ido.life.data.me.remote.AccountManager.16
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntity baseEntity) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onSuccess(baseEntity);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onFailed(str);
                }
            }
        });
    }

    public static void saveTimeSetting(PrivateSettingSaveBean privateSettingSaveBean, final OnCommCallback<BaseEntity> onCommCallback) {
        AccountApi.api.saveTimeSetting(privateSettingSaveBean).enqueue(new ApiCallback<BaseEntity>() { // from class: com.ido.life.data.me.remote.AccountManager.17
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntity baseEntity) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onSuccess(baseEntity);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onFailed(str);
                }
            }
        });
    }

    public static void saveWeekStartSetting(PrivateSettingSaveBean privateSettingSaveBean, final OnCommCallback<BaseEntity> onCommCallback) {
        AccountApi.api.saveWeekStartSetting(privateSettingSaveBean).enqueue(new ApiCallback<BaseEntity>() { // from class: com.ido.life.data.me.remote.AccountManager.18
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntity baseEntity) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onSuccess(baseEntity);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onFailed(str);
                }
            }
        });
    }

    public static void saveMapEngineSetting(PrivateSettingSaveBean privateSettingSaveBean, final OnCommCallback<BaseEntity> onCommCallback) {
        AccountApi.api.saveMapEngineSetting(privateSettingSaveBean).enqueue(new ApiCallback<BaseEntity>() { // from class: com.ido.life.data.me.remote.AccountManager.19
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntity baseEntity) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onSuccess(baseEntity);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onFailed(str);
                }
            }
        });
    }

    public static void agreePolicyLog(boolean z, final OnCommCallback<String> onCommCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(NotificationCompat.CATEGORY_STATUS, z);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        AccountApi.api.agreePolicyLog(RequestBody.create(MediaType.parse("application/json"), jSONObject.toString())).enqueue(new ApiCallback<ResultSEntity>() { // from class: com.ido.life.data.me.remote.AccountManager.20
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(ResultSEntity resultSEntity) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onSuccess(resultSEntity.getResult());
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onFailed(str);
                }
            }
        });
    }

    public static void getPriveteSafeSetting(final OnCommCallback<BaseEntityNew<List<SavePrivateSafeSettingBean.SavePrivateSafeSettingBeanItem>>> onCommCallback) {
        AccountApi.api.getPriveteSafeSetting().enqueue(new ApiCallback<BaseEntityNew<List<SavePrivateSafeSettingBean.SavePrivateSafeSettingBeanItem>>>() { // from class: com.ido.life.data.me.remote.AccountManager.21
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntityNew<List<SavePrivateSafeSettingBean.SavePrivateSafeSettingBeanItem>> baseEntityNew) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onSuccess(baseEntityNew);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnCommCallback onCommCallback2 = onCommCallback;
                if (onCommCallback2 != null) {
                    onCommCallback2.onFailed("message==" + str + "\tcode==" + i);
                }
            }
        });
    }

    private static ApiCallback<BaseEntity> getApiCallBack(final OnResponseCallback onResponseCallback) {
        return new ApiCallback<BaseEntity>() { // from class: com.ido.life.data.me.remote.AccountManager.22
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntity baseEntity) {
                OnResponseCallback onResponseCallback2 = onResponseCallback;
                if (onResponseCallback2 != null) {
                    onResponseCallback2.onSuccess();
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnResponseCallback onResponseCallback2 = onResponseCallback;
                if (onResponseCallback2 != null) {
                    onResponseCallback2.onFailed(str);
                }
            }
        };
    }

    private static ApiCallback<BaseEntityNew<UserInfo>> getResultIApiCallBack(final OnResultCallback onResultCallback) {
        return new ApiCallback<BaseEntityNew<UserInfo>>() { // from class: com.ido.life.data.me.remote.AccountManager.23
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntityNew<UserInfo> baseEntityNew) {
                Result result = new Result();
                result.setData(baseEntityNew.getResult());
                onResultCallback.onSuccess(result);
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                onResultCallback.onFailed(str);
            }
        };
    }

    private static ApiCallback<ResultBEntity> getResultBApiCallBack(final OnResultCallback onResultCallback) {
        return new ApiCallback<ResultBEntity>() { // from class: com.ido.life.data.me.remote.AccountManager.24
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(ResultBEntity resultBEntity) {
                Result result = new Result();
                result.setData(Boolean.valueOf(resultBEntity.isResult()));
                onResultCallback.onSuccess(result);
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                onResultCallback.onFailed(str);
            }
        };
    }

    private static ApiCallback<BaseEntityNew<String>> getCheckAccountArea(final OnResultCallback onResultCallback) {
        return new ApiCallback<BaseEntityNew<String>>() { // from class: com.ido.life.data.me.remote.AccountManager.25
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntityNew<String> baseEntityNew) {
                Result result = new Result();
                result.setData(baseEntityNew.getResult());
                OnResultCallback onResultCallback2 = onResultCallback;
                if (onResultCallback2 != null) {
                    onResultCallback2.onSuccess(result);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnResultCallback onResultCallback2 = onResultCallback;
                if (onResultCallback2 != null) {
                    onResultCallback2.onFailed(str);
                }
            }
        };
    }

    private static ApiCallback<ResultSEntity> getResultSApiCallBack(final OnResultCallback onResultCallback) {
        return new ApiCallback<ResultSEntity>() { // from class: com.ido.life.data.me.remote.AccountManager.26
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(ResultSEntity resultSEntity) {
                Result result = new Result();
                result.setData(resultSEntity.getResult());
                onResultCallback.onSuccess(result);
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                CommonLogUtil.d(AccountManager.TAG, "onError: " + i + AppInfo.DELIM + str);
                onResultCallback.onFailed(str);
            }
        };
    }

    private static ApiCallback<ResultSEntity> getResultHttpExceptionSApiCallBack(final OnResultLoginRegisterCallback onResultLoginRegisterCallback) {
        return new ApiCallback<ResultSEntity>() { // from class: com.ido.life.data.me.remote.AccountManager.27
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(ResultSEntity resultSEntity) {
                if (resultSEntity == null) {
                    return;
                }
                if (resultSEntity.status == 100030) {
                    CommonLogUtil.d(AccountManager.TAG, "onError: 100030," + resultSEntity.message + AppInfo.DELIM + resultSEntity.getResult());
                    onResultLoginRegisterCallback.onFailed(ErrorCode.LOGIN_FAILED, resultSEntity.getResult());
                    return;
                }
                Result result = new Result();
                result.setData(resultSEntity.getResult());
                onResultLoginRegisterCallback.onSuccess(result);
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                CommonLogUtil.d(AccountManager.TAG, "onError: " + i + AppInfo.DELIM + str);
                onResultLoginRegisterCallback.onFailed(i, str);
            }
        };
    }

    public static void saveMensConfig(PrivateSettingSaveBean privateSettingSaveBean, final CommonCallback<BaseEntityNew<Boolean>> commonCallback) {
        if (privateSettingSaveBean != null) {
            AccountApi.api.saveMensConfig(privateSettingSaveBean).enqueue(new ApiCallback<BaseEntityNew<Boolean>>() { // from class: com.ido.life.data.me.remote.AccountManager.28
                @Override // com.ido.life.data.listener.NetCallback
                public void onSuccess(BaseEntityNew<Boolean> baseEntityNew) {
                    CommonCallback commonCallback2 = commonCallback;
                    if (commonCallback2 != null) {
                        commonCallback2.onSuccess(baseEntityNew);
                    }
                }

                @Override // com.ido.life.data.listener.NetCallback
                public void onError(Throwable th, int i, String str) {
                    CommonCallback commonCallback2 = commonCallback;
                    if (commonCallback2 != null) {
                        commonCallback2.onFailed(i, str);
                    }
                }
            });
        } else if (commonCallback != null) {
            commonCallback.onFailed(-1, "");
        }
    }

    public static UserTargetNew convertUserTargetResponse(UserTargetEntity userTargetEntity) {
        UserTargetEntity userTargetEntity2 = new UserTargetEntity();
        userTargetEntity2.setResult(userTargetEntity.getResult());
        UserTargetNew result = userTargetEntity2.getResult();
        if (result != null) {
            GreenDaoUtil.addUserTarget(result);
            RunTimeUtil.getInstance().setUserId(result.getUserId());
            return result;
        }
        return new UserTargetNew();
    }

    private static ApiCallback<UserWeightEntity> getResultWeightApiCallBack(final OnResultCallback onResultCallback) {
        return new ApiCallback<UserWeightEntity>() { // from class: com.ido.life.data.me.remote.AccountManager.29
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(UserWeightEntity userWeightEntity) {
                Result result = new Result();
                result.setData(userWeightEntity.getResult());
                onResultCallback.onSuccess(result);
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                onResultCallback.onFailed(str);
            }
        };
    }

    public static void saveSportType(String str, final OnCommCallback<Boolean> onCommCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("value", str);
            jSONObject.put("timestamp", TimeUtil.now());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        AccountApi.api.saveSportType(RequestBody.create(MediaType.parse("application/json"), jSONObject.toString())).enqueue(new ApiCallback<BaseEntityNew<Boolean>>() { // from class: com.ido.life.data.me.remote.AccountManager.30
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntityNew<Boolean> baseEntityNew) {
                if (baseEntityNew == null) {
                    return;
                }
                onCommCallback.onSuccess(Boolean.valueOf(baseEntityNew.getResult().booleanValue()));
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str2) {
                CommonLogUtil.d(AccountManager.TAG, "onError: " + i + AppInfo.DELIM + str2);
                onCommCallback.onFailed(str2);
            }
        });
    }

    public static void getPrefs(final OnCommCallback<List<SortBeanUpload>> onCommCallback) {
        AccountApi.api.getPrefs().enqueue(new ApiCallback<SortBeanEntity>() { // from class: com.ido.life.data.me.remote.AccountManager.31
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(SortBeanEntity sortBeanEntity) {
                if (sortBeanEntity == null) {
                    return;
                }
                onCommCallback.onSuccess(sortBeanEntity.result);
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                CommonLogUtil.d(AccountManager.TAG, "onError: " + i + AppInfo.DELIM + str);
                onCommCallback.onFailed(str);
            }
        });
    }

    public static void getPrivicyNewVersion(final OnCommCallback<List<UserAgreementAndPrivacyVersionData>> onCommCallback) {
        AccountApi.api.getPrivicyNewVersion().enqueue(new ApiCallback<UserAgreementAndPrivacyVersionEntity>() { // from class: com.ido.life.data.me.remote.AccountManager.32
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(UserAgreementAndPrivacyVersionEntity userAgreementAndPrivacyVersionEntity) {
                if (userAgreementAndPrivacyVersionEntity == null) {
                    return;
                }
                onCommCallback.onSuccess(userAgreementAndPrivacyVersionEntity.result);
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                CommonLogUtil.d(AccountManager.TAG, "onError: " + i + AppInfo.DELIM + str);
                onCommCallback.onFailed(str);
            }
        });
    }
}