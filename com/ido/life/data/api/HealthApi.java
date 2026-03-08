package com.ido.life.data.api;

import com.ido.common.net.BaseEntity;
import com.ido.common.net.BaseEntityNew;
import com.ido.common.net.DataListEntity;
import com.ido.common.net.RetrofitService;
import com.ido.life.bean.DistenceDetailUploadBean;
import com.ido.life.bean.ServerBean;
import com.ido.life.bean.ServerHeartRateDayUploadBean;
import com.ido.life.bean.UploadPressureBean;
import com.ido.life.data.api.entity.DataSyncCountItem;
import com.ido.life.data.api.entity.ResultBEntity;
import com.ido.life.data.api.entity.ResultSEntity;
import com.ido.life.data.api.entity.SportHealthDetailEntity;
import com.ido.life.data.api.entity.SportHealthDetailSidEntity;
import com.ido.life.data.api.entity.SportSummaryEntity;
import com.ido.life.data.api.entity.UploadWeightBean;
import com.ido.life.data.api.entity.UserTargetEntity;
import com.ido.life.database.model.CalorieDayData;
import com.ido.life.database.model.HealthPressure;
import com.ido.life.database.model.ServerBloodOxyDayData;
import com.ido.life.database.model.ServerMenstrual;
import com.ido.life.database.model.ServerSleepDayData;
import com.ido.life.database.model.SportDistanceBean;
import com.ido.life.database.model.SportHealth;
import com.ido.life.database.model.SportHealthUpLoad;
import com.ido.life.database.model.StepDayData;
import com.ido.life.database.model.WalkDayData;
import com.ido.life.database.model.WeightItemBean;
import com.ido.life.database.model.middleModel.ActiveTimeEntity;
import com.ido.life.database.model.middleModel.BloodOxyDaysEntity;
import com.ido.life.database.model.middleModel.BloodOxyEntity;
import com.ido.life.database.model.middleModel.BloodOxyMonthEntity;
import com.ido.life.database.model.middleModel.CalorieEntity;
import com.ido.life.database.model.middleModel.HeartRateDateAreaEntity;
import com.ido.life.database.model.middleModel.HeartRateDateEntity;
import com.ido.life.database.model.middleModel.SleepDaysEntity;
import com.ido.life.database.model.middleModel.SleepEntity;
import com.ido.life.database.model.middleModel.SleepMonthEntity;
import com.ido.life.database.model.middleModel.SportUploadResult;
import com.ido.life.database.model.middleModel.StepEntity;
import com.ido.life.database.model.middleModel.WalkEntity;
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
public interface HealthApi {
    public static final HealthApi api = (HealthApi) RetrofitService.create(HealthApi.class);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/data/destroy")
    Call<BaseEntity> clearHealthData(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/menstrual/v2/destroy")
    Call<BaseEntityNew<Boolean>> deleteMenstrualV2(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/sports/destroy")
    Call<BaseEntity> deleteSportRecordBySid(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/bloodoxy/get")
    Call<BaseEntityNew<List<ServerBloodOxyDayData>>> getBloodOxyByDateArea(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/calorie/get")
    Call<BaseEntityNew<List<CalorieDayData>>> getCalorieByDateArea(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/data/sync/count")
    Call<BaseEntityNew<List<DataSyncCountItem>>> getDataSyncCount();

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/exercise/find")
    Call<ActiveTimeEntity> getDayActiveTime(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/bloodoxy/find")
    Call<BloodOxyEntity> getDayBloodOxy(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/calorie/find")
    Call<CalorieEntity> getDayCalorie(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/sleep/find")
    Call<SleepEntity> getDaySleep(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/steps/find")
    Call<StepEntity> getDayStep(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/walk/find")
    Call<WalkEntity> getDayWalkData(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/walk/get")
    Call<BaseEntityNew<List<WalkDayData>>> getDayWalkDataByDateArea(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/distance/find")
    Call<BaseEntityNew<SportDistanceBean>> getDistanceDetailByDate(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/distance/get")
    Call<BaseEntityNew<List<SportDistanceBean>>> getDistanceDetailByDateArea(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/heartrate/V2/find")
    Call<HeartRateDateEntity> getHeartRateByDate(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/heartrate/get")
    Call<HeartRateDateAreaEntity> getHeartRateByDateArea(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/menses/getLatest")
    Call<BaseEntityNew<ServerMenstrual>> getLatestMenstrual(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/bloodoxy/status/days")
    Call<BloodOxyDaysEntity> getMultiDailyBloodOxy(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/sleep/status/days")
    Call<SleepDaysEntity> getMultiDailySleep(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/bloodoxy/status/months")
    Call<BloodOxyMonthEntity> getMultiMonthBloodOxy(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/sleep/status/months")
    Call<SleepMonthEntity> getMultiMonthSleep(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/pressure/find")
    Call<BaseEntityNew<HealthPressure>> getPresureDetailByDate(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/pressure/get")
    Call<BaseEntityNew<List<HealthPressure>>> getPresureDetailByDateArea(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/sleep/get")
    Call<BaseEntityNew<List<ServerSleepDayData>>> getSleepByDateArea(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/sports/find")
    Call<SportHealthDetailEntity> getSportDetailBySid(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/sports/getRange")
    Call<BaseEntityNew<List<SportHealth>>> getSportRecordByDateArea(@Body RequestBody requestBody);

    @FormUrlEncoded
    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/sports/get")
    Call<DataListEntity<SportHealth>> getSportRecords(@Field("lastId") String str, @Field("pageSize") int i, @Field("type") int i2);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/steps/get")
    Call<BaseEntityNew<List<StepDayData>>> getStepByDateArea(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/sports/summary/get")
    Call<SportSummaryEntity> getSummary(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/sport/target/get")
    Call<UserTargetEntity> getUserTarget();

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/weight/find")
    Call<BaseEntityNew<WeightItemBean>> getWeightByDate(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/weight/get")
    Call<BaseEntityNew<List<WeightItemBean>>> getWeightByDateArea(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/sport/target/save")
    Call<ResultBEntity> setUserTarget(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/exercise/upload")
    Call<BaseEntity> uploadActiveTimeData(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/bloodoxy/upload")
    Call<BaseEntity> uploadBloodOxyData(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/calorie/upload")
    Call<BaseEntity> uploadCalorieData(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/distance/upload")
    Call<BaseEntity> uploadDistanceDetail(@Body DistenceDetailUploadBean distenceDetailUploadBean);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/sports/image/upload")
    @Multipart
    Call<ResultSEntity> uploadFileFdImg(@Part MultipartBody.Part part);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/heartrate/upload")
    Call<BaseEntity> uploadHeartRateData(@Body ServerHeartRateDayUploadBean serverHeartRateDayUploadBean);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/menstrual/v2/upload")
    Call<BaseEntityNew<Boolean>> uploadLifeCycle(@Body ServerBean.LifeCycleUploadBean lifeCycleUploadBean);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/menses/upload")
    Call<BaseEntityNew<Boolean>> uploadMenstrual(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/menstrual/v2/upload")
    Call<BaseEntityNew<Boolean>> uploadMenstrualV2(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/pressure/upload")
    Call<BaseEntity> uploadPressure(@Body UploadPressureBean uploadPressureBean);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/sleep/upload")
    Call<BaseEntity> uploadSleepData(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/sports/upload")
    Call<SportUploadResult> uploadSportData(@Body SportHealthUpLoad sportHealthUpLoad);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/sports/upload")
    Call<SportHealthDetailSidEntity> uploadSportHealth(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/steps/upload")
    Call<BaseEntity> uploadStepData(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/walk/upload")
    Call<BaseEntity> uploadWalkData(@Body RequestBody requestBody);

    @Headers({BaseUrl.URL_NAME_HEALTH_HEADER})
    @POST("/api/weight/upload")
    Call<BaseEntity> uploadWeight(@Body UploadWeightBean uploadWeightBean);
}