package com.ido.life.data.api;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.ido.common.net.RetrofitService;
import com.ido.life.bean.GoogleGeoCodeResult;
import com.ido.life.data.api.entity.ResultSEntity;
import com.ido.life.net.BaseUrl;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/* JADX INFO: loaded from: classes2.dex */
public interface StravaApi {
    public static final StravaApi api = (StravaApi) RetrofitService.getInstance().initDownloadRetrofit().create(StravaApi.class);

    @Headers({BaseUrl.URL_NAME_GOOGLE_MAP})
    @GET("geocode/json")
    Call<GoogleGeoCodeResult> queryAddress(@Path("latlng") String str, @Path("language") String str2);

    @FormUrlEncoded
    @Headers({BaseUrl.URL_NAME_STRAVA_HEADER})
    @POST("api/v3/activities")
    Call<ResultSEntity> uploadActivityData(@Field(AppMeasurementSdk.ConditionalUserProperty.NAME) String str, @Field("type") String str2, @Field("access_token") String str3);
}