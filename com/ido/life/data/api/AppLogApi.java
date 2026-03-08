package com.ido.life.data.api;

import com.ido.common.net.BaseEntityNew;
import com.ido.common.net.RetrofitService;
import com.ido.life.net.BaseUrl;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/* JADX INFO: loaded from: classes2.dex */
public interface AppLogApi {
    public static final AppLogApi api = (AppLogApi) RetrofitService.create(AppLogApi.class);

    @Headers({BaseUrl.URL_NAME_APP_LOG})
    @POST("/api/events/log")
    Call<BaseEntityNew<Boolean>> uploadAppLog(@Body RequestBody requestBody);
}