package com.ido.life.data.api;

import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.ido.common.net.BaseEntity;
import com.ido.common.net.BaseEntityNew;
import com.ido.common.net.RetrofitService;
import com.ido.life.bean.UsageDialBean;
import com.ido.life.bean.WallPaperDialInfo;
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
import com.ido.life.database.model.DeviceWhiteListEntity;
import com.ido.life.database.model.DialUpdateEntity;
import com.ido.life.database.model.WeatherEntity;
import com.ido.life.database.model.middleModel.DialEntity;
import com.ido.life.net.BaseUrl;
import java.util.List;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/* JADX INFO: loaded from: classes2.dex */
public interface DeviceApi {
    public static final DeviceApi api = (DeviceApi) RetrofitService.create(DeviceApi.class);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @POST("/api/user/face/status/update")
    Call<DialUpdateEntity> addDial(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @POST("/api/user/face/collected/add")
    Call<DialUpdateEntity> addDialCollect(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @POST("/api/user/face/collected/cancel")
    Call<DialUpdateEntity> cancelDialCollect(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @POST("/api/ota/dfu/checkUpdate")
    Call<OtaEntity> checkDfuOtaInfo(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @POST("/api/device/face/checkUpdate")
    Call<DialUpdateEntity> checkDialUpdate(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @POST("/api/ota/font/check")
    Call<OtaEntity> checkFlashInfo(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @POST("/api/ota/extensionFirmware/check")
    Call<OtaEntity> checkModuleSystemInfo(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @POST("/api/ota/checkUpdate")
    Call<OtaEntity> checkOtaInfo(@Body RequestBody requestBody);

    @FormUrlEncoded
    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @POST("/api/user/device/disconnect")
    Call<BaseEntity> deleteDevice(@Field("mac") String str);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @POST("/api/user/face/status/destroy")
    Call<DialUpdateEntity> deleteDial(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @POST("/api/user/face/photo/log/get")
    Call<BaseEntityNew<List<UsageDialBean>>> getWallpaperUsageList(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @GET("/api/device/list")
    Call<BaseEntityNew<List<DeviceInfo>>> queryDeviceInfoList(@Query("ids") String str);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @GET("/api/user/face/default/device/get")
    Call<MyDialListEntity> requestBuiltInDialList(@Query("deviceName") String str, @Query("deviceId") String str2, @Query("language") String str3);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @POST("/api/user/device/query")
    Call<DeviceListEntity> requestDeviceList(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @POST("/api/device/whitelist")
    Call<DeviceWhiteListEntity> requestDeviceWhiteList();

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @POST("/api/user/face/collected/query")
    Call<DialMarketDetail> requestDialCollect(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @GET("/api/device/face/v2/get")
    Call<DialEntity> requestDialInfo(@Query("id") long j, @Query("language") String str, @Query("appFaceVersion") String str2, @Query("supportFaceVersion") String str3, @Query("otaVersion") String str4);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @POST("/api/device/face/getByName")
    Call<DialInfoEntity> requestDialInfo(@Query("deviceName") String str, @Query("deviceId") String str2, @Query("faceName") String str3, @Query("language") String str4);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @GET("/api/device/face/v2/list")
    Call<DialMarket> requestDialMarketList(@Query("deviceName") String str, @Query("deviceId") String str2, @Query("language") String str3, @Query("supportFaceVersion") String str4, @Query("appFaceVersion") String str5, @Query("otaVersion") String str6);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @GET("/api/device/facestore/getByCategory")
    Call<DialMarketDetail> requestDialMarketListById(@Query("deviceName") String str, @Query("deviceId") String str2, @Query("language") String str3, @Query("supportFaceVersion") String str4, @Query("appFaceVersion") String str5, @Query("otaVersion") String str6, @Query("pageId") int i, @Query("pageSize") int i2, @Query("categoryId") int i3);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @GET("/api/device/facestore/get")
    Call<DialMarket> requestDialMarketListNew(@Query("deviceName") String str, @Query("deviceId") String str2, @Query("language") String str3, @Query("supportFaceVersion") String str4, @Query("appFaceVersion") String str5, @Query("otaVersion") String str6, @Query("pageSize") int i);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @POST("/api/user/face/status/find")
    Call<DialState> requestDialState(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @GET("/api/device/manual/get")
    Call<BaseEntityNew<String>> requestInstructionInfo(@Query("id") String str);

    @FormUrlEncoded
    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @POST("/device/lang/resource/list")
    Call<RemoteLanguage> requestLanguageList(@Field("deviceName") String str, @Field("deviceId") String str2, @Field("otaVersion") String str3);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @POST("/api/device/face/getByNames")
    Call<MyDialListEntity> requestMyDialInfoFromNames(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @GET("/api/user/face/device/get")
    Call<MyDialListEntity> requestMyDialList(@Query("deviceName") String str, @Query("deviceId") int i, @Query("mac") String str2, @Query("language") String str3, @Query("supportFaceVersion") String str4, @Query("appFaceVersion") String str5, @Query("otaVersion") String str6);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @GET("/api/user/face/device/query")
    Call<DialMarketDetail> requestMyDialRecord(@Query("deviceName") String str, @Query("deviceId") String str2, @Query("language") String str3, @Query("supportFaceVersion") String str4, @Query("appFaceVersion") String str5, @Query("pageId") int i, @Query("pageSize") int i2, @Query("mac") String str6);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @GET("/api/device/facestore/newest/get")
    Call<DialMarketDetail> requestOnlineDial(@Query("deviceName") String str, @Query("deviceId") String str2, @Query("language") String str3, @Query("supportFaceVersion") String str4, @Query("appFaceVersion") String str5, @Query("otaVersion") String str6, @Query("pageId") int i, @Query("pageSize") int i2);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @GET("/api/device/facestore/recommend/get")
    Call<DialMarketDetail> requestRecomandDial(@Query("deviceName") String str, @Query("deviceId") String str2, @Query("language") String str3, @Query("supportFaceVersion") String str4, @Query("appFaceVersion") String str5, @Query("otaVersion") String str6, @Query("pageId") int i, @Query("pageSize") int i2);

    @FormUrlEncoded
    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @POST("/api/device/face/query")
    Call<TopDialPlateEntity> requestTopDialPlate(@Field("deviceName") String str, @Field("deviceId") String str2, @Field("language") String str3, @Field("appFaceVersion") String str4, @Field("supportFaceVersion") String str5, @Field("otaVersion") String str6);

    @FormUrlEncoded
    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @POST("/api/weather/query")
    Call<WeatherEntity> requestWeatherData(@Field("longitude") String str, @Field("latitude") String str2, @Field("type") int i, @Field(AuthorizationResponseParser.CODE) String str3);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @POST("/api/ota/update/dfu/add")
    Call<BaseEntityNew<Boolean>> updateDfuOtaLog(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @POST("/api/ota/font/update/log")
    Call<BaseEntityNew<Boolean>> updateFlashLog(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @POST("/api/ota/update/add")
    Call<BaseEntityNew<Boolean>> updateOtaLog(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @POST("/api/user/face/photo/log/notify")
    Call<BaseEntityNew<Object>> updateWallpaperUsage(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @POST("/api/user/device/connect")
    Call<BaseEntity> uploadDevice(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @POST("/api/face/install/log")
    Call<BaseEntityNew<Boolean>> uploadDialLog(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @POST("/api/device/lang/update/log")
    Call<BaseEntityNew<Boolean>> uploadLanguageLog(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_DEVICE_HEADER})
    @POST("/api/user/face/photo/add")
    Call<BaseEntityNew<WallPaperDialInfo>> uploadWallWallpaperDial(@Body RequestBody requestBody);
}