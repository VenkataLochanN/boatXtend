package com.ido.life.data.api;

import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.common.net.BaseEntity;
import com.ido.common.net.BaseEntityNew;
import com.ido.common.net.RetrofitService;
import com.ido.life.bean.PrivateSettingSaveBean;
import com.ido.life.bean.SavePrivateSafeSettingBean;
import com.ido.life.data.api.entity.AppInfoEntity;
import com.ido.life.data.api.entity.FeedbackEntity;
import com.ido.life.data.api.entity.MultilLanguageEntity;
import com.ido.life.data.api.entity.ResultBEntity;
import com.ido.life.data.api.entity.ResultSEntity;
import com.ido.life.data.api.entity.ThirdLoginEntity;
import com.ido.life.data.api.entity.UploadMedal;
import com.ido.life.data.api.entity.UserInfoEntity;
import com.ido.life.data.api.entity.UserTargetEntity;
import com.ido.life.database.model.UserInfo;
import com.ido.life.database.model.UserMedalInfo;
import com.ido.life.database.model.middleModel.SortBeanEntity;
import com.ido.life.database.model.middleModel.UserAgreementAndPrivacyVersionEntity;
import com.ido.life.module.user.bindsetpassword.BindInputCodeActivity;
import com.ido.life.net.BaseUrl;
import java.util.List;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/* JADX INFO: loaded from: classes2.dex */
public interface AccountApi {
    public static final AccountApi api = (AccountApi) RetrofitService.create(AccountApi.class);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("/userapi/policy/log")
    Call<ResultSEntity> agreePolicyLog(@Body RequestBody requestBody);

    @FormUrlEncoded
    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("/userapi/account/oauth/bind/token")
    Call<ResultSEntity> bindThirdAccount(@Field("openId") String str, @Field("account") String str2);

    @FormUrlEncoded
    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("/userapi/account/oauth/cancel/bind/account")
    Call<ResultBEntity> cancelThirdAccount(@Field("openId") String str, @Field("account") String str2);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("userapi/account/region/check")
    Call<BaseEntityNew<String>> checkAccountArea(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("/userapi/app/version/check/update")
    Call<AppInfoEntity> checkAppVersionInfo(@Body RequestBody requestBody);

    @FormUrlEncoded
    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("messageapi/verify/check")
    Call<BaseEntity> checkCode(@Field("account") String str, @Field("type") String str2, @Field(AuthorizationResponseParser.CODE) String str3);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("/userapi/account/email/verify")
    Call<ResultSEntity> checkEmail(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("userapi/feedback/create")
    Call<FeedbackEntity> createFeedback(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("userapi/account/destory")
    Call<ResultSEntity> destoryAccount(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("/userapi/account/status/valid")
    Call<ResultBEntity> getEamilIsExist(@Body RequestBody requestBody);

    @FormUrlEncoded
    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("/app/lang/resource/checkUpdate")
    Call<MultilLanguageEntity> getMultiLanguage(@Field(AuthorizationResponseParser.CODE) String str, @Field("langFileVersion") String str2);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("/userapi/prefs/get")
    Call<SortBeanEntity> getPrefs();

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("/userapi/prefs/get")
    Call<BaseEntityNew<List<SavePrivateSafeSettingBean.SavePrivateSafeSettingBeanItem>>> getPriveteSafeSetting();

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("userapi/app/page/status/get")
    Call<UserAgreementAndPrivacyVersionEntity> getPrivicyNewVersion();

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("userapi/userbody/get")
    Call<UserInfoEntity> getUserInfo();

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("/userapi/medal/get")
    Call<BaseEntityNew<List<UserMedalInfo>>> getUserMedal();

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/sport/target/get")
    Call<UserTargetEntity> getUserTarget();

    @FormUrlEncoded
    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("userapi/public/account/login")
    Call<ResultSEntity> login(@Field("areaCode") String str, @Field("username") String str2, @Field(BindInputCodeActivity.PASSWORD) String str3);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("/userapi/account/oauth/binding/account")
    Call<ResultSEntity> loginThirdBindAlreadyRegister(@Body RequestBody requestBody);

    @FormUrlEncoded
    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("/userapi/oauth2/token/login")
    Call<ResultSEntity> loginThirdThroughAccessToken(@Field("accessToken") String str, @Field("openId") String str2, @Field("tokenSecret") String str3, @Field(FirebaseAnalytics.Param.SOURCE) String str4);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("/userapi/account/create")
    Call<ResultSEntity> modifyEmail(@Body RequestBody requestBody);

    @FormUrlEncoded
    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("/userapi/account/oauth/query")
    Call<ThirdLoginEntity> queryThirdAccount(@Field("account") String str);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("userapi/public/account/reg")
    Call<ResultSEntity> register(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("userapi/account/oauth/binding/account/reg")
    Call<ResultSEntity> requestBindRegisterAccount(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("userapi/account/passwd/change")
    Call<ResultSEntity> requestPasswordModify(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("userapi/account/passwd/forget")
    Call<ResultSEntity> requestPasswordReset(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("/userapi/prefs/mapEngine/save")
    Call<BaseEntity> saveMapEngineSetting(@Body PrivateSettingSaveBean privateSettingSaveBean);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("/userapi/prefs/menses/config/save")
    Call<BaseEntityNew<Boolean>> saveMensConfig(@Body PrivateSettingSaveBean privateSettingSaveBean);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("/userapi/prefs/indexCard/save")
    Call<BaseEntity> savePrivateIndexCardSetting(@Body PrivateSettingSaveBean privateSettingSaveBean);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("/userapi/prefs/sportType/save")
    Call<BaseEntity> savePrivateSportTypeSetting(@Body PrivateSettingSaveBean privateSettingSaveBean);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("/userapi/prefs/config/save")
    Call<BaseEntity> savePrivateSwitchSetting(@Body PrivateSettingSaveBean privateSettingSaveBean);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("/userapi/prefs/config/save")
    Call<BaseEntity> savePriveteSafeSetting(@Body SavePrivateSafeSettingBean savePrivateSafeSettingBean);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("/userapi/prefs/sportType/save")
    Call<BaseEntityNew<Boolean>> saveSportType(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("/userapi/prefs/systemUnit/save")
    Call<BaseEntity> saveSystemUnitSetting(@Body PrivateSettingSaveBean privateSettingSaveBean);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("/userapi/prefs/temperature/save")
    Call<BaseEntity> saveTempSetting(@Body PrivateSettingSaveBean privateSettingSaveBean);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("/userapi/prefs/time/save")
    Call<BaseEntity> saveTimeSetting(@Body PrivateSettingSaveBean privateSettingSaveBean);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("/userapi/medal/V2/save")
    Call<BaseEntity> saveUserMedal(@Body UploadMedal uploadMedal);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("/userapi/prefs/weekStart/save")
    Call<BaseEntity> saveWeekStartSetting(@Body PrivateSettingSaveBean privateSettingSaveBean);

    @FormUrlEncoded
    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("messageapi/verify/create")
    Call<BaseEntity> sendCode(@Field("areaCode") String str, @Field("account") String str2, @Field("type") String str3, @Field("type") String str4);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("userapi/userbody/update/userinfo")
    Call<BaseEntityNew<UserInfo>> updateUserInfo(@Body UserInfo userInfo);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("userapi/userbody/update/avatarUrl")
    @Multipart
    Call<ResultSEntity> uploadFile(@Part MultipartBody.Part part);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("userapi/feedback/upload")
    @Multipart
    Call<ResultSEntity> uploadFileFd(@Part MultipartBody.Part part);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("api/image/feedback/upload")
    @Multipart
    Call<ResultSEntity> uploadFileFdImg(@Part MultipartBody.Part part);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("userapi/account/status/valid")
    Call<ResultBEntity> validAccount(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_USER_HEADER})
    @POST("/userapi/account/email/status")
    Call<BaseEntityNew<Boolean>> verifyEmailHasBind();
}